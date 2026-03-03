# **Lezione 0 - Intro Unità Didattica 9 - Elementi di sicurezza dei protocolli TCP/IP**

---

## Introduzione

La suite **TCP/IP**, pur essendo la base di Internet, è nata in un’epoca in cui la **sicurezza non era una priorità di progetto**.  
Molti protocolli fondamentali — come IP, TCP, UDP e ICMP — furono concepiti in ambienti di fiducia e **non prevedono meccanismi di autenticazione o cifratura**.

Col tempo, questa assenza di protezioni ha esposto la rete a numerose **vulnerabilità**:  
spoofing, hijacking di sessioni, denial of service, sniffing del traffico e manipolazione dei pacchetti.

> L’obiettivo di questa unità è comprendere **le principali debolezze strutturali dei protocolli TCP/IP** e analizzare **le contromisure moderne**, come IPsec e TLS, che consentono di trasformare una rete intrinsecamente insicura in una rete affidabile e resiliente.