# **M10 UD2 Lezione 3 - Calcolo di limiti**

### **1. Quando usare l'Hôpital**

Nel calcolo dei limiti l'Hôpital è uno strumento utile, ma va scelto con criterio. Prima si prova a semplificare, raccogliere, razionalizzare o usare limiti notevoli. Se il limite resta in forma $0/0$ o $\infty/\infty$, allora si può valutare l'applicazione del teorema.

### **2. Forma $0/0$ con esponenziale**

Calcoliamo

$$
\lim_{x\to 0}\frac{e^x-1}{x}.
$$

La forma è $0/0$. Applicando l'Hôpital:

$$
\lim_{x\to 0}\frac{e^x}{1}=1.
$$

Quindi

$$
e^x-1\sim x
$$

per $x\to 0$.

### **3. Forma trasformabile in $0/0$**

Calcoliamo

$$
\lim_{x\to 0}\frac{\ln(1+x)-x}{x^2}.
$$

La sostituzione dà $0/0$. Deriviamo:

$$
\lim_{x\to 0}\frac{\frac{1}{1+x}-1}{2x}.
$$

Semplifichiamo il numeratore:

$$
\frac{1}{1+x}-1=\frac{1-(1+x)}{1+x}=-\frac{x}{1+x}.
$$

Quindi

$$
\lim_{x\to 0}\frac{-\frac{x}{1+x}}{2x}
=\lim_{x\to 0}-\frac{1}{2(1+x)}
=-\frac{1}{2}.
$$

In alternativa si poteva applicare l'Hôpital una seconda volta.

### **4. Forma $0\cdot\infty$**

l'Hôpital non si applica direttamente ai prodotti. Bisogna trasformarli in quozienti.

Calcoliamo

$$
\lim_{x\to 0^+}x\ln x.
$$

La forma è $0\cdot(-\infty)$. Riscriviamo:

$$
x\ln x=\frac{\ln x}{1/x}.
$$

Ora la forma è $-\infty/\infty$. Applicando l'Hôpital:

$$
\lim_{x\to 0^+}\frac{1/x}{-1/x^2}
=\lim_{x\to 0^+}(-x)=0.
$$

### **5. Forma $\infty-\infty$**

Calcoliamo

$$
\lim_{x\to+\infty}\left(\sqrt{x^2+x}-x\right).
$$

La forma è $\infty-\infty$. Razionalizziamo:

$$
\sqrt{x^2+x}-x
=\frac{(x^2+x)-x^2}{\sqrt{x^2+x}+x}
=\frac{x}{\sqrt{x^2+x}+x}.
$$

Dividendo per $x$:

$$
\frac{1}{\sqrt{1+\frac{1}{x}}+1}.
$$

Il limite è

$$
\frac{1}{2}.
$$

Qui l'Hôpital non era necessario: una trasformazione algebrica era più naturale.

### **6. Riepilogo**

Nel calcolo dei limiti bisogna riconoscere la forma indeterminata e scegliere la trasformazione adatta. l'Hôpital lavora sui quozienti $0/0$ e $\infty/\infty$; prodotti, differenze e potenze indeterminate vanno prima ricondotti a una di queste forme oppure trattati con strumenti più semplici.
