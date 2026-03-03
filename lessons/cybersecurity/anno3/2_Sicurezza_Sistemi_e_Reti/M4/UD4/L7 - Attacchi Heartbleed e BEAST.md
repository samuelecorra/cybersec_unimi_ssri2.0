## **Lezione 7: Attacchi Heartbleed e BEAST**

### **1. Introduzione**

Anche dopo vent’anni di sviluppo, la sicurezza di SSL/TLS è stata minacciata da vulnerabilità **non dovute agli algoritmi crittografici**, ma a **errori di implementazione**.  
Due dei casi più celebri sono:

- **Heartbleed (2014)** – un bug nel codice di OpenSSL che permetteva di leggere la memoria del server;
    
- **BEAST (2011)** – un attacco pratico contro la modalità di cifratura CBC in TLS 1.0.
    

Entrambi gli attacchi dimostrano come anche **un piccolo errore di programmazione** o una **scelta di progettazione errata** possa compromettere l’intera sicurezza del canale cifrato.

---

## **Parte I – L’attacco Heartbleed**

### **2. Descrizione generale**

**Heartbleed** è una vulnerabilità nella libreria **OpenSSL**, usata per implementare SSL e TLS.  
Il problema riguarda un’estensione opzionale del protocollo chiamata **Heartbeat**, introdotta per mantenere viva la connessione tra client e server.

#### **Funzionamento dell’estensione Heartbeat**

- Il client invia un piccolo **pacchetto di keep-alive** contenente:
    
    1. un **messaggio di testo** (ad esempio “ciao”),
        
    2. e la **lunghezza dichiarata** del messaggio.
        
- Il server risponde ripetendo lo stesso messaggio.
    

#### **La vulnerabilità**

Il bug stava nel fatto che **OpenSSL non verificava** che la lunghezza dichiarata corrispondesse alla reale dimensione del messaggio.  
Un client malevolo poteva dichiarare una lunghezza arbitraria, ad esempio **64 KB**, anche se il messaggio conteneva solo pochi byte.

Il server, fidandosi, copiava **64 KB dalla propria memoria interna** e li inviava come risposta al client.  
Questo blocco di memoria poteva contenere:

- password,
    
- cookie di sessione,
    
- chiavi private TLS,
    
- o altre informazioni sensibili in chiaro.
    

> In sintesi: Heartbleed consentiva di “**sanguinare la memoria**” del server, da cui il nome.

---

### **3. Cronologia dell’attacco**

|Evento|Data|
|---|---|
|Introduzione del bug|Dicembre 2011|
|Versione vulnerabile di OpenSSL rilasciata|14 marzo 2012 (v1.0.1)|
|Vulnerabilità resa pubblica|1 aprile 2014|
|Patch ufficiale (OpenSSL 1.0.1g)|7 aprile 2014|
|Primo exploit documentato|8 aprile 2014|

Le versioni **vulnerabili** erano:

- **OpenSSL 1.0.1 – 1.0.1f** (incluse),
    
- **NON vulnerabili:** 0.9.8, 1.0.0 e 1.0.1g (o successive).
    

> Il bug è rimasto “in the wild” per oltre **due anni**, esponendo milioni di server HTTPS, VPN, e-mail e servizi SSH basati su OpenSSL.

---

### **4. Contromisure**

1. **Aggiornare OpenSSL** alla versione **1.0.1g** o superiore.
    
2. **Ricompilare** OpenSSL disattivando completamente l’estensione vulnerabile:
    
    ```
    -DOPENSSL_NO_HEARTBEATS
    ```
    
3. **Rigenerare** tutte le chiavi e certificati privati (potenzialmente esposti).
    
4. **Revocare i certificati compromessi**, poiché Heartbleed poteva rivelare anche le chiavi private del server.
    

> Heartbleed non era un errore di crittografia, ma di **validazione dei parametri di input**, un classico caso di “fiducia malriposta”.

---

## **Parte II – L’attacco BEAST**

### **5. Introduzione al BEAST Attack**

**BEAST** (Browser Exploit Against SSL/TLS) è un attacco pubblicato nel 2011 da **Thai Duong** e **Juliano Rizzo**.  
Colpisce le sessioni SSL/TLS 1.0 sfruttando debolezze nella modalità di cifratura **CBC (Cipher Block Chaining)**.

L’obiettivo dell’attacco è ottenere dati sensibili (come **token di autenticazione** o **cookie di sessione**) da connessioni HTTPS intercettate da un **man-in-the-middle**.

---

### **6. Contesto tecnico: la cifratura a blocchi in CBC**

#### **a. Meccanismo di base**

In CBC, ogni blocco di testo in chiaro $P_i$ viene combinato (tramite XOR) con il blocco cifrato precedente $C_{i-1}$, e poi cifrato:

$$  
C_i = E_K(P_i \oplus C_{i-1})  
$$

Per il **primo blocco**, si usa un **vettore di inizializzazione (IV)** casuale:

$$  
C_1 = E_K(P_1 \oplus IV)  
$$

#### **b. Problema in TLS 1.0**

TLS 1.0 non generava un IV casuale per ogni record:  
usava **l’ultimo blocco cifrato del messaggio precedente** come IV per il successivo.

Questa scelta creava una **dipendenza diretta** tra i messaggi successivi, rendendo prevedibile il prossimo IV e aprendo la strada all’attacco BEAST.

---

### **7. Il principio dell’attacco**

#### **a. Requisiti**

- L’attaccante deve essere **in grado di osservare** e **iniettare** pacchetti nel canale (posizione MITM).
    
- Deve conoscere **parte della struttura del messaggio** (es. header HTTP prevedibili).
    
- L’obiettivo è recuperare **piccoli segmenti di testo in chiaro**, come i cookie.
    

#### **b. Tecnica**

L’attaccante può:

1. Iniettare blocchi di testo controllato nel flusso HTTPS.
    
2. Confrontare il blocco cifrato ottenuto con quello reale.
    
3. Se coincidono, significa che il blocco in chiaro è stato indovinato correttamente.
    
4. In caso contrario, ripete con un valore differente.
    

#### **c. Formula semplificata**

Poiché TLS 1.0 usa XOR, l’attaccante conosce:

- $C_{i-1}$ (dal messaggio precedente),
    
- e il vecchio IV (che è proprio $C_{i-1}$).
    

Può quindi costruire un blocco fittizio $P'$ tale che:

$$  
E_K(P' \oplus C_{i-1}) = C_i  
$$

Provando vari valori per $P'$, l’attaccante indovina progressivamente il testo in chiaro.

---

### **8. Ottimizzazione dell’attacco (Blockwise Chosen Boundary Attack)**

Nel 2011, Duong e Rizzo riuscirono a **ridurre drasticamente la complessità** dell’attacco:

- invece di forzare un intero blocco (es. 64 bit),
    
- riuscirono a forzare **un solo byte alla volta**.
    

Questo rese l’attacco **realistico** e sfruttabile in pratica.

#### **Esempio**

Per rubare un cookie di sessione di 10 caratteri:

- si effettuano circa **100 tentativi totali**,
    
- spostando progressivamente il “confine” dei blocchi fino a isolare ogni byte incognito.
    

#### **Tecnica**

Il browser viene indotto a:

- inviare richieste HTTP con contenuto controllato,
    
- iniettare dati predeterminati per modificare i limiti dei blocchi di cifratura.
    

Lo script malevolo, spesso realizzato in **JavaScript** o **Java applet**, lavora in background mentre l’utente visita siti sicuri.

---

### **9. Scenario pratico**

Un possibile scenario di exploit BEAST:

1. L’utente visita un sito malevolo che esegue lo script di attacco.
    
2. Senza chiudere la pagina, apre una sessione bancaria (HTTPS).
    
3. Entrambi i siti restano aperti per alcuni minuti.
    
4. Lo script di attacco, agendo come **man-in-the-middle**, inizia a forzare byte del cookie di sessione.
    
5. Dopo vari tentativi, riesce a ricostruire il cookie completo e **impersonare l’utente**.
    

> L’attacco era complesso ma tecnicamente possibile, e dimostrava che TLS 1.0 non era più adeguato alle minacce moderne.

---

### **10. Mitigazioni**

1. **Aggiornamento del protocollo:**
    
    - Passare a **TLS 1.1** o **TLS 1.2**, dove l’IV viene generato casualmente ad ogni record.
        
2. **Configurazioni di emergenza (mitigazioni temporanee):**
    
    - Disabilitare i cifrari **CBC** in favore di modalità più sicure come **RC4** (oggi però anch’essa deprecata).
        
    - Abilitare la protezione **BEAST mitigation** nei browser.
        
3. **Situazione attuale:**
    
    - Dal 2019, quasi tutti i siti supportano TLS 1.2 o 1.3.
        
    - Browser moderni (Chrome, Firefox, Safari, Edge) **non sono più vulnerabili**.
        

---

### **11. Lezioni apprese**

- Le vulnerabilità **teoriche** possono diventare **attacchi pratici** nel tempo.
    
- Le implementazioni reali spesso **ritardano l’adozione** delle nuove versioni dei protocolli.
    
- Quando BEAST fu reso pubblico nel 2011, la vulnerabilità era già **risolta dal 2006** in TLS 1.1, ma pochi sistemi l’avevano implementata.
    
- Anche implementazioni **leggermente imperfette** di schemi matematicamente solidi possono annullare la sicurezza complessiva.
    

#### **Buone pratiche**

- Aggiornare tempestivamente i protocolli (TLS 1.3).
    
- Effettuare **scansioni periodiche di vulnerabilità SSL/TLS**.
    
- Evitare protocolli o algoritmi **deprecati** (SSLv3, TLS 1.0, RC4, DES).
    

> La sicurezza non è solo un algoritmo robusto, ma anche la capacità di **mantenere il sistema aggiornato e coerente** con gli standard moderni.

---

### **12. Conclusione generale**

Heartbleed e BEAST hanno segnato due epoche diverse della sicurezza TLS:

- **Heartbleed** ha mostrato quanto un _bug di implementazione_ possa violare la riservatezza di milioni di utenti.
    
- **BEAST** ha dimostrato che un _protocollo tecnicamente sicuro_ può diventare vulnerabile se non aggiornato.
    

Entrambi gli attacchi hanno spinto la comunità verso:

- **TLS 1.3**,
    
- **algoritmi AEAD**,
    
- e una più rigorosa cultura di aggiornamento della sicurezza.