# L2 — Le Tre Politiche di Controllo degli Accessi: DAC, MAC e RBAC

---

### **1. Chi Stabilisce la Politica?**

Nella lezione precedente abbiamo visto chi **fa rispettare** la politica (il Reference Monitor) e chi la **implementa** (il meccanismo). Rimane aperta una domanda: di chi è la **responsabilità di definire** la politica stessa?

La risposta determina la classificazione delle politiche di controllo degli accessi in due macro-categorie:

| Responsabilità | Tipo di Politica |
|----------------|-----------------|
| Il **proprietario della risorsa** decide a chi è permesso l'accesso | **Discrezionaria (DAC)** |
| Una **politica centralizzata a livello di sistema** stabilisce gli accessi | **Mandatoria (MAC)** |

> ⚠️ Questa classificazione non è univoca in letteratura: esistono interpretazioni alternative dei termini *discrezionario* e *mandatorio*. Quella qui presentata è la più diffusa nei testi di riferimento.

---

### **2. Politica Discrezionaria — DAC (Discretionary Access Control)**

#### **2.1. Definizione e Funzionamento**

Nella politica discrezionaria, il **proprietario della risorsa** decide:
- **Chi** può accedere alla risorsa.
- In **quale modalità** (lettura, scrittura, esecuzione, ecc.).

Il proprietario ha inoltre il potere di **delegare i propri privilegi ad altri utenti** — questa capacità si chiama *delegation of duty*.

> 💡 Il sistema operativo **Linux** implementa la politica discrezionaria: ogni file ha un proprietario che assegna i permessi `r`, `w`, `x` a sé stesso, al gruppo e agli altri utenti.

#### **2.2. Limite del DAC: Vulnerabilità ai Trojan Horse**

La politica discrezionaria controlla solo gli **accessi diretti** alla risorsa. Non ha alcun controllo su cosa accade all'informazione **dopo** che l'accesso è stato concesso e il dato è stato letto.

Questo rende i sistemi DAC vulnerabili ai **Trojan Horse** — programmi apparentemente utili che contengono codice nascosto malevolo in grado di eseguire operazioni non autorizzate.

**Esempio — Come un Trojan bypassa il DAC:**

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Si considerino due soggetti $S_1$ e $S_2$, e due file $F_1$ e $F_2$:

- $S_1$ è proprietario di $F_1$ e ha stabilito che **solo lui può leggere** $F_1$.
- $S_2$ è proprietario di $F_2$ e ha stabilito: $S_2$ ha accesso in lettura a $F_2$; $S_1$ ha accesso in **scrittura** a $F_2$.

Scenario di attacco:
1. $S_1$ invoca inconsapevolmente un **Trojan Horse** — un programma di utilità che internamente contiene le operazioni `read(F1); write(F2)`.
2. Il Trojan viene eseguito **con i privilegi di $S_1$**.
3. `read(F1)` è autorizzata (il Reference Monitor la approva: $S_1$ ha diritto di leggere $F_1$).
4. `write(F2)` è autorizzata (il Reference Monitor la approva: $S_1$ ha diritto di scrivere su $F_2$).
5. Il contenuto di $F_1$ viene **copiato in $F_2$**.
6. $S_2$ legge $F_2$ e ottiene il contenuto riservato di $F_1$.

**Risultato:** $S_2$ accede ai dati privati di $S_1$ senza che nessuno se ne accorga e senza violare formalmente nessuna regola DAC. Il meccanismo è stato rispettato, ma la politica di riservatezza è stata aggirata.

---

### **3. Politica Mandatoria — MAC (Mandatory Access Control)**

#### **3.1. Definizione e Funzionamento**

Nella politica mandatoria, gli accessi sono gestiti **centralmente dal sistema**: viene assegnato un **livello di sicurezza** sia ai soggetti sia agli oggetti, senza che i soggetti abbiano alcun controllo sul livello loro assegnato.

La modalità di accesso è un **attributo dell'oggetto** e impone restrizioni sul **flusso di informazione tra livelli di sicurezza** diversi.

> 📌 La MAC risolve il problema del Trojan Horse del DAC: poiché i livelli di sicurezza di $F_1$, $F_2$, $S_1$ e $S_2$ sarebbero diversi, il **flusso di informazione** dal livello di $F_1$ verso $F_2$ verrebbe rilevato e bloccato dal sistema.

Esistono due modelli principali di politica MAC, a seconda della proprietà di sicurezza che si vuole garantire.

---

#### **3.2. Modello Bell-LaPadula — Garanzia della Segretezza**

Il modello **Bell-LaPadula** è usato per garantire la **confidenzialità** delle informazioni. È il modello di riferimento in ambito militare.

- Oggetti e soggetti vengono classificati in **livelli di sicurezza** (es. *public*, *restricted*, *secret*, *top secret*). I livelli possono formare un **reticolo** (struttura parzialmente ordinata).
- Il **livello del soggetto** rappresenta la sua autorizzazione e il grado di fiducia associato all'utente.
- Il **livello dell'oggetto** rappresenta la sensibilità dell'informazione contenuta.

**Le due regole fondamentali:**

> 📌 **No Read Up (NRU):** un soggetto di livello $L_s$ non può leggere un oggetto di livello $L_o > L_s$. Garantisce la **segretezza**: informazioni riservate non sono accessibili a utenti non sufficientemente autorizzati.

> 📌 **No Write Down (NWD):** un soggetto di livello $L_s$ non può scrivere su un oggetto di livello $L_o < L_s$. Previene il **downgrade** dell'informazione: un soggetto con alto privilegio non può copiare dati sensibili su risorse di livello inferiore (accessibili a utenti meno autorizzati).

---

#### **3.3. Modello Biba — Garanzia dell'Integrità**

Il modello **Biba** è complementare a Bell-LaPadula ed è usato per garantire l'**integrità** delle informazioni.

- Oggetti e soggetti vengono classificati in **livelli di integrità**.
- Il **livello del soggetto** rappresenta il grado di fiducia associato all'utente.
- Il **livello dell'oggetto** rappresenta il grado di fiducia nella validità dell'informazione contenuta (quanto ci si fida che il dato sia corretto e non corrotto).

**Le due regole fondamentali (complementari a Bell-LaPadula):**

> 📌 **No Write Up (NWU):** un soggetto di livello $L_s$ non può scrivere su un oggetto di livello $L_o > L_s$. Garantisce che dati integri **non vengano corrotti** da informazioni provenienti da sorgenti meno affidabili.

> 📌 **No Read Down (NRD):** un soggetto di livello $L_s$ non può leggere un oggetto di livello $L_o < L_s$. Garantisce che un soggetto con alto livello di fiducia non legga dati di qualità/affidabilità inferiore, che potrebbero influenzarne le azioni.

---

### **4. Politica Basata sui Ruoli — RBAC (Role-Based Access Control)**

#### **4.1. Definizione e Motivazione**

La politica RBAC si basa sull'osservazione che uno stesso soggetto può avere bisogno di **permessi diversi** a seconda del **ruolo** che svolge in un dato momento.

**Esempio — L'utente Tom:**

Tom è contemporaneamente:
- Amministratore di sistema.
- Utente generico (*Tommy*).
- Consulente della banca A.
- Consulente della compagnia B.

Ha senso che Tom disponga di un insieme di permessi diverso a seconda del ruolo che ricopre nel momento in cui accede al sistema.

#### **4.2. Funzionamento**

- L'accesso agli oggetti è **mediato dai ruoli**: il soggetto $S$ con ruolo $R$ ha tutti i permessi associati a $R$.
- Un soggetto da solo (senza ruolo attivo) non ha nessun permesso.
- È possibile definire **gerarchie di ruoli** (un ruolo di livello superiore eredita i permessi di quelli inferiori).

#### **4.3. Vantaggi**

| Vantaggio | Descrizione |
|-----------|-------------|
| **Least Privilege** | Ogni utente ha il privilegio minimo necessario per compiere l'azione corrente. |
| **Facilità di revoca** | Revocare un permesso su un ruolo revoca automaticamente il permesso a tutti gli utenti che hanno quel ruolo attivo. |
| **Separation of Duty** | Assegnando ruoli diversi, si garantisce che nessun singolo utente accumuli permessi incompatibili (es. chi approva una spesa non può anche emetterla). |
| **Anonimato parziale** | Non è il soggetto identitario a richiedere l'accesso, ma il soggetto-nel-ruolo: una forma di pseudonimia interna al sistema. |

#### **4.4. Differenza Fondamentale: Gruppo vs. Ruolo**

> ⚠️ Un **gruppo** è un insieme **statico** di utenti. Un **ruolo** è un insieme **dinamico** di permessi/privilegi — lo stesso utente può attivare ruoli diversi in momenti diversi. Confondere i due concetti è un errore frequente.

---

### **5. Confronto tra le Tre Politiche**

| Proprietà | DAC | MAC | RBAC |
|-----------|-----|-----|------|
| Chi stabilisce gli accessi | Il proprietario della risorsa | Il sistema (centralizzato) | Il sistema, tramite ruoli |
| Controllo del flusso informativo | ✗ No | ✓ Sì | Parziale |
| Vulnerabile ai Trojan Horse | ✓ Sì | ✗ No | Dipende dall'implementazione |
| Flessibilità | Alta | Bassa | Alta |
| Tipico contesto d'uso | Sistemi consumer (Linux, ecc.) | Ambito militare/governativo | Sistemi aziendali |

---

### **6. Riepilogo**

> ✅ Esistono tre principali politiche di controllo degli accessi. La **DAC** delega la decisione al proprietario della risorsa: è flessibile ma vulnerabile ai Trojan Horse, che possono aggirare i controlli sfruttando i privilegi del proprietario. La **MAC** gestisce centralmente gli accessi tramite livelli di sicurezza (Bell-LaPadula per la segretezza, Biba per l'integrità): impedisce il flusso illegittimo di informazioni tra livelli. La **RBAC** basa i permessi sui ruoli dinamicamente assegnati ai soggetti, supportando il principio del minimo privilegio e la separation of duty.
