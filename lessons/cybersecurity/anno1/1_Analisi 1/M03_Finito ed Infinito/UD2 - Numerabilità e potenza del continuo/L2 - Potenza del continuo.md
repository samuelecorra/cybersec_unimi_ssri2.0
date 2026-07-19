# **M3 UD2 Lezione 2 - Potenza del continuo**

### **1. Non tutti gli infiniti sono numerabili**

I naturali, gli interi e i razionali sono infiniti numerabili. Per dimostrare che esistono cardinalità maggiori non basta osservare che un insieme è denso o “riempie una linea”: serve provare che ogni possibile enumerazione fallisce.

La strategia centrale è la **diagonale di Cantor**: data una presunta lista completa, si costruisce un oggetto che differisce dal termine di indice $n$ nella coordinata $n$.

### **2. Successioni binarie infinite**

Indichiamo con:

$$
\{0,1\}^{\mathbb N}
$$

l'insieme di tutte le funzioni:

$$
s:\mathbb N\to\{0,1\}.
$$

Ogni $s$ è una successione infinita $s(0),s(1),s(2),\ldots$. Questo insieme è diverso da $\{0,1\}^*$, formato da stringhe finite.

**Teorema.** $\{0,1\}^{\mathbb N}$ non è numerabile.

**Dimostrazione.** Supponiamo per assurdo che esista una suriezione:

$$
E:\mathbb N\to\{0,1\}^{\mathbb N}.
$$

Scriviamo $s_n=E(n)$. Definiamo una nuova successione $d:\mathbb N\to\{0,1\}$ ponendo:

$$
d(n)=1-s_n(n).
$$

Per ogni $n$ si ha:

$$
d(n)\neq s_n(n),
$$

quindi $d\neq s_n$. Pertanto $d$ non appartiene all'immagine di $E$, contro la suriettività. Nessuna enumerazione può essere completa. $\square$

<!-- TODO FIGURA:
Costruire una matrice infinita schematica con riga n uguale alla successione s_n e colonna k uguale alla coordinata k. Evidenziare le celle diagonali s_n(n), mostrare sotto la matrice la successione d ottenuta invertendo ciascuna cifra diagonale e tracciare una freccia dalla n-esima cifra di d alla riga n che essa rende diversa. Obiettivo didattico: visualizzare che d è un oggetto ben definito e che differisce da ogni riga in almeno una posizione specifica.
-->

### **3. Insiemi delle parti e funzioni caratteristiche**

Per ogni insieme $A$ esiste una biiezione:

$$
\Phi:\mathcal P(A)\to\{0,1\}^A,
\qquad
\Phi(S)=\chi_S,
$$

dove:

$$
\chi_S(a)=
\begin{cases}
1,&a\in S,\\
0,&a\notin S.
\end{cases}
$$

L'inversa associa a una funzione $u:A\to\{0,1\}$ il sottoinsieme:

$$
\{a\in A:u(a)=1\}.
$$

Quindi:

$$
|\mathcal P(A)|=|\{0,1\}^A|=2^{|A|}.
$$

Nel caso $A=\mathbb N$, una successione binaria codifica esattamente il sottoinsieme degli indici in cui compare $1$.

### **4. Teorema di Cantor**

**Teorema.** Per ogni insieme $A$:

$$
|A|<|\mathcal P(A)|.
$$

**Dimostrazione.** La funzione:

$$
i:A\to\mathcal P(A),
\qquad
i(a)=\{a\},
$$

è iniettiva, quindi $|A|\leq|\mathcal P(A)|$.

Resta da mostrare che non esiste alcuna suriezione $f:A\to\mathcal P(A)$. Supponiamo che $f$ sia suriettiva e definiamo l'insieme diagonale:

$$
D=\{a\in A:a\notin f(a)\}.
$$

Poiché $D\subseteq A$, si ha $D\in\mathcal P(A)$. Per la suriettività dovrebbe esistere $d\in A$ tale che:

$$
f(d)=D.
$$

Applicando la definizione di $D$ proprio a $d$ otteniamo:

$$
d\in D
\iff
d\notin f(d)
\iff
d\notin D,
$$

una contraddizione. Quindi nessuna suriezione $A\to\mathcal P(A)$ esiste; in particolare non esiste una biiezione. $\square$

La dimostrazione comprende anche $A=\varnothing$: l'iniezione vuota esiste, mentre $\mathcal P(\varnothing)=\{\varnothing\}$ non può essere immagine di una funzione con dominio vuoto.

<!-- TODO FIGURA:
Rappresentare una tabella in cui ogni riga a di A descrive mediante caselle sì/no il sottoinsieme f(a) di A. Evidenziare la diagonale che registra se a appartiene a f(a) e costruire D scegliendo esattamente gli elementi per cui la casella diagonale è no. Mostrare la contraddizione nella presunta riga d con f(d)=D. Obiettivo didattico: collegare il teorema di Cantor alla diagonale senza confondere D con un insieme globale di tutti gli insiemi.
-->

> ⚠️ L'errore tipico è dire soltanto “$D$ non è nella lista”. Occorre prima verificare $D\subseteq A$, usare la suriettività per porre $D=f(d)$ e poi analizzare l'appartenenza dell'elemento $d$.

### **5. Cardinalità di $\mathcal P(\mathbb N)$**

Dal teorema di Cantor:

$$
|\mathbb N|<|\mathcal P(\mathbb N)|.
$$

Poiché $\mathcal P(\mathbb N)\sim\{0,1\}^{\mathbb N}$, scriviamo:

$$
|\mathcal P(\mathbb N)|
=
2^{\aleph_0}.
$$

La diagonale binaria è quindi un caso concreto dello stesso ostacolo strutturale espresso dal teorema di Cantor.

### **6. La cardinalità dei reali**

La cardinalità di $\mathbb R$ si chiama **cardinalità del continuo** e si indica con:

$$
\mathfrak c=|\mathbb R|.
$$

Dimostriamo il risultato fondamentale:

$$
\mathfrak c=2^{\aleph_0}.
$$

#### **6.1 Iniezione da $\mathcal P(\mathbb N)$ a $\mathbb R$**

Per $S\subseteq\mathbb N$ definiamo:

$$
F(S)=\sum_{n=0}^{\infty}\frac{2\chi_S(n)}{3^{n+1}}.
$$

Questa serie rappresenta un numero del Cantor ternario in $[0,1]$. Se $S\neq T$, sia $k$ il primo indice in cui le funzioni caratteristiche differiscono. Il contributo in posizione $k$ vale $2/3^{k+1}$, mentre la massima differenza possibile della coda successiva è:

$$
\sum_{n=k+1}^{\infty}\frac{2}{3^{n+1}}
=
\frac{1}{3^{k+1}}.
$$

La prima differenza non può essere annullata dalla coda; dunque $F(S)\neq F(T)$. Quindi $F$ è iniettiva e:

$$
2^{\aleph_0}\leq\mathfrak c.
$$

#### **6.2 Iniezione da $\mathbb R$ a $\mathcal P(\mathbb N)$**

Fissiamo un'enumerazione biiettiva $(q_n)_{n\in\mathbb N}$ di $\mathbb Q$. A ogni $x\in\mathbb R$ associamo:

$$
G(x)=\{n\in\mathbb N:q_n<x\}.
$$

Se $x<y$, per la densità di $\mathbb Q$ esiste $q_n$ con $x<q_n<y$. Allora $n\notin G(x)$ ma $n\in G(y)$, quindi $G(x)\neq G(y)$. La funzione $G$ è iniettiva e:

$$
\mathfrak c\leq2^{\aleph_0}.
$$

Per Cantor-Bernstein:

$$
\mathfrak c=2^{\aleph_0}.
$$

Poiché $2^{\aleph_0}>\aleph_0$, $\mathbb R$ non è numerabile.

### **7. Perché evitiamo la diagonale decimale**

Una dimostrazione sugli sviluppi decimali deve gestire la non unicità:

$$
0{,}4999\ldots=0{,}5000\ldots.
$$

Scegliere semplicemente una cifra diversa dalla diagonale può produrre un numero già presente con un'altra rappresentazione. Si può correggere il metodo usando soltanto cifre, per esempio, $1$ e $2$, oppure fissando una rappresentazione canonica. La diagonale sulle successioni binarie evita del tutto questo problema perché confronta funzioni, non rappresentazioni numeriche.

### **8. Razionali e irrazionali**

$\mathbb Q$ è numerabile, mentre $\mathbb R$ non lo è. Se anche $\mathbb R\setminus\mathbb Q$ fosse al più numerabile, allora:

$$
\mathbb R
=
\mathbb Q\cup(\mathbb R\setminus\mathbb Q)
$$

sarebbe unione di due insiemi al più numerabili e quindi al più numerabile, assurdo. Pertanto gli irrazionali sono non numerabili.

Questo conferma che densità e cardinalità non coincidono: sia $\mathbb Q$ sia gli irrazionali sono densi in $\mathbb R$, ma soltanto il primo è numerabile.

### **9. Errori frequenti**

- Confondere $\{0,1\}^*$ con $\{0,1\}^{\mathbb N}$.
- Costruire $d$ senza dichiararne dominio, codominio e legge per ogni indice.
- Dimenticare che per negare la numerabilità basta confutare ogni suriezione da $\mathbb N$.
- Usare sviluppi decimali senza gestire le doppie rappresentazioni.
- Confondere $\mathcal P(A)$ con $A\times A$ o $2^{|A|}$ con $2\cdot|A|$.
- Pensare che il teorema di Cantor valga soltanto per insiemi infiniti: vale anche per quelli finiti e per il vuoto.

### **10. Esercizi**

1. Data una presunta enumerazione $(S_n)$ dei sottoinsiemi di $\mathbb N$, costruire $D=\{n:n\notin S_n\}$ e dimostrare che $D\neq S_n$ per ogni $n$.
2. Dimostrare direttamente che l'insieme delle funzioni $\mathbb N\to\{a,b,c\}$ non è numerabile.
3. Verificare l'iniettività della mappa $S\mapsto\chi_S$ e scriverne esplicitamente l'inversa.
4. Spiegare perché l'iniezione $a\mapsto\{a\}$ non basta da sola a dimostrare il teorema di Cantor.
5. Correggere una dimostrazione diagonale decimale che sceglie $b_n=9$ quando $a_{nn}\neq9$ e $b_n=0$ altrimenti.
6. Mostrare che l'insieme di tutte le successioni di interi è non numerabile usando l'inclusione $\{0,1\}^{\mathbb N}\subseteq\mathbb Z^{\mathbb N}$.

### **11. Riepilogo**

> ✅ La diagonale costruisce un oggetto che sfugge a ogni elenco; il teorema di Cantor garantisce che l'insieme delle parti è sempre strettamente più grande.

- $\{0,1\}^{\mathbb N}$ non è numerabile;
- $\mathcal P(A)\sim\{0,1\}^A$;
- $|A|<|\mathcal P(A)|$ per ogni insieme $A$;
- $\mathfrak c=|\mathbb R|=2^{\aleph_0}$;
- la non numerabilità di $\mathbb R$ è dimostrata senza ambiguità decimali;
- gli irrazionali sono non numerabili.
