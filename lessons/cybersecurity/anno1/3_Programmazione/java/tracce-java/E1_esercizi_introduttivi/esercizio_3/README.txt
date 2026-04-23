ArrayAuto – README

Descrizione
Questo programma Java permette di memorizzare e visualizzare un insieme di automobili all’interno di un array di stringhe.
L’utente decide quanti elementi inserire e fornisce un nome o un modello di auto per ciascun elemento.
Al termine, il programma stampa l’elenco completo delle auto inserite.

Obiettivo didattico
Esercitarsi con:

la creazione di un array di dimensione variabile definita dall’utente;
l’inserimento di valori tramite Scanner e ciclo for;
la stampa sequenziale del contenuto di un array;
la gestione del buffer di input con scanner.nextLine() dopo nextInt().


Struttura logica del programma

Lettura del numero di auto da inserire.
Creazione di un array di stringhe con dimensione pari al valore fornito.
Inserimento delle auto una alla volta tramite un ciclo for.
Stampa finale dei valori contenuti nell’array.
Chiusura dello scanner.


Input attesi

Un numero intero positivo che rappresenta la dimensione dell’array.
Un insieme di stringhe (una per riga) corrispondenti ai modelli o ai nomi delle auto.

Output
Il programma mostra a video l’elenco completo delle auto inserite, con indicazione della loro posizione nell’array.

Esempio d’esecuzione
Inserisci il numero di auto che vuoi inserire: 3
Inserisci il valore per l'elemento 0: Fiat Panda
Inserisci il valore per l'elemento 1: Alfa Romeo Giulia
Inserisci il valore per l'elemento 2: Tesla Model 3
I valori inseriti nell'array sono:
Auto 0 inserita nel database: Fiat Panda
Auto 1 inserita nel database: Alfa Romeo Giulia
Auto 2 inserita nel database: Tesla Model 3

Concetti chiave

Gli array in Java sono strutture a dimensione fissa: la lunghezza viene stabilita alla creazione.
Gli indici partono da 0 e arrivano fino a length - 1.
Dopo l’uso di nextInt(), è necessario un nextLine() per pulire il buffer prima di leggere stringhe.
I valori inseriti rimangono in memoria solo durante l’esecuzione del programma (nessun salvataggio su file).


Estensioni possibili (facoltative)

Implementare un controllo per evitare dimensioni negative o pari a zero.
Consentire la ricerca di un modello specifico all’interno dell’array.
Aggiungere un contatore di auto inserite e un messaggio di conferma finale.


Come eseguire in IntelliJ

Crea un nuovo progetto Java.
Aggiungi un package chiamato esercizio_3.
Inserisci al suo interno il file ArrayAuto.java.

Esegui la classe con il metodo main.

Come compilare ed eseguire da terminale
javac esercizio_3/ArrayAuto.java
java esercizio_3.ArrayAuto