# RSA vs fattorizzazione e ottimizzazioni — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 settembre 2023**, Domanda 3 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 settembre 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L8%20-%20Appello%2022%20settembre%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Cifratura asimmetrica RSA (20 punti)

### Traccia originale

> **(20 punti) Cifratura asimmetrica RSA.**
>
> a. (10) Discutere l'equivalenza o meno tra il problema RSA e la fattorizzazione.
>
> b. (10) Discutere i metodi per ottimizzare il calcolo esponenziale nella fase di cifratura.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L5 - Sicurezza di RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L5%20-%20Sicurezza%20di%20RSA.md) — RSA vs fattorizzazione
- [`../../M3_Cifrature_Asimmetriche/UD2/L4_1 - Computazioni in RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L4_1%20-%20Computazioni%20in%20RSA.md) — esponenziazione modulare efficiente

### Soluzione dettagliata

#### Parte a — Problema RSA vs fattorizzazione (10 punti)

Il **problema RSA**: dati $(n, e, c)$ con $c = m^e \bmod n$, trovare $m$ (cioè calcolare radici $e$-esime mod $n$). La **fattorizzazione**: dato $n=pq$, trovare $p, q$.

- **Fattorizzazione $\Rightarrow$ RSA:** se so fattorizzare $n$, calcolo $\varphi(n)=(p-1)(q-1)$, quindi $d = e^{-1}\bmod\varphi(n)$ e $m = c^d$. Quindi **rompere la fattorizzazione rompe RSA**. (E conoscere $\varphi(n)$ equivale a fattorizzare, via $p+q = n-\varphi(n)+1$.)
- **RSA $\Rightarrow$ fattorizzazione?** **Non è dimostrato.** Non si conosce una riduzione che, dato un oracolo che risolve il problema RSA per *ogni* $e$, fattorizzi $n$. È quindi *concepibile* (anche se ritenuto improbabile) che esista un metodo per estrarre radici $e$-esime senza fattorizzare. 

**Conclusione:** la fattorizzazione è **almeno tanto difficile** quanto il problema RSA (fattorizzare basta a rompere RSA), ma **l'equivalenza nelle due direzioni non è provata**: rompere RSA *potrebbe* essere più facile che fattorizzare. In pratica i migliori attacchi noti passano comunque dalla fattorizzazione, quindi la sicurezza si basa su di essa con chiavi adeguate (≥ 2048 bit).

#### Parte b — Ottimizzazione dell'esponenziazione (10 punti)

La cifratura $c = m^e \bmod n$ richiede esponenziazione modulare con esponenti/moduli grandi. Tecniche principali:

1. **Square-and-multiply (esponenziazione binaria):** invece di $e$ moltiplicazioni, si scorre la rappresentazione binaria di $e$: si eleva al quadrato ad ogni bit e si moltiplica per $m$ quando il bit è 1. Costo $O(\log e)$ moltiplicazioni modulari invece di $O(e)$.
2. **Esponente pubblico piccolo:** scegliere $e = 65537 = 2^{16}+1$ (primo, solo due bit a 1) rende la **cifratura** velocissima (17 moltiplicazioni). Non si sceglie $e$ piccolissimo come 3 senza padding per evitare attacchi.
3. **CRT per la decifratura:** la **decifratura** $m = c^d \bmod n$ (con $d$ grande) si accelera col **Teorema Cinese del Resto**: si calcolano $m_p = c^{d \bmod (p-1)} \bmod p$ e $m_q = c^{d \bmod (q-1)} \bmod q$ e si ricombinano. Lavorare modulo $p$ e $q$ (metà delle cifre) dà uno speedup teorico $\sim 4\times$.
4. **Riduzione di Montgomery:** evita le divisioni costose nelle riduzioni modulari ripetute, rendendo efficienti le moltiplicazioni mod $n$.

> 📌 Riassunto: $e$ piccolo + square-and-multiply per cifrare; CRT + Montgomery per decifrare.
