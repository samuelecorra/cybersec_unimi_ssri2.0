# **L1 - Formulario M1: Probabilità elementare**

> 📌 Copre [M1 UD1-UD5](../M1_Probabilita_elementare/). Argomenti: definizione e assiomi, combinatoria, probabilità condizionata, legge del prodotto, legge della somma (alternative), Teorema di Bayes, affidabilità dei sistemi, valore atteso e legge dei grandi numeri. **Bayes compare in tutti e 8 gli appelli disponibili.**

---

### **1. Definizione e assiomi**

#### **1.1. Formule base**

$$P(E) = \frac{\#(E)}{\#(S)} = \frac{\text{casi favorevoli}}{\text{casi possibili}} \qquad 0\le P(E)\le 1 \qquad P(S)=1$$

**Assiomi di Kolmogorov:** $P(A)\ge 0$; $P(A\cup B)=P(A)+P(B)$ se $A\cap B=\varnothing$; $P(S)=1$.

#### **1.2. Operazioni sugli eventi**

| Operazione                     | Formula                                                       | Nota                              |
| ------------------------------ | ------------------------------------------------------------- | --------------------------------- |
| Complemento                    | $P(A^c)=1-P(A)$                                               | la scorciatoia più usata di tutte |
| Unione (inclusione-esclusione) | $P(A\cup B)=P(A)+P(B)-P(A\cap B)$                             |                                   |
| Unione, eventi **disgiunti**   | $P(A\cup B)=P(A)+P(B)$                                        | $A\cap B=\varnothing$             |
| Unione di tre                  | $P(A\cup B\cup C)=\sum P - \sum P(\cap_2) + P(A\cap B\cap C)$ | segni alternati                   |
| Unione via complemento         | $P(A\cup B)=1-P(A^c\cap B^c)$                                 | **preferita** con molti eventi    |

> 💡 **Parola spia "almeno"** ⇒ passare al complemento. _"Almeno un asso in 2 carte"_ $=1-P(\text{nessun asso})$; _"almeno un cuore"_ $=1-P(\text{nessun cuore})$; _"almeno un difettoso"_ $=1-P(X=0)$.

#### **1.3. Normalizzazione da pesi statistici (odds)**

Dati pesi grezzi $w_1,\dots,w_n$ (es. cavallo "dato 3 a 2", dado con $P(k)\propto k$):

$$N=\sum_i w_i \qquad P_i = \frac{w_i}{N} \qquad \sum_i P_i = 1$$

> **Mini esempio.** Dado truccato con $P(k)\propto k$: pesi $1{+}2{+}3{+}4{+}5{+}6=21$, quindi $P(k)=k/21$ e $P(\text{pari})=\frac{2+4+6}{21}=\frac{12}{21}=\frac47$.

---

### **2. Combinatoria**

#### **2.1. Quando usarla**

Il testo chiede di **contare** configurazioni: targhe, sequenze di DNA, mani di carte, estrazioni al Lotto, scelte di $k$ oggetti tra $n$.

#### **2.2. Formule**

| Situazione                                                    | Formula                                           | Esempio                            |
| ------------------------------------------------------------- | ------------------------------------------------- | ---------------------------------- |
| Scelte successive indipendenti                                | $N=k_1\cdot k_2\cdots k_n$                        | targhe: $26^2\cdot 10^3\cdot 26^2$ |
| Sequenze di lunghezza $r$ da $n$ simboli, **con** ripetizione | $n^r$                                             | DNA: $4^3=64$                      |
| Sequenze **ordinate senza** ripetizione (disposizioni)        | $\dfrac{n!}{(n-r)!}=n(n-1)\cdots(n-r+1)$          | $3$ nere da urna: $5\cdot4\cdot3$  |
| Sottoinsiemi **non ordinati** (combinazioni)                  | $\displaystyle\binom{n}{k}=\frac{n!}{k!\,(n-k)!}$ | mani di carte, lotto               |
| Numero di coppie in un gruppo di $n$                          | $\dbinom{n}{2}=\dfrac{n(n-1)}{2}$                 | paradosso del compleanno           |

Coefficienti binomiali utili a memoria: $\binom{n}{0}=\binom{n}{n}=1$, $\binom{n}{1}=n$, $\binom{n}{2}=\frac{n(n-1)}{2}$, $\binom{n}{3}=\frac{n(n-1)(n-2)}{6}$; simmetria $\binom{n}{k}=\binom{n}{n-k}$.

#### **2.3. Procedura d'esame**

1. Ordine conta? Sì ⇒ disposizioni/potenze. No ⇒ combinazioni.
2. Ripetizioni ammesse? Sì ⇒ $n^r$. No ⇒ fattoriali decrescenti.
3. Scrivere $\#(S)$ e $\#(E)$ **con lo stesso criterio** (entrambi ordinati o entrambi non ordinati).

> ⚠️ Mescolare i criteri (numeratore ordinato, denominatore no) è l'errore più costoso. Se in dubbio, si può risolvere **sequenzialmente** con la legge del prodotto e ottenere lo stesso risultato.

#### **2.4. Mini esempio (Lotto, 15/01/2025 D4)**

Terno giocato, 5 numeri estratti su 90. Via combinatoria:
$$P(\text{terno}) = \frac{\binom{87}{2}}{\binom{90}{5}}\cdot\frac{1}{1}=\frac{1}{11748}\approx 8.51\times10^{-5}$$
Via sequenziale (controllo): $\frac{5}{90}\cdot\frac{4}{89}\cdot\frac{3}{88}=\frac{1}{11748}$ ✓

Per $10$ combinazioni giocate: $P(\ge 1\text{ terno}) = 1-(1-p)^{10}\approx 10p$ (valida perché $p\ll 1$).

---

### **3. Probabilità condizionata**

#### **3.1. Quando usarla**

Il testo dice _"dato che"_, _"sapendo che"_, _"se è già accaduto"_, oppure descrive **estrazioni senza reinserimento** (la seconda estrazione è condizionata dalla prima).

#### **3.2. Formula principale**

$$P(A\mid B) = \frac{P(A\cap B)}{P(B)} = \frac{\#(A\cap B)}{\#(B)}$$

#### **3.3. Lettura del risultato**

| Relazione         | Significato                                    |
| ----------------- | ---------------------------------------------- |
| $P(A\mid B)>P(A)$ | $B$ favorisce $A$ — correlazione **positiva**  |
| $P(A\mid B)=P(A)$ | **indipendenza**                               |
| $P(A\mid B)<P(A)$ | $B$ sfavorisce $A$ — correlazione **negativa** |

> 📌 Domanda ricorrente (16/06/2025 D5c): _"si giustifichi il fatto che $P(U_1\mid R)<P(U_1)$"_. Risposta tipo: il rosso è un esito più tipico di $U_2$; poiché $P(R\mid U_1)=0.4 < P(R)=0.55$, il fattore $P(R\mid U_1)/P(R)<1$ **riduce** la prior. L'evidenza sposta la fiducia verso l'urna più ricca di rosse.

---

### **4. Legge del prodotto**

#### **4.1. Formule**

$$P(A\cap B)=P(A)\cdot P(B\mid A) \qquad\text{(sempre valida)}$$
$$P(A\cap B)=P(A)\cdot P(B) \qquad\text{(solo se indipendenti)}$$

Tre eventi: $\;P(A\cap B\cap C)=P(A)\,P(B\mid A)\,P(C\mid A\cap B)$.

$n$ eventi: $\;P\!\left(\bigcap_{i=1}^n A_i\right)=P(A_1)P(A_2\mid A_1)\cdots P(A_n\mid A_1\cap\dots\cap A_{n-1})$.

$n$ eventi **indipendenti**: $\;P\!\left(\bigcap_{i=1}^n A_i\right)=\prod_{i=1}^n P(A_i)$; se tutti con la stessa $p$: $\;p^n$.

#### **4.2. Procedura d'esame (diagramma ad albero)**

1. Primo livello: probabilità della prima estrazione.
2. Secondo livello: probabilità **condizionate** (con reinserimento restano uguali, senza reinserimento cambiano numeratore **e** denominatore).
3. Probabilità di un cammino = **prodotto** dei rami.
4. Probabilità di un evento = **somma** dei cammini favorevoli.
5. Verifica: la somma di tutti i cammini deve fare 1.

#### **4.3. Mini esempio (16/01/2026 D4)**

Mazzo da 40, 10 cuori, due carte senza reinserimento:

| Cammino                 | Probabilità                       | Valore               |
| ----------------------- | --------------------------------- | -------------------- |
| $C_1\cap C_2$           | $\frac{10}{40}\cdot\frac{9}{39}$  | $3/52\approx 0.0577$ |
| $C_1\cap \bar C_2$      | $\frac{10}{40}\cdot\frac{30}{39}$ | $5/26$               |
| $\bar C_1\cap C_2$      | $\frac{30}{40}\cdot\frac{10}{39}$ | $5/26$               |
| $\bar C_1\cap \bar C_2$ | $\frac{30}{40}\cdot\frac{29}{39}$ | $29/52$              |

$P(\text{almeno un cuore})=1-\frac{29}{52}=\frac{23}{52}\approx 0.4423$ (complemento: un solo cammino da calcolare).

> ⚠️ **Trappola.** Senza reinserimento cala il denominatore ($40\to39$) e cala il numeratore **solo sul ramo coerente** ($10\to9$ dopo un cuore, ma resta $10$ dopo un non-cuore).

---

### **5. Legge della somma (legge delle alternative / probabilità totale)**

#### **5.1. Quando usarla**

Il testo chiede la probabilità **totale/complessiva** di un evento che può realizzarsi per **più vie alternative** e mutuamente esclusive: si sceglie prima un'urna e poi si estrae, si sceglie una macchina e poi si controlla il pezzo, un bit viene trasmesso e poi ricevuto. È anche il metodo per i **sistemi non serie-parallelo** (§7.5).

#### **5.2. Formule**

$$P(B) = P(B\cap A) + P(B\cap A^c) = P(A)P(B\mid A) + P(A^c)P(B\mid A^c)$$

Forma generale su una partizione $A_1,\dots,A_n$ (disgiunti, unione $=S$):

$$\boxed{\;P(B)=\sum_{i=1}^{n} P(A_i)\,P(B\mid A_i)\;}$$

#### **5.3. Mini esempio (12/02/2025 D3)**

Macchine A/B/C con quote $0.5$/$0.3$/$0.2$ e difettosità $1\%$/$2\%$/$3\%$:
$$P(D)=0.01(0.5)+0.02(0.3)+0.03(0.2)=0.005+0.006+0.006=0.017$$

> 💡 Il denominatore di Bayes **è sempre** questa somma: conviene calcolarla per prima e tenerla da parte.

---

### **6. Teorema di Bayes**

#### **6.1. Quando usarlo**

Si osserva un **effetto** e si chiede la probabilità della **causa**: _"dato che il test è positivo, qual è la probabilità di essere malato"_, _"estratta una pallina rossa, da quale urna proviene"_, _"ricevuto un 1, cos'era stato trasmesso"_. Parole spia: **a posteriori**, **aggiornata**, _"supponendo di aver osservato ..."_.

#### **6.2. Formula principale**

$$\boxed{\;P(H_k\mid D) = \frac{P(H_k)\,P(D\mid H_k)}{P(D)} = \frac{P(H_k)\,P(D\mid H_k)}{\sum_i P(H_i)\,P(D\mid H_i)}\;}$$

$$\text{posterior} = \frac{\text{prior}\times\text{verosimiglianza}}{\text{evidenza}} \qquad\qquad \text{posterior}\ \propto\ \text{prior}\times\text{verosimiglianza}$$

#### **6.3. Parametri**

| Simbolo        | Nome                | Che cos'è nel testo                                                                   |
| -------------- | ------------------- | ------------------------------------------------------------------------------------- |
| $P(H_k)$       | **prior**           | prevalenza della malattia, quota di produzione della macchina, composizione dell'urna |
| $P(D\mid H_k)$ | **verosimiglianza** | sensibilità, tasso di difettosità, $P(\text{rossa}\mid U_1)$                          |
| $P(D)$         | **evidenza**        | probabilità totale del dato osservato (§5.2) — è il denominatore                      |
| $P(H_k\mid D)$ | **posterior**       | ciò che la traccia chiede                                                             |

#### **6.4. Procedura d'esame**

1. **Nominare gli eventi** ($M$ = malato, $T^+$ = test positivo) e scrivere i dati come prior e verosimiglianze.
2. Se la traccia dà la **specificità**, ricavare il falso positivo: $P(T^+\mid M^c)=1-\text{specificità}$.
3. Calcolare l'**evidenza** $P(T^+)=P(T^+\mid M)P(M)+P(T^+\mid M^c)P(M^c)$.
4. Applicare Bayes; verificare che le posterior sommino a 1.
5. **Commentare** (vale punti): il paradosso dei falsi positivi.

#### **6.5. Bayes iterativo (05/09/2025 D4d)**

Con un secondo test indipendente, **la posterior del primo diventa la prior del secondo**:

$$P'(M)=P(M\mid T_1^+) \quad\Rightarrow\quad P(M\mid T_1^+,T_2^+)=\frac{P(T^+\mid M)\,P'(M)}{P(T^+\mid M)P'(M)+P(T^+\mid M^c)P'(M^c)}$$

> ✅ Equivalente al calcolo diretto con verosimiglianza $[P(T^+\mid M)]^2$: il risultato è lo stesso, elaborando i dati in sequenza o tutti insieme.

#### **6.6. Mini esempio completo (16/01/2026 D5)**

$P(M)=0.001$, sensibilità $0.99$, falso positivo $0.05$:
$$P(T^+)=0.99(0.001)+0.05(0.999)=0.00099+0.04995=0.05094$$
$$P(M\mid T^+)=\frac{0.00099}{0.05094}\approx 0.0194\ (1.9\%)$$

**Commento da scrivere:** su 100 000 persone ci si attendono $\approx99$ veri positivi ma $\approx4995$ falsi positivi; con una malattia rara i falsi allarmi dominano, ed è la ragione per cui allo screening segue sempre un test di conferma.

> ⚠️ **Non confondere** sensibilità $P(T^+\mid M)$ con PPV $P(M\mid T^+)$: sono le due direzioni opposte del condizionamento, ed è esattamente ciò che l'esercizio vuole far notare.

---

### **7. Affidabilità dei sistemi**

#### **7.1. Quando usarla**

Componenti/linee/generatori/sensori collegati, e la traccia chiede la probabilità che **il sistema funzioni**. Notazione: $R$ = affidabilità (reliability), $F=1-R$ = fallibilità.

#### **7.2. Formule**

| Configurazione                 | Condizione                          | Affidabilità                                              |
| ------------------------------ | ----------------------------------- | --------------------------------------------------------- |
| **Serie** ($n$ componenti)     | funziona se **tutti** funzionano    | $R_S=\prod_{i} R_i$; se uguali: $R^n$                     |
| **Parallelo** ($n$ componenti) | funziona se **almeno uno** funziona | $R_P=1-\prod_i (1-R_i)=1-\prod_i F_i$; se uguali: $1-q^n$ |
| **Serie-parallelo**            | mista                               | ridurre a blocchi ("divide et impera")                    |
| **Non riducibile**             | ponte, stella                       | condizionare (§7.5)                                       |

Con componenti identici: $R=p$, $F=q=1-p$, quindi serie $=p^n$, parallelo $=1-q^n$.

#### **7.3. Mini esempio numerico**

$R_A=R_B=0.9$: serie $=0.81$; parallelo $=1-0.1^2=0.99$.
$R_A=0.9,R_B=0.8,R_C=0.7$: serie $=0.504$; parallelo $=1-(0.1)(0.2)(0.3)=0.994$.

#### **7.4. Affidabilità nel tempo (componenti esponenziali)**

$$R(t)=e^{-\lambda t} \qquad R_{\text{serie}}(t)=e^{-(\lambda_1+\dots+\lambda_n)t} \qquad R_{\text{par}}(t)=1-\prod_i\left(1-e^{-\lambda_i t}\right)$$

> 📌 La **serie di esponenziali è ancora esponenziale** con $\lambda_{\text{serie}}=\sum\lambda_i$ e vita media $\tau=1/(n\lambda)$. Il parallelo **non** lo è.

#### **7.5. Sistemi non serie-parallelo: metodo del condizionamento**

**Idea:** si sceglie il componente "scomodo" $C$ e si condiziona sul suo stato; in ciascuno dei due scenari la rete si riduce a serie/parallelo elementari.

$$P(T)=P(C)\,P(T\mid C)+P(C^c)\,P(T\mid C^c)$$

**Rete a ponte a 5 linee** (16/06/2025 D1): da $i$ due linee verso i nodi $A$ (sopra) e $B$ (sotto), da ciascuno una linea verso $o$, più la linea centrale bidirezionale $C$ fra $A$ e $B$.

- $C$ **funziona** (prob. $p$): $A$ e $B$ si fondono ⇒ **serie di due paralleli** ⇒ $R_{\text{on}}=(1-q^2)^2$
- $C$ **guasta** (prob. $q$): due cammini indipendenti $i\!-\!A\!-\!o$ e $i\!-\!B\!-\!o$ ⇒ **parallelo di due serie** ⇒ $R_{\text{off}}=1-(1-p^2)^2$

$$\boxed{\;R = p\,(1-q^2)^2 + q\left[1-(1-p^2)^2\right]\;}$$

Con $p=0.9$: $R_{\text{on}}=0.99^2=0.9801$, $R_{\text{off}}=1-0.19^2=0.9639$, quindi $R=0.9(0.9801)+0.1(0.9639)=\mathbf{0.97848}$.

Se la sola linea centrale degrada a $p_C=q_C=0.5$ (le altre restano a $0.9$): $R'=0.5(0.9801)+0.5(0.9639)=0.972$ ⇒ **fallibilità** $F'=1-R'=0.028$.

**Circuito a 4 componenti $A,B,C,D$** (M1/UD3/L4): $C$ guasto ⇒ serie $A$-$B$ ($p^2$); $C$ funzionante ⇒ parallelo $B$-$D$ ($1-q^2$):
$$P(T)=p(1-q^2)+q\,p^2$$

> ⚠️ La domanda chiede quasi sempre anche _"spiegare il metodo utilizzato"_: va scritto esplicitamente **"condiziono sullo stato della linea centrale e applico la legge delle alternative, perché la rete non è riducibile a serie o parallelo"**. Vale punti indipendentemente dal numero.

#### **7.6. Sistema $k$-su-$n$ (almeno 2 generatori su 3)**

Non è né serie né parallelo: si sommano i cammini, oppure si usa la **binomiale** ([L2 §7](L2%20-%20M2%20Formulario%20-%20Variabili%20aleatorie%20e%20distribuzioni.md)).

$$P(\text{almeno 2 su 3})=p^3+3p^2q = 3p^2-2p^3$$

---

### **8. Valore atteso e legge dei grandi numeri**

#### **8.1. Valore atteso di una quantità aleatoria**

$$\langle x\rangle = E[x] = \sum_i x(A_i)\,P(A_i)$$

Due soli esiti: $\;\langle x\rangle = x(A)\,p + x(A^c)\,q$.

| Segno                | Interpretazione (gioco d'azzardo) |
| -------------------- | --------------------------------- |
| $\langle x\rangle>0$ | gioco favorevole al giocatore     |
| $\langle x\rangle=0$ | gioco **equo**                    |
| $\langle x\rangle<0$ | gioco favorevole al banco         |

**Condizione di equità** fra due puntate $u$ e $v$: $\;\dfrac{u}{v}=\dfrac{1-p}{p}$.

> **Mini esempio.** Roulette, puntata da \$1 su un numero, vincita \$35, $p=1/38$: $\langle x\rangle=35\cdot\frac{1}{38}-1\cdot\frac{37}{38}=-\frac{2}{38}\approx-0.0526$ ($-5.26\%$). Su 100 000 giocate la perdita attesa è $\approx\$5260$.

#### **8.2. Legge dei grandi numeri**

| Formulazione       | Enunciato                                                                                                                   |
| ------------------ | --------------------------------------------------------------------------------------------------------------------------- |
| I — conteggio      | $\displaystyle\lim_{n\to\infty}\frac{k}{n}=p$; numero atteso di occorrenze $k=pn$                                           |
| II — valore atteso | $\displaystyle\lim_{n\to\infty} m = \langle x\rangle$, con $m=\frac1n\sum_i x_i$; totale atteso $t=\langle x\rangle\cdot n$ |

La prima è il caso particolare della seconda con $x=1$ se l'evento accade, $x=0$ altrimenti.

#### **8.3. Disuguaglianza di Chebyshev (Tchebichev)**

$$P\big(|X-\mu|\ge r\big) \le \frac{\sigma^2}{r^2} \qquad\Longleftrightarrow\qquad P\big(|X-\mu|<k\sigma\big)\ge 1-\frac{1}{k^2}$$

| $k$ | Garanzia di Chebyshev (qualunque distribuzione) | Valore effettivo per una gaussiana |
| --- | ----------------------------------------------- | ---------------------------------- |
| 2   | $\ge 50\%$                                      | $95.45\%$                          |
| 3   | $\ge 88.9\%$                                    | $99.73\%$                          |

> 📌 Chebyshev è un **limite inferiore valido per ogni distribuzione**, molto conservativo. Se si sa che la distribuzione è normale, si usano i valori esatti dei tre sigma.

#### **8.4. Dimostrazione della legge debole (12/02/2025 D1b — traccia da 4 punti)**

Ipotesi: $X_1,\dots,X_n$ i.i.d. con media $\mu$ e varianza $\sigma^2<\infty$; $\bar X_n=\frac1n\sum X_i$.

1. $E[\bar X_n]=\frac1n\sum E[X_i]=\mu$.
2. Per l'indipendenza $\mathrm{Var}\!\left(\sum X_i\right)=n\sigma^2$, quindi $\mathrm{Var}(\bar X_n)=\frac{1}{n^2}\,n\sigma^2=\frac{\sigma^2}{n}$.
3. Chebyshev con $r=\varepsilon$: $\;0\le P(|\bar X_n-\mu|\ge\varepsilon)\le \dfrac{\sigma^2}{n\varepsilon^2}\xrightarrow[n\to\infty]{}0$.
4. Per confronto: $\displaystyle\lim_{n\to\infty}P(|\bar X_n-\mu|\ge\varepsilon)=0$. $\square$

Il passo 2 richiede $\mathrm{Var}(X+Y)=\sigma_X^2+\sigma_Y^2$, dimostrata via covarianza in [L3 §3](L3%20-%20M3%20Formulario%20-%20Somme,%20campionamento%20e%20inferenza.md).

#### **8.5. Simulazione Monte Carlo**

$$p \approx \frac{k}{n} \qquad\text{esempio:}\quad \pi \approx 4\,\frac{k}{n}\ \ (\text{punti dentro il cerchio inscritto nel quadrato})$$

Accuratezza empirica: ogni $10^2$ prove in più guadagna **una cifra decimale**; errore relativo $\approx 3/\sqrt n$.

---

### **9. Riepilogo formule M1**

| Formula                                                | Nome                  | Quando                                |     |     |
| ------------------------------------------------------ | --------------------- | ------------------------------------- | --- | --- |
| $P(E)=\#(E)/\#(S)$                                     | definizione classica  | esiti equiprobabili                   |     |     |
| $P(A^c)=1-P(A)$                                        | complemento           | "almeno", "nessuno"                   |     |     |
| $P(A\cup B)=P(A)+P(B)-P(A\cap B)$                      | inclusione-esclusione | unione con sovrapposizione            |     |     |
| $P(A\mid B)=P(A\cap B)/P(B)$                           | condizionata          | "dato che"                            |     |     |
| $P(A\cap B)=P(A)P(B\mid A)$                            | prodotto              | estrazioni in sequenza                |     |     |
| $P(A\cap B)=P(A)P(B)$                                  | prodotto (indip.)     | con reinserimento                     |     |     |
| $P(B)=\sum_i P(A_i)P(B\mid A_i)$                       | alternative / totale  | più vie verso lo stesso esito         |     |     |
| $P(H\mid D)=\frac{P(H)P(D\mid H)}{P(D)}$               | **Bayes**             | dall'effetto alla causa               |     |     |
| $R_S=\prod R_i$                                        | serie                 | tutti devono funzionare               |     |     |
| $R_P=1-\prod(1-R_i)$                                   | parallelo             | basta uno                             |     |     |
| $P(T)=pP(T\mid C)+qP(T\mid C^c)$                       | condizionamento       | reti a ponte                          |     |     |
| $\langle x\rangle=\sum x_i P_i$                        | valore atteso         | giochi, guadagni                      |     |     |
| $P(\lvert X-\mu\rvert \ge r)\le \dfrac{\sigma^2}{r^2}$ | **Chebyshev**         | scostamento dalla media di almeno $r$ |
