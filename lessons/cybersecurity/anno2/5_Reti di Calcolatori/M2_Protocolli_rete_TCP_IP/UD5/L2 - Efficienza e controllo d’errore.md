# **Lezione 2: Efficienza e controllo d’errore**

---

### **1. Introduzione**

Nella lezione precedente abbiamo studiato i meccanismi fondamentali di **controllo d’errore**, basati su:

- **somme di controllo (checksum)**, che consentono di rilevare gli errori;
    
- **ritrasmissione controllata**, che permette di correggere l’errore reinviando il pacchetto.
    

In questa lezione analizziamo come tali tecniche influenzino l’**efficienza complessiva del canale di comunicazione**.  
Infatti, garantire l’affidabilità significa introdurre **tempi di attesa e meccanismi di conferma**, che possono ridurre la quantità effettiva di dati trasmessi nell’unità di tempo.

---

### **2. Il protocollo Idle RQ (Stop & Wait)**

Il protocollo **Idle RQ (o Stop & Wait)** è il più semplice tra quelli di controllo d’errore:  
il mittente invia **un solo pacchetto** e aspetta l’**acknowledgment (ACK)** prima di trasmettere il successivo.

Questo garantisce:

- che **i pacchetti arrivino in ordine**;
    
- e che **tutti i dati siano confermati**.
    

Tuttavia, durante l’attesa dell’ACK il canale resta **inutilizzato**, e quindi la **banda disponibile non viene sfruttata appieno**.

---

### **3. Descrizione formale del protocollo**

#### **3.1 Mittente (M)**

Il mittente mantiene una variabile di stato $V(S)$ che indica il numero del **prossimo pacchetto da inviare**.  
Essa viene inizializzata a 0.

```text
while (true):
    1. Se disponibile, accetta un pacchetto dal livello superiore e assegnagli il numero V(S)
    2. Trasmetti il pacchetto V(S); incrementa V(S); avvia il timer
    3. Se ricevi un ACK corretto con numero V(R) = V(S) - 1:
         → Ferma il timer e torna al punto 1
    4. Se ricevi un ACK errato → ignora e resta in attesa
    5. Se ricevi un NAK → ritrasmetti V(S) - 1 e riavvia il timer
    6. Se scade il timer → ritrasmetti V(S) - 1 e riavvia il timer
```

---

#### **3.2 Ricevente (R)**

Il ricevente mantiene una variabile di stato $V(R)$ che indica il numero del **prossimo pacchetto atteso**.  
Essa viene inizializzata a 0.

```text
while (true):
    1. Se ricevi un pacchetto corretto:
         - Se il numero ricevuto coincide con V(R):
             → Invia ACK[V(R)]
             → Passa il pacchetto al livello superiore
             → Incrementa V(R)
         - Altrimenti:
             → Scarta il pacchetto duplicato
             → Invia comunque un ACK[V(S)] (ultimo valido)
    2. Se il pacchetto è corrotto:
         → Invia NAK[V(R)] (se la notifica esplicita è attiva)
```

---

### **4. Efficienza del protocollo**

L’**efficienza di utilizzo del canale** rappresenta la **frazione del tempo** in cui il canale è effettivamente impegnato nella trasmissione di dati utili.

Si definisce come:

$$  
U = \frac{T_{ix}}{T_t}  
$$

dove:

- $T_{ix}$ = tempo di trasmissione del frame (tempo impiegato per inviarlo);
    
- $T_t$ = tempo totale trascorso tra l’invio di un frame e quello successivo.
    

---

### **5. Calcolo dell’efficienza**

#### **5.1 Tempo totale $T_t$**

Durante la trasmissione di un frame, il mittente deve attendere:

- il tempo di propagazione **andata e ritorno**;
    
- il tempo necessario per ricevere e processare l’ACK.
    

Indichiamo con:

- $T_p$ → tempo di propagazione (one way);
    
- $T_{ix}$ → tempo di invio del pacchetto;
    
- $T_{ip}$, $T_{ap}$, $T_{ax}$ → tempi minori di elaborazione e trasmissione dell’ACK.
    

Approssimando (trascurando i termini di secondo ordine):

$$  
T_t = T_{ix} + 2T_p  
$$

---

#### **5.2 Formula generale dell’efficienza**

Sostituendo nella formula precedente:

$$  
U = \frac{T_{ix}}{T_{ix} + 2T_p}  
$$

Dividendo numeratore e denominatore per $T_{ix}$:

$$  
U = \frac{1}{1 + 2\frac{T_p}{T_{ix}}}  
$$

Ponendo:

$$  
\alpha = \frac{T_p}{T_{ix}}  
$$

Si ottiene la **formula finale dell’efficienza**:

$$  
U = \frac{1}{1 + 2\alpha}  
$$

---

### **6. Interpretazione fisica del parametro α**

Il parametro $\alpha$ rappresenta il **rapporto tra tempo di propagazione e tempo di trasmissione**.  
In pratica, misura quanto tempo il segnale impiega a **viaggiare nel canale** rispetto al tempo necessario per **trasmettere il pacchetto**.

- Se $\alpha$ è piccolo → il tempo di propagazione è trascurabile → **alta efficienza**.
    
- Se $\alpha$ è grande → la latenza domina sul tempo di trasmissione → **bassa efficienza**.
    

#### **Esempio**

Se il tempo di trasmissione di un frame è $T_{ix} = 1$ ms e il tempo di propagazione $T_p = 10$ ms:

$$  
\alpha = \frac{10}{1} = 10  
$$

$$  
U = \frac{1}{1 + 2(10)} = \frac{1}{21} \approx 0.0476  
$$

➡️ Solo il **4,8% del tempo** è usato per inviare dati effettivi: il resto è sprecato in attesa degli ACK.

---

### **7. Calcolo del tempo di trasmissione**

Il tempo di invio di un frame è dato da:

$$  
T_{ix} = \frac{\text{numero di bit nel frame}}{\text{bit rate (bps)}}  
$$

#### **Esempio**

Un frame di 10.000 bit trasmesso su un canale a 1 Mbps:

$$  
T_{ix} = \frac{10,000}{1,000,000} = 0.01,s = 10,ms  
$$

---

### **8. Conclusione**

Il protocollo **Stop & Wait (Idle RQ)** garantisce la **correttezza** ma **sfrutta male la banda disponibile**, poiché il mittente resta in attesa dell’ACK dopo ogni pacchetto.

L’efficienza del canale dipende fortemente dal rapporto $\alpha = T_p/T_{ix}$:

- piccole $\alpha$ → canale efficiente;
    
- grandi $\alpha$ → canale inefficiente.
    

Per migliorare l’utilizzo del canale, le reti reali adottano protocolli più complessi come **Go-Back-N** e **Selective Repeat**, che permettono di inviare più frame prima di ricevere le conferme.

![](imgs/Pasted%20image%2020251125065736.png)

