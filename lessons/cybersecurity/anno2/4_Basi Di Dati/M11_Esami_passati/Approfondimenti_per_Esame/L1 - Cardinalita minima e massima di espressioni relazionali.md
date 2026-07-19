# **M11 Approfondimenti per l'esame - Lezione 1 - Cardinalità minima e massima di espressioni relazionali**

### **1. Obiettivo**

Negli esercizi d'esame non basta applicare una formula generale: occorre combinare cardinalità, chiavi, vincoli di integrità referenziale, tuple sicuramente presenti e semantica insiemistica o multinsieme dell'operatore.

---

### **2. Procedura universale**

Per ogni espressione:

1. controllare che gli schemi siano compatibili con l'operatore;
2. annotare chiavi e cardinalità delle relazioni;
3. tradurre ogni foreign key in un'inclusione fra proiezioni;
4. stabilire se il risultato elimina duplicati (`π`, `UNION`) oppure li conserva (`UNION ALL`);
5. ricavare separatamente un limite inferiore e uno superiore;
6. costruire mentalmente un'istanza che raggiunga ciascun estremo, per dimostrare che il limite è effettivamente assumibile.

> ⚠️ Un intervallo numericamente plausibile non è una risposta completa se non si dimostra che entrambi gli estremi sono compatibili con tutti i vincoli.

---

### **3. Vincoli come inclusioni**

Se `S.A` è foreign key verso la chiave `R.A`, allora:

$$
\pi_A(S) \subseteq \pi_A(R).
$$

Da questa sola informazione seguono subito:

$$
\pi_A(S)-\pi_A(R)=\varnothing
$$

e

$$
0 \leq \left|\pi_A(R)-\pi_A(S)\right| \leq |R|-\left|\pi_A(S)\right|.
$$

Per ottenere il massimo della differenza occorre quindi minimizzare il numero di valori distinti di `S.A`.

---

### **4. Come una chiave composta forza valori distinti**

Supponiamo:

- $|S|=20$;
- la chiave di $S$ sia $(A,C)$;
- `S.C` sia foreign key verso una relazione con esattamente 5 valori distinti di `C`.

Per un valore fissato di $A$ possono esistere al massimo 5 tuple distinte, una per ciascun valore di $C$. Di conseguenza:

$$
|\pi_A(S)| \geq \left\lceil\frac{|S|}{|\pi_C(S)|_{\max}}\right\rceil
=\left\lceil\frac{20}{5}\right\rceil=4.
$$

Se inoltre `S.A` riferisce una chiave `R.A` con $|R|=10$, allora:

$$
4\leq |\pi_A(S)|\leq 10.
$$

Pertanto:

$$
0\leq |\pi_A(R)-\pi_A(S)|\leq 10-4=6.
$$

Gli estremi sono raggiungibili:

- minimo 0: tutti i 10 valori di `R.A` compaiono in `S.A`;
- massimo 6: `S` usa esattamente 4 valori di `A`, ciascuno combinato con tutti i 5 valori di `C`.

---

### **5. Join naturale con foreign key**

Se `R.A` è chiave e `S.A` è foreign key non nulla verso `R.A`, ogni tupla di $S$ trova **esattamente una** tupla di $R$. Quindi:

$$
|R\bowtie S|=|S|.
$$

Senza il vincolo referenziale il limite generale sarebbe molto più ampio:

$$
0\leq |R\bowtie S|\leq |R|\,|S|,
$$

da restringere ulteriormente in presenza di chiavi.

---

### **6. UNION e UNION ALL**

`UNION` applica la semantica insiemistica ed elimina i duplicati. Per due risultati compatibili $A$ e $B$:

$$
\max(|A|,|B|)\leq |A\cup B|\leq |A|+|B|.
$$

Il minimo si ottiene quando l'insieme più piccolo è contenuto nel più grande; il massimo quando i due insiemi sono disgiunti.

`UNION ALL` conserva invece tutte le occorrenze:

$$
|A\ \texttt{UNION ALL}\ B|=|A|+|B|.
$$

> 📌 Prima di contare, verificare la compatibilità: stesso numero di colonne e domini corrispondenti compatibili. I nomi degli attributi non devono necessariamente coincidere in SQL.

---

### **7. Tuple sicuramente presenti**

Le tuple dichiarate presenti nella traccia impongono vincoli di sovrapposizione. Se $R$ contiene `(a,b)` e $T$ contiene una tupla il cui primo campo è `a`, le proiezioni sui rispettivi primi attributi hanno almeno `a` in comune. Questo può abbassare il massimo di una `UNION` di almeno una unità rispetto al caso disgiunto.

La tuple dichiarata va usata soltanto quando i valori sono confrontati nella stessa posizione e su domini compatibili: valori uguali che compaiono in attributi non confrontati non influenzano l'operazione.

---

### **8. Checklist da consegna**

Per ciascuna riga della tabella d'esame scrivere:

- applicabile / non applicabile;
- minimo e motivazione;
- massimo e motivazione;
- vincolo decisivo usato;
- breve istanza-test che mostra la raggiungibilità degli estremi.

> ✅ La cardinalità è un problema di vincoli, non di sola aritmetica: ogni estremo deve rispettare contemporaneamente chiavi, riferimenti e semantica dell'operatore.
