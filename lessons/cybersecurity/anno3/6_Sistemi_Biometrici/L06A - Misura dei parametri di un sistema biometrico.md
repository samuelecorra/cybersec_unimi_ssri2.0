# **Lezione 6: Misura dei parametri di un sistema biometrico**

---

### **0. Obiettivo della lezione**

In questa lezione impariamo a **misurare quanto “sbaglia” un sistema biometrico**, cioè:

- come si definiscono **genuini** e **impostori**;
    
- come funziona la **decisione con soglia** in verifica e identificazione;
    
- cosa sono e come si calcolano:
    
    - **FMR(T)** (False Match Rate),
        
    - **FNMR(T)** (False Non-Match Rate),
        
    - **FAR/FNAR**,
        
- come si leggono le curve:
    
    - **DET** (Decision Error Tradeoff),
        
    - **ROC** (Receiver Operating Characteristic),
        
    - **CMC** (Cumulative Match Characteristic);
        
- come si caratterizzano gli utenti (zoo di Doddington);
    
- come modellare statisticamente il sistema con **prove di Bernoulli**;
    
- che cosa sono la **regola dei 3**, la **regola dei 30** e la relazione fra errori in **verification** e **identification**.
    

---

### **1. “Quanto sbaglia?”: perché un solo numero non basta**

Nella biometria non ha senso chiedere:

> “Qual è il _tasso di errore_ del sistema?”

Perché:

- il sistema ha **due tipi diversi di errore** (accetta impostori / rifiuta genuini),
    
- entrambi dipendono dalla **soglia $T$** scelta,
    
- per descrivere le prestazioni servono **curve intere**, non un solo valore.
    

Quindi non esiste “il sistema A ha errore 1%, il sistema B 0.5% → B è meglio”.  
Devi sempre chiederti: _“A che soglia?”_ e _“Con quale trade-off fra i due tipi di errore?”_.

---

### **2. Match score e soglia**

#### **2.1 Schema di decisione**

Il sistema biometrico:

- acquisisce il tratto e ne estrae il template $X_Q$ (query);
    
- recupera dal DB il template di riferimento $X_I$;
    
- calcola una **funzione di similarità** $S(X_Q, X_I)$, chiamata **match score**;
    
- confronta lo score con una **soglia** $T$.
    

Se lo score è “alto abbastanza” rispetto a $T$, il sistema decide **Yes**, altrimenti **No**.

Intuitivamente:

- **Soglia molto bassa** → “entrano tutti”;
    
- **Soglia molto alta** → “non entra nessuno”.

![](imgs/Pasted%20image%2020260215140011.png)

---

### **3. Genuini e impostori**

Si introduce il linguaggio standard:

- **Genuino**: utente che **ha diritto** ad accedere (il template è suo).
    
- **Impostore**: utente che **non ha diritto** ma prova lo stesso ad entrare.
    

Tutta la teoria degli errori si basa su:

- cosa succede quando l’utente è **genuino**,
    
- cosa succede quando l’utente è **impostore**.
    

---

### **4. Formulazione del problema in verifica (1:1)**

#### **4.1 Verifica come classificazione binaria**

Nella **verification** la domanda è:

> “Tu sei chi dici di essere?”

Formalmente, dato:

- un vettore di caratteristiche **query** $X_Q$,
    
- un’**identità dichiarata** $I$,
    
- il template memorizzato $X_I$ associato a $I$,
    

dobbiamo decidere se la coppia $(I, X_Q)$ appartiene a:

- $\omega_1$ = richiesta vera (utente genuino),
    
- $\omega_2$ = richiesta falsa (impostore).
    

È un **problema di classificazione binaria**.

#### **4.2 Regola di decisione in verifica**

Sia:

- $S(X_Q,X_I)$ = similarity score,
    
- $T$ = soglia.
    

La regola è:

$$
(I, X_Q) \in  
\begin{cases}  
\omega_1 & \text{se } S(X_Q,X_I) \ge T \\\\  
\omega_2 & \text{altrimenti}  
\end{cases}  
$$

Quindi:

- se lo score supera $T$ → l’utente è accettato come genuino;
    
- se è sotto $T$ → l’utente è rifiutato (considerato impostore).


---

### **5. Formulazione del problema in identificazione (1:N)**

Nell’**identificazione** non c’è identità dichiarata. La domanda è:

> “Chi sei tra tutti gli utenti registrati?”

Abbiamo:

- un vettore di caratteristiche $X_Q$,
    
- un DB con $M$ identità $I_1,\dots, I_M$,
    
- un caso di “reiezione” $I_{M+1}$ (= nessuna identità è abbastanza simile).
    

La regola (semplificata) è:

- si calcola $S(X_Q, X_{I_k})$ per tutti $k=1,\dots,M$;
    
- si prende l’identità con **score massimo**;
    
- se il massimo è sopra la soglia $T$, si accetta quell’identità;
    
- se tutti gli score sono sotto $T$, si decide “nessuno nel DB” ($I_{M+1}$).
    

Formalmente:

$$
I(X_Q) =  
\begin{cases}  
I_k & \text{se } k = \arg\max_j \{S(X_Q, X_{I_j}\}) \text{ e } S(X_Q, X_{I_k}) \ge T\\\\  
I_{M+1} & \text{altrimenti}  
\end{cases}  
$$

---

### **6. Distanza tra template e replay attack**

#### **6.1 I template non coincidono mai**

Importante: **N template della stessa persona non sono mai identici**.  
C’è sempre una **distanza** nello spazio delle feature, a causa di:

- rumore di acquisizione,
    
- posa diversa,
    
- illuminazione e sfondo,
    
- condizioni ambientali (umidità, temperatura), ecc.
    

Per questo la soglia $T$ **non può essere troppo alta**:  
se $T$ fosse impostata in modo da accettare solo $S = \text{score massimo possibile}$, non verrebbe mai accettato nessuno.

#### **6.2 Replay attack**

Se si vedesse davvero **distanza zero** (o score massimo assoluto) tra $X_Q$ e $X_I$, più che “match perfetto” è sospetto:

> Potrebbe essere un **replay attack**:  
> qualcuno ha copiato il template memorizzato e lo sta reiniettando nel sistema.

---

### **7. Genuine score e impostor score**

Definizioni chiave:

- **Genuine score**: score ottenuto confrontando template della **stessa persona**  
    (confronti _genuini_).
    
- **Impostor score**: score ottenuto confrontando template di **persone diverse**  
    (confronti _impostori_).

![](imgs/Pasted%20image%2020260215141021.png)

Se chiami $X_{i,j}$ il $j$-esimo template dell’individuo $i$:

- esempi genuine:
    
    - $S(X_{1,1}, X_{1,2})$, $S(X_{1,1}, X_{1,3})$, $S(X_{2,1}, X_{2,2})$, …
        
- esempi impostori:
    
    - $S(X_{1,1}, X_{3,2})$, $S(X_{4,1}, X_{3,1})$, …
        

Graficamente:

![](imgs/Pasted%20image%2020260215141126.png)

- istogramma rosso = distribuzione degli **impostor score**;
    
- istogramma verde = distribuzione dei **genuine score**.


---

### **8. False Match e False Non-Match**

#### **8.1 I due tipi di errore**

Quando la soglia è fissata a $T$, succedono due tipi di errore:

1. **False Match (FM)** – _errore grave_
    
    - un impostore è accettato dal sistema;
        
    - cioè un impostor score è **sopra** la soglia $T$.
        
    
    Esempio mentale:
    
    > “Il ladro entra in casa perché il sistema lo scambia per te.”
    
2. **False Non-Match (FNM)** – _errore fastidioso_
    
    - un genuino è rifiutato dal sistema;
        
    - cioè un genuine score è **sotto** la soglia $T$.
        
    
    Esempio:
    
    > “Tu non riesci ad entrare a casa tua perché il sistema non ti riconosce.”
    

---

#### **8.2 FMR(T) e FNMR(T)**

Per caratterizzare il sistema non basta contare FM e FNM, bisogna **normalizzarli**:

- **False Match Rate**:
    

$$
\text{FMR}(T) = \frac{\text{numero di False Match alla soglia } T}{\text{numero totale di confronti impostori}}  
$$

- **False Non-Match Rate**:
    

$$
\text{FNMR}(T) = \frac{\text{numero di False Non-Match alla soglia } T}{\text{numero totale di confronti genuini}}  
$$

Questi due tassi **dipendono dalla soglia $T$**.  

![](imgs/Pasted%20image%2020260215142643.png)

Muovendo la soglia, le due curve FMR(T) e FNMR(T) si muovono in modo opposto.

---

#### **8.3 Interpretazione probabilistica**

Se indichi con $p_n(s)$ e $p_m(s)$ le **densità di probabilità** degli score per genuini e impostori, allora:

- FNMR(T) è l’“area” dell’istogramma dei genuini sotto la soglia:
    

$$
\text{FNMR}(T) = \int_{-\infty}^{T} p_n(s), ds  
$$

- FMR(T) è l’“area” dell’istogramma degli impostori sopra la soglia:
    

$$
\text{FMR}(T) = \int_{T}^{+\infty} p_m(s), ds  
$$

Queste formule valgono se **conosciamo** le distribuzioni $p_n$ e $p_m$; nella pratica le stimiamo da dati sperimentali.

---

#### **8.4 FAR e FNAR**

Nel caso di **identificazione positiva**, gli stessi concetti vengono spesso rinominati:

- **FAR(T)** – False Accept Rate = FMR(T);
    
- **FNAR(T)** – False Non-Accept Rate = FNMR(T).
    

Sono solo diversi nomi, non concetti nuovi.

---

### **9. Curve DET e ROC**

#### **9.1 DET – Decision Error Tradeoff**

La **curva DET** mostra, al variare di $T$, la coppia:

$$
\text{DET}(T) = (\text{FMR}(T), \text{FNMR}(T))  
$$

![](imgs/Pasted%20image%2020260215162951.png)

Nel grafico tipico:

- asse orizzontale: FMR(T),
    
- asse verticale: FNMR(T).
    

Variando $T$ da $-\infty$ a $+\infty$, ottieni una curva che va:

- verso $(1,0)$ a soglia $-\infty$ (accetti tutti),
    
- verso $(0,1)$ a soglia $+\infty$ (rifiuti tutti).
    

#### **9.2 ROC – Receiver Operating Characteristic**

La **ROC** contiene la stessa informazione della DET, ma esprime in verticale la **True Accept Rate**:

$$
\text{ROC}(T) = \big(\text{FMR}(T), 1 - \text{FNMR}(T)\big)  
$$

![](imgs/Pasted%20image%2020260215163133.png)

Quindi:

- DET mette in evidenza **quanti genuini non entrano** (FNMR),
    
- ROC mette in evidenza **quanti genuini entrano** ($1-\text{FNMR}$).
    

Formalmente:

$$
\text{ROC}(T) = 1 - \text{DET}(T)  
$$

da intendere come trasformazione dell’asse verticale.

---

#### **9.3 Regioni di funzionamento sulla DET**

La stessa curva DET può descrivere **applicazioni molto diverse** in base alla zona in cui operi (slide 19):

![](imgs/Pasted%20image%2020260215163436.png)

- **Applicazioni ad altissimo livello di sicurezza** (deposito esplosivi, basi militari):
    
    - si accettano **pochissimi FMR**,
        
    - si tollerano FNMR anche non trascurabili (meglio rifiutare qualcuno in più che far entrare un impostore).
        
- **Applicazioni civili normali** (sblocco smartphone, accesso ufficio):
    
    - serve un compromesso intermedio.
        
- **Applicazioni forensi**:
    
    - quando cerchi **tutti i possibili candidati** per un’impronta trovata sulla scena del crimine,
        
    - si accetta un FMR maggiore (tanti sospetti possibili), per ridurre al minimo la probabilità di perdere il vero colpevole.
        

**Nota d’esame**: in alcune dispense gli assi FMR/FNMR sono invertiti → _guardare sempre le etichette degli assi_.

---

#### **9.4 Equal Error Rate (EER)**

L’**EER** è il punto della curva DET dove:

$$
\text{FMR}(T) = \text{FNMR}(T)  
$$

![](imgs/Pasted%20image%2020260215163714.png)

- È **l’unico numero singolo** che riassume le prestazioni complessive del sistema.
    
- Più basso è l’EER, **migliore** è il sistema.
    

Attenzione però: usare solo l’EER è una **semplificazione**; per applicazioni reali devi osservare l’intera DET.

---

#### **9.5 Sistema ideale**

Nello scenario ideale:

![](imgs/Pasted%20image%2020260215163836.png)

- esiste una soglia intermedia $T$ tale che:
    
    - $\text{FMR}(T) = 0$ e $\text{FNMR}(T) = 0$;
        
- le distribuzioni genuine e impostori non si sovrappongono per niente.
    

Curva DET:

- passa esattamente per i punti:
    
    - $(1,0)$ a soglia $-\infty$,
        
    - $(0,0)$ a soglia intermedia,
        
    - $(0,1)$ a soglia $+\infty$.
        

Ovviamente nella realtà questo non succede quasi mai.

![](imgs/Pasted%20image%2020260215164046.png)

---

### **10. Accuratezza in identificazione: curva CMC**

Per l’**identificazione 1:N** si usa spesso la curva **Cumulative Match Characteristic (CMC)**.

![](imgs/Pasted%20image%2020260215164107.png)

Procedura concettuale:

1. Hai $N$ persone nel DB.
    
2. Per ogni individuo $k$:
    
    - calcoli gli score tra il template di $k$ e tutti gli altri;
        
    - ordini le identità per **similarità decrescente**, ottenendo una classifica.
        
3. Per ogni **Rank $R$** (1, 2, 3, …):
    
    - conti quante volte l’identità corretta $k$ compare _tra i primi $R$_ posti;
        
    - dividi per $N$ → ottieni la **probabilità di riconoscimento** a rank $R$.
        

La CMC è un grafico:

- asse x: Rank (1, 2, …),
    
- asse y: Probability of Recognition (%).
    

È molto usata per valutare sistemi **1:N** (es. video-sorveglianza, forense).

---

### **11. Caratterizzazione degli utenti: lo zoo di Doddington**

Non tutti gli utenti sono “uguali” dal punto di vista biometrico.  
Doddington propone un modello “zoologico”:

- **Sheeps (pecore)**
    
    - feature molto distintive,
        
    - bassa variabilità intraclasse,
        
    - generano **pochi False Match e pochi False Non-Match**.
        
- **Goats (capre)**
    
    - alta variabilità intraclasse (il loro tratto cambia molto),
        
    - tendono ad avere molti **False Reject** (FNMR alto).
        
- **Lambs (agnelli)**
    
    - feature simili a quelle degli altri,
        
    - bassa distanza interclasse,
        
    - sono facili da imitare → **FMR alto** (qualcun altro entra al posto loro).
        
- **Wolves (lupi)**
    
    - individui capaci di **manipolare il tratto** (soprattutto comportamentale, es. voce, firma)
        
    - riescono a impersonare altri utenti → aumentano il **False Accept Rate**.
        

Questa classificazione serve a ricordare che **gli errori non dipendono solo dall’algoritmo**, ma anche dalla distribuzione degli utenti nel mondo reale.

---

### **12. Dal sistema biometrico al modello probabilistico**

La seconda parte della lezione introduce il collegamento con la **statistica**: come modelliamo gli errori del sistema?

![](imgs/Pasted%20image%2020260215170106.png)

#### **12.1 Esperimenti di Bernoulli**

Esempi:

- “Il dado esce 6, sì/no”;
    
- “Il sistema sbaglia la classificazione del genere, sì/no”;
    
- “Il sistema biometrico sbaglia l’autenticazione, sì/no”.
    
![](imgs/Pasted%20image%2020260215170317.png)

Questi sono **esperimenti di Bernoulli** se:

1. ci sono solo **due esiti**: successo (1) / insuccesso (0);
    
2. la probabilità di successo $p$ è **costante**;
    
3. le prove sono **indipendenti**.
    

Per un sistema biometrico:

- mettiamo $X = 1$ se il sistema commette un **errore** su una prova,
    
- $X = 0$ se la prova è corretta,
    
- la probabilità $p = P(X=1)$ è il **tasso di errore** del sistema.
    

---

#### **12.2 Processo di Bernoulli**

Se usi il sistema biometrico in **identificazione** su un DB di $N$ persone, ogni confronto può essere visto come **una prova di Bernoulli**.

- La variabile aleatoria $S_n = X_1 + X_2 + \dots + X_n$  
    conta il **numero di errori** dopo $n$ prove.
    

La probabilità di avere esattamente $k$ errori su $n$ prove è binomiale:

$$
P(S_n = k) = \binom{n}{k} p^k (1-p)^{n-k}  
$$

Esempio: se il sistema ha $p = 10^{-2}$,  
la probabilità di avere **1 solo errore** su $n=1000$ prove si calcola con la binomiale per $k = 1$.

---

### **13. Cosa vogliamo stimare in pratica**

Possiamo porci alcune domande tipiche:

- Se oggi misuro un certo tasso di errore $p$, **quanto cambierebbe** se rifacessi l’esperimento con gli stessi volontari?
    
- Se porto il sistema da **verifica su 100 persone** a **identificazione su 1.000.000 persone**, come cambiano gli errori?
    
- **Quante persone N** devo usare per stimare bene $p$?
    
- E cosa significa “bene” (in termini di intervalli di confidenza)?
    

Le “regole dei 3 e dei 30” rispondono proprio a queste domande operative.

---

### **14. Regola dei 3**

Domanda:

> “Qual è il tasso di errore più basso $p$ che posso stimare da un esperimento con $N$ confronti indipendenti?”

Se in $N$ prove **osservo zero errori**, non posso dire “$p = 0$”.  
Con una confidenza del 95% si può dimostrare che:

$$
p_{\min} \approx \frac{3}{N}  
$$

Interpretazione:

- Se faccio **300 prove** indipendenti e ho **zero errori**,  
    non significa che il sistema è perfetto ($p=0$).  
    Posso dire, con confidenza 95%, che **$p$ è circa $3/300 = 1\%$**.
    

Questa è una regola molto usata per capire **quanto grande deve essere il dataset** per “vedere” errori rari.

---

### **15. Estensione della regola dei 3**

Se il sistema commette **5 errori** su $N$ prove, il grafico mostra:

![](imgs/Pasted%20image%2020260215170649.png)

- sull’asse x: **Error Rate osservato** (5/N),
    
- sull’asse y: **Error Rate “claimato”** (quello che dichiari),
    
- una banda di confidenza che ti dice **per quali valori di $p$ il claim è supportato** o **rigettato**.
    

Idea da portare via:

> non basta dire “ho visto 5 errori su $N$ prove” → serve sempre un **intervallo di confidenza** per il vero $p$.

---

### **16. Da verification a identification: crescita degli errori**

Domanda:

> “Quanto aumentano gli errori se uso un sistema di **Verifica** (1:1) in modalità di **Identificazione** (1:N)?”

Sotto ipotesi semplificative (campioni indipendenti, nessuna indicizzazione, un solo template per utente):

- per i genuini:  
    
    $\text{FNMR}_N = \text{FNMR}$ 
    
    il tasso di rifiuto dei genuini **non cambia** (continua a dipendere dalla soglia).
    
- per gli impostori:  
    
    $\text{FMR}_N = 1 - (1 - \text{FMR})^N$
    
    cioè 1 meno la probabilità che **non accada mai un False Match** nei $N$ confronti.
    

Se FMR è piccolo, puoi approssimare:

$$
\text{FMR}_N \approx N \cdot \text{FMR}  
$$

Quindi:

> **l’errore di False Match cresce linearmente con la dimensione del DB**  
> (se mantieni la stessa soglia).

Questo è cruciale: per database enormi, la stessa soglia che va bene in 1:1 potrebbe produrre FMR inaccettabili in 1:N.

---

### **17. Regola dei 30**

Usata per decidere “quanti errori servono per stimare bene $p$”.

Enunciato:

> Per essere sicuro, con **intervallo di confidenza del 90%**,  
> che il **vero tasso di errore** sia entro il **±30%** del tasso di errore osservato,  
> devono esserci **almeno 30 errori**.

Esempio:

- osservi **30 falsi Non-Match** su **3000 confronti genuini indipendenti**;
    
- tasso osservato: $p_{\text{obs}} = 30 / 3000 = 1\%$.
    

La regola dei 30 dice:

- con confidenza 90%,
    
- il vero tasso sta circa tra **0.7% e 1.3%** (cioè ±30% di 1%).
    

---

### **18. Sintesi della Lezione 6**

In questa lezione hai visto che:

- un sistema biometrico **non si valuta con un solo numero**, ma con:
    
    - **FMR(T)**, **FNMR(T)** (o FAR/FNAR),
        
    - curve **DET** e **ROC**,
        
    - **CMC** per l’identificazione,
        
    - **EER** come indicatore sintetico;
        
- gli utenti non sono tutti uguali (zoo di Doddington: pecore, capre, agnelli, lupi);
    
- gli errori possono essere modellati come **prove di Bernoulli** e **processi binomiali**;
    
- la **regola dei 3** ti dice il minimo errore stimabile con $N$ prove e zero errori;
    
- la **regola dei 30** ti dice quanti errori ti servono per avere una stima affidabile;
    
- passando da **verification a identification**, il **False Match Rate esplode con N** se non riprogetti soglia e architettura.
    

Con questa base, le prossime lezioni potranno entrare nel dettaglio di **come stimare operativamente questi parametri da un dataset reale** e **come progettare soglie e sistemi** per diverse applicazioni.