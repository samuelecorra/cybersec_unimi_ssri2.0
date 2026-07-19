# **M11 Approfondimenti per l'esame - Lezione 3 - Limiti per supporto e confidenza nelle regole di associazione**

### **1. Dalle percentuali agli insiemi**

Sia $N_T$ il numero di transazioni e siano $X$, $Y$, $Z$ gli insiemi delle transazioni che contengono i rispettivi itemset. Per una regola $X\to Y$:

$$
\operatorname{supporto}(X\to Y)=\frac{|X\cap Y|}{N_T},
$$

$$
\operatorname{confidenza}(X\to Y)=\frac{|X\cap Y|}{|X|}.
$$

Il primo passo deve sempre essere la conversione delle informazioni in cardinalità di insiemi.

---

### **2. Conseguenza della confidenza unitaria**

Se

$$
\operatorname{confidenza}(X\to Y)=1,
$$

allora ogni transazione che contiene $X$ contiene anche $Y$:

$$
X\subseteq Y
$$

e quindi

$$
|X|=|X\cap Y|.
$$

Se inoltre $N_T=100$ e

$$
\operatorname{supporto}(X\to Y)=0{,}3,
$$

si ottiene:

$$
|X\cap Y|=30,\qquad |X|=30.
$$

---

### **3. Supporto di $X\to Z$**

Da

$$
\operatorname{confidenza}(X\to Z)=0{,}5
$$

segue:

$$
|X\cap Z|=0{,}5|X|=15.
$$

Pertanto il supporto non varia: è determinato esattamente.

$$
\operatorname{supporto}(X\to Z)=\frac{15}{100}=0{,}15.
$$

Quindi:

$$
\boxed{\min=\max=0{,}15}.
$$

---

### **4. Limiti del supporto di $Y\to Z$**

Poiché $X\subseteq Y$, tutte le 15 transazioni in $X\cap Z$ appartengono anche a $Y\cap Z$:

$$
|Y\cap Z|\geq 15.
$$

Questo limite è raggiungibile scegliendo nessun'altra transazione contemporaneamente in $Y$ e in $Z$. Dunque:

$$
\min\operatorname{supporto}(Y\to Z)=\frac{15}{100}=0{,}15.
$$

Per il massimo, dentro $X$ esistono 15 transazioni che **non** appartengono a $Z$, perché $|X|=30$ e $|X\cap Z|=15$. Poiché $X\subseteq Y$, quelle 15 transazioni appartengono necessariamente a $Y\setminus Z$ e non possono contribuire a $Y\cap Z$. Ne segue:

$$
|Y\cap Z|\leq 100-15=85.
$$

Il limite è raggiungibile ponendo $Y$ uguale all'intera collezione e $Z$ formato dalle 15 transazioni di $X\cap Z$ più tutte le 70 transazioni esterne a $X$. Quindi:

$$
\max\operatorname{supporto}(Y\to Z)=\frac{85}{100}=0{,}85.
$$

In conclusione:

$$
\boxed{0{,}15\leq \operatorname{supporto}(Y\to Z)\leq 0{,}85}.
$$

---

### **5. Controllo con il diagramma di Venn**

La partizione obbligata di $X$ è:

|Regione|Cardinalità|
|---|---:|
|$X\cap Z$|15|
|$X\setminus Z$|15|

Entrambe le regioni sono contenute in $Y$. La seconda costituisce il vincolo che impedisce a $Y\cap Z$ di superare 85.

---

### **6. Procedura generale per gli estremi**

1. Trasformare supporti e confidenze in cardinalità.
2. Tradurre confidenza 1 in un'inclusione insiemistica.
3. Disegnare mentalmente le regioni obbligate e quelle libere.
4. Per il minimo, lasciare vuote tutte le intersezioni non obbligate.
5. Per il massimo, riempire tutte le regioni compatibili.
6. Esibire una configurazione che raggiunga ciascun estremo.

> ⚠️ Non confondere supporto e confidenza: il supporto divide per $N_T$, la confidenza divide per il numero di transazioni che contengono l'antecedente.

> ✅ Un limite è dimostrato soltanto quando è sia necessario sia raggiungibile.
