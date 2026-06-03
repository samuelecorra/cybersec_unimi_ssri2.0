# **L1 – Cifrari non-standard XOR+hash: decifratura e CPA attack**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 3 appelli (230216, 230707, 250210) con peso di **35 punti** ciascuno.
>
> **Studia prima:**
> - [UD1 / L2 – Cifrari di Feistel](../UD1/L2%20-%20Cifrari%20di%20Feistel.md) → struttura L/R, XOR con funzione di round, principi di confusione/diffusione
> - [UD2 / L2 – Modalità operative del DES](../UD2/L2%20-%20Modalità%20operative%20del%20DES.md) → conoscere ECB, CBC, CFB, OFB, CTR e il concetto di propagazione degli errori

---

### **1. Il pattern degli esami: cifrari toy non-standard**

Il professore costruisce cifrari *ad hoc* assemblando componenti note (XOR, funzioni hash, chiavi fisse) e chiede di:

1. **Derivare la decifratura** a partire dalle equazioni di cifratura.
2. **Costruire un Chosen-Plaintext Attack (CPA)** per rompere il cifrario.
3. **Eseguire un calcolo numerico** su valori piccoli.

Questi cifrari **non esistono nel programma canonico**, ma le tecniche per analizzarli sono sempre le stesse. Questa lezione fornisce la ricetta generale con esempi concreti.

---

### **2. Schema generale di cifratura a due blocchi con hash**

Il tipo di cifrario più comune negli appelli ha la forma:

$$
\begin{cases}
c_1 = f(m_1, m_2, k_1) \\
c_2 = g(m_1, m_2, c_1, k_2)
\end{cases}
$$

dove $f$ e $g$ sono composizioni di XOR con una funzione hash pubblica $h(\cdot)$ e le chiavi $k_1, k_2$.

#### **Variante canonica degli appelli (230216, 230707, 250210):**

$$
\begin{cases}
c_1 = m_1 \oplus h(m_2) \oplus k_1 \\
c_2 = m_2 \oplus c_1 \oplus k_2
\end{cases}
$$

> 📌 La funzione hash $h$ è **pubblica e nota** all'avversario. Anche il suo valore su qualsiasi input è calcolabile. La segretezza del cifrario risiede **solo nelle chiavi** $k_1$ e $k_2$.

---

### **3. Metodo per derivare la decifratura**

**Regola generale:** inizia dall'ultima equazione di cifratura e isola la variabile plaintext. Procedi a ritroso.

#### **Applicazione alla variante canonica:**

**Passo 1 — recupera $m_2$ da $c_2$:**

$$
c_2 = m_2 \oplus c_1 \oplus k_2
\implies
\boxed{m_2 = c_2 \oplus c_1 \oplus k_2}
$$

**Passo 2 — recupera $m_1$ da $c_1$:**

Ora che $m_2$ è noto, si può calcolare $h(m_2)$:

$$
c_1 = m_1 \oplus h(m_2) \oplus k_1
\implies
\boxed{m_1 = c_1 \oplus h(m_2) \oplus k_1}
$$

> ✅ **Decifratura completa:**
> $$m_2 = c_2 \oplus c_1 \oplus k_2, \qquad m_1 = c_1 \oplus h(m_2) \oplus k_1$$
> Il destinatario che conosce $(k_1, k_2)$ decifra in due passaggi.

---

### **4. Metodo per costruire un CPA attack**

Un **Chosen-Plaintext Attack (CPA)** consiste nel scegliere messaggi ad hoc, ottenerne la cifratura dall'oracolo, e sfruttare le coppie $(m, c)$ per ricavare informazioni sulla chiave o sul sistema.

**Strategia generale per cifrari XOR+hash a due blocchi:**

> 📌 **Ricetta CPA:** scegli $m_1 = \mathbf{0}$ per azzerare il termine $m_1$ nel primo blocco e isolare la relazione tra $h(m_2)$ e $k_1$.

#### **Attacco per recuperare $k_1$ e $k_2$ con due query:**

**Query 1:** scegli $m = (0, 0)$ (entrambi i blocchi nulli).

$$
c_1^{(1)} = 0 \oplus h(0) \oplus k_1 = h(0) \oplus k_1
$$

Poiché $h$ è pubblica e $h(0)$ è calcolabile dall'attaccante:

$$
\boxed{k_1 = c_1^{(1)} \oplus h(0)}
$$

$$
c_2^{(1)} = 0 \oplus c_1^{(1)} \oplus k_2 = c_1^{(1)} \oplus k_2
$$

$$
\boxed{k_2 = c_2^{(1)} \oplus c_1^{(1)}}
$$

> ⚠️ **Con una sola query** al cifrario il CPA recupera **entrambe le chiavi**! Il cifrario è completamente rotto in senso CPA perché non ha alcun meccanismo di randomizzazione (è deterministico).

**Verifica (Query 2):** scegli $m = (m_1, m_2)$ arbitrario e verifica che la decifratura con le chiavi trovate restituisca il messaggio originale. ✓

---

### **5. Perché questo cifrario è insicuro: analisi strutturale**

Il cifrario $c_1 = m_1 \oplus h(m_2) \oplus k_1,\ c_2 = m_2 \oplus c_1 \oplus k_2$ è **deterministico**: lo stesso messaggio produce sempre lo stesso ciphertext. Questo viola il requisito minimo di sicurezza IND-CPA (indistinguibilità sotto chosen-plaintext attack):

- Un avversario può distinguere due cifrature semplicemente cifrando lo stesso messaggio due volte.
- Scegliere $m_1 = 0$ isola completamente la chiave $k_1$, dato che $h(m_2)$ è calcolabile.
- La dipendenza $c_2 = m_2 \oplus c_1 \oplus k_2$ non aggiunge entropia randomizzata: $c_1$ è già determinato.

> 💡 **Confronto con Feistel:** nella struttura di Feistel standard, la funzione $F(K_i, R_{i-1})$ usa **sottochiavi derivate** che cambiano ad ogni round, rendendo molto più difficile isolare singoli componenti della chiave. Questi cifrari toy usano chiavi fisse direttamente, il che li rende immediatamente vulnerabili.

---

### **6. Varianti che possono comparire all'esame**

L'esame può presentare strutture leggermente diverse. La ricetta è sempre la stessa.

#### **Variante A:** $c_1 = m_1 \oplus k_1,\ c_2 = m_2 \oplus h(c_1) \oplus k_2$

Decifratura:
- $m_1 = c_1 \oplus k_1$ (diretto)
- $m_2 = c_2 \oplus h(c_1) \oplus k_2$ (usa $c_1$ già noto)

CPA: scegli $m_1 = 0$:
- $c_1 = k_1$ → $k_1 = c_1$ immediatamente!
- $c_2 = 0 \oplus h(k_1) \oplus k_2 = h(k_1) \oplus k_2$ → $k_2 = c_2 \oplus h(k_1)$

#### **Variante B:** $c_1 = h(m_1) \oplus m_2 \oplus k_1,\ c_2 = m_1 \oplus k_2$

Decifratura:
- $m_1 = c_2 \oplus k_2$ (dal secondo blocco, diretto)
- $m_2 = c_1 \oplus h(m_1) \oplus k_1$ (ora che $m_1$ è noto si calcola $h(m_1)$)

CPA: scegli $m_2 = 0$:
- $c_1 = h(m_1) \oplus k_1$ → $k_1 = c_1 \oplus h(m_1)$ (se $m_1$ è scelto da noi, $h(m_1)$ è calcolabile)
- Scegli anche $m_1 = 0$: $c_1 = h(0) \oplus k_1$ → $k_1 = c_1 \oplus h(0)$

---

### **7. Esercizio svolto completo stile esame (35 punti)**

#### **Testo**

> Sia dato il seguente cifrario simmetrico a due blocchi con chiave $(k_1, k_2)$ e funzione hash pubblica $h$:
>
> $$E_{k_1,k_2}(m_1, m_2) = (c_1, c_2) = (m_1 \oplus h(m_2) \oplus k_1,\ m_2 \oplus c_1 \oplus k_2)$$
>
> (a) Derivare l'algoritmo di decifratura. *(10 pt)*
>
> (b) Descrivere un chosen-plaintext attack che recuperi entrambe le chiavi. *(15 pt)*
>
> (c) Supponendo $h(0) = 1010$, $k_1 = 0110$, $k_2 = 1100$ (valori a 4 bit), cifrare il messaggio $m = (0000, 0000)$ e verificare con la decifratura. *(10 pt)*

---

#### **Soluzione (a) — Decifratura:**

Dall'ultima equazione: $c_2 = m_2 \oplus c_1 \oplus k_2 \implies m_2 = c_2 \oplus c_1 \oplus k_2$

Dalla prima equazione: $c_1 = m_1 \oplus h(m_2) \oplus k_1 \implies m_1 = c_1 \oplus h(m_2) \oplus k_1$

Algoritmo di decifratura:
1. $m_2 \leftarrow c_2 \oplus c_1 \oplus k_2$
2. $m_1 \leftarrow c_1 \oplus h(m_2) \oplus k_1$

---

#### **Soluzione (b) — CPA:**

**Scelta del messaggio:** $m = (0000, 0000)$.

**Cifratura ottenuta dall'oracolo:** l'attaccante riceve $(c_1, c_2)$.

**Recupero di $k_1$:**
$$c_1 = 0000 \oplus h(0000) \oplus k_1 = h(0) \oplus k_1 \implies k_1 = c_1 \oplus h(0)$$
Poiché $h$ è pubblica, $h(0)$ è calcolabile → $k_1$ recuperata.

**Recupero di $k_2$:**
$$c_2 = 0000 \oplus c_1 \oplus k_2 = c_1 \oplus k_2 \implies k_2 = c_2 \oplus c_1$$

**Risultato:** con **una sola query** al cifrario entrambe le chiavi sono recuperate. Il cifrario è completamente rotto.

---

#### **Soluzione (c) — Calcolo numerico:**

Dati: $h(0) = 1010$, $k_1 = 0110$, $k_2 = 1100$, $m = (0000, 0000)$.

**Cifratura:**

$$c_1 = 0000 \oplus h(0000) \oplus k_1 = 0000 \oplus 1010 \oplus 0110$$

$$= 1010 \oplus 0110 = 1100$$

$$c_2 = 0000 \oplus c_1 \oplus k_2 = 0000 \oplus 1100 \oplus 1100 = 0000$$

Ciphertext: $(c_1, c_2) = (1100, 0000)$.

**Verifica con decifratura:**

$$m_2 = c_2 \oplus c_1 \oplus k_2 = 0000 \oplus 1100 \oplus 1100 = 0000\ ✓$$

$$h(m_2) = h(0000) = 1010$$

$$m_1 = c_1 \oplus h(m_2) \oplus k_1 = 1100 \oplus 1010 \oplus 0110 = 0000\ ✓$$

---

### **8. Ricetta riassuntiva**

| Passo | Azione |
|-------|--------|
| **Decifratura** | Isola le variabili plaintext partendo dall'ultima equazione e procedendo a ritroso |
| **CPA — recupero chiave** | Scegli $m_1 = 0$ (o $m_2 = 0$) per eliminare i termini plaintext; le chiavi emergono dagli XOR residui |
| **Calcolo numerico** | Applica le equazioni di cifratura passo-passo; XOR si fa bit per bit |
| **Analisi sicurezza** | Il cifrario è deterministico → non è IND-CPA sicuro; la struttura non randomizzata lo rende vulnerabile con una query |

> ✅ **Recap:** il professore costruisce cifrari toy con hash e XOR. La decifratura si trova sempre invertendo le equazioni dall'ultima alla prima. Il CPA si costruisce scegliendo messaggi speciali (0) che azzerano componenti ignoti e isolano le chiavi. Con una query è quasi sempre possibile recuperare entrambe le chiavi.
