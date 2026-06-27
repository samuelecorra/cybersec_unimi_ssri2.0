## **Lezione 2: Costruzione di MAC**

### **1. Obiettivo della lezione**

Nella lezione precedente abbiamo introdotto i **codici MAC**, cioè funzioni crittografiche che, data una chiave segreta condivisa $K$ e un messaggio $M$, producono un valore breve a lunghezza fissata:

$$
MAC_K(M)
$$

Il destinatario, conoscendo la stessa chiave $K$, ricalcola il MAC sul messaggio ricevuto e confronta il risultato con il tag allegato. Se i due valori coincidono, il destinatario accetta il messaggio come autentico e integro.

In questa lezione il problema diventa più concreto: **come si costruisce effettivamente un MAC sicuro?**

Le due grandi famiglie di costruzione presentate sono:

- costruzioni basate su **cifrari a blocchi**, in particolare il **CBC-MAC**;
- costruzioni basate su **funzioni hash**, ottenute inserendo una chiave segreta dentro il calcolo dell'hash.

> 📌 Un MAC non deve essere solo difficile da invertire: deve essere difficile da falsificare. Anche se un attaccante osserva diversi messaggi con i relativi tag, non deve riuscire a produrre un nuovo messaggio valido con un MAC accettato.

---

### **2. Due tecniche generali di costruzione**

#### **2.1 Costruzione a partire da un cifrario a blocchi**

La prima tecnica usa come blocco costitutivo un **algoritmo di cifratura simmetrica a blocchi**, ad esempio DES o AES.

L'idea generale è questa:

- il messaggio viene diviso in blocchi di dimensione compatibile con il cifrario;
- i blocchi vengono combinati tra loro;
- il risultato finale viene cifrato con la chiave segreta condivisa;
- il valore cifrato, o una sua porzione, diventa il MAC.

In questa famiglia rientra il **CBC-MAC**, che deriva direttamente dalla modalità CBC, cioè **Cipher Block Chaining**.

#### **2.2 Costruzione a partire da una funzione hash**

La seconda tecnica usa una **funzione hash crittografica** come MD5, SHA-1 o le funzioni della famiglia SHA-2.

Una normale funzione hash calcola:

$$
H(M)
$$

ma questo valore non è un MAC, perché chiunque può calcolarlo. Per trasformare una funzione hash in un MAC bisogna inserire nel calcolo anche una chiave segreta $K$:

$$
MAC_K(M) = H(\text{qualcosa che contiene } K \text{ e } M)
$$

Il punto delicato è **dove** e **come** inserire la chiave. Le costruzioni ingenue, come il segreto prefisso e il segreto suffisso, sono infatti vulnerabili.

> ⚠️ Una funzione hash sicura come hash non diventa automaticamente un MAC sicuro. I requisiti sono diversi: in un MAC l'attaccante vede tag calcolati con una chiave segreta e cerca di falsificare nuovi messaggi, non solo di trovare collisioni o preimmagini nel senso classico.

---

### **3. Primo esempio: MAC basato su XOR e DES**

Prima di arrivare al CBC-MAC, consideriamo una costruzione semplice basata su DES. Lo scopo dell'esempio non è proporre uno schema sicuro, ma mostrare perché una costruzione troppo ingenua fallisce.

Supponiamo che il messaggio $M$ venga suddiviso in blocchi da 64 bit:

$$
M = X_1 || X_2 || \dots || X_m
$$

dove ogni blocco $X_i$ ha lunghezza 64 bit, cioè la dimensione del blocco di DES.

Si calcola poi lo XOR di tutti i blocchi del messaggio:

$$
\Delta_M = X_1 \oplus X_2 \oplus \dots \oplus X_m
$$

Il valore $\Delta_M$ è ancora un blocco da 64 bit. A questo punto lo schema cifra $\Delta_M$ con DES, usando la chiave segreta $K$:

$$
MAC_K(M) = E_K(\Delta_M)
$$

dove $E_K$ indica la cifratura DES con chiave $K$.

In questa costruzione:

- la chiave DES ha lunghezza effettiva 56 bit;
- il valore $\Delta_M$ ha lunghezza 64 bit;
- il MAC prodotto ha lunghezza 64 bit;
- DES viene usato come cifrario a blocchi, sostanzialmente su un solo blocco, quindi in una forma equivalente all'uso in ECB per quel blocco.

> 💡 Lo schema sembra ragionevole perché comprime l'intero messaggio in un blocco e poi cifra quel blocco con una chiave segreta. Il problema è che la compressione tramite XOR conserva troppo poca informazione sulla struttura del messaggio.

---

### **4. Sicurezza apparente dello schema XOR+DES**

Se un attaccante osserva una coppia valida:

$$
(M, MAC_K(M))
$$

potrebbe tentare un attacco esaustivo sulla chiave DES.

Dato che DES usa una chiave effettiva di 56 bit, l'attaccante dovrebbe provare circa:

$$
2^{56}
$$

chiavi possibili. Per ogni chiave candidata $K'$, calcola:

$$
E_{K'}(\Delta_M)
$$

e confronta il risultato con il MAC osservato.

Dal punto di vista della sola ricerca esaustiva sulla chiave, quindi, lo schema eredita la sicurezza di DES: rompere direttamente la chiave richiede uno sforzo dell'ordine di $2^{56}$ cifrature.

Questo però non basta a rendere lo schema un buon MAC.

> 📌 La sicurezza di un MAC non si misura solo dalla difficoltà di recuperare la chiave. Un attaccante può avere successo anche senza conoscere $K$, se riesce a produrre un nuovo messaggio con un tag valido.

---

### **5. Attacco allo schema XOR+DES**

Lo schema è vulnerabile perché il MAC dipende soltanto da:

$$
\Delta_M
$$

cioè dallo XOR complessivo dei blocchi, e non dal messaggio completo nella sua struttura, nel suo ordine e nella sua lunghezza.

Supponiamo che l'attaccante conosca una coppia valida:

$$
(M, T)
$$

dove:

$$
T = MAC_K(M) = E_K(\Delta_M)
$$

L'attaccante non conosce la chiave $K$, ma può calcolare $\Delta_M$ perché il messaggio $M$ è noto:

$$
\Delta_M = X_1 \oplus X_2 \oplus \dots \oplus X_m
$$

A questo punto può costruire un nuovo messaggio diverso da $M$ ma con lo stesso valore di XOR complessivo.

Per esempio sceglie arbitrariamente un blocco da 64 bit $Y_1$ e definisce:

$$
Y_2 = Y_1 \oplus \Delta_M
$$

Il nuovo messaggio:

$$
M' = Y_1 || Y_2
$$

ha XOR complessivo:

$$
\Delta_{M'} = Y_1 \oplus Y_2
$$

sostituendo la definizione di $Y_2$:

$$
\Delta_{M'} = Y_1 \oplus (Y_1 \oplus \Delta_M)
$$

Per associatività e perché $Y_1 \oplus Y_1 = 0$, si ottiene:

$$
\Delta_{M'} = \Delta_M
$$

Quindi:

$$
MAC_K(M') = E_K(\Delta_{M'}) = E_K(\Delta_M) = MAC_K(M)
$$

L'attaccante può allora inviare:

$$
(M', T)
$$

e il destinatario accetterà il messaggio come autentico, perché il MAC ricalcolato coincide con quello allegato.

Esiste anche una variante ancora più immediata: se l'attaccante vuole estendere il messaggio originale mantenendo invariato lo XOR, può appendere due blocchi uguali $Y$ e $Y$:

$$
M'' = M || Y || Y
$$

Infatti:

$$
\Delta_{M''} = \Delta_M \oplus Y \oplus Y = \Delta_M
$$

perché $Y \oplus Y = 0$.

> ⚠️ Questo è un attacco di falsificazione: l'attaccante non recupera la chiave, ma produce comunque un messaggio diverso accettato con lo stesso MAC. Per un MAC, questo è già un fallimento grave.

---

### **6. Perché lo XOR non basta**

La debolezza nasce dalle proprietà algebriche dello XOR:

- lo XOR è commutativo, quindi l'ordine dei blocchi non conta;
- lo XOR è associativo, quindi i blocchi possono essere raggruppati liberamente;
- ogni blocco annulla sé stesso, perché $X \oplus X = 0$;
- l'aggiunta di coppie di blocchi con XOR nullo non cambia $\Delta_M$;
- molti messaggi diversi possono avere lo stesso $\Delta_M$.

Lo schema quindi comprime il messaggio in modo troppo rozzo. Due messaggi completamente diversi possono produrre lo stesso valore $\Delta_M$ e quindi lo stesso MAC.

> ✅ Un MAC deve legare il tag all'intero messaggio, non solo a una sintesi lineare facilmente manipolabile.

---

### **7. CBC-MAC**

Per evitare la debolezza dell'esempio precedente si può usare una costruzione più strutturata: il **CBC-MAC**.

Il CBC-MAC utilizza un cifrario a blocchi in modo simile alla modalità CBC. La differenza è che non interessa produrre un testo cifrato completo: interessa ottenere un solo valore finale, usato come tag di autenticazione.

Lo schema prende in input:

- un messaggio $M$ diviso in blocchi;
- un cifrario a blocchi $E_K$;
- una chiave segreta condivisa $K$;
- un vettore iniziale fissato, normalmente uguale a zero.

Supponiamo che:

$$
M = X_1 || X_2 || \dots || X_m
$$

con blocchi tutti della lunghezza richiesta dal cifrario. Nel caso di DES i blocchi sono da 64 bit; nel caso di AES sono da 128 bit.

Si pone:

$$
C_0 = 0
$$

e poi, per ogni blocco del messaggio:

$$
C_i = E_K(X_i \oplus C_{i-1}) \qquad \text{per } i = 1,2,\dots,m
$$

Il MAC è l'ultimo blocco prodotto:

$$
MAC_K(M) = C_m
$$

Eventualmente il valore finale può essere troncato:

$$
MAC_K(M) = \text{trunc}(C_m)
$$

Nel caso storico basato su DES:

- la chiave ha lunghezza 56 bit;
- il blocco cifrato ha lunghezza 64 bit;
- il tag può essere lungo 64 bit oppure essere troncato;
- alcune specifiche permettono di conservare, ad esempio, i bit più a sinistra del blocco finale, scegliendo una lunghezza tra 16 e 64 bit.

Questa costruzione è stata standardizzata in contesti come **FIPS PUB 113** e **ANSI X9.17**.

> 📌 Nel CBC-MAC ogni blocco influenza il blocco successivo attraverso il concatenamento. Il MAC finale non dipende da un semplice XOR globale, ma da una catena di cifrature in cui ogni passo ingloba lo stato prodotto dai passi precedenti.

---

### **8. Lettura operativa del CBC-MAC**

Il CBC-MAC può essere letto come un processo iterativo di aggiornamento dello stato.

All'inizio lo stato è nullo:

$$
C_0 = 0
$$

Il primo blocco viene combinato con lo stato iniziale:

$$
C_1 = E_K(X_1 \oplus C_0) = E_K(X_1)
$$

Il secondo blocco viene combinato con il risultato della cifratura precedente:

$$
C_2 = E_K(X_2 \oplus C_1)
$$

Il terzo blocco viene combinato con $C_2$:

$$
C_3 = E_K(X_3 \oplus C_2)
$$

e così via fino all'ultimo blocco.

Il valore finale $C_m$ sintetizza l'intera catena. Modificare un blocco intermedio cambia l'input del passo successivo e tende a propagare la modifica fino al tag finale.

> ⚠️ Il CBC-MAC classico è sicuro solo sotto ipotesi precise, in particolare per messaggi di lunghezza fissa o gestiti con opportune varianti. Per messaggi di lunghezza variabile bisogna evitare costruzioni ingenue, perché possono comparire attacchi di estensione o concatenazione.

---

### **9. MAC basati su funzioni hash**

La seconda grande famiglia di costruzioni usa le **funzioni hash crittografiche**.

Una funzione hash ordinaria calcola un digest:

$$
H(M)
$$

Questo però non autentica nulla, perché chiunque conosca $M$ può calcolare $H(M)$. Per ottenere un MAC bisogna includere nel calcolo una chiave segreta condivisa:

$$
MAC_K(M) = H(\text{messaggio e chiave})
$$

Questa famiglia è interessante per vari motivi:

- le funzioni hash sono spesso più veloci dei cifrari a blocchi;
- sono implementate in molte librerie crittografiche e di sistema;
- sono semplici da usare in software;
- storicamente non erano soggette alle stesse restrizioni di esportazione applicate agli algoritmi di cifratura.

Il problema è che le proprietà richieste a una funzione hash e quelle richieste a un MAC non coincidono perfettamente.

Una funzione hash sicura dovrebbe rendere difficile:

- trovare una preimmagine;
- trovare una seconda preimmagine;
- trovare collisioni.

Un MAC, invece, deve impedire che un attaccante, anche dopo aver visto tag validi per alcuni messaggi, costruisca un tag valido per un nuovo messaggio.

> 📌 La one-wayness può impedire di ricavare la chiave dal tag, ma non garantisce automaticamente che sia impossibile falsificare un MAC per un altro messaggio.

---

### **10. Funzioni hash iterative e funzione di compressione**

Per capire le vulnerabilità dei MAC hash-based ingenui bisogna ricordare come funzionano molte funzioni hash iterative, come MD5 e SHA-1.

Il messaggio viene diviso in blocchi:

$$
M = M_1 || M_2 || \dots || M_n
$$

La funzione hash mantiene uno stato interno. Si parte da un valore iniziale fissato:

$$
H_0 = IV
$$

Poi si applica ripetutamente una funzione di compressione $F$:

$$
H_i = F(M_i, H_{i-1})
$$

per:

$$
i = 1,2,\dots,n
$$

Il digest finale è:

$$
H(M) = H_n
$$

Questa struttura iterata è efficiente e naturale, ma crea un rischio: se un attaccante conosce uno stato intermedio o finale, può talvolta usarlo come punto di partenza per continuare il calcolo su blocchi aggiuntivi.

---

### **11. Metodo del segreto prefisso**

Il primo metodo ingenuo consiste nel mettere la chiave segreta prima del messaggio:

$$
MAC_K(M) = H(K || M)
$$

Questo schema viene detto **metodo del segreto prefisso**, perché il segreto $K$ è prefissato al messaggio.

L'intuizione è semplice: solo chi conosce $K$ può calcolare l'hash del valore concatenato $K || M$.

Se un attaccante osserva:

$$
M
$$

e:

$$
T = H(K || M)
$$

non dovrebbe essere in grado di ricavare $K$, perché la funzione hash è one-way. Tuttavia, non è necessario recuperare $K$ per falsificare un messaggio.

---

### **12. Attacco di estensione al segreto prefisso**

Consideriamo il caso in cui:

$$
K || M = X_1 || X_2 || \dots || X_n
$$

dopo l'eventuale padding necessario per formare blocchi completi.

Il tag osservato è:

$$
T = H(K || M)
$$

Per una funzione hash iterata, questo valore coincide con lo stato finale dopo l'elaborazione dell'ultimo blocco:

$$
T = H_n
$$

Nel modello didattico semplificato, se l'attaccante vuole estendere il messaggio con un nuovo blocco $X_{n+1}$, può calcolare:

$$
H_{n+1} = F(X_{n+1}, H_n)
$$

cioè:

$$
H_{n+1} = F(X_{n+1}, T)
$$

In questo modo ottiene un valore coerente con l'hash del messaggio esteso:

$$
K || M || X_{n+1}
$$

senza conoscere $K$.

Il nuovo tag è:

$$
T' = H_{n+1}
$$

e l'attaccante può presentare una nuova coppia:

$$
(M || X_{n+1}, T')
$$

che il destinatario rischia di accettare.

Questa è una **existential forgery**, cioè una falsificazione esistenziale: l'attaccante non sceglie necessariamente un messaggio significativo a piacere, ma riesce comunque a produrre almeno un nuovo messaggio valido.

> ⚠️ Nelle funzioni hash reali di tipo MD/SHA bisogna considerare anche il padding e la codifica della lunghezza. In pratica il messaggio forgiato contiene il "glue padding" che la funzione avrebbe aggiunto dopo $K || M$, seguito dai blocchi scelti dall'attaccante. Il principio dell'attacco rimane lo stesso: il digest finale può essere usato come stato da cui proseguire il calcolo.

---

### **13. Contromisura intuitiva: includere la lunghezza**

Una contromisura semplice contro l'estensione consiste nell'includere esplicitamente la lunghezza del messaggio nella computazione.

Per esempio:

$$
MAC_K(M) = H(K || L || M)
$$

dove:

$$
L = \text{len}(M)
$$

In questo modo il valore autenticato non descrive solo il contenuto del messaggio, ma anche la sua lunghezza prevista.

Se un attaccante prova ad aggiungere blocchi alla fine, la lunghezza contenuta nel MAC non corrisponde più alla lunghezza del messaggio esteso.

Questa idea mostra il tipo di problema da risolvere, ma nella pratica si preferiscono costruzioni standardizzate e analizzate formalmente, come HMAC.

> ✅ Il punto essenziale è impedire che un MAC valido per un prefisso possa essere trasformato meccanicamente in un MAC valido per un messaggio più lungo.

---

### **14. Metodo del segreto suffisso**

Un'altra costruzione ingenua consiste nel mettere la chiave segreta dopo il messaggio:

$$
MAC_K(M) = H(M || K)
$$

Questo schema viene detto **metodo del segreto suffisso**, perché il segreto $K$ viene concatenato alla fine.

A prima vista sembra evitare l'attacco precedente: se la chiave è alla fine, l'attaccante non può semplicemente prolungare il messaggio dopo il tag senza dover superare il blocco segreto.

Tuttavia, anche questa costruzione è vulnerabile.

---

### **15. Attacco birthday al segreto suffisso**

Il metodo del segreto suffisso può essere attaccato sfruttando le collisioni della funzione hash iterata.

Supponiamo che l'attaccante trovi due messaggi distinti $M$ e $M'$ tali che producano lo stesso stato hash prima dell'aggiunta della chiave, con una struttura compatibile rispetto ai blocchi elaborati dalla funzione iterata:

$$
H(M) = H(M')
$$

In una funzione hash iterata, se due messaggi portano allo stesso stato interno, allora proseguire il calcolo con gli stessi blocchi successivi produce lo stesso risultato finale.

Quindi, aggiungendo in entrambi i casi la stessa chiave segreta $K$, si ottiene:

$$
H(M || K) = H(M' || K)
$$

cioè:

$$
MAC_K(M) = MAC_K(M')
$$

L'attaccante può allora chiedere o osservare il MAC di $M$ e riutilizzarlo come MAC valido per $M'$.

La ricerca di due messaggi con lo stesso digest può essere effettuata con un attacco di tipo compleanno. Se il digest ha lunghezza $n$ bit, la complessità attesa per trovare una collisione è dell'ordine di:

$$
2^{n/2}
$$

Per un hash a 128 bit, questo significa circa:

$$
2^{64}
$$

tentativi, che è molto meno di $2^{128}$.

> ⚠️ Il segreto suffisso elimina l'attacco di estensione diretto del prefisso, ma introduce una dipendenza critica dalla resistenza alle collisioni dello stato hash prima della chiave.

---

### **16. Confronto tra prefisso e suffisso**

I due metodi ingenui falliscono per motivi diversi.

Nel **segreto prefisso**:

$$
MAC_K(M) = H(K || M)
$$

il problema principale è che il tag finale può essere usato come stato da cui continuare il calcolo della funzione hash. Questo porta ad attacchi di estensione.

Nel **segreto suffisso**:

$$
MAC_K(M) = H(M || K)
$$

il problema principale è che una collisione trovata prima dell'aggiunta della chiave viene preservata anche dopo aver concatenato la stessa chiave segreta.

In entrambi i casi la chiave è presente, ma la posizione della chiave non è sufficiente a garantire sicurezza.

> 📌 Non basta "mettere una chiave dentro un hash". Serve una costruzione progettata in modo specifico per resistere agli attacchi compatibili con la struttura iterata della funzione hash.

---

### **17. Anticipo: HMAC**

La costruzione standard per ottenere un MAC da una funzione hash è **HMAC**, cioè **Hash-based Message Authentication Code**.

L'idea di HMAC è evitare sia la debolezza del segreto prefisso sia quella del segreto suffisso, usando due applicazioni della funzione hash e due costanti chiamate:

- `ipad`, inner padding;
- `opad`, outer padding.

La forma generale è:

$$
HMAC_K(M) = H((K' \oplus opad) || H((K' \oplus ipad) || M))
$$

dove $K'$ è la chiave adattata alla dimensione del blocco della funzione hash.

Questa formula verrà studiata nella prossima lezione. Per ora è importante osservare che HMAC non si limita a concatenare banalmente chiave e messaggio: costruisce due livelli di hashing in modo da proteggere la computazione dagli attacchi di estensione e dalle debolezze dei metodi ingenui.

---

### **18. Riepilogo**

In questa lezione abbiamo visto diversi modi per costruire un MAC:

- un primo schema basato su XOR dei blocchi e cifratura DES;
- l'attacco che mostra perché lo XOR globale non autentica davvero la struttura del messaggio;
- il CBC-MAC, che usa una catena di cifrature in stile CBC e prende come tag l'ultimo blocco;
- i MAC basati su funzioni hash;
- il metodo del segreto prefisso e il suo attacco di estensione;
- il metodo del segreto suffisso e il suo attacco birthday;
- l'idea generale di HMAC come costruzione hash-based corretta.

> ✅ La lezione mostra un principio fondamentale: una primitiva sicura non produce automaticamente un protocollo sicuro. Anche con DES o con una funzione hash crittografica, la costruzione del MAC deve essere progettata con attenzione.
