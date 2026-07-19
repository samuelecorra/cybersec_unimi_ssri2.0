# **M1 UD0 Lezione 1 - Sommatorie e produttorie**

### **1. Introduzione**

In Analisi 1 compaiono continuamente espressioni formate da molti termini simili: somme parziali di successioni, serie, polinomi di Taylor, prodotti ricorrenti e criteri di convergenza. Scrivere ogni termine a mano diventa presto impossibile.

La notazione di sommatoria e produttoria serve a rappresentare in modo compatto somme e prodotti finiti, mantenendo però chiaro quali termini vengono sommati o moltiplicati.

> 📌 Una sommatoria non è una formula “misteriosa”: è un modo abbreviato per scrivere una somma con una regola precisa.

In tutto il modulo adottiamo la convenzione:

$$
\mathbb{N}=\{0,1,2,\dots\}.
$$

Quando un indice deve partire da $1$ o da un altro naturale, la condizione viene sempre indicata esplicitamente. Questa convenzione evita ambiguità in fattoriali, induzione e coefficienti binomiali.

### **2. Notazione di sommatoria**

La scrittura:

$$
\sum_{k=1}^{n} a_k
$$

si legge “somma per $k$ che va da $1$ a $n$ dei termini $a_k$”.

Gli elementi della notazione sono:

- $\sum$: simbolo di sommatoria;
- $k$: indice di sommatoria;
- $1$: estremo inferiore;
- $n$: estremo superiore;
- $a_k$: termine generale.

Espandendo:

$$
\sum_{k=1}^{n} a_k=a_1+a_2+\dots+a_n.
$$

Se l'estremo inferiore è diverso da $1$, si procede nello stesso modo:

$$
\sum_{k=p}^{q} a_k=a_p+a_{p+1}+\dots+a_q.
$$

Il numero di addendi è:

$$
q-p+1
$$

quando $p\le q$.

Per esempio:

$$
\sum_{k=3}^{7} a_k=a_3+a_4+a_5+a_6+a_7.
$$

Ci sono $7-3+1=5$ addendi.

### **3. Il nome dell'indice è irrilevante**

L'indice di sommatoria è una variabile muta: il suo nome non conta, purché venga cambiato in modo coerente.

Per esempio:

$$
\sum_{k=1}^{n} k^2
=
\sum_{j=1}^{n} j^2.
$$

Entrambe indicano:

$$
1^2+2^2+\dots+n^2.
$$

> ⚠️ L'indice è locale alla sommatoria. Non va confuso con variabili esterne alla somma.

Per esempio, nell'espressione:

$$
n+\sum_{k=1}^{n} k
$$

$n$ è l'estremo superiore e anche un termine esterno alla somma; $k$ invece esiste solo dentro la sommatoria.

### **4. Sommatorie vuote**

In alcune formule avanzate è utile ammettere anche sommatorie in cui l'estremo inferiore supera quello superiore:

$$
\sum_{k=p}^{q} a_k
\quad \text{con } p>q.
$$

Questa si chiama **sommatoria vuota** e, per convenzione, vale:

$$
\sum_{k=p}^{q} a_k=0
\quad \text{se } p>q.
$$

La convenzione è naturale: lo zero è l'elemento neutro della somma. Aggiungere una somma vuota non cambia nulla.

Per esempio, nella decomposizione:

$$
\sum_{k=1}^{n} a_k=a_1+\sum_{k=2}^{n} a_k
$$

la formula resta valida anche per $n=1$, perché:

$$
\sum_{k=2}^{1} a_k=0.
$$

### **5. Espandere e comprimere una sommatoria**

Espandere una sommatoria significa scrivere esplicitamente i termini.

Esempio:

$$
\sum_{k=0}^{4}(2k+1)
=
(2\cdot 0+1)+(2\cdot 1+1)+(2\cdot 2+1)+(2\cdot 3+1)+(2\cdot 4+1).
$$

Quindi:

$$
\sum_{k=0}^{4}(2k+1)=1+3+5+7+9.
$$

Comprimere una somma significa riconoscere il termine generale. Per esempio:

$$
4+7+10+13+\dots+(3n+1)
$$

può essere scritto come:

$$
\sum_{k=1}^{n}(3k+1).
$$

Infatti per $k=1$ si ottiene $4$, per $k=2$ si ottiene $7$, e così via.

> 💡 Riconoscere il termine generale è una competenza essenziale per passare dalle somme finite alle serie.

### **6. Linearità della sommatoria**

La sommatoria è lineare. Se $a_k$ e $b_k$ sono due famiglie di numeri reali e $\lambda,\mu\in\mathbb{R}$, allora:

$$
\sum_{k=p}^{q}(\lambda a_k+\mu b_k)
=
\lambda\sum_{k=p}^{q}a_k+\mu\sum_{k=p}^{q}b_k.
$$

Questa proprietà si ottiene espandendo la somma e raccogliendo i termini simili.

Per esempio:

$$
\sum_{k=1}^{n}(3k^2-2k)
=
3\sum_{k=1}^{n}k^2-2\sum_{k=1}^{n}k.
$$

> ⚠️ La costante si estrae moltiplicando tutta la somma, non un singolo termine.

### **7. Somma di una costante**

Se $c$ è costante, allora:

$$
\sum_{k=1}^{n} c=nc.
$$

Infatti:

$$
\sum_{k=1}^{n} c=\underbrace{c+c+\dots+c}_{n\text{ volte}}=nc.
$$

Più in generale:

$$
\sum_{k=p}^{q} c=(q-p+1)c
$$

quando $p\le q$.

### **8. Separazione dell'intervallo di indici**

Se $p\le r<q$, allora:

$$
\sum_{k=p}^{q} a_k
=
\sum_{k=p}^{r} a_k+\sum_{k=r+1}^{q} a_k.
$$

Questa proprietà permette di spezzare una somma in due blocchi.

Per esempio:

$$
\sum_{k=1}^{10} a_k
=
\sum_{k=1}^{4} a_k+\sum_{k=5}^{10} a_k.
$$

È importante che i due intervalli non si sovrappongano e non lascino buchi.

### **9. Primo e ultimo termine**

Spesso è utile isolare il primo o l'ultimo termine:

$$
\sum_{k=1}^{n} a_k
=
a_1+\sum_{k=2}^{n} a_k
$$

e

$$
\sum_{k=1}^{n} a_k
=
\sum_{k=1}^{n-1} a_k+a_n.
$$

Queste identità sono usate continuamente nelle dimostrazioni per induzione e nello studio delle somme parziali delle serie.

### **10. Cambio di indice**

Un cambio di indice riscrive la stessa somma con una variabile diversa o con estremi diversi.

Consideriamo:

$$
\sum_{k=1}^{n} k^2.
$$

Poniamo:

$$
j=k-1.
$$

Quando $k=1$, si ha $j=0$. Quando $k=n$, si ha $j=n-1$. Inoltre:

$$
k=j+1.
$$

Quindi:

$$
\sum_{k=1}^{n} k^2
=
\sum_{j=0}^{n-1}(j+1)^2.
$$

> ⚠️ In un cambio di indice bisogna trasformare sia il termine generale sia gli estremi.

#### **10.1 Errore tipico**

La trasformazione:

$$
\sum_{k=1}^{n} k^2=\sum_{j=1}^{n}(j+1)^2
$$

è sbagliata, perché cambia i termini: il secondo membro è:

$$
2^2+3^2+\dots+(n+1)^2.
$$

Non rappresenta la somma originaria.

### **11. Inversione dell'ordine degli indici**

Talvolta si vuole leggere la somma al contrario. Per esempio:

$$
\sum_{k=1}^{n} a_k=a_n+a_{n-1}+\dots+a_1.
$$

Poniamo:

$$
j=n+1-k.
$$

Quando $k=1$, si ha $j=n$. Quando $k=n$, si ha $j=1$. Il termine $a_k$ diventa:

$$
a_{n+1-j}.
$$

Quindi:

$$
\sum_{k=1}^{n} a_k
=
\sum_{j=1}^{n} a_{n+1-j}.
$$

L'ordine dei termini in una somma finita non cambia il risultato, ma questa riscrittura è utile in alcune dimostrazioni.

### **12. Sommatorie telescopiche**

Una sommatoria è **telescopica** quando molti termini si cancellano.

Consideriamo:

$$
\sum_{k=1}^{n}(a_{k+1}-a_k).
$$

Espandendo:

$$
(a_2-a_1)+(a_3-a_2)+\dots+(a_{n+1}-a_n).
$$

Tutti i termini intermedi si cancellano:

$$
\sum_{k=1}^{n}(a_{k+1}-a_k)=a_{n+1}-a_1.
$$

Esempio:

$$
\sum_{k=1}^{n}\left(\frac{1}{k}-\frac{1}{k+1}\right).
$$

Espandendo:

$$
\left(1-\frac{1}{2}\right)+\left(\frac{1}{2}-\frac{1}{3}\right)+\dots+\left(\frac{1}{n}-\frac{1}{n+1}\right).
$$

Resta:

$$
1-\frac{1}{n+1}.
$$

### **13. Somme doppie finite**

Una somma doppia finita ha due indici. Per esempio:

$$
\sum_{i=1}^{m}\sum_{j=1}^{n} a_{ij}
$$

significa:

$$
\sum_{i=1}^{m}\left(\sum_{j=1}^{n} a_{ij}\right).
$$

Prima si fissa $i$ e si somma rispetto a $j$, poi si somma rispetto a $i$.

Se gli estremi sono indipendenti, l'ordine può essere scambiato:

$$
\sum_{i=1}^{m}\sum_{j=1}^{n} a_{ij}
=
\sum_{j=1}^{n}\sum_{i=1}^{m} a_{ij}.
$$

Esempio:

$$
\sum_{i=1}^{2}\sum_{j=1}^{3}(i+j)
$$

si espande come:

$$
(1+1)+(1+2)+(1+3)+(2+1)+(2+2)+(2+3).
$$

Quindi:

$$
\sum_{i=1}^{2}\sum_{j=1}^{3}(i+j)=2+3+4+3+4+5=21.
$$

### **14. Notazione di produttoria**

La produttoria è l'analogo moltiplicativo della sommatoria.

La scrittura:

$$
\prod_{k=1}^{n} a_k
$$

significa:

$$
a_1\cdot a_2\cdot \dots \cdot a_n.
$$

Per esempio:

$$
\prod_{k=1}^{4} k=1\cdot 2\cdot 3\cdot 4=24.
$$

Se $p\le q$:

$$
\prod_{k=p}^{q} a_k=a_p\cdot a_{p+1}\cdot \dots \cdot a_q.
$$

### **15. Produttoria vuota**

La produttoria vuota, cioè una produttoria con estremo inferiore maggiore dell'estremo superiore, vale per convenzione:

$$
\prod_{k=p}^{q} a_k=1
\quad \text{se } p>q.
$$

La ragione è che $1$ è l'elemento neutro del prodotto.

Questa convenzione rende coerenti formule come:

$$
\prod_{k=1}^{n} a_k=a_1\prod_{k=2}^{n} a_k
$$

anche per $n=1$.

### **16. Proprietà delle produttorie**

Se $a_k$ e $b_k$ sono termini reali, allora:

$$
\prod_{k=p}^{q}(a_kb_k)
=
\left(\prod_{k=p}^{q}a_k\right)\left(\prod_{k=p}^{q}b_k\right).
$$

Se $c$ è una costante, bisogna fare attenzione:

$$
\prod_{k=1}^{n} c=c^n.
$$

Invece:

$$
c\prod_{k=1}^{n} a_k
$$

significa:

$$
c\cdot a_1\cdot a_2\cdot \dots \cdot a_n.
$$

> ⚠️ Nelle produttorie una costante ripetuta $n$ volte diventa una potenza. Non vale la stessa regola delle sommatorie.

Per esempio:

$$
\prod_{k=1}^{4} 3=3^4=81,
$$

mentre:

$$
3\prod_{k=1}^{4} k=3\cdot 1\cdot 2\cdot 3\cdot 4=72.
$$

### **17. Separazione e cambio di indice nelle produttorie**

Se $p\le r<q$, allora:

$$
\prod_{k=p}^{q} a_k
=
\left(\prod_{k=p}^{r} a_k\right)\left(\prod_{k=r+1}^{q} a_k\right).
$$

Anche nelle produttorie si possono fare cambi di indice, trasformando sia il termine sia gli estremi.

Per esempio:

$$
\prod_{k=1}^{n} (k+1).
$$

Poniamo $j=k+1$. Quando $k=1$, $j=2$; quando $k=n$, $j=n+1$. Quindi:

$$
\prod_{k=1}^{n} (k+1)
=
\prod_{j=2}^{n+1} j.
$$

### **18. Prodotti telescopici**

Un prodotto telescopico contiene cancellazioni moltiplicative.

Per esempio:

$$
\prod_{k=1}^{n}\frac{k+1}{k}
=
\frac{2}{1}\cdot\frac{3}{2}\cdot\frac{4}{3}\cdots\frac{n+1}{n}.
$$

Tutti i fattori intermedi si semplificano:

$$
\prod_{k=1}^{n}\frac{k+1}{k}=n+1.
$$

Un altro esempio:

$$
\prod_{k=2}^{n}\frac{k-1}{k}
=
\frac{1}{2}\cdot\frac{2}{3}\cdot\dots\cdot\frac{n-1}{n}
=
\frac{1}{n}.
$$

### **19. Collegamenti futuri**

Le sommatorie saranno usate per:

- somme parziali di successioni e serie;
- serie geometriche e telescopiche;
- definizione di polinomi di Taylor;
- stime e confronti asintotici.

Le produttorie saranno usate per:

- fattoriali;
- coefficienti binomiali;
- prodotti telescopici;
- termini generali con rapporto tra termini consecutivi.

### **20. Riepilogo**

> ✅ Sommatorie e produttorie sono notazioni compatte, ma richiedono controllo preciso di indice, estremi e termine generale.

Punti fondamentali:

- $\sum_{k=p}^{q} a_k$ somma i termini da $p$ a $q$;
- il nome dell'indice è irrilevante;
- nei cambi di indice vanno trasformati anche gli estremi;
- le sommatorie vuote valgono $0$;
- le produttorie vuote valgono $1$;
- $\prod_{k=1}^{n} c=c^n$, non $nc$;
- somme e prodotti telescopici si riconoscono dalle cancellazioni successive.
