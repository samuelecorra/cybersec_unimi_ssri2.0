## **Lezione 2: Sicurezza di RSA**

### **1. Obiettivo della lezione**

Nella lezione precedente abbiamo visto come il crittosistema RSA possa essere adattato a uno schema di firma digitale.

Nello schema RSA diretto, Alice firma un messaggio numerico $M$ calcolando:

$$
F = M^d \bmod n
$$

dove:

- $(n,e)$ è la chiave pubblica di Alice;
- $d$ è l'esponente privato di Alice;
- $ed \equiv 1 \pmod{\varphi(n)}$.

La verifica consiste nel controllare che:

$$
F^e \bmod n = M
$$

In questa lezione analizziamo la **sicurezza dello schema di firma RSA**, classificando gli attacchi in base a due dimensioni:

- il **tipo di conoscenza** dell'avversario, ad esempio key-only, known message o chosen message;
- l'**obiettivo** dell'attacco, ad esempio selective forgery o existential forgery.

> 📌 La domanda centrale è: quanto è difficile produrre una coppia $(M,F)$ che venga accettata dalla verifica RSA come firma valida di Alice?

---

### **2. RSA diretto e fragilità strutturale**

Lo schema RSA diretto, spesso chiamato anche **textbook RSA**, è matematicamente molto semplice:

$$
\text{firma: } F = M^d \bmod n
$$

e:

$$
\text{verifica: } F^e \bmod n = M
$$

Questa semplicità è utile per capire l'idea di firma digitale, ma è anche pericolosa. RSA possiede infatti una struttura algebrica molto regolare, in particolare una proprietà di **omomorfismo moltiplicativo**:

$$
(x^d \bmod n)(y^d \bmod n) \equiv (xy)^d \pmod n
$$

Questa proprietà permette di combinare firme valide per ottenere nuove firme valide.

> ⚠️ La sicurezza pratica di RSA non deriva dalla formula nuda $M^d \bmod n$, ma dall'uso di codifiche, ridondanza, hash e padding di firma progettati per impedire gli attacchi strutturali.

---

### **3. Key-only selective forgery**

Consideriamo un attacco di tipo:

- **key-only attack**, perché l'avversario conosce soltanto la chiave pubblica di Alice;
- **selective forgery**, perché l'avversario vuole produrre una firma valida per uno specifico messaggio $M$ scelto come bersaglio.

L'avversario conosce:

$$
(n,e)
$$

e vuole calcolare una firma $F$ tale che:

$$
F^e \bmod n = M
$$

In altre parole, vuole trovare la radice $e$-esima di $M$ modulo $n$.

Nel linguaggio della firma RSA, il valore cercato è:

$$
F = M^d \bmod n
$$

ma l'avversario non conosce $d$.

Calcolare $F$ per un messaggio specifico $M$, conoscendo solo $(n,e)$, è essenzialmente il problema RSA: significa riuscire a invertire l'esponenziazione pubblica senza conoscere la chiave privata.

Quindi, in questo scenario, una selective forgery key-only contro RSA diretto equivale a rompere il problema computazionale alla base di RSA.

> ✅ Se l'obiettivo è firmare uno specifico messaggio $M$ conoscendo solo la chiave pubblica, l'attacco richiede di risolvere il problema RSA o di ricavare una capacità equivalente alla chiave privata.

---

### **4. Key-only existential forgery**

Consideriamo ora un altro scenario:

- l'avversario conosce sempre solo la chiave pubblica di Alice;
- però non vuole firmare un messaggio specifico;
- vuole soltanto generare **una qualunque coppia valida** messaggio-firma.

Questo è un attacco di tipo:

$$
\text{key-only existential forgery}
$$

In RSA diretto, l'attacco è immediato.

L'avversario sceglie un valore casuale:

$$
F
$$

Poi calcola:

$$
M = F^e \bmod n
$$

A questo punto la coppia:

$$
(M,F)
$$

è formalmente valida, perché la verifica RSA controlla proprio:

$$
F^e \bmod n = M
$$

Quindi l'avversario è riuscito a produrre una firma valida senza conoscere la chiave privata.

Il limite dell'attacco è che il messaggio ottenuto:

$$
M = F^e \bmod n
$$

non è scelto dall'avversario e, con alta probabilità, non rappresenta un documento significativo o dotato di un formato valido.

> ⚠️ Anche se il messaggio può essere privo di significato, l'attacco mostra una debolezza teorica importante: textbook RSA permette di generare coppie valide costruendo prima la firma e poi il messaggio.

---

### **5. Ruolo della ridondanza e del formato**

Per contrastare l'existential forgery appena descritta, si introduce normalmente una forma di **ridondanza** o di **formato controllato** nei messaggi validi.

L'idea è che non ogni valore numerico modulo $n$ debba essere considerato un messaggio valido.

Per esempio, un messaggio valido potrebbe dover contenere:

- un prefisso fissato;
- informazioni di formato;
- un identificatore dell'algoritmo hash;
- padding strutturato;
- una ridondanza controllabile dal verificatore.

Così, se l'avversario sceglie un valore casuale $F$ e calcola:

$$
M = F^e \bmod n
$$

è molto improbabile che $M$ abbia esattamente il formato richiesto.

Il verificatore non si limita più a controllare l'equazione RSA, ma controlla anche che il valore recuperato abbia una struttura valida.

> 📌 La ridondanza serve a distinguere i messaggi realmente ammissibili da valori casuali che soddisfano l'equazione matematica ma non rappresentano un documento firmabile secondo lo standard.

---

### **6. Known message existential forgery**

Passiamo ora a un attacco in cui l'avversario conosce alcune firme legittime.

Supponiamo che l'avversario conosca due coppie messaggio-firma:

$$
(M_1,F_1)
$$

e:

$$
(M_2,F_2)
$$

dove:

$$
F_1 = M_1^d \bmod n
$$

e:

$$
F_2 = M_2^d \bmod n
$$

L'obiettivo dell'avversario è produrre una nuova coppia messaggio-firma valida.

La proprietà sfruttata è l'omomorfismo moltiplicativo di RSA.

L'avversario calcola:

$$
F = F_1 \cdot F_2 \bmod n
$$

Vediamo cosa succede in verifica:

$$
F^e \bmod n = (F_1F_2)^e \bmod n
$$

Usando le proprietà dell'esponenziazione:

$$
(F_1F_2)^e \equiv F_1^eF_2^e \pmod n
$$

ma, poiché $F_1$ e $F_2$ sono firme valide:

$$
F_1^e \equiv M_1 \pmod n
$$

e:

$$
F_2^e \equiv M_2 \pmod n
$$

quindi:

$$
F^e \equiv M_1M_2 \pmod n
$$

La nuova firma:

$$
F = F_1F_2 \bmod n
$$

è quindi valida per il nuovo messaggio:

$$
M = M_1M_2 \bmod n
$$

L'avversario ha prodotto una nuova coppia:

$$
(M_1M_2 \bmod n,\ F_1F_2 \bmod n)
$$

che supera la verifica.

> ✅ Questo è un esempio di existential forgery known-message: l'avversario non sceglie necessariamente un messaggio utile, ma costruisce una nuova coppia valida combinando firme già osservate.

---

### **7. Perché l'omomorfismo è pericoloso**

La vulnerabilità nasce dal fatto che la firma RSA diretta preserva la moltiplicazione.

Se:

$$
F_1 = \text{Sign}(M_1)
$$

e:

$$
F_2 = \text{Sign}(M_2)
$$

allora:

$$
F_1F_2 \bmod n
$$

si comporta come una firma di:

$$
M_1M_2 \bmod n
$$

Quindi le firme non sono indipendenti dal punto di vista algebrico. Si possono combinare per ottenere firme nuove.

Questo è incompatibile con la nozione moderna di sicurezza delle firme digitali, in cui osservare firme valide non deve permettere di produrne altre.

> ⚠️ Una firma digitale sicura deve impedire che firme su messaggi già noti possano essere manipolate come mattoni algebrici per firmare nuovi messaggi.

---

### **8. Chosen message selective forgery**

Consideriamo ora un attacco più forte:

- l'avversario può scegliere messaggi da far firmare ad Alice;
- il suo obiettivo è ottenere una firma valida per uno specifico messaggio bersaglio $M$.

Questo è uno scenario di:

$$
\text{chosen message selective forgery}
$$

L'attacco si svolge in tre passi.

#### **8.1 Scelta di due fattori del messaggio bersaglio**

L'avversario vuole ottenere una firma valida per $M$.

Sceglie due valori $M_1$ e $M_2$ tali che:

$$
M \equiv M_1M_2 \pmod n
$$

Un modo concreto per farlo, se $M_1$ è invertibile modulo $n$, è:

$$
M_2 \equiv M \cdot M_1^{-1} \pmod n
$$

così:

$$
M_1M_2 \equiv M_1(MM_1^{-1}) \equiv M \pmod n
$$

#### **8.2 Richiesta di firme**

L'avversario chiede ad Alice di firmare separatamente $M_1$ e $M_2$.

Ottiene:

$$
F_1 = M_1^d \bmod n
$$

e:

$$
F_2 = M_2^d \bmod n
$$

#### **8.3 Combinazione delle firme**

L'avversario calcola:

$$
F = F_1F_2 \bmod n
$$

Allora:

$$
F^e \equiv F_1^eF_2^e \equiv M_1M_2 \equiv M \pmod n
$$

Quindi:

$$
F
$$

è una firma valida per il messaggio bersaglio:

$$
M
$$

L'avversario è riuscito a ottenere una firma di $M$ senza chiedere direttamente ad Alice di firmare $M$.

> 📌 L'attacco chosen-message mostra che RSA diretto è malleabile: la possibilità di ottenere firme su messaggi scelti permette di costruire firme su messaggi bersaglio.

---

### **9. Schema RSA con funzione hash**

Per evitare molti degli attacchi precedenti, nella pratica non si firma direttamente il messaggio $M$.

Si calcola invece un digest:

$$
h(M)
$$

e si firma il digest:

$$
F = h(M)^d \bmod n
$$

La verifica controlla che:

$$
F^e \bmod n = h(M)
$$

Il messaggio $M$ viene quindi dato in input alla funzione hash anche durante la verifica. Il verificatore confronta:

- il valore recuperato dalla firma, cioè $F^e \bmod n$;
- il digest calcolato localmente sul messaggio ricevuto, cioè $h(M)$.

Questo schema lega la firma all'impronta del documento, non direttamente al documento come intero modulo $n$.

> ✅ L'hash rompe la struttura immediata su cui si basano gli attacchi moltiplicativi al messaggio nudo, ma introduce una dipendenza dalle proprietà di sicurezza della funzione hash.

---

### **10. Key-only existential forgery con hash**

Consideriamo lo schema:

$$
F = h(M)^d \bmod n
$$

e un avversario che conosce solo la chiave pubblica di Alice.

Come nello schema RSA diretto, l'avversario può scegliere un valore casuale:

$$
F
$$

e calcolare:

$$
Z = F^e \bmod n
$$

Perché la coppia $(M,F)$ sia valida, dovrebbe esistere un messaggio $M$ tale che:

$$
h(M) = Z
$$

Quindi l'avversario dovrebbe invertire la funzione hash sul valore $Z$, cioè trovare una preimmagine:

$$
M \in h^{-1}(Z)
$$

Se la funzione hash è **preimage resistant**, questo è computazionalmente impraticabile.

L'attacco ha quindi successo solo se la funzione hash utilizzata non è resistente alla preimmagine.

> ⚠️ Nel modello con hash, la key-only existential forgery si trasforma nel problema di trovare un messaggio con hash prefissato. Per questo è necessaria la preimage resistance della funzione hash.

---

### **11. Chosen message forgery con hash e collisioni**

Consideriamo ora un avversario che può ottenere firme su messaggi scelti.

L'obiettivo è sfruttare una collisione della funzione hash.

L'avversario cerca due messaggi distinti:

$$
M_1 \ne M_2
$$

tali che:

$$
h(M_1) = h(M_2)
$$

Poi sottopone ad Alice il messaggio $M_1$ e ottiene una firma valida:

$$
F_1 = h(M_1)^d \bmod n
$$

Poiché:

$$
h(M_1) = h(M_2)
$$

si ha anche:

$$
F_1 = h(M_2)^d \bmod n
$$

Quindi la stessa firma $F_1$ è valida anche per $M_2$.

Infatti la verifica su $M_2$ calcola:

$$
F_1^e \bmod n
$$

e ottiene:

$$
h(M_1)
$$

che coincide con:

$$
h(M_2)
$$

La coppia:

$$
(M_2,F_1)
$$

viene quindi accettata come valida.

Questo attacco è possibile solo se la funzione hash non è **collision resistant**.

> 📌 La firma RSA con hash eredita una parte della propria sicurezza dalla funzione hash: se è facile trovare collisioni, una firma su un messaggio può diventare firma valida anche per un altro messaggio.

---

### **12. Collegamento tra attacchi e proprietà della hash**

Gli attacchi allo schema RSA con hash mostrano due dipendenze fondamentali.

#### **12.1 Preimage resistance**

Nel key-only existential forgery con hash, l'attaccante sceglie $F$, calcola:

$$
Z = F^e \bmod n
$$

e deve trovare:

$$
M \text{ tale che } h(M)=Z
$$

Questo richiede di invertire la funzione hash su un valore dato. La proprietà necessaria è quindi la **resistenza alla preimmagine**.

#### **12.2 Collision resistance**

Nel chosen message attack con hash, l'attaccante cerca:

$$
M_1 \ne M_2
$$

con:

$$
h(M_1)=h(M_2)
$$

e sfrutta la firma di $M_1$ come firma di $M_2$.

La proprietà necessaria è quindi la **resistenza alle collisioni**.

> ✅ La sicurezza dello schema RSA con hash non dipende solo da RSA: dipende anche dal fatto che la funzione hash sia resistente a preimmagini e collisioni.

---

### **13. Riepilogo degli attacchi**

Possiamo riassumere gli attacchi discussi nella lezione nel modo seguente.

Nel caso di **RSA diretto**:

- una selective forgery key-only su un messaggio specifico richiede di calcolare $M^d \bmod n$, quindi equivale a rompere RSA;
- una existential forgery key-only è banale: si sceglie $F$ e si pone $M = F^e \bmod n$;
- una existential forgery known-message sfrutta due firme note $F_1$ e $F_2$ per costruire una firma valida $F_1F_2 \bmod n$ su $M_1M_2 \bmod n$;
- una selective forgery chosen-message sfrutta la possibilità di far firmare $M_1$ e $M_2$ tali che $M \equiv M_1M_2 \pmod n$.

Nel caso di **RSA con hash**:

- una existential forgery key-only richiede di trovare $M$ tale che $h(M)=Z$, quindi richiede di violare la preimage resistance;
- una forgery basata su chosen message può sfruttare due messaggi con lo stesso hash, quindi richiede di violare la collision resistance.

---

### **14. Conclusione**

Lo schema RSA diretto è utile per capire la matematica della firma digitale, ma non è sicuro come schema pratico se usato senza protezioni.

La ragione principale è l'omomorfismo moltiplicativo:

$$
\text{Sign}(M_1)\cdot \text{Sign}(M_2) \equiv \text{Sign}(M_1M_2) \pmod n
$$

Questa proprietà consente di combinare firme valide per ottenere nuove firme valide.

L'uso di una funzione hash migliora lo schema, perché si firma:

$$
h(M)
$$

invece di:

$$
M
$$

ma la sicurezza dipende dalle proprietà della funzione hash usata.

> ✅ Bisogna ricordare due idee: l'omomorfismo di RSA rende vulnerabile lo schema textbook, mentre l'uso dell'hash sposta parte della sicurezza sulle proprietà di preimage resistance e collision resistance della funzione hash.
