# Cifrario a blocchi hash e XOR — Esercizio d'esame

> 🗂️ **Fonte:** appello del **16 febbraio 2023**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 16 febbraio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L2%20-%20Appello%2016%20febbraio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Cifrario a blocchi basato su hash e XOR (35 punti)

### Traccia originale

> **(35 punti)** Considera il seguente cifrario a blocchi basato su una funzione hash $h$ e sullo XOR. $h$ produce hash di 64 bit e la chiave simmetrica $k$ è di pari lunghezza. Sia $m = m_1 \| m_2$ (concatenazione), con $m_1, m_2$ di 64 bit; analogamente $k = k_1 \| k_2$. La cifratura $E_k(m) = c_1 \| c_2$ è:
> $$c_1 = m_1 \oplus h(m_2) \oplus k_1, \qquad c_2 = m_2 \oplus c_1 \oplus k_2$$
>
> a. (10 punti) Mostrare come avviene la decifratura conoscendo la chiave $k$.
>
> b. (15 punti) Considerazioni sulla sicurezza (sugg. chosen plaintext, con $m_1$ e $m_2$ scelti opportunamente).
>
> c. (10 punti) Con $h = {}$ modulo 16, numeri ridotti a messaggi di 32 bit e hash di 16 bit, cifrare $m = \{0101010101010101\}\{1\}^{16}$ con $k_1 = k_2 = 0000111111110000$.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — struttura dei cifrari a blocchi
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — proprietà delle funzioni hash

### Soluzione dettagliata

#### Parte a — Decifratura conoscendo $k$ (10 punti)

Si invertono le equazioni nell'ordine giusto. Dalla seconda:
$$c_2 = m_2 \oplus c_1 \oplus k_2 \;\Rightarrow\; \boxed{m_2 = c_2 \oplus c_1 \oplus k_2}$$
($c_1$ è già noto perché fa parte del ciphertext). Recuperato $m_2$, si calcola $h(m_2)$ e dalla prima:
$$c_1 = m_1 \oplus h(m_2) \oplus k_1 \;\Rightarrow\; \boxed{m_1 = c_1 \oplus h(m_2) \oplus k_1}$$

L'ordine è essenziale: prima $m_2$ (che non dipende dall'hash), poi $m_1$ (che richiede $h(m_2)$).

#### Parte b — Sicurezza e attacco chosen-plaintext (15 punti)

Il cifrario è **deterministico** (nessun IV/nonce): lo stesso plaintext dà sempre lo stesso ciphertext, quindi **non è semanticamente sicuro** (un avversario riconosce messaggi ripetuti).

Peggio, è **lineare/malleabile** rispetto allo XOR. Sotto **chosen plaintext** si recupera materiale di chiave:

- **Recupero immediato di $k_2$.** Da una singola coppia nota $(m, c)$:
$$k_2 = c_2 \oplus m_2 \oplus c_1$$
quindi il mascheramento del secondo blocco è completamente compromesso.

- **Recupero di $h(m_2) \oplus k_1$ per ogni $m_2$.** Scegliendo due plaintext con **lo stesso $m_2$** e $m_1 \neq m_1'$:
$$c_1 \oplus c_1' = (m_1 \oplus h(m_2)\oplus k_1) \oplus (m_1' \oplus h(m_2)\oplus k_1) = m_1 \oplus m_1'$$
cioè il primo blocco si comporta come un **cifrario a flusso a XOR**: flippare un bit di $m_1$ flippa lo stesso bit di $c_1$ (e, via $c_2 = m_2 \oplus c_1 \oplus k_2$, anche di $c_2$). Da una coppia con un dato $m_2$ l'avversario ricava la maschera $K' = h(m_2)\oplus k_1 = c_1 \oplus m_1$ e da quel momento cifra/decifra **qualsiasi** messaggio con quello stesso $m_2$.

> ⚠️ Conclusioni: cifrario deterministico, malleabile, con $k_2$ ricavabile da una sola coppia e maschera del primo blocco ricavabile per ogni valore di $m_2$. Non offre alcuna sicurezza CPA. Le costruzioni reali usano un IV/nonce casuale e una funzione pseudocasuale non lineare (cifrari a blocchi veri come AES in modalità sicura).

#### Parte c — Esempio numerico ($h = \bmod\,16$, blocchi a 16 bit) (10 punti)

Parametri ridotti: messaggio 32 bit $= m_1 \| m_2$ con $m_1, m_2$ di 16 bit, hash a 16 bit, $k_1 = k_2$ di 16 bit.

$$m_1 = 0101010101010101, \quad m_2 = 1111111111111111, \quad k_1 = k_2 = 0000111111110000$$

**Hash:** $h(m_2) = m_2 \bmod 16$. In binario "modulo 16" significa prendere i 4 bit meno significativi: $m_2 = \texttt{1111}\,1111\,1111\,\mathbf{1111}$, quindi $h(m_2) = 15 = 0000000000001111$.

**Primo blocco:** $c_1 = m_1 \oplus h(m_2) \oplus k_1$

```
  m1     = 0101 0101 0101 0101
  h(m2)  = 0000 0000 0000 1111
  k1     = 0000 1111 1111 0000
  ---------------------- XOR
  c1     = 0101 1010 1010 1010
```

**Secondo blocco:** $c_2 = m_2 \oplus c_1 \oplus k_2$

```
  m2     = 1111 1111 1111 1111
  c1     = 0101 1010 1010 1010
  k2     = 0000 1111 1111 0000
  ---------------------- XOR
  c2     = 1010 1010 1010 0101
```

**Ciphertext:** $C = c_1 \| c_2 = 0101101010101010 \; 1010101010100101$.

> ✅ Verifica (decifratura): $m_2 = c_2 \oplus c_1 \oplus k_2 = 1111111111111111$ ✓; $h(m_2)=0000000000001111$; $m_1 = c_1 \oplus h(m_2) \oplus k_1 = 0101010101010101$ ✓.
