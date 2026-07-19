# **M7 UD1 Lezione 1 - Intro funzioni - R f R**

### **1. Introduzione**

Le funzioni reali di variabile reale sono l’oggetto centrale dell’Analisi 1. Una funzione descrive una dipendenza: a ogni valore ammesso della variabile indipendente associa un valore della variabile dipendente.

La scrittura tipica è:

$$
f:\mathbb{R}\to\mathbb{R}
$$

oppure, più precisamente:

$$
f:D\subseteq\mathbb{R}\to\mathbb{R}
$$

dove $D$ è l’insieme dei valori per cui la funzione è definita.

> 📌 Studiare una funzione significa capire dominio, immagine, zeri, segno, monotonia, estremi, simmetrie e comportamento limite.

---

### **2. Definizione di funzione reale**

Una funzione reale di variabile reale è un'applicazione:

$$
f:D\to\mathbb{R}
$$

dove:

$$
D\subseteq\mathbb{R}
$$

e a ogni $x\in D$ associa uno e un solo numero reale:

$$
f(x)\in\mathbb{R}
$$

L’insieme $D$ si chiama **dominio** o **insieme di definizione**.

La notazione completa è

$$
f:D\to\mathbb{R},
\qquad
x\mapsto f(x).
$$

La definizione contiene tre dati inseparabili: dominio, codominio e legge di associazione. La legge deve soddisfare:

1. **esistenza:** per ogni $x\in D$ esiste un valore associato;
2. **unicità:** tale valore è uno solo.

Una funzione non coincide con la formula che può rappresentarla. Il grafico è un insieme di coppie, una tabella elenca alcuni o tutti i valori, un algoritmo descrive una procedura di calcolo, una relazione può associare più valori allo stesso ingresso e un'equazione esprime una condizione. Questi oggetti possono descrivere una funzione, ma non sono automaticamente la funzione stessa.

Esempio: l'equazione $y^2=x$ è una relazione. Per $x>0$ associa due valori, quindi non definisce $y$ come funzione di $x$ finché non si sceglie, per esempio, il ramo $y=\sqrt{x}$.

---

### **3. Dominio, codominio e immagine**

Il dominio è l’insieme degli input ammessi:

$$
D_f=\{x\in\mathbb{R}: f(x) \text{ è definita}\}
$$

Il codominio è l’insieme dichiarato dei possibili valori, spesso $\mathbb{R}$.

L’immagine è l’insieme dei valori effettivamente assunti:

$$
f(D_f)=\{f(x):x\in D_f\}
$$

Esempio:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

ha immagine:

$$
[0,+\infty)
$$

anche se il codominio dichiarato è $\mathbb{R}$.

La **controimmagine** di $E\subseteq\mathbb{R}$ è

$$
f^{-1}(E)=\{x\in D:f(x)\in E\}.
$$

Questa notazione non presuppone che $f$ sia invertibile. Per $f(x)=x^2$,

$$
f^{-1}([1,4])=[-2,-1]\cup[1,2].
$$

---

### **4. Grafico**

Il grafico di una funzione $f:D\to\mathbb{R}$ è:

$$
G_f=\{(x,f(x)):x\in D\}
$$

È un sottoinsieme del piano cartesiano.

<!-- TODO FIGURA:
Diagramma a frecce con dominio A, codominio B, alcuni elementi di B non raggiunti e l'immagine f(A) evidenziata; accanto, grafico cartesiano con proiezioni del grafico sugli assi x e y.
Obiettivo didattico: distinguere dominio, codominio, immagine e grafico come quattro oggetti diversi.
-->

Il grafico permette di leggere molte proprietà: dove la funzione è positiva, dove è nulla, dove cresce, dove ha massimi e minimi.

---

### **5. Variabile indipendente e dipendente**

Nella scrittura:

$$
y=f(x)
$$

la variabile $x$ è indipendente, mentre $y$ dipende da $x$.

Esempio:

$$
f(x)=2x+1
$$

Se:

$$
x=3
$$

allora:

$$
f(3)=7
$$

---

### **6. Funzioni elementari**

Le funzioni elementari che ricorreranno continuamente sono:

- polinomiali;
- razionali;
- irrazionali;
- esponenziali;
- logaritmiche;
- goniometriche;
- con valore assoluto;
- composizioni di funzioni elementari.

Per ciascuna di esse il primo passo è sempre individuare il dominio.

---

### **7. Funzioni uguali**

Due funzioni sono uguali se hanno lo stesso dominio e assumono lo stesso valore in ogni punto del dominio.

Per esempio:

$$
f(x)=\frac{x^2-1}{x-1}
$$

e:

$$
g(x)=x+1
$$

non sono la stessa funzione se $f$ ha dominio $\mathbb{R}\setminus\{1\}$ e $g$ ha dominio $\mathbb{R}$.

Infatti le formule coincidono per $x\neq 1$, ma i domini sono diversi.

> ⚠️ Una semplificazione algebrica non cancella automaticamente le restrizioni del dominio originale.

---

### **8. Dominio dichiarato e dominio naturale**

Il **dominio dichiarato** è l'insieme scritto nella definizione $f:A\to B$. Il **dominio naturale** di un'espressione è il più grande sottoinsieme di $\mathbb{R}$ nel quale tutte le operazioni presenti hanno significato reale.

La stessa espressione può essere usata su un dominio più piccolo del dominio naturale. Per esempio,

$$
f:[0,1]\to\mathbb{R},
\qquad
f(x)=x^2
$$

ha dominio dichiarato $[0,1]$, benché l'espressione $x^2$ abbia dominio naturale $\mathbb{R}$.

La stessa formula può inoltre definire funzioni diverse:

$$
f:\mathbb{R}\to\mathbb{R},
\qquad
f(x)=x^2,
$$

$$
g:[0,+\infty)\to[0,+\infty),
\qquad
g(x)=x^2.
$$

Le due funzioni hanno domini e codomini diversi. La prima non è invertibile; la seconda lo è.

### **9. Perimetro del modulo**

Il percorso principale riguarda funzioni

$$
f:A\subseteq\mathbb{R}\to\mathbb{R}.
$$

Esistono anche funzioni $\mathbb{R}\to\mathbb{C}$, funzioni complesse $\mathbb{C}\to\mathbb{C}$ e funzioni di più variabili $F:\mathbb{R}^n\to\mathbb{R}^m$. Richiedono strumenti specifici e non sono il tema di M07. Le successioni studiate in M05 sono invece funzioni con dominio discreto $\mathbb{N}$.

Per la teoria insiemistica completa di applicazioni, immagini e controimmagini si rinvia a [M02 UD2 L1](../../M02_Insiemistica e Relazioni/UD2 - Applicazioni/L1 - Intro Applicazioni.md). M07 la applica alle funzioni reali.

### **10. Errori frequenti ed esercizi**

> ⚠️ Scrivere soltanto $f(x)=x^2$ non fissa una funzione completa se dominio e codominio non sono ricavabili dal contesto.

1. Spiega quali tra $y=x^2$, la tabella $(0,0),(1,1)$ e l'algoritmo "restituisci il quadrato" determinano una funzione completa senza ulteriori dati.
2. Confronta $f:\mathbb{R}\to\mathbb{R}$ e $g:\mathbb{R}\to[0,+\infty)$, entrambe date da $x^2$: calcola immagine e controimmagine di $[1,4]$.
3. Stabilisci se $x^2+y^2=1$ definisce $y$ come funzione di $x$; proponi due restrizioni che producano funzioni.
4. Fornisci due funzioni con la stessa formula e lo stesso dominio ma codomini diversi.
5. Distingui valore $f(2)$, immagine $f(D)$ e grafico $G_f$ per $f(x)=2x-1$ su $D=[0,3]$.

---

### **11. Riepilogo**

> ✅ Una funzione reale di variabile reale associa a ogni $x$ del dominio un unico valore reale $f(x)$.

Punti essenziali:

- il dominio è parte della definizione della funzione;
- l’immagine è l’insieme dei valori assunti;
- il grafico è l’insieme delle coppie $(x,f(x))$;
- una funzione comprende dominio, codominio e legge di associazione;
- controimmagine e funzione inversa sono concetti diversi;
- due funzioni con stessa formula ma domini diversi non sono uguali;
- lo studio di funzione parte sempre dal dominio.
