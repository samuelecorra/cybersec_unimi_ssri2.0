# 📄 README

## **DISCHERIA – Gestionale di prestito dischi (versione completa con menu testuale)**

Questo progetto implementa un **mini-gestionale a oggetti** per una *discheria*, ovvero un sistema che permette di registrare clienti, aggiungere dischi musicali al catalogo e gestire **prestiti** e **restituzioni**.

Il programma è composto da quattro classi principali:

* `Disco`
* `Cliente`
* `Discheria`
* `Solver` (main di test con menu testuale)

---

## 🔹 **Obiettivo dell’esercizio**

L’obiettivo è costruire un sistema orientato agli oggetti in grado di:

1. **Memorizzare dischi** disponibili nel catalogo.
2. **Registrare clienti** della discheria.
3. **Consentire ai clienti di prendere in prestito dei dischi.**
4. **Gestire la restituzione** dei dischi presi.
5. **Interagire tramite una console testuale** usando un menu personalizzato.

Questo esercizio allena le seguenti competenze:

* progettazione di classi in Java
* gestione di liste tramite `ArrayList`
* ricerca lineare di elementi tramite ID
* incapsulamento dei dati e metodi dedicati
* progettazione di un “gestionale” semplice ma realistico
* separazione tra **logica del sistema** e **interfaccia testuale**

---

## 🔹 **Struttura delle classi**

### **1. Classe `Disco`**

Rappresenta un disco musicale con:

* `id` (intero, identificativo univoco)
* `titolo`
* `artista`

Contiene il costruttore, il getter dell’ID e il `toString()` descrittivo.

---

### **2. Classe `Cliente`**

Rappresenta un cliente della discheria.

Attributi:

* `id`
* `nome`
* `dischiPresi`: lista dei dischi attualmente in prestito

Funzioni principali:

* `prendiDisco(Disco disco)`
* `restituisciDisco(int idDisco)` con rimozione automatica
* `getId()`

---

### **3. Classe `Discheria`**

È il cuore del gestionale.

Mantiene:

* lista dei dischi nel catalogo
* lista dei clienti registrati

Funzionalità principali:

* `aggiungiDisco()`: aggiunge un disco al catalogo
* `registraCliente()`: registra un nuovo cliente
* `prendiDisco(idDisco, idCliente)`

    * verifica che il disco esista
    * verifica che il cliente esista
    * assegna il disco al cliente
* `restituisciDisco(idDisco, idCliente)`

    * verifica esistenza cliente
    * rimuove il disco dalla lista dei dischi presi

La ricerca degli elementi avviene tramite scansione semplice della lista (ricerca lineare).

---

### **4. Classe `DischeriaMain` (programma di test con menu testuale)**

Funziona come interfaccia utente console.

Alla partenza mostra i comandi disponibili:

```
Benvenuto nella Discheria
Comandi disponibili:
* ADD-DISCO <id>,<titolo>,<artista>
* ADD-CLIENTE <id>,<nome>
* PRENDI <idDisco>,<idCliente>
* RESTITUISCI <idDisco>,<idCliente>
* END (per terminare)
```

Il ciclo principale:

* legge input da tastiera
* interpreta il comando
* esegue l’operazione corrispondente
* stampa esito positivo o errore

### **Esempi di input validi**

```
ADD-DISCO 1,Back in Black,ACDC
ADD-CLIENTE 10,Marco
PRENDI 1,10
RESTITUISCI 1,10
END
```

---

## 🔹 **Finalità didattica**

Il progetto permette di:

* esercitarsi con classi multiple e interazione tra oggetti
* usare collezioni e metodi di ricerca
* progettare un mini sistema gestionale
* costruire un menu testuale completo e resistente agli input non validi
* simulare un caso d’uso reale con utenti, catalogo e prestiti

È una base perfetta per esercizi successivi più avanzati (salvataggio su file, rimozione dischi, log degli eventi, serializzazione, ecc.).

---
