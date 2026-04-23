## **Lezione 1: Introduzione all’Accessibilità**

### **1. Ripartiamo da zero: che cos’è davvero l’accessibilità**

Quando parliamo di accessibilità nel web, stiamo parlando di **tutte quelle operazioni che rendono un sito utilizzabile anche da utenti che hanno qualche forma di svantaggio**, temporaneo o permanente.

È il punto in cui il nostro lavoro di sviluppatori non riguarda più solo “come appare” una pagina, ma **come viene percepita, letta, navigata e compresa**.  
Le persone senza difficoltà particolari riescono lo stesso a “sopravvivere” anche a siti scritti male. Possono compensare con gli altri sensi.  
Ma quando il sito **non è ottimizzato**, c’è un’intera fetta di utenti che rimane tagliata fuori.

Un dato che fa riflettere: **un quarto della popolazione mondiale** rientra in qualche forma di disabilità o limitazione.  
Da lievi difficoltà visive fino a limiti cognitivi più seri.

In più, esistono anche **disabilità temporanee**: ti rompi un polso → non puoi usare il mouse → devi navigare da tastiera.  
Quindi non si parla di “casi rari”, ma di **realtà quotidiane**.

### **2. Perché ci interessa l’accessibilità**

L’accessibilità è importante per tre motivi fondamentali:

#### **2.1. Per le persone**

Non vogliamo creare un web dove solo il “profilo ideale” dell’utente riesce a capire cosa sta succedendo.  
Il web è un servizio pubblico informale: dovrebbe essere accessibile a tutti.

#### **2.2. Per la legge**

In molti Paesi, soprattutto nel settore pubblico, l’accessibilità **è obbligatoria per legge**.  
Il Regno Unito, gli USA e diversi Paesi del Nord Europa hanno requisiti precisi.

In Italia al momento gli obblighi riguardano principalmente le PA, ma è evidente che la direzione è quella di estendere le regole anche ai privati.

#### **2.3. Per il SEO**

I motori di ricerca ormai considerano l’accessibilità **un fattore di ranking**.  
Se il tuo sito è ben strutturato, conforme, semanticamente corretto e accessibile, puoi tranquillamente sorpassare un concorrente che ha ignorato tutto questo.

Primo posto = gran parte dei click → conversioni → vendite.

### **3. Web.dev: la bibbia dell’accessibilità**

Nel video hai fatto vedere **web.dev**, che è gestito dal team di Chrome e rappresenta uno standard di settore.

Quando controlli l’accessibilità di quel sito, ti accorgi che:

- ogni elemento è semanticamente corretto
    
- i testi sono perfettamente contrastati
    
- la struttura è esemplare
    
- gli screen reader interpretano tutto senza confusione
    

È un sito che puoi usare come **benchmark**.  
Se non sai come risolvere un problema, vai lì: troverai la soluzione.

### **4. Tipologie di disabilità (e problemi correlati)**

Qui serve capire **che tipo di difficoltà esistono**, perché ognuna di queste richiede accorgimenti specifici.

---

### **4.1. Disabilità visive**

Qui rientrano:

- cecità totale
    
- ipovisione
    
- daltonismo
    
- difficoltà nel distinguere contrasti
    

Problemi comuni:

- **Zoom bloccato** su mobile → testi illeggibili
    
- **Contrasto colori insufficiente** → elementi invisibili
    
- **Testi scritti in font poco leggibili**
    
- **Codice non semantico** → gli screen reader si perdono
    

La persona “normovedente” sopravvive.  
Una persona cieca, davanti a un mare di `<div>`, **non sente più nulla di utile**.

---

### **4.2. Disabilità uditive**

Riguarda chi non può percepire contenuti audio.  
Il problema è semplice: **se c’è audio senza equivalente testuale**, quella parte di contenuto è _persa_.

È essenziale:

- trascrizioni
    
- sottotitoli
    
- versioni testuali dei contenuti sonori
    

---

### **4.3. Disabilità motorie**

Qui pensiamo a persone che:

- non possono usare il mouse
    
- devono navigare da tastiera
    
- utilizzano switch speciali
    
- usano eye-tracking o dispositivi alternativi
    

L’interfaccia deve essere:

- navigabile con `Tab`
    
- prevedibile
    
- ordinata
    

Se un bottone è **raggiungibile solo col mouse**, quella persona non può usarlo.

---

### **4.4. Disabilità cognitive**

Qui lo spettro è enorme:

- autismo
    
- sindrome di Down
    
- problemi di memoria
    
- anziani
    
- difficoltà di attenzione
    

I problemi più comuni:

- interfacce confuse
    
- pop-up continui
    
- testi unici e densi senza paragrafi
    
- font eccessivamente stilizzati
    
- layout troppo ricchi di elementi rumorosi
    

Un’interfaccia semplice e pulita **aiuta tutti**, non solo loro.

---

### **4.5. Disabilità fotosensibili (epilessia)**

Esistono persone sensibili a:

- flash
    
- effetti animati veloci
    
- video in autoplay
    
- sfondi in movimento
    
- parallax aggressivi
    

La pagina può letteralmente provocare un malessere.

Le interfacce moderne stanno abbandonando:

- animazioni eccessive
    
- colori “sparati”
    
- video di background
    
- effetti parallax complessi
    

Stiamo andando verso un web **più sobrio e più accessibile**, senza perdere in estetica.

---

### **5. Perché l’accessibilità è il vero valore aggiunto del developer moderno**

Chiunque può montare un sito:

- WordPress
    
- page builder
    
- temi preconfezionati
    
- AI
    
- template già fatti
    

**Ma pochissimi sanno creare siti che siano:**

- performanti
    
- accessibili
    
- veloci
    
- ottimizzati per il SEO
    
- semantici
    
- chiari
    
- universali
    

È questo che ti rende davvero diverso da un developer “medio”.

Quando un’azienda cerca una figura web, non cerca “uno che sa scrivere HTML”.  
Cerca qualcuno che scriva **HTML fatto bene**.

### **6. Conclusione della lezione**

Questa era l’introduzione generale.  
Nel prossimo video entriamo nelle **regole pratiche dell’accessibilità**:

- cosa controllare
    
- quali errori evitare
    
- come semplificare la vita agli screen reader
    
- come costruire struttura e semantica pulite
    
- come migliorare navigazione e leggibilità
    

Adesso sai **perché** ti ho martellato dall’inizio del corso.  
Non è un capriccio: è la direzione in cui sta andando il web.