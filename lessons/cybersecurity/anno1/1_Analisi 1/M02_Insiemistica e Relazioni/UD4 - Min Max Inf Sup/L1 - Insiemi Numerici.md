# **M2 UD4 Lezione 1 - Insiemi Numerici**

### **1. Introduzione**

Gli insiemi numerici sono l’ambiente in cui si svolge l’Analisi. Non basta sapere che $\mathbb{N}$, $\mathbb{Z}$, $\mathbb{Q}$ e $\mathbb{R}$ sono insiemi di numeri: bisogna capire quali proprietà hanno e quali problemi risolvono.

La costruzione storica e concettuale è:

$$
\mathbb{N}\subseteq\mathbb{Z}\subseteq\mathbb{Q}\subseteq\mathbb{R}\subseteq\mathbb{C}
$$

Ogni passaggio nasce da un’esigenza matematica.

---

### **2. Numeri naturali**

I numeri naturali sono:

$$
\mathbb{N}
$$

Servono a contare.

A seconda della convenzione, possono iniziare da $0$ oppure da $1$. In molti contesti di Analisi e Informatica si usa:

$$
\mathbb{N}=\{0,1,2,3,\dots\}
$$

Se serve escludere lo zero, si può scrivere:

$$
\mathbb{N}^+=\{1,2,3,\dots\}
$$

---

### **3. Numeri interi**

I numeri interi sono:

$$
\mathbb{Z}=\{\dots,-3,-2,-1,0,1,2,3,\dots\}
$$

Nascono per rendere sempre possibile la sottrazione tra naturali.

Per esempio:

$$
2-5=-3
$$

non è un numero naturale, ma è un intero.

---

### **4. Numeri razionali**

I numeri razionali sono:

$$
\mathbb{Q}=\left\{\frac{p}{q}:p\in\mathbb{Z},\ q\in\mathbb{Z},\ q\neq 0\right\}
$$

Nascono per rendere possibile la divisione, tranne la divisione per zero.

Esempi:

$$
\frac{3}{5}\in\mathbb{Q}
$$

$$
-7=-\frac{7}{1}\in\mathbb{Q}
$$

Ogni intero è razionale.

---

### **5. Chiusura delle operazioni**

Dire che un insieme numerico è **chiuso** rispetto a un’operazione significa che applicando quell’operazione a elementi dell’insieme si resta nell’insieme.

Esempi:

- $\mathbb{N}$ è chiuso rispetto alla somma e al prodotto, ma non rispetto alla sottrazione;
- $\mathbb{Z}$ è chiuso rispetto a somma, prodotto e sottrazione, ma non rispetto alla divisione;
- $\mathbb{Q}$ è chiuso rispetto a somma, prodotto, sottrazione e divisione per un razionale non nullo.

Per esempio:

$$
2-5=-3\notin\mathbb{N}
$$

ma:

$$
2-5\in\mathbb{Z}
$$

Questa prospettiva spiega perché si passa da un insieme numerico al successivo.

---

### **6. Numeri irrazionali**

Non tutti i numeri reali sono razionali.

Numeri come:

$$
\sqrt{2},\quad \pi,\quad e
$$

non possono essere scritti come rapporto di due interi.

Si chiamano **irrazionali**.

Un fatto fondamentale è che la retta reale contiene sia razionali sia irrazionali, fittamente mescolati.

---

### **7. Numeri reali**

I numeri reali formano l’insieme:

$$
\mathbb{R}
$$

Geometricamente, $\mathbb{R}$ si identifica con la retta reale.

La proprietà decisiva dei reali, rispetto ai razionali, è la **completezza**: insiemi limitati superiormente hanno un estremo superiore reale.

Questa proprietà sarà formalizzata con il concetto di supremo.

> 📌 L’Analisi si fonda sulla completezza di $\mathbb{R}$.

---

### **8. Numeri complessi**

I numeri complessi formano l’insieme:

$$
\mathbb{C}
$$

e contengono i reali:

$$
\mathbb{R}\subseteq\mathbb{C}
$$

Servono per risolvere equazioni come:

$$
x^2+1=0
$$

che non hanno soluzioni reali. In Analisi 1 il lavoro principale resta su $\mathbb{R}$; $\mathbb{C}$ verrà usato quando servirà distinguere proprietà reali e proprietà complesse.

---

### **9. Densità di razionali e irrazionali**

I razionali sono **densi** in $\mathbb{R}$: tra due reali distinti $a<b$ esiste sempre un razionale $q$ tale che:

$$
a<q<b
$$

Anche gli irrazionali sono densi: tra due reali distinti esiste sempre un irrazionale $\alpha$ con:

$$
a<\alpha<b
$$

Questo significa che razionali e irrazionali sono intrecciati sulla retta reale: non esiste un intervallo reale non vuoto fatto solo di razionali o solo di irrazionali.

> 💡 La densità spiega perché gli intervalli reali sono molto più ricchi delle liste di punti: ogni zoom contiene ancora infiniti razionali e infiniti irrazionali.

---

### **10. Intervalli come sottoinsiemi di $\mathbb{R}$**

Gli intervalli sono sottoinsiemi speciali della retta reale.

Intervallo chiuso:

$$
[a,b]=\{x\in\mathbb{R}:a\le x\le b\}
$$

Intervallo aperto:

$$
(a,b)=\{x\in\mathbb{R}:a<x<b\}
$$

Semiretta:

$$
[a,+\infty)=\{x\in\mathbb{R}:x\ge a\}
$$

Gli intervalli saranno fondamentali per domini, limiti, continuità e derivate.

---

### **11. Limitatezza**

Un insieme $A\subseteq\mathbb{R}$ è **limitato superiormente** se esiste $M\in\mathbb{R}$ tale che:

$$
\forall x\in A,\ x\le M
$$

È **limitato inferiormente** se esiste $m\in\mathbb{R}$ tale che:

$$
\forall x\in A,\ m\le x
$$

È **limitato** se è limitato sia superiormente sia inferiormente.

Esempio:

$$
(0,1)
$$

è limitato, mentre:

$$
(0,+\infty)
$$

è limitato inferiormente ma non superiormente.

---

### **12. Riepilogo**

> ✅ Gli insiemi numerici formano una catena di estensioni che porta fino ai reali, l’ambiente naturale dell’Analisi.

Punti essenziali:

- $\mathbb{N}$ serve a contare;
- $\mathbb{Z}$ introduce gli opposti;
- $\mathbb{Q}$ introduce i rapporti;
- razionali e irrazionali sono densi nella retta reale;
- $\mathbb{R}$ completa la retta;
- $\mathbb{C}$ estende $\mathbb{R}$ ma non è l’ambiente ordinato di base dell’Analisi 1;
- gli intervalli sono sottoinsiemi fondamentali di $\mathbb{R}$;
- la limitatezza prepara minimo, massimo, infimo e supremo.
