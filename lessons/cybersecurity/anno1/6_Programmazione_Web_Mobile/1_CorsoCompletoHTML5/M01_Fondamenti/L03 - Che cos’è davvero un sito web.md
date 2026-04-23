# **Lezione 3: Che cos’è davvero un sito web**

## **1. Riprendere il filo: dal file HTML al sito completo**

Negli episodi precedenti abbiamo visto che una pagina HTML è semplicemente un file `.html`. Ora spostiamo lo sguardo da **una singola pagina** a ciò che chiamiamo **sito web**.

Il docente lo anticipa: un sito web è un insieme di file, cartelle e sottocartelle. Nulla di più misterioso. Possiamo perfino crearne uno sul desktop: la differenza tra “cartella sul computer” e “cartella sul server” è solo che il server rimane sempre acceso e risponde alle richieste.

Per visualizzarne la struttura, useremo l’organizzazione a sinistra di Visual Studio Code, che ci permette di osservare immediatamente l’architettura di un sito.

---

## **2. I file principali del sito**

### **2.1 L’home page: `index.html`**

In qualunque sito, la pagina di ingresso è quasi sempre `index.html`. È la prima che il server restituisce quando l’utente visita il dominio senza specificare nulla.

### **2.2 Le altre pagine**

Un sito raramente contiene un’unica pagina. Potremmo avere:

- `educazione.html`
    
- `contatti.html`
    
- `blog.html`
    
- e le singole pagine del blog, ad esempio:  
    `articolo-bellissimo.html`
    

Ogni file HTML rappresenta una pagina distinta del sito.

---

## **3. Le sottocartelle: il cuore dell’architettura**

Oltre ai file HTML principali, qualunque sito ben organizzato contiene **sottocartelle**, che racchiudono i componenti aggiuntivi necessari.

La cartella più comune è:

### **3.1 `assets/`**

Il termine “assets” indica tutti i file che non sono HTML ma che servono a completare il sito. Gli assets includono:

#### **• CSS**

Contengono le regole di stile. È qui che diciamo ai browser:

- quali colori usare,
    
- come posizionare gli elementi,
    
- quale font adottare,
    
- come devono comportarsi gli elementi grafici.
    

Un esempio tipico:

```
assets/css/stile.css
```

#### **• JavaScript**

Racchiude il codice che rende il sito interattivo o dinamico.  
Ad esempio, uno slider di immagini, un menu a comparsa, un bottone che reagisce al click:

```
assets/js/script.js
```

#### **• Immagini**

Ogni immagine utilizzata nel sito si trova tipicamente in:

```
assets/images/
```

E può contenere file come:

- `logo.webp`
    
- `foto1.jpg`
    
- `banner.png`
    

---

## **4. La cartella del sito e il server: nessuna differenza concettuale**

Se apriamo la cartella “del sito” in Esplora Risorse (o Finder), vediamo:

```
cartella-sito/
    index.html
    educazione.html
    contatti.html
    blog.html
    articolo-bellissimo.html
    assets/
        css/
        js/
        images/
```

Questa è **esattamente** la stessa cartella che visualizzeremo in VS Code.  
La differenza è solo _dove si trova_:

- sul nostro computer → la apriamo come cartella locale;
    
- su un server → la stessa struttura resta online e risponde al pubblico.
    

> **Un sito web è letteralmente una cartella con dentro file organizzati.  
> Nessuna magia, nessun software complicato nascosto dietro: solo file.**

---

## **5. Il processo di richiesta: come il server “serve” un sito**

Quando un utente visita il sito, avviene il seguente processo:

1. Il browser invia una richiesta HTTP alla cartella del sito, chiedendo un file (es. `index.html`).
    
2. Il server individua quel file nella cartella e lo restituisce.
    
3. Se quella pagina contiene:
    
    - immagini → il server le recupera da `assets/images/`
        
    - file CSS → li recupera da `assets/css/`
        
    - script JavaScript → li recupera da `assets/js/`
        
4. Il browser riceve tutto, interpreta i file e mostra la pagina completa.
    

L’intero funzionamento del web, nella sua essenza, è questo semplice scambio di file.

Il browser non fa altro che:

- comporre la struttura HTML,
    
- applicare i CSS,
    
- eseguire eventuali script,
    
- visualizzare immagini e media.
    

---

## **6. Perché gli editor sono indispensabili**

Se provassimo a costruire un sito con decine di file HTML, immagini, fogli di stile e script usando solo Blocco Note, sarebbe impossibile.  
È qui che un editor professionale come **Visual Studio Code** diventa essenziale: permette di lavorare in modo ordinato, visualizzare la struttura delle cartelle, gestire errori, indentazione, suggerimenti, anteprima e molto altro.

Nel corso impareremo a:

- creare tutta la struttura HTML,
    
- organizzare correttamente la cartella del sito,
    
- preparare i file che serviranno per metterlo online.
    

---

## **7. Conclusione e direzione della prossima lezione**

Abbiamo quindi chiarito la definizione operativa di “sito web”:

> **Un sito web è una cartella contenente file HTML e altri file (CSS, immagini, script) organizzati in sottocartelle. Il server non fa altro che restituire questi file al browser quando vengono richiesti.**

Nella prossima lezione affronteremo un argomento strettamente collegato:  
**che cos’è un motore di ricerca e perché è importante capirlo per progettare un sito web.**