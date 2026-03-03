# **Lezione 3: Attacchi al DES**

---

### **1. Tipologie di attacco**

Gli attacchi al **Data Encryption Standard (DES)** si dividono in due grandi categorie:

- **Attacchi a forza bruta**, basati sulla ricerca esaustiva di tutte le chiavi possibili.
    
- **Analisi crittografica**, basata su vulnerabilità strutturali dell’algoritmo.
    

Le principali tecniche note sono:

- **Criptoanalisi differenziale** (Biham e Shamir, 1990)
    
- **Criptoanalisi lineare** (Matsui, 1993)
    

---

### **2. Attacchi a forza bruta**

Il DES utilizza chiavi di **56 bit**, quindi esistono:

$$  
2^{56} \approx 7,2 \times 10^{16}  
$$

chiavi possibili.

#### **Attacco known plaintext**

Si dispone di uno o più **coppie testo in chiaro–testo cifrato**:

- Si calcola per tutte le $2^{56}$ chiavi possibili $y_k = DES_k(x)$.
    
- Si confrontano i risultati con il testo cifrato noto.
    
- In media, servono $2^{55}$ prove.
    

#### **Attacco chosen plaintext**

Si sceglie un testo in chiaro $x$ e si calcolano:  
$$  
y_k = DES_k(x)  
$$  
per ogni possibile chiave $k$, costruendo una **tabella (y_k, k)**.  
Quando si ottiene un nuovo testo cifrato $y$, basta cercarlo nella tabella per risalire alla chiave.

- **Tempo di ricerca:** logaritmico o costante.
    
- **Precomputazione:** $2^{56}$ cifrature.
    
- **Memoria:** $2^{56}$ voci.
    

---

### **3. Time–Space Trade-Off**

#### **3.1 Idea generale**

Si cerca un compromesso tra **tempo di calcolo** e **spazio di memoria**.

![](imgs/Pasted%20image%2020260222105443.png)

Si definisce una funzione di riduzione:  
$$  
F: {0,1}^{64} \rightarrow {0,1}^{56}  
$$

e si costruisce una catena di valori:

$$  
X_{(1,0)} \to X_{(1,1)} = F(DES_{X_{(1,0)}}(x)) \to X_{(1,2)} \to \dots \to X_{(1,t)}  
$$

Vengono create $m$ catene, ognuna di lunghezza $t$, formando una tabella di dimensioni $m \times t$.  
Ogni cella contiene un valore intermedio di 56 bit.

#### **3.2 Ricerca**

Quando si ottiene un testo cifrato $y$, si calcola la riduzione $F(y)$ e si controlla:

- se è presente nell’ultima colonna, si verifica la corrispondenza con $DES_{X(i,t-1)}(x)$;
    
- se no, si controllano le colonne precedenti, applicando iterativamente $DES$ e $F$.
    

Poiché $F$ non è iniettiva, ogni elemento può avere fino a $2^8 = 256$ preimmagini.

#### **3.3 Ottimizzazione**

Si memorizzano solo le **prime e ultime colonne** di ciascuna catena.  
Quando $F(y)$ compare nella colonna finale, si può ricostruire la chiave candidata.

Per ridurre ulteriormente i costi si possono usare **T tabelle indipendenti**, ciascuna con parametri $m$ e $t$ tali che:

$$  
m \cdot t^2 \approx N = 2^{56}  
$$

Scelta ottimale:

$$  
m \approx N^{1/3}, \quad t \approx N^{1/3}, \quad T \approx N^{1/3}  
$$

- **Spazio:** $T \cdot 2 \cdot N^{2/3}$
    
- **Tempo di ricerca:** $N^{2/3}$
    
- **Tempo di precomputazione:** $N$
    

---

### **4. Chiavi deboli e semideboli**

#### **4.1 Chiavi deboli**

Una chiave $k$ è **debole** se, per ogni testo $x$:

$$  
DES_k(DES_k(x)) = x  
$$

cioè la doppia cifratura con la stessa chiave restituisce il testo originale.  
Questo avviene quando **tutte le sottochiavi generate sono uguali**.

![](imgs/Pasted%20image%2020260222111111.png)

Le 4 chiavi deboli note (in esadecimale):

```
0101 0101 0101 0101
FEFE FEFE FEFE FEFE
1F1F 1F1F 0E0E 0E0E
E0E0 E0E0 F1F1 F1F1
```

---

#### **4.2 Chiavi semideboli**

Due chiavi $k$ e $k'$ sono **semideboli** se, per ogni testo $x$:

$$  
DES_k(DES_{k'}(x)) = x  
$$

Le loro sottochiavi si ripetono in coppie (solo due pattern diversi, ciascuno usato otto volte).  
Esistono **6 coppie di chiavi semideboli**.

![](imgs/Pasted%20image%2020260222111139.png)

---

#### **4.3 Proprietà del complemento**

Se $x$, $y$ e $k$ sono rispettivamente testo in chiaro, testo cifrato e chiave, allora vale:

$$  
DES_k(x) = y \Rightarrow DES_{\bar{k}}(\bar{x}) = \bar{y}  
$$

dove $\bar{x}$ indica il **complemento bit per bit** di $x$.  

![](imgs/Pasted%20image%2020260222111220.png)

Questa simmetria riduce lo spazio effettivo di ricerca: in un **chosen plaintext attack**, basta analizzare **$2^{55}$ chiavi** anziché $2^{56}$.

---

### **5. Efficacia pratica degli attacchi**

#### **5.1 Stime di ricerca esaustiva**

|Velocità|Tempo per testare metà chiavi ($2^{55}$)|
|---|---|
|1 cifratura/μs|~1142 anni|
|1 milione cifrature/μs|~10 ore|
|CPU 500 MHz (1 chiave/ciclo)|~834 giorni|

#### **5.2 DES Challenges**

- **1997:** RSA lancia una competizione con premio di $10.000.
    
    - Progetto **DESCHALL** (Rocke Verser): 70.000 computer coinvolti.
        
    - Messaggio trovato in **39 giorni**.
        
    - Testate ~24% delle chiavi.
        
- **1998:** la **EFF (Electronic Frontier Foundation)** realizza **Deep Crack**, macchina dedicata da $\$250.000$ (56h tempo d'attacco)

![](imgs/Pasted%20image%2020260222111712.png)
        
- **1999:** collaborazione **Distributed.net + EFF**,
    
    - 100.000 computer testano **245 miliardi di chiavi/sec**.
        
    - Tempo totale: **22 ore e 15 minuti**.
        

---

### **6. Deep Crack – architettura**

- **Clock:** 40 MHz
    
- **Cicli per decifratura:** 16
    
- **Chiavi testate/secondo per unità:**  
    $$  
    \frac{40.000.000}{16} = 2.500.000  
    $$
    
- **Unità totali:** 24  
    → $24 \times 2.500.000 = 60.000.000$ chiavi/s  
    → Tutte le chiavi in ~13.900 giorni (~38 anni)

![](imgs/Pasted%20image%2020260222111611.png)

#### **Board finale:**

- 64 processori (32 per lato) su scheda 40×40 cm.
    
- Totale: $64 \times 60.000.000 = 3,84 \times 10^9$ chiavi/s.
    
- Tutte le chiavi esplorate in ~218 giorni.

![](imgs/Pasted%20image%2020260222111631.png)

💥 **Nel 1998**: la chiave fu trovata in **56 ore effettive**.

---

### **7. Caratteristiche e criticità del DES**

#### **Effetto valanga**

- Due testi che differiscono per 1 bit → differenza di ~34 bit nel testo cifrato.
    
- Due testi uguali ma cifrati con chiavi che differiscono per 1 bit → differenza di ~35 bit.
    

#### **Criteri progettuali delle S-box**

- **Strict Avalanche:** invertendo 1 bit in input, ciascun bit di output cambia con probabilità ½.
    
- **Bit Independence:** i bit di output cambiano indipendentemente.
    
- **Guaranteed Avalanche:** se cambia un bit in input, almeno $1 < g < 6$ bit in output cambiano.
    

#### **Critiche**

- **Chiave di soli 56 bit:** probabilmente scelta dalla **NSA** per poter controllare la sicurezza.
    
- **S-box opache:** inizialmente si sospettava la presenza di una “trapdoor”, poi esclusa.
    
- **Numero di round (16):** scelto in modo da garantire una distribuzione statistica quasi casuale.
    

---

### **8. Sintesi finale**

Abbiamo visto:

- Gli **attacchi principali** al DES (forza bruta e analitici).
    
- Il principio del **time–space trade-off**.
    
- L’esistenza di **chiavi deboli e semideboli**.
    
- La **proprietà del complemento**.
    
- Le **dimostrazioni pratiche** dell’insufficienza della chiave a 56 bit (Deep Crack e DES Challenge).
    

👉 Il DES, pur essendo un capolavoro di progettazione per la sua epoca, è oggi considerato **inadeguato** per la sicurezza moderna, sostituito definitivamente dal **Triple DES** e, in seguito, dall’**AES**.