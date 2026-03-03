# **M1 UD2 Lezione 1 - Il principio di moltiplicazione**

### **1. Introduzione**

Quando si affrontano esperimenti composti — cioè esperimenti formati da più scelte successive — la probabilità di un evento dipende dal **numero di combinazioni possibili**.  
Per contare queste combinazioni si usa una regola fondamentale del **calcolo combinatorio**: il **principio di moltiplicazione**.

Questo principio è alla base della **probabilità condizionata** e della **legge del prodotto**, che verranno formalizzate nelle prossime lezioni.

---

### **2. Probabilità e conteggio**

La definizione insiemistica di probabilità si basa sul rapporto:

$$  
P(E) = \frac{\text{casi favorevoli}}{\text{casi possibili}}  
$$

Spesso, quindi, **calcolare una probabilità significa contare** gli elementi di un insieme.  
In casi semplici si può contare direttamente; in casi più complessi è utile ricorrere a strumenti combinatori.

Due strumenti fondamentali sono:

- le **tabelle** (per visualizzare tutte le combinazioni possibili);
    
- i **diagrammi ad albero** (per rappresentare in modo gerarchico le scelte successive).

---

### **3. Esempio introduttivo**

**Problema:** In quanti modi posso combinare **2 piatti** con **2 vini**?

- Piatti: ${ \text{Carne}, \text{Pesce} }$
    
- Vini: ${ \text{Rosso}, \text{Bianco} }$

Ogni piatto può essere associato a ciascun vino:

1. (Carne, Rosso)
    
2. (Carne, Bianco)
    
3. (Pesce, Rosso)
    
4. (Pesce, Bianco)

Totale combinazioni: $2 \times 2 = 4$

---

### **4. Rappresentazione a tabella**

| |Rosso|Bianco|
|---|---|---|
|Carne|(Carne, Rosso)|(Carne, Bianco)|
|Pesce|(Pesce, Rosso)|(Pesce, Bianco)|

Il numero di celle totali (2 righe × 2 colonne) è 4, confermando il risultato.

---

### **5. Rappresentazione ad albero**

![](imgs/Pasted%20image%2020251216122744.png)
Ogni ramo rappresenta una possibile combinazione.

---

### **6. Esempio con più scelte**

**Problema:** In quanti modi posso combinare **2 piatti** con **3 vini**?

- Piatti: ${ \text{Carne}, \text{Pesce} }$
    
- Vini: ${ \text{Rosso}, \text{Bianco}, \text{Rosato} }$

Totale combinazioni: $2 \times 3 = 6$

Le scelte possibili sono:  
(Car, Rosso), (Car, Bianco), (Car, Rosato), (Pes, Rosso), (Pes, Bianco), (Pes, Rosato)

---

### **7. Enunciato del principio di moltiplicazione**

Se:

- un primo insieme contiene $i$ elementi,
    
- un secondo insieme contiene $j$ elementi,

allora il numero totale di combinazioni (scegliendo un elemento dal primo e uno dal secondo) è:

$$  
N = i \times j  
$$

---

### **8. Estensione a tre insiemi**

Se consideriamo **tre** insiemi:

- il primo con $i$ elementi,
    
- il secondo con $j$ elementi,
    
- il terzo con $k$ elementi,

il numero totale di combinazioni sarà:

$$  
N = i \times j \times k  
$$

---

### **9. Esempio – Menu completo**

Disponiamo di:

- 2 piatti principali,
    
- 3 vini,
    
- 2 contorni.

Il numero di scelte possibili è:

$$  
N = 2 \times 3 \times 2 = 12  
$$

---

### **10. Principio di moltiplicazione in generale**

Se abbiamo $n$ insiemi distinti, contenenti rispettivamente $k_1, k_2, \dots, k_n$ elementi, il numero di combinazioni possibili è:

$$  
N_{comb} = k_1 \times k_2 \times \dots \times k_n  
$$

Questo principio è universale: si applica a qualunque esperimento formato da scelte successive ==***indipendenti***==.

---

### **11. Esempio – Targhe automobilistiche**

**Problema:** Quante targhe come `AA 000 AA` si possono formare?

Ogni targa è composta da:

- 2 lettere maiuscole ($26$ possibilità ciascuna),
    
- 3 cifre ($10$ possibilità ciascuna),
    
- altre 2 lettere ($26$ possibilità ciascuna).

Il numero totale di targhe possibili è:

$$  
N = 26 \times 26 \times 10 \times 10 \times 10 \times 26 \times 26  
$$

$$  
N = 676 \times 1000 \times 676 = 456{,}976{,}000  
$$

---

### **12. Coppie, terne e n-tuple ordinate**

Gli **esperimenti compositi** generano risultati che non sono più singoli elementi, ma **n-uple ordinate** di valori.

- Una **coppia ordinata** $(x, y)$ è diversa da $(y, x)$
    
- Una **terna ordinata** $(x, y, z)$ è diversa da $(y, x, z)$

In generale, una **n-upla ordinata** $(x_1, x_2, \dots, x_n)$ rappresenta una sequenza ordinata di scelte.  
Il principio di moltiplicazione permette di calcolare quante n-uple distinte si possono formare.

---

### **13. Riepilogo concettuale**

|Concetto|Descrizione|Formula|
|---|---|---|
|**Principio base**|Se ho $i$ elementi nel primo insieme e $j$ nel secondo|$N = i \times j$|
|**Tre insiemi**|Combinazione di tre categorie|$N = i \times j \times k$|
|**Generale**|Combinazione di $n$ categorie|$N = k_1 \times k_2 \times \dots \times k_n$|
|**Applicazione**|Esperimenti composti (monete, dadi, lettere, cifre)|Conteggio rapido delle possibilità|

---

### **14. Conclusione**

Il **principio di moltiplicazione** è la regola cardine per calcolare il numero di risultati possibili in esperimenti composti.  
Permette di passare dal semplice conteggio manuale a una **formula generale** valida per qualunque combinazione di scelte.

Nelle prossime lezioni verrà collegato direttamente alla **probabilità condizionata** e alla **legge del prodotto**, dove il conteggio si trasforma in calcolo di probabilità.