# **Lezione 5: Double DES**

---

### **1. Introduzione**

L’idea del **Double DES** nasce dal tentativo di **aumentare la sicurezza del DES** senza modificarne la struttura interna.  
Il principio è semplice: cifrare il messaggio **più volte**, utilizzando **chiavi diverse**, nella speranza che la sicurezza complessiva cresca proporzionalmente.

---

### **2. Struttura del Double DES**

La cifratura si ottiene applicando **due volte l’algoritmo DES**, con due chiavi distinte $k_1$ e $k_2$.

- **Lunghezza del blocco:** 64 bit
    
- **Chiave complessiva:** $(k_1, k_2)$ di lunghezza $56 + 56 = 112$ bit
    

#### **Cifratura**

$$  
y = DES_{k_2}(DES_{k_1}(x))  
$$

#### **Decifratura**

$$  
x = DES_{k_1}^{-1}(DES_{k_2}^{-1}(y))  
$$

---

### **3. È davvero più sicuro?**

Supponiamo che esista una chiave $k_3$ tale che:

$$  
DES_{k_3}(\cdot) = DES_{k_2}(DES_{k_1}(\cdot))  
$$

In tal caso, la doppia cifratura sarebbe **equivalente a una singola cifratura**, e il vantaggio di sicurezza sarebbe nullo.

Tuttavia, **DES non forma un gruppo** rispetto alla composizione di cifrature:

- Ogni chiave genera una **permutazione** sull’insieme dei $2^{64}$ blocchi possibili.
    
- Esistono $2^{56}$ permutazioni generate da DES, ma il numero totale di permutazioni possibili è:  
    $$  
    2^{64}! \gg 2^{56}  
    $$
![](imgs/Pasted%20image%2020260222120536.png)

- Quindi, la composizione di due cifrature DES **non appartiene** necessariamente all’insieme delle permutazioni realizzabili da una singola cifratura DES.
    

➡️ Conclusione:  
Il **Double DES non è equivalente** a un DES singolo, ma **non raddoppia realmente la sicurezza**.

---

### **4. Attacco “Meet in the Middle”**

L’attacco _Meet in the Middle_ sfrutta il fatto che la doppia cifratura può essere “incontrata” a metà, combinando una cifratura e una decifratura.

![](imgs/Pasted%20image%2020260222121716.png)
#### **Idea di base**

Dato un **testo in chiaro noto** $x$ e il corrispondente **testo cifrato** $y$:  
$$  
y = DES_{k_2}(DES_{k_1}(x))  
$$

1. **Fase 1 (cifratura):**  
    Si calcola e memorizza in una tabella:  
    $$  
    z = DES_{k_1}(x)  
    $$  
    per tutti i $2^{56}$ possibili valori di $k_1$.
    
2. **Fase 2 (decifratura):**  
    Si calcola:  
    $$  
    z' = DES^{-1}_{k_2}(y)  
    $$  
    per tutti i $2^{56}$ possibili valori di $k_2$.
    
3. **Fase 3 (incontro):**  
    Si cercano le corrispondenze tra i valori $z$ e $z'$.  
    Quando si trova una corrispondenza, si ottiene una coppia candidata $(k_1, k_2)$.
    

---

### **5. Pseudocodice dell’attacco**

```text
Input: x, y = DES_{k2}(DES_{k1}(x))

Costruisci tabella T:
  per ogni k1 ∈ {0,1}^56:
      z = DES_{k1}(x)
      memorizza (k1, z) in T

per ogni k2 ∈ {0,1}^56:
      z = DES^{-1}_{k2}(y)
      se esiste (k1, z) in T:
          restituisci (k1, k2)
```

![](imgs/Pasted%20image%2020260222121832.png)

---

### **6. Complessità dell’attacco**

- **Spazio:** $2^{56}$ voci nella tabella
    
- **Tempo:**
    
    - $2^{56}$ cifrature per costruire la tabella
        
    - $2^{56}$ decifrature per cercare la corrispondenza
        
    - $2^{56}$ ricerche (con complessità $O(1)$ se si usa una hash table)
        

👉 Complessità totale: **$O(2^{57})$** operazioni circa.  
Molto inferiore rispetto a **$O(2^{112})$**, atteso per un cifrario con chiave da 112 bit.

---

### **7. Numero di chiavi equivalenti**

Dato $x, y$, il numero medio di coppie $(k_1, k_2)$ tali che:

$$  
y = DES_{k_2}(DES_{k_1}(x))  
$$

è dato da:

$$  
\frac{2^{112}}{2^{64}} = 2^{48}  
$$

Quindi, in media, esistono **$2^{48}$ coppie di chiavi** che producono lo stesso risultato per una data coppia $(x, y)$.  
Questo riduce drasticamente la sicurezza effettiva.

---

### **8. Attacco migliorato**

Si considerano **due coppie note** $(x, y)$ e $(x', y')$ tali che:

$$  
y = DES_{k_2}(DES_{k_1}(x)), \quad  
y' = DES_{k_2}(DES_{k_1}(x'))  
$$

![](imgs/Pasted%20image%2020260222122142.png)

Ripetendo la procedura _Meet in the Middle_, ma verificando la corrispondenza per **entrambe le coppie**, la probabilità di individuare la chiave corretta cresce fino a:

$$  
1 - 2^{-16} \approx 0.9999847  
$$

Con due coppie, il numero medio di chiavi equivalenti si riduce a:

$$  
\frac{2^{112}}{2^{128}} = 2^{-16}  
$$

cioè **solo una chiave corretta su $2^{16}$ tentativi**.

---

### **9. Risultato finale e sicurezza**

|Metodo|Complessità|Sicurezza effettiva|
|---|---|---|
|Ricerca esaustiva su $(k_1, k_2)$|$2^{112}$|Ideale teorico|
|Attacco _Meet in the Middle_|$\approx 2^{56}$|Realistica|
|Double DES effettivo|$\approx$ DES singolo|56 bit di sicurezza|

💥 In pratica, **Double DES non offre vantaggi reali di sicurezza**:  
l’attacco _Meet in the Middle_ ne annulla quasi completamente i benefici.

---

### **10. Sintesi finale**

Abbiamo visto:

- Il principio del **Double DES** e il suo funzionamento.
    
- Il **problema dell’equivalenza parziale** delle permutazioni.
    
- L’attacco **Meet in the Middle**, che riduce la sicurezza effettiva a 56 bit.
    

➡️ **Conclusione:**  
Il Double DES **non è considerato sicuro**, e ha portato alla nascita del **Triple DES (3DES)**, studiato nella lezione successiva.