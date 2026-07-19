# Audit trasversale conclusivo di M05 - Successioni

Data dell'audit e dell'accettazione: 19 luglio 2026.

## 1. Ambito e metodo

L'audit riguarda l'intera cartella `M05_Successioni` e confronta lezioni, `schema_lezioni.md`, `manifest.json`, titoli, formule KaTeX, definizioni, teoremi, dimostrazioni, esempi, esercizi, rinvii e placeholder. Il lavoro è stato svolto nelle tre fasi richieste: inventario e gap analysis, integrazione correttiva, rilettura di accettazione con verifica puntuale delle lacune iniziali.

M01-M04 sono stati trattati come prerequisiti certificati senza modificarli. Di M06 sono stati ispezionati esclusivamente struttura e titoli; i moduli sui limiti di funzione e sulla continuità sono stati usati per definire il confine dei rinvii, senza anticiparne integralmente i contenuti.

## 2. Inventario strutturale

| Unità didattica | Lezioni iniziali | Lezioni finali | Stato |
|---|---:|---:|---|
| UD1 - Definizioni ed esempi | 4 | 6 | Completa |
| UD2 - Limiti e teoremi | 3 | 3 | Completa |
| UD3 - Calcolo dei limiti | 3 | 4 | Completa |
| UD4 - il numero e ed i limiti notevoli | 2 | 3 | Completa |
| UD5 - Infinitesime e definite per ricorrenza | 3 | 4 | Completa |
| **Totale** | **15** | **20** | **5 UD allineate** |

Sono stati aggiunti con suffissi non distruttivi:

- `UD1/L4A - Valori di aderenza e Bolzano-Weierstrass.md`;
- `UD1/L4B - Limite superiore e limite inferiore.md`;
- `UD3/L2A - Stolz-Cesaro e medie di Cesaro.md`;
- `UD4/L0 - Successioni geometriche e disuguaglianza di Bernoulli.md`;
- `UD5/L3A - Ricorrenze lineari.md`.

Non sono stati rinominati, spostati o rimossi file. I suffissi mantengono l'ordine originario e separano nuclei autonomi che avrebbero reso incoerenti o eccessivamente lunghe le lezioni esistenti.

## 3. Mappa dei prerequisiti

1. M01 fornisce algebra, polinomi, fattoriali, binomio di Newton, trigonometria, esponenziale e logaritmo a livello operativo.
2. M02 fornisce funzioni come oggetti insiemistici, quantificatori, ordine, supremo, infimo e completezza di $\mathbb R$.
3. M03 distingue insiemi finiti e infiniti e sostiene il linguaggio delle successioni come funzioni su $\mathbb N$.
4. M04 fornisce modulo e distanza complessa; M05 estende sinteticamente convergenza e Cauchy alle successioni complesse senza riaprire la teoria di $\mathbb C$.
5. UD1 introduce oggetti, ordine, sottosuccessioni e valori di aderenza; UD2 costruisce la teoria del limite; UD3 sviluppa il calcolo; UD4 fonda geometriche, Bernoulli ed $e$; UD5 tratta confronti e ricorrenze.
6. M06 riceve già pronti somme parziali, Cauchy, geometriche, confronto, limsup e ordini di crescita.

Non risultano dipendenze circolari. Continuità, limiti di funzione e costruzione completa di esponenziale e logaritmo sono dichiarati come anticipazioni quando realmente necessari.

## 4. Contenuti inizialmente presenti

I 15 file iniziali costituivano una traccia ordinata e sostanzialmente corretta: definizione di successione, limitatezza, monotonia, estratte, limite, confronto, Cauchy, algebra dei limiti, forme indeterminate, alcuni limiti, numero $e$, limiti notevoli, infinitesimi, confronti e ricorrenze.

La copertura era però introduttiva: 542 formule, un solo placeholder legacy, quasi nessuna dimostrazione completa e nessuna sezione sistematica di esercizi. Mancavano inoltre Bolzano-Weierstrass, intervalli annidati, limsup e liminf, Bernoulli, una prova di esistenza di $e$, Stolz-Cesàro, medie di Cesàro, ricorrenze lineari e una trattazione rigorosa delle dipendenze dalla completezza.

## 5. Lacune individuate

| Area | Lacuna iniziale | Gravità |
|---|---|---:|
| Fondamenti | Successione e immagine, indici, rappresentazioni e tipi notevoli non erano distinti formalmente | Alta |
| Limite | Definizione $\varepsilon$-$N$, negazione, limiti infiniti e primi termini erano troppo sintetici | Alta |
| Teoremi | Unicità, limitatezza, segno, confronto, carabinieri e algebra mancavano di prove complete | Alta |
| Ordine | Convergenza monotona non era collegata formalmente a supremo e completezza | Alta |
| Sottosuccessioni | Mancavano valori di aderenza, annidamento e Bolzano-Weierstrass | Alta |
| Completezza | Il criterio completo di Cauchy e il fallimento in $\mathbb Q$ non erano sviluppati | Alta |
| Estremi asintotici | Assenti $\limsup$, $\liminf$ e code | Alta |
| Calcolo | Forme indeterminate, radicali, parti intere e oscillazioni richiedevano ipotesi e controesempi | Media |
| Strumenti avanzati | Assenti Stolz-Cesàro, Cesàro e Toeplitz facoltativo | Media |
| Successioni notevoli | Mancavano classificazione geometrica completa e Bernoulli con prova | Alta |
| Numero $e$ | Era definito senza dimostrare monotonia e limitatezza della successione | Alta |
| Asintotica | $O$, $o$, $\sim$ e gerarchia non erano separati rigorosamente | Alta |
| Ricorrenze | Punto fisso usato senza un metodo completo; assenti lineari, secondo ordine ed errore | Alta |
| Didattica | Mancavano esercizi sistematici, errori frequenti, controesempi e riepiloghi | Alta |
| Grafici | Il solo placeholder non costituiva una specifica didattica sufficiente | Media |

## 6. Interventi effettuati

Sono state ampliate tutte le 15 lezioni iniziali e create 5 lezioni autonome. Gli interventi principali sono:

- definizione formale su $\mathbb N=\{0,1,2,\ldots\}$, successioni reali e complesse, immagini e rappresentazioni discrete;
- dimostrazioni $\varepsilon$-$N$, negazione quantificata, unicità e irrilevanza dei primi termini;
- prove di limitatezza, permanenza del segno, confronto, carabinieri e algebra dei limiti;
- convergenza monotona tramite supremo e divergenza delle monotone illimitate;
- sottosuccessioni, valori di aderenza, intervalli annidati e Bolzano-Weierstrass;
- Cauchy, completezza sequenziale e controesempio in $\mathbb Q$;
- code, $\limsup$, $\liminf$ e criterio di convergenza;
- forme indeterminate, radicali, radici $n$-esime, oscillazioni e parti intere;
- Stolz-Cesàro con prova, Cesàro con prova e Toeplitz come approfondimento;
- geometriche, Bernoulli e costruzione non circolare di $e$;
- infinitesimi, $O$, $o$, equivalenza e gerarchia delle crescite;
- ricorrenze con intervalli invarianti, metodo babilonese, errore quadratico, lineari affini, secondo ordine e Fibonacci;
- applicazioni a bisezione, interesse composto, algoritmi, complessità e pseudocasualità.

## 7. Definizioni revisionate

Sono ora formulate con dominio, quantificatori e convenzioni: successione, termine, indice, immagine, limitatezza, monotonia globale e definitiva, convergenza, divergenza, limite finito e infinito, sottosuccessione, valore di aderenza, Cauchy, $\limsup$, $\liminf$, infinitesimo, $O$, $o$, equivalenza asintotica, ricorrenza, insieme invariante e punto fisso.

Sono distinte in particolare:

- successione e insieme dei valori assunti;
- limite della successione, limite di una sottosuccessione e punto di accumulazione dell'immagine;
- convergenza reale, divergenza a infinito e oscillazione;
- massimo e supremo dell'immagine;
- $O$, stesso ordine ed equivalenza asintotica;
- convergenza matematica, criterio di arresto e stabilizzazione numerica.

## 8. Teoremi e dimostrazioni

| Risultato | Collocazione | Esito dell'accettazione |
|---|---|---|
| Unicità del limite | UD2/L1 | Prova completa per assurdo con fasce disgiunte |
| Convergenza implica limitatezza | UD2/L2 | Coda e massimo finito dei termini iniziali esplicitati |
| Permanenza del segno e separazione da zero | UD2/L2 | Ipotesi, scelta della soglia e versioni di confronto presenti |
| Confronto e carabinieri | UD2/L2 | Dimostrazioni $\varepsilon$-$N$ complete |
| Algebra dei limiti | UD3/L1 | Somma, prodotto, reciproco e quoziente provati con limitatezza e separazione |
| Sottosuccessioni di una convergente | UD1/L4 | Prova mediante $\varphi(k)\geq k$ |
| Convergenza monotona | UD2/L2 | Prova mediante proprietà del supremo e analogo inferiore |
| Monotona illimitata | UD2/L2 | Prova direttamente dalla definizione di limite infinito |
| Intervalli annidati | UD1/L4A | Prova con supremo; unicità con diametri nulli |
| Bolzano-Weierstrass | UD1/L4A | Prova completa per bisezione e scelta ricorsiva degli indici |
| Criterio di Cauchy in $\mathbb R$ | UD2/L3 | Necessità, limitatezza e sufficienza via Bolzano-Weierstrass |
| Proprietà di $\limsup$ e $\liminf$ | UD1/L4B | Code, valori di aderenza, confronto e criterio di convergenza provati |
| Stolz-Cesàro | UD3/L2A | Enunciato con ipotesi e prova telescopica |
| Medie di Cesàro | UD3/L2A | Prova coda più parte iniziale e converso falso |
| Disuguaglianza di Bernoulli | UD4/L0 | Prova per induzione e applicazioni |
| Esistenza del limite che definisce $e$ | UD4/L1 | Monotonia binomiale e maggiorazione $<3$ complete |
| Ricorrenze selezionate | UD5/L3-L3A | Invarianza, monotonia, punto fisso, formula chiusa ed errore provati |

Non sono emersi usi circolari della conclusione. Le equivalenze hanno entrambi i versi; i passaggi al limite nelle ricorrenze usano algebra già dimostrata oppure dichiarano esplicitamente la continuità come risultato futuro.

## 9. Esempi ed esercizi

Le 20 lezioni contengono complessivamente 159 esercizi. Ogni lezione possiede esercizi, errori frequenti e riepilogo; gli esempi coprono successioni esplicite, ricorsive, limitate, monotone, convergenti, infinite, oscillanti, Cauchy, estratte, con più valori di aderenza e con differenti ordini di crescita.

Sono presenti problemi trasversali su prova $\varepsilon$-$N$, negazione, Bolzano-Weierstrass, Cauchy in $\mathbb Q$, $\limsup/\liminf$, Stolz-Cesàro, Cesàro, Bernoulli, costruzione di $e$, equivalenti e ricorrenze. Il problema sul metodo babilonese richiede buona definizione, invarianza, monotonia, convergenza, scelta del punto fisso e stima quadratica dell'errore.

## 10. Notazione

La convenzione globale è $\mathbb N=\{0,1,2,\ldots\}$. Gli indici sono $n,m,k,N$, i limiti $L,\ell$, la precisione $\varepsilon>0$ e i reali estesi $\overline{\mathbb R}$. L'avvio da $n=1$ è dichiarato nei casi in cui compaiono $1/n$, fattoriali o medie.

Sono uniformati `definitivamente`, valore assoluto, $+\infty/-\infty$, $o$, $O$ e $\sim$. Le forme indeterminate sono presentate come classificazioni, non come valori. Le ricerche conclusive non rilevano comandi LaTeX privi di backslash o tabulazioni introdotte da escape corrotti.

## 11. Placeholder

Sono presenti 17 placeholder grafici conformi, tutti chiusi e con obiettivo didattico. L'inventario completo è in `reports/analisi1/M05_placeholder_grafici.md`; il confronto uno a uno non rileva commenti senza riga di report né righe senza commento sorgente.

## 12. Confine con M04

M04 fornisce modulo, distanza e algebra complessa. M05 si concentra sulle successioni reali e dedica soltanto estensioni sintetiche alle successioni complesse: convergenza per componenti, limitatezza mediante modulo, algebra e Cauchy. Non riapre forma polare, radici o logaritmo complesso.

| Argomento trasversale | Trattazione in M04 | Trattazione in M05 | Esito |
|---|---|---|---|
| Modulo e distanza | Costruzione e proprietà | Usati per convergenza e Cauchy complessi | Confine corretto |
| Successioni complesse | Anticipazione | Definizione e criteri essenziali | Prerequisito completato senza duplicazione |
| Esponenziale complesso | Costruzione formale controllata | Non riaperto | Separazione corretta |

## 13. Confine con M06

M06 contiene 8 lezioni su definizione di serie, somme parziali, Cauchy per serie, serie elementari, confronto, rapporto, radice, alternanza e convergenza assoluta. M05 prepara gli strumenti, ma non dimostra criteri di convergenza delle serie.

| Argomento trasversale | Trattazione in M05 | Trattazione prevista in M06 | Esito |
|---|---|---|---|
| Somme parziali | Successioni definite da somme | Definizione di serie tramite somme parziali | Prerequisito completo |
| Cauchy | Criterio per successioni reali | Criterio applicato alle somme parziali | Progressione corretta |
| Geometrica | Successione $q^n$ e somma finita | Serie geometrica infinita | Nessuna duplicazione integrale |
| Confronto e asintotica | Rapporti, $O$, $o$, $\sim$ | Criteri per serie positive | Prerequisito completo |
| Rapporto e radice | Limiti di successioni e cautele | Criteri specifici per serie | Correttamente rinviati |
| Alternanza e assoluta | Non sviluppate | UD3 di M06 | Correttamente rinviate |

## 14. Confine con limiti di funzione e continuità

M05 fonda esclusivamente limiti di successioni. Il criterio sequenziale per limiti di funzione, continuità, composizione generale, valori intermedi e asintoti restano nei moduli successivi. I limiti di seno, coseno, logaritmo ed esponenziale sono distinti tra risultati sequenziali dimostrati e anticipazioni dichiarate; non viene usata la derivata né l'Hôpital.

La generalizzazione $(1+a/n)^n\to e^a$ è enunciata con dominio e statuto logico: il caso che definisce $e$ è provato in M05, mentre la forma reale completa è rinviata alla costruzione analitica di esponenziale e logaritmo.

## 15. Controlli matematici

Sono state eseguite 1.831 asserzioni automatiche su soglie $\varepsilon$-$N$ per $1/(n+1)$, disuguaglianza di Bernoulli, monotonia e limitatezza della successione che definisce $e$, tutti i regimi geometrici, criterio di Cauchy, estremi delle code di $(-1)^n$, applicazioni di Stolz-Cesàro, medie di Cesàro, metodo babilonese, formula delle ricorrenze affini, formula di Binet e radici $n$-esime. Tutte le asserzioni sono state superate.

Sono state inoltre rilette manualmente le prove obbligatorie verificando ipotesi, tesi, quantificatori, entrambi i versi delle equivalenze, trattamento dei termini iniziali, segno dei denominatori, scelta crescente degli indici e assenza di passaggi al limite non giustificati.

## 16. Controlli tecnici

| Controllo | Risultato |
|---|---|
| KaTeX su M05 | 20 file, 1.396 formule, 0 errori |
| KaTeX su Analisi 1 | 189 file, 14.252 formule, 0 errori |
| KaTeX sui report di Analisi 1 | 10 file, 368 formule, 0 errori |
| Manifest SPA | Rigenerato, 5.868 file complessivi |
| Allineamento filesystem/schema/manifest | Analisi 1: 231/231; M05: 20/20 nello stesso ordine |
| Collegamenti interni M05 | 0 collegamenti Markdown, quindi 0 rotti; rinvii testuali verificati |
| Ordine del renderer | Coincide con filesystem e schema, inclusi L0, L2A, L3A, L4A e L4B |
| File vuoti o quasi vuoti M05 | 0; file minimo 3.196 byte |
| TODO non grafici e placeholder legacy | 0 e 0; restano soltanto 17 `TODO FIGURA` conformi |
| Commenti HTML | 17 aperture e 17 chiusure, nessuno sbilanciato |
| Comandi LaTeX privi di backslash e notazioni corrotte | 0; uniformato `\varepsilon` |
| Paragrafi lunghi duplicati | 0 duplicati esatti da almeno 240 caratteri |
| `git diff --check` sul perimetro | Superato; anche i due report non tracciati sono privi di spazi finali |
| `git diff --check` globale | Restano soltanto 4 spaziature finali preesistenti in M11, fuori perimetro |

## 17. Matrice di chiusura

| Elemento dell'audit | File interessati | Stato finale | Riscontro |
|---|---|---|---|
| Definizione di successione e differenza dall'immagine | UD1/L1 | Implementata integralmente | Funzione su $\mathbb N$, indice, termine, immagine e grafico discreto distinti |
| Definizione $\varepsilon$-$N$ e negazione | UD2/L1 | Implementata integralmente | Quantificatori, dipendenze, tre verifiche e non convergenza di $(-1)^n$ |
| Unicità e irrilevanza dei primi termini | UD2/L1 | Implementata integralmente | Prova per fasce disgiunte e soglia massima |
| Limitatezza e permanenza del segno | UD2/L2 | Implementata integralmente | Coda più termini iniziali; separazione da zero |
| Confronto, carabinieri e algebra | UD2/L2, UD3/L1 | Implementata integralmente | Prove complete e ipotesi sui quozienti |
| Limiti infiniti e forme indeterminate | UD2/L1-L2, UD3/L2 | Implementata integralmente | Definizioni, algebra determinata e controesempi per ogni forma |
| Successioni geometriche e Bernoulli | UD4/L0 | Implementata integralmente | Tutti i casi reali di $q$ e prova per induzione |
| Successione che definisce $e$ | UD4/L1 | Implementata integralmente | Crescita binomiale, maggiorazione $<3$ e definizione non circolare |
| Generalizzazione esponenziale | UD4/L1-L2 | Correttamente rinviata in parte | Caso fondativo provato; forma reale generale dichiarata dipendente da exp/log |
| Convergenza monotona e monotone illimitate | UD2/L2 | Implementata integralmente | Supremo/infimo e definizioni di limiti infiniti |
| Sottosuccessioni | UD1/L4 | Implementata integralmente | Mappa strettamente crescente, $\varphi(k)\geq k$, teoremi e controesempi |
| Valori di aderenza e Bolzano-Weierstrass | UD1/L4A | Implementata integralmente | Intervalli annidati e prova per bisezione |
| Cauchy e completezza | UD2/L3 | Implementata integralmente | Necessità, sufficienza in $\mathbb R$ e fallimento in $\mathbb Q$ |
| $\limsup$ e $\liminf$ | UD1/L4B | Implementata integralmente | Code, monotonia, valori di aderenza, confronto e criterio di convergenza |
| Stolz-Cesàro, Cesàro e Toeplitz | UD3/L2A | Implementata integralmente | Due prove; Toeplitz separato come approfondimento |
| Radicali, trigonometria, valore assoluto e parti intere | UD3/L3 | Implementata integralmente | Razionalizzazione, carabinieri, oscillazioni e controesempio nei punti interi |
| Confronti asintotici | UD5/L1-L2 | Implementata integralmente | $O$, $o$, $\sim$, gerarchia, proprietà e cancellazioni |
| Successioni ricorsive e punti fissi | UD5/L3 | Implementata integralmente | Invarianza, monotonia, passaggio giustificato, babilonese ed errore |
| Ricorrenze lineari e Fibonacci | UD5/L3A | Implementata integralmente | Formula chiusa, classificazione, equazione caratteristica e Binet |
| Applicazioni, esercizi ed errori frequenti | Tutte le lezioni | Implementata integralmente | 159 esercizi; 20 sezioni di errori e 20 riepiloghi |
| Placeholder grafici | 11 lezioni e report dedicato | Implementata integralmente | 17 specifiche inventariate uno a uno |
| Confine con M04, M06 e continuità | Lezioni e sezioni 12-14 | Implementata integralmente | Prerequisiti e rinvii espliciti, nessuna duplicazione integrale |

## 18. Gap analysis conclusiva

| Categoria | Stato | Gravità residua | Decisione |
|---|---|---:|---|
| Lacune matematiche pertinenti a M05 | Nessuna residua | Nessuna | Modulo chiuso |
| Prove obbligatorie | Complete | Nessuna | Nessun rinvio improprio |
| Esponenziale/logaritmo reali generali | Correttamente rinviati | Non è una lacuna di M05 | Completamento nei moduli di funzione |
| Criteri delle serie | Correttamente rinviati | Non è una lacuna di M05 | M06 |
| Toeplitz | Presente come approfondimento proporzionato | Nessuna | Nessuna espansione ulteriore |
| Figure finali | Restano da produrre manualmente | Bassa, non contenutistica | 17 specifiche complete |
| Approfondimenti ulteriori | Facoltativi | Nessuna | Nessun nuovo file necessario |

## 19. Valutazione finale

M05 è autosufficiente entro il proprio perimetro: costruisce il linguaggio delle successioni, la teoria rigorosa della convergenza, il ruolo della completezza, gli strumenti di calcolo, le nozioni asintotiche e il metodo per le ricorrenze. Le dimostrazioni centrali sono accessibili ma complete, gli esempi includono i casi patologici richiesti e l'apparato di 159 esercizi copre sia tecniche locali sia problemi trasversali da esame.

Il confine con M06 è netto: M05 prepara somme parziali e criteri sequenziali senza svolgere la teoria delle serie. Il confine con limiti di funzione e continuità è dichiarato nei punti in cui esponenziale, logaritmo o composizione generale richiedono risultati futuri. Non restano raccomandazioni dell'audit inattuate né lacune matematiche pertinenti.
