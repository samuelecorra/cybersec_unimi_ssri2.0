# L1 — Introduzione all'Autenticazione

---

### **1. Perché l'Autenticazione È Fondamentale**

Abbiamo visto che il Reference Monitor garantisce la correttezza del controllo degli accessi solo se:

1. Implementa correttamente la politica di sicurezza (fase di **autorizzazione**).
2. **Fa precedere** la fase di autorizzazione da una corretta fase di **autenticazione**.

Senza un'autenticazione affidabile, sapere chi è autorizzato a fare cosa è inutile: il sistema non può distinguere un utente legittimo da un impostore.

> 💡 **Analogia con il mondo reale:** nella vita quotidiana, una qualche forma di autenticazione precede sempre un'azione rilevante:
> - L'impiegato della banca chiede la carta d'identità prima di far incassare un assegno.
> - Il noleggiatore di auto chiede la patente prima di consegnare il veicolo.
>
> Nei sistemi informatici automatizzati vale lo stesso principio: **l'autenticazione deve sempre precedere l'autorizzazione**.

> 📌 Esistono anche casi in cui non è necessaria una vera autenticazione, ma basta semplicemente **tracciare** gli utenti che richiedono un servizio — registrando la loro identità nei file di log del sistema per eventuali verifiche successive.

---

### **2. Definizione di Autenticazione**

> 📌 **Autenticazione:** il processo di **verifica dell'identità** di un utente (o di un sistema) che richiede accesso a una risorsa.

Si parla di **mutua autenticazione** (*mutual authentication*) quando entrambe le parti che interagiscono devono verificare reciprocamente la propria identità — e non soltanto una delle due.

---

### **3. Tipologie di Autenticazione**

Le parti coinvolte in un'autenticazione possono essere **utenti** o **computer**. A seconda dei casi, si distinguono quattro combinazioni:

| Tipo | Chi si autentica | Con chi | Esempio |
|------|-----------------|---------|---------|
| **Computer ↔ Computer** | Computer | Computer | Stampa in rete: il computer dell'utente si autentica con la stampante |
| **Utente ↔ Utente** | Utente | Utente | Invio di posta elettronica certificata (PEC) |
| **Computer → Utente** | Computer (server) | Utente (client) | Autenticazione di un server web (es. certificato SSL/TLS) |
| **Utente → Computer** | Utente | Computer (sistema) | Login a un sistema informatico |

> 📌 Nelle prossime lezioni ci concentreremo sull'**autenticazione Utente → Computer**: il caso in cui un utente deve dimostrare la propria identità a un sistema informatico per ottenerne l'accesso. In sistemi complessi possono essere necessarie combinazioni di più tipi di autenticazione in sequenza.

---

### **4. I Cinque Elementi dell'Autenticazione Utente-Computer**

Nel caso specifico di autenticazione **utente → computer**, il processo coinvolge cinque elementi:

1. **La persona** che deve autenticarsi.
2. **La caratteristica** che contraddistingue la persona (il "segreto" o fattore di autenticazione).
3. **L'amministratore del sistema**, che gestisce le credenziali registrate.
4. **Il meccanismo di autenticazione**, che verifica la caratteristica presentata dall'utente.
5. **Il meccanismo di autorizzazione** (controllo degli accessi), che entra in gioco dopo la fase di autenticazione.

Il caso più comune vede come caratteristica distintiva la **password**, e il meccanismo di autenticazione si basa sulla coppia *(identità utente, password)*.

---

### **5. I Tre Fattori di Autenticazione**

Più in generale, la caratteristica che contraddistingue un utente può essere di tre tipi — i cosiddetti **fattori di autenticazione**:

| Fattore | Descrizione | Esempi |
|---------|-------------|--------|
| **Qualcosa che l'utente conosce** (*knowledge*) | Un'informazione segreta nota solo all'utente | Password, PIN, risposta a una domanda segreta |
| **Qualcosa che l'utente possiede** (*possession*) | Un oggetto fisico in possesso dell'utente | Chiavi fisiche, carta magnetica, smart card, token hardware (OTP) |
| **Qualcosa che l'utente è** (*inherence*) | Una caratteristica biometrica univoca dell'utente | Impronta digitale, riconoscimento vocale, iride, geometria del volto |

> 💡 I sistemi di **autenticazione a più fattori** (MFA) combinano due o più di questi fattori per aumentare la robustezza dell'autenticazione: anche se un fattore viene compromesso (es. la password rubata), l'attaccante deve superare anche gli altri.

---

### **6. Riepilogo**

> ✅ L'autenticazione è il processo di verifica dell'identità di un utente, ed è una precondizione necessaria per qualsiasi sistema di controllo degli accessi corretto. Può avvenire tra utenti, tra computer, o in combinazione. Il caso centrale del corso è l'autenticazione **utente → computer**. Esistono tre fattori di autenticazione: **conoscenza** (password, PIN), **possesso** (smart card, token) e **biometria** (impronta, iride). Le lezioni successive approfondiranno ciascuno di questi fattori.
