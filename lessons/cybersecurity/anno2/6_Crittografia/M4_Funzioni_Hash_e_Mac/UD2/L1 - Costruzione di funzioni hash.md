
## **Lezione 1: Costruzione di funzioni hash**

### **1. Introduzione**

In questa lezione analizziamo **come si costruiscono le funzioni hash crittografiche** a partire da messaggi di lunghezza arbitraria.  
Poiché un algoritmo di hash deve poter processare **messaggi di qualunque dimensione** ma restituire un **digest a lunghezza fissa**, è necessario introdurre **schemi strutturali** che consentano di trattare il messaggio in blocchi e combinarli in modo sicuro.

---

### **2. Struttura generale di una funzione hash**

#### **2.1 Messaggi di input**

Un messaggio $M$ viene diviso in **k blocchi** di lunghezza fissa:

$$  
M = (m_1, m_2, \ldots, m_k)  
$$

Ogni blocco viene elaborato attraverso la funzione di hash secondo due approcci principali:

- **Seriale / iterato**  
    L’elaborazione procede in sequenza: l’output di un blocco diventa l’input del successivo.
    
- **Parallelo**  
    I blocchi vengono elaborati simultaneamente e poi combinati.
    

---

### **3. Modelli generali**

#### **3.1 Hash parallelo**

Nel **modello parallelo**, ogni blocco $m_i$ viene processato da una funzione hash indipendente, e i risultati vengono concatenati o combinati.  
Secondo **Damgård**, una funzione hash costruita in modo parallelo è **resistente alle collisioni** se lo è anche la funzione interna $h$.

In formula, se:

$$  
H(M) = H_1(M) \circ H_2(M)  
$$

trovare una collisione per $H(M)$ significa trovarla **sia per $H_1$ che per $H_2$**.

---

#### **3.2 Hash iterato (seriale)**

Nel **modello iterato**, ogni blocco è elaborato in sequenza.  
La struttura è composta da:

- una **funzione di compressione** $f$,
    
- un **valore iniziale** (Initialization Vector, $IV$),
    
- e una sequenza di stati intermedi $H_i$.
    

Lo schema generale è:

$$  
H_i = f(H_{i-1}, X_i)  
$$

dove $X_i$ è il blocco di messaggio $i$-esimo, e $H_i$ rappresenta lo stato dell’hash dopo averlo processato.

Dopo l’ultimo blocco, si ottiene:

$$  
H(M) = H_k  
$$

L’aggiunta di **padding** serve ad assicurare che l’ultimo blocco abbia sempre lunghezza corretta.

> Una collisione su $h(M)$ implica una collisione sull’intera funzione $f$, quindi la sicurezza dipende dalla resistenza della funzione di compressione.

---

### **4. Funzioni hash a cascata**

Un altro modo per costruire funzioni hash è combinarle **in cascata**, cioè applicare più funzioni una dopo l’altra.

Se ad esempio abbiamo due funzioni hash $H_1$ e $H_2$, si può definire:

$$  
H(M) = H_1(M) \circ H_2(M)  
$$

Trovare una collisione in $H$ implica trovarla **in entrambe le componenti**, rendendo l’attacco più complesso.

---

### **5. Funzioni hash basate su cifrari a blocchi**

Molte funzioni hash si costruiscono sfruttando **cifrari a blocchi**, cioè funzioni di cifratura $E_K(x)$ che prendono un blocco di input e una chiave $K$ di lunghezza fissa.

L’idea generale è definire una funzione di compressione a partire da un cifrario, utilizzando diverse combinazioni di XOR e cifratura.

Alcune strutture famose:

#### **5.1 Matyas–Meyer–Oseas**

$$  
H_i = E_{M_i}(H_{i-1}) \oplus H_{i-1}  
$$

#### **5.2 Davies–Meyer**

$$  
H_i = E_{H_{i-1}}(M_i) \oplus M_i  
$$

#### **5.3 Miyaguchi–Preneel**

$$  
H_i = E_{H_{i-1}}(M_i) \oplus M_i \oplus H_{i-1}  
$$

Dove:

- $M_i$ è il blocco del messaggio (eventualmente con padding),
    
- $H_0$ è una **costante predefinita** (Initialization Vector),
    
- e $H_i$ è il valore hash parziale.
    

> Tutti questi schemi si basano sull’uso di cifrari a blocchi per garantire **resistenza alla collisione** e **buone proprietà di diffusione**.

---

### **6. Attacco “Meet in the Middle”**

#### **6.1 Idea generale**

Le funzioni hash con struttura **a catena iterata** possono essere vulnerabili a una variante del **birthday attack** nota come _attacco “Meet in the Middle”_.

Scopo: generare un **messaggio falso** $R = (r_1, r_2)$ diverso da un messaggio originale $M = (m_1, m_2)$, ma con **lo stesso hash**:

$$  
h(R) = h(M)  
$$

---

#### **6.2 Schema dell’attacco**

1. **Genera molte varianti** della prima parte $r_1$ e calcola i valori intermedi corrispondenti.
    
2. **Genera molte varianti** della seconda parte $r_2$ e calcola i valori intermedi “a ritroso”.
    
3. **Confronta i risultati intermedi**: quando si trova una corrispondenza, si ottiene una collisione completa.
    

Questo metodo ha **la stessa probabilità di successo** del _birthday attack_, cioè circa dopo $2^{n/2}$ tentativi.

---

#### **6.3 Esempio pratico**

Consideriamo una funzione hash a due blocchi $M = (m_1, m_2)$:

$$  
h_1 = E_{m_1}(IV)  
$$  
$$  
h(M) = E_{m_2}(h_1)  
$$

Per generare una collisione, si cerca un messaggio alternativo 
$M^{*} = (m_1^{*},\ m_2^{*})$ tale che:

$$
h(M) = h(M^{*})
$$

L’attaccante:

- genera $r_1$ varianti $m_1^{*}$ e calcola  
  $$
  h_1^{*} = E_{m_1^{*}}(IV)
  $$
- genera $r_2$ varianti $m_2^{*}$ e calcola  
  $$
  h_2^{*} = D_{m_2^{*}}(h(M))
  $$
- e cerca un **match** tra i due insiemi.

> L’attacco funziona anche con più round, purché la struttura sia concatenata in modo prevedibile.


---

### **7. Conclusione**

Abbiamo visto che le funzioni hash possono essere costruite secondo diversi **modelli strutturali**:

- **Parallelo**
    
- **Iterato (seriale)**
    
- **A cascata**
    
- **Basato su cifrari a blocchi**
    

Ogni modello presenta **vantaggi e vulnerabilità** specifiche.  
Gli attacchi _birthday_ e _meet in the middle_ mostrano che la **robustezza** di una funzione hash dipende fortemente **dalla funzione di compressione** e **dalla lunghezza del digest**.