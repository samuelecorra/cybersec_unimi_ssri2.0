# **M9 UD5 Lezione 1 - Teorema di Fermat e punti critici**

### **1. Obiettivo dell'unità e della lezione**

La UD5 è il cuore teorico del calcolo differenziale: i teoremi che trasformano informazioni **puntuali** sulla derivata in informazioni **globali** sulla funzione. Si parte dal teorema di Fermat, il ponte tra estremi locali e derivata nulla, con il vocabolario dei punti critici.

---
### **2. Estremi locali: richiamo e precisazione**

**Definizione.** $x_0\in A$ è punto di **massimo locale** per $f$ se esiste $\delta>0$ tale che

$$
f(x)\le f(x_0) \qquad \text{per ogni } x\in A \text{ con } |x-x_0|<\delta
$$

(minimo locale con $\ge$; massimo/minimo **globale** se la disuguaglianza vale su tutto $A$: M07). Un estremo globale è anche locale; il viceversa è falso.

---
### **3. Il teorema di Fermat**

**Teorema (Fermat).** Sia $x_0$ un punto di massimo o minimo locale per $f$, **interno** al dominio, e sia $f$ **derivabile** in $x_0$. Allora

$$
f'(x_0)=0
$$

*Dimostrazione* (caso massimo locale; il minimo è simmetrico)*.* Poiché $x_0$ è interno, per $h$ abbastanza piccolo (di entrambi i segni) il punto $x_0+h$ sta nel dominio e nell'intorno dell'ipotesi, quindi

$$
f(x_0+h)-f(x_0)\le 0
$$

Studiamo il rapporto incrementale nei due lati:

- per $h>0$: $\dfrac{f(x_0+h)-f(x_0)}{h}\le 0$ (numeratore $\le 0$, denominatore $>0$); passando al limite $h\to 0^+$, per la permanenza delle disuguaglianze larghe (M08): $f'_+(x_0)\le 0$;
- per $h<0$: $\dfrac{f(x_0+h)-f(x_0)}{h}\ge 0$ (denominatore $<0$); al limite $h\to 0^-$: $f'_-(x_0)\ge 0$.

Ma $f$ è derivabile in $x_0$: le due laterali coincidono con $f'(x_0)$ (UD1/L2), quindi $f'(x_0)\le 0$ e $f'(x_0)\ge 0$ insieme: $f'(x_0)=0$. $\square$

Geometricamente: in un estremo interno derivabile la tangente è **orizzontale** — la pendenza non può essere positiva (a destra si salirebbe oltre il massimo) né negativa (a sinistra idem).

<!-- TODO FIGURA:
Grafico di una funzione con massimo locale interno in x_0 e tangente orizzontale; a sinistra di x_0 alcune secanti con pendenza positiva, a destra con pendenza negativa, entrambe che si appiattiscono verso la tangente. Due riquadri di contrasto: |x| con minimo in 0 senza tangente (punto non derivabile) e una funzione crescente su [a,b] con massimo nel bordo b e tangente non orizzontale.
Obiettivo didattico: visualizzare la dimostrazione di Fermat (segni dei rapporti incrementali laterali) e i due modi in cui le ipotesi possono cadere: punto non derivabile, punto di bordo.
-->

---
### **4. Le ipotesi sono tutte essenziali**

- **"Interno" è essenziale**: $f(x)=x$ su $[0,1]$ ha massimo in $x_0=1$ (bordo) con $f'(1)=1\neq 0$. Ai bordi Fermat **non si applica**: vale solo la versione unilaterale delle disuguaglianze ($f'_-(b)\ge 0$ in un massimo al bordo destro), non l'annullamento;
- **"Derivabile" è essenziale**: $f(x)=|x|$ ha minimo (anche globale) in $0$, dove la derivata non esiste. L'estremo c'è, la tangente orizzontale no.

E il teorema è una **condizione necessaria, non sufficiente**:

- $f(x)=x^3$ ha $f'(0)=0$ ma $0$ non è estremo (la funzione è strettamente crescente): la derivata nulla segnala un *candidato*, non certifica nulla.

---
### **5. Il vocabolario dei punti critici**

**Convenzione adottata** (da dichiarare, perché i testi divergono):

- **punto stazionario**: $x_0$ interno con $f'(x_0)=0$;
- **punto critico**: $x_0$ interno in cui $f'(x_0)=0$ **oppure** $f$ non è derivabile (alcuni testi chiamano "critico" solo lo stazionario: qui adottiamo la versione larga, più utile per l'ottimizzazione);
- **candidato estremo** su un intervallo $[a,b]$: punto critico interno **oppure** estremo del dominio ($a$, $b$).

**Corollario operativo (di Fermat).** Gli estremi locali di $f$ su $[a,b]$ possono trovarsi **solo** nei candidati: punti stazionari, punti di non derivabilità, bordi. È la base del metodo di ottimizzazione della UD6: Weierstrass garantisce che max e min globali esistono (continuità su $[a,b]$, M08/UD5), Fermat restringe la caccia a una lista finita di candidati, e il confronto dei valori chiude.

---
### **6. Errori frequenti**

- ❌ Applicare Fermat ai **bordi** (l'errore singolo più frequente negli scritti di ottimizzazione).
- ❌ Usare Fermat al contrario: "$f'(x_0)=0$ quindi $x_0$ è un estremo" ($x^3$!). Servono criteri **sufficienti**: cambio di segno di $f'$ (Lezione 4) o derivata seconda (cenno in Lezione 4, teoria completa con Taylor in M10).
- ❌ Dimenticare i punti **non derivabili** dalla lista dei candidati ($|x|$ ha il minimo proprio lì).
- ❌ Cercare gli estremi risolvendo solo $f'=0$ su un dominio **aperto o illimitato**, dove il massimo può non esistere affatto (Weierstrass non applicabile: serve lo studio del comportamento agli estremi, come in UD6/L2).
- ❌ Confondere massimo **locale** e **globale** nella risposta finale.

---
### **7. Esercizi**

**Esercizio 1.** Trova tutti i candidati estremi di $f(x)=x^3-3x$ su $[-2,3]$ e determina max e min globali.

*Soluzione.* $f'=3x^2-3=0$ in $x=\pm 1$ (interni ✓); nessun punto di non derivabilità; bordi $-2,3$. Valori: $f(-1)=2$, $f(1)=-2$, $f(-2)=-2$, $f(3)=18$. Max globale $18$ in $x=3$ (bordo!), min globale $-2$ assunto in **due** punti, $x=1$ e $x=-2$.

**Esercizio 2.** Trova gli estremi di $f(x)=|x^2-1|$ su $[-2,2]$.

*Soluzione.* Candidati: stazionari di ciascun ramo ($x=0$, con $f(0)=1$), punti angolosi $x=\pm 1$ ($f=0$), bordi $\pm 2$ ($f=3$). Min globale $0$ in $\pm 1$ (punti **non derivabili**), max globale $3$ ai bordi, e $x=0$ è massimo locale interno (lo confermerà il criterio della Lezione 4).

**Esercizio 3.** Vero o falso: "se $x_0$ è interno, $f$ è derivabile in $x_0$ e $x_0$ è l'unico punto con $f'(x_0)=0$, allora $x_0$ è un estremo globale".

*Soluzione.* Falso: $f(x)=x^3$ su $\mathbb{R}$ ($x_0=0$ unico stazionario, nessun estremo). Anche con un estremo locale la globalità non è garantita su domini illimitati.

**Esercizio 4.** Dimostra la versione "di bordo" di Fermat: se $f:[a,b]\to\mathbb{R}$ ha massimo locale in $b$ ed esiste $f'_-(b)$, allora $f'_-(b)\ge 0$.

*Soluzione.* Per $h<0$ piccolo: $f(b+h)\le f(b)$, rapporto $\frac{f(b+h)-f(b)}{h}\ge 0$; al limite $h\to 0^-$ resta $f'_-(b)\ge 0$. (Solo metà della prova di Fermat: manca il lato destro, e infatti non si conclude l'annullamento.)

---
### **8. Riepilogo**

> ✅ Fermat: estremo locale + punto interno + derivabilità $\Rightarrow$ $f'(x_0)=0$; prova con i segni dei rapporti incrementali laterali e la permanenza delle disuguaglianze. Necessaria ma non sufficiente ($x^3$); cade ai bordi ($x$ su $[0,1]$) e nei punti non derivabili ($|x|$). Vocabolario fissato: stazionario ($f'=0$), critico (stazionario o non derivabile), candidati $=$ critici interni $+$ bordi. Con Weierstrass, Fermat riduce l'ottimizzazione su $[a,b]$ al confronto di una lista finita. Prossima lezione: Rolle, il primo teorema di esistenza per la derivata.
