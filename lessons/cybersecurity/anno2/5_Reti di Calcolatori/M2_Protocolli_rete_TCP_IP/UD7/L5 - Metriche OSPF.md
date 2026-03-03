# **Lezione 5: Metriche OSPF**

---

### **1. Introduzione**

In questa lezione analizziamo le **metriche** che OSPF utilizza per scegliere il percorso migliore all’interno di una rete.  
Ricorda che OSPF (_Open Shortest Path First_) è un protocollo **link state**, quindi ogni router conosce la topologia completa della rete e calcola il **cammino di costo minimo** verso ogni destinazione.

> L’obiettivo di OSPF non è solo raggiungere la destinazione, ma farlo attraverso il percorso **più efficiente**, secondo criteri di costo predefiniti o dinamici.

---

### **2. Struttura dell’LSA (Link State Advertisement)**

Ogni router OSPF descrive i propri collegamenti tramite pacchetti **LSA (Link State Advertisement)**.  
Ogni LSA include:

![](imgs/Pasted%20image%2020260225154315.png)

|Campo|Descrizione|
|---|---|
|**LS Age**|Tempo di vita del pacchetto, simile al TTL.|
|**Link State ID**|Identificatore del collegamento.|
|**Advertising Router**|Indirizzo del router che ha generato l’LSA.|
|**Sequence Number**|Numero di sequenza per verificare la versione più recente.|
|**Checksum**|Controllo d’errore sull’LSA.|
|**Length**|Lunghezza totale del pacchetto.|
|**Number of Links**|Numero di collegamenti descritti nel pacchetto.|
|**Link ID / Link Data**|Identificano ciascun collegamento e le informazioni associate.|
|**Metric**|Valore numerico che indica il costo del collegamento.|

> Ogni router diffonde questi pacchetti ai vicini; l’insieme di tutti gli LSA forma la **Link State Database**, base su cui OSPF costruisce la topologia completa.

---

### **3. Reinizializzazione di un nodo**

Quando un router OSPF si **riavvia**, il suo numero di sequenza viene **azzerato**.  
Il processo di reinizializzazione segue questi passi:

1. Il router invia a tutti i vicini il proprio stato di collegamento con **numero di sequenza = 0**.
    
2. Manda una **richiesta LSA** per aggiornare la sua tabella con i dati più recenti.
    
3. I vicini rispondono inviando le loro versioni aggiornate, incluso l’ultimo LSA conosciuto per quel router.
    
4. Il nodo aggiorna il proprio **numero di sequenza** e reintegra le informazioni nella rete.
    
5. Ogni nuova informazione ricevuta viene poi **floodata** (diffusa) a tutti.
    

> In questo modo, la rete si riconfigura rapidamente anche dopo un crash di un router.

---

### **4. Scoperta dei vicini**

I router OSPF eseguono un **multicast di pacchetti HELLO** su tutte le interfacce abilitate.
Quando due router condividono un collegamento, possono diventare **vicini (neighbors)**.

![](imgs/Pasted%20image%2020260225154334.png)

Esempio:

```
10.1.10.1  ↔  10.1.10.2
```

- Dopo aver ricevuto il messaggio _HELLO_, ciascun router risponde con un “I heard you” (“ti ho sentito”), confermando la relazione.
    
- Una volta stabilita la vicinanza, i due router **scambiano i loro database OSPF** per sincronizzarsi.
    

> Il meccanismo di scoperta dei vicini è la base per costruire e mantenere le **adiacenze** tra router.

---

### **5. Scoperta di adiacenza e sincronizzazione**

Dopo la fase di scoperta, i router avviano la **sincronizzazione dei database**:

![](imgs/Pasted%20image%2020260225154408.png)

1. Iniziano lo scambio inviando una **descrizione del database vuota**, contenente solo le intestazioni LSA.
    
2. Il router ricevente risponde confermando la ricezione.
    
3. Entrambi quindi inviano **richieste LSA** per ottenere i dati mancanti.
    
4. Al termine dello scambio, i database risultano **sincronizzati**.
    

> Le adiacenze assicurano che ogni router OSPF disponga di informazioni coerenti sulla rete, condizione essenziale per il calcolo dei percorsi minimi.

---

### **6. Sicurezza in OSPF**

OSPF non è immune da problemi di sicurezza.  
Un attaccante potrebbe tentare di **iniettare LSA falsi**, simulando collegamenti o router inesistenti.

Per evitare ciò:

- ogni router accetta solo **LSA provenienti da vicini legittimi**;
    
- si applica un meccanismo di **autenticazione** (password o crittografia).
    

> Un router compromesso potrebbe pubblicare **costi artificialmente bassi**, attirando tutto il traffico verso di sé e creando una sorta di “buco nero” di rete.

---

### **7. Tipi di metriche**

Le **metriche OSPF** rappresentano il **costo** di attraversamento di un collegamento.  
Possono essere calcolate in diversi modi.

#### **a) Metrica statica**

Inizialmente, alcune reti (es. Juniper) usavano un **costo fisso** basato sul **ritardo medio** del collegamento.

#### **b) Metrica dinamica**

In passato (es. ARPANET), la metrica era calcolata in base alla **lunghezza della coda del router**, come misura della congestione.  
Tuttavia, si è scoperto che questo metodo è **troppo reattivo** e porta a instabilità.

Per rendere la metrica dinamica più stabile, si impongono dei **vincoli di variazione**:

- Il costo di un collegamento **non può variare di più di un fattore 7** tra il collegamento più veloce e quello più lento.
    
- Tra due misurazioni successive, la variazione del costo **non deve superare un fattore 3**.
    
- Gli aggiornamenti vengono inviati **solo se il costo cambia oltre una certa soglia**.
    
- La metrica dipende principalmente dal **livello di utilizzo medio o alto del link**.
    

> In pratica, la metrica OSPF deve essere **sensibile ma non nervosa**: reagisce ai cambiamenti reali senza oscillare in modo caotico.

---

### **8. Type of Service (TOS)**

Ogni pacchetto IP può specificare un **Tipo di Servizio (TOS)**, indicando che tipo di trattamento desidera nella rete (es. priorità alta, bassa latenza, throughput elevato).  
OSPF consente di assegnare **metriche diverse in base al TOS**, adattando il percorso alle esigenze del traffico.

Esempio:

- un flusso _streaming_ può usare un collegamento con **bassa latenza**;
    
- un backup di file può preferire **alta capacità di banda**.
    

> In pratica, però, questa funzione è **raramente utilizzata**, perché la gestione del TOS è oggi demandata a protocolli di qualità del servizio (QoS) di livello superiore.

---

### **9. Conclusione**

Le metriche in OSPF servono a rappresentare il “**costo virtuale**” di attraversamento della rete.  
Possono essere statiche o dinamiche, ma devono sempre garantire **stabilità e coerenza** nel calcolo dei percorsi.

> OSPF, con il suo meccanismo di LSA, flooding e metriche controllate, riesce a bilanciare **precisione, efficienza e affidabilità**, mantenendo una rete dinamica sempre coerente.


---

![](imgs/Pasted%20image%2020251125073117.png)

