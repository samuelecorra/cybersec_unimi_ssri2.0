# **Lezione 1: Cifrature a blocchi – introduzione**

---

### **1. Concetto di cifratura a blocchi**

Nella **cifratura a blocchi**, il messaggio in chiaro viene suddiviso in **blocchi di $N$ bit**, e ciascun blocco viene trattato come un’**unità indipendente**.

Ogni blocco di testo in chiaro viene **trasformato** in un blocco cifrato della **stessa lunghezza**, tramite un algoritmo che utilizza una **chiave segreta** condivisa tra mittente e destinatario.

Rappresentazione schematica:

```
testo in chiaro  →  [ algoritmo + chiave ]  →  testo cifrato
```

Esempi di cifrature a blocchi:

- **DES (Data Encryption Standard)**
    
- **3DES (Triple DES)**
    
- **AES (Advanced Encryption Standard)**
    

---

### **2. Reversibilità della trasformazione**

Con blocchi di $n$ bit di testo in chiaro, si hanno $2^n$ possibili **input**.  
Affinché l’algoritmo sia **reversibile** (cioè permetta di decifrare correttamente):

- ogni blocco di testo in chiaro deve corrispondere a **un unico blocco cifrato**;
    
- la trasformazione deve essere **non singolare**, ossia **biunivoca** (ogni input ha un solo output e viceversa).

Esempio di trasformazione **corretta**:

|Testo chiaro|Testo cifrato|
|:-:|:-:|
|00|11|
|01|10|
|10|00|
|11|01|

Esempio di trasformazione **errata** (non reversibile, perché 10 e 11 producono lo stesso output):

|Testo chiaro|Testo cifrato|
|:-:|:-:|
|00|11|
|01|10|
|10|01|
|11|01|

Il numero totale di possibili trasformazioni **reversibili** per blocchi da $n$ bit è $2^n!$.

---

### **3. Trasformazioni generiche**

Per blocchi di $n = 4$ bit:

- Ci sono **16 possibili stati di input**.
    
- Ciascuno può essere mappato su **16 output** (anch’essi di 4 bit).
    
- Le trasformazioni reversibili possibili sono quindi **$16!$** (fattoriale di 16).

![](imgs/Pasted%20image%2020260221220603.png)

La **chiave** in una cifratura a blocchi di questo tipo è costituita **dalla colonna** che mostra, per ogni blocco in chiaro, il **blocco cifrato corrispondente**.

Esempio semplificato (schema):

![](imgs/Pasted%20image%2020260221220756.png)

La cifratura e la decifratura sono **speculari**: la tabella della cifratura rappresenta la **permutazione diretta**, quella della decifratura la **permutazione inversa**.

---

### **4. Dimensione del blocco e sicurezza**

- Con **blocchi piccoli**, la cifratura a blocchi si comporta come una **semplice sostituzione**, risultando vulnerabile agli **attacchi statistici**.
    
- Con **blocchi grandi**, si ottiene un effetto di **mascheramento** delle caratteristiche statistiche del testo, ma cresce la **dimensione della chiave**, creando problemi pratici.
    

La lunghezza della chiave per una cifratura generica di blocchi da $n$ bit è:

$$  
n \times 2^n  
$$

Esempi:

- Per $n = 4$, la chiave è lunga $64$ bit.
    
- Per $n = 64$, la chiave raggiunge circa $64 \times 2^{64} \approx 2^{70} \approx 10^{21}$ bit → **impraticabile**.
    

---

### **5. Proprietà dei cifrari moderni**

Un buon cifrario moderno deve soddisfare le seguenti proprietà:

1. **Ampio spazio delle chiavi** → per resistere agli **attacchi a forza bruta**.
    
2. **Trasparenza dell’algoritmo** → la conoscenza dell’algoritmo **non deve ridurre la sicurezza** (principio di Kerckhoffs).
    
3. **Robustezza uniforme** → ogni chiave deve produrre una cifratura sicura, senza “chiavi deboli”.
    
4. **Offuscamento totale** → eventuali ripetizioni o regolarità del testo in chiaro devono essere **completamente eliminate** nel testo cifrato.
    

---

### **6. Sintesi finale**

Abbiamo visto:

- Come funziona una **cifratura a blocchi** e perché la **reversibilità** è cruciale.
    
- Che i cifrari moderni cercano un **compromesso ottimale** tra **dimensione della chiave** e **robustezza** contro gli attacchi.