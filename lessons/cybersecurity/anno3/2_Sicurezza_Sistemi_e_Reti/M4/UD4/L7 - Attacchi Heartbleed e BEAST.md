## **Lezione 7: Attacchi Heartbleed e BEAST**

### **1. Introduzione**

Anche dopo vent’anni di sviluppo, la sicurezza di SSL/TLS è stata minacciata da vulnerabilità **non dovute agli algoritmi crittografici**, ma a **errori di implementazione**.  
Due dei casi più celebri sono:

- **Heartbleed (2014)** – un bug nel codice di OpenSSL che permetteva di leggere la memoria del server;
    
- **BEAST (2011)** – un attacco pratico contro la modalità di cifratura CBC in TLS 1.0.
    

Entrambi gli attacchi dimostrano come anche **un piccolo errore di programmazione** o una **scelta di progettazione errata** possa compromettere l’intera sicurezza del canale cifrato.

La differenza concettuale è importante:

- Heartbleed nasce da un **errore di implementazione** in una estensione di OpenSSL;

- BEAST nasce da una **debolezza pratica nell’uso della modalità CBC** nelle versioni TLS fino a TLS 1.0.

> 📌 Nessuno dei due attacchi “rompe” direttamente la matematica di AES o della crittografia moderna: sfruttano rispettivamente un controllo mancante nel codice e un dettaglio pericoloso del protocollo.

---

## **Parte I – L’attacco Heartbleed**

### **2. Descrizione generale**

**Heartbleed** è una vulnerabilità nella libreria **OpenSSL**, usata per implementare SSL e TLS.  
Il problema riguarda un’estensione opzionale del protocollo chiamata **Heartbeat**, introdotta per mantenere viva la connessione tra client e server.

#### **Funzionamento dell’estensione Heartbeat**

- Il client invia periodicamente un piccolo **pacchetto di keep-alive** contenente:
    
    1. un **messaggio di testo** (ad esempio “ciao”),
        
    2. e la **lunghezza dichiarata** del messaggio.
        
- Il server risponde ripetendo lo stesso messaggio.
    
L’idea è banale: se una delle due parti riceve indietro il piccolo payload inviato, può considerare la connessione ancora viva senza dover ristabilire una nuova sessione TLS.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->


#### **La vulnerabilità**

Il bug stava nel fatto che **OpenSSL non verificava** che la lunghezza dichiarata corrispondesse alla reale dimensione del messaggio.  
Un client malevolo poteva dichiarare una lunghezza arbitraria, ad esempio **64 KB**, anche se il messaggio conteneva solo pochi byte.

Il server, fidandosi, copiava **64 KB dalla propria memoria interna** e li inviava come risposta al client.  
Questo blocco di memoria poteva contenere:

- password,
    
- cookie di sessione,
    
- chiavi private TLS,
    
- o altre informazioni sensibili in chiaro.

![](imgs/Pasted%20image%2020260709032304.png)

> In sintesi: Heartbleed consentiva di “**sanguinare la memoria**” del server, da cui il nome.

Il punto tecnico è un classico **memory leakage**: il server non cifra né protegge magicamente la propria RAM interna; se una routine copia più byte di quelli effettivamente forniti dal client, può restituire porzioni di memoria adiacenti. Ripetendo la richiesta, un attaccante poteva ottenere frammenti diversi di memoria e aumentare la probabilità di estrarre dati sensibili.

> ⚠️ Heartbleed è grave perché può esporre anche la chiave privata TLS del server. In quel caso aggiornare OpenSSL non basta: bisogna rigenerare chiavi e certificati.

---

### **3. Cronologia dell’attacco**

![](imgs/Pasted%20image%2020260709032342.png)

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

Anche dopo la pubblicazione della patch, molti sistemi rimasero vulnerabili perché continuavano a eseguire versioni vecchie di OpenSSL. La disponibilità della correzione non coincide automaticamente con la sua installazione su tutti i server esposti.

![](imgs/Pasted%20image%2020260709032402.png)

---

### **4. Contromisure**

1. **Aggiornare OpenSSL** alla versione **1.0.1g** o superiore.
    
2. **Ricompilare** OpenSSL disattivando completamente l’estensione vulnerabile:
    
    ```
    -DOPENSSL_NO_HEARTBEATS
    ```
    
3. **Rigenerare** tutte le chiavi e certificati privati (potenzialmente esposti).
    
4. **Revocare i certificati compromessi**, poiché Heartbleed poteva rivelare anche le chiavi private del server.

![](imgs/Pasted%20image%2020260709032422.png)

> Heartbleed non era un errore di crittografia, ma di **validazione dei parametri di input**, un classico caso di “fiducia malriposta”.

---

## **Parte II – L’attacco BEAST**

### **5. Introduzione al BEAST Attack**

**BEAST** (Browser Exploit Against SSL/TLS) è un attacco pubblicato nel 2011 da **Thai Duong** e **Juliano Rizzo**.  
Colpisce le sessioni SSL/TLS 1.0 sfruttando debolezze nella modalità di cifratura **CBC (Cipher Block Chaining)**.

L’obiettivo dell’attacco è ottenere dati sensibili (come **token di autenticazione** o **cookie di sessione**) da connessioni HTTPS intercettate da un **man-in-the-middle**.

Il punto notevole è che BEAST può recuperare informazioni dal traffico cifrato **senza conoscere la chiave di cifratura**. L’attaccante sfrutta invece la prevedibilità del vettore di inizializzazione e la possibilità di iniettare testo controllato.

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

![](imgs/Pasted%20image%2020260709032543.png)

#### **b. Problema in TLS 1.0**

TLS 1.0 non generava un IV casuale per ogni record:  
usava **l’ultimo blocco cifrato del messaggio precedente** come IV per il successivo.

Questa scelta creava una **dipendenza diretta** tra i messaggi successivi, rendendo prevedibile il prossimo IV e aprendo la strada all’attacco BEAST.

![](imgs/Pasted%20image%2020260709032453.png)

La modalità CBC nasce proprio per evitare che la cifratura a blocchi sia deterministica: due blocchi uguali non devono produrre sempre lo stesso testo cifrato. Per questo il primo blocco dovrebbe usare un IV fresco e imprevedibile. In TLS 1.0, invece, l’IV del record successivo era ricavabile dall’ultimo blocco cifrato del record precedente, che un attaccante passivo può osservare sulla rete.

> 📌 AES resta sicuro come cifrario a blocchi; il problema è l’uso di CBC con IV prevedibile nel contesto specifico di TLS 1.0.

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

![](imgs/Pasted%20image%2020260709032607.png)

#### **c. Formula semplificata**

Poiché TLS 1.0 usa XOR, l’attaccante conosce:

- $C_{i-1}$ (dal messaggio precedente),
    
- e il vecchio IV (che è proprio $C_{i-1}$).
    

Può quindi costruire un blocco fittizio $P'$ tale che:

$$  
E_K(P' \oplus C_{i-1}) = C_i  
$$

Provando vari valori per $P'$, l’attaccante indovina progressivamente il testo in chiaro.

La ragione per cui l’attacco è possibile sta anche nella reversibilità dello XOR: se $A \oplus B = C$ e si conoscono due dei tre valori, il terzo può essere ricavato. L’attaccante non decifra direttamente con la chiave, ma costruisce ipotesi di plaintext e verifica se il ciphertext prodotto coincide con quello osservato.

Inizialmente questa vulnerabilità sembrava poco pratica: se il blocco fosse di 8 byte, un brute force sull’intero blocco richiederebbe $256^8$ tentativi, un numero troppo alto per un attacco realistico.

---

### **8. Ottimizzazione dell’attacco (Blockwise Chosen Boundary Attack)**

Nel 2011, Duong e Rizzo riuscirono a **ridurre drasticamente la complessità** dell’attacco:

- invece di forzare un intero blocco (es. 64 bit),
    
- riuscirono a forzare **un solo byte alla volta**.
    

Questo rese l’attacco **realistico** e sfruttabile in pratica.

La riduzione è decisiva: un byte ha solo 256 valori possibili. Se l’attaccante riesce a spostare il confine del blocco in modo che rimanga ignoto un solo byte alla volta, può provare i 256 valori possibili, fissare quello corretto e passare al byte successivo.

![](imgs/Pasted%20image%2020260709032650.png)

#### **Esempio**

Per rubare un cookie di sessione di 10 caratteri:

- si effettuano circa **100 tentativi totali**,
    
- spostando progressivamente il “confine” dei blocchi fino a isolare ogni byte incognito.
    

#### **Tecnica**

Il browser viene indotto a:

- inviare richieste HTTP con contenuto controllato,
    
- iniettare dati predeterminati per modificare i limiti dei blocchi di cifratura.
    

Lo script malevolo, spesso realizzato in **JavaScript** o **Java applet**, lavora in background mentre l’utente visita siti sicuri.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nel web molti campi sono prevedibili: struttura HTTP, header noti, prefissi dei cookie e formato delle richieste. L’attaccante sfrutta questa prevedibilità per ridurre ulteriormente l’incertezza e concentrarsi sui byte realmente segreti, per esempio il valore del cookie di sessione.

---

### **9. Scenario pratico**

Un possibile scenario di exploit BEAST:

1. L’utente visita un sito malevolo che esegue lo script di attacco.
    
2. Senza chiudere la pagina, apre una sessione bancaria (HTTPS).
    
3. Entrambi i siti restano aperti per alcuni minuti.
    
4. Lo script di attacco, agendo come **man-in-the-middle**, inizia a forzare byte del cookie di sessione.
    
5. Dopo vari tentativi, riesce a ricostruire il cookie completo e **impersonare l’utente**.
    

> L’attacco era complesso ma tecnicamente possibile, e dimostrava che TLS 1.0 non era più adeguato alle minacce moderne.

Il transcript sottolinea che, in dimostrazioni pratiche, mantenendo aperta la pagina malevola per alcuni minuti era possibile recuperare un cookie di sessione e aprire una sessione parallela impersonando la vittima.

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
        
La migrazione però è stata lenta: quando il proof-of-concept di BEAST fu pubblicato nel 2011, TLS 1.1 aveva già corretto il problema da anni, ma molti server e browser continuavano a usare TLS 1.0.

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

> ✅ Punto d’esame: una vulnerabilità crittografica che oggi sembra solo teorica può diventare pratica appena qualcuno trova un modo intelligente per ridurne la complessità o sfruttarla nel contesto applicativo reale.

---

### **12. Conclusione generale**

Heartbleed e BEAST hanno segnato due epoche diverse della sicurezza TLS:

- **Heartbleed** ha mostrato quanto un _bug di implementazione_ possa violare la riservatezza di milioni di utenti.
    
- **BEAST** ha dimostrato che un _protocollo tecnicamente sicuro_ può diventare vulnerabile se non aggiornato.
    

Entrambi gli attacchi hanno spinto la comunità verso:

- **TLS 1.3**,
    
- **algoritmi AEAD**,
    
- e una più rigorosa cultura di aggiornamento della sicurezza.
