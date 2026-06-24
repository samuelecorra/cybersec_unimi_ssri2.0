# Formulario — iptables: Pattern Esercizi d'Esame

**Basato su M2/UD2/L1, M2/UD5/L5, soluzioni esami 2025-2026 — Prof. Ernesto Damiani**
Complemento a `sintassi_iptables.md`. Questo file NON ripete la legenda dei flag: si concentra su come leggere la traccia e costruire le regole corrette.

---

## 1. Anatomia mentale di ogni esercizio iptables

Prima di scrivere una sola riga, rispondi a queste domande:

```
1. Chi può fare cosa verso dove?
   → identifica: (sorgente) [protocollo] (destinazione) [azione]

2. Dove si trova il firewall?
   → tra LAN e Internet (FORWARD) o sul gateway stesso (INPUT/OUTPUT)?

3. Ci sono più "chi"?
   → proxy? sottorete autorizzata? IP specifico? TUTTI tranne X?

4. Il traffico di ritorno va permesso?
   → Sì sempre. Usa conntrack ESTABLISHED,RELATED per il ritorno.

5. La policy di default è DROP o ACCEPT?
   → Quasi sempre DROP in esame (più sicura, più istruttiva da scrivere).
```

---

## 2. Template universale — struttura minima di ogni soluzione

```bash
# ── Variabili (metti sempre le variabili, il prof le apprezza) ──────
IF_INT=eth0          # interfaccia verso LAN interna
IF_EXT=eth1          # interfaccia verso Internet/esterno

# ── Policy di default ────────────────────────────────────────────────
iptables -P FORWARD DROP   # blocca tutto il transito per default

# ── Regole ACCEPT specifiche ──────────────────────────────────────────
# [qui le regole in uscita]
# [qui le regole per il traffico di ritorno]
# [qui eventuali DROP espliciti aggiuntivi]
```

---

## 3. Pattern A — "Solo la sottorete X può uscire su Internet"

**Traccia tipo:** "Permettete l'accesso a Internet solo dalla sottorete `192.168.10.0/24`. Bloccate tutto il resto."

```bash
AUTH_NET=192.168.10.0/24
IF_INT=eth0
IF_EXT=eth1

iptables -P FORWARD DROP

# Uscita: solo dalla sottorete autorizzata
iptables -A FORWARD -i $IF_INT -o $IF_EXT -s $AUTH_NET -j ACCEPT

# Ritorno: risposte alle connessioni avviate da noi
iptables -A FORWARD -i $IF_EXT -o $IF_INT \
         -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
```

**Tabella comportamento:**

| Direzione      | Sorgente                        | Esito             |
| -------------- | ------------------------------- | ----------------- |
| LAN → Internet | `192.168.10.x`                  | ACCEPT            |
| LAN → Internet | qualsiasi altro host            | DROP (policy)     |
| Internet → LAN | risposta a conn. avviata da noi | ACCEPT (stateful) |
| Internet → LAN | connessione nuova               | DROP (policy)     |

---

## 4. Pattern B — "Solo un IP specifico (proxy) può TCP verso esterno; UDP disabilitato"

**Traccia tipo (esame 8/05/2026):** "Solo il proxy `10.0.0.1` può avere connessioni TCP verso Internet. UDP disabilitato per tutti."

```bash
PROXY_IP=10.0.0.1
IF_INT=eth0
IF_EXT=eth1

iptables -P FORWARD DROP

# TCP uscente SOLO dal proxy
iptables -A FORWARD -i $IF_INT -o $IF_EXT \
         -s $PROXY_IP -p tcp -j ACCEPT

# TCP ritorno: solo per connessioni avviate dal proxy
iptables -A FORWARD -i $IF_EXT -o $IF_INT \
         -d $PROXY_IP -p tcp \
         -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# UDP bloccato in entrambe le direzioni (esplicito anche se coperto dalla policy)
iptables -A FORWARD -p udp -j DROP
```

**Tabella comportamento:**

| Direzione | Sorgente / Dest | Proto | Esito |
|---|---|---|---|
| LAN → Internet | `10.0.0.1` (proxy) | TCP | ACCEPT |
| LAN → Internet | qualsiasi altro | TCP | DROP |
| qualsiasi | qualsiasi | UDP | DROP |
| Internet → LAN | risposta TCP al proxy | TCP | ACCEPT (stateful) |
| Internet → LAN | connessione TCP nuova | TCP | DROP |

---

## 5. Pattern C — "Blocca connessioni TCP in ingresso; permetti uscita e ritorno"

**Traccia tipo (esame 23/05/2025):** "Impedite ai vostri utenti di rispondere a richieste TCP dall'esterno, ma lasciate che si connettano a Internet e ricevano le risposte."

### Soluzione stateless (con flag SYN)

```bash
iptables -P INPUT  ACCEPT   # default permissivo, poi filtriamo il SYN
iptables -P OUTPUT ACCEPT

# Blocca il PRIMO segmento di connessioni nuove in ingresso (SYN senza ACK)
iptables -A INPUT -i eth0 -p tcp --syn -j DROP
# --syn = SYN=1, FIN=0, RST=0, ACK=0  (equivale a --tcp-flags FIN,SYN,RST,ACK SYN)
# I SYN+ACK (risposta al nostro SYN) hanno ACK=1 → NON fanno match → passano
```

### Soluzione stateful (raccomandata, più robusta)

```bash
iptables -P INPUT  DROP
iptables -P OUTPUT ACCEPT

# Loopback sempre ammesso
iptables -A INPUT -i lo -j ACCEPT

# Ammetti solo pacchetti di connessioni già avviate da noi
iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
# NEW (SYN in ingresso) → catturato dalla policy DROP → mai ammesso
```

**Differenza stateless vs stateful:**

| | Stateless (`--syn`) | Stateful (conntrack) |
|---|---|---|
| **Cosa analizza** | Flag TCP | Stato della connessione |
| **Copertura** | Blocca solo SYN (e varianti) | Blocca TUTTO ciò che non è risposta a un nostro SYN |
| **Robustezza** | Vulnerabile a pacchetti malformati con ACK=1 falsificato | Sicura: la connessione deve esistere nella tabella |
| **Complessità** | Minore | Richiede modulo conntrack |

---

## 6. Pattern D — "Solo porta 80/443 verso Internet; resto bloccato"

**Traccia tipo:** "I client possono navigare (HTTP/HTTPS) ma non altro traffico."

```bash
IF_INT=eth0
IF_EXT=eth1

iptables -P FORWARD DROP

# HTTP (80) e HTTPS (443) in uscita
iptables -A FORWARD -i $IF_INT -o $IF_EXT -p tcp --dport 80  -j ACCEPT
iptables -A FORWARD -i $IF_INT -o $IF_EXT -p tcp --dport 443 -j ACCEPT

# Ritorno per entrambe le porte
iptables -A FORWARD -i $IF_EXT -o $IF_INT \
         -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
```

---

## 7. Pattern E — "Nega X, permetti tutto il resto" (blacklist)

```bash
# Blocca esplicitamente IP o rete specifica
iptables -A FORWARD -s 10.5.0.0/24 -j DROP   # nega questa sottorete

# Permetti tutto il resto (nessuna policy DROP globale)
# oppure: iptables -P FORWARD ACCEPT
```

---

## 8. Regola conntrack — spiegazione mnemonicana

```bash
-m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
```

**Traduzione:** "Lascia passare i pacchetti che appartengono a una connessione **già avviata dall'interno** (ESTABLISHED = bidirezionale attiva) o che sono **correlati** a essa (RELATED = es. ICMP error, FTP data channel)."

**Perché serve sempre nel Pattern A/B/C:**
- Senza questa regola, il server remoto risponde ma i pacchetti di ritorno vengono bloccati dalla policy DROP → la connessione muore
- È la regola che permette "out OK, in solo se siamo stati noi ad aprire"

**États conntrack:**

| Stato | Quando | Cosa fare |
|---|---|---|
| `NEW` | Primo SYN di una nuova connessione | Di solito DROP (in ingresso dall'esterno) |
| `ESTABLISHED` | Scambio bidirezionale in corso | ACCEPT (è il ritorno) |
| `RELATED` | Correlato (ICMP error, FTP data) | ACCEPT |
| `INVALID` | Non corrisponde ad alcuno stato | DROP sempre |

---

## 9. Come leggere una regola complessa

```bash
iptables -A FORWARD -i eth0 -o eth1 -s 192.168.10.0/24 -p tcp --dport 443 -m conntrack --ctstate NEW -j ACCEPT
```

Leggi da sinistra a destra:

| Pezzo | Traduzione |
|---|---|
| `-A FORWARD` | Catena FORWARD (traffico in transito) |
| `-i eth0` | Pacchetto arriva da eth0 (LAN) |
| `-o eth1` | Pacchetto esce da eth1 (Internet) |
| `-s 192.168.10.0/24` | Sorgente = sottorete LAN autorizzata |
| `-p tcp` | Protocollo = TCP |
| `--dport 443` | Porta destinazione = 443 (HTTPS) |
| `-m conntrack --ctstate NEW` | È una nuova connessione |
| `-j ACCEPT` | Lascia passare |

**Lettura in chiaro:** "Accetta il primo segmento TCP (nuova connessione) verso la porta 443 di Internet proveniente dalla sottorete LAN `192.168.10.0/24`."

---

## 10. Ordine delle regole — importantissimo

Le regole sono valutate **in ordine**. La prima che fa match termina la valutazione.

**Schema corretto:**
```
1. Regole ACCEPT specifiche (es. solo proxy)
2. Regole DROP esplicite aggiuntive (es. blocca UDP)
3. Policy di default DROP (cattura tutto il resto)
```

**Schema SBAGLIATO:**
```
1. ACCEPT tutto il TCP           ← troppo permissivo
2. DROP proxy diverso dall'IP   ← mai raggiunto!
```

> La regola DROP **deve venire prima** se vuoi che abbia effetto, oppure usa la policy di default DROP e scrivi solo le regole ACCEPT.

---

## 11. Tabella riepilogo pattern → comandi chiave

| Policy richiesta | Comandi essenziali |
|---|---|
| Solo sottorete X esce | `-P FORWARD DROP` + `-s X/mask -j ACCEPT` + `conntrack ESTABLISHED` |
| Solo IP proxy TCP, no UDP | `-s PROXY_IP -p tcp -j ACCEPT` + `conntrack ESTABLISHED` + `-p udp -j DROP` |
| Blocca SYN entranti (stateless) | `-p tcp --syn -j DROP` |
| Blocca connessioni nuove (stateful) | `-P INPUT DROP` + `conntrack ESTABLISHED,RELATED -j ACCEPT` |
| Solo porte 80/443 | `-p tcp --dport 80 -j ACCEPT` + `--dport 443` + `conntrack` |

---

## 12. Errori comuni da evitare in esame

1. **Dimenticare il traffico di ritorno.** Senza `conntrack ESTABLISHED,RELATED`, le risposte vengono droppate.

2. **Usare INPUT invece di FORWARD.** Se il firewall fa da router tra LAN e Internet, il traffico di transito è in `FORWARD`, non in `INPUT`.

3. **Scrivere `-d` invece di `-s` per bloccare l'uscita.** La sorgente del traffico uscente è il client LAN → usa `-s`.

4. **Dimenticare di specificare le interfacce.** Senza `-i`/`-o`, la regola si applica in tutte le direzioni.

5. **Mettere ACCEPT prima di DROP per lo stesso traffico.** L'ordine conta: la prima regola che fa match vince.

6. **Non mettere la policy di default.** Senza `-P FORWARD DROP`, il traffico non matchato passa — vanifica le regole.
