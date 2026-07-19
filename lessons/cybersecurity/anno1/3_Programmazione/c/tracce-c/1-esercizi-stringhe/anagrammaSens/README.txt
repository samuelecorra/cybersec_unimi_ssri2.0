ESERCIZIO DELL'APPELLO DI LABORATORIO DELL'11/06/2025

Scrivere una funzione che stabilisca se due stringhe alfabetiche sono anagrammi
case-sensitive: devono avere la stessa lunghezza e le stesse frequenze di ogni
carattere. In questa soluzione si adotta la convenzione dell'anagramma "proprio":
due stringhe identiche non contano come riordinamento diverso.

Il dominio dichiarato dalla traccia è ASCII alfabetico (A-Z, a-z). Il programma
lo valida esplicitamente e rifiuta righe assenti, vuote o più lunghe del buffer.

Esempi:
  amor / roma  -> anagrammi
  Amor / Roma  -> non anagrammi in modalità case-sensitive
  casa / casa  -> identiche, quindi escluse dalla convenzione adottata
