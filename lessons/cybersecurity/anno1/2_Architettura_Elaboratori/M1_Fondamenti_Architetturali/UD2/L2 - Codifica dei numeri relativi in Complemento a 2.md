## ***Lezione 2: Codifica dei numeri relativi in Complemento a 2***

---

## **1. Il problema fondamentale: rappresentare numeri negativi in binario**

Fino a questo punto abbiamo rappresentato:

- numeri **interi positivi** in binario
    
- usando **notazione posizionale**
    

Ma un computer deve gestire anche:

- temperature sotto zero
    
- conti bancari in rosso
    
- coordinate negative
    
- spostamenti verso sinistra
    
- differenze tra quantità
    

Quindi servono **numeri con segno**.

Il primo tentativo naturale è il sistema **modulo e segno**:

- 1 bit per il segno
    
- $b-1$ bit per il valore assoluto
    

Ma questo sistema ha **due difetti strutturali gravissimi**:

1. **Doppia rappresentazione dello zero**  
    +0 e −0 sono codifiche diverse → ambiguità matematica.
    
2. **Somma e sottrazione NON sono uniformi**  
    Serve controllare i segni:
    
    - concordi → somma
        
    - discordi → sottrazione  
        → servono **branch logici**, confronti, circuiti più complessi.
        

Questo è **inaccettabile per l’hardware**, che deve essere:

- semplice
    
- veloce
    
- uniforme
    
- parallelo
    

---

## **2. La soluzione definitiva: il Complemento a 2**

### **Definizione operativa**

Dato un numero binario $x$ su $b$ bit, il suo **negativo in complemento a 2** si ottiene così:

1. **Inverti tutti i bit** (complemento a 1)
    
2. **Aggiungi 1**
    

Formalmente:

$$  
-x = \overline{x} + 1  
$$

Questa non è una “regoletta tecnica”:  
è l’**implementazione hardware del negativo matematico in aritmetica modulare**.

---

## **3. Fondamento matematico: aritmetica modulare**

Con $b$ bit, i registri lavorano **modulo $2^b$**:

$$  
\mathbb{Z}_{2^b} = {0,1,2,\dots,2^b-1}  
$$

In questo sistema:

$$  
-n \equiv 2^b - n \pmod{2^b}  
$$

Ma:

- $2^b - 1$ = tutti 1 in binario
    
- quindi:
    

$$  
2^b - n = (\overline{n}) + 1  
$$

Quindi il **complemento a 2 è la realizzazione binaria del negativo in aritmetica modulare**.

---

## **4. Interpretazione del valore in complemento a 2**

Per un numero binario $x = b_{b-1} b_{b-2}\dots b_0$ su $b$ bit:

$$  
\text{valore}(x) = - b_{b-1}\cdot 2^{b-1} + \sum_{i=0}^{b-2} b_i \cdot 2^i  
$$

Il **bit più significativo (MSB)** ha **peso negativo**.

---

## **5. Il MSB indica il segno**

Nel complemento a 2:

|MSB|Significato|
|---|---|
|0|Numero positivo o zero|
|1|Numero negativo|

Questo vale **per qualsiasi numero di bit**.

### Esempi su 4 bit

- `0101` → MSB = 0 → $+5$
    
- `1011` → MSB = 1 → negativo  
    complemento a 2:
    
    - inverti → `0100`
        
    - +1 → `0101` → $5$  
        → valore = $-5$
        

---

## **6. Intervallo rappresentabile NON simmetrico**

Con $b$ bit in complemento a 2:

$$  
-2^{b-1} \le n \le 2^{b-1}-1  
$$

Esempio con 4 bit:

$$  
-8 \le n \le +7  
$$

|Binario|Valore|
|---|---|
|0000|0|
|0111|+7|
|1000|−8|
|1111|−1|

### Perché NON è simmetrico?

Perché:

- i negativi sono $2^{b-1}$
    
- i non-negativi sono $2^{b-1}$
    
- ma **lo zero è nei positivi**  
    → c’è **un negativo in più**
    

---

## **7. Proprietà strutturali fondamentali**

### ✅ Uno e un solo zero

Niente +0 / −0.  
Lo zero è unico: `000…000`

---

### ✅ Somma e sottrazione unificate

Nel complemento a 2:

- **sottrarre = sommare l’opposto**
    
- l’ALU fa **solo somme binarie**
    

$$  
A - B = A + (-B)  
$$

E $-B$ si ottiene con:

$$  
\overline{B} + 1  
$$

---

### ✅ Nessun controllo di segno in fase operativa

L’ALU **non deve sapere se un numero è positivo o negativo**.  
Deve solo **sommare bit**.

---

## **8. Carry Out ≠ Overflow Logico (DISTINZIONE CRITICA)**

### **8.1 Carry Out (overflow aritmetico)**

È il **riporto oltre il MSB**:

- è un **fenomeno fisico**
    
- nasce dalla somma binaria pura
    
- **non indica errore matematico**
    
- in complemento a 2 **si ignora**
    

Motivo:

L’aritmetica è **modulare modulo $2^b$**.  
Il carry out indica solo che hai fatto “un giro completo dell’anello”.

---

### **8.2 Overflow Logico (errore semantico)**

È un **errore MATEMATICO**.

Si verifica **solo se**:

1. Gli operandi hanno **stesso segno**
    
2. Il risultato ha **segno opposto**
    

Formalmente:

- $A>0$, $B>0$ e $A+B<0$ → overflow positivo
    
- $A<0$, $B<0$ e $A+B\ge 0$ → overflow negativo (underflow)
    

### Regola pratica hardware:

Basta confrontare i **tre MSB**:

- MSB(A)
    
- MSB(B)
    
- MSB(Risultato)
    

Se i primi due sono uguali e il terzo è diverso → **overflow**

---

## **9. Underflow**

È semplicemente:

> **overflow verso il basso**

Accade quando:

- sommi due numeri negativi
    
- ottieni un risultato positivo
    

È **sempre un overflow logico**, solo nel verso negativo.

---

## **10. Estensione del segno (Sign Extension)**

Se passi da $b$ bit a $k>b$ bit:

- **ripeti il MSB**
    

|Numero|4 bit|8 bit|
|---|---|---|
|+5|0101|00000101|
|−3|1101|11111101|

### Perché funziona?

Perché il MSB rappresenta il **peso negativo dominante**.  
Ripeterlo **preserva il valore matematico**.

---

## **11. Perché il complemento a 2 è PERFETTO per l’hardware**

|Motivo|Effetto|
|---|---|
|Unico zero|niente ambiguità|
|Somma unica|stessa ALU per + e −|
|MSB = segno|decodifica istantanea|
|Aritmetica modulare|carry out ignorabile|
|Overflow rilevabile logicamente|confronto MSB|
|Sign extension semplice|solo duplicazione bit|

👉 Tutto è ridotto a **porte logiche di somma**.

---

## **12. Sintesi finale definitiva**

Il **complemento a 2** è:

- una **rappresentazione matematica**
    
- una **codifica binaria**
    
- una **implementazione hardware**
    
- una **aritmetica modulare**
    
- una **teoria dei segni**
    
- una **semantica del calcolo**
    

tutto **in un unico schema coerente**.

---

## ✅ Conclusione assoluta

> Il complemento a 2 non è una “tecnica”.  
> È **la struttura matematica che rende possibile l’intero calcolo automatico moderno.**

CPU, ALU, registri, bus, stack, indirizzi, offset, salti, differenze, contatori…  
**Tutto vive dentro questa logica.**

---

Se vuoi, nella prossima lezione possiamo fare **solo esercizi guidati completi** su:

- calcolo del complemento
    
- somme con overflow
    
- sottrazioni mascherate
    
- esempi con 8, 16, 32 bit
    
- casi limite ($-2^{b-1}$, $+2^{b-1}-1$)
    

Dimmi:  
👉 **vuoi subito una batteria di esercizi “da esame”?**