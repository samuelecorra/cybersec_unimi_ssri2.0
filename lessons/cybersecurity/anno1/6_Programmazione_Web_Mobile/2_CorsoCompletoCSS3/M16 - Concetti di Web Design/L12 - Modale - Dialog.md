## **Lezione 12 – Modale / Dialog**

### **1. Cos’è una Modale (o “Dialog”)**

La **modale** (o _dialog_ nel linguaggio Material Design) è una finestra che compare sopra il contenuto principale del sito/app e **blocca l’interazione** con tutto il resto finché l’utente non compie un’azione.

Altri nomi che sentirai:

- **popup** (termine più generico)
    
- **modal window**
    
- **dialog box**
    
- **overlay modal**
    

### **Caratteristiche fondamentali**

- oscura lo sfondo con un overlay (di solito nero trasparente o sfocato)
    
- porta l’attenzione dell’utente su un’azione critica
    
- richiede una scelta: _Accetta_, _Conferma_, _Annulla_, _Esci_, ecc.
    
- impedisce ogni altra interazione fino alla chiusura
    

Quando compare, è come se ti dicesse:  
**“Fermati. Prima di continuare devi rispondere a questa cosa.”**

---

## **2. Differenza tra Modale e Dialog**

È quasi solo **una differenza terminologica**:

- Nel web (HTML, Bootstrap, librerie generiche): **Modale / Modal**
    
- Nel mobile, soprattutto nel mondo Android/Material Design: **Dialog**
    

La struttura è la stessa, cambia solo il naming ufficiale.

---

## **3. Dove le vedi ogni giorno**

Le modali sono ovunque. Qualche esempio:

### **Cookie Banner (versione modale)**

Prima di usare un sito:  
“Accetti i cookie?”  
→ Modale _bloccante_ (non puoi fare nulla finché non scegli)

### **Login scaduto**

Stavi leggendo un articolo, il token scade →  
compare una modale: “Devi effettuare nuovamente il login”.

### **Conferme importanti**

- “Vuoi eliminare questo elemento?”
    
- “Vuoi uscire senza salvare?”
    
- “Vuoi procedere al pagamento?”
    

### **Onboarding/collegamento account**

Collegare un wallet web3, un’app esterna, un provider OAuth:  
la modale si apre al centro, copre tutto, chiede conferma.

---

## **4. Struttura tipica di una Modale**

Una modale standard contiene sempre:

1. **Overlay** – lo sfondo opacizzato
    
2. **Contenitore centrale** – la finestra vera e propria
    
3. **Titolo** – l’argomento
    
4. **Testo** – istruzioni, spiegazioni
    
5. **Azioni** – pulsanti (uno o più, es. _Conferma_ / _Annulla_)
    

Esempio generico:

```
+-------------------------------+
|  Titolo della modale          |
|  Testo esplicativo...         |
|                               |
|  [Conferma]   [Annulla]       |
+-------------------------------+
```

---

## **5. Comportamento UX**

La modale:

- ha priorità **massima** rispetto al resto dell’interfaccia
    
- deve essere usata **solo quando necessario**
    
- deve essere chiara e breve
    
- deve avere pulsanti comprensibili (“Elimina”, “Annulla”, “Continua”, non “OK” generico)
    

Cattiva UX: modali inutili che interrompono il flusso.  
Buona UX: modali solo per decisioni importanti.

---

## **6. Nei framework**

### **Bootstrap**

Ha una modale pronta all’uso con JavaScript incluso.  
Il design è basilare, un po’ datato, ma funzionale e stabile.

### **Flowbite (Tailwind)**

Offre modali moderne con:

- classi Tailwind
    
- overlay
    
- animazioni
    
- icone
    
- layout a colonna o full-screen
    

Molto più aggiornate esteticamente rispetto a Bootstrap.

### **Material Design**

Nel mondo Android sono chiamate **Dialog** e seguono linee guida precise.

Tipologie:

- Alert Dialog
    
- Confirmation Dialog
    
- Simple Dialog
    
- Full-screen Dialog
    

Stesse logiche, naming diverso.

---

## **7. Cosa devi ricordare davvero**

- **Modale** = web
    
- **Dialog** = mobile / Android
    
- concetto identico: finestra sovrapposta che richiede un’azione
    
- blocca lo sfondo
    
- contiene titolo, testo e pulsanti
    
- da usare con parsimonia
    
- è fondamentale per UX, sicurezza e conferme importanti
    
