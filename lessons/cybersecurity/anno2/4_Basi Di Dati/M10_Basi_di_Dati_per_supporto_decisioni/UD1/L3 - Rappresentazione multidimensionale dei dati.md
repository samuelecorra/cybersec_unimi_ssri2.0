# **M10 UD1 Lezione 3 - Rappresentazione multidimensionale dei dati**

### **1. Introduzione**

Il **modello multidimensionale** rappresenta la base concettuale dei sistemi **OLAP**.  
A differenza dei modelli relazionali tradizionali, non si concentra sulla memorizzazione dei dati, ma sulla loro **organizzazione per l’analisi**, consentendo di osservare le informazioni da diverse **prospettive (dimensioni)**.

Questo modello permette di eseguire analisi complesse in modo intuitivo, grazie a una rappresentazione ad **alto livello** che riflette la logica con cui i decisori aziendali interpretano i dati.

---

### **2. Concetti fondamentali del modello multidimensionale**

#### **a) Fatto**

È il **fenomeno di interesse** su cui si centra l’analisi.  
Esempi:

- in una catena di negozi → la _vendita_;
    
- in una compagnia telefonica → la _telefonata_.

#### **b) Misura**

È la **proprietà numerica** associata al fatto, oggetto di analisi quantitativa.  
Esempi:

- per una vendita → _quantità venduta_, _incasso_;
    
- per una telefonata → _costo_, _durata_.

#### **c) Dimensione**

È la **prospettiva di analisi** lungo la quale si osservano i fatti.  
Esempi:

- _tempo_, _luogo_, _articolo_ nel caso delle vendite;
    
- _chiamante_, _chiamato_, _tempo_ per le telefonate.

---

### **3. Gerarchie delle dimensioni**

Ogni dimensione è organizzata secondo **livelli gerarchici**, che rappresentano diversi **gradi di aggregazione** dei dati.  
Esempi tipici:

|**Dimensione**|**Livelli di gerarchia**|
|---|---|
|**Luogo**|negozio → città → provincia → regione|
|**Articolo**|prodotto → categoria → marca|
|**Tempo**|giorno → mese → trimestre → anno|

Queste gerarchie consentono di **aggregare o disaggregare** i dati in funzione del livello di dettaglio richiesto.

---

### **4. Esempio di modello multidimensionale**

#### **Fatto:** vendita

#### **Misura:** quantità

#### **Dimensioni:** articolo, tempo, luogo

Il modello può essere rappresentato come un **cubo dei dati**, dove ogni asse rappresenta una dimensione:

```
          Tempo (trimestre)
                ↑
                │
                │
Luogo ──────────┼──────────▶ Articolo
                │
             Quantità
```

Ogni cella del cubo contiene una **misura aggregata** (es. quantità venduta) per una specifica combinazione di dimensioni (es. _prodotto X_, _trimestre 2_, _città di Milano_).

---

### **5. Analisi dal punto di vista dei manager**

Il modello multidimensionale consente diverse **prospettive di analisi**, a seconda del ruolo o dell’interesse dell’analista:

- Il **manager di città** esamina le vendite di tutti i prodotti nel tempo per la propria area.
    
- Il **manager di prodotto** analizza le vendite di uno specifico articolo in tutti i periodi e in tutte le città.
    
- Il **manager finanziario** confronta le vendite totali tra periodi diversi per tutte le città.
    
- Il **manager strategico** osserva categorie di prodotti su aree geografiche più ampie e orizzonti temporali medi.

---

### **6. Operazioni sui dati multidimensionali**

Le principali operazioni OLAP consentono di navigare e manipolare i dati nei cubi multidimensionali.

#### **a) Slice-and-Dice**

- **Slice**: seleziona un sottoinsieme di dati fissando una o più dimensioni (una “fetta” del cubo).
    
- **Dice**: proietta un sottoinsieme multidimensionale più complesso (una “sottosezione” del cubo).

Esempio:  
Selezionare le _vendite di pasta_ per _trimestre_ e _città_.

#### **b) Roll-Up**

- Aggrega i dati lungo una o più dimensioni, salendo nella gerarchia.
    
- Applica una **funzione aggregativa** (es. somma, media) sulle misure.
    
- È valida solo per **misure additive**, cioè grandezze su cui ha senso calcolare la somma.

Esempio:  
Aggregare le vendite per **anno**, partendo dai dati per **trimestre**.

#### **c) Drill-Down**

- È l’operazione inversa del roll-up.
    
- Disaggrega i dati, scendendo a un livello di dettaglio più fine.

Esempio:  
Passare dalle vendite per **città** alle vendite per **negozio**.

---

### **7. Esempi pratici**

#### **a) Slice-and-Dice**

Selezione dei dati relativi alla **pasta** per ciascun trimestre e città.

|Città|1° trim 2003|2° trim 2003|3° trim 2003|4° trim 2003|
|---|---|---|---|---|
|Milano|130000|125000|110000|145000|
|Bologna|125000|125000|135000|110000|
|Roma|80000|85000|90000|85000|
|Napoli|70000|75000|70000|90000|

---

#### **b) Drill-Down (Luogo → da città a negozio)**

|Città/Negozio|1° trim 2003|2° trim 2003|3° trim 2003|4° trim 2003|
|---|---|---|---|---|
|Milano-1|70000|65000|40000|75000|
|Milano-2|60000|60000|70000|70000|
|Bologna-1|60000|60000|55000|30000|
|Bologna-2|30000|35000|35000|40000|
|Bologna-3|35000|30000|45000|40000|

---

#### **c) Roll-Up (Tempo → da trimestre ad anno)**

|Città|2003|
|---|---|
|Milano|510000|
|Bologna|495000|
|Roma|340000|
|Napoli|305000|

---

### **8. Visualizzazione dei dati**

Per facilitare l’analisi e la comunicazione dei risultati, i dati multidimensionali vengono rappresentati **graficamente** attraverso:

- tabelle pivot,
    
- istogrammi,
    
- grafici a barre o a torta,
    
- superfici 3D,
    
- grafici a bolle o ad area impilata.

Esempio: andamento delle vendite per prodotto e trimestre:

```
Vendite (unità)
↑
│
│                  ● Pasta
│              ●
│          ●
│      ●
│  ●
│────────────────────────────────────→ Tempo
   1° T   2° T   3° T   4° T
```

---

### **9. Sintesi finale**

In questa lezione abbiamo introdotto:

- il **modello multidimensionale** come strumento concettuale per l’analisi OLAP;
    
- i concetti chiave di **fatto**, **misura** e **dimensione**, con le relative **gerarchie**;
    
- le principali **operazioni OLAP**: _slice-and-dice_, _roll-up_ e _drill-down_;
    
- e infine l’importanza della **visualizzazione grafica** per interpretare i dati.

**In sintesi:**  
Il modello multidimensionale consente di analizzare i dati aziendali in modo **dinamico, flessibile e intuitivo**, trasformando le informazioni in **conoscenza strategica** a supporto delle decisioni.

---


![](imgs/Pasted%20image%2020251125055352.png)