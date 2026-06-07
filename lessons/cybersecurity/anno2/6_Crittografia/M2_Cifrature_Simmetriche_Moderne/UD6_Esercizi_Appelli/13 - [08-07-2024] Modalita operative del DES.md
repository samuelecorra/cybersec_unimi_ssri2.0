# Modalita operative del DES — Esercizio d'esame

> 🗂️ **Fonte:** appello del **8 luglio 2024**, Domanda 2 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 8 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L5%20-%20Appello%208%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 2 — Modalità operative del DES (20 punti)

### Traccia originale

> **(20 punti) Cifratura simmetrica.**
>
> a. (10) Si descrivano le modalità di DES delineando vantaggi e svantaggi.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L2%20-%20Modalità%20operative%20del%20DES.md) — ECB, CBC, CFB, OFB, CTR

### Soluzione dettagliata

#### Le modalità operative dei cifrari a blocchi

I cifrari a blocchi (DES, AES) cifrano blocchi di dimensione fissa (64 bit per DES). Per messaggi più lunghi, si definiscono le **modalità operative** che specificano come applicare il cifrario a blocchi ripetutamente.

**1. ECB — Electronic Codebook**

**Schema:** ogni blocco $P_i$ viene cifrato indipendentemente con la stessa chiave $K$:
$$C_i = E_K(P_i)$$

**Vantaggi:**
- Semplicità di implementazione.
- Parallelizzabile (cifratura e decifratura).
- Errori in un blocco non si propagano agli altri.

**Svantaggi:**
- **Deterministico:** lo stesso blocco di plaintext produce sempre lo stesso blocco di ciphertext. Un avversario può rilevare pattern ripetuti nel messaggio.
- **Non semanticamente sicuro:** non è sicuro contro attacchi CPA (Chosen Plaintext Attack).
- **Vulnerabile alla sostituzione di blocchi:** un avversario può riordinare, duplicare o sostituire blocchi senza che il destinatario se ne accorga.

> ⚠️ ECB è la modalità meno sicura. Non dovrebbe essere usata in pratica. Il classico esempio è la cifratura ECB di un'immagine bitmap, dove i pattern visivi rimangono riconoscibili nel ciphertext.

**2. CBC — Cipher Block Chaining**

**Schema:** ogni blocco di plaintext viene XORato con il blocco di ciphertext precedente prima di essere cifrato:
$$C_i = E_K(P_i \oplus C_{i-1}) \quad \text{con } C_0 = IV \text{ (vettore di inizializzazione)}$$

**Decifratura:** $P_i = D_K(C_i) \oplus C_{i-1}$

**Vantaggi:**
- **Semanticamente sicuro** (con IV casuale): blocchi identici nel plaintext producono ciphertext diversi.
- **Self-synchronizing:** errori in un blocco di ciphertext influenzano solo due blocchi del plaintext decifrato.

**Svantaggi:**
- **Non parallelizzabile in cifratura** (ogni blocco dipende dal precedente).
- **Parallelizzabile in decifratura** (si può calcolare $D_K(C_i)$ in parallelo e poi XORare con $C_{i-1}$).
- Un errore nel blocco $C_i$ corrode $P_i$ e $P_{i+1}$.
- Richiede IV casuale e non riutilizzabile.

**3. CFB — Cipher Feedback**

**Schema:** converte il cifrario a blocchi in uno stream cipher. Usa il cifrario per generare un keystream:
$$C_i = P_i \oplus E_K(C_{i-1}) \quad \text{con } C_0 = IV$$

**Decifratura:** $P_i = C_i \oplus E_K(C_{i-1})$

**Vantaggi:**
- Cifra dati di lunghezza arbitraria (non richiede padding).
- Autosincronizzante: un errore si propaga a un numero limitato di blocchi.

**Svantaggi:**
- Non parallelizzabile in cifratura.
- Errori nel ciphertext si propagano per alcuni blocchi.

**4. OFB — Output Feedback**

**Schema:** genera il keystream in anticipo, indipendentemente dal plaintext:
$$O_i = E_K(O_{i-1}), \quad C_i = P_i \oplus O_i \quad \text{con } O_0 = IV$$

**Vantaggi:**
- Errori nel ciphertext non si propagano al plaintext (solo il bit/blocco corrispondente è compromesso).
- Il keystream può essere precalcolato.

**Svantaggi:**
- **Non autosincronizzante:** se un bit del ciphertext viene perso, la sincronizzazione del keystream si rompe.
- Il keystream dipende solo dalla chiave e dall'IV, non dal plaintext.

**5. CTR — Counter Mode**

**Schema:** usa un contatore che viene cifrato per generare il keystream:
$$C_i = P_i \oplus E_K(\text{counter}_i)$$

dove $\text{counter}_i = \text{IV} + i$ (o analoga funzione del contatore).

**Vantaggi:**
- **Completamente parallelizzabile** sia in cifratura che in decifratura.
- Accesso casuale ai blocchi senza cifrare quelli precedenti.
- Precalcolo del keystream possibile.
- Errori non si propagano.

**Svantaggi:**
- Il contatore non deve mai essere riusato con la stessa chiave (altrimenti si riusa il keystream → vulnerabilità come nel one-time pad).

> 📌 **Riepilogo:**

| Modalità | Parallelizzabile enc | Parallelizzabile dec | Propagazione errori | Padding richiesto |
|----------|---------------------|---------------------|--------------------|--------------------|
| ECB | Sì | Sì | No (locale) | Sì |
| CBC | No | Sì | 2 blocchi | Sì |
| CFB | No | Sì (parziale) | Pochi blocchi | No |
| OFB | No (ma precalcolo sì) | Come enc | No | No |
| CTR | Sì | Sì | No | No |
