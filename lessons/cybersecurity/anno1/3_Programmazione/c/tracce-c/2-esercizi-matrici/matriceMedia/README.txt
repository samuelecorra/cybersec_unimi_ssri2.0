ESERCIZIO: MEDIA DEI VALORI ADIACENTI

Acquisire una matrice quadrata e produrre una matrice in cui ogni elemento è la
media di tutti gli adiacenti orizzontali, verticali e diagonali, escludendo la
cella stessa. Coerentemente con l'output fornito dalla traccia, un angolo ha 3
vicini, una cella di bordo non angolare 5 e una cella interna 8.

mainV1.c separa clonazione, trasformazione e stampa; usa matrici di double.
mainV2.c è compatta, conserva interi in ingresso, accumula in long long e produce
medie double. Entrambe controllano l'esito dell'input.

Le matrici locali hanno durata automatica: chiamarle "statiche" sarebbe improprio.
La macro N è una scelta semplice per una dimensione nota a compile time; in C11-C17
un compilatore che supporta i VLA consente anche dimensioni ricevute a runtime.
