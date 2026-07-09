## **Lezione 5: Netfilter e IPTables**

### **1. Introduzione a Netfilter**

**Netfilter** è il componente del **kernel Linux** che permette di intercettare, filtrare e manipolare i pacchetti di rete.

È la base del firewalling nei sistemi Linux e può offrire funzionalità avanzate come:

- filtraggio dei pacchetti;
- filtraggio **stateful**;
- **Network Address Translation (NAT)**;
- modifica di indirizzi IP e porte;
- estensioni modulari del kernel;
- ispezioni più approfondite dei pacchetti;
- analisi protocollari anche a livello applicativo, quando sono presenti moduli adeguati.

L'interfaccia di configurazione storica di Netfilter è fornita da:

- `iptables`, per IPv4;
- `ip6tables`, per IPv6.

> 📌 Netfilter è il meccanismo nel kernel; `iptables` è il comando in user space con cui si configurano tabelle, chain e regole.

Questa distinzione è importante: quando si scrive una regola con `iptables`, non si sta implementando direttamente il firewall nel programma `iptables`; si sta modificando la configurazione che il kernel applicherà ai pacchetti.

---

### **2. Tabelle, chain e regole**

Netfilter organizza il filtraggio tramite **tabelle**. Ogni tabella gestisce un tipo di operazione diverso.

Le tabelle principali sono:

- `filter`, per il filtraggio ordinario dei pacchetti;
- `nat`, per la traduzione di indirizzi e porte;
- `mangle`, per modifiche avanzate alle opzioni dei pacchetti;
- `raw`, per eccezioni e disattivazione del tracciamento delle connessioni.

Ogni tabella contiene più **chain**. Una chain è una lista ordinata di regole. Storicamente il modello ricorda le vecchie `ipchains`: una regola viene valutata in sequenza e, quando fa match, può produrre un'azione.

Ogni regola ha due componenti:

- **match**, o filtro: le proprietà che il pacchetto deve avere perché la regola si applichi;
- **target**, o azione: ciò che deve accadere al pacchetto se il match è verificato.

Esempi di proprietà usate nel match:

- indirizzo sorgente;
- indirizzo destinazione;
- protocollo;
- porta sorgente;
- porta destinazione;
- interfaccia di ingresso o uscita;
- stato della connessione;
- flag TCP;
- tipo ICMP.

Esempi di target:

- accettare il pacchetto;
- scartarlo;
- rifiutarlo notificando l'errore;
- loggarlo;
- modificarne indirizzo o porta tramite NAT;
- inviarlo a una chain personalizzata.

> 📌 Una regola `iptables` risponde sempre a due domande: "quali pacchetti sto riconoscendo?" e "che cosa faccio quando li riconosco?".

---

### **3. Tabella `filter`**

La tabella `filter` è la tabella principale per il firewalling classico. Serve a decidere quali pacchetti possono passare e quali devono essere bloccati.

Le chain predefinite sono:

- `INPUT`, per i pacchetti in arrivo destinati al sistema locale;
- `FORWARD`, per i pacchetti che arrivano al sistema ma devono essere inoltrati verso un altro host;
- `OUTPUT`, per i pacchetti generati dal sistema locale e diretti verso la rete.

La distinzione tra queste chain è fondamentale.

Se il pacchetto è destinato proprio alla macchina su cui gira Netfilter, viene considerata la chain `INPUT`. Se invece la macchina Linux agisce da router o firewall tra reti, e quindi deve inoltrare pacchetti non destinati a sé, entra in gioco `FORWARD`. Se infine il pacchetto è stato generato da un processo locale, viene valutato in `OUTPUT`.

> ⚠️ Se un host Linux non sta facendo routing, molte regole in `FORWARD` non avranno effetto sul traffico locale. La chain corretta dipende dal ruolo della macchina nel percorso del pacchetto.

---

### **4. Tabella `nat`**

La tabella `nat` gestisce la **Network Address Translation**, cioè la modifica di indirizzi IP e, spesso, di porte.

Il NAT è storicamente usato per compensare la scarsità di indirizzi IPv4 pubblici: più host privati possono uscire su Internet usando un solo indirizzo pubblico condiviso.

In generale si distingue tra:

- **SNAT** o Source NAT, quando viene modificato l'indirizzo sorgente;
- **DNAT** o Destination NAT, quando viene modificato l'indirizzo di destinazione;
- **MASQUERADE**, variante dinamica di SNAT utile quando l'indirizzo esterno può cambiare.

Il NAT è spesso associato a router e firewall perché modifica informazioni decisive anche per il filtraggio. Un firewall deve sapere qual è la destinazione effettiva del pacchetto, non soltanto l'indirizzo apparente visto prima della traduzione.

> 📌 NAT e filtraggio sono concettualmente diversi, ma nella pratica sono strettamente collegati: cambiare indirizzi o porte cambia anche il modo in cui le policy devono essere valutate.

#### **4.1. Chain della tabella `nat`**

La tabella `nat` usa soprattutto tre chain:

- `PREROUTING`, per pacchetti in ingresso prima della decisione di routing;
- `POSTROUTING`, per pacchetti in uscita dopo la decisione di routing;
- `OUTPUT`, per pacchetti generati localmente prima dell'uscita sulla rete.

`PREROUTING` è tipicamente usata per DNAT: prima di decidere dove instradare il pacchetto, il kernel può riscrivere la destinazione reale.

`POSTROUTING` è tipicamente usata per SNAT o `MASQUERADE`: dopo aver deciso da quale interfaccia uscirà il pacchetto, il kernel può riscrivere la sorgente.

`OUTPUT` permette di applicare DNAT anche ai pacchetti generati localmente.

---

### **5. Tabella `mangle`**

La tabella `mangle` è meno usata nelle configurazioni firewall ordinarie, ma serve per modifiche avanzate dei pacchetti.

Può intervenire su opzioni e marcature legate, per esempio, a:

- Type of Service;
- Quality of Service;
- TTL;
- policy routing;
- marcature interne usate da regole successive.

Le chain della tabella `mangle` sono:

- `PREROUTING`, prima della consultazione della tabella di routing;
- `INPUT`, per pacchetti diretti al sistema locale;
- `FORWARD`, per pacchetti da inoltrare;
- `OUTPUT`, per pacchetti generati localmente;
- `POSTROUTING`, dopo la decisione di routing e prima dell'invio.

> 📌 `mangle` non è la tabella principale per decidere "passa o non passa", ma permette manipolazioni utili in configurazioni avanzate.

---

### **6. Tabella `raw`**

La tabella `raw` permette di intervenire prima del normale tracciamento delle connessioni.

È usata soprattutto quando si vuole evitare che certi pacchetti siano gestiti dal connection tracking, per esempio tramite target come `NOTRACK`.

Le chain principali sono:

- `PREROUTING`, per pacchetti provenienti da qualsiasi interfaccia;
- `OUTPUT`, per pacchetti generati localmente.

`raw` e `mangle` sono meno frequenti nelle configurazioni base, ma completano il modello di Netfilter perché permettono eccezioni e manipolazioni che `filter` e `nat` da sole non coprono.

---

### **7. Flusso semplificato dei pacchetti**

Per capire dove scrivere una regola bisogna sapere quale percorso segue il pacchetto dentro Netfilter.

In un modello semplificato, considerando soprattutto `nat` e `filter`, il flusso è questo.

![](imgs/Pasted%20image%2020260709160623.png)

Quando un pacchetto arriva dalla rete:

1. entra prima nella fase di `PREROUTING` della tabella `nat`;
2. qui può essere applicato DNAT, quindi può cambiare la destinazione effettiva;
3. il kernel prende la decisione di routing;
4. se la destinazione è locale, il pacchetto passa dalla chain `INPUT` della tabella `filter`;
5. se la destinazione non è locale e il sistema fa routing, il pacchetto passa dalla chain `FORWARD`;
6. prima di uscire verso la rete può attraversare `POSTROUTING` della tabella `nat`, dove si applicano SNAT o `MASQUERADE`.

Quando invece un pacchetto è generato da un'applicazione locale:

1. viene valutato come traffico `OUTPUT`;
2. può essere filtrato dalla chain `OUTPUT` della tabella `filter`;
3. può essere sottoposto a NAT in uscita;
4. viene inviato verso la rete.

> ⚠️ Una regola nella chain sbagliata può essere formalmente corretta ma completamente inefficace.

---

### **8. Flusso completo dei pacchetti**

Il flusso completo include anche le tabelle `raw` e `mangle`.

![](imgs/Pasted%20image%2020260709160641.png)

In ingresso, prima della decisione di routing, possono intervenire:

- `raw` in `PREROUTING`;
- `mangle` in `PREROUTING`;
- `nat` in `PREROUTING`.

Dopo la decisione di routing, il pacchetto prende uno dei due percorsi:

- verso `INPUT`, se è destinato al processo locale;
- verso `FORWARD`, se deve essere inoltrato.

Sul lato locale, il pacchetto arriva al processo. Se il processo genera una risposta o un nuovo pacchetto, quel traffico attraversa il percorso di `OUTPUT`.

In uscita, prima dell'invio effettivo, possono intervenire:

- `mangle` in `POSTROUTING`;
- `nat` in `POSTROUTING`.

Il docente sottolinea che, per la maggior parte delle configurazioni didattiche e operative di base, il flusso semplificato con `filter` e `nat` è quello più usato. Il flusso completo serve però a capire perché alcune regole avanzate devono stare in punti precisi.

> 📌 Netfilter non applica una singola lista di regole: applica tabelle diverse in momenti diversi del percorso del pacchetto.

---

### **9. Target**

Il **target** è l'azione eseguita quando una regola fa match.

I target più importanti sono:

- `ACCEPT`, che autorizza il pacchetto;
- `DROP`, che scarta il pacchetto senza notificare il mittente;
- `REJECT`, che scarta il pacchetto notificando il rifiuto;
- `QUEUE`, che mette il pacchetto in coda per un'applicazione in user space;
- `RETURN`, che ritorna alla chain chiamante, con una logica simile al ritorno da una funzione;
- `LOG`, che registra informazioni sul pacchetto;
- `DNAT`, che modifica la destinazione;
- `SNAT`, che modifica la sorgente;
- `MASQUERADE`, che esegue SNAT dinamico;
- `NFLOG`, che permette logging avanzato tramite infrastrutture dedicate.

`DROP` e `REJECT` non sono equivalenti: `DROP` rende il pacchetto silenziosamente invisibile, mentre `REJECT` informa l'altra parte che il traffico è stato rifiutato.

`QUEUE` e `NFLOG` mostrano che il firewall può anche integrarsi con applicazioni o sottosistemi esterni per analisi più articolate.

> 📌 Nei primi esercizi si usano soprattutto `ACCEPT` e `DROP`, ma Netfilter offre target più ricchi per logging, NAT, code e chain personalizzate.

---

### **10. Relazione tra Netfilter e IPTables**

`iptables` serve a configurare Netfilter.

Con `iptables` si possono:

- visualizzare regole esistenti;
- creare nuove regole;
- inserirle in testa o in posizione specifica;
- aggiungerle in coda;
- eliminarle;
- svuotare una chain;
- impostare policy di default;
- creare chain personalizzate.

Le slide finali della lezione hanno un valore quasi manualistico: non vanno memorizzate come teoria astratta, ma usate come riferimento operativo per scrivere regole corrette.

Per consultare la documentazione del comando:

```bash
man iptables
```

---

### **11. Visualizzazione e diagnostica**

Per elencare le regole:

```bash
iptables -L
```

Per indicare una tabella specifica:

```bash
iptables -t nat -L
```

Opzioni utili:

- `-v`, modalità verbose, per un output più dettagliato;
- `-n`, per evitare la risoluzione di nomi DNS, nomi di porte o host;
- `--line-numbers`, per mostrare il numero delle regole e poterle cancellare più facilmente.

Esempio:

```bash
iptables -t filter -L -v -n --line-numbers
```

> 📌 `-n` è utile perché evita ritardi e ambiguità dovute alla risoluzione dei nomi: per debugging firewall è spesso meglio vedere indirizzi e porte numerici.

---

### **12. Policy di default e flush**

Ogni chain può avere una **policy di default**, cioè l'azione da applicare se nessuna regola precedente fa match.

La sintassi generale è:

```bash
iptables -t <tabella> -P <chain> <target>
```

Esempio:

```bash
iptables -t filter -P OUTPUT DROP
```

Questo imposta la policy di default della chain `OUTPUT` a `DROP`.

Per eliminare tutte le regole da una chain si usa il **flush**:

```bash
iptables -t <tabella> -F <chain>
```

Esempio:

```bash
iptables -t filter -F INPUT
```

> ⚠️ Fare flush di regole su un firewall reale può aprire o bloccare traffico critico a seconda delle policy di default. È un comando da usare con piena consapevolezza.

---

### **13. Inserimento, append e cancellazione di regole**

Per inserire una regola in una posizione specifica:

```bash
iptables -t <tabella> -I <chain> <posizione> <match> -j <target>
```

Per aggiungerla in coda alla chain:

```bash
iptables -t <tabella> -A <chain> <match> -j <target>
```

Per cancellare una regola tramite numero:

```bash
iptables -t <tabella> -D <chain> <numero>
```

Per cancellare una regola specificandone il contenuto:

```bash
iptables -t <tabella> -D <chain> <match> -j <target>
```

L'ordine è fondamentale: le regole vengono valutate sequenzialmente. Inserire in testa con `-I` può cambiare radicalmente il comportamento rispetto ad aggiungere in coda con `-A`.

> ⚠️ Due firewall con le stesse regole ma in ordine diverso possono avere comportamenti diversi.

---

### **14. Costruzione del match**

Il match è la parte della regola che definisce quali pacchetti vengono riconosciuti.

I parametri più comuni sono:

- `-s`, indirizzo sorgente;
- `-d`, indirizzo destinazione;
- `-i`, interfaccia di ingresso;
- `-o`, interfaccia di uscita;
- `-p`, protocollo, per esempio `tcp`, `udp`, `icmp`;
- `--sport`, porta sorgente;
- `--dport`, porta destinazione;
- `-m`, modulo di matching aggiuntivo.

Esempio:

```bash
iptables -A INPUT -s 10.0.0.1 -p tcp --dport 22 -j ACCEPT
```

Questa regola aggiunge alla chain `INPUT` una regola che accetta traffico TCP proveniente da `10.0.0.1` e destinato alla porta `22`.

---

### **15. Match avanzati**

Oltre ai match di base, `iptables` può usare moduli aggiuntivi tramite `-m`.

Esempi:

- `--tcp-flags`, per filtrare in base ai flag TCP;
- `--icmp-type`, per distinguere tipi di messaggi ICMP;
- `-m multiport`, per indicare più porte nella stessa regola;
- `-m owner`, per filtrare pacchetti generati da processi appartenenti a un certo utente locale;
- `-m state`, per filtrare in base allo stato della connessione.

Esempio con più porte:

```bash
iptables -A INPUT -p tcp -m multiport --dports 22,80,443 -j ACCEPT
```

Esempio concettuale con owner:

```bash
iptables -A OUTPUT -m owner --uid-owner 1000 -j DROP
```

Il match `owner` ha senso soprattutto su traffico locale in `OUTPUT`, perché solo la macchina che genera il pacchetto può sapere quale processo o utente lo ha prodotto.

> 📌 I moduli `-m` estendono il linguaggio delle regole: non si filtra più solo per indirizzi e porte, ma anche per stato, flag, utente, stringhe o altri attributi.

---

### **16. Filtraggio stateful**

Il filtraggio stateful in `iptables` permette di distinguere pacchetti che iniziano una nuova connessione da pacchetti che appartengono a connessioni già osservate.

Con il modulo `state`, gli stati principali sono:

- `NEW`, pacchetto che avvia una nuova connessione;
- `ESTABLISHED`, pacchetto appartenente a una connessione già stabilita;
- `RELATED`, pacchetto collegato a una connessione esistente.

Esempio:

```bash
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
```

Questa regola accetta in ingresso pacchetti appartenenti a connessioni già stabilite o correlate a connessioni note.

`RELATED` è importante per protocolli multi-connessione come FTP, dove una connessione dati può essere collegata alla connessione di controllo.

> 📌 Lo stateful filtering consente policy più realistiche: si può permettere il traffico di risposta senza aprire indiscriminatamente tutte le porte.

---

### **17. Target di NAT**

Alcuni target sono specifici o tipici della tabella `nat`.

`DNAT` modifica la destinazione del pacchetto. È usato, per esempio, per port forwarding o per inoltrare traffico ricevuto su un indirizzo pubblico verso un host interno.

`SNAT` modifica la sorgente del pacchetto. È usato quando host interni escono verso l'esterno usando un indirizzo diverso.

`MASQUERADE` è una forma di SNAT pensata per interfacce con indirizzo dinamico. Se l'interfaccia cambia indirizzo o viene disconnessa, il kernel gestisce automaticamente l'adattamento delle connessioni.

> ⚠️ NAT non è una policy di sicurezza completa. Può nascondere indirizzi e modificare il percorso logico dei pacchetti, ma deve essere combinato con regole di filtraggio corrette.

---

### **18. Chain personalizzate**

Oltre alle chain predefinite, è possibile creare chain definite dall'utente.

Queste chain servono a dare ordine logico alle regole e a costruire configurazioni più leggibili. Invece di avere una lista enorme dentro `INPUT`, `FORWARD` o `OUTPUT`, si può inoltrare il pacchetto a una chain dedicata a un certo servizio, reparto o tipo di controllo.

Il transcript anticipa che queste chain saranno approfondite nella lezione successiva.

> 📌 Le chain personalizzate non cambiano il principio base: organizzano meglio il percorso decisionale delle regole.

---

### **19. Riepilogo operativo**

Netfilter è un'infrastruttura del kernel Linux per intercettare e manipolare pacchetti. `iptables` è lo strumento con cui si scrivono le regole che Netfilter applica.

I concetti da tenere insieme sono:

- tabella: area funzionale (`filter`, `nat`, `mangle`, `raw`);
- chain: lista ordinata di regole;
- match: condizioni sul pacchetto;
- target: azione da eseguire;
- stato: informazione mantenuta dal connection tracking;
- NAT: modifica controllata di indirizzi e porte;
- ordine delle regole: elemento decisivo del comportamento.

> ✅ Per configurare correttamente `iptables` bisogna sapere non solo che cosa bloccare o consentire, ma anche in quale tabella, in quale chain e in quale punto della sequenza di valutazione.
