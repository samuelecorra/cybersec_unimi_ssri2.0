ESERCIZIO: CARATTERE PIÙ FREQUENTE IN UNA MATRICE QUADRATA

Individuare l'unico carattere di frequenza massima, stampare la matrice sostituendo
gli altri caratteri con spazi e restituire carattere e frequenza in una struct.
La matrice sorgente non deve essere modificata.

mainV1.c usa un array di puntatori a righe allocate separatamente. Questa forma è
portabile in C17 ma non è l'unica consentita dallo standard.

mainV2.c usa un parametro VLA `matrice[n][n]`. I VLA sono una funzionalità
opzionale delle implementazioni C11-C17, non un'esclusiva di GNU11: va verificato
che il compilatore scelto li supporti.

Entrambe contano tutti i possibili valori di unsigned char e rispettano la richiesta
di visualizzare spazi, non un simbolo sostitutivo arbitrario.
