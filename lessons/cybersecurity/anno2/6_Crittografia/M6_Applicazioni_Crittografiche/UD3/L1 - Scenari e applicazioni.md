
## **Lezione 1: Scenari e applicazioni**

### **1. Introduzione**

Questa lezione introduce il concetto di **condivisione di segreti** (_secret sharing_), ossia una tecnica che permette di distribuire un **segreto $S$** tra più partecipanti in modo controllato.  
L’obiettivo è consentire la **ricostruzione del segreto** solo se un numero minimo di partecipanti collabora, garantendo al tempo stesso che gruppi più piccoli **non ottengano alcuna informazione**.

Il primo schema di questo tipo è stato proposto da **Adi Shamir nel 1979**, e rappresenta uno dei fondamenti della crittografia moderna distribuita.

---

### **2. Scenario generale**

Un’entità chiamata **dealer** (distributore del segreto) possiede un segreto $S$ e lo vuole condividere tra **$n$ partecipanti** $P_1, P_2, \dots, P_n$.  
Si impone una soglia **$k$**, tale che:

- **$k$ o più partecipanti** possono ricostruire $S$;
    
- **$k - 1$ o meno partecipanti** non ottengono alcuna informazione utile su $S$.
    

Questa configurazione prende il nome di **schema $(k, n)$**.

---

### **3. Distribuzione del segreto**

Durante la fase di distribuzione:

- Il **dealer** calcola e assegna a ciascun partecipante una **quota** del segreto, detta **share**.
    
- Ogni partecipante $P_i$ riceve una propria **share$_i$**, generalmente rappresentata come un elemento di un campo finito $\mathbb{Z}_p$.
    

In questo modo, nessun partecipante conosce il segreto completo, ma solo una parte che diventa utile **solo in combinazione con quelle degli altri**.

---

### **4. Ricostruzione del segreto**

#### **Caso 1 — $k$ partecipanti collaborano**

Quando almeno $k$ partecipanti combinano le proprie quote, è possibile **ricostruire il segreto $S$**.  
Questa operazione sfrutta una funzione di ricostruzione nota solo ai partecipanti autorizzati (nel caso di Shamir, un’interpolazione polinomiale).

#### **Caso 2 — meno di $k$ partecipanti**

Se collaborano solo $k - 1$ o meno partecipanti, la conoscenza parziale delle share **non rivela alcuna informazione su $S$**.  
In termini formali:  
$$  
\Pr(S | \text{share}_1, \dots, \text{share}_{k-1}) = \Pr(S)  
$$

ovvero la probabilità di conoscere $S$ non cambia.

---

### **5. Schema base $(n, n)$**

Un semplice schema di condivisione è quello **$(n, n)$**, dove **tutti i partecipanti** devono cooperare per ricostruire il segreto.

#### **Inizializzazione**

- Sia $p$ un **numero primo**.
    
- Il dealer sceglie casualmente $a_1, a_2, \dots, a_{n-1}$ in $\mathbb{Z}_p$.
    
- Si impone:  
    $$  
    a_n = S - (a_1 + a_2 + \dots + a_{n-1}) \mod p  
    $$
    

#### **Distribuzione**

Il dealer invia a ciascun partecipante $P_i$ il valore $a_i$.

#### **Ricostruzione**

Per ricomporre il segreto:  
$$  
S = (a_1 + a_2 + \dots + a_n) \mod p  
$$

---

### **6. Esempio pratico — schema $(5,5)$**

Sia:  
$$  
p = 7, \quad S = 5  
$$

Il dealer sceglie:  
$$  
a_1 = 3, \quad a_2 = 2, \quad a_3 = 1, \quad a_4 = 2  
$$  
Calcola poi:  
$$  
a_5 = 5 - 3 - 2 - 1 - 2 \mod 7 = 4  
$$

Distribuisce quindi:

|Partecipante|Share|
|---|---|
|P₁|3|
|P₂|2|
|P₃|1|
|P₄|2|
|P₅|4|

Quando tutti e 5 i partecipanti si riuniscono, ricostruiscono:  
$$  
S = 3 + 2 + 1 + 2 + 4 \mod 7 = 12 \mod 7 = 5  
$$

---

### **7. Proprietà di sicurezza**

- Ogni partecipante conosce **solo la propria share**, e nessuna informazione sulle altre.
    
- Solo l’unione di tutte le quote consente di **ottenere il segreto $S$**.
    
- Anche conoscendo $n - 1$ valori, **resta una soluzione possibile per ogni valore di $S$**, quindi l’attaccante **non guadagna alcuna conoscenza utile**.
    

---

### **8. Sintesi finale**

- Gli **schemi di condivisione del segreto** permettono di distribuire un’informazione in modo sicuro e cooperativo.
    
- Lo schema $(n, n)$ è la versione più semplice: tutte le quote sono necessarie per ricostruire $S$.
    
- Il metodo, esteso da **Shamir nel 1979**, consente di costruire schemi più flessibili $(k, n)$, dove solo un sottoinsieme minimo di partecipanti è sufficiente per recuperare il segreto.