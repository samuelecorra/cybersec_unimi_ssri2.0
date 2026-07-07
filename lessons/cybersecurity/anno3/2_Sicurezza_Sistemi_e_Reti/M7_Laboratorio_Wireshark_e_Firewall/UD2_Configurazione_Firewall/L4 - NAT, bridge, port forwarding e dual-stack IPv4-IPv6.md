## **Lezione 4: NAT, bridge, port forwarding e dual-stack IPv4/IPv6**

L'ultima lezione copre gli elementi che compaiono nelle tracce più recenti: la traduzione degli indirizzi (NAT), gli access point in bridge, l'esposizione di servizi tramite port forwarding e la presenza contemporanea di IPv4 e IPv6.

### **1. NAT: perché e dove**

Il **NAT** (*Network Address Translation*, vedi [M5/UD2/L5 – Netfilter e IPTables](../../M5/UD2/L5%20-%20Netfilter%20e%20IPTables.md) §4) riscrive gli indirizzi dei pacchetti che attraversano il firewall. Serve in due situazioni:

- **SNAT / Masquerade** (source NAT): gli host interni con IP privati (RFC 1918) escono verso Internet mascherati dietro l'**unico IP pubblico** del firewall. È il caso classico della LAN che naviga: `iptables -t nat -A POSTROUTING -o wan0 -s 10.0.0.0/24 -j MASQUERADE`.
- **DNAT / Port forwarding** (destination NAT): una connessione che arriva sull'IP pubblico del firewall su una certa porta viene **rediretta** verso un server interno. È il modo con cui un servizio interno diventa "esposto a Internet".

> 📌 Nella tabella di filtraggio astratta il NAT spesso non ha una colonna dedicata: si ragiona sugli **effetti**. Ma quando la traccia dice "il server è esposto a Internet sulla porta X", dietro c'è un DNAT dall'IP pubblico del firewall verso l'IP privato del server (vedi §3).

---

### **2. Access point in "bridge mode" e la sottorete guest**

Un **access point in bridge mode** collega la rete wireless alla rete cablata **senza fare da router/NAT**: i dispositivi Wi-Fi appaiono logicamente sulla stessa rete a cui l'AP è collegato. Nelle tracce d'esame, però, l'AP ha spesso due indirizzi — uno lato Wi-Fi (`wlan0 192.168.4.1`) e uno lato firewall (`eth0 10.0.1.10`) — e i telefoni guest stanno su `192.168.4.0/24`. In pratica la sottorete guest è **separata** e il suo traffico verso il resto passa dall'AP e poi dal firewall.

Ai fini delle regole contano due cose:

- **Chi genera il traffico**: i dispositivi guest (es. `192.168.4.0/24`) oppure l'AP stesso (`10.0.1.10`), che sono soggetti diversi con permessi diversi.
- **La policy di isolamento**: "*i dispositivi connessi all'AP possono comunicare solo con Internet*" ⇒ dalla sottorete guest si permette **solo** il traffico verso Internet, negando (per default) l'accesso a LAN e servizi.

Esempio — guest solo verso Internet, e AP che registra gli accessi sul servizio di autenticazione (`10.0.4.11`, HTTPS):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Guest→Internet | 192.168.4.0/24 | any (Internet) | TCP | >1023 | 80,443 | 0/any | permit |
| Internet→Guest | any | 192.168.4.0/24 | TCP | 80,443 | >1023 | 1 | permit |
| AP→Servizi (auth) | 10.0.1.10 | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| Servizi→AP (auth) | 10.0.4.11 | 10.0.1.10 | TCP | 443 | >1023 | 1 | permit |

> ⚠️ Distinzione sottile ma richiesta: **i client guest** (`192.168.4.x`) parlano solo con Internet, mentre **l'AP** (`10.0.1.10`) parla con il servizio di autenticazione per registrare gli accessi. Sono due regole con IP sorgente diversi. Confonderli — permettendo ai guest di raggiungere il servizio interno — violerebbe l'isolamento richiesto.

---

### **3. Port forwarding: esporre un servizio interno**

"*Il server di backup è esposto a Internet sulla porta 5000*" si realizza con un **DNAT**: le connessioni che arrivano sull'IP pubblico del firewall (`wan0`) alla porta 5000 vengono rigirate al server interno `10.0.4.15`. In iptables:

```bash
iptables -t nat -A PREROUTING -i wan0 -p tcp --dport 5000 -j DNAT --to-destination 10.0.4.15:5000
iptables -A FORWARD -p tcp -d 10.0.4.15 --dport 5000 -m state --state NEW,ESTABLISHED -j ACCEPT
```

Nella tabella astratta si esprime come una richiesta **in ingresso** verso il server sulla porta 5000, con la risposta di ritorno (ACK=1):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Internet→Servizi | any | 10.0.4.15 | TCP | >1023 | 5000 | 0/any | permit |
| Servizi→Internet | 10.0.4.15 | any | TCP | 5000 | >1023 | 1 | permit |

> 💡 Che si parli di IP pubblico del firewall (prima del DNAT) o di IP privato del server (dopo il DNAT) è una questione di *punto di vista*: la sostanza è che dall'esterno si può raggiungere **solo** quel server e **solo** su quella porta. Nella soluzione conviene indicare l'IP del server come destinazione e annotare che, a monte, il firewall fa port forwarding dalla propria interfaccia pubblica.

---

### **4. Dual-stack IPv4 / IPv6**

Alcune topologie assegnano a ogni nodo **sia un IPv4 sia un IPv6** (es. `10.0.1.10/24` e `fc00:1::10/64`). Concettualmente le regole sono **le stesse**: cambia solo la famiglia di indirizzi. Nella pratica dei sistemi Linux, però, IPv4 e IPv6 hanno **due strumenti distinti**: `iptables` per IPv4 e **`ip6tables`** per IPv6 (vedi [M5/UD2/L5](../../M5/UD2/L5%20-%20Netfilter%20e%20IPTables.md) §1). Filtrare solo l'IPv4 lasciando aperto l'IPv6 è un errore classico e pericoloso.

Nella tabella d'esame si può procedere in due modi, a seconda di cosa chiede la traccia: scrivere le regole **una volta per famiglia** (duplicando le righe con gli indirizzi `fc00:…`), oppure indicare host/reti in forma neutra e annotare che vanno replicate in `ip6tables`. Le porte e la logica (chi apre, flag ACK) restano identiche.

> ⚠️ `fc00::/7` è il blocco degli indirizzi IPv6 **Unique Local** (l'equivalente degli IP privati RFC 1918). Come per l'IPv4 privato, questi indirizzi non sono instradabili su Internet: se la traccia prevede uscita verso l'esterno serve comunque un meccanismo di traduzione/instradamento, e comunque il filtraggio IPv6 va scritto esplicitamente con `ip6tables`.

---

### **5. Checklist finale per l'esercizio firewall**

Prima di consegnare la tabella, verifica:

1. Ogni comunicazione consentita dalla policy ha **entrambe** le righe (richiesta + risposta con ACK=1)?
2. Hai stabilito correttamente **chi inizia** ogni connessione (specie FTP attivo e SMTP)?
3. Le porte note sono in **destinazione** nella richiesta e in **sorgente** nella risposta?
4. La parola "**solo**" è stata tradotta in restrizioni di IP sorgente e/o porta?
5. Le sottoreti che devono restare isolate **non** sono raggiunte da alcuna regola di permit?
6. C'è un servizio esposto che richiede **port forwarding** (DNAT)? Hai puntato l'host e la porta giusti?
7. Se la rete è **dual-stack**, hai coperto anche l'IPv6?
8. Hai chiuso con il **default deny**?

> ✅ **Ricapitolando** — Il **SNAT/Masquerade** maschera la LAN dietro l'IP pubblico in uscita; il **DNAT/port forwarding** espone un servizio interno su una porta pubblica (es. backup sulla 5000). Un **AP in bridge** tiene i guest su una sottorete separata: distingui i client guest (solo Internet) dall'AP (che parla col servizio di autenticazione). In **dual-stack** la logica è identica ma va replicata con `ip6tables`, ricordando che `fc00::/7` è l'IPv6 privato. La checklist finale evita gli errori tipici: righe di ritorno mancanti, iniziativa sbagliata, "solo" non applicato, IPv6 dimenticato, default deny assente.

Precedente: [[L3 - Servizi multi-connessione (FTP attivo-passivo, RPC, SMTP)]] · Torna alla cheat-sheet Wireshark: [[L6 - Cheat-sheet operativa d'esame]].
