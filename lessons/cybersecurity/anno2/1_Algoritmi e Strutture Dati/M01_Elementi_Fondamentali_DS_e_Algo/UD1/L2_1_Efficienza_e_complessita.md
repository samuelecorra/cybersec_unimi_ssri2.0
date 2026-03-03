## **Lezione 2 – Efficienza e complessità (parte 1)**

Finora abbiamo capito _che cosa_ sia un algoritmo.  
Ora arriva la domanda decisiva: **quanto è buono un algoritmo?**  
Questa lezione insegna a misurare la sua “velocità mentale”: quanto lavoro richiede per risolvere un problema e quanto cresce questo lavoro se l’input aumenta.

Impareremo che:

- esiste un modo **astratto** per valutare l’efficienza, indipendente dal computer usato;
    
- possiamo descrivere questa efficienza con una funzione chiamata **$T(n)$**, che lega il tempo di calcolo alla dimensione dei dati d’ingresso;
    
- grazie a $T(n)$, possiamo finalmente confrontare due algoritmi e capire _chi vince la corsa_ in termini di risorse.
    

È l’inizio della **scienza della complessità**: il punto in cui la programmazione smette di essere arte e diventa analisi quantitativa.

---

### **1. Perché serve misurare l’efficienza**

Due algoritmi possono fare la stessa cosa, ma uno finisce in un battito di ciglia e l’altro in un’era geologica.  
E non basta cronometrare: il tempo reale dipende dal processore, dal compilatore, perfino dalla temperatura della stanza.  
Serve una **misura astratta**, valida sempre e ovunque.

L’idea geniale è questa:

> “Non contiamo i secondi, contiamo le **operazioni fondamentali**.”

Ogni operazione elementare (somma, confronto, assegnamento, ecc.) vale un’unità di tempo ideale.  
Così possiamo stimare quanto “lavoro logico” serve, a prescindere dalla macchina.

Definiamo quindi:  

$T(n) = \text{numero totale di operazioni elementari necessarie per un input di taglia } n$

È una misura **concettuale** del tempo di calcolo: non dice quanti millisecondi servono, ma **come cresce la fatica** dell’algoritmo quando aumentano i dati.

---

### **2. Il concetto di operazione elementare**

Per costruire $T(n)$ dobbiamo prima scegliere cosa contare.  
Le **operazioni elementari** sono le più semplici e indivisibili:

- operazioni **aritmetiche**: somma, sottrazione, moltiplicazione;
    
- operazioni **logiche**: and, or, not;
    
- **confronti**: <, >, == etc
    
- **assegnamenti** e accessi a memoria.
    

Quando analizziamo un algoritmo, **non contiamo tutto**, ma solo le operazioni che **dominano** il suo tempo totale: quelle che si ripetono molte volte o sono particolarmente costose.

> È come valutare il tempo che serve per cucinare un piatto: non consideri ogni singolo gesto (aprire il frigo, prendere il mestolo), ma le fasi che assorbono davvero tempo — la cottura, la preparazione, la coda alla piastra.

---

### **3. Esempio 1 – La versione iterativa del minimo (`min_i`)**

Riprendiamo la funzione vista nella lezione 1:

```c
int minimo_iterativo(int* array, int j, int k) {

    int min = array[j];
    int i;

    for (i = j + 1; i <= k; i++) {
        min = array[i] < min ? array[i] : min;
    }

    return min;
}
```

#### **Analisi passo-passo**

![Pasted image 20251025212411](../../imgs/Pasted%20image%2020251025212411.png)

1. La dichiarazione della funzione/procedura che dir si voglia ha costo unitario;

2. La dichiarazione delle variabili non ha costo, per ipotesi;

3. L'assegnamento iniziale ha anch'esso costo unitario; 

4.  Per uscire dal ciclo for, bisogna valutare la condizione di uscita:
		Dal momento che il ciclo viene eseguito n-1 volte, la condizione viene
		rispettata le prime n-1 volte e poi alla n-esima valutazione fallisce, garantendo l'uscita
		dal ciclo. 
		Ergo, $n - 1 + 1 = \ si \ valuta \ n \ volte$
    
5. Il corpo del ciclo `for` viene ripetuto per ogni elemento da `j+1` a `k`, cioè **n − 1 volte**.
    All’interno del ciclo abbiamo un **confronto** e un **assegnamento condizionale**: due operazioni principali.
    
6. A fine ciclo, un `return` con costo unitario


Se indichiamo con $(c_1, c_2, \dots)$ i costi elementari di ciascun blocco, 
possiamo procedere con un otteniamo una formula del tipo:

$T(n) = c_1 + c_2 + c_3n + c_4(n - 1) + c_5$

Sviluppiamo la parentesi per poter poi raccogliere n a fattore parziale:

$T(n) = c_1 + c_2 + c_3n + c_4n - c_4 + c_5$

$T(n) = n(c_3 + c_4) + c_1 + c_2 - c_4 + c_5$

Ora, se definiamo:

$a = (c_3 + c_4)$

$b = c_1 + c_2 - c_4 + c_5$

Possiamo snellire la funzione 
$$T(n)=an+b$$

dove (a) e (b) sono costanti positive.  
In pratica, **il tempo di calcolo cresce linearmente con n**.  
Se raddoppio la dimensione dell’array, raddoppia anche il numero di operazioni.  
È un algoritmo di **complessità lineare**.


---

### **4. Esempio 2 – La versione ricorsiva del minimo (`min_r`)**

Ora osserviamo la versione ricorsiva:

```c
int minimo_ricorsivo(int* array, int j, int k) {

    int min;

    if (j == k) {
        min = array[j];
    } else {
        min = minimo_ricorsivo(array, j+1, k);
        min = array[j] < min ? array[j] : min;
    }
    return min;
}
```

#### **Analisi passo-passo**

![Pasted image 20251025215145](../../imgs/Pasted%20image%2020251025215145.png)
#### **Caso base (n = 1)**

Se l’array contiene un solo elemento, sfociamo nel caso base, dunque la funzione esegue poche operazioni fisse:  un confronto, un assegnamento, un ritorno.

Quindi $T(1)$ è una costante: $T(1) = c$.

Il problema è che bisogna estendere la validità per qualsiasi n.

#### **Caso generale (n > 1)**

Concentriamoci subito sul punto più critico, ovvero la reinvocazione della funzione.
		Dal momento che si continuerà a richiamare finché non si avrà j == k, possiamo dedurre
		che verrà richiamata per un totale di n-1 volte. Questo implica che: 

- una chiamata ricorsiva su $(n − 1)$ elementi → costa $T(n − 1)$;

$T(n) = T(n-1) + c_1 + c_2 + c_3 + c_4 + c_5$

Da cui, rinominando le operazioni costanti:

$T(n) = T(n - 1) + d$

Appurato ciò, possiamo riassumere che $T(n)$ può essere definito da un sistema:


$$  
T(n)=  
\begin{cases}  
c & \text{se } n=1\\\\  
T(n-1)+d & \text{se } n>1  
\end{cases}  
$$


#### **Metodo delle sostituzioni successive**

Vogliamo valutare $T(n)$, ma ciò appare complicato.
La chiave risiede nell'applicare il metodo delle sostituzioni successive.
Per $n>1$:  

$$T(n) = T(n-1) + d$$

Ora sviluppo $T(n-1)$, cioè la stessa formula, ma con $n-1$ al posto di $n$:

$$  
T(n-1) = T(n-2) + d  
$$

Quindi, sostituendolo in cima nell'oroginale, ottengo: 

$$  
T(n) = [T(n-2) + d] + d = T(n-2) + 2d  
$$

---
Ancora una volta, sostituisco $T(n-2)$:

$$  
T(n-2) = T(n-3) + d  
$$

Quindi:  

$$  
T(n) = [T(n-3) + d] + 2d = T(n-3) + 3d  
$$

---
Puoi notare il **pattern**:  
dopo $k$ sostituzioni, si ottiene

$$  
T(n) = T(n-k) + kd  
$$

---
La condizione base è $T(1) = c$, quindi smetto di sostituire quando $n - k = 1$, cioè $k = n - 1$.

Sostituendo:

$$  
T(n) = T(1) + (n-1)d  
$$

---
Poiché $T(1) = c$:

$$  
T(n) = c + (n-1)d  
$$

---
Sviluppiamo la parentesi e poi ridefiniamo:

$$  
T(n) = dn + (c - d)  
$$

$$T(n) = a n + b$$

Questo ci porta a concludere che:
La versione ricorsiva, dunque, **ha la stessa efficienza** della versione iterativa: entrambe crescono linearmente con n.

---

### **5. Caso pessimo e caso medio**

Non tutti gli input costano uguale.  
Alcune situazioni “fortunatissime” fanno terminare un algoritmo prima; altre lo mettono alla prova.  
Ecco perché si parla di:

- **caso migliore (best case)**: quando l’algoritmo trova la risposta subito;
    
- **caso peggiore (worst case)**: quando deve percorrere tutti i passi possibili;
    
- **caso medio (average case)**: il comportamento “statisticamente tipico”.
    

In genere si studia il **caso pessimo**, perché fornisce una garanzia: sappiamo che _mai_ andrà peggio di così.

---

### **6. Un esempio intuitivo: la generazione di password**

Immaginiamo due regole per creare password sicure di 8 caratteri.

- **Regola 1:** 8 caratteri qualsiasi (lettere maiuscole/minuscole, numeri, simboli).
    
- **Regola 2:** 8 caratteri, ma con l’obbligo di avere almeno una minuscola, una maiuscola, un numero e un simbolo.
    

Prima di procedere:
Definiamo le dimensioni delle classi di caratteri:

- $c_l$ = numero di lettere **minuscole** (di solito 26)
    
- $c_L$ = numero di lettere **maiuscole** (di solito 26)
    
- $c_n$ = numero di **cifre** (di solito 10)
    
- $c_p$ = numero di caratteri di **punteggiatura/simboli** (variabile, es. 32)
    

L’insieme totale di caratteri è $C = c_l + c_L + c_n + c_p$.


### **7. Caso pessimo (worst-case) — conteggio teorico puro**

- **Regola 1 (qualsiasi carattere)**: ogni posizione ha $C$ possibilità → il numero totale di password lunghe 8 è $C^8$

- **Regola 2 (deve contenere tutte e 4 le categorie)**: un modo semplice per conteggiare è: riservare 4 posizioni, una per ciascuna categoria, e lasciare le altre 4 posizioni libere (qualunque carattere). Con questa approssimazione il numero è
$$(c_l⋅c_L⋅c_n⋅c_p) ⋅C_4$$

> Conclusione worst-case: poiché $C^8$ cresce più veloce di $(c_l c_L c_n c_p) C^4$ (in generale, perché $C^8 = C^4 \cdot C^4$ e $C^4$ è grosso), **Regola 1** genererà più combinazioni nel caso peggiore — quindi è _più generosa_ nel numero di possibili password.

---

### **8. Caso medio (assunzione pratica sul comportamento degli utenti)**

Facciamo un'**ipotesi pratica**: in media gli utenti tendono a usare principalmente **lettere minuscole**. Quindi:

- **Regola 1 (media)**: mediamente gli utenti usano solo minuscole → numero approssimato di password = $c_l^8$.
    
- **Regola 2 (media)**: le 4 posizioni “obbligatorie” vengono occupate una rispettivamente da minuscola, maiuscola, numero, simbolo; le altre 4 posizioni, nella media, saranno minuscole. Quindi il conteggio medio stimato dalle slide è:  

$$  
(c_l \cdot c_L \cdot c_n \cdot c_p)\cdot c_l^4.  
$$

Ora confrontiamo le due quantità per vedere quale regola dà **più combinazioni medie** (cioè quale è “migliore” nella pratica, sotto questa ipotesi).

---

### **9. Confronto e disuguaglianza (passo-passo)**

Sia:

- $Reg1_{media} = c_l^8$
    
- $Reg2_{media} = (c_l \cdot c_L \cdot c_n \cdot c_p)\cdot c_l^4$
    

Poiché $c_L$ (maiuscole) è tipicamente uguale a $c_l$ (26), sostituiamo $c_L = c_l$:

$Reg2_{media} = (c_l \cdot c_l \cdot c_n \cdot c_p) \cdot c_l^4 = c_l^6 \cdot c_n \cdot c_p$.

Vogliamo sapere quando **Regola 2 è migliore** (cioè $Reg2_{media} > Reg1_{media}$):

$$  
c_l^6 \cdot c_n \cdot c_p > c_l^8.  
$$

Dividiamo entrambi i membri per $c_l^6$ (che è positivo):

$$  
c_n \cdot c_p > c_l^2.  
$$

Quindi la condizione è  

$$  
c_n \cdot c_p > c_l^2.  
$$

L'unico valore variabile qui è $c_p$. Isoliamolo:

$$
c_p > \frac{c_l^2}{c_n}
$$

Ora sostituiamo:

$$
c_p > \frac{26^2}{10}
$$

$$
c_p > \frac{676}{10}
$$

$$
c_p > 67.6
$$

👉 Questo significa che **Regola 2** (quella che obbliga a usare almeno una maiuscola, una minuscola, un numero e un simbolo) risulterebbe più efficace **solo se l’insieme dei simboli ammissibili superasse i 67 caratteri distinti**.

- In teoria, l’insieme Unicode contiene _centinaia_ di simboli diversi, quindi **matematicamente sarebbe possibile** avere $c_p > 67$.
    
- **In pratica però**, i sistemi reali **limitano fortemente** i simboli utilizzabili nelle password per motivi di:
    
    - compatibilità (alcuni caratteri Unicode possono rompersi nei database o nei form web),
    - sicurezza (evitare injection o ambiguità),
    - e soprattutto **usabilità** (molti utenti si confonderebbero).

Anche quando il sistema _permette_ molti simboli, **quasi nessuno li usa**.  
L’utente tipico sceglie 1 o 2 simboli ricorrenti:

- `!` (punto esclamativo)
- `?` (punto interrogativo)
- talvolta `.` o `_`

Quindi nella realtà dei fatti, **$c_p$ effettivo** è spesso 2 o 3, **non certo 67**.  
Questo porta la disuguaglianza a fallire nettamente: 

$$  
c_p \approx 3 \ll 67.6  
$$

➡️ **Regola 2 non aumenta la complessità media reale**, perché:

1. I simboli disponibili sono limitati.
2. Gli utenti ne usano pochissimi.
3. Il vantaggio teorico scompare del tutto se l’utente segue pattern prevedibili (“Password1!”).

---


> “In conclusione, la forza reale di una password non deriva tanto dalla complessità sintattica imposta, quanto dalla **lunghezza** e dall’**imprevedibilità effettiva** dei caratteri scelti dall’utente.”
> 
> Morale: l’efficienza dipende anche da _come_ arrivano i dati.  
> Studiare il caso pessimo garantisce robustezza; studiare il caso medio permette di ottimizzare nella pratica.

---

### **10. Cosa bisogna metabolizzare subito**

- **$T(n)$** è la misura astratta dell’efficienza: conta le operazioni dominanti, non i secondi reali.
    
- Un **algoritmo iterativo** e la sua **versione ricorsiva** possono avere la stessa complessità (entrambi lineari).
    
- L’analisi del **caso pessimo** serve per conoscere il limite massimo di tempo richiesto.
    
- Il **caso medio** è più realistico, ma difficile da calcolare perché richiede modelli probabilistici sugli input.
    
- Studiare l’efficienza significa capire _come cresce il lavoro_ al crescere dei dati: questa è la chiave per progettare software scalabili.
    

---

### **11. In sintesi**

Con questa lezione abbiamo messo piede nel territorio della **complessità computazionale**:  
abbiamo imparato a guardare gli algoritmi come sistemi che consumano risorse, e a misurare quella “fatica logica” in modo rigoroso ma indipendente dal computer.

Nella prossima parte approfondiremo **le notazioni asintotiche** — il linguaggio simbolico che ci permette di esprimere in modo elegante come si comporta $T(n)$ quando n cresce all’infinito.  
È il momento in cui le lettere greche diventano il nostro nuovo alfabeto di precisione.

---