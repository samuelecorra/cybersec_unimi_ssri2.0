# **Lezione 13C: Iride nel visibile, scenari operativi, spoofing e antispoofing**

---

### **1. Introduzione: evoluzione dei sistemi di riconoscimento dell’iride**

Storicamente i sistemi di riconoscimento dell’iride sono stati progettati per funzionare in condizioni controllate:

- illuminazione **near-infrared (NIR)**
    
- distanza ridotta
    
- utente cooperativo
    
- acquisizione guidata
  
Negli ultimi anni si è sviluppato un forte interesse verso sistemi più flessibili, in particolare:

- acquisizione **nel visibile**
    
- acquisizione a distanza maggiore
    
- utenti non cooperativi
    
- scenari di sorveglianza (watch-list)

Questi scenari introducono nuove problematiche tecniche e di sicurezza, tra cui lo **spoofing biometrico**.

---

### **2. Riconoscimento dell’iride nel visibile (VIS)**

L’acquisizione nel visibile presenta caratteristiche differenti rispetto all’infrarosso.

![](imgs/Pasted%20image%2020260220125508.png)

#### **2.1 Differenze fisiche tra VIS e NIR**

Nel visibile:

- domina la pigmentazione superficiale (melanina)
    
- le iridi scure appaiono meno strutturate
    
- maggiore sensibilità a riflessi speculari
    
- maggiore variabilità dovuta all’illuminazione

![](imgs/Pasted%20image%2020260220130958.png)

Nel NIR:

- penetrano strutture stromali più profonde
    
- le iridi scure mostrano texture ricca
    
- maggiore uniformità tra individui
    
- minore influenza del colore

![](imgs/Pasted%20image%2020260220131023.png)

Questo implica che i sistemi VIS sono generalmente:

> meno robusti ma più economici e più semplici da integrare (es. smartphone).

---

### **3. Problemi operativi nei sistemi non cooperativi**

Nei contesti reali possono verificarsi:

- movimento dell’utente
    
- variazioni di distanza
    
- blur da movimento
    
- illuminazione non controllata
    
- occlusioni (palpebre, ciglia, occhiali)
    
- rotazioni dell’occhio
    
- ridotta risoluzione

Questi fattori riducono:

- numero di bit affidabili
    
- qualità del matching
    
- separabilità delle distribuzioni

In pratica:

$$

\text{meno bit} \Rightarrow \text{maggiore probabilità di errore}

$$

---

### **4. Watch-list e identificazione uno-a-molti**

Uno scenario tipico è il riconoscimento in modalità:

$$

1 : N

$$

cioè identificazione contro un database di persone ricercate.

Applicazioni:

- sicurezza aeroportuale
    
- controllo accessi ad alta sicurezza
    
- sorveglianza pubblica
    
- frontiere
    
- forze dell’ordine

In questi contesti è fondamentale:

- FMR estremamente basso
    
- tempi di risposta rapidi
    
- robustezza a condizioni non ideali

![](imgs/Pasted%20image%2020260220131302.png)

---

### **5. Spoofing biometrico: definizione**

Lo **spoofing** consiste nel tentativo di ingannare un sistema biometrico presentando un artefatto al posto del tratto biologico reale.

Nel caso dell’iride:

> si cerca di simulare una iride valida davanti al sensore.

![](imgs/Pasted%20image%2020260220131347.png)

---

### **6. Tipologie di attacco all’iride**

Le principali categorie di attacco sono:

#### **6.1 Presentazione di immagini stampate**

- fotografie ad alta risoluzione
    
- stampe su carta
    
- immagini su schermo

![](imgs/Pasted%20image%2020260220131431.png)

Problemi:

- mancanza di profondità
    
- assenza di riflessi naturali
    
- assenza di dinamica pupillare
    

---

#### **6.2 Lenti a contatto artificiali**

Molto pericolose perché:

- riproducono texture realistica
    
- possono contenere pattern stampati
    
- sono indossabili dall’attaccante

Tipologie:

- lenti cosmetiche
    
- lenti con pattern sintetici
    
- lenti custom

![](imgs/Pasted%20image%2020260220131607.png)

---

#### **6.3 Occhi artificiali o modelli 3D**

Attacchi più sofisticati:

- protesi oculari
    
- modelli stampati in 3D
    
- display dinamici
    

---

#### **6.4 Replay attacks**

Uso di:

- video registrati
    
- display digitali
    
- sequenze di immagini

---

### **7. Iridi sintetiche generate artificialmente**

È possibile generare iridi artificiali tramite:

- modelli statistici
    
- algoritmi procedurali
    
- reti neurali
    
- GAN (Generative Adversarial Networks)

Obiettivi degli attaccanti:

- creare identità inesistenti
    
- impersonare utenti reali
    
- bypassare sistemi di sicurezza

Questo rappresenta una minaccia crescente.

---

### **8. Vulnerabilità principali dei sistemi biometrici**

I sistemi biometrici possono essere attaccati in diversi punti:

1. Sensore (presentation attack)
    
2. Canale di comunicazione
    
3. Template biometrico
    
4. Database
    
5. Modulo di matching
    
6. Decisione finale

Lo spoofing riguarda principalmente il **punto 1**.

---

### **9. Liveness detection (rilevamento della vitalità)**

Per difendersi dagli attacchi si usano tecniche di **liveness detection**, cioè verifiche che il campione provenga da un essere vivente.

Principali strategie:
#### **9.1 Risposta pupillare alla luce**

La pupilla reagisce alla variazione luminosa:

$$

\text{stimolo} \Rightarrow \text{costrizione pupilla}

$$

Un artefatto statico non può riprodurre questa dinamica.

![](imgs/Pasted%20image%2020260220131931.png)

---

#### **9.2 Analisi dei riflessi corneali**

L’occhio reale produce riflessi coerenti con:

- geometria tridimensionale
    
- posizione della luce
    
- movimento dell’occhio

Le stampe non presentano questi pattern.

---

#### **9.3 Analisi della texture ad alta frequenza**

Le lenti a contatto artificiali introducono:

- artefatti di stampa
    
- pattern periodici
    
- anomalie di microstruttura

Rilevabili tramite filtri di frequenza.

---

#### **9.4 Analisi spettrale multi-banda**

Uso di:

- visibile
    
- infrarosso
    
- multispettrale

![](imgs/Pasted%20image%2020260220132044.png)

La risposta dei materiali artificiali differisce dai tessuti biologici.

---

#### **9.5 Analisi della profondità (3D)**

Tecniche:

- stereo vision
    
- time-of-flight
    
- structured light

Permettono di verificare che l’oggetto sia tridimensionale.

---

#### **9.6 Movimento naturale dell’occhio**

Micro-movimenti involontari:

- saccadi
    
- tremori oculari
    
- blinking

Sono difficili da simulare.

---

### **10. Metriche di sicurezza contro spoofing**

Le prestazioni antispoofing vengono valutate con:

- **APCER** — Attack Presentation Classification Error Rate
    
- **BPCER** — Bona Fide Presentation Classification Error Rate
    
- **ACER** — Average Classification Error Rate

Queste metriche misurano:

- capacità di rilevare attacchi
    
- impatto sugli utenti genuini
    

---

### **11. Problema di fondo: il template biometrico non è “revocabile”**

Con una password, se la password viene rubata la si cambia.

Con una biometria pura no: se viene rubato un template (es. IrisCode), **non puoi “cambiare occhio”**.

Questo apre un tema centrale di sicurezza biometrica:

- **se un template viene esfiltrato**, l’attaccante potrebbe provare a riutilizzarlo in altri sistemi;
    
- l’utente rischia una compromissione permanente (a meno di contromisure).

Esiste una famiglia di contromisure basata su **trasformazioni revocabili** del template: le **permutazioni**.

---

### **12. Contromisura: permutazioni del template (cancellable biometrics)**

#### **12.1 Idea base: rendere “inservibile” il vecchio IrisCode**

L’obiettivo è trasformare il template originale in un template “derivato” attraverso uno **schema di permutazione** che può vivere sul dispositivo dell’utente.

Concettualmente:

- sul mio dispositivo ho l’IrisCode “vero” (o comunque una sua versione interna);
    
- prima di usarlo verso un servizio esterno, applico una permutazione segreta (o specifica del contesto);
    
- se quel template derivato viene rubato, posso **cambiarne la permutazione**, rendendo il vecchio template rubato **non più utile**.

Questa è l’intuizione chiave: **revocabilità tramite trasformazione**.

---

#### **12.2 Spazio delle permutazioni: perché è enorme**

Vige l’idea di usare permutazioni su **256 elementi**, con numerosità:

$$

256! \approx 10^{507}

$$

Il messaggio è: lo spazio delle permutazioni possibili è talmente grande che, senza conoscere lo schema di permutazione usato, “indovinare” la trasformazione è impraticabile.

![](imgs/Pasted%20image%2020260220132741.png)

---

#### **12.3 Proprietà cruciale: lo XOR non “risente” delle permutazioni**

Il confronto tra IrisCode, nel modello classico, usa XOR (e poi Hamming Distance).

Sottolineiamo una proprietà fondamentale:

$$

XOR(A,B) = XOR(Perm(A), Perm(B))

$$

dove $Perm(\cdot)$ è la stessa permutazione applicata a entrambi.

**Interpretazione operativa:**

- se permuto allo stesso modo entrambi i codici, la struttura del confronto XOR resta consistente;
    
- quindi posso “spostare” la permutazione prima del matching senza cambiare il senso del confronto.

Questa proprietà rende la permutazione **compatibile** con l’architettura di matching basata su XOR/Hamming.

---

#### **2.4 Nota importante: “dall’IrisCode non ricostruisco l’iride (completamente…)”**

Evidenziamo un’idea spesso citata: dall’IrisCode (che è una codifica di fase/segni dopo filtri) **non si ricostruisce integralmente** l’iride originale.

Qui il punto concettuale, lato sicurezza, è duplice:

1. il template non è una “foto compressa”, quindi non è un’inversione banale;
    
2. **nonostante ciò**, un template rubato resta un problema, perché può essere **riutilizzato** in sistemi compatibili (attacco di replay/template injection) se non c’è protezione.

Ed è qui che entrano permutazioni e template protection.

---

### **13. Permutazioni “contestuali”: un IrisCode diverso per ogni contesto**

La lezione aggiunge un’idea molto potente: invece di una sola permutazione “globale”, posso generare IrisCode permutati **specifici** per:

- applicazione
    
- sensore
    
- intervallo di tempo
    
- altri contesti

In pratica:

- **stessa biometria**, ma molte “istanze di template” dipendenti dal contesto;
    
- comprometterne una non compromette automaticamente tutte le altre.

Questa logica è analoga alle password “per sito”, ma applicata a template biometrici tramite trasformazioni.

---

### **14. Generazione sintetica di iridi: perché esiste e perché è rilevante**

Vi è anche la possibilità di generare **texture di iride sintetiche**, cioè immagini (o rappresentazioni) non provenienti da un occhio reale.

Questo è rilevante per due motivi:

1. **ricerca e testing**: dataset sintetici per valutare algoritmi;
    
2. **sicurezza**: possibilità di attacchi (spoofing) o creazione di identità artificiali.

Io qui ti spiego il concetto **a livello difensivo/accademico**, senza “ricette” operative.

---

### **15. Generazione sintetica con Markov Random Fields (MRF)**

La lezione cita tecniche basate su **Markov Random Fields** iterativi per generare texture.

![](imgs/Pasted%20image%2020260220133040.png)

L’idea concettuale è:

- si parte da piccole porzioni (primitive) tratte da immagini reali;
    
- si crea una struttura casuale (matrice random) che governa le scelte locali;
    
- iterativamente si “compone” una texture che rispetta statistiche locali simili alle reali.

In altri termini: si genera una texture **plausibile** perché localmente mantiene correlazioni simili alle texture vere, ma globalmente è una nuova istanza.

Oggi questo obiettivo è perseguibile anche con approcci basati su reti neurali (Convolutional Neural Networks)

---

### **16. Generazione sintetica “multistrato” fisiologicamente ispirata**

Un’altra famiglia di tecniche descritta è quella **a multistrato**, che cerca di imitare la fisiologia:

1. si costruisce un modello 3D dell’iride;
    
2. si generano più strati con texture orientate in modo pseudo-casuale;
    
3. si sovrappongono gli strati considerando trasparenze;
    
4. si adatta la texture al modello 3D calcolando luci e ombre.

![](imgs/Pasted%20image%2020260220133148.png)

Il punto concettuale è che non si genera solo “rumore plausibile”, ma un’immagine coerente con:

- profondità
    
- ombreggiatura
    
- stratificazione

e quindi potenzialmente più realistica.

---

### **17. Iridi sintetiche “deep”: generazione con CNN/GAN**

La lezione mostra un’impostazione tipica **GAN** (Generative Adversarial Network):

- un **generatore** produce campioni sintetici;
    
- un **discriminatore** cerca di distinguere tra reali e falsi;
    
- il training porta il generatore a creare campioni sempre più realistici.

![](imgs/Pasted%20image%2020260220133213.png)

Viene evidenziato un passaggio chiave per l’iride:

- **linearizzazione** dell’iride (passaggio in coordinate polari, dopo segmentazione),
    
- poi generazione nello spazio “srotolato” (più semplice da modellare),
    
- e infine ricostruzione/uso.

Dal punto di vista security, la conseguenza è chiara:

- aumentano gli attacchi di **presentation** (artefatti sempre più realistici),
    
- diventano cruciali **antispoofing/liveness detection** e controlli di coerenza fisica.

---

### **18. Prossimi passi: iride 3D (primi sistemi sperimentali)**

L’idea è: passare da un confronto puramente 2D/texture a un modello che include:

- depth map (mappa di profondità),
    
- mesh 3D,
    
- point cloud,
    
- segmentazione 3D della regione utile.

![](imgs/Pasted%20image%2020260220133259.png)
  
Qui sopra si vedono di pipeline con:

- immagine iride (input),
    
- depthmap predetta,
    
- mesh 3D,
    
- point cloud,
    
- modello segmentato.

E compaiono anche metriche/indicatori di confronto tra:

- confronto genuino
    
- confronto impostore

con valori esemplificativi (come RMSE, IR, EDIR) che servono a mostrare che il 3D può migliorare la separazione tra genuini e impostori, soprattutto contro attacchi “piatti” (stampe/schermi) e alcune lenti.

Qui il messaggio didattico è:

- la 3Dness introduce vincoli fisici aggiuntivi;
    
- quindi può rafforzare l’antispoofing.

---

### **19. Sfide future della sicurezza biometrica dell’iride**

Principali problemi aperti:

1. Attacchi con GAN sempre più realistici.
    
2. Sensori economici con qualità ridotta.
    
3. Sistemi mobili (smartphone).
    
4. Acquisizione a distanza.
    
5. Privacy e protezione dei template biometrici.
    
6. Attacchi al database.

---

### **20. Template protection**

Poiché il template biometrico è permanente (non cambiabile), è fondamentale proteggerlo tramite:

- hashing biometrico
    
- cancellable biometrics
    
- crittografia
    
- secure enclave hardware

---

### **21. Confronto sicurezza vs usabilità**

Esiste sempre un compromesso:

- maggiore sicurezza → maggiore complessità
    
- maggiore usabilità → maggiore rischio

La progettazione del sistema deve bilanciare:

$$

\text{Security} \leftrightarrow \text{Convenience}

$$

---

### **22. Ruolo dell’intelligenza artificiale**

L’AI viene utilizzata per:

- migliorare il matching
    
- rilevare spoofing
    
- segmentare l’iride
    
- migliorare immagini degradate
    
- riconoscere attacchi complessi

---

### **23. Sintesi finale della Parte C**

Concetti chiave:

- VIS vs NIR comportano differenze fisiche importanti.
    
- Gli scenari non cooperativi riducono l’affidabilità.
    
- Lo spoofing è una minaccia reale.
    
- Le difese principali sono tecniche di liveness detection.
    
- L’evoluzione futura riguarda AI e sicurezza dei template.