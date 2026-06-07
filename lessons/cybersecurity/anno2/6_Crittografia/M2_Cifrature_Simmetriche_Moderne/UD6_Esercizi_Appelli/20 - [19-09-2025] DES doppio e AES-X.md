# DES doppio e AES-X — Esercizio d'esame

> 🗂️ **Fonte:** appello del **19 settembre 2025**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 19 settembre 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L6%20-%20Appello%2019%20settembre%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Cifratura simmetrica: DES doppio e AES-X (40 punti)

### Traccia originale

> **(40 punti) Cifratura simmetrica.**
>
> a. (10) Si descriva l'attacco meet in the middle per DES doppio.
>
> b. (30) Si consideri AES-X una versione modificata di AES che utilizza anche lo XOR: Siano $k_1$ e $k_2$ due chiavi AES di 128 bit, la cifratura di un blocco $m$ avviene così:
> $$AESX_{k_1,k_2}(m) = AES_{k_1}(m) \oplus k_2$$
>
> i. (10) Si descrivano le caratteristiche di AES-X.
>
> ii. (20) Si consideri un attacco di tipo known plaintext. Si discuta la sicurezza di tale sistema sia su AES standard che su AES-X.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — MITM per Double DES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2 - AES-X - key whitening e analisi sicurezza KPT.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2%20-%20AES-X%20-%20key%20whitening%20e%20analisi%20sicurezza%20KPT.md) — AES-X

### Soluzione dettagliata

#### Parte a — Meet-in-the-Middle per DES doppio (10 punti)

(Trattazione completa in L5, Domanda 2b. Si riporta per completezza.)

**Double DES:** $C = DES_{K_2}(DES_{K_1}(P))$.

Definiamo $X = DES_{K_1}(P) = DES_{K_2}^{-1}(C)$.

**Attacco MITM:**

1. **Forward:** per ogni $K \in \{0,1\}^{56}$, calcola $X_K = DES_K(P)$ e memorizza in tabella $T$.
2. **Backward:** per ogni $K' \in \{0,1\}^{56}$, calcola $Y_{K'} = DES_{K'}^{-1}(C)$ e cerca in $T$.
3. Se $Y_{K'} = X_K$, il candidato è $(K_1, K_2) = (K, K')$.

**Complessità:** Tempo $O(2^{57})$, Spazio $O(2^{56})$. Sicurezza effettiva: 57 bit (invece dei 112 attesi).

#### Parte b.i — Caratteristiche di AES-X (10 punti)

**Schema AES-X:**

$$AESX_{k_1,k_2}(m) = AES_{k_1}(m) \oplus k_2$$

AES-X è una variante di AES che applica un'operazione di **key whitening** in uscita: dopo la cifratura AES standard con chiave $k_1$, si esegue uno XOR con una seconda chiave $k_2$.

**Caratteristiche:**

1. **Chiave totale:** $k_1 \| k_2$ = 256 bit (due chiavi AES da 128 bit ciascuna). Lo spazio delle chiavi è $2^{256}$.

2. **Struttura:** AES-X è un esempio di **key whitening** (o outer whitening). Il whitening è l'idea di aggiungere della chiave prima o dopo il cifrario principale tramite operazioni semplici (XOR) per rendere più costosi gli attacchi.

3. **Invertibilità:** la decifratura è:

$$m = AES_{k_1}^{-1}(AESX_{k_1,k_2}(m) \oplus k_2) = AES_{k_1}^{-1}(AES_{k_1}(m))$$

ovvero:

$$AESX^{-1}_{k_1,k_2}(c) = AES_{k_1}^{-1}(c \oplus k_2)$$

4. **Efficienza:** il costo computazionale è quasi identico ad AES (una cifratura AES + uno XOR), trascurabile il costo aggiuntivo.

5. **Relazione con AES XEXX/XEX:** la struttura è correlata alle modalità XEX (XOR-Encrypt-XOR) usate in crittografia authenticated encryption.

> 💡 Il key whitening è usato anche nel DES: DESX applica XOR della chiave prima e dopo DES, aumentando la sicurezza contro brute force senza aumentare significativamente il costo computazionale.

#### Parte b.ii — Sicurezza di AES standard e AES-X contro known plaintext attack (20 punti)

**Sicurezza di AES standard contro KPA:**

Sia $C = AES_{k_1}(m)$ con $k_1$ di 128 bit.

**KPA su AES:** l'avversario conosce coppie $(m_i, C_i)$. Per trovare $k_1$, deve eseguire una ricerca esaustiva su $2^{128}$ possibili chiavi. Anche un attacco a dizionario richiede $2^{128}$ valutazioni.

**Sicurezza stimata:** $2^{128}$ operazioni per brute force. Nessun attacco KPA pratico noto contro AES con chiave di 128 bit. AES è considerato sicuro contro KPA.

**Sicurezza di AES-X contro KPA:**

Sia $C = AES_{k_1}(m) \oplus k_2$ con $k_1, k_2$ entrambi di 128 bit.

**Attacco KPA su AES-X:**

Dall'equazione di cifratura: $k_2 = AES_{k_1}(m) \oplus C$ (per qualsiasi coppia $(m, C)$ nota).

**Strategia dell'attacco:**

**Fase 1 — Guess di $k_1$:** per ogni possibile $k_1 \in \{0,1\}^{128}$:

- Calcola $AES_{k_1}(m_1)$ per il primo plaintext $m_1$ noto.
- Calcola il candidato: $k_2^* = AES_{k_1}(m_1) \oplus C_1$.

**Fase 2 — Verifica con una seconda coppia:** verifica se $AES_{k_1}(m_2) \oplus k_2^* = C_2$ per una seconda coppia $(m_2, C_2)$ nota.

**Analisi della complessità:**

- In Fase 1 si calcolano $2^{128}$ candidati $(k_1, k_2^*)$.
- In Fase 2 si verificano i candidati: ogni coppia elimina circa $2^{128} - 1$ falsi positivi.
- Con due coppie note, il numero di falsi positivi attesi è $2^{256} / 2^{128} = 2^{128}$.
- Con tre coppie: $2^{256} / 2^{192} = 2^{64}$ falsi positivi.
- Con quattro coppie: $2^{256}/2^{256} \approx 1$ → unica soluzione.

**Quindi l'attacco KPA su AES-X richiede $O(2^{128})$ operazioni, non $O(2^{256})$.**

> ⚠️ **Sorprendente:** AES-X con chiave da 256 bit (128+128) non offre sicurezza superiore a AES con chiave da 128 bit contro KPA! La seconda chiave $k_2$ può essere calcolata immediatamente da qualsiasi coppia nota, riducendo il problema a trovare $k_1$ su $2^{128}$ possibilità.

**Perché succede?**

L'operazione finale $\oplus k_2$ è lineare rispetto a $k_2$: dato $(m, C)$, la relazione $k_2 = AES_{k_1}(m) \oplus C$ determina $k_2$ univocamente una volta fissato $k_1$. Quindi la ricerca non è su $\{k_1\} \times \{k_2\}$ ($2^{256}$ possibilità) ma su $\{k_1\}$ ($2^{128}$ possibilità).

**Confronto riassuntivo:**

| Sistema                | KPA security (bit) | Spazio chiave (bit) |
| ---------------------- | ------------------ | ------------------- |
| AES-128                | 128                | 128                 |
| AES-256                | 256                | 256                 |
| AES-X (k1=128, k2=128) | **128**            | 256                 |

> 💡 Per aumentare effettivamente la sicurezza oltre AES-128, il key whitening dovrebbe essere applicato sia prima che dopo la cifratura (XEX: $k_2 \oplus AES_{k_1}(m \oplus k_3)$), oppure usare chiave AES-256 nativa. AES-X con whitening solo in uscita non aiuta contro KPA.
