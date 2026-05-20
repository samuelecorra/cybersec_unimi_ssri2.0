# **Lezione 1: Strutturazione gerarchica delle reti locali e protocollo VTP**

---

### **1. Strutturazione gerarchica delle reti**

Una rete strutturata a livello 2 è una **struttura gerarchica composta da numerose VLAN collegate tra loro grazie ai protocolli di trunking**.  
Lo scopo fondamentale è quello di realizzare un **disaccoppiamento** tra il progetto logico delle VLAN aziendali — assegnate magari alle varie funzioni dell'organizzazione — e gli switch fisici che si hanno a disposizione.

In altre parole: si progetta prima la suddivisione in VLAN (es. aule, docenti, amministrazione), e poi si va a "calare" questa divisione logica in una struttura gerarchica fisica fatta di switch.

Questa architettura si articola tipicamente in **tre livelli funzionali**:

1. **Core layer** – il "cuore" della rete:
    - collega tra loro i principali segmenti di rete;
    - offre elevata velocità, ridondanza e disponibilità.

2. **Distribution layer** – gestisce la distribuzione del traffico:
    - collega gli switch di accesso al core;
    - applica politiche di filtraggio, sicurezza e QoS (Quality of Service).

3. **Access layer** – fornisce la connessione diretta agli utenti finali:
    - collega i computer, le stampanti e gli altri dispositivi di rete;
    - gestisce l'assegnazione alle VLAN.

---

### **2. Esempio di rete gerarchica**

Per capire bene il concetto di rete strutturata, il professore fa riferimento a una figura in cui si hanno **2 core switch, 4 distribution switch e sei access switch**.  
Su questi switch vengono create le VLAN: ci si aspetta che le VLAN con lo stesso numero, create su switch di distribuzione differenti, corrispondano in realtà a **un'unica rete logica**.

![](imgs/Pasted%20image%2020260213011628.png)

L'obiettivo è garantire che le porte assegnate, ad esempio, alla VLAN 10 su switch diversi possano **parlarsi tra loro**, anche se si trovano su switch fisici distinti.

Per realizzare questo:

- Gli **access switch** assicurano la connettività tra porte della **stessa VLAN** (stesso numero) su switch di distribuzione differenti, attraverso **collegamenti di trunking**. Ad esempio, uno switch di accesso nella parte sinistra della figura garantisce che il primo e il secondo switch di distribuzione si parlino, permettendo di avere porte sulla VLAN 10 su entrambi.

- A loro volta, gli access switch possono essere collegati tra di loro, ma questo non è sufficiente per garantire la connettività tra **VLAN diverse**. Per assicurare la connettività tra VLAN differenti, a prescindere dal loro mapping sugli switch di distribuzione, si usano i **core switch**, che collegano tra loro gli switch di accesso.

In sintesi:

- **access switch** → connettività tra porte della stessa VLAN su distribution switch diversi;
- **core switch** → connettività tra VLAN diverse, collegando tra loro gli access switch.

Questa struttura:

- migliora la **manutenibilità** della rete;
- consente un **controllo centralizzato delle configurazioni**;
- permette di partire dalla progettazione logica delle VLAN e calare il tutto su una struttura fisica gerarchica.

---

### **3. Problema della configurazione manuale**

Supponiamo che la rete abbia **10 VLAN**, ciascuna corrispondente a un'unità funzionale dell'organizzazione.

Per farle funzionare correttamente, bisogna:

- definire le **stesse VLAN su tutti gli switch**;
- indicare **quali porte appartengono a quale VLAN**.

**Esempio concreto** (polo universitario):

- una VLAN potrebbe corrispondere alle **aule didattiche** → tutte le porte di tutte le aule, su qualunque switch fisico si trovino, devono stare nella stessa VLAN;
- un'altra VLAN per gli **studi docenti**;
- un'altra per i **laboratori studenti**;
- e così via per le 10 unità funzionali.

Su ogni switch fisico bisogna quindi specificare quali porte appartengono alla VLAN delle aule, quali a quella degli studi docenti, ecc. — nonostante un'aula e uno studio docenti possano essere sullo stesso switch fisico perché magari contigui geograficamente.

![](imgs/Pasted%20image%2020260213012133.png)

La tabella in figura mostra una ripartizione tipica di VLAN aziendali:

- **VLAN 10** → funzione IT;
- **VLAN 20** → personale;
- **VLAN 30** → contabilità;
- ulteriori VLAN per magazzino Milano, magazzino Roma, ecc.

Ogni VLAN corrisponde a un numero e va mappata sugli insiemi di porte fisiche sugli switch.

Quando il numero di switch cresce, questa gestione manuale diventa:

- **complessa** → serve tempo e precisione;
- **rischiosa** → un solo errore può interrompere la connettività;
- **pericolosa per la sicurezza** → un VLAN ID errato può esporre dati sensibili a utenti non autorizzati.

Il professore sottolinea questo aspetto con un esempio: se per errore un certo posto di laboratorio studenti viene assegnato alla VLAN delle aule, si crea un **buco di sicurezza** — diventa possibile, ad esempio, attaccare uno sniffer e intercettare pacchetti contenenti il compito d'esame, proprio perché una porta che non doveva esserci è finita in quella VLAN.

Inoltre, modifiche o aggiornamenti su uno switch devono essere **replicati manualmente su tutti gli altri**, aumentando il rischio di **incoerenze** nella configurazione.

---

### **4. Introduzione al protocollo VTP**

Per risolvere questo problema nasce il **VTP (VLAN Trunking Protocol)**.  
Il suo scopo è **centralizzare la gestione delle VLAN** in reti di grandi dimensioni.

Il principio di base è quello di **partizionare gli switch aziendali in gruppi**, con l'idea che gli switch appartenenti a gruppi diversi non debbano scambiarsi informazioni sulle VLAN — e questo significa che switch in domini diversi **non hanno VLAN in comune**.

Gli switch vengono quindi organizzati in **domini di amministrazione** detti **VTP domain**:

- gli switch **dello stesso dominio** si scambiano le configurazioni VLAN;
- gli switch di **domini diversi** **non condividono alcuna informazione**.

---

### **5. Struttura di un dominio VTP**

All'interno di un dominio VTP, gli switch possono assumere **tre ruoli operativi**:

![](imgs/Pasted%20image%2020260213012537.png)

1. **VTP Server**
    - contiene la configurazione principale delle VLAN;
    - propaga automaticamente le modifiche a tutti gli switch del dominio (i VTP client);
    - i messaggi VTP vengono **time-stampati**, così ogni switch che li riceve può confrontarli con l'ultimo aggiornamento ricevuto e tenere il più recente.

2. **VTP Client**
    - riceve e applica le modifiche inviate dal server;
    - non può creare né eliminare VLAN localmente;
    - conserva una copia della configurazione aggiornata.

3. **VTP Transparent**
    - **fa parte del dominio** e riceve i messaggi VTP del server;
    - **propaga** i messaggi agli switch con cui è collegato (li comunica agli altri);
    - ma **non li applica** — non modifica la propria configurazione sulla base dei messaggi VTP ricevuti;
    - le VLAN devono essere configurate **manualmente** su di esso.

---

### **6. Funzionamento del VTP**

Quando viene effettuata una modifica su un **VTP server** (ad esempio, una porta cambia VLAN), la variazione viene immediatamente:

1. **inserita in un messaggio VTP**,
2. **etichettata con un timestamp**,
3. **propagata a tutti gli switch del dominio**.

Gli switch che ricevono il messaggio confrontano il timestamp con la propria configurazione:

- se il messaggio è più recente → aggiornano la tabella VLAN;
- se è più vecchio → lo ignorano.

---

### **7. Esempio pratico**

Immaginiamo la rete rappresentata in figura, con quattro switch:

- **SW1** e **SW2** → hanno le stesse VLAN (es. VLAN 10, 20, 30, 40): alcune porte sulla VLAN 10, altre sulla VLAN 20, ecc.
- **SW3** → VTP Transparent, ha solo la VLAN 10 (ma poiché VLAN 10 è condivisa con tutti gli altri switch del dominio, SW3 appartiene comunque allo stesso dominio VTP)
- **SW4** → VTP Client

![](imgs/Pasted%20image%2020260213012554.png)

**Scenario**: supponiamo che su SW2 venga cambiata l'associazione di una porta (es. la porta 7 passa dalla VLAN 10 alla VLAN 20).  
SW2 notifica questo cambiamento a **SW1** e a **SW3**.

- **SW1** (client/server dello stesso dominio) → **riceve e applica** la modifica;
- **SW3** (transparent) → **propaga** l'informazione a **SW4**, ma **non la applica** alla propria configurazione; nel suo caso le modifiche vanno apportate manualmente;
- **SW4** (client) → **riceve da SW3** e **applica** la modifica.

È interessante osservare che SW3, pur avendo solo la VLAN 10, appartiene allo stesso dominio VTP di tutti gli altri, proprio perché quella VLAN è in comune con gli altri switch del dominio.

---

### **8. Vantaggi e criticità del VTP**

Il protocollo VTP **semplifica enormemente l'amministrazione** delle reti VLAN complesse, ma va utilizzato con cautela.

#### **Vantaggi**

- Gestione **centralizzata e coerente** delle VLAN.
- Propagazione automatica delle modifiche.
- Riduzione degli errori di configurazione manuale.
- Quando una porta cambia VLAN, il VTP server lo notifica a tutto il dominio, raggiungendo sicuramente gli switch che devono saperlo.

#### **Criticità**

- **Traffico inutile**: se si inserisce nello stesso dominio VTP switch che hanno **poche VLAN in comune**, le notifiche di cambiamento raggiungono anche switch che non hanno porte configurate su quella VLAN — si tratta di frame che viaggiano sulla banda di rete e che rubano spazio ai frame di traffico utente. Un'errata ripartizione dei domini VTP può quindi causare **inefficienze** nel protocollo di amministrazione.
- Se uno switch configurato come server viene aggiunto in modo improprio, può **sovrascrivere l'intera configurazione VLAN del dominio**.
- Le modifiche non filtrate possono introdurre **rischi di sicurezza** e **loop di configurazione**.

---

### **9. Considerazioni progettuali**

Per utilizzare correttamente VTP, bisogna:

- pianificare con attenzione i **domini di amministrazione**, inserendo nello stesso dominio solo switch con molte VLAN in comune;
- limitare il numero di **server VTP** (idealmente uno per dominio);
- utilizzare la modalità **transparent** nelle zone che non devono applicare automaticamente le modifiche.

Il principio guida è semplice:

> **VTP è potente, ma va trattato con rispetto.**  
> Un errore di configurazione può propagarsi a tutta la rete in pochi secondi.

---

### **10. Sintesi concettuale**

- Le reti moderne si organizzano in **architetture gerarchiche** (core, distribution, access), realizzando un **disaccoppiamento** tra progettazione logica delle VLAN e infrastruttura fisica.
- Gli **access switch** garantiscono la connettività intra-VLAN tra switch di distribuzione diversi (trunking); i **core switch** garantiscono la connettività inter-VLAN collegando gli access switch.
- Il **VTP** centralizza la gestione delle VLAN partizionando gli switch in **domini omogenei**.
- Ogni switch può essere **server**, **client** o **transparent**.
- Il VTP semplifica la vita dell'amministratore, ma **richiede disciplina e progettazione attenta** per evitare configurazioni errate o traffico spurio.
