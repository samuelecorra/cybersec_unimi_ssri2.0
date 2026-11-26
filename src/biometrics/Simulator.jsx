import ExamSimulator from '../exam-simulator/ExamSimulator.jsx';
import { biometrics } from '../exam-simulator/courses.js';
import { bank } from './questions.js';

export default function Simulator({ lessonPaths }) {
  return <ExamSimulator course={biometrics} bank={bank} lessonPaths={lessonPaths} />;
}
