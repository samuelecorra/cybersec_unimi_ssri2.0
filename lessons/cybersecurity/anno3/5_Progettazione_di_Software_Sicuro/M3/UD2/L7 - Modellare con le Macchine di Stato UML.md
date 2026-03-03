# **Lezione 9: Modellare con le Macchine di Stato UML — Caso di Studio Tornello Metro**

### 1. Introduzione al caso di studio

In questa lezione viene presentato un caso di studio completo che mostra come utilizzare le macchine di stato UML per modellare il comportamento di un sistema reale attraverso un processo di raffinamento progressivo.

Il sistema considerato è un **tornello della metropolitana**, dispositivo che controlla l’accesso degli utenti tramite l’inserimento di una moneta o di un titolo di viaggio. Il caso di studio consente di comprendere come un modello UML possa evolvere da una descrizione semplice fino a una rappresentazione più complessa che include:

- gestione di comportamenti anomali;
    
- gestione degli stati di allarme;
    
- modalità di manutenzione e diagnostica;
    
- procedure di ripristino del sistema.
    

Questo esempio evidenzia la potenza della modellazione UML nel rappresentare sistemi reattivi e critici.

---

### 2. Modello base del tornello

Nel modello iniziale il tornello può trovarsi in due stati principali:

- **Locked (bloccato)** — il passaggio non è consentito;
    
- **Unlocked (sbloccato)** — il passaggio è consentito.
    

Il comportamento base è il seguente:

- quando il sistema è bloccato e viene inserita una moneta, il tornello si sblocca;
    
- quando una persona attraversa il tornello, il sistema torna allo stato bloccato.
    

Questo comportamento è illustrato nel diagramma mostrato nella slide iniziale (pagina 1), che rappresenta una macchina di stato semplice con due stati e due transizioni principali.

---

### 3. Gestione dei comportamenti anomali

Il modello viene successivamente raffinato introducendo situazioni anomale che possono verificarsi durante l’uso del tornello.

Due scenari principali sono considerati:

1. una persona tenta di passare senza inserire la moneta;
    
2. una persona inserisce una moneta quando il tornello è già sbloccato.
    

Nel primo caso il sistema deve attivare un **allarme**. Nel secondo caso può essere necessario gestire l’evento senza compromettere il funzionamento del sistema.

Il diagramma mostrato nella slide sui comportamenti anomali (pagina 2) introduce quindi nuove transizioni associate a eventi di errore e azioni di segnalazione.

---

### 4. Gestione della situazione di allarme

Il passo successivo consiste nell’introdurre uno stato specifico per la gestione della condizione di allarme.

Quando si verifica una violazione:

- il sistema entra nello stato **Violation**;
    
- il sistema rimane in questo stato finché non viene effettuato un ripristino;
    
- il ripristino comporta lo spegnimento dell’allarme e il ritorno allo stato operativo.
    

Il diagramma mostrato nella slide sulla gestione dell’allarme evidenzia come la macchina di stato venga estesa per includere un nuovo ramo comportamentale dedicato alla sicurezza.

Questo passaggio è particolarmente rilevante nel contesto della progettazione di software sicuro, poiché introduce esplicitamente la gestione degli errori e delle violazioni.

---

### 5. Modalità di manutenzione del sistema

Un ulteriore raffinamento introduce la modalità di manutenzione.

Quando è necessario effettuare manutenzione:

- il sistema viene disattivato;
    
- il sistema entra nello stato **Diagnosi**;
    
- lo stato corrente del sistema viene congelato.
    

Il congelamento dello stato è importante perché consente di ripristinare successivamente la configurazione operativa senza perdita di informazioni.

Il diagramma mostrato nella slide sulla manutenzione del tornello (pagina 3) rappresenta questa modalità come uno stato composto separato dalla modalità operativa normale.

---

### 6. Diagnostica del tornello

Durante la modalità di diagnostica i tecnici eseguono controlli sui sensori e sulle funzionalità del dispositivo.

La macchina di stato include quindi sottostati specifici per:

- test di inserimento moneta;
    
- test di passaggio;
    
- test dei meccanismi di blocco;
    
- test dell’allarme.
    

Il diagramma mostrato nella slide sulla diagnostica (pagina 3) rappresenta questa fase come una macchina di stato interna, dimostrando l’uso degli stati composti UML per modellare procedure tecniche.

---

### 7. Ripristino del sistema dopo la diagnostica

Terminata la fase di diagnostica, il sistema può essere riattivato in due modalità:

1. mediante il pulsante **Reset**, che imposta il sistema nello stato Locked con allarme spento e luce di cortesia spenta;
    
2. ripristinando la configurazione precedente alla diagnostica.
    

La seconda modalità utilizza il concetto di **History state**, che consente di tornare allo stato operativo precedente salvato prima della manutenzione.

Il diagramma mostrato nella slide sul ripristino (pagina 4) evidenzia come il sistema possa ritornare alla modalità normale tramite diversi percorsi.

---

### 8. Raffinamento progressivo del modello

L’intero caso di studio dimostra un principio fondamentale della modellazione UML: il comportamento di un sistema complesso può essere sviluppato mediante una sequenza di passi di raffinamento.

Si parte da un modello semplice e si introducono progressivamente:

- gestione degli errori;
    
- modalità operative aggiuntive;
    
- sottostati tecnici;
    
- meccanismi di recupero.
    

Questo approccio incrementale consente di mantenere il modello comprensibile anche quando il sistema diventa complesso.

---

### 9. Importanza per la progettazione di software sicuro

Il caso del tornello evidenzia aspetti fondamentali della progettazione sicura:

- gestione esplicita delle condizioni di errore;
    
- presenza di stati di sicurezza (violazione);
    
- possibilità di recupero controllato;
    
- separazione tra modalità operative e modalità di manutenzione;
    
- controllo degli eventi anomali.
    

La modellazione tramite macchine di stato consente di individuare vulnerabilità logiche già in fase di progettazione, riducendo il rischio di errori implementativi.

---

### 10. Sintesi della lezione

In questa lezione è stato presentato un caso di studio completo che mostra come modellare un sistema reale mediante macchine di stato UML attraverso raffinamenti successivi.

Sono stati analizzati:

- modello base del tornello;
    
- gestione dei comportamenti anomali;
    
- stato di violazione;
    
- modalità di manutenzione;
    
- diagnostica;
    
- procedure di ripristino;
    
- uso degli stati history;
    
- raffinamento iterativo.
    

Un aspetto fondamentale da ricordare è che UML supporta un processo iterativo di modellazione che consente di evolvere progressivamente il modello del sistema.

---

### 11. Prossimi passi

Il modulo si conclude evidenziando che la modellazione UML permette di rappresentare sistemi complessi mediante raffinamento progressivo, principio che verrà utilizzato nelle successive attività di progettazione.