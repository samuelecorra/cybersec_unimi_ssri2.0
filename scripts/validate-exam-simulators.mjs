import assert from 'node:assert/strict';
import { readdirSync } from 'node:fs';
import { bank as biometricsBank } from '../src/biometrics/questions.js';
import { bank as intelligentSystemsBank } from '../src/intelligent-systems/questions.js';
import {
  completeAttempt, createAttempt, examFormat, gradeAttempt, missedIds, normalizeExamRecord, orderedActivity,
  PRACTICE_COUNT, topicOf,
} from '../src/exam-simulator/exam.js';
import { biometrics, examPathFor, intelligentSystems, lessonsPathFor } from '../src/exam-simulator/courses.js';
import { buildHashRouteIndex, createHashPath, resolveHashRoute } from '../src/utils/hashRouting.js';

function validateBank(course, bank) {
  const lessonCount = course.topics.length * 3;
  const byId = new Map(bank.map(question => [question.id, question]));
  assert.equal(byId.size, bank.length, 'Question IDs must be unique');
  assert.equal(new Set(bank.map(question => question.prompt)).size, bank.length, 'Prompts must be unique');
  assert.ok(bank.length >= 200);
  for (const q of bank) {
    assert.ok(q.lesson >= 1 && q.lesson <= lessonCount, q.id);
    assert.ok(q.prompt && q.explanation, q.id);
    assert.ok(q.options.length >= 2, q.id);
    assert.equal(new Set(q.options).size, q.options.length, q.id);
    assert.ok(q.options[q.answer], q.id);
  }

  for (let lesson = 1; lesson <= lessonCount; lesson++) {
    const number = String(lesson).padStart(2, '0');
    const directory = new URL(`../lessons/${course.path}/Lezione${number}/`, import.meta.url);
    assert.ok(readdirSync(directory).some(file => file.startsWith(`L${number} - `) && file.endsWith('.md')),
      `Missing notes for ${course.title} lesson ${number}`);
  }

  const index = buildHashRouteIndex([{ type: 'dir', path: course.path, children: [] }]);
  for (const path of [course.path, examPathFor(course), lessonsPathFor(course)]) {
    assert.equal(resolveHashRoute(createHashPath(path), index).path, path);
  }
  assert.equal(resolveHashRoute(createHashPath(examPathFor(course)), buildHashRouteIndex([])).kind, 'invalid');

  const startedAt = 1_000_000;
  const examSettings = { mode: 'exam', minutes: course.defaultMinutes };
  assert.equal(examFormat(course).id, course.formats[0].id);

  for (const format of course.formats) {
    const quota = format.count / course.topics.length;
    for (let i = 0; i < 40; i++) {
      const attempt = createAttempt(course, bank, [], { ...examSettings, format: format.id }, startedAt);
      const questions = attempt.items.map(item => byId.get(item.id));
      assert.equal(attempt.format, format.id);
      assert.equal(questions.length, format.count);
      assert.equal(new Set(questions.map(q => q.family ?? q.id)).size, format.count);
      assert.equal(new Set(questions.map(q => q.lesson)).size, lessonCount);
      for (let topic = 0; topic < course.topics.length; topic++) {
        const group = questions.filter(q => topicOf(q.lesson) === topic);
        assert.ok(group.length >= Math.floor(quota) && group.length <= Math.ceil(quota));
        assert.ok(group.filter(q => q.applied).length >= 2);
      }
      for (const { id, order } of attempt.items) {
        assert.deepEqual([...order].sort(), byId.get(id).options.map((_, index) => index));
      }
    }

    const exam = createAttempt(course, bank, [], { ...examSettings, format: format.id }, startedAt);
    assert.equal(exam.deadline, startedAt + course.defaultMinutes * 60_000);
    const blank = gradeAttempt(exam, byId, 'timeout', exam.deadline + 1000);
    assert.equal(blank.finishedAt, exam.deadline);
    assert.equal(blank.blank, format.count);
    assert.equal(blank.grade, 0);

    const answers = Object.fromEntries(exam.items.map(({ id }) => [id, byId.get(id).answer]));
    const perfect = gradeAttempt({ ...exam, answers }, byId, 'submitted', startedAt + 1000);
    assert.equal(perfect.correct, format.count);
    assert.equal(perfect.grade, 31);
    const [wrong, omitted] = exam.items;
    answers[wrong.id] = (byId.get(wrong.id).answer + 1) % byId.get(wrong.id).options.length;
    delete answers[omitted.id];
    const mixed = gradeAttempt({ ...exam, answers }, byId, 'submitted', startedAt + 2000);
    assert.deepEqual([mixed.correct, mixed.wrong, mixed.blank, mixed.grade], [format.count - 2, 1, 1, 29]);
    assert.deepEqual(missedIds([mixed, perfect]), new Set([wrong.id, omitted.id]));
    assert.equal(missedIds([perfect, mixed]).size, 0);
    assert.equal(createAttempt(course, bank, [perfect], { mode: 'mistakes' }), null);

    const mistakes = createAttempt(course, bank, [mixed], { mode: 'mistakes' }, startedAt + 3000);
    assert.deepEqual(new Set(mistakes.items.map(item => item.id)), new Set([wrong.id, omitted.id]));
    assert.equal(mistakes.deadline, null);
    const legacyReview = gradeAttempt({ ...mistakes, id: 'legacy-review' }, byId, 'submitted', startedAt + 2500);
    assert.equal(legacyReview.grade, null);
    const saved = { ...mistakes, answers: { [wrong.id]: byId.get(wrong.id).answer } };
    const migrated = normalizeExamRecord({ active: saved, history: [legacyReview, mixed] });
    assert.equal(migrated.active, null);
    assert.equal(migrated.mistakes, saved);
    assert.deepEqual(migrated.history, [mixed]);
    assert.deepEqual(migrated.reviews, [legacyReview]);

    const concurrent = { ...migrated, active: exam };
    assert.equal(completeAttempt(concurrent, 'active', exam.id, byId, 'timeout', exam.deadline - 1), concurrent);
    assert.equal(completeAttempt(concurrent, 'mistakes', 'stale-id', byId, 'submitted', startedAt + 4000), concurrent);
    const finishedReview = completeAttempt(concurrent, 'mistakes', saved.id, byId, 'submitted', startedAt + 4000);
    assert.equal(finishedReview.mistakes, null);
    assert.equal(finishedReview.active, exam);
    assert.deepEqual(finishedReview.history, [mixed]);
    assert.deepEqual(missedIds(orderedActivity(finishedReview.history, finishedReview.reviews)), new Set([omitted.id]));
    const finishedExam = completeAttempt(concurrent, 'active', exam.id, byId, 'timeout', exam.deadline);
    assert.equal(finishedExam.active, null);
    assert.equal(finishedExam.mistakes, saved);
    assert.equal(finishedExam.history[0].reason, 'timeout');
  }

  const topic = createAttempt(course, bank, [], { mode: 'topic', topic: 5 });
  assert.equal(topic.items.length, PRACTICE_COUNT);
  assert.ok(topic.items.every(item => topicOf(byId.get(item.id).lesson) === 5));
  console.log(`EXAM_SIMULATOR_OK ${course.title} bank=${bank.length} lessons=${lessonCount}`);
}

assert.equal(intelligentSystems.storageKey, 'cyberlocker:intelligent-systems:v1');
assert.notEqual(biometrics.storageKey, intelligentSystems.storageKey);
assert.equal(examFormat(biometrics, 'lesson21').target, 25);
validateBank(intelligentSystems, intelligentSystemsBank);
if (process.argv[2] !== 'intelligent-systems') validateBank(biometrics, biometricsBank);
