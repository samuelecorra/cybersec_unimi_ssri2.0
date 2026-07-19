# **M1 UD1 Lezione 0 - Equivalenza, equazioni e sistemi**

### **1. Perché distinguere calcolo e logica**

Risolvere un'equazione o una disequazione non significa soltanto trasformare simboli: significa sostituire il problema iniziale con problemi che conservino esattamente le stesse soluzioni, oppure controllare alla fine gli eventuali candidati prodotti da passaggi non reversibili. Questa distinzione diventa indispensabile con denominatori, radicali, potenze e logaritmi.

> 📌 Ogni trasformazione deve essere accompagnata da una domanda: conserva l'insieme soluzione, aggiunge candidati oppure rischia di perdere soluzioni?

### **2. Espressioni, identità ed equazioni**

Un'**espressione algebrica** è una combinazione di numeri, variabili e operazioni, come $2x-3$ oppure $\frac{x+1}{x-2}$. Il suo **insieme di definizione** è l'insieme dei valori per i quali tutte le operazioni sono lecite.

Un'**identità** è un'uguaglianza vera per ogni valore del dominio comune. Per esempio:

$$
(x+1)^2=x^2+2x+1
$$

è vera per ogni $x\in\mathbb{R}$.

Un'**equazione** è invece una proposizione aperta: chiede per quali valori del dominio due espressioni assumono lo stesso valore. Per esempio:

$$
(x+1)^2=4
$$

è vera soltanto per $x=1$ e $x=-3$.

L'insieme di tali valori è l'**insieme soluzione** $S$. Rispetto al dominio $D$, un'equazione può essere:

- **impossibile**, se $S=\varnothing$;
- **indeterminata**, o un'identità sul dominio considerato, se $S=D$;
- **determinata**, se $\varnothing\subsetneq S\subsetneq D$.

Nel terzo caso l'insieme soluzione può essere finito oppure infinito. Per esempio, un'equazione trigonometrica può avere infinite soluzioni periodiche senza essere un'identità. In alcuni testi scolastici “determinata” viene riservato al caso di un numero finito di soluzioni: qui useremo la classificazione insiemistica appena dichiarata.

La stessa scrittura $P(x)=Q(x)$ può quindi indicare un'identità oppure un'equazione: il significato dipende dalla richiesta e dai quantificatori sottintesi.

### **3. Equazioni equivalenti e implicazioni**

Due equazioni $E$ e $F$, considerate nello stesso dominio $D$, sono **equivalenti** quando hanno lo stesso insieme soluzione:

$$
E\iff F
$$

La doppia implicazione richiede entrambe le direzioni. Se ogni soluzione di $E$ è soluzione di $F$, ma non necessariamente il contrario, si ha soltanto:

$$
E\implies F
$$

Per esempio:

$$
x=-1\implies x^2=1
$$

ma $x^2=1$ ha anche la soluzione $x=1$, quindi il passaggio al quadrato ha aggiunto un candidato.

Nella scrittura $E\implies F$, la condizione $F$ è **necessaria** per $E$, mentre $E$ è **sufficiente** per $F$. Nell'esempio, $x^2=1$ è necessario ma non sufficiente per avere $x=-1$; viceversa, $x=-1$ è sufficiente ma non necessario per avere $x^2=1$.

#### **3.1 Passaggi reversibili**

Sono equivalenti, nel dominio comune:

$$
A(x)=B(x)\iff A(x)+C(x)=B(x)+C(x)
$$

e, se $c\ne0$:

$$
A(x)=B(x)\iff cA(x)=cB(x)
$$

Più in generale si può moltiplicare per $C(x)$ soltanto quando è già noto che $C(x)\ne0$ in tutto il dominio in esame. Dividere per un'espressione che potrebbe annullarsi può invece eliminare soluzioni. Da:

$$
x(x-1)=0
$$

dividendo incautamente per $x$ si ottiene $x=1$ e si perde $x=0$.

#### **3.2 Applicare una funzione**

Se $f$ è iniettiva sull'insieme dei valori assunti dai due membri, allora:

$$
A(x)=B(x)\iff f(A(x))=f(B(x))
$$

La funzione $t\mapsto t^3$ è iniettiva su $\mathbb{R}$, mentre $t\mapsto t^2$ non lo è. Perciò elevare al cubo conserva l'equivalenza reale; elevare al quadrato richiede condizioni di segno oppure una verifica finale.

Prendere il logaritmo è equivalente soltanto se entrambi i membri sono positivi e la base è ammissibile. Estrarre una radice pari da $A=B$ richiede inoltre $A=B\ge0$.

### **4. Quadro operativo delle trasformazioni**

| Trasformazione | Natura del passaggio | Condizione essenziale |
|---|---|---|
| Sommare o sottrarre la stessa espressione | Equivalente | Espressioni definite |
| Moltiplicare o dividere per una costante | Equivalente | Costante non nulla |
| Moltiplicare per $C(x)$ | Equivalente nel dominio ristretto | $C(x)\ne0$ |
| Cancellare un fattore comune in una frazione | Equivalente sul dominio originario | Conservare gli zeri esclusi |
| Elevare a potenza dispari | Equivalente sui reali | Membri definiti |
| Elevare a potenza pari | In generale solo implicazione | Per l'equivalenza, membri non negativi |
| Applicare una funzione strettamente monotona | Equivalente | Argomenti nel suo dominio |
| Prendere il logaritmo | Equivalente | Membri positivi e base ammissibile |

<!-- TODO FIGURA:
Schema a frecce con quattro casi: passaggio equivalente, sola implicazione,
passaggio valido con condizioni e passaggio che può perdere soluzioni.
Associare a ciascun caso un esempio elementare.
Obiettivo didattico: distinguere visivamente trasformazioni reversibili e non reversibili.
-->

### **5. Principi di equivalenza per le disequazioni**

L'ordine reale è compatibile con l'addizione:

$$
a<b\iff a+c<b+c
$$

Se $c>0$, è compatibile con la moltiplicazione:

$$
a<b\iff ac<bc
$$

Se $c<0$, il verso si inverte:

$$
a<b\iff ac>bc
$$

La giustificazione dell'ultimo fatto si ottiene scrivendo $a<b$, quindi $b-a>0$. Moltiplicando per $-c>0$ si ha $(-c)(b-a)>0$, cioè $ac-bc>0$ e dunque $ac>bc$.

> ⚠️ Non si può moltiplicare una disequazione per un'espressione di segno ignoto. In particolare, da $\frac{A(x)}{B(x)}>0$ non si elimina $B(x)$ senza studiarne il segno.

Se $f$ è strettamente crescente, conserva l'ordine; se è strettamente decrescente, lo inverte. Se non è monotona sull'intervallo considerato, non è possibile dedurre una regola unica. Il quadrato, per esempio, è decrescente su $(-\infty,0]$ e crescente su $[0,+\infty)$.

### **6. Dominio e condizioni simultanee**

Prima di trasformare un problema si raccoglie il dominio originario:

- ogni denominatore deve essere diverso da zero;
- ogni radicando di indice pari deve essere non negativo;
- ogni argomento di logaritmo deve essere positivo;
- ogni base logaritmica deve essere positiva e diversa da $1$;
- le condizioni introdotte durante i passaggi devono essere intersecate con le precedenti.

Per esempio, l'espressione:

$$
\frac{\sqrt{x-1}}{\log_2(5-x)}
$$

richiede simultaneamente:

$$
\begin{cases}
x-1\ge0 \\
5-x>0 \\
\log_2(5-x)\ne0
\end{cases}
$$

L'ultima condizione equivale a $5-x\ne1$, cioè $x\ne4$. Pertanto:

$$
D=[1,5)\setminus\{4\}
$$

### **7. Equazioni razionali: denominatori e verifica**

Consideriamo:

$$
\frac{x+1}{x-2}=3
$$

Il dominio impone $x\ne2$. Nel dominio il denominatore è non nullo, quindi la moltiplicazione per $x-2$ è reversibile:

$$
x+1=3(x-2)
$$

Da cui $x=\frac72$, valore ammesso. Il metodo corretto è dunque:

1. determinare il dominio;
2. moltiplicare per un denominatore comune non nullo nel dominio;
3. risolvere l'equazione ottenuta;
4. intersecare con il dominio e verificare.

La cancellazione:

$$
\frac{x^2-1}{x-1}=x+1
$$

vale soltanto per $x\ne1$. La formula semplificata non restituisce alla funzione originaria il punto escluso.

### **8. Sistemi di equazioni**

Un **sistema** richiede che tutte le equazioni siano soddisfatte contemporaneamente. Il suo insieme soluzione è l'intersezione degli insiemi definiti dalle singole condizioni.

Per il sistema lineare:

$$
\begin{cases}
2x+y=7 \\
x-y=2
\end{cases}
$$

si possono usare tre metodi equivalenti.

#### **8.1 Sostituzione**

Dalla seconda equazione $y=x-2$. Sostituendo nella prima:

$$
2x+x-2=7
$$

quindi $x=3$ e $y=1$.

#### **8.2 Confronto**

Si isolano la stessa incognita:

$$
y=7-2x,\qquad y=x-2
$$

e si uguagliano i secondi membri. Si ottiene ancora $x=3$, $y=1$.

#### **8.3 Eliminazione**

Sommando membro a membro le due equazioni:

$$
3x=9
$$

da cui segue la stessa soluzione. Geometricamente, una soluzione unica corrisponde all'intersezione di due rette; rette parallele distinte producono un sistema impossibile, rette coincidenti un sistema indeterminato.

### **9. Sistemi di disequazioni**

Nel sistema:

$$
\begin{cases}
2x-1>0 \\
x\le3
\end{cases}
$$

le singole soluzioni sono $x>\frac12$ e $x\le3$. Il risultato è la loro **intersezione**:

$$
S=\left(\frac12,3\right]
$$

Confondere intersezione e unione cambia il significato logico di “tutte le condizioni”. Lo stesso principio governerà il dominio di funzioni composte da più operazioni.

### **10. Esempio misto con candidati estranei**

Risolviamo:

$$
\sqrt{x+2}=x
$$

Il dominio della radice richiede $x\ge-2$, ma l'uguaglianza richiede anche $x\ge0$ perché il primo membro è non negativo. Elevando al quadrato sotto queste condizioni:

$$
x+2=x^2
$$

ossia:

$$
(x-2)(x+1)=0
$$

I candidati sono $2$ e $-1$, ma soltanto $2$ soddisfa $x\ge0$ e l'equazione originaria. Se avessimo elevato subito al quadrato, $-1$ sarebbe comparso come soluzione estranea.

### **11. Errori frequenti**

- Dividere per un'espressione senza separare il caso in cui vale zero.
- Trasformare una sola implicazione in una doppia implicazione.
- Moltiplicare una disequazione per un denominatore di segno ignoto.
- Dimenticare il dominio originario dopo una semplificazione.
- Unire, anziché intersecare, le condizioni di un sistema.
- Saltare la verifica dopo un elevamento a potenza pari.

### **12. Riepilogo**

> ✅ Una soluzione algebrica è corretta quando ogni passaggio è equivalente nel dominio dichiarato oppure quando i candidati prodotti da passaggi non reversibili vengono verificati nell'espressione originaria.

Questi principi verranno applicati nelle lezioni successive al primo e secondo grado, alle frazioni razionali, ai radicali, agli esponenziali e ai logaritmi.
