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

Il problema è noto come **problema della bandiera olandese**, introdotto da Dijkstra, che era nato a Rotterdam.  
È un ottimo esempio perché unisce chiarezza logica e analisi rigorosa. Noi per semplicità prenderemo in esame la bandiera italiana. Non cambia assolutamente nada, come potete intuire, teste di rapa...

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

| Cursore | Significato                                                 |
| ------- | ----------------------------------------------------------- |
| $k$     | Prima posizione che contiene un colore diverso dal verde    |
| $j$     | Ultima posizione che contiene un colore diverso dal rosso   |
| $i$     | Cursore di scansione si muove tra $k$ e $j$, ovvero $k<i<j$ |

Il cursore $k$ è progettato affinché, se si trovasse inizialmente in prima posizione vorrebbe dire che il primo elemento non è verde, ma se si trovasse in terza posizione vorrebbe dire che le prime due celle sono verdi, e così via.

Questo ci permette di fare osservazioni analoghe sul cursore $j$: Se inizialmente si trovasse in penultima posizione, vorrebbe dire che l'ultimo elemento è rosso, mentre se si trovasse in ultima posizione, vorrebbe dire che l'ultimo elemento non è rosso, ma se si trovasse in terzultima posizione vorrebbe dire che gli ultimi due elementi sono rossi, e così via.

E quindi cosa possiamo dire sul cursore i? Che di sicuro, a prescindere dalla configurazione iniziale dei colori nel vettore, $i$ in partenza sarà sempre posizionato nella prima cella che non rispetta la configurazione desiderata.

Ad esempio: se il vettore inizia con un rosso e poi un verde, allora sia i che k saranno posizionati nella prima cella!
O ancora: se il vettore inizia con verde e poi rosso, allora i sarà posizionato nella seconda cella, mentre k sarà posizionato nella prima cella.
O ancora: se il vettore inizia con due verdi e poi un rosso, allora i sarà posizionato nella terza cella, mentre k sarà posizionato nella prima cella.

Quel che bisogna estrapolare da tutte queste considerazioni è che, matematicamente parlando:

$$
k \leq i < j
$$

Il docente nelle sue slide aveva scritto $k < i < j$, ma è più corretto scrivere $k \leq i < j$, perché è possibile che $i$ e $k$ coincidano, ad esempio quando il primo elemento del vettore è rosso, o quando il primo elemento è verde ma il secondo elemento è rosso, e così via. Credo che il suo fosse un refuso, ma è importante sottolineare questo dettaglio, perché se non lo facessimo, potremmo incorrere in errori logici durante la progettazione dell’algoritmo.

---

#### **Regole operative**

Durante la scansione, esaminiamo l’elemento in posizione $i$:

1. **Se è verde (0):**
   - Scambialo con l’elemento in posizione $k$
   - Incrementa $k$
   - Si può decidere se incrementare anche $i$, ma non è necessario: dopo lo scambio, in posizione $i$ arriverà di sicuro un elemento bianco, poiché $k$ è stato già superato da $i$ ergo non conterrà mai né un rosso né un verde, ma obbligatoriamente un bianco. Dal momento che questa è una certezza, ai vostri occhi dovrebbe risultare più "canonico" incorporare anche l’incremento di $i$ in questo caso, ma è importante sottolineare che non è necessario, perché anche se non lo facessimo, al prossimo ciclo $i$ punterebbe comunque su un bianco, e quindi verrebbe incrementato comunque. Quindi, in questo scenario, l’incremento di $i$ è una scelta stilistica più che una necessità logica.

2. **Se è bianco (1):**
   - Non fare nulla
   - Incrementa solo $i$

3. **Se è rosso (2):**
   - Scambialo con l’elemento in posizione $j$
   - Decrementa $j$ (ma non incrementare $i$, perché in questo scenario il nuovo elemento scambiato deve ancora essere controllato, a differenza del caso verde!!!) LA DIFFERENZA E' SOTTILE MA CRUCIALE, QUINDI PRESTATE MOLTA ATTENZIONE A QUESTO DETTAGLIO!!!

COSA MANCA? La condizione di uscita!

Spoiler, è quando $i$ supera $j$. Ma per poterlo affermare con certezza matematica, bisogna arrivare a dedurlo dallo scenario desiderato, ovvero tutti i verdi a sinistra, tutti i bianchi al centro e tutti i rossi a destra.

Da questa configurazione, possiamo dedurre che:

- Se tutti i verdi sono a sinistra, allora $k$ deve essere posizionato alla prima posizione che non è verde, ovvero alla prima posizione che contiene un bianco o un rosso.

- Se tutti i rossi sono a destra, allora $j$ deve essere posizionato all’ultima posizione che non è rosso, ovvero all’ultima posizione che contiene un verde o un bianco.

- Se tutti i bianchi sono al centro, allora $i$ deve essere posizionato tra $k$ e $j$, ovvero tra la prima posizione che non è verde e l’ultima posizione che non è rosso.

- Quindi, quando $i$ supera $j$, significa che abbiamo esaminato tutti gli elementi tra $k$ e $j$, e quindi abbiamo ordinato correttamente il vettore.

N.B.: alternativamente, si potrebbe addirittura porre $i = j$, ma è più elegante e pulito porre i > j, perché in questo modo si evidenzia chiaramente che la condizione di uscita è legata al superamento di un limite, e non alla coincidenza di due cursori.

---

### **5. Esempio passo-passo**

#### **Stato iniziale**

```
[ verde | bianco | rosso | bianco | verde | rosso | bianco | verde | rosso ]
           k i                                                 j      n-esimo el
```

#### **Passo 1**

$i$ punta a **bianco** → Incremento solo $i$.

```
[ verde | bianco | rosso | bianco | verde | rosso | bianco | verde | rosso ]
             k        i                                        j
```

#### **Passo 2**

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

Ora $i$ punta su verde → Scambiamo gli elementi $i$ e $k$:

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

Ora $i$ punta su rosso → Scambiamo gli elementi $i$ e $j$:

```
[ verde | verde | verde | bianco | bianco | *bianco* | *rosso* | rosso | rosso ]
                            k                   i         j
```

Per poi decrementare $j$:

```
[ verde | verde | verde | bianco | bianco | bianco | rosso | rosso | rosso ]
                            k                i  j
```

#### **Passo 9**

Ora $i$ punta su bianco, e dovremmo incrementarlo, ma facendo ciò hittiamo la condizione di uscita $i > j$:

```
[ verde | verde | verde | bianco | bianco | bianco | rosso | rosso | rosso ]
                            k                 j        i
```

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
                i++; // come detto poc'anzi, incorporiamo quest'incremento perché abbiam la certezza che dopo lo scambio arrivi un bianco!
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

- La prima domanda da porci è: quante volte viene incrementato $i$ nella peggiore delle ipotesi? Infatti la risposta ci serve a stabilire quando usciremo dal ciclo while (i < j). Questo dipende da moltissimi fattori:
  1. Il vettore è costituito da un numero di celle che è multiplo di 3, in modo da avere un numero uguale di verdi, bianchi e rossi? Se sì, e presupponendo che sia fillato davvero equidistribuendo i colori, cosa possiamo dire?
  2. Possiamo dire che i e j si andranno a scontrare verso i 2/3 del vettore, perché i primi 1/3 del vettore saranno occupati da verdi, i successivi 1/3 saranno occupati da bianchi e gli ultimi 1/3 saranno occupati da rossi. Quindi, in questo scenario, $i$ verrà incrementato circa $2n/3$ volte prima di superare $j$.
  3. Se invece il vettore è fillato in modo completamente casuale, allora le considerazioni si complicano ulteriormente:
  4. In questo caso, ad esempio supponendo che per errore di inizializzazione non ci siano celle rosse, allora $i$ verrà incrementato $n$ volte prima di superare $j$, che si trova sicuramente alla fine del vettore.

  Quale scenario prendiamo? Lo scenario totalmente randomico tecnicamente sta introducendo errori nella specifica, ma è anche vero che è uno scenario che potrebbe verificarsi, quindi è legittimo considerarlo. D’altra parte, lo scenario equidistribuito è più rappresentativo di un caso reale, e ci permette di fare considerazioni più precise, quindi è quello che prenderemo in esame.

  Abbiamo così analizzato il ciclo while, ma ora dobbiamo entrarci dentro, perché all'interno di esso, nello switch, abbiamo tre scenari, ognuno dei quali comporta un numero diverso di operazioni elementari, e quindi dobbiamo analizzarli tutti per capire qual è il numero massimo di operazioni che potremmo eseguire prima di incrementare $i$.

- Prima di ogni incremento di $i$, possono essere eseguiti **al più due scambi**:

  **Caso 1: Il colore è Bianco (1).**
  - **Azione:** `i++`.
  - **Scambi:** 0.

  **Caso 2: Il colore è Rosso (2).**
  - **Azione:** Scambio con j e `j--`.
  - **Scambi:** 1.
  - **Cosa succede dopo?** L'elemento che era in j è ora in i. **Non sappiamo cosa sia.** Potrebbe essere un verde, un bianco ma non un altro rosso, perché sarebbe discordante con la definizione di j che indica l'ultima posizione prima di un elemento rosso. Quindi i rimane fermo per analizzare questo nuovo elemento nel ciclo successivo. Se sarà bianco, allora lo scenario si chiude con un solo scambio, ma se sarà verde, allora si aprirà un nuovo scenario che ci porterà a eseguire un secondo scambio, questa volta con k, per spostare il verde a sinistra. Quindi, in questo caso, potremmo arrivare a eseguire due scambi prima di incrementare i.

  **Caso 3: Il colore è Verde (0).**
  - **Azione:** Scambio con k e `k++`.
  - **Scambi:** 1.
  - **Cosa succede dopo?** Qui c'è il trucco: poiché k punta alla prima posizione "non verde" (che è stata superata da i o è coincidente con i), l'elemento che arriva in i dopo lo scambio con k è **garantito** essere un bianco (o il verde stesso se i=k, ma questo è un caso limite). Pertanto, al giro successivo, l'algoritmo entrerà nel caso "Bianco" e incrementerà i. Ma allora qui non si presenta nemmeno la possibilità di eseguire un secondo scambio, perché il nuovo elemento in i è sicuramente un bianco, che non richiede ulteriori scambi.

- Ogni scambio comporta **5 operazioni elementari** (4 nel caso in cui non incrementassimo i dopo lo scambio con k, ma come abbiamo detto è più elegante e pulito incorporare anche quell'incremento, quindi consideriamo questo scenario).

Nel caso peggiore del nostro vettore, quindi, dobbiamo considerare tutti i valori estrapolati in questo ragionamento:

$$
T(n) \leq \frac{2n}{3} \cdot 2 \cdot 5 = \frac{20n}{3}
$$

Ora ricordiamo che possiamo eliminare i coefficienti costanti e i termini di ordine inferiore, quindi arriviamo a:

$$
\frac{20n}{3} = O(n)
$$

Da cui è chiaro che:

$$
T(n) = O(n)
$$

---

### **8. Sintesi finale**

| Fase                      | Complessità | Descrizione                                           |
| ------------------------- | ----------- | ----------------------------------------------------- |
| Analisi del problema      | $\Omega(n)$ | Serve leggere ogni elemento almeno una volta          |
| Progettazione e algoritmo | $O(n)$      | L’algoritmo scorre ogni elemento al massimo una volta |
| Conclusione               | Ottimo      | $O(n)$ e $\Omega(n)$ coincidono                       |

➡️ L’algoritmo è **ottimo**, perché la sua complessità coincide con quella del problema.
Inoltre, segue uno **schema generale di progettazione intelligente**: analizzare, progettare, ottimizzare fino a far coincidere la teoria con la pratica.

---

### **9. Riflessione conclusiva**

Questo semplice esempio racchiude **l’essenza dell’analisi algoritmica**:

- comprendere la **struttura del problema**,
- limitare al minimo le operazioni necessarie,
- e progettare una soluzione che raggiunga la complessità teoricamente più bassa possibile.

L’“ordinamento dei colori” è solo un caso particolare, ma il suo schema — **analisi → progettazione → verifica → ottimalità** — è ciò che sta alla base di ogni algoritmo efficiente.

$$
$$
