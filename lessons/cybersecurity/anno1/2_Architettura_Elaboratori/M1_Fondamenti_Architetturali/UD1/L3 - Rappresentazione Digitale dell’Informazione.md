## ***Lezione 3: Rappresentazione Digitale dell’Informazione***

---

### **1. Il problema da risolvere: i limiti dell’analogico**

Dalla **Lezione 2** sappiamo che la rappresentazione analogica è:

- **fedele** (segue il fenomeno in modo continuo),
    
- **intuitiva** (si legge “a vista”),
    

ma anche:

- **fragile rispetto ai disturbi**,
    
- **instabile nel tempo** (invecchiamento dei componenti),
    
- **inadatta a elaborazioni complesse** (errore che si accumula).
    

L’idea chiave della rappresentazione digitale nasce proprio come **risposta a questi limiti**:

> invece di ammettere infiniti valori possibili, si accetta di usare **solo un numero finito di stati**.

Questo è il cuore della rappresentazione digitale:

> **ridurre i possibili valori della grandezza fisica a un insieme discreto e finito di stati, ciascuno interpretato come una cifra (digit).**

---

### **2. Da continuo a discreto: l’idea di quantizzazione**

Consideriamo di nuovo la temperatura di una stanza tra $0^\circ C$ e $50^\circ C$.

- **Analogico**:  
    ogni valore, anche $12.37^\circ C$, ha un proprio valore distinto di tensione.
    
- **Digitale**:  
    l’intervallo non viene più rappresentato in modo continuo, ma **spezzato in sottointervalli**.
    

Esempio:

- $[0^\circ C, 10^\circ C) \rightarrow 0V$
    
- $[10^\circ C, 20^\circ C) \rightarrow 1V$
    
- $[20^\circ C, 30^\circ C) \rightarrow 2V$
    
- …
    

![](imgs/Pasted%20image%2020251208083540.png)

In questo modo:

- non rappresentiamo più **ogni singolo valore**,
    
- ma **solo in quale intervallo** cade la temperatura.
    

È il concetto di **quantizzazione**:

> il mondo continuo viene approssimato con “scalini” discreti.

---

### **3. Tipo di corrispondenza: univoca ma non biunivoca**

In questa rappresentazione:

- **dal rappresentato al rappresentante**:  
    ogni temperatura cade in **un solo** intervallo → **corrispondenza univoca**.
    
- **dal rappresentante al rappresentato**:  
    lo stesso valore di tensione (es. $1V$) rappresenta **tutti** i valori tra $10^\circ C$ e $20^\circ C$ → **non biunivoca**

![](imgs/Pasted%20image%2020251208083742.png)

Quindi:

- una cifra (digit) rappresenta **un intero insieme di valori reali**,
    
- si perde la distinzione tra i valori all’interno dello stesso intervallo.
    

Questa perdita di dettaglio è il **prezzo da pagare** per ottenere robustezza.

---

### **4. Vantaggi della rappresentazione digitale**

#### **4.1 Robustezza ai disturbi**

Se a un intervallo assegnamo un unico valore di tensione (es. $1V$), piccoli disturbi che spostano il valore reale di:

- $1.00V \rightarrow 1.04V$
    
- $0.98V \rightarrow 1.01V$
    

non cambiano l’**interpretazione logica** del dato: siamo sempre nello stesso intervallo.

Il sistema digitale applica delle **soglie**:

- se la tensione è tra $0.8V$ e $1.2V$, la interpreto come **“1”**,
    
- se è tra $-0.2V$ e $0.2V$, la interpreto come **“0”**, e così via.
    

![](imgs/Pasted%20image%2020251208083810.png)

L’effetto è:

- **i piccoli disturbi vengono “schiacciati”** in uno stesso simbolo,
    
- l’informazione è **stabile**, anche se il segnale fisico è leggermente sporco.
    

Questa è la base dell’idea di **autocorrezione** a livello fisico:

> non serve conoscere il valore esatto della grandezza, basta sapere in **quale fascia** ricade.

---

#### **4.2 Compatibilità con elaborazioni complesse**

Nell’analogico:

- ogni operazione (somma, prodotto, filtraggio, ecc.) introduce un errore reale,
    
- dopo molte operazioni, l’errore cresce e diventa ingestibile.
    

Nel digitale:

- non lavoriamo con grandezze fisiche continue,
    
- ma con **simboli discreti** (cifre),
    
- su cui applichiamo **regole logiche e aritmetiche** esatte.
    

In altre parole:

> il calcolatore digitale non “somma tensioni”, ma somma **bit** secondo regole esatte (aritmetica binaria).

L’errore non è più legato alla fisica continua, ma al **numero di bit** usati per rappresentare il dato (precisione).  
Questo rende il sistema:

- prevedibile,
    
- controllabile,
    
- scalabile.
    

---

### **5. Svantaggi della rappresentazione digitale**

#### **5.1 Perdita di fedeltà (errore di quantizzazione)**

Se due valori diversi del fenomeno reale cadono nello stesso intervallo:

- $12.3^\circ C$ e $17.9^\circ C$  
    possono essere codificati entrambi come, ad esempio, **“1V” → cifra “1”**.
    

![](imgs/Pasted%20image%2020251208083827.png)

Il sistema **non distingue** tra questi due valori:

- l’informazione reale viene **semplificata**,
    
- si perde una parte delle “sfumature” del dato.
    

Questo è l’**errore di quantizzazione**:  
il continuo viene approssimato con pochi scalini discreti.

---

#### **5.2 Poca intuitività**

In un sistema digitale, il valore fisico (es. $2V$) non è direttamente leggibile come significato:

- vedere **2V** non basta,
    
- bisogna sapere che **2V** significa, per esempio, “intervallo $20^\circ C$–$30^\circ C$”,
    
- oppure che la cifra logica è **“2”**, o una combinazione di bit.
    

Serve quindi sempre un **processo di codifica/decodifica**:

- il significato non è “scritto addosso” al segnale fisico,
    
- è definito dalla **convenzione logica** e dal **protocollo di codifica**.
    

---

### **6. Il caso speciale (e fondamentale): la rappresentazione binaria**

Tra tutte le rappresentazioni digitali, quella che ha vinto su tutte è la **binaria**.

Qui il rappresentante può assumere **solo due stati** ben distinti:

- livello basso (es. $0V$) → **0**
    
- livello alto (es. $5V$ o $3.3V$) → **1**
    

![](imgs/Pasted%20image%2020251208083848.png)

Questi due stati rappresentano le **due cifre del sistema binario**: $0$ e $1$.

#### **6.1 Vantaggi del binario**

- **Massima robustezza**  
    Serve un disturbo molto marcato o un grave guasto per far scambiare uno 0 con un 1.
    
- **Implementazione semplice**  
    Circuiti elettronici che riconoscono solo “basso” o “alto” sono relativamente facili da realizzare, piccoli ed efficienti.
    
- **Consumo ridotto**  
    Due livelli ben separati permettono di usare tensioni e correnti minime, con consumi stabili e prevedibili.
    
- **Scalabilità**  
    È facile replicare milioni di elementi binari (transistori) dentro un circuito integrato.
    

Per queste ragioni **tutta l’informatica moderna è binaria**:  
i computer non capiscono numeri in base 10, ma solo sequenze di 0 e 1.

---

#### **6.2 Apparente svantaggio del binario**

Con solo due valori possibili (0 e 1), **da soli** non possiamo rappresentare molte informazioni.

La soluzione è:

> **usare sequenze di bit.**

Una sequenza di $n$ bit può rappresentare $2^n$ valori diversi.

Esempi:

- con $3$ bit → $2^3 = 8$ valori (da 0 a 7),
    
- con $8$ bit (1 byte) → $2^8 = 256$ valori,
    
- con $16$ bit → $2^{16} = 65536$ valori.
    

Più bit usiamo:

- più valori diversi possiamo rappresentare,
    
- maggiore è la precisione con cui approssimiamo il fenomeno reale,
    
- ma maggiori sono **memoria**, **tempo di elaborazione** e **larghezza dei bus** necessari.
    

---

### **7. Bit: la cellula elementare dell’informazione digitale**

La parola **bit** viene da **binary digit**, cioè “cifra binaria”.

> Un bit è la **più piccola unità di informazione** in un sistema digitale.  
> Può valere solo **0** oppure **1**.

Nel mondo elettronico:

- $0$ → tipicamente “assenza di segnale”, tensione bassa (es. circa $0V$),
    
- $1$ → “presenza di segnale”, tensione alta (es. $5V$ o $3.3V$).
    

Qualunque informazione complessa (numeri, testi, immagini, audio, video) viene:

- **scomposta**,
    
- **ricodificata**,
    
- in una **sequenza di bit**.
    

Questo è il principio di base della **codifica binaria**.

---

### **8. Byte: il blocco base di memoria**

Un **byte** è un gruppo ordinato di **8 bit**.

Per convenzione architetturale:

- il byte è l’unità minima di indirizzamento della memoria,
    
- la maggior parte delle architetture è progettata “a multipli di byte”.
    

Con un byte si possono rappresentare:

- **256 valori diversi** ($2^8$),
    
- numeri interi da $0$ a $255$ (o da $-128$ a $+127$ con codifiche firmate),
    
- un **carattere** (lettere, cifre, simboli),
    
- un valore per un canale di colore (es. in RGB: $0$–$255$ per il rosso, verde o blu).
    

---

### **9. Multipli del byte e unità di misura dell’informazione**

Come per metri e grammi, anche per la memoria si usano multipli:

- **kilobyte (KB)** ≈ $10^3$ byte,
    
- **megabyte (MB)** ≈ $10^6$ byte,
    
- **gigabyte (GB)** ≈ $10^9$ byte,
    
- **terabyte (TB)** ≈ $10^{12}$ byte.
    

In ambito strettamente tecnico si usano anche le unità **binariamente esatte**:

- **kibibyte (KiB)** = $1024$ byte = $2^{10}$ byte,
    
- **mebibyte (MiB)** = $1024$ KiB = $2^{20}$ byte,
    
- **gibibyte (GiB)** = $1024$ MiB = $2^{30}$ byte,
    
- …
    

Nella pratica quotidiana:

- si continua spesso a usare **KB, MB, GB** in senso “binario”,
    
- anche se formalmente sarebbe più corretto usare **KiB, MiB, GiB** per i multipli esatti di potenze di 2.
    

---

### **10. Altre unità correlate (meno usate ma concettualmente utili)**

- **Nibble**:  
    gruppo di **4 bit**, cioè “mezzo byte”.  
    Utile per rappresentare una cifra esadecimale (0–15).
    
- **Trit**:  
    cifra ternaria, può assumere **3 stati** (es. $-1, 0, +1$).  
    È studiato nella teoria dell’informazione, ma **non è usato** nei computer binari standard.
    

Questi concetti servono per capire che:

> il digitale non deve essere per forza binario,  
> ma **il binario è la scelta industrialmente vincente**.

---

### **11. Sintesi concettuale: perché il digitale (e perché binario)**

**Rappresentazione digitale**

- **Vantaggi**
    
    - robusta ai disturbi,
        
    - adatta a elaborazioni complesse,
        
    - consente autocorrezione e ridondanza,
        
    - facilmente memorizzabile e trasmissibile.
        
- **Svantaggi**
    
    - perde le sfumature (errore di quantizzazione),
        
    - non è direttamente intuitiva (serve una codifica).
        

**Rappresentazione binaria**

- **Vantaggi**
    
    - massima robustezza,
        
    - semplicità circuitale,
        
    - efficienza energetica,
        
    - fondamento di tutta l’informatica moderna.
        
- **Svantaggio**
    
    - da sola, con 1 bit, rappresenta pochissime informazioni →  
        serve usare sequenze di bit e **schemi di codifica**.
        

Questo **compromesso** è accettabile perché:

> la piccola perdita di precisione dovuta alla quantizzazione è enormemente compensata da **stabilità, affidabilità e scalabilità** del calcolo digitale.

---

### **12. Collegamento con il resto del corso**

Da questo punto in poi:

- **registri**,
    
- **bus dati**,
    
- **memorie**,
    
- **ALU**,
    
- **cache**,
    
- **istruzioni macchina**,
    

parleranno **solo in termini di bit e byte**.

I prossimi passi naturali saranno:

- come rappresentare **numeri interi e reali in binario**,
    
- come codificare **caratteri**, **immagini**, **suoni**,
    
- come l’architettura di un elaboratore si organizza per **manipolare queste sequenze di bit**.
    

Questa lezione chiude il trittico:

1. **Perché devo rappresentare l’informazione (Lezione 1)**
    
2. **Perché l’analogico non basta (Lezione 2)**
    
3. **Perché il digitale (e il binario) è la base dell’informatica (Lezione 3)**
    

Da qui inizia veramente **Architettura degli Elaboratori**.