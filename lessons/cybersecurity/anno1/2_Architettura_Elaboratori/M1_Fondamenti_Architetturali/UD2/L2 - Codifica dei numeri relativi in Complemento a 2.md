## ***Lezione 2: Codifica dei numeri relativi in Complemento a 2***

---

> 📌 Questa lezione rielabora integralmente le pagine 31–42 di `M1doc.pdf`.

### **1. Il problema fondamentale: rappresentare numeri negativi in binario**

Fino a questo punto abbiamo rappresentato:

- numeri **interi positivi** in binario
    
- usando **notazione posizionale**
    

Ma un computer deve gestire anche:

- temperature sotto zero
    
- conti bancari in rosso
    
- coordinate negative
    
- spostamenti verso sinistra
    
- differenze tra quantità
    

Quindi servono **numeri con segno**.

Il primo tentativo naturale è il sistema **modulo e segno**:

- 1 bit per il segno
    
- $b-1$ bit per il valore assoluto
    

Ma questo sistema ha due limiti strutturali:

1. **Doppia rappresentazione dello zero**  
    +0 e −0 sono codifiche diverse → ambiguità matematica.
    
2. **Somma e sottrazione NON sono uniformi**  
    Serve controllare i segni:
    
    - concordi → somma
        
    - discordi → sottrazione  
        Per implementarle servono selezione dell’operazione, confronti e circuiti più complessi.
        

Per l’hardware è preferibile una codifica che renda l’operazione uniforme e consenta di riutilizzare lo stesso sommatore binario.

- semplice
    
- veloce
    
- uniforme
    
- parallelo
    

---

### **2. La soluzione: il complemento a 2**

#### **2.1. Definizione operativa**

Dato un numero binario $x$ su $b$ bit, il suo **negativo in complemento a 2** si ottiene così:

1. **Inverti tutti i bit** (complemento a 1)
    
2. **Aggiungi 1**
    

Formalmente:

$$  
-x = \overline{x} + 1  
$$

Il risultato va sempre calcolato mantenendo la stessa larghezza $b$: un eventuale riporto oltre l’MSB viene scartato.

---

### **3. Fondamento matematico: aritmetica modulare**

Con $b$ bit, i registri lavorano **modulo $2^b$**:

$$  
\mathbb{Z}_{2^b}=\{0,1,2,\dots,2^b-1\}
$$

In questo sistema:

$$  
-n \equiv 2^b - n \pmod{2^b}  
$$

Per una parola di $b$ bit, la configurazione di tutti $1$ vale $2^b-1$. Sottrarre $n$ da tale configurazione equivale a invertirne i bit, quindi:

- $2^b - 1$ = tutti 1 in binario
    
- quindi:
    

$$  
2^b - n = (\overline{n}) + 1  
$$

Quindi il **complemento a 2 realizza il negativo additivo nell’aritmetica modulo $2^b$**.

> 💡 Immagina le $2^b$ configurazioni disposte su un anello: dopo $2^b-1$ si ritorna a $0$. Le configurazioni della metà superiore vengono interpretate come i numeri negativi che precedono lo zero.

#### **3.1. L’anello delle configurazioni su 4 bit**

| configurazione | valore senza segno | valore in complemento a 2 |
| -------------- | -----------------: | ------------------------: |
| `0000`–`0111`  |            $0$–$7$ |                   $0$–$7$ |
| `1000`         |                $8$ |                      $-8$ |
| `1001`         |                $9$ |                      $-7$ |
| `1010`         |               $10$ |                      $-6$ |
| `1011`         |               $11$ |                      $-5$ |
| `1100`         |               $12$ |                      $-4$ |
| `1101`         |               $13$ |                      $-3$ |
| `1110`         |               $14$ |                      $-2$ |
| `1111`         |               $15$ |                      $-1$ |

---

### **4. Interpretazione e decodifica**

Per un numero binario $x = b_{b-1} b_{b-2}\dots b_0$ su $b$ bit:

$$  
\text{valore}(x) = - b_{b-1}\cdot 2^{b-1} + \sum_{i=0}^{b-2} b_i \cdot 2^i  
$$

Il **bit più significativo (MSB)** ha **peso negativo**.

Esistono dunque due metodi equivalenti per decodificare una parola:

1. applicare direttamente la formula dei pesi, con peso $-2^{b-1}$ per l’MSB;
2. se l’MSB è $1$, calcolare inverti-più-uno sul pattern e anteporre il segno meno.

Per `1101` su 4 bit:

$$
-1\cdot2^3+1\cdot2^2+0\cdot2+1=-8+4+1=-3.
$$

Con l’altro metodo, `1101` $\to$ `0010` $+1=$ `0011`, dunque il modulo è $3$ e il valore è $-3$.

---

### **5. Il MSB indica il segno**

Nel complemento a 2:

|MSB|Significato|
|---|---|
|0|Numero positivo o zero|
|1|Numero negativo|

Questo vale **per qualsiasi numero di bit**.

#### **5.1. Esempi su 4 bit**

- `0101` → MSB = 0 → $+5$
    
- `1011` → MSB = 1 → negativo  
    complemento a 2:
    
    - inverti → `0100`
        
    - +1 → `0101` → $5$  
        → valore = $-5$
        

---

### **6. Intervallo rappresentabile non simmetrico**

Con $b$ bit in complemento a 2:

$$  
-2^{b-1} \le n \le 2^{b-1}-1  
$$

Esempio con 4 bit:

$$  
-8 \le n \le +7  
$$

|Binario|Valore|
|---|---|
|0000|0|
|0111|+7|
|1000|−8|
|1111|−1|

#### **6.1. Perché non è simmetrico**

Perché:

- i negativi sono $2^{b-1}$
    
- i non-negativi sono $2^{b-1}$
    
- ma **lo zero è fra i non negativi**  
    → c’è **un negativo in più**
    

---

### **7. Proprietà strutturali fondamentali**

#### **7.1. Uno e un solo zero**

Niente +0 / −0.  
Lo zero è unico: `000…000`

---

#### **7.2. Somma e sottrazione unificate**

Nel complemento a 2:

- **sottrarre = sommare l’opposto**
    
- l’ALU fa **solo somme binarie**
    

$$  
A - B = A + (-B)  
$$

E $-B$ si ottiene con:

$$  
\overline{B} + 1  
$$

---

#### **7.3. Stesso sommatore per pattern con o senza segno**

La rete di somma dell’ALU combina i bit allo stesso modo. È però la **semantica scelta dal programma** a stabilire se quei bit vadano letti con o senza segno, e di conseguenza quale flag di overflow controllare.

#### **7.4. Esempi di somma su 4 bit**

**Due positivi senza overflow:**

$$
0101\ (+5)+0010\ (+2)=0111\ (+7).
$$

**Due negativi senza overflow:**

$$
1100\ (-4)+1110\ (-2)=1\,1010\ \longrightarrow\ 1010\ (-6).
$$

Il riporto esterno viene scartato e `1010` vale $-6$.

**Segni diversi:**

$$
0101\ (+5)+1010\ (-6)=1111\ (-1).
$$

Con operandi di segno diverso non può verificarsi overflow nella somma: il risultato matematico rimane compreso tra i due operandi.

#### **7.5. Negazione e sottrazione svolte**

Per negare $+5$ su 4 bit:

$$
0101\longrightarrow1010\longrightarrow1011=-5.
$$

Per negare $-3$:

$$
1101\longrightarrow0010\longrightarrow0011=+3.
$$

La sottrazione si riconduce quindi a

$$
A-B=A+(\overline{B}+1).
$$

> ⚠️ Il minimo rappresentabile, `1000` su 4 bit, è $-8$ e non possiede l’opposto $+8$ nella stessa larghezza. Inverti-più-uno restituisce ancora `1000`: la negazione causa overflow.

---

### **8. Riporto e overflow: due fenomeni distinti**

#### **8.1. Carry out**

È il **riporto oltre il MSB**:

- è il bit prodotto oltre la larghezza fissata,
    
- nasce dalla somma binaria pura
    
- segnala overflow nella lettura **senza segno**,
    
- nella lettura con segno non determina da solo l’overflow.
    

Motivo:

L’aritmetica è **modulare modulo $2^b$**.  
Il carry out indica solo che hai fatto “un giro completo dell’anello”.

---

#### **8.2. Overflow con segno**

Si verifica quando il risultato matematico non appartiene all’intervallo rappresentabile con $b$ bit.

Si verifica **solo se**:

1. Gli operandi hanno **stesso segno**
    
2. Il risultato ha **segno opposto**
    

Formalmente:

- $A>0$, $B>0$ e $A+B<0$ → overflow positivo
    
- $A<0$, $B<0$ e il pattern prodotto viene interpretato come non negativo → overflow nella direzione negativa.
    

Regola pratica hardware:

Basta confrontare i **tre MSB**:

- MSB(A)
    
- MSB(B)
    
- MSB(Risultato)
    

Se i primi due sono uguali e il terzo è diverso → **overflow**

Esempi del PDF su 4 bit:

$$
0101\ (+5)+0110\ (+6)=1011,
$$

ma $+11$ non è rappresentabile e `1011` verrebbe letto erroneamente come $-5$;

$$
1100\ (-4)+1001\ (-7)=1\,0101\ \longrightarrow\ 0101,
$$

ma $-11$ non è rappresentabile e `0101` verrebbe letto come $+5$. Il carry out presente nel secondo caso dimostra che il solo riporto non è il criterio dell’overflow con segno.

---

### **9. Overflow nella direzione negativa**

Accade quando:

- sommi due numeri negativi
    
- ottieni un risultato positivo
    

Nel contesto degli interi a complemento a 2 è più preciso chiamarlo **overflow con segno nella direzione negativa**. Il termine *underflow* è usato soprattutto per la virgola mobile, quando un valore non nullo è troppo vicino a zero per essere rappresentato normalmente.

---

### **10. Estensione del segno**

Se passi da $b$ bit a $k>b$ bit:

- **ripeti il MSB**
    

|Numero|4 bit|8 bit|
|---|---|---|
|+5|0101|00000101|
|−3|1101|11111101|

#### **10.1. Perché funziona**

Perché il MSB rappresenta il **peso negativo dominante**.  
Ripeterlo **preserva il valore matematico**.

Se il bit di segno è $0$, gli zeri aggiunti hanno peso nullo. Se è $1$, su $k$ bit i nuovi pesi negativi e positivi si compensano:

$$
-2^{k-1}+\sum_{i=b-1}^{k-2}2^i=-2^{b-1}.
$$

I bit originari di peso inferiore restano invariati, quindi resta invariato anche il valore complessivo.

---

### **11. Perché il complemento a 2 è adatto all’hardware**

|Motivo|Effetto|
|---|---|
|Unico zero|niente ambiguità|
|Somma unica|stessa ALU per + e −|
|MSB = segno|decodifica istantanea|
|Aritmetica modulare|il carry out viene scartato dalla parola; non è il criterio dell’overflow con segno|
|Overflow rilevabile logicamente|confronto MSB|
|Sign extension semplice|solo duplicazione bit|

Somma e sottrazione possono quindi condividere gran parte della stessa rete aritmetica.

---

### **12. Sintesi finale**

Il **complemento a 2** è:

- una **rappresentazione matematica**
    
- una **codifica binaria**
    
- una **implementazione hardware**
    
- una **aritmetica modulare**
    
- una **teoria dei segni**
    
- una **semantica del calcolo**
    

tutto **in un unico schema coerente**.

---

> ✅ Su $b$ bit il complemento a 2 rappresenta l’intervallo $[-2^{b-1},2^{b-1}-1]$, ha un solo zero e permette di eseguire la sottrazione sommando l’opposto. Carry out e overflow con segno devono essere controllati con criteri diversi.
