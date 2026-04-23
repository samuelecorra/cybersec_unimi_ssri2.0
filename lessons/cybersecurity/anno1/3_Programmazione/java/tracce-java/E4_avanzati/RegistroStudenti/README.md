# README

# ✅ **Registro Studenti con HashMap**

## **Descrizione generale**

Il progetto implementa un gestionale da terminale per gestire un registro di studenti.
L’obiettivo principale è mostrare in modo chiaro perché una `HashMap` è la struttura perfetta quando vogliamo associare un **ID (matricola)** a un **oggetto Studente** e accedervi rapidamente.

---

## **Classi del progetto**

### **1. Studente**

Rappresenta un singolo studente.

Campi:

* `int matricola`: identificativo univoco.
* `String nome`: nome dello studente.
* `List<Integer> voti`: lista dei voti.

Funzioni:

* Costruttore: inizializza lo studente e la lista dei voti.
* `aggiungiVoto(int)`: aggiunge un voto.
* `calcolaMedia()`: calcola la media dei voti (0.0 se nessun voto).
* Getter vari.
* `toString()`: rappresentazione completa dello studente, con voti e media.

---

### **2. RegistroStudenti**

Questa è la classe “gestionale”.

Struttura dati:

* `Map<Integer, Studente>` — chiave = matricola, valore = Studente.

Perché una HashMap?

* Accesso immediato allo studente tramite la matricola (`get`).
* Controllo semplice se esiste già (`containsKey`).
* Rimozione rapida (`remove`).
* Perfetta quando abbiamo un ID → oggetto.

Funzioni:

* `aggiungiStudente(matricola, nome)`
* `aggiungiVotoAStudente(matricola, voto)`
* `cercaStudente(matricola)`
* `rimuoviStudente(matricola)`
* `stampaRegistro()`

---

### **3. MainRegistroStudenti**

La classe con il `main`, che mostra un menù e permette all’utente di:

* Aggiungere studenti
* Aggiungere voti
* Calcolare medie
* Rimuovere studenti
* Cercare studenti
* Stampare tutto il registro

Sfrutta uno `Scanner` e uno `switch-case` per gestire le scelte.

---

## **Funzionamento della HashMap nel progetto**

Una `HashMap` associa:

* **CHIAVE (matricola)** → **VALORE (Studente)**

Vantaggi concreti:

* Non dobbiamo scorrere liste per cercare uno studente (come in un ArrayList).
* Possiamo aggiungere, rimuovere, aggiornare gli studenti in modo diretto ed efficiente.
* La struttura riflette perfettamente la logica di un registro reale: matricola → studente.

---
