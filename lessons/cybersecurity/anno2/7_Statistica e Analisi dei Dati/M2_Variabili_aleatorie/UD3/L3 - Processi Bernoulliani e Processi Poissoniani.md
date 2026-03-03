# **M2 UD2 Lezione 3 - Processi Bernoulliani e Processi Poissoniani**

### **1. Inquadramento generale**

Finora abbiamo visto:

- la **Binomiale** come modello discreto di conteggio,
    
- la **Poissoniana** come suo limite continuo per eventi rari,
    
- la **Geometrica** e l’**Esponenziale negativa** come modelli dei tempi d’attesa.

Questa lezione unifica questi modelli in due grandi famiglie di processi:

- i **processi Bernoulliani** (discreti),
    
- i **processi Poissoniani** (continui).

Entrambi descrivono fenomeni **memoryless**, cioè privi di memoria:  
la probabilità di un nuovo evento **non dipende dal passato**.

---

### **2. La Poissoniana come approssimazione della Binomiale**

La **distribuzione di Poisson** approssima la **Binomiale** quando:  
$$  
n \text{ è molto grande, } p \text{ è molto piccolo, } \text{e } np = \mu \text{ resta finito.}  
$$

$$  
P(k) = \frac{\mu^k e^{-\mu}}{k!}, \quad \text{con } \mu = np  
$$

In questa condizione:

- la **media** è $\mu = np$,
    
- la **varianza** è $\sigma^2 = \mu = np$.

#### **Esempio – Guasto del motore**

Un’auto ha probabilità $p = \tfrac{2}{100}$ di fallire l’accensione.  
Durante le vacanze verrà accesa $n = 200$ volte.

$$  
\mu = n p = (200) \times \frac{2}{100} = 4  
$$

$$  
P(k|\mu=4) = e^{-4} \frac{4^k}{k!}  
$$

|**Evento**|**Formula**|**Valore**|
|---|---|---|
|Nessun guasto|$P(0) = e^{-4} = 0.0183$|1.83%|
|Esattamente 1 guasto|$P(1) = 4 e^{-4} = 0.0733$|7.33%|
|Almeno 2 guasti|$P(k>1) = 1 - [P(0)+P(1)] = 0.9084$|90.84%|

---

### **3. Poissoniana ed Esponenziale: legame tra tempi e conteggi**

La **Poissoniana** descrive _quanti eventi_ accadono in un intervallo,  
mentre l’**Esponenziale negativa** descrive _quanto tempo passa_ tra due eventi.

I due modelli sono **complementari**.

| **Esperimento**    | **Discreto** | **Continuo** |
| ------------------ | ------------ | ------------ |
| **Tempi d’attesa** | Geometrica   | $G(ip)$      |
| **Conteggi**       | Binomiale    | $B(kpn)$     |

---

### **4. Legame tra i parametri λ e μ**

Il collegamento fra la **densità esponenziale** e la **Poissoniana** è dato da:

$$  
\mu = \lambda , \Delta t  
$$

dove:

- $\lambda$ = **rate** di eventi per unità di tempo (es. guasti/secondo),
    
- $\Delta t$ = **durata dell’intervallo di osservazione**,
    
- $\mu$ = **numero medio di eventi attesi** in $\Delta t$.

---

### **5. Poissoniana per fenomeni memoryless sul continuo**

Quando un fenomeno _senza memoria_ è osservato per un intervallo $\Delta t$,  
la sua distribuzione di conteggio è Poissoniana:

$$  
P(k|\mu=\lambda \Delta t) = e^{-\lambda \Delta t} \frac{(\lambda \Delta t)^k}{k!}  
$$

e vale:  
$$  
\mu = \lambda \Delta t, \quad \sigma^2 = \mu = \lambda \Delta t  
$$

---

### **6. Esempio – Fulmini in un cielo temporalesco**

Il cielo è attraversato da fulmini con rate $\lambda = \tfrac{2}{100}$ (2 ogni 100 s).  
L’otturatore resta aperto per $\Delta t = 200$ s.

$$  
\mu = \lambda \Delta t = (2/100)\times200 = 4  
$$

|**Evento**|**Formula**|**Valore**|
|---|---|---|
|Nessun fulmine|$P(0)=e^{-4}=0.0183$|1.83%|
|Esattamente 1 fulmine|$P(1)=4e^{-4}=0.0733$|7.33%|
|Almeno 2 fulmini|$P(k>1)=1-[P(0)+P(1)]=0.9084$|90.84%|

---

### **7. Equivalenza tra Poissoniana ed Esponenziale**

Verifichiamo che le due distribuzioni forniscono **risultati coerenti**.

1. **Nessun evento in Δt**  
    $$  
    P(k=0|\mu=\lambda \Delta t) = e^{-\lambda \Delta t}  
    $$  
    che coincide con la funzione di sopravvivenza dell’esponenziale:  
    $$  
    S(\Delta t) = e^{-\lambda \Delta t}  
    $$
    
2. **Almeno un evento in Δt**  
    $$  
    P(k>0) = 1 - e^{-\lambda \Delta t}  
    $$  
    che coincide con la funzione di fallibilità:  
    $$  
    F(\Delta t) = 1 - e^{-\lambda \Delta t}  
    $$
    
3. **Esattamente un evento**  
    $$  
    P(k=1|\mu=\lambda \Delta t) = (\lambda \Delta t)e^{-\lambda \Delta t}  
    $$  
    Non può essere ottenuto dall’esponenziale negativa, che fornisce solo $P(k\ge1)$.
    
4. **Almeno due eventi**  
    $$  
    P(k\ge2) = 1 - [P(k=0) + P(k=1)] = 1 - e^{-\lambda \Delta t}(1 + \lambda \Delta t)  
    $$

---

### **8. Processi Bernoulliani e Processi Poissoniani**

#### **Processo Bernoulliano**

È un insieme di prove indipendenti, ciascuna con probabilità di successo $p$ costante.  
I modelli associati sono:

- **Geometrica** → tempo d’attesa discreto,
    
- **Binomiale** → conteggio discreto.

#### **Processo Poissoniano**

È la versione continua del Bernoulliano, con un **tasso infinitesimo $\lambda$** per unità di tempo.  
I modelli associati sono:

- **Esponenziale negativa** → tempo d’attesa continuo,
    
- **Poissoniana** → conteggio continuo.

|**Aspetto**|**Processo Bernoulliano**|**Processo Poissoniano**|
|---|---|---|
|Tipo|Discreto|Continuo|
|Parametri|$p, n$|$\lambda, \Delta t$|
|Tempi d’attesa|Geometrica $G(i|p)$|
|Conteggi|Binomiale $B(k|p,n)$|
|Proprietà|Memoryless discreto|Memoryless continuo|

---

### **9. Proprietà fondamentali dei processi di Poisson**

I processi Poissoniani presentano due proprietà chiave, dirette conseguenze della loro **mancanza di memoria**:

#### **a) Proprietà di Merging**

Se sommiamo due o più processi Poissoniani indipendenti con rate $\lambda_1$ e $\lambda_2$,  
otteniamo ancora un processo Poissoniano, con rate totale:

$$  
\lambda = \lambda_1 + \lambda_2  
$$

Il fenomeno combinato resta Poissoniano.

#### **b) Proprietà di Splitting**

Se da un processo Poissoniano con rate $\lambda$ estraiamo ogni evento con probabilità $p$,  
i due processi risultanti sono **Poissoniani indipendenti** con rate:

$$  
\lambda_1 = p\lambda, \quad \lambda_2 = (1-p)\lambda  
$$

Questa proprietà è la base dei modelli di traffico e di rete in **teoria delle code** e **affidabilità**.

---

### **10. Sintesi finale**

|**Concetto**|**Bernoulliano (Discreto)**|**Poissoniano (Continuo)**|
|---|---|---|
|**Parametro di evento**|$p$|$\lambda$|
|**Numero di prove / intervallo**|$n$|$\Delta t$|
|**Media**|$\mu = np$|$\mu = \lambda \Delta t$|
|**Distribuzione dei conteggi**|Binomiale $B(k|p,n)$|
|**Distribuzione dei tempi d’attesa**|Geometrica $G(i|p)$|
|**Proprietà**|Memoryless (discreto)|Memoryless (continuo)|
|**Merging**|Non definito|$\lambda = \lambda_1 + \lambda_2$|
|**Splitting**|Non definito|$p\lambda$ e $(1-p)\lambda$|

---

### **11. Conclusione**

I processi **Bernoulliani** e **Poissoniani** rappresentano i due volti — discreto e continuo — degli stessi fenomeni casuali privi di memoria.  
Il passaggio da $p$ e $n$ a $\lambda$ e $\Delta t$ permette di estendere le leggi del conteggio e dell’attesa dal dominio discreto al continuo, mantenendo intatta la struttura probabilistica fondamentale.