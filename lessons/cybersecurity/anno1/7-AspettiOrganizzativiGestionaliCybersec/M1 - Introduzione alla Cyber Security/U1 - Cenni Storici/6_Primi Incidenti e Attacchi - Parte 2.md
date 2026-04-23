# Le minacce alla sicurezza informatica

## Idea chiave

Una **minaccia** è un _programma eseguibile_ che, una volta entrato in un sistema, può svolgere azioni dannose contro la macchina, l’intero ambiente informatico o i dati gestiti.

## Decodifichiamo ogni parola

- **“Codice eseguibile”**: non un semplice file inerte, ma istruzioni che la CPU può davvero far girare (eseguibili, script, macro, payload incorporati in documenti, firmware, ecc.). Il tratto distintivo è l’**esecuzione**: se gira, può agire.
    
- **“Entrato in un sistema”**: deve _arrivare_ da qualche parte (PC, server, smartphone, PLC, router) passando per un **vettore** di ingresso.
    
- **“Operazioni dannose”**: qualsiasi azione che minacci _CIA_ (Confidenzialità, Integrità, Disponibilità): esfiltrare o cifrare file, alterare configurazioni, creare backdoor, bloccare servizi, sabotare processi.
    
- **“Oggetto dell’azione”**:
    
    1. **la macchina** (es. comprometto l’host),
        
    2. **il sistema informatico** (mi muovo lateralmente in rete),
        
    3. **i dati** (li rubo, li corrompo, li rendo indisponibili).

## Come arriva dentro? Il ruolo dei vettori

La minaccia non “teletrasporta” se stessa: _viaggia_ su un **vettore**. In questa cornice, i due vettori generali da tenere a mente sono:

- **Attacco**: azione intenzionale di un attore (phishing, sfruttamento di un servizio esposto, ecc.).
    
- **Incidente**: evento accidentale o conseguenza indiretta (es. chi collega una chiavetta USB infetta senza saperlo).  
    Entrambi possono fungere da _mezzi di trasporto_ che portano il codice malevolo all’interno.

> La freccia nello schema collega **attacco** e **incidente** alla **minaccia** proprio per dire: _questi sono vettori che introducono il codice dannoso_.

## Perché il concetto è “ampio”

“Minaccia” copre sia la sfera **digitale** sia aspetti connessi al **fisico**: un payload può vivere in un firmware, un componente può essere manipolato, un supporto fisico può veicolare codice; in altre parole, non tutto è solo rete e software di applicazione.

## Mini-mappe mentali per non confondersi

- **Minaccia** → _il codice dannoso potenziale_ (il “cosa”).
    
    
    
- **Attacco / Incidente** → _il modo in cui quel codice arriva_ (il “come”).
    
    
    

_(Nelle prossime parti della lezione vedrai i due termini definiti formalmente; qui ti basta fissare che fungono da vettori.)_

## Due micro-esempi concreti

1. **Email con allegato macro**: l’utente apre il file → la macro (codice eseguibile) parte → cifra i documenti condivisi. Qui la **minaccia** è la macro/payload; il **vettore** è l’**attacco** di phishing che l’ha fatta arrivare.
    
    
    
2. **USB dimenticata in ufficio**: qualcuno la inserisce per curiosità → parte un autorun o uno script residente → viene creato un account amministratore nascosto. La **minaccia** è il codice sull’USB; il **vettore** è un **incidente** d’uso non controllato di supporti removibili.


---
# Attacco informatico

## Definizione di base

Un **attacco informatico** è un’azione **malevola e intenzionale**, compiuta da un individuo o da un’organizzazione, con lo scopo di **violare un sistema informativo**. Le finalità principali possono essere due:

1. **Economiche** → trarre un guadagno diretto o indiretto (furto di dati, estorsione, frodi).
    
2. **Di interruzione del servizio** → rendere un sistema indisponibile, bloccando o rallentando le operazioni di un’azienda, di un ente pubblico o di un singolo individuo.
    

Questa intenzionalità è ciò che lo distingue da un semplice incidente: l’attacco nasce sempre dalla volontà consapevole di danneggiare, violare o sfruttare un sistema.

---

## Elementi caratterizzanti

- **Attore**: può essere un singolo individuo, un gruppo organizzato, un hacktivist, un insider o perfino uno Stato-nazione.
    
- **Obiettivo**: il sistema informativo di qualcun altro (persona, azienda, ente pubblico).
    
- **Scopo**: economico (monetizzare) o strategico (bloccare, sabotare, colpire la reputazione o la disponibilità del servizio).
    
- **Strumenti**: malware, exploit di vulnerabilità, campagne di phishing, denial of service, ransomware, social engineering, ecc.
    

---

## Differenza da “minaccia” e “incidente”

- **Minaccia**: è il “codice o vettore dannoso” che può essere usato.
    
- **Attacco**: è l’**azione volontaria** di sfruttare una minaccia per raggiungere un obiettivo.
    
- **Incidente**: è l’**effetto concreto** che può derivare da un attacco o da un imprevisto.
    

---

## Tendenza storica

Dati recenti (Rapporto Clusit 2021) mostrano che il numero degli **attacchi gravi** è aumentato costantemente negli ultimi anni.  
La crescita è legata a:

- digitalizzazione crescente dei processi economici,
    
- disponibilità di strumenti sempre più automatizzati per gli aggressori,
    
- proliferazione di gruppi criminali ben organizzati,
    
- diffusione del ransomware come modello di business.
    

---

## Esempi concreti

1. **Ransomware su ospedali** → blocco dei sistemi sanitari e richiesta di riscatto in denaro.
    
2. **DDoS su un sito istituzionale** → il sito diventa irraggiungibile per ore, causando un danno di immagine e operativo.
    
3. **Furto di credenziali bancarie via phishing** → guadagno economico diretto per l’attaccante


---
# Incidente informatico

## Definizione

Un **incidente informatico** è una classe generale di **imprevisti e malfunzionamenti**, sia **hardware** sia **software**, che possono verificarsi anche in modo **accidentale** e che producono conseguenze negative: interruzioni di servizio, malfunzionamenti critici o **perdite economiche** con impatto sul business.

---

## Caratteristiche chiave

- Non sempre nasce da un’azione volontaria e malevola: può essere accidentale, fortuito o frutto di un errore umano.
    
- Può derivare sia da un **attacco informatico** (ad esempio un malware mirato), sia da una **causa interna** non intenzionale (un dipendente che perde un laptop, un guasto hardware).
    
- È collegato al concetto di **gestione del rischio**: anche un sistema senza attacchi attivi può comunque subire un incidente dovuto a vulnerabilità o disattenzioni.
    

---

## Esempi tipici

1. **Attacchi malware** (mirati o non mirati) → infezioni che causano blocchi o rallentamenti.
    
2. **Accessi non autorizzati** → un attore esterno o interno ottiene accesso senza permesso.
    
3. **Violazioni interne** → uso improprio di risorse da parte di un dipendente.
    
4. **Escalation di privilegi** → un utente senza autorizzazione ottiene diritti elevati.
    
5. **Furto o perdita di dispositivi** → laptop, smartphone o supporti rimovibili contenenti dati sensibili.
    

---

## Differenza da attacco e minaccia

- **Minaccia** → potenziale codice dannoso che può colpire.
    
- **Attacco** → azione malevola che sfrutta una minaccia.
    
- **Incidente** → l’**effetto reale** che interrompe operazioni o crea un danno, anche senza intenzionalità malevola.
    

---

## Relazione con i dati del Clusit
![Pasted image 20250909182052.png](../../imgs/Pasted%20image%2020250909182052.png)

Il _Report Clusit ottobre 2021_ mostra che:

- Non tutti gli attacchi producono incidenti (es. un attacco bloccato in tempo resta solo tentativo).
    
- Gli incidenti rappresentano circa il 20% degli eventi registrati, e hanno conseguenze dirette su continuità operativa e costi.
    
- La distinzione tra attacchi, incidenti e violazioni di privacy è fondamentale per le statistiche e per la gestione aziendale della sicurezza.
    

---

## Esempi concreti di incidenti

- Un ransomware che blocca i file aziendali → incidente con fermo della produzione.
    
- Un server che crasha a causa di un aggiornamento difettoso → incidente puramente accidentale.
    
- Una chiavetta USB infetta collegata a un PC interno → incidente scatenato da un errore umano, non da un attacco diretto.


---
# Le vulnerabilità informatiche

## Definizione generale

Le **vulnerabilità informatiche** sono **malfunzionamenti, configurazioni errate o errori (bug)** presenti in un sistema che lo espongono a rischi. La loro presenza rende un sistema **fragile** ed esposto a possibili minacce.

In altre parole: la vulnerabilità non è ancora un attacco, ma è la **porta socchiusa** che un attore malevolo (o un evento accidentale) può sfruttare.

---

## Le tre macro-categorie di vulnerabilità

### 1. Software

- Vengono spesso chiamate **bug software**.
    
- Sono errori di scrittura del codice, difetti logici o problemi che emergono durante l’installazione e la configurazione.
    
- Una vulnerabilità software può trovarsi sia **nel codice interno** sia nel **modo in cui il programma interagisce con l’esterno**.
    
- Ogni operazione errata compiuta da un software può aprire un varco: ad esempio, buffer overflow, SQL injection, errori di validazione input.
    

### 2. Protocolli

- Qui parliamo di **lacune nei sistemi di comunicazione** fra tecnologie che dialogano in rete.
    
- Un protocollo mal progettato o datato può consentire a un attaccante di intercettare, manipolare o falsificare messaggi.
    
- Esempi storici: mancanza di cifratura nei protocolli di posta, debolezze in SSL/TLS obsoleti.
    

### 3. Hardware

- Riguardano l’**apparato fisico** di un sistema informatico.
    
- Possono essere problemi **fisici** (polvere, umidità, usura dei componenti) oppure **logici** a basso livello, come un bug nel **firmware** (il software integrato in una scheda elettronica).
    
- Alcuni attacchi celebri hanno sfruttato proprio vulnerabilità hardware (es. _Spectre_ e _Meltdown_, che colpivano l’architettura delle CPU).
    

---

## Importanza del concetto

Una vulnerabilità **non è di per sé un danno**: diventa critica quando viene scoperta e sfruttata da una minaccia o innesca un incidente. Per questo motivo, in cybersecurity la gestione delle vulnerabilità è un’attività chiave:

- **Individuazione** (vulnerability scanning e penetration test).
    
- **Classificazione** (analisi di gravità, CVSS score).
    
- **Mitigazione** (patch, aggiornamenti, hardening dei sistemi).


---
# Il primo antivirus – cronologia degli inizi

## 1982 – Elk Cloner

Il primo virus diffuso in modo incontrollato fu **Elk Cloner**, creato dal quindicenne Richard “Rich” Skrenta.

- Si propagava tramite i **floppy disk**, che all’epoca erano il mezzo principale per scambiare software.
    
- Era un **virus di boot sector**: infettava i floppy e mostrava un messaggio scherzoso dopo un certo numero di avvii.
    
- Importanza storica: dimostrò che anche in assenza di reti globali era possibile un’epidemia virale informatica.
    

---

## 1984 – La definizione di virus

Il ricercatore **Fred Cohen** introdusse la prima definizione accademica di _virus per computer_.

- Lo descrisse come un **programma che colpisce altri programmi, modificandoli in modo da includere una copia di se stesso (eventualmente modificata)**.
    
- Questo formalizzò il concetto di “autopropagazione”, tuttora centrale nella cybersecurity.
    

---

## 1986 – Brain

Il primo virus per sistemi **MS-DOS** si chiamava **Brain**.

- Creato da due fratelli pakistani (Amjad e Basit Farooq Alvi).
    
- Era pensato inizialmente come una forma rudimentale di **“protezione del copyright”**, ma di fatto aprì la strada a infezioni su larga scala nei personal computer.
    
- Si diffondeva anch’esso tramite floppy, alterando il settore di avvio.
    

---

## 1987 – Il primo antivirus

L’hacker tedesco **Bernd Fix** scrisse un programma capace di eliminare il virus **Vienna** da un computer.

- È considerato il **primo antivirus della storia**.
    
- Questo episodio mostra la nascita della “controparte difensiva”: se esiste un codice malevolo, serve un codice dedicato a riconoscerlo e neutralizzarlo.
    
- Si avvia qui l’industria della sicurezza informatica come risposta sistematica ai malware.
    

---

## 1988 – Morris Worm

Il **Morris Worm**, diffuso da Robert Tappan Morris, fu il primo worm a propagarsi massicciamente via rete (ARPANET).

- Sfruttava più vulnerabilità (tra cui rsh, fingerd e password deboli).
    
- Causò enormi rallentamenti e blocchi: circa il 10% dei computer connessi a Internet venne compromesso.
    
- È ricordato come il **primo grande attacco su scala globale**, e come uno spartiacque che rese evidente la necessità di una cybersecurity strutturata.
    

---

## Significato storico della sequenza

- **1982–1986** → dalla comparsa dei primi virus alla presa di coscienza scientifica (Cohen).
    
- **1987** → nasce il concetto di antivirus: l’idea che i sistemi possano essere protetti da software difensivo.
    
- **1988** → con Morris Worm la minaccia diventa globale, mostrando che la rete moltiplica esponenzialmente l’impatto.
    

Questa timeline spiega perché, dagli anni ’90 in avanti, la sicurezza informatica sia diventata una disciplina autonoma: gli incidenti non erano più sporadici ma sistemici.