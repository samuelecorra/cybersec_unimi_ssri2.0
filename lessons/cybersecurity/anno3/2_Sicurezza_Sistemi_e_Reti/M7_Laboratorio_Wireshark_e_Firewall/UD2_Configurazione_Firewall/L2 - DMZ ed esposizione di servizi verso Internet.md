## **Lezione 2: DMZ, segmentazione ed esposizione di servizi verso Internet**

### **1. Il ruolo della DMZ nel disegno delle regole**

La **DMZ** (*De-Militarized Zone*) è la sottorete che ospita i servizi accessibili dall'esterno (web, mail, DNS pubblici), tenuta separata sia da Internet sia dalla LAN interna (vedi [M5/UD1/L2 – Introduzione ai Firewall](../../M5/UD1/L2%20-%20Introduzione%20ai%20Firewall.md) §8). Nel disegno delle regole, la DMZ introduce un principio guida: **ogni coppia di zone ha una policy propria**. Un firewall a più interfacce (LAN, DMZ, Internet, guest…) va pensato come una **matrice zona-a-zona**, dove per ciascuna direzione si decide cosa è permesso.

> 📌 Conviene ragionare per **triplette** «zona sorgente → zona destinazione : servizio». Ogni tripletta consentita diventa una coppia di righe (richiesta + risposta con ACK=1, vedi [[L1 - Metodologia della tabella di filtraggio (le 8 colonne e il flag ACK)]]). Tutto ciò che non compare tra le triplette è vietato dal default deny.

---

### **2. Segmentare: isolare le sottoreti tra loro**

Molte tracce impongono che due sottoreti **non si vedano** (es. "i PC della segreteria non hanno accesso ai PC degli sviluppatori e viceversa"). Con una policy *default deny* questo è **automatico**: se non scrivi alcuna regola che permetta quel traffico, esso è già negato. Non serve una riga di *deny* esplicita, a meno che la traccia non chieda di evidenziarla.

> 💡 Errore da evitare: aggiungere regole di *permit* troppo larghe (es. "LAN → tutto") che, come effetto collaterale, aprono anche i percorsi che dovevano restare chiusi. Con il default deny, **meno regole = più sicurezza**: si permette solo l'indispensabile, host o sottorete per volta.

---

### **3. Esporre un servizio pubblico (Internet → DMZ)**

"Il web server è esposto a Internet [in HTTPS]" significa che l'iniziativa della connessione è **esterna**: la richiesta **entra** dall'interfaccia Internet verso il server in DMZ, la risposta esce. Per un web server `10.0.4.11` in HTTPS:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Internet→DMZ | any | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| DMZ→Internet | 10.0.4.11 | any | TCP | 443 | >1023 | 1 | permit |

Se il servizio è esposto su una **porta non standard** (es. "il server di backup è esposto a Internet sulla porta 5000"), cambia solo la porta di destinazione della richiesta:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Internet→Servizi | any | 10.0.4.15 | TCP | >1023 | 5000 | 0/any | permit |
| Servizi→Internet | 10.0.4.15 | any | TCP | 5000 | >1023 | 1 | permit |

> ⚠️ "*Solo HTTPS del web server è accessibile da fuori*" impone due limiti: **solo la porta 443** (non 80) e **solo verso quel server** (non l'intera DMZ). La richiesta in ingresso deve avere IP destinazione = il web server e porta destinazione = 443; nient'altro dall'esterno deve trovare una regola di permit.

---

### **4. Accesso ristretto: "solo X può…"**

Quando la policy limita l'accesso a un singolo host o a una sottorete, si stringe l'**IP sorgente** della regola di richiesta:

- "*http del web server è accessibile solo per pc2*" (pc2 = `10.0.2.21`):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| LAN→DMZ | 10.0.2.21 | 10.0.1.10 | TCP | >1023 | 80 | 0/any | permit |
| DMZ→LAN | 10.0.1.10 | 10.0.2.21 | TCP | 80 | >1023 | 1 | permit |

- "*solo la LAN può accedere al servizio RPC nella DMZ*": IP sorgente = la sottorete LAN (`10.0.2.0/24`), non `any`.

> 💡 La parola "**solo**" nella traccia è un segnale: circoscrive l'IP sorgente (chi) e/o la porta di destinazione (quale servizio). "Solo da fuori" ⇒ sorgente esterna dall'interfaccia Internet; "solo la LAN" ⇒ sorgente = sottorete interna; "solo pc2" ⇒ sorgente = singolo host.

---

### **5. Comunicazioni interne tra zone di servizio**

Non tutto il traffico coinvolge Internet. Spesso i server devono parlarsi tra loro (es. "il servizio di autenticazione comunica con il database", "gli sviluppatori e il web server hanno accesso al database"). Sono connessioni **zona interna → zona servizi** su una porta applicativa (es. PostgreSQL 5432):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Servizi→Servizi | 10.0.4.11 | 10.0.4.10 | TCP | >1023 | 5432 | 0/any | permit |
| Servizi→Servizi | 10.0.4.10 | 10.0.4.11 | TCP | 5432 | >1023 | 1 | permit |

> ⚠️ Se due host stanno **sulla stessa sottorete** (stesso switch, stessa interfaccia del firewall), il loro traffico **non attraversa il firewall** e quindi non è filtrabile da esso. Le regole hanno senso solo per traffico che passa da un'interfaccia all'altra del firewall. Verifica sempre, sulla topologia, se sorgente e destinazione stanno su lati diversi del firewall.

---

### **6. La riga finale: default deny**

La tabella si chiude sempre con la regola che nega tutto il resto:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| any | any | any | any | any | any | any | **deny** |

È l'incarnazione del principio *default deny* / *fail-safe defaults* (vedi [M2/UD1/L4 – Principi di sicurezza](../../M2/UD1/L4%20-%20Principi%20di%20sicurezza.md)): ciò che non è stato esplicitamente autorizzato è proibito. Nei firewall reali corrisponde a `iptables -P FORWARD DROP`.

> ✅ **Ricapitolando** — Un firewall multi-zona si progetta come matrice «zona → zona : servizio»: ogni comunicazione ammessa diventa una coppia di righe (richiesta + risposta con ACK=1). L'isolamento tra sottoreti è gratuito grazie al default deny (non scrivere regole = vietare). Esporre un servizio significa una richiesta **in ingresso** verso quel preciso host e quella precisa porta; la parola "solo" restringe IP sorgente e/o porta. Attenzione a non filtrare traffico intra-sottorete (che non passa dal firewall) e a chiudere sempre con il *deny any*.

Precedente: [[L1 - Metodologia della tabella di filtraggio (le 8 colonne e il flag ACK)]] · Prossima: [[L3 - Servizi multi-connessione (FTP attivo-passivo, RPC, SMTP)]].
