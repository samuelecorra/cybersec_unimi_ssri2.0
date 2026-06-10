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

```c
Cipher(byte in[4·Nb], byte out[4·Nb], word w[Nb·(Nr + 1)]) 
{
    byte state[4, Nb]
    state ← in
    AddRoundKey(state, w)
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

**Parametri di ingresso:**

- `byte in[4·Nb]` — array lineare di **16 byte** (128 bit) che contiene il blocco di testo in chiaro da cifrare.
- `byte out[4·Nb]` — array di **16 byte** che riceverà il testo cifrato al termine della funzione.
- `word w[Nb·(Nr+1)]` — array di **round key** prodotte dall'espansione della chiave: contiene in totale $Nb \cdot (Nr+1)$ word da 32 bit, suddivise in blocchi da 4 word per ogni round (più il blocco iniziale); `w + k·Nb` indica la sottochiave usata al round $k$.

**Corpo della funzione, istruzione per istruzione:**

1. `byte state[4, Nb]` — alloca la matrice di stato 4×4 byte su cui avverranno tutte le trasformazioni.
2. `state ← in` — copia il blocco in chiaro nella matrice di stato (riempita per colonne, come descritto in L2).
3. `AddRoundKey(state, w)` — **round key iniziale** (round 0): XOR tra ogni byte dello stato e il byte corrispondente delle prime 4 word (`w[0..3]`). Avviene *prima* del ciclo: è l'unica difesa prima delle trasformazioni.
4. `for round = 1 to Nr - 1` — ciclo sui **Nr − 1 round intermedi** (da 1 a Nr−1 incluso); l'ultimo round è separato perché non include MixColumns.
5. `SubBytes(state)` — **sostituzione non lineare**: ogni byte è sostituito tramite la S-box in $GF(2^8)$; introduce confusione.
6. `ShiftRows(state)` — **rotazione ciclica delle righe**: la riga $r$ scorre a sinistra di $r$ posizioni ($r = 0,1,2,3$); mescola i byte tra colonne diverse.
7. `MixColumns(state)` — **mixing lineare per colonne**: ogni colonna viene moltiplicata per la matrice fissa in $GF(2^8)$; introduce diffusione.
8. `AddRoundKey(state, w + round·Nb)` — XOR con la round key del round corrente, cioè le 4 word `w[round·4 .. round·4+3]`.
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

1. Si inizializza la tabella con tutti i **256 byte** in ordine crescente.
    
2. Ogni byte viene sostituito con il **suo inverso moltiplicativo in $GF(2^8)$**  
    (eccetto `{00}`, che resta invariato).
    
3. A ciascun byte viene applicata una **trasformazione affine** in $GF(2^8)$:
    

$$  
b'_i = b_i \oplus b_{(i+4)\bmod8} \oplus b_{(i+5)\bmod8} \oplus b_{(i+6)\bmod8} \oplus b_{(i+7)\bmod8} \oplus c_i  
$$

dove $c = {63}_{16}$.

![](imgs/Pasted%20image%2020260222180517.png)

---

#### **2.4 Proprietà della S-box**

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

A partire dalla chiave iniziale di $4 \cdot Nk$ byte, si genera un array di $Nb \cdot (Nr + 1)$ word.

Ogni **round** utilizza $Nb = 4$ word:

- 1 per l'AddRoundKey iniziale,
    
- 1 per ciascuno dei $Nr$ round successivi.

#### **Funzioni utilizzate**

- **SubWord()** → applica la S-box a ogni byte della word.
    
- **RotWord()** → esegue una rotazione ciclica a sinistra di 1 byte.
    
- **Rcon[i] = [RC[i], {00}, {00}, {00}]**  
    con:  
    $$  
    RC[i] = 2 \cdot RC[i-1], \quad RC[1] = \{01\}  
    $$

![](imgs/Pasted%20image%2020260222181329.png)

---

#### **Pseudocodice dell'espansione della chiave**

![](imgs/Pasted%20image%2020260222181638.png)

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

![](imgs/Pasted%20image%2020260222181755.png)

---

### **7. Sintesi finale**

Abbiamo visto:

- Le **quattro trasformazioni fondamentali** dell'AES:  
    **SubBytes, ShiftRows, MixColumns e AddRoundKey**.
    
- La **costruzione e le proprietà** della S-box.
    
- Il **meccanismo di espansione della chiave**, che genera tutte le sottochiavi a partire dalla chiave principale.
    

➡️ Queste operazioni costituiscono il **core della cifratura AES**, basata su sostituzioni e permutazioni eseguite nel **campo finito $GF(2^8)$**.
