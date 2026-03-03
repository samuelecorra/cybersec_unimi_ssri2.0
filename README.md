# samu-cyberlocker

Archivio personale locale delle lezioni universitarie Cybersecurity.

## Obiettivo
- Repository separata da IronMath MVP (medie).
- Uso personale locale, non esposta come servizio pubblico.
- Apertura tramite Live Server (o server statico equivalente).

## Struttura
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
