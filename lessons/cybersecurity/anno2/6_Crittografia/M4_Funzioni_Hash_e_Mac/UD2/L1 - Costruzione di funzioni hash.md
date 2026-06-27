## **Lezione 1: Costruzione di funzioni hash**

### **1. Introduzione**

In questa lezione analizziamo **come si costruiscono le funzioni hash crittografiche** a partire da messaggi di lunghezza arbitraria.

Una funzione hash crittografica deve avere una caratteristica apparentemente semplice, ma tecnicamente delicata:

- deve accettare messaggi di input di lunghezza arbitraria;
- deve restituire sempre un digest di lunghezza fissata;
- deve essere efficiente da calcolare;
- deve conservare proprietà di sicurezza come resistenza alla preimmagine, alla seconda preimmagine e soprattutto alle collisioni.

Il problema nasce perché una primitiva crittografica elementare lavora quasi sempre su input di dimensione fissata.

Per esempio:

- una funzione di compressione accetta blocchi di una certa lunghezza;
- un cifrario a blocchi cifra blocchi di lunghezza fissata;
- una chiave di cifratura ha una dimensione specifica;
- uno stato interno ha una lunghezza fissata.

Una funzione hash completa, invece, deve trattare messaggi molto più variabili:

- un messaggio di pochi byte;
- un file di grandi dimensioni;
- un certificato;
- una password;
- un intero documento;
- un flusso di dati.

Per questo motivo non basta definire una funzione $h$ con input fisso.

Bisogna definire uno **schema di costruzione** che permetta di applicare ripetutamente, o combinare, blocchi elementari fino a ottenere un digest finale.

> 📌 La costruzione di una funzione hash serve a trasformare il problema "hashare messaggi arbitrariamente lunghi" nel problema più gestibile "combinare in modo sicuro blocchi di lunghezza fissa".

---

### **2. Messaggi di Lunghezza Arbitraria e Blocchi di Lunghezza Fissa**

Sia $M$ il messaggio di input.

In generale $M$ può avere una lunghezza qualsiasi.

Per poterlo elaborare con primitive a lunghezza fissata, lo si divide in blocchi:

$$
M = (m_1, m_2, \ldots, m_k)
$$

dove:

- $m_i$ è l'$i$-esimo blocco del messaggio;
- tutti i blocchi hanno la stessa lunghezza dopo l'eventuale padding;
- $k$ è il numero di blocchi;
- il digest finale deve avere lunghezza fissata, indipendentemente da $k$.

La divisione in blocchi non è solo un dettaglio implementativo.

È parte del progetto crittografico della funzione.

Infatti, se il modo in cui i blocchi vengono combinati è debole, un attaccante può cercare collisioni o manipolare parti del messaggio senza dover rompere direttamente la primitiva elementare.

#### **2.1. Padding**

Prima della divisione in blocchi, il messaggio viene spesso sottoposto a una fase di **padding**.

Il padding aggiunge bit al messaggio per fare in modo che la lunghezza complessiva sia compatibile con la dimensione dei blocchi.

L'obiettivo è ottenere:

$$
M' = (m_1, m_2, \ldots, m_k)
$$

dove $M'$ è il messaggio dopo padding e tutti i blocchi $m_i$ hanno lunghezza uguale.

Il padding è necessario perché l'ultimo blocco del messaggio originale potrebbe essere incompleto.

Per esempio, se la funzione lavora su blocchi da $512$ bit e il messaggio ha lunghezza non multipla di $512$, l'ultimo blocco non può essere processato direttamente.

> ⚠️ Il padding non deve essere ambiguo: due messaggi diversi non devono diventare la stessa sequenza di blocchi dopo il padding.

---

### **3. Due Approcci Generali: Parallelo e Seriale**

Una volta ottenuti blocchi di lunghezza fissa, esistono due grandi famiglie di costruzione:

- costruzioni **parallele**;
- costruzioni **seriali** o **iterate**.

Nel caso parallelo, più blocchi possono essere elaborati contemporaneamente.

Nel caso seriale, i blocchi vengono elaborati uno dopo l'altro, e ogni passo dipende dal risultato del passo precedente.

La differenza è importante sia per l'efficienza sia per la sicurezza.

#### **3.1. Approccio Parallelo**

Nell'approccio parallelo, il messaggio viene suddiviso in blocchi e i blocchi vengono combinati secondo una struttura ad albero.

L'idea è:

- raggruppare i blocchi a coppie;
- applicare una funzione hash o una funzione di compressione a ciascuna coppia;
- ottenere valori intermedi;
- raggruppare di nuovo i valori intermedi a coppie;
- ripetere fino ad arrivare a un unico digest finale.

#### **3.2. Approccio Seriale o Iterato**

Nell'approccio seriale, invece, si mantiene uno stato interno.

Ogni blocco del messaggio aggiorna lo stato:

$$
H_i = f(H_{i-1}, m_i)
$$

dove:

- $H_{i-1}$ è lo stato precedente;
- $m_i$ è il blocco corrente;
- $f$ è la funzione di compressione;
- $H_i$ è il nuovo stato.

Il digest finale è lo stato ottenuto dopo l'ultimo blocco.

---

### **4. Costruzione Parallela**

Consideriamo un messaggio suddiviso in $L$ blocchi.

Nel transcript il docente considera il caso in cui $L$ sia una potenza di due.

Per esempio:

$$
L = 2^3 = 8
$$

Quindi il messaggio è composto da otto blocchi:

$$
M = (m_1, m_2, m_3, m_4, m_5, m_6, m_7, m_8)
$$

Lo schema parallelo procede per livelli.

Al primo livello:

- $(m_1, m_2)$ vengono dati in input a una funzione hash o compressione;
- $(m_3, m_4)$ vengono dati in input a una seconda istanza;
- $(m_5, m_6)$ vengono dati in input a una terza istanza;
- $(m_7, m_8)$ vengono dati in input a una quarta istanza.

Si ottengono così quattro valori intermedi.

Al secondo livello, questi quattro valori vengono raggruppati a coppie e processati di nuovo.

Si ottengono due valori intermedi.

All'ultimo livello, i due valori intermedi vengono combinati per produrre il digest finale.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.1. Vantaggio del Parallelismo**

Il vantaggio principale è l'efficienza.

I calcoli dello stesso livello non dipendono l'uno dall'altro.

Quindi possono essere eseguiti contemporaneamente.

Nel caso degli otto blocchi:

- le quattro elaborazioni del primo livello possono essere eseguite in parallelo;
- le due elaborazioni del secondo livello possono essere eseguite in parallelo;
- solo l'ultimo passaggio deve attendere i due valori intermedi finali.

Questo riduce il tempo complessivo quando sono disponibili più processori, core o unità di calcolo.

> 💡 La costruzione parallela è più veloce della costruzione seriale perché molte elaborazioni intermedie possono essere precomputate o calcolate contemporaneamente.

#### **4.2. Proprietà di Damgård**

Il transcript richiama un risultato dovuto a Damgård.

Il risultato riguarda la sicurezza della composizione parallela.

L'idea è:

> se il blocco costitutivo $h$ è resistente alle collisioni, allora anche la funzione hash composta con lo schema parallelo è resistente alle collisioni.

Il ragionamento intuitivo è il seguente.

Supponiamo di trovare due messaggi diversi $M$ e $M'$ tali che:

$$
H(M) = H(M')
$$

Nella struttura ad albero, il digest finale è ottenuto applicando funzioni elementari a coppie di valori.

Se i due alberi producono lo stesso valore finale ma partono da foglie diverse, allora, risalendo l'albero, deve esistere almeno un punto in cui la stessa funzione elementare riceve due input diversi e produce lo stesso output.

Questo punto corrisponde a una collisione per il blocco $h$.

Quindi una collisione nella funzione composta permetterebbe di ricavare una collisione nella funzione interna.

Se la funzione interna è resistente alle collisioni, anche la composizione risulta sicura rispetto a collisioni.

> 📌 La sicurezza dello schema parallelo si riduce alla sicurezza della funzione hash elementare usata nei nodi dell'albero.

#### **4.3. Caso in cui il Numero di Blocchi non è una Potenza di Due**

Il transcript assume per semplicità che $L$ sia una potenza di due.

Questa ipotesi rende l'albero bilanciato.

Nelle implementazioni reali, però, il numero di blocchi può non essere una potenza di due.

In quel caso bisogna stabilire una regola di composizione:

- aggiungere padding strutturale;
- duplicare o trattare separatamente un nodo spaiato;
- usare un albero non perfettamente bilanciato;
- includere informazioni di dominio o di lunghezza per evitare ambiguità.

Il punto critico è sempre lo stesso:

> la stessa sequenza finale di operazioni non deve poter rappresentare due messaggi strutturalmente diversi.

---

### **5. Costruzione Seriale o Iterata**

Un secondo modello generale è quello **seriale**, detto anche **iterato**.

In questo schema il messaggio viene prima sottoposto a padding.

Poi viene diviso in $n$ blocchi di uguale lunghezza:

$$
M' = (m_1, m_2, \ldots, m_n)
$$

I blocchi vengono elaborati uno alla volta.

Ogni fase usa:

- il blocco corrente $m_i$;
- il valore intermedio prodotto dalla fase precedente;
- una funzione di compressione.

#### **5.1. Valore Iniziale**

Per la prima fase non esiste ancora un valore precedente.

Per questo si usa un valore iniziale, chiamato **Initialization Vector**:

$$
IV
$$

oppure, con la notazione del transcript:

$$
V
$$

Si pone:

$$
H_0 = IV
$$

La prima fase calcola:

$$
H_1 = f(H_0, m_1)
$$

La seconda fase calcola:

$$
H_2 = f(H_1, m_2)
$$

E così via fino all'ultimo blocco.

In generale:

$$
H_i = f(H_{i-1}, m_i)
$$

Il digest finale è:

$$
H(M) = H_n
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Interpretazione dello Stato Intermedio**

Il valore $H_i$ può essere visto come un riassunto di tutto ciò che è stato elaborato fino al blocco $i$.

Infatti:

- $H_1$ dipende da $IV$ e da $m_1$;
- $H_2$ dipende da $H_1$ e da $m_2$, quindi dipende anche da $m_1$;
- $H_3$ dipende da $H_2$ e da $m_3$, quindi dipende da $m_1$, $m_2$ e $m_3$;
- alla fine $H_n$ dipende da tutti i blocchi.

Questa dipendenza a catena è il motivo per cui si parla di struttura iterata.

#### **5.3. Sicurezza della Costruzione Iterata**

Il transcript richiama il fatto che una collisione per la funzione iterata è legata a una collisione nella funzione di compressione.

La formulazione prudente è:

> se la funzione di compressione è resistente alle collisioni e il padding è ben definito, allora la funzione hash iterata eredita la resistenza alle collisioni.

L'intuizione è simile a quella dello schema parallelo.

Supponiamo di avere due messaggi diversi $M$ e $M'$ tali che:

$$
H(M) = H(M')
$$

Con padding non ambiguo, i due messaggi diventano due sequenze di blocchi ben definite.

Se i digest finali coincidono, allora lungo la catena deve accadere una delle seguenti cose:

- a un certo passo due coppie diverse di input alla funzione di compressione producono lo stesso output;
- oppure il padding ha introdotto ambiguità.

Se il padding è corretto, resta la prima possibilità: una collisione nella funzione di compressione.

> 📌 Nelle costruzioni iterate, la funzione di compressione è il blocco critico: se è debole, l'intera funzione hash è debole.

---

### **6. Confronto tra Costruzione Parallela e Iterata**

Le due costruzioni trattano lo stesso problema in modo diverso.

#### **6.1. Costruzione Parallela**

La costruzione parallela:

- elabora più blocchi contemporaneamente;
- può essere più veloce su architetture parallele;
- organizza il calcolo come un albero;
- richiede attenzione alla struttura dell'albero;
- richiede regole chiare se il numero di blocchi non è una potenza di due.

#### **6.2. Costruzione Iterata**

La costruzione iterata:

- elabora un blocco alla volta;
- usa uno stato interno;
- è semplice da implementare;
- è adatta a messaggi in streaming;
- è alla base di molte funzioni hash storiche;
- dipende fortemente dalla funzione di compressione.

> 💡 La costruzione parallela privilegia l'efficienza su blocchi indipendenti; la costruzione iterata privilegia semplicità e trattamento sequenziale del messaggio.

---

### **7. Funzioni Hash a Cascata**

Un altro modello di composizione è quello delle **funzioni hash a cascata**.

L'idea è usare più funzioni hash già disponibili per costruirne una più robusta.

Siano:

$$
H_1 : \{0,1\}^* \to \{0,1\}^n
$$

e:

$$
H_2 : \{0,1\}^* \to \{0,1\}^n
$$

due funzioni hash con output di $n$ bit.

Una costruzione a cascata può essere definita concatenando i due digest:

$$
H(M) = H_1(M) \parallel H_2(M)
$$

dove $\parallel$ indica la concatenazione.

L'output complessivo ha quindi lunghezza:

$$
2n
$$

#### **7.1. Collisioni nella Cascata**

Per trovare una collisione per $H$, bisogna trovare due messaggi diversi $M$ e $M'$ tali che:

$$
H(M) = H(M')
$$

cioè:

$$
H_1(M) \parallel H_2(M) = H_1(M') \parallel H_2(M')
$$

Questo richiede entrambe le uguaglianze:

$$
H_1(M) = H_1(M')
$$

e:

$$
H_2(M) = H_2(M')
$$

Quindi una collisione per la funzione composta è simultaneamente una collisione per $H_1$ e per $H_2$.

#### **7.2. Ruolo dell'Indipendenza**

Il transcript sottolinea che $H_1$ e $H_2$ devono essere indipendenti.

L'indipendenza è importante perché, se le due funzioni fossero correlate, una debolezza di una potrebbe riflettersi anche sull'altra.

Se invece le funzioni sono indipendenti, trovare un messaggio che collide per entrambe è più difficile che trovare una collisione per una sola.

> 📌 La cascata rafforza la sicurezza perché obbliga l'attaccante a soddisfare contemporaneamente due condizioni di collisione.

#### **7.3. Perché la Cascata è una Tecnica Semplice**

La costruzione a cascata è concettualmente semplice:

- si prendono funzioni hash già note;
- si applicano entrambe allo stesso messaggio;
- si concatena il risultato.

Non richiede di progettare da zero una nuova funzione di compressione.

Il costo è che:

- l'output è più lungo;
- il calcolo richiede due funzioni;
- bisogna gestire due primitive invece di una.

---

### **8. Funzioni Hash Basate su Cifrari a Blocchi**

Una tecnica molto diffusa per costruire funzioni hash è usare come blocco costitutivo un **cifrario a blocchi**.

Un cifrario a blocchi è una funzione di cifratura del tipo:

$$
E_K(X)
$$

dove:

- $K$ è la chiave;
- $X$ è il blocco da cifrare;
- l'output ha la stessa lunghezza del blocco cifrato.

L'idea è usare la cifratura non per ottenere segretezza, ma per costruire una funzione di compressione.

Questo è possibile perché un cifrario a blocchi ben progettato ha buone proprietà di diffusione: piccole variazioni nell'input o nella chiave producono variazioni apparentemente imprevedibili nell'output.

> ⚠️ Usare un cifrario a blocchi dentro una funzione hash non significa cifrare il messaggio per renderlo segreto. Significa riusare la struttura del cifrario per produrre digest.

#### **8.1. Problema della Dimensione della Chiave**

Il transcript evidenzia un problema pratico.

Un cifrario a blocchi richiede una chiave di lunghezza fissata.

Per esempio, se il cifrario richiede una chiave di $\ell$ bit, non si può usare direttamente un valore di lunghezza diversa come chiave.

Per questo si introduce spesso una funzione:

$$
G
$$

che trasforma un input di $n$ bit in un valore adatto alla dimensione della chiave.

Per esempio:

$$
G : \{0,1\}^n \to \{0,1\}^{\ell}
$$

In questo modo, se lo stato precedente $H_{i-1}$ ha $n$ bit, si può ricavare una chiave valida:

$$
K_i = G(H_{i-1})
$$

#### **8.2. Struttura Generale con Padding e IV**

Come nelle costruzioni iterate, anche qui il messaggio viene sottoposto a padding:

$$
M \longrightarrow M' = (m_1, m_2, \ldots, m_k)
$$

Si fissa poi una costante predefinita:

$$
H_0 = IV
$$

o, come indicato nel transcript:

$$
H_0 = V
$$

Ogni blocco $m_i$ aggiorna lo stato interno.

Alla fine:

$$
H(M) = H_k
$$

Il digest finale è quindi ottenuto dopo aver processato anche l'ultimo blocco di input.

---

### **9. Schemi Classici Basati su Cifrari a Blocchi**

Il transcript presenta tre possibili composizioni di funzioni hash basate su un cifrario a blocchi.

Questi schemi differiscono per:

- cosa viene cifrato;
- cosa viene usato come chiave;
- quali valori vengono combinati con XOR;
- se serve una funzione $G$ per adattare la dimensione della chiave.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.1. Matyas-Meyer-Oseas**

Nel primo schema descritto dal docente, l'output della fase $i$ viene calcolato cifrando il blocco corrente $m_i$ con una chiave ricavata dal blocco precedente $H_{i-1}$.

Poi il risultato viene messo in XOR con il blocco corrente $m_i$.

Formalmente:

$$
H_i = E_{G(H_{i-1})}(m_i) \oplus m_i
$$

Questo schema è comunemente noto come **Matyas-Meyer-Oseas**.

Il ruolo dei componenti è:

- $H_{i-1}$ è lo stato precedente;
- $G(H_{i-1})$ produce una chiave della lunghezza richiesta dal cifrario;
- $m_i$ è il blocco cifrato;
- $\oplus m_i$ serve a combinare l'output della cifratura con il blocco di messaggio.

L'uso di $G$ è necessario quando la lunghezza dello stato $H_{i-1}$ non coincide con la lunghezza della chiave richiesta dal cifrario.

#### **9.2. Miyaguchi-Preneel**

Il secondo schema riprende il precedente, ma aggiunge un ulteriore XOR con il blocco calcolato nella fase precedente.

La formula diventa:

$$
H_i = E_{G(H_{i-1})}(m_i) \oplus m_i \oplus H_{i-1}
$$

Questo schema è noto come **Miyaguchi-Preneel**.

Rispetto allo schema precedente, l'output dipende esplicitamente da tre elementi:

- risultato della cifratura;
- blocco corrente $m_i$;
- stato precedente $H_{i-1}$.

L'aggiunta di $H_{i-1}$ nell'XOR lega ancora più esplicitamente il nuovo stato al vecchio stato.

> 💡 L'XOR finale non è decorativo: serve a combinare più contributi e a evitare che la funzione di compressione coincida semplicemente con una cifratura.

#### **9.3. Davies-Meyer**

Il terzo schema descritto dal docente considera il caso in cui i blocchi di input abbiano dimensione uguale a quella della chiave richiesta dal cifrario.

In questo caso il blocco $m_i$ può essere usato direttamente come chiave.

Si cifra lo stato precedente $H_{i-1}$ usando $m_i$ come chiave e si mette il risultato in XOR con $H_{i-1}$:

$$
H_i = E_{m_i}(H_{i-1}) \oplus H_{i-1}
$$

Questo schema è noto come **Davies-Meyer**.

Qui:

- $m_i$ è usato come chiave;
- $H_{i-1}$ è il blocco cifrato;
- l'output cifrato viene combinato con $H_{i-1}$ tramite XOR.

Il blocco del messaggio deve essere compatibile con la dimensione della chiave del cifrario.

Se non lo è, bisogna introdurre un adattamento.

#### **9.4. Confronto tra i Tre Schemi**

I tre schemi hanno una struttura comune:

- usano un cifrario a blocchi;
- mantengono uno stato interno;
- processano il messaggio blocco per blocco;
- usano XOR per combinare valori;
- producono un digest finale dopo l'ultimo blocco.

Differiscono però per la posizione del messaggio:

- in Matyas-Meyer-Oseas, il messaggio viene cifrato;
- in Davies-Meyer, il messaggio viene usato come chiave;
- in Miyaguchi-Preneel, lo schema include sia il blocco corrente sia lo stato precedente nell'XOR finale.

> 📌 Tutti questi schemi cercano di trasformare un cifrario a blocchi in una funzione di compressione adatta alla costruzione di una funzione hash.

---

### **10. Proprietà e Limiti degli Schemi con Cifrari a Blocchi**

Il transcript sottolinea che gli schemi proposti sono stati studiati da diversi gruppi di ricercatori e possiedono proprietà diverse.

Questo è importante perché non ogni combinazione intuitiva di cifratura e XOR produce automaticamente una funzione hash sicura.

Per progettare una funzione hash a partire da un cifrario a blocchi bisogna considerare:

- dimensione del blocco del cifrario;
- dimensione della chiave;
- dimensione dello stato interno;
- modalità di padding;
- presenza di costanti iniziali;
- possibilità di collisioni strutturali;
- attacchi generici come birthday attack e meet-in-the-middle;
- eventuali debolezze del cifrario usato.

> ⚠️ Un cifrario a blocchi sicuro per la cifratura non produce automaticamente una funzione hash sicura in qualunque schema. La sicurezza dipende anche dalla costruzione.

---

### **11. Strutture a Catena e Attacco Meet-in-the-Middle**

Le funzioni hash con struttura a catena possono essere attaccate con una variante dell'attacco del compleanno chiamata **meet-in-the-middle**.

L'obiettivo è costruire un messaggio falso diverso da quello originale ma con lo stesso digest.

Nel transcript il docente considera:

- un messaggio originale $M$;
- un messaggio falso $R$;
- una struttura a due parti.

Scriviamo:

$$
M = M_1 \parallel M_2
$$

e:

$$
R = R_1 \parallel R_2
$$

L'obiettivo dell'attaccante è trovare:

$$
R \ne M
$$

tale che:

$$
H(R) = H(M)
$$

oppure, più in generale, tale che $H(R)$ sia un digest scelto dall'attaccante.

> 📌 Il meet-in-the-middle non cerca solo due messaggi casuali con lo stesso digest: può essere usato per costruire un messaggio falso che raggiunge un digest bersaglio.

#### **11.1. Differenza rispetto al Birthday Attack**

Nel birthday attack classico si cerca una collisione generica:

$$
H(X) = H(Y)
$$

con:

$$
X \ne Y
$$

L'attaccante non necessariamente sceglie in anticipo il digest finale.

Nel meet-in-the-middle, invece, l'attaccante può partire da un digest desiderato e lavorare a ritroso su una parte della costruzione.

Questo è possibile quando la struttura della funzione consente di invertire localmente alcuni passaggi, per esempio usando la decifratura del cifrario a blocchi.

---

### **12. Idea Generale del Meet-in-the-Middle**

L'attacco prende il nome dal fatto che due calcoli procedono da direzioni opposte e cercano di incontrarsi nello stesso valore intermedio.

Da un lato, l'attaccante procede **in avanti**:

- genera molte varianti della prima parte del messaggio falso;
- per ciascuna variante calcola il blocco intermedio.

Dall'altro lato, procede **all'indietro**:

- genera molte varianti della seconda parte del messaggio falso;
- parte dal digest bersaglio;
- usa la decifratura per risalire al blocco intermedio precedente.

Se un valore intermedio calcolato in avanti coincide con un valore intermedio calcolato all'indietro, allora le due parti possono essere unite.

Si ottiene così un messaggio falso che produce il digest desiderato.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.1. Insieme dei Valori Calcolati in Avanti**

L'attaccante genera molte varianti:

$$
R_1^{(1)}, R_1^{(2)}, \ldots, R_1^{(r_1)}
$$

Per ciascuna calcola il valore intermedio:

$$
X_j = F(IV, R_1^{(j)})
$$

dove $F$ rappresenta il primo passo della funzione hash.

Ottiene quindi un insieme di valori intermedi:

$$
\{X_1, X_2, \ldots, X_{r_1}\}
$$

#### **12.2. Insieme dei Valori Calcolati all'Indietro**

L'attaccante genera molte varianti:

$$
R_2^{(1)}, R_2^{(2)}, \ldots, R_2^{(r_2)}
$$

Poi parte dal digest bersaglio $T$.

Per ciascuna seconda parte calcola a ritroso il valore che avrebbe dovuto precedere l'ultimo passo:

$$
Y_j = F^{-1}(T, R_2^{(j)})
$$

Nelle costruzioni basate su cifrari a blocchi, questo calcolo a ritroso può usare la decifratura.

Ottiene quindi:

$$
\{Y_1, Y_2, \ldots, Y_{r_2}\}
$$

#### **12.3. Match Intermedio**

L'attacco riesce se esistono $a$ e $b$ tali che:

$$
X_a = Y_b
$$

In quel caso, la prima parte $R_1^{(a)}$ porta al valore intermedio corretto, e la seconda parte $R_2^{(b)}$ porta da quel valore al digest bersaglio.

Quindi:

$$
H(R_1^{(a)} \parallel R_2^{(b)}) = T
$$

Se $T = H(M)$, allora si è costruito un messaggio falso con lo stesso digest del messaggio originale.

> 💡 L'attacco si chiama meet-in-the-middle perché il punto decisivo non è né l'inizio né la fine del calcolo, ma l'incontro tra due insiemi di stati intermedi.

---

### **13. Esempio a Due Fasi con Cifrario a Blocchi**

Il transcript presenta un esempio più concreto.

Consideriamo una funzione hash applicata a un messaggio composto da due parti:

$$
M = M_1 \parallel M_2
$$

La funzione procede in due fasi.

Prima calcola il valore intermedio:

$$
H_1 = E_{M_1}(IV)
$$

Poi calcola il digest finale:

$$
H(M) = E_{M_2}(H_1)
$$

Qui:

- $IV$ è il valore iniziale;
- $M_1$ viene usato come chiave nella prima cifratura;
- $M_2$ viene usato come chiave nella seconda cifratura;
- $H_1$ è il blocco intermedio;
- $H(M)$ è il digest finale.

#### **13.1. Obiettivo dell'Attaccante**

L'attaccante vuole trovare un messaggio falso:

$$
M^* = M_1^* \parallel M_2^*
$$

tale che:

$$
H(M^*) = H(M)
$$

Quindi il messaggio falso deve produrre lo stesso digest del messaggio originale.

#### **13.2. Calcolo in Avanti**

L'attaccante genera molte varianti della prima parte:

$$
M_1^{*(1)}, M_1^{*(2)}, \ldots, M_1^{*(r_1)}
$$

Per ciascuna calcola:

$$
X_j = E_{M_1^{*(j)}}(IV)
$$

Questi valori sono possibili blocchi intermedi ottenibili partendo dall'inizio.

#### **13.3. Calcolo all'Indietro**

L'attaccante genera molte varianti della seconda parte:

$$
M_2^{*(1)}, M_2^{*(2)}, \ldots, M_2^{*(r_2)}
$$

Partendo dal digest bersaglio $H(M)$, calcola:

$$
Y_j = D_{M_2^{*(j)}}(H(M))
$$

dove $D_K$ è la decifratura con chiave $K$.

Infatti, se:

$$
H(M) = E_{M_2^*}(H_1^*)
$$

allora:

$$
H_1^* = D_{M_2^*}(H(M))
$$

Quindi $Y_j$ rappresenta il valore intermedio che sarebbe necessario prima della seconda fase.

#### **13.4. Costruzione della Collisione**

Se l'attaccante trova:

$$
X_a = Y_b
$$

allora può scegliere:

$$
M_1^* = M_1^{*(a)}
$$

e:

$$
M_2^* = M_2^{*(b)}
$$

Il messaggio falso:

$$
M^* = M_1^{*(a)} \parallel M_2^{*(b)}
$$

produce lo stesso digest del messaggio originale:

$$
H(M^*) = H(M)
$$

Il motivo è che la prima parte porta esattamente al valore intermedio richiesto dalla seconda parte.

> ✅ Il match tra stati intermedi permette di incollare una prima metà calcolata in avanti con una seconda metà calcolata all'indietro.

---

### **14. Probabilità di Successo e Collegamento con il Paradosso del Compleanno**

Il transcript sottolinea che la probabilità di trovare un match tra i blocchi intermedi è la stessa logica probabilistica dell'attacco del compleanno.

Se gli stati intermedi hanno lunghezza $n$ bit, lo spazio dei possibili stati ha dimensione:

$$
2^n
$$

L'attaccante costruisce due insiemi:

- un insieme di circa $r_1$ valori calcolati in avanti;
- un insieme di circa $r_2$ valori calcolati all'indietro.

La probabilità che esista almeno una corrispondenza cresce con il prodotto:

$$
r_1 \cdot r_2
$$

Se si scelgono valori dell'ordine di:

$$
r_1 \approx r_2 \approx 2^{n/2}
$$

allora:

$$
r_1 \cdot r_2 \approx 2^n
$$

e diventa plausibile trovare un match.

Questa è la stessa soglia tipica del birthday attack.

> 📌 Il meet-in-the-middle sfrutta due liste di valori intermedi: con liste di dimensione circa $2^{n/2}$, la probabilità di incontro diventa significativa.

#### **14.1. Estensione a Più Fasi**

Il docente osserva che il risultato non vale solo per due fasi.

Può essere esteso anche a funzioni hash con più fasi di cifratura e quindi con più blocchi intermedi.

L'idea rimane:

- scegliere un punto intermedio della catena;
- calcolare molte possibilità in avanti fino a quel punto;
- calcolare molte possibilità all'indietro dal digest bersaglio fino a quel punto;
- cercare una collisione tra gli stati intermedi.

La presenza di più fasi rende la descrizione più complessa, ma il principio resta invariato.

---

### **15. Interpretazione di Sicurezza**

Il meet-in-the-middle evidenzia una debolezza potenziale delle strutture a catena.

Se l'attaccante può:

- controllare varianti della prima parte;
- controllare varianti della seconda parte;
- calcolare in avanti;
- calcolare all'indietro;
- memorizzare molti stati intermedi;

allora può tentare di costruire messaggi falsi con digest desiderato.

Questo non significa che ogni funzione hash iterata sia automaticamente insicura.

Significa però che, nella progettazione, bisogna considerare attacchi che non agiscono solo dall'inizio alla fine.

L'attaccante può sfruttare la struttura interna.

> ⚠️ Una funzione hash non deve essere valutata solo come scatola nera: anche il modo in cui combina i blocchi influisce sulla sicurezza.

---

### **16. Collegamento con le Proprietà delle Funzioni Hash**

Le costruzioni viste nella lezione servono a preservare le proprietà fondamentali già introdotte nelle lezioni precedenti.

#### **16.1. Resistenza alle Collisioni**

La resistenza alle collisioni richiede che sia computazionalmente difficile trovare:

$$
M \ne M'
$$

tali che:

$$
H(M) = H(M')
$$

Le costruzioni parallele, iterate e a cascata cercano di trasferire questa proprietà dai blocchi elementari alla funzione complessiva.

#### **16.2. Resistenza alla Seconda Preimmagine**

La seconda preimmagine richiede che, dato un messaggio $M$, sia difficile trovare:

$$
M' \ne M
$$

tale che:

$$
H(M') = H(M)
$$

Il meet-in-the-middle è particolarmente rilevante perché può essere interpretato come una strategia per costruire un messaggio falso con digest selezionato.

#### **16.3. Digest a Lunghezza Fissa**

Tutte le costruzioni devono rispettare il vincolo:

$$
H : \{0,1\}^* \to \{0,1\}^n
$$

cioè input arbitrariamente lungo e output di $n$ bit.

Questo vincolo rende inevitabile l'esistenza teorica di collisioni.

La sicurezza non richiede che collisioni non esistano.

Richiede che siano computazionalmente difficili da trovare.

---

### **17. Sintesi dei Metodi di Costruzione**

La lezione illustra diversi metodi di costruzione di funzioni hash.

#### **17.1. Costruzione Parallela**

Il messaggio viene diviso in blocchi e processato tramite una struttura ad albero.

Vantaggio principale:

- velocità, perché molti blocchi possono essere elaborati contemporaneamente.

Condizione di sicurezza:

- se la funzione elementare è resistente alle collisioni, la composizione parallela può ereditare tale proprietà.

#### **17.2. Costruzione Seriale o Iterata**

Il messaggio viene diviso in blocchi e processato sequenzialmente.

Ogni stato dipende dal precedente:

$$
H_i = f(H_{i-1}, m_i)
$$

Vantaggio principale:

- semplicità e naturalezza per messaggi lunghi o in streaming.

Condizione di sicurezza:

- la funzione di compressione deve essere resistente alle collisioni;
- il padding deve essere non ambiguo.

#### **17.3. Costruzione a Cascata**

Si combinano due o più funzioni hash indipendenti.

Esempio:

$$
H(M) = H_1(M) \parallel H_2(M)
$$

Vantaggio principale:

- una collisione richiede collisioni simultanee nelle funzioni componenti.

#### **17.4. Costruzione da Cifrari a Blocchi**

Si usa un cifrario a blocchi per costruire una funzione di compressione.

Esempi:

- Matyas-Meyer-Oseas;
- Miyaguchi-Preneel;
- Davies-Meyer.

Vantaggio principale:

- riuso di primitive di cifratura già studiate.

Punto critico:

- la sicurezza dipende dallo schema di composizione, non solo dal cifrario.

---

### **18. Errori Concettuali da Evitare**

#### **18.1. Confondere Hash e Cifratura**

Una funzione hash non serve a rendere segreto il messaggio.

Produce un digest.

Quando un cifrario a blocchi viene usato per costruire una funzione hash, la cifratura è un blocco tecnico interno, non l'obiettivo finale.

#### **18.2. Pensare che il Padding Sia Solo Formattazione**

Il padding influenza la sicurezza.

Se è ambiguo, due messaggi diversi potrebbero essere trasformati nella stessa sequenza di blocchi.

#### **18.3. Pensare che Parallelismo Significhi Sempre Sicurezza Maggiore**

Il parallelismo migliora l'efficienza, non automaticamente la sicurezza.

La sicurezza dipende dal blocco hash interno e dalla composizione.

#### **18.4. Confondere Cascata e Iterazione**

Nella cascata si combinano funzioni diverse sullo stesso messaggio.

Nell'iterazione si applica ripetutamente una funzione di compressione ai blocchi del messaggio.

#### **18.5. Trascurare gli Attacchi Strutturali**

Attacchi come meet-in-the-middle sfruttano la struttura interna della costruzione.

Non basta conoscere la lunghezza del digest: bisogna analizzare come viene prodotto.

---

### **19. Conclusione**

Abbiamo visto che le funzioni hash possono essere costruite secondo diversi modelli strutturali.

Il problema di partenza è sempre lo stesso:

> trattare messaggi di lunghezza arbitraria producendo una stringa di bit di lunghezza fissata.

Le tecniche principali sono:

- costruzione parallela;
- costruzione seriale o iterata;
- costruzione a cascata;
- costruzione basata su cifrari a blocchi.

La costruzione parallela organizza i blocchi in una struttura ad albero e permette di calcolare valori intermedi contemporaneamente.

La costruzione iterata usa padding, un valore iniziale e una funzione di compressione applicata sequenzialmente.

La costruzione a cascata combina funzioni hash indipendenti per ottenere un digest più lungo e rendere più difficile trovare collisioni simultanee.

Le costruzioni basate su cifrari a blocchi usano primitive di cifratura per definire funzioni di compressione, ma richiedono attenzione alla dimensione della chiave, allo schema di XOR e alla struttura complessiva.

Infine, l'attacco meet-in-the-middle mostra che le funzioni hash a catena possono essere analizzate anche "dal centro": l'attaccante calcola valori intermedi in avanti e all'indietro e cerca una corrispondenza.

> ✅ La robustezza di una funzione hash dipende dalla funzione di compressione, dal padding, dalla lunghezza del digest e soprattutto dallo schema con cui i blocchi vengono combinati.

---

### **20. Recap per l'Esame**

> ✅ Una funzione hash completa deve accettare messaggi arbitrariamente lunghi e restituire digest a lunghezza fissa.

> ✅ Per gestire messaggi lunghi, il messaggio viene diviso in blocchi di lunghezza fissa, dopo una fase di padding.

> ✅ Nella costruzione parallela, i blocchi vengono combinati in una struttura ad albero; i valori dello stesso livello possono essere calcolati contemporaneamente.

> ✅ Secondo il risultato richiamato da Damgård, una composizione parallela può preservare la resistenza alle collisioni se la funzione elementare è resistente alle collisioni.

> ✅ Nella costruzione iterata, ogni blocco aggiorna uno stato interno: $H_i = f(H_{i-1}, m_i)$.

> ✅ Il valore iniziale $IV$ serve ad avviare la prima fase della catena.

> ✅ Una collisione nella funzione iterata è collegata a una collisione nella funzione di compressione, se il padding è corretto.

> ✅ Nella costruzione a cascata, se $H(M) = H_1(M) \parallel H_2(M)$, una collisione richiede collisioni simultanee per $H_1$ e $H_2$.

> ✅ I cifrari a blocchi possono essere usati per costruire funzioni di compressione, come Matyas-Meyer-Oseas, Miyaguchi-Preneel e Davies-Meyer.

> ✅ La funzione $G$ serve ad adattare uno stato alla dimensione della chiave richiesta dal cifrario.

> ✅ Il meet-in-the-middle costruisce valori intermedi in avanti e all'indietro e cerca un match.

> ✅ La probabilità di trovare un match intermedio segue la stessa logica del paradosso del compleanno: per stati di $n$ bit, insiemi di dimensione circa $2^{n/2}$ rendono plausibile l'incontro.

> ⚠️ Non basta usare un cifrario sicuro: anche lo schema di composizione deve essere progettato e analizzato crittograficamente.
