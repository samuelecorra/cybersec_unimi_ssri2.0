# **Lezione 2: Che cos’è realmente una pagina HTML**

## **1. Dal sito al sorgente: due prospettive diverse**

Per comprendere che cosa sia una pagina HTML, partiamo da un’osservazione semplice: ciò che vediamo nel browser non coincide con ciò che _compone_ realmente la pagina. Se apriamo il sito sketch.io e selezioniamo **Visualizza sorgente pagina**, appare immediatamente evidente che l’interfaccia grafica, ordinata e coerente, è solo la rappresentazione visiva di un insieme di istruzioni testuali.

Il file mostrato nel sorgente contiene:

- immagini (ad esempio il logo del sito, inserito tramite tag specifici),
    
- codice HTML,
    
- riferimenti a fogli di stile (CSS),
    
- richiami a script JavaScript,
    
- e molto altro.
    

Questi elementi cooperano per produrre ciò che il browser traduce in un’esperienza visiva. Senza il browser, però, lo stesso contenuto apparirebbe come puro testo.

---
## **2. Una pagina HTML è un file: niente di più, niente di meno**

Quando salviamo il sorgente della pagina, vediamo che il file ha estensione .html.

Il principio fondamentale è questo:

> **Una pagina HTML è semplicemente un file di testo con estensione .html.**

Esattamente come:

- un file .txt è un semplice testo,
    
- un’immagine è .jpg o .png,
    
- un video è .mp4,
    
- un audio può essere .mp3.
    

L’HTML non è altro che un tipo di file. La magia accade perché il browser sa interpretarlo.

---

## **3. Il codice dietro la pagina: HTML, head e body**

Analizzando il sorgente di sketch.io, vediamo subito la struttura tipica:

- il tag `<html>` racchiude l’intera pagina,
    
- la sezione `<head>` contiene metadati, titoli, collegamenti ai fogli di stile,
    
- la sezione `<body>` contiene il contenuto visualizzato sullo schermo.
    

Dentro questi elementi ci sono centinaia (o migliaia) di righe che definiscono ogni dettaglio della pagina. Il browser legge questo codice e lo trasforma in un layout grafico.

Se però apriamo lo stesso file **non** con un browser, ma con un editor semplice come Blocco Note, il risultato è completamente diverso: il contenuto appare come puro codice, senza interpretazione.

Questo dimostra un concetto cruciale:

**Ciò che vediamo a schermo è il risultato dell’interpretazione del browser, non il file in sé.**

---
## **4. HTML, CSS e JavaScript: tre ruoli distinti**

All’interno del sorgente troviamo molto più del solo HTML:

- **HTML** definisce la struttura del contenuto (testi, titoli, paragrafi, immagini).
    
- **CSS** definisce l’aspetto grafico (colori, forme, margini, layout).
    
- **JavaScript** introduce comportamento e interattività (menu dinamici, animazioni, cambi di pagina).
    

In questa fase ci concentriamo solo sull’HTML; gli altri due linguaggi verranno affrontati più avanti.

---
## **5. Creare manualmente una pagina HTML**

Una pagina HTML può essere creata persino partendo da un semplice file di testo.

Ad esempio:

1. Creo un nuovo file .txt.
    
2. Scrivo del codice HTML al suo interno.
    
3. Lo salvo come homepage.html.
    

Questo file, se aperto con il browser, diventa una pagina web.

Un esempio minimale:

```
<html>
<head>
<title>Pagina Bellissima</title>
</head>
<body>
<h1>Ciao</h1>
</body>
</html>
```

Salvandolo come homepage.html e aprendolo con Chrome, la pagina mostra semplicemente “Ciao” in grande. Se modifichiamo il file nel Blocco Note e aggiorniamo la pagina nel browser, le modifiche appaiono immediatamente.

Ma in questo processo sorge un problema.

---
## **6. I limiti della modifica “artigianale” via file di testo**

Modificare HTML tramite un editor molto semplice (come Blocco Note) genera subito una serie di difficoltà:

- dobbiamo aprire manualmente il file ogni volta per modificarlo;
    
- rischiamo continuamente errori di sintassi (come dimenticare la chiusura di un tag);
    
- se il file cresce, diventa impossibile individuare l’esatta riga dell’errore;
    
- ogni aggiornamento richiede un salvataggio manuale + un nuovo caricamento della pagina;
    
- tutta la struttura appare disordinata e difficile da leggere.
    

Guardando il sorgente reale di un sito come sketch.io, anche se non è particolarmente complesso, è evidente quanto un file HTML vero sia troppo articolato per essere gestito con strumenti rudimentali. Risulta caotico, pieno di righe, riferimenti incrociati, codici annidati.

È normale chiedersi: **esiste un modo migliore per lavorare sul codice HTML?**

---
## **7. La risposta: utilizzare un editor professionale**

La soluzione è utilizzare strumenti progettati per programmare: **editor** o **IDE**.

- Gli **editor** sono programmi leggeri, nati per scrivere codice in maniera ordinata.
    
- Gli **IDE** (Integrated Development Environment) sono più complessi e integrano strumenti avanzati.
    

Per lavorare con HTML non serve un IDE in senso stretto: un editor moderno è più che sufficiente.

L’editor che useremo è **Visual Studio Code (VS Code)**.
È gratuito, leggero e pensato proprio per il lavoro sul codice.

Con VS Code possiamo:

- creare il file index.html,
    
- beneficiare dell’evidenziazione della sintassi,
    
- vedere l’indentazione corretta,
    
- essere avvisati in caso di errori,
    
- espandere automaticamente i tag,
    
- gestire molti file insieme in un’unica interfaccia.
    

Nelle prossime lezioni vedremo come installarlo, configurarlo e integrarlo nel nostro flusso di lavoro.

---
## **8. Conclusione: cosa abbiamo davvero imparato**

In questa lezione abbiamo visto che:

- Una pagina HTML è semplicemente un file .html.
    
- Il browser interpreta questo file e lo trasforma in una pagina visiva.
    
- Il contenuto reale è codice testuale.
    
- Possiamo creare una pagina HTML anche partendo da un semplice file .txt.
    
- Tuttavia, lavorare così diventa rapidamente inefficiente e pieno di errori.
    
- Per questo si utilizzano editor professionali come VS Code.
    

Nel prossimo episodio completeremo il quadro affrontando una domanda fondamentale:

> **Che cos’è, nel dettaglio, un sito web?**
  
Proseguiremo quindi nella costruzione graduale del modello mentale che ti permetterà di capire HTML, CSS, JavaScript e tutto lo stack front-end in modo chiaro e definitivo.