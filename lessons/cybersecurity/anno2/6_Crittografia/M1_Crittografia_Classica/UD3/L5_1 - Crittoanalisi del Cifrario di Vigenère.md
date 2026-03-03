# **Lezione 5: Crittoanalisi del Cifrario di Vigenère (Parte 1)**

### **1. Introduzione**

Il **Cifrario di Vigenère** è stato a lungo considerato **invincibile**, tanto da essere definito _“le chiffre indéchiffrable”_ (“il cifrario indecifrabile”).  
Tuttavia, a partire dall’Ottocento, alcuni studiosi riuscirono a **svelarne la debolezza strutturale**.

Il suo punto vulnerabile risiede nella **ripetizione periodica della chiave**, che produce **schemi regolari** all’interno del testo cifrato.  
Analizzando tali schemi, è possibile **determinare la lunghezza della chiave** e, successivamente, **risalire ai singoli alfabeti** utilizzati.

---

### **2. Prime analisi storiche**

I primi studiosi a tentare la crittoanalisi di Vigenère furono:

- **Charles Babbage (1834)**
    
- **Friedrich Kasiski (1863)**
    

Essi introdussero i due metodi fondamentali per attaccare il cifrario:

1. **Test di Kasiski**, per individuare la **lunghezza della chiave** $t$;
    
2. **Analisi delle frequenze** in ciascun alfabeto cifrante, una volta noto $t$.
    

---

### **3. Test di Kasiski**

Il metodo di Kasiski si basa sull’**osservazione delle ripetizioni** all’interno del testo cifrato.

#### **Principio**

Se una **sequenza di lettere** (ad esempio “XFG”) compare più volte nel testo cifrato, significa che:

- molto probabilmente corrisponde allo **stesso gruppo di lettere** del testo in chiaro,
    
- e che è stata cifrata con **la stessa parte di chiave** (cioè a distanza multipla di $t$).

![](imgs/Pasted%20image%2020260221202514.png)

#### **Procedura**

1. Si individuano tutte le **ripetizioni di 3 lettere** nel testo cifrato.
    
2. Si calcola la **distanza** (in caratteri) tra le occorrenze successive: $d_1, d_2, …, d_h$.
    
3. Si calcola il **massimo comune divisore**:
    

$$  
t \approx \gcd(d_1, d_2, …, d_h)  
$$

Il valore ottenuto è **un multiplo della lunghezza della chiave** $t$.

💡 Il test di Kasiski consente solo di **stimare la lunghezza della chiave**, ma non di determinarne i caratteri.

---

### **4. Tecniche statistiche complementari**

Dopo Kasiski, la crittoanalisi di Vigenère si è evoluta grazie a **tecniche basate su statistiche** del testo cifrato.

Due indicatori principali vengono utilizzati:

1. **Indice di coincidenza (IC)** → per determinare la **lunghezza della chiave**.
    
2. **Indice mutuo di coincidenza (MIC)** → per determinare i **caratteri della chiave**.
    

---

### **5. L’indice di coincidenza (IC)**

Definito da **Wolfe Friedman (1920)**, l’**indice di coincidenza** misura quanto le frequenze delle lettere **deviino dalla distribuzione uniforme**.

È definito come la **probabilità che due lettere scelte a caso** in un testo siano **uguali**.


Dato un testo $x_1x_2...x_n$, con $f_i$ = frequenza del carattere $i$-esimo:

$$  
IC(x_1x_2...x_n) =
\frac{\displaystyle \sum_{i=0}^{25} \binom{f_i}{2}}
{\displaystyle \binom{n}{2}}
=
\frac{\sum_{i=0}^{25} f_i (f_i - 1)}  
{n (n - 1)}  
$$

- Il numeratore rappresenta il **numero di coppie di lettere identiche**.
    
- Il denominatore rappresenta il **numero totale di coppie possibili**.
    

---

### **6. Esempi numerici**

Calcoliamo $IC$ per alcune parole brevi (solo per intuire l’ordine di grandezza):

| Testo | Lunghezza $n$ | Coppie uguali | IC                                |
| ----- | ------------- | ------------- | --------------------------------- |
| MONO  | 4             | 2             | $2/4(4-1)=2/12=1/6 \approx 0.166$ |
| ALFA  | 4             | 2             | $1/6 \approx 0.166$               |
| GAMMA | 5             | 4             | $4/20 = 0.20$                     |

💡 In testi reali, l’IC tende a valori molto più bassi, attorno a 0.07.

---

### **7. IC per lingue naturali**

L’**indice di coincidenza medio** varia a seconda della lingua.

#### **In inglese**

Le probabilità medie $p_i$ delle lettere danno:

![](imgs/Pasted%20image%2020260221204153.png)

$$  
IC_{English} \approx \sum_{i=0}^{25} p_i^2 = 0.065  
$$

Se invece le lettere fossero **casuali e uniformi**:

$$  
IC_{random} = \frac{1}{26} = 0.038  
$$

#### **In italiano**

Analogamente:

![](imgs/Pasted%20image%2020260221204208.png)

$$  
IC_{Italian} \approx 0.075  
$$

➡️ Questi valori di riferimento sono fondamentali per distinguere un testo **cifrato con cifrario monoalfabetico**, da uno **polialfabetico** o **casuale**.

![](imgs/Pasted%20image%2020260221204342.png)

Quello sopra è probabilmente inglese, quello sotto italiano:

![](imgs/Pasted%20image%2020260221204400.png)

---

### **8. Applicazione pratica dell’IC**

Però, poiché per una stringa totalmente random con valore 0.038 non abbiamo molti indizi, come si fa?

![](imgs/Pasted%20image%2020260221204749.png)

Si predilige partire con lo scoprire quanto è lunga la chiave...

Supponiamo di avere un testo cifrato e di voler determinare se la chiave è lunga 1, 2, 3, 4, 5, ecc.

Si procede così:

1. Si scrive il testo cifrato.
    
2. Si costruiscono $t$ **sottosequenze** formate dalle lettere cifrate con la stessa chiave.
    
3. Si calcola l’IC di ciascuna sottosequenza.

Esempio:

![](imgs/Pasted%20image%2020260221204825.png)

💡 Poiché per $t=5$ l’indice si avvicina a 0.075 (tipico dell’italiano), **la lunghezza stimata della chiave è 5**.

---

### **9. Interpretazione dell’indice**

- Se $IC \approx 0.038$ → testo completamente casuale.
    
- Se $IC \approx 0.065$ → testo cifrato monoalfabetico o lingua inglese.
    
- Se $IC$ oscilla tra questi valori → testo cifrato con Vigenère a chiave multipla.
    

⚠️ Il risultato dell’IC è **una stima** di $t$ e può variare con la lunghezza del testo.

---

### **10. In sintesi**

In questa prima parte della lezione abbiamo analizzato:

- le **basi della crittoanalisi di Vigenère**,
    
- il **test di Kasiski**, per stimare la lunghezza della chiave,
    
- l’**indice di coincidenza**, per confermare la periodicità del cifrario.
    

Nella prossima lezione vedremo:

- come determinare i **singoli caratteri della chiave**,
    
- e come applicare l’**indice mutuo di coincidenza** per la decifrazione completa.