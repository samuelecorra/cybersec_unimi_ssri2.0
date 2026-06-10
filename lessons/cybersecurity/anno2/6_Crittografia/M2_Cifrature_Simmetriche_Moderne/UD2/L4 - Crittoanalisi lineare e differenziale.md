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

A questo punto l'intuizione di Biham e Shamir fu che alcune differenze in input producono differenze in output con probabilità significativamente maggiore di altre, a causa della struttura delle S-box. Coostruirono quindi una tabella che conteneva tutte le possibili configurazioni di differenze in input e output, e le loro probabilità associate. Questa tabella è nota come **differential distribution table (DDT)**.

Una tabella avrà per forza di cose $2^6 = 64$ righe (per tutte le possibili differenze in input) e $2^4 = 16$ colonne (per tutte le possibili differenze in output). Ogni cella della tabella conterrà il numero di coppie di input che producono quella specifica combinazione di differenza in input e differenza in output.

---

In questo esempio, per i fini puramente illustrativi, consideriamo solo le prime 5/64 righe che indicano il profilo XOR della S-Box 1.

Usiamo δ = `000001` = **1 in decimale** = **riga 1** della tabella. ✓

![](imgs/telegram-cloud-photo-size-4-5857263596000185975-x%201.jpg)

##### Step 1 — Costruire le 64 coppie

Per ogni B da `000000` a `111111`, calcolo `B* = B ⊕ 000001`. Ovvero, vado a ricavarmi tutti i valori per i quali otteniamo questa differenza in input. In questo specifico caso, questo significa che B* è sempre semplicemente B con **l'ultimo bit flippato**:

|B (bin)|B (dec)|B* = B ⊕ 000001 (bin)|B* (dec)|
|---|---|---|---|
|`000000`|0|`000001`|1|
|`000001`|1|`000000`|0|
|`000010`|2|`000011`|3|
|`000011`|3|`000010`|2|
|`000100`|4|`000101`|5|
|...|...|...|...|
|`111110`|62|`111111`|63|
|`111111`|63|`111110`|62|

Nota: le coppie si ripetono speculari — (0,1) e (1,0) sono la stessa coppia. Ma le contiamo comunque tutte e 64 perché B scorre tutti i 64 valori.

---

##### Step 2 — Passare le coppie alla S-Box e calcolare Δ

Per ogni coppia calcolo `S(B)`, `S(B*)`, poi `Δ = S(B) ⊕ S(B*)`:

|B|S(B)|B*|S(B*)|Δ = S(B) ⊕ S(B*)|
|---|---|---|---|---|
|`000000`|`1110`|`000001`|`0000`|`1110 ⊕ 0000 = 1110`|
|`000001`|`0000`|`000000`|`1110`|`0000 ⊕ 1110 = 1110`|
|`000010`|`0100`|`000011`|`1111`|`0100 ⊕ 1111 = 1011`|
|`000011`|`1111`|`000010`|`0100`|`1111 ⊕ 0100 = 1011`|
|...|...|...|...|...|

Nota l'effetto della simmetria: la coppia (0,1) e la coppia (1,0) producono **lo stesso Δ** — per questo i valori nella tabella sono sempre pari.

---

##### Step 3 — Contare le occorrenze di ogni Δ

Conto le 64 occorrenze di Δ:

|Δ|`0000`|`0001`|`0010`|`0011`|`0100`|`0101`|`0110`|`0111`|
|---|---|---|---|---|---|---|---|---|
|**#**|0|0|0|6|0|2|4|4|

|Δ|`1000`|`1001`|`1010`|`1011`|`1100`|`1101`|`1110`|`1111`|
|---|---|---|---|---|---|---|---|---|
|**#**|0|10|12|4|10|6|2|4|

Verifica: 6+2+4+4+10+12+4+10+6+2+4 = **64** ✓

Questa è esattamente la **riga 1** della tabella Output XOR dello screenshot qui sotto.

![](imgs/telegram-cloud-photo-size-4-5857263596000185975-x%202.jpg)

Legenda della tabella:

- **Riga** = valore di δ in decimale (0, 1, 2, 3, 4...)
- **Colonna** = valore di Δ in esadecimale (0=`0000`, A=`1010`, F=`1111`...)
- **Cella (δ, Δ)** = numero di coppie su 64 con quella coppia di differenze

Esempio di lettura: cella (riga 1, colonna A) = **12** → su 64 coppie con δ=`000001`, ben 12 producono Δ=`1010`.

---

##### 5. Importanza crittografica

#### Non tutti i Δ sono raggiungibili

Nella riga 1, le colonne 0, 1, 2, 4, 8 valgono **0**. Su 16 possibili valori di Δ, solo alcuni sono effettivamente raggiungibili. L'attaccante che conosce δ può escludere a priori tutti i Δ impossibili.

#### La distribuzione non è uniforme — ci sono picchi

- Δ=`1010` appare **12/64** volte → probabilità **18.75%**
- Δ=`1001` e `1100` appaiono **10/64** volte → probabilità **15.6%**

In una S-Box perfettamente sicura questi valori sarebbero tutti uguali. I picchi sono esattamente ciò che un attaccante sfrutta per fare previsioni statistiche sulla chiave.

#### I valori sono sempre pari

Come si vede dall'esempio nello Step 2, le coppie sono sempre speculari — (B, B*) e (B*, B) producono sempre lo stesso Δ. Quindi ogni Δ viene contato un numero pari di volte, sempre.

#### La riga 0 è sempre (64, 0, 0, …, 0)

Se δ=0 allora B=B*, quindi S(B)=S(B*), quindi Δ=0 sempre.

---

Ora colleghiamo quanto detto col vero schema DES:

---

##### Come funziona il round di DES (promemoria)

Prima capiamo lo schema:

- **A** = 32 bit di input al round (metà del blocco)
- **J** = 48 bit di chiave di round (la sottochiave)
- **E(A)** = espansione di A da 32 a 48 bit (funzione fissa, senza chiave)
- **B = E(A) ⊕ J** = i 48 bit che entrano nelle S-Box
- Le S-Box producono 32 bit → permutazione P → output del round

Ogni S-Box riceve 6 bit di B. Chiamiamo quei 6 bit semplicemente **B** per la singola S-Box.

![](imgs/Pasted%20image%2020260222114807.png)

---

##### Il punto cruciale: cosa dipende dalla chiave e cosa no

L'attaccante conosce A ed E (che è pubblica), quindi conosce **E(A)**. Ma **non conosce J**.

Quindi B = E(A) ⊕ J è **sconosciuto** perché J è sconosciuto.

Ora supponiamo di avere **due plaintext** A e A* con differenza nota. I loro input alle S-Box sono:

```
B  = E(A)  ⊕ J
B* = E(A*) ⊕ J
```

Calcoliamo δ = B ⊕ B*:

```
δ = B ⊕ B*
  = (E(A) ⊕ J) ⊕ (E(A*) ⊕ J)
  = E(A) ⊕ E(A*)
```

**La J si cancella!** Poiché E è pubblica e A, A* sono noti, δ è **completamente calcolabile dall'attaccante** senza conoscere la chiave.

---

##### Ora entra in gioco il Profilo XOR

L'attaccante conosce δ e, osservando i ciphertext, riesce a ricavare anche Δ (la differenza in output della S-Box). Quindi sa sia δ che Δ.

Dal Profilo XOR della S-Box, la cella `(δ, Δ)` contiene un insieme di possibili input `{s_i1, s_i2, ..., s_ij}` — cioè i valori di B compatibili con quella coppia (δ, Δ). Questo insieme si chiama **$S^δ_Δ$** nella slide.

> Ricorda: B = E(A) ⊕ J, quindi J = B ⊕ E(A).

Per ogni candidato `s_ik` nell'insieme, la chiave corrispondente sarebbe:

```
J = s_ik ⊕ E(A)    oppure equivalentemente    J = s_ik ⊕ E(A*)
```

Entrambe le formule devono dare lo stesso J — ed è proprio per questo che nella slide scrive l'insieme come:

```
{ s_i1 ⊕ E , ... , s_ij ⊕ E } = { s_i1 ⊕ E* , ... , s_ij ⊕ E* }
```

---

## Cosa ha guadagnato l'attaccante?

Senza questo attacco, la chiave J è a 48 bit → $2^{48}$ possibilità.

Con una sola coppia (A, A*) e il profilo XOR, la chiave è ristretta a pochissimi candidati — quanti ne ha la cella `(δ, Δ)` del profilo, che tipicamente sono 0, 2, 4, o al massimo 12-16 su 64.

Con **molte coppie** con lo stesso δ, l'intersezione degli insiemi candidati si restringe sempre di più fino a isolare la chiave vera.

---

##### Schema riassuntivo dell'attacco

```
Scelgo molte coppie (A, A*) con E(A) ⊕ E(A*) = δ fissato
              ↓
δ è noto senza conoscere J   ← la chiave si cancella!
              ↓
Osservo i ciphertext → ricavo Δ per ogni coppia
              ↓
Consulto il Profilo XOR: cella (δ, Δ) → insieme S^δ_Δ di candidati per B
              ↓
Per ogni candidato s_ik: la chiave sarebbe J = s_ik ⊕ E(A)
              ↓
Con più coppie interseco gli insiemi → i candidati falsi vengono eliminati
              ↓
Rimane (idealmente) un solo J → chiave trovata!
```

Ricordiamo che:

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

Una funzione booleana $f : \Sigma^n \to \Sigma$ che prende in input stringhe di $n$ bit e li mappa in un singolo bit. Tale funzione può essere descritta dalla sua **tabella di verità**:

$$  
(f(a_0), f(a_1), \dots, f(a_{2^n - 1}))  
$$

dove $a_i$ è la rappresentazione binaria di $i$.

Una funzione $h$ in $n$ variabili è **lineare** se possiamo rappresentarla come combinazione XOR di alcune variabili di input, che chiameremo $s_1, s_2, \dots, s_n$:

$$  
h(s) = a_1 s_1 \oplus a_2 s_2 \oplus \dots \oplus a_n s_n  
$$

dove 

Un’approssimazione lineare $h^*$ è quella con **distanza minima** da $f$, cioè con il **minimo numero di differenze**.

---

#### **3.3 Approssimazione delle S-box**

Ogni S-box del DES realizza una trasformazione:

$$  
S : \Sigma^6 \to \Sigma^4  
$$

ossia da 6 bit in ingresso a 4 bit in uscita.

Può essere vista come 4 **funzioni booleane**:

$$f_i : \Sigma^6 \to \Sigma$$

Ciascuna di queste funzioni potrà **MAPPARE** i $2^6$ input in un singolo bit di output.
La precedente scrittura è equivalente a rappresentare S come un **vettore** di 4 funzioni booleane:

$$  
S(s) = (f_4(s), f_3(s), f_2(s), f_1(s)) \ dove \ s=(s_1, s_2, s_3, s_4, s_5, s_6)
$$


Per ciascuna S-box si costruisce una **tabella delle distanze**, dove:

- le **righe** rappresentano le 64 possibili funzioni lineari in input;
    
- le **colonne** rappresentano le 16 combinazioni lineari dei bit di output.
    

Esempio:

- Riga $h_{31} = 110001$ → $l(s) = s_6 \oplus s_5 \oplus s_1$
    
- Colonna $h_9 = 1001$ → $f = f_4 \oplus f_1$
    

	La tabella consente di identificare le **migliori approssimazioni lineari** (quelle con la più alta probabilità che $l(s) = f(s)$).

![](imgs/Pasted%20image%2020260222115603.png)

Data una funzione booleana e la sua approssimazione e $d$ è il numero di valori in input per i quale la funzione differisce dalla sua approssimazione, posso valutare qual è la probabilità che $l$ ed $f$ siano uguali. Tale valore è dato da $(2^n - d) / 2^n$ ossia i casi favorevoli su tutti i casi possibili. Il caso peggiore si ha quando la migliore approssimazione l differisce di circa la metà per tutti i possibili valori, ovvero quando $d$ è circa $2^{n-1}$.

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