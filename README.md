# Samu CyberLocker

> Archivio **open source e senza fini di lucro** delle lezioni del corso di laurea online **SSRI — Sicurezza dei Sistemi e delle Reti Informatiche** (Università degli Studi di Milano), realizzato da uno studente per tutti gli studenti del corso.

**🌐 Consultabile online:** <https://samuelecorra.github.io/cybersec_unimi_ssri2.0/>

Nato come archivio personale locale, il progetto è oggi una web app pubblica distribuita via GitHub Pages (oltre 120 deploy all'attivo) e in continuo aggiornamento.

---

## Perché esiste questo progetto

Il corso di laurea SSRI viene erogato interamente online attraverso la piattaforma ufficiale <https://ssrionline.unimi.it/>. Pur riconoscendo il valore del percorso e del corpo docente, è doveroso constatare — con il massimo rispetto istituzionale — che i materiali didattici messi a disposizione presentano criticità oggettive e note alla comunità studentesca:

- **Obsolescenza dei contenuti**: buona parte delle slide non riceve aggiornamenti sostanziali da circa il 2015, con riferimenti tecnici ormai superati dallo stato dell'arte.
- **Qualità tipografica e strutturale limitata**: formule non renderizzate correttamente, impaginazione datata, assenza di una navigazione moderna tra gli argomenti.
- **Divario tra videolezione e slide**: una parte significativa delle spiegazioni dei docenti — spesso la più preziosa — esiste solo nel parlato e non è riportata in alcun documento.

Questo repository è il risultato di **mesi di lavoro sistematico** per colmare quel divario: non una critica fine a sé stessa, ma una risposta costruttiva. Una laurea triennale in sicurezza informatica è già di per sé impegnativa; affrontarla con materiali inadeguati la rende inutilmente più ardua.

## Come sono nate queste lezioni

Il flusso di lavoro dietro ogni lezione:

1. **Registrazione integrale** di tutte le videolezioni dei docenti.
2. **Trascrizione fedele al 100%** del parlato tramite Apple Intelligence.
3. **Rielaborazione con Claude Code** (Anthropic): con molta pazienza — e moltissimi token — ogni trascrizione è stata trasformata in una lezione Markdown completa, che integra tutto ciò che il docente diceva a voce ma non era scritto nei lucidi, senza lasciare impliciti i passaggi matematici e logici.
4. **Aggiornamento dei contenuti al 2026**: dove le lezioni originali citavano nozioni superate, il materiale è stato allineato allo stato dell'arte. Un esempio fra i tanti: nelle lezioni di Crittografia si indicava RSA a 512 bit come dimensione di sicurezza di riferimento; qui viene correttamente indicato che oggi il minimo raccomandato è **2048 bit**. Interventi analoghi sono presenti in tutto l'archivio.

Il risultato: lezioni con **formule matematiche renderizzate perfettamente** (inline e block-level, KaTeX), callout didattici, tabelle, diagrammi e — punto fondamentale — il **tono narrativo originale dei professori**, preservato in ogni lezione. Chi studia da qui ritrova la voce del proprio docente, non un riassunto anonimo. Ed è esattamente questo che rende realistico ambire al **30 e lode** in ogni esame.

## Cosa contiene

Tutti e tre gli anni del corso di laurea, materia per materia:

| Anno | Insegnamenti |
|------|-------------|
| **1°** | Analisi 1 · Architettura degli Elaboratori · Matematica Discreta · Programmazione · Diritto Penale Informatico · Programmazione Web e Mobile · Aspetti Organizzativi e Gestionali della Cybersecurity |
| **2°** | Algoritmi e Strutture Dati · Sistemi Operativi 1 e 2 · Basi di Dati · Reti di Calcolatori · Crittografia · Statistica e Analisi dei Dati |
| **3°** | Computer Forensics · Sicurezza dei Sistemi e delle Reti · Aspetti Etici, Legali, Sociali ed Economici dell'Informatica · Gestione della Sicurezza nei Sistemi Informativi · Progettazione di Software Sicuro · Sistemi Biometrici · Sicurezza Web & Mobile |

Ove possibile (in particolare **Crittografia** e **Reti di Calcolatori**) sono presenti **moduli aggiuntivi interamente dedicati agli appelli passati risolti**, con soluzioni complete passo-passo: chi non ha mai sostenuto l'esame sa esattamente cosa aspettarsi.

## Posizionamento e finalità

Sia chiaro, senza ambiguità:

- Questo progetto **non intende sostituirsi** ai materiali ufficiali dell'Università degli Studi di Milano, né essere proposto ai docenti come fonte canonica da adottare nei corsi.
- È un lavoro **gratuito, senza fini di lucro**, condiviso da uno studente con i propri colleghi perché possano vivere pienamente l'esperienza della laurea triennale.
- È **open source**, e l'open source vince sempre: la conoscenza migliora quando è libera di essere letta, corretta e migliorata da chiunque. Contributi, segnalazioni di errori e pull request sono benvenuti.

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
- **Tema cyberpunk** — Palette neon (cyan/magenta/lime) su base scura, font mono

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
