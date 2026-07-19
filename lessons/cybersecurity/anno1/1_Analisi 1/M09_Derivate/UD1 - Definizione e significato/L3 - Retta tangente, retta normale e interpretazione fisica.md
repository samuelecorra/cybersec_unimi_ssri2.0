# **M9 UD1 Lezione 3 - Retta tangente, retta normale e interpretazione fisica**

### **1. Obiettivo della lezione**

Consolidiamo le due letture della derivata: quella **geometrica** (tangente e normale, con tutti i casi particolari) e quella **fisico-applicativa** (velocità, accelerazione, grandezze marginali, sensibilità).

---
### **2. La retta tangente**

Se $f$ è derivabile in $x_0$, la **retta tangente** al grafico nel punto $P=(x_0,f(x_0))$ è

$$
y = f(x_0) + f'(x_0)\,(x-x_0)
$$

È l'unica retta per $P$ che approssima $f$ "meglio di ogni altra retta" vicino a $x_0$ — affermazione resa precisa dalla Lezione 5 (approssimazione lineare). Casi particolari:

- $f'(x_0)=0$: **tangente orizzontale** $y=f(x_0)$ (candidati massimi/minimi: Fermat, UD5/L1);
- derivata infinita: **tangente verticale** $x=x_0$, senza derivabilità (Lezione 2, punto 4);
- punto angoloso: **due semitangenti** diverse, nessuna tangente unica;
- oscillazione del rapporto incrementale (es. $x\sin\frac 1x$ in $0$): **nessuna tangente**, nemmeno verticale (UD4/L1);
- nei punti **non interni** o isolati del dominio la tangente bilaterale non è definita (al più semitangenti ai bordi).

> ⚠️ "Tangente" non significa "retta che tocca il grafico in un solo punto": la tangente a $y=x^3$ in $0$ (che è $y=0$) **attraversa** il grafico, e la tangente a $y=\sin x$ in $\frac{\pi}{2}$ lo tocca infinite volte. La definizione corretta è quella per posizione limite delle secanti / migliore approssimazione lineare, non quella euclidea della circonferenza.

**Esempio.** $f(x)=e^x$ in $x_0=0$: $f(0)=1$, $f'(0)=1$: tangente $y=1+x$. In $x_0=1$: tangente $y=e+e(x-1)=ex$ — la tangente per l'origine!

---
### **3. La retta normale**

La **retta normale** in $P$ è la perpendicolare alla tangente per $P$. Se $f'(x_0)\neq 0$, il suo coefficiente angolare è l'antireciproco (prodotto dei coefficienti $=-1$, M01):

$$
y = f(x_0) - \frac{1}{f'(x_0)}\,(x-x_0)
$$

Casi degeneri: se $f'(x_0)=0$ la tangente è orizzontale e la normale è la **verticale** $x=x_0$; se la tangente è verticale, la normale è l'orizzontale $y=f(x_0)$.

**Esempio.** $f(x)=x^2$ in $x_0=1$: tangente $y=2x-1$, normale $y=1-\frac{1}{2}(x-1)=\frac{3-x}{2}$.

<!-- TODO FIGURA:
Grafico di una parabola y=x^2 con punto P=(1,1) evidenziato, retta tangente y=2x-1 e retta normale y=(3-x)/2 tratteggiate, angolo retto tra le due segnato in P; riquadro con i coefficienti m_tan=2 e m_norm=-1/2 e il prodotto -1.
Obiettivo didattico: visualizzare tangente e normale nello stesso punto e la relazione di antireciprocita' dei coefficienti angolari.
-->

---
### **4. Interpretazione cinematica**

Se $s(t)$ è la posizione di un punto su una retta al tempo $t$:

$$
v(t) = s'(t) \qquad\text{(velocità istantanea)},
\qquad
a(t) = v'(t) = s''(t) \qquad\text{(accelerazione)}
$$

La velocità media su $[t_0,t_0+h]$ è il rapporto incrementale; la velocità istantanea è il suo limite. Esempio della caduta libera: $s(t)=\frac{1}{2}gt^2$ dà $v(t)=gt$ e $a(t)=g$ costante — la lettura fisica delle derivate di $t^2$.

Osservazioni di metodo, spesso richieste:

- le **unità di misura** si trasformano coerentemente: se $s$ è in metri e $t$ in secondi, $v=s'$ è in $\mathrm{m/s}$ e $a$ in $\mathrm{m/s^2}$ (la derivata è un rapporto di incrementi, e ne eredita le unità);
- il segno di $v$ dice il **verso** del moto, il suo modulo la rapidità;
- la derivata è il limite delle **differenze finite** misurabili: nella pratica sperimentale si osserva $\frac{\Delta s}{\Delta t}$ su intervalli piccoli, e il modello matematico idealizza quel processo (torneremo sulla differenza tra derivata e stima numerica in UD6/L4).

---
### **5. Grandezze marginali, sensibilità ed elasticità**

La stessa struttura si ritrova fuori dalla fisica:

- **costo marginale**: se $C(q)$ è il costo per produrre quantità $q$, $C'(q)$ approssima il costo dell'unità aggiuntiva ("quanto costa produrre un'unità in più, al margine");
- **densità**: se $M(x)$ è la massa del tratto $[0,x]$ di un'asta, $M'(x)$ è la densità lineare nel punto;
- **sensibilità**: $f'(x_0)$ misura quanto l'uscita reagisce a una piccola perturbazione dell'ingresso: $\Delta f\approx f'(x_0)\Delta x$ (Lezione 5). È la lettura che rende la derivata centrale nell'analisi degli errori e nell'ottimizzazione numerica (UD6/L4);
- **tasso di crescita relativo**: $\dfrac{f'(x)}{f(x)}$ (crescita percentuale istantanea; per $f(t)=e^{kt}$ vale costantemente $k$);
- **elasticità**: $E(x)=\dfrac{x\,f'(x)}{f(x)}$, variazione percentuale dell'uscita per variazione percentuale unitaria dell'ingresso — la versione adimensionale della sensibilità, usata in economia e nella propagazione relativa degli errori.

> 💡 In tutte queste letture la derivata è un **tasso istantaneo di variazione**: la geometria (pendenza) e la fisica (velocità) sono due incarnazioni dello stesso limite. È il motivo per cui un solo teorema sulle derivate produce conseguenze in campi diversissimi.

---
### **6. Esercizi**

**Esercizio 1.** Scrivi tangente e normale a $f(x)=\ln x$ in $x_0=1$.

*Soluzione.* $f(1)=0$, $f'(x)=\frac 1x$, $f'(1)=1$: tangente $y=x-1$, normale $y=-(x-1)=1-x$.

**Esercizio 2.** Trova i punti del grafico di $f(x)=x^3-3x$ in cui la tangente è orizzontale, e quelli in cui è parallela alla retta $y=9x$.

*Soluzione.* $f'(x)=3x^2-3$. Orizzontale: $x=\pm 1$, punti $(1,-2)$ e $(-1,2)$. Parallela a $y=9x$: $3x^2-3=9$, $x=\pm 2$, punti $(2,2)$ e $(-2,-2)$.

**Esercizio 3.** Un corpo si muove con $s(t)=t^3-6t^2+9t$ (metri, secondi). Trova velocità e accelerazione, gli istanti in cui il corpo è fermo, e in quali intervalli si muove nel verso positivo.

*Soluzione.* $v(t)=3t^2-12t+9=3(t-1)(t-3)$, $a(t)=6t-12$. Fermo per $t=1$ e $t=3$; $v>0$ per $t<1$ o $t>3$ (verso positivo), $v<0$ in $(1,3)$.

**Esercizio 4.** Per $f(x)=x^\alpha$ ($x>0$) calcola l'elasticità.

*Soluzione.* $E(x)=\frac{x\cdot\alpha x^{\alpha-1}}{x^\alpha}=\alpha$: le potenze hanno elasticità costante — è la loro caratterizzazione economica.

---
### **7. Riepilogo**

> ✅ Tangente $y=f(x_0)+f'(x_0)(x-x_0)$, unica retta di miglior approssimazione locale (non "retta che tocca una volta sola"); normale con coefficiente $-\frac{1}{f'(x_0)}$ e casi degeneri gestiti a parte; tangenti orizzontali/verticali, semitangenti e assenza di tangente secondo la classificazione della UD4. Fisica: $v=s'$, $a=s''$, unità coerenti; applicazioni: marginale, densità, sensibilità $\Delta f\approx f'\Delta x$, tasso relativo $\frac{f'}{f}$, elasticità $\frac{xf'}{f}$.
