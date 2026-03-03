## **Lezione 3: Complementi sul controllo d’errore (Parte II)**

### **1. Efficienza del protocollo Idle RQ**

Come visto in precedenza, nel protocollo **Idle RQ (Stop & Wait)** il mittente può inviare **un solo frame per volta** e deve attendere il relativo **ACK** prima di proseguire.  
Questo limita fortemente l’efficienza di utilizzo del canale.

![](imgs/Pasted%20image%2020260225123627.png)

L’efficienza si calcola come:

$$  
U_{idle} = \frac{T_{ix}}{T_{ix} + 2T_p} = \frac{1}{1 + 2\alpha}  
$$

dove:

- $T_{ix}$ = tempo di trasmissione del frame;
    
- $T_p$ = tempo di propagazione (solo andata);
    
- $\alpha = \dfrac{T_p}{T_{ix}}$.
    

> Più grande è il valore di $\alpha$, minore è l’efficienza del canale, poiché il mittente resta a lungo inattivo in attesa degli ACK.

---

### **2. Efficienza del protocollo Continuous RQ**

Se consideriamo un **Continuous RQ** che consente di avere fino a **3 frame “in sospeso”**, cioè non ancora confermati, l’efficienza cresce in modo proporzionale al numero di pacchetti contemporaneamente trasmessi:

$$  
U_{cont,3} = \frac{3T_{ix}}{T_{ix} + 2T_p} = \frac{3}{1 + 2\alpha} = 3U_{idle}  
$$

![](imgs/Pasted%20image%2020260225123737.png)

Questo risultato mostra che la **capacità di inviare più frame prima di ricevere un ACK** aumenta linearmente l’utilizzo del link.

> In generale, se la finestra consente _N_ frame in volo:  
> $$  
> U_{cont,N} = \frac{N}{1 + 2\alpha}  
> $$

---

### **3. Continuous RQ in presenza di errori**

Se durante la trasmissione si verifica un errore, il comportamento del protocollo dipende dal **tipo di strategia di gestione** adottata.

Supponiamo che il **frame N** arrivi **corrotto** al ricevente **R**.  
Quando R riceve il pacchetto successivo **(N+1)**, si accorge che **manca il pacchetto N** (perché il numero di sequenza atteso non coincide con quello ricevuto).

A questo punto, sono possibili **due strategie alternative**:

1. **Selective Repeat**
    
2. **Go-Back-N**
    

---

### **4. Strategia Selective Repeat**

Nel metodo **Selective Repeat**, il ricevente adotta un comportamento più “intelligente” e flessibile:

1. R **attende che M ritrasmetta** automaticamente il pacchetto mancante, allo scadere del timer (ritrasmissione implicita),  
    oppure **chiede esplicitamente** a M di reinviare il pacchetto N tramite un messaggio **NAK (Negative Acknowledgment)**.
    
2. Nel frattempo, R **accumula i frame successivi corretti** nella **receive list**, ma **non li inoltra** al livello superiore finché non riceve il pacchetto N mancante.
    
3. I pacchetti vengono quindi **riordinati** e consegnati in ordine numerico crescente.
    

> In pratica, Selective Repeat consente di **salvare e conservare** i pacchetti validi arrivati dopo un errore, evitando inutili ritrasmissioni.

#### **Esempio (ritrasmissione implicita)**

- Il frame **N** arriva corrotto → scartato.
    
- M continua a inviare **N+1, N+2, …**, che vengono accettati e messi in buffer.
    
- Allo scadere del **timer del pacchetto N**, M lo ritrasmette.
    
- Quando R riceve N, **riordina** la sequenza e la inoltra al livello superiore.

![](imgs/Pasted%20image%2020260225124330.png)

---

### **5. Ritrasmissione implicita: funzionamento del mittente**

Durante il funzionamento del **Selective Repeat**, il mittente M gestisce la **retransmission list** (lista FIFO dei pacchetti inviati ma non ancora confermati).

M rimuove i pacchetti dalla lista quando:

1. riceve un **ACK** valido per quel pacchetto, oppure
    
2. scade il **timer associato** e il pacchetto deve essere ritrasmesso.
    

Inoltre, se M riceve un ACK relativo a un pacchetto che **non è il primo nella lista**, deduce che **alcuni frame precedenti non sono stati confermati** e li ritrasmette.

> In generale, il **tempo di timeout** viene impostato a circa $2T_p$ (un tempo di andata e ritorno completo).

Il ricevente, a sua volta, **riordina i pacchetti** e li passa al livello superiore solo quando la sequenza è completa.

---

### **6. Strategia Go-Back-N**

In alternativa, il metodo **Go-Back-N** è meno flessibile ma più semplice da implementare.

1. Quando R si accorge di aver perso o ricevuto corrotto un pacchetto N, invia a M un messaggio speciale **REJECT (NAK cumulativo)**.
    
2. Questo messaggio richiede a M di **ritrasmettere non solo N**, ma **tutti i pacchetti successivi (N+1, N+2, …)** già inviati.
    
3. Nel frattempo, R **scarta tutti i pacchetti corretti** ricevuti dopo N, poiché non può consegnarli al livello superiore finché non ha ricevuto N.
    

> L’effetto è una ritrasmissione massiva, ma garantisce la **sequenzialità rigorosa** dei dati al prezzo di una **minore efficienza**.

---

### **7. Confronto tra Selective Repeat e Go-Back-N**

|Aspetto|**Selective Repeat**|**Go-Back-N**|
|---|---|---|
|**Gestione degli errori**|Solo i pacchetti errati vengono ritrasmessi|Tutti i pacchetti dal primo errato in poi vengono ritrasmessi|
|**Efficienza**|Alta, riduce il traffico ridondante|Bassa, genera più ritrasmissioni|
|**Complessità del ricevente**|Elevata (buffering e riordino)|Minore|
|**Richieste di memoria**|Maggiori (receive list e reordering)|Minori|
|**Uso tipico**|Reti affidabili e ad alta velocità|Reti semplici o a bassa affidabilità|

---

### **8. Corruzione degli ACK**

Anche un **ACK può arrivare corrotto**.  
In tal caso, M non può sapere se il pacchetto è stato effettivamente ricevuto.  
Due scenari possibili:

1. **ACK perso o danneggiato:**  
    M non riceve conferma → allo scadere del timer, **ritrasmette** il pacchetto.  
    → Il ricevente riceve un **duplicato**, che riconosce e **scarta**.
    
2. **ACK errato (con numero sbagliato):**  
    Il mittente ignora l’ACK se non corrisponde a un pacchetto presente nella sua finestra attiva.

![](imgs/Pasted%20image%2020260225124849.png)

In entrambi i casi, il protocollo rimane affidabile, anche se con un piccolo degrado dell’efficienza.

---

### **9. Conclusione**

Le due varianti di Continuous RQ, **Go-Back-N** e **Selective Repeat**, rappresentano due approcci complementari al problema del controllo d’errore:

- **Go-Back-N** privilegia la semplicità implementativa;
    
- **Selective Repeat** massimizza l’efficienza e minimizza le ritrasmissioni.
    

Entrambe si basano su un concetto fondamentale: il mantenimento di **finestre di trasmissione e ricezione** che consentono a più pacchetti di “viaggiare in volo” contemporaneamente, migliorando drasticamente l’uso del canale rispetto allo schema Stop & Wait.