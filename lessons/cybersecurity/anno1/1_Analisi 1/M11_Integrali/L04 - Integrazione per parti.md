## **Lezione 4: Integrazione per parti**

---
### **1. La formula**

L’integrazione per parti serve quando hai un **prodotto** tra due pezzi e vuoi “spostare” la derivata da una parte all’altra.

La formula è:

$$

\int f(x),g’(x),dx = f(x),g(x) - \int f’(x),g(x),dx

$$

Idea: **il primo integrale deve essere più brutto del secondo**, altrimenti non hai guadagnato niente.

---
### **2. Come scegliere $f$ e $g’$**

Regola pratica (molto usata):

- scegli come **$f$** la parte che **si semplifica derivando** (polinomi, logaritmi, …)
    
- scegli come **$g’$** la parte che **resta “simile” integrando** (esponenziali, seno, coseno)

Questo fa sì che nel secondo integrale compaia qualcosa di più semplice.

---
### **3. Esempio 1: $\int x\cos x,dx$**

Scegliamo:
$$

f(x)=x \quad\Rightarrow\quad f’(x)=1

$$

$$

g’(x)=\cos x \quad\Rightarrow\quad g(x)=\sin x

$$

Applichiamo:
$$

\int x\cos x,dx = x\sin x - \int 1\cdot \sin x,dx

$$

Ma:
$$

\int \sin x,dx = -\cos x

$$

Quindi:
$$

\int x\cos x,dx = x\sin x + \cos x + C

$$

---
### **4. Esempio 2: $\int x e^x,dx$**

Scegliamo:
$$

f(x)=x \Rightarrow f’(x)=1

$$
$$

g’(x)=e^x \Rightarrow g(x)=e^x

$$

Formula:
$$

\int x e^x,dx = x e^x - \int 1\cdot e^x,dx

$$
$$

\int e^x,dx = e^x

$$

Risultato:
$$

\int x e^x,dx = x e^x - e^x + C

$$

---
### **5. Esempio 3: $\int x^2 e^x,dx$ (parti ripetute)**

Scegliamo:
$$

f(x)=x^2 \Rightarrow f’(x)=2x

$$
$$

g’(x)=e^x \Rightarrow g(x)=e^x

$$

Prima applicazione:
$$

\int x^2 e^x,dx = x^2 e^x - \int 2x e^x,dx

$$

Porto fuori il 2:

$$

= x^2 e^x - 2\int x e^x,dx

$$

Ma $\int x e^x,dx$ lo abbiamo appena trovato:

$$

\int x e^x,dx = x e^x - e^x

$$

Quindi:
$$

\int x^2 e^x,dx = x^2 e^x - 2(x e^x - e^x) + C

$$

Sviluppo:
$$

= x^2 e^x - 2x e^x + 2e^x + C

$$
Raccolgo $e^x$:

$$

\int x^2 e^x,dx = e^x(x^2 - 2x + 2) + C

$$

---
### **6. Caso generale: polinomio $\times$ esponenziale / seno / coseno**

Se hai:

$$

\int x^n e^x,dx,\quad \int x^n\sin x,dx,\quad \int x^n\cos x,dx

$$

fai **parti più volte**, perché ogni volta la derivata del polinomio abbassa il grado:

$$

x^n \to n x^{n-1} \to \dots \to 0

$$

Se invece hai un polinomio con più termini:

$$

\int (x^4 + 3x^2 - 6)e^x,dx

$$

usi la linearità:

$$
\int (x^4 + 3x^2 - 6)e^x,dx =

  

\int x^4 e^x,dx + 3\int x^2 e^x,dx - 6\int e^x,dx

$$

e poi risolvi ciascuno con parti (tranne l’ultimo che è immediato).

---
### **7. Esempio 4: $\int x\ln x,dx$**

Qui il logaritmo è “scomodo da integrare” ma “facile da derivare”, quindi:

$$

f(x)=\ln x \Rightarrow f’(x)=\frac{1}{x}

$$
$$

g’(x)=x \Rightarrow g(x)=\frac{x^2}{2}

$$

Applichiamo:
$$

\int x\ln x,dx = \ln x\cdot \frac{x^2}{2} - \int \frac{1}{x}\cdot \frac{x^2}{2},dx

$$

Semplifico dentro:
$$

\frac{1}{x}\cdot \frac{x^2}{2} = \frac{x}{2}

$$

Quindi:
$$

= \frac{x^2}{2}\ln x - \int \frac{x}{2},dx

= \frac{x^2}{2}\ln x - \frac{1}{2}\cdot \frac{x^2}{2} + C

$$

Risultato:
$$

\int x\ln x,dx = \frac{x^2}{2}\ln x - \frac{x^2}{4} + C

$$

Raccolta elegante:
$$

\int x\ln x,dx = \frac{x^2}{2}\left(\ln x - \frac{1}{2}\right) + C

$$

---
### **8. Checklist mentale lampo**

Quando vedi un integrale-prodotto, chiediti:

- uno dei fattori **diventa più semplice se lo derivo**? → quello è $f$
    
- l’altro **è facile da integrare** e resta “simile”? → quello è $g’$
    
- dopo aver applicato la formula, il nuovo integrale è davvero più facile? se sì → stai andando bene