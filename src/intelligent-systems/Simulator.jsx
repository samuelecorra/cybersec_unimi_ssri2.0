import ExamSimulator from '../exam-simulator/ExamSimulator.jsx';
import { intelligentSystems } from '../exam-simulator/courses.js';
import { bank } from './questions.js';

export default function Simulator({ lessonPaths }) {
  return <ExamSimulator course={intelligentSystems} bank={bank} lessonPaths={lessonPaths} />;
}
