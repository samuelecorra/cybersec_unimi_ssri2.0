const decimal = (value, digits = 2) => value.toFixed(digits).replace('.', ',');
const percent = (ratio, digits = 2) => `${decimal(ratio * 100, digits)}%`;

function exercise(lesson, name, index, prompt, options, explanation) {
  const family = `b${lesson}-${name}`;
  return { id: `${family}-${index + 1}`, family, lesson, applied: true, prompt, options: options.map(String), answer: 0, explanation };
}

const matchRates = [
  [4, 800, 9, 300, 'FMR'], [9, 600, 5, 250, 'FNMR'], [3, 300, 12, 240, 'FMR'],
  [7, 700, 9, 150, 'FNMR'], [5, 1250, 12, 300, 'FMR'], [8, 800, 15, 500, 'FNMR'],
].map(([fm, impostors, fnm, genuines, metric], index) => {
  const fmr = fm / impostors;
  const fnmr = fnm / genuines;
  const value = metric === 'FMR' ? fmr : fnmr;
  return exercise(2, 'match-rates', index,
    `Dopo l’acquisizione, si contano ${fm} falsi match su ${impostors} confronti impostore e ${fnm} falsi non-match su ${genuines} confronti genuini. A quanto ammonta il ${metric}, arrotondato a due decimali percentuali?`,
    [value, metric === 'FMR' ? fnmr : fmr, (fm + fnm) / (impostors + genuines), 1 - value].map(x => percent(x)),
    `Si usa soltanto il gruppo pertinente. ${metric === 'FMR' ? `FMR = ${fm}/${impostors}` : `FNMR = ${fnm}/${genuines}`} = ${percent(value)}.`);
});

const hamming = [[128, 80, 12], [200, 100, 16], [256, 150, 18], [192, 120, 9], [256, 160, 28], [220, 125, 20]]
  .map(([total, valid, different], index) => exercise(4, 'masked-hamming', index,
    `Due IrisCode di ${total} bit hanno ${valid} posizioni valide in comune. In ${different} di queste posizioni i bit differiscono. Qual è la distanza di Hamming normalizzata, con tre decimali?`,
    [different / valid, different / total, (valid - different) / valid, valid / total].map(x => decimal(x, 3)),
    `Si dividono le ${different} differenze per le ${valid} posizioni osservabili nei due codici, non per la lunghezza nominale. Risultato ${decimal(different / valid, 3)}.`));

const split = [[14, 5], [18, 6], [21, 4], [12, 7], [30, 3], [16, 8]]
  .map(([people, samples], index) => exercise(5, 'lopo-size', index,
    `Un dataset contiene ${people} soggetti e ${samples} acquisizioni per soggetto. In una singola iterazione leave-one-person-out, quante acquisizioni rimangono nel training?`,
    [(people - 1) * samples, people * samples - 1, people * samples, samples],
    `Vengono escluse tutte le ${samples} acquisizioni di una persona. Il training usa (${people} − 1) × ${samples} = ${(people - 1) * samples} campioni.`));

const dense = [[3, 6, 2], [5, 8, 4], [7, 4, 3], [9, 5, 2], [12, 6, 3], [4, 10, 2]]
  .map(([inputs, units, outputs], index) => {
    const weights = inputs * units + units * outputs;
    return exercise(6, 'dense-parameters', index,
      `Una rete densa ha ${inputs} ingressi, ${units} neuroni nel livello nascosto e ${outputs} uscite. Ogni neurone dei due livelli ha un bias. Quanti parametri sono apprendibili?`,
      [weights + units + outputs, weights, weights + units, weights + outputs],
      `Ogni neurone ha un peso per ciascun ingresso e il proprio bias. Si sommano (${inputs} + 1) × ${units} e (${units} + 1) × ${outputs}.`);
  });

const convolution = [[32, 5, 1, 0], [48, 3, 2, 1], [64, 5, 2, 2], [50, 7, 3, 0], [36, 3, 2, 0], [96, 5, 1, 1]]
  .map(([width, kernel, stride, pad], index) => {
    const result = Math.floor((width + 2 * pad - kernel) / stride) + 1;
    return exercise(7, 'conv-width', index,
      `L’ingresso ha larghezza ${width} pixel. Una convoluzione usa kernel largo ${kernel}, passo ${stride}, dilatazione 1 e padding ${pad} per lato. Qual è la larghezza in uscita?`,
      [result, result - 1, result + 1, width],
      `La larghezza è la parte intera di (${width} + 2 × ${pad} − ${kernel}) / ${stride}, più uno. Si ottiene ${result}.`);
  });

const triplets = [[2, 1, 2], [5, 9, 2], [4, 2, 1], [3, 6, 5], [2, 7, 3], [7, 5, 4]]
  .map(([positive, negative, margin], index) => {
    const value = Math.max(0, positive - negative + margin);
    const distractors = [...new Set([Math.max(0, negative - positive + margin), Math.abs(positive - negative),
      positive + negative, margin, value + 1, value + 2])].filter(option => option !== value).slice(0, 3);
    return exercise(8, 'triplet-loss', index,
      `Le distanze già calcolate da un anchor al positivo e al negativo sono ${positive} e ${negative}. Il margine è ${margin}. Quanto vale max(0, d₊ − d₋ + margine) per questa tripletta?`,
      [value, ...distractors],
      `Il termine non tagliato è ${positive} − ${negative} + ${margin} = ${positive - negative + margin}. Il massimo con zero restituisce ${value}.`);
  });

const ranks = [[100, 68, 14, 5], [200, 132, 28, 15], [80, 46, 12, 9], [150, 93, 21, 10], [250, 160, 35, 20], [120, 82, 11, 9]]
  .map(([total, first, second, third], index) => exercise(9, 'cmc-rank', index,
    `Su ${total} probe in una ricerca a insieme chiuso, il riferimento vero compare esattamente al rango 1 per ${first} probe, al rango 2 per ${second} e al rango 3 per ${third}. Qual è la CMC a rango 3?`,
    [(first + second + third) / total, first / total, third / total, (second + third) / total].map(v => percent(v)),
    `La CMC è cumulativa nei ranghi. Si sommano ${first} + ${second} + ${third} risultando in ${first + second + third}/${total}.`));

const rule3 = [150, 300, 400, 750, 1200, 3000].map((n, index) => exercise(10, 'rule-three', index,
  `Un collaudo conta zero errori su ${n} prove indipendenti. Qual è il limite superiore approssimato al 95% secondo la regola dei tre?`,
  [3 / n, 0, 1 / n, 30 / n].map(x => percent(x, 3)),
  `L’approssimazione è 3/n. Con n = ${n} il limite è ${percent(3 / n, 3)}, anche se la frequenza osservata è zero.`));

const openSet = [[40, 0.005], [15, 0.015], [80, 0.004], [50, 0.008], [90, 0.003], [150, 0.002]]
  .map(([n, p], index) => {
    const estimate = 1 - (1 - p) ** n;
    return exercise(10, 'open-set-error', index,
      `Una probe non appartiene a una gallery di ${n} riferimenti. Se i confronti sono indipendenti e ognuno ha FMR ${percent(p)}, qual è la probabilità di almeno un falso match a due decimali percentuali?`,
      [estimate, p, n * p, 1 - estimate].map(x => percent(x)),
      `La probabilità di nessun falso match è (1 − p)^N. Prendendo il complemento si ottiene ${percent(estimate)}.`);
  });

const aperture = [[24, 4], [50, 2], [48, 8], [80, 4], [36, 3], [64, 4]]
  .map(([focal, number], index) => exercise(11, 'aperture-diameter', index,
    `Una lente ha focale ${focal} mm e numero f/${number}. Qual è il diametro nominale della pupilla di ingresso?`,
    [focal / number, number, focal, focal * number].map(x => `${x} mm`),
    `Il numero f è il rapporto focale/diametro. Si trova il diametro dividendo ${focal} per ${number}, cioè ${focal / number} mm.`));

const frames = [[640, 480, 8], [320, 240, 16], [800, 600, 8], [1280, 720, 16], [160, 120, 16], [1920, 1080, 8]]
  .map(([width, height, bits], index) => {
    const bytes = width * height * bits / 8;
    return exercise(12, 'frame-bytes', index,
      `Una ripresa in scala di grigi misura ${width} × ${height} pixel con ${bits} bit per pixel. Quanto occupa un fotogramma non compresso, ignorando metadati e padding?`,
      [bytes, bytes * 8, bytes * 3, bytes / 2].map(x => `${x} byte`),
      `Si moltiplicano i pixel per i bit per pixel e si divide per otto. Il risultato è ${bytes} byte.`);
  });

const fusion = [[0.2, 0.8, 0.25], [0.7, 0.3, 0.75], [0.5, 0.9, 0.25], [0.9, 0.4, 0.6], [0.6, 0.2, 0.7], [0.4, 0.8, 0.8]]
  .map(([a, b, weight], index) => {
    const value = a * weight + b * (1 - weight);
    return exercise(15, 'fusion-score', index,
      `Due matcher producono similarità normalizzate ${decimal(a)} e ${decimal(b)}. I rispettivi pesi sono ${decimal(weight)} e ${decimal(1 - weight)}. Qual è lo score di fusione per somma pesata?`,
      [value, (a + b) / 2, a, b].map(x => decimal(x)),
      `Si moltiplica ciascuna similarità per il suo peso e si somma. Si ottiene ${decimal(value)}.`);
  });

const retrieval = [[1, 3], [2, 3], [1, 5], [3, 4], [2, 5], [3, 5]]
  .map(([a, b], index) => {
    const ap = (1 / a + 2 / b) / 2;
    return exercise(18, 'average-precision', index,
      `Per una query Re-ID ci sono esattamente due risultati rilevanti. Compaiono ai ranghi ${a} e ${b}. Quanto vale l’Average Precision come media delle precisioni ai ranghi rilevanti, con tre decimali?`,
      [ap, 1 / a, 2 / b, ap / 2].map(x => decimal(x, 3)),
      `Al primo rilevante la precisione è 1/${a}, al secondo è 2/${b}. La loro media vale ${decimal(ap, 3)}.`);
  });

const pad = [
  [6, 240, 12, 200, 'APCER'], [11, 250, 8, 400, 'BPCER'], [3, 120, 10, 200, 'APCER'],
  [9, 180, 9, 300, 'BPCER'], [7, 350, 9, 150, 'APCER'], [5, 250, 16, 400, 'BPCER'],
].map(([accepted, attacks, blocked, bonaFide, metric], index) => {
  const apcer = accepted / attacks;
  const bpcer = blocked / bonaFide;
  const value = metric === 'APCER' ? apcer : bpcer;
  return exercise(19, 'pad-rates', index,
    `Il PAD classifica come bona fide ${accepted} presentazioni su ${attacks} di una categoria d’attacco e blocca ${blocked} presentazioni bona fide su ${bonaFide}. Qual è ${metric}?`,
    [value, metric === 'APCER' ? bpcer : apcer, (accepted + blocked) / (attacks + bonaFide), 1 - value].map(x => percent(x)),
    `${metric} = ${metric === 'APCER' ? `${accepted}/${attacks}` : `${blocked}/${bonaFide}`} = ${percent(value)}. Si contano gli errori rispetto alle sole presentazioni del tipo pertinente.`);
});

const camera = [[20, 2000], [48, 2400], [15, 3000], [80, 4000], [30, 1500], [24, 4800]]
  .map(([width, pixels], index) => {
    const value = 100 * width / pixels;
    return exercise(21, 'ground-sampling-distance', index,
      `Un’immagine nadirale ideale copre ${width} metri in orizzontale con ${pixels} pixel. Qual è il GSD orizzontale in centimetri per pixel?`,
      [value, value / 100, value * 2, value * 100].map(x => `${decimal(x, 3)} cm/pixel`),
      `Si divide la larghezza al suolo in metri per il numero di pixel e si moltiplica per cento per ottenere ${decimal(value, 3)} cm/pixel.`);
  });

export const exercises = [
  ...matchRates, ...hamming, ...split, ...dense, ...convolution, ...triplets, ...ranks, ...rule3,
  ...openSet, ...aperture, ...frames, ...fusion, ...retrieval, ...pad, ...camera,
];
