export function flattenFiles(tree, list = []) {
  for (const node of tree) {
    if (node.type === 'file') {
      list.push(node.path);
    } else if (node.children) {
      flattenFiles(node.children, list);
    }
  }
  return list;
}

export function getParentPath(filePath) {
  const parts = filePath.split('/');
  parts.pop();
  return parts.join('/');
}

export function getFileName(filePath) {
  return filePath.split('/').pop();
}

export function formatSize(bytes) {
  if (bytes < 1024) return `${bytes} B`;
  if (bytes < 1024 * 1024) return `${(bytes / 1024).toFixed(1)} KB`;
  return `${(bytes / (1024 * 1024)).toFixed(1)} MB`;
}
