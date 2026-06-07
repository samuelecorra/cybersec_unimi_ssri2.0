# Firma DSS e caso (a,0) — Esercizio d'esame

> 🗂️ **Fonte:** appello del **22 giugno 2023**, Domanda 4 — Modulo M5 (Firme Digitali).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 22 giugno 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L3%20-%20Appello%2022%20giugno%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — Firma digitale DSS (25 punti)

### Traccia originale

> **(25 punti) Firma digitale.**
>
> a. (10 punti) Descrivere le fasi di generazione e verifica della firma DSS.
>
> b. (5) Cosa succede se viene firmato lo stesso messaggio in due diverse occasioni? Qual è la differenza con RSA?
>
> c. (10 punti) Dimostrare che se si ottiene per un certo valore $a$ la firma $(a, 0)$, allora il sistema DSA potrebbe essere rotto.

### Prerequisiti teorici

- [`../../M5_Firme_Digitali/UD3/L1 - Schema DSS (Digital Signature Standard).md`](../../M5_Firme_Digitali/UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) — schema DSS
- [`../../M5_Firme_Digitali/UD3/L3 - Sicurezza dello schema DSS.md`](../../M5_Firme_Digitali/UD3/L3%20-%20Sicurezza%20dello%20schema%20DSS.md) — nonce e attacchi
- [`../../M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1 - DSA - firma (a,0) e caso delta=0.md`](../../M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1%20-%20DSA%20-%20firma%20%28a,0%29%20e%20caso%20delta=0.md) — caso firma $(a,0)$

### Soluzione dettagliata

#### Parte a — Generazione e verifica della firma DSS (10 punti)

**Parametri pubblici:** primo $p$ ($L$ bit), primo $q$ (160/256 bit) con $q \mid (p-1)$, generatore $g = h^{(p-1)/q}\bmod p$ del sottogruppo di ordine $q$, hash $H$. **Chiavi:** privata $x \in \{1,\ldots,q-1\}$, pubblica $y = g^x \bmod p$.

**Firma di $M$:**
1. Scegliere un **nonce** $k \in \{1,\ldots,q-1\}$ casuale e unico.
2. $r = (g^k \bmod p) \bmod q$ (se $r=0$ ripetere).
3. $s = k^{-1}\big(H(M) + x\,r\big) \bmod q$ (se $s=0$ ripetere).
4. Firma $= (r,s)$.

**Verifica** (con $y$): $w = s^{-1}\bmod q$; $u_1 = H(M)w \bmod q$; $u_2 = r w \bmod q$; $v = (g^{u_1}y^{u_2}\bmod p)\bmod q$. **Accetta** se $v = r$.

**Correttezza:** da $s=k^{-1}(H(M)+xr)$ segue $k = w(H(M)+xr) = u_1 + x u_2 \pmod q$, quindi $v = (g^{u_1+xu_2}\bmod p)\bmod q = (g^k \bmod p)\bmod q = r$.

#### Parte b — Stesso messaggio firmato due volte; confronto con RSA (5 punti)

DSS è **probabilistico**: dipende dal nonce $k$. Con $k$ diverso, due firme dello stesso $M$ sono **diverse** ($r$ dipende da $k$) ed entrambe valide, senza rivelare nulla. Se invece si **riusa $k$**, allora $r=r'$ e l'avversario calcola $k=(s-s')^{-1}(H(M)-H(M'))$ e poi $x = r^{-1}(sk-H(M))$, **compromettendo la chiave privata** (è l'attacco PS3/Sony del 2010).

**Differenza con RSA:** il textbook RSA è **deterministico** — firmare lo stesso $M$ dà sempre la stessa firma $\sigma = M^d \bmod n$, senza nonce. DSS invece produce firme diverse ad ogni esecuzione (grazie a $k$).

#### Parte c — La firma $(a,0)$ rompe DSA (10 punti)

Supponiamo che per un messaggio $M$ si ottenga una firma con **seconda componente nulla**, cioè $(r,s) = (a, 0)$.

Per definizione $s = k^{-1}\big(H(M) + x\,r\big) \bmod q$. Imponendo $s = 0$ e ricordando che $k^{-1} \neq 0 \pmod q$ (q primo, $k\neq0$):
$$k^{-1}\big(H(M) + x\,r\big) \equiv 0 \pmod q \;\Longrightarrow\; H(M) + x\,r \equiv 0 \pmod q$$

Con $r = a$ (noto e, in DSA, $\neq 0$ e quindi invertibile mod $q$), si risolve per la **chiave privata**:
$$\boxed{x \equiv -H(M)\,a^{-1} \pmod q}$$

Tutto ciò che compare a destra è pubblico ($H(M)$, $a$, $q$): chiunque osservi la firma $(a,0)$ del messaggio $M$ **ricava la chiave privata $x$** e può falsificare qualunque firma. Il sistema è completamente rotto.

> ⚠️ Per questo lo standard DSA impone esplicitamente di **scartare e rigenerare** la firma se $r = 0$ **o** $s = 0$: il caso $s=0$ degenera nella rivelazione della chiave. È strettamente legato al caso $\delta = H(M)+xr = 0$.
