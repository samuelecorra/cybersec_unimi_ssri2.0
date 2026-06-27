## **Lezione 2: Scelta dei parametri**

### **1. Introduzione**

Nella lezione precedente è stato descritto il protocollo di **Diffie-Hellman** come meccanismo di accordo su una chiave: Alice e Bob, comunicando su un canale insicuro, riescono a calcolare una stessa chiave segreta senza trasmetterla direttamente.

Per far funzionare il protocollo, però, non basta dire che si lavora con potenze modulari. Occorre scegliere con attenzione i due parametri pubblici:

- il numero primo $p$, che determina il gruppo in cui vengono svolti i calcoli;
- il generatore $g$, cioè l'elemento di base da elevare agli esponenti segreti.

Nel protocollo Diffie-Hellman classico si lavora nel gruppo moltiplicativo:

$$
\mathbb{Z}_p^*=\{1,2,\dots,p-1\}
$$

con il prodotto modulo $p$.

Poiché $p$ è primo, tutti gli elementi da $1$ a $p-1$ sono invertibili modulo $p$, quindi $\mathbb{Z}_p^*$ contiene esattamente $p-1$ elementi.

> 📌 La scelta di $p$ e $g$ non è un dettaglio implementativo: determina sia la correttezza del protocollo, sia la difficoltà del problema matematico su cui si basa la sicurezza.

---

### **2. Il problema della scelta di $p$ e $g$**

Il problema affrontato in questa lezione è il seguente:

1. scegliere un primo $p$ adatto ai calcoli modulari;
2. scegliere un elemento $g \in \mathbb{Z}_p^*$ che sia un **generatore** del gruppo.

Dire che $g$ è un generatore significa che, elevando $g$ a potenze successive modulo $p$, si ottengono tutti gli elementi del gruppo:

$$
\{g^i \bmod p \mid 1 \le i \le p-1\}=\mathbb{Z}_p^*
$$

In questo caso $g$ viene anche chiamato **radice primitiva modulo $p$**.

Per Diffie-Hellman questa proprietà è importante perché Alice e Bob pubblicano valori del tipo:

$$
g^x \bmod p
$$

e:

$$
g^y \bmod p
$$

Se $g$ non generasse un insieme sufficientemente grande di valori, gli esponenti segreti $x$ e $y$ finirebbero per produrre risultati appartenenti a un sottogruppo più piccolo. Questo renderebbe più debole lo spazio effettivo delle possibilità.

> ⚠️ Un $p$ grande non basta. Se $g$ è scelto male, il protocollo può lavorare dentro un sottogruppo piccolo anche se il modulo $p$ è enorme.

---

### **3. Procedura banale per trovare un generatore**

Una procedura immediata, ma inefficiente, per scegliere $g$ è la seguente:

1. si sceglie casualmente un elemento $g \in \mathbb{Z}_p^*$;
2. si calcolano tutte le potenze:

$$
g^1,g^2,\dots,g^{p-1} \pmod p
$$

3. si controlla se i risultati ottenuti sono tutti gli elementi di $\mathbb{Z}_p^*$;
4. se sì, $g$ è accettato come generatore;
5. se no, si sceglie un altro candidato e si ripete.

Questa procedura è corretta dal punto di vista concettuale, perché verifica direttamente la definizione di generatore. Il problema è che non è praticabile quando $p$ è grande.

Nel contesto crittografico, infatti, $p$ può essere un numero primo di 1024 bit, 2048 bit o più. In questi casi $\mathbb{Z}_p^*$ ha un numero astronomico di elementi, quindi calcolare tutte le potenze di ogni candidato generatore è impossibile in pratica.

> 💡 La procedura banale è utile per capire la definizione, ma non è l'algoritmo usato in pratica.

---

### **4. Il criterio efficiente richiede la fattorizzazione di $p-1$**

Per verificare efficientemente se un elemento $g$ è un generatore di $\mathbb{Z}_p^*$, si usa la fattorizzazione di $p-1$.

Poiché $\mathbb{Z}_p^*$ ha ordine $p-1$, scriviamo:

$$
p-1=q_1^{e_1}q_2^{e_2}\cdots q_k^{e_k}
$$

dove $q_1,\dots,q_k$ sono i fattori primi distinti di $p-1$.

Allora un elemento $g \in \mathbb{Z}_p^*$ è un generatore se e solo se:

$$
g^{(p-1)/q_i}\not\equiv 1 \pmod p
$$

per ogni fattore primo distinto $q_i$ di $p-1$.

Non è quindi necessario calcolare tutte le potenze da $g^1$ fino a $g^{p-1}$. È sufficiente controllare una potenza per ciascun fattore primo distinto di $p-1$.

#### **4.1. Perché il criterio funziona**

L'idea è collegata all'**ordine** di un elemento.

L'ordine di $g$ modulo $p$ è il più piccolo intero positivo $t$ tale che:

$$
g^t\equiv 1 \pmod p
$$

Dire che $g$ è generatore di $\mathbb{Z}_p^*$ significa dire che il suo ordine è:

$$
\operatorname{ord}_p(g)=p-1
$$

Se invece per qualche fattore primo $q_i$ vale:

$$
g^{(p-1)/q_i}\equiv 1 \pmod p
$$

allora l'ordine di $g$ divide $(p-1)/q_i$, quindi è strettamente minore di $p-1$. In quel caso $g$ non può generare tutto il gruppo.

Viceversa, se nessuno di questi controlli produce $1$, allora l'ordine di $g$ non può essere un divisore proprio di $p-1$ ottenuto eliminando uno dei fattori primi: l'ordine deve essere l'intero $p-1$, quindi $g$ è un generatore.

> 📌 Il punto chiave è che per controllare un generatore non serve enumerare il gruppo: basta conoscere i fattori primi di $p-1$.

---

### **5. Esempio con $p=11$: generatore valido**

Consideriamo:

$$
p=11
$$

Allora:

$$
p-1=10=2\cdot 5
$$

I fattori primi distinti di $p-1$ sono quindi $2$ e $5$.

Proviamo come candidato:

$$
g=2
$$

Per applicare il criterio bisogna controllare:

$$
2^{(11-1)/2}=2^5
$$

e:

$$
2^{(11-1)/5}=2^2
$$

Calcoliamo il primo valore:

$$
2^5=32
$$

Poiché:

$$
32\equiv 10 \pmod {11}
$$

abbiamo:

$$
2^5\not\equiv 1 \pmod {11}
$$

Calcoliamo il secondo valore:

$$
2^2=4
$$

e:

$$
4\not\equiv 1 \pmod {11}
$$

Entrambi i controlli hanno dato un valore diverso da $1$. Quindi:

$$
2
$$

è un generatore di $\mathbb{Z}_{11}^*$.

In altri termini, usando le potenze di $2$ modulo $11$ si riescono a ottenere tutti gli elementi non nulli modulo $11$.

---

### **6. Esempio con $p=11$: generatore non valido**

Usiamo lo stesso primo:

$$
p=11
$$

e quindi ancora:

$$
p-1=10=2\cdot 5
$$

Questa volta proviamo:

$$
g=3
$$

Il primo controllo richiesto dal criterio è:

$$
3^{(11-1)/2}=3^5
$$

Ora:

$$
3^5=243
$$

e:

$$
243\equiv 1 \pmod {11}
$$

Poiché uno dei controlli produce $1$, possiamo fermarci subito: $3$ non è un generatore di $\mathbb{Z}_{11}^*$.

Non serve controllare anche $3^2$, perché basta un solo fattore primo $q_i$ per cui:

$$
g^{(p-1)/q_i}\equiv 1 \pmod p
$$

per escludere che $g$ sia una radice primitiva.

> ✅ Nel caso $p=11$, il criterio permette di distinguere rapidamente $g=2$, che è generatore, da $g=3$, che non lo è.

---

### **7. Ordine degli elementi e tabella per $\mathbb{Z}_{19}^*$**

Il docente considera poi il gruppo:

$$
\mathbb{Z}_{19}^*
$$

e mostra una tabella con le potenze dei diversi elementi modulo $19$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Poiché $19$ è primo:

$$
\varphi(19)=18
$$

Infatti gli interi positivi minori di $19$ e primi con $19$ sono:

$$
1,2,\dots,18
$$

La tabella serve a evidenziare tre fatti importanti.

Primo: ogni sequenza di potenze termina con $1$. Questo accade perché in un gruppo finito ogni elemento ha ordine finito, quindi prima o poi una sua potenza ritorna all'identità.

Secondo: la lunghezza della sequenza generata da un elemento divide sempre l'ordine del gruppo. Nel caso di $\mathbb{Z}_{19}^*$, l'ordine del gruppo è $18$, quindi le lunghezze possibili devono dividere $18$.

Per esempio, se un elemento genera una sequenza lunga $9$, allora il suo ordine è $9$, e $9$ divide $18$. Se un elemento genera una sequenza lunga $6$, il suo ordine è $6$, e anche $6$ divide $18$.

Terzo: gli elementi la cui sequenza ha lunghezza $18$ generano tutto il gruppo. Questi elementi sono precisamente i **generatori** o **radici primitive** modulo $19$.

> 📌 In un gruppo finito, l'ordine di un elemento misura quanti elementi riesce a generare. Un generatore è un elemento il cui ordine coincide con l'ordine dell'intero gruppo.

---

### **8. Quando $\mathbb{Z}_n^*$ ammette un generatore**

Non tutti i gruppi del tipo $\mathbb{Z}_n^*$ hanno un generatore.

Il risultato richiamato nella lezione è il seguente: $\mathbb{Z}_n^*$ ammette una radice primitiva solo nei casi:

$$
n=2
$$

oppure:

$$
n=4
$$

oppure:

$$
n=r^k
$$

oppure:

$$
n=2r^k
$$

dove $r$ è un primo dispari e $k\ge 1$.

Nel caso che interessa Diffie-Hellman classico, però, si sceglie $n=p$ con $p$ primo. Quindi:

$$
\mathbb{Z}_p^*
$$

ha sempre almeno un generatore.

Questo è uno dei motivi per cui il protocollo viene presentato sul gruppo moltiplicativo modulo un primo: la struttura del gruppo è sufficientemente regolare e garantisce l'esistenza di radici primitive.

---

### **9. Numero di generatori**

Se un gruppo finito ciclico ha ordine $m$, il numero dei suoi generatori è:

$$
\varphi(m)
$$

dove $\varphi$ è la **funzione di Eulero**.

Nel caso:

$$
\mathbb{Z}_p^*
$$

l'ordine del gruppo è:

$$
\varphi(p)=p-1
$$

perché $p$ è primo.

Di conseguenza, il numero di generatori di $\mathbb{Z}_p^*$ è:

$$
\varphi(p-1)
$$

equivalentemente:

$$
\varphi(\varphi(p))
$$

Questo dato sarà importante per stimare la probabilità che un elemento scelto a caso sia un generatore.

> 💡 Se i generatori sono tanti rispetto agli elementi del gruppo, allora una procedura casuale trova rapidamente un generatore valido.

---

### **10. Procedura efficiente per scegliere $g$**

La procedura efficiente per scegliere il generatore usa come input:

- il primo $p$;
- la fattorizzazione di $p-1$:

$$
p-1=q_1^{e_1}q_2^{e_2}\cdots q_k^{e_k}
$$

La procedura è:

1. scegliere casualmente un elemento $g \in \mathbb{Z}_p^*$;
2. per ogni fattore primo distinto $q_i$ di $p-1$, calcolare:

$$
g^{(p-1)/q_i}\bmod p
$$

3. se tutti questi valori sono diversi da $1$, accettare $g$ come generatore;
4. se almeno uno di questi valori è uguale a $1$, scartare $g$ e ripetere.

Questa procedura è efficiente perché il numero di fattori primi distinti di $p-1$ è molto più piccolo di $p-1$. Per ogni candidato $g$ si fanno quindi pochi calcoli di esponenziazione modulare, invece di enumerare tutte le potenze.

> ⚠️ L'efficienza della procedura dipende dal fatto di conoscere la fattorizzazione di $p-1$. Se $p$ è scelto a caso, fattorizzare $p-1$ può diventare difficile.

---

### **11. Probabilità di successo della procedura**

Calcoliamo ora la probabilità che un elemento scelto casualmente in $\mathbb{Z}_p^*$ sia un generatore.

La probabilità è:

$$
\Pr[g \text{ generatore}]
=
\frac{\text{numero di generatori di } \mathbb{Z}_p^*}{\text{numero di elementi di } \mathbb{Z}_p^*}
$$

Il numero di elementi di $\mathbb{Z}_p^*$ è:

$$
\varphi(p)=p-1
$$

Il numero di generatori è:

$$
\varphi(p-1)
$$

Quindi:

$$
\Pr[g \text{ generatore}]
=
\frac{\varphi(p-1)}{p-1}
$$

Per stimare questa quantità si usa il limite richiamato nella lezione: per ogni intero $n\ge 5$,

$$
\varphi(n) \ge \frac{n}{6\ln\ln n}
$$

Applicandolo a:

$$
n=p-1
$$

si ottiene:

$$
\varphi(p-1)\ge \frac{p-1}{6\ln\ln(p-1)}
$$

Sostituendo nella probabilità:

$$
\Pr[g \text{ generatore}]
\ge
\frac{1}{6\ln\ln(p-1)}
$$

Questo significa che la probabilità non è trascurabile: scegliendo candidati casuali, dopo un numero ragionevole di tentativi ci si aspetta di trovare un generatore.

---

### **12. Numero medio di tentativi**

Se la probabilità di successo di un singolo tentativo è almeno:

$$
\frac{1}{6\ln\ln(p-1)}
$$

allora il numero medio di tentativi necessari è al più:

$$
6\ln\ln(p-1)
$$

Per dimensioni crittografiche tipiche, questo valore resta molto contenuto. Il docente riporta che, sostituendo valori di $p$ lunghi:

- 512 bit;
- 1024 bit;
- 2048 bit;

il numero medio di tentativi varia indicativamente da circa $35$ a circa $43$.

Quindi il problema non è provare tanti candidati $g$: quello è gestibile. Il punto delicato è scegliere $p$ in modo tale da conoscere la fattorizzazione di $p-1$.

> ✅ La ricerca casuale di $g$ è efficiente, purché la fattorizzazione di $p-1$ sia nota.

---

### **13. Come scegliere $p$ conoscendo la fattorizzazione di $p-1$**

Per evitare di scegliere un primo $p$ e poi dover fattorizzare $p-1$, si può costruire $p$ in modo che la fattorizzazione di $p-1$ sia nota fin dall'inizio.

La procedura descritta nella lezione è:

1. scegliere casualmente due numeri primi $p_1$ e $p_2$ della lunghezza desiderata;
2. calcolare:

$$
p=2p_1p_2+1
$$

3. applicare un test di primalità a $p$;
4. se $p$ non è primo, ripetere scegliendo nuovi $p_1$ e $p_2$;
5. se $p$ è primo, usare questo valore come modulo per Diffie-Hellman.

Questa costruzione è utile perché, se:

$$
p=2p_1p_2+1
$$

allora:

$$
p-1=2p_1p_2
$$

Quindi la fattorizzazione di $p-1$ è immediatamente nota:

$$
p-1=2^1p_1^1p_2^1
$$

I fattori primi distinti da usare nel test del generatore sono quindi:

$$
2,\ p_1,\ p_2
$$

> 📌 Invece di generare $p$ e poi cercare di fattorizzare $p-1$, si genera $p$ partendo da una fattorizzazione già controllata.

---

### **14. Scelta finale del generatore con $p=2p_1p_2+1$**

Una volta trovato un primo:

$$
p=2p_1p_2+1
$$

si sceglie un candidato casuale:

$$
g\in\mathbb{Z}_p^*
$$

e si applica il criterio efficiente.

Poiché i fattori primi distinti di $p-1$ sono:

$$
2,\ p_1,\ p_2
$$

bisogna verificare:

$$
g^{(p-1)/2}\not\equiv 1 \pmod p
$$

$$
g^{(p-1)/p_1}\not\equiv 1 \pmod p
$$

$$
g^{(p-1)/p_2}\not\equiv 1 \pmod p
$$

Se tutte e tre le condizioni sono soddisfatte, allora $g$ è un generatore di $\mathbb{Z}_p^*$ e la coppia:

$$
(p,g)
$$

può essere usata come insieme di parametri pubblici del protocollo Diffie-Hellman.

Se almeno una delle tre potenze produce $1$, il candidato $g$ viene scartato e se ne prova un altro.

---

### **15. Riassunto operativo**

La procedura completa per generare i parametri del protocollo può essere vista così:

1. scegliere due primi casuali $p_1$ e $p_2$;
2. calcolare $p=2p_1p_2+1$;
3. verificare con un test di primalità che $p$ sia primo;
4. se $p$ non è primo, ripetere;
5. se $p$ è primo, osservare che $p-1=2p_1p_2$;
6. scegliere casualmente un candidato $g\in\mathbb{Z}_p^*$;
7. verificare che:

$$
g^{(p-1)/q}\not\equiv 1 \pmod p
$$

per ogni:

$$
q\in\{2,p_1,p_2\}
$$

8. se i test passano, usare $g$ come generatore;
9. altrimenti scegliere un nuovo candidato $g$.

Questa procedura risolve contemporaneamente i due problemi:

- produce un primo $p$ adatto;
- permette di verificare efficientemente che $g$ sia un generatore.

---

### **16. Sintesi finale**

In questa lezione abbiamo approfondito la scelta dei parametri necessari per eseguire Diffie-Hellman.

I punti principali sono:

- Diffie-Hellman richiede un primo $p$ e un generatore $g$ di $\mathbb{Z}_p^*$;
- controllare direttamente tutte le potenze di $g$ è corretto ma inefficiente;
- il controllo efficiente usa la fattorizzazione di $p-1$;
- se $p-1=q_1^{e_1}\cdots q_k^{e_k}$, allora $g$ è generatore se:

$$
g^{(p-1)/q_i}\not\equiv 1 \pmod p
$$

per ogni fattore primo distinto $q_i$;

- per $p=11$, $g=2$ è generatore mentre $g=3$ non lo è;
- in $\mathbb{Z}_{19}^*$ gli ordini degli elementi dividono $\varphi(19)=18$, e gli elementi di ordine $18$ sono i generatori;
- $\mathbb{Z}_p^*$, con $p$ primo, ammette sempre generatori;
- il numero di generatori è $\varphi(p-1)$;
- la probabilità di trovare un generatore scegliendo a caso è almeno:

$$
\frac{1}{6\ln\ln(p-1)}
$$

- il numero medio di tentativi è quindi limitato da:

$$
6\ln\ln(p-1)
$$

- per rendere nota la fattorizzazione di $p-1$, si può generare $p$ nella forma:

$$
p=2p_1p_2+1
$$

con $p_1$ e $p_2$ primi e poi verificare che anche $p$ sia primo.

> ✅ La scelta dei parametri in Diffie-Hellman è efficiente se si costruisce $p$ in modo da conoscere la fattorizzazione di $p-1$ e poi si usa tale fattorizzazione per testare rapidamente i candidati generatori.

---

> 💡 **Prossimo passo per l'esame:** ora conosci il criterio teorico per i generatori. L'appello 230922 chiede di **dimostrare numericamente** che $g=10$ è radice primitiva di $\mathbb{Z}_{19}^*$ (~4 punti). Vai a [UD5 / L1 – Verifica numerica che g è radice primitiva di Z_p](../UD5_Approfondimenti_Esame/L1%20-%20Verifica%20numerica%20radice%20primitiva.md) per i due metodi svolti passo-passo (enumerazione completa delle potenze e criterio efficiente con fattorizzazione di p-1).
