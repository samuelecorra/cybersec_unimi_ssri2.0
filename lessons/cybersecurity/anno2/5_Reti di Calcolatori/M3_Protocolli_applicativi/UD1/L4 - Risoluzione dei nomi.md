# **Lezione 4: Risoluzione dei nomi**

---

### **1. Il processo di risoluzione**

Quando un’applicazione (ad esempio un browser) deve connettersi a un sito web, come `www.ripe.net`, non conosce il relativo indirizzo IP.
Per ottenerlo, si affida al **DNS**, attraverso un meccanismo chiamato **risoluzione dei nomi** (_name resolution_).

Il processo può essere riassunto così:

1. L’applicazione chiede al **resolver locale** (spesso integrato nel sistema operativo) di trovare l’indirizzo IP associato al nome `www.ripe.net`.
    
2. Il resolver invia una richiesta al **DNS server locale** configurato nella rete (spesso il router o il provider).
    
3. Se il server non conosce la risposta, **consulta un root server** per sapere quale **server di livello superiore** può aiutarlo.
    
4. Il root server risponde indicando il **server TLD** (Top-Level Domain) appropriato, in questo caso `.net`.
    
5. Il server TLD indica il **name server autorevole** del dominio `ripe.net`.
    
6. Quest’ultimo restituisce l’**indirizzo IP** richiesto.
    
7. Il resolver **memorizza la risposta in cache** per usi futuri.

![](imgs/Pasted%20image%2020260225160526.png)

Questo meccanismo sfrutta due strumenti chiave:

- **Caching**, per velocizzare le risposte successive;
    
- **Forwarder**, un server DNS intermedio che può inoltrare richieste ad altri server in modo **ricorsivo**.
    

---

### **2. Il resolver**

I **resolver** sono programmi che **interrogano il DNS per conto delle applicazioni**.  
Ogni volta che un’applicazione deve convertire un nome in indirizzo (o viceversa), il resolver esegue la richiesta DNS necessaria.

Sono normalmente implementati come **librerie di sistema**.  
Esempi in linguaggio C:

```c
gethostbyname(char *name);
gethostbyaddr(char *addr, int len, int type);
```

Queste funzioni vengono utilizzate per ottenere rispettivamente l’indirizzo IP a partire da un nome, o il nome a partire da un indirizzo.

---

### **3. Resource Record (RR)**

Il DNS traduce i nomi in dati utilizzando delle **unità di informazione** chiamate **Resource Record (RR)**.  
Ogni record descrive una specifica informazione associata a un nome di dominio.

![](imgs/Pasted%20image%2020260225160607.png)

**Esempio:**

```
www.ripe.net.   IN   A   10.10.10.2
```

In questo caso:

- `www.ripe.net.` è l’**etichetta**;
    
- `IN` indica la **classe** (Internet);
    
- `A` è il **tipo** di record (address record);
    
- `10.10.10.2` è il **dato** (RDATA), ossia l’indirizzo IP associato.
    
![](imgs/Pasted%20image%2020260225160722.png)

---

### **4. Struttura di un Resource Record**

Ogni RR è composto da cinque elementi principali:

| Campo                  | Significato                                                                 |
| :--------------------- | :-------------------------------------------------------------------------- |
| **Etichetta**          | Il nome del dominio o host                                                  |
| **TTL (Time To Live)** | Tempo di validità del record in cache                                       |
| **Classe**             | Identifica il tipo di rete (la più comune è `IN` per Internet)              |
| **Tipo**               | Indica la natura dell’informazione (es. `A`, `MX`, `NS`, `CNAME`, `SOA`...) |
| **RDATA**              | Il dato vero e proprio, che può essere un indirizzo, un nome, o altro       |

Esempio completo:

```
www.ripe.net.   3600   IN   A   10.10.10.2
```

---

### **5. Esempio di file di zona**

Un **file di zona** contiene tutti i Resource Record di una determinata zona DNS.  
Ecco un esempio semplificato:

```
ripe.net. 7200 IN SOA ns.ripe.net. olaf.ripe.net. (
    2001061501 ; Serial
    43200      ; Refresh (12 ore)
    14400      ; Retry (4 ore)
    345600     ; Expire (4 giorni)
    7200       ; Negative cache (2 ore)
)

ripe.net.       7200 IN NS ns.ripe.net.
ripe.net.       7200 IN NS ns.eu.net.
pinkie.ripe.net. 3600 IN A 193.0.1.162
host25.ripe.net. 2600 IN A 193.0.3.25
```

![](imgs/Pasted%20image%2020260225160744.png)

Questo file descrive:

- i parametri principali della zona (`SOA`);
    
- i server autorevoli (`NS`);
    
- gli host e i relativi indirizzi (`A`).
    

---

### **6. Resource Record di tipo NS**

I **record NS (Name Server)** indicano **quali server sono responsabili** di una certa zona DNS.  
Sono usati per informare il sistema su **dove trovare i dati ufficiali** di un dominio.

Esempio:

```
ripe.net. 7200 IN NS ns.ripe.net.
ripe.net. 7200 IN NS ns.eu.net.
```

Ciò significa che `ns.ripe.net` e `ns.eu.net` sono i server autorevoli per la zona `ripe.net`.

---

### **7. Resource Record di tipo SOA**

Il record **SOA (Start of Authority)** fornisce informazioni sull’**origine dell’autorità** per una zona.  
Contiene i parametri di sincronizzazione e i riferimenti al server master.

![](imgs/Pasted%20image%2020260225160828.png)

Esempio:

```
net. 3600 IN SOA a.gtld-servers.net. nstld.verisign-grs.com. (
    2002021301 ; Serial (numero di versione)
    300M       ; Refresh
    15M        ; Retry
    1W         ; Expire
    1D         ; Negative answer TTL
)
```

Spiegazione dei parametri:

- **Serial** → numero di versione del file di zona (incrementato a ogni modifica);
    
- **Refresh** → intervallo tra due sincronizzazioni master–slave;
    
- **Retry** → tempo d’attesa prima di ritentare una sincronizzazione fallita;
    
- **Expire** → tempo massimo oltre il quale la copia slave non è più valida;
    
- **Negative answer TTL** → durata delle risposte negative (es. “nome inesistente”).
    

---

### **8. Il significato del TTL**

Il **TTL (Time To Live)** è un timer fondamentale per la **gestione delle cache DNS**:

- indica per **quanto tempo** una risposta può essere riutilizzata;
    
- i record più **stabili** (come i domini principali) possono avere TTL **più lunghi**;
    
- quelli soggetti a cambiamenti frequenti (come indirizzi dinamici) hanno TTL **più brevi**.
    

Grazie al TTL, il DNS trova un equilibrio tra **efficienza** (cache duratura) e **aggiornamento rapido** dei dati.

---

### **9. Propagazione dei dati**

Quando si aggiorna una zona DNS (ad esempio, cambiando un indirizzo IP), le modifiche **non si propagano immediatamente**.  
Occorre attendere che:

![](imgs/Pasted%20image%2020260225160934.png)

- il **TTL scada** nelle cache dei resolver;
    
- e che i **server secondari** sincronizzino la nuova versione del file di zona.
    

Questo spiega perché i cambiamenti DNS possono richiedere **diverse ore** o anche **giorni** per diventare effettivi in tutto il mondo.

---

### **10. DNS e prestazioni**

Per mantenere alte le prestazioni, il DNS sfrutta tre strategie principali:

1. **Server autorevoli multipli**  
    – più copie della stessa zona, distribuite in luoghi geografici differenti, riducono il rischio di guasti e bilanciano il carico.
    
2. **Caching diffuso**  
    – memorizzare le risposte nelle cache dei resolver riduce sia i tempi di risposta sia il carico sui server principali.
    
3. **Parametri ottimizzati**  
    – i timer SOA e i valori TTL devono essere **calibrati** in base alle esigenze della zona:
    
    - valori **alti** per domini stabili;
        
    - valori **bassi** per domini dinamici.
        

---

### **11. Conclusione**

La **risoluzione dei nomi** è il cuore operativo del DNS:  
trasforma i nomi simbolici in indirizzi IP reali, grazie a un processo distribuito basato su **resolver, caching e server autorevoli**.  
Il suo successo si deve a una progettazione bilanciata tra **efficienza**, **affidabilità** e **aggiornabilità continua**.