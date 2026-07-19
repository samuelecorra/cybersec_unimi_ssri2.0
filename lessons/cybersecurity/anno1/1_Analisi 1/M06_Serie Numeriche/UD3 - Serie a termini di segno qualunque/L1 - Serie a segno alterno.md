# **M6 UD3 Lezione 1 - Serie alternate e criterio di Leibniz**

### **1. Definizione e motivazione**

Una serie alternata ha, dopo eventuali termini iniziali, la forma

$$
\sum_{n=0}^{\infty}(-1)^n b_n
$$

oppure la forma con segno opposto, con $b_n\geq0$. Le cancellazioni possono produrre convergenza anche quando $\sum b_n$ diverge.

L'esempio fondamentale è

$$
\sum_{n=1}^{\infty}\frac{(-1)^{n-1}}n.
$$

### **2. Criterio di Leibniz**

> **Teorema.** Sia $b_n\geq0$, decrescente definitivamente e tale che $b_n\to0$. Allora

$$
\sum_{n=0}^{\infty}(-1)^n b_n
$$

converge.

Poiché modifiche finite non cambiano la natura, nella prova possiamo supporre la monotonia da $n=0$.

### **3. Dimostrazione mediante somme pari e dispari**

Le somme parziali pari soddisfano

$$
S_{2m+2}-S_{2m}=-b_{2m+1}+b_{2m+2}\leq0,
$$

quindi $(S_{2m})$ è decrescente. Le somme parziali dispari soddisfano

$$
S_{2m+3}-S_{2m+1}=b_{2m+2}-b_{2m+3}\geq0,
$$

quindi $(S_{2m+1})$ è crescente.

Inoltre

$$
S_{2m+1}\leq S_{2m}
$$

e le due successioni sono reciprocamente limitate. Per il teorema di convergenza monotona esistono limiti $L_-$ e $L_+$. Poiché

$$
S_{2m}-S_{2m+1}=b_{2m+1}\to0,
$$

si ha $L_+=L_-=:S$. Ogni somma parziale è pari o dispari, dunque $S_N\to S$. $\square$

<!-- TODO FIGURA: rappresentare S_0,S_2,S_4 come successione decrescente e S_1,S_3,S_5 come successione crescente, entrambe verso S; collegare coppie consecutive con ampiezza b_{N+1}. Obiettivo didattico: visualizzare tutti i passaggi della prova di Leibniz. -->

### **4. Stima e segno del resto**

Il limite $S$ è compreso tra due somme parziali consecutive. Pertanto

$$
|R_N|=|S-S_N|\leq|S_{N+1}-S_N|=b_{N+1}.
$$

Inoltre il resto ha il segno del primo termine trascurato:

$$
(-1)^{N+1}R_N\geq0
$$

per la convenzione $\sum(-1)^n b_n$. Le approssimazioni pari e dispari stanno da lati opposti della somma.

<!-- TODO FIGURA: intervallo con S_N, S, S_{N+1}, freccia del resto R_N e lunghezza massima b_{N+1}; mostrare separatamente N pari e dispari. Obiettivo didattico: distinguere segno del resto, stima superiore e alternanza delle approssimazioni. -->

### **5. Precisione assegnata**

Per garantire

$$
|R_N|<\varepsilon
$$

basta scegliere $N$ tale che $b_{N+1}<\varepsilon$. Per l'armonica alternata, la garanzia $10^{-4}$ richiede $N+1>10^4$.

La stima è affidabile ma può essere conservativa; non afferma in generale $|R_N|=b_{N+1}$.

### **6. Necessità delle ipotesi**

- Se $b_n\not\to0$, la serie fallisce il test del termine generale: $\sum(-1)^n$ diverge.
- Senza monotonia, $b_n\to0$ non basta. Ponendo per $k\geq1$

$$
b_{2k}=\frac1{\sqrt k}+\frac1k,
\qquad
b_{2k+1}=\frac1{\sqrt k},
$$

si ha $b_n\to0$, ma ogni coppia contribuisce $1/k$ e le somme parziali divergono.
- La non negatività identifica $b_n$ come ampiezza. Se i segni sono già incorporati in $b_n$, la struttura alternata va ricostruita prima di applicare il criterio.

### **7. Convergenza assoluta o condizionata**

Leibniz garantisce convergenza ordinaria, non assoluta. Dopo averlo applicato bisogna studiare separatamente

$$
\sum b_n.
$$

Per esempio, $\sum(-1)^{n-1}/n$ converge per Leibniz ma non assolutamente, perché la serie armonica diverge. È dunque condizionatamente convergente.

### **8. Errori frequenti**

1. Verificare soltanto l'alternanza dei segni.
2. Omettere il limite $b_n\to0$.
3. Controllare la monotonia su pochi termini e non definitivamente.
4. Concludere convergenza assoluta da Leibniz.
5. Usare $b_N$ invece del primo termine trascurato $b_{N+1}$.
6. Trattare la stima del resto come uguaglianza.
7. Dimenticare che il segno del resto dipende dalla convenzione iniziale.

### **9. Esercizi**

1. Ricostruire la prova completa con somme parziali pari e dispari.
2. Studiare $\sum(-1)^n/(n+1)^p$ al variare di $p>0$.
3. Stabilire la natura di $\sum(-1)^n n/(n+1)$.
4. Verificare in dettaglio il controesempio non monotono della sezione 6.
5. Determinare quanti termini garantiscono errore inferiore a $10^{-5}$ per $\sum(-1)^n/(n+1)$.
6. Per una serie alternata ammessa, dimostrare il segno del resto.
7. Costruire un esempio in cui $b_n$ diventi decrescente soltanto dopo dieci indici.
8. Confrontare la velocità garantita di una serie alternata con quella di una geometrica.
9. Correggere l'affermazione: «ogni serie con segni alterni converge».
10. Formulare Leibniz per indice iniziale generico $n_0$ senza errori di parità.

### **10. Riepilogo**

> ✅ Leibniz usa tutte e tre le ipotesi: ampiezze non negative, monotonia definitiva e limite nullo. La prova costruisce due successioni monotone di somme parziali e fornisce anche segno e stima del resto.
