# **M1 UD2 Lezione 2A - Radicali e razionalizzazione**

### **1. Radici come operazioni inverse controllate**

Il simbolo di radice non indica “tutte le soluzioni” di un'equazione. Per $n\ge2$, la radice $n$-esima principale di un numero $a$ è un valore scelto in modo univoco.

Se $n$ è pari e $a\ge0$, $\sqrt[n]{a}$ è l'unico numero **non negativo** $r$ tale che $r^n=a$. Se $n$ è dispari e $a\in\mathbb{R}$, $\sqrt[n]{a}$ è l'unico reale $r$ tale che $r^n=a$.

Per esempio:

$$
\sqrt{9}=3
$$

mentre l'equazione $x^2=9$ ha due soluzioni:

$$
x=\pm3
$$

> 📌 La radice principale è una funzione; le radici di un'equazione formano invece un insieme di soluzioni.

### **2. Indice pari e indice dispari**

Per $n=2k$ pari:

$$
\sqrt[2k]{a}\text{ è definita in }\mathbb{R}\iff a\ge0
$$

e il risultato è sempre non negativo. Per $n=2k+1$ dispari:

$$
\sqrt[2k+1]{a}\text{ è definita per ogni }a\in\mathbb{R}
$$

e conserva il segno del radicando.

Ne segue la distinzione fondamentale:

$$
\sqrt[2k]{x^{2k}}=|x|,
\qquad
\sqrt[2k+1]{x^{2k+1}}=x
$$

La prima identità contiene il valore assoluto perché la radice pari deve essere non negativa. Per $x=-2$, infatti, $\sqrt{x^2}=\sqrt4=2=|x|$, non $x$.

### **3. Proprietà e loro ipotesi**

Per indice pari, se $a,b\ge0$:

$$
\sqrt[n]{ab}=\sqrt[n]{a}\cdot\sqrt[n]{b}
$$

Se inoltre $b>0$:

$$
\sqrt[n]{\frac ab}=\frac{\sqrt[n]{a}}{\sqrt[n]{b}}
$$

Per indice dispari le stesse identità valgono per radicandi reali, purché i denominatori siano non nulli. Le ipotesi non sono decorative: nei reali la scrittura $\sqrt{(-1)(-1)}=\sqrt{-1}\sqrt{-1}$ non è lecita, perché i radicali al secondo membro non esistono.

Non esiste invece una proprietà distributiva sulla somma:

$$
\sqrt{a+b}\ne\sqrt a+\sqrt b
$$

in generale. Per $a=b=1$, il primo membro vale $\sqrt2$ e il secondo $2$.

### **4. Estrarre e introdurre fattori**

Se l'indice è pari, l'estrazione corretta tiene conto del valore assoluto:

$$
\sqrt{a^2b}=|a|\sqrt b,\qquad b\ge0
$$

Se è noto che $a\ge0$, allora $|a|=a$. Per esempio:

$$
\sqrt{72}=\sqrt{36\cdot2}=6\sqrt2
$$

Per introdurre un fattore non negativo sotto una radice pari:

$$
c\sqrt[n]{a}=\sqrt[n]{c^n a},\qquad c\ge0
$$

Se $c<0$, il segno meno deve restare fuori.

### **5. Radicali simili e riduzione allo stesso indice**

Si possono sommare soltanto radicali con uguale indice e radicando dopo la semplificazione:

$$
2\sqrt8-\sqrt{18}=4\sqrt2-3\sqrt2=\sqrt2
$$

Per moltiplicare radicali di indici diversi si può usare un indice comune. Per esempio:

$$
\sqrt[2]{a}\cdot\sqrt[3]{a}=\sqrt[6]{a^3}\cdot\sqrt[6]{a^2}=\sqrt[6]{a^5},\qquad a\ge0
$$

La condizione $a\ge0$ rende non ambigue tutte le potenze coinvolte.

### **6. Razionalizzazione con un radicale monomio**

Razionalizzare significa riscrivere una frazione in forma equivalente senza radicali al denominatore. Non è un obbligo matematico, ma spesso semplifica confronti e manipolazioni successive.

Per esempio:

$$
\frac{3}{\sqrt5}=\frac{3}{\sqrt5}\cdot\frac{\sqrt5}{\sqrt5}=\frac{3\sqrt5}{5}
$$

Per un indice superiore si completa la potenza nel radicando:

$$
\frac{1}{\sqrt[3]{2}}
=\frac{\sqrt[3]{4}}{\sqrt[3]{2}\sqrt[3]{4}}
=\frac{\sqrt[3]{4}}{2}
$$

### **7. Razionalizzazione mediante il coniugato**

Il **coniugato** di $a+\sqrt b$ è $a-\sqrt b$. Il prodotto elimina il radicale grazie alla differenza di quadrati:

$$
(a+\sqrt b)(a-\sqrt b)=a^2-b
$$

Per esempio:

$$
\frac{1}{3-\sqrt5}
=\frac{3+\sqrt5}{(3-\sqrt5)(3+\sqrt5)}
=\frac{3+\sqrt5}{4}
$$

Con due radicali:

$$
\frac{1}{\sqrt x+\sqrt y}
=\frac{\sqrt x-\sqrt y}{x-y}
$$

quando $x,y\ge0$ e $x\ne y$. Le condizioni impediscono sia radicali non reali sia un denominatore nullo.

### **8. Un'identità utile senza anticipare i limiti**

L'espressione:

$$
\sqrt{x+1}-\sqrt x
$$

può essere moltiplicata e divisa per il coniugato:

$$
\sqrt{x+1}-\sqrt x
=\frac{(x+1)-x}{\sqrt{x+1}+\sqrt x}
=\frac{1}{\sqrt{x+1}+\sqrt x}
$$

per $x\ge0$. Questa trasformazione mette in evidenza una struttura altrimenti nascosta e verrà usata nello studio dei limiti.

### **9. Potenze razionali e riduzione dell'esponente**

Prima di interpretare $a^{m/n}$ si scrive $m/n$ in forma ridotta. Senza questa precauzione, la stessa frazione può suggerire domini diversi:

$$
\frac13=\frac26
$$

ma $\sqrt[3]{-8}$ è reale, mentre la scrittura $\sqrt[6]{(-8)^2}$ usa una radice pari. Nel corso si adotta la convenzione seguente:

- per $a>0$, $a^{m/n}$ è definita per ogni razionale $m/n$ e le leggi delle potenze sono pienamente coerenti;
- per $a=0$, si richiede $m/n>0$;
- per $a<0$, si usa la frazione ridotta e si ammette il valore reale soltanto se il denominatore è dispari;
- per esponenti reali non razionali, la base deve essere positiva.

Per esempio:

$$
(-8)^{2/3}=\left(\sqrt[3]{-8}\right)^2=4
$$

mentre $(-8)^{1/2}$ non è reale.

### **10. Esempio misto**

Semplifichiamo, specificando il dominio:

$$
\frac{\sqrt{12x^2}}{\sqrt3}
$$

Poiché i radicandi devono essere non negativi, l'espressione è definita per ogni $x\in\mathbb{R}$. Inoltre:

$$
\frac{\sqrt{12x^2}}{\sqrt3}
=\sqrt{4x^2}
=2|x|
$$

Il risultato $2x$ sarebbe corretto soltanto sotto l'ipotesi aggiuntiva $x\ge0$.

### **11. Errori frequenti**

- Confondere $\sqrt{x^2}$ con $x$.
- Dimenticare che $\sqrt a$ è la radice principale non negativa.
- Distribuire una radice su una somma o differenza.
- Estrarre un fattore da una radice pari senza il valore assoluto.
- Applicare prodotto e quoziente fuori dalle condizioni di esistenza.
- Razionalizzare moltiplicando soltanto il denominatore.
- Interpretare un esponente razionale senza ridurne la frazione.

### **12. Riepilogo**

> ✅ Le proprietà dei radicali sono trasformazioni condizionate dal dominio. Scrivere esplicitamente tali condizioni evita sia identità false sia perdite di informazione sul segno.

Questa lezione prepara le equazioni e disequazioni irrazionali di UD4 e le razionalizzazioni che ricompariranno nei limiti.
