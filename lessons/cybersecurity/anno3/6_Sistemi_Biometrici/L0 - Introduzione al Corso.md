# **Lezione 0: Introduzione al Corso**

---

### **0. Panoramica della lezione**

Questa primissima lezione preliminare ha un semplice obiettivo:

1. **Presentare il corso di Sistemi Biometrici**
    
    - cosa imparerai,
        
    - come sono organizzati gli argomenti,
        
    - come funziona l’esame e come prepararti.

![](imgs/Pasted%20image%2020260209150338.png)

---

### **1. Presentazione dell’insegnamento**

#### **1.1 Obiettivo del corso: creare competenza reale**

L’obiettivo principale non è solo “passare l’esame”, ma **saper lavorare davvero con sistemi biometrici**. Questo significa:

- **Capire come funzionano** i sistemi biometrici:
    
    - componenti hardware (sensori, dispositivi),
        
    - componenti software (algoritmi, database, logica di decisione).
        
- **Saperli usare correttamente**:
    
    - capire limiti e condizioni in cui un sistema è affidabile,
        
    - evitare configurazioni “ingenue” che li rendono inutili o pericolosi.
        
- **Saperli progettare (a livello di ingegnere)**:
    
    - scegliere il tratto biometrico adatto (impronta, volto, iride, …),
        
    - pensare alla pipeline: acquisizione → estrazione di caratteristiche → confronto → decisione,
        
    - capire gli **indici di performance** (errori, soglie, accuratezza).
        
- **Saperli acquistare e integrare**:
    
    - leggere le specifiche commerciali,
        
    - valutare se un prodotto è adatto a un certo scenario (banca, aeroporto, smartphone, accesso a laboratorio),
        
    - capire requisiti legali e di privacy.

![](imgs/Pasted%20image%2020260209150848.png)

In sintesi: alla fine del corso dovresti essere in grado di **ragionare da “ingegnere dei sistemi biometrici”**, non solo riconoscere qualche definizione.

---

#### **1.2 Struttura dell’insegnamento**

La struttura logica del corso è a blocchi:

![](imgs/Pasted%20image%2020260209150928.png)

1. **Sistemi biometrici – Fondamenti**
    
    - Definizioni di biometria.
        
    - Struttura di base di un sistema biometrico.
        
    - Indici di performance (errori, soglie, accuratezza).
        
2. **Sistemi monomodali**
    
    - Sistemi basati su **un singolo tratto**:
        
        - **Impronta digitale**.
            
        - **Volto** (in versione 2D e 3D).
            
        - **Iride**.
            
        - Altri tratti (mano, voce, vene, ecc.).
            
3. **Progettazione dei sistemi**
    
    - Progettazione di sistemi **monomodali**.
        
    - Progettazione di sistemi **multimodali**:
        
        - fusione di più tratti biometrici (es. volto + impronta).
            
    - Gestione dei **database biometrici**.
        
4. **Aspetti legali e applicativi**
    
    - **Privacy**.
        
    - **Normativa** e leggi rilevanti.
        
    - Uso corretto dei sistemi biometrici nelle applicazioni reali.

Questa mappa la puoi tenere come **indice mentale del corso**: ogni lezione si inserisce in uno di questi blocchi.

---

#### **1.3 Come prepararsi al meglio**

Per prepararti bene all’esame (e al mestiere) devi lavorare su tre piani:

![](imgs/Pasted%20image%2020260209150952.png)

1. **Precisione sulle nozioni**
    
    - Definizioni chiare, senza approssimazioni.
        
    - Distinguere concetti simili ma diversi (es. autenticazione vs identificazione, positivo vs negativo, ecc.).
    
2. **Capacità di design**
    
    - Non basta sapere “cos’è la soglia” – devi saper dire **come la imposteresti** in un caso reale.
        
    - Gli esercizi di progettazione ti chiedono di pensare: “sono un’azienda/banca/aeroporto: che sistema scelgo, come lo configuro, perché?”.
    
3. **Approccio proattivo**
    
    - Durante lo studio: **poniti domande** (“come lo userei in banca?”, “cosa succede se cambia la soglia?”) e cerca le risposte nel materiale.
        
    - Non limitarti a memorizzare gli esempi: allenati a **generalizzare**.

---

#### **1.4 Modalità d’esame**

L’esame di Sistemi Biometrici ha queste caratteristiche fondamentali:

- **Prova scritta in laboratorio**:
    
    - si svolge al computer,
        
    - struttura simile alle simulazioni fatte a lezione.
    
- **Domande a risposta multipla**, ma su:
    
    - **teoria e nozioni** (definizioni, concetti, classificazioni),
        
    - **applicazioni** (scenari d’uso, esempi reali),
        
    - **esercizi numerici** (calcolo di indici di performance, soglie, ecc.),
        
    - **design** (progettazione di sistemi biometrici in casi pratici).
    
- **Non esiste “salto d’appello”**:
    
    - non ci sono meccanismi per saltare appelli successivi se un esame non va bene.
    
- **Non è previsto l’orale**.
    
- **Non sono previsti progetti** (almeno nella struttura base del corso: l’esame è solo scritto).

---

#### **1.5 Iscrizione all’esame e codici d’insegnamento**

Punto di logistica importantissimo: **senza iscrizione non puoi sostenere l’esame.**

- L’iscrizione si fa tramite **UNIMIA**.
    
- Devi iscriverti **entro le scadenze** (tipicamente almeno 15 giorni prima).
    
- **Non esistono eccezioni**: se non sei iscritto, non ti possono far entrare in aula.

Conviene segnarsi **in calendario** le date degli appelli e un promemoria per l’iscrizione (es. 2 settimane prima).

L’insegnamento ha **codici diversi** a seconda del corso di laurea:

- **F2Y0B – SISTEMI BIOMETRICI**  
    per **LM Sicurezza Informatica (LM-66)**.
    
- **F680R – SISTEMI BIOMETRICI**  
    per **LT Sicurezza dei Sistemi e delle Reti Informatiche (L-31)**.
    
- **F1A0G – SISTEMI BIOMETRICI**  
    per **LT Sicurezza dei Sistemi e delle Reti Informatiche – corso online (L-31)**.

Se il tuo corso non rientra in questi, devi **verificare in segreteria** quale codice usare.  
L’insegnamento e l’esame sono comunque **identici per tutti**: stessa aula, stesso materiale, stesso scritto.

---

#### **1.6 Corso avanzato: Tecniche e applicazioni biometriche**

Esiste un corso di livello successivo:

- **Tecniche e applicazioni biometriche** (tenuto dallo stesso docente).

Caratteristiche:

- È offerto principalmente:
    
    - nella **magistrale in sicurezza**,
        
    - come **esame a scelta** per alcuni corsi.
    
- **Obiettivi**:
    
    - analisi e progettazione avanzata di sistemi biometrici,
        
    - **ottimizzazione** delle prestazioni,
        
    - studio di **nuove applicazioni**,
        
    - introduzione alla **ricerca** nel settore.
    
- **Prerequisiti consigliati**:
    
    - non è obbligatorio, ma è **fortemente consigliato** aver sostenuto **Sistemi Biometrici** prima.
    
- **Modalità d’esame**:
    
    - struttura simile a quella di Sistemi Biometrici (scritto, laboratorio, focus su teoria + design).

![](imgs/Pasted%20image%2020260209151218.png)

Nel corso avanzato si approfondiscono temi come:

- biometria comportamentale,
    
- sistemi multimodali e fusione,
    
- tecniche di **deep learning** per la biometria,
    
- soft biometrics, sistemi contactless,
    
- autenticazione continua,
    
- applicazioni ad alta sicurezza (es. controllo frontiere – ABC),
    
- trend attuali di ricerca.

![](imgs/Pasted%20image%2020260209151354.png)

![](imgs/Pasted%20image%2020260209151409.png)

Tienilo in mente come naturale “sequel” del corso di base.