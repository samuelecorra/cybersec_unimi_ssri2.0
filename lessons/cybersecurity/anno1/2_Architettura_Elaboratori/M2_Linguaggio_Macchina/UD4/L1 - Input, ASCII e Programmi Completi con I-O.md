# **Lezione 1: Input, ASCII e Programmi Completi con I/O (pagg. 76–80)**

---

## **1. Il problema dell’Input nei programmi Assembly**

Fino ad ora i programmi:

- lavoravano solo con **dati già presenti in memoria**,
    
- oppure con **costanti scritte tramite `.FILL`**.
    

Ma un vero programma deve poter:

- **leggere dati dall’esterno**,
    
- interagire con l’utente.
    

Questo avviene tramite le **TRAP di input/output**.

---

## **2. Codifica dei caratteri: ASCII**

Tutti i caratteri digitati da tastiera:

- **non sono numeri in senso matematico**,
    
- ma **codici ASCII**.
    

Esempio:

- `'0'` → 48
    
- `'1'` → 49
    
- `'9'` → 57
    

Quindi quando si legge un carattere numerico dalla tastiera:

> **non si ottiene il numero 5, ma il codice ASCII del carattere `'5'` (53)**.

---

## **3. TRAP di input: lettura di un carattere**

Per leggere un carattere da tastiera si usa:

```
TRAP x20
```

Effetto:

- la CPU:
    
    - aspetta che l’utente prema un tasto,
        
    - legge il carattere,
        
    - lo memorizza nel registro **R0** in formato ASCII.
        

Formalmente:

```
R0 ← ASCII del carattere letto
```

---

## **4. TRAP di output: stampa di un carattere**

Per stampare un carattere:

```
TRAP x21
```

Effetto:

- stampa sullo schermo:
    
    - il carattere il cui **codice ASCII è contenuto in R0**.
        

---

## **5. TRAP per stampare una stringa**

Per stampare una stringa:

```
TRAP x22
```

Requisito fondamentale:

- **R0 deve contenere l’indirizzo della stringa**.
    

Funzionamento:

- legge un carattere alla volta,
    
- continua finché non trova lo **0 terminatore**,
    
- stampa ogni carattere sullo schermo.
    

---

## **6. Problema classico: trasformare un carattere in numero**

Se l’utente digita `'5'`, in **R0** arriva:

```
ASCII('5') = 53
```

Ma per fare calcoli numerici serve ottenere:

```
5
```

Quindi bisogna fare:

$$  
\text{numero} = \text{ASCII} - 48  
$$

In Assembly LC-2 questo si realizza con:

```
ADD R0, R0, #-48
```

Ora **R0 contiene davvero il valore numerico**.

---

## **7. Operazione inversa: da numero a carattere**

Se invece vogliamo stampare un numero:

- prima dobbiamo **trasformarlo in carattere**:
    

$$  
\text{ASCII} = \text{numero} + 48  
$$

In Assembly:

```
ADD R0, R0, #48
TRAP x21
```

---

## **8. Primo programma completo: lettura e stampa di un carattere**

Struttura concettuale:

```
TRAP x20   ; leggi un carattere
TRAP x21   ; ristampa lo stesso carattere
HALT
```

Questo programma:

- legge un tasto,
    
- lo stampa immediatamente,
    
- termina.
    

È il più semplice esempio completo di **I/O bidirezionale**.

---

## **9. Programma con conversione carattere → numero → carattere**

Logica completa:

1. leggo un carattere `'5'`
    
2. lo trasformo in numero `5`
    
3. lo uso per un calcolo
    
4. lo riconverto in carattere
    
5. lo stampo
    

Concettualmente:

```
TRAP x20        ; leggi carattere
ADD R0, R0, #-48  ; ASCII -> numero
ADD R0, R0, #1    ; esempio: incremento
ADD R0, R0, #48   ; numero -> ASCII
TRAP x21        ; stampa risultato
HALT
```

Se l’utente digita:

```
5
```

Il programma stampa:

```
6
```

---

## **10. Uso combinato di stringhe e input**

Un programma completo tipico ha questa struttura:

```
.ORIG x3000
    LEA R0, MSG1
    TRAP x22       ; stampa messaggio

    TRAP x20       ; leggi input

    ADD R0, R0, #-48
    ADD R0, R0, #1
    ADD R0, R0, #48

    TRAP x21       ; stampa risultato
    HALT

MSG1 .STRINGZ "Inserisci un numero: "
.END
```

Questo è a tutti gli effetti:

> un **programma interattivo completo**.

---

## **11. Differenza tra I/O Assembly e I/O nei linguaggi ad alto livello**

In C:

```c
scanf("%d", &x);
printf("%d", x);
```

In LC-2 invece:

- ogni singolo passaggio è **manuale**:
    
    - lettura del carattere,
        
    - conversione,
        
    - operazione,
        
    - riconversione,
        
    - stampa.
        

Questo rende evidente che:

> **l’I/O ad alto livello è solo un enorme strato di astrazione sopra queste primitive elementari.**

---

## **12. Significato conclusivo**

Ora sai:

- come funziona una CPU a livello strutturale,
    
- come si scrivono istruzioni macchina,
    
- come si gestisce la memoria,
    
- come funzionano:
    
    - salti,
        
    - sottoprogrammi,
        
    - stringhe,
        
    - array,
        
    - input/output,
        
    - conversioni ASCII.
        

Hai visto nascere **tutto ciò che userai poi in C, Java, Python, Assembly x86, ARM**.

---
