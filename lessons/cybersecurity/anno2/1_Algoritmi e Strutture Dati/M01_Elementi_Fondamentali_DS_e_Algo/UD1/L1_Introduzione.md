# **Lezione 1 – Algoritmi, problemi computazionali e primi strumenti di rappresentazione**

Questa prima lezione è la nostra “accensione del motore”.  
Ci dà le basi per capire che cosa sia davvero un algoritmo, come si collega al concetto di problema computazionale, e quali strumenti minimi servono per rappresentare i dati su cui operiamo.  
In pratica, impariamo a distinguere il “cosa voglio ottenere” (il problema) dal “come ci arrivo” (l’algoritmo), e a tradurre tutto questo in procedure concrete, iterative o ricorsive, che la macchina possa comprendere e portare a termine. È la lezione che trasforma la curiosità in metodo: il primo passo verso il pensiero computazionale vero e proprio.

---

### **1. Algoritmo, problema e rappresentazione**

Ogni volta che ci si trova davanti a un compito da risolvere — ordinare, cercare, calcolare, scegliere — si è davanti a un problema computazionale: una relazione tra dati in ingresso e risultato desiderato.  
L’algoritmo è la strategia, la sequenza chiara e finita di operazioni che, passo dopo passo, permette di ottenere la risposta giusta.  
Deve essere preciso, finito, deterministico (cioè non ambiguo) ed eseguibile da un qualsiasi esecutore dotato di regole elementari.

In altre parole, se il problema è “voglio trovare l’elemento più piccolo in un insieme di numeri”, l’algoritmo è la ricetta per farlo, passo dopo passo, in modo affidabile e ripetibile.

Un algoritmo quindi non è solo codice: è pensiero organizzato.  
Il problema definisce l’obiettivo; l’algoritmo ne è la traduzione operativa.  
Capire questa differenza è ciò che distingue chi “usa” la programmazione da chi pensa come un informatico.

---

### **2. Esempio: trovare il minimo in un intervallo di un array**

Prendiamo un esempio concreto.

Abbiamo un array di numeri, $a$, e vogliamo sapere qual è il valore minimo compreso tra le posizioni $j$ e $k$.

Il problema è chiaro: vogliamo il numero più piccolo in quella porzione. 
Ora serve l’algoritmo, cioè il metodo per arrivarci.

I possibili approcci sono due:
  

#### **2.1 - Versione iterativa (passo dopo passo)**

1. Si suppone che il primo elemento a[j] sia il minimo. Questo è uno step manuale di inizializzazione che serve per progredire coi successivi!
    
2. Si scorrono tutti gli altri, da a[j+1] fino ad a[k].    
    
3. Ogni volta che si trova un elemento più piccolo, si aggiorna il minimo.    
    
4. Alla fine del ciclo, il valore più basso rimasto è il minimo assoluto.  

```c
#include <stdio.h>

int minimo_iterativo(int* array, int j, int k) {

    int min = array[j]; // Accedo al primo elemento e lo ipotizzo come minimo

    int i; // Dichiaro la variabile di ciclo

    for (i = j + 1; i <= k; i++) { // Ciclo dal secondo elemento fino all'ultimo
        // Uso un ternary operator per snellire il codice:
        min = array[i] < min ? array[i] : min;
    }
    return min;
}

int main() { // Ora possiamo utilizzare la funzione appena creata:

    int numeri[6] = {10, 4, 2000, 20, 13, 2004};
    int j = 0;
    int k = 5;

    int minimo = minimo_iterativo(numeri, j, k);

    printf("Il minimo dell'array e': %.2d\n", minimo);
}
```

Questo modo di ragionare è sequenziale: passo dopo passo, sempre nello stesso schema.  
È semplice, lineare, intuitivo: come guardare tutti i numeri in fila finché non si trova il più piccolo.

#### **2.2 - Versione ricorsiva (divide et impera)**

La ricorsione funziona come uno specchio: la funzione chiama se stessa su un problema un po’ più piccolo, finché non arriva a un caso così semplice da poter dare subito la risposta.

In questo caso:

- Se c’è un solo elemento, è per forza il minimo.  
    
- Altrimenti si calcola il minimo nel sottoproblema (dall’indice j+1 a k) e lo si confronta con a[j].  
      
- Il più piccolo tra i due è il minimo finale.  

La ricorsione funziona se — e solo se — ogni chiamata riduce il problema e c’è un caso base chiaro che fa terminare la catena.  
È come scendere una scala togliendo un gradino ogni volta: se togli sempre un gradino, prima o poi tocchi terra.

```c
#include <stdio.h>

int minimo_ricorsivo(int* array, int j, int k) {

    int min;

    if (j == k) { // Caso base: se l'array ha un solo elemento...
        min = array[j];
    } else {
        // Caso ricorsivo:
        min = minimo_ricorsivo(array, j+1, k);
        min = array[j] < min ? array[j] : min;
    }
    return min;
}

int main() { // Ora possiamo utilizzare la funzione appena creata:

    int numeri[6] = {20, 46, 92, 15, 13, 2004};
    int j = 0;
    int k = 5;

    int minimo = minimo_ricorsivo(numeri, j, k);

    printf("Il minimo dell'array e': %.2d\n", minimo);
}
```

Simuliamo cosa accadrebbe eseguendo il programma:

- Alla prima chiamata, finiamo sin da subito nel ramo else, che "esclude" il primo elemento, avanzando di 1 e chiamando ricorsivamente la funzione sui numeri dal secondo al sesto;
- Ora dunque, annidati di 1 livello, entriamo per la seconda volta nel ramo else, escludendo anche il secondo elemento e riducendo il dominio del sottoproblema a { 92, 15, 13, 2004 };
- Si continua ad annidarsi in continue chiamate ricorsive finché non si arriva ad escludere tutti i numeri tranne 2004, che viene settato quindi a minimo dal ramo if;
- A questo punto, avendo inizializzato finalmente la variabile min, iniziamo a risalire e togliere annidamenti un passo alla volta.
- Il primo step di risalita consiste dunque nell'andare a eseguire l'istruzione dopo l'ultima chiamata ricorsiva che aveva portato al caso base, ovvero quella con l'operatore ternario.
- Confrontiamo dunque penultimo e ultimo elemento, decidendo che 13 è il nuovo minimo trovato nel sottoproblema;
- Risaliamo ancora, confrontando 13 e 15, ergo la variabile min rimane invariata;
- Tutti i successivi confronti durante la risalita danno falso come esito: alla fine, il minimo trovato è 13!
---

### **3. Iterazione e ricorsione: due modi per pensare**

Un ciclo iterativo e una funzione ricorsiva fanno spesso la stessa cosa, ma parlano due linguaggi diversi.  
L’iterazione è “meccanica”: fa ripetere una sequenza finché una condizione è vera.  
La ricorsione è “logica”: definisce un problema in termini di sé stesso, ma più piccolo.

Capire entrambi i modi di ragionare è fondamentale, perché alcuni problemi si risolvono più naturalmente in forma ricorsiva (come nel caso dei percorsi in un albero o dei problemi di tipo “divide et impera”), mentre altri restano più chiari in forma iterativa.  
Un buon programmatore deve saper scegliere la forma che rende più trasparente il pensiero.

---

### **4. I tipi di dato: i mattoni della rappresentazione**

Ogni algoritmo ha bisogno di “contenitori” per conservare e manipolare le informazioni: i tipi di dato.  
In C — il linguaggio di riferimento di questo corso — questi mattoni sono numerici (come int, float, double), logici (boolean, tramite libreria), e strutturati (come gli array, che raccolgono più elementi dello stesso tipo).

Nel nostro esempio, l’array a rappresenta l’insieme dei numeri in ingresso, e la variabile m memorizza il risultato, cioè il minimo trovato.  
La scelta del tipo di dato non è mai neutra: influenza la chiarezza, la precisione e persino l’efficienza dell’algoritmo.

---

### **5. Cosa bisogna metabolizzare ASAP**

- Un problema computazionale è la relazione tra dati in ingresso e risultato atteso.  
      
    
- Un algoritmo è la sequenza ordinata di azioni che permette di calcolare quel risultato in modo finito e determinato.  
      
    
- Una procedura è l’implementazione di un algoritmo in un linguaggio di programmazione.  
      
    
- Le soluzioni iterative e ricorsive sono due modi diversi di affrontare lo stesso compito: la prima ripete, la seconda si richiama finché non raggiunge il caso base.  
      
    
- I tipi di dato sono il linguaggio con cui rappresentiamo tutto ciò che un algoritmo manipola: numeri, condizioni, insiemi di elementi.  

In sintesi, questa lezione ci ha fornito la grammatica minima del pensiero computazionale:  
sapere cosa significa “risolvere un problema”, come tradurre quella risoluzione in una forma eseguibile, e con quali strumenti rappresentare i dati del mondo digitale.

