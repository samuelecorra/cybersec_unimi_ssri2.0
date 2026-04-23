## **Lezione 10 – Il Collapse**

### **1. Cos’è il Collapse**

Il **collapse** è un componente semplicissimo:  
un blocco di contenuto **nascosto**, che compare quando l’utente compie un’azione (di solito cliccare un bottone).

Non ha:

- titolo obbligatorio
    
- struttura complessa
    
- più sezioni come l’accordion
    

È _semplicemente_ un contenitore che si espande e si richiude.

Esempio concettuale:

- clicco un bottone → compare del testo
    
- riclicco → si richiude
    

Fine.  
È letteralmente un "apri/chiudi" su un singolo blocco.

---

## **2. Differenza tra Collapse e Accordion**

È molto importante non confonderli.

### **Accordion**

- È un _insieme_ di sezioni
    
- Ogni sezione ha **titolo + contenuto**
    
- Apri una sezione, ne chiudi un’altra
    
- Ha una struttura visiva precisa
    
- È progettato per FAQ, liste di informazioni, gruppi di domande
    

Esempio:

```
➤ Domanda 1
   Risposta...

➤ Domanda 2
   Risposta...
```

### **Collapse**

- Può essere anche **uno solo**
    
- Non richiede titoli
    
- Non richiede una struttura predefinita
    
- È un blocco generico nascosto
    
- Si attiva tramite un bottone, un link o un evento
    

Esempio:

```
[Bottone]
(click)
compare una sezione di testo
```

Semplice, minimale.

---

## **3. Dove si trova**

### **In Bootstrap**

È presente come componente ufficiale.

Funziona con:

- un trigger (button o link)
    
- un contenitore nascosto con classi Bootstrap
    
- gestione dell'animazione tramite JavaScript incluso nel framework
    

Molto facile da implementare, utile in contesti rapidi.

---

## **4. In Material Design?**

Praticamente **non esiste**.

Google non ha mai dato particolare importanza al collapse come pattern separato,  
perché Material Design lavora più su:

- **expansion panels** (che però sono accordion veri e propri)
    
- liste espandibili
    
- scaffold moderni
    
- animazioni di container
    

Non c’è un componente “collapse” puro e singolo come quello di Bootstrap.

Questo già ti dice molto: è un pattern **minore** e non fondamentale.

---

## **5. Perché esiste ancora?**

Perché è utile quando:

- vuoi nascondere pezzi di testo secondari
    
- vuoi far comparire una mini sezione senza costruire un accordion
    
- stai lavorando con Bootstrap e vuoi mantenere coerenza
    
- serve una soluzione veloce e plug-and-play
    

Non è un componente essenziale del web moderno, ma è un “utility component”.

---

## **6. Con Tailwind**

Non esiste un collapse nativo, perché Tailwind non ha componenti.

Per ottenere un collapse devi usare:

- JavaScript personalizzato  
    oppure
    
- una libreria come Flowbite (che fornisce i collapse pronti)
    

Esempio Flowbite:

- pulsante trigger
    
- div nascosto con classi `hidden` / `block`
    
- animazioni Tailwind per transizioni
    

---

## **Conclusione**

Il **collapse** è:

- una versione _ridotta_ dell’accordion
    
- molto più semplice
    
- molto meno usato nel design moderno
    
- utile solo come “mini espansione” senza struttura complessa
    

### **In breve:**

- **Accordion →** tante sezioni con titoli
    
- **Collapse →** una singola sezione nascosta che si apre/chiude
    

---

## **Prossima lezione**

Passiamo ora ai **Dropdown**, uno dei componenti più utilizzati nella navigazione web.