## **Lezione 1: Schema RSA**

### **1. Obiettivo della lezione**

In questa lezione vediamo un primo schema di firma digitale basato sul **crittosistema RSA**.

RSA, per la sua struttura matematica, può essere adattato in modo naturale alla produzione di firme digitali. Nella cifratura RSA classica, un mittente usa la **chiave pubblica** del destinatario per cifrare e il destinatario usa la propria **chiave privata** per decifrare. Nel caso della firma digitale, il ruolo delle chiavi viene concettualmente invertito:

- il firmatario usa la propria **chiave privata** per generare la firma;
- chiunque usa la **chiave pubblica** del firmatario per verificare la firma.

> 📌 Nella firma RSA, la chiave privata serve a produrre un valore che solo il possessore della chiave privata dovrebbe poter generare, mentre la chiave pubblica serve a controllare pubblicamente la correttezza di quel valore.

---

### **2. Richiamo sulle chiavi RSA**

Consideriamo un utente Alice.

Alice possiede una coppia di chiavi RSA:

- una **chiave pubblica**;
- una **chiave privata**.

La chiave pubblica è formata da:

$$
(n,e)
$$

dove:

- $n$ è il modulo RSA;
- $e$ è l'esponente pubblico.

Il modulo $n$ è ottenuto come prodotto di due numeri primi grandi:

$$
n = p \cdot q
$$

La chiave privata contiene l'esponente privato:

$$
d
$$

calcolato come inverso moltiplicativo di $e$ modulo:

$$
\varphi(n) = (p-1)(q-1)
$$

cioè:

$$
e \cdot d \equiv 1 \pmod{\varphi(n)}
$$

Questa congruenza significa che esiste un intero $k$ tale che:

$$
e \cdot d = 1 + k\varphi(n)
$$

È proprio questa relazione tra $e$ e $d$ che permette a RSA di funzionare sia per cifratura/decifratura sia per firma/verifica.

> ⚠️ La chiave pubblica $(n,e)$ può essere distribuita a tutti. La chiave privata, e in particolare $d$, deve rimanere segreta. Anche i fattori $p$ e $q$ devono rimanere segreti, perché permettono di calcolare $\varphi(n)$ e quindi di ricavare $d$.

---

### **3. Differenza rispetto alla cifratura RSA**

Nella cifratura RSA, se Bob vuole ricevere un messaggio cifrato, pubblica la chiave:

$$
(n,e)
$$

e mantiene segreto $d$.

Un mittente cifra usando l'esponente pubblico $e$, mentre Bob decifra usando l'esponente privato $d$.

Nello schema di firma digitale RSA il punto di vista è diverso. Alice vuole dimostrare di essere l'autrice di una firma. Per farlo usa il valore che solo lei conosce:

$$
d
$$

Chiunque può poi verificare la firma usando la chiave pubblica di Alice:

$$
(n,e)
$$

Questa inversione è naturale perché la verifica deve essere pubblica: non avrebbe senso richiedere una chiave segreta per controllare una firma digitale, altrimenti solo Alice potrebbe verificare le proprie firme.

> 💡 Nella cifratura RSA la chiave pubblica protegge la riservatezza verso il proprietario della chiave privata. Nella firma RSA la chiave privata crea autenticità, mentre la chiave pubblica rende la verifica accessibile a tutti.

---

### **4. Firma RSA di un messaggio**

Supponiamo che Alice voglia firmare un messaggio numerico:

$$
M
$$

Per applicare direttamente RSA, il messaggio deve essere rappresentato come un intero minore del modulo:

$$
0 \le M < n
$$

Alice calcola la firma elevando il messaggio all'esponente privato $d$ modulo $n$:

$$
F = M^d \bmod n
$$

Il valore:

$$
F
$$

è la firma digitale RSA del messaggio $M$.

Il documento firmato viene rappresentato dalla coppia:

$$
(M,F)
$$

cioè dal messaggio originale e dal valore di firma allegato o posto in appendice al messaggio stesso.

> 📌 La firma $F$ dipende dal messaggio $M$. Se cambia il messaggio, in generale cambia anche il valore che deve essere prodotto dalla chiave privata.

---

### **5. Verifica della firma RSA**

La procedura di verifica è analoga, nella forma matematica, alla procedura di decifratura RSA.

Chi verifica possiede:

- il messaggio $M$;
- la firma $F$;
- la chiave pubblica di Alice $(n,e)$.

Il verificatore calcola:

$$
F^e \bmod n
$$

La firma è valida se:

$$
F^e \bmod n = M
$$

Sostituendo la definizione di $F$, si ottiene:

$$
F^e \equiv (M^d)^e \equiv M^{de} \pmod n
$$

Poiché $d$ è l'inverso moltiplicativo di $e$ modulo $\varphi(n)$, si ha:

$$
de \equiv 1 \pmod{\varphi(n)}
$$

e quindi, sotto le ipotesi usuali di RSA:

$$
M^{de} \equiv M \pmod n
$$

Per questo il verificatore recupera il messaggio $M$ elevando la firma all'esponente pubblico.

> ✅ La verifica ha successo quando il valore ottenuto dalla firma coincide con il messaggio allegato al documento firmato.

---

### **6. Schema con recupero del messaggio**

Nel caso appena descritto, la procedura di verifica non si limita a restituire `vero` o `falso`.

Calcolando:

$$
F^e \bmod n
$$

il verificatore ricostruisce direttamente il valore:

$$
M
$$

e lo confronta con il messaggio allegato.

Per questo motivo lo schema RSA diretto viene detto anche **schema di firma con recupero del messaggio**: il messaggio viene recuperato contemporaneamente alla verifica della firma.

Questo non accade in tutti gli schemi di firma digitale. In altri schemi, il messaggio deve essere fornito separatamente alla procedura di verifica e la firma non permette di ricostruirlo.

> 📌 In questa versione didattica di RSA, verificare significa anche recuperare il valore firmato dalla firma stessa.

---

### **7. Esempio numerico**

Consideriamo l'esempio del docente.

Siano:

$$
p = 47
$$

e:

$$
q = 71
$$

Allora:

$$
n = p \cdot q = 47 \cdot 71 = 3337
$$

La funzione di Eulero vale:

$$
\varphi(n) = (p-1)(q-1)
$$

quindi:

$$
\varphi(n) = 46 \cdot 70 = 3220
$$

Alice sceglie come esponente pubblico:

$$
e = 79
$$

e calcola l'esponente privato:

$$
d = 1019
$$

perché:

$$
79 \cdot 1019 \equiv 1 \pmod{3220}
$$

La chiave pubblica di Alice è:

$$
(n,e) = (3337,79)
$$

La chiave privata contiene:

$$
d = 1019
$$

oltre alle informazioni segrete necessarie alla gestione della chiave.

---

### **8. Firma dell'esempio**

Supponiamo che Alice voglia firmare il messaggio:

$$
M = 1570
$$

Il messaggio è minore del modulo:

$$
1570 < 3337
$$

quindi può essere firmato direttamente nello schema RSA didattico.

Alice calcola:

$$
F = M^d \bmod n
$$

cioè:

$$
F = 1570^{1019} \bmod 3337
$$

Il risultato è:

$$
F = 668
$$

Il documento firmato è quindi la coppia:

$$
(1570,668)
$$

dove:

- $1570$ è il messaggio originario;
- $668$ è la firma calcolata da Alice usando la propria chiave privata.

---

### **9. Verifica dell'esempio**

Un verificatore che riceve:

$$
(1570,668)
$$

usa la chiave pubblica di Alice:

$$
(3337,79)
$$

e calcola:

$$
668^{79} \bmod 3337
$$

Il risultato è:

$$
668^{79} \bmod 3337 = 1570
$$

Questo valore coincide esattamente con il messaggio allegato:

$$
1570
$$

Perciò la firma viene accettata come valida.

> ✅ L'esempio mostra la simmetria matematica di RSA: la firma è ottenuta con l'esponente privato, mentre la verifica applica l'esponente pubblico e recupera il valore firmato.

---

### **10. Problema dei messaggi lunghi**

Lo schema diretto appena visto richiede che il messaggio sia un numero minore del modulo:

$$
M < n
$$

Nella pratica, però, un documento digitale può avere lunghezza arbitraria. Può essere molto più grande del modulo RSA, oppure semplicemente non essere già rappresentato come un singolo intero minore di $n$.

Serve quindi una trasformazione che renda applicabile lo schema di firma anche a messaggi lunghi.

Il docente presenta due possibili approcci:

- suddividere il messaggio in blocchi;
- firmare l'hash del messaggio.

---

### **11. Prima soluzione: firma separata dei blocchi**

Una prima soluzione consiste nel dividere il messaggio in blocchi:

$$
M = M_1 || M_2 || \dots || M_t
$$

in modo che ciascun blocco sia minore del modulo RSA:

$$
M_i < n
$$

per ogni:

$$
i = 1,2,\dots,t
$$

Alice potrebbe allora firmare separatamente ogni blocco:

$$
F_i = M_i^d \bmod n
$$

La firma dell'intero messaggio sarebbe la sequenza:

$$
(F_1,F_2,\dots,F_t)
$$

Questa soluzione è intuitiva, ma presenta due problemi importanti.

#### **11.1 Problema di efficienza**

Se il documento è lungo, il numero di blocchi $t$ può essere grande.

Alice dovrebbe quindi produrre tante firme quanti sono i blocchi:

$$
t \text{ firme per } t \text{ blocchi}
$$

Dato che ogni firma RSA richiede un'esponenziazione modulare con l'esponente privato, il costo complessivo diventa elevato.

#### **11.2 Problema di sicurezza**

La firma separata dei blocchi non lega necessariamente l'ordine dei blocchi al documento complessivo.

Se un avversario ottiene:

$$
(M_1,F_1),(M_2,F_2),\dots,(M_t,F_t)
$$

può permutare i blocchi e le firme corrispondenti, ottenendo ad esempio:

$$
(M_2,F_2),(M_1,F_1),\dots,(M_t,F_t)
$$

Ogni singola coppia blocco-firma continua a superare la verifica, ma il messaggio complessivo è diverso da quello originario.

Quindi si ottiene un nuovo documento composto da blocchi validamente firmati, ma riordinati.

> ⚠️ Firmare separatamente i blocchi non basta: una firma digitale deve autenticare il documento come oggetto unico, non solo singoli pezzi isolati.

---

### **12. Seconda soluzione: firma dell'hash del messaggio**

La soluzione più naturale e utilizzata consiste nell'introdurre una funzione hash crittografica:

$$
h
$$

Dato un messaggio di lunghezza arbitraria:

$$
M
$$

si calcola un digest di lunghezza fissa:

$$
h(M)
$$

Questo valore ha dimensione fissata, indipendente dalla lunghezza del documento originario, e dipende dal contenuto del messaggio.

Invece di firmare direttamente $M$, Alice firma il suo hash:

$$
F = h(M)^d \bmod n
$$

Il documento firmato è ancora rappresentato come:

$$
(M,F)
$$

ma la firma $F$ non è calcolata direttamente su $M$: è calcolata sul digest $h(M)$.

> 📌 La funzione hash trasforma un documento lungo in un'impronta breve a lunghezza fissa. RSA firma questa impronta, non l'intero documento.

---

### **13. Verifica con hash**

La procedura di verifica cambia leggermente.

Il verificatore riceve:

$$
(M,F)
$$

e conosce la chiave pubblica di Alice:

$$
(n,e)
$$

Prima calcola:

$$
F^e \bmod n
$$

Poiché:

$$
F = h(M)^d \bmod n
$$

il risultato atteso è:

$$
F^e \bmod n = h(M)
$$

Il verificatore calcola anche autonomamente l'hash del messaggio ricevuto:

$$
h(M)
$$

La firma è valida se i due valori coincidono:

$$
F^e \bmod n = h(M)
$$

Se l'uguaglianza non vale, allora la verifica fallisce.

Questo può significare che:

- il documento è stato modificato;
- la firma non è stata prodotta con la chiave privata corrispondente alla chiave pubblica usata;
- oppure è stata usata una funzione hash o una codifica diversa da quella prevista.

---

### **14. Vantaggi della firma dell'hash**

La firma dell'hash risolve entrambi i problemi della firma a blocchi.

#### **14.1 Efficienza**

Indipendentemente dalla lunghezza del messaggio, Alice firma un solo valore:

$$
h(M)
$$

La firma RSA viene quindi applicata a un blocco di dimensione fissa.

Il costo della firma non cresce con il numero di blocchi del documento, ma dipende essenzialmente dal costo di:

- calcolare l'hash del documento;
- eseguire una sola esponenziazione RSA privata.

#### **14.2 Integrità**

Poiché il digest dipende dall'intero messaggio, una modifica del documento dovrebbe cambiare il valore:

$$
h(M)
$$

Se un avversario altera anche una parte del documento, il verificatore calcola:

$$
h(M')
$$

e, in generale:

$$
h(M') \ne h(M)
$$

Quindi il confronto con:

$$
F^e \bmod n
$$

fallisce.

#### **14.3 Sicurezza collegata alle proprietà della funzione hash**

La sicurezza dello schema dipende anche dalle proprietà della funzione hash utilizzata.

In particolare, è importante che l'avversario non riesca a trovare due messaggi distinti:

$$
M \ne M'
$$

tali che:

$$
h(M) = h(M')
$$

Se riuscisse a farlo, una firma valida per $M$ potrebbe essere riutilizzata anche per $M'$.

Per questo, nello schema con hash, la funzione hash deve essere resistente alle collisioni e deve comportarsi come una buona impronta crittografica del documento.

> ✅ Firmare l'hash rende la firma efficiente e lega la firma all'intero documento, ma sposta parte della sicurezza sulle proprietà della funzione hash.

---

### **15. Nota sulla versione didattica dello schema**

Lo schema descritto nella lezione mostra l'idea essenziale della firma RSA:

$$
F = M^d \bmod n
$$

oppure, più realisticamente:

$$
F = h(M)^d \bmod n
$$

Questa è la forma matematica di base. Nelle implementazioni reali, però, non si firma mai un hash "nudo" con RSA in modo diretto. Si usa una codifica e un padding di firma standard, ad esempio schemi della famiglia **RSA-PSS** o codifiche definite dagli standard applicativi.

Il motivo è che RSA puro ha proprietà algebriche che possono essere sfruttate in attacchi se non viene inserito in una costruzione completa e correttamente codificata.

> ⚠️ Per l'esame teorico è fondamentale capire la formula base e il ruolo dell'hash. Per la sicurezza pratica, però, RSA deve essere usato con padding di firma appropriato.

---

### **16. Riepilogo**

In questa lezione abbiamo visto come il crittosistema RSA possa essere adattato a uno schema di firma digitale.

I punti fondamentali sono:

- la chiave pubblica RSA di Alice è $(n,e)$;
- la chiave privata contiene l'esponente $d$;
- $d$ è l'inverso moltiplicativo di $e$ modulo $\varphi(n)$;
- per firmare un messaggio numerico $M < n$, Alice calcola $F = M^d \bmod n$;
- per verificare, chiunque calcola $F^e \bmod n$ e controlla che coincida con $M$;
- in questa forma lo schema permette anche il recupero del messaggio durante la verifica;
- nell'esempio numerico, con $n=3337$, $e=79$, $d=1019$ e $M=1570$, la firma è $F=668$;
- per messaggi lunghi, la firma separata dei blocchi è inefficiente e fragile rispetto a permutazioni;
- la soluzione naturale è calcolare un hash $h(M)$ e firmare il digest;
- la verifica con hash confronta $F^e \bmod n$ con $h(M)$;
- la sicurezza dello schema con hash dipende anche dalla resistenza alle collisioni della funzione hash.

> ✅ La firma RSA usa la chiave privata per generare la firma e la chiave pubblica per verificarla. Per documenti reali, si firma l'impronta hash del documento, non il documento intero blocco per blocco.
