## **Lezione 3: SHA-1**

### **1. Introduzione**

In questa lezione viene introdotto **SHA-1**, una funzione hash storica progettata come evoluzione della famiglia MD e, per molto tempo, considerata un sostituto più sicuro di MD5.

La sigla **SHA** significa:

> **Secure Hash Algorithm**

mentre **SHS** significa:

> **Secure Hash Standard**

Lo **SHS** è lo standard che specifica l'algoritmo hash sicuro.

La prima versione dello standard fu pubblicata dal **NIST** nel 1993.

L'algoritmo originario è spesso indicato come **SHA** o **SHA-0**.

Successivamente venne introdotta una versione modificata, chiamata **SHA-1**, in cui fu aggiunta una rotazione nell'espansione dei blocchi.

> 📌 SHA-1 è molto simile per filosofia e struttura alla famiglia MD4/MD5, ma produce un digest più lungo e ha una funzione di compressione più articolata.

---

### **2. SHA, SHS, SHA-0 e SHA-1**

È utile distinguere bene i nomi.

#### **2.1. SHS**

**SHS** sta per **Secure Hash Standard**.

È il nome dello standard.

Lo standard definisce quale algoritmo usare e come applicarlo.

#### **2.2. SHA**

**SHA** sta per **Secure Hash Algorithm**.

Nel contesto storico iniziale, SHA indicava l'algoritmo contenuto nello standard del 1993.

Questa prima versione viene oggi spesso chiamata **SHA-0** per distinguerla da SHA-1.

#### **2.3. SHA-1**

**SHA-1** è la versione modificata di SHA.

La differenza principale richiamata nel transcript è:

> nell'espansione del blocco viene aggiunta una rotazione circolare a sinistra di un bit.

Questa modifica sembra piccola, ma è crittograficamente rilevante perché aumenta la diffusione delle differenze tra le word espanse.

> ⚠️ Nel transcript compaiono diversi refusi fonetici come "SH", "SH1" o "NIT": i nomi corretti sono SHA, SHA-1 e NIST.

---

### **3. Caratteristiche Generali di SHA-1**

SHA-1 opera su messaggi di lunghezza arbitraria, con la stessa limitazione generale vista per MD4 e MD5:

$$
|M| < 2^{64} \text{ bit}
$$

Il limite deriva dal fatto che la lunghezza del messaggio viene codificata su $64$ bit nella fase di padding.

SHA-1 produce un digest di:

$$
160 \text{ bit}
$$

Quindi:

$$
H(M) \in \{0,1\}^{160}
$$

Questo è un aumento rispetto a MD4 e MD5, che producono digest da $128$ bit.

#### **3.1. Conseguenza sulla Sicurezza Generica**

Per una funzione hash ideale con digest da $n$ bit, un attacco generico del compleanno richiede circa:

$$
2^{n/2}
$$

operazioni.

Per MD5:

$$
n = 128
$$

quindi la soglia birthday è:

$$
2^{64}
$$

Per SHA-1:

$$
n = 160
$$

quindi la soglia birthday è:

$$
2^{80}
$$

> 📌 Il digest più lungo rende SHA-1 più resistente di MD5 agli attacchi generici di tipo compleanno.

#### **3.2. Similarità con MD4 e MD5**

SHA-1 è simile alla famiglia MD per molti aspetti:

- usa padding;
- processa blocchi da $512$ bit;
- usa parole da $32$ bit;
- mantiene un buffer interno;
- usa funzioni logiche bit a bit;
- usa somme modulo $2^{32}$;
- usa rotazioni cicliche;
- applica una funzione di compressione iterata.

La differenza principale è che SHA-1:

- produce $160$ bit invece di $128$;
- usa cinque registri invece di quattro;
- esegue $80$ operazioni invece delle $64$ di MD5;
- espande il blocco di messaggio in $80$ word.

---

### **4. Padding del Messaggio**

La prima fase applicata da SHA-1 è il **padding**.

Il padding è sostanzialmente analogo a quello di MD4 e MD5.

Dato un messaggio originale $M$ di lunghezza:

$$
|M| = b
$$

bit, SHA-1 costruisce un messaggio $M'$ la cui lunghezza è multipla di $512$ bit.

#### **4.1. Procedura di Padding**

La procedura è:

1. si aggiunge un bit `1`;
2. si aggiungono zeri;
3. si arriva a una lunghezza congrua a $448$ modulo $512$;
4. si aggiungono $64$ bit contenenti la lunghezza originale $b$.

In formula:

$$
M' = M \parallel 1 \parallel 0^p \parallel \operatorname{len}_{64}(M)
$$

dove:

- $\parallel$ indica concatenazione;
- $0^p$ è una sequenza di $p$ zeri;
- $\operatorname{len}_{64}(M)$ è la lunghezza originale del messaggio codificata su $64$ bit.

Il valore $p$ viene scelto in modo che:

$$
b + 1 + p \equiv 448 \pmod{512}
$$

Dopo aver aggiunto i $64$ bit finali:

$$
b + 1 + p + 64 \equiv 0 \pmod{512}
$$

Quindi $M'$ ha lunghezza multipla di $512$.

#### **4.2. Blocchi da 512 bit e Word da 32 bit**

Dopo il padding, il messaggio viene diviso in blocchi:

$$
M' = B_0 \parallel B_1 \parallel \cdots \parallel B_{L-1}
$$

Ogni blocco ha:

$$
512 \text{ bit}
$$

ed è composto da:

$$
16
$$

word da:

$$
32 \text{ bit}
$$

Infatti:

$$
16 \cdot 32 = 512
$$

Quindi ogni blocco può essere scritto come:

$$
B_j = (W_0, W_1, \ldots, W_{15})
$$

prima dell'espansione.

> 📌 Come MD5, SHA-1 trasforma il messaggio in blocchi da 512 bit; diversamente da MD5, SHA-1 usa convenzioni big-endian nella rappresentazione delle word.

---

### **5. Espansione del Blocco**

Il cuore di SHA-1 usa una tabella di $80$ word da $32$ bit.

Questa tabella viene indicata come:

$$
W[0], W[1], \ldots, W[79]
$$

Il blocco di input fornisce direttamente solo $16$ word.

Le altre $64$ word vengono derivate dalle prime.

#### **5.1. Prime 16 Word**

Per:

$$
0 \le t \le 15
$$

si pone:

$$
W[t] = \text{la } t\text{-esima word del blocco corrente}
$$

Quindi le prime $16$ word sono copiate direttamente dal blocco di $512$ bit.

#### **5.2. Word da 16 a 79**

Per:

$$
16 \le t \le 79
$$

SHA-1 calcola:

$$
W[t] = (W[t-3] \oplus W[t-8] \oplus W[t-14] \oplus W[t-16]) \lll 1
$$

dove:

- $\oplus$ è XOR bit a bit;
- $\lll 1$ è una rotazione circolare a sinistra di $1$ bit.

Il transcript descrive proprio questo passaggio:

> la word $W[t]$ è ottenuta come shift circolare a sinistra di un bit dello XOR tra quattro valori precedenti della tabella.

#### **5.3. Differenza con SHA-0**

La differenza principale tra SHA-0 e SHA-1 sta proprio qui.

In SHA-0 mancava la rotazione:

$$
W[t] = W[t-3] \oplus W[t-8] \oplus W[t-14] \oplus W[t-16]
$$

In SHA-1 viene aggiunto:

$$
\lll 1
$$

Questa rotazione migliora la propagazione delle differenze nelle word espanse.

> 📌 Le 16 word del blocco vengono espanse in 80 word, che verranno poi usate nelle 80 operazioni della funzione di compressione.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Funzioni Primitive di SHA-1**

SHA-1 usa quattro funzioni primitive, applicate in intervalli diversi degli $80$ round.

Ogni funzione prende in input tre word da $32$ bit:

$$
B, C, D
$$

e produce una word da $32$ bit.

Le funzioni sono definite bit a bit usando operazioni logiche:

- AND;
- OR;
- NOT;
- XOR.

Nel transcript si dice che ciascuna funzione corrisponde a una funzione binaria rappresentabile con una tabella di verità.

Il punto è che ogni bit dell'output dipende dai bit corrispondenti dei tre input.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.1. Round 0-19: Funzione Choose**

Per:

$$
0 \le t \le 19
$$

si usa:

$$
f_t(B,C,D) = (B \land C) \lor (\neg B \land D)
$$

Questa è una funzione condizionale:

> se $B$ allora $C$, altrimenti $D$.

Bit per bit:

- se il bit di $B$ vale $1$, viene scelto il bit di $C$;
- se il bit di $B$ vale $0$, viene scelto il bit di $D$.

È analoga alla funzione $F$ vista in MD5.

#### **6.2. Round 20-39: Parità**

Per:

$$
20 \le t \le 39
$$

si usa:

$$
f_t(B,C,D) = B \oplus C \oplus D
$$

Questa funzione calcola la parità bit a bit.

Il bit di output vale $1$ se il numero di bit a $1$ tra i tre input è dispari.

#### **6.3. Round 40-59: Majority**

Per:

$$
40 \le t \le 59
$$

si usa:

$$
f_t(B,C,D) = (B \land C) \lor (B \land D) \lor (C \land D)
$$

Questa è la funzione **majority**.

Il bit di output vale $1$ se almeno due tra i tre bit di input valgono $1$.

#### **6.4. Round 60-79: Parità**

Per:

$$
60 \le t \le 79
$$

si torna alla funzione di parità:

$$
f_t(B,C,D) = B \oplus C \oplus D
$$

Quindi SHA-1 usa quattro fasi, ma non quattro funzioni tutte distinte:

- choose;
- parity;
- majority;
- parity.

> ⚠️ Nel confronto con MD5, non bisogna mappare meccanicamente le funzioni: SHA-1 usa intervalli di 20 operazioni e una sequenza funzionale propria.

---

### **7. Costanti Additive**

In ogni fase viene usata una costante additiva $K[t]$.

Le costanti sono quattro, una per ciascun intervallo di $20$ round.

#### **7.1. Definizione tramite Radici Quadrate**

Il transcript descrive le costanti come valori ottenuti dalla parte intera di:

$$
2^{30}\sqrt{x}
$$

per quattro valori:

$$
2,\;3,\;5,\;10
$$

Più precisamente, le costanti sono:

$$
K[t] =
\begin{cases}
\texttt{0x5A827999} & 0 \le t \le 19 \\
\texttt{0x6ED9EBA1} & 20 \le t \le 39 \\
\texttt{0x8F1BBCDC} & 40 \le t \le 59 \\
\texttt{0xCA62C1D6} & 60 \le t \le 79
\end{cases}
$$

Questi valori corrispondono rispettivamente a:

$$
\left\lfloor 2^{30}\sqrt{2} \right\rfloor
$$

$$
\left\lfloor 2^{30}\sqrt{3} \right\rfloor
$$

$$
\left\lfloor 2^{30}\sqrt{5} \right\rfloor
$$

$$
\left\lfloor 2^{30}\sqrt{10} \right\rfloor
$$

> 📌 Come in MD5, anche qui le costanti sono pubbliche e determinate in modo riproducibile, non segrete.

#### **7.2. Ruolo delle Costanti**

Le costanti servono a differenziare le fasi.

Senza costanti, l'algoritmo avrebbe una struttura più regolare e potenzialmente più facile da analizzare o sfruttare.

L'aggiunta di valori diversi nei diversi intervalli introduce asimmetria nei round.

---

### **8. Buffer di Stato**

SHA-1 lavora su un buffer composto da cinque word da $32$ bit:

$$
(A,B,C,D,E)
$$

Il buffer totale ha quindi:

$$
5 \cdot 32 = 160
$$

bit.

Questa è anche la lunghezza del digest finale.

#### **8.1. Valori Iniziali**

Il buffer viene inizializzato con costanti fissate:

$$
\begin{cases}
H_0 = \texttt{0x67452301} \\
H_1 = \texttt{0xEFCDAB89} \\
H_2 = \texttt{0x98BADCFE} \\
H_3 = \texttt{0x10325476} \\
H_4 = \texttt{0xC3D2E1F0}
\end{cases}
$$

Nel transcript viene notato che le prime quattro word sono gli stessi valori usati anche nella famiglia MD.

La quinta word è aggiunta perché SHA-1 ha uno stato da $160$ bit invece che da $128$.

> 📌 MD5 usa quattro registri da 32 bit; SHA-1 ne usa cinque.

---

### **9. Funzione di Compressione di SHA-1**

Per ogni blocco da $512$ bit, SHA-1 esegue una funzione di compressione.

L'input della compressione è:

- lo stato corrente da $160$ bit;
- il blocco corrente da $512$ bit.

L'output è un nuovo stato da $160$ bit.

In forma astratta:

$$
f : \{0,1\}^{160} \times \{0,1\}^{512} \to \{0,1\}^{160}
$$

#### **9.1. Espansione del Blocco**

Per prima cosa si costruisce la tabella:

$$
W[0], W[1], \ldots, W[79]
$$

come descritto nella sezione precedente.

#### **9.2. Salvataggio dello Stato Corrente**

Prima delle $80$ operazioni, lo stato corrente viene copiato nei registri di lavoro:

$$
\begin{cases}
A = H_0 \\
B = H_1 \\
C = H_2 \\
D = H_3 \\
E = H_4
\end{cases}
$$

Questi registri verranno aggiornati durante gli $80$ round.

#### **9.3. Operazione Elementare**

Per:

$$
t = 0,1,\ldots,79
$$

si calcola:

$$
TEMP = (A \lll 5) + f_t(B,C,D) + E + W[t] + K[t]
$$

dove tutte le somme sono modulo:

$$
2^{32}
$$

Poi si aggiornano i registri:

$$
\begin{cases}
E \leftarrow D \\
D \leftarrow C \\
C \leftarrow B \lll 30 \\
B \leftarrow A \\
A \leftarrow TEMP
\end{cases}
$$

Il transcript descrive proprio questo passaggio:

- rotazione di $A$ di $5$ posizioni;
- applicazione della funzione del round;
- somma con $E$;
- somma con la word $W[t]$;
- somma con la costante additiva;
- aggiornamento dei registri del buffer.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.4. Feed-Forward Finale**

Dopo le $80$ operazioni, i valori calcolati vengono sommati allo stato che si aveva prima dell'elaborazione del blocco.

Se lo stato precedente era:

$$
(H_0,H_1,H_2,H_3,H_4)
$$

si aggiorna:

$$
\begin{cases}
H_0 \leftarrow H_0 + A \pmod{2^{32}} \\
H_1 \leftarrow H_1 + B \pmod{2^{32}} \\
H_2 \leftarrow H_2 + C \pmod{2^{32}} \\
H_3 \leftarrow H_3 + D \pmod{2^{32}} \\
H_4 \leftarrow H_4 + E \pmod{2^{32}}
\end{cases}
$$

Questo passaggio è analogo al feed-forward visto per MD5.

#### **9.5. Digest Finale**

La funzione di compressione viene applicata a tutti i blocchi.

Dopo l'ultimo blocco, il digest finale è:

$$
H(M) = H_0 \parallel H_1 \parallel H_2 \parallel H_3 \parallel H_4
$$

La lunghezza è:

$$
5 \cdot 32 = 160 \text{ bit}
$$

---

### **10. Struttura Iterata dell'Algoritmo**

Come MD5, anche SHA-1 è una funzione hash iterata.

Dopo il padding, il messaggio viene diviso in blocchi:

$$
B_0,B_1,\ldots,B_{L-1}
$$

Si parte da uno stato iniziale:

$$
H^{(0)}
$$

e si calcola:

$$
H^{(j+1)} = f(H^{(j)}, B_j)
$$

per:

$$
j = 0,1,\ldots,L-1
$$

Alla fine:

$$
H(M) = H^{(L)}
$$

> 📌 SHA-1 è un caso concreto dello schema seriale/iterato studiato nella lezione sulla costruzione delle funzioni hash.

---

### **11. Confronto tra SHA-1 e MD5**

Il transcript propone un confronto tra SHA-1 e MD4/MD5, evidenziando somiglianze e differenze.

#### **11.1. Lunghezza del Digest**

MD5 produce:

$$
128 \text{ bit}
$$

SHA-1 produce:

$$
160 \text{ bit}
$$

La differenza è:

$$
160 - 128 = 32 \text{ bit}
$$

Questi $32$ bit in più aumentano la resistenza agli attacchi generici.

#### **11.2. Birthday Attack**

Per MD5:

$$
2^{128/2} = 2^{64}
$$

Per SHA-1:

$$
2^{160/2} = 2^{80}
$$

Quindi un attacco generico del compleanno richiede circa:

- $2^{64}$ operazioni per MD5;
- $2^{80}$ operazioni per SHA-1.

> 📌 Dal solo punto di vista della lunghezza del digest, SHA-1 ha un margine maggiore contro collisioni generiche.

#### **11.3. Numero di Operazioni**

MD5 usa:

$$
4 \cdot 16 = 64
$$

operazioni.

SHA-1 usa:

$$
4 \cdot 20 = 80
$$

operazioni.

Quindi SHA-1 esegue più passi.

#### **11.4. Dimensione del Buffer**

MD5 usa un buffer da:

$$
4 \cdot 32 = 128 \text{ bit}
$$

SHA-1 usa un buffer da:

$$
5 \cdot 32 = 160 \text{ bit}
$$

Questo è coerente con la lunghezza dell'output.

#### **11.5. Velocità**

Entrambi gli algoritmi usano operazioni primitive semplici:

- AND;
- OR;
- NOT;
- XOR;
- somme modulo $2^{32}$;
- rotazioni.

Per questo sono entrambi efficienti.

Tuttavia SHA-1:

- ha più operazioni;
- ha un buffer più grande;
- espande ogni blocco in $80$ word;
- produce un digest più lungo.

Di conseguenza può essere più lento di MD5.

> 💡 SHA-1 sacrifica parte della velocità di MD5 per ottenere un digest più lungo e un margine di sicurezza storicamente maggiore.

---

### **12. Famiglia SHA-2**

Il transcript ricorda che nel 2002 furono proposti come standard altri algoritmi basati sulla stessa linea evolutiva.

Questi algoritmi producono digest più lunghi:

- $256$ bit;
- $384$ bit;
- $512$ bit.

Sono comunemente associati alla famiglia **SHA-2**.

#### **12.1. SHA-256**

SHA-256 produce un digest da:

$$
256 \text{ bit}
$$

Opera su:

- blocchi da $512$ bit;
- word da $32$ bit.

La resistenza generica al birthday attack è dell'ordine:

$$
2^{128}
$$

#### **12.2. SHA-512**

SHA-512 produce un digest da:

$$
512 \text{ bit}
$$

Opera su:

- blocchi da $1024$ bit;
- word da $64$ bit.

La resistenza generica al birthday attack è dell'ordine:

$$
2^{256}
$$

#### **12.3. SHA-384**

SHA-384 produce un digest da:

$$
384 \text{ bit}
$$

È sostanzialmente collegato a SHA-512:

- usa una struttura basata su blocchi da $1024$ bit;
- usa word da $64$ bit;
- produce il digest prendendo i bit più significativi del risultato di una variante di SHA-512;
- usa costanti iniziali diverse.

Il transcript descrive SHA-384 come versione intermedia ottenuta considerando solo i primi $384$ bit di SHA-512, con una variazione nelle costanti iniziali.

#### **12.4. Tendenza ad Aumentare le Lunghezze**

La tendenza ad aumentare la lunghezza del digest è parallela alla tendenza osservata nei cifrari a blocchi.

Per esempio, AES prevede chiavi da:

- $128$ bit;
- $192$ bit;
- $256$ bit.

Allo stesso modo, le funzioni hash successive a SHA-1 aumentano la lunghezza dell'output per aumentare il margine di sicurezza.

> 📌 Aumentare la lunghezza dell'output aumenta il costo degli attacchi generici di collisione, perché la soglia birthday cresce come $2^{n/2}$.

---

### **13. Altre Funzioni Hash**

MD5 e SHA sono tra le famiglie più diffuse, ma non sono le uniche proposte.

Nel corso della storia sono state progettate molte funzioni hash con proprietà diverse.

Il transcript cita alcune proposte.

#### **13.1. FFT-Hash**

Alcune funzioni, come **FFT-Hash**, furono rotte poco tempo dopo la loro presentazione.

Questo mostra quanto sia difficile progettare funzioni hash sicure.

Una costruzione può apparire convincente inizialmente, ma rivelare debolezze dopo l'analisi della comunità crittografica.

#### **13.2. HAVAL**

**HAVAL** è una funzione hash configurabile, storicamente abbastanza diffusa in alcuni contesti.

È stata studiata anche nella crittanalisi delle collisioni.

#### **13.3. RIPEMD-160**

**RIPEMD-160** è una funzione hash con digest da $160$ bit.

È stata proposta come alternativa a MD5 e SHA-1 in diversi contesti.

Il transcript la cita come funzione sempre più utilizzata al posto di MD5.

---

### **14. Attacchi alle Funzioni Hash**

Il transcript dedica una parte importante agli attacchi.

Vengono citati risultati contro:

- MD4;
- MD5;
- HAVAL-128;
- RIPEMD;
- SHA-0;
- SHA-1.

#### **14.1. Collision Attack**

Gli attacchi citati sono principalmente **collision attack**.

Un collision attack cerca due messaggi diversi:

$$
M \ne M'
$$

tali che:

$$
H(M) = H(M')
$$

L'attaccante non parte da un digest specifico.

Cerca semplicemente una coppia di messaggi che collidono.

#### **14.2. Preimage Attack**

Un preimage attack è diverso.

Dato un digest:

$$
y
$$

l'attaccante deve trovare un messaggio:

$$
M
$$

tale che:

$$
H(M) = y
$$

Questo è in genere un obiettivo più difficile.

Il transcript sottolinea che gli attacchi menzionati trovano collisioni, non preimmagini di un digest dato.

> ⚠️ Rompere la resistenza alle collisioni non equivale automaticamente a rompere la resistenza alla preimmagine, ma è comunque gravissimo per molte applicazioni crittografiche.

#### **14.3. Attacchi Cinesi del 2004-2005**

Il transcript cita un gruppo di studiosi cinesi che annunciò collisioni contro molte funzioni hash.

Il riferimento storico è alla crittanalisi di Xiaoyun Wang e collaboratori.

Furono annunciati attacchi contro funzioni come:

- MD4;
- MD5;
- HAVAL-128;
- RIPEMD;
- SHA-0;
- SHA-1.

Per SHA-1 venne presentato un attacco con complessità inferiore alla soglia birthday generica.

Il transcript riporta uno sforzo dell'ordine:

$$
2^{69}
$$

calcoli per trovare una collisione.

Questo valore va letto nel contesto storico della lezione: è un miglioramento crittanalitico rispetto alla soglia generica $2^{80}$.

#### **14.4. Implicazioni Pratiche**

Il transcript osserva che, al tempo della lezione, questi attacchi avevano implicazioni pratiche limitate.

Le ragioni indicate sono:

- lo sforzo richiesto era ancora elevato;
- molti protocolli usavano le funzioni hash in modi che non esponevano direttamente a collision attacks semplici;
- in protocolli come SSL/TLS, l'hash veniva spesso combinato con altri meccanismi.

Questa osservazione va però interpretata con attenzione.

Oggi SHA-1 è considerato deprecato per usi crittografici sensibili, soprattutto dove la resistenza alle collisioni è necessaria.

> ⚠️ Una collisione può non implicare immediatamente una preimmagine, ma può compromettere firme digitali, certificati, sistemi di integrità e protocolli che trattano il digest come identificatore univoco del messaggio.

---

### **15. Stato di Sicurezza di SHA-1**

SHA-1 fu per molti anni considerato più sicuro di MD5.

Le ragioni erano:

- digest più lungo;
- più operazioni;
- buffer più grande;
- espansione del blocco più robusta rispetto a SHA-0;
- assenza iniziale di attacchi pratici comparabili a quelli su MD5.

Tuttavia, la crittanalisi ha progressivamente ridotto il margine di sicurezza.

Oggi SHA-1 non è raccomandato per nuovi sistemi crittografici.

È preferibile usare funzioni più moderne, come:

- SHA-256;
- SHA-384;
- SHA-512;
- SHA-3, quando appropriato.

> 📌 SHA-1 è fondamentale da studiare perché collega MD5 alla famiglia SHA moderna, ma non rappresenta più una scelta sicura per nuove applicazioni.

---

### **16. Collegamento con MD5**

Il confronto tra MD5 e SHA-1 è utile per capire l'evoluzione delle funzioni hash.

MD5 ha:

- digest da $128$ bit;
- quattro registri;
- $64$ operazioni;
- blocchi da $512$ bit;
- word da $32$ bit;
- endianess little-endian.

SHA-1 ha:

- digest da $160$ bit;
- cinque registri;
- $80$ operazioni;
- blocchi da $512$ bit;
- word da $32$ bit;
- endianess big-endian;
- espansione del blocco da $16$ a $80$ word.

Entrambi sono:

- iterati;
- basati su funzioni booleane;
- veloci in software;
- storicamente molto diffusi;
- oggi insufficienti per applicazioni crittografiche moderne che richiedono sicurezza contro collisioni.

---

### **17. Errori Concettuali da Evitare**

#### **17.1. Confondere SHA con SHS**

SHS è lo standard.

SHA è l'algoritmo.

SHA-1 è la versione modificata dell'algoritmo originario.

#### **17.2. Pensare che 160 bit Rendano SHA-1 Sempre Sicuro**

Il digest più lungo aumenta la soglia degli attacchi generici.

Ma la sicurezza effettiva dipende anche dalla struttura interna e dagli attacchi specifici.

#### **17.3. Confondere Collisione e Preimmagine**

Un collision attack trova due messaggi con lo stesso digest.

Un preimage attack parte da un digest e cerca un messaggio che lo produce.

Sono proprietà diverse.

#### **17.4. Dimenticare l'Espansione del Blocco**

SHA-1 non usa solo le $16$ word del blocco originale.

Le espande in $80$ word.

Questa espansione è una parte essenziale dell'algoritmo.

#### **17.5. Pensare che SHA-384 Sia un Algoritmo Totalmente Indipendente da SHA-512**

SHA-384 è strettamente legato a SHA-512:

- usa struttura a $64$ bit;
- usa blocchi da $1024$ bit;
- usa costanti iniziali diverse;
- restituisce un digest troncato a $384$ bit.

---

### **18. Conclusione**

SHA-1 è una funzione hash della famiglia SHA, introdotta come evoluzione dello standard SHA originario.

Lavora su messaggi di lunghezza inferiore a $2^{64}$ bit e produce digest da $160$ bit.

Il messaggio viene sottoposto a padding, diviso in blocchi da $512$ bit, e ogni blocco viene espanso da $16$ a $80$ word da $32$ bit.

La funzione di compressione usa:

- cinque registri $A,B,C,D,E$;
- $80$ operazioni;
- quattro intervalli da $20$ operazioni;
- funzioni logiche diverse nei vari intervalli;
- costanti additive derivate da radici quadrate;
- rotazioni cicliche e somme modulo $2^{32}$.

Rispetto a MD5, SHA-1 ha un digest più lungo e richiede un birthday attack generico dell'ordine di $2^{80}$ operazioni invece di $2^{64}$.

Tuttavia, attacchi specifici hanno ridotto il margine di sicurezza di SHA-1.

Per questo, pur essendo storicamente fondamentale, SHA-1 è oggi sostituito da varianti più sicure come SHA-256, SHA-384 e SHA-512.

---

### **19. Recap per l'Esame**

> ✅ SHS significa Secure Hash Standard; SHA significa Secure Hash Algorithm.

> ✅ SHA-1 è una variante modificata di SHA/SHA-0 con una rotazione aggiunta nell'espansione dei blocchi.

> ✅ SHA-1 elabora messaggi di lunghezza inferiore a $2^{64}$ bit e produce digest da $160$ bit.

> ✅ Il padding è analogo a quello di MD4/MD5: bit `1`, zeri fino a $448$ modulo $512$, poi lunghezza originale su $64$ bit.

> ✅ Ogni blocco da $512$ bit contiene inizialmente $16$ word da $32$ bit.

> ✅ SHA-1 espande le $16$ word iniziali in $80$ word usando XOR di quattro word precedenti e rotazione a sinistra di un bit.

> ✅ SHA-1 usa quattro fasi da $20$ operazioni ciascuna, per un totale di $80$ operazioni.

> ✅ Il buffer di SHA-1 contiene cinque word da $32$ bit, per un totale di $160$ bit.

> ✅ Le costanti additive derivano da $\lfloor 2^{30}\sqrt{2} \rfloor$, $\lfloor 2^{30}\sqrt{3} \rfloor$, $\lfloor 2^{30}\sqrt{5} \rfloor$ e $\lfloor 2^{30}\sqrt{10} \rfloor$.

> ✅ Rispetto a MD5, SHA-1 ha output più lungo, più operazioni e maggiore resistenza generica al birthday attack.

> ✅ SHA-256, SHA-384 e SHA-512 aumentano ulteriormente la lunghezza dell'output e il margine di sicurezza.

> ✅ Gli attacchi citati contro SHA-1 sono collision attacks, non preimage attacks.

> ⚠️ SHA-1 è storicamente importante, ma oggi non va scelto per nuovi sistemi che richiedono resistenza crittografica alle collisioni.
