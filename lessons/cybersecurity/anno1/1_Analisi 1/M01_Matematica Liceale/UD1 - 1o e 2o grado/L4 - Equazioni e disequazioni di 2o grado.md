# **M1 UD1 Lezione 4 - Equazioni e disequazioni di secondo grado**

### **1. Introduzione: perché il secondo grado “apre un portone”**

Le equazioni e disequazioni di **secondo grado** sono il primo vero salto di complessità rispetto al primo grado perché introducono:

- **non linearità** strutturale ($x^2$)
    
- **molteplicità di soluzioni** (0, 1 doppia, 2)
    
- **dipendenza qualitativa dai parametri** ($a,b,c$)
    
- **collegamento profondo con il grafico** (parabola)
    
- **studio del segno** come strumento universale per risolvere disequazioni
    

In Analisi 1, il secondo grado è la “palestra” perfetta per imparare la mentalità corretta:

- non esiste solo “fare i conti”
    
- esiste anche capire **cosa può succedere** e **perché**
    

---

## **2. Equazione di secondo grado: definizione generale**

Un’equazione di secondo grado in $x$ è:

$$  
ax^2 + bx + c = 0  
$$

con:

- $a,b,c \in \mathbb{R}$
    
- $a \neq 0$
    

---

## **3. Normalizzazione: ridurre al caso monico**

Spesso conviene dividere per $a$ (se $a\neq 0$), ottenendo:

$$  
x^2 + px + q = 0  
$$

dove:

$$  
p = \frac{b}{a}, \quad q = \frac{c}{a}.  
$$

Questo passaggio semplifica molte manipolazioni e fa emergere chiaramente le formule di Viète.

---

## **4. Il discriminante $\Delta$ e la classificazione delle soluzioni**

Si definisce il **discriminante**:

$$  
\Delta = b^2 - 4ac  
$$

La natura delle soluzioni dipende _solo_ da $\Delta$:

- $\Delta > 0$ → **due soluzioni reali distinte**
    
- $\Delta = 0$ → **una soluzione reale doppia**
    
- $\Delta < 0$ → **nessuna soluzione reale** (due complesse coniugate)
    

Interpretazione geometrica (fondamentale):

- $\Delta > 0$: la parabola interseca l’asse $x$ in due punti
    
- $\Delta = 0$: la parabola è tangente all’asse $x$
    
- $\Delta < 0$: la parabola non interseca l’asse $x$
    

---

## **5. Formula risolutiva classica**

Se $\Delta \ge 0$, le soluzioni reali sono:

$$  
x_{1,2} = \frac{-b \pm \sqrt{\Delta}}{2a}  
$$

---

## **6. Formula del $\Delta/4$: trucco “da ingegneria” per snellire i conti**

Quando $b$ è pari o comunque conviene lavorare con $b/2$, si usa:

$$  
\Delta' = \left(\frac{b}{2}\right)^2 - ac  
$$

Osserva che:

$$  
\Delta = b^2 - 4ac = 4\left[\left(\frac{b}{2}\right)^2 - ac\right] = 4\Delta'  
$$

Quindi:

$$  
\sqrt{\Delta} = \sqrt{4\Delta'} = 2\sqrt{\Delta'}  
$$

e la formula risolutiva diventa:

$$  
x_{1,2} = \frac{-b \pm 2\sqrt{\Delta'}}{2a}  
= \frac{-b}{2a} \pm \frac{\sqrt{\Delta'}}{a}  
$$

Questa forma è spesso molto più rapida:

$$  
x_{1,2} = -\frac{b}{2a} \pm \frac{\sqrt{\left(\frac{b}{2}\right)^2 - ac}}{a}  
$$

---

## **7. Completamento del quadrato e forma canonica**

Un metodo concettualmente fondamentale (e che prepara Taylor, derivate, minimi) è il **completamento del quadrato**.

Partiamo da:

$$  
ax^2 + bx + c  
$$

Fattorizziamo $a$:

$$  
a\left(x^2 + \frac{b}{a}x\right) + c  
$$

Completiamo il quadrato:

# $$  
x^2 + \frac{b}{a}x

\left(x + \frac{b}{2a}\right)^2 - \left(\frac{b}{2a}\right)^2  
$$

Quindi:

# $$  
ax^2 + bx + c

a\left(x + \frac{b}{2a}\right)^2 - a\left(\frac{b}{2a}\right)^2 + c  
$$

cioè:

# $$  
ax^2 + bx + c

a\left(x + \frac{b}{2a}\right)^2 + c - \frac{b^2}{4a}  
$$

Forma canonica:

# $$  
ax^2 + bx + c

a(x-x_V)^2 + y_V  
$$

dove:

$$  
x_V = -\frac{b}{2a},  
\quad  
y_V = c - \frac{b^2}{4a}  
$$

---

## **8. Le formule di Viète (fondamentali, e spesso “dimenticate”)**

Le formule di Viète collegano le **radici** ai coefficienti.

Sia:

$$  
ax^2 + bx + c = 0  
$$

con radici $x_1, x_2$ (reali o complesse).

Allora:

$$  
x_1 + x_2 = -\frac{b}{a}  
$$

$$  
x_1x_2 = \frac{c}{a}  
$$

Queste due relazioni sono potentissime perché ti permettono di:

- ricostruire l’equazione se conosci somma e prodotto
    
- controllare velocemente se una coppia di numeri può essere soluzione
    
- ragionare sul segno delle radici senza calcolarle esplicitamente
    
- fare problemi parametrici in modo elegante
    

Nel caso monico:

$$  
x^2 + px + q = 0  
$$

diventano:

$$  
x_1 + x_2 = -p,  
\quad  
x_1x_2 = q  
$$

---

## **9. Fattorizzazione tramite radici**

Se l’equazione ha radici $x_1, x_2$, allora:

$$  
ax^2 + bx + c = a(x-x_1)(x-x_2)  
$$

Se la radice è doppia $x_0$:

$$  
ax^2 + bx + c = a(x-x_0)^2  
$$

Questo è essenziale per lo **studio del segno** e per le **disequazioni**.

---

## **10. Disequazioni di secondo grado: idea chiave**

Una disequazione di secondo grado è:

$$  
ax^2 + bx + c > 0  
$$

oppure $\ge 0$, $<0$, $\le 0$.

Il modo “ingegneristico” di risolverla è sempre questo:

1. studia la parabola $y=ax^2+bx+c$
    
2. trova le radici (se esistono)
    
3. usa la concavità (segno di $a$)
    
4. deduci dove la funzione è positiva o negativa
    

---

## **11. Caso $\Delta > 0$: due radici reali distinte**

Siano $x_1 < x_2$ le radici.

Allora:

- se $a>0$ (concava verso l’alto):
    

$$  
ax^2 + bx + c > 0  
\quad \text{per } x < x_1 \ \text{oppure}\ x > x_2  
$$

$$  
ax^2 + bx + c < 0  
\quad \text{per } x_1 < x < x_2  
$$

- se $a<0$ (concava verso il basso) si inverte:
    

$$  
ax^2 + bx + c > 0  
\quad \text{per } x_1 < x < x_2  
$$

$$  
ax^2 + bx + c < 0  
\quad \text{per } x < x_1 \ \text{oppure}\ x > x_2  
$$

Per $\ge$ o $\le$ basta includere le radici.

---

## **12. Caso $\Delta = 0$: radice doppia**

La radice doppia è:

$$  
x_0 = -\frac{b}{2a}  
$$

e:

$$  
ax^2 + bx + c = a(x-x_0)^2  
$$

Poiché $(x-x_0)^2 \ge 0$ sempre:

- se $a>0$:
    

$$  
ax^2 + bx + c \ge 0 \quad \text{per ogni } x  
$$

e vale $=0$ solo in $x=x_0$.

- se $a<0$:
    

$$  
ax^2 + bx + c \le 0 \quad \text{per ogni } x  
$$

e vale $=0$ solo in $x=x_0$.

Questo caso è importante perché è il primo esempio di funzione che non cambia segno ma “tocca” lo zero.

---

## **13. Caso $\Delta < 0$: nessuna radice reale**

La parabola non interseca l’asse $x$, quindi non cambia mai segno.

Il segno dipende solo da $a$:

- se $a>0$:
    

$$  
ax^2 + bx + c > 0 \quad \forall x  
$$

- se $a<0$:
    

$$  
ax^2 + bx + c < 0 \quad \forall x  
$$

Questo è un caso chiave per risolvere disequazioni “senza radici”.

---

## **14. Metodo “universale” per le disequazioni quadratiche**

Schema operativo che deve diventare automatico:

1. Porta tutto a sinistra:
    

$$  
ax^2 + bx + c \ \gtrless \ 0  
$$

2. Calcola $\Delta$ (o $\Delta'$)
    
3. Se $\Delta \ge 0$, trova $x_1, x_2$
    
4. Metti in ordine $x_1 < x_2$
    
5. Disegna mentalmente (o realmente) la parabola:
    
    - concavità data da $a$
        
    - intersezioni date dalle radici
        
6. Scrivi l’insieme soluzione come intervalli
    

---

## **15. Problemi parametrici: usare Viète invece di “fare casino”**

Molti esercizi “da ingegneria” chiedono condizioni sui parametri perché:

- l’equazione abbia soluzioni reali
    
- le soluzioni siano entrambe positive
    
- una sia maggiore dell’altra
    
- la somma delle soluzioni sia un certo valore
    

In questi casi:

- la condizione “soluzioni reali” è:
    

$$  
\Delta \ge 0  
$$

- la condizione “entrambe positive” usa Viète:
    

$$  
x_1 + x_2 > 0  
\quad \text{e} \quad  
x_1x_2 > 0  
$$

cioè:

$$  
-\frac{b}{a} > 0  
\quad \text{e} \quad  
\frac{c}{a} > 0  
$$

con in più (per essere sicuri che siano reali):

$$  
\Delta \ge 0  
$$

Questa mentalità è quella giusta: **usare struttura**, non brute force.

---

## **16. Checklist finale (da padrone del secondo grado)**

Devi saper fare:

1. Calcolare $\Delta$ e classificare soluzioni
    
2. Usare formula risolutiva classica
    
3. Usare il trucco del $\Delta/4$ quando conviene
    
4. Completare il quadrato e ottenere la forma canonica
    
5. Calcolare vertice e asse
    
6. Usare Viète:
    
    - somma radici $=-b/a$
        
    - prodotto radici $=c/a$
        
7. Fattorizzare $ax^2+bx+c$ con le radici
    
8. Risolvere disequazioni quadratiche con schema del segno
    
9. Gestire i tre casi $\Delta>0$, $\Delta=0$, $\Delta<0$
    
10. Impostare problemi parametrici in modo elegante
    

---

### **17. Ponte naturale: cosa succede dopo**

Dopo aver dominato il secondo grado, sei pronto per:

- disequazioni razionali (M1 UD3)
    
- domini di funzioni (M7)
    
- limiti (M8)
    
- derivate (M9) dove il secondo grado ricompare ovunque
    
- ottimizzazione (minimi e massimi) che è letteralmente “vertice generalizzato”