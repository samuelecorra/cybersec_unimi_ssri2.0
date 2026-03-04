# Samu CyberLocker

> **⚠️ USO PERSONALE LOCALE — NON ESPORRE SU INTERNET**

Archivio personale locale delle lezioni universitarie di Cybersecurity (3 anni, tutte le materie).  
Web app React + Vite con UI cyberpunk, navigazione filesystem stile IronMath, viewer Markdown completo.

## Quick Start

```bash
git clone https://github.com/samuelecorra/samu-cyberlocker.git
cd samu-cyberlocker
npm install
npm run dev
```

L'app sarà disponibile su **http://127.0.0.1:5180** (solo localhost).

## Funzionalità

- **Filesystem navigabile** — Albero cartelle/file ricorsivo nella sidebar sinistra
- **Viewer Markdown** — Rendering completo con titoli, liste, tabelle, code block, immagini relative
- **Ricerca live** — Cerca per nome file, heading e contenuto (Ctrl+K per focus)
- **Navigazione prev/next** — Scorri tutti i file in ordine con i pulsanti precedente/successivo
- **Breadcrumb** — Percorso del file aperto sempre visibile
- **Persistenza** — Ultimo file aperto e stato dell'albero salvati in localStorage
- **Tema cyberpunk** — Palette neon (cyan/magenta/lime) su base scura, font mono

## Struttura Progetto

```
samu-cyberlocker/
├── lessons/                    # Lezioni .md (anno1/anno2/anno3)
│   └── cybersecurity/
│       ├── anno1/
│       ├── anno2/
│       └── anno3/
├── src/
│   ├── main.jsx               # Entry point React
│   ├── App.jsx                # Component principale + state
│   ├── components/
│   │   ├── Sidebar.jsx        # Sidebar con tree + risultati ricerca
│   │   ├── FileTree.jsx       # Albero file ricorsivo
│   │   ├── Viewer.jsx         # Renderer Markdown
│   │   ├── Breadcrumb.jsx     # Breadcrumb percorso file
│   │   ├── Navigation.jsx     # Pulsanti prev/next
│   │   └── SearchBar.jsx      # Barra di ricerca con Ctrl+K
│   ├── hooks/
│   │   └── useLocalStorage.js # Hook per persistenza localStorage
│   ├── styles/
│   │   └── cyberpunk.css      # Tema cyberpunk completo
│   └── utils/
│       └── tree.js            # Utility per albero file
├── vite-plugin-lessons.js     # Plugin Vite per scan filesystem
├── vite.config.js             # Config Vite (host 127.0.0.1)
├── package.json
├── index.html
└── README.md
```

## Comandi

| Comando | Descrizione |
|---------|-------------|
| `npm run dev` | Avvia dev server su http://127.0.0.1:5180 |
| `npm run build` | Build di produzione in `dist/` |
| `npm run preview` | Preview build su http://127.0.0.1:5180 |

## Sicurezza

- L'app si binda **esclusivamente su 127.0.0.1** (mai 0.0.0.0)
- **Nessun login** richiesto (uso personale offline)
- **Nessuna API esterna** chiamata
- **Nessun backend** separato — tutto servito dal dev server Vite
- I file .md vengono letti dal filesystem locale tramite plugin Vite

## Stack

- React 18
- Vite 6
- react-markdown + remark-gfm + rehype-highlight
- highlight.js (syntax highlighting code block)
- CSS custom (tema cyberpunk, no framework)

## Struttura Lezioni
- `lessons/cybersecurity/`: contenuto completo lezioni/materiali.
- `index.html`, `app.js`, `styles.css`: viewer locale filesystem-style.
- `manifest.json`: indice file generato automaticamente.
- `scripts/build-manifest.mjs`: rigenera il manifest.

## Avvio locale
1. Apri la repo in VS Code.
2. Avvia `Live Server` su `index.html`.
3. Apri l'URL locale nel browser.

## Aggiornare il manifest
Quando aggiungi/rimuovi file in `lessons/cybersecurity`:

```bash
node scripts/build-manifest.mjs
```

Poi ricarica la pagina.

## Note privacy
Repository pensata per uso personale locale.
