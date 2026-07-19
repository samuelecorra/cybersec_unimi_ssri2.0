# **M3 UD2 Lezione 1A - Stabilità della numerabilità e applicazioni**

### **1. Perché servono teoremi di stabilità**

Per dimostrare che un insieme è al più numerabile non è sempre necessario trovare subito una formula biiettiva. Spesso basta rappresentarlo come immagine, unione o prodotto di insiemi già noti, controllando duplicati e scelte effettuate.

### **2. Unioni finite e numerabili**

Un'unione finita di insiemi finiti è finita. Un'unione finita di insiemi al più numerabili è al più numerabile: si possono alternare le enumerazioni dei vari insiemi e poi eliminare le ripetizioni.

Il risultato fondamentale è più generale.

**Teorema.** Sia $(A_n)_{n\in\mathbb N}$ una famiglia di insiemi al più numerabili, ciascuno accompagnato da un'enumerazione esplicita. Allora:

$$
\bigcup_{n\in\mathbb N}A_n
$$

è al più numerabile.

**Dimostrazione.** Poniamo $I=\{n\in\mathbb N:A_n\neq\varnothing\}$. Se $I=\varnothing$, l'unione è vuota. Altrimenti lavoriamo sul sottoinsieme al più numerabile $I$ degli indici non vuoti. Per ogni $n\in I$ scegliamo una suriezione:

$$
e_n:\mathbb N\to A_n.
$$

Se $A_n$ è finito e non vuoto, possiamo ripeterne ciclicamente gli elementi. Definiamo:

$$
E:I\times\mathbb N\to\bigcup_{n\in\mathbb N}A_n,
\qquad
E(n,k)=e_n(k).
$$

La funzione $E$ è suriettiva: ogni $a$ appartiene ad almeno un $A_n$ e quindi è $e_n(k)$ per qualche $k$. Poiché $I\times\mathbb N$ è al più numerabile, anche la sua immagine mediante $E$ è al più numerabile. Le eventuali ripetizioni non compromettono il risultato. $\square$

Se gli $A_n$ sono dati soltanto come insiemi al più numerabili, senza enumerazioni fissate, scegliere simultaneamente una enumerazione per ciascuno usa una forma di **scelta numerabile**. In ZFC il teorema vale nella forma usuale; nelle applicazioni concrete le enumerazioni sono quasi sempre costruite esplicitamente.

### **3. Prodotti finiti e unioni numerabili**

Per ogni $k\geq1$ finito:

$$
|\mathbb N^k|=\aleph_0.
$$

Di conseguenza, ogni prodotto finito di insiemi al più numerabili è al più numerabile. Attenzione alla parola “finito”: l'insieme di tutte le successioni infinite di naturali, $\mathbb N^{\mathbb N}$, non è numerabile.

### **4. Numerabilità di $\mathbb Q$**

Consideriamo:

$$
D=\mathbb Z\times\mathbb N_{>0}.
$$

$D$ è numerabile perché è prodotto di due insiemi numerabili. La funzione:

$$
q:D\to\mathbb Q,
\qquad
q(p,r)=\frac{p}{r},
$$

è suriettiva, quindi $\mathbb Q=q(D)$ è al più numerabile.

La funzione $n\mapsto n/1$ è un'iniezione $\mathbb N\to\mathbb Q$, perciò $\mathbb Q$ è infinito. Concludiamo:

$$
|\mathbb Q|=\aleph_0.
$$

Questa dimostrazione gestisce correttamente le duplicazioni:

$$
\frac12=\frac24=\frac36.
$$

La griglia delle frazioni produce una suriezione, non automaticamente una biiezione. In alternativa si possono conservare soltanto le coppie $(p,r)$ con $r>0$ e $\gcd(|p|,r)=1$, ottenendo rappresentazioni ridotte uniche.

<!-- TODO FIGURA:
Disegnare una griglia con numeratori interi sulle righe e denominatori positivi sulle colonne; mostrare un percorso diagonale che visita tutte le coppie (p,r). Collegare con lo stesso colore le celle (1,2), (2,4), (3,6) per evidenziare le frazioni duplicate e indicare due strategie: saltare le ripetizioni oppure accettare una suriezione. Obiettivo didattico: chiarire perché la griglia prova l'al più numerabilità di Q ma non è di per sé una biiezione.
-->

> 💡 $\mathbb Q$ è denso in $\mathbb R$: tra due reali distinti esiste un razionale. Tuttavia è numerabile. La densità descrive la posizione nell'ordine, non la cardinalità.

### **5. Stringhe finite su un alfabeto finito**

Sia $\Sigma$ un alfabeto finito non vuoto. Indichiamo con $\Sigma^n$ l'insieme delle stringhe di lunghezza $n$ e con:

$$
\Sigma^*=\bigcup_{n=0}^{\infty}\Sigma^n
$$

l'insieme di tutte le stringhe finite, inclusa la stringa vuota $\varepsilon\in\Sigma^0$.

Se $|\Sigma|=s$, allora:

$$
|\Sigma^n|=s^n.
$$

Ogni livello è finito e $\Sigma^*$ è unione numerabile di insiemi finiti; dunque è al più numerabile. Se $\Sigma\neq\varnothing$, le stringhe $\varepsilon,a,aa,aaa,\ldots$ mostrano che è infinito, quindi:

$$
|\Sigma^*|=\aleph_0.
$$

Una enumerazione concreta ordina prima per lunghezza e, all'interno di ogni lunghezza, lessicograficamente.

<!-- TODO FIGURA:
Rappresentare le stringhe su Sigma={0,1} per livelli di lunghezza: livello 0 con epsilon, livello 1 con 0 e 1, livello 2 con 00,01,10,11, e così via. Inserire una freccia di lettura da sinistra a destra e poi al livello successivo; a lato contrapporre una singola colonna infinita che rappresenti una successione binaria senza lunghezza finale. Obiettivo didattico: mostrare perché l'unione dei livelli finiti è numerabile e distinguere Sigma* dalle successioni infinite.
-->

### **6. Sequenze finite di naturali**

L'insieme di tutte le sequenze finite di naturali è:

$$
\mathbb N^{<\mathbb N}
=
\bigcup_{k\in\mathbb N}\mathbb N^k.
$$

Ogni $\mathbb N^k$ è al più numerabile e l'unione è numerabile; pertanto:

$$
|\mathbb N^{<\mathbb N}|=\aleph_0.
$$

Il simbolo $<\mathbb N$ indica che ogni singola sequenza ha lunghezza finita, anche se non esiste un limite comune alle lunghezze.

### **7. Linguaggi, programmi e oggetti finiti**

Ogni linguaggio $L\subseteq\Sigma^*$ è al più numerabile perché è sottoinsieme di un insieme numerabile. L'insieme di **tutti** i linguaggi su $\Sigma$, invece, è:

$$
\mathcal P(\Sigma^*),
$$

e sarà dimostrato non numerabile.

Ogni programma scritto in un linguaggio con alfabeto finito è una stringa finita che soddisfa certe regole sintattiche. L'insieme dei programmi è quindi al più numerabile. Questo non implica che ogni funzione sia calcolabile: le funzioni $\mathbb N\to\{0,1\}$ sono non numerabili, come vedremo con la diagonale di Cantor.

### **8. Finito contro infinito**

Per l'alfabeto binario distinguiamo:

$$
\{0,1\}^*
$$

delle parole finite da:

$$
\{0,1\}^{\mathbb N}
$$

delle successioni infinite. Una parola come $0101$ appartiene al primo insieme; una funzione $s:\mathbb N\to\{0,1\}$, con una cifra per ogni indice naturale, appartiene al secondo.

Il primo insieme è numerabile. Il secondo non lo è. La differenza non è una notazione secondaria: è il passaggio da oggetti terminanti, enumerabili per lunghezza, a oggetti con infinitamente molte coordinate.

### **9. Errori frequenti**

- Sostenere che una unione numerabile è numerabile senza enumerare gli insiemi componenti o dichiarare la scelta necessaria.
- Trattare la griglia delle frazioni come biiezione ignorando le rappresentazioni duplicate.
- Confondere $\Sigma^*$ con $\Sigma^{\mathbb N}$.
- Concludere che l'insieme di tutti i linguaggi è numerabile perché ogni linguaggio è al più numerabile.
- Confondere un prodotto finito $\mathbb N^k$ con il prodotto numerabile $\mathbb N^{\mathbb N}$.

### **10. Esercizi**

1. Dimostrare che l'insieme dei polinomi a coefficienti interi è numerabile, codificando un polinomio mediante una sequenza finita di interi.
2. Dimostrare che l'insieme delle matrici quadrate di ordine finito a coefficienti razionali è numerabile.
3. Costruire una suriezione esplicita da $\mathbb N^2$ a $\mathbb Q_{\geq0}$ e indicare dove compaiono duplicati.
4. Enumerare le prime quindici stringhe di $\{0,1\}^*$ in ordine di lunghezza e poi lessicografico.
5. Spiegare perché l'insieme delle stringhe di lunghezza al più $n$ è finito e calcolarne la cardinalità per un alfabeto di $s>1$ simboli.
6. Problema trasversale: classificare l'insieme delle configurazioni finite di una macchina astratta, assumendo che ogni configurazione sia descritta da una stringa finita e da una tupla finita di naturali. Costruire le codifiche necessarie e indicare quali teoremi di stabilità vengono usati.

### **11. Riepilogo**

> ✅ Immagini, sottoinsiemi, prodotti finiti e unioni numerabili preservano l'al più numerabilità nelle ipotesi dichiarate.

- un'unione numerabile si enumera mediante coppie $(n,k)$;
- $\mathbb Q$ è immagine suriettiva di $\mathbb Z\times\mathbb N_{>0}$ ed è infinito;
- $\Sigma^*$ e $\mathbb N^{<\mathbb N}$ sono numerabili;
- i duplicati sono compatibili con una suriezione, non con una biiezione;
- la scelta numerabile va distinta dalle costruzioni esplicite;
- stringhe finite e successioni infinite hanno cardinalità diverse.
