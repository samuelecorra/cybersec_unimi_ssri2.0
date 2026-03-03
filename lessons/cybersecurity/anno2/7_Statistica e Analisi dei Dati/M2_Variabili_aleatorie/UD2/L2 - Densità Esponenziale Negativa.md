# **M1 UD2 Lezione 2 - Densità Esponenziale Negativa**

### **1. Dalla distribuzione geometrica alla densità esponenziale**

La **distribuzione geometrica** descrive la probabilità che un evento (ad esempio un “successo”) si verifichi **al tentativo $i$-esimo**, dopo una sequenza di fallimenti indipendenti:

$$  
G(i \mid p) = q^{i-1}p \quad \text{con } q = 1 - p  
$$

Questa distribuzione è **discreta** e si applica quando i tentativi sono separati (per esempio lanci di moneta, estrazioni, prove successive).  
Vogliamo ora **trasferire lo stesso modello al continuo**, dove l’evento può avvenire in **qualsiasi istante di tempo** $t$.

Esempi tipici:

- Arrivi di richieste a un server.
    
- Decadimenti radioattivi rilevati da un contatore Geiger.
    
- Picchi di rumore su una linea di trasmissione.

In tutti questi casi, gli eventi sono **indipendenti** e il sistema **non ha memoria**: il passato non influenza la probabilità di eventi futuri.

---

### **2. Dal discreto al continuo**

Nel modello discreto:

- la variabile $i$ rappresenta il **numero di tentativi**;
    
- $P(i)$ rappresenta la **probabilità** che il successo avvenga al tentativo $i$.

Nel continuo:

- la variabile $t$ rappresenta il **tempo**;
    
- $f(t)$ rappresenta la **densità di probabilità**, cioè la probabilità (infinitesima) che il successo avvenga nell’intervallo $[t, t + dt)$.

Formalmente:

$$  
f(t) = P(t \le x < t + dt)  
$$

Quindi:

- sul discreto abbiamo **probabilità di evento singolo** $P(i)$,
    
- sul continuo abbiamo **densità** $f(t)$, da integrare su un intervallo per ottenere una probabilità finita.

---

### **3. Dalla funzione di sopravvivenza discreta a quella continua**

La funzione di **sopravvivenza** della distribuzione geometrica è:

$$  
S(i) = q^i = (1 - p)^i  
$$

Passiamo ora al caso continuo.  
Consideriamo un processo dove:

- a ogni tentativo (o intervallo di tempo) la probabilità di successo è $p$;
    
- vogliamo rendere i tentativi sempre più ravvicinati (**infittimento**);
    
- riduciamo $p$ proporzionalmente, per mantenere costante la **media complessiva** del processo.

Sia la **media** del tempo tra due eventi $\mu$, allora il **tasso medio di eventi** per unità di tempo è:

$$  
\lambda = \frac{1}{\mu}  
$$

---

### **4. Il passaggio al limite continuo**

Partendo da $S(i) = (1 - p)^i$, infittiamo i tentativi nel tempo:

- sostituiamo $i$ con $t$, cioè $t = i$
    
- sostituiamo $p$ con un valore infinitesimo $dp = \lambda dt$

Nel limite di infiniti tentativi per unità di tempo ($n \to \infty$):

$$  
S(t) = \lim_{n \to \infty} (1 - \lambda \tfrac{t}{n})^n = e^{-\lambda t}  
$$

Questa è la **funzione di sopravvivenza dell’esponenziale negativa**.

---

### **5. Le tre funzioni fondamentali**

1. **Funzione di sopravvivenza**

$$  
S(t) = e^{-\lambda t}  
$$

2. **Funzione di fallibilità (cumulativa)**

$$  
F(t) = 1 - e^{-\lambda t}  
$$

3. **Densità di probabilità**

$$  
f(t) = \frac{dF(t)}{dt} = \lambda e^{-\lambda t}  
$$

---

### **6. Proprietà generali**

|**Funzione**|**Espressione**|**Significato**|
|---|---|---|
|$f(t)$|$\lambda e^{-\lambda t}$|Densità di probabilità (continua)|
|$F(t)$|$1 - e^{-\lambda t}$|Probabilità che l’evento si sia verificato entro $t$|
|$S(t)$|$e^{-\lambda t}$|Probabilità che l’evento non si sia ancora verificato|
|**Media**|$\mu = \frac{1}{\lambda}$|Tempo medio di attesa|
|**Varianza**|$\sigma^2 = \frac{1}{\lambda^2}$|Dispersione dei tempi|
|**Deviazione standard**|$\sigma = \frac{1}{\lambda}$|Ampiezza media attorno alla media|

---

### **7. Verifica di normalizzazione**

La densità deve essere normalizzata:

$$  
\int_0^{+\infty} f(t) \ dt = \int_0^{+\infty} \lambda e^{-\lambda t} \ dt = \lambda \cdot \frac{1}{\lambda} = 1  
$$

Quindi la **densità esponenziale negativa è già normalizzata**.

---

### **8. Interpretazione fisica e statistica**

- La **media $\mu = 1/\lambda$** rappresenta il **tempo medio di attesa** tra due eventi.
    
- La distribuzione è **monotonamente decrescente**: la probabilità di eventi “a breve” è sempre maggiore di quella di eventi “tardivi”.
    
- La **mancanza di memoria** significa che la probabilità di fallimento o successo **non dipende dal passato**:

$$  
P(t > s + t_0 \mid t > t_0) = P(t > s)  
$$

Questa proprietà distingue l’esponenziale da qualsiasi altra distribuzione continua.

---

### **9. Densità esponenziale e affidabilità**

L’esponenziale negativa è il **modello base per i tempi di vita** (o di guasto) di componenti che si degradano **senza usura progressiva**, cioè con tasso di guasto costante.

Esempi:

- Durata media di un componente elettronico.
    
- Tempo medio tra due arrivi a un server.
    
- Tempo di decadimento radioattivo.

Il parametro $\lambda$ viene interpretato come **tasso di guasto** o **frequenza media di arrivo**.

---

### **10. Riepilogo finale**

|**Proprietà**|**Formula / Espressione**|**Interpretazione**|
|---|---|---|
|**Densità**|$f(t) = \lambda e^{-\lambda t}$|Distribuzione continua dei tempi d’attesa|
|**Cumulativa (Fallibilità)**|$F(t) = 1 - e^{-\lambda t}$|Probabilità che l’evento si sia già verificato|
|**Sopravvivenza**|$S(t) = e^{-\lambda t}$|Probabilità di non aver avuto l’evento entro $t$|
|**Media**|$\mu = \frac{1}{\lambda}$|Tempo medio di attesa|
|**Varianza**|$\sigma^2 = \frac{1}{\lambda^2}$|Dispersione dei tempi|
|**Deviazione standard**|$\sigma = \frac{1}{\lambda}$|Ampiezza media|
|**Proprietà di memoria**|$P(t > s + t_0 \mid t > t_0) = P(t > s)$|Nessuna dipendenza dal passato|
|**Applicazione**|Tempi di vita, arrivi, decadimenti|Modello continuo con tasso costante|
