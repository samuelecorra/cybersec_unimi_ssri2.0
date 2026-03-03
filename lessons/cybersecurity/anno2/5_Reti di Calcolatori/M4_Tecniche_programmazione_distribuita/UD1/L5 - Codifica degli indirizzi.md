## **Lezione 5: Codifica degli indirizzi**

### **1. Introduzione**

Ogni volta che un programma di rete deve comunicare, deve **specificare chiaramente a chi** sta inviando i dati.  
Questa informazione — l’**indirizzo di destinazione** — deve essere **codificata in modo uniforme**, così che computer con **architetture e sistemi diversi** possano capirsi.

Per questo motivo, la **Socket Library** utilizza **strutture dati standard** per rappresentare **indirizzi e porte**, garantendo la compatibilità tra piattaforme e linguaggi.

---

### **2. La struttura generica `sockaddr`**

Il tipo base per rappresentare un indirizzo è la struttura **`sockaddr`**, definita così:

```c
struct sockaddr {
    u_short sa_family;   // tipo di famiglia di indirizzi
    char sa_data[14];    // dati binari dell’indirizzo
};
```

#### **Campi principali**

- **`sa_family`** → specifica quale **famiglia di indirizzi** viene utilizzata (es. IPv4, IPv6, UNIX domain, ecc.);
    
- **`sa_data`** → contiene l’indirizzo vero e proprio e il numero di porta, in formato binario.
    

Questa struttura è **generica**, e quindi non dipende da un protocollo specifico.  
Tuttavia, per le reti TCP/IP è necessario un tipo **più specializzato**.

---

### **3. La struttura `sockaddr_in` per IP**

Per le reti Internet basate su **IPv4**, si utilizza una struttura dedicata chiamata **`sockaddr_in`**, che estende e specializza la versione generica:

```c
struct sockaddr_in {
    short sin_family;      // tipo di famiglia: AF_INET
    u_short sin_port;      // numero di porta (0–65535)
    struct in_addr sin_addr; // indirizzo IP
    char sin_zero[8];      // campo inutilizzato
};
```

#### **Descrizione dei campi**

- **`sin_family`** → deve sempre valere **`AF_INET`**, che indica l’uso di indirizzi IPv4;
    
- **`sin_port`** → rappresenta la **porta** del processo con cui comunicare;
    
- **`sin_addr`** → contiene l’**indirizzo IP** del nodo remoto;
    
- **`sin_zero`** → è un campo di **padding** (riempimento) non utilizzato, usato solo per mantenere la struttura della giusta dimensione.
    

---

### **4. Ordinamento dei byte (Endianness)**

Uno dei problemi più importanti nella programmazione di rete è che **non tutti i computer rappresentano i numeri nello stesso modo in memoria**.  
Ogni macchina può avere un **ordine dei byte** diverso, e questo influisce su come vengono interpretati gli indirizzi e i numeri di porta.

#### **Tipi di ordinamento**

- **Little-endian** → il byte meno significativo viene memorizzato **per primo** (es. Intel x86).
    
- **Big-endian** → il byte più significativo viene memorizzato **per primo** (es. architetture Motorola, rete Internet).
    

Esempio (numero a 16 bit = 0x1234):

|Ordinamento|Rappresentazione in memoria|
|---|---|
|Little-endian|`34 12`|
|Big-endian|`12 34`|

---

### **5. Endianness dell’host e della rete**

In un sistema distribuito, ogni macchina ha il proprio **ordinamento dei byte locale (host order)**, ma la rete adotta uno **standard unico**:

|Tipo di ordinamento|Descrizione|
|---|---|
|**Host byte order**|Ordinamento usato internamente dal sistema locale (può essere big o little endian).|
|**Network byte order**|Ordinamento **universale di rete**, sempre **big-endian**.|

👉 **Tutti i dati numerici trasmessi sulla rete (come indirizzi IP o numeri di porta) devono essere convertiti nell’ordine dei byte di rete** prima della trasmissione, e riconvertiti dopo la ricezione.

---

### **6. Conversione tra formati: le funzioni UNIX**

Per uniformare l’ordine dei byte, la Socket Library fornisce quattro funzioni fondamentali che **traducono automaticamente** tra l’ordine dell’host e quello di rete.

|Funzione|Scopo|Conversione|
|---|---|---|
|`htonl(x)`|Host TO Network Long|converte un numero **a 32 bit** (es. indirizzi IP)|
|`htons(x)`|Host TO Network Short|converte un numero **a 16 bit** (es. porte)|
|`ntohl(x)`|Network TO Host Long|conversione inversa (32 bit)|
|`ntohs(x)`|Network TO Host Short|conversione inversa (16 bit)|

#### **Esempio**

```c
sin.sin_port = htons(80);
```

→ converte il numero di porta 80 dall’ordine locale al **network byte order** prima di inserirlo nella struttura `sockaddr_in`.

---

### **7. Dettagli di funzionamento**

- Su **macchine big-endian**, queste funzioni **non fanno nulla**, perché l’ordine dei byte dell’host coincide con quello della rete.
    
- Su **macchine little-endian**, invece, **invertono l’ordine dei byte** automaticamente, garantendo la compatibilità.
    

In questo modo, lo **stesso codice** funziona su **qualsiasi architettura**, senza dover conoscere il tipo di CPU o sistema operativo.

---

### **8. Conclusione**

Quando un programma crea o usa una socket, deve sempre ricordare che:

- ogni **porta** e **indirizzo IP** devono essere **memorizzati in formato binario**, non testuale;
    
- e devono essere **convertiti in ordine di rete** prima della trasmissione.
    

Le strutture `sockaddr` e `sockaddr_in`, unite alle funzioni `htonl()`, `htons()`, `ntohl()` e `ntohs()`, rendono possibile uno scambio di dati **universale e indipendente dall’hardware**.

> In breve: grazie alla codifica uniforme degli indirizzi, **Internet parla una lingua binaria comune**, comprensibile da ogni macchina, ovunque nel mondo.


---

