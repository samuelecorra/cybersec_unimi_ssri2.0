const SOURCE_FILE_TYPES = Object.freeze({
  '.c': Object.freeze({ language: 'c', label: 'C', category: 'source' }),
  '.h': Object.freeze({ language: 'c', label: 'Header C', category: 'source' }),
  '.java': Object.freeze({ language: 'java', label: 'Java', category: 'source' }),
  '.txt': Object.freeze({ language: null, label: 'Testo', category: 'support' }),
});

const MEDIA_FILE_TYPES = Object.freeze({
  '.png': Object.freeze({ label: 'Immagine', category: 'image' }),
  '.jpg': Object.freeze({ label: 'Immagine', category: 'image' }),
  '.jpeg': Object.freeze({ label: 'Immagine', category: 'image' }),
  '.gif': Object.freeze({ label: 'Immagine', category: 'image' }),
  '.svg': Object.freeze({ label: 'Immagine', category: 'image' }),
  '.webp': Object.freeze({ label: 'Immagine', category: 'image' }),
  '.wav': Object.freeze({ label: 'Audio', category: 'audio' }),
});

export const SOURCE_EXTENSIONS = Object.freeze(Object.keys(SOURCE_FILE_TYPES));

export function getFileExtension(filePath = '') {
  const fileName = filePath.split('/').pop()?.split('\\').pop()?.toLowerCase() ?? '';
  const dotIndex = fileName.lastIndexOf('.');
  return dotIndex >= 0 ? fileName.slice(dotIndex) : '';
}

export function getSourceFileType(filePath) {
  return SOURCE_FILE_TYPES[getFileExtension(filePath)] ?? null;
}

export function isSourceFile(filePath) {
  return getSourceFileType(filePath) !== null;
}

export function getMediaFileType(filePath) {
  return MEDIA_FILE_TYPES[getFileExtension(filePath)] ?? null;
}

export function getDisplayFileType(filePath) {
  return getSourceFileType(filePath) ?? getMediaFileType(filePath);
}

export function getFileKind(filePath) {
  const extension = getFileExtension(filePath);
  if (extension === '.md') return 'markdown';
  if (extension === '.html') return 'web-lesson';
  if (extension === '.pdf') return 'pdf';
  if (isSourceFile(filePath)) return 'source';
  const mediaType = getMediaFileType(filePath);
  if (mediaType) return mediaType.category;
  return 'unsupported';
}

export function isTextViewerFile(filePath) {
  const kind = getFileKind(filePath);
  return kind === 'markdown' || kind === 'source';
}

export function encodePathSegments(filePath) {
  return filePath
    .split('/')
    .map(segment => encodeURIComponent(segment)
      .replace(/%26/gi, '&')
      .replace(/%2B/gi, '+'))
    .join('/');
}
