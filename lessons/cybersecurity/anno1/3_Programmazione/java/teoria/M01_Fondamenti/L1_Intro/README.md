# Intro.java – Introduzione al tuo primo programma Java

Questo file rappresenta il punto di partenza ideale per comprendere come è strutturato un programma Java.
Anche se *Intro.java* contiene pochissime righe di codice, al suo interno sono nascosti i concetti fondamentali dell’intero ecosistema Java: **package**, **classi**, **metodo main**, **visibilità** e **rapporto tra file e codice**.

## 1. Struttura di un progetto Java e ruolo dei package

In un progetto Java anche minimo, ogni file `.java` può appartenere a un **package**, cioè un contenitore logico che raggruppa classi affini.
Il package viene dichiarato *sempre* come **prima riga** del file; è una sorta di intestazione.

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

Il motivo è semplice: quando la JVM deve eseguire il programma, deve sapere con certezza qual è la classe principale.
Non rispettare questa regola genera errori di compilazione.

## 5. Perché la classe deve essere `public`?

La classe che contiene il `main` deve essere dichiarata come `public` perché la JVM (che è esterna al codice) deve potervi accedere.
Vedremo più avanti tutti i livelli di visibilità (`public`, `private`, `protected` e default), ma per ora ci basta sapere che:

* **public = accessibile da chiunque**
* serve obbligatoriamente per rendere eseguibile la classe da Java

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