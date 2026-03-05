# **M6 UD3 Lezione 3 - View-serializzabilità**

### **1. Introduzione**

La **view-serializzabilità** rappresenta un criterio teorico fondamentale per stabilire se uno **schedule non seriale** possa essere considerato **corretto**, ossia se produca **lo stesso risultato** di uno **schedule seriale**.  
Per comprendere questo concetto, è necessario introdurre alcune definizioni di base che riguardano le relazioni tra operazioni di lettura e scrittura.

---

### **2. Definizioni preliminari**

In uno schedule $S$ che contiene operazioni di più transazioni, definiamo:

- **Operazione “legge-da” (read-from):**  
    Un’operazione $r_i(x)$ _legge-da_ $w_j(x)$ se:
    
    1. $w_j(x)$ **precede** $r_i(x)$ nello schedule $S$
        
    2. Non esiste alcuna altra scrittura $w_k(x)$ tra $w_j(x)$ e $r_i(x)$ in $S$
    
    In altre parole, $r_i(x)$ legge l’ultimo valore scritto da $w_j(x)$.
    
- **Scrittura finale:**  
    Un’operazione $w_i(x)$ è una **scrittura finale** su $x$ in $S$ se è **l’ultima scrittura su $x$** nello schedule.

**Esempio:**

```
S: r1(x) w1(x) w1(y) r2(x) w2(y)
```

- $r2(x)$ legge-da $w1(x)$
    
- $w1(x)$ è scrittura finale su `x`
    
- $w2(y)$ è scrittura finale su `y`

---

### **3. View-equivalenza**

Due schedule $S_i$ e $S_j$ (con $i ≠ j$) sono **view-equivalenti** se soddisfano **entrambe** le seguenti condizioni:

1. Hanno **le stesse relazioni “legge-da”** (read-from)
    
2. Hanno **le stesse scritture finali**

Formalmente:

$$  
S_i \equiv_v S_j  
$$

significa che gli schedule producono **la stessa vista logica** sui dati: ogni transazione osserva gli stessi valori e le stesse scritture finali in entrambi.

---

### **4. View-serializzabilità**

Uno **schedule è view-serializzabile** se esiste **almeno uno schedule seriale** a cui esso è **view-equivalente**.

L’insieme degli schedule view-serializzabili si indica con:

$$  
VSR = { S \ | \ S \equiv_v S_{seriale} }  
$$

In pratica, ciò significa che anche se le operazioni sono intrecciate, il risultato è identico a quello ottenuto da una qualche esecuzione seriale delle stesse transazioni.

---

### **5. Esempio 1 – Schedule view-serializzabile**

```
S: w0(x) r2(x) r1(x) w2(x) w2(z)
```

- **Relazioni legge-da:**
    
    - $r2(x) \leftarrow w0(x)$
        
    - $r1(x) \leftarrow w0(x)$
    
- **Scritture finali:**
    
    - su `x`: $w2(x)$
        
    - su `z`: $w2(z)$

Questo schedule è **view-equivalente** a:

```
S': w0(x) r1(x) r2(x) w2(x) w2(z)
```

poiché legge e scritture finali coincidono.  
✅ **Conclusione:** lo schedule è **view-serializzabile**.

---

### **6. Esempio 2 – Schedule view-serializzabile**

```
S: w0(x) r1(x) w1(x) r2(x) w1(z)
```

- **Relazioni legge-da:**
    
    - $r1(x) \leftarrow w0(x)$
        
    - $r2(x) \leftarrow w1(x)$
    
- **Scritture finali:**
    
    - su `x`: $w1(x)$
        
    - su `z`: $w1(z)$

Schedule **equivalente**:

```
S': w0(x) r1(x) w1(x) w1(z) r2(x)
```

✅ **Conclusione:** anche questo è **view-serializzabile**.

---

### **7. Esempio 3 – Schedule non view-serializzabile (perdita di aggiornamento)**

```
S: r1(x) r2(x) w2(x) w1(x)
```

- **Relazioni legge-da:** non definite coerentemente
    
- **Scrittura finale:** $w1(x)$

In questo caso, la scrittura di `T1` **sovrascrive** quella di `T2`, causando una **perdita di aggiornamento**.  
❌ **Conclusione:** lo schedule **non è view-serializzabile**.

---

### **8. Esempio 4 – Schedule non view-serializzabile (letture inconsistenti)**

```
S: r1(x) r2(x) w2(x) r1(x)
```

- **Relazioni legge-da:**
    
    - la prima lettura di $r1(x)$ non definita;
        
    - $r2(x)$ legge-da un valore non coerente;
        
    - la seconda $r1(x)$ legge da $w2(x)$
    
- **Scrittura finale:** $w2(x)$

Poiché `T1` legge due versioni diverse di `x`, si verifica una **lettura inconsistente**.  
❌ **Conclusione:** **non view-serializzabile**.

---

### **9. Esempio 5 – Schedule non view-serializzabile (aggiornamento fantasma)**

```
S: r1(x) r1(y) r2(z) r2(y) w2(y) w2(z) r1(z)
```

- **Relazioni legge-da:**
    
    - $r1(x)$, $r1(y)$, $r2(z)$ e $r2(y)$ leggono valori iniziali;
        
    - $r1(z)$ legge da $w2(z)$
    
- **Scritture finali:**
    
    - su `y`: $w2(y)$
        
    - su `z`: $w2(z)$

`T1` osserva uno stato della base di dati che rappresenta **solo parte** delle modifiche di `T2`, violando la coerenza logica del sistema.  
❌ **Conclusione:** **non view-serializzabile** (anomalia di aggiornamento fantasma).

---

### **10. Complessità della view-serializzabilità**

Determinare se due schedule sono view-equivalenti ha **costo polinomiale**; tuttavia, stabilire se uno schedule generico sia **view-serializzabile** è un problema **NP-difficile**.

Questo perché:

- occorre confrontare lo schedule con **tutti i possibili schedule seriali** delle stesse transazioni;
    
- il numero di schedule seriali cresce **fattorialmente** con il numero di transazioni.

Per questo motivo, la view-serializzabilità è **teoricamente corretta ma impraticabile**.  
Nei sistemi reali si utilizzano **condizioni più restrittive**, ma più efficienti da verificare (come la **conflict-serializzabilità**).

---

### **11. In sintesi**

In questa lezione abbiamo introdotto la **view-serializzabilità**, che rappresenta il criterio generale di correttezza per l’esecuzione concorrente di transazioni.

**Abbiamo visto:**

- Le definizioni di **legge-da** e **scrittura finale**
    
- Il concetto di **view-equivalenza**
    
- La definizione di **view-serializzabilità**
    
- Esempi di schedule view-serializzabili e non
    
- La **complessità computazionale** del problema (NP-difficile)

**Concetto chiave:**  
La view-serializzabilità garantisce la stessa “vista logica” di una serializzazione perfetta, ma è troppo costosa da applicare direttamente.  
Le lezioni successive introdurranno criteri più pratici come la **conflict-serializzabilità**, usata nei veri DBMS.

---


![](imgs/Pasted%20image%2020251125051928.png)

