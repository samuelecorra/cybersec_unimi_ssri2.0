# **Roadmap esame - Sicurezza dei Sistemi e delle Reti**

### **1. Premessa realistica**

L'esame di **Sicurezza dei Sistemi e delle Reti** non è difficile perché richiede calcoli impossibili: è difficile perché contiene una quantità enorme di nozioni, esempi, attacchi, protocolli, strumenti, casi limite e dettagli operativi. La difficoltà principale è non perdere il filo tra teoria, laboratorio, esempi del docente e appelli passati.

Abbiamo però un vantaggio concreto: i **4 appelli risolti** della prima parte di teoria mostrano abbastanza bene che tipo di domande vengono poste. Non bisogna studiare "a sentimento": bisogna studiare con in testa il formato reale dell'esame.

> 📌 L'obiettivo non è sapere tutto in modo indistinto. L'obiettivo è saper riconoscere subito quale blocco teorico viene chiesto, collegarlo agli esempi pratici giusti e scrivere una risposta completa, ordinata e difendibile.

### **2. Com'è strutturato davvero l'esame**

Dagli appelli risolti emerge una divisione molto netta:

- **Prima parte: teoria.**
  Domande discorsive su sicurezza, malware, autenticazione, controllo accessi, Set-UID, variabili d'ambiente, Shellshock, TCP attacks, firewall, IDS/IPS, honeypot, protocolli, scanning, SSL/TLS e altri argomenti del corso.

- **Seconda parte: laboratorio.**
  Analisi di un set di pacchetti dato dal docente con Wireshark più configurazione firewall, tipicamente con regole in stile `iptables` o tabella di filtraggio. Punto. Non compaiono esercizi Docker, Set-UID o exploit da eseguire live nella seconda parte.

> ⚠️ Gli esercizi Docker sugli attacchi TCP, i lab Set-UID, Shellshock, ambiente, `PATH`, `system()`, Scapy e simili non vanno confusi con la seconda parte di laboratorio. Servono soprattutto per capire bene gli argomenti che poi possono essere chiesti nella **prima parte teorica**.

### **3. Cosa significa "studiare bene la teoria"**

Studiare la teoria non significa solo leggere le lezioni. Significa arrivare a saper rispondere, senza panico, a domande del tipo:

- perché Set-UID cambia il modello dei privilegi?
- che differenza c'è tra RUID, EUID e SUID?
- perché `system()` in un programma privilegiato è pericoloso?
- come funziona un attacco via `PATH`?
- perché Shellshock è un caso di injection tramite ambiente?
- come si costruisce un TCP reset attack?
- quando un TCP hijacking richiede di conoscere `SEQ` e `ACK`?
- perché SYN flooding consuma backlog?
- cosa cambiano i SYN cookies?
- che differenza c'è tra packet filter, stateful firewall, application gateway e circuit gateway?
- come si riconosce un attacco o una navigazione sospetta da una traccia Wireshark?
- come si traduce una topologia in regole firewall corrette?

> 💡 La teoria di questa materia è piena di oggetti pratici. Se un attacco lo hai visto in laboratorio, poi lo spieghi meglio nella risposta scritta.

### **4. Dove stanno Docker, Set-UID e Wireshark**

La mappa mentale corretta è questa:

- **Set-UID, variabili d'ambiente, Shellshock, privilege dropping, `PATH`, `LD_PRELOAD`:**
  servono a rendere solide le risposte di teoria su controllo accessi, processi Unix, privilegi, escalation e programmi privilegiati.

- **Docker SEED, Scapy, TCP spoofing, SYN flood, RST, hijacking, reverse shell:**
  servono a capire davvero gli attacchi TCP che possono comparire nella parte teorica.

- **Wireshark canonico M4/UD3/L5:**
  serve a imparare bene l'interfaccia e i protocolli fondamentali su tracce pulite: HTTP, DNS, ARP, ICMP, TCP, DHCP, NAT, TLS, 802.11.

- **M7 laboratorio Wireshark e firewall:**
  è il blocco più vicino alla seconda parte d'esame: analisi pacchetti + configurazione firewall.

- **M6 appelli passati:**
  è il banco di prova finale, perché mostra lo stile reale della commissione.

> ✅ Prima teoria, poi laboratorio d'esame. Se la teoria non è ordinata, il laboratorio pratico rischia di diventare solo movimento senza consolidamento.

### **5. Roadmap fino al 15/07/2026**

L'esame è il **15 luglio 2026**. Dal punto di vista operativo, la settimana va trattata come una preparazione ad alta intensità ma con obiettivi chiari. Non serve aprire dieci fronti insieme: serve una sequenza che riduca il cambio di contesto.

#### **5.1. Fase 1 - Allineamento teoria pesante**

Obiettivo: coprire tutti gli argomenti che possono essere rifilati come domanda discorsiva.

Priorità:

1. Malware, worm, payload, stealthing, botnet, rootkit, contromisure.
2. Autenticazione: password, token, biometria, MFA, protocolli, replay, challenge-response, zero-knowledge.
3. Controllo accessi: DAC, MAC, RBAC, ABAC, Windows, Linux.
4. Set-UID e privilegi Unix: RUID/EUID/SUID, SetGID, Sticky Bit, root, processi.
5. Variabili d'ambiente, `PATH`, `system()`, Shellshock, dynamic linker.
6. TCP/IP attacks: SYN flood, RST, hijacking, ACK storm, spoofing.
7. Network scanning: vertical/horizontal scan, SYN/connect/UDP/ACK/FIN/Xmas, stealth, OS fingerprinting.
8. Firewall, IDS/IPS, honeypot, DMZ, NAT, application/circuit gateway.
9. SSL/TLS e sicurezza dei protocolli.

Output atteso: per ogni macrotema devi saper scrivere una risposta da 15-25 righe con definizione, funzionamento, esempio d'attacco, limiti e contromisure.

### **6. Piano giornaliero**

#### **6.1. 08/07/2026 - Stabilizzazione e mappa**

Obiettivo: non studiare ancora in modo dispersivo. Devi costruire il quadro.

Da fare:

- leggere questa roadmap;
- aprire i 4 appelli teoria risolti;
- segnare le domande ricorrenti;
- ripassare M3/UD2-UD4: controllo accessi, Linux, Set-UID, ambiente, Shellshock;
- ripassare M4/UD5 approfondimenti TCP hijacking/reset.

Risultato serale: sapere quali blocchi sono "ad alto rendimento".

#### **6.2. 09/07/2026 - Controllo accessi e Unix**

Obiettivo: rendere blindata la parte Set-UID/Linux.

Da fare:

- M3/UD2: DAC/MAC/RBAC/ABAC;
- M3/UD3: Windows e Linux access control;
- M3/UD4: L0-L8 laboratorio moderno Set-UID;
- rifare mentalmente gli esempi su `myenv`, `myprintenv`, `catall`, `cap_leak`;
- scrivere a mano almeno 3 risposte teoriche:
  - Set-UID e RUID/EUID;
  - attacco via `PATH`;
  - Shellshock.

> 📌 Qui il lab serve a fissare la teoria, non a preparare la seconda parte dell'esame.

#### **6.3. 10/07/2026 - TCP/IP attacks e scanning**

Obiettivo: trasformare Docker/SEED in risposte teoriche solide.

Da fare:

- M4/UD2: vulnerabilità TCP/IP, spoofing, ACK storm, SYN flooding, altri attacchi TCP/IP;
- M4/UD2/L8: esercizi Docker TCP/IP;
- M4/UD3: network e port scanning;
- M4/UD3/L5: tracce Wireshark canoniche come rinforzo sui protocolli.

Risposte da saper scrivere:

- SYN flood e SYN cookies;
- TCP reset attack;
- TCP session hijacking;
- IP spoofing;
- port scanning e tecniche stealth.

#### **6.4. 11/07/2026 - Malware, autenticazione, protocolli**

Obiettivo: coprire i blocchi teorici che possono uscire come domanda secca.

Da fare:

- M2 malware: virus, worm, Trojan, payload, stealth, contromisure;
- M3/UD1 autenticazione;
- protocolli di autenticazione, replay, challenge-response, Lamport, zero-knowledge;
- SSL/TLS se collegato a sicurezza dei protocolli e traffico cifrato.

Output:

- 4 risposte discorsive complete;
- elenco di esempi da citare: Morris Worm, Shellshock, SYN flood, phishing/malware da appelli.

#### **6.5. 12/07/2026 - Laboratorio Wireshark canonico**

Obiettivo: rendere fluida la lettura delle tracce.

Da fare:

- M4/UD3/L5 L0-L5;
- aprire le tracce `http`, `dns`, `ethernet`, `icmp`, `tcp`, `dhcp`, `NAT`, `ssl`;
- per ogni traccia trovare almeno:
  - IP principali;
  - protocolli;
  - porte;
  - pacchetto chiave;
  - interpretazione.

Esercizio:

- usare Wireshark su Windows;
- usare `Follow TCP Stream`;
- usare `Statistics -> Conversations`;
- usare filtri `http`, `dns`, `tcp.flags.syn == 1`, `arp`, `icmp`, `tls`.

#### **6.6. 13/07/2026 - M7 laboratorio d'esame**

Obiettivo: allinearsi al formato reale della seconda parte.

Da fare:

- M7/UD1 Wireshark L1-L6;
- M7/UD2 Firewall L1-L4;
- rifare almeno 2 appelli lab M6 senza guardare subito la soluzione;
- correggere con le soluzioni già verificate.

Focus:

- deduzione host/software da pcap;
- phishing/malware da traffico;
- FTP/HTTP/DNS/TCP/TLS;
- tabella firewall a 8 colonne;
- ACK per traffico di ritorno;
- NAT, DMZ, bridge, guest AP.

> ⚠️ La seconda parte d'esame, dagli appelli risolti, è Wireshark + firewall. Non disperdersi qui su Set-UID o Docker.

#### **6.7. 14/07/2026 - Simulazione completa**

Obiettivo: fare un giro d'esame realistico.

Da fare:

- 1 appello teoria cronometrato;
- 1 appello laboratorio cronometrato;
- correzione immediata;
- lista degli errori;
- ripasso mirato solo sugli errori.

Regola:

> 📌 Il giorno prima dell'esame non si apre un mondo nuovo. Si chiudono buchi precisi.

#### **6.8. 15/07/2026 - Esame**

Obiettivo: esecuzione pulita.

Prima dell'esame:

- ripassare solo checklist, non intere lezioni;
- riguardare domande ricorrenti teoria;
- riguardare tabella firewall;
- riguardare filtri Wireshark principali;
- non devastare il focus con materiale nuovo.

Durante l'esame:

- nelle domande teoriche: definizione, meccanismo, esempio, contromisure;
- nel laboratorio Wireshark: pacchetto, filtro, campo, interpretazione;
- nel firewall: topologia, direzione, sorgente, destinazione, servizio, porta, azione, stato/ACK.

### **7. Strategia AUDHD-friendly**

Questa materia è pericolosa per chi tende a iperfocalizzarsi perché offre infinite diramazioni. La strategia non deve combattere il focus: deve incanalarlo.

Regole operative:

- Una sessione = un solo blocco.
- Niente cambio continuo tra teoria, Docker, Wireshark e firewall.
- Timer da 45-60 minuti.
- Pausa vera da 10 minuti.
- Alla fine di ogni sessione: scrivere 5 righe di output, non "ho letto".
- Se entra l'iperfocus, usarlo sui blocchi ad alto rendimento, non su dettagli marginali.

Esempio di output valido:

```text
So spiegare SYN flood:
- obiettivo: saturare backlog half-open
- meccanismo: molti SYN con sorgenti spoofate
- effetto: risorse allocate in SYN-RECV
- contromisura: SYN cookies, rate limit, firewall/IDS
- collegamento lab: tcpattack, synflood.c, tcp_syncookies
```

> ✅ Con focus buono e materiale già preparato, puntare al 30 e lode è realistico. Non perché l'esame sia banale, ma perché ora l'ambiente, le soluzioni e la mappa degli argomenti sono finalmente allineati.

### **8. Ordine di priorità assoluta**

Se il tempo si stringe, l'ordine è:

1. Appelli teoria risolti.
2. M3 controllo accessi + Set-UID/Shellshock.
3. M4 TCP/IP attacks + scanning.
4. M7 Wireshark e firewall.
5. Appelli lab risolti.
6. Wireshark canonico M4/UD3/L5.
7. Docker SEED come rinforzo concettuale, non come buco nero.

### **9. Cosa non fare**

Non fare:

- leggere tutto linearmente senza output;
- perdere ore a perfezionare la VM invece di studiare;
- confondere laboratorio Docker con seconda parte d'esame;
- rifare mille catture live se le tracce del docente bastano;
- studiare solo Wireshark e trascurare teoria;
- studiare solo teoria e arrivare lento su firewall.

> ⚠️ Il rischio principale non è non avere materiale. Il rischio principale è averne troppo e usarlo senza gerarchia.

### **10. Obiettivo finale**

L'obiettivo finale è arrivare al 15/07/2026 con tre competenze integrate:

1. **Teoria discorsiva:** risposte complete, ordinate, con esempi e contromisure.
2. **Analisi Wireshark:** lettura rapida di PCAP con filtri e campi corretti.
3. **Firewall:** traduzione precisa di topologie e policy in regole.

Se queste tre aree sono solide, il 30 e lode non è una fantasia: è un obiettivo alto ma coerente con il lavoro fatto.

