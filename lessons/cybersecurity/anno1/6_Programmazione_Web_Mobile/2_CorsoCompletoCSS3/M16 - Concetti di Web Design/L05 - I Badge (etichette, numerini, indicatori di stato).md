## **Lezione 5: I Badge (etichette, numerini, indicatori di stato)**

### **1. Che cos’è un badge**

Un **badge** è un elemento grafico piccolo, compatto, quasi sempre arrotondato, che serve per **aggiungere un’informazione breve** accanto a un titolo, un’icona o un bottone.

Pensalo come la “spilletta” attaccata a un giubbotto:  
non è il protagonista, ma arricchisce, segnala, qualifica.

Sul web il badge comunica:

- quantità (notifiche, messaggi, elementi nuovi)
    
- stato (online / offline)
    
- etichette di categoria (NEW, BETA, HOT)
    
- priorità
    
- mini-informazioni che non meritano un alert
    

È un indicatore **non invasivo**, pensato per completare un elemento già esistente.

---

### **2. Perché i badge funzionano**

Perché sono:

- **piccoli → non disturbano**
    
- **colorati → risaltano subito**
    
- **posizionati vicino al contesto → capisci immediatamente a cosa si riferiscono**
    

Esempio psicologico:

- “Notification **4**” scritto in linea → il cervello lo legge come una frase intera.
    
- Icona 🔔 con badge **4** → il cervello capisce istantaneamente: _4 notifiche non lette_.
    

Quel cerchietto rosso **isola l’informazione**.  
Non confonde il titolo. Non lo appesantisce. È perfetto.

---

### **3. Dove si usano i badge**

#### **3.1. Sulle icone**

Esempio classico:

- 🔔 **4**
    
- ✉️ **99+**
    
- 💬 **7**
    

Questo è nato su Facebook e poi adottato ovunque (mail, chat, app bancarie, dashboard).

#### **3.2. Sui titoli delle sezioni**

Esempio:

```
Esempio Heading    [NEW]
```

Serve a dire:

- "Questa sezione è stata aggiornata"
    
- "Questa feature è nuova"
    
- "C’è qualcosa che non c’era prima"
    

#### **3.3. Sui bottoni**

Esempio:

```
Notifications   [4]
```

In questo caso il badge specifica un quantitativo legato all’azione del bottone.

#### **3.4. Come indicatori di stato**

Esempio nelle chat:

- 🟢 Online
    
- 🔴 Non disponibile
    
- 🟡 Occupato
    

Il badge prende un significato universale, molto intuitivo.

---

### **4. Tipi di badge**

Indipendentemente dal framework:

- **badge tondo** (piccolo, compatto → numeri, stato)
    
- **badge pillola** (allungato → etichette tipo "NEW")
    
- **badge colorati** (verde = success, blu = info, rosso = error, giallo = warning)
    

In Material Design vengono chiamati **“chips”** se sono più grandi e addirittura cliccabili.

---

### **5. Struttura tecnica di un badge**

Tecnicamente è **semplicissimo**:

```
<span class="badge">4</span>
```

Nel CSS o nel framework si applica:

- background color
    
- border-radius (spesso 9999px = cerchio / pillola)
    
- font-size ridotto
    
- padding piccolo
    
- posizione **assoluta** se deve stare sopra un'icona
    

Esempio mentale per un badge sopra un’icona:

```
<div class="icon">
   <i class="fa fa-bell"></i>
   <span class="badge">4</span>
</div>
```

---

### **6. Badge in Bootstrap (come esempio standard)**

Bootstrap fornisce l’elemento `badge`:

- `badge bg-primary`
    
- `badge bg-danger`
    
- `badge rounded-pill`
    

Sono pensati per essere incollati accanto a:

- titoli
    
- link
    
- bottoni
    
- icone
    

Supportano anche il conteggio dinamico con JS.

---

### **7. Differenza tra badge e alert**

|Componente|A cosa serve|Importanza|Effetto visivo|
|---|---|---|---|
|**Alert**|Comunicare qualcosa di importante|ALTA|Grande, colorato, a tutta larghezza|
|**Badge**|Aggiungere un’informazione contestuale|BASSA|Piccolo, discreto, incollato a un elemento|

Quindi:

- **Alert** → “Fermati, ci sono cose da sapere”
    
- **Badge** → “Aggiungo un dettaglio a ciò che stai già guardando”
    

---

### **8. Cosa portarti a casa dalla lezione**

- I badge sono piccoli indicatori visivi che arricchiscono un elemento.
    
- Comunicano numeri, stato, categorie, etichette.
    
- Sono discreti, ma immediatamente riconoscibili.
    
- Hanno quasi sempre forma circolare o “pill”.
    
- Sono nati da Facebook e ora esistono ovunque.
    
- Sono facili da costruire: un semplice `span` con stile.
    

---

### **9. Prossima lezione**

Adesso passiamo alle **breadcrumb**, le “briciole di pane” che servono per capire _dove ci troviamo_ all’interno di una struttura complessa di pagine.