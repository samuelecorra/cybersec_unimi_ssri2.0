# Lambda expression e interfacce funzionali

Una lambda è una forma compatta per fornire il comportamento richiesto dal
singolo metodo astratto di un'interfaccia funzionale. Non è un metodo autonomo
e non sostituisce qualunque classe anonima: il tipo della lambda è determinato
dal contesto, per esempio `Predicate<String>` o `Comparator<Integer>`.

```java
Predicate<String> nonVuota = testo -> !testo.isEmpty();
Comparator<String> perLunghezza =
        Comparator.comparingInt(String::length);
```

La parte a sinistra di `->` contiene i parametri; quella a destra è una singola
espressione oppure un blocco. Se il blocco deve produrre un risultato, usa
`return`:

```java
BinaryOperator<Integer> massimo = (a, b) -> {
    if (a >= b) {
        return a;
    }
    return b;
};
```

Le principali interfacce di `java.util.function` sono:

- `Predicate<T>`: `T -> boolean`;
- `Function<T, R>`: `T -> R`;
- `Consumer<T>`: `T -> void`;
- `Supplier<T>`: `() -> T`;
- `UnaryOperator<T>` e `BinaryOperator<T>`: operazioni che mantengono lo stesso tipo.

Un method reference come `String::length` o `System.out::println` è una forma
più diretta della lambda equivalente quando questa si limita a chiamare un
metodo già esistente.

Le variabili locali catturate devono essere `final` o *effectively final*. I
campi dell'oggetto possono invece cambiare, quindi l'uso concorrente di lambda
che modificano stato condiviso richiede le stesse cautele di qualunque altro
codice multithread.

Il sorgente `LambdaExpressions.java` mostra un'interfaccia funzionale propria,
le interfacce standard, l'ordinamento, i method reference e la cattura delle
variabili locali.
