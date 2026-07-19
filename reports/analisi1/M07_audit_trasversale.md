# Audit trasversale conclusivo di M07 - Funzioni

Data dell'audit: 19 luglio 2026.

## 1. Ambito e metodo

L'audit riguarda l'intera cartella `M07_Funzioni`. La fase iniziale ha letto integralmente le dieci lezioni preesistenti, ricostruito filesystem e schema, confrontato i prerequisiti di M01-M06 e ispezionato soltanto struttura e titoli di M08-M10. La fase correttiva ha integrato definizioni, prove, esempi, controesempi, esercizi e placeholder. La fase di accettazione ha ricontrollato ogni lacuna iniziale, formule KaTeX, link, struttura, manifest, notazione e correttezza matematica campionata.

## 2. Inventario strutturale

| Unità didattica | Lezioni iniziali | Lezioni finali | Stato |
|---|---:|---:|---|
| UD1 - Fondamenti delle funzioni | 3 | 7 | Completa |
| UD2 - Insieme di definizione | 4 | 5 | Completa |
| UD3 - Zeri e Segno | 3 | 3 | Completa |
| **Totale** | **10** | **15** | **3 UD allineate** |

Sono state aggiunte, con suffissi non distruttivi, UD1/L1A, L1B, L2A, L3A e UD2/L4A. Non sono stati rinominati, spostati o rimossi file. L'ordine finale è L1, L1A, L1B, L2, L2A, L3, L3A; L1-L4, L4A; L1-L3.

## 3. Mappa dei prerequisiti

1. M02 fornisce applicazioni tra insiemi, immagini, controimmagini, composizione e inverse unilaterali; M07 le specializza alle funzioni reali.
2. M01 fornisce algebra, disequazioni, valore assoluto e famiglie elementari; M07 ne organizza dominio, immagine e proprietà globali.
3. M05 identifica le successioni come funzioni su $\mathbb{N}$ e M06 usa le somme parziali; M07 esplicita il collegamento discreto.
4. M08 formalizzerà limiti, continuità, teorema degli zeri e Weierstrass.
5. M09-M10 useranno derivate per monotonia, estremi, concavità, inverse e trasformazioni locali.

Non risultano dipendenze circolari. I risultati futuri compaiono soltanto come anticipazioni dichiarate o come teoremi usati con tutte le ipotesi esplicite.

## 4. Contenuti inizialmente presenti

La versione iniziale comprendeva 10 lezioni, 1.350 righe e 386 formule. Erano corretti: definizione basilare di funzione reale, dominio/codominio/immagine, grafico come insieme, uguaglianza con domini diversi, domini di razionali, radicali, logaritmi e funzioni trigonometriche, valore assoluto, zeri, segno e richiamo condizionale alla bisezione.

Non erano presenti esercizi strutturati o link interni. Quattro placeholder usavano il formato legacy. Le definizioni erano sintetiche e quasi prive di dimostrazioni.

## 5. Lacune individuate

Le lacune sostanziali riguardavano: funzione contro formula e relazione; dominio dichiarato e naturale; domini di operazioni e composizioni; immagini e controimmagini; restrizione ed estensione; test verticale e orizzontale; iniettività, suriettività, inverse unilaterali e bilatere; monotonia e limitatezza; algebra di parità e periodicità; trasformazioni; funzioni a tratti e speciali; classificazione elementare; famiglie parametriche; equazioni funzionali; collegamenti informatici; dimostrazioni, controesempi ed esercizi.

Sono emersi anche due errori puntuali: periodicità formulata senza invarianza del dominio e confusione tra lunghezza dell'intervallo di bisezione ed errore del punto medio.

## 6. Interventi effettuati

Tutte le dieci lezioni preesistenti sono state ampliate. Le cinque nuove lezioni ospitano nuclei autonomi che avrebbero reso incoerenti o smisurate le lezioni originarie:

- UD1/L1A: operazioni, composizione, immagini, controimmagini, grafico, restrizione ed estensione;
- UD1/L1B: classificazione, test orizzontale, inverse e applicazioni informatiche;
- UD1/L2A: monotonia, limitatezza ed estremi senza derivate;
- UD1/L3A: trasformazioni e famiglie parametriche;
- UD2/L4A: funzioni a tratti, speciali, elementari ed equazioni funzionali.

Lo schema è stato aggiornato perché la struttura è cambiata. Nessuna lezione di M01-M06 o M08-M11 è stata modificata.

## 7. Definizioni revisionate

Sono ora distinte funzione, formula, espressione, grafico, tabella, algoritmo, relazione ed equazione. La funzione comprende dominio, codominio, legge, esistenza e unicità. Sono formalizzati dominio dichiarato e naturale, immagine e controimmagine, grafico, restrizione, estensione, iniettività, suriettività, biiettività, inversa, monotonia larga e stretta, limitatezza, estremi, parità, periodicità con invarianza del dominio, funzioni a tratti, segno, floor, ceiling, parte frazionaria, indicatrice, parti positiva e negativa, involuzioni e idempotenti.

## 8. Proprietà, teoremi e dimostrazioni

| Risultato | Collocazione | Esito |
|---|---|---|
| Domini di somma, prodotto e quoziente | UD1/L1A | Dimostrazione necessaria e sufficiente |
| Dominio della composizione | UD1/L1A | Dimostrazione e controesempio all'intersezione |
| Proprietà di immagini e controimmagini | UD1/L1A | Prova elemento per elemento e inclusione stretta |
| Test della retta verticale | UD1/L1A | Caratterizzazione da esistenza e unicità |
| Test della retta orizzontale | UD1/L1B | Caratterizzazione dell'iniettività |
| Invertibile se e solo se biiettiva | UD1/L1B | Dimostrazione nei due versi |
| Inverse sinistre e destre | UD1/L1B | Implicazioni dimostrate |
| Simmetria del grafico inverso | UD1/L1B | Equivalenza sulle coppie ordinate |
| Stretta monotonia implica iniettività | UD1/L2A | Dimostrazione e converso falso |
| Monotonia dell'inversa | UD1/L2A | Dimostrazione per assurdo |
| Composizione della monotonia | UD1/L2A | Quattro casi motivati |
| Unicità dei valori estremi | UD1/L2 | Doppia disuguaglianza |
| Decomposizione pari-dispari | UD1/L3 | Esistenza e unicità dimostrate |
| Proprietà dei periodi | UD1/L3 | Multipli, commensurabilità e controesempi |
| Trasformazione generale dei punti | UD1/L3A | Derivazione algebrica completa |
| Identità di indicatori e parti positive | UD2/L4A | Verifica per casi |

Nessuna prova usa derivate o assume la conclusione. Le equivalenze contengono entrambi i versi e i domini sono dichiarati.

## 9. Esempi ed esercizi

Il modulo finale contiene 105 esercizi numerati nelle sezioni dedicate, oltre a esempi e domande interne. Copre domini multipli, composizioni, immagini, zeri, segno, classificazione, inverse, monotonia, estremi, periodicità, trasformazioni, funzioni a tratti, floor, parametri e applicazioni informatiche.

UD3/L3 contiene un problema trasversale in dieci richieste con soluzione guidata: dominio, codominio, immagine, zeri, segno, monotonia, simmetrie, periodicità, biiettività, inversa, trasformazioni e rinvii analitici.

## 10. Notazione

Sono usati $D_f$, $\operatorname{Im}(f)$, $G_f$, $Z_f$, $f|_C$, $f^{-1}(E)$ per la controimmagine e $f^{-1}$ per l'inversa solo quando il contesto lo distingue. Composizione, funzioni trigonometriche e inverse, `arsinh`, `arcosh`, `artanh`, floor e ceiling seguono le convenzioni dei moduli certificati. I prodotti usano giustapposizione o `\cdot`; non risultano comandi LaTeX corrotti o privi di backslash.

## 11. Placeholder

Sono presenti 21 placeholder `TODO FIGURA`, tutti multilinea, chiusi e dotati di obiettivo didattico. I quattro placeholder legacy sono stati convertiti. L'inventario completo è in `M07_placeholder_grafici.md`.

## 12. Confine con M02

M02 resta la sede della teoria generale delle applicazioni e delle relazioni. M07 richiama sinteticamente i risultati necessari, li dimostra quando sono centrali per l'uso operativo e li applica a sottoinsiemi di $\mathbb{R}$. Non duplica relazioni di equivalenza, ordini o costruzioni insiemistiche.

## 13. Confine con M01

M01 conserva identità, equazioni e tecniche algebriche delle famiglie elementari. M07 non le ricostruisce integralmente: ne sistematizza dominio, immagine, invertibilità, simmetrie e trasformazioni e inserisce link diretti alle lezioni pertinenti.

## 14. Confine con M08

| Argomento | Trattazione in M07 | Trattazione prevista in M08 | Esito |
|---|---|---|---|
| Limiti | Solo proprietà rinviate | Definizioni e calcolo | Separazione corretta |
| Continuità | Ipotesi dichiarata nella bisezione | Definizione, teorema degli zeri e Weierstrass | Rinvio esplicito |
| Immagini elementari | Deduzioni algebriche e d'ordine | Giustificazioni analitiche ulteriori | Nessun vuoto |
| Supporto compatto | Non definito topologicamente | Nozioni topologiche future | Rinvio motivato |

M07 fornisce dominio, immagine, composizione, monotonia e funzioni elementari necessari per iniziare M08.

## 15. Confine con M09-M10

Monotonia, estremi e trasformazioni sono definiti senza derivate. Criteri tramite segno della derivata, tangenti, concavità, Taylor e derivata dell'inversa restano in M09-M10. M07 prepara le ipotesi e la terminologia senza anticipare le dimostrazioni differenziali.

## 16. Controlli matematici

Sono state verificate manualmente le dimostrazioni principali e campionati automaticamente 3.491 casi su: inverse affini e radicali, composizioni, trasformazione generale dei punti, decomposizione pari-dispari, identità floor/ceiling, parte frazionaria, periodicità del seno, indicatori e venti passi di bisezione. Il campionamento ha prodotto 3.491 esiti corretti e 0 fallimenti.

Sono stati inoltre ricontrollati i casi di dominio vuoto o con punti esclusi, le immagini di quadratiche e omografiche, la non commutatività della composizione, le inverse unilaterali, la non equivalenza tra iniettività e monotonia su domini arbitrari e il ruolo della continuità nel cambio di segno.

## 17. Controlli tecnici

| Controllo | Risultato |
|---|---|
| KaTeX su M07 | 15 file, 1.294 formule, 0 errori |
| KaTeX su Analisi 1 | 199 file, 15.779 formule, 0 errori |
| KaTeX sui report di Analisi 1 | 14 file, 478 formule, 0 errori |
| Manifest SPA | Rigenerato, 5.878 file complessivi |
| Filesystem/manifest di Analisi 1 | 241/241 file, nessun mancante o aggiuntivo |
| Struttura M07 | 15/15 lezioni su filesystem, schema e manifest, stesso ordine |
| Ordine del renderer M07 | L1, L1A, L1B, L2, L2A, L3, L3A; L1-L4, L4A; L1-L3 |
| Link interni M07 | 7 controllati, 0 rotti |
| File vuoti o quasi vuoti | 0 |
| TODO non grafici | 0 |
| Placeholder legacy | 0 |
| Commenti HTML | 21 aperture, 21 chiusure |
| Esercizi nelle sezioni dedicate | 105 |
| Verifiche matematiche campionate | 3.491, 0 fallimenti |
| Comandi LaTeX privi di backslash | 0 residui sostanziali |
| Paragrafi lunghi duplicati | 0 duplicati esatti su 73 paragrafi di almeno 200 caratteri |
| `git diff --check` sul perimetro | Superato |
| `git diff --check` globale | Restano soltanto 4 spaziature finali preesistenti in M11 |

Il conteggio strutturale globale passa a 248 nodi: 11 moduli, 39 UD e 198 lezioni. Restano le 22 coppie nominali storicamente difformi in M09-M11 già certificate nell'audit precedente; non riguardano M07 e non sono state modificate.

## 18. Matrice di chiusura

| Lacuna iniziale | File coinvolto | Intervento | Stato finale | Verifica |
|---|---|---|---|---|
| Funzione contro formula | UD1/L1 | Distinzioni e controesempi | Chiusa | Definizioni presenti |
| Dominio dichiarato/naturale | UD1/L1, UD2/L1 | Procedura e cancellazioni | Chiusa | Esempi multipli |
| Operazioni tra funzioni | UD1/L1A | Domini e prova | Chiusa | Dimostrazione completa |
| Dominio della composizione | UD1/L1A | Formula e controesempio | Chiusa | Riscontro algebrico |
| Immagini e controimmagini | UD1/L1, L1A | Proprietà e inclusione stretta | Chiusa | Prova elemento per elemento |
| Grafico e test verticale | UD1/L1A | Caratterizzazione | Chiusa | Esistenza e unicità |
| Restrizione ed estensione | UD1/L1A | Definizioni ed esempi | Chiusa | Quadrato e punto escluso |
| Classificazione e inverse | UD1/L1B | Teorema, inverse unilaterali | Chiusa | Due versi dimostrati |
| Monotonia e limitatezza | UD1/L2A | Definizioni, prove e controesempi | Chiusa | Nessuna derivata usata |
| Estremi | UD1/L2, L2A | Locali/globali, unicità | Chiusa | Esempi di non raggiungimento |
| Parità | UD1/L3 | Algebra e decomposizione | Chiusa | Unicità dimostrata |
| Periodicità | UD1/L3 | Dominio invariante e periodi | Chiusa | Errore iniziale corretto |
| Trasformazioni | UD1/L3A | Formula generale e proprietà | Chiusa | Mappa dei punti verificata |
| Domini complessi | UD2/L1-L3 | Radici, potenze, log, inverse | Chiusa | Sistemi di condizioni |
| Funzioni speciali | UD2/L4A | Floor, indicatori, parti positive | Chiusa | Identità per casi |
| Famiglie elementari e parametri | UD1/L3A, UD2/L4A | Classificazione ed esempi | Chiusa | Esercizi parametrici |
| Equazioni funzionali | UD2/L4A | Introduzione e limiti delle ipotesi | Chiusa nel perimetro | Regolarità rinviata |
| Zeri e segno | UD3/L1, L3 | Composizioni e problema integrato | Chiusa | Esempi controllati |
| Bisezione | UD3/L2 | Ipotesi e stima corretta | Chiusa | 20 passi campionati |
| Esercizi e grafici | Tutto M07 | 105 esercizi e 21 placeholder | Chiusa | Conteggio automatico |

## 19. Gap analysis conclusiva

| Categoria | Stato | Residuo |
|---|---|---|
| Lacune matematiche pertinenti | Nessuna | Nessuno |
| Dimostrazioni obbligatorie | Complete | Prove analitiche correttamente rinviate |
| Esempi ed esercizi | Completi | Ulteriori varianti sono facoltative |
| Placeholder | Completi come specifica | Immagini da inserire manualmente |
| Confine modulare | Coerente | Nessuna duplicazione integrale |
| Contenuti avanzati | Rinviati | Limiti, continuità, derivate e topologia |

## 20. Valutazione finale

M07 costituisce ora un modulo autosufficiente sulle funzioni reali di una variabile reale: parte dall'oggetto insiemistico, sviluppa proprietà globali e famiglie elementari, controlla domini e immagini, costruisce inverse e trasformazioni e prepara i moduli analitici successivi. I rinvii riguardano soltanto risultati che richiedono realmente limiti, continuità, topologia o derivate.

La revisione di accettazione non rileva raccomandazioni dell'audit rimaste inattuate né lacune matematiche pertinenti. Gli approfondimenti possibili, come equazioni funzionali patologiche o supporto compatto, sono esterni al perimetro effettivo e non necessari alla progressione di Analisi 1.
