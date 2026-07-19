## ***Lezione 3: Interrupt vettorizzato***

---

> 📌 Questa lezione rielabora integralmente le pagine 48–53 di `M3doc.pdf`, inclusi protocollo e registri del PIC.

### **1. Il vettore di interrupt**

Nell’**interrupt vettorizzato**, dopo il riconoscimento la CPU riceve sul bus dati un identificativo della sorgente, il **vettore di interrupt**. Nel modello del PDF il vettore è di 8 bit, quindi può assumere:

$$
2^8=256
$$

valori. La CPU lo usa come indice in una tabella di 256 celle; ciascuna cella contiene l’indirizzo iniziale della RRI associata.

Il meccanismo ricorda la vector table delle istruzioni `TRAP` della LC-2:

$$
PC\leftarrow M[\operatorname{ZEXT}(vector8)].
$$

La routine corretta viene raggiunta direttamente, senza scandire tutte le interfacce.

### **2. Vantaggi e problemi di una soluzione diretta**

Il vantaggio immediato è un tempo di identificazione pressoché costante: non serve il polling. Se però ogni interfaccia dovesse generare autonomamente il proprio byte identificativo, emergerebbero problemi:

- circuiti delle interfacce più complessi;
- identificativi da assegnare e memorizzare in ogni dispositivo;
- rischio di duplicati, per esempio due schede dello stesso tipo;
- priorità non ancora risolta;
- se gli interrupt sono riabilitati durante una RRI, qualunque sorgente potrebbe interromperla.

### **3. Programmable Interrupt Controller**

Il **PIC**, *Programmable Interrupt Controller*, centralizza queste funzioni. Le interfacce segnalano al PIC le proprie richieste; il PIC decide se inoltrarle e quale rendere prioritaria. Esso:

- riceve i segnali `Ready` o di richiesta dalle interfacce;
- applica abilitazioni, maschere e priorità;
- attiva `INTREQ` verso la CPU;
- riceve `INTACK` dalla CPU;
- pone sul bus dati il vettore della richiesta scelta.

L’associazione tra linea di periferica e vettore è gestita nel controllore, quindi le interfacce non devono conoscere direttamente il proprio byte di identificazione.

### **4. Sequenza completa con PIC**

1. Una periferica produce un evento e la sua interfaccia segnala `Ready` al PIC.
2. Se la sorgente non è mascherata, il PIC registra la richiesta.
3. Fra le richieste pendenti, il PIC seleziona quella di priorità più alta.
4. Il PIC attiva `INTREQ` verso la CPU.
5. La CPU completa l’istruzione, salva il PC, blocca inizialmente altri interrupt e attiva `INTACK`.
6. Il PIC pone sul data bus il vettore della sorgente selezionata.
7. La CPU indicizza la vector table e carica l’indirizzo della RRI appropriata.
8. La routine serve il dispositivo e notifica la conclusione secondo il protocollo del controllore.

Se più richieste arrivano insieme, rimangono pendenti e vengono servite secondo la politica programmata.

### **5. Registri programmabili del PIC**

Il PIC è visibile alla CPU tramite normali registri di interfaccia.

#### **5.1. IVR — Interrupt Vector Register**

Conserva i vettori associati alle linee di periferica. Se il PIC gestisce otto dispositivi e ogni vettore è di un byte, il contenitore deve offrire otto byte logici, o un meccanismo equivalente di selezione.

#### **5.2. IPR — Interrupt Priority Register**

Definisce l’ordine di priorità. Essendo scrivibile, consente di modificare la politica via software durante la vita del sistema, senza cambiare il circuito o spostare le interfacce.

#### **5.3. IMR — Interrupt Mask Register**

Maschera selettivamente le sorgenti. Per ogni linea, un bit stabilisce se il PIC possa inoltrarne le richieste. Una periferica poco critica può così essere temporaneamente esclusa, mentre le altre restano abilitate.

> ⚠️ Il significato elettrico esatto di `0` e `1` nei bit di maschera dipende dal PIC concreto. Il concetto invariabile è la possibilità di bloccare selettivamente una sorgente.

### **6. Priorità e annidamento controllato**

Prima di riabilitare gli interrupt dentro una RRI, il software può aggiornare `IMR` in modo che soltanto periferiche più prioritarie possano interrompere il servizio corrente. `IPR` determina l’ordine; `IMR` determina l’insieme ammesso.

Questa combinazione evita il limite della riabilitazione globale nell’interrupt cablato e consente politiche dinamiche.

### **7. Confronto finale**

| Proprietà | Interrupt cablato | Interrupt vettorizzato con PIC |
| --- | --- | --- |
| identificazione | polling/daisy chain | vettore sul bus dati |
| indirizzo RRI | ingresso comune | voce specifica della vector table |
| priorità | fisica o software nel polling | programmabile con `IPR` |
| esclusione selettiva | assente nel modello base | `IMR` |
| complessità | bassa | maggiore, concentrata nel PIC |

> ✅ PIC e vettore rendono rapido il riconoscimento, programmabili le priorità e selettivo il mascheramento; la CPU raggiunge direttamente la routine della periferica scelta.
