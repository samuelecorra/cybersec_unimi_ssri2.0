# **M9 UD5 Lezione 2 - Teorema di Rolle**

### **1. Obiettivo della lezione**

Il primo teorema di **esistenza** per la derivata: sotto tre ipotesi precise, da qualche parte la tangente è orizzontale. Rolle è il mattone su cui poggiano Lagrange e Cauchy (Lezioni 3 e 5): la sua dimostrazione — Weierstrass più Fermat — è tra le più richieste agli orali.

---
### **2. Enunciato**

**Teorema (Rolle).** Sia $f:[a,b]\to\mathbb{R}$ tale che:

1. $f$ è **continua** su $[a,b]$ (chiuso, bordi compresi);
2. $f$ è **derivabile** su $(a,b)$ (aperto: ai bordi la derivabilità non serve);
3. $f(a)=f(b)$.

Allora esiste **almeno un** punto $c\in(a,b)$ tale che

$$
f'(c)=0
$$

Lettura geometrica: se il grafico parte e arriva alla stessa quota, in mezzo c'è almeno un punto a tangente orizzontale. Lettura cinematica: se torni al punto di partenza, in qualche istante la velocità è stata nulla.

---
### **3. Dimostrazione**

*Dimostrazione.* Per l'ipotesi 1 e il teorema di **Weierstrass** (M08/UD5/L5), $f$ assume su $[a,b]$ massimo $M$ e minimo $m$:

$$
m = f(x_m) \le f(x) \le f(x_M) = M \qquad \forall x\in[a,b]
$$

**Caso 1: $M=m$.** Allora $f$ è costante su $[a,b]$, e $f'(c)=0$ per **ogni** $c\in(a,b)$: tesi verificata in abbondanza.

**Caso 2: $M>m$.** Almeno uno dei due valori è diverso dal valore comune ai bordi $f(a)=f(b)$. Supponiamo $M\neq f(a)$ (l'altro caso è simmetrico): allora il punto di massimo $x_M$ non può essere né $a$ né $b$ (lì la funzione vale $f(a)\neq M$), quindi $x_M\in(a,b)$ è **interno**. In $x_M$ la funzione è derivabile (ipotesi 2) e ha un massimo (globale, quindi locale): per il **teorema di Fermat** (Lezione 1),

$$
f'(x_M)=0
$$

e $c=x_M$ conclude. $\square$

> 📌 Struttura della prova, da saper raccontare: Weierstrass fornisce gli estremi (serve la continuità sul **chiuso**), l'ipotesi $f(a)=f(b)$ spinge un estremo **all'interno**, Fermat lo trasforma in un punto stazionario (serve la derivabilità **all'interno**, e basta lì). Ogni ipotesi lavora in un punto preciso della dimostrazione.

<!-- TODO FIGURA:
Grafico di una funzione continua su [a,b] con f(a)=f(b), un massimo interno con tangente orizzontale evidenziata in c; la quota comune f(a)=f(b) tratteggiata. Tre mini-riquadri sotto con i controesempi: funzione con salto in un bordo (ipotesi 1 violata), |x| su [-1,1] (ipotesi 2 violata, lo spigolo evita la tangente orizzontale), identita' su [0,1] (ipotesi 3 violata).
Obiettivo didattico: visualizzare l'enunciato di Rolle e mostrare che togliendo una qualunque delle tre ipotesi la tesi puo' fallire.
-->

---
### **4. Ogni ipotesi è irrinunciabile: i controesempi**

| Ipotesi tolta | Controesempio | Cosa succede |
|---|---|---|
| Continuità sul chiuso | $f(x)=x$ su $[0,1)$, $f(1)=0$ | $f(0)=f(1)=0$, derivabile su $(0,1)$ con $f'\equiv 1$: **nessun** $c$; la discontinuità nel bordo $1$ rompe Weierstrass |
| Derivabilità sull'aperto | $f(x)=\vert x\vert$ su $[-1,1]$ | continua, $f(-1)=f(1)=1$, ma $f'=\pm 1$ dove esiste: il minimo cade nello **spigolo** $0$, dove Fermat non può agire |
| $f(a)=f(b)$ | $f(x)=x$ su $[0,1]$ | continua e derivabile, ma $f'\equiv 1$: senza quota comune nessun estremo è costretto all'interno |

Nota di finezza: le ipotesi sono **sufficienti**, non necessarie — $f(x)=x^3$ su $[-1,1]$ non soddisfa la 3 ($f(-1)=-1\neq 1=f(1)$) eppure $f'(0)=0$. I controesempi mostrano che le ipotesi non si possono **rimuovere in blocco**, non che senza di esse la tesi sia sempre falsa.

---
### **5. Applicazioni tipiche**

#### **5.1 Zeri della derivata tra zeri della funzione**

Se $f$ è continua su $[a,b]$, derivabile all'interno, e $x_1<x_2$ sono due **zeri** di $f$, allora tra essi c'è uno zero di $f'$ (Rolle su $[x_1,x_2]$, con $f(x_1)=f(x_2)=0$). Conseguenza contrapposta, preziosa per contare le radici: **tra due zeri consecutivi di $f'$ c'è al più uno zero di $f$** — se ce ne fossero due, Rolle fabbricherebbe uno zero di $f'$ in mezzo, contro la consecutività.

**Esempio.** $p(x)=x^3-3x+1$: $p'=3x^2-3$ ha zeri $\pm 1$, quindi $p$ ha al più uno zero in ciascuno dei tre intervalli $(-\infty,-1)$, $(-1,1)$, $(1,+\infty)$ — al più tre zeri in tutto; i cambi di segno ($p(-2)=-1$, $p(-1)=3$, $p(1)=-1$, $p(2)=3$) ne garantiscono esattamente tre (teorema degli zeri, M08).

#### **5.2 Il "lemma della corda" chiuso**

L'esercizio 3 di M08/UD5/L4 (esiste $c$ con $f(c)=f(c+1)$ se $f(0)=f(2)$) era Rolle travestito: ora possiamo riconoscerlo come caso discreto del teorema.

---
### **6. Errori frequenti**

- ❌ Chiedere la derivabilità anche nei bordi: l'ipotesi giusta è su $(a,b)$ — e ci sono applicazioni (radici quadrate ai bordi) dove questa generosità serve davvero.
- ❌ Dimenticare di **verificare** le ipotesi prima di invocare il teorema (specie la continuità nei bordi di funzioni definite a tratti).
- ❌ Concludere l'**unicità** di $c$: Rolle dà "almeno uno" ($\sin$ su $[0,2\pi]$ ne ha due).
- ❌ Usare Rolle su domini che non sono intervalli chiusi e limitati.
- ❌ Invertire il teorema: "c'è un punto a tangente orizzontale, quindi $f(a)=f(b)$" (falso: $x^3$ su $[-1,2]$).

---
### **7. Esercizi**

**Esercizio 1.** Verifica le ipotesi di Rolle per $f(x)=x^2-4x+3$ su $[1,3]$ e trova i punti $c$.

*Soluzione.* Polinomio: continuo e derivabile ovunque; $f(1)=0=f(3)$ ✓. $f'(x)=2x-4=0$ dà $c=2\in(1,3)$, unico.

**Esercizio 2.** Si può applicare Rolle a $f(x)=1-|x|$ su $[-1,1]$? La tesi vale comunque?

*Soluzione.* No: non derivabile in $0$. E la tesi **fallisce**: $f'=\mp 1$ su ogni lato, mai nulla — il massimo sta nello spigolo. (Controesempio vivo dell'ipotesi 2.)

**Esercizio 3.** Dimostra che $f(x)=x^5+x-1$ ha esattamente uno zero reale.

*Soluzione.* Esistenza: $f(0)=-1<0<1=f(1)$ e teorema degli zeri. Unicità: se ci fossero due zeri, Rolle darebbe $c$ con $f'(c)=5c^4+1=0$, impossibile ($f'\ge 1$). Quindi uno zero esatto.

**Esercizio 4.** Sia $f$ derivabile su $\mathbb{R}$ con **tre** zeri distinti. Dimostra che $f''$ ha almeno uno zero.

*Soluzione.* Rolle sui due intervalli tra zeri consecutivi dà due zeri distinti di $f'$; Rolle applicato a $f'$ tra questi due (con le ipotesi ereditate: serve $f$ derivabile due volte — assumendolo, come nel testo implicito) dà uno zero di $f''$. Se il testo dà solo $f$ derivabile, l'enunciato va corretto richiedendo $f\in C^1$ con $f'$ derivabile: le ipotesi si controllano sempre.

---
### **8. Riepilogo**

> ✅ Rolle: continua su $[a,b]$ + derivabile su $(a,b)$ + $f(a)=f(b)$ $\Rightarrow$ esiste $c$ interno con $f'(c)=0$; prova = Weierstrass (estremi) + caso costante + Fermat sull'estremo interno. Tre controesempi, uno per ipotesi (salto al bordo, $|x|$, identità); "almeno uno", non unico; ipotesi sufficienti, non necessarie. Applicazioni: zeri di $f'$ tra zeri di $f$ e conteggio delle radici. È il trampolino per Lagrange: basta "inclinare" il teorema.
