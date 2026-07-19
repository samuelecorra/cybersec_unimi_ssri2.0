# **M3 UD1 Lezione 2 - Insiemi infiniti**

### **1. Dal finito all'infinito**

Un insieme non diventa infinito perché è “molto grande”. La distinzione è strutturale: nel caso finito esiste una biiezione con un insieme $I_n$; nel caso infinito nessun naturale conclude il conteggio.

**Definizione.** Un insieme $A$ è **infinito** se non è finito, cioè se:

$$
\forall n\in\mathbb N,
\quad
A\not\sim I_n.
$$

Questa è la definizione principale del corso e non richiede alcun assioma di scelta.

### **2. Perché $\mathbb N$ è infinito**

Supponiamo per assurdo che esista una biiezione $f:I_n\to\mathbb N$. L'immagine finita:

$$
\{f(0),f(1),\ldots,f(n-1)\}
$$

possiede un massimo $M$. Ma $M+1\in\mathbb N$ e non appartiene all'immagine di $f$, contro la suriettività. Dunque:

$$
\mathbb N\text{ è infinito}.
$$

La convenzione resta:

$$
\mathbb N=\{0,1,2,\ldots\}.
$$

### **3. Equipotenza**

Per insiemi arbitrari definiamo:

$$
A\sim B
$$

se esiste una biiezione $f:A\to B$. L'equipotenza è una relazione di equivalenza:

- **riflessiva:** $\operatorname{id}_A:A\to A$ è biiettiva;
- **simmetrica:** se $f:A\to B$ è biiettiva, allora $f^{-1}:B\to A$ è biiettiva;
- **transitiva:** se $f:A\to B$ e $g:B\to C$ sono biiettive, allora $g\circ f:A\to C$ è biiettiva.

La cardinalità può essere pensata intuitivamente come ciò che tutti gli insiemi equipotenti hanno in comune. Una costruzione assiomatica dei cardinali come oggetti non è necessaria in questo corso.

### **4. Una parte grande quanto il tutto**

Consideriamo:

$$
\mathbb N_{>0}=\mathbb N\setminus\{0\}.
$$

La funzione:

$$
s:\mathbb N\to\mathbb N_{>0},
\qquad
s(n)=n+1,
$$

è iniettiva, perché $s(m)=s(n)$ implica $m=n$, ed è suriettiva, perché per ogni $k\in\mathbb N_{>0}$ vale $k=s(k-1)$. Quindi:

$$
\mathbb N\sim\mathbb N\setminus\{0\}.
$$

Analogamente:

$$
d:\mathbb N\to2\mathbb N,
\qquad
d(n)=2n,
$$

è una biiezione tra i naturali e i naturali pari.

<!-- TODO FIGURA:
Disporre su due righe i naturali 0,1,2,3,... e i pari 0,2,4,6,...; collegare verticalmente n con 2n e marcare che la seconda riga è un sottoinsieme proprio della prima. Aggiungere in un riquadro parallelo la corrispondenza n -> n+1 tra N e N senza 0. Obiettivo didattico: mostrare concretamente come un insieme infinito possa essere equipotente a una sua parte propria senza suggerire che gli elementi vengano persi.
-->

Questo fenomeno è impossibile nel caso finito: se $B\subsetneq A$ e $A$ è finito, allora $|B|<|A|$, quindi non esiste alcuna biiezione $A\to B$.

### **5. Insiemi Dedekind-infiniti**

**Definizione.** Un insieme $A$ è **Dedekind-infinito** se esiste un sottoinsieme proprio $B\subsetneq A$ tale che:

$$
A\sim B.
$$

Equivalentemente, $A$ è Dedekind-infinito se esiste un'iniezione $u:A\to A$ non suriettiva.

**Dimostrazione dell'equivalenza.** Se $f:A\to B\subsetneq A$ è biiettiva, l'inclusione $B\hookrightarrow A$ composta con $f$ è iniettiva ma non suriettiva. Viceversa, se $u:A\to A$ è iniettiva e non suriettiva, allora $u:A\to u(A)$ è una biiezione e $u(A)\subsetneq A$. $\square$

Ogni insieme Dedekind-infinito è infinito, perché nessun insieme finito è equipotente a un proprio sottoinsieme.

### **6. La sottigliezza dell'assioma della scelta**

Nella teoria usuale ZFC vale anche il viceversa:

$$
A\text{ infinito}
\iff
A\text{ Dedekind-infinito}.
$$

L'idea è scegliere ricorsivamente elementi distinti $a_0,a_1,a_2,\ldots$ di $A$ e farli scorrere con $a_n\mapsto a_{n+1}$, lasciando fissi gli altri elementi. Questa costruzione richiede una forma di scelta quando $A$ non è accompagnato da una regola esplicita per selezionare gli elementi.

> 📌 In assenza dell'assioma della scelta possono esistere modelli della teoria degli insiemi con insiemi infiniti Dedekind-finiti. Nel percorso principale lavoreremo nelle usuali assunzioni ZFC e segnaleremo i punti in cui una scelta simultanea è rilevante.

### **7. Aggiungere o togliere elementi**

Per $\mathbb N$ aggiungere o togliere un numero finito di elementi non cambia la cardinalità. Per esempio, se $F=\{a_1,\ldots,a_r\}$ è disgiunto da $\mathbb N$, la funzione:

$$
h:\mathbb N\cup F\to\mathbb N
$$

definita da:

$$
h(a_j)=j-1,
\qquad
h(n)=n+r
$$

è biiettiva. Quindi:

$$
|\mathbb N\cup F|=|\mathbb N|.
$$

Nelle usuali assunzioni, lo stesso principio vale per ogni insieme infinito: l'aggiunta o la rimozione di un numero finito di elementi non ne cambia la cardinalità. Non è però corretto usarlo come slogan senza costruire una biiezione o richiamare un teorema applicabile.

### **8. Esempi e controesempi**

- $\mathbb Z$, $\mathbb Q$ e $\mathbb R$ sono infiniti, ma non hanno tutti la stessa cardinalità.
- Un sottoinsieme di un insieme infinito può essere finito, come $\{0,1\}\subseteq\mathbb N$.
- Può essere infinito e proprio, come $2\mathbb N\subsetneq\mathbb N$.
- L'inclusione $A\subseteq B$ fornisce un'iniezione $A\hookrightarrow B$, ma da sola non stabilisce se le cardinalità siano uguali o strettamente diverse.
- Densità e cardinalità sono indipendenti: $\mathbb Q$ è denso in $\mathbb R$ ma sarà dimostrato numerabile.

### **9. Errori frequenti**

- Definire “infinito” come “equipotente a un proprio sottoinsieme” senza dichiarare la dipendenza dalle assunzioni insiemistiche.
- Pensare che ogni sottoinsieme proprio abbia cardinalità minore: è vero nel finito, falso nell'infinito.
- Confondere $a\in A$ con $A\sim B$: appartenenza ed equipotenza hanno oggetti e significati diversi.
- Dire che $\mathbb N$ è infinito soltanto perché contiene i puntini di sospensione: serve un argomento che escluda ogni biiezione con $I_n$.

### **10. Esercizi**

1. Verificare formalmente che $n\mapsto n+3$ è una biiezione $\mathbb N\to\mathbb N\setminus\{0,1,2\}$.
2. Costruire una biiezione tra $\mathbb Z$ e $\mathbb Z\setminus\{0\}$.
3. Mostrare che l'esistenza di un'iniezione non suriettiva $A\to A$ implica che $A$ non è finito.
4. Dare un esempio di sottoinsieme finito e uno infinito di $\mathbb Q$.
5. Spiegare esattamente dove interviene una scelta nella costruzione di una successione di elementi distinti di un insieme infinito astratto.

### **11. Riepilogo**

> ✅ Un insieme infinito non è equipotente ad alcun $I_n$; nelle usuali assunzioni è anche equipotente a un proprio sottoinsieme.

- l'equipotenza è una relazione di equivalenza;
- $\mathbb N\sim\mathbb N\setminus\{0\}\sim2\mathbb N$;
- Dedekind-infinito implica infinito senza ipotesi ulteriori;
- il viceversa è valido in ZFC e richiede cautela senza scelta;
- inclusione ed equipotenza non sono la stessa relazione.
