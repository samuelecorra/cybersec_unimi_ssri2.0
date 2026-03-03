# **Lezione 1: Cifrature multilettera e polialfabetica**

---

### **1. Oltre la cifratura monoalfabetica**

Nei **cifrari monoalfabetici**, ogni lettera del testo in chiaro è sostituita **sempre dalla stessa lettera cifrata**, secondo un unico alfabeto.  
Una volta scelta la chiave, la mappatura è **fissa e deterministica**.

Questo rende l’**analisi crittografica** piuttosto semplice:  
la distribuzione di frequenza delle lettere nel testo cifrato riflette quella del linguaggio naturale.

---

### **2. L’evoluzione: la cifratura polialfabetica**

Per superare questa debolezza, i cifrari polialfabetici introducono:

- l’**uso di più alfabeti cifranti**, che si alternano durante la cifratura;
    
- la **cifratura di più lettere per volta**, anziché di una sola.
    

Queste tecniche sono state sviluppate rispettivamente da:

- **Leon Battista Alberti**, **Giovanni Battista Porta**,
    
- **Charles Wheatstone** e il **Barone Lyon Playfair**.
    

---

### **3. Cifrario di Alberti**

**Leon Battista Alberti** (1404–1472), pittore, architetto, filosofo e musicista, fu il primo a proporre un sistema basato su **più alfabeti cifranti**.

#### **Principio di funzionamento**

L’idea è di **variare periodicamente l’alfabeto di cifratura**, in modo che **una stessa lettera** possa essere cifrata in **modi diversi**.

![](imgs/Pasted%20image%2020260221163517.png)

---

### **4. Il disco di Alberti**

Alberti progettò anche un **disco cifrante**, costituito da **due dischi concentrici**:

- quello esterno riporta le lettere dell’alfabeto in ordine normale,
    
- quello interno in ordine variabile o ruotato.

![](imgs/Pasted%20image%2020260221163643.png)

Il messaggio si cifra **ruotando il disco interno** in base alla chiave (ad esempio qui partiamo allineati con “AL”).

La D allora diventa U.
Poi ruotiamo in avanti di 1 lettera il disco interno:

![](imgs/Pasted%20image%2020260221163910.png)

allora per il secondo carattere da I $\rightarrow$ B.

E così via... S $\rightarrow$ X
C $\rightarrow$ M 
O $\rightarrow$ G

Questo sistema è considerato il primo **cifrario meccanico a rotazione**, e venne impiegato anche nella **guerra di secessione americana**.

Alberti pensò anche di usare una parola chiave che individuasse di volta in volta gli spostamenti dell'alfabeto cifrante.

---

### **5. Cifrario di Porta**

**Giovanni Battista Porta** (1563) ideò uno dei primi **cifrari per digrammi**, ossia basato su **coppie di lettere**.

![](imgs/Pasted%20image%2020260221164150.png)

Ogni coppia viene cifrata tramite una **permutazione** di numeri o lettere, definita da una chiave arbitraria.  
In questo modo, la sostituzione non agisce su singole lettere ma su **blocchi di due**.

> **Esempio:**  
> Testo in chiaro: `DO MA NI`  
> Testo cifrato: `92 312 346`

La chiave è data da una **permutazione di righe e colonne** all’interno di una grande tabella.  
Alcune versioni più complesse usavano anche **caratteri speciali** come parte dell’alfabeto cifrante. Obiettivo: disorientare ulteriormente l'avversario!

![](imgs/Pasted%20image%2020260221164252.png)

In questa versione, si suppose inoltre di posizionare le lettere che facevano da indici di riga e colonna in maniera del tutto casuale.

Nel momento in cui un mittente volesse comunicare un messaggio, avrebbe dovuto comporre il messaggio stesso seguendo la tabella, come nel caso precedente, e avrebbe dovuto poi condividere la chiave, che consisteva appunto nella particolare disposizione usata per individuare righe e colonne, al destinatario.

---

### **6. Cifrario di Playfair**

Il **cifrario di Playfair** fu progettato da **Charles Wheatstone** nel XIX secolo,  
ma reso celebre dal suo amico **Barone Lyon Playfair**, da cui prese il nome.

Venne utilizzato dai **britannici** e anche dall’**Australia** durante la **Seconda guerra mondiale**.

#### **Matrice di esempio (5×5)**

| M   | T   | Z   | C   | L   |
| --- | --- | --- | --- | --- |
| H   | A   | U   | I/J | E   |
| K   | F   | G   | N   | R   |
| V   | W   | X   | B   | D   |
| Q   | O   | S   | Y   | P   |

_(Le lettere I e J sono considerate equivalenti.)_

Nota: la disposizione poteva essere sia casuale che secondo keyword:

![](imgs/Pasted%20image%2020260221164624.png)

(Esistono anche varianti non quadrate...)

> **Esempio:**  
> Testo in chiaro: `DO MA NI`  

Si traccia il "rettangolino" ovvero si individuano righe e colonne corrispondenti alle due lettere:

D = 4a riga, 5a colonna; O = 5a riga, 2a colonna

Al posto della D prendo l'elemento in posizione 4a riga, 2a colonna = W
Al posto della O prendo l'elemento in posizione 5a riga, 5a colonna = P

E si procede così per le due altre coppie di lettere...

MA diventa TH;

NI è un caso particolare (stessa colonna) si utilizzano le lettere sotto di loro $\rightarrow$ BN

Risultato cifrato: WP TH BN

![](imgs/Pasted%20image%2020260221165218.png)

---

### **7. Regole del cifrario di Playfair**

1. Le **lettere ripetute** vanno separate da una **lettera di riempimento** (es. `BALLOON → BALXLOON`). Questo per garantire decifrabilità
    
2. Le lettere sulla **stessa riga** sono sostituite da quelle **immediatamente a destra** (ciclicamente).
    
3. Le lettere sulla **stessa colonna** sono sostituite da quelle **subito sotto**.
    
4. Se le due lettere del digramma formano un **rettangolo**, ciascuna è sostituita dalla lettera **sulla stessa riga** ma **nella colonna dell’altra**.
    

---

### **8. Sicurezza del cifrario di Playfair**

Rispetto alla cifratura monoalfabetica, Playfair è **molto più sicuro**, perché:

- lavora su 676 **digrammi** (non singole lettere);
    
- la chiave è fornita dalla **disposizione della matrice**;
    
- l’analisi di frequenza deve ora considerare **coppie di lettere** (es. “ES”, “ON”, “RE”, “EL”, “DE”, “ER”…).

> Sebbene la matrice Playfair contenga 25 celle con fusione I/J, lo spazio dei digrammi del linguaggio rimane di 26² = 676 combinazioni possibili, poiché la distinzione tra I e J può essere recuperata dal contesto semantico durante la decifrazione, contesto che può essere acquisito sia da mente umana che da IA.

Tuttavia, la **struttura linguistica del testo** rimane parzialmente visibile,  
quindi il cifrario non è invulnerabile a crittoanalisi avanzate.

---

### **9. In sintesi**

- I **cifrari polialfabetici** rappresentano un salto qualitativo rispetto a quelli monoalfabetici.
    
- Introducono **rotazioni e variazioni multiple** di alfabeti per rendere più difficile la crittoanalisi.
    
- L’analisi statistica deve ora estendersi a **gruppi di lettere (n-grammi)**.
    
- Le basi di queste tecniche porteranno allo sviluppo di sistemi più complessi come **Vigenère**, **Hill** e, più avanti, le **macchine Enigma**.