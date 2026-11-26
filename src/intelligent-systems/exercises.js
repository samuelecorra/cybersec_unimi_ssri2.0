function exercise(lesson, family, variant, prompt, options, explanation) {
  return {
    id: `l${lesson}-${family}-${variant + 1}`,
    family: `l${lesson}-${family}`,
    lesson,
    applied: true,
    prompt,
    options: options.map(String),
    answer: 0,
    explanation,
  };
}

const interpolation = [
  [4, 16, 5, 9, 11], [0, 14, 4, 10, 25], [2, 18, 8, 16, 32],
  [10, 6, 13, 19, 38], [1, 30, 5, 13, 54], [4, 8, 6, 12, 36],
].map(([t0, v0, t, t1, v1], index) => {
  const value = v0 + (v1 - v0) * (t - t0) / (t1 - t0);
  return exercise(6, 'interpolation', index,
    `A sensor reads ${v0} at t = ${t0} and ${v1} at t = ${t1}. What value would linear interpolation assign at t = ${t}?`,
    [value, (v0 + v1) / 2, v0, v1],
    `Interpolation uses (${t} − ${t0})/(${t1} − ${t0}) of the change from ${v0} to ${v1}, giving ${value}.`);
});

const dense = [[3, 5, 2], [6, 4, 3], [8, 7, 2], [2, 9, 4], [5, 6, 1], [10, 3, 6]]
  .map(([inputs, hidden, outputs], index) => {
    const weights = inputs * hidden + hidden * outputs;
    const total = weights + hidden + outputs;
    return exercise(7, 'dense-count', index,
      `A dense network has ${inputs} inputs, one hidden layer with ${hidden} units and an output layer with ${outputs} units. Each non-input unit has a bias. How many parameters are trainable?`,
      [total, weights, weights + hidden, weights + outputs],
      `The hidden layer has (${inputs} + 1) × ${hidden} parameters and the output has (${hidden} + 1) × ${outputs}, for ${total} in total.`);
  });

const similarity = [[2, 1], [3, 4], [4, 3], [2, 5], [5, 3], [6, 1]]
  .map(([x, y], index) => exercise(9, 'cosine-calc', index,
    `An image-query embedding is (${x}, ${y}) and a catalogue embedding is (${x}, 0). What is their cosine similarity, rounded to three decimals?`,
    [x / Math.hypot(x, y), y / Math.hypot(x, y), 1, 0].map(value => value.toFixed(3)),
    `The dot product is ${x * x} and the product of norms is ${x}√${x * x + y * y}. The ratio is ${(x / Math.hypot(x, y)).toFixed(3)}.`));

const scaling = [[0, 20, 12], [-10, 30, 0], [10, 50, 40], [15, 35, 19], [-6, 14, 8], [5, 45, 19]]
  .map(([low, high, value], index) => {
    const fraction = (value - low) / (high - low);
    return exercise(10, 'scale-calc', index,
      `A min–max scaler has fitted bounds ${low} and ${high}. What is the scaled value of ${value} in the interval [0, 1]?`,
      [fraction.toFixed(2), (1 - fraction).toFixed(2), '0.00', '1.00'],
      `Subtract the fitted minimum and divide by the fitted range. (${value} − (${low})) / (${high} − (${low})) = ${fraction.toFixed(2)}.`);
  });

const impurity = [[1, 9], [2, 8], [3, 7], [4, 6], [5, 15], [7, 13]]
  .map(([positives, negatives], index) => {
    const p = positives / (positives + negatives);
    const gini = 1 - p ** 2 - (1 - p) ** 2;
    return exercise(12, 'gini-calc', index,
      `A node contains ${positives} positive and ${negatives} negative training examples. What is its Gini impurity to three decimals?`,
      [gini, p, 1 - p, 0.5].map(value => value.toFixed(3)),
      `The two class proportions are ${p.toFixed(3)} and ${(1 - p).toFixed(3)}. Gini is one minus the sum of their squares, or ${gini.toFixed(3)}.`);
  });

const folds = [[150, 5], [1200, 6], [960, 8], [250, 10], [640, 4], [700, 7]]
  .map(([samples, k], index) => {
    const heldOut = samples / k;
    return exercise(14, 'fold-size', index,
      `A team runs ordinary ${k}-fold cross-validation on ${samples} i.i.d. records, with equally sized folds. How many records train the model in each iteration?`,
      [samples - heldOut, heldOut, samples, samples - k],
      `One fold of ${heldOut} records is held out and the remaining ${k - 1} folds contribute ${samples - heldOut} training records.`);
  });

const confusion = [
  [48, 12, 32, 108, 'precision'], [30, 45, 10, 115, 'recall'], [45, 15, 30, 110, 'accuracy'],
  [24, 16, 6, 54, 'precision'], [72, 18, 48, 62, 'recall'], [30, 20, 10, 140, 'accuracy'],
].map(([tp, fp, fn, tn, metric], index) => {
  const ratios = { precision: tp / (tp + fp), recall: tp / (tp + fn), accuracy: (tp + tn) / (tp + fp + fn + tn) };
  const formula = { precision: 'TP / (TP + FP)', recall: 'TP / (TP + FN)', accuracy: '(TP + TN) / (TP + FP + FN + TN)' };
  const metricLabel = metric === 'accuracy' ? 'overall accuracy' : `positive-class ${metric}`;
  const percent = value => `${(value * 100).toFixed(1)}%`;
  return exercise(16, 'confusion', index,
    `On a test set a defect classifier has TP = ${tp}, FP = ${fp}, FN = ${fn} and TN = ${tn}. What is its ${metricLabel}, rounded to one decimal percent?`,
    [ratios[metric], ...Object.entries(ratios).filter(([key]) => key !== metric).map(([, ratio]) => ratio), tn / (tn + fp)].map(percent),
    `Use ${formula[metric]} with the reported counts. That gives ${percent(ratios[metric])}.`);
});

const f1 = [[18, 6, 12], [24, 12, 6], [30, 10, 20], [42, 14, 7], [16, 4, 12], [55, 15, 30]]
  .map(([tp, fp, fn], index) => {
    const score = 2 * tp / (2 * tp + fp + fn);
    return exercise(16, 'f1-calc', index,
      `An alarm yields ${tp} true positives, ${fp} false positives and ${fn} false negatives. What is the positive-class F1, rounded to three decimals?`,
      [score, tp / (tp + fp), tp / (tp + fn), tp / (tp + fp + fn)].map(value => value.toFixed(3)),
      `F1 = 2TP/(2TP + FP + FN), so the result is ${2 * tp}/${2 * tp + fp + fn} ≈ ${score.toFixed(3)}.`);
  });

const regression = [[12, 8, 10], [18, 12, 9], [7, 4, 6], [16, 11, 7], [10, 3, 11], [22, 14, 20]]
  .map(([a, b, c], index) => {
    const error = Math.abs(a - b);
    return exercise(17, 'mae-calc', index,
      `A regression system makes two predictions, ${b} and ${c}, for targets ${a} and ${c} in the same order. What is the mean absolute error?`,
      [error / 2, error, 0, error ** 2 / 2],
      `The absolute errors are ${error} and 0. Their mean is ${error / 2}.`);
  });

const bayes = [[1, 5], [2, 8], [3, 9], [4, 6], [1, 9], [4, 12]]
  .map(([fpCost, fnCost], index) => {
    const threshold = fpCost / (fpCost + fnCost);
    return exercise(19, 'bayes-risk', index,
      `Correct diagnoses cost zero. A false alarm costs ${fpCost} units and a missed fault costs ${fnCost}. Above what posterior fault probability (to three decimals) should a cost-minimizing system raise the alarm?`,
      [threshold, 1 - threshold, 0.5, 1].map(value => value.toFixed(3)),
      `An alarm costs (1 − p) × ${fpCost} in expectation and silence costs p × ${fnCost}. Alarm once p exceeds ${fpCost}/${fpCost + fnCost} ≈ ${threshold.toFixed(3)}.`);
  });

const convolution = [[32, 5, 1, 0], [28, 3, 2, 1], [64, 5, 2, 2], [48, 7, 3, 0], [50, 3, 2, 0], [30, 5, 1, 1]]
  .map(([size, kernel, stride, padding], index) => {
    const output = Math.floor((size + 2 * padding - kernel) / stride) + 1;
    return exercise(21, 'conv-shape', index,
      `A convolution takes a ${size} × ${size} input, uses a ${kernel} × ${kernel} kernel, stride ${stride}, padding ${padding} per side and dilation 1. What is the output width?`,
      [output, output - 1, output + 1, size],
      `Output width = floor((${size} + 2 × ${padding} − ${kernel}) / ${stride}) + 1 = ${output}.`);
  });

const head = [[64, 5], [128, 8], [256, 3], [32, 12], [96, 4], [48, 10]]
  .map(([features, classes], index) => exercise(22, 'frozen-head', index,
    `A frozen backbone returns ${features} features. It is followed only by a new dense head with ${classes} outputs, each with a bias. How many parameters can the optimizer update?`,
    [(features + 1) * classes, features * classes, features + classes, classes],
    `The backbone contributes zero trainable parameters. The head has ${features} × ${classes} weights and ${classes} biases.`));

const overlap = [[40, 35, 15], [70, 45, 25], [36, 64, 20], [30, 30, 12], [100, 90, 60], [72, 60, 30]]
  .map(([a, b, common], index) => {
    const ratio = common / (a + b - common);
    return exercise(23, 'iou-calc', index,
      `A prediction covers ${a} square pixels and its ground-truth box covers ${b}. Their intersection covers ${common}. What is the IoU to three decimals?`,
      [ratio, common / (a + b), common / a, (a + b - common) / (a + b)].map(value => value.toFixed(3)),
      `The union is ${a} + ${b} − ${common} = ${a + b - common}. Divide the intersection by that union to obtain ${ratio.toFixed(3)}.`);
  });

const patches = [[224, 16], [128, 16], [256, 32], [192, 16], [240, 20], [96, 8]]
  .map(([size, patch], index) => {
    const side = size / patch;
    return exercise(24, 'patch-count', index,
      `A Vision Transformer splits a ${size} × ${size} image into non-overlapping ${patch} × ${patch} patches. How many patch tokens are produced before adding any class token?`,
      [side ** 2, side ** 2 + 1, side, 2 * side],
      `Each side has ${size}/${patch} = ${side} patches, giving ${side}² = ${side ** 2} patch tokens.`);
  });

export const exercises = [
  ...interpolation, ...dense, ...similarity, ...scaling, ...impurity, ...folds, ...confusion,
  ...f1, ...regression, ...bayes, ...convolution, ...head, ...overlap, ...patches,
];
