Il modulo sulle **Firme Digitali** introduce uno dei pilastri fondamentali della sicurezza informatica moderna: la possibilità di garantire **autenticità**, **integrità** e, nei contesti applicativi corretti, **non ripudio** per documenti digitali.

L'obiettivo non è soltanto capire quale algoritmo produca una firma, ma comprendere perché la firma digitale non possa essere trattata come una semplice immagine allegata a un file. Nel mondo digitale i documenti sono modificabili, trasmissibili e duplicabili con estrema facilità: proprio per questo uno schema di firma deve essere progettato come una primitiva crittografica, con procedure precise di generazione, verifica e sicurezza contro falsificazioni.

---

## **Lezione 1: Scenario e applicazioni**

### **1. Obiettivo della lezione**

In questa lezione introduciamo gli **schemi di firma digitale**, cioè schemi che permettono di apporre una firma a un documento digitale.

L'idea di partenza è analoga alla firma tradizionale su carta: quando una persona firma un documento, la firma serve a collegare quel documento al soggetto che lo ha sottoscritto. In particolare, nel mondo fisico una firma dovrebbe fornire almeno due garanzie:

- l'**autenticità** del firmatario, cioè il fatto che il documento sia stato effettivamente sottoscritto da quella persona;
- la **veridicità e integrità** dei dati contenuti nel documento, nel senso che il documento firmato deve essere quello approvato dal firmatario.

Trasportando questa idea nel mondo digitale, una firma digitale dovrebbe garantire almeno le stesse proprietà di una firma convenzionale. Tuttavia, il contesto digitale introduce problemi specifici che non possono essere ignorati.

> 📌 Una firma digitale non è una firma "scansionata": è un valore crittografico calcolato sul documento e legato a una chiave segreta del firmatario.

---

### **2. Perché il documento digitale è più problematico**

Un documento digitale ha caratteristiche molto diverse da un documento cartaceo.

Prima di tutto, è **facilmente modificabile**. Cambiare alcuni bit di un file può alterare il contenuto del documento senza lasciare segni fisici evidenti come cancellature, abrasioni o sovrascritture.

In secondo luogo, è **facilmente trasmissibile**. Un file può essere inviato su reti insicure, copiato tra dispositivi, pubblicato, inoltrato e archiviato in molti punti diversi.

In terzo luogo, è **facilmente duplicabile**. Copiare un documento digitale produce una copia indistinguibile dall'originale a livello di bit.

Queste proprietà rendono il problema della firma digitale più delicato della firma tradizionale. Su carta, la firma è fisicamente legata al supporto: compare su quel foglio, in quella posizione, insieme al resto del documento. Nel digitale, invece, anche la firma è una sequenza di bit. Questo significa che, se non viene protetta crittograficamente, può essere copiata, spostata o modificata come qualunque altro dato.

> ⚠️ Il problema non è soltanto firmare un file: è impedire che una firma valida per un documento possa essere riutilizzata, duplicata o adattata a un altro documento.

---

### **3. Il fallimento della firma digitalizzata**

Una soluzione ingenua potrebbe essere usare una **firma digitalizzata**, cioè la scansione o l'immagine digitale di una firma autografa.

Per esempio, Alice firma fisicamente un foglio, scansiona la firma e inserisce l'immagine in un documento elettronico.

Questa soluzione, però, non è una vera firma digitale. È soltanto un'immagine, cioè una sequenza di bit. Chiunque riesca ad accedere al documento può:

- ritagliare l'immagine della firma;
- copiarla in un altro file;
- appenderla a un documento diverso;
- costruire un nuovo documento apparentemente firmato da Alice.

Il risultato sarebbe un documento firmato illegalmente, ma visivamente plausibile.

La firma digitalizzata non lega crittograficamente la firma al contenuto del documento. Non permette quindi di distinguere in modo robusto tra:

- il documento realmente firmato;
- una copia lecita;
- una copia alterata;
- un documento contraffatto a cui è stata incollata la firma.

> ✅ Una firma digitale corretta deve dipendere dal documento firmato. Se il documento cambia, la verifica della firma deve fallire.

---

### **4. Requisiti di uno schema di firma digitale**

Uno schema di firma digitale deve soddisfare alcuni requisiti fondamentali.

#### **4.1 Algoritmo di firma per l'utente legittimo**

Deve esistere una procedura che permetta al legittimo firmatario di produrre la firma di un documento.

Se Alice vuole firmare un documento $M$, deve poter applicare una procedura di firma usando un'informazione segreta nota solo a lei.

Questa informazione segreta è tipicamente la sua **chiave privata**:

$$
sk_A
$$

La procedura deve essere efficiente: Alice deve poter firmare documenti in un tempo pratico.

#### **4.2 Impossibilità computazionale di falsificazione**

Deve essere computazionalmente inammissibile produrre o falsificare la firma di un'altra persona.

In altre parole, un avversario non deve poter firmare un documento fingendo di essere Alice.

Questo requisito non significa che falsificare sia logicamente impossibile in senso assoluto, ma che lo sforzo richiesto deve essere irrealizzabile con risorse realistiche.

#### **4.3 Procedura efficiente di verifica**

Deve esistere una procedura che permetta di verificare la validità di una firma.

La verifica deve essere efficiente e deve poter essere eseguita da chiunque disponga dell'informazione pubblica associata ad Alice, tipicamente la sua **chiave pubblica**:

$$
pk_A
$$

> 📌 Uno schema di firma digitale è asimmetrico: la firma richiede una parte segreta, mentre la verifica usa una parte pubblica.

---

### **5. Le due procedure fondamentali**

Uno schema di firma digitale è costituito almeno da due procedure:

- una procedura di **firma**;
- una procedura di **verifica**.

La procedura di firma prende in input:

- il messaggio o documento $M$;
- la chiave privata del firmatario $sk_A$.

Produce in output una firma:

$$
\sigma = \text{Sign}(sk_A, M)
$$

La procedura di verifica prende in input:

- il messaggio $M$;
- la firma $\sigma$;
- la chiave pubblica del presunto firmatario $pk_A$.

Restituisce un valore booleano:

$$
\text{Verify}(pk_A, M, \sigma) \in \{\text{vero}, \text{falso}\}
$$

Più esplicitamente:

$$
\text{Verify}(pk_A, M, \sigma) =
\begin{cases}
\text{vero} & \text{se la firma è valida per } M \text{ rispetto ad } pk_A\\
\text{falso} & \text{altrimenti}
\end{cases}
$$

Entrambe le procedure devono essere efficienti.

---

### **6. Scenario Alice-Bob**

Supponiamo che Alice voglia apporre legittimamente la propria firma a un documento $M$.

Alice applica la procedura di firma:

$$
\sigma = \text{Sign}(sk_A, M)
$$

Il valore $\sigma$ è la firma digitale del documento $M$.

A questo punto Alice possiede una versione firmata del documento, rappresentata dalla coppia:

$$
(M, \sigma)
$$

Questa coppia può essere trasmessa a un altro utente, ad esempio Bob.

È importante osservare che, in questo modello, non ci stiamo occupando della confidenzialità del documento. Il messaggio $M$ può viaggiare in chiaro anche su un canale insicuro. La firma digitale serve a fornire autenticità e integrità, non segretezza.

> ⚠️ Firmare non significa cifrare. Un documento firmato può essere perfettamente leggibile da chiunque, se non viene anche cifrato con un meccanismo separato.

---

### **7. Verifica della firma**

Quando Bob riceve:

$$
(M, \sigma)
$$

vuole decidere se $\sigma$ sia davvero una firma prodotta da Alice sul messaggio $M$.

Per farlo usa la procedura di verifica:

$$
\text{Verify}(pk_A, M, \sigma)
$$

dove $pk_A$ è la chiave pubblica di Alice.

Se la verifica restituisce `vero`, Bob accetta la firma:

$$
\text{Verify}(pk_A, M, \sigma) = \text{vero}
$$

Se invece restituisce `falso`, Bob rifiuta il documento firmato:

$$
\text{Verify}(pk_A, M, \sigma) = \text{falso}
$$

La verifica positiva indica che la coppia messaggio-firma è coerente con la chiave pubblica di Alice. Di conseguenza, se la chiave pubblica è stata associata correttamente all'identità di Alice, Bob può concludere che:

- il documento è stato firmato da Alice;
- il documento non è stato modificato dopo la firma;
- Alice non può ragionevolmente negare di aver prodotto la firma, salvo compromissione della chiave privata o problemi nel sistema di certificazione.

> 📌 La verifica crittografica dimostra il legame tra firma e chiave pubblica. Il legame tra chiave pubblica e identità reale richiede invece un'infrastruttura di certificazione o un meccanismo affidabile di distribuzione delle chiavi.

---

### **8. Come discutere la sicurezza di uno schema di firma**

Per discutere la sicurezza di uno schema di firma digitale, come già fatto per altre primitive crittografiche, bisogna specificare due elementi:

- l'**obiettivo dell'attaccante**;
- il **modello di attacco**, cioè quali informazioni e capacità sono disponibili all'attaccante.

Nel caso delle firme digitali, l'obiettivo generale dell'avversario è produrre **firme false**, cioè firme che verranno riconosciute dagli altri utenti come firme legittimamente prodotte dall'utente attaccato.

Per esempio, l'avversario vuole produrre una coppia:

$$
(M, \sigma)
$$

tale che:

$$
\text{Verify}(pk_A, M, \sigma) = \text{vero}
$$

senza che Alice abbia realmente firmato $M$.

---

### **9. Attacchi classificati in base alle conoscenze**

Una prima classificazione riguarda ciò che l'avversario conosce o può ottenere.

#### **9.1 Key-only attack**

Nel **key-only attack**, l'avversario conosce soltanto la chiave pubblica di Alice:

$$
pk_A
$$

Questa conoscenza è naturale, perché la chiave pubblica deve essere disponibile per permettere a chiunque di verificare le firme di Alice.

Lo schema deve quindi essere sicuro anche se l'attaccante conosce perfettamente la chiave pubblica.

#### **9.2 Known message attack**

Nel **known message attack**, l'avversario conosce una lista di messaggi e le rispettive firme valide.

Per esempio conosce:

$$
(M_1, \sigma_1), (M_2, \sigma_2), \dots, (M_t, \sigma_t)
$$

dove:

$$
\sigma_i = \text{Sign}(sk_A, M_i)
$$

per ogni $i$.

L'avversario non sceglie necessariamente questi messaggi: li osserva o li ottiene perché sono documenti già firmati da Alice.

#### **9.3 Chosen message attack**

Nel **chosen message attack**, l'avversario può scegliere alcuni messaggi e chiedere ad Alice di firmarli.

Riceve quindi firme valide su messaggi scelti da lui:

$$
\sigma_i = \text{Sign}(sk_A, M_i)
$$

Questo modello è più forte del known message attack, perché l'attaccante non si limita a osservare firme disponibili: può orientare la scelta dei messaggi da far firmare.

#### **9.4 Adaptive chosen message attack**

Nel **adaptive chosen message attack**, l'avversario può scegliere i messaggi in modo adattivo.

Questo significa che:

- sceglie un primo messaggio $M_1$;
- ottiene la firma $\sigma_1$;
- usa l'informazione contenuta in $\sigma_1$ per scegliere un nuovo messaggio $M_2$;
- ottiene $\sigma_2$;
- continua scegliendo i messaggi successivi in base alle risposte precedenti.

Questo è il modello di attacco più forte tra quelli elencati nella lezione, perché l'avversario può interagire con il firmatario e adattare la strategia durante l'attacco.

> ✅ Uno schema di firma robusto deve rimanere sicuro anche se l'avversario osserva molte firme valide e, nei modelli più forti, può persino scegliere messaggi da far firmare.

---

### **10. Attacchi classificati in base all'obiettivo**

Una seconda classificazione riguarda ciò che l'avversario riesce a ottenere.

#### **10.1 Total break**

Nel caso di **total break**, l'attacco è devastante.

L'avversario riesce a:

- recuperare la chiave privata di Alice $sk_A$;
- oppure costruire una procedura di firma equivalente a quella di Alice.

In entrambi i casi può firmare qualunque documento al posto di Alice:

$$
\forall M,\ \exists \sigma \text{ tale che } \text{Verify}(pk_A, M, \sigma) = \text{vero}
$$

La firma perde sostanzialmente ogni valore, perché l'attaccante può impersonare Alice su qualsiasi documento.

#### **10.2 Selective forgery**

Nella **selective forgery**, l'avversario riesce a produrre una firma valida per uno specifico messaggio $M$, oppure per una specifica classe di messaggi.

L'attaccante ha quindi un bersaglio definito.

Formalmente riesce a produrre una firma $\sigma$ tale che:

$$
\text{Verify}(pk_A, M, \sigma) = \text{vero}
$$

per quel particolare messaggio $M$ che gli interessa.

Questo scenario è meno grave del total break, perché l'avversario non può firmare qualunque documento, ma è comunque un fallimento serio dello schema.

#### **10.3 Existential forgery**

Nella **existential forgery**, l'avversario riesce a produrre almeno una coppia messaggio-firma valida:

$$
(M, \sigma)
$$

tale che:

$$
\text{Verify}(pk_A, M, \sigma) = \text{vero}
$$

La differenza rispetto alla selective forgery è che qui l'avversario non controlla necessariamente il significato o il contenuto utile del messaggio. Può anche produrre una coppia valida per un messaggio casuale, senza valore pratico immediato.

Tuttavia, dal punto di vista teorico, anche questa è una violazione della sicurezza: lo schema ha permesso di generare una firma valida non prodotta dal legittimo firmatario.

> ⚠️ Anche una existential forgery è considerata un fallimento crittografico. Uno schema moderno deve impedire la produzione di qualunque nuova coppia messaggio-firma valida non autorizzata.

---

### **11. Relazione tra autenticità, integrità e non ripudio**

Una firma digitale corretta fornisce autenticità e integrità in modo congiunto.

Fornisce **autenticità** perché solo il possessore della chiave privata dovrebbe poter generare una firma accettata dalla chiave pubblica corrispondente.

Fornisce **integrità** perché la firma dipende dal messaggio. Se un avversario modifica anche solo una parte del documento, la verifica deve fallire:

$$
\text{Verify}(pk_A, M', \sigma) = \text{falso}
$$

dove $M'$ è il messaggio modificato.

Il **non ripudio** deriva dal fatto che la firma è verificabile pubblicamente: se la chiave pubblica è correttamente associata ad Alice e la chiave privata non è stata compromessa, Alice non può negare in modo credibile di aver firmato il documento.

> 📌 Il non ripudio non è solo una proprietà matematica: richiede anche gestione corretta delle chiavi, certificati, identità e responsabilità del titolare della chiave privata.

---

### **12. Riepilogo**

In questa lezione abbiamo introdotto lo scenario delle firme digitali.

I punti fondamentali sono:

- un documento digitale è facile da modificare, trasmettere e duplicare;
- una firma digitalizzata, cioè l'immagine di una firma autografa, non è una vera firma digitale;
- una firma digitale deve legare crittograficamente il documento al firmatario;
- uno schema di firma è composto almeno da una procedura di firma e una procedura di verifica;
- la firma usa una parte segreta, cioè la chiave privata;
- la verifica usa una parte pubblica, cioè la chiave pubblica;
- la coppia $(M,\sigma)$ può viaggiare anche su un canale insicuro, perché la firma non ha l'obiettivo di garantire confidenzialità;
- la sicurezza si valuta specificando conoscenze dell'attaccante e obiettivo dell'attacco;
- gli attacchi possono essere key-only, known message, chosen message o adaptive chosen message;
- gli obiettivi possono essere total break, selective forgery o existential forgery.

> ✅ Una firma digitale è sicura solo se un avversario non riesce a produrre nuove firme valide, anche osservando firme legittime e conoscendo la chiave pubblica del firmatario.
