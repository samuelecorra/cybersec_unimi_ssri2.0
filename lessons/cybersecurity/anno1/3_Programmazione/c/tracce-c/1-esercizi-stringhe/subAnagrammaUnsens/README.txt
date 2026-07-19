ESERCIZIO: SUB-ANAGRAMMI CASE-INSENSITIVE

Si confrontano le frequenze delle 26 lettere ASCII senza distinguere il case.
L'input deve contenere esclusivamente A-Z o a-z.

1) mainV1.c usa due istogrammi e richiama la stessa funzione nelle due direzioni.
2) mainV2.c usa un solo istogramma per confronto e una bitmask per raccogliere le
   due relazioni. Entrambe hanno complessità temporale lineare nella lunghezza
   complessiva delle stringhe e memoria ausiliaria costante.
