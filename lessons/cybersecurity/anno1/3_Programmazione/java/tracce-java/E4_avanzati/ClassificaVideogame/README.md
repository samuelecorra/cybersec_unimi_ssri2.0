## README

**Titolo:** Classifica di un Videogioco (Leaderboard con `HashMap` e ordinamento)

### Struttura del progetto

Il progetto simula una semplice classifica (leaderboard) di un videogioco da terminale.
Le classi principali sono:

* `Giocatore`
  Rappresenta il singolo giocatore con:

    * `id` (intero positivo, univoco nella classifica)
    * `nickname` (stringa non vuota)
    * `punteggio` (intero, può essere aggiornato)

* `Classifica`
  Gestisce l’insieme dei giocatori tramite:

    * `Map<Integer, Giocatore> giocatori`
      Implementata come `HashMap`, dove:

        * la **chiave** (`Integer`) è l’`id` del giocatore
        * il **valore** è l’oggetto `Giocatore` corrispondente

* `MainClassifica`
  Contiene il `main` con un menù testuale che permette di:

    * aggiungere un giocatore
    * aggiornare il punteggio
    * stampare la classifica ordinata
    * uscire dal programma

---

### Perché usare `Map` / `HashMap`

L’idea è che l’ID del giocatore sia un identificatore univoco.
Se memorizzassimo tutto in una semplice `List<Giocatore>`, per trovare il giocatore con un certo id dovremmo scorrere tutta la lista ogni volta.

Con una `Map<Integer, Giocatore>` abbiamo invece:

* inserimento in media O(1): `giocatori.put(id, giocatore)`
* ricerca per id in media O(1): `giocatori.get(id)`
* controllo rapido se un id esiste: `giocatori.containsKey(id)`

Per questo la `HashMap` è perfetta quando la “chiave logica” del tuo oggetto è un intero (in questo caso l’id del giocatore) e devi continuamente:

* aggiungere,
* cercare,
* aggiornare oggetti in base a quell’id.

---

### Metodi principali di `Classifica`

1. **`aggiungiGiocatore(int id, String nickname, int punteggioIniziale)`**

    * Controlla se l’id è già presente nella `Map`:

      ```java
      if (giocatori.containsKey(id)) return false;
      ```
    * Se l’id è libero, crea un nuovo `Giocatore` e lo inserisce con:

      ```java
      giocatori.put(id, g);
      ```
    * Restituisce `true` se l’aggiunta va a buon fine, `false` se l’id era già occupato.

2. **`aggiornaPunteggio(int id, int nuovoPunteggio)`**

    * Recupera il giocatore con:

      ```java
      Giocatore g = giocatori.get(id);
      ```
    * Se `g` è `null`, l’id non esiste → ritorna `false`.
    * Altrimenti aggiorna il punteggio con `g.setPunteggio(nuovoPunteggio)` e ritorna `true`.

3. **`stampaClassificaOrdinata()`**

    * Parte dalla `Map`, che per definizione **non è ordinata**.

    * Copia tutti i giocatori in una `List`:

      ```java
      List<Giocatore> lista = new ArrayList<>(giocatori.values());
      ```

    * Ordina la lista con `Comparator`:

      ```java
      lista.sort(
          Comparator
              .comparingInt(Giocatore::getPunteggio)
              .reversed()
              .thenComparing(Giocatore::getNickname)
      );
      ```

        * `comparingInt(Giocatore::getPunteggio)`: ordina per punteggio crescente
        * `.reversed()`: ribalta l’ordine → dal punteggio più alto al più basso
        * `.thenComparing(Giocatore::getNickname)`: a parità di punteggio, ordina alfabeticamente per nickname.

    * Infine stampa la classifica con una posizione (1°, 2°, 3°, …).

---

### Uso di `ArrayList` e `.sort(...)`

Per ordinare i giocatori non possiamo agire direttamente sulla `Map` (che è per definizione “non ordinata”), quindi:

1. Prendiamo la “vista” dei valori (`Collection<Giocatore>`) con `giocatori.values()`.
2. La trasformiamo in una `List` concreta con `new ArrayList<>(...)`.
3. Usiamo `lista.sort(Comparator...)` per definire l’ordinamento che ci serve.

Questa è una combinazione molto tipica:

* `HashMap` per accesso/aggiornamento rapido,
* `ArrayList` + `Comparator` quando vogliamo una vista **ordinata** dei dati.

---

### Flusso del `main`

Il `main` offre un piccolo menù testuale:

1. **Aggiungi giocatore**

    * Chiede id, nickname e punteggio iniziale.
    * Chiama `classifica.aggiungiGiocatore(...)`.
    * Stampa se l’operazione è andata a buon fine o se l’id era già usato.

2. **Aggiorna punteggio**

    * Chiede l’id del giocatore e il nuovo punteggio.
    * Chiama `classifica.aggiornaPunteggio(...)`.
    * Se l’id non esiste, lo segnala.

3. **Stampa classifica ordinata**

    * Chiama `classifica.stampaClassificaOrdinata()` e mostra i giocatori dal più forte al più scarso.

4. **Esci**

    * Termina il ciclo e chiude il programma.

La funzione di utilità `leggiIntero(Scanner scanner)` si occupa di leggere un intero da tastiera gestendo i casi in cui l’utente inserisce roba non numerica, chiedendo finché non ottiene un valore valido.

---

Se vuoi, al prossimo giro possiamo aggiungere:

* salvataggio/caricamento della classifica da file,
* bonus, penalità, “partite giocate” ecc.,
* oppure una leaderboard per più giochi diversi usando una seconda `Map` nidificata.
