# Feistel a 2 round — Esercizio d'esame

> 🗂️ **Fonte:** appello del **12 luglio 2023**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 12 luglio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L5%20-%20Appello%2012%20luglio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Cifratura simmetrica: rete di Feistel a 2 round (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.**
>
> a. (10) Descrivere l'utilizzo delle strutture di Feistel, ad esempio in DES.
>
> b. (20) Rete di Feistel a 2 fasi, chiave 8 bit, blocco 16 bit. Chiave come numero decimale (0–255), input come due numeri decimali. Derivazione sottochiave $K_i = K + 75 i \pmod{256}$, $0\le i\le1$. Funzione $f(K_i, R_i) = 127\,(K_i + R_i) \pmod{256}$, con $R_i$ rappresentazione decimale degli 8 bit di destra. Cifrare il messaggio $(86, 83)$ con chiave $89$.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — struttura Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20%20Struttura%20del%20DES.md) — Feistel nel DES

### Soluzione dettagliata

#### Parte a — Strutture di Feistel in DES (10 punti)

Una **rete di Feistel** cifra un blocco diviso in due metà $(L, R)$ iterando, per ogni round $i$:
$$L_i = R_{i-1}, \qquad R_i = L_{i-1} \oplus f(R_{i-1}, K_i)$$
dove $f$ è la funzione di round e $K_i$ la sottochiave del round.

**Proprietà chiave:** la decifratura usa **la stessa struttura** percorrendo le sottochiavi in ordine inverso, **senza dover invertire $f$**. Questo perché ogni round è un'involuzione strutturale: ciò permette di usare funzioni $f$ arbitrarie (anche non invertibili), spostando tutta la sicurezza su confusione/diffusione di $f$ e sul numero di round.

**Nel DES:** blocco 64 bit (metà da 32), **16 round**, sottochiavi da 48 bit generate dal *key schedule*; la $f$ di DES combina espansione, XOR con la sottochiave, **S-box** (non linearità/confusione) e permutazione (diffusione). La struttura di Feistel rende cifratura e decifratura quasi identiche in hardware.

#### Parte b — Cifratura numerica di $(86,83)$ con $K=89$ (20 punti)

Blocco $16$ bit $= L\,\|\,R$ con $L, R$ di 8 bit. Plaintext: $L_0 = 86$, $R_0 = 83$. Chiave $K = 89$.

**Sottochiavi** ($K_i = K + 75i \bmod 256$): $\;K_0 = 89$, $\;K_1 = 89 + 75 = 164$.

**Round 1** ($i=0$, sottochiave $K_0=89$, $R_0 = 83$):
$$f(K_0, R_0) = 127\,(89 + 83) \bmod 256 = 127 \cdot 172 \bmod 256 = 21844 \bmod 256 = 84$$
$$L_1 = R_0 = 83$$
$$R_1 = L_0 \oplus f = 86 \oplus 84 = \texttt{01010110} \oplus \texttt{01010100} = \texttt{00000010} = 2$$

**Round 2** ($i=1$, sottochiave $K_1=164$, $R_1 = 2$):
$$f(K_1, R_1) = 127\,(164 + 2) \bmod 256 = 127 \cdot 166 \bmod 256 = 21082 \bmod 256 = 90$$
$$L_2 = R_1 = 2$$
$$R_2 = L_1 \oplus f = 83 \oplus 90 = \texttt{01010011} \oplus \texttt{01011010} = \texttt{00001001} = 9$$

**Ciphertext:** $C = (L_2, R_2) = (2, 9)$, ovvero in binario $\texttt{00000010}\,\texttt{00001001}$.

> 📌 **Riepilogo:**
>
> | Fase | $L$ | $R$ | $f(K_i,R)$ |
> |------|-----|-----|------------|
> | Plaintext | 86 | 83 | — |
> | Dopo Round 1 ($K_0=89$) | 83 | 2 | $127\cdot172 \bmod 256 = 84$ |
> | Dopo Round 2 ($K_1=164$) | 2 | 9 | $127\cdot166 \bmod 256 = 90$ |

> ⚠️ Sicurezza: $f$ è **affine** ($127(K+R)\bmod256$) → lineare, vulnerabile alla crittoanalisi lineare; chiave a 8 bit (256 valori) → forza bruta immediata; solo 2 round. Cifrario puramente didattico.
