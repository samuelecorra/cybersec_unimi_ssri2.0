# SSRI 2.0

> Archivio pubblico di materiali di studio per **SSRI — Sicurezza dei Sistemi e delle Reti Informatiche** dell'Università degli Studi di Milano, organizzato e mantenuto da uno studente per la comunità del corso.

**🌐 Consultabile online:** <https://samuelecorra.github.io/cybersec_unimi_ssri2.0/>

Nato dall'esigenza di organizzare e consultare più facilmente gli appunti del percorso, il progetto è cresciuto in un archivio navigabile con una web app pubblica. I materiali sono una risorsa di studio indipendente e complementare.

---

## Perché esiste questo progetto

Seguire un corso interamente online significa passare tra lezioni, appunti e argomenti collegati. Ho creato questo archivio per raccogliere i materiali di studio in un formato ricercabile e navigabile, rendere più chiari i collegamenti tra i temi e poter correggere e ampliare le note nel tempo. È un progetto personale costruito a partire da un'esigenza concreta di studio, non una risorsa ufficiale dell'Università.

## Come sono nate queste lezioni

Le lezioni sono organizzate in Markdown e possono includere formule KaTeX, tabelle, diagrammi e collegamenti tra argomenti. La web app aggiunge ricerca e navigazione, così i contenuti possono essere consultati come un archivio coerente invece che come file isolati. I materiali sono appunti di studio e vanno verificati con le fonti del corso; segnalazioni e correzioni sono benvenute.

## Cosa contiene

Tutti e tre gli anni del corso di laurea, materia per materia:

| Anno | Insegnamenti |
|------|-------------|
| **1°** | Analisi 1 · Architettura degli Elaboratori · Matematica Discreta · Programmazione · Diritto Penale Informatico · Programmazione Web e Mobile · Aspetti Organizzativi e Gestionali della Cybersecurity |
| **2°** | Algoritmi e Strutture Dati · Sistemi Operativi 1 e 2 · Basi di Dati · Reti di Calcolatori · Crittografia · Statistica e Analisi dei Dati |
| **3°** | Computer Forensics · Sicurezza dei Sistemi e delle Reti · Aspetti Etici, Legali, Sociali ed Economici dell'Informatica · Gestione della Sicurezza nei Sistemi Informativi · Progettazione di Software Sicuro · Sistemi Biometrici · Sicurezza Web & Mobile |

Ove possibile (in particolare **Crittografia** e **Reti di Calcolatori**) sono presenti **moduli aggiuntivi interamente dedicati agli appelli passati risolti**, con soluzioni complete passo-passo: chi non ha mai sostenuto l'esame sa esattamente cosa aspettarsi.

## Posizionamento e finalità

Questo archivio è indipendente e non sostituisce i materiali o le indicazioni ufficiali del corso. È condiviso come risorsa di studio; chiunque può esaminarne i contenuti e proporre correzioni tramite issue o pull request.

---

## La web app

Viewer React + Vite con UI cyberpunk, navigazione filesystem, ricerca live e rendering Markdown/KaTeX completo.

### Quick start (sviluppo locale)

```bash
git clone https://github.com/samuelecorra/cybersec_unimi_ssri2.0.git
cd cybersec_unimi_ssri2.0
npm install
npm run dev
```

L'app sarà disponibile su **http://127.0.0.1:5180**.

### Funzionalità

- **Filesystem navigabile** — Albero cartelle/file ricorsivo nella sidebar
- **Viewer Markdown** — Rendering completo: titoli, liste, tabelle, code block, immagini, formule KaTeX inline e block
- **Ricerca live** — Per nome file, heading e contenuto (Ctrl+K)
- **Navigazione prev/next** — Scorrimento sequenziale delle lezioni
- **Breadcrumb** — Percorso del file sempre visibile
- **Persistenza** — Ultimo file aperto e stato dell'albero in localStorage
- **Link profondi** — Hash URL condivisibili per anni, materie, cartelle e singole lezioni
- **Tema cyberpunk** — Palette neon (cyan/magenta/lime) su base scura, font mono

Ogni navigazione aggiorna l'hash del browser. Per esempio:

```text
https://samuelecorra.github.io/cybersec_unimi_ssri2.0/#/cybersecurity/anno1/3_Programmazione
```

I segmenti contenenti spazi o caratteri speciali vengono codificati automaticamente. È quindi sufficiente aprire la cartella o la lezione desiderata e copiare l'URL dalla barra del browser.

### Comandi

| Comando | Descrizione |
|---------|-------------|
| `npm run dev` | Dev server su http://127.0.0.1:5180 |
| `npm run build` | Build di produzione in `dist/` |
| `npm run preview` | Preview della build |

### Deploy

Ogni push su `main` attiva la GitHub Action ([.github/workflows/deploy.yml](.github/workflows/deploy.yml)) che builda e pubblica automaticamente su GitHub Pages.

### Stack

- React 18 + Vite 6
- react-markdown + remark-gfm + rehype-highlight
- KaTeX (rendering formule)
- highlight.js (syntax highlighting)
- CSS custom, nessun framework

### Struttura

```
├── lessons/cybersecurity/     # Tutte le lezioni .md (anno1/anno2/anno3)
├── src/                       # Web app React (components, hooks, utils)
├── scripts/                   # Script di build e manutenzione contenuti
├── vite-plugin-lessons.js     # Plugin Vite per lo scan del filesystem
└── .github/workflows/         # Deploy automatico su GitHub Pages
```

---

*Fatto con mesi di pazienza, da uno studente SSRI per gli studenti SSRI. Buono studio — e in bocca al lupo per il 30 e lode.*
