# 📄 README

## **VENDING MACHINE – Simulatore/gestionale di una macchinetta automatica (con menu testuale)**

Questo progetto implementa una **macchinetta automatica (vending machine)** che vende due tipi di prodotti: **bevande** e **merendine**. Il sistema è organizzato tramite classi astratte, ereditarietà e un programma di test interattivo basato su comandi testuali.

Le classi principali del progetto sono:

* `Prodotto` (astratta)
* `Merendina`
* `Bevanda`
* `VendingMachine`
* `VendingMachineMain` (programma principale con menu)

---

## 🔹 **Obiettivo dell’esercizio**

L’obiettivo è creare un gestionale semplice, ma completo, che permetta di:

1. **Aggiungere prodotti alla macchinetta**, distinguendo tra:

    * bevande (con volume)
    * merendine (con calorie)

2. **Gestire il credito inserito dall’utente**.

3. **Comprare i prodotti**, con:

    * verifica del credito
    * ricerca del prodotto in base a volume/calorie
    * rimozione del prodotto dalla macchinetta

4. **Mostrare i prodotti ordinati per costo**, grazie a una funzione dedicata.

5. **Interagire completamente da console**, tramite un set di comandi testuali.

Questo esercizio consolida concetti fondamentali della programmazione a oggetti, come ereditarietà, classi astratte, liste dinamiche, polimorfismo e gestione dei dati in un contesto simulato.

---

## 🔹 **Struttura delle classi**

### **1. Classe astratta `Prodotto`**

Rappresenta un generico prodotto venduto dalla macchinetta.

Possiede:

* `costo`
* `nome`

Questi attributi sono `final` per garantire immutabilità dopo la creazione.

---

### **2. Classe `Merendina`**

Sottoclasse di `Prodotto`.

Aggiunge:

* `calorie`

Метodo rilevante:

* `getCalorie()`

---

### **3. Classe `Bevanda`**

Sottoclasse di `Prodotto`.

Aggiunge:

* `volume` (in millilitri)

Metodo rilevante:

* `getVolume()`

---

### **4. Classe `VendingMachine`**

Contiene tutta la logica della macchinetta.

Gestisce una lista di `Prodotto` (merendine + bevande).

Funzioni principali:

* `aggiungiProdotto(Prodotto p)`
  Aggiunge una bevanda o merendina al catalogo.

* `compraMerendina(int soldi, int calorie)`
  Cerca una merendina con le calorie specificate e con costo ≤ soldi disponibili.

* `compraBevanda(int soldi, int volume)`
  Cerca una bevanda con il volume richiesto e con costo ≤ soldi.

* `stampaProdottiOrdinatiPerCosto()`
  Ordina tutti i prodotti per costo usando un `Comparator` e li stampa in ordine crescente.

La ricerca dei prodotti avviene con scansione lineare della lista (`for` con `instanceof` e cast).

---

## 🔹 **5. Classe `VendingMachineMain` – Menu testuale interattivo**

È il programma principale che permette all’utente di interagire con la macchinetta.

Comandi disponibili:

```
F      termina l’esecuzione
B      aggiunge una bevanda  (nome, costo, volume)
M      aggiunge una merendina (nome, costo, calorie)
S      aggiunge soldi alla macchinetta
CB     compra una bevanda (in base al volume)
CM     compra una merendina (in base alle calorie)
SORT   mostra tutti i prodotti ordinati per costo
```

### **Esempi di input validi**

```
M Kinder 2 150
B CocaCola 3 330
S 10
CB 330
CM 150
SORT
F
```

Alla fine di ogni operazione viene mostrato il credito disponibile.

---

## 🔹 **Finalità Didattica**

Questo progetto esercita:

* classi astratte e ereditarietà
* overriding e polimorfismo
* uso di `ArrayList`
* gestione del cast e `instanceof`
* implementazione di un sistema simulato basato su oggetti
* scrittura di un menu testuale con `Scanner`
* ordinamento via `Collections.sort()` e `Comparator`

È un esercizio perfetto per imparare a modellare entità reali tramite oggetti, simulando il comportamento di una macchinetta automatica completa.

---
