## **Lezione – Tabs (Penultimo componente)**

### **1. Che cosa sono le Tabs**

Le **tabs** sono un componente di navigazione interno alla _stessa pagina_.  
Servono per cambiare **contenuto** senza cambiare URL o ricaricare la pagina.

In pratica:

- in **alto** ci sono delle etichette (tab 1, tab 2, tab 3…)
    
- clicchi una tab → cambia solo il contenuto **sotto**
    
- la pagina **resta la stessa**
    

È esattamente lo stesso meccanismo che usa il **browser** qui sopra:  
le tab del browser non cambiano finestra, cambiano contenuto, restando nel contesto corrente.

---

## **2. Dove le hai già viste (senza saperlo)**

Le tabs sono ovunque:

- profili Instagram → “Post”, “Reel”, “Taggati”
    
- YouTube Studio → “Contenuti”, “Analytics”, “Commenti”
    
- documentazioni (Tailwind, Flowbite, Bootstrap)
    
- pannelli admin (dashboard)
    
- pagine account (profilo / sicurezza / metodi di pagamento)
    

Sono un pattern _storico_: esistono da decenni e continuano a essere utili.

---

## **3. Come si riconoscono**

Una tab è sempre composta da:

1. **Barra superiore di navigazione interna**
    
    - etichette cliccabili
        
    - evidenziazione della tab attiva (bordo, underline, sfondo)
        
2. **Contenuto associato alla tab attiva**
    
    - cambia dinamicamente
        
    - non carica una nuova pagina
        

Visivamente possono essere:

- con **bordo attorno**
    
- con **underline**
    
- in stile **pulsanti**
    
- versioni minimal con testo e highlight
    
- più elaborate con icone
    

Qualunque design scegliate, la logica resta identica.

---

## **4. Esempi concreti**

### **Esempio Bootstrap**

- barra di tab
    
- contenuti sotto associati a ciascuna tab
    

### **Esempio Flowbite (Tailwind)**

- tab moderne, con JS già incluso
    
- evidenziazione chiara della tab attiva
    
- contenuto che cambia fluido
    

### **Esempio sul tuo sito**

Se domani volessi dividere la pagina di un corso in:

- **Descrizione**
    
- **Programma**
    
- **Recensioni**
    
- **FAQ**
    

le tabs sarebbero perfette.

---

## **5. Quando usarle e quando evitarle**

### **Usale quando:**

- devi separare contenuti correlati **senza creare più pagine**
    
- l’utente deve confrontare rapidamente sezioni diverse
    
- la pagina sarebbe troppo lunga con tutto il contenuto visibile
    

### **Evita le tabs se:**

- i contenuti non sono correlati
    
- ogni sezione meriterebbe una pagina dedicata
    
- rischiano di complicare l’interfaccia senza motivo
    

Le tabs inserite “tanto per” sono un classico errore dei principianti.

---

## **6. UX: perché funzionano**

Le tabs:

- riducono scroll inutile
    
- rendono i contenuti immediatamente raggiungibili
    
- organizzano la pagina in blocchi logici
    
- sono familiari all’utente (pattern intuitivo)
    

Sono uno dei componenti più stabili della UI/UX moderna.

---

## **7. In sintesi**

- Le tabs sono un **mini-menu interno** che non ricarica la pagina.
    
- Servono per mostrare contenuti correlati in modo ordinato.
    
- Sono facili da implementare con qualsiasi framework.
    
- Se usate male, sono ingombranti e inutili.
    
- Se usate bene, migliorano enormemente la UX.
    

---

## **Prossima lezione**

**La paginazione (pagination)** — uno degli elementi più usati nei siti web con liste, pagine multiple e grandi quantità di dati.