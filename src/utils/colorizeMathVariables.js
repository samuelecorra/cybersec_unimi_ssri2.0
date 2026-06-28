const MATH_VARIABLE_PALETTE = [
  { color: "#00e5ff", glow: "rgba(0, 229, 255, 0.55)" },
  { color: "#ff4dff", glow: "rgba(255, 77, 255, 0.55)" },
  { color: "#7cff00", glow: "rgba(124, 255, 0, 0.5)" },
  { color: "#ff8c00", glow: "rgba(255, 140, 0, 0.55)" },
  { color: "#b388ff", glow: "rgba(179, 136, 255, 0.55)" },
  { color: "#00ff9d", glow: "rgba(0, 255, 157, 0.5)" },
  { color: "#ff5c8a", glow: "rgba(255, 92, 138, 0.55)" },
  { color: "#ffe45e", glow: "rgba(255, 228, 94, 0.45)" },
  { color: "#4dffea", glow: "rgba(77, 255, 234, 0.5)" },
  { color: "#ff6b35", glow: "rgba(255, 107, 53, 0.55)" },
  { color: "#9dff00", glow: "rgba(157, 255, 0, 0.45)" },
  { color: "#6ea8ff", glow: "rgba(110, 168, 255, 0.55)" },
];

const VARIABLE_SELECTOR = [
  ".katex .katex-html .mord.mathnormal",
  ".katex .katex-html .mord.mathit",
  ".katex .katex-html .mord.mainit",
].join(",");

const LETTER_PATTERN = /^\p{L}$/u;

function cleanTokenText(text) {
  return text.replace(/​/g, "").replace(/\s+/g, "");
}

function isVariableGlyph(node) {
  const token = cleanTokenText(node.textContent || "");
  return LETTER_PATTERN.test(token);
}

function getScriptedParent(node) {
  const parent = node.parentElement;
  if (!parent?.classList.contains("mord")) return null;
  if (parent.firstElementChild !== node) return null;
  return parent.querySelector(":scope > .msupsub") ? parent : null;
}

function getSubscriptIdentity(parent) {
  const scripts = parent.querySelector(":scope > .msupsub");
  if (!scripts?.querySelector(".vlist-t2")) return "";
  return cleanTokenText(scripts.textContent || "");
}

export function colorizeMathVariables(root) {
  if (!root) return () => {};

  root.querySelectorAll(".math-var-token").forEach((node) => {
    node.classList.remove("math-var-token");
    node.removeAttribute("data-math-token");
    node.style.removeProperty("--math-var-color");
    node.style.removeProperty("--math-var-glow");
  });

  const nodes = Array.from(root.querySelectorAll(VARIABLE_SELECTOR));

  // For each letter node compute its effective element (scripted wrapper or itself)
  // and map effective element → { node, baseLetter }
  const effectiveElMap = new Map(); // effectiveEl -> { node, baseLetter }

  for (const node of nodes) {
    if (!isVariableGlyph(node)) continue;
    const scriptedParent = getScriptedParent(node);
    const effectiveEl = scriptedParent || node;
    const baseLetter = cleanTokenText(node.textContent || "");
    effectiveElMap.set(effectiveEl, { node, baseLetter });
  }

  // Group effective elements by their parent to find consecutive runs (multi-letter operators)
  const parentToEls = new Map();
  for (const effectiveEl of effectiveElMap.keys()) {
    const parent = effectiveEl.parentElement;
    if (!parent) continue;
    if (!parentToEls.has(parent)) parentToEls.set(parent, new Set());
    parentToEls.get(parent).add(effectiveEl);
  }

  // multiLetterKey: effectiveEl -> string key (the full operator word, e.g. "MAC")
  const multiLetterKey = new Map();

  for (const [parent, elSet] of parentToEls) {
    const children = Array.from(parent.children);
    let i = 0;
    while (i < children.length) {
      const child = children[i];
      if (elSet.has(child)) {
        const run = [child];
        let j = i + 1;
        while (j < children.length && elSet.has(children[j])) {
          run.push(children[j]);
          j++;
        }
        if (run.length >= 2) {
          const word = run
            .map((el) => effectiveElMap.get(el)?.baseLetter || "")
            .join("");
          for (const el of run) multiLetterKey.set(el, word);
        }
        i = j;
      } else {
        i++;
      }
    }
  }

  // Assign colors
  const tokenIndexes = new Map();
  const coloredTargets = new Set();

  for (const [effectiveEl, { node, baseLetter }] of effectiveElMap) {
    if (coloredTargets.has(effectiveEl)) continue;

    let key;
    if (multiLetterKey.has(effectiveEl)) {
      // Multi-letter operator: all letters share the operator name as key
      key = multiLetterKey.get(effectiveEl);
    } else {
      // Single variable: letter + optional subscript identity
      const scriptedParent = getScriptedParent(node);
      if (scriptedParent) {
        const subscript = getSubscriptIdentity(scriptedParent);
        key = subscript ? `${baseLetter}_${subscript}` : baseLetter;
      } else {
        key = baseLetter;
      }
    }

    if (!tokenIndexes.has(key)) tokenIndexes.set(key, tokenIndexes.size);

    const paletteEntry =
      MATH_VARIABLE_PALETTE[tokenIndexes.get(key) % MATH_VARIABLE_PALETTE.length];

    effectiveEl.classList.add("math-var-token");
    effectiveEl.dataset.mathToken = key;
    effectiveEl.style.setProperty("--math-var-color", paletteEntry.color);
    effectiveEl.style.setProperty("--math-var-glow", paletteEntry.glow);
    coloredTargets.add(effectiveEl);
  }

  return () => {
    for (const target of coloredTargets) {
      target.classList.remove("math-var-token");
      target.removeAttribute("data-math-token");
      target.style.removeProperty("--math-var-color");
      target.style.removeProperty("--math-var-glow");
    }
  };
}
