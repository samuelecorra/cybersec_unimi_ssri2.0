## **Lezione 2: Parametri dello schema DSS**

### **1. Obiettivo della lezione**

In questa lezione vediamo come generare i parametri necessari per applicare lo schema di firma digitale **DSS/DSA**.

Dalla lezione precedente sappiamo che lo schema DSA richiede tre parametri pubblici fondamentali:

- un numero primo $p$;
- un numero primo $q$;
- un elemento $\alpha \in \mathbb{Z}_p^*$ di ordine $q$.

Questi parametri non possono essere scelti in modo arbitrario. Devono soddisfare relazioni precise:

$$
q \mid (p-1)
$$

e:

$$
\operatorname{ord}(\alpha)=q
$$

Il problema della lezione è quindi: **come generare efficientemente $p$, $q$ e $\alpha$ rispettando tutti questi vincoli?**

> 📌 La generazione dei parametri DSS non è un dettaglio implementativo secondario: se i parametri non sono scelti correttamente, la sicurezza dello schema DSA può essere compromessa.

---

### **2. Perché non conviene scegliere prima $p$**

Una soluzione ingenua potrebbe essere:

1. scegliere casualmente un primo grande $p$;
2. cercare un primo $q$ di 160 bit tale che:

$$
q \mid (p-1)
$$

Questo approccio però è inefficiente.

Il motivo è che, una volta scelto $p$, per trovare un divisore primo $q$ di $p-1$ bisognerebbe conoscere almeno una parte della fattorizzazione di:

$$
p-1
$$

Ma fattorizzare un intero grande è computazionalmente costoso. Quindi non conviene scegliere prima $p$ e poi cercare $q$ dentro la fattorizzazione di $p-1$.

La strategia corretta è l'opposto:

1. generare prima un primo $q$ di 160 bit;
2. costruire poi un primo $p$ tale che $p \equiv 1 \pmod q$.

> 💡 Invece di cercare $q$ dentro $p-1$, si costruisce $p$ in modo che $q$ divida automaticamente $p-1$.

---

### **3. Idea efficiente per generare $q$ e $p$**

Per prima cosa si genera un numero primo:

$$
q
$$

di 160 bit.

Questo può essere fatto generando una sequenza casuale di 160 bit, interpretandola come intero e applicando un test di primalità. Se il test fallisce, si ripete finché non si trova un primo.

Poi si vuole generare un primo:

$$
p
$$

di lunghezza $L$ bit, dove nel DSS classico:

$$
512 \le L \le 1024
$$

e $L$ è multiplo di 64.

Inoltre deve valere:

$$
q \mid (p-1)
$$

Una tecnica semplice è partire da un numero casuale $X$ di $L$ bit e modificarlo in modo che sia congruente a 1 modulo $2q$.

Si calcola:

$$
p = X - ((X \bmod 2q)-1)
$$

Infatti, se poniamo:

$$
c = X \bmod 2q
$$

allora:

$$
p = X - c + 1
$$

e quindi:

$$
p \equiv 1 \pmod{2q}
$$

Da questo segue:

$$
2q \mid (p-1)
$$

e quindi, in particolare:

$$
q \mid (p-1)
$$

A questo punto si applica un test di primalità a $p$. Se $p$ è primo e ha ancora la lunghezza richiesta, cioè:

$$
2^{L-1} \le p < 2^L
$$

allora $p$ viene accettato. Altrimenti si ripete la procedura.

> ✅ La formula per $p$ forza la congruenza $p \equiv 1 \pmod{2q}$, quindi evita di dover fattorizzare $p-1$ dopo aver scelto $p$.

---

### **4. Procedura DSS verificabile**

Lo standard DSS fornisce una procedura più rigorosa per generare $p$ e $q$.

La procedura restituisce non solo:

$$
p
$$

e:

$$
q
$$

ma anche:

- un **seme** $S$;
- un **contatore**.

Questi valori aggiuntivi servono a rendere verificabile la generazione dei parametri. Chiunque, conoscendo $S$ e il contatore, può rieseguire la procedura e controllare che $p$ e $q$ siano stati davvero ottenuti secondo lo standard.

Questo è importante perché i parametri pubblici devono essere considerati affidabili. Il seed e il contatore danno trasparenza al processo di generazione.

> 📌 La procedura DSS non produce solo numeri primi: produce anche una traccia verificabile della loro generazione.

---

### **5. Scelta della lunghezza $L$**

La procedura prende in input un parametro:

$$
L
$$

che rappresenta la lunghezza in bit del primo $p$ e quindi il livello di sicurezza considerato.

Nel DSS classico:

$$
512 \le L \le 1024
$$

e:

$$
L \equiv 0 \pmod{64}
$$

La procedura calcola due interi $n$ e $b$ tali che:

$$
L-1 = 160n + b
$$

con:

$$
0 \le b < 160
$$

Questa decomposizione serve perché SHA produce blocchi di 160 bit. Per costruire un numero di $L$ bit, la procedura concatena più output di SHA da 160 bit, usando eventualmente solo $b$ bit dell'ultimo blocco.

---

### **6. Generazione di $q$**

Per generare $q$, la procedura sceglie casualmente un seme:

$$
S
$$

di almeno 160 bit.

Poi applica SHA sia a $S$ sia al suo successivo:

$$
S+1
$$

ottenendo due stringhe di 160 bit:

$$
\operatorname{SHA}(S)
$$

e:

$$
\operatorname{SHA}(S+1)
$$

Queste due stringhe vengono combinate tramite XOR:

$$
U = \operatorname{SHA}(S) \oplus \operatorname{SHA}(S+1)
$$

Il valore $U$ è una stringa di 160 bit.

Da $U$ si ricava il candidato $q$ forzando a 1:

- il bit più significativo;
- il bit meno significativo.

Forzare il bit più significativo garantisce che il numero abbia effettivamente 160 bit. Forzare il bit meno significativo garantisce che sia dispari, quindi candidato plausibile a essere primo.

Sul candidato così ottenuto si applica un test di primalità.

Se il test fallisce, si sceglie un nuovo seme e si ripete. Se il test riesce, il valore ottenuto viene accettato come:

$$
q
$$

> ✅ La generazione di $q$ usa SHA per trasformare un seme casuale in un candidato primo verificabile e di lunghezza corretta.

---

### **7. Generazione di $p$: costruzione dei valori $V_i$**

Una volta generato $q$, si procede alla generazione di $p$.

La procedura usa valori successivi del seme:

$$
S+2,\ S+3,\ S+4,\dots
$$

e li fornisce in input a SHA.

Nel caso in cui $L=512$, servono più blocchi da 160 bit. Il transcript descrive, per esempio, l'uso dei valori:

$$
S+2,\ S+3,\ S+4,\ S+5
$$

per ottenere quattro stringhe:

$$
V_0,V_1,V_2,V_3
$$

ciascuna di 160 bit.

In generale si generano valori:

$$
V_0,V_1,\dots,V_n
$$

dove ogni $V_i$ deriva dall'applicazione di SHA a un valore del seme opportunamente incrementato.

---

### **8. Costruzione di $W$ e $X$**

I valori $V_i$ vengono interpretati come coefficienti in base:

$$
2^{160}
$$

La procedura costruisce:

$$
W = V_0 + V_1 2^{160} + V_2 2^{320} + \dots + (V_n \bmod 2^b)2^{160n}
$$

Il termine finale viene ridotto modulo:

$$
2^b
$$

perché, nella decomposizione:

$$
L-1 = 160n + b
$$

potrebbero servire solo $b$ bit dell'ultimo blocco SHA.

Poi si costruisce:

$$
X = W + 2^{L-1}
$$

L'aggiunta di:

$$
2^{L-1}
$$

serve a forzare il bit più significativo e quindi a garantire che $X$ sia un numero di $L$ bit.

Nel caso $L=512$, questo significa aggiungere:

$$
2^{511}
$$

---

### **9. Da $X$ a $p$**

Una volta ottenuto $X$, si modifica il valore per renderlo congruente a 1 modulo $2q$.

Si calcola:

$$
p = X - ((X \bmod 2q)-1)
$$

Equivalentemente, se:

$$
c = X \bmod 2q
$$

allora:

$$
p = X - c + 1
$$

Quindi:

$$
p \equiv 1 \pmod{2q}
$$

e:

$$
2q \mid (p-1)
$$

La procedura verifica poi che:

$$
p \ge 2^{L-1}
$$

e applica un test di primalità.

Se $p$ è primo e ha la lunghezza richiesta, viene accettato. Altrimenti il contatore viene incrementato e la procedura genera nuovi valori $V_i$, quindi un nuovo $W$, un nuovo $X$ e un nuovo candidato $p$.

> 📌 La generazione di $p$ combina due esigenze: avere un primo di $L$ bit e garantire che $q$ divida $p-1$.

---

### **10. Output della procedura per $p$ e $q$**

Quando la procedura termina con successo, restituisce:

$$
p
$$

$$
q
$$

il seme:

$$
S
$$

e il contatore:

$$
\text{counter}
$$

Il seed e il counter permettono di ricostruire l'intero processo.

Questo evita che i parametri siano semplicemente "dichiarati" corretti: possono essere controllati ripetendo la procedura e verificando:

- che $q$ sia stato generato dal seed;
- che $q$ sia primo;
- che $p$ sia stato derivato dagli output SHA successivi;
- che $p$ sia primo;
- che $q \mid (p-1)$.

> ✅ La verificabilità dei parametri è una parte importante della fiducia nello schema DSS.

---

### **11. Generazione di $\alpha$**

Dopo aver generato $p$ e $q$, resta da trovare un elemento:

$$
\alpha \in \mathbb{Z}_p^*
$$

di ordine:

$$
q
$$

Poiché:

$$
q \mid (p-1)
$$

il numero:

$$
\frac{p-1}{q}
$$

è intero.

La procedura sceglie un elemento casuale:

$$
g \in \mathbb{Z}_p^*
$$

e calcola:

$$
\alpha = g^{(p-1)/q} \bmod p
$$

Se:

$$
\alpha \ne 1
$$

la procedura restituisce $\alpha$. Se invece:

$$
\alpha = 1
$$

si sceglie un nuovo $g$ e si ripete.

---

### **12. Perché $\alpha$ ha ordine $q$**

Dimostriamo perché il valore ottenuto ha ordine $q$.

Per definizione:

$$
\alpha = g^{(p-1)/q} \bmod p
$$

Elevando alla $q$:

$$
\alpha^q \equiv \left(g^{(p-1)/q}\right)^q \pmod p
$$

quindi:

$$
\alpha^q \equiv g^{p-1} \pmod p
$$

Poiché $p$ è primo e $g \in \mathbb{Z}_p^*$, per il teorema di Fermat:

$$
g^{p-1} \equiv 1 \pmod p
$$

quindi:

$$
\alpha^q \equiv 1 \pmod p
$$

Questo mostra che l'ordine di $\alpha$ divide $q$.

Dato che $q$ è primo, i soli divisori positivi di $q$ sono:

$$
1
$$

e:

$$
q
$$

Quindi l'ordine di $\alpha$ può essere solo:

$$
1
$$

oppure:

$$
q
$$

L'ordine è 1 se e solo se:

$$
\alpha = 1
$$

Per questo la procedura scarta il caso $\alpha=1$. Se $\alpha \ne 1$, allora necessariamente:

$$
\operatorname{ord}(\alpha)=q
$$

e $\alpha$ è un parametro valido per DSA.

> 📌 La condizione $\alpha \ne 1$ elimina l'unico caso in cui l'ordine non sarebbe $q$.

---

### **13. Probabilità di successo nella scelta di $\alpha$**

La procedura per generare $\alpha$ ripete la scelta casuale di $g$ finché il valore:

$$
\alpha = g^{(p-1)/q} \bmod p
$$

non è diverso da 1.

La procedura ha successo sicuramente se il valore scelto $g$ è un generatore di:

$$
\mathbb{Z}_p^*
$$

Infatti, se $g$ è un generatore, allora:

$$
g^{(p-1)/q} \not\equiv 1 \pmod p
$$

perché $(p-1)/q$ è più piccolo dell'ordine del generatore, che è $p-1$.

Il numero di generatori modulo un primo $p$ è:

$$
\varphi(p-1)
$$

Quindi la probabilità che un elemento casuale sia un generatore è:

$$
\frac{\varphi(p-1)}{p-1}
$$

Si può usare una stima inferiore:

$$
\varphi(p-1) > \frac{p-1}{6\ln\ln(p-1)}
$$

da cui:

$$
\frac{\varphi(p-1)}{p-1} > \frac{1}{6\ln\ln(p-1)}
$$

Questa è una stima conservativa della probabilità di successo, perché la procedura può riuscire anche con valori $g$ che non sono generatori dell'intero gruppo.

Il numero medio di tentativi è quindi limitato superiormente da:

$$
6\ln\ln(p-1)
$$

> 💡 La stima non dice che servano sempre molti tentativi: dà solo un limite superiore prudente al numero medio di prove.

---

### **14. Esempi numerici sui tentativi medi**

Sostituendo valori tipici per la lunghezza di $p$, si ottengono limiti medi dell'ordine di poche decine di tentativi.

Per esempio:

- se $p$ ha 512 bit, il numero medio di tentativi è circa 35;
- se $p$ ha 1024 bit, il numero medio è poco inferiore a 40;
- se $p$ ha 2048 bit, il numero medio è circa 43 o 44.

Questi valori mostrano che la generazione di $\alpha$ è computazionalmente gestibile.

> ✅ Anche per moduli grandi, trovare un elemento di ordine $q$ non richiede un numero proibitivo di tentativi.

---

### **15. Riepilogo**

In questa lezione abbiamo visto la fase di generazione dei parametri pubblici dello schema DSS/DSA.

I punti fondamentali sono:

- servono tre parametri di base: $p$, $q$ e $\alpha$;
- $q$ deve essere un primo di 160 bit;
- $p$ deve essere un primo di lunghezza $L$, con $512 \le L \le 1024$ e $L$ multiplo di 64 nel DSS classico;
- deve valere $q \mid (p-1)$;
- scegliere prima $p$ e poi cercare $q$ è inefficiente, perché richiederebbe di fattorizzare $p-1$;
- si genera quindi prima $q$ e poi si costruisce $p$ in modo che $p \equiv 1 \pmod{2q}$;
- la procedura DSS usa SHA, un seed $S$ e un contatore per rendere verificabile la generazione di $p$ e $q$;
- la costruzione di $q$ forza a 1 il bit più significativo e il bit meno significativo del candidato;
- la costruzione di $p$ usa più output SHA combinati in base $2^{160}$;
- $\alpha$ si ottiene come $\alpha=g^{(p-1)/q}\bmod p$;
- se $\alpha \ne 1$, allora $\alpha$ ha ordine $q$;
- il numero medio di tentativi per trovare $\alpha$ è piccolo, dell'ordine di poche decine.

> ✅ La generazione corretta di $p$, $q$ e $\alpha$ prepara il terreno matematico su cui DSA costruisce le chiavi e le firme: $q$ definisce il sottogruppo, $p$ definisce il campo modulare, e $\alpha$ è l'elemento di ordine $q$ usato nello schema.
