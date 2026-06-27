## **Lezione 2: MD4 e MD5**

### **1. Introduzione**

In questa lezione vengono introdotti **MD4** e **MD5**, due algoritmi storici per il calcolo di funzioni hash crittografiche.

La lezione precedente ha mostrato diversi modi generali per costruire funzioni hash:

- schemi paralleli;
- schemi seriali o iterati;
- schemi a cascata;
- costruzioni basate su cifrari a blocchi.

MD4 e MD5 appartengono a una prospettiva diversa.

Non sono semplicemente funzioni hash costruite componendo un cifrario a blocchi già esistente.

Sono algoritmi progettati appositamente per svolgere le operazioni tipiche di una funzione hash.

In altre parole:

> 📌 MD4 e MD5 sono funzioni hash progettate da zero per produrre digest, non adattamenti diretti di primitive pensate originariamente per cifrare.

Questa distinzione è importante.

Quando si costruisce una funzione hash a partire da un cifrario a blocchi, la sicurezza dipende anche da come il cifrario viene riusato.

Nel caso di MD4 e MD5, invece, l'intera struttura dell'algoritmo è stata progettata con l'obiettivo esplicito di:

- elaborare messaggi di lunghezza arbitraria;
- produrre un digest compatto;
- essere veloce in software;
- ottenere un buon effetto di diffusione;
- rendere difficile la ricerca di collisioni.

---

### **2. La Famiglia MD**

La famiglia di algoritmi studiata in questa lezione è stata progettata da **Ron Rivest**.

La sigla **MD** significa:

> **Message Digest**

cioè "digest del messaggio" o "impronta del messaggio".

Le due versioni principali considerate sono:

- **MD4**, progettato nel **1990**;
- **MD5**, progettato nel **1991** come evoluzione più robusta di MD4.

Il transcript contiene un evidente errore di trascrizione quando riporta l'anno di MD5 come "21": il riferimento corretto, coerente con la storia dell'algoritmo, è **1991**.

#### **2.1. Output**

Entrambi gli algoritmi producono un digest di:

$$
128 \text{ bit}
$$

Quindi:

$$
H(M) \in \{0,1\}^{128}
$$

per ogni messaggio $M$ ammesso dall'algoritmo.

#### **2.2. Lunghezza Massima del Messaggio**

MD4 e MD5 operano su messaggi di lunghezza arbitraria, ma con una limitazione tecnica:

$$
|M| < 2^{64} \text{ bit}
$$

Il motivo è che, nella fase di padding, la lunghezza originale del messaggio viene codificata su $64$ bit.

Quindi l'algoritmo può rappresentare lunghezze fino a $2^{64}-1$ bit.

> 📌 Il digest è sempre di 128 bit, mentre il messaggio può essere lungo fino a quasi $2^{64}$ bit.

---

### **3. Architetture a 32 bit e Little-Endian**

MD4 e MD5 sono progettati per essere efficienti su architetture a **32 bit**.

Questo significa che le operazioni fondamentali dell'algoritmo lavorano su parole di:

$$
32 \text{ bit}
$$

Una parola da $32$ bit può essere vista come una sequenza di $4$ byte:

$$
4 \cdot 8 = 32
$$

Quando una parola da $32$ bit viene memorizzata in memoria, bisogna decidere in quale ordine scrivere i suoi byte.

Da qui nasce la distinzione tra:

- **little-endian**;
- **big-endian**.

#### **3.1. Little-Endian**

In un'architettura **little-endian**, il byte con indirizzo più basso è quello **meno significativo**.

Supponiamo che una parola da $32$ bit sia composta dai byte:

$$
B_1, B_2, B_3, B_4
$$

memorizzati in indirizzi crescenti.

Nel caso little-endian:

- $B_1$ è il byte meno significativo;
- $B_4$ è il byte più significativo.

La parola numerica corrispondente è:

$$
W = 2^{0}B_1 + 2^{8}B_2 + 2^{16}B_3 + 2^{24}B_4
$$

Questa è l'architettura adottata dai processori di tipo **Intel** e, storicamente, da molte piattaforme software su cui MD4 e MD5 erano pensati per essere veloci.

#### **3.2. Big-Endian**

In un'architettura **big-endian**, il byte con indirizzo più basso è quello **più significativo**.

Con gli stessi byte:

$$
B_1, B_2, B_3, B_4
$$

la parola numerica è:

$$
W = 2^{24}B_1 + 2^{16}B_2 + 2^{8}B_3 + 2^{0}B_4
$$

Questa rappresentazione era adottata, per esempio, da architetture come **SPARCstation**.

#### **3.3. Perché l'Endianess Conta**

L'algoritmo spezza il messaggio in parole da $32$ bit.

Se la stessa sequenza di byte viene interpretata in ordine diverso, le parole ottenute cambiano.

Quindi un'implementazione deve rispettare esattamente la convenzione prevista dall'algoritmo.

MD4 e MD5 usano una rappresentazione **little-endian**.

> ⚠️ L'ordine dei byte non è un dettaglio estetico: cambia il valore delle parole da 32 bit su cui l'algoritmo opera.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Obiettivi di Progettazione**

MD4 e MD5 sono stati progettati per soddisfare diversi requisiti.

#### **4.1. Resistenza alle Collisioni**

Un obiettivo centrale era la resistenza alle collisioni.

Dato l'algoritmo $H$, dovrebbe essere computazionalmente difficile trovare due messaggi diversi:

$$
M \ne M'
$$

tali che:

$$
H(M) = H(M')
$$

Questo requisito è essenziale perché il digest deve comportarsi come un'impronta del messaggio.

Se fosse facile trovare due messaggi con lo stesso digest, il digest non sarebbe affidabile per applicazioni di integrità, firma digitale o identificazione del contenuto.

#### **4.2. Sicurezza non Basata su Problemi di Teoria dei Numeri**

Il transcript sottolinea che la sicurezza di MD4 e MD5 non è basata su problemi classici della teoria dei numeri.

Per esempio, non si basa direttamente su:

- fattorizzazione di interi;
- logaritmo discreto;
- RSA;
- Diffie-Hellman.

La sicurezza viene invece valutata analizzando la struttura dell'algoritmo:

- round;
- funzioni booleane;
- rotazioni;
- somme modulo $2^{32}$;
- diffusione;
- resistenza ad attacchi differenziali e collisioni.

> 📌 Per MD4 e MD5 la sicurezza è una proprietà della costruzione algoritmica, non una riduzione semplice a un problema matematico classico.

#### **4.3. Velocità**

MD4 e MD5 dovevano essere veloci in software.

Per questo usano operazioni naturali per CPU a $32$ bit:

- AND;
- OR;
- XOR;
- NOT;
- somma modulo $2^{32}$;
- rotazione ciclica.

Queste operazioni sono efficienti perché corrispondono a istruzioni molto semplici a livello macchina.

#### **4.4. Semplicità e Compattezza**

Un ulteriore requisito era la semplicità.

L'algoritmo doveva essere:

- facile da descrivere;
- facile da implementare;
- compatto;
- privo di strutture dati complesse.

Il transcript sottolinea che l'algoritmo non fa uso di tabelle complesse.

MD5 usa una tabella di $64$ costanti additive $T[i]$, ma queste costanti non sono una tabella arbitraria progettata a mano: vengono generate tramite la funzione seno.

Quindi l'algoritmo rimane descrivibile in modo compatto.

---

### **5. Padding del Messaggio**

MD4 e MD5 processano il messaggio in blocchi da:

$$
512 \text{ bit}
$$

Ogni blocco da $512$ bit è composto da:

$$
16
$$

parole da:

$$
32 \text{ bit}
$$

Infatti:

$$
16 \cdot 32 = 512
$$

Il messaggio originale $M$ può però avere una lunghezza qualsiasi.

Prima di essere processato, viene quindi trasformato in un messaggio $M'$ la cui lunghezza è multipla di $512$ bit.

Questa trasformazione avviene con il **padding**.

#### **5.1. Schema Standard del Padding**

Sia:

$$
|M| = b
$$

la lunghezza del messaggio originale in bit.

Il padding segue questi passi:

1. si aggiunge un bit `1`;
2. si aggiunge un certo numero di bit `0`;
3. si arriva a una lunghezza congrua a $448$ modulo $512$;
4. si aggiungono $64$ bit che codificano la lunghezza originale $b$.

In formula:

$$
M' = M \parallel 1 \parallel 0^p \parallel \operatorname{len}_{64}(M)
$$

dove:

- $\parallel$ indica concatenazione;
- $0^p$ indica una sequenza di $p$ zeri;
- $\operatorname{len}_{64}(M)$ è la lunghezza originale di $M$ codificata su $64$ bit;
- $p$ è scelto in modo che la lunghezza prima degli ultimi $64$ bit sia congrua a $448$ modulo $512$.

Quindi:

$$
b + 1 + p \equiv 448 \pmod{512}
$$

Dopo aver aggiunto anche la lunghezza su $64$ bit, si ottiene:

$$
b + 1 + p + 64 \equiv 0 \pmod{512}
$$

Il messaggio finale $M'$ ha quindi lunghezza multipla di $512$.

#### **5.2. Perché Si Usa 448 Modulo 512**

Il valore $448$ non è casuale.

In ogni blocco finale da $512$ bit, si vuole lasciare spazio per:

$$
64 \text{ bit}
$$

destinati alla lunghezza originale del messaggio.

Poiché:

$$
512 - 64 = 448
$$

il messaggio, dopo il bit `1` e gli zeri, deve arrivare a $448$ bit modulo $512$.

Gli ultimi $64$ bit completano il blocco.

#### **5.3. Numero di Bit di Padding**

Il numero di bit di padding può variare.

Si aggiunge sempre almeno un bit, il bit `1`.

Poi si aggiungono zeri fino a raggiungere la posizione corretta.

Se l'ultimo blocco ha già troppi bit per contenere anche i $64$ bit finali, viene creato un blocco aggiuntivo.

Quindi il padding può occupare:

- pochi bit, se c'è spazio nel blocco corrente;
- molti bit, se è necessario passare al blocco successivo.

> 📌 Il padding garantisce che il messaggio processato sia una sequenza di blocchi da 512 bit, ciascuno interpretabile come 16 parole da 32 bit.

#### **5.4. Blocchi e Parole dopo il Padding**

Dopo il padding, il messaggio $M'$ può essere scritto come:

$$
M' = B_0 \parallel B_1 \parallel \cdots \parallel B_{L-1}
$$

dove ogni blocco $B_j$ ha $512$ bit.

Ogni blocco è poi diviso in $16$ parole:

$$
B_j = (X_j[0], X_j[1], \ldots, X_j[15])
$$

dove ogni $X_j[k]$ è una parola da $32$ bit.

In alternativa, si può vedere l'intero messaggio come una sequenza di $N$ parole da $32$ bit:

$$
N = 16L
$$

quindi $N$ è multiplo di $16$.

---

### **6. Operazioni Fondamentali su Word**

MD4 e MD5 lavorano su **word** da $32$ bit.

Una word è un valore:

$$
X \in \{0,1\}^{32}
$$

Le operazioni fondamentali prendono una o più word in input e producono ancora una word da $32$ bit.

#### **6.1. AND Bit a Bit**

L'operazione AND:

$$
X \land Y
$$

produce una word in cui ogni bit vale $1$ solo se i bit corrispondenti di $X$ e $Y$ valgono entrambi $1$.

#### **6.2. OR Bit a Bit**

L'operazione OR:

$$
X \lor Y
$$

produce una word in cui ogni bit vale $1$ se almeno uno dei bit corrispondenti vale $1$.

#### **6.3. XOR Bit a Bit**

L'operazione XOR:

$$
X \oplus Y
$$

produce una word in cui ogni bit vale $1$ se i bit corrispondenti sono diversi.

#### **6.4. Complemento Bit a Bit**

L'operazione NOT:

$$
\neg X
$$

inverte tutti i bit di $X$.

#### **6.5. Somma Modulo $2^{32}$**

La somma tra word è eseguita modulo:

$$
2^{32}
$$

Quindi:

$$
X + Y \pmod{2^{32}}
$$

produce ancora una word da $32$ bit.

Se il risultato supera $2^{32}-1$, i bit in eccesso vengono scartati.

#### **6.6. Rotazione Ciclica a Sinistra**

La rotazione ciclica a sinistra di $s$ posizioni viene indicata come:

$$
X \lll s
$$

I bit che escono a sinistra rientrano a destra.

Non è uno shift normale con inserimento di zeri.

È una rotazione.

> ⚠️ Rotazione e shift non sono la stessa operazione: nella rotazione i bit non vengono persi, ma reinseriti dall'altro lato.

#### **6.7. Perché Queste Operazioni Sono Efficienti**

Tutte queste operazioni sono estremamente semplici per una CPU.

Sono:

- locali sui bit;
- veloci;
- implementabili con poche istruzioni;
- adatte a registri da $32$ bit.

Per questo MD4 e MD5 erano molto efficienti nelle implementazioni software.

---

### **7. Funzioni Logiche dei Round**

MD5 usa quattro round.

In ciascun round viene usata una funzione logica diversa.

Le funzioni prendono in input tre word da $32$ bit:

$$
X, Y, Z
$$

e producono una word da $32$ bit.

Queste funzioni si applicano bit a bit.

Ciò significa che la funzione su word da $32$ bit può essere vista come la trasposizione su $32$ posizioni di una funzione booleana su tre bit.

Per ogni posizione $j$, si considera il terzetto:

$$
X_j, Y_j, Z_j
$$

e si produce il bit di output corrispondente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.1. Funzione F**

La funzione $F$ è:

$$
F(X,Y,Z) = (X \land Y) \lor (\neg X \land Z)
$$

Questa funzione può essere letta come una funzione condizionale:

> se $X$ allora $Y$, altrimenti $Z$.

Infatti, bit per bit:

- se il bit di $X$ vale $1$, il risultato prende il bit di $Y$;
- se il bit di $X$ vale $0$, il risultato prende il bit di $Z$.

Per questo è spesso detta funzione **choose**.

#### **7.2. Funzione G in MD5**

In MD5, la funzione $G$ è:

$$
G(X,Y,Z) = (X \land Z) \lor (Y \land \neg Z)
$$

È una funzione non lineare bit a bit, usata nel secondo round.

È diversa dalla funzione $G$ di MD4.

#### **7.3. Funzione G in MD4**

In MD4, la funzione $G$ è invece:

$$
G_{\text{MD4}}(X,Y,Z) = (X \land Y) \lor (X \land Z) \lor (Y \land Z)
$$

Questa funzione vale $1$ quando almeno due tra i tre bit di input valgono $1$.

Per questo è detta funzione **majority**.

> ⚠️ Quando si confrontano MD4 e MD5 bisogna fare attenzione: la lettera $G$ non identifica esattamente la stessa formula nei due algoritmi.

#### **7.4. Funzione H**

La funzione $H$ è:

$$
H(X,Y,Z) = X \oplus Y \oplus Z
$$

Questa funzione calcola la parità bit a bit.

Il transcript la descrive come legata al bit di parità: il risultato è $1$ quando il numero di bit a $1$ tra $X_j$, $Y_j$, $Z_j$ è dispari.

#### **7.5. Funzione I**

La funzione $I$, usata nel quarto round di MD5, è:

$$
I(X,Y,Z) = Y \oplus (X \lor \neg Z)
$$

MD4 non ha questo quarto round.

Questa è una delle differenze principali con MD5.

---

### **8. Funzione di Compressione di MD5**

La funzione di compressione di MD5 opera su due input:

- il blocco corrente da $512$ bit;
- il valore corrente dell'hash, rappresentato da quattro word da $32$ bit.

Il blocco corrente viene scritto come:

$$
X[0], X[1], \ldots, X[15]
$$

dove ciascun $X[k]$ è una word da $32$ bit.

Il valore corrente dell'hash è rappresentato dal buffer:

$$
(A,B,C,D)
$$

dove:

$$
A,B,C,D \in \{0,1\}^{32}
$$

Il buffer complessivo ha quindi:

$$
4 \cdot 32 = 128
$$

bit.

> 📌 Il blocco di messaggio fornisce 512 bit di input; lo stato interno corrente fornisce 128 bit; la compressione aggiorna lo stato a un nuovo valore da 128 bit.

#### **8.1. Quattro Round**

MD5 è composto da quattro round.

Ogni round contiene:

$$
16
$$

operazioni elementari.

Il totale è quindi:

$$
4 \cdot 16 = 64
$$

operazioni.

Ogni round usa una funzione logica diversa:

- round 1: $F$;
- round 2: $G$;
- round 3: $H$;
- round 4: $I$.

#### **8.2. Notazione delle Operazioni**

Nel transcript le operazioni sono indicate con una notazione del tipo:

$$
[ABCD \; k \; s \; i]
$$

Questa notazione significa che l'operazione:

- lavora sul buffer ordinato come $(A,B,C,D)$;
- usa la parola $X[k]$ del blocco corrente;
- usa una rotazione ciclica di $s$ bit;
- usa la costante additiva $T[i]$;
- usa la funzione logica del round corrente.

La formula generale dell'aggiornamento in MD5 è:

$$
A \leftarrow B + \left((A + v(B,C,D) + X[k] + T[i]) \lll s\right)
$$

dove:

- $v$ è $F$, $G$, $H$ oppure $I$ a seconda del round;
- tutte le somme sono modulo $2^{32}$;
- $\lll s$ indica rotazione ciclica a sinistra;
- $X[k]$ è la $k$-esima word del blocco;
- $T[i]$ è la $i$-esima costante additiva.

Dopo ogni operazione, i ruoli dei registri vengono permutati nelle istruzioni successive.

Per questo nelle descrizioni dell'algoritmo si trovano sequenze del tipo:

$$
[ABCD \; k \; s \; i]
$$

poi:

$$
[DABC \; k' \; s' \; i']
$$

poi:

$$
[CDAB \; k'' \; s'' \; i'']
$$

e così via.

Questa rotazione dei ruoli fa sì che tutte le word del buffer vengano aggiornate ripetutamente.

> 💡 L'operazione elementare non aggiorna "genericamente il digest": aggiorna una word del buffer usando le altre tre, una word del messaggio, una costante e una rotazione.

---

### **9. Costanti Additive $T[i]$**

MD5 usa $64$ costanti additive.

Queste costanti sono indicate con:

$$
T[1], T[2], \ldots, T[64]
$$

Il transcript sottolinea che la tabella $T$ viene costruita usando la funzione seno.

La definizione è:

$$
T[i] = \left\lfloor 2^{32} \cdot |\sin(i)| \right\rfloor
$$

per:

$$
i = 1,2,\ldots,64
$$

dove $i$ è espresso in radianti.

Poiché:

$$
0 \le |\sin(i)| \le 1
$$

si ha:

$$
0 \le 2^{32}|\sin(i)| \le 2^{32}
$$

La parte intera produce un valore rappresentabile su $32$ bit.

#### **9.1. Perché Usare il Seno**

L'uso della funzione seno non serve per ragioni matematiche profonde legate alla trigonometria.

Serve a generare costanti apparentemente irregolari, ma definite in modo pubblico e riproducibile.

Questo evita l'impressione che le costanti siano state scelte arbitrariamente per introdurre una qualche proprietà nascosta.

> 📌 Le costanti $T[i]$ sono deterministicamente generate: non sono una tabella segreta.

---

### **10. Inizializzazione del Buffer**

MD5 mantiene un buffer di quattro word:

$$
(A,B,C,D)
$$

All'inizio dell'algoritmo, queste word vengono inizializzate con costanti fissate.

I valori numerici standard sono:

$$
\begin{cases}
A = \texttt{0x67452301} \\
B = \texttt{0xefcdab89} \\
C = \texttt{0x98badcfe} \\
D = \texttt{0x10325476}
\end{cases}
$$

Questi valori sono spesso fonte di confusione perché MD5 è little-endian.

In memoria, i byte del valore:

$$
\texttt{0x67452301}
$$

sono memorizzati nell'ordine:

$$
\texttt{01 23 45 67}
$$

Quindi alcune slide o descrizioni possono mostrare la sequenza dei byte, mentre la costante numerica viene scritta nel formato esadecimale usuale.

> ⚠️ Numericamente $A$ è $\texttt{0x67452301}$, non $\texttt{0x01234567}$. Quest'ultima è la sequenza che si ottiene se si confonde valore numerico e ordine dei byte in memoria.

---

### **11. Elaborazione di un Blocco**

Per ogni blocco da $512$ bit, MD5 esegue la stessa procedura.

Sia il blocco corrente:

$$
B_j = (X[0], X[1], \ldots, X[15])
$$

e sia lo stato corrente:

$$
(A,B,C,D)
$$

#### **11.1. Salvataggio dello Stato Corrente**

Prima di eseguire i round, l'algoritmo salva il valore corrente del buffer in variabili temporanee:

$$
\begin{cases}
AA = A \\
BB = B \\
CC = C \\
DD = D
\end{cases}
$$

Questo serve perché, alla fine dei quattro round, il nuovo valore calcolato verrà sommato allo stato iniziale del blocco.

#### **11.2. Esecuzione dei Quattro Round**

L'algoritmo esegue:

- $16$ operazioni del primo round;
- $16$ operazioni del secondo round;
- $16$ operazioni del terzo round;
- $16$ operazioni del quarto round.

Ogni round:

- usa una funzione logica specifica;
- usa una sequenza di parole $X[k]$;
- usa rotazioni prestabilite;
- usa costanti $T[i]$;
- aggiorna ciclicamente i registri $A$, $B$, $C$, $D$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3. Feed-Forward Finale**

Dopo i quattro round, il valore corrente del buffer viene sommato al valore salvato all'inizio del blocco.

Si calcola:

$$
\begin{cases}
A \leftarrow A + AA \pmod{2^{32}} \\
B \leftarrow B + BB \pmod{2^{32}} \\
C \leftarrow C + CC \pmod{2^{32}} \\
D \leftarrow D + DD \pmod{2^{32}}
\end{cases}
$$

Questo passaggio è detto spesso **feed-forward**.

Il transcript lo descrive come il recupero del valore salvato del registro e la sua somma al valore corrente.

Il risultato è il nuovo stato interno dell'algoritmo.

#### **11.4. Digest Finale**

La procedura viene ripetuta per tutti gli $L$ blocchi del messaggio dopo padding.

Dopo l'ultimo blocco, il contenuto del buffer:

$$
(A,B,C,D)
$$

costituisce il digest finale.

Poiché ogni word ha $32$ bit, il digest ha:

$$
4 \cdot 32 = 128
$$

bit.

---

### **12. Struttura Iterata di MD5**

MD5 è una funzione hash iterata.

Si può vedere come una funzione di compressione:

$$
f : \{0,1\}^{128} \times \{0,1\}^{512} \to \{0,1\}^{128}
$$

che prende:

- uno stato da $128$ bit;
- un blocco di messaggio da $512$ bit;

e produce:

- un nuovo stato da $128$ bit.

Se il messaggio dopo padding è:

$$
B_0, B_1, \ldots, B_{L-1}
$$

allora:

$$
H_0 = IV
$$

e:

$$
H_{j+1} = f(H_j, B_j)
$$

per:

$$
j = 0,1,\ldots,L-1
$$

Il digest è:

$$
H(M) = H_L
$$

> 📌 MD5 applica ripetutamente la stessa funzione di compressione a blocchi da 512 bit, mantenendo uno stato interno da 128 bit.

---

### **13. MD4 rispetto a MD5**

MD5 nasce come evoluzione di MD4.

I due algoritmi condividono l'idea generale:

- messaggi con padding;
- blocchi da $512$ bit;
- word da $32$ bit;
- buffer interno da $128$ bit;
- operazioni bit a bit;
- somme modulo $2^{32}$;
- rotazioni cicliche.

MD5 introduce però modifiche per aumentare la robustezza.

#### **13.1. Numero di Round**

MD4 usa:

$$
3
$$

round.

MD5 usa:

$$
4
$$

round.

Poiché ogni round contiene $16$ operazioni:

- MD4 esegue $3 \cdot 16 = 48$ operazioni;
- MD5 esegue $4 \cdot 16 = 64$ operazioni.

#### **13.2. Funzioni Logiche**

MD4 usa tre funzioni logiche:

- $F$;
- $G_{\text{MD4}}$;
- $H$.

MD5 usa quattro funzioni logiche:

- $F$;
- $G$;
- $H$;
- $I$.

Il quarto round di MD5 è uno dei rafforzamenti rispetto a MD4.

#### **13.3. Costanti Additive**

MD5 usa $64$ costanti additive $T[i]$.

Questo aumenta la differenziazione tra le singole operazioni.

MD4, invece, ha una struttura più semplice e meno rinforzata.

#### **13.4. Somma del Risultato del Passo Precedente**

Nel transcript viene evidenziato che in MD5, in ogni passo, viene sommato il risultato del passo precedente.

Questo si vede nella formula:

$$
A \leftarrow B + \left((A + v(B,C,D) + X[k] + T[i]) \lll s\right)
$$

Il termine esterno $B$ collega esplicitamente il nuovo valore al registro aggiornato nel passo precedente.

Questa modifica contribuisce alla diffusione interna di MD5.

---

### **14. Effetto Valanga**

Una proprietà desiderabile per una funzione hash è l'**effetto valanga**.

L'idea è:

> una piccola variazione nell'input deve produrre una grande variazione apparentemente imprevedibile nell'output.

Nel transcript viene detto che in MD5 ogni bit del codice hash è funzione di ogni bit dell'input.

Questo significa che, dopo i round dell'algoritmo, l'influenza di ciascun bit del messaggio si propaga attraverso:

- funzioni booleane;
- somme modulo $2^{32}$;
- rotazioni;
- aggiornamenti successivi del buffer;
- feed-forward finale.

Di conseguenza, modificare un solo bit del messaggio dovrebbe cambiare molti bit del digest.

> 📌 L'effetto valanga è essenziale perché impedisce che differenze piccole e controllate nell'input producano differenze semplici e prevedibili nell'output.

#### **14.1. Collisioni Casuali e Collisioni Costruite**

Il transcript osserva che è molto improbabile che due messaggi scelti casualmente producano lo stesso digest.

Questo è vero per lo spazio dei digest da $128$ bit.

Se si scelgono due messaggi a caso, la probabilità che abbiano lo stesso digest è circa:

$$
2^{-128}
$$

Tuttavia, la sicurezza crittografica non riguarda solo collisioni casuali.

Riguarda la capacità di un attaccante di costruire intenzionalmente collisioni sfruttando la struttura dell'algoritmo.

Ed è proprio su questo punto che MD4 e MD5 sono stati compromessi.

> ⚠️ Un buon effetto valanga empirico non basta a garantire resistenza crittografica alle collisioni.

---

### **15. Sicurezza Storica di MD4 e MD5**

MD4 e MD5 sono stati molto usati.

MD5, in particolare, è rimasto presente per anni in molte applicazioni, protocolli e sistemi legacy.

Questo è dovuto a:

- semplicità;
- velocità;
- digest compatto;
- disponibilità di implementazioni;
- ampia standardizzazione storica.

Tuttavia, entrambi gli algoritmi sono oggi considerati insicuri per applicazioni crittografiche che richiedono resistenza alle collisioni.

#### **15.1. Attacchi su Versioni Ridotte**

Il transcript cita attacchi storici su versioni ridotte dell'algoritmo.

Una tecnica comune in crittanalisi è studiare versioni semplificate:

- con meno round;
- con un round omesso;
- con una parte dell'algoritmo isolata.

Questo serve a capire quanto margine di sicurezza abbia la costruzione completa.

Se una versione con pochi round in meno è facile da attaccare, significa che l'algoritmo ha margine ridotto.

#### **15.2. Merkle e MD4 Ridotto**

Il transcript richiama un risultato attribuito a Merkle.

L'idea è che per versioni ridotte di MD4, concentrandosi sui primi round o omettendo una parte della struttura, è possibile trovare collisioni più facilmente.

Questo mostrava già che MD4 era molto veloce ma anche fragile.

#### **15.3. Den Boer e Bosselaers**

Il transcript cita anche Den Boer e Bosselaers.

Questi risultati storici mostrarono debolezze su varianti o versioni ridotte legate a MD5, per esempio omettendo un round.

Il punto didattico è:

> gli attacchi ridotti non rompono sempre immediatamente l'algoritmo completo, ma indicano che la struttura ha vulnerabilità sfruttabili.

#### **15.4. Dobbertin**

Il transcript indica come attacco più grave quello di Dobbertin.

Storicamente, Dobbertin mostrò debolezze molto significative nella funzione di compressione di MD5 e nella famiglia MD.

Nel flusso della lezione, il risultato viene presentato così:

- la tecnica consente di generare collisioni per MD5 in un caso limitato;
- in particolare quando si considera una struttura con un solo blocco di input o una componente della compressione;
- inizialmente non si aveva una generalizzazione immediata a messaggi di lunghezza arbitraria.

Questo è importante perché distingue:

- collisioni nella funzione di compressione;
- collisioni per un singolo blocco;
- collisioni per l'intera funzione hash su messaggi arbitrari.

#### **15.5. Attacchi Successivi e Collisioni Pratiche**

Il transcript accenna poi ad attacchi più recenti che permettono di trovare collisioni con sforzo contenuto.

Il riferimento storico più noto è la linea di attacchi iniziata nei primi anni 2000, con collisioni pratiche per MD5.

Da quel momento MD5 non è più considerato adeguato per applicazioni che richiedono resistenza alle collisioni.

> ⚠️ MD5 non deve essere usato per firme digitali, certificati, integrità autenticata o contesti in cui un attaccante può scegliere messaggi e cercare collisioni.

---

### **16. Attacco del Compleanno su MD5**

MD5 produce digest di:

$$
128 \text{ bit}
$$

Per una funzione hash ideale con digest da $n$ bit, un attacco generico di tipo compleanno richiede circa:

$$
2^{n/2}
$$

tentativi per trovare una collisione.

Nel caso di MD5:

$$
n = 128
$$

quindi:

$$
2^{n/2} = 2^{64}
$$

Questo significa che anche senza sfruttare debolezze specifiche dell'algoritmo, il limite teorico generico per collisioni è dell'ordine di $2^{64}$.

Il transcript sottolinea che questa soglia non è più considerata impossibile.

Inoltre, gli attacchi specifici a MD5 sono più efficienti del birthday attack generico.

> 📌 Il digest da 128 bit è oggi troppo corto per garantire un margine adeguato contro collisioni in contesti crittografici moderni.

---

### **17. Perché MD5 è Vulnerabile Oggi**

MD5 è vulnerabile per due motivi distinti.

#### **17.1. Digest Troppo Corto**

Un digest di $128$ bit implica una soglia birthday di $2^{64}$.

Questa soglia, pur grande, non è più considerata sufficientemente conservativa per sicurezza a lungo termine.

#### **17.2. Debolezze Strutturali**

MD5 non si comporta come una funzione hash ideale.

La sua struttura interna è stata crittanalizzata e sono stati trovati metodi per costruire collisioni con costo molto inferiore rispetto a $2^{64}$.

Quindi il problema non è solo la lunghezza del digest.

È anche la struttura dell'algoritmo.

> ⚠️ Dire "MD5 ha 128 bit" non basta: la sicurezza effettiva è inferiore a quella che ci si aspetterebbe da una funzione hash ideale da 128 bit.

---

### **18. Quando MD5 Può Ancora Comparire**

MD5 può ancora comparire in applicazioni esistenti o legacy.

Esempi:

- checksum non crittografici;
- deduplicazione;
- identificazione rapida di file in contesti non avversariali;
- vecchi database;
- vecchi protocolli;
- sistemi non aggiornati.

Bisogna però distinguere tra:

- uso come checksum accidentale;
- uso come funzione hash crittografica.

Nel primo caso, MD5 può talvolta essere ancora trovato per compatibilità.

Nel secondo caso, non è più raccomandato.

> ⚠️ Se un attaccante può scegliere o modificare l'input, MD5 non deve essere considerato sicuro.

---

### **19. Sostituzione con Algoritmi più Sicuri**

La conclusione del transcript è che MD5 deve essere sostituito da algoritmi più sicuri.

In particolare, la lezione anticipa gli algoritmi della famiglia **SHA**, che verranno trattati nelle lezioni successive.

Il motivo è che le funzioni SHA moderne offrono:

- digest più lunghi;
- maggiore margine di sicurezza;
- migliore resistenza agli attacchi noti;
- standardizzazione più recente;
- uso ancora accettato in molte applicazioni moderne, a seconda della variante.

> ✅ MD5 è importante da studiare perché mostra la struttura delle funzioni hash storiche e il percorso della crittanalisi, ma non è più una scelta sicura per nuovi sistemi.

---

### **20. Collegamento con la Lezione Precedente**

La lezione precedente ha introdotto le costruzioni generali di funzioni hash.

MD4 e MD5 sono esempi concreti di costruzione iterata.

Infatti:

- il messaggio viene trasformato tramite padding;
- viene diviso in blocchi da $512$ bit;
- ogni blocco aggiorna uno stato da $128$ bit;
- lo stato finale è il digest.

In termini astratti:

$$
H_{j+1} = f(H_j, B_j)
$$

MD5 specifica in dettaglio la funzione $f$ usando:

- quattro registri $A,B,C,D$;
- quattro round;
- funzioni logiche $F,G,H,I$;
- parole $X[k]$ del blocco;
- costanti $T[i]$;
- rotazioni cicliche;
- somme modulo $2^{32}$.

Questo rende MD5 un caso concreto del modello seriale/iterato visto nella lezione sulla costruzione delle funzioni hash.

---

### **21. Errori Concettuali da Evitare**

#### **21.1. Pensare che MD5 Sia Sicuro perché Produce 128 Bit**

La lunghezza del digest è solo un parametro.

MD5 ha anche debolezze strutturali.

#### **21.2. Confondere Collisione Casuale e Collisione Costruita**

Due messaggi casuali difficilmente collidono.

Ma un attaccante non sceglie messaggi casuali: cerca messaggi costruiti apposta.

#### **21.3. Confondere Shift e Rotazione**

MD5 usa rotazioni cicliche.

I bit non vengono persi: rientrano dall'altro lato.

#### **21.4. Confondere Valore Esadecimale e Ordine dei Byte**

MD5 è little-endian.

Le costanti numeriche devono essere distinte dal modo in cui i loro byte sono memorizzati.

#### **21.5. Pensare che MD5 Sia Basato su un Problema Matematico Difficile**

MD5 non si basa su fattorizzazione o logaritmo discreto.

La sua sicurezza dipende dalla struttura interna dell'algoritmo.

---

### **22. Conclusione**

MD4 e MD5 sono funzioni hash storiche progettate da Ron Rivest.

Sono nate per essere:

- veloci;
- semplici;
- compatte;
- efficienti su architetture a $32$ bit little-endian;
- capaci di processare messaggi lunghi fino a $2^{64}$ bit;
- capaci di produrre digest da $128$ bit.

Il messaggio viene sottoposto a padding, diviso in blocchi da $512$ bit e processato tramite una funzione di compressione iterata.

MD5 rafforza MD4 aggiungendo:

- un quarto round;
- una quarta funzione logica;
- $64$ costanti additive;
- una struttura interna più mescolata.

Tuttavia, MD5 è stato oggetto di numerosi attacchi.

Il digest da $128$ bit espone già a un birthday attack dell'ordine di $2^{64}$ operazioni, e la crittanalisi ha mostrato collisioni pratiche con sforzo inferiore rispetto a quello atteso da una funzione ideale.

Per questo MD5 è oggi considerato vulnerabile e va sostituito con algoritmi più sicuri, come quelli della famiglia SHA.

---

### **23. Recap per l'Esame**

> ✅ MD significa Message Digest; MD4 e MD5 sono stati progettati da Ron Rivest.

> ✅ MD4 è del 1990, MD5 è del 1991 ed è un'evoluzione più robusta di MD4.

> ✅ Entrambi elaborano messaggi di lunghezza inferiore a $2^{64}$ bit e producono digest da $128$ bit.

> ✅ MD4 e MD5 sono ottimizzati per architetture a $32$ bit little-endian.

> ✅ Il padding aggiunge un bit `1`, zeri fino a $448$ modulo $512$, e poi la lunghezza originale su $64$ bit.

> ✅ Ogni blocco è di $512$ bit, cioè $16$ parole da $32$ bit.

> ✅ Le operazioni base sono AND, OR, XOR, NOT, somma modulo $2^{32}$ e rotazione ciclica a sinistra.

> ✅ MD5 usa quattro round da $16$ operazioni ciascuno, per un totale di $64$ operazioni.

> ✅ Le funzioni logiche di MD5 sono $F$, $G$, $H$ e $I$.

> ✅ Ogni operazione usa una parola $X[k]$, una costante $T[i]$, una rotazione $s$ e la funzione logica del round.

> ✅ Le costanti $T[i]$ sono definite come $\lfloor 2^{32}|\sin(i)| \rfloor$.

> ✅ Il buffer MD5 contiene quattro word $A,B,C,D$, inizializzate con costanti fissate.

> ✅ Dopo ogni blocco, il valore calcolato viene sommato modulo $2^{32}$ al valore del buffer salvato all'inizio del blocco.

> ✅ MD5 ha un forte effetto valanga, ma questo non basta a renderlo sicuro contro collisioni costruite.

> ✅ Il birthday attack generico su digest da $128$ bit richiede circa $2^{64}$ tentativi.

> ⚠️ MD5 è oggi considerato vulnerabile e non deve essere usato in nuovi sistemi crittografici.
