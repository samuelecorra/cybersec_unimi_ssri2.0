ESERCIZIO: SEPARARE UNA STRINGA IN DUE LISTE

Data una stringa di lunghezza non nota a priori, creare una lista di caratteri
alfabetici e una lista dei restanti caratteri, preservandone l'ordine.

Sono presenti due versioni:

1) mainV1.c legge una riga con buffer dinamico e usa soltanto la testa. Ogni
   inserimento in coda percorre la lista: costruire n nodi costa O(n^2).
2) mainV2.c usa una struttura Lista con testa e coda. Ogni inserimento è O(1),
   quindi la separazione completa è O(n); l'input di test è hardcoded.

Entrambe controllano malloc, propagano i fallimenti e liberano anche le liste
parziali. isalpha riceve sempre un valore convertito a unsigned char; la nozione
di carattere alfabetico dipende dal locale C attivo.
