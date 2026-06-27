## **Lezione 3: Puzzle di Merkle**

### **1. Introduzione**

In questa lezione viene presentato un altro protocollo di **accordo su chiavi**, diverso da Diffie-Hellman, chiamato **Puzzle di Merkle**.

Lo schema è dovuto a Ralph Merkle ed è storicamente molto importante perché rappresenta una delle prime idee di scambio di chiavi su un canale non sicuro.

La caratteristica principale dei Puzzle di Merkle è che la sicurezza non viene presentata come dipendente da un problema matematico specifico, come il logaritmo discreto nel caso di Diffie-Hellman. L'idea è invece creare una forte differenza tra:

- il tempo necessario a un partecipante legittimo per calcolare la chiave;
- il tempo necessario a un avversario per scoprire quale chiave è stata scelta.

Se questa differenza è abbastanza grande, l'attacco diventa inutile dal punto di vista pratico.

> 📌 I Puzzle di Merkle non cercano di impedire in assoluto la risoluzione dei puzzle: cercano di fare in modo che Bob debba risolverne uno solo, mentre l'avversario debba risolverne molti.

---

### **2. Idea generale dello schema**

L'idea di base è la seguente.

Alice genera un numero elevato di chiavi distinte:

$$
X_1,X_2,\dots,X_N
$$

e nasconde ciascuna chiave dentro un **puzzle**.

Il termine puzzle viene usato perché la chiave non è inviata in chiaro a Bob. Il messaggio che Bob riceve contiene solo informazioni cifrate, sufficienti però a ricavare una chiave con un lavoro ragionevole.

Quindi:

- risolvere un singolo puzzle deve essere fattibile;
- risolvere tutti i puzzle deve essere troppo costoso;
- Bob deve risolverne uno solo;
- l'avversario non sa quale puzzle Bob sceglierà, quindi deve risolverne molti.

In questo modo Alice e Bob ottengono un segreto condiviso, mentre un nemico che osserva il traffico non ha informazioni sufficienti per sapere subito quale segreto sia stato scelto.

> 💡 Lo schema si basa su un'asimmetria di lavoro: Bob fa una sola ricerca, l'avversario deve ripetere la ricerca su molti puzzle.

---

### **3. Che cosa contiene un puzzle**

Nel modello descritto nella lezione, ogni puzzle contiene tre informazioni:

$$
X,\ ID,\ S
$$

dove:

- $X$ è la **soluzione del puzzle**, cioè il valore che diventerà la chiave segreta condivisa se quel puzzle viene scelto;
- $ID$ è l'**identificativo del puzzle**, necessario perché Bob possa comunicare ad Alice quale puzzle ha risolto;
- $S$ è un valore noto, usato per garantire l'unicità e riconoscibilità della soluzione.

Il puzzle non trasmette direttamente questi valori in chiaro. Alice li cifra usando una chiave casuale $K$.

Nel transcript viene considerato un esempio basato su **DES in modalità CBC**:

$$
Y=\operatorname{CBC\text{-}DES}_K(X,ID,S)
$$

Il puzzle inviato contiene:

$$
Y
$$

insieme ai primi $20$ bit della chiave $K$ usata per cifrare.

Poiché DES usa chiavi di $56$ bit, conoscere i primi $20$ bit lascia sconosciuti:

$$
56-20=36
$$

bit della chiave.

Per risolvere il puzzle, un utente che non conosce $K$ deve provare le possibili chiavi compatibili con quei primi $20$ bit noti. In media deve fare circa:

$$
2^{35}
$$

tentativi, perché lo spazio residuo ha dimensione $2^{36}$ e mediamente la chiave corretta si trova a metà dello spazio di ricerca.

> 📌 Il valore $S$ serve come controllo: quando una decifratura produce il valore noto atteso, chi risolve il puzzle capisce di avere trovato la chiave corretta.

---

### **4. Costruzione dei puzzle da parte di Alice**

Alice prepara $N$ puzzle, con $N$ abbastanza grande.

Per ogni indice:

$$
i=1,\dots,N
$$

Alice sceglie:

- una soluzione segreta $X_i$;
- un identificativo $ID_i$;
- una chiave DES casuale $K_i$ da $56$ bit.

Poi costruisce:

$$
Y_i=\operatorname{CBC\text{-}DES}_{K_i}(X_i,ID_i,S)
$$

e forma il puzzle:

$$
P_i=(Y_i,\ \text{primi 20 bit di }K_i)
$$

L'insieme dei puzzle è quindi:

$$
\{P_1,P_2,\dots,P_N\}
$$

Alice invia tutti questi puzzle a Bob.

Alice conserva anche la corrispondenza tra ogni identificativo $ID_i$ e la rispettiva soluzione $X_i$, perché più avanti dovrà capire quale puzzle Bob ha scelto.

> ⚠️ Alice genera molti puzzle, ma per ciascun puzzle conosce già la soluzione. Il costo per lei è la costruzione dell'insieme dei puzzle, non la loro rottura per forza bruta.

---

### **5. Scelta e risoluzione del puzzle da parte di Bob**

Bob riceve tutti i puzzle:

$$
P_1,P_2,\dots,P_N
$$

e ne sceglie uno a caso, per esempio il puzzle:

$$
P_j
$$

A questo punto Bob prova a risolverlo.

Nel caso dell'esempio con DES, Bob conosce:

- il testo cifrato $Y_j$;
- i primi $20$ bit della chiave $K_j$.

Gli restano da indovinare i $36$ bit mancanti della chiave.

Bob esegue quindi una ricerca esaustiva sulle chiavi compatibili, fino a trovare una decifratura che produca una struttura coerente:

$$
(X_j,ID_j,S)
$$

Quando Bob ottiene il valore noto $S$, riconosce di avere trovato la chiave corretta del puzzle. A quel punto conosce:

- $X_j$, cioè la soluzione del puzzle;
- $ID_j$, cioè l'identificativo del puzzle scelto.

Il valore:

$$
X_j
$$

diventa il segreto condiviso che Bob userà con Alice.

---

### **6. Comunicazione dell'identificativo ad Alice**

Dopo avere risolto il puzzle, Bob non deve inviare ad Alice la chiave segreta $X_j$.

Bob invia solo l'identificativo:

$$
ID_j
$$

Questo identificativo può essere mandato sul canale pubblico, perché da solo non contiene la soluzione del puzzle.

Alice, che aveva generato tutti i puzzle, cerca nella propria tabella il puzzle con identificativo $ID_j$. Così recupera la soluzione corrispondente:

$$
X_j
$$

A questo punto:

- Bob conosce $X_j$ perché ha risolto il puzzle;
- Alice conosce $X_j$ perché aveva generato il puzzle;
- l'avversario vede $ID_j$, ma non conosce automaticamente $X_j$.

La chiave condivisa tra Alice e Bob è quindi:

$$
K_{AB}=X_j
$$

> ✅ Bob non comunica mai la chiave. Comunica solo quale puzzle ha risolto; Alice usa l'identificativo per recuperare la chiave già presente nella propria lista.

---

### **7. Costo computazionale per Alice**

Analizziamo ora i tempi necessari per eseguire lo schema.

Alice deve costruire $N$ puzzle.

Se consideriamo costante il costo di costruzione di un singolo puzzle, il tempo totale di Alice è proporzionale a:

$$
N
$$

Quindi:

$$
T_A=\Theta(N)
$$

Alice deve anche trasmettere tutti i puzzle a Bob. Anche la dimensione della comunicazione cresce quindi con $N$.

Questo è uno dei motivi per cui lo schema diventa rapidamente poco pratico quando si aumenta il livello di sicurezza.

---

### **8. Costo computazionale per Bob**

Bob deve risolvere un solo puzzle.

Indichiamo con:

$$
T
$$

il tempo necessario per risolvere un singolo puzzle.

Nel caso dell'esempio con DES e primi $20$ bit della chiave noti, questo tempo corrisponde in media a circa:

$$
2^{35}
$$

tentativi.

Quindi:

$$
T_B=\Theta(T)
$$

Bob non deve risolvere tutti i puzzle: ne sceglie uno e lavora solo su quello.

---

### **9. Costo computazionale per l'avversario**

Un avversario osserva:

- tutti i puzzle inviati da Alice a Bob;
- l'identificativo $ID_j$ inviato da Bob ad Alice.

Il problema dell'avversario è che, prima di risolvere i puzzle, non conosce la corrispondenza tra identificativo e soluzione. Per sapere quale chiave Alice e Bob useranno, deve trovare il puzzle associato a $ID_j$ e ricavarne la soluzione.

Nel caso peggiore, l'avversario potrebbe dover risolvere tutti gli $N$ puzzle.

In media, se l'ordine di ricerca è casuale, dovrà risolverne circa:

$$
\frac{N}{2}
$$

Il tempo medio dell'attaccante è quindi proporzionale a:

$$
T\cdot N
$$

trascurando il fattore costante $\frac{1}{2}$ nella notazione asintotica.

Quindi:

$$
T_E=\Theta(TN)
$$

> 📌 La sicurezza nasce dal fatto che Bob paga il costo $T$ una sola volta, mentre l'avversario paga lo stesso costo per molti puzzle.

---

### **10. Scelta dei parametri $T$ e $N$**

Per rendere visibile il vantaggio dello schema, si scelgono $T$ e $N$ dello stesso ordine di grandezza.

Supponiamo:

$$
N=\Theta(T)
$$

Allora:

- il tempo di Alice è:

$$
T_A=\Theta(N)
$$

- il tempo di Bob è:

$$
T_B=\Theta(T)=\Theta(N)
$$

- il tempo dell'avversario diventa:

$$
T_E=\Theta(TN)=\Theta(N^2)
$$

Questa è la proprietà fondamentale dello schema: i partecipanti legittimi hanno costo lineare, mentre l'avversario ha costo quadratico.

> 💡 I Puzzle di Merkle forniscono una separazione quadratica: se Alice e Bob spendono circa $N$, l'avversario deve spendere circa $N^2$.

---

### **11. Significato della sicurezza quadratica**

La sicurezza dello schema dipende dal numero di puzzle.

Se $N$ è piccolo, l'avversario può risolvere tutti i puzzle e scoprire la chiave.

Se $N$ è molto grande, l'avversario deve fare un lavoro molto più alto rispetto a Bob. In questo caso il costo dell'attacco può diventare troppo grande per essere utile.

Tuttavia, la crescita è solo quadratica. Questo significa che, per ottenere un livello di sicurezza elevato, bisogna scegliere $N$ molto grande.

Ma scegliere $N$ molto grande significa anche:

- generare moltissimi puzzle;
- trasmettere moltissimi puzzle;
- memorizzare una grande quantità di dati;
- far crescere il costo iniziale di Alice;
- rendere più pesante la comunicazione tra Alice e Bob.

Quindi il protocollo è interessante dal punto di vista teorico e storico, ma poco pratico per applicazioni reali moderne.

> ⚠️ La sicurezza richiede un numero grande di puzzle; lo stesso numero grande di puzzle rende però il protocollo inefficiente.

---

### **12. Confronto con Diffie-Hellman**

Diffie-Hellman e i Puzzle di Merkle risolvono lo stesso problema generale: permettere a due utenti di accordarsi su una chiave segreta attraverso un canale pubblico.

La differenza è nel principio di sicurezza.

Diffie-Hellman si basa sulla difficoltà di calcolare:

$$
g^{xy}\bmod p
$$

avendo solo:

$$
p,\ g,\ g^x,\ g^y
$$

Questa difficoltà è collegata al problema del logaritmo discreto e al problema computazionale di Diffie-Hellman.

I Puzzle di Merkle, invece, non usano una struttura algebrica di questo tipo. La sicurezza è data dal fatto che l'attaccante deve risolvere molti problemi indipendenti, mentre Bob ne risolve uno solo.

In termini asintotici:

- Bob lavora in tempo $\Theta(N)$, scegliendo i parametri in modo che $T=\Theta(N)$;
- l'attaccante lavora in tempo $\Theta(N^2)$.

Questa separazione è reale, ma meno forte di quella desiderata nei protocolli crittografici moderni, dove si vuole che il lavoro dell'avversario cresca in modo esponenziale rispetto al parametro di sicurezza.

---

### **13. Sintesi finale**

In questa lezione abbiamo visto lo schema dei **Puzzle di Merkle** per l'accordo su chiavi.

I punti principali sono:

- Alice genera $N$ chiavi distinte e le nasconde in $N$ puzzle;
- ogni puzzle contiene una soluzione $X_i$, un identificativo $ID_i$ e un valore noto $S$;
- nell'esempio della lezione, il puzzle è costruito come cifratura DES-CBC:

$$
Y_i=\operatorname{CBC\text{-}DES}_{K_i}(X_i,ID_i,S)
$$

- insieme al cifrato vengono resi noti i primi $20$ bit della chiave DES da $56$ bit;
- risolvere un puzzle richiede mediamente circa $2^{35}$ tentativi;
- Bob sceglie un solo puzzle, lo risolve e ottiene $(X_j,ID_j)$;
- Bob rimanda ad Alice solo $ID_j$;
- Alice usa $ID_j$ per recuperare $X_j$ dalla propria lista;
- $X_j$ diventa la chiave condivisa;
- Alice lavora in tempo $\Theta(N)$;
- Bob lavora in tempo $\Theta(T)$;
- l'avversario lavora mediamente in tempo $\Theta(TN)$;
- scegliendo $T$ e $N$ dello stesso ordine, Alice e Bob hanno costo lineare mentre l'avversario ha costo quadratico;
- per avere sicurezza serve un $N$ molto grande, e questo rende lo schema poco pratico.

> ✅ I Puzzle di Merkle mostrano che è possibile ottenere accordo su chiavi senza un canale sicuro sfruttando una differenza di complessità tra utenti legittimi e avversario; il limite è che la sicurezza cresce solo quadraticamente e richiede un numero molto grande di puzzle.
