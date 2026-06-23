# **Lezione 3: Complementi di SNMP**

---

### **1. I due costrutti fondamentali della MIB SNMP**

La MIB SNMP si basa su **due costrutti fondamentali**:

1. **`OBJECT-TYPE`**: definisce un tipo di oggetto semplice (una singola variabile gestita con i suoi attributi, tipo, accesso e descrizione).
2. **`MODULE-IDENTITY`**: consente di raggruppare definizioni di oggetti per definire un dispositivo più complesso — un intero modulo MIB.

---

### **2. Il costrutto `MODULE-IDENTITY`**

Il costrutto `MODULE-IDENTITY` dichiara il nome e le informazioni anagrafiche di un modulo MIB. Come si vede dall'esempio, contiene non solo una descrizione e i numeri di revisione, ma in molti casi anche le **informazioni di contatto** di chi lo ha definito.

![](imgs/Pasted%20image%2020260623140824.png)

Esempio tratto dal modulo **ipMIB**:

```
ipMIB MODULE-IDENTITY
    LAST-UPDATED "941101000Z"
    ORGANIZATION "IETF SNMPv2 Working Group"
    CONTACT-INFO
        "Keith McCloghrie..."
    DESCRIPTION
        "Il modulo MIB per gestire implementazioni IP e ICMP,
         escludendo però la gestione delle rotte IP."
    REVISION "019331000Z"
    .....
::= { mib-2 48 }
```

- `LAST-UPDATED` → data dell'ultima revisione;
- `ORGANIZATION` → gruppo che mantiene il modulo;
- `CONTACT-INFO` → riferimento al responsabile della definizione;
- `DESCRIPTION` → cosa gestisce la MIB;
- `::= { mib-2 48 }` → punto di ancoraggio del modulo nell'albero globale degli OID.

---

### **3. Il modulo UDP nella MIB-II**

Consideriamo un modulo per la gestione dei pacchetti UDP entranti in un dispositivo tipo router. Gli oggetti del modulo hanno ciascuno un OID, un nome, un tipo e la propria semantica. Quasi tutti sono contatori a 32 bit (`Counter32`), tranne l'ultimo che è una `SEQUENCE` (a sua volta composta da sotto-elementi più semplici).

![](imgs/Pasted%20image%2020260623140838.png)

| OID | Nome | Tipo | Significato |
|---|---|---|---|
| `1.3.6.1.2.1.7.1` | `UDPInDatagr` | `Counter32` | Numero di datagrammi UDP consegnati a questo nodo |
| `1.3.6.1.2.1.7.2` | `UDPNoPorte` | `Counter32` | Numero di datagrammi non consegnabili per mancanza di applicazioni alla porta di destinazione |
| `1.3.6.1.2.1.7.3` | `UDPInError` | `Counter32` | Numero di datagrammi non consegnabili per altri motivi |
| `1.3.6.1.2.1.7.4` | `UDPDatagrInv` | `Counter32` | Numero di datagrammi inviati |
| `1.3.6.1.2.1.7.5` | `UDPTabella` | `Sequence` | Un elemento per ogni porta in uso dall'applicazione: dà il numero di porta e l'indirizzo IP |

Tutti questi oggetti danno origine a un database che contiene lo stato di quel dispositivo nel momento in cui viene interrogato.

> 📌 **Non è nostro compito definire i moduli.** I moduli e i relativi oggetti sono stati definiti per noi: in parte dal **cuore dello standard** (oggetti predefiniti), in parte dai **fabbricanti di dispositivi di rete** che supportano SNMP — quando si acquista un prodotto, il database MIB è già definito al suo interno. Ci interessa però capire come funzionano, ad esempio come vengono assegnati gli OID.

---

### **4. Assegnazione degli OID: struttura gerarchica**

Gli OID vengono assegnati attraverso una **struttura gerarchica ad albero**: i vari moduli sono composti di oggetti; alcuni oggetti possono essere composti di oggetti più semplici. Ad ogni biforcazione dell'albero viene attribuito un **nome** e un **numero**.

![](imgs/Pasted%20image%2020260623140951.png)

Esempio: `1.3.6.1.2.1.7.1`

| Numero | Nome | Significato |
|---|---|---|
| `1` | ISO | Nell'albero di assegnazione degli OID standardizzato dall'ISO |
| `3` | ISO-ident | Sotto-albero standardizzato dal **Dipartimento della Difesa degli Stati Uniti (US DoD)** — l'ISO potrebbe avere altri sotto-alberi standardizzati da altri enti |
| `6` | US DoD | Oggetti usati sulle reti |
| `1` | Internet | Ambito Internet |
| `2` | management | Oggetti di gestione |
| `1` | MIB-2 | Blocco predefinito degli oggetti della MIB-II (seconda versione) |
| `7` | UDP | All'interno degli oggetti di management ci sono oggetti per TCP, UDP, ICMP, ecc. — `7` identifica UDP |
| `1` | udpInDatagrams | Contatore dei datagrammi UDP in ingresso |

> 💡 Questo schema è stato adottato anche dall'ISO come standard internazionale, garantendo unicità globale degli identificatori.

---

### **5. Modalità di accesso ai dati: richiesta/risposta e trap**

Una volta compresa la struttura dei dati di stato, la domanda è: come vi si accede dall'esterno?

![](imgs/Pasted%20image%2020260623141114.png)

SNMP prevede due modalità:

#### **a. Modalità sincrona (richiesta/risposta)**

Il client SNMP — che gira tipicamente su un PC/host dell'amministratore — richiede **esplicitamente** ai vari dispositivi di comunicare lo stato dei loro oggetti. Questo avviene di solito in modo automatico, e ciò che si vede a schermo è una **rappresentazione grafica dello stato degli oggetti** che compongono la rete. È la tecnica di **polling** periodico.

#### **b. Modalità asincrona (Trap)**

Soprattutto ai fini di **allarme** — cioè per sollecitare l'intervento su un determinato dispositivo — si vuole che sia il **dispositivo a prendere l'iniziativa**. Il client se ne sta in attesa; quando lo stato di un certo attributo di un oggetto cambia, il dispositivo lo comunica spontaneamente al client (**messaggio trap**), che lo segnala all'operatore per consentirgli di intervenire e ripristinare le condizioni di funzionamento.

---

### **6. Software di gestione di rete**

Al giorno d'oggi esistono molte implementazioni SNMP da parte dei produttori di apparati di rete, corredate da una **interfaccia grafica** per l'amministratore. Per l'amministratore non è necessario conoscere i dettagli della MIB o del protocollo sottostante: questi programmi gestionali **nascondono i dettagli di SNMP** mettendo l'operatore in grado di intervenire sui dispositivi problematici o di cambiare le configurazioni quando serve.

Esempi:

- **CiscoWorks** — soluzione Cisco per il monitoraggio e la configurazione centralizzata.
- **OpenView** — piattaforma HP per la supervisione di reti eterogenee.
- **Solstice** — sistema Sun Microsystems per la gestione distribuita.

Esistono anche molte soluzioni **free e open source** con funzionalità equivalenti.

---

### **7. Conclusione**

SNMP, grazie alla sua architettura modulare basata su MIB e OID, rappresenta un sistema universale di monitoraggio per reti TCP/IP. I costrutti `OBJECT-TYPE` e `MODULE-IDENTITY` rendono le MIB documentate e scalabili, mentre i meccanismi di polling (sincrono) e trap (asincrono) permettono di bilanciare efficienza e tempestività. In combinazione con i moderni software di gestione, SNMP continua a essere la spina dorsale della network administration.
