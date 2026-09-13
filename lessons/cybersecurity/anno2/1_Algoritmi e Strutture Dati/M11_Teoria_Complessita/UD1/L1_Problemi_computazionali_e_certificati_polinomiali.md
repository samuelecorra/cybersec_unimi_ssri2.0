# Modulo finale — Computational Complexity Theory / Teoria della Complessità Computazionale

# Lezione 1 — Computational Problems and Polynomial Certificates / Problemi computazionali e certificati polinomiali

## 0. Perché studiamo la teoria della complessità?

Finora abbiamo studiato soprattutto **come progettare algoritmi**:

- **Greedy Algorithms / algoritmi greedy**;
    
- **Backtracking / backtrack**;
    
- **Divide and Conquer / divide et impera**;
    
- **Dynamic Programming / programmazione dinamica**;
    
- algoritmi sui grafi;
    
- algoritmi di ordinamento e ricerca.
    

In questo modulo cambiamo prospettiva.

Non chiediamo più soltanto:

> **Come posso risolvere questo problema?**

ma soprattutto:

> **Quanto è difficile, in linea di principio, risolvere questo problema?**

La **Computational Complexity Theory / Teoria della Complessità Computazionale** studia le risorse necessarie per risolvere problemi computazionali, principalmente:

$$  
\text{tempo}  
\qquad\text{e}\qquad  
\text{spazio}.  
$$

Nel corso ci concentreremo soprattutto sulla **complessità temporale**.

L'obiettivo non è quindi soltanto confrontare due algoritmi già noti, ma comprendere qualcosa di più profondo:

> esiste oppure no un algoritmo efficiente per un determinato problema?

---

# 1. Prima distinzione fondamentale: Algorithm vs Computational Problem / Algoritmo vs problema computazionale

Questa distinzione è essenziale per tutto il modulo.

Un **computational problem / problema computazionale** specifica:

- quali sono gli input possibili;
    
- quale output deve essere prodotto;
    
- quale proprietà deve essere soddisfatta dalla soluzione.
    

Un **algorithm / algoritmo** è invece un particolare procedimento utilizzato per risolvere quel problema.

## Esempio: Sorting / ordinamento

Consideriamo il problema:

> Ordinare un vettore di $n$ elementi.

Il problema computazionale è uno solo.

Possiamo però risolverlo utilizzando algoritmi differenti:

$$  
\text{Insertion Sort},  
\qquad  
\text{Merge Sort},  
\qquad  
\text{Quick Sort},  
\qquad \ldots  
$$

che hanno complessità differenti.

Ad esempio:

$$  
\text{Insertion Sort}: O(n^2)  
$$

mentre:

$$  
\text{Merge Sort}: O(n\log n).  
$$

Quindi:

> **La complessità di un particolare algoritmo non coincide automaticamente con la complessità intrinseca del problema.**

Questo punto diventa fondamentale quando incontriamo problemi per i quali conosciamo soltanto algoritmi molto costosi.

Se troviamo un algoritmo che impiega:

$$  
O(2^n),  
$$

abbiamo dimostrato soltanto che:

> **quell'algoritmo** richiede tempo esponenziale.

Non abbiamo dimostrato che:

> **ogni possibile algoritmo** debba necessariamente richiedere tempo esponenziale.

Potrebbe esistere un algoritmo completamente diverso e molto più efficiente che non abbiamo ancora scoperto.

---

# 2. Input Size / Dimensione dell'input

Quando diciamo che un algoritmo impiega tempo:

$$  
O(n^2)  
$$

oppure:

$$  
O(2^n),  
$$

dobbiamo sempre specificare cosa rappresenta $n$.

Nella teoria della complessità $n$ rappresenta, in generale, la:

$$  
\boxed{\text{input size / dimensione dell'input}}.  
$$

Quindi la complessità non viene misurata rispetto al valore astratto del problema, ma rispetto alla quantità di informazione necessaria per rappresentarne l'istanza.

Per esempio, per un grafo possiamo avere come parametri:

$$  
|V|  
$$

numero di vertici, e:

$$  
|E|  
$$

numero di archi.

Per una griglia $n\times n$, invece, il numero di celle è:

$$  
n^2.  
$$

Questa attenzione alla dimensione dell'input sarà particolarmente importante quando distingueremo tempo polinomiale e tempo superpolinomiale.

---

# 3. Tractable vs Intractable Problems / Problemi trattabili e intrattabili

Informalmente distinguiamo tra problemi **trattabili** e problemi **intrattabili**.

## 3.1 Tractable Problems / problemi trattabili

Un problema viene considerato teoricamente **tractable / trattabile** quando esiste un algoritmo che lo risolve in tempo polinomiale rispetto alla dimensione dell'input.

Esempi di complessità polinomiali sono:

$$  
O(n),  
\qquad  
O(n\log n),  
\qquad  
O(n^2),  
\qquad  
O(n^3),  
\qquad  
O(n^{10}).  
$$

In generale:

$$  
\boxed{O(n^c)}  
$$

dove $c$ è una costante.

Nella teoria della complessità il tempo polinomiale viene utilizzato come modello teorico di:

$$  
\boxed{\text{efficient computation / computazione efficiente}}.  
$$

Attenzione però:

> **polinomiale non significa necessariamente veloce nella pratica.**

Un algoritmo:

$$  
O(n^{100})  
$$

è formalmente polinomiale, pur essendo completamente inutilizzabile per input di dimensioni realistiche.

La distinzione è quindi teorica:

$$  
\text{polynomial}  
\quad\text{vs}\quad  
\text{superpolynomial}.  
$$

---

## 3.2 Intractable Problems / problemi intrattabili

Informalmente chiamiamo **intractable / intrattabili** quei problemi per i quali non conosciamo algoritmi polinomiali e per i quali gli algoritmi noti possono avere costi del tipo:

$$  
2^n,  
\qquad  
3^n,  
\qquad  
n!,  
\qquad  
n^n,  
\qquad\ldots  
$$

Queste funzioni crescono molto più rapidamente di qualsiasi polinomio.

Per esempio:

$$  
n^3  
$$

rimane polinomiale, mentre:

$$  
2^n  
$$

è esponenziale.

La differenza diventa enorme già per valori moderati di $n$.

---

# 4. Decision Problems / Problemi decisionali

La teoria di $P$ e $NP$ viene formalmente costruita soprattutto sui:

$$  
\boxed{\text{Decision Problems / problemi decisionali}}.  
$$

Un problema decisionale è un problema la cui risposta può essere soltanto:

$$  
\boxed{YES}  
\qquad\text{oppure}\qquad  
\boxed{NO}.  
$$

Questa forma può sembrare limitante, ma moltissimi problemi di ricerca o ottimizzazione possono essere trasformati in una versione decisionale.

---

# 5. Optimization Problem vs Decision Problem / Problema di ottimizzazione vs problema decisionale

Supponiamo di avere un problema di ottimizzazione.

## Optimization version / versione di ottimizzazione

Chiede di trovare la soluzione migliore.

Esempio:

> Qual è il tour di costo minimo che visita tutte le città?

Formalmente:

$$  
\min \operatorname{cost}(T).  
$$

---

## Decision version / versione decisionale

Chiede invece se esiste una soluzione che soddisfa una determinata soglia.

Esempio:

> Esiste un tour che visita tutte le città e ha costo totale al massimo $k$?

Quindi:

$$  
\exists T:  
\operatorname{cost}(T)\leq k?  
$$

La risposta è semplicemente:

$$  
YES  
\qquad\text{oppure}\qquad  
NO.  
$$

Quando parleremo formalmente delle classi $P$ e $NP$, ragioneremo principalmente sulle **versioni decisionali** dei problemi.

---

# 6. Primo esempio: Bounded Tiling Problem / Problema del Domino Limitato

Uno dei problemi utilizzati dalla docente è il:

$$  
\boxed{\text{Bounded Tiling Problem}}  
$$

che possiamo rendere in italiano come:

> **Problema del Domino Limitato**.

Si può incontrare anche il termine:

$$  
\boxed{\text{Bounded Wang Tiling}}  
$$

quando le tessere utilizzate sono **Wang Tiles / tessere di Wang**.

Attenzione:

> qui “domino” non indica necessariamente le comuni tessere rettangolari $1\times2$.

Possiamo immaginare invece tessere quadrate con colori o simboli sui quattro lati.

---

## 6.1 Istanza del problema

Sono dati:

$$  
n>0  
$$

e un insieme finito di tipi di tessera:

$$  
D={d_1,d_2,\ldots,d_m}.  
$$

![](imgs/Pasted%20image%2020260907213118.png)

Dobbiamo stabilire se sia possibile riempire completamente una griglia:

$$  
n\times n  
$$

utilizzando copie delle tessere appartenenti a $D$.

Le condizioni sono:

1. le tessere non possono essere ruotate;
    
2. una determinata tessera $d\in D$ deve essere collocata in una posizione prestabilita, per esempio in basso a sinistra;
    
3. due tessere adiacenti devono avere colori compatibili sui lati condivisi.
    

La domanda decisionale è quindi:

> Esiste almeno una configurazione valida della griglia?

---

# 7. Perché il Bounded Tiling sembra difficile?

Una griglia:

$$  
n\times n  
$$

contiene:

$$  
n^2  
$$

celle.

Supponiamo che una cella sia già fissata.

Rimangono:

$$  
n^2-1  
$$

celle da riempire.

Se per ciascuna cella possiamo scegliere uno tra $m$ tipi di tessera, il numero teorico di assegnamenti possibili è:

$$  
\boxed{m^{n^2-1}}.  
$$

Questo numero cresce molto rapidamente.

---

## 7.1 Esempio: $m=n=3$

La griglia contiene:

$$  
3^2=9  
$$

celle.

Una è già fissata, quindi rimangono:

$$  
9-1=8  
$$

celle.

Con $3$ tipi di tessera:

$$  
3^8=6561.  
$$

Abbiamo quindi già migliaia di configurazioni possibili per una griglia minuscola.

![](imgs/Pasted%20image%2020260907213157.png)

Se invece:

$$  
m=5,  
\qquad  
n=10,  
$$

otteniamo:

$$  
5^{99},  
$$

un numero astronomico.

---

# 8. Exhaustive Search / Ricerca esaustiva

L'algoritmo più ingenuo sarebbe:

> genera tutte le possibili configurazioni e verifica se almeno una è valida.

Questa strategia prende il nome di:

$$  
\boxed{\text{Exhaustive Search / ricerca esaustiva}}  
$$

oppure, informalmente:

$$  
\boxed{\text{Brute Force / forza bruta}}.  
$$

Nel caso del Bounded Tiling, una ricerca completamente esaustiva potrebbe esaminare fino a:

$$  
m^{n^2-1}  
$$

configurazioni.

---

## 8.1 Distinzione fondamentale

Da questo possiamo concludere:

> **Il brute force appena descritto ha costo superpolinomiale.**

Non possiamo invece concludere:

> **Il Bounded Tiling richiede necessariamente tempo superpolinomiale.**

Questa seconda affermazione riguarderebbe la complessità intrinseca del problema, non la complessità di un singolo algoritmo.

Quindi:

$$  
\boxed{  
\text{algoritmo lento}  
\not\Rightarrow  
\text{problema intrinsecamente lento}  
}  
$$

Potrebbe esistere un algoritmo più intelligente.

---

# 9. k-COLORABILITY / Problema della Colorazione

Consideriamo ora il:

$$  
\boxed{\text{k-COLORABILITY}}  
$$

o più genericamente:

$$  
\boxed{\text{Graph Coloring Problem / problema della colorazione dei grafi}}.  
$$

## Input

Un grafo non orientato:

$$  
G=(V,E)  
$$

e un intero:

$$  
k\geq 1.  
$$

## Domanda

> È possibile colorare tutti i vertici utilizzando al massimo $k$ colori in modo che due vertici adiacenti abbiano colori differenti?

Formalmente cerchiamo una funzione:

$$  
c\rightarrow{1,\ldots,k}  
$$

tale che:

$$  
(u,v)\in E  
\Longrightarrow  
c(u)\neq c(v).  
$$

![](imgs/Pasted%20image%2020260907214648.png)

---

## 9.1 Esempio

Consideriamo un triangolo:

$$  
A-B-C-A.  
$$

Se utilizziamo soltanto due colori, il triangolo non può essere colorato correttamente.

Quindi:

$$  
(G,2)\rightarrow NO.  
$$

Con tre colori invece:

$$  
(G,3)\rightarrow YES.  
$$

---

## 9.2 Attenzione: il valore di $k$ conta

Non bisogna imparare genericamente:

> “Graph Coloring è difficile.”

Per esempio:

$$  
\boxed{\text{2-COLORABILITY}\in P}  
$$

perché verificare se un grafo è 2-colorabile equivale a verificare se è **bipartito**.

Quindi piccoli cambiamenti nei parametri di un problema possono modificarne drasticamente la complessità.

---

# 10. CLIQUE / Problema della Cricca

Il nome internazionale è:

$$  
\boxed{\text{CLIQUE}}.  
$$

Una **clique / cricca** è un insieme di vertici tali che ogni coppia di vertici dell'insieme è collegata da un arco.

Equivalentemente, il sottografo indotto da quei vertici è completo.

---

## 10.1 Versione decisionale

### Input

Un grafo non orientato:

$$  
G=(V,E)  
$$

e un intero:

$$  
k.  
$$

### Domanda

> Esiste una clique contenente almeno $k$ vertici?

Cerchiamo quindi un insieme:

$$  
C\subseteq V  
$$

tale che:

$$  
|C|\geq k  
$$

e:

$$  
\forall u,v\in C,\quad  
u\neq v  
\Rightarrow  
(u,v)\in E.  
$$

![](imgs/Pasted%20image%2020260907215329.png)

---

## 10.2 Esempio

Supponiamo che:

$$  
C={A,B,C,D}  
$$

e che tutti questi vertici siano collegati a coppie.

Allora il sottografo indotto è:

$$  
K_4  
$$

e quindi abbiamo una clique di dimensione $4$.

L'istanza:

$$  
(G,4)  
$$

ha risposta:

$$  
YES.  
$$

---

# 11. Traveling Salesman Problem — TSP / Problema del Commesso Viaggiatore

Nome standard:

$$  
\boxed{\text{Traveling Salesman Problem — TSP}}  
$$

oppure, nell'inglese britannico:

$$  
\text{Travelling Salesman Problem}.  
$$

---

## 11.1 Optimization TSP / TSP di ottimizzazione

La versione classica chiede:

> Qual è il tour di costo minimo che visita ogni città esattamente una volta e ritorna alla città di partenza?

---

## 11.2 Decision TSP / TSP decisionale

Per la teoria della complessità utilizziamo invece la versione:

### Input

- un insieme di città;
    
- costi o distanze tra le città;
    
- una soglia $k$.
    

### Domanda

> Esiste un tour che visita ogni città esattamente una volta, ritorna alla città iniziale e ha costo totale non superiore a $k$?

Il tour è quindi strettamente collegato al concetto di:

$$  
\boxed{\text{Hamiltonian Cycle / ciclo hamiltoniano}}.  
$$

---

## 11.3 Esempio

Supponiamo di avere il tour:

$$  
A\rightarrow B\rightarrow C\rightarrow D\rightarrow A  
$$

con costi:

$$  
3,\quad5,\quad2,\quad4.  
$$

Il costo totale è:

$$  
3+5+2+4=14.  
$$

Se:

$$  
k=15,  
$$

allora:

$$  
14\leq15  
$$

e quindi la risposta è:

$$  
YES.  
$$

Se invece:

$$  
k=12,  
$$

quel tour non soddisfa il vincolo.

---

# 12. Il punto comune: trovare sembra difficile, verificare è facile

Consideriamo nuovamente:

- **Bounded Tiling**;
    
- **k-COLORABILITY**;
    
- **CLIQUE**;
    
- **Decision TSP**.
    

Sembrano problemi completamente diversi.

Eppure condividono una caratteristica molto importante:

> se qualcuno ci propone già una soluzione, controllare che sia corretta è molto più semplice che trovarla da zero.

Questa distinzione introduce uno dei concetti fondamentali della teoria della complessità:

$$  
\boxed{\text{Certificate / Certificato}}.  
$$

---

# 13. Certificate / Certificato e Verifier / Verificatore

Supponiamo di avere un problema decisionale.

Indichiamo con:

$$  
x  
$$

l'istanza del problema.

Un:

$$  
\boxed{\text{certificate / certificato}}  
$$

o:

$$  
\boxed{\text{witness / testimone}}  
$$

è un'informazione aggiuntiva che dimostra che l'istanza $x$ è una **YES-instance**.

Il certificato **non è un algoritmo**.

L'algoritmo che controlla il certificato prende invece il nome di:

$$  
\boxed{\text{verifier / verificatore}}.  
$$

Il verificatore riceve quindi:

$$  
V(x,y),  
$$

dove:

- $x$ è l'istanza;
    
- $y$ è il certificato.
    

L'idea fondamentale è:

$$  
\boxed{  
\text{certificato}=\text{soluzione proposta}  
}  
$$

mentre:

$$  
\boxed{  
\text{verificatore}=\text{algoritmo che la controlla}  
}  
$$

---

# 14. Polynomial Certificate / Certificato polinomiale

Non basta che esista un certificato.

Per essere utile nella caratterizzazione che introdurremo successivamente, il certificato deve avere **dimensione polinomiale rispetto alla dimensione dell'input** e deve poter essere verificato in **tempo polinomiale**.

Informalmente:

$$  
x\text{ è YES}  
$$

se esiste un certificato:

$$  
y  
$$

di lunghezza polinomiale tale che:

$$  
V(x,y)=YES  
$$

e $V$ termina in tempo polinomiale.

L'idea è quindi:

$$  
\boxed{  
\text{YES-instance}  
\Longrightarrow  
\text{esiste una prova corta e verificabile efficientemente}  
}  
$$

---

# 15. Certificato per k-COLORABILITY

Supponiamo che qualcuno sostenga che un grafo sia $k$-colorabile.

## Certificato

Ci fornisce un colore per ogni vertice:

$$  
(c(v_1),c(v_2),\ldots,c(v_n)).  
$$

---

## Verifica

Per ogni arco:

$$  
(u,v)\in E  
$$

controlliamo che:

$$  
c(u)\neq c(v).  
$$

Se tutti gli archi soddisfano questa condizione, la colorazione è valida.

Il controllo richiede di esaminare gli archi del grafo.

Quindi, con un'opportuna rappresentazione:

$$  
\boxed{O(|V|+|E|)}  
$$

oppure, se l'assegnazione è già disponibile e ci interessa soltanto il controllo degli archi:

$$  
O(|E|).  
$$

Tempo polinomiale.

---

# 16. Certificato per CLIQUE

Supponiamo che qualcuno sostenga:

> Nel grafo esiste una clique di almeno $k$ vertici.

## Certificato

Ci fornisce direttamente l'insieme:

$$  
C={v_1,\ldots,v_k}.  
$$

---

## Verifica

Dobbiamo controllare che ogni coppia di vertici appartenente a $C$ sia collegata.

Il numero di coppie è:

$$\frac{k(k-1)}{2}.  
$$

Quindi dobbiamo effettuare:

$$  
O(k^2)  
$$

controlli di adiacenza.

Questo è tempo polinomiale.

La distinzione è evidente:

$$  
\boxed{\text{trovare la clique}}  
$$

può essere difficile,

mentre:

$$  
\boxed{\text{controllare una clique già proposta}}  
$$

è semplice.

---

# 17. Certificato per Decision TSP

Supponiamo che qualcuno sostenga che esista un tour di costo al massimo $k$.

## Certificato

Ci fornisce una sequenza:

$$  
v_1,v_2,\ldots,v_n  
$$

che rappresenta il tour:

$$  
v_1  
\rightarrow  
v_2  
\rightarrow  
\cdots  
\rightarrow  
v_n  
\rightarrow  
v_1.  
$$

---

## Verifica

Il verificatore controlla:

1. che ogni elemento della sequenza sia un vertice valido;
    
2. che ogni vertice compaia esattamente una volta;
    
3. che ogni arco del tour esista;
    
4. che il tour torni al vertice iniziale;
    
5. che il costo complessivo sia:
    

$$  
\sum_{i=1}^{n-1}  
w(v_i,v_{i+1})  
+  
w(v_n,v_1)  
\leq k.  
$$

Tutti questi controlli possono essere effettuati in tempo polinomiale.

Quindi il tour costituisce un:

$$  
\boxed{\text{Polynomial Certificate / certificato polinomiale}}.  
$$

---

# 18. Certificato per Bounded Tiling

Supponiamo che qualcuno sostenga che la griglia sia ricopribile.

## Certificato

Ci fornisce direttamente una configurazione completa:

$$  
n\times n  
$$

delle tessere.

---

## Verifica

Non dobbiamo generare tutte le configurazioni.

Dobbiamo controllare soltanto quella proposta.

Per ogni coppia di celle adiacenti verifichiamo che i lati delle rispettive tessere siano compatibili.

Il numero di adiacenze da controllare è dell'ordine di:

$$  
O(n^2).  
$$

Quindi:

$$  
\boxed{\text{verificare una configurazione è polinomiale}}.  
$$

Il problema combinatorio è invece **trovarne una valida**.

---

# 19. Trovare vs verificare

Questa è l'idea centrale della prima lezione.

Abbiamo due attività concettualmente differenti:

$$  
\boxed{\text{SEARCH / trovare}}  
$$

e:

$$  
\boxed{\text{VERIFY / verificare}}.  
$$

Trovare una soluzione significa esplorare lo spazio delle possibilità.

Verificare significa controllare una soluzione già proposta.

---

## 19.1 Analogia: il labirinto

Supponiamo di avere un grande labirinto.

La domanda è:

> Esiste un percorso dall'ingresso all'uscita?

Trovare il percorso può richiedere esplorazione.

Ma se qualcuno ci fornisce:

$$  
\text{Ingresso}  
\rightarrow  
A  
\rightarrow  
B  
\rightarrow  
C  
\rightarrow  
\text{Uscita},  
$$

possiamo semplicemente verificare che ogni passaggio sia consentito.

Quindi:

$$  
\boxed{  
\text{trovare una soluzione}  
\neq  
\text{verificare una soluzione}  
}  
$$

dal punto di vista computazionale.

---

# 20. Prima anticipazione delle classi P e NP

Possiamo ora anticipare, senza ancora formalizzarle completamente, due classi fondamentali.

## P — Polynomial Time

La classe:

$$  
\boxed{P}  
$$

conterrà i problemi decisionali che possiamo **risolvere deterministicamente** in tempo polinomiale.

Quindi, intuitivamente:

$$
\text{problemi che sappiamo risolvere efficientemente}  

$$

nel senso teorico della complessità.

---

## NP — Nondeterministic Polynomial Time

La classe:

$$  
\boxed{NP}  
$$

può essere caratterizzata intuitivamente come la classe dei problemi decisionali le cui **YES-instances possiedono certificati di lunghezza polinomiale verificabili deterministicamente in tempo polinomiale**.

Quindi:

$$  
\boxed{  
NP  
\approx  
\text{YES-solutions verificabili efficientemente}  
}  
$$

Questa non è ancora la storia completa.

La sigla $NP$ significa infatti:

$$  
\boxed{\text{Nondeterministic Polynomial Time}}  
$$

e non:

$$  
\cancel{\text{Non-Polynomial}}.  
$$

Ma per capire davvero perché compare la parola **Nondeterministic**, dobbiamo introdurre un nuovo modello teorico di computazione.

Sarà precisamente l'argomento della prossima lezione.

---

# 21. Una relazione che possiamo già intuire

Se un problema può essere **risolto** in tempo polinomiale, allora possiamo certamente anche verificarne una soluzione in tempo polinomiale.

Per questo motivo:

$$  
\boxed{P\subseteq NP}.  
$$

Si legge:

> **P è contenuto in NP.**

Cioè:

$$  
X\in P  
\Longrightarrow  
X\in NP.  
$$

La questione fondamentale sarà capire se questa inclusione sia in realtà un'uguaglianza:

$$  
P\stackrel{?}{=}NP.  
$$

Ma per arrivare correttamente a questa domanda dobbiamo prima capire cosa significhi realmente **computazione non deterministica**.

---

# 22. Cosa NON dobbiamo ancora concludere

Da questa lezione non dobbiamo ancora concludere che:

> i problemi come CLIQUE, TSP o Coloring richiedano necessariamente tempo esponenziale.

La formulazione corretta è:

> Per alcuni di questi problemi conosciamo algoritmi deterministici molto costosi, mentre una soluzione già proposta può essere verificata in tempo polinomiale.

Non abbiamo ancora gli strumenti teorici per parlare in modo completo di:

- **NP-hardness / NP-difficoltà**;
    
- **NP-completeness / NP-completezza**;
    
- **Polynomial-Time Reductions / riduzioni in tempo polinomiale**.
    

Questi concetti verranno introdotti successivamente.

---

# 23. Tabella definitiva della Lezione 1

|Termine internazionale|Resa italiana|Idea fondamentale|
|---|---|---|
|**Computational Complexity Theory**|Teoria della Complessità Computazionale|Studia le risorse necessarie per risolvere problemi|
|**Computational Problem**|Problema computazionale|Specifica input e soluzione richiesta|
|**Algorithm**|Algoritmo|Procedimento concreto che risolve un problema|
|**Input Size**|Dimensione dell'input|Quantità di informazione che descrive l'istanza|
|**Tractable Problem**|Problema trattabile|Ammette un algoritmo polinomiale|
|**Intractable Problem**|Problema intrattabile|Informalmente, problema per cui non conosciamo algoritmi efficienti|
|**Decision Problem**|Problema decisionale|Risposta YES/NO|
|**Optimization Problem**|Problema di ottimizzazione|Richiede la soluzione migliore|
|**Exhaustive Search**|Ricerca esaustiva|Esamina tutte le possibili soluzioni|
|**Brute Force**|Forza bruta|Strategia ingenua di enumerazione completa|
|**Bounded Tiling Problem**|Problema del Domino Limitato|Riempire una griglia con tessere compatibili|
|**k-COLORABILITY**|Problema della Colorazione|Colorare il grafo con al massimo $k$ colori|
|**CLIQUE**|Problema della Cricca|Cercare almeno $k$ vertici mutuamente adiacenti|
|**Traveling Salesman Problem — TSP**|Problema del Commesso Viaggiatore|Cercare un tour che visita tutte le città|
|**Certificate / Witness**|Certificato / testimone|Prova di una YES-instance|
|**Verifier**|Verificatore|Algoritmo che controlla il certificato|
|**Polynomial Certificate**|Certificato polinomiale|Certificato corto e verificabile in tempo polinomiale|
|**P — Polynomial Time**|Classe P|Problemi decisionali risolvibili deterministicamente in tempo polinomiale|
|**NP — Nondeterministic Polynomial Time**|Classe NP|Anticipazione: YES-solutions verificabili in tempo polinomiale|

---

# 24. Le frasi da sapere perfettamente all'esame

### 1. Problema e algoritmo non sono la stessa cosa

> Un problema computazionale specifica ciò che deve essere risolto; un algoritmo è un particolare procedimento utilizzato per risolverlo.

Quindi:

$$  
\boxed{  
\text{complessità di un algoritmo}  
\neq  
\text{necessariamente complessità intrinseca del problema}  
}  
$$

---

### 2. Un problema decisionale produce YES oppure NO

$$  
\boxed{  
\text{Decision Problem}  
\rightarrow  
{YES,NO}  
}  
$$

---

### 3. Un certificato non è un algoritmo

Il:

$$  
\boxed{\text{Certificate / certificato}}  
$$

è la prova proposta.

Il:

$$  
\boxed{\text{Verifier / verificatore}}  
$$

è l'algoritmo che la controlla.

---

### 4. Trovare e verificare sono concetti diversi

$$  
\boxed{  
\text{SEARCH / trovare}  
\qquad\neq\qquad  
\text{VERIFY / verificare}  
}  
$$

Una soluzione può essere difficile da trovare pur essendo molto semplice da controllare una volta fornita.

---

### 5. NP non significa Non-Polynomial

$$  
\boxed{  
NP=\text{Nondeterministic Polynomial Time}  
}  
$$

Il significato preciso di **Nondeterministic** verrà introdotto nella prossima lezione.

---

# 25. Idea centrale della Lezione 1

Tutta la lezione può essere compressa nella contrapposizione:

$$  
\boxed{\text{TROVARE una soluzione}}  
\qquad\text{vs}\qquad  
\boxed{\text{VERIFICARE una soluzione}}.  
$$

Per diversi problemi combinatori, lo spazio delle possibili soluzioni può essere enorme.

Per esempio:

$$  
m^{n^2-1}  
$$

nel brute force del Bounded Tiling.

Ma se qualcuno ci fornisce già una soluzione candidata, spesso possiamo controllarla con un numero polinomiale di operazioni.

Questo ci porta alla domanda naturale:

$$  
\boxed{  
\text{Come possiamo modellare teoricamente una computazione}  
\text{ che possa "scegliere" una soluzione corretta?}  
}  
$$

La risposta introduce il concetto di:

$$  
\boxed{\text{Nondeterministic Computation / computazione non deterministica}}.  
$$

Ed è esattamente il punto da cui partirà la **Lezione 2**.