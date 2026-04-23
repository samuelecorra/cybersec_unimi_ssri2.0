## **Lezione 2: Copyright, Licenze e Buone Pratiche per l’Uso delle Immagini sul Web**

### 1. Perché oggi le immagini contano più che mai

Il web contemporaneo è dominato dai contenuti multimediali. Le pagine non sono più composte soltanto da testo: includono immagini, GIF, brevi video, audio, podcast e contenuti generati costantemente dagli utenti.  
Il traffico e l’attenzione gravitano attorno a ciò che è visivo e dinamico. Per questo la gestione corretta delle immagini è diventata una competenza imprescindibile per chiunque crei contenuti web.

### 2. Dov’è cambiato Internet: dal Far West al rispetto del copyright

Internet non è più quello dei primi anni 2000, quando ci si sentiva in un terreno senza regole.  
Oggi:

- le opere digitali sono protette dalla legge sul copyright;
    
- i proprietari dei contenuti vigilano molto di più;
    
- esistono software automatici che scansionano il web alla ricerca di utilizzi non autorizzati di immagini, video e altri media.
    

Il risultato è semplice: **utilizzare un’immagine senza licenza espone a multe concrete**, anche a distanza di anni.  
Questo vale per chiunque: aziende, freelance, blogger, privati.

Un esempio reale: un sito abbandonato, vecchio di sette anni, ha ricevuto una multa di circa 700€ per una singola immagine senza licenza. Ora immagina un blog con 365 articoli l’anno: se anche solo 10 immagini fossero irregolari, il totale salirebbe a 7000€.

### 3. Prima regola: non si prendono immagini da Google

Scrivere una parola, aprire Google Immagini e salvare la prima foto che piace **non è permesso**.  
Google non è un archivio libero: mostra immagini che appartengono a fotografi, aziende, agenzie o piattaforme con regole precise.

Se vuoi filtrare le immagini che puoi usare, devi selezionare:

**Strumenti → Diritti di utilizzo → Licenze Creative Commons**

Questo mostra solo immagini riutilizzabili.  
Ma anche qui bisogna fare attenzione: “riutilizzabile” non significa “faccio quello che voglio”. Ogni immagine ha la sua licenza, e la licenza va letta.

### 4. Seconda regola: non si incorporano immagini tramite link esterno

Un errore gravissimo consiste nel fare così:

- tasto destro su un’immagine trovata online;
    
- “Copia indirizzo immagine”;
    
- incollare quell’indirizzo nel proprio `<img src="...">`.
    

In questo modo:

- **stai usando la banda di un altro sito**, senza permesso;
    
- **non controlli la stabilità dell’immagine** (può essere rimossa o cambiata);
    
- **in alcuni casi stai violando la licenza**, perché l’immagine è servita da un server che non ti appartiene.
    

È come prendere una bibita dal frigo di un amico e portarla a una festa a casa tua.  
È usare risorse altrui per un tuo contenuto. È scorretto e spesso illegale.

L’unica eccezione accettata è quando la piattaforma stessa autorizza espressamente l’incorporamento, come avviene con YouTube.

### 5. Dove devono stare le immagini corrette

Le immagini vanno:

- ospitate **sul tuo sito**  
    oppure
    
- ospitate su un tuo **CDN** (Content Delivery Network), di cui parlerai più avanti.
    

In entrambi i casi, l’immagine è sotto il tuo controllo e non grava su terzi.

### 6. Dove trovare immagini legalmente utilizzabili

Hai tre possibilità principali.

#### 6.1. Archivio gratuito: Creative Commons e simili

Puoi usare:

- Unsplash
    
- Pixabay
    
- Pexels
    
- Freepik (con limitazioni)
    
- Altri siti indicati come “free images – no copyright”.
    

Queste piattaforme offrono immagini:

- utilizzabili gratuitamente;
    
- spesso anche per scopi commerciali;
    
- con licenze molto permissive.
    

**Ma la licenza va sempre letta.**  
Alcuni siti richiedono l’attribuzione obbligatoria, altri no.

#### 6.2. Archivio a pagamento

Perché pagare se ci sono immagini gratis?

Perché:

- il materiale a pagamento è molto più aggiornato;
    
- la qualità è mediamente più alta;
    
- c’è molta più scelta;
    
- molte immagini professionali non vengono pubblicate nei siti free.
    

Per chi ha un’attività che genera anche solo qualche migliaio di euro l’anno, un abbonamento da qualche centinaio di euro è un investimento minimo rispetto alle conseguenze di una violazione.

#### 6.3. Produrre immagini originali

La soluzione migliore è produrre tu stesso le immagini.

Se le foto sono tue:

- il copyright è tuo;
    
- puoi usarle come vuoi;
    
- gli altri devono chiedere il permesso a te.
    

Puoi anche farle produrre da fotografi o freelance: basta stabilire nel contratto che i diritti vengono ceduti a te.

### 7. Come usare correttamente le immagini scaricate

La procedura corretta è semplice:

1. Scegli l’immagine (da un sito affidabile).
    
2. **Scaricala**, non copiarla tramite link.
    
3. Salvala nella cartella del tuo progetto, ad esempio:
    

```
/immagini/prova.jpg
```

4. Inseriscila nel codice:
    

```html
<img src="immagini/prova.jpg" alt="descrizione">
```

5. Controlla sempre la pagina “Licenza” del sito da cui l’hai presa.
    

### 8. Cosa controllare nella licenza

Ogni piattaforma spiega:

- se puoi usare l’immagine commercialmente;
    
- se devi attribuire l’autore;
    
- cosa è vietato (es. rivenderla così com’è, creare un sito clone, fare scraping massivo).
    

Se la licenza richiede l’attribuzione, devi inserirla.  
Non farlo è una violazione, anche se l’immagine era “gratuita”.

### 9. Gli errori da evitare assolutamente

- prendere immagini da Google senza filtri;
    
- copiare l’URL delle immagini dai siti altrui;
    
- usare immagini prese senza leggere la licenza;
    
- dare per scontato che “tanto è gratis”;
    
- riutilizzare grandi quantità di immagini da un’unica piattaforma (vietato da molte licenze, es. Unsplash).
    

### 10. Conclusione

Il copyright non è un dettaglio tecnico: è una responsabilità seria per chiunque pubblichi online.  
Le multe arrivano davvero, anche dopo anni, e per importi non trascurabili.

Fortunatamente:

- esistono immagini gratuite e legali;
    
- esistono servizi professionali a basso costo;
    
- puoi produrre da solo i tuoi contenuti;
    
- basta leggere le licenze e rispettarle.
    

Usare immagini in modo corretto significa pubblicare contenuti sicuri, professionali e senza rischi.