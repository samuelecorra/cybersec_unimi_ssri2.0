# Ricerca binaria di confine ed elemento maggioritario

### **1. Ricerca binaria di un confine**

La ricerca binaria non serve soltanto a stabilire se una chiave esiste. Se il predicato osservato lungo un vettore ordinato cambia una sola volta, può individuare in $O(\log n)$ l'ultima posizione vera o la prima posizione falsa.

L'invariante comune è:

- `ans` contiene la migliore posizione valida già trovata;
- l'intervallo `[lo, hi]` contiene tutte le posizioni non ancora escluse;
- quando si trova una soluzione, si continua nella metà che potrebbe contenerne una migliore.

### **2. Ultimo zero in un vettore binario ordinato**

Sia $A$ formato da zero seguiti da uno. Per trovare l'indice dell'ultimo zero:

```text
ULTIMO-ZERO(A, n)
    lo <- 0
    hi <- n - 1
    ans <- -1
    mentre lo <= hi
        m <- lo + floor((hi - lo) / 2)
        se A[m] = 0
            ans <- m
            lo <- m + 1
        altrimenti
            hi <- m - 1
    restituisci ans
```

Se non esistono zeri, `ans` resta $-1$; se sono tutti zero, il risultato è $n-1$. Ogni iterazione dimezza l'intervallo, quindi il tempo è $O(\log n)$ e lo spazio $O(1)$.

### **3. Ultima occorrenza di una chiave**

Per un vettore ordinato con ripetizioni e una chiave $k$:

```text
ULTIMA-OCCORRENZA(A, n, k)
    lo <- 0
    hi <- n - 1
    ans <- -1
    mentre lo <= hi
        m <- lo + floor((hi - lo) / 2)
        se A[m] <= k
            se A[m] = k
                ans <- m
            lo <- m + 1
        altrimenti
            hi <- m - 1
    restituisci ans
```

Quando $A[m]=k$ non si termina: si salva $m$ e si cerca a destra. Quando $A[m]<k$, ogni posizione a sinistra vale al più $A[m]$ e non può offrire una nuova occorrenza di $k$, quindi si va ancora a destra. Se $A[m]>k$, si va a sinistra.

> ⚠️ Fermarsi alla prima uguaglianza risolve la ricerca ordinaria, non il problema dell'ultima occorrenza.

### **4. Elemento maggioritario**

In un vettore di lunghezza $n$, un elemento è **maggioritario** se compare strettamente più di $n/2$ volte. Ne può esistere al massimo uno.

#### **4.1. Voto di Boyer–Moore**

```text
CANDIDATO-MAGGIORITARIO(A, n)
    candidato <- NIL
    conteggio <- 0
    per x in A
        se conteggio = 0
            candidato <- x
            conteggio <- 1
        altrimenti se x = candidato
            conteggio <- conteggio + 1
        altrimenti
            conteggio <- conteggio - 1

    occorrenze <- 0
    per x in A
        se x = candidato
            occorrenze <- occorrenze + 1

    se occorrenze > floor(n / 2)
        restituisci candidato
    altrimenti
        restituisci NESSUNO
```

La prima scansione cancella concettualmente coppie di elementi diversi. Se esiste una maggioranza, non può essere cancellata completamente e il candidato finale coincide con essa. La seconda scansione è indispensabile se l'esistenza della maggioranza non è garantita: per $[1,2,3]$ la prima fase produce comunque un candidato, ma nessun valore compare più di $3/2$ volte.

Tempo totale $\Theta(n)$, spazio ausiliario $\Theta(1)$.

#### **4.2. Esempio**

Per $A=[2,1,2,3,2,2,4,2]$ le cancellazioni possono eliminare coppie $(2,1)$, $(2,3)$ e $(2,4)$; rimangono occorrenze di $2$. La verifica conta $5$ occorrenze e, poiché $5>8/2$, restituisce $2$.

> ✅ La ricerca binaria sfrutta l'ordinamento e un confine monotono; Boyer–Moore sfrutta invece la cancellazione a coppie e non richiede che il vettore sia ordinato.
