## **Lezione 3: Codifiche binarie di informazioni non numeriche**

---

### **1. Dal numero a “tutto il resto”: idea unificante**

Finora hai visto come rappresentare **numeri**:

- interi positivi in base 2
    
- numeri con segno (modulo/segno, complemento a 2)
    
- numeri frazionari, virgola fissa e virgola mobile
    

Ora la domanda è: **come rappresentiamo tutto il resto?**

- testo (caratteri, simboli, emoji)
    
- immagini
    
- suoni
    
- filmati
    

L’idea chiave è sempre la stessa:

> **Qualsiasi informazione può essere rappresentata come una sequenza di bit, se definiamo una codifica coerente e sufficientemente ricca.**

Tutto il resto della lezione consiste nel vedere **come** si costruiscono queste codifiche.

---

## **2. Codifica di caratteri – Da ASCII a Unicode**

### **2.1 Perché serve una codifica per i caratteri**

Un computer non “capisce” le lettere **A, z, @**:  
capisce solo **numeri interi** e quindi **bit**.

Serve quindi una **tabella di corrispondenza**:

- da **carattere astratto** → a **codice numerico**
    
- poi il codice numerico → a **sequenza di bit**
    

Questa tabella è uno **standard**, in modo che:

- il file creato su un computer A
    
- possa essere letto correttamente sul computer B.
    

---

### **2.2 ASCII a 7 bit**

Lo standard storico si chiama **ASCII**  
(_American Standard Code for Information Interchange_).

Caratteristiche principali:

- usa **7 bit per carattere** → $2^7 = 128$ codici possibili (da 0 a 127);
    
- definito negli anni ’60;
    
- spesso indicato come **US-ASCII**.
    

La tabella contiene:

- **0–31**: caratteri di controllo (NUL, ESC, LF, CR, TAB, …)
    
- **32–47**: spazi e punteggiatura base
    
- **48–57**: cifre ‘0’–‘9’
    
- **65–90**: lettere maiuscole ‘A’–‘Z’
    
- **97–122**: lettere minuscole ‘a’–‘z’
    
- **altri codici**: simboli vari (@, #, +, -, …)
    

#### Esempio: la lettera `A`

- codice decimale: $65_{10}$
    
- in binario a 7 bit: $1000001_2$
    

La tabella ASCII viene spesso organizzata come **matrice**:

- i bit meno significativi (3–4 bit) → riga
    
- i bit più significativi (3–4 bit) → colonna
    

In questo modo puoi “leggere” la posizione del carattere direttamente dai bit.

---

### **2.3 Perché 8 bit? ASCII esteso e compatibilità hardware**

L’hardware moderno lavora su **byte di 8 bit**, non su gruppi da 7 bit.

Quindi, in memoria:

- ogni carattere ASCII occupa **1 byte = 8 bit**
    
- l’ottavo bit, per i codici 0–127, è semplicemente **0**
    

Esempio:

- `A` (65) in byte: `01000001` (bit più significativo aggiunto a 0)
    

Questo ha aperto la porta a usare i codici **128–255** per:

- lettere accentate (à, è, ñ, ç, …)
    
- simboli grafici
    
- caratteri specifici per lingua
    

Nascono così le tante codifiche **“ASCII estese”** (ISO-8859-x, Windows-1252, ecc.).  
Importante: **non sono standard universali**, cambiano da paese a paese.

---

### **2.4 Oltre ASCII: Unicode (cenno)**

ASCII non basta più per:

- lingue con alfabeti diversi (cirillico, greco, arabo, cinese, …)
    
- emoji, simboli matematici avanzati, caratteri speciali
    

Per questo si usa oggi **Unicode**, che:

- definisce un **codice univoco** per ogni carattere
    
- supporta **decine di migliaia di simboli**
    

Le codifiche più usate (UTF-8, UTF-16…) sono modi diversi di rappresentare quei codici in **sequenze di byte**.

---

## **3. Immagini digitali e pixel**

### **3.1 Origine storica del termine “pixel”**

“Pixel” = **picture element** (elemento dell’immagine).

Il termine nasce negli anni ’60, in particolare:

- usato da ingegneri che lavoravano su immagini digitali delle sonde spaziali,
    
- come abbreviazione pratica per indicare il **singolo punto elementare** di un’immagine.
    

Da allora, “pixel” è diventato il mattone fondamentale di tutta la grafica digitale.

---

### **3.2 Definizione formale di pixel**

Un **pixel** è:

> il più piccolo elemento discreto di un’immagine digitale, identificato da una posizione $(x,y)$ nella griglia e da un **valore numerico** (colore o intensità).

L’immagine è una **matrice bidimensionale**:

- dimensioni: $L \times H$ (larghezza × altezza)
    
- ogni cella = 1 pixel
    

Esempio:

- immagine **1280×720**
    
- numero di pixel totali: $1280 \cdot 720 = 921,600$
    

---

### **3.3 Risoluzione spaziale e cromatica**

- **Risoluzione spaziale**: numero di pixel dell’immagine.  
    Più pixel → più dettagli → immagine più definita.
    
- **Risoluzione cromatica**: quanti **bit per pixel**.  
    Alcuni esempi:
    
    - 1 bit/pixel → bianco/nero
        
    - 8 bit/pixel → 256 livelli di grigio
        
    - 24 bit/pixel → 3 canali (R,G,B), 8 bit ciascuno → $256^3$ colori ≈ 16,7 milioni
        

Ogni pixel è quindi un **numero (o un gruppo di numeri)**, e ognuno di questi numeri è rappresentato in **binario**.

---

## **4. Audio digitale: campionamento e quantizzazione**

### **4.1 Che cos’è un segnale audio**

Un segnale audio analogico è una funzione continua nel tempo:

$$  
s(t)  
$$

dove:

- $t$ è il tempo,
    
- $s(t)$ può rappresentare pressione dell’aria, tensione elettrica, ecc.
    

Graficamente, è una **forma d’onda**:  
asse $x$ = tempo, asse $y$ = ampiezza del segnale.

---

### **4.2 Dal continuo al discreto: il campionamento**

Un computer non può memorizzare **infinito** numero di valori continui.  
Serve trasformare il segnale in una sequenza discreta di numeri:

1. scegliamo intervalli regolari di tempo $\Delta t$
    
2. misuriamo il valore del segnale in quegli istanti
    
3. ogni misura è un **campione** (sample)
    

La **frequenza di campionamento** $f_s$ indica:

> quante misure al secondo vengono effettuate  
> (si misura in Hertz, Hz).

Esempio:

- **CD audio**: $f_s = 44,100\ \text{Hz}$ → 44.100 campioni al secondo.
    

---

### **4.3 Teorema di Nyquist–Shannon (idea)**

Domanda: **quanto spesso** bisogna campionare un segnale per non perdere informazione?

> Se la massima frequenza presente nel segnale è $f_\text{max}$,  
> allora la frequenza di campionamento deve essere **almeno**:  
> $$  
 f_s \ge 2 f_\text{max}  
 $$  
> per evitare aliasing.

L’aliasing è l’errore per cui frequenze distinte diventano **indistinguibili** dopo il campionamento.

Per l’audio:

- l’orecchio umano percepisce circa **20 Hz – 20 kHz**
    
- per registrare tutto lo spettro serve almeno: $f_s \ge 40\ \text{kHz}$
    
- per questo il CD audio usa 44.1 kHz.
    

---

### **4.4 Quantizzazione: da ampiezze reali a numeri finiti**

Ogni campione ha un certo valore reale $s(t_k)$, da trasformare in un **numero intero**.

Si sceglie una **profondità in bit** per campione:

- 8 bit → $2^8 = 256$ livelli possibili
    
- 16 bit → $2^{16} = 65,536$ livelli possibili
    

Più bit → maggiore **precisione** (minore errore di quantizzazione).

Un secondo di audio stereo CD:

- $f_s = 44,100$ campioni/s
    
- 16 bit = 2 byte per canale
    
- 2 canali (stereo)
    

Numero di byte al secondo:

$$  
44,100 \cdot 2 \cdot 2 \approx 176,400\ \text{byte} \approx 172\ \text{KB/s}  
$$

---

## **5. Video digitale: immagini + audio + compressione**

### **5.1 Che cos’è un video digitale**

Un video è:

> una sequenza di **fotogrammi** (immagini) mostrati rapidamente,  
> più un **audio** sincronizzato.

Parametri principali:

- **frame rate**: numero di fotogrammi al secondo (es. 24, 30, 60 fps)
    
- **risoluzione** di ogni fotogramma (es. 1280×720, 1920×1080)
    
- **profondità di colore** (es. 24 bit per pixel)
    

---

### **5.2 Quanto pesa un video non compresso? (esempio)**

Prendiamo:

- risoluzione: $720 \times 576$
    
- 24 bit per pixel (3 byte: R,G,B)
    
- 30 frame/s
    
- durata: 1 minuto
    

**Peso di 1 frame:**

- pixel/frame: $720 \cdot 576 = 414,720$
    
- byte/frame: $414,720 \cdot 3 \approx 1,244,160\ \text{byte} \approx 1{,}19\ \text{MB}$
    

**Frame in 1 minuto:**

- $30 \cdot 60 = 1,800$ frame
    

**Peso totale:**

- $1{,}19\ \text{MB} \cdot 1,800 \approx 2{,}14\ \text{GB}$
    

Quindi:

> 1 minuto di video **non compresso** può superare tranquillamente i **2 GB**.

Ovviamente è insostenibile per archiviazione e trasmissione → serve la **compressione**.

---

### **5.3 Compressione video: idea generale**

Scopo:

> ridurre drasticamente la quantità di dati  
> mantenendo qualità percettiva accettabile.

Tipi principali:

1. **Intra-frame**  
    comprime **ogni fotogramma singolarmente**, come se fosse una foto (tipo JPEG).
    
2. **Inter-frame**  
    sfrutta il fatto che tra frame consecutivi la scena cambia poco:  
    memorizza **solo le differenze** tra fotogrammi.
    

Altre idee:

- l’occhio è più sensibile alla **luminosità** che al colore → si comprimono di più le componenti cromatiche;
    
- nelle zone con molto movimento tolleriamo più perdita di dettaglio.
    

Da qui derivano gli standard:

- MPEG-2, H.264, H.265, AV1, …
    

Risultato tipico:

- da **2 GB/minuto non compresso**
    
- a **qualche decina di MB/minuto** compresso.
    

---

## **6. Unità di misura dell’informazione**

Alla base di tutto ci sono le unità che userai continuamente in Architettura, Sistemi Operativi, Reti.

### **6.1 bit**

- unità base di informazione: **binary digit**
    
- può valere **0** o **1**
    
- rappresenta l’esito di una decisione sì/no, vero/falso
    

Tutto nasce da qui.

---

### **6.2 byte**

- gruppo ordinato di **8 bit**
    
- è l’unità standard di misura della memoria
    
- con 8 bit puoi rappresentare $2^8 = 256$ valori distinti
    

Esempi:

- un carattere ASCII occupa 1 byte
    
- un campione audio a 8 bit = 1 byte
    
- un pixel in scala di grigi a 8 bit = 1 byte
    

---

### **6.3 word (parola)**

Una **word** è la dimensione “naturale” di elaborazione di una certa architettura.

Storicamente:

- 1 word = 16 bit = 2 byte
    

Nelle architetture moderne:

- su CPU a 32 bit → spesso 1 word = 32 bit
    
- su CPU a 64 bit → spesso 1 word = 64 bit
    

Quindi “word” è **architettura-dipendente**.

---

### **6.4 double word, quad word**

- **double word (dword)**
    
    - 2 word
        
    - tipicamente 32 bit (su architetture dove word=16)
        
    - oppure 64 bit (dove word=32)
        
- **quad word (qword)**
    
    - 4 word
        
    - spesso 64 o 128 bit, a seconda del contesto
        

Usate per:

- interi grandi
    
- indirizzi di memoria
    
- registri per operazioni in virgola mobile o SIMD (vector, AVX, SSE)
    

---

### **6.5 Riassunto delle unità (tipico scenario moderno)**

In una classica architettura a 64 bit:

- 1 bit → 0 o 1
    
- 8 bit → 1 byte
    
- 16 bit → halfword
    
- 32 bit → word
    
- 64 bit → double word / quad word (dipende dalla convenzione)
    

Queste dimensioni sono fondamentali per:

- la struttura dei **registri**
    
- la larghezza dei **bus**
    
- il formato di **indirizzi** e **dati**
    

---

## **7. Schema concettuale finale**

Puoi vedere tutta la lezione così:

- **Testo**  
    carattere → codice numerico (ASCII/Unicode) → bit
    
- **Immagini**  
    immagine → matrice di pixel  
    pixel → numeri (colore o intensità) → bit
    
- **Audio**  
    segnale continuo → campionamento (tempo) + quantizzazione (ampiezza)  
    sample → numero → bit
    
- **Video**  
    sequenza di fotogrammi + audio  
    ogni fotogramma → matrice di pixel → numeri → bit  
    compressione → meno bit a parità di percezione
    
- **Unità di misura**  
    bit, byte, word, dword, qword → mattoni per ragionare su memoria, registri, file, rete.
    

---

## **8. Frase-chiave da ricordare**

> **Tutto può essere bit, a patto di usare il numero giusto di bit e la codifica giusta.**

Questa frase è il ponte diretto tra **teoria dell’informazione**, **architettura degli elaboratori** e tutti i corsi successivi (Sistemi Operativi, Reti, Sicurezza, Multimedia…).