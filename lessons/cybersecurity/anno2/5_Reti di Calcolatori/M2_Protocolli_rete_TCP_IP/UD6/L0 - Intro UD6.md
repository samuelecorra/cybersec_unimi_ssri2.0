# **Lezione 0 - Intro Unità Didattica 6 - User Datagram Protocol (UDP)**

---

### **Introduzione**

Il **protocollo UDP** è un protocollo di **trasporto non orientato alla connessione**, basato su **datagrammi indipendenti**.  
Non stabilisce alcun collegamento prima dell’invio, **non garantisce consegna, ordine né controllo di flusso**, ma è **molto più veloce e leggero** di TCP.

Viene usato quando la **velocità è più importante dell’affidabilità**, come in **streaming, giochi online, DNS e VoIP**.  
Ogni datagramma UDP contiene un **header di 8 byte** con quattro campi: porta sorgente, porta destinazione, lunghezza e checksum.

> In sintesi: UDP punta alla **rapidità**, lasciando alle applicazioni il compito di gestire eventuali perdite o ritrasmissioni.