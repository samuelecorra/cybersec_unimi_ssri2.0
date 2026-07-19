## ***Lezione 3: Circuiti moltiplicatori***

---

> 📌 Questa lezione rielabora integralmente le pagine 37–41 di `M4doc.pdf`: algoritmo binario in colonna, matrice dei prodotti parziali, struttura della cella AND/full-adder, propagazioni e ritardo del moltiplicatore combinatorio.

### **1. Perché il prodotto è più complesso della somma**

Oltre alla somma, la moltiplicazione è un'operazione aritmetica fondamentale dell'ALU. Un sommatore deve gestire un riporto lungo una sola catena; un moltiplicatore deve prima produrre numerosi **prodotti parziali**, allinearli secondo il loro peso e poi sommarli.

Per due operandi senza segno da $n$ bit:

$$
M=\sum_{j=0}^{n-1}m_j2^j,
\qquad
Q=\sum_{i=0}^{n-1}q_i2^i.
$$

Il prodotto è:

$$
P=M Q
=\sum_{i=0}^{n-1}\sum_{j=0}^{n-1}q_im_j2^{i+j}.
$$

Ogni termine $q_im_j$ è l'AND di due bit e appartiene alla colonna di peso $2^{i+j}$. Il risultato completo può richiedere $2n$ bit.

### **2. Esempio di moltiplicazione binaria in colonna**

Il PDF usa due operandi a 4 bit:

$$
M=1101_2=13,
\qquad
Q=1011_2=11.
$$

Il prodotto atteso è:

$$
13\cdot11=143=10001111_2.
$$

La regola è la stessa della moltiplicazione decimale in colonna. Si considera un bit del moltiplicatore alla volta, iniziando dal meno significativo:

| Bit di $Q$ | Valore | Prodotto parziale, già allineato su 8 bit |
| --- | ---: | --- |
| $q_0$ | 1 | `00001101` |
| $q_1$ | 1 | `00011010` |
| $q_2$ | 0 | `00000000` |
| $q_3$ | 1 | `01101000` |

La somma dei prodotti parziali è:

$$
00001101+00011010+00000000+01101000=10001111.
$$

Ad ogni riga, lo spostamento a sinistra di una posizione moltiplica il prodotto parziale per 2. Se $q_i=0$ la riga contiene zeri; se $q_i=1$ contiene una copia di $M$ spostata di $i$ posizioni.

> 💡 I bit del moltiplicatore selezionano intere righe; i bit del moltiplicando, combinati con tali selezioni, si dispongono lungo diagonali di uguale peso.

### **3. Regola di calcolo della matrice**

L'algoritmo può essere espresso in tre passaggi:

1. costruire la matrice diagonale dei prodotti parziali $q_im_j$;
2. sommare per colonne tutti i termini dello stesso peso $2^{i+j}$;
3. trasferire ogni riporto alla colonna immediatamente più significativa, cioè verso sinistra nella scrittura convenzionale.

Per un moltiplicatore $n\times n$ esistono $n^2$ prodotti parziali, quindi il circuito combinatorio regolare usa una matrice di $n^2$ celle.

### **4. La cella elementare**

Ogni incrocio della matrice deve compiere più di una semplice moltiplicazione di bit. La cella riceve:

- un bit $m_j$ del moltiplicando;
- un bit $q_i$ del moltiplicatore;
- un bit di somma proveniente dalla cella soprastante nella stessa colonna;
- un riporto proveniente dalla colonna immediatamente meno significativa, posta a destra.

Contiene:

1. una porta AND, che produce il prodotto parziale

$$
x_{i,j}=q_im_j;
$$

2. un full-adder, che calcola

$$
(s_{out},c_{out})=x_{i,j}+s_{in}+c_{in}.
$$

Le uscite vengono instradate così:

- $s_{out}$ si propaga verso il basso nella stessa colonna;
- $c_{out}$ va verso sinistra, alla colonna più significativa;
- il bit $q_i$ prosegue orizzontalmente sulla propria riga;
- il bit $m_j$ prosegue diagonalmente verso la cella sottostante e a sinistra.

La combinazione AND più full-adder realizza dunque nello stesso punto il prodotto parziale e il suo accumulo.

### **5. Collegamento delle celle**

Nella matrice completa:

- i bit del moltiplicando entrano nella prima riga e avanzano diagonalmente;
- i bit del moltiplicatore entrano nella prima colonna obliqua e avanzano sulla propria riga;
- i riporti iniziali valgono 0;
- anche i bit di somma che entrano nella prima riga sono inizializzati a 0.

Quando un riporto raggiunge la cella più a sinistra di una riga, non esiste una cella adiacente nella stessa riga. Il riporto viene allora trattato come bit di somma della colonna immediatamente più significativa e prosegue verso il basso.

Nel caso $4\times4$, il prodotto è composto da $p_7\ldots p_0$:

- i primi tre bit meno significativi emergono dalle prime tre colonne concluse;
- i quattro bit successivi emergono dalle colonne della riga finale;
- il bit più significativo è il riporto finale del full-adder più significativo.

Questa distribuzione non aggiunge bit arbitrari: ogni uscita corrisponde a una colonna di peso determinato nella somma dei prodotti parziali.

### **6. Verifica dei pesi**

Il prodotto parziale nella cella $(i,j)$ ha peso:

$$
2^i\cdot2^j=2^{i+j}.
$$

Per esempio, $q_2m_3$ appartiene alla colonna di peso $2^5$. Se la somma di quella colonna genera un riporto, il riporto vale due volte il peso corrente e deve quindi andare nella colonna $2^6$.

> ⚠️ Spostare un prodotto parziale nella direzione sbagliata cambia il suo peso. La riga associata a $q_i$ deve essere traslata di esattamente $i$ posizioni verso i bit più significativi.

### **7. Numero di celle e area**

Con due operandi da $n$ bit, ogni bit del moltiplicatore deve essere combinato con ogni bit del moltiplicando:

$$
N_{celle}=n\cdot n=n^2.
$$

Ogni cella include almeno una AND e un full-adder. L'area cresce quindi quadraticamente con la larghezza degli operandi, molto più rapidamente rispetto al sommatore lineare da $n$ celle.

### **8. Ritardo della rete combinatoria**

Il PDF valuta il cammino critico contando i livelli logici:

- la generazione iniziale dei prodotti parziali tramite AND richiede 1 livello;
- ogni full-adder ottimizzato aggiunge 2 livelli;
- il segnale critico attraversa $n$ celle lungo la prima riga;
- deve poi attraversare altre $n-1$ celle scendendo lungo la diagonale.

Il totale è:

$$
N_{livelli}
=1+2\bigl(n+(n-1)\bigr)
=4n-1.
$$

Per $n=4$:

$$
N_{livelli}=4\cdot4-1=15.
$$

Il tempo cresce linearmente con $n$ lungo il cammino critico, mentre l'area cresce come $n^2$. Rispetto ai circuiti sommatori, il prodotto completo richiede quindi una rete più grande e un'attesa notevole.

### **9. Interpretazione architetturale**

La matrice descritta è un moltiplicatore combinatorio didattico: operandi stabili in ingresso producono, dopo la propagazione, tutti i $2n$ bit del prodotto. La sua regolarità è adatta alla realizzazione hardware, ma il costo può diventare elevato.

Una progettazione reale deve scegliere fra area, latenza e throughput. Un circuito iterativo può riusare meno adders per più cicli; una rete parallela come quella studiata occupa più area ma evita di ripetere esplicitamente l'algoritmo tramite istruzioni software. Le evoluzioni volte ad abbreviare i cammini di somma e prodotto saranno riprese nel modulo successivo, come anticipato dal PDF.

> ✅ La moltiplicazione binaria nasce da $n^2$ AND, allineamento per peso e somme con riporto. La matrice di celle AND/full-adder traduce direttamente l'algoritmo in colonna in una rete regolare, con profondità critica $4n-1$ nel modello analizzato.
