## **Lezione 4: Cifrari a rotazione – Enigma**

### **1. Introduzione**

Dopo i cifrari polialfabetici manuali (come Vigenère), la crittografia fece un salto decisivo con l’introduzione di **macchine elettromeccaniche a rotazione**.  
Questi sistemi automatizzavano il principio di Vigenère, cambiando **alfabeto cifrante dopo ogni lettera**.

Il più celebre di questi dispositivi è la **macchina Enigma**, usata dalla **Germania nazista durante la Seconda guerra mondiale**.

---

### **2. Il predecessore: il cilindro di Jefferson**

Il **terzo presidente degli Stati Uniti**, **Thomas Jefferson** (fine XVIII secolo), ideò un **cilindro cifrante** lungo circa **15 cm**, composto da **36 dischi di legno**.

- Ogni disco riportava l’alfabeto disposto in ordine casuale.
    
- I dischi potevano essere **ruotati indipendentemente**, generando **36! ≈ 3.72 × 10⁴¹ combinazioni** possibili.
    
- Una volta impostata la parola chiave, si poteva leggere il messaggio cifrato allineando una riga alternativa.
    

🧠 Questo concetto — **rotori multipli con alfabeti diversi** — è alla base di Enigma.

---

### **3. Le prime macchine a rotori (1918–1930)**

L’idea di utilizzare **rotori elettrici** venne ripresa da diversi inventori tra il 1918 e il 1930.

- **E. H. Hebern** (USA) costruì nel 1918 la prima **macchina a rotori elettrici**.
    
    - Fondò nel 1921 la _Hebern Electric Code Inc._, la prima azienda crittografica americana.
        
    - La U.S. Navy utilizzò nel 1929 una versione con **5 rotori**.
        
- **Boris Hagelin** (Svezia) progettò macchine più avanzate:
    
    - **B-21 (1925)** per l’esercito svedese,
        
    - **C-36 (1934)** per i francesi,
        
    - **C-48 (1940)** prodotta in **140.000 esemplari**, usata dall’esercito americano (ribattezzata **M-209**),
        
    - **C-52, CD-55, T-55** dal 1948 con la sua azienda svizzera.
        

Queste macchine prepararono il terreno alla **Enigma**, inventata in Germania nel 1918.

---

### **4. Enigma: nascita e struttura**

**Arthur Scherbius** brevettò la **macchina Enigma** nel 1918.  
Fu inizialmente un prodotto commerciale, poi adottata dal **Wehrmacht** come sistema di cifratura militare.

#### **Componenti principali**

1. **Tastiera** → per inserire il testo in chiaro.
    
2. **Scambiatore (plugboard o pannello di prese multiple)** → scambia alcune lettere tra loro.
    
3. **Rotori (o ruote cifranti)** → realizzano la trasformazione elettrica che cambia a ogni pressione di tasto.
    
4. **Riflettore (reflector)** → rimanda il segnale elettrico indietro, completando la cifratura.
    
5. **Visore luminoso** → mostra la lettera cifrata corrispondente.
    

---

### **5. Funzionamento di Enigma**

Ogni pressione di tasto fa passare la corrente elettrica attraverso:

1. il **pannello di scambio**,
    
2. i **tre rotori in serie**,
    
3. il **riflettore**,
    
4. e poi nuovamente attraverso i rotori, fino al visore luminoso.
    

Dopo ogni lettera, **almeno un rotore ruota**, modificando completamente la mappatura di cifratura.  
Così, **la stessa lettera in chiaro non viene mai cifrata nello stesso modo**.

💡 In altre parole, l’alfabeto cifrante **cambia a ogni battuta** → è una **cifratura polialfabetica meccanizzata**.

---

### **6. Il meccanismo dei rotori**

I rotori di Enigma funzionano come **un contachilometri (odometro)**:

- Il **primo rotore** ruota a ogni pressione di tasto.
    
- Quando compie un giro completo, **fa scattare** il secondo rotore di una posizione.
    
- Analogamente, il **secondo rotore**, una volta completato il proprio ciclo, **attiva il terzo**.
    
- Il **riflettore** assicura che nessuna lettera venga cifrata come se stessa.
    

📜 Questo sistema genera una **sequenza ciclica molto lunga**, che impedisce l’analisi di frequenza classica.

---

### **7. Numero di chiavi possibili**

Enigma disponeva di un numero colossale di configurazioni:

#### **a) Orientamento dei rotori**

Ogni rotore si poteva posizionare in **26 modi diversi** →  
$26^3 = 17,576$ combinazioni (con 3 rotori).

Con 5 rotori disponibili, e solo 3 scelti alla volta:

- 60 possibili disposizioni (permute di 3 su 5).
    
- 11 881 376 configurazioni totali solo per la disposizione e rotazione dei rotori.
    

#### **b) Pannello di scambio (Plugboard)**

Si potevano collegare **12 coppie di lettere** su 26 disponibili →  
circa **10¹⁴ combinazioni**.

👉 **Totale stimato:** circa **10 milioni di miliardi di chiavi** ($\approx 10^{19}$).

---

### **8. Sicurezza di Enigma**

- Il **pannello di scambio** realizza una **sostituzione monoalfabetica** statica, con un numero elevato di chiavi, ma **non cambia durante la cifratura**.
    
- I **rotori**, invece, cambiano posizione a ogni lettera → l’**alfabeto cifrante varia continuamente**.
    
- Di conseguenza, Enigma **resiste all’analisi di frequenza tradizionale**.
    
- Il **periodo di cifratura** è così ampio che occorrerebbero migliaia di lettere cifrate per ripetere una stessa configurazione.
    

---

### **9. Procedura operativa**

Ogni messaggio richiedeva una **configurazione iniziale segreta**, definita in tre passaggi:

1. **Assetto del pannello di scambio**
    
    - Es. A↔L, P↔R, T↔D, B↔W, K↔F, O↔Y
        
2. **Disposizione dei rotori**
    
    - Es. ordine 1–3–2
        
3. **Orientamento iniziale dei rotori**
    
    - Es. Q–C–W
        

Per maggiore sicurezza, veniva scelta anche una **chiave di messaggio**, cifrata due volte con la **chiave giornaliera**.  
Esempio:

- Chiave giornaliera: `QCW`
    
- Chiave di messaggio: `PGH` → ripetuta `PGHPGH`
    
- Cifratura con QCW → `KIVBJE`
    
- Il messaggio viene cifrato usando `PGH` come nuova chiave di partenza.
    

---

### **10. Crittoanalisi di Enigma**

#### **Rejewski e i matematici polacchi**

Il matematico **Marian Rejewski**, con **Jerzy Różycki** e **Henryk Zygalski**, avviò la prima decifrazione sistematica di Enigma.

- Analizzò le **concatenazioni di lettere** generate dalle doppie cifrature di chiavi di messaggio.
    
- Scoprì che il numero di collegamenti dipendeva **solo dall’ordine dei rotori**, non dalle chiavi giornaliere.
    
- Classificando tutti i possibili ordini (≈ 105 456 combinazioni), riuscì a creare un **repertorio di configurazioni**.
    

Questo lavoro permise di ridurre drasticamente il tempo necessario per ricostruire l’assetto della macchina.

---

### **11. Bletchley Park e Alan Turing**

Con lo scoppio della guerra, i matematici polacchi condivisero le loro scoperte con gli inglesi.  
Il progetto passò alla **Government Code and Cipher School** di **Bletchley Park**.

#### **Debolezze di Enigma**

1. Nessuna lettera cifra mai se stessa.
    
2. Una lettera non cifra mai una contigua.
    
3. Uso di **chiavi semplici e ripetitive** (dette _cillies_, es. `QWEQWE`).
    
4. Trasmissione in chiaro della **posizione iniziale dei rotori**.
    

#### **La macchina “Bombe”**

- Progettata da **Alan Turing** e **Gordon Welchman**.
    
- Basata su principi meccanici che testavano rapidamente tutte le configurazioni possibili.
    
- Permise di **automatizzare la decifrazione**, rendendo Enigma vulnerabile.
    

---

### **12. In sintesi**

- Enigma fu il **primo cifrario meccanico moderno**, basato su **rotori elettromeccanici**.
    
- Il suo principio fondamentale era la **rotazione dinamica degli alfabeti cifranti**.
    
- Era considerata **teoricamente inviolabile**, ma fu sconfitta da:
    
    - **errori umani**,
        
    - **uso improprio delle chiavi**,
        
    - e dalle **macchine decifranti di Turing**.
        

💡 _Conclusione:_  
La decifrazione di Enigma segnò la nascita della **crittanalisi moderna automatizzata**, aprendo la strada ai **computer digitali** e alla **crittografia elettronica**.