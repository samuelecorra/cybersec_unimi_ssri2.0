## **Lezione 4: Crittoanalisi lineare e differenziale**

### **1. Attacchi sofisticati al DES**

Oltre agli attacchi a forza bruta, il DES è stato oggetto di due celebri forme di **crittoanalisi avanzata**, capaci di ridurre drasticamente lo spazio di ricerca:

- **Crittoanalisi differenziale** (Biham e Shamir, 1990)  
    → Attacco _chosen plaintext_, in grado di recuperare la chiave con circa  
    $2^{47}$ coppie di testi scelti.
    
- **Crittoanalisi lineare** (Matsui, 1993)  
    → Attacco _known plaintext_, che richiede circa  
    $2^{43}$ coppie di testi noti.
    

Entrambe hanno valore **teorico**: dimostrano vulnerabilità concettuali ma richiedono condizioni difficilmente realizzabili nella pratica.

---

### **2. Crittoanalisi differenziale**

#### **2.1 Concetto generale**

È un attacco di tipo **chosen plaintext**, basato sullo studio delle **differenze** tra coppie di testi in chiaro e i corrispondenti testi cifrati.

L’obiettivo è dedurre la chiave sfruttando il comportamento **non lineare** delle **S-box** del DES.

- Con poche iterazioni (es. 8 round) l’attacco può riuscire in pochi minuti.
    
- Per DES completo (16 round), richiede circa **$2^{47}$ coppie** di testi scelti → non praticabile.

![](imgs/Pasted%20image%2020260222112922.png)

---

#### **2.2 Idea di base**

Si analizzano due coppie di testi:

$$  
(x_1, y_1), \quad (x_2, y_2)  
$$

dove  
$y_1 = DES_k(x_1)$ e $y_2 = DES_k(x_2)$.

Si definiscono le **differenze**:

$$  
x' = x_1 \oplus x_2 \quad \text{e} \quad y' = y_1 \oplus y_2  
$$

Poiché la non linearità è concentrata nelle S-box, si studiano le differenze tra **input** e **output** di ciascuna S-box:

$$
\delta = B \oplus B', \quad \Delta = S(B) \oplus S(B')
$$


Si costruiscono così **tabelle di differenza** per ogni S-box, che indicano con quale probabilità un certo $\delta$ produce un certo $\Delta$.

---

#### **2.3 Analisi delle S-box**

Per ogni coppia di input $(B, B')$, la S-box genera due output $(S(B),\, S(B'))$.

![](imgs/Pasted%20image%2020260222112951.png)

Questa coppia è caratterizzata da:

- **Differenza in input:** $\delta = B \oplus B^*$
    
- **Differenza in output:** $\Delta = S(B) \oplus S(B^*)$

![](imgs/Pasted%20image%2020260222114727.png)

Conoscendo $\delta$ e $\Delta$, si possono costruire le **differential distribution tables (DDT)**, che contano quante coppie di input producono la stessa coppia di differenze $(\delta, \Delta)$

![](imgs/Pasted%20image%2020260222114807.png)

In particolare:

- $\delta$ **non dipende dalla chiave**;
    
- le differenze di output permettono di dedurre **vincoli sulla chiave**.

---

#### **2.4 Procedura**

1. Si sceglie una coppia di testi in chiaro con differenza nota $\delta$.
    
2. Si cifrano e si osserva la differenza $\Delta$ tra i due testi cifrati.
    
3. Si ottiene un insieme di possibili valori per alcuni bit della chiave ($K_1$).
    
4. Si ripete con altre coppie → si ottengono altri insiemi ($K_2$, $K_3$, …).
    
5. La **chiave corretta** appartiene all’intersezione di tutti gli insiemi trovati.

---

#### **2.5 Efficacia e limiti**

![](imgs/Pasted%20image%2020260222115030.png)

👉 Con **16 round**, l’attacco resta solo teorico, ma ha ispirato tecniche pratiche contro cifrari successivi.

---

### **3. Crittoanalisi lineare**

#### **3.1 Origine e principio**

Proposta da **Mitsuru Matsui (1993)**, è un attacco **known plaintext**.  
Richiede circa **$2^{47}$ testi noti** per ricostruire la chiave.

L’idea è **approssimare** la funzione non lineare del cifrario con una **funzione lineare**.

Si cerca cioè una relazione del tipo:

$$  
y = A x \oplus B k  
$$

che rappresenta una buona approssimazione della funzione reale:

$$  
y = f(x, k)  
$$

---

#### **3.2 Approssimazione lineare**

Una funzione booleana $f : \Sigma^n \to \Sigma$ può essere descritta dalla sua **tabella di verità**:

$$  
(f(a_0), f(a_1), \dots, f(a_{2^n - 1}))  
$$

Una funzione $h$ è **lineare** se:

$$  
h(s) = a_1 s_1 \oplus a_2 s_2 \oplus \dots \oplus a_n s_n  
$$

Un’approssimazione lineare $h^*$ è quella con **distanza minima** da $f$, cioè con il **minimo numero di differenze**.

---

#### **3.3 Approssimazione delle S-box**

Ogni S-box del DES realizza una trasformazione:

$$  
S : \Sigma^6 \to \Sigma^4  
$$

ossia da 6 bit in ingresso a 4 bit in uscita.

Può essere vista come 4 **funzioni booleane**:

$$  
S(s) = (f_4(s), f_3(s), f_2(s), f_1(s))  
$$

Per ciascuna S-box si costruisce una **tabella delle distanze**, dove:

- le **righe** rappresentano le 64 possibili funzioni lineari in input;
    
- le **colonne** rappresentano le 16 combinazioni lineari dei bit di output.
    

Esempio:

- Riga $h_{31} = 110001$ → $l(s) = s_6 \oplus s_5 \oplus s_1$
    
- Colonna $h_9 = 1001$ → $f = f_4 \oplus f_1$
    

	La tabella consente di identificare le **migliori approssimazioni lineari** (quelle con la più alta probabilità che $l(s) = f(s)$).

![](imgs/Pasted%20image%2020260222115603.png)

Data una funzione booleana e la sua approssimazione e d è il numero di valori in input per i quale la funzione differisce dalla sua approssimazione, posso valutare qual è la probabilità che l ed f siano uguali. Tale valore è dato da $(2^n - d) / 2^n$ ossia i casi favorevoli su tutti i casi possibili. Il caso peggiore si ha quando la migliore approssimazione l differisce di circa la metà per tutti i possibili valori, ovvero quando $d$ è circa $2^{n-1}$.

---

#### **3.4 Costruzione dell’attacco**

1. Si selezionano approssimazioni lineari per alcune S-box.
    
2. Si combinano tra loro per creare **relazioni lineari globali** tra bit di input, output e chiave.
    
3. Si raccolgono molte coppie _plaintext–ciphertext_ note.
    
4. Si calcola, per ogni possibile valore dei bit della chiave, quante volte la relazione lineare è verificata.
    
5. La chiave corretta è quella che massimizza la probabilità.
    

---

### **4. Confronto e risultati**

|Tipo di crittoanalisi|Autore|Anno|Tipo di attacco|Coppie richieste|Note|
|---|---|---|---|---|---|
|**Differenziale**|Biham & Shamir|1990|Chosen plaintext|$2^{47}$|Teorico, non pratico per 16 round|
|**Lineare**|Matsui|1993|Known plaintext|$2^{43}$–$2^{47}$|Più efficiente, richiede testi noti|

Entrambe le tecniche hanno influenzato **la progettazione dell’AES**, dove la resistenza a questi due attacchi è stata un requisito fondamentale.

---

### **5. Sintesi finale**

Abbiamo visto:

- Le due principali tecniche di **crittoanalisi avanzata** del DES:
    
    - **Differenziale**, basata sul confronto delle differenze (input/output);
        
    - **Lineare**, basata su approssimazioni statistiche delle S-box.
        
- Entrambe richiedono **grandi quantità di dati** e **risorse computazionali**, ma hanno avuto enorme impatto teorico.
    
- La **crittoanalisi lineare** rimane l’attacco _known plaintext_ più efficiente contro DES:  
    necessita di circa **$2^{43}$ coppie testo chiaro–testo cifrato**.