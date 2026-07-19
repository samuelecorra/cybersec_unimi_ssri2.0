# README

## Sistema di Login  (HashMap + Sicurezza base)

### 1. Obiettivo dell’esercizio

Questo progetto simula un **semplice sistema di login da console**, utile per fissare:

* uso di `Map` / `HashMap` in Java;
* gestione di oggetti personalizzati (`Utente`);
* derivazione sicura di un verificatore di password con **PBKDF2**, salt casuale e confronto a tempo costante.

Resta un esempio didattico in memoria, non un sistema di autenticazione pronto per la produzione.

---

### 2. Struttura del progetto

* `Utente.java`
  Rappresenta un singolo utente registrato:

    * `username` (stringa univoca);
    * `passwordSalt` (16 byte casuali e distinti per utente);
    * `passwordHash` (256 bit derivati con PBKDF2-HMAC-SHA-256 e 120.000 iterazioni).

* `SistemaLogin.java`
  Contiene la **logica applicativa**:

    * `Map<String, Utente> utenti`: mappa che associa lo `username` all’oggetto `Utente`;
    * metodi per:

        * registrazione utente;
        * login;
        * cambio password;
        * stampa degli utenti registrati (senza password).

* `MainSistemaLogin.java`
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

### 4. Gestione delle password: PBKDF2 con salt

La classe `Utente` non conserva la password in chiaro. A ogni impostazione o cambio password genera un nuovo salt con `SecureRandom` e deriva un valore tramite:

```java
new PBEKeySpec(password, salt, 120_000, 256);
SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
```

Il salt non è segreto: serve a far produrre valori derivati differenti anche a password uguali e rende inefficaci le rainbow table precalcolate. Le iterazioni aumentano deliberatamente il costo di ogni tentativo offline.

Quando un utente prova a fare login:

* deriva un nuovo hash dalla password inserita usando il salt memorizzato;
* lo confronta con l'hash salvato tramite `MessageDigest.isEqual`, evitando un confronto byte per byte con uscita anticipata;
* azzera gli array temporanei quando non servono più.

```java
return MessageDigest.isEqual(passwordHash, hashDaVerificare);
```

> ⚠️ `String.hashCode()` non è una funzione crittografica e presenta collisioni note: password diverse potrebbero risultare equivalenti. Per questo non viene usato neppure nell'esempio didattico.

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

        * genera un salt casuale;
        * deriva e memorizza soltanto salt e hash PBKDF2;
    * lo inserisce in `utenti.put(username, utente)`.

#### 5.2 Login

1. L’utente sceglie “2) Login”.
2. Inserisce username e password.
3. `SistemaLogin.login(...)`:

    * recupera l’utente con `utenti.get(username)`;
    * se non esiste → errore;
    * se esiste, chiama `u.verificaPassword(password)`:

        * ricalcola l’hash PBKDF2 della password inserita con il salt dell'utente;
        * lo confronta a tempo costante con l’hash salvato;
    * se coincidono → login riuscito, altrimenti fallisce.

#### 5.3 Cambio password

1. L’utente sceglie “3) Cambio password”.
2. Inserisce username, vecchia password, nuova password.
3. `SistemaLogin.cambiaPassword(...)`:

    * recupera l’utente con `utenti.get(username)`;
    * verifica la vecchia password con `verificaPassword`;
    * se corretta, chiama `u.cambiaPassword(nuovaPassword)`, che genera un nuovo salt e aggiorna l’hash.

#### 5.4 Mostra utenti registrati

1. L’utente sceglie “4) Mostra utenti registrati”.
2. `SistemaLogin.mostraUtentiRegistrati()`:

    * se mappa vuota → messaggio;
    * altrimenti stampa solo gli username:

        * nessuna password, nessun hash.

---

### 6. Limiti dell'esempio e miglioramenti necessari

PBKDF2 corregge il difetto più grave della versione basata su `hashCode()`, ma un sistema reale richiede ancora:

* serializzare gli utenti su file / database anziché solo in RAM;
* blocco o ritardo progressivo dopo tentativi falliti e logging degli accessi;
* una policy sulle password e procedure sicure di recupero;
* una scelta aggiornata dei parametri o di algoritmi come Argon2id in base al contesto;
* input non visibile: `Scanner` mostra la password sul terminale, mentre un'applicazione reale dovrebbe usare un canale protetto;
* controllo della concorrenza, gestione delle sessioni, autorizzazione e protezione dei dati persistenti.

L’esercizio allena su:

* concetto di mapping username → utente con `HashMap`;
* gestione oggetti e metodi;
* differenza tra una normale funzione hash e una funzione di derivazione per password;
* gestione coerente dello username, che viene ripulito con `trim()` in registrazione, login e cambio password.
