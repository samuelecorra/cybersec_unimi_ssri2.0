
# README

L’idea è simulare un ristorante che gestisce tavoli e prenotazioni usando `HashMap`.

### 1. Modellazione delle classi

* **`Tavolo`**
  Rappresenta un tavolo fisico:

    * `numero` → identificativo univoco del tavolo.
    * `posti` → quanti posti ha il tavolo.
    * È *immutabile*: i campi sono `final` e non ci sono setter. Una volta creato, quel tavolo rimane così.

* **`Prenotazione`**
  Rappresenta una singola prenotazione:

    * `nomeCliente` → chi ha prenotato.
    * `numeroPersone` → per quante persone è la prenotazione.
    * Anche qui immutabilità: solo costruttore + getter.

* **`Ristorante`**
  È il “gestore” centrale, che usa due mappe:

    * `Map<Integer, Tavolo> tavoli`
      Tiene traccia di tutti i tavoli esistenti, chiave = numero tavolo.
      Questo è il “catalogo” dei tavoli.
    * `Map<Integer, Prenotazione> prenotazioni`
      Tiene le prenotazioni attive, chiave sempre = numero tavolo.
      Se un tavolo è presente in questa mappa → è occupato.
      Se non è presente → è libero.

Questa scelta ti fa lavorare bene con `HashMap`: accesso diretto O(1) al tavolo tramite numero, e stessa chiave riutilizzata per sapere se c’è una prenotazione.

---

### 2. Funzionalità richieste

Nel `Ristorante` ci sono i metodi chiave:

* **`aggiungiTavolo(int numeroTavolo, int posti)`**

    * Controlla se il tavolo esiste già nella `Map` dei tavoli.
    * Se c’è già, torna `false`.
    * Se non c’è, crea un nuovo `Tavolo` e lo mette nella mappa `tavoli`, poi torna `true`.

* **`prenotaTavoloAutomatico(String nomeCliente, int numeroPersone)`**

    * Valida nome del cliente e numero di persone creando una `Prenotazione`.
    * Considera soltanto i tavoli liberi e sufficientemente capienti.
    * Sceglie il tavolo con meno posti tra quelli adatti, così limita i posti inutilizzati; a parità sceglie il numero di tavolo minore.
    * Restituisce il numero assegnato, `-1` se non c'è un tavolo libero adatto oppure `-2` se il gruppo supera la capienza di qualunque tavolo esistente.

* **`liberaTavolo(int numeroTavolo)`**

    * Se il tavolo non esiste in `tavoli`, ritorna `false`.
    * Altrimenti prova a fare `prenotazioni.remove(numeroTavolo)`:

        * se ritorna `null`, non c’era prenotazione (già libero).
        * se ritorna una prenotazione, vuol dire che l’abbiamo appena rimossa → tavolo liberato, ritorna `true`.

* **`getTavoliLiberi()`**

    * Scorre tutti i tavoli di `tavoli.values()`.
    * Per ogni tavolo controlla se **non** è presente nelle chiavi della `prenotazioni`.
    * Aggiunge quelli liberi a una `List<Tavolo>`, la ordina per numero e la ritorna.

* **`getTavoliOccupati()`**

    * Scorre le chiavi della mappa `prenotazioni` (i numeri dei tavoli occupati).
    * Per ogni numero recupera il corrispondente `Tavolo` da `tavoli`, lo inserisce nella lista e ordina il risultato per numero.

* Metodi di utilità:

    * `stampaTavoliLiberi()` → usa `getTavoliLiberi()` e li stampa con formato carino.
    * `stampaTavoliOccupati()` → stampa tavoli occupati associandoli alla `Prenotazione`.

---

### 3. Il `main`: menù testuale

La classe `MainRistorante` è solo un client che:

* Crea uno `Scanner` per leggere da tastiera.
* Crea un oggetto `Ristorante`.
* Mostra un menù con le opzioni:

1. Aggiungi tavolo
2. Prenota tavolo
3. Libera tavolo
4. Mostra tavoli liberi
5. Mostra tavoli occupati
0. Esci

Per ogni scelta:

* legge gli input necessari (numero tavolo, nome cliente, numero persone),
* chiama il metodo opportuno su `Ristorante`,
* mostra un messaggio coerente con il valore di ritorno.

Il programma parte senza tavoli precaricati: prima di prenotare occorre aggiungerne almeno uno tramite l'opzione 1. Gli input non numerici e i valori non positivi vengono segnalati senza interrompere il programma.

---
