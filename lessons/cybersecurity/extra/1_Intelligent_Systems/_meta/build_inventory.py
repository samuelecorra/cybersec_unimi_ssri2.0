"""Read-only source discovery; initialize metadata once, never overwrite coverage.

Run from any directory with Python + PyMuPDF. Generated metadata is excluded
from the immutable-source manifest. PDF page counts are NOT inspection claims.
"""
from pathlib import Path
import collections
import hashlib
import json
import subprocess
import fitz

ROOT = Path(__file__).resolve().parents[1]
META = ROOT / '_meta'
MANIFEST = META / 'SOURCE_MANIFEST.json'


def main():
    if MANIFEST.exists():
        raise SystemExit('Inventory already exists: verify hashes and reconcile new files explicitly; do not reset progress.')
    files = sorted(p for p in ROOT.rglob('*') if p.is_file() and META not in p.parents)
    records = []
    for p in files:
        item = dict(path=p.relative_to(ROOT).as_posix(), bytes=p.stat().st_size,
                    sha256=hashlib.sha256(p.read_bytes()).hexdigest(), extension=p.suffix.lower())
        if p.suffix.lower() == '.pdf':
            with fitz.open(p) as doc:
                item['pages'] = len(doc)
        if p.suffix.lower() == '.ipynb':
            notebook = json.loads(p.read_text(encoding='utf-8'))
            item['cells'] = dict(collections.Counter(c['cell_type'] for c in notebook['cells']))
        records.append(item)
    MANIFEST.write_text(json.dumps({'date': '2026-09-11', 'sources': records}, ensure_ascii=False, indent=2)+'\n', encoding='utf-8')
    pdfs = [r for r in records if 'pages' in r]
    groups = sorted(set(Path(r['path']).parts[0] for r in records if '/' in r['path']))
    lines = ['# Inventario delle fonti — Intelligent Systems', '',
             'Rilevazione ricorsiva del 11 settembre 2026, prima della ricostruzione. Fonti immutabili: hash SHA-256 e dimensioni in `SOURCE_MANIFEST.json`.', '',
             f'**24 lezioni numerate (01–24), 1 cartella accessoria (00/RAG), {len(records)} file originali, {len(pdfs)} PDF, {sum(r["pages"] for r in pdfs)} pagine PDF, {len(records)-len(pdfs)} file non PDF.**', '',
             'Le circa 900 pagine ipotizzate nella richiesta non corrispondono al materiale presente: il totale verificato è 2.068. Le cartelle 03 e 12 contengono due PDF ciascuna; nessun archivio compresso presente.', '',
             'Inventariato significa individuato e descritto strutturalmente, non letto semanticamente. Le letture effettive sono in COURSE_PROGRESS e SOURCE_COVERAGE.', '',
             '## Materiale globale', '',
             '- `Intro_Corso.md`: descrizione preesistente, anno accademico 2025–2026 e collegamento al catalogo con percorso 2027; conservare entrambi senza dedurre una nuova edizione.', '',]
    relations = {
        'Lezione00_RAG_Jarvis_for_IS4': 'Risorsa globale: RAG.md descrive il supporto RAG e rimanda a MyAriel. Non è una venticinquesima lezione PDF. Letto integralmente; il servizio remoto non è una fonte locale acquisita.',
        'Lezione03': 'Due PDF da leggere entrambi; la matrice AI Apocalypse è un allegato della stessa lezione, non una lezione autonoma.',
        'Lezione07': 'Due notebook e uno script Python su Iris e numero di parametri. Collegamento apparente con il PDF e con gli esempi Iris della 16; verificare contenuti e differenze durante la ricostruzione.',
        'Lezione08': 'Script Python di image hashing; collegamento apparente con similarità e immagini nel PDF e nella 09.',
        'Lezione09': 'Sottocartelle code/ e images/: 4 sorgenti e 10 JPEG. Verificare gli input realmente consumati dagli script, incluso il file prefissato __no__ (non ometterlo).',
        'Lezione12': 'PDF principale più PDF di punteggi della simulazione (1 pagina); entrambi obbligatori.',
        'Lezione16': 'Dataset .dat, notebook e script MATLAB Iris: struttura, etichette, encoding e dipendenze da verificare con il PDF.',
        'Lezione17': 'Tre supplementi con prefisso Lesson_16, conservati nella 17: regressori lineari, modelli neurali e decision tree wine. Associazione provvisoria alla cartella 17; verificare rimandi alla 16 senza rinominare o spostare.',
        'Lezione18': 'Notebook associato alla riduzione dimensionale, da confrontare con il PDF.',
        'Lezione19': 'Script MATLAB kNN associato al PDF, da leggere integralmente.',
        'Lezione21': 'Notebook Inception v3 e Truck.jpg/Truck299.jpg; verificare classificazione, dimensioni e preprocessing effettivi.',
    }
    for group in groups:
        subset = [r for r in records if r['path'].startswith(group+'/')]
        lines += ['## '+group, '', relations.get(group, 'Associazione certa per collocazione: tutti i file della cartella appartengono a questa unità di lavoro. Nessun supplemento separato presente.'), '',
                  '| File relativo al corso | Tipo / struttura | Pagine |', '|---|---|---:|']
        for r in subset:
            detail = r['extension']
            if 'cells' in r:
                detail += '; celle: ' + ', '.join(f'{k}={v}' for k,v in r['cells'].items()) + ' (solo inventario strutturale)'
            lines += [f'| `{r["path"]}` | {detail} | {r.get("pages", "—")} |']
        lines += ['']
    lines += ['## Conservazione e pubblicazione', '',
              'Tutti i 57 file erano già presenti e l’intera directory del corso risultava non tracciata da Git. Non confondere questi originali con i file generati in questa sessione.', '',
              'Il frontend indicizza automaticamente Markdown/PDF. I formati .py, .m, .ipynb e .dat di questo corso non sono attualmente copiati dalla build di produzione; i riferimenti ai supplementi restano percorsi del repository. Risolvere la distribuzione dei supplementi prima del quality gate globale, senza modificarli. Nessuna modifica al frontend in questo ciclo.', '']
    (META/'COURSE_INVENTORY.md').write_text('\n'.join(lines), encoding='utf-8')
    lines = ['# Copertura delle fonti — Intelligent Systems', '',
             'Numerazione: pagina fisica PDF, a partire da 1. `no` significa non ispezionata; estrazione automatica e conteggio pagine non costituiscono lettura. Una riga per ogni pagina, anche per titoli e duplicati.', '',
             'I placeholder mantengono il commento vuoto del repository; un commento SOURCE_VISUAL immediatamente precedente ne registra ID, PDF, pagina, tipo e descrizione. Il registro visivo sarà aggiunto dopo la lettura.', '']
    for pdf in pdfs:
        lines += ['## '+pdf['path'], '', '| Pagina | Testo letto | Visivo letto | Tipo / esito | Destinazione | Note |', '|---:|---|---|---|---|---|']
        lines += [f'| {i} | no | no | NOT STARTED | — | — |' for i in range(1,pdf['pages']+1)]
        lines += ['']
    lines += ['## Fonti non PDF', '', '| File | Ispezione | Destinazione / note |', '|---|---|---|']
    for r in records:
        if 'pages' not in r:
            read = r['extension'] == '.md'
            lines += [f'| `{r["path"]}` | {"letta integralmente" if read else "solo inventario"} | {"risorsa introduttiva conservata" if read else "da integrare nella lezione associata"} |']
    (META/'SOURCE_COVERAGE.md').write_text('\n'.join(lines)+'\n', encoding='utf-8')
    print(f'{len(records)} sources, {len(pdfs)} PDFs, {sum(r["pages"] for r in pdfs)} pages')


if __name__ == '__main__':
    main()
