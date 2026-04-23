## ***Lezione 3 – Porte logiche***

---

### **1. Dalla logica alla fisica**

L’algebra booleana è un linguaggio **astratto** per lavorare con variabili che possono valere solo:

- $0$ → **FALSO** / **spento**
    
- $1$ → **VERO** / **acceso**
    

La cosa fondamentale è che **questa algebra è realizzabile in elettronica**:

- un livello di tensione “basso” (vicino a $0\ \text{V}$) viene interpretato come $0$ logico;
    
- un livello di tensione “alto” (es. $5\ \text{V}$ o $3{,}3\ \text{V}$) viene interpretato come $1$ logico.
    

Tutti gli operatori logici (NOT, AND, OR, NAND, NOR, …):

- hanno una **definizione formale** nell’algebra booleana;
    
- e una **realizzazione fisica** in termini di transistor, resistenze, alimentazione e massa.
    

Queste realizzazioni fisiche prendono il nome di **porte logiche** (_logic gates_).

---

### **2. Dall’elettronica analogica all’elettronica digitale**

Storicamente:

- transistor, diodi e resistenze nascono per l’**elettronica analogica**:
    
    - amplificatori audio,
        
    - stadi radio,
        
    - alimentatori, ecc.
        
- non si pensava ancora di usarli per “ragionare”, ma per **gestire energia** e **segnali continui**.
    

Con i lavori di:

- **Alan Turing** (computazione, modelli astratti di calcolo),
    
- **Claude Shannon** (collegamento tra **logica booleana** e **circuiti elettrici**),
    

si capisce che:

> è possibile implementare le operazioni dell’algebra booleana **con circuiti elettrici reali**.

Da qui nasce l’**elettronica digitale**: i transistor non servono più solo ad amplificare, ma a **decidere** (passa / non passa → $1/0$).

---

### **3. Livelli logici: alimentazione (VCC) e massa (GND)**

Per trasformare formule logiche in circuiti reali servono almeno tre ingredienti:

1. **Sorgente di alimentazione continua (DC)**
    
    - fornisce una tensione costante nel tempo, es. $5\ \text{V}$ o $3{,}3\ \text{V}$;
        
    - nei circuiti digitali è spesso indicata come **VCC** ( Voltage at the Common Collector, o **VDD** nelle tecnologie MOS).
        
2. **Massa / ground (GND = 0 V)**
    
    - è il **riferimento** di tutte le tensioni del circuito;
        
    - per convenzione la prendiamo a $0\ \text{V}$;
        
    - quando diciamo “5 V” intendiamo “5 V in più rispetto alla massa”.
        
3. **Rete di componenti**
    
    - transistor (componenti attivi controllabili da un segnale),
        
    - resistenze (per limitare la corrente, definire livelli),
        
    - connessioni che stabiliscono quando una linea viene portata a VCC (1 logico) o GND (0 logico).
        

Senza massa il circuito non “chiude” il percorso della corrente e **non funziona**.

---

### **4. L’idea del circuito logico**

Un **circuito logico** è un circuito elettronico che:

- lavora con **due soli livelli** di tensione (0 ≈ GND, 1 ≈ VCC);
    
- associa a questi livelli i valori logici $0$ e $1$;
    
- realizza una funzione logica ben precisa (NOT, AND, OR, …) tra gli ingressi e le uscite.
    

> In pratica: trasformiamo tensione e corrente in **informazione**.

Per capire come, costruiamo da zero la porta più semplice: la **NOT**.

---

### **5. Costruzione fisica di una porta NOT (negatore)**

Vogliamo un circuito con 1 ingresso $X$ e 1 uscita $Y$ tale che:

- se in ingresso c’è $0$ → in uscita vogliamo $1$;
    
- se in ingresso c’è $1$ → in uscita vogliamo $0$.
    

Formalmente:

$$ 
Y = \overline{X}  
$$

#### **5.1 Nodo di uscita e resistenza di pull-up**

1. Immaginiamo un **nodo di uscita** che può stare:
    
    - vicino a **VCC** (→ $1$ logico),
        
    - oppure vicino a **GND** (→ $0$ logico).
        
2. Colleghiamo questo nodo a VCC tramite una **resistenza** $R_U$:
    
    - questa resistenza si chiama **resistenza di pull-up**.
        

Effetto:

- se nessuno “tira giù” il nodo verso GND, la resistenza lo tiene **alto** → livello logico 1;
    
- se invece qualcuno lo collega “fortemente” a massa, la tensione scende ≈ 0 V → livello logico 0.
    

Metafora: è come una **molla** che tira il nodo verso l’alto finché nessuno lo spinge verso il basso.

> Da sola, però, la pull-up non basta: l’uscita sarebbe sempre 1.

#### **5.2 Aggiungiamo l’interruttore controllato: il transistor**

Ci serve un “interruttore intelligente” che:

- chiuda verso massa (GND) **solo quando l’ingresso è alto**;
    
- resti aperto quando l’ingresso è basso.
    

Questo è il ruolo del **transistor bipolare (BJT)** usato come interruttore.

- **Tre terminali**:
    
    - base ($B$),
        
    - collettore ($C$),
        
    - emettitore ($E$).
        
- Nella modalità “switch”:
    
    - se alla base arriva corrente → transistor **satura** → $C$ è collegato a $E$ (interruttore chiuso);
        
    - se alla base non arriva corrente → transistor **tagliato** → $C$ e $E$ sono aperti (interruttore aperto).
        

Montiamo il transistor così:

- collettore $C$ collegato al nodo di uscita;
    
- emettitore $E$ collegato a **GND**;
    
- base $B$ collegata all’ingresso $X$ tramite una resistenza $R_I$ (per limitare la corrente di base).
    

#### **5.3 Comportamento complessivo**

Caso 1 – ingresso $X = 0$ (≈ 0 V):

- non scorre corrente nella base,
    
- il transistor è **spento** (interruttore aperto),
    
- il nodo di uscita non è collegato a massa,
    
- la pull-up lo porta a VCC → $Y = 1$.
    

Caso 2 – ingresso $X = 1$ (≈ VCC):

- scorre corrente nella base (limitata da $R_I$),
    
- il transistor **conduce** → collega uscita a GND,
    
- l’uscita viene “tirata giù” a 0 V → $Y = 0$.
    

Quindi il circuito realizza esattamente la funzione:

$$
Y = \overline{X}  
$$

Abbiamo costruito **fisicamente** una porta **NOT**.

---

### **6. Porta NOT: simbolo e tabella di verità**

A questo punto possiamo **astrarre** dalla fisica e descrivere solo il comportamento logico.

- **Ingresso**: $X$
    
- **Uscita**: $Y = \overline{X}$
    

Tabella di verità:

|X|Y = ¬X|
|---|---|
|0|1|
|1|0|

Simbolo standard:

- triangolo che indica la direzione del segnale,
    
- **cerchietto in uscita** (bubble) che indica la negazione.
    

Spesso, per semplicità, si disegna solo il cerchietto sulla linea, se la direzione è chiara.

> La porta NOT è la più semplice, ma è **fondamentale**: compare ovunque, da sola o combinata ad altre porte.

---

### **7. Altre porte logiche fondamentali**

Una volta compreso il meccanismo con la NOT, sappiamo che allo stesso modo (con transistor e resistenze) si possono implementare tutte le altre porte:

- **AND**, **OR**, **NAND**, **NOR**, …
    

Nel corso di Architettura non serve sapere _esattamente_ come sono i transistor dentro le porte; basta conoscere:

- **significato logico**,
    
- **tabella di verità**,
    
- **espressione booleana**,
    
- **intuito funzionale** (che ci servirà quando costruiremo circuiti più grandi).
    

---

### **7.1 Porta AND**

#### **Definizione logica**

La porta **AND** a due ingressi $A$ e $B$ restituisce $1$ **solo se entrambi** gli ingressi valgono $1$.

Altrimenti l’uscita è $0$.

Tabella di verità:

|A|B|Y = A · B|
|---|---|---|
|0|0|0|
|0|1|0|
|1|0|0|
|1|1|1|

Espressione booleana:

$$
Y = A \cdot B  
$$

(si può spesso omettere il punto e scrivere $AB$).

#### **Significato funzionale**

- È una **congiunzione**: “$A$ **e** $B$ devono essere veri”.
    
- Agisce come un **filtro**: lascia passare il valore 1 solo se tutte le condizioni sono soddisfatte.
    
- È alla base di:
    
    - condizioni multiple (“se _questa_ cosa e _quell’altra_ allora attiva…”),
        
    - decoder, controlli di abilitazione, maschere di bit, ecc.
        

---

### **7.2 Porta OR**

#### **Definizione logica**

La porta **OR** a due ingressi $A$ e $B$ restituisce $1$ se **almeno uno** dei due è $1$.

L’unico caso in cui l’uscita è $0$ è quando **entrambi** sono $0$.

Tabella di verità:

|A|B|Y = A + B|
|---|---|---|
|0|0|0|
|0|1|1|
|1|0|1|
|1|1|1|

Espressione booleana:

$$
Y = A + B  
$$

Qui il simbolo $+$ è **somma logica**, non somma aritmetica (per questo in logica $1 + 1 = 1$).

#### **Significato funzionale**

- È un “**rivelatore di presenza**”: l’uscita va a 1 se un qualunque ingresso è attivo.
    
- Serve per:
    
    - accorpare più sorgenti di segnale,
        
    - generare allarmi o flag se _una qualsiasi_ condizione si verifica,
        
    - combinare più cause che portano allo stesso effetto.
        

---

### **7.3 Porta NAND (NOT-AND)**

#### **Definizione logica**

La porta **NAND** è la negazione dell’AND:

$$
Y = \overline{A \cdot B}  
$$

Tabella di verità:

|A|B|Y = ¬(A · B)|
|---|---|---|
|0|0|1|
|0|1|1|
|1|0|1|
|1|1|0|

Interpretazione:

- restituisce $0$ solo quando **tutti** gli ingressi sono $1$;
    
- in tutti gli altri casi restituisce $1$.
    

#### **Importanza teorica**

La NAND è una porta **universalmente completa**:

> Usando **solo porte NAND** si può realizzare **qualsiasi** funzione logica (NOT, AND, OR, XOR, …).

Perché interessa:

- molte famiglie logiche (TTL, CMOS) implementano internamente la maggior parte delle funzioni usando strutture NAND;
    
- a livello teorico è la base di sistemi minimali di logica.
    

---

### **7.4 Porta NOR (NOT-OR)**

#### **Definizione logica**

La porta **NOR** è la negazione della OR:

$$ 
Y = \overline{A + B}  
$$

Tabella di verità:

|A|B|Y = ¬(A + B)|
|---|---|---|
|0|0|1|
|0|1|0|
|1|0|0|
|1|1|0|

Interpretazione:

- restituisce $1$ solo quando **tutti** gli ingressi sono $0$;
    
- appena un ingresso va a 1, l’uscita cade a 0.
    

#### **Importanza teorica**

Anche la NOR è **universalmente completa**:

> con sole porte NOR si possono costruire tutte le altre porte logiche.

In pratica è molto usata in:

- circuiti di **reset** e **idle** (uscita alta finché non arriva alcun segnale),
    
- primi modelli di latch e flip-flop (RS-NOR).
    

---

### **8. Perché il corso non entra nei dettagli elettronici di ogni porta**

Il corso di **Architettura degli Elaboratori** non è un corso di **Elettronica**.

Quello che ci serve sapere qui è:

- cosa fa una porta logica,
    
- come si rappresenta con un **simbolo**,
    
- qual è la sua **tabella di verità**,
    
- come si combinano tra loro AND/OR/NOT/NAND/NOR per costruire:
    
    - circuiti combinatori (adder, multiplexer, comparatori…),
        
    - circuiti sequenziali (latch, flip-flop, registri, contatori…),
        
    - fino ad arrivare alla **CPU** vera e propria.
        

L’esempio della NOT fisica con transistor è stato sufficiente per capire:

> la logica digitale non è magia: è una **conseguenza diretta** del comportamento fisico dei componenti.

Da ora in poi lavoreremo soprattutto a livello **logico-funzionale**, usando i simboli delle porte e le loro tabelle di verità, senza scendere a ogni singolo transistor.

---

### **9. Dall’elaborazione alla memorizzazione**

Tutte le porte viste finora hanno una caratteristica comune:

- le loro uscite dipendono **solo** dagli ingressi presenti **in quell’istante**.
    

Questi sono i **circuiti combinatori**: appena cambiano gli ingressi, cambiano le uscite.

Ma un calcolatore ha bisogno anche di:

- **memorizzare** informazioni,
    
- **ricordare** un valore calcolato,
    
- conservarlo nel tempo anche se gli ingressi cambiano.
    

Per questo servono circuiti con **stato interno**:

- ricevono un dato,
    
- lo memorizzano,
    
- lo mantengono finché non arriva un comando che lo aggiorna.
    

Questi sono i **bistabili** (latch, flip-flop):

> i primi circuiti logici dotati di **memoria**, che studieremo nella prossima lezione.