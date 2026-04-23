# **Lezione 4: Struttura e Set di Istruzioni della CPU LC-2**

---

## **1. Cenni storici sulla LC-2**

La **LC-2 (Little Computer 2)** è una **CPU didattica** progettata nei primi anni 2000 da un team di docenti del **Dipartimento di Ingegneria Informatica dell’Università della Virginia (UVA)**, in particolare dal **professor Yale N. Patt**, uno dei massimi esperti mondiali di architettura dei calcolatori.

È co-autore del testo di riferimento:

> _Introduction to Computing Systems: From Bits and Gates to C and Beyond_

Questo manuale è alla base della progettazione della **LC-2 e della LC-3** ed è utilizzato in **decine di corsi universitari nel mondo** per introdurre i principi fondamentali dell’architettura dei calcolatori.

Il grande vantaggio della LC-2 è quello di essere una **CPU estremamente semplificata** ma dotata di **ottimi strumenti di simulazione**, soprattutto in ambiente Windows.

---

## **2. Struttura interna della CPU LC-2**

Analizzando lo schema interno della LC-2 si osserva che **tutti i componenti fondamentali della CPU di Von Neumann sono presenti**:

- **Control Unit (CU)**
    
- **ALU**
    
- **Registri**
    
- **Bus dati, indirizzi e controllo**
    

Dallo schema si ricavano subito due informazioni fondamentali:

- la **dimensione dei dati è di 16 bit**,
    
- il **Program Counter è anch’esso a 16 bit**.
    

Questo significa che **l’intera architettura LC-2 è una macchina a 16 bit**.

---

## **3. La LC-2 come architettura RISC**

La LC-2 è un esempio perfetto di:

> **RISC – Reduced Instruction Set Computer**

cioè una CPU caratterizzata da:

- **numero ridotto di istruzioni macchina**,
    
- istruzioni **tutte della stessa lunghezza**,
    
- **decodifica semplice**,
    
- **esecuzione veloce**.
    

---

## **4. Opcode e numero di istruzioni**

Ogni istruzione LC-2 è lunga **16 bit**.

Di questi:

- i **primi 4 bit** sono riservati all’**opcode** (codice operativo),
    
- restano **12 bit per la specifica degli operandi**.
    

Con 4 bit di opcode si possono rappresentare:

$$  
2^4 = 16 \text{ istruzioni macchina}  
$$

Il modello LC-2 studiato è quindi una macchina con:

> **16 istruzioni macchina totali**

Tutte le istruzioni:

- hanno **lunghezza fissa di 16 bit**,
    
- occupano **esattamente una parola di memoria**.
    

---

## **5. Pochi modi di indirizzamento**

Per mantenere la semplicità tipica delle RISC, la LC-2 adotta **pochi modi di indirizzamento**, che sono quelli già studiati:

- immediato,
    
- diretto,
    
- indiretto,
    
- base + offset.
    

---

## **6. Conseguenze dell’architettura a 16 bit**

Dalla struttura della LC-2 derivano queste caratteristiche fondamentali:

### **6.1 Data Bus a 16 bit**

- ogni cella di memoria è larga **16 bit**,
    
- ogni trasferimento dati avviene su 16 bit.
    

---

### **6.2 Address Bus a 16 bit**

- lo spazio di indirizzamento è:
    

$$  
2^{16} = 65,536 \text{ celle}  
$$

---

### **6.3 General Purpose Registers**

I **registri GPR** sono:

- **8 registri**,
    
- indicizzati da:
    
    - R0, R1, R2, R3, R4, R5, R6, R7,
        
- servono **3 bit** per rappresentarli.
    

---

### **6.4 Condition Codes**

Il registro **CC (Condition Codes)** è composto da **3 bit**, che indicano:

- **N** → risultato negativo,
    
- **Z** → risultato nullo,
    
- **P** → risultato positivo.
    

Questi bit rappresentano **il segno dell’ultimo valore numerico scritto in un qualsiasi GPR**.

---

### **6.5 ALU minimale**

L’**ALU della LC-2** implementa solo le operazioni **strettamente indispensabili**:

- **ADD** (somma),
    
- **AND** (prodotto logico),
    
- **NOT** (negazione).
    

Non esiste l’operazione **OR** come istruzione diretta.

---

## **7. OR tramite teorema di De Morgan**

L’operazione OR viene realizzata **a livello software** sfruttando il **teorema di De Morgan**:

$$  
A \lor B = \neg(\neg A \land \neg B)  
$$

Questo mostra un concetto fondamentale:

> **il software può estendere le capacità dell’hardware**.

---

## **8. Limite degli operandi a 12 bit**

Ogni istruzione ha:

- 4 bit di opcode,
    
- 12 bit per gli operandi.
    

Questo significa che:

> **non è possibile rappresentare direttamente un indirizzo a 16 bit in un’istruzione immediata o diretta**.

Le RISC adottano quindi una strategia alternativa.

---

## **9. Indirizzamento a pagina corrente**

La LC-2 utilizza l’**indirizzamento a pagina corrente**.

L’indirizzo completo a 16 bit viene costruito concatenando:

- i **bit più significativi del Program Counter (PC)**,
    
- con l’**offset contenuto nell’istruzione**.
    

Formalmente:

$$  
\text{indirizzo a 16 bit} = PC_{\text{MSB}}\ \& \ \text{offset}  
$$

Questo permette di:

- indirizzare **celle appartenenti alla stessa pagina di memoria** dell’istruzione corrente.
    

---

## **10. Struttura delle pagine di memoria**

Ogni pagina contiene:

$$  
2^{12} = 4096 \text{ celle}  
$$

Poiché la memoria totale della LC-2 è:

$$  
2^{16} = 65,536  
$$

il numero di pagine è:

$$  
\frac{2^{16}}{2^{12}} = 2^4 = 16 \text{ pagine}  
$$

Ogni pagina è identificata dai **4 bit più significativi dell’indirizzo**.

---

## **11. Concetto di “pagina corrente”**

La **pagina corrente** è:

> la pagina in cui si trova l’istruzione puntata dal **Program Counter** nel momento dell’esecuzione.

Tutte le istruzioni che usano:

- indirizzamento diretto,
    
- indirizzamento indiretto,
    
- LEA,
    
- LD,
    

operano **per default all’interno della pagina corrente**, salvo i casi a doppio accesso.

---

## **12. Istruzione LEA – Load Effective Address**

L’istruzione **LEA** non carica un valore, ma:

> **carica un indirizzo a 16 bit in un registro GPR**.

Formato:

```
LEA DR, PCoffset9
```

Dove:

- **DR** → Destination Register (3 bit),
    
- **PCoffset9** → offset in complemento a 2 su 9 bit.
    

L’indirizzo viene costruito come:

$$  
\text{indirizzo} = PC_{\text{MSB}}\ \& \ PCoffset_9  
$$

---

## **13. Significato operativo della LEA**

La LEA è fondamentale perché permette di:

- costruire indirizzi a 16 bit,
    
- aggirando il limite dei 12 bit disponibili negli operandi,
    
- mantenendo la filosofia RISC.
    

Metafora concettuale:

- i **MSB del PC** sono il **numero di pagina del libro**,
    
- l’**offset** è la **riga all’interno della pagina**.
    

---

## **14. Pagina corrente e PC dinamico**

Poiché il **Program Counter avanza continuamente**, anche la **pagina corrente cambia durante l’esecuzione del programma**.

Questo rende:

- gli indirizzi **relativi e dinamici**,
    
- il codice **rilocabile** all’interno della memoria.
    

---
