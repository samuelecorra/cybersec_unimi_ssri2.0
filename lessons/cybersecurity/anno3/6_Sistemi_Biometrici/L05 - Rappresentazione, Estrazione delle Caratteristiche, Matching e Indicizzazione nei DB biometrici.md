# **Lezione 5: Rappresentazione, Estrazione delle Caratteristiche, Matching e Indicizzazione nei DB biometrici**

---

### **0. Introduzione**

Questa lezione è uno **snodo chiave** del corso: entriamo nel cuore matematico-ingegneristico del sistema biometrico, cioè **come trasformiamo un sample grezzo in un template machine-readable**, come lo confrontiamo (matching) e come organizziamo un **database scalabile** per l’identificazione 1:N.

È la lezione più importante a livello concettuale: la qualità di qualunque sistema biometrico **dipende totalmente** da quanto bene risolvi i problemi di:

- rappresentazione del sample,
    
- estrazione corretta delle feature,
    
- definizione del template,
    
- definizione della metrica di matching,
    
- indicizzazione intelligente del DB.
    

---

## **Parte A — Template, Feature Extraction e Matching**

---

### **1. La rappresentazione: il problema centrale**

#### **1.1 Cosa arriva dal sensore?**

Un **sample grezzo** non elaborato:

- **non è invariante** (cioè cambia da acquisizione ad acquisizione dello stesso individuo),
    
- **non è discriminatorio** (tutti i sample sono diversi, anche per la stessa persona).

![](imgs/Pasted%20image%2020260214191146.png)

Esempio: due impronte digitali dello stesso dito prese a distanza di 10 secondi **non coincidono mai pixel-per-pixel**.

#### **1.2 Cosa invece ci serve davvero?**

Esattamente **il contrario**:

- _bassa variabilità intraclasse_: io = io, anche se cambio posizione, luce, pressione, ecc.;
    
- _alta variabilità interclasse_: io ≠ chiunque altro.
    

#### **1.3 La domanda fondamentale**

> _Quale rappresentazione “machine-readable” cattura completamente l’informazione invariante e discriminatoria del tratto biometrico in ingresso?_

Questa frase è **il cuore assoluto** della biometria ingegneristica.

---

### **2. Lo spazio delle feature**

(pp. 5–7)

La rappresentazione cerca di creare uno **spazio N-dimensionale delle feature**, in cui:

- i campioni dello **stesso individuo** formano cluster compatti → **bassa variabilità intraclasse**;
    
- i campioni di **individui diversi** sono lontani tra loro → **alta variabilità interclasse**.
    
![](imgs/Pasted%20image%2020260214191504.png)

Le immagini mostrano punti (individui i, j, k) immersi nello spazio delle feature:  
l’obiettivo è plasmare lo spazio in modo che i cluster non si sovrappongano.

![](imgs/Pasted%20image%2020260214191540.png)

---

### **3. Le tre componenti della rappresentazione**

La rappresentazione si divide in:

1. **Rappresentazione del sample**
    
2. **Estrazione delle feature**
    
3. **Rappresentazione del template**

![](imgs/Pasted%20image%2020260214191721.png)

Ogni tratto biometrico ha vincoli e possibilità diverse.

#### **3.1 Rappresentazione del sample**

L’affidabilità della rappresentazione dipende dal tratto biometrico e dominio applicativo del sistema:

- firma online → frequenza di campionamento, assi (x, y, pressione), bit di quantizzazione;
    
- impronta → tipo di sensore, risoluzione, profondità della scala di grigi;
    
- volto → risoluzione, colore, compressione.

![](imgs/Pasted%20image%2020260214192149.png)

Questi sono i **raw data**, la materia prima.

Effetto del dominio applicativo: la rappresentazione diventa meno affidabile 

- all’aumentare degli individui da confrontare fra loro nello spazio delle caratteristiche (e quindi nel DB);
-  in caso di sensori rumorosi, soggetti non allenati o collaborativi, ecc.

![](imgs/Pasted%20image%2020260214192521.png)

#### **3.2 Estrazione delle caratteristiche**

(pp. 12–14)

Dai raw data dobbiamo estrarre un **set di feature robuste**.

![](imgs/Pasted%20image%2020260215134141.png)

È il passaggio più difficile perché:

- i dati sono rumorosi,
    
- l’ambiente cambia,
    
- le condizioni d’acquisizione cambiano.

![](imgs/Pasted%20image%2020260215134203.png)

Ci sono essenzialmente due approcci possibili:

- **manuale**: operatore seleziona manualmente minuzie (raro, perizie forensi);
    
- **automatico**: tipico dei sistemi reali → pipeline software modulare (anche distribuita).
    

#### **3.3 Rappresentazione del template**

(pp. 15)

Esempi (slide con immagine):

- **Template mano**: lunghezze e larghezze → template a lunghezza fissa;
    
- **Template volto**: eigen-coefficients (PCA) → vettore fisso;
    
- **Template impronte**: lista di minuzie (posizione + orientazione) → **lunghezza variabile**.

![](imgs/Pasted%20image%2020260215134345.png)

Il template può avere:

- **lunghezza fissa** (pratico per matching veloce);
    
- **lunghezza variabile** (più ricco ma più lento da confrontare).
    

---

### **4. Il Matching**

(pp. 17–20)

#### **4.1 Cos’è realmente il matching?**

Una **metrica** nello spazio delle feature:

![](imgs/Pasted%20image%2020260215134524.png)

$$
d = matching(x, y)  
$$

dove:

- x = template in ingresso,
    
- y = template salvato nel DB.
    

#### **4.2 Esempio completo: impronte digitali**

Le fasi sono:

![](imgs/Pasted%20image%2020260215134604.png)

1. **rototraslazione** per allineare le due mappe di minuzie;
    
2. **ricerca di coppie di minuzie corrispondenti**;
    
3. **calcolo della distanza o indice di matching**.
    

### **4.3 Problemi reali del matching**

(pp. 20)

Le impronte **si deformano** a causa di:

- torsione,
    
- pressioni differenti,
    
- inclinazione del dito.

![](imgs/Pasted%20image%2020260215134650.png)

I sistemi moderni includono:

- **modelli elastici** (deformation models) → migliorano l’intraclasse,
    
- ma **peggiorano l’interclasse** → aumentano i falsi match,
    
- e aumentano la complessità computazionale.
    

---

### **5. Sintesi della Parte A**

(pp. 21)

- scopo della rappresentazione → creare feature **invarianti e discriminanti**;
    
- tre livelli: sample → feature → template;
    
- il matching implementa la **metrica nello spazio delle feature**;
    
- la qualità di questa parte determina **tutte le prestazioni del sistema**.
    

---

## **Parte B — Ricerca, Organizzazione e Scalabilità del DB biome­trico**

---

### **6. Scalabilità dei sistemi biometrici**

(pp. 24–25)

Quando un DB cresce (milioni di utenti):

- la ricerca deve rimanere **efficiente**,
    
- il degrado delle prestazioni deve essere **più lento** della crescita del numero di utenti.
    

Esempi USA (pagina 24–25):

- registro elettorale,
    
- patenti,
    
- frontiere,
    
- sistemi bancari.
    

Il vantaggio è che i sistemi biometrici _non_ richiedono rinnovo frequente come le smart-card.

---

### **7. Indicizzazione (Indexing) e Penetration Rate**

(pp. 26–28)

#### **7.1 Perché indicizzare un DB biometrico?**

Senza indicizzazione:

- un input deve essere confrontato con **tutti i template nel DB** (ricerca lineare).  
    Costo enorme.
    

Con indicizzazione:

- il DB è diviso in **partizioni (bin)**,
    
- si confronta l’input solo con i template nel bin corretto.

![](imgs/Pasted%20image%2020260215134840.png)

#### **7.2 Penetration Rate**

(Slide p. 27)

Definizione formale:

> "Proporzione attesa dei template da cercare per ogni input, assumendo che la ricerca percorra l’intera partizione anche se trova un match."

Praticamente:  
**quanto DB dobbiamo scandire, in media, per ogni identificazione?**

Esempio (p. 28):

- classifico le impronte come _arch_ / _loop_;
    
- se uso due dita → creo combinazioni (dx, sx);
    
- i sistemi moderni ottengono PR ≈ **10%** (ottimo).
    

#### **7.3 Condizione critica**

Serve un classificatore automatico **molto robusto** per assegnare l’impronta al bin corretto.

---

### **8. Esempio completo: il Binning per le impronte**

![](imgs/Pasted%20image%2020260215135051.png)

#### **8.1 Meccanismo**

Vediamo l’esempio:

- durante enrollment → classifico ogni impronta nel suo tipo (arch / loop / whorl, ecc.);
    
- creo **N bin** = tutte le combinazioni possibili dei tipi delle impronte utilizzate.
    

![](imgs/Pasted%20image%2020260215135113.png)

- 2 impronte, 2 tipi → 4 bin:
    
    - (arch, arch)
        
    - (arch, loop)
        
    - (loop, arch)
        
    - (loop, loop)
        

#### **8.2 Formula per il numero di bin ottimale**

(p. 32)

Se ho:

- $N_\text{impronte}$ (indice, medio, ecc.),
    
- $N_\text{tipi}$ (categorie come arch, whorl, loop…),
    

allora:

$$
N_{\text{bin}} = (N_\text{tipi})^{N_\text{impronte}}  
$$

Esempio:

- 2 impronte, 3 tipi → (3^2 = 9) bin.

---

### **9. Binning Error**

(pp. 33–35)

#### **9.1 Problema**

Se il classificatore sbaglia il bin → l’identificazione fallisce, perché cerco nel bin sbagliato.

#### **9.2 Quanto vale il Binning Error?**

Se:

- $p$ = probabilità di sbagliare la classificazione di **una impronta**,
    
- uso **N impronte**,
    

allora (p. 35):

$$
\text{Binning error} \approx N p  
$$

Esempio:

- se $p = 0.05$ (5%) e uso 2 impronte → $2p = 10\%$.
    

#### **9.3 Risultati reali**

(pp. 34)

- classificazione automatica con schema Henry (5 classi) → errori 5–10%;
    
- NIST SD4 (4000 impronte) → errori 3–8%.

![](imgs/Pasted%20image%2020260215135536.png)

---

## **10. Relazione tra numero di classi e Penetration Rate**

![](imgs/Pasted%20image%2020260215135600.png)

- **se riduci il numero di classi**:
    
    1. **Penetration Rate sale** → i bin sono meno discriminanti, peggiora la scalabilità;
        
    2. **Errore di classificazione scende** → il classificatore sbaglia meno.
        

Quindi:

- poche classi → classificazione più robusta, ma PR meno utile;
    
- molte classi → indexing migliore ma più errori di classificazione.

![](imgs/Pasted%20image%2020260215135714.png)

Il progettista deve trovare un **trade-off ottimale**.

---

## **11. Sintesi della Parte B**

(pp. 38)

Hai visto:

- scalabilità dei DB biometrici,
    
- indexing e binning,
    
- definizione del Penetration Rate,
    
- trade-off tra numero di classi e errore di classificazione,
    
- formula per il numero ottimale di bin,
    
- impatto dei classificatori.
    

---

# **Conclusione della Lezione 5**

Lezione densissima e fondamentale: mette insieme la parte **numerica e strutturale** del sistema biometrico.

In sintesi:

1. **La rappresentazione è tutto**: il vero problema è ottenere feature robuste e template significativi.
    
2. **Il matching è geometria + metrica** nello spazio delle feature.
    
3. **L’indicizzazione è obbligatoria** per sistemi 1:N di grandi dimensioni.
    
4. **Il Binning è potente ma rischioso**: serve un classificatore estremamente accurato.
    
5. **La scalabilità è possibile** solo se representation, matching e indexing lavorano insieme in armonia.