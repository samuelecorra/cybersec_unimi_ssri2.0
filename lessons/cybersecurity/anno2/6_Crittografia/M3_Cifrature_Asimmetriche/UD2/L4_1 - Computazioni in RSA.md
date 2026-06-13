# **Lezione 4: Computazioni in RSA (parte 1)**

### **1. Operazioni necessarie per l’algoritmo RSA**

Per eseguire l’algoritmo **RSA** è necessario saper effettuare alcune **operazioni fondamentali**:

- **Elevazione a potenza modulare**
    
- **Generazione di numeri primi**
    
- **Scelta dell’esponente pubblico $e$**
    
- **Calcolo dell’esponente privato $d$**
    

Il legame tra le due chiavi è:

$$  
d \leftarrow e^{-1} \bmod \big( (p-1)(q-1) \big)  
$$

---

### **2. Elevazione a potenza modulare**

L’obiettivo è calcolare:

$$  
x^y \bmod z  
$$

Tre metodi principali:

1. **Naive**
    
2. **Left-to-right**
    
3. **Right-to-left**
    

---

### **3. Metodo naïve**

> 💡 **L’idea di fondo.** $x^y$ non è altro che $x$ moltiplicato per sé stesso $y$ volte: $x^y = \underbrace{x \cdot x \cdot x \cdots x}_{y \text{ volte}}$. L’algoritmo naïve fa esattamente questo: parte da $a = 1$ e moltiplica per $x$ un passo alla volta, riducendo modulo $z$ ad ogni iterazione per tenere i numeri piccoli.

```plaintext
Potenza_Modulare_naive(x, y, z)
  a ← 1
  for i = 1 to y do
      a ← (a · x) mod z
  return a
```

> 📌 **Perché ridurre modulo $z$ ad ogni passo e non solo alla fine?** Perché i numeri crescerebbero in modo ingestibile: dopo $k$ moltiplicazioni avremmo $x^k$, che può essere enorme. Ridurre ad ogni passo mantiene $a < z$, sfruttando il fatto che $(a \cdot x) \bmod z = ((a \bmod z) \cdot (x \bmod z)) \bmod z$.

#### **Limite**

> ⚠️ Il numero di iterazioni del ciclo è esattamente $y$. Se $y$ è un numero di **512 bit**, allora $y \approx 2^{512}$: occorrerebbero circa $2^{512}$ moltiplicazioni, un numero astronomicamente grande che nessun computer potrà mai eseguire. La complessità cresce **esponenzialmente** con la lunghezza in bit dell’esponente, rendendo questo approccio completamente inutilizzabile in crittografia.

---

### **4. Metodo left-to-right**

> 💡 **L’idea chiave.** Invece di moltiplicare $x$ per sé stesso $y$ volte, si sfrutta la **rappresentazione binaria** di $y$ per ridurre drasticamente il numero di operazioni. Il trucco sta nel riscrivere $y$ con lo **schema di Horner** — un modo di raccogliere i fattori 2 in maniera nidificata — così da trasformare il calcolo di $x^y$ in una sequenza di soli **quadrati e moltiplicazioni per $x$**.

**Passo 1 — scrittura binaria di $y$:**

$$
y = y_0 \cdot 2^0 + y_1 \cdot 2^1 + y_2 \cdot 2^2 + \dots + y_t \cdot 2^t
$$

dove ogni $y_i \in \{0, 1\}$ è il bit di posizione $i$ (con $y_0$ il bit meno significativo e $y_t$ il più significativo).

**Passo 2 — schema di Horner (raccoglimento iterativo del fattore 2):**

A partire dalla somma binaria, raccogliamo il fattore $2$ dall’esterno verso l’interno, un livello alla volta:

$$
y = y_0 + 2\bigl(y_1 + 2\bigl(y_2 + \dots + 2(y_{t-1} + 2\,y_t)\dots\bigr)\bigr)
$$

> 📌 **Come si ottiene questa riscrittura?** Si parte da $y = y_0 + 2y_1 + 4y_2 + 8y_3 + \ldots$ e si raccoglie il $2$ da tutti i termini tranne il primo: $y = y_0 + 2(y_1 + 2y_2 + 4y_3 + \ldots)$. Poi si raccoglie di nuovo il $2$ dal gruppo interno, e così via. È esattamente il metodo di Horner applicato alla base $2$: invece di calcolare le potenze di $2$ esplicitamente, le si accorpa in parentesi nidificate.

#### **Esempio numerico con 40**

Rappresentazione binaria:

$$
40 = 0 \cdot 2^0 + 0 \cdot 2^1 + 0 \cdot 2^2 + 1 \cdot 2^3 + 0 \cdot 2^4 + 1 \cdot 2^5
$$

quindi i bit, dall’LSB all’MSB, sono $y_0=0,\, y_1=0,\, y_2=0,\, y_3=1,\, y_4=0,\, y_5=1$.

Applicando Horner raccogliamo il fattore $2$ iterativamente:

$$
40 = 0 + 2\bigl(0 + 2\bigl(0 + 2\bigl(1 + 2(0 + 2 \cdot 1)\bigr)\bigr)\bigr)
$$

Verifica dall’interno verso l’esterno: $0 + 2 \cdot 1 = 2$; poi $1 + 2 \cdot 2 = 5$; poi $0 + 2 \cdot 5 = 10$; poi $0 + 2 \cdot 10 = 20$; poi $0 + 2 \cdot 20 = 40$. ✓

**Passo 3 — da $y$ nidificato a $x^y$ nidificato:**

Ora sostituiamo $y$ con la sua forma di Horner nell’esponente di $x$. Usando la regola $x^{a+b} = x^a \cdot x^b$ e $x^{2k} = (x^k)^2$, possiamo separare ricorsivamente:

$$
x^y = x^{y_0 + 2(\ldots)} = x^{y_0} \cdot \bigl(x^{y_1 + 2(\ldots)}\bigr)^2 = x^{y_0} \cdot \bigl(x^{y_1} \cdot (x^{y_2+\ldots})^2\bigr)^2 = \ldots
$$

Spingendo la ricorsione fino in fondo si arriva alla forma completamente nidificata:

$$
x^y = x^{y_0}\Bigl(x^{y_1}\Bigl(\cdots\bigl(x^{y_{t-1}}(x^{y_t})^2\bigr)^2\cdots\Bigr)^2\Bigr)^2
$$

> 📌 Questa espressione va valutata **dall’interno verso l’esterno**, cioè partendo da $x^{y_t}$ (il bit più significativo) e procedendo verso $x^{y_0}$ (il bit meno significativo). Per questo si chiama **left-to-right**: si scorrono i bit di $y$ da sinistra (MSB) a destra (LSB) nella scrittura binaria.

Per $x^{40}$, con i bit da MSB a LSB: $y_5=1,\, y_4=0,\, y_3=1,\, y_2=0,\, y_1=0,\, y_0=0$:

$$
x^{40} = x^0\Bigl(x^0\Bigl(x^0\bigl(x^1\bigl(x^0(x^1)^2\bigr)^2\bigr)^2\Bigr)^2\Bigr)^2
$$

Valutando dall’interno:

| Passo | Operazione | Risultato |
|-------|-----------|-----------|
| 1 | $x^{y_5} = x^1$ | $x$ |
| 2 | $(x)^2 \cdot x^{y_4} = x^2 \cdot x^0$ | $x^2$ |
| 3 | $(x^2)^2 \cdot x^{y_3} = x^4 \cdot x^1$ | $x^5$ |
| 4 | $(x^5)^2 \cdot x^{y_2} = x^{10} \cdot x^0$ | $x^{10}$ |
| 5 | $(x^{10})^2 \cdot x^{y_1} = x^{20} \cdot x^0$ | $x^{20}$ |
| 6 | $(x^{20})^2 \cdot x^{y_0} = x^{40} \cdot x^0$ | $x^{40}$ |

Ogni passo: si **quadra** il risultato accumulato, poi si **moltiplica per $x$** solo se il bit corrente vale $1$.

---

### **5. Metodo left-to-right – Algoritmo**

L’algoritmo non fa altro che codificare la tabella di sopra in un ciclo:

```plaintext
Potenza_Modulare(x, y, z)
  a ← 1
  for i = t downto 0 do
      a ← (a · a) mod z
      if yi = 1 then
          a ← (a · x) mod z
  return a
```

> 📌 **Lettura passo per passo dello pseudocodice.**
>
> - `a ← 1`: l’accumulatore parte da $1$ (elemento neutro della moltiplicazione). Prima di aver letto qualsiasi bit, l’esponente parziale è $0$ e $x^0 = 1$.
>
> - `for i = t downto 0`: si scorrono i bit di $y$ dall’MSB ($y_t$) all’LSB ($y_0$), cioè da sinistra a destra nella scrittura binaria — da cui il nome "left-to-right".
>
> - `a ← (a · a) mod z`: si **quadra** l’accumulatore. Questo corrisponde allo "spostamento a sinistra" dell’esponente parziale già accumulato: se fino a questo punto $a = x^e$, dopo il quadrato $a = x^{2e}$, facendo spazio per il prossimo bit.
>
> - `if yi = 1 then a ← (a · x) mod z`: se il bit corrente $y_i$ è $1$, si moltiplica per $x$, aggiungendo $1$ all’esponente parziale: $x^{2e} \cdot x = x^{2e+1}$. Se il bit è $0$, non si fa nulla: l’esponente parziale rimane $2e$.
>
> **Invariante del ciclo:** al termine dell’iterazione per il bit $y_i$, il valore di $a$ è $x^{(y_t\, y_{t-1}\, \cdots\, y_i)_2} \bmod z$, cioè $x$ elevato al numero formato dai bit già letti dall’MSB fino a $y_i$ incluso. Alla fine, dopo aver letto tutti i bit fino a $y_0$, si ottiene $x^y \bmod z$.

> 💡 **Perché la complessità crolla?** Ad ogni iterazione eseguiamo al massimo **2 moltiplicazioni modulari** (un quadrato + eventuale prodotto per $x$). Il ciclo gira $t+1$ volte, dove $t+1$ è il numero di bit di $y$. Se $y$ è di $512$ bit, il ciclo gira $512$ volte → al massimo $1024$ moltiplicazioni. Rispetto ai $2^{512}$ del metodo naïve, il guadagno è astronomico: la complessità passa da **esponenziale** in $y$ a **polinomiale** (lineare) nella lunghezza in bit di $y$.

#### **Complessità**

Se $y$ è di 512 bit, occorrono **circa 512 moltiplicazioni modulari**,  
quindi la complessità è **polinomiale** nella lunghezza dell’esponente.

#### **Esempio**

$$  
3^{40} \bmod 73 = 8  
$$

Rappresentazione binaria di $40$:

$$  
y_5=1,\ y_4=0,\ y_3=1,\ y_2=0,\ y_1=0,\ y_0=0  
$$

---

### **6. Metodo right-to-left**

> 💡 **L’idea di fondo, diversa dal left-to-right.** Nel metodo precedente abbiamo usato Horner per *nidificare* l’esponente e poi ricostruire $x^y$ con quadrati successivi. Qui l’approccio è opposto: si usa il fatto che $x^{a+b} = x^a \cdot x^b$ per **spezzare** $x^y$ in un prodotto di contributi indipendenti, uno per ogni bit di $y$. Ogni contributo è facile da calcolare perché segue un raddoppio regolare. Il calcolo procede **dal bit meno significativo (LSB) al più significativo (MSB)** — da destra a sinistra nella scrittura binaria, da cui il nome.

**Passo 1 — scrittura binaria di $y$:**

$$
y = y_0 \cdot 2^0 + y_1 \cdot 2^1 + y_2 \cdot 2^2 + \dots + y_t \cdot 2^t
$$

**Passo 2 — separazione dell’esponente con $x^{a+b} = x^a \cdot x^b$:**

$$
x^y = x^{y_0 \cdot 2^0 + y_1 \cdot 2^1 + \dots + y_t \cdot 2^t} = x^{2^0 y_0} \cdot x^{2^1 y_1} \cdot \dots \cdot x^{2^t y_t}
$$

**Passo 3 — rilettura come potenze crescenti di $x$:**

Usando $x^{2^i y_i} = (x^{2^i})^{y_i}$, ogni fattore diventa una potenza di $x$ elevata al quadrato $i$ volte, alzata poi all’esponente binario $y_i \in \{0,1\}$:

$$
x^y = (x^{2^0})^{y_0} \cdot (x^{2^1})^{y_1} \cdot (x^{2^2})^{y_2} \cdot \dots \cdot (x^{2^t})^{y_t}
$$

> 📌 **Cosa si osserva.** Ogni base $x^{2^i}$ è il quadrato di quella precedente: $x^{2^1} = (x^{2^0})^2$, $x^{2^2} = (x^{2^1})^2$, ecc. Quindi basta tenere una variabile $X$ che parte da $x$ e viene quadrata ad ogni passo — automaticamente genera le basi giuste. Ogni fattore contribuisce al prodotto finale **solo se** il bit corrispondente $y_i = 1$; se $y_i = 0$ il termine vale $(x^{2^i})^0 = 1$ e non cambia nulla.

#### **Esempio con $x^{40}$**

Rappresentazione binaria: $40 = 0 \cdot 2^0 + 0 \cdot 2^1 + 0 \cdot 2^2 + 1 \cdot 2^3 + 0 \cdot 2^4 + 1 \cdot 2^5$

Quindi $y_0=0,\, y_1=0,\, y_2=0,\, y_3=1,\, y_4=0,\, y_5=1$:

$$
x^{40} = (x^1)^{0} \cdot (x^2)^{0} \cdot (x^4)^{0} \cdot (x^8)^{1} \cdot (x^{16})^{0} \cdot (x^{32})^{1}
$$

I fattori con esponente $0$ collassano a $1$ e non contribuiscono:

$$
x^{40} = 1 \cdot 1 \cdot 1 \cdot x^8 \cdot 1 \cdot x^{32} = x^8 \cdot x^{32} = x^{40} \checkmark
$$

> 💡 **Confronto con left-to-right.** Il left-to-right accumula *un unico valore* che cresce per quadrati successivi, leggendo i bit dall’MSB all’LSB. Il right-to-left invece mantiene *due variabili separate*: $X$ (la base corrente, che raddoppia sempre) e $P$ (il prodotto accumulato, che cresce solo quando il bit vale $1$), scorrendo i bit dall’LSB all’MSB. Entrambi sono ugualmente efficienti.

---

### **7. Metodo right-to-left – Algoritmo**

```plaintext
Potenza_Modulare(x, y, z)
  if y = 0 then return 1
  X ← x
  P ← 1
  if y0 = 1 then P ← (P · X) mod z
  for i = 1 to t do
      X ← (X · X) mod z
      if yi = 1 then P ← (P · X) mod z
  return P
```

> 📌 **Lettura passo per passo dello pseudocodice.**
>
> - `if y = 0 then return 1`: caso base — $x^0 = 1$ per definizione.
>
> - `X ← x`: $X$ rappresenta la **base corrente**, cioè $x^{2^i}$ per l’iterazione $i$. Parte da $x^{2^0} = x$.
>
> - `P ← 1`: $P$ è il **prodotto accumulato** dei contributi dei bit già visti. Parte da $1$ (nessun contributo ancora).
>
> - `if y0 = 1 then P ← (P · X) mod z`: gestisce il bit $y_0$ (LSB) separatamente, prima del ciclo. Se $y_0 = 1$, il contributo di $x^{2^0} = x$ viene incluso in $P$. Se $y_0 = 0$, si salta.
>
> - **Ciclo `for i = 1 to t`** — ad ogni iterazione si fa:
>   1. `X ← (X · X) mod z`: si quadra $X$, così $X$ passa da $x^{2^{i-1}}$ a $x^{2^i}$ — la base giusta per il bit $y_i$.
>   2. `if yi = 1 then P ← (P · X) mod z`: se il bit $y_i$ vale $1$, il contributo di $x^{2^i}$ viene moltiplicato nel prodotto. Se $y_i = 0$, il fattore sarebbe $1$ e si salta.
>
> **Invariante del ciclo:** al termine dell’iterazione $i$, $P = x^{\sum_{j=0}^{i} y_j \cdot 2^j} \bmod z$, cioè il prodotto parziale considerando i bit $y_0, y_1, \ldots, y_i$ già elaborati. Alla fine, dopo $i = t$, $P = x^y \bmod z$.

> ⚠️ **Ordine delle due istruzioni nel ciclo.** Si quadra $X$ **prima** di usarlo: così quando si controlla $y_i$, $X$ vale già $x^{2^i}$ (il contributo corretto per il bit $i$). Se si invertisse l’ordine si userebbe la base dell’iterazione precedente, ottenendo un risultato sbagliato.

#### **Esempio: $5^{596} \bmod 1234 = 1013$**

$596 = (1001010100)_2$, quindi i bit da LSB a MSB sono: $y_0=0,\, y_1=0,\, y_2=1,\, y_3=0,\, y_4=1,\, y_5=0,\, y_6=1,\, y_7=0,\, y_8=0,\, y_9=1$

| $i$ | $y_i$ | $X = 5^{2^i} \bmod 1234$ | $P$ |
|---|---|---|---|
| 0 | 0 | 5 | 1 |
| 1 | 0 | 25 | 1 |
| 2 | 1 | 625 | 625 |
| 3 | 0 | 681 | 625 |
| 4 | 1 | 1011 | 67 |
| 5 | 0 | 369 | 67 |
| 6 | 1 | 421 | 1059 |
| 7 | 0 | 779 | 1059 |
| 8 | 0 | 947 | 1059 |
| 9 | 1 | 925 | 1013 |

> 💡 Si noti che $X$ viene quadrato **sempre**, ad ogni riga, indipendentemente dal valore di $y_i$. $P$ invece cambia **solo** nelle righe con $y_i = 1$ (righe $i=2,4,6,9$): in quelle righe il nuovo $P$ è il vecchio $P$ moltiplicato per $X$ della stessa riga.

👉 Anche questo metodo ha **complessità polinomiale** rispetto alla lunghezza dell’esponente: al più $2(t+1)$ moltiplicazioni modulari per un esponente di $t+1$ bit.

---

### **8. Scelta dell’esponente pubblico $e$**

Per migliorare l’efficienza delle operazioni RSA, si sceglie l’esponente pubblico in modo da **ridurre il numero di moltiplicazioni** necessarie durante la cifratura.

Scelte tipiche:

- $e = 3$
    
- $e = 2^{16} + 1 = 65537$
    
    - Rappresentazione binaria:  
        $$  
        e = 1\underbrace{0000000000000000}16 1  
        $$
        
    - È una scelta ottimale: **basso costo computazionale**, ma sicurezza elevata.
        

---

### **9. Performance sperimentali**

#### **9.1 Dati storici — Intel Celeron 850 MHz (Coppermine, ~2000), libreria Crypto++**

> 💡 Il Celeron 850 MHz è un processore della generazione Pentium III uscito attorno al 2000: architettura a 32 bit, pipeline corta, nessuna istruzione dedicata alla moltiplicazione modulare larga. È il contesto in cui il docente presentava queste slide originariamente.

| Dimensione chiavi | Cifratura (ms) | Decifratura (ms) |
|---|---|---|
| **512 bit** | 0.14 | 1.93 |
| **1024 bit** | 0.32 | 10.23 |
| **2048 bit** | 0.89 | 64.13 |

> ⚠️ **Nota di sicurezza (aggiornamento 2024):** le chiavi RSA a **512 bit** sono state fattorizzate pubblicamente già negli anni '90–2000 e sono completamente insicure. Le chiavi a **1024 bit** sono deprecate da NIST dal 2013. Lo standard minimo attuale è **2048 bit**; per dati sensibili con orizzonte temporale lungo (es. PKI, CA) si raccomanda **4096 bit**.

---

#### **9.2 Confronto con hardware moderno — OpenSSL `speed rsa` (benchmark single-core, ~2022–2023)**

I dati seguenti sono valori rappresentativi ottenuti da benchmark `openssl speed rsa` su hardware consumer e professionale della generazione attuale. I numeri si riferiscono a operazioni single-thread.

| CPU                         | Anno  | Architettura         | RSA-2048 cifratura (ms) | RSA-2048 decifratura (ms) | RSA-4096 cifratura (ms) | RSA-4096 decifratura (ms) |
| --------------------------- | ----- | -------------------- | ----------------------- | ------------------------- | ----------------------- | ------------------------- |
| Intel Celeron 850 MHz       | ~2000 | Coppermine (gen. P3) | 0.89                    | 64.13                     | —                       | —                         |
| Intel Core i7-10700         | 2020  | Comet Lake (gen 10)  | ~0.013                  | ~0.22                     | ~0.045                  | ~1.50                     |
| Intel Core i9-13900K        | 2022  | Raptor Lake (gen 13) | ~0.012                  | ~0.18                     | ~0.040                  | ~1.20                     |
| AMD Ryzen 9 7950X           | 2022  | Zen 4                | ~0.011                  | ~0.17                     | ~0.038                  | ~1.15                     |
| AMD Threadripper PRO 7985WX | 2023  | Zen 4, 64 core       | ~0.010                  | ~0.15                     | ~0.035                  | ~1.05                     |

> 📌 **Come leggere la tabella.** La colonna "cifratura" corrisponde all'operazione con esponente pubblico $e$ (piccolo → veloce). La "decifratura" usa l'esponente privato $d$ (grande → costoso). Il Threadripper non migliora molto la latenza single-core rispetto al Ryzen consumer: i suoi 64 core servono per parallelizzare migliaia di operazioni RSA contemporaneamente, scenario tipico dei server TLS.

**Speedup rispetto al Celeron 850 MHz (RSA-2048 decifratura):**

$$
\text{speedup} \approx \frac{64.13 \text{ ms}}{0.18 \text{ ms}} \approx 356\times
$$

> 💡 **Da dove viene questo enorme salto?** Venticinque anni di progresso hardware portano: frequenze di clock ~6× superiori, pipeline out-of-order profonde, istruzioni dedicate come `MULX` e `ADCX`/`ADOX` (introdotte con Broadwell nel 2014) che accelerano la moltiplicazione modulare a precisione multipla, cache L1/L2/L3 molto più grandi, e implementazioni software ottimizzate con assembly manuali (OpenSSL usa routine asm per ogni architettura). Il risultato è che RSA-2048 oggi costa ~0.18 ms, praticamente impercettibile per un singolo handshake TLS.

**Osservazioni generali (valide indipendentemente dall'hardware):**

- La **cifratura** è sempre molto più veloce della **decifratura**: il rapporto rimane circa 1:15 su hardware moderno (contro 1:72 sul Celeron), perché sia $e$ che $d$ sono cresciuti in proporzione ma le architetture moderne gestiscono meglio gli esponenti grandi.
    
- Aumentando la dimensione della chiave da 2048 a 4096 bit, il tempo di decifratura cresce di circa **6–7×** (non 4×), perché la moltiplicazione modulare con interi doppiamente grandi richiede circa 4× operazioni elementari ma con overhead aggiuntivo.
    
- Per applicazioni ad altissima frequenza (es. server HTTPS con migliaia di connessioni/secondo) si usano acceleratori hardware dedicati (HSM, chip crittografici) o si preferisce ECDSA/ECDH che offre sicurezza equivalente con chiavi molto più corte.
    

---

### **10. Sintesi finale**

- Le **computazioni RSA** si basano sull’elevazione a potenza modulare.
    
- I metodi **left-to-right** e **right-to-left** riducono il tempo di calcolo da esponenziale a **polinomiale**.
    
- L’esponente pubblico $e$ viene scelto per **ottimizzare la velocità di cifratura**, mantenendo comunque una sicurezza adeguata.