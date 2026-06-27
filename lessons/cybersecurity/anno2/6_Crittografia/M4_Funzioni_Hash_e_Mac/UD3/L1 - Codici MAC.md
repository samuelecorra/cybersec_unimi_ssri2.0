## **Lezione 1: Codici MAC**

### **1. Introduzione**

In questa lezione vengono introdotte le funzioni **MAC**, acronimo di:

> **Message Authentication Code**

cioè **codice di autenticazione del messaggio**.

Le funzioni MAC hanno alcune somiglianze con le funzioni hash:

- prendono in input un messaggio di lunghezza variabile;
- producono un valore di uscita di lunghezza fissata;
- possono essere viste come impronte compatte del messaggio;
- servono per controllare che un messaggio non sia stato alterato.

La differenza fondamentale è però la presenza di una **chiave segreta condivisa**.

Una funzione hash ordinaria calcola:

$$
h = H(M)
$$

usando solo il messaggio.

Una funzione MAC calcola invece:

$$
Y = MAC_K(M)
$$

oppure:

$$
Y = MAC(M,K)
$$

usando:

- il messaggio $M$;
- una chiave segreta $K$ condivisa da mittente e destinatario.

> 📌 Un MAC è un'impronta del messaggio calcolata anche in funzione di una chiave segreta. Senza la chiave, non deve essere possibile produrre un MAC valido per un nuovo messaggio.

---

### **2. Definizione di Codice MAC**

Un codice MAC è una funzione crittografica che prende in input:

- un messaggio $M$ di lunghezza variabile;
- una chiave segreta $K$ di lunghezza fissata.

La chiave ha lunghezza:

$$
k \text{ bit}
$$

Il messaggio può avere lunghezza arbitraria, purché finita.

L'output è una stringa di lunghezza fissata:

$$
n \text{ bit}
$$

Quindi:

$$
MAC : \{0,1\}^* \times \{0,1\}^k \to \{0,1\}^n
$$

Il valore prodotto:

$$
Y = MAC(M,K)
$$

è chiamato:

- MAC;
- tag;
- codice di autenticazione;
- etichetta di autenticazione.

> 📌 Il MAC comprime un messaggio arbitrariamente lungo in un valore corto, ma diversamente da un hash ordinario usa una chiave segreta.

---

### **3. Obiettivi di un MAC**

Le applicazioni tipiche di una funzione MAC sono:

- autenticità del messaggio;
- integrità del messaggio.

#### **3.1. Autenticità**

L'autenticità significa che il destinatario può convincersi che il messaggio provenga da qualcuno che conosce la chiave segreta.

Se Alice e Bob sono gli unici a conoscere $K$, e Bob riceve un MAC valido, allora Bob conclude che il messaggio è stato generato da Alice o comunque da qualcuno che conosce $K$.

#### **3.2. Integrità**

L'integrità significa che il messaggio non è stato modificato durante la trasmissione.

Se un attaccante modifica anche solo una parte del messaggio, il MAC ricalcolato da Bob non coinciderà più con quello ricevuto.

#### **3.3. Cosa un MAC non Garantisce**

Un MAC non garantisce, da solo:

- confidenzialità;
- non ripudio;
- segretezza del messaggio.

La confidenzialità richiede cifratura.

Il non ripudio richiede tipicamente firme digitali, perché nel MAC mittente e destinatario condividono la stessa chiave.

> ⚠️ Un MAC autentica il messaggio rispetto a una chiave condivisa, ma non prova a terzi che solo Alice potesse averlo prodotto: anche Bob conosce la stessa chiave.

---

### **4. Schema Alice-Bob**

Supponiamo che Alice e Bob vogliano scambiarsi un messaggio in modo autentico e integro.

Alice e Bob condividono una chiave segreta:

$$
K
$$

Questa chiave deve essere nota solo a loro.

#### **4.1. Generazione del MAC**

Quando Alice vuole inviare il messaggio $M$, calcola:

$$
Y = MAC(M,K)
$$

Poi invia a Bob:

$$
(M,Y)
$$

cioè il messaggio concatenato o accompagnato dal suo codice MAC.

#### **4.2. Verifica del MAC**

Bob riceve:

$$
(M,Y)
$$

Bob conosce la chiave $K$.

Quindi ricalcola:

$$
Y' = MAC(M,K)
$$

Poi confronta:

$$
Y' \stackrel{?}{=} Y
$$

Se:

$$
Y' = Y
$$

Bob accetta il messaggio.

Se:

$$
Y' \ne Y
$$

Bob rifiuta il messaggio.

#### **4.3. Interpretazione del Controllo**

Se i due valori coincidono, Bob conclude che:

- il messaggio non è stato modificato;
- il tag è coerente con la chiave condivisa;
- chi ha prodotto il tag conosceva $K$.

Nel modello in cui solo Alice e Bob conoscono $K$, Bob interpreta questo come autenticità del messaggio rispetto ad Alice.

> ✅ Il controllo del MAC è una verifica locale: Bob non deve chiedere a terzi, deve solo ricalcolare il tag con la chiave condivisa.

---

### **5. Ruolo di Trudy o Oscar**

Nel modello di sicurezza, l'attaccante può osservare o modificare il canale.

Indichiamolo con Oscar o Trudy.

L'attaccante può vedere coppie:

$$
(M,Y)
$$

dove:

$$
Y = MAC(M,K)
$$

ma non conosce la chiave $K$.

Il suo obiettivo può essere:

- modificare un messaggio mantenendo un MAC valido;
- creare un nuovo messaggio con MAC valido;
- recuperare la chiave;
- ingannare Bob facendogli accettare un messaggio non prodotto da Alice.

La sicurezza del MAC consiste nel rendere computazionalmente impraticabili questi obiettivi.

---

### **6. Proprietà Fondamentali di una Funzione MAC**

Il transcript elenca le principali proprietà richieste a una funzione MAC.

#### **6.1. Facilità di Calcolo**

Dato:

- il messaggio $M$;
- la chiave segreta $K$;

deve essere facile calcolare:

$$
MAC(M,K)
$$

Questo è necessario perché mittente e destinatario devono poter usare il MAC in modo efficiente.

#### **6.2. Lunghezza Finita e Compressione**

Il messaggio $M$ può avere lunghezza arbitraria, ma finita.

Il MAC deve invece avere lunghezza fissata:

$$
n \text{ bit}
$$

Questa è una proprietà di compressione:

$$
M \in \{0,1\}^*
$$

ma:

$$
MAC(M,K) \in \{0,1\}^n
$$

> 📌 Come una funzione hash, anche un MAC comprime input lunghi in un output corto; a differenza dell'hash, la compressione dipende da una chiave segreta.

#### **6.3. Computational Resistance**

La proprietà centrale è la resistenza alla falsificazione.

Anche conoscendo più coppie:

$$
(M_1,Y_1), (M_2,Y_2), \ldots, (M_q,Y_q)
$$

dove:

$$
Y_i = MAC(M_i,K)
$$

non deve essere possibile calcolare una nuova coppia valida:

$$
(M^*,Y^*)
$$

con:

$$
M^* \notin \{M_1,M_2,\ldots,M_q\}
$$

e:

$$
Y^* = MAC(M^*,K)
$$

In altre parole, vedere MAC validi su messaggi precedenti non deve permettere di costruire il MAC di un nuovo messaggio.

#### **6.4. Non Recupero della Chiave**

Data una o più coppie messaggio-MAC:

$$
(M_i,Y_i)
$$

non deve essere possibile risalire alla chiave:

$$
K
$$

Questa proprietà è fondamentale perché, se l'attaccante recupera $K$, può calcolare MAC validi per qualunque messaggio.

> ⚠️ Recuperare la chiave è l'attacco più grave: da quel momento l'attaccante può impersonare qualunque partecipante che usa quella chiave.

---

### **7. MAC e Confidenzialità**

Il transcript sottolinea un punto essenziale:

> il MAC garantisce autenticazione e integrità, ma non confidenzialità.

Se Alice invia:

$$
(M, MAC(M,K))
$$

il messaggio $M$ è ancora in chiaro.

Chiunque osservi il canale può leggere $M$.

Il MAC impedisce di modificarlo senza essere scoperti, ma non lo nasconde.

#### **7.1. Aggiungere Cifratura**

Se si vuole anche confidenzialità, bisogna cifrare.

Si usa una chiave diversa:

$$
K'
$$

condivisa tra Alice e Bob.

È buona pratica distinguere:

- la chiave $K$ per il MAC;
- la chiave $K'$ per la cifratura.

> ⚠️ Separare le chiavi evita interazioni pericolose tra primitive diverse.

#### **7.2. MAC sul Testo Cifrato**

Una possibilità è cifrare il messaggio e poi autenticare il testo cifrato.

Alice calcola:

$$
C = E_{K'}(M)
$$

poi:

$$
Y = MAC(C,K)
$$

e invia:

$$
(C,Y)
$$

Bob verifica prima il MAC sul testo cifrato.

Se il MAC è valido, decifra:

$$
M = D_{K'}(C)
$$

#### **7.3. MAC sul Testo in Chiaro e Cifratura del Tutto**

Un'altra possibilità descritta nel transcript è calcolare il MAC sul messaggio in chiaro e poi cifrare tutto.

Alice calcola:

$$
Y = MAC(M,K)
$$

poi cifra:

$$
C = E_{K'}(M \parallel Y)
$$

e invia $C$.

Bob decifra, recupera $M$ e $Y$, poi verifica il MAC.

#### **7.4. Nota sulle Composizioni**

La lezione presenta entrambe le possibilità come modi per combinare MAC e cifratura.

In protocolli moderni, la composizione va progettata con molta attenzione.

Il principio didattico qui è:

> se serve confidenzialità, il MAC da solo non basta; bisogna aggiungere cifratura con una chiave appropriata.

---

### **8. Sicurezza di uno Schema MAC**

Per descrivere la sicurezza di uno schema MAC si considerano due aspetti:

- lo scopo dell'attacco;
- le capacità dell'attaccante.

Il transcript presenta entrambe le classificazioni.

---

### **9. Scopi dell'Attacco**

#### **9.1. Total Break**

Si parla di **total break** quando l'attaccante riesce a determinare la chiave segreta:

$$
K
$$

Questo è l'attacco più forte.

Se Oscar conosce $K$, può calcolare:

$$
MAC(M,K)
$$

per qualunque messaggio $M$.

Quindi può generare tutti i tag che vuole.

#### **9.2. Selective Forgery**

Si parla di **selective forgery** quando l'attaccante riesce a determinare il MAC corretto per un messaggio scelto in anticipo.

Dato un messaggio specifico:

$$
M^*
$$

l'attaccante riesce a calcolare:

$$
Y^* = MAC(M^*,K)
$$

senza conoscere $K$.

Questo è meno forte del total break, ma comunque grave.

#### **9.3. Existential Forgery**

Si parla di **existential forgery** quando l'attaccante riesce a produrre una qualunque coppia valida:

$$
(M^*,Y^*)
$$

tale che:

$$
Y^* = MAC(M^*,K)
$$

L'attaccante non controlla necessariamente quale messaggio $M^*$ verrà falsificato.

Gli basta produrre almeno una coppia nuova che venga accettata.

> 📌 La sicurezza moderna dei MAC punta a impedire anche l'existential forgery sotto chosen message attack, perché è la nozione più prudente tra quelle discusse.

---

### **10. Capacità dell'Attaccante**

Gli attacchi possono essere classificati anche in base alla conoscenza o all'accesso dell'avversario.

#### **10.1. Known Message Attack**

Nel transcript viene chiamato anche "non message attack" per probabile errore di trascrizione.

Il caso corretto è **known message attack**.

L'attaccante conosce una lista di messaggi e i relativi MAC:

$$
(M_1,Y_1), \ldots, (M_q,Y_q)
$$

Non ha scelto lui i messaggi.

Li osserva soltanto.

#### **10.2. Chosen Message Attack**

Nel **chosen message attack**, l'attaccante può scegliere alcuni messaggi:

$$
M_1, M_2, \ldots, M_q
$$

e ottenere i rispettivi MAC:

$$
Y_i = MAC(M_i,K)
$$

Il transcript descrive questo caso dicendo che Oscar può chiedere ad Alice o Bob di computare i MAC relativi a messaggi scelti.

#### **10.3. Adaptive Chosen Message Attack**

Nel **adaptive chosen message attack**, l'attaccante interagisce in modo adattivo.

Questo significa:

- sceglie un primo messaggio;
- ottiene il MAC;
- usa la risposta per scegliere il messaggio successivo;
- ripete il processo.

Quindi i messaggi scelti non sono fissati tutti all'inizio.

Dipendono dalle risposte ottenute.

Questo è il modello di attacco più forte tra quelli elencati.

> ⚠️ Un MAC sicuro deve restare sicuro anche se l'attaccante può ottenere MAC validi per messaggi scelti da lui, purché non chieda direttamente il MAC del messaggio che poi vuole falsificare.

---

### **11. Ricerca Esaustiva della Chiave**

Una prima tecnica di attacco è la ricerca esaustiva della chiave.

Supponiamo che:

- la chiave abbia lunghezza $k$ bit;
- il MAC abbia lunghezza $n$ bit.

Lo spazio delle chiavi ha dimensione:

$$
2^k
$$

Lo spazio dei MAC ha dimensione:

$$
2^n
$$

L'attaccante conosce alcune coppie:

$$
(M_i,Y_i)
$$

dove:

$$
Y_i = MAC(M_i,K)
$$

Vuole trovare $K$ provando tutte le chiavi possibili.

#### **11.1. Prima Coppia**

L'attaccante prende la prima coppia:

$$
(M_1,Y_1)
$$

Per ogni chiave candidata $K'$ calcola:

$$
MAC(M_1,K')
$$

e controlla se:

$$
MAC(M_1,K') = Y_1
$$

In media, se il MAC si comporta come una funzione casuale, una frazione:

$$
2^{-n}
$$

delle chiavi produrrà il tag corretto.

Quindi tra $2^k$ chiavi rimangono circa:

$$
2^{k-n}
$$

chiavi candidate.

#### **11.2. Seconda Coppia**

Con una seconda coppia:

$$
(M_2,Y_2)
$$

l'attaccante filtra di nuovo le chiavi candidate.

Rimangono circa:

$$
2^{k-2n}
$$

chiavi.

#### **11.3. Caso Generale**

Dopo $r$ coppie indipendenti, il numero atteso di chiavi candidate è:

$$
2^{k-rn}
$$

Per arrivare a circa una sola chiave candidata, serve:

$$
k-rn \approx 0
$$

cioè:

$$
r \approx \frac{k}{n}
$$

Se:

$$
k = \beta n
$$

allora servono circa:

$$
\beta
$$

coppie o round di filtraggio.

> 📌 Ogni coppia messaggio-MAC elimina in media un fattore $2^n$ di chiavi candidate.

#### **11.4. Esempio Numerico**

Il transcript propone:

$$
k = 80
$$

e:

$$
n = 32
$$

Dopo la prima coppia rimangono circa:

$$
2^{80-32} = 2^{48}
$$

chiavi candidate.

Dopo la seconda coppia rimangono circa:

$$
2^{80-64} = 2^{16}
$$

chiavi candidate.

Dopo la terza coppia:

$$
2^{80-96} = 2^{-16}
$$

quindi ci si aspetta di aver isolato la chiave corretta.

Naturalmente, lo sforzo principale resta provare molte chiavi.

La prima fase richiede comunque un lavoro dell'ordine di:

$$
2^k
$$

tentativi.

---

### **12. Ricerca Esaustiva sul Valore del MAC**

Un altro attacco non cerca la chiave.

Cerca direttamente di indovinare il MAC corretto per un messaggio.

Supponiamo che l'attaccante voglia inviare un messaggio:

$$
M^*
$$

senza conoscere $K$.

Deve produrre:

$$
Y^* = MAC(M^*,K)
$$

Se il tag ha lunghezza $n$ bit, esistono:

$$
2^n
$$

possibili valori.

La probabilità di indovinare al primo tentativo è:

$$
\frac{1}{2^n}
$$

Quindi la ricerca esaustiva sul tag richiede uno sforzo dell'ordine:

$$
2^n
$$

tentativi.

#### **12.1. Confronto con Ricerca sulla Chiave**

L'attaccante può scegliere tra:

- provare a recuperare la chiave, costo circa $2^k$;
- provare a indovinare direttamente il tag, costo circa $2^n$.

Lo sforzo generico migliore è quindi:

$$
\min(2^k,2^n)
$$

Per questo non basta avere una chiave lunga se il tag è troppo corto.

E non basta avere un tag lungo se la chiave è troppo corta.

> ⚠️ La sicurezza generica di un MAC è limitata dal minimo tra dimensione della chiave e dimensione del tag.

---

### **13. Scelta delle Dimensioni**

Per rendere impraticabili gli attacchi esaustivi, bisogna scegliere:

- chiavi abbastanza lunghe;
- tag abbastanza lunghi.

Il transcript conclude che è consigliabile avere dimensioni minime dell'ordine di:

$$
128 \text{ bit}
$$

Quindi:

$$
k \ge 128
$$

e idealmente:

$$
n \ge 128
$$

o comunque un tag sufficientemente lungo per il livello di sicurezza richiesto.

In applicazioni pratiche, talvolta i tag vengono troncati.

In quel caso bisogna ricordare che la probabilità di forgery casuale aumenta.

Un tag da $32$ bit, per esempio, è troppo corto per molti contesti moderni, perché un attaccante può provare molti tentativi.

> 📌 Parametri piccoli possono essere sicuri solo in scenari molto limitati; per sicurezza crittografica generale si usano chiavi e tag con margini adeguati.

---

### **14. MAC, Hash e Firme Digitali**

È utile confrontare MAC, hash e firme digitali.

#### **14.1. Hash**

Una funzione hash calcola:

$$
H(M)
$$

Non usa chiave.

Garantisce un'impronta del messaggio, ma chiunque può calcolarla.

Da sola non autentica il mittente.

#### **14.2. MAC**

Un MAC calcola:

$$
MAC(M,K)
$$

Usa una chiave segreta condivisa.

Garantisce autenticità e integrità rispetto ai soggetti che conoscono $K$.

#### **14.3. Firma Digitale**

Una firma digitale usa una chiave privata per firmare e una chiave pubblica per verificare.

Fornisce anche non ripudio, perché il verificatore non conosce la chiave privata del firmatario.

Nel MAC, invece, Bob conosce la stessa chiave di Alice.

Quindi Bob potrebbe generare un MAC valido.

Per questo un MAC non fornisce non ripudio verso terzi.

---

### **15. Errori Concettuali da Evitare**

#### **15.1. Pensare che il MAC Cifri il Messaggio**

Il MAC non cifra.

Il messaggio resta leggibile se viene inviato in chiaro.

#### **15.2. Pensare che il MAC Sia un Hash Senza Differenze**

Un MAC assomiglia a un hash perché produce un output corto.

Ma usa una chiave segreta.

Questa chiave è ciò che permette l'autenticazione.

#### **15.3. Pensare che Integrità Implichi Confidenzialità**

Un messaggio può essere integro e autentico ma visibile a tutti.

Per nasconderlo serve cifratura.

#### **15.4. Pensare che un Tag Corto Sia Sufficiente**

Un tag corto può essere indovinato con probabilità non trascurabile se l'attaccante può fare molti tentativi.

#### **15.5. Confondere Selective ed Existential Forgery**

Nella selective forgery l'attaccante vuole falsificare un messaggio specifico.

Nella existential forgery basta trovare una qualunque nuova coppia valida.

---

### **16. Conclusione**

I codici MAC, **Message Authentication Code**, sono strumenti crittografici per garantire autenticità e integrità dei messaggi.

Un MAC prende in input:

- un messaggio $M$ di lunghezza variabile;
- una chiave segreta condivisa $K$ di lunghezza fissata;

e produce:

- un tag $Y$ di lunghezza fissata.

Nel protocollo base, Alice invia:

$$
(M,Y)
$$

dove:

$$
Y = MAC(M,K)
$$

Bob ricalcola il MAC con la stessa chiave e accetta solo se i due valori coincidono.

La sicurezza richiede che, anche osservando molte coppie messaggio-MAC, l'attaccante non possa:

- recuperare la chiave;
- calcolare il MAC di un messaggio scelto;
- produrre una nuova coppia valida.

Il MAC non fornisce confidenzialità.

Se serve segretezza del contenuto, bisogna combinarlo con cifratura usando una chiave distinta.

Infine, gli attacchi esaustivi mostrano che la sicurezza generica dipende dal minimo tra:

$$
2^k
$$

e:

$$
2^n
$$

Per questo chiave e tag devono avere dimensioni sufficientemente grandi.

---

### **17. Recap per l'Esame**

> ✅ MAC significa Message Authentication Code.

> ✅ Un MAC prende in input messaggio $M$ e chiave segreta $K$ e produce un tag $Y$ di lunghezza fissata.

> ✅ La chiave $K$ è condivisa tra mittente e destinatario.

> ✅ Il MAC garantisce autenticità e integrità, non confidenzialità.

> ✅ Alice invia $(M,Y)$ con $Y = MAC(M,K)$; Bob ricalcola il MAC e confronta.

> ✅ Un buon MAC deve essere facile da calcolare, comprimere input arbitrari in output fisso, resistere alla falsificazione e non permettere il recupero della chiave.

> ✅ Gli scopi d'attacco sono total break, selective forgery ed existential forgery.

> ✅ Le capacità dell'attaccante includono known message attack, chosen message attack e adaptive chosen message attack.

> ✅ Con una coppia messaggio-MAC, una ricerca esaustiva sulla chiave lascia circa $2^{k-n}$ chiavi candidate.

> ✅ Dopo $r$ coppie, rimangono circa $2^{k-rn}$ candidati.

> ✅ Provare direttamente il tag ha probabilità di successo $1/2^n$ per tentativo.

> ✅ Lo sforzo generico è limitato da $\min(2^k,2^n)$.

> ⚠️ Per sicurezza moderna è opportuno usare chiavi di almeno $128$ bit e tag sufficientemente lunghi per il contesto.
