# **L2 - Formulario M2: Variabili aleatorie e distribuzioni**

> 📌 Copre [M2 UD1-UD5](../M2_Variabili_aleatorie/). Argomenti: distribuzioni e densità, cumulativa e anticumulativa, indicatori di posizione e ampiezza, tutte le distribuzioni notevoli (Bernoulli, uniforme, binomiale, geometrica, Poisson, ipergeometrica, esponenziale, normale) e le approssimazioni fra loro. **È il modulo con più esercizi di calcolo negli appelli.**

---

### **1. Discreto vs continuo: lo schema di riferimento**

| | **Discreto** | **Continuo** |
|---|---|---|
| Legge | distribuzione $P(k)$ | densità $f(x)$ |
| Normalizzazione | $\sum_k P(k)=1$ | $\int_{-\infty}^{+\infty} f(x)\,dx = 1$ |
| Cumulativa | $F(r)=\sum_{k\le r}P(k)$ | $F(x)=\int_{-\infty}^{x}f(t)\,dt$ |
| Anticumulativa | $S(r)=\sum_{k>r}P(k)$ | $S(x)=\int_x^{+\infty}f(t)\,dt$ |
| Media | $\mu=\sum_k k\,P(k)$ | $\mu=\int x\,f(x)\,dx$ |
| Momento $r$-esimo | $\langle k^r\rangle=\sum_k k^r P(k)$ | $\langle x^r\rangle=\int x^r f(x)\,dx$ |
| Varianza | $\sigma^2=\sum_k (k-\mu)^2P(k)$ | $\sigma^2=\int (x-\mu)^2 f(x)\,dx$ |
| $P(X=x)$ | può essere $>0$ | **sempre $0$** |

**Relazioni sempre valide:** $\;F(x)+S(x)=1\;$ e $\;f(x)=F'(x)=-S'(x)\;$ e $\;F(x)=\int f$.

$$\boxed{\;\sigma^2 = \langle x^2\rangle - \langle x\rangle^2 = E[X^2]-(E[X])^2\;}$$

> 💡 È **la** formula di calcolo della varianza: quasi sempre più rapida della definizione con gli scarti.

---

### **2. Schema delle quattro famiglie fondamentali**

| Domanda | Discreto | Continuo |
|---|---|---|
| **Quanto aspetto** prima del primo successo? | **Geometrica** $G(i\mid p)=q^{i-1}p$ | **Esponenziale** $f(t)=\lambda e^{-\lambda t}$ |
| **Quanti eventi** in $n$ prove / in $\Delta t$? | **Binomiale** $B(k\mid n,p)$ | **Poisson** $P(k\mid \mu=\lambda\Delta t)$ |

Ponti fra le famiglie:

$$\text{Ipergeom.}\xrightarrow[K/N=p]{N\to\infty}\text{Binomiale}\xrightarrow[np=\mu]{n\to\infty,\,p\to0}\text{Poisson} \qquad\qquad \text{Binomiale}\xrightarrow[np,\,n(1-p)\ge10]{}\text{Normale}$$

$$\text{Geometrica}\xrightarrow[\lambda=-\ln q]{\text{infittimento}}\text{Esponenziale} \qquad\qquad \mu = \lambda\,\Delta t$$

---

### **3. Densità continua con costante $C$ da determinare**

#### **3.1. Quando usarla**

La traccia assegna $f(x)=C\cdot g(x)$ su un intervallo, $0$ altrove, e chiede $C$, la cumulativa, una probabilità, la mediana o la varianza. **Compare in 3 appelli su 8** (15/01, 05/07, 05/09/2025).

#### **3.2. Condizioni perché $f$ sia una densità valida**

$$f(x)\ge 0 \ \ \forall x \qquad\text{e}\qquad \int_{-\infty}^{+\infty} f(x)\,dx = 1$$

#### **3.3. Procedura d'esame**

1. **Costante:** imporre $\int_a^b C\,g(x)\,dx = 1 \Rightarrow C = \dfrac{1}{\int_a^b g(x)\,dx}$. Verificare anche $g\ge 0$ sull'intervallo (vale mezzo punto: si scrive "inoltre $g(x)\ge0$ su $[a,b]$, quindi la densità è ben definita").
2. **Cumulativa:** $F(x)=\int_a^x f(t)\,dt$, scritta **a tratti** ($0$ prima di $a$, l'integrale in mezzo, $1$ dopo $b$). Verifica: $F(a)=0$, $F(b)=1$.
3. **Probabilità di un intervallo:** $P(a'<X<b')=F(b')-F(a')$ oppure $\int_{a'}^{b'}f$. Coda destra: $P(X>c)=1-F(c)$.
4. **Mediana:** risolvere $F(m)=\frac12$.
5. **Media e varianza:** $E[X]=\int x f$, $E[X^2]=\int x^2 f$, $\mathrm{Var}=E[X^2]-(E[X])^2$.

#### **3.4. Tre esempi svolti (dagli appelli)**

| Traccia | $C$ | $F(x)$ sul dominio | Risultati chiesti |
|---|---|---|---|
| $f=cx$ su $[0,2]$ (15/01/25) | $\int_0^2 cx=2c=1\Rightarrow c=\frac12$ | $x^2/4$ | $P(1\le X\le1.5)=\frac{1.25}{4}=0.3125$; mediana $=\sqrt2$; $E[X]=\frac43$, $E[X^2]=2$, $\mathrm{Var}=\frac29$ |
| $f=Cx^2$ su $[0,3]$ (05/07/25) | $C\cdot 9=1\Rightarrow C=\frac19$ | $x^3/27$ | $P(1<X\le2)=\frac{8-1}{27}=\frac{7}{27}\approx0.2593$ |
| $f=C(4x-2x^2)$ su $(0,2)$ (05/09/25) | $C\cdot\frac83=1\Rightarrow C=\frac38$ | $\frac{x^2(3-x)}{4}$ | $P(X>1)=1-F(1)=1-\frac12=\frac12$ |

> ⚠️ **Errori tipici.** (1) Dimenticare i tratti $0$ e $1$ della cumulativa. (2) Calcolare $E[X]$ come $\int f$ invece di $\int x f$. (3) Usare $\left(\int x f\right)^2$ al posto di $\int x^2 f$ nel momento secondo. (4) Nel continuo, preoccuparsi se l'estremo è incluso: è **irrilevante**, $P(X=a)=0$.

---

### **4. Indicatori di posizione e ampiezza (di una distribuzione teorica)**

#### **4.1. Posizione**

| Indicatore | Definizione | Note |
|---|---|---|
| **Moda** | punto di massimo di $P(k)$ o $f(x)$ | può non esistere (uniforme) o essere multipla |
| **Mediana** | $F(x_{\text{med}})=\dfrac12$, cioè $F=S$ | **sempre** definita |
| **Media** | $\mu=\sum k P(k)$ o $\int x f(x)dx$ | può divergere (Cauchy) |

Per distribuzioni **simmetriche**: media $=$ mediana $=$ moda. In generale, con coda a destra: moda $<$ mediana $<$ media.

Sensibilità: **moda** bassa, **mediana** media (robusta agli outlier), **media** alta (trascinata dagli estremi).

#### **4.2. Quartili, percentili, quantili**

$$F(Q_1)=0.25 \qquad F(Q_2)=F(x_{\text{med}})=0.50 \qquad F(Q_3)=0.75 \qquad \text{IQR}=\Delta=Q_3-Q_1$$

Quantile di ordine $p$: il valore $x_p$ tale che $F(x_p)=p$. Il $90^\circ$ percentile è $x_{0.90}$.

#### **4.3. Ampiezza**

$$\sigma^2=\langle x^2\rangle-\langle x\rangle^2 \qquad \sigma=\sqrt{\sigma^2} \qquad \text{IQR}=Q_3-Q_1$$

**Momenti centrali:** $\langle (x-\mu)^r\rangle$. Ordine 1 $=0$; ordine 2 $=$ varianza; ordine 3 $\Rightarrow$ **asimmetria** (skewness); ordine 4 $\Rightarrow$ **curtosi**.

**Chebyshev:** $P(|x-\mu|<k\sigma)\ge 1-\frac{1}{k^2}$ (vale per **qualunque** distribuzione).

---

### **5. Bernoulli**

**Scenario:** un solo esperimento con esito successo (1) / fallimento (0).

$$P(0)=q=1-p \qquad P(1)=p$$

| Grandezza | Valore |
|---|---|
| Supporto | $\{0,1\}$ |
| Media | $\mu=p$ |
| Varianza | $\sigma^2=p(1-p)=pq$ |
| Dev. standard | $\sigma=\sqrt{pq}$ |
| Moda e mediana | $1$ se $p>\frac12$, $0$ se $p<\frac12$ |

> 💡 È il mattone di tutto: la **binomiale** è la somma di $n$ Bernoulli i.i.d.; la **frequenza campionaria** $k/n$ è la media di $n$ Bernoulli, ed è per questo che l'errore standard di una proporzione è $\sqrt{p(1-p)/n}$ ([L3 §7](L3%20-%20M3%20Formulario%20-%20Somme,%20campionamento%20e%20inferenza.md)).

---

### **6. Uniforme**

#### **6.1. Discreta** ($m$ valori equiprobabili)

$$P(k)=\frac{1}{m}$$

#### **6.2. Continua su $[a,b]$**

$$f(x)=\begin{cases}\dfrac{1}{b-a} & a\le x\le b\\[4pt] 0 & \text{altrove}\end{cases} \qquad F(x)=\begin{cases}0 & x<a\\[2pt] \dfrac{x-a}{b-a} & a\le x\le b\\[4pt] 1 & x>b\end{cases}$$

| Grandezza | Valore |
|---|---|
| Media = mediana | $\dfrac{a+b}{2}$ |
| Moda | non definita (densità piatta) |
| Varianza | $\dfrac{(b-a)^2}{12}$ |
| Dev. standard | $\dfrac{b-a}{\sqrt{12}}$ |
| Quartili | $Q_1=a+\frac{b-a}{4}$, $Q_3=a+\frac{3(b-a)}{4}$, IQR $=\frac{b-a}{2}$ |

---

### **7. Binomiale**

#### **7.1. Quando usarla**

*"Numero di successi in $n$ prove indipendenti"*, con probabilità di successo $p$ **costante**: controllo qualità su un lotto grande, lanci di moneta, test a risposta multipla, componenti prodotti indipendentemente.

#### **7.2. Formula principale**

$$\boxed{\;P(X=k)=\binom{n}{k}p^k q^{n-k},\qquad k=0,1,\dots,n,\quad q=1-p\;}$$

#### **7.3. Parametri e momenti**

| Simbolo | Significato | Momenti |
|---|---|---|
| $n$ | numero di prove | $\mu=np$ |
| $p$ | probabilità di successo per prova | $\sigma^2=npq=np(1-p)$ |
| $k$ | numero di successi osservati | $\sigma=\sqrt{npq}$ |

**Moda:** l'intero $k$ con $p(n+1)-1\le k\le p(n+1)$. **Normalizzazione:** $\sum_k \binom{n}{k}p^kq^{n-k}=(p+q)^n=1$.

#### **7.4. Shortcut d'esame**

$$P(X\ge 1)=1-P(X=0)=1-q^n \qquad P(X>k)=1-\sum_{i=0}^{k}P(i) \qquad P(X\le k)=\sum_{i=0}^{k}P(i)$$

Casi piccoli da riconoscere al volo: $n=2$: $q^2,\,2pq,\,p^2$. $n=3$: $q^3,\,3pq^2,\,3p^2q,\,p^3$.

**Somma di binomiali con lo stesso $p$:** $B(n,p)+B(m,p)=B(n+m,p)$.

#### **7.5. Mini esempio (23/07/2025 D1)**

$X\sim B(20,0.05)$: $\;P(X=2)=\binom{20}{2}(0.05)^2(0.95)^{18}=190(0.0025)(0.3972)\approx 0.1887$; $\;E[X]=1$; $\;\sigma=\sqrt{0.95}\approx0.975$;
$P(X>3)=1-[P(0)+P(1)+P(2)+P(3)]=1-0.9841=0.0159$.

> ⚠️ Con $n$ grande la somma diventa impraticabile: passare a **Poisson** (se $p$ piccolo, §9) o alla **normale** (se $np$ e $n(1-p)\ge10$, §13). La traccia dice esplicitamente *"se le condizioni sono soddisfatte, utilizzare un'approssimazione"*.

---

### **8. Geometrica**

#### **8.1. Quando usarla**

*"Numero di tentativi fino al primo successo"*, prove indipendenti con $p$ costante: chiamate a un centralino fino a quella accettata, roulette russa, tentativi fino al primo guasto.

#### **8.2. Formule**

$$\boxed{\;P(i)=G(i\mid p)=q^{i-1}p,\qquad i=1,2,3,\dots\;}$$

| Grandezza | Formula |
|---|---|
| Cumulativa (fallibilità) | $F(i)=P(X\le i)=1-q^i$ |
| Anticumulativa (sopravvivenza) | $S(i)=P(X>i)=q^i$ |
| Media | $\mu=\dfrac{1}{p}$ |
| Varianza | $\sigma^2=\dfrac{q}{p^2}$ |
| Dev. standard | $\sigma=\dfrac{\sqrt q}{p}$ |
| Moda | $i=1$ (sempre) |
| Mediana | $i_{\text{med}}=-\dfrac{\log 2}{\log q}$ (poi arrotondare all'intero) |

**Memoryless:** $P(X>s+t\mid X>s)=P(X>t)$.

#### **8.3. Mini esempio**

Chiamate accettate con $p=0.02$: prima accettata alla decima $\Rightarrow (0.98)^9(0.02)$; più di 5 chiamate $\Rightarrow S(5)=(0.98)^5$; numero medio $\Rightarrow 1/0.02=50$.

> 📌 **Mai comparsa esplicitamente negli 8 appelli disponibili**, ma è nel programma e la sua struttura ("$q^{i-1}p$, media $1/p$") è la controparte discreta dell'esponenziale, che invece compare spesso: va comunque saputa.

---

### **9. Poisson**

#### **9.1. Quando usarla**

*"Numero di eventi in un intervallo di tempo/spazio con tasso medio costante"*, eventi indipendenti e rari: chiamate a un call center in un'ora, decadimenti radioattivi, guasti al giorno, forature ogni $x$ km. **Anche** come approssimazione della binomiale con $n$ grande e $p$ piccolo.

#### **9.2. Formula principale**

$$\boxed{\;P(X=k)=\frac{\mu^k e^{-\mu}}{k!},\qquad k=0,1,2,\dots\;}$$

#### **9.3. Parametri e momenti**

| Simbolo | Significato |
|---|---|
| $\mu$ (o $\lambda$) | **numero medio** di eventi nell'intervallo considerato |
| $\mu=np$ | se nasce come limite della binomiale |
| $\mu=\lambda\,\Delta t$ | se nasce da un processo con **tasso** $\lambda$ osservato per $\Delta t$ |

$$\mu = \sigma^2 \qquad \sigma=\sqrt{\mu} \qquad k_{\text{moda}}=\lfloor\mu\rfloor$$

> ⚠️ **Media e varianza coincidono**: è la firma della Poisson. Se un esercizio dà media e varianza diverse, non è una Poisson.

#### **9.4. Shortcut d'esame**

$$P(X=0)=e^{-\mu} \qquad P(X\ge 1)=1-e^{-\mu} \qquad P(X\le 2)=e^{-\mu}\!\left(1+\mu+\frac{\mu^2}{2}\right)$$

$$P(X\le k)=e^{-\mu}\sum_{i=0}^{k}\frac{\mu^i}{i!} \qquad P(X\ge 2)=1-e^{-\mu}(1+\mu)$$

Valori di $e^{-\mu}$ ricorrenti: $e^{-1}=0.3679$, $e^{-2}=0.1353$, $e^{-2.5}=0.08208$, $e^{-4}=0.01832$, $e^{-5}=0.006738$.

#### **9.5. Condizioni per approssimare la binomiale**

$$n\to\infty,\quad p\to 0,\quad np=\mu \text{ finito} \qquad\text{in pratica: } n\ge 50 \text{ e } p\le 0.1$$

#### **9.6. Proprietà dei processi di Poisson**

- **Merging:** somma di processi indipendenti ⇒ ancora Poisson con $\lambda=\lambda_1+\lambda_2$.
- **Splitting:** selezionando gli eventi con probabilità $p$ ⇒ due Poisson indipendenti con $p\lambda$ e $(1-p)\lambda$.
- Somma di v.a.: $\mathrm{Pois}(\mu)+\mathrm{Pois}(\nu)=\mathrm{Pois}(\mu+\nu)$.

#### **9.7. Mini esempi (dagli appelli)**

**23/07/2025 D4:** call center, $\lambda=5$/ora ⇒ $P(X=3)=\frac{e^{-5}5^3}{6}\approx 0.1404$; $P(X\le2)=e^{-5}(1+5+12.5)\approx 0.1247$.

**12/02/2025 D5:** $p=1/200$, $n=500$ ⇒ formula esatta $\binom{500}{3}(1/200)^3(199/200)^{497}$, approssimata con $\mu=np=2.5$: $P(X=3)=\frac{e^{-2.5}2.5^3}{6}\approx0.2138$; $P(X\le2)=e^{-2.5}(1+2.5+3.125)\approx 0.5438$.

> 💡 Se la traccia chiede prima la formula esatta e poi quella approssimata, **scrivere entrambe**: il primo punto vuole la binomiale impostata (senza svolgere), il secondo la Poisson calcolata.

---

### **10. Ipergeometrica**

#### **10.1. Quando usarla**

**Parola chiave: "senza reinserimento"** da una popolazione **finita** divisa in due categorie. Testi tipici: *"un lotto di $N$ componenti tra cui $K$ difettosi, si estrae un campione di $n$"*. Le prove **non** sono indipendenti e $p$ **non** è costante ⇒ la binomiale non si applica.

> 📌 **4 appelli su 8** (15/01, 05/07, 19/09/2025 e 16/01/2026). Il 16/01/2026 assegna 2 punti alla sola **identificazione giustificata**: scrivere *"campionamento senza reinserimento da popolazione finita ⇒ prove dipendenti, $p$ non costante ⇒ ipergeometrica (con reinserimento sarebbe stata $B(n,K/N)$)"*.

#### **10.2. Formula principale**

$$\boxed{\;P(X=k)=\frac{\dbinom{K}{k}\dbinom{N-K}{n-k}}{\dbinom{N}{n}}\;}$$

**Supporto:** $\max(0,\;n-(N-K))\le k\le\min(n,K)$.

#### **10.3. Parametri**

| Simbolo | Significato |
|---|---|
| $N$ | dimensione della popolazione (il lotto) |
| $K$ | numero di "successi" nella popolazione (difettosi, rosse, nuovi) |
| $n$ | dimensione del campione estratto |
| $k$ | numero di successi nel campione |
| $p=K/N$ | frazione di successi nella popolazione |

#### **10.4. Momenti**

$$E[X]=n\,\frac{K}{N}=np \qquad\qquad \mathrm{Var}(X)=n\,p\,(1-p)\cdot\underbrace{\frac{N-n}{N-1}}_{\text{correzione per popolazione finita}}$$

> 📌 **Il fattore di correzione per popolazione finita** $\frac{N-n}{N-1}<1$: campionare senza reinserimento vincola gli esiti e **riduce la variabilità** rispetto alla binomiale. Casi limite: $n=1\Rightarrow$ fattore $=1$ (una sola estrazione, nessuna differenza); $n=N\Rightarrow$ fattore $=0$ (estraggo tutto, $X=K$ con certezza, varianza nulla).
>
> ⚠️ **Non ha nulla a che vedere con il $n-1$ di Bessel** della varianza campionaria: quello è un denominatore in statistica descrittiva, questo è un fattore moltiplicativo in una varianza teorica.

#### **10.5. Controllo di correttezza della formula**

Nel numeratore gli argomenti **in alto** devono sommare a $N$ ($K + (N-K)$) e quelli **in basso** a $n$ ($k + (n-k)$). Se non torna, la formula è sbagliata.

#### **10.6. Mini esempio completo (16/01/2026 D1)**

$N=24$, $K=6$, $n=4$, $\binom{24}{4}=10626$:

$$P(X=2)=\frac{\binom62\binom{18}{2}}{10626}=\frac{15\cdot153}{10626}=\frac{2295}{10626}\approx 0.216$$
$$P(X\ge1)=1-\frac{\binom{18}{4}}{\binom{24}{4}}=1-\frac{3060}{10626}\approx 0.712$$
$$E[X]=4\cdot\tfrac14=1 \qquad \mathrm{Var}(X)=4\cdot\tfrac14\cdot\tfrac34\cdot\tfrac{20}{23}=\tfrac{15}{23}\approx 0.652$$

Altri appelli: $N=20,K=5,n=4$ ⇒ $E[X]=1$, $\mathrm{Var}=\frac{12}{19}\approx0.632$ (05/07); $N=300,K=25,n=20$ ⇒ $E[X]=\frac53\approx1.67$ (19/09).

#### **10.7. Errori tipici**

> ⚠️ In ordine di frequenza: (1) usare la binomiale nonostante il "senza reinserimento"; (2) scrivere $\binom{N}{n-k}$ invece di $\binom{N-K}{n-k}$; (3) **dimenticare il fattore di correzione** nella varianza; (4) leggere male le disuguaglianze — *"più di una ma meno di quattro"* significa $k\in\{2,3\}$, estremi esclusi.

---

### **11. Esponenziale negativa**

#### **11.1. Quando usarla**

*"Tempo di attesa"*, *"durata di vita"*, *"tempo tra due arrivi"*, con **tasso costante** e componente che **non invecchia**. Spia decisiva nel testo: viene dato il **valore atteso** (es. "vita media 8 mesi", "media 10 minuti").

#### **11.2. Formule fondamentali**

$$f(t)=\lambda e^{-\lambda t}\quad (t\ge0) \qquad F(t)=P(T\le t)=1-e^{-\lambda t} \qquad \boxed{\;S(t)=P(T>t)=e^{-\lambda t}\;}$$

#### **11.3. Parametri e momenti**

| Simbolo | Significato |
|---|---|
| $\lambda$ | **tasso** di evento (guasti/ora, arrivi/minuto): dimensione $[\text{tempo}]^{-1}$ |
| $\tau=\mu=1/\lambda$ | **tempo medio** di attesa / vita media |

$$\mu=\frac{1}{\lambda} \qquad \sigma^2=\frac{1}{\lambda^2} \qquad \sigma=\frac{1}{\lambda}=\mu \qquad t_{\text{moda}}=0,\ f(0)=\lambda$$

> ⚠️ **Il passaggio che si sbaglia più spesso.** "Valore atteso di 8 mesi" ⇒ $\mu=8$ ⇒ $\boxed{\lambda=1/8}$. Da lì $P(T>12)=e^{-12/8}=e^{-1.5}$. Scrivere $e^{-8\cdot12}$ è l'errore classico.
>
> 💡 Nella pratica conviene non calcolare mai $\lambda$ separatamente: usare direttamente $S(t)=e^{-t/\mu}$.

#### **11.4. Proprietà di assenza di memoria (memoryless)**

$$\boxed{\;P(T>s+t\mid T>s)=P(T>t)\;}$$

**Traduzione da scrivere all'esame:** l'esponenziale è l'unica densità continua senza memoria; il componente "non invecchia", quindi la probabilità di durare altri $t$ è la stessa di un componente appena installato, indipendentemente da quanto ha già funzionato.

Variante con la cumulativa (16/01/2026 D3b): *"sono passati 5 minuti, probabilità che arrivi entro i prossimi 10"*:
$$P(T\le 15\mid T>5)=P(T\le 10)=1-e^{-10/10}=1-e^{-1}\approx 0.6321$$

#### **11.5. Quantili**

$$\boxed{\;x_p = -\frac{\ln(1-p)}{\lambda} = -\tau\ln(1-p)\;}$$

| Quantile | Formula esatta | In unità di $\tau$ |
|---|---|---|
| $Q_1$ | $\dfrac{\ln(4/3)}{\lambda}$ | $0.288\,\tau$ |
| **Mediana** | $\dfrac{\ln 2}{\lambda}=\tau\ln 2$ | $0.693\,\tau$ |
| $Q_3$ | $\dfrac{\ln 4}{\lambda}$ | $1.386\,\tau$ |
| $80^\circ$ perc. | $\dfrac{\ln 5}{\lambda}$ | $1.609\,\tau$ |
| $90^\circ$ perc. | $\dfrac{\ln 10}{\lambda}$ | $2.303\,\tau$ |
| $95^\circ$ perc. | $\dfrac{\ln 20}{\lambda}$ | $2.996\,\tau\approx 3\tau$ |

> 📌 Nell'esponenziale la **mediana è sempre minore della media** ($0.693\,\tau<\tau$): la lunga coda destra trascina in alto la media. $\ln 2\approx 0.693$ è una costante da ricordare.

#### **11.6. Sistemi di componenti esponenziali**

| Configurazione | Formula | Nota |
|---|---|---|
| **Serie** di $n$ (funziona se tutti funzionano) | $R(t)=e^{-n\lambda t}$ | ancora esponenziale, $\lambda_{\text{tot}}=n\lambda$, $\tau_{\text{tot}}=\frac{1}{n\lambda}$ |
| **Parallelo** di $n$ (basta uno) | $R(t)=1-\left(1-e^{-\lambda t}\right)^n$ | **non** più esponenziale |
| **Stand-by** (il secondo parte quando il primo si guasta) | somma dei tempi ⇒ **Erlang**$(r,\lambda)$ | vedi [L3 §2](L3%20-%20M3%20Formulario%20-%20Somme,%20campionamento%20e%20inferenza.md) |

#### **11.7. Mini esempi (dagli appelli)**

**15/01/2025 D2** — $\mu=1000$ ore: singolo $P(X>1500)=e^{-1.5}\approx0.2231$; **parallelo** $1-(1-e^{-1.5})^2=1-0.7769^2\approx0.3964$; **serie** $e^{-1.5}\cdot e^{-1.5}=e^{-3}\approx0.0498$.

**05/09/2025 D1** — $\mu=8$ mesi: $P(X>12)=e^{-1.5}\approx0.2231$; tre in serie per almeno 4 mesi $=\left(e^{-0.5}\right)^3=e^{-1.5}\approx0.2231$; memoryless $P(X>12\mid X>6)=P(X>6)=e^{-0.75}\approx0.4724$.

**16/01/2026 D3** — $\mu=10$ min: $P(T>15)=e^{-1.5}\approx0.2231$; mediana $=10\ln2\approx6.93$ min.

---

### **12. Normale (gaussiana)**

#### **12.1. Quando usarla**

Fenomeno continuo, simmetrico attorno a una media, con $\mu$ e $\sigma$ dati: QI, punteggi, altezze, pesi, dimensioni di file, errori di misura. Oppure come **approssimazione** di una binomiale o della media campionaria (TLC).

#### **12.2. Densità e standardizzazione**

$$f(x)=\frac{1}{\sigma\sqrt{2\pi}}\,e^{-\frac{(x-\mu)^2}{2\sigma^2}} \qquad X\sim \mathcal N(\mu,\sigma^2)$$

$$\boxed{\;Z=\frac{X-\mu}{\sigma}\sim\mathcal N(0,1)\;}\qquad\qquad \boxed{\;X=\mu+z\sigma\;}$$

> ⚠️ La notazione $\mathcal N(100,256)$ indica $\sigma^2=256$, cioè $\sigma=16$: nella standardizzazione va $\sigma$, **non** $\sigma^2$. Controllo: i valori di $z$ ragionevoli stanno fra $-4$ e $+4$.

#### **12.3. Le quattro probabilità (con $\Phi(z)=P(Z\le z)$)**

| Domanda | Formula |
|---|---|
| $P(X<a)$ | $\Phi(z_a)$ |
| $P(X>b)$ | $1-\Phi(z_b)$ |
| $P(a<X<b)$ | $\Phi(z_b)-\Phi(z_a)$ |
| $P(\mu-k\sigma<X<\mu+k\sigma)$ | $2\Phi(k)-1$ |
| Simmetria (per $z$ negativi) | $\Phi(-z)=1-\Phi(z)$ |

#### **12.4. Procedura d'esame**

1. Scrivere il modello: $X\sim\mathcal N(\mu,\sigma^2)$ con i valori numerici.
2. Standardizzare: $z=(x-\mu)/\sigma$.
3. Leggere la tavola ([L0 §6.3](L0%20-%20Guida%20rapida%20d'esame%20e%20tavole%20statistiche.md)) — **verificare quale tavola si sta usando**.
4. Applicare simmetria/complemento se serve.
5. Riportare il risultato anche in percentuale.

#### **12.5. Percorso inverso: percentili**

Cercare **dentro** la tavola la probabilità richiesta, leggere il $z$ corrispondente, poi $x=\mu+z\sigma$.

$$x_{0.90}=\mu+1.282\,\sigma \qquad x_{0.95}=\mu+1.645\,\sigma \qquad x_{0.99}=\mu+2.326\,\sigma$$

**Mini esempio (23/07/2025 D5d):** $\mu=75$, $\sigma=8$ ⇒ $x_{0.90}=75+1.282(8)=85.26$ punti.

#### **12.6. Regola dei tre sigma e legge dei percentili**

| Intervallo | Probabilità |
|---|---|
| $\mu\pm 1\sigma$ | $68.27\%$ |
| $\mu\pm 2\sigma$ | $95.45\%$ |
| $\mu\pm 3\sigma$ | $99.73\%$ (fuori: $0.27\%$, cioè 3 su 1000) |
| $\mu\pm 1.96\sigma$ | $95\%$ **esatto** |
| $\mu\pm 2.576\sigma$ | $99\%$ **esatto** |

> 💡 **Tre sigma vs percentili:** nella regola dei tre sigma si fissa l'**intervallo** e si ricava la probabilità; nella legge dei percentili si fissa la **probabilità** e si ricava l'intervallo. Per gli intervalli di confidenza serve la seconda ($1.96$, non $2$).

#### **12.7. Somma e proprietà**

Somma di normali indipendenti: $\;\mathcal N(\mu_1,\sigma_1^2)+\mathcal N(\mu_2,\sigma_2^2)=\mathcal N(\mu_1+\mu_2,\ \sigma_1^2+\sigma_2^2)$.

Per la normale: media $=$ mediana $=$ moda $=\mu$; la densità è simmetrica e non ha primitiva elementare (da qui le tavole).

#### **12.8. Mini esempi (dagli appelli)**

**05/07/2025 D4** — QI $\mathcal N(100,16^2)$: $P(X<84)$: $z=-1\Rightarrow 1-0.8413=0.1587$. $P(X>124)$: $z=1.5\Rightarrow1-0.9332=0.0668$. Dieci individui **tutti** in $(92,108)$: $p=2\Phi(0.5)-1=0.383$, quindi $p^{10}\approx 6.8\times10^{-5}$.

**23/07/2025 D5** — $\mathcal N(75,64)$: $P(70\le X\le80)=2\Phi(0.625)-1=2(0.7340)-1=0.4680$; $P(X\le65)=\Phi(-1.25)=0.1056$; $P(X>90)=1-\Phi(1.875)=0.0304$.

**16/01/2026 D3d** — $\mathcal N(500,100^2)$: $P(X<350)$: $z=-1.5\Rightarrow 1-0.9332=0.0668$.

> ⚠️ *"Tutti e 10 gli individui"* ⇒ **$p^{10}$**, non $10p$: sono 10 eventi indipendenti che devono verificarsi **tutti** (legge del prodotto).

---

### **13. Approssimazione normale della binomiale (con correzione di continuità)**

#### **13.1. Quando usarla**

La traccia chiede $P(a\le X\le b)$ per una binomiale con $n$ grande e dice *"è possibile, se le condizioni sono soddisfatte, utilizzare un'approssimazione per evitare un calcolo complesso"* (19/09/2025 D2c, 4 punti).

#### **13.2. Condizioni (da verificare per iscritto)**

$$np\ge 10 \qquad\text{e}\qquad n(1-p)\ge 10$$

(alcune fonti usano la soglia 5: citarne una e verificarla numericamente).

#### **13.3. Parametri dell'approssimante**

$$X \approx \mathcal N\big(\mu=np,\ \sigma^2=np(1-p)\big)$$

#### **13.4. Correzione di continuità**

Ogni intero $k$ va pensato "spalmato" su $[k-0.5,\,k+0.5]$: gli intervalli si **allargano di mezzo punto verso l'esterno**.

| Evento discreto | Evento continuo |
|---|---|
| $P(X=k)$ | $P(k-0.5<X_{\mathcal N}<k+0.5)$ |
| $P(a\le X\le b)$ | $P(a-0.5<X_{\mathcal N}<b+0.5)$ |
| $P(X\le b)$ | $P(X_{\mathcal N}<b+0.5)$ |
| $P(X\ge a)$ | $P(X_{\mathcal N}>a-0.5)$ |
| $P(X>a)$ cioè $P(X\ge a+1)$ | $P(X_{\mathcal N}>a+0.5)$ |
| $P(X<b)$ cioè $P(X\le b-1)$ | $P(X_{\mathcal N}<b-0.5)$ |

> 📌 **Regola per non sbagliare il segno:** riscrivere prima l'evento con disuguaglianze **larghe** sugli interi, poi allargare di $0.5$ verso l'esterno.

#### **13.5. Mini esempio completo (19/09/2025 D2c)**

$X\sim B(200,0.05)$, si chiede $P(8\le X\le 15)$.

1. Condizioni: $np=10\ge10$ ✓, $n(1-p)=190\ge10$ ✓
2. Parametri: $\mu=10$, $\sigma=\sqrt{9.5}\approx3.082$
3. Continuità: $P(7.5<X_{\mathcal N}<15.5)$
4. $z_1=\frac{7.5-10}{3.082}\approx-0.811$, $z_2=\frac{15.5-10}{3.082}\approx1.784$
5. $\Phi(1.78)=0.9625$, $\Phi(-0.81)=1-0.7910=0.2090$ ⇒ $\;\mathbf{0.7535}$

> ⚠️ **Quanto pesa la correzione.** Il valore binomiale **esatto** è $0.7423$; con la correzione di continuità si ottiene $0.7535$ (errore $+1.1$ punti percentuali), **senza** correzione si otterrebbe $\Phi(1.622)-\Phi(-0.649)\approx 0.6894$ (errore $-5.3$ punti). I 4 punti premiano **verifica delle condizioni + correzione + uso corretto della tavola**: ometterne una costa punti anche col numero finale giusto.

---

### **14. Tabella riassuntiva di tutte le distribuzioni**

| Distribuzione | Legge | Supporto | Media | Varianza | Trigger nel testo |
|---|---|---|---|---|---|
| **Bernoulli**$(p)$ | $P(1)=p$, $P(0)=q$ | $\{0,1\}$ | $p$ | $pq$ | una prova, successo/fallimento |
| **Uniforme discreta** | $1/m$ | $m$ valori | media dei valori | — | tutti equiprobabili |
| **Uniforme**$[a,b]$ | $\frac{1}{b-a}$ | $[a,b]$ | $\frac{a+b}{2}$ | $\frac{(b-a)^2}{12}$ | densità costante |
| **Binomiale**$(n,p)$ | $\binom nk p^kq^{n-k}$ | $0..n$ | $np$ | $npq$ | $n$ prove indipendenti |
| **Geometrica**$(p)$ | $q^{i-1}p$ | $1,2,\dots$ | $\frac1p$ | $\frac{q}{p^2}$ | tentativi fino al 1° successo |
| **Poisson**$(\mu)$ | $\frac{\mu^ke^{-\mu}}{k!}$ | $0,1,\dots$ | $\mu$ | $\mu$ | conteggi in un intervallo |
| **Ipergeom.**$(N,K,n)$ | $\frac{\binom Kk\binom{N-K}{n-k}}{\binom Nn}$ | vedi §10.2 | $n\frac KN$ | $np(1-p)\frac{N-n}{N-1}$ | **senza reinserimento** |
| **Esponenziale**$(\lambda)$ | $\lambda e^{-\lambda t}$ | $t\ge0$ | $\frac1\lambda$ | $\frac{1}{\lambda^2}$ | tempo d'attesa, vita media |
| **Erlang**$(r,\lambda)$ | $\frac{\lambda^r t^{r-1}e^{-\lambda t}}{(r-1)!}$ | $t\ge0$ | $\frac r\lambda$ | $\frac{r}{\lambda^2}$ | somma di $r$ esponenziali (stand-by) |
| **Normale**$(\mu,\sigma^2)$ | $\frac{1}{\sigma\sqrt{2\pi}}e^{-\frac{(x-\mu)^2}{2\sigma^2}}$ | $\mathbb R$ | $\mu$ | $\sigma^2$ | continuo simmetrico, tavole |
