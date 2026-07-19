ESERCIZIO DELL'APPELLO DI LABORATORIO DEL 16/07/2025

Per ogni cella di una matrice quadrata di interi, calcolare il massimo tra gli
adiacenti orizzontali, verticali e diagonali, escludendo la cella stessa. Si assume
N >= 2. INT_MIN è la sentinella corretta: un valore arbitrario come -99999 non
coprirebbe l'intero dominio di int.

mainV1.c usa matrici allocate come array di puntatori alle righe e gestisce cleanup
e fallimenti parziali.

mainV2.c usa VLA supportati dal compilatore e riceve dal chiamante anche la matrice
di output; non effettua allocazioni dinamiche. Le due versioni hanno entrambe tempo
O(N^2), perché per ogni cella visitano al massimo otto vicini.
