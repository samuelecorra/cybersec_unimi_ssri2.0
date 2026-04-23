**Lezione 2 – Installare Visual Studio Code e configurare l’ambiente di lavoro**

**Perché installiamo Visual Studio Code**

Per lavorare in modo efficiente con HTML, CSS e tutto ciò che verrà in futuro, serve un editor moderno, leggero, gratuito e potente. Visual Studio Code soddisfa tutti questi requisiti. Prima di iniziare con il CSS vero e proprio, dobbiamo quindi prepararci l’ambiente di lavoro.

**Download di Visual Studio Code**

Si cerca “Visual Studio Code” su Google oppure si usa il link presente nell’articolo della lezione.  
La home del sito mostra direttamente il pulsante “Download for Windows”.  
Un clic avvia il download automatico dell’eseguibile.

Questo processo è identico a quello che abbiamo visto nei siti web: si visita una pagina, la pagina rimanda a un file e il browser lo scarica. È esattamente ciò che si fa quando si crea un sito con una cartella chiamata “download” e si permette agli utenti di scaricare un file ospitato lì.

Visual Studio Code è un programma complesso, costruito da Microsoft, ma ciò non cambia il principio di base: una pagina HTML conduce a un file da scaricare.

**Installazione e opzioni consigliate**

Durante l’installazione compare il contratto di licenza da accettare.  
Le opzioni che conviene selezionare sono tutte quelle suggerite:

- aggiungere “Apri con Code” nel menu tasto destro sui file
    
- aggiungere “Apri con Code” nel menu tasto destro sulle cartelle
    
- registrare Visual Studio Code come editor predefinito per i file supportati
    
- abilitare l’uso del comando `code` nel terminale (tramite l’opzione PF)
    

Queste opzioni permettono di aprire file e progetti molto più velocemente. Con il tasto destro su qualunque file o cartella è possibile aprirlo direttamente in Visual Studio Code. Il comando `code .` da terminale, invece, apre una cartella intera dentro l’editor (non serve capire subito il terminale, lo vedremo molto più avanti: qui basta sapere che quella funzione è utile).

Dopo aver scelto dove creare l’icona (desktop o barra delle applicazioni), si procede con “Avanti” e poi “Installa”.

Una volta completato, si avvia Visual Studio Code, che riapre automaticamente l’ultimo progetto aperto.

**Installare le estensioni essenziali**

Le estensioni aggiungono funzionalità utili per scrivere codice in modo fluido ed evitare errori. Si trovano nella sezione “Extensions” di Visual Studio Code.

Le estensioni fondamentali sono:

**1. Prettier – Code Formatter**  
È un formattatore di codice. Automatizza l’allineamento, l’indentazione, la pulizia e l’ordine dei file.  
Se si apre un file HTML nel blocco note, risulta disordinato; Prettier sistemerà tutto con un comando da tastiera, rendendo il codice leggibile e piacevole.

**2. Auto Rename Tag**  
Quando si rinomina un tag HTML di apertura, questo plugin rinomina automaticamente anche il tag di chiusura.

**3. Auto Close Tag**  
Appena si chiude il simbolo `>`, Visual Studio Code completa automaticamente il tag di chiusura, evitando errori e risparmiando tempo.

**4. Live Server**  
È uno dei plugin più importanti: crea un piccolo server locale sul proprio computer.  
Il server non è “pubblico”: è attivo solo in locale, quindi nessuno dall’esterno può accedere.  
Live Server permette di aggiornare automaticamente la pagina ogni volta che si salva un file, simulando il comportamento di un vero sito ospitato su un server.

**5. Edge Tools (opzionale)**  
Questa estensione aiuta soprattutto con l’accessibilità e offre funzioni aggiuntive di debug. Non è fondamentale; puoi installarla o ignorarla.

**Dimostrazione rapida delle estensioni**

L’insegnante crea un file HTML con un semplice `h1`.  
Grazie alle estensioni installate:

- il tag di chiusura viene generato automaticamente (Auto Close Tag);
    
- rinominare `h1` rinomina anche `</h1>` (Auto Rename Tag);
    
- cliccando su un tag viene evidenziato il corrispettivo tag di chiusura (funzionalità integrata).
    

Per vedere la pagina, si usa Live Server:

- si fa tasto destro → “Open with Live Server”, oppure
    
- si clicca su “Go Live” in basso a destra.
    

È fondamentale che la pagina si trovi dentro una cartella di progetto: Live Server funziona solo partendo da una cartella, perché simula un sito completo, non un file isolato.

Quando si salva (`Ctrl + S`), la pagina aggiorna automaticamente:

- Live Server intercetta il salvataggio;
    
- finge che il browser stia richiedendo di nuovo la stessa pagina;
    
- la ricarica immediatamente.
    

Tutto questo avviene in _locale_, cioè all’indirizzo `127.0.0.1` oppure `localhost`.  
Questi due sono sinonimi per “il tuo computer”.

**Perché prepariamo tutto questo adesso**

Prima di scrivere vero HTML e vero CSS dobbiamo essere sicuri di lavorare in modo comodo, veloce e ordinato. Nella prossima lezione verranno spiegate le scorciatoie da tastiera più utili per usare Visual Studio Code in modo efficiente: questo permette di risparmiare tempo fin da subito e rende tutto il corso molto più fluido.

La prossima lezione riguarda quindi le shortcut essenziali dell’editor.