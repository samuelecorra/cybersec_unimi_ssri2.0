import { applications } from './applications.js';
import { exercises } from './exercises.js';
import { foundations } from './foundations.js';

export const bank = [
  ...[...foundations, ...applications].flatMap(([lesson, rows]) =>
    rows.map(([suffix, format, prompt, correct, ...rest]) => ({
      id: `b${lesson}-${suffix}`,
      lesson,
      applied: format === 'caso',
      prompt,
      options: [correct, ...rest.slice(0, -1)],
      answer: 0,
      explanation: rest.at(-1),
    })),
  ),
  ...exercises,
];
