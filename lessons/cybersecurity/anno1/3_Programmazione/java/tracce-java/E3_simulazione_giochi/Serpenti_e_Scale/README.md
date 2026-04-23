
# README
## 🎲 Serpenti e Scale – Simulatore da Terminale (Java)

Questo mini-progetto implementa una versione testuale del celebre gioco **Serpenti e Scale**, con un’architettura completamente a oggetti:

* `Giocatore`
* `Dado`
* `Tabellone`
* `Partita`
* `SerpentiEScaleGame` (classe `main`)

La parte nuova e più educativa, rispetto ai gestionali sviluppati finora, è l’introduzione dell’uso delle **Mappe** e delle **HashMap**, strumenti fondamentali nella programmazione Java moderna.

---

## 🧠 Perché usare una `Map<Integer, Integer>` per serpenti e scale?

Nel tabellone del gioco alcune caselle **“puntano”** ad altre caselle:

* se arrivi in cima a una **scala**, sali;
* se finisci sulla testa di un **serpente**, scendi.

Serve quindi una struttura capace di associare:

```
casella_di_partenza → casella_di_arrivo
```

E questa associazione 1-a-1 è esattamente ciò che fa una **Map**.

---

# 🗂️ Cos’è una Map?

In Java, una `Map<K, V>` è una struttura dati che memorizza **coppie chiave-valore**:

* ogni **chiave** è unica
* a ogni chiave è associato un **valore**

### Esempio intuitivo:

```
4  →  14   (scala)
17 →  7    (serpente)
99 → 78    (serpente)
```

Questo è perfetto per modellare il nostro tabellone.

---

# ⚡ Cos’è una HashMap?

`HashMap` è la più comune implementazione di `Map`.

Caratteristiche:

* accesso **istantaneo** (O(1) medio) alla destinazione data la casella di partenza
* nessun ordine specifico degli elementi
* chiave univoca
* ottima per lookup veloci (perfetta per il nostro turno di gioco)

### Come si definisce:

```java
Map<Integer, Integer> serpentiEScale = new HashMap<>();
```

### Come si inseriscono dati:

```java
serpentiEScale.put(4, 14);   // scala
serpentiEScale.put(17, 7);   // serpente
```

### Come si controlla se una casella ha un effetto:

```java
if (serpentiEScale.containsKey(posizione)) {
    // allora è serpente o scala
}
```

### Come si recupera la destinazione:

```java
int nuovaPosizione = serpentiEScale.get(posizione);
```

---

# 🐍 Perché è congeniale al nostro gioco?

Usare una `HashMap` rende il codice:

### ✔ Pulito

Ogni serpente o scala è solo una coppia chiave→valore, senza condizioni `if` annidate.

### ✔ Estendibile

Vuoi aggiungere un tabellone custom? Basta aggiungere altre associazioni.

### ✔ Efficiente

Il lookup della destinazione della casella è immediato, quindi il gioco scorre veloce.

### ✔ Mantenibile

Se un domani vuoi caricare il tabellone da file JSON o CSV, la `Map` è perfetta per questo.

---

# 🏗️ Architettura del progetto

```
src/
 ├── Giocatore.java
 ├── Dado.java
 ├── Tabellone.java      → contiene la HashMap con serpenti e scale
 ├── Partita.java        → gestisce turni, movimento e controllo vittoria
 └── SerpentiEScaleGame.java (main)
```

---

# 📦 Strutture dati usate e perché

### `List<Giocatore>`

Per gestire in modo ordinato i turni dei giocatori.

### `Dado`

Classe riutilizzabile e testabile (due dadi per turno → nessuna duplicazione).

### `Map<Integer,Integer>` / `HashMap`

Il cuore del tabellone.

### `Scanner`

Per l'interazione da terminale.

---

# 🎯 Cosa impari da questo progetto

* Uso corretto delle **collezioni Java**: `List`, `Map`, `HashMap`
* Modellazione di un sistema con più classi interconnesse
* Gestione di stato e cicli di gioco
* Separazione netta tra:

    * modello (`Giocatore`, `Tabellone`, `Dado`)
    * logica (`Partita`)
    * interfaccia testuale (`main`)

---

# ▶️ Avvio del gioco

Compila ed esegui:

```bash
javac *.java
java SerpentiEScaleGame
```
Se invece sei già su IntelliJ, puoi debuggare con un click...

---


