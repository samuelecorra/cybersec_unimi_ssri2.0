# Sicurezza e cifrario affine composto — Esercizio d'esame

> 🗂️ **Fonte:** appello del **5 luglio 2024**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 5 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L4%20-%20Appello%205%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura classica: sicurezza e cifrario affine composto (20 punti)

### Traccia originale

> **(20 punti) Cifratura classica.**
>
> a. (5) Dare una definizione di sicurezza condizionata e incondizionata.
>
> b. (5) Descrivere un cifrario incondizionatamente sicuro.
>
> c. (10) Si consideri per un cifrario affine, l'applicazione in sequenza della cifratura con chiave $k_1 = (a_1, b_1)$ e $k_2 = (a_2, b_2)$, $E_{k_1}(E_{k_2}(\text{plaintext})) = \text{ciphertext}$. Tale doppia cifratura aumenta la robustezza del cifrario? (giustificare la risposta)

### Soluzione dettagliata

> 📌 Le parti a e b di questa domanda sono identiche all'Appello del 24 giugno 2024 (L3, Domanda 1). Si rimanda a quella soluzione completa. Qui viene riportata la soluzione per completezza.

#### Parte a — Sicurezza condizionata e incondizionata (5 punti)

**Sicurezza condizionata (computazionale):**

Un sistema è **condizionatamente sicuro** se è computazionalmente infattibile per un avversario con risorse di calcolo polinomiali rompere il sistema. La sicurezza si basa sulla difficoltà di problemi matematici (fattorizzazione, logaritmo discreto). Un avversario con risorse illimitate potrebbe teoricamente rompere il sistema.

**Sicurezza incondizionata (perfetta, information-theoretic):**

Un sistema è **incondizionatamente sicuro** se, anche con risorse computazionali illimitate, un avversario non può ricavare alcuna informazione sul testo in chiaro dal testo cifrato. Formalmente (Shannon, 1949):

$$P(\text{plaintext} \mid \text{ciphertext}) = P(\text{plaintext})$$

Il teorema di Shannon afferma che la perfect secrecy richiede $|\mathcal{K}| \geq |\mathcal{M}|$.

#### Parte b — Cifrario incondizionatamente sicuro (5 punti)

Il **One-Time Pad (OTP)** è l'esempio canonico di cifrario incondizionatamente sicuro:
- Messaggio $m \in \{0,1\}^n$; chiave $k \in \{0,1\}^n$ uniforme, usata una volta sola.
- Cifratura: $c = m \oplus k$; decifratura: $m = c \oplus k$.
- Perfect secrecy: per ogni $c$, $m_1$, $m_2$ si ha $P(C=c|M=m_1) = P(C=c|M=m_2) = 2^{-n}$.
- Limite pratico: chiave lunga quanto il messaggio, non riusabile.

#### Parte c — Doppia cifratura affine (10 punti)

La doppia cifratura affine $E_{k_1}(E_{k_2}(x))$ si calcola come:

$$E_{k_2}(x) = a_2 x + b_2 \bmod 26$$
$$E_{k_1}(E_{k_2}(x)) = a_1(a_2 x + b_2) + b_1 = (a_1 a_2)x + (a_1 b_2 + b_1) \bmod 26$$

Il risultato è ancora un cifrario affine con chiave equivalente $(a_1 a_2 \bmod 26,\; a_1 b_2 + b_1 \bmod 26)$.

**La doppia cifratura NON aumenta la robustezza** perché:
1. La famiglia dei cifrari affini è **chiusa** rispetto alla composizione: la composizione di due cifrari affini è ancora un cifrario affine.
2. Qualsiasi attacco al cifrario affine singolo si applica al doppio cifrario, poiché sono crittograficamente equivalenti.
3. Lo spazio delle chiavi effettivo non cresce: rimane al più il numero di chiavi affini valide (208 per l'alfabeto da 26 lettere).
