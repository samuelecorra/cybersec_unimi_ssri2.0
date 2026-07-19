# **M4 UD3 Lezione 2 - Logaritmi e potenze in C**

### **1. Perché il logaritmo è multivalore**

Il logaritmo complesso nasce dalla risoluzione di

$$
e^w=z.
$$

Poiché $e^w\ne0$, è necessario $z\ne0$. Scriviamo

$$
w=u+iv
$$

e

$$
z=\rho e^{i\theta},\qquad \rho>0.
$$

L'uguaglianza

$$
e^u e^{iv}=\rho e^{i\theta}
$$

impone

$$
u=\ln\rho
$$

e

$$
v=\theta+2k\pi,\qquad k\in\mathbb Z.
$$

Quindi tutti e soli i logaritmi di $z$ sono

$$
\log z
=
\{\ln|z|+i(\theta+2k\pi):k\in\mathbb Z\}.
$$

Qui $\log z$ indica un insieme infinito di valori, non una funzione a valore singolo.

> 📌 Il multivalore non è un difetto di calcolo: riflette esattamente la periodicità $e^{w+2k\pi i}=e^w$.

---

### **2. Verifica di completezza**

Per ogni $k\in\mathbb Z$,

$$
e^{\ln|z|+i(\theta+2k\pi)}
=
|z|e^{i\theta}
=z,
$$

quindi ogni valore elencato è un logaritmo.

Viceversa, se $e^{u+iv}=z$, il confronto dei moduli dà $e^u=|z|$ e dunque $u=\ln|z|$; il confronto degli argomenti dà $v=\theta+2k\pi$. Non esistono altri valori.

Per $z=0$ non esiste alcun logaritmo, perché l'esponenziale complesso non si annulla.

---

### **3. Valore principale e ramo principale**

Con la convenzione

$$
\operatorname{Arg}(z)\in(-\pi,\pi],
$$

definiamo il **valore principale**

$$
\operatorname{Log}z
=
\ln|z|+i\operatorname{Arg}(z),
\qquad z\ne0.
$$

È una funzione su $\mathbb C\setminus\{0\}$, ma presenta un salto lungo il semiasse reale negativo: avvicinandosi da sopra l'argomento tende a $\pi$, da sotto tende a $-\pi$.

Per ottenere un ramo continuo e, in analisi complessa, olomorfo, si elimina il semiasse reale non positivo e si usa

$$
-\pi<\arg z<\pi.
$$

Il dominio è allora

$$
\mathbb C\setminus(-\infty,0].
$$

<!-- TODO FIGURA: piano complesso con taglio lungo il semiasse reale non positivo, valori di Arg prossimi a pi sopra e -pi sotto e superficie a elica dei valori del logaritmo; obiettivo didattico: distinguere valore principale, salto di ramo e multivalore. -->

> ⚠️ "Valore principale" è una selezione convenzionale; "ramo" include anche una scelta di dominio su cui tale selezione è regolare.

---

### **4. Esempi fondamentali**

#### **4.1 Logaritmo di 1**

$$
\log1=\{2k\pi i:k\in\mathbb Z\},
$$

mentre

$$
\operatorname{Log}1=0.
$$

#### **4.2 Logaritmo di -1**

$$
\log(-1)=\{(2k+1)\pi i:k\in\mathbb Z\},
$$

e, con la convenzione adottata,

$$
\operatorname{Log}(-1)=i\pi.
$$

#### **4.3 Logaritmo di 1+i**

Poiché $|1+i|=\sqrt2$ e $\operatorname{Arg}(1+i)=\pi/4$,

$$
\log(1+i)
=
\left\{
\frac12\ln2+i\left(\frac\pi4+2k\pi\right):k\in\mathbb Z
\right\}.
$$

Il valore principale è

$$
\operatorname{Log}(1+i)=\frac12\ln2+\frac\pi4i.
$$

---

### **5. Proprietà e cautele**

#### **5.1 Logaritmo multivalore del prodotto**

Interpretando $\log z$ come insieme e la somma come insieme di tutte le somme possibili,

$$
\log(zw)=\log z+\log w.
$$

Infatti moduli e argomenti si sommano, e $2\pi(m+n)$ percorre tutti i multipli di $2\pi$.

Analogamente, per $w\ne0$,

$$
\log\left(\frac zw\right)=\log z-\log w
$$

come uguaglianza tra insiemi di valori.

#### **5.2 Il valore principale non è additivo**

In generale,

$$
\operatorname{Log}(zw)
\ne
\operatorname{Log}z+\operatorname{Log}w.
$$

Controesempio: con $z=w=-1$,

$$
\operatorname{Log}(zw)=\operatorname{Log}1=0,
$$

ma

$$
\operatorname{Log}(-1)+\operatorname{Log}(-1)=2\pi i.
$$

I due membri differiscono di un multiplo di $2\pi i$.

#### **5.3 Potenze intere dentro il logaritmo**

Anche la scrittura $\log(z^n)=n\log z$ richiede cautela se entrambi i lati sono interpretati come insiemi. Per $n=2$ e $z=1$,

$$
\log(1^2)=\{2m\pi i:m\in\mathbb Z\},
$$

mentre moltiplicare ogni valore di $\log1$ per $2$ produce solo

$$
\{4k\pi i:k\in\mathbb Z\}.
$$

È sempre vero che

$$
n\log z\subseteq\log(z^n),
$$

ma l'uguaglianza insiemistica può fallire.

---

### **6. Potenze complesse**

#### **6.1 Definizione multivalore**

Per $z\ne0$ e $w\in\mathbb C$, si definisce

$$
z^w=\{e^{wL}:L\in\log z\}.
$$

Se $\log z=\ln|z|+i(\theta+2k\pi)$, allora

$$
z^w
=
\left\{
\exp\left(w[\ln|z|+i(\theta+2k\pi)]\right):k\in\mathbb Z
\right\}.
$$

La **potenza principale** è la selezione

$$
z^w_{\mathrm{pr}}=e^{w\operatorname{Log}z}.
$$

#### **6.2 Esponenti interi**

Se $w=n\in\mathbb Z$, tutti i valori coincidono perché

$$
e^{n\cdot2k\pi i}=1.
$$

Si recupera quindi l'ordinaria potenza intera, a valore unico.

#### **6.3 Esponenti razionali**

Se $w=p/q$ in forma ridotta, con $q\ge1$, i valori distinti sono in generale $q$ e coincidono con le soluzioni di

$$
\xi^q=z^p.
$$

Per esempio,

$$
(-1)^{1/2}=\{i,-i\}.
$$

#### **6.4 Esponenti non reali**

Le potenze possono avere valori inattesi. Per esempio,

$$
i^i
=
\left\{
e^{-(\pi/2+2k\pi)}:k\in\mathbb Z
\right\}.
$$

Sono infiniti valori reali positivi; il principale è

$$
i^i_{\mathrm{pr}}=e^{-\pi/2}.
$$

Anche

$$
1^i=\{e^{-2k\pi}:k\in\mathbb Z\}
$$

è multivalore, benché la base sia $1$.

> ⚠️ Le usuali leggi $(z^a)^b=z^{ab}$ e $(zw)^a=z^aw^a$ possono fallire per le potenze principali o richiedere una scelta coerente di rami.

---

### **7. Equazioni logaritmiche ed esponenziali**

#### **7.1 logaritmi assegnati**

L'equazione

$$
\operatorname{Log}z=2+i\frac\pi3
$$

ha soluzione unica

$$
z=e^2e^{i\pi/3}.
$$

Il valore assegnato ha parte immaginaria nel ramo $(-\pi,\pi]$.

#### **7.2 Equazione e^z=w**

Per $w\ne0$, le soluzioni sono precisamente

$$
z\in\log w.
$$

Per esempio,

$$
e^{2z}=1
$$

equivale a

$$
2z=2k\pi i,
$$

quindi

$$
z=k\pi i,\qquad k\in\mathbb Z.
$$

#### **7.3 Equazione con potenza principale**

La scrittura

$$
z^{1/2}_{\mathrm{pr}}=i
$$

non equivale semplicemente a $z=-1$ senza controllare il ramo. Con la convenzione $\operatorname{Arg}(-1)=\pi$, la radice quadrata principale di $-1$ è effettivamente $i$; l'altra radice $-i$ non è il valore principale.

---

### **8. Collegamenti futuri**

La geometria dei rami e dei tagli anticipa l'analisi complessa, non inclusa nel programma corrente. Per Analisi 1 sono invece rilevanti:

- M05: successioni come approssimazioni numeriche di esponenziali e radici;
- M06: serie esponenziale e giustificazione di Eulero;
- M08-M10: limiti, continuità, Taylor e derivate nel contesto reale;
- informatica: fase, Fourier, stabilità numerica e rappresentazione finita dei complessi.

Non si assumono qui continuità o derivabilità complesse.

---

### **9. Errori frequenti**

1. Scrivere $\log z$ come un solo numero senza dichiarare un ramo.
2. Dimenticare che $\log0$ non esiste.
3. Applicare l'additività reale a $\operatorname{Log}$ senza correzioni di $2\pi i$.
4. Confondere $\operatorname{Arg}z$ con tutti gli argomenti.
5. Trattare $z^w$ come univoca per ogni esponente.
6. Usare le leggi delle potenze senza verificare i rami.
7. Parlare di ramo continuo includendo il taglio lungo cui l'argomento salta.

---

### **10. Esercizi d'esame**

1. Determina tutti i valori di $\log(-i)$ e il valore principale.
2. Calcola $\operatorname{Log}(-1-i)$ con la convenzione adottata.
3. Verifica insiemisticamente $\log(zw)=\log z+\log w$ per $z=i$, $w=-1$.
4. Trova un controesempio diverso da $z=w=-1$ all'additività di $\operatorname{Log}$.
5. Risolvi $e^{3z}=2i$.
6. Determina tutti i valori di $(-8)^{1/3}$ mediante il logaritmo e confrontali con la formula delle radici.
7. Calcola tutti i valori di $(-1)^i$ e quello principale.
8. Confronta $(z^{1/2}_{\mathrm{pr}})^2$ e $(z^2)^{1/2}_{\mathrm{pr}}$ per $z=-1$.
9. Spiega perché $2\log1$ è un sottoinsieme proprio di $\log(1^2)$.
10. Determina per quali $z\ne0$ il valore principale $\operatorname{Log}z$ è reale.

---

### **11. Riepilogo**

> ✅ Il logaritmo complesso è l'insieme completo delle controimmagini dell'esponenziale; il valore principale ne seleziona una, mentre un ramo richiede anche un dominio coerente.

$$
\log z
=
\{\ln|z|+i(\operatorname{Arg}z+2k\pi):k\in\mathbb Z\},
$$

$$
z^w=\{e^{wL}:L\in\log z\}.
$$

Proprietà dei logaritmi e delle potenze devono essere formulate distinguendo uguaglianze tra insiemi, valori principali e scelte di ramo.
