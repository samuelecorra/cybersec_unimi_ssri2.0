# Soluzione dell'esercitazione — 31 ottobre 2025

Esercitazione di **Algoritmi e Strutture Dati** sugli **alberi rosso-neri**: costruzione incrementale di un albero mediante quattordici inserimenti con tutti i casi di ripristino, e schema completo dei quattro casi della cancellazione.

> 📌 La teoria completa degli alberi rosso-neri — proprietà, dimostrazione del limite sull'altezza, rotazioni, casi di inserimento e cancellazione, corrispondenza con gli alberi 2-3-4 — si trova in [M05/UD3 — Alberi rosso-neri](../M05_DS_Orizzontali/UD3/L2_Alberi_rosso_neri.md). Questa soluzione richiama comunque le proprietà e i casi prima di applicarli, così da restare leggibile per conto proprio.

> **Riferimenti di teoria:** [M05/UD3 — Alberi rosso-neri](../M05_DS_Orizzontali/UD3/L2_Alberi_rosso_neri.md), [M05/UD3 — Alberi bilanciati di ricerca](../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md), [M03/UD1 — Alberi binari](../M03_DS_Alberi/UD1/L5_Alberi_binari.md), [M03/UD1 — Alberi binari e complessità](../M03_DS_Alberi/UD1/L7_Alberi_binari_e_complessita.md).

---

### **1. Richiamo: proprietà degli alberi rosso-neri**

Un albero rosso-nero è un albero binario di ricerca in cui ogni nodo porta un attributo aggiuntivo di **colore** e valgono cinque proprietà:

1. ogni nodo è rosso o nero;
2. la **radice** è nera;
3. ogni foglia (i nodi `NIL` sentinella) è nera;
4. se un nodo è rosso, **entrambi i suoi figli sono neri** — non esistono due rossi consecutivi;
5. per ogni nodo, tutti i cammini che scendono da esso fino alle foglie contengono lo **stesso numero di nodi neri**. Tale numero si chiama **altezza nera** $bh(x)$.

Da queste proprietà segue il risultato che rende utili questi alberi: un albero rosso-nero con $n$ nodi interni ha altezza al più $2\log_2(n+1)$, quindi ricerca, inserimento e cancellazione costano $O(\log n)$ nel **caso peggiore**.

> 💡 L'intuizione della proprietà 5 combinata con la 4: su un cammino radice-foglia i rossi non possono essere adiacenti, quindi al più metà dei nodi è rossa. Se il "sottoscheletro nero" ha altezza $h_b$, l'albero intero non può superare $2h_b$. Il colore serve solo a impedire che un ramo diventi più del doppio di un altro.

#### **1.1. Le rotazioni**

Le rotazioni sono le uniche operazioni che modificano la forma dell'albero; preservano la proprietà di ricerca e costano $\Theta(1)$.

**Rotazione sinistra su $x$** (richiede $y = x.\text{destro} \ne \text{NIL}$): $y$ prende il posto di $x$, $x$ diventa figlio sinistro di $y$, e il sottoalbero sinistro di $y$ diventa il sottoalbero destro di $x$.

**Rotazione destra su $y$**: è l'operazione inversa, con i ruoli di sinistra e destra scambiati.

#### **1.2. I tre casi dell'inserimento**

Il nodo appena inserito è sempre **rosso**: così non altera l'altezza nera (proprietà 5) e l'unica violazione possibile è quella dei due rossi consecutivi (proprietà 4), che si verifica se anche il padre è rosso. Detto $z$ il nodo problematico, il ripristino dipende dal colore dello **zio** (il fratello del padre):

| Caso | Condizione | Azione |
|---|---|---|
| **1** | lo **zio è rosso** | ricolorazione: padre e zio diventano neri, il nonno rosso; $z$ risale al nonno e si ripete |
| **2** | zio nero, $z$ **disallineato** rispetto a padre e nonno (configurazione "a triangolo") | rotazione sul **padre**, che riconduce al caso 3 |
| **3** | zio nero, $z$ **allineato** (configurazione "a linea") | ricolorazione di padre e nonno + rotazione sul **nonno**; il ripristino termina |

Al termine la radice viene sempre riportata a nero.

> ⚠️ Il caso 1 è l'unico che può **propagarsi verso l'alto**: sposta il problema di due livelli, quindi può ripetersi fino a $O(\log n)$ volte. I casi 2 e 3 chiudono definitivamente il ripristino, per cui in ogni inserimento si eseguono al massimo **due rotazioni**.

---

### **2. Esercizio — Costruzione dell'albero rosso-nero**

#### **2.1. Traccia**

Inserire nell'ordine le chiavi

$$
10,\; 85,\; 15,\; 70,\; 20,\; 60,\; 30,\; 50,\; 65,\; 80,\; 90,\; 40,\; 5,\; 55
$$

in un albero rosso-nero inizialmente vuoto, indicando a ogni passo il caso applicato.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nella descrizione degli alberi si scrive **(N)** per nero e **(R)** per rosso; i nodi `NIL` non vengono riportati.

#### **2.2. Inserimento di 10, 85**

**10.** L'albero è vuoto: il nodo diventa radice e viene colorato di nero (proprietà 2).

- 10 **(N)**

**85.** $85 > 10$, quindi diventa figlio destro; è rosso ma il padre è nero, nessuna violazione.

- 10 **(N)**
  - destro: 85 **(R)**

#### **2.3. Inserimento di 15 — caso 2 seguito dal caso 3**

Percorso di ricerca: $15 > 10$ (destra), $15 < 85$ (sinistra). Il nodo $15$ **(R)** diventa figlio sinistro di $85$ **(R)**: due rossi consecutivi.

Lo **zio** è il figlio sinistro di $10$, cioè `NIL`, che è nero. Inoltre $z=15$ è figlio *sinistro* mentre suo padre $85$ è figlio *destro* del nonno: la configurazione è **disallineata** (a triangolo).

**Caso 2** — rotazione **destra** sul padre $85$; il ruolo di nodo problematico passa a $85$:

- 10 **(N)**
  - destro: 15 **(R)**
    - destro: 85 **(R)**

Ora $85$ è figlio destro di $15$, che è figlio destro di $10$: la configurazione è **allineata**.

**Caso 3** — si colora il padre $15$ di nero e il nonno $10$ di rosso, poi si ruota a **sinistra** sul nonno:

- 15 **(N)**
  - sinistro: 10 **(R)**
  - destro: 85 **(R)**

#### **2.4. Inserimento di 70 — caso 1**

Percorso: $70 > 15$ (destra), $70 < 85$ (sinistra). Il nodo $70$ **(R)** ha padre $85$ **(R)**: violazione. Lo zio è $10$, che è **rosso**.

**Caso 1** — ricolorazione: padre $85$ e zio $10$ diventano neri, il nonno $15$ rosso. Il nodo problematico risale a $15$, che è la radice: la radice torna nera.

- 15 **(N)**
  - sinistro: 10 **(N)**
  - destro: 85 **(N)**
    - sinistro: 70 **(R)**

#### **2.5. Inserimento di 20 — caso 3**

Percorso: destra ($85$), sinistra ($70$), sinistra. Il nodo $20$ **(R)** ha padre $70$ **(R)**. Il nonno è $85$ e lo zio è `NIL` (nero). Sia $20$ sia $70$ sono figli sinistri: configurazione **allineata**.

**Caso 3** — padre $70$ nero, nonno $85$ rosso, rotazione **destra** sul nonno $85$:

- 15 **(N)**
  - sinistro: 10 **(N)**
  - destro: 70 **(N)**
    - sinistro: 20 **(R)**
    - destro: 85 **(R)**

#### **2.6. Inserimento di 60 — caso 1**

Percorso: destra ($70$), sinistra ($20$), destra. Il nodo $60$ **(R)** ha padre $20$ **(R)**; il nonno è $70$ e lo zio è $85$, **rosso**.

**Caso 1** — $20$ e $85$ diventano neri, $70$ rosso; il nodo problematico risale a $70$, il cui padre $15$ è nero: il ripristino termina.

- 15 **(N)**
  - sinistro: 10 **(N)**
  - destro: 70 **(R)**
    - sinistro: 20 **(N)** → destro: 60 **(R)**
    - destro: 85 **(N)**

#### **2.7. Inserimento di 30 — caso 2 seguito dal caso 3**

Percorso: destra ($70$), sinistra ($20$), destra ($60$), sinistra. Il nodo $30$ **(R)** ha padre $60$ **(R)**; il nonno è $20$ **(N)** e lo zio è `NIL` (nero). $30$ è figlio sinistro, $60$ è figlio destro: **disallineato**.

**Caso 2** — rotazione destra su $60$, il nodo problematico diventa $60$; la catena è ora $20 \to 30 \to 60$, tutta a destra.

**Caso 3** — $30$ diventa nero, $20$ rosso, rotazione sinistra su $20$:

- 15 **(N)**
  - sinistro: 10 **(N)**
  - destro: 70 **(R)**
    - sinistro: 30 **(N)**
      - sinistro: 20 **(R)**
      - destro: 60 **(R)**
    - destro: 85 **(N)**

#### **2.8. Inserimento di 50 — caso 1, poi caso 2 e caso 3**

Questo è l'inserimento più laborioso: il caso 1 sposta la violazione verso l'alto, dove serve una seconda correzione.

Percorso: destra ($70$), sinistra ($30$), destra ($60$), sinistra. Il nodo $50$ **(R)** ha padre $60$ **(R)**; il nonno è $30$ e lo zio è $20$, **rosso**.

**Caso 1** — $60$ e $20$ diventano neri, $30$ rosso; il nodo problematico risale a $30$. Ma il padre di $30$ è $70$, anch'esso **rosso**: la violazione si è spostata, non risolta.

- 15 **(N)**
  - sinistro: 10 **(N)**
  - destro: 70 **(R)**
    - sinistro: 30 **(R)** ← nuova violazione
      - sinistro: 20 **(N)**
      - destro: 60 **(N)** → sinistro: 50 **(R)**
    - destro: 85 **(N)**

Nuovo nonno: $15$; nuovo zio: $85$, **nero**. Il nodo $30$ è figlio sinistro mentre $70$ è figlio destro: **disallineato**.

**Caso 2** — rotazione destra su $70$; il nodo problematico diventa $70$ e la catena $15 \to 30 \to 70$ è allineata a destra.

**Caso 3** — $30$ diventa nero, $15$ rosso, rotazione sinistra su $15$. Il nodo $30$ diventa la **nuova radice**:

- 30 **(N)**
  - sinistro: 15 **(R)**
    - sinistro: 10 **(N)**
    - destro: 20 **(N)**
  - destro: 70 **(R)**
    - sinistro: 60 **(N)** → sinistro: 50 **(R)**
    - destro: 85 **(N)**

#### **2.9. Inserimenti di 65, 80, 90 — nessuna violazione**

Tutti e tre trovano un padre **nero** e non richiedono alcun intervento:

| Chiave | Percorso | Padre | Esito |
|---:|---|---|---|
| $65$ | $30 \to 70 \to 60 \to$ destra | $60$ **(N)** | inserito rosso |
| $80$ | $30 \to 70 \to 85 \to$ sinistra | $85$ **(N)** | inserito rosso |
| $90$ | $30 \to 70 \to 85 \to$ destra | $85$ **(N)** | inserito rosso |

- 30 **(N)**
  - sinistro: 15 **(R)** → 10 **(N)**, 20 **(N)**
  - destro: 70 **(R)**
    - sinistro: 60 **(N)** → 50 **(R)**, 65 **(R)**
    - destro: 85 **(N)** → 80 **(R)**, 90 **(R)**

#### **2.10. Inserimento di 40 — caso 1 applicato due volte**

Percorso: destra ($70$), sinistra ($60$), sinistra ($50$), sinistra. Il nodo $40$ **(R)** ha padre $50$ **(R)**; il nonno è $60$ e lo zio è $65$, **rosso**.

**Caso 1 (prima applicazione)** — $50$ e $65$ diventano neri, $60$ rosso; il nodo problematico risale a $60$, il cui padre $70$ è **rosso**.

- 30 **(N)**
  - sinistro: 15 **(R)** → 10 **(N)**, 20 **(N)**
  - destro: 70 **(R)**
    - sinistro: 60 **(R)** ← nuova violazione
      - sinistro: 50 **(N)** → sinistro: 40 **(R)**
      - destro: 65 **(N)**
    - destro: 85 **(N)** → 80 **(R)**, 90 **(R)**

Nuovo nonno: $30$; nuovo zio: $15$, **rosso**.

**Caso 1 (seconda applicazione)** — $70$ e $15$ diventano neri, $30$ rosso; il nodo problematico risale a $30$, che è la radice e torna **nera**.

- 30 **(N)**
  - sinistro: 15 **(N)** → 10 **(N)**, 20 **(N)**
  - destro: 70 **(N)**
    - sinistro: 60 **(R)**
      - sinistro: 50 **(N)** → sinistro: 40 **(R)**
      - destro: 65 **(N)**
    - destro: 85 **(N)** → 80 **(R)**, 90 **(R)**

> 💡 Quando il caso 1 raggiunge la radice, la ricolorazione del "nonno" viene immediatamente annullata dal ripristino della proprietà 2. Il risultato netto è che **l'altezza nera dell'intero albero aumenta di uno**: è l'unico modo in cui un albero rosso-nero può crescere in profondità nera, ed è il corrispettivo della "risalita della radice" nei B-alberi.

#### **2.11. Inserimenti di 5 e 55 — nessuna violazione**

| Chiave | Percorso | Padre | Esito |
|---:|---|---|---|
| $5$ | $30 \to 15 \to 10 \to$ sinistra | $10$ **(N)** | inserito rosso |
| $55$ | $30 \to 70 \to 60 \to 50 \to$ destra | $50$ **(N)** | inserito rosso |

#### **2.12. Albero finale**

- **30 (N)** — radice
  - **15 (N)**
    - **10 (N)** → sinistro: **5 (R)**
    - **20 (N)**
  - **70 (N)**
    - **60 (R)**
      - **50 (N)** → sinistro: **40 (R)**, destro: **55 (R)**
      - **65 (N)**
    - **85 (N)**
      - **80 (R)**
      - **90 (R)**

#### **2.13. Verifica delle proprietà**

**Proprietà 2 e 3.** La radice $30$ è nera; tutte le foglie `NIL` sono nere per definizione.

**Proprietà 4 (nessun rosso con figlio rosso).** I nodi rossi sono $5$, $60$, $40$, $55$, $80$, $90$. I nodi $5, 40, 55, 80, 90$ sono foglie, quindi i loro figli sono `NIL` neri; il nodo $60$ ha figli $50$ e $65$, entrambi neri. ✅

**Proprietà 5 (altezza nera uniforme).** Contando i nodi neri incontrati da un figlio della radice fino a `NIL` incluso:

| Cammino dalla radice | Nodi neri contati | $bh$ |
|---|---|---:|
| $30 \to 15 \to 10 \to 5 \to \text{NIL}$ | $15,\,10,\,\text{NIL}$ | 3 |
| $30 \to 15 \to 20 \to \text{NIL}$ | $15,\,20,\,\text{NIL}$ | 3 |
| $30 \to 70 \to 60 \to 50 \to 40 \to \text{NIL}$ | $70,\,50,\,\text{NIL}$ | 3 |
| $30 \to 70 \to 60 \to 65 \to \text{NIL}$ | $70,\,65,\,\text{NIL}$ | 3 |
| $30 \to 70 \to 85 \to 80 \to \text{NIL}$ | $70,\,85,\,\text{NIL}$ | 3 |
| $30 \to 70 \to 85 \to 90 \to \text{NIL}$ | $70,\,85,\,\text{NIL}$ | 3 |

Tutti i cammini hanno altezza nera $3$. ✅

**Vincolo sull'altezza.** Con $n=14$ nodi si ha $2\log_2(15) \approx 7.8$; l'altezza effettiva è $4$, ampiamente entro il limite.

---

### **3. La cancellazione: i quattro casi di ripristino**

#### **3.1. Perché la cancellazione è più delicata dell'inserimento**

La cancellazione in un albero rosso-nero comincia come in un normale albero binario di ricerca: si individua il nodo da eliminare e, se ha due figli, lo si sostituisce con il suo **successore** (il minimo del sottoalbero destro), che ha al più un figlio. Il nodo fisicamente rimosso $y$ è quindi sempre un nodo con al più un figlio.

Il colore di $y$ determina se serve un ripristino:

- se $y$ era **rosso**, nessuna proprietà viene violata: l'altezza nera non cambia e non si creano rossi adiacenti;
- se $y$ era **nero**, tutti i cammini che passavano per $y$ hanno perso un nodo nero: la proprietà 5 è violata.

Per formalizzare la riparazione si attribuisce al nodo $x$ che ha preso il posto di $y$ un **nero in eccesso**: $x$ diventa "doppiamente nero" (o "rosso-e-nero" se era rosso). Il nero aggiuntivo non è un colore legale: va spinto verso l'alto finché non si riesce a scaricarlo.

Il nero in eccesso si elimina in tre modi:

1. $x$ diventa la radice: si scarta semplicemente (l'altezza nera di tutto l'albero cala di uno);
2. $x$ è rosso-e-nero: lo si colora di nero e si finisce;
3. tramite rotazioni e ricolorazioni che trasferiscono il nero dal fratello.

Nelle figure che seguono $a$ è il nodo doppiamente nero, $b$ è suo padre, $c$ è il **fratello** di $a$, e $d$, $e$ sono i figli di $c$. I nodi disegnati **con sfondo bianco** possono essere indifferentemente rossi o neri.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Caso 1 — fratello nero con entrambi i figli neri**

**Condizioni:**
- il fratello $c$ del nodo doppiamente nero $a$ è **nero**;
- entrambi i figli $d$ ed $e$ di $c$ sono **neri**.

**Azione:** si toglie un nero al fratello, colorandolo di **rosso**, e si sposta il nero in eccesso sul padre $b$.

| Prima | Dopo |
|---|---|
| $b$ (indifferente); $a$ nero doppio; $c$ nero con $d$, $e$ neri | $b$ **doppiamente nero**; $a$ nero; $c$ **rosso** con $d$, $e$ neri |

Ora entrambi i sottoalberi di $b$ hanno perso un nero, per cui sono di nuovo bilanciati fra loro; il difetto si è però trasferito a $b$, e il procedimento si ripete con $x=b$.

> ⚠️ È l'unico dei quattro casi che **non termina**: può risalire fino alla radice, per un totale di $O(\log n)$ iterazioni. Se $b$ era rosso, alla iterazione successiva si esce immediatamente colorandolo di nero.

#### **3.3. Caso 2 — fratello rosso**

**Condizioni:**
- il fratello $c$ è **rosso** (e dunque, per la proprietà 4, il padre $b$ e i figli $d$, $e$ di $c$ sono tutti neri).

**Azione (trasformazione):** si scambiano i colori di $b$ e $c$ e si esegue una rotazione **sinistra** su $b$ (destra, se $a$ è figlio destro).

| Prima | Dopo |
|---|---|
| $b$ nero; $a$ nero doppio; $c$ **rosso** con $d$, $e$ neri | $c$ **nero** radice del sottoalbero; $b$ **rosso** con figli $a$ (ancora doppiamente nero) e $d$; $e$ nero |

Il nodo $a$ resta doppiamente nero, ma il suo nuovo fratello è $d$, che è **nero**: il caso 2 non risolve nulla da solo, serve a **ricondursi** a uno degli altri tre.

> 💡 Il caso 2 si applica al massimo una volta per cancellazione: dopo la rotazione il fratello è certamente nero e non può tornare rosso.

#### **3.4. Caso 3 — fratello nero, figlio "vicino" rosso (disallineato)**

**Condizioni:**
- il fratello $c$ è **nero**;
- il figlio di $c$ dalla parte di $a$ (il figlio *vicino*, qui $d$) è **rosso**, mentre il figlio *lontano* $e$ è nero.

**Azione (trasformazione):** si scambiano i colori di $c$ e $d$ e si esegue una rotazione **destra** su $c$ (sinistra, nel caso speculare).

| Prima | Dopo |
|---|---|
| $b$; $a$ nero doppio; $c$ nero con $d$ **rosso** ed $e$ nero | $b$; $a$ nero doppio; $d$ **nero** con figlio destro $c$ **rosso**, che ha figlio destro $e$ nero |

Anche questo caso non conclude: serve a portare il rosso dalla parte *lontana*, riducendosi al caso 4.

#### **3.5. Caso 4 — fratello nero, figlio "lontano" rosso (allineato)**

**Condizioni:**
- il fratello $c$ è **nero**;
- il figlio *lontano* $e$ di $c$ è **rosso** (il colore del figlio vicino $d$ è irrilevante).

**Azione (terminale):** $c$ assume il colore di $b$; $b$ ed $e$ diventano **neri**; si esegue una rotazione **sinistra** su $b$. Il nero in eccesso su $a$ viene rimosso.

Il ripristino **termina qui**, con al più tre rotazioni complessive nell'intera operazione di cancellazione.

> 📌 Intuizione del caso 4: il sottoalbero di $a$ è "povero" di un nero e quello del fratello ne ha uno di troppo dalla parte lontana. La rotazione sposta il fratello in cima e trasferisce fisicamente il nodo rosso $e$, ricolorato di nero, dalla parte del ramo impoverito. È un vero e proprio **prestito** fra fratelli, l'analogo della ridistribuzione fra nodi adiacenti nei B-alberi.

#### **3.6. Corrispondenza con la numerazione del libro di testo**

> ⚠️ La numerazione usata a lezione **non coincide** con quella di Cormen, Leiserson, Rivest e Stein (CLRS), che è la più diffusa nei testi e negli esercizi trovati online. La corrispondenza è la seguente:

| Numerazione dell'esercitazione | Numerazione CLRS | Condizione | Terminale? |
|---|---|---|---|
| **Caso 1** | caso 2 | fratello nero, entrambi i figli neri | no, risale |
| **Caso 2** | caso 1 | fratello rosso | no, riconduce agli altri |
| **Caso 3** | caso 3 | fratello nero, figlio vicino rosso e lontano nero | no, riconduce al caso 4 |
| **Caso 4** | caso 4 | fratello nero, figlio lontano rosso | **sì** |

In sede d'esame conviene descrivere le **condizioni** anziché il solo numero: la condizione è univoca, il numero dipende dalla fonte.

#### **3.7. Schema decisionale riassuntivo**

Dato il nodo $x$ doppiamente nero con fratello $w$:

1. se $x$ è la radice → si scarta il nero in eccesso e si termina;
2. se $x$ è rosso-e-nero → lo si colora di nero e si termina;
3. se $w$ è **rosso** → **caso 2**, rotazione sul padre, poi si rivaluta;
4. se $w$ è nero con **entrambi i figli neri** → **caso 1**, si ricolora $w$ di rosso e si sale;
5. se $w$ è nero con **figlio vicino rosso e lontano nero** → **caso 3**, rotazione su $w$, poi caso 4;
6. se $w$ è nero con **figlio lontano rosso** → **caso 4**, rotazione sul padre, fine.

---

### **4. Sintesi**

| Operazione | Rotazioni | Ricolorazioni | Costo |
|---|---:|---|---|
| Ricerca | 0 | — | $O(\log n)$ |
| Inserimento | al più **2** | fino a $O(\log n)$ | $O(\log n)$ |
| Cancellazione | al più **3** | fino a $O(\log n)$ | $O(\log n)$ |

> ✅ Il numero di rotazioni è **costante**, mentre le ricolorazioni possono propagarsi lungo tutto il cammino verso la radice. È questa asimmetria a rendere gli alberi rosso-neri preferibili agli AVL nelle applicazioni con molti aggiornamenti: gli AVL garantiscono un bilanciamento più stretto (e quindi ricerche leggermente più veloci), ma possono richiedere $O(\log n)$ rotazioni in cancellazione.
