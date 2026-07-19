# **M10 UD2 Lezione 2 - Confronto fra infiniti**

### **1. Crescite diverse all'infinito**

Quando $x\to+\infty$, molte funzioni divergono, ma non tutte alla stessa velocità. Il confronto tra infiniti serve a stabilire quale termine domina in un limite.

Si dice che $f$ è trascurabile rispetto a $g$ per $x\to+\infty$ se

$$
\lim_{x\to+\infty}\frac{f(x)}{g(x)}=0.
$$

In questo caso si scrive

$$
f(x)=o(g(x)).
$$

### **2. Gerarchia fondamentale**

Per $x\to+\infty$ vale la seguente scala di crescita:

$$
\ln x \ll x^\alpha \ll a^x
$$

per ogni $\alpha>0$ e per ogni $a>1$. In parole:

- i logaritmi crescono più lentamente delle potenze positive;
- le potenze crescono più lentamente degli esponenziali con base maggiore di $1$.

Confronti più precisi sono:

$$
\lim_{x\to+\infty}\frac{\ln x}{x^\alpha}=0,
$$

$$
\lim_{x\to+\infty}\frac{x^\alpha}{a^x}=0.
$$

### **3. Uso di l'Hôpital nel confronto**

Dimostriamo il primo confronto nel caso $\alpha=1$:

$$
\lim_{x\to+\infty}\frac{\ln x}{x}.
$$

È una forma $\infty/\infty$. Applicando l'Hôpital:

$$
\lim_{x\to+\infty}\frac{1/x}{1}=0.
$$

Per $\alpha>0$, applicazioni ripetute o sostituzioni opportune portano allo stesso risultato.

Per il confronto tra potenze ed esponenziali, consideriamo

$$
\lim_{x\to+\infty}\frac{x^n}{e^x}
$$

con $n\in\mathbb{N}$. Applicando l'Hôpital $n$ volte, il numeratore diventa una costante, mentre il denominatore resta $e^x$. Quindi il limite è $0$.

### **4. Esempio**

Calcoliamo

$$
\lim_{x\to+\infty}\frac{3x^4-2x+\ln x}{5x^4+x^2}.
$$

I termini dominanti sono $3x^4$ al numeratore e $5x^4$ al denominatore. Dividendo per $x^4$:

$$
\frac{3-\frac{2}{x^3}+\frac{\ln x}{x^4}}{5+\frac{1}{x^2}}.
$$

Poiché

$$
\frac{\ln x}{x^4}\to 0,
$$

il limite è

$$
\frac{3}{5}.
$$

### **5. Esempio con esponenziale**

Calcoliamo

$$
\lim_{x\to+\infty}\frac{x^3+e^x}{2e^x-7x}.
$$

L'esponenziale domina le potenze. Dividiamo per $e^x$:

$$
\frac{\frac{x^3}{e^x}+1}{2-\frac{7x}{e^x}}.
$$

Poiché

$$
\frac{x^3}{e^x}\to 0,\qquad \frac{x}{e^x}\to 0,
$$

otteniamo

$$
\frac{1}{2}.
$$

### **6. Riepilogo**

Il confronto fra infiniti evita calcoli inutili: nei limiti all'infinito si identificano i termini dominanti e si giustifica la trascurabilità degli altri. l'Hôpital fornisce una dimostrazione potente della gerarchia $\ln x \ll x^\alpha \ll a^x$.

> 💡 Coerenza con M08: la stessa scala era già stata **dimostrata senza derivate** in M08/UD2/L3 (disuguaglianza $\ln t\le t-1$ e successioni di M05 con la parte intera). Qui l'Hôpital la ridimostra in due righe: è la seconda strada, non la prima — e all'orale sapere entrambe (e quale viene logicamente prima) vale molto.
