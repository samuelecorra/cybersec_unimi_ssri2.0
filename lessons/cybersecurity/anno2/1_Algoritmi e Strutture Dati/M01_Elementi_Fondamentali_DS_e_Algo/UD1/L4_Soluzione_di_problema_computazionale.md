## **Lezione 4 – Soluzione di un semplice problema computazionale**

### **1. Introduzione generale**

In questa lezione impariamo ad **applicare in modo pratico** lo schema di analisi di un problema computazionale.  
Vedremo come, partendo da un problema semplice ma rappresentativo, si possa arrivare a:

1. Analizzare la **complessità del problema** $\Omega(n)$
    
2. **Progettare l’algoritmo** che lo risolve
    
3. Valutare la **complessità dell’algoritmo** $O(n)$
    

L’obiettivo finale è mostrare **quando un algoritmo può essere considerato ottimo**, cioè quando il suo ordine di complessità coincide con la complessità intrinseca del problema.

---

### **2. Il problema: l’ordinamento dei colori**

Il problema è noto come **problema della bandiera italiana**, introdotto da Dijkstra.  
È un ottimo esempio perché unisce chiarezza logica e analisi rigorosa.

#### **Formulazione**

Si consideri un **vettore di n elementi**, ciascuno dei quali può assumere uno dei tre colori:

- **0 → verde**
    
- **1 → bianco**
    
- **2 → rosso**
    

Si vuole ordinare il vettore in modo che:

1. Tutti i verdi precedano i bianchi
    
2. Tutti i bianchi precedano i rossi
    

Le uniche operazioni consentite sono:

- **esame di un elemento**
    
- **scambio di due elementi**, dati i loro indici
    

#### **Esempio (concettuale)**

Input casuale:

```
[rosso, verde, bianco, rosso, bianco, verde]
```

Output desiderato:

```
[verde, verde, bianco, bianco, rosso, rosso]
```

---

### **3. Analisi della complessità del problema**

#### **3.1. Dimensione dei dati**

Non possiamo fare assunzioni sulla distribuzione dei colori:  
potrebbero essere tutti uguali o completamente mescolati.

Per ordinare correttamente il vettore dobbiamo **esaminare ogni posizione almeno una volta**.

- Numero di posizioni: $n$
    
- Ogni posizione deve essere letta almeno una volta
    

➡️ **Complessità del problema:**  

$$  
\Omega(n)  
$$

---

#### **3.2. Eventi contabili**

Anche considerando solo i confronti, per ordinare gli n colori **servono almeno n−1 confronti** (basta pensare al caso in cui il vettore sia già ordinato).

- Numero minimo di confronti: $n - 1$
    

➡️ **Complessità del problema (in termini di eventi contabili):**  

$$  
\Omega(n)  
$$

---

### **4. Progettazione dell’algoritmo**

#### **Idea di base**

Gli elementi dello stesso colore sono **indistinguibili** tra loro, quindi non importa l’ordine relativo all’interno del gruppo.  
Scorrendo il vettore da sinistra verso destra, possiamo:

- spostare i **verdi** a sinistra,
    
- spostare i **rossi** a destra,
    
- lasciare i **bianchi** al centro.
    

Il risultato sarà automaticamente ordinato.

---

#### **Definizione dei cursori**

Supponiamo di utilizzare **tre indici** per tenere traccia delle zone del vettore:

| Cursore | Significato                                                  |
| ------- | ------------------------------------------------------------ |
| $k$     | Prima posizione che contiene un colore diverso dal verde     |
| $j$     | Ultima posizione che contiene un colore diverso dal rosso    |
| $i$     | Cursore di scansione (si muove tra $k$ e $j$, ovvero $k<i<j$ |

Condizioni iniziali:

- $k = 0$
    
- $j = n - 1$
    
- $i = 0$
    

---

#### **Regole operative**

Durante la scansione, esaminiamo l’elemento in posizione $i$:

1. **Se è verde (0):**
    
    - Scambialo con l’elemento in posizione $k$
        
    - Incrementa $k$
        
2. **Se è bianco (1):**
    
    - Non fare nulla
        
    - Incrementa solo $i$
        
3. **Se è rosso (2):**
    
    - Scambialo con l’elemento in posizione $j$
        
    - Decrementa $j$ (ma non incrementare $i$, perché il nuovo elemento scambiato deve ancora essere controllato)
        

---

### **5. Esempio passo-passo**

#### **Stato iniziale

```
[ verde | bianco | rosso | bianco | verde | rosso | bianco | verde | rosso ]
           k i                                                 j      n-esimo el
```

#### **Passo 1

$i$ punta a **bianco** → Incremento solo $i$.

```
[ verde | bianco | rosso | bianco | verde | rosso | bianco | verde | rosso ]
             k        i                                        j
```
#### **Passo 2

$i$ punta a **rosso** → scambio con l'elemento in posizione $j$.

```
[ verde | bianco | *verde* | bianco | verde | rosso | bianco | *rosso* | rosso ]
             k        i                                           j
```
Per poi fare j--

```
[ verde | bianco | verde | bianco | verde | rosso | bianco | rosso | rosso ]
             k       i                                 j
```

#### **Passo 3**
$i$ punta a **verde** → Scambiamo gli elementi $i$ e $k$:

```
[ verde | *verde* | *bianco* | bianco | verde | rosso | bianco | rosso | rosso ]
             k         i                                  j
```
E ora incrementiamo k:

```
[ verde | verde | bianco | bianco | verde | rosso | bianco | rosso | rosso ]
                   k  i                                j
```

#### **Passo 4**
Ora $i$ punta su bianco, incrementiamolo:

```
[ verde | verde | bianco | bianco | verde | rosso | bianco | rosso | rosso ]
                     k        i                       j
```
#### **Passo 5**
Ora $i$ punta su bianco, incrementiamolo:

```
[ verde | verde | bianco | bianco | verde | rosso | bianco | rosso | rosso ]
                     k                i                j
```
#### **Passo 6**
Ora $i$ punta  su verde → Scambiamo gli elementi $i$ e $k$:

```
[ verde | verde | *verde* | bianco | *bianco* | rosso | bianco | rosso | rosso ]
                     k                   i                 j
```
E incrementiamo k:

```
[ verde | verde | verde | bianco | bianco | rosso | bianco | rosso | rosso ]
                            k         i               j
```
#### **Passo 7**
Ora $i$ punta su bianco, incrementiamolo:

```
[ verde | verde | verde | bianco | bianco | rosso | bianco | rosso | rosso ]
                            k                 i       j
```
#### **Passo 8**

Ora $i$ punta  su rosso → Scambiamo gli elementi $i$ e $j$:

```
[ verde | verde | verde | bianco | bianco | *bianco* | *rosso* | rosso | rosso ]
                            k                   i         j
```
Per poi decrementare $j$:

```
[ verde | verde | verde | bianco | bianco | bianco | rosso | rosso | rosso ]
                            k                i  j
```
#### **Passo 7:
Ora $i$ punta su bianco. Incrementandolo, notiamo subito che abbiamo raggiunto la:

Condizione di uscita: quando $i > j$ → **fine algoritmo**.

---

### **6. Codice in linguaggio C**

```c
#include <stdio.h>
#include <stdbool.h>

int main() {

    // Innanzitutto dichiariamo e filliamo l'array non-ordinato:

    int colori_bandiera[] = {

        0,  // verde
        1,  // bianco   // k ed i iniziali
        2,  // rosso
        1,  // bianco
        0,  // verde
        2,  // rosso
        1,  // bianco
        0,  // verde    // j iniziale
        2   // rosso
    };

    // Ora possiamo procedere con la dichiarazione dei tre cursori:
    int dimensione_array, i, j, k;
    i = k = 1;
    dimensione_array = sizeof(colori_bandiera) / sizeof(colori_bandiera[0]);
    printf("Dimensione array: %d\n", dimensione_array); // DEBUG SEMPRE UTILE (9)
    j = 7;

    // Come preannunciato, condizione di uscita è che i superi j:
    while (i < j) {

        switch(colori_bandiera[i]) {
    // Gli scambi dei valori sono classici scambi con variabile di appoggio temporanea
            case 0: {   // verde

                int temp = colori_bandiera[i];
                colori_bandiera[i] = colori_bandiera[k];
                colori_bandiera[k] = temp;
                k++;
                break;
            }

            case 1: {   // bianco
                i++;
                break;
            }

            case 2: {   // rosso
                int temp = colori_bandiera[i];
                colori_bandiera[i] = colori_bandiera[j];
                colori_bandiera[j] = temp;
                j--;
                break;
            }
        }} // Fine switch e fine while
  
    // Debuggiamo per verificare, converttendo i numeri in colori:
    for (int idx = 0; idx < dimensione_array; idx++) {
        switch(colori_bandiera[idx]) {
            case 0:
                printf("verde ");
                break;
            case 1:
                printf("bianco ");
                break;
            case 2:
                printf("rosso ");
                break;
        }
    }
  
    return 0;
}
```

---

### **7. Analisi della complessità dell’algoritmo**

Vediamo quante operazioni vengono eseguite nel **caso pessimo**.

- Il cursore $i$ viene incrementato **al massimo $n−2$ volte**. Ma questo solo perché primo e ultimo elemento sono già ordinati. Se l'array intero fosse totalmente disordinato, allora $i$ potrebbe essere incrementato per un totale di $n$ volte.
    
- Prima di ogni incremento di $i$, possono essere eseguiti **al più due scambi** (nel caso rosso in cui si scambia con un elemento in coda ancora da verificare)
    
- Ogni scambio comporta **4 operazioni elementari** (vedere il case...)
    

Nel caso peggiore del nostro vettore, quindi:

$$  
T(n) \leq 2 \times 4 \times (n - 2) = 8n - 16  
$$

E se invece considerassimo l'array per niente ordinato:

$$  
T(n) \leq 2 \times 4 \times n = 8n  
$$

La parte costante non influisce sull’ordine di grandezza, quindi in entrambi i casi giungiamo alla medesima conclusione:

$$  
T(n) = O(n)  
$$

---

### **8. Sintesi finale**

|Fase|Complessità|Descrizione|
|---|---|---|
|Analisi del problema|$\Omega(n)$|Serve leggere ogni elemento almeno una volta|
|Progettazione e algoritmo|$O(n)$|L’algoritmo scorre ogni elemento al massimo una volta|
|Conclusione|Ottimo|$O(n)$ e $\Omega(n)$ coincidono|

➡️ L’algoritmo è **ottimo**, perché la sua complessità coincide con quella del problema.  
Inoltre, segue uno **schema generale di progettazione intelligente**: analizzare, progettare, ottimizzare fino a far coincidere la teoria con la pratica.

---

### **9. Riflessione conclusiva**

Questo semplice esempio racchiude **l’essenza dell’analisi algoritmica**:

- comprendere la **struttura del problema**,
    
- limitare al minimo le operazioni necessarie,
    
- e progettare una soluzione che raggiunga la complessità teoricamente più bassa possibile.
    

L’“ordinamento dei colori” è solo un caso particolare, ma il suo schema — **analisi → progettazione → verifica → ottimalità** — è ciò che sta alla base di ogni algoritmo efficiente.