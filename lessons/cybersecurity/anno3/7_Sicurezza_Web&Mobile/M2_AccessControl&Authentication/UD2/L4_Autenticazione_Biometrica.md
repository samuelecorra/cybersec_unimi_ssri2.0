# L4 — Autenticazione Basata su Caratteristiche Biometriche

---

### **1. Principio Generale**

Nel caso dell'autenticazione biometrica, la prova dell'identità è fornita da **caratteristiche univoche dell'utente** — fisiche o comportamentali — che non possono essere perse, rubate o dimenticate.

**Caratteristiche fisiche:**
- Impronta digitale.
- Forma della mano o del viso.
- Impronta della retina o dell'iride.

**Caratteristiche comportamentali:**
- Firma autografa.
- Timbro e tono della voce.
- Stile di scrittura.
- Andatura (*gait*).

---

### **2. Proprietà Ideali di una Caratteristica Biometrica**

Non tutte le caratteristiche biometriche sono ugualmente adatte all'autenticazione. Una caratteristica biometrica ideale dovrebbe soddisfare le seguenti proprietà:

| Proprietà | Descrizione |
|-----------|-------------|
| **Universalità** | Tutti gli individui dovrebbero possederla |
| **Unicità** | Ogni individuo dovrebbe averla distinta rispetto a tutti gli altri |
| **Stabilità** | Non deve cambiare nel tempo e non deve essere modificabile (deve restare la stessa a 5, 20, 40, 60, 80 anni) |
| **Rilevabilità** | Deve essere facile da acquisire con un sensore |
| **Accettabilità** | Deve essere accettata dall'utente senza risultare eccessivamente intrusiva |
| **Difficoltà di contraffazione** | Deve essere difficile da riprodurre o falsificare artificialmente |

---

### **3. Vantaggi rispetto alle Tecniche Precedenti**

L'autenticazione biometrica risolve i problemi fondamentali delle due tecniche viste nelle lezioni precedenti:

- Rispetto alla **password** (*conoscenza*): non può essere dimenticata, indovinata con attacchi a dizionario o rubata tramite social engineering.
- Rispetto al **token** (*possesso*): non può essere persa fisicamente o clonata.

> 📌 Se la caratteristica biometrica è scelta bene, offre una forma di autenticazione **più forte** rispetto alla password o al token, perché è intrinsecamente legata all'identità biologica dell'utente.

---

### **4. Limitazioni e Stato Attuale**

Nonostante i vantaggi teorici, la biometria è ancora **meno diffusa** rispetto alle tecniche tradizionali per diverse ragioni:

- Alcuni dispositivi di acquisizione sono ancora **costosi**.
- **Non è universalmente accettata dagli utenti**, che la percepiscono come intrusiva.
- Molti utenti sono preoccupati per la potenziale **perdita di privacy** (i dati biometrici sono permanenti e non possono essere "cambiati" come una password).
- Tecnicamente è più complessa da implementare correttamente rispetto a password e token.

---

### **5. Il Problema del Campionamento e del Template**

A differenza delle tecniche precedenti, l'autenticazione biometrica richiede una **fase preliminare di campionamento** per costruire il *template*.

#### **5.1. Generazione del Template**

1. Durante la **fase di enrollment** (registrazione), vengono eseguite **più misurazioni** della caratteristica biometrica dell'utente.
2. Da queste misurazioni viene generato un singolo **template** — la rappresentazione digitale delle caratteristiche univoche dell'utente — che viene memorizzato nel database del sistema.

> ⚠️ **Perché più misurazioni?** La **perfetta uguaglianza** tra due acquisizioni dello stesso dato biometrico è tecnicamente impossibile: la stessa impronta digitale letta due volte in due momenti diversi produrrà due immagini digitali leggermente differenti (pressione diversa, angolazione, condizioni della pelle, ecc.).

#### **5.2. Verifica al Momento dell'Autenticazione**

Al momento del login, la caratteristica biometrica viene acquisita e **confrontata con il template** memorizzato nel database. L'utente viene autenticato se la differenza tra la misurazione corrente e il template è **inferiore a una soglia di tolleranza** $\delta$ definita dal sistema:

$$d(\text{campione}_{nuovo}, \text{template}_{utente}) \leq \delta \Rightarrow \text{autenticato}$$

> 📌 Il punto cruciale dell'intero meccanismo è: come calibrare $\delta$ per **associare correttamente** il campione al template giusto, quando la perfetta uguaglianza non è mai raggiungibile?

---

### **6. Metriche della Biometria: FAR e FRR**

Per calibrare la soglia di tolleranza si usano due metriche fondamentali:

> 📌 **False Acceptance Rate (FAR):** probabilità che il sistema **accetti un impostore**, ovvero che associ il campione in input al template di un utente *diverso* da quello che si sta autenticando. Misura quante volte il sistema "sbaglia in favore dell'attaccante".

> 📌 **False Rejection Rate (FRR):** probabilità che il sistema **non riconosca un utente legittimo**, ovvero che non associ il campione in input al template corretto. Misura quante volte il sistema "sbaglia in sfavore dell'utente legittimo".

#### **6.1. Trade-off tra FAR e FRR**

Le due metriche sono in **conflitto**:

- Se la soglia $\delta$ è **troppo alta** (tolleranza elevata): il sistema è permissivo → la FAR aumenta → si accettano impostori.
- Se la soglia $\delta$ è **troppo bassa** (tolleranza ridotta): il sistema è restrittivo → la FRR aumenta → si respingono utenti legittimi.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Equal Error Rate (EER):** il punto di operazione ottimale dove $\text{FAR} = \text{FRR}$. Rappresenta il miglior compromesso tra sicurezza (FAR bassa) e usabilità (FRR bassa). Viene usato come metrica di riferimento per confrontare diversi sistemi biometrici.

---

### **7. Attacchi ai Sistemi Biometrici**

Un sistema di autenticazione biometrica può essere attaccato in diversi modi:

| Tipo di attacco | Descrizione |
|-----------------|-------------|
| **Contraffazione della caratteristica** | L'attaccante usa una replica artificiale: guanto per l'impronta digitale, lente a contatto per l'iride, maschera per il volto |
| **Replay di un vecchio template** | L'attaccante intercetta e riutilizza un template precedentemente catturato |
| **Attacco al canale sensore-database** | Intercettazione della comunicazione tra il sensore di acquisizione e il database dei template, sostituendo i dati trasmessi |
| **Modifica del template nel database** | Alterazione diretta del template memorizzato — richiede l'accesso al database (attacco dall'interno) |
| **Override della decisione finale** | Sovrascrittura del segnale di output del modulo di confronto (es. da "rifiutato" a "accettato") |

---

### **8. Identificazione vs. Autenticazione in Biometria**

> ⚠️ Esiste una distinzione importante tra **identificazione** e **autenticazione** in ambito biometrico:
> - **Autenticazione** (*1:1*): il sistema confronta il campione dell'utente con il **solo template** dichiarato dall'utente (l'utente afferma di essere Mario Rossi e il sistema verifica se il campione corrisponde al template di Mario Rossi). Funziona bene nonostante la non-perfetta uguaglianza.
> - **Identificazione** (*1:N*): il sistema confronta il campione con **tutti i template** nel database per determinare chi sia l'utente senza che questi si dichiari. Qui la non-perfetta uguaglianza causa più problemi, perché aumenta la probabilità di match falsi con template simili.

---

### **9. Riepilogo**

> ✅ L'autenticazione biometrica usa caratteristiche fisiche (impronta, iride, viso) o comportamentali (voce, firma) per verificare l'identità. È più robusta di password e token perché la caratteristica non può essere persa, rubata o indovinata. Il nodo centrale è che la **perfetta uguaglianza tra due campioni è impossibile**: il sistema usa una soglia di tolleranza calibrata bilanciando **FAR** (falsi accettati) e **FRR** (falsi rifiutati), con ottimo nell'**Equal Error Rate**. Le vulnerabilità principali riguardano la contraffazione, il replay di template, e attacchi al database o al canale di comunicazione tra sensore e sistema.
