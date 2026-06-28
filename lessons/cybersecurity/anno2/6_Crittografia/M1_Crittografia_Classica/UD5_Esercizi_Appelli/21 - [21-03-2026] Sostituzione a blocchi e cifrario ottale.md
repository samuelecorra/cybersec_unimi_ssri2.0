# Sostituzione a blocchi e cifrario ottale — Esercizio d'esame

> 🗂️ **Fonte:** appello del **21 marzo 2026**, Domanda 1 — Modulo M1 (Crittografia Classica).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 21 marzo 2026](../../M7_Appelli_Svolti/UD4_Anno_2026/L3%20-%20Appello%2021%20marzo%202026.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 1 — Cifratura simmetrica a sostituzione a blocchi (20 punti)

### Traccia originale

> **(20 punti) Cifratura simmetrica.**
>
> a. (5 punti) Discutere: *"In un generico algoritmo di cifratura a sostituzione a blocchi di $n$ bit, le dimensioni della chiave sono $n \cdot 2^n$"*.
>
> b–c. (5 punti) Il cifrario opera su blocchi binari di 3 bit, converte in ottale, applica un mapping (identità: $0\to0,\ldots,7\to7$) e restituisce blocchi di 3 bit. Quanti cifrari diversi con mapping diverso?
>
> d. (10 punti) Alfabeto: R=0, O=1, L=2, P=3, S=4, T=5, A=6, E=7. Cifrare "PROLE". Considerazioni sulla robustezza.

### Prerequisiti teorici

- [`../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md`](../../M1_Crittografia_Classica/UD2/L3%20-%20Cifrari%20a%20sostituzione.md) — sostituzione su blocchi binari e dimensione chiave

### Soluzione dettagliata

#### Parte a — Affermazione sulla dimensione della chiave (5 punti)

L'affermazione è **corretta**. Un cifrario a sostituzione su $n$ bit è una permutazione di $\{0,1\}^n$, specificata da una tabella con:
- $2^n$ voci (una per ciascun possibile plaintext)
- $n$ bit per voce (l'output corrispondente)

$$\text{Dimensione chiave} = n \cdot 2^n \text{ bit}$$

Per $n=3$: $3 \cdot 8 = 24$ bit. Per $n=8$: $8 \cdot 256 = 2048$ bit. Per $n=64$: $64 \cdot 2^{64} \approx 10^{21}$ byte — impraticabile senza struttura algebrica.

#### Parte c — Quanti cifrari diversi (5 punti)

Il mapping su $\{0,\ldots,7\}$ è una permutazione di 8 elementi:

$$8! = 40320 \text{ cifrari possibili}$$

Il mapping identità (mostrato nella traccia) è uno solo di questi 40320.

#### Parte d — Cifratura di "PROLE" e robustezza (10 punti)

PROLE = P(3), R(0), O(1), L(2), E(7).

**Con mapping identità** ($i \to i$): ogni lettera mappa su se stessa.

| Lettera | Decimale | Mapping | Output |
|---|---|---|---|
| P | 3 | 3→3 | P |
| R | 0 | 0→0 | R |
| O | 1 | 1→1 | O |
| L | 2 | 2→2 | L |
| E | 7 | 7→7 | E |

**Ciphertext: "PROLE"** (identico al plaintext).

**Considerazioni sulla robustezza:**

1. **Con mapping identità:** sicurezza nulla — il ciphertext è il plaintext.
2. **Con mapping casuale segreto (caso generale):**
   - Vulnerabile all'**analisi delle frequenze**: l'alfabeto ha solo 8 simboli, le frequenze si preservano nel ciphertext.
   - Vulnerabile a **KPA**: 8 coppie note ricostruiscono completamente la permutazione (la chiave).
   - Vulnerabile a **CPA**: cifrare tutti gli 8 simboli con una query rivela immediatamente l'intera tabella.
   - **Spazio delle chiavi** esauribile: $8! = 40320 \approx 2^{15{,}3}$ — brute-force in frazioni di secondo.

> ⚠️ Il cifrario è equivalente a una sostituzione monoalfabetica classica su 8 simboli. Ha esattamente le stesse vulnerabilità dei cifrari classici, con in più uno spazio delle chiavi ancor più piccolo (8! vs 26! per l'alfabeto latino).
