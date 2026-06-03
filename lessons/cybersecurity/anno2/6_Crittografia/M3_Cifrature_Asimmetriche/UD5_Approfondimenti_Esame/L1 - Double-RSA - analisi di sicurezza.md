# **L1 – Double-RSA: analisi di sicurezza**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 2 appelli (240708 e 250715) con peso di **10 punti** ciascuno — stesso testo in entrambi gli appelli.
>
> **Studia prima:**
> - [UD2 / L1 – Algoritmo RSA](../UD2/L1%20-%20Algoritmo%20RSA.md) → generazione chiavi $(n, e, d)$, cifratura $C = M^e \bmod n$, decifratura $M = C^d \bmod n$
> - [UD2 / L5 – Sicurezza di RSA](../UD2/L5%20-%20Sicurezza%20di%20RSA.md) → attacchi CCA, Common Modulus, Low Exponent; equivalenza fattorizzazione ↔ calcolo di $d$

---

### **1. Definizione di Double-RSA**

Il **Double-RSA** è definito come segue:

> Sia dato un utente con modulo $n = p \cdot q$ e due diversi esponenti pubblici $e_1$ e $e_2$, entrambi coprimi con $\varphi(n) = (p-1)(q-1)$.
> La cifratura Double-RSA di un messaggio $M$ è:
>
> $$\boxed{C(M) = \left((M)^{e_1}\right)^{e_2} \bmod n = M^{e_1 \cdot e_2} \bmod n}$$

La domanda d'esame è invariabilmente: **"Fare considerazioni sulla sicurezza di questo cifrario."**

---

### **2. Riduzione algebrica: Double-RSA = RSA con esponente composto**

La prima osservazione fondamentale è puramente algebrica. Per la proprietà delle potenze:

$$C(M) = (M^{e_1})^{e_2} = M^{e_1 \cdot e_2} \bmod n$$

> 📌 Double-RSA è **matematicamente equivalente** a RSA standard con un singolo esponente pubblico:
> $$e_{\text{eff}} = e_1 \cdot e_2$$
> Cifrare due volte con $e_1$ e poi $e_2$ produce lo stesso risultato di cifrare una volta con $e_1 \cdot e_2$.

---

### **3. Condizione di decifrabilità**

Perché Double-RSA sia decifrabile, deve esistere l'esponente privato $d_{\text{eff}}$ tale che:

$$d_{\text{eff}} = (e_1 \cdot e_2)^{-1} \bmod \varphi(n)$$

Questo esiste **se e solo se**:

$$\gcd(e_1 \cdot e_2,\ \varphi(n)) = 1$$

Se $e_1$ e $e_2$ sono entrambi coprimi con $\varphi(n)$ individualmente (condizione necessaria per RSA standard), il loro prodotto è **generalmente** anch'esso coprimo con $\varphi(n)$ — ma non è garantito, poiché $e_1 \cdot e_2$ potrebbe essere più grande e condividere fattori con $\varphi(n)$.

> ⚠️ **Rischio pratico:** se $e_1 \cdot e_2$ non è coprimo con $\varphi(n)$, la decifratura non è possibile. In RSA standard si sceglie $e$ con attenzione; in Double-RSA il prodotto $e_1 \cdot e_2$ potrebbe non soddisfare questa condizione.

---

### **4. Analisi della sicurezza**

#### **4.1 Double-RSA non aggiunge sicurezza rispetto a RSA singolo**

Supponiamo che $\gcd(e_1 \cdot e_2, \varphi(n)) = 1$. Un avversario che vuole decifrare $C$ deve trovare $d_{\text{eff}}$ tale che:

$$M = C^{d_{\text{eff}}} \bmod n$$

Per fare questo, deve calcolare $d_{\text{eff}} = (e_1 \cdot e_2)^{-1} \bmod \varphi(n)$, il che richiede conoscere $\varphi(n)$, il che richiede fattorizzare $n$.

**Riduzione:** rompere Double-RSA $\Longleftrightarrow$ fattorizzare $n$ $\Longleftrightarrow$ rompere RSA standard.

La sicurezza dipende interamente dalla difficoltà di fattorizzare $n$, **esattamente come RSA standard**. L'uso di due esponenti non rende il problema più difficile.

> 📌 **Conclusione di sicurezza:** Double-RSA offre la **stessa sicurezza** di RSA con esponente singolo $e_{\text{eff}} = e_1 \cdot e_2$. Usare due applicazioni di RSA non aumenta il livello di protezione.

#### **4.2 Il prodotto $e_1 \cdot e_2$ è un esponente RSA ordinario**

Un avversario che conosce $n$, $e_1$, $e_2$ (tutti pubblici) calcola immediatamente $e_{\text{eff}} = e_1 \cdot e_2$. Il problema si riduce a RSA standard con chiave pubblica $(n, e_{\text{eff}})$.

$$\underbrace{(n,\ e_1,\ e_2)}_{\text{chiave pubblica Double-RSA}} \xrightarrow{\text{calcolo banale}} \underbrace{(n,\ e_1 \cdot e_2)}_{\text{chiave pubblica RSA standard}}$$

Nessuna informazione aggiuntiva è nascosta.

---

### **5. Confronto con Double DES**

È utile mettere a confronto con il **Double DES** (già studiato in M2), dove la doppia cifratura con chiavi indipendenti almeno non riduce il problema a una singola cifratura (DES non forma un gruppo rispetto alla composizione).

| Proprietà | Double DES | Double-RSA |
|-----------|-----------|-----------|
| Equivalente a singola cifratura? | No (DES non è un gruppo) | **Sì** ($M^{e_1 e_2}$ = RSA con un esponente) |
| Sicurezza aggiuntiva? | Marginale (attacco MitM riduce a $2^{57}$) | **Nessuna** (identica a RSA con $e = e_1 e_2$) |
| Decifratura possibile? | Sempre | Solo se $\gcd(e_1 e_2, \varphi(n))=1$ |

> 💡 **Differenza chiave:** RSA opera in $\mathbb{Z}_n^*$ che ha struttura di gruppo abeliano, quindi la composizione di due esponenziazioni è sempre un'altra esponenziazione — il gruppo è chiuso. Per DES, lo spazio delle permutazioni sui $2^{64}$ blocchi è enormemente più grande del sottogruppo generato da DES, quindi la composizione può uscire dal sottogruppo.

---

### **6. Caso particolare: $e_1 = e_2$**

Se i due esponenti sono uguali ($e_1 = e_2 = e$):

$$C(M) = M^{e^2} \bmod n$$

L'esponente effettivo è $e^2$. Per $e = 3$ (scelta comune): $e_{\text{eff}} = 9$. Poiché $\gcd(9, \varphi(n)) = 1$ (assumendo $\varphi(n)$ non divisibile per 3), la decifratura è possibile ma la sicurezza è ancora identica a RSA con esponente 9.

---

### **7. Esercizio svolto stile esame (10 punti)**

#### **Testo (Appello 240708 e 250715 — identico)**

> Sia dato il cifrario Double-RSA definito da $C(M) = ((M)^{e_1})^{e_2} \bmod n$, dove $e_1$ ed $e_2$ sono due diverse chiavi pubbliche dello stesso utente RSA con modulo $n$.
>
> Fare considerazioni sulla sicurezza di questo cifrario. *(10 pt)*

---

#### **Soluzione:**

**Riduzione algebrica.** Per la proprietà delle potenze:
$$C(M) = M^{e_1 \cdot e_2} \bmod n$$
Double-RSA è equivalente a RSA con esponente $e_{\text{eff}} = e_1 \cdot e_2$.

**Decifrabilità.** Se $\gcd(e_1 \cdot e_2,\ \varphi(n)) = 1$, esiste $d_{\text{eff}} = (e_1 e_2)^{-1} \bmod \varphi(n)$ e la decifratura è $M = C^{d_{\text{eff}}} \bmod n$. Se la condizione non vale, il cifrario non è invertibile.

**Sicurezza.** Un avversario che conosce la chiave pubblica $(n, e_1, e_2)$ calcola banalmente $e_{\text{eff}} = e_1 \cdot e_2$. Il problema di rompere Double-RSA si riduce immediatamente al problema di rompere RSA standard con chiave $(n, e_1 e_2)$, che è equivalente alla fattorizzazione di $n$.

**Conclusione.** Double-RSA **non offre sicurezza aggiuntiva** rispetto a RSA con esponente singolo. Il prodotto $e_1 \cdot e_2$ è calcolabile pubblicamente, quindi il sistema ha esattamente la stessa resistenza di RSA standard. Cifrare due volte con RSA non è analogo al Double DES: poiché RSA opera su un gruppo abeliano chiuso ($\mathbb{Z}_n^*$), la composizione di due esponenziazioni è ancora un'esponenziazione.

---

### **8. Sintesi**

| Domanda | Risposta |
|---------|---------|
| $C(M) = ((M)^{e_1})^{e_2}$ equivale a? | RSA con $e_{\text{eff}} = e_1 \cdot e_2$ |
| Sicurezza aggiuntiva rispetto a RSA? | Nessuna |
| Condizione per decifrare? | $\gcd(e_1 e_2, \varphi(n)) = 1$ |
| Esponente privato? | $d_{\text{eff}} = (e_1 e_2)^{-1} \bmod \varphi(n)$ |
| Confronto con Double DES? | Double DES non si riduce a DES singolo; Double-RSA sì |

> ✅ **Recap:** Double-RSA si riduce algebricamente a RSA con esponente $e_1 \cdot e_2$. Il fatto che questo esponente sia il prodotto di due valori pubblici non aggiunge alcuna sicurezza: l'avversario lo calcola istantaneamente. La sicurezza del sistema rimane interamente agganciata alla difficoltà di fattorizzare $n$, identica a RSA standard.
