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
  return text.replace(/\u200b/g, "").replace(/\s+/g, "");
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

function getVariableTarget(node) {
  const scriptedParent = getScriptedParent(node);
  if (!scriptedParent) {
    return {
      target: node,
      key: cleanTokenText(node.textContent || ""),
    };
  }

  const base = cleanTokenText(node.textContent || "");
  const subscript = getSubscriptIdentity(scriptedParent);

  if (!subscript) {
    return {
      target: node,
      key: base,
    };
  }

  return {
    target: scriptedParent,
    key: `${base}_${subscript}`,
  };
}

export function colorizeMathVariables(root) {
  if (!root) return () => {};

  root.querySelectorAll(".math-var-token").forEach((node) => {
    node.classList.remove("math-var-token");
    node.removeAttribute("data-math-token");
    node.style.removeProperty("--math-var-color");
    node.style.removeProperty("--math-var-glow");
  });

  const tokenIndexes = new Map();
  const coloredTargets = new Set();
  const nodes = Array.from(root.querySelectorAll(VARIABLE_SELECTOR));

  for (const node of nodes) {
    if (!isVariableGlyph(node)) continue;

    const { target, key } = getVariableTarget(node);
    if (!key || coloredTargets.has(target)) continue;

    if (!tokenIndexes.has(key)) {
      tokenIndexes.set(key, tokenIndexes.size);
    }

    const paletteEntry = MATH_VARIABLE_PALETTE[
      tokenIndexes.get(key) % MATH_VARIABLE_PALETTE.length
    ];

    target.classList.add("math-var-token");
    target.dataset.mathToken = key;
    target.style.setProperty("--math-var-color", paletteEntry.color);
    target.style.setProperty("--math-var-glow", paletteEntry.glow);
    coloredTargets.add(target);
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
