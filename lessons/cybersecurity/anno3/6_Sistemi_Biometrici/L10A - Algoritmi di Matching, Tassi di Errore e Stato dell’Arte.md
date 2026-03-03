
# **Lezione 10a – Algoritmi di Matching, Tassi di Errore e Stato dell’Arte**

---

(dispensa originale in fondo alla pagina)

### **1. Introduzione: il problema del matching**

Il matching di due impronte digitali risponde a una domanda binaria:

**“Queste due impronte provengono dalla stessa persona?”**

Il sistema (AFIS) può produrre quattro situazioni:

- **Correct Match:** stessa persona → AFIS dice _match_
    
- **False Non-Match:** stessa persona → AFIS dice _no match_
    
- **False Match:** persone diverse → AFIS dice _match_
    
- **Correct Non-Match:** persone diverse → AFIS dice _no match_
    

Le due tipologie di errore che contano sono:

- **FMR (False Match Rate)**: accetta un impostore
    
- **FNMR (False Non-Match Rate)**: rifiuta un genuino
    

Lo standard preferito a livello internazionale è ISO/IEC 19795: usare **FMR/FNMR** invece delle denominazioni FAR/FRR.

---

### **2. Perché è difficile fare matching?**

Il matching è un problema difficile per via dell’enorme variabilità **intra-classe**:  
la stessa persona produce impronte molto diverse.

Le cause:

- sensore sporco
    
- sudore, dita secche, dita bagnate
    
- tagli, cicatrici, abrasioni
    
- pressione troppo forte o troppo debole
    
- piccola area del sensore → impronta parziale
    
- elasticità della pelle → **deformazioni non lineari**
    

I due esempi mostrano differenze di qualità e differente area di overlap nella stessa impronta.

L’effetto finale:

- compaiono **minuzie spurie**
    
- alcune minuzie reali vengono **perse**
    
- stesso dito → impronte che sembrano “quasi diverse”

![](imgs/Pasted%20image%2020260218141603.png)

---

### **3. Contromisure alle deformazioni**

Esistono due livelli di contromisure:

**1. Trasformazioni lineari:**

- rotazione
    
- traslazione

![](imgs/Pasted%20image%2020260218141724.png)

Sono quelle mostrate in figura 1.1: l’immagine blu (input) viene riallineata a quella rossa (template).

**2. Deformazioni non lineari:**  
Usare modelli che stimano come la pelle si è deformata sul sensore.

![](imgs/Pasted%20image%2020260218141753.png)

la deformazione aumenta man mano che ci si allontana dal centro dell’impronta, alterando le distanze tra minuzie → matching più difficile.

---

### **4. Come funziona il matching?**

Ogni algoritmo produce un **matching score**, di solito compreso fra:

- _0_ = nessuna similarità
    
- _100_ = identità quasi perfetta
    

Oppure usa una **distanza**: più bassa = più simili.

Si considera “match” se lo score supera una **soglia** fissata.

---

### **5. Classi di algoritmi di matching (p.6)**

#### **Manuale**

- Combinazione di giudizio umano + esperienza.
    
- Usato ancora in forense come _ultimo livello_.
    

#### **Image-Based (correlation)**

- Confronto diretto delle immagini.
    
- Veloce, ma richiede allineamento perfetto.
    

#### **Texture-Based (filterbank)**

- Impronta vista come texture orientata.
    
- Usa Gabor → produce coefficienti locali.
    
- Meno accurata delle minutiae.
    

#### **Minutiae-Based**

- La più usata e la più accurata.
    
- Richiede:
    
    - estrazione minuzie
        
    - allineamento
        
    - compensazione deformazioni
        

Problematiche: se l’immagine è povera, le minuzie vengono estratte male → matching pessimo.

---

### **6. Image-Based Matching – Optical Correlation (p.7)**

Vediamo due curve:

- _genuine match_ → picco alto
    
- _impostor match_ → correlazione bassa

![](imgs/Pasted%20image%2020260218141904.png)

**Vantaggi:**

- velocissimo
    
- usa direttamente l’immagine
    
- non richiede estrazione minuzie
    

**Svantaggi:**

- template enorme (30 KB)
    
- necessita allineamento preciso
    
- non robusto a rotazioni/scalature/distorsioni
    

---

### **7. Texture-Based Matching – Filterbanks**

Funzionamento:

- l’immagine è divisa in N regioni locali
    
- per ciascuna regione si applicano M filtri (es. Gabor)
    
- il template diventa una matrice M×N di coefficienti

![](imgs/Pasted%20image%2020260218141949.png)

**Vantaggi:**

- sfrutta texture che le minuzie non catturano
    
- funziona meglio delle minuzie su immagini scadenti
    
- può essere combinato con minutiae-based
    
- calcolo semplice (norma euclidea)
    

**Svantaggi:**

- richiede allineamento preciso
    
- non robusto a rotazioni/traslazioni
    
- meno accurato delle minutiae
    

---

### **8. Minutiae-Based Matching**

Rappresentazione di una minutia:

- coordinate (x, y)
    
- angolo θ
    
- tipo t (biforcazione o terminazione)

![](imgs/Pasted%20image%2020260218142018.png)

Il matching è un **point-pattern matching** tra due insiemi di punti.

Prima servono:

- allineamento angolare
    
- allineamento in scala
    
- allineamento spaziale
    
- riduzione deformazioni elastiche
    

#### **Vantaggi:**

- invarianti a rotazione/traslazione
    
- molto accurate
    

#### **Svantaggi:**

- estrazione minuzie difficile in immagini povere
    
- non tengono conto della struttura globale dei ridge
    

---

### **9. Approcci al matching basato su minuzie**

#### **Core-based**

Usa il **core** per allineare le impronte.

- Veloce, utile in real-time
    
- Ma fallisce su immagini di bassa qualità dove il core non è visibile
    

#### **Local Structure**

Usa micro-aree e confronta sottoinsiemi piccoli.

**Pro:**

- invarianti a rotazione/traslazione
    
- robusti a piccole deformazioni
    

**Contro:**

- possibili molte “strutture simili” tra dita diverse
    
- poche strutture disponibili se l’immagine è piccola o sporca ⇒ instabilità
    

#### **Global Structure**

Confronta strutture grandi.

**Pro:** molto discriminante  
**Contro:** sensibile alle deformazioni non lineari, che aumentano lontano dal centro

#### **Hybrid Structure**

Combina locale + globale.

Pipeline:

1. trovare una buona coppia di minutiae locali
    
2. usarla come riferimento per allineare
    
3. confrontare strutture globali (distanze, angoli, ridge count)
    

![](imgs/Pasted%20image%2020260218142356.png)

---

### **10. Kovac Matching Algorithm – Metodo dei triangoli**

Descrizione:

- ogni fingerprint → set di triangoli formati da triplette di minuzie
    
- si confrontano **distanze**, **angoli**, **rapporti invarianti**
    
- robusto a rotazioni e traslazioni
    
- poco costoso → ideale per sistemi embedded


È definito “ibrido”:

- usa Level-2 (minuzie)
    
- ma confronta Level-1 (geometria globale)
    

---

### **11. Stato dell'Arte – SOA**

![](imgs/Pasted%20image%2020260218142600.png)

Ci sono due grandi fonti di dataset:

- **NIST** → impronte off-line
    
- **FVC2004** → impronte live-scan
    

#### Prestazioni moderne

Nel 2025, Dermalog raggiunge **errore ZERO** su FV-STD-1.0  
(v. tabella e grafici in p.14).

Attenzione:  
zero errori = **dataset troppo piccolo**, non sistema perfetto.

La pipeline del metodo di Dermalog (p.15):

**Preprocessing**

1. Segmentazione basata sul contrasto
    
2. Gabor enhancement
    
3. Binarizzazione
    

**Rappresentazioni intermedie**

- mappa orientamenti
    
- mappa frequenza
    

**Matching**

- strutture geometriche locali (triangoli, grafi)
    
- similarità testurale (Gabor/wavelet)
    
- allineamento (traslazioni+rotazioni, no scaling)
    

#### Risultati su dataset difficile (FV-HARD)

Molto migliorati rispetto al passato, ma **non perfetti**.

---

### **12. Competizioni storiche (p.17)**

#### **FVC2004**

Quattro database:

- DB1: Optical CrossMatch
    
- DB2: Optical DigitalPersona UareU 4000
    
- DB3: Thermal sweeping Atmel
    
- DB4: SFinGe (sintetiche)

![](imgs/Pasted%20image%2020260218142952.png)

Per ogni DB:

- set B fornito prima (tuning)
    
- set A segreto (test vero)
    

Metodo considerato best-practice: **test su set non visto**.

---

### **13. Esempio completo di matching locale+globale (p.18–20)**

Metodologia di Jiang & Yau (2000):

1. Trovare centro immagine = baricentro delle minuzie
    
2. Se troppe minuzie, selezionare solo N più vicine al centro
    
3. Calcolare distanza tra ogni minutia e tutte le altre → distance matrix
    
4. Per ciascuna minutia, costruire LFV (Local Feature Vector), 11 elementi
    
5. Confrontare i LFV → Similarity matrix
    
6. Se tutta la matrice è zero → _non-match_
    
7. Trovare la coppia con similarità massima → punto di allineamento
    
8. Generare GFV (Global Feature Vector) per tutte le altre
    
9. Confrontare GFV → Matching Level
    
10. Evitare duplicazioni di minuzie nel matching
    
11. Calcolare matching score
    
12. Confrontare con soglia → _match / non-match_
    

![](imgs/Pasted%20image%2020260218143046.png)

---

### **14. Messaggio chiave della lezione**

Il matching delle impronte **non è un problema risolto**, anche se:

- oggi abbiamo prestazioni incredibili su dataset standard
    
- approcci moderni combinano più livelli (texture + minuzie + geometrie)
    
- deformations modeling è ancora limite fondamentale
    
- la qualità reale dell’immagine sul campo resta l’ostacolo principale
    

Ogni sistema biometrico va progettato considerando:

- sensore
    
- popolazione
    
- qualità reale delle immagini
    
- FMR/FNMR accettabili per l’applicazione

---

Dispensa originale:

![](imgs/SCO_M3_U2_L3_D.pdf)

