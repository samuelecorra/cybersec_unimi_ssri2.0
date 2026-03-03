# M6 UD1 L2 - Un framework per i diritti di cittadinanza digitale - la tutela dei diritti

### **Livello 0 – The Net**

### **1. La Rete come infrastruttura**

La Rete, con la “R” maiuscola, rappresenta il backbone delle comunicazioni contemporanee. È l’infrastruttura invisibile che consente la trasmissione di informazioni su scala globale.

Internet trasporta pacchetti di dati, cioè insiemi di byte, da una sorgente a una destinazione, cercando il percorso più efficiente possibile.

Ma cosa significa “più efficiente”? Più veloce? Più breve? Più economico? Più stabile?

Qui entra in gioco un concetto fondamentale: la relatività dell’osservatore.

---

### **2. Relatività e rete**

Un’analogia forte è quella con la teoria della relatività.

Due osservatori di uno stesso evento fisico non percepiscono esattamente le stesse cose, perché l’informazione viaggia a velocità finita e può subire distorsioni dovute al mezzo attraversato o a campi gravitazionali.

In rete la situazione è ancora più complessa.

La “propagazione della luce” diventa “trasmissione dei dati”. Tuttavia:

- i dati non viaggiano in linea retta;
    
- la rete è un grafo irregolare;
    
- i percorsi possono variare dinamicamente;
    
- la velocità dipende dai nodi attraversati;
    
- i dati possono essere modificati, persi o cancellati.
    

Da chi?  
Perché?

Lo stato complessivo della rete non è conoscibile integralmente da nessun singolo osservatore.

---

### **3. Domande fondamentali**

A questo punto emergono alcune domande strutturali:

Chiunque può vedere qualunque sito web o accedere a tutti i servizi di un generico server?

Chiunque accede con la stessa velocità?

Dato un sito specifico, tutti lo vedono allo stesso modo?

Le risposte, come vedremo, sono negative.

---

### **TCP/IP**

### **4. Struttura a livelli**

Transmission Control Protocol / Internet Protocol è un insieme di standard di trasmissione organizzati a livelli.

Ai livelli bassi del pacchetto dati troviamo informazioni essenziali come:

- mittente
    
- destinatario
    
- contenuto
    

Durante il tragitto, i pacchetti possono essere elaborati.

---

### **5. Modifiche lungo il percorso**

Le elaborazioni possibili includono:

NAT (Network Address Translation), che modifica il mittente per non esporre indirizzi interni o privati.

Modifica del contenuto, ad esempio per eliminare virus o contenuti ritenuti dannosi, oppure per nascondere informazioni.

Modifica del destinatario, come nel caso dei mirror che rendono raggiungibile un sito temporaneamente offline.

Modifica del tragitto, per velocizzare o rallentare alcune connessioni.

Molte di queste tecniche nascono con intento positivo: migliorare efficienza e sicurezza.

Ma possono avere implicazioni problematiche.

---

### **Quality of Service (QoS)**

### **6. Efficienza o scarsità artificiale?**

Quality of Service è un insieme di tecniche che privilegiano alcuni flussi di traffico (ad esempio video o voce) rispetto ad altri.

Questo rende la trasmissione più efficace per determinati servizi.

Tuttavia, QoS può creare scarsità artificiale:

offrire livelli di servizio diversi a fronte di tariffe differenti;

abbattere traffico concorrente in presenza di conflitti di interesse.

Un caso noto riguarda il provider Comcast, in cui fu dimostrato un abbassamento automatico della velocità durante l’utilizzo di protocolli peer-to-peer associati allo scambio di file protetti da copyright.

Si punisce il mezzo invece dell’azione.

---

### **7. Firewalling e Deep Packet Inspection**

Firewalling è una forma di controllo binario: un dato passa o non passa.

Serve per:

protezione da attacchi esterni;

limitare usi ritenuti impropri della rete interna.

Deep Packet Inspection rappresenta un livello ulteriore: ispezione del contenuto dei pacchetti (wiretapping).

Qui emerge chiaramente la dimensione del (tecno)controllo.

---

### **(Tecno)controllo**

### **8. Target-based content**

Il contenuto può essere modificato:

sul web server;

in un qualsiasi punto del tragitto (man-in-the-middle).

L’adattamento può essere positivo, come nel caso delle versioni mobili dei siti.

Ma può anche essere usato per mostrare contenuti diversi a richiedenti diversi.

Il filtraggio può essere:

IP-based;  
browser-based;  
time-based.

---

### **9. DNS-based censorship**

Un’altra tecnica è la censura basata su DNS.

Si fa “sparire” un sito oscurandone il nome.

È un metodo ingenuo, funziona solo se l’utente non dispone di altri sistemi di risoluzione del nome.

È facilmente aggirabile, ma è stato ed è utilizzato, anche in Italia.

---

### **10. Monitoraggio e previsione**

Il controllo può assumere forme più sofisticate.

Profilazione degli utenti sulla base del traffico di rete.

Analisi di livelli superiori, come reti sociali e relazioni.

Previsione di comportamento, evocando scenari simili a “Minority Report”.

Qui si collega il riferimento a Stefano Rodotà e al concetto espresso ne _Il diritto di avere diritti_ (2012), in particolare il diritto alla diversità.

---

### **Risposte alle domande iniziali**

### **11. Accesso universale?**

Chiunque può vedere qualunque sito?

No.

In Italia alcuni siti di gioco online non autorizzati vengono oscurati.

In Cina il firewall di stato oscura gran parte della rete esterna.

---

### **12. Stessa velocità per tutti?**

No.

QoS e tariffazione differenziata producono velocità diverse.

---

### **13. Stessa esperienza per tutti?**

No.

Il contenuto può essere adattato in funzione:

della provenienza geografica;

del browser utilizzato;

di altre variabili.

---

### **Difendersi**

### **14. Struttura a cipolla**

Una possibile strategia è l’uso di reti overlay come Tor, basate su una struttura a cipolla.

L’idea è incapsulare i dati in più livelli crittografici, in modo che ogni nodo conosca solo il precedente e il successivo.

Tor consente di:

proteggere anonimato;

aggirare censura;

ridurre tracciabilità.

Tuttavia esiste il problema degli exit node, cioè della responsabilità del nodo finale da cui il traffico esce in chiaro.

Esistono anche hidden services.

Strumenti come Vidalia o sistemi come TAILS forniscono bundle preconfigurati.

---

### **15. Diritto all’anonimato**

Periodicamente emergono proposte politiche per limitare o eliminare l’anonimato in rete, spesso giustificate dalla lotta alla pedopornografia.

Tali proposte mostrano spesso scarsa comprensione tecnica del funzionamento della rete.

Nel 2009 vi fu una proposta in tal senso, poi abbandonata.

La questione rimane ciclicamente oggetto di dibattito.

---

### **Livello 1 – Access**

### **16. Servizi minimi di cittadinanza digitale**

Quali sono i servizi minimi necessari per una piena cittadinanza digitale?

Qual è l’equivalente tecnologico di acqua, luce, gas, trasporti, sanità?

È sufficiente il semplice accesso alla rete?

La metafora della strada aiuta: avere accesso non significa avere un’autostrada efficiente e accessibile a tutti.

Occorrono reti veloci, a prezzi sostenibili.

PEC funzionante.

Standard aperti e interoperabili.

Eventualmente un “cloud civico”.

---

### **Barriere**

### **17. Barriere digitali**

Esistono barriere architettoniche anche nel mondo digitale.

Barriere culturali.

Barriere tecnologiche, come strumenti e formati proprietari.

Barriere legislative, come normative restrittive o tentativi di controllo.

Barriere finanziarie, come alta pressione fiscale sulle imprese.

Queste barriere producono digital divide.

---

### **Digital Divide**

### **18. Disuguaglianze territoriali**

Il divario digitale si manifesta:

fra regioni italiane;

fra stati europei;

fra aree del mondo.

Non tutti hanno lo stesso livello di accesso, velocità e qualità dei servizi.

---

### **Metafora stradale**

### **19. Requisiti per “circolare”**

Per circolare nel mondo digitale occorre:

un mezzo omologato;

una patente (competenze);

assicurazione e tasse;

risorse economiche per l’accesso.

L’asticella si alza continuamente.

Possono emergere barriere protezionistiche all’ingresso di nuovi produttori.

Le omologazioni possono diventare sempre più onerose.

---

### **Raccolta informazioni**

### **1. Information leaking / gathering**

I dati sono spesso definiti “il nuovo petrolio”. La metafora è potente: come il petrolio, il dato grezzo ha valore solo se estratto, raffinato e utilizzato.

L’attività di raccolta di informazioni può essere estremamente proficua, sia economicamente sia politicamente.

La raccolta può essere:

**consapevole**, quando l’utente fornisce direttamente informazioni, ad esempio:

- compilando un form;
    
- registrandosi a un servizio;
    
- pubblicando su un social network;
    
- scrivendo un blog post.
    

Oppure **inconsapevole**, quando:

- vengono installati cookie;
    
- il provider analizza il traffico;
    
- vengono utilizzati strumenti invasivi senza piena consapevolezza dell’utente.
    

La differenza tra consapevolezza e inconsapevolezza è centrale per la tutela dei diritti.

---

### **Contatori intelligenti**

### **2. Smart meters e inferenza comportamentale**

I contatori intelligenti registrano in modo dettagliato i consumi energetici.

Da tali dati è possibile inferire attività domestiche: quando viene acceso il forno, la lavatrice, il bollitore, il riscaldamento.

Il consumo elettrico produce “firme” riconoscibili.

Non si tratta solo di sapere quanta energia si consuma, ma di ricostruire abitudini di vita.

Questo apre interrogativi su:

- privacy;
    
- sicurezza;
    
- profilazione.
    

Un dato apparentemente neutro può diventare altamente informativo.

---

### **Telefonia mobile**

### **3. Dati di traffico e localizzazione**

La telefonia mobile genera una quantità enorme di metadati:

- chiamate in entrata e in uscita;
    
- durata delle comunicazioni;
    
- SMS;
    
- connessioni dati;
    
- localizzazione tramite celle.
    

Anche senza conoscere il contenuto delle comunicazioni, l’analisi dei metadati consente di ricostruire:

- spostamenti;
    
- reti relazionali;
    
- abitudini quotidiane.
    

Il caso di Malte Spitz ha mostrato come, pubblicando i dati di traffico telefonico, fosse possibile ricostruire con precisione la vita quotidiana di una persona.

Il metadato è spesso più potente del dato.

---

### **Profilazione**

### **4. Analisi del comportamento**

La profilazione consiste in:

- analisi del comportamento;
    
- classificazione in categorie;
    
- previsione di azioni future.
    

Il concetto di “dato aberrante” richiama la possibilità di individuare deviazioni statistiche rispetto a un comportamento atteso.

Qui emerge il riferimento a scenari simili a _Minority Report_: prevenzione del crimine attraverso la previsione.

Il rischio è quello di sostituire la responsabilità individuale con una presunzione algoritmica.

---

### **Strumenti di difesa**

### **5. Strumenti tecnologici**

Tra gli strumenti di difesa si citano:

- Tor;
    
- steganografia;
    
- crittografia;
    
- moneta digitale anonima.
    

Tuttavia esiste il rischio della criminalizzazione degli strumenti.

La crittografia, ad esempio, può essere vista come mezzo legittimo di tutela della privacy oppure come ostacolo alle indagini.

La famosa vignetta sulla crittografia mostra una contrapposizione ironica: la sicurezza matematica può essere aggirata con la coercizione fisica.

La sicurezza tecnica non elimina il problema del potere.

---

### **Barriere strumentali: formati**

### **6. Il concetto di formato**

Un formato è il modo di organizzare e interpretare dati in un file.

Una sequenza di bit non ha significato intrinseco.

La semantica è data dall’interprete, cioè dal programma che legge quei bit.

Qui si collega il riferimento alla Macchina di Turing: il significato dipende dalla macchina che esegue l’interpretazione.

---

### **7. Formati testuali e binari**

Si distinguono:

Formati testuali, come HTML, XML.

Formati binari, come immagini o documenti proprietari.

La differenza non è solo tecnica, ma politica.

Un formato può essere:

- de facto, cioè semplicemente diffuso;
    
- de iure, cioè formalmente approvato;
    
- aperto, cioè utilizzabile liberamente;
    
- chiuso, soggetto a restrizioni legali.
    

Lo standard è un concetto più ampio che include protocolli, linguaggi e specifiche tecniche.

Il reverse engineering può consentire l’interoperabilità, ma può essere soggetto a limiti legali.

---

### **8. Formato come servizio**

Spesso formato significa programma.

Se per accedere a un dato è necessario un determinato software, l’accesso al formato diventa accesso a un servizio.

Esempi problematici:

- PDF scansionati non accessibili;
    
- moduli compilabili solo in formato proprietario;
    
- streaming basato su tecnologie chiuse.
    

Il formato può diventare barriera.

---

### **Livello 2 – Consapevolezza**

### **9. Competenza vs consapevolezza**

Le tecnologie informatiche sono più complesse di servizi tradizionali.

Nel passato si è enfatizzata la competenza pratica.

Ma è ancora più importante la consapevolezza:

- sapere come funzionano i sistemi;
    
- conoscere i principi sottostanti;
    
- comprendere le conseguenze delle proprie azioni.
    

---

### **Principio di Locard**

### **10. Ogni contatto lascia una traccia**

Il principio di Locard afferma che ogni contatto lascia una traccia.

Nel digitale questo principio è amplificato.

La vita digitale si estende oltre il corpo fisico, nello spazio e nel tempo.

Tracce includono:

- dati fiscali;
    
- acquisti online;
    
- tag sui social;
    
- GPS;
    
- videosorveglianza;
    
- carte fedeltà;
    
- telepass.
    

La lista è potenzialmente infinita.

---

### **Traccia di byte**

### **11. Domande fondamentali**

Di fronte a una sequenza di byte occorre chiedersi:

Cosa significa?  
Chi l’ha scritta?  
Quando è stata creata?  
Quando decadrà?  
Chi ne è responsabile?  
Quale legislazione si applica?  
Dove si trova fisicamente?

Queste domande sono giuridiche oltre che tecniche.

---

### **Metadati e degradazione**

### **12. Metadati e manipolazione**

I metadati descrivono i dati.

Possono essere falsificati (forging).

Le tracce possono degradarsi o essere alterate.

L’informazione non è mai neutra né eterna nella forma, ma può essere persistente nella sostanza.

---

### **Orizzonte degli eventi**

### **13. Confine irreversibile**

La metafora dell’orizzonte degli eventi descrive il punto oltre il quale i dati escono dal controllo.

Nel digitale l’orizzonte degli eventi è in continua contrazione.

Una volta che i dati sono pubblicati o condivisi, recuperarli integralmente è praticamente impossibile.

La traccia digitale tende a non morire.

---

### **Azioni di difesa**

### **14. Attacchi normativi**

Nel tempo vi sono stati tentativi legislativi percepiti come limitativi:

- ACTA;
    
- SOPA;
    
- PIPA;
    
- HADOPI.
    

Queste iniziative hanno sollevato dibattiti su libertà, neutralità e apertura della rete.

---

### **15. Controllo tecnologico industriale**

Le industrie hanno promosso strumenti come:

- DRM;
    
- UEFI.
    

Questi strumenti possono incanalare l’utente in ecosistemi controllati.

Spesso tali dinamiche ricevono poca attenzione mediatica.

---

### **Nostro ruolo**

### **16. Conoscere e far conoscere**

Il ruolo dell’utente consapevole è:

- conoscere;
    
- divulgare;
    
- sviluppare spirito critico;
    
- domandarsi sempre “cui prodest?”.
    

La consapevolezza è il primo strumento di difesa.

---

### **Livello 3 – Online service**

### **17. Diritto ai servizi online**

Esiste un diritto a usufruire di servizi online che sostituiscono o affiancano quelli offline.

Ma spesso manca una vera exit option.

L’utente deve adeguarsi a:

- standard;
    
- formati;
    
- applicativi;
    
- tempi;
    
- costi imposti.
    

La mancanza di concorrenza amplifica il problema.

Se un servizio è erogato solo online, occorre prima garantire accesso universale.

---

### **Garanzie necessarie**

### **18. Condizioni per l’erogazione digitale**

Per garantire diritti nei servizi online occorrono:

- deburocratizzazione;
    
- usabilità;
    
- accessibilità;
    
- rispetto delle tempistiche;
    
- tutela della privacy;
    
- corretto trattamento dei dati;
    
- condivisione trasparente tra enti;
    
- accesso ai dati pubblici (open data);
    
- civic hacking.
    

Il livello 3 chiude il percorso iniziato con il livello 0.

Dalla struttura tecnica della rete si arriva alla dimensione dei diritti nei servizi digitali.

L’informatica è infrastruttura, strumento di potere, ambiente sociale e spazio giuridico.

Comprenderla significa comprendere le trasformazioni profonde della cittadinanza.