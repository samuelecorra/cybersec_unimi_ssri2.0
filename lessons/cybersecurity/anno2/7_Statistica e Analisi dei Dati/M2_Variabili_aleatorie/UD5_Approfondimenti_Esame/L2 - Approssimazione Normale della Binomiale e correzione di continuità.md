# **M2 UD5 Lezione 2 - Approssimazione Normale della Binomiale e correzione di continuità**

> ⚠️ **Unità integrativa d'esame.** La tecnica è richiesta esplicitamente nell'appello del [19/09/2025 (D2c, 4 punti)](../../M4_Esami_svolti/esami_2025/7_esame_19_settembre_2025_soluzione.md) — "è possibile, se le condizioni sono soddisfatte, utilizzare un'approssimazione" — ma **le videolezioni ufficiali non trattano né le condizioni operative né la correzione di continuità**. Questa lezione colma la lacuna.

### **1. Il problema**

Per $X \sim B(n, p)$ con $n$ grande, calcolare probabilità cumulate come

$$
P(8 \leq X \leq 15) = \sum_{k=8}^{15} \binom{200}{k} (0.05)^k (0.95)^{200-k}
$$

richiede la somma di molti termini binomiali, improponibile a mano in sede d'esame. La via d'uscita è **approssimare la Binomiale con una Gaussiana**.

### **2. Perché funziona: il Teorema del Limite Centrale**

La Binomiale è per costruzione una **somma di $n$ Bernoulliane i.i.d.**: $X = \sum_{i=1}^n X_i$ con $X_i \in \{0,1\}$. Il [Teorema del Limite Centrale](../../M3_Argomenti_avanzati/UD1/L6%20-%20Il%20Teorema%20del%20Limite%20Centrale.md) garantisce che, per $n$ grande, la somma si distribuisce approssimativamente come una Normale con gli stessi momenti:

$$
X \ \approx\ \mathcal{N}\big(\mu = np,\ \sigma^2 = np(1-p)\big)
$$

> 💡 Non serve "una nuova formula": i parametri della Normale approssimante sono semplicemente **media e varianza della Binomiale** di partenza.

### **3. Le condizioni di applicabilità (da verificare SEMPRE per iscritto)**

L'approssimazione è affidabile quando la Binomiale non è troppo asimmetrica, cioè quando il grosso della massa è lontano dai bordi $0$ e $n$. Regola operativa richiesta all'esame:

$$
np \geq 10 \qquad \text{e} \qquad n(1-p) \geq 10
$$

(in alcune fonti la soglia è 5: citare una delle due e verificarla numericamente).

> ⚠️ Nella domanda da 4 punti dell'appello 19/09/2025, la **verifica esplicita delle condizioni** vale punti quanto il calcolo: scrivere sempre "$np = 10 \geq 10$ ✓, $n(1-p) = 190 \geq 10$ ✓" prima di procedere.

Quadro completo delle approssimazioni della Binomiale:

| Regime | Approssimazione | Riferimento |
|---|---|---|
| $n$ grande, $p$ piccolo, $np = \mu$ moderato | **Poissoniana** $P(k \mid \mu)$ | [M2/UD3/L2](../UD3/L2%20-%20orma%20analitica%20della%20distribuzione%20di%20Poisson.md) |
| $np \geq 10$ e $n(1-p) \geq 10$ | **Normale** $\mathcal{N}(np,\ np(1-p))$ | questa lezione |

### **4. La correzione di continuità**

Qui sta il punto più delicato: stiamo approssimando una distribuzione **discreta** (che concentra massa sugli interi) con una **continua** (che assegna probabilità nulla ai punti). La convenzione corretta è pensare ogni intero $k$ come "spalmato" sull'intervallo $[k - 0.5,\ k + 0.5]$ e tradurre gli eventi discreti in intervalli continui **allargati di mezzo punto verso l'esterno**:

| Evento discreto | Evento continuo equivalente |
|---|---|
| $P(X = k)$ | $P(k - 0.5 < X_{\mathcal{N}} < k + 0.5)$ |
| $P(a \leq X \leq b)$ | $P(a - 0.5 < X_{\mathcal{N}} < b + 0.5)$ |
| $P(X \leq b)$ | $P(X_{\mathcal{N}} < b + 0.5)$ |
| $P(X < b)$, cioè $P(X \leq b-1)$ | $P(X_{\mathcal{N}} < b - 0.5)$ |
| $P(X \geq a)$ | $P(X_{\mathcal{N}} > a - 0.5)$ |
| $P(X > a)$, cioè $P(X \geq a+1)$ | $P(X_{\mathcal{N}} > a + 0.5)$ |

> 📌 Regola per non sbagliare mai il segno: **prima** riscrivere l'evento con disuguaglianze larghe ($\leq,\ \geq$) sugli interi, **poi** allargare di $0.5$ verso l'esterno dell'intervallo.

### **5. Procedura completa in quattro passi**

1. **Verificare le condizioni** $np \geq 10$, $n(1-p) \geq 10$.
2. **Calcolare i parametri** $\mu = np$, $\sigma = \sqrt{np(1-p)}$.
3. **Applicare la correzione di continuità** e **standardizzare**: $z = \frac{x \pm 0.5 - \mu}{\sigma}$.
4. **Usare le tavole** della Normale standard: $P(z_1 < Z < z_2) = \Phi(z_2) - \Phi(z_1)$, ricordando $\Phi(-z) = 1 - \Phi(z)$.

### **6. Esempio guidato (dall'appello del 19/09/2025)**

$X \sim B(200,\ 0.05)$: probabilità di avere tra 8 e 15 difettosi **inclusi**.

1. **Condizioni**: $np = 10 \geq 10$ ✓, $n(1-p) = 190 \geq 10$ ✓.
2. **Parametri**: $\mu = 10$, $\sigma = \sqrt{9.5} \approx 3.082$.
3. **Correzione e standardizzazione**: $P(8 \leq X \leq 15) \to P(7.5 < X_{\mathcal{N}} < 15.5)$;

$$
z_1 = \frac{7.5 - 10}{3.082} \approx -0.811 \qquad z_2 = \frac{15.5 - 10}{3.082} \approx 1.784
$$

4. **Tavole**: $\Phi(1.78) \approx 0.9625$, $\Phi(-0.81) = 1 - \Phi(0.81) \approx 0.2090$:

$$
P(8 \leq X \leq 15) \approx 0.9625 - 0.2090 = \boxed{0.7535}
$$

### **7. Errori tipici da esame**

> ⚠️ In ordine di frequenza:
>
> 1. **Omettere la correzione di continuità**: con $\sigma \approx 3$ l'errore è tutt'altro che trascurabile. Il valore binomiale **esatto** è $0.7423$; con la correzione si ottiene $0.7535$ (scarto $+0.011$), senza correzione si otterrebbe $\Phi(1.622) - \Phi(-0.649) \approx 0.6894$ (scarto $-0.053$, cinque volte peggiore).
> 2. **Allargare nel verso sbagliato** (usare $8.5$ e $14.5$): l'intervallo va allargato **verso l'esterno**, gli estremi inclusi devono restare dentro.
> 3. **Non verificare le condizioni** o non citarle: si perde il punteggio dedicato al metodo.
> 4. **Usare $\sigma^2$ al posto di $\sigma$** nella standardizzazione: controllare sempre l'ordine di grandezza di $z$ (valori "ragionevoli" tra $-4$ e $4$).

### **8. Sintesi finale**

| **Passo** | **Formula / Azione** |
|---|---|
| Condizioni | $np \geq 10$ e $n(1-p) \geq 10$ |
| Parametri | $\mu = np$, $\sigma = \sqrt{np(1-p)}$ |
| Continuità | intervalli discreti allargati di $\pm 0.5$ verso l'esterno |
| Standardizzazione | $z = (x \pm 0.5 - \mu)/\sigma$ |
| Tavole | $\Phi(z_2) - \Phi(z_1)$, con $\Phi(-z) = 1-\Phi(z)$ |
