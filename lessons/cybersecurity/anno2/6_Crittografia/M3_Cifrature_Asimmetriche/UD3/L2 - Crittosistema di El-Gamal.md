# **Lezione 2: Crittosistema di El-Gamal**

### **1. Introduzione**

Il **crittosistema di El-Gamal**, proposto da **Taher El-Gamal nel 1984**, è uno dei più importanti algoritmi di **cifratura a chiave pubblica**. Si basa su due problemi matematici strettamente legati:

1. **Il problema del logaritmo discreto (DL):** dato $\beta = g^\alpha \bmod p$, trovare $\alpha$. (Difficile per $p$ grande, come visto in L1.)

2. **Il problema di Diffie-Hellman computazionale (CDH):** dati $g^a \bmod p$ e $g^b \bmod p$, calcolare $g^{ab} \bmod p$ — *senza* conoscere né $a$ né $b$.

> 📌 **Relazione tra DL e CDH.** Se sai risolvere DL, puoi risolvere CDH: basta estrarre $a$ da $g^a$ (con DL), poi elevare $g^b$ alla $a$. Quindi DL è *almeno* difficile quanto CDH. Se invece CDH sia strettamente più facile di DL è un **problema aperto**: la sicurezza di El-Gamal riposa sull'ipotesi CDH, non necessariamente sull'ipotesi DL più forte.

> 💡 **Intuizione del sistema.** El-Gamal è essenzialmente uno schema di **cifratura con una chiave di sessione condivisa**: Bob usa la chiave pubblica di Alice per costruire un "segreto condiviso" $g^{\alpha k}$ che solo Alice può ricostruire conoscendo $\alpha$. Il messaggio viene mascherato con questo segreto. Alice lo smascherà eseguendo la stessa operazione dalla propria parte.

Entrambi i problemi operano nel gruppo moltiplicativo $\mathbb{Z}_p^*$, dove $p$ è un numero primo grande e $g$ è un generatore del gruppo.

---

### **2. Generazione delle chiavi**

#### **Parametri di sistema (pubblici, condivisi da tutti)**

- $p$: numero **primo grande** (tipicamente 2048–4096 bit in usi moderni)
- $g$: **generatore** del gruppo moltiplicativo $\mathbb{Z}_p^*$ (cioè $g$ genera tutti i $p-1$ elementi non-zero)

Questi valori sono pubblici e possono essere standardizzati (RFC 3526 definisce gruppi raccomandati per vari livelli di sicurezza).

#### **Chiave privata**

Ogni utente sceglie un intero casuale:

$$  
\alpha \in \mathbb{Z}_{p-1} = \{0, 1, \ldots, p-2\}
$$

> 💡 **Perché $\alpha \in \mathbb{Z}_{p-1}$ e non $\mathbb{Z}_p$?** Per il **piccolo teorema di Fermat**, in $\mathbb{Z}_p^*$ vale $g^{p-1} \equiv 1 \pmod{p}$. Questo significa che le potenze di $g$ sono **periodiche con periodo $p-1$**: $g^{\alpha} = g^{\alpha \bmod (p-1)}$. Gli esponenti distinti sono quindi solo $p-1$, non $p$, e si scelgono in $\mathbb{Z}_{p-1}$.

#### **Chiave pubblica**

Si calcola:

$$  
\beta = g^{\alpha} \bmod p  
$$

> 📌 **Asimmetria fondamentale.** Calcolare $\beta$ da $\alpha$ è facile: basta usare square-and-multiply in $O(\log \alpha)$ moltiplicazioni. Risalire ad $\alpha$ da $\beta$ richiede di risolvere il DL in $\mathbb{Z}_p^*$, che è computazionalmente difficile per $p$ grande. Questa asimmetria è l'intera base della sicurezza del sistema.

#### **Riepilogo**

| Utente | Chiave pubblica | Chiave privata |
|---|---|---|
| Alice | $(p,\; g,\; \beta = g^\alpha \bmod p)$ | $\alpha$ |

---

### **3. Cifratura**

> 💡 **L'idea prima delle formule.** Bob vuole mascherare $M$ con un segreto che solo Alice può ricostruire. Se Bob conosce $\beta = g^\alpha$ (chiave pubblica di Alice), sceglie un esponente casuale $k$ e calcola $\beta^k = g^{\alpha k}$: questo è il **segreto condiviso di sessione**. Alice potrà ricostruirlo perché conosce $\alpha$ e riceverà $g^k$; un avversario che conosce solo $g^k$ e $g^\alpha$ dovrebbe risolvere il problema CDH per ottenerlo.

Quando **Bob** vuole inviare un messaggio $M$ ad **Alice** (chiave pubblica $(p, g, \beta)$):

1. Sceglie un intero casuale $k \in \mathbb{Z}_{p-1}$ — usato una sola volta (nonce di sessione).

2. Calcola il **segreto condiviso di sessione** $\beta^k = (g^\alpha)^k = g^{\alpha k} \bmod p$, poi costruisce la coppia:

$$
y_1 = g^k \bmod p
$$

$$
y_2 = M \cdot \beta^k \bmod p
$$

3. Il **testo cifrato** è la coppia:

$$
C = (y_1,\; y_2)
$$

> 📌 **Cosa trasportano $y_1$ e $y_2$.** $y_1 = g^k$ è la "busta chiusa" che dice ad Alice quale $k$ ha usato Bob — ma per aprirla e trovare $k$ bisognerebbe risolvere il DL. $y_2 = M \cdot \beta^k$ è il messaggio mascherato dal segreto condiviso: senza $\beta^k$, $y_2$ è indistinguibile da un elemento casuale di $\mathbb{Z}_p^*$.

> ⚠️ **Il valore $k$ deve essere segreto e usato una sola volta.** Se un avversario conosce $k$, può calcolare $\beta^k$ e recuperare $M = y_2 \cdot (\beta^k)^{-1}$. Se lo stesso $k$ viene riutilizzato per due messaggi $M_1$ e $M_2$, l'avversario che osserva $(y_1, y_{2,1})$ e $(y_1, y_{2,2})$ può calcolare $M_1 \cdot M_2^{-1} = y_{2,1} \cdot y_{2,2}^{-1} \bmod p$, ottenendo informazioni su entrambi.
    

---

### **4. Decifratura**

Quando **Alice** riceve $C = (y_1, y_2)$ e usa la propria chiave privata $\alpha$:

**Passo 1 — ricostruire il segreto condiviso:**

$$
z = y_1^{\alpha} \bmod p
$$

**Passo 2 — smascherare il messaggio:**

$$
M = y_2 \cdot z^{-1} \bmod p
$$

dove $z^{-1}$ è l’inverso moltiplicativo di $z$ modulo $p$, calcolabile con Euclide esteso (o tramite $z^{p-2} \bmod p$ per il teorema di Fermat).

> 📌 **Perché funziona — derivazione algebrica.**
>
> Alice calcola $z = y_1^\alpha = (g^k)^\alpha = g^{k\alpha}$. Ma $\beta^k = (g^\alpha)^k = g^{k\alpha}$: Alice ha ricostruito esattamente il segreto condiviso che Bob ha usato per mascherare il messaggio.
>
> Quindi:
> $$y_2 \cdot z^{-1} = (M \cdot \beta^k) \cdot (g^{k\alpha})^{-1} = M \cdot g^{k\alpha} \cdot g^{-k\alpha} = M \cdot g^0 = M$$
>
> La cancellazione è esatta: il segreto si azzera moltiplicando per il proprio inverso.

> 💡 **Chi non ha $\alpha$ non può ricostruire $z$.** Un avversario conosce $y_1 = g^k$ e $\beta = g^\alpha$, ma per calcolare $z = g^{k\alpha}$ dovrebbe risolvere il problema CDH — che è computazionalmente difficile. Questo è esattamente il punto in cui la sicurezza del sistema si appoggia all’ipotesi CDH.

---

### **5. Esempio numerico completo**

#### **Dati di Alice**

- $p = 2579$
    
- $g = 2$
    
- $\alpha = 765$
    
- $\beta = g^{\alpha} \bmod p = 2^{765} \bmod 2579 = 949$
    

**Chiavi:**

- Chiave pubblica $(p=2579, g=2, \beta=949)$
    
- Chiave privata $\alpha=765$
    

---

#### **Cifratura (Bob)**

Bob vuole inviare a Alice il messaggio $M = 1299$.

1. Sceglie $k = 853$
    
2. Calcola:  
    $$  
    \begin{aligned}  
    y_1 &= g^k \bmod p = 2^{853} \bmod 2579 = 435
    \end{aligned}
    $$
      
    $$
    \begin{aligned}
    y_2 &= M \cdot \beta^k \bmod p = 1299 \cdot 949^{853} \bmod 2579 = 2396  
    \end{aligned}  
    $$
    

**Testo cifrato:**  
$$  
C = (435, 2396)  
$$

---

#### **Decifratura (Alice)**

Alice riceve $C = (435, 2396)$ e usa $\alpha = 765$:

**Passo 1 — ricostruire il segreto condiviso:**

$$
z = y_1^{\alpha} \bmod p = 435^{765} \bmod 2579 = 2396 \cdot 1299^{-1} \bmod 2579
$$

> 💡 Il valore numerico esatto di $z$ non si calcola a mano in questo esempio (richiederebbe 765 quadrati modulari). Possiamo però verificarlo indirettamente: deve valere $z = \beta^k \bmod p = 949^{853} \bmod 2579$, cioè lo stesso segreto condiviso che ha usato Bob. La coerenza si controlla sul risultato finale.

**Passo 2 — smascherare il messaggio:**

$$
M = y_2 \cdot z^{-1} \bmod p = 2396 \cdot z^{-1} \bmod 2579 = 1299
$$

**Verifica algebrica:**

$$
y_2 \cdot z^{-1} = (M \cdot \beta^k) \cdot (\beta^k)^{-1} = M = 1299 \checkmark
$$

Il messaggio originale è stato correttamente recuperato.

---

### **6. Sicurezza del sistema**

#### **a) Sicurezza della generazione delle chiavi — riduzione al DL**

Un avversario conosce $(p, g, \beta)$ e vuole calcolare $\alpha$ tale che $\beta = g^{\alpha} \bmod p$.

Questo è **esattamente** la definizione del problema del logaritmo discreto in $\mathbb{Z}_p^*$. Per $p$ di 2048+ bit, il miglior algoritmo noto (NFS) ha complessità $L_p[1/3, 1.923]$ — sub-esponenziale ma computazionalmente impraticabile per valori realistici.

> ⚠️ Se si riuscisse a rompere il DL in $\mathbb{Z}_p^*$, El-Gamal cadrebbe immediatamente: l'avversario recupererebbe $\alpha$ e potrebbe decifrare tutti i messaggi passati e futuri.

---

#### **b) Sicurezza della cifratura — riduzione al CDH**

Un avversario conosce $(p, g, \beta, y_1, y_2)$ e vuole trovare $M$.

Per farlo, deve calcolare il segreto condiviso $z = g^{\alpha k}$ da $y_1 = g^k$ e $\beta = g^\alpha$, cioè risolvere:

$$
\textbf{CDH:} \quad \text{dato } g,\; g^k \bmod p,\; g^\alpha \bmod p \;\Longrightarrow\; \text{calcolare } g^{\alpha k} \bmod p
$$

Questo è il **problema di Diffie-Hellman computazionale**, considerato difficile in $\mathbb{Z}_p^*$.

> 📌 **Gerarchia delle ipotesi.** Si sa che DL $\Rightarrow$ CDH (chi rompe DL rompe CDH), ma non si sa se CDH $\Rightarrow$ DL. Quindi l'ipotesi CDH è *potenzialmente* più debole dell'ipotesi DL: El-Gamal potrebbe essere sicuro anche in un mondo in cui il DL fosse trattabile, purché il CDH rimanga difficile.

---

#### **c) Proprietà probabilistica — sicurezza semantica**

> 💡 **Una proprietà che RSA testbook non ha.** In El-Gamal, lo stesso messaggio $M$ cifrato due volte produce quasi certamente due crittotesti diversi, perché ogni cifratura sceglie un $k$ casuale indipendente. Questo significa che un avversario non può distinguere la cifratura di $M_0$ da quella di $M_1$ semplicemente confrontando i crittotesti — una proprietà chiamata **sicurezza IND-CPA** (indistinguibilità sotto attacchi a testo in chiaro scelto).

In termini pratici: intercettare molti messaggi cifrati non permette di dedurre se due crittotesti corrispondono allo stesso plaintext — protezione nativa contro attacchi statistici.

> ⚠️ **Espansione del crittotesto.** Il prezzo di questa sicurezza è che il crittotesto $(y_1, y_2)$ è **il doppio** della dimensione del plaintext $M$: per un messaggio di $n$ bit si producono $2n$ bit di crittotesto. Questo è uno dei principali svantaggi pratici rispetto a RSA, dove il crittotesto ha la stessa dimensione del modulo.

---

### **7. Sintesi finale**

> ✅ **Schema di lettura rapida per l'esame.**

| Componente | Valore | Ruolo |
|---|---|---|
| $p, g$ | pubblici | parametri di sistema; $g$ generatore di $\mathbb{Z}_p^*$ |
| $\alpha$ | **segreto** di Alice | chiave privata; protetto dal DL |
| $\beta = g^\alpha$ | pubblico | chiave pubblica di Alice |
| $k$ | segreto usa-e-getta di Bob | nonce di sessione; mai riutilizzato |
| $y_1 = g^k$ | nel crittotesto | "busta" che trasporta $k$ protetto dal DL |
| $y_2 = M \cdot \beta^k$ | nel crittotesto | $M$ mascherato dal segreto condiviso $g^{\alpha k}$ |
| $z = y_1^\alpha = g^{k\alpha}$ | calcolato da Alice | ricostruzione del segreto condiviso |

**Tre punti chiave:**

1. **Sicurezza della chiave:** trovare $\alpha$ da $\beta$ richiede DL in $\mathbb{Z}_p^*$ — difficile per $p$ grande (NFS, sub-esponenziale).

2. **Sicurezza della cifratura:** trovare $M$ da $(y_1, y_2)$ senza $\alpha$ richiede CDH — almeno difficile quanto DL, forse uguale (problema aperto).

3. **Proprietà probabilistica (IND-CPA):** il nonce $k$ rende ogni cifratura dello stesso $M$ diversa — protezione nativa che RSA testbook non ha. Il costo è l'**espansione 2×** del crittotesto rispetto al plaintext.