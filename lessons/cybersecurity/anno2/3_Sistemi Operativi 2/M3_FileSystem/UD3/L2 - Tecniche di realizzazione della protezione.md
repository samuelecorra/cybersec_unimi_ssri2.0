# **M3 UD3 Lezione 2 - Tecniche di realizzazione della protezione**

---

### **1. Introduzione**

Questa lezione analizza le tecniche usate per realizzare la protezione nel file system e, piu' in generale, nel sistema operativo.

L'obiettivo e' rappresentare concretamente i **domini di protezione**, cioe' l'insieme di risorse e diritti associati a processi, utenti o domini.

Le principali tecniche sono:

- matrice degli accessi;
- liste di controllo degli accessi;
- liste di capacita' dei domini;
- meccanismo serratura-chiave;
- sistemi basati sulle capacita';
- protezione a livello di linguaggio.

> 📌 Tutte queste tecniche rappresentano lo stesso problema: stabilire quali operazioni un dominio puo' eseguire su una risorsa.

---

### **2. Matrice degli accessi**

La **matrice degli accessi** rappresenta in modo diretto i domini di protezione.

Le righe rappresentano i domini.

Le colonne rappresentano le risorse da proteggere.

Ogni cella contiene l'insieme delle operazioni lecite che un processo, operando in quel dominio, puo' eseguire su quella risorsa.

| | \(F_1\) | \(F_2\) | \(F_3\) | Stampante |
|---|---|---|---|---|
| \(D_1\) | lettura | - | lettura | - |
| \(D_2\) | - | lettura, scrittura | - | stampa |
| \(D_3\) | - | lettura | scrittura | - |

Se un processo si trova nel dominio \(D_3\), puo' eseguire sul file \(F_2\) solo le operazioni indicate nella cella corrispondente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Formalmente:

$$
A[D_i, O_j] = \{\text{diritti del dominio } D_i \text{ sull'oggetto } O_j\}
$$

> ✅ La matrice degli accessi raccoglie in un'unica struttura tutte le autorizzazioni del sistema.

---

### **3. Domini come oggetti della matrice**

Nella matrice degli accessi e' possibile inserire anche i domini come colonne.

In questo modo, un dominio viene trattato come una risorsa.

Il cambiamento di dominio e' autorizzato solo se nella cella corrispondente e' presente il diritto di ingresso o commutazione.

Per esempio:

$$
\text{switch} \in A[D_1, D_2]
$$

significa che un processo operante nel dominio \(D_1\) puo' entrare nel dominio \(D_2\).

> ⚠️ Anche il passaggio tra domini deve essere controllato come qualunque altra operazione protetta.

---

### **4. Diritti di copia**

La matrice puo' includere diritti per modificare la matrice stessa.

Un esempio e' il **diritto di copia**.

Se un dominio possiede un diritto con possibilita' di copia, puo' concedere quel diritto ad altri domini.

Il diritto copiabile viene spesso indicato con un asterisco:

$$
\text{lettura}^*
$$

Se un processo nel dominio \(D_2\) ha il diritto \(\text{lettura}^*\) sulla risorsa \(F_2\), puo' concedere il diritto di lettura su \(F_2\) anche a un altro dominio, per esempio \(D_3\).

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Il diritto di copia permette la propagazione controllata delle autorizzazioni.

---

### **5. Diritti di proprieta'**

Un'altra estensione e' il **diritto di proprieta'**.

Il proprietario di un oggetto puo':

- aggiungere diritti;
- rimuovere diritti;
- concedere diritti ad altri domini;
- eventualmente trasferire o modificare autorizzazioni.

Per esempio, se il dominio \(D_1\) e' proprietario dell'oggetto \(F_1\), allora un processo che opera in \(D_1\) puo' modificare le autorizzazioni associate a \(F_1\).

Analogamente, se il dominio \(D_2\) e' proprietario di \(F_3\), puo' concedere ad altri domini, come \(D_3\), alcuni diritti su \(F_3\).

> 💡 Il diritto di proprieta' consente una gestione dinamica delle regole di protezione.

---

### **6. Limite della matrice completa**

La matrice degli accessi e' concettualmente semplice e completa.

Tuttavia, nei sistemi reali e' spesso molto sparsa.

La maggior parte delle celle e' vuota, perche' ogni dominio ha diritti solo su poche risorse rispetto al totale.

Memorizzare l'intera matrice comporterebbe quindi un grande spreco di spazio nel sistema operativo.

Per questo si usano rappresentazioni ridotte:

- liste di controllo degli accessi;
- liste di capacita' dei domini.

> ⚠️ La matrice completa e' utile come modello teorico, ma poco conveniente come struttura fisica di memorizzazione.

---

### **7. Liste di controllo degli accessi**

Le **liste di controllo degli accessi** leggono la matrice per colonne.

Per ogni risorsa viene conservata solo la lista dei domini che possiedono diritti su quella risorsa.

Una ACL e' quindi una lista di coppie:

$$
\text{ACL}(O_j) = \{(D_1, R_1), (D_2, R_2), \dots\}
$$

dove:

- \(O_j\) e' la risorsa;
- \(D_i\) e' un dominio autorizzato;
- \(R_i\) e' l'insieme dei diritti concessi su quella risorsa.

Esempio:

```text
F1:
  D1 -> lettura
  D4 -> lettura, scrittura
```

Questo significa che:

- i processi nel dominio \(D_1\) possono leggere \(F_1\);
- i processi nel dominio \(D_4\) possono leggere e scrivere \(F_1\).

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Una ACL raccoglie tutte le autorizzazioni relative a una singola risorsa.

---

### **8. Liste di capacita' dei domini**

Le **liste di capacita'** leggono la matrice per righe.

Per ogni dominio si conserva l'insieme delle risorse accessibili e dei diritti corrispondenti.

Una capability list e':

$$
\text{Cap}(D_i) = \{(O_1, R_1), (O_2, R_2), \dots\}
$$

dove:

- \(D_i\) e' il dominio;
- \(O_j\) e' una risorsa;
- \(R_j\) e' l'insieme dei diritti concessi su quella risorsa.

Esempio:

```text
D1:
  F1 -> lettura
  F3 -> lettura
  D2 -> switch
```

Un processo nel dominio \(D_1\) puo' quindi:

- leggere \(F_1\);
- leggere \(F_3\);
- passare al dominio \(D_2\), se invoca il cambiamento di dominio.

> 💡 Una lista di capacita' raccoglie cio' che un dominio puo' fare sulle varie risorse.

---

### **9. Revoca con liste di controllo degli accessi**

Con le ACL, la revoca e' relativamente semplice.

Poiche' tutti i diritti su una risorsa sono raccolti nella lista della risorsa, basta individuare e rimuovere la coppia desiderata:

$$
(D_i, R_i)
$$

oppure modificare l'insieme dei diritti \(R_i\).

La revoca puo' essere:

- immediata o ritardata;
- generale o selettiva;
- totale o parziale;
- permanente o temporanea.

> ✅ Le ACL rendono naturale la revoca dei diritti relativi a una risorsa specifica.

---

### **10. Revoca con liste di capacita'**

Con le liste di capacita', la revoca e' piu' complessa.

I diritti relativi a una stessa risorsa non sono raccolti in un'unica struttura, ma sono sparsi nelle liste dei vari domini.

Per revocare un diritto su una risorsa, bisognerebbe analizzare tutte le capability list e rimuovere tutte le coppie che coinvolgono:

- la risorsa desiderata;
- il diritto da revocare.

Questo puo' essere costoso.

Esistono diverse soluzioni.

#### **10.1. Riacquisizione periodica**

Periodicamente vengono revocate tutte le capacita'.

I processi devono poi riacquisire i diritti richiedendoli al sistema.

Se nel frattempo una capacita' e' stata revocata, il sistema non la concede piu' durante la riacquisizione.

#### **10.2. Puntatori alle capacita'**

Per ogni oggetto si puo' mantenere una lista che individua tutte le capacita' relative a quell'oggetto.

Quando si revoca un diritto, il sistema usa questa lista per trovare e cancellare le capacita' corrispondenti.

#### **10.3. Indirezione**

Le capacita' possono puntare a una tabella generale invece che direttamente all'oggetto.

Se un diritto deve essere revocato, il sistema invalida il puntatore nella tabella.

La capacita' resta formalmente presente, ma non permette piu' di raggiungere l'oggetto.

#### **10.4. Tecnica basata su chiavi**

Ogni risorsa puo' essere associata a una chiave.

Revocare il diritto significa cambiare la chiave richiesta dalla risorsa.

I processi che possiedono la vecchia chiave non riescono piu' ad accedere.

> ⚠️ Le capability list sono efficienti per sapere cosa puo' fare un dominio, ma rendono piu' difficile revocare globalmente un diritto su una risorsa.

---

### **11. Confronto tra ACL e capability list**

Le ACL e le capability list sono due letture diverse della stessa matrice.

Le ACL sono orientate alla risorsa.

Le capability list sono orientate al dominio.

| Tecnica | Organizzazione | Vantaggi | Limiti |
|---|---|---|---|
| **ACL** | Per risorsa | Visione globale dei diritti su un oggetto, revoca piu' semplice | Inefficienti con molte risorse e liste molto grandi |
| **Capability list** | Per dominio | Informazioni localizzate, accesso rapido ai diritti del processo | Revoca globale piu' difficile |

Le ACL sono comode e flessibili, spesso modificabili dagli utenti oltre che dal sistema.

Le capability list sono piu' localizzate e vicine all'uso effettivo da parte del processo.

---

### **12. Meccanismo serratura-chiave**

Un altro approccio e' il meccanismo **serratura-chiave**.

Ogni risorsa ha una **serratura**.

Ogni processo o dominio possiede una o piu' **chiavi**.

Serrature e chiavi possono essere rappresentate come gruppi di bit.

L'accesso e' consentito se la chiave posseduta dal processo combacia con la serratura associata alla risorsa e all'operazione desiderata.

Formalmente:

$$
\text{accesso consentito} \iff \text{chiave del processo} = \text{serratura della risorsa}
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se la chiave non combacia, il sistema operativo rifiuta l'operazione.

La revoca puo' essere realizzata cambiando la serratura della risorsa o la chiave valida nel sistema.

> 📌 Nel modello lock-key, il possesso della chiave corretta abilita l'uso della risorsa.

---

### **13. Sistemi basati sulle capacita'**

Alcuni sistemi operativi supportano nativamente l'uso delle capacita'.

In questi sistemi, le capacita' sono oggetti protetti dal sistema operativo e rappresentano direttamente il diritto di usare una risorsa.

Gli utenti o i processi possono, se autorizzati:

- definire capacita';
- trasferire capacita';
- specializzare diritti;
- controllare l'accesso alle risorse tramite capacita'.

Questo rende il modello flessibile e naturale per l'uso dinamico dei diritti.

> 💡 Nei sistemi capability-based, il diritto di accesso e' trattato come un oggetto gestito esplicitamente.

---

### **14. Protezione nei linguaggi di programmazione**

Un approccio ulteriore consiste nel portare i concetti di protezione dentro il linguaggio di programmazione.

In questo caso:

- il compilatore introduce controlli sui riferimenti;
- il runtime verifica gli accessi;
- le regole possono essere espresse a livello piu' vicino al programma applicativo.

I controlli possono avvenire:

- in fase di compilazione;
- in fase di esecuzione.

Questo permette una protezione piu' granulare.

Le responsabilita' possono essere distribuite:

- il progettista definisce meccanismi e regole di base;
- l'amministratore specifica le politiche;
- l'utente o il programmatore aggiunge eventuali specializzazioni.

Rispetto alla protezione basata solo sul kernel, questo approccio offre piu' flessibilita' ed efficienza, ma puo' garantire minore sicurezza complessiva.

> ⚠️ Spostare parte della protezione fuori dal kernel aumenta la flessibilita', ma riduce il livello di controllo centralizzato.

---

### **15. Sintesi**

| Tecnica | Idea principale | Punto forte | Problema principale |
|---|---|---|---|
| **Matrice degli accessi** | Righe come domini, colonne come oggetti | Modello completo e chiaro | Matrice sparsa e costosa |
| **Domini come oggetti** | I domini sono colonne della matrice | Controlla il cambio di dominio | Aumenta la dimensione della matrice |
| **Diritto di copia** | Propaga diritti ad altri domini | Protezione dinamica | Richiede controllo sulla diffusione |
| **Diritto di proprieta'** | Modifica i diritti su un oggetto | Gestione flessibile | Potenziale abuso se mal gestito |
| **ACL** | Lettura per colonne | Revoca piu' semplice | Liste grandi nei sistemi complessi |
| **Capability list** | Lettura per righe | Diritti localizzati per dominio | Revoca inefficiente |
| **Lock-key** | Chiave del processo contro serratura della risorsa | Accesso rapido, revoca tramite cambio chiave | Gestione delle chiavi |
| **Protezione nel linguaggio** | Controlli nel compilatore/runtime | Granularita' fine | Meno sicura del kernel |

---

### **16. Conclusione**

La matrice degli accessi e' il modello concettuale piu' diretto per rappresentare i domini di protezione.

Poiche' la matrice completa e' spesso troppo grande e sparsa, i sistemi reali usano rappresentazioni piu' compatte:

- ACL, orientate alle risorse;
- capability list, orientate ai domini.

Il meccanismo serratura-chiave offre un'alternativa basata sul confronto tra chiavi e serrature.

Infine, alcuni sistemi operativi e linguaggi di programmazione portano la protezione a livelli piu' vicini all'utente o al programmatore, aumentando flessibilita' e granularita', ma spesso con un compromesso sulla sicurezza.

---

### **17. Fine del Modulo 3**

Con questa lezione si conclude il **Modulo 3 - File System** di Sistemi Operativi 2.

Il modulo ha trattato:

- file, directory e loro caratteristiche;
- condivisione e protezione dei file;
- struttura e realizzazione del file system;
- realizzazione dei direttori;
- realizzazione dei file;
- allocazione dei blocchi;
- efficienza e prestazioni;
- manutenzione;
- protezione delle risorse.
