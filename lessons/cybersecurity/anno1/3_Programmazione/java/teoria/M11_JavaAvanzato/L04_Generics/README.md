## 0. Che cosa sono i Generics (idea intuitiva)

**Idea chiave:**
I *Generics* ti permettono di scrivere **classi, interfacce e metodi** che funzionano **con tipi diversi**, mantenendo **il controllo statico sui tipi** (cioè controllato dal compilatore).

* Senza generics:
  usi `Object`, fai cast dappertutto, rischi errori a runtime.
* Con generics:
  scrivi una volta il codice, lo usi con qualsiasi tipo (`String`, `Integer`, `Utente`, ecc.), ma il compilatore controlla che non fai casini.

Nella sintassi:

* `T`, `E`, `K`, `V` … = **type parameter** → “segnaposto” per un tipo.
* `List<String>` = **type argument** → il tipo reale che rimpiazza il segnaposto, qui `String`.

---

## 1. Quando sono stati introdotti e perché

### 1.1. Quando

I generics sono stati introdotti in **Java 5**
(ufficialmente **J2SE 5.0**, rilasciata nel **2004**).

Prima di Java 5:

* esistevano già le collezioni (`Vector`, `ArrayList`, `HashMap`…),
* ma **non erano tipizzate**: tutto era di tipo `Object`.

### 1.2. Perché sono così rilevanti

Tre motivi fondamentali:

1. **Type safety (sicurezza di tipo a compile time)**

    * Prima dei generics: mettevi oggetti diversi in una `ArrayList`, li tiravi fuori, facevi cast… e potevi avere `ClassCastException` a runtime.
    * Con i generics: il compilatore ti impedisce proprio di fare l’inserimento sbagliato.

2. **Meno cast, codice più leggibile**

    * Senza generics:

      ```java
      ArrayList lista = new ArrayList();
      lista.add("Ciao");
      String s = (String) lista.get(0); // cast obbligatorio
      ```
    * Con generics:

      ```java
      ArrayList<String> lista = new ArrayList<>();
      lista.add("Ciao");
      String s = lista.get(0); // niente cast
      ```

3. **Riutilizzabilità e astrazione**

    * Puoi scrivere una struttura dati (stack, lista collegata, repository, cache…) **una sola volta**, e usarla con qualunque tipo: `Stack<Integer>`, `Stack<String>`, `Stack<Utente>` ecc.

---

## 2. Il problema prima dei Generics

Partiamo dall’errore tipico “pre-generics”.

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList lista = new ArrayList(); // nessun <String>, nessun tipo

        lista.add("Ciao");
        lista.add(42); // int autoboxato in Integer

        // Io penso (sbagliando) che dentro ci siano solo String
        for (int i = 0; i < lista.size(); i++) {
            String s = (String) lista.get(i); // CAST
            System.out.println(s.toUpperCase());
        }
    }
}
```

Questo **compila**, ma a runtime:

* per il primo elemento (`"Ciao"`) tutto ok;
* quando arrivi al `42`, hai:

```text
Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
```

Quindi:

* **Errore individuato tardi** (a runtime, magari dal cliente…)
* Debug più complesso.

Con i generics lo stesso codice non compila proprio.

---

## 3. Prima vista ai generics con le collezioni

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> nomi = new ArrayList<>(); // <String> è il tipo degli elementi

        nomi.add("Alice");
        nomi.add("Bob");
        // nomi.add(42); // ERRORE di compilazione: 42 non è una String

        for (String nome : nomi) {
            System.out.println(nome.toUpperCase());
        }
    }
}
```

Qui:

* `List<String>` → stai dicendo: “è una lista che contiene **solo** `String`”.
* Se provi ad aggiungere un `Integer`, il compilatore ti blocca prima ancora che il programma giri.

---

## 4. Classi generiche: `class Box<T>`

Adesso non usiamo solo le collezioni, ma **scriviamo noi una classe generica**.

### 4.1. Definizione

```java
// Classe generica Box che può contenere UN oggetto di tipo T
class Box<T> {

    private T valore; // T è un segnaposto per il tipo reale

    public Box(T valore) {
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    @Override
    public String toString() {
        return "Box contiene: " + valore;
    }
}
```

* `class Box<T>`:

    * `<T>` è il **type parameter**.
    * Dentro la classe puoi usare `T` come fosse un tipo normale.

### 4.2. Uso nel `main`

```java
public class Main {
    public static void main(String[] args) {

        Box<String> scatolaStringhe = new Box<>("Ciao");
        String s = scatolaStringhe.getValore(); // già String, niente cast
        System.out.println(s.toUpperCase());

        Box<Integer> scatolaInteri = new Box<>(123);
        Integer n = scatolaInteri.getValore();
        System.out.println(n + 10);

        // Box<Double>, Box<Utente>, Box<List<String>>...
    }
}
```

Qui hai **una sola classe `Box`**, ma la usi come se avessi centinaia di varianti:

* `Box<String>`
* `Box<Integer>`
* `Box<Utente>`
* …

---

## 5. Metodi generici: `<T> void metodo(...)`

I generics non sono solo per le classi: puoi avere **metodi generici**, anche in classi non generiche.

### 5.1. Esempio: stampa di un array di qualunque tipo

```java
public class Main {

    // Metodo generico: il tipo T è dichiarato davanti al tipo di ritorno
    public static <T> void stampaArray(T[] array) {
        for (T elem : array) {
            System.out.println(elem);
        }
    }

    public static void main(String[] args) {

        String[] parole = {"Java", "Generics", "Sono", "Potenti"};
        Integer[] numeri = {1, 2, 3, 4, 5};

        stampaArray(parole);  // T viene inferito come String
        stampaArray(numeri);  // T viene inferito come Integer
    }
}
```

* `<T>` prima di `void` → **dichiara** il parametro di tipo del metodo.
* Quando chiami `stampaArray(parole)`, il compilatore “deduce” che `T` = `String`.
* Quando chiami `stampaArray(numeri)`, `T` = `Integer`.

---

## 6. Interfacce generiche

Molte interfacce della libreria standard sono generiche.
L’esempio più famoso che già usi senza accorgertene:

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

Quando scrivi:

```java
class Studente implements Comparable<Studente> {

    private String nome;
    private int voto;

    public Studente(String nome, int voto) {
        this.nome = nome;
        this.voto = voto;
    }

    @Override
    public int compareTo(Studente altro) {
        // Ordina per voto crescente
        return Integer.compare(this.voto, altro.voto);
    }

    @Override
    public String toString() {
        return nome + " (" + voto + ")";
    }
}
```

E nel `main`:

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Studente> studenti = new ArrayList<>();
        studenti.add(new Studente("Alice", 28));
        studenti.add(new Studente("Bob", 24));
        studenti.add(new Studente("Carla", 30));

        Collections.sort(studenti); // usa Comparable<Studente>
        System.out.println(studenti);
    }
}
```

Grazie ai generics:

* `Comparable<Studente>` ti garantisce che `compareTo` prenda **sempre** un `Studente`.
* Non rischi di confrontare un `Studente` con un `Gatto`.

---

## 7. Bounded generics: `<T extends ...>`

A volte vuoi dire: “Questo tipo generico T deve essere **almeno** di un certo tipo base”.
Lo fai con **upper bound**:

```java
// Accetta qualunque tipo che sia Number o sottoclasse (Integer, Double, ...)
class StatisticheNumeriche<T extends Number> {

    private T valore;

    public StatisticheNumeriche(T valore) {
        this.valore = valore;
    }

    public double raddoppia() {
        return valore.doubleValue() * 2;
    }
}
```

Uso:

```java
public class Main {
    public static void main(String[] args) {

        StatisticheNumeriche<Integer> snInt = new StatisticheNumeriche<>(10);
        StatisticheNumeriche<Double> snDouble = new StatisticheNumeriche<>(3.14);

        System.out.println(snInt.raddoppia());
        System.out.println(snDouble.raddoppia());

        // StatisticheNumeriche<String> snString = new StatisticheNumeriche<>("ciao");
        // ERRORE: String non estende Number
    }
}
```

Quindi:

* `<T extends Number>` limita T solo a tipi numerici.
* Dentro la classe sai che `valore.doubleValue()` esiste sempre.

Puoi anche avere **più vincoli**:

```java
class ArchivioOrdinabile<T extends Number & Comparable<T>> {
    // T deve estendere Number e implementare Comparable<T>
}
```

---

## 8. Wildcard: `?`, `? extends ...`, `? super ...`

Le wildcard arrivano quando vuoi scrivere metodi flessibili che accettano collezioni di tipi *compatibili*.

### 8.1. `?` semplice

```java
public static void stampaLista(List<?> lista) {
    for (Object elem : lista) {
        System.out.println(elem);
    }
}
```

* `List<?>` = “lista di **qualcosa**, non mi importa esattamente cosa”.
* Puoi **leggere** come `Object`, ma non puoi aggiungere (tranne `null`) perché il tipo esatto è sconosciuto.

### 8.2. `? extends T` – upper bounded wildcard

```java
public static double somma(List<? extends Number> lista) {
    double somma = 0;
    for (Number n : lista) {
        somma += n.doubleValue();
    }
    return somma;
}
```

* `List<? extends Number>` = lista di **qualunque sottotipo di Number** (`List<Integer>`, `List<Double>`, ecc.).
* Puoi **leggere** come `Number`, ma **non aggiungere** perché non sai il sottotipo preciso.

### 8.3. `? super T` – lower bounded wildcard

```java
public static void aggiungiNumeriDa1a10(List<? super Integer> lista) {
    for (int i = 1; i <= 10; i++) {
        lista.add(i); // ok
    }
}
```

* `List<? super Integer>` = lista il cui tipo è `Integer` o un suo **supertipo** (`Integer`, `Number`, `Object`).
* Qui puoi **scrivere Integer** tranquillamente (perché qualsiasi supertipo può contenerlo),
* ma quando **leggi**, ottieni solo `Object`.

Regola mentale famosa:

* **PECS** = *Producer Extends, Consumer Super*

    * *Producer Extends*: se la lista **produce** dati (la leggi) → `? extends T`.
    * *Consumer Super*: se la lista **consuma** dati (ci scrivi) → `? super T`.

---

## 9. Type erasure: il “trucco” dietro le quinte

Domanda naturale:
“Ma a runtime, `List<String>` e `List<Integer>` sono tipi diversi?”

In Java, **no**.
A runtime esiste solo **`List`**, senza informazione sul tipo generico.
Questo è il meccanismo di **type erasure** (cancellazione dei tipi).

### 9.1. Cosa fa il compilatore

Quando compila una classe generica:

1. **Sostituisce** i parametri di tipo (`T`) con il loro **bound** (o `Object` se non c’è bound).
2. **Inserisce cast** dove serve.
3. Elimina le informazioni sui parametri di tipo a runtime.

Esempio intuitivo:

```java
class Box<T> {
    private T valore;
    public T getValore() { return valore; }
}
```

Dopo l’erasure diventa qualcosa tipo:

```java
class Box {
    private Object valore;
    public Object getValore() { return valore; }
}
```

Il bello è che:

* il compilatore controlla i tipi,
* ma a runtime il bytecode rimane compatibile con il vecchio Java (pre-generics).

### 9.2. Conseguenze pratiche

Per via dell’erasure:

* Non puoi fare `new T()`:

  ```java
  class C<T> {
      // T x = new T(); // proibito
  }
  ```
* Non puoi fare `instanceof` con un tipo generico parametrizzato:

  ```java
  // if (obj instanceof List<String>) { ... } // vietato
  if (obj instanceof List<?>) { ... }        // con wildcard sì
  ```
* Non puoi avere `List<String>` e `List<Integer>` come overload distinti:

  ```java
  void m(List<String> x) { }
  void m(List<Integer> x) { } // ERRORE: stesso tipo dopo erasure
  ```

---

## 10. Perché i generics sono fondamentali **oggi**

Nel Java moderno:

* **tutte** le collezioni (`List`, `Set`, `Map`, `Queue`…) sono generiche;
* moltissime API esposte dai framework (Spring, Hibernate, ecc.) usano generics;
* la programmazione generica è essenziale per:

    * strutture dati personalizzate,
    * repository di oggetti,
    * API pulite e type-safe,
    * programmazione funzionale con `Stream<T>`.

Se vuoi scrivere codice Java:

* **pulito**,
* **sicuro**,
* **riutilizzabile**,

devi essere **molto a tuo agio** con:

1. dichiarazione di classi e metodi generici;
2. uso dei generics con le collezioni;
3. wildcard (`?`, `? extends`, `? super`);
4. lettura di firme complicate nelle API (tipo `Map<? extends K, ? extends V>`).

---

## 11. Mini-riassunto “a memoria per l’esame”

* Generics introdotti in **Java 5 (J2SE 5.0, 2004)**.
* Servono per:

    * **type safety** (niente più `ClassCastException` per errori banali),
    * **riusabilità** (una classe/metodo funziona con tanti tipi),
    * **leggibilità** (meno cast, tipi espliciti).
* Sintassi base:

    * `class Nome<T> { ... }`
    * `class Nome<K, V> { ... }`
    * `public <T> void metodo(T x) { ... }`
* Collezioni generiche:

    * `List<String>`, `Map<String, Integer>`, `Set<Utente>`, ecc.
* Bounded generics:

    * `<T extends Number>` (upper bound),
    * `<T extends Number & Comparable<T>>`,
    * wildcard: `List<?>`, `List<? extends Number>`, `List<? super Integer>`.
* Sotto il cofano: **type erasure** → compatibilità con il vecchio bytecode, ma niente informazioni di tipo a runtime.
