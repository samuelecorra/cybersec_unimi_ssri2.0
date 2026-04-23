# **Lezione 1: Pseudo-istruzioni, Stringhe, Aree di Memoria e HALT (pagg. 63–75)**

---

## **1. Cosa sono le pseudo-istruzioni**

Le **pseudo-istruzioni** sono comandi:

- **non eseguiti dalla CPU**,
    
- interpretati **solo dall’assembler**,
    
- utilizzati per:
    
    - organizzare la memoria,
        
    - inizializzare dati,
        
    - riservare spazio,
        
    - facilitare la scrittura dei programmi.
        

Differenza fondamentale:

- **Istruzioni (ADD, LD, BR, …)** → eseguite dalla CPU
    
- **Pseudo-istruzioni (.ORIG, .FILL, …)** → eseguite dall’assembler
    

---

## **2. Direttiva .FILL – Richiamo operativo**

```
.FILL x000A
```

Effetto:

> nella cella di memoria corrente viene memorizzato **direttamente il valore indicato**.

Serve per:

- definire **costanti**,
    
- inizializzare **variabili statiche**,
    
- inserire dati numerici in memoria.
    

---

## **3. Direttiva .BLKW – Block of Words**

Formato:

```
.BLKW n
```

Significato:

> riserva **n celle consecutive di memoria**, inizializzandole a zero.

Esempio:

```
VETT .BLKW 10
```

Riserva **10 celle di memoria consecutive** per un vettore.

Utilizzo tipico:

- array,
    
- buffer,
    
- pile (stack),
    
- zone di lavoro.
    

---

## **4. Direttiva .STRINGZ – Stringhe terminate da zero**

Formato:

```
.STRINGZ "TESTO"
```

Effetto:

- ogni carattere viene memorizzato in una cella,
    
- **l’ultimo carattere è automaticamente seguito da uno 0 (terminatore)**.
    

Esempio:

```
MSG .STRINGZ "CIAO"
```

In memoria diventa:

```
'C'  'I'  'A'  'O'   0
```

Questa è la stessa logica delle **stringhe C**.

---

## **5. Perché il terminatore 0 è fondamentale**

Il carattere **0** serve per:

- sapere **quando termina una stringa**,
    
- permettere cicli del tipo:
    

```c
while (*p != 0) { … }
```

In Assembly LC-2 questo controllo viene fatto con:

- `LDR`
    
- aggiornamento dei **Condition Codes**
    
- `BRz` per uscire dal ciclo.
    

---

## **6. Istruzione HALT**

La **HALT** è una istruzione speciale che:

> **ferma definitivamente l’esecuzione del programma**.

Formalmente è una:

```
TRAP x25
```

Effetto:

- il controllo passa al sistema,
    
- il programma termina,
    
- la simulazione si arresta.
    

Ogni programma Assembly **ben scritto deve terminare con HALT**.

---

## **7. Organizzazione tipica di un programma Assembly LC-2**

Struttura standard:

```
.ORIG x3000
    istruzioni
    HALT
    dati
.END
```

Dove:

- la parte alta è **codice eseguibile**,
    
- la parte bassa è **zona dati**.
    

Questo rispecchia perfettamente il modello di **Von Neumann**.

---

## **8. Primo programma completo con stringa**

Esempio concettuale:

```
.ORIG x3000
    LEA R0, MSG
    TRAP x22
    HALT
MSG .STRINGZ "HELLO"
.END
```

Significato:

- carica in R0 l’indirizzo della stringa,
    
- invia il controllo al sistema operativo,
    
- la routine di TRAP stampa i caratteri fino allo 0,
    
- il programma termina.
    

---

## **9. Uso combinato di .BLKW e registri**

Esempio tipico per un vettore:

```
VETT .BLKW 20
```

Poi nel programma:

- un registro punta a `VETT`,
    
- viene incrementato a ogni iterazione,
    
- si accede con `LDR` e `STR`.
    

Questo realizza:

> la **scansione sequenziale di un array**.

---

## **10. Inizializzazione di array con .FILL**

Esempio:

```
V1 .FILL 3
V2 .FILL 7
V3 .FILL 1
V4 .FILL 0
```

Qui:

- si costruisce manualmente un vettore,
    
- lo 0 finale funge da **terminatore**.
    

---

## **11. Gestione della memoria come spazio unico**

In LC-2:

- **non esiste separazione tra memoria dati e memoria codice**,
    
- tutto è visto come:
    

> **un’unica sequenza di celle indirizzabili**

Questo implica che:

- un errore di indirizzo può:
    
    - sovrascrivere istruzioni,
        
    - distruggere dati,
        
    - causare salti imprevisti.
        

È una versione **didattica ma realistica** del rischio dei linguaggi a basso livello.

---

## **12. Simulazione ed esecuzione passo-passo**

Durante la simulazione si osservano:

- avanzamento del **PC**,
    
- aggiornamento dell’**IR**,
    
- variazione dei **GPR**,
    
- modifica dei **CC**,
    
- accessi ripetuti a memoria.
    

Questo permette di:

> “vedere vivere” il programma a livello hardware.

---

## **13. Collegamento concettuale con il C**

Tutti i costrutti del C nascono qui:

|Assembly LC-2|C|
|---|---|
|BR + CC|if|
|JMP R7|return|
|JSR|chiamata di funzione|
|LDR/STR|accesso a variabili|
|.BLKW|array|
|.STRINGZ|stringa|

---

## **14. Significato formativo del blocco 63–75**

Con queste pagine lo studente:

- capisce cos’è realmente:
    
    - una variabile,
        
    - un array,
        
    - una stringa,
        
    - una funzione,
        
- vede nascere:
    
    - la gestione manuale della memoria,
        
    - il concetto di puntatore,
        
    - l’I/O a basso livello,
        
- comprende che:
    

> **tutto l’alto livello è costruito su queste primitive elementari.**

---
