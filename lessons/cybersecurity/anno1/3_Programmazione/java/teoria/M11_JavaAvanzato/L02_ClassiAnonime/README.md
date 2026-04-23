## 1. Idea intuitiva: che cos’è una *anonymous class*?

Immagina di dover creare **una volta sola** un oggetto con un piccolo comportamento personalizzato.

Potresti:

1. Dichiarare una nuova classe con `class MiaClasse implements Qualcosa { ... }`
2. Fare `new MiaClasse()` *solo in un punto del codice*

È **tanta burocrazia** per una cosa usata una sola volta.

👉 Java ti dice: “Vuoi solo un oggetto con quel comportamento? Te lo faccio creare **al volo**, senza dichiarare una nuova classe con un nome”.
Questa è la **classe anonima**.

> **Definizione:**
> Una *anonymous class* è una classe **senza nome**, dichiarata e istanziata **in un colpo solo**, usata di solito **una sola volta**, per personalizzare al volo il comportamento di una classe o di un’interfaccia.

---

## 2. Dove posso usare una classe anonima?

Solo in due situazioni:

1. **Per estendere una classe esistente**

```java
new SomeClass() {
    // override di metodi
};
```

2. **Per implementare un’interfaccia**

```java
new SomeInterface() {
    // implementazione dei metodi dell’interfaccia
};
```

In entrambi i casi:

* **crei una sottoclasse “al volo”**
* **NON gli dai un nome**
* **crei subito l’oggetto**

---

## 3. Esempio semplice con interfaccia

Partiamo da un’interfaccia:

```java
interface Saluto {
    void saluta();
}
```

### 3.1 Versione “classica” con classe nominata

```java
class SalutoItaliano implements Saluto {
    @Override
    public void saluta() {
        System.out.println("Ciao!");
    }
}

public class Main {
    public static void main(String[] args) {
        Saluto s = new SalutoItaliano();
        s.saluta();
    }
}
```

Qui abbiamo:

* `SalutoItaliano` ha un *nome*
* è riutilizzabile in tutto il progetto

---

### 3.2 Stessa cosa con **classe anonima**

```java
public class Main {
    public static void main(String[] args) {

        Saluto s = new Saluto() {
            @Override
            public void saluta() {
                System.out.println("Ciao!");
            }
        };

        s.saluta();
    }
}
```

Cosa succede qui?

* `new Saluto() { ... }`
  crea **una classe che implementa Saluto**
  ma **non ha nome**.
* Dentro le `{ ... }` scrivi l’override dei metodi richiesti.
* Alla fine hai **già l’istanza**, salvata in `s`.

Non puoi fare:

```java
new Saluto() { ... }; // SENZA assegnare a una variabile → inutile
```

ma tipicamente:

```java
Saluto s = new Saluto() { ... };
```

---

## 4. Esempio con `Runnable` e Thread

Qui vedi bene il pattern “una volta e basta”.

```java
public class Main {
    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Sto girando in un thread separato");
            }
        });

        t.start();
    }
}
```

* `new Runnable() { ... }` è una **classe anonima** che implementa `Runnable`.
* Viene usata **solo** per passare il comportamento al costruttore di `Thread`.

---

## 5. Esempio molto usato: `Comparator` per ordinare una lista

### 5.1 Senza classe anonima

```java
import java.util.*;

class ComparatorePerLunghezza implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> nomi = Arrays.asList("Samuele", "Erika", "Bob");

        nomi.sort(new ComparatorePerLunghezza());

        System.out.println(nomi);
    }
}
```

### 5.2 Con classe anonima

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> nomi = Arrays.asList("Samuele", "Erika", "Bob");

        nomi.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        System.out.println(nomi);
    }
}
```

Niente classe separata, tutto in linea con l’uso.

---

## 6. Forma generale della sintassi

Caso 1 – **estendere una classe**

```java
ClasseBase oggetto = new ClasseBase(/*eventuale costruttore*/) {
    // eventuali campi aggiuntivi
    // eventuali metodi aggiuntivi
    @Override
    public void metodoDaSovrascrivere() {
        // nuovo comportamento
    }
};
```

Caso 2 – **implementare un’interfaccia**

```java
Interfaccia i = new Interfaccia() {
    @Override
    public void metodo1() { ... }

    @Override
    public void metodo2() { ... }
};
```

---

## 7. Limiti e caratteristiche importanti

1. **Non ha nome**
   Non puoi fare:

   ```java
   NomeClasseAnonima x; // impossibile, non esiste un nome
   ```

2. **Non è riutilizzabile**
   Se vuoi lo stesso comportamento in un altro punto del codice:

    * o **copi/incolli** (brutto),
    * o ti crei una classe nominata normale e la usi ovunque.

3. **Viene istanziata subito**
   Class declaration + `new` nello stesso punto.

4. **Spesso è usata per:**

    * `Runnable`, `Callable`
    * `Comparator`
    * listener grafici (es. `ActionListener` in Swing)
    * callback di librerie

---

## 8. Perché sono nate, storicamente?

Prima di **Java 8** non esistevano le **lambda**.

Se volevi “passare un pezzo di codice” come parametro (un’azione da eseguire), l’unico modo era:

* un’interfaccia con un singolo metodo
* **classe anonima** che implementava quell’interfaccia

Esempio:

```java
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click!");
    }
});
```

Con Java 8 → lambda:

```java
button.addActionListener(e -> System.out.println("Click!"));
```

---

## 9. Anonymous class vs Lambda (rapido confronto mentale)

* **Anonymous class**

    * può implementare *qualsiasi* interfaccia (anche con più metodi)
    * può estendere una classe concreta
    * puoi dichiarare campi e metodi aggiuntivi
    * sintassi più verbosa

* **Lambda**

    * funziona **solo** con interfacce funzionali (un solo metodo astratto)
    * non ha nome e non dichiari metodi dentro
    * molto più compatta
    * è il modo moderno, preferito

Spesso vedi ancora anonime dove:

* la libreria è vecchia
* l’interfaccia ha **più di un metodo abstract**
* serve una piccola classe al volo che estende una classe base

---

## 10. Mini–riassunto “da ricordare”

* Una **anonymous class** è una **classe senza nome**, creata e istanziata nello stesso punto.
* Serve per **definire al volo** una sottoclasse di:

    * una **classe** esistente, oppure
    * un’**interfaccia**
* Viene usata per **casi singoli**, come callback, listener, comparatori, thread.
* È stata il ponte storico verso la programmazione “a funzioni” in Java, prima delle lambda.

---

Se vuoi, nella **prossima lezione** possiamo fare:

* solo **esercizi guidati** su anonymous class (es. simuliamo un piccolo sistema di eventi),
  oppure
* il passo successivo naturale: **lambda expressions**, partendo proprio dagli esempi con anonymous class e trasformandoli passo–passo in lambda.
