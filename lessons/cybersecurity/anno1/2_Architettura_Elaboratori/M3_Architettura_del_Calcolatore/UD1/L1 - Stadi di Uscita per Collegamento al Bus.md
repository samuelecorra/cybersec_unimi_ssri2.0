## ***Lezione 1: Stadi di uscita per il collegamento al bus***

---

> 📌 Questa lezione rielabora integralmente le pagine 1–6 di `M3doc.pdf`, comprese le configurazioni circuitali illustrate.

### **1. Dal livello software al livello hardware**

Nel modulo precedente la CPU LC-2 è stata osservata dal lato software: linguaggio macchina, assembly e traduzione di programmi di alto livello. Ora si torna al livello fisico, dove bus, porte e bistabili devono realizzare elettricamente quei trasferimenti.

Una linea di bus è un conduttore condiviso sul quale una sorgente impone un livello di tensione, interpretato come valore logico, e uno o più destinatari lo leggono. La domanda decisiva è: **quanti dispositivi possono pilotare la linea e chi stabilisce quando possono farlo?**

### **2. Tre tipi di linee di bus**

| Tipo di linea | Sorgenti possibili | Coordinamento | Esempio |
| --- | ---: | --- | --- |
| **monosorgente** | una sola | non necessario | bus indirizzi, finché la CPU è l’unico master |
| **multisorgente sincrona** | più dispositivi, uno per volta | un arbitro assegna il turno | bus dati |
| **multisorgente asincrona** | più dispositivi in istanti indipendenti | non si può assegnare preventivamente un turno | richieste di interrupt o di possesso del bus |

In una linea monosorgente un solo dispositivo genera il valore elettrico, mentre molti ingressi possono riceverlo. In una linea multisorgente sincrona cambia invece il trasmettitore: durante una scrittura la CPU pilota il bus dati e la memoria riceve; durante una lettura la memoria pilota il bus e la CPU riceve. Il controllo del sistema impedisce che due sorgenti trasmettano insieme.

Nelle linee multisorgente asincrone ciascun dispositivo può dover segnalare un evento quando questo accade nel mondo esterno. Non esiste un istante concordato nel quale “tocca” a una sorgente: il circuito deve quindi tollerare richieste simultanee.

### **3. Uscita totem-pole**

Uno stadio **totem-pole** può trovarsi in due stati attivi:

- `0` a bassa impedenza: assorbe corrente e mantiene la tensione vicina a zero;
- `1` a bassa impedenza: eroga corrente e mantiene la tensione vicina a quella di alimentazione.

In entrambi i casi la sorgente pilota energicamente la linea. Ciò rende il fronte rapido e il livello robusto ai disturbi, ma è sicuro soltanto quando una sola uscita è collegata come sorgente.

Se due uscite totem-pole condividono una linea e una forza `1` mentre l’altra forza `0`, si crea un percorso a bassa resistenza fra alimentazione e massa. Il risultato non è soltanto un valore logico ambiguo: circola una corrente elevata che può danneggiare i componenti.

> ⚠️ Due uscite attive non possono essere collegate insieme confidando che “vinca” lo zero o l’uno. Senza una struttura apposita si ha contesa elettrica.

### **4. Uscita tri-state**

Per una linea multisorgente sincrona serve un terzo stato:

| Abilitazione | Dato | Uscita fisica |
| ---: | ---: | --- |
| 0 | qualunque | `Z`, alta impedenza |
| 1 | 0 | `0`, bassa impedenza |
| 1 | 1 | `1`, bassa impedenza |

`Z` non è un terzo valore booleano: indica che l’uscita è elettricamente quasi scollegata e non determina la tensione del nodo. Con $n$ sorgenti potenziali, l’arbitro deve garantire che:

$$
1\ \text{sorgente sia abilitata},
\qquad n-1\ \text{sorgenti siano in }Z.
$$

Il filo di abilitazione dello stadio stabilisce se il dispositivo può pilotare la linea. Sul bus dati, per esempio, la logica di controllo abilita la memoria selezionata durante una lettura oppure la CPU durante una scrittura.

> 💡 “Tri-state” descrive tre comportamenti elettrici (`0`, `1`, `Z`), ma l’informazione utile rimane binaria: quando il dispositivo è abilitato comunica soltanto `0` oppure `1`.

### **5. Uscita open-collector e resistenza di pull-up**

La sincronizzazione non è disponibile per le linee multisorgente asincrone. Lo stadio **open-collector** (o **open-drain** in tecnologia MOS) ammette allora soltanto:

- `0` a bassa impedenza: il dispositivo porta la linea a massa;
- `Z` ad alta impedenza: il dispositivo non impone alcun livello.

Più uscite possono così condividere il filo senza conflitto: se più dispositivi richiedono contemporaneamente lo zero, tutti tendono verso lo stesso livello. Rimane però un problema quando tutte le uscite sono in `Z`: la linea è flottante e può assumere valori casuali a causa dei disturbi.

Una resistenza di **pull-up** collega debolmente la linea all’alimentazione:

| Sorgenti che forzano `0` | Stato delle altre | Valore letto |
| ---: | --- | ---: |
| nessuna | tutte in `Z` | `1`, imposto dal pull-up |
| almeno una | `Z` oppure altre a `0` | `0` |

In logica positiva il collegamento realizza una funzione **wired-AND**: la linea vale `1` soltanto se ogni sorgente la lascia inattiva. Poiché spesso il segnale è attivo basso, lo zero comune significa “almeno una richiesta presente”.

La resistenza limita la corrente quando una sorgente forza lo zero. Senza di essa, collegare direttamente la linea all’alimentazione riprodurrebbe il corto circuito che si voleva evitare.

### **6. Scelta dello stadio corretto**

| Situazione | Stadio adatto | Condizione di sicurezza |
| --- | --- | --- |
| una sola sorgente fisica | totem-pole | nessun’altra uscita attiva sul nodo |
| più sorgenti coordinate | tri-state | esattamente una abilitata |
| più sorgenti non coordinate | open-collector + pull-up | ciascuna può soltanto forzare `0` |

Il bus indirizzi è monosorgente solo se la CPU resta sempre master. Quando sarà introdotto il DMA, anche un controllore esterno dovrà poter generare indirizzi: le uscite della CPU dovranno allora diventare tri-state per cedere il bus.

> ✅ La topologia logica del bus determina il comportamento elettrico richiesto: totem-pole per una sorgente, tri-state per sorgenti arbitrate, open-collector con pull-up per richieste asincrone condivise.
