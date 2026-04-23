
/* -----------------------------------------------------------------------------------------

ESERCIZIO:

Scrivere un programma in C che definisce una matrice quadrata di dimensione N contenente 
valori char indicanti una lettera a-z o A-Z. 

N.B. Per semplicità didattica si può evitare di controllare che la matrice di partenza
contenga solo lettere valide, hardcodando i valori della matrice stessa a inizio main.

Il programma elabora una nuova matrice quadrata della stessa dimensione, dove:

- ogni elemento della nuova matrice è calcolato in base agli adiacenti (obliqui inclusi, elemento stesso escluso):
deve essere la lettera che tra tutti gli adiacenti viene prima nell'alfabeto internazionale,
CASE UNSENSITIVE. 

Non avrebbe particolare senso fare un esercizio case-sensitive analogo perché
verterebbe di più sulla posizione ASCII che su quella alfabetica effettiva.

Si tenti di realizzare il tutto con allocazioni dinamiche, per poter far compilare il programma
sia secondo lo standard C17 che non prevede VLA passati alle funzioni, sia secondo GNU11 che lo prevede.

------------------------------------------------------------------------------------------------------- */

ESEMPIO:

Ingresso:       Uscita:
a b C d         b a b C
e F G h         a a b C
i J k L         e e F G
M N o P         i i J k