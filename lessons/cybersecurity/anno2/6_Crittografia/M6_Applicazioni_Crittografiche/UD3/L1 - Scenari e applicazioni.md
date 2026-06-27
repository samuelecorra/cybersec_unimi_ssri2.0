## **Lezione 1: Scenari e applicazioni**

### **1. Introduzione**

In molti crittosistemi e, più in generale, in molti contesti di sicurezza, esiste la necessità di proteggere un **segreto**.

Il segreto può essere:

- una chiave crittografica;
- una password amministrativa;
- un codice di sblocco;
- un'informazione riservata;
- un'autorizzazione critica che non deve dipendere da una sola persona.

Il problema è che conservare il segreto in un unico punto può essere pericoloso. Se una sola persona lo conosce, quella persona diventa un punto singolo di fallimento: può perderlo, rivelarlo, essere compromessa, oppure abusarne.

La **condivisione del segreto** risolve questo problema suddividendo il segreto tra più partecipanti. Ogni partecipante riceve una parte del segreto, chiamata **share**, ma la propria share isolata non è sufficiente a ricostruire l'informazione originaria.

Solo in un secondo momento, attraverso la collaborazione di tutti i partecipanti o di una parte qualificata di essi, sarà possibile ricostruire il segreto.

> 📌 Il secret sharing non consiste nel dare una copia del segreto a più persone: consiste nel distribuire pezzi separati che diventano utili solo quando vengono combinati secondo una regola precisa.

---

### **2. Scenario generale**

Lo scenario considerato è il seguente.

Esiste un'entità chiamata **dealer**, cioè il distributore del segreto.

Il dealer possiede un segreto:

$$
S
$$

e vuole condividerlo tra un insieme di:

$$
n
$$

partecipanti:

$$
P_1,P_2,\dots,P_n
$$

La distribuzione deve essere fatta in modo che:

- un insieme di almeno $k$ partecipanti possa collaborare e ricostruire il segreto $S$;
- un insieme di meno di $k$ partecipanti non possa ricostruire $S$;
- un insieme di meno di $k$ partecipanti non debba ottenere alcuna informazione utile sul valore di $S$.

Questa struttura prende il nome di **schema a soglia**.

Quando si parla di schema:

$$
(k,n)
$$

si intende uno schema in cui:

- $n$ è il numero totale dei partecipanti;
- $k$ è la soglia minima richiesta per ricostruire il segreto.

> ⚠️ La proprietà richiesta non è soltanto "non riuscire a ricostruire il segreto". Si vuole una garanzia più forte: chi ha meno di $k$ share non deve imparare nulla sul segreto.

---

### **3. Schemi a soglia di Shamir**

Una prima soluzione generale a questo problema è stata proposta da **Adi Shamir nel 1979**.

Shamir introdusse gli schemi a soglia, cioè meccanismi in cui il segreto viene distribuito in modo che solo un gruppo abbastanza numeroso di partecipanti possa ricostruirlo.

L'idea generale è:

1. il dealer parte dal segreto $S$;
2. calcola $n$ share;
3. assegna una share diversa a ciascun partecipante;
4. più avanti, almeno $k$ partecipanti combinano le proprie share;
5. dalla combinazione delle share viene ricostruito $S$.

Lo schema completo di Shamir verrà approfondito nella lezione successiva. In questa lezione si introduce lo scenario e si studia una versione molto semplice, cioè lo schema:

$$
(n,n)
$$

in cui servono tutti i partecipanti per ricostruire il segreto.

---

### **4. Esempi reali di autorizzazione congiunta**

Il concetto alla base del secret sharing ha analogie naturali con situazioni reali di **autorizzazione congiunta**.

Un primo esempio è quello delle casseforti bancarie. In alcuni scenari non si vuole che una sola persona possa aprire una cassaforte. L'accesso può richiedere la collaborazione di due direttori, o comunque di più persone autorizzate.

Un secondo esempio, citato nel transcript, è quello dell'autorizzazione al lancio di un missile nucleare durante la Guerra Fredda. In uno scenario di questo tipo, l'azione è talmente critica che non deve dipendere dalla volontà di una sola persona. Può essere richiesto, per esempio, che almeno due persone su tre usino le proprie chiavi per autorizzare l'operazione.

Questi esempi non sono necessariamente schemi crittografici in senso stretto, ma mostrano la stessa esigenza:

- evitare il controllo individuale assoluto;
- richiedere collaborazione;
- consentire l'azione solo quando viene raggiunta una soglia;
- impedire a gruppi troppo piccoli di procedere.

> 💡 Il secret sharing è la versione crittografica dell'idea "nessuno da solo può decidere, ma un gruppo autorizzato può farlo".

---

### **5. Share e distribuzione segreta**

Nel secret sharing, il segreto viene suddiviso in pezzi chiamati **share**.

Ogni partecipante:

$$
P_i
$$

riceve una propria share:

$$
a_i
$$

La share contiene informazione parziale, ma non deve rivelare il segreto da sola.

La distribuzione delle share deve avvenire in modo sicuro. Questo significa che il dealer deve consegnare a ciascun partecipante solo la sua share, evitando che gli altri partecipanti o un avversario possano osservare tutte le quote.

In particolare:

- $P_1$ conosce solo la share assegnata a $P_1$;
- $P_2$ conosce solo la share assegnata a $P_2$;
- in generale $P_i$ conosce solo $a_i$;
- nessun partecipante deve conoscere le share assegnate agli altri, salvo nella fase di ricostruzione quando si decide volontariamente di collaborare.

> 📌 Le share devono essere distribuite segretamente. Se un avversario intercetta abbastanza share, può partecipare alla ricostruzione del segreto.

---

### **6. Ricostruzione e sicurezza informativa**

La fase di ricostruzione deve soddisfare due proprietà opposte.

La prima proprietà è la **correttezza**:

se almeno $k$ partecipanti collaborano, allora devono poter ricostruire il segreto:

$$
S
$$

La seconda proprietà è la **sicurezza**:

se collaborano al più $k-1$ partecipanti, allora non devono poter ricostruire il segreto e non devono ottenere informazioni sul suo valore.

In forma intuitiva, se un gruppo non autorizzato osserva alcune share, la sua conoscenza del segreto deve restare la stessa che aveva prima di vedere quelle share.

Formalmente, si può esprimere l'idea dicendo che:

$$
\Pr(S \mid \text{share osservate})=\Pr(S)
$$

quando il numero di share osservate è minore della soglia.

Questo significa che le share osservate non cambiano la distribuzione di probabilità del segreto.

> ⚠️ Non basta che il gruppo non autorizzato abbia "troppi possibili segreti" tra cui scegliere. La proprietà forte è che tutti i possibili segreti restino compatibili nello stesso modo.

---

### **7. Schema base $(n,n)$**

Vediamo ora una costruzione semplice: lo schema:

$$
(n,n)
$$

In questo schema servono tutti gli $n$ partecipanti per ricostruire il segreto.

Quindi:

- $n$ partecipanti insieme possono ricostruire $S$;
- $n-1$ partecipanti non hanno alcuna informazione su $S$.

Questa è una forma estrema di schema a soglia, perché la soglia coincide con il numero totale dei partecipanti:

$$
k=n
$$

La costruzione lavora modulo un numero primo:

$$
p
$$

scelto abbastanza grande.

Il segreto da condividere è un elemento di:

$$
\mathbb{Z}_p
$$

cioè:

$$
S\in\mathbb{Z}_p
$$

Il dealer sceglie casualmente:

$$
a_1,a_2,\dots,a_{n-1}\in\mathbb{Z}_p
$$

Poi calcola l'ultima share in modo che la somma di tutte le share sia uguale al segreto modulo $p$:

$$
a_n=S-(a_1+a_2+\dots+a_{n-1}) \bmod p
$$

A questo punto il dealer distribuisce:

$$
a_i
$$

al partecipante:

$$
P_i
$$

per ogni:

$$
i=1,\dots,n
$$

> 📌 La share finale non è scelta casualmente: è calcolata per far tornare la somma complessiva al valore del segreto.

---

### **8. Ricostruzione nello schema $(n,n)$**

Per ricostruire il segreto nello schema appena descritto, tutti i partecipanti mettono insieme le proprie share.

La regola di ricostruzione è semplicemente:

$$
S=(a_1+a_2+\dots+a_n)\bmod p
$$

Infatti, per costruzione:

$$
a_n=S-(a_1+a_2+\dots+a_{n-1})\bmod p
$$

Sostituendo:

$$
a_1+a_2+\dots+a_{n-1}+a_n
\equiv
a_1+\dots+a_{n-1}+S-(a_1+\dots+a_{n-1})
\equiv S
\pmod p
$$

Quindi, se tutte le share sono disponibili, il segreto viene ricostruito correttamente.

---

### **9. Esempio numerico: schema $(5,5)$**

Consideriamo uno schema a soglia:

$$
(5,5)
$$

Ci sono quindi cinque partecipanti e servono tutti e cinque per ricostruire il segreto.

Scegliamo:

$$
p=7
$$

e fissiamo il segreto:

$$
S=5
$$

Il dealer sceglie casualmente quattro share:

$$
a_1=3
$$

$$
a_2=2
$$

$$
a_3=1
$$

$$
a_4=2
$$

La quinta share viene calcolata come:

$$
a_5=S-a_1-a_2-a_3-a_4 \bmod 7
$$

Sostituendo i valori:

$$
a_5=5-3-2-1-2 \bmod 7
$$

cioè:

$$
a_5=-3 \bmod 7
$$

Poiché:

$$
-3\equiv 4 \pmod 7
$$

otteniamo:

$$
a_5=4
$$

Le share distribuite sono quindi:

- $P_1$ riceve $a_1=3$;
- $P_2$ riceve $a_2=2$;
- $P_3$ riceve $a_3=1$;
- $P_4$ riceve $a_4=2$;
- $P_5$ riceve $a_5=4$.

---

### **10. Ricostruzione dell'esempio**

Se tutti e cinque i partecipanti collaborano, sommano le proprie share modulo $7$:

$$
S=a_1+a_2+a_3+a_4+a_5 \bmod 7
$$

Sostituendo:

$$
S=3+2+1+2+4 \bmod 7
$$

La somma è:

$$
3+2+1+2+4=12
$$

e:

$$
12 \bmod 7=5
$$

Quindi:

$$
S=5
$$

Il segreto è stato ricostruito correttamente.

> ✅ Nello schema $(5,5)$ dell'esempio, tutte e cinque le share sono necessarie per ottenere il segreto $S=5$.

---

### **11. Cosa succede con solo quattro partecipanti**

Analizziamo ora il caso in cui solo quattro partecipanti provino a ricostruire il segreto.

Supponiamo che collaborino:

$$
P_1,P_2,P_3,P_5
$$

Questi partecipanti conoscono:

$$
a_1=3
$$

$$
a_2=2
$$

$$
a_3=1
$$

$$
a_5=4
$$

Non conoscono invece:

$$
a_4
$$

La relazione di ricostruzione sarebbe:

$$
S=a_1+a_2+a_3+a_4+a_5 \bmod 7
$$

Sostituendo le share note:

$$
S=3+2+1+a_4+4 \bmod 7
$$

cioè:

$$
S=10+a_4 \bmod 7
$$

Il problema è che ora ci sono due quantità non note:

- il segreto $S$;
- la share mancante $a_4$.

I quattro partecipanti hanno quindi una sola equazione con due incognite.

Per ogni possibile valore di $S$ in $\mathbb{Z}_7$, esiste un valore di $a_4$ che rende vera l'equazione. Infatti:

$$
a_4=S-10 \bmod 7
$$

Quindi i quattro partecipanti non possono distinguere quale sia il valore corretto di $S$.

Per esempio:

- se $S=0$, allora $a_4=0-10\equiv 4\pmod 7$;
- se $S=1$, allora $a_4=1-10\equiv 5\pmod 7$;
- se $S=2$, allora $a_4=2-10\equiv 6\pmod 7$;
- se $S=3$, allora $a_4=3-10\equiv 0\pmod 7$;
- se $S=4$, allora $a_4=4-10\equiv 1\pmod 7$;
- se $S=5$, allora $a_4=5-10\equiv 2\pmod 7$;
- se $S=6$, allora $a_4=6-10\equiv 3\pmod 7$.

Tutti i possibili valori del segreto restano compatibili con una qualche share mancante.

> 📌 Questo è il punto centrale: con quattro share su cinque non è solo impossibile ricostruire $S$; è impossibile anche restringere l'insieme dei possibili valori di $S$.

---

### **12. Perché lo schema $(n,n)$ è sicuro**

La sicurezza dello schema $(n,n)$ deriva dalla casualità delle prime $n-1$ share.

Le share:

$$
a_1,\dots,a_{n-1}
$$

sono scelte casualmente in:

$$
\mathbb{Z}_p
$$

L'ultima share è calcolata in modo da chiudere la somma al valore del segreto.

Se manca anche una sola share, quella share mancante può assumere qualunque valore di $\mathbb{Z}_p$. Di conseguenza, anche il segreto può essere qualunque elemento di $\mathbb{Z}_p$.

Questo garantisce che un insieme di $n-1$ partecipanti non ottenga informazione su $S$.

In termini intuitivi:

- con tutte le share, la somma è determinata;
- senza una share, la somma può essere completata in qualunque modo;
- quindi il segreto resta completamente nascosto.

> ⚠️ La sicurezza vale se le share casuali sono davvero scelte uniformemente e se la distribuzione delle share avviene tramite canali sicuri.

---

### **13. Limite dello schema $(n,n)$**

Lo schema $(n,n)$ è semplice e sicuro, ma è anche rigido.

Richiede infatti la collaborazione di tutti i partecipanti. Se anche un solo partecipante:

- è assente;
- perde la propria share;
- si rifiuta di collaborare;
- viene compromesso o non è disponibile;

il segreto non può essere ricostruito.

Per questo motivo, nella pratica sono spesso preferibili schemi:

$$
(k,n)
$$

con:

$$
k<n
$$

In uno schema di questo tipo, non serve la partecipazione di tutti: basta raggiungere una soglia minima $k$.

Lo schema di Shamir completo, che verrà approfondito nella lezione successiva, permette proprio di costruire schemi a soglia generali.

---

### **14. Sintesi finale**

In questa lezione abbiamo introdotto il problema della **condivisione del segreto**.

I punti principali sono:

- in molti contesti crittografici serve proteggere un segreto senza affidarlo a una sola persona;
- il dealer possiede il segreto $S$ e lo distribuisce tra $n$ partecipanti;
- ogni partecipante riceve una share;
- in uno schema $(k,n)$, almeno $k$ partecipanti possono ricostruire il segreto;
- meno di $k$ partecipanti non devono ricostruire il segreto e non devono ottenere informazioni su di esso;
- esempi intuitivi di autorizzazione congiunta sono l'accesso a casseforti bancarie e le procedure critiche che richiedono più chiavi;
- Shamir propose nel 1979 schemi a soglia per risolvere questo problema;
- lo schema semplice $(n,n)$ sceglie $n-1$ share casuali in $\mathbb{Z}_p$ e calcola l'ultima come:

$$
a_n=S-(a_1+\dots+a_{n-1})\bmod p
$$

- il segreto si ricostruisce sommando tutte le share modulo $p$;
- nell'esempio $(5,5)$ con $p=7$ e $S=5$, le share $3,2,1,2,4$ ricostruiscono correttamente il segreto;
- con solo quattro share resta un'equazione con due incognite, quindi tutti i valori del segreto restano possibili;
- lo schema $(n,n)$ è sicuro ma poco flessibile, perché richiede tutti i partecipanti.

> ✅ Il secret sharing permette di distribuire un segreto in modo che solo un gruppo autorizzato possa ricostruirlo; la versione $(n,n)$ è il caso più semplice, mentre lo schema di Shamir generalizza l'idea a soglie arbitrarie $(k,n)$.
