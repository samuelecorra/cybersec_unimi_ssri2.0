# Politica attiva: TEXT-FIRST

Questa politica, richiesta dall’utente durante il ciclo del 11 settembre 2026, **sostituisce i requisiti di rendering sistematico del MASTER_TASK**. Testo completo dell’istruzione in [TEXT_FIRST_OVERRIDE.md](TEXT_FIRST_OVERRIDE.md). Non riavviare inventario o lezioni già lette.

1. Contare le pagine ed estrarre il testo nativo in UTF-8 con confini di pagina, preferibilmente in una sola passata per PDF.
2. Leggere per blocchi gestibili, capire la funzione di ogni pagina e integrare o classificare consapevolmente ogni contenuto. L’estrazione da sola non equivale alla ricostruzione.
3. Creare placeholder conservativi dalle evidenze testuali quando il contenuto visivo è ricostruibile senza immagine.
4. Renderizzare **solo la pagina precisa** che richiede di risolvere un’ambiguità semantica: testo assente/parziale non spiegato dalle pagine vicine, formula ambigua, tabella disorganizzata, grafico o relazione spaziale essenziale non interpretabile dal testo.
5. Non renderizzare automaticamente le pagine LOW_TEXT o LIKELY_VISUAL. Verificare prima il contesto. Niente OCR se il testo nativo basta; registrare la ragione di ogni eccezione visiva/OCR.
6. Ogni pagina conta come elaborata quando contenuto e scopo sono compresi, incorporati/classificati, i placeholder utili sono presenti e la copertura è registrata. **Copertura non implica rendering.**

Sono vietati screenshot o rendering sistematici ogni N pagine, e nuove ispezioni di pagine già correttamente elaborate solo per applicare la nuova strategia.

## Stati della copertura visiva

- `not required`: valore ordinario per pagine elaborate dal testo senza bisogno di fallback.
- `inspected` / `sì`: visione effettivamente eseguita; per nuove pagine, aggiungere la motivazione dell’eccezione.
- `pending` / `no`: pagina non ancora analizzata; non inferire che debba essere renderizzata.

Le 82 pagine della Lezione 01 erano già state lette visivamente prima della correzione e mantengono tale registrazione storica. Non sono nuove eccezioni della modalità text-first. Nessuna pagina PDF è stata riletta o renderizzata dopo il cambio di politica.

## Efficienza del ciclo 01

| Fase | Pagine elaborate | Solo testo | Viste visivamente | OCR |
|---|---:|---:|---:|---:|
| Prima della correzione | 82 | 0 | 82 | 0 |
| Dopo la correzione | 0 nuove; completamento verifiche Lezione 01 | 0 | 0 | 0 |

La vecchia strategia ha prodotto 14 tavole di contatto e ingrandimenti mirati per contenuti densi. È un dato storico, non un metodo da replicare. I rendering/cache sono temporanei fuori dal corso, non asset da committare. Per il prossimo batch, riportare pagine elaborate, solo testo, visive/OCR e ragioni delle sole eccezioni.
