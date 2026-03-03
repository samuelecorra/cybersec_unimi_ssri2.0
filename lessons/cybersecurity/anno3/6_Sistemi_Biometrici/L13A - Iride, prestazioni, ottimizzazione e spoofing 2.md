# **Lezione 13A: Iride, prestazioni, ottimizzazione e spoofing**

---

### **1. Introduzione ai sistemi biometrici basati sull’iride**

Il riconoscimento dell’iride rappresenta una delle tecnologie biometriche più accurate oggi disponibili. Il principio fondamentale consiste nell’analizzare la **texture complessa e altamente distintiva** dell’iride, cioè la porzione colorata dell’occhio compresa tra pupilla e sclera, e trasformarla in una rappresentazione numerica confrontabile.

L’intero processo di riconoscimento può essere suddiviso nelle seguenti fasi principali:

1. Acquisizione dell’immagine dell’occhio.
    
2. Localizzazione della regione dell’iride.
    
3. Verifica della qualità e del fuoco.
    
4. Normalizzazione geometrica.
    
5. Estrazione delle caratteristiche.
    
6. Codifica in template biometrico.
    
7. Gestione delle occlusioni tramite maschere.
    

---

### **2. Localizzazione di pupilla e iride mediante operatore integrodifferenziale**

Per identificare la posizione dell’iride nell’immagine è necessario stimare con precisione:

- Centro della pupilla
    
- Centro dell’iride
    
- Raggi delle due circonferenze

Questo problema viene risolto attraverso un operatore integrodifferenziale che ricerca i bordi circolari caratterizzati da variazioni di intensità radiale.

La formulazione generale è:

$$

\max_{r, x_0, y_0}

\left|

G_{\sigma}(r) *

\frac{\partial}{\partial r}

\oint_{(x_0,y_0,r)}

\frac{I(x,y)}{2 \pi r} , ds

\right|

$$

![](imgs/Pasted%20image%2020260220122515.png)

dove:

- $I(x,y)$ è l’intensità dell’immagine.
    
- $(x_0, y_0)$ è il centro candidato.
    
- $r$ è il raggio.
    
- $G_{\sigma}$ è un filtro gaussiano per la regolarizzazione.

Il primo massimo individua la pupilla, il secondo massimo individua l’iride.

---

### **3. Normalizzazione e rappresentazione in coordinate polari**

Una volta localizzata l’iride, la regione anulare viene trasformata da coordinate cartesiane a coordinate polari:

$$

I(\rho, \theta)

$$

Questa trasformazione consente di compensare variazioni dovute a:

- Dilatazione pupillare
    
- Rotazioni dell’occhio
    
- Differenze di distanza dal sensore

---

### **4. Estrazione delle caratteristiche mediante wavelet di Gabor**

L’estrazione del template avviene tramite filtri di Gabor bidimensionali, che permettono di analizzare la texture dell’iride in termini di frequenza e fase locale.

Il risultato della demodulazione produce una sequenza binaria chiamata **IrisCode**, in cui:

- Ogni posizione genera tipicamente **2 bit accoppiati**
    
- Viene generata anche una **maschera** che indica i bit non affidabili (occlusioni, riflessi, ciglia)

![](imgs/Pasted%20image%2020260220122602.png)

---

### **5. Tassi di errore nei sistemi di riconoscimento dell’iride**

I principali indicatori di prestazione sono:

- **FMR (False Match Rate)** — probabilità che un impostore venga accettato.
    
- **FNMR (False Non-Match Rate)** — probabilità che un genuino venga rifiutato.
    
- **FTE (Failure To Enroll)** — impossibilità di registrare un utente.
    
- **FTA (Failure To Acquire)** — impossibilità di acquisire una buona immagine.

In letteratura esistono studi che riportano valori estremamente bassi di FMR, talvolta prossimi allo zero, mentre FNMR può dipendere dalle condizioni operative.

![](imgs/Pasted%20image%2020260220122640.png)

Esempi tipici:

- FNMR circa 4% al primo tentativo.
    
- FNMR circa 0.4% su tre tentativi.
    
- FTE fino al 7% in condizioni difficili.

![](imgs/Pasted%20image%2020260220122726.png)

![](imgs/Pasted%20image%2020260220122737.png)



---

### **6. Valutazioni indipendenti e benchmark internazionali**

Le prestazioni vengono spesso valutate tramite benchmark indipendenti come quelli del NIST (National Institute of Standards and Technology).

Esempio di risultati:

- FNMR ≈ 0.0057 a FMR = $10^{-5}$
    
- Accuratezza superiore al 99% su immagini infrarosse.

![](imgs/Pasted%20image%2020260220122836.png)

Questi risultati dipendono fortemente da:

- Qualità dell’immagine
    
- Sensore utilizzato
    
- Condizioni operative

---

### **7. Identificazione su larga scala e metriche FNIR/FPIR**

Nel contesto dell’identificazione uno-a-molti si introducono:

- **FPIR (False Positive Identification Rate)** — impostori erroneamente trovati nel database.
    
- **FNIR (False Negative Identification Rate)** — genuini non trovati nel database.

![](imgs/Pasted%20image%2020260220122907.png)

![](imgs/Pasted%20image%2020260220122936.png)

Esempi:

- Database da 500.000 individui con FPIR = 1%.
    
- FNIR tra 0.23% e 0.78% a seconda del sistema e dell’uso di uno o due occhi.

![](imgs/Pasted%20image%2020260220123002.png)

---

### **8. Svantaggi della tecnologia dell’iride**

Nonostante l’elevata accuratezza, esistono alcune limitazioni:

- Necessità di cooperazione dell’utente.
    
- Posizionamento preciso rispetto al sensore.
    
- Costi elevati dei dispositivi ad alta sicurezza.
    
- Possibili problemi di acquisizione dovuti a:
    
    - Occlusioni
        
    - Lenti a contatto
        
    - Riflessi
        
    - Chirurgie oculari
        
    - Movimenti involontari

---

### **9. Failure To Acquire e Failure To Enroll**

FTA e FTE dipendono da numerosi fattori:

- Qualità dell’immagine.
    
- Banda spettrale del sensore.
    
- Protocollo di acquisizione.
    
- Condizioni ambientali.
    
- Fisiologia dell’occhio.
    
- Farmaci e patologie.

![](imgs/Pasted%20image%2020260220123105.png)

Studi indicano valori medi di FTE intorno allo 0.2% con variazioni fino allo 0.8%.

---

### **10. Il template biometrico come stringa identificativa**

Un template biometrico può essere visto come una stringa di bit che:

- È molto simile per acquisizioni dello stesso individuo.
    
- È molto diversa tra individui diversi.

![](imgs/Pasted%20image%2020260220123145.png)

La distanza viene calcolata tipicamente tramite **distanza di Hamming**.

---

### **11. Numero di bit significativi nell’IrisCode**

Esperimenti su migliaia di iridi mostrano che le distanze tra IrisCode di individui diversi seguono una distribuzione binomiale con:

  

$$

p = 0.5

$$

e circa **249 gradi di libertà**.

Ciò implica che gli IrisCode si comportano come stringhe casuali di 249 bit indipendenti.

La probabilità che due individui condividano il 31% dei bit è estremamente bassa.

![](imgs/Pasted%20image%2020260220123228.png)

Il numero di bit effettivi dipende dalle condizioni operative:

- Infrarosso con utenti cooperativi: circa 249 bit.
    
- Visibile con utenti non cooperativi: circa 100-200 bit.

![](imgs/Pasted%20image%2020260220123337.png)

---

### **12. Progettazione della soglia decisionale**

Se possiamo stimare la distanza di hamming fra iriscode di diversi (impostori) con la bernulliana vista, possiamo calcolare quanti tentativi dovremmo fare per trovare il primo FALSE MATCH (ovvero un impostore che entra!)

![](imgs/Pasted%20image%2020260220123434.png)

La soglia di distanza di Hamming deve essere scelta considerando:

- Dimensione del database.
    
- Numero di bit disponibili.
    
- Probabilità cumulativa di false accettazioni.

Se sono disponibili meno bit (per occlusioni), la distanza deve essere **rinormalizzata**.

![](imgs/Pasted%20image%2020260220123515.png)

---

### **13. Asimmetria delle distribuzioni reali**

Le distribuzioni sperimentali possono non essere perfettamente simmetriche a causa di:

- Shift multipli durante il matching.
    
- Imperfezioni ottiche.
    
- Rumore di acquisizione.

![](imgs/Pasted%20image%2020260220123539.png)

Le stime teoriche risultano spesso ottimistiche, rendendo necessario il test sul campo.

---

### **14. Confronto tra occhi geneticamente correlati**

Esperimenti mostrano che:

- Le due iridi della stessa persona (destra e sinistra) sono statisticamente indipendenti.
    
- La distanza tra le due iridi di uno stesso individuo è simile a quella tra individui diversi.

![](imgs/Pasted%20image%2020260220123617.png)

Ciò dimostra l’elevata entropia dell’iride.

---

### **15. Tempi di calcolo e architetture hardware**

Le prestazioni computazionali dipendono dall’hardware utilizzato.

Con architetture dedicate (anche RISC a frequenze moderate) i sistemi commerciali raggiungono tempi di elaborazione molto ridotti.

![](imgs/Pasted%20image%2020260220123645.png)

---

### **16. Sistemi commerciali avanzati**

I dispositivi di fascia alta possono includere:

- Acquisizione dual-eye.
    
- Sensori infrarossi ad alta qualità.
    
- Controllo della pupilla.
    
- Tecniche di liveness detection.
    
- Neutralizzazione dei riflessi degli occhiali.

L’uso di due occhi riduce ulteriormente i tassi di errore.

![](imgs/Pasted%20image%2020260220123822.png)

![](imgs/Pasted%20image%2020260220123838.png)

![](imgs/Pasted%20image%2020260220123854.png)

![](imgs/Pasted%20image%2020260220123907.png)

---

### **17. Considerazioni finali sulla tecnologia dell’iride**

I sistemi basati su IrisCode rappresentano attualmente:

- I sistemi biometrici “live-scan” più accurati disponibili.
    
- Tecnologie adatte ad applicazioni di sicurezza elevata.
    
- Soluzioni con altissima capacità discriminativa grazie al grande numero di bit indipendenti.

Tuttavia, le prestazioni reali dipendono sempre dalle condizioni operative e dalla qualità dei sensori.