# UD2 — Appelli Svolti Anno 2024

> 📌 **Panoramica:** 7 appelli dell'anno accademico 2023/2024 (sessione invernale, straordinaria, estiva e autunnale). Tutti gli appelli seguono la struttura del corso del Prof. S. Cimato, Università degli Studi di Milano.

---

## Tabella riassuntiva degli appelli 2024

| # | Data | D1 (punti) | D2 (punti) | D3 (punti) | D4 (punti) | Difficoltà stimata |
|---|------|-----------|-----------|-----------|-----------|-------------------|
| L1 | 17 gennaio 2024 | Cifratura classica / sostituzione (30) | AES (20) | Funzioni hash (20) | El-Gamal (30) | ⭐⭐⭐ Media |
| L2 | 20 febbraio 2024 | Cifratura Playfair (25) | DES simmetria (20) | Funzioni MAC / HMAC (30) | Firma digitale DSS (25) | ⭐⭐⭐⭐ Alta |
| L3 | 24 giugno 2024 | Sicurezza condizionata/incondizionata + cifrario affine doppio (20) | Feistel 2 round con f aritmetica (30) | RSA: correttezza, fattorizzazione da φ(n), calcolo d (30) | Firma DSS + caso s=0 (20) | ⭐⭐⭐⭐ Alta |
| L4 | 5 luglio 2024 | Sicurezza condizionata/incondizionata + cifrario affine doppio (20) | Feistel 2 round con f XOR (30) | RSA: correttezza, fattorizzazione da φ(n), calcolo d (30) | Secret Sharing (n,n) e (k,n) (20) | ⭐⭐⭐⭐ Alta |
| L5 | 8 luglio 2024 | Cifratura simmetrica a blocchi su 3 bit + cifratura "KOSTI" (35) | Modalità DES (20) | RSA naive, double-RSA (20) | Secret Sharing (3,3) e (2,3) numerico (20) | ⭐⭐⭐⭐⭐ Molto alta |
| L6 | 6 settembre 2024 | Cifrari a permutazione + composizione K1∘K2 (30) | Funzioni MAC / HMAC (25) | RSA: equivalenza fattorizzazione, ottimizzazione esponenziale (20) | Secret Sharing (4,4) e (2,3) numerico (25) | ⭐⭐⭐⭐ Alta |
| L7 | 20 settembre 2024 | Cifrario di Hill: caratteristiche, attacchi, cifratura/decifratura (20) | Feistel 2 round con f aritmetica mod 15 (35) | Funzioni hash: identità, paradosso compleanno (25) | RSA completo: generazione chiavi, cifratura, decifratura, firma (20) | ⭐⭐⭐⭐⭐ Molto alta |

---

## Pattern e argomenti ricorrenti nel 2024

### Argomenti presenti in quasi tutti gli appelli

| Argomento | Frequenza | Appelli |
|-----------|-----------|---------|
| RSA (calcoli numerici: d, decifratura, firma) | 5/7 | L3, L4, L5, L6, L7 |
| Strutture di Feistel (calcolo cifratura 2 round) | 3/7 | L3, L4, L7 |
| Funzioni hash (proprietà, collisioni, paradosso) | 4/7 | L1, L3, L7 + MAC in L2/L6 |
| Secret Sharing (Shamir, schema (n,n) e (k,n)) | 3/7 | L4, L5, L6 |
| El-Gamal | 1/7 | L1 |
| Firma digitale DSS | 2/7 | L2, L3 |
| Cifratura classica (sostituzione, affine, Playfair, Hill) | 5/7 | L1, L2, L3, L4, L7 |
| AES | 1/7 | L1 |
| DES (dimostrazione simmetria o modalità) | 2/7 | L2, L4 |
| MAC / HMAC | 2/7 | L2, L6 |

### Caratteristiche strutturali degli appelli 2024

- **4 domande** per ogni appello, distribuzione punti variabile ma totale sempre 100.
- **D1 (cifratura classica o simmetrica):** quasi sempre presente, vale tra 20 e 35 punti. Richiede sia teoria che calcolo.
- **D2 (simmetrica moderna o Feistel):** DES o strutture di Feistel. Domande dimostrative o calcoli numerici passo-passo.
- **D3 (asimmetrica o hash):** spesso RSA con calcoli espliciti (fattorizzazione da φ(n), Euclide esteso, decifratura).
- **D4 (protocolli avanzati):** El-Gamal, Secret Sharing, DSS, firma RSA. Domande miste teoria+calcolo.

### Consigli per affrontare gli appelli 2024

1. **RSA è onnipresente:** padroneggiare l'algoritmo di Euclide esteso per calcolare $d$, l'esponenziazione modulare rapida, la fattorizzazione da φ(n).
2. **Feistel a 2 round:** esercitarsi con funzioni f non standard (aritmetiche, XOR, miste). Mostrare L0,R0 → L1,R1 → L2,R2 con tutti i calcoli.
3. **Secret Sharing:** per schema (n,n) somma modulare; per (k,n) interpolazione di Lagrange in $\mathbb{Z}_p$. Calcolare esplicitamente gli inversi modulari.
4. **Hash e MAC:** enunciare le 3 proprietà con definizioni formali; per HMAC descrivere la costruzione con ipad/opad.
5. **Cifratura classica:** Hill richiede inversione di matrice mod 26 (determinante + matrice aggiunta). Playfair richiede costruzione della griglia. Affine richiede dimostrazione chiusura della composizione.

---

## Lezioni teoriche di riferimento

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — cifrari a sostituzione, Hill, Playfair, affine
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L2 - Cifrari di Feistel.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L2%20-%20Cifrari%20di%20Feistel.md) — strutture di Feistel
- [`../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD2/L1%20-%20Struttura%20del%20DES.md) — DES
- [`../../M2_Cifrature_Simmetriche_Moderne/UD3/L1 - AES.md`](../../M2_Cifrature_Simmetriche_Moderne/UD3/L1%20-%20AES.md) — AES
- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA
- [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — El-Gamal
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — funzioni hash
- [`../../M4_Funzioni_Hash_e_Mac/UD3/L3 - HMAC.md`](../../M4_Funzioni_Hash_e_Mac/UD3/L3%20-%20HMAC.md) — HMAC
- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20(Digital%20Signature%20Standard).md) — DSS
- [`../../M6_Applicazioni_Crittografiche/UD3/L2 - Schema di Shamir.md`](../../M6_Applicazioni_Crittografiche/UD3/L2%20-%20Schema%20di%20Shamir.md) — Secret Sharing di Shamir
