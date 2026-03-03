# **Lezione 6B: Dispensa operativa sulla misura dei parametri dei sistemi biometrici**

### **1. Introduzione**

Questa “Lezione 6B” è una **dispensa estesa** che accompagna la Lezione 6 standard.  
La differenza rispetto alla precedente è che qui il docente:

- entra nei **dettagli matematici** delle stime;
    
- mostra **intervalli di confidenza**, test statistici, approssimazioni;
    
- discute **dataset**, campionamenti, e cosa significa realmente “stimare un errore”;
    
- tratta **fallacie comuni** e regole da laboratorio per misurare FMR/FNMR;
    
- analizza l’impatto della **dimensione del DB** e della **varianza stimata**;
    
- spiega come **pubblicare numeri credibili** su un sistema biometrico.
    

È la parte più “scientifica” del modulo sui parametri: da qui nasce la metodologia corretta per dire:

> “Il mio sistema ha FMR = 0.0001 al 95% di confidenza.”

Molti articoli pubblicati in letteratura **non** seguono queste regole—questa dispensa serve a formare l’ingegnere biometrico che non commette errori di valutazione.

---

### **2. Errori biometrici come esperimenti di Bernoulli**

La dispensa ribadisce che ogni tentativo di confronto (genuino o impostore) è un **esperimento Bernoulliano**:

- esito = 1 → errore (false match o false non-match),
    
- esito = 0 → corretto,
    
- probabilità di errore = $p$ (costante),
    
- prove indipendenti.
    

Il numero totale di errori dopo $n$ prove:

$$
S_n = X_1 + \dots + X_n  
$$

segue una **binomiale**:

$$
P(S_n = k) = \binom{n}{k} p^k (1-p)^{n-k}.  
$$

Questo è il modello matematico che permette:

- di stimare $p$ dai dati,
    
- di costruire intervalli di confidenza,
    
- di valutare quanto è affidabile la stima.
    

---

### **3. Stima puntuale del tasso di errore**

Quando esegui $N$ prove (ad esempio $N$ confronti impostori) e osservi:

- $K$ errori (False Match),
    

la **stima puntuale** del tasso di errore è:

$$
\hat{p} = \frac{K}{N}.  
$$

Ma la dispensa chiarisce che **la stima puntuale non basta**: devi sempre accompagnarla con un **intervallo di confidenza**.

---

### **4. Intervalli di confidenza per errori biometrici**

La dispensa mostra un concetto fondamentale:

> Non basta dire “FMR = 0.1%”: devi dire  
> **FMR = 0.1% ± qualcosa**  
> e devi specificare **con quale livello di confidenza** (es. 90%, 95%).

#### **4.1 Intervallo classico di Clopper-Pearson**

Dato $K$ errori su $N$ prove, l’intervallo di confidenza esatto (Clopper-Pearson) è basato sulla distribuzione binomiale.

La dispensa ne discute l’uso perché per errori **molto piccoli** (cosa tipica per FMR) questo intervallo diventa **asimmetrico** e spesso molto ampio.

---

### **5. La regola dei 3 — analisi approfondita**

La dispensa riprende quanto visto nella Lezione 6, ma lo formula in modo operativo e rigoroso:

#### **5.1 Caso K = 0 (nessun errore osservato)**

In molti esperimenti biometrici capita di osservare **zero errori impostori**, perché i sistemi moderni sono molto accurati.

Allora:

$$
\hat{p} = 0, 
$$
ma ovviamente **p non può essere zero**.

#### **5.2 Intervallo a 95% di confidenza dopo 0 errori**

Il limite superiore dell’intervallo è:

$$
p < \frac{3}{N}  
$$

dove il “3” deriva dall’approssimazione:

$$
(1 - p)^N \approx e^{-Np}.  
$$

Interpretazione:  
se fai **N tentativi impostori** e vedi **0 false match**,

> il sistema ha un errore reale inferiore a circa (3/N).

#### **5.3 Esempi pratici dalla dispensa**

- Con **1000** tentativi impostori e 0 errori:  
    $p < 3/1000 = 0.003 = 0.3\%$  
    → NON puoi affermare che FMR sia “zero” o “0.01%”.
    
- Con **100.000** confronti impostori e 0 errori:  
    $p < 3/100000 = 3 \times 10^{-5}$  
    → ora puoi sostenere FMR < 0.00003.
    

---

### **6. Regola dei 30 — analisi approfondita**

La dispensa formalizza la regola così:

> Per stimare un tasso di errore con un’incertezza relativa ±30% al 90% di confidenza, occorrono almeno **30 errori osservati**.

La motivazione è statistica:

- approssimazione normale della binomiale →  
    la deviazione standard della stima è:  
    $$
    \sigma_{\hat{p}} = \sqrt{\frac{p(1-p)}{N}} \approx \sqrt{\frac{p}{N}}  
    $$ 
    per $p$ piccolo.
    
- Per avere errore relativo del 30%, serve:  
    $$
    \frac{\sigma_{\hat{p}}}{\hat{p}} \approx 0.3  
    $$ 
    che porta appunto a $K \approx 30$.
    

#### **6.1 Implicazione pratica**

Se un sistema biometrico ha FMR ≈ $10^{-4}$ e vuoi stimarlo con precisione:

- per ottenere **30 false match**,
    
- servono circa **300.000 confronti impostori**.
    

Questo è il motivo per cui dataset seri (NIST, FVC, LFW, ecc.) contengono:

- **milioni** di confronti impostori.
    

---

### **7. Il problema del campionamento non indipendente**

La dispensa sottolinea un punto **fondamentale** che molti studenti ignorano:

> Le prove non sono sempre indipendenti.

Esempi:

- due confronti impostori che coinvolgono lo **stesso individuo** possono essere correlati;
    
- acquisizioni fatte nello **stesso ambiente** (luce, temperatura, stress) sono correlate;
    
- template generati da algoritmi preprocessati in batch possono condividere errori sistematici.
    

Questo riduce la validità delle stime **binomiali** perché la binomiale assume **indipendenza**.

#### **Conseguenza**

Gli intervalli reali sono spesso **più larghi** di quelli che calcoliamo assumendo indipendenza.

---

### **8. Dataset, numero di persone e numero di impressioni**

La dispensa insiste su un punto operativo:

> Non basta avere “tante immagini”, serve avere **tante persone diverse**.

Perché?

- I confronti impostori crescono come **quadratico** nel numero di utenti:  
    $$
    N_{\text{impostori}} \approx \frac{n(n-1)}{2}  
    $$
    
- I confronti genuini crescono solo **linearmente** nel numero di campioni per utente.
    

Esempio:

- 10 utenti, 10 immagini ciascuno:
    
    - genuini ≈ 90;
        
    - impostori ≈ 45.
        
- 100 utenti, 10 immagini ciascuno:
    
    - genuini ≈ 900;
        
    - impostori ≈ 4950.
        

Importante:  
più cresce il numero di utenti, più puoi stimare correttamente i tassi di errore impostori.

---

### **9. Effetto della soglia e spostamento delle distribuzioni**

La dispensa dedica varie pagine a mostrare (con grafici alle pp. 17–21):

- come gli istogrammi di **genuine** e **impostor score** si sovrappongono;
    
- come la soglia sposta la regione di accettazione;
    
- come piccoli cambiamenti nella soglia cambiano **drasticamente** FMR e FNMR.
    

#### **Messaggio chiave**

> In un sistema biometrico, la soglia è _tutto_.  
> La stessa tecnologia può avere prestazioni pessime o eccellenti  
> **a seconda della soglia scelta**.

Questo giustifica anche le curve DET e ROC introdotte nella lezione precedente.

---

### **10. Disuguaglianza di Markov e limite superiore dell’errore**

La dispensa introduce anche la **disuguaglianza di Markov** come strumento grezzo per ottenere un limite superiore:

$$
P(S_n \ge 1) \le E[S_n] = np.  
$$

Interpretazione:

- la probabilità di almeno un errore in $n$ prove è al massimo $np$.
    

Applicazione biometrica:

- se FMR = $10^{-5}$ e nel DB hai $n=50.000$ persone,  
    la probabilità di falso match in identificazione 1:N è:  
    $$
    \le 50.000 \times 10^{-5} = 0.5.  
    $$
    

Ovviamente Markov è un limite **loose** (pessimista), ma utile come upper bound.

---

### **11. Identificazione 1:N e crescita dell’errore**

La dispensa riprende e giustifica più formalmente la formula già vista:

$$
\text{FMR}_N = 1 - (1 - \text{FMR})^N.  
$$

Per piccoli $p$:

$$
\text{FMR}_N \approx Np.  
$$

La dispensa sottolinea:

- questa approssimazione è valida **solo se gli score sono indipendenti**,
    
- nella pratica non lo sono, quindi è un **worst-case**.
    

---

### **12. Scegliere N (dimensione del test) per stimare un errore target**

La dispensa include vari grafici (pp. 23–26).

Il punto essenziale:

> Se vuoi stimare un tasso di errore dell’ordine di $10^{-4}$,  
> servono **almeno centinaia di migliaia di confronti**.

E se vuoi stimare errori dell’ordine di $10^{-6}$ (tipici sistemi governativi di frontiera):

- servono **milioni** di tentativi impostori.
    
- e dataset con **molte migliaia di utenti**.
    

Non esiste scorciatoia matematica.

---

### **13. Errori sistematici vs errori statistici**

La dispensa distingue fra:

#### **13.1 Errori statistici**

- dovuti alla natura random del processo;
    
- gestibili con modelli binomiali e intervalli di confidenza.
    

#### **13.2 Errori sistematici**

- dovuti a:
    
    - cattiva calibrazione del sensore,
        
    - bias nel dataset,
        
    - preprocessing scorretto,
        
    - condizioni ambientali particolari,
        
    - non indipendenza delle prove.
        

Questi **non** vengono catturati dalla binomiale.

Come dice il docente nella dispensa:

> “Errori sistematici = morte dell’esperimento biometrico”.

---

### **14. La filosofia del testing biometrico**

La dispensa chiude con una serie di principi operativi:

#### **14.1 Per stimare un parametro devi:**

- avere un dataset **grande**,
    
- avere utenti diversi,
    
- avere immagini acquisite **in momenti diversi**,
    
- controllare l’indipendenza,
    
- separare i test genuini e impostori,
    
- etichettare bene ogni match,
    
- generare curve DET/ROC complete,
    
- riportare intervalli di confidenza,
    
- indicare la **soglia** alla quale vengono dichiarati i risultati.
    

#### **14.2 Per pubblicare risultati seri devi:**

- indicare **numero totale di confronti impostori**,
    
- indicare **numero totale di confronti genuini**,
    
- riportare **dataset utilizzato**,
    
- indicare eventuali **distorsioni ambientali**,
    
- indicare strumenti statistici usati (binomiale, Clopper-Pearson, bootstrap, ecc.).
    

---

### **15. Sintesi finale della Lezione 6B**

Questa dispensa ti dà:

- il **modello statistico ufficiale** per quantificare gli errori;
    
- gli **strumenti per stimare correttamente FMR/FNMR**;
    
- le regole per capire quante prove ti servono (3 & 30);
    
- la differenza fra **errore osservato** e **errore reale**;
    
- i limiti della binomiale per prove non indipendenti;
    
- il comportamento dell’errore in identificazione 1:N;
    
- le best-practice operative per test biometrici seri.
    

È la parte più “metodologica” del corso: tutto ciò che farai nelle lezioni successive (curve, test, soglie, dataset, identificazione) si basa su queste regole.