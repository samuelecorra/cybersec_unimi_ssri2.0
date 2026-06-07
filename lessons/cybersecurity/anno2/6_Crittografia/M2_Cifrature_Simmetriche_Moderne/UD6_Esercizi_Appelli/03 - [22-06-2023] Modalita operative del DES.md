# Modalita operative del DES — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 giugno 2023**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 giugno 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L3%20-%20Appello%2022%20giugno%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Modalità operative del DES (20 punti)

### Traccia originale

> **(20 punti) DES.** Discutere le modalità di cifratura di DES, evidenziandone vantaggi e svantaggi.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalità%20operative%20del%20DES.md) — modalità operative

### Soluzione dettagliata

Le **modalità operative** definiscono come usare il cifrario a blocchi DES (blocco 64 bit) su messaggi di lunghezza arbitraria.

- **ECB:** $c_i = E_k(m_i)$. *Pro:* semplice, parallelizzabile, accesso casuale. *Contro:* deterministica, rivela pattern (blocchi uguali → cifrati uguali). **Da evitare.**
- **CBC:** $c_i = E_k(m_i \oplus c_{i-1})$, $c_0 = IV$. *Pro:* nasconde i pattern con IV casuale. *Contro:* sequenziale; serve un IV imprevedibile.
- **CFB:** $c_i = m_i \oplus E_k(c_{i-1})$. *Pro:* a flusso, niente padding. *Contro:* sequenziale; propagazione errore.
- **OFB:** $w_i = E_k(w_{i-1})$, $c_i = m_i \oplus w_i$. *Pro:* a flusso, errori di bit non si propagano. *Contro:* keystream riusabile se si riusa l'IV (two-time-pad).
- **CTR:** $c_i = m_i \oplus E_k(\text{nonce}\,\|\,i)$. *Pro:* parallelizzabile, accesso casuale. *Contro:* mai riusare (chiave, contatore).

> 📌 Linea guida: mai ECB per dati strutturati; usare CBC/CTR con IV/nonce casuale e mai ripetuto sotto la stessa chiave.
