# **Lezione 5: Flusso TCP (Parte II)**

---

### **1. Trasferimento di grandi quantità di dati**

Quando il mittente deve inviare **molti dati consecutivamente**, genera un gran numero di segmenti TCP in un intervallo di tempo molto breve.  
In queste condizioni, possono emergere problemi legati alla **differenza di velocità** tra:

- il **mittente**, che produce i dati rapidamente, e
    
- il **destinatario**, che potrebbe **non riuscire a consumarli altrettanto in fretta** (ad esempio perché l’applicazione che riceve i dati li elabora lentamente o attende input dell’utente).
    

#### **Soluzione**

TCP utilizza il **campo “Advertised Window”** per rallentare il mittente.  
Il ricevente comunica al mittente la quantità di spazio disponibile nel proprio buffer, limitando così il flusso di trasmissione.

> Tuttavia, se la finestra diventa troppo piccola, possono verificarsi problemi di stallo o inefficienza.

---

### **2. Chiusura della finestra di ricezione**

Quando il buffer del ricevente si riempie, esso:

1. continua a inviare gli **ACK** per i pacchetti ricevuti,
    
2. ma specifica una **finestra di dimensioni nulle** (`window = 0`).
    

In questo modo segnala al mittente che **non può più accettare nuovi dati** finché l’applicazione non ne avrà liberato.

Durante questa fase:

- il mittente **non deve inviare nuovi dati**,
    
- ma invia **segmenti TCP di 1 byte** (chiamati _window probes_) per verificare periodicamente se la finestra si è riaperta.

![](imgs/Pasted%20image%2020260225144414.png)

> Questi pacchetti “di prova” consentono al ricevente di rispondere con un ACK che aggiorna la finestra.

---

### **3. Messaggio di “Window Update”**

Quando l’applicazione ricevente riprende a leggere dati dal buffer, lo spazio si libera.  
Il ricevente invia quindi un messaggio **ACK di tipo “Window Update”**, che comunica al mittente la **nuova dimensione della finestra disponibile**.

#### **Problema**

Se il messaggio di _window update_ si perde:

- il **destinatario aspetta invano nuovi dati**, e
    
- il **mittente** non può trasmettere, poiché crede ancora che la finestra sia chiusa.
    

#### **Soluzione**

TCP introduce due strategie:

1. **Timer sul lato ricevente**  
    Dopo l’invio del _window update_, il ricevente imposta un **timer**.  
    Se il mittente non riprende a inviare entro un certo tempo, il ricevente **ritrasmette** il messaggio.
    
2. **Persist Timer sul lato mittente**  
    Il mittente imposta un **persist timer** quando la finestra ricevuta è pari a zero.  
    Al termine del timer, invia un **window probe**, ossia un pacchetto TCP con 1 byte di dati per “testare” la finestra.
    

---

### **4. Il Persist Timer TCP**

Il **persist timer** serve a **mantenere viva la comunicazione** anche quando la finestra è chiusa, evitando un deadlock tra i due host.

![](imgs/Pasted%20image%2020260225144458.png)

#### **Funzionamento**

- Quando la finestra annunciata dal ricevente è **uguale a zero**, il mittente:
    
    1. **avvia il persist timer**,
        
    2. e alla sua scadenza **invia un “window probe”** (un pacchetto TCP contenente 1 byte).
        
- Se la finestra è ancora chiusa, il ricevente risponde con un ACK (senza incrementare l’ACK number).
    
- Il mittente riavvia il timer e **ripete il processo** fino a quando non riceve un _window update_ che riapre la finestra.
    

#### **Scopo**

Questo meccanismo evita lo **stallo definitivo** della connessione quando un _window update_ si perde o non viene trasmesso.

---

### **5. La sindrome della “Silly Window”**

La **Silly Window Syndrome (SWS)** è una condizione di **inefficienza cronica** nella gestione della finestra TCP, che si verifica quando mittente e destinatario scambiano **piccole quantità di dati** in modo ripetuto, invece di segmenti completi.

#### **Esempio di scenario**

- Il mittente può inviare solo pochi byte perché la finestra è piccola.
    
- Il ricevente accetta quei pochi byte, ma non riesce ad annunciare una finestra molto più grande finché non ne libera abbastanza.
    
- Si instaura un ciclo di piccoli scambi che mantiene **la rete occupata**, ma con **bassa efficienza**.
    

> In pratica, TCP continua a trasmettere segmenti minuscoli (1–2 byte), sprecando banda per le intestazioni e causando congestione.

---

### **6. Soluzioni alla Silly Window Syndrome**

Per evitare la sindrome, TCP applica strategie **sia sul lato ricevente** che **sul lato mittente**.

#### **a) Lato ricevente**

Il ricevente **non deve annunciare finestre troppo piccole**.  
Attende di poter annunciare un incremento “sufficientemente grande”, pari almeno al minimo tra:

$$  
\text{Incremento minimo} = \min(\text{MSS}, \frac{\text{Buffer}}{2})  
$$

dove:

- **MSS** = Maximum Segment Size (dimensione massima di un segmento TCP),
    
- **Buffer** = capacità totale del buffer di ricezione.
    

> Così il mittente potrà inviare segmenti “di dimensioni reali” anziché una raffica di pacchetti minuscoli.

---

#### **b) Lato mittente**

Il mittente **evita di inviare** dati finché non può spedire **una quantità significativa**.  
In particolare, trasmette solo quando può inviare almeno una delle seguenti quantità:

1. **Un intero segmento** della dimensione MSS;
    
2. **Almeno metà** della finestra più grande mai annunciata dal destinatario;
    
3. **Tutto il buffer disponibile.**
    

---

### **7. Risultato complessivo**

Queste regole permettono di:

- evitare l’invio di pacchetti piccoli e inefficaci;
    
- migliorare l’utilizzo della banda;
    
- e ridurre la probabilità di congestione o di rallentamenti.
    

> In sintesi, la sindrome “silly window” è un problema di _inefficienza_, non di _errore_: TCP non perde dati, ma spreca risorse.

---

### **8. Conclusione**

Il flusso TCP, nel caso di trasferimenti massivi, richiede una **gestione accurata della finestra di ricezione** per mantenere il flusso continuo ma controllato.  
I meccanismi che ne garantiscono la stabilità sono:

- **Advertised Window** → regola la velocità del mittente;
    
- **Window Update & Persist Timer** → prevengono blocchi della comunicazione;
    
- **Silly Window Avoidance** → mantiene l’efficienza del canale.
    

> Insieme, questi meccanismi assicurano che TCP possa adattarsi dinamicamente sia a connessioni lente e interattive, sia a trasferimenti di massa di grandi dimensioni, senza perdere l’equilibrio tra affidabilità e prestazioni.


---


![](imgs/Pasted%20image%2020251125070709.png)

![](imgs/Pasted%20image%2020251125070719.png)

![](imgs/Pasted%20image%2020251125070728.png)


---


![](imgs/Pasted%20image%2020251125070907.png)

![](imgs/Pasted%20image%2020251125070914.png)


---


![](imgs/Pasted%20image%2020251125070936.png)

![](imgs/Pasted%20image%2020251125071000.png)

![](imgs/Pasted%20image%2020251125071008.png)

