## **Lezione 7: Limiti all’infinito con esponenziali e logaritmi – scala di confronto asintotico**

### **1. Obiettivo della lezione**

In questa lezione vediamo come risolvere alcuni **limiti per $x \to +\infty$** che coinvolgono **esponenziali e logaritmi**, e introduciamo la **scala di confronto asintotico**.

Nel video precedente abbiamo imparato a risolvere i limiti all’infinito di **polinomi** e **funzioni razionali**, usando una strategia chiara: individuare il **termine preponderante** (quello che cresce più rapidamente), raccoglierlo, e osservare che tutti gli altri pezzi diventano trascurabili.

Questa idea resta valida anche qui, ma dobbiamo imparare a rispondere a una domanda nuova: quando in un limite compaiono **potenze**, **logaritmi** ed **esponenziali**, chi è “più grosso” e quindi domina?

---
### **2. Scala di confronto asintotico per $x \to +\infty$**

È possibile dimostrare che, per $x \to +\infty$, vale il seguente ordine di crescita:

$$

  

\log x \ll x^k \ll; a^x

  

$$

con $k>0$ e $a>1$.

![](../imgs/Pasted%20image%2020251218135736.png)

Questo significa:

- i **logaritmi** crescono più lentamente delle **potenze**
    
- le **potenze** crescono più lentamente degli **esponenziali**

Quindi, quando dobbiamo scegliere il termine preponderante:

- tra logaritmo e potenza vince la potenza
    
- tra potenza ed esponenziale vince l’esponenziale

---
### **3. Esempio numerico per fissare l’idea**

Consideriamo tre funzioni:

- $\log_{10}x$
    
- $x^2$
    
- $10^x$

Se proviamo a valutare queste funzioni in $x=10$, $x=100$, $x=1000$, vediamo che:

- il logaritmo cresce lentissimamente (da 1 a 2 a 3)
    
- la potenza cresce in modo intermedio
    
- l’esponenziale cresce in modo esplosivo

E se continuassimo a sostituire valori di $x$ sempre più grandi, le differenze diventerebbero sempre più vistose: gli esponenziali schiacciano tutto, le potenze schiacciano i logaritmi.

![](../imgs/Pasted%20image%2020251218135845.png)

Questa cosa si vede anche dai grafici: logaritmi “lenti”, potenze “intermedie”, esponenziali “verticali”.

---
### **4. Primo esempio: potenza contro esponenziale**

Consideriamo il limite:

$$

  

\lim_{x\to +\infty}\left(x^6 - 6^x\right)

  

$$
#### **4.1 Analisi preliminare**

- $x^6 \to +\infty$
    
- $6^x \to +\infty$

Quindi siamo davanti a una forma indeterminata:
	
$$

  

\infty - \infty

  

$$

Per risolverla dobbiamo raccogliere il termine preponderante. Tra una potenza e un esponenziale, domina l’esponenziale.

---
#### **4.2 Raccoglimento del termine preponderante**

Raccogliamo $6^x$:

$$

  

x^6 - 6^x

  

=

  

6^x\left(\frac{x^6}{6^x} - 1\right)

  

$$
Ora analizziamo i pezzi:

- $6^x \to +\infty$
    
- $\dfrac{x^6}{6^x} \to 0$ perché l’esponenziale cresce molto più velocemente della potenza

Quindi la parentesi tende a $-1$.

---
#### **4.3 Conclusione**

$$

  

\lim_{x\to +\infty}\left(x^6 - 6^x\right)

  

= (+\infty)\cdot(-1)

  

= -\infty

  

$$

---
### **5. Secondo esempio: esponenziale, potenza e logaritmo**

Consideriamo ora:

$$

  

\lim_{x\to +\infty}\frac{e^x - x^2}{3x + \ln x}

  

$$
#### **5.1 Analisi preliminare**

- al numeratore: $e^x \to +\infty$ e $x^2 \to +\infty$ quindi numeratore è $\infty - \infty$
    
- al denominatore: $3x \to +\infty$ e $\ln x \to +\infty$ quindi denominatore è $\infty + \infty$

Quindi siamo in una situazione più “brutta”:

$$

  

\frac{\infty - \infty}{\infty + \infty}

  

$$

Ma la strategia resta la stessa: raccogliere i termini preponderanti sopra e sotto.

---
#### **5.2 Termine dominante al numeratore e al denominatore**

- al numeratore, tra $e^x$ e $x^2$ domina $e^x$
    
- al denominatore, tra $3x$ e $\ln x$ domina $3x$ (la potenza vince sul logaritmo)
    

---
#### **5.3 Raccoglimento**

$$

  

\frac{e^x - x^2}{3x + \ln x}

  

=

  

\frac{e^x\left(1 - \frac{x^2}{e^x}\right)}{3x\left(1 + \frac{\ln x}{3x}\right)}

  

$$

---

#### **5.4 Limite dei singoli pezzi**

- $\dfrac{x^2}{e^x} \to 0$ (l’esponenziale domina la potenza)
    
- $\dfrac{\ln x}{3x} \to 0$ (la potenza domina il logaritmo)

Quindi entrambe le parentesi tendono a $1$.

Resta quindi, come comportamento dominante:

$$

  

\frac{e^x}{3x}

  

$$

---
#### **5.5 Conclusione**

Poiché l’esponenziale domina la potenza, abbiamo:
$$

  

\lim_{x\to +\infty}\frac{e^x}{3x} = +\infty

  

$$
Quindi:

$$

  

\lim_{x\to +\infty}\frac{e^x - x^2}{3x - \ln x} = +\infty

  

$$

---
### **6. Terzo esempio: radice, logaritmo e potenza**

Consideriamo:

$$

  

\lim_{x\to +\infty}\frac{\sqrt{x^2+3}}{\log_2 x - 2x}

  

$$
#### **6.1 Analisi preliminare**

- al numeratore: $\sqrt{x^2+3} \to +\infty$
    
- al denominatore: $\log_2 x \to +\infty$ e $2x \to +\infty$

Quindi il denominatore è una forma:

$$

  

\infty - \infty

  

$$

Per risolvere, raccogliamo i termini preponderanti:

- al numeratore, “il grosso” è $x^2$ dentro la radice
    
- al denominatore, tra $\log_2 x$ e $2x$ domina $2x$

---
#### **6.2 Riscrittura del numeratore (attenzione al valore assoluto)**

$$

  

\sqrt{x^2+3}

  

=

  

\sqrt{x^2\left(1+\frac{3}{x^2}\right)}

  

=

  

|x|\sqrt{1+\frac{3}{x^2}}

  

$$

Poiché qui $x \to +\infty$, quindi $x$ è positivo, possiamo dire:

$$

  

|x| = x

  

$$
e quindi:
$$

  

\sqrt{x^2+3} = x\sqrt{1+\frac{3}{x^2}}

  

$$

---
#### **6.3 Raccoglimento al denominatore**

$$

  

\log_2 x - 2x

  

=

  

2x\left(\frac{\log_2 x}{2x} - 1\right)

  

$$

---
#### **6.4 Assemblaggio e semplificazione**

Il rapporto diventa:

$$

  

\frac{x\sqrt{1+\frac{3}{x^2}}}{2x\left(\frac{\log_2 x}{2x} - 1\right)}

  

=

  

\frac{\sqrt{1+\frac{3}{x^2}}}{2\left(\frac{\log_2 x}{2x} - 1\right)}

  

$$

---
#### **6.5 Limite dei singoli pezzi**

- $\dfrac{3}{x^2} \to 0$ quindi $\sqrt{1+\dfrac{3}{x^2}} \to 1$
    
- $\dfrac{\log_2 x}{2x} \to 0$ quindi $\left(\dfrac{\log_2 x}{2x} - 1\right)\to -1$

---
#### **6.6 Conclusione**

$$

  

\lim_{x\to +\infty}\frac{\sqrt{x^2+3}}{\log_2 x - 2x}

  

=

  

\frac{1}{2\cdot(-1)}

  

=

  

-\frac{1}{2}

  

$$

---
### **7. Estensione della scala: esiste qualcosa che domina un esponenziale?**

La scala di confronto che abbiamo usato è già quella più importante per Analisi 1, perché logaritmi, potenze ed esponenziali sono le funzioni più frequenti.

Però è utile sapere che la scala può essere ampliata: esistono funzioni che dominano qualunque esponenziale a base fissa. Un esempio classico è:

$$

  

x^x

  

$$

che cresce più rapidamente di $a^x$ per qualunque $a>1$ fissato.

In sintesi, una scala più ampia può essere:

$$

  

\log x \ll x^k \ll a^x \ll; x^x

  

$$

---
### **8. Chiusura**

In questa lezione abbiamo introdotto la **scala di confronto asintotico** e abbiamo visto come usarla per scegliere il termine preponderante nei limiti all’infinito.

L’idea pratica è sempre la stessa:

- individua chi domina (logaritmo, potenza, esponenziale)
    
- raccogli il dominante
    
- osserva che i rapporti “debole/dominante” tendono a $0$

Nella prossima lezione daremo un’occhiata ai **limiti di funzioni composte** e vedremo alcune strategie che semplificano tantissimo molti esercizi.