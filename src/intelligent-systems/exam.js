export const EXAM_COUNT = 40;
export const PRACTICE_COUNT = 12;
export const DEFAULT_MINUTES = 30;
export const DURATIONS = [30, 40, 60];
export const HISTORY_LIMIT = 20;

export const TOPICS = [
  'Fondamenti, regolamentazione e sensori',
  'Progettazione e preparazione dei dati',
  'Dataset, similarità e retrieval',
  'Feature engineering, visualizzazione e alberi',
  'LLM, validazione e agenti',
  'Metriche, regressione e riduzione dimensionale',
  'Classificatori, reti neurali e deep learning',
  'Transfer learning, visione e AIoT',
];

export const topicOf = lesson => Math.floor((lesson - 1) / 3);
export const lessonLabel = lesson => `Lezione ${String(lesson).padStart(2, '0')}`;

function shuffled(items, random) {
  const result = [...items];
  for (let i = result.length - 1; i > 0; i--) {
    const j = Math.floor(random() * (i + 1));
    [result[i], result[j]] = [result[j], result[i]];
  }
  return result;
}

export function missedIds(history) {
  const seen = new Set();
  const missed = new Set();
  for (const attempt of history) {
    for (const { id } of attempt.items) {
      if (seen.has(id)) continue;
      seen.add(id);
      if (attempt.outcomes[id] !== 'correct') missed.add(id);
    }
  }
  return missed;
}

export function createAttempt(bank, history, settings, now = Date.now(), random = Math.random) {
  const { mode, topic, minutes } = settings;
  const lastUsed = new Map();
  for (const [index, attempt] of history.entries()) {
    for (const item of attempt.items) {
      if (!lastUsed.has(item.id)) lastUsed.set(item.id, history.length - index);
    }
  }
  const ranked = shuffled(bank, random).sort((a, b) => (lastUsed.get(a.id) ?? 0) - (lastUsed.get(b.id) ?? 0));
  let questions;
  if (mode === 'exam') {
    const chosen = new Map();
    for (let topicIndex = 0; topicIndex < TOPICS.length; topicIndex++) {
      const pool = ranked.filter(q => topicOf(q.lesson) === topicIndex);
      const lessons = shuffled([1, 2, 3], random).map(offset => topicIndex * 3 + offset);
      // Include every lesson and at least two applied questions in each topic.
      for (const [index, lesson] of lessons.entries()) {
        const question = pool.find(q => q.lesson === lesson && q.applied === (index < 2))
          ?? pool.find(q => q.lesson === lesson);
        if (question) chosen.set(question.id, question);
      }
      const families = new Set([...chosen.values()].map(q => q.family ?? q.id));
      const extra = pool.filter(q => !families.has(q.family ?? q.id));
      for (const question of distinctFamilies(extra).slice(0, 2)) {
        chosen.set(question.id, question);
      }
    }
    questions = shuffled(chosen.values(), random).slice(0, EXAM_COUNT);
  } else {
    const missed = mode === 'mistakes' ? missedIds(history) : null;
    const pool = missed ? ranked.filter(q => missed.has(q.id)) : ranked.filter(q => topicOf(q.lesson) === topic);
    questions = distinctFamilies(pool).slice(0, PRACTICE_COUNT);
    if (questions.length < PRACTICE_COUNT) {
      const ids = new Set(questions.map(q => q.id));
      questions.push(...pool.filter(q => !ids.has(q.id)).slice(0, PRACTICE_COUNT - questions.length));
    }
  }
  if (questions.length === 0) return null;

  return {
    id: `${now}-${Math.floor(random() * Number.MAX_SAFE_INTEGER).toString(36)}`,
    mode,
    topic,
    startedAt: now,
    deadline: mode === 'exam' ? now + minutes * 60_000 : null,
    items: questions.map(q => ({ id: q.id, order: shuffled(q.options.map((_, index) => index), random) })),
    answers: {},
    flags: {},
    position: 0,
  };
}

function distinctFamilies(questions) {
  const seen = new Set();
  return questions.filter(question => {
    const key = question.family ?? question.id;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

export function gradeAttempt(attempt, questions, reason, now = Date.now()) {
  const counts = { correct: 0, wrong: 0, blank: 0 };
  const outcomes = {};
  for (const { id } of attempt.items) {
    const selected = attempt.answers[id];
    const outcome = selected === undefined ? 'blank' : selected === questions.get(id).answer ? 'correct' : 'wrong';
    outcomes[id] = outcome;
    counts[outcome]++;
  }
  const total = attempt.items.length;
  const grade = attempt.mode === 'exam'
    ? counts.correct === total ? 31 : Math.min(30, Math.round(31 * counts.correct / total))
    : null;
  return {
    ...attempt,
    ...counts,
    outcomes,
    total,
    grade,
    reason,
    finishedAt: attempt.deadline ? Math.min(now, attempt.deadline) : now,
  };
}
