In questa lezione introdurremo i paradigmi o modelli della Cyber Security dal punto di vista dei fattori critici e delle priorità nelle diverse aree applicative delle aziende.

---
# I fattori critici nel modello ISA-95

Nel modello ISA-95 (standard internazionale per integrare IT e OT nelle aziende di produzione), le priorità di sicurezza cambiano a seconda del livello in cui ci troviamo:

- **Livello 4 (Business/IT):** il valore è nei **dati** e nelle **informazioni**.
    
- **Livello 3 (Operations & Control):** la priorità è coordinare e ottimizzare la produzione.
    
- **Livelli 2-1-0 (OT fisico):** qui si gestiscono direttamente **macchine, linee produttive, attuatori**; la criticità è il **controllo fisico** del processo e la **safety** (protezione di persone e impianti).
    

---

### Analisi del grafico

![Pasted image 20250913154820.png](../../imgs/Pasted%20image%2020250913154820.png)

Il grafico mostra due cose:

1. **Stratificazione dei livelli ISA-95:**
    
    - **Level 4 – Business Planning & Logistics:** riguarda pianificazione della produzione, logistica, gestione operativa. Qui il rischio principale è legato a **dati** e **informazioni** (es. perdita di database, manipolazione di ordini).
        
    - **Level 3 – Manufacturing Operations & Control:** riguarda il coordinamento della produzione in fabbrica (dispatching, scheduling, assurance di affidabilità).
        
    - **Levels 2, 1, 0 – Controllo di processo:** suddivisi in:
        
        - **Batch Control** → produzione a lotti (es. farmaceutica, alimentare).
            
        - **Continuous Control** → processi continui (es. raffinerie, chimica).
            
        - **Discrete Control** → produzione discreta (es. automotive, elettronica).
            
2. **Freccia IT/OT a destra:**
    
    - **IT (verde chiaro, in alto):** la criticità sta nei **dati/informazioni**.
        
    - **OT (verde scuro, in basso):** la criticità sta nel **controllo del processo** e nella **safety**.
        

**Interpretazione:**  
Scendendo dal livello 4 al livello 0, la priorità della cyber security si **sposta progressivamente**: dall’attenzione a **riservatezza e integrità dei dati** (IT) alla priorità su **disponibilità e sicurezza fisica** (OT).

---

### Spiegazione in stile Feynman

Immagina un’azienda come una torre a piani:

- **All’ultimo piano (Level 4)** ci sono gli uffici: qui conta che i dati dei clienti, i piani di produzione e i bilanci siano segreti, corretti e disponibili.
    
- **Al piano intermedio (Level 3)** ci sono i manager di fabbrica: qui conta che il coordinamento tra linee e turni non si blocchi.
    
- **Ai piani bassi (Level 2-0)** ci sono le macchine: se qui sbagli, non perdi un file Excel ma **blocchi un macchinario** o metti a rischio la sicurezza delle persone.
    

Quindi: più scendi nei livelli, più la **cybersecurity** deve guardare non ai dati ma al **controllo fisico** e alla **safety**.


---
# Paradigma RID (CIA) nell’IT

### Contenuto della slide

Il modello classico di riferimento in ambito **IT** è la triade **CIA (Riservatezza – Integrità – Disponibilità)**, che stabilisce le priorità della sicurezza informatica **in ordine decrescente di importanza**:

1. **Riservatezza (Confidentiality)**
    
2. **Integrità (Integrity)**
    
3. **Disponibilità (Availability)**
    

---

### Spiegazione dei tre fattori

**1. Riservatezza (Confidentiality)**

- Scopo: impedire accessi non autorizzati alle informazioni sensibili.
    
- Pratica comune: **classificazione dei dati** in base al danno potenziale (es. “pubblico, riservato, segreto”).
    
- Misure: applicare controlli proporzionati (es. cifratura per dati critici, autenticazione semplice per dati a basso rischio).
    
- Idea chiave: la **Riservatezza** è l’aspetto **più importante** in IT → proteggere segreti aziendali, dati finanziari, informazioni personali.
    

---

**2. Integrità (Integrity)**

- Significato: i dati devono mantenere **coerenza, accuratezza e affidabilità** lungo tutto il ciclo di vita.
    
- Problema tipico: i dati possono venire corrotti durante i trasferimenti o alterati da utenti non autorizzati.
    
- Soluzione: controlli di integrità (hash, firme digitali, backup verificati).
    
- Nota: perdita di integrità = dati falsati, quindi decisioni sbagliate.
    

---

**3. Disponibilità (Availability)**

- Scopo: i dati devono essere **prontamente accessibili** agli utenti autorizzati quando servono.
    
- Richiede:
    
    - corretta manutenzione hardware (server, storage, rete),
        
    - gestione delle infrastrutture tecniche (alimentazione, ridondanza),
        
    - sistemi applicativi sempre aggiornati.
        
- Esempio: un server down o un ransomware che blocca l’accesso → perdita di disponibilità.
    

---

### Analisi critica

- In **ambito IT** la logica è: “prima proteggi i **segreti**, poi la loro **correttezza**, e solo alla fine l’**accesso puntuale**.”
    
- Questo ordine riflette il fatto che una fuga di dati può avere impatti **legali, reputazionali ed economici gravissimi**.
    
- Disponibilità è importante, ma in IT i tempi di fermo sono spesso **più tollerabili** rispetto al rischio di violazione della riservatezza.
    

---

### Feynman-style

Immagina il database clienti di una banca:

- **Confidentiality:** se qualcuno ruba i dati dei conti correnti → disastro.
    
- **Integrity:** se i dati vengono corrotti (saldo che cambia da 10.000 a 100) → caos.
    
- **Availability:** se il sistema è offline per 2 ore → disagio, ma non è grave come i due casi precedenti.


---
# Evoluzione del modello RID (CIA)

Con lo sviluppo delle tecnologie digitali e delle normative (identità digitale, firme elettroniche, sistemi di audit e logging), il modello classico **CIA** si è **esteso a cinque fattori**:

1. **Authentication**
    
2. **Availability**
    
3. **Integrity**
    
4. **Confidentiality**
    
5. **Non-Repudiation**
    
![Pasted image 20250913155056.png](../../imgs/Pasted%20image%2020250913155056.png)
---

### Authentication

- Definizione: capacità di **dimostrare in modo certo l’identità** di un utente o di un’applicazione.
    
- Esempi: password, smart card, token OTP, certificati digitali, sistemi biometrici.
    
- Importanza: senza autenticazione forte, non puoi essere sicuro che i dati siano protetti, perché non sai **chi** realmente accede.
    

---

### Non-Repudiation (Non-ripudio)

- Definizione: garanzia che chi ha usato credenziali **non possa negare** successivamente di aver compiuto certe azioni (es. modifiche a dati, firma di documenti).
    
- Esempi: firme digitali qualificate, log firmati, blockchain.
    
- Funzione: assicura **responsabilità** e **accountability** degli utenti → nessuno può “scaricare la colpa” su altri.
    

---

### Impatto sul modello

- Con **Authentication** e **Non-Repudiation**, il paradigma si rafforza e cambia ordine di priorità.
    
- Ora non basta proteggere i segreti (Confidentiality), bisogna garantire anche:
    
    - **chi** sta interagendo,
        
    - **che non possa rinnegare** ciò che ha fatto.
        
- **Conseguenza diretta:** la **Riservatezza** resta importante, ma **scende di priorità relativa** → avere controllo su identità e responsabilità è oggi ancora più critico.
    

---

### Analisi grafico

- A sinistra: la nuova classifica dei fattori → **Authentication** al primo posto, seguita da **Availability** e **Integrity**.
    
- Confidentiality viene spostata al **quarto posto**.
    
- Non-Repudiation entra come quinto elemento essenziale per tracciabilità e responsabilità.
    

---

### Spiegazione in stile Feynman

Immagina di firmare un contratto digitale:

- **Authentication:** serve a dimostrare che sei davvero tu a firmare.
    
- **Non-repudiation:** una volta firmato, non puoi dire “non sono stato io”.
    
- Senza queste due garanzie, anche se i dati fossero cifrati (Confidentiality) o corretti (Integrity), mancherebbe la certezza su “chi” li ha usati.


---
# Paradigma Operazionale (OT)

### Contenuto della slide

Quando ci spostiamo dal mondo **IT** a quello **OT** (rete industriale con SCADA, PLC, sensori, attuatori), il paradigma **RID (CIA)** cambia radicalmente:

- **Disponibilità** diventa il fattore **primario**.
    
- **Integrità** conta solo se assicura il corretto funzionamento del **controllo del processo**.
    
- **Riservatezza** perde quasi completamente significato (importante solo in casi di proprietà intellettuale, es. ricette industriali).
    

Inoltre, emergono **nuove priorità** specifiche dell’OT:

1. **Safety** → protezione fisica di persone, impianti, ambiente.
    
2. **Reliability** → affidabilità operativa dei sistemi (funzionamento continuo e stabile).
    
3. **Product/Service Impacted** → garanzia che il prodotto/servizio finale non venga compromesso.
    

---

### Spiegazione concettuale

- In **IT** la triade CIA è al centro; in **OT** invece **perde senso** se applicata rigidamente.
    
- L’obiettivo primario in OT non è proteggere i segreti, ma **garantire che il processo industriale funzioni senza interruzioni e senza rischi fisici**.
    
- Un attacco che **ferma una linea produttiva** o fa sbagliare un macchinario è molto più critico di un furto di dati.
    

---

### Analisi del grafico

- A sinistra: la triade CIA “rimappata” per OT → **Availability > Integrity > Confidentiality**.
    
- A destra: i tre fattori reali che guidano la cybersecurity in ambito OT → **Safety, Reliability, Product/Service Impacted**.
    
- Al centro: la freccia mostra il passaggio da un modello “dati-centrico” (CIA) a un modello “processo-centrico” (Safety + Reliability).
    

---

### Feynman-style

Immagina un impianto chimico:

- Se un hacker ruba la formula (Confidentiality), è un problema commerciale, ma non immediato.
    
- Se manomette i dati di un sensore (Integrity), puoi perdere il controllo e rischiare una reazione incontrollata.
    
- Se blocca il PLC e ferma le pompe (Availability), la produzione si arresta → perdite economiche e rischio di incidente.
    
- Ma soprattutto: se il guasto genera **un’esplosione o una fuoriuscita tossica (Safety)**, i danni vanno ben oltre i dati → si parla di vite umane e ambiente.
    

---

### Sintesi chiave

- In OT, la **Disponibilità** è più critica della Riservatezza.
    
- La **Safety** diventa la priorità assoluta.
    
- L’**affidabilità del sistema** e l’**impatto sul servizio/prodotto** contano più della protezione dei dati.


---
# Differenze IT/OT in breve

La tabella mette a confronto i due mondi (IT e OT) evidenziando come cambiano **fattori critici, rischi, disponibilità, conseguenze, tempi e software**.

![Pasted image 20250913155406.png](../../imgs/Pasted%20image%2020250913155406.png)

---

### Analisi fattore per fattore

**1. Rischio**

- **IT:** rischio principale = **perdita di dati e informazioni** (file, documenti, transazioni).
    
    - Impatti: ritardi nei processi, danni economici, perdita di fiducia (risorse, tempo, soldi).
        
- **OT:** rischio principale = **integrità fisica di persone e ambiente**.
    
    - Incidenti sul lavoro, rischio ambientale/territoriale.
        
    - Conservazione di impianti, macchinari e continuità produttiva.
        
    - Qui il danno non è solo economico, ma **fisico e ambientale**.
        

---

**2. Disponibilità / Accessibilità**

- **IT:** attività soprattutto in orario di ufficio; fermate e riavvii (re-boot) gestibili per manutenzione.
    
- **OT:** sistemi quasi sempre attivi **24/7/365**.
    
    - Produzione continua o per turni.
        
    - **Fermare un sistema = fermare la produzione**.
        
    - La disponibilità è vitale: downtime = perdite enormi.
        

---

**3. Conseguenze incidenti**

- **IT:** conseguenza tipica = perdita di dati.
    
- **OT:** conseguenze dipendono dal processo controllato:
    
    - ritardi o errori negli allarmi, nei sensori, negli attuatori;
        
    - potenziale rischio per persone, ambiente e impianti.
        
- In OT, ogni nuova funzione deve essere verificata per non introdurre **nuove vulnerabilità**.
    

---

**4. Tempi di risposta**

- **IT:** throughput e prestazioni di rete **prevedibili**; ritardi spesso non critici.
    
- **OT:** ritardi **inaccettabili**.
    
    - Allarmi, sensori, attuatori richiedono **real-time** e **determinismo**.
        
    - Determinismo = stessi input producono stessi output entro tempi garantiti.
        
- Esempio: se un sensore di pressione segnala un problema con 10 secondi di ritardo, può esplodere un reattore.
    

---

**5. Software**

- **IT:** sistemi operativi **standard** (Windows, Linux, macOS).
    
- **OT:** OS **non standard** o **versioni standard adattate** con vincoli particolari.
    
    - Spesso software legacy, non aggiornabile facilmente.
        
    - Hardening specifico per tempo reale e cicli di vita molto lunghi (impianti durano 10-20 anni).
        

---

### Feynman-style

Immagina due scenari:

- **IT:** se ti si blocca l’ERP o l’e-mail, perdi ordini o clienti → danno economico.
    
- **OT:** se ti si blocca un PLC che controlla una turbina, rischi un blackout o un’esplosione → danno fisico + ambientale + economico.
    

Quindi:

- in IT la priorità è **proteggere i dati**;
    
- in OT la priorità è **proteggere la vita, gli impianti e la continuità produttiva**.
    

---

# Riepilogo finale della lezione

- **IT (Livello 4):** paradigma CIA (Confidentiality > Integrity > Availability).
    
- **OT (Livelli 2-0):** paradigma Safety + Reliability + Continuity (Availability > Integrity > Confidentiality).
    
- Con l’evoluzione digitale, il modello si estende ad **Authentication** e **Non-Repudiation**.
    
- Le differenze principali IT vs OT:
    
    - **Rischi:** dati vs persone/impianti.
        
    - **Disponibilità:** tollerabile vs critica.
        
    - **Incidenti:** perdita di dati vs danni fisici.
        
    - **Tempi di risposta:** tolleranza vs real-time.
        
    - **Software:** standard vs non standard/legacy.


---
