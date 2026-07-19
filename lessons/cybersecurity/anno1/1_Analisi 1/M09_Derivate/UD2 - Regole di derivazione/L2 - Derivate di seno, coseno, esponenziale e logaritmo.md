# **M9 UD2 Lezione 2 - Derivate di seno, coseno, esponenziale e logaritmo**

### **1. Obiettivo della lezione**

Concludiamo lo studio delle **derivate fondamentali** calcolandole **a partire dalla definizione** per le seguenti funzioni:

- seno
    
- coseno
    
- esponenziale
    
- logaritmo naturale

L’idea di fondo è sempre la stessa:

**applicare il limite del rapporto incrementale** e riscrivere il numeratore in modo intelligente, sfruttando identità e limiti notevoli già noti.

> 📌 **Ordine logico dichiarato (niente circoli).** I limiti notevoli usati qui — $\frac{\sin h}{h}\to 1$, $\frac{e^h-1}{h}\to 1$, $\frac{\ln(1+u)}{u}\to 1$ — sono stati dimostrati in M08 (UD3) **senza derivate**: il primo con l'argomento geometrico delle aree, gli altri due a partire dalla definizione di $e$ come limite di successione (M05). Solo per questo è lecito usarli ora per costruire le derivate: l'implicazione va da M05/M08 verso M09, mai al contrario. Vale anche il requisito dei **radianti**: se l'angolo fosse in gradi, $\frac{\sin h}{h}\to\frac{\pi}{180}$ e la derivata del seno sarebbe $\frac{\pi}{180}\cos x$ — è il motivo per cui in Analisi si lavora sempre in radianti.

---
### **2. Derivata della funzione seno**

Consideriamo:
$$
f(x) = \sin x
$$
Per definizione:

$$
f'(x) =
\lim_{h \to 0}
\frac{\sin(x+h) - \sin x}{h}
$$

Il problema è il **numeratore**, che così com’è non è gestibile.

Per riscriverlo in modo utile, utilizziamo la **formula di addizione del seno**:

$$
\sin(x+h) = \sin x \cos h + \cos x \sin h
$$
Sostituendo:
$$
\frac{\sin x \cos h + \cos x \sin h - \sin x}{h}
$$

Raccogliamo $\sin x$ nei termini opportuni:

$$
\frac{\sin x(\cos h - 1) + \cos x \sin h}{h}
$$

Ora separiamo la frazione in due termini:

$$
\sin x \cdot \frac{\cos h - 1}{h}
\;+\;
\cos x \cdot \frac{\sin h}{h}
$$

A questo punto entrano in gioco i **limiti notevoli**:

$$
\lim_{h \to 0} \frac{\sin h}{h} = 1
$$

$$
\lim_{h \to 0} \frac{\cos h - 1}{h} = 0
$$

(il secondo si ricava dal notevole $\frac{1-\cos h}{h^2}\to\frac12$ di M08: $\frac{\cos h-1}{h} = -\frac{1-\cos h}{h^2}\cdot h \to -\frac12\cdot 0 = 0$)

Passando al limite:
$$
\sin x \cdot 0 + \cos x \cdot 1 = \cos x
$$

**Conclusione**
$$
\frac{d}{dx}(\sin x) = \cos x
$$

---
### **3. Derivata della funzione coseno**

Consideriamo ora:
$$
f(x) = \cos x
$$

Il procedimento è **identico** a quello visto per il seno. Svolgiamolo per intero. Si parte dal rapporto incrementale:

$$
\lim_{h \to 0}
\frac{\cos(x+h) - \cos x}{h}
$$

Con la **formula di addizione del coseno** (M01), $\cos(x+h)=\cos x\cos h-\sin x\sin h$:

$$
\frac{\cos x\cos h - \sin x\sin h - \cos x}{h}
= \cos x\cdot\frac{\cos h-1}{h} \;-\; \sin x\cdot\frac{\sin h}{h}
$$

Passando al limite con gli stessi due notevoli:

$$
\cos x\cdot 0 - \sin x\cdot 1 = -\sin x
$$

Il risultato finale è:
$$
\frac{d}{dx}(\cos x) = -\sin x
$$

---
### **4. Derivata della funzione esponenziale**

Consideriamo:
$$
f(x) = e^x
$$

Il rapporto incrementale è:
$$
\lim_{h \to 0}
\frac{e^{x+h} - e^x}{h}
$$

Usando le proprietà delle potenze:

$$
e^{x+h} = e^x \cdot e^h
$$

Sostituendo e raccogliendo $e^x$:

$$
\lim_{h \to 0}
e^x \cdot \frac{e^h - 1}{h}
$$

Ora tutto dipende da questo limite:

$$
\lim_{h \to 0} \frac{e^h - 1}{h} = 1
$$

che è un **limite notevole**.

Passando al limite otteniamo:

$$
f'(x) = e^x
$$
**Conclusione**

$$
\frac{d}{dx}(e^x) = e^x
$$

La funzione esponenziale è quindi **l’unica funzione non banale uguale alla propria derivata**.

---
### **5. Derivata della funzione logaritmo naturale**

Consideriamo:
$$
f(x) = \ln x
$$

Per definizione:
$$
f'(x) =
\lim_{h \to 0}
\frac{\ln(x+h) - \ln x}{h}
$$

Usiamo le proprietà dei logaritmi:

$$
\ln(x+h) - \ln x = \ln\left(\frac{x+h}{x}\right)
$$

Scriviamo il rapporto come:

$$
\ln\left(1 + \frac{h}{x}\right)
$$

Il rapporto incrementale diventa:

$$
\frac{\ln\left(1 + \frac{h}{x}\right)}{h}
$$

Per ricondurci a un limite notevole, moltiplichiamo e dividiamo per $x$:

$$
\frac{1}{x}
\cdot
\frac{\ln\left(1 + \frac{h}{x}\right)}{\frac{h}{x}}
$$

Ora riconosciamo il limite notevole:

$$
\lim_{u \to 0} \frac{\ln(1+u)}{u} = 1
$$

con $u = \frac{h}{x}$.

Passando al limite:

$$
f'(x) = \frac{1}{x}
$$

**Attenzione al dominio**

Il logaritmo naturale è definito solo per $x > 0$, quindi:

$$
\frac{d}{dx}(\ln x) = \frac{1}{x}
\qquad \text{per } x > 0
$$

---
### **6. Riepilogo delle derivate fondamentali**

$$
\frac{d}{dx}(k) = 0
$$

$$
\frac{d}{dx}(x^\alpha) = \alpha x^{\alpha - 1}
$$

$$
\frac{d}{dx}(\sin x) = \cos x
$$

$$
\frac{d}{dx}(\cos x) = -\sin x
$$

$$
\frac{d}{dx}(e^x) = e^x
$$

$$
\frac{d}{dx}(\ln x) = \frac{1}{x} \quad (x > 0)
$$

---
### **7. Chiusura del primo blocco sulle derivate**

Con queste tre lezioni abbiamo visto:

- la **definizione di derivata**
    
- il **significato geometrico**
    
- il calcolo diretto delle **derivate fondamentali**

Da ora in poi **non useremo più la definizione ogni volta**.

---
### **8. Anticipazione**

👉 **Nella prossima lezione** studieremo le **regole di derivazione**, che permettono di calcolare rapidamente la derivata di funzioni anche molto complesse.