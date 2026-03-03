# **Lezione 1: Concetti base**

### 1. Definizioni formali del testing

Per studiare il testing del software in modo rigoroso è utile introdurre una formalizzazione matematica dei concetti fondamentali. Si definisce un predicato:

$$  
OK(P, d)  
$$

dove $d \in D$ rappresenta un input appartenente al dominio del programma e il predicato è vero se il programma $P$ produce il risultato corretto per quell’input.

Si definisce inoltre:

$$  
OK(P)  
$$

che indica che il programma è corretto per tutti gli input del dominio, cioè:

$$  
OK(P) \iff \forall d \in D ; OK(P, d)  
$$

Un programma può essere visto formalmente come una funzione:

$$  
P : D \rightarrow R  
$$

dove $D$ è il dominio degli input e $R$ è il codominio degli output. È importante osservare che un programma può essere una funzione **parziale**, cioè non definita per alcuni elementi del dominio, ad esempio in presenza di crash o comportamenti non gestiti.

---

### 2. Failure, fault ed errore nella formalizzazione teorica

Riprendendo la terminologia già introdotta nelle lezioni precedenti, possiamo formalizzare i concetti principali.

Un **failure** (malfunzionamento) si verifica quando, eseguendo il programma con input $d$, non si ottiene il risultato corretto previsto dalla funzione ideale $P(d)$. Questo può accadere perché il programma produce un valore diverso oppure perché si blocca. Formalmente:

$$  
\text{Failure} \iff \neg OK(P, d)  
$$

Un **fault** (difetto o bug) rappresenta la differenza tra il programma ideale $P$ e il programma implementato $P'$. Se:

$$  
P' \neq P  
$$

allora la differenza costituisce il difetto.

L’**errore** rappresenta invece la causa umana che ha generato il difetto.

Questa catena causale rimane quindi:

$$  
Errore \rightarrow Difetto \rightarrow Malfunzionamento  
$$

---

### 3. Caso di test e test suite

Un **caso di test** (test case) è un elemento del dominio degli input $D$. Ad esempio, se il dominio è costituito dagli interi, un caso di test sarà un numero intero. Se il dominio è costituito da sequenze di interi, un caso di test sarà una sequenza.

Un **test set** o **test suite** è un sottoinsieme finito del dominio:

$$  
T \subseteq D  
$$

Applicando un test set $T$ al programma $P$ si definisce:

$$  
OK(P, T)  
$$

se per ogni elemento $t \in T$ il programma produce risultati corretti. In questo caso il test non ha individuato difetti (test negativo).

Se invece esiste almeno un elemento $t \in T$ tale che:

$$  
\neg OK(P, t)  
$$

allora il test è positivo perché ha individuato un malfunzionamento.

Un esempio chiarisce il concetto. Considerando una funzione:

```java
static void foo(String s, int x)
```

un caso di test è una coppia composta da una stringa e un intero, ad esempio `["pippo", 3]`. Un test set è un insieme di tali coppie.

---

### 4. Test ideale e correttezza dei programmi

Nelle prime ricerche sul testing si cercava di individuare insiemi di test che potessero dimostrare la correttezza dei programmi. Si introduce quindi il concetto di **test ideale**.

Un test set $T$ è ideale se:

$$  
OK(P, T) \Rightarrow OK(P)  
$$

cioè se il programma supera tutti i test di $T$, allora è garantito che sia corretto per tutti gli input del dominio.

Questo significa che l’esecuzione senza errori del test set costituisce una prova della correttezza del programma.

Il problema fondamentale diventa quindi trovare un insieme di test ideale.

---

### 5. Testing esaustivo e limiti pratici

Il caso più evidente di test ideale è il **testing esaustivo**, in cui:

$$  
T = D  
$$

cioè si testano tutti i possibili input del programma.

Tuttavia questo approccio è generalmente impraticabile. Ad esempio, considerando una funzione che somma due interi a 32 bit:

```c
static int sum(int a, int b) { return a + b; }
```

il numero di possibili input è:

$$  
2^{32} \times 2^{32} = 2^{64} \approx 10^{21}  
$$

Anche eseguendo un test ogni nanosecondo, sarebbero necessari circa 30.000 anni per completare il testing.

Se il dominio è infinito, come nei sistemi reattivi, il testing esaustivo diventa addirittura impossibile.

Di conseguenza:

$$  
T \subset D  
$$

ed è necessario selezionare un sottoinsieme significativo del dominio.

---

### 6. Criteri di test e adeguatezza

Per selezionare i test si introducono i **criteri di adeguatezza** (test criteria). Un criterio è una funzione che, dato un programma $P$, la sua specifica $S$ e un test set $T$, restituisce vero o falso a seconda che il test set sia adeguato.

Formalmente:

$$  
C : P \times S \times T \rightarrow {T, F}  
$$

Dato un test suite $T$, si dice che soddisfa il criterio se è adeguato a trovare difetti secondo il criterio scelto.

Un esempio semplice di criterio può essere: un test set è adeguato se contiene almeno un numero positivo e uno negativo.

---

### 7. Program-based e specification-based criteria

I criteri di testing possono dipendere dal programma o dalla specifica.

Nel **program-based testing**, il criterio non dipende dalla specifica:

$$  
C : P \times T \rightarrow {T, F}  
$$

Nel **specification-based testing**, invece, il criterio non dipende dal programma:

$$  
C : S \times T \rightarrow {T, F}  
$$

Un criterio può anche essere visto come un generatore di test:

$$  
C : P \times S \rightarrow T  
$$

che produce un test set adeguato.

---

### 8. Criterio affidabile

Un criterio è **affidabile** se produce risultati consistenti indipendentemente dal test set selezionato. Formalmente, per ogni coppia di test set adeguati secondo il criterio, se uno individua un malfunzionamento allora anche l’altro lo individuerà.

Questo significa che la capacità di individuare difetti non dipende dal particolare insieme scelto ma dal criterio stesso.

Tuttavia l’affidabilità non garantisce che il difetto venga effettivamente trovato. Un criterio può essere affidabile ma inefficace, ad esempio se seleziona solo input che non rivelano il bug.

---

### 9. Criterio valido

Un criterio è **valido** se, quando il programma non è corretto, esiste almeno un test set che soddisfa il criterio e che è in grado di individuare il difetto.

Formalmente:

$$  
\exists T ; \text{tale che} ; C(P,S,T) \land \neg OK(P,T)  
$$

Un criterio valido quindi rende possibile trovare difetti, ma non garantisce che ogni test set selezionato li trovi.

Un criterio casuale è valido ma poco utile, perché potrebbe non trovare difetti nella pratica.

---

### 10. Esempi di criteri affidabili e validi

Considerando il programma errato:

```c
int raddoppia(int x) { return x*x; }
```

Un criterio che seleziona solo valori nell’insieme {0,2} è affidabile, perché produce risultati consistenti, ma non valido, perché non scopre il difetto.

Un criterio che richiede almeno un valore maggiore o uguale a 3 è invece sia valido sia affidabile, perché garantisce la scoperta del difetto.

---

### 11. Teorema di Goodenough e Gerhart

Un risultato teorico fondamentale del testing è il **teorema di Goodenough e Gerhart**.

Se un programma passa un test set selezionato mediante un criterio che è sia affidabile sia valido (cioè ideale), allora il programma è corretto.

Formalmente:

Se:

- il criterio è affidabile per il programma,
    
- il criterio è valido per il programma,
    
- il test set è selezionato con il criterio,
    
- il test set non trova malfunzionamenti,
    

allora:

$$  
OK(P)  
$$

In questo caso il criterio è detto **ideale**.

---

### 12. Criterio ideale

Un criterio ideale è quindi un criterio che seleziona test ideali. Nell’esempio del programma raddoppia, un criterio che richiede almeno un input maggiore o uguale a 3 è sia valido sia affidabile, e quindi ideale.

---

### Sintesi della lezione

In questa lezione sono stati introdotti i fondamenti teorici del testing attraverso una formalizzazione matematica dei concetti principali. È stato definito il predicato di correttezza di un programma, il concetto di caso di test e test suite, e il problema del test ideale.

È stato dimostrato che il testing esaustivo, pur essendo ideale, è generalmente impraticabile, rendendo necessario l’uso di criteri di adeguatezza per selezionare sottoinsiemi di test significativi. Sono stati inoltre introdotti i concetti di criterio affidabile, criterio valido e criterio ideale, culminando nel teorema di Goodenough e Gerhart che collega testing e correttezza del programma.

Questi concetti costituiscono la base teorica per comprendere le tecniche di testing avanzate che verranno studiate nelle lezioni successive.