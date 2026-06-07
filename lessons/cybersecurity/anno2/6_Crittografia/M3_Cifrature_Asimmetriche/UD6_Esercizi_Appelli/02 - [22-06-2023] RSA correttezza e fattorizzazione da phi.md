# RSA correttezza e fattorizzazione da phi — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 giugno 2023**, Domanda 3 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 giugno 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L3%20-%20Appello%2022%20giugno%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Cifratura asimmetrica RSA (25 punti)

### Traccia originale

> **(25 punti) Cifratura asimmetrica.**
>
> a. (10) Si dimostri la correttezza di RSA.
>
> b. (15) Si consideri il crittosistema RSA con $n = 1363$. Si è scoperto che $\varphi(n) = 1288$. Usare questa informazione per fattorizzare $n$.

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — schema RSA
- [`../../M3_Cifrature_Asimmetriche/UD2/L3 - Fattorizzazione e test di primalità.md`](../../M3_Cifrature_Asimmetriche/UD2/L3%20-%20Fattorizzazione%20e%20test%20di%20primalità.md) — fattorizzazione

### Soluzione dettagliata

#### Parte a — Correttezza di RSA (10 punti)

Setup: $n = pq$ ($p,q$ primi distinti), $\varphi(n) = (p-1)(q-1)$, esponente pubblico $e$ con $\gcd(e,\varphi(n))=1$, esponente privato $d \equiv e^{-1} \pmod{\varphi(n)}$. Cifratura $c = m^e \bmod n$, decifratura $m' = c^d \bmod n$. Va dimostrato $m' = m$.

Poiché $ed \equiv 1 \pmod{\varphi(n)}$, esiste un intero $t$ con $ed = 1 + t\,\varphi(n)$. Quindi
$$c^d = m^{ed} = m^{1 + t\varphi(n)} = m \cdot \big(m^{\varphi(n)}\big)^t \pmod n$$

- **Se $\gcd(m,n)=1$:** per il teorema di Eulero $m^{\varphi(n)} \equiv 1 \pmod n$, quindi $m^{ed} \equiv m \cdot 1^t = m$.
- **Caso generale (anche $\gcd(m,n)>1$):** si dimostra modulo $p$ e $q$ separatamente e si conclude col **Teorema Cinese del Resto**. Modulo $p$: se $p \nmid m$, per il piccolo teorema di Fermat $m^{p-1}\equiv1$, e $m^{ed} = m\cdot m^{t(p-1)(q-1)} \equiv m \pmod p$; se $p \mid m$ allora $m \equiv 0$ e $m^{ed}\equiv 0 \equiv m \pmod p$. In entrambi i casi $m^{ed}\equiv m \pmod p$, e analogamente $\pmod q$. Poiché $p,q$ sono coprimi, per il CRT $m^{ed} \equiv m \pmod{pq=n}$.

Quindi $c^d \equiv m \pmod n$: RSA è corretto. $\blacksquare$

#### Parte b — Fattorizzare $n=1363$ noto $\varphi(n)=1288$ (15 punti)

Conoscere $\varphi(n)$ insieme a $n$ permette di fattorizzare immediatamente. Da $n = pq$ e $\varphi(n) = (p-1)(q-1) = pq - (p+q) + 1$:

$$p + q = n - \varphi(n) + 1 = 1363 - 1288 + 1 = 76$$

Quindi $p, q$ sono le radici dell'equazione di secondo grado $x^2 - (p+q)x + pq = 0$:
$$x^2 - 76x + 1363 = 0$$
$$x = \frac{76 \pm \sqrt{76^2 - 4\cdot1363}}{2} = \frac{76 \pm \sqrt{5776 - 5452}}{2} = \frac{76 \pm \sqrt{324}}{2} = \frac{76 \pm 18}{2}$$

$$p = \frac{76 + 18}{2} = 47, \qquad q = \frac{76 - 18}{2} = 29$$

**Fattorizzazione:** $n = 1363 = 47 \times 29$. Verifica: $47\cdot29 = 1363$ ✓, $\varphi = 46\cdot28 = 1288$ ✓.

> ⚠️ Questo mostra che **calcolare $\varphi(n)$ è equivalente a fattorizzare $n$**: chi conosce $\varphi(n)$ rompe RSA. Per questo $\varphi(n)$ (e quindi $d$) deve restare segreto: la sua segretezza è la sicurezza stessa di RSA.
