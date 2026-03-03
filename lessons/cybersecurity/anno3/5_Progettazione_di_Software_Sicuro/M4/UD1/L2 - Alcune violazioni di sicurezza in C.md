# **Lezione 2: Alcune violazioni di sicurezza in C**

---

### 1. Introduzione: perché il C non è sicuro

Il linguaggio C (e in parte C++) è noto per essere estremamente potente, efficiente e vicino all’hardware, ma questa stessa caratteristica lo rende intrinsecamente pericoloso dal punto di vista della sicurezza. Infatti, molte vulnerabilità software storiche derivano proprio da proprietà del linguaggio che permettono al programmatore di manipolare direttamente la memoria senza controlli automatici.

Le principali fonti di errore nel C includono:

1. dereferenziazione di puntatori nulli;
2. conversioni di tipo non controllate (type cast);
3. aritmetica dei puntatori;
4. accesso alla memoria non valido:
   - violazioni spaziali (out-of-bounds);
   - violazioni temporali (dangling pointers).

Queste caratteristiche rendono il linguaggio molto flessibile e veloce, ma trasferiscono completamente al programmatore la responsabilità di evitare difetti e vulnerabilità.

---

### 2. Dereferenziazione di puntatore nullo

Nel linguaggio C, l’accesso a un puntatore nullo non viene controllato automaticamente dal compilatore o dal runtime. Se un programma tenta di accedere alla memoria attraverso un puntatore nullo, il risultato tipico è un errore del sistema operativo chiamato **segmentation fault**.

Un esempio di programma che genera questo problema è:

```c
int main() {
    int *ptr;
    ptr = 0;
    *ptr = 2;
}
````

In questo caso il puntatore `ptr` contiene l’indirizzo nullo, quindi l’operazione `*ptr = 2` tenta di scrivere in una zona di memoria non valida. Il sistema operativo interrompe l’esecuzione del processo per evitare danni maggiori.

Dal punto di vista della sicurezza, la dereferenziazione di null può essere sfruttata in alcune condizioni per causare denial of service o per manipolare flussi di esecuzione.

---

### 3. Type cast non sicuro

Il C permette conversioni tra tipi molto permissive, senza controlli di sicurezza.

I problemi principali includono:

- conversione da un tipo più grande a uno più piccolo con perdita di informazioni;
    
- conversione di interi in puntatori o addirittura in funzioni;
    
- interpretazione errata di sequenze di bit.
    

Un esempio semplice è:

```c
double d;
int i;
...
i = d;
```

Questa conversione è accettata dal compilatore ma può comportare perdita di precisione.

Un caso ancora più pericoloso è la conversione di un valore intero in puntatore, che può permettere l’accesso arbitrario alla memoria.

---

### 4. Pointer arithmetic e violazione dei tipi

L’aritmetica dei puntatori consente di manipolare indirizzi di memoria direttamente.

Se `p` è un puntatore di tipo `A*`, l’espressione:

```
*(p + i)
```

ha tipo `A`, ma il contenuto della memoria in quella posizione potrebbe appartenere a qualsiasi tipo reale. Questo permette di violare la sicurezza dei tipi e inserire valori non coerenti nel programma.

Di conseguenza, il C non è **memory safe**, cioè non garantisce che un programma acceda solo alla memoria che gli è stata assegnata.

Un esempio di funzione pericolosa è:

```c
void f(int* p, int i, int v) {
    p[i] = v;
}
```

L’indirizzo `p + i` potrebbe trovarsi fuori dai limiti dell’array o sovrascrivere dati critici, come:

- indirizzi di ritorno delle funzioni;
    
- diritti di accesso;
    
- informazioni sensibili.
    

Questo è il principio alla base di attacchi classici come il **buffer overflow**, già analizzato nei corsi di sicurezza informatica.

---

### 5. Type cast e accesso arbitrario alla memoria

Nel linguaggio C, i puntatori possono essere assimilati a interi. Questo consente conversioni estremamente pericolose.

Ad esempio:

```c
int main() {
    char *PTR;
    PTR = 1000;
    *PTR = 'a';
}
```

Il compilatore accetta il programma, ma l’indirizzo 1000 potrebbe non appartenere al processo oppure contenere dati critici. Questo permette potenzialmente di scrivere in memoria arbitraria.

Dal punto di vista della sicurezza, questa possibilità è una delle cause principali di vulnerabilità sfruttabili dagli attaccanti.

---

### 6. Deallocazione esplicita e dangling pointers

Nei linguaggi come C e Pascal, la memoria viene gestita manualmente dal programmatore. Quando una zona di memoria viene liberata, i puntatori che la riferivano non vengono automaticamente invalidati.

Un **dangling pointer** è un puntatore che continua a riferirsi a una zona di memoria già deallocata.

Questo crea problemi perché:

- il sistema operativo potrebbe riutilizzare quella memoria per altri dati;
    
- il programma può continuare ad accedere a quella memoria;
    
- si rompe la sicurezza dei tipi.
    

---

### 7. Dangling pointers nello stack

Un errore molto comune si verifica quando si restituisce un puntatore a una variabile locale allocata sullo stack.

Consideriamo il seguente esempio:

```c
char *itoa(int i) {
    char buf[20];
    sprintf(buf, "%d", i);
    return buf;
}
```

La variabile `buf` è allocata sullo stack della funzione. Quando la funzione termina, la memoria viene liberata automaticamente. Il puntatore restituito quindi punta a memoria non più valida.

Questo genera un dangling pointer e comportamenti imprevedibili.

---

### 8. Esempio in C++

Un problema analogo può verificarsi in C++:

```cpp
struct Point { int x; int y; };

struct Point *newPoint(int x, int y) {
    struct Point result = {x, y};
    return &result;
}

void bar() {
    struct Point *p = newPoint(1, 2);
    p->y = 1234;
}
```

La funzione `newPoint` restituisce un puntatore a una variabile locale (`result`). Dopo il ritorno dalla funzione, il puntatore diventa dangling.

---

### 9. Strategie per evitare dangling pointers

Esistono diverse strategie per prevenire questo tipo di errore.

#### 9.1 Allocazione dinamica sicura

Una soluzione consiste nell’allocare la memoria sull’heap tramite `malloc`:

```c
struct Point *result = malloc(sizeof(struct Point));
```

La memoria allocata dinamicamente rimane valida fino alla sua esplicita liberazione.

#### 9.2 Garbage collector

Un’altra soluzione è utilizzare linguaggi o runtime che implementano il **garbage collector**, cioè un sistema automatico di gestione della memoria che identifica le zone non più utilizzate e le libera in modo sicuro.

---

### 10. Come evitare errori di sicurezza nel C

Se si desidera scrivere codice sicuro, alcune strategie includono:

- progettare attentamente il software prima dell’implementazione;
    
- documentare il codice;
    
- scrivere con attenzione;
    
- evitare manipolazioni rischiose della memoria.
    

Se invece si vuole avere una maggiore garanzia di sicurezza, esistono due approcci principali:

1. utilizzare linguaggi type safe (come Java o Lisp);
    
2. utilizzare il C insieme a strumenti e librerie che aumentano la sicurezza.
    

Questi strumenti verranno analizzati nelle lezioni successive.

---

### 11. Sintesi della lezione

In questa lezione sono state analizzate alcune delle principali fonti di vulnerabilità nel linguaggio C.

In particolare:

- dereferenziazione di puntatori nulli;
    
- conversioni di tipo non controllate;
    
- aritmetica dei puntatori;
    
- violazioni spaziali della memoria (buffer overflow);
    
- violazioni temporali della memoria (dangling pointers).
    

Sono state inoltre presentate possibili soluzioni:

- passare a linguaggi più sicuri;
    
- utilizzare strumenti per aumentare la sicurezza del codice C.
    

---

### Prossimi passi

Nella prossima lezione verranno introdotti strumenti e librerie per migliorare la sicurezza dei programmi scritti in C.