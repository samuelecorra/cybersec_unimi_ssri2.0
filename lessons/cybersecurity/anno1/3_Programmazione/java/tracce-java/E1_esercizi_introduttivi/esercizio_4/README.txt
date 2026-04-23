ArrayRicerca – README

Descrizione
Questo programma mostra l’algoritmo di ricerca lineare su due array: uno di interi e uno di stringhe.
L’utente inserisce un numero da cercare nell’array numerico e una parola da cercare nell’array di stringhe.
Il programma restituisce l’indice dell’elemento se trovato, altrimenti segnala che non è presente.

Obiettivo didattico
Applicare la ricerca lineare (scansione sequenziale) su array.
Distinguere il confronto tra tipi primitivi (==) e tipi riferimento (equals).
Gestire input robusto con Scanner (controllo hasNextInt e pulizia buffer).
Strutturare il codice in funzioni riusabili (linearSearch su int[] e String[]).


Dati di esempio nel codice
Array di interi: {1, 3, 4, 5, 6, 2, 7, 8, 9}
Array di stringhe: {"cane", "gatto", "pesce", "uccello"}


Algoritmo (ricerca lineare)
Partire dall’indice 0 e avanzare fino a length - 1.
Confrontare ogni elemento con il bersaglio.
Se coincide, restituire l’indice.
Se si termina il ciclo senza trovare l’elemento, restituire -1.


Note importanti
Confronto tra interi: usare l’operatore ==.
Confronto tra stringhe: usare il metodo equals (o equalsIgnoreCase per ignorare le maiuscole).
Dopo nextInt() è necessaria una nextLine() per consumare la newline residua.
La ricerca lineare ha complessità temporale O(n) e spaziale O(1).


Esempio di esecuzione
Inserisci il valore intero da cercare nell'array numerico: 6
Il valore bersaglio e' all'indice 4
Inserisci la parola da cercare nell'array di stringhe: gatto
La parola bersaglio e' all'indice 1


Estensioni possibili (facoltative)
Rendere la ricerca su stringhe case-insensitive con equalsIgnoreCase.
Stampare tutti gli indici in cui compare l’elemento, se l’array contiene duplicati.
Ordinare preventivamente l’array e implementare la ricerca binaria.
Leggere gli array da input e non solo dal codice.


Come eseguire in IntelliJ
Creare un progetto Java.
Creare un package chiamato esercizio_4.
Inserire il file ArrayRicerca.java nel package.
Eseguire la classe con il metodo main.

Compilazione ed esecuzione da terminale
javac esercizio_4/ArrayRicerca.java
java esercizio_4.ArrayRicerca