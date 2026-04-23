## **Lezione 9: Il Carosello (Carousel)**

### **1. Cos’è davvero un carosello**

Il **carosello** è un componente che serve a mostrare una serie di **immagini (o immagini + testo)** che scorrono orizzontalmente, una dopo l’altra.  
È stato uno dei pattern più usati nei siti web tra il **2010 e il 2017**, soprattutto in homepage:

- banner grandi
    
- immagini che scorrono automaticamente
    
- frecce laterali
    
- “pallini” sotto per navigare tra le slide
    

Oggi è un pattern **superato** nel web moderno, soprattutto per:

- problemi di UX (la gente non guarda le slide successive)
    
- performance (immagini pesanti)
    
- accessibilità (screen reader, autoplay, focus, movimento forzato)
    
- design (aspetto datato, poco “pulito”)
    

Però **viene ancora richiesto** da:

- clienti poco aggiornati
    
- template WordPress già pronti
    
- siti aziendali con design anni 2010
    
- landing page prefabbricate
    

Quindi devi conoscerlo, anche se _non è più cool_.

---

## **2. Com’è fatto un carosello**

Un carosello ha **sempre** questi elementi:

- **slide** → contenitore con immagine (obbligatoria) e magari testo
    
- **freccia sinistra/destra** → per passare alla slide precedente o successiva
    
- **indicatori (i pallini)** → rappresentano il numero delle slide, permettono la selezione
    
- **animazione orizzontale** → le slide scorrono da una parte all’altra
    
- **autoplay (opzionale)** → cambia la slide automaticamente
    

Il carosello **deve avere un’immagine**, altrimenti perde completamente senso:  
è un _carosello di contenuti visivi_, non testuali.

---

## **3. Carosello: un pattern vecchio vs uno moderno**

### **Carosello classico (vecchio)**

- grande banner orizzontale
    
- immagini grandi
    
- testo sovrapposto
    
- bottoni call-to-action
    
- utilizzo massiccio nei template del passato
    

Questo è ciò che trovi nei siti "vecchiotti", stile anni 2010.

### **Carosello moderno**

In realtà… nel web moderno **il carosello non esiste quasi più**,  
ma è rimasto nel **mobile**, trasformandosi in:

- **stories**
    
- **slider orizzontali di card**
    
- **caroselli di elementi piccoli** tipo Netflix
    

Questi sono molto più efficaci, perché:

- l’utente controlla manualmente lo scroll
    
- non c’è autoplay
    
- non c’è animazione forzata
    
- sono più leggibili, più utili, più accessibili
    

Esempi moderni:

- carosello di film Netflix
    
- carosello di prodotti Amazon
    
- carosello di “storie” Instagram/Facebook
    
- caroselli interattivi nelle app (Material Design)
    

Ma questi **non sono più i caroselli del 2010**.

---

## **4. Carosello e Material Design**

In Material Design trovi esempi lato **mobile**:

- slider di contenuti
    
- caroselli di card
    
- mini-gallery scorrevoli
    

Questi sono basati sullo swipe ed è per questo che Material dà esempi “story-like”.

Ma a livello **web**, i caroselli Material sono:

- minimi,
    
- raramente utilizzati,
    
- meno importanti rispetto alle card, alle liste, alle chip e ai fab button.
    

---

## **5. Perché i caroselli web sono considerati “superati”**

Le ragioni principali:

### **❌ L’utente guarda solo la prima slide**

Studi di UX confermano che oltre il 70% delle persone ignora la seconda slide.

### **❌ L’autoplay è fastidioso e distraente**

### **❌ Pesano tanto (immagini grandi = page weight elevato)**

### **❌ Uccidono la SEO**

Google vede un blocco che cambia dinamicamente → non apprezza.

### **❌ Non sono accessibili**

Screen reader, timer, movimento non controllabile = pessima esperienza.

### **❌ Estetica datata**

Si vede immediatamente quando un sito è del 2012.

---

## **6. Quando si usano oggi?**

Solo se il cliente:

- insiste
    
- vuole un template preconfezionato
    
- vuole “ricreare” un sito vecchio che già conosce
    

Oppure se il contesto mobile lo giustifica (tipo card scrollabili).

---

## **7. Come si implementano oggi**

### **Con Bootstrap**

Ha un componente Carousel integrato, completo di:

- JavaScript
    
- swipe
    
- animazioni
    
- indicatori
    
- autoplay
    

### **Con Tailwind**

Non esiste un componente nativo (Tailwind non ha componenti).  
Servono:

- librerie esterne (Flowbite, Tailwind UI)  
    oppure
    
- scriverlo da zero con JavaScript + classi Tailwind
    

---

## **Conclusione**

Il carosello è uno dei componenti più iconici del web “vecchia scuola”,  
oggi rimpiazzato da pattern più moderni, soprattutto su mobile.

Devi conoscerlo, perché potresti ancora trovartelo davanti,  
ma devi anche riconoscere **perché è considerato un pattern obsoleto**.

---

## **Prossimo componente**

Vediamo un altro super-classico del web design: il collapse.