# **M4 UD4 Lezione 2 - Ristrutturazione di schemi E-R - Eliminazione delle gerarchie**

### **1. Introduzione**

Durante la fase di **ristrutturazione di uno schema E–R**, le **gerarchie di generalizzazione/specializzazione** devono essere **eliminate**, poiché i **DBMS relazionali** non consentono di rappresentarle direttamente.  
Esistono **tre principali strategie** per gestire la loro eliminazione:

1. **Accorpamento delle figlie nel padre** (_collasso verso l’alto_)
    
2. **Accorpamento del padre nelle figlie** (_collasso verso il basso_)
    
3. **Mantenimento delle entità** (tramite relazioni di collegamento)

Ognuna di queste soluzioni presenta **vantaggi, svantaggi e vincoli di applicabilità**, e la scelta dipende dal **tipo di gerarchia** (totale/parziale, esclusiva/sovrapposta) e dalle **caratteristiche delle operazioni applicative**.

---

### **2. Le fasi della ristrutturazione**

Ricordiamo che la ristrutturazione di schemi E–R comprende quattro fasi fondamentali:

1. Analisi delle ridondanze
    
2. Eliminazione delle gerarchie
    
3. Partizionamento o accorpamento di entità e relazioni
    
4. Scelta degli identificatori primari

In questa lezione ci concentriamo sulla **fase 2: eliminazione delle gerarchie**.

---

### **3. Motivazione dell’eliminazione**

Le **gerarchie** non possono essere rappresentate direttamente nel modello relazionale, perché:

- le **relazioni** tra padre e figlie non hanno un corrispettivo naturale in tabelle SQL;
    
- la **generalizzazione** non è un costrutto nativo del modello relazionale;
    
- occorre quindi **“appiattire”** o **riconfigurare** la struttura per rendere esplicite le informazioni.

---

### **4. Approcci possibili**

#### **Le tre strategie di trasformazione:**

1. **Accorpamento delle figlie nel padre** → _collasso verso l’alto_
    
2. **Accorpamento del padre nelle figlie** → _collasso verso il basso_
    
3. **Mantenimento delle entità** → _sostituzione del legame gerarchico con relazioni esplicite_

---

## **5. Accorpamento delle figlie nel padre (collasso verso l’alto)**

### **a) Descrizione**

- Le **entità figlie** vengono **eliminate**.
    
- Gli **attributi e le relazioni** delle figlie vengono **trasferiti al padre**, diventando **opzionali**.
    
    - Di conseguenza, la **cardinalità minima** degli attributi e delle relazioni dalla parte dell’entità padre diventa **0**.
    
- Vengono aggiunti **uno o più attributi “selettori”** per indicare a quale o quali figlie appartiene ogni istanza del padre.

---

### **b) Selettori**

A seconda del tipo di gerarchia, si introducono selettori con regole diverse:

#### **Gerarchia esclusiva**

- Si usa **un solo selettore**.
    
- Se la gerarchia è **totale**, il dominio del selettore ha **N valori** (uno per ciascuna figlia).
    
- Se la gerarchia è **parziale**, il dominio del selettore ha **N + 1 valori**, includendo il caso “nessuna figlia”.

#### **Gerarchia sovrapposta**

- Si introducono **N selettori booleani** (_vero/falso_), uno per ogni figlia.
    
- Se la gerarchia è **totale**, almeno un selettore deve essere _vero_.
    
- Se è **parziale**, è ammesso che tutti siano _falsi_.

---

### **c) Esempio grafico semplificato**

Da:

```
     E
   /   \
  E1   E2
```

A:

```
E (a1, a2, sel1, sel2)
```

Gli attributi e le relazioni di _E1_ ed _E2_ vengono spostati in _E_, con cardinalità minima 0.

---

### **d) Vantaggi**

- **Conveniente** quando le operazioni **non distinguono** tra le occorrenze del padre e delle figlie.
    
- Permette di accedere a **un’unica entità** invece di più entità collegate.
    
- Semplifica la struttura eliminando entità intermedie.

### **e) Svantaggi**

- Gli attributi o le relazioni **obbligatori** nelle figlie diventano **opzionali** nel padre.
    
- Si generano **molti valori nulli**, specialmente in presenza di numerose specializzazioni.

---

## **6. Accorpamento del padre nelle figlie (collasso verso il basso)**

### **a) Descrizione**

- L’**entità padre** viene **eliminata**.
    
- Tutti i **suoi attributi e relazioni** vengono **replicati** in ciascuna delle figlie.
    
- Le entità che erano in relazione con il padre diventano in relazione **opzionale** con le figlie (cardinalità minima 0).

---

### **b) Condizioni di applicabilità**

- È **possibile solo se la gerarchia è totale**.
    
    - Se la gerarchia è **parziale**, bisogna aggiungere una **nuova entità figlia “fittizia”** che rappresenta le istanze del padre non appartenenti ad alcuna figlia.

---

### **c) Esempio grafico semplificato**

Da:

```
     E
   /   \
  E1   E2
```

A:

```
E1 (attributi E + attributi E1)
E2 (attributi E + attributi E2)
```

---

### **d) Vantaggi**

- Conveniente se le operazioni **accedono separatamente alle figlie**.
    
- Utile quando ci sono **molti attributi di specializzazione**, poiché il collasso verso l’alto genererebbe troppi nulli.

### **e) Svantaggi**

- **Non applicabile** in gerarchie parziali (a meno di aggiungere una figlia fittizia).
    
- In caso di **gerarchie sovrapposte**, si introducono **ridondanze**, poiché un’istanza può appartenere a più figlie e avere quindi **attributi duplicati**.

---

## **7. Mantenimento delle entità**

### **a) Descrizione**

- Le **entità padre e figlie vengono mantenute**.
    
- Il **legame gerarchico** viene sostituito da **relazioni esplicite** tra il padre e ciascuna delle figlie.
    
- Le figlie vengono identificate **esternamente** tramite la relazione con il padre (diventano **entità deboli**).
    
- È necessario introdurre **vincoli aggiuntivi** per simulare il comportamento della gerarchia.

---

### **b) Vincoli derivati dalla gerarchia**

|Tipo di gerarchia|Vincolo da applicare|
|---|---|
|**Esclusiva**|Ogni istanza del padre può partecipare **a una sola** relazione con una figlia.|
|**Totale**|Ogni istanza del padre deve partecipare **ad almeno una** relazione con una figlia.|

---

### **c) Esempio grafico semplificato**

Da:

```
     E
   /   \
  E1   E2
```

A:

```
E ──< Rg1 >── E1
E ──< Rg2 >── E2
```

Ogni figlia è legata al padre tramite una relazione con cardinalità (1,1).

---

### **d) Vantaggi**

- È **sempre applicabile**, indipendentemente dal tipo di gerarchia.
    
- Conveniente se le operazioni accedono **solo alle figlie**, senza bisogno di combinare attributi del padre e delle figlie.

### **e) Svantaggi**

- Le operazioni che accedono **congiuntamente** agli attributi di padre e figlie richiedono **più accessi** (join).
    
- Occorre **mantenere esplicitamente i vincoli** di esclusività e totalità.

---

## **8. Criteri di scelta dell’approccio**

La decisione tra le tre strategie dipende da:

- **Copertura** della gerarchia (totale o parziale);
    
- **Esclusività** (esclusiva o sovrapposta);
    
- **Carico operativo** (frequenza di accesso a padre o figlie);
    
- **Spazio di memoria** e **costo degli accessi**.

> Non esiste una soluzione migliore in assoluto.  
> La scelta ottimale varia in base al **profilo d’uso** del sistema e al **tipo di dati** gestiti.

---

### **Esempio di valutazione**

- Il **mantenimento delle entità** può richiedere più accessi a memoria secondaria,  
    ma le entità risultano **più piccole e snelle**, consentendo di recuperare **più tuple per singolo accesso fisico**.
    
- L’**accorpamento verso l’alto** riduce il numero di entità ma può introdurre **molti valori nulli**.
    
- L’**accorpamento verso il basso** elimina il padre ma può duplicare i dati.

---

## **9. Ristrutturazioni ibride**

In pratica, è possibile applicare **approcci misti**, combinando più strategie per differenti parti dello schema.

#### **Esempio**

Una parte della gerarchia può essere **collassata verso l’alto**,  
mentre un’altra può essere **mantenuta tramite relazioni esplicite**,  
a seconda delle esigenze operative.

---

## **10. Sintesi finale**

**Abbiamo visto:**

- le **tre principali strategie** per eliminare le gerarchie E–R:
    
    - **accorpamento delle figlie nel padre**,
        
    - **accorpamento del padre nelle figlie**,
        
    - **mantenimento delle entità**;
    
- i relativi **vantaggi, svantaggi e vincoli**;
    
- i **criteri pratici** per scegliere la soluzione più efficiente.

**Ricorda:**

> Nessuna strategia è universalmente migliore.  
> La scelta dipende da un **equilibrio** tra:
> 
> - spazio occupato,
>     
> - efficienza delle operazioni,
>     
> - e complessità dei vincoli da mantenere.

---


![](imgs/Pasted%20image%2020251125050823.png)

![](imgs/Pasted%20image%2020251125050849.png)

![](imgs/Pasted%20image%2020251125050911.png)

