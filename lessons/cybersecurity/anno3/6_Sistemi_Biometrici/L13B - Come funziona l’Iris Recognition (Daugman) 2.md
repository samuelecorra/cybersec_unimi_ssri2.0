# **Lezione 13B: Come funziona l’Iris Recognition (Daugman)**

---

### **1. Principio fondamentale del riconoscimento dell’iride**

Il riconoscimento dell’iride si basa su un principio estremamente elegante dal punto di vista matematico:

> **il riconoscimento avviene verificando il fallimento di un test di indipendenza statistica.**

Due iridi diverse sono statisticamente indipendenti, mentre due immagini della stessa iride non lo sono.

Questo consente di ottenere:

- livelli di confidenza estremamente elevati
    
- capacità di ricerca in database enormi
    
- assenza pratica di falsi match anche con milioni di confronti

L’informazione discriminante contenuta nell’iride ha circa:

$$

249 \text{ gradi di libertà}

$$

che corrispondono a una densità informativa di circa:

$$

3.2 \ \text{bit/mm}^2

$$

sulla superficie dell’iride.

---

### **2. Perché l’iride è una biometria ideale**

L’iride possiede proprietà molto favorevoli per il riconoscimento:

1. È altamente variabile tra individui.
    
2. È stabile nel tempo.
    
3. È protetta dall’ambiente (organo interno ma visibile).
    
4. È facilmente localizzabile grazie alla forma anulare.
    
5. Le deformazioni dovute alla pupilla sono modellabili matematicamente.
    
6. Le variazioni di illuminazione producono trasformazioni semplici.

L’iride si forma durante la gestazione e la sua struttura fine è completata prima della nascita, con variazioni epigenetiche casuali che generano unicità individuale.

---

### **3. Acquisizione e requisiti di imaging**

Per catturare dettagli utili:

- raggio dell’iride ≥ 70 pixel
    
- tipicamente 80–130 pixel nei sistemi reali

Le immagini vengono acquisite con illuminazione **near-infrared (700–900 nm)** perché:

- è invisibile all’uomo
    
- riduce riflessi
    
- rivela texture anche in iridi scure

---

### **4. Valutazione del fuoco in tempo reale**

Il sistema deve valutare rapidamente se un’immagine è a fuoco.

Il principio fisico è:

> la sfocatura attenua le alte frequenze spaziali.

Formalmente, se l’immagine perfettamente a fuoco ha trasformata di Fourier:

$$

F_0(u,v)

$$

l’immagine defocalizzata diventa:

$$

F(u,v) = F_0(u,v) \cdot e^{-c(u^2 + v^2)}

$$

quindi:

- alte frequenze → attenuate
    
- basse frequenze → quasi invarianti

Il focus score viene stimato misurando la potenza spettrale nelle alte frequenze tramite filtri convoluzionali.

---

### **5. Localizzazione dell’iride e della pupilla**

La posizione viene stimata tramite un operatore integrodifferenziale:

$$

\max_{r, x_0, y_0}

\left|

G_{\sigma}(r)

*

\frac{\partial}{\partial r}

\oint

\frac{I(x,y)}{2\pi r} ds

\right|

$$

che agisce come **edge detector circolare**.

Si stimano separatamente:

- centro pupilla
    
- centro iride
    
- raggi

poiché non sono concentrici.

---

### **6. Codifica delle caratteristiche tramite wavelet di Gabor**

La texture dell’iride viene demodulata usando wavelet di Gabor bidimensionali:

$$

\int I(\rho,\theta)

e^{-i\omega(\theta-\theta_0)}

e^{-\frac{(\rho-\rho_0)^2}{\alpha^2}}

e^{-\frac{(\theta-\theta_0)^2}{\beta^2}}

\rho \cdot d\rho \cdot d\theta

$$

Il risultato è un coefficiente complesso:

$$

z = a + ib

$$

La fase viene quantizzata in quattro quadranti → **2 bit per campione**.

Totale tipico:

$$

2048 \text{ bit}

$$

più una maschera di validità.

---

### **7. Perché si usa la fase e non l’ampiezza**

La fase è preferita perché:

- indipendente dal contrasto
    
- robusta a variazioni di illuminazione
    
- stabile anche con immagini rumorose

Anche immagini sfocate producono bit casuali ma non correlati, evitando falsi match.

---

### **8. Confronto tra iridi: distanza di Hamming**

Il confronto usa operazioni booleane:

- XOR → differenze
    
- AND → maschere valide

La distanza è:

$$

HD =

\frac{

| (code_A \oplus code_B) \land mask_A \land mask_B |

}{

| mask_A \land mask_B |

}

$$

Interpretazione:

- $HD = 0$ → match perfetto
    
- $HD \approx 0.5$ → indipendenza statistica

---

### **9. Distribuzione statistica tra iridi diverse**

I confronti tra iridi diverse seguono una distribuzione binomiale:

$$

p = 0.5, \quad N \approx 249

$$

cioè equivalente a:

> 249 lanci di moneta equa.

Questo implica probabilità estremamente basse di collisione.

Esempi:

- HD ≤ 0.33 → circa 1 su 16 milioni
    
- HD ≤ 0.30 → circa 1 su 10 miliardi

---

### **10. Indipendenza genetica delle iridi**

Esperimenti mostrano che:

- occhio destro e sinistro della stessa persona sono indipendenti
    
- anche gemelli identici hanno pattern indipendenti

Conclusione:

> la texture dell’iride è principalmente epigenetica.

---

### **11. Invarianza rispetto a dimensione, posizione e rotazione**

Il sistema usa il modello della **gomma elastica (rubber sheet)**.

Coordinate polari normalizzate:

$$

I(x(r,\theta), y(r,\theta)) \rightarrow I(r,\theta)

$$

con:

$$

x(r,\theta) = (1-r)x_p(\theta) + r x_s(\theta)

$$

$$

y(r,\theta) = (1-r)y_p(\theta) + r y_s(\theta)

$$

dove:

- $p$ = bordo pupilla
    
- $s$ = bordo iride  

Questo garantisce invarianza a:

- scala
    
- traslazione
    
- dilatazione pupillare

---

### **12. Invarianza alla rotazione**

La rotazione viene gestita tramite **shift ciclico** del template.

Se si testano $n$ rotazioni:

$$

F_n(x) = 1 - (1 - F_0(x))^n

$$

cioè la probabilità di falso match aumenta secondo teoria dei valori estremi.

---

### **13. Probabilità di falso match e database grandi**

Per un database di dimensione $N$:

$$

P_N = 1 - (1 - P_1)^N

$$

Questo mostra che:

> l’identificazione è molto più difficile della verifica.

L’iride funziona bene perché la coda della distribuzione binomiale decade velocemente.

---

### **14. Ambiente decisionale (decision environment)**

Esistono due distribuzioni:

1. **Same iris**
    
2. **Different iris**

La separazione tra le distribuzioni determina l’accuratezza.

Indice di separabilità:

$$

d’ =

\frac{|\mu_1 - \mu_2|}

{\sqrt{\frac{\sigma_1^2 + \sigma_2^2}{2}}}

$$

Valori tipici:

- condizioni non ideali: $d’ \approx 7.3$
    
- condizioni ideali: $d’ \approx 14.1$

---

### **15. Stabilità nel tempo**

Non esiste evidenza scientifica che l’iride cambi con:

- salute
    
- personalità
    
- organi interni

(iridologia = pseudoscienza).

Il sistema tollera fino a circa:

$$

30\%

$$

di dati corrotti senza perdita significativa di accuratezza.

---

### **16. Prestazioni computazionali**

Grazie alle operazioni booleane:

- circa **100.000 confronti al secondo** su CPU 300 MHz
    
- oltre **1 milione** su server moderno

Il sistema è facilmente parallelizzabile per database nazionali.

---

### **17. Valutazione del fuoco tramite convoluzione veloce**

Il focus score si basa su un kernel 8×8 equivalente alla differenza di due box:

Trasformata:

$$K(\mu,\nu) = \frac{\sin(\mu)\sin(\nu)}{\pi^2 \mu \nu} -
\frac{\sin(2\mu)\sin(2\nu)}{4\pi^2 \mu \nu}

$$

La potenza spettrale seleziona una banda di frequenze alte.

Score normalizzato:

$$

f(x) = 100 \cdot \frac{x^2}{x^2 + c^2}

$$

Tempo tipico:

$$

15 \text{ ms}

$$

per immagine 480×640.

---

### **18. Principio finale del riconoscimento dell’iride**

Il cuore teorico è:

> Il riconoscimento dell’iride è possibile perché due iridi diverse sono statisticamente indipendenti, mentre due immagini della stessa iride non lo sono.

Quindi:

- match = fallimento dell’indipendenza
    
- non-match = indipendenza

---

### **19. Sintesi concettuale (da esame)**

Pipeline completa:

1. Acquisizione NIR.
    
2. Localizzazione pupilla/iride.
    
3. Normalizzazione rubber sheet.
    
4. Filtri di Gabor.
    
5. IrisCode binario.
    
6. Confronto con distanza di Hamming.
    
7. Decisione statistica.

Elemento chiave:

$$

249 \text{ bit indipendenti}

$$

→ probabilità di collisione astronomicamente bassa.

---

Di seguito allego il paper originale per i curiosi e per completezza...

![](imgs/Lezione13b_paper_Daugman.pdf)