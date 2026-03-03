## **Lezione 4: Architetture e Scenari**

### **1. Introduzione**

Le diverse **architetture di firewall** riflettono differenti livelli di protezione e complessità.  
Ogni modello combina, in modo più o meno integrato, **packet filtering**, **proxy** e **bastion host**.  
L’obiettivo è creare **barriere multilivello** che riducano la probabilità di compromissione di un singolo componente.

---

## **2. Screened Host Firewall (single-homed)**

### **Descrizione**

È la forma più semplice di architettura combinata.  
Un **packet filter** controlla il traffico tra rete interna ed esterna, permettendo solo:

- pacchetti dall’esterno diretti al **bastion host** (che funge da proxy);
    
- pacchetti diretti a un **server pubblico** (es. web server);
    
- pacchetti in uscita provenienti dal bastion host verso l’esterno.
    

### **Funzionamento**

Il traffico viene ispezionato **due volte**:

1. dal packet filter;
    
2. dal proxy sul bastion host.
    

In caso di compromissione del packet filter, il bastion host resta comunque una barriera di difesa.

```
Rete Esterna → Packet Filter → Bastion Host (Proxy) → Rete Interna
```

---

## **3. Screened Host Firewall (dual-homed)**

Il **dual-homed bastion host** dispone di **due interfacce di rete** (una interna e una esterna).  
Ogni pacchetto deve **fisicamente attraversare il bastion host**, riducendo il rischio che un filtro compromesso consenta l’accesso diretto alla rete interna.

### **Vantaggi**

- Maggiore isolamento tra le reti.
    
- Nessun pacchetto può transitare senza passare dal bastion host.
    

### **Svantaggi**

- Maggior complessità di gestione.
    
- Prestazioni inferiori rispetto al single-homed.
    

---

## **4. Screened Subnet Firewall**

Questa è l’architettura **più sicura** tra le precedenti.  
Introduce una **DMZ (Demilitarized Zone)** — una zona intermedia accessibile da entrambe le reti ma che **non permette transito diretto**.

### **Struttura**

```
Rete Esterna → Packet Filter → DMZ (Bastion Host, Web Server) → Packet Filter → Rete Interna
```

- La DMZ ospita i server pubblici (web, mail, FTP).
    
- I filtri alle due estremità controllano rigidamente il traffico in ingresso e uscita.
    
- Nessun pacchetto può attraversare direttamente la DMZ.
    

> È il modello più usato nelle architetture aziendali moderne.

---

## **5. FTP Bounce Attack**

### **5.1 Comando PORT**

Il protocollo FTP utilizza il comando:

```
PORT h1,h2,h3,h4,p1,p2
```

dove:

- $(h1,h2,h3,h4)$ rappresentano l’indirizzo IP del client;
    
- $(256 × p1 + p2)$ indica la porta su cui il client riceverà la connessione.
    

Esempio:  
`PORT 159,149,10,5,4,1 → IP: 159.149.10.5 Porta: 1025`

---

### **5.2 Scenario di attacco**

- Dall’esterno sono ammesse connessioni verso l’**FTP Server** e il **Web Server**.
    
- Il **Telnet Server** è accessibile **solo dall’interno**.
    

```
Attacker (159.149.10.5) → FTP Server → Web/Telnet Server (159.149.10.8)
```

---

### **5.3 Fasi dell’attacco**

1. L’attaccante si connette all’FTP server (anche in modalità anonima).
    
2. Invia:  
    `PORT 159,149,10,8,0,23`  
    forzando l’FTP server ad aprire una connessione verso **159.149.10.8:23** (porta Telnet).
    
3. Invia il comando `RETR`, che causa l’apertura della connessione.
    
4. Il **firewall perimetrale viene bypassato**.
    
5. È possibile eseguire comandi sul Telnet Server.
    

---

### **5.4 Considerazioni di sicurezza**

- Uno **stateful firewall** non rileva l’anomalia (la connessione è “legittima”).
    
- Un **FTP proxy** invece riconosce l’uso improprio del comando PORT e **blocca la sessione**.
    
- L’attacco (noto dal 1997) è oggi mitigato: i moderni server FTP impediscono il bounce.
    

> Il caso mostra come un firewall “consapevole dell’applicazione” sia cruciale per fermare attacchi logici.

---

## **6. Architetture di Firewall: Scelta e Criteri**

### **Principio teorico**

Più alto è il livello a cui opera il firewall:

- maggiore è il consumo di risorse;
    
- maggiore è la protezione offerta.
    

### **Osservazione di Marcus Ranum**

> “Gli utenti hanno votato per la trasparenza e le prestazioni invece che per la sicurezza; non c’è da sorprendersi dei risultati.”  
> _(Firewall Wizard Mailing List, 2000)_

### **Scelta ottimale**

- Non un singolo prodotto, ma **una combinazione di firewall** complementari.
    
- Preferire soluzioni che **supportano architetture multiple**.
    
- Diffidare dei prodotti “miracolosi” che promettono protezione totale.
    

---

## **7. Stealth Firewall**

### **Definizione**

Un **firewall invisibile**: privo di indirizzo IP, quindi **non direttamente attaccabile**.

### **Funzionamento**

- Intercetta fisicamente i pacchetti in transito.
    
- L’interfaccia opera in **modalità promiscua**, analizzando tutto il traffico.
    
- Nessun host può instaurare connessioni dirette verso di lui.
    

> È una difesa passiva e invisibile, adatta a reti ad alta criticità.

---

## **8. Storici Hacking dei Packet Filters**

### **Principali tecniche di evasione**

- I filtri TCP agivano solo sul **frammento 0** → bastava impostare il campo fragment = 1 per superare il filtro.
    
- Alcuni vecchi sistemi filtravano solo **porte < 1024** → applicazioni su porte alte passavano inosservate.
    
- I **Trojan** potevano superare il filtro tramite **NAT mismatch**.
    
- I **servizi ridirezionati** (port forwarding) diventavano vie di fuga.
    

> Le tecniche di evasione sfruttano le zone cieche della configurazione dei filtri.

---

## **9. Best Practices di Configurazione**

1. **Usare un Proxy**  
    – interrompe fisicamente il percorso di rete;  
    – aggiunge un livello di mediazione.
    
2. **Preferire Stateful Packet Filter**  
    – più robusti rispetto agli stateless.
    
3. **Politica Default Deny**  
    – disabilitare tutte le porte, abilitare solo quelle indispensabili.
    
4. **Bloccare la comunicazione dei Trojan interni**  
    – nessun processo locale deve poter aprire connessioni esterne senza controllo.
    
5. **Hardening del Sistema Operativo**  
    – installare patches e aggiornamenti.
    
6. **Controllo periodico delle vulnerabilità**  
    – utilizzare solo stack protocollari sicuri e collaudati.
    

---

## **10. Conclusione**

Le architetture di firewall evolvono da modelli **semplici e trasparenti** (single-homed) a configurazioni **complesse e multilivello** (screened subnet con DMZ).  
La sicurezza cresce insieme alla consapevolezza applicativa e alla segmentazione fisica della rete.

> In definitiva, **non esiste il firewall perfetto**: esiste **una buona architettura**, composta da più livelli che si proteggono a vicenda.


---