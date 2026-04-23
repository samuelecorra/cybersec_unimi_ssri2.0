# **Lezione 3: Esempi di Programmi in Linguaggio Macchina LC-2 (pagg. 45–57)**

---

## **1. Obiettivo della sezione**

Dopo aver studiato:

- struttura della CPU LC-2,
    
- ISA completa,
    
- modi di indirizzamento,
    
- istruzioni operative, di trasferimento e di controllo,
    

lo scopo di questa lezione è:

> **passare dalla teoria alla pratica**, osservando come un programma in linguaggio macchina viene **eseguito realmente dalla CPU**, istruzione dopo istruzione.

---

## **2. Impostazione del primo esempio**

Le istruzioni macchina sono:

- collocate **in memoria in modo sequenziale**,
    
- a partire dall’indirizzo **x30F6**,
    
- scritte in **notazione esadecimale** per compattezza.
    

L’uso della notazione HEX è giustificato dal fatto che:

- ogni istruzione è lunga **16 bit**,
    
- quindi è rappresentabile con **4 cifre esadecimali**.
    

---

## **3. Uso della LEA con abuso di notazione**

Nel primo esempio compare:

```
LEA R1, x30F6
```

Formalmente LEA dovrebbe essere scritta con:

```
LEA DR, PCoffset9
```

Ma viene usato un **abuso di notazione didattico**, perché:

- l’indirizzo **x30F6** è nella **stessa pagina del PC**,
    
- quindi la concatenazione:
    

$$  
PC_{\text{MSB}} \ \& \ PCoffset_9  
$$

produce comunque **lo stesso risultato finale**.

Effetto:

> in **R1** viene caricato **l’indirizzo x30F6**.

---

## **4. ADD con immediato e sign-extension**

Successivamente:

```
ADD R2, R1, x000E
```

L’immediato:

- è su **5 bit**,
    
- viene **esteso con segno a 16 bit**,
    
- quindi:
    

```
x000E = 14 decimale
```

L’operazione eseguita è:

$$  
R2 = R1 + 14  
$$

Il risultato viene memorizzato in **R2**.

---

## **5. ST – Scrittura diretta in memoria**

Segue:

```
ST R2, x30F4
```

Che significa:

> scrivere **il contenuto di R2 nella cella di memoria x30F4**.

Qui si usa:

- **indirizzamento diretto a pagina corrente**.
    

---

## **6. AND per azzerare un registro**

Poi troviamo:

```
AND R2, R2, 0
```

Il risultato è sempre:

$$  
R2 = 0  
$$

Questo è il metodo standard in assembly per:

> **azzerare un registro in modo deterministico**.

---

## **7. ADD per inizializzazione dell’accumulatore**

Successivamente:

```
ADD R2, R2, 5
```

Dato che **R2 era stato azzerato**, ora:

$$  
R2 = 5  
$$

In questo modo:

> **R2 viene inizializzato manualmente come accumulatore**.

---

## **8. STR – Store con Base + Offset**

Segue l’istruzione:

```
STR R2, R1, x000E
```

Qui:

- BaseR = R1 = x30F6
    
- Offset = 14
    

L’indirizzo effettivo è:

$$  
x30F6 + 14 = x3104  
$$

Quindi:

> il contenuto di **R2 viene scritto in memoria in una zona FUORI dalla pagina corrente**.

Questo è uno degli aspetti più potenti dell’indirizzamento **Base + Offset**.

---

## **9. Introduzione al secondo grande esempio: somma di un vettore**

A questo punto la lezione introduce un **programma più importante**, che:

- scorre un **vettore monodimensionale di numeri in memoria**,
    
- **somma tutti gli elementi**,
    
- **termina quando incontra uno zero** (valore terminatore).
    

Formalmente è l’equivalente logico di:

```c
while (array[i] != 0) {
    somma += array[i];
    i++;
}
```

---

## **10. Concetto di puntatore al vettore**

Nel programma:

- un registro contiene **il puntatore al vettore**,
    
- a ogni iterazione viene **incrementato di 1**,
    
- quindi passa all’elemento successivo.
    

Questo realizza **lo scorrimento sequenziale in memoria**.

---

## **11. Concetto di accumulatore**

Un secondo registro viene usato come:

> **accumulatore della somma parziale**

A ogni ciclo:

$$  
\text{accumulatore} = \text{accumulatore} + \text{valore corrente}  
$$

---

## **12. Uso del valore terminatore**

Nel vettore è presente uno **zero finale**.

Quando l’istruzione:

```
LDR Rx, BaseR, index
```

carica:

$$  
Rx = 0  
$$

si attiva il flag:

- **Z = 1** nei Condition Codes.
    

A questo punto:

```
BRz FINISH
```

permette:

> l’**uscita immediata dal ciclo**.

---

## **13. Scrittura del risultato finale**

Quando il ciclo termina:

- il puntatore è arrivato alla cella del terminatore,
    
- l’accumulatore contiene **la somma completa**,
    

quindi viene eseguita:

```
ST accumulatore, RISULTATO
```

che copia il valore finale in una **cella di memoria dedicata al risultato**.

---

## **14. Stato della CPU durante l’esecuzione**

Durante l’esecuzione passo-passo si osservano:

- **PC** che avanza a ogni istruzione,
    
- **IR** che si aggiorna con la nuova istruzione,
    
- **R2** che cresce progressivamente,
    
- **CC** che vengono aggiornati a ogni operazione aritmetica,
    
- **accessi ripetuti alla memoria** per leggere il vettore.
    

---

## **15. Stato finale del programma**

Alla fine:

- il puntatore punta **alla cella del terminatore**,
    
- l’accumulatore ha sommato **tutti i valori non nulli del vettore**,
    
- il risultato è **correttamente scritto in memoria**.
    

---

## **16. Significato didattico della lezione**

Questa sezione mostra concretamente che:

- la LC-2 **è una macchina realmente programmabile**,
    
- con poche istruzioni è possibile costruire:
    
    - cicli,
        
    - contatori,
        
    - accumulatori,
        
    - scansioni di array,
        
- tutte le strutture del C nascono qui:
    
    - `while`,
        
    - `for`,
        
    - `if`,
        
    - `array`,
        
    - `variabili`.
        

---
