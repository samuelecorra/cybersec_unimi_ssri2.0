# **M9 UD6 Lezione 1 - Massimi e minimi con le derivate**

### **Problema: triangolo inscritto in semicirconferenza di raggio 2 con perimetro massimo**

> 📌 Questa unità applica i teoremi della UD5: l'esistenza del massimo è garantita da **Weierstrass** (funzione continua su $[0,4]$, M08/UD5), i candidati interni si trovano con **Fermat** ($P'=0$ o punti non derivabili), e il passaggio "segno della derivata $\Rightarrow$ crescenza/decrescenza" è il **criterio della derivata prima** (UD5, Lezione 4). Ogni passo del metodo ha quindi un teorema alle spalle.

### **1. Cosa ci chiede il problema**

Tra **tutti** i triangoli inscritti in una **semicirconferenza di raggio $2$**, dobbiamo trovare quello con **perimetro massimo**.

La semicirconferenza ha **diametro $4$**, quindi il segmento di base (il diametro) ha lunghezza:

$$
\text{ipotenusa}=4
$$

Tutti i triangoli inscritti in una semicirconferenza con il diametro come lato sono **rettangoli** (teorema di Talete: angolo alla circonferenza che insiste sul diametro è di $90^\circ$).

---
### **2. Scelta delle variabili e scrittura del perimetro**

Chiamiamo:

- $x$ e $y$ le lunghezze dei due cateti
    
- l’ipotenusa vale sempre $4$

Allora il perimetro è:

$$
P = x+y+4
$$

Problema: $P$ dipende da **due variabili** ($x$ e $y$).

Per massimizzare con le derivate vogliamo una funzione **di una sola variabile**.

---
### **3. Eliminare una variabile usando il vincolo geometrico**

Il vincolo è Pitagora:

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
### **4. Intervallo ammesso per** $x$

$x$ è una lunghezza, quindi:
$$
x\ge 0
$$

Il massimo valore di $x$ si ha quando il vertice del triangolo “scivola” fino a un estremo del diametro, e allora il cateto coincide con l’ipotenusa:

$$
x\le 4
$$

Quindi il dominio del problema è:

$$
x\in[0,4]
$$

---
### **5. Derivata di** $P(x)$

Deriviamo:

$$
P(x)=x+\sqrt{16-x^2}+4
$$

- derivata di $x$ è $1$
    
- derivata di $4$ è $0$
    
- derivata di $\sqrt{16-x^2}$: è una composta

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
P'(x)=1-\frac{x}{\sqrt{16-x^2}}
$$

---
### **6. Studio del segno di** $P'(x)$

Cerchiamo dove $P'(x)=0$:

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

Ora capiamo il segno di $P'(x)$:

- se $\sqrt{16-x^2} > x$ allora $P'(x)>0$ (crescente)
    
- se $\sqrt{16-x^2} < x$ allora $P'(x)<0$ (decrescente)

La soglia è proprio dove sono uguali, cioè $x=2\sqrt{2}$.

Quindi:

- $P$ cresce su $[0,2\sqrt{2}]$
    
- $P$ decresce su $[2\sqrt{2},4]$

Conclusione: **massimo** in

$$
x=2\sqrt{2}
$$

Controllo con i valori: $P(0)=P(4)=8$ (triangoli degeneri agli estremi, dove la derivata non serve) contro $P(2\sqrt2)=2\sqrt2+2\sqrt2+4=4+4\sqrt2\approx 9{,}66$: il massimo è interno, come previsto dallo studio del segno. Nota anche che $P$ non è derivabile in $x=4$ (tangente verticale della radice): è un **candidato** da valutare col valore, non con la derivata.

---
### **7. Calcolo di** y **e interpretazione geometrica**

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

Cioè è un **triangolo rettangolo isoscele**.

Questo corrisponde al vertice nel punto più alto della semicirconferenza, e all’angolo acuto:

$$
45^\circ
$$

---
### **8. Risposta finale**

Il triangolo inscritto nella semicirconferenza di raggio $2$ con **perimetro massimo** è quello:

- rettangolo (sempre)
    
- **isoscele** ($x=y$)
    
- con cateti

$$
x=y=2\sqrt{2}
$$

---
### **9. Procedura generale esportabile**

#### **Step 1: definisci cosa vuoi massimizzare/minimizzare**

Qui:

$$
P=x+y+4
$$

#### **Step 2: usa i vincoli per ridurre a 1 variabile**

Qui Pitagora:

$$
y=\sqrt{16-x^2}
$$

#### **Step 3: trova il dominio della variabile**

Qui:

$$
x\in[0,4]
$$

#### **Step 4: derivata e segno**

- trova i punti critici con $P'(x)=0$
    
- studia crescenza/decrescenza nel dominio
    
- deduci massimo/minimo

