# Modalita operative del DES — Esercizio d'esame

> 🗂️ **Fonte:** appello del **16 febbraio 2023**, Domanda 3 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 16 febbraio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L2%20-%20Appello%2016%20febbraio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Modalità operative del DES (15 punti)

### Traccia originale

> **(15 punti) Modalità di DES.** Si discutano le modalità di cifratura evidenziandone vantaggi e svantaggi.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalità%20operative%20del%20DES.md) — ECB, CBC, CFB, OFB, CTR

### Soluzione dettagliata

Un cifrario a blocchi come DES cifra blocchi di 64 bit; le **modalità operative** definiscono come concatenare la cifratura di più blocchi.

**ECB (Electronic Codebook):** $c_i = E_k(m_i)$. *Vantaggi:* semplice, parallelizzabile, accesso casuale. *Svantaggi:* deterministica — blocchi in chiaro uguali danno blocchi cifrati uguali, quindi rivela pattern (il classico esempio dell'immagine "pinguino" ancora riconoscibile). Da evitare.

**CBC (Cipher Block Chaining):** $c_i = E_k(m_i \oplus c_{i-1})$, $c_0 = IV$. *Vantaggi:* nasconde i pattern (con IV casuale ogni cifratura è diversa), errore di trasmissione si propaga in modo limitato. *Svantaggi:* cifratura sequenziale (non parallelizzabile), serve un IV imprevedibile; vulnerabile a padding oracle se mal implementata.

**CFB (Cipher Feedback):** trasforma il cifrario a blocchi in cifrario a flusso: $c_i = m_i \oplus E_k(c_{i-1})$. *Vantaggi:* non serve padding, adatto a stream. *Svantaggi:* cifratura sequenziale; un errore si propaga.

**OFB (Output Feedback):** keystream indipendente dal plaintext: $w_i = E_k(w_{i-1})$, $c_i = m_i \oplus w_i$. *Vantaggi:* niente padding, errori non si propagano (errore di bit → solo quel bit). *Svantaggi:* **keystream riutilizzabile** se si riusa l'IV (problema del two-time-pad); sequenziale nella generazione del keystream.

**CTR (Counter):** $c_i = m_i \oplus E_k(\text{nonce} \| i)$. *Vantaggi:* parallelizzabile, accesso casuale, niente padding. *Svantaggi:* catastrofico riusare la coppia (chiave, contatore).

> 📌 Regola pratica: **mai ECB** per dati strutturati; usare modalità con IV casuale (CBC) o a flusso (CTR), e **non riutilizzare mai IV/contatore/keystream** con la stessa chiave.
