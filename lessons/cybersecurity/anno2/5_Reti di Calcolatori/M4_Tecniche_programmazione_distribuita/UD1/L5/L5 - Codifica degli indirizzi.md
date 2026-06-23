# **Lezione 5: Codifica degli indirizzi**

---

### **1. I quattro parametri che qualificano un socket**

Abbiamo visto nelle lezioni precedenti che per qualificare un socket occorrono **quattro parametri**:

$$(\text{IP mittente},\ \text{porta mittente},\ \text{IP destinatario},\ \text{porta destinatario})$$

Questi quattro parametri vengono passati alle chiamate di `bind()` e `connect()` sotto forma di una **struttura dati**. In alcuni casi sono tutti e quattro completamente definiti (quando è un client che esegue il binding o la connessione); in altri, quando è il server, sono definiti solo IP e porta lato server, lasciando **wildcard** per l'indirizzo e la porta del mittente.

---

### **2. La struttura generica `sockaddr`**

Il tipo base per rappresentare un indirizzo è la struttura `sockaddr`:

```c
struct sockaddr {
    u_short sa_family;   // tipo di famiglia di indirizzi
    char sa_data[14];    // dati binari dell'indirizzo
};
```

- **`sa_family`** → specifica quale famiglia di indirizzi viene utilizzata (es. `AF_INET` per IPv4).
- **`sa_data`** → contiene indirizzo e porta in formato binario.

Questa struttura è **generica**: le API della Socket Library la usano come tipo comune in `bind()`, `connect()`, `accept()`, `sendto()`, `recvfrom()`. In pratica si usa sempre la variante specializzata per IPv4 (`sockaddr_in`) e si esegue il cast al tipo generico.

---

### **3. La struttura `sockaddr_in` per IPv4**

Per le reti Internet IPv4 si usa la struttura specializzata `sockaddr_in`:

```c
struct sockaddr_in {
    short           sin_family;   // famiglia: AF_INET
    u_short         sin_port;     // numero di porta (16 bit)
    struct in_addr  sin_addr;     // indirizzo IP (struttura a 32 bit)
    char            sin_zero[8];  // padding inutilizzato
};
```

#### **Dettagli di ogni campo**

**`sin_family`** — deve sempre essere `AF_INET`. Questo valore specifica che si tratta di una struttura per socket di rete IPv4. Esistono varianti per altri tipi (es. `AF_UNIX` per socket intra-macchina, equivalenti alle pipe), ma noi non le trattiamo.

**`sin_port`** — rappresenta il numero di porta su 16 bit senza segno (`u_short`). I numeri di porta vanno da 0 a 65535, esattamente lo spazio disponibile nei 16 bit dell'header TCP e UDP. Non occorre più spazio.

**`sin_addr`** — l'indirizzo IP è a sua volta una **struttura** (`struct in_addr`), non una semplice stringa. Per riempirlo correttamente occorre inizializzare i campi della struttura — non è sufficiente copiare la stringa dotted-decimal: va usata la funzione `inet_addr()` (o simili) che converte la stringa nel valore binario a 32 bit atteso dalla struttura.

**`sin_zero[8]`** — otto byte di padding non utilizzati, presenti solo per mantenere `sockaddr_in` della stessa dimensione di `sockaddr` e consentire il cast tra i due tipi.

---

### **4. Il problema dell'ordinamento dei byte (Endianness)**

Un problema rilevante al momento di inizializzare queste strutture è che **macchine diverse memorizzano gli interi in modo diverso**.

Porta e indirizzo IP sono interi, rispettivamente a 16 e 32 bit. Esistono due convenzioni:

| Ordinamento | Nome | Descrizione |
|---|---|---|
| **Little-endian** | — | Il byte **meno** significativo viene memorizzato **per primo** (es. Intel x86/x64) |
| **Big-endian** | — | Il byte **più** significativo viene memorizzato **per primo** (es. Motorola, SPARC) |

#### **Esempio concreto**

![](imgs/Pasted%20image%2020260623154414.png)

Si vuole comunicare l'indirizzo IP `128.119.40.12` da una macchina **big-endian** a una macchina **little-endian**. In memoria big-endian i byte sono nell'ordine `128 | 119 | 40 | 12`. La macchina little-endian li legge dall'ordine opposto: `12 | 40 | 119 | 128`, ottenendo l'indirizzo `12.40.119.128` — un indirizzo di Classe A completamente diverso da quello originale. La connessione andrebbe verso la macchina sbagliata.

> ⚠️ Questo dimostra perché non si può passare un valore intero "così com'è" da una macchina all'altra senza accordarsi sul formato.

---

### **5. Il formato standard di rete: sempre big-endian**

Per risolvere il problema, si distingue tra:

| Tipo | Descrizione |
|---|---|
| **Host byte order** | L'ordinamento usato internamente dalla macchina (può essere big o little-endian) |
| **Network byte order** | L'ordinamento **universale di rete**: sempre **big-endian** |

**È nostra responsabilità**, quando inizializziamo una struttura `sockaddr_in`, scrivere i valori di porta e indirizzo IP nel formato **network byte order (big-endian)**. Questo garantisce che qualunque macchina riceva la struttura la interpreti sempre nello stesso modo.

Se la macchina destinataria usa little-endian, sarà compito suo riconvertire il valore dal network byte order al proprio formato interno prima di usarlo.

---

### **6. Le funzioni di conversione della Socket Library**

Per eseguire queste conversioni, la Socket Library mette a disposizione **quattro funzioni standard**:

| Funzione | Significato | Conversione |
|---|---|---|
| `htonl(x)` | **H**ost **T**o **N**etwork **L**ong | 32 bit: host → network (es. indirizzi IP) |
| `htons(x)` | **H**ost **T**o **N**etwork **S**hort | 16 bit: host → network (es. numeri di porta) |
| `ntohl(x)` | **N**etwork **T**o **H**ost **L**ong | 32 bit: network → host |
| `ntohs(x)` | **N**etwork **T**o **H**ost **S**hort | 16 bit: network → host |

**Esempio d'uso:**

```c
echoServAddr.sin_port        = htons(80);           // porta → network byte order
echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);   // IP → network byte order
```

![](imgs/Pasted%20image%2020260623154515.png)

#### **Comportamento su macchine big-endian**

Se la macchina è già big-endian, l'ordinamento del host coincide con quello di rete: le funzioni `hton*` e `ntoh*` **non fanno nulla** (sono implementate come no-op). Su macchine little-endian invertono invece l'ordine dei byte automaticamente.

> 📌 Il codice che usa queste funzioni è **portabile**: compila e funziona correttamente su qualsiasi architettura, senza dover sapere se la macchina su cui gira è big o little-endian.

---

### **7. Conclusione**

Quando si inizializza una struttura `sockaddr_in` per eseguire `bind()`, `connect()`, `sendto()` o `recvfrom()`:

1. Si impostano i campi con i valori corretti (famiglia, porta, indirizzo IP).
2. Si applicano `htons()` ai valori a 16 bit (porte) e `htonl()` o `inet_addr()` ai valori a 32 bit (indirizzi IP).
3. Si passa la struttura — con cast a `(struct sockaddr *)` — alla chiamata di socket appropriata.

Le funzioni `hton*` / `ntoh*` sono il primo esempio della necessità di definire **formati indipendenti dalla singola macchina** per lo scambio di informazioni in rete — un principio che ritornerà nelle ultime lezioni del corso quando si parlerà di paradigmi di programmazione distribuita.
