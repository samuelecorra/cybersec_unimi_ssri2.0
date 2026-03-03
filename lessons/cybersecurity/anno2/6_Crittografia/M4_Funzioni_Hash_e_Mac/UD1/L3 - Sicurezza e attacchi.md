## **Lezione 3: Sicurezza e attacchi**

### **1. Introduzione**

Dopo aver studiato le proprietà fondamentali delle funzioni hash, in questa lezione affrontiamo il tema della **sicurezza pratica** e degli **attacchi basati sulla probabilità di collisione**.

Il concetto chiave è il **paradosso del compleanno**, che mostra come le collisioni siano **molto più probabili** di quanto si possa intuire. Questo porta a definire la **lunghezza minima dell’hash** necessaria per garantire una sicurezza adeguata.

---

### **2. Dimensione del valore hash e sicurezza**

#### **2.1 Il problema della lunghezza**

Una domanda naturale è:  
**“Quanto deve essere lungo un digest per garantire una sicurezza forte?”**

Se una funzione hash produce un output di $|Z|$ bit, il numero di digest possibili è $2^{|Z|}$.  
Per essere **certi** di trovare almeno una collisione (cioè due messaggi diversi con lo stesso hash), basterebbe considerare $2^{|Z|}+1$ messaggi diversi — per semplice principio dei cassetti (_pigeonhole principle_).

Tuttavia, nella pratica non cerchiamo **certezza**, ma una **buona probabilità** di collisione: anche solo del 50%.  
Qui entra in gioco il **paradosso del compleanno**.

---

### **3. Il paradosso del compleanno**

#### **3.1 L’esperimento mentale**

Domanda:

> Quante persone servono in una stanza affinché ci sia una probabilità ≥ 0.5 che almeno due abbiano lo stesso compleanno?

Risposta sorprendente: **bastano 23 persone!**

Perché?  
In un gruppo di 23 persone ci sono $\frac{23 \cdot 22}{2} = 253$ coppie possibili.  
Con così tante combinazioni, la probabilità che due persone condividano lo stesso giorno è già del **50,7%**.

---

#### **3.2 Calcolo della probabilità**

Chiamiamo $P(365, k)$ la probabilità che **esista un duplicato** tra $k$ persone, assumendo 365 giorni possibili.  
Allora:

$$  
P(365, k) = 1 - Q(365, k)  
$$

dove $Q(365, k)$ è la probabilità che **tutti i compleanni siano diversi**:

$$  
Q(365, k) = \frac{365!}{(365 - k)! \cdot 365^k}  
$$

Dunque:

$$  
P(365, k) = 1 - \frac{365!}{(365 - k)! \cdot 365^k}  
$$

Esempi:

- $P(365, 23) = 0.5073$
    
- $P(365, 100) \approx 0.999999997$
    

---

#### **3.3 Formula generale**

Se un insieme ha cardinalità $n$, e scegliamo $t$ elementi a caso, la probabilità di trovare almeno due elementi uguali è:

$$  
\varepsilon \approx 1 - e^{-\frac{t(t-1)}{2n}}  
$$

Da cui, invertendo rispetto a $t$:

$$  
t \approx \sqrt{2n \ln{\frac{1}{1 - \varepsilon}}}  
$$

Questa formula ci dice **quanti messaggi servono** per ottenere una collisione con probabilità $\varepsilon$.

---

#### **3.4 Applicazione al caso $\varepsilon = 0.5$**

Se vogliamo una probabilità del 50% di collisione:

$$  
t \approx 1.17 \sqrt{n}  
$$

Esempi:

- Se $n = 365$, $t = 22.3$ (il caso dei compleanni).
    
- Se $n = 2^{80}$, allora $t \approx 2^{40}$.
    
- Se $n = 2^{160}$, allora $t \approx 2^{80}$.
    

---

### **4. Applicazione alle funzioni hash**

Nel contesto crittografico, il paradosso del compleanno si traduce così:  
se una funzione hash produce digest di **n bit**, un attaccante può trovare una collisione con **probabilità ≈ 0.5** dopo aver calcolato circa $2^{n/2}$ hash casuali.

#### **4.1 Interpretazione pratica**

|Lunghezza digest (n)|Hash da calcolare per collisione (≈)|
|--:|--:|
|40 bit|$2^{20} ≈ 1.000.000$ hash|
|80 bit|$2^{40} ≈ 10^{12}$ hash|
|128 bit|$2^{64} ≈ 1.8 \times 10^{19}$ hash|
|160 bit|$2^{80} ≈ 1.2 \times 10^{24}$ hash|

Quindi la **sicurezza effettiva** di una funzione hash a $n$ bit contro collisioni è pari a **$n/2$ bit**.

---

### **5. L’attacco del compleanno**

#### **5.1 Descrizione dell’attacco**

L’**attacco del compleanno** sfrutta proprio la probabilità di collisione per **forzare due messaggi a produrre lo stesso hash**.

Esempio:

- Messaggio vero:  
    “Alice è in debito con Bob di 1000 euro.”
    
- Messaggio falso:  
    “Alice è in credito con Bob di 1000 euro.”
    

L’attaccante genera **molte varianti** di ciascun messaggio (aggiungendo spazi, parole neutre, ecc.), fino a ottenere una coppia di varianti $(M, M')$ tali che:

$$  
h(M) = h(M')  
$$

Quando Alice firma $h(M)$, la stessa firma risulta valida anche per $M'$.

---

#### **5.2 Probabilità di successo**

La probabilità di trovare una coppia di collisione supera il 50% quando il numero totale di tentativi $K$ è pari a:

$$  
K = 2^{n/2}  
$$

Esempio numerico:

- Se $n = 40$, bastano $2^{20}$ tentativi (≈ 1 milione di hash).
    
- Se $n = 160$, servono $2^{80}$ tentativi (impraticabile oggi).
    

---

### **6. Implicazioni e misure di sicurezza**

- Un attacco del compleanno consente di trovare collisioni in **$2^{n/2}$ operazioni**, non in $2^n$.  
    → la sicurezza effettiva di una funzione hash è quindi **metà** della sua lunghezza nominale.
    
- Per questo motivo, **le funzioni hash moderne** (come SHA-2 e SHA-3) hanno digest **di almeno 256 bit**, garantendo una sicurezza di 128 bit contro collisioni.
    

**In pratica:**  
$$  
n > 128 \quad \text{è la soglia minima consigliata.}  
$$

---

### **7. Riepilogo finale**

- Il **paradosso del compleanno** mostra che le collisioni si verificano molto prima del previsto.
    
- La probabilità di collisione cresce rapidamente con il numero di messaggi generati.
    
- L’**attacco del compleanno** sfrutta questa proprietà per costruire messaggi diversi con lo stesso hash.
    
- Servono circa $2^{n/2}$ tentativi per ottenere una collisione con probabilità ½.
    
- **Conclusione:** per mantenere la sicurezza, le funzioni hash devono produrre digest **lunghi almeno 256 bit**.