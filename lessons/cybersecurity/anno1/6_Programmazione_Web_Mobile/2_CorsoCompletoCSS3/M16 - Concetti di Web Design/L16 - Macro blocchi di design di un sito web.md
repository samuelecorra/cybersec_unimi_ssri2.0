## **Lezione 16: Macro blocchi di design di un sito web**

### **1. Componenti vs macro blocchi**

Finora hai visto tanti **componenti singoli**:

- navbar
    
- bottoni
    
- card
    
- alert
    
- accordion
    
- modali, tab, pagination, ecc.
    

Ma **un sito reale** non è solo una somma di micro-componenti:  
è organizzato in **macro blocchi di contenuto**, cioè **sezioni**.

Esempi di macro blocchi:

- **Hero** (sezione principale in alto)
    
- **Sezione “Features”** (cosa fa il prodotto)
    
- **Sezione “Clienti / Loghi”**
    
- **Sezione “Team”**
    
- **Sezione “Blog / Articoli”**
    
- **Sezione “Pricing”**
    
- **Sezione “FAQ”**
    
- **Sezione “Newsletter / Lead capture”**
    
- **Sezione “Checkout / Carrello”**
    
- **Footer**
    

Quindi:

- i **componenti** sono i mattoncini (card, button, navbar, ecc.)
    
- i **macro blocchi** sono le **sezioni della pagina** che combinano quei mattoncini in qualcosa di più grande e con uno scopo preciso.
    

---

### **2. Esempio semplice: struttura di Codrind**

Sul tuo sito d’esempio (Codrind) la home ha una struttura molto pulita:

- **Hero**
    
    - titolo principale
        
    - sottotitolo / descrizione
        
    - uno o più bottoni (call to action)
        
- **Sezione blog / contenuti**
    
    - lista di articoli o aggiornamenti
        
- **Footer**
    
    - link legali
        
    - info sul sito
        
    - mini sezione newsletter
        

Se avessi un modello ad **abbonamento**, potresti aggiungere:

- **Sezione “Pricing”**
    
    - piani mensili / annuali
        
    - confronto tra piani
        
    - call to action “Abbonati”
        

Già con questi macro blocchi ottieni una home page chiara e professionale.

---

### **3. Esempio più complesso: Stripe**

Prendendo un sito grosso come Stripe (ma vale per un sacco di SaaS), la home è una sequenza di macro blocchi:

1. **Hero**
    
    - grande titolo che spiega il valore del prodotto
        
    - sottotitolo
        
    - 1–2 bottoni principali (es. _Inizia ora_, _Contatta vendite_)
        
    - spesso uno screenshot o illustrazione a destra
        
2. **Sezione “Clienti / Loghi famosi”**
    
    - loghi di aziende grandi (Deliveroo, Booking, ecc.)
        
    - serve a creare **credibilità**: “se lo usano loro, puoi fidarti anche tu”
        
3. **Sezione “Spiegazione del prodotto / Features”**
    
    - blocchi che spiegano a cosa serve il servizio
        
    - spesso organizzati in card con icone, titoli e paragrafi
        
4. **Sezione “Per sviluppatori” o “Per ruoli diversi”**
    
    - macro blocco dedicato a un target specifico (developer, marketing, finance…)
        
5. **Altre sezioni tematiche**
    
    - integrazioni
        
    - sicurezza
        
    - casi d’uso
        
    - testimonianze clienti
        
6. **Sezione FAQ**
    
    - di solito in forma di accordion
        
    - risponde alle obiezioni finali prima dell’acquisto
        
7. **Footer**
    
    - link legali
        
    - documentazione
        
    - risorse
        
    - social
        
    - info aziendali
        

Nella pagina **Pricing** di Stripe succede la stessa cosa:

- blocco con i **piani** (tabella / card con i prezzi)
    
- blocco con **FAQ specifiche sul prezzo**
    
- blocco con **link a documentazione e supporto**
    
- blocco finale “Contatta il commerciale”
    
- footer
    

---

### **4. Macro blocchi “standard” che vedrai ovunque**

#### **4.1. Hero section**

È la **prima sezione** subito sotto la navbar.  
È “l’eroe” della pagina: deve rispondere in 2 secondi a:

- Chi sei?
    
- Cosa offri?
    
- Perché dovrei continuare a leggere?
    

Di solito contiene:

- titolo grande
    
- sottotitolo
    
- 1–2 **CTA (call to action)**:
    
    - es. _Inizia gratis_, _Guarda il corso_, _Scarica l’app_
        
- spesso:
    
    - screenshot / mockup
        
    - illustrazione
        
    - badge tipo “Nuovo”, “Best seller”, ecc.
        

#### **4.2. Sezione “Features”**

Subito dopo la hero, spesso hai una sezione che spiega:

- cosa fa il prodotto / sito
    
- in cosa è diverso o migliore
    

Di solito è organizzata in:

- **3–6 card** con:
    
    - icona
        
    - titolo (feature)
        
    - breve testo
        

È quella che, concettualmente, nel tuo corso HTML/CSS corrisponde alle “feature boxes” con icona + titolo + descrizione.

#### **4.3. Sezione “Clienti / loghi”**

Serve per:

- mostrare loghi di aziende che usano il prodotto
    
- dare **prova sociale** immediata
    

Di solito è un blocco molto semplice:

- titolo tipo: “Scelto da…”
    
- griglia di loghi
    

#### **4.4. Sezione “Pricing”**

Macro blocco dedicato ai prezzi.

Tipicamente:

- da 2 a 4 **card di pricing**:
    
    - piano Free
        
    - piano Standard
        
    - piano Pro / Business
        
- ogni card contiene:
    
    - prezzo (mensile / annuale)
        
    - elenco di feature incluse
        
    - bottone principale (_Inizia_, _Abbonati_, ecc.)
        
- spesso c’è:
    
    - un badge tipo “Più scelto”
        
    - un toggle Mensile / Annuale
        

Può stare:

- sia nella **home page**
    
- sia in una **pagina separata** `/pricing`
    

Spesso vedi entrambe le cose (una versione riassunta in home, quella completa nella pagina dedicata).

#### **4.5. Sezione “Blog / Articoli / News”**

Quando il sito include contenuti editoriali, hai un blocco che mostra:

- le ultime **card di articolo**
    
    - immagine
        
    - categoria
        
    - titolo
        
    - estratto
        
    - link “Leggi di più”
        

Se il sito è un **blog puro**, questa sezione è praticamente la **home** stessa.

#### **4.6. Sezioni “Social proof” (testimonianze, recensioni)**

Possono apparire come:

- slider/carousel di recensioni
    
- card con foto, nome, ruolo, citazione
    

Servono a:

- far vedere che altre persone hanno già usato/provato il prodotto
    
- aumentare fiducia
    

#### **4.7. Sezione “Newsletter / Lead capture”**

È spesso un blocco con:

- titolo (“Rimani aggiornato…”)
    
- sottotitolo (“Iscriviti alla newsletter…”)
    
- input email + bottone
    

Può stare:

- a metà pagina
    
- verso il footer
    
- ripetuta in diverse pagine (home, blog, ecc.)
    

#### **4.8. Sezione “FAQ”**

Usa spesso l’**accordion**:

- elenco di domande
    
- clicchi e si apre la risposta
    

Posizionamento classico:

- subito dopo il pricing
    
- oppure verso fine pagina, prima del footer
    

#### **4.9. Sezione “Login / Registrazione”**

Può essere:

- una **pagina dedicata** (_login.html / register.html_)
    
- una **modale** che si apre sopra la pagina
    
- un blocco incorporato in una landing (es. form di registrazione nella hero)
    

Struttura tipica:

- titolo (“Accedi” / “Crea account”)
    
- 2–4 campi (email, password…)
    
- pulsante principale
    
- link secondari (“Password dimenticata?”, “Non hai un account?”)
    

#### **4.10. Checkout / Carrello**

Se vendi qualcosa (prodotto, corso, servizio):

- pagina dedicata con:
    
    - riepilogo ordine
        
    - dati di fatturazione
        
    - metodo di pagamento
        
    - eventuale campo “codice sconto”
        
    - bottone _Conferma e paga_
        

È un macro blocco critico per la UX, ma che lato HTML/CSS è comunque una combinazione di:

- form + card + riepilogo + CTA.
    

#### **4.11. Footer**

Bloccone finale della pagina, sempre presente.

Contiene spesso:

- logo in piccolo
    
- descrizione breve del brand
    
- sezioni linkate:
    
    - Prodotto
        
    - Azienda
        
    - Risorse
        
    - Legale (Privacy, Cookie, Termini)
        
- social
    
- info copyright
    

Può includere anche una piccola **newsletter** o un campo email.

---

### **5. Macro blocchi + componenti: come pensarla da dev**

Quando progetti un sito, la mentalità giusta è:

1. **Parti dai macro blocchi (sezioni)**
    
    - Hero
        
    - Features
        
    - Pricing
        
    - Blog
        
    - FAQ
        
    - Footer
        
2. **Dentro ogni sezione, scegli i componenti**
    
    - Hero → titolo + sottotitolo + CTA + immagine/card
        
    - Features → griglia di card
        
    - Pricing → card di pricing + bottoni
        
    - FAQ → accordion
        
    - Footer → liste di link + social + mini-text
        
3. **Poi pensi a stile e framework**
    
    - puro CSS
        
    - Bootstrap con i suoi componenti
        
    - Tailwind + libreria tipo Flowbite
        
    - mix di componenti custom
        

---

### **6. Cosa ti serve davvero portarti a casa**

Da questa lezione, quello che ti interessa fissare è:

- non pensare più a una pagina come:
    
    > “una roba piena di div e classi”
    
- ma come:
    
    > una **sequenza di macro blocchi**, ognuno con uno **scopo preciso**.
    

Quando farai:

- il tuo primo portfolio
    
- la landing per un progetto
    
- la home di un’app
    

ti conviene sempre chiederti:

1. Qual è la **Hero**?
    
2. Dove metto le **features**?
    
3. Ho bisogno di **pricing**?
    
4. Ho testimonianze / social proof?
    
5. Metto una **newsletter**?
    
6. Come strutturo **footer** e pagine interne?
    

Da “so usare CSS” passi a:

> “so **progettare** una pagina che abbia senso, sezionata in blocchi chiari e riutilizzabili”.

Ed è esattamente lo step mentale che questa sezione del corso vuole farti fare.