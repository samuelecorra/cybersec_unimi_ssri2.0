# **UD2 – Configurazione del firewall (metodo d'esame)**

Questa unità insegna a tradurre una **topologia** e una **politica di sicurezza** nella **tabella di filtraggio** richiesta dall'esame di laboratorio. La tabella ha sempre otto colonne — *Direzione, IP Sorgente, IP Destinazione, Protocollo, Porta Sorgente, Porta Destinazione, Flag ACK, Azione* — ed è un **packet filter stateless**: per questo la gestione del **traffico di ritorno tramite il bit ACK** è il cuore del metodo. Le lezioni completano quanto già visto in [M5/UD1/L3–L4](../../M5/UD1/L4%20-%20Access%20Control%20List%20%28ACL%29.md) (packet filtering, ACL) e [M5/UD2/L5–L6](../../M5/UD2/L5%20-%20Netfilter%20e%20IPTables.md) (Netfilter/iptables), collegando il metodo astratto alla sintassi reale.

---

## **Lezione 1: Metodologia della tabella di filtraggio (le 8 colonne e il flag ACK)**

### **1. Che cosa modella la tabella**

La tabella descrive le regole di un **packet filter** posto sul firewall: per ogni pacchetto che attraversa il dispositivo, le regole vengono valutate **in ordine, dall'alto verso il basso**, e si applica la **prima** che combacia (*first match wins*, come le ACL Cisco). Ciò che non è esplicitamente permesso viene **negato** dalla regola finale di *default deny*. Ogni regola confronta i campi del pacchetto — indirizzi IP, protocollo, porte, flag TCP — con i valori specificati, ed esegue l'azione (permit/deny).

> 📌 È un filtro **stateless**: il firewall non tiene memoria delle connessioni aperte. Non potendo "ricordare" che una connessione è stata avviata dall'interno, deve **riconoscere il traffico di ritorno dai campi del singolo pacchetto** — ed è qui che entra in gioco il flag ACK.

---

### **2. Le otto colonne, una per una**

| Colonna | Significato e valori tipici |
|---|---|
| **Direz.** | La direzione del pacchetto rispetto al firewall/alla rete: si indica il verso del flusso (es. *da LAN a DMZ*, *IN* dall'esterno, *OUT* verso l'esterno). Serve a legare la regola all'interfaccia giusta. |
| **IP Sorg** | Indirizzo (o sottorete) di partenza: un host (`10.0.4.11`), una rete (`10.0.0.0/24`) o `any`. |
| **IP Dest** | Indirizzo (o sottorete) di destinazione, con gli stessi formati. |
| **Protoc.** | `TCP`, `UDP`, `ICMP` (o `any`). |
| **Porta Sorg** | Porta di partenza. Per un **client** è una porta effimera, che si scrive `>1023` (o `1024:`, "porte alte"). Per la **risposta di un server** è la porta del servizio. |
| **Porta Dest** | Porta di arrivo. Per la **richiesta a un servizio** è la porta nota (80, 443, 21…); per la **risposta al client** è la porta effimera (`>1023`). |
| **Flag ACK** | Stato del bit ACK del segmento TCP. `0` = pacchetto **senza ACK** (è un SYN, cioè *apertura* di una connessione). `1` = pacchetto **con ACK** (appartiene a una connessione **già avviata**). Spesso indicato come `-`/`any` quando irrilevante (UDP, ICMP) o quando si vuole coprire entrambi. |
| **Azione** | `permit`/`allow` oppure `deny`/`drop`. |

> ⚠️ La colonna **Porta Sorgente** è quella che gli studenti sbagliano più spesso: un client non parte mai dalla porta del servizio, ma da una **porta effimera** assegnata dal sistema operativo (convenzionalmente `>1023`). La porta "nota" (80, 443, …) compare come **destinazione** nella richiesta e come **sorgente** nella risposta.

---

### **3. Il flag ACK: il meccanismo che sostituisce lo stato**

In TCP, il **primo** segmento di una connessione è un **SYN con ACK=0**; **tutti** i segmenti successivi (a partire dal SYN-ACK di risposta) hanno **ACK=1**. Questa asimmetria consente a un filtro stateless di distinguere *chi ha aperto* la connessione:

- Un pacchetto **ACK=0** in ingresso è un **tentativo di aprire** una nuova connessione dall'esterno.
- Un pacchetto **ACK=1** in ingresso può solo essere la **risposta** a una connessione aperta dall'interno.

La regola d'oro: **il traffico di ritorno si autorizza solo con `ACK=1`**. Così una risposta legittima passa, ma un host esterno **non** può iniziare una connessione verso l'interno (il suo SYN, con ACK=0, non trova alcuna regola che lo accetti e viene negato dal default deny). È l'equivalente stateless dell'operatore `established` delle ACL Cisco (vedi [M5/UD1/L4](../../M5/UD1/L4%20-%20Access%20Control%20List%20%28ACL%29.md) §17).

> 💡 Perché non basta una sola regola? Perché una connessione TCP è **bidirezionale**: i pacchetti viaggiano in *entrambi* i versi. Il filtro vede separatamente la richiesta (un verso) e la risposta (il verso opposto), e ciascun verso attraversa il firewall come flusso a sé. Servono quindi, di norma, **due righe per ogni connessione consentita**.

---

### **4. Lo schema base: client interno → servizio esterno**

Consideriamo un host interno `10.0.0.10` che deve raggiungere un server web esterno in HTTPS. Le due righe necessarie:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| OUT | 10.0.0.10 | any | TCP | >1023 | 443 | 0 / any | permit |
| IN | any | 10.0.0.10 | TCP | 443 | >1023 | 1 | permit |

- La prima riga lascia **uscire la richiesta**: dalla porta effimera del client verso la porta 443 del server.
- La seconda lascia **rientrare solo la risposta**: dalla porta 443 del server verso la porta effimera del client, **e soltanto se ACK=1**. Se un attaccante esterno provasse ad aprire una connessione verso `10.0.0.10` (SYN, ACK=0), non esisterebbe regola che lo consente.

> 📌 Modello mentale: **una connessione consentita = due righe** (richiesta con ACK 0/any nel verso di apertura; risposta con **ACK=1** nel verso opposto). La richiesta ha la porta nota come *destinazione*; la risposta ce l'ha come *sorgente*.

---

### **5. Servizio interno esposto: esterno → server interno**

Il caso simmetrico è un server interno che deve **ricevere** connessioni. Per un web server `10.0.4.11` esposto a Internet in HTTPS:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN | any | 10.0.4.11 | TCP | >1023 | 443 | 0 / any | permit |
| OUT | 10.0.4.11 | any | TCP | 443 | >1023 | 1 | permit |

Qui è **l'esterno** ad aprire la connessione (la richiesta entra, ACK 0/any), e il server risponde (uscita con ACK=1). Notare la differenza rispetto al caso precedente: chi *inizia* determina in quale riga sta l'ACK=0/any e in quale l'ACK=1.

> ⚠️ Chi apre la connessione è la chiave di tutto. "Il web server è esposto a Internet" ⇒ l'iniziativa è di Internet ⇒ la riga di richiesta è **in ingresso** con ACK 0/any. "I PC interni navigano" ⇒ l'iniziativa è interna ⇒ la riga di richiesta è **in uscita**. Sbagliare chi inizia significa aprire il firewall nel verso sbagliato.

---

### **6. UDP e ICMP: niente ACK**

UDP e ICMP non hanno il bit ACK. Per un servizio UDP (es. DNS verso un resolver esterno) si scrivono comunque due righe (richiesta e risposta), ma nella colonna Flag ACK si mette `-`/`any`, distinguendo i due versi solo tramite porte e direzione:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| OUT | 10.0.0.0/24 | any | UDP | >1023 | 53 | - | permit |
| IN | any | 10.0.0.0/24 | UDP | 53 | >1023 | - | permit |

> 💡 Senza il bit ACK, un filtro stateless è intrinsecamente più debole con UDP: non può distinguere una "risposta" da una nuova richiesta in arrivo. È uno dei motivi per cui i firewall reali sono *stateful* (vedi [M5/UD2/L2](../../M5/UD2/L2%20-%20New%20Generation%20Packet%20Filtering.md)); nell'esercizio d'esame ci si limita a permettere il verso di ritorno sulla porta corretta.

---

### **7. Procedura per compilare la tabella (metodo)**

1. **Elenca le comunicazioni consentite** dalla policy, una per una, esplicitando **chi inizia** e **verso quale servizio/porta**.
2. Per ciascuna, scrivi **la riga di richiesta** (porta nota in destinazione, ACK 0/any, direzione = verso di apertura) e **la riga di risposta** (porta nota in sorgente, ACK=1, direzione opposta).
3. Usa **sottoreti** (`/24`) quando la regola vale per un'intera rete, host singoli quando è mirata.
4. Traduci "solo X può…" in un **IP sorgente** ristretto; "…da fuori" in sorgente `any` dall'interfaccia esterna; "servizio esposto" in una richiesta **in ingresso**.
5. Chiudi **sempre** con la regola di *default deny* (tutto ciò che non è stato permesso è vietato).

> ✅ **Ricapitolando** — La tabella è un packet filter stateless valutato *first-match* con *default deny* finale. Poiché non c'è stato, il traffico di ritorno si riconosce dal **flag ACK**: richiesta con ACK 0/any nel verso di chi apre, risposta con **ACK=1** nel verso opposto — due righe per connessione. La porta nota del servizio è **destinazione** nella richiesta e **sorgente** nella risposta; il client usa porte effimere `>1023`. Stabilire **chi inizia** la connessione determina il verso delle righe ed è il passaggio da cui dipende la correttezza dell'intera soluzione.

Prossima: [[L2 - DMZ ed esposizione di servizi verso Internet]].
