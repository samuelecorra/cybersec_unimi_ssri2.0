# **Lezione 1: Sicurezza dei tipi nei linguaggi di programmazione**

---

### 1. Introduzione alla sicurezza dei tipi

La sicurezza dei tipi rappresenta uno dei fondamenti teorici e pratici della progettazione di software sicuro. In un sistema informatico, l’esecutore — che può essere un processore fisico oppure una macchina virtuale — deve essere in grado di distinguere correttamente i tipi dei dati manipolati dal programma. Se questa distinzione non viene mantenuta, possono verificarsi errori anche gravi, fino alla compromissione del comportamento del sistema.

Dal punto di vista della sicurezza informatica, il problema assume un’importanza ancora maggiore, poiché numerosi attacchi sfruttano proprio debolezze nel controllo dei tipi presenti in linguaggi molto diffusi, in particolare nel linguaggio C.

Un principio fondamentale della teoria dei tipi, formulato da Robert Milner, sintetizza l’obiettivo ideale dei sistemi di tipi:

> “Well-typed programs never go wrong.”

Ovvero: i programmi correttamente tipati non producono errori di tipo durante l’esecuzione.

---

### 2. Definizione di tipo

Un tipo può essere definito come:

> un insieme di valori omogenei associato alle operazioni che possono essere applicate su tali valori.

Questa definizione evidenzia due componenti fondamentali:

1. l’insieme dei valori ammessi;
2. le operazioni valide su tali valori.

I tipi possono assumere forme diverse:

- tipi primitivi, come interi e stringhe;
- tipi strutturati, come classi e oggetti;
- tipi funzionali, ad esempio una funzione con tipo `int → bool`, cioè una funzione che riceve un intero e restituisce un valore booleano.

È importante osservare che anche le funzioni e i metodi possiedono un tipo.

Esistono invece insiemi che non sono considerati tipi nella maggior parte dei linguaggi, ad esempio:

- l’insieme dei numeri dispari;
- array contenenti elementi di tipi eterogenei come stringhe e interi.

Tuttavia, ciò dipende fortemente dal linguaggio di programmazione adottato.

---

### 3. Ruolo dei tipi nei programmi

I tipi svolgono diversi ruoli essenziali nella progettazione del software.

#### 3.1 Organizzazione concettuale e documentazione

I tipi consentono di organizzare e denominare concetti presenti nel dominio del problema. Spesso i tipi corrispondono direttamente agli oggetti del mondo reale modellati dal programma, migliorando la leggibilità e la comprensione del codice.

#### 3.2 Supporto al controllo del compilatore

Attraverso i tipi è possibile indicare al compilatore come verranno utilizzati gli identificatori. Questo permette al compilatore di verificare automaticamente la correttezza delle operazioni.

Ad esempio, l’espressione:

```

3 + true + "Angelo"

```

risulta semanticamente priva di significato e può essere intercettata grazie al sistema dei tipi.

#### 3.3 Interpretazione corretta dei dati in memoria

I tipi garantiscono che le sequenze di bit presenti in memoria vengano interpretate correttamente. Senza questa informazione, gli stessi bit potrebbero rappresentare valori completamente diversi.

#### 3.4 Supporto alla rappresentazione dei dati

I tipi forniscono indicazioni al compilatore su come rappresentare i dati in memoria. Ad esempio, un valore `short` occupa meno bit rispetto a un `int`, permettendo ottimizzazioni in termini di spazio e prestazioni.

---

### 4. Errori di tipo a livello hardware

Gli errori di tipo non riguardano solo il livello logico del programma, ma possono manifestarsi anche a livello hardware.

#### 4.1 Confusione tra dati e programmi

Un errore classico consiste nel tentare di eseguire un dato come se fosse codice, ad esempio chiamando:

```

x()

```

quando `x` è un intero e non una procedura. In questo caso la CPU potrebbe tentare di eseguire sequenze di bit non valide come istruzioni.

#### 4.2 Confusione tra tipi primitivi

Un altro esempio riguarda l’uso errato di operazioni hardware specializzate, come operazioni in virgola mobile. Se una funzione destinata a operare su float riceve invece un intero, la sequenza di bit potrebbe essere interpretata in modo errato generando comportamenti imprevedibili.

---

### 5. Errori semantici dovuti ai tipi

Un programma può anche essere sintatticamente corretto ma semanticamente errato a causa di problemi di tipo.

Un esempio è:

```

int_add(3, 4.5)

````

La sequenza di bit che rappresenta `4.5` potrebbe essere interpretata come intero, ma il valore risultante non sarebbe quello previsto.

Nel paradigma orientato agli oggetti, gli errori di tipo possono emergere con ereditarietà e sottotipi.

Ad esempio:

```java
class Quadrato extends Figura
````

L’assegnazione:

```java
Figura a1 = new Quadrato();
```

è corretta perché un quadrato è una figura.

Al contrario:

```java
Quadrato b1 = new Figura();
```

non è corretta, poiché una figura generica potrebbe non possedere i metodi specifici di un quadrato.

---

### 6. Type safety: sicurezza dei tipi

Un linguaggio di programmazione viene definito **type safe** se non esiste alcun programma scritto in quel linguaggio che possa violare la distinzione tra tipi.

Le violazioni dei tipi includono:

- confusione tra interi e float;
    
- chiamata di funzioni attraverso valori non funzionali;
    
- accesso a zone di memoria non corrette;
    
- violazioni di memory safety.
    

La memory safety è strettamente collegata alla sicurezza dei tipi, poiché accedere a memoria errata implica interpretare sequenze di bit con un tipo diverso da quello previsto.

---

### 7. Sicurezza dei linguaggi di programmazione

I linguaggi possono essere classificati in base alla sicurezza del loro sistema di tipi.

- Linguaggi non sicuri: C, C++
    
- Linguaggi quasi sicuri: Pascal
    
- Linguaggi sicuri: Java, Lisp
    

Le principali cause di insicurezza nei linguaggi non sicuri includono:

- conversioni di tipo esplicite (type cast);
    
- aritmetica dei puntatori;
    
- gestione manuale della memoria.
    

---

### 8. Problemi specifici del C e C++

Il linguaggio C (e in parte C++) presenta numerosi problemi legati alla sicurezza dei tipi.

Tra gli errori principali:

1. Type casting arbitrario;
    
2. Pointer arithmetic;
    
3. Accesso a memoria non valida:
    
    - violazioni spaziali (out-of-bounds);
        
    - violazioni temporali (dangling pointers);
        
4. Dereferenziazione del puntatore nullo.
    

Queste caratteristiche rendono il linguaggio potente ma anche pericoloso dal punto di vista della sicurezza.

---

### 9. Momento del controllo dei tipi

Nei linguaggi type safe si distinguono due approcci principali a seconda del momento in cui avviene il controllo dei tipi.

#### 9.1 Run-time type checking

Il controllo avviene durante l’esecuzione del programma.

Esempio nel linguaggio Lisp:

Quando viene eseguita l’istruzione `(car x)`, il sistema verifica che `x` sia effettivamente una lista prima di applicare l’operazione.

#### 9.2 Compile-time type checking

Il controllo avviene durante la compilazione.

Esempio nel linguaggio ML:

Se viene compilata l’espressione `f(x)` e `f` ha tipo `A → B`, il compilatore verifica che `x` abbia tipo `A`.

---

### 10. Approccio ibrido: il caso di Java

Java utilizza principalmente il controllo dei tipi a compile time. Tuttavia, quando il compilatore non può garantire completamente la sicurezza, introduce controlli a run-time.

Ad esempio:

```java
Quadrato a = (Quadrato) b;
```

Se `b` è dichiarato come `Figura`, la conversione è valida solo se l’oggetto reale è effettivamente un quadrato. Questo controllo viene quindi effettuato durante l’esecuzione.

---

### 11. Vantaggi e svantaggi dei due approcci

Entrambi gli approcci prevengono errori di tipo, ma presentano differenze importanti.

#### Run-time checking

Vantaggi:

- maggiore flessibilità;
    
- possibilità di eseguire programmi che non sarebbero accettati dal compilatore.
    

Svantaggi:

- rallentamento dell’esecuzione;
    
- controlli ripetuti durante il runtime.
    

#### Compile-time checking

Vantaggi:

- maggiore efficienza;
    
- errori individuati prima dell’esecuzione.
    

Svantaggi:

- minore flessibilità;
    
- anche codice non eseguito deve essere corretto.
    

Un esempio interessante riguarda Lisp e Java.

In Lisp:

```
(cond ((< x 10) x) (else (car x)))
```

Il programma può funzionare correttamente in alcune esecuzioni e generare errori in altre, ma il sistema runtime gestisce il controllo.

In Java invece:

```java
int x;
if (0 > -1) { x++; } else { x = "ciao"; }
```

Il programma viene rifiutato dal compilatore, anche se il ramo errato non verrà mai eseguito.

---

### 12. Sintesi della lezione

In questa lezione sono stati introdotti i concetti fondamentali della sicurezza dei tipi nei linguaggi di programmazione.

Sono stati analizzati:

- il ruolo dei tipi nei programmi;
    
- la definizione di linguaggio type safe;
    
- le differenze tra linguaggi sicuri e non sicuri;
    
- i problemi del linguaggio C;
    
- il confronto tra controllo dei tipi a compile time e run-time;
    
- i compromessi tra efficienza e flessibilità.
    

Un punto fondamentale da ricordare è che il linguaggio C non è type safe, e molte vulnerabilità software derivano proprio da questa caratteristica.

---

### Prossimi passi

Nella lezione successiva verranno analizzati alcuni errori tipici del linguaggio C legati alla sicurezza dei tipi.