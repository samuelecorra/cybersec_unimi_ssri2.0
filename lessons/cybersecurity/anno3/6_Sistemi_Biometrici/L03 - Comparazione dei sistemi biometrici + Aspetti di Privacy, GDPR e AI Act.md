# **Lezione 3: Comparazione dei sistemi biometrici + Aspetti di Privacy, GDPR e AI Act**

---

### **1. Introduzione generale**

Questa lezione affronta due blocchi fondamentali:

1. **Come confrontare diversi sistemi biometrici** (accuratezza, costi, sensori, variabilità, ecc.)
    
2. **Quali implicazioni legali, etiche e di privacy esistono** quando si utilizzano tecnologie biometriche moderne (dal Decalogo del 2006 → GDPR → AI Act).

L’obiettivo è renderti capace di:

- valutare criticamente un sistema biometrico,
    
- scegliere il tratto più adatto per un’applicazione reale,
    
- capire **dove nascono i rischi** per utenti e aziende,
    
- sapere **cosa impone davvero la legge** oggi.

---

## **PARTE A — Comparazione dei Sistemi Biometrici**

---

### **2. Perché confrontare i sistemi biometrici è difficile**

(Pagine 3–4)

Confrontare due sistemi biometrici è complicato perché entrano in gioco molti parametri, spesso difficili da stimare correttamente. La comparazione richiede di analizzare:

- **Accuratezza**
    
- **Velocità**
    
- **Usabilità**
    
- **Accettazione da parte degli utenti**
    
- **Costo**
    
- **Scalabilità**
    
- **Interoperabilità**

![](imgs/Pasted%20image%2020260209180654.png)

Mostra un diagramma multidimensionale che evidenzia come ogni sistema eccella in alcune dimensioni e fallisca in altre.

**Idea chiave Feynman**

> Non esiste “la biometria migliore in assoluto”. Esiste solo la biometria _più adatta_ alla situazione.

---

### **3. Le 7 caratteristiche fondamentali di confronto**

(Tabellone pagina 4)

![](imgs/Pasted%20image%2020260209180733.png)

Qui trovi i parametri classici della letteratura biometrica:

#### **1. Universalità**

Quanto il tratto è presente in tutti gli individui?

#### **2. Unicità**

Quanto differisce da persona a persona?

#### **3. Permanenza**

Quanto resta stabile nel tempo?

#### **4. Raccoglibilità (Collectability)**

Quanto è facile acquisirlo in modo affidabile?

#### **5. Performance**

Quanto è accurato il sistema basato su quel tratto?

#### **6. Accettabilità**

Quanto gli utenti si sentono a proprio agio ad usarlo?

#### **7. Circumvention**

Quanto è facile da falsificare o attaccare?

---

### **4. Tabella comparativa dei tratti biometrici**

La tabella precedente mostra valori qualitativi per tutti i tratti più comuni.  
Esempio semplificato:

- **Volto**: alta universalità, ma bassa unicità → accurato? Non molto.
    
- **Iride**: universalità alta, unicità altissima, permanenza altissima → è uno dei tratti più affidabili dell'intero panorama.
    
- **Firma**: accettabile dagli utenti, ma variabile, poco unica e facilmente falsificabile.
    
- **DNA**: molto unico ma difficilissimo da raccogliere e invasivo → non usato per autenticazione quotidiana.

> Più un tratto è “comodo”, più tende a essere poco stabile e quindi meno accurato.

---

### **5. Tabella B — Uso reale dei tratti biometrici**

![](imgs/Pasted%20image%2020260209180829.png)

Questa tabella incrocia:

- **Autenticazione (1:1)**
    
- **Identificazione (1:N)**
    
- **Variabilità del tratto nel tempo**
    
- **Costo del sensore**
    
- **Numero di campioni indipendenti**
    

#### **Conclusioni operative**

- Oggi _solo iride e impronta_ possono fare **identificazione 1:N con N molto grandi**.
    
- Voce, volto, firma, mano → adatte solo a 1:1 o a 1:N molto piccoli.
    

---

### **6. Autenticazione vs Identificazione**

(Pagina 6)

#### **Autenticazione (1:1)**

Rispondi alla domanda: _“Sei davvero chi dici di essere?”_

- È più tollerante agli errori.
    
- Richiede un solo confronto.
    

#### **Identificazione (1:N)**

Risposta: _“Chi sei tra questi N utenti?”_

- Richiede **accuratezza estremamente elevata** (errore ≪ 10⁻⁵).
    
- Richiede template molto piccoli (< 1 KB).
    
- Richiede matching velocissimi (< millisecondi).

---

### **7. Variazioni del tratto biometrico nel tempo**

(Pagine 7–8)

#### **Perché contano?**

Un tratto che cambia velocemente:

- genera più falsi negativi,
    
- peggiora l’accuratezza,
    
- aumenta la frustrazione degli utenti.
    

#### **Esempi pratici**

- **Voce**: influenzata da raffreddore, raucedine.
    
- **Firma**: dipende da allenamento, stanchezza, stile.
    
- **Volto**: barba, capelli, occhiali, espressioni.
    

#### **Tratti stabili per tutta la vita**

- **Iride**
    
- **Impronta**
    

---

![](imgs/Pasted%20image%2020260209181154.png)

---

### **8. Campioni indipendenti**

(Pagina 10)

Possedere molti campioni dello stesso tratto **migliora enormemente la robustezza** del riconoscimento.

- Impronte disponibili: 10
    
- Occhi: 2
    
- Mani: 2
    
- Firma: solo 1
    
- Voce: 1
    
- Volto: 1
    

Esempio:  
un sistema può chiedere **due impronte** invece di una sola → FMR (False Match Rate) scende in modo drastico.

---

### **9. Costo e dimensione del sensore**

(Pagine 11–13)

#### **Costo**

- Microfono → costo zero
    
- Sensore iride aeroportuale → > 3000€

#### **Dimensioni**

Influiscono sulla possibilità di integrare il sensore in:

- smartphone
    
- laptop
    
- sistemi embedded
    
- badge aziendali
    

Alcuni sensori (firma grafometrica) sono troppo grandi per dispositivi mobili.

---

### **10. Velocità del sistema**

(Pagina 14)

Metriche principali:

- **Tempo di matching** (ms)
    
- **Utenti/ora**
    
- **Tempo reale** vs **offline**
    

Esempio critico:  
Se N = 2000 iridi e vogliamo identificare una persona in 2 secondi,  
→ _matching singolo deve stare sotto 1 ms_.

---

### **11. Interoperabilità**

(Pagine 15–16)

#### **Definizione**

Un sistema biometrico è interoperabile se può funzionare con **sample acquisiti da sensori diversi**, magari in epoche diverse.

#### **Esempi — pagina 15**

- impronte su carta → scanner → sistema digitale moderno

#### **Osservazione importante**

L’interoperabilità **aumenterà sempre** perché i sensori stanno esplodendo in varietà.

![](imgs/Pasted%20image%2020260209181604.png)

![](imgs/Pasted%20image%2020260209181634.png)

---

### **12. Scalabilità**

(Pagina 17)

Domanda chiave:

> se il database cresce, il sistema resta accurato e veloce?

Molti sistemi dichiarano performance ottime **solo su scale piccole**, e peggiorano con database reali.

![](imgs/Pasted%20image%2020260209181655.png)

---

### **13. Sintesi della Parte A**

(Pagina 18)

Da verificare sempre:

- caratteristiche del tratto
    
- autenticazione / identificazione
    
- variabilità
    
- sensore
    
- velocità
    
- interoperabilità
    
- scalabilità
    
- costo
    
- accettazione dell’utente
    

---

## **PARTE B — Privacy, Legislazione, GDPR, AI Act**

---

### **14. Percezione degli utenti**

(Pagina 20)

Gli utenti oscillano tra entusiasmo e paura.

#### **Vantaggi percepiti**

- Addio password e chiavi
    
- Più sicurezza contro frodi
    
- Utile contro terrorismo
    

#### **Svantaggi percepiti**

- “Mi stanno schedando”
    
- “Saprebbero dove vado”
    
- “Saprebbero cosa compro”
    

Molti svantaggi… **succedono già oggi** con carte di credito, Telepass, fidelity card, ecommerce.

---

### **15. Il vero anello debole della catena**

(Pagine 23–24)

**Non è la biometria.  
È la fonte.**

Il primo punto della catena è la _validazione dell’identità_: documenti, foto, timbro, funzionario.

Se qui c’è un errore, la biometria eredita il problema **perfettamente**.

---

### **16. Sample vs Template**

(Pagina 25)

#### **Sample**

![](imgs/Pasted%20image%2020260209181931.png)

- è l’immagine grezza → impronta, foto, voce
    
- può essere rielaborato, aggiornato, migrato
    
- occupa più spazio
    
- è più facile da rubare e falsificare
    
- peggiora la privacy
    

#### **Template**

![](imgs/Pasted%20image%2020260209181942.png)

- più piccolo
    
- più sicuro (ma non assoluto!)
    
- non contiene tutto il tratto
    
- difficile da migliorare nel tempo
    
- dipende dalla tecnologia usata
    

**Attenzione:**  
molti studi mostrano che **dal template si può ricostruire sample quasi identici** → richiede monitoraggio costante.

---

### **17. Proscription: il problema delle liste**

(Pagine 26–27)

La proscrizione è l’uso improprio di un tratto biometrico per scopi non dichiarati.

Esempio:

- verifico se sei in una “lista nera”
    
- oppure ti inserisco in una lista nera senza dirtelo

![](imgs/Pasted%20image%2020260209182116.png)

![](imgs/Pasted%20image%2020260209182109.png)

La biometria peggiora il problema perché:

- è difficilmente ripudiabile
    
- “invecchia” molto lentamente (può essere usata dopo decenni)
    
- è interoperabile → sample di 40 anni fa possono entrare in sistemi nuovi

![](imgs/Pasted%20image%2020260209182059.png)

---

### **18. Privacy tramite variabilità del tratto**

(Pagina 28)

Idee importanti:

- Tratti molto variabili → generano molti falsi negativi → poco invasivi.
    
- Per applicazioni “leggere” (DisneyWorld) è preferibile **voce** o **firma**.
    
- Per applicazioni “critiche” (aeroporto) servono **iride** o **impronta**.
    

---

### **19. Apriti Sesamo: due modelli di uso**

(Pagina 29)

Due mondi:

1. **Iride/impronta** → lunga persistenza → tracciabilità storica → sicurezza altissima
    
2. **Voce basata su parola chiave** → accuratezza solo se cooperi → sicurezza più bassa → invasività minima
    

---

## **20. Evoluzione normativa: dal 1996 al GDPR all’AI Act**

![](imgs/Pasted%20image%2020260209182525.png)

---

### **21. Tre grandi pilastri normativi**

![](imgs/Pasted%20image%2020260209182546.png)

#### **1. Decalogo 2006**

- minimizzazione
    
- no archivi centralizzati
    
- uso proporzionato
    

#### **2. GDPR 2018**

- i dati biometrici sono **categoria speciale**
    
- serve base giuridica rafforzata
    
- DPIA obbligatoria
    
- introduzione del DPO
    
- sicurezza by design
    

#### **3. AI Act 2024**

- vietato il riconoscimento facciale realtime in luoghi pubblici (salvo casi rarissimi)
    
- vietato il social scoring pubblico
    
- obbligo di registrazione dei sistemi ad alto rischio
    
- controlli, documentazione, audit, qualità del dataset

![](imgs/Pasted%20image%2020260209182640.png)

---

### **22. GDPR — definizioni e obblighi**

(Pagina 37)

Il GDPR definisce i dati biometrici come:

> dati ottenuti tramite un trattamento tecnico specifico che permettono identificazione univoca.

E vieta il trattamento _salvo eccezioni_:

- consenso esplicito
    
- interesse pubblico rilevante
    
- obbligo di legge
    

---

### **23. DPO — Data Protection Officer**

(Pagina 38)

Serve quando:

- c’è un trattamento su larga scala
    
- l’ente è pubblico
    
- vengono trattati dati biometrici di molte persone
    

Ruoli:

- supervisione privacy
    
- parere sulle DPIA
    
- punto di contatto con il Garante
    
- indipendenza assoluta
    

---

### **24. DPIA — Data Protection Impact Assessment**

(Pagine 39–40)

Documento obbligatorio che contiene:

- descrizione delle operazioni
    
- proporzionalità e necessità
    
- rischi
    
- misure tecniche (es. cifratura, pseudonimizzazione)
    
- alternative non biometriche
    

---

### **25. Buone prassi del Decalogo ancora valide**

(Pagine 41–42)

- evitare archivi centralizzati
    
- conservare i dati per poco tempo
    
- prevedere alternative non biometriche
    
- nominare figure indipendenti
    

---

### **26. Consenso informato**

(Pagina 43)

**Il consenso per dati biometrici deve essere:**

- libero
    
- esplicito
    
- tracciabile
    
- revocabile
    
- con alternativa equivalente per l’utente
    

Non è ammesso come base legale nel mondo del lavoro (squilibrio di potere).

---

### **27. Data Breach**

(Pagina 44)

Se un sistema biometrico subisce violazione:

- bisogna notificare al Garante entro **72 ore**
    
- tramite PEC
    
- usando il modello previsto
    

---

### **28. Pseudonimizzazione e resilienza**

(Pagine 48–50)

#### **Pseudonimizzazione**

Tre condizioni:

1. impossibilità di identificare senza info aggiuntive
    
2. informazioni aggiuntive separate
    
3. misure tecniche forti per impedire abusi (la pseudonimizzazione viene incorporata nella **privacy by design**
    

#### **Resilienza**

Capacità del sistema di restare operativo anche sotto attacco.

---

### **29. Moratoria italiana sul riconoscimento facciale**

(Pagina 51)

Fino al **31 dicembre 2025**:

- vietata installazione e uso di sistemi di riconoscimento facciale biometrici in Italia (tranne magistratura e reati gravi).
    
- i comuni devono chiedere parere del Garante → quasi sempre rigettato.
    

---

### **30. Casi d’uso biometrici e obblighi**

(Pagine 52–56)

#### **1. Autenticazione informatica ad alta sicurezza**

DPIA obbligatoria  
Consenso non necessario  
Obbligo di legge o interesse pubblico

#### **2. Controllo accessi fisici (laboratori, aree sensibili)**

DPIA obbligatoria  
Consenso non necessario

#### **3. Firma grafometrica**

Consentita solo con consenso  
Necessaria alternativa non biometrica

#### **4. Accesso facilitativo (palestra, biblioteca)**

Consenso necessario  
Alternativa obbligatoria

![](imgs/Pasted%20image%2020260209183651.png)

---

### **31. Esempi reali di sanzioni**

(Pagine 60–62)

- **66.000 €** a società di Catania per impronte raccolte senza autorizzazione.
    
- **200.000 €** a Università Bocconi per riconoscimento facciale negli esami online.
    
- **20 milioni €** a Clearview AI per scraping illegale dei volti.
    

---

### **32. Social Scoring**

![](imgs/Pasted%20image%2020260209184031.png)

(Pagine 63–64)

È vietato in UE (per enti pubblici).

L’AI Act vieta:

- social scoring
    
- tecniche subliminali manipolative
    
- previsione di comportamenti criminali

![](imgs/Pasted%20image%2020260209184120.png)

![](imgs/Pasted%20image%2020260209184130.png)

---

### **33. Sintesi finale**

(Pagina 65)

- Il punto debole è sempre **la fonte**, non la biometria.
    
- Il problema della proscrizione esiste e va gestito.
    
- Preferire template ai sample, senza illudersi che siano perfetti.
    
- Scegliere il tratto in base al livello di sicurezza richiesto.
    
- Rispetto rigoroso di GDPR e AI Act.
    
- Evitare archivi centralizzati e prevedere alternative.
    
- Valutare sempre l’impatto (DPIA).
    
- Social scoring vietato nel pubblico.