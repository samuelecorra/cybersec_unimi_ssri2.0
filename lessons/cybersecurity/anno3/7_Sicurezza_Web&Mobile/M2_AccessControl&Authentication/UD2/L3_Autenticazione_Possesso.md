# L3 — Autenticazione Basata sul Possesso: Token

---

### **1. Principio Generale**

Nel caso dell'autenticazione basata sul **possesso**, la prova dell'identità è fornita dal fatto che l'utente detiene fisicamente un oggetto — il **token**.

Tipologie di token:
- **Carta magnetica** — striscia magnetica con capacità di memoria limitata.
- **Smart card** — carta con microprocessore e memoria integrati.
- **Smart token** — dispositivo portatile con display e, a volte, tastierina.
- **RFID** (*Radio Frequency Identifier*) — etichetta elettronica a radiofrequenza.

> ⚠️ **Svantaggio fondamentale:** l'autenticazione dimostra l'identità del **token**, non quella dell'**utente**. Se il token viene rubato, chiunque lo possegga può impersonare l'utente — esattamente come rubando le chiavi di casa si può entrare nell'abitazione altrui.

> 💡 **Mitigazione:** è difficile estrarre un segreto da un token fisico. Per questo motivo, i token vengono spesso usati **in combinazione** con un secondo fattore di autenticazione (tipicamente una password o un PIN), rendendo molto più difficile l'impersonificazione anche in caso di furto.

---

### **2. Carta Magnetica**

La carta magnetica è il token più semplice: una striscia magnetica in grado di memorizzare una quantità limitata di dati.

**Usi tipici:** badge di accesso, bancomat, carte di credito (in passato).

**Struttura standard:** esistono standard internazionali che definiscono come deve essere organizzata la striscia magnetica, garantendo l'interoperabilità con qualsiasi lettore (indipendente dal produttore). La striscia contiene tipicamente **tre tracce**: una non utilizzata e due che replicano gli stessi dati.

**Verifica del PIN durante l'autenticazione:**
- Confronto con un **server autenticatore centralizzato** (online).
- Oppure confronto con il PIN salvato direttamente **sulla carta**.

**Svantaggi:**
- Memoria molto limitata.
- Può essere **clonata** con relativa facilità con dispositivi disponibili sul mercato.

---

### **3. Smart Card**

La smart card è un'evoluzione della carta magnetica: incapsula un **circuito integrato** direttamente nella carta.

**Caratteristiche tecniche:**
- **Microprocessore** integrato.
- **Memoria** significativamente maggiore rispetto alla carta magnetica.
- **Canale di input/output** per comunicare con il lettore.
- Spesso un **coprocessore crittografico**, che consente di eseguire operazioni crittografiche direttamente sulla carta (es. meccanismi challenge-response — approfonditi nel modulo sui protocolli crittografici).

**Storia:** ideata negli anni '70, commercializzata negli anni '80. Solo dalla fine degli anni '90 si è diffusa come carta di credito; oggi è adottata anche come bancomat.

**Applicazioni attuali:**
- **SIM** (*Subscriber Identity Module*) nei telefoni cellulari — memorizza identità dell'utente e PIN.
- **Pay TV** — controllo degli accessi ai canali a pagamento.
- **Badge** evoluto rispetto alla semplice carta magnetica.
- **Titoli di viaggio elettronici** (es. biglietti NFC).
- **Denaro elettronico** (es. carte prepagate con chip).

---

### **4. Smart Token**

Lo smart token è un dispositivo portatile che dispone di:
- **Microprocessore** e **memoria** (come la smart card).
- **Display** per mostrare informazioni all'utente.
- A volte una **tastierina** fisica.

Tutto questo in dimensioni molto compatte (paragonabili a un portachiavi).

**Utilizzi principali:**
1. **Generatore di One-Time Password (OTP)** — genera password valide per un solo utilizzo e per un intervallo di tempo limitato.
2. **Contenitore di credenziali digitali** (chiavi crittografiche) — es. liToken Pro.

**Svantaggio:** la gestione in caso di **furto o smarrimento** è complessa. Nel periodo transitorio tra la perdita del token e l'emissione di uno sostitutivo, l'utente legittimo non può autenticarsi.

---

### **5. Funzionamento delle One-Time Password (OTP)**

#### **5.1. Principio Generale**

Il meccanismo OTP si basa su tre elementi condivisi tra il token e il server:

1. Una **chiave segreta** (*seme*) — memorizzata dal produttore all'interno del token e condivisa con il server di autenticazione.
2. Un **algoritmo di generazione** — noto a entrambi: stesso input → stesso output.
3. Una **informazione esterna** variabile — tipicamente l'**ora corrente** (token sincroni) o un contatore.

**Generazione della OTP:**
- Il token combina il seme con l'ora (e, a volte, il PIN dell'utente) tramite l'algoritmo condiviso.
- La password risultante viene visualizzata sul **display per 30–90 secondi**, dopodiché viene rigenerata automaticamente.
- Il server, conoscendo seme, algoritmo e orologio sincronizzato, genera in autonomia la stessa OTP nello stesso intervallo: se coincidono, l'autenticazione ha successo.

#### **5.2. Caso Pratico — RSA SecurID**

L'**RSA SecurID** è uno smart token ampiamente utilizzato dalle banche per l'autenticazione al servizio di home banking. Implementa un'**autenticazione a due fattori**:

1. **Qualcosa che l'utente conosce:** un PIN segreto (scelto dall'utente, condiviso con il server).
2. **Qualcosa che l'utente possiede:** il codice a 6 cifre generato dal token ogni **60 secondi**.

**Flusso di autenticazione:**

```
Utente                          Server
  |                                |
  |─── codice titolare ───────────►|  (identifica l'utente)
  |─── PIN segreto ───────────────►|  (fattore "conoscenza")
  |─── OTP dal token ─────────────►|  (fattore "possesso")
  |                                |
  |        [server verifica:]      |
  |        - codice titolare ✓     |
  |        - PIN ✓                 |
  |        - OTP generata ✓        |
  |                                |
  |◄── accesso concesso ───────────|
```

Se tutte e tre le informazioni sono valide, l'utente viene autenticato. In caso contrario, il server chiede di generare una nuova OTP e riprovare.

**Come server e token generano la stessa OTP:**
- Seme condiviso al momento della distribuzione del token.
- Algoritmo identico su entrambi i lati.
- Orologi sincronizzati → stesso input nello stesso intervallo → stesso output.

#### **5.3. Token come Contenitore di Credenziali — liToken Pro**

Il **liToken Pro** è uno smart token usato non per generare OTP, ma per **custodire chiavi crittografiche** (credenziali digitali).

**Funzionamento:**
1. L'utente inserisce il liToken Pro nella porta USB del dispositivo.
2. Digita la propria password.
3. Se la password è corretta, le chiavi contenute nel token diventano disponibili per **firme digitali** o **comunicazioni sicure** per la durata della sessione.

---

### **6. RFID (Radio Frequency Identifier)**

L'**RFID** è una tecnologia di identificazione a radiofrequenza: un'etichetta elettronica (*tag*) che memorizza informazioni sull'oggetto (o sull'animale) a cui è applicata.

**Componenti del sistema RFID:**
1. **Apparecchio di lettura/scrittura** (reader/writer).
2. **Tag / transponder** — l'etichetta RFID applicata all'oggetto.
3. **Sistema informativo** di gestione dei dati trasmessi dai lettori.

**Tipologie di tag:**
- **Attivi** — dotati di batteria propria, trasmettono autonomamente.
- **Passivi** — alimentati induttivamente dal campo elettromagnetico del lettore, senza batteria.
- **Semi-passivi / Semi-attivi** — varianti ibride.

I tag RFID possono avere **dimensioni molto ridotte** (anche millimetriche).

**Applicazioni diffuse:**
- **Antitaccheggio** nei negozi.
- **Catalogazione libri** nelle biblioteche.
- **Microchip identificativi** negli animali domestici.
- Ticketing e controllo accessi.

---

### **7. Riepilogo**

> ✅ L'autenticazione basata sul possesso usa un **token fisico** come prova d'identità. Le tipologie principali sono: **carta magnetica** (semplice ma clonabile), **smart card** (microprocessore + coprocessore crittografico, usi molto diffusi), **smart token** (OTP o custodia chiavi, es. RSA SecurID con intervalli da 60s), **RFID** (etichetta a radiofrequenza). Il limite fondamentale è che si autentica il **token**, non l'utente: per questo i token vengono quasi sempre abbinati a un secondo fattore (PIN o password).
