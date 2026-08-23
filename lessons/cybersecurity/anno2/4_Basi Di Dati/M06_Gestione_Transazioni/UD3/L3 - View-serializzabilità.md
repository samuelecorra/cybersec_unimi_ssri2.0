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
     In altre parole, $r_i(x)$ legge l’ultimo valore scritto da $w_j(x)$. Al posto di "legge da" useremo $\leftarrow$ come per gli assegnamenti in pseudocodice
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

Quali sono i 6 possibili schedule seriali delle transazioni `T0`, `T1` e `T2`?

$3! = 6$ schedule seriali:

- `S1: T0 → T1 → T2`
- `S2: T0 → T2 → T1`
- `S3: T1 → T0 → T2`
- `S4: T1 → T2 → T0`
- `S5: T2 → T0 → T1`
- `S6: T2 → T1 → T0`

Ma chiaramente possiamo escluderne alcuni perché non rispettano le relazioni legge-da e le scritture finali.

La prima "legge da" è $r2(x) \leftarrow w0(x)$, quindi implica che `T0` deve precedere `T2`.
Quindi eliminiamo tutti gli schedule seriali con `T2` prima di `T0`: scompaiono `S4`, `S5` e `S6`.

La seconda "legge da" è $r1(x) \leftarrow w0(x)$, quindi implica che `T0` deve precedere `T1`.
Quindi eliminiamo tutti gli schedule seriali con `T1` prima di `T0`: scompare dunque `S3`.

Inoltre notiamo che T2 scrive su x, quindi deve essere l'ultima transazione a scrivere su x, perché se T2 venisse prima di r1(x), allora questo r1(x) leggerebbe w2(x) e non w0(x).
Quest'ultima osservazione elimina anche `S2`, lasciando come unico schedule seriale possibile
`S1: T0 → T1 → T2`

Quindi lo schedule di partenza è **view-equivalente** a:

```

S': w0(x) r1(x) r2(x) w2(x) w2(z)

```

poiché legge e scritture finali coincidono.
✅ **Conclusione:** lo schedule è **view-serializzabile**, nello specifico solo in un caso.

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

Anche qui abbiamo 6 possibili schedule seriali delle transazioni `T0`, `T1` e `T2`.

- `S1: T0 → T1 → T2`
- `S2: T0 → T2 → T1`
- `S3: T1 → T0 → T2`
- `S4: T1 → T2 → T0`
- `S5: T2 → T0 → T1`
- `S6: T2 → T1 → T0`

Procediamo ancora una volta per eliminazione:

- la prima legge-da $r1(x) \leftarrow w0(x)$ implica che `T0` deve precedere `T1`, quindi eliminiamo `S3`, `S4` e `S6`.

-la seconda legge-da $r2(x) \leftarrow w1(x)$ implica che `T1` deve precedere `T2`, quindi eliminiamo `S2` e `S5`.

Rimane ancora una volta un unico schedule seriale possibile: `S1: T0 → T1 → T2`.

Schedule **equivalente**:

```
S': w0(x) r1(x) w1(x) w1(z) r2(x)
```

Ora con questo schedule, se ci scrivessimo le relazioni legge-da e le scritture finali, vedremmo che coincidono con quelle dello schedule originale.

✅ **Conclusione:** anche questo è **view-serializzabile**.

---

### **7. Esempio 3 – Schedule non view-serializzabile (perdita di aggiornamento)**

```
S: r1(x) r2(x) w2(x) w1(x)
```

- **Relazioni legge-da:** non definite coerentemente
- **Scrittura finale:** $w1(x)$

Stavolta abbiamo 2! = 2 schedule seriali possibili delle transazioni `T1` e `T2`:

- `S1: T1 → T2`
- `S2: T2 → T1`

Ma attenzione, siccome entrambe le transazioni scrivono su `x`, se facessimo S1 allora r2(x) leggerebbe il valore scritto da w1(x), mentre se facessimo S2 allora r1(x) leggerebbe il valore scritto da w2(x). In entrambi i casi, le relazioni legge-da non coincidono con quelle dello schedule originale.

Per dirla meglio:

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

Ancora una volta abbiamo 2 possibili schedule seriali delle transazioni `T1` e `T2`:

- `S1: T1 → T2`
- `S2: T2 → T1`

Se andiamo fare prima `T1`, le sue due letture subito consecutive di `x` leggono due valori non definiti anteriormente, ergo non coincidono con le relazioni legge-da dello schedule originale.

Idem con patate se facciamo prima `T2`, perché la sua write sovrascrive il valore che verrà letto due volte dalle read di `T1`, quindi anche in questo caso le relazioni legge-da non coincidono con quelle dello schedule originale.

Ricollegandoci alle anomalie delle quali avevamo parlato, questo è un caso di **lettura inconsistente**, perché nello schedule originale le due read di `T1` leggono valori diversi.
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

Abbiamo 2 possibili schedule seriali delle transazioni `T1` e `T2`:

- `S1: T1 → T2`
- `S2: T2 → T1`

L'ultima "legge-da" chiaramente ci impone che `T2` deve precedere `T1` quindi rimane solo `S2: T2 → T1`.

Ma eseguendo `T2` prima di `T1`, eseguiamo la scrittura w2(y) che invalida la legge-da di `r1(y)` che leggeva il valore iniziale.

Quindi le leggi-da cambiano e non coincidono con quelle dello schedule originale.

`T1` osserva uno stato della base di dati che rappresenta **solo parte** delle modifiche di `T2`, violando la coerenza logica del sistema.
❌ **Conclusione:** **non view-serializzabile** (ripensandoci è quella che avevamo chiamato anomalia di aggiornamento fantasma).

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

```

```
