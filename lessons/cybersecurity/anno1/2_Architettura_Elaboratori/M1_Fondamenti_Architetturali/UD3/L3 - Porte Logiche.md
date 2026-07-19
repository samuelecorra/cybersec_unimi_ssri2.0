## ***Lezione 3 – Porte logiche***

---

> 📌 Questa lezione rielabora integralmente le pagine 69–85 di `M1doc.pdf`.

### **1. Dalla logica alla fisica**

L’algebra booleana è un linguaggio **astratto** per lavorare con variabili che possono valere solo:

- $0$ → **FALSO** / **spento**
    
- $1$ → **VERO** / **acceso**
    

La cosa fondamentale è che **questa algebra è realizzabile in elettronica**:

- una tensione compresa nella fascia ammessa come livello “basso” viene interpretata come $0$ logico;
    
- una tensione compresa nella fascia “alta” viene interpretata come $1$ logico.

Fra le due fasce può esistere una zona non garantita. I valori fisici non sono quindi esattamente $0\ \mathrm V$ e la tensione di alimentazione: le soglie e i margini di rumore dipendono dalla famiglia logica.
    

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
        
- gli elementi di commutazione venivano inizialmente impiegati anche per controllare energia e segnali continui.
    

Il passaggio concettuale fondamentale fu mostrare la corrispondenza fra calcolo logico e reti di interruttori. I relè e i circuiti di commutazione precedono i transistor; nel 1937 **Claude Shannon** formalizzò l’uso dell’algebra booleana per analizzarli e progettarli. I lavori di **Alan Turing** riguardano invece i modelli astratti di computazione e la calcolabilità. I due contributi sono complementari, ma storicamente distinti.

Con questi lavori:

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
        
    - nei circuiti digitali è spesso indicata come $V_{CC}$ nelle famiglie bipolari o $V_{DD}$ nelle tecnologie MOS. Sono etichette convenzionali dei nodi di alimentazione, non definizioni universali della tensione logica alta.
        
2. **Massa / ground (GND = 0 V)**
    
    - è il **riferimento** di tutte le tensioni del circuito;
        
    - per convenzione la prendiamo a $0\ \text{V}$;
        
    - quando diciamo “5 V” intendiamo una differenza di potenziale di 5 V rispetto al riferimento.

    La massa circuitale non coincide necessariamente con la terra di protezione né con il polo negativo di ogni possibile alimentazione: è anzitutto il nodo scelto come riferimento $0\ \mathrm V$.
        
3. **Rete di componenti**
    
    - transistor (componenti attivi controllabili da un segnale),
        
    - resistenze (per limitare la corrente, definire livelli),
        
    - connessioni che stabiliscono quando una linea viene portata a VCC (1 logico) o GND (0 logico).
        

Per funzionare, il circuito richiede percorsi di corrente e riferimenti elettrici definiti; l’etichetta GND identifica il riferimento comune nello schema.

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

#### **5.1. Nodo di uscita e resistenza di pull-up**

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

#### **5.2. L’interruttore controllato: il transistor**

Ci serve un “interruttore intelligente” che:

- chiuda verso massa (GND) **solo quando l’ingresso è alto**;
    
- resti aperto quando l’ingresso è basso.
    

Questo è il ruolo del **transistor bipolare (BJT)** usato come interruttore.

- **Tre terminali**:
    
    - base ($B$),
        
    - collettore ($C$),
        
    - emettitore ($E$).
        
- Nel modello semplificato da interruttore:
    
    - se alla base arriva corrente → transistor **satura** → $C$ è collegato a $E$ (interruttore chiuso);
        
    - se alla base non arriva corrente → transistor **tagliato** → $C$ e $E$ sono aperti (interruttore aperto).
        

Montiamo il transistor così:

- collettore $C$ collegato al nodo di uscita;
    
- emettitore $E$ collegato a **GND**;
    
- base $B$ collegata all’ingresso $X$ tramite una resistenza $R_I$ (per limitare la corrente di base).
    

#### **5.3. Comportamento complessivo**

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
    

Il cerchietto è un **marcatore di inversione** e può comparire sugli ingressi o sulle uscite di simboli più complessi; una porta NOT autonoma viene normalmente rappresentata dal triangolo con il cerchietto.

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

#### **7.1. Porta AND**

##### **7.1.1. Definizione logica**

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

##### **7.1.2. Significato funzionale**

- È una **congiunzione**: “$A$ **e** $B$ devono essere veri”.
    
- Agisce come un **filtro**: lascia passare il valore 1 solo se tutte le condizioni sono soddisfatte.
    
- È alla base di:
    
    - condizioni multiple (“se _questa_ cosa e _quell’altra_ allora attiva…”),
        
    - decoder, controlli di abilitazione, maschere di bit, ecc.
        

---

#### **7.2. Porta OR**

##### **7.2.1. Definizione logica**

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

##### **7.2.2. Significato funzionale**

- È un “**rivelatore di presenza**”: l’uscita va a 1 se un qualunque ingresso è attivo.
    
- Serve per:
    
    - accorpare più sorgenti di segnale,
        
    - generare allarmi o flag se _una qualsiasi_ condizione si verifica,
        
    - combinare più cause che portano allo stesso effetto.
        

---

#### **7.3. Porta NAND (NOT-AND)**

##### **7.3.1. Definizione logica**

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
    

##### **7.3.2. Importanza teorica**

La NAND è una porta **universalmente completa**:

> Usando **solo porte NAND** si può realizzare **qualsiasi** funzione logica (NOT, AND, OR, XOR, …).

Perché interessa:

- molte famiglie logiche (TTL, CMOS) implementano internamente la maggior parte delle funzioni usando strutture NAND;
    
- a livello teorico è la base di sistemi minimali di logica.
    

---

#### **7.4. Porta NOR (NOT-OR)**

##### **7.4.1. Definizione logica**

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
    

##### **7.4.2. Importanza teorica**

Anche la NOR è **universalmente completa**:

> con sole porte NOR si possono costruire tutte le altre porte logiche.

In pratica è molto usata in:

- circuiti di **reset** e **idle** (uscita alta finché non arriva alcun segnale),
    
- primi modelli di latch e flip-flop (RS-NOR).

#### **7.5. Confronto unitario delle porte**

| $A$ | $B$ | AND $AB$ | OR $A+B$ | XOR $A\oplus B$ | NAND $\overline{AB}$ | NOR $\overline{A+B}$ | XNOR $\overline{A\oplus B}$ |
| ---: | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| 0 | 0 | 0 | 0 | 0 | 1 | 1 | 1 |
| 0 | 1 | 0 | 1 | 1 | 1 | 0 | 0 |
| 1 | 0 | 0 | 1 | 1 | 1 | 0 | 0 |
| 1 | 1 | 1 | 1 | 0 | 0 | 0 | 1 |

La XNOR è vera quando gli ingressi coincidono ed è quindi utile nei comparatori di uguaglianza. XOR e XNOR completano il quadro delle operazioni già incontrate nel sommatore.

#### **7.6. Dalla funzione alla rete di transistor**

La descrizione “transistor in serie per AND e in parallelo per OR” è un’intuizione utile per reti di interruttori, ma non è lo schema completo di ogni famiglia logica. In CMOS statico si realizzano in modo naturale porte **invertenti** NAND e NOR mediante reti complementari di transistor NMOS e PMOS; AND e OR si possono ottenere aggiungendo un inverter. La scelta concreta dipende da area, ritardo, potenza e fan-out.

Il nome delle memorie **NAND flash** citate nel PDF deriva dalla disposizione in serie delle celle e dal comportamento dell’array, simile a una funzione NAND durante la lettura; non significa che i dati siano memorizzati in una rete ordinaria di porte NAND logiche.

#### **7.7. Collegamento con sicurezza, firmware e sistemi embedded**

Le condizioni AND compaiono nelle abilitazioni multiple, per esempio un’azione permessa soltanto con `PIN valido AND badge valido`; le OR aggregano eventi, allarmi e richieste di interrupt; NAND e NOR ricorrono nei controlli attivi bassi, nei reset e nei blocchi di memoria. Saper leggere queste reti aiuta quindi a interpretare schemi di microcontrollori, segnali di boot e registri di controllo durante l’analisi di firmware o dispositivi embedded. Non implica che un controllo software sia letteralmente una singola porta, ma mostra la logica binaria che l’hardware dovrà infine realizzare.
    

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

> ✅ Una porta logica è l’astrazione funzionale di un circuito fisico: soglie elettriche interpretano i segnali come bit, mentre simbolo, espressione e tabella di verità ne descrivono il comportamento indipendentemente dalla tecnologia.
