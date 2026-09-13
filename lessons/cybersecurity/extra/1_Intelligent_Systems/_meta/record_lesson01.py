"""Persist the human/agent page inspection performed on 2026-09-11.

This is a transcription of the inspection log, NOT automatic PDF comprehension.
Do not reuse it to claim inspection of a changed source: compare the manifest.
"""
from pathlib import Path
import json
import re
from urllib.parse import quote

ROOT = Path(__file__).resolve().parents[1]
META = ROOT / '_meta'
LESSON = ROOT / 'Lezione01' / 'L01 - Introduzione ai sistemi intelligenti.md'
PDF = 'Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf'

# physical page | destination | purpose and inspection finding
NOTES = '''1|Introduzione|Titolo, docente e nome IS4 incorporati; fascia fotografica di copertina decorativa, ripetuta nei separatori.
2|Introduzione; §§1–10|Indice: introduzione, progressi, corso, esame, bias, limiti e applicazioni. Tutti i nuclei rappresentati.
3|§1.1|Domande guida sul sistema intelligente e sui suoi usi; copertina e cervello stilizzato non aggiungono contenuto.
4|§1.2|Tutte le sette sigle conservate. Precisazione distinta su testing e validation, accostati nella slide.
5|§1.1|Definizione operativa e quattro capacità cognitive; elaborazione dati, predizioni e insight.
6|§1.3|Tutti i dieci esempi conservati; sensore, chatbot e radiografie letti visivamente.
7|§1.3|Diffusione, lavoro/economie, pattern, rilevazione precoce e risorse. Fotogrammi fantascientifici spiegati come contrasto retorico, non risultati tecnici.
8|§1.3|Grafico Google Trends letto: crescita dell’interesse, distinta da accuratezza e adozione.
9|§1.3|Elenco completo dei settori e sottocasi finanziari; fotografia di cockpit illustrativa senza dati aggiuntivi.
10|§1.3|Task completi, inclusi OCR, scrittura, voce, volti, creatività, giochi, NLP e controllo; foto con telefono non aggiunge un caso diverso.
11|§1.3|Applicazioni informatiche e invito al progetto personale; freccia di enfasi non è un’architettura. Space exploration contestualizzato dagli esempi areas/cities/houses.
12|§2.1|Diagramma dei quattro contenuti e interconnessioni; confini fuzzy esplicitati.
13|§2.1|Teoria, pattern matching, apprendimento, analisi/elaborazione, feature extraction/fusion. Diagramma duplicato di p12; clipart decorativa.
14|§2.1|Toolbox SW/HW, flessibilità, MATLAB/TensorFlow/Colab, CUDA, edge e fog. Diagramma duplicato di p12.
15|§2.1|Overviews, stato dell’arte, settori, strumenti, dataset, limiti/trend e presenza all’esame. Diagramma duplicato p12 e clipart ornamentali.
16|§2.1|Casi d’uso, gestione dati, I/O, risultati, codice e strumenti. Diagramma duplicato p12.
17|§2.2|Flusso lezioni-laboratori simulati-esame; dettagli delle attività conservati.
18|§2.2|Avviso amministrativo di riscaricare i PDF aggiornati. Immagini e meme decorativi omessi.
19|Introduzione; §2|Separatore IS4, duplicato del titolo; fascia fotografica ripetuta.
20|§2.2|Livelli di astrazione dalla teoria alle applicazioni e agli use cases.
21|§2.3|Competenze ulteriori rispetto all’addestramento NN: dati, modelli, errori, settori e toolbox.
22|§2.3|ML diverso da deep NN; analogia visiva martello-chiodo e rischio della monocultura tecnica.
23|§2.3|Stato dell’arte storico, best practices, ricerca/mercato, fusione dati e confronti fair. Illustrazione dashboard ornamentale.
24|§2.3|Feature comuni/distintive, tabelle e grafici prima di training-validation-deployment. Dashboard duplicata p23.
25|§3|Separatore esame; clipart priva di quesiti didattici leggibili da ricostruire.
26|§3.1–3.2|Ambiti esame e consiglio di interrogarsi su tutte le slide. Foto aula/cartello decorativi.
27|§3.1|Solo scritto, nessun progetto, simulazioni e dettagli durante il corso; duplicato parziale p28.
28|§3.1|Prova in laboratorio PC, niente orale/progetti, casi di design e interpretazione codice, domande dal materiale.
29|§3.1|Barratura VISIVA della vecchia penalità -1: regola sostitutiva nessun peso negativo. Durata, appelli e divieto materiale conservati.
30|§3.1–3.2|Iscrizione obbligatoria ogni appello, nessuna reiscrizione automatica. Calendario illustrativo senza scadenza specifica.
31|§3.2|Tabella ricostruita esattamente: 49/35/12/65; fiducia 67/75/99,9/80%. Regola di esclusione, non calcolo probabilistico.
32|§3.2|Scorrere tutte le slide, essere specifici, usare simulazioni per ragionamento e autovalutazione.
33|§4|Separatore panoramica e accelerazione; diagramma quattro settori duplicato p12, copertina ripetuta.
34|§4.1|Pagina quasi tutta raster: letti tutti i sei strati e relative etichette. Icone AI/A senza espansione non interpretate come nuovi concetti.
35|§4.2|Embedding, tutti i sette vettori con coordinate, corrispondenze dei due documenti. Date del PDF preservate e precisazione Transformer 2017 separata.
36|§4.2|Analogia Parigi/Francia/Tokyo/Giappone e calzini/piedi/guanti/mani.
37|§4.2|Tre conversazioni raster lette: capitali, indumenti e automobili-città/formiche-colonia.
38|§4.3|Tabella IMO letta ingrandita: tutte le 8 righe, punteggi e contesti preservati. Distinti grading ufficiale e claim esterno.
39|§4.3|Confronto biologico storico, 302/7000 e 10^11/10^3, data Nature; limiti della comparabilità esplicitati.
40|§7.1|Griglia di pattern artificiali, classi e setup AlexNet/ImageNet. Esempi rappresentativi e placeholder per tutta la griglia.
41|§7.1|Otto immagini simili a rumore, tutte le etichette conservate; non confondere con sole perturbazioni impercettibili.
42|§5.1|Tabella 3 società e infografica 6 società lette ingrandite, valori e indicatori conservati. Periodi eterogenei, ricavi diversi da utili e da ricavi AI.
43|§5.2|Lista prodotti/versioni trascritta come contenuto della fonte, esempi storici e priorità dei modelli locali. Loghi decorativi esclusi.
44|§5.2|Schema storico Copilot con flusso contesto/suggerimenti e frammento merge_sort letto. merge assente e parametro list segnalati; pannello JS parzialmente tagliato, illustra editor senza programma completo.
45|§5.3|Multimodalità e screenshot: nove mele, bullone sellino, manuale e attrezzi. Quattro modalità esplicitate.
46|§5.3|Tutte le componenti: immagine-encoder-latente-decoder, collage Cage, proteina e Nobel 2024. Lo schema non è da solo l’algoritmo di diffusione.
47|§5.4|Robotica, Optimus, manipolazione, magazzino, Boston Dynamics/Agility e atlante umanoidi: panoramica visiva conservata, nessun confronto quantitativo dedotto dalle foto.
48|§5.4|Cloud/edge, calcolo-storage remoti, latenza/privacy/energia locali; nomi produttori e limite delle garanzie esplicitati.
49|§5.5|Piramide 4 rischi e tutte le 5 date lette ingrandite. Calendario attribuito al PDF, non verifica esaustiva delle norme correnti.
50|§5.4|Self-supervised e continual distinti; pretext/target task, knowledge transfer, static/adaptive ML; esempi radiografie, wav2vec 2.0 e guida autonoma.
51|§6|Separatore sugli LLM come strumenti: tema incorporato; diagramma dei settori duplicato p12.
52|§6.1|Pipeline completa e ruolo congiunto Human Intelligence + LLM; frecce lette.
53|§6.1|Schermata tree/NN: struttura, feature importance, white-box e IF-THEN; testo finale della schermata tagliato nella fonte, nessuna continuazione inventata.
54|§6.2|Codice screenshot trascritto integralmente; assunzione RGB e assenza training/classificatore chiarite; coda esplicativa tagliata nella fonte non inventata.
55|§6.1; §6.3|Analisi/report/documentazione più schema prompt-context-LLM-output con feedback; accesso effettivo ai dati distinto dalla capacità dichiarata.
56|§6.3|Definizione hallucination, trascrizione numerica anomala e falsa capitale di Marte; la sequenza ripetitiva è output errato illustrativo, non dataset da ricopiare.
57|§6.4|Privacy, NDA/GDPR e privacy layer; frecce input e output sensibili lette e spiegate.
58|§6.4; §8|Bias dei dati di training, obsolescenza e specializzazione di dominio.
59|§6.5|Biografia falsa di matematico Bicocca; contenuto identificato come output errato, non fatto biografico.
60|§6.5|Aggiunta contesto Milano; falsa statistica e falsa paternità del libro; copertina Lai/Xing verifica interna. Prezzo/rating del negozio non pertinenti e omessi.
61|§6.5|Sette autodescrizioni dei limiti del modello, input e Gestalt; contraddizione apparente sui bias chiarita.
62|§6.5|Risposta versione 4, cutoff settembre 2021, maggiore pertinenza e limiti; risposta successiva differente. Nessun dato di carriera assunto come verificato.
63|§7.1|Gatto-scatola classificato Snail e persona con passeggino in attraversamento; conseguenze dipendono dal task.
64|§7.2|Separatore Natural Interaction, limite centrale; visuali di copertina/settori duplicati.
65|§7.2|Promessa di interazione tramite linguaggio/gesti/emozioni; foto bambino-robot pertinente.
66|§7.2|Quattro modalità e tutte le applicazioni: voce, gesti, aptica, visione/emozioni. Fotografie e landmark conservati.
67|§7.2|Context-aware sensing, feedback adattivo e architettura multimodale completa; encoder/proiezioni/LLM/uscite spiegati.
68|§7.3|Tassi storici e opinione Ng contestualizzati; <5% diverso da 5%, esempio 800x0,05=40. Titoli professionali di footer non riprodotti come incarichi attuali.
69|§7.4|Voice picking: fotografie, produttività/accuratezza/sicurezza e rimando esterno conservato nel registro fonti.
70|§7.4|Cobot/Cooperative Robots, cooperazione fisica e istruzione mediante tablet.
71|§8.1|Separatore bias con analogia del bambino e di chi insegna; copertina e schema ripetuti.
72|§8.1|Definizione bias cognitivi, caso CEO di Koller e immagini ricerca; illustrazione Harriet Lee-Merrion ornamentale non estratta.
73|§8.2|Intero esempio ospedali/fratture/apparecchi e correlazione spuria; radiografia conservata come visuale del caso.
74|§9.1|Separatore Data Knowledge Spectrum, domanda sulla conoscenza; copertina e schema ripetuti.
75|§9.1|Assi DATA/KNOWLEDGE, quattro regioni, Deep/Trad. NN, riferimento Watt-Borhani-Katsaggelos ed etimo; curva qualitativa non formula quantitativa.
76|§9.2|Grafici crescita illimitata/logistica, K=2000 nell’esempio, sequenza giorni 0–6 e Paramecium per mL; nessun punto campionato inventato.
77|§9.3|Task di object detection con cinque etichette/riquadri e immagini etichettate; posizione sullo spettro.
78|§9.4|Log senza label, assenza di modello noto e apprendimento non supervisionato; impossibilità circoscritta al training supervisionato diretto.
79|§10.1|Separatore use case veicoli autonomi; copertina e quattro settori ripetuti.
80|§10.1|Ottobot e tutte le funzioni annotate, assenza GPS dichiarata, elaborazioni locali; video collegato ma non allegato.
81|§10.2|Tutte le 7 funzioni con tutte le tecnologie e modelli conservate in tabella; descrizione generale distinta dall’hardware effettivo di Ottobot.
82|§10.3|Sintesi completa: teoria, esperienza, metodologia, toolbox, bias, interazione, spiegabilità, spettro ed esame. Foto promemoria decorativa.'''


def main():
    text = LESSON.read_text(encoding='utf-8')
    visuals = []
    def replace(match):
        page, kind, description = match.groups()
        identifier = f'IS01-V{len(visuals)+1:02}'
        visuals.append(dict(id=identifier, source=PDF, page=int(page), type=kind, description=description))
        return (f'<!-- SOURCE_VISUAL id="{identifier}" source="{PDF}" page="{page}" type="{kind}" description="{description}" -->\n'
                '<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->')
    if '{{VISUAL:' in text:
        text = re.sub(r'\{\{VISUAL:(\d+)\|([^|]+)\|([^}]+)\}\}', replace, text)
        LESSON.write_text(text, encoding='utf-8')
    else:
        for attributes in re.findall(r'<!-- SOURCE_VISUAL (.*?) -->', text):
            v = dict(re.findall(r'(\w+)="([^"]*)"', attributes));v['page'] = int(v['page']);visuals.append(v)
    rows = []
    for entry in NOTES.splitlines():
        page, section, note = entry.split('|', 2)
        page = int(page)
        rows.append(dict(page=page, text_read=True, visual_read=True, section=section,
                         result='incorporated; boilerplate/duplicate omitted as documented', note=note,
                         visuals=[v['id'] for v in visuals if v['page']==page]))
    assert [r['page'] for r in rows] == list(range(1,83))
    (META/'IS01_INSPECTION.json').write_text(json.dumps(dict(
        source='Lezione01/'+PDF, date='2026-09-11', method='Native text for all 82 pages + all 14 six-page contact sheets; individual enlarged pages for dense raster content.',
        detail_pages=[34,35,38,42,44,45,46,49,53,54,56,59,60,61,62,67,76],
        pages=rows, visuals=visuals),ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
    coverage = (META/'SOURCE_COVERAGE.md').read_text(encoding='utf-8')
    start = coverage.index('## Lezione01/')
    end = coverage.index('## Lezione02/',start)
    url='../'+quote(LESSON.relative_to(ROOT).as_posix(),safe='/')
    lines=['## Lezione01/'+PDF,'',f'Destinazione consolidata: [Lezione 01]({url}). Tutte le pagine lette nel testo nativo e nella rappresentazione visiva; note strutturate in IS01_INSPECTION.json.','',
           '| Pagina | Testo letto | Visivo letto | Tipo / esito | Destinazione | Note |','|---:|---|---|---|---|---|']
    for row in rows:
        result='incorporated'
        if row['page'] in [1,3,19,25,33,51,64,71,74,79]: result='title / incorporated; decorative omitted'
        note=row['note']+' '+', '.join(row['visuals'])
        lines.append(f'| {row["page"]} | sì | sì | {result} | {row["section"]} | {note.strip()} |')
    coverage=coverage[:start]+'\n'.join(lines)+'\n\n'+coverage[end:]
    marker='## Registro visuale Lezione 01'
    if marker in coverage: coverage=coverage[:coverage.index(marker)]
    coverage+='\n'+marker+'\n\nOgni ID identifica un commento SOURCE_VISUAL e il placeholder vuoto immediatamente successivo nella lezione. Tutti gli elementi di ogni composizione sono nella stessa pagina del PDF sopra indicato. Le tabelle semplici di pp.31, 38, 42 e 81 sono anche ricostruite nel testo.\n\n| ID | PDF pagina | Tipo | Descrizione |\n|---|---:|---|---|\n'
    coverage+='\n'.join(f'| {v["id"]} | {v["page"]} | {v["type"]} | {v["description"]} |' for v in visuals)+'\n'
    (META/'SOURCE_COVERAGE.md').write_text(coverage,encoding='utf-8')
    print(f'82 inspection rows, {len(visuals)} visual placeholders')


if __name__ == '__main__':
    main()
