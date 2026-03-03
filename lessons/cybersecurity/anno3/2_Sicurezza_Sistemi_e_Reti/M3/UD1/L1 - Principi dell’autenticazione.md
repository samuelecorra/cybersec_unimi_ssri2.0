Questo modulo introduce i **meccanismi fondamentali di sicurezza logica** che garantiscono che solo gli utenti autorizzati possano accedere alle risorse di un sistema informatico.  
Verranno analizzate le principali **tipologie di autenticazione** (basate su conoscenza, possesso o caratteristiche biometriche) e i **modelli di controllo degli accessi** utilizzati nei sistemi operativi e nelle reti.  
L’obiettivo è comprendere come si stabilisce l’**identità di un utente** e come il sistema decide **quali operazioni può eseguire**, secondo politiche e regole di sicurezza predefinite.


---

# **UD1 – Autenticazione**

Questa unità didattica approfondisce i **principi e i meccanismi dell’autenticazione**, ossia il processo con cui un sistema verifica l’identità di un utente o di un’entità che richiede l’accesso.  
Vengono esaminate le **principali tipologie di autenticazione**: basate su **password**, su **token fisici o digitali**, su **caratteristiche biometriche**, e sui più moderni **metodi multifattore (MFA)**.  
L’obiettivo è comprendere come ciascun approccio bilanci **sicurezza, usabilità e robustezza**, e quali siano i **punti di forza e di vulnerabilità** dei diversi metodi di verifica dell’identità.



---

## **Lezione 1: Principi dell’autenticazione**

### **1. Autenticazione utente**

L’**autenticazione** è il processo con cui un sistema determina se un utente, un’applicazione o un processo che agisce per conto di un utente **è realmente chi dichiara di essere**.

La tecnologia di autenticazione fornisce il **controllo degli accessi**, verificando se le **credenziali fornite** corrispondono a quelle **memorizzate nel database** dell’organizzazione.

#### **Funzione principale**

- Consentire l’accesso alle **risorse protette** solo a utenti o processi **autenticati**.
    
- Garantire che le reti e i sistemi restino sicuri, prevenendo accessi non autorizzati.
    

---

### **2. Autenticazione del messaggio**

L’**autenticazione dell’utente** si distingue dall’**autenticazione del messaggio**.

- **Autenticazione dell’utente:** verifica l’identità del soggetto che accede al sistema.
    
- **Autenticazione del messaggio:** verifica che un messaggio **non sia stato alterato** durante la trasmissione e che la **fonte sia autentica**.
    

Questa seconda forma di autenticazione è spesso realizzata tramite **funzioni di hash, firme digitali o codici MAC**, fondamentali nella **sicurezza delle comunicazioni**.

---

### **3. Principi di autenticazione**

L’autenticazione si fonda su tre concetti fondamentali: **identità digitale**, **prova dell’identità** e **autenticazione digitale**.

#### **Identità digitale**

È la **rappresentazione univoca di un soggetto** che partecipa a una transazione online.  
Si basa su **uno o più attributi** (es. nome, codice fiscale, credenziali digitali) che descrivono in modo unico l’individuo o il sistema.

#### **Prova dell’identità**

Stabilisce che un soggetto **è realmente chi afferma di essere**, con un **livello di certezza proporzionato al rischio**.  
Prevede la **raccolta, convalida e verifica** delle informazioni relative alla persona.

#### **Autenticazione digitale**

È il **processo di verifica della validità** di uno o più **autenticatori** (password, token, biometria, certificati, ecc.) associati a un’identità digitale.

Una **autenticazione riuscita** fornisce **garanzie basate sul rischio** che:

> il soggetto che accede oggi al servizio è lo stesso che vi ha avuto accesso in precedenza.

---

### **4. Requisiti di sicurezza secondo NIST SP 800-171**

Il documento **NIST SP 800-171** definisce i **requisiti di sicurezza** per i sistemi informativi, distinguendo tra requisiti **di base** e **derivati**.

#### **Requisiti di base**

1. **Identificare** gli utenti del sistema, i processi che agiscono per loro conto e i dispositivi coinvolti.
    
2. **Autenticare** (verificare) le identità di tali entità prima di concedere l’accesso alle risorse organizzative.
    

#### **Requisiti derivati**

3. Impiegare **autenticazione multifattore (MFA)** per l’accesso locale o remoto agli account privilegiati e agli account non privilegiati.
    
4. Utilizzare **meccanismi resistenti a replay** per l’accesso in rete.
    
5. Impedire il **riutilizzo degli identificatori** per un periodo definito.
    
6. **Disabilitare gli account inattivi** dopo un certo intervallo di tempo.
    
7. Applicare una **complessità minima delle password** e imporre la modifica dei caratteri nelle nuove password.
    
8. **Proibire il riutilizzo delle password** per un numero specificato di generazioni.
    
9. Consentire **password temporanee** solo per accessi iniziali, con obbligo di modifica immediata.
    
10. **Proteggere le password** durante la memorizzazione e la trasmissione tramite crittografia.
    
11. **Oscurare il feedback** durante l’inserimento delle credenziali (es. mascherare i caratteri digitati).
    

---

### **5. Modello architetturale di autenticazione elettronica (NIST SP 800-63)**

Il documento **NIST SP 800-63** definisce il **modello architetturale generale** dell’autenticazione elettronica.

Prevede un flusso composto da:

1. **Rivendicazione dell’identità** da parte dell’utente (es. inserimento delle credenziali).
    
2. **Verifica** dell’autenticatore da parte del sistema (es. confronto con il database).
    
3. **Decisione di accesso** basata sull’esito della verifica.
    
4. **Assegnazione di una sessione autenticata**, utilizzata per l’accesso alle risorse.
    

Questo modello viene adottato come riferimento in molti standard di sicurezza internazionali.

---

### **6. Sintesi finale**

- L’**autenticazione** è una componente **essenziale della sicurezza dei sistemi informatici**.
    
- Si basa su **principi chiari**: identità digitale, prova dell’identità e autenticazione digitale.
    
- Gli standard **NIST SP 800-171** e **SP 800-63** forniscono **linee guida di riferimento** per la progettazione e la verifica dei meccanismi di autenticazione.
    
- Una corretta implementazione garantisce che **solo utenti legittimi** possano accedere ai servizi e alle informazioni sensibili.
    

> **In sintesi:** l’autenticazione è la prima barriera contro l’accesso non autorizzato — il fondamento su cui si costruisce l’intera sicurezza del sistema.