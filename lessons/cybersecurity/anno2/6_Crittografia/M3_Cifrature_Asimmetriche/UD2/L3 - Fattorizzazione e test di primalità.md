# **Lezione 3: Fattorizzazione e test di primalità**

### **1. Infinità dei numeri primi**

Esistono **infiniti numeri primi**.  
La dimostrazione classica procede **per assurdo**:

- Supponiamo che esista un **massimo numero primo**, chiamato $p_{max}$.
    
- Consideriamo il numero:  
    $$  
    N = p_1 \cdot p_2 \cdot \dots \cdot p_{max} + 1  
    $$
    
- $N$ è maggiore di $p_{max}$, quindi dovrebbe essere **composto**.
    
- Tuttavia, **nessuno dei primi noti divide $N$**, poiché tutti lasciano resto $1$.
    
- Di conseguenza, $N$ deve essere **un nuovo numero primo**, contraddicendo l’ipotesi iniziale.
    

👉 **Conclusione:** i numeri primi sono infiniti.

---

### **2. Distribuzione dei numeri primi**

La funzione $\pi(x)$ indica il **numero di numeri primi minori o uguali a $x$**.

$$  
\pi(x) = \text{numero di primi in } [2, x]  
$$

#### **Teorema dei numeri primi**

Il **teorema dei numeri primi** afferma che $\pi(x)$ cresce asintoticamente come $\frac{x}{\ln x}$. In forma di limite:

$$
\lim_{x \to \infty} \frac{\pi(x)}{x / \ln x} = 1
$$

Questo significa che, per valori molto grandi di $x$, il rapporto tra il numero reale di primi fino a $x$ e la quantità $\frac{x}{\ln x}$ tende a $1$. Quindi $\frac{x}{\ln x}$ non dà esattamente il numero di primi, ma diventa una stima sempre più proporzionata al valore reale.

In notazione asintotica:

$$  
\pi(x) \sim \frac{x}{\ln x}  
$$

#### **Esempio numerico**

$$  
\pi(10^{10}) = 455{,}052{,}511 \  
\frac{10^{10}}{\ln 10^{10}} \approx 434{,}294{,}481.9  
$$

L’errore relativo è circa del **4%**, confermando l’accuratezza dell’approssimazione.

Per avere anche un controllo esplicito, non solo asintotico, vale il seguente limite superiore e inferiore: per ogni $x \ge 17$,

$$
\frac{x}{\ln x} < \pi(x) < 1.25506 \cdot \frac{x}{\ln x}
$$

Questa disuguaglianza dice che, da $17$ in poi, $\frac{x}{\ln x}$ è una stima per difetto del numero di primi fino a $x$, mentre $1.25506 \cdot \frac{x}{\ln x}$ fornisce una stima per eccesso. In altre parole, $\pi(x)$ resta intrappolata tra due quantità proporzionali a $\frac{x}{\ln x}$.

> ✅ Il teorema dei numeri primi descrive il comportamento limite di $\pi(x)$; la disuguaglianza per $x \ge 17$ dà invece un intervallo concreto entro cui si trova il numero reale di primi.

---

### **3. Probabilità che un numero casuale sia primo**

La **probabilità** che un intero casuale in $[2, x]$ sia primo è circa:  
$$  
P(\text{primo}) \approx \frac{1}{\ln x}  
$$

#### **Esempio 1 – Numeri di 512 bit**

- $\ln 2^{512} \approx 354.89$
    
- $P(\text{primo}) \approx \frac{1}{354.89}$
    
- In media, serve provare circa **355 numeri casuali** per trovarne uno primo.
    

Se si scelgono solo numeri **dispari**, i tentativi medi si dimezzano → circa **178 prove**.

La stessa stima si può rendere più precisa se non scegliamo un intero casuale generico fino a $2^{512}$, ma scegliamo un numero **dispari di esattamente 512 bit**, quindi nel range:

$$
[2^{511}, 2^{512}]
$$

Il numero stimato di primi in questo intervallo si ottiene sottraendo la stima dei primi fino a $2^{511}$ dalla stima dei primi fino a $2^{512}$:

$$
\frac{2^{512}}{\ln 2^{512}} - \frac{2^{511}}{\ln 2^{511}}
$$

Questa quantità va poi divisa per il numero di candidati dispari nel range. Nell’intervallo $[2^{511}, 2^{512}]$ ci sono circa $2^{511}$ interi, e circa metà sono dispari, quindi i candidati dispari sono circa:

$$
\frac{2^{511}}{2}
$$

La probabilità che un candidato dispari scelto in quel range sia primo è quindi:

$$
\frac{
\frac{2^{512}}{\ln 2^{512}} - \frac{2^{511}}{\ln 2^{511}}
}{
2^{511}/2
}
\approx
\frac{1}{177.79}
$$

Quindi servono in media circa **178 tentativi dispari** anche ragionando direttamente sul range dei numeri a 512 bit.

> 💡 Scegliere un numero dispari di 512 bit equivale a fissare il bit più significativo a $1$ per garantire la lunghezza, fissare il bit meno significativo a $1$ per garantire che sia dispari, e scegliere casualmente i **510 bit centrali**.

#### **Esempio 2 – Numeri di 1024 bit**

- $\ln 2^{1024} \approx 709.78$
    
- $P(\text{primo}) \approx \frac{1}{709.78}$
    
- In media servono circa **710 prove**, o **355** se si scelgono solo numeri dispari.

Per RSA questo caso è più interessante, perché spesso si scelgono primi grandi, ad esempio di **1024 bit**. Se scegliamo un intero casuale in:

$$
[2, 2^{1024}]
$$

la probabilità che sia primo è circa:

$$
\frac{1}{\ln 2^{1024}}
$$

e poiché:

$$
\ln 2^{1024} \approx 709.78
$$

servono in media circa **709.78 tentativi**. Se però scegliamo solo candidati dispari, scartiamo automaticamente tutti i numeri pari maggiori di $2$, che non possono essere primi. Per questo la media si dimezza:

$$
\frac{709.78}{2} \approx 354.89
$$

Ragionando direttamente sui numeri dispari di esattamente 1024 bit, il range corretto è:

$$
[2^{1023}, 2^{1024}]
$$

Il numero stimato di primi in questo intervallo è:

$$
\frac{2^{1024}}{\ln 2^{1024}} - \frac{2^{1023}}{\ln 2^{1023}}
$$

I candidati dispari nel range sono circa metà degli interi dell’intervallo, quindi:

$$
\frac{2^{1023}}{2}
$$

La probabilità stimata diventa:

$$
\frac{
\frac{2^{1024}}{\ln 2^{1024}} - \frac{2^{1023}}{\ln 2^{1023}}
}{
2^{1023}/2
}
\approx
\frac{1}{355.23}
$$

Quindi, scegliendo dispari nel range dei numeri a 1024 bit, servono in media circa **355 tentativi** per trovare un primo.

> 💡 Un candidato dispari di 1024 bit ha il bit più significativo fissato a $1$, per essere davvero lungo 1024 bit, e il bit meno significativo fissato a $1$, per essere dispari. Restano quindi **1022 bit centrali** scelti casualmente.


---

### **4. Test di primalità**

I **test di primalità** servono a determinare se un numero $n$ è **primo o composto**.

Esistono due categorie principali:

#### **• Test deterministici**

- Forniscono un **risultato certo** (nessuna probabilità di errore).
    
- Storicamente molto lenti: fino al 2002 il miglior algoritmo era una variante di **Cohen–Lenstra** basata su **Adleman et al. (1983)**, con complessità:  
    $$  
    O((\ln p)^{\log \log \log p})  
    $$
    
- Nel **2002**, **Agrawal, Kayal e Saxena (AKS)** scoprirono un algoritmo **deterministico polinomiale**:  
    $$  
    O((\ln p)^{6 + \varepsilon})  
    $$
    

#### **• Test probabilistici**

- Forniscono un risultato **probabilistico**:
    
    - "Probabilmente primo"
        
    - "Certamente composto"
        
- Possiedono una **probabilità di errore ≤ (1/2)**.
    
- Ripetendo il test $t$ volte, la probabilità di errore diventa:  
    $$  
    P_{errore} \le \left(\frac{1}{2}\right)^t  
    $$
    

---

### **5. Principali test probabilistici**

#### **Test di Solovay–Strassen (1977)**

- Basato sul criterio di **Euler–Jacobi**.
    
- Probabilità di errore ≤ $(1/2)^t$.
    
- Poco usato oggi, sostituito da test più efficienti.
    

#### **Test di Miller–Rabin**

- È **il più veloce e utilizzato nella pratica**.
    
- Probabilità di errore ≤ $(1/4)^t$.
    
- Basato sul **Teorema di Fermat**:  
    se $p$ è primo e $a \in Z_p^*$, allora  
    $$  
    a^{p-1} \equiv 1 \pmod{p}  
    $$
    

---

### **6. Algoritmo di Miller–Rabin**

> 💡 **L'idea di fondo, prima delle formule.** Il test di Fermat (sezione 5) dice solo: «se $p$ è primo, allora $a^{p-1} \equiv 1$». Miller–Rabin **non si accontenta** di arrivare a $1$ alla fine: controlla *come* ci si arriva, guardando la "strada" dei quadrati successivi. Il trucco è spezzare l'esponente $p-1$ per poter osservare i passaggi intermedi, ed è proprio quello che fanno i passi qui sotto.

Sia $p$ un numero dispari (lo assumiamo dispari perché i pari diversi da $2$ non sono mai primi: non avrebbe senso testarli).

1. Si scrive:  
    $$  
    p - 1 = 2^k \cdot q \quad \text{con } q \text{ dispari e } k > 0  
    $$
    
    > 📌 **Perché questa scrittura è sempre possibile?** Dato che $p$ è dispari, $p-1$ è **sicuramente pari**. Ogni numero pari si può esprimere come una potenza di $2$ (con esponente $k$) moltiplicata per una parte dispari $q$: basta continuare a dividere per $2$ finché si può, e $k$ conta *quante volte* riusciamo a dividere. Ci conviene farlo perché in questo modo isoliamo da una parte tutta la "parte pari" ($2^k$) e dall'altra il nocciolo dispari ($q$): l'esponente dispari $q$ sarà il punto di partenza, mentre i $k$ fattori $2$ diventeranno i $k$ elevamenti al quadrato che eseguiremo uno dopo l'altro.
    >
    > *Esempio:* se $p = 29$, allora $p-1 = 28 = 4 \cdot 7 = 2^2 \cdot 7$, quindi $k = 2$ e $q = 7$.
    
2. Si sceglie un $a$ casuale con $1 < a < p - 1$
    
    > 💡 Questo $a$ è il nostro **"interrogato"**: lo usiamo come testimone per mettere alla prova $p$. Lo prendiamo strettamente tra $1$ e $p-1$ perché $a = 1$ e $a = p-1 \equiv -1$ darebbero sempre risposta "primo" qualunque sia $p$, quindi sarebbero testimoni inutili (non distinguono primi da composti).
    
3. Si calcolano i seguenti valori:  
    $$  
    a^q, \ a^{2q}, \ a^{4q}, \ \dots, \ a^{2^k q} \pmod{p}  
    $$
    
    > 📌 **Cosa stiamo davvero calcolando.** Notare che ogni termine è il **quadrato del precedente**: $a^{2q} = (a^q)^2$, $a^{4q} = (a^{2q})^2$, e così via. Quindi non serve ricominciare da capo ogni volta: si parte da $a^q$ e si eleva ripetutamente al quadrato, $k$ volte. L'ultimo termine è $a^{2^k q} = a^{p-1}$, cioè **esattamente** la quantità del test di Fermat. In pratica stiamo ricostruendo $a^{p-1}$ passo dopo passo, e ci fermiamo a *spiare* ogni gradino intermedio invece di guardare solo il risultato finale.
    
4. Se:
    
    - $a^q \equiv 1 \pmod{p}$ oppure
        
    - $\exists j \in [0, k-1] \text{ tale che } a^{2^j q} \equiv -1 \pmod{p}$  
        → **$p$ è probabilmente primo.**
        
    > 💡 **Da dove escono queste due condizioni?** Se $p$ è primo, l'ultimo valore $a^{p-1}$ vale $1$ (Fermat). Ma $1$ è un quadrato: l'unico passo che può aver prodotto $1$ elevando al quadrato un valore precedente è un valore la cui radice quadrata (modulo $p$) sia $+1$ oppure $-1$. Infatti, quando $p$ è primo, le **uniche radici quadrate di $1$** sono $+1$ e $-1$ (è questo il fatto chiave che i composti violano). Quindi, risalendo la catena dei quadrati, o partiamo già da $a^q \equiv 1$, oppure prima o poi incontriamo un $-1$ che, elevato al quadrato, genera il $1$ finale. Trovare uno di questi due "segnali" è coerente con $p$ primo.
    
5. In caso contrario → **$p$ è composto.**
    
    > ⚠️ **Perché "in caso contrario" significa composto (e non solo "boh").** Se la catena arriva a $1$ **senza** essere mai passata per $-1$ e **senza** partire da $a^q \equiv 1$, allora abbiamo trovato una radice quadrata di $1$ diversa da $\pm 1$. Per un numero primo questo è **impossibile**: solo i numeri composti ammettono "radici quadrate non banali di $1$". Quindi quel $a$ è una prova schiacciante (un *witness*, vedi sezione 7) che $p$ **non** è primo. Da qui l'asimmetria del test: "composto" è una **certezza**, "primo" è solo "probabilmente".
    
    > 💡 **Perché allora $(1/4)^t$ e non $(1/2)^t$?** Quando $p$ è composto, *almeno tre quarti* dei possibili $a$ sono witness (sezione 7): scegliendo $a$ a caso, la probabilità di essere ingannati da un singolo test è al più $1/4$. Ripetendo il test $t$ volte con $a$ indipendenti, la probabilità di sbagliare $t$ volte di fila crolla a $(1/4)^t$. È questo che rende Miller–Rabin più affidabile, a parità di ripetizioni, di Solovay–Strassen.

---

### **7. Insieme dei witness di Miller–Rabin**

Definiamo l’insieme dei **witness** (testimoni di compositezza):

$$
W_{MR}(p) = \left\{ 
\, a \;:\; a^q \not\equiv 1 \pmod{p} \;\text{ e }\; 
a^{2^j q} \not\equiv -1 \pmod{p}, \;
\forall j \in [0,\, k-1]
\,\right\}
$$


- Se $p$ è **primo**, allora $W_{MR}(p)$ è **vuoto**.
    
- Se $p$ è **composto**, allora:  
    $$  
    |W_{MR}(p)| \ge \frac{3}{4} \cdot \varphi(p)  
    $$
    

---

### **8. Esempio 1 – Miller–Rabin con p = 29**

$$  
p - 1 = 2^2 \cdot 7  
$$

Quindi:  
$$  
W_{MR}(29) = { a : a^7 \not\equiv 1 \pmod{29}, \ a^{2^j \cdot 7} \not\equiv -1 \pmod{29} }  
$$

- Per $a = 10$:  
    $$  
    10^7 \equiv 17 \pmod{29}, \quad (10^7)^2 \equiv 28 \equiv -1 \pmod{29}  
    $$  
    → output: **“primo”**
    
- Per $a = 2$:  
    $$  
    2^7 \equiv 12 \pmod{29}, \quad (2^7)^2 \equiv 28 \equiv -1 \pmod{29}  
    $$  
    → output: **“primo”**
    

Provando per tutti $a \in [1, 28]$ il risultato è sempre “primo”.

---

### **9. Esempio 2 – Miller–Rabin con p = 221**

$$  
p - 1 = 2^2 \cdot 55  
$$

$$  
W_{MR}(221) = { a : a^{55} \not\equiv 1 \pmod{221}, \ a^{2^j \cdot 55} \not\equiv -1 \pmod{221} }  
$$

- Per $a = 5$:  
    $$  
    5^{55} \equiv 112 \pmod{221}, \quad (5^{55})^2 \equiv 168 \pmod{221}  
    $$  
    → output: **“composto”**
    
- Per $a = 21$:  
    $$  
    21^{55} \equiv 200 \pmod{221}, \quad (21^{55})^2 \equiv 220 \equiv -1 \pmod{221}  
    $$  
    → output: **“primo”**
    

---

### **10. Sintesi finale**

In questa lezione abbiamo visto:

- La **distribuzione dei numeri primi** e il motivo per cui sono infiniti.
    
- I **test di primalità**, sia deterministici (come AKS) che probabilistici.
    
- Il **test di Miller–Rabin**, oggi il più usato nella pratica, grazie alla sua **velocità e affidabilità statistica**.
    

**Concetti chiave:**

- I numeri primi sono infiniti ma distribuiti in modo irregolare.
    
- La generazione di grandi primi (512 o 1024 bit) è alla base della sicurezza RSA.
    
- I test di primalità probabilistici sono fondamentali per generare rapidamente tali numeri.
