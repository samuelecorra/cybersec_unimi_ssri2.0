## **Lezione 2: Creazione di un socket**

### **1. Introduzione**

La **creazione di un socket** è il primo passo concreto nella **programmazione di rete**.  
Attraverso le socket, un programma può **aprire un canale di comunicazione** con un altro processo, locale o remoto, usando i protocolli **TCP** o **UDP**.

Il modello classico è quello **client/server**:

- Il **server** resta in ascolto su una **porta** specifica, in attesa di richieste.
    
- Il **client** invia una **richiesta di connessione** al server, indicando l’indirizzo IP e la porta.
    
- Quando la connessione è stabilita, i due processi possono **scambiarsi dati bidirezionali**.
    

---

### **2. Comunicazione socket – fase 1**

Immagina il socket come una **presa di rete virtuale** a cui il programma si collega per comunicare.  
Ogni server:

- gira su un computer specifico,
    
- apre un **socket legato a una porta**,
    
- e resta **in ascolto** finché un client non richiede la connessione.
    

```plaintext
CLIENT  →  Richiesta di connessione  →  SERVER (porta dedicata)
```

Solo quando la richiesta arriva, il server accetta il collegamento e stabilisce la sessione di comunicazione.

---

### **3. Comunicazione socket – fase 2**

Quando un client si connette, il server **non usa il socket originale** per comunicare.  
Invece:

1. Accetta la connessione.
    
2. Riceve dal sistema operativo **un nuovo socket**, legato a una **porta differente**.
    
3. Usa questo nuovo socket per dialogare con il client.
    

In questo modo:

- il **socket originale** resta libero per **nuove richieste** da altri client;
    
- il server può **gestire più connessioni simultanee**, una per ogni client, senza interrompere l’ascolto principale.
    

Ecco la logica grafica:

```plaintext
[Server socket principale] → Ascolta nuove connessioni
↓
[Socket dedicato al client 1] ↔ Comunicazione attiva
[Socket dedicato al client 2] ↔ Comunicazione attiva
...
```

---

### **4. Indirizzi, porte e socket – la metafora della posta**

Per capire meglio, pensa alla **posta tradizionale**:

|Elemento reale|Equivalente di rete|
|---|---|
|**Utente**|L’applicazione|
|**Indirizzo di casa**|L’indirizzo IP|
|**Casella postale**|La porta|
|**Ufficio postale**|La rete|
|**Chiave della casella**|Il socket|

Come la chiave permette all’utente di accedere alla propria casella postale, il **socket** consente al programma di accedere alla **porta giusta** della rete.

> Nota: si presume che anche la “posta in uscita” (i pacchetti inviati) venga inserita nella stessa casella, non imbucata altrove.  
> In altre parole, ogni socket gestisce sia l’invio che la ricezione di dati attraverso la propria porta.

---

### **5. La funzione `bind()`**

La chiamata di sistema **`bind()`** serve per **associare un socket a una porta specifica** del computer locale.

#### **Sintassi in C**

```c
int status = bind(sockid, &addrport, size);
```

#### **Parametri**

- `status` → valore di ritorno: `-1` se la funzione fallisce.
    
- `sockid` → identificatore intero del socket.
    
- `addrport` → struttura (`struct sockaddr`) che contiene:
    
    - **indirizzo IP locale**;
        
    - **numero di porta**.  
        Nella maggior parte dei casi, l’indirizzo è impostato a **`INADDR_ANY`**, così il sistema sceglie automaticamente l’indirizzo locale corretto.
        
- `size` → dimensione della struttura `addrport` in byte.
    

La `bind()` riserva la porta in modo **esclusivo**, impedendo ad altri processi di usarla contemporaneamente.

---

### **6. Quando usare `bind()`**

La necessità di chiamare `bind()` dipende dal **tipo di socket** (UDP o TCP).

#### **a. Socket UDP (`SOCK_DGRAM`)**

- Se il programma **invia solo dati**, `bind()` **non è necessaria**:  
    il sistema operativo sceglie automaticamente una porta libera per ogni invio.
    
- Se invece il programma **deve ricevere dati**, `bind()` è **obbligatoria**,  
    perché serve a specificare **su quale porta** il socket dovrà ascoltare.
    

#### **b. Socket TCP (`SOCK_STREAM`)**

- La destinazione è determinata durante la fase di **handshake a tre vie (three-way handshake)**, tipica del protocollo TCP.
    
- Il lato client **non ha bisogno** di conoscere o impostare la propria porta di invio; il sistema se ne occupa automaticamente.
    
- Il lato server, invece, deve **chiamare `bind()`** per associare la propria socket alla porta **su cui i client si collegheranno**.
    

---

### **7. Sintesi operativa**

|Ruolo|Protocollo|Quando serve `bind()`|Scopo|
|---|---|---|---|
|**Client UDP**|SOCK_DGRAM|Non serve|Lascia scegliere la porta al sistema|
|**Server UDP**|SOCK_DGRAM|Necessaria|Definisce la porta di ricezione|
|**Client TCP**|SOCK_STREAM|Non serve|La porta è assegnata automaticamente|
|**Server TCP**|SOCK_STREAM|Necessaria|Identifica la porta di ascolto|

---

### **8. Conclusione**

Creare un socket significa **stabilire un punto logico di contatto** tra un processo e la rete.  
La chiamata `bind()` gli assegna **un indirizzo e una porta**, permettendo al sistema operativo di instradare correttamente i pacchetti in ingresso e in uscita.

Capire bene questa fase è essenziale, perché è qui che **il codice del programmatore incontra l’infrastruttura TCP/IP**.  
È il momento in cui un programma locale diventa **parte attiva della rete globale**.