# **M10 UD2 Lezione 4 - Esercizi su l'Hôpital e forme indeterminate**

### **1. Esercizio con applicazione ripetuta**

Calcoliamo

$$
\lim_{x\to 0}\frac{x-\sin x}{x^3}.
$$

La forma è $0/0$. Applichiamo l'Hôpital:

$$
\lim_{x\to 0}\frac{1-\cos x}{3x^2}.
$$

La forma è ancora $0/0$. Applichiamo di nuovo:

$$
\lim_{x\to 0}\frac{\sin x}{6x}.
$$

Ancora $0/0$. Terza applicazione:

$$
\lim_{x\to 0}\frac{\cos x}{6}=\frac{1}{6}.
$$

Quindi

$$
\lim_{x\to 0}\frac{x-\sin x}{x^3}=\frac{1}{6}.
$$

### **2. Esercizio con potenza indeterminata**

Calcoliamo

$$
\lim_{x\to 0^+}x^x.
$$

La forma è $0^0$, indeterminata. Poniamo

$$
y=x^x.
$$

Prendiamo il logaritmo:

$$
\ln y=x\ln x.
$$

Dal calcolo già noto:

$$
\lim_{x\to 0^+}x\ln x=0.
$$

Quindi

$$
\lim_{x\to 0^+}\ln y=0.
$$

Passando all'esponenziale:

$$
\lim_{x\to 0^+}x^x=e^0=1.
$$

### **3. Esercizio con confronto tra infiniti**

Calcoliamo

$$
\lim_{x\to+\infty}\frac{x^2}{e^{3x}}.
$$

È una forma $\infty/\infty$. Applicando una prima volta l'Hôpital:

$$
\lim_{x\to+\infty}\frac{2x}{3e^{3x}}.
$$

È ancora $\infty/\infty$. Seconda applicazione:

$$
\lim_{x\to+\infty}\frac{2}{9e^{3x}}=0.
$$

La conclusione conferma che l'esponenziale domina ogni potenza.

### **4. Esercizio con parametro**

Studiamo

$$
\lim_{x\to 0}\frac{e^{ax}-1}{x}
$$

con $a\in\mathbb{R}$. La forma è $0/0$. Applicando l'Hôpital:

$$
\lim_{x\to 0}\frac{ae^{ax}}{1}=a.
$$

Quindi

$$
e^{ax}-1\sim ax
$$

per $x\to 0$.

### **5. Esercizio in cui l'Hôpital non conviene**

Calcoliamo

$$
\lim_{x\to+\infty}\frac{2x^3-x+1}{5x^3+7}.
$$

Si potrebbe applicare l'Hôpital tre volte, ma sarebbe inutile. Dividendo per $x^3$:

$$
\frac{2-\frac{1}{x^2}+\frac{1}{x^3}}{5+\frac{7}{x^3}}\to\frac{2}{5}.
$$

> ⚠️ l'Hôpital è potente, ma non sostituisce il riconoscimento dei termini dominanti.

### **5bis. Controesempi: dove la regola non arriva**

#### **(a) Il rapporto delle derivate non ha limite, ma quello originario sì**

$$
\lim_{x\to+\infty}\frac{x+\sin x}{x}
$$

È $\frac{\infty}{\infty}$ con tutte le ipotesi (1)-(3) a posto, ma il rapporto delle derivate è

$$
\frac{1+\cos x}{1} = 1+\cos x
$$

che **non ha limite**. La regola non conclude nulla — eppure il limite originario esiste ed è banale:

$$
\frac{x+\sin x}{x} = 1+\frac{\sin x}{x} \longrightarrow 1
$$

(limitata su infinita, M08). Morale: quando $\lim\frac{f'}{g'}$ non esiste, **non** si può dedurre che il limite di partenza non esista; l'implicazione va in una direzione sola.

#### **(b) Applicazione a una forma non indeterminata: risultato falso**

$$
\lim_{x\to 0^+}\frac{x}{x+\cos x}
$$

vale $\frac{0}{1}=0$ per continuità: **nessuna** forma indeterminata. "Applicando l'Hôpital" a occhi chiusi:

$$
\frac{1}{1-\sin x}\longrightarrow 1 \neq 0
$$

conclusione **sbagliata**: senza la forma $\frac 00$ o $\frac{\infty}{\infty}$ il passaggio è illegale, e qui lo si vede dare proprio un valore diverso da quello vero.

#### **(c) L'ipotesi $g'\neq 0$ non è decorativa**

Con $g$ la cui derivata si annulla infinite volte in ogni intorno (esempi standard: $g(x)=x+\cos x\sin x$ contro $f(x)=e^{\sin x}(x+\cos x\sin x)$ per $x\to+\infty$) si costruiscono casi in cui $\frac{f'}{g'}$ ha limite lungo i punti dove è definito, ma $\frac fg$ **non** converge allo stesso valore. È un controesempio classico (Stolz): non serve riprodurlo per intero all'esame, serve sapere che la condizione (3) protegge da patologie reali.

#### **(d) Il circolo vizioso da evitare**

Calcolare $\lim_{x\to 0}\frac{\sin x}{x}$ con l'Hôpital "funziona" ($\frac{\cos x}{1}\to 1$) ma è **logicamente vuoto** come dimostrazione: la derivata del seno usata nel passaggio è stata costruita **usando quel limite** (M08/UD3 → M09/UD2/L2). Come verifica a posteriori è lecito; come fondazione è un cerchio. Stesso discorso per $\frac{e^x-1}{x}$ e $\frac{\ln(1+x)}{x}$.

### **6. Riepilogo**

Gli esercizi mostrano tre passaggi ricorrenti: verificare la forma, trasformare se necessario, applicare l'Hôpital solo quando il rapporto è del tipo corretto. Le potenze indeterminate si gestiscono spesso passando al logaritmo, mentre i rapporti tra polinomi si risolvono più rapidamente con i termini di grado massimo.
