# REPORT DI COPERTURA: Esami di Crittografia 2023–2025 vs Materiale Didattico

**Data di redazione:** 29 maggio 2026  
**Oggetto:** Analisi pignola e dettagliata della corrispondenza tra i contenuti dei 6 moduli di Crittografia e le domande degli appelli 2023–2025 (21 esami analizzati).  
**Domanda di fondo:** Studiando esclusivamente il materiale dei 6 moduli, si può prendere il massimo dei voti?

---

## Premessa metodologica

Sono stati analizzati **21 esami**: 8 appelli del 2023, 7 del 2024 e 6 del 2025. Per ciascun esame sono state estratte tutte le domande con il relativo punteggio. Il materiale didattico comprende circa **95 file .md** distribuiti su 6 moduli (M1–M6). Il punteggio totale di ogni esame è **100 punti**, suddiviso in 4 domande principali, spesso con più sottopunti.

---

## SEZIONE 1 – Mappa degli argomenti richiesti agli esami (2023–2025)

La tabella seguente elenca tutti gli argomenti che appaiono negli esami, ordinati per frequenza decrescente di apparizione negli appelli (su 21 totali).

| Argomento                                                                 | Freq. (su 21) | Punteggio tipico | Tipo           |
| ------------------------------------------------------------------------- | ------------- | ---------------- | -------------- |
| **Secret Sharing – schema (k,n) di Shamir: teoria + calcolo numerico**    | 17            | 20–30            | Entrambi       |
| **DSS/DSA – generazione e verifica firma**                                | 11            | 10–15            | Teoria+calcolo |
| **El-Gamal – cifratura/decifratura con numeri**                           | 9             | 15–30            | Entrambi       |
| **Cifratura simmetrica: struttura di Feistel + calcolo su rete Feistel**  | 9             | 20–35            | Entrambi       |
| **DES – modalità operative (ECB, CBC, CFB, OFB, CTR)**                    | 8             | 15–20            | Teoria         |
| **RSA – correttezza, parametri, calcolo (cifratura/decifratura)**         | 8             | 10–30            | Entrambi       |
| **Funzioni MAC – caratteristiche, applicazioni, HMAC**                    | 8             | 15–30            | Teoria         |
| **Vigenère – IC, MIC, crittoanalisi**                                     | 5             | 25               | Entrambi       |
| **Cifrario di Hill – cifratura/decifratura/chiave da known-plaintext**    | 5             | 20–25            | Entrambi       |
| **Cifrario Playfair – cifratura/decifratura con parola chiave**           | 5             | 25               | Entrambi       |
| **Tipi di attacco (KCA, KPA, CPA, CCA) + classificazione cifrari**        | 5             | 25               | Teoria         |
| **Diffie-Hellman – calcolo chiavi + attacco man-in-the-middle**           | 5             | 20–25            | Entrambi       |
| **Funzioni hash – proprietà (one-way, weak/strong collision resistance)** | 5             | 20–25            | Teoria         |
| **Sicurezza condizionata vs incondizionata + OTP**                        | 5             | 10               | Teoria         |
| **DES doppio e attacco meet-in-the-middle**                               | 4             | 10–20            | Entrambi       |
| **AES – caratteristiche, trasformazioni, struttura**                      | 4             | 20               | Teoria         |
| **Cifrario affine – calcolo + doppia cifratura affine**                   | 4             | 15–25            | Entrambi       |
| **DSA – sicurezza (firma (a,0), riuso k, differenza con RSA)**            | 4             | 5–10             | Teoria+Analisi |
| **Funzioni hash – paradosso del compleanno + attacco**                    | 4             | 10–15            | Teoria         |
| **Certificati digitali + PKI + X.509**                                    | 3             | 15–25            | Teoria         |
| **RSA – fattorizzazione tramite phi(n)**                                  | 3             | 10–15            | Calcolo        |
| **RSA – sicurezza (naive RSA, double-RSA, low exponent)**                 | 3             | 10               | Analisi        |
| **Crittoanalisi di cifrari a blocchi XOR+hash personalizzati**            | 3             | 15–35            | Entrambi       |
| **Curve ellittiche – cenni/applicazioni**                                 | 3             | 5                | Teoria         |
| **Blowfish – caratteristiche**                                            | 1             | 15               | Teoria         |
| **Cifrari a permutazione (trasposizione)**                                | 1             | 30               | Entrambi       |
| **AES-X (variante con XOR esterno) + sicurezza known-plaintext**          | 2             | 30               | Analisi        |
| **RC4 / RC5**                                                             | 0             | —                | —              |

---

## SEZIONE 2 – Analisi per modulo

### M1 – Crittografia Classica

**Argomenti trattati nelle lezioni:**
- Crittografia e steganografia; protocolli e scenari; primitive crittografiche; proprietà di sicurezza
- Tipi di attacco (KCA, KPA, CPA, CCA, CTA); sicurezza condizionata vs incondizionata; forza bruta; principio di Kerckhoffs
- Cifrari a sostituzione monoalfabetica (Cesare, shift, frase-chiave); cifrario affine
- Cifrari a trasposizione (Rail Fence, colonne); sicurezza dei cifrari; crittoanalisi monoalfabetica
- Cifrario di Playfair (regole, tabella 5×5, sicurezza)
- Cifrario di Hill (moltiplicazione matriciale, invertibilità, attacco known-plaintext)
- Cifrario di Vigenère (quadrato, IC, MIC, test di Kasiski, crittoanalisi completa)
- Enigma (cenni)

**Argomenti M1 richiesti agli esami:**

| Domanda d'esame specifica | Appello | Punti | Copertura |
|---|---|---|---|
| Classificazione attacchi (KCA, KPA, CPA, CCA) + resistenza cifrari | 230124, 230707, 250919 | 25 | Coperto bene |
| Vigenère: IC e MIC, calcolo su parole date | 230216, 250210 | 25 | Coperto bene |
| Hill: cifratura/decifratura/determinazione chiave da KPT | 230712, 230922, 240920, 250704 | 20–25 | Coperto bene |
| Playfair: cifratura/decifratura con parola chiave data | 230907, 230908, 240220 | 25 | Coperto bene |
| Affine: trovare la chiave da coppie plain/ciphertext; doppia cifratura affine | 230622, 240624, 240705, 250905 | 10–25 | Coperto bene |
| Sicurezza condizionata vs incondizionata + OTP | 230622, 240624, 240705, 250120 | 5–10 | Coperto bene |
| Cifrari a permutazione: composizione di permutation cipher | 240906 | 30 | Parzialmente coperto |
| Cifrari a sostituzione binaria (parole di n bit, tabella, XOR-key) | 240117, 240708, 250715 | 30 | Parzialmente coperto |

**Valutazione copertura M1:** ✅ **Coperto bene** per la grande maggioranza. ⚠️ **Coperto parzialmente** per due tipologie particolari descritte sotto.

**Gap specifici M1:**

1. **Cifrari a permutazione e loro composizione** (appello 240906, 15 punti della domanda 1c): la lezione L2 di UD2 tratta i cifrari a trasposizione per colonne e Rail Fence, ma non approfondisce la composizione formale di due permutation cipher Pk1∘Pk2 su blocchi di 5 lettere né l'analisi del cifrario risultante. Il materiale copre il concetto ma non il calcolo pratico dettagliato richiesto.

2. **Cifrari a sostituzione su parole binarie di n bit** (appelli 240117, 240708, 250715, 30 punti): l'esame chiede di ragionare su cifrari a sostituzione dove i simboli sono stringhe di n bit, calcolare le dimensioni della tabella di verità (n·2^n bit), calcolare il numero di cifrari possibili ((2^n)!), e analizzare varianti XOR-key. Il materiale di M1 non tratta esplicitamente questa astrazione su parole binarie. Si tratta di una generalizzazione teorica che richiede un inquadramento che le lezioni non forniscono direttamente.

3. **Calcolo di IC su parole italiane specifiche** (es. "gassoso", "pattinatore"): la formula è ben spiegata nelle lezioni (L5_1 di UD3 M1), ma non ci sono molti esempi svolti per parole brevi. L'esame richiede che lo studente sappia eseguire il calcolo manualmente in modo rapido e corretto, contando le frequenze lettera per lettera.

---

### M2 – Cifrature Simmetriche Moderne

**Argomenti trattati nelle lezioni:**
- Cifrari a blocchi: introduzione, struttura generale, S-box, P-box
- Struttura di Feistel: principi di Shannon (confusione/diffusione), cifratura e decifratura
- DES: struttura completa (IP, 16 round, f, S-box, schedulazione chiavi, IP⁻¹), decifratura
- Modalità operative DES: ECB, CBC, CFB, OFB, CTR (vantaggi, svantaggi, propagazione errori)
- Attacchi al DES: crittoanalisi differenziale e lineare (cenni)
- Double DES: struttura, attacco meet-in-the-middle, complessità O(2^57)
- Triple DES (3DES): EEE, EDE, sicurezza
- AES: storia, criteri di selezione, struttura SP-network, SubBytes, ShiftRows, MixColumns, AddRoundKey, decifratura
- Blowfish: struttura Feistel 16 round, espansione chiave basata su π, funzione F, sicurezza
- RC5: struttura, word rotation
- RC4: cifrario a flusso, keystream

**Argomenti M2 richiesti agli esami:**

| Domanda d'esame specifica | Appello | Punti | Copertura |
|---|---|---|---|
| Modalità operative DES (vantaggi/svantaggi) | 230216, 230622, 240708 | 15–20 | Coperto bene |
| Struttura Feistel (in DES) + calcolo numerico su rete Feistel toy | 230712, 240624, 240705, 240920 | 20–35 | Coperto bene |
| Cifrario a blocchi con hash+XOR personalizzato (decifratura + sicurezza CPA) | 230216, 230707, 250210 | 35 | Coperto parzialmente |
| Attacco meet-in-the-middle su Double DES | 230922, 250905, 250919 | 10 | Coperto bene |
| AES: caratteristiche + operazioni di cifratura/decifratura | 240117 | 20 | Coperto bene |
| DES: prova che decifratura = cifratura con chiavi inverse | 240220, 250120 | 20 | Coperto bene |
| CTR/OFB-like (modalità con wt = EK(wt-1)) + analisi sicurezza | 230907, 230908 | 30 | Coperto parzialmente |
| AES-X = AESk1(m) ⊕ k2: sicurezza known-plaintext vs AES standard | 230922, 250919 | 30 | Non coperto |
| Blowfish: caratteristiche principali | 250704 | 15 | Coperto bene |
| Cifrario a sostituzione su blocchi con mapping arbitrario | 240708 | 35 | Coperto parzialmente |

**Valutazione copertura M2:** ✅ **Coperto bene** per Feistel, DES (struttura, modalità, decifratura), meet-in-the-middle, AES, Blowfish. ⚠️ **Coperto parzialmente** per l'analisi di cifrari non-standard. ❌ **Non coperto** per AES-X.

**Gap specifici M2:**

1. **AES-X = AESk1(m) ⊕ k2** (appelli 230922 e 250919, 20 punti ciascuno): questa variante specifica di AES con XOR esterno di una seconda chiave non è trattata nelle lezioni. L'esame chiede di descriverne le caratteristiche e discuterne la sicurezza rispetto ad AES standard sotto known-plaintext attack. La risposta richiede di notare che, data una coppia (m, c=AESk1(m)⊕k2) nota, si può calcolare k2 = c ⊕ AESk1(m) con una singola cifratura, riducendo la sicurezza reale a quella di AES standard. Le lezioni trattano AES e XOR separatamente, ma non questa costruzione composita.

2. **Modalità CTR/OFB in variante personalizzata con dimostrazione formale** (appelli 230907/230908, 30 punti): la domanda richiede una dimostrazione formale passo-passo che, noto mi e i file di ciphertext originale e aggiornato, si possono dedurre tutti i valori wt e mt per t≥i. Il materiale copre OFB (sezione 5 di L2 M2 UD2) e spiega la struttura del keystream wt = EK(wt-1), ma non svolge questa dimostrazione deduttiva specifica. La dimostrazione richiede: wi = ci ⊕ mi (dal blocco originale), wi+1 = EK(wi), mt = c't ⊕ wt.

3. **Cifrari con hash+XOR (decifratura e CPA)** (appelli 230216, 230707, 250210, 35 punti): sono cifrari toy dove c1 = m1⊕h(m2)⊕k1 e c2 = m2⊕c1⊕k2 (o varianti). Il materiale fornisce i concetti base (Feistel, XOR, hash), ma non esempi di analisi di questi schemi compositi. Lo studente deve saper derivare la decifratura simbolicamente e costruire un chosen-plaintext attack scegliendo opportunamente m1=0 per isolare h(m2).

---

### M3 – Cifrature Asimmetriche

**Argomenti trattati nelle lezioni:**
- Scenario e applicazioni della crittografia asimmetrica
- RSA: generazione chiavi (p, q, n, φ(n), e, d), cifratura, decifratura, correttezza
- Aritmetica modulare e numeri primi: inverso modulare, algoritmo di Euclide esteso
- Fattorizzazione e test di primalità (Miller-Rabin, Fermat)
- Computazioni in RSA: metodi left-to-right e right-to-left, scelta di e, performance
- Sicurezza RSA: fattorizzazione di n dato φ(n), equivalenza fattorizzazione/calcolo d, CCA, common modulus attack, low exponent attack, attacchi fisici
- Logaritmo discreto: definizione, relazione con DH
- El-Gamal: generazione chiavi, cifratura, decifratura, sicurezza
- Curve ellittiche: addizione punti, moltiplicazione scalare, ECDLP
- ECC: crittosistema di El-Gamal su curve ellittiche

**Argomenti M3 richiesti agli esami:**

| Domanda d'esame specifica                              | Appello                        | Punti | Copertura            |
| ------------------------------------------------------ | ------------------------------ | ----- | -------------------- |
| RSA correttezza (dimostrazione)                        | 230622, 240624, 240705         | 10    | Coperto bene         |
| RSA: fattorizzare n dato φ(n)                          | 230622, 240624, 240705         | 10–15 | Coperto bene         |
| RSA: calcolare d, cifrare/decifrare con numeri piccoli | 240624, 240705, 240920         | 10    | Coperto bene         |
| RSA: generazione parametri                             | 230712, 240708                 | 5–15  | Coperto bene         |
| RSA: naive RSA + double-RSA (sicurezza)                | 240708, 250715                 | 10    | Coperto bene         |
| RSA: equivalenza problema RSA / fattorizzazione        | 230922, 240906                 | 10    | Coperto bene         |
| RSA: ottimizzare calcolo esponenziale                  | 230922, 240906                 | 10    | Coperto bene         |
| El-Gamal: cifratura/decifratura con parametri numerici | 230216, 230707, 240117, 250120 | 15–30 | Coperto bene         |
| El-Gamal: cenni a ECC                                  | 240117, 250120                 | 5     | Coperto parzialmente |
| Curve ellittiche: cenni/applicazioni                   | 230712                         | 5     | Coperto bene         |
| RSA firma: parametri p=5, q=7, cifratura e firma RSA   | 240920                         | 20    | Coperto bene         |

**Valutazione copertura M3:** ✅ **Coperto bene** nella quasi totalità. ⚠️ **Coperto parzialmente** per i cenni a El-Gamal su curve ellittiche.

**Gap specifici M3:**

1. **El-Gamal su curve ellittiche** (appelli 240117, 250120, 5 punti): la L2 di UD4 M3 tratta i crittosistemi ECC. Poiché questi appelli richiedono solo "cenni", il materiale è probabilmente sufficiente, ma va verificato che la lezione L2 di UD4 M3 sia stata letta completamente.

2. **Double-RSA C(M) = ((M)^e1)^e2 – analisi sicurezza** (appelli 240708, 250715, 10 punti): questo schema specifico non è esplicitamente trattato nelle lezioni. Tuttavia, la risposta (C(M) = M^(e1·e2) che equivale a RSA con esponente composito; la sicurezza non aumenta se gcd(e1·e2, φ(n))=1) è deducibile dalle conoscenze RSA. La lezione sulla sicurezza RSA (L5 UD2 M3) fornisce i mattoni necessari ma non questo esempio specifico.

---

### M4 – Funzioni Hash e MAC

**Argomenti trattati nelle lezioni:**
- Scenario e applicazioni delle funzioni hash
- Proprietà: one-wayness, second pre-image resistance, collision resistance; relazioni tra le proprietà (collision-resistant ⟹ second pre-image-resistant ⟹ one-way)
- Sicurezza e attacchi: paradosso del compleanno, attacco del compleanno, implicazioni sulla lunghezza del digest
- Costruzione di funzioni hash (struttura Merkle-Damgård implicita), MD4, MD5, SHA-1
- Notaio digitale
- Codici MAC: caratteristiche, proprietà, sicurezza, attacchi (forgery)
- Costruzione di MAC (CBC-MAC)
- HMAC: formula H((K'⊕opad)||H((K'⊕ipad)||M)), ipad/opad, normalizzazione chiave, sicurezza, costruzione con SHA-1 e MD5

**Argomenti M4 richiesti agli esami:**

| Domanda d'esame specifica | Appello | Punti | Copertura |
|---|---|---|---|
| MAC: caratteristiche e applicazioni | 230124, 230907, 230908, 240220, 240906 | 10–15 | Coperto bene |
| HMAC: descrizione approccio + costruzione con SHA-1 | 230907, 230908, 240220, 240906 | 15–20 | Coperto bene |
| MAC personalizzato (AESk(m) + hash): parametri + attacco | 230124, 250905 | 20 | Coperto parzialmente |
| Funzioni hash: proprietà di sicurezza | 240117, 240920, 250120, 250715 | 10 | Coperto bene |
| Funzioni hash: dimostrare che H(x)=ax+b mod n non è second pre-image resistant | 240117, 250120, 250715 | 10 | Coperto bene |
| Paradosso del compleanno + implicazioni per hash | 240920, 250704 | 10–15 | Coperto bene |
| Hash identità I:{0,1}^n → {0,1}^n: analisi proprietà | 240920, 250210 | 15 | Coperto bene |
| H(x) = DES_k(x): one-wayness per k variabile/fisso/noto/ignoto | 250704 | 15 | Coperto parzialmente |

**Valutazione copertura M4:** ✅ **Coperto bene** per la grande maggioranza. ⚠️ **Coperto parzialmente** per due casi specifici.

**Gap specifici M4:**

1. **H(x) = DES_k(x) come funzione hash** (appello 250704, 15 punti): l'appello chiede di analizzare la one-wayness di questa funzione nei tre casi: k variabile, k fisso e noto, k fisso e ignoto. Il materiale tratta le proprietà hash in generale (L2 UD1 M4) e la struttura DES (L1 UD2 M2), ma non questa analisi composita specifica. La risposta richiede ragionamenti come: (i) k variabile → non è one-way perché basta decifrare con qualsiasi k; (ii) k fisso e noto → DES è invertibile, H non è one-way; (iii) k fisso e ignoto → probabilmente one-way perché rompere DES richiede trovare k.

2. **MAC personalizzato con AES+hash: attacco specifico** (appelli 230124, 250905, 15–20 punti): il materiale copre HMAC e i principi di sicurezza MAC. La costruzione proposta (MACk(m) = AESk(m) se |m|=n, AESk(h(m)) se |m|>n) richiede di trovare un attacco sfruttando due messaggi m e h(m) come input. La dimostrazione non è guidata nelle lezioni: se h(m) ha lunghezza n, allora MACk(h(m)) = AESk(h(m)) = MACk(m), il che costituisce una collisione che rompe la sicurezza del MAC. Questo è deducibile ma non esplicitato.

---

### M5 – Firme Digitali

**Argomenti trattati nelle lezioni:**
- Scenario e applicazioni delle firme digitali (autenticità, integrità, non ripudio)
- Schema RSA per firme: chiavi, firma F = M^d mod n, verifica M = F^e mod n, firma con hash, messaggi lunghi
- Sicurezza firma RSA: attacchi
- Schema DSS/DSA: parametri (p, q, α, s, β), firma (γ, δ), verifica, correttezza della verifica
- Parametri DSS: scelta di p, q, generatore α di ordine q
- Sicurezza DSS: total break, selective forgery, existential forgery; prestazioni vs RSA

**Argomenti M5 richiesti agli esami:**

| Domanda d'esame specifica | Appello | Punti | Copertura |
|---|---|---|---|
| DSS: generazione e verifica della firma | 230124, 230622, 230907, 230908, 240220, 240624, 250919 | 10–15 | Coperto bene |
| DSS: cosa succede se stesso messaggio firmato 2 volte vs RSA | 230124, 230622, 250919 | 5 | Coperto bene |
| DSS: firma (a, 0) rompibilità | 230622, 240624 | 10 | Coperto parzialmente |
| DSS: parametri dell'algoritmo | 240220 | 5 | Coperto bene |
| DSS: δ=0 → bisogna rigenerare k? | 240220 | 10 | Coperto parzialmente |
| RSA firma: p=5, q=7, cifratura e firma | 240920 | 20 | Coperto bene |

**Valutazione copertura M5:** ✅ **Coperto bene** per la maggior parte. ⚠️ **Coperto parzialmente** per i casi speciali δ=0 e γ=0.

**Gap specifici M5:**

1. **DSS firma (a, 0): dimostrazione della rottura** (appelli 230622 domanda 4c, 240624 domanda 4b, 10 punti): la lezione L3 di UD3 M5 tratta la sicurezza del DSS ma non presenta esplicitamente il caso δ=0. La dimostrazione richiede: se δ=0, dalla formula δ = (SHA(M) + sγ)r⁻¹ mod q segue SHA(M) = −sγ mod q, da cui s = −SHA(M)·γ⁻¹ mod q, ovvero la chiave privata è direttamente recuperabile. Questo ragionamento è deducibile dalle formule presenti nelle lezioni ma non viene svolto come esempio.

2. **DSA δ=0: bisogna rigenerare k?** (appello 240220, 10 punti): il materiale non tratta esplicitamente questo caso. La risposta è sì, perché se δ=0 la firma è matematicamente inutilizzabile (la verifica richiede di calcolare δ⁻¹ mod q, che non esiste se δ=0). Deducibile dal materiale con ragionamento.

---

### M6 – Applicazioni Crittografiche

**Argomenti trattati nelle lezioni:**
- Diffie-Hellman: protocollo, calcolo chiavi, sicurezza (DLP), attacco man-in-the-middle
- Scelta dei parametri DH: generatori, radici primitive
- Puzzle di Merkle
- Distribuzione di chiavi pubbliche
- Certificati digitali: creazione, scambio, revoca, CRL
- Standard X.509
- Autorità di certificazione
- Autenticazione in X.509
- Schema di Shamir: polinomio segreto, distribuzione share, ricostruzione (sistema lineare + interpolazione di Lagrange), sicurezza perfetta
- Condivisione segreto tramite immagini (crittografia visuale, matrici base)
- Schema di Naor e Pinkas

**Argomenti M6 richiesti agli esami:**

| Domanda d'esame specifica | Appello | Punti | Copertura |
|---|---|---|---|
| Shamir (k,n): distribuzione + ricostruzione + calcolo numerico | 230124, 230907, 230908, 240705, 240708, 240906, 250120, 250210, 250704, 250715, 250905, 250919 | 20–30 | Coperto bene |
| Shamir (3,3) in Z11: ricostruire segreto da s1=6, s2=4, s3=2 | 230124, 230907, 230908, 240708 | 10 | Coperto bene |
| Shamir (2,3) in Z11: ricostruire segreto da s(1)=3, s(2)=4 | 230124, 230907, 230908, 240708, 250210 | 10–15 | Coperto bene |
| Shamir (n,n): teoria | 240705 | 10 | Coperto bene |
| Shamir (4,4) in Z13: ricostruire da P1=6,P2=5,P3=4,P4=9 | 240906, 250905 | 10 | Coperto bene |
| Shamir (2,3) in Z19: applicazione con s=3 | 250704 | 10 | Coperto bene |
| Shamir (3,3) in Z7 | 250715 | 10 | Coperto bene |
| Diffie-Hellman: calcolo chiavi + man-in-the-middle numerico | 230712, 230922 | 25 | Coperto bene |
| Diffie-Hellman: dimostrare che g è generatore/radice primitiva | 230922 | ~4 | Coperto parzialmente |
| Certificati digitali + CA + esempio Alice-Bob con stessa CA | 230707 | 25 | Coperto bene |

**Valutazione copertura M6:** ✅ **Coperto bene** per la grande maggioranza. ⚠️ **Coperto parzialmente** per la verifica numerica che g sia radice primitiva.

**Gap specifici M6:**

1. **Dimostrazione numerica che g è radice primitiva di Z_p** (appello 230922, ~4 punti della domanda 4a): la lezione L2 di UD1 M6 tratta la definizione di generatore e la struttura di Z_p*, ma non fornisce una procedura numerica passo-passo per verificare che un elemento g sia radice primitiva. La verifica consiste nel calcolare g^1, g^2, ..., g^(p-1) mod p e mostrare che si generano tutti i valori da 1 a p-1. All'esame viene chiesto "Dimostrare che 10 è un generatore = radice primitiva di Z19". Il materiale fornisce la definizione ma non questo tipo di calcolo di verifica.

---

## SEZIONE 3 – Argomenti richiesti agli esami NON presenti (o insufficientemente trattati) nelle lezioni

Di seguito la lista dettagliata degli argomenti/esercizi che appaiono negli esami ma che le lezioni non coprono in modo diretto e sufficiente. Per ogni voce: esame(i) dove appare, punteggio, suggerimento di integrazione.

### 3.1 AES-X = AES_k1(m) ⊕ k2 – analisi sicurezza

- **Esami:** Appello-230922 (20 punti sulla domanda 2b), Appello-250919 (20 punti sulla domanda 2b)
- **Peso:** 20 punti per appello
- **Descrizione precisa:** Viene definita la variante AES-X dove la cifratura è AESX(k1,k2)(m) = AESk1(m) ⊕ k2, con k1 e k2 chiavi AES da 128 bit. Viene chiesto: (i) descriverne le caratteristiche (10 punti); (ii) confrontare la sicurezza di AES standard vs AES-X sotto known-plaintext attack (20 punti).
- **Cosa manca nelle lezioni:** Le lezioni trattano AES in modo completo (L1-L4 UD3 M2) e spiegano il concetto di XOR con chiave (AddRoundKey), ma non analizzano questa costruzione ibrida. La risposta richiede di notare che: dato un known-plaintext (m, c) con c = AESk1(m) ⊕ k2, se si indovina k1 si può calcolare AESk1(m) e quindi k2 = c ⊕ AESk1(m) banalmente; questo riduce il problema a un attacco known-plaintext su AES standard, senza vantaggio dalla doppia chiave. Il ragionamento non è guidato nel materiale.
- **Suggerimento di integrazione:** Studiare il pattern di "key whitening" (XOR di chiave prima/dopo il cifrario) dalla letteratura. Leggere le domande degli appelli 230922 e 250919 come testo di autoapprendimento.

### 3.2 Cifrari a sostituzione su parole binarie di n bit

- **Esami:** Appello-240117 (30 punti, domanda 1 completa), Appello-250715 (30 punti, domanda 1 completa)
- **Peso:** 30 punti per appello
- **Descrizione precisa:** Viene chiesto: (a) quanti cifrari a sostituzione esistono per parole binarie di lunghezza n (risposta: (2^n)!); (b) dimensione della tabella di sostituzione in bit (risposta: n × 2^n bit totali); (c) analisi di un cifrario XOR-key specifico: data c=(m1⊕m2⊕k1, m2⊕k2), quante sostituzioni implementa, cifrare/decifrare un esempio numerico.
- **Cosa manca nelle lezioni:** Il materiale tratta i cifrari a sostituzione su alfabeto di 26 lettere (L3 UD2 M1) e li generalizza in modo descrittivo, ma non svolge l'astrazione su alfabeti binari di n bit né il ragionamento combinatorio (2^n simboli → (2^n)! permutazioni → tabella di n×2^n bit). L'analisi del cifrario XOR-key richiede un ragionamento su quante chiavi diverse producono sostituzioni diverse.
- **Suggerimento di integrazione:** Questo argomento appare in 2 appelli distinti (2024 e 2025): è un argomento emergente e ricorrente. Preparare una trattazione formale del caso binario, partendo dalla definizione generale di cifrario a sostituzione e generalizzandola.

### 3.3 Composizione di cifrari a permutazione (Pk1 ∘ Pk2)

- **Esame:** Appello-240906 (15 punti, domanda 1c-d)
- **Peso:** 15 punti
- **Descrizione precisa:** Vengono dati due permutation cipher Pk1 e Pk2 su blocchi di 5 lettere con tabelle esplicite (k1: 1→2, 2→3, 3→5, 4→4, 5→1; k2: 1→2, 2→5, 3→4, 4→3, 5→1). Viene chiesto di: (c) applicare ciascun cifrario al messaggio "ciao estate"; (d) descrivere il cifrario composito c = Pk1(Pk2(m)) e commentarne il livello di sicurezza.
- **Cosa manca nelle lezioni:** Il materiale tratta Rail Fence e la trasposizione per colonne (L2 UD2 M1) come esempi di cifrari a trasposizione, ma non formalizza la composizione di permutation cipher né fornisce esempi con tabelle esplicite del tipo proposto dall'esame. In particolare non viene analizzato se la composizione amplia o no lo spazio delle chiavi effettive.
- **Suggerimento di integrazione:** Capire che la composizione di due permutazioni di n elementi è ancora una permutazione di n elementi (il gruppo simmetrico S_n è chiuso rispetto alla composizione). La sicurezza del cifrario composito dipende da quante permutazioni distinte si possono ottenere variando k1 e k2 — se le permutazioni Pk1 e Pk2 generano l'intero S_n, il cifrario composito non è più sicuro.

### 3.4 Modalità OFB personalizzata: deduzione formale del keystream

- **Esami:** Appello-230907 (30 punti, domanda 2), Appello-230908 (30 punti, domanda 2) — stesso testo
- **Peso:** 30 punti per appello (ma stessa domanda in due versioni dello stesso appello)
- **Descrizione precisa:** Si considera ct = mt ⊕ wt, w0=IV, wt=EK(wt-1). Si elimina il blocco ci dal file cifrato e si ricifrano i successivi con la stessa chiave, ottenendo c'i = mi+1 ⊕ wi, c'i+1 = mi+2 ⊕ wi+1, ecc. Dimostrare che se mi è noto, assieme ai file originale e aggiornato, si possono dedurre tutti i wt e mt per t≥i.
- **Cosa manca nelle lezioni:** Il materiale copre la modalità OFB (sezione 5 di L2 UD2 M2) e spiega che il keystream wt = EK(wt-1) è indipendente dal plaintext. Ma non svolge questa dimostrazione deduttiva formale: dal blocco ci originale e da mi noto si calcola wi = ci ⊕ mi; poi wi+1 = EK(wi), wi+2 = EK(wi+1), ...; poi mt = c't ⊕ wt per ogni t≥i. La procedura step-by-step non è presente.
- **Suggerimento di integrazione:** Lavorare sulla struttura matematica dell'OFB e costruire la dimostrazione passo-passo. È una domanda di analisi che vale 30 punti e si presta a essere preparata come esercizio di autovalutazione.

### 3.5 H(x) = DES_k(x) come funzione hash: analisi one-wayness

- **Esame:** Appello-250704 (15 punti, domanda 3b)
- **Peso:** 15 punti
- **Descrizione precisa:** Analizzare la proprietà di one-wayness per H(x) = DESk(x) per un certo k, nei tre casi: (i) se k può variare; (ii) per k fisso e noto; (iii) per k fisso e ignoto.
- **Cosa manca nelle lezioni:** Le lezioni trattano le proprietà delle funzioni hash (L2 UD1 M4) e la struttura DES (L1 UD2 M2) separatamente, ma non questa analisi composita. Le risposte attese sono: (i) se k varia, non è one-way perché dato H(x) si può decifrare con qualsiasi k; (ii) per k fisso e noto, DES è invertibile quindi DES⁻¹_k(H(x)) = x: non è one-way; (iii) per k fisso e ignoto, H è presumibilmente one-way perché rompere DES richiede trovare k (≈ 2^56 operazioni).
- **Suggerimento di integrazione:** Costruire questa analisi applicando la definizione di one-wayness (data nel materiale) alle tre situazioni, ragionando sulla invertibilità di DES in ciascun caso.

### 3.6 DSA – firma (a, 0): dimostrazione della rottura del sistema

- **Esami:** Appello-230622 (10 punti, domanda 4c), Appello-240624 (10 punti, domanda 4b)
- **Peso:** 10 punti per appello
- **Descrizione precisa:** "Dimostrare che in caso si ottenga per un certo valore a la firma (a, 0), il sistema DSA potrebbe essere rotto."
- **Cosa manca nelle lezioni:** La lezione L3 di UD3 M5 tratta la sicurezza del DSS (total break, selective forgery, existential forgery) ma non presenta esplicitamente il caso δ=0. La dimostrazione richiede: se δ=0, dalla formula di firma DSA δ = (SHA(M) + sγ) · r⁻¹ mod q, con δ=0 si ha SHA(M) + sγ ≡ 0 mod q, quindi s ≡ −SHA(M) · γ⁻¹ mod q. La chiave privata s è direttamente recuperabile. Il ragionamento è deducibile dalle formule presenti nel materiale (L1 UD3 M5) ma non viene svolto esplicitamente.
- **Suggerimento di integrazione:** Lavorare sulla formula DSA di firma, mettere δ=0 e derivare le conseguenze algebriche. Appare in due appelli distinti (2023 e 2024): argomento confermato come ricorrente.

### 3.7 DSA – δ=0 durante generazione firma: bisogna rigenerare k?

- **Esame:** Appello-240220 (10 punti, domanda 4c)
- **Peso:** 10 punti
- **Descrizione precisa:** "Cosa succede se durante la generazione della firma (r,s) (o (γ,δ)) di un messaggio m si produce un valore s=0 (δ=0). È vero che bisogna generare un nuovo valore casuale k (o r) e ricalcolare la firma? Giustificare la risposta."
- **Cosa manca nelle lezioni:** Il materiale non tratta esplicitamente questo caso. La risposta (sì, bisogna rigenerare k perché se δ=0 la firma non è valida — la verifica richiede δ⁻¹ mod q che non esiste per δ=0 — e l'argomento precedente mostra che δ=0 rivela la chiave privata) è deducibile con ragionamento.
- **Suggerimento di integrazione:** Analizzare cosa succede nella procedura di verifica DSA quando δ=0: si calcola δ⁻¹ mod q che non esiste → verifica fallisce; inoltre, come visto nel punto 3.6, δ=0 rivela s. Quindi è obbligatorio rigenerare k.

### 3.8 Double-RSA C(M) = ((M)^e1)^e2: analisi di sicurezza

- **Esami:** Appello-240708 (10 punti, domanda 3c), Appello-250715 (10 punti, domanda 3c) — testo identico
- **Peso:** 10 punti per appello
- **Descrizione precisa:** Si definisce il double-RSA come C(M) = ((M)^e1)^e2 con e1 ed e2 chiavi pubbliche diverse dello stesso utente. "Si facciano considerazioni sulla sicurezza di questo cifrario."
- **Cosa manca nelle lezioni:** Le lezioni trattano RSA standard e vari attacchi (common modulus, low exponent, CCA — L5 UD2 M3) ma non il double-RSA così definito. La risposta richiede di notare che C(M) = M^(e1·e2) mod n, che è equivalente a RSA con un singolo esponente pubblico e = e1·e2. Se e1·e2 è invertibile mod φ(n), la sicurezza è quella di RSA standard — il double-RSA non aggiunge protezione. Se e1·e2 non è invertibile mod φ(n), la decifrazione potrebbe non essere possibile.
- **Suggerimento di integrazione:** Ragionare sulla proprietà di esponenziazione iterata: (M^e1)^e2 = M^(e1·e2). Appare in due appelli distinti (2024 e 2025): argomento emergente e ricorrente.

### 3.9 Dimostrazione numerica che g è radice primitiva di Z_p

- **Esame:** Appello-230922 (parte della domanda 4a, ~3-4 punti)
- **Peso:** ~4 punti
- **Descrizione precisa:** "Sia q=19 il numero primo comune e sia il generatore g=10. Dimostrare che 10 è un generatore = radice primitiva di Z19."
- **Cosa manca nelle lezioni:** Il materiale definisce cos'è un generatore (L1 UD1 M6) e ne usa il concetto nel protocollo DH, ma non fornisce la procedura numerica completa per verificarlo: calcolare 10^1, 10^2, ..., 10^18 mod 19 e mostrare che si ottengono tutti i valori {1, 2, ..., 18}.
- **Suggerimento di integrazione:** Conoscere la procedura di verifica: calcolare le potenze successive fino a p-1. In alternativa, sfruttare il teorema di Lagrange: g è radice primitiva di Z_p iff l'ordine di g è p-1, verificabile mostrando che g^((p-1)/q) ≠ 1 per ogni fattore primo q di p-1.

---

## SEZIONE 4 – Tipologie di domande ed esercizi

### 4.1 Domande di teoria pura (definizioni, proprietà, confronti)

**Frequenza:** Alta — presenti in ogni esame, circa 25–35% del punteggio totale.  
**Punteggio medio per domanda:** 5–15 punti.  
**Argomenti più richiesti:** classificazione attacchi (KCA/KPA/CPA/CCA), sicurezza condizionata vs incondizionata e OTP, proprietà hash (one-way, weak/strong collision), caratteristiche MAC/HMAC, struttura AES (4 trasformazioni + round), parametri DSS, modalità DES (5 modalità + pro/contro), caratteristiche Blowfish.

**Copertura nelle lezioni:** ✅ **Ottima**. Il materiale tratta tutti i concetti teorici con definizioni precise, schemi e tabelle riassuntive. La struttura narrativa delle lezioni facilita lo studio delle definizioni.

**Esempio preciso — completamente coperto:** Appello 240220 domanda 3a: "Descrivere caratteristiche e applicazioni delle funzioni MAC" (10 punti) → coperto completamente da L1 UD3 M4 (Codici MAC).

**Esempio preciso — completamente coperto:** Appello 240117 domanda 2: "Descrivere le principali caratteristiche del cifrario AES e le operazioni di cifratura e decifratura" (20 punti) → coperto da L1–L4 UD3 M2.

### 4.2 Esercizi di calcolo

**Frequenza:** Molto alta — presenti in ogni esame, circa 40–55% del punteggio totale.  
**Punteggio medio per esercizio:** 10–20 punti.  
**Argomenti più richiesti:**
- Shamir: ricostruzione segreto tramite sistema lineare o Lagrange in Z_p
- El-Gamal: calcolo y1=g^k mod p, y2=M·β^k mod p, decifratura z=y1^α mod p, M=y2·z⁻¹ mod p
- Diffie-Hellman: calcolo chiavi pubbliche A=g^x mod p e B=g^y mod p, chiave condivisa K
- Hill: moltiplicazione matriciale mod 26, calcolo inversa matrice mod 26, determinazione chiave da known-plaintext
- Vigenère: calcolo IC su una parola, calcolo MIC tra due parole
- Affine: trovare a e b da due coppie (plaintext, ciphertext), applicare cifratura a nuove parole
- RSA: calcolo di d dato e e φ(n), fattorizzazione di n dato φ(n), cifratura/decifratura numeri
- Feistel toy: calcolo step-by-step dei round con funzione f specifica

**Copertura nelle lezioni:** ✅ **Buona** per la presenza di algoritmi e di esempi numerici. Le lezioni forniscono le formule e almeno un esempio svolto per ogni argomento.

**Lacuna trasversale di tipo pratico:** Le lezioni forniscono gli algoritmi e qualche esempio, ma **non offrono esercizi in abbondanza**. L'esame non ammette materiale di consultazione: lo studente deve eseguire calcoli modulari a mano (inversi, potenze, sistemi lineari in Z_p) con velocità e precisione. Chi studia solo le lezioni conosce il metodo ma rischia di essere lento o di fare errori aritmetici per mancanza di pratica ripetuta.

**Esempio critico — calcolo inverso modulare:** L'interpolazione di Lagrange in Shamir richiede calcolare inversi come 4⁻¹ mod 11 = 3 (perché 4×3=12≡1 mod 11). Questo calcolo va fatto a mano velocemente. Il materiale spiega il concetto di inverso modulare (L2 UD2 M3) ma non fornisce una tabella o abbastanza esercizi per automatizzarlo.

### 4.3 Domande di analisi/sicurezza

**Frequenza:** Media-alta — presenti in 14-16 su 21 esami, circa 15–30% del punteggio.  
**Punteggio medio:** 10–20 punti.  
**Argomenti:** analisi di cifrari non-standard (XOR+hash), sicurezza RSA (naive, common modulus, low exponent, double-RSA), sicurezza DH (man-in-the-middle numerico), sicurezza DES (meet-in-the-middle con complessità), AES-X, sicurezza MAC personalizzato, firma (a,0) in DSA, doppia cifratura affine.

**Copertura nelle lezioni:** ⚠️ **Parziale**. Le lezioni trattano la sicurezza degli schemi standard (RSA, DH, DSS, DES) ma non sempre preparano alle "domande di analisi creativa" dove l'esame propone uno schema non-standard e chiede di trovarne un attacco o di valutarne la sicurezza. Queste domande richiedono di applicare i principi studiati a casi nuovi.

**Esempio critico non coperto:** Appello 230216 domanda 2 (35 punti): cifrario a blocchi personalizzato con hash+XOR. Richiede (a) derivare la decifratura, (b) discutere la sicurezza con un CPA, (c) calcolo numerico. Nessun analogo nelle lezioni.

**Esempio critico parzialmente coperto:** Appello 230922 domanda 3b (10 punti): "Discutere l'equivalenza o meno tra il problema RSA e la fattorizzazione dei numeri primi." Coperto bene da L5 UD2 M3.

### 4.4 Schemi/protocolli da descrivere

**Frequenza:** Alta — presenti in quasi ogni esame.  
**Punteggio medio:** 10–15 punti.  
**Argomenti:** schema DH completo (chiavi private, pubbliche, condivise), schema RSA firma/verifica, schema DSS firma/verifica passo-passo, schema Shamir distribuzione/ricostruzione, schema certificati X.509 + ruolo CA, schema HMAC con formula.

**Copertura nelle lezioni:** ✅ **Ottima**. Le lezioni descrivono passo per passo tutti i protocolli standard richiesti, con formule matematiche e schemi.

---

## SEZIONE 5 – Valutazione finale e raccomandazioni

### 5.1 Risposta diretta: studiando solo questi 6 moduli si può prendere 30/30?

**Risposta: Probabilmente NO con certezza, ma un 27–30 è raggiungibile con il giusto approccio.**

La risposta onesta è che il materiale dei 6 moduli **copre direttamente l'80–85% del punteggio medio di ogni esame**, ma lascia scoperti o parzialmente scoperti alcuni argomenti che valgono i restanti 15–20 punti. Questi argomenti "a rischio" si presentano con frequenza sufficiente da incidere significativamente:

- **AES-X** appare in 2 appelli (2023, 2025) per 20 punti ciascuno.
- **Cifrari a sostituzione binaria** appare in 2 appelli (2024, 2025) per 30 punti ciascuno.
- **Cifrario personalizzato XOR+hash (CPA)** appare in 3 appelli (2023×2, 2025) per 35 punti ciascuno.
- **OFB personalizzato (dimostrazione)** appare in 2 versioni dello stesso appello per 30 punti.
- **DSA firma (a,0)** appare in 2 appelli (2023, 2024) per 10 punti ciascuno.
- **Double-RSA** appare in 2 appelli (2024, 2025) per 10 punti ciascuno.

Se in un appello capita uno di questi argomenti non coperti (evento frequente), si perdono 10–35 punti. Con il solo materiale delle lezioni si può stimare un voto tra **65/100 e 88/100** a seconda dell'appello specifico.

**Per prendere 28–30/30 con buona probabilità** occorre:
1. Padroneggiare alla perfezione tutto il materiale dei 6 moduli (condizione necessaria ma non sufficiente);
2. Colmare le 5–6 lacune prioritarie identificate nella Sezione 3;
3. Praticare intensivamente i calcoli numerici (Shamir, El-Gamal, Hill, RSA, Feistel).

### 5.2 Percentuale stimata di esame coperta dal materiale

Analizzando la distribuzione di punteggio per argomento e la frequenza di apparizione:

| Categoria                                                                        | % punteggio coperto dal materiale |
| -------------------------------------------------------------------------------- | --------------------------------- |
| Teoria pura (definizioni, proprietà, schemi standard)                            | ~95%                              |
| Esercizi di calcolo standard (Shamir, El-Gamal, DH, Hill, RSA, affine)           | ~90%                              |
| Analisi sicurezza schemi standard (DH MitM, RSA fattorizzazione, meet-in-middle) | ~80%                              |
| Analisi sicurezza schemi non-standard (XOR+hash, AES-X, OFB personalizzato)      | ~35%                              |
| **Media pesata complessiva**                                                     | **~82%**                          |

Concretamente: su un esame tipico da 100 punti, il materiale consente di rispondere con sicurezza a circa **82 punti**. I restanti ~18 punti richiedono integrazione o ragionamento creativo non guidato.

### 5.3 Lista ordinata per priorità delle lacune da colmare

Le lacune sono ordinate per **impatto sul voto finale** (frequenza × punteggio medio):

**PRIORITÀ MASSIMA (colmare queste lacune ha il massimo impatto):**

1. **Cifrari a blocchi non-standard XOR+hash: decifratura + CPA attack** (appare in 3 appelli, 35 punti)
   - Imparare a derivare la decifratura simbolicamente da qualsiasi cifrario c = f(m, h(m'), k).
   - Imparare a costruire CPA attack: scegliere m1=0 (o valori che azzerano componenti) per isolare la chiave o l'hash.
   - Tempo stimato di preparazione: 3–4 ore con esercizi svolti su tutti e tre gli appelli.

2. **Cifrari a sostituzione su parole binarie di n bit** (appare in 2 appelli, 30 punti)
   - Imparare: (2^n)! permutazioni possibili per n-bit words; tabella di n×2^n bit; analisi XOR-key.
   - Tempo stimato: 2–3 ore.

3. **AES-X = AES_k1(m) ⊕ k2: caratteristiche e sicurezza KPT** (appare in 2 appelli, 20 punti)
   - Imparare: definizione di key whitening, perché k2 è isolabile da un KPT, confronto con AES standard.
   - Tempo stimato: 1–2 ore.

**PRIORITÀ ALTA:**

4. **OFB personalizzato: deduzione formale del keystream** (appare in 2 versioni dello stesso appello, 30 punti)
   - Imparare a costruire la dimostrazione formale passo-passo: wi = ci ⊕ mi, poi ricorsione.
   - Tempo stimato: 2 ore.

5. **DSA firma (a,0): dimostrazione della rottura** (appare in 2 appelli, 10 punti)
   - Imparare: δ=0 → s = −SHA(M)·γ⁻¹ mod q → chiave privata recuperata.
   - Tempo stimato: 1 ora.

6. **H(x) = DES_k(x): one-wayness nei tre scenari** (appare in 1 appello, 15 punti)
   - Imparare l'analisi sistematica dei tre casi (k variabile, fisso noto, fisso ignoto).
   - Tempo stimato: 1 ora.

**PRIORITÀ MEDIA:**

7. **Double-RSA C(M) = ((M)^e1)^e2 – sicurezza** (appare in 2 appelli, 10 punti)
   - Imparare: C(M) = M^(e1·e2), equivalente a RSA singolo, nessun vantaggio di sicurezza.
   - Tempo stimato: 30 minuti.

8. **DSA δ=0: bisogna rigenerare k?** (appare in 1 appello, 10 punti)
   - Imparare: sì, perché δ⁻¹ mod q non esiste e la firma è compromessa.
   - Tempo stimato: 30 minuti.

9. **Composizione di permutation cipher** (appare in 1 appello, 15 punti)
   - Imparare a calcolare la tabella composita e commentare sicurezza.
   - Tempo stimato: 1 ora.

10. **Dimostrazione numerica che g è radice primitiva di Z_p** (appare in 1 appello, ~4 punti)
    - Imparare la procedura: calcolare le potenze g^1, ..., g^(p-1) mod p.
    - Tempo stimato: 30 minuti.

### 5.4 Consigli pratici su cosa approfondire e come

**Consiglio 1 – La pratica numerica è fondamentale (lacuna trasversale)**

Il materiale spiega gli algoritmi ma non fornisce abbastanza esercizi svolti. Tutti gli argomenti di calcolo devono essere praticati su carta, senza calcolatrice, perché l'esame non la ammette. In particolare:

- **Per Shamir**: esercitarsi a risolvere sistemi 2×2 e 3×3 in Z_p con l'eliminazione di Gauss modulare, e a calcolare inversi moltiplicativi (es.: 4⁻¹ mod 11 = 3, verificato come 4×3=12≡1; 2⁻¹ mod 11 = 6; 3⁻¹ mod 11 = 4). Shamir appare nell'81% degli appelli: è l'argomento più importante del corso.
- **Per El-Gamal**: calcolare potenze modulari grandi con il metodo left-to-right (square-and-multiply), che riduce le operazioni da 2^k a ~1.5k moltiplicazioni.
- **Per Hill**: calcolare determinanti mod 26, trovare l'inverso modulare del determinante, costruire la matrice aggiunta, verificare det×K⁻¹ ≡ I mod 26.
- **Per RSA**: calcolare d = e⁻¹ mod φ(n) con l'algoritmo di Euclide esteso; fattorizzare n da φ(n) risolvendo p² − (n − φ(n) + 1)p + n = 0.

**Consiglio 2 – Su come affrontare i cifrari non-standard**

Gli esami presentano cifrari "toy" costruiti con componenti note (hash, XOR, DES). La strategia generale è:
- Per la **decifratura**: partire dall'ultima equazione di cifratura e isolare la variabile plaintext, procedendo all'indietro.
- Per il **chosen-plaintext attack**: scegliere m1=0 per eliminare il termine h(m2)⊕k1 dal primo blocco cifrato, oppure scegliere m2=0 per eliminare h(0) e isolare k1.
- Per la **sicurezza**: verificare se il cifrario è deterministico (stesso messaggio → stesso ciphertext, vulnerabile a KPT), se ha proprietà omomorfiche, se propaga gli errori.

**Consiglio 3 – DSS: padroneggiare le formule e le loro conseguenze**

Le lezioni coprono bene la teoria DSS. Il gap è nell'analisi delle conseguenze di valori particolari. Strategia: rileggere le formule di firma (γ = (α^r mod p) mod q, δ = (SHA(M)+sγ)r⁻¹ mod q) e verifica (γ ?= (α^e' β^e'' mod p) mod q) e chiedersi sistematicamente: "Cosa succede se γ=0? Se δ=0? Se r viene riusato per due messaggi diversi?" Queste domande compaiono negli esami e le risposte si derivano direttamente dalle formule.

**Consiglio 4 – Secret Sharing è l'argomento più importante**

Appare nell'**81% degli appelli** (17 su 21). Spesso vale 20–30 punti. Il materiale lo tratta bene (L2 UD2 M6). Assolutamente da padroneggiare:
- La differenza concettuale tra schema (k,n), (n,n), e (2,3).
- La costruzione del polinomio f(x) = S + a1x + ... + a(k-1)x^(k-1) mod p.
- La ricostruzione tramite sistema lineare (gaussiana in Z_p) e tramite interpolazione di Lagrange.
- Il calcolo dell'inverso moltiplicativo in Z_p per piccoli valori (essenziale per Lagrange).
- La verifica che con k-1 share il segreto è perfettamente nascosto (sistema indeterminato).

**Consiglio 5 – Struttura dell'esame e distribuzione del tempo**

Ogni appello ha 4 domande da ~25 punti ciascuna. La distribuzione è quasi sempre: (1) crittografia classica, (2) crittografia simmetrica, (3) crittografia asimmetrica/firma, (4) Secret Sharing o hash/MAC. Per prendere un voto alto:
- Dominare il Secret Sharing (domanda 4, ~25 punti, quasi certa): garantisce punti sicuri nell'81% degli appelli.
- Dominare DSS teoria + calcolo (domande sparse, ~10-15 punti, presente nel 52% degli appelli).
- Dominare El-Gamal con calcolo numerico (domanda 3 o 4, ~25-30 punti, presente nel 43% degli appelli).
- Dominare le modalità DES (domanda 2, ~15-20 punti, presente nel 38% degli appelli).

---

## Conclusione sintetica

Il materiale dei 6 moduli è **ricco, dettagliato e ben strutturato**. Copre la struttura teorica del corso in modo eccellente e tratta in profondità: struttura di Feistel/DES/AES, RSA con dimostrazioni di correttezza e sicurezza, El-Gamal, schema di Shamir, HMAC, DSS, Vigenère con IC/MIC, Hill, Playfair, classificazione degli attacchi, certificati digitali e PKI.

Le lacune principali sono di due tipi:

1. **Argomenti non trattati o insufficientemente trattati** (AES-X, cifrari binari n-bit, composizione permutazioni, double-RSA, OFB con dimostrazione formale, H=DES_k): richiedono studio integrativo di 1–4 ore ciascuno, con priorità decrescente secondo la Sezione 5.3.

2. **Applicazioni creative** (attacchi a cifrari non-standard XOR+hash, dimostrazioni formali su schemi modificati, analisi caso per caso di proprietà crittografiche): richiedono **pratica nel ragionamento applicativo**, non solo la lettura passiva delle lezioni. La capacità di generalizzare i principi a casi nuovi non si acquisisce leggendo ma risolvendo problemi.

**Con un piano di studio che:**
- padroneggia completamente il materiale dei 6 moduli (~75% dello sforzo),
- colma le 5–6 lacune prioritarie identificate (~15% dello sforzo),
- pratica numericamente tutti gli esercizi di calcolo con simulazioni di esame (~10% dello sforzo),

**è ragionevole e realistico puntare a 28–30/30.**

---

*Report generato il 29/05/2026 — basato su 21 esami (2023–2025) e 95+ file di lezione analizzati.*
