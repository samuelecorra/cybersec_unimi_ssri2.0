# Audit trasversale conclusivo di M04 - Numeri Complessi

Data dell'audit e dell'accettazione: 19 luglio 2026.

## 1. Ambito e metodo

L'audit riguarda l'intera cartella `M04_Numeri Complessi` e confronta filesystem, `schema_lezioni.md`, `manifest.json`, titoli, formule KaTeX, definizioni, dimostrazioni, esempi, esercizi, rinvii e placeholder. Il lavoro è stato svolto in tre fasi: lettura integrale degli 8 file iniziali, integrazione matematica e didattica, rilettura di accettazione con matrice di chiusura.

M01-M03 sono stati usati come prerequisiti senza modificarli. Di M05 e dei moduli successivi sono stati ispezionati soltanto struttura e titoli necessari a stabilire il confine didattico.

## 2. Inventario strutturale

| Unità didattica | Lezioni finali | Stato |
|---|---:|---|
| UD1 - Costruzione ed operazioni | 4 | Completa |
| UD2 - Forma trigonometrica ed esponenziale | 3 | Completa |
| UD3 - Radici e logaritmi | 3 | Completa |
| **Totale** | **10** | **3 UD allineate** |

Agli 8 file iniziali sono state aggiunte, con suffissi non distruttivi, `UD1/L3A - Luoghi geometrici e trasformazioni.md` e `UD3/L1A - Equazioni e fattorizzazione in C.md`. Non sono stati rinominati, spostati o rimossi file. I 10 titoli rispettano il formato `M4 UDx Lezione N` e l'ordine pedagogico è algebrico, geometrico, polare, esponenziale, radicale e polinomiale.

## 3. Mappa dei prerequisiti

1. M01 fornisce algebra, polinomi, fattorizzazione, radicali, trigonometria, esponenziale reale e funzioni iperboliche.
2. M02 fornisce insiemi, funzioni, relazioni, ordine reale e completezza; M04 chiarisce perché l'ordine di campo non si estende a $\mathbb C$.
3. M03 non è prerequisito tecnico diretto, ma la distinzione finito/infinito sostiene la lettura degli insiemi di valori del logaritmo.
4. UD1 costruisce $\mathbb C$, operazioni, coniugato, modulo, distanza, luoghi e trasformazioni.
5. UD2 usa trigonometria e modulo per forma polare, De Moivre ed esponenziale.
6. UD3 usa tutte le forme precedenti per radici, equazioni, fattorizzazione, logaritmi e potenze generali.

Non risultano dipendenze circolari. Le prove mediante serie, limiti, derivate o analisi complessa sono dichiarate come anticipazioni o rinvii.

## 4. Contenuti inizialmente presenti

Gli 8 file iniziali fornivano una progressione corretta ma introduttiva: motivazione tramite $x^2+1=0$, forma $a+bi$, operazioni elementari, piano di Argand-Gauss, modulo e argomento, forma trigonometrica, prodotti e quozienti polari, De Moivre operativa, forma esponenziale, formula delle radici e logaritmo multivalore.

Erano corretti la convenzione $\operatorname{Arg}z\in(-\pi,\pi]$, l'avvertenza sui quadranti, il caso $z=0$ nelle radici e il rinvio della giustificazione profonda di Eulero. Tuttavia le lezioni erano prive della maggior parte delle dimostrazioni, di una teoria delle equazioni e di un apparato esercitativo sufficiente.

## 5. Lacune individuate

| Area | Lacuna iniziale | Gravità |
|---|---|---:|
| Costruzione | Mancavano $\mathbb C=\mathbb R^2$, operazioni sulle coppie, immersione di $\mathbb R$ e inverso | Alta |
| Struttura | Campo, campo ordinato, chiusura algebrica e impossibilità dell'ordine non erano distinti | Alta |
| Algebra | Proprietà del coniugato, potenze negative e somme di potenze di $i$ erano incomplete | Media |
| Modulo | Mancavano moltiplicatività, disuguaglianza triangolare e inversa con prove | Alta |
| Geometria | Luoghi, corone, rapporti di distanze, similitudini, riflessioni e inversione erano assenti | Alta |
| Forma polare | Mancavano prova dei prodotti, criterio di uguaglianza e controllo sistematico dell'argomento principale | Alta |
| De Moivre | La prova non copriva formalmente $n=0$ e $n<0$ | Media |
| Radici | Mancavano prove di distinzione e completezza, ordine e struttura delle radici dell'unità | Alta |
| Equazioni | Assenti quadratiche complesse, radice algebrica, coniugate, molteplicità, fattorizzazione e TFA | Alta |
| Funzioni | Esponenziale generale, trigonometria complessa, rami, potenze generali e cautele erano insufficienti | Media |
| Didattica | Mancavano esercizi sistematici, controesempi, errori frequenti e riepiloghi formativi | Alta |
| Grafici | Due placeholder legacy non descrivevano contenuto né obiettivo | Media |

## 6. Interventi effettuati

Sono state ampliate tutte le 8 lezioni iniziali e create 2 lezioni autonome. L'integrazione comprende:

- costruzione sulle coppie, struttura di campo e dimostrazione dell'impossibilità di un ordine compatibile;
- calcolo algebrico completo, inversi, coniugio, modulo e disuguaglianze;
- luoghi geometrici e trasformazioni $az+b$, $a\overline z+b$ e $1/z$;
- forma polare con quadranti, congruenze degli argomenti e interpretazione del prodotto;
- De Moivre per tutti gli interi e identità trigonometriche;
- esponenziale e trigonometria complessi come approfondimento controllato;
- radici con prove di esistenza, distinzione e completezza, radici dell'unità e fattorizzazione di $z^n-1$;
- equazioni quadratiche e superiori, radici coniugate, molteplicità, fattorizzazioni su $\mathbb R$ e $\mathbb C$ e TFA;
- logaritmi, valore principale, rami e potenze complesse multivalore;
- 84 esercizi e 16 specifiche grafiche.

## 7. Definizioni revisionate

Sono ora formulate con dominio, convenzioni e unicità: numero complesso come coppia, unità immaginaria, parte reale e immaginaria, coniugato, modulo, distanza, argomento e argomento principale, forme algebrica/trigonometrica/esponenziale, radice $n$-esima, radice primitiva e ordine, molteplicità, campo algebricamente chiuso, esponenziale, logaritmo multivalore, valore principale, ramo e potenza complessa.

Sono separate in particolare:

- $i^2=-1$ dalla notazione ambigua $i=\sqrt{-1}$;
- $\operatorname{Im}(a+bi)=b$ dal termine $bi$;
- $\arg z$ multivalore da $\operatorname{Arg}z$;
- radice principale dall'insieme di tutte le radici;
- $\log z$ come insieme da $\operatorname{Log}z$;
- valore principale da ramo continuo su un dominio tagliato.

## 8. Teoremi e dimostrazioni

| Risultato | Collocazione | Esito |
|---|---|---|
| Costruzione di $\mathbb C$ e formula dell'inverso | UD1/L1 | Definizione e verifica completa |
| Impossibilità di un ordine di campo | UD1/L1 | Dimostrazione per assurdo |
| Proprietà del coniugato | UD1/L2 | Formule e prova del prodotto |
| Moltiplicatività del modulo | UD1/L3 | Dimostrazione tramite coniugio |
| Disuguaglianze triangolare e inversa | UD1/L3 | Dimostrazioni complete e casi di uguaglianza |
| Prodotto e quoziente polari | UD2/L1 | Derivazione dalle formule di addizione |
| De Moivre per $n\in\mathbb Z$ | UD2/L2 | Induzione, zero e inversi |
| Criterio di uguaglianza dell'esponenziale | UD2/L3 | Dimostrazione tramite modulo e fase |
| Formula delle radici $n$-esime | UD3/L1 | Esistenza, distinzione e completezza |
| Somma delle radici dell'unità | UD3/L1 | Prova algebrica; interpretazione geometrica |
| Radici coniugate dei polinomi reali | UD3/L1A | Dimostrazione completa e molteplicità |
| Teorema fondamentale dell'algebra | UD3/L1A | Enunciato rigoroso e conseguenze; prova rinviata motivatamente |

La formula di Eulero è dichiarata operativa e la prova mediante serie è rinviata a M06. Non risultano dimostrazioni circolari né equivalenze con un solo verso.

## 9. Esempi ed esercizi

Le 10 lezioni contengono 84 esercizi finali, oltre agli esempi svolti. Sono coperti operazioni e divisioni, potenze e somme di potenze di $i$, coniugato, modulo, distanze, luoghi, conversioni, argomenti, prodotti polari, potenze, radici, radici dell'unità, quadratiche, fattorizzazioni, equazioni con modulo o coniugato, trasformazioni, logaritmi e potenze generali.

I controesempi correggono uguaglianza triangolare, additività di $\operatorname{Arg}$ e $\operatorname{Log}$, unicità delle radici, regole delle radici principali, coppie coniugate per coefficienti non reali e leggi delle potenze con rami diversi. Non sono stati aggiunti blocchi di esercizi meramente ripetitivi.

## 10. Notazione

La notazione finale usa $z,w$ per complessi, $a,b,x,y$ per componenti reali, $\rho,r>0$ per moduli, $\theta,\varphi$ per argomenti, $k\in\mathbb Z$ per i rami e $\omega_k$ per le radici dell'unità. Parte reale e immaginaria sono rese con $\operatorname{Re}$ e $\operatorname{Im}$; coniugio con $\overline z$; valore principale del logaritmo con $\operatorname{Log}$.

Gli angoli sono in radianti. Le uguaglianze di argomenti sono formulate modulo $2\pi$ quando necessario. Sono stati corretti due comandi `\qquad` privi di backslash emersi dalla scansione e non restano comandi LaTeX noti corrotti.

## 11. Placeholder

Sono presenti 16 placeholder `TODO FIGURA`, tutti dettagliati, chiusi e inventariati uno a uno in `reports/analisi1/M04_placeholder_grafici.md`. I due placeholder legacy iniziali sono stati sostituiti. Non risultano TODO non grafici.

## 12. Confine con M01

| Argomento | M01 | M04 | Esito |
|---|---|---|---|
| Algebra e polinomi | Regole, fattorizzazione, molteplicità preliminare | Estensione dei coefficienti a $\mathbb C$ e chiusura algebrica | Nessuna duplicazione integrale |
| Trigonometria | Radianti, formule di addizione e identità | Coordinate polari, prodotti, De Moivre e radici | Prerequisito riusato con nuovo scopo |
| Esponenziale reale | Definizione operativa e proprietà | Esponenziale complesso, fase e periodicità | Estensione coerente |
| Funzioni iperboliche | Definizioni reali | Identità $\cos(iy)=\cosh y$, $\sin(iy)=i\sinh y$ | Collegamento breve |
| Radicali | Radici reali e convenzione principale | Plurivocità e rami complessi | Distinzione esplicita |

## 13. Confine con M05 e moduli successivi

| Contenuto | Stato in M04 | Collocazione futura | Motivazione |
|---|---|---|---|
| Successioni complesse e convergenza tramite modulo | Anticipato | M05 e futura analisi complessa | Richiede teoria dei limiti |
| Serie di $e^z$, seno e coseno | Anticipate | M06 | Servono per provare Eulero |
| Limiti e continuità complessi | Rinviati | Dopo M08, fuori dal nucleo reale di Analisi 1 | Richiedono topologia e limiti |
| Derivabilità e olomorfia | Rinviate | Analisi complessa | Non coincidono con la derivabilità reale |
| Integrali complessi e residui | Rinviati | Corso successivo | Fuori perimetro |
| Equazioni differenziali | Solo motivazione tramite radici caratteristiche | Moduli aggiuntivi dopo M05-M11 | Sequenza concordata rispettata |
| Fourier, FFT e fasori | Collegamento applicativo | Corsi di segnali e calcolo numerico | Nessuna teoria avanzata duplicata |
| Codici e curve ellittiche | Richiamo con caveat sui campi finiti | Algebra e crittografia | Non attribuiti direttamente a $\mathbb C$ |

M04 contiene quindi tutti i prerequisiti complessi necessari, ma non anticipa il programma operativo di M05.

## 14. Controlli matematici

Sono stati verificati manualmente e con un campione automatico:

- prodotti, divisioni, potenze positive e negative di $i$;
- coniugato di prodotto, moltiplicatività e disuguaglianza triangolare;
- potenze in forma polare e casi di De Moivre;
- radici dell'unità per $2\le n\le12$, con somma, prodotto e verifica $w^n=1$;
- radice algebrica di $5+12i$;
- quattro radici di $z^4+4$;
- soluzioni di $z^2=\overline z$;
- quadratiche e fattorizzazioni riportate negli esempi.

Il controllo automatico ha eseguito 170 asserzioni: 170 superate, 0 fallite. La revisione manuale ha inoltre controllato domini, casi $z=0$, indici $k=0,\ldots,n-1$, congruenze degli argomenti e differenza tra valori distinti e molteplicità.

## 15. Controlli tecnici

| Controllo | Risultato |
|---|---|
| KaTeX su M04 | 10 file, 1.169 formule, 0 errori |
| KaTeX su Analisi 1 | 184 file, 13.398 formule, 0 errori |
| KaTeX sui report di Analisi 1 | 8 file, 250 formule, 0 errori |
| Manifest SPA | Rigenerato, 5.863 file complessivi |
| Filesystem/schema/manifest e ordine | M04 10/10; Analisi 1 filesystem/manifest 226/226; ordine corretto |
| Collegamenti interni | 0 link Markdown in M04, 0 collegamenti rotti; rinvii testuali esistenti verificati |
| File vuoti o quasi vuoti | 0 in M04 |
| TODO non grafici | 0 in M04 |
| Placeholder legacy | 0 in M04 |
| Commenti HTML | 16 aperti e 16 chiusi |
| Comandi LaTeX noti privi di backslash | 0 residui |
| Paragrafi lunghi duplicati | 0 |
| `git diff --check` sul perimetro | Superato; controllo supplementare eseguito anche sui file nuovi non tracciati |
| `git diff --check` globale | Restano 4 spaziature finali preesistenti in M11, fuori perimetro |

## 16. Matrice di chiusura delle lacune

| Lacuna iniziale | File coinvolto | Intervento | Stato finale | Verifica |
|---|---|---|---|---|
| Costruzione di $\mathbb C$ | UD1/L1 | Coppie, operazioni, immersione reale e $i$ | Implementata integralmente | Definizioni e calcoli verificati |
| Struttura di campo | UD1/L1-L2 | Neutri, opposti, inversi, distributività | Implementata integralmente | Formula dell'inverso verificata |
| Ordine | UD1/L1 | Prova per assurdo e confronto con il modulo | Implementata integralmente | Nessuna disuguaglianza impropria residua |
| Forma algebrica | UD1/L1-L2 | Operazioni, parametri, potenze e somme di $i$ | Implementata integralmente | Esempi e 14 esercizi |
| Coniugato | UD1/L2-L3 | Proprietà algebriche e simmetria | Implementata integralmente | Prova e campione numerico |
| Modulo | UD1/L2-L3 | Distanza, prodotto, triangolare e inversa | Implementata integralmente | Due dimostrazioni complete |
| Luoghi geometrici | UD1/L3-L3A | Dischi, rette, corone, rapporti e argomenti | Implementata integralmente | Esempi cartesiani e grafici specificati |
| Trasformazioni | UD1/L3A, UD2/L1 | Similitudini, coniugio, inversione, rotazioni | Implementata integralmente | Problema trasversale svolto |
| Argomento | UD1/L3, UD2/L1 | Multivalore, principale, quadranti e `atan2` | Implementata integralmente | Casi sugli assi inclusi |
| Forma polare | UD2/L1 | Esistenza, unicità e conversioni | Implementata integralmente | Prodotti e quozienti derivati |
| Formula di Eulero | UD2/L3 | Status operativo e rinvio alle serie | Correttamente rinviata nella prova | Nessuna circolarità |
| De Moivre | UD2/L2 | Prova per interi positivi, zero e negativi | Implementata integralmente | Induzione completa |
| Potenze | UD1/L2, UD2/L2-L3 | Intere, negative, alte e periodiche | Implementata integralmente | Esempi e controlli automatici |
| Radici $n$-esime | UD3/L1 | Formula, distinzione e completezza | Implementata integralmente | Prova in tre parti |
| Radici dell'unità | UD3/L1 | Primitive, ordine, gruppo, somma e prodotto | Implementata integralmente | $n=2,\ldots,12$ campionati |
| Equazioni quadratiche | UD3/L1A | Discriminante complesso e radice algebrica | Implementata integralmente | Esempi reali e complessi |
| Radici coniugate | UD3/L1A | Teorema, prova e fattore reale | Implementata integralmente | Ipotesi sui coefficienti esplicita |
| Molteplicità | UD3/L1A | Fattorizzazione e criterio con derivate formali | Implementata integralmente | Valori distinti separati dalle molteplicità |
| Fattorizzazione | UD3/L1-L1A | $z^n-1$, fattori su $\mathbb C$ e $\mathbb R$ | Implementata integralmente | Esempi di grado 4 e 6 |
| Teorema fondamentale dell'algebra | UD3/L1A | Enunciato, equivalenza e chiusura algebrica | Prova correttamente rinviata | Motivazione analitica esplicita |
| Equazioni non polinomiali | UD3/L1A | Modulo, coniugato e parte reale | Implementata integralmente | Soluzioni finite e luoghi infiniti |
| Esponenziale e trigonometria complessi | UD2/L3 | Definizioni, proprietà e confini | Implementata come approfondimento controllato | Limiti e olomorfia esclusi |
| Logaritmi e potenze generali | UD3/L2 | Multivalore, principale, rami e controesempi | Implementata integralmente | Domini e inclusioni controllati |
| Placeholder | Sei lezioni | 16 specifiche dettagliate e inventario | Implementata integralmente | Corrispondenza uno a uno |
| Preparazione d'esame | Tutte le lezioni | 84 esercizi, errori e riepiloghi | Implementata integralmente | Tutti i nuclei rappresentati |

## 17. Gap analysis conclusiva

| Categoria | Stato finale | Residuo |
|---|---|---|
| Lacune matematiche pertinenti a M04 | Chiuse | Nessuno |
| Definizioni e notazione | Complete e coerenti | Nessuno |
| Dimostrazioni interne al perimetro | Complete | Nessuno |
| Teorema fondamentale dell'algebra | Enunciato e conseguenze completi | Prova analitica correttamente rinviata |
| Formula di Eulero | Uso operativo coerente | Prova mediante serie correttamente rinviata |
| Analisi complessa | Fuori perimetro | Limiti, olomorfia, integrali e residui |
| Equazioni differenziali | Fuori sequenza corrente | Solo collegamento motivazionale |
| Figure definitive | Specifiche complete | Inserimento manuale futuro di 16 immagini |

I residui sono approfondimenti futuri motivati, non lacune del modulo.

## 18. Valutazione finale

M04 è autosufficiente nel proprio perimetro: costruisce il campo complesso, sviluppa calcolo e geometria, controlla argomento e forme di rappresentazione, dimostra potenze e radici, completa la teoria elementare delle equazioni polinomiali e gestisce con rigore logaritmi e potenze multivalore.

La matrice di accettazione non contiene elementi mancanti o implementati soltanto in parte. I soli rinvii riguardano prove e teorie che richiedono serie o analisi complessa e sono motivati nei file interessati. Manifest, ordine, report, formule, placeholder e controlli di integrità sono consolidati.

**Verdetto: M04 COMPLETO E CERTIFICATO.**
