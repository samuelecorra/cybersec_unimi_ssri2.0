# **M4 UD2 Lezione 3 - Forma esponenziale e funzioni complesse**

### **1. Statuto della formula di Eulero**

La formula di Eulero è

$$
e^{i\theta}=\cos\theta+i\sin\theta,
\qquad \theta\in\mathbb R.
$$

In M04 la assumiamo come definizione operativa dell'esponenziale su numeri immaginari puri. Non la si può dedurre semplicemente dalle leggi dell'esponenziale reale, perché $i\theta$ non è reale e l'esponenziale complesso deve prima essere definito.

Una giustificazione rigorosa sarà disponibile dopo lo studio delle serie di potenze:

$$
e^z=\sum_{n=0}^{\infty}\frac{z^n}{n!},
$$

separando termini pari e dispari per $z=i\theta$. Le serie appartengono a M06 e gli sviluppi di Taylor ai moduli successivi.

> 📌 Qui Eulero è uno strumento dichiaratamente anticipato; nessuna dimostrazione circolare viene nascosta.

---

### **2. Forma esponenziale**

Se $z\ne0$ e

$$
z=\rho(\cos\theta+i\sin\theta),
$$

allora

$$
z=\rho e^{i\theta}.
$$

Poiché $e^{i(\theta+2k\pi)}=e^{i\theta}$,

$$
z=\rho e^{i(\theta+2k\pi)},\qquad k\in\mathbb Z.
$$

La scrittura con argomento principale è

$$
z=|z|e^{i\operatorname{Arg}(z)}.
$$

Esempio:

$$
-1-i=\sqrt2e^{-3\pi i/4}.
$$

---

### **3. Calcolo in forma esponenziale**

Per $z_1=\rho_1e^{i\theta_1}$ e $z_2=\rho_2e^{i\theta_2}\ne0$,

$$
z_1z_2=\rho_1\rho_2e^{i(\theta_1+\theta_2)},
$$

$$
\frac{z_1}{z_2}=\frac{\rho_1}{\rho_2}e^{i(\theta_1-\theta_2)},
$$

e, per $n\in\mathbb Z$,

$$
z_1^n=\rho_1^ne^{in\theta_1}.
$$

Queste formule sono riscritture compatte dei risultati trigonometrici già dimostrati; non costituiscono una prova indipendente se le proprietà dell'esponenziale complesso non sono ancora state costruite.

---

### **4. Esponenziale su tutto C**

#### **4.1 Definizione operativa**

Per $z=x+iy$ definiamo

$$
e^z=e^x(\cos y+i\sin y).
$$

La parte reale controlla il modulo, quella immaginaria la fase:

$$
|e^{x+iy}|=e^x,
$$

$$
\arg(e^{x+iy})\equiv y\pmod{2\pi}.
$$

In particolare $e^z\ne0$ per ogni $z\in\mathbb C$.

#### **4.2 Legge additiva**

Se $z=x+iy$ e $w=u+iv$, allora

$$
e^{z+w}=e^{x+u}[\cos(y+v)+i\sin(y+v)].
$$

Usando le formule di addizione,

$$
e^{z+w}=e^ze^w.
$$

Ne segue

$$
e^{-z}=\frac1{e^z}.
$$

#### **4.3 Periodicità e non iniettività**

Per ogni $z\in\mathbb C$ e $k\in\mathbb Z$,

$$
e^{z+2k\pi i}=e^z.
$$

Più precisamente,

$$
e^z=e^w\iff z-w\in2\pi i\mathbb Z.
$$

**Dimostrazione.** L'uguaglianza dei moduli dà $e^{\operatorname{Re}z}=e^{\operatorname{Re}w}$ e quindi $\operatorname{Re}z=\operatorname{Re}w$. L'uguaglianza delle fasi dà $\operatorname{Im}z-\operatorname{Im}w\in2\pi\mathbb Z$. Il viceversa segue dalla periodicità. $\square$

L'esponenziale complesso è suriettivo da $\mathbb C$ a $\mathbb C\setminus\{0\}$, ma non è iniettivo.

<!-- TODO FIGURA: piano z con strisce orizzontali di altezza 2pi e piano immagine senza origine, mostrando punti z, z+2pi i con la stessa immagine e modulo e^x; obiettivo didattico: visualizzare periodicità, non iniettività e assenza dello zero nell'immagine. -->

---

### **5. Identità di Eulero**

Ponendo $\theta=\pi$,

$$
e^{i\pi}=\cos\pi+i\sin\pi=-1,
$$

da cui

$$
e^{i\pi}+1=0.
$$

L'identità raccoglie in una sola relazione $0$, $1$, $i$, $\pi$ ed $e$, ma il suo valore matematico deriva soprattutto dal legame strutturale tra esponenziale e rotazioni.

---

### **6. Seno e coseno complessi**

Le identità di Eulero suggeriscono, per ogni $z\in\mathbb C$, le definizioni

$$
\cos z=\frac{e^{iz}+e^{-iz}}2,
$$

$$
\sin z=\frac{e^{iz}-e^{-iz}}{2i}.
$$

Per $z=x\in\mathbb R$ coincidono con seno e coseno reali. Da queste definizioni seguono algebricamente

$$
e^{iz}=\cos z+i\sin z
$$

e

$$
\cos^2z+\sin^2z=1.
$$

Seguono inoltre, per ogni $z,w\in\mathbb C$,

$$
\cos(z+w)=\cos z\cos w-\sin z\sin w,
$$

$$
\sin(z+w)=\sin z\cos w+\cos z\sin w,
$$

e quindi le formule di duplicazione. Per argomenti reali queste identità coincidono con quelle già dimostrate in M01; qui si osserva che le definizioni esponenziali le estendono a $\mathbb C$.

La seconda identità resta valida in $\mathbb C$, ma $\sin z$ e $\cos z$ non sono limitati. Per esempio, ponendo $z=iy$,

$$
\cos(iy)=\frac{e^{-y}+e^y}{2}=\cosh y,
$$

$$
\sin(iy)=i\frac{e^y-e^{-y}}2=i\sinh y.
$$

Questo collega le funzioni trigonometriche alle iperboliche studiate in M01.

> ⚠️ Le funzioni complesse sono presentate come approfondimento controllato. Limiti, continuità, derivazione e olomorfia non appartengono a M04.

---

### **7. Equazioni esponenziali elementari**

#### **7.1 Equazione e^z=1**

Da periodicità e criterio di uguaglianza,

$$
e^z=1\iff z=2k\pi i,\qquad k\in\mathbb Z.
$$

#### **7.2 Equazione e^z=-e**

Scriviamo

$$
-e=e^{1+i(\pi+2k\pi)}.
$$

Le soluzioni sono

$$
z=1+(2k+1)\pi i,\qquad k\in\mathbb Z.
$$

#### **7.3 Sistema su modulo e fase**

Per risolvere $e^{x+iy}=2i$, confrontiamo:

$$
e^x=2,
$$

$$
y=\frac\pi2+2k\pi.
$$

Quindi

$$
x=\ln2,\qquad y=\frac\pi2+2k\pi.
$$

---

### **8. Applicazioni e confini**

I numeri $e^{i\theta}$ descrivono rotazioni e oscillazioni. Compaiono in:

- circuiti e segnali sinusoidali, mediante fasori;
- trasformata discreta e veloce di Fourier;
- elaborazione di immagini e audio;
- sistemi dinamici lineari;
- meccanica quantistica e telecomunicazioni.

Nelle equazioni differenziali lineari, radici caratteristiche $a\pm bi$ producono termini reali del tipo

$$
e^{at}(A\cos bt+B\sin bt).
$$

Questo è soltanto un collegamento motivazionale: la teoria delle equazioni differenziali verrà progettata dopo il completamento di M05-M11, non in M04.

Le radici dell'unità sono il nucleo algebrico della DFT e della FFT. Codici e crittografia usano spesso polinomi e curve ellittiche, ma normalmente su **campi finiti**, non direttamente sul campo $\mathbb C$; il richiamo riguarda tecniche algebriche comuni, non l'identità dei contesti.

L'uso applicativo non richiede confondere il piano complesso con grandezze fisiche: il complesso è uno strumento che codifica simultaneamente ampiezza e fase.

---

### **9. Errori frequenti**

1. Considerare dimostrata Eulero usando proprietà non ancora definite dell'esponenziale complesso.
2. Scrivere $e^{z+w}=e^z+e^w$.
3. Dimenticare la periodicità $2\pi i$ nelle equazioni.
4. Affermare che $e^z$ può essere $0$.
5. Confondere $e^{i\theta}$, che ha modulo $1$, con $e^{-\theta}$.
6. Applicare a seno e coseno complessi la limitatezza reale.

---

### **10. Esercizi**

1. Scrivi in forma esponenziale $-2$, $3i$, $-1+\sqrt3i$ e tutti i relativi argomenti.
2. Calcola $e^{2+3\pi i/2}$ e $|e^{-1+7i}|$.
3. Dimostra $\overline{e^z}=e^{\overline z}$.
4. Risolvi $e^z=i$, $e^z=-4$ ed $e^{2z}=1$.
5. Dimostra che $e^z=e^{\overline z}$ se e solo se $\operatorname{Im}z\in\pi\mathbb Z$.
6. Ricava dalle definizioni $\cos(-z)=\cos z$ e $\sin(-z)=-\sin z$.
7. Verifica $\cos(iy)=\cosh y$ e $\sin(iy)=i\sinh y$ per $y=\ln2$.
8. Spiega perché $e^z$ non possiede un'inversa globale su $\mathbb C$.

---

### **11. Riepilogo**

> ✅ La forma $z=\rho e^{i\theta}$ condensa modulo e fase; l'esponenziale complesso soddisfa $e^{z+w}=e^ze^w$, non si annulla ed è periodico di periodo $2\pi i$.

$$
e^{x+iy}=e^x(\cos y+i\sin y),
$$

$$
e^z=e^w\iff z-w\in2\pi i\mathbb Z.
$$

La prova analitica mediante serie è correttamente rinviata a M06 e ai moduli successivi; M04 usa queste formule in modo operativo e coerente.
