# **Lezione 3: Trasformazioni di cifratura dell'AES**

---

### **1. Struttura generale della cifratura**

L'AES opera su blocchi di **128 bit**, rappresentati come una **matrice di stato** `state[4][Nb]`, dove `Nb = 4`.

L'algoritmo utilizza un insieme di **sottochiavi (round keys)**, ottenute tramite un processo di **espansione della chiave** a partire dalla chiave iniziale.  
Le operazioni fondamentali del processo di cifratura sono quattro:

1. **SubBytes**
    
2. **ShiftRows**
    
3. **MixColumns**
    
4. **AddRoundKey**
    

#### **Pseudocodice della cifratura AES**

Prima dello pseudocodice in sé, vi ripropongo un piccolo sunto delle variabili essenziali, perché la nomenclatura poco chiara potrebbe non farvele ricordare al volo...

![](imgs/Pasted%20image%2020260610215234.png)

---

**Parametri di ingresso:**

- `byte in[4·Nb]` — array lineare di **16 byte** (128 bit) che contiene il blocco di testo in chiaro da cifrare. [4x4]
- `byte out[4·Nb]` — array di **16 byte** che riceverà il testo cifrato al termine della funzione. [4x4]
- `word w[Nb·(Nr+1)]` — array di **round key** prodotte dall'espansione della chiave: contiene in totale $Nb \cdot (Nr+1)$ word da 32 bit, suddivise in blocchi da 4 word per ogni round (più il blocco iniziale); `w + k·Nb` indica la sottochiave usata al round $k$. (rispettivamente 44, 52 e 60 words in entrata per AES 128/192/256)

```c
Cipher(byte in[4·Nb], byte out[4·Nb], word w[Nb·(Nr + 1)]) 
{
    byte state[4, Nb] // inizializzo la matrice di stato 4x4word
    state ← in // la popolo con l'input
    AddRoundKey(state, w) // c'è un round key iniziale (round 0)
    for round = 1 to Nr - 1 {
        SubBytes(state)
        ShiftRows(state)
        MixColumns(state)
        AddRoundKey(state, w + round · Nb)
    }
    SubBytes(state)
    ShiftRows(state)
    AddRoundKey(state, w + Nr · Nb)
    out ← state
}
```

**Corpo della funzione, istruzione per istruzione:**

1. `byte state[4, Nb]` — alloca la matrice di stato 4×4 byte su cui avverranno tutte le trasformazioni.
2. `state ← in` — copia il blocco in chiaro nella matrice di stato (riempita per colonne, come descritto in L2).
3. `AddRoundKey(state, w)` — **round key iniziale** (round 0): XOR tra ogni byte dello stato e il byte corrispondente delle prime 4 word (`w[0..3]`). Avviene *prima* del ciclo: è l'unica difesa prima delle trasformazioni.
4. `for round = 1 to Nr - 1` — ciclo sui **Nr − 1 round intermedi** (da 1 a Nr−1 incluso); l'ultimo round è separato perché non include MixColumns.
5. `SubBytes(state)` — **sostituzione non lineare**: ogni byte è sostituito tramite la S-box in $GF(2^8)$; introduce confusione.
6. `ShiftRows(state)` — **rotazione ciclica delle righe**: la riga $r$ scorre a sinistra di $r$ posizioni ($r = 0,1,2,3$); mescola i byte tra colonne diverse.
7. `MixColumns(state)` — **mixing lineare per colonne**: ogni colonna viene moltiplicata per la matrice fissa in $GF(2^8)$; introduce diffusione.
8. `AddRoundKey(state, w + round·Nb)` — XOR con la round key del round corrente, cioè le 4 word `w[round·4 .. round·4+3]`. In pratica:
- **Round 0** (pre-loop): words 0–3
- **Round 1**: words 4–7
- **Round 2**: words 8–11
- …
- **Round 10** (AES-128): words 40–43

9. `SubBytes(state)` — SubBytes dell'**ultimo round**.
10. `ShiftRows(state)` — ShiftRows dell'**ultimo round**.
11. `AddRoundKey(state, w + Nr·Nb)` — XOR con l'**ultima round key** (`w[Nr·4 .. Nr·4+3]`). MixColumns viene omessa nell'ultimo round per rendere la decifratura strutturalmente simmetrica.
12. `out ← state` — copia la matrice di stato nell'array di output: il blocco cifrato è pronto.

> 📌 La struttura è: *AddRoundKey iniziale → (Nr−1) round completi con tutte e 4 le trasformazioni → 1 round finale senza MixColumns*. L'omissione di MixColumns nell'ultimo round non indebolisce la sicurezza (è solo una scelta di simmetria con la decifratura).



![](imgs/Pasted%20image%2020260222175949.png)

---

### **2. Trasformazione SubBytes**

#### **2.1 Definizione**

La trasformazione **SubBytes** sostituisce ogni byte $S_{r,c}$ della matrice di stato con un altro byte $S'_{r,c}$ secondo una **S-box non lineare ma invertibile**:

$$  
S'_{r,c} = S\text{-box}(S_{r,c})  
$$

![](imgs/Pasted%20image%2020260222180155.png)

Ogni byte viene sostituito in modo **indipendente**.  
La S-box è una **tabella 16×16** che contiene una **permutazione dei 256 valori** possibili di un byte.

---

#### **2.2 Struttura della S-box**

La S-box è organizzata come una **tabella a doppio indice** (righe e colonne):

![](imgs/Pasted%20image%2020260222180245.png)

- I primi **4 bit** del byte determinano l'indice di **riga**,
    
- Gli altri **4 bit** determinano la **colonna**.
    

**Esempio:**  
Il byte `{53}` (riga 5, colonna 3) → `{ed}`.

---

#### **2.3 Costruzione della S-box**

A differenza del DES, l'AES permette di costruire le S-Box ANCHE dinamicamente mediante la composizione di due trasformazioni.

1. Si inizializza la tabella con tutti i **256 byte** in ordine crescente.
    
2. Ogni byte viene sostituito con il **suo inverso moltiplicativo in $GF(2^8)$**  
    (eccetto `{00}`, che resta invariato).

> 💡 **Perché l'inverso moltiplicativo?** È l'operazione più non-lineare disponibile in $GF(2^8)$: la mappa $x \mapsto x^{-1}$ non ha nessuna struttura affine sfruttabile da un attaccante. Questo è il cuore della resistenza di AES alla crittoanalisi differenziale e lineare — se si usasse una semplice permutazione lineare, un attaccante potrebbe costruire equazioni lineari sui bit e risolverle. L'inversione in $GF(2^8)$ rompe qualsiasi tale struttura.

3. A ciascun byte viene applicata una **trasformazione affine** in $GF(2^8)$:

$$
b'_i = b_i \oplus b_{(i+4)\bmod 8} \oplus b_{(i+5)\bmod 8} \oplus b_{(i+6)\bmod 8} \oplus b_{(i+7)\bmod 8} \oplus c_i
$$

dove $c = \mathtt{63}_{16} = 01100011_2$.

> 💡 **Perché questi specifici shift?** La formula calcola ogni bit di output $b'_i$ come XOR del bit $b_i$ con i bit nelle posizioni $(i+4), (i+5), (i+6), (i+7)$ modulo 8 — cioè con quattro copie del byte ruotate ciclicamente di 4, 5, 6 e 7 posizioni. In forma matriciale questo equivale a moltiplicare il vettore dei bit del byte per una matrice $8 \times 8$ in $GF(2)$ (vedi sotto): ogni riga della matrice è la riga precedente shiftata a sinistra di 1 — la stessa struttura circolante che abbiamo visto per MixColumns, ma qui a livello di singolo byte. Questa operazione è **lineare su $GF(2)$**, ed è progettata per mescolare ulteriormente i bit dopo l'inversione (che è non-lineare). La combinazione delle due garantisce che la S-box non sia né puramente lineare né puramente non-lineare: nessuna delle due parti da sola è sicura, ma insieme producono una biiezione con ottime proprietà crittografiche.

> 💡 **Perché $c = \mathtt{63}_{16}$?** Il termine $\oplus\, c_i$ è una **traslazione affine** — sposta l'output lontano da 0 e da 1 in modo da eliminare i punti fissi ($S(x) = x$) e i punti anti-fissi ($S(x) = \bar{x}$). Il valore $\mathtt{63}$ non è stato scelto per ragioni algebriche profonde: i progettatori di Rijndael lo hanno selezionato empiricamente per massimizzare la distanza da queste situazioni indesiderate, e hanno scelto un valore dal pattern binario non ovvio ($01100011$) per evitare qualsiasi accusa di backdoor nascosta.

**Forma matriciale della trasformazione affine**

La formula scalare $b'_i = b_i \oplus b_{(i+4)\bmod 8} \oplus \ldots \oplus c_i$ si può riscrivere in forma compatta come:

$$
\mathbf{b'} = A\,\mathbf{b} \oplus \mathbf{c}
$$

dove $A$ è la matrice $8 \times 8$ in $GF(2)$ in cui ogni riga è lo shift ciclico della precedente, $\mathbf{b}$ è il vettore dei bit del byte dopo l'inversione, e $\mathbf{c} = (1,1,0,0,0,1,1,0)^T$ è il vettore costante corrispondente a $\mathtt{63}_{16}$. Ogni riga $i$ della matrice ha un 1 nelle colonne $i,\,(i+4)\bmod 8,\,(i+5)\bmod 8,\,(i+6)\bmod 8,\,(i+7)\bmod 8$ — che sono esattamente i cinque termini $b_j$ che contribuiscono a $b'_i$.

![](imgs/Pasted%20image%2020260222180517.png)

---

#### **2.4 Proprietà della S-box**

A differenza delle S-Box di DES, che realizzavano una funzione casuale dell'input, queste invece danno una trasformazione algebrica - attraverso quest'analisi è dimostrabile che soddisfano queste proprietà:

- Non è una funzione lineare.
    
- Non ha **punti fissi** diretti né opposti:  
    $S(a) \ne a$ e $S(a) \ne \bar{a}$.
    
- È **invertibile**, con una **Inverse S-box**.
    
- Non è **auto-invertente**:  
    $S(a) \ne S^{-1}(a)$.
    

**Esempio:**  
$S(95) = 2A$, ma $S^{-1}(95) = AD$.

Ecco la Inverse_S-Box:

![](imgs/Pasted%20image%2020260222180723.png)

I criteri progettuali sono stati motivati dall'esigenza di far resistere AES a tutti gli attacchi analitici sviluppatisi negli anni '90, quali differenziale e lineare.

---

### **3. Trasformazione ShiftRows**

La trasformazione **ShiftRows** effettua uno **shift ciclico a sinistra** sulle righe della matrice di stato.

![](imgs/Pasted%20image%2020260222180803.png)

$$  
S'_{r,c} = S_{r,(c + \text{shift}(r,Nb)) \bmod Nb}  
$$

dove:

$$  
\text{shift}(r,4) =  
\begin{cases}  
0 & r = 0 \\  
1 & r = 1 \\  
2 & r = 2 \\  
3 & r = 3  
\end{cases}  
$$

- La **prima riga** non viene modificata.
    
- La **seconda, terza e quarta riga** vengono spostate rispettivamente di 1, 2 e 3 posizioni a sinistra.
    

---

### **4. Trasformazione MixColumns**

La trasformazione **MixColumns** combina linearmente i 4 byte di ogni colonna:

![](imgs/Pasted%20image%2020260222181001.png)

$$  
\begin{bmatrix}  
S'_{0,c} \\ S'_{1,c} \\ S'_{2,c} \\ S'_{3,c}  
\end{bmatrix}
=
\begin{bmatrix}  
02 & 03 & 01 & 01 \\  
01 & 02 & 03 & 01 \\  
01 & 01 & 02 & 03 \\  
03 & 01 & 01 & 02  
\end{bmatrix}  
\cdot  
\begin{bmatrix}  
S_{0,c} \\ S_{1,c} \\ S_{2,c} \\ S_{3,c}  
\end{bmatrix}  
$$

Le moltiplicazioni sono eseguite nel **campo finito $GF(2^8)$**, modulo il polinomio:

$$  
m(x) = x^8 + x^4 + x^3 + x + 1  
$$

In forma polinomiale, la trasformazione equivale a:

$$  
S'_c(x) = a(x) \cdot S_c(x) \mod (x^4 + 1)  
$$

con:

$$  
a(x) = \{03\}x^3 + \{01\}x^2 + \{01\}x + \{02\}  
$$

---

### **5. Trasformazione AddRoundKey**

Ogni byte della matrice di stato viene combinato con il corrispondente byte della **chiave di round** tramite un'operazione XOR:

![](imgs/Pasted%20image%2020260222181104.png)

$$  
S'_{r,c} = S_{r,c} \oplus W_{r,c}  
$$

dove $W_{r,c}$ rappresenta la sottochiave ottenuta dall'espansione della chiave originale.

---

### **6. Espansione della chiave**

A partire dalla chiave iniziale di $4 \cdot Nk$ byte (16 nel caso sempre trattato di AES 128), si genera un array di $Nb \cdot (Nr + 1)$ word (e quindi nel nostro caso di 44word)

Ogni **round** utilizza $Nb = 4$ word:

- 1 per l'AddRoundKey iniziale,
    
- 1 per ciascuno dei $Nr$ round successivi.

#### **Funzioni e costanti utilizzate**

- **RotWord($w$)** → rotazione ciclica a sinistra di 1 byte: $[a,b,c,d] \to [b,c,d,a]$. Serve a "spostare" il materiale chiave prima della sostituzione.

- **SubWord($w$)** → applica la S-box di SubBytes a ciascuno dei 4 byte della word. Introduce non-linearità nella schedule.

- **Rcon[$i$]** → array di costanti di round $[\,RC[i],\,\{00\},\,\{00\},\,\{00\}\,]$, dove:
$$
RC[i] = 2 \cdot RC[i-1] \quad \text{in } GF(2^8), \qquad RC[1] = \{01\}
$$
I tre byte a zero fanno sì che Rcon agisca **solo** sul byte più significativo della word, iniettando una costante diversa ad ogni round così da impedire che round key identici si ripetano (rompe la simmetria della schedule).

![](imgs/Pasted%20image%2020260222181329.png)

---

La chiave espansa è quindi l'array $w[0..43]$ mostrato in figura sotto (per AES-128: $Nk=4$, $Nr=10$, totale $4 \times 11 = 44$ word).

**Fase 1 — copia della chiave originale (word 0–3)**

Le prime $Nk = 4$ word vengono popolate direttamente con i byte della chiave originale, raggruppati a 4 a 4. Nessuna trasformazione.

**Fase 2 — generazione iterativa (word 4–43)**

Per ogni indice $i \geq Nk$, la nuova word dipende sempre da due word già calcolate:

$$
w[i] = w[i - Nk] \oplus \text{temp}
$$

dove `temp` parte da $w[i-1]$ e viene trasformato in modo diverso a seconda di $i$:

- **Caso normale** ($i \bmod Nk \neq 0$): `temp` resta $w[i-1]$ inalterato. La word è semplicemente XOR tra la precedente e quella $Nk$ posizioni indietro.

- **Caso speciale** ($i \bmod Nk = 0$, cioè all'inizio di ogni nuovo blocco da $Nk$ word): `temp` subisce le tre trasformazioni definite sopra in sequenza — **RotWord**, poi **SubWord**, poi **XOR con Rcon[i/Nk]** — prima di essere XOR-ato con $w[i-Nk]$.

> 💡 **Perché la trasformazione speciale ogni $Nk$ word?** Lo XOR semplice da solo produrrebbe una struttura periodica: chiavi simili genererebbero schedule simili, aprendo ad attacchi con chiave correlata. RotWord + SubWord introducono non-linearità e asimmetria; il ruolo di Rcon è già spiegato sopra.

- **Caso AES-256** ($Nk = 8$ e $i \bmod 8 = 4$): a metà di ogni blocco da 8 word si applica solo **SubWord** (senza RotWord né Rcon), per aumentare la diffusione sui round più lunghi.

![](imgs/Pasted%20image%2020260222181638.png)

---
#### **Pseudocodice dell'espansione della chiave**

```c
KeyExpansion (byte key[4 * Nk], word w[Nb * (Nr+1)], Nk)
{
    i ← 0
    while (i < Nk) {
        w[i] ← word[key[4*i], key[4*i+1], key[4*i+2], key[4*i+3]]
        i ← i + 1
    }
    i ← Nk
    while (i < Nb * (Nr + 1)) {
        temp ← w[i - 1]
        if (i mod Nk = 0)
            temp ← SubWord(RotWord(temp)) xor Rcon[i/Nk]
        else if (Nk = 8 and i mod Nk = 4)
            temp ← SubWord(temp)
        w[i] ← w[i - Nk] xor temp
        i ← i + 1
    }
}
```

**Parametri di ingresso:**

- `byte key[4*Nk]` — array della chiave originale: 16 byte per AES-128 ($Nk=4$), 24 per AES-192 ($Nk=6$), 32 per AES-256 ($Nk=8$).
- `word w[Nb*(Nr+1)]` — array di output che conterrà tutte le round key espanse; alla fine ha $4 \cdot (Nr+1)$ word, una "fetta" da 4 word per ogni round.
- `Nk` — numero di word (32 bit) nella chiave originale; determina quanti round ci sono e con quale cadenza vengono applicate le trasformazioni speciali.

**Corpo della funzione, istruzione per istruzione:**

1. `i ← 0` — inizializza l'indice al primo ciclo.
2. `while (i < Nk)` — primo ciclo: copia la chiave originale nelle prime `Nk` word dell'array `w`, senza modificarla.
3. `w[i] ← word[key[4*i], ..., key[4*i+3]]` — raggruppa 4 byte consecutivi della chiave in una singola word a 32 bit (il byte `4*i` diventa il byte più significativo).
4. `i ← Nk` — riposiziona `i` all'indice successivo all'ultimo copiato, per far ripartire il secondo ciclo da dove il primo si è fermato.
5. `while (i < Nb*(Nr+1))` — secondo ciclo: genera le restanti word fino a riempire tutto l'array.
6. `temp ← w[i-1]` — punto di partenza: prende la word immediatamente precedente.
7. `if (i mod Nk = 0)` — ogni `Nk` word (cioè ogni "blocco chiave") si applica la trasformazione principale di schedule.
8. `temp ← SubWord(RotWord(temp)) xor Rcon[i/Nk]` — **RotWord**: ruota ciclicamente i 4 byte di `temp` di una posizione a sinistra (`[a,b,c,d] → [b,c,d,a]`); poi **SubWord**: applica la S-box a tutti e 4 i byte; infine XOR con la costante di round `Rcon[i/Nk]` (il cui primo byte è $RC[i/Nk] = 2^{i/Nk - 1}$ in $GF(2^8)$, gli altri tre sono `{00}`). Questo garantisce che chiavi simili producano schedule diverse (rompe la simmetria).
9. `else if (Nk = 8 and i mod Nk = 4)` — caso speciale **solo per AES-256**: a metà di ogni blocco da 8 word (cioè quando `i mod 8 = 4`) si applica un'ulteriore sostituzione senza rotazione né Rcon, per aumentare la diffusione della chiave nei round lunghi.
10. `temp ← SubWord(temp)` — S-box senza RotWord (caso AES-256).
11. `w[i] ← w[i-Nk] xor temp` — la nuova word è lo XOR tra `temp` (eventualmente trasformato) e la word che si trovava `Nk` posizioni prima; così ogni round key "eredita" il materiale della chiave originale mescolato con le trasformazioni applicate.
12. `i ← i + 1` — avanza all'iterazione successiva.

> 📌 Il risultato sono $Nb \cdot (Nr+1)$ word: le prime $Nk$ word sono la chiave originale inalterata, le successive sono generate iterativamente. Ogni "blocco" da 4 word consecutive costituisce la round key di un round.

---

### **7. Sintesi finale**

Abbiamo visto:

- Le **quattro trasformazioni fondamentali** dell'AES:  
    **SubBytes, ShiftRows, MixColumns e AddRoundKey**.
    
- La **costruzione e le proprietà** della S-box.
    
- Il **meccanismo di espansione della chiave**, che genera tutte le sottochiavi a partire dalla chiave principale.
    

➡️ Queste operazioni costituiscono il **core della cifratura AES**, basata su sostituzioni e permutazioni eseguite nel **campo finito $GF(2^8)$**.
