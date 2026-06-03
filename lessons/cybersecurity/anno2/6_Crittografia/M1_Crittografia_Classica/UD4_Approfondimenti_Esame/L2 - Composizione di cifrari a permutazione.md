# **L2 – Composizione di cifrari a permutazione**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 1 appello d'esame (240906) con peso di **15 punti**.
>
> **Studia prima:**
> - [UD2 / L2 – Metodi di cifratura simmetrica](../UD2/L2%20-%20Metodi%20di%20cifratura%20simmetrica.md) → cifrari a trasposizione (Rail Fence, trasposizione per colonne), concetto di permutazione del testo

---

### **1. Definizione formale di cifrario a permutazione**

Nelle lezioni canoniche i cifrari a trasposizione (Rail Fence, trasposizione per colonne) vengono presentati informalmente. Qui ne diamo la formalizzazione algebrica che l'esame richiede.

Sia dato un **blocco di $n$ simboli** (lettere o bit). L'insieme di tutte le permutazioni su $n$ elementi è il **gruppo simmetrico** $S_n$, che contiene $n!$ elementi.

> 📌 Un **cifrario a permutazione** $P_k$ su blocchi di $n$ simboli è definito da una permutazione $\sigma_k \in S_n$:
>
> $$P_k(m_1, m_2, \ldots, m_n) = (m_{\sigma_k(1)},\ m_{\sigma_k(2)},\ \ldots,\ m_{\sigma_k(n)})$$
>
> La chiave $k$ specifica quale permutazione $\sigma_k$ applicare. Il messaggio in uscita è il messaggio in entrata con le posizioni rimescolate secondo $\sigma_k$.

#### **Convenzione: notazione tabellare**

La permutazione $\sigma_k$ si esprime come tabella a due righe:

$$
\sigma_k = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ \sigma(1) & \sigma(2) & \sigma(3) & \sigma(4) & \sigma(5) \end{pmatrix}
$$

dove la prima riga elenca le posizioni originali e la seconda riga elenca le posizioni di destinazione.

> 💡 Convenzione **scatter** (usata in questo documento): $\sigma(i) = j$ significa che il simbolo che si trovava in posizione $i$ nel messaggio in chiaro viene **spostato** in posizione $j$ nel messaggio cifrato.

---

### **2. Spazio delle chiavi**

Per blocchi di $n$ simboli, il numero di permutazioni possibili è:

$$
|S_n| = n!
$$

| $n$ | $n!$ |
|-----|------|
| 2   | 2 |
| 3   | 6 |
| 4   | 24 |
| 5   | 120 |
| 6   | 720 |
| 10  | 3 628 800 |

> ⚠️ Per blocchi di 5 simboli esistono 120 chiavi distinte. Questo è lo scenario dell'appello 240906.

---

### **3. Composizione di due cifrari a permutazione**

Dati due cifrari $P_{k_1}$ e $P_{k_2}$ (entrambi su blocchi di $n$ simboli), la **composizione** è:

$$
(P_{k_1} \circ P_{k_2})(m) = P_{k_1}(P_{k_2}(m))
$$

cioè: prima si applica $P_{k_2}$, poi $P_{k_1}$ al risultato.

#### **Proprietà fondamentale**

La composizione di due permutazioni è ancora una permutazione:

$$
\sigma_{k_1} \circ \sigma_{k_2} \in S_n
$$

> 📌 Il gruppo $S_n$ è **chiuso** rispetto alla composizione. Quindi il cifrario composito $P_{k_1} \circ P_{k_2}$ è equivalente a un cifrario $P_{k_3}$ con una singola chiave $k_3 = (k_1 \circ k_2)$.

#### **Come calcolare la tabella composita**

Per ogni posizione $i$ del messaggio originale, si calcola dove finisce applicando prima $\sigma_{k_2}$ poi $\sigma_{k_1}$:

$$
(\sigma_{k_1} \circ \sigma_{k_2})(i) = \sigma_{k_1}(\sigma_{k_2}(i))
$$

**Procedura passo-passo:**
1. Calcola $j = \sigma_{k_2}(i)$ (dove va la posizione $i$ dopo $P_{k_2}$).
2. Calcola $\sigma_{k_1}(j)$ (dove va la posizione $j$ dopo $P_{k_1}$).
3. La posizione finale è $\sigma_{k_1}(\sigma_{k_2}(i))$.

---

### **4. Analisi della sicurezza del cifrario composito**

#### **Il cifrario composito è più sicuro di ciascuno dei due?**

Dipende dallo spazio delle chiavi effettive.

**Caso 1 – Le chiavi $k_1$ e $k_2$ sono indipendenti e scelte casualmente da $S_n$:**

La composizione $\sigma_{k_1} \circ \sigma_{k_2}$ può produrre qualsiasi elemento di $S_n$ (poiché $S_n$ è un gruppo generato da qualsiasi coppia di generatori non banali). Pertanto lo spazio delle chiavi effettive del cifrario composito è ancora $n!$, **identico** a quello di ciascun cifrario singolo.

> ⚠️ **La composizione non aumenta la sicurezza** se entrambe le chiavi provengono dall'intero $S_n$: l'avversario non ha più spazio di chiavi da esplorare rispetto al cifrario singolo.

**Caso 2 – Le chiavi $k_1$ e $k_2$ sono fisse e note (solo il set di chiavi possibili è limitato):**

Se le chiavi sono scelte da un sottoinsieme $K \subset S_n$, la composizione $K \circ K = \{k_1 \circ k_2 \mid k_1, k_2 \in K\}$ può contenere permutazioni non in $K$. In questo caso la composizione può effettivamente **aumentare lo spazio** delle chiavi effettive.

**Caso 3 – Cifrario composito con stessa chiave:**

$P_k \circ P_k$ non è necessariamente la permutazione identità (lo è solo se $\sigma_k^2 = \text{id}$, cioè se $\sigma_k$ è un'involuzione).

> 💡 **Conclusione pratica:** usare due permutation cipher in cascata con chiavi casuali da $S_n$ **non migliora la sicurezza** rispetto a un singolo permutation cipher, perché il risultato è ancora un elemento di $S_n$ e l'avversario deve comunque esplorare al più $n!$ chiavi.

---

### **5. Esercizio svolto stile esame (Appello 240906)**

#### **Testo**

> Siano dati due cifrari a permutazione su blocchi di 5 lettere, con le seguenti chiavi:
>
> $k_1$: $1 \to 2,\ 2 \to 3,\ 3 \to 5,\ 4 \to 4,\ 5 \to 1$
>
> $k_2$: $1 \to 2,\ 2 \to 5,\ 3 \to 4,\ 4 \to 3,\ 5 \to 1$
>
> (a) Applicare il cifrario $P_{k_1}$ al messaggio `"ciaoe"`. *(5 pt)*
>
> (b) Applicare il cifrario $P_{k_2}$ al messaggio `"ciaoe"`. *(5 pt)*
>
> (c) Descrivere il cifrario composito $c = P_{k_1}(P_{k_2}(m))$ e commentarne il livello di sicurezza. *(5 pt)*

---

#### **Tabelle delle chiavi**

$$
\sigma_{k_1} = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 2 & 3 & 5 & 4 & 1 \end{pmatrix}
\qquad
\sigma_{k_2} = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 2 & 5 & 4 & 3 & 1 \end{pmatrix}
$$

---

#### **Risposta (a): Applicazione di $P_{k_1}$ a "ciaoe"**

Messaggio: $m = (c, i, a, o, e)$ con posizioni $1=c,\ 2=i,\ 3=a,\ 4=o,\ 5=e$.

Secondo $\sigma_{k_1}$: il simbolo in posizione $i$ va in posizione $\sigma_{k_1}(i)$.

| Posizione originale $i$ | Simbolo $m_i$ | Destinazione $\sigma_{k_1}(i)$ |
|------------------------|---------------|-------------------------------|
| 1 | c | 2 |
| 2 | i | 3 |
| 3 | a | 5 |
| 4 | o | 4 |
| 5 | e | 1 |

Costruzione del ciphertext: poniamo ogni simbolo alla sua posizione di destinazione.

| Posizione nel cifrato | Simbolo |
|-----------------------|---------|
| 1 | e (viene da pos. 5) |
| 2 | c (viene da pos. 1) |
| 3 | i (viene da pos. 2) |
| 4 | o (viene da pos. 4) |
| 5 | a (viene da pos. 3) |

$$
P_{k_1}(\text{``ciaoe''}) = \text{``ecioa''}
$$

---

#### **Risposta (b): Applicazione di $P_{k_2}$ a "ciaoe"**

Secondo $\sigma_{k_2}$:

| Posizione originale $i$ | Simbolo $m_i$ | Destinazione $\sigma_{k_2}(i)$ |
|------------------------|---------------|-------------------------------|
| 1 | c | 2 |
| 2 | i | 5 |
| 3 | a | 4 |
| 4 | o | 3 |
| 5 | e | 1 |

| Posizione nel cifrato | Simbolo |
|-----------------------|---------|
| 1 | e (viene da pos. 5) |
| 2 | c (viene da pos. 1) |
| 3 | o (viene da pos. 4) |
| 4 | a (viene da pos. 3) |
| 5 | i (viene da pos. 2) |

$$
P_{k_2}(\text{``ciaoe''}) = \text{``ecoai''}
$$

---

#### **Risposta (c): Cifrario composito $P_{k_1} \circ P_{k_2}$**

Calcoliamo la permutazione composita $\sigma_{k_1} \circ \sigma_{k_2}$, definita da:
$$(\sigma_{k_1} \circ \sigma_{k_2})(i) = \sigma_{k_1}(\sigma_{k_2}(i))$$

| $i$ | $\sigma_{k_2}(i)$ | $\sigma_{k_1}(\sigma_{k_2}(i))$ |
|-----|-------------------|--------------------------------|
| 1 | 2 | $\sigma_{k_1}(2) = 3$ |
| 2 | 5 | $\sigma_{k_1}(5) = 1$ |
| 3 | 4 | $\sigma_{k_1}(4) = 4$ |
| 4 | 3 | $\sigma_{k_1}(3) = 5$ |
| 5 | 1 | $\sigma_{k_1}(1) = 2$ |

Tabella composita:

$$
\sigma_{k_1} \circ \sigma_{k_2} = \begin{pmatrix} 1 & 2 & 3 & 4 & 5 \\ 3 & 1 & 4 & 5 & 2 \end{pmatrix}
$$

**Verifica:** applichiamo la composita a "ciaoe":

| Posizione originale $i$ | Simbolo | Destinazione |
|------------------------|---------|--------------|
| 1 | c | 3 |
| 2 | i | 1 |
| 3 | a | 4 |
| 4 | o | 5 |
| 5 | e | 2 |

Cifrato: posizione 1 = i, 2 = e, 3 = c, 4 = a, 5 = o → `"iecao"`

**Verifica a cascata:** $P_{k_1}(P_{k_2}(\text{``ciaoe''})) = P_{k_1}(\text{``ecoai''})$

Applichiamo $\sigma_{k_1}$ a `"ecoai"` = $(e,c,o,a,i)$:

| $i$ | Simbolo | Destinazione $\sigma_{k_1}(i)$ |
|-----|---------|-------------------------------|
| 1 | e | 2 |
| 2 | c | 3 |
| 3 | o | 5 |
| 4 | a | 4 |
| 5 | i | 1 |

Cifrato: pos 1=i, 2=e, 3=c, 4=a, 5=o → `"iecao"` ✓

**Analisi della sicurezza:**

La permutazione composita $\sigma_{k_1} \circ \sigma_{k_2}$ è un elemento di $S_5$ (120 permutazioni totali). Un avversario che sa che il cifrario usato è un permutation cipher su blocchi di 5 deve ancora esplorare al più 120 chiavi — **identico** al caso di un singolo permutation cipher. La composizione **non amplia lo spazio delle chiavi effettive**, poiché $S_5$ è già chiuso rispetto alla composizione.

> ✅ **Il cifrario composito $P_{k_1} \circ P_{k_2}$ ha lo stesso livello di sicurezza di un singolo permutation cipher su blocchi di 5 lettere** (spazio chiavi: $5! = 120$). Non è intrinsecamente più sicuro perché la sua chiave equivalente è semplicemente un altro elemento di $S_5$.

---

### **6. Riepilogo**

| Concetto | Formula / Valore |
|----------|----------------|
| Numero di chiavi per blocchi di $n$ simboli | $n!$ |
| Composizione $\sigma_{k_1} \circ \sigma_{k_2}$ | ancora in $S_n$ |
| Sicurezza del composito (chiavi da $S_n$) | uguale al singolo (stesse $n!$ chiavi) |
| Calcolo $(\sigma_{k_1} \circ \sigma_{k_2})(i)$ | $\sigma_{k_1}(\sigma_{k_2}(i))$ |

> ✅ **Recap:** la composizione di due permutation cipher è ancora un permutation cipher. Il gruppo $S_n$ è chiuso, quindi la doppia cifratura non aggiunge sicurezza se le chiavi provengono dall'intero $S_n$. All'esame, per trovare il cifrario composito basta costruire la tabella $(\sigma_{k_1} \circ \sigma_{k_2})(i) = \sigma_{k_1}(\sigma_{k_2}(i))$ per ogni posizione $i$.
