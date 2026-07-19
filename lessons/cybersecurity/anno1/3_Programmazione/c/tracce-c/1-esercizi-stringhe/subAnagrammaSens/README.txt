ESERCIZIO: SUB-ANAGRAMMI CASE-SENSITIVE

La stringa s1 è un sub-anagramma di s2 quando ogni carattere di s1 compare in s2
con frequenza almeno uguale. Il confronto distingue maiuscole e minuscole.

Il programma valida il dominio ASCII alfabetico, calcola entrambe le direzioni e
usa una bitmask: bit 1 per s1 contenuta in s2, bit 2 per s2 contenuta in s1. Se
entrambi sono attivi, le frequenze coincidono e le stringhe sono anagrammi completi.
