## **Lezione 9: CDN (Content Delivery Network)**

**Capitolo finale – concetti avanzati per siti veloci a livello mondiale**

In questa lezione allarghiamo il quadro:  
hai un sito HTML online, stai per imparare CSS e poi JavaScript, quindi non sei lontano da:

- un blog serio
    
- un sito aziendale
    
- un progetto con **traffico reale**
    

È il momento giusto per introdurre i **CDN**: non servono per il “sitarello” di prova, ma diventano fondamentali quando:

- hai utenti in giro per il mondo
    
- hai molte immagini, video, asset pesanti
    
- vuoi un sito **veloce ovunque**, non solo in Italia.
    

---

## **1. Il problema di base: distanza fisica e latenza**

Immagina la situazione attuale:

- Hai scelto un **hosting** (es. Altervista, Hostinger, Aruba, ecc.)
    
- Il server fisico è in **Italia** (datacenter italiano o europeo).
    

Dal punto di vista della rete:

- tu sei in Italia → il server è vicino → il sito risponde veloce;
    
- ma un utente in:
    
    - **Canada**
        
    - **Stati Uniti**
        
    - **Messico**
        
    - **Giappone**
        
    - **Africa**
        

deve “attraversare mezzo mondo” per recuperare:

- la tua pagina HTML
    
- le immagini
    
- i video
    
- i file CSS/JS.
    

La connessione passa:

- attraverso **cavi in fibra ottica**, anche **sottomarini**, che collegano i continenti.
    

Anche se la fibra è velocissima, la **distanza fisica conta**:

- la **latenza** (tempo tra richiesta e risposta) aumenta;
    
- scaricare la stessa immagine dall’Italia o dal Canada **non è la stessa cosa**.
    

Per un:

- **blog di cucina italiana scritto in italiano**, il 99% del traffico sarà probabilmente italiano → il problema quasi non si sente.
    
- **sito internazionale** (in inglese o multi-lingua) → hai gente da tutto il mondo, e lì la distanza diventa importante.
    

---

## **2. Definizione: cos’è un CDN**

CDN = **Content Delivery Network**

- **Content** → contenuti (immagini, video, file statici, ecc.)
    
- **Delivery** → spedizione, consegna
    
- **Network** → rete di server distribuiti
    

In pratica:

> Un CDN è **una rete di server sparsi nel mondo** che conserva copie dei tuoi contenuti statici (asset) e li consegna all’utente dal server **più vicino geograficamente**.

Tipicamente su un CDN metti:

- immagini (`.jpg`, `.png`, `.webp`, `.avif`)
    
- video
    
- file CSS
    
- file JavaScript
    
- font, icone, ecc.
    

Il tuo **server principale (hosting)** rimane il “cervello” del sito (pagina HTML, logica, ecc.), ma gli **asset pesanti** vengono serviti dai nodi del CDN.

---

## **3. Modello concettuale: server origin + nodi CDN**

Disegno mentale (semplificato):

- Hai il tuo **server origin** (hosting) in Italia.
    
- Attivi un **servizio CDN** che ha server (nodi) in:
    
    - Europa
        
    - Nord America
        
    - Sud America
        
    - Asia
        
    - Africa
        
    - Oceania
        

Flusso:

1. **Tu carichi** i tuoi asset (immagini, video, ecc.) sul CDN.
    
2. Il CDN li distribuisce (replica) sui suoi nodi nel mondo.
    
3. Quando un utente apre la pagina:
    
    - la **pagina HTML** può arrivare dal server origin (Italia);
        
    - gli **asset statici** vengono scaricati dal nodo CDN **più vicino** all’utente.
        

Esempio:

- Utente in **Canada**:
    
    - HTML → dal server in Italia
        
    - immagini → dal nodo CDN in Canada o USA
        
- Utente in **Messico**:
    
    - immagini → dal nodo più vicino nel continente americano
        
- Utente in **Giappone**:
    
    - immagini → dal nodo CDN in Asia
        

Risultato:

- tempo di scaricamento degli asset **drasticamente ridotto**
    
- percezione di sito **molto più veloce** a livello globale.
    

---

## **4. Vantaggi pratici del CDN**

### **4.1 Performance globali**

- Riduci la **latenza** per utenti lontani.
    
- Riduci il tempo di caricamento di:
    
    - immagini
        
    - video
        
    - font e JS pesanti.
        
- L’esperienza utente è più fluida, anche a migliaia di km.
    

### **4.2 Scarico il server principale**

Senza CDN:

- ogni immagine/video viene servita dal tuo hosting (es. Altervista, Aruba, Hostinger).
    
- consumi:
    
    - **spazio disco** del tuo hosting
        
    - **banda/traffico** del tuo piano (es. Altervista: 30 GB/mese).
        

Con CDN:

- il carico sul tuo hosting diminuisce:
    
    - meno richieste di file pesanti
        
    - meno traffico consumato
        
- puoi restare su un piano hosting più economico e “scaricare” il grosso del traffico sugli asset al CDN.
    

### **4.3 Meglio CDN che solo “più banda”**

Se aumenti solo la banda sul tuo hosting:

- il server rimane comunque in Italia;
    
- un utente giapponese deve comunque “attraversare mezzo mondo”.
    

Con CDN:

- paghi il servizio che è **già distribuito globalmente**;
    
- non solo più banda, ma anche **maggiore vicinanza geografica** ai visitatori.
    

---

## **5. Esempio reale: bunny.net**

L’autore del corso cita un CDN concreto che usa spesso: **bunny.net**.

Perché gli piace:

1. **Grafica e dashboard piacevoli**  
    Interfaccia chiara, moderna, facile da usare.
    
2. **Usato da molte aziende**  
    È un servizio serio, non una cosa improvvisata.
    
3. **Modello “pay as you go”**  
    Non è un abbonamento fisso mensile, ma:
    
    > Ricarichi un credito (come una ricarica telefonica / gift card) e paghi solo **il traffico che consumi**.
    
    Esempio:
    
    - fai una ricarica da **100 €**:
        
        - il credito rimane lì
            
        - si consuma man mano che gli utenti scaricano immagini/video.
            
    - per siti con solo immagini e traffico normale:
        
        - anche una ricarica da **10 €** può bastare **6–12 mesi** (dipende dal traffico).
            
4. **Perfetto per siti con molte immagini/video**
    

- Se il tuo hosting ti dà, ad esempio, **30 GB di traffico** al mese:
    
    - con solo il tuo sito va bene;
        
    - se però altri siti incorporano le tue immagini o hai moltissimi accessi, potresti esaurire la banda.
        
- Con il CDN:
    
    - quegli accessi gravano **sul CDN**, non direttamente sull’hosting.
        

---

## **6. Cosa cambia nel codice HTML quando usi un CDN**

Senza CDN, tipicamente:

```html
<img src="/assets/immagini/colazione.jpg" alt="Colazione">
```

Questa immagine viene servita:

- dal **tuo hosting**
    
- dalla cartella `assets/immagini` sul server italiano.
    

Con CDN:

1. Carichi l’immagine su bunny.net (o altro CDN).
    
2. Bunny ti fornisce un URL del tipo:
    

```text
https://cdn.bunny.net/qualcosa/colazione.jpg
```

Nel codice HTML cambi semplicemente il `src`:

```html
<img src="https://cdn.bunny.net/qualcosa/colazione.jpg" alt="Colazione">
```

Risultato:

- Il browser:
    
    - per l’HTML della pagina → parla col tuo server in Italia.
        
    - per l’immagine → parla con il nodo CDN più vicino all’utente.
        

Concetto chiave:

> **L’immagine non vive più sul tuo server**, ma sullo storage del CDN.  
> Tu nel codice punti al **nuovo URL esterno**.

---

## **7. Quando ha senso usare un CDN (e quando no)**

### **Ha senso usare un CDN quando:**

- Il tuo sito è **internazionale** (lingua inglese o pubblico globale).
    
- Hai molte:
    
    - immagini
        
    - gallerie
        
    - video
        
    - contenuti multimediali.
        
- Vuoi **massimizzare la velocità** percepita ovunque.
    
- Vuoi alleggerire:
    
    - spazio
        
    - traffico
        
    - carico del tuo hosting.
        

### **Per il “sitarello di prova” del corso:**

- È **totalmente inutile**, sarebbe overkill:
    
    - traffico quasi nullo
        
    - pubblico solo italiano
        
    - poche immagini.
        
- Ma è importante **conoscere il concetto adesso** perché:
    
    - lo vedrai spesso in WordPress, pannelli di hosting, plugin performance;
        
    - ti comparirà la scritta “CDN” e saprai **esattamente di cosa stanno parlando**.
        

---

## **8. Collegamento al resto del corso**

All’inizio del corso hai visto:

- come funziona il web “base”:
    
    - browser → richiesta → server → risposta.
        

Ora, alla fine:

- aggiungiamo **un tassello avanzato**:
    
    - il server origin **non è l’unico** che risponde;
        
    - esiste una **rete di server (CDN)** che aiuta nella consegna dei contenuti.
        

Il tutto si incastra con:

- **minificazione** (HTML/CSS/JS leggeri)
    
- **ottimizzazione delle immagini** (WebP, AVIF, ecc.)
    
- **cache** (browser + `.htaccess`)
    
- e, più avanti:
    
    - CSS per lo stile
        
    - JavaScript per l’interattività.
        

Questa lezione sui CDN chiude il cerchio “macro” sulle performance:

> Hosting → struttura cartelle → minificazione → robots/sitemap/htaccess → CDN per la distribuzione globale.
