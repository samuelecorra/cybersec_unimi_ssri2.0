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

### **5. Accorpamento delle figlie nel padre (collasso verso l’alto)**

#### **a) Descrizione**

- Le **entità figlie** vengono **eliminate**.
- Gli **attributi e le relazioni** delle figlie vengono **trasferiti al padre**, diventando **opzionali**.
  - Di conseguenza, la **cardinalità minima** degli attributi e delle relazioni dalla parte dell’entità padre diventa **0**.
- Vengono aggiunti **uno o più attributi “selettori”** per indicare a quale o quali figlie appartiene ogni istanza del padre.

---

#### **b) Selettori**

A seconda del tipo di gerarchia, si introducono selettori con regole diverse:

##### **Gerarchia esclusiva**

- Si usa **un solo selettore**.
- Se la gerarchia è **totale**, il dominio del selettore ha **N valori** (uno per ciascuna figlia).
- Se la gerarchia è **parziale**, il dominio del selettore ha **N + 1 valori**, includendo il caso “nessuna figlia”.

##### **Gerarchia sovrapposta**

- Si introducono **N selettori booleani** (_vero/falso_), uno per ogni figlia.
- Se la gerarchia è **totale**, almeno un selettore deve essere _vero_.
- Se è **parziale**, è ammesso che tutti siano _falsi_.

---

#### **c) Esempio grafico semplificato**

Partiamo dallo schema sulla sinistra dell'immagine, composto da 6 entità e 3 relazioni.

C'è legame gerarchico tra $E$ e le due entità figlie $E1$ ed $E2$.
Supponiamo che tale legame sia sovrapposto, e per l'esempio non ci interessa totale/parziale, ergo scriveremo solamente:

$$
(\ \ \  , s)
$$

Le cardinalità rappresentate con carattere $italico$ sono volutamente lasciate generiche:

$$
(x,y) \ e \ (z, w)
$$

Ora ristrutturiamo: eliminiamo le due entità figlie e poniamo i loro ex-attributi nel padre.

- $a1$ era attributo scalare e pertanto la sua cardinalità passa a $(0, 1)$

- $a2$ non era scalare: la sua cardinalità minima $x$ passa dunque a 0, mentre la massima $y$ rimane invariata.

Dunque li abbiamo resi entrambi opzionali.

Ora invece dobbiamo refactorare le cardinalità che sussistono tra:

- $E1$ ed $R1$ (non specificata inizalmente $\implies$ scalare, 1,1)
- $E2$ ed $R2$ (z,w)

Entrambe le partecipazioni diventano opzionali ergo:

- 0,1
- 0,w

Infine possiamo aggiungere i due attributi che fungono da selettori all'entita $E$ così ristrutturata.

![](imgs/Pasted%20image%2020260821105017.png)

---

#### **d) Vantaggi**

- **Conveniente** quando le operazioni **non distinguono** tra le occorrenze del padre e delle figlie.
- Permette di accedere a **un’unica entità** invece di più entità collegate.
- Semplifica la struttura eliminando entità intermedie.

#### **e) Svantaggi**

- Gli attributi o le relazioni **obbligatori** nelle figlie diventano **opzionali** nel padre.
- Si generano **molti valori nulli**, specialmente in presenza di numerose specializzazioni.

---

### **6. Accorpamento del padre nelle figlie (collasso verso il basso)**

#### **a) Descrizione**

- L’**entità padre** viene **eliminata**.
- Tutti i **suoi attributi e relazioni** vengono **replicati** in ciascuna delle figlie.
- Le entità che erano in relazione con il padre diventano in relazione **opzionale** con le figlie (cardinalità minima 0).

---

#### **b) Condizioni di applicabilità**

- È **possibile solo se la gerarchia è totale**.
  - Se la gerarchia è **parziale**, bisogna aggiungere una **nuova entità figlia “fittizia”** che rappresenta le istanze del padre non appartenenti ad alcuna figlia.

---

#### **c) Esempio grafico semplificato**

ATTENZIONE: quando eliminiamo il padre $E$, la relazione $R$ che lo collegava ad $E3$ soprastante non può diventare **ternaria** (perché non è possibile collegare $E3$ a due entità figlie contemporaneamente).
La soluzione corretta è duplicarla come $R'$ ed $R''$, ciascuna delle quali sarà collegata ad $E3$ ora con cardinalità/partecipazione opzionale, ergo passiamo da (x,y) a (0,y).

Le cardinalità invece verso il padre (z,w) rimangono invariate e duplicate verso le due figlie ora che il padre si è volatilizzato!

Gli attributi sono stati - come si può vedere - clonati correttamente su entrambe le figlie.

N.B.: ristrutturando così lo schema in realtà c'è una perdita di informazione: se $x$ fosse stata 1, stiamo perdendo il fatto che per ogni istanza di $E3$ doveva esserci almeno un'istanza dall'altra parte, che appartenga ad $E1$ o ad $E2$ non ci interessa, ma il punto è che non possiamo più garantire tale vincolo.

Questa restrizione andrebbe espressa come vincolo aggiuntivo, in quanto lo schema non va più bene, e lo posso fare nei vincoli d'integrità.

Se tra padre e figlie ci fosse stata una gerarchia parziale quindi (p, ), allora avremmo dovuto aggiungere una $E6$ fittizia aggiuntiva che sarebbe andata a raggrupparci tutte le istanze del padre che non appartengono né a $E1$ né a $E2$, e quindi avremmo potuto replicare correttamente la relazione $R$ verso $E3$ senza perdere informazione.

![](imgs/Pasted%20image%2020260821150713.png)

---

#### **d) Vantaggi**

- Conveniente se le operazioni **accedono separatamente alle figlie**.
- Utile quando ci sono **molti attributi di specializzazione**, poiché il collasso verso l’alto genererebbe troppi nulli.

#### **e) Svantaggi**

- **Non applicabile** in gerarchie parziali (a meno di aggiungere una figlia fittizia).
- In caso di **gerarchie sovrapposte**, si introducono **ridondanze**, poiché un’istanza può appartenere a più figlie e avere quindi **attributi duplicati**.

---

### **7. Mantenimento delle entità**

#### **a) Descrizione**

- Le **entità padre e figlie vengono mantenute**.
- Il **legame gerarchico** viene sostituito da **relazioni esplicite** tra il padre e ciascuna delle figlie.
- Le figlie vengono identificate **esternamente** tramite la relazione con il padre (diventano **entità deboli**).
- È necessario introdurre **vincoli aggiuntivi** per simulare il comportamento della gerarchia.

---

#### **b) Vincoli derivati dalla gerarchia**

| Tipo di gerarchia | Vincolo da applicare                                                                |
| ----------------- | ----------------------------------------------------------------------------------- |
| **Esclusiva**     | Ogni istanza del padre può partecipare **a una sola** relazione con una figlia.     |
| **Totale**        | Ogni istanza del padre deve partecipare **ad almeno una** relazione con una figlia. |

---

#### **c) Esempio grafico semplificato**

Come si può notare abbiamo aggiustato le cardinalità dopo aver rimosso la freccia gerarchica.

Inoltre, abbiamo apposto ad entrambi i lati, appena sopra esternamente alle figlie, la linea secante con pallino pieno a quella che collega figlie a Rg1 ed Rg2 come identificatore esterno. Questo accade poiché se prima ereditavano l'identificatore dal padre, ora che abbiamo tolto il legame gerarchico ne necessitano uno ex-novo, e possiamo farlo esternamente mutandole in entità deboli, denotando la loro partecipazione alle relazioni che le collegano all'entità padre.

Anche qui si noti che in realtà c'è una perdita di informazione: nel caso in cui la gerarchia fosse esclusiva, bisogna aggiungere un vincolo che ci dica che o vale Rg1 o vale Rg2 per una singola istanza.

L'altro caso da valutare è quello di gerarchia totale, che richiede un vincolo appropriato a certificare che per ogni istanza di E si abbia "qualcuno" tra E1 ed E2 dall'altra parte sicuramente.

![](imgs/Pasted%20image%2020260821152059.png)

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

![](imgs/Pasted%20image%2020260821153000.png)

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
> - efficienza delle operazioni,
> - e complessità dei vincoli da mantenere.

---

![](imgs/Pasted%20image%2020251125050823.png)

![](imgs/Pasted%20image%2020251125050849.png)

![](imgs/Pasted%20image%2020251125050911.png)
