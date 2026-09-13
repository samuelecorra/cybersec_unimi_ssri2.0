# Riferimenti e limiti dell’acquisizione — Lezione 01

La fonte didattica è il PDF locale, versione identificata dallo SHA-256 nel manifest. Tutte le 82 pagine sono state lette nel testo e visivamente. La lettura delle schermate incorporate non implica l’accesso alle conversazioni originali, ai siti o ai video rappresentati.

## Rimandi presenti nelle slide

| Pagina | Riferimento | Trattamento |
|---:|---|---|
| 8 | `https://trends.google.com/trends/explore?date=all&q=AI&hl=en-GB` | Letta la schermata archiviata, non ricostruita una serie attuale. |
| 34 | `https://www.linkedin.com/in/harry-ratcliffe-92b485183/` | Attribuzione Harry Ratcliffe della figura; due link identici incorporati nel PDF. Profilo remoto non consultato. |
| 36 | Frank Schilder, Armineh Nourbakhsh, Thomson Reuters | Attribuzione dell’esempio nelle slide, senza riferimento bibliografico completo. |
| 39 | Nature, 21 August 2019 | Riferimento incompleto nel PDF, mantenuto come data/attribuzione senza inventare titolo o DOI. |
| 40–41 | Anh Nguyen et al. | Rintracciato il lavoro primario corrispondente, vedi sotto. |
| 69 | `https://www.realtimelogistics.com.au/technologies/voice-picking-systems/` | Letti testo e foto della slide; non consultato il sito. |
| 72–73 | Daphne Koller | Attribuzione del docente degli esempi bias CEO/fratture; non inventato un articolo. |
| 75 | Machine Learning Refined, Watt – Borhani – Katsaggelos | Citazione presente nel grafico. Libro non allegato né dichiarato letto integralmente. |
| 80 | `https://ottonomy.io/` e `https://youtu.be/ESR2lveUgCc` | Link incorporati nel PDF. Letta la slide di Ottobot, non il video remoto. |

## Verifiche esterne puntuali del 11 settembre 2026

Queste fonti sostengono soltanto precisazioni distinte dal contenuto del docente; non aumentano il conteggio delle pagine del corso lette.

- [Keras — Conv2D](https://keras.io/api/layers/convolution_layers/convolution2d/): verificati stride 1, padding valid, use_bias=True, convenzioni dei canali e dimensioni di input/output. Derivati in modo esplicito l’output 254×254×25 e i 700 parametri del frammento della slide 54; TensorFlow non eseguito.
- [Vaswani et al. — Attention Is All You Need](https://arxiv.org/abs/1706.03762): metadati e abstract, data della prima versione 12 giugno 2017; precisazione sull’etichetta compressa “2018+” nella slide 35. Articolo non dichiarato letto integralmente.
- [Nguyen et al. — Deep Neural Networks are Easily Fooled](https://arxiv.org/abs/1412.1897): metadati/abstract del lavoro sulle immagini irriconoscibili classificate con alta confidenza; collegamento delle slide 40–41 alla fonte primaria. Articolo non dichiarato letto integralmente.
- [Regolamento UE 2024/1689 — testo originario](https://eur-lex.europa.eu/eli/reg/2024/1689/oj/eng): identificazione della base normativa. La timeline della slide 49 è conservata come contenuto della fonte; non è stata certificata la conformità corrente di alcun sistema né svolto un audit di tutte le modifiche legislative.

## Decisioni editoriali che evitano attribuzioni improprie

- Punteggi IMO e dati economici sono riportati come contenuto delle tabelle del PDF, non come ricerche statistiche autonome. Distinti benchmark, anni e modalità di valutazione; segnalato che ricavi aziendali non misurano utili della sola AI.
- Nomi/versioni dei prodotti sono l’elenco della slide 43; non è stata certificata disponibilità o classifica attuale.
- Il confronto neuroni biologici/reti artificiali è storico e non equivale a una metrica universale di intelligenza.
- Le biografie false di pp.59–60 e le autodescrizioni di p61 rimangono esempi di output, non fatti assunti dal testo. La risposta più recente a p62 non viene adottata come biografia verificata.
- Il diagramma encoder–bottleneck–decoder di p46 non basta a rappresentare il processo di diffusione.
- I limiti <5% e 5% di p68 sono distinti senza alterare il messaggio sul singolo errore critico.
- La frase “non possiamo creare un classificatore” di p78 è circoscritta all’addestramento supervisionato diretto con dati privi di label; non si afferma che ogni metodo di anomaly detection debba essere una grande rete.
