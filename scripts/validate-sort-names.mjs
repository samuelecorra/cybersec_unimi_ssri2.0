/**
 * Verifica dell'ordinamento cronologico dei nomi (src/utils/sortNames.js).
 *
 * Uso: node scripts/validate-sort-names.mjs
 */
import { compareNames, normalizeDatesInName, sortKey } from '../src/utils/sortNames.js';

let failed = 0;
const eq = (actual, expected, label) => {
  const a = JSON.stringify(actual), e = JSON.stringify(expected);
  if (a !== e) { failed++; console.log('FAIL', label, '\n  atteso  ', e, '\n  ottenuto', a); }
  else console.log('ok  ', label);
};
const sorted = (arr) => [...arr].sort(compareNames);

eq(sorted(['esame_10_gennaio_2025','esame_7_febbraio_2025','esame_9_giugno_2025','esame_28_luglio_2025','esame_22_novembre_2025','esame_9_gennaio_2026','esame_21_gennaio_2026','esame_20_marzo_2026','esame_17_gennaio_2025']),
  ['esame_10_gennaio_2025','esame_17_gennaio_2025','esame_7_febbraio_2025','esame_9_giugno_2025','esame_28_luglio_2025','esame_22_novembre_2025','esame_9_gennaio_2026','esame_21_gennaio_2026','esame_20_marzo_2026'], 'cartelle appello Algoritmi');

eq(sorted(['Soluzione_Appello_12_09_2025.md','Soluzione_Appello_02_07_2025.md','Soluzione_Appello_21_02_2025.md','Soluzione_Appello_13_06_2025.md']),
  ['Soluzione_Appello_21_02_2025.md','Soluzione_Appello_13_06_2025.md','Soluzione_Appello_02_07_2025.md','Soluzione_Appello_12_09_2025.md'], 'soluzioni GG_MM_AAAA');

eq(sorted(['traccia_10luglio2026.pdf','traccia_16giugno2026.pdf','traccia_3luglio2026.pdf']),
  ['traccia_16giugno2026.pdf','traccia_3luglio2026.pdf','traccia_10luglio2026.pdf'], 'tracce senza separatori');

eq(sorted(['Appello-250912.pdf','Appello-250221.pdf','Appello-250613new.pdf','Appello-250702.pdf']),
  ['Appello-250221.pdf','Appello-250613new.pdf','Appello-250702.pdf','Appello-250912.pdf'], 'AAMMGG compatto');

eq(sorted(['BD-2025_09_18.pdf','BD-2025_01_14.pdf','BD-2026_01_07.pdf']),
  ['BD-2025_01_14.pdf','BD-2025_09_18.pdf','BD-2026_01_07.pdf'], 'AAAA_MM_GG');

eq(sorted(['1_esame_23_luglio_2025_soluzione.md','2_esame_5_luglio_2025_soluzione.md','8_esame_16_gennaio_2026_soluzione.md','4_esame_15_gennaio_2025_soluzione.md']),
  ['4_esame_15_gennaio_2025_soluzione.md','2_esame_5_luglio_2025_soluzione.md','1_esame_23_luglio_2025_soluzione.md','8_esame_16_gennaio_2026_soluzione.md'], 'prefisso progressivo nascosto');

eq(sorted(['1_5sett2025.md','2_21febb2025.md']), ['2_21febb2025.md','1_5sett2025.md'], 'abbreviazioni di mese');

// i prefissi visibili restano la chiave primaria
eq(sorted(['L10 - Appello 1 gennaio 2020.md','L2 - Appello 10 febbraio 2025.md','L1 - Appello 20 gennaio 2025.md']),
  ['L1 - Appello 20 gennaio 2025.md','L2 - Appello 10 febbraio 2025.md','L10 - Appello 1 gennaio 2020.md'], 'prefisso L visibile');
eq(sorted(['02 - [16-02-2023] Vigenere.md','01 - [24-01-2023] Cifrari.md','21 - [21-03-2026] Ottale.md']),
  ['01 - [24-01-2023] Cifrari.md','02 - [16-02-2023] Vigenere.md','21 - [21-03-2026] Ottale.md'], 'prefisso NN visibile');

// nomi senza date: ordinamento invariato
eq(sorted(['M10_Derivabilita','M2_Gruppi','M1_Insiemi']), ['M1_Insiemi','M2_Gruppi','M10_Derivabilita'], 'nessuna data, collazione numerica');
eq(sorted(['4_Basi Di Dati','1_Algoritmi','10_Altro','2_Reti']), ['1_Algoritmi','2_Reti','4_Basi Di Dati','10_Altro'], 'materie con prefisso numerico');

// falsi positivi
eq(normalizeDatesInName('photo_2025-10-27_22-12-59.jpg'), 'photo_20251027_22-12-59.jpg', 'orario non e una data');
eq(normalizeDatesInName('photo_2026-03-19_16-05-19.jpg'), 'photo_20260319_16-05-19.jpg', 'orario con anno 19 scartato');
eq(normalizeDatesInName('prod-965551-workstation-1920x1440.avif'), 'prod-965551-workstation-1920x1440.avif', 'numeri non-data intatti');
eq(normalizeDatesInName('L2 - Merge sort.md'), 'L2 - Merge sort.md', 'nome senza cifre di data');
eq(sortKey('9_esame_9_giugno_2025.md'), 'esame_20250609.md', 'chiave con prefisso nascosto');
eq(sortKey('9_altro.md'), '9_altro.md', 'prefisso mantenuto senza data');

// ordine totale: stesso risultato indipendentemente dalla permutazione iniziale
const pool = ['esame_10_gennaio_2025','Approfondimenti_per_Esame','esame_7_febbraio_2025','prove_in_itenere_unite','esame_9_gennaio_2026','zz_altro','1_x_2026','5_x_2025','3_y'];
const ref = sorted(pool);
let stable = true;
for (let i = 0; i < 200; i++) {
  const shuffled = [...pool].sort(() => Math.random() - 0.5);
  if (JSON.stringify(sorted(shuffled)) !== JSON.stringify(ref)) stable = false;
}
eq(stable, true, 'ordine indipendente dalla permutazione iniziale');

console.log(failed ? `\n${failed} test falliti` : '\nTutti i test superati');
process.exit(failed ? 1 : 0);
