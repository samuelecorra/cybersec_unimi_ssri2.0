# **Lezione 10b: Spoofing e anti-spoofing nelle impronte digitali**

### **1. Furti di identità e ruolo della biometria**

Il problema dei furti di identità rappresenta una delle principali motivazioni per lo sviluppo dei sistemi biometrici. Le perdite economiche dovute alle frodi, in particolare nel settore delle carte di credito, raggiungono cifre estremamente elevate a livello globale. L’identificazione tramite impronte digitali è stata a lungo considerata una possibile soluzione tecnologica efficace, in quanto il tratto biometrico è intrinsecamente legato alla persona e difficilmente trasferibile.

Tuttavia, studi e sperimentazioni hanno dimostrato che i sistemi biometrici non sono invulnerabili. Secondo ricerche citate in letteratura, il software è in grado di distinguere un dito reale da uno artificiale con una probabilità significativa ma non assoluta, e nel corso degli anni i miglioramenti tecnologici non hanno eliminato completamente il problema dello spoofing. 

---

### **2. Modalità di attacco ai sistemi biometrici**

Un sistema biometrico può essere attaccato in diversi punti della sua architettura. Le principali categorie di attacco comprendono:

![](imgs/Pasted%20image%2020260218145318.png)

- Attacchi ai canali di comunicazione, come i replay attack, che consistono nella intercettazione e riproduzione dei dati biometrici trasmessi tra sensore e sistema.
    
- Compromissione dei moduli software, ad esempio sostituendo componenti di estrazione delle caratteristiche o di matching con versioni malevole.
    
- Attacchi al database contenente i template biometrici raccolti durante l’enrollment.
    
- Attacchi al sensore mediante la presentazione di un tratto biometrico contraffatto.

Quest’ultima categoria, nota come sensor spoofing, è una delle più studiate nel contesto delle impronte digitali. 

---

### **3. Sensor spoofing e fake fingers**

Lo spoofing del sensore consiste nell’ingannare il dispositivo di acquisizione presentando un’impronta artificiale invece di un dito reale. Le tecniche possono utilizzare materiali diversi, tra cui gelatina, silicone e gomma, oppure situazioni più estreme come trapianti cutanei o amputazioni.

![](imgs/Pasted%20image%2020260218145339.png)

Il principio fondamentale è replicare la struttura delle friction ridges della pelle, cioè le creste e i solchi dell’impronta digitale, in modo sufficientemente accurato da essere riconosciuti dal sistema biometrico come autentici. 

---

### **4. Modelli di frode classici nei sistemi biometrici**

Esistono diversi scenari di frode teorici e pratici.

Nel primo scenario, una persona legittima effettua l’enrollment del proprio tratto biometrico e successivamente consente a un’altra persona di utilizzarlo o copiarlo. In questo modo il tratto biometrico diventa replicabile e distribuibile.

![](imgs/Pasted%20image%2020260218145439.png)

Nel secondo scenario, l’attaccante ottiene il tratto biometrico di una vittima e lo utilizza per effettuare l’enrollment nel sistema, ottenendo così accesso non autorizzato.

![](imgs/Pasted%20image%2020260218145453.png)

Un ulteriore scenario moderno prevede la generazione sintetica di impronte tramite modelli artificiali, ad esempio reti generative (GAN), che producono template plausibili senza necessità di acquisire un’impronta reale.

![](imgs/Pasted%20image%2020260218145508.png)

Questi modelli dimostrano che il rischio non riguarda solo la copia fisica del dito ma anche la compromissione digitale del sistema. 

---

### **5. Creazione di impronte artificiali**

Le impronte artificiali possono essere ottenute partendo da diverse fonti:

- Impronte latenti lasciate su oggetti, come bicchieri o superfici.
    
- Immagini rubate da sistemi biometrici.
    
- Impronte fornite da collaboratori interni.
    
![](imgs/Pasted%20image%2020260218145526.png)

La duplicazione può essere realizzata tramite la creazione di uno stampo e la successiva colata di materiali elastici come gelatina o silicone. L’obiettivo è ottenere una replica tridimensionale delle creste dell’impronta.

Di seguito: gelatina, silicone e gomma:

![](imgs/Pasted%20image%2020260218145613.png)

Il confronto visivo e le acquisizioni tramite sensori dimostrano che tali repliche possono produrre immagini biometriche molto simili a quelle reali. In alcuni casi i sensori capacitivi mostrano una maggiore resistenza rispetto a quelli ottici, evidenziando come il tipo di tecnologia influenzi la sicurezza. 

---
### **5.1 Clonare un'impronta**

![](imgs/Pasted%20image%2020260218150232.png)

![](imgs/Pasted%20image%2020260218150247.png)

![](imgs/Pasted%20image%2020260218150304.png)

![](imgs/Pasted%20image%2020260218150315.png)

![](imgs/Pasted%20image%2020260218150344.png)

![](imgs/Pasted%20image%2020260218150355.png)

![](imgs/Pasted%20image%2020260218150427.png)

---

### **5.2 Clonare un'impronta latente**

![](imgs/Pasted%20image%2020260218150549.png)

![](imgs/Pasted%20image%2020260218150601.png)

![](imgs/Pasted%20image%2020260218150624.png)

---

![](imgs/Pasted%20image%2020260218151820.png)

---

### **6. Efficacia degli attacchi spoofing**

Esperimenti condotti su sistemi commerciali hanno mostrato risultati significativi: in molti casi i sistemi biometrici sono stati ingannati con dita artificiali con percentuali di successo elevate, anche superiori all’80%.

![](imgs/Pasted%20image%2020260218152033.png)

Test più recenti confermano che il problema non è stato completamente risolto, e in alcuni scenari moderni è possibile ottenere tassi di successo molto elevati, specialmente quando l’attaccante ha tempo per preparare il materiale e testarlo sul dispositivo bersaglio.

![](imgs/Pasted%20image%2020260218152056.png)

Questo dimostra che la biometria non può essere considerata una soluzione completamente sicura senza adeguate misure di protezione. 

---

### **8. Situazioni estreme di frode**

Sono stati documentati casi reali di tentativi estremi di frode, come amputazioni di dita per furti di veicoli o interventi chirurgici per modificare le impronte digitali. Sebbene rari, questi esempi dimostrano il valore economico e strategico dei sistemi biometrici e i rischi associati. 

---

### **9. Anti-spoofing e test di vitalità (liveness detection)**

Per contrastare lo spoofing sono stati sviluppati sistemi di anti-spoofing basati principalmente sulla verifica della vitalità del dito.

Nei sensori ottici, i test possono includere:

- Rilevazione del flusso sanguigno tramite luce riflessa o trasmessa.
    
- Analisi della temperatura e della distribuzione termica.
    
- Rilevazione dei dettagli microscopici della pelle ad alta risoluzione.
    
- Analisi delle variazioni di colore dovute alla pressione sul sensore.

Nei sensori allo stato solido, possono essere misurate proprietà elettriche come:

- Impedenza del tessuto.
    
- Differenze di potenziale muscolare.
    
- Evoluzione della sudorazione nel tempo.

Sono inoltre in sviluppo tecniche innovative basate sulla deformazione meccanica del dito e sull’analisi chimica delle sostanze presenti sulla superficie cutanea. 

![](imgs/Pasted%20image%2020260218152807.png)

---

### **10. Limiti delle tecniche di anti-spoofing**

Nonostante i progressi, le tecniche di anti-spoofing non sono perfette. Se il materiale artificiale è sufficientemente sottile o realistico, può ingannare anche sistemi che rilevano parametri fisiologici come il battito o la temperatura.

Questo implica che la sicurezza biometrica deve essere progettata considerando attacchi avanzati e non solo scenari ideali. 

---

### **11. Generazione sintetica di impronte**

I generatori sintetici di impronte sono strumenti utilizzati principalmente per testare sistemi biometrici senza dover raccogliere grandi dataset reali. Essi simulano caratteristiche come orientamento delle creste, rumore di acquisizione e variabilità morfologica.

Tuttavia, tali generatori non possono sostituire completamente i test con dati reali, perché potrebbero non rappresentare tutte le complessità delle impronte umane. 

![](imgs/Pasted%20image%2020260218152834.png)

---

### **12. Tecnologie anti-spoofing hardware e software**

Le soluzioni anti-spoofing possono essere implementate sia a livello software sia hardware.

Le soluzioni software analizzano caratteristiche dell’immagine acquisita, come nitidezza delle linee e presenza di pori, e possono essere aggiornate facilmente anche tramite aggiornamenti remoti. Sono particolarmente adatte all’uso di tecniche di machine learning.

Le soluzioni hardware richiedono sensori avanzati capaci di rilevare parametri fisiologici, offrendo una maggiore affidabilità nella rilevazione della vitalità ma con costi, consumi energetici e latenza più elevati.

La scelta tra approcci hardware e software dipende dal contesto applicativo e dai requisiti di sicurezza. 

---

### **13. Esempi commerciali di anti-spoofing**

Sono disponibili soluzioni commerciali che integrano tecnologie di anti-spoofing basate su intelligenza artificiale o sensori avanzati. Alcuni dispositivi utilizzano sistemi di matching interno al sensore e comunicazioni cifrate per ridurre il rischio di intercettazione.

![](imgs/Pasted%20image%2020260218152954.png)

Scanner biometrici professionali, utilizzati ad esempio nelle forze dell’ordine o nel settore bancario, integrano funzionalità di rilevazione della vitalità e acquisizione ad alta qualità conformi a standard internazionali. 

![](imgs/Pasted%20image%2020260218153009.png)

---

### **14. Sintesi finale**

La seconda parte della lezione ha analizzato in modo approfondito le vulnerabilità dei sistemi biometrici basati sulle impronte digitali, evidenziando le tecniche di spoofing e le contromisure anti-spoofing.

È emerso che i sistemi biometrici non sono intrinsecamente sicuri e possono essere attaccati attraverso diversi vettori, in particolare tramite la presentazione di impronte artificiali. Le tecniche di rilevazione della vitalità rappresentano una contromisura fondamentale, ma non definitiva.

La progettazione di sistemi biometrici sicuri richiede quindi un approccio multilivello che integri protezioni hardware, software e organizzative.