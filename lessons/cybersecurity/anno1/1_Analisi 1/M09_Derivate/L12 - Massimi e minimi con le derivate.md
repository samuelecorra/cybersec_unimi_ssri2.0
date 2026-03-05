## **Lezione 12: Massimi e minimi con le derivate**

### **Problema: triangolo inscritto in semicirconferenza di raggio 2 con perimetro massimo**

### **1. Cosa ci chiede il problema**

Tra **tutti** i triangoli inscritti in una **semicirconferenza di raggio $2$**, dobbiamo trovare quello con **perimetro massimo**.

La semicirconferenza ha **diametro $4$**, quindi il segmento di base (il diametro) ha lunghezza:

$$

\text{ipotenusa}=4

$$

Tutti i triangoli inscritti in una semicirconferenza con il diametro come lato sono **rettangoli** (teorema di Talete: angolo alla circonferenza che insiste sul diametro e di $90^\circ$).

---
## **2. Scelta delle variabili e scrittura del perimetro**

Chiamiamo:

- $x$ e $y$ le lunghezze dei due cateti
    
- l’ipotenusa vale sempre $4$

Allora il perimetro e:

$$

P = x+y+4

$$

Problema: $P$ dipende da **due variabili** ($x$ e $y$).

Per massimizzare con le derivate vogliamo una funzione **di una sola variabile**.

---
## **3. Eliminare una variabile usando il vincolo geometrico**

Il vincolo e Pitagora:

$$

x^2+y^2=4^2=16

$$

Quindi:

$$

y=\sqrt{16-x^2}

$$

Sostituendo in $P$:
$$

P(x)=x+\sqrt{16-x^2}+4

$$

---
## **4. Intervallo ammesso per** $x$

$x$ e una lunghezza, quindi:
$$

x\ge 0

$$

Il massimo valore di $x$ si ha quando il vertice del triangolo “scivola” fino a un estremo del diametro, e allora il cateto coincide con l’ipotenusa:

$$

x\le 4

$$

Quindi il dominio del problema e:

$$

x\in[0,4]

$$

---
## **5. Derivata di** $P(x)$

Deriviamo:

$$

P(x)=x+\sqrt{16-x^2}+4

$$

- derivata di $x$ e $1$
    
- derivata di $4$ e $0$
    
- derivata di $\sqrt{16-x^2}$: e una composta

Scriviamo:

$$

\sqrt{16-x^2}=(16-x^2)^{1/2}

$$

Allora:

$$

\frac{d}{dx}\sqrt{16-x^2}

=\frac{1}{2}(16-x^2)^{-1/2}\cdot(-2x)

=-\frac{x}{\sqrt{16-x^2}}

$$

Quindi:
$$

P’(x)=1-\frac{x}{\sqrt{16-x^2}}

$$

---
## **6. Studio del segno di** $P'(x)$

Cerchiamo dove $P’(x)=0$:

$$

1-\frac{x}{\sqrt{16-x^2}}=0

\quad\Rightarrow\quad

1=\frac{x}{\sqrt{16-x^2}}

\quad\Rightarrow\quad

\sqrt{16-x^2}=x

$$

Nel nostro intervallo $[0,4]$ entrambi i membri sono $\ge 0$, quindi possiamo elevare al quadrato senza cambiare il senso:

$$

16-x^2=x^2

$$

$$

16=2x^2

$$

$$

x^2=8

$$

$$

x=2\sqrt{2}

$$

Punto critico nell’intervallo:

$$

x_0=2\sqrt{2}

$$

Ora capiamo il segno di $P’(x)$:

- se $\sqrt{16-x^2} > x$ allora $P’(x)>0$ (crescente)
    
- se $\sqrt{16-x^2} < x$ allora $P’(x)<0$ (decrescente)

La soglia e proprio dove sono uguali, cioe $x=2\sqrt{2}$.

Quindi:

- $P$ cresce su $[0,2\sqrt{2}]$
    
- $P$ decresce su $[2\sqrt{2},4]$

Conclusione: **massimo** in

$$

x=2\sqrt{2}

$$

---
## **7. Calcolo di** y **e interpretazione geometrica**

Ricaviamo $y$:

$$

y=\sqrt{16-x^2}

=\sqrt{16-8}

=\sqrt{8}

=2\sqrt{2}

$$

Quindi nel triangolo che massimizza il perimetro:

$$

x=y=2\sqrt{2}

$$

Cioe e un **triangolo rettangolo isoscele**.

Questo corrisponde al vertice nel punto piu alto della semicirconferenza, e all’angolo acuto:

$$

45^\circ

$$

---
## **8. Risposta finale**

Il triangolo inscritto nella semicirconferenza di raggio $2$ con **perimetro massimo** e quello:

- rettangolo (sempre)
    
- **isoscele** ($x=y$)
    
- con cateti

$$

x=y=2\sqrt{2}

$$

---
## **9. Procedura generale esportabile**

### **Step 1: definisci cosa vuoi massimizzare/minimizzare**

Qui:

$$

P=x+y+4

$$

### **Step 2: usa i vincoli per ridurre a 1 variabile**

Qui Pitagora:

$$

y=\sqrt{16-x^2}

$$

### **Step 3: trova il dominio della variabile**

Qui:

$$

x\in[0,4]

$$

### **Step 4: derivata e segno**

- trova i punti critici con $P’(x)=0$
    
- studia crescenza/decrescenza nel dominio
    
- deduci massimo/minimo

