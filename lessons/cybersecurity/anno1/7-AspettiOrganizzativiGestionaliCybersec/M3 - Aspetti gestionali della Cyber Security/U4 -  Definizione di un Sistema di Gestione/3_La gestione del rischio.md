In questa lezione introdurremo alcune nozioni sui processi di gestione del rischio focalizzandoci, in particolare, sul rischio informatico.


---
### 🔹 Il rischio e la sua gestione

Il **risk management** è il processo con cui un’organizzazione affronta le minacce che possono compromettere **capitale, utili e sicurezza delle persone**. È un’attività sistematica che comprende tre momenti chiave:

1. **Identificazione** → capire quali rischi esistono.
    
2. **Valutazione** → stimare la loro entità e gravità.
    
3. **Controllo** → mettere in atto misure di mitigazione per ridurli o eliminarli.
    

I rischi non derivano da un’unica fonte, ma possono nascere da:

- **Incertezze finanziarie** (es. oscillazioni di mercato).
    
- **Responsabilità legali** (es. contenziosi o non conformità normative).
    
- **Problemi tecnologici** (es. guasti o vulnerabilità dei sistemi).
    
- **Errori di gestione strategica** (decisioni aziendali errate).
    
- **Incidenti e disastri naturali** (eventi imprevedibili e spesso incontrollabili).
    

---

### 🔹 La doppia natura del rischio

Secondo gli **standard moderni**, il rischio non va inteso solo come **minaccia** ma anche come **opportunità**:

- Se l’evento imprevisto ha **conseguenze negative**, si tratta di una **minaccia** da gestire.
    
- Se invece porta **conseguenze positive**, diventa un’**opportunità** da cogliere.
    

In sintesi: **“rischio = possibilità di evento imprevisto → minaccia o opportunità”**.


---
### Cos’è (e perché serve) una tassonomia

Una tassonomia è solo **un modo ordinato di classificare** i rischi: dalla radice greca tàxis (“ordine”) + nòmos (“regola”). Qui la prima grande distinzione è **dove vive il rischio**:

- **Rischi esterni**: arrivano da fuori (mercati, politica, norme, calamità, ecc.).
    
- **Rischi interni**: nascono dentro l’organizzazione.  
    Fra gli interni distinguiamo **strategici**, **operativi** e **finanziari**. Nel corso ci concentreremo soprattutto sui **rischi informatici**, che sono un **sottoinsieme dei rischi interni operativi**.
    
---

### Come leggere la figura (mappa visiva dei rischi)

![Pasted image 20250913144332.png](../../imgs/Pasted%20image%2020250913144332.png)

In alto: **Rischi esterni** (concorrenza, mercati finanziari, disponibilità di capitali, politica, regolamenti, calamità naturali…). Questi sono driver che l’azienda **subisce** e che impattano strategia e continuità.  
Al centro: **Rischi interni** suddivisi in:

1. **Strategici** – es. modello di business, reputazione, dipendenza da fornitori, ritardo tecnologico: errori o scelte sbagliate **di direzione** che poi ricadono ovunque.
    
2. **Operativi** – sono i rischi “di quotidiano” e si articolano in più aree, tra cui:
    
    - **Processi** (burocrazia, processi critici non informatizzati, interruzioni attività…)
        
    - **Risorse Umane** (skill inadeguate, resistenza al cambiamento, scioperi…)
        
    - **Sistemi Informativi** (**riquadro verde** nella slide, vedi sotto)
        
    - **Informativa/Dati** (security, compliance, informazioni contabili)
        
    - **Compliance** (231, 262, 81/2008, antiriciclaggio)
        
    - **Integrità** (conflitti d’interesse, frodi)
        
    - **Legali** (contenziosi, compliance, proprietà intellettuale)
        
3. **Finanziari** – es. credito, tassi, liquidità, cambio, commodities: sono i rischi legati alla **struttura economico-finanziaria**.  
    Questa mappa serve a: (i) **non dimenticare pezzi**; (ii) **capire le interdipendenze**; (iii) **decidere dove mettere i controlli**.
    

---

### Zoom sul riquadro verde: **Sistemi Informativi** (cuore del corso)

La slide elenca i tipici **rischi operativi IT**. Per ciascuno: cosa significa in pratica e **controlli tipici** per mitigarli.

- **Sistemi frammentati** → applicazioni che non “parlano” tra loro ⇒ dati duplicati/incoerenti, errori manuali.  
    _Controlli_: integrazione (API/ETL), standardizzazione, inventario applicativo, decommissioning del legacy.
    
- **Trattamento dati disomogeneo** → regole diverse per gli stessi dati tra reparti ⇒ decisioni incoerenti.  
    _Controlli_: data governance, glossario dati, quality checks (validità, completezza), MDM dove serve.
    
- **Accesso al dato non sempre garantito** → scarsa disponibilità (downtime, colli di bottiglia).  
    _Controlli_: ridondanza/HA, backup & restore testati, monitoraggio SLO/SLA, capacity planning.
    
- **Accesso non autorizzato** → violazioni di confidenzialità e integrità.  
    _Controlli_: IAM con **least privilege**, MFA, hardening, patching, segmentazione, logging e alerting.
    
- **Manomissione dati** → alterazioni volontarie/accidentali dei dati.  
    _Controlli_: controllo versioni, checksum/hash, RBAC, journaling/immutabilità (WORM), audit trail.
    
- **Danni informatici** → malware/ransomware, guasti hardware, errori software.  
    _Controlli_: EDR/antimalware, patch management, secure SDLC, test ripristino, network segmentation.
    
- **Blocco ai sistemi informativi** → interruzione del servizio (DoS/DDoS, crash, blackout).  
    _Controlli_: CDN/WAF/anti-DDoS, failover geografico, UPS/gruppi elettrogeni, runbook incident response.
    

Questo blocco “Sistemi Informativi” è **dove atterra la cybersecurity**: disponibilità, integrità, confidenzialità, tracciabilità. È il ponte con tutto ciò che vediamo su ISMS, controlli tecnici/organizzativi e metriche.


---
## 🔹 Cos’è il rischio informatico

Il **rischio informatico** è la possibilità che un evento legato a **sistemi informatici** (hardware, software, reti, database) provochi:

- **perdite economiche/finanziarie** (es. blocco produzione, danni d’immagine, sanzioni);
    
- **conseguenze sulla sicurezza delle persone** (es. malfunzionamento di sistemi sanitari, trasporti, energia).
    

Quindi non riguarda solo “dati e computer”, ma può impattare la **continuità operativa** e persino la **salute umana**, soprattutto nei contesti industriali e critici (ospedali, ICS/SCADA, utility).

---

## 🔹 Le tre cause principali

### 1. **Errore umano** 🧑‍💻

È la causa più frequente. Può sembrare banale, ma un singolo “click di troppo” può aprire la strada a malware e attacchi:

- dipendente che apre un allegato di phishing,
    
- password debole riutilizzata,
    
- configurazione errata di un server.
    

➡️ Qui la difesa è **formazione**, **policy chiare**, **controlli tecnici** (MFA, blocchi, segmentazione).

---

### 2. **Evento accidentale** ⚡

Imprevisti tecnici o ambientali che interrompono il funzionamento dei sistemi:

- **sbalzo di tensione** che danneggia i server,
    
- **rottura impianto di climatizzazione** in data center → surriscaldamento e crash,
    
- **alluvioni, incendi, blackout**.
    

➡️ Si gestiscono con **ridondanza**, **disaster recovery**, **business continuity planning**.

---

### 3. **Azione dolosa di terzi** 🎭

Attacchi intenzionali, sia dall’interno sia dall’esterno:

- furto di informazioni riservate,
    
- attacco hacker (es. ransomware, phishing mirato, DDoS),
    
- sabotaggio interno,
    
- sequestro fisico di computer e storage.
    

➡️ Qui servono **controlli tecnici avanzati** (IDS/IPS, SIEM, crittografia) + **procedure organizzative** (gestione accessi, logging, incident response).

---

## 🔹 Collegamento con CIA triad

Tutte e tre le cause vanno viste con gli occhi della **triade CIA**:

- **Confidentiality**: accesso non autorizzato (doloso o per errore).
    
- **Integrity**: corruzione/manomissione dei dati (errore umano o attacco).
    
- **Availability**: interruzione accidentale o dolosa (downtime, blocchi).


---
Ottimo 👌 adesso siamo nella **fase di Analisi del Rischio**, cioè il cuore del risk management.

---

## 🔹 Due fasi fondamentali

1. **Identificazione** → scoprire _cosa può andare storto_ (o bene).
    
2. **Valutazione** → misurare _quanto conta_ quel rischio (probabilità × impatto).
    

---

## 🔹 Identificazione del rischio

Qui l’obiettivo è **individuare le minacce** e descriverne bene le caratteristiche.  
Ci sono due approcci principali:

### a) **Cause → Effetti**

- Metodo “diagnostico”: parti dalle **cause** possibili e immagini gli **effetti**.
    
- Esempio IT: “se non aggiorno il server → vulnerabilità → exploit → data breach”.
    
- È utile quando vuoi prevenire in anticipo gli scenari peggiori.
    

### b) **Effetti → Cause**

- Metodo “forense” o “retrospettivo”: parti dagli **effetti** osservabili e risali alle **cause**.
    
- Esempio IT: “il sistema è down → possibili cause: guasto hardware, attacco DoS, errore configurazione”.
    
- È molto usato in informatica perché di solito si parte dall’**effetto visibile sugli asset** (es. un malfunzionamento) e si indaga la catena causale.
    

💡 **Nota didattica**: in ambito informatico, è più pratico il secondo approccio perché spesso ci accorgiamo del **danno** prima di conoscere la causa (un crash, un blocco, un alert). Da lì si risale all’origine.

---

## 🔹 Valutazione del rischio

Una volta individuato, dobbiamo stimarne l’entità con **due parametri**:

- **Probabilità**: quanto è realistico che accada.
    
- **Impatto**: quanto sarebbe grave se accadesse.
    

➡️ Questi due valori vengono poi combinati (nella slide successiva vedremo la formula **R = P × I** e la matrice a colori verde-giallo-rosso).

---

## 🔹 Collegamento al risk management

- **Identificazione** = creare un inventario strutturato di minacce/scenari.
    
- **Valutazione** = dare priorità (decidere su quali rischi concentrare budget e risorse).
    
- Entrambe le fasi sono **necessarie prima di pianificare qualsiasi controllo**.


---
## 🔹 La formula del rischio

Il rischio viene quantificato come:

$$\textbf{Rischio = Probabilità × Impatto}$$

- **Probabilità (asse X del grafico)** → quanto è realistico che l’evento accada.  
    Si stima guardando **dati storici** (incidenti passati) o **proiezioni** (trend futuri).  
    Viene classificata in 5 livelli: **molto bassa, bassa, media, alta, molto alta**.
    
- **Impatto (asse Y del grafico)** → quanto è grave l’effetto se l’evento si verifica.  
    Può riguardare perdita economica, danni operativi, reputazione, sicurezza fisica.  
    Anche qui ci sono 5 livelli: da **molto basso** a **molto alto**.
    

---

## 🔹 Il grafico: matrice del rischio

![Pasted image 20250913144632.png](../../imgs/Pasted%20image%2020250913144632.png)

La matrice 5×5 che vedi è uno strumento visuale per **mappare i rischi**:

- **Zona verde (1–4)** → rischio basso, accettabile. Non richiede azioni particolari, basta monitoraggio.
    
- **Zona gialla/arancione (6–12)** → rischio medio. Richiede attenzione, valutazione di controlli e misure di mitigazione proporzionate.
    
- **Zona rossa (15–25)** → rischio alto/critico. Non può essere trascurato: richiede interventi urgenti e spesso strutturali (es. investimenti, ridondanza, nuove policy).
    

### Come leggerla:

- In basso a sinistra → **eventi improbabili e poco impattanti** (verde).
    
- In alto a destra → **eventi probabili e molto impattanti** (rosso).
    
- Al centro → la “terra di mezzo”, dove il management deve decidere se tollerare, mitigare o trasferire il rischio.
    

---

## 🔹 Nota metodologica

A volte invece della scala 1–5 si usa 1–4 per **evitare valori mediani simmetrici (3×3=9)** che rischiano di creare ambiguità. Così si obbliga il valutatore a sbilanciarsi verso “alto” o “basso”.



---
# Processo operativo di gestione del Rischio
## 🔹 Idea di fondo

La gestione del rischio **non è un evento puntuale**, ma un **ciclo continuo**.  
Ogni fase prepara la successiva e i risultati del controllo riportano indietro all’identificazione → creando un **loop di miglioramento continuo**.

---

## 🔹 Le fasi del processo

![Pasted image 20250913144837.png](../../imgs/Pasted%20image%2020250913144837.png)

### 1. **Identificazione** 🔍

- Domanda chiave: _quali sono le possibili cause di rischio?_
    
- Si mappano minacce e vulnerabilità, aggiornando costantemente gli **indicatori** (eventi storici, anomalie, trend).
    
- Esempio IT: elenco delle vulnerabilità note nei server, accessi non autorizzati, incidenti passati.
    

---

### 2. **Valutazione** 📊

- Domanda chiave: _quanto è grave e quanto è probabile?_
    
- Si stima il rischio usando i parametri **Probabilità × Impatto**.
    
- Output: una **mappa dei rischi prioritizzati**, utile per decidere su cosa intervenire.
    

---

### 3. **Pianificazione** 📝

- Domanda chiave: _come affrontiamo i rischi più rilevanti?_
    
- Si definiscono **azioni di mitigazione**, assegnando **ruoli, responsabilità e budget**.
    
- Esempio: per rischio blackout → comprare UPS, assegnare a IT manager il test semestrale, budget X €.
    

---

### 4. **Esecuzione** ⚙️

- Domanda chiave: _stiamo mettendo in pratica le misure pianificate?_
    
- Implementazione operativa: applicazione patch, formazione utenti, installazione firewall, revisione policy, test di DR.
    

---

### 5. **Controllo** ✅

- Domanda chiave: _le misure funzionano davvero?_
    
- Si verifica l’efficacia delle azioni tramite **Key Risk Indicators (KRI)** e audit periodici.
    
- Se i controlli non bastano → si ricomincia dal ciclo (nuova identificazione).
    

---

## 🔹 Perché la forma circolare?

- Simbolizza che non è un “progetto a scadenza”, ma un **processo ciclico e iterativo**.
    
- Ogni volta che emergono **nuove minacce** (es. ransomware zero-day) o cambiano **contesto e business** (es. smart working massivo), il ciclo si aggiorna.
    
- Questo garantisce **resilienza nel tempo**, non solo sicurezza “istantanea”.


---
# Definizione di una strategia di mitigazione
## Risk Appetite

- È il **livello massimo di rischio** (per probabilità × impatto) che un’organizzazione **è disposta ad accettare** senza compromettere i propri obiettivi strategici.
    
- Funziona come **confine operativo**: sotto questa soglia si può convivere con il rischio, sopra bisogna intervenire.
    

---

## 🔹 Le tre aree di gestione del rischio

![Pasted image 20250913145000.png](../../imgs/Pasted%20image%2020250913150000.png)

Il grafico divide lo spazio **Probabilità–Impatto** in tre zone, delimitate da due soglie:

1. **Soglia di indifferenza** (verde chiaro → area di attenzione)
    
    - Qui il rischio è **basso**.
        
    - L’azienda **non applica misure straordinarie** di mitigazione, ma solo controlli di routine (es. audit periodici, monitoraggio base).
        
    - Esempio: rischio “guasto stampante in ufficio” → costo basso, probabilità gestibile, nessun intervento extra.
        

---

2. **Tra soglia di indifferenza e risk appetite** (giallo → area di monitoraggio)
    
    - Qui il rischio è **medio**, quindi non può essere ignorato.
        
    - Richiede **monitoraggio costante e gestione attiva**, con misure organizzative e/o tecniche proporzionate.
        
    - Esempio: rischio “interruzione temporanea rete aziendale” → serve monitoraggio con sistemi di alert, piano di continuità e responsabilità definite.
        

---

3. **Sopra la soglia di Risk Appetite** (rosso → area di evitare/ridurre)
    
    - Qui il rischio è **inaccettabile**.
        
    - L’organizzazione deve **evitarlo** o **mitigarlo drasticamente** usando tutte le risorse disponibili.
        
    - Esempio: rischio “perdita massiva di dati sanitari” → implementazione obbligatoria di backup cifrati, disaster recovery, test periodici.
        

---

## 🔹 Come leggere il grafico

- **Asse X** = probabilità (da molto bassa a molto alta).
    
- **Asse Y** = impatto (da molto basso a molto alto).
    
- L’area verde è “zona di comfort”, la gialla è “zona di attenzione”, la rossa è “zona critica”.
    
- Il **punto Rischio X** (nel giallo) è un esempio: non è trascurabile, ma è ancora sotto al limite massimo → quindi va **monitorato costantemente**, non ignorato.
    

---

👉 In altre parole: questa classificazione è come un **semaforo del rischio**:

- **Verde → procedi** (attenzione minima).
    
- **Giallo → rallenta e osserva** (monitoraggio stretto).
    
- **Rosso → stop** (evitare o ridurre subito).


---
## 🔹 Perché servono i KRI

Nelle organizzazioni moderne esistono **molti strumenti di sicurezza** (firewall, SIEM, IDS, EDR, sistemi di logging, ecc.).  
Paradossalmente, questa abbondanza genera **complessità di governance**: i dati arrivano da fonti eterogenee e aggregarli in modo coerente diventa difficile.

👉 Per questo è indispensabile definire **indicatori chiave di rischio (KRI)**, ossia **metriche affidabili e significative** che:

- misurino in modo continuativo il **valore delle risorse aziendali**;
    
- permettano di verificare i **progressi verso gli obiettivi pianificati**;
    
- offrano una **mappa chiara dell’esposizione al rischio** e del livello di sicurezza.
    

In sostanza, i KRI traducono la teoria del risk management in **numeri concreti e monitorabili**.

---

## 🔹 Esempi di KRI principali

![Pasted image 20250913145330.png](../../imgs/Pasted%20image%2020250913153330.png)

### 1. **Numero di attività critiche con vulnerabilità note**

- Se molte attività essenziali presentano vulnerabilità non risolte, l’azienda è più esposta a rischi.
    
- Incrociando **punti di forza** (asset protetti) e **vulnerabilità note**, si possono **prioritizzare le azioni**.
    

---

### 2. **Vulnerabilità interne vs. esterne**

- Distinzione fra quelle che l’azienda può gestire direttamente (configurazioni, codice, patching) e quelle legate a terzi (supply chain, fornitori).
    
- Le vulnerabilità esterne sono spesso più costose e rischiose perché fuori dal controllo diretto dell’azienda.
    

---

### 3. **Frequenza della revisione di terzi (audit esterni)**

- Le revisioni periodiche della rete esterna permettono di intercettare punti deboli che internamente possono sfuggire.
    
- Se la frequenza cala, aumenta la probabilità che le **best practice decadano** (modifiche non controllate, cambi di priorità, eccesso di fiducia).
    

---

### 4. **Numero di utenti con accesso come “superuser”**

- Troppi privilegi aumentano il rischio di **errori o abusi**.
    
- Rischi:
    
    1. utenti che modificano inconsapevolmente configurazioni critiche;
        
    2. accesso a informazioni sensibili da parte di chi non dovrebbe.
        
- Soluzione: applicare il principio del **least privilege** e ridurre i superuser al minimo indispensabile.
    

---

### 5. **Numero di giorni per disattivare le credenziali degli ex dipendenti**

- Ogni ritardo = finestra di esposizione in cui un ex dipendente può accedere impropriamente.
    
- Questo KRI è anche un KPI di efficienza HR.
    
- Best practice: **automazione** (collegare HR database ai sistemi di IAM).
    

---

### 6. **Frequenza di accesso ai sistemi aziendali essenziali da parte di terzi**

- Monitorare le abitudini di accesso di fornitori e partner.
    
- Accessi anomali o troppo frequenti possono segnalare un potenziale abuso o attacco.
    

---

### 7. **Tempo medio di rilevamento degli incidenti (MTTD)**

- Quanto tempo passa prima che un incidente venga scoperto?
    
- Un MTTD basso = sistemi di monitoraggio e canali di segnalazione efficaci.
    
- Un MTTD alto = possibili falle tecnologiche o culturali (dipendenti che non segnalano).
    

---

### 8. **Tempo medio di risoluzione (MTTR)**

- Indica l’efficienza del team di risposta agli incidenti.
    
- Un MTTR basso = buona prontezza e processi ben rodati.
    
- Un MTTR alto = rischio di perdite prolungate e danni reputazionali.
    

---

### 9. **Tempo di interruzione a seguito di un attacco**

- Misura i **tempi di inattività operativa** dopo un attacco (es. DDoS, ransomware).
    
- Diretto legame con **perdite economiche** e **continuità aziendale**.
    
- Serve per quantificare l’impatto degli incidenti sul business.
    

---

### 10. **Numero di corsi di formazione completati**

- Misura la diffusione delle **competenze di sicurezza** tra gli stakeholder.
    
- Se pochi completano i corsi, è probabile che aumentino gli **errori umani** (causa principale di incidenti).
    

---

### 11. **Numero di quiz di attestazione superati**

- Complemento alla formazione: verifica che i concetti siano **davvero assimilati**.
    
- Importante perché non basta “frequentare il corso”, ma serve dimostrare comprensione delle policy.
    

---

## 🔹 Interpretazione visiva (tachimetro)

Il grafico in basso a destra della slide mostra un **indicatore tipo tachimetro**:

- **Verde** = livello di rischio accettabile, KRI in range positivo.
    
- **Giallo** = attenzione, valori borderline, rischio crescente.
    
- **Rosso** = situazione critica, servono azioni immediate di mitigazione.
    

---

👉 In sintesi: i **KRI** sono la bussola della sicurezza. Consentono al management di capire se il livello di rischio è sotto controllo, da monitorare o da ridurre urgentemente.


---
# 📊 Tabella riassuntiva KRI

|**KRI**|**Rischio che segnala**|**Interpretazione (Verde / Giallo / Rosso)**|
|---|---|---|
|Numero di attività critiche con vulnerabilità note|Esposizione diretta a exploit e attacchi|Verde: poche vulnerabilità note.  <br>Giallo: vulnerabilità in crescita.  <br>Rosso: molte vulnerabilità aperte su sistemi critici.|
|Vulnerabilità interne vs. esterne|Dipendenza da fattori interni o supply chain|Verde: vulnerabilità gestibili internamente.  <br>Giallo: alcune vulnerabilità esterne non mitigate.  <br>Rosso: prevalenza di vulnerabilità esterne fuori controllo.|
|Frequenza della revisione di terzi|Qualità delle verifiche esterne (audit)|Verde: audit regolari e frequenti.  <br>Giallo: revisioni saltuarie.  <br>Rosso: audit mancanti o molto rari.|
|Numero di utenti “superuser”|Eccessivi privilegi, rischio di abuso o errore|Verde: pochi superuser ben controllati.  <br>Giallo: numero medio senza regole chiare.  <br>Rosso: molti superuser senza gestione dei privilegi.|
|Giorni per disattivare credenziali ex dipendenti|Rischio accessi non autorizzati dopo l’uscita|Verde: disattivazione immediata (1 giorno).  <br>Giallo: ritardi di alcuni giorni.  <br>Rosso: settimane di accesso non revocato.|
|Accessi ai sistemi aziendali essenziali da parte di terzi|Rischio abuso o anomalia nei fornitori|Verde: accessi rari e giustificati.  <br>Giallo: accessi frequenti ma monitorati.  <br>Rosso: accessi anomali o incontrollati.|
|Tempo medio di rilevamento incidenti (MTTD)|Ritardo nell’individuare le minacce|Verde: incidenti rilevati rapidamente.  <br>Giallo: tempi medio-alti.  <br>Rosso: incidenti che restano a lungo inosservati.|
|Tempo medio di risoluzione (MTTR)|Lentezza nel chiudere gli incidenti|Verde: risoluzione rapida.  <br>Giallo: tempi medio-lunghi.  <br>Rosso: incidenti aperti a lungo, danni elevati.|
|Tempo di interruzione dopo un attacco|Impatto diretto sul business (downtime)|Verde: downtime minimo.  <br>Giallo: interruzioni significative ma gestibili.  <br>Rosso: lunghi blackout con perdite economiche gravi.|
|Corsi di formazione completati|Cultura e consapevolezza di sicurezza|Verde: alta partecipazione ai corsi.  <br>Giallo: formazione parziale.  <br>Rosso: scarsa o nulla formazione.|
|Quiz di attestazione superati|Efficacia reale della formazione|Verde: la maggioranza supera i test.  <br>Giallo: risultati medi, lacune da colmare.  <br>Rosso: test falliti, scarsa assimilazione delle policy.|

---

👉 Con questa tabella hai sia il **dettaglio concettuale** che la **logica di valutazione immediata** (verde/giallo/rosso), così puoi usarla al volo per schemi, riassunti o ripasso pre-esame.