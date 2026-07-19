# **M1 UD0 Lezione 4 - Binomio di Newton**

### **1. Introduzione**

Il binomio di Newton descrive lo sviluppo di una potenza:

$$
(a+b)^n
$$

senza dover moltiplicare il binomio per sé stesso $n$ volte. È un ponte naturale tra algebra, coefficienti binomiali, induzione e formule di Taylor.

Per piccoli esponenti conosciamo già:

$$
(a+b)^2=a^2+2ab+b^2
$$

e

$$
(a+b)^3=a^3+3a^2b+3ab^2+b^3.
$$

I coefficienti:

$$
1,\quad 2,\quad 1
$$

e:

$$
1,\quad 3,\quad 3,\quad 1
$$

sono coefficienti binomiali.

### **2. Enunciato del binomio di Newton**

Per ogni $n\in\mathbb{N}$ e per ogni $a,b\in\mathbb{R}$ vale:

$$
(a+b)^n=\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k.
$$

Il termine generale è:

$$
\binom{n}{k}a^{n-k}b^k.
$$

Quando $k$ aumenta:

- l'esponente di $a$ scende da $n$ a $0$;
- l'esponente di $b$ sale da $0$ a $n$;
- la somma degli esponenti resta sempre $n$:

$$
(n-k)+k=n.
$$

Il numero dei termini è $n+1$, perché $k$ assume i valori:

$$
0,1,2,\dots,n.
$$

### **3. Espansione dei primi casi**

Per $n=0$:

$$
(a+b)^0=1.
$$

La formula dà:

$$
\sum_{k=0}^{0}\binom{0}{k}a^{0-k}b^k
=
\binom{0}{0}a^0b^0=1.
$$

Per $n=1$:

$$
(a+b)^1=\binom{1}{0}a+\binom{1}{1}b=a+b.
$$

Per $n=2$:

$$
(a+b)^2
=
\binom{2}{0}a^2+\binom{2}{1}ab+\binom{2}{2}b^2
=
a^2+2ab+b^2.
$$

Per $n=3$:

$$
(a+b)^3
=
\binom{3}{0}a^3+\binom{3}{1}a^2b+\binom{3}{2}ab^2+\binom{3}{3}b^3.
$$

Quindi:

$$
(a+b)^3=a^3+3a^2b+3ab^2+b^3.
$$

### **4. Interpretazione combinatoria**

Scriviamo:

$$
(a+b)^n=(a+b)(a+b)\cdots(a+b).
$$

Ci sono $n$ fattori. Per ottenere un termine dello sviluppo bisogna scegliere, da ciascun fattore, o $a$ oppure $b$.

Per ottenere:

$$
a^{n-k}b^k
$$

bisogna scegliere $b$ da esattamente $k$ fattori e $a$ dai restanti $n-k$ fattori. Il numero di modi per scegliere quali sono i $k$ fattori da cui prendere $b$ è:

$$
\binom{n}{k}.
$$

Per questo il coefficiente di $a^{n-k}b^k$ è proprio $\binom{n}{k}$.

### **5. Dimostrazione per induzione**

Dimostriamo il binomio di Newton per ogni $n\ge 0$.

#### **5.1 Caso base**

Per $n=0$:

$$
(a+b)^0=1.
$$

Il secondo membro è:

$$
\sum_{k=0}^{0}\binom{0}{k}a^{0-k}b^k
=
\binom{0}{0}a^0b^0=1.
$$

Quindi la formula è vera per $n=0$.

#### **5.2 Ipotesi induttiva**

Supponiamo che per un certo $n\ge 0$ valga:

$$
(a+b)^n=\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k.
$$

#### **5.3 Passo induttivo**

Dobbiamo dimostrare:

$$
(a+b)^{n+1}=\sum_{k=0}^{n+1}\binom{n+1}{k}a^{n+1-k}b^k.
$$

Partiamo dal primo membro:

$$
(a+b)^{n+1}=(a+b)(a+b)^n.
$$

Usiamo l'ipotesi induttiva:

$$
(a+b)^{n+1}
=
(a+b)\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k.
$$

Distribuiamo:

$$
(a+b)^{n+1}
=
a\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k
+
b\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k.
$$

Quindi:

$$
(a+b)^{n+1}
=
\sum_{k=0}^{n}\binom{n}{k}a^{n+1-k}b^k
+
\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^{k+1}.
$$

Nel secondo sommatorio facciamo il cambio di indice $j=k+1$. Quando $k=0$, $j=1$; quando $k=n$, $j=n+1$. Inoltre $k=j-1$. Otteniamo:

$$
\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^{k+1}
=
\sum_{j=1}^{n+1}\binom{n}{j-1}a^{n+1-j}b^j.
$$

Rinominiamo $j$ in $k$:

$$
\sum_{j=1}^{n+1}\binom{n}{j-1}a^{n+1-j}b^j
=
\sum_{k=1}^{n+1}\binom{n}{k-1}a^{n+1-k}b^k.
$$

Allora:

$$
(a+b)^{n+1}
=
\sum_{k=0}^{n}\binom{n}{k}a^{n+1-k}b^k
+
\sum_{k=1}^{n+1}\binom{n}{k-1}a^{n+1-k}b^k.
$$

Separiamo i termini estremi e sommiamo quelli con $1\le k\le n$:

$$
(a+b)^{n+1}
=
a^{n+1}
+
\sum_{k=1}^{n}\left[\binom{n}{k}+\binom{n}{k-1}\right]a^{n+1-k}b^k
+
b^{n+1}.
$$

Per la relazione di Pascal:

$$
\binom{n}{k}+\binom{n}{k-1}=\binom{n+1}{k}.
$$

Inoltre:

$$
a^{n+1}=\binom{n+1}{0}a^{n+1}
$$

e

$$
b^{n+1}=\binom{n+1}{n+1}b^{n+1}.
$$

Quindi:

$$
(a+b)^{n+1}
=
\sum_{k=0}^{n+1}\binom{n+1}{k}a^{n+1-k}b^k.
$$

Abbiamo dimostrato il passo induttivo. Per induzione, il binomio di Newton vale per ogni $n\ge 0$.

### **6. Collegamento con il triangolo di Pascal**

La riga $n$ del triangolo di Pascal contiene i coefficienti dello sviluppo di $(a+b)^n$.

Per esempio, la riga $4$ è:

$$
1,\quad 4,\quad 6,\quad 4,\quad 1.
$$

Quindi:

$$
(a+b)^4=a^4+4a^3b+6a^2b^2+4ab^3+b^4.
$$

La relazione di Pascal spiega perché ogni riga si ottiene dalla precedente: quando si moltiplica per $(a+b)$, i coefficienti adiacenti si sommano.

### **7. Esempi di sviluppo**

Sviluppiamo:

$$
(x+2)^4.
$$

Usiamo:

$$
(a+b)^4=a^4+4a^3b+6a^2b^2+4ab^3+b^4.
$$

Con $a=x$ e $b=2$:

$$
(x+2)^4
=
x^4+4x^3\cdot 2+6x^2\cdot 2^2+4x\cdot 2^3+2^4.
$$

Quindi:

$$
(x+2)^4=x^4+8x^3+24x^2+32x+16.
$$

### **8. Segni nello sviluppo di $(a-b)^n$**

Per sviluppare:

$$
(a-b)^n
$$

si usa il binomio con $b$ sostituito da $-b$:

$$
(a-b)^n=\sum_{k=0}^{n}\binom{n}{k}a^{n-k}(-b)^k.
$$

Poiché:

$$
(-b)^k=(-1)^kb^k,
$$

si ottiene:

$$
(a-b)^n=\sum_{k=0}^{n}(-1)^k\binom{n}{k}a^{n-k}b^k.
$$

Esempio:

$$
(x-1)^5
=
x^5-5x^4+10x^3-10x^2+5x-1.
$$

> ⚠️ I segni alternano perché il termine $(-b)^k$ è positivo per $k$ pari e negativo per $k$ dispari.

### **9. Trovare un coefficiente senza sviluppare tutto**

Vogliamo trovare il coefficiente di $x^7$ nello sviluppo di:

$$
(2x-3)^{10}.
$$

Il termine generale è:

$$
\binom{10}{k}(2x)^{10-k}(-3)^k.
$$

La potenza di $x$ è:

$$
x^{10-k}.
$$

Vogliamo:

$$
10-k=7.
$$

Quindi:

$$
k=3.
$$

Il termine corrispondente è:

$$
\binom{10}{3}(2x)^7(-3)^3.
$$

Il coefficiente di $x^7$ è:

$$
\binom{10}{3}2^7(-3)^3.
$$

Calcoliamo:

$$
\binom{10}{3}=120,
$$

quindi:

$$
120\cdot 128\cdot(-27)=-414720.
$$

Il coefficiente cercato è:

$$
-414720.
$$

### **10. Identità ottenute dal binomio**

Ponendo $a=1$ e $b=1$:

$$
(1+1)^n=\sum_{k=0}^{n}\binom{n}{k}.
$$

Quindi:

$$
\sum_{k=0}^{n}\binom{n}{k}=2^n.
$$

Ponendo $a=1$ e $b=-1$:

$$
(1-1)^n=\sum_{k=0}^{n}(-1)^k\binom{n}{k}.
$$

Per $n\ge 1$, il primo membro vale $0$, quindi:

$$
\sum_{k=0}^{n}(-1)^k\binom{n}{k}=0.
$$

Questa identità dice che, per $n\ge 1$, la somma dei coefficienti di posto pari e quella dei coefficienti di posto dispari coincidono.

### **11. Problema misto**

Calcoliamo:

$$
\sum_{k=0}^{4}\binom{4}{k}2^{4-k}3^k.
$$

Riconosciamo il binomio di Newton con $a=2$, $b=3$, $n=4$:

$$
\sum_{k=0}^{4}\binom{4}{k}2^{4-k}3^k=(2+3)^4.
$$

Quindi:

$$
\sum_{k=0}^{4}\binom{4}{k}2^{4-k}3^k=5^4=625.
$$

Questo esempio mostra come il binomio di Newton permetta non solo di sviluppare potenze, ma anche di riconoscere somme già strutturate.

### **12. Errori frequenti**

#### **12.1 Invertire gli esponenti senza coerenza**

Nella formula:

$$
(a+b)^n=\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k
$$

gli esponenti devono sempre sommare a $n$. Scrivere $a^kb^k$ è sbagliato, perché il grado totale diventerebbe $2k$.

#### **12.2 Dimenticare i segni in $(a-b)^n$**

Lo sviluppo:

$$
(a-b)^4=a^4+4a^3b+6a^2b^2+4ab^3+b^4
$$

è falso: quello è lo sviluppo di $(a+b)^4$. Per $(a-b)^4$ i segni alternano:

$$
(a-b)^4=a^4-4a^3b+6a^2b^2-4ab^3+b^4.
$$

#### **12.3 Confondere coefficiente e termine**

Nel termine:

$$
\binom{n}{k}a^{n-k}b^k
$$

il coefficiente binomiale è solo:

$$
\binom{n}{k}.
$$

Se $a$ o $b$ contengono coefficienti numerici, come in $(2x-3)^n$, anche quei coefficienti contribuiscono al coefficiente finale della potenza di $x$.

### **13. Collegamenti futuri**

Il binomio di Newton verrà usato:

- nello studio di successioni e ricorrenze;
- nelle stime asintotiche;
- nelle serie di potenze;
- nella formula di Taylor;
- nel riconoscimento di coefficienti senza sviluppare interamente un'espressione;
- in probabilità, dove $\binom{n}{k}$ conta combinazioni di eventi.

### **14. Riepilogo**

> ✅ Il binomio di Newton espande $(a+b)^n$ usando coefficienti binomiali e termini di grado totale $n$.

Formula fondamentale:

$$
(a+b)^n=\sum_{k=0}^{n}\binom{n}{k}a^{n-k}b^k.
$$

Punti chiave:

- i termini sono $n+1$;
- gli esponenti di $a$ decrescono;
- gli esponenti di $b$ crescono;
- i coefficienti sono quelli della riga $n$ del triangolo di Pascal;
- per $(a-b)^n$ compaiono segni alterni;
- si può trovare un singolo coefficiente senza sviluppare tutto.
