## **Lezione 11: Errori comuni nel calcolo dei limiti e strumenti avanzati**

### **1. Obiettivo della lezione**

In questa lezione concludiamo il percorso sul **calcolo dei limiti**, concentrandoci su:

- gli **errori più comuni** che si commettono nei calcoli;
    
- perché questi errori sono **concettualmente sbagliati**, anche quando “per caso” portano al risultato giusto;
    
- quali **strumenti avanzati** entrano in gioco quando i metodi standard non bastano più.

---
### **2. Errore n.1: dimenticare la notazione di limite**

Consideriamo il limite:

$$

\lim_{x\to 1}\frac{x^2-3x+2}{x^2-1}

$$

Sostituendo $x=1$ otteniamo una forma indeterminata:

$$

\frac{0}{0}

$$

Si procede correttamente scomponendo:

$$

\frac{(x-1)(x-2)}{(x-1)(x+1)}

$$

e semplificando il fattore comune $(x-1)$.

A questo punto **è corretto** scrivere:

$$

\lim_{x\to 1}\frac{x-2}{x+1}

$$

e solo **alla fine** sostituire $x=1$:

$$

\frac{1-2}{1+1}=-\frac{1}{2}

$$

---
#### **Errore tipico**

Scrivere direttamente:

$$

\frac{x-2}{x+1}=-\frac{1}{2}

$$

❌ **Errore concettuale**:

$\frac{x-2}{x+1}$ è una **funzione**, mentre $-\frac{1}{2}$ è un **numero**.

La forma corretta è sempre:

$$

\lim_{x\to 1}\frac{x-2}{x+1}=-\frac{1}{2}

$$

👉 **Regola d’oro**:

non perdere mai la notazione di limite finché non hai davvero finito.

---
### **3. Errore n.2: usare i limiti notevoli fuori contesto**

Consideriamo:

$$

\lim_{x\to\infty}\left(\frac{x-1}{x+1}+\frac{\sin x}{x}\right)

$$

Un errore comune è dire:

- $\frac{x-1}{x+1}\to 1$
    
- $\frac{\sin x}{x}\to 1$

e concludere:

$$

1+1=2

$$

❌ **Errore grave**.

---
#### **Perché è sbagliato**

Il limite notevole:
$$

\lim_{x\to 0}\frac{\sin x}{x}=1

$$
vale **solo** per $x\to 0$, **non** per $x\to\infty$.

Per $x\to\infty$:

- $\sin x$ è sempre compreso tra $-1$ e $1$;
    
- $x\to\infty$;

quindi:
$$

\frac{\sin x}{x}\to 0

$$

---
#### **Conclusione corretta**

$$

\lim_{x\to\infty}\left(\frac{x-1}{x+1}+\frac{\sin x}{x}\right)

=1+0=1

$$
👉 **Morale**:

un limite notevole va usato **solo** quando la variabile tende al valore giusto.

---
### **4. Errore n.3: cambi di variabile illegittimi**

Consideriamo:
$$

\lim_{x\to 0}\frac{\ln(1+\cos x)}{\cos x}

$$

Qualcuno potrebbe pensare di porre:
$$

y=\cos x

$$
e scrivere:
$$

\lim_{y\to 0}\frac{\ln(1+y)}{y}=1

$$
❌ **Errore concettuale**.

---
#### **Il problema**

Quando $x\to 0$:
$$

\cos x \to 1

$$
non a $0$.

Il limite notevole:
$$

\lim_{y\to 0}\frac{\ln(1+y)}{y}=1

$$
si applica **solo** se $y\to 0$.

---
#### **Soluzione corretta**

Basta sostituire direttamente $x=0$:
$$

\frac{\ln(1+\cos 0)}{\cos 0}

=\ln(2)

$$
👉 **Regola**:

prima di ogni cambio di variabile, controlla **dove tende davvero** la nuova variabile.

---
### **5. Errore n.4: fare il limite solo di una parte dell’espressione**

Consideriamo:
$$

\lim_{x\to 0}\frac{\sin x - x + 2x^5}{3x^3}

$$
Forma indeterminata $\frac{0}{0}$.

Un errore frequente è usare subito:
$$

\frac{\sin x}{x}\to 1

$$
e “portarsi avanti” gli altri termini.

❌ **Errore metodologico**.

---
#### **Perché è sbagliato**

Stai facendo il limite **solo di un pezzo**, lasciando gli altri “in sospeso”.

Questo **non è lecito**:

il limite va valutato sull’espressione **intera**.

In alcuni casi funziona per coincidenza, in altri (come questo) porta a risultati sbagliati.

---
### **6. Come si risolve correttamente**

Con le tecniche elementari non basta.

Servono strumenti più potenti.

---
#### **Metodo 1: Teorema di de l’Hôpital**

Il limite è del tipo $\frac{0}{0}$.

Applicando de l’Hôpital **tre volte** si ottiene:

$$

\lim_{x\to 0}\frac{\sin x - x + 2x^5}{3x^3}

=-\frac{1}{18}

$$

---
#### **Metodo 2: sviluppi di Taylor / equivalenze asintotiche**

Usando lo sviluppo di Taylor:
$$

\sin x = x - \frac{x^3}{6} + o(x^3)

$$
si ricava lo stesso risultato:
$$

-\frac{1}{18}

$$

---
### **7. Conclusione finale**

Gli errori più pericolosi nel calcolo dei limiti sono:

1. perdere la **notazione di limite**;
    
2. usare i **limiti notevoli fuori contesto**;
    
3. fare **cambi di variabile illegittimi**;
    
4. calcolare il limite di **solo una parte** dell’espressione.

Quando i metodi standard falliscono, entrano in gioco:

- **de l’Hôpital**;
    
- **sviluppi di Taylor**;
    
- **equivalenze asintotiche**.

👉 Sono gli strumenti che useremo per dominare anche le **forme indeterminate più complesse**.

Nelle prossime lezioni questi metodi diventeranno centrali.