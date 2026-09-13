# **Lezione 14: Accesso fisico ad elevata sicurezza e Automated Border Control**

---

### **0. Panoramica della lezione**

_(slide pp. 1–3)_

La lezione affronta l'**accesso fisico ad elevata sicurezza** usando come caso di studio uno degli scenari più complessi: l'**Automated Border Control** (ABC), cioè l'automazione dei controlli di frontiera.

**Outline** (p. 3):

1. **Sicurezza fisica**: basi ed elementi, focus su biometria e videosorveglianza.
2. **Automated Border Control**:
   - introduzione e contesto;
   - tailgating, piggybacking e contromisure;
   - biometria monomodale impiegata;
   - biometria multimodale;
   - biometria sui terminali mobili per ABC;
   - esempi applicativi (sistema multimodale Vision-Box Happyflow).

> ⚠️ **Nome corretto.** Nell'outline (pp. 3 e 80) la sigla è sciolta come «Advanced border control»; il nome corretto, usato nel resto delle slide e nella dispensa, è **Automated Border Control**.

**Materiali di accompagnamento**:

| Materiale | Contenuto | Status per l'esame |
|---|---|---|
| **Dispensa ABC** | R. Donida Labati, A. Genovese, E. Muñoz, V. Piuri, F. Scotti, G. Sforza, «Biometric Recognition in Automated Border Control: A Survey», *ACM Computing Surveys*, 2016 | **materiale di consultazione**: non richiesto all'esame (slide pp. 23 e 79) |
| **Brochure Pelco** | «Guide to Physical Security: Controls, Planning, Policies & Measures», ebook commerciale di Pelco Inc., 2023 | materiale di contesto di un produttore terzo |

> 📌 **Che cosa si studia.** All'esame è richiesto il contenuto **delle slide**. Dispensa e brochure servono a chiarire i dubbi; nel testo i loro contenuti sono indicati esplicitamente.

---

## **Parte A — La sicurezza fisica**

### **1. Perché ripassare la sicurezza fisica**

_(slide pp. 4–7)_

Si ripassano le basi della sicurezza fisica per capire **quali sfide applicative** devono risolvere i sistemi avanzati basati su biometria e videosorveglianza biometrica, e come queste sfide **guidano la progettazione** (p. 5).

**Sicurezza logica contro sicurezza fisica** (p. 6):

| | **Sicurezza logica** | **Sicurezza fisica** |
|---|---|---|
| Protegge | dati, servizi e risorse **digitali** | **persone, spazi e infrastrutture** |
| Strumenti | account, password e PIN, token, certificati | porte, tornelli, varchi, aree controllate |
| Ruolo della biometria | biometria **come informazione** | biometria **come corpo** |
| Domanda | «Sei autorizzato ad **accedere** a questa informazione?» | «Sei autorizzato a **entrare** qui dentro?» |

**Quando falliscono** (p. 7):

| | **Sicurezza logica** | **Sicurezza fisica** |
|---|---|---|
| Conseguenze | compromissione di dati, servizi e account; perdite economiche e trasferimenti non autorizzati; frodi | compromissione di spazi e infrastrutture critiche; furto e danneggiamento; **pericolo per le persone** |
| Gestione | **Incident Response** (ISO/IEC 27035, gestione degli incidenti di sicurezza delle informazioni) | **Crisis Management** (ISO 22361, gestione delle crisi) |

> 💡 **«Un errore logico è un incidente. Un errore fisico è un evento.»** Il motto della slide sottolinea la differenza di gravità: un conto svuotato (l'immagine mostra un saldo di 0 euro) si gestisce come incidente e spesso si recupera; un'intrusione fisica può mettere a rischio l'incolumità delle persone e diventa una crisi da gestire.

<!-- SOURCE_VISUAL id="TAB14-V01" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="4" type="pagina di sezione" description="Sicurezza fisica: terminologia ed elementi principali" reason="Pagina grafica di apertura della parte" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Definizione ed elementi della sicurezza fisica**

_(slide pp. 8–10)_

_(brochure pp. 1–2)_

> 📌 **Sicurezza fisica** (definizione per questo insegnamento, p. 8). La **protezione di persone, proprietà e asset** da danni o accessi non autorizzati attraverso l'uso di **misure preventive e reattive**.

- Include la protezione fisica di **attrezzature e tecnologia**, compresi i sistemi di memorizzazione dei dati (per esempio di ricerca e sviluppo), i server e i computer dei dipendenti. Esempio della slide: una **sala server** con diverse GPU **Nvidia H200** da circa 30.000 euro l'una.
- Alcuni piani di sicurezza sono determinati da **fattori ambientali** (disposizione del sito, installazione di sistemi biometrici, di videosorveglianza e di allarme), altri da **fattori comportamentali** (formazione del personale).
- La sicurezza fisica viene descritta scherzosamente come **«guardie e cancelli»**, ma i sistemi moderni comprendono **molti elementi**.

**I sette elementi principali** (pp. 9–10):

| # | Elemento | Domanda o contenuto |
|:---:|---|---|
| 1 | **Configurazione del sito e sicurezza** | dove sono i punti deboli? che cosa ha bisogno di più protezione? |
| 2 | **Visibilità delle aree critiche** | illuminazione e videocamere |
| 3 | **Controllo degli accessi** | da semplici serrature a tastiere e varchi biometrici |
| 4 | **Protezione del perimetro** | l'aspetto «guardie e cancelli» |
| 5 | **Rilevazione di intrusioni e anomalie** | sensori di movimento, telecamere, allarmi |
| 6 | **Protezione delle infrastrutture** | energia, acqua, antincendio, connettività di rete cablata e wireless |
| 7 | **Formazione del personale e risposta agli incidenti** | i dipendenti sanno gestire un incidente? esiste un processo di risposta alle emergenze? |

> 💡 **La fonte.** La definizione e i sette elementi corrispondono all'impostazione della brochure Pelco (pp. 1–2), che li presenta nella stessa sequenza.

---

### **3. Minacce, vulnerabilità e rischi**

_(slide p. 11)_

_(brochure p. 3)_

| Minaccia | Descrizione |
|---|---|
| **Accesso non autorizzato** | tailgating, ingegneria sociale, pass o codici rubati. Le prime violazioni avvengono **al primo punto di ingresso**: se un visitatore indesiderato entra, è solo questione di tempo prima che si verifichino altre minacce |
| **Furto e rapina** | attrezzature, documenti e **proprietà intellettuale**, badge, materiali. Alcune aziende sono molto esposte per ciò che custodiscono (gioiellerie, negozi di tecnologia) o per le informazioni che trattano (società di gestione patrimoniale) |
| **Vandalismo** | legato alla **posizione** (per esempio vicino a uno stadio; nella brochure, vicino a bar e locali notturni) o a **motivazioni ideologiche** di attivisti |
| **Terrorismo** | citato nelle slide come ulteriore minaccia |

La brochure aggiunge che ogni azienda, facendo la propria **valutazione del rischio**, scopre rischi specifici del proprio settore e della propria posizione.

---

### **4. Come si usano le tecnologie biometriche**

_(slide pp. 12–14)_

**Prima dei problemi (ex ante)** (pp. 12–13):

- **deterrente**: la presenza **visibile** di sistemi biometrici e videocamere scoraggia i potenziali trasgressori;
- **controllo di accesso rafforzato**: solo le persone autorizzate entrano nelle aree sensibili;
- **allerta precoce e anomaly detection**: la videosorveglianza, anche biometrica, insieme alla rilevazione di comportamenti anomali può **anticipare** il problema. Esempi: **troppe persone** in un ambiente, **presenze fuori turno**;
- **integrazione** con gli altri sistemi di sicurezza fisica, per un sistema **complessivo e stratificato**.

**Dopo i problemi (ex post)** (p. 14):

- **identificazione dei responsabili**: registrazioni video, log degli accessi biometrici, **impronte latenti**;
- **ricostruzione delle azioni**: combinare video, log e testimonianze per ricostruire la sequenza degli eventi;
- **analisi degli incidenti**: capire cause e dinamiche.

---

### **5. I quattro livelli della sicurezza fisica**

_(slide p. 15)_

_(brochure p. 4)_

| Livello | Obiettivo (slide) | Esempi |
|---|---|---|
| **1. Dissuasione** (*Deter*) | sarebbe la soluzione ideale, **se bastasse**; può essere **fisica** (porte, barriere) o **psicologica** (videosorveglianza) | recinzioni alte, filo spinato, cartelli, videocamere, controllo accessi |
| **2. Rilevamento** (*Detect*) | i sistemi devono essere **immediatamente allertati** che un attacco è iniziato | dagli antifurto che rilevano il punto di ingresso alle telecamere con **analisi AI** |
| **3. Ritardo** (*Delay*) | ritardare l'intruso dà al personale il tempo di **reagire** ed eventualmente **evacuare** | finestre e porte resistenti, barriere antisfondamento, accessi blindati, vetri rinforzati, **più perimetri** |
| **4. Risposta** (*Respond*) | azioni manuali o automatiche di reazione | segnalazione alle forze dell'ordine, raccolta delle evidenze, invio automatico di filmati e log |

> 📌 **Security Onion.** La difesa si imposta con la **«cipolla della sicurezza»**: l'insieme di strutture e metodi a strati, progettato durante un **audit**, che protegge l'asset. I livelli vanno **dall'esterno verso l'interno**: la dissuasione è lo strato più esterno e la risposta serve solo quando tutti gli altri sono stati superati. Esiste anche una Security Onion puramente informatica, trattata nei corsi di cybersecurity.

La brochure aggiunge due osservazioni utili:

- molti sistemi hanno **più ruoli**: il controllo accessi **dissuade**, **rileva** e **ritarda** allo stesso tempo;
- la **risposta** è cruciale ma **spesso trascurata**: servono sistemi di comunicazione, guardie, primi soccorritori designati e processi per il **lockdown** del sito e l'allerta delle forze dell'ordine.

<!-- SOURCE_VISUAL id="TAB14-V02" source="Lezione_14_brocure_commerciale_Pelco_Physical_Security_Guide.pdf" page="4" type="diagramma a cerchi concentrici" description="Livelli della sicurezza fisica: Deter, Detect, Delay, Respond dall'esterno verso l'interno" reason="Diagramma a strati della brochure" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Tecnologie per la sicurezza fisica secondo la brochure**

_(brochure pp. 5–8)_

> ⚠️ **Materiale commerciale.** Questa sezione riassume l'ebook di un produttore di videosorveglianza. È utile come panoramica del mercato, ma non è contenuto delle slide.

**Tecnologie di controllo** (p. 5):

- i dispositivi usano sempre più **cloud** e **intelligenza artificiale** per l'elaborazione in tempo reale;
- la tecnologia è **scalabile**: si può partire con poche telecamere, serrature o sensori;
- i dati raccolti servono per **audit trail** e analisi;
- conta l'**integrazione** tra dispositivi: lo standard **ONVIF** permette a dispositivi di produttori diversi di interoperare; vanno considerate anche certificazioni come **FIPS**.

**Videosorveglianza** (p. 6), primariamente strumento di **rilevamento** ma anche di **dissuasione**:

| Tipo di camera | Uso |
|---|---|
| **analogiche HD** | economiche, con buona qualità |
| **IP** | video di alta qualità via rete, rilevazione del movimento, **anti-tampering** |
| **IP fisse** (*bullet* o *dome*) | un'area alla volta, interni ed esterni, anche antivandalo e negli ascensori |
| **varifocali** | piccoli spazi specifici |
| **PTZ** (*pan-tilt-zoom*) | visione a 360° controllabile, lunghe distanze, bassa latenza |
| **panoramiche** | registrazione continua da tutti gli angoli |
| **rinforzate** (*ruggedized*) | ambienti estremi: impianti oil & gas, porti, esplosioni e temperature estreme |

**Controllo accessi** (p. 7): aiuta a **rilevare** e **ritardare**, e **dissuade** rendendo difficile l'ingresso. Il **controllo senza chiavi**, per esempio con lo **smartphone**, elimina il rischio di chiavi e badge persi o duplicati; molte unità includono **video bidirezionale** per verificare chi chiede di entrare.

**Analitica e AI** (p. 8): elabora grandi quantità di dati, riconosce **persone e veicoli**, **filtra i falsi allarmi**, produce riepiloghi e report utili per **conformità** e **audit**.

---

### **7. Pianificare la sicurezza fisica**

_(brochure pp. 9–12)_

**Sfide tipiche** (p. 9):

- **carenza di budget**: le guardie costano, l'hardware avanzato anche, ma non averlo espone a rischi altrettanto costosi;
- **carenza di personale**, anche formato sulle tecnologie e sulle policy;
- **tecnologia non integrata**: se i dispositivi non comunicano, informazioni critiche vanno perse (di nuovo ONVIF);
- **siti multipli**, ognuno con minacce proprie.

**Il piano in cinque passi** (pp. 10–11):

| Passo | Contenuto |
|---|---|
| **1. Valutazione del rischio** | minacce per ogni livello Deter-Detect-Delay-Respond; punti di ingresso non protetti e aree di valore; storico degli incidenti e delle assicurazioni; coinvolgimento di tutti i reparti; ispezione del sito (a volte basta **migliorare l'illuminazione**); zone ad alto e basso traffico; **reti di ridondanza** |
| **2. Revisione di operazioni e risorse** | banda di rete e spazio di archiviazione per i nuovi dispositivi; monitoraggio interno o esterno; spazio per un **SOC** (*security operations center*); vincoli di riservatezza |
| **3. Approvazione commerciale e operativa** | piano finanziariamente sostenibile senza lasciare rischi scoperti; una nuova **matrice dei rischi** per ogni iterazione; documentare tutto; compromessi (per esempio illuminazione invece di telecamere termiche, analytics invece di nuovi operatori) |
| **4. Policy e messa in opera** | definire la **risposta**: referenti, verifica delle minacce, quando chiamare le forze dell'ordine, monitoraggio fuori orario, attivazione e disattivazione degli allarmi; **KPI** concordati per iscritto |
| **5. Best practice** | **responsabilità** come principio cardine; un **playbook** con componenti, configurazioni, obiettivi, protocolli di ridondanza, test e manutenzione periodici, standard e normative con le date di rinnovo |

La p. 12 contiene i contatti commerciali del produttore.

---

### **8. Tailgating e piggybacking**

_(slide pp. 16–20)_

#### **8.1 Definizioni**

| | **Tailgating** | **Piggybacking** |
|---|---|---|
| Definizione | una persona **non autorizzata** segue da vicino una persona autorizzata per entrare **senza essere rilevata** | una persona non autorizzata entra **con il consenso o l'aiuto** di una persona autorizzata |
| Consapevolezza di chi è autorizzato | **nessuna**: avviene in modo furtivo | **consapevole** e consenziente |
| Esempio (p. 16) | qualcuno aspetta che un dipendente apra una porta con il badge e **gli entra dietro** | un dipendente apre la porta e **fa entrare** un visitatore senza badge |

> 💡 **Perché la distinzione conta.** Contro il tailgating basta **rilevare** la seconda persona; contro il piggybacking serve anche la **responsabilizzazione** di chi è autorizzato, perché la persona autorizzata collabora. In entrambi i casi l'autenticazione della prima persona è corretta: il problema è **quante persone passano**.

#### **8.2 Contromisure**

Per limitare tailgating e piggybacking (pp. 17–18):

- **telecamere** di sicurezza o basate su AI per la **rilevazione di persone multiple**;
- **barriere** e **verso di apertura** delle porte;
- **sensori sul pavimento** o **fotocellule** esterne;
- **sensori sul pavimento interni** e **visione stereo**.

L'Automated Border Control è l'esempio in cui queste contromisure vengono combinate.

**Esempio commerciale: Gunnebo SpeedStile FLs** (p. 19). Aspetti interessanti di questo tipo di soluzioni:

- equilibrio tra **sicurezza e libertà di movimento**;
- **design di barriera fluido**;
- **ergonomia** e facilità d'uso;
- costruzione **durevole** e affidabile;
- **efficienza dei costi**;
- **tecnologie antispoofing** adottate.

**Tipi di varchi** che possono ospitare la biometria (p. 20): la slide riporta una valutazione comparativa (fonte: sicurezzamagazine.it) e sottolinea il **forte impatto sui costi di mantenimento**.

<!-- SOURCE_VISUAL id="TAB14-V03" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="17" type="illustrazioni" description="Tailgating e piggybacking con tre contromisure indicate" reason="Pagina con sole immagini ed etichette" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V04" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="18" type="schema" description="Contromisure: telecamere AI per persone multiple, barriere e verso di apertura, sensori a pavimento, fotocellule, visione stereo" reason="Posizione delle contromisure nel varco" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V05" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="20" type="tabella grafica" description="Tipi di varchi che possono ospitare biometria con valutazione comparativa e impatto sui costi di mantenimento" reason="Valutazione presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Automated Border Control**

### **9. Contesto: perché automatizzare le frontiere**

_(slide pp. 21–26)_

_(dispensa pp. 1–3)_

L'ABC è **uno dei casi più complessi** di accesso fisico ad elevata sicurezza (p. 22). Le soluzioni studiate valgono anche per **dipartimenti di ricerca e sviluppo**, **dotazioni pericolose**, **magazzini valori** e **macchinari critici** (p. 21). La lezione si concentra sulla biometria; la videosorveglianza ha una lezione dedicata (Lezione 18).

**Introduzione** (pp. 24–26):

- **3,3 miliardi** di viaggiatori all'anno solo nel trasporto aereo, in crescita; vanno considerate anche le frontiere **marittime e terrestri**;
- i punti di attraversamento faticheranno a gestire l'aumento del **throughput**;
- **obiettivi**: **facilitare il viaggio** mantenendo la **sicurezza**, quindi serve **automazione**;
- i controlli di frontiera verificano che una persona possa **entrare o uscire** da un paese. Tradizionalmente una **guardia**, **in pochi secondi**, controlla: **validità del documento**, **identità** del viaggiatore, **idoneità** ad attraversare, **minacce** potenziali;
- la biometria identifica i viaggiatori:
  - i passaporti elettronici di **prima generazione** contengono l'**immagine del volto**;
  - quelli di **seconda generazione**, **obbligatori nell'UE da giugno 2009**, contengono anche **due impronte digitali**;
  - circolano circa **1 miliardo** di e-Passport.

**Dati aggiuntivi della dispensa** (pp. 1–3):

- il traffico aereo è previsto in crescita del **5% annuo** per 20 anni (Boeing, 2015), e per molti stati aumentare le guardie non è sostenibile;
- una guardia ha in media **12 secondi** per decidere se un viaggiatore può passare;
- il **91%** dei viaggiatori è interessato ai varchi automatici (IATA, 2012), ma molti non ne conoscono l'esistenza;
- automatizzare i viaggiatori **a basso rischio** libera risorse per quelli **ad alto rischio**, con un'efficienza costante e senza i fattori umani come la **fatica**.

> 📌 **Definizione di ABC** (dispensa p. 2, da Frontex). L'uso di sistemi **automatici o semi-automatici** che verificano **identità e autorizzazione** dei viaggiatori ad attraversare la frontiera, **senza intervento umano**. Il processo comprende **tre controlli**:
> 1. **autenticazione del documento** di viaggio;
> 2. **verifica biometrica** dell'identità, con confronto **1:1** tra il campione acquisito dal vivo e quello nel documento;
> 3. verifica dell'**autorizzazione** ad attraversare.
>
> Il sistema è **supervisionato** da un operatore in una postazione remota, che interviene in caso di problemi.

**Tre generazioni di ABC** (dispensa p. 3):

| Generazione | Chi serve | Esempi |
|---|---|---|
| **1ª** (primi anni 2000) | solo **viaggiatori registrati** | Privium (Paesi Bassi), Global Entry (USA), Nexus (Canada-USA) |
| **2ª**: gli **e-Gate** | viaggiatori con **e-MRTD** del paese che ha installato il sistema, ed eventualmente stranieri con accordi bilaterali | stati dell'UE, Australia |
| **3ª** (prevista) | comunicazione **globale** | visione IATA |

**Tre tipi di implementazione** (dispensa p. 3): l'**e-Gate** verifica il viaggiatore e ne consente il passaggio; il **kiosk ABC** svolge solo una parte (pre-enrollment biometrico, controllo del documento, ricevuta); i **kiosk semi-automatici** producono una ricevuta verificata poi da un e-Gate o da un agente (come l'APC statunitense). All'epoca operavano **oltre 180** installazioni ABC aeroportuali.

<!-- SOURCE_VISUAL id="TAB14-V06" source="Lezione_14_DispensaABC.pdf" page="3" type="schema" description="Fig. 1: fasi del processo ABC" reason="Schema del processo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. ABC, eGate e diffusione**

_(slide pp. 27–31)_

> 📌 **eGate** (p. 27). Porte automatiche usate nei controlli di frontiera che permettono ai viaggiatori di passare **senza interazione diretta** con gli agenti. Insieme al sistema ABC verificano rapidamente l'identità tramite **documento elettronico** e **verifica biometrica**: il viaggiatore presenta passaporto o carta d'identità elettronica, il sistema verifica i dati biometrici e, se tutto è in ordine, **la porta si apre**.

> 📌 **ABC** (p. 28). Sistema automatizzato ai punti di ingresso di un paese per **semplificare e velocizzare** il controllo, basato su scansione biometrica (impronta, volto). **Le eGate sono uno dei componenti di un ABC**. È progettato per ridurre gli **ingressi illegali** e aumentare l'**efficienza** del personale.

**Benefici degli ABC biometrici** (p. 29):

| Beneficio | Come |
|---|---|
| **meno tempo di elaborazione** | attraversamento più fluido, meno frustrazione |
| **più throughput** | le guardie si concentrano sui **controlli di secondo livello** |
| **più sicurezza** | verifica dell'identità **più coerente** |
| **meno costi** | più passeggeri controllati al primo livello, **meno guardie** necessarie |

**Diffusione** (p. 30): **oltre 100 stati** impiegano tecnologie ABC. La slide cita: **IRIS** (Regno Unito, iride), **SmartGate** (Australia), **PARAFE** (Francia), **ABC System** (Spagna). **In Italia** sono presenti in **tutti i maggiori scali**.

> ⚠️ **Numeri che cambiano nel tempo.** La dispensa (2016) parla di ABC in **48 paesi** e di oltre **600 milioni** di e-Passport emessi da circa **120 paesi**; le slide, più recenti, di **oltre 100 stati** e **1 miliardo** di e-Passport. Sono fotografie di momenti diversi.

**Le applicazioni in aeroporto** (p. 31):

- stazioni **self-service di consegna bagagli** (*baggage drop*);
- **eGate** per il controllo automatico delle frontiere;
- **varchi di auto-imbarco** (*self-boarding gates*).

---

### **11. Architettura di un sistema ABC**

#### **11.1 Infrastruttura e processo**

_(slide pp. 32–33)_

_(dispensa pp. 3–6)_

Le slide pp. 32–33 mostrano graficamente l'**infrastruttura** e il **processo** ABC.

**Il processo di un e-Gate** (dispensa p. 3, da Frontex):

1. il viaggiatore presenta il documento; il lettore estrae i dati anagrafici dalla **MRZ** (*Machine Readable Zone*);
2. il sistema verifica l'**e-MRTD** e l'**idoneità** del viaggiatore;
3. legge i **dati biometrici dal chip** e li confronta con i campioni **acquisiti dal vivo**;
4. se il confronto riesce, **apre le porte**.

La dispensa stima che gli e-Gate multibiometrici installati nel mondo abbiano gestito **oltre cento milioni di persone all'anno**.

**Architettura logica** (dispensa p. 4, Fig. 3): quattro sottosistemi interconnessi.

| Sottosistema | Compito |
|---|---|
| **DAS**, *Document Authentication System* | verifica la **validità del documento** ed estrae i dati da MRZ e chip |
| **BVS**, *Biometric Verification System* | **ruolo centrale**: verifica l'identità con i tratti biometrici |
| **CSI**, *Central Systems Interface* | gestisce le interfacce con i **sistemi esterni** |
| **BGMS**, *Border Guard Maintenance System* | supporta il **monitoraggio e controllo** da parte delle guardie |

**Componenti del BVS** (dispensa p. 6):

- **sensori di acquisizione** con requisiti di qualità stringenti, che si adattano al viaggiatore;
- **sistema di illuminazione** uniforme e simmetrico, che compensa le luci esterne;
- **modulo di valutazione della qualità**, che scarta i campioni inadatti;
- **modulo di verifica**: estrazione del template e matching.

Per la comunicazione tra software e sensori di produttori diversi si usa lo standard **ISO/IEC 19784-1 BioAPI**, con i componenti *Biometric Service Provider*.

<!-- SOURCE_VISUAL id="TAB14-V07" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="32" type="schema" description="Infrastruttura di un sistema ABC" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V08" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="33" type="diagramma di flusso" description="Processo ABC dalla lettura del documento all'apertura della porta" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V09" source="Lezione_14_DispensaABC.pdf" page="4" type="schema e fotografia" description="Fig. 2: struttura di un e-Gate (1 porta di ingresso, 2 lettore passaporto, 3 display, 4 lettore impronte, 5 camera volto e display, 6 porta di uscita) e installazione a Roma Fiumicino" reason="Componenti numerati visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V10" source="Lezione_14_DispensaABC.pdf" page="5" type="schema" description="Fig. 3: architettura logica di un e-Gate (DAS, BVS, CSI, BGMS) e collegamenti ai sistemi esterni" reason="Schema dei sottosistemi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2 Il passaporto elettronico**

_(slide p. 34)_

_(dispensa pp. 7–8)_

**Secondo le slide** (p. 34):

- è specificato dall'**ICAO** nel **Doc 9303**;
- usa la tecnologia **RFID**;
- implementa **protocolli di sicurezza** per controllare l'accesso ai dati;
- contiene:
  - il **simbolo** dell'e-Passport;
  - la **MRZ**;
  - i **dati personali** (nomi, data di nascita…);
  - i **tratti biometrici**: **volto** (obbligatorio), **impronta** (opzionale), **iride** (opzionale).

**Dettagli dalla dispensa** (pp. 7–8):

- l'e-MRTD combina **carta ed elettronica**. Il circuito integrato **contactless** (microprocessore più antenna RFID) è inserito nella pagina centrale o in una copertina, e i dati sono protetti con **PKI**;
- per scegliere i tratti l'ICAO ha considerato **interoperabilità globale, uniformità, affidabilità tecnica, praticità e durabilità**;
- **USA, Regno Unito, Australia e Nuova Zelanda** includono **solo il volto**; l'**area Schengen** volto e **due impronte**; la **Malesia** volto e **due pollici**;
- aggiungere un secondo tratto **migliora** le prestazioni degli e-Gate (caso spagnolo). Le impronte però sono dati **sensibili**, protetti da un **controllo di accesso aggiuntivo** che richiede lo **scambio di certificati** tra paesi.

**Documenti accettati dagli e-Gate** (dispensa p. 7): **MRTD** (passaporti, visti e carte d'identità leggibili a macchina) oppure **tessere di programmi per viaggiatori registrati**. Alcuni aeroporti (Spagna, Portogallo) accettano anche le **carte d'identità elettroniche** nazionali.

**Formati di scambio** (dispensa p. 7):

- **CBEFF** (ISO/IEC 19785): elementi dati con metadati;
- **ANSI/NIST-ITL 1-2011**;
- **ISO/IEC 19794**: formati di interscambio con limiti di compressione.

Scambiare **sample** rende il sistema più **flessibile**; scambiare **template** richiede meno banda ed è più **rispettoso della privacy** (Lezione 13).

<!-- SOURCE_VISUAL id="TAB14-V11" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="34" type="fotografia annotata" description="Passaporto elettronico ICAO Doc 9303 con simbolo, MRZ, dati personali e chip RFID" reason="Elementi del documento indicati sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 I componenti del gate**

_(slide pp. 35–40)_

| Componente | Slide |
|---|---|
| **porte di ingresso e uscita** | p. 35 |
| **lettore di documenti** | p. 36 |
| **dispositivi di acquisizione e verifica biometrica**: volto, impronta, iride… | p. 37 |
| **sensori di sicurezza** che monitorano il processo di passaggio | p. 38 |
| **display di istruzioni** (*signalling*) | p. 39 |

> 📌 **Signalling** (p. 40). La comunicazione **visiva o sonora** che indica agli utenti le istruzioni da seguire o lo stato del controllo. Obiettivo: **semplificare** il processo, **ridurre gli errori** e garantire un controllo **efficiente e sicuro**.

| Forma di signalling | Esempio |
|---|---|
| **segnali visivi** | luci lampeggianti, LED, schermi, pannelli; un **semaforo** che indica quando procedere o attendere |
| **segnali sonori** | un segnale acustico quando l'accesso è consentito |
| **messaggi testuali o vocali** | istruzioni su schermo o annunci dagli altoparlanti |
| **segnali di stato e feedback** | «in corso di verifica», «autorizzato», per **rassicurare** l'utente |

> 💡 **Perché è un tema biometrico.** Un utente che non capisce dove guardare o dove appoggiare il dito produce **campioni di bassa qualità**: aumentano FTA e FNMR. Un buon signalling migliora direttamente le **prestazioni** del sistema, non solo il comfort.

<!-- SOURCE_VISUAL id="TAB14-V12" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="35" type="fotografia annotata" description="Architettura del gate ABC: porte di ingresso e uscita evidenziate" reason="Componente indicato solo sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V13" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="36" type="fotografia annotata" description="Architettura del gate ABC: lettore di documenti evidenziato" reason="Componente indicato solo sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. One-step e two-step**

_(slide pp. 41–46)_

_(dispensa pp. 5–6)_

#### **12.1 La struttura di un ABC**

La parte fisica di un ABC si organizza in due famiglie (p. 41):

| Processo | Realizzazioni |
|---|---|
| **One step** | **man-trap** fisica; **virtual man-trap** |
| **Two steps** | **integrated**; **segregated** (sensore biometrico separato) |

La slide nota che una delle soluzioni permette di **allungare significativamente** il tempo tra la lettura del passaporto e l'accettazione, consentendo **controlli più approfonditi**: è il caso in cui i due passi sono separati.

#### **12.2 One-step**

**Aggrega tutte le verifiche in un'unica transazione** (p. 42):

- ✅ tempo di sdoganamento **rapido** per i viaggiatori **esperti**;
- ❌ può **confondere** gli utenti **inesperti**.

**Due realizzazioni** (p. 43):

| | **Man-trap** | **Virtual man-trap** |
|---|---|---|
| Che cos'è | struttura fisica o stanza che consente l'ingresso di **una sola persona alla volta** | **non** una struttura fisica, ma un sistema di **controllo e monitoraggio** |
| Come funziona | la persona entra; **solo quando la porta di ingresso si chiude**, si apre quella di uscita | sensori, telecamere e software rilevano e impediscono il passaggio **simultaneo** di più persone |
| Reazione | impossibile passare in due | se due persone passano in rapida successione, genera un'**allerta** o **blocca** la seconda finché la prima non ha finito |

**Sensori per il conteggio delle persone** (p. 44), per esempio **Irisys Vector 4D** e **IEE sensing 3D MLI**. Possono funzionare come:

- **contatore di persone** autonomo in rete (con indirizzo IP);
- **rilevatore di tailgating** all'ingresso;
- **rilevatore di persona singola** (virtual man-trap);
- dispositivo di **sorveglianza degli oggetti**, anche per proteggere **opere d'arte tridimensionali**.

Si usano anche in contesti non di alta sicurezza, come i negozi, per controllare le **code** e la **capienza massima**.

> 💡 **Dispensa: che cosa guadagna il one-step** (p. 5). Il one-step si realizza con una **doppia porta** e tutti i componenti **dentro** l'e-Gate. È veloce perché più azioni si svolgono **in parallelo**, purché il viaggiatore sia **esperto**. Esempio: **PARAFE** in Francia.

<!-- SOURCE_VISUAL id="TAB14-V14" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="41" type="diagramma ad albero" description="Struttura di un ABC: one step (man trap, virtual trap) e two steps (segregated, integrated)" reason="Classificazione disegnata come albero" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V15" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="43" type="fotografie" description="Man-trap fisica e virtual man-trap con sensore per il controllo della persona singola" reason="Confronto visivo delle due realizzazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V16" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="44" type="fotografie prodotto" description="Sensori per il conteggio delle persone Irisys Vector 4D e IEE sensing 3D MLI" reason="Esempi di dispositivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3 Two-step**

**Due passi** (p. 45):

1. **passo 1**: si **estraggono le informazioni** sul viaggiatore;
2. **passo 2**: **verifica dell'identità** e controllo dell'**idoneità**.

I due passi possono essere **contigui** o **separati** (p. 46):

| | **Integrated** | **Segregated** |
|---|---|---|
| Posizione | lettore e scanner **nello stesso luogo**; il passo 1 avviene **vicino** allo scanner biometrico | lo scanner è **confinato** altrove; il passo 1 avviene **lontano** dallo scanner |
| Realizzazione (dispensa p. 5) | doppia porta, verifica del documento **fuori** dall'e-Gate e matching biometrico **dentro** | **kiosk** di pre-enrollment più e-Gate a **porta singola** |
| Esempio (dispensa) | **RAPID** in Portogallo | **SmartGate** di Australia e Nuova Zelanda, inizialmente |

**Secondo la dispensa** (p. 5):

- in ogni configurazione il primo passo produce un **token temporaneo** verificato nel secondo;
- il two-step dà **maggiore controllo** sul processo, riducendo **leggermente** il throughput;
- migliora **flessibilità** e **usabilità** e riduce gli **errori** dell'utente, che capisce facilmente che cosa fare a ogni passo;
- nel **luglio 2015** la Nuova Zelanda è passata a **SmartGate Plus**, soluzione **one-step**, per accelerare il passaggio.

<!-- SOURCE_VISUAL id="TAB14-V17" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="45" type="fotografie" description="Processo two-step integrated con passo 1 vicino allo scanner e segregated con passo 1 lontano" reason="Confronto visivo delle due configurazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V18" source="Lezione_14_DispensaABC.pdf" page="6" type="schema" description="Fig. 4: topologie di e-Gate, mantrap one-step, two-step integrato, two-step segregato con kiosk ed e-Gate a porta singola" reason="Schema delle tre topologie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. Sistemi collegati all'ABC**

_(slide pp. 47–51)_

_(dispensa pp. 6–7)_

L'ABC dialoga con altri sistemi (p. 47): **Visa Information System** (VIS), **Registered Traveler Program** (RTP), **Entry-Exit System** (EES) e **dispositivi mobili**.

| Sistema | Funzione |
|---|---|
| **VIS** (nella dispensa *Visa Management System*, VMS) | memorizza i dati delle **domande di visto**, compresi i **campioni biometrici** acquisiti, i dati personali, i viaggi e le domande precedenti |
| **EES** (nella dispensa EEMS) | registra **ingressi e uscite** dei cittadini di paesi terzi, sostituendo il **timbro** sul passaporto con registrazioni elettroniche biometriche; aiuta a individuare chi supera la durata consentita (*overstayer*) e fornisce statistiche sui flussi |
| **RTP** | iscrizione **volontaria** per velocizzare i viaggiatori frequenti |

**L'EES europeo** (pp. 48–49):

- **digitalizza** i controlli alle frontiere esterne **Schengen**, sostituendo il timbro manuale;
- ha avviato una **fase cruciale tra fine 2025 e inizio 2026**;
- **non** sono soggetti i cittadini **italiani** ed **europei** e i **titolari di permessi di soggiorno** europei;
- serve a controllare i **periodi di permanenza consentiti**, **aggiungendo la biometria**: per **turismo** o affari brevi, e per **lavoro** (tipicamente **1 anno**).

> ⚠️ **Refuso sulla durata.** La slide p. 49 scrive «90-80 giorni». La regola Schengen per i soggiorni brevi è di **90 giorni in qualsiasi periodo di 180 giorni**.

**Registered Traveler Program** (p. 50):

- sistema di **iscrizione volontaria** per velocizzare certi gruppi di **viaggiatori frequenti**: viaggiatori d'affari, familiari di residenti;
- i viaggiatori sono **pre-verificati** (*pre-vetted*, *pre-screened*) per garantire che non rappresentino un rischio;
- vengono raccolti **marcatori biometrici**;
- l'RTP deve garantire un **passaggio veloce**, e l'ABC ha un **ruolo chiave**.

**Dispositivi mobili** (p. 51). Dispositivi **palmari** che aiutano le guardie a:

- **autenticare** i viaggiatori con la biometria;
- **controllare** a fondo i documenti;
- **interrogare** i database centrali.

| Componenti | Impatto sugli ABC |
|---|---|
| tablet o smartphone come dispositivo di controllo | necessità di **scaricare informazioni** per l'elaborazione **offline** |
| scanner biometrico | esecuzione di **ricerche** nei database |
| lettore di documenti elettronici | |
| interfacce **wireless** | |

> 💡 **Verifica o identificazione?** Di solito un e-Gate fa una **verifica 1:1** tra il viaggiatore e il documento. Secondo la dispensa (p. 8) serve invece un'**identificazione 1:N** quando: il viaggiatore è **senza documenti**, va controllata una **watch list**, l'ABC è basato su **RTP** o include un **EES**, oppure l'e-Gate usa **due passi segregati**.

<!-- SOURCE_VISUAL id="TAB14-V19" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="49" type="infografica" description="A cosa serve l'EES: controllo dei periodi di permanenza con biometria" reason="Infografica senza testo completo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. I tratti biometrici negli ABC**

#### **14.1 La procedura di verifica e la qualità**

_(slide pp. 52–53)_

_(dispensa pp. 8–10)_

**Tratti più usati** (p. 53):

- i controlli d'identità negli e-Gate si basano sulla **verifica** di uno o più marcatori biometrici: il dato **dal vivo** è confrontato con il riferimento nel **passaporto** o in un **database**;
- i tratti previsti dall'**ICAO** sono **volto**, **impronta** e **iride**;
- **agli inizi** impronta e iride erano considerate **più sicure**, ma **negli ultimi anni il divario tecnologico si è chiuso**.

**I passi della verifica e i tempi accettabili** (dispensa pp. 8–9, da Frontex):

| Passo | Tempo accettabile |
|---|---|
| lettura del documento: **controllo ottico** | **2 s** |
| lettura del documento: **lettura del chip** | **8 s** |
| **acquisizione** del campione, con istruzioni e adattamento all'altezza | **< 1 s per frame** |
| **controllo di qualità** ed eventuali nuovi tentativi | — |
| **matching**, con soglia fissata dall'autorità secondo il livello di sicurezza | **pochi secondi** |
| **passaggio completo** nell'e-Gate, dalla scansione del documento all'uscita | **≤ 30 s in media** |

> 💡 **Tradotto in throughput (nota didattica).** Con 30 secondi per viaggiatore un singolo e-Gate serve al massimo
> $$\frac{3600\ \text{s/h}}{30\ \text{s}} = 120\ \text{viaggiatori all'ora}$$
> Ogni secondo risparmiato conta: a 20 secondi si sale a 180 viaggiatori all'ora.

**La qualità del campione** (dispensa pp. 9–10). Secondo ISO/IEC 29794 la qualità ha **tre componenti**:

| Componente | Significato |
|---|---|
| **carattere** | qualità fisica **intrinseca** del tratto del soggetto |
| **fedeltà** | somiglianza tra il campione acquisito e la sorgente originale |
| **utilità** | impatto del campione sulle **prestazioni** del matching; dipende da carattere e fedeltà |

Il **quality score** dovrebbe **predire le prestazioni di matching** del campione.

**Fattori di degrado più rilevanti negli ABC**:

- viaggiatori **inesperti**;
- **stress** del passaggio di frontiera;
- **bagagli** che rendono scomoda l'acquisizione;
- **mancanza di feedback**;
- **mancanza di supervisione** da parte di un operatore.

<!-- SOURCE_VISUAL id="TAB14-V20" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="52" type="pagina di sezione" description="Tratti biometrici negli ABC" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.2 Il volto**

_(slide pp. 54–58)_

_(dispensa pp. 10–14)_

**Il tratto preferito dall'ICAO** (p. 54):

- le persone lo **mostrano abitualmente** in pubblico;
- è **accettato globalmente**;
- è incluso **di default** nei passaporti ICAO;
- è **non intrusivo** e facile da usare;
- **non** richiede nuove apparecchiature costose;
- è disponibile per il confronto con le **watch list**;
- è un processo **semplice e familiare** per le autorità.

Secondo le slide lo usa il **60%** dei sistemi installati: Portogallo, Regno Unito, Giappone, Finlandia, Hong Kong, Colombia, Australia.

**Setup** (p. 55):

| Elemento | Requisiti |
|---|---|
| **camere di acquisizione** | più camere per **altezze diverse**, oppure regolazione **automatica** dell'altezza |
| **illuminazione** | **uniforme e simmetrica**, compensa le luci esterne, **evita l'abbagliamento** |
| **modulo di verifica della qualità** | garantisce immagini di alta qualità |
| **modulo di verifica facciale** | esegue il matching |

La dispensa aggiunge soluzioni come la camera **pan-tilt**, lo **specchio mobile** a 1 o 2 assi e la camera **grandangolare** singola; RAPID regola l'altezza sugli occhi del viaggiatore, SmartGate e il sistema spagnolo usano più camere.

**Raccomandazioni di qualità** (pp. 56–57). Le slide mostrano esempi di foto **non conformi**:

| Problemi di inquadratura e scena (p. 56) | Problemi del soggetto e della foto (p. 57) |
|---|---|
| dimensioni errate, foto **danneggiata o macchiata** | **occhi rossi** |
| **troppo vicino**, **troppo lontano** | **cappello**, **volto coperto**, **occhi coperti** dai capelli |
| **altri soggetti** o oggetti nella foto | **occhiali scuri**, **riflessi** sugli occhiali |
| **non centrato** | montatura **spessa** o che **copre gli occhi** |
| **posa artistica**, **volto inclinato** | **colori innaturali** |
| foto **sbiadita**, **puntinata** | **troppo scura**, **troppo chiara** |
| **ombre** sullo sfondo o sul volto | **fuori fuoco** |

**Le sfide del volto negli e-Gate** (p. 58):

- garantire la **qualità** del campione e immagini **conformi ICAO**;
- gestire **altezza e posa** del viaggiatore;
- gestire le **variazioni di illuminazione** ambientale;
- migliorare l'**usabilità**;
- rilevare **liveness e spoofing** con alta accuratezza.

<!-- SOURCE_VISUAL id="TAB14-V21" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="56" type="griglia di fotografie" description="Raccomandazioni di qualità (1): esempi di foto non conformi per dimensioni, distanza, centratura, posa, sbiadimento e ombre" reason="Gli esempi sono solo visivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V22" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="57" type="griglia di fotografie" description="Raccomandazioni di qualità (2): occhi rossi, cappello, occhiali, colori innaturali, esposizione e fuoco" reason="Gli esempi sono solo visivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Approfondimento dalla dispensa** (pp. 10–14):

- **Diffusione**: il **45%** dei sistemi censiti usa il volto, da solo o combinato. Esempi: RAPID (Portogallo), SmartGate (Australia), il sistema spagnolo, APC (Canada e USA).
- **Degradi specifici**:
  - posa, espressione, acconciatura, trucco, piccole occlusioni, **motion blur**;
  - illuminazione, che può cambiare **drasticamente** l'aspetto;
  - per le foto **stampate** nel documento: **halftoning**, **dithering**, **filigrane** di sicurezza. Il **5%** dei passaporti europei ha foto di qualità **insufficiente**.
- **Standard**:
  - **ISO/IEC 29794-5**: punteggi di qualità del volto;
  - **ISO/IEC 19794-5**: formati, vincoli di scena e proprietà fotografiche, con i formati *full frontal* e *token face*, obbligatorio per gli e-Passport.
- **Algoritmi commerciali**: Cognitec (SmartGate), Vision-Box (APC statunitense), NEC (Giappone).
- **Pipeline**:
  1. **rilevamento** del volto;
  2. **normalizzazione** geometrica (allineamento con landmark come i centri degli occhi) e fotometrica;
  3. **estrazione delle feature**;
  4. **matching**.

| Approccio | Metodi | Pro | Contro |
|---|---|---|---|
| **Globale** (olistico) | eigenface (PCA), Fisherface (LDA), Laplacianface, dominio della frequenza, rappresentazione sparsa, reti profonde (DeepID, DeepFace) | usa **tutta** l'informazione; preferibile con immagini di **alta qualità** | tutti i pixel ugualmente importanti; costoso; sensibile a posa, scala, illuminazione |
| **Locale** (feature-based) | wavelet di Gabor, local feature analysis, elastic bunch graph matching, active appearance models, LBP, SIFT, HOG | robusto alla **posa**, rappresentazione compatta, matching veloce; migliore con immagini **imperfette** | rilevamento automatico delle feature difficile; scelta delle feature a carico dell'implementatore |

- **Matching**: LDA, SVM, distanza euclidea, reti neurali; le reti profonde stanno migliorando lo stato dell'arte, ma richiedono più risorse di calcolo.

> ⚠️ **Refuso nella dispensa.** SIFT è citato come «scalar image feature transform»; il nome corretto è **Scale-Invariant Feature Transform**.

**Prestazioni del volto** (dispensa pp. 13–14):

| Contesto | FAR | FRR |
|---|---:|---:|
| migliori algoritmi in valutazione indipendente, ambiente controllato (NIST 2011) | 0,1% | 0,3% |
| **requisito Frontex** per gli e-Gate | < 0,1% | < 5% |
| valore talvolta impostato negli e-Gate reali | fino a 1% | — |
| miglior algoritmo su immagini ABC reali (Schiphol) | 0,1% | circa 2% |
| EasyPASS (Germania) | 0,1% | 5% |
| RAPID, algoritmo del fornitore | 0,03% | 4,25% |
| RAPID, database di **sosia** (genitori e figli, gemelli) | — | 5,2% |

Nello studio RAPID il **17%** dei falsi rifiuti era dovuto agli **occhiali**; altri fattori erano cappelli e capelli sul volto.

#### **14.3 L'impronta digitale**

_(slide p. 59)_

_(dispensa pp. 14–16)_

**Nelle slide** (p. 59): l'ICAO la descrive come tratto **opzionale** per gli e-Passport. Non tutti i documenti la includono, ma è prevista in **VIS, EES e RTP**. La usa il **61%** dei sistemi installati: Corea, Regno Unito, Giappone, Spagna, Ruanda, Venezuela, Francia.

**Approfondimento dalla dispensa**:

- **Diffusione**: il **56%** dei sistemi censiti; esempi Global Entry (USA), PARAFE (Francia), e-Channel (Hong Kong), sistema spagnolo.
- **Sensori**: **ottici**, i più accurati, che richiedono di premere il dito; dispositivi a **un dito** e a **quattro dita** (questi ultimi sempre più diffusi). Le specifiche (FBI, BSI) fissano area minima, risoluzione, risposta in frequenza spaziale e distorsione massima.
- **Degradi**: pelle **troppo secca o umida**, dita **sporche**, **impronte latenti** sul sensore, **pressione** errata, sensore ad **altezza e inclinazione** scomode, **polvere e grasso**, bagagli.
- **Valutazione della qualità**:
  - metodi **globali**, **locali** e **basati su classificatori**;
  - **NFIQ** (NIST Fingerprint Image Quality) è lo **standard de facto**: una rete neurale combina le feature e assegna la qualità; NFIQ 2.0 era allora in sviluppo;
  - **standard**: **ISO/IEC 29794-4** (qualità) e **ISO/IEC 19794-4** (formati WSQ, JPEG, JPEG2000, usato per gli e-Passport);
  - **algoritmi commerciali**: Lumidigm a Fiumicino, Morpho per PARAFE.
- **Livelli di analisi** (Lezione 3):
  - **livello 1**: flusso globale delle creste;
  - **livello 2**: **minuzie**, il più usato, che richiede almeno **500 dpi**;
  - **livello 3**: pori e dettagli fini, poco usato perché richiede sensori a risoluzione maggiore di quelli degli ABC.
- **Pipeline**: segmentazione → **enhancement** (filtri di Gabor contestuali di Hong et al., 1998) → estrazione delle minuzie (da immagine binaria, scheletrizzata o direttamente in scala di grigi) → matching **globale** (allineamento dell'intero insieme) o **locale** (sotto-regioni, grafi ausiliari).

| Contesto | FAR | FRR |
|---|---:|---:|
| valutazioni tecnologiche indipendenti | ≤ 0,01% | 0,2–0,3% |
| valutazioni tecnologiche indipendenti | 0,1% | 0,1% |
| scenario reale su larga scala (Aadhaar, miglior dito) | ≤ 0,01% | 3,5% |
| **requisito Frontex** per gli e-Gate | ≤ 0,1% | ≤ 3% |
| PEGASE, e-Gate reale | non disponibile | 1,6% |

<!-- SOURCE_VISUAL id="TAB14-V23" source="Lezione_14_DispensaABC.pdf" page="14" type="fotografie" description="Fig. 6: sensore di impronte installato in un e-Gate della Polizia di Frontiera italiana" reason="Esempio di sensore reale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.4 L'iride**

_(slide p. 60)_

_(dispensa pp. 16–19)_

**Nelle slide** (p. 60): anche l'iride è **opzionale** per l'ICAO; non tutti i documenti la includono, ma alcuni paesi la prevedono. La usa l'**8%** dei sistemi: Regno Unito, Germania, Qatar, USA, Paesi Bassi.

**Approfondimento dalla dispensa**:

- **Diffusione**: il **12%** dei sistemi censiti.
- **Vantaggi**: straordinaria **resistenza ai falsi match** e **velocità** di matching. Per questo è stata usata nei **primi** e-Gate, basati su **RTP** e **identificazione**: IRIS (Regno Unito), Privium (Paesi Bassi), NEXUS e CANPASS Air (Canada), ABG (Germania), Emirati Arabi Uniti.
- **Svantaggio principale**: **nessun e-Passport in circolazione contiene l'iride**. Servono **back-end** dedicati, quindi i sistemi sono **meno flessibili ed economici**.
- **Acquisizione**: camere a corto raggio, circa **25 cm** per un occhio e **100 cm** per entrambi, con illuminazione nel **vicino infrarosso** (700–900 nm), che rende visibile la tessitura di occhi **chiari e scuri** (Lezione 12).
- **Qualità**:
  - **fattori**: presentazione non vincolata (sguardo fuori asse, occlusioni) ed effetti di ambiente e camera (poca luce, sfocatura); si scartano pupille troppo **dilatate** e palpebre troppo **chiuse**;
  - **metodi** locali, globali e ibridi;
  - **standard**: **ISO/IEC 29794-6**, che considera anche la **compressione** (iride compressa fino a **2 kB** con poco impatto), e **ISO/IEC 19794-6** per i formati; molti sistemi RTP usano direttamente il template, **non interoperabile**.
- **Pipeline** (Lezione 4):
  - **segmentazione** con due circonferenze concentriche (Daugman) o forme più complesse (ellissi, contorni attivi);
  - **IrisCode** con wavelet di Gabor 2D, template da **512 o 1024 byte**;
  - matching con **distanza di Hamming**;
  - nei grandi database (Emirati) servono algoritmi di **ricerca veloce**;
  - reti profonde per la verifica **eterogenea** tra sensori diversi.

| Contesto | FAR | FRR |
|---|---:|---:|
| migliori algoritmi, valutazione indipendente (IREX I, 2009) | 0,001% | 0,28% |
| immagini ABC degli Emirati | 0% | 0,32% |
| Privium, con errori di lettura della smart card e di acquisizione | molto basso | circa 1,5% |
| IRIS (Regno Unito) | 0% | < 2% |

<!-- SOURCE_VISUAL id="TAB14-V24" source="Lezione_14_DispensaABC.pdf" page="17" type="fotografia" description="Fig. 7: kiosk per il riconoscimento dell'iride del programma NEXUS" reason="Esempio di sistema reale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.5 I tratti degli ABC futuri**

_(slide pp. 61–62)_

**Tratti per i gate futuri** (p. 61): **impronta contactless**, **palmo contactless**, **vene del palmo**.

**Iride on-the-move** (p. 62):

| Vantaggi | Sfide |
|---|---|
| **meno vincoli** | variabilità della **posizione dell'iride** |
| **più usabilità** | variabilità della **posizione dell'occhio** |
| maggiore **accettazione** | **occlusioni** |
| | **sfocatura** e **fuori fuoco** |

> 💡 **Collegamento con le Lezioni 11 e 12.** L'iride in movimento richiede tempi di esposizione brevissimi (senza motion blur), grande profondità di campo o messa a fuoco rapida (lenti liquide), illuminazione NIR potente ma sicura e sensori ad alta risoluzione: tutti i parametri studiati nelle lezioni sull'acquisizione.

<!-- SOURCE_VISUAL id="TAB14-V25" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="61" type="fotografie" description="Tratti per i futuri gate ABC: impronta contactless, palmo contactless, vene del palmo" reason="Esempi visivi dei dispositivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.6 Multibiometria**

_(slide p. 63)_

_(dispensa pp. 19–21)_

**Nelle slide** (p. 63): il **28%** dei sistemi usa la multibiometria (Spagna, Taiwan, Germania, Georgia…).

| Pro | Contro |
|---|---|
| **più accuratezza** | può **rallentare** il flusso dei viaggiatori |
| **più usabilità** | |
| **più sicurezza** | |

> ⚠️ **Le percentuali non sommano a 100.** Volto 60%, impronta 61%, iride 8%, multibiometria 28%: i sistemi multimodali sono contati in più categorie. Anche la dispensa, con dati diversi e precedenti (volto 45%, impronta 56%, iride 12%), conta ogni tratto «da solo o combinato con altri».

**Approfondimento dalla dispensa**:

- **Esempi**:
  - Pechino, Shenzhen Bay e J-BIS (Giappone): **volto + impronta**;
  - Friendship Gate (Pakistan): **volto, impronta e/o iride**;
  - confine Israele-Palestina: **volto + geometria della mano**.
- **Problemi dei sistemi monomodali che riduce**: **non universalità** (rilevante con una popolazione così varia), accuratezza del sensore, dati rumorosi, variabilità intraclasse, discriminabilità limitata, **robustezza allo spoofing** limitata. Con il solo volto la verifica del titolare può essere poco efficace contro il **furto d'identità**.
- **Costi**: più costi, tempi di acquisizione e di calcolo, e una percezione di maggiore **invasività**.
- **Presentazione**:
  - **simultanea**: più tratti in un solo evento, per esempio volto e iride dalla stessa camera; aumenta il throughput ma anche la complessità;
  - **sequenziale**: eventi separati.

**I quattro livelli di fusione** (Lezione 15):

| Livello | Come | Nell'ABC |
|---|---|---|
| **sensore** | concatenazione dei dati grezzi | il meno usato, complesso |
| **feature** | unione dei vettori di feature | sfrutta le dipendenze tra tratti; promettente (progetto ABC4EU) ma difficile |
| **punteggio** (*score*) | aggregazione dei punteggi, un solo modulo di decisione | **il più usato** nelle applicazioni reali: grande miglioramento, implementazione semplice |
| **decisione** | aggregazione delle decisioni binarie | applicabile a qualsiasi sistema, ma meno accurata; già usata negli ABC |

In generale la fusione **precoce** favorisce l'accuratezza, ma negli e-Gate i dati grezzi dei sensori non sono di solito accessibili, quindi si fonde a livello di **punteggio o decisione**.

**Due casi reali**, entrambi con volto e impronta in **cascata a livello di decisione** (dispensa p. 21):

- **Sistema spagnolo**: il **volto** è primario, con **due soglie** $t_L < t_U$ sul punteggio del volto $s_f$:
$$\text{esito} = \begin{cases} \text{rifiuto} & \text{se } s_f < t_L \\ \text{verifica con l'impronta} & \text{se } t_L \leq s_f \leq t_U \\ \text{accettazione} & \text{se } s_f > t_U \end{cases}$$
L'impronta si usa solo nella **zona di incertezza**. Risultato: il FRR scende dal **12,23%** al **3,72%**.
- **Sistema italiano**: il volto è primario; **se la verifica del volto fallisce**, il viaggiatore passa **sempre** alla verifica dell'impronta, indipendentemente dal punteggio. L'autorità **non** ha riportato miglioramenti delle prestazioni.

> 💡 **Perché la cascata spagnola funziona.** I viaggiatori con punteggio del volto molto alto o molto basso vengono decisi subito, senza rallentare il flusso. Solo i casi dubbi, quelli che altrimenti genererebbero la maggior parte degli errori, pagano il tempo aggiuntivo dell'impronta. Si ottiene quasi l'accuratezza della multibiometria con quasi il throughput del monomodale.

#### **14.7 Diffusione nel mondo**

_(dispensa pp. 21–22)_

- Gli ABC erano presenti in **48 paesi**; la maggior parte degli e-Gate è negli **aeroporti**. La dispensa indica per i porti terrestri e marittimi quote del **4,7%** e del **22%**.
- Molti sistemi usano il **volto** (Portogallo, Australia, USA); l'**impronta** è diffusa in Francia, USA, Ruanda, Venezuela, Qatar; Venezuela, Colombia ed Estonia combinano volto e impronta.
- Si distinguono i sistemi **regolari**, per chi ha un e-MRTD, e quelli **RTP**, con tessera di iscrizione. **Global Entry** (USA) è il più grande sistema con registrazione.
- Tra i sistemi **con registrazione** prevale l'**impronta**, tra quelli **senza registrazione** prevale il **volto**. Senza registrazione, esclusa l'UE (il cui passaporto contiene le impronte), tratti diversi dal volto si usano solo nel **35%** dei casi.

<!-- SOURCE_VISUAL id="TAB14-V26" source="Lezione_14_DispensaABC.pdf" page="22" type="mappa e tabelle" description="Fig. 8: mappa dei tratti usati negli ABC nel mondo; Tabella I: modalità nelle 181 installazioni aeroportuali; Tabella II: modalità con e senza registrazione" reason="I valori numerici delle tabelle non sono presenti nel livello testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **15. Sfide aperte: usabilità, valutazione, attacchi e privacy**

_(dispensa pp. 22–31)_

#### **15.1 Usabilità ed ergonomia**

| | **Usabilità** (ISO 9241-11) | **Ergonomia** |
|---|---|---|
| Definizione | misura in cui utenti specifici raggiungono obiettivi specifici con **efficacia, efficienza e soddisfazione** in un contesto d'uso | principi di progetto per disporre i componenti in modo che l'uso sia **facile e sicuro** |
| Negli ABC | soprattutto l'**interfaccia** che guida il viaggiatore | soprattutto la **posizione** dei dispositivi biometrici |
| Misure | efficacia = errori e tasso di completamento; efficienza = tempo; soddisfazione = percezione | altezza e inclinazione dei sensori, dimensioni del varco |

**Scelte di progetto**:

- **simboli** invece di testo, per viaggiatori di lingue e culture diverse;
- indicazioni su dove mettere i **piedi**, dove **guardare**, quale **dito** usare;
- **luci** che si accendono sul sensore attivo;
- sensori **regolabili in altezza** e **inclinazione**.

**Viaggiatori con esigenze particolari**:

| Categoria | Problemi | Soluzioni proposte |
|---|---|---|
| **feriti** | tratto non utilizzabile, difficoltà ad appoggiare il dito | controllo manuale con operatore e scanner mobile |
| **con ausili per camminare** | mano occupata, varco stretto | sedia o supporto |
| **in sedia a rotelle** | varco stretto, sensori troppo alti | varchi larghi, regolazione automatica di altezza e inclinazione |
| **con mobilità limitata degli arti** | posa frontale, pressione del dito, avvicinamento all'iride | algoritmi per volti non frontali, scanner con sensori di pressione, iride fuori asse |
| **daltonici** | colori dell'interfaccia | combinazioni di colori sicure |
| **ipovedenti** (anche anziani) | lettura, posizionamento preciso | simboli grandi, luci, suoni |
| **non vedenti** | nessuna istruzione grafica utilizzabile | annotazioni Braille per chi conosce il luogo, altrimenti un operatore avvisato per tempo |
| **anziani** | declino cognitivo, tempi di reazione, modelli mentali | formazione, feedback, interfaccia adatta; la pratica migliora le prestazioni |

**Tendenze di ricerca**:

- progetti **ABC4EU**, **FastPass**, **MobilePass** per armonizzare gli e-Gate;
- riconoscimento del volto **3D**;
- nel progetto ABC4EU, un metodo per riconoscere il **tipo di sporco** sul dito (farina, crema) o le alterazioni temporanee (mano gonfia per una borsa pesante) e **suggerire un'azione correttiva** al viaggiatore;
- sistemi **contactless** e meno vincolati per iride, palmo e impronta.

#### **15.2 Valutare il sistema biometrico**

**Otto aspetti** di valutazione:

| Aspetto | Significato nell'ABC |
|---|---|
| **accuratezza** | EER, FAR, FRR del confronto con l'e-Passport o dell'identificazione (RTP, watch list) |
| **velocità** | cruciale soprattutto in identificazione |
| **scalabilità** | efficienza al crescere di viaggiatori e iscritti |
| **interoperabilità** | formati comuni di dati e punteggi, stessi dispositivi |
| **usabilità** | facilità d'uso e di apprendimento |
| **accettazione sociale** | usabilità, invasività, rischi reali e percepiti, opinioni |
| **sicurezza** | anti-spoofing e difesa dell'architettura |
| **privacy** | protezione da furto e abuso dei dati |

> ⚠️ **Perché è difficile misurare l'accuratezza di un ABC reale.** Ci sono **pochissimi tentativi di impostori**: quasi tutti i viaggiatori sono genuini, quindi il FAR non si stima con precisione (regola del 3, Lezione 9). Inoltre molte legislazioni **non permettono di divulgare** dati biometrici, punteggi e soglie dei sistemi governativi. L'accuratezza si stima quindi con **valutazioni tecnologiche** su dataset pubblici o con **test interni**, eventualmente raccogliendo dati su un sottoinsieme delle transazioni con adeguate tecniche di protezione.

#### **15.3 Vulnerabilità e attacchi**

Gli **otto tipi di attacco** di Ratha (Lezione 13, §1.4) adattati all'e-Gate (Tabella III):

| Tipo | Punto di attacco | Descrizione | Classe |
|---|---|---|---|
| 1 | **sensore** | **spoofing**: tratto falso | **diretto** |
| 2 | canale sensore → estrattore | reinvio di un campione memorizzato | indiretto |
| 3 | **estrattore** | forzare un template scelto dall'attaccante (richiede per esempio un trojan) | indiretto |
| 4 | canale estrattore → matcher | reinvio di un template memorizzato | indiretto |
| 5 | **matcher** | forzare punteggi più alti o più bassi | indiretto |
| 6a | database esterni (EES, RTP) | alterare i dati biometrici memorizzati | indiretto |
| 6b | **chip dell'e-MRTD** | alterare i dati nel documento, possibile con un **documento falso** | indiretto |
| 7a | canale database esterno → ABC | sostituire i dati con quelli dell'attaccante | indiretto |
| 7b | canale **e-MRTD → lettore** | sostituire i dati; rilevante perché la comunicazione è **wireless** | indiretto |
| 8 | canale matcher → meccanismo del gate | **sovrascrivere la decisione** | indiretto |

**Difese e osservazioni**:

- contro il tipo 6b, gli e-MRTD hanno **elementi di sicurezza ottici** che rivelano le manomissioni, dati **firmati digitalmente** dall'emittente e accesso al chip protetto da **BAC** (*Basic Access Control*) o **EAC** (*Extended Access Control*);
- per il tipo 7b: il chip RFID è progettato per **0–10 cm**, ma si possono costruire dispositivi che operano a **distanze maggiori**;
- gli attacchi **indiretti** richiedono conoscenza del sistema e spesso **accesso fisico** ai componenti, quindi sono **più complessi**;
- gli attacchi **diretti** richiedono **solo un tratto falso** e sono quindi più praticabili: servono test di **liveness**, richiesti dalle linee guida Frontex per volto e impronta e specificati da **ISO/IEC 30107** (allora in preparazione);
- **caso reale**: nel **2008** le guardie giapponesi hanno scoperto una donna che usava un **nastro speciale** sulle dita per ingannare il sensore di impronte, e ritengono che molti altri lo abbiano fatto.

**Approcci anti-spoofing**:

| Approccio | Esempio |
|---|---|
| **sensori diversi** | volto e iride acquisiti insieme |
| **dati aggiuntivi** dallo stesso sensore | più immagini del volto per rilevare **movimento** o **flusso sanguigno** |
| **elaborazione del campione** | cercare tracce di **stampa** nell'immagine del volto |

| Tratto | Attacchi | Contromisure |
|---|---|---|
| **volto 2D** | foto, video, trucco, maschere, teste di manichino (il 3D resiste meglio, ma è vulnerabile a maschere 3D) | analisi del **movimento**, della **texture**, **liveness** |
| **impronta** | dita finte in **gelatina** o **silicone**, dita di cadavere | analisi della **vitalità**, del comportamento **dinamico**, delle caratteristiche **statiche** |
| **iride** | occhi artificiali, iridi stampate, **lenti a contatto** cosmetiche stampate, display | caratteristiche **fisiologiche** (movimento dell'occhio) o **ottiche** (riflettanza) |

Le tecniche anti-spoofing hanno una **validità limitata nel tempo**, perché gli attacchi migliorano: da qui iniziative come il progetto **TABULA RASA** e il gruppo di esperti del **Biometrics Institute**. Lo spoofing è il tema delle Lezioni 19–20.

<!-- SOURCE_VISUAL id="TAB14-V27" source="Lezione_14_DispensaABC.pdf" page="28" type="schema e tabella" description="Fig. 9: vulnerabilità del sistema biometrico di un e-Gate e punti di attacco; Tabella III: panoramica degli attacchi" reason="Posizione dei punti di attacco nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **15.4 Privacy negli ABC**

**Dilemmi etici**:

- la biometria può **categorizzare** le persone per genere, età, etnia o peso;
- concetti come usabilità o «impossibilità di usare il sistema» possono definire che cosa è **«normale»** (*normalization*), con rischio di **esclusione sociale**.

**Rischi specifici**:

- i tratti **non si possono cambiare**;
- un'associazione errata (per esempio un'impronta finita per sbaglio in una **watch list**) è **difficile da rimuovere**;
- il **furto** di un tratto non sempre viene scoperto.

**Rischi percepiti e reali** (Lezione 13):

- la popolazione teme **watch list** e **tracciamento**, anche perché non sempre conosce gli scopi;
- i sistemi **nascosti** e **governativi** sono più invasivi;
- i tratti adatti all'**identificazione 1:N** (volto, impronta, iride), usati nella maggior parte degli ABC, sono più rischiosi di mano, voce e firma.

**Approcci diversi tra stati**:

- **SmartGate** (Australia) archivia dati MRZ e immagini fino a **sette anni** in un database centrale;
- nell'**UE** i dati devono essere trattati in modo più restrittivo e **anonimizzati** prima della memorizzazione;
- i dati biometrici degli e-Passport europei si possono usare **solo** per il rilascio dei documenti e la verifica al passaggio.

**Quattro aspetti** da considerare (le linee guida della Lezione 13, §4):

1. **scopo** comunicato e invariato. Il progetto FastPass ha rilevato che i viaggiatori **credono** che l'e-Gate conservi i loro dati per un tempo sconosciuto e li condivida con ogni sorta di agenzia;
2. **controllo dell'utente**: diritto alla **cancellazione** dei dati;
3. **trasparenza** su scopo, modalità e protezione;
4. **protezione dei dati**: rappresentazioni crittografiche e controllo di accesso sicuro agli e-Passport.

> ⚠️ **Lo stato dell'arte è in ritardo.** Per l'accesso ai dati dell'e-Passport si usano metodi **PKI**, ma la **protezione crittografica dei template** non è diffusa: i passaporti contengono di norma **campioni veri**. AES e RSA non si applicano ai dati biometrici per la **variabilità intraclasse**: servono biometria cancellabile, crittosistemi biometrici o metodi crittograficamente sicuri (Lezione 13). Il progetto **FIDELITY** studia e-Passport più sicuri e rispettosi della privacy. Ulteriori raccomandazioni: **ISO/IEC TR 24714-1**.

---

### **16. Casi applicativi**

#### **16.1 Sistemi multimodali e a basso impatto**

_(slide pp. 64–67)_

Le applicazioni aeroportuali (p. 64) sono le stesse del §10: **baggage drop**, **eGate ABC**, **self-boarding**.

**Vision-Box Happyflow** (p. 65): sistema **integrato multimodale** con **iride**, **impronta** e **volto**.

**Soluzioni a bassissimo impatto infrastrutturale e alta velocità** (p. 66): eGate di **Thales**, impiegabili anche per **stadi e concerti**.

**Ulteriori azioni per le applicazioni** (p. 67):

| Azione | Domanda da porsi |
|---|---|
| **Integrazione** di ABC ed eGate con altri sistemi | conviene collegarli a sistemi visti, programmi per viaggiatori registrati, sistemi di ingresso e uscita e dispositivi mobili? (vedi Vision-Box) |
| **Procedure di emergenza e anomalie** | come si gestiscono **documenti danneggiati** o **comportamenti sospetti**? |
| **Formazione e operatività del personale** | come cambiano responsabilità e competenze delle guardie? I sistemi **non sono completamente unattended** |
| **Feedback dei viaggiatori** | come percepiscono le tecnologie? Serve migliorare il **signalling** o semplificare le procedure? |

<!-- SOURCE_VISUAL id="TAB14-V28" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="64" type="fotografie" description="Applicazioni in aeroporto: baggage drop, eGate ABC, self-boarding gate" reason="Pagina con sole immagini ed etichette" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V29" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="65" type="fotografie" description="Sistema multimodale Vision-Box Happyflow con iride, impronta e volto e luogo di installazione" reason="Esempio visivo del sistema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V30" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="66" type="fotografie prodotto" description="eGate Thales a bassissimo impatto infrastrutturale, anche per stadi e concerti" reason="Esempio visivo del prodotto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **16.2 Vision-Box Seamless Journey Platform**

_(slide pp. 68–78)_

**L'azienda** (p. 69):

- leader con **oltre il 30%** del mercato;
- **oltre 2 miliardi** di utenti processati;
- installazioni in **oltre 100 paesi**;
- certificazione **«Privacy by Design»** rilasciata da **MSECB** (ente specializzato nella certificazione dei sistemi di gestione), secondo il programma del *Privacy by Design Centre for Excellence* della Ryerson University (Canada). La privacy by design è richiesta anche dal **GDPR**.

**Il viaggio del passeggero**:

| Fase | Che cosa succede |
|---|---|
| **A casa** (p. 70) | con il **cellulare** e l'app della compagnia aerea, come nell'apertura di un conto corrente da remoto, si crea un **token biometrico** (*Digital ID*): autenticazione **1:1** con l'**MRTD** scansionato, creazione della carta d'imbarco. Il sistema ottimizza accuratezza e velocità e funzionerà poi anche in **identificazione 1:N** |
| **In aeroporto** (p. 71) | il sistema lavora in **1:N**. I limiti di accuratezza del volto **su scala nazionale** sono aggirati creando **dinamicamente gallery** con i soli **utenti previsti in aeroporto** |
| **Passi successivi** (p. 72) | il riconoscimento è **automatico** |
| **Servizi** (p. 73) | l'aeroporto diventa un **ecosistema smart** che **offre e vende** servizi: basandosi sull'identificazione, senza documenti, token o interazioni particolari, si acquista e si accede ad aree riservate come le **lounge** |
| **Bag drop** (p. 74) | consegna dei bagagli **senza personale**: l'utente etichetta i bagagli e, dopo essere stato identificato, li appoggia sul nastro |
| **Gate** (p. 75) | accesso all'imbarco con identificazione **1:N**, con **N limitato** dalla selezione dinamica delle persone previste |
| **All'arrivo** (p. 76) | **screening automatico** per la nuova frontiera (*immigration exit*) o un nuovo imbarco in caso di **transito** |

Seguono **servizi e accessori** (p. 77) e l'esempio del **Dubai Airport** (p. 78): **Integrated Biometric Path** con riconoscimento **facciale e contactless «hands-free»** per check-in e immigrazione, lounge e imbarco.

> 📌 **Perché le gallery dinamiche funzionano.** Ricordando dalla Lezione 1 e dalla Lezione 9 che in identificazione
> $$FPIR \approx N \cdot FMR \qquad \text{per } N \cdot FMR \ll 1$$
> il tasso di falsi positivi cresce **linearmente con la dimensione della gallery**. Con un $FMR = 10^{-6}$:
>
> | Gallery | $N$ | $N \cdot FMR$ |
> |---|---:|---:|
> | popolazione nazionale (ordine di grandezza) | $6 \times 10^{7}$ | $60$: l'approssimazione non vale più, falsi match quasi certi a ogni ricerca |
> | passeggeri previsti in un giorno in un aeroporto | $5 \times 10^{4}$ | $0{,}05$ |
> | passeggeri di un singolo volo | $3 \times 10^{2}$ | $3 \times 10^{-4}$ |
>
> Restringere $N$ ai soli passeggeri **attesi** (e ancora di più a quelli di un **volo**) rende il volto utilizzabile in 1:N, pur senza cambiare l'algoritmo. I valori numerici sono un esempio didattico.

> ⚠️ **Il rovescio della medaglia: privacy.** Un sistema 1:N che accompagna il passeggero da casa all'imbarco e **vende servizi** basati sull'identificazione tocca molte domande a rischio maggiore della Lezione 13 (§3.1). Il Garante italiano, nel febbraio 2026, ha **bloccato** sistemi di *facial scanning* aeroportuali usati al posto del biglietto per carenze di cifratura e di controllo dell'utente (Lezione 13, §17).

<!-- SOURCE_VISUAL id="TAB14-V31" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="70" type="schermate app" description="Creazione del token biometrico a casa con app della compagnia aerea e confronto 1:1 con l'MRTD" reason="Flusso mostrato con schermate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V32" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="71" type="schema" description="Identificazione 1:N in aeroporto con gallery dinamiche dei passeggeri previsti" reason="Schema delle gallery dinamiche" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB14-V33" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="77" type="fotografie prodotto" description="Seamless Journey Platform: servizi e accessori" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **17. Conclusioni**

_(slide pp. 79–80)_

_(dispensa p. 31)_

La slide p. 79 allega la **dispensa ABC**, **non richiesta all'esame**, per chi è interessato al tema.

**In sintesi** (p. 80): sicurezza fisica (basi, elementi, focus sulla biometria); Automated Border Control (introduzione e contesto, tailgating e piggybacking con contromisure, biometria monomodale e multimodale, biometria sui terminali mobili, esempi applicativi Vision-Box Happyflow e Seamless Journey Platform).

**Conclusioni della dispensa** (p. 31): l'ABC è una grande innovazione del trasporto passeggeri, fortemente legata alla biometria e studiata sia dall'industria sia dalla ricerca. Il survey presenta l'architettura degli e-Gate, le procedure di verifica, le tecniche di riconoscimento e i principali problemi aperti.

<!-- SOURCE_VISUAL id="TAB14-V34" source="Lezione_14_ElevataSicurezzaFisica_ABC.pdf" page="79" type="copertina" description="Dispensa ABC allegata, non richiesta all'esame" reason="Pagina con immagine della copertina" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **18. Bibliografia della dispensa**

_(dispensa pp. 32–39)_

La dispensa riporta circa **170 riferimenti**. I più utili per approfondire:

| Tema | Riferimenti |
|---|---|
| Linee guida ABC | Frontex, *Best practice operational/technical guidelines for ABC systems* (2012); IATA, *ABC implementation guide* (2014); ISO/IEC TR 29195 (2015) |
| Documenti | ICAO Doc 9303, *Machine Readable Travel Documents* (2006, 7ª ed. 2015) |
| Casi reali | Frontex BIOPASS (2007) e BIOPASS II su RAPID e SmartGate (2010); Spreeuwers et al., volto a Schiphol (2012); Cuesta Cantarero et al., fusione nel sistema spagnolo (2013) |
| Standard | ISO/IEC 19794 (formati), 29794 (qualità), 19784 (BioAPI), 19785 (CBEFF), 30107 (presentation attack detection), TR 24722 (fusione), TR 24714 (aspetti giuridici e sociali) |
| Tecniche | Daugman, *How iris recognition works* (2004); Maltoni et al., *Handbook of Fingerprint Recognition* (2009); Taigman et al., *DeepFace* (2014); Ross, Nandakumar, Jain, *Handbook of Multibiometrics* (2006) |
| Valutazione | Mansfield e Wayman, *Best practices in testing and reporting performance of biometric devices* (2002, Lezione 10); Grother et al., IREX I (2009) |
| Anti-spoofing e privacy | Marasco e Ross, survey anti-spoofing per impronte (2014); Jain, Nandakumar, Nagar, *Biometric template security* (2008); Donida Labati, Piuri, Scotti, *Biometric privacy protection* (2012, dispensa della Lezione 13) |

---

### **19. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Sicurezza logica** / **fisica** | accesso alle informazioni, biometria come dato / accesso agli spazi, biometria come corpo |
| **Incident response** / **crisis management** | ISO/IEC 27035 / ISO 22361 |
| **Dissuasione** / **rilevamento** / **ritardo** / **risposta** | scoraggiare / accorgersi / rallentare / reagire, dall'esterno all'interno |
| **Tailgating** / **piggybacking** | l'autorizzato non se ne accorge / l'autorizzato acconsente |
| **ABC** / **eGate** | l'intero sistema automatizzato / il varco, uno dei suoi componenti |
| **Man-trap** / **virtual man-trap** | due porte, una persona alla volta / sensori e software che rilevano più persone |
| **One-step** / **two-step** | tutto in una transazione, veloce per esperti / due passi con token temporaneo, più controllo e usabilità |
| **Two-step integrated** / **segregated** | lettore e scanner nello stesso luogo / kiosk separato più e-Gate a porta singola |
| **VIS** / **EES** / **RTP** | domande di visto / ingressi e uscite al posto del timbro / viaggiatori frequenti pre-verificati |
| **Verifica 1:1** / **identificazione 1:N** | viaggiatore contro documento / senza documento, watch list, RTP, EES, due passi segregati, gallery dinamiche |
| **Carattere** / **fedeltà** / **utilità** | qualità intrinseca del tratto / fedeltà dell'acquisizione / effetto sul matching |
| **Approccio globale** / **locale** (volto) | tutta l'immagine, alta qualità / landmark e regioni, robusto a posa e occlusioni |
| **Usabilità** / **ergonomia** | interfaccia e istruzioni / posizione e regolazione dei dispositivi |
| **Attacco diretto** / **indiretto** | tratto falso al sensore, facile / canali e moduli, richiede conoscenza e accesso |
| **BAC** / **EAC** | controllo di accesso base al chip / esteso, per dati sensibili come le impronte |
| **Cascata spagnola** / **italiana** | impronta solo nella zona di incertezza del volto, FRR da 12,23% a 3,72% / impronta a ogni fallimento del volto, nessun miglioramento riportato |

---

### **20. Sintesi della lezione**

> ✅ **In sintesi.**
> - **Sicurezza fisica**: protegge persone, proprietà e asset; sette elementi (sito, visibilità, accessi, perimetro, rilevazione, infrastrutture, formazione); minacce di accesso non autorizzato, furto, vandalismo e terrorismo; la biometria agisce ex ante (deterrenza, accessi, anomalie) ed ex post (identificazione, ricostruzione, analisi); quattro livelli della Security Onion.
> - **Tailgating e piggybacking**: il problema non è chi entra ma **quanti** entrano; contromisure con telecamere AI, barriere, sensori a pavimento, visione stereo e varchi come i Gunnebo.
> - **ABC**: automazione delle frontiere con tre controlli (documento, biometria, autorizzazione); e-Passport ICAO Doc 9303 con volto obbligatorio e impronta e iride opzionali; componenti del gate e signalling; one-step (man-trap fisica o virtuale) e two-step (integrated o segregated); collegamenti con VIS, EES, RTP e dispositivi mobili.
> - **Tratti**: volto preferito dall'ICAO, impronta diffusa soprattutto con registrazione, iride accurata ma assente dai passaporti; qualità e requisiti Frontex (volto FAR < 0,1% e FRR < 5%, impronta FAR ≤ 0,1% e FRR ≤ 3%); tratti futuri contactless e iride on-the-move; multibiometria con fusione e cascata spagnola.
> - **Sfide**: usabilità per tutti i viaggiatori, valutazione difficile senza impostori né dati pubblici, otto punti di attacco e anti-spoofing, privacy e protezione dei template ancora poco adottata.
> - **Casi reali**: Vision-Box Happyflow e Seamless Journey Platform con token biometrico da casa e gallery dinamiche 1:N, eGate Thales, aeroporto di Dubai.
