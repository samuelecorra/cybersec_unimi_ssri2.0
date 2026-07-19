# Placeholder grafici di M06 - Serie Numeriche

Data dell'inventario: 19 luglio 2026.

## 1. Ambito

L'inventario comprende tutti e soli i commenti `TODO FIGURA` presenti nelle 13 lezioni di `M06_Serie Numeriche`. Le figure non sono state generate: ogni riga costituisce una specifica autonoma per la successiva realizzazione grafica.

## 2. Inventario

| N. | Lezione e sezione | Contenuto richiesto | Obiettivo didattico |
|---:|---|---|---|
| 1 | UD1/L1, sezione 1 | Piano discreto con indice $N$, somme parziali convergenti, divergenti a $+\infty$ e oscillanti, con quota limite quando esiste | Definire una serie mediante la successione delle somme parziali |
| 2 | UD1/L1, sezione 2 | Due righe coordinate per termini alternati $a_n$ e somme parziali $S_N$, con incrementi $S_N-S_{N-1}$ collegati da frecce | Impedire la confusione tra termine, somma parziale e somma della serie |
| 3 | UD1/L2, sezione 1 | Due pannelli per $q=1/2$: termini $q^n$, somme $S_N=2-2^{-N}$, retta $S=2$ e resto | Visualizzare crescita, limitatezza e convergenza di una geometrica positiva |
| 4 | UD1/L2, sezione 1 | Quattro pannelli delle somme parziali per $q=1,-1,3/2,-3/2$, con crescita e oscillazioni indicate | Distinguere i diversi comportamenti divergenti della serie geometrica |
| 5 | UD1/L2, sezione 3 | Espansione della serie di Mengoli su righe successive, cancellazioni orientate e due termini di bordo | Visualizzare il telescopaggio senza perdere indici o residui |
| 6 | UD1/L2, sezione 5 | Blocchi armonici di lunghezza $1,2,4,8$, minorazione $1/2$ e somme $S_{2^m}$ | Mostrare la divergenza armonica nonostante $1/n\to0$ |
| 7 | UD1/L2, sezione 8 | Grafico semilogaritmico delle stime $2^{-N}$ e $1/(N+1)$, soglia $10^{-6}$ e indici richiesti | Separare convergenza teorica e velocità numerica |
| 8 | UD1/L3, sezione 1 | Coda delle somme parziali in intervallo o disco di diametro $\varepsilon$, indici $p<q$ e vettore $S_q-S_p$ | Derivare il criterio di Cauchy per serie da quello per successioni |
| 9 | UD1/L3A, sezione 2 | Blocchi $[2^k,2^{k+1})$, numero di termini e maggioranti/minoranti $2^ka_{2^k}$ e $2^ka_{2^{k+1}}$ | Rendere visibili entrambi i versi della condensazione di Cauchy |
| 10 | UD2/L1, sezione 2 | Somme parziali $A_N\leq B_N$, quota superiore nel caso convergente e secondo pannello con illimitatezza | Visualizzare le sole due implicazioni valide del confronto diretto |
| 11 | UD3/L1, sezione 3 | Sottosuccessioni $S_{2m}$ decrescente e $S_{2m+1}$ crescente verso lo stesso $S$, con distanza $b_{N+1}$ | Seguire tutti i passaggi della dimostrazione di Leibniz |
| 12 | UD3/L1, sezione 4 | Intervalli con $S_N$, $S$, $S_{N+1}$ e freccia del resto nei due casi di parità | Distinguere segno del resto, stima e alternanza delle approssimazioni |
| 13 | UD3/L2, sezione 2 | Tre successioni di somme parziali e accumulo dei moduli per casi assoluto, alternato assoluto e condizionale | Distinguere controllo della massa totale e convergenza per cancellazione |
| 14 | UD3/L2B, sezione 1 | Griglia $(k,j)$ con diagonali $k+j=n$ e triangolo $k+j\leq N$ | Distinguere prodotto di Cauchy, prodotto termine a termine e prodotto delle somme |
| 15 | UD3/L2B, sezione 6 | Blocchi positivi e negativi del riordinamento di Riemann attorno a un bersaglio $L$, con sovraelongazioni decrescenti | Mostrare costruttivamente la dipendenza dall'ordine nel caso condizionale |
| 16 | UD3/L2C, sezione 3 | Griglia $a_{m,n}$ con rettangoli, righe, colonne e diagonali orientate | Confrontare somme rettangolari, iterate ed enumerazione diagonale |

## 3. Verifica di conformità

Tutti i 16 placeholder:

- usano il formato `<!-- TODO FIGURA: ... -->` e hanno il commento HTML chiuso;
- specificano assi, indici, termini, somme parziali, blocchi, frecce, cancellazioni o ordine di lettura quando pertinenti;
- dichiarano un obiettivo didattico esplicito;
- hanno una e una sola riga corrispondente nell'inventario;
- coprono tutte le 15 tipologie minime pertinenti richieste, sostituendo la regione di convergenza delle serie di potenze, correttamente fuori perimetro, con una figura sulle serie doppie.

Non risultano placeholder legacy, commenti non chiusi, placeholder non inventariati o righe del report prive del relativo commento sorgente.
