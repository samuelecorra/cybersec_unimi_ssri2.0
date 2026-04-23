
---

# 📄 README

## **GESTIONALE BIBLIOTECA – DESCRIZIONE DELL’ESERCIZIO**

Questo progetto implementa un **gestionale di base per una biblioteca**, costruito interamente in Java, organizzato in package distinti e composto da tre classi principali:

* `Libro`
* `Utente`
* `Biblioteca`

In aggiunta, è presente la classe `BibliotecaMain`, che funge da **programma di test** e integra un **menu testuale personalizzato** per interagire con il sistema.

---

## 🔹 **Obiettivo generale**

L’obiettivo dell’esercizio è sviluppare un piccolo sistema gestionale capace di:

1. **Registrare utenti** della biblioteca.
2. **Aggiungere libri** nel catalogo.
3. **Effettuare prestiti** di libri da un utente.
4. **Effettuare restituzioni** di libri.
5. **Elencare e manipolare questi dati** tramite una classe apposita (`Biblioteca`) che funge da contenitore e controller.

Il tutto deve essere progettato usando:

* classi separate
* encapsulation
* costruttori corretti
* collezioni (`ArrayList`)
* una logica pulita di ricerca (`trovaLibroPerId`, `trovaUtentePerId`)
* messaggi di feedback espliciti verso l’utente

---

## 🔹 **Struttura delle Classi**

### **1. Libro**

Rappresenta un libro identificato da:

* `id` (intero, univoco)
* `titolo`
* `autore`

È immutabile grazie all’uso di attributi `final`.

---

### **2. Utente**

Rappresenta un utente della biblioteca, identificato da:

* `id` (intero, univoco)
* `nome`
* lista di libri attualmente presi in prestito

Metodi principali:

* `addLibro()` per aggiungere un libro in prestito
* `removeLibro()` per restituirlo

---

### **3. Biblioteca**

È la classe centrale che:

* mantiene la lista dei libri disponibili
* mantiene la lista degli utenti registrati
* gestisce prestiti e restituzioni
* implementa le funzioni di ricerca interne

Metodi principali:

* `aggiungiLibro()`
* `registraUtente()`
* `prestaLibro()`
* `restituisciLibro()`

---

### **4. BibliotecaMain**

Contiene il **programma di test**, che permette di interagire con il gestionale tramite un **menu testuale personalizzato**.

Il menu supporta i seguenti comandi:

```
ADDLIBRO <id>,<titolo>,<autore>
ADDUTENTE <id>,<nome>
PRESTA <idLibro>,<idUtente>
RESTITUISCI <idLibro>,<idUtente>
END
```

Esempi:

```
ADDLIBRO 1,Il Signore degli Anelli,Tolkien
ADDUTENTE 10,Marco
PRESTA 1,10
RESTITUISCI 1,10
END
```

Il main legge continuamente istruzioni da tastiera, interpreta il comando e chiama i metodi corrispondenti della classe `Biblioteca`, mostrando messaggi di successo o errore.

---

## 🔹 **Funzionalità Verificate dal Main**

Il main serve come test completo per verificare che:

* la creazione e registrazione di libri e utenti funzioni
* la ricerca per ID sia corretta
* il prestito rimuova correttamente il libro dalla lista dei disponibili e lo assegni all’utente
* la restituzione lo riporti tra i disponibili
* l'interazione tramite input dell’utente sia ben gestita
* il sistema risponda con messaggi chiari e coerenti

---

## 🔹 **Obiettivo Didattico**

Questo esercizio allena a:

* progettazione di classi in Java
* uso di package
* gestione delle collezioni (`ArrayList`)
* encapsulation e uso corretto di `final`
* separazione tra logica (classe Biblioteca) e interfaccia testuale (Main)
* creazione di un semplice “gestionale” multi-entità

È un ottimo primo esempio di **mini-applicazione a oggetti** con logica reale.

---
