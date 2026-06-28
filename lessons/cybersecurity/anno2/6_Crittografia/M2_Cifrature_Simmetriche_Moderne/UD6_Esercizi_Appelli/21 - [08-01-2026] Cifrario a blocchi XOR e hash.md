# Cifrario a blocchi XOR e hash — Esercizio d'esame

> 🗂️ **Fonte:** appello dell'**8 gennaio 2026**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello dell'8 gennaio 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L1%20-%20Appello%208%20gennaio%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.
>
> ⚠️ **Nota:** lo stesso schema e gli stessi parametri numerici (parte c) sono stati proposti nell'[appello del 10 febbraio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L2%20-%20Appello%2010%20febbraio%202025.md). Le soluzioni sono identiche.

---

## Domanda 2 — Cifrario a blocchi XOR+hash (35 punti)

### Traccia originale

> **(35 punti)** Considera il seguente cifrario a blocchi basato su una funzione hash $h$ e sullo XOR. Assumiamo che $h$ produca valori di hash lunghi 64 bit e una chiave simmetrica $k$ di 128 bit. Sia $m = m_1 \cdot m_2$ con $m_1, m_2$ di 64 bit e $k = k_1 \| k_2$ con $k_1, k_2$ di 64 bit.
>
> La cifratura $E_k(m) = c_1 c_2$ funziona così:
> $$c_1 = m_1 \oplus h(m_2) \oplus k_1$$
> $$c_2 = m_2 \oplus c_1 \oplus k_2$$
>
> a. (10 punti) Mostrare come avviene la decifratura nell'ipotesi di conoscere la chiave $k$.
>
> b. (15 punti) Fare considerazioni sulla sicurezza di questo cifrario e considerare un attacco di tipo chosen plaintext, scegliendo opportunamente $m_1$ e $m_2$.
>
> c. (10 punti) Nel caso $h$ sia la funzione complemento a bit e i numeri siano ridotti a messaggi di 32 bit e hash di 16 bit, cifrare $m = \{01010101\ 01010101\}\{1\}^{16}$ con chiave $k_1 = k_2 = 00001111\ 11110000$.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1 - Cifrari non-standard XOR+hash - decifratura e CPA attack.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L1%20-%20Cifrari%20non-standard%20XOR+hash%20-%20decifratura%20e%20CPA%20attack.md) — analisi completa di questo schema

### Soluzione dettagliata

#### Parte a — Decifratura (10 punti)

**Passo 1 — Recupero di $m_2$:**

$$\boxed{m_2 = c_2 \oplus c_1 \oplus k_2}$$

**Passo 2 — Recupero di $m_1$:** ora $h(m_2)$ è calcolabile:

$$\boxed{m_1 = c_1 \oplus h(m_2) \oplus k_1}$$

> 📌 La decifratura è sempre univoca grazie alla struttura XOR.

#### Parte b — Sicurezza e attacco CPA (15 punti)

**Attacco:** scegliamo $m = 0^{64} \| m_2$ per qualsiasi $m_2$ noto.

$$c_1 = h(m_2) \oplus k_1 \quad \Rightarrow \quad k_1 = c_1 \oplus h(m_2)$$

$$k_2 = c_2 \oplus m_2 \oplus c_1$$

**Con un solo messaggio scelto, l'intera chiave $k = k_1 \| k_2$ è compromessa.**

> ⚠️ La vulnerabilità deriva dalla linearità di $c_1$ rispetto a $k_1$: scegliendo $m_1 = 0^{64}$, il termine $m_1 \oplus k_1$ si riduce a $k_1$ (visibile direttamente dopo XOR con $h(m_2)$ calcolabile).

#### Parte c — Cifratura numerica con complemento a bit (10 punti)

**Dati:** $m_1 = 0101010101010101$, $m_2 = 1111111111111111$, $k_1 = k_2 = 0000111111110000$.

$$h(m_2) = \overline{1111111111111111} = 0000000000000000$$

**Calcolo di $c_1$:**

```
  0101010101010101   (m1)
⊕ 0000000000000000   (h(m2))
⊕ 0000111111110000   (k1)
= 0101101010100101
```

**Calcolo di $c_2$:**

```
  1111111111111111   (m2)
⊕ 0101101010100101   (c1)
= 1010010101011010
⊕ 0000111111110000   (k2)
= 1010101010101010
```

**Risultato:** $(c_1, c_2) = (0101101010100101,\ 1010101010101010)$.
