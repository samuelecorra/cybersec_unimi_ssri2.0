# **M1 UD6 Lezione 7 - Triangoli e trigonometria**

### **1. Introduzione**

La trigonometria nasce storicamente dallo studio dei triangoli. Anche se in Analisi 1 seno e coseno diventano funzioni reali, il loro significato geometrico nei triangoli resta fondamentale.

In questa lezione colleghiamo:

- angoli;
- lati;
- seno, coseno e tangente;
- teoremi fondamentali sui triangoli.

> 📌 Nei triangoli, la trigonometria permette di passare da angoli a lunghezze e da lunghezze ad angoli.

---

### **2. Triangolo rettangolo**

In un triangolo rettangolo, rispetto a un angolo acuto $\alpha$:

- l’ipotenusa è il lato opposto all’angolo retto;
- il cateto opposto ad $\alpha$ è il lato davanti all’angolo;
- il cateto adiacente ad $\alpha$ è il cateto che forma l’angolo insieme all’ipotenusa.

Le definizioni sono:

$$
\sin\alpha=\frac{\text{cateto opposto}}{\text{ipotenusa}}
$$

$$
\cos\alpha=\frac{\text{cateto adiacente}}{\text{ipotenusa}}
$$

$$
\tan\alpha=\frac{\text{cateto opposto}}{\text{cateto adiacente}}
$$

Queste definizioni coincidono con quelle sulla circonferenza goniometrica quando l’angolo è acuto.

<!-- TODO FIGURA:
Triangolo rettangolo con angolo acuto alpha, ipotenusa e cateti opposto e adiacente chiaramente etichettati. Affiancare un secondo schema con l'altro angolo acuto per mostrare che i ruoli dei cateti cambiano. Obiettivo didattico: collegare i rapporti trigonometrici all'angolo scelto.
-->

---

### **3. Ricavare lati da un angolo**

Supponiamo di conoscere l’ipotenusa $c$ e un angolo acuto $\alpha$.

Il cateto opposto vale:

$$
c\sin\alpha
$$

Il cateto adiacente vale:

$$
c\cos\alpha
$$

Per esempio, se:

$$
c=10,\quad \alpha=\frac{\pi}{6}
$$

allora:

$$
\text{cateto opposto}=10\sin\frac{\pi}{6}=10\cdot\frac{1}{2}=5
$$

e:

$$
\text{cateto adiacente}=10\cos\frac{\pi}{6}=10\cdot\frac{\sqrt{3}}{2}=5\sqrt{3}
$$

---

### **4. Ricavare angoli da lati**

Se conosciamo i lati, possiamo ricavare gli angoli usando le inverse goniometriche.

Per esempio, se in un triangolo rettangolo:

$$
\frac{\text{cateto opposto}}{\text{ipotenusa}}=\frac{1}{2}
$$

allora:

$$
\sin\alpha=\frac{1}{2}
$$

Poiché $\alpha$ è acuto:

$$
\alpha=\arcsin\frac{1}{2}=\frac{\pi}{6}
$$

Il fatto che l’angolo sia acuto elimina l’ambiguità periodica tipica delle equazioni goniometriche.

---

### **5. Teorema dei seni**

In un triangolo qualunque, con lati $a,b,c$ opposti rispettivamente agli angoli $\alpha,\beta,\gamma$, vale:

$$
\frac{a}{\sin\alpha}=\frac{b}{\sin\beta}=\frac{c}{\sin\gamma}
$$

Se $R$ è il raggio della circonferenza circoscritta, il valore comune è $2R$:

$$
\frac{a}{\sin\alpha}=\frac{b}{\sin\beta}=\frac{c}{\sin\gamma}=2R.
$$

#### **5.1 Dimostrazione**

Tracciamo l'altezza relativa al lato $c$. Essa vale sia $b\sin\alpha$ sia $a\sin\beta$. Quindi

$$
b\sin\alpha=a\sin\beta,
$$

da cui

$$
\frac{a}{\sin\alpha}=\frac{b}{\sin\beta}.
$$

Ripetendo il ragionamento con un'altra altezza si ottiene anche l'uguaglianza con $c/\sin\gamma$.

Se il triangolo è ottusangolo, una delle altezze cade sul prolungamento di un lato. Il calcolo resta valido perché l'angolo supplementare ha lo stesso seno: $\sin(\pi-\theta)=\sin\theta$.

Per identificare il valore comune, osserviamo che il lato $a$ è una corda della circonferenza circoscritta e sottende l'angolo alla circonferenza $\alpha$. L'angolo al centro corrispondente misura $2\alpha$; dimezzando il triangolo isoscele formato da due raggi si trova

$$
\frac{a}{2}=R\sin\alpha,
$$

cioè $a/\sin\alpha=2R$.

<!-- TODO FIGURA:
Triangolo ABC inscritto in una circonferenza di raggio R. Evidenziare il lato a, l'angolo alpha opposto, l'angolo al centro 2alpha e la metà della corda a/2. Obiettivo didattico: dimostrare a/sin alpha=2R.
-->

Questa relazione permette di trovare un lato se si conosce un altro lato e due angoli, oppure un angolo se si conoscono lati opportuni.

Esempio:

Se:

$$
a=6,\quad \alpha=\frac{\pi}{6},\quad \beta=\frac{\pi}{3}
$$

allora:

$$
\frac{a}{\sin\alpha}=\frac{b}{\sin\beta}
$$

quindi:

$$
b=a\cdot\frac{\sin\beta}{\sin\alpha}
$$

Sostituendo:

$$
b=6\cdot\frac{\sin\frac{\pi}{3}}{\sin\frac{\pi}{6}}
=6\cdot\frac{\frac{\sqrt{3}}{2}}{\frac{1}{2}}
=6\sqrt{3}
$$

---

### **6. Teorema del coseno**

In un triangolo qualunque vale:

$$
c^2=a^2+b^2-2ab\cos\gamma
$$

dove $\gamma$ è l’angolo compreso tra i lati $a$ e $b$.

Questo teorema generalizza il teorema di Pitagora.

#### **6.1 Dimostrazione vettoriale**

Poniamo un vertice nell'origine e rappresentiamo i lati adiacenti all'angolo $\gamma$ mediante vettori $u$ e $v$, con

$$
\|u\|=a,\qquad \|v\|=b.
$$

Il terzo lato è rappresentato da $u-v$, quindi

$$
c^2=\|u-v\|^2.
$$

Sviluppando il prodotto scalare,

$$
\|u-v\|^2=(u-v)\cdot(u-v)=\|u\|^2+\|v\|^2-2u\cdot v.
$$

Poiché $u\cdot v=ab\cos\gamma$, segue

$$
c^2=a^2+b^2-2ab\cos\gamma.
$$

La dimostrazione richiede soltanto la definizione geometrica del prodotto scalare, già usata per le formule di addizione.

<!-- TODO FIGURA:
Triangolo con vettori u e v uscenti dallo stesso vertice, angolo compreso gamma e vettore u-v rappresentante il terzo lato. Indicare lunghezze a, b, c. Obiettivo didattico: visualizzare la dimostrazione vettoriale del teorema del coseno.
-->

Infatti, se:

$$
\gamma=\frac{\pi}{2}
$$

allora:

$$
\cos\gamma=0
$$

e quindi:

$$
c^2=a^2+b^2
$$

---

### **7. Esempio con il teorema del coseno**

Siano:

$$
a=3,\quad b=5,\quad \gamma=\frac{\pi}{3}
$$

Allora:

$$
c^2=3^2+5^2-2\cdot 3\cdot 5\cos\frac{\pi}{3}
$$

Poiché:

$$
\cos\frac{\pi}{3}=\frac{1}{2}
$$

si ha:

$$
c^2=9+25-30\cdot\frac{1}{2}
$$

quindi:

$$
c^2=34-15=19
$$

Perciò:

$$
c=\sqrt{19}
$$

---

### **8. Area di un triangolo**

Se conosciamo due lati $a,b$ e l’angolo compreso $\gamma$, l’area del triangolo è:

$$
A=\frac{1}{2}ab\sin\gamma
$$

Questa formula deriva dall’idea base:

$$
A=\frac{1}{2}\cdot \text{base}\cdot \text{altezza}
$$

Se scegliamo come base $a$, l’altezza relativa è:

$$
b\sin\gamma
$$

quindi:

$$
A=\frac{1}{2}a\cdot b\sin\gamma
$$

La formula è simmetrica rispetto alla scelta dei lati:

$$
A=\frac{1}{2}ab\sin\gamma
=\frac{1}{2}bc\sin\alpha
=\frac{1}{2}ca\sin\beta.
$$

---

### **9. Angoli in un triangolo**

In ogni triangolo euclideo:

$$
\alpha+\beta+\gamma=\pi
$$

Questa relazione permette di trovare il terzo angolo quando se ne conoscono due.

Per esempio, se:

$$
\alpha=\frac{\pi}{4},\quad \beta=\frac{\pi}{6}
$$

allora:

$$
\gamma=\pi-\frac{\pi}{4}-\frac{\pi}{6}
$$

Portando a denominatore comune:

$$
\gamma=\frac{12\pi}{12}-\frac{3\pi}{12}-\frac{2\pi}{12}
$$

quindi:

$$
\gamma=\frac{7\pi}{12}
$$

### **10. Quali dati determinano un triangolo**

Un triangolo non è determinato da dati arbitrari. I casi principali sono:

- **SSS**: tre lati; si verifica prima la disuguaglianza triangolare e poi si usa il teorema del coseno;
- **SAS**: due lati e l'angolo compreso; il teorema del coseno determina il terzo lato;
- **ASA** o **AAS**: due angoli e un lato; il terzo angolo segue dalla somma $\pi$ e i lati dal teorema dei seni;
- **SSA**: due lati e un angolo non compreso; possono esistere zero, uno o due triangoli.

Per tre lunghezze positive $a,b,c$ esiste un triangolo non degenere se e solo se

$$
a<b+c,\qquad b<a+c,\qquad c<a+b.
$$

Se una delle uguaglianze è esatta, i vertici sono allineati e il triangolo è degenere.

### **11. Il caso ambiguo SSA**

Supponiamo noti $a$, $b$ e l'angolo $\alpha$ opposto ad $a$. Dal teorema dei seni,

$$
\sin\beta=\frac{b\sin\alpha}{a}.
$$

Se il membro destro è maggiore di $1$, non esiste alcun triangolo. Se appartiene a $(0,1)$, l'equazione per $\beta$ può avere due soluzioni in $(0,\pi)$:

$$
\beta_1=\arcsin\left(\frac{b\sin\alpha}{a}\right),
$$

$$
\beta_2=\pi-\beta_1.
$$

La seconda soluzione è ammissibile soltanto se $\alpha+\beta_2<\pi$.

#### **11.1 Esempio con due triangoli**

Siano

$$
a=8,\qquad b=10,\qquad \alpha=\frac{\pi}{6}.
$$

Allora

$$
\sin\beta=\frac{10\cdot\frac12}{8}=\frac58.
$$

Si ottengono

$$
\beta_1=\arcsin\frac58\approx 0{,}675,
$$

$$
\beta_2=\pi-\beta_1\approx 2{,}466.
$$

Entrambe soddisfano $\alpha+\beta<\pi$, quindi esistono due triangoli. I rispettivi terzi angoli sono circa $1{,}943$ e $0{,}152$ radianti; dal teorema dei seni si ottengono due diversi valori del terzo lato, circa $14{,}90$ e $2{,}42$.

<!-- TODO FIGURA:
Caso ambiguo SSA con base comune b=10, lato a=8 e angolo alpha=pi/6. Disegnare le due possibili posizioni del vertice ottenute dall'intersezione tra una semiretta e una circonferenza di raggio a. Obiettivo didattico: mostrare perché gli stessi dati possono generare due triangoli.
-->

### **12. Esempio SSS completo**

Siano $a=5$, $b=6$, $c=7$. Le disuguaglianze triangolari sono soddisfatte. Per trovare l'angolo $\gamma$ opposto a $c$, dal teorema del coseno:

$$
\cos\gamma=\frac{a^2+b^2-c^2}{2ab}
=\frac{25+36-49}{60}
=\frac15.
$$

Quindi

$$
\gamma=\arccos\frac15.
$$

Gli altri angoli si possono calcolare analogamente con il teorema del coseno. Usare il teorema dei seni dopo avere trovato un solo angolo è possibile, ma richiede attenzione all'ambiguità dell'arcoseno.

### **13. Collegamenti futuri**

La forma trigonometrica dei numeri complessi userà modulo e argomento per descrivere punti del piano; la formula di De Moivre fornirà una seconda lettura delle formule di addizione. Nei moduli di Analisi, seno e coseno compariranno nei limiti fondamentali, nelle derivate, negli sviluppi di Taylor, negli integrali e nei modelli oscillatori.

---

### **14. Errori tipici**

#### **14.1 Usare gradi e radianti insieme**

Se si lavora in radianti, tutti gli angoli devono essere in radianti. Mescolare $60^\circ$ e $\frac{\pi}{4}$ nello stesso calcolo senza conversione genera errori.

#### **14.2 Confondere lato opposto e lato adiacente**

Le definizioni di seno, coseno e tangente dipendono dall’angolo scelto. Cambiando angolo, cambiano i ruoli dei cateti.

#### **14.3 Usare il teorema del coseno con l’angolo sbagliato**

Nella formula:

$$
c^2=a^2+b^2-2ab\cos\gamma
$$

l’angolo $\gamma$ deve essere quello compreso tra $a$ e $b$.

#### **14.4 Ignorare il caso ambiguo**

Da $\sin\beta=s$ non segue necessariamente $\beta=\arcsin s$: nell'intervallo $(0,\pi)$ può essere ammesso anche $\pi-\arcsin s$.

#### **14.5 Non verificare l'esistenza geometrica**

Tre lati devono soddisfare le disuguaglianze triangolari; nel caso SSA bisogna verificare che la somma degli angoli resti minore di $\pi$.

---

### **15. Riepilogo**

> ✅ Nei triangoli, seno, coseno e tangente collegano angoli e lati.

Formule principali:

$$
\sin\alpha=\frac{\text{cateto opposto}}{\text{ipotenusa}}
$$

$$
\cos\alpha=\frac{\text{cateto adiacente}}{\text{ipotenusa}}
$$

$$
\tan\alpha=\frac{\text{cateto opposto}}{\text{cateto adiacente}}
$$

$$
\frac{a}{\sin\alpha}=\frac{b}{\sin\beta}=\frac{c}{\sin\gamma}
$$

$$
c^2=a^2+b^2-2ab\cos\gamma
$$

$$
A=\frac{1}{2}ab\sin\gamma
$$
