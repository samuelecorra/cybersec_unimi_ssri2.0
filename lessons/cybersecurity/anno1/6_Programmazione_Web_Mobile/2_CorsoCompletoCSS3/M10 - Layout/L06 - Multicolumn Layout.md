** Lezione 6: Multicolumn Layout**

In questo video affrontiamo il **multicolumn layout**, un tipo di layout pensato soprattutto per organizzare testo in più colonne, in modo simile ai giornali e alle riviste. È molto comodo quando vogliamo ottenere una distribuzione verticale naturale, soprattutto su testi lunghi, ma può essere applicato anche ad altri tipi di elementi (div, immagini, box). Non si limita quindi ai paragrafi: è un layout generale, anche se il suo utilizzo più comune rimane quello “da giornale”.

Per cominciare costruiamo una sezione che conterrà del testo. Inseriamo un `section` e all’interno aggiungiamo un paragrafo con un testo LOREM molto lungo. In questo modo possiamo vedere chiaramente come il multicolumn interviene sul flusso del contenuto.

Per trasformare questa sezione in un multicolumn layout è sufficiente applicare la regola `column-count`. Se scriviamo:

```
column-count: 3;
```

il testo viene automaticamente distribuito in tre colonne. Se allarghiamo la finestra, le colonne diventano più larghe; se stringiamo lo schermo, diventano più strette e più alte. È il comportamento naturale del layout.

Al posto di definire il numero delle colonne, possiamo definire la loro larghezza minima tramite `column-width`. Per esempio:

```
column-width: 100px;
```

Significa: “usa colonne larghe circa 100px e creane quante possibili nello spazio disponibile”. Se la larghezza della sezione non basta per più colonne, ne verrà creata una sola; se è più ampia, ne appariranno due, tre o più. Allo stesso modo, con valori molto alti (come 400px o 500px) il numero di colonne diminuirà, perché ne entreranno meno nello spazio del contenitore.

In sintesi:

- `column-count` determina quante colonne vogliamo.
    
- `column-width` determina quanto devono essere larghe.
    
- Le due proprietà sono collegate: una influenza l’altra.
    

Possiamo aggiungere spazio tra le colonne tramite `column-gap`.  
Esempio:

```
column-gap: 50px;
```

Aumentando il gap aumenta lo spazio vuoto tra una colonna e quella successiva.

Il multicolumn layout permette anche di impostare una linea di separazione tra le colonne, tramite le tre proprietà `column-rule-style`, `column-rule-width` e `column-rule-color`. Funzionano esattamente come i border:

- `column-rule-style: dotted;`
    
- `column-rule-width: 10px;`
    
- `column-rule-color: red;`
    

Possiamo condensarle in una sola proprietà abbreviata:

```
column-rule: dotted 10px orange;
```

Il risultato è una linea verticale tra le colonne, esattamente come i separatori che vediamo sui giornali.

C’è poi una proprietà importante: `column-span`.  
Serve quando un elemento interno alla sezione deve estendersi su tutte le colonne, “spannando” orizzontalmente. Il termine span ricorda infatti l’idea della “spanna”, cioè un tratto più lungo che copre più celle o più colonne. È lo stesso concetto di `colspan` nelle tabelle HTML.

Supponiamo di avere un titolo `h2` sopra il testo. Di default il titolo viene trattato come parte della prima colonna, perché è dentro la `section`. Se però vogliamo che il titolo compaia sopra tutte le colonne, dobbiamo applicare:

```
column-span: all;
```

In questo modo l’H2 occupa la larghezza completa della sezione, mentre il testo sotto rimane diviso in colonne.

Attenzione: `column-span` va applicato sull’elemento che deve “spannare” le colonne, non sulla section.

Esiste anche una proprietà chiamata `column` (senza trattino) che può definirsi con:

```
column: 2;
```

oppure:

```
column: 100px;
```

È una shorthand che combina `column-count` e `column-width`, ma può generare confusione perché non è chiaro a prima vista quale dei due valori stiamo controllando. Per questo, almeno all’inizio, è consigliabile usare sempre le regole separate (`column-count`, `column-width`) che sono molto più leggibili.

Il multicolumn layout non deve essere necessariamente applicato solo al testo. Possiamo tranquillamente riempire la sezione con altri elementi, per esempio vari div. Se creiamo:

```
<div class="item">1</div>
<div class="item">2</div>
<div class="item">3</div>
...
```

e poi applichiamo alla sezione:

```
column-count: 3;
```

gli elementi verranno distribuiti automaticamente in tre colonne, proprio come avviene per il testo.

Possiamo anche stilizzare gli item, per esempio con un bordo o con un colore di sfondo, e il multicolumn li distribuirà in verticale, lasciando che ogni colonna si riempia dall’alto verso il basso e poi inizi quella successiva.

Questo è molto comodo, perché permette di ottenere effetti simili a griglie verticali senza usare grid o flexbox. Anche se non è un layout adatto a tutti i casi, è molto potente in quei contesti in cui vogliamo generare una divisione naturale in colonne senza preoccuparci troppo del posizionamento degli elementi.

In sintesi, le proprietà fondamentali del multicolumn layout sono:

- `column-count`
    
- `column-width`
    
- `column-gap`
    
- `column-rule` (o le sue versioni separate)
    
- `column-span`
    

E il multicolumn, nonostante venga spesso associato solo al testo, può essere usato per qualsiasi tipo di contenuto interno.

Il prossimo video sarà dedicato all’esercitazione su questo layout, per consolidare tutto quanto visto finora.