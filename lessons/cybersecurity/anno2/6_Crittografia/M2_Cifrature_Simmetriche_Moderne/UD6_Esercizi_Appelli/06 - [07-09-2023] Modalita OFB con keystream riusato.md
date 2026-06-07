# Modalita OFB con keystream riusato — Esercizio d'esame

> 🗂️ **Fonte:** appello del **7 settembre 2023**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 7 settembre 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L6%20-%20Appello%207%20settembre%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Cifratura simmetrica: attacco a una modalità OFB con keystream riusato (30 punti)

### Traccia originale

> **(30 punti) Cifratura simmetrica.** Codice a blocchi $E$ usato in modalità $c_t = m_t \oplus w_t$, con $w_0 = IV$ e $w_t = E_K(w_{t-1})$. I cifrati sono archiviati. Il blocco $c_i$ viene **eliminato** e i blocchi $c_{i+1},\ldots,c_n$ vengono decifrati, **ri-cifrati con le stesse variabili** e riarchiviati, ottenendo $c'_i = m_{i+1}\oplus w_i,\; c'_{i+1} = m_{i+2}\oplus w_{i+1},\;\ldots,\; c'_{n-1} = m_n \oplus w_{n-1}$. Dimostrare che se $m_i$ è noto, oltre al file originale e a quello aggiornato, allora per ogni $t\ge i$ si deducono tutte le $w_t$ e tutti gli $m_t$. Suggerire infine quando usare o non usare questa modalità.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalità%20operative%20del%20DES.md) — modalità OFB e riuso del keystream

### Soluzione dettagliata

La modalità descritta è **OFB (Output Feedback)**: il keystream $w_t = E_K(w_{t-1})$ è **indipendente dal plaintext** e $c_t = m_t \oplus w_t$. Il punto chiave è che la ri-archiviazione **riusa lo stesso keystream** $w_t$ (stesso IV/chiave): ogni $w_t$ maschera così **due** plaintext diversi (problema del **two-time-pad**).

**Dati noti all'avversario:** $m_i$, il file originale $\{c_t = m_t \oplus w_t\}_{t}$, il file aggiornato $\{c'_t = m_{t+1} \oplus w_t\}_{t\ge i}$.

**Dimostrazione per induzione su $t \ge i$.**

*Base* ($t = i$): da $c_i = m_i \oplus w_i$ con $m_i$ e $c_i$ noti,
$$w_i = c_i \oplus m_i.$$

*Passo induttivo*: supponiamo nota $w_t$ (con $t \ge i$). Allora:
1. dal file **aggiornato**, $c'_t = m_{t+1} \oplus w_t \Rightarrow m_{t+1} = c'_t \oplus w_t$ (recupero il plaintext successivo);
2. dal file **originale**, $c_{t+1} = m_{t+1} \oplus w_{t+1} \Rightarrow w_{t+1} = c_{t+1} \oplus m_{t+1}$ (recupero la keystream successiva).

Quindi da $w_i$ si ricavano in cascata $m_{i+1}, w_{i+1}, m_{i+2}, w_{i+2}, \ldots$: **tutte** le $w_t$ e **tutti** gli $m_t$ per $t \ge i$ (oltre a $m_i$ già noto). $\blacksquare$

> 📌 Equivalentemente, "allineando" i due file: $c_t \oplus c'_t = (m_t \oplus w_t)\oplus(m_{t+1}\oplus w_t) = m_t \oplus m_{t+1}$. Il keystream si cancella e resta lo XOR di plaintext adiacenti: noto **un** plaintext ($m_i$), tutta la catena si srotola. È esattamente la rottura del two-time-pad.

**Indicazioni d'uso.** Questa modalità (OFB/stream) è sicura **solo** se ogni cifratura usa una **coppia (chiave, IV) unica e mai riutilizzata**: il keystream non va mai riusato per cifrare dati diversi. Quindi:

- **Non usarla** quando i dati vengono **modificati/ri-archiviati riusando lo stesso IV/keystream** (come nello scenario): qualsiasi edit con keystream riusato è catastrofico.
- **Usarla** solo per cifrare un flusso *una volta sola* con IV fresco; per ogni nuova cifratura (anche di una versione modificata) generare un **nuovo IV**. In alternativa usare modalità progettate per il riuso controllato (es. CTR con nonce unico) o, se serve integrità/editabilità, schemi autenticati con nonce gestiti correttamente.
