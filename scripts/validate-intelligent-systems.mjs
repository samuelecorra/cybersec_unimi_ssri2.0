import assert from 'node:assert/strict';
import { bank } from '../src/intelligent-systems/questions.js';
import {
  createAttempt, DEFAULT_MINUTES, EXAM_COUNT, gradeAttempt, missedIds, PRACTICE_COUNT, topicOf, TOPICS,
} from '../src/intelligent-systems/exam.js';
import { COURSE_PATH, EXAM_PATH, LESSONS_PATH } from '../src/intelligent-systems/routes.js';
import { buildHashRouteIndex, createHashPath, resolveHashRoute } from '../src/utils/hashRouting.js';

const byId = new Map(bank.map(question => [question.id, question]));
assert.equal(byId.size, bank.length, 'Question IDs must be unique');
assert.equal(new Set(bank.map(question => question.prompt)).size, bank.length, 'Prompts must be unique');
assert.ok(bank.length >= 200);
for (const q of bank) {
  assert.ok(q.lesson >= 1 && q.lesson <= 24, q.id);
  assert.ok(q.prompt && q.explanation, q.id);
  assert.equal(new Set(q.options).size, q.options.length, q.id);
  assert.ok(q.options[q.answer], q.id);
}

for (const path of [COURSE_PATH, EXAM_PATH, LESSONS_PATH]) {
  const index = buildHashRouteIndex([{ type: 'dir', path: COURSE_PATH, children: [] }]);
  assert.equal(resolveHashRoute(createHashPath(path), index).path, path);
}
assert.equal(resolveHashRoute(createHashPath(EXAM_PATH), buildHashRouteIndex([])).kind, 'invalid');

const startedAt = 1_000_000;
const settings = { mode: 'exam', minutes: DEFAULT_MINUTES };
for (let i = 0; i < 40; i++) {
  const attempt = createAttempt(bank, [], settings, startedAt);
  const questions = attempt.items.map(item => byId.get(item.id));
  assert.equal(questions.length, EXAM_COUNT);
  assert.equal(new Set(questions.map(q => q.family ?? q.id)).size, EXAM_COUNT);
  assert.equal(new Set(questions.map(q => q.lesson)).size, 24);
  for (let topic = 0; topic < TOPICS.length; topic++) {
    const group = questions.filter(q => topicOf(q.lesson) === topic);
    assert.equal(group.length, 5);
    assert.ok(group.filter(q => q.applied).length >= 2);
  }
  for (const { id, order } of attempt.items) {
    assert.deepEqual([...order].sort(), byId.get(id).options.map((_, index) => index));
  }
}

const exam = createAttempt(bank, [], settings, startedAt);
assert.equal(exam.deadline, startedAt + 30 * 60_000);
const blank = gradeAttempt(exam, byId, 'timeout', exam.deadline + 1000);
assert.equal(blank.finishedAt, exam.deadline);
assert.equal(blank.blank, EXAM_COUNT);
assert.equal(blank.grade, 0);

const answers = Object.fromEntries(exam.items.map(({ id }) => [id, byId.get(id).answer]));
const perfect = gradeAttempt({ ...exam, answers }, byId, 'submitted', startedAt + 1000);
assert.equal(perfect.correct, EXAM_COUNT);
assert.equal(perfect.grade, 31);
const [wrong, omitted] = exam.items;
answers[wrong.id] = (byId.get(wrong.id).answer + 1) % byId.get(wrong.id).options.length;
delete answers[omitted.id];
const mixed = gradeAttempt({ ...exam, answers }, byId, 'submitted', startedAt + 2000);
assert.deepEqual([mixed.correct, mixed.wrong, mixed.blank, mixed.grade], [38, 1, 1, 29]);
assert.deepEqual(missedIds([mixed, perfect]), new Set([wrong.id, omitted.id]));
assert.equal(missedIds([perfect, mixed]).size, 0);
assert.equal(createAttempt(bank, [perfect], { mode: 'mistakes' }), null);

const mistakes = createAttempt(bank, [mixed], { mode: 'mistakes' });
assert.deepEqual(new Set(mistakes.items.map(item => item.id)), new Set([wrong.id, omitted.id]));
assert.equal(mistakes.deadline, null);
assert.equal(gradeAttempt(mistakes, byId, 'submitted').grade, null);
const topic = createAttempt(bank, [], { mode: 'topic', topic: 5 });
assert.equal(topic.items.length, PRACTICE_COUNT);
assert.ok(topic.items.every(item => topicOf(byId.get(item.id).lesson) === 5));
console.log(`INTELLIGENT_SYSTEMS_OK bank=${bank.length} lessons=24 exam=${EXAM_COUNT}`);
