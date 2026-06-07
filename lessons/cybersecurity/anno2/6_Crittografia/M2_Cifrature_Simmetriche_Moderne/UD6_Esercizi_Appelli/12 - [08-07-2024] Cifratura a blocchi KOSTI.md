# Cifratura a blocchi KOSTI — Esercizio d'esame

> 🗂️ **Fonte:** appello del **8 luglio 2024**, Domanda 1 — Modulo M2 (Cifrature Simmetriche Moderne).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 8 luglio 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L5%20-%20Appello%208%20luglio%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura simmetrica a blocchi e cifratura "KOSTI" (35 punti)

### Traccia originale

> **(35 punti) Cifratura simmetrica.**
>
> a. (10 punti) Discutere la seguente affermazione: "In un generico algoritmo di cifratura a sostituzione a blocchi di $n$ bit, le dimensioni della chiave sono $n \cdot 2^n$".
>
> b. (10 punti) Si consideri il seguente cifrario:
> [cifrario che prende blocchi binari di 3 bit, esegue una conversione in ottale, esegue un mapping $\pi$, e restituisce blocchi binari di 3 bit]. Si indichino le caratteristiche del cifrario, specificando quanti possibili cifrari diversi è possibile ottenere cambiando il mapping.
>
> c. (15 punti) Si abbia un linguaggio con 8 lettere: A, B, I, K, M, O, S, T dove si usa la rappresentazione decimale A = 0, B = 1, I = 2, K = 3, M = 4, O = 5, S = 6, T = 7. Per cifrare una lettera in questo linguaggio, si converte la lettera in forma binaria, si applica lo schema in figura [il mapping $\pi$], e si ottiene una nuova lettera corrispondente. Cifrare la parola "KOSTI". Si faccia qualche considerazione sulla robustezza di questo cifrario.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — spazio delle chiavi, sostituzione su blocchi
- [`../../M2_Cifrature_Simmetriche_Moderne/UD1/L1 - Cifrature a blocchi – introduzione.md`](../../M2_Cifrature_Simmetriche_Moderne/UD1/L1%20-%20Cifrature%20a%20blocchi%20–%20introduzione.md) — cifrari a sostituzione generali

### Soluzione dettagliata

#### Parte a — Dimensioni della chiave in un cifrario a sostituzione su $n$ bit (10 punti)

**Analisi dell'affermazione "le dimensioni della chiave sono $n \cdot 2^n$":**

Un cifrario a sostituzione generico su blocchi di $n$ bit è una **permutazione** dell'insieme $\{0,1\}^n$ di $2^n$ elementi. Per specificare completamente quale delle $(2^n)!$ permutazioni si sta usando, si può adottare la rappresentazione tabellare.

**La tabella di sostituzione** ha:
- $2^n$ righe (una per ogni possibile input)
- Ogni riga contiene il valore di output, che è un blocco di $n$ bit

Quindi la tabella occupa $2^n \times n$ bit $= n \cdot 2^n$ bit.

**L'affermazione è corretta in un senso preciso:**

$n \cdot 2^n$ bit è la dimensione della **rappresentazione tabellare** del cifrario (la "chiave" intesa come tabella completa di sostituzione). Questa è la quantità di informazione necessaria per memorizzare completamente il cifrario.

**Tuttavia, questa non è la dimensione della chiave nel senso tradizionale:**

La chiave ideale dovrebbe essere un indice compatto che identifica una delle $(2^n)!$ permutazioni. La dimensione ottimale della chiave sarebbe $\lceil \log_2((2^n)!) \rceil$ bit. Per $n$ grande:

$$\log_2((2^n)!) \approx 2^n \cdot n - 2^n \cdot \log_2 e \approx n \cdot 2^n$$

(per la formula di Stirling: $\log(m!) \approx m\log m - m\log e$). Quindi asintoticamente le due misure coincidono, ma la tabella è una rappresentazione ridondante (include elementi dell'output direttamente, non solo l'indice della permutazione).

**Esempio con $n = 3$:**

- Blocchi: $2^3 = 8$ possibili valori (da 000 a 111)
- Dimensione tabella: $3 \times 8 = 24$ bit
- Numero di permutazioni: $8! = 40320$
- Bit per identificare una permutazione: $\lceil \log_2(40320) \rceil = 16$ bit

La tabella occupa 24 bit ma basterebbero 16 bit per indicizzare la permutazione desiderata.

**Conclusione:** l'affermazione è sostanzialmente corretta per la rappresentazione tabellare della chiave. La dimensione della tabella è $n \cdot 2^n$ bit, che cresce molto rapidamente con $n$ (es. per $n=64$: circa $10^{21}$ bit), rendendo i cifrari a sostituzione generali impraticabili per $n$ grande. Questo è il motivo per cui i cifrari moderni usano strutture parametrizzate (S-box di dimensioni fisse) invece di tabelle generali.

> 📌 Questa discussione è alla base della **teoria delle chiavi in crittografia**: la lunghezza della chiave determina lo spazio dei cifrari possibili. Per $n=64$ (blocchi DES), $(2^{64})!$ è astronomicamente più grande di $2^{56}$ (lo spazio delle chiavi DES), il che significa che DES esplora una frazione microscopica dei possibili cifrari a sostituzione su 64 bit.

#### Parte b — Caratteristiche del cifrario su 3 bit con mapping $\pi$ (10 punti)

**Struttura del cifrario:**

Il cifrario opera su blocchi di **3 bit**. Il processo è:
1. Input: blocco di 3 bit (da 000 a 111, cioè valori ottali da 0 a 7)
2. Conversione in ottale: ogni blocco di 3 bit corrisponde a una cifra ottale (0-7)
3. Applicazione del mapping $\pi$: una permutazione di $\{0,1,2,3,4,5,6,7\}$
4. Output: il risultato del mapping riconvertito in 3 bit

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Caratteristiche del cifrario:**

- **Dimensione del blocco:** 3 bit
- **Spazio dell'input:** $2^3 = 8$ valori possibili (0-7 in ottale)
- **Struttura:** cifrario a sostituzione semplice (monoalfabetico sui simboli ottali)
- **Chiave:** la permutazione $\pi$ dei simboli $\{0,1,2,3,4,5,6,7\}$
- **Invertibilità:** poiché $\pi$ è una permutazione (biiezione), il cifrario è sempre invertibile.

**Numero di cifrari diversi:**

Cambiando il mapping $\pi$, si può scegliere qualsiasi permutazione dell'insieme $\{0,1,2,3,4,5,6,7\}$. Il numero di permutazioni di 8 elementi è:

$$8! = 8 \times 7 \times 6 \times 5 \times 4 \times 3 \times 2 \times 1 = 40320$$

Quindi esistono **40320 possibili cifrari diversi**, ognuno corrispondente a una diversa permutazione dei simboli ottali.

**Dimensione della chiave:** Per identificare uno dei 40320 mapping, servono $\lceil \log_2(40320) \rceil = 16$ bit. La tabella completa occupa $3 \times 8 = 24$ bit.

#### Parte c — Cifratura della parola "KOSTI" (15 punti)

**Codifica delle lettere:**

| Lettera | Valore decimale | Valore binario (3 bit) |
|---------|----------------|------------------------|
| A | 0 | 000 |
| B | 1 | 001 |
| I | 2 | 010 |
| K | 3 | 011 |
| M | 4 | 100 |
| O | 5 | 101 |
| S | 6 | 110 |
| T | 7 | 111 |

**La parola "KOSTI" in binario:**

| Lettera | Bin |
|---------|-----|
| K | 011 |
| O | 101 |
| S | 110 |
| T | 111 |
| I | 010 |

> ⚠️ Il mapping $\pi$ specifico dipende dalla figura/schema della traccia, che non è visivamente disponibile nel PDF (riferisce a "lo schema in figura"). Dalla struttura della domanda e dal contesto del corso, il cifrario proposto è probabilmente una permutazione fissa dei 3 bit o una S-box semplice fornita in figura.

**Applicando un mapping $\pi$ di esempio** (mapping identità per illustrare il procedimento, da sostituire con il mapping effettivo della figura):

Il procedimento generale è:
1. Convertire ogni lettera nel suo valore binario a 3 bit.
2. Applicare il mapping $\pi$ (prendere il valore ottale, leggere il corrispondente dalla tabella di $\pi$).
3. Riconvertire il risultato nella lettera corrispondente.

**Considerazioni sulla robustezza:**

Il cifrario è **estremamente debole** per i seguenti motivi:

1. **Sostituzione monoalfabetica su 8 simboli:** lo stesso simbolo viene sempre cifrato nello stesso modo. Un'analisi delle frequenze sulle lettere del ciphertext, confrontata con le frequenze dell'alfabeto di 8 lettere, permette di identificare rapidamente il mapping.

2. **Blocco troppo piccolo (3 bit = 8 simboli):** con soli 8 possibili valori di input, c'è un numero molto ridotto di combinazioni. L'intero spazio $\{0,1\}^3$ può essere esplorato in pochi istanti.

3. **Spazio delle chiavi ridottissimo:** anche se ci sono 40320 possibili permutazioni, un attacco a forza bruta che prova tutte le permutazioni richiede al massimo 40320 tentativi, il che è computazionalmente banale.

4. **Nessuna diffusione:** ogni lettera è cifrata indipendentemente. Non c'è dipendenza tra i caratteri del messaggio e del ciphertext.

5. **Vulnerabilità a known plaintext:** se si conosce anche solo una coppia (plaintext-lettera, ciphertext-lettera), si rivela il mapping per quel simbolo. Con 8 coppie note si recupera l'intero mapping.

> ✅ Questo cifrario è utile solo per scopi didattici, per illustrare il concetto di sostituzione su blocchi. Non ha alcuna sicurezza crittografica pratica.
