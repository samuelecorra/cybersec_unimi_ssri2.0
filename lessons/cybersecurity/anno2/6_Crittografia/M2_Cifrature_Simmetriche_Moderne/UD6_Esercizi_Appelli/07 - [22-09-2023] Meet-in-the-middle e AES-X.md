# Meet-in-the-middle e AES-X — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 settembre 2023**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 settembre 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L8%20-%20Appello%2022%20settembre%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Cifratura simmetrica: meet-in-the-middle e AES-X (40 punti)

### Traccia originale

> **(40 punti) Cifratura simmetrica.**
>
> a. (10) Descrivere l'attacco meet-in-the-middle per il DES doppio.
>
> b. (30) AES-X è una versione modificata di AES con XOR: con $k_1, k_2$ chiavi AES di 128 bit, $\text{AESX}_{k_1,k_2}(m) = \text{AES}_{k_1}(m) \oplus k_2$. i. (10) Descrivere le caratteristiche di AES-X. ii. (20) Discutere la sicurezza rispetto a un attacco known-plaintext, sia su AES standard sia su AES-X.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L5 - Double DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L5%20-%20Double%20DES.md) — Double DES e meet-in-the-middle
- [`../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2 - AES-X - key whitening e analisi sicurezza KPT.md`](../../M2_Cifrature_Simmetriche_Moderne/UD5_Approfondimenti_Esame/L2%20-%20AES-X%20-%20key%20whitening%20e%20analisi%20sicurezza%20KPT.md) — AES-X e key whitening

### Soluzione dettagliata

#### Parte a — Meet-in-the-middle sul Double DES (10 punti)

Il **Double DES** cifra $C = E_{k_2}(E_{k_1}(M))$ con due chiavi da 56 bit (112 bit totali). Ci si aspetterebbe sicurezza $2^{112}$, ma l'attacco **meet-in-the-middle** la riduce a $\sim 2^{57}$.

Idea: data una coppia nota $(M, C)$, vale $E_{k_1}(M) = D_{k_2}(C)$ (il "valore intermedio"). L'avversario:
1. calcola $E_{k_1}(M)$ per **tutte** le $2^{56}$ chiavi $k_1$ e le memorizza in tabella (valore → $k_1$);
2. calcola $D_{k_2}(C)$ per **tutte** le $2^{56}$ chiavi $k_2$ e cerca una **collisione** in tabella.

Ogni collisione $E_{k_1}(M) = D_{k_2}(C)$ fornisce una candidata $(k_1, k_2)$; con una o due coppie aggiuntive si elimina ogni falso positivo. Costo: $\sim 2^{56}$ cifrature + $2^{56}$ decifrature ($\approx 2^{57}$) e $\sim 2^{56}$ memoria, invece di $2^{112}$. È il motivo per cui il Double DES è inutile e si è passati al **Triple DES**.

#### Parte b.i — Caratteristiche di AES-X (10 punti)

AES-X applica un **key whitening** in **uscita**: cifra con AES sotto $k_1$ e poi XORa l'output con una seconda chiave $k_2$:
$$\text{AESX}_{k_1,k_2}(m) = \text{AES}_{k_1}(m) \oplus k_2$$
La chiave totale è di $128 + 128 = 256$ bit. La decifratura è $\text{AES}^{-1}_{k_1}(c \oplus k_2)$. È analogo alla costruzione **DESX**, ma con whitening **solo in uscita** (DESX whitening sia in ingresso sia in uscita). L'obiettivo *apparente* è aumentare la sicurezza alla forza bruta portandola da $2^{128}$ a $2^{256}$.

#### Parte b.ii — Sicurezza known-plaintext: AES vs AES-X (20 punti)

**AES standard.** Sotto known-plaintext, l'unico attacco generico è la **forza bruta sulla chiave** $k_1$: provare le $2^{128}$ chiavi finché $\text{AES}_{k_1}(m) = c$ su una coppia nota (con una seconda coppia si conferma). Sicurezza $\approx 2^{128}$, intrattabile.

**AES-X.** L'aggiunta di $k_2$ **non aumenta** la sicurezza contro il known-plaintext, perché il whitening è solo in uscita ed è **lineare**. Con **due** coppie note $(m, c)$ e $(m', c')$:
$$c \oplus c' = (\text{AES}_{k_1}(m)\oplus k_2) \oplus (\text{AES}_{k_1}(m')\oplus k_2) = \text{AES}_{k_1}(m) \oplus \text{AES}_{k_1}(m')$$
il termine $k_2$ **si cancella**. L'avversario fa quindi forza bruta **solo su $k_1$** ($2^{128}$): per ogni candidata verifica se $\text{AES}_{k_1}(m)\oplus\text{AES}_{k_1}(m') = c\oplus c'$. Trovata $k_1$, ricava $k_2 = c \oplus \text{AES}_{k_1}(m)$.

**Conclusione:** la sicurezza KPA di AES-X resta $\approx 2^{128}$ (come AES), **non** $2^{256}$: il whitening in **sola uscita** è trasparente a un KPA con $\ge 2$ coppie. Per ottenere un guadagno reale (come in DESX) serve whitening **anche in ingresso** ($\text{AES}_{k_1}(m \oplus k_0) \oplus k_2$), che impedisce la cancellazione lineare di $k_2$.

> ⚠️ Lezione: aggiungere uno XOR con una chiave **lineare** in un solo punto non aggiunge sicurezza contro avversari che possono combinare più osservazioni. La non-linearità e il posizionamento del whitening (ingresso *e* uscita) sono essenziali.
