# Teoria Java

Il percorso raccoglie esempi eseguibili e commentati, organizzati in ordine
progressivo dai fondamenti del linguaggio fino a programmazione a oggetti,
gestione dei file e costrutti avanzati.

## Moduli

- `M01_Fondamenti`: struttura di un programma, output, tipi, variabili, input,
  conversioni numeriche e overflow.
- `M02_Matematica`: aritmetica, `Math`, numeri pseudocasuali e operatori bitwise.
- `M03_Condizioni_e_Logica`: `if`, condizioni annidate, ternario, switch
  expression e operatori logici.
- `M04_Stringhe`: API di `String`, sottostringhe, Unicode, immutabilità e
  `StringBuilder`.
- `M05_Cicli`: `while`, `do-while`, `for`, enhanced for, `break`, `continue` e
  cicli annidati.
- `M06_Metodi`: parametri, valori di ritorno, overloading, varargs, passaggio
  per valore e ricorsione.
- `M07_ScopeVariabili`: visibilità, shadowing, durata, `final` e costanti di classe.
- `M08_Arrays`: array monodimensionali e bidimensionali, ricerca e API di
  supporto.
- `M09_OOP`: classi, costruttori, ereditarietà, polimorfismo, incapsulamento,
  composizione, collezioni ed eccezioni.
- `M10_FileHandling`: lettura e scrittura di file con le principali API Java.
- `M11_JavaAvanzato`: date, classi anonime, timer, generics, mappe, enum,
  concorrenza, interfacce funzionali e lambda expression.

## Compilazione degli esempi

Tutti i sorgenti sono compatibili con Java 17. Dalla cartella
`teoria`, un singolo esempio con package può essere compilato ed eseguito così:

```text
javac M01_Fondamenti/L1_Intro/Intro.java
java M01_Fondamenti.L1_Intro.Intro
```

Ogni classe dimostrativa espone il punto di ingresso portabile
`public static void main(String[] args)`.
