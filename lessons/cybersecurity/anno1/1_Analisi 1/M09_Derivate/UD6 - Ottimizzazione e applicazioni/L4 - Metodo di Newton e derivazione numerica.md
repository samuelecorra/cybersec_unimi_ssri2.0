# **M9 UD6 Lezione 4 - Metodo di Newton e derivazione numerica**

### **1. Obiettivo della lezione**

Due applicazioni computazionali della derivata: il **metodo di Newton** per risolvere $f(x)=0$ (l'upgrade della bisezione di M08) e la **derivazione numerica** (come stimare $f'$ quando la formula non c'è), con la lettura informatica: errori, criteri di arresto, discesa del gradiente.

---
### **2. Il metodo di Newton: l'idea geometrica**

Per risolvere $f(x)=0$: parti da una stima $x_0$, sostituisci $f$ con la sua **tangente** in $x_0$ (l'approssimazione lineare della UD1/L5), e prendi come nuova stima lo zero della tangente. La tangente in $x_n$ è $y=f(x_n)+f'(x_n)(x-x_n)$; il suo zero (per $f'(x_n)\neq 0$) dà l'**iterazione di Newton**:

$$
x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)}
$$

**Esempio guida ($\sqrt 2$).** $f(x)=x^2-2$: $x_{n+1}=x_n-\frac{x_n^2-2}{2x_n}=\frac{x_n}{2}+\frac{1}{x_n}$. Da $x_0=1{,}5$:

$$
x_1 = 1{,}41\overline{6},\qquad x_2 = 1{,}414215\ldots,\qquad x_3 = 1{,}4142135623\ldots
$$

con $\sqrt2=1{,}4142135623\ldots$: $x_2$ ha già 6 cifre esatte, $x_3$ ne ha 11. Le cifre corrette **raddoppiano** circa a ogni passo (convergenza *quadratica*) — contro il singolo bit per passo della bisezione (M08/UD5/L4). È l'algoritmo con cui i calcolatori estraggono le radici.

<!-- TODO FIGURA:
Grafico di f(x)=x^2-2 con la sequenza di Newton: da x_0 sale la verticale al punto (x_0,f(x_0)), la tangente scende a tagliare l'asse x in x_1, poi si ripete verso x_2, con le iterate che si accalcano sullo zero sqrt(2); accanto, mini-riquadro di confronto con la bisezione che dimezza gli intervalli molto piu' lentamente.
Obiettivo didattico: visualizzare l'iterazione tangente-zero-tangente e il contrasto di velocita' con la bisezione.
-->

---
### **3. Condizioni, fallimenti, arresto**

Il metodo **non** converge sempre; l'onestà sulle ipotesi è parte del metodo.

- **Divisione per zero**: se $f'(x_n)=0$ (tangente orizzontale) l'iterazione non è definita; vicino a punti stazionari il passo $\frac{f}{f'}$ esplode e spara lontano;
- **scelta iniziale**: la convergenza è **locale** — garantita solo partendo abbastanza vicino a uno zero con $f'\neq 0$. Da lontano può divergere o saltare su un altro zero. Esempio di ciclo: $f(x)=x^3-2x+2$ da $x_0=0$ dà $x_1=1$, $x_2=0$, $x_3=1,\dots$: oscillazione perpetua senza convergenza;
- **fallimento strutturale**: $f(x)=\sqrt[3]{x}$ da qualunque $x_0\neq 0$: $x_{n+1}=x_n-\frac{x_n^{1/3}}{\frac13 x_n^{-2/3}}=-2x_n$ — le iterate **raddoppiano** allontanandosi a ogni passo (la tangente verticale nello zero rompe tutto);
- **criteri di arresto pratici**: ci si ferma quando $|x_{n+1}-x_n|\le\tau$ **e/o** $|f(x_{n+1})|\le\tau'$, con un tetto sul numero di iterazioni; i due criteri misurano cose diverse (vicinanza tra iterate contro piccolezza del residuo) e su funzioni piatte o ripide possono ingannare separatamente: usarli in coppia;
- **confronto con la bisezione** (M08): la bisezione è lenta ma **inarrestabile** (bastano continuità e cambio di segno, convergenza garantita con stima d'errore certa); Newton è velocissimo ma esigente ($f'$ disponibile e non nulla, innesco vicino). La strategia dei risolutori reali è ibrida: bisezione per avvicinarsi, Newton per rifinire;
- la **dimostrazione** della convergenza quadratica richiede la formula di Taylor col resto: rinviata a M10 (UD3), qui si dichiara e si osserva sperimentalmente.

---
### **4. Derivazione numerica**

Quando $f$ è nota solo per punti (misure, software), la derivata si **stima** con differenze finite:

$$
f'(x) \approx \frac{f(x+h)-f(x)}{h} \qquad\text{(differenza in avanti)}
$$

$$
f'(x) \approx \frac{f(x+h)-f(x-h)}{2h} \qquad\text{(differenza centrata)}
$$

La centrata è la media dei rapporti avanti e indietro ed è **più precisa**: per una funzione regolare l'errore di troncamento è dell'ordine di $h$ per la prima e di $h^2$ per la seconda (la quantificazione esatta userà Taylor, M10; l'intuizione: nella centrata i contributi simmetrici dell'errore lineare si elidono).

**Il nemico nascosto: la cancellazione numerica.** In aritmetica floating point $f(x+h)$ e $f(x)$ sono quasi uguali per $h$ piccolo: la loro differenza perde cifre significative (cancellazione, come nel test dei segni della bisezione in M08/UD5/L4), e l'errore di **arrotondamento** sul rapporto cresce come $\frac{\varepsilon_{\text{mach}}}{h}$. Errore totale $\approx C_1 h^{p} + \frac{C_2\varepsilon}{h}$: al calare di $h$ prima migliora (troncamento) poi peggiora (arrotondamento). Esiste un $h$ **ottimale** (per la centrata, dell'ordine di $\sqrt[3]{\varepsilon}\approx 6\cdot 10^{-6}$ in doppia precisione): "più piccolo è meglio" è **falso** al calcolatore.

> 📌 Derivata matematica e stima numerica sono oggetti diversi: la prima è un limite esatto, la seconda un rapporto incrementale a $h$ fissato, con due errori in competizione. Confonderle porta a bug silenziosi nei test numerici.

---
### **5. Discesa del gradiente in una dimensione**

L'ottimizzazione numerica usa la derivata come **bussola**: per minimizzare $f$, muoviti nella direzione in cui $f$ scende, cioè **contro** il segno di $f'$:

$$
x_{n+1} = x_n - \eta\,f'(x_n)
$$

con $\eta>0$ (**learning rate**). Se $f'(x_n)>0$ la funzione sale: ci si sposta a sinistra; se $f'(x_n)<0$, a destra; nei punti stazionari l'iterazione si ferma (Fermat riletto come condizione di arresto). Con $\eta$ troppo grande si scavalca il minimo e si può divergere; troppo piccolo, si converge lentissimamente. In più dimensioni $f'$ diventa il gradiente, e questa iterazione — con tutte le sue raffinazioni — è il motore dell'addestramento dei modelli di **machine learning**: ogni passo di training è, in fondo, un'applicazione del criterio del segno della derivata (UD5/L4).

Avvertenza di perimetro: per funzioni su **insiemi discreti** (complessità di algoritmi al variare di $n$ intero, funzioni su bit) la derivata non è definita — non c'è limite del rapporto su un dominio senza punti di accumulazione (M08/UD1/L3A). Si usano differenze finite o si **modella** il problema con una variabile continua, dichiarandolo: la derivata è uno strumento del continuo.

---
### **6. Errori frequenti**

- ❌ Applicare Newton senza controllare $f'(x_n)\neq 0$ o promettere convergenza globale (cicli e fughe esistono: punto 3).
- ❌ Fermarsi al solo criterio $|x_{n+1}-x_n|$ piccolo su funzioni molto piatte (il residuo può essere ancora grande), o al solo $|f|$ piccolo su funzioni molto ripide.
- ❌ Nella derivazione numerica, ridurre $h$ "il più possibile" ignorando la cancellazione.
- ❌ Usare la differenza in avanti quando la centrata è disponibile allo stesso costo.
- ❌ Nel gradient descent, dare a $\eta$ la colpa di ogni divergenza senza guardare la scala di $f'$.
- ❌ Derivare rispetto a variabili intrinsecamente discrete senza dichiarare il modello continuo.

---
### **7. Esercizi**

**Esercizio 1.** Scrivi l'iterazione di Newton per $f(x)=x^3-5$ e calcola $x_1,x_2$ da $x_0=2$.

*Soluzione.* $x_{n+1}=x_n-\frac{x_n^3-5}{3x_n^2}=\frac{2x_n}{3}+\frac{5}{3x_n^2}$. $x_1=\frac{4}{3}+\frac{5}{12}=\frac{7}{4}=1{,}75$; $x_2=\frac{7}{6}+\frac{5}{3\cdot(49/16)}=\frac{7}{6}+\frac{80}{147}\approx 1{,}7109$ (e $\sqrt[3]{5}=1{,}7099\ldots$: già 3 cifre).

**Esercizio 2.** Mostra che per $f(x)=x^2$ (zero doppio in $0$) Newton dà $x_{n+1}=\frac{x_n}{2}$: converge, ma a che velocità?

*Soluzione.* $x_{n+1}=x_n-\frac{x_n^2}{2x_n}=\frac{x_n}{2}$: dimezzamento a ogni passo — convergenza **lineare**, non quadratica. Gli zeri multipli (dove $f'$ si annulla nello zero stesso) degradano Newton al passo della bisezione.

**Esercizio 3.** Stima $f'(1)$ per $f(x)=x^3$ con differenza in avanti e centrata, $h=0{,}1$, e confronta con il valore esatto $3$.

*Soluzione.* Avanti: $\frac{1{,}331-1}{0{,}1}=3{,}31$ (errore $0{,}31$). Centrata: $\frac{1{,}331-0{,}729}{0{,}2}=3{,}01$ (errore $0{,}01$): trenta volte meglio allo stesso costo, coerente con gli ordini $h$ contro $h^2$.

**Esercizio 4.** Per $f(x)=x^2$ e discesa del gradiente $x_{n+1}=x_n-\eta\cdot 2x_n=(1-2\eta)x_n$: per quali $\eta$ l'iterazione converge a $0$?

*Soluzione.* È una successione geometrica di ragione $1-2\eta$ (M05): converge a $0$ per $|1-2\eta|<1$, cioè $0<\eta<1$; per $\eta=\frac 12$ arriva in un passo; per $\eta>1$ diverge oscillando — il "learning rate troppo alto" in formato giocattolo.

---
### **8. Riepilogo**

> ✅ Newton: zero della tangente, $x_{n+1}=x_n-\frac{f(x_n)}{f'(x_n)}$; convergenza **locale** e (su zeri semplici) quadratica — dichiarata, con prova rinviata a Taylor/M10; fallimenti catalogati (derivata nulla, cicli, $\sqrt[3]{x}$, zeri multipli) e arresto a doppio criterio; ibrido con la bisezione nella pratica. Derivazione numerica: avanti ($\sim h$) contro centrata ($\sim h^2$), errore totale troncamento+arrotondamento con $h$ ottimale, cancellazione come nemico. Gradient descent 1D: muoversi contro il segno di $f'$, con $\eta$ da calibrare — Fermat e il criterio del segno trasformati in algoritmo.
