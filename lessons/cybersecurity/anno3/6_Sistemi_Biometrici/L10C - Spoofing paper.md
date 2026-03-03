# **Lezione 10C – Spoofing dei sensori di impronte: attacchi cooperativi e non-cooperativi**

_(dal paper Carvalho–Tihanyi 2021)_

---

### **Introduzione**

I sistemi biometrici basati su impronte digitali promettono autenticazione “basata su chi sei”, ma sono vulnerabili ad attacchi diretti (_Type I attacks_ secondo la classificazione di Ratha).

Gli attacchi diretti presentati nel paper non modificano né software né canali di comunicazione, ma **ingannano fisicamente il sensore mostrando un’impronta finta**.

Gli autori ottengono:

- **96–100% di successo** contro sensori capacitivi
    
- **98–100% di successo** contro sensori ottici
    
- funzionano persino contro dispositivi certificati ad alta sicurezza (Lexar F35, Windows Hello, Suprema Biostation 2)
    

---

### **Biometria e superfici d’attacco**

Il paper introduce le tre classi di autenticazione:

- _what you know_ (password)
    
- _what you have_ (token, certificati)
    
- _who you are_ (biometria)
    

Poi riprende gli **otto punti d’attacco** di Ratha nel sistema biometrico (p.1–2):

1. **Sensor attack (Type I)** → presentazione di impronta falsa
    
2. Replay dell’immagine biometrica
    
3. Override dell’estrattore
    
4. Tampering della rappresentazione
    
5. Override del matcher
    
6. Attacco al canale fra template e matcher
    
7. Manipolazione del database dei template
    
8. Manipolazione della decisione finale
    

Il lavoro si concentra sul punto **1**: ingannare sensore ottico o capacitivo con un dito artificiale.

---

## Tipi di sensori rilevanti

Riassunti a p.2:

- **Ottici** → immagine visiva tramite fotocamera
    
- **Capacitivi** → misurano variazioni di carica (ridges scaricano più dei valleys)
    
- **Ultrasuoni** → pattern di eco
    
- **Termici** → variazione di temperatura su piroelettrico
    

Il paper attacca **ottici e capacitivi**, i due più diffusi su laptop e mobile.

---

## Metodologia: aumentare il FAR fino al 100%

Un attacco diretto punta a **massimizzare il FAR**, cioè far accettare all’algoritmo un impostore come genuino. Il punto cruciale è realizzare un _fake finger_ che sia:

- flessibile
    
- conduttivo/isolante quanto basta (per i capacitivi)
    
- con un rilievo creste–valli realistico
    
- dello spessore corretto
    
- stabile durante il contatto
    

Gli autori presentano due metodi:

- **cooperativo** → la vittima collabora (consapevolmente o meno)
    
- **non-cooperativo** → si usa un’impronta latente lasciata su una superficie
    

---

# **Metodo cooperativo** (p.3–4)

### Obiettivo

Creare una copia perfetta dell’impronta usando materiali economici (5 dollari).  
Il processo richiede circa **5–10 minuti**.

### Materiali

- **EVA** (Ethylene-Vinyl Acetate) = colla a caldo
    
- **PVA** (Polyvinyl Acetate) = “vinavil”, “white school glue”
    
- Lubrificante (vaselina)
    
- Pistola termica da 65W (o accendino)
    
- Carta, lampada, aria calda
    

### Procedura

1. **Riscaldare la colla EVA** (pistola HL-603, fig.3 p.3).
    
2. **Stendere la colla calda** su carta creando uno strato uniforme.
    
3. **Attendere 25–35 secondi** per evitare ustioni e deformazioni.
    
4. **Lubrificare il dito** (vaselina).
    
5. **Premere il dito nella colla** per 2–3 secondi, senza troppa pressione → si ottiene lo stampo perfetto (fig.4 p.3).
    
6. **Riempire lo stampo con PVA** (fig.5–6 p.3): massimo 1 mm di spessore.
    
7. **Asciugare con aria calda** per 3–5 minuti. Ripetere per tre strati.
    
8. **Sollevare delicatamente il cast** (fig.7 p.4).
    

### Risultato

Un **dito artificiale PVA** estremamente preciso nelle creste, elastico, realistico, che funziona sia su:

- sensori ottici
    
- sensori capacitivi
    

Con questo metodo:  
**96%–100% di successo**.

---

# **Metodo non-cooperativo** (p.4–5)

Questo è più complesso: si parte da **un’impronta latente** lasciata dalla vittima, come sullo schermo di un notebook (fig.8 p.4).

### Passaggi

1. **Dusting con grafite**
    
    - Si ricopre l’impronta con polvere di grafite (ricavata da matita o acquistata).
        
    - Brush morbido per non distruggere il pattern.
        
    - La grafite aderisce ai grassi dell’impronta → pattern visibile.
        
2. **Lifting con scotch** (fig.9 p.4)
    
    - Si appoggia il nastro adesivo, si preme leggermente e si solleva con movimento controllato.
        
    - Si applica il nastro su un foglio A4.
        
3. **Scansione a 1200 DPI** (fig.10 p.4)
    
    - Risoluzione minima consigliata: **1200 dpi**.
        
4. **Enhancement digitale** (fig.11 p.4)
    
    - Pulizia, inversione, riduzione rumore, background nero, impronta bianca.
        
    - Capovolgimento orizzontale se necessario.
        
5. **Stampa su acetato/transparency** (fig.12 p.5)
    
    - Laser 1200 DPI.
        
    - La dimensione deve essere identica all’impronta reale: si usa bounding box per allineare.
        
6. **Trasferimento su PCB fotosensibile**
    
    - Il transparency è posto su una PCB pre-fototrattata con strato rame.
        
    - Illuminazione UV per **130 secondi** (fig.13 p.5).
        
7. **Sviluppo** con Na₂SiO₃ (fig.14 p.5)
    
    - Appare il negativo dell’impronta.
        
8. **Incisione con Na₂S₂O₈** (fig.15–16 p.5)
    
    - Si rimuove il rame in eccesso, lasciando un rilievo perfetto delle creste.
        
9. **Creazione del cast PVA** (fig.17 p.5)
    
    - Si applicano 3 strati di colla PVA e si asciugano.
        
    - Si solleva il dito artificiale.
        

### Risultato

Metodo più lungo, ma completamente non-cooperativo.  
Funziona eccellentemente contro **sensori ottici** → **98% successo**.

Il limite: i capacitivi richiedono informazioni _3D_ (altezza creste), difficili da estrarre da un’impronta latente 2D.

---

# **Risultati sperimentali** (p.6)

### Success rate (tabella p.6)

|Dispositivo|Sensore|Successo|
|---|---|---|
|MacBook Pro 2017|capacitivo|**100%**|
|MacBook Air 2019|capacitivo|**100%**|
|ASUS Zenbook UX461F Z14|capacitivo|**96%**|
|Huawei P20|capacitivo|**100%**|
|Huawei P9|capacitivo|**100%**|
|iPhone 8 Plus|capacitivo|**100%**|
|Lexar Jumpdrive F35|capacitivo|**100%**|
|Suprema Biostation 2|ottico|**100%**|

### Osservazioni importanti

- La qualità del fake finger era ottima già al primo tentativo, **ma il successo iniziale era solo 10–17%** sul laptop Windows Hello (Zenbook).  
    → Il problema non era il cast, **ma l’angolo e la pressione**.
    
- Dopo **10–15 minuti di pratica**, con lo stesso identico cast, gli autori hanno ottenuto **96% di successo**.
    
- Molti dispositivi aggiornano il template con machine learning dopo ogni login, ma i test sono stati ripetuti con nuove registrazioni → **stesso successo**.  
    Quindi il miglioramento non era dovuto al “training del device”, ma alla manualità dell’attaccante.
    

### Nuova metodologia emersa dai risultati

1. Creare un cast di test
    
2. Addestrarsi sulla stessa tipologia di dispositivo
    
3. Solo dopo, attacco reale alla macchina target
    

Dimostra che lo spoofing dei sensori biometrici è **procedurale**, non solo tecnologico.

---

## Conclusioni (p.6)

- Gli autori hanno ottenuto **quasi 100% di successo** in tutte le prove.
    
- Il metodo cooperativo è banale e preciso.
    
- Il metodo non-cooperativo è per ora efficace solo su **ottici**; i capacitivi richiedono ancora la generazione del rilievo 3D.
    
- Futuro: comprendere come estrarre o ricostruire la **mappa di profondità** per attaccare anche i capacitivi in modo non-cooperativo.
    

---

Di seguito il paper di riferimento:

![](imgs/Lezione10b_Spoofing2021Fingerprint_Carvalho_Tihanyi.pdf)