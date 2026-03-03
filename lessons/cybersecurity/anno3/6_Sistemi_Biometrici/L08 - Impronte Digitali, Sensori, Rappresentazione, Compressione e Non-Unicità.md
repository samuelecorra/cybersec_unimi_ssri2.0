# **Lezione 8: Impronte Digitali, Sensori, Rappresentazione, Compressione e Non-Unicità**

---

### **1. Introduzione generale**

Questa lezione approfondisce due blocchi fondamentali:

1. **Parte A – Sensori e acquisizione delle impronte digitali**  
    Come si cattura l’impronta? Quali sensori esistono? Quali sono i problemi reali della cattura?
    
2. **Parte B – Rappresentazione dell’impronta, compressione, formati e non-unicità**  
    Come il sistema biometrico rappresenta l’impronta? Come si comprime? Quanto è unica un’impronta?
    

---

## **PARTE A – Acquisizione e Sensori**

---

### **2. Qual è il compito dei sensori?**

Il sensore deve catturare **la distribuzione delle creste e delle valli** presenti sul polpastrello.

Le impronte possono essere viste:

- come **immagini 2D**, cioè pattern di livelli di grigio;
    
- oppure come **superfici 3D**, perché in realtà le creste sono rilievi fisici.
    

> **Osservazione chiave:** l’immagine digitale dell’impronta non è il tratto biometrico originale, ma una **proiezione semplificata e distorta** del vero rilievo tridimensionale del dito. Questo è il cuore dell’imprecisione del dominio biometrico.

---

### **3. Modalità di acquisizione**

#### **3.1 Acquisizione off-line**

Due fasi:

1. Il dito viene inchiostrato.
    
2. L’impronta viene rotolata su carta, poi digitalizzata tramite scanner ottico ad alta risoluzione.
    
![](imgs/Pasted%20image%2020260216135050.png)

**Impronte latenti** (scena del crimine):  
Sono off-line per definizione: le creste depositano **grasso** sulla superficie → la traccia viene evidenziata con reagenti chimici.

![](imgs/Pasted%20image%2020260216135125.png)

Problemi:

- soggetto **non cooperativo**
    
- impronta **degradata**, distorta, incompleta
    
- processo di sollevamento complesso
    

---

#### **3.2 Acquisizione live-scan**

Il dito tocca direttamente il sensore digitale. Sono i sensori dei telefoni, dei varchi aziendali, ecc.

Tipologie:

- **Sensori ottici**
    
    - FTIR (Frustrated Total Internal Reflection)
        
    - Scanner tradizionali
        
- **Sensori a stato solido**
    
    - **Capacitivo** (il più comune)
        
    - **Piezoelettrico** (pressione)
        
    - **Termico** (temperatura)
        
- **Sensori ad ultrasuoni**
    
    - Cattura 3D del rilievo (tecnologia moderna)

![](imgs/Pasted%20image%2020260216135151.png)

---

### **4. Panoramica visiva dei sensori**

La slide mostra come **lo stesso dito** appare diversissimo su sensori differenti:

- ottico → immagine molto contrastata
    
- capacitivo → creste nette
    
- piezoelettrico → pattern particolare
    
- termico sweeping → immagine “strisciata”
    
- termico statico
    
- impressione inchiostrata
    
- impronta latente
    
- ricostruzione 3D
    

> **Messaggio chiave:** ogni sensore crea una **sua versione “interpretata”** dell’impronta.

---

### **5. Proprietà fondamentali del sensore**

Quando scegli un sensore devi valutare:

- **Risoluzione (dpi)**  
    500 dpi è lo standard FBI.
    
- **Area attiva**
    
- **Numero di pixel**
    
- **Bit per pixel** (8, 16, 24)
    
- **Contrasto**
    
- **Distorsione geometrica**

![](imgs/Pasted%20image%2020260216135243.png)

Altre caratteristiche importanti:

- FPS (frame per secondo)
    
- Finger-detect hardware/software
    
- Supporto a canali **crittografati**
    
- Driver, SDK, documentazione.
    

---

### **6. Principio FTIR – Riflessione interna totale**

Quando un raggio di luce passa da un mezzo ad alto indice (vetro) a uno con indice minore (aria), se supera l’angolo critico, la luce **viene riflessa totalmente**.

![](imgs/Pasted%20image%2020260216135323.png)

Ma se sulla superficie del vetro poggia un **ridge**,  
→ l’indice di rifrazione vetro-pelle è diverso  
→ la luce non viene riflessa  
→ il pixel diventa scuro.

Questo genera la tipica immagine ottica dell’impronta: **cresta = buio**, **valle = chiaro**.

![](imgs/Pasted%20image%2020260216135427.png)

![](imgs/Pasted%20image%2020260216135446.png)

---

### **7. Sensori a stato solido**

Funzionano misurando la variazione di **capacità elettrica** tra due piastre quando la cresta tocca la superficie.

Pixel = microcircuito che misura variazioni infinitesime.

Varianti:

- capacitivo
    
- piezoelettrico (pressione)
    
- termico (variazione di temperatura)

![](imgs/Pasted%20image%2020260216135510.png)

---

### **8. Sensori 3D ad ultrasuoni**

Catturano il **rilievo fisico reale**, non solo l'immagine 2D.

Vantaggi:

- potenziale accuratezza maggiore
    
- maggiore anti-spoofing
    
- leggono anche sotto vetro e materiali non conduttivi

![](imgs/Pasted%20image%2020260216135534.png)

---

### **9. Problemi di acquisizione**

#### **9.1 Pressione**

Aumentando la pressione:

![](imgs/Pasted%20image%2020260216135614.png)

- creste che prima sembravano discontinue ora diventano continue
    
- emergono dettagli:
    
    - **pori**
        
    - **incipient ridges** (presenti nel 45% della popolazione)
        
- troppa pressione = creste che si schiacciano o uniscono → **distorsione irreversibile**
    

---

#### **9.2 Roto-traslazione e deformazioni (slide p.16)**

Quando il dito non scorre perfettamente:

- alcune parti ruotano
    
- altre rimangono fisse  
    → si generano **deformazioni non lineari**

![](imgs/Pasted%20image%2020260216135642.png)

La slide mostra 4 mappe reali di deformazioni, tutte con pattern diversi.

---

### **10. Sensori commerciali moderni**

#### **10.1 Qualcomm 3D Snapdragon Sense ID**

- Sensore **ultrasonico 3D**
    
- Cattura dettagli nel volume della pelle
    
- Ottima resistenza allo spoofing rispetto ai capacitivi

![](imgs/Pasted%20image%2020260216135735.png)

---

#### **10.2 Nexus 6P – FPC1025**

- Capacitivo
    
- 508 dpi
    
- Lettura 360°
    
- Funziona con dita asciutte e bagnate
    
- 600 ms

![](imgs/Pasted%20image%2020260216135809.png)

---

#### **10.3 iPhone 5 / iPhone 6 (slide p.20)**

- Capacitivo
    
- Funziona anche sotto vetro di protezione
    
- Spessore: 170 µm
    
- Risoluzione: 500 dpi
    
![](imgs/Pasted%20image%2020260216135844.png)

---

#### **10.4 Synaptics FS4500 – Under the Glass (slide p.21)**

- Capacitivo sotto vetro/ceramica fino a 300 µm
    
- Permette design full-screen senza pulsanti dedicati

![](imgs/Pasted%20image%2020260216135953.png)

---

#### **10.5 SentryPoint e Match-in-Sensor (slide p.22–23)**

**SentryPoint:**

- Criptazione AES-256
    
- TLS
    
- Anti-spoof
    
- “Match-in-sensor”: il confronto NON avviene nel telefono, ma **direttamente nel sensore** → enorme sicurezza.

![](imgs/Pasted%20image%2020260216140015.png)

**FS7600:**

- Database _off-the-grid_, non accessibile dal sistema operativo

![](imgs/Pasted%20image%2020260216140031.png)

---

### **10.6 Clear ID FS9500 (slide p.24)**

Sensore **ottico sotto schermo** (OLED):

- Range di misura 1,5 mm (molto profondo)
    
- Funziona sotto vetro + pellicola
    
- Anti-spoof AI-based (PurePrint)

![](imgs/Pasted%20image%2020260216140054.png)

---

### **11. Comparazione dei sensori**

La tabella in p.25 mostra:

![](imgs/Pasted%20image%2020260216140122.png)

- tecnologia (FTIR, capacitivo, piezo, ecc.)
    
- dpi
    
- area
    
- pixel
    

Slide p.26: **lo stesso dito** acquisito su 8 sensori diversi → risultati completamente differenti.

![](imgs/Pasted%20image%2020260216140218.png)

---

### **12. Sistemi commerciali di fascia alta (slide p.28–30)**

#### **IDEMIA SIGMA**

- Sensore 23×23 mm
    
- Fake detection elettrica
    
- 250.000 utenti registrati
    
- IP65, PoE, RFID integrato

![](imgs/Pasted%20image%2020260216140328.png)

#### **Suprema (algoritmi)**

- EER 0.23% @ FAR 0.01 (2008)
    
- Oggi i migliori: **<0.06% EER**

![](imgs/Pasted%20image%2020260216140348.png)

#### **Suprema W2 (slide p.30)**

- Quad-core 1.2 GHz
    
- Anti-spoof avanzato a doppia luce (IR + visibile)
    
- Housing IP67

![](imgs/Pasted%20image%2020260216140410.png)

![](imgs/Pasted%20image%2020260216140510.png)

---

## **PARTE B – Rappresentazione, Compressione e Non-Unicità**

---

### **13. Livelli di rappresentazione dell’impronta**

Tre livelli gerarchici:

1. **Livello I – Globale**  
    macro-pattern (loop, whorl, arch)
    
2. **Livello II – Locale**  
    **minuzie**: biforcazioni, terminazioni, coordinate, orientamento  
    → è lo standard usato nei sistemi biometrici
    
3. **Livello III – Ultrafine**  
    pori, forma dettagliata della cresta, incipient ridges  
    → visibili solo con sensori ad altissima risoluzione
    

La scelta dipende dal sensore.

---

### **14. Immagini e risoluzione**

- FBI digitalizza a **500 dpi**, 8 bit/pixel
    
- 10 impronte = circa **10 MB**
    
- L’immagine è un _sample_ della vera impronta → contiene rumore, distorsioni, artefatti.

![](imgs/Pasted%20image%2020260216140632.png)

---

### **15. Compressione (slide p.36)**

Con formati lossless:

- fattore 2:1 → troppo poco.
    

Soluzione adottata:

**WSQ – Wavelet Scalar Quantization**

Lo standard FBI/NIST per la compressione delle impronte.

A parità di qualità (Q = 12.9), WSQ è molto più efficiente del JPEG.

![](imgs/Pasted%20image%2020260216140618.png)

---

### **16. Formati di interscambio – ISO/IEC 19794 (slide p.37)**

Regola come si scambiano i dati biometrici tra istituzioni.

Parti più rilevanti:

- **Part 2** – Finger Minutiae
    
- **Part 4** – Finger Image
    
- **Part 3** – Spectral
    
- **Part 8** – Skeletal
    

Standard fondamentale per interoperabilità tra sensori, forze dell’ordine, dispositivi.

---

### **17. Unicità delle impronte – Approccio statistico**

#### **17.1 Il problema**

Data un’impronta con **n** minuzie, qual è la probabilità che un’altra impronta abbia **q** minuzie in comune?

![](imgs/Pasted%20image%2020260216140804.png)

La formula deriva da:

- area di overlap M = A/C
    
- combinazioni possibili di m e n minuzie

![](imgs/Pasted%20image%2020260216140816.png)

Tratto dal paper fondamentale:  
Pankanti, Prabhakar, Jain (2002).

---

#### **17.2 Risultati pratici (slide p.40)**

Con un sensore a 500 dpi:

- si vedono in media **12 minuzie** su una impronta parziale.
    
- Il valore dominante è **q**, il numero di minutiae in comune:
    
    - già 12 coincidenze → probabilità astronomicamente piccola.

![](imgs/Pasted%20image%2020260216140902.png)

Comparazione finale:

- atomi nell’universo = 10⁸²
    
- stelle = 3×10²³
    

Le probabilità di collisione sono molto più basse.

![](imgs/Pasted%20image%2020260216140958.png)

---

### **18. Sintesi finale della lezione**

#### **Parte A**

Abbiamo visto:

- due modalità di acquisizione (offline e live-scan)
    
- tipi di sensori (ottici, stato solido, ultrasuoni)
    
- caratteristiche tecniche chiave
    
- problemi di acquisizione (pressione, roto-traslazione)
    
- panorama dei sensori commerciali, anche avanzati
    

#### **Parte B**

Abbiamo visto:

- livelli di rappresentazione dell’impronta
    
- compressione WSQ
    
- standard ISO/IEC 19794
    
- stima di unicità delle impronte digitali