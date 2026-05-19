# Esame di Reti di Calcolatori (parte A)

**20/03/2026 – Prof. Ernesto Damiani**

---

**Esercizio 1 (10 punti)** In una rete Ethernet che utilizza un protocollo a contesa CSMA/CD operante a bit rate R = 100 Mbps, la lunghezza minima del PDU (frame) è fissata a L_min = 512 bit. Calcolate la distanza massima consentita tra due stazioni, in modo da garantire l'integrità del frame (cioè che una stazione termini la trasmissione del frame prima che eventuali collisioni vengano rilevate). Motivate la formula utilizzata e fornite il risultato numerico in metri (arrotondato all'intero più vicino).

---

**Esercizio 2 (10 punti)** – Si devono collegare due piani di un edificio con i seguenti requisiti:

- Switch A (piano terra) e Switch B (primo piano) sono collegati tra loro tramite un cavo di trunking (porta GigabitEthernet 0/24).
- Su entrambi gli switch devono esistere: VLAN Ufficio (ID 10) per host cablati; VLAN Wi-Fi (ID 20) per Access Point 802.11
- Sullo Switch A deve esistere anche la VLAN Internet (ID 30) alla quale è collegato (porta GigabitEthernet 0/1) il router esterno verso Internet.

**(1)** Fornite (in stile Cisco IOS) la configurazione delle VLAN e delle porte per entrambi gli switch. Specificate: La creazione delle VLAN; le porte di accesso per gli host ufficio e per gli AP Wi-Fi; La configurazione del trunking tra i due switch; Le porte a cui è connesso il router interno tra VLAN Wi-Fi e VLAN Internet (usate porte normali o porte di trunk? – motivate la scelta)

**(2)** Progettate il piano di indirizzamento IP per la rete rispettando le seguenti regole: Ogni VLAN corrisponde a un subnet IP. Il router interno funge da gateway di default per tutte le VLAN. La VLAN Wi-Fi (20) deve poter raggiungere Internet (attraverso la VLAN 30). Specificate: subnet e maschere per le tre VLAN; gli indirizzi IP assegnati al router su ciascuna interfaccia; Come viene realizzata la connessione L3 tra VLAN Wi-Fi e VLAN Internet (router-on-a-stick o tradizionale).

---

**Esercizio 3 (10 punti)** Illustrate con un esempio concreto (nomi di host, indirizzi IP, sequenza di messaggi) l'attacco di spoofing DHCP (o rogue DHCP server). Indicate: La sequenza di messaggi DHCP coinvolti; quali campi sono falsificati dall'attaccante; le conseguenze per le vittime (MITM, furto credenziali, ecc.); una possibile contromisura semplice (a livello di switch o router).
