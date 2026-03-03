# **Lezione 3: Complementi sul controllo d’errore (Parte III)**

---

### **1. Introduzione**

In questa terza parte concludiamo l’analisi dei protocolli di **ritrasmissione automatica (ARQ)**, approfondendo:

- la **variante esplicita** del _Selective Repeat_;
    
- il funzionamento dettagliato del _Go-Back-N_;
    
- e la tecnica del **piggybacking**, utilizzata nelle comunicazioni _full-duplex_ per ottimizzare il traffico di rete.
    

---

### **2. Selective Repeat con richiesta esplicita (Explicit Request)**

Nel _Selective Repeat_ classico, la ritrasmissione di un pacchetto difettoso avviene in due modi:

1. **implicito**, allo scadere del timer associato al pacchetto mancante;
    
2. **esplicito**, quando il ricevente invia un messaggio **NAK (Not Acknowledged)**.
    
![](imgs/Pasted%20image%2020260225125500.png)

In questa lezione analizziamo proprio il secondo caso: la **richiesta esplicita di ritrasmissione**.

---

#### **2.1 Funzionamento**

Quando il ricevente $R$ riceve un pacchetto errato:

- invia un **NAK[N]** al mittente $M$, dove $N$ è il numero del pacchetto corrotto o mancante;
    
- dopo l’invio del NAK, **R sospende l’invio degli ACK** per evitare conflitti in caso di errori di trasmissione del NAK stesso.
    

Se infatti il NAK venisse corrotto, continuando a inviare ACK successivi, il mittente **potrebbe non accorgersi** della perdita del pacchetto e quindi non ritrasmetterlo.

> L’idea è semplice: **meno messaggi circolano dopo un errore, minore è il rischio di confusione**.

---

#### **2.2 Esempio**

Supponiamo che il **frame N+1** arrivi corrotto.

1. $R$ invia un **NAK(N+1)**.
    
2. Fino alla ritrasmissione di $N+1$, $R$ **non invia ACK** per i frame successivi.
    
3. Quando riceve correttamente $N+1$, riprende a inviare ACK cumulativi (es. `ACK(N+4)` che conferma tutti i frame fino a $N+4$ compreso).
    

Questo schema è robusto e rapido, ma **costoso in termini di memoria**, poiché richiede buffer più ampi al ricevente per mantenere i pacchetti successivi in attesa.

---

#### **2.3 Quando usare il Selective Repeat esplicito**

Il _Selective Repeat_ è ideale quando:

- l’ordine di arrivo dei pacchetti **non è strettamente importante**, ad esempio nei flussi multimediali o pacchetti molto piccoli;
    
- il ricevente **riassembla i pacchetti direttamente** prima di inoltrarli al livello superiore;
    
- la rete è **ad alta velocità (bit rate elevato)**, dove i ritardi di ritrasmissione pesano di più sull’efficienza complessiva.
    

Se i pacchetti sono invece grandi o la rete introduce ritardi significativi, è preferibile utilizzare un meccanismo più semplice: il **Go-Back-N**.

---

### **3. Il protocollo Go-Back-N**

Il **Go-Back-N** è una variante di _Continuous RQ_ più semplice e più economica da implementare rispetto al _Selective Repeat_.  
È anche il meccanismo alla base del protocollo **TCP/IP**.

---

#### **3.1 Principio operativo**

1. Se un frame $N+1$ arriva corrotto, $R$ invia un **NAK(N+1)**.
    
2. Tutti i frame successivi ($N+2, N+3, \dots$) vengono **scartati**.
    
3. Il mittente $M$, quando riceve il NAK, **ritrasmette da $N+1$ in poi**.
    
4. Gli ACK ricevuti da $M$ sono **cumulativi**:  
    ad esempio, `ACK(N+2)` conferma la ricezione di **tutti i frame fino a $N+2$ compreso**.

![](imgs/Pasted%20image%2020260225125953.png)

![](imgs/Pasted%20image%2020260225130053.png)

---

### **4. Pseudocodice del Go-Back-N**

#### **4.1 Lato mittente (M)**

```text
[V(S) = 0]
[RL] = lista di ritrasmissione

start:
switch (evento) {
  case "arriva pacchetto dal livello superiore":
      assegna numero V(S);
      metti pacchetto in [RL];
      trasmetti pacchetto;
      avvia Timer(V(S));
      V(S)++;
      break;

  case "ACK[N]":
      if (ACK corretto)
          rimuovi pacchetti <= N da [RL];
          ferma Timer;
      else
          ignora ACK;
      break;

  case "NAK[N]":
  case "scade Timer(V(S))":
      per tutti i pacchetti in [RL]:
          ritrasmetti pacchetto;
          riavvia Timer;
      break;
}
goto start;
```

---

#### **4.2 Lato ricevente (R)**

```text
[V(R) = 0]
stato = "normale"

start:
switch (evento) {
  case "pacchetto[N] ricevuto":
      if (corretto) {
          if (N == V(R)) {
              manda ACK[V(R)];
              passa pacchetto al livello superiore;
              V(R)++;
              stato = "normale";
              stop Timer_NAK;
          } else if (N > V(R) && stato == "normale") {
              invia NAK[V(R)];
              avvia Timer_NAK;
              stato = "nak";
          } else {
              invia ACK[V(R)];
              scarta pacchetto duplicato;
          }
      } else {
          scarta pacchetto;
      }
      break;

  case "scade Timer_NAK":
      invia NAK[V(R)];
      riavvia Timer_NAK;
      break;
}
goto start;
```

---

### **5. Commenti e osservazioni**

- Nel **Go-Back-N**, il ricevente ha bisogno di **una sola posizione di buffer**, poiché mantiene solo il prossimo pacchetto atteso.
    
- Il **mittente**, invece, deve disporre di una **retransmission list** abbastanza ampia da contenere tutti i pacchetti non ancora confermati.
    

La dimensione minima della lista del mittente è data da:

$$  
n_{RL} \geq \frac{T_{ix} + 2T_p}{T_{ix}} = 1 + 2\alpha  
$$

dove:

- $T_{ix}$ è il tempo di trasmissione del pacchetto;
    
- $T_p$ è il tempo di propagazione;
    
- $\alpha = T_p / T_{ix}$.
    

---

### **6. Piggybacking**

Finora abbiamo considerato connessioni **half-duplex**, dove la comunicazione avviene in **una sola direzione alla volta**.  
Nelle reti reali, però, la maggior parte delle connessioni è **full-duplex**: entrambi gli host agiscono **simultaneamente da mittente e ricevente**.

Questo significa che, in ogni direzione, fluiscono:

- **pacchetti dati**, e
    
- **ACK/NAK** di conferma.
    

Per evitare di trasmettere messaggi separati di ACK, si utilizza la tecnica detta **piggybacking** (“portare sulla schiena”).

---

#### **6.1 Funzionamento del piggybacking**

Ogni pacchetto inviato contiene:

- un codice **I(N)**, che indica il suo **numero di sequenza**, e
    
- un codice **N(R)**, che rappresenta l’**ultimo pacchetto ricevuto correttamente** nella direzione opposta.
    

> In questo modo, i pacchetti stessi trasportano anche le informazioni di ACK/NAK relative all’altra direzione, riducendo il numero di frame trasmessi.

---

#### **6.2 Vantaggi**

- Diminuzione del numero di pacchetti di controllo;
    
- Migliore efficienza del canale;
    
- Maggiore velocità nelle comunicazioni simmetriche.
    

---

### **7. Conclusione**

In sintesi:

- **Selective Repeat esplicito** → ottimo per reti veloci, flessibile ma costoso in memoria;
    
- **Go-Back-N** → più semplice e adottato in TCP/IP;
    
- **Piggybacking** → ottimizza i flussi bidirezionali riducendo il traffico di ACK.
    

Con questi tre meccanismi, si chiude l’analisi dei protocolli ARQ, che rappresentano la base del **controllo d’errore e di flusso** nei sistemi TCP/IP moderni.


---


![](imgs/Pasted%20image%2020251125070043.png)

![](imgs/Pasted%20image%2020251125070052.png)


---

![](imgs/Pasted%20image%2020251125070124.png)


---


![](imgs/Pasted%20image%2020251125070148.png)


---

![](imgs/Pasted%20image%2020251125070219.png)

