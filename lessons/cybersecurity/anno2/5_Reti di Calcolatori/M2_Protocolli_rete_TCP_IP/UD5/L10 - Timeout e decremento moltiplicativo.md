# **Lezione 10: Timeout e decremento moltiplicativo**

---

### **1. Introduzione**

Uno dei modi più semplici — e apparentemente più ovvi — per individuare la perdita di un pacchetto in TCP è **usare il timer di ritrasmissione**.  
Quando il **timeout** scade e non è arrivato alcun **ACK** per un segmento, il mittente **ritrasmette** il pacchetto.

Tuttavia, questa strategia presenta **limiti notevoli** soprattutto quando:

- la **finestra di congestione (CongWin)** è grande;
    
- e il **tempo di round trip (RTT)** è elevato.
    

> In tali casi, la rilevazione basata unicamente sul timeout comporta un ritardo eccessivo nella ripresa del flusso, con perdita di efficienza e spreco di banda.

---

### **2. Effetti del timeout con grandi finestre**

Consideriamo una **finestra di 5 segmenti MSS**.

#### **Scenario**

1. Il mittente invia i segmenti **1, 2, 3, 4, 5**.
    
2. Il **primo segmento** viene **perso**.
    
3. I segmenti successivi arrivano regolarmente, ma il mittente **non riceve ACK cumulativi**, perché il ricevente non può confermare pacchetti successivi a uno mancante.
    
4. Il mittente, quindi, **attende la scadenza del timeout** per capire che qualcosa non va.
    

#### **Conseguenze**

- Nel migliore dei casi, il **timer di ritrasmissione scade dopo circa $2 \times RTT$**.
    
- Poi il pacchetto ritrasmesso deve **attraversare la rete (RTT)** e il suo ACK deve **ritornare indietro (RTT)** → si perdono quindi **più di due finestre complete di dati**.
    

$$  
\text{Tempo perso} > 2 \times RTT  
$$

> Il risultato è un notevole rallentamento nella trasmissione complessiva.

---

### **3. Impatto sulla finestra di congestione**

Dopo il timeout, TCP esegue **due operazioni penalizzanti**:

1. **CongWin** viene **riportata a 1** segmento (ripartenza in Slow Start).
    
2. **Threshold** viene **dimezzata** rispetto al valore precedente.
    

In pratica, il protocollo **reinizia daccapo** come se la rete fosse di nuovo in fase di apprendimento.

> Questo comportamento, sebbene conservativo, riduce bruscamente il throughput: dopo ogni perdita rilevata da timeout, la connessione deve “ricostruire” lentamente la finestra ottimale.

---

### **4. Pseudocodice del meccanismo**

```c
/* Congestion Avoidance con decremento moltiplicativo */

while (no loss event) {
    ogni w segmenti ACKed:
        CongWin++;
}

if (loss event detected) {
    threshold = CongWin / 2;
    CongWin = 1;     // reset: slow start
    perform slow start;
}
```

Questo schema riflette il principio base del **decremento moltiplicativo e incremento additivo (AIMD)**:

- In caso di congestione, si **riduce drasticamente** la finestra (moltiplicativamente).
    
- In assenza di problemi, si **aumenta lentamente** (additivamente).
    

---

### **5. Decremento moltiplicativo: andamento nel tempo**

Il comportamento di TCP può essere visualizzato con un grafico temporale della **finestra di congestione (CongWin)**:

![](imgs/Pasted%20image%2020260225150604.png)

---

#### **Descrizione del grafico sotto**

- **Asse X:** tempo (in secondi).
    
- **Asse Y:** dimensione della finestra (in KB).
    
- **Linea blu:** andamento della CongWin.
    
- **Tratti verticali brevi:** invio dei segmenti.
    
- **Tratti verticali lunghi:** segmento ritrasmesso (indica perdita).
    
- **Punti nella parte superiore:** timeout verificato.
    

#### **Osservazioni chiave**

- **Da 0 a 0.4 s:** fase di **Slow Start** → crescita rapida della finestra.
    
- **A 2.0 s:** si verifica un **timeout** → finestra azzerata e ripartenza da 1.
    
- **Da 5.5 a 5.6 s:** nuova **Slow Start**.
    
- **Da 5.6 a 6.8 s:** fase di **Congestion Avoidance** (incremento additivo).

![](imgs/Pasted%20image%2020260225150648.png)

> Il ciclo completo mostra la tipica **oscillazione TCP** tra espansione e contrazione della finestra in risposta agli eventi di perdita.

---

### **6. Analisi qualitativa**

Questo andamento mostra chiaramente due aspetti fondamentali:

1. **TCP è prudente:** dopo ogni perdita, riparte lentamente (Slow Start) per non saturare la rete.
    
2. **TCP è adattivo:** quando la rete è stabile, aumenta la finestra in modo graduale per sfruttare tutta la banda disponibile.
    

Il meccanismo complessivo segue un comportamento **oscillante ma convergente**, che mantiene la rete:

- **vicina al limite di saturazione**,
    
- ma **senza collassare** per congestione.
    

---

### **7. Sintesi finale**

|**Evento**|**Effetto su CongWin**|**Effetto su Threshold**|**Fase successiva**|
|---|---|---|---|
|ACK regolari|Incremento additivo|Nessuna variazione|Congestion Avoidance|
|Timeout|Azzeramento (CongWin=1)|Dimezzamento|Slow Start|
|Nuova stabilità|Crescita esponenziale|Rimane dimezzata|Fase di probing|

---

### **8. Conclusione**

Il **controllo tramite timeout** è essenziale per la **correttezza** del TCP, ma non per la **rapidità**.  
È una **rete di sicurezza** che entra in gioco quando tutti gli altri meccanismi (ACK duplicati, fast retransmit, fast recovery) falliscono.

> Tuttavia, l’approccio basato sul solo timeout — senza tecniche come Fast Retransmit — porterebbe a un TCP troppo “lento a reagire”, con un throughput complessivo molto più basso.

In sintesi:

- **Timeout → strumento di ultima istanza**, robusto ma penalizzante.
    
- **Fast Retransmit/Fast Recovery → strumenti proattivi**, veloci e più efficienti.