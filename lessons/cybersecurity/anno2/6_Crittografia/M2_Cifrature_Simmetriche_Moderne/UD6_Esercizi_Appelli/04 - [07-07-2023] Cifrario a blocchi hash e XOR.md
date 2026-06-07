# Cifrario a blocchi hash e XOR — Esercizio d'esame

> 🗂️ **Fonte:** appello del **7 luglio 2023**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 7 luglio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L4%20-%20Appello%207%20luglio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Cifrario a blocchi basato su hash e XOR (variante) (25 punti)

### Traccia originale

> **(25 punti)** Cifrario a blocchi con hash $h$ (64 bit) e XOR, chiave $k = k_1\|k_2$, messaggio $m = m_1\|m_2$ (blocchi da 64 bit). La cifratura $E_k(m) = c_1\|c_2$ è:
> $$c_1 = h(m_1) \oplus m_2 \oplus k_1, \qquad c_2 = m_1 \oplus c_1 \oplus k_2$$
>
> a. (10 punti) Mostrare come avviene la decifratura conoscendo la chiave $k$.
>
> b. (15 punti) Considerazioni sulla sicurezza, in generale e nel caso di chosen-plaintext attack.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — cifrari a blocchi
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — funzioni hash

### Soluzione dettagliata

#### Parte a — Decifratura conoscendo $k$ (10 punti)

Si inverte nell'ordine giusto. Dalla seconda equazione, dato che $c_1$ è noto:
$$c_2 = m_1 \oplus c_1 \oplus k_2 \;\Rightarrow\; \boxed{m_1 = c_2 \oplus c_1 \oplus k_2}$$
Recuperato $m_1$, si calcola $h(m_1)$ e dalla prima:
$$c_1 = h(m_1) \oplus m_2 \oplus k_1 \;\Rightarrow\; \boxed{m_2 = c_1 \oplus h(m_1) \oplus k_1}$$

#### Parte b — Sicurezza e attacco chosen-plaintext (15 punti)

Come la variante "speculare" (cfr. 16/02/2023), il cifrario è **deterministico** (nessun IV) → non semanticamente sicuro, e **lineare rispetto allo XOR** → malleabile.

- **Recupero di $k_2$ da una sola coppia nota:** $k_2 = c_2 \oplus m_1 \oplus c_1$. Il mascheramento del secondo blocco di equazione è quindi noto.
- **Maschera del secondo blocco per ogni $m_1$:** scegliendo due plaintext con **lo stesso $m_1$** e $m_2 \neq m_2'$:
$$c_1 \oplus c_1' = (h(m_1)\oplus m_2 \oplus k_1) \oplus (h(m_1)\oplus m_2'\oplus k_1) = m_2 \oplus m_2'$$
cioè per $m_1$ fissato il cifrario è uno **XOR a maschera costante** $K' = h(m_1)\oplus k_1 = c_1 \oplus m_2$: l'avversario ricava $K'$ e cifra/decifra qualunque messaggio con quello stesso $m_1$, e può **flippare bit** scelti nel ciphertext (malleabilità).

> ⚠️ Deterministico + malleabile + chiave parziale ($k_2$, e $K'=h(m_1)\oplus k_1$) ricavabile sotto CPA → schema **insicuro**. La hash $h$ non aiuta: è pubblica e applicata solo a un blocco di plaintext (in CPA noto), quindi non introduce segretezza.
