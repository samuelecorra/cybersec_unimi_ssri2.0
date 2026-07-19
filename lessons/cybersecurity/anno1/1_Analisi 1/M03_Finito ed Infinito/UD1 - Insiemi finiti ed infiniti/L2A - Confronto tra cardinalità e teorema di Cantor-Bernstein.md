# **M3 UD1 Lezione 2A - Confronto tra cardinalità e teorema di Cantor-Bernstein**

### **1. Dalla biiezione al confronto**

Per confrontare insiemi infiniti non possiamo sottrarre o elencare tutti gli elementi. Usiamo invece funzioni con dominio e codominio dichiarati.

**Definizione.** Scriviamo:

$$
|A|\leq|B|
$$

se esiste un'iniezione:

$$
f:A\to B.
$$

Scriviamo:

$$
|A|<|B|
$$

se $|A|\leq|B|$ ma $A$ e $B$ non sono equipotenti.

L'inclusione $A\subseteq B$ produce l'iniezione canonica $a\mapsto a$, ma non decide se valga l'uguaglianza o la disuguaglianza stretta.

### **2. Iniezioni e suriezioni: casi limite**

Se $A\neq\varnothing$, l'esistenza di un'iniezione $i:A\to B$ permette di costruire una suriezione $p:B\to A$: fissato $a_0\in A$, si pone:

$$
p(b)=
\begin{cases}
i^{-1}(b),&b\in i(A),\\
a_0,&b\notin i(A).
\end{cases}
$$

Quindi $|A|\leq|B|$ implica una suriezione $B\to A$ quando $A$ non è vuoto.

Il caso $A=\varnothing$ è speciale: esiste sempre un'iniezione $\varnothing\to B$, ma esiste una funzione $B\to\varnothing$ soltanto se $B=\varnothing$.

Viceversa, da una suriezione $q:B\to A$ si ottiene un'iniezione $A\to B$ scegliendo per ogni $a\in A$ un elemento della fibra $q^{-1}(\{a\})$. Per famiglie arbitrarie questa scelta usa l'assioma della scelta; nei casi finiti o con rappresentanti espliciti la costruzione è diretta.

### **3. Proprietà del confronto cardinale**

La relazione $\leq$ tra cardinalità è:

- **riflessiva**, perché $\operatorname{id}_A$ è iniettiva;
- **transitiva**, perché la composizione di iniezioni è iniettiva;
- **antisimmetrica a livello cardinale**, grazie al teorema seguente.

Non è invece corretto concludere $A\subseteq B$ da $|A|\leq|B|$: la relazione confronta numerosità, non appartenenza a un universo comune.

### **4. Teorema di Cantor-Bernstein-Schröder**

**Teorema.** Siano:

$$
f:A\to B,
\qquad
g:B\to A
$$

due funzioni iniettive. Allora esiste una biiezione $h:A\to B$. In simboli:

$$
|A|\leq|B|
\land
|B|\leq|A|
\Longrightarrow
|A|=|B|.
$$

Il risultato non dice che $g\circ f$ sia la biiezione cercata: tale composizione ha dominio e codominio entrambi in $A$ e può non essere suriettiva.

### **5. Dimostrazione costruttiva**

Definiamo gli insiemi ausiliari:

$$
A_0=A\setminus g(B),
$$

e, ricorsivamente per $n\in\mathbb N$:

$$
A_{n+1}=g(f(A_n)).
$$

Poniamo:

$$
C=\bigcup_{n\in\mathbb N}A_n.
$$

Poiché $g$ è iniettiva, ogni elemento di $g(B)$ ha un'unica controimmagine mediante $g$. Definiamo:

$$
h:A\to B,
\qquad
h(a)=
\begin{cases}
f(a),&a\in C,\\
g^{-1}(a),&a\notin C.
\end{cases}
$$

La seconda riga è ben definita: se $a\notin C$, allora $a\notin A_0=A\setminus g(B)$, dunque $a\in g(B)$.

#### **5.1 Iniettività**

Se due elementi sono entrambi in $C$, l'uguaglianza delle immagini implica la loro uguaglianza perché $f$ è iniettiva. Se sono entrambi fuori da $C$, vale lo stesso per $g^{-1}$.

Resta il caso misto. Siano $a\in C$ e $a'\notin C$. Se $h(a)=h(a')$, allora:

$$
f(a)=g^{-1}(a'),
$$

quindi:

$$
a'=g(f(a)).
$$

Ma $a\in A_n$ per qualche $n$, perciò $a'\in A_{n+1}\subseteq C$, contraddizione. Dunque $h$ è iniettiva.

#### **5.2 Suriettività**

Sia $b\in B$. Se $g(b)\notin C$, allora:

$$
h(g(b))=g^{-1}(g(b))=b.
$$

Se invece $g(b)\in C$, non può appartenere ad $A_0$, perché $A_0\cap g(B)=\varnothing$. Esiste quindi $n$ con:

$$
g(b)\in A_{n+1}=g(f(A_n)).
$$

Per qualche $a\in A_n$ vale $g(b)=g(f(a))$; l'iniettività di $g$ implica $b=f(a)=h(a)$. In entrambi i casi $b$ ha una controimmagine. Quindi $h$ è suriettiva e pertanto biiettiva. $\square$

<!-- TODO FIGURA:
Disegnare il grafo bipartito indotto dalle iniezioni f:A->B e g:B->A come catene alternate. Evidenziare le catene che iniziano in A senza predecessore, corrispondenti ad A_0 e ai suoi iterati, e orientare su esse gli abbinamenti tramite f; sulle altre catene orientare gli abbinamenti tramite g^{-1}. Obiettivo didattico: rendere visibile la suddivisione C/non C usata nella dimostrazione di Cantor-Bernstein e chiarire perché la semplice composizione g composto f non basta.
-->

### **6. Applicazioni**

#### **6.1 Aggiungere un elemento a $\mathbb N$**

Sia $B=\mathbb N\cup\{\star\}$. L'inclusione $\mathbb N\hookrightarrow B$ è iniettiva. È iniettiva anche:

$$
j:B\to\mathbb N,
\qquad
j(\star)=0,
\quad
j(n)=n+1.
$$

Cantor-Bernstein dà:

$$
|B|=|\mathbb N|.
$$

In questo caso $j$ è già suriettiva e quindi biiettiva, ma il confronto tramite due iniezioni illustra il metodo generale.

#### **6.2 Un intervallo e una semiretta**

L'inclusione $(0,1)\hookrightarrow\mathbb R$ è iniettiva. La funzione:

$$
r:\mathbb R\to(0,1),
\qquad
r(x)=\frac{1}{2}+\frac{1}{\pi}\arctan x,
$$

è iniettiva. Quindi Cantor-Bernstein garantisce:

$$
|(0,1)|=|\mathbb R|.
$$

Nella lezione sul continuo costruiremo anche la biiezione inversa esplicita.

### **7. Somma, prodotto e potenza di cardinali**

Le operazioni cardinali estendono quelle finite:

- $|A|+|B|$ è la cardinalità di un'unione disgiunta, per esempio $(A\times\{0\})\cup(B\times\{1\})$;
- $|A|\cdot|B|$ è la cardinalità di $A\times B$;
- $|B|^{|A|}$ è la cardinalità di $B^A$, l'insieme di tutte le funzioni $A\to B$.

Le etichette $0$ e $1$ nella somma impediscono sovrapposizioni anche quando $A\cap B\neq\varnothing$.

Nel caso numerabile dimostreremo:

$$
\aleph_0+\aleph_0=\aleph_0,
\qquad
\aleph_0\cdot\aleph_0=\aleph_0.
$$

Il teorema di Cantor mostrerà invece:

$$
2^{|A|}>|A|
$$

per ogni insieme $A$.

### **8. Errori frequenti**

- Dedurre l'equipotenza da una sola iniezione.
- Confondere $|A|\leq|B|$ con $A\subseteq B$.
- Presentare $g\circ f$ come biiezione $A\to B$: il codominio della composizione è $A$.
- Usare l'equivalenza “iniezione in un verso, suriezione nell'altro” ignorando il caso vuoto o la scelta dei rappresentanti.
- Scrivere $|A|<|B|$ soltanto perché $A\subsetneq B$.

### **9. Esercizi**

1. Costruire due iniezioni opposte tra $\mathbb N$ e $\mathbb N\setminus\{0,1,2\}$ e applicare Cantor-Bernstein.
2. Mostrare che $|[0,1]|=|(0,1)|$ usando le iniezioni $x\mapsto x$ e $x\mapsto(x+1)/3$ nei versi appropriati.
3. Individuare $A_0,A_1,A_2$ nella dimostrazione del teorema quando $A=\mathbb N$, $B=\mathbb N_{>0}$, $f(n)=n+1$ e $g(k)=k$.
4. Spiegare perché una suriezione $B\to A$ non produce automaticamente una funzione iniettiva esplicita $A\to B$ se non è data una regola per scegliere le controimmagini.
5. Problema trasversale: per $X=\{0\}\cup(1,2)$ costruire un'iniezione $X\to(0,1)$ e una in senso opposto, verificare dominio, codominio e iniettività, quindi concludere mediante Cantor-Bernstein. Spiegare perché la sola inclusione $(1,2)\subset X$ non risolve il confronto.

### **10. Riepilogo**

> ✅ Due iniezioni in versi opposti sono sufficienti per costruire una biiezione.

- $|A|\leq|B|$ significa che esiste un'iniezione $A\to B$;
- i casi vuoti e l'assioma della scelta richiedono attenzione nel passaggio alle suriezioni;
- Cantor-Bernstein rende antisimmetrico il confronto tra cardinalità;
- la dimostrazione separa $A$ in una parte trattata con $f$ e una trattata con $g^{-1}$;
- somma, prodotto e potenza cardinali si definiscono tramite insiemi costruiti esplicitamente.
