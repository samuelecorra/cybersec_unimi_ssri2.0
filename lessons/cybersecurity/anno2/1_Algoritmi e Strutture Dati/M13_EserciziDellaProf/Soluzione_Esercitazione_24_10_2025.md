# Soluzione dell'esercitazione — 24 ottobre 2025

Esercitazione di **Algoritmi e Strutture Dati** sulle **tabelle hash con indirizzamento aperto**: la stessa sequenza di chiavi viene inserita con tre metodi di scansione interna diversi — lineare, quadratica e hashing doppio — per confrontarne il comportamento in presenza di collisioni.

> **Riferimenti di teoria:** [M05/UD2 — Tabelle Hash](../M05_DS_Orizzontali/UD2/L2_Tabelle_Hash.md), [M05/UD2 — Dizionari con vettori ordinati](../M05_DS_Orizzontali/UD2/L1_Dizionari_con_vettori_ordinati.md).

---

### **1. Traccia comune**

Inserire nell'ordine indicato le chiavi

$$
58,\; 37,\; 67,\; 29,\; 78,\; 50
$$

in una tabella hash di dimensione $m = 10$ (posizioni da $0$ a $9$), con funzione hash primaria

$$
h'(k) = k \bmod 10 .
$$

Si usi l'**indirizzamento aperto**: tutte le chiavi risiedono nel vettore, senza liste di trabocco; in caso di collisione si cerca una posizione alternativa secondo un metodo di scansione.

I valori di $h'$ sulle sei chiavi sono:

| $k$ | 58 | 37 | 67 | 29 | 78 | 50 |
|---|---:|---:|---:|---:|---:|---:|
| $h'(k) = k \bmod 10$ | 8 | 7 | **7** | 9 | **8** | **0** |

Tre chiavi collidono già in partenza: $67$ con $37$ (entrambe in $7$) e $78$ con $58$ (entrambe in $8$). Il $50$ finisce in $0$, posizione che nei primi due metodi risulterà nel frattempo occupata.

> 💡 Con $h'(k)=k \bmod 10$ l'indirizzo dipende **solo dall'ultima cifra decimale** della chiave. È una funzione hash didatticamente comoda ma pessima nella pratica: qualunque insieme di chiavi che condivida l'ultima cifra collassa in un'unica cella. È esattamente il motivo per cui la teoria raccomanda $m$ **primo** e lontano dalle potenze della base di rappresentazione.

---

### **2. Metodo 1 — Scansione lineare**

#### **2.1. Formula**

$$
h(k,i) = \bigl(h'(k) + i\bigr) \bmod m, \qquad i = 0, 1, 2, \dots, m-1
$$

La sequenza di ispezione parte dalla posizione naturale $h'(k)$ e prosegue **una cella alla volta**, tornando all'inizio dopo l'ultima (aritmetica modulare).

#### **2.2. Inserimenti passo per passo**

| Chiave | $h'(k)$ | Sequenza di ispezione | Esito |
|---:|---:|---|---|
| $58$ | 8 | $i=0 \to 8$ | libera: **58 in 8** |
| $37$ | 7 | $i=0 \to 7$ | libera: **37 in 7** |
| $67$ | 7 | $i=0 \to 7$ (occupata da 37), $i=1 \to 8$ (occupata da 58), $i=2 \to 9$ | libera: **67 in 9** |
| $29$ | 9 | $i=0 \to 9$ (occupata da 67), $i=1 \to (9+1) \bmod 10 = 0$ | libera: **29 in 0** |
| $78$ | 8 | $i=0 \to 8$, $i=1 \to 9$, $i=2 \to 0$, $i=3 \to 1$ | libera: **78 in 1** |
| $50$ | 0 | $i=0 \to 0$ (occupata da 29), $i=1 \to 1$ (occupata da 78), $i=2 \to 2$ | libera: **50 in 2** |

#### **2.3. Tabella risultante**

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Contenuto | 29 | 78 | 50 | | | | | 37 | 58 | 67 |

#### **2.4. Osservazioni**

Il numero di ispezioni cresce rapidamente: $58$ e $37$ richiedono una sola prova, $67$ ne richiede tre, $78$ ben quattro. La causa è il **clustering primario**: le celle occupate tendono ad addensarsi in blocchi contigui, e ogni nuova chiave che cade dentro o appena prima di un blocco deve attraversarlo tutto.

Nella tabella finale si nota il blocco $7,8,9,0,1,2$: sei celle consecutive (in senso circolare) tutte piene. Una settima chiave con $h'(k) \in \{7,8,9,0,1\}$ dovrebbe percorrere l'intero blocco prima di trovare posto.

> ⚠️ Con l'indirizzamento aperto la **cancellazione** non può azzerare semplicemente la cella: interromperebbe la catena di ispezione e renderebbe irraggiungibili le chiavi inserite dopo. Si usa un marcatore speciale (`DELETED`), che la ricerca attraversa e l'inserimento può riutilizzare.

---

### **3. Metodo 2 — Scansione quadratica**

#### **3.1. Formula**

$$
h(k,i) = \bigl(h'(k) + c_1 \cdot i + c_2 \cdot i^{2}\bigr) \bmod m
$$

Nell'esercizio si pone $c_1 = 0$ e $c_2 = 1$, per cui la formula si riduce a

$$
h(k,i) = \bigl((k \bmod m) + i^{2}\bigr) \bmod m .
$$

Lo scostamento dalla posizione iniziale segue i quadrati perfetti $0, 1, 4, 9, 16, \dots$ anziché i numeri naturali.

#### **3.2. Inserimenti passo per passo**

| Chiave | $h'(k)$ | Sequenza di ispezione | Esito |
|---:|---:|---|---|
| $58$ | 8 | $i=0 \to 8+0=8$ | libera: **58 in 8** |
| $37$ | 7 | $i=0 \to 7$ | libera: **37 in 7** |
| $67$ | 7 | $i=0 \to 7$ (occupata), $i=1 \to 7+1=8$ (occupata), $i=2 \to (7+4) \bmod 10 = 1$ | libera: **67 in 1** |
| $29$ | 9 | $i=0 \to 9$ | libera: **29 in 9** |
| $78$ | 8 | $i=0 \to 8$ (occupata), $i=1 \to 9$ (occupata), $i=2 \to (8+4)\bmod 10 = 2$ | libera: **78 in 2** |
| $50$ | 0 | $i=0 \to 0$ | libera: **50 in 0** |

#### **3.3. Tabella risultante**

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Contenuto | 50 | 67 | 78 | | | | | 37 | 58 | 29 |

#### **3.4. Osservazioni**

Rispetto alla scansione lineare il risultato è visibilmente migliore: nessuna chiave richiede più di tre ispezioni e il blocco contiguo si è spezzato. Il salto quadratico allontana rapidamente la sequenza di ispezione dal punto di collisione, eliminando il clustering primario.

Rimane però il **clustering secondario**: due chiavi con lo stesso $h'(k)$ generano **esattamente la stessa** sequenza di ispezione, perché lo scostamento $i^2$ non dipende dalla chiave. Nell'esercizio $37$ e $67$ collidono in $7$ e da quel momento seguono lo stesso percorso $7 \to 8 \to 1 \to \dots$

> ⚠️ Con $c_1=0$, $c_2=1$ e $m=10$ la sequenza di ispezione **non copre tutta la tabella**: i quadrati modulo $10$ valgono $0,1,4,9,6,5,6,9,4,1$, cioè assumono solo i valori $\{0,1,4,5,6,9\}$. Un inserimento può quindi fallire anche in presenza di celle libere. La condizione classica che garantisce la copertura di almeno metà tabella è $m$ primo con $c_1=c_2=1/2$, oppure $m$ potenza di due con $c_1=c_2=1/2$.

---

### **4. Metodo 3 — Hashing doppio**

#### **4.1. Formula**

$$
h(k,i) = \bigl(h_1(k) + i \cdot h_2(k)\bigr) \bmod m
$$

con

$$
h_1(k) = k \bmod 10, \qquad h_2(k) = 7 - (k \bmod 7).
$$

La differenza sostanziale rispetto ai due metodi precedenti è che **il passo di scansione dipende dalla chiave**: due chiavi che collidono nella posizione iniziale proseguono su percorsi diversi.

#### **4.2. Calcolo della funzione secondaria**

| $k$ | $k \bmod 7$ | $h_2(k) = 7 - (k \bmod 7)$ |
|---:|---:|---:|
| $58$ | $58 = 8\cdot7+2 \Rightarrow 2$ | 5 |
| $37$ | $37 = 5\cdot7+2 \Rightarrow 2$ | 5 |
| $67$ | $67 = 9\cdot7+4 \Rightarrow 4$ | **3** |
| $29$ | $29 = 4\cdot7+1 \Rightarrow 1$ | 6 |
| $78$ | $78 = 11\cdot7+1 \Rightarrow 1$ | **6** |
| $50$ | $50 = 7\cdot7+1 \Rightarrow 1$ | **6** |

> 📌 La forma $h_2(k) = q - (k \bmod q)$ con $q$ primo minore di $m$ è la scelta standard: restituisce sempre un valore in $\{1, \dots, q\}$, quindi **mai zero** — un passo nullo bloccherebbe la scansione sulla stessa cella all'infinito.

#### **4.3. Inserimenti passo per passo**

| Chiave | $h_1$ | $h_2$ | Sequenza di ispezione | Esito |
|---:|---:|---:|---|---|
| $58$ | 8 | 5 | $i=0 \to 8$ | libera: **58 in 8** |
| $37$ | 7 | 5 | $i=0 \to 7$ | libera: **37 in 7** |
| $67$ | 7 | 3 | $i=0 \to 7$ (occupata), $i=1 \to (7 + 1\cdot3) \bmod 10 = 0$ | libera: **67 in 0** |
| $29$ | 9 | 6 | $i=0 \to 9$ | libera: **29 in 9** |
| $78$ | 8 | 6 | $i=0 \to 8$ (occupata), $i=1 \to (8 + 1\cdot 6) \bmod 10 = 4$ | libera: **78 in 4** |
| $50$ | 0 | 6 | $i=0 \to 0$ (occupata da 67), $i=1 \to (0 + 1\cdot 6) \bmod 10 = 6$ | libera: **50 in 6** |

#### **4.4. Tabella risultante**

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Contenuto | 67 | | | | 78 | | 50 | 37 | 58 | 29 |

#### **4.5. Osservazioni**

Nessuna chiave richiede più di **due** ispezioni e le posizioni occupate sono distribuite su tutta la tabella. Il motivo è che l'hashing doppio elimina anche il clustering secondario: $37$ e $67$ collidono in $7$, ma da lì in poi la prima procede a passi di $5$ e la seconda a passi di $3$.

> ⚠️ La scelta $m=10$ e $h_2(k) \in \{1,\dots,7\}$ **non garantisce** che la scansione visiti tutta la tabella. La sequenza $h_1, h_1+h_2, h_1+2h_2, \dots$ copre tutte le $m$ posizioni solo se $\mathrm{MCD}(h_2(k), m) = 1$. Con $m=10$ i valori accettabili sono $h_2 \in \{1,3,7,9\}$, ma la funzione può restituire anche $2,4,5,6$. Per la chiave $78$ si ha $h_2=6$ e $\mathrm{MCD}(6,10)=2$: la sequenza è $8, 4, 0, 6, 2, 8, \dots$, cioè visita solo le **cinque celle pari**. Se tutte fossero occupate, l'inserimento fallirebbe pur essendoci celle dispari libere.
>
> La correzione consiste nello scegliere $m$ primo (allora $\mathrm{MCD}(h_2,m)=1$ per ogni $h_2 < m$), oppure $m$ potenza di $2$ con $h_2$ sempre dispari.

---

### **5. Confronto dei tre metodi**

| | Scansione lineare | Scansione quadratica | Hashing doppio |
|---|---|---|---|
| Formula | $h'(k)+i$ | $h'(k)+i^{2}$ | $h_1(k)+i\,h_2(k)$ |
| Ispezioni totali per le 6 chiavi | $1+1+3+2+4+3 = 14$ | $1+1+3+1+3+1 = 10$ | $1+1+2+1+2+2 = 9$ |
| Clustering primario | presente | assente | assente |
| Clustering secondario | presente | presente | assente |
| Sequenze di ispezione distinte | $m$ | $m$ | fino a $m^{2}$ |
| Località in memoria (cache) | ottima | media | scarsa |

Le tre tabelle finali a confronto:

| Indice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| lineare | 29 | 78 | 50 | | | | | 37 | 58 | 67 |
| quadratica | 50 | 67 | 78 | | | | | 37 | 58 | 29 |
| doppio | 67 | | | | 78 | | 50 | 37 | 58 | 29 |

#### **5.1. Costo atteso in funzione del fattore di carico**

Detto $\alpha = n/m$ il **fattore di carico** (nel nostro caso $\alpha = 6/10 = 0.6$), sotto l'ipotesi di hashing uniforme il numero atteso di ispezioni è:

$$
\text{ricerca senza successo:}\quad \frac{1}{1-\alpha}, \qquad
\text{ricerca con successo:}\quad \frac{1}{\alpha}\ln\frac{1}{1-\alpha}.
$$

Con $\alpha = 0.6$ si ottengono rispettivamente $2.5$ e circa $1.53$ ispezioni. Le formule valgono per l'hashing uniforme, che l'hashing doppio approssima bene mentre la scansione lineare, per via del clustering, peggiora sensibilmente all'aumentare di $\alpha$.

> ✅ Regola pratica: mantenere $\alpha \le 0.7$–$0.75$ e ricostruire la tabella (**rehashing**) raddoppiando $m$ quando la soglia viene superata. Il costo della ricostruzione, $\Theta(n)$, si ammortizza sugli inserimenti che l'hanno provocata, mantenendo $O(1)$ ammortizzato per operazione.
