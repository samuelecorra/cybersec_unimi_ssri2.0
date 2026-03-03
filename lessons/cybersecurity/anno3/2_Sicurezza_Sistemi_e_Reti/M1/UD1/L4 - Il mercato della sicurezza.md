## **Lezione 4: Il mercato della sicurezza**

### **1. Introduzione**

La sicurezza informatica non è solo una disciplina tecnica, ma anche un **settore economico** in continua espansione.  
Dati, vulnerabilità, exploit e persino sistemi compromessi hanno oggi un **valore di mercato concreto**, tanto nel mondo legale quanto in quello criminale.  
Comprendere la dimensione economica della sicurezza è fondamentale per interpretare le dinamiche globali del cybercrime e della difesa informatica.

---

### **2. Dati vs denaro fisico**

Un paragone utile per comprendere il valore delle informazioni digitali è quello tra **protezione dei dati** e **protezione del denaro fisico**:

|**Caratteristiche**|**Denaro in banca**|**Informazioni digitali**|
|---|---|---|
|**Dimensione e portabilità**|I depositi sono fisicamente grandi e richiedono strutture protette (casseforti, guardie, sistemi di allarme).|Le informazioni di valore sono leggere e facilmente trasportabili (anche in pochi byte).|
|**Contatto fisico**|Necessario: serve la presenza fisica per prelevare o depositare.|Non necessario: i dati possono essere rubati o trasferiti a distanza, senza contatto diretto.|
|**Valore del bene**|Sempre elevato.|Variabile: può essere bassissimo (dati banali) o altissimo (dati sensibili o riservati).|

Esempio emblematico: il **sistema di posta di Wilshire Associates**, che gestiva investimenti per **10 miliardi di dollari**, evidenzia come il furto di informazioni possa equivalere al furto diretto di denaro.

---

### **3. Il mercato delle vulnerabilità**

Le vulnerabilità software hanno oggi un **valore economico preciso**, a seconda del loro utilizzo:

#### **Opzione 1 – Bug bounty programs (mercato legale)**

Le grandi aziende pagano ricompense a chi segnala vulnerabilità in modo responsabile:

- **Google:** fino a $20.000 per bug critici.
    
- **Facebook:** fino a $20.000.
    
- **Microsoft:** fino a $150.000.
    
- **Pwn2Own Competition:** premi tra $10.000 e $15.000.
    
- **United Airlines:** biglietti aerei come ricompensa per segnalazioni di bug nei sistemi di volo.
    

#### **Opzione 2 – Vulnerability brokers (mercato grigio)**

Società intermediarie come **ZDI** e **iDefense** acquistano vulnerabilità da ricercatori e le rivendono a clienti selezionati, con cifre tra **$2.000 e $25.000**.

#### **Opzione 3 – Mercato nero (black market)**

Nei circuiti illegali, i prezzi sono molto più alti:

- exploit zero-day venduti fino a **$100.000–$250.000**;
    
- vulnerabilità critiche per iOS possono raggiungere anche **$500.000**.  
    (_Fonte: Forbes, 2012 – Andy Greenberg_)
    

---

### **4. Exploit come business**

Diverse aziende si sono specializzate nel **trovare, collezionare e rivendere exploit**, con modelli commerciali simili alle società di consulenza:

- **ReVuln**, **Vupen**, **Netragard**, **Exodus Intelligence**
    
- Prezzo medio per exploit: **$35.000–$160.000**
    
- Abbonamento annuale ai database privati: **$100.000**
    

Molti **governi e servizi segreti** sono acquirenti diretti.  
Secondo il _New York Times_ (luglio 2013), tra i maggiori compratori figurano **Israele, Gran Bretagna, Russia, India, Brasile, Corea del Nord** e vari Paesi del Medio Oriente e dell’area Asia-Pacifico.

Un caso noto in Italia è quello di **Hacking Team**, società milanese che forniva software di sorveglianza a forze governative e agenzie di intelligence di diversi Paesi.

---

### **5. Mercato dei dati rubati**

Nel **dark web**, le informazioni personali e finanziarie vengono vendute a prezzi standardizzati:

|**Tipo di dato**|**Prezzo medio**|
|---|---|
|Numero di carta di credito|$4–$15|
|Carta con dati del nastro magnetico|$12–$30|
|“Fullz” (profilo completo) – nome, indirizzo, data di nascita, IBAN, credenziali, SSN|$25–$40|
|Credenziali bancarie online (saldo $70k–$150k)|meno di $300|

Questi dati vengono poi usati per truffe, furti d’identità e frodi finanziarie.

---

### **6. Mercato per le vittime**

Esiste anche un mercato secondario che **monetizza direttamente i sistemi compromessi**:

- **Pay-per-install:** pagamento per ogni macchina infettata.
    
    - USA: $100–$150 per 1000 installazioni.
        
    - “Global mix”: $12–$15.
        

Utilizzi principali:

- invio di spam,
    
- attacchi DDoS,
    
- _click fraud_,
    
- hosting di siti fraudolenti.
    

**Botnet in affitto:**

- DDoS: **$10/ora** o **$150/settimana**;
    
- Spam: **$10 per 1.000.000 di email**.
    

**Servizi e strumenti in vendita:**

- Trojan di base: **$3–$10**;
    
- Rootkit per Windows: **$300**;
    
- Tool per spam via email o SMS: **$30–$50**;
    
- Setup e manutenzione botnet: **$200/mese**.
    

---

### **7. La sicurezza come campo di studio**

Il corso di sicurezza affronta tre aree principali:

1. **Attacco:** come gli avversari compromettono sistemi e reti.
    
2. **Difesa:** come prevenire, rilevare e rispondere agli attacchi.
    
3. **Progettazione:** come costruire architetture sicure e resilienti.
    

Il “peccato originale” di Internet è che **non fu progettata con la sicurezza in mente**.  
L’idea iniziale era una rete tra **utenti fidati**, priva di autenticazione e controllo.

---

### **8. Cattive notizie**

- Gli sviluppatori spesso privilegiano **prestazioni e usabilità** rispetto alla sicurezza.
    
- Il codice è pieno di **bug e vulnerabilità comuni**:
    
    - _buffer overflow_,
        
    - _cross-site scripting_ (XSS),
        
    - _injection_ e altri attacchi web.
        
- Le reti moderne sono **più aperte e complesse**, aumentando l’esposizione agli attacchi e la difficoltà di tracciarli.
    
- Molti attacchi non sono tecnici ma **sociali** (es. _phishing_, _social engineering_).
    

---

### **9. Buone notizie**

Nonostante le difficoltà, esistono **numerosi meccanismi di difesa**:

- **Crittografia:** strumento fondamentale ma con limiti pratici (chiavi, algoritmi, implementazioni).
    
- **Consapevolezza della sicurezza:** sempre più diffusa tra utenti e sviluppatori.
    
- **Usabilità e fattori economici:** oggi riconosciuti come elementi essenziali nella progettazione sicura.
    

La sicurezza informatica sta progressivamente entrando nel **focus dello sviluppo software moderno**.

---

### **10. Obiettivi del corso di sicurezza**

Il corso mira a formare studenti capaci di:

- comprendere i diversi aspetti della sicurezza e classificarli;
    
- utilizzare strumenti e protocolli reali (Wireshark, firewall, ecc.);
    
- diventare **“consumatori consapevoli” di sicurezza**, o idealmente **security manager**;
    
- applicare la teoria in **esercitazioni pratiche** di laboratorio.
    

La filosofia del corso è “**mettere le mani in pasta**”: imparare facendo.

---

### **11. Conclusione**

- La sicurezza informatica ha **un forte impatto economico**.
    
- Esiste un vero e proprio **mercato nero** di dati, exploit e attacchi.
    
- Gli sviluppatori stanno finalmente integrando la sicurezza nel ciclo di vita del software, ma le sfide restano enormi a causa di:
    
    - reti sempre più aperte,
        
    - software sempre più complessi,
        
    - bug inevitabili.
        

In sintesi:

> La sicurezza non è solo una questione tecnica, ma anche economica, sociale e strategica.  
> È un equilibrio tra difesa, costo e consapevolezza collettiva.

---

