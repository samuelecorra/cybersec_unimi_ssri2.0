# **M1 UD3 Lezione 1 - Polinomi nelle disequazioni**

### **1. Introduzione**

Le disequazioni polinomiali sono il primo contesto in cui il calcolo algebrico deve essere affiancato da uno **studio del segno** ordinato.

Le lezioni preliminari `L0A`-`L0E` hanno definito polinomi, prodotti notevoli, divisione, Ruffini, teoremi del resto e del fattore, radici e molteplicità. Qui useremo quegli strumenti per risolvere disequazioni senza ripeterne integralmente le dimostrazioni.

Un’equazione chiede dove un’espressione vale zero. Una disequazione chiede invece dove un’espressione è positiva, negativa, non negativa o non positiva:

$$
P(x)>0,\quad P(x)\ge 0,\quad P(x)<0,\quad P(x)\le 0
$$

dove $P(x)$ è un polinomio reale.

L’idea centrale è semplice:

> 📌 Per risolvere una disequazione polinomiale bisogna capire in quali intervalli della retta reale il polinomio mantiene segno costante.

Gli zeri del polinomio dividono la retta reale in intervalli. Dentro ciascun intervallo il segno non cambia, perché un polinomio può cambiare segno solo passando da uno zero.

---

### **2. Richiamo: che cos’è un polinomio reale**

Un **polinomio reale** in una variabile è un’espressione del tipo:

$$
P(x)=a_nx^n+a_{n-1}x^{n-1}+\dots+a_1x+a_0
$$

con:

- $a_0,a_1,\dots,a_n\in\mathbb{R}$;
- $a_n\neq 0$;
- $n\in\mathbb{N}$.

Il numero $n$ è il **grado** del polinomio.

Esempi:

$$
P(x)=2x^3-5x^2+x-7
$$

è un polinomio di terzo grado, mentre:

$$
Q(x)=x^4-1
$$

è un polinomio di quarto grado.

---

### **3. Zeri e fattorizzazione**

Uno **zero** di un polinomio $P$ è un numero reale $\alpha$ tale che:

$$
P(\alpha)=0
$$

Se $\alpha$ è uno zero, allora il fattore $(x-\alpha)$ divide il polinomio.

Questa affermazione è precisamente il teorema del fattore dimostrato in `L0D - Teoremi del resto e del fattore.md`.

Per esempio:

$$
P(x)=x^2-5x+6
$$

ha zeri $2$ e $3$, quindi:

$$
P(x)=(x-2)(x-3)
$$

Questa forma è molto più utile per le disequazioni, perché il segno del prodotto si ottiene dal segno dei singoli fattori.

---

### **4. Segno di un prodotto**

La regola elementare è:

- il prodotto di un numero pari di fattori negativi è positivo;
- il prodotto di un numero dispari di fattori negativi è negativo;
- se almeno un fattore è zero, il prodotto è zero.

Per una disequazione del tipo:

$$
(x-2)(x+1)(x-4)>0
$$

non conviene sviluppare. Conviene studiare il segno dei tre fattori:

$$
x-2,\quad x+1,\quad x-4
$$

I punti critici sono:

$$
x=-1,\quad x=2,\quad x=4
$$

Essi dividono la retta in quattro intervalli:

$$
(-\infty,-1),\quad (-1,2),\quad (2,4),\quad (4,+\infty)
$$

Su ciascun intervallo ogni fattore mantiene segno costante.

---

### **5. Metodo generale per disequazioni polinomiali fattorizzate**

Supponiamo di dover risolvere:

$$
P(x)>0
$$

e di riuscire a scrivere:

$$
P(x)=a(x-\alpha_1)(x-\alpha_2)\cdots(x-\alpha_k)
$$

con eventuali fattori ripetuti.

Il metodo è:

1. portare tutto a primo membro;
2. fattorizzare il polinomio;
3. trovare gli zeri reali;
4. ordinare gli zeri sulla retta;
5. studiare il segno del prodotto in ogni intervallo;
6. scegliere gli intervalli compatibili con il verso della disequazione;
7. includere gli zeri solo se il verso è $\ge$ oppure $\le$.

> ⚠️ Gli zeri non vanno inclusi nelle disequazioni strette $>$ e $<$, perché in quei punti il polinomio vale $0$.

---

### **6. Esempio completo: prodotto di fattori lineari**

Risolviamo:

$$
(x-1)(x+2)(x-5)\ge 0
$$

Gli zeri sono:

$$
x=-2,\quad x=1,\quad x=5
$$

Ordiniamoli:

$$
-2<1<5
$$

Gli intervalli sono:

$$
(-\infty,-2),\quad (-2,1),\quad (1,5),\quad (5,+\infty)
$$

Studiamo il segno scegliendo un punto di prova in ogni intervallo.

Per $x=-3$:

$$
(x-1)(x+2)(x-5)=(-4)(-1)(-8)<0
$$

Per $x=0$:

$$
(x-1)(x+2)(x-5)=(-1)(2)(-5)>0
$$

Per $x=2$:

$$
(x-1)(x+2)(x-5)=(1)(4)(-3)<0
$$

Per $x=6$:

$$
(x-1)(x+2)(x-5)=(5)(8)(1)>0
$$

La disequazione richiede $\ge 0$, quindi prendiamo gli intervalli positivi e includiamo gli zeri:

$$
x\in[-2,1]\cup[5,+\infty)
$$

---

### **7. Molteplicità degli zeri**

Uno zero può comparire più volte nella fattorizzazione.

Per esempio:

$$
P(x)=(x-2)^2(x+1)
$$

Lo zero $x=2$ ha **molteplicità pari**, mentre lo zero $x=-1$ ha **molteplicità dispari**.

La molteplicità è fondamentale per il segno:

> 📌 Attraversando uno zero di molteplicità dispari il segno cambia; attraversando uno zero di molteplicità pari il segno non cambia.

Perché? Il fattore $(x-\alpha)^m$:

- cambia segno quando $m$ è dispari;
- resta sempre non negativo quando $m$ è pari.

---

### **8. Esempio con radice doppia**

Risolviamo:

$$
(x-2)^2(x+1)<0
$$

Gli zeri sono:

$$
x=-1,\quad x=2
$$

Il fattore $(x-2)^2$ è sempre $\ge 0$ e vale zero solo per $x=2$.

Studiamo gli intervalli:

- per $x<-1$, $(x+1)<0$ e $(x-2)^2>0$, quindi il prodotto è negativo;
- per $-1<x<2$, $(x+1)>0$ e $(x-2)^2>0$, quindi il prodotto è positivo;
- per $x>2$, $(x+1)>0$ e $(x-2)^2>0$, quindi il prodotto è positivo.

La disequazione è stretta:

$$
(x-2)^2(x+1)<0
$$

quindi non includiamo gli zeri.

La soluzione è:

$$
x\in(-\infty,-1)
$$

Notiamo che $x=2$ non si include, anche se il segno non cambia lì, perché il polinomio vale zero.

---

### **9. Disequazioni di secondo grado come caso particolare**

Una disequazione di secondo grado:

$$
ax^2+bx+c>0
$$

si può risolvere studiando la parabola, ma anche fattorizzando quando possibile.

Se:

$$
ax^2+bx+c=a(x-x_1)(x-x_2)
$$

con $x_1<x_2$, allora:

- fuori dall’intervallo tra le radici il segno è quello di $a$;
- dentro l’intervallo tra le radici il segno è opposto a quello di $a$.

Per esempio:

$$
x^2-4x+3>0
$$

Fattorizziamo:

$$
x^2-4x+3=(x-1)(x-3)
$$

Il coefficiente principale è positivo. Quindi il polinomio è positivo fuori dalle radici:

$$
x\in(-\infty,1)\cup(3,+\infty)
$$

---

### **10. Quando il polinomio non si fattorizza in fattori lineari reali**

Non tutti i polinomi hanno radici reali.

Per esempio:

$$
x^2+1
$$

non si annulla mai in $\mathbb{R}$ e vale sempre:

$$
x^2+1>0
$$

Quindi:

$$
x^2+1\ge 0
$$

è vera per ogni $x\in\mathbb{R}$, mentre:

$$
x^2+1<0
$$

non ha soluzioni reali.

In generale, un fattore quadratico con discriminante negativo:

$$
ax^2+bx+c
$$

ha segno costante, uguale al segno di $a$.

> 💡 Un fattore quadratico senza radici reali non divide la retta in nuovi intervalli: non cambia mai segno.

---

### **11. Esempio con fattore quadratico sempre positivo**

Risolviamo:

$$
(x^2+1)(x-4)\le 0
$$

Il fattore:

$$
x^2+1
$$

è sempre positivo. Dunque il segno del prodotto dipende solo da:

$$
x-4
$$

La disequazione diventa, per quanto riguarda il segno:

$$
x-4\le 0
$$

quindi:

$$
x\le 4
$$

Poiché per $x=4$ il prodotto vale zero, il punto è incluso.

La soluzione è:

$$
(-\infty,4]
$$

---

### **12. Portare tutto a primo membro**

Prima di studiare il segno bisogna avere una disequazione nella forma:

$$
P(x)\ \square\ 0
$$

dove $\square$ è uno tra:

$$
>,\quad \ge,\quad <,\quad \le
$$

Per esempio:

$$
x^2+2x>3
$$

non si studia confrontando separatamente $x^2+2x$ e $3$. Si porta tutto a sinistra:

$$
x^2+2x-3>0
$$

poi si fattorizza:

$$
x^2+2x-3=(x+3)(x-1)
$$

La disequazione diventa:

$$
(x+3)(x-1)>0
$$

Il prodotto è positivo fuori dagli zeri:

$$
x\in(-\infty,-3)\cup(1,+\infty)
$$

---

### **13. Errore tipico: dividere per un’espressione di segno ignoto**

Consideriamo:

$$
(x-1)(x+2)>0
$$

Non è lecito dividere per $(x-1)$ scrivendo:

$$
x+2>0
$$

perché il verso della disequazione dipenderebbe dal segno di $(x-1)$:

- se $x-1>0$, il verso resta uguale;
- se $x-1<0$, il verso si inverte;
- se $x-1=0$, non si può dividere.

> ⚠️ In una disequazione non si divide mai per un’espressione contenente $x$ senza prima discuterne il segno.

Lo studio del segno serve proprio a evitare questo errore.

---

### **14. Scrittura finale della soluzione**

La soluzione può essere scritta in diversi modi equivalenti.

Per esempio:

$$
x\in(-\infty,-3)\cup(1,+\infty)
$$

oppure:

$$
x<-3\ \text{oppure}\ x>1
$$

Bisogna invece evitare scritture ambigue come:

$$
-3>x>1
$$

che non rappresentano correttamente l’unione di due intervalli.

---

### **15. Riepilogo**

> ✅ Una disequazione polinomiale si risolve portando tutto a primo membro, fattorizzando, trovando gli zeri e studiando il segno sui sottointervalli della retta reale.

Punti fondamentali:

- gli zeri dividono la retta reale in intervalli;
- il segno è costante tra due zeri consecutivi;
- gli zeri di molteplicità dispari fanno cambiare segno;
- gli zeri di molteplicità pari non fanno cambiare segno;
- gli zeri si includono solo con $\ge$ o $\le$;
- non si divide per espressioni di segno ignoto.

Questo metodo sarà la base per le disequazioni razionali fratte e per molte condizioni di dominio nelle funzioni.
