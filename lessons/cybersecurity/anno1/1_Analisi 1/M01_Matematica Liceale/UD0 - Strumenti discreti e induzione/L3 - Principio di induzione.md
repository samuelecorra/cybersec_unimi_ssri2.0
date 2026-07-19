# **M1 UD0 Lezione 3 - Principio di induzione**

### **1. Introduzione**

Molte affermazioni matematiche dipendono da un numero naturale. Per esempio:

$$
1+2+\dots+n=\frac{n(n+1)}{2}
$$

è una frase che cambia al variare di $n$. Per $n=1$ afferma:

$$
1=\frac{1\cdot 2}{2}.
$$

Per $n=2$ afferma:

$$
1+2=\frac{2\cdot 3}{2}.
$$

E così via.

Il principio di induzione serve a dimostrare in modo rigoroso che una proposizione $P(n)$ è vera per tutti i naturali a partire da un certo indice.

> 📌 L'induzione non verifica infiniti casi uno per uno: dimostra che il primo caso è vero e che ogni caso vero trascina con sé il successivo.

### **2. Proposizioni dipendenti da $n$**

Una proposizione $P(n)$ è un'affermazione che dipende da un intero naturale $n$.

Esempio:

$$
P(n):\quad \sum_{k=1}^{n} k=\frac{n(n+1)}{2}.
$$

Dire che $P(n)$ è vera per ogni $n\ge 1$ significa dire:

$$
\forall n\in\mathbb{N},\ n\ge 1 \Longrightarrow P(n).
$$

In questa lezione useremo i quantificatori in modo operativo; una trattazione più sistematica del linguaggio logico appartiene al modulo su insiemi e relazioni.

### **3. Principio di induzione semplice**

Sia $P(n)$ una proposizione definita per ogni intero $n\ge n_0$. Per dimostrare che $P(n)$ è vera per ogni $n\ge n_0$, basta provare:

1. **caso base**: $P(n_0)$ è vera;
2. **passo induttivo**: per ogni $n\ge n_0$, se $P(n)$ è vera, allora $P(n+1)$ è vera.

Allora $P(n)$ è vera per ogni $n\ge n_0$.

Il caso base avvia la catena. Il passo induttivo garantisce che la verità si propaghi da ogni indice al successivo.

<!-- TODO FIGURA:
Rappresentazione intuitiva dell'induzione come catena di tessere.
Mostrare una prima tessera che cade (caso base) e una regola che fa cadere la successiva
quando la precedente è caduta (passo induttivo). Specificare nella figura che è solo un'analogia.
Obiettivo didattico: distinguere il caso base dal meccanismo logico del passo induttivo.
-->

> ⚠️ Nel passo induttivo non si assume che la tesi sia vera per tutti gli indici. Si assume solo $P(n)$ per un indice generico $n$ e si deve dimostrare $P(n+1)$.

### **4. Struttura di una dimostrazione per induzione**

Una dimostrazione per induzione ben scritta contiene sempre:

1. dichiarazione della proposizione $P(n)$;
2. indicazione dell'insieme degli indici;
3. caso base;
4. ipotesi induttiva;
5. passo induttivo;
6. conclusione.

Quando manca uno di questi elementi, la dimostrazione rischia di essere incompleta.

### **5. Somma dei primi naturali**

Dimostriamo che per ogni $n\ge 1$ vale:

$$
\sum_{k=1}^{n} k=\frac{n(n+1)}{2}.
$$

#### **5.1 Caso base**

Per $n=1$:

$$
\sum_{k=1}^{1} k=1.
$$

Il secondo membro vale:

$$
\frac{1(1+1)}{2}=1.
$$

Quindi $P(1)$ è vera.

#### **5.2 Ipotesi induttiva**

Supponiamo vera la formula per un certo $n\ge 1$:

$$
\sum_{k=1}^{n} k=\frac{n(n+1)}{2}.
$$

Questa è l'ipotesi induttiva.

#### **5.3 Passo induttivo**

Dobbiamo dimostrare che:

$$
\sum_{k=1}^{n+1} k=\frac{(n+1)(n+2)}{2}.
$$

Separiamo l'ultimo termine:

$$
\sum_{k=1}^{n+1} k=\sum_{k=1}^{n} k+(n+1).
$$

Ora usiamo l'ipotesi induttiva:

$$
\sum_{k=1}^{n+1} k=\frac{n(n+1)}{2}+(n+1).
$$

Raccogliamo $n+1$:

$$
\frac{n(n+1)}{2}+(n+1)
=
(n+1)\left(\frac{n}{2}+1\right).
$$

Quindi:

$$
(n+1)\left(\frac{n}{2}+1\right)
=
(n+1)\frac{n+2}{2}
=
\frac{(n+1)(n+2)}{2}.
$$

Abbiamo dimostrato $P(n+1)$.

#### **5.4 Conclusione**

Per il principio di induzione, la formula vale per ogni $n\ge 1$.

### **6. Somma geometrica finita**

Dimostriamo che, per $q\neq 1$ e per ogni $n\ge 0$:

$$
\sum_{k=0}^{n} q^k=\frac{1-q^{n+1}}{1-q}.
$$

#### **6.1 Caso base**

Per $n=0$:

$$
\sum_{k=0}^{0}q^k=q^0=1.
$$

Il secondo membro vale:

$$
\frac{1-q^{1}}{1-q}=1.
$$

Quindi $P(0)$ è vera.

#### **6.2 Ipotesi induttiva**

Supponiamo che per un certo $n\ge 0$ valga:

$$
\sum_{k=0}^{n} q^k=\frac{1-q^{n+1}}{1-q}.
$$

#### **6.3 Passo induttivo**

Dobbiamo dimostrare:

$$
\sum_{k=0}^{n+1}q^k=\frac{1-q^{n+2}}{1-q}.
$$

Separiamo l'ultimo termine:

$$
\sum_{k=0}^{n+1}q^k=\sum_{k=0}^{n}q^k+q^{n+1}.
$$

Usando l'ipotesi induttiva:

$$
\sum_{k=0}^{n+1}q^k=\frac{1-q^{n+1}}{1-q}+q^{n+1}.
$$

Portiamo a denominatore comune:

$$
\frac{1-q^{n+1}}{1-q}+\frac{q^{n+1}(1-q)}{1-q}.
$$

Sommiamo:

$$
\frac{1-q^{n+1}+q^{n+1}-q^{n+2}}{1-q}
=
\frac{1-q^{n+2}}{1-q}.
$$

Quindi $P(n+1)$ è vera.

#### **6.4 Conclusione**

Per induzione, la formula vale per ogni $n\ge 0$ e per ogni $q\neq 1$.

Se $q=1$, invece:

$$
\sum_{k=0}^{n}1=n+1.
$$

### **7. Formule fondamentali delle somme**

Raccogliamo alcune formule che saranno usate spesso.

Per ogni $n\ge 1$:

$$
\sum_{k=1}^{n}1=n.
$$

Abbiamo già dimostrato:

$$
\sum_{k=1}^{n}k=\frac{n(n+1)}{2}.
$$

Valgono inoltre:

$$
\sum_{k=1}^{n}k^2=\frac{n(n+1)(2n+1)}{6}
$$

e

$$
\sum_{k=1}^{n}k^3=\left(\frac{n(n+1)}{2}\right)^2.
$$

#### **7.1 Dimostrazione per induzione della somma dei quadrati**

Dimostriamo:

$$
\sum_{k=1}^{n}k^2=\frac{n(n+1)(2n+1)}{6}
$$

per ogni $n\ge 1$.

Caso base $n=1$:

$$
\sum_{k=1}^{1}k^2=1
$$

e

$$
\frac{1\cdot 2\cdot 3}{6}=1.
$$

Ipotesi induttiva:

$$
\sum_{k=1}^{n}k^2=\frac{n(n+1)(2n+1)}{6}.
$$

Passo induttivo:

$$
\sum_{k=1}^{n+1}k^2=\sum_{k=1}^{n}k^2+(n+1)^2.
$$

Usando l'ipotesi induttiva:

$$
\sum_{k=1}^{n+1}k^2
=
\frac{n(n+1)(2n+1)}{6}+(n+1)^2.
$$

Raccogliamo $n+1$:

$$
\frac{n(n+1)(2n+1)}{6}+(n+1)^2
=
(n+1)\left(\frac{n(2n+1)}{6}+n+1\right).
$$

Portiamo a denominatore comune:

$$
(n+1)\left(\frac{2n^2+n+6n+6}{6}\right)
=
(n+1)\frac{2n^2+7n+6}{6}.
$$

Fattorizziamo:

$$
2n^2+7n+6=(n+2)(2n+3).
$$

Quindi:

$$
\sum_{k=1}^{n+1}k^2
=
\frac{(n+1)(n+2)(2n+3)}{6}.
$$

Poiché:

$$
2n+3=2(n+1)+1,
$$

la formula è esattamente quella richiesta per $n+1$.

#### **7.2 Osservazione sulla somma dei cubi**

La formula:

$$
\sum_{k=1}^{n}k^3=\left(\frac{n(n+1)}{2}\right)^2
$$

dice che la somma dei primi $n$ cubi è il quadrato della somma dei primi $n$ naturali:

$$
1^3+2^3+\dots+n^3=(1+2+\dots+n)^2.
$$

Una dimostrazione per induzione è accessibile e segue la stessa struttura delle precedenti. Il caso base è $n=1$. Nel passo induttivo si aggiunge $(n+1)^3$ al secondo membro e si verifica che:

$$
\left(\frac{n(n+1)}{2}\right)^2+(n+1)^3
=
\left(\frac{(n+1)(n+2)}{2}\right)^2.
$$

Infatti:

$$
\left(\frac{n(n+1)}{2}\right)^2+(n+1)^3
=
(n+1)^2\left(\frac{n^2}{4}+n+1\right)
$$

e:

$$
\frac{n^2}{4}+n+1=\frac{(n+2)^2}{4}.
$$

Quindi:

$$
(n+1)^2\frac{(n+2)^2}{4}
=
\left(\frac{(n+1)(n+2)}{2}\right)^2.
$$

### **8. Esempio di induzione con disuguaglianza**

Dimostriamo che per ogni $n\ge 1$ vale:

$$
2^n\ge n+1.
$$

Caso base $n=1$:

$$
2^1=2\ge 2.
$$

Ipotesi induttiva:

$$
2^n\ge n+1.
$$

Passo induttivo. Dobbiamo dimostrare:

$$
2^{n+1}\ge n+2.
$$

Partiamo dal primo membro:

$$
2^{n+1}=2\cdot 2^n.
$$

Usando l'ipotesi induttiva:

$$
2\cdot 2^n\ge 2(n+1)=2n+2.
$$

Ora, per $n\ge 1$:

$$
2n+2\ge n+2.
$$

Quindi:

$$
2^{n+1}\ge n+2.
$$

Per induzione, la disuguaglianza vale per ogni $n\ge 1$.

### **9. Esempio di induzione con divisibilità**

Dimostriamo che per ogni $n\ge 1$:

$$
3 \text{ divide } 4^n-1.
$$

In simboli:

$$
3\mid(4^n-1).
$$

Caso base $n=1$:

$$
4^1-1=3,
$$

quindi $3$ divide $4^1-1$.

Ipotesi induttiva: supponiamo che per un certo $n\ge 1$ esista un intero $m$ tale che:

$$
4^n-1=3m.
$$

Dobbiamo dimostrare che $3$ divide $4^{n+1}-1$.

Scriviamo:

$$
4^{n+1}-1=4\cdot 4^n-1.
$$

Aggiungiamo e sottraiamo $4$:

$$
4\cdot 4^n-1=4(4^n-1)+3.
$$

Usando l'ipotesi induttiva:

$$
4(4^n-1)+3=4\cdot 3m+3=3(4m+1).
$$

Quindi $3$ divide $4^{n+1}-1$.

### **10. Induzione con caso base diverso da $1$**

Non tutte le proposizioni iniziano da $1$.

Dimostriamo che per ogni $n\ge 4$:

$$
2^n\ge n^2.
$$

Caso base $n=4$:

$$
2^4=16=4^2.
$$

Ipotesi induttiva: supponiamo che per un certo $n\ge 4$ valga:

$$
2^n\ge n^2.
$$

Dobbiamo dimostrare:

$$
2^{n+1}\ge (n+1)^2.
$$

Abbiamo:

$$
2^{n+1}=2\cdot 2^n\ge 2n^2.
$$

Ora bisogna verificare che:

$$
2n^2\ge (n+1)^2
$$

per $n\ge 4$. Sviluppando:

$$
2n^2-(n+1)^2=n^2-2n-1.
$$

Per $n\ge 4$:

$$
n^2-2n-1\ge 16-8-1=7>0.
$$

Quindi:

$$
2n^2\ge (n+1)^2.
$$

Segue:

$$
2^{n+1}\ge (n+1)^2.
$$

Per induzione, la disuguaglianza vale per ogni $n\ge 4$.

### **11. Induzione forte**

L'induzione forte si usa quando per dimostrare $P(n+1)$ può servire conoscere non solo $P(n)$, ma tutti i casi precedenti.

La forma è:

1. si dimostra il caso base;
2. si suppone che $P(n_0),P(n_0+1),\dots,P(n)$ siano vere;
3. si dimostra $P(n+1)$.

La conclusione è la stessa: $P(n)$ è vera per ogni $n\ge n_0$.

Induzione semplice e forte sono concettualmente equivalenti, ma l'induzione forte è più comoda quando la struttura del problema usa più passi precedenti.

### **12. Esempio di induzione forte**

Dimostriamo che ogni intero $n\ge 2$ si può scrivere come prodotto di numeri primi.

Caso base $n=2$: $2$ è primo, quindi è prodotto di un solo primo.

Ipotesi induttiva forte: supponiamo che ogni intero $m$ con:

$$
2\le m\le n
$$

si possa scrivere come prodotto di primi.

Dobbiamo dimostrare che anche $n+1$ si può scrivere come prodotto di primi.

Ci sono due casi.

Se $n+1$ è primo, allora è già prodotto di un solo primo.

Se $n+1$ non è primo, allora esistono due interi $a,b$ tali che:

$$
n+1=ab
$$

con:

$$
2\le a\le n,\quad 2\le b\le n.
$$

Per ipotesi induttiva forte, sia $a$ sia $b$ si scrivono come prodotti di primi. Allora anche il loro prodotto $ab=n+1$ si scrive come prodotto di primi.

In entrambi i casi, $n+1$ ha una fattorizzazione in primi. Per induzione forte, ogni intero $n\ge 2$ si scrive come prodotto di numeri primi.

> 💡 Qui l'induzione forte è naturale perché, nel caso composto, i fattori $a$ e $b$ possono essere molto più piccoli di $n$, non necessariamente uguali a $n$.

### **13. Esempio di induzione errata**

Consideriamo il seguente ragionamento falso:

> “Dimostriamo che tutti i numeri naturali sono uguali. Caso base: in un insieme con un solo numero, tutti i numeri sono uguali. Passo induttivo: se in ogni insieme di $n$ numeri tutti sono uguali, allora in un insieme di $n+1$ numeri tolgo l'ultimo e i primi $n$ sono uguali; poi tolgo il primo e gli ultimi $n$ sono uguali; quindi tutti gli $n+1$ numeri sono uguali.”

L'errore è nel passaggio da $n=1$ a $n=2$. Se ho due numeri, togliendo l'ultimo resta il primo; togliendo il primo resta il secondo. I due sottoinsiemi non hanno elementi in comune, quindi non c'è nessun elemento che colleghi le due conclusioni.

Il passo induttivo funziona solo da $n\ge 2$, ma il caso base fornito è $n=1$. La catena si rompe proprio nel primo passaggio.

> ⚠️ Una dimostrazione per induzione deve verificare che il passo induttivo sia valido per tutti gli indici a partire dal caso base.

### **14. Errori logici frequenti**

#### **14.1 Saltare il caso base**

Dimostrare solo che $P(n)\Rightarrow P(n+1)$ non basta. Senza caso base, la catena non parte.

#### **14.2 Assumere la tesi**

Nel passo induttivo si può assumere solo $P(n)$, non $P(n+1)$. Se durante la dimostrazione si usa già ciò che si vuole dimostrare, il ragionamento è circolare.

#### **14.3 Cambiare proposizione**

La proposizione $P(n+1)$ deve essere la stessa formula di $P(n)$ con $n$ sostituito da $n+1$. Non bisogna dimostrare una frase diversa.

#### **14.4 Usare un caso base troppo debole**

Se il passo induttivo vale solo da $n\ge 4$, il caso base deve permettere di arrivare a quella zona. Bisogna controllare sempre l'indice da cui parte la dimostrazione.

### **15. Collegamenti futuri**

L'induzione tornerà:

- nelle successioni definite per ricorrenza;
- nella dimostrazione di formule per somme parziali;
- nelle stime di successioni;
- nel binomio di Newton;
- in alcune proprietà delle serie;
- nelle formule di Taylor, dove compaiono coefficienti e derivate di ordine arbitrario.

### **16. Riepilogo**

> ✅ L'induzione dimostra una proposizione per infiniti indici naturali usando un caso base e un passo di propagazione.

Schema:

- definisci $P(n)$;
- dimostra il caso base;
- assumi $P(n)$ come ipotesi induttiva;
- dimostra $P(n+1)$;
- concludi per ogni $n$ dell'intervallo considerato.

L'induzione forte permette di assumere veri tutti i casi precedenti e non solo quello immediatamente precedente.
