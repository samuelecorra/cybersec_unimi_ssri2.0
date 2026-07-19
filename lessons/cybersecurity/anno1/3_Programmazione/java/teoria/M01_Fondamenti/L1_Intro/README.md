# Intro.java – Introduzione al tuo primo programma Java

Questo file rappresenta il punto di partenza ideale per comprendere come è strutturato un programma Java.
Anche se *Intro.java* contiene pochissime righe di codice, al suo interno sono nascosti i concetti fondamentali dell’intero ecosistema Java: **package**, **classi**, **metodo main**, **visibilità** e **rapporto tra file e codice**.

## 1. Struttura di un progetto Java e ruolo dei package

In un progetto Java anche minimo, ogni file `.java` può appartenere a un **package**, cioè un contenitore logico che raggruppa classi affini.
La dichiarazione di package, quando presente, precede import e dichiarazioni di
tipo; può essere preceduta soltanto da commenti e spazi bianchi.

Esempio:

```java
package intro;
```

Per ora il concetto di package non è essenziale ai primi passi, ma è importante sapere che:

* Nei progetti reali è fondamentale organizzare il codice in package ordinati.
* La prima riga del file serve a Java per capire “dove” vive quella classe nel progetto.
* Quando un progetto cresce, i package diventano indispensabili per gestire visibilità, modularità e manutenzione.

## 2. Cos’è un programma Java?

Un programma Java è formato da **classi**.
Una classe è un *modello* da cui è possibile creare oggetti, e più classi possono collaborare tra loro per svolgere un compito.

Nei primi esempi il progetto sarà semplice: useremo una *singola* classe.

## 3. Il punto d’ingresso: il metodo `main`

Ogni programma eseguibile in Java deve possedere un **punto di ingresso**, cioè un punto ben preciso da cui l’esecuzione comincia.
Questo ruolo è svolto dal metodo:

```java
public static void main(String[] args)
```

* **public** → accessibile dall’esterno (la JVM deve poterlo chiamare)
* **static** → appartiene alla classe stessa, non serve creare oggetti
* **void** → non restituisce alcun valore
* **String[] args** → permette di passare argomenti da terminale

Il metodo `main` vive dentro una classe dedicata, e per programmi semplici sarà l’unico metodo presente.

## 4. Il nome del file deve coincidere con il nome della classe pubblica

Questa è la **regola d’oro di Java**:

> Il nome del file `.java` deve coincidere esattamente con il nome della classe pubblica definita al suo interno.

Se il file si chiama:

```
Intro.java
```

allora la classe dovrà essere:

```java
public class Intro {
    public static void main(String[] args) {
        // codice
    }
}
```

La regola riguarda ogni classe top-level dichiarata `public`: è il compilatore a
richiedere la corrispondenza tra il suo nome e quello del file. Un file può
contenere anche tipi top-level non pubblici con nomi diversi.

## 5. Classe pubblica e metodo `main`

Nel modello classico la classe principale si dichiara normalmente `public`, ma
il launcher può avviare anche una classe top-level con accesso di package. È
invece il metodo di ingresso a dover rispettare il contratto portabile
`public static void main(String[] args)`.

Vedremo più avanti tutti i livelli di visibilità (`public`, `private`,
`protected` e accesso di package), ma per ora basta sapere che:

* `public` rende il tipo o membro accessibile da ogni package compatibile con il sistema dei moduli;
* `static` permette al launcher di chiamare `main` senza costruire un oggetto;
* `void` indica che il metodo non restituisce un valore al chiamante.

## 6. Cosa troverai in *Intro.java*

Il file *Intro.java* contiene:

* la dichiarazione del package (eventuale)
* la dichiarazione della classe pubblica `Intro`
* il metodo `main`, punto di partenza del programma
* il tuo primissimo blocco di codice in Java (es. una stampa a schermo)

Si tratta del programma più semplice in assoluto, ma è anche la base da cui deriva tutto il resto: classi multiple, oggetti, metodi personalizzati, visibilità, interazione tra componenti, ecc.

---

## Conclusione

*Intro.java* è molto più di un file introduttivo: è il mattoncino fondamentale dell’intero universo Java.
Comprendere questa struttura iniziale ti permetterà di affrontare facilmente progetti più complessi, sapendo esattamente dove posizionare classi, package, metodi e logica.

Quando dominerai questo schema, Java inizierà veramente a prendere forma sotto le tue mani.
Buon coding!
