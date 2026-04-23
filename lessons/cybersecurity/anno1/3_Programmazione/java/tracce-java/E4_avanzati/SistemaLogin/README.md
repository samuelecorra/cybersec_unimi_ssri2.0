# README

## Sistema di Login  (HashMap + Sicurezza base)

### 1. Obiettivo dell’esercizio

Questo progetto simula un **semplice sistema di login da console**, utile per fissare:

* uso di `Map` / `HashMap` in Java;
* gestione di oggetti personalizzati (`Utente`);
* concetto base di **password hashata** (con un “finto” hash tramite `String.hashCode()`).

Non è un sistema sicuro reale, ma è perfetto come palestra per la mentalità da cybersecurity.

---

### 2. Struttura del progetto

* `Utente.java`
  Rappresenta un singolo utente registrato:

    * `username` (stringa univoca);
    * `passwordHash` (int, ottenuto da `password.hashCode()`).

* `SistemaLogin.java`
  Contiene la **logica applicativa**:

    * `Map<String, Utente> utenti`: mappa che associa lo `username` all’oggetto `Utente`;
    * metodi per:

        * registrazione utente;
        * login;
        * cambio password;
        * stampa degli utenti registrati (senza password).

* `MainLogin.java`
  Fornisce un **menu testuale** che permette all’utente di:

    * registrarsi,
    * fare login,
    * cambiare password,
    * vedere gli utenti registrati,
    * uscire dal programma.

---

### 3. Perché usare `Map` / `HashMap` qui?

L’operazione fondamentale in un sistema di login è:

> “Dato uno username, recupera velocemente il relativo utente.”

Questo è esattamente il problema che una `Map<K, V>` risolve:

* la **chiave** è lo `username` (tipo `String`);
* il **valore** è l’oggetto `Utente` con tutti i suoi dati.

In particolare:

* `HashMap` implementa `Map` usando **hashing** sulle chiavi;
* le operazioni tipiche (`put`, `get`, `containsKey`, `remove`) sono in media `O(1)`:

    * registrazione: `utenti.put(username, utente);`
    * login: `utenti.get(username);`
    * controllo se esiste già: `utenti.containsKey(username);`

Quindi la struttura dati è perfettamente allineata al modello mentale:

* username → utente corrispondente

Molto più naturale che scorrersi un `List<Utente>` a mano ogni volta.

---

### 4. Gestione delle password: hash finto con `hashCode()`

Nella classe `Utente` le password **non sono salvate in chiaro**, ma convertite in un “hash”:

```java
private int calcolaHash(String password) {
    return Objects.requireNonNull(password, "password").hashCode();
}
```

e memorizzate come `int passwordHash`.

Quando un utente prova a fare login:

* prende la password in input,
* ne calcola l’hash con lo stesso metodo,
* lo confronta con `passwordHash` salvato in memoria:

```java
public boolean verificaPassword(String password) {
    return this.passwordHash == calcolaHash(password);
}
```

> **Importantissimo lato cybersecurity:**
> `String.hashCode()` **NON è assolutamente adatto** per la protezione reale delle password:
>
> * non è criptograficamente sicuro;
> * è deterministico e facile da invertire con attacchi a dizionario;
> * non usa salt, né iterazioni, né funzioni lente.

Nel mondo reale dovresti usare algoritmi tipo:

* `PBKDF2`, `bcrypt`, `scrypt`, `Argon2`, ecc.

Qui lo usiamo **solo come giocattolo didattico** per:

1. abituarti all’idea che **non si memorizzano mai password in chiaro**;
2. mostrarti come si può confrontare una password tramite hash.

---

### 5. Flusso delle operazioni principali

#### 5.1 Registrazione

1. L’utente sceglie “1) Registrazione utente”.
2. Il programma chiede:

    * username
    * password
3. `SistemaLogin.registraUtente(...)`:

    * controlla che username non sia vuoto;
    * controlla che non esista già la chiave nella mappa (`containsKey`);
    * crea un nuovo `Utente(username, password)` che al suo interno:

        * calcola l’hash della password;
        * memorizza solo `passwordHash`;
    * lo inserisce in `utenti.put(username, utente)`.

#### 5.2 Login

1. L’utente sceglie “2) Login”.
2. Inserisce username e password.
3. `SistemaLogin.login(...)`:

    * recupera l’utente con `utenti.get(username)`;
    * se non esiste → errore;
    * se esiste, chiama `u.verificaPassword(password)`:

        * ricalcola l’hash della password inserita;
        * lo confronta con l’hash salvato;
    * se coincidono → login riuscito, altrimenti fallisce.

#### 5.3 Cambio password

1. L’utente sceglie “3) Cambio password”.
2. Inserisce username, vecchia password, nuova password.
3. `SistemaLogin.cambiaPassword(...)`:

    * recupera l’utente con `utenti.get(username)`;
    * verifica la vecchia password con `verificaPassword`;
    * se corretta, chiama `u.cambiaPassword(nuovaPassword)` che aggiorna l’hash.

#### 5.4 Mostra utenti registrati

1. L’utente sceglie “4) Mostra utenti registrati”.
2. `SistemaLogin.mostraUtentiRegistrati()`:

    * se mappa vuota → messaggio;
    * altrimenti stampa solo gli username:

        * nessuna password, nessun hash.

---

### 6. Spunti di miglioramento (versione “seria” cybersecurity)

Se volessi trasformare questo giocattolo in qualcosa di più vicino a un sistema reale, potresti:

* sostituire `hashCode()` con una vera funzione di hashing per password;
* introdurre un campo `salt` per ogni utente (random);
* serializzare gli utenti su file / database anziché solo in RAM;
* inserire:

    * blocco dopo X tentativi falliti,
    * logging degli accessi,
    * policy sulle password (minimo 8 caratteri, numeri, simboli, ecc.);
    * distinzione tra ruoli (admin, user normale, ecc.).

Per l’esercizio però è perfetto così: ti allena su:

* concetto di mapping username → utente con `HashMap`;
* gestione oggetti e metodi;
* primo contatto mentale col concetto di hash delle password.
