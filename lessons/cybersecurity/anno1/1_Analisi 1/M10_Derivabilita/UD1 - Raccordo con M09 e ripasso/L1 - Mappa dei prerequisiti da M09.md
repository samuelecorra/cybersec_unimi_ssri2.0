# **M10 UD1 Lezione 1 - Mappa dei prerequisiti da M09**

### **1. Che cos'è questo modulo (e che cosa non è)**

M10 è il **secondo tempo** del calcolo differenziale: dato per acquisito tutto M09 (definizione di derivata, regole dimostrate, catalogo delle funzioni elementari, teoremi di Fermat-Rolle-Lagrange-Cauchy-Darboux e loro conseguenze), qui si costruiscono i tre strumenti applicativi maggiori — **l'Hôpital** (UD2), **Taylor** (UD3), **convessità** (UD4) — che confluiranno nello **studio completo di funzione** (M11) e nell'integrazione (M12).

Questa lezione è la mappa di ciò che M10 userà di M09, con i puntatori esatti: se un anello della catena non ti è saldo, è lì che devi tornare **prima** di proseguire.

---
### **2. La catena dei prerequisiti**

| Strumento di M10 | Che cosa usa | Dove è dimostrato |
|---|---|---|
| l'Hôpital (UD2) | teorema di Cauchy (forma con quoziente e condizione $g'\neq 0$) | M09/UD5/L5 |
| l'Hôpital (UD2) | estensione per continuità, carabinieri, teorema del limite della derivata | M08/UD5/L3, M08/UD1/L3D, M09/UD5/L3 |
| Taylor-Peano (UD3) | caratterizzazione $f(x_0+h)=f(x_0)+f'(x_0)h+o(h)$ (caso $n=1$) | M09/UD1/L5 |
| Taylor-Peano (UD3) | piccolo-o e sue regole algebriche | M08/UD4/L3 |
| Taylor-Lagrange (UD3) | teorema di Cauchy con funzioni ausiliarie | M09/UD5/L5 |
| Sviluppi di Maclaurin (UD3) | derivate $n$-esime delle elementari (pattern) | M09/UD3/L5 |
| Criterio derivate successive (UD3/L5) | permanenza del segno | M08/UD1/L3D |
| Convessità (UD4) | monotonia di $f'$ via segno di $f''$; Lagrange sulle pendenze | M09/UD5/L4, L3 |
| Tutto il modulo | derivabile $\Rightarrow$ continua; regole di calcolo | M09/UD1/L4, UD2-UD3 |

> 📌 Il flusso logico complessivo, da saper raccontare a un orale: **limiti** (M08) $\to$ **derivata e valor medio** (M09) $\to$ **Cauchy** $\to$ **l'Hôpital e Taylor** (M10) $\to$ **studio di funzione** (M11). Nessuna freccia torna indietro: i limiti notevoli non si dimostrano con l'Hôpital (circolo, UD2/L4), la scala di crescita ha già una prova senza derivate (M08/UD2/L3), e Taylor generalizza — non fonda — l'approssimazione lineare.

---
### **3. Ripasso lampo con esempi nuovi**

Tre richiami operativi da M09, su esempi che non hai già visto lì.

#### **3.1 Formule e domini (da M09/UD3)**

$f(x)=x^{5/3}$ è definita su tutto $\mathbb{R}$ (radice cubica di $x^5$); la formula dà $f'(x)=\frac{5}{3}x^{2/3}$ per $x\neq 0$, e in $0$ decide la definizione: $\frac{h^{5/3}}{h}=h^{2/3}\to 0$, quindi $f'(0)=0$ — la formula delle potenze si estende al punto critico **solo dopo** il controllo diretto. Contrasto istruttivo: $f(x)=\ln(x^2)$ ha dominio $x\neq 0$ e conviene riscriverla come $2\ln|x|$, da cui $f'(x)=\frac{2}{x}$ sui **due rami separati**; in $0$ non c'è nulla da classificare, perché il punto non appartiene al dominio.

#### **3.2 Raccordi (da M09/UD4)**

$f(x)=\begin{cases}x^2, & x\le 1\\ ax+b, & x>1\end{cases}$: continuità in $1$ dà $a+b=1$; uguaglianza dei limiti di $f'$ (lecita dopo la continuità: teorema del limite della derivata) dà $a=2$, quindi $b=-1$ — e la retta $y=2x-1$ è la tangente alla parabola in $(1,1)$: il raccordo $C^1$ è un incollaggio di tangenti.

#### **3.3 Monotonia su domini spezzati (da M09/UD5/L4)**

$f(x)=\frac 1x$ ha $f'(x)=-\frac{1}{x^2}<0$ su tutto il dominio, ma è decrescente **su ciascuno** dei due intervalli $(-\infty,0)$ e $(0,+\infty)$, non globalmente ($f(-1)=-1<1=f(1)$): i teoremi di monotonia lavorano sugli intervalli, e i punti esclusi dal dominio spezzano la conclusione.

---
### **4. Errori frequenti (ereditati e nuovi)**

- ❌ Rileggere M10 come "il modulo dove si impara a derivare": quello è M09; qui le regole si **usano**.
- ❌ Usare l'Hôpital o Taylor per fondare i limiti notevoli (circolo: UD2/L4).
- ❌ Applicare le formule di derivazione senza controllo dei domini (3.1).
- ❌ Imporre l'uguaglianza dei limiti di $f'$ **prima** della continuità nei raccordi (3.2).
- ❌ Estendere la monotonia attraverso i buchi del dominio (3.3).

---
### **5. Riepilogo**

> ✅ M10 poggia interamente su M09 (e M08): Cauchy per l'Hôpital, approssimazione lineare + piccolo-o + derivate $n$-esime per Taylor, monotonia di $f'$ per la convessità. La mappa dei puntatori è la tabella del punto 2; i tre ripassi (formule/domini, raccordi, monotonia sugli intervalli) coprono i riflessi condizionati che serviranno in ogni esercizio del modulo. Prossima lezione: eserciziario di riscaldamento.
