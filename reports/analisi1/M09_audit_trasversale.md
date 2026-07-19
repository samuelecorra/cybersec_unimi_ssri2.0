# M09 - Derivate: audit trasversale, riorganizzazione e certificazione

> Nota di rinumerazione (2026-07-19, sessione M10): `M11_Integrali` è stato rinominato `M12_Integrali` e il blueprint M10 del §17 è stato attuato con struttura definitiva UD1 Raccordo, UD2 l'Hôpital, UD3 Taylor, UD4 Concavità (lo studio di funzione è il nuovo M11, dai materiali MCS_M11). I riferimenti a "M11" (integrali) e alle vecchie UD di M10 in questo report storico vanno letti di conseguenza; le lezioni di M09 sono già state aggiornate.

## 1. Ambito e metodo

Audit dell'intera cartella `lessons/cybersecurity/anno1/1_Analisi 1/M09_Derivate`. Fase A: lettura integrale delle 14 lezioni preesistenti (modulo piatto, senza UD, origine video), ispezione di struttura e titoli di M10 (senza modificarne i contenuti), censimento delle difformità nominali. Fase B: **riorganizzazione strutturale autorizzata dall'utente** (direttiva di massima linearità sui moduli delle derivate), correzioni, 16 lezioni nuove. Fase C: rilettura del report, chiusura verificata delle lacune, validazioni ripetute. Fuori perimetro: nessun file toccato oltre M09, i suoi report, `schema_lezioni.md`, `manifest.json`, AGENTS/CLAUDE.

## 2. Inventario strutturale

Riorganizzazione: da modulo **piatto** L1-L14 a **6 UD / 30 lezioni** (14 spostate e rinominate con `git mv`, 16 nuove). Stato finale: **2.919 formule, 58 esercizi numerati, 15 placeholder grafici, 48 callout**, titoli 30/30 nello standard `# **M9 UDx Lezione N - Titolo**`.

- **UD1 - Definizione e significato** (5): L1 introduzione (ex L1, +velocità, due forme del rapporto, glossario); **L2** definizione rigorosa/laterali/derivata infinita/notazioni; **L3** tangente-normale-fisica (marginale, sensibilità, elasticità); **L4** derivabile⇒continua con controesempi; **L5** approssimazione lineare e differenziale.
- **UD2 - Regole di derivazione** (6): L1 fondamentali dalla definizione (ex L2, +binomio completo, $1/x$, $\sqrt x$ per razionalizzazione, rinvio dichiarato per $x^\alpha$); L2 sin/cos/exp/ln (ex L3, +prova completa del coseno, ordine logico anti-circolo, nota radianti); L3 linearità+prodotto (ex L4, +linearità generale, uso esplicito di derivabile⇒continua, estensioni e Leibniz annunciata); L4 reciproco+quoziente (ex L5, +separazione da zero via permanenza del segno, errori); L5 catena (ex L6, +**dimostrazione completa con $\omega(k)$** e fallimento della prova ingenua su $g(x)=g(x_0)$); **L6** inversa (dimostrazione non circolare via continuità dell'inversa di M08, controesempio $x^3$).
- **UD3 - Funzioni elementari** (6): L1 potenze reali/esponenziali/derivazione logaritmica (ex L7, +teorema $x^\alpha$ con casistica dei domini, $a^x$, $\log_a$, formula $\frac{y'}{y}$); **L2** trigonometriche e iperboliche ($\tan,\cot,\sec,\csc$, $\sinh,\cosh,\tanh,\coth$, confronto segni, prime equazioni differenziali); **L3** inverse trigonometriche e iperboliche (tutte ricavate dal teorema dell'inversa, scelta dei segni motivata dai rami, convenzione arccot dichiarata); **L4** valore assoluto e implicita (teorema per $|g|$ con casistica degli zeri; implicita con ipotesi di Dini dichiarate, circonferenza e folium); **L5** derivate successive e classi $C^k$ (separatori espliciti, Leibniz dimostrata per induzione, pattern $n$-esimi); L6 esercizi (ex L8, typo corretti).
- **UD4 - Non derivabilità e raccordi** (3): L1 classificazione (ex L9, +distinzione derivate laterali/limiti di $f'$ con rinvio al teorema, quinto caso oscillazione, convenzione "flesso a tangente verticale" dichiarata, errori); L2 esercizi (ex L10, +bordo di dominio); L3 parametri e casi sottili (ex L11, +giustificazione del metodo via UD5/L3, ponte alle classi $C^k$ e a Darboux).
- **UD5 - Teoremi fondamentali** (6, tutta nuova): **L1** Fermat (dimostrazione con i rapporti laterali, ipotesi essenziali, vocabolario punti critici con convenzione); **L2** Rolle (Weierstrass+Fermat, controesempio per ciascuna ipotesi, conteggio radici); **L3** Lagrange (ausiliaria, forma incremento finito, derivata limitata⇒Lipschitz, **teorema del limite della derivata** con dimostrazione — il debito della UD4); **L4** conseguenze (derivata nulla⇒costante con controesempio non-intervallo, monotonia con conversi esatti, criterio della derivata prima dimostrato senza derivabilità nel punto, controesempio $x+2x^2\sin\frac 1x$ sul segno puntuale, cenno giustificato alla derivata seconda con rinvio Taylor); **L5** Cauchy (forma simmetrica vs quoziente, ausiliaria incrociata, lettura parametrica, rinvio de l'Hôpital) e Darboux (dimostrazione completa, niente salti per le derivate, non ogni funzione è una derivata); **L6** disuguaglianze (metodo della differenza con ancoraggio, quattro classici, cascata di monotonie, stime Lagrange).
- **UD6 - Ottimizzazione e applicazioni** (4): L1-L3 i tre problemi svolti preesistenti (ex L12-L14, +fondazione teorica Weierstrass/Fermat/criterio, controllo dei bordi, nota sul dominio aperto in L2, fix vari); **L4** Newton e derivazione numerica (convergenza quadratica osservata con prova rinviata a Taylor, fallimenti catalogati, differenze finite avanti/centrata, cancellazione e $h$ ottimale, gradient descent 1D, confine col discreto).

## 3. Mappa dei prerequisiti

In ingresso: M01 (binomio, addizione trigonometrica, esponenziali/iperboliche, formule logaritmiche), M05 ($e$, geometriche per Newton/gradient), M07 (monotonia, inverse, grafici), M08 (limiti, algebra, composizione, notevoli **dimostrati senza derivate**, continuità, permanenza del segno, Weierstrass, inversa continua, Lipschitz/uniforme, bisezione). In uscita: tutto il necessario per M10 (l'Hôpital via Cauchy, Taylor via derivate successive e approssimazione lineare, convessità via monotonia di $f'$) e M11 (primitive: costante additiva, Darboux; differenziale).

## 4. Contenuti inizialmente presenti

Lato calcolo ben coperto (definizione intuitiva, elementari, regole con prove di somma/prodotto/reciproco/quoziente, catena operativa, $f^g$, esercizi, classificazione non derivabilità in 4 casi, raccordi parametrici, il gioiello $x^2\sin\frac 1x$, tre ottimizzazioni). Lato teorico quasi assente: nessun teorema fondamentale, catena e inversa non dimostrate, criteri usati senza giustificazione.

## 5. Errori matematici e tecnici corretti

| Problema | File (nuova collocazione) | Correzione |
|---|---|---|
| `\ln!` (backslash perso di `\!`), 5 occorrenze | UD2/L2, UD3/L6 | `\ln\left(` |
| `;+;` (perso `\;+\;`) | UD2/L2 | ripristinato |
| `e^{,g(x)\ln f(x)}` e virgole spurie `,(x-x_0)`, `,,0` (×4) | UD3/L1, UD1/L1, UD6/L3 | `\,` |
| `e^{\cos \cdot x\ln x}` | UD3/L6 §9.2 | `e^{\cos x\,\ln x}` |
| "derivata di $4$ e $0$" | UD6/L1 | "è $0$" |
| Prodotto: "se $p$ è almeno continua" senza teorema | UD2/L3 | uso esplicito di derivabile⇒continua (UD1/L4) |
| Criterio "$\lim f'$" usato senza giustificazione | UD4/L1-L3 | teorema del limite della derivata enunciato e dimostrato in UD5/L3, richiami espliciti |
| "flesso a tangente verticale" senza convenzione | UD4/L1 | convenzione dichiarata (flesso ⇒ concavità, M10) |
| Regola $x^\alpha$ usata prima della prova ($\sqrt x$) | UD2/L1 | derivazione per razionalizzazione + rinvio dichiarato a UD3/L1 |
| Riferimenti a "video/lezione 7" obsoleti | vari | aggiornati alla nuova struttura |

## 6. Lacune chiuse (sintesi; matrice al punto 21)

Derivate laterali, derivata infinita, funzione derivata, notazioni con avvertenza anti-frazione; retta normale e casi degeneri; interpretazione fisica/marginale/elasticità; derivabile⇒continua; approssimazione lineare, differenziale, propagazione errori; dimostrazioni di catena e inversa; $x^\alpha$ completa; trigonometriche restanti e iperboliche; inverse trigonometriche e iperboliche; $|g|$ e implicita; derivate successive, $C^k$, Leibniz, pattern; Fermat, Rolle, Lagrange, conseguenze, Cauchy, Darboux, criterio della derivata prima, teorema del limite della derivata; disuguaglianze via derivate; Newton e derivazione numerica; placeholder e callout (prima assenti).

## 7-10. Definizioni, regole, teoremi e dimostrazioni

Definizioni auditate/riscritte con dominio, punto, finitezza e convenzioni: rapporto incrementale, derivata, derivabilità (punto/insieme/intervallo, laterali ai bordi), derivate laterali, funzione derivata, tangente/normale, differenziale, punto angoloso/cuspide/tangente verticale/oscillazione, stazionario/critico (convenzione larga dichiarata), $C^k$, Lipschitz (via $|f'|\le M$), derivazione implicita (ipotesi di Dini dichiarate), derivazione logaritmica ($u>0$). Dimostrazioni complete presenti: elementari dalla definizione (costante, $x$, $x^2$, $x^3$, $x^n$ col binomio, $1/x$, $\sqrt x$, sin, cos, $e^x$, $\ln$ — con ordine logico M05→M08→M09 dichiarato e nessun circolo), linearità, prodotto, reciproco, quoziente, **catena** (formulazione col resto, caso $g(x)=g(x_0)$ trattato), **inversa** (rapporto incrementale + continuità dell'inversa; il "circolo della catena" spiegato), $x^\alpha$, tutte le inverse elementari, $|g|$ con casistica, Leibniz per induzione, Fermat, Rolle, Lagrange, monotonia con conversi, criterio derivata prima, Cauchy (ausiliaria incrociata, stesso $c$), Darboux, teorema del limite della derivata, disuguaglianze classiche. Rinviati con dichiarazione: de l'Hôpital, Taylor (e con essi convergenza quadratica di Newton, criterio $f''$ completo, stime di troncamento).

## 11. De l'Hôpital: collocazione

Struttura reale: M10/UD5 ("l'Hopital e applicazioni", 4 lezioni). Decisione: **resta in M10**; M09 fornisce il motore (Cauchy con forma quoziente e sue condizioni) e il rinvio esplicito in UD5/L5. Nessuna duplicazione.

## 12. Esempi ed esercizi

58 esercizi numerati con soluzione + esempi svolti interni. Controesempi obbligatori presenti: continua non derivabile ($|x|$, $x^{1/3}$, $x\sin\frac 1x$, cenno Weierstrass-ovunque); $x^3$ (stazionario senza estremo; stretta crescenza con $f'(0)=0$; inversa non derivabile); $|x|$ (estremo non derivabile); derivata esistente non continua ($x^2\sin\frac 1x$); laterali infinite discordi ($|x|^{2/3}$, $|\sqrt[3]{x}|$); Rolle senza ciascuna ipotesi; $\arctan x+\arctan\frac 1x$ (derivata nulla su non-intervallo); $x+2x^2\sin\frac 1x$ (segno puntuale della derivata); zeri multipli che degradano Newton; ciclo di Newton su $x^3-2x+2$; segno/parte intera senza primitiva (Darboux). Problema complesso multi-step: coperto dalla filiera UD4/L2-L3 (dominio, continuità, laterali, classificazione, parametri) + UD5 (teoremi con verifica ipotesi, monotonia/unicità) + UD6 (ottimizzazione e scelta degli strumenti), con i rinvii a M10 dichiarati in ciascun punto.

## 13. Notazione

Uniformata: Lagrange come notazione principale, Leibniz con avvertenza, Newton solo temporale; $f'_\pm$; $h$ incremento, $x_0$ punto; $C^k$; `\operatorname{sgn}`, `\operatorname{sech}`, `\operatorname{csch}`, `\operatorname{arsinh/arcosh/artanh}`, convenzione arccot dichiarata; nessun `frac/lim/infty` senza backslash residuo (verificato); titoli conformi 30/30.

## 14. Placeholder

15 placeholder in formato standard, inventario e mappa di copertura in `reports/analisi1/M09_placeholder_grafici.md`.

## 15. Difformità nominali

Le difformità M09 (accenti mancanti nei nomi file L9-L11, qualificatori dello schema assenti dai filename, `f(x) alla g(x)`, `fabs(x alla 2 - 1)`, doppio nome "Continuita e derivabilita") sono state **risolte alla radice** dalla riorganizzazione: nuovi nomi file ASCII-safe coerenti con M08/M10 e `schema_lezioni.md` riscritto in corrispondenza esatta 30/30. Le difformità **M10-M11 restano fuori perimetro**, segnalate e non toccate (per M10: nomi senza accento coerenti coi titoli interni accentati, stile già uniforme; per M11: nomi L01-L18 con qualificatori dello schema parzialmente divergenti e i **quattro trailing whitespace storici**, anch'essi non toccati).

## 16. Confine con M08

Richiamati (mai ridimostrati): limiti e algebra, composizione (forma (b) nella catena via $\omega$ continua), notevoli (con dichiarazione di non-circolarità: dimostrati in M08 senza derivate), permanenza del segno (reciproco, Darboux), Weierstrass (Rolle, Darboux, ottimizzazione), inversa continua (derivata dell'inversa), Lipschitz/uniforme (ora col criterio $|f'|\le M$), bisezione (confronto con Newton). Il link M07→M08 e i riferimenti interni citati sono stati verificati; nessun riferimento esterno puntava ai vecchi path di M09 (grep preventivo), quindi la ristrutturazione non ha rotto collegamenti.

## 17. Confine con M10

Struttura reale di M10 (ispezionata, non modificata): UD1 Definizione e calcolo, UD2 Composta e inversa, UD3 Crescere e decrescere, UD4 Rolle/Lagrange/Cauchy, UD5 l'Hôpital, UD6 Taylor, UD7 Concavità.

- **Completati in M09**: definizione e calcolo completo, catena/inversa dimostrate, teoria del valor medio (Fermat→Darboux), monotonia e criterio della derivata prima, disuguaglianze, Newton (senza stima quadratica).
- **Solo introdotti in M09**: derivata seconda e cenno al criterio $f''$ (giustificazione rapida, prova completa con Taylor); pattern $n$-esimi (carburante di Taylor); lettura parametrica di Cauchy.
- **Rinviati a M10 con motivazione**: de l'Hôpital (M10/UD5; M09 dà Cauchy), Taylor e resti (M10/UD6; M09 dà il caso $n=1$), convessità/flessi (M10/UD7; in M09 solo la convenzione sul "flesso a tangente verticale"), studio completo di funzione, serie di potenze (oltre), convergenza quadratica di Newton, stime di troncamento della derivazione numerica.
- **Blueprint per la sessione M10** (deciso con l'utente, da attuare allora): M10/UD1-UD2 duplicano ora il nucleo di M09 → da riconvertire in ripasso/eserciziario con puntatori a M09 o da fondere; M10/UD3 diventa l'esercitazione estesa della monotonia (teoria in M09/UD5/L4); M10/UD4 idem per Rolle/Lagrange/Cauchy (teoria in M09/UD5) con taglio applicativo; UD5-UD7 restano i nuclei propri di M10, da portare allo standard con Taylor come baricentro. File reali coinvolti: i 26 elencati nell'ispezione (esistenza verificata).

## 18. Confine con M11

Nessuno sviluppo di primitive/integrali. Anticipazioni lecite e dichiarate: costante additiva delle primitive (UD5/L4), Darboux come vincolo sulle funzioni dotate di primitiva (UD5/L5), differenziale e $dx$ (UD1/L5), doppia stima logaritmica per le future stime integrali (UD5/L6).

## 19. Controlli matematici

Verifiche manuali su tutte le dimostrazioni nuove (ipotesi, tesi, uso dei teoremi precedenti, casi nulli: $\omega(0)$ nella catena, separazione da zero nel reciproco, bordi esclusi in Darboux, stesso $c$ in Cauchy) e su ogni esempio numerico: Newton su $\sqrt2$ e $\sqrt[3]5$ (iterate ricontrollate), differenze finite su $x^3$ ($3{,}31$/$3{,}01$), $E=\alpha$ per le potenze, esercizi di monotonia ($x^{2/3}(x-5)$: segni e valori), folium ($y'=-1$), Cauchy su $[1,2]$ ($c=\frac{14}{9}$), gradiente su $x^2$ (ragione $1-2\eta$), tangenti/normali. **12/12 verifiche numeriche automatiche** (script Node) superate su: derivate di $x^n$, $\sin$, $e^x$, $\ln$, catena, inversa ($x^3+x$), quoziente, Lagrange (esistenza del $c$ per $x^2$), Newton (convergenza a $\sqrt2$), differenza centrata vs avanti, disuguaglianze $e^x\ge 1+x$ e cornice del logaritmo su campioni casuali.

## 20. Controlli tecnici

KaTeX M09: **30 file, 2.919 formule, 0 errori**; Analisi 1 intera e report: 0 errori (numeri finali in §23). Manifest rigenerato (5.969 file), allineamento filesystem/manifest verificato; `git diff --check` pulito sul perimetro M09 (restano solo i 4 trailing whitespace storici di M11, fuori perimetro); nessun file vuoto, nessun TODO non grafico, nessun percorso assoluto; titoli, gerarchia `### **N.**`/`#### **N.M**`, accenti italiani, `\cdot` verificati; build frontend eseguita con successo.

## 21. Matrice di chiusura

| Lacuna o errore iniziale | File coinvolto | Intervento | Stato finale | Verifica |
|---|---|---|---|---|
| Modulo piatto, nessuna UD | tutto M09 | riorganizzazione 6 UD (git mv) | Chiusa | filesystem+schema |
| Derivate laterali e derivata infinita assenti | **UD1/L2** | definizioni+teorema+esempi | Chiusa | L2 §3-4 |
| Normale, fisica, marginale assenti | **UD1/L3** | lezione completa | Chiusa | L3 §3-5 |
| Derivabile⇒continua assente ma usato | **UD1/L4**, UD2/L3-L4 | teorema+prova+controesempi; richiami espliciti | Chiusa | L4 §2-3 |
| Approssimazione lineare/differenziale assenti | **UD1/L5** | teorema o-piccolo+differenziale+errori | Chiusa | L5 §2-4 |
| $x^n$ solo abbozzata; $1/x$, $\sqrt x$ mancanti | UD2/L1 | prove complete + rinvio $x^\alpha$ | Chiusa | L1 §8-10 |
| Coseno solo accennato; circolarità non dichiarata | UD2/L2 | prova completa+callout ordine logico | Chiusa | L2 §1,3 |
| Catena non dimostrata | UD2/L5 | prova con $\omega(k)$+fallimento ingenuo | Chiusa | L5 §2bis |
| Inversa assente | **UD2/L6** | teorema+prova+controesempio $x^3$ | Chiusa | L6 §2-4 |
| $x^\alpha$, $a^x$, $\log_a$, log-derivazione | UD3/L1 | §1bis-1quater con domini | Chiusa | L1 |
| tan/cot/sec/csc, iperboliche | **UD3/L2** | lezione completa | Chiusa | L2 |
| Inverse trig+iperboliche | **UD3/L3** | tutte ricavate, segni motivati | Chiusa | L3 |
| $\vert g\vert$ e implicita | **UD3/L4** | teoremi+casistica+Dini dichiarato | Chiusa | L4 |
| Derivate successive, $C^k$, Leibniz | **UD3/L5** | lezione completa | Chiusa | L5 |
| Criterio $\lim f'$ ingiustificato | UD4/L1-L3, **UD5/L3** | teorema dimostrato+richiami | Chiusa | UD5/L3 §5 |
| Caso oscillazione mancante nella classificazione | UD4/L1 | §8 con $x\sin\frac 1x$ | Chiusa | L1 §8 |
| Fermat/Rolle/Lagrange/Cauchy/Darboux assenti | **UD5/L1-L5** | cinque lezioni con prove complete | Chiusa | UD5 |
| Monotonia e criterio derivata prima non fondati | **UD5/L4** | teoremi+conversi+controesempi | Chiusa | L4 |
| Disuguaglianze via derivate assenti | **UD5/L6** | metodo+4 classici+cascata | Chiusa | L6 |
| Ottimizzazioni senza fondazione teorica | UD6/L1-L2 | callout Weierstrass/Fermat/criterio+bordi | Chiusa | L1-L2 |
| Newton/derivazione numerica assenti | **UD6/L4** | lezione completa | Chiusa | L4 |
| Errori LaTeX/typo (punto 5) | vari | corretti | Chiusa | grep+KaTeX |
| Difformità nominali M09 | filesystem+schema | risolte dalla riorganizzazione | Chiusa | §15 |
| Placeholder e callout assenti | tutto M09 | 15 placeholder+48 callout | Chiusa | report gemello |

## 22. Gap analysis conclusiva

Nessuna lacuna pertinente residua sul perimetro "derivate e calcolo differenziale fino ai teoremi del valor medio e alle applicazioni non-Taylor". Restano fuori, per scelta documentata e coerente con la struttura reale dei moduli: de l'Hôpital, Taylor e tutto ciò che ne dipende (criterio $f''$ completo, convergenza quadratica, troncamento), convessità/flessi, studio completo di funzione, derivabilità complessa (nota di confine non necessaria oltre il cenno M08), difformità e trailing whitespace M10-M11 (segnalati, non toccati).

## 23. Valutazione finale (Fase C)

Rilettura integrale del report; ogni riga della matrice ricontrollata sui file finali; validazioni ripetute a valle dell'ultima modifica: KaTeX 0 errori su M09 (30/2.919), su tutta Analisi 1 e sui report; manifest 5.969 file allineato; `git diff --check` pulito sul perimetro; build ok; 12/12 verifiche numeriche. Il modulo è ora lineare (definizione → regole → catalogo → patologie → teoria → applicazioni), rigoroso, non circolare, coordinato con M08 a monte e con M10-M11 a valle, e fornisce il blueprint per la sessione M10.

Verdetto:

```text
M09 COMPLETO E CERTIFICATO
```
