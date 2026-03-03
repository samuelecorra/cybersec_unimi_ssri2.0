Il Modulo  introduce una delle componenti fondamentali della sicurezza informatica moderna: le **funzioni di digest e autenticazione dei messaggi**.

In questo modulo vengono analizzate le **proprietà crittografiche** che rendono una funzione hash sicura (come la resistenza alle collisioni, la preimmagine e la seconda preimmagine) e le **principali applicazioni pratiche**, tra cui la **firma digitale**, la **verifica dell’integrità dei dati**, e i **Message Authentication Code (MAC)** utilizzati per garantire autenticità e integrità nelle comunicazioni.

Saranno inoltre studiati gli **algoritmi hash più noti** (come MD5, SHA-1, SHA-2, SHA-3 e HMAC), insieme ai **principali tipi di attacchi** (collision attacks, length extension, preimage attacks), e verranno fornite le basi per **valutare la robustezza** e la **sicurezza effettiva** di ciascuna funzione in contesti reali.

---

## **Lezione 1: Scenario e applicazioni**

### **1. Introduzione**

Le **funzioni hash** sono strumenti fondamentali della crittografia moderna.  
Esse permettono di rappresentare in modo compatto e sicuro un messaggio di **lunghezza arbitraria** attraverso un valore di **lunghezza fissa**, detto **digest**, **fingerprint** o semplicemente **hash**.

Formalmente, una funzione hash può essere descritta come:

$$  
h : \Sigma^* \rightarrow \Sigma^n  
$$

dove $\Sigma^*$ rappresenta l’insieme di tutti i messaggi di lunghezza arbitraria, mentre $\Sigma^n$ è l’insieme di tutte le sequenze di lunghezza fissa $n$ (tipicamente in bit).

L’**output** $h(M)$ è una rappresentazione **non ambigua e non falsificabile** del messaggio $M$.  
La funzione hash deve avere due proprietà fondamentali:

- **Comprimere**, cioè ridurre un messaggio lungo in un valore più corto.
    
- **Essere facile da calcolare**, per consentirne l’uso efficiente nei sistemi di sicurezza.
    

---

### **2. Applicazioni principali delle funzioni hash**

Le funzioni hash trovano applicazione in diversi ambiti della sicurezza informatica, in particolare in:

- **Firme digitali**
    
- **Verifica dell’integrità dei dati**
    
- **Certificazione temporale (timestamping)**
    

---

### **3. Firme digitali**

Un problema classico nella firma digitale è la **firma di messaggi molto lunghi**.

Una soluzione ingenua sarebbe dividere il messaggio in blocchi e **firmare ogni blocco singolarmente**.  
Tuttavia:

- il processo risulterebbe **lento**;
    
- la **sicurezza** sarebbe compromessa, poiché una combinazione o permutazione delle firme potrebbe generare una **nuova firma valida**.
    

La soluzione moderna consiste invece nel **firmare il valore hash del messaggio**:

$$  
\text{firma}(M) = F_k(h(M))  
$$

dove $F_k$ rappresenta l’algoritmo di firma con chiave privata $k$.

In questo modo si ottengono due vantaggi fondamentali:

- **Integrità dei dati**: la firma si riferisce in modo univoco al contenuto del messaggio.
    
- **Efficienza**: l’algoritmo di firma lavora su un digest di lunghezza fissa, non sull’intero messaggio.
    

---

### **4. Verifica dell’integrità dei dati**

L’uso più comune delle funzioni hash è la **verifica di integrità** dei file o dei messaggi.

Il procedimento tipico è il seguente:

1. Al tempo $T$ si calcola il valore hash del file $M$:  
    $$  
    H = h(M)  
    $$
    
2. Si conserva $H$ in un luogo sicuro.
    
3. In seguito, per controllare se il file è stato modificato, si ricalcola:  
    $$  
    h(M')  
    $$
    
4. Se $h(M') = H$, il file **non è stato alterato**.
    

Il valore $h(M)$ rappresenta quindi **l’impronta digitale** del file, e ogni minima modifica a $M$ produrrà un hash completamente diverso.

---

### **5. Collisioni e vulnerabilità**

Due messaggi $m_1$ e $m_2$ **collidono** se:

$$  
h(m_1) = h(m_2)  
$$

Poiché una funzione hash produce un output di lunghezza fissa a partire da un insieme di input potenzialmente infinito, **le collisioni sono inevitabili**.

Tuttavia, una funzione hash è considerata **sicura** se rende **computazionalmente impossibile** trovare due messaggi che collidono.  
In altre parole, anche se le collisioni esistono, nessun attaccante deve poterle individuare in tempi ragionevoli.

---

### **6. Esempio di attacco**

Un tipico attacco sfrutta la possibilità di creare due messaggi diversi con lo stesso hash.

Un avversario prepara:

- un messaggio $M$ favorevole ad **Alice**,
    
- e un messaggio $M'$ sfavorevole ad **Alice**.
    

Poi modifica $M'$ (aggiungendo spazi o piccole variazioni) fino a ottenere:

$$  
h(M) = h(M')  
$$

Quando Alice firma $M$ calcolando $\text{Firma}_{k_{priv}}(h(M))$,  
l’avversario può **riutilizzare la stessa firma** per $M'$, ottenendo $\text{Firma}_{k_{priv}}(h(M'))$.

Questo dimostra che la **sicurezza delle firme digitali** dipende fortemente dalla **resistenza alle collisioni** della funzione hash utilizzata.

---

### **7. Conclusioni**

- Le funzioni hash trasformano messaggi di lunghezza arbitraria in **digest di lunghezza fissa**.
    
- Esistono sempre input che producono collisioni, ma una buona funzione hash deve rendere **impraticabile** trovarli.
    
- Le loro applicazioni principali riguardano **firme digitali**, **integrità dei dati** e **sicurezza dei protocolli**.