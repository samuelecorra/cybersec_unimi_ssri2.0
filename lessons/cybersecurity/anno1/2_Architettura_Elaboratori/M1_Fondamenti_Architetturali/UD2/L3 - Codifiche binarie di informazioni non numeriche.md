## ***Lezione 3: Codifiche binarie di informazioni non numeriche***

---

> 📌 Questa lezione rielabora integralmente le pagine 43–54 di `M1doc.pdf`.

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

### **2. Codifica di caratteri: da ASCII a Unicode**

#### **2.1. Perché serve una codifica per i caratteri**

Un computer non “capisce” le lettere **A, z, @**:  
capisce solo **numeri interi** e quindi **bit**.

Serve quindi una **tabella di corrispondenza**:

- da **carattere astratto** → a **codice numerico**
    
- poi il codice numerico → a **sequenza di bit**
    

Questa tabella è uno **standard**, in modo che:

- il file creato su un computer A
    
- possa essere letto correttamente sul computer B.
    

---

#### **2.2. ASCII a 7 bit**

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
    
- **58–64**: altra punteggiatura e simboli
- **65–90**: lettere maiuscole `A`–`Z`
- **91–96**: simboli e punteggiatura
- **97–122**: lettere minuscole `a`–`z`
- **123–126**: simboli e punteggiatura
- **127**: carattere di controllo `DEL`.
    

##### **2.2.1. Esempio: la lettera `A`**

- codice decimale: $65_{10}$
    
- in binario a 7 bit: $1000001_2$
    

La tabella del PDF è organizzata come una matrice $16\times8$: i **tre bit più significativi** selezionano una delle otto colonne e i **quattro bit meno significativi** una delle sedici righe. Per `A`, $100\,0001_2$, la colonna è $100_2=4$ e la riga $0001_2=1$.

In questo modo si ricostruisce il codice concatenando i bit della colonna e quelli della riga.

---

#### **2.3. Perché 8 bit? Famiglie a 8 bit e compatibilità hardware**

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
    

Nascono così molte codifiche colloquialmente dette **“ASCII estese”**. Non costituiscono un’unica estensione universale: ISO/IEC 8859 è una famiglia standardizzata di repertori a 8 bit, mentre Windows-1252 è una code page distinta. Lo stesso byte tra 128 e 255 può quindi significare caratteri diversi a seconda della codifica.

---

#### **2.4. Oltre ASCII: Unicode**

ASCII non basta più per:

- lingue con alfabeti diversi (cirillico, greco, arabo, cinese, …)
    
- emoji, simboli matematici avanzati, caratteri speciali
    

Per questo si usa oggi **Unicode**, che:

- definisce un **codice univoco** per ogni carattere
    
- supporta **decine di migliaia di simboli**
    

Le codifiche più usate (UTF-8, UTF-16…) sono modi diversi di rappresentare quei codici in **sequenze di byte**.

Unicode separa dunque il **code point** astratto dalla sua codifica. UTF-8 usa da uno a quattro byte e conserva la compatibilità con ASCII: i code point da U+0000 a U+007F hanno esattamente lo stesso singolo byte di ASCII. Non va descritto come una semplice tabella a 8 bit.

| codifica | lunghezza | capacità o repertorio |
| --- | --- | --- |
| ASCII | fissa, 7 bit | 128 codici |
| codifiche a 8 bit | fissa, 1 byte | 256 codici per ciascuna tabella |
| UTF-8 | variabile, 1–4 byte | code point Unicode, oltre un milione di posizioni possibili |

---

### **3. Immagini digitali e pixel**

#### **3.1. Origine storica del termine “pixel”**

“Pixel” = **picture element** (elemento dell’immagine).

Il termine nasce negli anni ’60, in particolare:

- usato da ingegneri che lavoravano su immagini digitali delle sonde spaziali,
    
- come abbreviazione pratica per indicare il **singolo punto elementare** di un’immagine.
    

Da allora, “pixel” è diventato il mattone fondamentale di tutta la grafica digitale.

---

#### **3.2. Definizione di pixel**

Un **pixel** (*picture element*) è:

> il più piccolo elemento discreto di un’immagine digitale, identificato da una posizione $(x,y)$ nella griglia e da un **valore numerico** (colore o intensità).

È l’elemento di campionamento dell’immagine, non necessariamente un quadratino fisico indivisibile: forma e disposizione dei componenti del display dipendono dal dispositivo, mentre il pixel logico appartiene alla matrice dei dati.

L’immagine è una **matrice bidimensionale**:

- dimensioni: $L \times H$ (larghezza × altezza)
    
- ogni cella = 1 pixel
    

Esempio:

- immagine **1280×720**
    
- numero di pixel totali: $1280 \cdot 720 = 921,600$
    

---

#### **3.3. Risoluzione spaziale e cromatica**

- **Risoluzione spaziale**: numero di pixel dell’immagine.  
    Più pixel → più dettagli → immagine più definita.
    
- **Risoluzione cromatica**: quanti **bit per pixel**.  
    Alcuni esempi:
    
    - 1 bit/pixel → bianco/nero
        
    - 8 bit/pixel → 256 livelli di grigio
        
    - 24 bit/pixel → 3 canali (R,G,B), 8 bit ciascuno → $256^3$ colori ≈ 16,7 milioni

    - 32 bit/pixel → spesso RGB a 8 bit più un canale alpha a 8 bit, quindi $2^{32}$ configurazioni complessive
        

Ogni pixel è quindi un **numero (o un gruppo di numeri)**, e ognuno di questi numeri è rappresentato in **binario**.

---

### **4. Audio digitale: campionamento e quantizzazione**

#### **4.1. Che cos’è un segnale audio**

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

#### **4.2. Dal continuo al discreto: il campionamento**

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

#### **4.3. Teorema di Nyquist–Shannon**

Domanda: **quanto spesso** bisogna campionare un segnale per non perdere informazione?

> Se il segnale è limitato in banda e la sua massima frequenza è $f_\text{max}$, la ricostruzione ideale richiede una frequenza di campionamento **strettamente maggiore del doppio** della banda; nei modelli introduttivi si scrive:

$$
f_s\geq2f_\text{max}.
$$

> In pratica si lascia una banda di transizione e si usa un filtro analogico anti-aliasing prima del campionamento.

L’aliasing è l’errore per cui frequenze distinte diventano **indistinguibili** dopo il campionamento.

Per l’audio:

- l’orecchio umano percepisce circa **20 Hz – 20 kHz**
    
- per registrare tutto lo spettro serve almeno: $f_s \ge 40\ \text{kHz}$
    
- per questo il CD audio usa 44.1 kHz.
    

---

#### **4.4. Quantizzazione: da ampiezze reali a numeri finiti**

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
44\,100\cdot2\cdot2=176\,400\ \text{byte/s}\approx172{,}3\ \text{KiB/s}.
$$

Per un minuto di PCM stereo:

$$
176\,400\cdot60=10\,584\,000\ \text{byte}
\approx10{,}58\ \text{MB}
\approx10{,}09\ \text{MiB}.
$$

> ⚠️ La tabella del PDF riporta anche $5{,}3\ \text{MB}$ per un minuto: quel valore corrisponde a un solo canale PCM a 16 bit, non allo stereo specificato. Lo stereo non compresso occupa il doppio; una compressione potrebbe invece produrre altre dimensioni.

#### **4.5. Confronto degli ordini di grandezza**

| informazione | ipotesi | dimensione non compressa o esemplificativa |
| --- | --- | ---: |
| numero in doppia precisione | 64 bit | 8 byte; ordine massimo finito circa $1{,}8\cdot10^{308}$ |
| pagina di testo | 2000 caratteri ASCII | 2000 byte, circa $2\ \text{kB}$ |
| foto | 10 milioni di pixel, RGB 24 bpp | $30\,000\,000$ byte = $30\ \text{MB}$; il PDF mostra circa $2{,}5\ \text{MB}$ dopo JPEG |
| audio PCM mono | 44,1 kHz, 16 bit, 60 s | $5\,292\,000$ byte |
| audio PCM stereo | 44,1 kHz, 16 bit, 60 s | $10\,584\,000$ byte |

Il confronto del PDF tra il massimo di un `double` e il numero stimato di atomi nell’universo osservabile, dell’ordine di $10^{79}$–$10^{80}$, illustra la grande **estensione** della virgola mobile; non significa che tutti gli interi fino a $10^{308}$ siano rappresentabili esattamente, perché la precisione del significando rimane finita.

---

### **5. Video digitale: immagini, audio e compressione**

#### **5.1. Che cos’è un video digitale**

Un video è:

> una sequenza di **fotogrammi** (immagini) mostrati rapidamente,  
> più un **audio** sincronizzato.

Parametri principali:

- **frame rate**: numero di fotogrammi al secondo (es. 24, 30, 60 fps)
    
- **risoluzione** di ogni fotogramma (es. 1280×720, 1920×1080)
    
- **profondità di colore** (es. 24 bit per pixel)
    

---

#### **5.2. Quanto pesa un video non compresso**

Prendiamo:

- risoluzione: $720 \times 576$
    
- 24 bit per pixel (3 byte: R,G,B)
    
- 30 frame/s
    
- durata: 1 minuto
    

**Peso di 1 frame:**

- pixel/frame: $720 \cdot 576 = 414,720$
    
- byte/frame: $414\,720\cdot3=1\,244\,160\ \text{byte}$
    

**Frame in 1 minuto:**

- $30 \cdot 60 = 1,800$ frame
    

**Peso totale:**

- $1\,244\,160\cdot1\,800=2\,239\,488\,000\ \text{byte}$, cioè circa $2{,}24\ \text{GB}$ decimali o $2{,}09\ \text{GiB}$
    

Quindi:

> 1 minuto di video **non compresso** può superare tranquillamente i **2 GB**.

Ovviamente è insostenibile per archiviazione e trasmissione → serve la **compressione**.

---

#### **5.3. Compressione video: idea generale**

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

Il PDF usa come esempio MPEG-2 a $9\ \mathrm{Mbit/s}$:

$$
9\ \mathrm{Mbit/s}\div8=1{,}125\ \mathrm{MB/s},
$$

quindi un minuto occupa circa $67{,}5\ \mathrm{MB}$, senza includere eventuali differenze dovute a overhead e convenzioni di misura.
    

---

### **6. Ordini di grandezza e unità dell’informazione**

Alla base di tutto ci sono le unità che userai continuamente in Architettura, Sistemi Operativi, Reti.

#### **6.1. Bit**

- unità base di informazione: **binary digit**
    
- può valere **0** o **1**
    
- rappresenta l’esito di una decisione sì/no, vero/falso
    

Tutto nasce da qui.

---

#### **6.2. Byte**

- gruppo ordinato di **8 bit**
    
- è l’unità standard di misura della memoria
    
- con 8 bit puoi rappresentare $2^8 = 256$ valori distinti
    

Esempi:

- un carattere ASCII occupa 1 byte
    
- un campione audio a 8 bit = 1 byte
    
- un pixel in scala di grigi a 8 bit = 1 byte
    

---

#### **6.3. Word**

Una **word** è la dimensione “naturale” di elaborazione di una certa architettura.

La sua ampiezza dipende dall’ISA e dal contesto: sono esistite word da 8, 12, 16, 32, 36, 64 bit e altre dimensioni. Non si può dedurre automaticamente la dimensione denominata `word` dalla sola etichetta “CPU a 64 bit”.

---

#### **6.4. Double word, quad word e convenzioni dell’ISA**

- **double word (dword)**: letteralmente due word nel contesto che la definisce;
- **quad word (qword)**: letteralmente quattro word.

Nella terminologia x86, tuttavia, i nomi storici sono dimensioni fisse: `WORD` è 16 bit, `DWORD` 32 bit e `QWORD` 64 bit anche in modalità x86-64. Bisogna quindi seguire la documentazione dell’architettura e non una regola universale.

Usate per:

- interi grandi
    
- indirizzi di memoria
    
- registri per operazioni in virgola mobile o SIMD (vector, AVX, SSE)
    

---

#### **6.5. Riassunto e terminologia del PDF**

Una convenzione frequentemente incontrata è:

- 1 bit → 0 o 1
    
- 8 bit → 1 byte
    
- 16 bit → halfword
    
- 32 bit → word
    
- 64 bit → doubleword oppure quadword, secondo la convenzione dell’ISA

Il PDF menziona anche l’espressione **octal word** per $128$ bit e la collega, come esempio, alle chiavi AES-128. Non è però una denominazione generale standard: in x86, per un operando vettoriale di $128$ bit si incontra piuttosto `xmmword`; una chiave AES-128 ha semplicemente lunghezza $128$ bit.
    

Queste dimensioni sono fondamentali per:

- la struttura dei **registri**
    
- la larghezza dei **bus**
    
- il formato di **indirizzi** e **dati**
    

---

### **7. Schema concettuale finale**

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

### **8. Frase chiave da ricordare**

> **Tutto può essere bit, a patto di usare il numero giusto di bit e la codifica giusta.**

Questa frase è il ponte diretto tra **teoria dell’informazione**, **architettura degli elaboratori** e tutti i corsi successivi (Sistemi Operativi, Reti, Sicurezza, Multimedia…).

> ✅ Testo, immagini, audio e video diventano bit attraverso codifiche diverse. La sequenza di bit non rivela da sola che cosa rappresenta: servono formato, metadati e convenzione interpretativa.
