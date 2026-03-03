
## **Lezione 1: Codici MAC**

### **1. Introduzione**

I **Message Authentication Code (MAC)** sono funzioni crittografiche che permettono di verificare due proprietà fondamentali di un messaggio:

- **Autenticità:** il messaggio proviene realmente dal mittente che conosce la chiave segreta.
    
- **Integrità:** il messaggio non è stato modificato durante la trasmissione.
    

Un MAC è una funzione che prende in input un **messaggio $M$** e una **chiave segreta $K$**, restituendo un **valore di uscita $Y$** di lunghezza fissata:

$$  
Y = MAC(M, K)  
$$

Il valore $Y$ viene poi trasmesso insieme al messaggio come “etichetta di autenticazione”.

---

### **2. Funzionamento generale**

Il mittente (**Alice**) e il destinatario (**Bob**) condividono la stessa chiave segreta $K$.

1. Alice calcola il valore $Y = MAC(M, K)$
    
2. Invia a Bob la coppia $(M, Y)$
    
3. Bob, conoscendo $K$, ricalcola $MAC(M, K)$
    
4. Se il valore ottenuto coincide con $Y$, il messaggio è autentico e integro.
    

In caso di differenza, significa che il messaggio o il MAC sono stati alterati.

---

### **3. Proprietà fondamentali**

Un buon schema MAC deve soddisfare quattro proprietà essenziali:

1. **Easy computation**  
    Dato $M$ e $K$, il valore $MAC(M, K)$ deve essere **facile da calcolare**.
    
2. **Compression**  
    Il messaggio $M$ può avere lunghezza arbitraria, ma l’output del MAC deve essere **di lunghezza fissa** (es. 128 o 256 bit).
    
3. **Computation resistance**  
    Anche conoscendo alcune coppie $(M_i, MAC(M_i, K))$, deve essere **impossibile generare** il MAC di un nuovo messaggio $M_j \ne M_i$.
    
4. **Key non-recovery**  
    Anche conoscendo più coppie messaggio-MAC, non deve essere possibile **ricavare la chiave segreta $K$**.
    

---

### **4. MAC e confidenzialità**

Il MAC **non garantisce confidenzialità**, ma solo autenticità e integrità.  
Se si vuole proteggere anche il contenuto del messaggio, si deve combinare il MAC con una **cifratura** usando una seconda chiave $K'$:

Due approcci equivalenti:

$$  
E_{K'}(M, MAC(M, K))  
\quad \text{o} \quad  
(E_{K'}(M), MAC(E_{K'}(M), K))  
$$

Il primo cifra sia il messaggio che il suo MAC;  
il secondo cifra solo il messaggio e autentica il testo cifrato.

---

### **5. Sicurezza di un MAC**

La sicurezza di uno schema MAC dipende da due aspetti:

- **Il tipo di attacco** che l’avversario può condurre.
    
- **Lo scopo** che l’attacco vuole ottenere.
    

#### **Tipi di attacco**

1. **Known Message Attack:**  
    L’attaccante conosce una lista di messaggi e i relativi MAC.
    
2. **Chosen Message Attack:**  
    L’attaccante sceglie i messaggi e chiede al sistema di calcolare i rispettivi MAC.
    
3. **Adaptive Chosen Message Attack:**  
    Come il precedente, ma le scelte successive dipendono dalle risposte ottenute.
    

#### **Scopi dell’attacco**

1. **Total break:**  
    L’attaccante riesce a determinare la chiave $K$.
    
2. **Selective forgery:**  
    L’attaccante riesce a creare il MAC corretto per un messaggio scelto.
    
3. **Existential forgery:**  
    L’attaccante riesce a generare una qualunque coppia $(M, MAC(M, K))$ valida.
    

---

### **6. Ricerca esaustiva sulla chiave**

Dato un insieme di coppie $(M_i, y_i)$ con $y_i = MAC(M_i, K)$, l’avversario può tentare una **ricerca esaustiva** sulla chiave $K$.

Se $k$ è la lunghezza della chiave e $n$ la lunghezza dell’output del MAC:

- Provando tutte le $2^k$ chiavi sulla prima coppia $(M_1, y_1)$ si ottengono circa $2^{k-n}$ possibili chiavi.
    
- Applicando le coppie successive $(M_2, y_2), (M_3, y_3)...$ si riduce progressivamente il numero di candidati.
    

Esempio:

- $k = 80$ bit, $n = 32$ bit  
    → dopo 3 coppie si restringe a **una sola chiave corretta**.
    

---

### **7. Ricerca esaustiva sul valore del MAC**

Un secondo approccio è provare a indovinare direttamente il valore $y = MAC(M, K)$ senza conoscere la chiave.

- La probabilità di successo casuale è $1 / 2^n$.
    
- Lo sforzo richiesto è dell’ordine di $\min(2^k, 2^n)$ operazioni.
    

**Raccomandazione:**  
Scegliere parametri tali che $\min(k, n) \ge 128$ per evitare attacchi pratici.

---

### **8. Conclusioni**

Abbiamo visto che:

- un **MAC** serve per **autenticità** e **integrità**,
    
- richiede una **chiave segreta condivisa**,
    
- non offre **confidenzialità**,
    
- e la sua sicurezza dipende dalla **resistenza a forgery e attacchi esaustivi**.