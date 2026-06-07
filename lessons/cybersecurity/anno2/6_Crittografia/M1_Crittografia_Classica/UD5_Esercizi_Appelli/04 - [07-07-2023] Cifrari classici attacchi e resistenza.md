# Cifrari classici attacchi e resistenza — Esercizio d'esame

> 🗂️ **Fonte:** appello del **7 luglio 2023**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 7 luglio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L4%20-%20Appello%207%20luglio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

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
