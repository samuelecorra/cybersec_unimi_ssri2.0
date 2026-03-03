
# **Lezione 1: Strutturazione gerarchica delle reti locali e protocollo VTP**

### **1. Strutturazione gerarchica delle reti**

Con l’evoluzione delle reti locali (LAN), è diventato necessario **organizzare la connettività in modo gerarchico**, per migliorare **scalabilità, gestione e sicurezza**.  
Grazie a **VLAN** e **protocolli di trunking**, gli switch possono essere collegati tra loro **a livello 2**, realizzando un’architettura ordinata e facilmente estendibile.

Una rete moderna è solitamente suddivisa in **tre livelli funzionali**:

1. **Core layer** – rappresenta il “cuore” della rete:
    
    - collega tra loro i principali segmenti di rete;
        
    - offre elevata velocità, ridondanza e disponibilità.
        
2. **Distribution layer** – gestisce la distribuzione del traffico:
    
    - collega gli switch di accesso al core;
        
    - applica politiche di filtraggio, sicurezza e QoS (Quality of Service).
        
3. **Access layer** – fornisce la connessione diretta agli utenti finali:
    
    - collega i computer, le stampanti e gli altri dispositivi di rete;
        
    - gestisce l’assegnazione alle VLAN.
        

---

### **2. Esempio di rete gerarchica**

In una configurazione tipica:

- **2 core switch** assicurano la connessione centrale e ridondante;
    
- **6 distribution switch** raccolgono il traffico dei piani o dei reparti;
    
- **6 access switch** collegano direttamente i terminali degli utenti.

![](imgs/Pasted%20image%2020260213011628.png)

Ogni switch di accesso collega **più host**, che condividono la stessa VLAN o appartengono a VLAN diverse a seconda del ruolo (es. amministrazione, docenti, studenti…).

Questa struttura:

- migliora la **manutenibilità** della rete;
    
- consente un **controllo centralizzato delle configurazioni**;
    
- ma richiede un’attenta **gestione della coerenza** tra le impostazioni dei vari switch.
    

---

### **3. Problema della configurazione manuale**

Supponiamo che la rete abbia **10 VLAN**.  

![](imgs/Pasted%20image%2020260213012133.png)

Per farle funzionare correttamente, bisogna:

- definire le **stesse 10 VLAN su tutti gli switch**;
    
- indicare **quali porte appartengono a quale VLAN**.
    

Quando il numero di switch cresce, questa gestione manuale diventa:

- **complessa** → serve tempo e precisione;
    
- **rischiosa** → un solo errore può interrompere la connettività;
    
- **pericolosa per la sicurezza** → un VLAN ID errato può esporre dati sensibili a utenti non autorizzati.
    

Inoltre, modifiche o aggiornamenti su uno switch devono essere **replicati manualmente su tutti gli altri**, aumentando il rischio di **incoerenze** nella configurazione.

---

### **4. Introduzione al protocollo VTP**

Per risolvere questo problema nasce il **VTP (VLAN Trunking Protocol)**, sviluppato da **Cisco**.  
Il suo scopo è **centralizzare la gestione delle VLAN** in reti di grandi dimensioni.

Il principio di base è semplice:  
gli switch vengono organizzati in **domini di amministrazione** detti **VTP domain**, all’interno dei quali **le informazioni sulle VLAN vengono condivise automaticamente**.

- Gli switch **dello stesso dominio** si scambiano le configurazioni VLAN.
    
- Gli switch di **domini diversi** **non condividono alcuna informazione**.
    

---

### **5. Struttura di un dominio VTP**

All’interno di un dominio VTP, gli switch possono assumere **tre ruoli operativi**:

![](imgs/Pasted%20image%2020260213012537.png)

1. **VTP Server**
    
    - contiene la configurazione principale delle VLAN;
        
    - propaga automaticamente le modifiche a tutti gli altri switch del dominio;
        
    - mantiene un **timestamp** per ordinare i messaggi di aggiornamento.
        
2. **VTP Client**
    
    - riceve e applica le modifiche inviate dal server;
        
    - non può creare né eliminare VLAN localmente;
        
    - conserva una copia della configurazione aggiornata.
        
3. **VTP Transparent**
    
    - inoltra i messaggi VTP ricevuti, ma **non li applica**;
        
    - le VLAN devono essere configurate **manualmente** su di esso;
        
    - utile per collegare domini diversi o zone della rete isolate.
        

---

### **6. Funzionamento del VTP**

Quando una modifica viene effettuata su un **VTP server** (ad esempio, aggiunta di una nuova VLAN), la variazione viene immediatamente:

1. **inserita in un messaggio VTP**,
    
2. **etichettata con un timestamp**,
    
3. **propagata a tutti gli switch del dominio**.
    

Gli switch che ricevono il messaggio confrontano il timestamp con la propria configurazione:

- se il messaggio è più recente → aggiornano la tabella VLAN;
    
- se è più vecchio → lo ignorano.
    

---

### **7. Esempio pratico**

Immaginiamo una rete con quattro switch:

- **SW1 (server)**
    
- **SW2 (client)**
    
- **SW3 (transparent)**
    
- **SW4 (client)**

![](imgs/Pasted%20image%2020260213012554.png)

Quando si crea una nuova VLAN su **SW1**, i messaggi di aggiornamento vengono inviati a **SW2**, **SW3** e **SW4**.  
Tuttavia:

- **SW3**, essendo “transparent”, **inoltra ma non applica** i cambiamenti.
    
- **SW2** e **SW4**, in modalità “client”, **aggiornano automaticamente** la propria configurazione VLAN.
    

---

### **8. Vantaggi e criticità del VTP**

Il protocollo VTP **semplifica enormemente l’amministrazione** delle reti VLAN complesse, ma va utilizzato con cautela.

#### **Vantaggi**

- Gestione **centralizzata e coerente** delle VLAN.
    
- Propagazione automatica delle modifiche.
    
- Riduzione degli errori di configurazione manuale.
    

#### **Criticità**

- Se uno switch configurato come server viene aggiunto in modo improprio, può **sovrascrivere l’intera configurazione VLAN del dominio**.
    
- Inserire nello stesso dominio switch con **poche VLAN in comune** può generare **traffico inutile** di aggiornamento (broadcast VLAN info).
    
- Le modifiche non filtrate possono introdurre **rischi di sicurezza** e **loop di configurazione**.
    

---

### **9. Considerazioni progettuali**

Per utilizzare correttamente VTP, bisogna:

- pianificare con attenzione i **domini di amministrazione**;
    
- limitare il numero di **server VTP** (idealmente uno per dominio);
    
- utilizzare modalità **transparent** nelle zone che non devono propagare automaticamente modifiche.
    

Il principio guida è semplice:

> **VTP è potente, ma va trattato con rispetto.**  
> Un errore di configurazione può propagarsi a tutta la rete in pochi secondi.

---

### **10. Sintesi concettuale**

- Le reti moderne si organizzano in **architetture gerarchiche** (core, distribution, access).
    
- Le VLAN e i trunk permettono di segmentare logicamente la rete.
    
- Il **VTP** centralizza la gestione delle VLAN in domini omogenei.
    
- Ogni switch può essere **server**, **client** o **transparent**.
    
- Il VTP semplifica la vita dell’amministratore, ma **richiede disciplina e progettazione attenta** per evitare configurazioni errate o traffico spurio.