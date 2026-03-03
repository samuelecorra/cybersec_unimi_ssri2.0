## **Lezione 14: Massimi e minimi – rettangolo di area massima sotto $y=\lvert x^2-1\rvert$**

### **1. Obiettivo del problema**

È data la curva:

$$

y=\lvert x^2-1\rvert

$$

Si chiede: tra tutti i **rettangoli iscritti nella regione compresa tra la curva e l’asse $x$**, trovare quello di **area massima**.

L’oggetto da massimizzare è quindi:

$$

A=\text{area del rettangolo}=\text{base}\cdot \text{altezza}

$$

---
## **2. Capire la curva (passo geometrico)**

Prima guardiamo $y=x^2-1$ (parabola con vertice in $(0,-1)$).

Poi, per l’assoluto, la parte **sotto** l’asse $x$ viene **ribaltata sopra**.

Quindi, nell’intervallo $-1\le x\le 1$ vale:

$$

x^2-1\le 0 \quad\Rightarrow\quad \lvert x^2-1\rvert =-(x^2-1)=1-x^2

$$

Ed è proprio questa “cupola” sopra l’asse $x$ che contiene i rettangoli del problema.

---
## **3. Scelta furba della variabile**

La figura è simmetrica rispetto all’asse $y$, quindi scegliamo come variabile la **semibase**:

$$

\gamma=\text{semibase del rettangolo}

$$

Allora:

- la base è $2\gamma$
    
- il rettangolo sta sotto la curva solo se $0\le \gamma\le 1$

Quindi dominio:

$$

\gamma\in[0,1]

$$

---
## **4. Base e altezza in funzione di $\gamma$**
### **Base**

Per simmetria:
$$

\text{base}=2\gamma

$$
### **Altezza**

Il vertice in alto a destra ha ascissa $\gamma$ e sta sulla curva, quindi la sua ordinata è:

$$

y=\lvert \gamma^2-1\rvert

$$

Ma per $0\le\gamma\le 1$ sappiamo che $\gamma^2-1\le 0$, quindi:

$$

\lvert \gamma^2-1\rvert = 1-\gamma^2

$$

Quindi l’altezza è:
$$

\text{altezza}=1-\gamma^2

$$

---
## **5. Funzione area**

Ora l’area diventa una funzione di **una sola variabile**:

$$

A(\gamma)= (2\gamma)(1-\gamma^2)

$$
con
$$

\gamma\in[0,1]

$$

Controllo casi limite (utile come “test di sanità”):

- $A(0)=0$ (rettangolo schiacciato)
    
- $A(1)=0$ (rettangolo schiacciato)

Perfetto: torna con l’intuizione geometrica.

---
## **6. Derivata e massimo**

Deriviamo $A(\gamma)$.

Usiamo la regola del prodotto:

$$

A’(\gamma)= (2)(1-\gamma^2) + (2\gamma)(-2\gamma)

$$
Semplifichiamo:
$$

A’(\gamma)=2-2\gamma^2-4\gamma^2

$$
$$

A’(\gamma)=2-6\gamma^2

$$
Mettiamo in evidenza:
$$

A’(\gamma)=2(1-3\gamma^2)

$$

---
## **7. Studio del segno**

Il fattore $2$ è positivo e non cambia il segno, quindi studiamo:
$$

1-3\gamma^2

$$
Punti critici:
$$

1-3\gamma^2=0

\quad\Rightarrow\quad

\gamma^2=\frac{1}{3}

\quad\Rightarrow\quad

\gamma=\frac{1}{\sqrt{3}}=\frac{\sqrt{3}}{3}

$$
Nel dominio $[0,1]$ il punto critico è:
$$

\gamma=\frac{\sqrt{3}}{3}

$$

Segno:

- per $0\le \gamma<\frac{\sqrt{3}}{3}$, $A’(\gamma)>0$ → area cresce
    
- per $\frac{\sqrt{3}}{3}<\gamma\le 1$, $A’(\gamma)<0$ → area decresce

Quindi lì c’è un **massimo**.

---
## **8. Rettangolo di area massima**

### **Semibase ottimale**

$$

\gamma^*=\frac{\sqrt{3}}{3}

$$
### **Base**
$$

b=2\gamma^*=\frac{2\sqrt{3}}{3}

$$
### **Altezza**
$$

h=1-(\gamma^*)^2

=1-\frac{1}{3}

=\frac{2}{3}

$$

---
## **9. Area massima**

Calcoliamola:
$$

A_{\max}=b\cdot h

=\left(\frac{2\sqrt{3}}{3}\right)\left(\frac{2}{3}\right)

=\frac{4\sqrt{3}}{9}

$$

---
## **10. Coordinate dei vertici**

Vertici sulla base (asse $x$):
$$

\left(-\frac{\sqrt{3}}{3},,0\right),

\quad

\left(\frac{\sqrt{3}}{3},,0\right)

$$
Vertici in alto (quota $h=\frac{2}{3}$):

$$

\left(-\frac{\sqrt{3}}{3},,\frac{2}{3}\right),

\quad

\left(\frac{\sqrt{3}}{3},,\frac{2}{3}\right)

$$

---
## **11. Risultato finale**

Il rettangolo di area massima nella regione tra la curva e l’asse $x$ ha:

$$

\boxed{

\gamma=\frac{\sqrt{3}}{3},\quad

b=\frac{2\sqrt{3}}{3},\quad

h=\frac{2}{3},\quad

A_{\max}=\frac{4\sqrt{3}}{9}

}

$$

---
## **12. Strategia generale “standard” dei massimi/minimi**

1. Identifica la quantità da ottimizzare (qui: $A$).
    
2. Riscrivila in funzione di variabili geometriche.
    
3. Riduci a **una sola variabile** usando vincoli/simmetrie.
    
4. Fissa il dominio della variabile.
    
5. Deriva.
    
6. Studia il segno di $A’$ nel dominio.
    
7. Concludi massimo/minimo e ricostruisci la configurazione.