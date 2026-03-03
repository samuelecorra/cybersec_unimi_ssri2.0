## **Lezione 6: La chiamata `select()`**

### **1. Introduzione**

Fino ad ora abbiamo visto che molte funzioni della **Socket Library** — come `accept()`, `connect()`, `recv()`, `send()` — sono **bloccanti**.  
Questo significa che, quando vengono chiamate, **il programma si ferma** finché non si verifica un certo evento:

- `accept()` → attende l’arrivo di una richiesta di connessione.
    
- `connect()` → attende la conferma della connessione.
    
- `recv()` o `recvfrom()` → attendono l’arrivo di dati.
    
- `send()` o `sendto()` → attendono che i dati vengano copiati nel buffer del socket.
    

Per programmi semplici (come un singolo client o server con una sola connessione), questo comportamento è accettabile — anzi, comodo.  
Ma nei **programmi complessi**, dove bisogna gestire **più connessioni simultanee**, queste chiamate **diventano un problema**.

---

### **2. Il problema del blocco**

Immagina un **server che deve gestire molti client contemporaneamente**.  
Se il server usa chiamate bloccanti, resterà fermo in attesa di un client, **ignorando tutti gli altri**.

Due strategie possono risolvere questo limite:

1. **Programmazione multi-thread o multi-processo**  
    Ogni connessione viene gestita da un thread o processo separato.  
    È efficace ma costoso: più connessioni → più overhead di sistema.
    
2. **Chiamate non bloccanti**  
    Il programma continua a funzionare anche se una socket non è pronta, grazie a meccanismi di controllo periodico.  
    Tra questi, il più importante è proprio **`select()`**.
    

---

### **3. La chiamata `select()`**

La funzione **`select()`** permette a un programma di **controllare più socket contemporaneamente**, verificando **quali sono pronte** per essere lette o scritte.

È una chiamata estremamente utile nei server **multi-connessione**.

#### **Caratteristiche**

- Può essere:
    
    - **permanentemente bloccante**,
        
    - **bloccante per un tempo limitato**,
        
    - oppure **non bloccante del tutto**.
        
- In input riceve **una lista di socket (descrittori di file)** da monitorare.
    
- In output restituisce **informazioni sullo stato** di ciascuna socket: se è pronta per leggere, scrivere o se ha generato un’eccezione.
    

---

### **4. Struttura `fd_set`**

Per gestire più socket, `select()` utilizza una struttura speciale chiamata **`fd_set`**, cioè un insieme di **file descriptor**.

Un `fd_set` è, in sostanza, **un vettore di bit**:

- ogni bit rappresenta un descrittore di file (un socket);
    
- se il bit è impostato, `select()` controllerà quello specifico socket.
    

#### **Macro di gestione**

Prima di chiamare `select()`, il programmatore deve preparare il `fd_set`:

```c
FD_ZERO(&fdvar);   // Inizializza la struttura a vuota
FD_SET(i, &fdvar); // Aggiunge il socket i all’elenco
```

Dopo la chiamata a `select()`, si può verificare se un socket è pronto con:

```c
if (FD_ISSET(i, &fdvar)) {
    // Il socket i è pronto per l’operazione richiesta
}
```

---

### **5. Sintassi generale di `select()`**

```c
int status = select(nfds, &readfds, &writefds, &exceptfds, &timeout);
```

#### **Parametri**

|Parametro|Descrizione|
|---|---|
|`status`|Numero di socket “pronte”, oppure **-1** se c’è un errore.|
|`nfds`|1 + numero massimo di descrittori da controllare.|
|`readfds`|Elenco di socket da controllare per **lettura**.|
|`writefds`|Elenco di socket da controllare per **scrittura**.|
|`exceptfds`|Elenco di socket da controllare per **eventi eccezionali** (es. errori).|
|`timeout`|Tempo massimo di attesa (può essere 0, ∞, o puntare a `NULL`).|

---

### **6. Il parametro `timeout`**

Il parametro **`timeout`** controlla **quanto tempo `select()` resta in attesa**:

|Valore di `timeout`|Effetto|
|---|---|
|`NULL`|Attesa **infinita** (bloccante permanente).|
|Valore positivo|Attesa **limitata nel tempo** (es. 5 secondi).|
|`0`|Nessuna attesa (funzione **non bloccante**): ritorna subito.|

Questo parametro è estremamente utile per implementare **polling periodici** o **timeout personalizzati** nelle connessioni di rete.

---

### **7. Come funziona `select()` passo dopo passo**

1. Il programma prepara tre insiemi di socket (`readfds`, `writefds`, `exceptfds`).
    
2. Chiama `select()`, che controlla tutti i descrittori specificati.
    
3. `select()` si sospende fino a:
    
    - quando almeno uno dei socket è pronto,
        
    - oppure quando scade il `timeout`.
        
4. Alla fine, `select()` restituisce il numero di socket pronti.
    
5. Il programma controlla, con `FD_ISSET()`, **quali** socket sono pronti e agisce di conseguenza.
    

---

### **8. Esempio pratico**

Supponiamo di avere un server che ascolta su due socket contemporaneamente (ad esempio, una per comandi e una per dati).

```c
FD_ZERO(&readfds);
FD_SET(sock_cmd, &readfds);
FD_SET(sock_data, &readfds);

status = select(max(sock_cmd, sock_data) + 1, &readfds, NULL, NULL, &timeout);

if (status > 0) {
    if (FD_ISSET(sock_cmd, &readfds)) {
        // Dati pronti sul canale dei comandi
    }
    if (FD_ISSET(sock_data, &readfds)) {
        // Dati pronti sul canale dati
    }
}
```

In questo modo, il programma **non resta bloccato** su una singola connessione e può gestire **più client in parallelo** senza multithreading.

---

### **9. Vantaggi della `select()`**

|Vantaggio|Descrizione|
|---|---|
|**Efficienza**|Controlla molte connessioni contemporaneamente con una sola chiamata.|
|**Flessibilità**|Può essere bloccante, temporizzata o non bloccante.|
|**Semplicità**|Non richiede thread o processi multipli.|
|**Compatibilità**|Disponibile su tutti i sistemi UNIX e compatibili.|

---

### **10. Conclusione**

La chiamata **`select()`** è un potente strumento per **gestire comunicazioni multiple in modo non bloccante**.  
Permette a un singolo processo di controllare **molte connessioni** contemporaneamente, evitando la complessità del multithreading.

> In breve: `select()` è il **cuore della programmazione distribuita efficiente**, perché consente al programma di “ascoltare” su più canali senza mai fermarsi del tutto.