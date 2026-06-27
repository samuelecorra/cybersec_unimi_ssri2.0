# Firma digitale DSS — Esercizio d'esame

> 🗂️ **Fonte:** appello del **24 giugno 2024**, Domanda 4 — Modulo M5 (Firme Digitali).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 24 giugno 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L3%20-%20Appello%2024%20giugno%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Firma digitale DSS (20 punti)

### Traccia originale

> **(20 punti) Firma digitale.**
>
> a. (10 punti) Descrivere le fasi di generazione e verifica della firma DSS.
>
> b. (10 punti) Dimostrare che in caso si ottenga per un certo valore $a$ la firma $(a, 0)$, allora il sistema DSA potrebbe essere rotto.

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — DSS completo

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

**Parametri del sistema (si rimanda all'Appello L2 per descrizione dettagliata):**
- $p$: primo grande; $q$: primo divisore di $p-1$, $|q|=160$ bit
- $g$: generatore del sottogruppo di ordine $q$ in $\mathbb{Z}_p^*$
- $x$: chiave privata, $0 < x < q$; $y = g^x \bmod p$: chiave pubblica

**Generazione della firma di $m$:**
1. Scegliere $k$ casuale con $0 < k < q$.
2. Calcolare $r = (g^k \bmod p) \bmod q$. Se $r = 0$: rifare con nuovo $k$.
3. Calcolare $s = k^{-1}(H(m) + xr) \bmod q$. Se $s = 0$: rifare con nuovo $k$.
4. Firma: $(r, s)$.

**Verifica della firma $(r, s)$ di $m$:**
1. Verificare $0 < r < q$ e $0 < s < q$.
2. $w = s^{-1} \bmod q$
3. $u_1 = H(m) \cdot w \bmod q$
4. $u_2 = r \cdot w \bmod q$
5. $v = (g^{u_1} \cdot y^{u_2} \bmod p) \bmod q$
6. Firma valida $\iff v = r$.

#### Parte b — Firma $(a, 0)$ rompe DSA (10 punti)

Sia la firma del messaggio $m$ la coppia $(r, s) = (a, 0)$ con $a \neq 0$.

**La componente $s$ è definita come:**

$$s = k^{-1}(H(m) + xr) \bmod q$$

Avere $s = 0$ significa:

$$k^{-1}(H(m) + xr) \equiv 0 \pmod{q}$$

Poiché $k^{-1}$ esiste (è invertibile modulo $q$ primo), possiamo moltiplicare entrambi i lati per $k$:

$$H(m) + xr \equiv 0 \pmod{q}$$

$$xr \equiv -H(m) \pmod{q}$$

Ora $r = a \neq 0$. Poiché $q$ è primo e $0 < r < q$, si ha $\operatorname{mcd}(r, q) = 1$, quindi $r$ è invertibile modulo $q$. Moltiplicando entrambi i lati per $r^{-1} \bmod q$:

$$x \equiv -H(m) \cdot r^{-1} \pmod{q}$$

**L'avversario può calcolare $x$ direttamente:**

Conoscendo $(r, s) = (a, 0)$, $m$ (o $H(m)$), e i parametri pubblici $p, q, g, y$, un avversario può:
1. Calcolare $r^{-1} \bmod q$ (operazione banale con Euclide esteso).
2. Calcolare $H(m)$.
3. Calcolare $x = -H(m) \cdot r^{-1} \bmod q$.

Questo rivela la chiave privata $x$, compromettendo l'intero sistema: l'avversario può ora generare firme valide per qualsiasi messaggio.

> ✅ **Conclusione:** la firma $(a, 0)$ espone direttamente la chiave privata $x$ tramite una semplice operazione aritmetica modulare. Pertanto il protocollo DSA richiede che se $s = 0$ (o $r = 0$), la firma venga scartata e rigenerata con un nuovo $k$ casuale.
