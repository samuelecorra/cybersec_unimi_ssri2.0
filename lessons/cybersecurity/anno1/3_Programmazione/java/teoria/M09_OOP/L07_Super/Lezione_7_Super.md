# 📘 **L07 – La keyword `super` in Java**


La parola chiave `super` è uno strumento fondamentale della programmazione orientata agli oggetti in Java. Si usa ogni volta che una **sottoclasse** ha bisogno di interagire in modo esplicito con la **superclasse** da cui eredita.

`super` serve principalmente in tre situazioni:

1. per chiamare i **costruttori della superclasse**
2. per invocare **metodi della superclasse**, soprattutto se **sovrascritti**
3. per accedere ad **attributi della superclasse**, anche quando la sottoclasse ne ridefinisce uno con lo stesso nome

In questa lezione vediamo **perché esiste** `super`, **quando è obbligatorio**, e **come si usa correttamente**.

---


# 🟦 1. Perché esiste `super`


Quando una classe `B` estende una classe `A`, significa che `B` **contiene** anche tutta la parte di `A`.
Quindi costruire un oggetto `B` significa prima costruire la “parte A” e poi aggiungere la “parte B”.

Java garantisce questo processo in modo rigoroso:
👉 prima viene costruita la superclasse
👉 *poi* viene costruita la sottoclasse

Per gestire tutto questo, Java offre la parola chiave `super`.

---


# 🟦 2. Chiamare il costruttore della superclasse



La prima istruzione di ogni costruttore è una chiamata, implicita o esplicita,
a un altro costruttore: `this(...)` nella stessa classe oppure `super(...)`
nella superclasse. Le due forme non possono comparire insieme nello stesso
costruttore.

### ✔ Caso 1 — La superclasse ha un costruttore senza parametri

Se non scrivi né `this(...)` né `super(...)`, il compilatore inserisce automaticamente:

```java
super();
```

### ✔ Caso 2 — La superclasse NON ha un costruttore senza parametri

Allora devi chiamare esplicitamente un costruttore accessibile con `super(...)`,
passando i parametri richiesti, oppure delegare con `this(...)` a un costruttore
che lo faccia.

Se non lo fai ⇒ **errore di compilazione**.

### ✔ Esempio pratico (con obbligo di super)

```java
class Animale {
    String specie;

    Animale(String specie) {
        this.specie = specie;
    }
}
```

Qui **non esiste** un costruttore vuoto.

Quindi nella sottoclasse:

```java
class Cane extends Animale {

    String nome;
    int eta;

    Cane(String nome, int eta) {
        super("Canis lupus familiaris"); // obbligatorio
        this.nome = nome;
        this.eta = eta;
    }
}
```

---


# 🟦 3. Ordine reale di inizializzazione degli oggetti


Quando istanzi un oggetto di una sottoclasse, Java segue SEMPRE questo ordine:

1️⃣ I campi dell'oggetto ricevono prima i valori di default (`0`, `false`, `null`, ecc.)
2️⃣ La superclasse completa inizializzatori di istanza e corpo del proprio costruttore
3️⃣ Vengono eseguiti inizializzatori di istanza e assegnazioni dei campi della sottoclasse
4️⃣ Viene eseguito il corpo del costruttore della sottoclasse

Questo garantisce che la superclasse sia **sempre pronta** prima della sottoclasse.

---


# 🟦 4. Usare super per accedere ai METODI della superclasse


Se la sottoclasse **sovrascrive** un metodo, può comunque richiamare la versione originale:

```java
@Override
void verso() {
    super.verso(); // chiamo il metodo della superclasse
    System.out.println("Il cane abbaia.");
}
```

Questo meccanismo si chiama **estensione del comportamento**.

---


# 🟦 5. Usare super per accedere agli ATTRIBUTI della superclasse



Anche gli attributi possono essere “nascosti” nella sottoclasse.

### Esempio:

```java
class A {
    int x = 10;
}

class B extends A {
    int x = 20;

    void stampa() {
        System.out.println(x);       // 20
        System.out.println(this.x);  // 20
        System.out.println(super.x); // 10
    }
}
```

Questo caso si chiama **shadowing**.

---


# 🟦 6. Esempio completo e didattico


## 📌 Superclasse

```java
package M09_OOP.L07_Super;

public class Animale {

    boolean eVivo;
    String specie;

    Animale(String specie) {
        this.eVivo = true;
        this.specie = specie;
    }

    void verso() {
        System.out.println("L'animale emette un verso generico.");
    }
}
```

## 📌 Sottoclasse

```java
package M09_OOP.L07_Super;

public class Cane extends Animale {

    String nome;
    int eta;

    Cane(String nome, int eta) {
        super("Canis lupus familiaris"); // richiamo della superclasse
        this.nome = nome;
        this.eta = eta;
    }

    @Override
    void verso() {
        System.out.println("Il cane abbaia.");
    }

    void versoOriginale() {
        super.verso(); // chiamo il metodo della superclasse
    }
}
```

## 📌 Test

```java
package M09_OOP.L07_Super;

public class TestSuper {

    public static void main(String[] args) {

        Cane c = new Cane("Fido", 3);

        System.out.println("Specie: " + c.specie);
        System.out.println("Nome: " + c.nome);
        System.out.println("Età: " + c.eta);
        System.out.println("È vivo? " + c.eVivo);

        c.verso();           // metodo sovrascritto
        c.versoOriginale();  // metodo della superclasse
    }
}
```

---


# 🟩 7. Riepilogo finale (da esame)


### ✔ `super` serve per:

* chiamare i costruttori della superclasse
* accedere ai metodi della superclasse
* accedere agli attributi della superclasse
* evitare ambiguità tra metodi/attributi omonimi
* estendere il comportamento di un metodo

### ✔ `super(...)` esplicito è necessario se:

* la superclasse non ha un costruttore senza parametri accessibile e il
  costruttore corrente non delega con `this(...)`

### ✔ ordine di esecuzione:

1. valori di default dell'oggetto
2. inizializzatori e costruttore della superclasse
3. inizializzatori della sottoclasse
4. corpo del costruttore della sottoclasse

### ✔ `super.metodo()` richiama la versione “originale” sovrascritta.

### ✔ `super.attributo` accede all’attributo della superclasse in caso di shadowing.
