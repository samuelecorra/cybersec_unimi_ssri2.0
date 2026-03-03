Questo modulo conclusivo è dedicato alle **applicazioni pratiche della crittografia**, ossia a come i principi teorici visti finora vengono utilizzati per **scambiare chiavi in modo sicuro**, **condividere segreti tra più parti** e **proteggere le informazioni tramite tecniche visive**.

Si approfondiranno quindi tre ambiti fondamentali:

- lo **scambio di chiavi crittografiche**, necessario per stabilire comunicazioni riservate su canali insicuri;
    
- la **condivisione del segreto**, in cui un’informazione sensibile viene suddivisa tra più soggetti, ciascuno con una parte necessaria alla ricostruzione;
    
- la **crittografia visuale**, che applica principi matematici alla codifica di immagini o documenti visivi.
    

L’obiettivo del modulo è mostrare come la **teoria crittografica** diventi **strumento operativo** nei moderni sistemi di sicurezza, combinando rigore matematico e applicazioni concrete.

---

## **Lezione 1: Protocollo Diffie-Hellman**

### **1. Introduzione**

In questa lezione viene presentato il **protocollo di Diffie-Hellman** (1976), uno dei meccanismi fondamentali per lo **scambio sicuro di chiavi** su un canale insicuro.  
L’idea alla base è permettere a due utenti, **Alice** e **Bob**, di ottenere una **chiave segreta condivisa** senza mai trasmetterla direttamente, sfruttando le proprietà matematiche del **problema del logaritmo discreto**.

---

### **2. Accordo su chiavi**

Gli schemi principali che permettono di stabilire una chiave comune sono:

- **Diffie-Hellman**, basato sull’intrattabilità del **problema del logaritmo discreto**;
    
- **Puzzle di Merkle**, schema alternativo che non si fonda su ipotesi computazionali, ma su complessità di ricerca.
    

In questa lezione ci concentriamo sul primo, che costituisce la base teorica di molti protocolli moderni (come TLS, SSH, IPsec).

---

### **3. Il protocollo Diffie-Hellman**

Siano dati:

- un numero **primo $p$**,
    
- un **generatore $g$** del gruppo moltiplicativo $\mathbb{Z}_p^*$.
    

Si dice che $g$ è un generatore se:  
$$  
\{ \ g^i \mid 1 \le i \le p-1 \ \} = \mathbb{Z}_p^*  
$$

#### **Fase 1 — Scelta dei segreti**

- **Alice** sceglie un numero casuale $x \in \mathbb{Z}_p$
    
- **Bob** sceglie un numero casuale $y \in \mathbb{Z}_p$
    

#### **Fase 2 — Scambio dei valori pubblici**

- Alice calcola $A = g^x \bmod p$ e lo invia a Bob
    
- Bob calcola $B = g^y \bmod p$ e lo invia ad Alice
    

#### **Fase 3 — Calcolo della chiave condivisa**

Entrambi possono ora calcolare la stessa chiave segreta:

$$  
K = g^{xy} \bmod p  
$$

Infatti:

- Alice calcola $K = (B)^x \bmod p = (g^y)^x \bmod p = g^{xy} \bmod p$
    
- Bob calcola $K = (A)^y \bmod p = (g^x)^y \bmod p = g^{xy} \bmod p$
    

In questo modo, **Alice e Bob condividono la stessa chiave $K$**, senza mai averla trasmessa esplicitamente.

---

### **4. Esempio numerico**

#### **Esempio 1 (piccolo modulo)**

Siano:  
$$  
p = 11, \quad g = 2  
$$  
Alice sceglie $x = 3$  
Bob sceglie $y = 4$

Calcolo:

- $A = 2^3 \bmod 11 = 8$
    
- $B = 2^4 \bmod 11 = 5$
    

Scambio dei valori:

- Alice riceve $B = 5$ e calcola $K = 5^3 \bmod 11 = 4$
    
- Bob riceve $A = 8$ e calcola $K = 8^4 \bmod 11 = 4$
    

**Chiave condivisa: $K = 4$**

---

#### **Esempio 2 (modulo grande)**

Con:  
$$  
p = 25307, \quad g = 2, \quad x = 19956, \quad y = 3578  
$$

- $A = 2^{19956} \bmod 25307 = 6113$
    
- $B = 2^{3578} \bmod 25307 = 7984$
    

Alice calcola $K = 7984^{19956} \bmod 25307 = 3694$  
Bob calcola $K = 6113^{3578} \bmod 25307 = 3694$

**Chiave condivisa: $K = 3694$**

---

### **5. Sicurezza del protocollo**

Il problema su cui si basa Diffie-Hellman è il **calcolo della chiave segreta $K = g^{xy} \bmod p$**, dati solo:

- $p$,
    
- $g$,
    
- $g^x \bmod p$,
    
- $g^y \bmod p$.
    

Questa operazione è chiamata **problema di Diffie-Hellman**, e la sua difficoltà è strettamente legata al **problema del logaritmo discreto**:

$$  
x = \log_g (g^x \bmod p)  
$$

Anche se il logaritmo discreto è noto per essere difficile, non è ancora provato se la risoluzione del problema di Diffie-Hellman sia esattamente equivalente.

---

### **6. Attacco “Man in the Middle”**

Un punto debole del protocollo è la mancanza di autenticazione: un aggressore può **intercettare e sostituire** i valori scambiati.

Esempio:

1. Alice invia $g^x$, ma l’attaccante (Eve) lo sostituisce con $g^a$.
    
2. Bob invia $g^y$, ma Eve lo sostituisce con $g^b$.
    

Di conseguenza:

- Alice calcola $K = g^{xa}$
    
- Bob calcola $K = g^{yb}$
    
- Eve conosce **entrambe le chiavi**, potendo leggere e modificare i messaggi.
    

**Soluzione:** autenticare i valori scambiati (es. firme digitali o certificati).

---

### **7. Sintesi finale**

- Il **protocollo Diffie-Hellman** consente a due parti di stabilire una chiave comune su un canale insicuro.
    
- La **sicurezza** dipende dall’intrattabilità del **problema del logaritmo discreto**.
    
- È vulnerabile ad attacchi di tipo **Man in the Middle** se non è accompagnato da un meccanismo di **autenticazione**.
    
- Costituisce la base teorica di molti protocolli crittografici moderni per la **negoziazione sicura delle chiavi**.
