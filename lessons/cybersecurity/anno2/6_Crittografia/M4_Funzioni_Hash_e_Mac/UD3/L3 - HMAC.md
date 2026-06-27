## **Lezione 3: HMAC**

### **1. Obiettivo della lezione**

Nella lezione precedente abbiamo visto che una funzione hash può essere usata per costruire un codice MAC, ma abbiamo anche visto che le costruzioni ingenue:

$$
H(K || M)
$$

e:

$$
H(M || K)
$$

non sono soddisfacenti in generale. Il metodo del **segreto prefisso** è esposto ad attacchi di estensione, mentre il metodo del **segreto suffisso** può essere attaccato tramite collisioni e ragionamenti di tipo birthday.

In questa lezione introduciamo la costruzione che è diventata lo standard per ottenere un MAC a partire da una funzione hash: **HMAC**, cioè **Hash-based Message Authentication Code**.

L'idea fondamentale è usare la funzione hash come componente interno, senza modificarla, ma inserirla in una struttura a due livelli che protegge la chiave e impedisce gli attacchi tipici delle concatenazioni ingenue.

> 📌 HMAC non è semplicemente "hash della chiave e del messaggio". È una costruzione specifica, progettata per trasformare una funzione hash iterata in un MAC sicuro.

---

### **2. Standardizzazione e uso pratico**

L'uso di funzioni hash per generare codici MAC è presente in diversi documenti ufficiali. La costruzione HMAC è descritta, tra gli altri, in:

- **RFC 2104**, dedicata a HMAC come keyed-hashing for message authentication;
- **ANSI X9.71**, relativo ai MAC basati su funzioni hash;
- **FIPS 198**, in cui viene standardizzato lo schema **The Keyed-Hash Message Authentication Code (HMAC)**.

HMAC è stato adottato in molti protocolli reali, tra cui protocolli della famiglia **SSL/TLS**, e più in generale in numerose applicazioni che richiedono autenticazione e integrità dei messaggi.

Il motivo del successo di HMAC è duplice:

- consente di riutilizzare funzioni hash già disponibili e ampiamente implementate;
- fornisce una costruzione standard, analizzata e indipendente dalla specifica funzione hash scelta.

> 💡 Dal punto di vista progettuale, HMAC è comodo perché tratta la funzione hash come una scatola nera: non serve modificare MD5, SHA-1, SHA-256 o RIPEMD-160; basta inserirle nello schema.

---

### **3. Vantaggi delle costruzioni hash-based**

Le costruzioni MAC basate su funzioni hash hanno vari vantaggi pratici.

Primo, le funzioni hash sono generalmente molto efficienti in software. Sono state progettate per elaborare messaggi lunghi rapidamente e con operazioni semplici.

Secondo, le implementazioni di funzioni hash sono disponibili praticamente in ogni libreria crittografica. Questo rende HMAC facile da implementare senza progettare un nuovo algoritmo da zero.

Terzo, la funzione hash usata dentro HMAC è facilmente intercambiabile. Se in un certo momento si ritiene opportuno sostituire SHA-1 con SHA-256, o usare un'altra funzione hash iterata, la struttura di HMAC rimane la stessa.

Quarto, storicamente le funzioni hash non erano soggette alle stesse restrizioni di esportazione che hanno riguardato alcuni algoritmi di cifratura. Questo ne ha favorito l'adozione in protocolli e software distribuiti globalmente.

> ✅ La costruzione HMAC separa lo schema MAC dalla funzione hash concreta: cambiando $H$ si ottiene una nuova istanza, ad esempio HMAC-MD5, HMAC-SHA1 o HMAC-SHA256.

---

### **4. Parametri della costruzione HMAC**

Per descrivere HMAC fissiamo alcuni parametri.

Indichiamo con:

$$
H
$$

una funzione hash iterata, dotata di un vettore di inizializzazione:

$$
IV
$$

La funzione $H$ può essere istanziata con una funzione hash concreta, ad esempio:

- MD5;
- SHA-1;
- SHA-256;
- RIPEMD-160.

Indichiamo con:

$$
n
$$

la lunghezza in bit del digest prodotto da $H$. Per esempio:

- MD5 produce $n = 128$ bit;
- SHA-1 produce $n = 160$ bit;
- SHA-256 produce $n = 256$ bit.

Indichiamo con:

$$
b
$$

la lunghezza in bit del blocco di input della funzione hash. Nelle funzioni MD5 e SHA-1, per esempio:

$$
b = 512
$$

In generale si assume:

$$
b > n
$$

Il messaggio $M$ viene suddiviso in $L$ blocchi da $b$ bit ciascuno, dopo il padding previsto dalla funzione hash:

$$
M = M_1 || M_2 || \dots || M_L
$$

Infine indichiamo con:

$$
K
$$

la chiave segreta condivisa tra mittente e destinatario.

---

### **5. Le costanti ipad e opad**

HMAC usa due stringhe costanti, chiamate:

- `ipad`, inner padding;
- `opad`, outer padding.

Queste due stringhe hanno lunghezza pari a un blocco della funzione hash, quindi $b$ bit.

Sono costruite ripetendo un byte fisso:

$$
ipad = \texttt{0x36} \text{ ripetuto } b/8 \text{ volte}
$$

e:

$$
opad = \texttt{0x5C} \text{ ripetuto } b/8 \text{ volte}
$$

Per esempio, se:

$$
b = 512
$$

allora:

$$
b/8 = 64
$$

quindi `ipad` è formato da 64 byte tutti uguali a `0x36`, mentre `opad` è formato da 64 byte tutti uguali a `0x5C`.

> 📌 `ipad` e `opad` non sono valori segreti. Sono costanti pubbliche, fissate dallo standard. Il segreto rimane solo la chiave $K$.

---

### **6. Normalizzazione della chiave**

Prima di applicare HMAC, la chiave $K$ viene adattata alla dimensione del blocco della funzione hash.

Lo scopo è ottenere una chiave normalizzata:

$$
K_0
$$

di lunghezza esattamente $b$ bit, così da poterla combinare tramite XOR con `ipad` e `opad`.

Ci sono tre casi.

#### **6.1 Chiave più corta di un blocco**

Se:

$$
|K| < b
$$

allora la chiave viene riempita con zeri fino a raggiungere $b$ bit:

$$
K_0 = K || 0\ldots0
$$

Lo zero padding viene aggiunto dopo la chiave, in modo che la stringa finale abbia lunghezza pari a un blocco.

#### **6.2 Chiave lunga esattamente un blocco**

Se:

$$
|K| = b
$$

allora:

$$
K_0 = K
$$

Non serve alcuna trasformazione.

#### **6.3 Chiave più lunga di un blocco**

Se:

$$
|K| > b
$$

allora prima si calcola l'hash della chiave:

$$
H(K)
$$

e poi si riempie il digest con zeri fino a ottenere $b$ bit:

$$
K_0 = H(K) || 0\ldots0
$$

Questa regola permette a HMAC di accettare chiavi di lunghezza arbitraria, pur lavorando internamente con una chiave lunga quanto un blocco della funzione hash.

> ⚠️ La normalizzazione della chiave non serve a rendere pubblica la chiave o a comprimerla per comodità estetica: serve perché le operazioni $K_0 \oplus ipad$ e $K_0 \oplus opad$ siano definite su stringhe della stessa lunghezza.

---

### **7. Formula di HMAC**

Una volta ottenuta la chiave normalizzata $K_0$, si calcolano due valori mascherati:

$$
K_0 \oplus ipad
$$

e:

$$
K_0 \oplus opad
$$

La formula completa di HMAC è:

$$
HMAC_K(M) = H((K_0 \oplus opad) || H((K_0 \oplus ipad) || M))
$$

Questa formula va letta dall'interno verso l'esterno.

Prima si calcola l'hash interno:

$$
H_{\text{inner}} = H((K_0 \oplus ipad) || M)
$$

Poi si calcola l'hash esterno:

$$
HMAC_K(M) = H((K_0 \oplus opad) || H_{\text{inner}})
$$

Quindi HMAC esegue due applicazioni della funzione hash:

- una interna, che lega il messaggio alla chiave mascherata con `ipad`;
- una esterna, che autentica il risultato interno usando la chiave mascherata con `opad`.

---

### **8. Schema operativo di HMAC**

Il docente descrive lo schema operativo di HMAC come una sequenza di trasformazioni sulla chiave e sul messaggio.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il procedimento è il seguente.

1. Si prende la chiave segreta $K$.
2. Se $K$ è più lunga di $b$ bit, si sostituisce $K$ con $H(K)$.
3. Se il risultato è più corto di $b$ bit, si aggiungono zeri fino a ottenere $K_0$ lungo esattamente $b$ bit.
4. Si calcola:

$$
K_0 \oplus ipad
$$

5. Si concatena il messaggio $M$:

$$
(K_0 \oplus ipad) || M
$$

6. Si applica la funzione hash:

$$
H((K_0 \oplus ipad) || M)
$$

7. In parallelo si calcola:

$$
K_0 \oplus opad
$$

8. Si concatena il risultato dell'hash interno:

$$
(K_0 \oplus opad) || H((K_0 \oplus ipad) || M)
$$

9. Si applica nuovamente la funzione hash:

$$
H((K_0 \oplus opad) || H((K_0 \oplus ipad) || M))
$$

Il valore finale è il codice HMAC del messaggio $M$ sotto la chiave $K$.

> ✅ Il messaggio vero e proprio compare solo nell'hash interno. L'hash esterno protegge il digest interno e impedisce che il valore osservato possa essere usato direttamente come stato utile per estendere il messaggio.

---

### **9. Perché due maschere diverse**

Una domanda naturale è perché HMAC non usi semplicemente due volte la stessa chiave.

La risposta è che `ipad` e `opad` separano in modo netto il contesto interno da quello esterno. Anche se la chiave di partenza è la stessa, i due valori effettivamente usati dalla funzione hash sono diversi:

$$
K_0 \oplus ipad
$$

e:

$$
K_0 \oplus opad
$$

Quindi l'hash interno e quello esterno non partono dallo stesso prefisso.

Questa separazione è importante perché evita che proprietà strutturali della funzione hash iterata possano essere sfruttate come nei metodi ingenui:

- nel segreto prefisso, il tag può diventare uno stato da cui proseguire il calcolo;
- nel segreto suffisso, una collisione prima della chiave può propagarsi anche dopo la chiave;
- in HMAC, il digest interno viene reinserito dentro un secondo hash protetto da una maschera diversa della chiave.

> 📌 HMAC costruisce una barriera tra il digest visibile all'attaccante e lo stato interno usato per processare direttamente il messaggio.

---

### **10. Intercambiabilità della funzione hash**

La definizione di HMAC è indipendente dalla specifica funzione hash adottata.

Questo significa che, nello schema:

$$
HMAC_K(M) = H((K_0 \oplus opad) || H((K_0 \oplus ipad) || M))
$$

il simbolo $H$ può essere sostituito da diverse funzioni hash iterate.

Per esempio:

- usando MD5 si ottiene HMAC-MD5;
- usando SHA-1 si ottiene HMAC-SHA1;
- usando SHA-256 si ottiene HMAC-SHA256;
- usando RIPEMD-160 si ottiene HMAC-RIPEMD160.

La struttura di HMAC non cambia. Cambiano soltanto:

- la lunghezza del digest $n$;
- la lunghezza del blocco $b$, se diversa;
- il livello di sicurezza ereditato dalla funzione hash scelta;
- la lunghezza naturale del tag finale.

> 💡 Questa modularità è uno dei motivi per cui HMAC è diventato uno standard: quando una funzione hash diventa obsoleta, si può migrare verso una funzione più robusta mantenendo la stessa struttura MAC.

---

### **11. Precomputazione**

HMAC può essere reso più efficiente quando si devono autenticare molti messaggi con la stessa chiave.

Osserviamo infatti che i valori:

$$
K_0 \oplus ipad
$$

e:

$$
K_0 \oplus opad
$$

dipendono solo dalla chiave, non dal messaggio.

Se la funzione hash è iterata, il primo blocco elaborato nell'hash interno è proprio:

$$
K_0 \oplus ipad
$$

mentre il primo blocco elaborato nell'hash esterno è:

$$
K_0 \oplus opad
$$

Si possono quindi precalcolare gli stati ottenuti comprimendo questi due blocchi a partire dal vettore iniziale $IV$.

Indicando con $F(S,X)$ la funzione di compressione applicata allo stato $S$ e al blocco $X$, si possono calcolare:

$$
IV_{\text{inner}} = F(IV, K_0 \oplus ipad)
$$

e:

$$
IV_{\text{outer}} = F(IV, K_0 \oplus opad)
$$

Questi valori devono essere ricalcolati solo:

- all'inizio;
- oppure quando cambia la chiave $K$.

Per tutti i messaggi successivi autenticati con la stessa chiave, $IV_{\text{inner}}$ può sostituire l'IV iniziale dell'hash interno, mentre $IV_{\text{outer}}$ può sostituire l'IV iniziale dell'hash esterno.

> 📌 La precomputazione non cambia il valore matematico di HMAC. È solo un'ottimizzazione: evita di ripetere compressioni identiche per ogni messaggio quando la chiave è la stessa.

---

### **12. Costo computazionale**

Il costo totale per generare HMAC su un messaggio $M$ è molto vicino al costo di calcolare semplicemente:

$$
H(M)
$$

soprattutto quando $M$ è grande.

Per capire perché, consideriamo un messaggio composto da $L$ blocchi. Calcolare $H(M)$ richiede, a grandi linee, una compressione per ogni blocco del messaggio, più il costo del padding finale.

HMAC richiede:

- un hash interno su $(K_0 \oplus ipad) || M$;
- un hash esterno su $(K_0 \oplus opad) || H_{\text{inner}}$.

Rispetto al solo hash del messaggio, quindi, HMAC aggiunge un overhead costante:

- il blocco della chiave mascherata con `ipad`;
- il blocco della chiave mascherata con `opad`;
- il digest interno, che viene elaborato dall'hash esterno;
- i padding richiesti dalle due applicazioni di $H$.

Quando $M$ è lungo, il costo dominante rimane l'elaborazione dei blocchi del messaggio. Per questo il costo di HMAC è vicino a quello di una normale funzione hash, specialmente con la precomputazione degli stati iniziali dipendenti dalla chiave.

In modo informale, il costo aggiuntivo viene spesso visto come un piccolo numero costante di applicazioni della funzione di compressione, non come un costo proporzionale alla lunghezza del messaggio.

> ✅ HMAC è efficiente perché non cifra il messaggio e non introduce una struttura complessa: usa quasi solo il costo della funzione hash sottostante, più un overhead fisso.

---

### **13. Output troncato**

Molti codici MAC non usano tutto l'output calcolato dalla funzione, ma ne conservano solo una parte.

Se la funzione hash produce $n$ bit, il valore HMAC completo ha lunghezza naturale $n$ bit:

$$
HMAC_K(M) \in \{0,1\}^n
$$

È però possibile usare solo i primi $t$ bit:

$$
HMAC^t_K(M) = \text{first}_t(HMAC_K(M))
$$

Questo si chiama **troncamento** del MAC.

Esempi:

- **HMAC-SHA1-80** usa solo i primi 80 bit dei 160 bit prodotti da SHA-1;
- **HMAC-MD5** usa tipicamente tutti i 128 bit prodotti da MD5.

Il vantaggio del troncamento è che l'attaccante vede meno informazione sull'output completo della funzione. In alcuni protocolli questo è utile anche per ridurre la dimensione dei tag trasmessi.

Lo svantaggio è che il tag verificato dal destinatario è più corto. Se il tag ha lunghezza $t$, un tentativo casuale di indovinare il MAC ha probabilità:

$$
2^{-t}
$$

Per questo $t$ non deve essere scelto troppo piccolo.

Una regola indicata nelle specifiche è mantenere:

$$
t \ge \frac{n}{2}
$$

e comunque non scendere sotto soglie minime di sicurezza, spesso indicate intorno agli 80 bit nei contesti classici.

> ⚠️ Il troncamento è un compromesso: riduce ciò che l'avversario osserva, ma riduce anche lo spazio dei tag che l'avversario deve indovinare.

---

### **14. Da cosa dipende la sicurezza di HMAC**

La sicurezza di HMAC dipende dalle proprietà della funzione hash usata al suo interno, ma in modo più sottile rispetto al semplice uso di:

$$
H(K || M)
$$

o:

$$
H(M || K)
$$

L'idea della dimostrazione di sicurezza è che, se un avversario riuscisse ad attaccare HMAC in modo significativo, allora dovrebbe essere in grado di ottenere almeno uno dei seguenti risultati:

- calcolare o prevedere l'output della funzione di compressione della hash in condizioni in cui il vettore iniziale si comporta come casuale e non è noto all'attaccante;
- trovare collisioni rilevanti per la funzione hash usata internamente, sempre in uno scenario in cui l'IV effettivo è legato alla chiave e quindi non è controllato dall'avversario.

In altre parole, rompere HMAC non equivale semplicemente a trovare una collisione generica della funzione hash standard con IV pubblico. L'avversario deve affrontare una situazione più vincolata, perché gli stati iniziali effettivi dell'hash interno ed esterno dipendono dalla chiave mascherata.

> 📌 Per questo alcune debolezze collision-resistant di una funzione hash non si traducono automaticamente in una rottura pratica di HMAC con quella funzione.

---

### **15. Miglior attacco teorico: birthday attack**

Il miglior attacco generale discusso dal docente contro HMAC si basa sul **paradosso del compleanno**.

Se l'output della funzione hash ha lunghezza $n$ bit, un attacco birthday richiede circa:

$$
2^{n/2}
$$

coppie del tipo:

$$
(M, HMAC_K(M))
$$

tutte generate con la stessa chiave $K$.

Queste coppie sono necessarie perché l'avversario non conosce la chiave e non può calcolare autonomamente HMAC su messaggi arbitrari, a meno di avere accesso a un oracolo MAC. In uno scenario passivo, può solo osservare messaggi autentici e i rispettivi codici MAC.

Per HMAC-MD5:

$$
n = 128
$$

quindi un attacco birthday richiederebbe circa:

$$
2^{64}
$$

coppie messaggio-MAC generate con la stessa chiave.

Per HMAC-SHA1:

$$
n = 160
$$

la soglia sale a:

$$
2^{80}
$$

coppie.

Questi numeri sono enormi non solo dal punto di vista computazionale, ma anche dal punto di vista osservazionale: l'attaccante dovrebbe vedere un volume proibitivo di messaggi autenticati con la stessa chiave.

> ✅ L'attacco birthday non richiede solo calcoli: richiede anche una quantità enorme di coppie reali $(M, HMAC_K(M))$ sotto la stessa chiave. Questa condizione rende l'attacco impraticabile nei normali scenari d'uso.

---

### **16. Il caso HMAC-MD5**

MD5 produce un digest di 128 bit. Per una funzione hash nuda, una sicurezza birthday di:

$$
2^{64}
$$

non è più considerata adeguata per applicazioni moderne ad alta sicurezza.

Tuttavia, nel contesto di HMAC, la situazione è diversa. L'attaccante non può generare liberamente tutte le coppie necessarie, perché non conosce la chiave $K$. Dovrebbe osservare circa:

$$
2^{64}
$$

messaggi con i rispettivi HMAC, tutti prodotti con la stessa chiave.

Questo richiederebbe un flusso di comunicazioni così grande e per un tempo così lungo da rendere l'attacco non realistico nel modello discusso dal docente.

Da qui nasce l'osservazione didattica: anche se MD5 come funzione hash non è più consigliabile per nuove progettazioni, l'uso di MD5 dentro HMAC non è automaticamente rotto dagli stessi attacchi che colpiscono MD5 come hash semplice.

> ⚠️ Per sistemi moderni è comunque preferibile usare HMAC con funzioni hash più robuste, come SHA-256 o superiori. Il punto teorico è che HMAC-MD5 non collassa semplicemente perché esistono collisioni pratiche per MD5.

---

### **17. Ruolo della chiave**

La chiave segreta $K$ rimane l'elemento che distingue HMAC da una normale funzione hash.

Un attaccante che conosce il messaggio $M$ può calcolare:

$$
H(M)
$$

ma non può calcolare:

$$
HMAC_K(M)
$$

senza conoscere $K$.

Inoltre, anche osservando molte coppie:

$$
(M_i, HMAC_K(M_i))
$$

l'attaccante non dovrebbe riuscire a ricavare la chiave né a produrre un MAC valido per un nuovo messaggio.

La sicurezza pratica richiede però che:

- la chiave sia generata con sufficiente entropia;
- la chiave rimanga segreta;
- la stessa chiave non venga riutilizzata oltre limiti ragionevoli;
- il tag non venga troncato eccessivamente;
- la funzione hash scelta non sia troppo debole per il livello di sicurezza richiesto.

---

### **18. Confronto con le costruzioni ingenue**

Rispetto al segreto prefisso:

$$
H(K || M)
$$

HMAC evita che il tag finale sia semplicemente lo stato raggiunto dopo aver elaborato chiave e messaggio. Il digest visibile all'attaccante è il risultato dell'hash esterno, non lo stato interno direttamente proseguibile sul messaggio.

Rispetto al segreto suffisso:

$$
H(M || K)
$$

HMAC non si limita ad appendere la chiave dopo il messaggio. Il messaggio viene elaborato dentro un hash interno già inizializzato da una chiave mascherata, e il risultato viene poi autenticato da un secondo hash con una maschera diversa.

Questa doppia struttura impedisce di applicare direttamente gli attacchi visti nella lezione precedente.

> 📌 HMAC è progettato esattamente per evitare gli errori concettuali delle costruzioni $H(K || M)$ e $H(M || K)$.

---

### **19. Sintesi operativa**

Per calcolare HMAC su un messaggio $M$:

1. si sceglie una funzione hash iterata $H$;
2. si determina la dimensione del blocco $b$ e la lunghezza dell'output $n$;
3. si normalizza la chiave ottenendo $K_0$ lungo $b$ bit;
4. si costruiscono `ipad` e `opad` ripetendo rispettivamente i byte `0x36` e `0x5C`;
5. si calcola l'hash interno:

$$
H_{\text{inner}} = H((K_0 \oplus ipad) || M)
$$

6. si calcola l'hash esterno:

$$
HMAC_K(M) = H((K_0 \oplus opad) || H_{\text{inner}})
$$

7. se richiesto dallo standard applicativo, si tronca il risultato ai primi $t$ bit.

---

### **20. Riepilogo**

In questa lezione abbiamo visto che:

- HMAC è lo standard per costruire MAC basati su funzioni hash;
- è descritto in documenti ufficiali come FIPS 198 e RFC 2104;
- viene usato in molti protocolli, inclusa la famiglia SSL/TLS;
- usa la funzione hash come black-box;
- permette di sostituire facilmente la funzione hash interna;
- normalizza la chiave alla dimensione del blocco $b$;
- usa due costanti pubbliche, `ipad = 0x36` e `opad = 0x5C`;
- calcola un hash interno e un hash esterno;
- consente precomputazione quando la chiave resta invariata;
- ha un costo vicino a quello del solo hash del messaggio, soprattutto per messaggi grandi;
- può usare output troncato, purché il tag resti sufficientemente lungo;
- la sua sicurezza dipende dalla funzione hash, ma non coincide banalmente con la sicurezza della hash nuda;
- il miglior attacco generale richiede circa $2^{n/2}$ coppie messaggio-MAC sotto la stessa chiave.

> ✅ HMAC è una costruzione standard perché risolve in modo pulito il problema lasciato aperto nella lezione precedente: usare una funzione hash per autenticare messaggi senza cadere nelle vulnerabilità delle concatenazioni ingenue.

---

> 💡 **Prossimo passo per l'esame:** ora sai perché HMAC è sicuro. Gli appelli 230124 e 250905 propongono uno schema MAC personalizzato, AES diretto per messaggi corti e $AES(h(m))$ per messaggi lunghi, e chiedono di trovarne l'attacco. Vai a [UD4 / L2 - MAC personalizzato AES+hash: attacco per collisione](../UD4_Approfondimenti_Esame/L2%20-%20MAC%20personalizzato%20AES%2Bhash%20-%20attacco%20per%20collisione.md) per la dimostrazione della forgery con una sola query.
