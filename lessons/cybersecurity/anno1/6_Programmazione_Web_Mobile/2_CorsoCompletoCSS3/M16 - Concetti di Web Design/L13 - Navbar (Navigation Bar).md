## **Lezione 13 – Navbar (Navigation Bar)**

### **1. Cos’è una Navbar**

La **navbar** è la barra di navigazione principale del sito.  
È l’elemento che _tutti_ gli utenti vedono per primo e che usano per orientarsi.

È composta generalmente da:

- **logo** (quasi sempre cliccabile → Home)
    
- **menu di navigazione**
    
- **dropdown** per sezioni complesse
    
- **casella di ricerca**
    
- **icone utili** (carrello, notifiche, profilo)
    
- **azioni rapide** (accedi / registrati)
    
- **tema** (dark/light mode)
    
- **lingua** (per i siti multilingua)
    

La navbar è **il componente più importante** dell’interfaccia, perché guida l’utente in ogni pagina del sito.

---

## **2. Dov’è e come si riconosce**

Sempre **in alto** alla pagina (header).  
Visivamente, è una barra orizzontale che contiene il menu principale e gli elementi di interazione immediata.

Esempi comuni:

- la navbar di _Flowbite_
    
- quella di _Bootstrap_
    
- la navbar del tuo _CoderInd_ (logo + corsi + login + carrello)
    

---

## **3. Cosa può contenere (elementi standard)**

### **Logo**

Elemento essenziale.  
Funzione principale: **link alla homepage**.

### **Menu**

Una lista di link principali: Home, Corsi, Contatti, Blog, ecc.

Può essere:

- statico
    
- collapsibile (mobile)
    
- a dropdown (per sezioni complesse)
    

### **Dropdown**

Utilizzato quando una singola voce apre un sottomenu:  
Corsi → HTML, CSS, JavaScript, Java…

### **Ricerca**

Molto comune su:

- e-commerce
    
- blog
    
- documentazioni
    
- piattaforme educative
    

### **Pulsanti di autenticazione**

- Accedi
    
- Registrati
    
- Profilo
    
- Logout
    

### **Icone**

- carrello
    
- notifiche (badge)
    
- preferiti
    
- impostazioni
    

### **Dark/Light Mode**

Il classico toggle del tema.  
Comune negli ultimi 5 anni, ormai quasi obbligatorio.

### **Lingua**

Componente fondamentale nei siti internazionali.

---

## **4. Navbar in Bootstrap vs Tailwind (Flowbite)**

### **In Bootstrap**

Hai componenti già pronti:

- navbar
    
- dropdown
    
- collapse per mobile
    

Tutto è già funzionante **con JavaScript incluso**.  
Copi il codice dalla documentazione e parte subito.

### **In Flowbite (Tailwind)**

Hai la bellezza dello stile moderno + classi tailwind.  
E soprattutto: molti componenti **hanno già JavaScript incluso**, come dropdown e collapsible navigation.

Questo è un vantaggio enorme, perché fare un dropdown da zero in JavaScript richiede:

- logiche di click
    
- gestione del focus
    
- accessibility (ARIA)
    
- animazioni
    
- chiusura quando clicchi fuori
    

Per chi inizia, avere tutto già pronto vale oro.

---

## **5. Navbar e design moderno**

Il trend attuale delle navbar è:

- **minimalismo**
    
- **sfondi traslucidi o a tinta piena**
    
- **icons + label**
    
- **dark mode support**
    
- **posizione fixed-top**
    
- **transizioni fluide quando l'utente scrolla**
    

Gli annunci in alto (banner sopra la navbar) sono diventati molto comuni negli ultimi anni:

- saldi
    
- nuove funzionalità
    
- avviso importante
    
- link rapidi
    

Sono chiamati **announcement bar** e sono ormai uno standard.

Esempio: il tuo _coderind_ usa proprio questo pattern.

---

## **6. Perché la Navbar è fondamentale**

La navbar:

- guida l’utente
    
- dà struttura e ordine al sito
    
- comunica branding (logo, colori, stile)
    
- influenza la UX più di ogni altro componente
    
- è uno degli elementi più complessi da rendere responsive
    

È un componente _semplice da capire_, ma **tecnicamente molto ricco**.

---

## **7. Da ricordare**

- La navbar è l’header del sito.
    
- Contiene logo, menu, dropdown, ricerca, icone, login…
    
- È quasi sempre sticky/fixed per restare visibile.
    
- Il design deve essere pulito: è la prima cosa che un utente giudica.
    
- Flowbite e Bootstrap ti danno versioni già complete, inclusa la logica JS.
    
- È un componente che userai SEMPRE in qualsiasi progetto web.
    

---

## **Prossima lezione**

Passiamo ora alle **Tabs**, il componente che permette di cambiare sezione restando nella stessa pagina.