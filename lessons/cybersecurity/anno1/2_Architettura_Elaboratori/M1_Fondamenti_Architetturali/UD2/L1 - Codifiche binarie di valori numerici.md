## ***Lezione 1: Codifiche binarie di valori numerici***

---

### **1. Introduzione: dal “mondo continuo” al “mondo discreto”**

Nell’Unità 1 hai visto il salto concettuale decisivo:

- dal **continuo** dell’analogico
    
- al **discreto** del digitale,
    
- e, in particolare, alla rappresentazione **binaria**.
    

Ora iniziamo a fare davvero **Architettura degli Elaboratori**:  
come si rappresentano, esattamente, i **numeri** all’interno della macchina?

Punto chiave:

> Prima di essere testi, immagini, suoni… **per il computer tutto è (o diventa) numero intero**.

- Un carattere è associato a un **codice numerico** (es. ASCII, Unicode),
    
- un pixel è descritto da **tre numeri interi** (RGB),
    
- un campione audio è un **numero intero** che rappresenta un’ampiezza,
    
- un indirizzo di memoria è un **numero intero**,
    
- un opcode di istruzione è un **numero intero**.
    

Gli informatici, alla radice, sono **matematici del discreto**:  
giocano con insiemi finiti, combinazioni di simboli, numeri interi, codifiche.

---

### **2. Tutto diventa numero intero**

Tipi di informazione che un calcolatore deve rappresentare:

- **Numeri**: valori, contatori, indici, indirizzi, timestamp…
    
- **Testi**: lettere, cifre, punteggiatura, simboli speciali.
    
- **Immagini**: matrici di pixel (es. $1280 \times 1024$), ogni pixel descritto da uno o più numeri.
    
- **Suoni**: sequenze di campioni, ciascuno un numero che indica la pressione dell’aria in un certo istante.
    
- **Video**: sequenze di immagini + suono, quindi una grande combinazione di numeri.
    

Tutto questo, a un certo livello, viene ricondotto a **sequenze finite di numeri interi**, che poi vengono tradotti in **sequenze di bit**.

Per partire in modo rigoroso, cominciamo dai **numeri interi positivi**.

---

### **3. Sistemi di numerazione posizionale**

Per capire il binario, è fondamentale avere ben chiaro come funziona il sistema che usiamo quotidianamente: il **decimale posizionale**.

L’idea generale è sempre la stessa:

> un numero è una **somma pesata** delle sue cifre,  
> e i pesi sono **potenze della base**.

#### **3.1. Notazione decimale (base 10)**

Il sistema decimale:

- usa **10 cifre**: $0,1,2,\dots,9$,
    
- è **posizionale**: la posizione determina il peso,
    
- i pesi sono **potenze di 10**.
    

Esempio:  
$$3947_{10} = 3\cdot 10^3 + 9\cdot 10^2 + 4\cdot 10^1 + 7\cdot 10^0$$

cioè:

- $3$ migliaia,
    
- $9$ centinaia,
    
- $4$ decine,
    
- $7$ unità.
    

Questa logica è ciò che vogliamo **copiare** in altri sistemi di numerazione, cambiando solo:

- la **base** (10 → 2, 16, …),
    
- l’insieme di **cifre ammesse**.
    

---

#### **3.2. Notazione binaria (base 2)**

Nel sistema binario:

- le cifre possibili sono solo **0 e 1**,
    
- la base è **2**,
    
- i pesi sono potenze di 2: $2^0, 2^1, 2^2, \dots$
    

Esempio:  
$$101101_2 = 1\cdot 2^5 + 0\cdot 2^4 + 1\cdot 2^3 + 1\cdot 2^2 + 0\cdot 2^1 + 1\cdot 2^0$$

cioè:

- $1\cdot 32$
    
- $0\cdot 16$
    
- $1\cdot 8$
    
- $1\cdot 4$
    
- $0\cdot 2$
    
- $1\cdot 1$
    

Somma:  
$$32 + 8 + 4 + 1 = 45_{10}$$

Quindi:

$$101101_2 = 45_{10}$$

---

#### **3.3. Notazione esadecimale (base 16)**

L’esadecimale è molto usato in informatica perché si **aggancia bene al binario** (ogni cifra esadecimale = 4 bit).

- Base: **16**
    
- Cifre:
    
    - da $0$ a $9$ con il solito significato,
        
    - poi $A=10$, $B=11$, $C=12$, $D=13$, $E=14$, $F=15$.
        

Esempio:  
$$3A_{16} = 3\cdot 16^1 + 10\cdot 16^0 = 3\cdot 16 + 10 = 48 + 10 = 58_{10}$$

Collegamento col binario (4 bit per cifra esadecimale):

- $3_{16} = 0011_2$
    
- $A_{16} = 1010_2$
    

Quindi:

$$3A_{16} = 00111010_2$$

---

### **4. Quante cifre (o bit) servono per rappresentare un numero?**

Ogni sistema numerico ha due ingredienti:

- una **base** $B$,
    
- un certo numero di **cifre** disponibili.
    

Più c’è base “potente”, meno cifre servono per rappresentare un certo numero.

---

#### **4.1. In decimale**

Con $c$ cifre in base 10 posso rappresentare tutti i numeri interi da:

- minimo: $0$
    
- massimo: $10^c - 1$
    

Esempi:

- con $2$ cifre: da $0$ a $99$ ($10^2 - 1$),
    
- con $3$ cifre: da $0$ a $999$ ($10^3 - 1$), e così via.
    

Se ho un numero $N$ e voglio sapere quante cifre servono per scriverlo in decimale (per $N \ge 1$):

$$  
c = \left\lfloor \log_{10} N \right\rfloor + 1  
$$

Per $N \ge 1$ queste due sono chiaramente **equivalenti**:

$$⌊log⁡_{10}N⌋+1=⌈log⁡_{10}N⌉$$

Esempio: $N = 512$

- $\log_{10} 512 \approx 2.709$
    
- $\lfloor 2.709 \rfloor = 2$
    
- $c = 2 + 1 = 3$ cifre → infatti $512$ ha 3 cifre.
    

---

#### **4.2. In binario**

Con $b$ bit posso rappresentare tutti i numeri interi da:

- minimo: $0$
    
- massimo: $2^b - 1$
    

Esempi:

- con $3$ bit: $0 \dots 7$ (da $000_2$ a $111_2$),
    
- con $8$ bit: $0 \dots 255$ (da $00000000_2$ a $11111111_2$).
    

Se ho un numero $N$ e voglio sapere quanti bit servono per rappresentarlo in binario (per $N \ge 1$):

$$  
b = \left\lfloor \log_2 N \right\rfloor + 1  
$$

Esempio: $N = 1000$

- $\log_2 1000 \approx 9.97$
    
- $\lfloor 9.97 \rfloor = 9$
    
- $b = 9 + 1 = 10$ bit.
    

Quindi per rappresentare $1000_{10}$ in binario servono **10 bit**.

---

### **5. Conversione tra binario e decimale**

#### **5.1. Da binario a decimale: notazione posizionale**

Metodo:

1. prendi il numero binario,
    
2. assegna l’indice alle cifre da destra verso sinistra ($2^0, 2^1, 2^2, \dots$),
    
3. somma $b_i \cdot 2^i$ per tutte le posizioni.
    

Formula generale:

Se il numero è  
$$b_{n-1} b_{n-2} \dots b_1 b_0$$  
allora:

$$  
(b_{n-1} b_{n-2} \dots b_1 b_0)_2  
= \sum_{i=0}^{n-1} b_i \cdot 2^i  
$$

Esempio:

$$  
101101_2 = 1\cdot 2^5 + 0\cdot 2^4 + 1\cdot 2^3 + 1\cdot 2^2 + 0\cdot 2^1 + 1\cdot 2^0 = 45_{10}  
$$

---

#### **5.2. Da decimale a binario: divisione per 2 con raccolta resti**

Metodo:

1. dividi il numero $N$ per 2,
    
2. annota il **resto** (0 o 1),
    
3. sostituisci $N$ con il **quoziente**,
    
4. ripeti finché il quoziente diventa 0,
    
5. il numero binario è dato dai resti letti **dal basso verso l’alto**.
    

Esempio: convertiamo $1017_{10}$ in binario.

Costruiamo la tabella:

- $1017 : 2 = 508$ resto $1$
    
- $508 : 2 = 254$ resto $0$
    
- $254 : 2 = 127$ resto $0$
    
- $127 : 2 = 63$ resto $1$
    
- $63 : 2 = 31$ resto $1$
    
- $31 : 2 = 15$ resto $1$
    
- $15 : 2 = 7$ resto $1$
    
- $7 : 2 = 3$ resto $1$
    
- $3 : 2 = 1$ resto $1$
    
- $1 : 2 = 0$ resto $1$
    

Scrivendo i resti **dal basso verso l’alto**:

$$  
1017_{10} = 1111111001_2  
$$

(Se vuoi puoi verificare rifacendo la somma posizionale.)

---

### **6. Multipli binari: KB, KiB e l’ambiguità storica**

#### **6.1. I prefissi nel Sistema Internazionale (SI)**

Nel SI (fisica, matematica), i prefissi sono **decimali**:

- $1,\text{kilo} = 10^3$,
    
- $1,\text{mega} = 10^6$,
    
- $1,\text{giga} = 10^9$,  
    ecc.
    

Quindi:

- $1,\text{kB} = 10^3,\text{byte}$,
    
- $1,\text{MB} = 10^6,\text{byte}$,
    
- $1,\text{GB} = 10^9,\text{byte}$.
    

---

#### **6.2. L’hardware, però, ragiona in base 2**

Le memorie sono fatte di **celle indirizzabili**, e gli indirizzi sono numeri binari → **potenze di 2**.

Per questo, storicamente, nel mondo dei calcolatori si è usato:

- $1,\text{KB} \approx 2^{10} = 1024,\text{byte}$,
    
- $1,\text{MB} \approx 2^{20}$,
    
- $1,\text{GB} \approx 2^{30}$.
    

Questa è una comodità matematica per l’hardware, ma entra **in conflitto** con le definizioni decimali del SI.

---

#### **6.3. La confusione concreta: l’hard disk “da 500 GB”**

Quando compri un disco da “500 GB”:

- il produttore intende:  
    $$500 \cdot 10^9 \text{ byte}$$
    
- il sistema operativo, invece, spesso mostra la capacità in **potenze di 2** (GiB):
    

$$  
\text{GiB} = \frac{500 \cdot 10^9}{2^{30}} \approx 465  
$$

Ed è per questo che “spariscono” circa $35$ “giga” agli occhi dell’utente.

---

#### **6.4. Le unità IEC: KiB, MiB, GiB**

Per risolvere l’ambiguità, la norma IEC 80000-13 introduce prefissi binari **ufficiali**:

- $1,\text{KiB} = 1024,\text{B}$,
    
- $1,\text{MiB} = 1024,\text{KiB} = 2^{20},\text{B}$,
    
- $1,\text{GiB} = 1024,\text{MiB} = 2^{30},\text{B}$.
    

Nella pratica, però, molti continuano a usare “KB/MB/GB” con significato binario.

> **Quando studi Architettura degli Elaboratori, assumi sempre base 2**:
> 
> - $1,\text{KB} = 1024,\text{B}$
>     
> - $1,\text{MB} = 1024,\text{KB}$
>     
> - $1,\text{GB} = 1024,\text{MB}$
>     

---

### **7. Operazioni aritmetiche in binario**

Le operazioni si fanno **esattamente come in decimale**, ma usando solo 0 e 1.

#### **7.1. Somma binaria**

Regole locali:

- $0 + 0 = 0$
    
- $0 + 1 = 1$
    
- $1 + 0 = 1$
    
- $1 + 1 = 0$ con **riporto di 1**
    

Esempio:

$$  
\begin{array}{cccccc}  
& 1 & 0 & 1 & 1 & 0 \\  
+& 0 & 0 & 1 & 1 & 1 \\  
\hline  
& 1 & 0 & 0 & 1 & 0 \\  
\end{array}  
$$

(colonna per colonna, da destra a sinistra, gestendo i riporti come fai in base 10.)

---

#### **7.2. Sottrazione binaria**

Regole locali, pensando al prestito:

- $1 - 0 = 1$
    
- $1 - 1 = 0$
    
- $0 - 0 = 0$
    
- $0 - 1$ → serve un **prestito** dalla colonna successiva.
    

In base 10, un prestito vale 10;  
in base 2, un prestito vale **2**, perché stai prendendo 1 unità dalla colonna di sinistra che pesa il doppio.

Esempio concettuale:

- vuoi fare $0 - 1$ in una colonna,
    
- chiedi un prestito alla colonna a sinistra,
    
- quella colonna perde 1 (in binario) e ti “dona” $2$,
    
- diventa quindi $(2 + 0) - 1 = 1$.
    

La logica è **identica** alla sottrazione decimale, ma con base $B=2$.

---

#### **7.3. Prodotto binario**

Enorme semplificazione:  
in binario, una cifra è solo 0 o 1, quindi:

- $0 \cdot$ (qualcosa) = 0
    
- $1 \cdot$ (qualcosa) = (qualcosa)
    

Quando moltiplichi due numeri binari:

- ogni riga di prodotto parziale è:
    
    - una copia del moltiplicando (se il bit del moltiplicatore è 1),
        
    - una riga di zeri (se il bit è 0),
        
- poi sommi tutti i prodotti parziali con la **somma binaria**.
    

Quindi la moltiplicazione binaria si riduce a:

- **shift** (spostamenti a sinistra),
    
- **somme**.
    

---

### **8. Numeri frazionari in notazione posizionale**

Finora abbiamo visto **solo numeri interi**.  
Ma il computer deve gestire anche numeri con parte frazionaria: $18{,}75$, $0{,}3$, $-127{,}3$, ecc.

La buona notizia:

> la notazione posizionale funziona **anche con la virgola**, sia in base 10 che in base 2.

---

#### **8.1. In decimale**

Esempio:

$$  
127{,}3_{10} = 1\cdot 10^2 + 2\cdot 10^1 + 7\cdot 10^0 + 3\cdot 10^{-1}  
$$

cioè:

- parte intera: $1\cdot 100 + 2\cdot 10 + 7\cdot 1 = 127$
    
- parte frazionaria: $3\cdot 10^{-1} = 0{,}3$
    

---

#### **8.2. In binario**

Esempio:

$$  
101{,}01_2 = 1\cdot 2^2 + 0\cdot 2^1 + 1\cdot 2^0 + 0\cdot 2^{-1} + 1\cdot 2^{-2}  
$$

Calcolo:

- parte intera: $1\cdot 4 + 0\cdot 2 + 1\cdot 1 = 5$
    
- parte frazionaria: $0\cdot \frac{1}{2} + 1\cdot \frac{1}{4} = 0{,}25$
    

Quindi:

$$  
101{,}01_2 = 5{,}25_{10}  
$$

La regola è la stessa:

- a sinistra della virgola → potenze **positive** della base,
    
- a destra della virgola → potenze **negative** della base.
    

---

### **9. Il problema: precisione ed estensione**

Per rappresentare numeri reali con:

- **alta precisione** (molte cifre dopo la virgola),
    
- **grande estensione** (numeri molto grandi),
    

in notazione posizionale “semplice” servirebbero:

- tanti bit per la parte intera,
    
- tanti bit per la parte frazionaria,
    

cioè una **parola binaria lunghissima**.

Esempi concettuali:

- $0{,}0000001$ → servono molte potenze negative (molti bit dopo la virgola),
    
- $1{,}000,000,000,000$ → servono molte potenze positive (molti bit prima della virgola).
    

Per gestire **entrambi** i casi con un numero fisso di bit nasce l’idea della **virgola mobile**.

---

### **10. Virgola mobile (floating point) – idea concettuale**

Definizione:

> La **virgola mobile** è un metodo per rappresentare numeri reali in cui la posizione della **virgola** non è fissa, ma viene **codificata tramite un esponente**, come nella notazione scientifica.

Forma generale:

$$  
x = m \cdot b^e  
$$

dove:

- $m$ = **mantissa** (o significando): contiene le cifre significative,
    
- $b$ = **base** (nei computer: $b = 2$),
    
- $e$ = **esponente**: intero (positivo o negativo) che “sposta la virgola”.
    

---

#### **10.1. Esempi in notazione scientifica decimale**

- $3140000 = 3{,}14 \cdot 10^6$
    
- $0{,}00052 = 5{,}2 \cdot 10^{-4}$
    

La mantissa conserva le cifre “importanti”, l’esponente indica l’ordine di grandezza.

---

#### **10.2. Esempio concettuale in binario**

In binario:

- base $b = 2$,
    
- rappresentiamo un numero come:  
    $$x = m \cdot 2^e$$
    

La mantissa è un numero binario “normalizzato”, del tipo:

- $1{,}xxxxx\dots_2$ (la prima cifra diversa da zero è 1).
    

L’esponente dice **dove** posizionare la virgola.

Risultato:

- con un numero **fisso** di bit,  
    posso rappresentare numeri molto piccoli e molto grandi,  
    spostando la scala con l’esponente.
    

> È il compromesso ideale tra **precisione** ed **estensione**.

(Lo standard concreto che troverai più avanti è l’IEEE 754, ma qui ci basta l’idea.)

---

### **11. Numeri relativi: come rappresentare il segno**

Finora abbiamo sempre considerato **numeri positivi**.  
Ma in realtà servono anche valori negativi: temperatura sotto 0, saldo in rosso, spostamenti verso sinistra, ecc.

Idea naturale:

> aggiungere un **bit di segno**.

---

#### **11.1. Rappresentazione con modulo e segno**

Un numero relativo può essere visto come:

$$  
\text{numero} = \text{segno} + \text{modulo}  
$$

In binario:

- il **segno** è una informazione binaria → basta **1 bit**,
    
- il **modulo** è un numero intero positivo rappresentato con i bit rimanenti.
    

Convenzione classica:

- bit di segno $= 0$ → numero **positivo**,
    
- bit di segno $= 1$ → numero **negativo**.
    

Struttura con $b$ bit totali:

- $1$ bit → segno,
    
- $b-1$ bit → modulo.
    

Esempio con $b = 4$:

- bit 3 (il più a sinistra) → segno,
    
- bit 2–1–0 → modulo (0–7).
    

Valori rappresentabili:

- modulo massimo con $b-1$ bit: $2^{b-1} - 1$,
    
- intervallo dei numeri relativi con modulo e segno:
    

$$  
-(2^{b-1}-1), \ \dots, \ -1,\ -0,\ +0,\ 1,\ \dots, \ (2^{b-1}-1)  
$$

---

#### **11.2. Limiti del modulo e segno**

Questa rappresentazione è **semplice**, ma **non è quella usata nei processori reali**, perché:

1. esistono **due zeri**: $+0$ e $-0$,
    
2. le operazioni aritmetiche sono più complesse da implementare.
    

Per questo, nelle architetture reali, si preferisce la rappresentazione in **complemento a due**, che vedrai in una lezione successiva.

Ma capire modulo e segno è importante perché:

- è il modello concettuale più immediato,
    
- ti fa vedere chiaramente come entra in gioco il **bit di segno**.
    

---

### **12. Sintesi finale della lezione**

In questa lezione hai messo insieme parecchi mattoncini fondamentali:

- i **sistemi di numerazione posizionale** (decimale, binario, esadecimale),
    
- il legame tra **bit**, **byte**, **multipli binari** e capacità di memoria,
    
- come **convertire** tra binario e decimale (posizionale e divisione per 2),
    
- come funzionano le **operazioni aritmetiche** in binario (somma, prestito, prodotto),
    
- come si rappresentano **numeri frazionari** e perché serve la **virgola mobile**,
    
- come si introducono i **numeri relativi** con il modello **modulo e segno**.
    

Da qui in poi, ogni volta che sentirai parlare di:

- **parola di $b$ bit**,
    
- **registri**,
    
- **bus dati**,
    
- **overflow**,
    
- **floating point**,
    
- **complemento a due**,
    

stai in realtà manipolando **codifiche binarie di valori numerici**.
