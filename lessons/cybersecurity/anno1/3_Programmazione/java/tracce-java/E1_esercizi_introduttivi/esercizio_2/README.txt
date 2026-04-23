Interesse Composto – README

Descrizione
Questo programma calcola il montante finale di un capitale investito a interesse composto,
dopo un certo numero di anni e capitalizzazioni annuali.
Questa versione include controlli completi sull’input per evitare errori dell’utente.

Il programma richiede in ingresso:

- il capitale iniziale (in euro),
- il tasso di interesse annuo in percentuale,
- il numero di volte in cui l’interesse viene capitalizzato ogni anno,
- il numero di anni.

Esegue quindi il calcolo del montante finale e lo stampa con due decimali.

Formula
La formula utilizzata è quella classica dell’interesse composto:

M = C * (1 + r / n)^(n * t)

dove:
C = capitale iniziale
r = tasso annuo in forma decimale (es. 5% → 0.05)
n = numero di capitalizzazioni per anno (es. 12 per mensile)
t = numero di anni
M = montante finale

Input validi
Il programma accetta solo valori numerici positivi secondo le seguenti regole:

Capitale iniziale: maggiore o uguale a 0
Tasso di interesse: maggiore o uguale a 0
Numero di capitalizzazioni annue (n): intero maggiore o uguale a 1
Numero di anni (t): intero maggiore o uguale a 0

In caso di input non numerico o non valido, il programma richiede nuovamente il valore.

Esempio di esecuzione
Inserisci il capitale iniziale (>= 0): 1000
Inserisci il tasso di interesse (in percentuale, >= 0): 5
Inserisci il numero di volte che l'interesse viene capitalizzato in un anno (>= 1): 12
Inserisci il numero di anni (>= 0): 10
Il montante finale dopo 10 anni è: 1647.01

Come eseguire in IntelliJ IDEA

Crea un nuovo progetto Java.

Crea un package chiamato esercizio_2.

Inserisci al suo interno il file InteresseComposto.java.

Esegui la classe cliccando sul tasto di esecuzione ▶ accanto al metodo main.

Come compilare ed eseguire da terminale (con JDK installato)
Da dentro la cartella che contiene il package:
javac esercizio_2/InteresseComposto.java
java esercizio_2.InteresseComposto

--------------------------------------------------------------------------------
Estensioni possibili (facoltative)

Calcolare e stampare una tabella con il montante anno per anno.

Aggiungere la capitalizzazione continua (M = C * e^(r*t)).

Permettere versamenti periodici per simulare piani di accumulo.

Note
Il programma utilizza printf("%.2f") per arrotondare il risultato a due decimali.
La formula e la logica sono valide per capitalizzazione discreta, non continua.