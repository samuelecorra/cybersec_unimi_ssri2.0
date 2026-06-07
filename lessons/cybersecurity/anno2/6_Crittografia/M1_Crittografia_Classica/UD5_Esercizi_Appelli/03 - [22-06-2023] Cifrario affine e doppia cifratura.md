# Cifrario affine e doppia cifratura — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 giugno 2023**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 giugno 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L3%20-%20Appello%2022%20giugno%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura simmetrica: sicurezza e cifrario affine (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.**
>
> a. (5) Dare una definizione di sicurezza condizionata ed incondizionata.
>
> b. (5) Descrivere un cifrario incondizionatamente sicuro.
>
> c. (10) Per un cifrario affine con chiave $k=(a,b)$ si sa che $E_{(a,b)}(\text{O}) = \text{Z}$ e $E_{(a,b)}(\text{T}) = \text{I}$. Trovare la chiave utilizzata e applicarla alla cifratura di "CIAO".
>
> d. (10) Sempre per un cifrario affine, l'applicazione in sequenza con chiavi $k_1=(a_1,b_1)$ e $k_2=(a_2,b_2)$, $E_{k_1}(E_{k_2}(\text{plaintext}))$, aumenta la robustezza del cifrario? (giustificare)

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — sicurezza condizionata/incondizionata
- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrario affine

### Soluzione dettagliata

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

- **Sicurezza incondizionata (perfetta):** il cifrario resiste a un avversario con **potenza di calcolo illimitata**. Formalmente, il ciphertext non dà alcuna informazione sul plaintext: $\Pr[M = m \mid C = c] = \Pr[M = m]$ per ogni $m, c$ (Shannon). Richiede chiave lunga almeno quanto il messaggio.
- **Sicurezza condizionata (computazionale):** il cifrario resiste a un avversario con **risorse di calcolo limitate** (tempo polinomiale). La sicurezza dipende dalla difficoltà pratica di un problema (forza bruta, fattorizzazione, log discreto): teoricamente violabile, ma non in tempo ragionevole.

#### Parte b — Un cifrario incondizionatamente sicuro (5 punti)

Il **One-Time Pad (OTP)** di Vernam: $c_i = m_i \oplus k_i$ con chiave $k$ **casuale, lunga quanto il messaggio e usata una sola volta**. Per ogni ciphertext, ogni plaintext è equiprobabile (esiste una chiave che lo produce), quindi il ciphertext non rivela nulla: sicurezza perfetta dimostrata da Shannon. Limiti pratici: distribuzione e gestione di chiavi enormi, mai riusabili.

#### Parte c — Trovare la chiave affine e cifrare "CIAO" (10 punti)

Cifrario affine: $E_{(a,b)}(x) = (a x + b) \bmod 26$, con $\gcd(a,26)=1$. Codifica A=0, …, Z=25. Dati: O=14, Z=25, T=19, I=8.

$$\begin{cases} 14a + b \equiv 25 \pmod{26} \\ 19a + b \equiv 8 \pmod{26} \end{cases}$$

Sottraendo la prima dalla seconda: $5a \equiv 8 - 25 = -17 \equiv 9 \pmod{26}$. L'inverso di 5 mod 26 è 21 (perché $5\cdot21 = 105 \equiv 1$), quindi:
$$a \equiv 21 \cdot 9 = 189 \equiv 189 - 7\cdot26 = 7 \pmod{26}$$
Da $14a + b \equiv 25$: $b \equiv 25 - 14\cdot7 = 25 - 98 = -73 \equiv -73 + 3\cdot26 = 5 \pmod{26}$.

**Chiave:** $k = (a,b) = (7,5)$, con $\gcd(7,26)=1$ (valida). Verifica: $E(\text{O}) = 7\cdot14+5 = 103 \equiv 25 = \text{Z}$ ✓; $E(\text{T}) = 7\cdot19+5 = 138 \equiv 8 = \text{I}$ ✓.

**Cifratura di "CIAO"** (C=2, I=8, A=0, O=14):
$$E(2) = 7\cdot2+5 = 19 = \text{T}, \quad E(8) = 7\cdot8+5 = 61 \equiv 9 = \text{J}$$
$$E(0) = 5 = \text{F}, \quad E(14) = 7\cdot14+5 = 103 \equiv 25 = \text{Z}$$

**"CIAO" → "TJFZ"**.

#### Parte d — La doppia cifratura affine aumenta la robustezza? (10 punti)

**No.** Componendo due cifrari affini:
$$E_{k_1}(E_{k_2}(x)) = a_1(a_2 x + b_2) + b_1 = (a_1 a_2)\,x + (a_1 b_2 + b_1) \pmod{26}$$
che è **ancora un cifrario affine** con chiave $(a', b') = (a_1 a_2 \bmod 26,\; a_1 b_2 + b_1 \bmod 26)$.

L'insieme dei cifrari affini è **chiuso rispetto alla composizione** (forma un gruppo): la doppia cifratura non produce nulla che un singolo affine non possa già produrre. Lo spazio delle chiavi resta quello di un affine ($\varphi(26)\cdot26 = 12 \cdot 26 = 312$ chiavi), quindi la doppia cifratura **non aumenta** la robustezza e resta banalmente violabile (2 coppie note bastano).

> 💡 È lo stesso motivo per cui il "doppio Cesare" è un Cesare e per cui servì il **3DES** e non il "2DES": comporre cifrari aiuta solo se il cifrario **non è chiuso** rispetto alla composizione.
