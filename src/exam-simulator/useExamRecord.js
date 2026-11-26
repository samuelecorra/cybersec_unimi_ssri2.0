import { useCallback, useMemo, useSyncExternalStore } from 'react';
import { normalizeExamRecord } from './exam.js';

const changeEvent = 'exam-simulator:record';
const unsaved = new Map();

function snapshot(storageKey) {
  if (unsaved.has(storageKey)) return unsaved.get(storageKey);
  try { return window.localStorage.getItem(storageKey); }
  catch { return null; }
}

function parse(raw) {
  try { return normalizeExamRecord(JSON.parse(raw)); }
  catch { return normalizeExamRecord(null); }
}

function subscribe(storageKey, listener) {
  const onStorage = event => {
    if (event.key === storageKey || event.key === null) listener();
  };
  window.addEventListener('storage', onStorage);
  window.addEventListener(changeEvent, listener);
  return () => {
    window.removeEventListener('storage', onStorage);
    window.removeEventListener(changeEvent, listener);
  };
}

function updateRecord(storageKey, change) {
  const write = () => {
    const previous = parse(snapshot(storageKey));
    const next = change(previous);
    if (next === previous) return;
    const raw = JSON.stringify(next);
    try {
      window.localStorage.setItem(storageKey, raw);
      unsaved.delete(storageKey);
    } catch {
      unsaved.set(storageKey, raw);
    }
    window.dispatchEvent(new Event(changeEvent));
  };
  return navigator.locks ? navigator.locks.request(storageKey, write) : write();
}

export function useExamRecord(storageKey) {
  const listen = useCallback(listener => subscribe(storageKey, listener), [storageKey]);
  const read = useCallback(() => snapshot(storageKey), [storageKey]);
  const update = useCallback(change => updateRecord(storageKey, change), [storageKey]);
  const raw = useSyncExternalStore(listen, read);
  return [useMemo(() => parse(raw), [raw]), update];
}
