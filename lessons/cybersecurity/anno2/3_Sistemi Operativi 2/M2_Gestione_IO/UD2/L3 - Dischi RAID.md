# **M2 UD2 Lezione 3 - Dischi RAID**

---

### **1. Introduzione**

I **dischi RAID** sono gruppi di dischi fisici gestiti in modo integrato per apparire al sistema operativo come un unico disco logico con caratteristiche migliori.

Il termine **RAID** indica un **array ridondante di dischi a basso costo** (*Redundant Array of Inexpensive Disks*).

Gli obiettivi principali sono:

- aumentare la capacità complessiva usando più dischi economici;
- migliorare l'affidabilità tramite ridondanza;
- migliorare le prestazioni tramite parallelismo;
- combinare affidabilità e prestazioni in configurazioni ibride.

> 📌 Un RAID combina più dischi fisici per ottenere un disco logico più capiente, più affidabile o più veloce rispetto ai singoli dischi.

---

### **2. Obiettivi dei sistemi RAID**

#### **2.1. Grande capacità a costo ridotto**

Il primo obiettivo è costruire unità di memoria di massa di grande capacità senza dover acquistare dischi singoli molto grandi e costosi.

La somma delle capacità di più dischi piccoli può raggiungere la capacità di un disco di grandi dimensioni con un costo complessivamente inferiore.

#### **2.2. Ridondanza e affidabilità**

Il secondo obiettivo è introdurre **ridondanza** nella memorizzazione.

La ridondanza può essere ottenuta tramite:

- replicazione completa dei dati;
- informazioni ridondanti di controllo;
- codici per la rilevazione e correzione degli errori;
- bit o blocchi di parità.

Queste informazioni permettono di ricostruire dati persi o corrotti a causa di guasti del disco o errori di memorizzazione.

#### **2.3. Parallelismo negli accessi**

Il terzo obiettivo è migliorare le prestazioni tramite **parallelismo**.

Distribuendo i dati di uno stesso file su più dischi, i dischi possono operare contemporaneamente e fornire globalmente una quantità maggiore di dati per unità di tempo.

---

### **3. Affidabilità nei RAID**

Per valutare l'affidabilità di un sistema RAID si considera il tempo medio tra i guasti dell'intero array, legato al guasto dei singoli dischi e al tempo necessario per ripararli.

Parametri importanti:

- **MTTF** (*Mean Time To Failure*): tempo medio al guasto;
- **MTTR** (*Mean Time To Repair*): tempo medio di riparazione;
- **MTTDL** (*Mean Time To Data Loss*): tempo medio prima della perdita irreversibile dei dati.

> ⚠️ L'affidabilità di un RAID non dipende solo dal fatto che un disco possa guastarsi, ma anche da quanto rapidamente il sistema viene riparato prima che avvenga un ulteriore guasto critico.

---

### **4. Data striping**

La tecnica usata per ottenere parallelismo è il **data striping**, cioè la distribuzione dei dati su più dischi.

#### **4.1. Striping a livello di bit**

Nel **bit-level striping**, i singoli bit vengono distribuiti tra i dischi.

Per esempio, il primo bit di un byte può essere memorizzato sul primo disco, il secondo bit sul secondo disco, il terzo sul terzo e così via.

#### **4.2. Striping a livello di blocco**

Nel **block-level striping**, i dati vengono distribuiti a blocchi:

- il primo blocco viene memorizzato sul primo disco;
- il secondo blocco sul secondo disco;
- il terzo blocco sul terzo disco;
- poi la distribuzione continua ciclicamente.

> 💡 Lo striping aumenta il parallelismo perché più dischi partecipano contemporaneamente alla lettura o scrittura dello stesso file.

---

### **5. RAID 0**

Il **RAID 0** è il livello più semplice orientato alle prestazioni.

Il file viene diviso in blocchi o porzioni, ciascuna memorizzata su un disco diverso. Questo permette di leggere e scrivere più parti del file in parallelo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.1. Caratteristiche**

- usa striping;
- aumenta il parallelismo;
- migliora la velocità di accesso;
- non introduce ridondanza.

> ⚠️ RAID 0 non tollera guasti: se un disco si rompe, una parte dei dati diventa indisponibile e l'intero array può risultare inutilizzabile.

---

### **6. RAID 1**

Il **RAID 1** è diretto a garantire tolleranza ai guasti tramite **mirroring**.

Ogni disco viene duplicato da un disco identico che contiene una copia speculare di tutte le informazioni.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.1. Caratteristiche**

- garantisce elevata tolleranza al guasto di un singolo disco;
- mantiene una copia completa dei dati;
- richiede ridondanza fisica del 100%;
- può migliorare le letture, perché è possibile leggere da una delle copie.

> 📌 RAID 1 sacrifica capacità per affidabilità: metà dello spazio fisico viene usato per la copia ridondante.

---

### **7. RAID 2**

Il **RAID 2** riduce il costo della ridondanza completa usando **codici correttori di errore**.

Invece di duplicare tutti i dati, introduce informazioni ridondanti che permettono di rilevare e correggere errori.

Per esempio, per proteggere dati distribuiti su quattro dischi, possono essere necessari alcuni dischi aggiuntivi contenenti codici di correzione.

#### **7.1. Limiti**

RAID 2 non introduce un parallelismo particolarmente vantaggioso come RAID 0 e richiede elaborazione per calcolare, verificare e usare i codici correttori.

> ⚠️ RAID 2 riduce la duplicazione completa, ma aumenta la complessità di calcolo e gestione.

---

### **8. RAID 3**

Il **RAID 3** usa parità dedicata a livello di bit o gruppi di bit.

Invece di usare codici correttori complessi, calcola un bit di parità per proteggere i dati distribuiti sui dischi. La parità viene memorizzata su un disco aggiuntivo.

#### **8.1. Ricostruzione**

In caso di guasto di un disco, i bit mancanti possono essere ricostruiti combinando:

- i bit presenti sugli altri dischi dati;
- il bit di parità;
- la posizione del disco guasto.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2. Distribuzione per gruppi di bit**

La distribuzione può anche raggruppare i bit di ogni byte. Per esempio, usando quattro dischi dati, un byte da 8 bit può essere diviso in gruppi da 2 bit per disco.

Questo aumenta il parallelismo nell'accesso alle informazioni.

> 💡 RAID 3 semplifica la ridondanza rispetto agli ECC, ma concentra la parità su un disco dedicato.

---

### **9. RAID 4**

Il **RAID 4** opera a livello di blocchi, non più a livello di bit o gruppi di bit.

I blocchi di dati vengono distribuiti sui dischi dati, mentre un disco dedicato conserva la parità calcolata bit per bit sui blocchi corrispondenti.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.1. Ricostruzione**

Se un blocco o un disco dati si guasta, il contenuto può essere ricostruito combinando:

- i blocchi corrispondenti sugli altri dischi;
- il blocco di parità presente sul disco di ridondanza.

#### **9.2. Limite**

Il limite di RAID 4 è che il disco di parità viene usato in molte operazioni, diventando un collo di bottiglia.

> ⚠️ RAID 3 e RAID 4 concentrano la parità su un solo disco: questo disco rischia di limitare le prestazioni globali.

---

### **10. RAID 5**

Il **RAID 5** distribuisce le informazioni di parità su tutti i dischi, evitando che esista un unico disco di parità.

In pratica, quella che in RAID 4 era una zona concentrata su un disco viene spezzata in porzioni distribuite tra i vari dischi.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.1. Caratteristiche**

- usa striping a blocchi;
- distribuisce la parità;
- tollera il guasto di un disco;
- migliora il parallelismo rispetto a RAID 4;
- evita il collo di bottiglia del disco di parità dedicato.

> ✅ RAID 5 è un buon compromesso tra capacità, prestazioni e tolleranza al guasto di un disco.

---

### **11. RAID 6**

Il **RAID 6** estende RAID 5 introducendo ridondanza aggiuntiva per tollerare guasti multipli.

Usa tecniche di codifica più robuste, capaci di rilevare e correggere errori dovuti al guasto di due dischi.

Per esempio, alcune tecniche permettono di proteggere gruppi di bit dati con pochi bit ridondanti aggiuntivi, consentendo il recupero anche in presenza di guasti doppi.

#### **11.1. Caratteristiche**

- tollera fino a due guasti simultanei;
- aumenta l'affidabilità;
- richiede più ridondanza e più calcolo rispetto a RAID 5.

> ⚠️ RAID 6 migliora la sopravvivenza ai guasti multipli, ma aumenta overhead di spazio e di calcolo.

---

### **12. RAID 0+1 e RAID 1+0**

Esistono configurazioni ibride che combinano striping e mirroring.

#### **12.1. RAID 0+1**

Nel **RAID 0+1**, prima si crea uno striping su un gruppo di dischi, poi l'intero gruppo viene duplicato con mirroring.

In caso di guasto di un disco, può essere considerato non accessibile l'intero gruppo a cui quel disco appartiene.

#### **12.2. RAID 1+0**

Nel **RAID 1+0**, o **RAID 10**, prima si crea il mirroring di ciascun disco, poi le coppie mirror vengono collegate tramite striping.

Se un disco si guasta, solo quel disco viene sostituito logicamente dalla sua copia nella coppia. Il resto dello stripe continua a essere accessibile.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 RAID 1+0 è in genere più robusto di RAID 0+1, perché protegge ogni disco singolarmente prima di applicare lo striping.

---

### **13. Sintesi finale**

> ✅ Le tecniche RAID combinano più dischi per ridurre i costi, aumentare l'affidabilità tramite ridondanza e migliorare le prestazioni tramite parallelismo.

I livelli principali sono:

- **RAID 0**, striping senza ridondanza, orientato alle prestazioni;
- **RAID 1**, mirroring completo, orientato all'affidabilità;
- **RAID 2**, codici correttori di errore;
- **RAID 3**, parità dedicata a livello di bit o gruppi di bit;
- **RAID 4**, parità dedicata a livello di blocchi;
- **RAID 5**, parità distribuita;
- **RAID 6**, ridondanza sufficiente per tollerare guasti multipli;
- **RAID 0+1** e **RAID 1+0**, combinazioni di striping e mirroring.

La scelta del livello RAID dipende dal compromesso desiderato tra costo, capacità utile, prestazioni e tolleranza ai guasti.
