export const intelligentSystems = {
  path: 'cybersecurity/extra/1_Intelligent_Systems',
  title: 'Intelligent Systems',
  subtitle: 'For Industry, Supply Chain and Environment',
  lang: 'en',
  language: 'inglese',
  formats: [{ id: 'standard', count: 40 }],
  defaultMinutes: 30,
  durations: [30, 40, 60],
  storageKey: 'cyberlocker:intelligent-systems:v1',
  formatNote: 'Il gruppo del corso riporta 40 quesiti in 30 minuti per un appello. La scheda UniMi indica circa un’ora.',
  topics: [
    'Fondamenti, regolamentazione e sensori',
    'Progettazione e preparazione dei dati',
    'Dataset, similarità e retrieval',
    'Feature engineering, visualizzazione e alberi',
    'LLM, validazione e agenti',
    'Metriche, regressione e riduzione dimensionale',
    'Classificatori, reti neurali e deep learning',
    'Transfer learning, visione e AIoT',
  ],
  sources: [
    ['Quiz della community', 'https://github.com/gorkembaslik/quiz-app'],
    ['Scheda UniMi', 'https://www.unimi.it/it/corsi/insegnamenti-dei-corsi-di-laurea/2026/intelligent-systems-industry-supply-chain-and-environment-0'],
  ],
};

export const biometrics = {
  path: 'cybersecurity/extra/2_Tecniche_ed_applicazioni_biometriche',
  title: 'Tecniche e applicazioni biometriche',
  subtitle: 'Già Complementi di Biometria',
  lang: 'it',
  language: 'italiano',
  formats: [
    { id: 'standard', count: 40, label: '40 quesiti · formato appello' },
    { id: 'lesson21', count: 33, label: '33 quesiti · griglia di lezione 21', title: 'Simulazione della lezione 21', target: 25 },
  ],
  defaultMinutes: 40,
  durations: [30, 40, 60],
  storageKey: 'cyberlocker:biometric-techniques:v1',
  formatNote: 'Il gruppo riporta 40 quesiti in 40 minuti per un appello. La scheda UniMi indica circa un’ora. La griglia di autovalutazione della lezione 21 ha 33 quesiti con obiettivo di almeno 25 punti.',
  topics: [
    'Fondamenti, soglie e impronte digitali',
    'Iride e apprendimento automatico',
    'CNN, template update e metriche',
    'Statistica, ottiche e sensori',
    'Privacy, accessi e fusione multimodale',
    'Biometria comportamentale, deepfake e Re-ID',
    'Antispoofing, autenticazione e applicazioni',
  ],
  sources: [
    ['Scheda UniMi', 'https://www.unimi.it/it/corsi/insegnamenti-dei-corsi-di-laurea/2026/tecniche-e-applicazioni-biometriche-0'],
    ['Materiali su myAriel', 'https://myariel.unimi.it/course/view.php?id=10070'],
    ['Griglia di autovalutazione', 'https://myariel.unimi.it/mod/resource/view.php?id=142961'],
    ['Quiz della community', 'https://quiz-biometria.netlify.app/'],
  ],
};

export const examCourses = [intelligentSystems, biometrics];
export const lessonsPathFor = course => `${course.path}/lezioni`;
export const examPathFor = course => `${course.path}/simulatore`;
export const findExamCourse = path => examCourses.find(course => path === course.path || path.startsWith(`${course.path}/`));
