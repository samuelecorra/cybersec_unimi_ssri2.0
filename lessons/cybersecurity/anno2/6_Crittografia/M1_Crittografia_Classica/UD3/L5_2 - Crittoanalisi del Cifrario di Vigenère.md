# **Lezione 5: Crittoanalisi del Cifrario di Vigenère (Parte 2)**

---

### **1. Introduzione**

Dopo aver stimato la **lunghezza della chiave** $t$ tramite il **test di Kasiski** o l’**indice di coincidenza (IC)**,  
il passo successivo consiste nel **determinare i valori effettivi della chiave** $K_0, K_1, K_2, ..., K_{t-1}$.

Per farlo si utilizza un nuovo strumento statistico:  
👉 l’**Indice Mutuo di Coincidenza (IMC)**.

---

### **2. L’indice mutuo di coincidenza (IMC)**

L’**IMC** misura la probabilità che **due lettere** — una estratta da un testo $x_1x_2...x_n$ e una da un testo $y_1y_2...y_{n'}$ — siano **uguali**.

$$  
IMC(x_1x_2...x_n; y_1y_2...y_{n'}) =  
\frac{\sum_{i=0}^{25} f_i \cdot f'_i}{n \cdot n'}  
$$

dove:

- $f_i$ è il **numero di occorrenze** del carattere $i$ in $x$,
    
- $f'_i$ è il **numero di occorrenze** del carattere $i$ in $y$,
    
- $n$ e $n'$ sono le rispettive lunghezze dei due testi.
    

---

### **3. Significato**

L’IMC rappresenta la **probabilità media** che due lettere, prese casualmente dai due testi, coincidano.

#### **Esempi:**

- $IMC(CIA; CIAO) = \frac{3}{12} = 0.25$
    
- $IMC(ALFA; GAMMA) = \frac{4}{20} = 0.20$
    

---

### **4. Applicazione al Cifrario di Vigenère**

Una volta nota la lunghezza della chiave $t$, possiamo suddividere il testo cifrato $C_0, C_1, C_2, ...$ in **t sottosequenze**:

- $C_0, C_t, C_{2t}, ...$ cifrato con **$K_0$**
    
- $C_1, C_{t+1}, C_{2t+1}, ...$ cifrato con **$K_1$**
    
- ...
    
- $C_{t-1}, C_{2t-1}, C_{3t-1}, ...$ cifrato con **$K_{t-1}$**
    

L’obiettivo è **trovare i valori di ciascun $K_i$** confrontando le sottosequenze con **rotazioni successive** fino a ottenere il valore medio di IMC **più vicino a quello della lingua naturale**.

---

### **5. Proprietà fondamentali dell’IMC (approfondimento logico)**

Consideriamo due sottosequenze cifrate con chiavi diverse $K_0$ e $K_1$:

- $C_0, C_t, C_{2t}, …$ cifrata con $K_0$
    
- $C_1, C_{t+1}, C_{2t+1}, …$ cifrata con $K_1$

Vogliamo calcolare il **valore medio dell’IMC** tra queste due sequenze.

Nel cifrario di Vigenère vale la relazione:

$$

C = P + K \pmod{26}

$$

quindi, invertendo:

$$

P = C - K \pmod{26}

$$

Se una lettera cifrata ha indice $i$:

- nella prima sequenza il plaintext corrispondente era $i - K_0$
    
- nella seconda sequenza il plaintext corrispondente era $i - K_1$

---

#### **Probabilità che le due sequenze producano la stessa lettera cifrata**

Per esempio, la probabilità che entrambe producano la lettera $A$ è:

$$

p_{-K_0} \cdot p_{-K_1}

$$

Analogamente:

- probabilità di ottenere $B$ in entrambe:

$$

p_{1-K_0} \cdot p_{1-K_1}

$$

- probabilità di ottenere $C$ in entrambe:

$$

p_{2-K_0} \cdot p_{2-K_1}

$$

e così via per tutte le lettere.

Qui:

- $p_i$ rappresenta la **probabilità della lettera $i$ nella lingua naturale**
    
- cioè la frequenza relativa teorica della lingua.

---

#### **Valore medio complessivo**

Sommando su tutte le lettere dell’alfabeto si ottiene:

$$

IMC(C_0… ; C_1…)

\approx

\sum_{i=0}^{25} p_{i-K_0} \cdot p_{i-K_1}

$$

Questa espressione rappresenta la probabilità media che due lettere cifrate coincidano.

---

#### **Dipendenza solo dalla differenza tra le chiavi**

Per evidenziare la dipendenza dalle chiavi si introduce il cambio di variabile:

$$

h = i - K_0

$$

Da cui:

$$

i = h + K_0

$$

Sostituendo nel secondo termine:

$$

i - K_1 = h + K_0 - K_1 = h + (K_0 - K_1)

$$

L’espressione diventa quindi:

$$

IMC(C_0… ; C_1…)

\approx

\sum_{h=0}^{25} p_h \cdot p_{h + (K_0 - K_1)}

$$
ATTENZIONE: nella sommatoria rimane $h = 0$ poiché lavoriamo mod26 
L’indice mutuo di coincidenza tra due sottosequenze cifrate:

$$

IMC(C_0… ; C_1…)

$$

dipende **unicamente** dalla differenza:

$$

K_0 - K_1

$$

e non dai valori assoluti delle due chiavi.

---

### **6. Significato crittoanalitico**

Questa proprietà è cruciale perché permette di:

1. confrontare due colonne cifrate,
    
2. stimare la differenza tra le lettere della chiave,
    
3. ricostruire progressivamente l’intera chiave del cifrario di Vigenère.

In pratica, si provano diversi shift relativi tra due colonne e si sceglie quello che produce un IMC più vicino a quello della lingua naturale.

---

### **7. Simmetria dell’IMC rispetto al segno e deduzione dei 14 valori**

Quando si arriva alla formula teorica dell’IMC medio tra due colonne cifrate con chiavi $K_0$ e $K_1$, si ottiene un’espressione del tipo

$$

IMC \approx \sum_{h=0}^{25} p_h \cdot p_{h+(K_0-K_1)}

$$

  

Vediamo ora perché ci conviene la sostituzione di $+(K_0-K_1)$ con $-(K_0-K_1)$:

$$

\sum_{h=0}^{25} p_h \cdot p_{h+(K_0-K_1)}

=

\sum_{h=0}^{25} p_h \cdot p_{h-(K_0-K_1)}

$$

Il motivo non è un trucco, ma una conseguenza del fatto che gli indici sono sempre interpretati **modulo 26** e che la somma scorre su **tutto** l’alfabeto. Infatti, se definiamo

$$

d = K_0 - K_1 \pmod{26}

$$

allora $-d$ non è un nuovo valore “diverso”, ma è semplicemente la stessa distanza letta nel verso opposto della ruota:

$$

-d \equiv 26-d \pmod{26}

$$

Poiché nella sommatoria si stanno considerando tutti i valori $h=0,1,\dots,25$, sostituire $h$ con $h+d$ (o con $h-d$) significa solo **rinumerare** gli stessi 26 termini in ordine diverso. In una somma completa su un insieme ciclico, una rotazione non cambia il risultato: cambia solo l’etichetta con cui chiamiamo gli indici.

Questa simmetria implica un fatto crittoanalitico importantissimo: il valore teorico dell’IMC non dipende dal “verso” dello shift relativo, ma solo dalla **distanza** tra $K_0$ e $K_1$. In altri termini, lo shift $d$ e lo shift $26-d$ producono lo stesso valore medio di IMC.

A questo punto possiamo dedurre quanti sono i **valori distinti** possibili.

1. La differenza relativa tra due lettere di chiave, lavorando modulo 26, può assumere in teoria 26 valori:

$$

d \in {0,1,2,\dots,25}

$$

2. Però la simmetria vista sopra ci dice che i valori si accoppiano così:

$$

d \longleftrightarrow 26-d

$$

cioè:

- $1$ dà lo stesso IMC di $25$
    
- $2$ dà lo stesso IMC di $24$
    
- $3$ dà lo stesso IMC di $23$
    
- …
    

3. Ci sono due casi speciali che non si “accoppiano” con un altro valore diverso:
    

- $d=0$ è uguale solo a sé stesso (perché $26-0=0$)
    
- $d=13$ è uguale solo a sé stesso (perché $26-13=13$)

4. Tutti gli altri valori da 1 a 12 si accoppiano con un valore distinto da 14 a 25. Quindi il numero di casi realmente diversi diventa:

- 1 caso per $d=0$
    
- 12 casi per $d=1,2,\dots,12$
    
- 1 caso per $d=13$

Totale:

$$

1 + 12 + 1 = 14

$$

Quindi, anche se formalmente $d$ potrebbe essere uno qualunque tra 0 e 25, dal punto di vista dell’IMC medio esistono soltanto **14 valori distinti**, corrispondenti alle distanze:

$$

d \in {0,1,2,\dots,13}

$$

Questa è esattamente l’idea della slide: l’IMC tra due colonne non ti dice “in che verso” è lo shift relativo, ma ti dice la **distanza** tra le due lettere di chiave, e su un cerchio di 26 posizioni la distanza massima distinguibile è 13, da cui i 14 casi complessivi (incluso lo 0).

---

### **7. Tabella dei valori medi (Inglese)**

|$K_0 - K_1$|IMC medio|
|---|---|
|0|**0.065**|
|1, 25|0.039|
|2, 24|0.032|
|3, 23|0.034|
|4, 22|0.044|
|5, 21|0.033|
|6, 20|0.036|
|7, 19|0.039|
|8, 18|0.034|
|9, 17|0.034|
|10, 16|0.038|
|11, 15|0.045|
|12, 14|0.039|
|13|0.043|

💡 **Conclusione:**

- Se $K_0 - K_1 = 0$ → $IMC ≈ 0.065$
    
- Se $K_0 - K_1 \neq 0$ → $IMC ≤ 0.045$
    

---

### **8. Tabella dei valori medi (Italiano)**

|$K_0 - K_1$|IMC medio|
|---|---|
|0|**0.075**|
|1, 25|0.033|
|2, 24|0.034|
|3, 23|0.034|
|4, 22|0.047|
|5, 21|0.027|
|6, 20|0.032|
|7, 19|0.026|
|8, 18|0.027|
|9, 17|0.023|
|10, 16|0.024|
|11, 15|0.027|
|12, 14|0.015|
|13|0.021|

💡 **Conclusione per l’italiano:**

- Se $K_0 - K_1 = 0$ → $IMC ≈ 0.075$
    
- Se $K_0 - K_1 \neq 0$ → $IMC < 0.047$
    

---

### **9. Applicazione pratica**

Per ogni differenza $d$ tra le posizioni della chiave:

1. Si costruisce una **versione ruotata** del testo cifrato:  
    $$  
    Y_i = (C_i - d) \mod 26  
    $$
    
2. Si calcola l’IMC tra le due sequenze:  
    $IMC(Y_0Y_t...; C_1C_{t+1}...)$
    
3. Se l’IMC medio risulta vicino a **0.075**, allora $K_0 - K_1 = d$.
    

💡 Si ripete il procedimento per tutti i 26 valori possibili di $d$.

---

### **10. Determinazione della chiave completa**

Una volta calcolate tutte le differenze tra coppie di chiavi ($K_1-K_0$, $K_2-K_0$, $K_3-K_0$, ...),  
si ottiene un sistema di **$t-1$ equazioni con $t$ incognite**.

Scegliendo arbitrariamente un valore per $K_0$, si possono ricavare tutti gli altri.

---

### **11. Esempio pratico**

#### **Risultati delle differenze:**

|Coppia|Differenza|
|---|---|
|$K_0 - K_1$|10|
|$K_0 - K_2$|1|
|$K_0 - K_3$|14|
|$K_0 - K_4$|13|

Da queste differenze ricaviamo:

$$  
\begin{cases}  
K_1 = K_0 - 10 \\\\  
K_2 = K_0 - 1 \\\\  
K_3 = K_0 - 14 \\\\  
K_4 = K_0 - 13  
\end{cases}  
$$

Impostando $K_0 = 1$ (cioè **B**), otteniamo:

|Posizione|Valore numerico|Lettera|
|---|---|---|
|$K_0$|1|B|
|$K_1$|17|R|
|$K_2$|0|A|
|$K_3$|13|N|
|$K_4$|14|O|

✅ **Chiave decifrata:** `BRANO`

---

### **12. Esempio di testo cifrato**

Il testo cifrato (tratto da _I Promessi Sposi_) è stato decifrato correttamente grazie alla chiave `BRANO`.

> “Quel ramo del lago di Como che volge a mezzogiorno tra due catene non interrotte di monti…”

💡 Questo esempio dimostra come sia possibile **rompere un cifrario di Vigenère** con strumenti puramente statistici.

---

### **13. In sintesi**

Abbiamo completato la **crittoanalisi del Cifrario di Vigenère**.  
Ricapitolando:

- Il **test di Kasiski** e l’**indice di coincidenza (IC)** permettono di stimare la **lunghezza della chiave**.
    
- L’**indice mutuo di coincidenza (IMC)** consente di **determinare i caratteri esatti della chiave**.
    
- Combinando questi due metodi, è possibile **decifrare completamente un testo cifrato** senza conoscere la chiave.
    

---

### **14. Conclusione del Modulo 1**

Con questa lezione si chiude il **Modulo 1 – Tecniche di crittografia classica**.  
Abbiamo esplorato l’evoluzione storica e matematica della crittografia:

1. **Dalle origini** (steganografia e cifrari monoalfabetici)
    
2. **Ai sistemi polialfabetici** (Vigenère, Hill, Enigma)
    
3. **Fino alla crittoanalisi**, che segna la nascita della **scienza moderna della sicurezza delle informazioni**.
    

💡 La crittografia classica non è solo un insieme di trucchi linguistici,  
ma il punto di partenza della **crittografia matematica e computazionale moderna**.