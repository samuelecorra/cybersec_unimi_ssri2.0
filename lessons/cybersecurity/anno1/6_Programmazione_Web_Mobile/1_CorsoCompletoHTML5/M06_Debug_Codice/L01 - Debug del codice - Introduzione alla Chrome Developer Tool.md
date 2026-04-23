## **Lezione: Debug del codice — Introduzione alla Chrome Developer Tool**

Questa è la prima delle tre lezioni dedicate al debug del codice.  
È un modulo brevissimo, ma FONDAMENTALE: da qui in avanti la Developer Tool diventerà il tuo **microscopio** quando lavori con HTML, CSS e JavaScript.

---

# **1. Cos’è la Developer Tool**

È l’insieme di strumenti che apri con:

- **F12**
    
- **tasto destro → Ispeziona**
    

E non è esclusiva di Chrome: anche Firefox, Edge e Safari hanno strumenti molto simili.  
Tu vedi Chrome nei video solo perché è comodo, stabile, e ha la migliore UX per chi inizia.

---

# **2. La tab “Elements”: il cuore dell’ispezione**

La prima scheda è **Elements**, e mostra:

- tutto l’HTML della pagina (head + body),
    
- la struttura esatta renderizzata dal browser,
    
- i nodi DOM su cui puoi cliccare,
    
- lo stile CSS applicato a ogni elemento (anche se non lo hai ancora studiato).
    

Qui puoi:

- **modificare il testo direttamente** (doppio click),
    
- **modificare attributi** (tasto destro → Aggiungi attributo),
    
- **trascinare elementi** per cambiare il loro ordine,
    
- **vedere cosa il browser ha effettivamente ricevuto**.
    

Questa scheda diventa cruciale perché:

> La Developer Tool mostra la pagina _renderizzata_, non il file originale.

Se nel tuo file HTML pensi di aver scritto X, ma qui vedi Y, allora sai che **qualcosa non è stato caricato correttamente**.

---

# **3. La scheda “Styles”**

Sotto elements trovi lo stile applicato a ciascun nodo HTML.  
La userai massicciamente nel modulo CSS.

Per ora ti basta sapere:

- puoi attivare/disattivare regole CSS con un click,
    
- puoi aggiungere nuove regole live,
    
- puoi vedere la cascata, le priorità, e cosa sta sovrascrivendo cosa.
    

---

# **4. La Console**

La scheda **Console** è il terminale del browser.

Serve a:

- mostrare errori del codice (es: risorsa mancante),
    
- eseguire JavaScript temporaneo,
    
- vedere messaggi di log,
    
- controllare stati e variabili durante il debug JS.
    

Nell’esempio che citi:

> errore: icona.webp non trovata

Probabilmente hai spostato la cartella e la pagina sta ancora cercando il vecchio percorso.

La Console è il tuo primo campanello d’allarme.

---

# **5. La scheda “Sources”**

Qui trovi tutti i file che compongono quella pagina:

- l’HTML,
    
- le immagini,
    
- i CSS collegati,
    
- eventuali file JavaScript.
    

Puoi:

- aprire le cartelle,
    
- vedere percorsi reali,
    
- aprire le immagini cliccandoci sopra,
    
- leggere il contenuto dei file come in un mini-editor.
    

---

# **6. La scheda “Network”**

È la radiografia del **caricamento della pagina**.

Mostra:

- quali file vengono scaricati,
    
- in che ordine,
    
- quanto pesano,
    
- quanto tempo impiega ogni risorsa,
    
- quali chiamate falliscono,
    
- se c’è caching.
    

È fondamentale per:

- capire se un’immagine non si carica,
    
- capire perché un sito è lento,
    
- controllare file mancanti o percorsi sbagliati.
    

---

# **7. La scheda “Application”**

Questa è potentissima, perché ti mostra il “dietro le quinte” del browser:

### Salvataggi locali

- **Local Storage**
    
- **Session Storage**
    
- **Cookies**
    

I cookie che vedi lì sono gli stessi che “si cancellano” quando fai:

> Cancella cookie del sito

Per esempio:

- La dark mode di YouTube è salvata **nei cookie del browser**, non sul tuo account.
    
- Se cambi browser → perdi tutto.
    
- Se cancelli i cookie → devi riselezionare la modalità scura.
    

Qui puoi:

- leggere ogni cookie,
    
- modificarlo,
    
- eliminarlo,
    
- vedere la loro scadenza.
    

### Cache

Qui vedi asset salvati dal browser per non riscaricarli ogni volta.

---

# **8. La scheda “Lighthouse”**

Serve per analizzare automaticamente:

- performance,
    
- accessibilità,
    
- SEO,
    
- qualità del codice,
    
- ottimizzazioni consigliate.
    

Ti genera un report completo con punteggi da 0 a 100.  
Uno strumento fondamentale per siti reali.

---

# **9. Modificare l’HTML in diretta**

Puoi fare:

- doppio click per cambiare testo,
    
- tasto destro → Edit as HTML,
    
- aggiungere classi,
    
- spostare nodi,
    
- eliminare elementi,
    
- simulare contenuti diversi.
    

⚠️ Importante:

> Le modifiche NON sono permanenti.  
> Si perdono con il refresh.

Perché stai modificando la **pagina già caricata**, non il file su disco né il server.

Ma questa capacità è essenziale per il debug:

- testare un attributo,
    
- verificare una struttura,
    
- capire se un div è effettivamente in pagina,
    
- controllare se un percorso è corretto.
    

---

# **10. Perché questo strumento è vitale**

Perché il debug _non si fa mai_ “guardando solo il file”.

Il debug si fa qui:

- vedi esattamente cosa il browser sta interpretando,
    
- puoi smontare la pagina come fosse Lego,
    
- puoi individuare errori invisibili a occhio nudo,
    
- puoi testare modifiche live,
    
- puoi confermare se un tag è chiuso male,
    
- puoi vedere se un file non è stato caricato.
    

È la tua “sala operativa”.