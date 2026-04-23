## **Lezione 1: Critical Rendering Path**

---

## **1. Introduzione**

In questa lezione inauguriamo una nuova sezione del corso, dedicata alle _performance_ del web. Mettiamo momentaneamente da parte il puro CSS per costruire una comprensione più ampia del funzionamento interno del browser. Utilizzeremo le conoscenze già acquisite in HTML e, passo dopo passo, vedremo come il CSS influisce sul tempo che intercorre tra il momento in cui un utente apre un sito e il momento in cui vede comparire qualcosa sullo schermo.

Completeremo il quadro quando studieremo JavaScript e, solo allora, potremo affrontare l’intero corso di ottimizzazione vera e propria. Per ora ci concentriamo su un concetto chiave: **il Critical Rendering Path**.

---

## **2. Cos’è il Critical Rendering Path**

### **2.1 Definizione intuitiva**

Il _Critical Rendering Path_ (CRP) è il percorso di operazioni che il browser deve eseguire per mostrare a schermo il primo contenuto visibile di una pagina web.

Detto in modo diretto:  
**È il tempo e la sequenza di passaggi che portano dalla richiesta di una pagina alla comparsa del primo pixel renderizzato.**

È “critical” perché questo percorso è essenziale: se non viene completato, non appare nulla. Tutto ciò che vediamo sullo schermo — testo, spazi, colori, layout — esiste solo perché il browser ha completato con successo questo percorso.

---

## **3. I passi fondamentali del Critical Rendering Path**

Il CRP è composto da una serie di passaggi che avvengono _sempre_, indipendentemente dal sito. Conoscerli significa capire dove si può intervenire per ottimizzare.

Procediamo dal primo all’ultimo step.

---

## **4. Download delle risorse**

### **4.1 Scaricamento di HTML e CSS**

Quando l’utente accede a un sito, il browser compie immediatamente due operazioni:

1. Scarica l’**HTML**.
    
2. Individua e scarica i **file CSS** collegati.
    

Queste sono le due risorse minime e indispensabili per poter iniziare qualunque forma di renderizzazione. Senza HTML non c’è struttura; senza CSS non c’è aspetto grafico (e il browser deve comunque processare i fogli di stile prima di poter procedere).

---

## **5. Costruzione del DOM**

### **5.1 Cos’è il DOM**

Una volta scaricato l’HTML, il browser costruisce il **Document Object Model (DOM)**.

Il DOM è una rappresentazione in memoria del documento HTML, tratta come una struttura ad albero composta da oggetti.  
Ogni tag — `html`, `body`, `div`, `p`, `h1` — diventa un nodo dell’albero.

Il browser:

- legge il codice HTML;
    
- lo interpreta;
    
- crea un modello del documento come se fosse fatto di _oggetti JavaScript_.
    

Il DOM serve per permettere successivi interventi da parte del CSS e soprattutto del futuro JavaScript.

---

## **6. Costruzione del CSSOM**

### **6.1 Cos’è il CSSOM**

Parallelamente alla creazione del DOM, il browser elabora anche il CSS. Tutte le regole — selettori, classi, ID, proprietà — vengono trasformate in un altro albero chiamato **CSS Object Model (CSSOM)**.

Il CSSOM è quindi l’equivalente del DOM, ma per lo stile:

- contiene tutte le regole di stile interpretate;
    
- definisce quali stili devono essere applicati agli elementi del DOM.
    

---

## **7. Costruzione del Render Tree**

### **7.1 L’unione di DOM e CSSOM**

Una volta pronti DOM e CSSOM, il browser crea il **Render Tree**.  
Questo albero contiene **solo gli elementi realmente visibili**, con le relative regole di stile associate.

Esempio:  
se un elemento HTML è `display: none`, compare nel DOM ma **non** nel Render Tree.

Il Render Tree è ciò che permette al browser di sapere:

- quali elementi vanno disegnati,
    
- con quali proprietà,
    
- con quale stile finale dopo aver risolto la cascata, l’ereditarietà e la specificità.
    

---

## **8. Fase di Layout**

### **8.1 Calcolo delle posizioni**

Una volta costruito il Render Tree, il browser deve stabilire la posizione effettiva di ogni elemento.

È la fase chiamata **layout** (o “reflow”).

Qui il browser determina:

- distanza dal bordo superiore,
    
- distanza dai lati,
    
- dimensioni finali,
    
- spaziature interne ed esterne,
    
- relazioni tra i vari blocchi.
    

In pratica:  
**come quando da bambini sistemavamo i cartoncini di un gioco da tavolo o i pezzi di un puzzle.**

Il layout stabilisce dove va tutto, prima che compaia a schermo.

---

## **9. Fase di Paint**

### **9.1 Il browser “dipinge”**

Ultimo step: il **paint**.

Il browser, come un pittore davanti a una tela bianca, applica colore, testo, immagini e layout calcolato.

Avviene il disegno fisico dei pixel:

- sfondi,
    
- testi,
    
- bordi,
    
- immagini,
    
- forme e decorazioni.
    

È in questo momento che la pagina _appare davvero_ all’utente.

---

## **10. Relazione con JavaScript**

### **10.1 Perché JavaScript può influire sul render**

JavaScript ha accesso sia al **DOM** sia al **CSSOM**.

Può quindi:

- aggiungere, rimuovere o modificare elementi del DOM;
    
- modificare regole CSS dinamicamente.
    

Esempi:

```javascript
document.querySelector("p").remove();
```

oppure

```javascript
document.querySelector("p").style.backgroundColor = "red";
```

Ogni modifica a DOM o CSSOM obbliga il browser a ricalcolare:

1. il Render Tree,
    
2. il layout,
    
3. eventualmente il paint.
    

Quindi JavaScript è un attore fondamentale per le performance, ma lo approfondiremo più avanti.

---

## **11. Riassunto del Critical Rendering Path**

Il CRP è l’intera sequenza:

1. **Download dell’HTML**
    
2. **Download del CSS**
    
3. **Creazione del DOM**
    
4. **Creazione del CSSOM**
    
5. **Costruzione del Render Tree**
    
6. **Calcolo del Layout**
    
7. **Paint dello schermo**
    

Quando tutto questo termina, appare il _first render_.

Quello che vediamo come un “istantaneo caricamento” è, in realtà, un processo tecnico complesso e fondamentale.

---

## **12. Conclusione**

Questa è una panoramica chiara e completa del **Critical Rendering Path**, senza addentrarci nei livelli avanzati che richiedono JavaScript. Ti servirà per capire il comportamento dei CSS, dei layout complessi e delle ottimizzazioni future.

Nella **Lezione 2** parleremo di uno degli argomenti più importanti della performance web:

## **Render Blocking**

Ci vediamo lì.