# Audit trasversale conclusivo di M03 - Finito ed Infinito

Data dell'audit e della revisione di accettazione: 19 luglio 2026.

## 1. Ambito e metodo

L'audit riguarda l'intera cartella `M03_Finito ed Infinito`. Sono stati confrontati filesystem, `schema_lezioni.md`, `manifest.json`, ordine del renderer, collegamenti Markdown, titoli, formule KaTeX, definizioni, teoremi, dimostrazioni, esempi, esercizi, dipendenze assiomatiche e placeholder.

La revisione si è svolta in tre fasi: inventario e gap analysis; integrazione dei contenuti; accettazione mediante rilettura della matrice delle lacune e ripetizione dei controlli. Il superamento di KaTeX non è stato considerato prova di correttezza matematica.

## 2. Inventario strutturale

La struttura iniziale comprendeva 2 UD e 4 lezioni, per 144 formule. I quattro file erano bozze introduttive valide ma troppo brevi per sostenere le dimostrazioni richieste.

La struttura finale è:

| Unità didattica | Lezioni | Contenuto principale |
|---|---:|---|
| UD1 - Insiemi finiti ed infiniti | 4 | Cardinalità finita, conteggio, cassetti, infinito, Dedekind, confronto cardinale e Cantor-Bernstein |
| UD2 - Numerabilità e potenza del continuo | 4 | Enumerazioni, stabilità, $\mathbb Z$, $\mathbb N^2$, $\mathbb Q$, stringhe, Cantor, reali, intervalli e gerarchia |
| **Totale** | **8** | **2 UD allineate** |

Sono stati mantenuti nomi e numeri dei quattro file esistenti. Sono stati aggiunti soltanto i suffissi non distruttivi `L1A` e `L2A`, ordinati tra le lezioni originarie dal renderer.

## 3. Mappa dei prerequisiti

1. M02 fornisce insiemi, insieme delle parti, prodotto cartesiano, funzioni, composizione, iniettività, suriettività e biiettività.
2. UD1/L1 traduce la biiezione in cardinalità finita.
3. UD1/L1A sviluppa i principi di conteggio e il principio dei cassetti.
4. UD1/L2 separa infinito e Dedekind-infinito, dichiarando il ruolo della scelta.
5. UD1/L2A definisce il confronto cardinale e dimostra Cantor-Bernstein.
6. UD2/L1 usa questi strumenti per $\mathbb Z$, $\mathbb N^2$, sottoinsiemi e prodotti finiti.
7. UD2/L1A dimostra stabilità per unioni, numerabilità di $\mathbb Q$ e codifiche finite.
8. UD2/L2 dimostra Cantor, la diagonale binaria e $\mathfrak c=2^{\aleph_0}$.
9. UD2/L2A classifica intervalli, $\mathbb R^n$, insiemi di funzioni e gerarchia degli infiniti.

Non risultano dipendenze circolari. Le formule trigonometriche e logaritmiche usate per le biiezioni tra intervalli sono già disponibili in M01.

## 4. Contenuti inizialmente presenti

Le bozze iniziali contenevano:

- definizione elementare di cardinalità finita;
- unione, prodotto e principio dei cassetti solo enunciati;
- definizione negativa di infinito e esempio $\mathbb N\sim2\mathbb N$;
- nozione di Dedekind-infinito senza sottigliezze assiomatiche;
- convenzione utile tra numerabile infinito e al più numerabile;
- elenchi intuitivi di $\mathbb Z$ e $\mathbb Q$ senza verifiche complete;
- unione numerabile soltanto affermata;
- diagonale decimale senza gestione della doppia rappresentazione;
- potenza dell'insieme delle parti soltanto enunciata.

Questi contenuti sono stati conservati come nucleo e sostituiti, dove necessario, da formulazioni dimostrate.

## 5. Lacune individuate

Le lacune ad alta priorità erano:

- unicità della cardinalità finita e distinzione tra insieme, lista e successione;
- giustificazioni di addizione, moltiplicazione, inclusione-esclusione, parti e conteggio di funzioni;
- forma generalizzata e dimostrazione del principio dei cassetti;
- distinzione rigorosa tra infinito e Dedekind-infinito in assenza di scelta;
- confronto $|A|\leq|B|$, casi vuoti e rapporto iniezione-suriezione;
- teorema di Cantor-Bernstein con dimostrazione;
- formule biiettive e verifiche per $\mathbb Z$ e $\mathbb N^2$;
- teoremi su sottoinsiemi, immagini, prodotti e unioni numerabili;
- prova completa della numerabilità di $\mathbb Q$ con duplicati;
- stringhe e sequenze finite, programmi e distinzione dalle successioni infinite;
- teorema di Cantor, diagonale binaria e non numerabilità rigorosa di $\mathbb R$;
- equipotenza degli intervalli e cardinalità di $\mathbb R^n$;
- aritmetica cardinale, $\aleph_0$, $\mathfrak c$, $\aleph_1$ e ipotesi del continuo;
- esercizi d'esame, errori frequenti e placeholder dettagliati.

## 6. Interventi effettuati

Sono state ampliate integralmente le quattro lezioni esistenti e create quattro lezioni autonome:

- UD1/L1A, principi di conteggio e principio dei cassetti;
- UD1/L2A, confronto cardinale e Cantor-Bernstein;
- UD2/L1A, stabilità della numerabilità e applicazioni;
- UD2/L2A, cardinalità del continuo e gerarchia degli infiniti.

La creazione è stata preferita all'accumulo nei quattro file originari perché i nuclei aggiunti hanno teoremi, dimostrazioni, esempi ed esercizi autonomi. Non sono stati rinominati, spostati o rimossi file.

## 7. Definizioni revisionate

Sono ora formalizzate con dominio, codominio e quantificatori pertinenti:

- insieme finito mediante $I_n=\{0,\ldots,n-1\}$ e cardinalità unica;
- equipotenza mediante biiezione;
- insieme infinito come non finito;
- insieme Dedekind-infinito e caratterizzazione tramite endoiniezione non suriettiva;
- confronto cardinale mediante iniezione;
- numerabile infinito, al più numerabile, non numerabile ed enumerazione;
- $B^A$ come insieme delle funzioni $A\to B$;
- $\aleph_0=|\mathbb N|$, $\mathfrak c=|\mathbb R|=2^{\aleph_0}$ e $\aleph_1$;
- ipotesi del continuo come $2^{\aleph_0}=\aleph_1$, non come teorema.

La convenzione $\mathbb N=\{0,1,2,\ldots\}$ è esplicita e coerente.

## 8. Teoremi e dimostrazioni

| Risultato | Collocazione | Esito |
|---|---|---|
| Addizione, moltiplicazione e inclusione-esclusione | UD1/L1A | Giustificazioni tramite partizioni e prodotti |
| Cardinalità di $\mathcal P(A)$ e $B^A$ nel finito | UD1/L1A | Biiezione caratteristica e conteggio delle scelte |
| Principio dei cassetti, anche generalizzato | UD1/L1A | Enunciato funzionale e dimostrazione |
| Dedekind-infinito | UD1/L2 | Equivalenza con endoiniezione non suriettiva e nota su ZFC |
| Cantor-Bernstein-Schröder | UD1/L2A | Dimostrazione completa con $A_n$, $C$ e funzione a tratti |
| Numerabilità di $\mathbb Z$ | UD2/L1 | Formula, inversa, iniettività e suriettività |
| Numerabilità di $\mathbb N^2$ | UD2/L1 | Accoppiamento di Cantor e inversa per diagonali |
| Sottoinsiemi, immagini e prodotti finiti | UD2/L1 | Dimostrazioni e induzione |
| Unione numerabile | UD2/L1A | Suriezione da $I\times\mathbb N$ e nota sulla scelta numerabile |
| Numerabilità di $\mathbb Q$ | UD2/L1A | Suriezione da $\mathbb Z\times\mathbb N_{>0}$ e prova di infinità |
| Stringhe e sequenze finite | UD2/L1A | Unioni numerabili di livelli finiti o numerabili |
| Diagonale binaria | UD2/L2 | Costruzione $d(n)=1-s_n(n)$ completa |
| Teorema di Cantor | UD2/L2 | Iniezione dei singoletti e insieme diagonale |
| $\mathfrak c=2^{\aleph_0}$ | UD2/L2 | Due iniezioni rigorose e Cantor-Bernstein |
| Intervalli e $\mathbb R^n$ | UD2/L2A | Biiezioni, due iniezioni e codifica di successioni |

Le prove dichiarano ipotesi e tesi, definiscono gli insiemi ausiliari e non sostituiscono la suriettività con una enumerazione grafica.

## 9. Esempi ed esercizi

Ogni lezione contiene esempi, errori frequenti, esercizi e riepilogo. La batteria finale copre:

- cardinalità finita, parti, funzioni e inclusione-esclusione;
- cassetti elementari, generalizzati, congruenze e collisioni hash;
- biiezioni con sottoinsiemi propri e Cantor-Bernstein;
- formule per $\mathbb Z$ e accoppiamento di Cantor;
- unioni, prodotti, $\mathbb Q$, polinomi, matrici e stringhe;
- diagonale, insieme delle parti e correzione di prove decimali fallaci;
- intervalli, $\mathbb C$, $\mathbb R^n$ e insiemi di funzioni.

UD1/L2A e UD2/L2A includono problemi trasversali che richiedono scelta della strategia, costruzione di funzioni, verifica delle proprietà, uso di Cantor-Bernstein o Cantor e critica di una strategia ingenua.

## 10. Notazione

La notazione principale è uniforme:

$$
\mathbb N=\{0,1,2,\ldots\},
\qquad
|A|,
\qquad
\mathcal P(A),
\qquad
B^A=\{f:f:A\to B\}.
$$

Si usano $A\sim B$ per equipotenza, $|A|\leq|B|$ per l'esistenza di un'iniezione, $\aleph_0$ per il numerabile e $\mathfrak c$ per il continuo. I prodotti matematici usano `\cdot` o spazio matematico.

## 11. Placeholder

Sono presenti 11 placeholder `TODO FIGURA`, tutti dettagliati e inventariati in `reports/analisi1/M03_placeholder_grafici.md`. Non restano placeholder legacy.

## 12. Confine con M02

| Argomento | Trattazione in M02 | Trattazione in M03 | Esito |
|---|---|---|---|
| Insiemi e parti | Definizioni e operazioni | Cardinalità e crescita di $\mathcal P(A)$ | Nessuna duplicazione integrale |
| Funzioni | Dominio, codominio, iniettività, suriettività, biiettività, inverse | Equipotenza e confronto cardinale | Prerequisito riutilizzato |
| Prodotto cartesiano | Definizione | Conteggio e numerabilità | Estensione pertinente |
| Famiglie indicizzate | Unioni e intersezioni | Unioni numerabili | Progressione corretta |
| Densità e ordine reale | Densità di razionali e irrazionali | Densità distinta dalla cardinalità | Confine rispettato |
| Completezza di $\mathbb R$ | Estremi e ordine | Non usata come sostituto della cardinalità | Nessuna duplicazione |

I richiami diretti a M02 puntano a file esistenti e non hanno richiesto modifiche a M02.

## 13. Confine con i moduli successivi

| Contenuto | Stato in M03 | Modulo successivo | Ragione |
|---|---|---|---|
| Numeri complessi | Solo $\mathbb C\sim\mathbb R^2$ come esercizio/collegamento | M04 | Forma, operazioni e analisi complessa restano fuori perimetro |
| Successioni | Anticipate come funzioni da $\mathbb N$ e come enumerazioni | M05 | Limiti e proprietà analitiche sono rinviati |
| Serie | Solo indicizzazione numerabile | M06 | Convergenza non appartiene alla cardinalità |
| Funzioni reali | Solo cardinalità degli insiemi di funzioni | M07-M10 | Dominio, limiti, continuità e derivate restano nei moduli propri |
| Integrali | Assenti | M11 | Nessun prerequisito pertinente a M03 |
| Calcolabilità | Conseguenza cardinale sui programmi | Corsi dedicati | Arresto e teoria della computabilità sono soltanto richiamati |

## 14. Controlli matematici

Sono stati controllati manualmente e automaticamente:

- formula e inversa dell'enumerazione di $\mathbb Z$;
- funzione di accoppiamento di Cantor e ricostruzione della coppia;
- inclusione-esclusione e conteggi finiti;
- costruzione di Cantor-Bernstein e separazione dei casi $C$ e $A\setminus C$;
- suriezione sulle unioni numerabili e gestione degli insiemi vuoti;
- duplicazioni nella rappresentazione dei razionali;
- diagonale binaria e insieme diagonale del teorema di Cantor;
- iniezione ternaria $\mathcal P(\mathbb N)\to\mathbb R$;
- iniezione dei tagli razionali $\mathbb R\to\mathcal P(\mathbb N)$;
- biiezione tra $(0,1)$ e $\mathbb R$ e codifica di $\mathbb R^n$;
- conteggi di stringhe finite e confronto con successioni infinite.

I campioni automatici non hanno prodotto collisioni o inversioni errate nei domini verificati.

## 15. Controlli tecnici

| Controllo | Risultato finale |
|---|---|
| KaTeX su M03 | 8 file, 828 formule, 0 errori |
| KaTeX su Analisi 1 | 182 file, 12.625 formule, 0 errori |
| KaTeX sui report di Analisi 1 | Superato |
| Manifest SPA | Rigenerato, 5.861 file complessivi |
| Filesystem/schema/manifest M03 | 8 su 8, stesso ordine |
| Link interni M03 | Nessun collegamento rotto |
| File vuoti o quasi vuoti M03 | 0 |
| TODO non grafici | 0 |
| Placeholder legacy | 0 |
| Placeholder grafici | 11, tutti inventariati e chiusi |
| Commenti HTML non chiusi | 0 |
| Comandi LaTeX privi di backslash cercati | 0 residui |
| Paragrafi lunghi duplicati | 0 |
| `git diff --check` sul perimetro M03 | Superato |
| `git diff --check` globale | Restano soltanto 4 trailing whitespace preesistenti in M11 |

## 16. Matrice di chiusura delle lacune

| Lacuna iniziale | File coinvolto | Intervento | Stato finale | Verifica |
|---|---|---|---|---|
| Cardinalità finita incompleta | UD1/L1 | $I_n$, unicità, notazioni, liste e ripetizioni | Implementata integralmente | Definizioni ed esercizi presenti |
| Principi di conteggio non dimostrati | UD1/L1A | Addizione, prodotto, inclusione-esclusione, parti e funzioni | Implementata integralmente | Dimostrazioni combinatorie |
| Cassetti solo enunciato | UD1/L1A | Forma funzionale/generalizzata, prova e applicazioni | Implementata integralmente | Esempi ed esercizi graduati |
| Infinito e Dedekind non distinti | UD1/L2 | Definizioni, equivalenza in ZFC e nota sulla scelta | Implementata integralmente | Dipendenza assiomatica esplicita |
| Confronto cardinale assente | UD1/L2A | Iniezioni, suriezioni, vuoto e scelta | Implementata integralmente | Casi limite trattati |
| Cantor-Bernstein assente | UD1/L2A | Enunciato e prova costruttiva completa | Implementata integralmente | Iniettività e suriettività verificate |
| Convenzione numerabile ambigua | UD2/L1 | Numerabile infinito, al più e non numerabile | Implementata integralmente | Terminologia uniforme |
| $\mathbb Z$ soltanto elencato | UD2/L1 | Formula, inversa e prova biiettiva | Implementata integralmente | Controllo campionato |
| $\mathbb N^2$ assente | UD2/L1 | Accoppiamento di Cantor con inversa | Implementata integralmente | Controllo campionato |
| Sottoinsiemi e prodotti senza prova | UD2/L1 | Teoremi e induzione | Implementata integralmente | Ipotesi esplicite |
| Unione numerabile affermata | UD2/L1A | Suriezione da $I\times\mathbb N$ e scelta numerabile | Implementata integralmente | Vuoti e duplicati gestiti |
| $\mathbb Q$ soltanto illustrato | UD2/L1A | Suriezione, duplicati e prova di infinità | Implementata integralmente | Griglia non confusa con biiezione |
| Stringhe e sequenze finite assenti | UD2/L1A | $\Sigma^*$, $\mathbb N^{<\mathbb N}$, programmi | Implementata integralmente | Confronto con sequenze infinite |
| Diagonale decimale fallace | UD2/L2 | Diagonale binaria e nota sulle espansioni | Implementata integralmente | Oggetto diagonale formalizzato |
| Teorema di Cantor solo enunciato | UD2/L2 | Iniezione e prova di non suriettività | Implementata integralmente | Caso vuoto incluso |
| Non numerabilità di $\mathbb R$ incompleta | UD2/L2 | Due iniezioni e Cantor-Bernstein | Implementata integralmente | $\mathfrak c=2^{\aleph_0}$ dimostrato |
| Intervalli e $\mathbb R^n$ mancanti | UD2/L2A | Biiezioni e codifiche senza decimali | Implementata integralmente | Casi vuoto/degenere separati |
| Gerarchia e CH assenti | UD2/L2A | Aritmetica, gerarchia, $\aleph_1$, indipendenza | Implementata integralmente | CH distinta da teorema |
| Collegamenti informatici assenti | UD2/L1A, UD2/L2A | Stringhe, programmi e funzioni non calcolabili | Implementata integralmente | Digressioni avanzate rinviate |
| Esercizi e placeholder insufficienti | Tutte le lezioni | Problemi trasversali e 11 specifiche grafiche | Implementata integralmente | Inventario uno a uno |

## 17. Gap analysis conclusiva

| Categoria | Stato | Residuo |
|---|---|---|
| Cardinalità finita e conteggio | Completo | Combinatoria enumerativa avanzata non necessaria |
| Infiniti e confronto | Completo | Teoria ordinale e costruzione assiomatica dei cardinali rinviate |
| Numerabilità | Completo | Forme deboli dell'assioma della scelta solo segnalate |
| Cantor e continuo | Completo | Forcing e modelli di ZFC fuori perimetro |
| Applicazioni informatiche | Complete e proporzionate | Calcolabilità formale rinviata |
| Esercizi e dimostrazioni | Completi | Nessuna competenza obbligatoria scoperta |
| Grafici | Completi come specifica | Immagini da inserire manualmente |

Non risultano lacune matematiche pertinenti residue. Gli argomenti non sviluppati sono approfondimenti di teoria assiomatica, combinatoria avanzata o calcolabilità e sono rinviati con motivazione.

## 18. Valutazione finale

M03 è autosufficiente entro il proprio perimetro e utilizza M02 senza duplicarlo. Lo studente dispone di definizioni formali, costruzioni esplicite, dimostrazioni dei risultati centrali, controesempi, errori frequenti ed esercizi che richiedono strategia e verifica delle funzioni.

La revisione di accettazione ha controllato separatamente finito/infinito, convenzione numerabile, Cantor-Bernstein, $\mathbb Z$, $\mathbb N^2$, $\mathbb Q$, unioni e prodotti, stringhe, successioni binarie, teorema di Cantor, reali, intervalli, $\aleph_0$, $\mathfrak c$, ipotesi del continuo e confini modulari. Tutte le lacune iniziali risultano chiuse o correttamente classificate come approfondimenti esterni.

Verdetto: **M03 COMPLETO E CERTIFICATO**.
