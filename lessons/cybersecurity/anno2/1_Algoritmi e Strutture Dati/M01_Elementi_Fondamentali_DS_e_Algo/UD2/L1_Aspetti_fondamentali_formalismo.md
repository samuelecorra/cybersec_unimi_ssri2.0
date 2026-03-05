
## **Lezione 1 – Aspetti fondamentali del formalismo**

---

### **1. Introduzione**

I termini _dato_ e _tipo di dato_ non sono sinonimi: hanno un significato profondamente diverso.

- **Dato:** è un valore che una variabile può assumere.
    
- **Tipo di dato:** è un modello matematico, cioè un insieme di valori sui quali sono definite determinate operazioni.
    

#### **Esempio**

- Il numero `7` è un **dato**.
    
- L’insieme dei numeri interi, con le quattro operazioni aritmetiche (somma, sottrazione, moltiplicazione, divisione), costituisce un **tipo di dato**.
    

> Quindi il tipo di dato definisce il _mondo logico_ in cui i dati vivono e interagiscono.

---

### **2. Tipo di dato astratto (TDA)**

Le proprietà che caratterizzano un tipo di dato **non dipendono** dalla sua rappresentazione fisica nella memoria, ma **dalla sua specifica logica**.

#### **Definizione**

Un **tipo di dato astratto** (TDA) è un tipo di dato definito solo tramite la **descrizione delle operazioni possibili** e delle **proprietà logiche** che tali operazioni devono rispettare.

In altre parole:

- La **specifica** del tipo di dato ne descrive i valori e le operazioni in termini matematici.
    
- Una buona specifica è come un **manuale d’uso**: chiara, concisa e priva di ambiguità.
    

> Esempio: il concetto di _insieme_ come struttura che supporta operazioni come “appartenenza”, “unione” e “intersezione”, indipendentemente da come gli elementi siano memorizzati.

---

### **3. Strutture dati – definizione**

Una **struttura dati** (o struttura informativa) è un modo sistematico di organizzare i dati per consentirne l’elaborazione efficiente.

#### **Definizione**

Una struttura dati è caratterizzata da:

1. Un **modo specifico** di organizzare i dati
    
2. Un insieme di **operatori** che permettono di manipolare i dati o aggregarli per costruire nuove strutture
    

---

### **4. Classificazione delle strutture dati**

Le strutture dati possono essere distinte in base a diversi criteri:

|Criterio|Tipologie|
|---|---|
|**Relazione tra gli elementi**|Lineari (dati in sequenza) / Non lineari|
|**Dimensione**|A dimensione fissa / A dimensione variabile|
|**Omogeneità**|Omogenee (stesso tipo di dato) / Non omogenee|

#### **Esempio**

Un **vettore** è una struttura **lineare**, **omogenea** e **a dimensione fissa**.

---

### **5. Specifica di un tipo di dato**

Ogni tipo di dato è descritto attraverso due livelli di specifica:

- **Specifica sintattica**
    
- **Specifica semantica**
    

---

#### **5.1. Specifica sintattica**

È la parte che definisce _come_ descriviamo il tipo di dato, cioè:

- i nomi dei tipi di dato coinvolti,
    
- la struttura,
    
- le operazioni specifiche,
    
- le costanti utilizzate.
    

Indica inoltre **i domini di partenza e di arrivo** per ogni operatore.

> In sostanza: la parte “grammaticale” della definizione.

---

#### **5.2. Specifica semantica**

È la parte che descrive _cosa fa_ ogni operatore.  
Si esprime in termini di funzioni matematiche definite sui domini di partenza e di arrivo, specificando:

- una **precondizione** (ciò che deve essere vero prima dell’operazione)
    
- una **postcondizione** (ciò che deve essere vero dopo l’operazione)
    

> È la parte “logica” e “significativa” della definizione.

---

### **6. Esempio di specifica: il tipo di dato “vettore”**

#### **Specifica sintattica**

- Tipi coinvolti:  
    `vettore`, `intero`, `tipoelem`
    Il primo è autoesplicativo: è l'identificatore del vettore stesso;
    Il secondo si riferisce verosimilmente alla dimensione, al numero di elementi,
	dal momento che gli array hanno dimensione fissa, ma anche all'indice qualsiasi che si può passare ai vari operatori/funzioni;
	Il terzo infine si riferisce al qualsiasi elemento che risiede nel vettore, e quindi ci consente di dedurre le dimensioni della singola cella e quindi le dimensioni totali del vettore.
    
- Operatori:
	    Sintassi:
	    Dominio di partenza → Dominio di arrivo
	    
    - `creavettore() → vettore`
        Non si prendono parametri in ingresso (casomai in certi linguaggi si deve prendere in ingresso la lunghezza ma per semplificazione diciamo  che non si passa nulla) e si restituisce un "oggetto" di tipo vettore.
        
    - `leggivettore(vettore, intero) → tipoelem`
        Si passa alla funzione il vettore su cui si vuole agire, un intero che funge da indice della cella in cui leggere e viene restituito tale elemento di tale cella.
        
    - `scrivivettore(vettore, intero, tipoelem) → vettore`
        Valgono considerazioni analoghe...

---

Piccola digressione più che doverosa...
#### **🧠 a. Nel modello astratto (formale, matematico)**

A breve scriveremo:  
$$1 \le i \le n$$
Non facciamoci ingannare se abbiamo già competenze di programmazione e siamo soliti dire che gli indici validi sono tra 0 e dimensione - 1!
👉 Qui **n** indica la **dimensione del vettore** che coincide con il massimo indice valido, poiché gli indici validi partono da 1 per nostra scelta.
 
Quindi un vettore di dimensione `n = 5` ha elementi:

$$v(1), \ v(2), \ v(3),\ v(4),\ v(5)$$

È un modo classico nei modelli teorici (insiemistica, logica, teoria dei tipi): gli indici partono da 1 perché il concetto di "primo elemento" non è legato alla memoria, ma alla posizione logica.

---

#### **💻 b. Nel modello implementativo (C, Java, Python, ecc.)**

Gli indici partono da **0**, quindi l’ultimo è **n − 1**.  
In questo caso, la condizione dovrebbe essere:

$$0 \le i \le n-1$$

E la dimensione “fisica” del vettore resta **n**, ma gli indici validi vanno da 0 a n−1.

Esempio in C:

```c
int v[5]; // dimensione = 5
// indici validi: 0,1,2,3,4
```

In parole povere, non confondiamoci... il nostro è un modello astratto 1-based!

---

#### **Specifica semantica**
Le precondizioni, se presenti, sono *quasi* sempre la validità degli indici, come qui si può notare...

**Operatore `creavettore`**

creavettore = $v$

	non ci sono precondizioni
	post: per ogni i, 1 ≤ i ≤ n, l'i-esimo elemento v(i) è uguale a un certo
	tipoelem


**Operatore `leggivettore`**

`leggivettore(v, i) = e 
	
	pre: 1 ≤ i ≤ n 
	post: e = v(i)`


**Operatore `scrivivettore`**

`scrivivettore(v, i, e) = v’ 
	
	pre: 1 ≤ i ≤ n 
	post: v’(i) = e, 
		e v’(j) = v(j) per ogni j ≠ i`

---

### **7. Realizzazione del tipo di dato**

Una volta definita la specifica logica, bisogna **implementarla** in un linguaggio di programmazione.  
Nel linguaggio C, ad esempio:

`creavettore: tipoelem v[n];` 
`leggivettore: v[i]; `
`scrivivettore: v[i] = e;`

> L’implementazione traduce la definizione astratta in un meccanismo concreto di manipolazione della memoria.

---

### **8. Rappresentazione in memoria**

L’efficienza di una struttura dati dipende anche dal modo in cui viene **memorizzata in memoria**.  
In questa fase si assume che i dati siano organizzati indipendentemente dalle caratteristiche fisiche della macchina, ma resta importante sapere **come vengono effettivamente allocati**.

---

#### **8.1. Memorizzazione di un vettore**

Se `tipoelem` è un tipo di dato primitivo, il vettore viene allocato in **n celle consecutive** di memoria, a partire da un indirizzo base $b$.

|Indirizzo|Contenuto|
|---|---|
|b|v[0]|
|b + i|v[i]|
|b + n - 1|v[n - 1]|

> Questa disposizione garantisce **accesso diretto** agli elementi, con costo costante $O(1)$.

![Pasted image 20251026114850](../UD1/imgs/Pasted%20image%2020251026114850.png)

---

#### **8.2. Memorizzazione di una matrice**

La memoria del calcolatore è lineare, ma una matrice è bidimensionale.  
Per memorizzarla, si può scegliere tra due metodi:

1. **Memorizzazione per righe**
    
2. **Memorizzazione per colonne**
    

In entrambi i casi, ogni riga (o colonna) è un **vettore**.

Esempio:  
Una matrice $m[n,2]$ memorizzata per colonne occupa posizioni contigue per ciascuna colonna.

![Pasted image 20251026114812](../UD1/imgs/Pasted%20image%2020251026114812.png)

---

### **9. Il tipo di dato “puntatore”**

Il **puntatore** è un tipo di dato primitivo che memorizza **indirizzi di memoria** invece di valori numerici o simbolici.

#### **Funzioni principali**

- Permette la **realizzazione di strutture dati a dimensione variabile** (liste, alberi, grafi).
    
- Consente di **restituire strutture dati** come risultato di funzioni.
    

Un puntatore può quindi essere visto come una “freccia” che **punta** a una cella di memoria.

---

#### **Esempio in C**

```c
int main() {

    int a, *b;
    a = 2008;
    b = &a;

    printf("variabile intera a = %d, memorizzata in %p\n", a, &a);
    printf("variabile puntatore a intero b = %p, memorizzato in %p\n", b, &b);
    printf("Dereferenziando b otteniamo il valore di a: *b = %d, tale valore
    conservato in %p\n", *b, b);

    return 0;

}
```

**Output (come da console di VSCode):

variabile intera a = 2008, memorizzata in 000000F0771FFA4C

variabile puntatore a intero b = 000000F0771FFA4C, memorizzato in 000000F0771FFA40

Dereferenziando b otteniamo il valore di a: \*b = 2008, tale valore conservato in 000000F0771FFA4C

👉 `b` contiene l’indirizzo di `a`, mentre `*b` permette di accedere al valore contenuto in quell’indirizzo.  
È il principio base dell’**accesso indiretto alla memoria**.

---

### **10. Sintesi finale**

- È stato introdotto un **formalismo matematico** per la descrizione dei tipi di dato e delle strutture dati.
    
- Si è analizzato il formalismo attraverso l’esempio del **vettore**.
    
- È stata evidenziata l’importanza della **realizzazione concreta** rispetto all’efficienza.
    
- Si è introdotto il concetto di **puntatore**, indispensabile per strutture dinamiche.
    

> In conclusione, questa lezione segna il passaggio dal “pensare in termini di operazioni” al “pensare in termini di modelli dati”: il punto in cui l’informatica comincia a dialogare con la matematica.