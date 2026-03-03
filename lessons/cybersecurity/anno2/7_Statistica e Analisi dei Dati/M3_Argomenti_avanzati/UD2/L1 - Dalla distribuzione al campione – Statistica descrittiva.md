# **M3 UD2 Lezione 1 - Dalla distribuzione al campione - Statistica descrittiva**

### **1. Dalla distribuzione al campione**

Finora abbiamo studiato le **distribuzioni di probabilità**, ossia le leggi teoriche che descrivono **tutti i possibili esiti** di un fenomeno casuale.  
Nella realtà sperimentale, però, non osserviamo l’intera distribuzione: osserviamo solo un **campione di dati**.

Esempio:  
Se lanciamo un dado bilanciato molte volte, la probabilità teorica di ogni faccia è $1/6$.  
Ma se lo lanciamo solo 12 volte, i risultati ottenuti costituiranno **un campione casuale** che **approssima** la distribuzione solo in parte.

👉 **Conclusione:**  
Il campione è una “realizzazione parziale” della distribuzione.  
Solo quando il numero di osservazioni è molto grande, la **frequenza empirica** tende alla **probabilità teorica** (Legge dei grandi numeri).

---

### **2. Campione e distribuzione**

Un campione non deve mai essere preso “alla lettera”: non rappresenta esattamente la distribuzione, ma ne fornisce **informazioni indirette**.  
Se però non conosciamo nulla della distribuzione originaria, il campione diventa **la nostra unica fonte di informazione** — a patto di saperlo **rappresentare, descrivere e sintetizzare** correttamente.

Da qui nasce la distinzione fondamentale tra:

- **Statistica descrittiva:** studia **come rappresentare e sintetizzare** un campione.
    
- **Statistica inferenziale:** usa il campione per **dedurre proprietà della distribuzione** che lo ha generato.

---

### **3. Statistica descrittiva**

La statistica descrittiva è la disciplina che, dato un insieme di dati sperimentali, si occupa di:

- **rappresentarli graficamente** (istogrammi, diagrammi, box plot, ecc.),
    
- **riassumerli** tramite indicatori numerici di **posizione** e **ampiezza** (media, varianza, mediana, ecc.).

È quindi una prima fase di analisi, **puramente osservativa**, ma indispensabile per comprendere i dati reali.

---

### **4. Statistica inferenziale e distribuzioni campionarie**

La **statistica inferenziale** mira invece a ricostruire, a partire dal campione, le **caratteristiche della popolazione** (cioè della distribuzione originaria).  
Si basa sulla **probabilità inversa**, e verrà approfondita nella prossima unità didattica.

Tra i due mondi — probabilità diretta e probabilità inversa — si colloca lo studio delle **distribuzioni campionarie**, che:

> Data una distribuzione teorica, deducono la distribuzione delle grandezze derivate da campioni (es. media, massimo, minimo).

Questa sarà la parte centrale delle prossime lezioni dell’unità.

---

### **5. Istogrammi di conteggio e di frequenza**

#### **Istogrammi di conteggio**

I campioni di eventi numerici discreti si possono rappresentare tramite un **istogramma di conteggio**, che mostra quante volte ciascun valore è stato osservato.

Esempio:  
Nel lancio di un dado, ogni faccia corrisponde a una barra la cui altezza è pari al numero di volte in cui è uscita.

- **Minimo campionario:** valore più piccolo osservato.
    
- **Massimo campionario:** valore più grande osservato.

#### **Istogrammi di frequenza**

Dividendo ogni conteggio per il numero totale di osservazioni $n$, si ottiene l’**istogramma di frequenza relativa**:

$$  
f(i) = \frac{\#(i)}{n}  
$$

Quando il campione è molto grande, l’istogramma di frequenza **approssima visivamente la distribuzione** che ha generato i dati:  
$$  
f(i) \to P(i)  
$$

---

### **6. Campioni continui e istogrammi**

Nel caso di **variabili continue**, ogni evento può essere rappresentato da un punto o da un piccolo segmento verticale (“rug plot”).  
In pratica, però, si raccolgono gli eventi in **canali** o **intervalli** per costruire un istogramma continuo.

⚠️ **Attenzione:**  
La forma dell’istogramma dipende dal numero e dall’ampiezza dei canali (binning).  
Lo stesso campione può generare **istogrammi diversi**, a seconda della scelta dei bin.

Se il campione è sufficientemente grande e il binning è ben scelto, l’istogramma fornisce una buona **approssimazione della densità di probabilità** originaria.

---

### **7. Scatter plot e Lego plot**

Per distribuzioni **bidimensionali** (es. due variabili $x$ e $y$) si usa il **diagramma a dispersione (scatter plot)**:

- Ogni punto rappresenta una coppia $(x_i, y_i)$ del campione.
    
- Permette di visualizzare **relazioni o pattern** tra le due variabili.

Raggruppando i punti in celle regolari si ottiene un **istogramma 2D**, chiamato:

- **Lego plot** o **Manhattan plot**,
    
- oppure la sua variante a colori, che usa l’intensità cromatica per rappresentare la frequenza.

---

### **8. Cumulativa campionaria**

Per un campione discreto, la **funzione cumulativa empirica** si ottiene sommando le frequenze $f(i)$ fino al valore considerato:

$$  
F(i) = \sum_{j \le i} f(j)  
$$

Sul continuo, i gradini della cumulativa hanno tutti la stessa altezza $1/n$ e la distanza orizzontale tra i gradini riflette la spaziatura tra i punti.

---

### **9. Indicatori di posizione e ampiezza**

Per descrivere sinteticamente un campione, si usano due categorie di indicatori:

- **Indicatori di posizione:**
    
    - media campionaria
        
    - mediana campionaria
        
    - (eventualmente) moda campionaria
    
- **Indicatori di ampiezza:**
    
    - varianza campionaria
        
    - intervallo interquartile

Queste grandezze permettono di riassumere “dove” si concentrano i dati e “quanto” sono dispersi.

---

### **10. Campioni sul discreto**

Un **campione discreto** è una distribuzione di **frequenze empiriche** $f(i)$, simile a una distribuzione di probabilità $P(i)$ ma costruita sui dati osservati.

#### **Media campionaria (discreta)**

$$  
m = \sum_i i , f(i) = \frac{1}{n} \sum_i i , \#(i)  
$$

#### **Momento secondo e varianza campionaria**

$$  
s^2 = \langle i^2 \rangle - \langle i \rangle^2 = \sum_i i^2 f(i) - \left(\sum_i i f(i)\right)^2  
$$

La **deviazione standard campionaria** è $s = \sqrt{s^2}$.

#### **Notazione**

|Quantità|Distribuzione teorica|Campione|
|:--|:--|:--|
|Media|$\mu$|$m$|
|Varianza|$\sigma^2$|$s^2$|
|Dev. standard|$\sigma$|$s$|

---

### **11. Mediana e quartili (discreti)**

La **mediana** è il punto per cui la cumulativa raggiunge 0,5.  
I **quartili** (primo, secondo, terzo) dividono il campione in quattro parti uguali.  
Sul discreto, questi valori coincidono spesso con più punti a causa della natura “a salti” della cumulativa.

---

### **12. Campioni sul continuo**

Nel caso continuo, ogni punto ha **peso uguale** $1/n$.

#### **Media campionaria**

$$  
m = \frac{1}{n} \sum_{j=1}^{n} x_j  
$$

#### **Momento secondo e varianza**

$$  
s^2 = \frac{1}{n} \sum_{j=1}^{n} x_j^2 - m^2  
$$

Se si costruisce un istogramma, la media e la varianza calcolate sui bin rappresentano un’**approssimazione** di quelle del campione.

---

### **13. Mediana e quartili empirici**

Per $n$ dati ordinati in modo crescente:

- la **mediana** è la coordinata del punto con indice $\frac{n+1}{2}$;
    
- il **primo quartile** è il punto con indice $\frac{n}{4}$;
    
- il **terzo quartile** è il punto con indice $\frac{3n}{4}$.

L’**intervallo interquartile (IQR)** è la distanza tra il primo e il terzo quartile:  
$$  
IQR = Q_3 - Q_1  
$$

---

### **14. Box plot**

Il **box plot** (o diagramma a scatola e baffi) è una rappresentazione grafica che mostra:

- la **mediana** (linea centrale nel box),
    
- il **primo e terzo quartile** (limiti del box),
    
- i **baffi** che si estendono fino ai valori estremi non anomali,
    
- e gli **outlier** (punti anomali) eventualmente segnati separatamente.

Serve principalmente a **confrontare visivamente** più campioni e a cogliere differenze di posizione e ampiezza.

---

### **15. Sintesi finale**

|Argomento|Descrizione|
|:--|:--|
|**Campione**|Realizzazione di un insieme di osservazioni casuali|
|**Statistica descrittiva**|Studio delle rappresentazioni e delle misure sintetiche del campione|
|**Indicatori principali**|Media, varianza, mediana, quartili, IQR|
|**Strumenti grafici**|Istogrammi, cumulativa, box plot|
|**Legame con la probabilità**|Il campione è un’approssimazione della distribuzione; per grandi $n$, $f(i) \to P(i)$|
