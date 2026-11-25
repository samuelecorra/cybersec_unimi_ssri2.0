import manifest from '../data/r2-assets-public.json';
import { encodePathSegments } from './fileTypes.js';

const assets = manifest.assets ?? {};
const configuredBaseUrl = import.meta.env.VITE_R2_PUBLIC_BASE_URL || '';
const baseUrl = configuredBaseUrl.replace(/\/+$/, '');

export function getR2Asset(filePath) {
  const normalized = filePath.replaceAll('\\', '/').replace(/^\/+/, '');
  const repoPath = normalized.startsWith('lessons/') ? normalized : `lessons/${normalized}`;
  const asset = assets[repoPath] ?? assets[normalized];
  if (!asset || !baseUrl) return null;
  return `${baseUrl}/${encodePathSegments(asset.key)}`;
}

export function getLessonAssetUrl(filePath) {
  return getR2Asset(filePath)
    ?? `${import.meta.env.BASE_URL}lessons/${encodePathSegments(filePath)}`;
}
