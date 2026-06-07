# Appello del 7 luglio 2023 — Soluzione completa

> 📌 **Informazioni appello:** 07/07/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (25 + 25 + 25 + 25).
> Lezioni di teoria da studiare prima di affrontare questo appello:
> - [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — modelli di attacco
> - [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Propriet%C3%A0.md) — funzioni hash
> - [`../../M6_Applicazioni_Crittografiche/UD2/L2 - Certificati – Creazione, scambio e revoca.md`](../../M6_Applicazioni_Crittografiche/UD2/L2%20-%20Certificati%20%E2%80%93%20Creazione%2C%20scambio%20e%20revoca.md) — certificati digitali
> - [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — El-Gamal

---

## Domanda 1 — Cifrari classici: classificazione attacchi e resistenza (25 punti)

### Traccia originale

> **(25 punti) Cifrari classici.**
>
> a. (5 punti) Dare una classificazione dei possibili attacchi in base alla conoscenza dell'avversario.
>
> b. (20 punti) Secondo la classificazione precedente dire a quali tipi di attacchi resistono i seguenti cifrari, motivando la risposta con un esempio: i. Cifrario a sostituzione, ii. Cifrario Playfair, iii. Cifrario Hill, iv. DES, v. RSA.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L4 - Sicurezza dei cifrari e crittoanalisi.md`](../../M1_Crittografia_Classica/UD2/L4%20-%20Sicurezza%20dei%20cifrari%20e%20crittoanalisi.md) — modelli di attacco
- [`../../M1_Crittografia_Classica/UD2/L5 - Crittoanalisi dei cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L5%20-%20Crittoanalisi%20dei%20cifrari%20a%20sostituzione.md) — attacchi ai cifrari classici

### Soluzione dettagliata

#### Parte a — Classificazione degli attacchi (5 punti)

In ordine crescente di potenza dell'avversario:

1. **COA (Ciphertext-Only):** conosce solo testi cifrati. _Esempio:_ analisi delle frequenze su testo monoalfabetico.
2. **KPA (Known-Plaintext):** conosce coppie $(P_i, C_i)$ con la stessa chiave. _Esempio:_ i "crib" su Enigma.
3. **CPA (Chosen-Plaintext):** ha un oracolo di cifratura (sceglie i plaintext). _Esempio:_ attacchi su ECB.
4. **CCA (Chosen-Ciphertext):** ha un oracolo di decifratura. _Esempio:_ padding oracle (POODLE).

> 📌 Gerarchia: $\text{CCA} > \text{CPA} > \text{KPA} > \text{COA}$. La sicurezza moderna richiede resistenza almeno a CPA.

#### Parte b — Resistenza dei vari cifrari (20 punti)

| Cifrario | COA | KPA | CPA | Motivazione sintetica |
| --- | --- | --- | --- | --- |
| **Sostituzione** | ✗ | ✗ | ✗ | Analisi frequenze (~50 lettere) rompe COA; 26 coppie note danno la tabella (KPA); deterministico (CPA). |
| **Playfair** | parziale ✗ | ✗ | ✗ | Resiste alle frequenze monogramma ma cade su quelle dei **bigrammi**; coppie note vincolano la griglia $5\times5$. |
| **Hill** ($m\times m$) | parziale ✗ | ✗ | ✗ | Nasconde le frequenze delle singole lettere, ma con $m$ coppie indipendenti $K = C P^{-1}\bmod 26$; in CPA si scelgono i vettori base. |
| **DES** | ✓ (riserve) | ✓ (riserve) | ✓ (teor.) | Limite reale = chiave 56 bit (brute force oggi fattibile); crittoanalisi lineare $2^{43}$ KPA, differenziale $2^{47}$ CPA. |
| **RSA** | ✓ | ✓ (con padding) | ✓ (con OAEP) | Sicurezza = fattorizzazione; textbook RSA deterministico è vulnerabile a CPA/CCA (malleabilità), risolto da OAEP. |

> ⚠️ I cifrari **classici** (sostituzione, Playfair, Hill) cadono già nei modelli più deboli; **DES e RSA** resistono ai modelli classici alle condizioni indicate (chiave adeguata / padding sicuro).

---

## Domanda 2 — Cifrario a blocchi basato su hash e XOR (variante) (25 punti)

### Traccia originale

> **(25 punti)** Cifrario a blocchi con hash $h$ (64 bit) e XOR, chiave $k = k_1\|k_2$, messaggio $m = m_1\|m_2$ (blocchi da 64 bit). La cifratura $E_k(m) = c_1\|c_2$ è:
> $$c_1 = h(m_1) \oplus m_2 \oplus k_1, \qquad c_2 = m_1 \oplus c_1 \oplus k_2$$
>
> a. (10 punti) Mostrare come avviene la decifratura conoscendo la chiave $k$.
>
> b. (15 punti) Considerazioni sulla sicurezza, in generale e nel caso di chosen-plaintext attack.

### Prerequisiti teorici

- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20%E2%80%93%20introduzione.md) — cifrari a blocchi
- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Propriet%C3%A0.md) — funzioni hash

### Soluzione dettagliata

#### Parte a — Decifratura conoscendo $k$ (10 punti)

Si inverte nell'ordine giusto. Dalla seconda equazione, dato che $c_1$ è noto:
$$c_2 = m_1 \oplus c_1 \oplus k_2 \;\Rightarrow\; \boxed{m_1 = c_2 \oplus c_1 \oplus k_2}$$
Recuperato $m_1$, si calcola $h(m_1)$ e dalla prima:
$$c_1 = h(m_1) \oplus m_2 \oplus k_1 \;\Rightarrow\; \boxed{m_2 = c_1 \oplus h(m_1) \oplus k_1}$$

#### Parte b — Sicurezza e attacco chosen-plaintext (15 punti)

Come la variante "speculare" (cfr. 16/02/2023), il cifrario è **deterministico** (nessun IV) → non semanticamente sicuro, e **lineare rispetto allo XOR** → malleabile.

- **Recupero di $k_2$ da una sola coppia nota:** $k_2 = c_2 \oplus m_1 \oplus c_1$. Il mascheramento del secondo blocco di equazione è quindi noto.
- **Maschera del secondo blocco per ogni $m_1$:** scegliendo due plaintext con **lo stesso $m_1$** e $m_2 \neq m_2'$:
$$c_1 \oplus c_1' = (h(m_1)\oplus m_2 \oplus k_1) \oplus (h(m_1)\oplus m_2'\oplus k_1) = m_2 \oplus m_2'$$
cioè per $m_1$ fissato il cifrario è uno **XOR a maschera costante** $K' = h(m_1)\oplus k_1 = c_1 \oplus m_2$: l'avversario ricava $K'$ e cifra/decifra qualunque messaggio con quello stesso $m_1$, e può **flippare bit** scelti nel ciphertext (malleabilità).

> ⚠️ Deterministico + malleabile + chiave parziale ($k_2$, e $K'=h(m_1)\oplus k_1$) ricavabile sotto CPA → schema **insicuro**. La hash $h$ non aiuta: è pubblica e applicata solo a un blocco di plaintext (in CPA noto), quindi non introduce segretezza.

---

## Domanda 3 — Certificati digitali (25 punti)

### Traccia originale

> **(25 punti) Certificati digitali.**
>
> a. (10) Illustrare la funzione di un certificato digitale e il ruolo di una autorità di certificazione.
>
> b. (15) Esempio di come Alice e Bob possano usare un certificato rilasciato da una stessa CA.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD2/L2 - Certificati – Creazione, scambio e revoca.md`](../../M6_Applicazioni_Crittografiche/UD2/L2%20-%20Certificati%20%E2%80%93%20Creazione%2C%20scambio%20e%20revoca.md) — certificati e revoca
- [`../../M6_Applicazioni_Crittografiche/UD2/L4 - Autorità di certificazione.md`](../../M6_Applicazioni_Crittografiche/UD2/L4%20-%20Autorit%C3%A0%20di%20certificazione.md) — ruolo della CA
- [`../../M6_Applicazioni_Crittografiche/UD2/L3 - Standard X.509.md`](../../M6_Applicazioni_Crittografiche/UD2/L3%20-%20Standard%20X.509.md) — formato X.509

### Soluzione dettagliata

#### Parte a — Funzione del certificato e ruolo della CA (10 punti)

Un **certificato digitale** lega in modo verificabile un'**identità** (es. "Alice", un dominio) a una **chiave pubblica**. Risolve il problema fondamentale della crittografia a chiave pubblica: come fidarsi che una chiave pubblica appartenga davvero a chi dice di esserne il proprietario (evitando attacchi man-in-the-middle in cui un avversario spaccia la propria chiave per quella di Alice).

Un certificato (formato **X.509**) contiene tipicamente: identità del soggetto, sua chiave pubblica, identità dell'emittente, periodo di validità, numero di serie, algoritmi, e soprattutto la **firma digitale della CA** su tutti questi campi.

L'**Autorità di Certificazione (CA)** è una terza parte fidata che: (1) **verifica l'identità** del richiedente; (2) **emette** il certificato firmandolo con la propria chiave privata; (3) **pubblica e revoca** i certificati (CRL / OCSP). Chiunque conosca la chiave pubblica della CA (preinstallata come *root* fidata) può verificarne la firma e quindi fidarsi del legame identità↔chiave.

#### Parte b — Esempio: Alice e Bob con la stessa CA (15 punti)

Premessa: Alice e Bob possiedono già, in modo affidabile, la **chiave pubblica della CA** $PU_{CA}$ (è una root fidata).

1. **Registrazione.** Alice genera $(PU_A, PR_A)$ e prova la propria identità alla CA. La CA crea il certificato $\text{Cert}_A = \{\text{"Alice"}, PU_A, \text{validità}, \ldots\}$ e lo firma: $\text{Sig}_{CA} = \text{Sign}_{PR_{CA}}(\text{Cert}_A)$. Analogamente per Bob ($\text{Cert}_B$).
2. **Scambio.** Quando Alice vuole comunicare con Bob, gli invia $\text{Cert}_A$ (e viceversa); i certificati non sono segreti.
3. **Verifica.** Bob verifica la firma della CA su $\text{Cert}_A$ usando $PU_{CA}$: se valida (e il certificato non è scaduto né revocato), Bob è certo che $PU_A$ appartiene davvero ad Alice. Alice fa lo stesso con $\text{Cert}_B$.
4. **Uso.** Ora possono comunicare in sicurezza: ad esempio Bob cifra una chiave di sessione con $PU_A$ (solo Alice la decifra con $PR_A$), oppure si autenticano firmando un nonce. Un man-in-the-middle non può sostituire le chiavi perché non possiede $PR_{CA}$ per firmare un certificato falso.

> 💡 Avere la **stessa CA** semplifica: basta un'unica chiave radice fidata per validare entrambi. Con CA diverse serve una **catena di certificati** fino a una root comune.

---

## Domanda 4 — Crittosistema El-Gamal (25 punti)

### Traccia originale

> **(25 punti) Crittosistema El-Gamal.**
>
> a. (10) Descrivere i parametri e le fasi di cifratura e decifratura.
>
> b. (15) Con modulo $p=11$, generatore $g=2$, chiave privata di Alice $\alpha = 6$, calcolare: i. la chiave pubblica di Alice; ii. la cifratura $C$ del messaggio $M=5$ con $k=3$; iii. la decifratura di $C$.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD3/L2 - Crittosistema di El-Gamal.md`](../../M3_Cifrature_Asimmetriche/UD3/L2%20-%20Crittosistema%20di%20El-Gamal.md) — schema completo
- [`../../M3_Cifrature_Asimmetriche/UD3/L1 - Il problema del logaritmo discreto.md`](../../M3_Cifrature_Asimmetriche/UD3/L1%20-%20Il%20problema%20del%20logaritmo%20discreto.md) — sicurezza

### Soluzione dettagliata

#### Parte a — Parametri e fasi (10 punti)

**Parametri pubblici:** primo $p$, generatore $g$ di $\mathbb{Z}_p^*$. **Chiavi di Alice:** privata $\alpha$, pubblica $\beta = g^\alpha \bmod p$; pubblica $(p,g,\beta)$.

**Cifratura (Bob, $M$):** sceglie $k$ casuale; $c_1 = g^k \bmod p$, $c_2 = M\cdot\beta^k \bmod p$; invia $(c_1,c_2)$.

**Decifratura (Alice):** $M = c_2 \cdot (c_1^\alpha)^{-1} \bmod p$, corretto perché $c_1^\alpha = g^{k\alpha} = \beta^k$.

#### Parte b — Esempio in $\mathbb{Z}_{11}$, $g=2$, $\alpha=6$, $M=5$, $k=3$ (15 punti)

> 📌 Verifica che $g=2$ sia generatore di $\mathbb{Z}_{11}^*$: $2^1=2,2^2=4,2^3=8,2^4=5,2^5=10,2^6=9,2^7=7,2^8=3,2^9=6,2^{10}=1$ — compaiono tutti i 10 elementi, quindi $2$ è radice primitiva.

**i. Chiave pubblica di Alice:** $\beta = g^\alpha = 2^6 \bmod 11$. Da sopra $2^6 = 9$. **$\beta = 9$**; chiave pubblica $(11, 2, 9)$.

**ii. Cifratura di $M=5$ con $k=3$:**
$$c_1 = g^k = 2^3 = 8 \pmod{11}$$
$$c_2 = M\cdot\beta^k = 5 \cdot 9^3 \bmod 11$$
$9^2 = 81 \equiv 4$, $9^3 = 9^2\cdot9 = 4\cdot9 = 36 \equiv 3 \pmod{11}$; quindi $c_2 = 5\cdot3 = 15 \equiv 4 \pmod{11}$.
**Ciphertext:** $C = (c_1, c_2) = (8, 4)$.

**iii. Decifratura:** $M = c_2 \cdot (c_1^\alpha)^{-1} \bmod 11$.
$$c_1^\alpha = 8^6 \bmod 11: \quad 8^2 = 64 \equiv 9,\; 8^3 = 8\cdot9 = 72 \equiv 6,\; 8^6 = (8^3)^2 = 6^2 = 36 \equiv 3$$
Inverso: $3^{-1} \equiv 4 \pmod{11}$ (poiché $3\cdot4=12\equiv1$). Quindi:
$$M = 4 \cdot 4 = 16 \equiv 5 \pmod{11}$$

**Messaggio recuperato:** $M = 5$ ✓.

---

## Pattern e osservazioni per l'esame

Appello della sessione estiva (luglio 2023).

- **D1 (25):** identica alla domanda "cifrari classici/attacchi" di altri appelli (cfr. 24/01/2023, 19/09/2025). Schema: classificare COA/KPA/CPA/CCA + tabella di resistenza.
- **D2 (25):** variante "speculare" del cifrario hash+XOR (cfr. 16/02/2023): decifrare invertendo nell'ordine giusto e mostrare malleabilità/recupero chiave sotto CPA.
- **D3 (25):** certificati digitali — funzione (legare identità↔chiave pubblica), ruolo della CA, esempio di scambio con verifica della firma della CA.
- **D4 (25):** El-Gamal numerico (stessi parametri base del 17/01/2024, qui $M=5,k=3$). Verificare il generatore, poi calcolare $\beta$, la coppia $(c_1,c_2)$ e la decifratura con $(c_1^\alpha)^{-1}$.
