
## **Lezione 1: Schema RSA**

### **1. Introduzione**

In questa lezione viene illustrato lo **schema di firma digitale RSA**, basato sugli stessi principi matematici dell’algoritmo RSA di cifratura.  
Lo scopo è comprendere come si generano le chiavi, come si crea una firma e come si verifica la sua autenticità.

---

### **2. Generazione delle chiavi RSA**

Ogni utente, ad esempio **Alice**, possiede una coppia di chiavi:

- **Chiave pubblica:** $(n, e)$
    
- **Chiave privata:** $(n, d)$
    

La chiave pubblica viene resa disponibile a tutti, mentre la chiave privata deve essere mantenuta segreta.

Le chiavi vengono generate nel modo seguente:

$$  
n = p \cdot q  
$$

dove $p$ e $q$ sono **numeri primi**.  
Il valore di $d$ viene scelto in modo che:

$$  
e \cdot d \equiv 1 \pmod{(p-1)(q-1)}  
$$

---

### **3. Firma RSA**

Per firmare un messaggio $M$, il firmatario (Alice) usa la propria chiave privata secondo la formula:

$$  
F \leftarrow M^d \bmod n  
$$

Il risultato $F$ è la **firma digitale** associata a $M$.

La coppia $(M, F)$ rappresenta quindi il messaggio e la sua firma, verificabile da chiunque conosca la chiave pubblica di Alice.

---

### **4. Verifica della firma RSA**

Il destinatario (ad esempio **Bob**) può verificare la validità della firma applicando la chiave pubblica $(n, e)$:

$$  
\text{Firma valida se } M = F^e \bmod n  
$$

Se l’uguaglianza è verificata, la firma è autentica; altrimenti, è falsa.

---

### **5. Esempio pratico**

Siano:

$$  
p = 47, \quad q = 71 \Rightarrow n = 3337  
$$  
$$  
(p - 1)(q - 1) = 3220  
$$  
Si scelgono:  
$$  
e = 79, \quad d = 1019  
$$  
poiché $79 \cdot 1019 \equiv 1 \pmod{3220}$

**Firma del messaggio:**

$$  
M = 1570  
$$  
$$  
F = 1570^{1019} \bmod 3337 = 668  
$$

**Verifica della firma:**

$$  
F^e \bmod n = 668^{79} \bmod 3337 = 1570 = M  
$$

La firma è dunque **valida**.

---

### **6. Firma di messaggi lunghi**

Se il messaggio $M$ è maggiore di $n$, non può essere firmato direttamente.  
Le soluzioni sono due:

1. **Suddivisione del messaggio**  
    Dividere $M$ in blocchi più piccoli $M_1, M_2, \dots, M_i < n$ e firmare ogni blocco:
    
    $$  
    \text{Firma}(M) = (\text{Firma}(M_1), \text{Firma}(M_2), \dots)  
    $$
    
    Tuttavia, questo approccio è **poco efficiente** e può creare problemi di sicurezza.
    
2. **Firma dell’hash del messaggio**  
    Si calcola il valore $h(M)$ (digest del messaggio) e si firma solo questo valore:
    
    $$  
    \text{Firma}(M) = \text{Firma}(h(M))  
    $$
    
    Questa soluzione è **più efficiente**, **più sicura** e garantisce l’**integrità** dei dati.
    

---

### **7. Firma RSA con hash**

Per firmare tramite hash, si procede così:

**Firma:**  
$$  
F \leftarrow [h(M)]^d \bmod n  
$$

**Verifica:**  
$$  
\text{Firma valida se } h(M) = F^e \bmod n  
$$

In questo modo la firma non dipende dal messaggio intero, ma dal suo hash, riducendo il rischio di manipolazioni e migliorando le prestazioni.

---

### **8. Sintesi finale**

- Lo schema RSA permette di **generare firme digitali** tramite l’elevazione modulare del messaggio (o del suo hash).
    
- La **firma** si ottiene con la **chiave privata**, mentre la **verifica** avviene con la **chiave pubblica**.
    
- Per **messaggi lunghi**, si firma l’**hash** invece del testo originale.
    
- Questo metodo garantisce **autenticità, integrità e non ripudio**, a condizione che la chiave privata resti segreta.