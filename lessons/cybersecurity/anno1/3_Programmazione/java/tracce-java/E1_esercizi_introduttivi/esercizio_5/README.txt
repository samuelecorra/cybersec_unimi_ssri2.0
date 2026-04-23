JavaQuizGame – README

Descrizione
Questo programma implementa un semplice gioco a quiz a risposta multipla. L’utente inserisce il proprio nome, quindi risponde a 5 domande con 4 opzioni ciascuna. Il programma verifica ogni risposta, fornisce (se necessario) una breve spiegazione e, al termine, mostra il punteggio e la percentuale.

Obiettivi didattici
Gestione input utente con Scanner e convalida dell’input.
Lavorare con array di stringhe bidimensionale per le opzioni.
Controllo di flusso e confronto di stringhe.
Calcolo del punteggio e formattazione dell’output.


Struttura logica
Saluto iniziale e lettura del nome (case-insensitive per “Principessa”).
Stampa delle domande e delle 4 opzioni.
Lettura della risposta con validazione (solo interi nel range 1–4).
Verifica, feedback immediato e eventuale spiegazione.
Calcolo punteggio finale e percentuale.
Messaggio di chiusura.


Input attesi
Nome utente (stringa).
Una risposta numerica per ciascuna domanda, tra 1 e 4.

Output
Per ogni domanda: esito corretto/sbagliato, eventuale spiegazione.
Riepilogo finale con punteggio totale e percentuale.


Esempio d’esecuzione (sintesi)
Benvenuto nel gioco del quiz!
Inserisci il tuo nome: Samu
Ciao Samu! Puoi comunque partecipare al quiz.
Premi INVIO per iniziare...
Domanda 1: Quale componente informatico è filosoficamente detto il cervello del computer?
CPU GPU RAM Hard Disk
La tua risposta (numero 1-4): 1
Risposta corretta!

...

Punteggio finale: 4 su 5 (80.00%)
Grazie per aver giocato!


Come eseguire in IntelliJ IDEA
Crea un progetto Java.
Crea un package: esercizio_5.
Inserisci il file JavaQuizGame.java nel package.
Esegui la classe con il metodo main.

Compilazione ed esecuzione da terminale (JDK installato)
javac esercizio_5/JavaQuizGame.java
java esercizio_5.JavaQuizGame

Note
Il programma convalida la risposta imponendo un intero nell’intervallo 1–4.
Le spiegazioni sono mostrate per le risposte errate.
Il calcolo della percentuale è mostrato con due decimali (printf).
È possibile rendere le domande casuali in futuro (mescolando l’ordine con uno shuffle).