## **Lezione 1: Introduzione intuitiva al concetto di limite**

### **1. Perché iniziamo dai limiti (senza formule difficili)**

In questa lezione iniziamo a parlare di **limiti**, uno dei concetti più importanti di tutta l’Analisi matematica.

A differenza di molti corsi universitari, **non partiamo subito dalle definizioni formali**, che sono tecnicamente complesse e poco intuitive.

L’obiettivo iniziale è **capire davvero che cosa significhi “fare un limite”**, cioè che tipo di domanda stiamo ponendo e che tipo di risposta ci viene richiesta.

Solo dopo aver chiarito il significato intuitivo del limite, introdurremo il formalismo rigoroso.

Questa scelta è fondamentale: **senza intuizione, il formalismo resta vuoto**.

---
### **2. Primo esempio: limite di una funzione semplice**

Consideriamo la funzione:

$$

y = x + 1

$$

Il suo grafico è una retta.

![](../imgs/Pasted%20image%2020251218105338.png)

Supponiamo ora che ci venga chiesto di calcolare:  

$$

\lim_{x \to 1} (x + 1)

$$

Che cosa ci stanno chiedendo **davvero** con questa scrittura?

---
### **3. Cosa significa “$x$ tende a 1”**


La domanda non è:

> “Quanto vale la funzione in $x = 1$?”

La domanda è invece:

> “Che cosa succede ai valori della funzione quando $x$ **si avvicina** a 1?”

In termini grafici, stiamo osservando **le ordinate dei punti del grafico** mentre ci muoviamo sempre più vicino al punto di ascissa $x = 1$.

Immaginiamo quindi di **muoverci lungo il grafico**:

- prima avvicinandoci a $x = 1$ **da sinistra**,
    
- poi avvicinandoci a $x = 1$ **da destra**.

![](../imgs/Pasted%20image%2020251218105415.png)
  
In entrambi i casi, le ordinate dei punti del grafico si avvicinano sempre di più al valore $y = 2$.

---
### **4. Conclusione del primo esempio**

Poiché:

- avvicinandoci da sinistra le $y$ si avvicinano a 2,
    
- avvicinandoci da destra le $y$ si avvicinano a 2,  

possiamo concludere che:
$$

\lim_{x \to 1} (x + 1) = 2

$$

Il limite descrive quindi **il valore verso cui tende la funzione**, non necessariamente il valore che assume nel punto.

---

### **5. Secondo esempio: una parabola**

Consideriamo ora la funzione:
$$

y = x^2

$$

Il suo grafico è una parabola.

![](../imgs/Pasted%20image%2020251218105542.png)

Calcoliamo il limite:
$$

\lim_{x \to -1} x^2

$$

Come prima, la domanda va interpretata così:

> “A che valore tende $x^2$ quando $x$ si avvicina a $-1$?”

---
### **6. Interpretazione grafica del secondo esempio**

Immaginiamo di muoverci sul grafico verso il punto di ascissa $x = -1$:

- avvicinandoci da sinistra,
    
- avvicinandoci da destra.

In entrambi i casi, le ordinate dei punti del grafico si avvicinano al valore $y = 1$.

![](../imgs/Pasted%20image%2020251218105636.png)

---
### **7. Conclusione del secondo esempio**

Poiché il comportamento è lo stesso da entrambi i lati, possiamo affermare che:

$$

\lim_{x \to -1} x^2 = 1

$$

---
### **8. Ma allora perché non sostituire direttamente?**

A questo punto è naturale pensare:

> “Ma non potevamo semplicemente sostituire il valore di $x$ nella funzione?”

Infatti:

- nel primo esempio: $1 + 1 = 2$,
    
- nel secondo esempio: $(-1)^2 = 1$.

In questi casi, **la sostituzione diretta funziona perfettamente** ed è anche il metodo più rapido.

---
### **9. Perché serve davvero il concetto di limite**

Il problema nasce quando **la sostituzione non è possibile**.
Esistono funzioni che presentano **comportamenti anomali** in prossimità di certi punti.
Un esempio tipico è quello di una funzione con **asintoto verticale**.

In questi casi:

- la funzione **non è definita** in un certo punto,
    
- il denominatore può annullarsi,
    
- la sostituzione diretta diventa illegale.

---
### **10. Esempio di funzione non definita in un punto**

Consideriamo una funzione che, per $x = 2$, presenta un asintoto verticale.

![](../imgs/Pasted%20image%2020251218105744.png)

Se proviamo a sostituire $x = 2$:

- al numeratore otteniamo un numero finito,
    
- al denominatore otteniamo zero.

Questo porta a una **divisione per zero**, che non ha significato matematico.

Già osservando il **dominio della funzione**, possiamo accorgerci che in $x = 2$ la funzione **non è definita**.

---
### **11. Come si calcola il limite in questi casi**

Quando non possiamo sostituire direttamente:

- dobbiamo studiare **il comportamento della funzione vicino al punto**,
    
- osservare cosa succede alle ordinate quando $x$ si avvicina al valore problematico.

Nei prossimi video vedremo che **non sarà necessario disegnare ogni volta il grafico**.

Esistono **regole algebriche e scorciatoie di calcolo** che permettono di prevedere il risultato del limite in modo rapido ed efficace.

---
### **12. Altri casi importanti in cui la sostituzione fallisce**

Oltre agli asintoti verticali, esistono altri casi fondamentali:

- funzioni con **punti esclusi** (buchi nel grafico),
    
- funzioni con **salti** (limiti destro e sinistro diversi).  

![](../imgs/Pasted%20image%2020251218105855.png)

Tutte queste situazioni verranno analizzate in modo sistematico più avanti.

---

### **13. Anticipazione: il caso fondamentale dell’infinito**

Per concludere questa introduzione, segnaliamo un caso centrale dell’Analisi:

$$

\lim_{x \to \pm \infty} f(x)

$$
I limiti all’infinito sono fondamentali per capire:

- il comportamento globale delle funzioni,
    
- la presenza di asintoti,
    
- la crescita o decrescita dei modelli matematici.

Di questo parleremo **nella prossima lezione**, in modo approfondito.