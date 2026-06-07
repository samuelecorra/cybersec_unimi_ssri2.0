# Sostituzione su parole binarie — Esercizio d'esame

> 🗂️ **Fonte:** appello del **15 luglio 2025**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 15 luglio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L4%20-%20Appello%2015%20luglio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura classica: sostituzione su parole binarie (30 punti)

### Traccia originale

> **(30 punti) Cifratura classica.**
>
> In un cifrario a sostituzione stringhe di 3 bit sono sostituite da altri blocchi di 3 bit, dove ciascun blocco viene sostituito con il blocco ottenuto sommando 1. Per esempio il blocco $000 \to 001$; $001 \to 010$, $010 \to 011$ etc.
>
> a. (5) Quanti cifrari a sostituzione esistono per parole binarie di lunghezza 3?
>
> b. (10) Se si scrive il cifrario ottenuto in forma tabellare, qual è la dimensione della tabella in bit? Generalizzare la risposta al caso di parole formate da $n$ bit.
>
> c. (15) Se il cifrario opera su un messaggio $m = (m_1, m_2, m_3)$ e con una chiave $k = (k_1, k_2, k_3)$ si ottiene il testo cifrato $c = (m_1 \oplus m_2 \oplus k_1,\ m_2 \oplus k_2,\ m_3 \oplus k_3)$, dove $\oplus$ è lo XOR:
>
> i. Quante sostituzioni sono possibili con questo cifrario?
>
> ii. Cifrare il messaggio $M_1 = 011001$ con chiave $k = (1,1,0)$, e decifrare il cifrato ottenuto.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L1 - Cifrari a sostituzione su parole binarie di n bit.md`](../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L1%20-%20Cifrari%20a%20sostituzione%20su%20parole%20binarie%20di%20n%20bit.md) — conteggio cifrari a sostituzione

### Soluzione dettagliata

#### Parte a — Numero di cifrari a sostituzione su parole di 3 bit (5 punti)

Un cifrario a sostituzione su parole di 3 bit è una **biiezione** (permutazione) sull'insieme $\{0,1\}^3 = \{000, 001, 010, 011, 100, 101, 110, 111\}$.

Questo insieme ha $2^3 = 8$ elementi.

Il numero di permutazioni di un insieme di $N$ elementi è $N!$.

Per parole di 3 bit: $N = 2^3 = 8$, quindi il numero di cifrari a sostituzione è:

$$8! = 40320$$

> 📌 In generale, per parole di $n$ bit, il numero di cifrari a sostituzione è $(2^n)!$.

#### Parte b — Dimensione della tabella e generalizzazione (10 punti)

**Rappresentazione tabellare del cifrario:**

Il cifrario descritto (somma di 1 modulo $2^3$) è la permutazione:
$$000 \to 001,\ 001 \to 010,\ 010 \to 011,\ 011 \to 100,\ 100 \to 101,\ 101 \to 110,\ 110 \to 111,\ 111 \to 000$$

Per descrivere completamente un cifrario a sostituzione su parole di 3 bit in forma tabellare, la tabella deve contenere:
- **Tutte le $2^3 = 8$ righe**: una per ciascun possibile valore di input.
- Per ogni riga, l'output corrispondente: un valore di **3 bit**.

**Dimensione della tabella:**

La tabella ha $2^3$ righe × 3 bit per riga = $8 \times 3 = 24$ bit.

**Generalizzazione a parole di $n$ bit:**

- Numero di righe: $2^n$ (tutti i possibili input)
- Bit per riga: $n$ (ogni output è di $n$ bit)
- **Dimensione totale:** $n \cdot 2^n$ bit

**Verifica con $n=3$:** $3 \cdot 2^3 = 3 \cdot 8 = 24$ bit. ✓

> 💡 Per confronto, una chiave DES (56 bit) rappresenta un'S-box parziale, mentre la specifica completa delle 8 S-box del DES richiede molto più spazio ($8 \times 64 \times 4 = 2048$ bit, ma non tutte le S-box del DES sono permutazioni complete).

#### Parte c — Cifrario XOR a blocchi (15 punti)

Il cifrario opera su blocchi di 3 bit con chiave di 3 bit, secondo le regole:

$$c = (m_1 \oplus m_2 \oplus k_1,\ m_2 \oplus k_2,\ m_3 \oplus k_3)$$

#### Parte c.i — Numero di sostituzioni possibili (5 punti)

La chiave $k = (k_1, k_2, k_3)$ con $k_i \in \{0,1\}$ ha $2^3 = 8$ possibili valori.

Ogni diverso valore di $k$ produce una diversa funzione di cifratura (ovvero una diversa sostituzione/permutazione sui blocchi di 3 bit).

**Numero di sostituzioni possibili = numero di chiavi distinte = $2^3 = 8$.**

> ⚠️ Questo numero (8) è molto minore del numero totale di permutazioni possibili su 3 bit ($8! = 40320$). Il cifrario XOR strutturato realizza solo un sottoinsieme molto limitato di tutte le possibili permutazioni, il che lo rende crittograficamente debole.

#### Parte c.ii — Cifratura e decifratura (10 punti)

**Messaggio:** $M_1 = 011001$ (6 bit = 2 blocchi da 3 bit)
- Primo blocco: $m = (0, 1, 1)$
- Secondo blocco: $m' = (0, 0, 1)$

**Chiave:** $k = (k_1, k_2, k_3) = (1, 1, 0)$

**Cifratura del primo blocco $(0, 1, 1)$:**

$$c_1 = m_1 \oplus m_2 \oplus k_1 = 0 \oplus 1 \oplus 1 = 0$$

$$c_2 = m_2 \oplus k_2 = 1 \oplus 1 = 0$$

$$c_3 = m_3 \oplus k_3 = 1 \oplus 0 = 1$$

Primo blocco cifrato: $(0, 0, 1) = $ **001**

**Cifratura del secondo blocco $(0, 0, 1)$:**

$$c_1 = m_1 \oplus m_2 \oplus k_1 = 0 \oplus 0 \oplus 1 = 1$$

$$c_2 = m_2 \oplus k_2 = 0 \oplus 1 = 1$$

$$c_3 = m_3 \oplus k_3 = 1 \oplus 0 = 1$$

Secondo blocco cifrato: $(1, 1, 1) = $ **111**

**Testo cifrato:** $C = 001111$

**Decifratura del ciphertext $C = 001111$:**

Dobbiamo invertire la funzione di cifratura. Dall'equazione:

$$c_1 = m_1 \oplus m_2 \oplus k_1$$
$$c_2 = m_2 \oplus k_2$$
$$c_3 = m_3 \oplus k_3$$

**Recupero di $m$:** da queste equazioni, poiché XOR è la propria inversa:

$$m_3 = c_3 \oplus k_3$$

$$m_2 = c_2 \oplus k_2$$

$$m_1 = c_1 \oplus m_2 \oplus k_1 = c_1 \oplus (c_2 \oplus k_2) \oplus k_1$$

**Decifratura primo blocco** $c = (0, 0, 1)$, chiave $(1, 1, 0)$:

$$m_3 = 1 \oplus 0 = 1$$
$$m_2 = 0 \oplus 1 = 1$$
$$m_1 = 0 \oplus 1 \oplus 1 = 0$$

Primo blocco decrittato: $(0, 1, 1) = $ **011** ✓

**Decifratura secondo blocco** $c = (1, 1, 1)$, chiave $(1, 1, 0)$:

$$m_3 = 1 \oplus 0 = 1$$
$$m_2 = 1 \oplus 1 = 0$$
$$m_1 = 1 \oplus 0 \oplus 1 = 0$$

Secondo blocco decrittato: $(0, 0, 1) = $ **001** ✓

**Messaggio recuperato:** $M_1 = 011001$ ✓
