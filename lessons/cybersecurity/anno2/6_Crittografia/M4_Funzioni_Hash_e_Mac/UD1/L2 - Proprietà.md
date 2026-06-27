## **Lezione 2: Proprietà**

### **1. Obiettivo della lezione**

In questa lezione comprendiamo **quali proprietà rendono sicure** le funzioni hash e **come queste proprietà si collegano** tra loro.  
Alla fine saprai distinguere chiaramente tra:

- **One-wayness (pre-image resistance)**
- **Second pre-image resistance (sicurezza debole)**
- **Collision resistance (sicurezza forte)**

e capire **perché la sicurezza forte implica la one-wayness**.

---

### **2. Le tre proprietà fondamentali**

#### **2.1 One-wayness (pre-image resistance)**

**Idea intuitiva:** dato un digest $y$, deve essere **impraticabile** risalire a **qualche** messaggio $M$ tale che $h(M)=y$.

**Perché è importante:** se si potesse invertire facilmente $h$, un attaccante potrebbe costruire messaggi con hash scelti a piacere, compromettendo firme digitali, autenticazioni e protocolli.

---

#### **2.2 Sicurezza debole (second pre-image resistance)**

**Idea intuitiva:** fissato un messaggio $M$, è **impraticabile** trovarne **un altro** $M'$ con **lo stesso hash**, cioè $h(M)=h(M')$.

**Significato pratico:** se qualcuno possiede un documento $M$ firmato, non deve poter creare un secondo documento diverso ma con lo stesso digest, altrimenti la firma resterebbe valida anche per il documento falso.

---

#### **2.3 Sicurezza forte (collision resistance)**

**Idea intuitiva:** è **impraticabile** trovare **qualunque coppia** di messaggi distinti $(M_1, M_2)$ con lo stesso hash $h(M_1)=h(M_2)$.

**Significato pratico:** protegge da attacchi in cui l’avversario cerca due messaggi qualsiasi che collidano, senza bisogno che uno sia fissato in anticipo.

---

### **3. Classi di funzioni hash crittografiche**

#### **3.1 OWHF – One-Way Hash Function (debole)**

Una **OWHF** è una funzione hash che soddisfa:

- la **pre-image resistance**
- la **second pre-image resistance**

È detta anche **weak one-way hash function**.  
In sintesi: difficile invertire e difficile duplicare un messaggio fissato.

---

#### **3.2 CRHF – Collision-Resistant Hash Function (forte)**

Una **CRHF** soddisfa la proprietà di **collision resistance**, ed è detta anche **strong one-way hash function**.  
In sintesi: difficile trovare _qualsiasi coppia_ di messaggi diversi con lo stesso digest.

---

### **4. One-Way Function (OWF) vs One-Way Hash**

Per chiarezza, distinguiamo la **funzione one-way** in senso matematico da una **one-way hash**:

- In una **OWF**:
  - è **facile** calcolare $y=f(x)$ per ogni $x$;
  - è **impraticabile** trovare $x$ dato $y$ tale che $f(x)=y$.
- Differenze con una **OWHF**:
  - una OWF **non deve comprimere** (l’output può essere grande quanto o più dell’input);
  - una OWF **non richiede** la proprietà di **second pre-image resistance**.

---

### **5. Relazioni tra le proprietà**

#### **5.1 Sicurezza forte implica sicurezza debole**

SI PROCEDE PER ASSURDO:
Se una funzione è **collision-resistant**, allora, fissato un input $x_1$, non posso trovarne un altro $x_2 \ne x_1$ con $h(x_1)=h(x_2)$, altrimenti sarebbe già una collisione.

**Conclusione:**

$$
\text{Collision resistance} \Rightarrow \text{Second pre-image resistance}
$$

---

#### **5.2 Le non-implicazioni**

- **Second pre-image NON IMPLICA ⇏ pre-image:**  
   _Esempio concettuale:_ la **funzione identità**.  
   Dato $y$, trovare $x$ tale che $x=y$ è banale, quindi non è one-way.  
   Ma il concetto di “second pre-image” per una funzione identità non aggiunge protezione utile: mostra solo che le due proprietà sono indipendenti.
- **Pre-image NON IMPLICA ⇏ second pre-image:**  
   _Esempio pratico:_ $g(x) = x^2 \bmod n$.
  L'elevazione al quadrato modulare è difficile da invertire in quanto per farlo richiede la fattorizzazione di $n$. Con fattorizzazione si intende trovare $x$ dato $y$ tale che $x^2 \equiv y \mod n$. E' difficile poiché la fattorizzazione di grandi numeri è computazionalmente complessa, dato che non esistono ad oggi algoritmi efficienti per farlo.
  Dato $x$, anche $-x$ produce lo stesso valore quadrato, quindi è facile trovare un secondo pre-image.  
   Dunque, anche se la funzione è difficile da invertire, non è necessariamente sicura rispetto alla seconda pre-image.

---

### **6. Perché la sicurezza forte implica la one-wayness**

#### **6.1 Dimostrazione intuitiva (per assurdo)**

Supponiamo che una funzione hash sia **collision-resistant** ma **non** **one-way**.  
Allora esisterebbe un **algoritmo di inversione** in grado di ricavare un messaggio $x$ da un digest $z$ con $h(x)=z$.

A partire da tale invertitore, possiamo costruire un algoritmo che **trova collisioni** con alta probabilità, contraddicendo la resistenza alle collisioni. Questa famiglia di algoritmi prende il nome Algoritmi Las Vegas, che trovano una soluzione ergo collisioni con probabilità di successo di almeno il 50%.

---

#### **6.2 Schema di costruzione dell'algoritmo Las Vegas**

Sia $h : X \to Z$ una funzione hash con $|X| \ge 2|Z|$ (cioè comprime).  
Definiamo un invertitore ipotetico `ALG` per $h$.

1. Scegli casualmente $x \in X$
2. Calcola $z = h(x)$
3. Calcola $x' = ALG(z)$
4. Se $x' \ne x$, abbiamo trovato una **collisione** $(x, x')$

Poiché $h$ è compressiva, con probabilità almeno $1/2$ l’algoritmo troverà una collisione, violando la collision-resistance.

**Conclusione:**

$$
\text{Collision resistance} \Rightarrow \text{One-wayness}
$$

---

### **7. Riepilogo finale**

- **Proprietà fondamentali:**
  1. One-wayness (pre-image resistance)
  2. Second pre-image resistance
  3. Collision resistance
- **Relazioni tra loro:**
  $$
  \text{Collision-resistant} \Rightarrow \text{Second pre-image-resistant}
  $$
  $$
  \text{Collision-resistant} \Rightarrow \text{One-way}
  $$
- **Non-implicazioni:**  
   Nessuna relazione automatica tra pre-image e second pre-image.
- **Esempi chiave:**
  - Funzione identità → non è one-way.
  - $x^2 \bmod n$ → pre-image ok, ma second pre-image debole.

---

> 💡 **Prossimo passo per l'esame:** ora conosci la definizione formale di one-wayness. L'appello 250704 chiede di applicarla a $H(x) = \text{DES}_k(x)$ in tre scenari distinti ($k$ variabile, fisso noto, fisso ignoto) — argomento da **15 punti**. Vai a [UD4 / L1 – H(x)=DES_k(x): analisi one-wayness](../UD4_Approfondimenti_Esame/L1%20-%20H%28x%29%3DDES_k%28x%29%20-%20analisi%20one-wayness.md) per la trattazione completa dei tre casi.
