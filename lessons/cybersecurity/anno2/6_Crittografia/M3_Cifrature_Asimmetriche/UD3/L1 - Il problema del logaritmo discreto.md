
# **Lezione 1: Il problema del logaritmo discreto**

### **1. Altri cifrari asimmetrici e problemi matematici correlati**

Esistono diversi schemi di **cifratura asimmetrica**, ciascuno basato su un **problema matematico difficile** da risolvere.

|Algoritmo|Anno|Problema su cui si basa|Note|
|---|---|---|---|
|**RSA**|1977|Fattorizzazione di numeri grandi|Sicuro, molto diffuso|
|**Merkle–Hellman**|1978|Problema dello zaino 0–1|Molte varianti, quasi tutte rotte|
|**Rabin**|1979|Fattorizzazione|Simile a RSA, ma più teorico|
|**McEliece**|1978|Decodifica di codici lineari|Sicuro ma inefficiente|
|**El-Gamal**|1984|Logaritmo discreto|Base anche del DSA|
|**Curve ellittiche**|1985|Logaritmo discreto su curve|Alta sicurezza a chiavi più corte|
|**Hidden Field Equation (HFE)**|1996|Polinomi multivariabili|Sistema rotto|

📘 **Nota:**  
Il **crittosistema di El-Gamal** si fonda sulla **difficoltà del calcolo del logaritmo discreto**, un problema ritenuto **computazionalmente intrattabile** per numeri grandi.

---

### **2. Definizione del problema del logaritmo discreto**

> 💡 **L’asimmetria chiave.** Calcolare $a^x \bmod n$ dato $(a, x, n)$ è **facile**: basta usare square-and-multiply (complessità $O(\log x)$ moltiplicazioni). Il problema del logaritmo discreto chiede il contrario: dato il risultato $b = a^x \bmod n$, trovare $x$. Come la fattorizzazione rispetto alla moltiplicazione, questa operazione inversa è **computazionalmente molto più costosa** — e su certi gruppi non si conoscono algoritmi efficienti.

Dato un gruppo $G$ finito con operazione moltiplicativa, un elemento $a \in G$ (la **base**) e un altro elemento $b \in G$, il **problema del logaritmo discreto (DL)** consiste nel determinare l’intero $x$ tale che:

$$  
a^x = b \quad \text{in } G
$$

In $\mathbb{Z}_p^*$ (il gruppo moltiplicativo degli interi modulo un primo $p$) questo diventa:

$$  
a^x \equiv b \pmod{p}  
$$

#### **Esempio**

Trovare $x$ tale che:

$$  
3^x \equiv 7 \pmod{13}  
$$

Si prova sistematicamente: $3^1=3,\ 3^2=9,\ 3^3=1,\ 3^4=3,\ldots$ — per moduli piccoli si può fare per enumerazione. La soluzione è $x = 6$, perché:

$$
3^6 = 729 = 56 \cdot 13 + 1 \;\Rightarrow\; 729 \equiv 1 \pmod{13}
$$

> ⚠️ Aspetta — $3^6 \equiv 1$, non $7$. Verifichiamo più attentamente: $3^1=3,\ 3^2=9,\ 3^3=27\equiv 1,\ 3^4=3,\ 3^5=9,\ 3^6=1 \pmod{13}$. Quindi $3$ non è un generatore di $\mathbb{Z}_{13}^*$ e $7$ non è raggiungibile da $3$. L’esempio corretto con base $2$: $2^6 = 64 = 4\cdot13 + 12 \equiv 12 \pmod{13}$; $2^{11} \equiv 7 \pmod{13}$ è la soluzione reale per la base $2$.

> 📌 Il punto non è il valore numerico specifico, ma che **per grandi $p$** non esiste un modo sistematicamente più veloce dell’enumerazione (o degli algoritmi specializzati) per trovare $x$.

---

### **3. Formulazione generale**

Sia $G$ un gruppo finito con operazione generica $\otimes$, e sia $H$ il **sottogruppo ciclico generato da** $a \in G$:

$$  
H = \{\ a^i \mid i \ge 0\ \} = \{a^0, a^1, a^2, \ldots\}
$$

> 📌 La notazione $a^i$ qui significa $\underbrace{a \otimes a \otimes \cdots \otimes a}_{i \text{ volte}}$ rispetto all’operazione del gruppo, qualunque essa sia — moltiplicazione, addizione, o operazione su punti di curva.

Il problema del DL è: dato $b \in H$, determinare l’**unico** intero $x$ con $0 \le x \le |H|-1$ tale che:

$$  
a^x = b  
$$

Questo valore si chiama **logaritmo discreto di $b$ in base $a$** nel gruppo $G$:

$$  
x = \log_a b
$$

> 💡 L’unicità di $x$ è garantita dal fatto che $H$ è ciclico di ordine $|H|$: nella sequenza $a^0, a^1, \ldots, a^{|H|-1}$ ogni elemento di $H$ compare esattamente una volta, poi la sequenza si ripete. Quindi ogni $b \in H$ ha esattamente un logaritmo discreto modulo $|H|$.

---

### **4. Complessità del problema — e perché dipende dal gruppo**

> 📌 **Il punto cruciale che spesso si omette.** Il DL non è difficile in *tutti* i gruppi: la difficoltà dipende interamente dalla **struttura algebrica del gruppo scelto**. Scegliere il gruppo sbagliato rende il DL banalmente risolvibile. Vediamo i due casi principali.

---

#### **4.1 Gruppo additivo $(\mathbb{Z}_n, +)$ — DL facile**

In notazione additiva, "$a^x$" significa $\underbrace{a + a + \cdots + a}_{x} = x \cdot a$. Il problema del DL diventa:

> *trovare $x$ tale che $x \cdot a \equiv b \pmod{n}$*

Questa è una **congruenza lineare** nella variabile $x$. Come abbiamo visto nella lezione su Euclide esteso, si risolve in tempo $O((\log n)^2)$:

1. Calcola $g = \mcd(a, n)$ con Euclide classico.
2. Se $g \nmid b$: nessuna soluzione.
3. Se $g \mid b$: $x = (b/g) \cdot (a/g)^{-1} \bmod (n/g)$, calcolando l'inverso con Euclide esteso.

> ⚠️ Il gruppo $(\mathbb{Z}_n, +)$ è **inutile per la crittografia DL**: chiunque conosca $(a, b, n)$ può trovare $x$ in tempo polinomiale. Usarlo come base di un sistema crittografico sarebbe un errore fatale.

---

#### **4.2 Gruppo moltiplicativo $(\mathbb{Z}_p^*, \cdot)$ — DL difficile**

Qui l'operazione è la moltiplicazione modulo $p$, e "$a^x$" è davvero l'elevazione a potenza modulare. Non esiste un analogo della "divisione" che permetta di estrarre $x$ direttamente: la struttura moltiplicativa nasconde l'esponente in modo che nessun algoritmo polinomiale è noto.

Se $p$ è primo, i migliori algoritmi conosciuti hanno complessità **sub-esponenziale** della forma:

$$  
L_p[\alpha,\, c] = O\!\left(\exp\!\left((c + o(1))\,(\ln p)^{\alpha}\,(\ln \ln p)^{1-\alpha}\right)\right)
$$

dove $0 < \alpha < 1$ e $c > 0$.

> 💡 **Come leggere $L_p[\alpha, c]$.** Per $\alpha = 0$ si avrebbe complessità polinomiale in $\ln p$ (facile); per $\alpha = 1$ sarebbe esponenziale in $\ln p$ (il caso peggiore). Il parametro $\alpha = 1/3$ del NFS è "a metà strada": sub-esponenziale, ma molto più lento di qualsiasi algoritmo polinomiale per $p$ grande.

**Algoritmo più efficiente noto per $\mathbb{Z}_p^*$: Number Field Sieve (NFS)**

$$  
L_p\!\left[\tfrac{1}{3},\ 1.923\right]
$$

> 📌 **Cosa fa il NFS intuitivamente.** Il NFS (e la sua variante Function Field Sieve per certi casi) sfrutta la struttura algebrica di $\mathbb{Z}_p^*$ decomponendo il problema in passi di "setacciatura": si trova un insieme di relazioni tra logaritmi di numeri piccoli (la **base di fattori**), poi si risolve un sistema lineare per estrarre i logaritmi cercati. È computazionalmente pesante ma sub-esponenziale — non polinomiale.

---

#### **4.3 Curve ellittiche — DL ancora più difficile**

Per il **problema del logaritmo discreto su curve ellittiche (ECDLP)**, la migliore strategia generale conosciuta è l'algoritmo **Baby-step Giant-step** (o Pollard's rho), con complessità $O(\sqrt{|G|})$ — **esponenziale** nella metà della dimensione del gruppo. Non esiste un equivalente del NFS per le curve ellittiche su corpi primi.

> 💡 Questo spiega perché ECDSA con chiavi a 256 bit offre sicurezza comparabile a RSA-3072: il logaritmo discreto su curve è un problema intrinsecamente più difficile di quello in $\mathbb{Z}_p^*$.

---

#### **4.4 Riepilogo comparativo**

| Gruppo | Notazione DL | Difficoltà | Algoritmo migliore |
|---|---|---|---|
| $(\mathbb{Z}_n, +)$ addittivo | $x \cdot a \equiv b \pmod{n}$ | **Facile** (polinomiale) | Euclide esteso |
| $(\mathbb{Z}_p^*, \cdot)$ moltiplicativo | $a^x \equiv b \pmod{p}$ | **Difficile** (sub-exp.) | NFS $L_p[1/3, 1.923]$ |
| Curve ellittiche $E(\mathbb{F}_p)$ | $x \cdot P = Q$ | **Molto difficile** (esponenziale) | Pollard's rho $O(\sqrt{p})$ |

---

### **5. Generatori nei gruppi modulari**

> 💡 **Perché i generatori sono essenziali per il DL.** Perché il logaritmo discreto di $b$ in base $a$ esista e sia unico, $b$ deve appartenere al sottogruppo generato da $a$. Se $a$ è un **generatore** dell’intero $\mathbb{Z}_p^*$, allora *ogni* elemento non-zero ha un logaritmo discreto in base $a$: il DL è definito su tutto il gruppo, non solo su un sottogruppo. Nei sistemi basati su DL (El-Gamal, DH, DSA) si sceglie sempre un generatore come base pubblica, proprio per garantire questa proprietà.

Nel gruppo moltiplicativo $\mathbb{Z}_p^*$ (gli interi da $1$ a $p-1$ con moltiplicazione modulo $p$, con $p$ primo), un elemento $g$ si dice **generatore** (o **radice primitiva** modulo $p$) se:

$$  
\{\ g^i \bmod p \mid 1 \le i \le p-1\ \} = \mathbb{Z}_p^*  
$$

cioè se le potenze di $g$ percorrono **tutti** gli elementi del gruppo prima di tornare a $1$.

> 📌 Non tutti gli elementi di $\mathbb{Z}_p^*$ sono generatori. Il numero di generatori è $\varphi(p-1)$ (la funzione di Eulero applicata a $p-1$). Per $p = 11$: $\varphi(10) = 4$, quindi ci sono 4 generatori su 10 elementi.

#### **Esempio con $p = 11$**

Calcoliamo le potenze di $g = 2$ modulo $11$:

| $i$ | $2^i \bmod 11$ |
|---|---|
| 1 | 2 |
| 2 | 4 |
| 3 | 8 |
| 4 | 5 |
| 5 | 10 |
| 6 | 9 |
| 7 | 7 |
| 8 | 3 |
| 9 | 6 |
| 10 | 1 |

Tutti i valori $\{1, 2, \ldots, 10\}$ compaiono esattamente una volta: $g = 2$ è un **generatore di $\mathbb{Z}_{11}^*$**. In particolare, per ogni $b \in \{1,\ldots,10\}$ esiste un unico $x \in \{0,\ldots,9\}$ tale che $2^x \equiv b \pmod{11}$.

Proviamo invece $g = 3$: $3^1=3,\ 3^2=9,\ 3^3=1,\ 3^4=3,\ldots$ — il ciclo si chiude dopo 5 passi, generando solo $\{1, 3, 9, 5, 4\}$. L’elemento $2 \in \mathbb{Z}_{11}^*$ non è raggiungibile: $g=3$ **non** è un generatore.

---

### **6. Struttura di $\mathbb{Z}_p$ e $\mathbb{Z}_p^*$**

$$  
\mathbb{Z}_p = \{\ 0, 1, 2, \dots, p-1\ \} \quad \text{(gruppo additivo, ordine } p\text{)}
$$

$$  
\mathbb{Z}_p^* = \{\ a \in \mathbb{Z}_p : \mcd(a, p) = 1\ \} = \{1, 2, \ldots, p-1\} \quad \text{(gruppo moltiplicativo, ordine } p-1\text{)}
$$

> 💡 **La differenza fondamentale tra i due.** $\mathbb{Z}_p$ con l’addizione è un gruppo dove DL è facile (congruenza lineare). $\mathbb{Z}_p^*$ con la moltiplicazione è il gruppo dove DL è difficile. Quando si parla di "logaritmo discreto" in crittografia, ci si riferisce **sempre** al secondo — il gruppo moltiplicativo. La confusione tra i due è la fonte dell’errore concettuale più comune su questo argomento.

Per ogni generatore $g$ di $\mathbb{Z}_p^*$, la mappa $x \mapsto g^x \bmod p$ è una biiezione da $\{0, 1, \ldots, p-2\}$ a $\mathbb{Z}_p^*$: ogni elemento del gruppo ha esattamente un esponente. Il **logaritmo discreto** in base $g$ è la funzione inversa di questa mappa — facile da costruire (tabella di look-up per $p$ piccolo), ma computazionalmente difficile da invertire per $p$ grande senza la tabella.

---

### **7. Sintesi finale**

> ✅ **Tre punti da ricordare.**

1. **Il DL non è universalmente difficile**: in $(\mathbb{Z}_n, +)$ si riduce a una congruenza lineare risolvibile con Euclide esteso. È difficile solo in gruppi con struttura moltiplicativa appropriata — in primis $\mathbb{Z}_p^*$ con $p$ primo grande, e ancor di più su curve ellittiche.

2. **La scelta del generatore è fondamentale**: $g$ deve generare l’intero gruppo $\mathbb{Z}_p^*$ (o un suo sottogruppo di ordine primo sufficientemente grande) perché il DL sia ben definito e l’esponente $x$ sia unico. Non tutti gli elementi sono generatori — bisogna verificarlo.

3. **La difficoltà in $\mathbb{Z}_p^*$ è sub-esponenziale, non esponenziale**: il NFS ha complessità $L_p[1/3, 1.923]$, cioè né polinomiale né esponenziale pura. Per questo i moduli $p$ devono essere molto più grandi di quelli usati per le curve ellittiche, dove il miglior algoritmo noto è $O(\sqrt{p})$ — esponenziale nella metà dei bit.