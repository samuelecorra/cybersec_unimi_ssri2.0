# **Lezione 0 - Intro Unità Didattica 7 - Instradamento dinamico**

---

### **Introduzione**

L’**instradamento dinamico** permette ai router di **aggiornare automaticamente le proprie tabelle di routing** in base alle variazioni della rete, senza intervento manuale.  
A differenza dell’instradamento statico, in cui le rotte sono fissate dall’amministratore, i protocolli dinamici consentono alla rete di **adattarsi ai guasti e ai cambiamenti topologici**.

Esistono due categorie principali:

- **IGP (Interior Gateway Protocol)** per l’instradamento **interno** a una rete o dominio amministrativo (es. RIP, OSPF);
    
- **EGP (Exterior Gateway Protocol)** per l’instradamento **tra reti diverse** o su scala Internet (es. BGP).
    

> L’obiettivo di questa unità è comprendere il funzionamento dei protocolli dinamici e imparare a **configurare un gateway** capace di aggiornare le rotte in modo autonomo e affidabile.