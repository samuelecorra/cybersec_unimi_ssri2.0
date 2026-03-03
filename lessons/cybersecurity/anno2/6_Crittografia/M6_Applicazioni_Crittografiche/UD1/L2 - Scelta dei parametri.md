## **Lezione 2: Scelta dei parametri**

### **1. Introduzione**

Questa lezione spiega come scegliere correttamente i **parametri crittografici** del protocollo **Diffie-Hellman**, cioè il numero primo $p$ e il **generatore $g$** del gruppo moltiplicativo $\mathbb{Z}_p^*$.  
La sicurezza e l’efficienza dello scambio di chiavi dipendono strettamente da una scelta adeguata di questi valori.

---

### **2. Scelta di $p$ e $g$**

Per stabilire un gruppo $\mathbb{Z}_p^*$ adatto, bisogna:

- scegliere un **numero primo $p$** sufficientemente grande;
    
- trovare un **generatore $g$**, cioè un elemento tale che:  
    $$  
    \{ \ g^i \mid 1 \le i \le p - 1 \ \} = \mathbb{Z}_p^*  
    $$
    

Il metodo più diretto per scegliere un generatore è quello **naive**:

**Procedura Scegli_Generatore_Naive(p):**

1. Scegli casualmente $g \in \mathbb{Z}_p^*$
    
2. Se $\{ \ g^i \mid 1 \le i \le p - 1 \ \} = \mathbb{Z}_p^*$, termina
    
3. Altrimenti ripeti
    

Tuttavia, questo metodo è inefficiente, poiché richiede di verificare tutte le potenze di $g$.  
L’unico algoritmo pratico per determinare un generatore **richiede la fattorizzazione di $p - 1$**.

---

### **3. Condizione per essere generatore**

Sia $p$ un numero primo e sia:  
$$  
p - 1 = p_1^{e_1} p_2^{e_2} \dots p_k^{e_k}  
$$

Un elemento $g \in \mathbb{Z}_p^*$ è un **generatore** se e solo se:

$$  
g^{(p-1)/p_i} \not\equiv 1 \pmod p \quad \text{per ogni } i = 1, \dots, k  
$$

In altre parole, nessuna di queste potenze deve restituire 1 modulo $p$.

---

### **4. Esempi pratici**

#### **Esempio 1 – Generatore valido**

Sia $p = 11$, allora $p - 1 = 10 = 2 \cdot 5$.

Verifica per $g = 2$:  
$$  
2^{(11-1)/2} = 2^5 = 32 \equiv 10 \not\equiv 1 \pmod{11}  
$$  
$$  
2^{(11-1)/5} = 2^2 = 4 \not\equiv 1 \pmod{11}  
$$  
Poiché entrambe le condizioni sono soddisfatte, **$g = 2$ è un generatore di $\mathbb{Z}_{11}^*$**.

---

#### **Esempio 2 – Generatore non valido**

Stesso $p = 11$, prova con $g = 3$:  
$$  
3^{(11-1)/2} = 3^5 = 243 \equiv 1 \pmod{11}  
$$  
Poiché il risultato è 1, **$g = 3$ non è un generatore** di $\mathbb{Z}_{11}^*$.

---

### **5. Numero di generatori**

Un gruppo $\mathbb{Z}_n^*$ ammette un generatore **se e solo se**:  
$$  
n = 2, 4, p^k, \text{ oppure } 2p^k \quad \text{con } p \text{ primo e } k \ge 1  
$$

Nel caso di un numero primo $p$, $\mathbb{Z}_p^*$ ha sicuramente dei generatori.  
Il **numero di generatori** è dato da:  
$$  
\varphi(\varphi(p)) = \varphi(p - 1)  
$$  
dove $\varphi$ è la **funzione di Eulero**.

---

### **6. Procedura efficiente per trovare un generatore**

**Procedura Scegli_Generatore(p, (p₁, e₁, …, pₖ, eₖ)):**

1. Scegli casualmente $g \in \mathbb{Z}_p^*$.
    
2. Se per ogni fattore primo $p_i$ vale:  
    $$  
    g^{(p-1)/p_i} \not\equiv 1 \pmod p  
    $$  
    allora $g$ è un generatore.
    
3. Altrimenti ripeti.
    

---

### **7. Probabilità di successo**

Il numero di generatori modulo $p$ è:  
$$  
\varphi(\varphi(p)) > \frac{p - 1}{6 \ln \ln (p - 1)}  
$$

Pertanto, la **probabilità che un elemento casuale sia un generatore** è almeno:  
$$  
\frac{1}{6 \ln \ln (p - 1)}  
$$

Il **numero medio di iterazioni** necessarie per trovarne uno è:  
$$  
< 6 \ln \ln (p - 1)  
$$

Esempi:

- per $p$ di 512 bit → circa **35 iterazioni**;
    
- per $p$ di 1024 bit → circa **39 iterazioni**;
    
- per $p$ di 2048 bit → circa **44 iterazioni**.
    

---

### **8. Generazione delle chiavi Diffie-Hellman**

Un metodo alternativo e più efficiente per generare parametri validi:

1. Scegli due numeri primi $p_1$ e $p_2$;
    
2. Calcola:  
    $$  
    p = 1 + 2p_1p_2  
    $$
    
3. Se $p$ non è primo, ripeti;
    
4. Una volta ottenuto $p$, usa la procedura Scegli_Generatore$(p, (2,1,p_1,1,p_2,1))$
 per determinare un generatore $g$.
    

Questa tecnica produce **parametri robusti** per l’accordo di chiavi Diffie-Hellman.

---

### **9. Sintesi finale**

- La **sicurezza di Diffie-Hellman** dipende fortemente dalla **scelta corretta dei parametri**.
    
- Il numero di generatori è legato alla **fattorizzazione di $p - 1$**.
    
- Esiste una **procedura efficiente** per trovarli con probabilità elevata.
    
- Per garantire sicurezza, i valori di $p$ devono essere grandi (≥ 2048 bit) e i generatori scelti con cura per evitare sottogruppi deboli.