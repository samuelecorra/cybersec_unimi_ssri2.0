# **Lezione 4: Mettere online il sito con Altervista e FileZilla**

In questa lezione prendiamo il nostro **sitarello di prova** fatto in HTML e lo **mettiamo online** su un vero hosting.

Non è un sito che mostreresti al mondo per orgoglio 😅, ma ci serve per imparare **tutta la procedura reale**:

- scelta dell’hosting
    
- creazione del sito su Altervista
    
- accessi FTP
    
- configurazione di FileZilla
    
- upload dei file (versione minificata in `pro/`)
    
- controllo del sito online
    
- cenno a HTTPS, spazio disco, traffico e dominio personalizzato
    

Tutto quello che il video dice, ma **ordinato e completo**, senza saltare nulla.

---

## **1. Hosting vs VPS: dove vive il nostro sito**

Prima distinzione importante: **dove mettiamo fisicamente il sito**.

- **Hosting condiviso (tipo Altervista, Hostinger, ecc.)**
    
    - Perfetto per:
        
        - blog
            
        - piccoli siti vetrina
            
        - progetti iniziali
            
    - Il provider ti dà:
        
        - spazio su disco
            
        - dominio (a volte incluso)
            
        - gestione pannello
            
        - email collegate (spesso nei piani a pagamento)
            
    - Si occupano loro di tutta l’infrastruttura: server, aggiornamenti, sicurezza base.
        
- **VPS / Server dedicato**
    
    - È come avere **un altro computer tutto tuo** in un datacenter.
        
    - Hai molta più libertà:
        
        - installi quello che vuoi
            
        - gestisci servizi, firewall, database, configurazioni avanzate.
            
    - Ma richiede competenze sistemistiche serie:
        
        - configurare Linux
            
        - gestire web server (Apache, Nginx)
            
        - sicurezza, backup, ecc.
            
    - È ciò che userai quando:
        
        - il tuo sito ha tanto traffico
            
        - hai bisogno di configurazioni particolari
            
        - vuoi applicazioni web complesse.
            

Per il nostro livello attuale (**solo HTML, corso base**):

> **L’hosting condiviso è perfetto.**  
> Ci dà tutto pronto senza dover fare il sysadmin.

Nel video viene nominato anche **Hostinger** come hosting “serio” per progetti veri, perché ha:

- piani di hosting condiviso
    
- possibilità di passare a VPS quando le cose diventano “massive”
    

Ma **per imparare** useremo Altervista, perché è:

- gratuito
    
- sufficiente per testare
    
- comodo per questo esercizio.
    

---

## **2. Creazione del sito su Altervista**

Andiamo su **altervista.org**.

Vedrai che:

- spingono molto su **hosting WordPress gestito**
    
- ti propongono **“Crea blog WordPress”**, temi, ecc.
    

> A noi **non interessa WordPress**  
> perché stiamo imparando a **programmare il nostro HTML** a mano.

Quindi:

1. Vai su qualcosa tipo **“Crea sito” / “Crea il tuo sito”**.
    
2. Ti viene proposta la scelta del **nome del sito**:
    
    - es: `sitoesempio1.altervista.org`
        
3. Il dominio base `nome.altervista.org` è:
    
    - **gratuito per sempre** (come specificato nel video)
        
4. Non puoi usare un nome già preso (esattamente come la targa di un’auto):
    
    - se `sitoesempio.altervista.org` è occupato, devi aggiungere qualcosa
        
    - nel video: `sitoesempio1` viene accettato.
        

> Se vuoi un **tuo dominio personale** (tipo `tuonome.it` o `.com`), quello è **sempre a pagamento**, ovunque, non solo su Altervista.

Per questo ha senso, in futuro, scegliere direttamente un **hosting serio a pagamento** che includa:

- dominio personalizzato
    
- email tipo `info@tuodominio.it`
    
- pacchetto completo.
    

Ma per ora:

- scegli un nome tipo `sitoesempio1`
    
- confermi
    
- completi la **registrazione** con:
    
    - indirizzo email
        
    - dati richiesti da Altervista.
        

Alla fine della procedura:

- Altervista ti dice che ti ha inviato una **mail di conferma**
    
- specifica che entro **48 ore** dovresti ricevere il link per completare l’attivazione.
    

Nel video:

- lui “stacca”, aspetta circa **16 ore**
    
- poi la mail arriva con:
    
    - link alla dashboard
        
    - successivamente, un’altra mail con:
        
        - la **password**
            
        - i **dati di accesso FTP**.
            

---

## **3. Arrivo nella dashboard di Altervista**

Quando la registrazione è completata e clicchi il link in mail, arrivi alla **dashboard** del tuo sito su Altervista.

Qui puoi:

- vedere lo stato del sito
    
- gestire file, database, traffico, spazio, ecc.
    
- usare l’editor integrato (non lo useremo, è scomodo)
    
- trovare il link **“Gestione dei file / FTP”**
    

Nel video:

1. Clicca su **“Gestione dei file – Accedi”**.
    
2. Si apre un file manager online Altervista con:
    
    - cartelle
        
    - file
        
    - un `index.html` creato di default.
        

È una struttura simile a quella che hai in locale.

**Problema:**  
Gestire i file solo da quell’interfaccia web è **scomodo**.

Per lavorare bene usiamo un client FTP serio: **FileZilla**.

---

## **4. Installare e aprire FileZilla**

Passaggio successivo:

1. Vai su Google e cerca `FileZilla client`.
    
2. Scarichi **FileZilla Client** (non Server).
    
3. Lo installi normalmente.
    
4. Lo apri.
    

Interfaccia:

- nella parte **sinistra**: file del tuo computer (locale)
    
- nella parte **destra**: file sul server (remoto) _quando ti connetti_
    
- in alto: barra di connessione veloce
    
- in alto a sinistra: **“Gestione siti”** (Site Manager).
    

---

## **5. Creare una connessione FTP in FileZilla**

Ora dobbiamo creare una **connessione FTP** al nostro sito Altervista.

In Altervista, nella sezione FTP (quella che hai visto prima), trovi questi dati:

- **Host** (es. `ftp.sitoesempio1.altervista.org` o simile)
    
- **Porta** (di default **21** per FTP)
    
- **Nome utente** (es. `sitoesempio1`)
    
- **Password** (arriva via mail o visibile nel pannello)
    

In FileZilla:

1. Clicca su **“File” → “Gestione siti…”** (o l’icona a fianco della barra).
    
2. Clicca su **“Nuovo sito”**.
    
3. Compila:
    
    - **Host:** incolla l’host FTP fornito da Altervista (senza spazi)
        
    - **Porta:** `21` (salvo indicazioni diverse)
        
    - **Protocollo:** FTP (non FTP su TLS esplicito, a meno che Altervista lo richieda)
        
    - **Tipo di accesso:** Normale o come indicato
        
    - **Utente:** es. `sitoesempio1`
        
    - **Password:** incolla quella ricevuta via mail.
        
4. Rinomina il sito nel gestore, ad esempio:
    
    - `sito-altervista-corso-html`
        
5. Clicca **“Connetti”**.
    

FileZilla probabilmente ti chiede di:

- accettare un certificato o un warning iniziale → clicca “OK / Accetta”.
    

Se tutto va bene, nella colonna **di destra** compaiono i file remoti:

- in genere una cartella tipo `public_html/` o simile
    
- dentro trovi un `index.html` creato da Altervista.
    

Quella è la **root web** del tuo sito.

---

## **6. Aprire la cartella del sito in locale**

Adesso dobbiamo “abbinarla” alla cartella del nostro progetto **in locale**.

Hai qualcosa del tipo:

- cartella del progetto
    
    - `index.html` (versione leggibile)
        
    - altre pagine (`about.html`, `blog.html`, `contatti.html`, ecc.)
        
    - cartella `assets/` (immagini, ecc.)
        
    - cartella `pro/` (versione **minificata** delle pagine, pronta per la produzione) – come impostato nella lezione sulla minificazione.
        

Nel video:

1. Fa tasto destro sul file `index.html` nel progetto su VS Code
    
2. Clicca su **“Mostra in Esplora risorse”** (o equivalente)
    
3. Si apre la cartella locale con **tutto il progetto**.
    

Questa cartella locale è quella che vogliamo caricare **sul server** (ma usando i file giusti).

---

## **7. Quali file carichiamo sul server**

Qui entra in gioco il discorso **minificazione** e cartella `pro/`.

- Tu lavori sui file **non minificati**:
    
    - `index.html`
        
    - `about.html`
        
    - `blog.html`
        
    - `contatti.html`
        
    - ecc.
        
- Quando sei soddisfatto, **minifichi** il codice e crei la copia pronta per la produzione nella cartella `pro/`:
    
    - `pro/index.html`
        
    - `pro/about.html`
        
    - `pro/blog.html`
        
    - `pro/contatti.html`
        
    - ecc.
        

Perché?

- I file minificati:
    
    - occupano meno spazio (anche -15/25%)
        
    - si scaricano più velocemente
        
    - sono ideali per l’utente finale.
        

Quindi la logica è:

> **Localmente** lavoriamo sui file normali.  
> **Online** carichiamo i file minificati dentro `pro/` e li usiamo come pagine da servire.

In pratica:

- nella root del server (public_html) vogliamo:
    
    - **gli asset** (immagini ecc.)
        
    - **le pagine minificate** prese da `pro/`.
        

---

## **8. Copiare gli asset e le pagine minificate con FileZilla**

### **8.1 Copiare gli asset**

Nella colonna sinistra di FileZilla:

1. Vai nella cartella locale del progetto.
    
2. Se hai una cartella `assets/`, selezionala.
    
3. Trascinala nella colonna destra, dentro `public_html/` (o root del sito Altervista).
    

Risultato lato server:

- `public_html/assets/...` con dentro le tue immagini, icone, ecc.
    

### **8.2 Copiare le pagine minificate (`pro/`)**

Nella colonna sinistra:

1. Apri la cartella `pro/`.
    
2. Seleziona i file HTML minificati (es. `index.html`, `about.html`, `blog.html`, `contatti.html`, `articolo.html`, ecc.).
    
3. Trascinali nella colonna destra, nella root (`public_html/`).
    

FileZilla ti chiederà:

> C’è già un file `index.html`. Vuoi sovrascriverlo?

Scegli:

- **“Usa sempre questa azione”**
    
- **“OK / Sovrascrivi”**
    

In questo modo:

- il `index.html` di Altervista viene sostituito con **il tuo index minificato**.
    
- tutte le tue pagine diventano disponibili online.
    

Nel video viene fatto notare anche che:

- nel caso specifico, il `index.html` minificato era circa **un quarto più leggero** rispetto a quello non minificato (esempi 5 file / 15 KB vs 5 file / 20 KB).
    
- in progetti più grandi il risparmio diventa importante.
    

---

## **9. Verificare il sito online**

Ora che i file sono stati caricati:

1. Torna nella **dashboard di Altervista**.
    
2. Cerca il pulsante tipo **“Visualizza il tuo sito”**.
    
3. Clicca.
    

Si apre il browser con l’URL del tipo:

```
https://sitoesempio1.altervista.org
```

(o senza `https` all’inizio).

Potrebbe succedere che:

- per **cache** del browser, tu veda la vecchia pagina di default di Altervista.
    
- basta fare **aggiorna/refresh** (Ctrl+R, F5 o Ctrl+Shift+R per bypassare la cache).
    

Dopo l’aggiornamento:

- dovresti vedere **il tuo sito HTML**, con:
    
    - home
        
    - link a `about`, `blog`, `contatti`, ecc.
        
    - immagini e contenuti esattamente come in locale.
        

Nel video:

- va sulla pagina `blog`
    
- clicca su **Read more / Leggi di più**
    
- viene correttamente aperta la pagina `articolo.html`.
    

Quindi: **il sito funziona online.**

---

## **10. HTTPS, spazio, traffico e dominio**

Guardando la dashboard Altervista, noti alcune cose:

### **10.1 HTTPS**

In alto, Altervista mostra lo stato:

- il sito all’inizio è segnato come **“non sicuro”** se usi solo `http://`.
    
- c’è l’opzione **“Attiva HTTPS”** nelle risorse.
    

Per avere:

- `https://sitoesempio1.altervista.org`
    
- certificato SSL attivo
    
- lucchetto “sicuro” nel browser.
    

Per il nostro test non è indispensabile, ma:

> Per qualsiasi sito reale, in produzione → **HTTPS è obbligatorio**.

### **10.2 Spazio e traffico**

Altervista ti mostra anche:

- **Spazio** disponibile: es. **3 GB**
    
- **Traffico mensile**: es. **30 GB**
    

Per un sitarello di prova è tantissimo, non lo supererai mai.

Ma il discorso è importante:

- immagini, video, file grossi → consumano spazio e banda
    
- se altri siti linkano direttamente i tuoi file (hotlinking) → consumi anche il loro traffico.
    

Più avanti, con `.htaccess`, imparerai a:

- ottimizzare cache
    
- prevenire hotlinking.
    

### **10.3 Dominio personalizzato**

Altervista permette di:

- aggiungere un **dominio personalizzato** (`tuodominio.it`) puntato al tuo spazio Altervista.
    

Ma:

- dominio → **sempre a pagamento**
    
- di solito conviene farlo insieme a un hosting serio (Hostinger o simili) più avanti, quando il progetto è reale.
    

Per ora ti basta:

- il sottodominio `tuosito.altervista.org`
    
- gratuito e perfetto per test e studio.
    

---

## **11. Riepilogo della lezione**

Ciò che abbiamo fatto, passo per passo:

1. **Capito la differenza** tra hosting e VPS (per ora hosting tutta la vita).
    
2. **Creato un sito su Altervista** con sottodominio gratis.
    
3. **Aspettato le mail** di conferma e accesso FTP.
    
4. **Aperto la dashboard** e visto il file manager online (scomodo).
    
5. **Installato e configurato FileZilla** con:
    
    - host, porta 21, utente, password.
        
6. **Collegato FileZilla** e aperto la root (`public_html`).
    
7. **Aperto la cartella progetto locale** e:
    
    - copiato `assets/` sul server
        
    - copiato le pagine **minificate** da `pro/` nella root del sito, sovrascrivendo l’`index.html` di default.
        
8. **Verificato il sito online** tramite “Visualizza il tuo sito”:
    
    - home, blog, articolo, link funziona tutto.
        
9. **Visto le opzioni di Altervista**:
    
    - attivare HTTPS
        
    - spazio disco (es. 3 GB)
        
    - traffico (es. 30 GB)
        
    - dominio personalizzato.
        

Questo conclude **la parte “mettiamo online il nostro brutto sito”** 😄

---

## **12. Collegamento alla prossima parte**

Nel video, dopo questo passo, l’autore dice:

> “Nel prossimo video parliamo di una struttura della cartella un pochino più complessa”.

Ed è lì che entrano in gioco:

- la struttura “definitiva” del progetto
    
- i file:
    
    - `robots.txt`
        
    - `sitemap.xml`
        
    - `.htaccess`
        

che abbiamo già iniziato a vedere nelle altre tue richieste.