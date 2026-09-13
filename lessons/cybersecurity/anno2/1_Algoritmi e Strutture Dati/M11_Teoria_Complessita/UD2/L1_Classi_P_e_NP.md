## **Lezione 1 — P, NP, Polynomial-Time Reductions and NP-Completeness / Classi P e NP, riduzioni polinomiali e NP-completezza**

### **1. Introduzione**

Nelle lezioni precedenti abbiamo visto:

- la distinzione tra **trovare** e **verificare** una soluzione;
- il concetto di **polynomial certificate / certificato polinomiale**;
- la **nondeterministic computation / computazione non deterministica**;
- la simulazione deterministica mediante **enumeration / enumerazione**.

Ora possiamo formalizzare la classificazione dei problemi computazionali e introdurre lo strumento fondamentale per confrontarne la difficoltà:

\[ \boxed{\text{Polynomial-Time Reduction / riduzione in tempo polinomiale}}. \]

Da questo nasceranno le nozioni di:

\[ \boxed{P,\quad NP,\quad NP\text{-hard},\quad NP\text{-complete}}. \]

---

# **2. Le classi P e NP**

## **2.1 P — Polynomial Time**

La classe:

\[ \boxed{P} \]

contiene tutti i **decision problems / problemi decisionali** risolvibili da un algoritmo **deterministico** in tempo polinomiale.

Formalmente:

\[ \boxed{ P= \{L\mid L\text{ è decidibile deterministicamente in tempo polinomiale}\}. } \]

In termini intuitivi, $P$ rappresenta la classe dei problemi considerati **efficientemente risolvibili** nel modello teorico della complessità.

---

## **2.2 NP — Nondeterministic Polynomial Time**

La classe:

\[ \boxed{NP} \]

contiene tutti i problemi decisionali risolvibili in tempo polinomiale da un algoritmo **non deterministico**.

Equivalentemente:

> un problema appartiene a $NP$ se ogni sua **YES-instance** possiede un certificato di lunghezza polinomiale verificabile deterministicamente in tempo polinomiale.

Quindi abbiamo le due caratterizzazioni equivalenti:

\[ \boxed{ \text{Nondeterministic Polynomial Time} \iff \text{Polynomial-Time Verification} } \]

---

# **3. Relazione tra P e NP**

Ogni algoritmo deterministico può essere considerato un caso particolare di algoritmo non deterministico.

Pertanto:

\[ \boxed{P\subseteq NP}. \]

La grande questione aperta è capire se questa inclusione sia stretta oppure no:

\[ \boxed{P\stackrel{?}{=}NP}. \]

Equivalentemente:

> **Tutto ciò che può essere verificato in tempo polinomiale può anche essere trovato e deciso deterministicamente in tempo polinomiale?**

Ad oggi non sappiamo se:

\[ P=NP \]

oppure:

\[ P\neq NP. \]

---

# **4. Polynomial-Time Reduction / Riduzione in tempo polinomiale**

Per confrontare formalmente la difficoltà di due problemi utilizziamo le **riduzioni polinomiali**.

Dati due problemi decisionali $A$ e $B$, scriviamo:

\[ \boxed{A\leq_P B} \]

oppure, nella notazione della docente:

\[ A\preceq B. \]

Si legge:

> **$A$ si riduce in tempo polinomiale a $B$.**

Significa che esiste una funzione:

\[ f:\text{istanze di }A\rightarrow\text{istanze di }B \]

tale che:

1. $f$ è calcolabile deterministicamente in tempo polinomiale;
2. per ogni istanza $x$:

\[ \boxed{ x\in A \iff f(x)\in B. } \]

In altre parole, trasformiamo efficientemente un'istanza di $A$ in un'istanza equivalente di $B$.

---

# **5. Come va interpretata la direzione della riduzione**

Se:

\[ A\leq_P B, \]

stiamo dicendo:

> **se sapessi risolvere efficientemente $B$, saprei risolvere efficientemente anche $A$.**

Infatti:

\[ A \xrightarrow{\ f\ } B \xrightarrow{\text{algoritmo per }B} YES/NO. \]

Quindi $B$ è, intuitivamente, **almeno difficile quanto $A$** rispetto alle riduzioni polinomiali.

Questa direzione è fondamentale:

\[ \boxed{ A\leq_P B \quad\Rightarrow\quad B\text{ è almeno difficile quanto }A. } \]

---

# **6. Conseguenze fondamentali delle riduzioni**

Se:

\[ A\leq_P B \]

allora valgono in particolare queste proprietà.

### Se $B\in P$

\[ \boxed{ A\leq_P B \land B\in P \Rightarrow A\in P. } \]

Perché possiamo trasformare $A$ in $B$ e poi usare l'algoritmo polinomiale per $B$.

---

### Se $B\in NP$

\[ \boxed{ A\leq_P B \land B\in NP \Rightarrow A\in NP. } \]

---

### Uso contrario per dimostrare difficoltà

Se sappiamo che $A$ è un problema difficile e mostriamo:

\[ A\leq_P B, \]

allora stiamo trasferendo la difficoltà verso $B$.

Questa sarà precisamente la tecnica utilizzata per dimostrare la **NP-hardness**.

> Non è invece corretto affermare in generale che una riduzione polinomiale trasferisca automaticamente uno specifico lower bound $\Omega(p(n))$ da $A$ a $B$ nella stessa identica forma: bisogna considerare anche il costo della riduzione e la dimensione dell'istanza prodotta.

---

# **7. Transitivity / Transitività delle riduzioni**

Le riduzioni polinomiali sono transitive.

Se:

\[ A\leq_P B \]

e:

\[ B\leq_P C, \]

allora:

\[ \boxed{A\leq_P C}. \]

Infatti possiamo comporre le due trasformazioni polinomiali:

\[ A\rightarrow B\rightarrow C. \]

Questa proprietà è ciò che rende possibile costruire una rete di problemi NP-completi partendo da pochi problemi iniziali.

---

# **8. NP-hard / NP-difficile**

Un problema $A$ è:

\[ \boxed{\text{NP-hard / NP-difficile}} \]

se ogni problema appartenente a $NP$ si riduce polynomialmente ad $A$.

Formalmente:

\[ \boxed{ \forall B\in NP,\quad B\leq_P A. } \]

Quindi $A$ è almeno difficile quanto ogni problema appartenente a $NP$.

Attenzione:

> Un problema NP-hard **non deve necessariamente appartenere a $NP$**.

Può anche non essere un problema decisionale oppure trovarsi al di fuori di $NP$.

---

# **9. NP-complete / NP-completo**

Un problema $A$ è:

\[ \boxed{\text{NP-complete / NP-completo}} \]

se valgono contemporaneamente:

\[ \boxed{A\in NP} \]

e:

\[ \boxed{A\text{ è NP-hard}}. \]

Quindi:

\[ \boxed{ NP\text{-complete} = NP \cap NP\text{-hard}. } \]

I problemi NP-completi sono quindi i problemi **più difficili all'interno di $NP$**, nel senso delle riduzioni polinomiali.

---

# **10. Perché gli NP-completi sono così importanti?**

Supponiamo che $C$ sia NP-completo.

Per definizione:

\[ \forall A\in NP,\quad A\leq_P C. \]

Ora immaginiamo di trovare un algoritmo deterministico polinomiale per $C$.

Avremmo:

\[ A \leq_P C \in P. \]

Quindi:

\[ A\in P \]

per ogni $A\in NP$.

Da cui:

\[ NP\subseteq P. \]

Ma sappiamo già che:

\[ P\subseteq NP. \]

Pertanto:

\[ \boxed{P=NP}. \]

Quindi:

> **Se trovassimo un algoritmo deterministico polinomiale per un solo problema NP-completo, allora tutti i problemi di $NP$ diventerebbero polynomial-time solvable.**

---

# **11. Cook–Levin Theorem / Teorema di Cook–Levin**

Il risultato fondamentale che diede origine alla teoria della NP-completezza è il:

\[ \boxed{\text{Cook–Levin Theorem}} \]

del 1971.

Il suo enunciato standard è:

\[ \boxed{\text{SAT è NP-completo}.} \]

---

## **11.1 SAT — Boolean Satisfiability Problem / Problema di soddisfacibilità booleana**

Data una formula booleana, SAT chiede:

> Esiste un'assegnazione di valori TRUE/FALSE alle variabili che renda vera la formula?

Per esempio:

\[ (x\lor y)\land(\neg x\lor z). \]

La domanda è se esista almeno un'assegnazione di:

\[ x,y,z\in\{TRUE,FALSE\} \]

che renda vera l'intera formula.

---

## **11.2 Significato del teorema**

Cook–Levin dimostra che:

1. $SAT\in NP$;
2. ogni problema $A\in NP$ può essere ridotto polynomialmente a SAT:

\[ \boxed{ \forall A\in NP,\quad A\leq_P SAT. } \]

Quindi SAT è contemporaneamente:

\[ SAT\in NP \]

e:

\[ SAT\text{ è NP-hard}. \]

Pertanto:

\[ \boxed{SAT\text{ è NP-completo}.} \]

---

# **12. Nota sul Bounded Tiling / Domino Limitato**

Nelle slide della docente il **Bounded Tiling Problem / Problema del Domino Limitato** viene utilizzato come problema fondamentale.

È corretto studiarlo come problema NP-completo e utilizzarlo come base per successive riduzioni.

Va però distinto dall'enunciato standard del Cook–Levin Theorem:

\[ \boxed{\text{Cook--Levin dimostra direttamente che SAT è NP-completo}.} \]

Una volta stabilita la NP-completezza di SAT, si possono poi dimostrare NP-completi molti altri problemi, tra cui opportune formulazioni di **Bounded Tiling**, mediante riduzioni polinomiali.

Quindi, concettualmente:

\[ SAT \leq_P \text{Bounded Tiling} \]

insieme all'appartenenza del Bounded Tiling a $NP$, permette di stabilirne la NP-completezza.

---

# **13. Come si dimostra che un nuovo problema è NP-completo**

Supponiamo di voler dimostrare che un nuovo problema $A$ sia NP-completo.

La procedura standard è composta da **due parti**.

## **Passo 1 — Dimostrare che $A\in NP$**

Mostriamo che una YES-solution di $A$ possiede un certificato polinomiale verificabile in tempo polinomiale.

\[ \boxed{A\in NP} \]

---

## **Passo 2 — Dimostrare che $A$ è NP-hard**

Prendiamo un problema $B$ già noto NP-completo e costruiamo:

\[ \boxed{B\leq_P A}. \]

Attenzione alla direzione:

\[ \boxed{ \text{problema NP-completo noto} \longrightarrow \text{nuovo problema} } \]

e **non il contrario**.

---

## **Perché basta un solo problema NP-completo?**

Poiché $B$ è NP-completo:

\[ \forall X\in NP,\quad X\leq_P B. \]

Se inoltre dimostriamo:

\[ B\leq_P A, \]

per transitività:

\[ X\leq_P B\leq_P A \]

e quindi:

\[ \forall X\in NP,\quad X\leq_P A. \]

Pertanto $A$ è NP-hard.

Dato che avevamo già dimostrato:

\[ A\in NP, \]

concludiamo:

\[ \boxed{A\text{ è NP-completo}.} \]

---

# **14. Schema da ricordare all'esame**

Per dimostrare:

\[ \boxed{A\text{ NP-completo}} \]

devo mostrare:

\[ \boxed{ \underbrace{A\in NP}_{\text{certificato + verifier}} } \]

e:

\[ \boxed{ \underbrace{B\leq_P A}_{B\text{ già NP-completo}} } \]

Quindi:

\[ \boxed{ A\in NP \quad+\quad B\leq_P A \quad\Longrightarrow\quad A\text{ NP-completo}. } \]

Questo è lo schema fondamentale.

---

# **15. Esempi classici di problemi NP-completi**

Tra i problemi NP-completi più importanti troviamo:

- **SAT — Boolean Satisfiability Problem / soddisfacibilità booleana**;
- **3-SAT**;
- **CLIQUE / problema della cricca**;
- **Independent Set / insieme indipendente**;
- **Vertex Cover / copertura di vertici**;
- **Hamiltonian Cycle / circuito hamiltoniano**;
- **3-COLORABILITY / 3-colorabilità**;
- **Decision TSP / TSP decisionale**;
- **Subset Sum / somma di sottoinsiemi**;
- **Partition / partizione**;
- **Decision Knapsack / versione decisionale dello zaino**;
- **3-Dimensional Matching — 3DM / abbinamento tridimensionale**;
- opportune formulazioni del **Bounded Tiling Problem / Domino Limitato**.

Attenzione alle formulazioni: per esempio,

\[ 2\text{-COLORABILITY}\in P \]

mentre:

\[ 3\text{-COLORABILITY} \]

è NP-completo.

Analogamente, quando si parla di NP-completezza di problemi di ottimizzazione come TSP o Knapsack, si intende formalmente la loro **versione decisionale**.

---

# **16. Mappa concettuale finale**

Possiamo finalmente organizzare tutto il modulo.

### Problemi risolvibili deterministicamente in tempo polinomiale

\[ \boxed{P} \]

e:

\[ P\subseteq NP. \]

---

### Problemi verificabili in tempo polinomiale

\[ \boxed{NP} \]

equivalentemente risolvibili in tempo polinomiale da una macchina non deterministica.

---

### Problemi almeno difficili quanto ogni problema di NP

\[ \boxed{NP\text{-hard}}. \]

---

### Problemi contemporaneamente in NP e NP-hard

\[ \boxed{NP\text{-complete}}. \]

Quindi:

\[ \boxed{ NP\text{-complete} = NP\cap NP\text{-hard}. } \]

---

# **17. Le frasi da sapere perfettamente all'esame**

### **1. P**

> $P$ è la classe dei problemi decisionali risolvibili deterministicamente in tempo polinomiale.

---

### **2. NP**

> $NP$ è la classe dei problemi decisionali risolvibili nondeterministicamente in tempo polinomiale; equivalentemente, le YES-instances possiedono certificati polinomiali verificabili deterministicamente in tempo polinomiale.

---

### **3. Riduzione polinomiale**

\[ A\leq_P B \]

significa che possiamo trasformare in tempo polinomiale un'istanza di $A$ in un'istanza equivalente di $B$.

La direzione va letta come:

\[ \boxed{B\text{ è almeno difficile quanto }A}. \]

---

### **4. NP-hard**

\[ \boxed{ A\text{ NP-hard} \iff \forall B\in NP,\ B\leq_P A. } \]

---

### **5. NP-complete**

\[ \boxed{ A\text{ NP-completo} \iff A\in NP \land A\text{ NP-hard}. } \]

---

### **6. Metodo pratico per dimostrare NP-completezza**

\[ \boxed{ A\in NP \quad+\quad B\leq_P A \text{ con }B\text{ NP-completo} } \]\[ \boxed{\Longrightarrow A\text{ NP-completo}.} \]

---

### **7. Cook–Levin**

\[ \boxed{SAT\text{ è NP-completo}.} \]

---

# **18. Idea conclusiva del modulo**

L'intero percorso può essere riassunto così:

\[ \boxed{ \text{VERIFY} \longrightarrow NP } \]\[ \boxed{ \text{DETERMINISTIC POLYNOMIAL SOLVE} \longrightarrow P } \]

e:

\[ \boxed{ P\subseteq NP. } \]

Le riduzioni permettono poi di confrontare la difficoltà dei problemi:

\[ A\leq_P B \Rightarrow B\text{ è almeno difficile quanto }A. \]

I problemi che stanno contemporaneamente in $NP$ e sono almeno difficili quanto tutto $NP$ sono:

\[ \boxed{NP\text{-complete}}. \]

Ed è proprio su questi problemi che si concentra la grande domanda:

\[ \boxed{P\stackrel{?}{=}NP}. \]

Se anche **un solo** problema NP-completo venisse risolto deterministicamente in tempo polinomiale, allora:

\[ \boxed{P=NP}. \]

Viceversa, dimostrare:

\[ P\neq NP \]

implicherebbe che **nessun problema NP-completo** può appartenere a $P$.