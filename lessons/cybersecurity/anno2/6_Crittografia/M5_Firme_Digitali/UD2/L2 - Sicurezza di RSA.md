## **Lezione 2: Sicurezza di RSA**

### **1. Introduzione**

Questa lezione approfondisce la **sicurezza dello schema di firma RSA**, analizzando i principali **attacchi** a cui può essere soggetto.  
Poiché la firma RSA utilizza la stessa struttura matematica della cifratura RSA, **falsificare una firma** equivale di fatto a **rompere il crittosistema RSA**.

---

### **2. Attacchi allo schema RSA**

#### **2.1 Selective forgery – Key-only attack**

L’obiettivo dell’attaccante è falsificare la firma di un messaggio $M$ a nome di Alice.  
Per riuscirci, dovrebbe calcolare:

$$  
F = M^d \bmod n  
$$

Il che è equivalente a conoscere la chiave privata $d$.  
In pratica, quindi, riuscire a produrre una firma corretta senza conoscere $d$ equivale a **rompere RSA**.

---

#### **2.2 Existential forgery – Key-only attack**

L’attaccante non mira a firmare un messaggio specifico, ma a **produrre qualsiasi coppia valida** $(M, F)$ tale che:

$$  
F^e \bmod n = M  
$$

Il metodo è semplice:

1. Sceglie un valore casuale $F$.
    
2. Calcola $M = F^e \bmod n$.
    

La coppia $(M, F)$ così ottenuta risulta **formalmente valida**, anche se priva di significato pratico.  
Questo dimostra che lo **schema RSA “textbook” è vulnerabile** senza l’uso di hash o padding.

---

#### **2.3 Existential forgery – Known message attack**

Se l’attaccante conosce alcune coppie firmate $(M_1, F_1)$ e $(M_2, F_2)$, può sfruttare la **proprietà di omomorfismo** di RSA:

$$  
F_1 = M_1^d \bmod n, \quad F_2 = M_2^d \bmod n  
$$

Allora:

$$  
(F_1 F_2)^e \bmod n = F_1^e F_2^e \bmod n = M_1 M_2 \bmod n  
$$

Ne segue che:

$$  
F_1 F_2 \bmod n  
$$

è una **firma valida per il messaggio $M_1 M_2 \bmod n$**.  
L’attaccante può quindi costruire nuove firme combinando quelle già note: un chiaro esempio di **vulnerabilità strutturale**.

---

#### **2.4 Selective forgery – Chosen message attack**

In questo scenario, l’attaccante può **scegliere dei messaggi da far firmare** al legittimo utente (Alice).

Procedura tipica:

1. Sceglie $M_1$ e $M_2$ tali che $M = M_1 M_2 \bmod n$.
    
2. Chiede ad Alice di firmare $M_1$ e $M_2$, ottenendo $F_1$ e $F_2$.
    
3. Calcola $F = F_1 F_2 \bmod n$, che risulta una **firma valida per $M$**.
    

Questo tipo di attacco sfrutta **direttamente l’omomorfismo moltiplicativo** di RSA.

---

### **3. Sicurezza della firma RSA con hash**

Per mitigare gli attacchi precedenti, lo schema RSA viene migliorato firmando **l’hash del messaggio**, e non il messaggio stesso:

$$  
F = [h(M)]^d \bmod n  
$$

Tuttavia, anche in questo caso **la sicurezza dipende dalla funzione hash**.

#### **3.1 Existential forgery – Key-only attack con hash**

Procedura:

1. L’attaccante sceglie $F$ casualmente.
    
2. Calcola $z = F^e \bmod n$.
    
3. Se riuscisse a trovare un $M$ tale che $h(M) = z$, avrebbe generato una firma valida.
    

Per riuscirci, dovrebbe però **invertire la funzione hash**, cioè calcolare:

$$  
M = h^{-1}(z)  
$$

Se l’hash è robusto (non invertibile), l’attacco fallisce.

---

#### **3.2 Existential forgery – Chosen message attack con hash**

L’attaccante sceglie due messaggi $M_1$ e $M_2$ tali che:

$$  
h(M_1) = h(M_2)  
$$

Dopo aver ottenuto da Alice la firma $F_1$ di $M_1$, può riutilizzare $F_1$ come firma di $M_2$.  
Poiché gli hash coincidono, la verifica risulterà positiva anche per $M_2$.  
Questo è un **attacco basato su collisione dell’hash**.

---

### **4. Sintesi finale**

- Gli **attacchi RSA** sfruttano la **struttura matematica omomorfica** dell’algoritmo e la **mancanza di protezioni** come hashing o padding.
    
- Lo schema di base (“textbook RSA”) è vulnerabile a **existential** e **selective forgery**.
    
- L’uso di un **hash sicuro** è indispensabile per garantire integrità e autenticità.
    
- La **sicurezza complessiva** di una firma RSA dipende non solo dalla forza di RSA stesso, ma anche dalle **proprietà crittografiche della funzione hash** utilizzata.