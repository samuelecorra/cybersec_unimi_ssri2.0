# **M1 UD5 Lezione 3 - Prior e Verosimiglianza**

### **1. Introduzione**

Questa lezione approfondisce due elementi centrali del Teorema di Bayes: la **prior** (probabilità a priori) e la **verosimiglianza** (likelihood).  
Vengono illustrati diversi esempi pratici, tra cui:

- la **variazione della prior** nel classico esperimento delle monete,
    
- e l’**applicazione del teorema di Bayes al test medico diagnostico**, che mostra il potere (e i limiti) dell’inferenza bayesiana in presenza di eventi rari.

---

### **2. Richiamo del Teorema di Bayes**

Per ipotesi alternative $H_1, H_2, \dots, H_n$ con probabilità a priori $P(H_1), P(H_2), \dots, P(H_n)$, 

e dato un evento osservato $D$ con probabilità condizionate $P(D|H_1), P(D|H_2), \dots, P(D|H_n)$,  

il Teorema di Bayes si scrive come:

$$  
P(H_k|D) = \frac{P(H_k) \cdot P(D|H_k)}{\sum_i P(H_i) \cdot P(D|H_i)}  
$$

---

### **3. Un meccanismo di aggiornamento della conoscenza**

Il teorema di Bayes è un **meccanismo di apprendimento** che trasforma:

- la **conoscenza precedente** (probabilità a priori, _prior_),
    
- in **conoscenza aggiornata** (probabilità a posteriori, _posterior_),
    
- utilizzando il **modello generativo** ($P(D|H_k)$, la _verosimiglianza_)  
    e i **dati osservati** ($D$, l’evidenza).

In forma proporzionale:

$$  
Posterior \propto Prior \times Verosimiglianza  
$$
Ovvero:
$$  
P(H_k|D) \propto P(H_k) \times P(D|H_k)  
$$

Questo mostra che la _posterior_ è proporzionale al prodotto di _prior_ e _verosimiglianza_, ovvero
il nostro valore di probabilità che cerchiamo è legato proporzionalmente al numeratore.

---

### **4. Ruolo della Prior**

Riprendiamo l’esempio classico delle **tre monete** (A, B, C):

- Moneta A: dà sempre Testa → $P(T|A) = 1$
    
- Moneta B: bilanciata → $P(T|B) = 1/2$
    
- Moneta C: dà sempre Croce → $P(T|C) = 0$

Nel caso originario le prior erano uniformi:  
$$  
P(A) = P(B) = P(C) = \tfrac{1}{3}  
$$

Osservando una Testa, la probabilità a posteriori risultava:  
$$  
P(A|T) = \tfrac{2}{3}, \quad P(B|T) = \tfrac{1}{3}, \quad P(C|T) = 0  
$$

![](imgs/Pasted%20image%2020251213115923.png)

---

### **5. Effetto della variazione della Prior**

Se cambiamo la composizione del sacchetto:

- 1 moneta A
    
- 2 monete B
    
- 1 moneta C

Le probabilità a priori diventano:  
$$  
P(A) = P(C) = \tfrac{1}{4}, \quad P(B) = \frac{2}{4} = 1/2
$$

I pesi dei cammini verso l’evento $T$ sono:  
$$  
P(A) \cdot P(T|A) = \tfrac{1}{4}, \quad P(B) \cdot P(T|B) = \tfrac{1}{4}, \quad P(C) \cdot P(T|C) = 0  
$$

L’evidenza vale:  
$$  
P(T) = \tfrac{2}{4}  
$$

Applicando Bayes:  
$$  
P(A|T) = \tfrac{1}{2}, \quad P(B|T) = \tfrac{1}{2}  
$$

➡️ Le monete A e B diventano **equiprobabili** a posteriori:  
l’osservazione di una Testa “favorisce” A, ma inizialmente le B erano più numerose, e nel nostro caso ne bilancia le chance perfettamente.

---

### **6. Generalizzazione con molte monete**

Anche aumentando enormemente il numero di monete C (che non danno mai Testa),  
il risultato non cambia, perché il contributo di $P(T|C)=0$ annulla il loro effetto nel calcolo di $P(T)$.

Esempio:  
$$  
P(A)=\tfrac{1}{100}, \quad P(B)=\tfrac{2}{100}, \quad P(C)=\tfrac{98}{100}  
$$

Si ottiene ancora:  
$$  
P(A|T)=P(B|T)=\tfrac{1}{2}  
$$

➡️ Conta **solo il rapporto tra prior e verosimiglianze**, non i valori assoluti.

---

### **7. Calcolo rapido con rapporti**

Se i rapporti tra prior sono $3:6:91$  
e le verosimiglianze (probabilità di Testa) sono $2:1:0$,  
le posteriori si ottengono direttamente:

$$  
P(A|T) = \frac{3 \times 2}{3\times2 + 6\times1 + 91\times0} = \tfrac{1}{2}  
$$

$$  
P(B|T) = \frac{6 \times 1}{3\times2 + 6\times1 + 91\times0} = \tfrac{1}{2}  
$$

---

### **8. Significato della Prior nei diversi contesti**

- **Canale di comunicazione:** la prior rappresenta il modello del segnale (percentuale di 1 e 0).
    
- **Sushi Delight:** la prior è la probabilità che il pesce iniziale fosse un piranha.
    
- **Diagnosi medica:** la prior corrisponde all’incidenza della malattia nella popolazione. Lo vediamo subito...

---

### **9. Esempio del Test Medico**

In una popolazione, l’incidenza di una malattia è del **5%**:  
$$  
P(M) = 0.05, \quad P(S) = 0.95  
$$  
Un test diagnostico fornisce il **risultato corretto nel 95% dei casi**:  
$$  
P(M^*|M) = 0.95, \quad P(S^*|S) = 0.95  
$$  
e quindi:  
$$  
P(M^*|S) = 0.05, \quad P(S^*|M) = 0.05  
$$

---

### **10. Prior per verosimiglianza - Calcolo con una popolazione di riferimento**

Consideriamo una popolazione di **10 000 individui**.  
Dato che:

- $P(S)=0.95 \Rightarrow 9500$ individui sani
    
- $P(M)=0.05 \Rightarrow 500$ individui malati

in teoria i soggetti seguono i seguenti percorsi.

---

### **Un sano viene dichiarato sano**

$$  
P(S) \cdot P(S^* \mid S) = (0.95)(0.95)  
$$

Nella popolazione:

$$  
9500 \times 0.95 = 9025  
$$

---

### **Un sano viene dichiarato malato**

$$  
P(S) \cdot P(M^* \mid S) = (0.95)(0.05)  
$$

Nella popolazione:

$$  
9500 \times 0.05 = 475  
$$

---

### **Un malato viene dichiarato malato**

$$  
P(M) \cdot P(M^* \mid M) = (0.05)(0.95)  
$$

Nella popolazione:

$$  
500 \times 0.95 = 475  
$$

---

### **Un malato viene dichiarato sano**

$$  
P(M) \cdot P(S^* \mid M) = (0.05)(0.05)  
$$

Nella popolazione:

$$  
500 \times 0.05 = 25  
$$

---

### **11. Risultati**

- Totale dichiarati **sani**: 9050
    
- Totale dichiarati **malati**: 950

$$  
P(S|S^*) = \frac{9025}{9050} \approx 0.997  
$$  
$$  
P(M|M^*) = \frac{475}{950} = 0.50  
$$


![](imgs/Pasted%20image%2020251213121444.png)

➡️ Chi risulta **sano** può essere sereno (99,7% di probabilità di esserlo davvero).  

![](imgs/Pasted%20image%2020251213121636.png)

➡️ Chi risulta **malato** ha solo il 50% di probabilità di esserlo realmente.

---

### **12. Interpretazione**

Il risultato è **controintuitivo**:  
nonostante il test sia accurato al 95%, quando l’evento è raro (malattia al 5%)  
la probabilità di errore resta alta.  
La causa è la **prior sbilanciata** verso i sani:  
i **falsi positivi** diventano numerosi rispetto ai **veri positivi**.

---

### **13. Applicazione formale del Teorema**

$$  
P(S|S^*) = \frac{P(S)P(S^*|S)}{P(S)P(S^*|S) + P(M)P(S^*|M)} = \frac{19×19}{19×19 + 1×1} ≈ 0.997  
$$

$$  
P(M|M^*) = \frac{P(M)P(M^*|M)}{P(S)P(M^*|S) + P(M)P(M^*|M)} = \frac{19×1}{19×1 + 1×19} = 0.50  
$$

---

### **14. Variante: malattia più rara**

Se l’incidenza scende all’1%:

- $P(M)=0.01$, $P(S)=0.99$
    
- $P(M^*|M)=0.95$, $P(M^*|S)=0.05$

Allora:  
$$  
P(M|M^*) = \frac{0.01×0.95}{0.01×0.95 + 0.99×0.05} ≈ 0.16  
$$

➡️ Il test positivo indica in realtà **84% di probabilità di essere sani**!

---

### **15. Il concetto di “sfuocamento”**

Il test diagnostico è come uno **strumento di misura** con **risoluzione limitata**:  
un errore del 5% (“sfuocamento del 5%”) non può rilevare accuratamente un effetto raro (malattia all’1%).

Questo spiega perché un test anche “quasi perfetto” può risultare poco utile in presenza di eventi molto rari.

---

### **16. Informazione e utilità del test**

Sebbene il test non sia conclusivo, **fornisce comunque informazione**:

- un esito **sano** aumenta la fiducia da 95% → 99,7%,
    
- un esito **malato** la aumenta da 5% → 50%.

Il **guadagno informativo relativo** è più alto nel secondo caso,  
anche se la certezza assoluta resta bassa.

---

### **17. Conclusione**

Il Teorema di Bayes mostra come **la probabilità non sia una verità assoluta**,  
ma un modo per **aggiornare razionalmente le credenze** alla luce dei dati.  
Il ruolo della _prior_ è determinante: può amplificare o ridurre l’impatto delle evidenze.

Quando l’evento è raro, persino un test molto affidabile può fornire risultati fuorvianti - 
ma è proprio il calcolo bayesiano che ci permette di **quantificare questa incertezza** in modo rigoroso.