# **Reti di Calcolatori — Progetto Integrativo: Regole e Opzioni**

### **1. Che cos'è il progetto integrativo**

Il progetto integrativo è un lavoro **individuale e facoltativo** che consente di ottenere un **incremento da 0 a 3 punti** sulla **media per difetto** dei voti conseguiti nella PARTE A e nella PARTE B dell'esame scritto.

> 📌 Il voto finale verbalizzabile è quindi: $\text{Voto} = \left\lfloor \frac{A + B}{2} \right\rfloor + \Delta_{\text{progetto}}, \quad \Delta_{\text{progetto}} \in \{0, 1, 2, 3\}$

Il punteggio assegnato dipende da **completezza, difficoltà e qualità della discussione** del progetto: i 3 punti pieni non sono garantiti dalla semplice consegna, ma vanno conquistati con un lavoro che dimostri padronanza reale degli argomenti del corso.

> 💡 **Il nostro caso concreto**: PARTE A = 25, PARTE B = 27 → media per difetto $\lfloor 52/2 \rfloor = 26$. Con un progetto da 3 punti pieni il voto verbalizzato diventa **29**.

#### **1.1. Regole di consegna e discussione**

- Il progetto va **caricato su `upload.unimi.it`**, nella stanza dell'esame di Reti di Calcolatori, **entro la deadline** comunicata (tipicamente qualche giorno prima dello scritto).
- La consegna comprende **due elementi**: i file del progetto (codice e/o configurazioni) e una **relazione scritta**.
- La discussione (orale) avviene **esclusivamente durante uno degli appelli scritti**, salvo casi eccezionali di numerosità elevata. L'orale consiste **nella sola discussione del progetto**.

#### **1.2. Requisiti della relazione**

La relazione deve contenere:

1. la **descrizione del progetto** (obiettivi, architettura, scelte progettuali);
2. la **discussione dei brani di codice significativi**;
3. un **walkthrough completo** dell'esecuzione, supportato da **screenshot**.

#### **1.3. Progetti alternativi**

È possibile proporre un progetto non presente nell'elenco ufficiale, a due condizioni:

1. garantire che **non sia già stato utilizzato in un altro insegnamento**;
2. inviare al docente **una pagina di descrizione dettagliata**; il docente conferma se il progetto può valere i 3 punti massimi, oppure suggerisce come estenderlo.

---

### **2. Le opzioni ufficiali**

#### **2.1. Progetti di design e deployment (IMUNES / IPTABLES)**

Progetti di **configurazione e messa in opera** di reti emulate: nessuno sviluppo software vero e proprio, ma progettazione di topologie, indirizzamento e servizi.

| # | Progetto | Tecnologie | Contenuto |
|---|----------|------------|-----------|
| 1 | **Rete universitaria** | IMUNES | Tre sezioni (amministrazione, docenti, studenti), due laboratori studenti, cinque laboratori di ricerca. Progettazione a livello 2 e 3: VLAN, trunking, piano di indirizzamento IP |
| 5 | **Rete con servizi base** | IMUNES | Server DHCP + server DNS + due a scelta tra FTP/HTTP/SMTP, con un utente interno e uno esterno |
| 6 | **Rete con proxy trasparente** | IMUNES | DHCP + DNS + proxy trasparente per filtraggio e load balancing verso un server HTTP, utente interno ed esterno |
| 7 | **Rete con firewall Linux** | IPTABLES | Tutto il traffico da/verso le macchine interne (client e server) mediato da un firewall Linux |
| 8 | **Rete con virtual hosting e SSL** | IMUNES | DHCP + DNS + server HTTP con virtual hosting e connessioni SSL + proxy HTTP, utente interno ed esterno |

#### **2.2. Progetti di implementazione (C / socket API)**

Progetti di **sviluppo software di rete in C**, in continuità diretta con il modulo M4 del corso (socket API, `select()`, client/server TCP-UDP).

| # | Progetto | Tecnologie | Contenuto |
|---|----------|------------|-----------|
| 2 | **Router in C** | C, kernel space o Quagga | Implementazione di un router con funzionalità/protocolli di routing |
| 3 | **Client P2P Gnutella-like** | C | Client peer-to-peer non strutturato in stile Gnutella (flooding, query/query-hit) |
| 4 | **Protocollo P2P Chord** | C, socket | Implementazione della DHT Chord (overlay strutturato, finger table, lookup in $O(\log N)$) |
| 9 | **Protocollo applicativo a scelta** | C, socket API | Progettazione e implementazione di un protocollo applicativo originale |
| 12 | **Traceroute** | C, socket raw | Reimplementazione di traceroute (TTL incrementale, ICMP Time Exceeded) |

#### **2.3. Progetti di studio e dimostrazione**

| # | Progetto | Contenuto |
|---|----------|-----------|
| 10 | **HTTP/3** | Studio e dimostrazione pratica del protocollo (QUIC, UDP, 0-RTT, multiplexing senza head-of-line blocking) |
| 11 | **SNMP** | Test e dimostrazione del protocollo di gestione rete (MIB, GetRequest/Trap, community) |

---

### **3. Analisi comparativa: difficoltà vs impatto**

| # | Progetto | Difficoltà | Impatto sul docente | Note |
|---|----------|:----------:|:-------------------:|------|
| 2 | Router in C | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | Tocca il **cuore del corso** (livello 3, forwarding, routing dinamico). Il più ambizioso in assoluto |
| 4 | Chord in C | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | Accademicamente elegante, ma il P2P è marginale nel programma rispetto al routing |
| 3 | Gnutella-like | ⭐⭐⭐⭐ | ⭐⭐⭐ | Impegnativo ma concettualmente più semplice di Chord |
| 10 | HTTP/3 | ⭐⭐⭐ | ⭐⭐⭐⭐ | Modernissimo, ma "studio e demo": meno codice, meno rischio, meno gloria |
| 8 | Virtual hosting + SSL | ⭐⭐⭐ | ⭐⭐⭐ | Il più ricco tra gli IMUNES |
| 1 | Rete universitaria | ⭐⭐⭐ | ⭐⭐⭐ | Buona progettazione L2/L3, ma niente sviluppo |
| 6 | Proxy trasparente | ⭐⭐⭐ | ⭐⭐⭐ | Interessante il load balancing |
| 9 | Protocollo applicativo | ⭐⭐⭐ | ⭐⭐⭐ | Valore dipende interamente dall'idea |
| 7 | Firewall IPTABLES | ⭐⭐ | ⭐⭐ | Standard |
| 5 | Servizi base | ⭐⭐ | ⭐⭐ | Il più "compitino" |
| 12 | Traceroute | ⭐⭐ | ⭐⭐⭐ | Piccolo ma chirurgico (socket raw, ICMP) |
| 11 | SNMP | ⭐ | ⭐⭐ | Il meno impegnativo |

> ✅ **Scelta consigliata per i 3 punti pieni: il Router in C (#2)**, potenziato con una demo in IMUNES. È l'unico progetto che unisce **implementazione di basso livello** (socket raw, parsing di pacchetti, forwarding), **protocolli di routing dinamico** (il capitolo teoricamente più nobile del corso) e **deployment emulato** (IMUNES, come i progetti di design). In pratica: due categorie di progetto in una.

> ⚠️ È anche il progetto con il rischio più alto: richiede pianificazione rigorosa e test incrementali. La progettazione completa è sviluppata in [L2 - Progetto Router IPv4 in C - Design](L2%20-%20Progetto%20Router%20IPv4%20in%20C%20-%20Design.md).
