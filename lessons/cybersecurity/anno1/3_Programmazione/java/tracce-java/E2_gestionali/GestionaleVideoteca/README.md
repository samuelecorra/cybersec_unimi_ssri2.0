# 📄 README

## **VIDEOTECA – Gestionale di noleggio film (versione completa con menu testuale)**

Questo progetto realizza un **gestionale di videoteca** in Java, strutturato tramite classi separate e un *main* testuale che permette di registrare nuovi film, aggiungere clienti, gestire noleggi e restituzioni, e monitorare il numero di copie disponibili per ciascun film.

Il sistema è composto da quattro classi principali:

* `Film`
* `Cliente`
* `Videoteca`
* `Solver` (programma principale con menu interattivo)

---

## 🔹 **Scopo dell’esercizio**

L’obiettivo è progettare e implementare un mini–gestionale che sia in grado di:

1. **Registrare film nel catalogo**, specificando:

    * identificativo univoco
    * titolo
    * genere
    * numero di copie disponibili

2. **Aggiungere nuovi clienti** alla videoteca.

3. **Gestire il noleggio di un film**, con:

    * decremento automatico del numero di copie
    * registrazione del film nella lista del cliente

4. **Gestire la restituzione di un film**, con:

    * incremento del numero di copie
    * rimozione del film dalla lista del cliente

5. **Interagire tramite comandi testuali**, grazie al menu fornito dal programma principale `Solver`.

Questo esercizio sviluppa capacità fondamentali di programmazione a oggetti, tra cui:

* utilizzo di classi e incapsulamento
* uso di `ArrayList` per gestire insiemi dinamici
* progettazione di logica di ricerca tramite ID
* gestione dell’integrità dei dati (copie disponibili)
* costruzione di un’interfaccia testuale funzionante e robusta

---

## 🔹 **Struttura delle classi**

### **1. Classe `Film`**

Rappresenta una pellicola disponibile nella videoteca.

Contiene:

* `id`: identificativo numerico del film
* `copie`: numero di copie disponibili
* `titolo`
* `genere`

Include metodi per incrementare o decrementare il numero di copie e un `toString()` descrittivo.

---

### **2. Classe `Cliente`**

Rappresenta un cliente della videoteca.

Attributi:

* `id`
* `nome`
* lista di film noleggiati (`filmNoleggiati`)

Metodi fondamentali:

* `noleggiaFilm(Film film)`: registra un noleggio
* `restituisciFilm(int idFilm)`: rimuove il film se presente
* `getId()`
* `toString()` per mostrare la situazione del cliente

---

### **3. Classe `Videoteca`**

Contiene la logica principale.

Liste gestite:

* catalogo dei film
* cliente registrati

Funzioni principali:

* `aggiungiFilm(Film film)`

    * se l’ID esiste e titolo/genere combaciano → incrementa copie
    * altrimenti aggiunge un nuovo Film

* `registraCliente(Cliente cliente)`

* `noleggiaFilm(idFilm, idCliente)`

    * verifica che il film esista
    * controlla che ci siano copie disponibili
    * verifica che il cliente esista
    * decrementa copie e registra il noleggio

* `restituisciFilm(idFilm, idCliente)`

    * verifica cliente
    * rimuove il film dalla lista personale
    * incrementa il numero di copie

La ricerca di film e clienti avviene tramite scansione lineare delle liste.

---

## 🔹 **4. Classe `Solver` – Menu testuale**

È l’interfaccia principale del programma.
Mostra i comandi e gestisce l’interazione con l’utente tramite `Scanner`.

Menu disponibile:

```
Benvenuto nella Videoteca
Comandi disponibili:
* ADDFILM <id>,<titolo>,<genere>
* ADDCLIENTE <id>,<nome>
* NOLEGGIA <idFilm>,<idCliente>
* RESTITUISCI <idFilm>,<idCliente>
* END (per terminare)
```

### **Esempi di input validi:**

```
ADDFILM 5,Inception,Fantascienza
ADDCLIENTE 12,Marco
NOLEGGIA 5,12
RESTITUISCI 5,12
END
```

Ogni comando stampa un messaggio che indica se l’operazione è andata a buon fine.

---

## 🔹 **Finalità Didattica**

L’esercizio permette di:

* consolidare l’uso delle liste dinamiche
* applicare l’incapsulamento e la cooperazione tra classi
* controllare la consistenza dei dati (copie film)
* sviluppare logica gestionale realistica
* costruire e testare un sistema completo attraverso un'interfaccia testuale

Questa struttura può essere facilmente estesa con funzionalità aggiuntive, come:

* ricerca film per titolo/genere
* eliminazione clienti o film
* visualizzazione dello stato della videoteca
* salvataggio e caricamento dei dati da file

---
