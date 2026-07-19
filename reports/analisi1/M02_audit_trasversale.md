# Audit trasversale conclusivo di M02 - Insiemistica e Relazioni

## 1. Ambito e metodo

Audit eseguito su `lessons/cybersecurity/anno1/1_Analisi 1/M02_Insiemistica e Relazioni/`.

Perimetro controllato:

- UD1 - Insiemi: 4 lezioni;
- UD2 - Applicazioni: 4 lezioni;
- UD3 - Relazioni: 3 lezioni;
- UD4 - Min Max Inf Sup: 3 lezioni.

Metodo:

- inventario dei file e confronto con `schema_lezioni.md` e `manifest.json`;
- controllo dei nuclei matematici richiesti: logica, insiemi, applicazioni, relazioni, equivalenze, ordini, insiemi numerici, estremi;
- verifica di definizioni, ipotesi, esempi, controesempi, dimostrazioni e notazione;
- validazione KaTeX con `scripts/check-latex.mjs`;
- controllo dei placeholder grafici, TODO non grafici, link interni e coerenza tecnica.

## 2. Inventario strutturale

M02 contiene 14 file markdown:

- `UD1 - Insiemi/L1 - Intro Insiemistica.md`;
- `UD1 - Insiemi/L2 - Sottoinsiemi.md`;
- `UD1 - Insiemi/L3 - Operazioni insiemistiche.md`;
- `UD1 - Insiemi/L4 - Operatori Booleani OR AND NOT.md`;
- `UD2 - Applicazioni/L1 - Intro Applicazioni.md`;
- `UD2 - Applicazioni/L2 - Caratteristiche delle applicazioni.md`;
- `UD2 - Applicazioni/L3 - Applicazione inversa.md`;
- `UD2 - Applicazioni/L4 - Composizione.md`;
- `UD3 - Relazioni/L1 - Intro Relazioni.md`;
- `UD3 - Relazioni/L2 - Relazioni d'ordine.md`;
- `UD3 - Relazioni/L3 - Relazioni d'equivalenza.md`;
- `UD4 - Min Max Inf Sup/L1 - Insiemi Numerici.md`;
- `UD4 - Min Max Inf Sup/L2 - Minimo e massimo.md`;
- `UD4 - Min Max Inf Sup/L3 - Sup e Inf.md`.

La struttura è coerente con `schema_lezioni.md`; non è stato necessario creare nuove lezioni né modificare lo schema.

## 3. Stato iniziale dell'audit

La base esistente era corretta e già leggibile, ma ancora troppo essenziale in vari punti:

- insiemi: mancavano differenza simmetrica, famiglie indicizzate e dimostrazioni elemento per elemento delle identità;
- logica: mancavano condizioni necessarie/sufficienti, contronominale e uso esplicito dei controesempi;
- funzioni: mancavano controimmagini di sottoinsiemi, restrizione, estensione, funzioni identità/costante e proprietà di immagini e controimmagini;
- inverse: il criterio invertibile se e solo se biiettiva era enunciato ma non dimostrato, e mancavano inverse destre/sinistre;
- relazioni: mancavano dominio, immagine, relazione inversa, composizione, irriflessività, asimmetria e totalità;
- ordini: mancavano ordini stretti, diagrammi di Hasse, elementi minimali/massimali e distinzione minimale/minimo;
- equivalenze: mancavano dimostrazione completa del fatto che le classi sono coincidenti o disgiunte e corrispondenza con partizioni;
- insiemi numerici: mancavano inclusione in $\mathbb{C}$, chiusura delle operazioni e densità di razionali/irrazionali;
- estremi: mancavano casi vuoti/illimitati, esempi parametrici, unicità del supremo e collegamenti operativi con successioni e ottimizzazione;
- placeholder: due commenti legacy `INSERT INSTRUCTOR SLIDE/DIAGRAM HERE` non rispettavano il formato `TODO FIGURA`.

## 4. Interventi conclusivi

Sono stati integrati:

- differenza simmetrica, famiglie indicizzate, unioni/intersezioni indicizzate e dimostrazione elemento per elemento di De Morgan;
- condizioni necessarie e sufficienti, tecniche di dimostrazione diretta/contronominale e controesempi;
- controimmagine di sottoinsiemi, restrizione, estensione, identità, funzione costante e proprietà di immagini/controimmagini rispetto a unione, intersezione e complementare;
- dimostrazione completa di `invertibile iff biiettiva`, inverse sinistre e inverse destre;
- dominio, immagine, inversa e composizione di relazioni;
- irriflessività, asimmetria, totalità e controesempi;
- ordini stretti/non stretti, Hasse, minimali/massimali, minimo/massimo in ordini parziali;
- dimostrazione delle classi di equivalenza uguali o disgiunte e passaggio partizione -> equivalenza;
- $\mathbb{N}\subseteq\mathbb{Z}\subseteq\mathbb{Q}\subseteq\mathbb{R}\subseteq\mathbb{C}$, chiusura e densità;
- casi vuoti e illimitati per min/max/sup/inf, esempi parametrici e collegamenti a successioni/ottimizzazione;
- esercizi rappresentativi trasversali a fine unità;
- 4 placeholder grafici dettagliati nel formato richiesto.

## 5. Copertura per nucleo

Logica operativa:

- proposizioni, predicati, AND/OR/NOT, implicazione, equivalenza e quantificatori coperti;
- negazioni di congiunzioni, disgiunzioni e quantificatori coperte;
- necessità/sufficienza, contronominale e controesempi integrati.

Insiemi:

- appartenenza, vuoto, universo, uguaglianza, inclusione propria/impropria, parti e intervalli coperti;
- unione, intersezione, differenza, complementare, differenza simmetrica e prodotto cartesiano coperti;
- De Morgan giustificato elemento per elemento;
- famiglie indicizzate coperte con esempio su intervalli annidati.

Applicazioni:

- dominio, codominio, immagine, grafico e uguaglianza fra funzioni coperti;
- controimmagine, restrizione, estensione, identità e costante integrati;
- iniettività, suriettività, biiettività, composizione, associatività e inversa coperti;
- inverse destre/sinistre e criterio di invertibilità integrati;
- proprietà di immagini e controimmagini chiarite, con inclusione propria per le immagini di intersezioni.

Relazioni:

- relazioni binarie, coppie ordinate, grafi funzionali, dominio, immagine, inversa e composizione coperti;
- riflessività, irriflessività, simmetria, antisimmetria, asimmetria, transitività e totalità coperte con esempi.

Equivalenze:

- classi, quoziente, congruenza modulo $n$ e frazioni equivalenti coperti;
- dimostrazione classi disgiunte/coincidenti integrata;
- corrispondenza tra partizioni e relazioni di equivalenza integrata.

Ordini:

- ordini parziali, totali, stretti e non stretti coperti;
- confrontabilità, Hasse, minimali/massimali, minimo/massimo, maggioranti/minoranti coperti;
- distinzione minimale/minimo esplicitata con controesempio.

Insiemi numerici ed estremi:

- convenzione $\mathbb{N}=\{0,1,2,\dots\}$ mantenuta;
- chiusura, densità di razionali e irrazionali, completezza di $\mathbb{R}$ e ruolo di $\mathbb{C}$ coperti;
- minimo, massimo, infimo, supremo, unicità, appartenenza/non appartenenza, vuoto e illimitatezza coperti.

## 6. Verifiche matematiche campionate

- De Morgan insiemistico verificato tramite appartenenza elemento per elemento.
- $f(E\cap F)\subseteq f(E)\cap f(F)$ verificato con controesempio a uguaglianza.
- Le controimmagini preservano esattamente unione, intersezione e complementare.
- `invertibile iff biiettiva` dimostrato in entrambi i versi.
- Classi di equivalenza uguali o disgiunte dimostrate usando simmetria e transitività.
- Minimo/massimale distinti con ordine parziale su sottoinsiemi non confrontabili.
- Supremo/infimo trattati con unicità, caratterizzazione con $\varepsilon$, casi vuoti e illimitati.

## 7. Qualità tecnica conclusiva

Esito dopo le integrazioni:

- `node scripts/check-latex.mjs "lessons/cybersecurity/anno1/1_Analisi 1/M02_Insiemistica e Relazioni"`: 14 file, 1182 formule, 0 errori;
- nessun `INSERT INSTRUCTOR SLIDE/DIAGRAM HERE`;
- nessun TODO non grafico rilevato in M02;
- 4 placeholder grafici nel formato `<!-- TODO FIGURA: ... -->`;
- nessun link markdown interno da risolvere in M02;
- struttura invariata rispetto a `schema_lezioni.md`.

## 8. Valutazione

M02 è ora adatto come modulo autosufficiente di fondamenti per Analisi 1. La copertura è rigorosa ma propedeutica: evita di duplicare integralmente M03 sulla cardinalità e rinvia ad altri moduli le costruzioni avanzate non necessarie, mantenendo però precise completezza reale, densità, funzioni inverse, relazioni e ordini.

## 9. Revisione conclusiva di accettazione

Data della revisione di accettazione: 19 luglio 2026.

La revisione di accettazione ha verificato che gli interventi dichiarati dall'audit siano effettivamente presenti nei file finali e non soltanto nominati nei titoli.

Correzioni aggiuntive applicate durante l'accettazione:

- integrate tavole di verità essenziali per AND, OR, NOT e implicazione;
- esplicitato che l'ordine dei quantificatori è significativo, con esempio vero/falso;
- integrata la dimostrazione per assurdo con esempio su assenza di massimo in $(0,1)$;
- aggiunta la convenzione operativa per la composizione di relazioni: in $S\circ R$ si applica prima $R$ e poi $S$;
- aggiunti esercizi rappresentativi su operazioni insiemistiche, insieme delle parti, prodotto cartesiano, identità elemento per elemento, dominio/immagine/inversa/composizione di relazioni, ordini parziali, Hasse, minimali, massimali, minimo e massimo.

Matrice di chiusura sintetica:

| Elemento dell'audit | File interessato | Stato finale | Riscontro |
|---|---|---|---|
| Differenza simmetrica | UD1/L3 | Implementato | Definizione tramite differenze e tramite unione meno intersezione, esempio ed esercizio |
| Famiglie indicizzate | UD1/L3 | Implementato | Definizione di $(A_i)_{i\in I}$, unione/intersezione con $\exists$ e $\forall$, esempio su intervalli |
| De Morgan elemento per elemento | UD1/L3 | Implementato | Dimostrazione con elemento generico e passaggi logici reversibili |
| Condizioni necessarie/sufficienti | UD1/L4 | Implementato | Definizioni, verso delle implicazioni, esempio ed esercizio |
| Contronominale e controesempi | UD1/L4 | Implementato | Equivalenza logica, esempio su parità, controesempio universale |
| Assurdo | UD1/L4 | Implementato | Aggiunto esempio su assenza di massimo di $(0,1)$ |
| Tavole di verità | UD1/L4 | Implementato | Tabelle essenziali per connettivi e implicazione |
| Ordine dei quantificatori | UD1/L4 | Implementato | Esempio $\forall x\exists y$ vero e $\exists y\forall x$ falso |
| Controimmagini e proprietà | UD2/L1 | Implementato | Definizione per sottoinsiemi, distinzione da inversa, proprietà rispetto a operazioni |
| Restrizione, estensione, identità, costante | UD2/L1, UD2/L4 | Implementato | Definizioni e identità come neutro della composizione |
| Immagine di intersezione | UD2/L1, UD2/L4 | Implementato | Inclusione corretta e controesempio a uguaglianza |
| Invertibile iff biiettiva | UD2/L3 | Implementato | Dimostrazione in entrambi i versi |
| Inverse destre/sinistre | UD2/L3, UD2/L4 | Implementato | Definizione, significato e esercizio su inversa destra |
| Dominio/immagine/inversa/composizione di relazioni | UD3/L1 | Implementato | Definizioni formali ed esercizi |
| Convenzione composizione relazioni | UD3/L1 | Implementato | Esplicitato che in $S\circ R$ si applica prima $R$ e poi $S$ |
| Irriflessività, asimmetria, totalità | UD3/L1 | Implementato | Definizioni, esempi e confronto asimmetria/antisimmetria |
| Ordini stretti/non stretti | UD3/L2 | Implementato | Definizione tramite $\preceq$ e $\neq$, esempi su $\mathbb{R}$ |
| Hasse, minimali/massimali | UD3/L2 | Implementato | Descrizione, placeholder, controesempi ed esercizi |
| Classi disgiunte/coincidenti | UD3/L3 | Implementato | Dimostrazione completa con simmetria e transitività |
| Partizioni ed equivalenze | UD3/L3 | Implementato | Costruzione da partizione a relazione di equivalenza |
| Numerici, chiusura, densità, $\mathbb{C}$ | UD4/L1 | Implementato | Catena inclusiva, chiusura per operazioni, densità e ruolo propedeutico di $\mathbb{C}$ |
| Casi vuoti/illimitati per estremi | UD4/L2, UD4/L3 | Implementato | Min/max del vuoto, sup/inf del vuoto non definiti in $\mathbb{R}$, insiemi illimitati |
| Esempi parametrici e collegamenti futuri | UD4/L2, UD4/L3 | Implementato | Famiglie $A_t$, $A_\alpha$, collegamenti a ottimizzazione e successioni |
| Placeholder grafici | UD1/L3, UD2/L1, UD2/L3, UD3/L2 | Implementato | 4 placeholder conformi e inventariati |

Confine con M03:

| Argomento trasversale | Trattazione in M02 | Trattazione prevista in M03 | Esito |
|---|---|---|---|
| Insiemi finiti | Definizione di parti, prodotto cartesiano, cenni a insiemi finiti non vuoti | Cardinalità finita, biiezioni con insiemi standard, unioni finite, prodotto cartesiano, principio dei cassetti | Separazione corretta |
| Insiemi infiniti | Solo esempi e prerequisiti insiemistici | Definizione intuitiva, Dedekind-infinito, sottoinsiemi infiniti | Rinviato correttamente |
| Numerabilità | Non sviluppata, solo famiglie indicizzate e $\mathbb{N}$ come dominio futuro | Insiemi numerabili, $\mathbb{Z}$, $\mathbb{Q}$, unioni numerabili | Rinviato correttamente |
| Potenza del continuo | Non sviluppata; densità di razionali/irrazionali limitata a ordine reale | Intervalli equipotenti, diagonale di Cantor, continuo | Rinviato correttamente |
| Insieme delle parti | Definizione e uso in ordini parziali | Potenza dell'insieme delle parti e crescita di cardinalità | Nessuna duplicazione integrale |
| Biiezioni | Definizione e inversa funzionale | Uso per cardinalità e confronti tra insiemi | Prerequisito coperto |

Esito dell'accettazione: M02 non presenta lacune matematiche pertinenti al proprio perimetro. Gli approfondimenti rimasti fuori modulo, cioè cardinalità, numerabilità e potenza del continuo, sono collocati in M03.
