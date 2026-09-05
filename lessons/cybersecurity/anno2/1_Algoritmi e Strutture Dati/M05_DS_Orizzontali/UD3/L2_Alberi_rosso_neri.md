## **Lezione 2: Alberi rosso-neri**

---

### **1. Introduzione**

Nella lezione precedente si è visto che un **albero binario di ricerca** offre operatori `appartiene`, `inserisci`, `cancella`, `min` e `cancellamin` tutti con complessità $O(h)$, dove $h$ è l'altezza dell'albero. Questo risultato è eccellente **soltanto se** $h$ resta vicino a $\log_2 n$.

Il problema è che l'altezza dipende dall'**ordine di inserimento** e non dal contenuto dell'insieme. Inserendo le chiavi $1, 2, 3, \dots, n$ in ordine crescente si ottiene una catena tutta a destra: un albero di altezza $n-1$, indistinguibile da una lista concatenata. Gli operatori degradano da $O(\log n)$ a $O(n)$ e si perde ogni vantaggio rispetto al vettore ordinato.

Servono quindi **alberi bilanciati**: strutture che garantiscono $h = O(\log n)$ **nel caso peggiore**, qualunque sia l'ordine degli inserimenti. Gli **alberi rosso-neri** sono la realizzazione più diffusa di questa idea: sono il tipo di dato dietro `std::map` e `std::set` del C++, `TreeMap` e `TreeSet` di Java, e lo scheduler CFS del kernel Linux.

L'intuizione di fondo è semplice e sorprendente: si aggiunge a ogni nodo **un solo bit** di informazione — il colore, rosso o nero — e si impone un piccolo insieme di regole sulla distribuzione dei colori. Queste regole, apparentemente arbitrarie, sono sufficienti a garantire che nessun cammino radice-foglia possa essere più del **doppio** di qualunque altro.

> [!important]
> Il colore non ha alcun significato semantico rispetto ai dati: è un artificio strutturale che serve unicamente a limitare lo sbilanciamento. Un albero rosso-nero, tolti i colori, resta un normale albero binario di ricerca e la visita simmetrica ne restituisce gli elementi in ordine crescente.

---

### **2. Definizione e proprietà**

Un **albero rosso-nero** è un albero binario di ricerca in cui ogni nodo possiede un attributo aggiuntivo `colore` $\in \{\text{rosso}, \text{nero}\}$ e valgono le seguenti cinque proprietà.

#### **Le cinque proprietà**

| | Proprietà |
|---|---|
| **P1** | Ogni nodo è **rosso** oppure **nero**. |
| **P2** | La **radice** è nera. |
| **P3** | Ogni **foglia** (nodo `NIL`) è nera. |
| **P4** | Se un nodo è **rosso**, entrambi i suoi figli sono **neri**. |
| **P5** | Per ogni nodo, tutti i cammini che scendono da esso fino alle foglie contengono lo **stesso numero di nodi neri**. |

#### **I nodi NIL**

Nella trattazione classica si assume che ogni "posto vuoto" dell'albero sia occupato da un nodo speciale `NIL`, sempre nero e privo di chiave. Questi nodi non contengono dati e nelle implementazioni reali si realizzano con un'**unica sentinella condivisa**, per non sprecare memoria: tutti i puntatori che sarebbero nulli puntano allo stesso oggetto `NIL`.

I nodi `NIL` sono le **vere foglie** dell'albero; i nodi con chiave sono tutti nodi interni. Questa convenzione semplifica enormemente la formulazione delle proprietà e degli algoritmi, perché elimina i casi particolari sui puntatori nulli.

#### **Altezza nera**

> [!important]
> Si dice **altezza nera** di un nodo $x$, e si scrive $bh(x)$, il numero di nodi neri sul cammino che scende da $x$ (**escluso**) fino a una qualunque foglia `NIL` (**inclusa**).

La proprietà **P5** garantisce che questo numero sia lo stesso per tutti i cammini, quindi $bh(x)$ è ben definita. L'**altezza nera dell'albero** è $bh(\text{radice})$.

#### **Esempio di albero rosso-nero valido**

- **13 (N)** — radice
  - **8 (R)**
    - **1 (N)** → destro: **6 (R)**
    - **11 (N)**
  - **17 (N)**
    - **15 (R)**
    - **25 (R)**

Verifica delle proprietà:

- **P2**: la radice $13$ è nera ✅
- **P4**: i nodi rossi sono $8$, $6$, $15$, $25$; il nodo $8$ ha figli $1$ e $11$, entrambi neri; $6$, $15$ e $25$ sono foglie con figli `NIL` neri ✅
- **P5**: contando i neri da un figlio della radice a `NIL`:

| Cammino | Neri | $bh$ |
|---|---|---:|
| $13 \to 8 \to 1 \to 6 \to \text{NIL}$ | $1$, NIL | 2 |
| $13 \to 8 \to 1 \to \text{NIL}$ (sinistro) | $1$, NIL | 2 |
| $13 \to 8 \to 11 \to \text{NIL}$ | $11$, NIL | 2 |
| $13 \to 17 \to 15 \to \text{NIL}$ | $17$, NIL | 2 |
| $13 \to 17 \to 25 \to \text{NIL}$ | $17$, NIL | 2 |

Tutti i cammini hanno $bh = 2$ ✅

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Il teorema sull'altezza**

Le cinque proprietà non sono un elenco arbitrario: sono esattamente ciò che serve a dimostrare il risultato che segue, il quale a sua volta giustifica l'intera costruzione.

> [!important]
> **Teorema.** Un albero rosso-nero con $n$ nodi interni ha altezza
> $$h \le 2\log_2(n+1).$$

#### **Passo 1: il sottoalbero radicato in $x$ contiene almeno $2^{bh(x)}-1$ nodi interni**

Si procede per induzione sull'altezza di $x$.

**Caso base.** Se $x$ ha altezza $0$, allora $x$ è una foglia `NIL`, $bh(x) = 0$ e il sottoalbero contiene $2^{0}-1 = 0$ nodi interni ✅

**Passo induttivo.** Sia $x$ un nodo interno di altezza positiva, con figli $y_1$ e $y_2$. Ciascun figlio ha altezza nera pari a $bh(x)$ oppure $bh(x)-1$, a seconda che sia rosso o nero:

- se il figlio è **rosso**, non contribuisce al conteggio dei neri e la sua altezza nera è $bh(x)$;
- se il figlio è **nero**, contribuisce con sé stesso e la sua altezza nera è $bh(x)-1$.

In entrambi i casi $bh(y_i) \ge bh(x)-1$. Per ipotesi induttiva ogni figlio ha almeno $2^{bh(x)-1}-1$ nodi interni, quindi il sottoalbero di $x$ ne ha almeno

$$
\underbrace{\left(2^{bh(x)-1}-1\right)}_{y_1} + \underbrace{\left(2^{bh(x)-1}-1\right)}_{y_2} + \underbrace{1}_{x} = 2\cdot 2^{bh(x)-1} - 1 = 2^{bh(x)}-1 . \qquad \blacksquare
$$

#### **Passo 2: l'altezza nera è almeno metà dell'altezza**

Per la proprietà **P4** non possono esistere due nodi rossi consecutivi su un cammino. Su un cammino radice-foglia di lunghezza $h$, quindi, **almeno la metà** dei nodi è nera (nel caso estremo i colori si alternano, e la radice è comunque nera per P2):

$$
bh(\text{radice}) \ge \frac{h}{2}.
$$

#### **Passo 3: conclusione**

Combinando i due passi:

$$
n \;\ge\; 2^{bh(\text{radice})}-1 \;\ge\; 2^{h/2}-1 .
$$

Isolando $h$:

$$
n+1 \ge 2^{h/2}
\quad\Longrightarrow\quad
\log_2(n+1) \ge \frac{h}{2}
\quad\Longrightarrow\quad
\boxed{h \le 2\log_2(n+1)} \qquad \blacksquare
$$

#### **Conseguenza operativa**

Poiché tutti gli operatori dell'albero binario di ricerca costano $O(h)$, si ottiene immediatamente:

$$
\texttt{appartiene},\;\texttt{min},\;\texttt{max},\;\texttt{successore},\;\texttt{predecessore} \;=\; O(\log n)
$$

**nel caso peggiore**, non solo in media. È questa la differenza sostanziale rispetto al BST non bilanciato, dove il caso peggiore è $O(n)$, e rispetto alla tabella hash, dove il caso peggiore è $O(n)$ e non esiste ordinamento fra le chiavi.

> [!note]
> Il fattore $2$ è il "prezzo" della flessibilità: un albero rosso-nero può essere alto fino al **doppio** dell'ottimo. Gli alberi AVL, più rigidi, garantiscono $h \le 1.44\log_2 n$ ma pagano con un maggior numero di ristrutturazioni. Su un milione di nodi: altezza ottima $20$, limite AVL $29$, limite rosso-nero $40$.

---

### **4. Le rotazioni**

Le rotazioni sono le **uniche** operazioni che modificano la forma dell'albero. Preservano la proprietà di ricerca (P1 e P2 della lezione precedente), agiscono su un numero costante di puntatori e costano quindi $\Theta(1)$.

#### **Rotazione sinistra**

Si applica a un nodo $x$ il cui figlio destro $y$ non sia `NIL`. Il nodo $y$ prende il posto di $x$, il nodo $x$ diventa figlio sinistro di $y$, e il sottoalbero sinistro di $y$ (chiamiamolo $\beta$) diventa il sottoalbero destro di $x$.

**Prima:**

- $x$ → sinistro: $\alpha$; destro: $y$ → sinistro: $\beta$, destro: $\gamma$

**Dopo:**

- $y$ → sinistro: $x$ → sinistro: $\alpha$, destro: $\beta$; destro: $\gamma$

#### **Perché l'ordine è preservato**

L'ordine relativo fra i tre sottoalberi e le due chiavi non cambia. Prima della rotazione la visita simmetrica produce

$$
\alpha,\; x,\; \beta,\; y,\; \gamma ;
$$

dopo la rotazione produce

$$
\alpha,\; x,\; \beta,\; y,\; \gamma
$$

— la stessa sequenza. Il sottoalbero $\beta$ contiene chiavi comprese fra $x$ e $y$, e può quindi stare indifferentemente a destra di $x$ o a sinistra di $y$: è proprio questa libertà che la rotazione sfrutta.

#### **Rotazione destra**

È l'operazione inversa, ottenuta scambiando i ruoli di sinistra e destra. Si applica a un nodo $y$ il cui figlio sinistro $x$ non sia `NIL`.

#### **Pseudocodice**

```c
void ruotaSinistra(Albero T, Nodo *x)
{
    Nodo *y = x->destro;             /* y non deve essere NIL */

    x->destro = y->sinistro;         /* beta diventa figlio destro di x */
    if (y->sinistro != NIL)
        y->sinistro->padre = x;

    y->padre = x->padre;             /* y prende il posto di x */
    if (x->padre == NIL)
        T->radice = y;
    else if (x == x->padre->sinistro)
        x->padre->sinistro = y;
    else
        x->padre->destro = y;

    y->sinistro = x;                 /* x diventa figlio sinistro di y */
    x->padre = y;
}
```

Il numero di puntatori modificati è costante ($6$ nel caso generale), da cui il costo $\Theta(1)$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Inserimento**

#### **Schema generale**

L'inserimento procede in due fasi:

1. si inserisce il nuovo nodo **come in un normale albero binario di ricerca**, scendendo dalla radice fino alla posizione corretta;
2. si colora il nuovo nodo di **rosso** e si esegue una procedura di ripristino delle proprietà.

#### **Perché il nuovo nodo è rosso**

La scelta non è arbitraria. Se il nuovo nodo fosse **nero**, tutti i cammini che passano per esso guadagnerebbero un nero e la proprietà **P5** (altezza nera uniforme) verrebbe violata su tutti i cammini dell'albero — una violazione globale, difficile da riparare.

Colorandolo di **rosso**, invece:

- **P5** resta soddisfatta: il nodo rosso non contribuisce al conteggio dei neri;
- **P2** può essere violata solo se il nuovo nodo è la radice, caso banale da sistemare (basta colorarlo di nero);
- l'unica violazione possibile è **P4**: due rossi consecutivi, se anche il padre è rosso.

> [!important]
> Il nuovo nodo rosso produce al più **una sola violazione locale**, confinata alla coppia nodo-padre. È questa la ragione della scelta: si passa da un problema globale a uno locale, riparabile con un numero costante di rotazioni.

#### **I tre casi del ripristino**

Sia $z$ il nodo problematico (inizialmente il nuovo nodo). Se il padre di $z$ è nero, non c'è nulla da fare. Altrimenti — padre rosso — il **nonno** esiste necessariamente ed è nero (perché il padre rosso non può essere la radice, e per P4 il padre di un rosso è nero). Il caso da applicare dipende dal colore dello **zio**, cioè del fratello del padre.

| Caso | Condizione | Azione | Termina? |
|---|---|---|---|
| **1** | zio **rosso** | ricolorazione: padre e zio → neri, nonno → rosso; $z$ risale al nonno | **no**, si ripete |
| **2** | zio nero, $z$ **disallineato** ("triangolo") | rotazione sul **padre**; si passa al caso 3 | no, prepara il caso 3 |
| **3** | zio nero, $z$ **allineato** ("linea") | padre → nero, nonno → rosso, rotazione sul **nonno** | **sì** |

Al termine si colora sempre la radice di nero, il che ripristina **P2** se il caso 1 l'ha violata.

#### **Che cosa significa "allineato" e "disallineato"**

Si osserva il cammino di **due passi** dal nonno a $z$:

- **allineato (linea):** i due passi vanno nella stessa direzione, cioè $z$ è figlio sinistro di un padre che è figlio sinistro (SS), oppure figlio destro di un figlio destro (DD);
- **disallineato (triangolo):** i due passi cambiano direzione, cioè SD oppure DS.

Nel caso disallineato una rotazione sul padre "raddrizza" la configurazione, riconducendola al caso allineato.

#### **Analisi dei tre casi**

**Caso 1 — zio rosso.** Padre e zio sono entrambi rossi, il nonno è nero. Scambiando i colori (padre e zio neri, nonno rosso) l'altezza nera di ogni cammino resta invariata: si è aggiunto un nero sui due rami e tolto uno dal nonno, che sta sopra entrambi. La violazione P4 si sposta però sul nonno, che ora è rosso e potrebbe avere un padre rosso.

> [!warning]
> Il caso 1 è l'unico che **si propaga verso l'alto**: sposta il problema di due livelli e può quindi ripetersi fino a $O(\log n)$ volte. Quando raggiunge la radice, la ricolorazione finale ne annulla l'effetto e **l'altezza nera dell'intero albero cresce di uno** — l'unico modo in cui un albero rosso-nero può diventare più profondo in senso nero.

**Caso 2 — zio nero, disallineato.** Una rotazione sul padre nella direzione opposta a quella di $z$ trasforma il triangolo in una linea. Il nodo problematico diventa l'ex padre. Nessuna proprietà viene riparata: il caso 2 è puramente preparatorio.

**Caso 3 — zio nero, allineato.** Si colora il padre di nero e il nonno di rosso, poi si ruota sul nonno nella direzione opposta a $z$. Il padre sale al posto del nonno, che scende diventando suo figlio. Dopo questa operazione non ci sono più rossi consecutivi e il ripristino **termina definitivamente**.

#### **Pseudocodice**

```c
void inserisciFixup(Albero T, Nodo *z)
{
    while (z->padre->colore == ROSSO) {
        if (z->padre == z->padre->padre->sinistro) {
            Nodo *y = z->padre->padre->destro;        /* lo zio */

            if (y->colore == ROSSO) {                 /* CASO 1 */
                z->padre->colore = NERO;
                y->colore = NERO;
                z->padre->padre->colore = ROSSO;
                z = z->padre->padre;
            } else {
                if (z == z->padre->destro) {          /* CASO 2 */
                    z = z->padre;
                    ruotaSinistra(T, z);
                }
                z->padre->colore = NERO;              /* CASO 3 */
                z->padre->padre->colore = ROSSO;
                ruotaDestra(T, z->padre->padre);
            }
        } else {
            /* simmetrico, con sinistra e destra scambiate */
        }
    }
    T->radice->colore = NERO;
}
```

#### **Esempio svolto**

Si inseriscono nell'ordine $11, 21, 8, 18, 20$.

**Inserimento di 11.** Albero vuoto: $11$ diventa radice e viene colorato di nero.

- **11 (N)**

**Inserimento di 21 e 8.** Entrambi hanno padre nero: nessuna violazione.

- **11 (N)** → sinistro: **8 (R)**, destro: **21 (R)**

**Inserimento di 18 — caso 1.** Il nodo $18$ **(R)** ha padre $21$ **(R)**: violazione P4. Lo zio è $8$, **rosso**.

Ricolorazione: $21$ e $8$ diventano neri, $11$ rosso. Il nodo problematico risale a $11$, che è la radice e torna nera.

- **11 (N)** → sinistro: **8 (N)**; destro: **21 (N)** → sinistro: **18 (R)**

**Inserimento di 20 — caso 2 seguito dal caso 3.** Il nodo $20$ **(R)** diventa figlio destro di $18$ **(R)**: violazione. Il nonno è $21$, lo zio è `NIL` (nero).

Il nodo $20$ è figlio **destro** mentre il padre $18$ è figlio **sinistro**: configurazione **disallineata**.

*Caso 2* — rotazione sinistra su $18$: la catena diventa $21 \to 20 \to 18$, allineata a sinistra; il nodo problematico è ora $18$.

*Caso 3* — $20$ diventa nero, $21$ rosso, rotazione destra su $21$:

- **11 (N)** → sinistro: **8 (N)**; destro: **20 (N)** → sinistro: **18 (R)**, destro: **21 (R)**

Verifica finale: nessun rosso ha figli rossi ✅; tutti i cammini hanno altezza nera $2$ ✅

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Cancellazione**

La cancellazione è la parte più laboriosa, ma segue una logica analoga.

#### **Schema generale**

Si comincia come in un normale albero binario di ricerca:

- se il nodo da cancellare ha **al più un figlio**, lo si rimuove sostituendolo con quel figlio;
- se ha **due figli**, lo si sostituisce con il proprio **successore** (il minimo del sottoalbero destro), che ha al più un figlio destro; è quest'ultimo il nodo fisicamente rimosso.

Detto $y$ il nodo effettivamente rimosso e $x$ il nodo che ne prende il posto:

- se $y$ era **rosso**, nessuna proprietà è violata: l'altezza nera non cambia e non si creano rossi adiacenti;
- se $y$ era **nero**, tutti i cammini che passavano per $y$ hanno perso un nero e **P5** è violata.

#### **Il nodo "doppiamente nero"**

Per formalizzare la riparazione si attribuisce a $x$ un **nero in eccesso**: $x$ diventa concettualmente "doppiamente nero" (o "rosso-e-nero", se era rosso). Questo colore fittizio non è legale e va eliminato spingendolo verso l'alto o scaricandolo.

Il nero in eccesso sparisce in tre modi:

1. $x$ diventa la **radice**: lo si scarta e l'altezza nera dell'intero albero cala di uno;
2. $x$ è **rosso-e-nero**: lo si colora di nero e si termina;
3. tramite **rotazioni e ricolorazioni** che prendono in prestito un nero dal fratello.

#### **I quattro casi del ripristino**

Sia $x$ il nodo doppiamente nero e $w$ il suo **fratello**. Il fratello esiste sempre e non può essere `NIL`: se lo fosse, il ramo di $w$ avrebbe altezza nera inferiore a quello di $x$, contro P5.

| Caso | Condizione su $w$ | Azione | Termina? |
|---|---|---|---|
| **A** | $w$ è **rosso** | scambio colori $w$↔padre + rotazione sul padre | no, riconduce agli altri |
| **B** | $w$ nero, **entrambi i figli neri** | $w$ → rosso; il nero in eccesso sale al padre | **no**, si ripete |
| **C** | $w$ nero, figlio **vicino** rosso e lontano nero | scambio colori $w$↔figlio vicino + rotazione su $w$ | no, riconduce al caso D |
| **D** | $w$ nero, figlio **lontano** rosso | $w$ prende il colore del padre; padre e figlio lontano → neri; rotazione sul padre | **sì** |

Per "figlio vicino" si intende quello di $w$ dalla parte di $x$, per "figlio lontano" l'altro.

#### **Analisi dei quattro casi**

**Caso A — fratello rosso.** Poiché $w$ è rosso, il padre e i figli di $w$ sono necessariamente neri (P4). Scambiando i colori di $w$ e del padre e ruotando sul padre, il nuovo fratello di $x$ diventa un figlio di $w$, che è **nero**. Il nero in eccesso resta su $x$, ma ora si ricade in uno dei casi B, C o D.

> [!note]
> Il caso A si applica **al massimo una volta** per cancellazione: dopo la rotazione il fratello è certamente nero e non può tornare rosso.

**Caso B — fratello nero con figli neri.** Si toglie un nero al fratello colorandolo di rosso. Ora entrambi i sottoalberi del padre hanno perso un nero e sono di nuovo bilanciati **fra loro**, ma il difetto si è trasferito al padre: il procedimento riprende con $x$ = padre.

Se il padre era **rosso**, alla iterazione successiva si esce immediatamente colorandolo di nero (è il caso "rosso-e-nero").

> [!warning]
> Il caso B è l'unico che **risale** verso la radice, per un massimo di $O(\log n)$ iterazioni. È il duale del caso 1 dell'inserimento.

**Caso C — figlio vicino rosso.** Si scambiano i colori di $w$ e del suo figlio vicino, poi si ruota su $w$ nella direzione di $x$. Il rosso si sposta dalla parte **lontana** e ci si riconduce al caso D. Anche il caso C è puramente preparatorio.

**Caso D — figlio lontano rosso.** È il caso terminale. Il fratello $w$ assume il colore del padre; il padre e il figlio lontano di $w$ diventano neri; si ruota sul padre nella direzione di $x$.

L'effetto netto: il ramo di $x$, che era povero di un nero, ne riceve uno; il ramo del fratello, che ne aveva uno in eccesso dalla parte lontana, lo cede. Il nodo rosso lontano, ricolorato di nero, compensa la perdita. Il ripristino **termina**.

> [!important]
> Il caso D è un vero **prestito fra fratelli**, l'analogo esatto della ridistribuzione fra nodi adiacenti nei B-alberi. Il caso B, che non trova nulla da prendere in prestito, corrisponde invece alla **fusione** di due nodi con propagazione verso l'alto.

#### **Schema decisionale**

Dato $x$ doppiamente nero con fratello $w$:

1. $x$ è la radice → si scarta il nero in eccesso, **fine**;
2. $x$ è rosso-e-nero → lo si colora di nero, **fine**;
3. $w$ è **rosso** → **caso A**, poi si rivaluta;
4. $w$ nero con **entrambi i figli neri** → **caso B**, si sale e si ripete;
5. $w$ nero con **figlio vicino rosso, lontano nero** → **caso C**, poi caso D;
6. $w$ nero con **figlio lontano rosso** → **caso D**, **fine**.

> [!warning]
> La numerazione dei casi **varia fra le fonti**. Nella formulazione classica di Cormen, Leiserson, Rivest e Stein (CLRS) l'ordine è: caso 1 = fratello rosso (qui A), caso 2 = fratello nero con figli neri (qui B), caso 3 = figlio vicino rosso (qui C), caso 4 = figlio lontano rosso (qui D). In sede d'esame conviene descrivere le **condizioni** anziché citare il numero: la condizione è univoca, il numero dipende dal testo.

---

### **7. Complessità degli operatori**

| Operatore | Rotazioni | Ricolorazioni | Costo totale |
|---|---:|---|---|
| `appartiene` | $0$ | — | $O(\log n)$ |
| `min`, `max` | $0$ | — | $O(\log n)$ |
| `successore`, `predecessore` | $0$ | — | $O(\log n)$ |
| `inserisci` | al più **2** | fino a $O(\log n)$ | $O(\log n)$ |
| `cancella` | al più **3** | fino a $O(\log n)$ | $O(\log n)$ |
| visita simmetrica | $0$ | — | $\Theta(n)$ |

#### **Perché il numero di rotazioni è costante**

Nell'**inserimento**, i casi 2 e 3 terminano il ripristino ed eseguono in tutto al più due rotazioni; il caso 1 non ne esegue nessuna, pur potendo ripetersi molte volte. Nella **cancellazione**, i casi A, C e D eseguono una rotazione ciascuno e la sequenza più lunga possibile è A → C → D, per un totale di tre; il caso B, che si ripete, non ruota.

> [!important]
> È questa asimmetria — **poche rotazioni, molte ricolorazioni** — a rendere gli alberi rosso-neri preferibili agli AVL nelle applicazioni con molti aggiornamenti. Una ricolorazione modifica un bit; una rotazione riscrive sei puntatori e, su strutture persistenti o concorrenti, costa molto di più.

#### **Occupazione di memoria**

Ogni nodo richiede: chiave, puntatore al figlio sinistro, puntatore al figlio destro, puntatore al padre e **un bit** di colore. Nella pratica il bit viene spesso nascosto nel bit meno significativo di un puntatore (sfruttando l'allineamento degli indirizzi), rendendo l'occupazione identica a quella di un BST ordinario.

---

### **8. Confronto con le altre strutture**

| | BST non bilanciato | Rosso-nero | AVL | B-albero | Tabella hash |
|---|---|---|---|---|---|
| Ricerca (peggiore) | $O(n)$ | $O(\log n)$ | $O(\log n)$ | $O(\log_m n)$ | $O(n)$ |
| Ricerca (medio) | $O(\log n)$ | $O(\log n)$ | $O(\log n)$ | $O(\log_m n)$ | $O(1)$ |
| Altezza garantita | nessuna | $\le 2\log_2(n+1)$ | $\le 1.44\log_2 n$ | $\log_m n$ | — |
| Rotazioni in inserimento | — | $\le 2$ | $\le 2$ | — | — |
| Rotazioni in cancellazione | — | $\le 3$ | $O(\log n)$ | — | — |
| Ordinamento delle chiavi | ✅ | ✅ | ✅ | ✅ | ✗ |
| `min`, `max`, `successore` | $O(h)$ | $O(\log n)$ | $O(\log n)$ | $O(\log_m n)$ | $O(n)$ |
| Adatto a memoria secondaria | ✗ | ✗ | ✗ | ✅ | ✗ |

#### **Quando scegliere quale**

- **Rosso-nero**: struttura ordinata di uso generale, con inserimenti e cancellazioni frequenti. È la scelta di default delle librerie standard.
- **AVL**: quando le ricerche prevalgono nettamente sugli aggiornamenti; il bilanciamento più stretto ripaga il costo delle ristrutturazioni.
- **B-albero**: quando i dati risiedono su **memoria secondaria**; il fattore di ramificazione elevato minimizza gli accessi a disco.
- **Tabella hash**: quando servono solo `inserisci`, `cancella` e `appartiene`, e **non** interessano l'ordinamento né `min`/`max`/`successore`.

> [!note]
> Il confronto decisivo con la tabella hash non è sul costo medio — dove l'hash vince, $O(1)$ contro $O(\log n)$ — ma sulle **funzionalità**: la tabella hash non conserva alcun ordine, quindi non può rispondere a interrogazioni come "qual è il minimo", "qual è l'elemento successivo a $x$" o "elencami tutte le chiavi fra $a$ e $b$". Se servono queste operazioni, l'albero bilanciato è l'unica scelta.

#### **La corrispondenza con gli alberi 2-3-4**

Esiste un'equivalenza esatta fra alberi rosso-neri e **alberi 2-3-4** (B-alberi di ordine $4$): ogni nodo nero, insieme ai suoi eventuali figli rossi, corrisponde a un nodo dell'albero 2-3-4.

| Nodo 2-3-4 | Configurazione rosso-nera |
|---|---|
| nodo con **1 chiave** (2-nodo) | un nodo nero senza figli rossi |
| nodo con **2 chiavi** (3-nodo) | un nodo nero con **un** figlio rosso |
| nodo con **3 chiavi** (4-nodo) | un nodo nero con **due** figli rossi |

Sotto questa lettura:

- l'**altezza nera** del rosso-nero è esattamente l'altezza dell'albero 2-3-4 corrispondente;
- la proprietà **P5** corrisponde al fatto che nel B-albero tutte le foglie sono alla stessa profondità;
- il **caso 1 dell'inserimento** (zio rosso) corrisponde allo **split** di un 4-nodo;
- il **caso B della cancellazione** corrisponde alla **fusione** di due nodi.

> [!important]
> Questa corrispondenza spiega *perché* le cinque proprietà sono quelle e non altre: l'albero rosso-nero è la rappresentazione binaria di un albero 2-3-4, e i colori codificano quali nodi binari appartengono allo stesso nodo del B-albero. Le regole sui colori non sono un'invenzione arbitraria, ma la traduzione delle regole del B-albero.

---

### **9. Sintesi finale**

- Un **albero rosso-nero** è un albero binario di ricerca che aggiunge a ogni nodo un bit di colore e rispetta **cinque proprietà**, la più importante delle quali (**P5**) impone che tutti i cammini da un nodo alle foglie contengano lo stesso numero di nodi neri.
- Le proprietà garantiscono $h \le 2\log_2(n+1)$: tutti gli operatori costano $O(\log n)$ **nel caso peggiore**, non solo in media.
- Il nuovo nodo viene inserito **rosso** per non alterare l'altezza nera; l'unica violazione possibile è quella dei due rossi consecutivi, riparata da **tre casi** che dipendono dal colore dello zio.
- La cancellazione introduce il concetto di **nodo doppiamente nero** e lo elimina con **quattro casi** che dipendono dal colore del fratello e dei suoi figli.
- Le **rotazioni** sono al più $2$ in inserimento e $3$ in cancellazione: un numero **costante**, contro le $O(\log n)$ ricolorazioni.
- Gli alberi rosso-neri sono equivalenti agli **alberi 2-3-4**: i colori codificano l'appartenenza allo stesso nodo del B-albero, e i casi di ripristino corrispondono a split e fusione.
- Rispetto alla **tabella hash**, sono più lenti in media ma mantengono l'**ordinamento** delle chiavi, indispensabile per `min`, `max`, `successore` e interrogazioni su intervalli.

> [!important]
> **Da ricordare per l'esame.** Le cinque proprietà; la dimostrazione $h \le 2\log_2(n+1)$ in tre passi; la ragione per cui il nuovo nodo è rosso; i tre casi dell'inserimento distinti dal **colore dello zio**; i quattro casi della cancellazione distinti dal **colore del fratello e dei suoi figli**; il fatto che il numero di rotazioni sia costante mentre le ricolorazioni sono logaritmiche.

> [!note]
> Esercizi svolti su questa struttura si trovano nel modulo M13: [esercitazione del 31 ottobre 2025](../../M13_EserciziDellaProf/Soluzione_Esercitazione_31_10_2025.md), con quattordici inserimenti e lo schema completo dei casi di cancellazione, e [esercitazione del 1 dicembre 2025](../../M13_EserciziDellaProf/Soluzione_Esercitazione_01_12_2025.md), che affianca alberi rosso-neri e AVL sulle stesse chiavi.
