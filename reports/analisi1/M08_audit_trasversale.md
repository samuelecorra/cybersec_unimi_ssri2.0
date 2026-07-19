# M08 - Limiti: audit trasversale, integrazione e certificazione

> Nota di rinumerazione (2026-07-19, sessione M10): `M11_Integrali` è stato rinominato `M12_Integrali` e `M10` è stato ristrutturato in 4 UD (UD2 l'Hôpital, UD3 Taylor, UD4 Concavità). I riferimenti a "M11" (integrali) e alle vecchie UD5-UD7 di M10 in questo report storico vanno letti rispettivamente come M12 e UD2-UD4; le lezioni di M08 sono già state aggiornate.

## 1. Ambito e metodo

L'audit riguarda l'intera cartella `lessons/cybersecurity/anno1/1_Analisi 1/M08_Limiti`. Fase A: lettura integrale delle 17 lezioni preesistenti (4 UD, origine video-trascrizione), ricostruzione di struttura, dipendenze e notazione, confronto con M01-M07 certificati e ispezione di soli titoli/struttura di M09-M11. Fase B: correzione degli errori, integrazione delle lacune con 14 lezioni nuove (UD1 L3A-L3F, UD2 L5-L6, UD5 L1-L6), uniformazione dei titoli allo standard `# **M8 UDx Lezione N - Titolo**`. Fase C: rilettura del report, verifica della chiusura di ogni lacuna sui file finali, validazioni ripetute. Non sono stati modificati `dist/` né moduli esterni, con l'unica eccezione minima documentata al punto 15 (un link in M07).

## 2. Inventario strutturale

Stato finale: **5 UD, 31 lezioni, 4.140 formule, 56 esercizi numerati con soluzione** (oltre agli esempi svolti nel corpo), **19 placeholder grafici** (inventario in `M08_placeholder_grafici.md`), **42 immagini reali** in `imgs/` (tutte esistenti e referenziate), **70 callout** (📌/⚠️/💡/✅).

- UD1 - Fondamenti (10): L1-L2 introduzione intuitiva (preesistenti), L3 definizione formale (preesistente, bonificata), **L3A** topologia della retta, **L3B** dimostrazioni ε–δ e negazione, **L3C** laterali/infiniti/retta estesa, **L3D** teoremi generali, **L3E** Heine e Cauchy, **L3F** algebra/composizione/forme indeterminate, L4 continuità operativa (preesistente, corretta).
- UD2 - Calcolo dei limiti (6): L1-L4 preesistenti (razionali al finito, razionali all'infinito, scala di crescita, composte/carabinieri) integrate; **L5** asintoti, **L6** modulo/tratti/parametri.
- UD3 - Limiti Notevoli (2): L1-L2 preesistenti, integrate con le dimostrazioni fondamentali.
- UD4 - Argomenti avanzati (7): L1-L7 preesistenti (errori comuni, equivalenze, piccolo-o, Taylor-Peano, esempi, ordine di infinitesimo, esercizi), corrette e integrate; Taylor dichiarato anticipazione di M9-M10.
- UD5 - Continuita e teoremi globali (6): interamente nuova (definizione e criteri; algebra e funzioni elementari; discontinuità ed estensione; zeri/valori intermedi/bisezione; Weierstrass e inversa; uniforme continuità e Heine-Cantor).

## 3. Mappa dei prerequisiti

In ingresso: M01 (disuguaglianza triangolare, radicali, trigonometria con prostaferesi e bisezione, esponenziali/logaritmi, iperboliche), M02 (insiemi, densità di $\mathbb{Q}$, intervalli, sup/inf), M03 (numerabilità, usata per i salti delle monotone), M05 (ε–N, monotone, Bolzano-Weierstrass, Cauchy e completezza, aritmetizzazione, gerarchie, costruzione di $e$, Bernoulli/$e^y\ge 1+y$), M07 (dominio, monotonia, elementari, bisezione operativa). In uscita: prerequisiti completi per M9-M10 (derivate) e M11 (integrale, via Heine-Cantor).

## 4. Contenuti inizialmente presenti

Le 17 lezioni originali coprivano bene il lato operativo (interpretazione grafica, casistica delle razionali, scala di crescita asserita, tecniche sui notevoli, equivalenze, piccolo-o, sviluppi, esercizi) e contenevano la definizione con intorni/ε–δ e una definizione di continuità. Mancava quasi interamente il lato teorico-dimostrativo, e la continuità era ridotta a strumento di sostituzione.

## 5. Lacune individuate (sintesi; matrice completa al punto 21)

Intorno puntato e classificazione dei punti; nessuna dimostrazione ε–δ né negazione della definizione; unicità, carattere locale, limitatezza locale, permanenza del segno, confronto, carabinieri senza enunciato/prova; laterali mai definiti; limiti infiniti/all'infinito senza definizioni complete; criterio di Heine e criterio di Cauchy assenti (con rinvio pendente da M05/UD3/L1); algebra dei limiti e composizione senza ipotesi né controesempi; forme indeterminate non sistematiche; scala di crescita non dimostrata; $\sin x/x$ non dimostrato; ponte $e$ successione→funzione assente; $(1+x)^\alpha-1\sim\alpha x$ non derivato; grande-O assente; asintoti (specie obliqui) assenti; modulo/tratti/parametri assenti; iperboliche assenti; differenze di radicali assenti; tutta la teoria della continuità assente (definizione con $x_0\in A$, laterale, criterio sequenziale, algebra, elementari, discontinuità, estensione, monotone, zeri, valori intermedi, bisezione analitica, punto fisso, Weierstrass, inversa, uniforme, Lipschitz/Hölder, Heine-Cantor); applicazioni numeriche assenti.

## 6. Errori corretti nei file preesistenti

| Errore | File | Correzione |
|---|---|---|
| Definizione di continuità senza $x_0\in A$ | UD1/L4 §5 | Richiesto $x_0\in A$; tricotomia esistenza/limite/uguaglianza; caso isolato |
| `=` al posto di `-` nella sottrazione di sviluppi | UD4/L6 §5.3 | Segno ripristinato |
| Segno `+x^4/8` nei passi intermedi di $\ln(\cos x)$ (esito finale corretto) | UD4/L7 Es. 3 | `-\frac{1}{2}(x^4/4)` e `-x^4/8` |
| Denominatore `3x - ln x` nella conclusione (era `3x + ln x`) | UD2/L3 §5.5 | Ripristinato `+` |
| Formula malformata `\lim_{x\to 0} = ...` | UD3/L1 §6.3 | Limite riscritto; giustificazione col quoziente |
| `\setminus{x_0}`, `\cup{-\infty,+\infty}` senza graffe visibili | UD1/L3 | `\{x_0\}`, `\{-\infty,+\infty\}` |
| Backslash persi: `,;` negli intorni, `\ll;` nella scala | UD1/L3, UD2/L3 | Ripristinati |
| `+infinito` testuale in math | UD2/L2 §3.5 | Riscritto in prosa con $+\infty$ |
| `# $$` e ` **$$` spurii davanti a display math | UD2/L4, UD2/L2, UD3/L1 | Rimossi |
| LaTeX crudo fuori dai delimitatori (`\varphi(x)`, heading con `x \to 0`) | UD4/L2 | Delimitato |
| `$ε–δ$` unicode e simboli in testo piano | UD1/L3 | $\varepsilon$–$\delta$ uniformato |
| Quantificatori con `\mid` e `\text{allora}` | UD1/L3 | Forma standard $\forall\ \exists\ \forall:\ \Rightarrow$ |
| Soglia chiamata $x_0$ (confliggeva col punto base) | UD1/L2 | Rinominata $K$ |
| Heading indentati (2 spazi) | UD3/L1 | Portati a colonna 0 |
| Titoli `## Lezione 1-17` a numerazione continua | tutti i 17 | Standard `# **M8 UDx Lezione N - Titolo**` |

## 7. Definizioni revisionate o introdotte

Intorno, intorno puntato, intorni laterali, intorni di $\pm\infty$; punto interno/esterno/di frontiera/isolato/di accumulazione (anche laterale), derivato e chiusura; limite finito (intorni ed ε–δ, con lettura dei quantificatori e della dipendenza $\delta(\varepsilon)$), negazione quantificata; limiti laterali; limiti infiniti al finito ($M$), limiti all'infinito ($R$), limiti estesi e $\overline{\mathbb{R}}$; divergenza vs oscillazione; forma indeterminata; equivalenza asintotica (con dominio del rapporto), piccolo-o, grande-O, $f\ll g$; ordine di infinitesimo/infinito e parte principale (campioni $x-x_0$, $1/x$, $x$); asintoto verticale/orizzontale/obliquo; continuità in un punto (con $x_0\in A$), laterale, su insieme, ai bordi; discontinuità eliminabile/salto/infinita/oscillatoria con convenzione dichiarata; estensione per continuità; continuità uniforme; Lipschitz e Hölder. Convenzione sui limiti nei punti isolati dichiarata (non si definiscono; convenzione alternativa segnalata).

## 8. Teoremi e dimostrazioni presenti (tutte complete, salvo dove indicato)

Unicità ($\varepsilon=|L-M|/3$); carattere locale; limitatezza locale ($\varepsilon=1$); permanenza del segno ($\varepsilon=L/2$, separazione, confronto con costante, caso $L=0$ discusso); confronto ($\le$ al limite, controesempio per $<$); carabinieri (con esistenza nella tesi); bilaterale $\iff$ laterali; Heine (entrambi i versi, successione testimone $1/n$; estensioni a laterali/estesi/infinito); Cauchy per funzioni (necessità e sufficienza via completezza e successioni intrecciate); algebra dei limiti (somma, multiplo, prodotto con limitatezza locale, modulo, potenze intere, radici con fattorizzazione); lemma di separazione e quoziente; composizione con condizioni (a)/(b), dimostrazione e controesempio ($x\sin\frac 1x$ dentro $f$ discontinua); ε–δ campione: affine, $x^2$ (con $\delta=\min\{1,\cdot\}$), $|x|$, $1/x$, $1/x^2\to+\infty$, non-esistenza del segno (per ogni $L$, con $\varepsilon_0=1$); $\sin x/x$ (aree, radianti, pari, carabinieri; caso in gradi $\pi/180$); $\cos x\to 1$; ponte $e$: confinamento con parte intera e caso $x\to-\infty$ per sostituzione; $\tan x/x$, $(1-\cos x)/x^2$, $\ln(1+x)/x$, $(e^x-1)/x$, $(a^x-1)/x=\ln a$, $\log_a(1+x)/x$; $(1+x)^\alpha-1\sim\alpha x$ (via $e^{\alpha\ln(1+x)}$, non circolare); $(1+a/x)^{bx}\to e^{ab}$; scala di confronto (log/potenze via $\ln t\le t-1$; potenze/esponenziali via successioni M05 + parte intera; $a^x\ll x^x$); iperboliche a $\pm\infty$ ($\tanh\to\pm 1$); proprietà delle equivalenze (relazione di equivalenza, prodotto, quoziente, potenze con ipotesi) e controesempio sulle differenze svolto ($\frac{\sin x-x}{x^3}$); formule degli asintoti obliqui (necessità e sufficienza); criterio sequenziale di continuità (successioni che toccano il punto); algebra delle continue (max/min via identità), composizione di continue (diretta, senza ipotesi extra); continuità delle elementari per induzione strutturale (polinomi, razionali, radici, $\sin$/$\cos$ via prostaferesi + $|\sin t|\le|t|$, esponenziale dal notevole senza circoli, logaritmo, potenze reali, iperboliche, inverse); discontinuità delle monotone (solo salti, numerabili, via sup/densità); estensione continua (carattere locale); teorema degli zeri (bisezione + monotone M05 + criterio sequenziale; necessità delle ipotesi); valori intermedi e corollario immagine-intervallo (controesempio al viceversa: $\sin\frac 1x$); punto fisso; Weierstrass (due estrazioni B-W); inversa monotona continua (stima $y_\mp$; controesempio senza intervallo; continua+iniettiva $\Rightarrow$ monotona citato con schema); Lipschitz $\Rightarrow$ uniforme, Hölder, catena stretta con separatori ($\sqrt x$, $x^2$, $1/x$); Heine-Cantor (coppie $1/n$-vicine + B-W). Nessuna dimostrazione usa derivate, Taylor o integrali; l'unico blocco che li usa (UD4 L4-L7) è dichiarato anticipazione.

## 9. Limiti notevoli ed equivalenze

Catalogo completo con derivazioni non circolari e ordine dichiarato: geometria → $\sin x/x$ → conseguenze trigonometriche; M05 ($e$) → ponte funzionale → $\ln(1+x)/x$ → $(e^x-1)/x$ → basi generali → potenza generalizzata. Equivalenze con ipotesi ($\alpha\neq 0$, positività per le potenze), sostituzione di infinitesimi via composizione, procedura anti-cancellazione in 4 passi, collegamento $f\sim g\iff f=g+o(g)$.

## 10. Continuità

Collocata in M08 (UD5): decisione basata sui file reali — M09/M10 usano la continuità solo in rapporto alla derivabilità, nessun altro modulo la fonda. UD1/L4 resta l'introduzione operativa; UD5 sviluppa teoria e teoremi globali (punto 8). Il monito di M07/UD1/L2 su Weierstrass e quello di M07/UD1/L2A su iniettività/monotonia sono onorati.

## 11. Esempi ed esercizi

56 esercizi numerati con soluzione distribuiti su tutte le lezioni nuove e integrate, più gli esempi svolti interni. Controesempi fondamentali presenti: valore nel punto irrilevante; punto isolato; laterali diversi; limitatezza locale non sufficiente; una successione non basta per Heine; composizione senza ipotesi; ogni forma indeterminata con tre esiti (tabella con uguaglianze esatte in L3F); equivalenti nelle differenze; denominatore nullo senza asintoto; continua su aperto illimitata; sup non assunto; puntuale non uniforme; salto da laterali finiti diversi; limite senza definizione nel punto. Problema complesso multi-step: coperto dalla combinazione UD2/L6 (parametri, raccordi, discussione dei casi) + UD2/L5 (asintoti) + UD5/L3 (classificazione ed estensione) + UD4/L7 (riepilogo); ogni tappa richiesta (dominio/accumulazione, laterali, notevoli senza circolarità, equivalenti e cancellazioni, parametri, discontinuità, estensione, asintoti, divieto di strumenti differenziali) è esercitata in almeno un esercizio dedicato.

## 12. Notazione

Uniformata: $\lim_{x\to x_0}$, $x\to x_0^\pm$, $x\to\pm\infty$; $x_0$ punto, $L$ limite, $\varepsilon$/$\delta$, $M$ soglie verticali, $R$/$K$ soglie orizzontali, $\eta$ per la composizione; $U_\delta$, $U_\delta^\ast$, $V_\varepsilon$; $\overline{\mathbb{R}}$ con graffe; $\sim$, $o$, $O$, $\ll$ definiti; convenzione $0^\pm$ dichiarata; italiano con accenti; `$...$`/`$$...$$`, `\begin{cases}`, `\cdot`. Ricerca sistematica di `epsilon`/`delta`/`forall`/`exists`/`infty` senza backslash, virgole spurie, `x\to a+` senza apice: nessun residuo.

## 13. Placeholder

19 placeholder `TODO FIGURA` in 13 lezioni, tutti con descrizione e obiettivo didattico; inventario e mappa di copertura in `reports/analisi1/M08_placeholder_grafici.md`. Le 42 immagini reali preesistenti sono tutte esistenti su disco e tutte referenziate (verifica bidirezionale). Nessun placeholder legacy da convertire (il modulo usava immagini reali).

## 14. Confine con M05

Richiamati senza duplicare: ε–N, monotone, B-W, Cauchy/completezza, aritmetizzazione, $e$, Bernoulli, gerarchie successioni. Sviluppati in M08: Heine (il "ponte" promesso da M05/UD3/L1), trasferimento dei teoremi, versione funzionale della scala con parte intera, ponte $e$. I fattoriali restano sequenziali (nota esplicita in UD2/L3).

## 15. Confine con M07

M07 fornisce dominio/immagine/monotonia/elementari/bisezione operativa; M08 aggiunge il fondamento analitico (limiti, continuità, asintoti formali, giustificazione della bisezione con stima dell'errore). Nessuna duplicazione della classificazione delle funzioni. **Unico intervento fuori M08**: aggiornato il link di `M07/UD3 - Zeri e Segno/L2 - Valori approssimati.md` che puntava il teorema degli zeri a "M08 UD1 L4" (dove non c'era) verso la nuova sede `M08 UD5 L4`; target verificato.

## 16. Confine con M09-M10

- **Prerequisiti completati da M08**: limiti con teoria completa, continuità con teoremi globali, equivalenti/o-piccolo/O-grande, elementari continue, negazione e criteri, asintoti.
- **Contenuti anticipati (preesistenti, mantenuti e dichiarati)**: UD4 L4-L5 (Taylor con resto di Peano) e l'uso di sviluppi in L6-L7; menzione di de l'Hôpital in UD4/L1. Ora recano preamboli espliciti di dipendenza da M9-M10 (enunciato e prova in M10/UD6; de l'Hôpital in M10/UD5).
- **Vietati in M08 e rispettati nel nuovo materiale**: derivate, interpretazione geometrica della derivata, Fermat/Rolle/Lagrange/Cauchy, de l'Hôpital, monotonia via derivate, convessità, criterio "derivata limitata ⇒ Lipschitz" (esplicitamente rinviato in UD5/L6).
- **Rinviati**: resto di Lagrange, Maclaurin, applicazioni di Taylor (M10/UD6); Newton e contrazioni (corsi successivi).

## 17. Confine con M11

Nessun uso di integrali. Anticipazioni lecite: aree nel limite trigonometrico (geometria elementare), Heine-Cantor presentato come ponte verso l'integrabilità delle continue, nota sugli integrali impropri/asintoti non sviluppata.

## 18. Collocazione delle serie di potenze

M08 non contiene né deve contenere serie di potenze: confermato il rinvio di M06 (definizione come anticipazione in M06/UD3/L2C; teoria del raggio rinviata al blocco Taylor/M10 e a corsi successivi). I prerequisiti di M08 (limiti, continuità, o-piccolo) sono completi allo scopo.

## 19. Controlli matematici

Verifiche puntuali (a campione sistematico) su: catena del ponte $e$ (doppio confinamento e sostituzione $x=-t-1$); stima $|x+x_0|<1+2|x_0|$; $\delta$ di $1/x$; disuguaglianza triangolare nell'unicità e nella non-esistenza del segno; tabella delle forme con uguaglianze esatte ($x^{\sin x/\ln x}=e^{\sin x}$ ecc.); scala (con $\ln t\le t-1$ da $e^y\ge 1+y$); razionalizzazioni ($\sqrt{x^2+x}-x$, esercizio con $|x|=-x$); Lipschitz di $x/(1+x^2)$ ($L=1$); bisezione ($2^{20}\ge 10^6$); salti della parte intera "ricuciti" dal quadrato della frazione; controesempio inversa discontinua su dominio non intervallo; correzioni di segno in UD4/L6-L7 ricontrollate contro gli sviluppi noti ($\ln\cos x=-\frac{x^2}{2}-\frac{x^4}{12}+o(x^4)$). Il superamento del validatore KaTeX non è stato considerato prova di correttezza matematica.

## 20. Controlli tecnici

- KaTeX: `node scripts/check-latex.mjs` su M08 → **31 file, 4.140 formule, 0 errori**; sull'intera Analisi 1 e sui report: 0 errori (numeri aggiornati in Fase C, punto 23).
- Manifest rigenerato (`build-manifest.mjs`): 5.892 file totali; tutte le lezioni M08 presenti; allineamento filesystem/manifest verificato.
- Immagini: 42/42 esistenti e referenziate; nessun percorso assoluto locale nei Markdown.
- Link interni: verificati i target dei riferimenti M05/M07↔M08 citati (incluso il link corretto in M07).
- Nessun file vuoto, nessuna sezione troncata, nessun TODO non grafico.
- Lint MD0xx ignorato per policy di progetto; le righe `=` isolate nei blocchi `$$` preesistenti sono pattern storico del repo gestito dal renderer.

## 21. Matrice di chiusura

| Lacuna iniziale | File coinvolto | Intervento | Stato finale | Verifica |
|---|---|---|---|---|
| Intorno puntato, laterali, classificazione punti | UD1/L3, **L3A** | Definizioni, esempio guida, tabella, accumulazione laterale | Chiusa | L3A §2-5; L3 §2,5 |
| Perché serve l'accumulazione; punti isolati | **L3A** | Argomento del "vero a vuoto" + convenzione dichiarata | Chiusa | L3A §6 |
| Dimostrazioni ε–δ | **L3B** | Affine, $x^2$, $\vert x\vert$, $1/x$, $1/x^2$ | Chiusa | L3B §2-5,8 |
| Negazione quantificata + non-esistenza | **L3B** | Negazione formale + prova per ogni $L$ (segno) | Chiusa | L3B §6-7 |
| Laterali, infiniti, all'infinito, $\overline{\mathbb{R}}$ | **L3C** | Definizioni complete, teorema bilaterale⇔laterali, tabella unificata | Chiusa | L3C §2-7 |
| Divergenza vs oscillazione | UD1/L2, **L3C** | Callout + §8 con $x\sin x$ | Chiusa | L3C §8 |
| Unicità, locale, limitatezza, segno, confronto, carabinieri | **L3D** | Cinque teoremi dimostrati + corollari | Chiusa | L3D §2-7 |
| Criterio di Heine | **L3E** | Doppia implicazione + usi + $\sin(1/x)$ | Chiusa | L3E §2-4 |
| Criterio di Cauchy funzionale | **L3E** | Enunciato + prova completa | Chiusa | L3E §5 |
| Algebra dei limiti con prove | **L3F** | Somma/prodotto/modulo/potenze/radici | Chiusa | L3F §2 |
| Quoziente e separazione | **L3F** | Lemma + teorema + casi $M=0$ | Chiusa | L3F §3 |
| Forme indeterminate sistematiche | **L3F** | Sette forme × tre esiti + riconduzione esponenziale | Chiusa | L3F §5 |
| Composizione con ipotesi + controesempio | **L3F**, UD2/L4, UD4/L2 | Teorema (a)/(b) + controesempio + checklist sostituzione + richiami | Chiusa | L3F §6 |
| Continuità: definizione errata | UD1/L4 | $x_0\in A$ + tricotomia + isolati | Chiusa | L4 §5,7 |
| Prova di $\sin x/x$ + radianti | UD3/L1 | Prova geometrica completa + caso gradi | Chiusa | L1 §2.1.1-2.1.3 |
| Ponte $e$ M05→funzione | UD3/L1 | Confinamento parte intera + $x\to-\infty$ | Chiusa | L1 §2.2 |
| $(a^x-1)/x$, $\log_a$ derivati | UD3/L1 | Derivazioni | Chiusa | L1 §11 |
| $(1+x)^\alpha-1\sim\alpha x$; $(1+a/x)^{bx}$ | UD3/L2 | §8-9 nuovi | Chiusa | L2 §8-9 |
| Scala di crescita non dimostrata | UD2/L3 | §8 con tre prove | Chiusa | L3 §8 |
| Iperboliche | UD2/L3, UD4/L2 | §9 + equivalenze in lista | Chiusa | L3 §9 |
| Differenze di radicali | UD2/L4 | §7 con $\sqrt{x^2+x}-x$ | Chiusa | L4 §7 |
| Asintoti V/O/O | **UD2/L5** | Lezione completa con formule dimostrate | Chiusa | L5 §2-5 |
| Modulo/tratti/parametri | **UD2/L6** | Lezione completa | Chiusa | L6 §2-5 |
| Proprietà equivalenze + cancellazioni | UD4/L2 | Prove, ipotesi, controesempio svolto, procedura | Chiusa | L2 §2,5,8 |
| Grande-O | UD4/L3 | §15 nuovo | Chiusa | L3 §15 |
| Taylor non dichiarato anticipazione | UD4/L1,L4,L5 | Preamboli espliciti | Chiusa | preamboli |
| Errori di segno/formula | UD4/L6,L7; UD2/L3; UD3/L1 | Corretti | Chiusa | punto 6 |
| Continuità: criteri, algebra, elementari | **UD5/L1-L2** | Lezioni complete | Chiusa | L1-L2 |
| Discontinuità + estensione + monotone | **UD5/L3** | Classificazione con convenzione + teorema monotone | Chiusa | L3 §2-4 |
| Zeri, valori intermedi, bisezione, punto fisso | **UD5/L4** | Prove + errore $\frac{b-a}{2^{n+1}}$ + numerica | Chiusa | L4 §2-5 |
| Weierstrass + inversa | **UD5/L5** | Prove + tabella controesempi | Chiusa | L5 §2-3 |
| Uniforme, Lipschitz/Hölder, Heine-Cantor | **UD5/L6** | Prove + catena stretta + lettura applicativa | Chiusa | L6 §2-6 |
| Limiti complessi (approfondimento) | **L3C** | §9 con $\bar z/z$ | Chiusa | L3C §9 |
| Applicazioni numeriche | **UD5/L4, UD5/L6** | Bisezione floating point; sensibilità/Lipschitz | Chiusa | L4 §4.1; L6 §6 |
| Titoli difformi | tutti | Standard M01-M07 | Chiusa | grep titoli |
| Link M07→teorema degli zeri errato | M07/UD3/L2 | Puntato a UD5/L4 | Chiusa | punto 15 |

## 22. Gap analysis conclusiva

Nessuna lacuna matematica pertinente residua rispetto a un corso universitario eccellente di Analisi 1 sul perimetro limiti+continuità. Restano fuori perimetro, per scelta documentata: dimostrazione di Taylor (M10), de l'Hôpital (M10), serie di potenze (M10+), contrazioni complete, un separatore esplicito Hölder/uniforme, analisi complessa oltre il cenno. I quattro trailing whitespace preesistenti in M11 e le 22 difformità nominali storiche di M09-M11 rilevate dai controlli globali **non riguardano M08** e restano segnalate a parte, non corrette per mandato.

## 23. Valutazione finale (Fase C)

Rilettura integrale del report a valle delle modifiche; ogni riga della matrice ricontrollata sui file finali (spot-check su enunciati, ipotesi, quantificatori e conti indicati al punto 19); validazioni ripetute dopo l'ultima modifica: KaTeX 0 errori su M08, su tutta Analisi 1 e sui report; manifest allineato; immagini e link verificati; `git diff --check` pulito sul perimetro toccato (residuano solo i quattro trailing whitespace storici di M11). Il modulo è matematicamente rigoroso, autosufficiente nel suo perimetro, progressivo (intuizione → definizione → teoria → calcolo → continuità → teoremi globali), coerente con M05 e M07, non circolare, e fornisce tutti i prerequisiti dichiarati per M9-M11.

Verdetto:

```text
M08 COMPLETO E CERTIFICATO
```
