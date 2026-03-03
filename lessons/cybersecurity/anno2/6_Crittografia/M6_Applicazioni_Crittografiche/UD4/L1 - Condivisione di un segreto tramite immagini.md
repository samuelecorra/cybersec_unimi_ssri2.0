
## **Lezione 1: Condivisione di un segreto tramite immagini**

### **1. Introduzione**

La **crittografia visuale** è una tecnica che permette di **cifrare immagini** o di **condividere segreti** in forma visiva, in modo che la **decodifica non richieda alcun calcolo matematico**, ma solo la **percezione visiva umana**.  
Questa tecnica è utile per applicazioni di **autenticazione, protezione di dati sensibili** o **comunicazione sicura tramite immagini**.

L’idea è suddividere un’immagine segreta in più **sottoimmagini (share)**, ciascuna delle quali non contiene informazioni comprensibili.  
Solo **sovrapponendo le share** corrette si può ricostruire visivamente il messaggio originale.

---

### **2. Concetto base**

Nel modello più semplice (schema **2 su 2**), l’immagine segreta viene divisa in **due share**:

- **Share 1** → contiene una parte apparentemente casuale dell’immagine.
    
- **Share 2** → contiene un’altra parte casuale.
    

Nessuna delle due, presa singolarmente, fornisce alcuna informazione utile.  
Ma se le due share vengono **sovrapposte**, la **figura originale** diventa visibile.

#### **Esempio schematico**

```
Immagine originale →  ███░░
Share 1             →  █░█░░
Share 2             →  ░█░██
Sovrapposizione     →  █████
```

La **ricostruzione visiva** è ottenuta semplicemente combinando i pattern delle due immagini, senza algoritmi o chiavi numeriche.

---

### **3. Cifratura di un pixel**

Ogni **pixel dell’immagine originale** viene convertito in un gruppo di **sottopixel** distribuiti tra le share.

#### **Esempio: codifica di un pixel nero o bianco**

|Pixel originale|Possibile codifica Share A|Possibile codifica Share B|Risultato sovrapposto|
|---|---|---|---|
|Nero|▓░|░▓|▓▓ (nero)|
|Bianco|▓░|▓░|▓░ (grigio/bianco)|

- La **combinazione casuale dei sottopixel** garantisce la **sicurezza**: da una singola share non si può dedurre se il pixel originale fosse nero o bianco.
    
- La **visione binoculare umana** ricompone l’immagine tramite il contrasto visivo.
    

---

### **4. Esempio pratico**

- **Input**: immagine binaria (bianco/nero) da cifrare.
    
- **Output**: due immagini apparentemente casuali (Share 1 e Share 2).
    
- **Decodifica**: sovrapposizione fisica o digitale delle due share (ad esempio stampandole su lucidi).
    

#### **Rappresentazione grafica**

```
Immagine segreta → Share 1 + Share 2 → Sovrapposizione = Segreto rivelato
```

Questo meccanismo è concettualmente simile a uno **schema di Shamir (2,2)**, ma adattato al dominio visivo.

---

### **5. Sicurezza dello schema**

Le share sono generate in modo tale che:

- Ogni pixel cifrato ha la **stessa probabilità** di rappresentare un pixel bianco o nero.
    
- Osservando una sola share, **nessuna informazione** sull’immagine originale è recuperabile.
    
- Solo la **sovrapposizione corretta** delle share restituisce il segreto.
    

In termini probabilistici:  
$$  
P(\text{pixel originale} | \text{una share}) = P(\text{pixel originale})  
$$

---

### **6. Matrici di base**

Le codifiche dei pixel possono essere rappresentate da **matrici binarie**.  
Esempio di due possibili insiemi di matrici base:

$$  
S_0 =  
\begin{bmatrix}  
0 & 1 \\  
0 & 1  
\end{bmatrix},  
\quad  
S_1 =  
\begin{bmatrix}  
0 & 1 \\  
1 & 0  
\end{bmatrix}  
$$

oppure, in alternativa:

$$  
S_0 =  
\begin{bmatrix}  
1 & 0 \\  
1 & 0  
\end{bmatrix},  
\quad  
S_1 =  
\begin{bmatrix}  
1 & 0 \\  
0 & 1  
\end{bmatrix}  
$$

Queste matrici descrivono come distribuire i sottopixel per rappresentare i pixel bianchi e neri in ciascuna share.

---

### **7. Vantaggi e limiti**

**Vantaggi:**

- Decodifica **immediata** e **senza calcolo** (basta sovrapporre).
    
- Schema **robusto e semplice da implementare**.
    
- Utilizzabile anche in ambienti senza dispositivi elettronici (es. autenticazione fisica).
    

**Limiti:**

- **Aumento della dimensione** dell’immagine cifrata.
    
- **Riduzione del contrasto visivo** rispetto all’immagine originale.
    
- Difficoltà nel gestire immagini a più livelli di colore o in scala di grigi (richiede estensioni dello schema base).
    

---

### **8. Sintesi finale**

- La **crittografia visuale** permette di **cifrare e condividere un segreto tramite immagini**.
    
- Le **share** non rivelano alcuna informazione singolarmente.
    
- La **decodifica avviene otticamente**, senza calcoli matematici.
    
- L’intero schema si basa su **probabilità e percezione visiva**, rendendolo una delle applicazioni più affascinanti della crittografia moderna.