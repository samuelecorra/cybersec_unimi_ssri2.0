# **Lezione 4: Come funzionano i motori di ricerca e perché riguardano l’HTML**

## **1. Perché parlare di motori di ricerca in un corso HTML**

Una domanda legittima potrebbe essere: _«Stiamo studiando HTML… perché dovremmo approfondire i motori di ricerca?»_  
La risposta è semplice: HTML e motori di ricerca sono strettamente collegati. Il modo in cui scriviamo il codice influenza direttamente la capacità dei motori di ricerca di interpretare, comprendere e classificare le nostre pagine.

Per capirlo, partiamo da un esempio concreto.

Quando cerchiamo “draw online”, Google ci restituisce un elenco di siti. Cliccando sul risultato relativo a `sketch.io`, il browser invia una richiesta alla pagina `sketch.io/sketchpad`. Ma ciò che riceviamo non è altro che una **pagina HTML**, accompagnata da altri file.

Il motore di ricerca stesso — Google, Bing o altri — è esso stesso una pagina HTML che mostra una serie di collegamenti ad altre pagine HTML.

---

## **2. I motori di ricerca “leggono” le pagine come algoritmi, non come persone**

Google possiede software autonomi, chiamati solitamente _crawler_, _bot_ o _spider_, che visitano i siti esattamente come facciamo noi. Seguono i link da una pagina all’altra, attraversano menu, sottopagine e strutture interne.

Con una differenza fondamentale:

> I crawler non “vedono” la pagina. Non hanno bisogno del layout grafico. Leggono esclusivamente il **codice HTML**.

Non eseguono interazioni visive: analizzano struttura, elementi, contenuti testuali, titoli, metadati e collegamenti.

Questo significa che:

- tutto ciò che è scritto **in modo scorretto**,
    
- disordinato,
    
- poco chiaro,
    
- o non accessibile,
    

diventa immediatamente un problema per loro.

---

## **3. La valutazione delle pagine: punteggi, fattori e qualità del codice**

Ogni pagina visitata dai crawler riceve una valutazione composta da numerosi fattori, tra cui:

- qualità del contenuto,
    
- struttura interna,
    
- presenza di elementi semantici corretti,
    
- velocità del sito,
    
- qualità del codice HTML,
    
- accessibilità,
    
- leggibilità anche da parte degli screen reader,
    
- pertinenza rispetto alla ricerca dell’utente,
    
- e molti altri.
    

Il motore di ricerca deve decidere **quali risultati sono migliori** per chi ha effettuato la ricerca. E per farlo utilizza questa valutazione.

Pertanto:

> Il codice HTML influisce direttamente sul posizionamento di un sito.

Se il codice è scritto male, disordinato o con elementi sbagliati, Google se ne accorge.  
Se la pagina è lenta o poco accessibile, Google se ne accorge.  
Se la struttura non segue logica né gerarchie chiare, Google se ne accorge.

HTML è la base su cui tutto si regge: è il primo mattoncino.

---

## **4. L’accessibilità: un fattore chiave nella SEO moderna**

Negli ultimi anni l’accessibilità è diventata un elemento centrale nella valutazione delle pagine.  
Il motivo è duplice:

1. **Inclusione degli utenti con disabilità**, ad esempio non vedenti o ipovedenti.
    
2. **I motori di ricerca premiano i siti accessibili.**
    

Un esempio concreto: gli screen reader sono programmi che leggono a voce il contenuto della pagina HTML.  
Per funzionare, hanno bisogno di:

- titoli ben strutturati,
    
- elementi semanticamente corretti,
    
- testo alternativo nelle immagini,
    
- link comprensibili,
    
- sezioni ordinate.
    

Lo screen reader analizza **esattamente il codice HTML**, non il layout visivo.  
Se la pagina è scritta male, l’utente non vedente non riesce a navigarla… e Google registra questo come segnale negativo.

L’accessibilità non è più un’opzione.  
È un criterio valutativo.

---

## **5. HTML scritto “bene” è HTML utile agli utenti e ai motori di ricerca**

Scrivere HTML corretto significa:

- utilizzare gli elementi giusti per lo scopo giusto (titoli, paragrafi, liste, link),
    
- rispettare la gerarchia degli heading,
    
- strutturare il contenuto in sezioni coerenti,
    
- evitare pratiche obsolete,
    
- garantire leggibilità e ordine del codice,
    
- includere informazioni necessarie per gli screen reader,
    
- rendere la pagina chiara sia agli utenti che ai crawler.
    

Il docente sottolinea che il corso non insegnerà “HTML per fare un sito qualunque”, ma **HTML fatto come si deve**, cioè:

- accessibile,
    
- pulito,
    
- semantico,
    
- SEO-friendly.
    

Molti siti anche di grandi aziende non rispettano questi criteri: sono “belli da vedere”, ma pessimi da leggere dal punto di vista del codice. E questo incide sul posizionamento.

---

## **6. Il posizionamento nei risultati di ricerca**

Se due aziende competono per la stessa parola chiave — ad esempio “padelle” — l’azienda con la pagina:

- più veloce,
    
- meglio strutturata,
    
- più accessibile,
    
- meglio scritta in HTML,
    

ha un vantaggio molto significativo.

Il posizionamento influisce direttamente:

- sui click che il sito riceve,
    
- sul traffico complessivo,
    
- sulle possibilità di vendita.
    

Essere in prima posizione significa esporsi a un volume di visite immensamente superiore rispetto alla seconda pagina, che quasi nessuno apre.

> HTML non determina da solo il posizionamento, ma è uno dei mattoni fondamentali del processo.

---

## **7. Riepilogo del funzionamento dei motori di ricerca**

1. Il motore di ricerca visita le pagine del web tramite algoritmi automatici.
    
2. Analizza il codice HTML e la struttura del sito.
    
3. Valuta accessibilità, qualità del contenuto e leggibilità.
    
4. Assegna un punteggio a ogni pagina.
    
5. Quando un utente cerca qualcosa, restituisce i risultati in ordine di relevanza e qualità.
    
6. L’HTML, se scritto bene, contribuisce in modo significativo a posizionare un sito più in alto.
    

---

## **8. Direzione della prossima lezione**

HTML è uno dei tanti “mattoncini” che determinano la complessità del web.  
Noi, in questo corso, lavoreremo proprio su quel mattoncino, imparando a scriverlo:

- correttamente,
    
- in modo accessibile,
    
- in modo comprensibile ai motori di ricerca,
    
- e in coerenza con gli standard moderni.
    

Nella prossima lezione inizieremo finalmente a utilizzare **Visual Studio Code**, il programma che ci accompagnerà.