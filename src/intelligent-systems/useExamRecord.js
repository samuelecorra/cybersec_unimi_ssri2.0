import { useMemo, useSyncExternalStore } from 'react';

const storageKey = 'cyberlocker:intelligent-systems:v1';
const changeEvent = 'intelligent-systems:record';
const initialRecord = { active: null, history: [] };
let unsaved = null;

function snapshot() {
  if (unsaved !== null) return unsaved;
  try { return window.localStorage.getItem(storageKey); }
  catch { return null; }
}

function parse(raw) {
  try {
    const result = JSON.parse(raw);
    if (Array.isArray(result?.history)) return result;
  } catch { /* The stored value is invalid. */ }
  return initialRecord;
}

function subscribe(listener) {
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

function updateRecord(change) {
  const write = () => {
    const previous = parse(snapshot());
    const next = change(previous);
    if (next === previous) return;
    const raw = JSON.stringify(next);
    try {
      window.localStorage.setItem(storageKey, raw);
      unsaved = null;
    } catch {
      unsaved = raw;
    }
    window.dispatchEvent(new Event(changeEvent));
  };
  return navigator.locks ? navigator.locks.request(storageKey, write) : write();
}

export function useExamRecord() {
  const raw = useSyncExternalStore(subscribe, snapshot);
  return [useMemo(() => parse(raw), [raw]), updateRecord];
}
