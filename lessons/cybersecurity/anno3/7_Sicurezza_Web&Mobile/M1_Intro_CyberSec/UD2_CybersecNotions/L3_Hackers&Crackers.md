# L3 — Attaccanti, Obiettivi e Terminologia: Hacker e Cracker

---

### **1. Chi Attacca e Perché**

Come concluso nella lezione precedente, la sicurezza informatica non è mai un concetto assoluto: bisogna specificare il contesto, da cosa ci si vuole proteggere e quale livello di sicurezza si vuole garantire. Un fattore determinante in questo quadro è la **tipologia degli attaccanti** e i loro obiettivi.

---

### **2. Tassonomia degli Attaccanti**

La seguente lista — non esaustiva — ordina gli attaccanti in modo crescente per **gravità delle conseguenze potenziali**. Nei primi casi spesso non c'è nemmeno un movente economico; negli ultimi casi gli impatti possono essere catastrofici.

| # | Attaccante | Caratteristiche |
|---|-----------|-----------------|
| 1 | **Errore umano grossolano** | Errore non intenzionale, es. un bug nel codice. Nessun movente. |
| 2 | **Hacker** (accezione originale) | Persona esperta in cerca di nuove sfide tecniche. Spesso nessun movente economico. |
| 3 | **Dipendenti o clienti scontenti** | Motivazione: vendetta personale. Obiettivo tipico: pubblicare dati privati della vittima. |
| 4 | **Piccoli criminali** | Motivazione economica limitata, es. furto di un numero ristretto di numeri di carte di credito. |
| 5 | **Crimine organizzato** | Struttura organizzata, obiettivi illeciti ben definiti e su larga scala. Anche la criminalità organizzata tradizionale (mafia) opera ormai in ambito informatico. |
| 6 | **Gruppi terroristici** | Attacchi con motivazioni ideologiche o politiche, obiettivi ad alto impatto. |
| 7 | **Agenti di spionaggio / spionaggio in tempo di guerra** | I casi più gravi: risorse statali, obiettivi militari e governativi, danni potenzialmente devastanti. |

> 📌 L'ordine è deliberato: nei primi livelli la motivazione è spesso la sfida intellettuale o la vendetta personale; nei livelli superiori entrano in gioco interessi economici strutturati, poi ideologici e infine geopolitici.

---

### **3. Obiettivi Comuni degli Attacchi**

A diverse tipologie di attaccanti corrispondono diversi obiettivi. I più frequentemente citati — in ordine puramente indicativo — sono:

- **Diffusione di malware (virus):** contagiare il maggior numero possibile di host connessi a Internet.
- **Defacement:** sfregiare pagine web altrui (es. sostituire il contenuto con messaggi politici o di protesta).
- **Denial of Service (DoS):** rendere un servizio inaccessibile agli utenti legittimi.
- **Furto di dati finanziari:** sottrarre numeri di carte di credito o credenziali bancarie.
- **Truffe online:** frodi economiche di vario tipo.
- **Violazione di dati personali:** cancellazione o lettura di file privati.
- **Spionaggio:** raccolta di informazioni riservate per conto di terzi (concorrenti, stati esteri, ecc.).
- **Furto di proprietà intellettuale:** sottrazione di brevetti, codice sorgente, ricerche riservate.

---

### **4. Terminologia: Hacker, Cracker, Script Kiddie**

> ⚠️ Il termine **hacker** è spesso usato impropriamente nei media con accezione negativa. Storicamente il significato è ben diverso.

#### **4.1. Hacker**

Con il termine *hacker* si indica storicamente una **persona esperta di sistemi informatici**, in grado di introdursi in reti protette o di acquisire una conoscenza approfondita di un sistema, per poi accedervi o adattarlo alle proprie esigenze. La connotazione originale è **neutrale o positiva**: lo smanettone curioso, l'esperto che esplora i limiti della tecnologia.

Si distingue ulteriormente tra:
- **White Hat Hacker** (cappello bianco): hacker "buono", opera entro limiti etici e legali, spesso come consulente di sicurezza.
- **Black Hat Hacker** (cappello nero): hacker con **scopi illeciti**. In questo caso il termine corretto è **cracker**.

#### **4.2. Cracker**

Il termine *cracker* indica un esperto informatico che utilizza le proprie competenze per scopi illeciti: violare sistemi, rubare dati, creare malware. È il termine tecnicamente corretto per chi si definisce comunemente "hacker cattivo".

#### **4.3. Script Kiddie**

Con *script kiddie* si indica un utente con **poca o nessuna competenza tecnica** che utilizza malware e strumenti di attacco creati da altri, scaricati da Internet. Non ha la conoscenza per sviluppare autonomamente gli strumenti che usa.

---

### **5. Il Profilo FBI dell'Hacker (anni '90)**

Negli anni '90, l'FBI condusse un'indagine su circa **200 hacker** con cui aveva avuto a che fare, stilando un profilo sintetico delle caratteristiche comuni:

- **Sesso ed età:** maschio, tra i 14 e i 34 anni.
- **Passione dominante:** il computer, con una media di **57 ore settimanali** trascorse davanti allo schermo.
- **Competenze:** vasta cultura informatica sia sistemistica che nella conoscenza di numerosi linguaggi di programmazione.
- **Vita sociale:** tipicamente single, bassa vita sociale — conseguenza diretta delle ore dedicate al PC.
- **Motivazione economica:** assente o marginale; l'intrusione è fine a se stessa come sfida intellettuale.

> 💡 Questo profilo si riferisce agli hacker "storici" degli anni '80–'90, ovvero prevalentemente White Hat mossi da curiosità tecnica, sebbene le loro attività fossero comunque illegalmente perseguibili.

---

### **6. Hacker Storici Celebri**

#### **6.1. Kevin Mitnick**

Kevin Mitnick fu tra i primi a utilizzare la tecnica dello **IP spoofing**, che consente di rendere non rintracciabile il computer da cui si opera. Si introdusse nei sistemi informatici del governo degli Stati Uniti e di grandi compagnie private, le quali richiesero l'intervento dell'FBI. L'agenzia impiegò diversi anni per catturarlo: nel **1995** Mitnick fu arrestato e condannato a diversi anni di carcere. Rilasciato nel **gennaio 2000**, ebbe l'obbligo di astenersi dall'uso di Internet per circa due anni. Successivamente decise di mettere le proprie competenze a fini leciti: è oggi **CEO della società di consulenza Mitnick Security Consulting** e ha pubblicato diversi libri autobiografici sulla sua storia.

#### **6.2. Onel de Guzmán — Il Virus "I Love You" (2000)**

Il **4 maggio 2000**, dalle Filippine, Onel de Guzmán rilasciò il virus **"I Love You"** (detto anche *Love Bug*). Si trattava di un allegato scritto in **Visual Basic Script** allegato a un'e-mail apparentemente innocua:

- **Oggetto:** `I love you`
- **Corpo:** `kindly check the attached love letter coming from me`

Se il destinatario apriva l'allegato, il virus:
1. Si inviava automaticamente a **tutti i contatti** presenti nella rubrica di Outlook Express.
2. **Sovrascriveva** alcune tipologie di file (immagini, file audio, chiavi di registro di sistema).

**Impatto:**
- Circa **45 milioni di computer** infettati nel mondo.
- Tra le vittime: sistemi del Pentagono, del Parlamento britannico, di Ford, Vodafone Italia, Telecom Italia, Ministero del Tesoro italiano.
- Il volume di mail generate fu tale da **bloccare Internet** e causare problemi di DoS su numerosi server.
- Danno economico stimato: circa **15 miliardi di dollari**.

Nelle Filippine, al tempo del fatto, questo tipo di reato non era punito dalla legge: Onel de Guzmán non fu incarcerato. Anche lui, in seguito, orientò le proprie competenze verso la sicurezza informatica, diventando esperto nel settore nel Regno Unito.

#### **6.3. Gary McKinnon**

Gary McKinnon è accusato dalla giustizia statunitense di aver perpetrato la **più grande intrusione informatica su sistemi militari americani** mai registrata. In un periodo in cui era un amministratore di sistema temporaneamente disoccupato, si introdusse in **97 server militari** di USA e NASA tra il **2001 e il 2002**, tra cui:

- US Army
- US Navy
- Dipartimento della Difesa
- Forza Aerea degli Stati Uniti
- Un sistema del Pentagono

I costi di monitoraggio e correzione dei problemi causati sono stimati tra i **450 e i 700 milioni di dollari**.

**Cronologia giudiziaria:**
- **2002:** rintracciato nella rete telematica britannica e arrestato nel Regno Unito.
- **2002–2005:** il governo USA istruisce il processo e nel 2005 avanza la richiesta di estradizione.
- **2012:** il Sottosegretario per gli Affari Interni del Regno Unito respinge la richiesta di estradizione a causa delle **precarie condizioni di salute** di McKinnon, affetto da una particolare forma di **autismo**.

**Come fu scoperto:** Al **Security Europe 2006**, nella sessione dedicata agli hacker, McKinnon spiegò come fu rintracciato. Operando da remoto su computer fisicamente situati negli Stati Uniti, aveva **calcolato male il fuso orario**: si trovò a operare su una macchina proprio mentre il legittimo proprietario la stava utilizzando. Questi si accorse dell'intrusione e da lì partirono le indagini che portarono alla sua identificazione.

---

### **7. Riepilogo**

> ✅ Gli attaccanti di un sistema informatico spaziano dall'errore umano involontario fino ad agenti di spionaggio statale; gli obiettivi includono diffusione di malware, furto di dati, DoS, frodi e spionaggio. Il termine **hacker** ha storicamente accezione neutrale (esperto curioso); il termine corretto per chi opera illecitamente è **cracker**. Chi usa strumenti altrui senza competenze proprie è detto **script kiddie**. La storia degli hacker storici mostra che anche senza motivazioni economiche, le intrusioni informatiche producono danni enormi e conseguenze legali rilevanti.
