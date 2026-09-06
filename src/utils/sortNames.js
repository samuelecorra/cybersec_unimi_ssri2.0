/**
 * Ordinamento dei nomi di file e cartelle con riconoscimento delle date.
 *
 * Il corpus usa molte convenzioni di denominazione per gli appelli d'esame
 * ("esame_7_febbraio_2025", "Soluzione_Appello_02_07_2025.md",
 * "BD-2025_01_14.pdf", "Appello-250221.pdf", "traccia_16giugno2026.pdf").
 * Un confronto puramente alfabetico mette "esame_10_gennaio_2025" prima di
 * "esame_7_febbraio_2025" e sparpaglia gli appelli in un ordine che non ha
 * nulla a che vedere con la cronologia.
 *
 * La soluzione non e' un comparatore ad hoc (facile da rendere non
 * transitivo, e quindi imprevedibile), ma una *normalizzazione*: ogni data
 * riconosciuta dentro il nome viene riscritta nella stessa posizione come
 * token `AAAAMMGG` a larghezza fissa. Il confronto resta quello di sempre
 * (locale italiana, `numeric: true`), ma le date vengono lette come date.
 * Il resto del nome continua a pesare come prima, quindi i prefissi
 * progressivi ("L1 - ...", "1_...") mantengono la priorita' voluta
 * dall'autore.
 */

// Mesi italiani con le abbreviazioni usate nel corpus ("5sett2025",
// "21febb2025"). L'ordine delle alternative nella regex e' dal piu' lungo al
// piu' corto, altrimenti "mar" divorerebbe il prefisso di "marzo".
const MONTHS = new Map([
  ['gennaio', 1], ['genn', 1], ['gen', 1],
  ['febbraio', 2], ['febb', 2], ['feb', 2],
  ['marzo', 3], ['mar', 3],
  ['aprile', 4], ['apr', 4],
  ['maggio', 5], ['magg', 5], ['mag', 5],
  ['giugno', 6], ['giug', 6], ['giu', 6],
  ['luglio', 7], ['lugl', 7], ['lug', 7],
  ['agosto', 8], ['agos', 8], ['ago', 8],
  ['settembre', 9], ['settemb', 9], ['sett', 9], ['set', 9],
  ['ottobre', 10], ['otto', 10], ['ott', 10],
  ['novembre', 11], ['nov', 11],
  ['dicembre', 12], ['dic', 12],
]);

const MONTH_ALT = [...MONTHS.keys()].join('|');

// Le alternative sono in ordine di specificita' decrescente: la prima che
// combacia in una data posizione vince, e la scansione riprende dopo il
// match (niente sovrapposizioni). `(?<!\d)` / `(?!\d)` evitano di ritagliare
// una data dentro una cifra piu' lunga.
const DATE_RE = new RegExp(
  '(?<!\\d)(?:' +
    // 1. AAAA-MM-GG e AAAAMMGG  ("BD-2025_01_14", "20250919online")
    '(?<y1>\\d{4})[-_./](?<m1>\\d{1,2})[-_./](?<d1>\\d{1,2})' +
    '|(?<y2>\\d{4})(?<m2>\\d{2})(?<d2>\\d{2})' +
    // 2. giorno + mese in lettere + anno  ("7_febbraio_2025", "16giugno2026")
    `|(?<d3>\\d{1,2})[-_./ ]?(?<mn3>${MONTH_ALT})[-_./ ]?(?<y3>\\d{4})` +
    // 3. GG-MM-AAAA  ("Soluzione_Appello_02_07_2025", "[24-06-2024]")
    '|(?<d4>\\d{1,2})[-_./](?<m4>\\d{1,2})[-_./](?<y4>\\d{4})' +
    // 4. GG-MM-AA  ("Esame statistica 05-09-25")
    '|(?<d5>\\d{1,2})[-_./](?<m5>\\d{1,2})[-_./](?<y5>\\d{2})' +
    // 5. AAMMGG compatto  ("Appello-250221")
    '|(?<y6>\\d{2})(?<m6>\\d{2})(?<d6>\\d{2})' +
  ')(?!\\d)',
  'gi',
);

function pad(value, width) {
  return String(value).padStart(width, '0');
}

/** Restituisce il token `AAAAMMGG`, oppure null se i campi non sono una data. */
function isoToken(year, month, day) {
  if (!Number.isInteger(year) || month < 1 || month > 12 || day < 1 || day > 31) {
    return null;
  }
  return `${pad(year, 4)}${pad(month, 2)}${pad(day, 2)}`;
}

/**
 * Espande un anno a due cifre: nel corpus tutte le date sono del 2000+.
 * `max` limita la finestra accettata, perche' le forme a due cifre sono le
 * piu' ambigue: in "photo_2026-03-19_16-05-19.jpg" la coda "16-05-19" ha la
 * forma GG-MM-AA ma e' un orario, non una data. Le forme GG-MM-AA reali del
 * corpus stanno tutte dal 2020 in poi, mentre l'archivio compatto AAMMGG
 * risale al 2005: da qui le due finestre diverse.
 */
function expandYear(twoDigits, min, max) {
  return twoDigits >= min && twoDigits <= max ? 2000 + twoDigits : NaN;
}

/**
 * Riscrive ogni data riconosciuta in `name` come token `AAAAMMGG`.
 * Le sequenze che sembrano date ma non lo sono (mese 22, giorno 59, ...)
 * restano intatte.
 */
export function normalizeDatesInName(name) {
  return name.replace(DATE_RE, (match, ...args) => {
    const g = args[args.length - 1];
    let token = null;

    if (g.y1) token = isoToken(+g.y1, +g.m1, +g.d1);
    else if (g.y2) token = isoToken(+g.y2, +g.m2, +g.d2);
    else if (g.mn3) token = isoToken(+g.y3, MONTHS.get(g.mn3.toLowerCase()), +g.d3);
    else if (g.y4) token = isoToken(+g.y4, +g.m4, +g.d4);
    else if (g.y5) token = isoToken(expandYear(+g.y5, 20, 39), +g.m5, +g.d5);
    else if (g.y6) token = isoToken(expandYear(+g.y6, 0, 39), +g.m6, +g.d6);

    return token ?? match;
  });
}

// Prefisso progressivo che il frontend nasconde in visualizzazione
// (`BrowseView`/`Breadcrumb` tolgono "1_", "2_", ...): se il resto del nome
// contiene una data, quel numero e' solo l'ordine in cui il file e' stato
// aggiunto e non deve prevalere sulla cronologia, altrimenti l'utente vede
// "esame 23 luglio" prima di "esame 5 luglio" senza alcun indizio del
// perche'. I prefissi visibili ("L1 - ...", "01 - [...]") restano intatti.
const HIDDEN_PREFIX_RE = /^\d+_(?=.*\d{8})/;

/** Chiave di ordinamento di un nome di file o cartella. */
export function sortKey(name) {
  return normalizeDatesInName(name).replace(HIDDEN_PREFIX_RE, '');
}

/**
 * Confronto fra nomi di file/cartelle: come il confronto naturale italiano
 * con collazione numerica, ma con le date lette come date. Il nome originale
 * fa da spareggio, cosi' l'ordine resta totale e deterministico.
 */
export function compareNames(a, b) {
  const byDate = sortKey(a).localeCompare(sortKey(b), 'it', { numeric: true });
  return byDate !== 0 ? byDate : a.localeCompare(b, 'it', { numeric: true });
}
