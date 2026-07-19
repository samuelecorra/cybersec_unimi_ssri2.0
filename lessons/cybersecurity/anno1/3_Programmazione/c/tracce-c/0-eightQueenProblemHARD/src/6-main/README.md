# Esercizio 3 — Main interattivo N-regine

Il programma integra stampa, verifica, euristica, caricamento e valutazione delle
mosse. `N` è fissato a compile time (`8` per impostazione predefinita, massimo `26`).

## Menu

```text
1) Stampa stato scacchiera
2) Verifica soluzione
3) Costo euristico
4) Carica stato da file
5) Valuta una mossa (senza modificare)
6) Esegui una mossa (modifica lo stato)
0) Esci
```

Le mosse accettano forme come `g7`, `G7` e `g 7`. La riga è quella scacchistica
`1..N`; il programma la converte in `0..N-1`. Input con suffissi, overflow o righe
troppo lunghe viene rifiutato.

Il caricamento accetta `N` interi interni `0..N-1`. Un errore non modifica lo
stato corrente. Il main iniziale per `N=8` usa la soluzione della Figura 1; per
altre dimensioni parte dalla diagonale `{0,1,...,N-1}`, da correggere manualmente.

## Compilazione

Il file contiene già tutte le funzioni necessarie:

```bash
gcc -std=c17 -Wall -Wextra -Wpedantic mainV1.c -o nregine
gcc -std=c17 -Wall -Wextra -Wpedantic -DN=4 mainV1.c -o nregine4
```

Non bisogna collegarlo insieme ai file degli esercizi 1.1–2.2: quei file sono
tappe autonome e alcune funzioni sarebbero definite due volte.
