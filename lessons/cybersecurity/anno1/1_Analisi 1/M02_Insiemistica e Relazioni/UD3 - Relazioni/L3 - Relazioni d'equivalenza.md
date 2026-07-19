# **M2 UD3 Lezione 3 - Relazioni d'equivalenza**

### **1. Introduzione**

Una relazione di equivalenza formalizza l’idea di “essere uguali rispetto a un certo criterio”.

Non sempre interessa l’uguaglianza assoluta. A volte vogliamo considerare equivalenti oggetti diversi che condividono una proprietà: numeri con lo stesso resto, frazioni che rappresentano lo stesso razionale, punti che appartengono alla stessa classe.

> 📌 Una relazione di equivalenza raggruppa gli elementi in classi disgiunte.

---

### **2. Definizione**

Una relazione $R$ su un insieme $A$ è una **relazione di equivalenza** se è:

- riflessiva;
- simmetrica;
- transitiva.

Si scrive spesso:

$$
a\sim b
$$

per indicare che $a$ è equivalente a $b$.

---

### **3. Esempio: congruenza modulo $n$**

Fissato $n\in\mathbb{N}$ con $n\ge 2$, su $\mathbb{Z}$ definiamo:

$$
a\sim b \iff n \text{ divide } a-b
$$

Si dice che $a$ e $b$ sono congruenti modulo $n$.

Per esempio, modulo $3$:

$$
8\sim 2
$$

perché:

$$
8-2=6
$$

ed è divisibile per $3$.

---

### **4. Verifica delle proprietà**

La congruenza modulo $n$ è riflessiva:

$$
a-a=0
$$

e $0$ è divisibile per $n$.

È simmetrica: se $n$ divide $a-b$, allora divide anche:

$$
b-a=-(a-b)
$$

È transitiva: se $n$ divide $a-b$ e $n$ divide $b-c$, allora divide la somma:

$$
(a-b)+(b-c)=a-c
$$

Quindi:

$$
a\sim c
$$

---

### **5. Classe di equivalenza**

Dato $a\in A$, la **classe di equivalenza** di $a$ è:

$$
[a]=\{x\in A: x\sim a\}
$$

È l’insieme di tutti gli elementi equivalenti ad $a$.

Nel caso della congruenza modulo $3$:

$$
[0]=\{\dots,-6,-3,0,3,6,\dots\}
$$

$$
[1]=\{\dots,-5,-2,1,4,7,\dots\}
$$

$$
[2]=\{\dots,-4,-1,2,5,8,\dots\}
$$

---

### **6. Partizione**

Le classi di equivalenza formano una **partizione** dell’insieme $A$:

- ogni elemento di $A$ appartiene a una classe;
- due classi sono uguali oppure disgiunte;
- l’unione di tutte le classi è $A$.

Questo significa che una relazione di equivalenza divide l’insieme in blocchi senza sovrapposizioni.

---

### **7. Perché le classi sono uguali o disgiunte**

Dimostriamo il punto centrale. Siano $[a]$ e $[b]$ due classi di equivalenza.

Se:

$$
[a]\cap[b]\neq\varnothing
$$

allora esiste $z$ tale che:

$$
z\sim a,\qquad z\sim b
$$

Per simmetria, da $z\sim a$ segue $a\sim z$. Per transitività, da $a\sim z$ e $z\sim b$ segue:

$$
a\sim b
$$

Ora prendiamo $x\in[a]$. Allora $x\sim a$; poiché $a\sim b$, per transitività:

$$
x\sim b
$$

quindi $x\in[b]$. Abbiamo mostrato $[a]\subseteq[b]$. Scambiando i ruoli di $a$ e $b$ si ottiene $[b]\subseteq[a]$, dunque:

$$
[a]=[b]
$$

Quindi due classi o sono disgiunte o coincidono.

---

### **8. Da partizioni a relazioni di equivalenza**

Vale anche il viceversa: ogni partizione di $A$ definisce una relazione di equivalenza.

Sia $\mathcal{F}$ una partizione di $A$. Definiamo:

$$
a\sim b \iff a \text{ e } b \text{ appartengono allo stesso blocco di } \mathcal{F}
$$

La relazione è riflessiva perché ogni elemento appartiene a un blocco; è simmetrica perché “stare nello stesso blocco” non dipende dall’ordine; è transitiva perché se $a$ sta nello stesso blocco di $b$ e $b$ nello stesso blocco di $c$, allora tutti e tre stanno nello stesso blocco.

> 📌 Relazioni di equivalenza e partizioni sono due modi equivalenti di descrivere la stessa struttura.

---

### **9. Insieme quoziente**

L’insieme delle classi di equivalenza si chiama **insieme quoziente**:

$$
A/R
$$

Nel caso della congruenza modulo $3$:

$$
\mathbb{Z}/3\mathbb{Z}=\{[0],[1],[2]\}
$$

Anche se $\mathbb{Z}$ è infinito, il quoziente modulo $3$ ha tre classi.

---

### **10. Esempio con frazioni**

Le frazioni:

$$
\frac{1}{2},\quad \frac{2}{4},\quad \frac{3}{6}
$$

sono scritture diverse dello stesso numero razionale.

Formalmente, sulle coppie $(a,b)$ con $b\neq 0$, si definisce:

$$
(a,b)\sim(c,d) \iff ad=bc
$$

La classe di equivalenza di $(1,2)$ contiene tutte le coppie che rappresentano lo stesso razionale.

---

### **11. Esercizi rappresentativi**

1. Su $\mathbb{Z}$, la relazione:

$$
a\sim b \iff a-b \text{ è pari}
$$

è una relazione di equivalenza. Le classi sono:

$$
[0]=\{\text{interi pari}\},\qquad [1]=\{\text{interi dispari}\}
$$

Il quoziente ha due classi:

$$
\mathbb{Z}/\sim=\{[0],[1]\}
$$

2. Su $\mathbb{R}$, la relazione:

$$
xRy \iff x<y
$$

non è una equivalenza: è irriflessiva e transitiva, ma non è riflessiva né simmetrica.

3. Sia $A=\{1,2,3,4\}$ e sia:

$$
\mathcal{F}=\{\{1,3\},\{2,4\}\}
$$

La partizione induce la relazione:

$$
x\sim y \iff x,y \text{ appartengono allo stesso blocco di } \mathcal{F}
$$

quindi $1\sim 3$, $2\sim 4$, ma $1\not\sim 2$.

---

### **12. Riepilogo**

> ✅ Una relazione di equivalenza è riflessiva, simmetrica e transitiva, e produce classi di equivalenza.

Punti essenziali:

- $a\sim b$ significa che $a$ e $b$ sono equivalenti rispetto a un criterio;
- la classe $[a]$ contiene tutti gli elementi equivalenti ad $a$;
- le classi formano una partizione;
- due classi di equivalenza sono disgiunte oppure uguali;
- ogni partizione induce una relazione di equivalenza;
- l’insieme quoziente raccoglie le classi;
- congruenze modulo $n$ e frazioni equivalenti sono esempi fondamentali.
