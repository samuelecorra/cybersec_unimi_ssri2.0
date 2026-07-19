# **Basi di Dati - Soluzione completa dell'appello dell'8 maggio 2026**

**Docente:** Pierangela Samarati · **Prova:** seconda prova in itinere · **Tempo:** 2 ore

Fonti originali: [pagina 1](esame_8_maggio_2026_1.png) · [pagina 2](esame_8_maggio_2026_2.png)

> ⚠️ La pagina 2 rinvia a una tabella allegata per l'Esercizio 4, ma tale tabella non è presente nei file disponibili. Inoltre lo schedule stampato dell'Esercizio 3 è coperto in più punti da annotazioni manoscritte. Queste due parti sono quindi risolte fino al massimo consentito dalla fonte, senza inventare operazioni mancanti.

---

### **1. Domanda 1 - Proprietà ACID e distribuzione**

#### **1.1. Traccia originale**

> Elencare e descrivere in modo completo le proprietà ACIDe delle transazioni. Indicare quali di queste proprietà cambiano a seguito della distribuzione della base di dati, fornendo la motivazione e un esempio adeguato.

**Riferimenti di teoria:** [M6/UD1/L1 - Transazioni](<../../M06_Gestione_Transazioni/UD1/L1 - Transazioni.md>) · [M7/UD3/L1 - Ottimizzazione, concorrenza e atomicità](<../../M07_Architetture_Distribuite/UD3/L1 - Ottimizzazione, concorrenza e atomicità.md>)

#### **1.2. Soluzione**

Una transazione è un'unità logica di lavoro delimitata da `begin transaction` e da una decisione finale di `commit` oppure `rollback`. Le quattro proprietà sono:

- **Atomicità:** la transazione è indivisibile: o tutti i suoi effetti diventano permanenti, oppure nessuno. Prima del commit un guasto richiede l'`UNDO`; dopo il commit il sistema deve poter eseguire il `REDO` degli effetti non ancora stabilizzati.
- **Consistenza:** se lo stato iniziale soddisfa i vincoli di integrità e la transazione è corretta, anche lo stato finale li soddisfa. I controlli possono essere immediati o differiti al termine della transazione.
- **Isolamento:** l'effetto di transazioni concorrenti deve essere equivalente a quello di un'esecuzione seriale. Ogni transazione deve essere protetta dagli stati intermedi prodotti dalle altre.
- **Durabilità o persistenza:** dopo il commit gli effetti non devono andare persi, neppure in caso di crash; log, memoria stabile e recovery rendono ripetibili le scritture confermate.

Con la distribuzione **cambiano atomicità e isolamento**.

L'atomicità non è più una decisione locale. Se un trasferimento di 100 euro addebita un conto sul nodo $N_1$ e accredita un conto sul nodo $N_2$, non è ammissibile che $N_1$ committi mentre $N_2$ abortisce. Serve un protocollo di commit distribuito, tipicamente il **Two-Phase Commit**, che obblighi tutti i partecipanti alla stessa decisione globale anche in presenza di perdita di messaggi o caduta di un nodo.

L'isolamento diventa globale. Due schedule possono essere serializzabili su ciascun nodo e tuttavia imporre ordini opposti fra le stesse transazioni: $T_1<T_2$ sul nodo $N_1$ e $T_2<T_1$ sul nodo $N_2$. Il grafo globale contiene allora un ciclo. Occorrono 2PL stretto coordinato con commit atomico oppure timestamp globali coerenti.

Nel modello del corso, **consistenza e persistenza non cambiano concettualmente**:

- i vincoli continuano a descrivere gli stati ammessi, anche se i vincoli globali fra nodi sono tecnologicamente più difficili da controllare;
- ogni nodo continua a garantire localmente la durabilità mediante log, checkpoint e recovery.

> 📌 La distribuzione non ridefinisce ACID: rende globali i meccanismi necessari per atomicità e isolamento.

---

### **2. Domanda 2 - Prevenzione dei deadlock basata sui timestamp**

#### **2.1. Traccia originale**

> Si consideri la tecnica di prevenzione dei deadlock basata sui timestamp.
>
> 1. Dire quale transazione viene uccisa nel caso preemptive e non preemptive, indicando anche il controllo fatto per decidere se uccidere o mettere in attesa una risorsa.
> 2. Quale timestamp viene assegnato alla successiva attivazione della transazione abortita? Perché?

**Riferimento di teoria:** [M6/UD3/L8 - Deadlock](<../../M06_Gestione_Transazioni/UD3/L8 - Deadlock.md>)

#### **2.2. Soluzione**

Sia $T_i$ la transazione che richiede una risorsa posseduta da $T_j$ e sia un timestamp minore indice di una transazione più anziana.

**Caso non preemptive - wait-die.** Non si interrompe il possessore:

- se $TS(T_i)<TS(T_j)$, la richiedente è più anziana e **attende**;
- se $TS(T_i)>TS(T_j)$, la richiedente è più giovane e viene **abortita**.

**Caso preemptive - wound-wait.** La richiedente anziana può interrompere il possessore:

- se $TS(T_i)<TS(T_j)$, $T_i$ è più anziana e **uccide $T_j$**;
- se $TS(T_i)>TS(T_j)$, $T_i$ è più giovane e **attende**.

In entrambi i casi l'attesa può orientarsi soltanto in una direzione rispetto all'età delle transazioni; un ciclo è quindi impossibile.

La transazione abortita viene riavviata con **lo stesso timestamp**. Se ricevesse ogni volta un timestamp nuovo, diventerebbe sempre più giovane e potrebbe essere uccisa indefinitamente. Conservare il timestamp originario le fa acquisire priorità relativa e previene la starvation.

---

### **3. Domanda 3 - Proprietà delle regole attive**

#### **3.1. Traccia originale**

> 1. Nell'ambito delle basi di dati attive, illustrare i concetti di terminazione, confluenza e determinismo delle osservazioni.
> 2. Dire se il seguente insieme di trigger è terminante oppure no, motivando opportunamente la risposta.

```sql
CREATE TRIGGER CalcolaSaldi
AFTER UPDATE OF Prezzo ON Prodotto
REFERENCING new_table AS NuovoPrezzo
BEGIN
  UPDATE Prodotto
  SET Sconto = Prezzo * 0.20
  WHERE IdProdotto IN (
    SELECT IdProdotto
    FROM NuovoPrezzo);
END;
```

```sql
CREATE TRIGGER VerificaSconto
AFTER UPDATE OF Sconto ON Prodotto
FOR EACH ROW
WHEN (10 > new.Sconto)
BEGIN
  UPDATE Prodotto
  SET Prezzo = 0.90 * Prezzo
  WHERE IDProdotto = new.IdProdotto;
END;
```

**Riferimento di teoria:** [M9/UD1/L3 - Proprietà delle regole attive](<../../M09_Basi_di_Dati_Attive/UD1/L3 - Proprietà delle regole attive.md>)

#### **3.2. Definizioni**

- **Terminazione:** per ogni stato iniziale e sequenza di modifiche, l'elaborazione delle regole produce un numero finito di attivazioni e raggiunge uno stato stabile.
- **Confluenza:** a parità di stato iniziale e modifiche esterne, tutti gli ordini leciti di esecuzione delle regole terminano nello stesso stato finale.
- **Determinismo delle osservazioni:** non soltanto lo stato finale, ma anche le osservazioni visibili durante l'esecuzione sono indipendenti dall'ordine scelto. È una garanzia più forte della confluenza.

#### **3.3. Analisi dei trigger**

Il grafo di attivazione contiene:

$$
\texttt{CalcolaSaldi}\to\texttt{VerificaSconto}
$$

perché il primo aggiorna `Sconto`, e

$$
\texttt{VerificaSconto}\to\texttt{CalcolaSaldi}
$$

perché il secondo aggiorna `Prezzo`. Esiste dunque un ciclo.

Il ciclo è realmente percorribile. Se, dopo `CalcolaSaldi`, il nuovo sconto è minore di 10, `VerificaSconto` moltiplica il prezzo per $0{,}90$. Il nuovo aggiornamento del prezzo ricalcola:

$$
Sconto=0{,}20\cdot Prezzo.
$$

Il prezzo positivo diminuisce geometricamente e lo sconto resta inferiore a 10; la condizione continua quindi a essere vera. In aritmetica esatta si ottiene una catena infinita.

L'insieme **non è terminante**, perché la terminazione deve valere per ogni stato iniziale: basta un prodotto per cui la condizione `10 > new.Sconto` diventi vera per produrre il ciclo. Se lo sconto è almeno 10, quella specifica attivazione termina, ma ciò non salva la proprietà universale dell'insieme.

---

### **4. Esercizio 1 - Checkpoint e ripresa a caldo**

#### **4.1. Traccia originale**

```text
DUMP, B(T1), B(T2), U(T1,O1,B1,A1), I(T2,O2,A2), B(T3),
D(T1,O3,B3), A(T1), B(T4), CK(...), U(T3,O4,B4,A4),
D(T4,O5,B5), I(T3,O6,A6), B(T5), I(T5,O7,A7), C(T3),
B(T6), D(T6,O8,B8), C(T4), A(T2), U(T5,O9,B9,A9), GUASTO
```

> 1. Scrivere, in corrispondenza di ogni record di checkpoint, le transazioni attive.
> 2. Illustrare dettagliatamente i passi da compiere per effettuare la ripresa a caldo.

**Riferimento di teoria:** [M6/UD2/L3 - Gestione dei guasti](<../../M06_Gestione_Transazioni/UD2/L3 - Gestione dei guasti.md>)

#### **4.2. Transazioni attive al checkpoint**

Prima di `CK`:

- $T_1$ è iniziata ma ha già eseguito `A(T1)`: non è più attiva;
- $T_2$ e $T_3$ sono iniziate e non hanno ancora terminato;
- $T_4$ è appena iniziata.

Il record corretto è quindi:

```text
CK(T2,T3,T4)
```

#### **4.3. Costruzione di UNDO e REDO**

Si inizializza al checkpoint:

$$
UNDO=\{T_2,T_3,T_4\},\qquad REDO=\varnothing.
$$

Scorrendo in avanti:

|Record rilevante|UNDO|REDO|
|---|---|---|
|`B(T5)`|$\{T_2,T_3,T_4,T_5\}$|$\varnothing$|
|`C(T3)`|$\{T_2,T_4,T_5\}$|$\{T_3\}$|
|`B(T6)`|$\{T_2,T_4,T_5,T_6\}$|$\{T_3\}$|
|`C(T4)`|$\{T_2,T_5,T_6\}$|$\{T_3,T_4\}$|
|`A(T2)`|$\{T_5,T_6\}$|$\{T_3,T_4\}$|

Al guasto:

$$
\boxed{UNDO=\{T_5,T_6\}},\qquad
\boxed{REDO=\{T_3,T_4\}}.
$$

$T_2$ non va in UNDO di recovery perché il suo abort è già concluso e registrato.

#### **4.4. UNDO all'indietro**

|Record|Azione di compensazione|
|---|---|
|`U(T5,O9,B9,A9)`|ripristinare $O_9:=B_9$|
|`D(T6,O8,B8)`|reinserire $O_8:=B_8$|
|`I(T5,O7,A7)`|eliminare $O_7$|

#### **4.5. REDO in avanti**

|Record|Azione da ripetere|
|---|---|
|`U(T3,O4,B4,A4)`|porre $O_4:=A_4$|
|`D(T4,O5,B5)`|eliminare $O_5$|
|`I(T3,O6,A6)`|inserire $O_6:=A_6$|

Il sistema torna così a uno stato che include tutti e soli gli effetti delle transazioni committate.

---

### **5. Esercizio 2 - VSR e CSR**

#### **5.1. Traccia originale**

```text
r1(x) r4(y) w1(z) r4(z) w2(y) r3(y) w1(x) w2(x) w3(z) w3(x)
```

> Dire se lo schedule è VSR e/o CSR, indicando tutti gli schedule seriali equivalenti e illustrando il ragionamento.

#### **5.2. View-serializzabilità**

Relazioni di lettura:

- $r_1(x)$ legge il valore iniziale: $T_1$ deve precedere $T_2$ e $T_3$, che scrivono $x$;
- $r_4(y)$ legge il valore iniziale: $T_4$ deve precedere $T_2$;
- $r_4(z)$ legge da $w_1(z)$: $T_1<T_4$ e $T_3$ non può interporsi;
- $r_3(y)$ legge da $w_2(y)$: $T_2<T_3$.

Le scritture finali sono di $T_2$ su $y$ e di $T_3$ su $z$ e $x$. L'unico ordine seriale compatibile è:

$$
\boxed{T_1\to T_4\to T_2\to T_3}.
$$

Lo schedule è quindi VSR.

#### **5.3. Conflict-serializzabilità**

Gli archi distinti del grafo dei conflitti sono:

$$
T_1\to T_4,\quad T_1\to T_2,\quad T_1\to T_3,
$$

$$
T_4\to T_2,\quad T_4\to T_3,\quad T_2\to T_3.
$$

Il grafo è aciclico e ha un solo ordinamento topologico:

$$
\boxed{T_1,T_4,T_2,T_3}.
$$

Pertanto:

$$
\boxed{S\in CSR\subseteq VSR}.
$$

---

### **6. Esercizio 3 - Verifica 2PL**

#### **6.1. Limite della fonte**

La richiesta è leggibile: stabilire se lo schedule possa essere generato da uno scheduler 2PL base e, in caso positivo, indicare richieste di lock/unlock con lock a due stati. Tuttavia varie operazioni dello schedule sono materialmente coperte dalle annotazioni presenti nella fotografia; una trascrizione precisa non è recuperabile.

#### **6.2. Metodo completo di soluzione**

Per ciascuna transazione si inserisce `lock_i(o)` prima del primo accesso all'oggetto $o$. Il lock è esclusivo, perché il modello richiesto è a due stati. Si cerca poi un punto di rilascio che rispetti entrambe le condizioni:

1. nessuna transazione accede a un oggetto senza possederne il lock;
2. dopo il primo `unlock_i`, $T_i$ non richiede più alcun nuovo lock.

Se una transazione deve rilasciare un lock per consentire un'operazione già visibile di un'altra transazione e, successivamente, deve acquisire un lock su un oggetto nuovo, lo schedule **non è 2PL**. Se si riesce invece a collocare per ogni transazione un unico punto di lock, lo schedule è generabile.

> ⚠️ L'esito sì/no dipende dall'ordine esatto delle operazioni coperte: non è matematicamente corretto dedurlo dai soli frammenti visibili.

---

### **7. Esercizio 4 - Timestamp multiversione**

#### **7.1. Dati leggibili**

La traccia inizializza un oggetto $x$ con:

$$
RTM(x_2)=5,\qquad WTM(x_2)=2.
$$

Chiede di completare una tabella allegata indicando accettazione, versione usata o creata, nuovi timestamp ed eventuale transazione uccisa, e poi di confrontare il risultato con il metodo monoversione. La sequenza di richieste contenuta nella tabella allegata non è presente.

#### **7.2. Regole per completare la tabella**

Per `read(x,t)`, si sceglie la versione $x_k$ con massimo $WTM(x_k)\leq t$; la lettura è sempre accettata e:

$$
RTM(x_k):=\max(RTM(x_k),t).
$$

Per `write(x,t)`, si sceglie la stessa versione candidata $x_j$. Se $t<RTM(x_j)$, la scrittura è rifiutata e $T_t$ viene uccisa; altrimenti nasce una nuova versione $x_t$ con:

$$
RTM(x_t)=WTM(x_t)=t.
$$

Nel metodo monoversione:

- `read(x,t)` uccide $T_t$ se $t<WTM(x)$;
- `write(x,t)` uccide $T_t$ se $t<RTM(x)$ oppure $t<WTM(x)$.

Senza l'ordine delle richieste non è determinabile quali transazioni vengano uccise, perché ogni operazione modifica lo stato usato dalla successiva.

---

### **8. Esercizio 5 - Supporto e confidenza**

#### **8.1. Traccia originale**

Siano $N_T=100$ transazioni e tre insiemi di oggetti $X,Y,Z$ tali che:

$$
supporto(X\to Y)=0{,}3,
$$

$$
confidenza(X\to Y)=1,
$$

$$
confidenza(X\to Z)=0{,}5.
$$

Determinare i valori minimi e massimi di $supporto(X\to Z)$ e $supporto(Y\to Z)$.

**Riferimenti:** [M10/UD1/L5 - Data Mining](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>) · [approfondimento sui limiti](<../Approfondimenti_per_Esame/L3 - Limiti per supporto e confidenza nelle regole di associazione.md>)

#### **8.2. Soluzione**

Il supporto dato implica $|X\cap Y|=30$. La confidenza unitaria implica $X\subseteq Y$, quindi $|X|=30$.

Da $confidenza(X\to Z)=0{,}5$:

$$
|X\cap Z|=0{,}5\cdot 30=15.
$$

Pertanto:

$$
\boxed{\min supporto(X\to Z)=\max supporto(X\to Z)=0{,}15}.
$$

Poiché $X\subseteq Y$, i 15 elementi di $X\cap Z$ appartengono a $Y\cap Z$, quindi il minimo è $0{,}15$. D'altra parte i 15 elementi di $X\setminus Z$ appartengono necessariamente a $Y\setminus Z$; al massimo 85 transazioni possono quindi stare in $Y\cap Z$. Entrambi gli estremi sono raggiungibili:

$$
\boxed{0{,}15\leq supporto(Y\to Z)\leq 0{,}85}.
$$

---

### **9. Checklist finale dell'appello**

- Domande teoriche 1–3: risolte integralmente.
- Esercizi 1, 2 e 5: risolti integralmente con risultati verificati.
- Esercizio 3: metodo completo; esito non determinabile per copertura manoscritta dello schedule.
- Esercizio 4: metodo completo; esiti riga per riga non determinabili per assenza della tabella allegata.
