/*------------------------------------------------------------------------------------

ESERCIZIO:

Realizzare una funzione/sottoprogramma in C che, data in ingresso una stringa
di cui non è nota a priori la dimensione, crei dinamicamente due liste :

    1. Nella prima si inseriscano tutti i caratteri alfabetici 
    2. Nella seconda tutti i caratteri non alfabetici come numeri, 
    spazi, caratteri speciali e di controllo ecc. ecc.

Scrivere poi una funzione main che testi la funzione appena realizzata.

ESEMPIO

Input:              Output:
"Ciao 123!"         Lista 1: C i a o
                    Lista 2: 1 2 3 !

------------------------------------------------------------------------------------*/

CI SONO DUE VERSIONI. 

1) mainV1.c: versione base, non ottimizzata e "mal pensata" ma funzionante.
2) mainV2.c: versione migliorata, con struttura Lista che contiene i puntatori
   alla testa e alla coda della lista, per ottimizzare l'inserimento in coda.

La seconda versione è più efficiente e più elegante, ergo meglio studiarsela al meglio!