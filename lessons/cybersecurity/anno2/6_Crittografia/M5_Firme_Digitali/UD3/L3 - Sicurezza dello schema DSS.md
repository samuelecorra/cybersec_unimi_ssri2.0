## **Lezione 3: Sicurezza dello schema DSS**

### **1. Obiettivo della lezione**

In questa lezione facciamo alcune considerazioni sulla **sicurezza dello schema DSS/DSA**.

L'obiettivo è duplice:

- analizzare alcuni attacchi possibili contro lo schema;
- confrontare le prestazioni di DSA con quelle dello schema di firma RSA.

La sicurezza del DSA è collegata principalmente all'intrattabilità del **problema del logaritmo discreto**. Inoltre, quando lo schema utilizza una funzione hash come SHA, alcune proprietà di sicurezza dipendono anche dalla difficoltà di invertire tale funzione hash.

> 📌 Per attaccare DSA, l'avversario dovrebbe riuscire a recuperare il segreto $s$, costruire firme valide senza conoscere $s$, oppure produrre una coppia messaggio-firma accettata dalla verifica.

---

### **2. Richiamo dello schema DSA**

Ricordiamo i parametri dello schema.

I parametri pubblici globali sono:

$$
p,\ q,\ \alpha
$$

dove:

- $p$ è un primo grande;
- $q$ è un primo di 160 bit tale che $q \mid (p-1)$;
- $\alpha \in \mathbb{Z}_p^*$ ha ordine $q$.

La chiave privata dell'utente Alice è:

$$
s
$$

con:

$$
0 < s < q
$$

La chiave pubblica corrispondente è:

$$
\beta = \alpha^s \bmod p
$$

Per firmare un messaggio $M$, Alice sceglie un valore casuale $r$ e calcola:

$$
\gamma = (\alpha^r \bmod p) \bmod q
$$

e:

$$
\delta = (\operatorname{SHA}(M)+s\gamma)r^{-1} \bmod q
$$

La firma è:

$$
(\gamma,\delta)
$$

La verifica calcola:

$$
e' = \operatorname{SHA}(M)\delta^{-1} \bmod q
$$

e:

$$
e'' = \gamma\delta^{-1} \bmod q
$$

e accetta la firma se:

$$
\gamma = (\alpha^{e'}\beta^{e''} \bmod p) \bmod q
$$

---

### **3. Total break in un key-only attack**

Il primo attacco considerato è un **key-only attack** con obiettivo di **total break**.

In questo scenario l'avversario conosce solo la chiave pubblica di Alice:

$$
(p,q,\alpha,\beta)
$$

e vuole recuperare la chiave privata:

$$
s
$$

Dato che:

$$
\beta = \alpha^s \bmod p
$$

recuperare $s$ significa risolvere:

$$
s = \log_{\alpha}(\beta) \pmod p
$$

cioè calcolare il logaritmo discreto di $\beta$ in base $\alpha$ nel gruppo modulo $p$.

Per parametri scelti correttamente, il problema del logaritmo discreto è computazionalmente intrattabile. Quindi l'attacco non è considerato praticabile.

> ✅ Un total break key-only contro DSA richiede di ricavare $s$ da $\beta=\alpha^s \bmod p$, quindi si riduce al problema del logaritmo discreto.

---

### **4. Selective forgery in un key-only attack**

Consideriamo ora un attacco di tipo:

- **key-only**, perché l'avversario conosce solo la chiave pubblica;
- **selective forgery**, perché l'avversario vuole produrre una firma valida per un messaggio specifico $M$.

L'avversario vuole costruire una coppia:

$$
(\gamma,\delta)
$$

tale che la verifica su $M$ abbia successo:

$$
\gamma = (\alpha^{e'}\beta^{e''} \bmod p) \bmod q
$$

dove:

$$
e' = \operatorname{SHA}(M)\delta^{-1} \bmod q
$$

e:

$$
e'' = \gamma\delta^{-1} \bmod q
$$

Supponiamo che l'avversario scelga casualmente un primo valore $\gamma$ e cerchi poi di determinare un valore $\delta$ che faccia passare la verifica.

Sostituendo $e'$ ed $e''$ nella formula di verifica, il controllo contiene una relazione del tipo:

$$
\alpha^{\operatorname{SHA}(M)\delta^{-1}}\beta^{\gamma\delta^{-1}}
$$

Usando:

$$
\beta = \alpha^s \bmod p
$$

la quantità verificata dipende implicitamente da:

$$
\operatorname{SHA}(M)+s\gamma
$$

cioè dalla chiave privata $s$.

Per scegliere un $\delta$ valido senza conoscere $s$, l'avversario dovrebbe risolvere una relazione esponenziale nel gruppo, equivalente a un problema di logaritmo discreto. Nel linguaggio del transcript, dovrebbe estrarre un logaritmo discreto della quantità costruita a partire da:

$$
\alpha^{\operatorname{SHA}(M)}\beta^{\gamma}
$$

rispetto alla base legata al valore scelto per la firma.

Poiché il logaritmo discreto è intrattabile, l'attaccante non riesce a produrre la firma selettiva.

> 📌 La selective forgery richiede una firma valida su un messaggio scelto. In DSA, determinare una coppia $(\gamma,\delta)$ coerente con quel messaggio e con la chiave pubblica porta a un problema di logaritmo discreto.

---

### **5. Existential forgery in un key-only attack**

Consideriamo infine un attacco di tipo **existential forgery**.

In questo caso l'avversario non vuole firmare un messaggio specifico. Vuole soltanto produrre una qualunque coppia:

$$
(M,(\gamma,\delta))
$$

che venga accettata dalla verifica.

Una possibile strategia sarebbe scegliere casualmente:

$$
\gamma
$$

e:

$$
\delta
$$

e poi cercare un messaggio $M$ che renda valida la verifica.

La verifica richiede:

$$
\gamma = (\alpha^{e'}\beta^{e''} \bmod p) \bmod q
$$

con:

$$
e' = \operatorname{SHA}(M)\delta^{-1} \bmod q
$$

e:

$$
e'' = \gamma\delta^{-1} \bmod q
$$

Poniamo:

$$
z = \operatorname{SHA}(M)
$$

L'avversario dovrebbe quindi trovare un valore $z$ tale che la verifica risulti soddisfatta.

Il transcript descrive questa condizione come la necessità di trovare $z$ tale che:

$$
\alpha^z = \gamma^\delta \beta^{-\gamma}
$$

Da questa espressione, per ricavare $z$, l'avversario dovrebbe calcolare:

$$
z = \log_{\alpha}(\gamma^\delta \beta^{-\gamma})
$$

cioè ancora un logaritmo discreto.

Anche se riuscisse a ottenere tale valore $z$, dovrebbe poi trovare un messaggio $M$ tale che:

$$
\operatorname{SHA}(M)=z
$$

cioè dovrebbe invertire la funzione hash:

$$
M \in \operatorname{SHA}^{-1}(z)
$$

L'attacco richiede quindi due capacità considerate impraticabili:

- risolvere un logaritmo discreto;
- invertire SHA su un valore dato.

> ⚠️ L'existential forgery key-only contro DSA non è banale come in RSA textbook: anche generare una coppia qualsiasi valida richiede sia un problema di logaritmo discreto sia una preimmagine della funzione hash.

---

### **6. Parametri globali e chiavi individuali**

Nel DSA bisogna distinguere tra:

- **parametri globali**;
- **chiavi individuali**.

I parametri globali sono:

$$
p,\ q,\ \alpha
$$

Questi valori possono essere condivisi da un gruppo di utenti. Possono, ad esempio, essere generati da un'autorità e poi usati da più soggetti.

Ogni singolo utente deve invece generare:

- una chiave privata $s$;
- una chiave pubblica $\beta$.

La generazione della chiave individuale è molto semplice:

1. l'utente sceglie un valore casuale segreto $s$ con $0<s<q$;
2. calcola:

$$
\beta = \alpha^s \bmod p
$$

Quindi, una volta fissati i parametri globali, generare una coppia di chiavi DSA per un utente richiede essenzialmente una sola esponenziazione modulare.

> ✅ La sicurezza dello schema per il singolo utente dipende dal segreto $s$. I parametri $p$, $q$ e $\alpha$ possono essere comuni, purché siano stati generati correttamente.

---

### **7. Confronto con RSA nella generazione delle chiavi**

La generazione delle chiavi DSA, una volta disponibili i parametri globali, è più efficiente della generazione di una chiave RSA.

Nel caso RSA, ogni utente deve generare due grandi primi:

$$
p
$$

e:

$$
q
$$

calcolare:

$$
n=pq
$$

determinare $\varphi(n)$ e scegliere gli esponenti pubblico e privato.

Nel DSA, invece, se i parametri globali sono già stati scelti, l'utente deve soltanto:

- scegliere $s$;
- calcolare $\beta=\alpha^s \bmod p$.

Il costo individuale di generazione delle chiavi è quindi molto ridotto.

> 💡 La parte pesante della generazione DSA è nella scelta dei parametri globali. Una volta fissati, ogni utente genera la propria chiave in modo rapido.

---

### **8. Prestazioni: tabelle sperimentali**

Il docente mostra alcune tabelle di confronto tra RSA e DSA.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le tabelle confrontano tempi di firma e verifica, considerando:

- diverse lunghezze di chiave;
- implementazioni su processori dell'epoca;
- misure espresse come tempo per operazione o come numero di firme/verifiche al secondo.

Il punto importante non è memorizzare i singoli numeri, ma capire il comportamento qualitativo dei due schemi.

---

### **9. Verifica RSA e verifica DSA**

RSA è generalmente molto veloce nella fase di verifica.

Il motivo è che, in RSA, l'esponente pubblico:

$$
e
$$

viene spesso scelto piccolo. Di conseguenza, la verifica:

$$
F^e \bmod n
$$

richiede un'esponenziazione modulare relativamente economica.

Nel DSA, invece, la verifica richiede il calcolo di:

$$
\alpha^{e'}\beta^{e''} \bmod p
$$

cioè due esponenziazioni modulari, più una moltiplicazione modulo $p$ e una riduzione modulo $q$.

Per questo, la verifica DSA è in genere più pesante della verifica RSA.

> 📌 RSA è spesso progettato per avere verifiche rapide, mentre DSA richiede una verifica algebricamente più articolata.

---

### **10. Firma RSA e firma DSA**

La fase di firma ha un comportamento diverso.

In RSA, la firma richiede l'uso dell'esponente privato:

$$
d
$$

che è grande. La firma:

$$
F=M^d \bmod n
$$

richiede quindi un'esponenziazione modulare costosa.

In DSA, la firma richiede:

- un'esponenziazione modulare per calcolare $\alpha^r \bmod p$;
- il calcolo di un inverso modulo $q$;
- alcune moltiplicazioni e addizioni modulo $q$;
- il calcolo dell'hash del messaggio.

Tuttavia, DSA consente una precomputazione importante. Prima di conoscere il messaggio, si possono già preparare:

$$
r
$$

$$
r^{-1} \bmod q
$$

e:

$$
\gamma = (\alpha^r \bmod p) \bmod q
$$

Quando il messaggio è disponibile, resta da calcolare:

$$
\delta = (\operatorname{SHA}(M)+s\gamma)r^{-1} \bmod q
$$

In RSA, invece, non c'è una precomputazione analoga indipendente dal messaggio: la firma deve essere calcolata quando il messaggio da firmare è disponibile.

> ✅ La firma DSA può essere più veloce della firma RSA proprio perché una parte rilevante del lavoro può essere anticipata.

---

### **11. Critica prestazionale al DSA**

Il fatto che DSA renda più veloce la generazione della firma rispetto alla verifica è stato oggetto di critiche.

Il motivo è applicativo: una firma viene generata una volta sola, ma può essere verificata molte volte.

Per esempio, un documento firmato può essere inviato a più destinatari, archiviato, controllato da autorità diverse o verificato in momenti successivi. In questi scenari, il costo della verifica pesa più del costo della generazione.

RSA, con esponente pubblico piccolo, è spesso più vantaggioso proprio quando le verifiche sono molto numerose.

DSA, invece, è vantaggioso quando è importante velocizzare la firma, specialmente se la precomputazione è sfruttabile.

> ⚠️ L'efficienza di uno schema di firma non si valuta solo guardando la firma: bisogna considerare quante volte la firma verrà verificata.

---

### **12. Sicurezza pratica del valore casuale $r$**

Il transcript si concentra sugli attacchi legati al logaritmo discreto e alla funzione hash, ma è utile richiamare un punto essenziale già emerso nella descrizione del DSA: il valore casuale $r$ è critico.

Ogni firma deve usare un nuovo valore:

$$
r
$$

segreto e non riutilizzato.

Se $r$ viene riutilizzato in due firme diverse, oppure se è generato in modo prevedibile, la chiave privata $s$ può diventare recuperabile.

Quindi la sicurezza pratica di DSA richiede:

- parametri globali corretti;
- segreto $s$ protetto;
- funzione hash robusta;
- generazione sicura e non ripetuta del valore $r$.

> 📌 La sicurezza teorica basata sul logaritmo discreto non salva un'implementazione che riusa o predice il nonce $r$.

---

### **13. Riepilogo**

In questa lezione abbiamo visto diverse considerazioni sulla sicurezza e sulle prestazioni del DSS/DSA.

I punti fondamentali sono:

- un total break key-only richiede di recuperare $s$ da $\beta=\alpha^s \bmod p$;
- recuperare $s$ equivale a risolvere un logaritmo discreto;
- una selective forgery su un messaggio dato porta ancora a una relazione di logaritmo discreto;
- una existential forgery richiede sia un logaritmo discreto sia l'inversione della funzione SHA;
- i parametri $p$, $q$ e $\alpha$ possono essere globali e condivisi;
- ogni utente sceglie il proprio segreto $s$ e calcola $\beta=\alpha^s \bmod p$;
- la generazione delle chiavi individuali DSA è molto efficiente;
- RSA tende ad avere verifiche più veloci grazie a esponenti pubblici piccoli;
- DSA può avere firme più veloci grazie alla precomputazione;
- la verifica DSA è più pesante perché richiede due esponenziazioni modulari;
- la maggiore velocità della firma DSA è stata criticata perché una firma può essere verificata molte volte;
- il valore casuale $r$ deve essere generato correttamente e mai riutilizzato.

> ✅ La sicurezza del DSA si basa sull'intrattabilità del logaritmo discreto, ma la sicurezza effettiva dello schema dipende anche dalla funzione hash, dalla gestione dei parametri e dalla qualità del valore casuale usato in ogni firma.
