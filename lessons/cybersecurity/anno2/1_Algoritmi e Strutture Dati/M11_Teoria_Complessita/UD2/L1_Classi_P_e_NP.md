
## **Lezione 1: Classi P e NP**

### **1. Introduzione**

Dopo aver distinto i problemi facili e difficili nel modulo precedente, in questa lezione si introduce la **classificazione formale dei problemi computazionali** in base al loro **grado di difficoltà**.  
L’obiettivo è comprendere come, partendo da una definizione rigorosa di “tempo polinomiale”, si possano individuare classi di problemi accomunati dallo stesso livello di complessità.

---

### **2. Questione aperta: P vs NP**

Abbiamo visto che un **algoritmo polinomiale deterministico** può simulare, in un certo senso, un **algoritmo non deterministico** nel visitare l’albero delle scelte.  
La domanda fondamentale che guida la teoria della complessità è quindi:

> **Esiste una simulazione polinomiale di un algoritmo non deterministico tramite un algoritmo deterministico?**

In altre parole:

> **P = NP ?**

Ad oggi, questa è una **questione aperta**: nessuno è riuscito a dimostrare né che P = NP, né che P ≠ NP.  
È uno dei più grandi problemi irrisolti della matematica e dell’informatica.

---

### **3. Problemi NP-completi**

Si è individuata una particolare classe di problemi, detti **NP-completi**, che presentano tre caratteristiche fondamentali:

1. Sono risolvibili in **tempo polinomiale** da un **algoritmo non deterministico**.
    
2. **Non si conosce alcun algoritmo deterministico** in tempo polinomiale che li risolva.
    
3. Se si trovasse un algoritmo deterministico polinomiale per **uno solo di essi**, allora **tutti** i problemi in NP diventerebbero risolvibili in tempo polinomiale (cioè P = NP).
    

Questi problemi rappresentano dunque **il punto critico** tra ciò che è risolvibile in modo efficiente e ciò che non lo è.

---

### **4. Ipotesi di lavoro**

Per lo studio della complessità si assume che:

- I problemi siano considerati **in forma decisionale**, cioè con risposta “SÌ” o “NO”.
    
- Il linguaggio di riferimento includa le istruzioni **`choice`**, **`success`** e **`failure`**, usate nel modello non deterministico.
    
- I numeri utilizzati siano **codificati in modo conciso**, ossia in una base $b > 1$ (ad esempio binaria o decimale).
    

---

### **5. Le classi P e NP**

#### **Classe P**

È la classe di **tutti i problemi decisionali risolvibili in tempo polinomiale** mediante **algoritmi deterministici**.  
In pratica, raccoglie i **problemi “facili”**, per i quali esistono soluzioni efficienti.

#### **Classe NP**

È la classe di **tutti i problemi decisionali risolvibili in tempo polinomiale da algoritmi non deterministici**.  
Equivalente: tutti i problemi per cui **una soluzione può essere verificata in tempo polinomiale**.

#### **Risultati noti**

- È noto che **P ⊆ NP**.
    
- La questione **P = NP** rimane **aperta**.
    

---

### **6. Riducibilità polinomiale**

Dati due problemi decisionali $A$ e $B$, si dice che:

$$  
A \preceq B  
$$

(se legge “A si riduce in tempo polinomiale a B”)  
se esiste una **funzione di trasformazione polinomiale** $f$ tale che:

$$  
f : \text{(istanze di A)} \to \text{(istanze di B)}  
$$

e vale che:

- $f$ è **computabile in tempo polinomiale** da un algoritmo deterministico;
    
- $x$ è istanza sì per $A$ **se e solo se** $f(x)$ è istanza sì per $B$.
    

---

#### **Conseguenze della riduzione**

- Se $B \in \text{NP}$, allora anche $A \in \text{NP}$.
    
- Se $B \in \text{P}$, allora anche $A \in \text{P}$.
    
- Se $A$ richiede $\Omega(p(n))$ passi e $f$ è calcolabile in $O(p(n))$, allora anche $B$ è $\Omega(p(n))$.
    

La **riduzione polinomiale** è quindi lo strumento con cui **si confrontano i livelli di difficoltà** dei problemi computazionali.

---

### **7. Il Teorema di Cook-Levin**

Il **Teorema di Cook-Levin (1971)** risponde alla domanda:

> “Esiste un problema in NP tale che, se fosse dimostrato appartenere a P, allora si avrebbe automaticamente P = NP?”

La risposta è **sì**.

#### **Enunciato**

Ogni problema in NP si **riduce in tempo polinomiale** al **problema del Domino Limitato** (equivalente al problema della soddisfacibilità booleana, SAT).

#### **Conseguenze**

- **Corollario:**  
    P = NP se e solo se **Domino Limitato ∈ P**.
    
- **Definizioni derivate:**
    
    - Un problema $A$ è **NP-arduo** se ogni $B ∈ NP$ si riduce a $A$.
        
    - Un problema $A$ è **NP-completo** se è **NP-arduo** e **$A ∈ NP$**.
        

---

### **8. Dimostrare la NP-completezza**

Per dimostrare che un problema decisionale $A$ è **NP-completo**, si segue questo schema:

1. Dimostrare che **$A ∈ NP$** (cioè che ha un certificato polinomiale).
    
2. Scegliere un problema noto **NP-completo** $B$.
    
3. Mostrare che **$B \preceq A$**, ossia che esiste una **riduzione polinomiale** da $B$ ad $A$.
    

Non è necessario dimostrare la riduzione da **ogni** problema in NP:  
basta mostrarla da **uno già noto NP-completo**, poiché la transitività delle riduzioni garantisce la validità del risultato.

---

### **9. Esempi di problemi NP-completi**

- Domino Limitato
    
- Soddisfacibilità Booleana (SAT)
    
- Circuito Hamiltoniano
    
- Problema della Cricca
    
- Colorazione dei grafi
    
- Insieme indipendente
    
- Problema dello Zaino
    
- Partizione
    
- Abbinamento tridimensionale (3DM)
    
- TSP (versione decisionale)
    

---

### **10. In sintesi**

- Le **classi P e NP** forniscono una prima distinzione formale tra **problemi facili e difficili**.
    
- La **riducibilità polinomiale** è lo strumento per confrontare problemi tra loro.
    
- Il **Teorema di Cook-Levin** ha introdotto il concetto di **NP-completezza**, che unifica molti problemi difficili sotto un’unica categoria.
    
- Dimostrare che un problema è NP-completo significa collocarlo tra i **più complessi problemi risolvibili in tempo polinomiale non deterministico**.
    

Con questa lezione si conclude il percorso iniziato con la nozione di “problema difficile” e si arriva alla **fondazione teorica moderna della complessità computazionale**.