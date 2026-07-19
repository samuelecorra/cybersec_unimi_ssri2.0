ESERCIZIO: LETTERA ALFABETICAMENTE MINIMA TRA GLI ADIACENTI

Data una matrice quadrata dinamica di lettere ASCII, produrre una seconda matrice.
Ogni cella contiene la lettera alfabeticamente minima tra gli adiacenti orizzontali,
verticali e diagonali, escludendo la cella originale e ignorando il case.

La dimensione deve essere almeno 2, così ogni cella possiede un vicino. In caso di
parità tra maiuscola e minuscola della stessa lettera viene conservato il case del
primo vicino incontrato. Le allocazioni parziali vengono liberate in caso di errore.

Esempio:
  a b C d      b a b C
  e F G h  ->  a a b C
  i J k L      e e F G
  M N o P      i i J k
