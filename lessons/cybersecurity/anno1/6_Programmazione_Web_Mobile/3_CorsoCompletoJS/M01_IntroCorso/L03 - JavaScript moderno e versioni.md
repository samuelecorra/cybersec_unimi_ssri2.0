## **Lezione 3: JavaScript moderno e versioni**

### **1. Perché dobbiamo parlare di versioni**

Chiudiamo l’introduzione al corso con un tema che _deve_ essere citato fin da subito, anche se lo affronteremo bene più avanti:  
**le versioni di JavaScript** e ciò che chiamiamo **JavaScript moderno**.

Se cerchi su Google `javascript versions`, vedrai subito un mare di sigle:

- **ECMAScript**
    
- **ES5, ES6, ES2015, ES2016, ES2017…**
    
- anni tipo **2009, 2015, 2016, 2017…**
    

e il cervello, legittimamente, rischia di esplodere.

Quello che vogliamo fare ora è:

- capire **a grandi linee** che cosa stiamo guardando,
    
- sapere che esistono **versioni diverse**,
    
- prepararci alla sezione dedicata a **JavaScript moderno**, che verrà più avanti.
    

Non entriamo ancora nei dettagli tecnici, ma piantiamo i paletti concettuali.

---

### **2. ECMAScript, JavaScript, ES2015… che confusione è?**

Se apri un sito tipo W3Schools o simili, troverai una tabella con:

- colonne tipo **ECMAScript 5, ECMAScript 6**, ecc.
    
- sigle tipo **ES5, ES2015, ES2016…**
    

E la domanda spontanea è: **“Ma dov’è JavaScript?”**

Il punto è questo:

- **ECMAScript** è lo **standard** ufficiale.
    
- **JavaScript** è il **linguaggio** implementato nei browser, _basato_ su quello standard.
    
- Le sigle tipo **ES2015** indicano le _versioni_ dello standard ECMAScript.
    

Di fatto, quando vedi:

- **ES5** → stai parlando di una “versione di JavaScript” vecchia
    
- **ES2015 (ES6)** → la versione che ha introdotto il cosiddetto **JavaScript moderno**
    
- ES2016, ES2017, … → tutte le versioni successive che hanno aggiunto altra roba.
    

Quindi:  
**quelle tabelle di versioni che vedi online sono le “versioni di JavaScript”**, anche se a livello formale si chiamano ECMAScript.

---

### **3. Il grande spartiacque: prima del 2009 e dal 2015 in poi**

Nella storia di JavaScript c’è un buco enorme:

- **2009**: ultima versione “vecchia” importante.
    
- Poi **silenzio per 6 anni**.
    
- **2015**: arrivo di **ES2015 (ES6)** → considerato l’inizio del **JavaScript moderno**.
    

Dal **2015 in poi**:

- è cambiato praticamente **tutto** nel modo di scrivere JavaScript;
    
- ogni anno sono uscite nuove versioni con nuove funzionalità;
    
- si è passati da un linguaggio “limitato ma diffuso” a uno strumento **potentissimo** e moderno.
    

Per questo, oggi si tende a dividere grossolanamente la storia in due blocchi:

1. **JavaScript “pre-2015”**
    
2. **JavaScript moderno (dal 2015 in poi)**
    

---

### **4. Che cosa ha portato JavaScript moderno**

A partire da ES2015, sono arrivate _tantissime_ cose nuove. Alcuni esempi importanti:

- **`let`** e **`const`** (nuovi modi di dichiarare variabili)
    
- **classi** (sintassi più chiara per la programmazione a oggetti)
    
- nuovi **metodi sugli array** (tipo `map`, `filter`, `reduce`, ecc.)
    
- nuovi **metodi sugli oggetti**
    
- una gestione **decente e potente dell’asincronia** (Promesse, `async/await`)
    
- altre funzionalità che non sono nemmeno elencate del tutto nei siti base.
    

Queste cose:

- **non cancellano** il JavaScript vecchio (altrimenti si romperebbe il web),
    
- ma **si aggiungono** sopra.
    

Risultato:  
oggi puoi ancora scrivere JavaScript “vecchio stile”, ma se **usi i costrutti moderni** la tua vita di sviluppatore diventa immensamente più facile.

---

### **5. È ancora “moderno”? Dipende dal punto di vista**

Dal **punto di vista storico**:

- nel 2015 si è cominciato a parlare di **JavaScript moderno**;
    
- negli anni immediatamente successivi aveva senso dire “questo è il modo _nuovo_ di scrivere JS”.
    

Dal **punto di vista di chi impara oggi**:

- sono passati **8–9 anni**,
    
- **ES2015 e successivi sono ormai la norma**.
    

Quindi per te:

- ciò che viene chiamato “JavaScript moderno” è, di fatto, **JavaScript normale**.
    
- È il modo in cui **va scritto JavaScript oggi**.
    

Nel corso useremo questa terminologia:

- a volte lo chiameremo **JavaScript moderno**, per marcare la differenza storica;
    
- ma, di fatto, lo tratteremo come **standard attuale**.
    

---

### **6. Come useremo JavaScript nel corso**

In pratica, nel corso faremo così:

- useremo **fin da subito** i costrutti moderni:
    
    - **`let`** e **`const`** invece di `var`;
        
    - sintassi e pattern moderni ovunque abbia senso;
        
- tratteremo certe cose vecchie **solo per farvele riconoscere** (per leggere codice legacy o roba generata male).
    

Per esempio:

- quando parleremo di **variabili**, vedremo:
    
    - che cos’era `var`,
        
    - perché oggi **non dovrebbe più essere usata**,
        
    - e perché preferiamo **`let`** e **`const`**.
        

Spesso vedrai ancora `var`:

- in vecchi tutorial,
    
- in codice di progetti datati,
    
- persino in qualche risposta generata da strumenti come ChatGPT.
    

Per questo è importante:

- sapere **che cosa vuol dire**,
    
- ma anche sapere **che oggi non è più la scelta giusta**.
    

In generale:

- molte cose vecchie **non le vedremo nemmeno** se non sono più necessarie;
    
- useremo direttamente lo stile **moderno**, quello che usano gli sviluppatori reali nel 202X.
    

---

### **7. Una sezione dedicata a JavaScript moderno**

Anche se useremo subito JS moderno, avremo **una sezione dedicata** in cui:

- parleremo con calma delle **versioni**:
    
    - che cos’è ECMAScript,
        
    - come leggere **ES2015, ES2016, ES2017, …**,
        
    - come capire che cosa è stato introdotto **in quale anno**.
        
- vedremo una **carrellata di funzionalità moderne** arrivate dal 2015 in poi, ad esempio:
    
    - **destructuring** (oggetti e array),
        
    - **spread operator** (`...`),
        
    - **rest parameters**,
        
    - pattern più avanzati sugli oggetti e sugli array,
        
    - altre funzioni e costrutti “nuovi”.
        

Alcune di queste:

- **non fanno parte dei “fondamenti” classici**,
    
- ma sono usatissime **tutti i giorni** da milioni di sviluppatori.
    

Quindi la strategia sarà:

- nei **fondamenti**, impari il cuore del linguaggio;
    
- nella sezione **JavaScript moderno**, ti faccio vedere:
    
    - “Ecco tutta la roba nuova che completa il quadro”,
        
    - “Ecco che cosa dovresti cominciare a usare per scrivere codice davvero moderno”.
        

---

### **8. JavaScript non si ferma mai: nuove feature ogni anno**

Se apri una pagina tipo Wikipedia sulle versioni di ECMAScript, noterai che:

- **ogni anno** esce una nuova versione,
    
- ogni versione porta **nuove funzionalità**,
    
- negli ultimi anni il ritmo è diventato **molto veloce**.
    

Questo significa che:

- non basta imparare una volta per tutte,
    
- devi essere disposto a **restare aggiornato**.
    

L’obiettivo del corso non è solo darti un elenco di funzioni, ma:

- insegnarti a **capire cosa stai leggendo**,
    
- riconoscere **se una funzione appartiene a JS “vecchio” o moderno**,
    
- leggerti da solo la documentazione delle versioni future.
    

---

### **9. Che cosa sappiamo ora (riassunto finale dell’introduzione)**

Dopo questi video introduttivi, hai in mano alcune idee fondamentali:

1. **JavaScript è la terza gamba del web**
    
    - HTML → contenuto e struttura
        
    - CSS → stile
        
    - JavaScript → **dinamicità e funzionalità**
        
2. **JavaScript dà vita al sito**
    
    - interazioni,
        
    - eventi,
        
    - animazioni complesse,
        
    - filtri, ricerche, pulsanti “torna su”, editor di testo, Pokedex interattivi, ecc.
        
3. **L’ecosistema JavaScript è gigantesco**
    
    - framework frontend (React, Angular, Vue, …),
        
    - strumenti desktop (Electron),
        
    - mobile (React Native),
        
    - tool di sviluppo (Prettier, ESLint, Babel, TypeScript…),
        
    - e ogni anno spunta qualcosa di nuovo.
        
4. **Con JavaScript puoi fare praticamente tutto**
    
    - **web** (siti, SPA, dashboard),
        
    - **desktop** (VS Code, Discord, Figma, Postman…),
        
    - **mobile** (app iOS/Android con React Native),
        
    - **server** (API e backend con Node.js, Deno, ecc.).
        
5. **JavaScript ha una storia di versioni importante**
    
    - fino al **2009**: ultima versione pre-moderna,
        
    - dal **2015** in poi: **JavaScript moderno (ES2015+)**,
        
    - le vecchie feature esistono ancora per compatibilità,
        
    - le nuove feature ti semplificano la vita se le impari.
        
6. **Nel corso useremo già JavaScript moderno**
    
    - useremo **`let`/`const`**, non `var`,
        
    - useremo pattern moderni dove ha senso,
        
    - avremo una **sezione dedicata** per:
        
        - capire le versioni,
            
        - vedere una lista ragionata di funzionalità moderne.
            
7. **Obiettivo vero: darti una bussola, non solo un manuale di sintassi**
    
    - non basta “saper programmare” un po’,
        
    - voglio che tu sappia **navigare l’ecosistema**,
        
    - capire dove si inserisce un nuovo framework o strumento,
        
    - restare in piedi anche quando escono cose nuove senza avere sempre qualcuno per mano.
        

In CSS abbiamo fatto la stessa cosa: non solo proprietà e selettori, ma anche ecosistema (framework, preprocessori, ecc.).  
Con HTML non ce n’era bisogno, perché è molto più semplice.  
Con JavaScript, invece, è **obbligatorio**.

---

### **10. Chiusura dell’introduzione e cosa succede adesso**

Questa introduzione, messa tutta insieme, probabilmente durerà intorno alla mezz’ora e ti avrò “riempito le orecchie” di concetti, nomi e visione d’insieme.

Ma era fondamentale, perché:

- ora **non entri nel corso pensando solo “devo imparare a programmare”**,
    
- entri con l’ottica:
    
    > “Voglio diventare un professionista di JavaScript,  
    > capire come si muove questo mondo e saperci stare dentro.”
    

Da qui in avanti:

- comincia la sezione sui **fondamenti di JavaScript**,
    
- ci saranno **tanti video**,
    
- concetti **semplici ma numerosi**,
    
- **molti esercizi**.
    

Adesso abbiamo finito il prologo.  
Possiamo finalmente passare alla prossima sezione e iniziare a costruire, pezzo per pezzo, il tuo **JavaScript “di base”**, già scritto nello stile **moderno**.