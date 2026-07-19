# **M4 UD1 Lezione 3A - Luoghi geometrici e trasformazioni**

### **1. Perché studiare i luoghi complessi**

Una condizione algebrica su $z=x+yi$ descrive spesso una figura del piano. Il metodo fondamentale consiste nel:

1. interpretare geometricamente modulo, parte reale e argomento;
2. tradurre in coordinate solo quando serve;
3. controllare uguaglianze, disuguaglianze e punti esclusi.

Questa lettura prepara la geometria delle trasformazioni e, in prospettiva, le funzioni di variabile complessa.

---

### **2. Rette e semipiani**

La condizione

$$
\operatorname{Re}(z)=a
$$

descrive la retta verticale $x=a$; analogamente,

$$
\operatorname{Im}(z)=b
$$

descrive la retta orizzontale $y=b$.

Le disuguaglianze

$$
\operatorname{Re}(z)>a,\qquad \operatorname{Im}(z)\le b
$$

descrivono rispettivamente un semipiano aperto a destra e un semipiano chiuso inferiore.

Una retta generale

$$
\alpha x+\beta y=\gamma
$$

può essere scritta come

$$
\operatorname{Re}\bigl((\alpha-i\beta)z\bigr)=\gamma.
$$

Infatti, se $z=x+yi$,

$$
(\alpha-i\beta)z=(\alpha x+\beta y)+i(\alpha y-\beta x).
$$

---

### **3. Luoghi definiti da distanze**

#### **3.1 Circonferenze e corone**

La condizione

$$
|z-z_0|=r
$$

con $r>0$ descrive una circonferenza. La doppia disuguaglianza

$$
r<|z-z_0|\le R
$$

con $0\le r<R$ descrive una corona circolare aperta sul bordo interno e chiusa su quello esterno.

#### **3.2 Asse di un segmento**

Per $z_1\ne z_2$,

$$
|z-z_1|=|z-z_2|
$$

descrive l'asse del segmento con estremi $z_1,z_2$.

#### **3.3 Ellissi e iperboli**

Fissati due fuochi distinti $z_1,z_2$, la condizione

$$
|z-z_1|+|z-z_2|=2a
$$

descrive un'ellisse se $2a>|z_1-z_2|$. La condizione

$$
\bigl||z-z_1|-|z-z_2|\bigr|=2a
$$

descrive un'iperbole se $0<2a<|z_1-z_2|$.

<!-- TODO FIGURA: quattro pannelli con semipiano, corona, asse di segmento ed ellisse a due fuochi; indicare bordi inclusi/esclusi, centri e fuochi; obiettivo didattico: associare condizioni su Re, Im e distanze ai rispettivi luoghi. -->

---

### **4. Luoghi definiti da rapporti**

Per $z_1\ne z_2$ e $k>0$, consideriamo

$$
\frac{|z-z_1|}{|z-z_2|}=k,
$$

con $z\ne z_2$.

- Se $k=1$, il luogo è l'asse del segmento $z_1z_2$.
- Se $k\ne1$, il luogo è una circonferenza di Apollonio.

Esempio: risolviamo

$$
|z-1|=2|z+1|.
$$

Ponendo $z=x+yi$ e quadrando,

$$
(x-1)^2+y^2=4\bigl((x+1)^2+y^2\bigr).
$$

Riducendo e completando il quadrato,

$$
\left(x+\frac53\right)^2+y^2=\left(\frac43\right)^2.
$$

Il punto $z=-1$, escluso dal rapporto iniziale, non appartiene comunque alla circonferenza ottenuta.

---

### **5. Luoghi definiti dall'argomento**

Fissato $z_0$ e un angolo $\theta$, la condizione

$$
\arg(z-z_0)=\theta+2k\pi
$$

descrive la semiretta uscente da $z_0$ con direzione $\theta$, escluso il vertice $z_0$.

La condizione

$$
\alpha<\operatorname{Arg}(z-z_0)<\beta
$$

descrive un settore angolare, purché l'intervallo sia interpretato coerentemente con il ramo principale.

Più in generale,

$$
\arg\left(\frac{z-z_1}{z-z_2}\right)=\theta\pmod{2\pi}
$$

esprime l'angolo orientato tra i vettori $z-z_2$ e $z-z_1$. Per $\theta\not\equiv0\pmod\pi$ il luogo è un arco di circonferenza passante per $z_1,z_2$, con i punti $z_1,z_2$ esclusi dalla formula.

<!-- TODO FIGURA: semiretta arg(z-z0)=theta, settore alpha<Arg(z-z0)<beta e arco a angolo orientato costante tra z1 e z2; evidenziare vertici esclusi; obiettivo didattico: leggere l'argomento come direzione e angolo orientato. -->

---

### **6. Traslazioni, omotetie e rotazioni**

#### **6.1 Traslazione**

La mappa

$$
T_b(z)=z+b
$$

trasla ogni punto del vettore $b$ e conserva le distanze:

$$
|T_b(z)-T_b(w)|=|z-w|.
$$

#### **6.2 Moltiplicazione per un complesso**

Scriviamo $a=\rho(\cos\theta+i\sin\theta)$ con $a\ne0$. La mappa

$$
M_a(z)=az
$$

moltiplica le distanze per $\rho=|a|$ e ruota gli argomenti di $\theta$:

$$
|az-aw|=|a|\,|z-w|.
$$

- Se $|a|=1$, è una rotazione attorno all'origine.
- Se $a=r>0$, è un'omotetia di rapporto $r$.
- Se $a=-1$, è la rotazione di angolo $\pi$.

La giustificazione angolare sarà completata in UD2 mediante il prodotto in forma trigonometrica.

#### **6.3 Similitudine diretta**

La trasformazione affine

$$
f(z)=az+b,\qquad a\ne0,
$$

è la composizione di una rotazione, un'omotetia e una traslazione. È biiettiva e

$$
f^{-1}(w)=\frac{w-b}{a}.
$$

<!-- TODO FIGURA: triangolo originale e sua immagine mediante z mapsto az+b, con freccia di traslazione b, fattore di scala |a| e angolo arg(a); obiettivo didattico: scomporre una similitudine complessa nelle sue componenti geometriche. -->

---

### **7. Coniugio e riflessioni**

La mappa

$$
C(z)=\overline z
$$

è la riflessione rispetto all'asse reale. Conserva distanze ma inverte l'orientamento:

$$
|\overline z-\overline w|=|z-w|.
$$

La trasformazione

$$
f(z)=a\overline z+b,\qquad a\ne0,
$$

è una similitudine inversa: combina riflessione, rotazione, omotetia e traslazione.

Esempio: $f(z)=i\overline z$ manda $1$ in $i$ e $i$ in $1$; è la riflessione rispetto alla retta $y=x$.

---

### **8. Inversione**

Per $z\ne0$,

$$
\frac1z=\frac{\overline z}{|z|^2}.
$$

Geometricamente l'inversione:

- sostituisce il modulo $r$ con $1/r$;
- cambia l'argomento $\theta$ in $-\theta$.

In particolare, i punti della circonferenza unitaria restano su di essa e

$$
|z|=1\implies \frac1z=\overline z.
$$

<!-- TODO FIGURA: due punti sulla stessa semiretta/coniugata con moduli r e 1/r rispetto alla circonferenza unitaria, più un punto unitario fisso in modulo; obiettivo didattico: visualizzare l'effetto di z mapsto 1/z su modulo e argomento. -->

---

### **9. Problema trasversale svolto**

Determiniamo l'immagine del disco

$$
D=\{z:|z-(1-i)|\le2\}
$$

mediante $f(z)=(1+i)z-2$.

Il centro diventa

$$
f(1-i)=(1+i)(1-i)-2=2-2=0.
$$

Per $w=f(z)$,

$$
|w-f(1-i)|=|(1+i)(z-(1-i))|=\sqrt2\,|z-(1-i)|.
$$

Quindi l'immagine è

$$
f(D)=\{w:|w|\le2\sqrt2\}.
$$

La moltiplicazione ruota anche la figura di $\pi/4$, ma un disco centrato non cambia aspetto sotto rotazione.

---

### **10. Errori frequenti**

1. Elevare al quadrato un'equazione di moduli senza controllare che entrambi i membri siano non negativi.
2. Perdere punti esclusi quando compare un denominatore o un argomento.
3. Confondere $az+b$ con una sola traslazione.
4. Affermare che $z\mapsto1/z$ conserva le distanze.
5. Usare $\operatorname{Arg}$ in un intervallo che attraversa il taglio senza esplicitare la convenzione.

---

### **11. Esercizi**

1. Descrivi e rappresenta $\{z:-1<\operatorname{Re}(z)\le2,\ |z|<3\}$.
2. Determina il luogo $|z-2|=|z-2i|$.
3. Riduci in forma cartesiana $|z-i|=3|z+i|$.
4. Descrivi $1<|z+1-i|\le4$ e indica quali bordi appartengono al luogo.
5. Trova l'immagine della circonferenza $|z-1|=2$ mediante $w=2iz+1$.
6. Determina la trasformazione $w=az+b$ che manda $0$ in $1+i$ e $1$ in $3+i$.
7. Verifica che $z\mapsto\overline z$ inverte l'orientamento di un triangolo non degenere.
8. Risolvi $\left|\dfrac{z-1}{z+1}\right|=1$ indicando il punto escluso.
9. Descrivi geometricamente $\operatorname{Arg}(z-i)=\pi/4$.

---

### **12. Riepilogo**

> ✅ Modulo, parte reale e argomento trasformano condizioni algebriche in rette, circonferenze, regioni e luoghi a distanza o angolo assegnato; le mappe $az+b$ sono similitudini dirette e il coniugio produce riflessioni.

La strategia d'esame è: identificare il significato geometrico, tradurre in coordinate, controllare dominio e bordi, quindi verificare il risultato con un punto campione.
