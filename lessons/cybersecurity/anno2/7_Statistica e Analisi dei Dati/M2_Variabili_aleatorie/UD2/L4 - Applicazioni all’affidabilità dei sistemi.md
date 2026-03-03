# **M2 UD2 Lezione 4 - Applicazioni all’affidabilità dei sistemi**

### **1. Confronto tra distribuzione geometrica ed esponenziale**

Le distribuzioni **geometrica** (discreta) ed **esponenziale negativa** (continua) sono strettamente collegate: entrambe modellano fenomeni **senza memoria**, in cui la probabilità di un evento **non dipende dal passato**.

#### **Distribuzione geometrica**

$$  
G(i \mid p) = p \cdot q^{i-1}, \quad \text{con } q = 1 - p  
$$

$$  
\begin{cases}  
F(i) = 1 - q^i \  
S(i) = q^i  
\end{cases}  
$$

$$  
\langle i \rangle = \frac{1}{p}, \quad \operatorname{Var}(i) = \frac{q}{p^2}  
$$

#### **Densità esponenziale negativa**

$$  
f(t) = \lambda e^{-\lambda t}  
$$

$$  
\begin{cases}  
F(t) = 1 - e^{-\lambda t} \\\\  
S(t) = e^{-\lambda t}  
\end{cases}  
$$

$$  
\langle t \rangle = \frac{1}{\lambda}, \quad \operatorname{Var}(t) = \frac{1}{\lambda^2}  
$$

#### **Analogia**

Le due forme sono **funzionalmente equivalenti**:

$$  
q^i \longleftrightarrow e^{-\lambda t}  
$$

La differenza è solo nel **dominio**:

- la geometrica valuta la funzione in tempi discreti $t = i$;
    
- l’esponenziale negativa in tempi continui $t \in \mathbb{R}^+$.

In effetti, si può mostrare che ponendo:

$$  
\lambda = -\ln q  
$$

le due distribuzioni risultano **analiticamente identiche**.

---

### **2. Mancanza di memoria e indipendenza**

Sia nella distribuzione geometrica che in quella esponenziale vale la **proprietà di mancanza di memoria** (_memoryless_).

In formule:

$$  
P(t > s + t_0 \mid t > t_0) = P(t > s)  
$$

Ciò significa che la **probabilità di sopravvivere ancora per un tempo $s$**, dato che si è già sopravvissuti fino a $t_0$, è **indipendente dal passato**.

#### **Esempio intuitivo**

- Una **moneta bilanciata** con $p = 0.5$ non “ricorda” i risultati precedenti:  
    la probabilità di ottenere “testa” al prossimo lancio resta sempre $0.5$, indipendentemente da quante “croci” siano uscite prima.
    
- Un **dispositivo senza memoria** funziona nello stesso modo:  
    il fatto che abbia già lavorato 10 ore non cambia la sua probabilità di resistere per altre 10 ore.

Formalmente:

$$  
P(\text{futuro} \mid \text{passato}) = P(\text{futuro})  
$$

---

### **3. Esempio pratico: la roulette russa**

Sia $p = \tfrac{1}{6}$, $q = \tfrac{5}{6}$.

- Probabilità di sopravvivere ad almeno 10 duelli:

$$  
S(10) = q^{10} = 0.1615  
$$

- Probabilità di sopravvivere ad almeno 20 duelli:

$$  
S(20) = q^{20} = 0.0261  
$$

Se il giocatore ha già superato 10 duelli, la **probabilità di superarne altri 10** è ancora $q^{10} = 0.1615$,  
nonostante l’intuizione suggerisca il contrario.  
Il meccanismo di generazione degli eventi **non cambia nel tempo**.

---

### **4. Sistemi che invecchiano e sistemi senza memoria**

Nel mondo reale molti sistemi **invecchiano**:

- gli esseri viventi sperimentano l’usura biologica;
    
- i dispositivi meccanici degradano con l’uso (es. una pietra focaia che si consuma).

In questi casi, la probabilità di sopravvivenza **diminuisce nel tempo**: $q$ non è costante.

$$  
q_i < q_{i-1}  
$$

Viceversa, nei **sistemi senza memoria**, $q$ rimane costante nel tempo:  
la probabilità di resistere per un nuovo intervallo di tempo **non dipende da quanto si è già durato**.

Nel continuo, ciò corrisponde a un **tasso di fallimento $\lambda$ costante** nel tempo.  
Nei sistemi che invecchiano, invece, $\lambda$ tende ad **aumentare** progressivamente.

---

### **5. Dispositivi memoryless**

Un **dispositivo senza memoria** si comporta così:

- Dopo un anno di funzionamento, **se è ancora attivo**, è _come nuovo_.
    
- Non si può però dire che **tra un anno sarà ancora come nuovo**, perché potrebbe essersi già guastato.

Formalmente:

> “Se è ancora in funzione, il suo stato è identico a quello iniziale.”

Questa è la base dell’**esponenziale negativa**, che rappresenta **componenti con tasso di guasto costante**.

---

### **6. Affidabilità e fallibilità nel tempo**

Nel contesto dell’affidabilità dei sistemi:

- $R_A(t)$ o $S_A(t)$ → **affidabilità**: probabilità che il componente A funzioni fino al tempo $t$.
    
- $F_A(t)$ → **fallibilità**: probabilità che il componente fallisca entro $t$.

Per un componente senza memoria con parametro $\lambda_A$:

$$  
\begin{cases}  
R_A(t) = e^{-\lambda_A t} \\\\  
F_A(t) = 1 - e^{-\lambda_A t}  
\end{cases}  
$$

---

### **7. Sistemi in serie e in parallelo**

#### **Componenti in serie**

Un sistema in **serie** funziona se **tutti i componenti** funzionano.  
L’affidabilità complessiva è il **prodotto** delle singole affidabilità:

$$  
R_{\text{serie}}(t) = R_1(t) , R_2(t) , \dots , R_n(t)  
$$

Se i componenti sono indipendenti e **senza memoria**, allora:

$$  
R_{\text{serie}}(t) = e^{-(\lambda_1 + \lambda_2 + \dots + \lambda_n)t}  
$$

Il sistema risulta ancora **memoryless**, con tasso equivalente:

$$  
\lambda_{\text{serie}} = \lambda_1 + \lambda_2 + \dots + \lambda_n  
$$

**Osservazione:**  
aggiungere componenti in serie **riduce la vita media complessiva**.

Se tutti hanno lo stesso $\lambda$:

$$  
R_{\text{serie}}(t) = e^{-n \lambda t} \quad \Rightarrow \quad \tau_{\text{serie}} = \frac{1}{n\lambda}  
$$

---

#### **Componenti in parallelo**

Un sistema in **parallelo** funziona se **almeno un componente** è attivo.  
La fallibilità complessiva è il **prodotto** delle singole fallibilità:

$$  
F_{\text{parallelo}}(t) = F_1(t) , F_2(t) , \dots , F_n(t)  
$$

Da cui:

$$  
R_{\text{parallelo}}(t) = 1 - F_{\text{parallelo}}(t)  
$$

Per due componenti:

$$  
\begin{cases}  
F_1(t) = 1 - e^{-\lambda_1 t} \\\\  
F_2(t) = 1 - e^{-\lambda_2 t}  
\end{cases}  
$$

$$  
F_{\text{parallelo}}(t) = (1 - e^{-\lambda_1 t})(1 - e^{-\lambda_2 t})  
$$

$$  
\Rightarrow f_{\text{parallelo}}(t) = \lambda_1 e^{-\lambda_1 t} (1 - e^{-\lambda_2 t}) + \lambda_2 e^{-\lambda_2 t} (1 - e^{-\lambda_1 t})  
$$

Se $\lambda_1 = \lambda_2 = \lambda$:

$$  
f_{\text{parallelo}}(t) = 2 \lambda e^{-\lambda t} (1 - e^{-\lambda t})  
$$

In questo caso, aggiungere componenti in parallelo **aumenta la vita media** del sistema, ma **in modo non lineare**.

---

### **8. Interpretazione statistica: minimo e massimo del campione**

- Nei sistemi **in serie**, il guasto del sistema è determinato dal **primo componente** che si guasta → **tempo minimo**.
    
- Nei sistemi **in parallelo**, il guasto del sistema è determinato dall’**ultimo componente** che si guasta → **tempo massimo**.

In generale:

- Il tempo di guasto in un sistema in serie segue la **distribuzione del minimo** di un campione.
    
- Il tempo di guasto in un sistema in parallelo segue la **distribuzione del massimo** di un campione.

---

### **9. Riepilogo finale**

|**Concetto**|**Formula / Relazione**|**Significato**|
|---|---|---|
|**Proprietà memoryless**|$P(t > s + t_0 \mid t > t_0) = P(t > s)$|Il passato non influenza il futuro|
|**Affidabilità singolo componente**|$R(t) = e^{-\lambda t}$|Probabilità che funzioni fino al tempo $t$|
|**Fallibilità singolo componente**|$F(t) = 1 - e^{-\lambda t}$|Probabilità di fallimento entro $t$|
|**Serie di componenti**|$R_{\text{serie}}(t) = e^{-(\lambda_1 + \dots + \lambda_n)t}$|Il sistema fallisce quando fallisce il primo|
|**Parallelo di componenti**|$R_{\text{parallelo}}(t) = 1 - \prod_i (1 - e^{-\lambda_i t})$|Il sistema fallisce quando fallisce l’ultimo|
|**Caso identico ($\lambda_i = \lambda$)**|$R_{\text{serie}}(t) = e^{-n\lambda t}$, $R_{\text{parallelo}}(t) = 1 - (1 - e^{-\lambda t})^n$|Serie accorcia, parallelo allunga la vita|
|**Interpretazione statistica**|Serie → minimo, Parallelo → massimo|Distribuzioni dei tempi di guasto|
