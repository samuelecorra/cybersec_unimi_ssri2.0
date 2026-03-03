## **Lezione 3: Minacce, risorse e attacchi**

### **1. Introduzione**

La sicurezza informatica si fonda sull’identificazione e sulla gestione di tre concetti chiave:

- **Minacce (threats)**
    
- **Attacchi (attacks)**
    
- **Risorse o asset (resources)**
    

Questi elementi definiscono il **campo d’azione della sicurezza** e permettono di comprendere come proteggere efficacemente un sistema informatico o di rete.

---

### **2. Architettura della sicurezza (ITU-T X.800)**

La **ITU-T Recommendation X.800 (1991)** definisce la **Security Architecture for OSI**, fornendo un modello generale per affrontare il problema della sicurezza in un sistema aperto.

Secondo la X.800, la sicurezza può essere analizzata attraverso tre componenti fondamentali:

1. **Attacchi alla sicurezza**  
    → Qualsiasi azione che comprometta la sicurezza delle informazioni di un’organizzazione.
    
2. **Meccanismi di sicurezza**  
    → Processi o dispositivi progettati per rilevare, prevenire o recuperare da un attacco.
    
3. **Servizi di sicurezza**  
    → Insiemi di meccanismi che operano per contrastare gli attacchi e far rispettare le politiche di sicurezza.
    

---

### **3. Definizioni secondo RFC 4949**

La **RFC 4949 – Internet Security Glossary (2007)** fornisce terminologia standard per il dominio della sicurezza informatica.

- **Minaccia (Threat):**  
    possibilità di una violazione della sicurezza.
    
- **Attacco (Attack):**  
    tentativo concreto di violare la politica di sicurezza, eludendo uno o più servizi di protezione.
    

---

### **4. Modello di un sistema informatico**

Un **sistema informatico** può essere visto come un insieme di componenti (hardware, software, persone, rete) che elaborano e scambiano informazioni.  
Il suo obiettivo primario è **trasmettere informazioni in modo corretto e sicuro**.

Un **attacco** avviene quando un soggetto esterno o interno altera o osserva queste informazioni in modo non autorizzato.

---

### **5. Tipologie di meccanismi di sicurezza**

#### **Meccanismi specifici**

Implementati a un livello particolare dell’architettura OSI (es. crittografia, controllo accessi).  
Servono a fornire servizi di sicurezza mirati.

#### **Meccanismi pervasivi**

Agiscono trasversalmente su più livelli del sistema:

- _Trusted functionality_
    
- _Security label_
    
- _Event detection_
    
- _Security audit trail_
    
- _Security recovery_
    

I **servizi di sicurezza** utilizzano uno o più di questi meccanismi per applicare la **politica di sicurezza** e contrastare gli attacchi.

---

### **6. Attacchi passivi**

Gli **attacchi passivi** mirano a **osservare o intercettare informazioni** senza alterarle.  
L’obiettivo è ottenere conoscenze sensibili sul sistema o sulle comunicazioni.

Esempi:

- **Accesso al contenuto dei messaggi** (eavesdropping).
    
- **Analisi del traffico di rete:** la frequenza e la lunghezza dei messaggi possono rivelare la natura delle comunicazioni.
    

→ Le **tecniche di cifratura e tunneling** contrastano questo tipo di minaccia.

---

### **7. Attacchi attivi**

Gli **attacchi attivi** modificano o interrompono il flusso delle informazioni.  
Possono:

- alterare i dati (es. cambiare “€10” in “€100”),
    
- creare flussi falsi (es. replay o impersonificazione),
    
- bloccare l’accesso a un servizio (attacco **DoS**).
    

Esempi:

- **Masquerade / Spoofing** – fingere di essere un altro utente.
    
- **Replay attack** – reinvio di messaggi validi per ottenere vantaggi.
    
- **Denial of Service** – rendere un servizio inaccessibile agli utenti autorizzati.
    

---

### **8. Esempio: controllo d’accesso ai file**

**Politica di sicurezza:**  
Un utente non deve poter leggere i file di un altro utente.

**Meccanismo di sicurezza:**  
Controlli sui permessi del filesystem, ad esempio:

```bash
-rw-r--r--  pippo staff  esame.pdf   ← accesso consentito a tutti
-rw-------  pippo staff  esame.pdf   ← accesso riservato
```

L’impostazione corretta dei permessi garantisce il rispetto della politica di riservatezza.

---

### **9. Relazioni tra meccanismi e servizi**

I **meccanismi di sicurezza** sono gli strumenti pratici (autenticazione, cifratura, logging).  
I **servizi di sicurezza** sono gli obiettivi che si vogliono raggiungere (riservatezza, integrità, disponibilità).

Ogni servizio si basa su uno o più meccanismi adeguatamente coordinati.

---

### **10. Classificazione delle minacce secondo Shirey (RFC 4949)**

Shirey suddivide le minacce in **quattro classi principali**:

|**Categoria**|**Descrizione**|**Proprietà minacciata**|
|---|---|---|
|**Disclosure (Divulgazione)**|Accesso non autorizzato a informazioni riservate|Riservatezza|
|**Deception (Inganno)**|Accettazione di dati falsi o alterati|Integrità|
|**Disruption (Interruzione)**|Interruzione del corretto funzionamento di un sistema|Disponibilità / Integrità|
|**Usurpation (Usurpazione)**|Controllo non autorizzato di risorse o processi|Integrità / Disponibilità|

---

### **11. Tipologie di minaccia e attacco (RFC 4949)**

#### **A. Disclosure – Divulgazione non autorizzata**

- **Exposure:** perdita di dati sensibili per errore umano o tecnico.
    
- **Interception:** intercettazione di comunicazioni (sniffing).
    
- **Inference:** deduzione di informazioni tramite osservazione del traffico.
    
- **Intrusion:** accesso diretto e non autorizzato ai sistemi.
    

#### **B. Deception – Inganno**

- **Masquerade:** un attaccante si finge un utente autorizzato (es. trojan).
    
- **Falsification:** alterazione o sostituzione di dati legittimi.
    
- **Repudiation:** negazione di aver inviato o ricevuto dati (problema di non ripudio).
    

#### **C. Disruption – Interruzione**

- **Incapacitation:** distruzione o danneggiamento fisico dell’hardware.
    
- **Corruption:** corruzione di risorse o servizi (es. modifica di file).
    
- **Obstruction:** interferenza con la comunicazione (es. blocco di rete o jamming).
    

#### **D. Usurpation – Usurpazione**

- **Misappropriation:** sottrazione di risorse o servizi (es. uso non autorizzato di banda o CPU).
    
- **Misuse:** uso improprio del sistema, ad esempio tramite malware o accessi abusivi.
    

---

### **12. Esempi pratici di minacce**

|**Tipo di minaccia**|**Descrizione**|**Proprietà colpita**|
|---|---|---|
|**Snooping**|Intercettazione non autorizzata di comunicazioni o file|Riservatezza|
|**Modifica / Alterazione**|Cambiamento non autorizzato dei dati|Integrità|
|**Man-in-the-Middle**|Intercettazione attiva con modifica dei messaggi|Integrità / Autenticità|
|**Spoofing / Masquerade**|Un’entità si finge un’altra (es. sito phishing)|Integrità|
|**Repudiation**|L’utente nega di aver inviato o ricevuto dati|Integrità / Non ripudio|
|**Delay**|Ritardo temporaneo nell’erogazione del servizio|Disponibilità|
|**Denial of Service (DoS)**|Blocco prolungato del servizio|Disponibilità|

---

### **13. Risorse informatiche e minacce associate**

|**Risorsa (asset)**|**Esempio di minaccia**|**Tipo di attacco**|
|---|---|---|
|Hardware|Furto o danneggiamento fisico|Interruzione|
|Software|Corruzione o modifica di codice|Usurpazione|
|Dati|Furto, cancellazione o manipolazione|Disclosure / Deception|
|Rete|Intercettazione o jamming|Intercettazione / Disruption|
|Utenti|Phishing, social engineering|Inganno / Usurpazione|

---

### **14. Conclusione**

Le **minacce**, gli **attacchi** e le **risorse** rappresentano i pilastri dell’analisi della sicurezza.  
Per progettare sistemi affidabili è necessario:

1. **Identificare le risorse critiche**,
    
2. **Stimare le minacce rilevanti**,
    
3. **Applicare meccanismi di difesa adeguati**,
    
4. **Monitorare costantemente** i rischi.
    

> La sicurezza non consiste nell’eliminare le minacce, ma nel **gestirle in modo consapevole e proporzionato al valore delle risorse da proteggere**.