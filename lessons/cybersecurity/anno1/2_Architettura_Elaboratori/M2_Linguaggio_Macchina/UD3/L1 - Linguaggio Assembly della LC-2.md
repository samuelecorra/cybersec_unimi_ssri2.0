# **Lezione 1: Linguaggio Assembly della LC-2 (pagine 58–62)**

---

## **1. Perché non si programma direttamente in linguaggio macchina**

Il **linguaggio macchina** della LC-2 è costituito esclusivamente da:

- stringhe di **16 bit**,
    
- scritte in **binario o esadecimale**.
    

Esempio concettuale:

```
0011 000 010 000101
```

Questo tipo di programmazione è:

- difficile da leggere,
    
- difficile da scrivere,
    
- soggetta a errori,
    
- praticamente impossibile da manutenere.
    

Per questo motivo si introduce un linguaggio **simbolico**, più vicino al ragionamento umano:

> **il linguaggio Assembly**.

---

## **2. Cos’è il linguaggio Assembly**

Il **linguaggio Assembly** è:

- una rappresentazione **simbolica del linguaggio macchina**,
    
- ogni istruzione Assembly corrisponde a **una sola istruzione macchina**,
    
- il legame tra Assembly e macchina è **uno a uno**.
    

Esempio:

```
ADD R1, R2, R3
```

rappresenta esattamente **una sola istruzione binaria da 16 bit**.

---

## **3. Il ruolo dell’Assembler**

L’**assembler** è un programma che:

> **traduce il codice Assembly in linguaggio macchina eseguibile dalla CPU**.

Formalmente:

```
Assembly  →  Assembler  →  Codice Macchina
```

L’assembler:

- elimina i simboli,
    
- sostituisce le etichette con indirizzi,
    
- converte gli opcode in codici binari,
    
- produce il file eseguibile in memoria.
    

---

## **4. Struttura generale di un programma Assembly LC-2**

Un programma Assembly LC-2 ha una struttura **rigida e standardizzata**, composta da:

- una **direttiva di inizio**,
    
- una sequenza di **istruzioni e dati**,
    
- una **direttiva di fine**.
    

Schema generale:

```
.ORIG x3000
    istruzioni
    dati
.END
```

---

## **5. Direttiva .ORIG**

La direttiva:

```
.ORIG x3000
```

significa:

> il programma deve essere **caricato in memoria a partire dall’indirizzo x3000**.

Da questo momento in poi:

- la **prima istruzione** andrà in x3000,
    
- la seconda in x3001,
    
- poi x3002, x3003, ecc.
    

Serve per:

- stabilire la **posizione iniziale del programma**,
    
- rendere prevedibile il contenuto della memoria.
    

---

## **6. Direttiva .END**

La direttiva:

```
.END
```

indica:

> la **fine fisica del programma Assembly**.

Tutto ciò che viene scritto dopo **non viene tradotto dall’assembler**.

---

## **7. Etichette (LABEL)**

Un’**etichetta** è un:

> nome simbolico associato a un **indirizzo di memoria**.

Esempio:

```
START   ADD R1, R1, #1
```

Qui:

- `START` è un’etichetta,
    
- rappresenta l’indirizzo in cui si trova l’istruzione `ADD`.
    

Le etichette servono per:

- salti con `BR`,
    
- chiamate con `JSR`,
    
- riferimenti a dati in memoria (`LD`, `ST`).
    

---

## **8. Vantaggio fondamentale delle etichette**

Senza etichette, dovresti scrivere:

```
BR x3012
```

Con le etichette puoi scrivere:

```
BR LOOP
```

E sarà l’assembler a:

> calcolare automaticamente l’offset corretto.

Questo rende il codice:

- **indipendente dagli indirizzi assoluti**,
    
- più leggibile,
    
- più sicuro.
    

---

## **9. Struttura formale di una riga Assembly**

Ogni riga di codice Assembly segue questa struttura:

```
[LABEL]   OPCODE   OPERANDI   ; COMMENTO
```

Dove:

- **LABEL** → opzionale,
    
- **OPCODE** → obbligatorio,
    
- **OPERANDI** → dipendono dall’istruzione,
    
- **COMMENTO** → tutto ciò che segue il carattere `;` viene ignorato dall’assembler.
    

---

## **10. Commenti**

Il commento serve esclusivamente per:

- chiarire cosa fa l’istruzione,
    
- rendere il programma leggibile all’essere umano.
    

Esempio:

```
ADD R1, R1, #1   ; incremento del contatore
```

Per la CPU e per l’assembler, il commento **non esiste**.

---

## **11. Istruzioni vs Direttive**

È fondamentale distinguere tra:

- **istruzioni vere della CPU** (ADD, AND, LD, BR, ecc.)
    
- **direttive dell’assembler** (.ORIG, .END, .FILL, ecc.)
    

Differenza concettuale:

- le **istruzioni** vengono eseguite dalla CPU,
    
- le **direttive** vengono usate **solo durante la traduzione Assembly → macchina**.
    

---

## **12. Direttiva .FILL**

La direttiva:

```
.FILL x0005
```

ordina all’assembler di:

> inserire direttamente in memoria il valore specificato.

Non è un’istruzione eseguita dalla CPU, ma un modo per:

- inizializzare una cella di memoria,
    
- definire una costante,
    
- creare una variabile statica.
    

---

## **13. Programma Assembly come miscela di codice e dati**

In Assembly LC-2:

- **istruzioni e dati stanno nella stessa memoria**,
    
- esattamente come nel modello di Von Neumann.
    

Esempio tipico:

```
.ORIG x3000
    LD R1, VAL
    ADD R1, R1, #1
    ST R1, VAL
    HALT
VAL .FILL x0003
.END
```

Qui:

- le prime righe sono **istruzioni**,
    
- `VAL` è un **dato in memoria**.
    

---

## **14. Differenza tra Assembly e linguaggi ad alto livello**

A differenza del C, Java o Python:

- non esistono:
    
    - `while`,
        
    - `for`,
        
    - `if`,
        
- non esistono:
    
    - funzioni vere,
        
    - variabili automatiche,
        
    - tipi di dato complessi.
        

Tutto viene costruito usando solo:

- registri,
    
- memoria,
    
- salti,
    
- somme,
    
- confronti indiretti tramite **Condition Codes**.
    

---

## **15. Significato didattico dell’Assembly LC-2**

Il linguaggio Assembly LC-2 serve a:

- capire **come ragiona davvero una CPU**,
    
- vedere nascere:
    
    - i cicli,
        
    - le condizioni,
        
    - le variabili,
        
    - gli array,
        
- comprendere cosa fanno realmente:
    
    - compilatore,
        
    - linker,
        
    - loader,
        
    - sistema operativo.
        

È il **ponte diretto tra hardware e software**.

---
