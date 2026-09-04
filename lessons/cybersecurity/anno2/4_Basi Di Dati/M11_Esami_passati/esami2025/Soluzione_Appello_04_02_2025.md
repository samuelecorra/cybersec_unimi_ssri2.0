# **Basi di Dati - Soluzione completa dell'appello del 4 febbraio 2025**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2025_02_04.pdf)

---

### **1. Domanda 1 - Proprietà ACIDe e distribuzione**

**Riferimenti:** [M6/UD1/L1 - Transazioni](<../../M06_Gestione_Transazioni/UD1/L1 - Transazioni.md>) · [M7/UD3/L1 - Atomicità distribuita](<../../M07_Architetture_Distribuite/UD3/L1 - Ottimizzazione, concorrenza e atomicità.md>)

- **Atomicità:** tutti gli effetti oppure nessuno; log, `UNDO` e `REDO` ripristinano lo stato corretto.
- **Consistenza:** una transazione corretta porta uno stato valido in un altro stato valido.
- **Isolamento:** l'esito concorrente deve essere equivalente a un'esecuzione seriale.
- **Durabilità:** dopo il commit gli effetti sopravvivono ai guasti.

In distribuzione atomicità e isolamento diventano globali: il 2PC impedisce commit discordi fra nodi, mentre il controllo di concorrenza deve imporre un unico ordine globale. Consistenza e durabilità non cambiano definizione, ma vincoli inter-sito, repliche e recovery ne rendono più complessa l'attuazione.

---

### **2. Domanda 2 - Distributività degli operatori**

Per relazioni compatibili $r,s$ e predicato $F$:

$$
\sigma_F(r\cup s)=\sigma_F(r)\cup\sigma_F(s),
$$

$$
\sigma_F(r-s)=\sigma_F(r)-\sigma_F(s),
$$

$$
\pi_X(r\cup s)=\pi_X(r)\cup\pi_X(s).
$$

La proiezione **non** è distributiva rispetto alla differenza. Per esempio, con $r(A,B)=\{(1,a)\}$ e $s(A,B)=\{(1,b)\}$ si ha $\pi_A(r-s)=\{1\}$, ma $\pi_A(r)-\pi_A(s)=\varnothing$: la proiezione elimina l'attributo che distingueva le tuple.

---

### **3. Domanda 3 - Cardinalità relazionali**

Le chiavi sono `r.A`, `s.C` e `(t.A,t.C)`, con

$$
\pi_A(t)\subseteq\pi_A(r),\qquad \pi_C(t)\subseteq\pi_C(s).
$$

|Espressione|Minimo|Massimo|Motivazione|
|---|---:|---:|---|
|$\pi_A(r)\cap\pi_A(t)$|4|10|L'intersezione è $\pi_A(t)$. Ogni `A` si combina con al più 5 valori di `C`; 20 chiavi `(A,C)` richiedono almeno $20/5=4$ valori di `A`.| 
|$\sigma_{C=1}(s)$|0|1|`C` è chiave.| 
|$(r\bowtie s)-\pi_{A,C}(t)$|non applicabile|non applicabile|$r$ e $s$ non hanno attributi omonimi, quindi il primo operando ha schema `(A,B,C,D)`, incompatibile con `(A,C)`.| 

---

### **4. Esercizio 1 - Timestamp mono- e multiversione**

**Riferimento:** [M6/UD3/L6 - Timestamp](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>)

Nel monoversione una lettura è ammessa se $ts\geq WTM$; una scrittura se $ts\geq WTM$ e $ts\geq RTM$. Nel multiversione si sceglie la versione di massimo $WTM\leq ts$; la lettura è sempre ammessa e la scrittura è ammessa se $ts\geq RTM$ della versione scelta.

|Incognita|Valori possibili|Passaggio decisivo|
|---|---|---|
|$T_1$|$\boxed{12<T_1\leq15}$|`write(x,12)=NO` forza $T_1>12$; `write(x,15)=OK` forza $T_1\leq15$.|
|$T_2$|$\boxed{T_2<10}$|Dopo `write(y,10)`, la lettura è respinta esattamente sotto $WTM(y)=10$.|
|$T_3$|$\boxed{[2,5)\cup[10,+\infty)}$|Sotto 5 si usa $z_0$ con $RTM=2$; da 5 in poi si usa $z_5$ con $RTM=10$.|
|$T_4$|$\boxed{12<T_4<15}$|Solo una lettura della versione $t_8$ con timestamp maggiore di 12 fa respingere `write(t,12)`.| 

---

### **5. Esercizio 2 - Supporto e confidenza**

Con $N=100$, $|X|=100$, $|Y|=80$, $|Z|=40$, gli estremi di $|Y\cap Z|$ sono

$$
20=80+40-100\leq |Y\cap Z|\leq40.
$$

|Misura|Minimo|Massimo|
|---|---:|---:|
|$supporto(X\to Y)$|0,80|0,80|
|$supporto(Y\to Z)$|0,20|0,40|
|$confidenza(Y\to Z)$|0,25|0,50|
|$confidenza(Z\to Y)$|0,50|1|
|$confidenza(Z\to X)$|1|1|

`X` compare in tutte le transazioni, quindi contiene necessariamente `Y` e `Z`.

---

### **6. Esercizio 3 - SQL e algebra relazionale**

#### **6.1. Tipi di esame col prezzo medio massimo**

```sql
WITH Medie AS (
  SELECT Tipo, AVG(Prezzo) AS MediaPrezzo
  FROM ESAME
  GROUP BY Tipo
)
SELECT Tipo
FROM Medie
WHERE MediaPrezzo = (SELECT MAX(MediaPrezzo) FROM Medie);
```

#### **6.2. Esami pediatrici prenotati solo da nati dopo il 2000**

```sql
SELECT DISTINCT e.Nome, e.Prezzo
FROM ESAME AS e
WHERE e.Tipo = 'pediatrico'
  AND EXISTS (
    SELECT 1 FROM PRENOTAZIONE AS pr
    WHERE pr.CodEsame = e.Codice
  )
  AND NOT EXISTS (
    SELECT 1
    FROM PRENOTAZIONE AS pr
    JOIN PAZIENTE AS p ON p.Codice = pr.CodPaziente
    WHERE pr.CodEsame = e.Codice
      AND p.DataNascita <= DATE '2000-12-31'
  );
```

Il primo `EXISTS` evita di considerare vera per vacuità la proprietà per un esame mai prenotato.

#### **6.3. Prenotazioni più remote per esame**

Poste $P_1:=\rho_{E_1,C_1,D_1}(PRENOTAZIONE)$ e $P_2:=\rho_{E_2,C_2,D_2}(PRENOTAZIONE)$:

$$
Recenti:=\pi_{E_1,C_1}\left(\sigma_{E_1=E_2\land D_1>D_2}(P_1\times P_2)\right),
$$

$$
\boxed{\rho_{E_1,C_1}(\pi_{CodEsame,CodPaziente}(PRENOTAZIONE))-Recenti}.
$$

Sono conservati tutti i pazienti a pari data minima.

---

### **7. Esercizio 4 - Schema E-R MobilBagno**

**Riferimenti:** [modello E-R](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L1 - Modello E-R - Entità, relazioni e attributi.md>) · [identificatori e gerarchie](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L3 - Modello E-R - Identificatori e gerarchie.md>)

#### **7.1. Entità**

|Entità|Identificatore|Attributi|
|---|---|---|
|MOBILE|`(NomeCommerciale, Linea)`|Stile, Dimensioni composte, PrezzoListino, Colore $(1,N)$|
|COMPOSIZIONE|IdComposizione|LunghezzaTotale, PrezzoComplessivo|
|CLIENTE|CodiceFiscale|Nome, Cognome, Telefono|
|LAVABO|IdLavabo|Forma, Materiale, Prezzo, Dimensioni composte|
|TOP|IdTop|Materiale, PrezzoMq, Colore $(1,N)$|
|RUBINETTO|IdRubinetto|Materiale, Colore, Tipologia, PrezzoListino|

Gli identificatori tecnici di composizione, lavabo, top e rubinetto sono necessari perché la traccia non fornisce attributi naturali sicuramente univoci.

`LAVABO` ha una gerarchia totale ed esclusiva: `CLASSICO(AltezzaDaTerra)` e `INCASSO`.

#### **7.2. Associazioni**

|Associazione|Cardinalità|Attributi|
|---|---|---|
|COMPONE|COMPOSIZIONE $(1,N)$ - MOBILE $(0,N)$|Quantità positiva|
|PRENOTA|CLIENTE $(0,N)$ - COMPOSIZIONE $(0,N)$|—|
|ABBINABILE|INCASSO $(0,N)$ - MOBILE $(0,N)$|—|
|COLLOCABILE|INCASSO $(0,N)$ - TOP $(1,N)$|—|
|MONTA|LAVABO $(1,1)$ - RUBINETTO $(0,N)$|—|

`COMPONE` è molti-a-molti perché una composizione usa più modelli e un modello può ricorrere in più composizioni. La quantità è proprietà della coppia. Ogni top deve avere almeno un lavabo compatibile, mentre un lavabo a incasso può non avere top. Ogni lavabo monta esattamente un rubinetto; lo stesso modello di rubinetto può servire più lavabi.

> ✅ La soluzione conserva tutte le molteplicità espresse dalla traccia senza dedurre identificatori naturali non garantiti.
