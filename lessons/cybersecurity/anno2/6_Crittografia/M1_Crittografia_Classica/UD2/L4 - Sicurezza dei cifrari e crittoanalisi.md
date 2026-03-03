# **Lezione 4: Sicurezza dei cifrari e crittoanalisi**

---

### **1. Sicurezza del cifrario**

Un **algoritmo di cifratura** è considerato _sicuro_ se, anche conoscendo:

- **uno o più testi cifrati**,
    
- **l’algoritmo stesso di cifratura**,
    

non è possibile, in modo semplice o veloce:

- **risalire al testo in chiaro**, oppure
    
- **determinare la chiave segreta** $K$.
    

Una volta individuata la chiave $K$, conoscendo la funzione di decifratura $D_K$,  
**tutti i messaggi cifrati con la stessa chiave diventano vulnerabili.**

---

### **2. Principio di Kerckhoffs**

Secondo **Jean Guillaume Hubert Victor François Alexandre Auguste Kerckhoffs von Nieuwenhof** (1835–1903), nella sua opera _La Cryptographie Militaire_ (1883),

> “La sicurezza di un sistema crittografico deve dipendere **solo dalla segretezza della chiave** e non dalla segretezza dell’algoritmo.”

---

#### **Significato pratico**

- L’**algoritmo** può essere pubblico (e spesso lo è).
    
- Solo la **chiave segreta** deve restare ignota all’avversario.
    
- In questo modo il sistema è **verificabile, robusto e trasparente**, senza affidarsi al “segreto” del metodo.
    

Questo principio è alla base della **crittografia moderna**.

---

### **3. Tipi di analisi crittografica**

La **crittanalisi** è lo studio dei metodi usati per violare o indebolire un sistema crittografico.  
Può essere condotta in due modi principali:

#### **a) Analisi teorica**

- Si basa sulle **proprietà matematiche dell’algoritmo**.
    
- Ricerca **debolezze strutturali** o relazioni tra chiavi, messaggi e testi cifrati.
    

#### **b) Analisi sperimentale**

- Sfrutta **informazioni note o osservabili**, come:
    
    - testo in chiaro parziale,
        
    - coppie di testi chiaro/cifrato.
        

---

### **4. Attacco a forza bruta (Brute Force)**

È la forma più semplice ma anche più costosa di attacco.  
Consiste nel **provare tutte le possibili chiavi**, fino a trovare quella che produce un testo in chiaro plausibile.

- In media, serve provare **la metà delle chiavi possibili**.
    
- La difficoltà cresce **esponenzialmente con la lunghezza della chiave**.
    

---

### **5. Esempio: forza bruta su cifrari a shift**

Testo cifrato:  
`PHHW PH DIWHU WKH WRJD SDUWB`

Si prova a decifrare con tutte le 25 chiavi possibili (rotazioni dell’alfabeto).  
Dopo alcuni tentativi si ottiene:

```
1  oggv og chvgt vjg vqic rctva
2  nffu nf bgufs uif uphb qbsuz
3  meet me after the toga party
4  ldds ld zesdq sgd snfz ozqsx
```

✅ Al **terzo tentativo** (shift = 3) il messaggio diventa leggibile:

> **“MEET ME AFTER THE TOGA PARTY”**

È quindi decifrato correttamente.

---

### **6. Numero di chiavi possibili**

Nel caso di un **cifrario a sostituzione monoalfabetico**, il numero di chiavi corrisponde a **tutte le permutazioni possibili dell’alfabeto**.

$$  
N_{\text{chiavi}} = 26! \approx 4 \times 10^{26}  
$$

- Con un alfabeto ridotto di 21 lettere: circa $5 \times 10^{19}$ possibilità.
    
- Anche se teoricamente enorme, l’uso di analisi statistiche e pattern può ridurre notevolmente il lavoro del crittoanalista.
    

---

### **7. Tempi di forza bruta (stime pratiche)**

| **Dimensione chiave (bit)** | **Numero di chiavi possibili** | **Tempo a 1 critt/s**     | **Tempo a 10⁶ critt/s**   |
| --------------------------- | ------------------------------ | ------------------------- | ------------------------- |
| 32                          | $2^{32} = 4.3 \times 10^9$     | 35.8 minuti               | 2.15 ms                   |
| 56                          | $2^{56} = 7.2 \times 10^{16}$  | 1142 anni                 | 10.01 h                   |
| 128                         | $2^{128} = 3.4 \times 10^{38}$ | $5.4 \times 10^{24}$ anni | $5.4 \times 10^{18}$ anni |
| 168                         | $2^{168} = 3.7 \times 10^{50}$ | $5.9 \times 10^{36}$ anni | $5.9 \times 10^{30}$ anni |
| 256                         | $2^{256} = 7.2 \times 10^{77}$ | $6.4 \times 10^{61}$ anni | $6.4 \times 10^{55}$ anni |
| 26 caratteri (permut.)      | $26! \approx 4 \times 10^{26}$ | $6.4 \times 10^{12}$ anni | $6.4 \times 10^{6}$ anni  |

> 💡 **Nota 1:** $2^{77}$ ≈ numero stimato di atomi nell’universo visibile.

> 💡 **Nota 2**: 56bit è la dimensione usata dal DES $\rightarrow$ già sicurezza abbastanza elevata!

---

### **8. Tipi di attacco crittoanalitico**

Gli attacchi si classificano in base **alle informazioni a disposizione dell’avversario**:

#### **a) Known Ciphertext Attack (KCA)**

L’avversario conosce **solo il testo cifrato**. E' lo scenario in cui l'opponent ha la minore quantità di informazioni possibili!

#### **b) Known Plaintext Attack (KPA)**

L’avversario conosce **alcune coppie** di testo in chiaro e testo cifrato, o addirittura sia il cifrato che il plaintext interi.

#### **c) Chosen Plaintext Attack (CPA)**

L’avversario può **scegliere un testo in chiaro** e ottenere la sua cifratura. Ergo può accedere alla procedura di encryption.

#### **d) Chosen Ciphertext Attack (CCA)**

L’avversario può **scegliere un testo cifrato** e ottenere la sua decifratura. E' lo scenario analogo ma inverso al precedente!

#### **e) Chosen Text Attack (CTA)**

L’avversario può ottenere **sia cifratura che decifratura** di coppie chiaro/cifrato.

---

### **9. Classificazione della sicurezza**

|**Tipo di sicurezza**|**Descrizione**|
|---|---|
|**Unconditionally Secure (incondizionatamente sicuro)**|Anche con risorse e tempo illimitati, è **impossibile** risalire al messaggio in chiaro. L’unico sistema noto di questo tipo è il **One-Time Pad**.|
|**Computationally Secure (computazionalmente sicuro)**|È teoricamente violabile, ma il tempo necessario per farlo è **maggiore della vita utile delle informazioni cifrate**. È la base della **crittografia moderna**.|

---

### **10. In sintesi**

In questa lezione abbiamo analizzato:

- Il **principio di Kerckhoffs**, che stabilisce che solo la **chiave** deve restare segreta.
    
- Gli **attacchi a forza bruta**, basati sul test di tutte le chiavi.
    
- Le **diverse tipologie di attacco** a seconda delle informazioni disponibili.
    
- La distinzione tra **sicurezza assoluta** e **sicurezza computazionale**.
    

💡 **Conclusione:**  
I sistemi crittografici moderni si basano sull’assunzione che l’avversario disponga di **risorse limitate** e che la **difficoltà computazionale** renda l’attacco **impraticabile** nei tempi reali.