# **M2 UD2 Lezione 2 - Organizzazione del disco**

---

### **1. Introduzione**

La **configurazione e organizzazione del disco** sono passaggi fondamentali per rendere una memoria di massa utilizzabile dal sistema operativo.

Un disco deve essere inizializzato, suddiviso e preparato per contenere un file system, un'area di swap o altre strutture logiche. Inoltre, il sistema deve gestire i blocchi difettosi e organizzare l'accesso efficiente alle informazioni.

In questa lezione vengono trattati:

- configurazione del disco;
- struttura interna del disco;
- blocco di avvio;
- gestione dei blocchi difettosi;
- uso del disco come area di swap.

---

### **2. Configurazione del disco**

Per configurare un disco sono tipicamente necessari tre passi:

1. **formattazione fisica**;
2. **partizionamento**;
3. **formattazione logica**.

> 📌 La configurazione trasforma un supporto fisico in uno o più dischi logici utilizzabili dal sistema operativo.

#### **2.1. Formattazione fisica**

La **formattazione fisica**, detta anche **formattazione a basso livello**, divide il disco in settori gestiti direttamente dal controller.

Ogni settore contiene:

- un'**intestazione** (*header*), con informazioni di controllo;
- una zona per i **dati veri e propri**;
- un **terminatore**.

Nel terminatore viene tipicamente inserito un codice di controllo per verificare la presenza di errori nei dati memorizzati ed eventualmente correggerli.

#### **2.2. Partizionamento**

Il **partizionamento** suddivide il disco in porzioni gestite separatamente dal sistema operativo come dischi logici.

Un disco può contenere:

- una sola partizione;
- più partizioni, ciascuna con organizzazione e scopo diverso.

Ogni partizione può essere destinata a un file system, a un'area di swap o ad altri usi specializzati.

#### **2.3. Formattazione logica**

La **formattazione logica**, detta anche **formattazione ad alto livello**, crea all'interno della partizione il **file system**.

Il file system contiene le strutture dati che permettono di reperire le informazioni usando posizioni logiche, non più direttamente posizioni fisiche.

In alternativa, una partizione può essere formattata per l'uso come **disco grezzo** (*raw disk*), cioè senza file system. Questo è tipico delle aree di swap dedicate.

> 💡 Il file system offre flessibilità e astrazione; il raw disk riduce l'overhead quando serve accesso diretto e veloce.

---

### **3. Struttura interna del disco**

Un disco organizzato per l'uso del sistema operativo contiene alcune aree fondamentali:

- blocco di avvio;
- blocchi con informazioni di organizzazione;
- blocchi dati;
- strutture per directory e sottodirectory.

#### **3.1. Boot block**

Il **blocco di avvio** (*boot block*) contiene il sistema operativo o, più spesso, una sua parte iniziale: un caricatore che provvede poi a caricare il resto del sistema operativo.

Questo blocco viene usato nella fase di avvio della macchina per inizializzare il caricamento del sistema.

#### **3.2. Informazioni di organizzazione**

Nei blocchi successivi sono presenti le informazioni essenziali sull'organizzazione del disco.

Queste strutture indicano:

- dove si trovano i blocchi;
- come reperire i blocchi concatenati che costituiscono i file;
- come gestire metadati, directory e sottodirectory.

I dettagli di queste strutture dipendono dal file system e vengono approfonditi nella gestione del file system.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Blocchi difettosi**

Un disco può contenere **blocchi difettosi** (*bad blocks*), cioè blocchi che non riescono a conservare correttamente le informazioni.

Questi blocchi devono essere esclusi dall'uso normale, altrimenti potrebbero causare perdita o corruzione dei dati.

#### **4.1. Gestione manuale**

Durante la formattazione logica, i blocchi difettosi possono essere individuati e rimossi manualmente dall'insieme dei blocchi disponibili.

#### **4.2. Sector sparing**

Nel **sector sparing**, il disco mantiene un insieme di settori di riserva non assegnati all'uso normale.

Quando viene trovato un settore difettoso, questo viene logicamente sostituito con uno dei settori di riserva.

> 📌 Nel sector sparing il settore guasto resta escluso e viene rimpiazzato da un settore di ricambio.

#### **4.3. Sector forwarding**

Nel **sector forwarding**, i blocchi di riserva vengono mantenuti in una zona dedicata, spesso in fondo al disco.

Quando durante la formattazione viene incontrato un blocco difettoso, le richieste dirette a quel blocco vengono deviate verso un blocco di riserva.

#### **4.4. Sector slipping**

Nel **sector slipping**, quando viene trovato un blocco difettoso, quel blocco viene saltato e i blocchi successivi vengono fatti "slittare" in avanti, usando la zona di riserva.

In questo modo si conserva una maggiore continuità logica nella sequenza dei blocchi utilizzabili.

> ⚠️ La gestione dei bad blocks deve essere trasparente per i livelli superiori: file system e applicazioni non devono accedere a blocchi fisicamente inaffidabili.

---

### **5. Area di swap**

L'**area di swap** è una zona del disco usata per supportare multiprogrammazione e multitasking, contenendo parti della memoria virtuale del sistema.

A seconda della tecnica di gestione della memoria, l'area di swap può contenere:

- interi processi, nei sistemi basati solo su swapping;
- pagine di processo, nei sistemi con paginazione;
- segmenti di processo, nei sistemi con segmentazione.

#### **5.1. Partizione dedicata**

L'area di swap può essere una partizione dedicata gestita come **raw disk**.

In questo caso l'accesso è molto rapido, perché il gestore della memoria virtuale mantiene direttamente una mappa delle informazioni necessarie e accede ai blocchi senza attraversare tutte le funzioni del file system.

Il limite è che la dimensione dell'area di swap è fissa. Se la partizione si esaurisce, non è possibile contenere ulteriori processi o porzioni di memoria virtuale.

#### **5.2. File di swap**

In alternativa, lo swap può essere realizzato come un normale file all'interno del file system.

Questa soluzione è più flessibile, perché il file può crescere finché esiste spazio disponibile nel file system. Tuttavia è più lenta, perché l'accesso ai blocchi deve passare attraverso le funzioni di gestione del file system.

$$
\text{swap} =
\begin{cases}
\text{partizione raw} & \text{più veloce, dimensione fissa} \\
\text{file nel file system} & \text{più flessibile, accesso più lento}
\end{cases}
$$

> ⚠️ La partizione dedicata migliora le prestazioni, ma riduce la flessibilità; il file di swap è più adattabile, ma introduce overhead.

---

### **6. Strutturazione dell'area di swap**

L'area di swap è strutturata tramite:

- una **mappa**;
- un insieme di **blocchi**.

La mappa definisce l'ordine con cui i blocchi devono essere considerati per ricostruire lo spazio di indirizzamento del processo.

In particolare, nella mappa si trovano gli indici che individuano i blocchi all'interno del disco. I blocchi vengono letti nell'ordine definito dalla mappa.

Questa organizzazione consente anche di gestire blocchi di dimensione diversa, se il sistema lo prevede.

> 💡 La mappa dello swap separa l'ordine logico dello spazio del processo dalla posizione fisica dei blocchi sul disco.

---

### **7. Sintesi finale**

> ✅ L'organizzazione del disco definisce come il supporto fisico viene trasformato in una struttura logica utilizzabile dal sistema operativo.

In questa lezione sono stati analizzati:

- **formattazione fisica**, che suddivide il disco in settori;
- **partizionamento**, che crea dischi logici separati;
- **formattazione logica**, che crea il file system o prepara un raw disk;
- **boot block**, usato per avviare il caricamento del sistema operativo;
- gestione dei **blocchi difettosi** tramite esclusione, sparing, forwarding e slipping;
- uso dell'**area di swap** come partizione dedicata o file;
- struttura dello swap tramite mappa e blocchi.

La corretta organizzazione del disco è necessaria per garantire affidabilità, efficienza e supporto alla memoria virtuale.
