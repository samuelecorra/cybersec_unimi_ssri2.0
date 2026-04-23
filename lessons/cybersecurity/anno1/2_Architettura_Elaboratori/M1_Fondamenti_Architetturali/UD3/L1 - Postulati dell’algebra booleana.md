## ***Lezione 1: Postulati dell’algebra booleana***

---

### **1. Perché ci serve l’algebra booleana**

Nel mondo fisico dei circuiti digitali:

- una linea di segnale può essere **bassa** (0 V) oppure **alta** (es. 5 V),
    
- un flip-flop può essere **reset** oppure **set**,
    
- una cella di memoria può contenere **0** oppure **1**.
    

Tutta l’architettura degli elaboratori è quindi basata su **grandezze binarie**.  
Serve allora un’algebra che lavori **non su infiniti numeri reali**, ma su **solo due valori**:

- $0$ = **FALSO**
    
- $1$ = **VERO**
    

Questa è l’**algebra booleana**, introdotta da **George Boole** nel 1854.  
Le sue regole sono esattamente quelle che governano:

- porte logiche (AND, OR, NOT),
    
- reti combinatorie,
    
- ALU e parte logica della CPU.
    

---

### **2. Postulato P1 – Dicotomia binaria**

Definiamo una **variabile booleana** $X$.

> **Postulato P1**  
> Una variabile booleana può assumere **solo** uno di questi due valori:  
> $X \in {0,1}$

Non esistono:

- mezzi valori,
    
- “forse”,
    
- probabilità interne all’algebra.
    

Tutto è ridotto a **0 o 1**.

---

### **3. Postulato P2 – Negazione logica (NOT)**

Introduciamo l’operatore **NOT**, indicato con la **barra sopra**:


$\overline{X}$

> **Postulato P2**  
> La negazione di una variabile booleana inverte il suo valore:  
> 
> $\overline{0} = 1, \qquad \overline{1} = 0$
> 

Tabella di verità del NOT:

$$  
\begin{array}{c|c}  
X & \overline{X} \\  
\hline  
0 & 1 \\  
1 & 0  
\end{array}  
$$

Interpretazione logica:

- se “$X$” è vero, “non $X$” è falso;
    
- se “$X$” è falso, “non $X$” è vero.
    

A livello hardware, NOT corrisponde a una **porta invertitrice**.

---

### **4. Operazione AND (prodotto logico)**

Indichiamo l’operazione AND con il **punto**:

 
$X \cdot Y$

(Spesso in logica si usa anche $X \land Y$; nel contesto di architettura useremo $X \cdot Y$.)

**Intuizione logica**:  
“$X$ **e** $Y$” è vero **solo** se sono veri **entrambi**.

I postulati per l’AND sono:

- **P3**: $0 \cdot 0 = 0$
    
- **P4**: $1 \cdot 1 = 1$
    
- **P5**: $1 \cdot 0 = 0 \cdot 1 = 0$
    

Tabella di verità completa:

$$  
\begin{array}{c|c|c}  
X & Y & X \cdot Y \\  
\hline  
0 & 0 & 0 \\  
0 & 1 & 0 \\  
1 & 0 & 0 \\  
1 & 1 & 1  
\end{array}  
$$

Morale:

> L’AND restituisce $1$ **solo se tutte le condizioni sono vere**.  
> È un’operazione **restrittiva**.

A livello hardware, è una **porta AND** a due ingressi.

---

### **5. Operazione OR (somma logica)**

Indichiamo l’operazione OR con il **più**:

$X + Y$

(in logica spesso si usa $X \lor Y$; qui manterremo il “+” logico.)

**Intuizione logica**:  
“$X$ **o** $Y$” è vero se **almeno una** delle condizioni è vera.

I postulati per l’OR sono:

- **P3′**: $1 + 1 = 1$
    
- **P4′**: $0 + 0 = 0$
    
- **P5′**: $0 + 1 = 1 + 0 = 1$
    

Tabella di verità completa:

$$  
\begin{array}{c|c|c}  
X & Y & X + Y \\  
\hline  
0 & 0 & 0 \\  
0 & 1 & 1 \\  
1 & 0 & 1 \\  
1 & 1 & 1  
\end{array}  
$$

Morale:

> L’OR restituisce $1$ se **almeno una** condizione è vera.  
> È un’operazione **inclusiva**.

A livello hardware, è una **porta OR**.

---

### **6. Perché questi postulati sono fondamentali**

Da $P1, P2, P3, P4, P5$ e le loro versioni duali $P3',P4',P5'$ derivano:

1. **La costruzione fisica dei circuiti**
    
    - ogni porta AND/OR/NOT implementa esattamente queste tabelle di verità;
        
2. **La semplificazione delle espressioni booleane**
    
    - esattamente come in algebra ordinaria semplifichi $x\cdot 1 = x$, qui potrai usare:  
        
        $X \cdot 1 = X, \quad X + 0 = X, \quad X \cdot 0 = 0, \quad X + 1 = 1, \dots$
        
        
3. **La progettazione di reti combinatorie**
    
    - decodificatori, sommatore, comparatori vengono descritti e minimizzati con espressioni booleane.
        

Tutta la parte combinatoria della logica di una CPU è, di fatto, un **enorme polinomio booleano** costruito con questi operatori.

---

### **7. Il principio di dualità**

L’algebra booleana ha una proprietà potentissima: la **dualità**.

Regola:

> Il **duale** di un’espressione si ottiene scambiando:  
> 
> $0 \leftrightarrow 1, \qquad + \leftrightarrow \cdot$
> 

Esempi sui postulati:

- **P3**: $0 \cdot 0 = 0$  
    duale → $1 + 1 = 1$ = **P3′**
    
- **P4**: $1 \cdot 1 = 1$  
    duale → $0 + 0 = 0$ = **P4′**
    
- **P5**: $1 \cdot 0 = 0 \cdot 1 = 0$  
    duale → $0 + 1 = 1 + 0 = 1$ = **P5′**
    

Principio chiave:

> Se un teorema è vero in algebra booleana, anche il suo **duale** è vero.

Questo ti permette spesso di dimostrare **metà dei risultati** e ottenere l’altra metà per dualità.

---

### **8. Le leggi di De Morgan**

Le **leggi di De Morgan** sono due teoremi chiave che collegano:

- negazione,
    
- AND,
    
- OR.
    

Sono indispensabili sia a livello **logico** (manipolazione formule) che **hardware** (realizzare un circuito con porte diverse).

#### **8.1 Primo teorema di De Morgan**

  
$\overline{A + B} = \overline{A} \cdot \overline{B}$

> La negazione di una **somma logica** è uguale al **prodotto** delle negazioni.

Tabella di verità:

$$  
\begin{array}{c|c|c|c}  
A & B & A + B & \overline{A + B} \\  
\hline  
0 & 0 & 0 & 1 \\  
0 & 1 & 1 & 0 \\  
1 & 0 & 1 & 0 \\  
1 & 1 & 1 & 0  
\end{array}  
\qquad
$$

$$
\begin{array}{c|c|c}  
A & B & \overline{A}\cdot\overline{B} \\  
\hline  
0 & 0 & 1 \\  
0 & 1 & 0 \\  
1 & 0 & 0 \\  
1 & 1 & 0  
\end{array}  
$$

Le due colonne finali coincidono → le espressioni sono equivalenti.

**Lettura logica:**

> “Non (A oppure B)” è vero **solo se** A è falsa **e** B è falsa.

---

#### **8.2 Secondo teorema di De Morgan**

 
$\overline{A \cdot B} = \overline{A} + \overline{B}$

> La negazione di un **prodotto logico** è uguale alla **somma** delle negazioni.

Tabella di verità:

$$  
\begin{array}{c|c|c|c}  
A & B & A \cdot B & \overline{A \cdot B} \\  
\hline  
0 & 0 & 0 & 1 \\  
0 & 1 & 0 & 1 \\ 
1 & 0 & 0 & 1 \\  
1 & 1 & 1 & 0  
\end{array}  
\qquad 
$$

$$
\begin{array}{c|c|c}  
A & B & \overline{A} + \overline{B} \\  
\hline  
0 & 0 & 1 \\  
0 & 1 & 1 \\  
1 & 0 & 1 \\  
1 & 1 & 0  
\end{array}  
$$

Anche qui le colonne finali coincidono.

**Lettura logica:**

> “Non (A e B)” è vero se **almeno una** tra A e B è falsa.

---

### **9. Perché De Morgan è fondamentale per l’hardware**

Dal punto di vista elettronico:

- implementare una porta AND può avere costi diversi rispetto a una porta OR;
    
- alcune tecnologie (es. logica CMOS) implementano in modo **particolarmente efficiente**:
    
    - la porta **NAND** (NOT-AND),
        
    - la porta **NOR** (NOT-OR).
        

Grazie alle leggi di De Morgan possiamo:

- riscrivere circuiti espressi con AND/OR/NOT
    
- in circuiti **equivalenti** che usano **solo NAND** o **solo NOR**.
    

Esempio:

$$ 
\overline{A + B} = \overline{A} \cdot \overline{B}  
$$

Significa che:

- una porta NOR su $(A,B)$
    
- equivale a un AND di due NOT delle singole variabili.
    

Quindi:

> Se la tecnologia realizza facilmente le porte NOR, puoi sostituire combinazioni di NOT+AND con una sola porta NOR, riducendo **area**, **latenza** e **consumo**.

Questo è uno dei motivi per cui De Morgan è **onnipresente** nei libri di architettura e nella sintesi logica automatica (es. strumenti CAD per circuiti).

---

### **10. Cosa portarti via da questa lezione**

1. L’algebra booleana lavora su **due soli valori**: $0$ e $1$.
    
2. Gli operatori base sono:
    
    - NOT: $\overline{X}$
        
    - AND: $X \cdot Y$
        
    - OR: $X + Y$
        
3. I **postulati P1–P5** e le versioni duali definiscono il comportamento di queste operazioni.
    
4. Il **principio di dualità** ti permette di ottenere un secondo teorema vero scambiando $0 \leftrightarrow 1$ e $+ \leftrightarrow \cdot$.
    
5. Le **leggi di De Morgan**:  
    $$  
    \overline{A + B} = \overline{A}\cdot\overline{B}, \qquad  
    \overline{A \cdot B} = \overline{A} + \overline{B}  
    $$ 
    sono essenziali sia per:
    
    - semplificare formule logiche,
        
    - sia per progettare circuiti efficienti usando NAND/NOR.
        

Tutto ciò prepara il terreno per le prossime lezioni, in cui:

- introdurrai altre leggi (associatività, commutatività, distributività, assorbimento, idempotenza…),
    
- e le userai per **minimizzare espressioni booleane** e progettare **circuiti combinatori reali**.