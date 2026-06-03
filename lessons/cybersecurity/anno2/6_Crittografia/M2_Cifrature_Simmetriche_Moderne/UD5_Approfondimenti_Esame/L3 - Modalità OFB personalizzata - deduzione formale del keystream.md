# **L3 – Modalità OFB personalizzata: deduzione formale del keystream**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 2 versioni dello stesso appello (230907 e 230908) con peso di **30 punti** ciascuno.
>
> **Studia prima:**
> - [UD2 / L2 – Modalità operative del DES](../UD2/L2%20-%20Modalità%20operative%20del%20DES.md) → sezione 5 (OFB): struttura $w_t = E_K(w_{t-1})$, $c_t = m_t \oplus w_t$, indipendenza del keystream dal plaintext

---

### **1. Riepilogo della modalità OFB**

In modalità **OFB (Output Feedback)**, il keystream è generato in modo autonomo rispetto al plaintext:

$$
\begin{cases}
w_0 = IV \\
w_t = E_K(w_{t-1}) \quad \text{per } t = 1, 2, \ldots
\end{cases}
$$

La cifratura/decifratura usa XOR con il keystream:

$$
c_t = m_t \oplus w_t \qquad \Leftrightarrow \qquad m_t = c_t \oplus w_t
$$

> 📌 **Proprietà chiave di OFB:** il keystream $w_1, w_2, \ldots$ dipende solo da $K$ e $IV$, **mai dal plaintext o dal ciphertext**. Questo rende OFB un cifrario a flusso sincrono. La stessa coppia $(K, IV)$ produce sempre lo stesso keystream.

---

### **2. Lo scenario dell'esame**

Il professore descrive la seguente situazione:

> Un file è cifrato in modalità OFB con chiave $K$ e IV fissati. Il file cifrato originale è:
> $$[c_1, c_2, \ldots, c_{i-1},\ c_i,\ c_{i+1}, \ldots, c_n]$$
>
> Si elimina il blocco $c_i$ dal file e i blocchi successivi vengono **re-cifrati** con la **stessa chiave $K$ e lo stesso keystream** (a partire dalla stessa posizione $i$ del keystream), ottenendo un file aggiornato:
> $$[c_1, c_2, \ldots, c_{i-1},\ c'_i,\ c'_{i+1}, \ldots, c'_{n-1}]$$
>
> dove $c'_j = m_{j+1} \oplus w_j$ per $j \geq i$.
>
> **Dimostrare che**, noto $m_i$ (il plaintext del blocco eliminato), avendo accesso sia al file originale che al file aggiornato, si possono dedurre tutti i valori $w_t$ e $m_t$ per $t \geq i$.

---

### **3. Dimostrazione formale step-by-step**

#### **Premessa: cosa è noto all'avversario**

| Informazione disponibile | Fonte |
|--------------------------|-------|
| $c_t$ per tutti i $t$ | File originale |
| $c'_j$ per $j \geq i$ | File aggiornato |
| $m_i$ | Ipotesi dell'esame (plaintext del blocco eliminato) |
| $E_K(\cdot)$ | Algoritmo noto (principio di Kerckhoffs); la chiave $K$ è **ignota** |

> 💡 L'avversario **non conosce $K$** e **non può calcolare $E_K(\cdot)$** direttamente. Tuttavia, usando le relazioni tra i file, riesce a recuperare tutti i valori del keystream senza conoscere $K$.

---

#### **Passo 1: recupero di $w_i$**

Dal file originale, il blocco $i$ è:
$$c_i = m_i \oplus w_i$$

Poiché $m_i$ è noto:
$$\boxed{w_i = c_i \oplus m_i}$$

**$w_i$ è recuperato.** ✓

---

#### **Passo 2: recupero di $m_{i+1}$**

Dal file aggiornato, il blocco in posizione $i$ è:
$$c'_i = m_{i+1} \oplus w_i$$

Poiché $w_i$ è ora noto (passo 1):
$$\boxed{m_{i+1} = c'_i \oplus w_i}$$

**$m_{i+1}$ è recuperato.** ✓

---

#### **Passo 3: recupero di $w_{i+1}$**

Dal file originale, il blocco $i+1$ è:
$$c_{i+1} = m_{i+1} \oplus w_{i+1}$$

Poiché $m_{i+1}$ è ora noto (passo 2):
$$\boxed{w_{i+1} = c_{i+1} \oplus m_{i+1}}$$

**$w_{i+1}$ è recuperato.** ✓

---

#### **Passo 4: recupero di $m_{i+2}$**

Dal file aggiornato, il blocco in posizione $i+1$ è:
$$c'_{i+1} = m_{i+2} \oplus w_{i+1}$$

Poiché $w_{i+1}$ è ora noto (passo 3):
$$\boxed{m_{i+2} = c'_{i+1} \oplus w_{i+1}}$$

**$m_{i+2}$ è recuperato.** ✓

---

#### **Caso generale: dimostrazione per induzione**

**Ipotesi induttiva:** supponiamo di aver recuperato $w_t$ e $m_t, m_{t+1}, \ldots, m_t$ per tutti i valori fino a $t$.

**Passo induttivo:** per $t \geq i$:

1. **Recupero di $m_{t+1}$** (da file aggiornato):
$$c'_t = m_{t+1} \oplus w_t \implies \boxed{m_{t+1} = c'_t \oplus w_t}$$

2. **Recupero di $w_{t+1}$** (da file originale):
$$c_{t+1} = m_{t+1} \oplus w_{t+1} \implies \boxed{w_{t+1} = c_{t+1} \oplus m_{t+1}}$$

Per induzione, questo vale per ogni $t \geq i$.

> ✅ **Conclusione:** a partire da $m_i$ noto, usando il file originale per ricavare il keystream e il file aggiornato per ricavare i plaintext successivi, si recuperano **tutti** i valori $w_t$ e $m_t$ per $t \geq i$ tramite XOR, senza mai conoscere la chiave $K$.

---

### **4. Schema visivo della deduzione**

```
NOTO:  m_i
         │
         ▼
w_i  =  c_i ⊕ m_i           (file originale, blocco i)
         │
         ▼
m_{i+1} = c'_i ⊕ w_i        (file aggiornato, blocco i)
         │
         ▼
w_{i+1} = c_{i+1} ⊕ m_{i+1} (file originale, blocco i+1)
         │
         ▼
m_{i+2} = c'_{i+1} ⊕ w_{i+1}(file aggiornato, blocco i+1)
         │
         ▼
         ... (continua per tutti i blocchi successivi)
```

---

### **5. Implicazioni di sicurezza**

Questa dimostrazione espone una vulnerabilità **strutturale** della modalità OFB quando usata in scenari con modifiche al file cifrato.

**Perché OFB è vulnerabile qui:**

1. **Keystream fisso:** usare la stessa coppia $(K, IV)$ per cifrare versioni diverse dello stesso file produce lo stesso keystream per le stesse posizioni.

2. **Propagazione deterministica:** conoscendo un solo plaintext $m_i$ in una posizione nota, l'intera sequenza del keystream a partire da quella posizione è recuperabile.

3. **Assenza di dipendenza incrociata:** in modalità CBC il ciphertext di ogni blocco dipende da tutti i blocchi precedenti. In OFB il keystream è **completamente indipendente** dal plaintext, il che è vantaggioso per la resistenza agli errori ma catastrofico in caso di riutilizzo del keystream.

> ⚠️ **Regola fondamentale:** la coppia $(K, IV)$ in modalità OFB (e CTR) **non deve mai essere riutilizzata** per cifrare dati diversi. Il riutilizzo compromette la riservatezza di tutti i blocchi a partire dal punto di coincidenza.

> 💡 **Confronto con CBC:** in CBC, $c_t = E_K(m_t \oplus c_{t-1})$. Se si modifica un blocco, l'intero ciphertext successivo cambia in modo imprevedibile. In OFB, la modifica di un blocco plaintext **non altera il keystream**, lasciando intatta la struttura che permette la deduzione.

---

### **6. Esercizio svolto stile esame (30 punti)**

#### **Testo (Appello 230907 / 230908)**

> Un file è cifrato in modalità OFB: $c_t = m_t \oplus w_t$, con $w_0 = IV$ e $w_t = E_K(w_{t-1})$. Sia dato il file originale cifrato $[c_1, c_2, c_3, c_4, c_5]$. Si elimina il blocco $c_2$ e i blocchi successivi vengono re-cifrati con lo stesso keystream (a partire da $w_2$), ottenendo il file aggiornato $[c_1, c'_2, c'_3, c'_4]$.
>
> È noto che $m_2 = 1011$, e i valori (in bit): $c_2 = 0110$, $c'_2 = 1101$, $c_3 = 1010$, $c'_3 = 0011$.
>
> Dimostrare e calcolare: ricavare $w_2$, $m_3$, $w_3$, $m_4$. *(30 pt)*

---

#### **Soluzione:**

**Recupero di $w_2$** (da file originale, blocco 2, con $m_2$ noto):
$$w_2 = c_2 \oplus m_2 = 0110 \oplus 1011 = 1101$$

**Recupero di $m_3$** (da file aggiornato, blocco 2):
$$c'_2 = m_3 \oplus w_2 \implies m_3 = c'_2 \oplus w_2 = 1101 \oplus 1101 = 0000$$

**Recupero di $w_3$** (da file originale, blocco 3, con $m_3$ noto):
$$w_3 = c_3 \oplus m_3 = 1010 \oplus 0000 = 1010$$

**Recupero di $m_4$** (da file aggiornato, blocco 3):
$$c'_3 = m_4 \oplus w_3 \implies m_4 = c'_3 \oplus w_3 = 0011 \oplus 1010 = 1001$$

**Riepilogo:**

| Variabile | Valore |
|-----------|--------|
| $w_2$ | $1101$ |
| $m_3$ | $0000$ |
| $w_3$ | $1010$ |
| $m_4$ | $1001$ |

---

### **7. Tabella riassuntiva della dimostrazione**

| Passo | Formula | Fonte |
|-------|---------|-------|
| $w_i = c_i \oplus m_i$ | Keystream al passo $i$ | File originale + $m_i$ noto |
| $m_{t+1} = c'_t \oplus w_t$ | Plaintext successivo | File aggiornato |
| $w_{t+1} = c_{t+1} \oplus m_{t+1}$ | Keystream al passo $t+1$ | File originale |
| Ripeti per $t = i, i+1, \ldots$ | Deduzione completa | Alternanza file originale/aggiornato |

> ✅ **Recap:** la modalità OFB genera un keystream fisso indipendente dal plaintext. Se un blocco $m_i$ è noto, si calcola $w_i = c_i \oplus m_i$. Da lì, alternando le equazioni del file originale e di quello aggiornato, si recuperano tutti i blocchi successivi tramite XOR. La dimostrazione è induttiva: ogni passo usa i valori del passo precedente. Il punto debole di OFB è il riutilizzo del keystream.
