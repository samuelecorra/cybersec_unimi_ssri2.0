Questo modulo conclusivo è dedicato alle **applicazioni pratiche della crittografia**, ossia a come i principi teorici visti finora vengono utilizzati per **scambiare chiavi in modo sicuro**, **condividere segreti tra più parti** e **proteggere le informazioni tramite tecniche visive**.

Si approfondiranno quindi tre ambiti fondamentali:

- lo **scambio di chiavi crittografiche**, necessario per stabilire comunicazioni riservate su canali insicuri;
- la **condivisione del segreto**, in cui un'informazione sensibile viene suddivisa tra più soggetti, ciascuno con una parte necessaria alla ricostruzione;
- la **crittografia visuale**, che applica principi matematici alla codifica di immagini o documenti visivi.

L'obiettivo del modulo è mostrare come la **teoria crittografica** diventi **strumento operativo** nei moderni sistemi di sicurezza, combinando rigore matematico e applicazioni concrete.

---

## **Lezione 1: Protocollo Diffie-Hellman**

### **1. Obiettivo della lezione**

In questa lezione vediamo un protocollo che permette a due utenti, che comunicano su un **canale insicuro**, di scambiarsi informazioni pubbliche attraverso le quali riescono a calcolare una **chiave simmetrica condivisa**.

Il problema nasce dal fatto che, per comunicare in modo riservato, Alice e Bob possono usare un algoritmo di cifratura simmetrica. Tuttavia, un algoritmo simmetrico richiede che entrambe le parti conoscano la stessa chiave segreta:

$$
K
$$

Questa chiave deve essere nota solo ad Alice e Bob. Se un avversario la conoscesse, potrebbe decifrare i messaggi. Se invece Alice e Bob non la conoscono entrambi, non possono cifrare e decifrare correttamente.

Il punto critico è quindi: **come fanno due utenti a stabilire una chiave segreta comune comunicando su un canale osservabile da altri?**

Il protocollo di **Diffie-Hellman** risponde proprio a questa domanda.

> 📌 Diffie-Hellman non cifra direttamente i messaggi: serve a stabilire una chiave condivisa che potrà poi essere usata da un cifrario simmetrico.

---

### **2. Protocolli di accordo su chiave**

Un **protocollo di accordo su chiave** specifica un meccanismo attraverso il quale due o più parti possono stabilire una chiave segreta comune.

In uno schema di questo tipo, la chiave finale non viene trasmessa direttamente. Viene invece calcolata come funzione:

- dei valori segreti scelti localmente dagli utenti;
- dei valori pubblici scambiati sul canale.

Un protocollo di accordo su chiave deve garantire che un avversario non riesca a ricavare la chiave, anche se osserva le informazioni scambiate.

In generale possiamo distinguere:

- un **avversario passivo**, che ascolta il traffico sul canale insicuro;
- un **avversario attivo**, che può anche modificare, sostituire o iniettare messaggi.

La sicurezza desiderata è che l'avversario, pur vedendo le informazioni pubbliche scambiate, non sia in grado di ricostruire la chiave condivisa.

> ⚠️ La protezione contro avversari passivi e quella contro avversari attivi non sono la stessa cosa. Diffie-Hellman base protegge dalla sola osservazione, ma senza autenticazione non protegge dal Man-in-the-Middle.

---

### **3. Due schemi di accordo su chiave**

Nel corso vengono presentati due schemi di accordo su chiave:

- il protocollo di **Diffie-Hellman**;
- i **Puzzle di Merkle**.

Il protocollo di Diffie-Hellman si basa su un problema computazionale difficile collegato al **logaritmo discreto**.

I Puzzle di Merkle, che verranno visti nella lezione successiva dell'unità, seguono invece una logica diversa e non si basano sulla stessa assunzione matematica del logaritmo discreto.

Storicamente, Diffie-Hellman è particolarmente importante perché compare nel lavoro in cui Diffie e Hellman introdussero la crittografia a chiave pubblica.

> 💡 Diffie-Hellman è uno dei primi esempi in cui due utenti riescono a stabilire un segreto comune senza essersi incontrati prima per condividere una chiave.

---

### **4. Contesto matematico**

Il protocollo lavora nel gruppo moltiplicativo:

$$
\mathbb{Z}_p^*
$$

dove:

$$
p
$$

è un numero primo.

Il gruppo:

$$
\mathbb{Z}_p^*
$$

contiene gli elementi non nulli modulo $p$:

$$
\{1,2,\dots,p-1\}
$$

con l'operazione di moltiplicazione modulo $p$.

Serve poi un elemento:

$$
g \in \mathbb{Z}_p^*
$$

che sia un **generatore** del gruppo.

Dire che $g$ è generatore significa che le sue potenze producono tutti gli elementi del gruppo:

$$
\{g^i \bmod p \mid 1 \le i \le p-1\} = \mathbb{Z}_p^*
$$

Un generatore di $\mathbb{Z}_p^*$ viene anche chiamato **radice primitiva modulo $p$**.

Poiché $p$ è primo, il gruppo $\mathbb{Z}_p^*$ è ciclico, quindi esiste almeno un generatore.

> 📌 La scelta di un generatore $g$ serve perché le potenze di $g$ coprano il gruppo: in questo modo i valori pubblici scambiati hanno la struttura matematica richiesta dal protocollo.

---

### **5. Parametri pubblici del protocollo**

Alice e Bob si accordano pubblicamente su due valori:

$$
p
$$

e:

$$
g
$$

dove:

- $p$ è un numero primo;
- $g$ è un generatore di $\mathbb{Z}_p^*$.

Questi valori non devono essere segreti. Possono essere noti anche all'avversario.

L'aritmetica del protocollo avviene modulo $p$.

> ✅ In Diffie-Hellman, i parametri $p$ e $g$ sono pubblici. La segretezza dipende dagli esponenti privati scelti da Alice e Bob.

---

### **6. Scelta dei segreti**

Alice sceglie casualmente un esponente segreto:

$$
x
$$

Bob sceglie casualmente un esponente segreto:

$$
y
$$

Nel modello didattico possiamo considerarli come elementi scelti in un insieme di esponenti modulo $p$ o modulo l'ordine del gruppo. In pratica devono essere scelti casualmente in un intervallo adeguato e mantenuti segreti.

Alice non comunica $x$.

Bob non comunica $y$.

Questi due valori rimangono privati.

---

### **7. Scambio dei valori pubblici**

Alice calcola:

$$
A = g^x \bmod p
$$

e invia $A$ a Bob.

Bob calcola:

$$
B = g^y \bmod p
$$

e invia $B$ ad Alice.

Sul canale insicuro transitano quindi:

$$
g^x \bmod p
$$

e:

$$
g^y \bmod p
$$

Un avversario che ascolta il canale vede questi valori, oltre ai parametri pubblici $p$ e $g$.

Non vede però gli esponenti segreti:

$$
x
$$

e:

$$
y
$$

---

### **8. Calcolo della chiave condivisa**

Dopo lo scambio, Alice riceve:

$$
B = g^y \bmod p
$$

e lo eleva al proprio esponente segreto $x$:

$$
K_A = B^x \bmod p
$$

Sostituendo $B$:

$$
K_A = (g^y)^x \bmod p
$$

quindi:

$$
K_A = g^{xy} \bmod p
$$

Bob riceve:

$$
A = g^x \bmod p
$$

e lo eleva al proprio esponente segreto $y$:

$$
K_B = A^y \bmod p
$$

Sostituendo $A$:

$$
K_B = (g^x)^y \bmod p
$$

quindi:

$$
K_B = g^{xy} \bmod p
$$

Alice e Bob ottengono lo stesso valore:

$$
K_A = K_B = g^{xy} \bmod p
$$

Questo valore diventa la chiave condivisa:

$$
K = g^{xy} \bmod p
$$

> ✅ La chiave $K$ non viene mai trasmessa sul canale. Alice e Bob la calcolano indipendentemente a partire dai propri segreti e dal valore pubblico ricevuto dall'altro.

---

### **9. Cosa vede l'avversario**

Un avversario passivo vede:

$$
p
$$

$$
g
$$

$$
g^x \bmod p
$$

$$
g^y \bmod p
$$

L'obiettivo dell'avversario sarebbe calcolare:

$$
g^{xy} \bmod p
$$

cioè la chiave condivisa.

Il protocollo è progettato in modo che, per parametri sufficientemente grandi, questo calcolo sia computazionalmente impraticabile.

---

### **10. Esempio numerico piccolo**

Consideriamo:

$$
p = 11
$$

e:

$$
g = 2
$$

Il valore $2$ è un generatore di:

$$
\mathbb{Z}_{11}^*
$$

Alice sceglie:

$$
x = 3
$$

Bob sceglie:

$$
y = 4
$$

Alice calcola:

$$
A = 2^3 \bmod 11 = 8
$$

e invia:

$$
8
$$

a Bob.

Bob calcola:

$$
B = 2^4 \bmod 11 = 5
$$

e invia:

$$
5
$$

ad Alice.

Alice calcola la chiave:

$$
K_A = 5^3 \bmod 11
$$

cioè:

$$
K_A = 125 \bmod 11 = 4
$$

Bob calcola:

$$
K_B = 8^4 \bmod 11
$$

e:

$$
8^4 = 4096
$$

quindi:

$$
K_B = 4096 \bmod 11 = 4
$$

Entrambi ottengono:

$$
K = 4
$$

---

### **11. Esempio numerico più grande**

Consideriamo ora l'esempio con numeri più grandi.

Sia:

$$
p = 25307
$$

e:

$$
g = 2
$$

Alice e Bob scelgono due esponenti segreti:

$$
x = 19956
$$

e:

$$
y = 3578
$$

Alice calcola:

$$
A = 2^{19956} \bmod 25307 = 6113
$$

Bob calcola:

$$
B = 2^{3578} \bmod 25307 = 7984
$$

Sul canale l'avversario vede soltanto:

$$
6113
$$

e:

$$
7984
$$

oltre ai parametri pubblici $p$ e $g$.

Alice calcola:

$$
K_A = 7984^{19956} \bmod 25307
$$

Bob calcola:

$$
K_B = 6113^{3578} \bmod 25307
$$

I due valori coincidono:

$$
K_A = K_B = 3694
$$

Quindi la chiave condivisa è:

$$
K = 3694
$$

> 📌 Negli esempi didattici i numeri sono piccoli o comunque gestibili a mano. Nella pratica reale, $p$ deve avere dimensioni molto maggiori, storicamente almeno 1024 bit e oggi preferibilmente più grandi o sostituito da gruppi ellittici adeguati.

---

### **12. Problema di Diffie-Hellman**

La sicurezza dello schema non si basa esattamente solo sul problema del logaritmo discreto, ma su una variante chiamata **problema di Diffie-Hellman**.

L'avversario conosce:

$$
p
$$

$$
g
$$

$$
g^x \bmod p
$$

$$
g^y \bmod p
$$

e vuole calcolare:

$$
g^{xy} \bmod p
$$

Questo problema è chiamato **Computational Diffie-Hellman problem**, o più semplicemente problema di Diffie-Hellman.

Il problema del logaritmo discreto, invece, consiste nel ricavare $x$ da:

$$
g^x \bmod p
$$

cioè:

$$
x = \log_g(g^x)
$$

Se esistesse un algoritmo efficiente per risolvere il logaritmo discreto, allora sarebbe facile risolvere anche il problema di Diffie-Hellman:

1. si osserva $g^x \bmod p$;
2. si calcola $x$ tramite logaritmo discreto;
3. si calcola $(g^y)^x \bmod p = g^{xy}\bmod p$.

Quindi:

$$
\text{DLP efficiente} \Rightarrow \text{DH efficiente}
$$

Non è invece dimostrato il viceversa: non sappiamo in generale se un algoritmo efficiente per calcolare direttamente $g^{xy}$ permetterebbe anche di risolvere il logaritmo discreto.

> ⚠️ Il problema di Diffie-Hellman è ritenuto difficile quanto basta per la sicurezza pratica del protocollo, ma la sua equivalenza completa con il logaritmo discreto non è dimostrata in generale.

---

### **13. Man-in-the-Middle**

Il protocollo Diffie-Hellman base risolve il problema di stabilire una chiave contro un avversario che ascolta passivamente.

Non risolve però il problema dell'**autenticazione**.

Alice sa di aver calcolato una chiave con qualcuno, ma non ha garanzia crittografica che quel qualcuno sia davvero Bob.

Bob sa di aver calcolato una chiave con qualcuno, ma non ha garanzia che quel qualcuno sia davvero Alice.

Questa debolezza permette un attacco di tipo **Man-in-the-Middle**.

---

### **14. Dinamica dell'attacco Man-in-the-Middle**

Supponiamo che Eve sia l'avversario attivo.

Alice avvia il protocollo pensando di parlare con Bob.

Alice invia:

$$
g^x \bmod p
$$

Eve intercetta il messaggio e non lo inoltra direttamente a Bob. Invece risponde ad Alice fingendo di essere Bob e inviando un proprio valore:

$$
g^a \bmod p
$$

Alice calcola quindi una chiave condivisa con Eve:

$$
K_{AE} = g^{xa} \bmod p
$$

Nel frattempo Eve contatta Bob fingendo di essere Alice. Bob invia:

$$
g^y \bmod p
$$

Eve gli risponde con un altro proprio valore:

$$
g^b \bmod p
$$

Bob calcola quindi una chiave condivisa con Eve:

$$
K_{BE} = g^{yb} \bmod p
$$

A questo punto:

- Alice crede di condividere una chiave con Bob;
- Bob crede di condividere una chiave con Alice;
- in realtà Alice condivide una chiave con Eve;
- Bob condivide un'altra chiave con Eve.

Eve può quindi agire da intermediario:

1. riceve i messaggi cifrati da Alice;
2. li decifra con $K_{AE}$;
3. li legge o modifica;
4. li ricifra con $K_{BE}$;
5. li inoltra a Bob.

Lo stesso può fare nel verso opposto.

> ⚠️ Il problema non è matematico nel calcolo di $g^{xy}$: il problema è che Diffie-Hellman base non autentica l'identità delle parti.

---

### **15. Diffie-Hellman autenticato**

Per evitare il Man-in-the-Middle, il protocollo deve essere modificato introducendo un meccanismo di autenticazione.

Le soluzioni tipiche sono:

- firme digitali sui valori Diffie-Hellman scambiati;
- certificati digitali che legano una chiave pubblica a un'identità;
- protocolli più completi che integrano Diffie-Hellman dentro uno schema autenticato.

L'idea è che Alice non deve solo ricevere un valore del tipo:

$$
g^y \bmod p
$$

ma deve anche avere una prova che quel valore provenga davvero da Bob.

Analogamente, Bob deve avere una prova che:

$$
g^x \bmod p
$$

provenga davvero da Alice.

> ✅ Diffie-Hellman fornisce segretezza della chiave contro ascolto passivo; per proteggere l'identità dei partecipanti serve una versione autenticata.

---

### **16. Riepilogo**

In questa lezione abbiamo visto il protocollo di Diffie-Hellman per lo scambio di chiavi.

I punti fondamentali sono:

- le comunicazioni simmetriche richiedono una chiave segreta condivisa;
- un protocollo di accordo su chiave permette di stabilire tale chiave su un canale insicuro;
- Diffie-Hellman usa un primo $p$ e un generatore $g$ di $\mathbb{Z}_p^*$;
- Alice sceglie un segreto $x$ e invia $g^x \bmod p$;
- Bob sceglie un segreto $y$ e invia $g^y \bmod p$;
- entrambi calcolano la stessa chiave $K=g^{xy}\bmod p$;
- l'avversario passivo vede $p$, $g$, $g^x$ e $g^y$, ma non dovrebbe riuscire a calcolare $g^{xy}$;
- il problema di Diffie-Hellman è collegato al logaritmo discreto, ma non è provata in generale l'equivalenza completa;
- il protocollo base è vulnerabile al Man-in-the-Middle perché non autentica le parti;
- per l'uso reale serve una variante autenticata, ad esempio tramite firme digitali o certificati.

> ✅ Diffie-Hellman è fondamentale perché permette di accordarsi su una chiave segreta senza trasmetterla; la sua sicurezza contro ascolto passivo deriva dal problema di Diffie-Hellman, mentre la protezione contro attacchi attivi richiede autenticazione.
