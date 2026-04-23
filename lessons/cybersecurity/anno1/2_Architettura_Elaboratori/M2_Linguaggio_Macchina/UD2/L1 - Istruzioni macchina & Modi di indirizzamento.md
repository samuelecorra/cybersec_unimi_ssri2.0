# **Lezione 3: Istruzioni macchina & Modi di indirizzamento**

---

## **1. Il “vocabolario” della CPU**

L’obiettivo della lezione è rispondere a una domanda fondamentale:

> **Qual è il vocabolario dell’unità centrale?**  
> Cioè: **qual è l’insieme delle direttive che la CPU è in grado di riconoscere, decodificare ed eseguire?**

Per rispondere correttamente bisogna entrare nella **struttura interna della CPU**, che è composta da:

- **registri**,
    
- **ALU**,
    
- **Unità di Controllo**,
    
- collegamenti ai bus.
    

---

## **2. I registri: cosa sono e che ruolo hanno**

I **registri** sono:

- **contenitori di bit**,
    
- costituiti tipicamente da un insieme di **bistabili**,
    
- **non hanno iniziativa propria**,
    
- non “pensano” né decidono,
    
- **servono solo a contenere informazioni** utili all’elaborazione.
    

Sono strumenti **passivi**, al servizio dell’ALU e della Control Unit.

---

## **3. Gli elementi attivi della CPU**

Gli elementi che invece **realizzano realmente il lavoro** sono due:

### **3.1 ALU – Arithmetic Logic Unit**

È un **circuito combinatorio**, quindi:

- **senza memoria**,
    
- che esegue:
    
    - operazioni **aritmetiche** (somma, sottrazione, a volte moltiplicazione e divisione),
        
    - operazioni **logiche** (AND, OR, NOT, confronti).
        

Lavora sui dati contenuti nei **General Purpose Registers**.

L’esito dell’operazione (positivo, nullo, negativo, overflow, errore) viene salvato nel registro **Condition Codes**.

---

### **3.2 Control Unit (CU)**

È una **rete sequenziale**, quindi:

- **con memoria**,
    
- che:
    
    - acquisisce le istruzioni dalla memoria,
        
    - le decodifica,
        
    - orchestra il funzionamento dell’intera CPU,
        
    - coordina le interazioni con il mondo esterno tramite il **Control Bus**.
        

---

## **4. Registri fondamentali visibili al programmatore**

### **4.1 Program Counter (PC)**

Contiene:

> l’indirizzo della cella di memoria che contiene la **prossima istruzione da eseguire**.

È il **puntatore all’avanzamento del programma**.

---

### **4.2 General Purpose Registers (GPR)**

Sono:

- registri ad **uso generale**,
    
- contengono:
    
    - i **dati in corso di elaborazione**,
        
    - i dati necessari ai **prossimi passi di trasformazione**.
        

---

### **4.3 Condition Codes (CC)**

Contiene:

- informazioni **accessorie sull’esito dell’ultima elaborazione**,
    
- ad esempio:
    
    - segno del risultato (negativo, nullo, positivo),
        
    - overflow,
        
    - underflow.
        

NON contiene il risultato numerico, ma **informazioni sul risultato**.

---

## **5. Registri interni non visibili al programmatore**

Oltre ai registri “logici”, esistono registri **fondamentali per il funzionamento interno della CPU**, ma **non direttamente programmabili**.

---

### **5.1 Instruction Register (IR)**

Contiene:

> il **codice binario dell’istruzione attualmente in esecuzione**.

Dopo la fase di **Fetch**, l’istruzione:

- viene copiata dalla memoria nell’IR,
    
- per poter essere decodificata.
    

---

### **5.2 Memory Address Register (MAR)**

Contiene:

> l’**indirizzo della cella** del dispositivo slave che la CPU vuole leggere o scrivere.

Serve a **emettere l’indirizzo sull’Address Bus**.

---

### **5.3 Memory Data Register (MDR)**

Contiene:

- il **dato che va dalla CPU al bus** durante una scrittura,
    
- oppure il **dato che arriva dal bus alla CPU** durante una lettura.
    

IR, MAR e MDR sono:

> le **finestre della CPU sui bus**.

---

## **6. Il progetto della CPU e la ISA**

Il progetto di una CPU è definito da:

- i suoi **elementi interni**,
    
- il suo **insieme di istruzioni**, detto formalmente:
    

> **ISA – Instruction Set Architecture**

---

## **7. Cos’è la ISA**

La **ISA** è:

> l’insieme delle **istruzioni macchina elementari** che una specifica CPU è in grado di:
> 
> - riconoscere,
>     
> - decodificare,
>     
> - eseguire.
>     

È il **vocabolario ufficiale** della CPU.

La **codifica binaria delle istruzioni** costituisce il **linguaggio macchina** di quella CPU.

---

## **8. Struttura generale di un’istruzione macchina**

Ogni istruzione macchina è composta da **due parti fondamentali**:

### **8.1 Opcode (codice operativo)**

Specifica:

> **che tipo di operazione** deve eseguire la CPU.

---

### **8.2 Operandi (operands)**

Sono:

- i **dati**,
    
- oppure gli **indirizzi** dei dati,
    
- necessari allo svolgimento corretto dell’istruzione.
    

---

## **9. Tipologie fondamentali di istruzioni macchina**

### **1) Istruzioni operative**

Richiedono alla CPU di svolgere:

- operazioni aritmetiche,
    
- operazioni logiche,
    

utilizzando l’**ALU**.

---

### **2) Istruzioni di trasferimento**

Servono a:

- prelevare dati da memoria o da I/O,
    
- caricarli nei GPR,
    
- riscrivere i risultati in memoria o periferiche.
    

---

### **3) Istruzioni di controllo**

Servono a:

> **modificare il flusso sequenziale di esecuzione delle istruzioni**.

Sono i **salti (branch)**:

- condizionati,
    
- incondizionati.
    

Sono **l’essenza stessa della programmazione**.

---

## **10. I modi di indirizzamento**

I **modi di indirizzamento** sono:

> le diverse modalità con cui il linguaggio macchina permette di **recuperare i dati necessari** per l’esecuzione delle istruzioni.

---

### **10.1 Indirizzamento immediato**

Il dato:

- è **contenuto direttamente nell’istruzione macchina**.
    

Durante il Fetch vengono già prelevate anche le informazioni necessarie all’esecuzione.

---

### **10.2 Indirizzamento diretto**

L’istruzione fornisce:

> l’**indirizzo della cella di memoria** che contiene il dato.

È necessario:

- **un secondo accesso a memoria** per leggere il dato vero e proprio.
    

---

### **10.3 Indirizzamento indiretto**

L’istruzione fornisce:

- un **indirizzo intermedio**,
    
- che punta a una cella che **contiene a sua volta l’indirizzo finale del dato**.
    

Sono necessari:

- **due accessi a memoria**.
    

---

### **10.4 Indirizzamento Base + Offset**

È un modo di indirizzamento **largamente diffuso**.

L’istruzione fornisce:

- un **registro base (GPR)**,
    
- un **offset costante**.
    

L’indirizzo finale si ottiene come:

$$  
\text{indirizzo} = \text{registro base} + \text{offset}  
$$

Questo indirizzamento:

- usa **un solo accesso a memoria**,
    
- permette indirizzi **dinamici** perché il registro base può cambiare valore durante l’esecuzione.
    

---

