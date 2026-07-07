## **Lezione 3: Statistiche, Conversations, Follow Stream ed Expert Info**

### **1. Perché partire dalle statistiche**

Aprire una cattura di migliaia di pacchetti e scorrerla a mano è il modo più lento (e più fallace) di lavorare. Il menu **Statistics** offre viste aggregate che, in pochi clic, dicono *cosa* contiene la traccia e *chi* sono i protagonisti. All'esame è il primo posto dove guardare.

---

### **2. Protocol Hierarchy — la radiografia della cattura**

`Statistics → Protocol Hierarchy` mostra un albero dei protocolli presenti con la percentuale di pacchetti e byte per ciascuno. In una riga si capisce se la cattura è prevalentemente web (HTTP/TLS), se contiene DNS, FTP, e-mail, o traffico anomalo.

> 💡 Esempio reale: nella traccia del 21/02 la voce **FTP** domina la gerarchia, con migliaia di pacchetti sulla porta 21 — segnale immediato che l'esercizio ruota attorno a FTP. Nella traccia del 13/06 spiccano invece **TLS** (porta 443) e **DNS**, con una piccola quota di **HTTP** dedicata quasi solo all'OCSP: è una normale sessione di navigazione cifrata.

Questa vista è anche utile per **individuare l'inatteso**: la presenza di un protocollo che non ti aspetti (es. un canale su una porta insolita) è spesso il filo da tirare.

---

### **3. Conversations ed Endpoints — chi parla con chi**

`Statistics → Conversations` elenca le **coppie di interlocutori**, organizzate per schede (Ethernet, IPv4, IPv6, TCP, UDP). Ogni riga riporta i due estremi, il numero di pacchetti, i byte scambiati, la durata e la direzione dei volumi. Ordinando per numero di pacchetti si individua subito la conversazione principale.

`Statistics → Endpoints` fa la stessa cosa ma per **singolo nodo**: elenca tutti gli host (per MAC, IP, o coppia IP:porta) con i rispettivi totali. È il modo più rapido per rispondere a "*quali sono i nodi coinvolti*" e per capire, ad esempio, se un host contatta **molti** IP esterni (tipico di scansioni o navigazione) o **uno solo** in modo intensivo (tipico di un trasferimento o di un canale C2).

> 💡 Nella scheda **Ethernet** delle Conversations Wireshark mostra i MAC con il produttore risolto dall'OUI: è lì che, nella traccia del 13/06, si legge che sia il client sia il gateway sono **VMware** — cioè macchine virtuali. Un doppio clic su una riga applica automaticamente il display filter di quella conversazione.

---

### **4. Follow Stream — ricostruire il dialogo**

Il singolo pacchetto racconta poco; la **conversazione** racconta tutto. Selezionato un pacchetto, tasto destro → **Follow → TCP Stream** (oppure *HTTP Stream*, *TLS Stream*, *UDP Stream*) riassembla l'intero flusso applicativo e lo mostra come **testo continuo**, con i due versi della comunicazione colorati diversamente (di norma rosso = client→server, blu = server→client).

È lo strumento decisivo per le domande "descrivi la sessione", "quali comandi sono stati usati", "quali file sono stati richiesti":

- Su una sessione **FTP** il Follow TCP Stream della porta 21 mostra in chiaro il dialogo `USER`/`PASS`, i comandi (`RETR`, `STOR`, `LIST`, `CWD`, `PASV`/`PORT`) e le risposte numeriche del server (`220`, `331`, `230`, `530`, `150`, `226`).
- Su una sessione **HTTP** mostra la richiesta completa (metodo, URL, header, eventuale body) e la risposta (status line, header, corpo).
- Su una sessione **TLS** mostra solo l'handshake in chiaro e poi byte cifrati: è la conferma visiva che il contenuto è protetto.

> 📌 Follow Stream applica implicitamente il filtro `tcp.stream eq N` della sessione selezionata: è il modo naturale per "entrare" in una singola conversazione dopo averla individuata nelle Conversations.

---

### **5. Statistiche accessorie utili**

- `Statistics → DNS`: riassume query e risposte, tipi di record, nomi richiesti — comodo per elencare "i domini contattati".
- `Statistics → HTTP → Requests`: elenca tutte le richieste HTTP per host e URL, ottimo per rispondere a "quali file/pagine sono stati richiesti".
- `Statistics → I/O Graph`: l'andamento del traffico nel tempo, utile per vedere picchi (es. un trasferimento o un flood).
- `Statistics → Flow Graph`: un diagramma a frecce delle interazioni tra host, che rende visibile l'ordine dei messaggi (utile per descrivere un handshake o un attacco).

---

### **6. Expert Information — le anomalie già segnalate**

`Analyze → Expert Information` raccoglie le osservazioni che Wireshark genera automaticamente, classificate per gravità: **Error**, **Warning**, **Note**, **Chat**. Vi compaiono ritrasmissioni TCP, ACK duplicati, segmenti fuori sequenza, connessioni resettate, *window full*. È una scorciatoia per trovare problemi di rete o tracce di attacchi (una raffica di RST o di ritrasmissioni può indicare uno scan o un reset attack).

> ⚠️ L'Expert Info aiuta ma non sostituisce il ragionamento: una singola ritrasmissione è normale, un pattern sistematico no. Interpreta sempre il dato nel contesto della cattura.

> ✅ **Ricapitolando** — Il menu *Statistics* dà la visione d'insieme: *Protocol Hierarchy* dice quali protocolli ci sono, *Conversations*/*Endpoints* dicono chi comunica (e con quale MAC/produttore), *DNS* e *HTTP → Requests* elencano domini e URL. *Follow Stream* ricostruisce il dialogo applicativo in chiaro (FTP, HTTP) o mostra che è cifrato (TLS), ed è lo strumento chiave per "descrivi la sessione". *Expert Information* segnala automaticamente ritrasmissioni, RST e anomalie. Partire da queste viste, e non dallo scorrimento manuale, è ciò che rende veloce l'analisi.

Precedente: [[L2 - Filtri di cattura e di visualizzazione]] · Prossima: [[L4 - Analisi per protocollo (ARP, DNS, TCP, TLS, HTTP, FTP)]].
