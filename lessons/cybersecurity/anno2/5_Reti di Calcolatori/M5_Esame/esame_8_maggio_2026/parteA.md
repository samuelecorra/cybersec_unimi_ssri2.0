# Esame di Reti di Calcolatori

**Prof. Ernesto Damiani**

**Parte A 8-5-2026**

---

**Esercizio 1 (5 punti)**

Calcolate il tempo richiesto in secondi per trasferire via TCP un file di 1 Gbyte tra un computer A e un computer B attraverso un cavo Ethernet 100 Mbps che collega direttamente i due computer. Fate tutte le ipotesi necessarie per rendere la vostra analisi il più precisa possibile.

---

**Esercizio 2 (8 punti)**

Considerando la seguente tabella di instradamento

| Dest. prefix    | Next Hop   |
| --------------- | ---------- |
| 192.24.1.0/24   | direct     |
| 192.24.192.0/18 | 192.24.1.5 |
| 0.0.0.0         | 192.24.1.1 |

Mostrate i dettagli del calcolo eseguito per instradare pacchetti indirizzati all'host 192.24.128.5 e all'host 192.24.1.5.

---

**Esercizio 3 (12 punti)**

- Dato il blocco CIDR 193.204.176.0/22, sub-nettatelo con la tecnica VLSM per creare due sottoreti A, B e due collegamenti IP punto-punto: A-B, B-Internet. Precisate quanti router utilizzerete, a quali sottoreti saranno connessi. Per ciascuna sottorete fornite subnetID, intervallo d'indirizzamento e indirizzo di broadcast.
- Fornite un progetto di switching a livello 2 che supporti il piano d'indirizzamento IP usando due switch, lasciando su ciascuno switch porte di entrambe le reti. Specificate la configurazione degli switch, e la tecnica di connessione dei router agli switch.
- Simulate una richiesta ARP fatta da un host sulla rete A, facendo le opportune ipotesi e mostrando tutti i frame generati.

---

**Esercizio 4 (5 punti)**

Data la rete in figura, specificate la tabella di instradamento per il router A.

![](imgs/Pasted%20image%2020260519141502.png)

**Interfacce:**

- **Router B:** 10.0.1.2/24 eth0 | 10.0.2.2/24 eth1
- **Host A:** 10.0.3.1/24 eth0
- **Host B:** 10.0.3.2/24 eth0 (collegato a Router B eth1 side)
- **Router A:** 10.0.1.1/24 eth0 | 100.4.1.24 d0 | 10.0.2.1/24 eth1 | 10.0.1.2/24 eth0

> _Nota: la topologia nell'immagine originale mostra Host A e Host B collegati rispettivamente a Router B e Router A tramite diverse interfacce, con i due router interconnessi. Gli indirizzi esatti come visibili dall'immagine sono:_
>
> - **Host A:** 10.0.1.1/24 eth0
> - **Host B:** 10.0.3.2/24 eth0 (oppure 10.0.2.1/24 eth1)
> - **Router B:** 10.0.1.2/24 eth0, 10.0.2.2/24 eth1, 10.0.3.1/24 eth0
> - **Router A:** 100.4.1.24 d0, 10.0.2.1/24 eth1, 10.0.1.2/24 eth0
