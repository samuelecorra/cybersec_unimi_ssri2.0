# L2 — Autenticazione Basata sulla Conoscenza: Password

---

### **1. Funzionamento di Base**

Il meccanismo di autenticazione tramite password è concettualmente semplice:

1. L'utente possiede una **password segreta**, condivisa solo con il sistema.
2. Al momento del login, l'utente fornisce:
   - Lo **username** → per *identificarsi*.
   - La **password** → per *autenticarsi*.
3. Il sistema confronta la password inserita con quella memorizzata: se coincidono, l'utente è autenticato.

> 📌 È il meccanismo più **diffuso**, **economico** e **facile da implementare**. È però anche il più **debole** — come vedremo nel dettaglio.

Le problematiche da risolvere sono:
- **Dove e come memorizzare** le password.
- **Come verificare** la password al login.
- **Quali vulnerabilità** presenta e come mitigarle.

---

### **2. Memorizzazione delle Password: Evoluzione Storica**

#### **2.1. Password in Chiaro (CTSS, 1961–1973)**

Il primo sistema operativo multi-utente, il **Compatible Time-Sharing System (CTSS)** del MIT (1961), memorizzava le password **in chiaro** in un file protetto.

- **Verifica:** confronto diretto tra la password nel file e quella inserita dall'utente.
- **Problema critico:** chiunque riesca ad accedere al file delle password può **impersonare tutti gli utenti** del sistema.
- **Paradosso logico:** la fase di autenticazione (accesso al file delle password) dipende essa stessa da chi ha l'**autorizzazione** ad accedere al file — una dipendenza circolare che costituisce il punto debole del sistema.

#### **2.2. Password Cifrate / Hash (CTSS post-1973, Unix)**

La soluzione adottata da CTSS dopo il 1973 e poi standardizzata in Unix è di non memorizzare la password in chiaro, ma il suo **hash** — il risultato di una funzione crittografica **irreversibile** $H(\cdot)$.

**Verifica al login:**
$$H(\text{password inserita}) \stackrel{?}{=} H(\text{password memorizzata})$$

> ⚠️ **Problema residuo:** se due utenti scelgono la stessa password, il loro hash memorizzato sarà identico. Un attaccante che ottiene il file degli hash può notarlo immediatamente e concentrare l'attacco su tali account.

#### **2.3. Salt + Hash (Unix moderno, Linux)**

La soluzione definitiva adottata dai sistemi Unix/Linux moderni aggiunge il concetto di **salt**: una sequenza di bit generata casualmente dal sistema, **univoca per ogni utente**.

**Procedura di registrazione di un nuovo utente:**
1. Il sistema genera un **salt** casuale univoco (storicamente 12 bit, oggi 48–128 bit).
2. Il salt viene **concatenato** alla password scelta dall'utente: $\text{salt} \| \text{password}$.
3. Viene calcolato $H(\text{salt} \| \text{password})$.
4. Nel file delle password vengono salvati: lo **username**, il **salt in chiaro** e l'**hash** risultante.

```
File delle password:
| username | salt (in chiaro) | H(salt || password) |
|----------|-----------------|---------------------|
| alice    | a3f7            | 9d2e8c...           |
| bob      | 12bc            | 4f91a3...           |
```

**Procedura di verifica al login:**
1. Il sistema recupera il **salt** dell'utente dal file delle password (è in chiaro).
2. Concatena il salt alla password appena inserita dall'utente: $\text{salt} \| \text{password}_{input}$.
3. Calcola $H(\text{salt} \| \text{password}_{input})$.
4. Confronta il risultato con l'hash memorizzato: se coincidono, l'utente è autenticato.

> 📌 **Perché il salt è salvato in chiaro?** Perché è necessario conoscerlo per riprodurre esattamente la stessa operazione di hashing durante la verifica. Non è un segreto: la sua funzione non è essere nascosto, ma essere **diverso per ogni utente**.

**Vantaggi del salt:**

1. **Elimina le collisioni di hash:** anche se due utenti scelgono la stessa password, i loro salt sono diversi → gli hash memorizzati sono diversi.
2. **Complica gli attacchi a forza bruta:** l'attaccante deve ora provare le combinazioni $\text{salt} \| \text{password}$ per ogni utente separatamente — non può usare una tabella precalcolata (rainbow table) valida per tutti. Con 12 bit di salt, l'attacco richiede $2^{12} = 4096$ volte più tentativi.

---

### **3. Tipologie di Attacchi alle Password**

#### **3.1. Attacco a Forza Bruta (Exhaustive Search)**

L'attaccante prova **sistematicamente tutte le possibili stringhe** dell'insieme dei caratteri ammessi, in ordine crescente di lunghezza. È computazionalmente costoso ma garantisce di trovare la password se ci si aspetta che abbastanza tempo.

#### **3.2. Attacco a Dizionario**

L'attaccante prova solo le **password più probabili**, sfruttando il comportamento tipico degli utenti:
- Preferiscono password **brevi** (più facili da ricordare).
- Le legano a informazioni personali: nome proprio, nome dei figli, data di nascita, nome del cane, squadra del calcio preferita, ecc.

#### **3.3. Password Guessing**

Indovinare la password sfruttando informazioni note sull'utente (nome, interessi, abitudini). Caso limite e informale dell'attacco a dizionario.

#### **3.4. Snooping / Shoulder Surfing**

Sbirciare la password mentre viene digitata dall'utente, fisicamente (guardare da sopra le spalle) o tramite telecamere.

#### **3.5. Sniffing**

Intercettare la password durante la sua **trasmissione in rete**, nel caso in cui non venga usato un canale cifrato.

#### **3.6. Trojan Login / Spoofing**

Un programma malevolo **impersona l'interfaccia di login** legittima, raccogliendo le credenziali inserite dall'utente inconsapevole.

#### **3.7. Social Engineering**

Un attaccante induce l'utente a **rivelare la propria password** attraverso manipolazione psicologica, fingendo di essere un tecnico, l'amministratore di sistema, ecc.

> ⚠️ Chiunque conosca la password di un utente può **impersonarlo** completamente nei confronti del sistema — non c'è modo per il sistema di distinguere l'utente legittimo dall'impostore.

---

### **4. Contromisure agli Attacchi**

| Minaccia | Contromisura |
|----------|-------------|
| Guessing / Forza bruta | Verificare i log degli accessi; limitare il numero di tentativi falliti; vincolare la complessità della password |
| Sniffing | Mascherare i caratteri digitati sullo schermo; proteggere l'area di memoria temporanea |
| Attacco a dizionario offline | Rendere il file delle password accessibile solo agli amministratori (es. *shadow password* di Unix) |
| Shoulder surfing | Schermare fisicamente l'area di digitazione |

---

### **5. Cause Principali di Vulnerabilità delle Password**

Comprendere le cause aiuta a definire le buone pratiche:

- La password rimane **invariata per lungo tempo**.
- Le password vengono **condivise** con colleghi o amici.
- Si scelgono password **deboli** (brevi, intuibili).
- Si usa la **stessa password** per servizi diversi.
- Password complesse vengono **scritte su carta** — vanificando la sicurezza.

---

### **6. Linee Guida per la Buona Gestione delle Password**

Partendo dalle cause di vulnerabilità, si ricavano le regole pratiche:

- **Cambiare** la password frequentemente.
- **Non condividerla** con nessuno.
- **Non usare la stessa** password per servizi diversi.
- Usare una password di almeno **8 caratteri**.
- **Non usare parole di dizionario** (vulnerabili ad attacchi a dizionario).
- **Bilanciare complessità e memorabilità:** la password deve essere abbastanza complessa da resistere agli attacchi, ma abbastanza memorabile da non dover essere scritta.

**Controlli automatici del sistema al momento dell'impostazione:**
- Obbligo di inserire **caratteri misti** (maiuscole, minuscole, numeri, simboli).
- Rifiuto di parole del linguaggio naturale.
- Obbligo di **cambio periodico** della password.

---

### **7. Distribuzione Iniziale delle Password**

Un problema spesso sottovalutato è come distribuire in modo sicuro la **prima password** all'utente. Gli approcci possibili sono:

| Metodo | Vantaggi | Svantaggi |
|--------|----------|-----------|
| Consegna di persona dall'amministratore | Sicuro | Impraticabile su scala globale; richiede verifica dell'identità |
| Nessuna password iniziale / password di default | Semplice | Gli utenti raramente modificano la password di default |
| Invio via email o comunicazione telefonica | Pratico | Il canale può essere non sicuro e intercettato |
| Password iniziale temporanea + cambio obbligatorio al primo accesso | Buon compromesso | L'amministratore controlla la password iniziale; l'utente la sostituisce con una personale più robusta |

> 💡 La soluzione più adottata in pratica è l'ultima: l'amministratore prepara un account con una **password temporanea** che l'utente è **obbligato a cambiare** entro un tempo massimo o al primo accesso.

---

### **8. Riepilogo**

> ✅ L'autenticazione tramite password è il meccanismo più diffuso ma anche il più debole. Le password non vanno mai memorizzate in chiaro: il sistema moderno usa $H(\text{salt} \| \text{password})$, dove il **salt** è un valore univoco per utente che elimina le collisioni di hash e complica gli attacchi offline. Le principali vulnerabilità sono guessing, dizionario, sniffing, shoulder surfing e social engineering. Le contromisure combinano controlli tecnici (limitazione tentativi, shadow password, complessità obbligatoria) e comportamentali (cambio frequente, non riutilizzo, non condivisione).
