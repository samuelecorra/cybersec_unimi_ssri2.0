# M10 - Derivabilità: audit trasversale, rifocalizzazione e certificazione

## 1. Ambito e metodo

Audit dell'intera cartella `lessons/cybersecurity/anno1/1_Analisi 1/M10_Derivabilita` (26 lezioni lette integralmente), eseguito **dopo** la certificazione di M09 e con la direttiva utente di massima linearità sui moduli delle derivate. Fase A: inventario, mappa di sovrapposizione con M09, individuazione dei nuclei propri. Fase B: **rifocalizzazione strutturale approvata dall'utente** — eliminazione dei duplicati, rinumerazione delle UD superstiti, integrazione delle dimostrazioni mancanti, creazione della UD di raccordo. Fase C: validazioni e verdetto. Contestualmente, su decisione dell'utente: **M11_Integrali rinominato M12_Integrali** (lo studio di funzione diventa il nuovo M11, dai materiali docente `MCS_M11.zip`).

## 2. Diagnosi di partenza (Fase A)

M10 contava 7 UD / 26 lezioni (binario "dispense docente" MCS_M10), corrette ma sottili (~117 righe/lezione, quasi senza dimostrazioni, zero esercizi numerati). Mappa di sovrapposizione post-M09:

- UD1 Definizione e calcolo (5), UD2 Composta e inversa (3), UD3 Crescere e decrescere (4), UD4 Rolle/Lagrange/Cauchy (2): **~95-100% duplicati** di M09 (UD1-UD2, UD2/L5-L6+UD3/L3, UD5/L1+L4, UD5/L2-L5), in versione più debole;
- UD5 l'Hôpital (4), UD6 Taylor (5), UD7 Concavità (3): **nuclei propri**, promessi da 14 riferimenti incrociati in M08/M09, ma privi delle dimostrazioni (l'Hôpital non dimostrato, Peano non dimostrato, resto di Lagrange non dimostrato, criterio di convessità non dimostrato).

Decisione (proposta, approvata dall'utente): niente fusione in M09 (M09 diventerebbe un modulo da ~45 lezioni e la separazione fondamenti/applicazioni È il flusso lineare); M10 resta autonomo come **"secondo tempo" applicativo**, dimagrito e completato.

## 3. Struttura finale

**4 UD / 14 lezioni**, 737 formule, 14 callout, 4 placeholder (2 TODO FIGURA + 2 slide docente), titoli 14/14 a standard:

- **UD1 - Raccordo con M09 e ripasso** (2, nuove): L1 mappa dei prerequisiti con tabella dei puntatori esatti e flusso logico anti-circolo; L2 eserciziario di riscaldamento — **assorbe il 100% del contenuto unico delle 14 lezioni eliminate** ($x^{5/3}$ in $0$, $\ln(x^2)=2\ln|x|$, raccordo $a,b$, $\ln\frac{1+x}{1-x}$, $e^{\sqrt{1+x^2}}$, $(f^{-1})'(2)$ per $x^3+x$, $\arcsin(2x-1)$ coi bordi, $\frac{x^2+1}{x}$, $x\sqrt{x+1}$, $|x^2-4|$, monotonia per rami di $\frac 1x$);
- **UD2 - l'Hopital e applicazioni** (4, ex UD5): L1 con **dimostrazione del caso $\frac 00$ via Cauchy** (prolungamento per continuità + $c_x$ schiacciato), quattro condizioni d'uso in ordine, caso $\frac{\infty}{\infty}$ dichiarato; L2 confronto fra infiniti con nota di coerenza verso la prova senza derivate di M08; L3 forme trasformabili; L4 esercizi + **sezione controesempi**: $\frac{x+\sin x}{x}$ (rapporto derivate senza limite, originale convergente), forma non indeterminata che dà risultato falso, ipotesi $g'\neq 0$ (cenno Stolz), circolo vizioso sui notevoli;
- **UD3 - Taylor e applicazioni** (5, ex UD6): L1 con verifica della costruzione ($T_n^{(k)}(x_0)=f^{(k)}(x_0)$) e **unicità** (doppia: algebrica e via Peano); L2 con **dimostrazione di Taylor-Peano** (induzione + l'Hôpital + $T_n'$ = Taylor di $f'$, ipotesi minime dichiarate); L3 con **dimostrazione del resto di Lagrange** (Cauchy sulle ausiliarie $g(t)$ telescopica e $h(t)=(x-t)^{n+1}$, caso $n=0$ = Lagrange di M09); L4 Maclaurin completata (binomiale generalizzato con $o(x^n)$, $\frac{1}{1+x}$, $\sqrt{1+x}$, $\tan$, $\arctan$, $\arcsin$, $\sinh$, $\cosh$, $\tanh$, lettura dei segni circolare/iperbolico); L5 con il **criterio delle derivate successive dimostrato** via Peano + permanenza del segno ($m$ pari/dispari; il debito di M09/UD5/L4 saldato; caso $C^\infty$ piatto come limite dello strumento);
- **UD4 - Concavita e flessi** (3, ex UD7): L1 con **definizione rigorosa con le corde** ($t x_1+(1-t)x_2$), convessità stretta, dualità $f$/$-f$, caratterizzazione con le tangenti ($e^x\ge 1+x$ riletta), **dimostrazione di "$f'$ crescente $\Rightarrow$ convessa"** via doppio Lagrange sulle pendenze; L2 con la **prova del criterio $f''\ge 0$** (due ingranaggi: monotonia di $f'$ + L1) e la **condizione necessaria di flesso** $f''(x_0)=0$ (analogo di Fermat, con controesempio $x^4$); L3 con l'approfondimento **convessità $\Rightarrow$ disuguaglianze** (AM-GM dalla concavità di $\ln$, cenno a Jensen).

## 4. Rinumerazione M11→M12 e nuovo M11

- `M11_Integrali` → `M12_Integrali` (git mv; ripulite 7 voci fantasma dell'indice `L12 -.md`…`L18 -.md`, residui di una sessione precedente);
- **14 riferimenti testuali** a "M11" (= integrali) in 8 lezioni di M08/M09 aggiornati a M12; heading dello schema aggiornato;
- nuovo `M11_Studio_di_Funzione/`: conservati `MCS_M11.zip` (file del docente) e i **14 PDF** estratti in `pdf/` (U01×3, U02×4, U03×3, U04×4); rimossa l'estrazione pesante (video mp4/swf, ~150MB) per non inquinare manifest e build — lo zip preserva tutto; sezione segnaposto aggiunta allo schema. La creazione del modulo dai PDF è il [NEXT TASK].
- **14 riferimenti incrociati** M08/M09 → M10/UD5-UD6-UD7 aggiornati alla nuova numerazione UD2-UD3-UD4 (0 residui).

## 5. Confini

- **Con M09**: nessuna teoria duplicata; UD1 è puntatori+esercizi; ogni dimostrazione di M10 usa esplicitamente i teoremi di M09 (Cauchy per l'Hôpital e per il resto di Lagrange, approssimazione lineare per la base di Peano, monotonia per la convessità) — la gerarchia Fermat→Rolle→Lagrange/Cauchy→l'Hôpital/Taylor è ora un'unica catena senza salti;
- **con M08**: i rinvii di M08/UD4 (Taylor operativo) puntano a M10/UD3; la scala di crescita ha due prove dichiarate (M08 senza derivate, M10 con l'Hôpital) con nota di precedenza logica; nessun notevole è "fondato" con l'Hôpital (controesempio (d) di UD2/L4);
- **con M11 (Studio di Funzione, da creare)**: M10 fornisce tutto il necessario (monotonia da M09, l'Hôpital, Taylor, concavità/flessi); l'eserciziario UD1/L2 anticipa consapevolmente i pattern ($x+\frac 1x$ e asintoti, modulo a "W");
- **con M12 (Integrali)**: nessuno sviluppo; restano le anticipazioni lecite già presenti in M08/M09 (aggiornate a M12).

## 6. Controlli

- KaTeX: M10 **14 file / 737 formule / 0 errori**; Analisi 1 intera e report: 0 errori (numeri finali in §8);
- manifest rigenerato; allineamento filesystem/manifest verificato; build frontend ok;
- verifiche matematiche puntuali: prova di l'Hôpital (uso corretto di Cauchy col medesimo $c_x$, non-annullamento di $g$ via Rolle), induzione di Peano ($T_n' =$ Taylor di $f'$: coefficienti ricontrollati), telescopia di $g'(t)$ nel resto di Lagrange, caso $n=0$ = Lagrange, criterio delle derivate successive (segni di $(x-x_0)^m$), doppio-Lagrange della convessità ($c_1<c_2$), AM-GM; sviluppi aggiunti confrontati coi pattern noti ($\tan$, $\arctan$, $\arcsin$, iperbolici); verifica numerica campionaria (script) su sviluppi e controesempio $\frac{x+\sin x}{x}$;
- `git diff --check` pulito sul perimetro; nessun file vuoto; nessun TODO non grafico.

## 7. Matrice di chiusura

| Lacuna o errore iniziale | Intervento | Stato | Verifica |
|---|---|---|---|
| 14 lezioni duplicate di M09 (UD1-UD4 vecchie) | eliminate; contenuto unico assorbito in UD1 nuova | Chiusa | UD1/L1-L2 |
| l'Hôpital senza dimostrazione né ipotesi ordinate | prova $\frac 00$ via Cauchy + 4 condizioni + $\frac{\infty}{\infty}$ dichiarato | Chiusa | UD2/L1 |
| Controesempi l'Hôpital assenti | sezione 5bis con 4 controesempi | Chiusa | UD2/L4 |
| Taylor: costruzione non verificata, unicità assente | verifica + doppia unicità | Chiusa | UD3/L1 |
| Peano senza dimostrazione | induzione + l'Hôpital, ipotesi minime | Chiusa | UD3/L2 |
| Resto di Lagrange senza dimostrazione | Cauchy su ausiliarie telescopiche | Chiusa | UD3/L3 |
| Sviluppi incompleti | binomiale generalizzato, $\tan$, $\arctan$, $\arcsin$, iperbolici | Chiusa | UD3/L4 |
| Criterio derivate successive (debito M09) | teorema dimostrato via Peano | Chiusa | UD3/L5 |
| Convessità senza definizione rigorosa né prove | corde+tangenti, $f'$ crescente $\Rightarrow$ convessa, criterio $f''$, necessità flesso | Chiusa | UD4/L1-L2 |
| Nessun legame convessità-disuguaglianze | AM-GM + cenno Jensen | Chiusa | UD4/L3 |
| Rinumerazione integrali + refs M08/M09 + fantasmi indice | M12 + 14 refs + 7 git rm | Chiusa | §4 |
| Cross-refs M10/UD5-7 obsoleti | aggiornati a UD2-4 (0 residui) | Chiusa | grep |

## 8. Valutazione finale (Fase C)

Rilettura del report a valle delle modifiche, matrice ricontrollata sui file finali, validazioni ripetute (KaTeX 0 errori su M10, su tutta Analisi 1 e sui report; manifest allineato; build ok; diff pulito nel perimetro). M10 è ora un modulo snello e interamente dimostrato che fa esattamente le tre cose promesse dalla catena M08-M09 — l'Hôpital, Taylor, convessità — senza ripetere nulla e preparando M11 (Studio di Funzione) e M12 (Integrali).

Verdetto:

```text
M10 COMPLETO E CERTIFICATO
```
