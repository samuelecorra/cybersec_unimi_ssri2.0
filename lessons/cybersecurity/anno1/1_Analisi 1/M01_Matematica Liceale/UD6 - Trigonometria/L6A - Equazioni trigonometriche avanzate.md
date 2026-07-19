# **M1 UD6 Lezione 6A - Equazioni trigonometriche avanzate**

### **1. Strategia generale**

Le equazioni non elementari devono essere trasformate senza perdere il dominio o le soluzioni. Una procedura affidabile è:

1. determinare il dominio;
2. portare l'espressione a un solo membro;
3. scegliere un'identità che riduca il numero di funzioni o argomenti;
4. fattorizzare quando possibile;
5. separare i casi prima di dividere;
6. risolvere le equazioni elementari ottenute;
7. eliminare duplicazioni e applicare eventuali restrizioni.

### **2. Equazioni quadratiche in una funzione trigonometrica**

Risolviamo

$$
2\sin^2x-3\sin x+1=0.
$$

Poniamo $t=\sin x$, ricordando che $t\in[-1,1]$. Si ottiene

$$
2t^2-3t+1=(2t-1)(t-1)=0.
$$

Quindi $t=1/2$ oppure $t=1$, entrambi ammissibili. Le soluzioni sono

$$
x=\frac{\pi}{6}+2k\pi,\qquad x=\frac{5\pi}{6}+2k\pi,\qquad x=\frac{\pi}{2}+2k\pi,
$$

con $k\in\mathbb{Z}$.

> ⚠️ Una radice dell'equazione algebrica esterna a $[-1,1]$ non corrisponde ad alcun valore reale del seno o del coseno.

### **3. Equazioni omogenee di secondo grado**

Consideriamo

$$
a\sin^2x+b\sin x\cos x+c\cos^2x=0.
$$

Prima di dividere per $\cos^2x$ si controlla il caso $\cos x=0$. Se $x=\pi/2+k\pi$, l'equazione originaria diventa $a=0$; questi punti sono soluzioni soltanto se il coefficiente $a$ è nullo.

Nel caso $\cos x\neq 0$ si divide per $\cos^2x$:

$$
a\tan^2x+b\tan x+c=0.
$$

Ponendo $t=\tan x$ si risolve una quadratica ordinaria e poi si torna a $x$.

#### **3.1 Esempio**

Risolviamo

$$
\sin^2x-\sin x\cos x-2\cos^2x=0.
$$

Se $\cos x=0$, il primo membro vale $1$, quindi non si ottengono soluzioni. Possiamo dividere per $\cos^2x$:

$$
\tan^2x-\tan x-2=0.
$$

Fattorizzando,

$$
(\tan x-2)(\tan x+1)=0.
$$

Pertanto

$$
x=\arctan 2+k\pi
$$

oppure

$$
x=-\frac{\pi}{4}+k\pi,
$$

con $k\in\mathbb{Z}$.

### **4. Equazioni lineari in seno e coseno**

Per

$$
a\sin x+b\cos x=c,
$$

con $(a,b)\neq(0,0)$, poniamo

$$
R=\sqrt{a^2+b^2}.
$$

Scegliamo $\varphi$ in modo che

$$
\cos\varphi=\frac{a}{R},\qquad \sin\varphi=\frac{b}{R}.
$$

Allora, per la formula di addizione,

$$
R\sin(x+\varphi)
=R\sin x\cos\varphi+R\cos x\sin\varphi
=a\sin x+b\cos x.
$$

L'equazione diventa

$$
\sin(x+\varphi)=\frac{c}{R}.
$$

Esistono soluzioni reali se e solo se

$$
|c|\leq R.
$$

Geometricamente, $a\sin x+b\cos x$ è il prodotto scalare tra $(a,b)$ e il vettore unitario $(\sin x,\cos x)$; il suo valore assoluto non può superare la lunghezza $R$ del primo vettore.

#### **4.1 Esempio**

Risolviamo

$$
\sin x+\sqrt{3}\cos x=1.
$$

Qui $R=2$. Scegliamo $\varphi=\pi/3$, perché $\cos\varphi=1/2$ e $\sin\varphi=\sqrt{3}/2$. Quindi

$$
2\sin\left(x+\frac{\pi}{3}\right)=1.
$$

Ne segue

$$
x+\frac{\pi}{3}=\frac{\pi}{6}+2k\pi
$$

oppure

$$
x+\frac{\pi}{3}=\frac{5\pi}{6}+2k\pi.
$$

Pertanto

$$
x=-\frac{\pi}{6}+2k\pi
$$

oppure

$$
x=\frac{\pi}{2}+2k\pi.
$$

### **5. Prostaferesi e argomenti differenti**

Risolviamo

$$
\sin(2x)=\sin x.
$$

Portando tutto a sinistra e usando prostaferesi,

$$
\sin(2x)-\sin x
=2\cos\frac{3x}{2}\sin\frac{x}{2}=0.
$$

Quindi

$$
\cos\frac{3x}{2}=0
$$

oppure

$$
\sin\frac{x}{2}=0.
$$

Si ottengono

$$
x=\frac{\pi}{3}+\frac{2k\pi}{3}
$$

oppure

$$
x=2k\pi.
$$

Le due famiglie non si possono fondere in $x=k\pi/3$: tale scrittura includerebbe, per esempio, $x=2\pi/3$, che non soddisfa l'equazione. La soluzione corretta resta

$$
x=\frac{\pi}{3}+\frac{2k\pi}{3}
$$

oppure

$$
x=2k\pi,
$$

con $k\in\mathbb{Z}$.

### **6. Uso della sostituzione del semitangente**

La sostituzione $t=\tan(x/2)$ è utile quando seno e coseno compaiono razionalmente. Per esempio,

$$
\sin x+\cos x=0
$$

diventa, per $x\neq\pi+2k\pi$,

$$
\frac{2t}{1+t^2}+\frac{1-t^2}{1+t^2}=0,
$$

ossia

$$
t^2-2t-1=0.
$$

I valori $t=1\pm\sqrt{2}$ producono le famiglie corrispondenti. I punti esclusi dalla sostituzione devono essere verificati nell'equazione originaria; in questo esempio $x=\pi+2k\pi$ non è soluzione.

### **7. Equazioni con parametro**

L'equazione

$$
a\sin x+b\cos x=c
$$

ha:

- nessuna soluzione se $|c|>\sqrt{a^2+b^2}$;
- una classe di tangenza per periodo se $|c|=\sqrt{a^2+b^2}$;
- due soluzioni per periodo se $|c|<\sqrt{a^2+b^2}$.

Questa classificazione evita calcoli inutili e mostra il ruolo geometrico del parametro.

### **8. Esercizio completo su dominio, fattorizzazione e casi**

Risolviamo in $[0,2\pi)$

$$
\frac{\sin(2x)-\sin x}{\cos x}=0.
$$

Il dominio impone

$$
\cos x\neq 0,
$$

quindi $x\neq\pi/2$ e $x\neq3\pi/2$ nell'intervallo assegnato. Un rapporto è zero se il numeratore è zero e il denominatore non lo è. Per prostaferesi,

$$
\sin(2x)-\sin x
=2\cos\frac{3x}{2}\sin\frac{x}{2}.
$$

Il primo fattore si annulla per

$$
x=\frac{\pi}{3}+\frac{2k\pi}{3},
$$

il secondo per $x=2k\pi$. In $[0,2\pi)$ i candidati sono

$$
0,\quad \frac{\pi}{3},\quad \pi,\quad \frac{5\pi}{3}.
$$

Nessuno annulla il denominatore, quindi l'insieme soluzione è

$$
S=\left\{0,\frac{\pi}{3},\pi,\frac{5\pi}{3}\right\}.
$$

L'esercizio combina dominio, trasformazione, fattorizzazione, soluzione generale e restrizione a un intervallo.

### **9. Errori frequenti**

- dividere per $\sin x$ o $\cos x$ prima di studiarne gli zeri;
- dimenticare il vincolo $t\in[-1,1]$ nelle sostituzioni $t=\sin x$ o $t=\cos x$;
- usare $R=|a+b|$ invece di $R=\sqrt{a^2+b^2}$;
- scegliere $\varphi$ senza controllare entrambi i segni di seno e coseno;
- usare il semitangente senza verificare i punti in cui non è definito;
- mantenere famiglie duplicate come se rappresentassero soluzioni diverse.

### **10. Riepilogo**

> ✅ Le equazioni avanzate diventano elementari dopo una trasformazione scelta in base alla struttura, purché dominio e casi esclusi siano controllati prima di dividere o sostituire.
