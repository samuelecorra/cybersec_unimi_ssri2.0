# **M3 UD1 Lezione 1 - Segmenti orientati e vettori**

### **1. Obiettivi e prerequisiti**

Questa lezione introduce il passaggio dai segmenti orientati ai **vettori liberi**. Al termine sapremo:

- distinguere lunghezza, direzione, verso e punto di applicazione;
- riconoscere segmenti orientati equivalenti;
- interpretare un vettore come classe di equivalenza;
- sommare e sottrarre vettori;
- moltiplicare un vettore per uno scalare reale;
- caratterizzare il parallelismo mediante un multiplo scalare.

Sono richieste soltanto le nozioni di punto, retta, parallelismo e prodotto cartesiano.

---

### **2. Segmenti orientati**

Sia $S$ l'insieme dei punti dello spazio. Un **segmento orientato** è una coppia ordinata di punti

$$
overrightarrow{AB}=(A,B)\in S\times S
$$

dove $A$ è il **punto iniziale** e $B$ è il **punto finale**. L'ordine è essenziale: in generale $(A,B)\neq(B,A)$.

Un segmento orientato è caratterizzato da quattro dati:

1. la **lunghezza**, cioè la distanza tra $A$ e $B$;
2. la **direzione**, cioè la retta di appartenenza, considerata a meno di parallelismo;
3. il **verso**, da $A$ verso $B$;
4. il **punto di applicazione**, cioè $A$.

Due segmenti orientati sono diversi se differiscono in almeno uno di questi elementi.

#### **2.1. Il segmento nullo**

Se $A=B$, il segmento orientato è **nullo**. La sua lunghezza è zero, mentre direzione e verso sono indeterminati.

> ⚠️ Un segmento nullo non individua una direzione: non bisogna attribuirgli arbitrariamente la direzione di una retta.

---

### **3. Equivalenza di segmenti orientati e vettori**

Due segmenti orientati non nulli $(A,B)$ e $(C,D)$ si dicono **equivalenti**, e si scrive

$$
(A,B)\sim(C,D)
$$

se hanno la stessa lunghezza, la stessa direzione e lo stesso verso. Tutti i segmenti nulli sono considerati equivalenti tra loro.

La relazione così definita è una relazione di equivalenza: è riflessiva, simmetrica e transitiva. Essa dimentica il punto di applicazione, ma conserva le altre tre caratteristiche.

> 📌 Un **vettore** è una classe di equivalenza di segmenti orientati rispetto a $\sim$.

Nel testo indicheremo i vettori con lettere in grassetto, per esempio $\mathbf v$, e la loro lunghezza, detta anche **modulo**, con $\lVert\mathbf v\rVert$.

Dato un segmento orientato $(A,B)$ e un punto qualsiasi $C$, esiste ed è unico un punto $D$ tale che

$$
(A,B)\sim(C,D)
$$

Questa proprietà consente di traslare un rappresentante del vettore in qualunque punto senza cambiare il vettore. In particolare, da ogni punto parte esattamente un rappresentante di ciascun vettore.

**Esempio.** Due frecce parallele, concordi e lunghe $5$ rappresentano lo stesso vettore anche se applicate in punti diversi. Due frecce ugualmente lunghe e parallele ma discordi non sono equivalenti: rappresentano vettori opposti.

---

### **4. Somma di vettori**

La somma $\mathbf v+\mathbf w$ si costruisce con il metodo **punta-coda**:

1. si sceglie un rappresentante di $\mathbf v$;
2. si applica un rappresentante di $\mathbf w$ alla punta del primo;
3. il vettore somma va dalla coda del primo alla punta del secondo.

La costruzione è indipendente dai rappresentanti scelti, perché una traslazione non cambia lunghezze, direzioni e versi.

La somma è:

- **commutativa**:

$$
\mathbf v+\mathbf w=\mathbf w+\mathbf v
$$

- **associativa**:

$$
(\mathbf v+\mathbf w)+\mathbf u=\mathbf v+(\mathbf w+\mathbf u)
$$

Il vettore nullo $\mathbf 0$ è l'elemento neutro:

$$
\mathbf v+\mathbf 0=\mathbf v
$$

#### **4.1. Disuguaglianza triangolare**

La costruzione punta-coda mostra che il lato diretto tra punto iniziale e punto finale non è più lungo del percorso spezzato:

$$
\lVert\mathbf v+\mathbf w\rVert\leq
\lVert\mathbf v\rVert+\lVert\mathbf w\rVert
$$

L'uguaglianza si verifica quando i due vettori sono paralleli e concordi, incluso il caso in cui uno dei due sia nullo.

**Rappresentazione della pagina 2 del PDF.** Il parallelogramma costruito su due rappresentanti di $\mathbf v$ e $\mathbf w$ mostra simultaneamente la regola punta-coda, la commutatività e la diagonale $\mathbf v+\mathbf w$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Opposto e differenza**

Per ogni vettore $\mathbf v$ esiste il vettore opposto $-\mathbf v$, caratterizzato da stessa lunghezza e direzione ma verso opposto, tale che

$$
\mathbf v+(-\mathbf v)=\mathbf 0
$$

La differenza è definita mediante la somma con l'opposto:

$$
\mathbf v-\mathbf w=\mathbf v+(-\mathbf w)
$$

Nel parallelogramma di lati adiacenti $\mathbf v$ e $\mathbf w$:

- una diagonale rappresenta $\mathbf v+\mathbf w$;
- l'altra rappresenta $\mathbf v-\mathbf w$ oppure $\mathbf w-\mathbf v$, secondo il verso scelto.

Infatti

$$
\mathbf v-\mathbf w=-(\mathbf w-\mathbf v)
$$

Perciò, anche nella rappresentazione grafica, è necessario sapere quale vettore compare per primo.

Se $\mathbf v$ e $\mathbf w$ hanno stessa direzione, stesso verso e uguale modulo, allora appartengono alla stessa classe di equivalenza e

$$
\mathbf v-\mathbf w=\mathbf 0
$$

> ⚠️ La sola uguaglianza dei moduli non implica $\mathbf v=\mathbf w$: occorrono anche direzione e verso uguali.

---

### **6. Prodotto di un vettore per uno scalare**

Sia $V$ l'insieme dei vettori. La moltiplicazione per scalare è l'applicazione

$$
\mathbb R\times V\longrightarrow V,
\qquad (k,\mathbf v)\longmapsto k\mathbf v
$$

Se $k=0$ oppure $\mathbf v=\mathbf 0$, si pone $k\mathbf v=\mathbf 0$. Negli altri casi:

- $k\mathbf v$ ha la stessa direzione di $\mathbf v$;
- ha lo stesso verso se $k>0$ e verso opposto se $k<0$;
- il suo modulo è

$$
\lVert k\mathbf v\rVert=|k|\,\lVert\mathbf v\rVert
$$

In particolare,

$$
1\mathbf v=\mathbf v,
\qquad (-1)\mathbf v=-\mathbf v
$$

**Esempi.** Il vettore $2\mathbf v$ è concorde con $\mathbf v$ e lungo il doppio; $-\frac12\mathbf v$ è discorde e lungo la metà.

#### **6.1. Parallelismo**

Due vettori non nulli $\mathbf v$ e $\mathbf w$ sono paralleli se e solo se esiste $k\in\mathbb R\setminus\{0\}$ tale che

$$
\mathbf w=k\mathbf v
$$

Se $k>0$ sono concordi; se $k<0$ sono discordi. Per il vettore nullo, la direzione resta indeterminata e questa caratterizzazione va usata con cautela.

> 💡 Applicando due rappresentanti nell'origine, il parallelismo equivale al fatto che essi giacciano sulla stessa retta.

---

### **7. Errori comuni**

- Confondere un vettore con uno specifico segmento orientato: il vettore è l'intera classe di rappresentanti equivalenti.
- Dimenticare che il punto di applicazione distingue i segmenti orientati, ma non i vettori liberi.
- Disegnare $\mathbf v-\mathbf w$ con il verso di $\mathbf w-\mathbf v$.
- Scrivere $\lVert k\mathbf v\rVert=k\lVert\mathbf v\rVert$ anche per $k<0$: serve $|k|$.

---

> ✅ Un vettore è una classe di equivalenza di segmenti orientati con uguali lunghezza, direzione e verso. I vettori si sommano con la regola punta-coda, ammettono opposto e differenza, e possono essere moltiplicati per scalari reali. Il parallelismo tra vettori non nulli equivale all'essere multipli scalari.
