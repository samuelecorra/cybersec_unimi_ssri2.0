## ***Lezione 2 – Aritmetica con l’Algebra Booleana***

---

### **1. Obiettivo della lezione**

In questa lezione vogliamo **dimostrare operativamente** che:

> le regole dell’algebra booleana, applicate a variabili che possono valere solo $0$ o $1$, sono sufficienti per costruire **circuiti che eseguono vera aritmetica** su numeri interi binari.

L’esempio fondamentale è la **somma**:

- prima somma di **1 bit + 1 bit**,
    
- poi somma di **numeri a più bit**,
    
- fino a vedere come nasce un **adder a più bit** collegando più moduli elementari.
    

---

## **2. Somma di due bit singoli**

Consideriamo due variabili booleane / bit:

- $A \in {0,1}$
    
- $B \in {0,1}$
    

Ogni coppia $(A,B)$ rappresenta la somma di due numeri binari a 1 bit.  
Le combinazioni possibili sono solo **4**:

|A|B|Somma aritmetica|Bit di somma S|Riporto R|
|---|---|---|---|---|
|0|0|0|0|0|
|0|1|1|1|0|
|1|0|1|1|0|
|1|1|2 (cioè 10₂)|0|1|

Quindi la nostra somma **a 1 bit** produce sempre **due** output:

- il **bit di somma** $S$ (colonna corrente),
    
- il **bit di riporto** $R$ (che va “alla colonna successiva”).
    

---

### **2.1 Espressione logica del riporto R**

Dalla tabella:

- $R = 1$ **solo** quando $A=1$ e $B=1$.
    

In forma booleana:
  
$R = A \cdot B$

Questa è la formula del **riporto**:

- $R$ è 1 **se e solo se** entrambi i bit in ingresso valgono 1.
    

---

### **2.2 Espressione logica del bit di somma S**

Guardiamo la colonna $S$:

- $S = 1$ quando $(A,B) = (0,1)$ oppure $(1,0)$
    
- $S = 0$ quando i due bit sono **uguali** $(0,0)$ o $(1,1)$
    

Costruiamo i **mintermini** per le righe dove $S=1$.

- Riga 2: $A=0, B=1 \Rightarrow \overline{A} \cdot B$
    
- Riga 3: $A=1, B=0 \Rightarrow A \cdot \overline{B}$
    

Facciamo la somma logica dei mintermini:

$$ 
S = \overline{A}B + A\overline{B}  
$$

Questa è già una forma perfettamente valida.  
È anche la definizione canonica dell’operatore **XOR**.

---

### **2.3 Definizione dell’operatore XOR**

Introduciamo un nuovo operatore, estremamente usato in aritmetica binaria.

> **Operatore XOR (eXclusive OR)**  
> $$
 S = A \oplus B  \stackrel{\text{def}}{=}  \overline{A}B + A\overline{B}  
 $$ 
> Restituisce 1 **se e solo se** i due bit sono **diversi**.

Tabella di verità dell’XOR:

|A|B|A ⊕ B|
|---|---|---|
|0|0|0|
|0|1|1|
|1|0|1|
|1|1|0|

Riassumendo, la somma di due bit si esprime come:

$$
\boxed{S = A \oplus B} \qquad \boxed{R = A \cdot B}  
$$

Queste due formule sono **la base teorica** di tutto il resto.

---

## **3. Half Adder: il primo mattoncino aritmetico**

### **3.1 Definizione**

Un **Half Adder (HA)** è un circuito combinatorio che:

- riceve in ingresso **due bit** $A$ e $B$,
    
- produce in uscita:
    
    - il bit di somma $S$,
        
    - il bit di riporto $R$.
        

Formalmente:

- ingressi: $A, B$
    
- uscite: $S = A \oplus B,; R = A \cdot B$
    

Schema funzionale (a parole):

- una porta **XOR** calcola $S$,
    
- una porta **AND** calcola $R$.
    

Questo circuito:

- è il **primo componente aritmetico elementare**,
    
- implementa esattamente la tabella di verità della somma a 1 bit.
    

---

## **4. Somma di numeri a 2 bit**

Passiamo ora a due numeri binari **a 2 bit**:

$$
A = A_1A_0, \qquad B = B_1B_0  
$$

dove:

- $A_0, B_0$ sono i **bit meno significativi** (LSB, peso $2^0$),
    
- $A_1, B_1$ sono i **bit più significativi** (MSB, peso $2^1$).
    

Esempio:

- $A = 10_2$ corrisponde a $2_{10}$ perché $1\cdot 2^1 + 0\cdot 2^0$.
    

### **4.1 Fase 1: somma dei bit meno significativi**

Sommiamo prima $A_0$ e $B_0$:

- usiamo un **Half Adder**,
    
- otteniamo:
    
    - $S_0$ = bit di somma meno significativo,
        
    - $R_0$ = riporto verso la colonna successiva.
        

Formule:

$$
S_0 = A_0 \oplus B_0 \qquad R_0 = A_0 \cdot B_0  
$$

---

### **4.2 Fase 2: somma dei bit più significativi con riporto**

Ora dobbiamo sommare **tre bit**:

- $A_1$
    
- $B_1$
    
- riporto $R_0$
    

Questa volta non basta un Half Adder:  
ci serve un circuito che sommi **3 ingressi** e produca:

- bit di somma $S_1$
    
- riporto finale $R_1$
    

Questo circuito è il **Full Adder (FA)**.

---

## **5. Full Adder e funzione di maggioranza**

### **5.1 Tabella della verità del Full Adder**

Indichiamo gli ingressi:

- $X = A_1$,
    
- $Y = B_1$,
    
- $C_\text{in} = R_0$ (carry in).
    

Uscite:

- $S_1$ (somma),
    
- $R_1 = C_\text{out}$ (carry out).
    

Tabella:

|X|Y|C_in|Somma aritmetica|S₁|R₁|
|---|---|---|---|---|---|
|0|0|0|0|0|0|
|0|0|1|1|1|0|
|0|1|0|1|1|0|
|0|1|1|2 (10₂)|0|1|
|1|0|0|1|1|0|
|1|0|1|2 (10₂)|0|1|
|1|1|0|2 (10₂)|0|1|
|1|1|1|3 (11₂)|1|1|

---

### **5.2 Espressione logica del riporto $R_1$**

Guardiamo dove $R_1 = 1$:

- $(X,Y,C_\text{in}) = (0,1,1)$
    
- $(1,0,1)$
    
- $(1,1,0)$
    
- $(1,1,1)$
    

Si può osservare che:

> $R_1$ è 1 quando **almeno due** ingressi sono uguali a 1.

Questa è la **funzione di maggioranza** a 3 ingressi.

La forma classica:

$$
R_1 = XY + XC_\text{in} + YC_\text{in}  
$$

Ogni termine AND rappresenta il caso in cui **una coppia** di ingressi è contemporaneamente 1.

---

### **5.3 Espressione logica del bit di somma $S_1$**

Dalla tabella:

- $S_1 = 1$ quando il numero di 1 tra $(X,Y,C_\text{in})$ è **dispari**.
    

Quindi:

$$
S_1 = X \oplus Y \oplus C_\text{in}  
$$

XOR a **tre ingressi**: è 1 se c’è **1 solo** o **3** bit a 1, cioè se la parità è dispari.

Espansione in mintermini (se vuoi la forma puramente AND/OR):

$$
S_1 = \overline{X} \ \overline{Y} \ C_\text{in} + \overline{X}\ Y\ \overline{C_\text{in}}  
+ X\ \overline{Y}\ \overline{C_\text{in}} + X\ Y \ C_\text{in}  
$$

---

### **5.4 Full Adder: definizione finale**

Un **Full Adder (FA)** a 1 bit:

- **Ingressi**: $X, Y, C_\text{in}$
    
- **Uscite**:  
    $$
    S = X \oplus Y \oplus C_\text{in}  
    $$
     
    $$  
    C_\text{out} = XY + XC_\text{in} + YC_\text{in}  
    $$
    

Realizzazione tipica:

- due XOR in cascata per $S$,
    
- tre AND + una OR a 3 ingressi (o OR a due livelli) per il carry.
    

---

## **6. Dalla somma a 2 bit al sommatore a N bit**

### **6.1 Sommatore a 2 bit completo**

Per sommare:

$$
A = A_1A_0, \quad B = B_1B_0  
$$

colleghiamo:

1. **Half Adder** per i bit meno significativi:  
    $$
    S_0 = A_0 \oplus B_0,\quad R_0 = A_0 \cdot B_0  
    $$
    
2. **Full Adder** per i bit più significativi, usando $R_0$ come carry in:  
    $$
    S_1 = A_1 \oplus B_1 \oplus R_0  
    $$
     
    $$ 
    R_1 = A_1B_1 + A_1R_0 + B_1R_0  
    $$
    

Il risultato finale è un numero a 3 bit:

$$
\text{Risultato} = R_1 S_1 S_0  
$$

---

### **6.2 Sommatore ripple-carry a N bit**

L’idea si generalizza immediatamente:

- per sommare due numeri a **N bit**, si mettono in cascata:
    
    - 1 **Half Adder** sulla colonna meno significativa,
        
    - $(N-1)$ **Full Adder** sulle colonne successive.
        

Lo schema è detto **ripple-carry adder** perché:

- il carry “rimbalza” (ripple) da un blocco all’altro,
    
- da destra verso sinistra, fino al bit più significativo.
    

**Estensione a 3, 4, 5 bit, …**:

- basta aggiungere un Full Adder per **ogni nuova colonna** di bit,
    
- collegando il carry out del blocco a destra al carry in del blocco successivo a sinistra.
    

In questo modo, con **solo AND, OR, NOT (e XOR costruiti da essi)**, l’algebra booleana:

- implementa la **somma di interi binari** di qualsiasi lunghezza.
    

---

## **7. Messaggio concettuale finale**

Mettiamo insieme il quadro:

1. Partendo dai **postulati dell’algebra booleana** (0/1, AND, OR, NOT) abbiamo definito:
    
    - XOR,
        
    - Half Adder,
        
    - Full Adder,
        
    - sommatore a N bit.
        
2. Abbiamo quindi costruito un circuito che esegue una vera **operazione aritmetica**:  
    la somma di numeri interi.
    
3. Collegando fra loro molti mattoncini di questo tipo (adder, comparatori, multiplexer, ecc.) si arriva all’**ALU** di un processore, in grado di eseguire:
    
    - somme, sottrazioni, confronti, operazioni logiche, shift…
        

Da qui puoi leggere la frase delle slide così, in modo pieno:

> **Circuiti descritti con le regole dell’algebra booleana possono svolgere elaborazioni su informazioni di qualunque tipo.**

Perché:

- qualunque informazione (numeri, testo, immagini, audio…) è ridotta a **bit**,
    
- e qualunque manipolazione su quei bit è realizzabile tramite reti di **porte logiche** che seguono esattamente le leggi dell’algebra booleana.
    

Questa lezione è il ponte diretto fra:

- **teoria logica** (Algebra di Boole)
    
- e **hardware reale** (sommatore, ALU, CPU).