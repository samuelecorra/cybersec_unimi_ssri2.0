
# **Lezione 9 – Prefiltraggio, Enhancement e Feature Extraction nelle impronte digitali**

---

### **1. Introduzione generale**

Questa lezione entra nel cuore della pipeline del fingerprint recognition: come trasformiamo un’immagine grezza di impronta in un **template strutturato**.  
L’obiettivo è migliorare l’immagine, pulire il rumore, estrarre le strutture fondamentali (creste, minuzie, punti singolari, pori).

La pipeline completa contiene quattro moduli consecutivi:

1. Filtraggio iniziale
    
2. Enhancement (manipolazione dell’immagine)
    
3. Feature extraction
    
4. Codifica
    

---

## **PARTE A – Prefiltraggio e Enhancement**

---

![](imgs/Pasted%20image%2020260216142323.png)

![](imgs/Pasted%20image%2020260216142436.png)

### **2. Perché servono i filtraggi nelle impronte digitali**

Le impronte digitali sono immagini altamente strutturate ma soggette a:

- rumore (pressione, sudore, secchezza, sensori diversi)
    
- non uniformità locale
    
- variazioni di contrasto
    
- distorsioni locali
    

Il filtraggio iniziale ha due scopi:

- migliorare visivamente i ridge
    
- rendere più robusta e semplice l’estrazione delle feature
    

---

### **3. Tipologie di filtri**

Dal PDF della lezione (p.4) emergono tre famiglie fondamentali:

#### **Filtri di trasformazione (pointwise / globali)**

Operano direttamente sui valori di intensità dell’immagine:

- normalizzazione
    
- equalizzazione
    
- gamma correction
    
- contrast stretching
    

Sono globali o locali: agiscono sui pixel senza considerare la vicinanza, ma usando statistiche locali (media, varianza).

#### **Filtri convolutivi semplici**

Usano kernel fissi (Sobel, Gauss, media filtrante).  
Funzioni:

- riduzione rumore
    
- enfatizzazione creste
    
- calcolo gradiente
    

#### **Filtri adattativi avanzati**

Sono il livello più evoluto: si adattano alla geometria dell’impronta.  
Esempi:

- Wiener filtering
    
- Filtro O’Gorman–Nickerson
    
- Filtri di Gabor (banco di orientamenti e frequenze)
    

---

### **4. Filtri di trasformazione: concetti chiave**

Il PDF (p.5) definisce la trasformazione come:

$$
O(x,y) = F(I(x,y); \theta, \Pi(I))  
$$

Dove:

- I = immagine originale
    
- O = immagine filtrata
    
- F = funzione di trasformazione
    
- θ = parametri (es. μ₀, σ₀)
    
- Π(I) = statistiche globali o locali (media, varianza, percentili, CDF)
    

#### Due esempi fondamentali

**Normalizzazione:**  
$$
F(I) = \frac{I - \mu_I}{\sigma_I}\sigma_0 + \mu_0  
$$

**Correzione gamma:**  
$$
F(I)= I^\gamma  
$$

---

### **5. Filtraggio = Convoluzione**

La slide p.6 illustra la classica operazione:

$$
O(x,y) = I(x,y) * k(x,y)  
$$

La convoluzione sostituisce ogni pixel con una combinazione pesata del suo intorno.

È la base di:

- sobel
    
- emboss
    
- sharpening
    
- gaussian blur

![](imgs/Pasted%20image%2020260216142712.png)

![](imgs/Pasted%20image%2020260216142745.png)

---

### **6. Filtraggi iniziali: cosa si applica davvero**

Il PDF (p.8) elenca:

- contrast stretching
    
- histogram manipulation
    
- normalization
    
- Wiener filtering

![](imgs/Pasted%20image%2020260216142814.png)

#### **Contrast stretching** (p.9)

Le impronte hanno gamma di grigi ridotta → stretching = espansione lineare dei valori per aumentare visibilità.

![](imgs/Pasted%20image%2020260216142828.png)

#### **Manipolazione dell’istogramma** (p.10)

Funzioni logaritmiche o esponenziali permettono di enfatizzare zone poco contrastate.

![](imgs/Pasted%20image%2020260216142844.png)

#### **Wiener filtering** (p.11)

Si comporta come un **passa-banda ottimizzato**:

- conoscere la distanza media inter-ridge
    
- assumere rumore gaussiano additivo
    
- filtrare frequenze che non appartengono alla struttura dell’impronta

![](imgs/Pasted%20image%2020260218131517.png)

---

### **7. Normalizzazione**

Obiettivo: rendere i ridge **statisticamente uniformi** su tutta l’immagine.

![](imgs/Pasted%20image%2020260218131819.png)

Non è equalizzazione dell’istogramma!  
Lavora anche localmente (blocchi) ed è regolata da media e varianza desiderate.

---

### **8. Segmentazione**

Serve a separare:

- foreground = impronta
    
- background = sfondo    

![](imgs/Pasted%20image%2020260218131859.png)

Metodo base:

1. Dividere l’immagine in blocchi 16×16
    
2. Calcolare varianza locale (o gradiente)
    
3. Eliminare i blocchi sotto soglia → niente ridge → sfondo
    

Risultato: maschera che delimita l’impronta.

---

### **9. Regioni con qualità diversa**

Punti critici:

- pressione irregolare
    
- traslazioni
    
- ferite, tagli, abrasioni
    
- errori off-line
    
- latent fingerprints
    

Classificazione:

a) well-defined  
b) recoverable  
c) unrecoverable

![](imgs/Pasted%20image%2020260218131928.png)

Queste categorie guidano quali filtri attivare/disattivare.

---

### **10. Enhancement – Filtri contestuali**

![](imgs/Pasted%20image%2020260218132355.png)

Gli enhancement adattativi o contestuali lavorano sulla immagine in ingresso attraverso un’operazione chiamata convoluzione con una maschera di filtraggio:

- considerano distanza tra ridge
    
- considerano orientamento locale
    
- evitano di amplificare rumore
    
- adattano la maschera di filtraggio in ogni regione

![](imgs/Pasted%20image%2020260218132722.png)

#### **Filtro O’Gorman–Nickerson**

(p.18)

Maschera costruita per:

- “matchare” spessore dei ridge
    
- seguire orientamento locale
    
- attenuare rumore

![](imgs/Pasted%20image%2020260218132742.png)

#### **Filtri di Gabor (Hong, Wan, Jain)**

![](imgs/Pasted%20image%2020260218133128.png)

Procedura:

1. creare un **banco di filtri** per varie direzioni/frequenze
    
2. in ogni blocco scegliere la maschera più simile alla struttura locale
    
3. convolverla solo su quel blocco

![](imgs/Pasted%20image%2020260218133201.png)

Effetti collaterali:

- artefatti (B) nelle regioni recoverable
    
- blocchettizzazione (C)
    

Zone unrecoverable → filtro disattivato per evitare creste false.

---

## **PARTE B – Feature Extraction**

---

Siamo giunti dunque al punto dopo la freccia 3:

![](imgs/Pasted%20image%2020260218133456.png)

### **11. Livelli di analisi**

Come da p.24:

- **Livello I:** orientamenti, ridge count, core, delta
    
- **Livello II:** minutiae (terminazioni, biforcazioni)
    
- **Livello III:** pori, cicatrici, micro-dettagli

![](imgs/Pasted%20image%2020260218133534.png)

---

### **12. Ridge Counting**

Misura quanti ridge separano due minutiae.

Esempio:

- tra a e b = 4 ridge
    
- tra b e c = 0 ridge
    
- tra c e a = 3 ridge

![](imgs/Pasted%20image%2020260218133557.png)

È livello I perché riguarda regioni estese, non un punto.

---

### **13. Ridge Frequency**

Misura quanto sono distanziati i ridge.

Procedura:

1. prendere un profilo di intensità perpendicolare ai ridge
    
2. usare interpolazione sinusoidale
    
3. trovare picchi consecutivi
    
4. la frequenza = 1 / distanza media
    
![](imgs/Pasted%20image%2020260218133714.png)

Usata nei filtri di Gabor.

---

### **14. Mappa delle frequenze**

![](imgs/Pasted%20image%2020260218133928.png)

Calcolata blocco per blocco.  
Serve a capire:

- regioni coerenti
    
- regioni troppo rumorose
    
- dove filtrare in modo più conservativo
    

---

### **15. Orientamento dei ridge**

È uno degli step più complessi.

Procedura:

1. dividere immagine in blocchi W×W
    
2. calcolare gradiente Gx, Gy (Sobel o Marr–Hildreth)
    
3. calcolare direzione media ottimizzata tramite le formule:

![](imgs/Pasted%20image%2020260218134050.png)
    
4. correggere salti di fase (5° ↔ 175°)
    
5. filtrare con passa-basso vicino a core/delta
    

La mappa degli orientamenti è fondamentale per:

- enhancement
    
- estrazione minutiae
    
- matching

![](imgs/Pasted%20image%2020260218134230.png)

---

### **16. Orientation Field Flow Curves (OFFC)**

Curve sintetiche:

![](imgs/Pasted%20image%2020260218134402.png)

- tangenti al campo di orientamento
    
- seguono l’andamento delle creste
    
- identificano automaticamente core e delta
    
- permettono classificazioni loop/whorl/arch attraverso mappe isometriche

![](imgs/Pasted%20image%2020260218134416.png)

Il grafico di seguito mostra come curve diverse generano pattern distinti usati per classificazione:

![](imgs/Pasted%20image%2020260218134444.png)

---

### **17. Classificazione delle impronte**

![](imgs/Pasted%20image%2020260218135703.png)

Usando:

- Core/Delta
    
- OFFC
    

Si ottiene errore:

- tra 3%–5%
    

Osservazioni chiave:

1. meno classi → più semplice classificazione → errore cala
    
2. meno classi → più campioni da esaminare nel database
    

---

### **18. Core Detection – Metodo delle Normali**

Procedura:

1. seguire un ridge
    
2. calcolare normali a quel ridge
    
3. molte intersezioni in un intorno → presenza di core

![](imgs/Pasted%20image%2020260218134803.png)

---

### **19. Core/Delta Detection – Metodo di Poincaré**

Si calcola l’indice topologico:

- **Whorl** → P = +1
    
- **Loop** → P = +1/2
    
- **Delta** → P = −1/2

![](imgs/Pasted%20image%2020260218135747.png)

![](imgs/Pasted%20image%2020260218135805.png)

Il valore deriva dalla somma delle variazioni angolari lungo una curva chiusa.

Problema pratico: un blocco può produrre più celle con P = 1/2 → si prende il baricentro.

![](imgs/Pasted%20image%2020260218135150.png)

---

### **20. Minutiae Extraction – Pipeline classica**

![](imgs/Pasted%20image%2020260218135222.png)

![](imgs/Pasted%20image%2020260218135250.png)

A questo punto siamo davanti a un bivio: la tecnica classica è quella che sceglie il ramo sinistro:
1. Enhancement
2. Binarization
3. Thinning

![](imgs/Pasted%20image%2020260218135908.png)

#### Binarizzazione

Problema:  
il threshold globale cancella o unisce ridge.  

![](imgs/Pasted%20image%2020260218140025.png)

Soluzioni avanzate:

- FBI minutiae reader
    
- Adaptive Flow Orientation (Ratha et al.)

![](imgs/Pasted%20image%2020260218140053.png)

#### Thinning

Riduzione creste a spessore di 1 pixel.  

![](imgs/Pasted%20image%2020260218140125.png)

Importante: evitare fori e artefatti, ovvero:

![](imgs/Pasted%20image%2020260218140139.png)

---

### **21. Identificazione delle minuzie**

Guardare la matrice 3×3 intorno al pixel:

- 1 intersezione → terminazione
    
- 3 intersezioni → biforcazione
    
- 2 intersezioni → ridge passante
    
- > 3 → casi speciali da analizzare

![](imgs/Pasted%20image%2020260218140244.png)

![](imgs/Pasted%20image%2020260218140305.png)

---

### **22. Post-Processing**

![](imgs/Pasted%20image%2020260218140327.png)

Due categorie:

#### Structural post-processing

Applica **regole strutturali** allo scheletro per eliminare minutiae spurie.  
Ecco 8 regole molto usate.

![](imgs/Pasted%20image%2020260218140459.png)

![](imgs/Pasted%20image%2020260218140516.png)

#### Gray-scale post-processing

Analizza regioni della versione originale a toni di grigio:  
reti neurali e classificatori distinguono minuzie vere da artefatti.

![](imgs/Pasted%20image%2020260218140554.png)

---

### **23. Direct Gray Scale Extraction**

Approccio alternativo:

1. scegliere punti iniziali da una griglia
    
2. seguire i ridge sull’immagine originale
    
3. fermarsi a terminazioni/biforcazioni
    
4. usare etichette per non seguire lo stesso ridge due volte

![](imgs/Pasted%20image%2020260218140616.png)

Vantaggi: evita errori di binarizzazione e thinning.

---

### **24. Livello III – Estrazione dei pori**

Operazioni tipiche:

- segmentazione molto fine
    
- operatori morfologici
    
- distinzione pori aperti / chiusi
    
![](imgs/Pasted%20image%2020260218140735.png)

Richiede risoluzioni **altissime** (≥1000 dpi).

---

### **25. Esempio finale**

Tre livelli combinati:

![](imgs/Pasted%20image%2020260218140809.png)

- Livello I → mappa orientamenti + ridge
    
- Livello II → minutiae
    
- Livello III → pori
    

Mostra la ricchezza del template completo.

---

### **26. Sintesi finale della Lezione 9**

Hai visto:

- tecniche di prefiltraggio (stretching, istogrammi, normalizzazione, Wiener)
    
- filtri contestuali (O’Gorman–Nickerson, Gabor)
    
- segmentazione e analisi qualità
    
- extraction livello I (orientamenti, ridge count, core/delta)
    
- extraction livello II (minutiae)
    
    - binarizzazione
        
    - thinning
        
    - regole strutturali
        
    - gray-scale extraction
        
- extraction livello III (pori)
    
