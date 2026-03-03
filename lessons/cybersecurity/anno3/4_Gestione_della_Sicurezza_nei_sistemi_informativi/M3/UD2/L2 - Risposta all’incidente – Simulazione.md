# **Lezione 2: Risposta all’incidente – Simulazione**

### 1. Presupposto della simulazione

La lezione si colloca nel contesto di una simulazione operativa e parte dal presupposto che l’organizzazione sia già ben strutturata dal punto di vista della sicurezza. In particolare, si assume che sia stata effettuata una valutazione dei rischi e che siano state implementate tutte le misure necessarie per ridurre o documentare il rischio.

Questo scenario rappresenta una situazione ideale nella quale è possibile osservare come devono essere gestiti concretamente diversi tipi di incidenti informatici, analizzando procedure operative specifiche per ciascun caso.

---

### 2. Procedura di risposta all’incidente: Cryptolocker

Il primo caso analizzato riguarda un attacco di tipo cryptolocker, cioè un ransomware che cifra i dati della vittima.

La procedura operativa prevede innanzitutto l’isolamento immediato del sistema compromesso. In particolare, occorre scollegare sia il cavo di alimentazione sia il cavo di rete del computer, evitando che il malware possa propagarsi ad altri sistemi.

Successivamente deve essere fornito all’utente un sistema alternativo per consentire la continuità operativa e devono essere verificati eventuali danni ai sistemi collegati in rete, come file server o archivi di posta elettronica.

È necessario contattare il CSIRT aziendale e procedere con l’acquisizione forense del computer compromesso per consentire eventuali analisi successive.

Il sistema deve poi essere reinstallato completamente da zero e l’incidente deve essere censito e registrato con il massimo livello di dettaglio possibile fino alla completa risoluzione.

Questa procedura evidenzia l’importanza dell’isolamento tempestivo e della documentazione accurata.

---

### 3. Procedura di risposta all’incidente: dipendente infedele

Il secondo caso riguarda il sospetto di comportamento illecito da parte di un dipendente.

In questa situazione è necessario coinvolgere immediatamente il CSIRT, l’ufficio legale e un consulente tecnico informatico, poiché potrebbero essere necessari provvedimenti disciplinari o azioni giudiziarie.

Il dispositivo informatico deve essere raccolto con documentazione firmata relativa alla restituzione e successivamente sottoposto ad acquisizione forense.

Segue l’analisi tecnica del sistema informatico e la registrazione dettagliata dell’incidente fino alla sua conclusione.

Gli esiti possibili sono due:

- Se il sistema risulta pulito, il dispositivo viene restituito al dipendente.
    
- Se emergono pratiche illecite, viene redatta una relazione tecnica forense e si procede con eventuali azioni giudiziarie.
    

Questo caso mostra il collegamento tra gestione degli incidenti e diritto del lavoro.

---

### 4. Procedura di risposta all’incidente: intervento dell’autorità giudiziaria

Un ulteriore scenario riguarda l’intervento dell’autorità giudiziaria, ad esempio in caso di perquisizione o sequestro.

In questo caso è necessario coinvolgere CSIRT, ufficio legale e consulente tecnico informatico.

L’ufficio legale definisce il perimetro giuridico dell’intervento, mentre il consulente tecnico valuta gli aspetti tecnici e verifica la correttezza delle operazioni effettuate dalle autorità.

Il CSIRT ha il compito di assistere l’autorità giudiziaria durante le operazioni.

È importante richiedere l’acquisizione forense dei sistemi con calcolo dell’hash, documentare tutte le operazioni e registrare l’incidente fino alla completa conclusione.

Al termine dell’intervento, se i sistemi risultano sequestrati o inutilizzabili, l’organizzazione deve fornire nuovi strumenti al personale per garantire la continuità operativa.

Questo scenario evidenzia la necessità di preparazione preventiva anche per eventi legali.

---

### 5. Gestione degli incidenti: lista dei contatti

La gestione efficace degli incidenti richiede una lista di contatti aggiornata con le figure chiave da coinvolgere.

Tra queste vi sono:

- Responsabile della sicurezza aziendale
    
- Responsabile del CSIRT
    
- Referente IT
    
- Referente dell’ufficio legale
    
- Responsabile delle pubbliche relazioni o ufficio stampa
    
- Consulente tecnico informatico
    
- Referenti delle forze dell’ordine
    
- Internet Service Provider
    
- Altri ruoli specifici
    

La disponibilità immediata dei contatti riduce i tempi di risposta e migliora l’efficacia della gestione dell’incidente.

---

### 6. Catena di custodia e log dell’incidente

La documentazione rappresenta un elemento fondamentale nella gestione degli incidenti.

Nella slide a pagina 4 sono mostrati esempi di moduli per la gestione della catena di custodia e per il log dell’incidente.

Il modulo di catena di custodia include informazioni come identificativo del reperto, descrizione del dispositivo, modalità di acquisizione, passaggi di consegna tra soggetti, firme e motivazioni dei trasferimenti.

Il modulo di log dell’incidente include informazioni su data e ora, modalità di comunicazione, soggetti coinvolti, sistemi interessati e dettagli dell’evento.

Questi documenti garantiscono tracciabilità e validità probatoria delle attività svolte.

---

### 7. Isolamento, ripristino e backup certificato

La fase finale della gestione dell’incidente riguarda l’isolamento dei sistemi compromessi, il ripristino operativo e l’utilizzo di backup certificati.

Il backup certificato rappresenta una garanzia di integrità dei dati e consente di ripristinare rapidamente i sistemi senza compromettere la validità delle prove.

Questa fase consente il ritorno alla normale operatività mantenendo la sicurezza.

---

### 8. Sintesi finale

La lezione ha presentato una simulazione pratica della risposta agli incidenti informatici, analizzando tre scenari principali: attacco ransomware, dipendente infedele e intervento dell’autorità giudiziaria.

Sono stati evidenziati il ruolo delle diverse figure organizzative, l’importanza della documentazione, la gestione della catena di custodia e la necessità di garantire continuità operativa.

Il messaggio principale è che qualsiasi evento anomalo nei sistemi informativi deve essere documentato in modo dettagliato, poiché potrebbe assumere rilevanza legale o organizzativa in futuro.