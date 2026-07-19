# **M1 UD6 Lezione 5A - Prostaferesi, Werner e identità trigonometriche**

### **1. Perché trasformare somme e prodotti**

Le formule di addizione generano due famiglie di trasformazioni complementari:

- le formule di **prostaferesi** trasformano somme o differenze in prodotti;
- le formule di **Werner** trasformano prodotti in somme.

Questi passaggi permettono di fattorizzare equazioni, riconoscere il segno di un'espressione e preparare integrali trigonometrici. Non introducono nuove funzioni: riorganizzano identità già dimostrate.

### **2. Derivazione delle formule di prostaferesi**

Poniamo

$$
u=\frac{\alpha+\beta}{2},\qquad v=\frac{\alpha-\beta}{2}.
$$

Allora $\alpha=u+v$ e $\beta=u-v$. Usando le formule di addizione e sottrazione,

$$
\sin\alpha+\sin\beta
=\sin(u+v)+\sin(u-v).
$$

Sviluppando,

$$
\sin(u+v)=\sin u\cos v+\cos u\sin v,
$$

$$
\sin(u-v)=\sin u\cos v-\cos u\sin v.
$$

Sommando, i termini con $\cos u\sin v$ si cancellano:

$$
\sin\alpha+\sin\beta
=2\sin u\cos v
=2\sin\frac{\alpha+\beta}{2}\cos\frac{\alpha-\beta}{2}.
$$

Con lo stesso procedimento si ottengono

$$
\sin\alpha-\sin\beta
=2\cos\frac{\alpha+\beta}{2}\sin\frac{\alpha-\beta}{2},
$$

$$
\cos\alpha+\cos\beta
=2\cos\frac{\alpha+\beta}{2}\cos\frac{\alpha-\beta}{2},
$$

$$
\cos\alpha-\cos\beta
=-2\sin\frac{\alpha+\beta}{2}\sin\frac{\alpha-\beta}{2}.
$$

Il segno meno nell'ultima formula deriva dall'ordine $\cos\alpha-\cos\beta$ e non va omesso.

### **3. Applicazione della prostaferesi**

Risolviamo

$$
\sin(3x)+\sin x=0.
$$

La somma diventa

$$
2\sin(2x)\cos x=0.
$$

Quindi

$$
\sin(2x)=0
$$

oppure

$$
\cos x=0.
$$

La prima equazione dà $x=k\pi/2$; la seconda dà $x=\pi/2+k\pi$, già contenuta nella prima famiglia. La soluzione senza duplicazioni è

$$
x=\frac{k\pi}{2},\qquad k\in\mathbb{Z}.
$$

### **4. Derivazione delle formule di Werner**

Scriviamo le formule del coseno:

$$
\cos(\alpha-\beta)=\cos\alpha\cos\beta+\sin\alpha\sin\beta,
$$

$$
\cos(\alpha+\beta)=\cos\alpha\cos\beta-\sin\alpha\sin\beta.
$$

Sottraendo la seconda dalla prima,

$$
2\sin\alpha\sin\beta
=\cos(\alpha-\beta)-\cos(\alpha+\beta),
$$

da cui

$$
\sin\alpha\sin\beta
=\frac{1}{2}\left[\cos(\alpha-\beta)-\cos(\alpha+\beta)\right].
$$

Sommando invece le due formule si ottiene

$$
\cos\alpha\cos\beta
=\frac{1}{2}\left[\cos(\alpha-\beta)+\cos(\alpha+\beta)\right].
$$

Infine, sommando o sottraendo le formule del seno,

$$
\sin\alpha\cos\beta
=\frac{1}{2}\left[\sin(\alpha+\beta)+\sin(\alpha-\beta)\right],
$$

$$
\cos\alpha\sin\beta
=\frac{1}{2}\left[\sin(\alpha+\beta)-\sin(\alpha-\beta)\right].
$$

### **5. Applicazione delle formule di Werner**

Trasformiamo

$$
\sin(3x)\cos x.
$$

Ponendo $\alpha=3x$ e $\beta=x$,

$$
\sin(3x)\cos x
=\frac{1}{2}[\sin(4x)+\sin(2x)].
$$

Questa forma sarà utile nell'integrazione, perché sostituisce un prodotto con una somma di funzioni elementari.

### **6. Identità ed equazioni: una distinzione essenziale**

Un'**identità trigonometrica** è vera per ogni valore appartenente al dominio comune dei due membri. Un'equazione è vera soltanto per alcuni valori dell'incognita.

Per dimostrare un'identità conviene:

1. determinare il dominio comune;
2. trasformare un solo membro;
3. esprimere, se utile, tutto in seno e coseno;
4. applicare identità fondamentali e fattorizzazioni;
5. evitare divisioni per espressioni che possono annullarsi.

### **7. Esempio di identità con dominio**

Dimostriamo che

$$
\frac{1-\cos(2x)}{\sin(2x)}=\tan x
$$

sul dominio comune. Il primo membro richiede $\sin(2x)\neq 0$, quindi in particolare $\sin x\neq 0$ e $\cos x\neq 0$. Usando le formule di duplicazione,

$$
\frac{1-\cos(2x)}{\sin(2x)}
=\frac{2\sin^2x}{2\sin x\cos x}
=\frac{\sin x}{\cos x}
=\tan x.
$$

La semplificazione per $\sin x$ è lecita perché il dominio originario ne garantisce la non nullità.

### **8. Una dimostrazione errata**

Consideriamo l'equazione

$$
\sin x(1-\cos x)=0.
$$

Dividere per $\sin x$ e concludere $\cos x=1$ perde tutte le soluzioni $\sin x=0$ con coseno uguale a $-1$. La divisione trasforma l'equazione soltanto sul sottoinsieme in cui $\sin x\neq 0$.

> ⚠️ In un'identità la divisione può restringere il dominio; in un'equazione può anche eliminare intere famiglie di soluzioni.

### **9. Esercizio misto**

Semplifichiamo

$$
\cos(5x)-\cos x.
$$

Per prostaferesi,

$$
\cos(5x)-\cos x
=-2\sin(3x)\sin(2x).
$$

Se l'espressione è posta uguale a zero, la trasformazione produce direttamente

$$
\sin(3x)=0
$$

oppure

$$
\sin(2x)=0.
$$

Le famiglie ottenute vanno poi confrontate per eliminare eventuali duplicazioni.

### **10. Scelta della trasformazione**

- somma o differenza da porre uguale a zero: prostaferesi;
- prodotto da integrare o confrontare: Werner;
- quadrati di seno e coseno: riduzione di potenza;
- argomenti doppi: duplicazione;
- espressioni razionali in seno e coseno: possibile sostituzione $t=\tan(x/2)$.

Non esiste una formula sempre migliore: la scelta dipende dalla forma che si vuole ottenere.

### **11. Collegamenti futuri**

Nei limiti la prostaferesi permette di isolare fattori del tipo $\sin u$; negli integrali Werner e le formule di riduzione trasformano prodotti e potenze. Nel modulo sui numeri complessi le formule di addizione saranno reinterpretate mediante moltiplicazione in forma trigonometrica e formula di De Moivre.

### **12. Riepilogo**

> ✅ Prostaferesi e Werner sono conseguenze delle formule di addizione: la prima famiglia converte somme in prodotti, la seconda prodotti in somme.

Una manipolazione trigonometrica è corretta soltanto se conserva il dominio comune e, quando si risolve un'equazione, non elimina fattori senza analizzarne gli zeri.
