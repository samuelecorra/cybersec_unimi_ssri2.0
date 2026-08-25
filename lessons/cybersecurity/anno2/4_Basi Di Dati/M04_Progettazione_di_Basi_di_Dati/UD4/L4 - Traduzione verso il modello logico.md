# **M4 UD4 Lezione 4 - Traduzione verso il modello logico**

### **1. Introduzione**

La **traduzione verso il modello logico** è la fase in cui lo **schema concettuale ristrutturato** viene convertito in uno **schema logico relazionale**, cioè una rappresentazione compatibile con le strutture e i linguaggi dei DBMS relazionali.

Questa fase può includere ulteriori **ottimizzazioni** basate sulle **caratteristiche specifiche** del modello relazionale, ma senza alterare il significato informativo dello schema E–R.

> Per chiarezza, distinguiamo:
> 
> - le **relazioni E–R**, che appartengono al modello concettuale;
>     
> - le **relazioni del modello relazionale**, che corrispondono a **tabelle**.

---

### **2. Traduzione delle entità**

#### **2.1 Entità forti**

Ogni **entità forte** $E$ viene tradotta in una **tabella** $R$ che contiene:

**Attributi:**

- tutti gli attributi di $E$;
    
- la **chiave primaria**, cioè l’identificatore primario di $E$;
    
- eventuali vincoli di _non nullità_ per gli attributi obbligatori.

**Esempio:**

![](imgs/Pasted%20image%2020260821193051.png)

---

#### **2.2 Entità deboli**

Ogni **entità debole** $E$, identificata esternamente da un’entità forte $E'$, è tradotta in una tabella che contiene:

**Attributi:**

- gli attributi propri di $E$;
    
- l’identificatore primario di $E'$;
    
- gli eventuali attributi della relazione che identifica $E$ esternamente.

**Chiave primaria:**

- l’unione degli **attributi interni** di $E$ e dell’**identificatore** di $E'$.

**Vincoli:**

- _integrità referenziale_ sull’identificatore di $E'$;
    
- _non nullità_ per gli attributi obbligatori.

**Esempio:**

![](imgs/Pasted%20image%2020260821193249.png)

L’entità `IMPIEGATO` è debole rispetto a `DIPARTIMENTO`, poiché la sua identificazione dipende da essa.

---

#### **2.3 Catene di entità deboli**

In presenza di **catene di entità deboli**, la traduzione deve procedere **partendo dall’entità forte** e proseguendo lungo la catena.

**Esempio:**

![](imgs/Pasted%20image%2020260821193555.png)

Ogni entità eredita l’identificatore della precedente.

---

### **3. Traduzione delle relazioni (standard)**

#### **3.1 Regola generale**

Ogni **relazione** $R$ tra entità $E_1, E_2, \dots, E_n$ viene tradotta in una **tabella** con:

**Attributi:**

- gli attributi propri della relazione $R$;
    
- gli identificatori primari di tutte le entità partecipanti $E_1, \dots, E_n$.

**Chiave primaria:**

- l’unione delle **chiavi** delle entità partecipanti (o un suo sottoinsieme, se le cardinalità lo consentono).

**Vincoli:**

- _integrità referenziale_ sugli identificatori delle entità;
    
- vincoli come _NOT NULL_ sugli attributi obbligatori.

> 🔹 Le relazioni che legano **entità deboli** alle loro **entità forti identificanti** non vengono tradotte,  
> poiché sono già incorporate nella tabella dell’entità debole.

---

#### **3.2 Esempio di traduzione standard**

![](imgs/Pasted%20image%2020260821194058.png)

La tabella `FORNITURA` contiene:

- gli identificatori delle entità coinvolte;
    
- l’attributo proprio `quantità`;
    
- le chiavi esterne verso `DIPARTIMENTO`, `FORNITORE` e `PRODOTTO`.

---

#### **3.3 Autorelazioni**

Quando una relazione collega **un’entità con sé stessa**, la tabella risultante deve contenere **due copie** dell’identificatore della stessa entità, opportunamente **rinominate**.

**Esempio:**

![](imgs/Pasted%20image%2020260821194235.png)

Gli attributi `composto` e `componente` fanno entrambi riferimento alla chiave di `PRODOTTO`.

---

#### **3.4 Relazioni (1:n)**

Per una relazione con **cardinalità (1:n)**, la chiave primaria è l’identificatore dell’entità che partecipa con **cardinalità massima 1**.

**Esempio:**

![](imgs/Pasted%20image%2020260821194711.png)

Chiave primaria: $(c.f.)$  
Chiave esterna: $(città)$

---

#### **3.5 Relazioni (1:1)**

Per le relazioni **uno a uno**, la chiave primaria può essere **una delle due entità**, in base alle cardinalità minime.

![](imgs/Pasted%20image%2020260821194752.png)

In questi casi sono possibili **traduzioni alternative**, come mostrato nella sezione seguente.

---

### **4. Traduzioni alternative**

#### **4.1 Motivazione**

Per relazioni **(1:1)** e **(1:n)**, è possibile scegliere tra più modalità di traduzione per **ridurre il numero di tabelle** e i **join** necessari durante le interrogazioni.

#### **Svantaggio**

Le operazioni che accedono a **una sola entità** possono diventare meno efficienti, perché la tabella risultante è più grande.

---

#### **4.2 Relazioni (1:n) – Fusione nella parte 1**

È possibile **fondere la relazione** con la tabella dell’entità che partecipa con **cardinalità massima 1**.  
Se la cardinalità minima è 0, gli attributi inglobati diventano **opzionali (NULL)**.

**Esempio:**

![](imgs/Pasted%20image%2020260821194852.png)

---

#### **4.3 Esempio applicativo**

![](imgs/Pasted%20image%2020260821194922.png)

L’asterisco indica attributi **facoltativi** (potenzialmente _NULL_).

---

#### **4.4 Relazioni (1:1) – Casi possibili**

Si distinguono tre casi, in base alle **cardinalità minime**:

1. $(1,1)$ – $(1,1)$ → entrambe obbligatorie
    
2. $(0,1)$ – $(1,1)$ → una opzionale, una obbligatoria
    
3. $(0,1)$ – $(0,1)$ → entrambe opzionali

---

#### **4.5 Caso $(1,1)$ – $(1,1)$**

Entrambe le entità partecipano obbligatoriamente.

**Traduzioni possibili:**

![](imgs/Pasted%20image%2020260821195028.png)

---

#### **4.6 Caso $(0,1)$ – $(1,1)$**

Un’entità partecipa **facoltativamente**, l’altra **obbligatoriamente**.

**Traduzioni possibili:**

![](imgs/Pasted%20image%2020260821195104.png)

Gli asterischi indicano attributi **opzionali**.

---

#### **4.7 Caso $(0,1)$ – $(0,1)$**

Entrambe le entità partecipano **facoltativamente**.

**Traduzioni possibili:**

![](imgs/Pasted%20image%2020260821195147.png)

---

#### **4.8 Esempio pratico**

![](imgs/Pasted%20image%2020260821195214.png)

---

### **5. Sintesi finale**

**Abbiamo visto:**

- come tradurre **entità forti** e **deboli** in tabelle relazionali;
    
- le regole per la traduzione delle **relazioni standard** e delle loro **varianti**;
    
- i casi particolari di relazioni **(1:n)** e **(1:1)**;
    
- le **fusioni alternative** per ottimizzare lo schema logico.

**In sintesi:**

> La traduzione standard è obbligatoria per relazioni **(n:m)**,  
> ma per relazioni **(1:n)** e **(1:1)** è possibile scegliere varianti più efficienti in base alle **cardinalità minime** e al **carico operativo** del sistema informativo.

---


![](imgs/Pasted%20image%2020251125050955.png)

