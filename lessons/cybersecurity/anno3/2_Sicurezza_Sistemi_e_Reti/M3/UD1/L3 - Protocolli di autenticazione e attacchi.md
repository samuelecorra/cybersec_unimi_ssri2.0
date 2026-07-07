## **Lezione 3: Protocolli di autenticazione e attacchi**

### **1. Introduzione**

L’autenticazione su **canali non sicuri** (come Internet) richiede meccanismi più robusti delle semplici password statiche.  
Questa lezione presenta i **principali protocolli di autenticazione sicura** — **One-Time Password**, **Challenge-Response** e **Zero-Knowledge Proof** — insieme alle **tecniche di attacco** più comuni e alle **contromisure** per prevenirle.

Il problema centrale è impedire che un avversario, dopo aver intercettato il traffico tra client e server, possa riutilizzare le informazioni osservate per autenticarsi al posto della vittima. Per questo i protocolli robusti cercano di fare in modo che ogni prova di autenticazione sia **fresca**, **non riutilizzabile** e legata a un segreto che l’attaccante non possiede.

---

### **2. One-Time Password (OTP)**

Una **One-Time Password** è una password **valida per una sola sessione o transazione**.  
Viene utilizzata per **difendersi dagli attacchi passivi**, come l’intercettazione del traffico di rete, poiché una password già usata non può essere riutilizzata.

Gli schemi OTP richiedono normalmente una forma di sincronizzazione tra utente e server: può essere una sincronizzazione temporale, una lista precondivisa di password monouso oppure un contatore condiviso che indica quale password deve essere usata nel round corrente.

#### **Vantaggi**

- Elimina il rischio di **replay attack**.
    
- Impedisce l’uso di **password intercettate**.
    
- È ideale per sistemi **ad alto rischio** di sniffing o compromissione.
    

#### **Implementazioni comuni**

1. **OTP sincronizzato nel tempo (TOTP)**
    
    - Basato su un **orologio condiviso** tra utente e server.
        
    - La password è valida solo per un breve intervallo di tempo (es. 30 s).
        
    - Esempio: Google Authenticator.
        
2. **OTP basato su elenco precondiviso**
    
    - Utente e server condividono **una lista di password monouso** generate in anticipo.
        
    - Ogni password viene invalidata dopo l’uso.
        
3. **OTP tramite catena di hash (Lamport)**
    
    - Basato su una **sequenza di hash unidirezionali**:  
        $h(s),\ h(h(s)),\ h(h(h(s))),\ ...,\ h^{(n)}(s)$
        
    - Le password vengono utilizzate in **ordine inverso**: il server memorizza inizialmente l’ultimo valore della catena, mentre l’utente presenta via via i valori precedenti, verificabili applicando una sola volta la funzione hash.
        

---

### **3. Lamport’s One-Time Password**

Il **protocollo Lamport OTP** è una delle implementazioni più note delle password monouso.

#### **Configurazione iniziale**

1. Il client $A$ sceglie:
    
    - un valore segreto $w$,
        
    - una funzione di hash $H()$,
        
    - e un numero intero $t$ (numero massimo di autenticazioni).
        
2. Calcola $w_0 = H^{(t)}(w)$ e lo invia al server $B$, che lo memorizza.

Il valore $w$ resta noto solo al client: il server memorizza soltanto un valore derivato dopo $t$ applicazioni della funzione hash. La sicurezza dipende dalla non invertibilità di $H$: conoscere $H(x)$ non deve permettere di risalire a $x$.
    

#### **Protocollo operativo**

Per la $i$-esima autenticazione ($1 < i < t$):

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

1. $A$ invia a $B$:  
    $(A, i, w_i = H^{(t - i)}(w))$
    
2. $B$ verifica:
    
    - che $i$ corrisponda al contatore atteso,
        
    - e che $H(w_i) = w_{i-1}$.
        
3. Se entrambe le condizioni sono vere, $B$ accetta l’autenticazione e incrementa $i_A$.
    

Dopo un’autenticazione riuscita, il server aggiorna il proprio database sostituendo il vecchio valore memorizzato con quello appena ricevuto. Al round successivo accetterà solo il nuovo predecessore nella catena.

> 💡 In questo modo, anche se un avversario intercetta $w_i$, non può calcolare il valore successivo da presentare al server, perché dovrebbe invertire la funzione hash.

---

### **4. Protocolli Challenge-Response**

Nei protocolli **Challenge-Response**, il server invia una **sfida (challenge)** casuale e l’utente deve rispondere con un valore **dipendente dalla sfida e da un segreto condiviso**.  
Questo metodo previene i **replay attack** poiché ogni sfida è diversa.

La sfida può essere un timestamp, un nonce o un altro valore fresco. Il punto comune è che la risposta deve dimostrare la conoscenza di un segreto, ad esempio una chiave condivisa $K$, senza trasmettere direttamente tale segreto.

#### **a. Autenticazione unilaterale basata su timestamp**

- Da $A$ a $B$:  
    $MAC_K(t_A, B)$  
    dove $t_A$ è un timestamp e $MAC_K$ una funzione autenticata con chiave $K$.

Il timestamp viene incluso nel MAC per legare la prova di autenticazione a un intervallo temporale limitato. Un messaggio vecchio, anche se formalmente corretto, deve essere rifiutato fuori dalla finestra di validità.
    

#### **b. Autenticazione unilaterale basata su nonce**

1. Da $B$ ad $A$: invia un numero casuale $r_B$.
    
2. Da $A$ a $B$: risponde con $MAC_K(r_B, B)$.  
    → Solo chi conosce la chiave $K$ può generare la risposta corretta.
	    
In questo caso la freschezza non dipende dall’orologio, ma dal nonce generato dal server. L’attaccante può anche osservare una risposta valida, ma non può riutilizzarla per una sfida diversa.


#### **c. Autenticazione reciproca basata su nonce**

1. $B \to A$: invia $r_B$.
    
2. $A \to B$: invia $r_A, MAC_K(r_A, r_B, B)$.
    
3. $B \to A$: invia $MAC_K(r_B, r_A)$.
    

Entrambe le parti si autenticano a vicenda, assicurando **mutual authentication**.

L’uso di due nonce evita che solo una delle due parti sia autenticata: $B$ verifica che $A$ conosca $K$ e sappia rispondere a $r_B$, mentre $A$ verifica che $B$ conosca $K$ e sappia rispondere a $r_A$.

---

### **5. Autenticazione Zero-Knowledge Proof (ZKP)**

Una **Zero-Knowledge Proof (ZKP)** è un protocollo crittografico che consente a una parte (**prover**) di convincere un’altra (**verifier**) del possesso di un segreto **senza rivelarlo**.

La proprietà rilevante per l’autenticazione è che il verifier ottiene evidenza della conoscenza del segreto, ma i messaggi scambiati non contengono il segreto in forma riutilizzabile.

#### **Definizione**

> Una ZKP dimostra la conoscenza di un’informazione (es. una password o una chiave privata) senza mai divulgarla, neppure parzialmente.

#### **Esempio intuitivo**

Immagina un amico che afferma di saper aprire un **lucchetto a combinazione**:

- invece di rivelare la combinazione, apre semplicemente il lucchetto davanti a te;
    
- tu sei convinto della sua abilità, ma non conosci la combinazione.
    

Nel mondo digitale, l’utente dimostra di conoscere la password **senza inviarla**, evitando così di esporla a eventuali intercettatori.

#### **Applicazioni**

- Sistemi di autenticazione fortemente resistenti a intercettazione.
    
- Schemi di **autenticazione anonima** e **blockchain**.
    
- Autenticazione **senza trasmissione di segreti**.
    

---

### **6. Smart Token**

Gli **smart token** sono dispositivi fisici che memorizzano credenziali digitali e permettono **autenticazioni sicure**.

#### **Caratteristiche fisiche**

- Contengono un **microprocessore integrato**.
    
- Possono avere forma di **smart card**, **chiave USB**, **token OTP** o **dispositivo portatile**.
    
- Alcuni includono **tastiera e display** per l’interazione diretta (es. token bancari).
	    
Nei token con interfaccia minima l’utente spesso deve solo premere un pulsante e leggere un codice dal display, da inserire poi nel servizio di autenticazione. Il dispositivo svolge internamente il calcolo, mentre all’esterno viene esposto solo il valore necessario al protocollo.


#### **Interfacce**

- **Elettroniche:** comunicano con un lettore via **contatto** (chip) o **contactless** (NFC).
    
- **Manuali:** inserimento o lettura di codici OTP sul display.
    

#### **Categorie di autenticazione**

1. **Statica:** memorizza un segreto fisso.
    
2. **Generatore dinamico di password:** crea OTP temporanee.
    
3. **Challenge-Response:** calcola risposte a sfide inviate dal server.
    

---

### **7. Autenticazione biometrica**

L’autenticazione biometrica riconosce un individuo in base a **caratteristiche fisiche o comportamentali uniche**.

#### **Caratteristiche comuni**

- Facciali
    
- Impronte digitali
    
- Geometria della mano
    
- Retina o iride
    
- Firma grafometrica
    
- Voce
    

#### **Vantaggi**

- Difficile da duplicare o condividere.
    
- Non necessita di ricordare o digitare informazioni.
    

#### **Svantaggi**

- Tecnologicamente **complessa e costosa**.
    
- Possibilità di **falsi positivi o falsi negativi**.
    
- Problemi di **privacy** e **irreversibilità** in caso di compromissione (non si può “cambiare” un’impronta digitale).
    

---

### **8. Problemi di sicurezza dell’autenticazione**

|**Tipo di attacco**|**Descrizione**|
|---|---|
|**Intercettazione**|L’attaccante ascolta le comunicazioni per carpire password o token.|
|**Attacco host**|Colpisce il server che memorizza le credenziali o i modelli biometrici.|
|**Replay**|L’attaccante ripete una risposta valida precedentemente registrata.|
|**Attacco client**|Mira al dispositivo dell’utente, rubando le credenziali locali.|
|**Trojan horse**|Un’applicazione malevola si finge legittima per catturare password o codici.|
|**Denial of Service (DoS)**|Blocca il servizio di autenticazione tramite richieste massicce o malformate.|

Gli attacchi non riguardano solo il traffico di rete. Un avversario può osservare fisicamente l’utente mentre digita una password, colpire il database che memorizza password o template biometrici, compromettere il client con keylogger o trojan, oppure indurre la vittima a inserire credenziali in un contesto controllato dall’attaccante.

Nel caso biometrico il rischio è particolarmente delicato: se viene rubato un template o un codice derivato da una caratteristica fisica, non è possibile “cambiare” quell’attributo con la stessa semplicità con cui si cambia una password.

Per la disponibilità, l’obiettivo dell’attaccante può essere impedire l’uso del servizio di autenticazione, sommergendolo di richieste e rendendo impossibile l’accesso agli utenti legittimi.

---

### **9. Replay Attack**

Il **replay attack** consiste nel **riutilizzare messaggi o credenziali valide** per ottenere accessi fraudolenti.

Nel caso più semplice l’attaccante copia un messaggio valido e lo riproduce in seguito. Una variante più forte consiste nel bloccare o sopprimere il messaggio originale della vittima e inviare al suo posto la copia intercettata: in questo modo il sistema non vede due messaggi uguali, ma solo quello dell’attaccante.

#### **Esempi**

- Ripetizione di un messaggio con **timestamp ancora valido**.
    
- Soppressione del messaggio originale e successiva riproduzione, così da evitare che il sistema rilevi il doppione.
    
- Riproduzione “all’indietro” con cifratura simmetrica, rendendo difficile distinguere i messaggi originali da quelli replicati.

> ⚠️ La cifratura da sola non impedisce il replay: un messaggio cifrato può essere copiato e ritrasmesso anche se l’attaccante non ne comprende il contenuto.
    

---

### **10. Contromisure ai Replay Attack**

#### **Numero di sequenza**

- Ogni messaggio contiene un **numero progressivo**.
    
- Il sistema accetta solo messaggi con numeri nell’ordine corretto.
    
- Ogni parte mantiene traccia dell’ultimo numero ricevuto da ciascun interlocutore.

Il numero di sequenza deve essere protetto, ad esempio includendolo nel MAC o nel token autenticato. Se l’attaccante potesse modificarlo liberamente, potrebbe adattare un vecchio messaggio alla sequenza attesa.
    

#### **Timestamp**

- Ogni messaggio è accompagnato da un **marcatore temporale**.
    
- Il sistema accetta solo messaggi entro una finestra di tempo valida.
    
- Richiede **sincronizzazione precisa degli orologi**.

Durante la finestra di validità del timestamp, il server può memorizzare i messaggi già ricevuti e scartare eventuali duplicati. Questo limita il replay semplice, ma non basta contro un attaccante che riesce a sopprimere il messaggio originale e a far arrivare solo la propria copia.
    

#### **Protocolli Challenge-Response**

- Eliminano la possibilità di replay grazie all’uso di **sfide uniche (nonce)** generate per ogni sessione.

Il challenge-response è la contromisura più pulita quando si vuole evitare dipendenza forte dagli orologi: la risposta valida per un nonce non è riutilizzabile con un altro nonce, e solo chi conosce il segreto condiviso può calcolarla correttamente.
    

---

### **11. Sintesi finale**

In questa lezione abbiamo analizzato:

- i principali **protocolli di autenticazione sicura** (OTP, Challenge-Response, ZKP),
    
- il ruolo dei **token intelligenti** e dei **sistemi biometrici**,
    
- le **principali vulnerabilità** dei meccanismi di autenticazione,
    
- e le **contromisure contro i replay attack**.

- la necessità di proteggere non solo il canale, ma anche host, client, token, database delle credenziali e disponibilità del servizio.
    

> L’autenticazione sicura non si limita alla verifica dell’identità: richiede **protocollo, sincronizzazione e protezione del canale di comunicazione**.  
> In un sistema robusto, **ogni messaggio è unico, verificabile e non riutilizzabile**.
