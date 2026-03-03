const treeEl = document.getElementById("tree");
const contentEl = document.getElementById("content");
const metaEl = document.getElementById("viewer-meta");
const searchEl = document.getElementById("search");

const state = {
  manifest: null,
  fileIndex: [],
};

function bytes(n) {
  if (n < 1024) return `${n} B`;
  if (n < 1024 * 1024) return `${(n / 1024).toFixed(1)} KB`;
  return `${(n / (1024 * 1024)).toFixed(1)} MB`;
}

function buildTree(paths) {
  const root = { type: "dir", name: "cybersecurity", children: new Map() };
  for (const item of paths) {
    const parts = item.path.split("/");
    let node = root;
    for (let i = 0; i < parts.length; i += 1) {
      const part = parts[i];
      const isFile = i === parts.length - 1;
      if (!node.children.has(part)) {
        node.children.set(part, {
          type: isFile ? "file" : "dir",
          name: part,
          path: isFile ? item.path : null,
          size: isFile ? item.size : null,
          ext: isFile ? item.ext : null,
          children: isFile ? null : new Map(),
        });
      }
      node = node.children.get(part);
    }
  }
  return root;
}

function sortNodes(nodes) {
  return [...nodes].sort((a, b) => {
    if (a.type !== b.type) return a.type === "dir" ? -1 : 1;
    return a.name.localeCompare(b.name, "it");
  });
}

function renderNode(node, mount) {
  if (node.type === "file") {
    const wrap = document.createElement("div");
    wrap.className = "tree-file";
    const btn = document.createElement("button");
    btn.textContent = node.name;
    btn.addEventListener("click", () => openFile(node));
    wrap.appendChild(btn);
    mount.appendChild(wrap);
    return;
  }

  const wrap = document.createElement("div");
  wrap.className = "tree-folder";
  const btn = document.createElement("button");
  btn.textContent = `▾ ${node.name}`;
  wrap.appendChild(btn);

  const childrenWrap = document.createElement("div");
  childrenWrap.className = "tree-children";

  const nodes = sortNodes(node.children.values());
  for (const child of nodes) renderNode(child, childrenWrap);

  let open = true;
  btn.addEventListener("click", () => {
    open = !open;
    childrenWrap.style.display = open ? "block" : "none";
    btn.textContent = `${open ? "▾" : "▸"} ${node.name}`;
  });

  wrap.appendChild(childrenWrap);
  mount.appendChild(wrap);
}

function renderTree(files) {
  treeEl.innerHTML = "";
  const tree = buildTree(files);
  for (const node of sortNodes(tree.children.values())) {
    renderNode(node, treeEl);
  }
}

async function openFile(file) {
  const root = state.manifest.root;
  const url = `${root}/${encodeURI(file.path)}`;
  const res = await fetch(url);
  if (!res.ok) {
    contentEl.innerHTML = `<p>Errore apertura file: ${res.status}</p>`;
    return;
  }

  metaEl.textContent = `${file.path} - ${bytes(file.size)}`;

  if (file.ext === ".md") {
    const text = await res.text();
    if (window.marked) {
      contentEl.innerHTML = marked.parse(text);
    } else {
      contentEl.innerHTML = `<pre>${escapeHtml(text)}</pre>`;
    }
    return;
  }

  if ([".png", ".jpg", ".jpeg", ".gif", ".webp", ".svg"].includes(file.ext)) {
    contentEl.innerHTML = `<img alt="${file.path}" src="${url}" style="max-width: 100%; border-radius: 8px;" />`;
    return;
  }

  contentEl.innerHTML = `<p>File non markdown. <a href="${url}" target="_blank" rel="noreferrer">Apri in nuova scheda</a></p>`;
}

function escapeHtml(value) {
  return value
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#39;");
}

function applySearch() {
  const q = searchEl.value.trim().toLowerCase();
  if (!q) {
    renderTree(state.fileIndex);
    return;
  }
  const filtered = state.fileIndex.filter((f) => f.path.toLowerCase().includes(q));
  renderTree(filtered);
}

async function init() {
  const res = await fetch("manifest.json");
  if (!res.ok) {
    contentEl.innerHTML = `<p>Manifest non trovato. Esegui <code>node scripts/build-manifest.mjs</code></p>`;
    return;
  }
  state.manifest = await res.json();
  state.fileIndex = state.manifest.files;
  renderTree(state.fileIndex);
  metaEl.textContent = `File indicizzati: ${state.manifest.totalFiles}`;
}

searchEl.addEventListener("input", applySearch);
init();
