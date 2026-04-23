## **Lezione 13 – Developer Tools di Chrome per HTML e CSS**

### **1. Introduzione: perché usare la console developer**

In questa lezione arriviamo all’ultimo video di spiegazione del **primo modulo sui fondamentali**, dedicandoci a uno strumento che userai continuamente lavorando con HTML e CSS:  
la **console developer del browser**, in particolare quella di **Chrome**.

Strumenti simili esistono anche su **Firefox** e **Edge**, quindi non sei vincolato a Chrome.  
I concetti cambiano pochissimo: l’interfaccia è diversa, ma l’idea è la stessa.

In questa lezione il prof usa **Chrome** e ti mostra:

- come **aprire** la console
    
- quali **pannelli** sono importanti per HTML/CSS
    
- come **modificare al volo** HTML e CSS direttamente dal browser
    
- come leggere gli **stili applicati** e la **specificità**
    
- come visualizzare il **box model** (margini, bordi, padding, contenuto)
    
- come leggere lo **stile finale (computed)**
    

---

### **2. Come aprire la console developer**

Per aprire i Developer Tools di Chrome hai due modi principali:

- **Tasto destro → “Ispeziona”**
    
- Oppure scorciatoia **F12**
    

Appena li apri, ti trovi davanti un pannello molto grande, pieno di schede e informazioni.  
Non useremo tutto subito: ci concentreremo sulle parti utili per **HTML e CSS**.

---

### **3. Panoramica delle schede principali**

Quando apri i DevTools, in alto trovi diverse tab. Le più importanti che il prof cita sono:

#### **3.1. Console**

È la scheda dove appare il log JavaScript e dove puoi eseguire codice JS.  
Per ora non ci interessa, perché non stiamo ancora usando Javascript.

#### **3.2. Network (Rete)**

Questa scheda mostra:

- tutti i file che il browser scarica dal server
    
- richieste e risposte (HTML, CSS, JS, immagini, ecc.)
    

È la prova che **la pagina viene scaricata dal server**: il browser riceve i file e poi li visualizza.  
Questa informazione sarà utile quando parleremo di modifiche “solo front end”.

#### **3.3. Application**

Qui troverai:

- **cookie**
    
- **sessioni**
    
- **localStorage / sessionStorage**
    
- altre informazioni legate allo stato dell’applicazione nel browser
    

In futuro, con JavaScript, useremo questa sezione per vedere e manipolare questi dati lato client.  
Da qui, ad esempio, puoi anche **cancellare i cookie**.

#### **3.4. Lighthouse**

È lo strumento per analizzare:

- **SEO**
    
- **Performance**
    
- **Accessibilità**
    
- **Best practices**
    

Lo hai già incrociato parlando di HTML.  
Ti permette di generare un report sulla qualità del sito.

#### **3.5. Elements (la sezione che ci interessa ora)**

Questa è la scheda più importante per noi in questo momento.  
Qui puoi:

- vedere **la struttura HTML** della pagina
    
- vedere e modificare gli **stili CSS** applicati a ogni elemento
    

È il cuore del lavoro front end quando devi capire “perché questo elemento appare così?”

---

### **4. Scheda Elements: esplorare l’HTML della pagina**

Nella scheda **Elements** vedi l’intero albero della pagina (il DOM).

- Se passi col mouse su un elemento nell’albero, Chrome **evidenzia** il corrispondente elemento nella pagina.
    
- Bastano pochi secondi per capire che elemento stai guardando.
    

Su ogni elemento HTML, col tasto destro puoi:

- **aggiungere un attributo**
    
- **modificare l’HTML** (`Edit as HTML`)
    
- **duplicare l’elemento**
    
- **eliminarlo** dalla pagina
    
- **nasconderlo** (hide)
    
- **forzare uno stato** (hover, active, focus, ecc.) – utile soprattutto per i link e i pulsanti
    

Esempio: puoi modificare il testo di un paragrafo direttamente da lì, oppure cambiare un attributo come `disabled`, aggiungerlo, toglierlo e vedere subito l’effetto sulla pagina.

Ricorda:  
tutte queste modifiche avvengono **solo nel browser**, **non sul server**.

---

### **5. Modifiche front end: cosa possiamo e cosa non possiamo fare**

È fondamentale capire il limite:

- Dal browser puoi modificare **il codice HTML e CSS che hai scaricato**.
    
- Non puoi toccare ciò che è **lato server** (database, logica lato backend, dati reali).
    

Infatti:

- Se modifichi il testo di un paragrafo, ad esempio “Bellissimo paragrafo” in “Bruttissimo paragrafo” o quello che vuoi, la modifica è **solo locale**.
    
- Nel momento in cui **aggiorni la pagina (F5)**, tutto torna come prima, perché il server rimanda la versione originale.
    

Il prof fa l’esempio degli studenti che modificano il **voto sul registro online** davanti ai genitori:

- Aprono i DevTools
    
- Cambiano il numero del voto in “9”
    
- I genitori, se non sanno di DevTools, potrebbero crederci
    
- Ma appena aggiornano la pagina… il browser ricarica la versione reale dal server, e la “finta” sparisce
    

Conclusione:  
puoi **giochicchiare** con l’HTML e far vedere “finte” modifiche, ma **non stai cambiando i dati reali**.

È tutto **front end**, temporaneo.

---

### **6. La sezione degli stili: leggere e modificare il CSS di un elemento**

La parte più importante, sotto la scheda Elements, è il pannello **Styles** (Stili).

Quando selezioni un elemento (ad esempio un `h1`), in questo pannello vedi:

- tutte le **regole CSS** che lo riguardano
    
- in che ordine vengono applicate
    
- quali sono **attive** e quali **sovrascritte**
    

Esempio concreto:

- vedi una regola `element.style` con `color: pink`
    
    - questo indica uno **stile inline**: lo vedi anche sull’elemento `<h1 style="color: pink">`
        
- sotto, trovi `.titolone { ... }`
    
- ancora sotto, `h1 { ... }`
    
- infine, il **foglio di stile dello User Agent** (il CSS di default del browser)
    

Ciò che puoi fare:

- **attivare/disattivare** una dichiarazione (spuntando la casella a sinistra)
    
- **modificare i valori** (es. cambiare `pink` in `orange`)
    
- **aggiungere nuove proprietà** (scrivi il nome, Chrome ti suggerisce i completamenti)
    
- **cancellare temporaneamente** una regola per vedere cosa succede
    

Le regole dello **User Agent stylesheet** sono il CSS di base del browser:  
non le puoi rimuovere, **le puoi solo sovrascrivere** con le tue.

---

### **7. Color picker, formati e contrasto**

Quando lavori con proprietà come `color` o `background-color`, puoi cliccare sul quadratino del colore per aprire il **color picker**.

Da lì puoi:

- scegliere un colore manualmente
    
- usare il **contagocce** per selezionare un colore da qualsiasi punto della pagina
    
- cambiare il formato del colore:
    
    - esadecimale (`#ffffff`)
        
    - `rgb(...)`
        
    - altri formati supportati
        

Molto importante: il pannello mostra anche il **rapporto di contrasto** tra testo e sfondo, utile per l’**accessibilità**.

Se il contrasto è troppo basso (es. testo grigio chiaro su sfondo bianco), il tool ti segnala che **non va bene**, indicandoti un rapporto di contrasto non sufficiente.

Questo è fondamentale per non creare pagine illeggibili per molti utenti.

---

### **8. Il box model: margine, bordo, padding, contenuto**

Sotto gli stili, trovi la rappresentazione grafica del **box model** del CSS.

Il browser mostra un “rettangolo concentrico” con vari colori:

- **Arancione** → margine (`margin`)
    
- **Giallo** → bordo (`border`)
    
- **Verde** → padding (`padding`)
    
- **Blu** → contenuto (`content`)
    

Esempio:

Se aggiungi via Styles:

```css
padding: 30px;
border: 10px solid black;
```

e poi passi il mouse sul box model:

- vedi la parte arancione (margini)
    
- la striscia gialla (bordo)
    
- l’area verde (padding)
    
- il rettangolo blu (contenuto effettivo)
    

Sotto, Chrome mostra anche le **dimensioni** effettive del box:

- larghezza, altezza
    
- margini, padding, bordi
    

Più avanti, quando studierai il **box model** in dettaglio, questa sezione diventerà essenziale per capire perché un elemento “occupa” più spazio di quanto ti aspettavi.

---

### **9. Forzare stati e aggiungere classi al volo**

Nella parte alta del pannello degli stili puoi:

- **forzare lo stato** di un elemento (ad esempio `:hover`, `:active`, `:focus`)
    
- **aggiungere una classe** temporanea
    

Esempio:

- forzi `:hover` su un link per vedere come appaiono gli stili quando il mouse è sopra, senza dover muovere fisicamente il mouse
    
- aggiungi una classe `prova` al tuo `h1`, così:
    
    - l’elemento diventa, ad esempio, `class="Edoardo prova"`
        
    - puoi testare regole CSS per `.prova` senza modificare l’HTML alla fonte
        

Questo è utilissimo per testare velocemente nuove idee di stile prima di scriverle nel tuo file CSS.

---

### **10. Scheda “Computed” / “Elaborati”: lo stile finale dell’elemento**

Insieme a “Stili” c’è anche la scheda:

- **Computed** (in inglese)
    
- **Elaborati** (se imposti Chrome in italiano)
    

Qui non vedi più tutte le regole sorgenti, ma solo **il risultato finale**:

- il **colore effettivo** del testo (es. `color: pink`)
    
- lo **stile finale del bordo**
    
- il **display effettivo** (`block`, `flex`, ecc.)
    
- i valori finali di margin, padding, font-size, ecc.
    

In altre parole:

- la scheda **Styles** ti mostra **da dove arrivano** i vari stili (file, riga, regola, sovrascritture)
    
- la scheda **Computed / Elaborati** ti mostra **che cosa vince alla fine**
    

Se ti sposti su un altro elemento, la scheda si aggiorna e mostra il CSS finale per quell’elemento.

---

### **11. Usare la console developer nella pratica quotidiana**

Nel lavoro reale, la console developer diventa spesso più usata dell’editor per i test rapidi.

Workflow tipico:

1. **Apri la console**
    
2. Selezioni l’elemento che ti interessa
    
3. Provi:
    
    - un altro colore
        
    - un nuovo `padding`
        
    - un `display: flex`
        
    - un margine diverso, ecc.
        
4. Quando trovi una combinazione che ti piace, **copi** la regola e la riporti nel tuo file CSS (es. in Visual Studio Code).
    

Vantaggi:

- vedi **subito** l’effetto, senza salvare e ricaricare la pagina ogni volta
    
- hai **autocompletamento**, suggerimenti e strumenti visuali (color picker, box model, pannelli `flex` avanzati, ecc.)
    
- puoi capire velocemente **perché uno stile sta vincendo** rispetto a un altro (grazie a specificità, cascata, ecc.)
    

Il prof sottolinea che, con l’andare del tempo, userai la console **tantissimo**, spesso più dell’editor, per fare tutte le prove a caldo.

---

### **12. Conclusione: un tool essenziale per HTML e CSS**

Ricapitolando, i Developer Tools (in particolare la scheda **Elements** e i pannelli **Styles** e **Computed/Elaborati**) sono:

- fondamentali per capire **cosa sta succedendo davvero** ai tuoi elementi
    
- perfetti per **debuggare** problemi di CSS, specificità, cascade, ereditarietà
    
- indispensabili per testare rapidamente modifiche di layout, colori, margini, padding, ecc.
    

Questi strumenti esistono anche su **Edge**, **Firefox** e altri browser.  
Non sono un optional: sono una **parte integrante** del lavoro di chi sviluppa interfacce web.

Nel **prossimo video**, si passa finalmente al **primo esercizio pratico** di questo corso, dove metterai insieme:

- HTML
    
- CSS
    
- e l’uso della console developer
    

per iniziare a lavorare davvero “come uno sviluppatore front end”.