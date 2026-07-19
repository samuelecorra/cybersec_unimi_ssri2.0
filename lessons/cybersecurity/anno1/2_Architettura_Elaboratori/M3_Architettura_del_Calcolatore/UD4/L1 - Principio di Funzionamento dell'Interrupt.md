## ***Lezione 1: Principio di funzionamento dell’interrupt***

---

> 📌 Questa lezione rielabora integralmente le pagine 41–44 di `M3doc.pdf`, incluse sequenza temporale e struttura della routine di risposta.

### **1. La periferica richiede servizio**

Con l’**interrupt** una periferica segnala alla CPU che si è verificato un evento che richiede servizio. Sul bus di controllo è presente una linea dedicata `INTREQ`, abbreviazione di *Interrupt Request*.

Nel modello del PDF `INTREQ` è **attiva bassa**:

$$
INTREQ=0\Rightarrow\text{richiesta presente},
\qquad
INTREQ=1\Rightarrow\text{nessuna richiesta}.
$$

La CPU non deve più scoprire l’evento mediante interrogazioni periodiche. È il mondo esterno a imporre il proprio tempo, secondo la prospettiva “copernicana” introdotta nella lezione precedente. Ciò rende l’interrupt adatto ai fenomeni urgenti.

> ⚠️ L’interrupt riduce e rende controllabile la latenza di riconoscimento, ma non è la soluzione efficiente per trasferire migliaia di byte uno alla volta: per quello serve il DMA.

### **2. Un trasferimento di controllo asincrono**

L’interrupt assomiglia a una chiamata di sottoprogramma perché la CPU:

1. interrompe il flusso corrente;
2. esegue una routine dedicata;
3. torna al programma sospeso.

La differenza è sostanziale: una `CALL` è un’istruzione voluta dal programma e collocata in una posizione determinata; un interrupt è una chiamata **asincrona**, provocata dall’hardware esterno in un punto non scelto dal programma.

Il programma interrotto deve osservare soltanto un ritardo. Al ritorno, il suo stato logico deve essere quello che avrebbe avuto senza l’intervento della routine.

### **3. Le cinque fasi dell’interrupt**

#### **3.1. Richiesta**

La periferica o la sua interfaccia attiva `INTREQ`.

#### **3.2. Riconoscimento**

La CPU completa l’istruzione in corso e, prima del fetch successivo, verifica la richiesta. Se può accettarla, attiva `INTACK`, *Interrupt Acknowledge*.

La scelta del confine tra istruzioni è essenziale: il programma viene sospeso in uno stato architetturale coerente, non a metà degli effetti di un’istruzione.

#### **3.3. Salvataggio automatico del punto di ripresa**

La CPU salva automaticamente il valore corrente del Program Counter prima di caricare l’indirizzo della routine. Il PC salvato identifica la prossima istruzione del programma interrotto.

La CPU disabilita inoltre, almeno inizialmente, il riconoscimento di ulteriori interrupt per non entrare ricorsivamente in nuove routine senza una politica esplicita.

#### **3.4. Routine di risposta all’interrupt**

La **RRI** identifica la sorgente, gestisce l’evento e preserva il contesto. Oltre al PC già salvato dall’hardware, la routine deve salvare i registri che userà, compresi eventuali codici di condizione, e ripristinarli prima del ritorno.

Una struttura sicura è:

1. prologo: salvataggio dei registri;
2. identificazione e servizio della periferica;
3. cancellazione o conferma della condizione che ha provocato la richiesta;
4. epilogo: ripristino del contesto;
5. istruzione specifica di ritorno da interrupt.

> 📌 La trasparenza richiede che variabili, memoria e registri del programma non siano “sporcati” dalla RRI. Anche il registro dei codici di condizione può essere necessario per riprendere correttamente un salto condizionato.

#### **3.5. Ritorno**

La RRI ripristina il contesto e il PC precedentemente salvato. La CPU riprende dal punto successivo all’istruzione completata prima dell’interrupt.

### **4. Perché la linea condivisa è open-collector**

Più interfacce possono richiedere un interrupt in istanti indipendenti. Non esiste un arbitro che impedisca richieste contemporanee prima che esse avvengano. `INTREQ` deve quindi essere una linea multisorgente asincrona:

- una resistenza di pull-up la mantiene a `1` quando nessuno richiede servizio;
- ogni interfaccia dispone di un’uscita open-collector;
- una o più richieste portano la linea a `0` senza conflitto elettrico.

Il risultato è una richiesta aggregata: la CPU sa che almeno una periferica necessita servizio, ma non ancora quale.

### **5. Problemi ancora aperti**

La struttura di base lascia tre domande:

1. se più periferiche richiedono servizio, quale ha la precedenza?
2. come si identifica la sorgente della richiesta condivisa?
3. durante una RRI, quali altre periferiche possono interrompere la CPU?

Le realizzazioni cablata e vettorizzata rispondono in modo diverso.

> ✅ Un interrupt è un trasferimento hardware asincrono eseguito tra due istruzioni: richiesta, riconoscimento, salvataggio, routine trasparente e ritorno formano un protocollo completo.
