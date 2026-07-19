# M08 - Limiti: inventario dei placeholder grafici

Censimento completo dei segnaposto `<!-- TODO FIGURA: ... -->` presenti in `lessons/cybersecurity/anno1/1_Analisi 1/M08_Limiti/` dopo la revisione trasversale. Totale: **19 placeholder in 13 lezioni**, tutti nel formato standard con descrizione dettagliata e obiettivo didattico. Le 17 lezioni preesistenti contengono inoltre **42 immagini reali** (screenshot in `imgs/`, tutte verificate esistenti su disco e tutte referenziate): per quelle lezioni non servono placeholder aggiuntivi.

## UD1 - Fondamenti

| # | Lezione | Figura richiesta |
|---|---|---|
| 1 | L3A - Topologia della retta | Intorno $U_\delta(x_0)$ contro intorno puntato $U_\delta^\ast(x_0)$ sull'asse reale, con il "buco" nel centro |
| 2 | L3A - Topologia della retta | Insieme $A=(0,1)\cup\{2\}$ con punti campione classificati (interno, frontiera/accumulazione, isolato, esterno) |
| 3 | L3B - Prime dimostrazioni | Retta $y=mx+q$ con fasce $\varepsilon$ e $\delta=\varepsilon/|m|$; secondo pannello con pendenza minore e $\delta$ più largo |
| 4 | L3B - Prime dimostrazioni | Funzione segno $|x|/x$ con candidato $L$ e fascia di semiampiezza $1$ che non cattura entrambi i rami |
| 5 | L3C - Limiti laterali | Tre pannelli: salto con laterali diversi, $\sqrt{x}$ con solo lato destro, $1/x$ con laterali infiniti opposti |
| 6 | L3C - Limiti laterali | $\sin(1/x)$ su $(0,0.5]$ (oscillazione limitata) contro $x\sin x$ su $[0,30]$ con inviluppo $\pm x$ (oscillazione illimitata) |
| 7 | L3D - Teoremi generali | Unicità: fasce disgiunte di semiampiezza $|L-M|/3$ attorno a due candidati e punto $f(x^\ast)$ impossibile |
| 8 | L3D - Teoremi generali | Carabinieri: $f\le g\le h$ con $x^2\sin(1/x)$ schiacciata tra $\pm x^2$ nella fascia $L\pm\varepsilon$ |
| 9 | L3E - Criterio sequenziale | $\sin(1/x)$ con le due successioni campione sui passaggi per lo zero e sulle creste, accumulate in $0$ a quote diverse |

## UD2 - Calcolo dei limiti

| # | Lezione | Figura richiesta |
|---|---|---|
| 10 | L5 - Asintoti | Tre pannelli: $1/(x-2)$ (verticale bilaterale), $\ln x$ (verticale da un lato), $(x^2-1)/(x-1)$ (buco senza asintoto) |
| 11 | L5 - Asintoti | $(x^2+1)/(x-1)$ con asintoto verticale $x=1$ e obliquo $y=x+1$, distanza verticale che si schiaccia |
| 12 | L6 - Modulo, tratti, parametri | Raccordo parametrico: ramo $ax+3$ per due valori di $a$ contro ramo fisso $x^2+b$, salto che si chiude solo per il parametro giusto |

## UD3 - Limiti Notevoli

| # | Lezione | Figura richiesta |
|---|---|---|
| 13 | L1 - Limiti notevoli | Circonferenza goniometrica con triangolo $OAP$, settore circolare e triangolo $OAT$: catena delle aree per $\sin x\le x\le\tan x$ |

## UD5 - Continuita e teoremi globali

| # | Lezione | Figura richiesta |
|---|---|---|
| 14 | L1 - Continuità: definizione | Parte intera $\lfloor x\rfloor$ con pallini pieni/vuoti e laterali in un intero (continuità solo da destra) |
| 15 | L3 - Discontinuità | Quattro pannelli: eliminabile, salto, infinita, oscillatoria, ciascuno con la condizione sui laterali |
| 16 | L4 - Zeri e bisezione | Funzione con cambio di segno e primi tre intervalli di bisezione stretti attorno allo zero |
| 17 | L4 - Zeri e bisezione | Quadrato $[a,b]\times[a,b]$ con diagonale $y=x$ e grafico che la attraversa: punto fisso |
| 18 | L5 - Weierstrass e inversa | Funzione continua su $[a,b]$ con massimo e minimo evidenziati e fascia $[\min,\max]$ |
| 19 | L6 - Continuità uniforme | $x^2$ (coppie a distanza fissa con immagini divergenti all'infinito) contro $1/x$ su $(0,1)$ (guasto al bordo) |

## Note

- Ogni placeholder specifica nel commento HTML: elementi del disegno (assi, punti, fasce $\varepsilon$/$\delta$, intorni, laterali, pallini pieni/vuoti, asintoti, successioni, intervalli) e obiettivo didattico.
- Le immagini non vanno generate automaticamente: verranno inserite manualmente dall'utente (screenshot/disegni), come da flusso concordato del progetto.
- Copertura rispetto alla checklist minima della revisione: intorno/puntato (1), accumulazione vs isolato (2), definizione $\varepsilon$–$\delta$ e dipendenza $\delta(\varepsilon)$ (3), valore $\neq$ limite (immagini reali di UD1/L4 + pannello 15), criterio sequenziale (9), laterali diversi (5), limite infinito e asintoto verticale (5, 10), asintoto orizzontale (immagini reali UD1/L4 + 11 per l'obliquo), obliquo (11), oscillazione di $\sin(1/x)$ (6, 9), prova geometrica di $\sin x/x$ (13), discontinuità nelle quattro specie (15), teorema degli zeri e bisezione (16), Weierstrass (18), uniforme vs puntuale (19), inversa monotona continua (coperta testualmente in UD5/L5 §3, figura non necessaria: la costruzione $y_\mp=f(x_0\mp\varepsilon)$ è unidimensionale), equivalenti e cancellazione (coperta dagli esempi numerici svolti in UD4/L2 §8, dove il fenomeno è algebrico e non geometrico).
