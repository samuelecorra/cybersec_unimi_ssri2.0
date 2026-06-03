# **L1 – DSA: firma (a,0) e caso δ=0**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre due argomenti d'esame:
> - **Appelli 230622 e 240624** (10 pt): *"Dimostrare che se si ottiene la firma (a, 0), il sistema DSA potrebbe essere rotto."*
> - **Appello 240220** (10 pt): *"Se durante la generazione della firma si produce δ=0, bisogna rigenerare r? Giustificare."*
>
> **Studia prima:**
> - [UD3 / L1 – Schema DSS](../UD3/L1%20-%20Schema%20DSS%20%28Digital%20Signature%20Standard%29.md) → formule di firma (γ, δ) e di verifica; è obbligatorio conoscere le formule a memoria prima di procedere

---

### **1. Riepilogo delle formule DSA**

Prima di analizzare il caso δ=0, richiamiamo le formule fondamentali.

**Parametri:**
- $p, q$ numeri primi con $q \mid (p-1)$, $|q|=160$ bit
- $\alpha \in \mathbb{Z}_p^*$ di ordine $q$; $\beta = \alpha^s \bmod p$
- $s$: chiave privata; $(p, q, \alpha, \beta)$: chiave pubblica

**Firma del messaggio $M$** con nonce casuale $r \in [1, q-1]$:

$$
\gamma = (\alpha^r \bmod p) \bmod q
$$

$$
\delta = (\text{SHA}(M) + s\gamma) \cdot r^{-1} \bmod q
$$

La **firma** è la coppia $(\gamma, \delta)$.

**Verifica della firma $(\gamma, \delta)$:**

$$
e' = \text{SHA}(M) \cdot \delta^{-1} \bmod q \qquad e'' = \gamma \cdot \delta^{-1} \bmod q
$$

$$
\text{Accetta se: } \gamma \stackrel{?}{=} (\alpha^{e'} \cdot \beta^{e''} \bmod p) \bmod q
$$

> 📌 **Punto critico:** entrambe le fasi di verifica richiedono il calcolo di $\delta^{-1} \bmod q$. Questo inverso esiste **se e solo se** $\gcd(\delta, q) = 1$, cioè se $\delta \not\equiv 0 \pmod{q}$.

---

### **2. Analisi del caso δ=0: la chiave privata è recuperabile**

Supponiamo che la firma prodotta sia $(\gamma, \delta) = (a, 0)$ per qualche $a \ne 0$.

**Cosa implica δ=0 algebricamente?**

Dalla formula di firma:

$$
\delta = (\text{SHA}(M) + s \cdot \gamma) \cdot r^{-1} \bmod q
$$

Se $\delta = 0$, allora $(\text{SHA}(M) + s \cdot \gamma) \cdot r^{-1} \equiv 0 \pmod{q}$.

Poiché $r^{-1} \bmod q$ esiste (avevamo scelto $r \in [1, q-1]$ coprimo con $q$ primo), questo implica:

$$
\text{SHA}(M) + s \cdot \gamma \equiv 0 \pmod{q}
$$

Isolando $s$:

$$
s \cdot \gamma \equiv -\text{SHA}(M) \pmod{q}
$$

$$
\boxed{s \equiv -\text{SHA}(M) \cdot \gamma^{-1} \pmod{q}}
$$

> 📌 **Conclusione: la chiave privata $s$ è direttamente recuperabile** da chiunque conosca la firma $(a, 0)$, il messaggio $M$ e i parametri pubblici $(p, q, \alpha, \beta, \gamma)$.

**Tutti i termini del secondo membro sono pubblici o osservabili:**
- $\text{SHA}(M)$: calcolabile da chiunque conosca $M$
- $\gamma = a$: componente pubblica della firma
- $\gamma^{-1} \bmod q$: calcolabile tramite algoritmo di Euclide esteso
- $q$: parametro pubblico

Un avversario che osserva la firma $(a, 0)$ recupera $s$ con una singola operazione modulare.

---

### **3. Perché la firma (a, 0) è comunque invalida**

Oltre alla violazione della chiave, la firma con δ=0 è **matematicamente inutilizzabile** per la verifica.

La procedura di verifica richiede il calcolo di:

$$
e' = \text{SHA}(M) \cdot \delta^{-1} \bmod q \qquad \text{e} \qquad e'' = \gamma \cdot \delta^{-1} \bmod q
$$

Se $\delta = 0$, l'inverso $\delta^{-1} \bmod q$ **non esiste** perché $\gcd(0, q) = q \ne 1$.

> ⚠️ La verifica fallisce prima ancora di procedere: la firma $(a, 0)$ non può essere accettata da nessun verificatore corretto. Quindi δ=0 comporta contemporaneamente:
> 1. Firma **invalida** (non verificabile)
> 2. Chiave privata **esposta** (recuperabile da chiunque)

---

### **4. Bisogna rigenerare r quando δ=0?**

Questa è la domanda dell'appello 240220. La risposta è **sì, obbligatoriamente**, per due ragioni indipendenti:

#### **Ragione 1 — La firma è invalida e non può essere usata**

Se si trasmette $(a, 0)$, il destinatario non può verificarla (δ⁻¹ non esiste). La firma è inutile: la comunicazione fallisce. È quindi necessario produrre una nuova firma valida.

#### **Ragione 2 — δ=0 rivela la chiave privata**

Come dimostrato nella sezione 2, chiunque osservi la firma $(a, 0)$ può calcolare:

$$
s = -\text{SHA}(M) \cdot \gamma^{-1} \bmod q
$$

Trasmettere una firma con δ=0, anche solo una volta, compromette **permanentemente** la sicurezza del sistema per quell'utente. Anche rigenerare $r$ e ritrasmettre non aiuta: la chiave $s$ è già nota all'avversario.

> ⚠️ **In pratica:** un'implementazione corretta di DSA deve verificare $\delta \ne 0$ **prima** di trasmettere la firma. Se $\delta = 0$, si deve:
> 1. **Non trasmettere** la firma $(a, 0)$
> 2. **Generare un nuovo $r$** casuale
> 3. **Ricalcolare** $\gamma$ e $\delta$ da zero

Questo controllo è spesso implementato come un semplice `if δ == 0: goto step 1`.

---

### **5. Caso analogo: γ=0**

Per completezza, analizziamo brevemente il caso $\gamma = 0$ (firma $(0, \delta)$).

Dalla formula: $\gamma = (\alpha^r \bmod p) \bmod q$

Se $\gamma = 0$, la formula di firma diventa:

$$
\delta = (\text{SHA}(M) + s \cdot 0) \cdot r^{-1} \bmod q = \text{SHA}(M) \cdot r^{-1} \bmod q
$$

Questo non rivela direttamente $s$, ma la verifica usa:

$$
e'' = \gamma \cdot \delta^{-1} \bmod q = 0 \cdot \delta^{-1} = 0
$$

La verifica diventa:

$$
\gamma \stackrel{?}{=} (\alpha^{e'} \cdot \beta^0 \bmod p) \bmod q = (\alpha^{e'} \bmod p) \bmod q
$$

Un avversario che conosce $e'$ può costruire firme (0, δ) valide per messaggi arbitrari senza conoscere $s$ — questo è un tipo di **existential forgery**. Anche γ=0 è quindi proibito.

> 📌 **Regola generale:** una firma DSA valida richiede $\gamma \ne 0$ **e** $\delta \ne 0$. Se uno dei due è zero, la firma deve essere scartata e r rigenerato.

---

### **6. Confronto con il riuso di r**

Un'altra vulnerabilità DSA classica (già citata nelle lezioni) è il **riuso del nonce $r$**: firmare due messaggi diversi con lo stesso $r$ rivela $s$. Il caso δ=0 è una vulnerabilità diversa ma con la stessa conseguenza catastrofica.

| Vulnerabilità | Causa | Conseguenza |
|--------------|-------|-------------|
| Riuso di $r$ | Stesso $r$ per due messaggi | $s$ recuperabile da differenza delle firme |
| δ=0 | $\text{SHA}(M) + s\gamma \equiv 0 \pmod{q}$ | $s$ recuperabile direttamente dalla firma |
| γ=0 | $\alpha^r \bmod p \equiv 0 \pmod{q}$ (raro) | Existential forgery possibile |

---

### **7. Esercizi svolti stile esame**

#### **Esercizio A — Appelli 230622 e 240624 (10 punti)**

> *"Dimostrare che se per un messaggio $M$ si ottiene la firma $(a, 0)$, il sistema DSA potrebbe essere rotto."*

**Soluzione:**

La firma $(a, 0)$ significa $\gamma = a$ e $\delta = 0$.

Dalla formula di firma:
$$\delta = (\text{SHA}(M) + s\gamma) \cdot r^{-1} \bmod q$$

Se $\delta = 0$ e $r^{-1}$ esiste (il che vale sempre per $r \in [1, q-1]$ con $q$ primo):
$$\text{SHA}(M) + s \cdot a \equiv 0 \pmod{q}$$

Isolando $s$:
$$s \equiv -\text{SHA}(M) \cdot a^{-1} \pmod{q}$$

Poiché $\text{SHA}(M)$, $a$ e $q$ sono tutti noti o osservabili, la chiave privata $s$ è calcolabile con una singola operazione. Il sistema è completamente rotto.

---

#### **Esercizio B — Appello 240220 (10 punti)**

> *"Cosa succede se durante la generazione della firma si produce δ=0? È vero che bisogna generare un nuovo valore casuale r? Giustificare."*

**Soluzione:**

Sì, bisogna obbligatoriamente rigenerare $r$ per due ragioni:

**1. La firma è invalida.** La verifica richiede $\delta^{-1} \bmod q$, che non esiste quando $\delta = 0$ (poiché $\gcd(0, q) = q \ne 1$). La firma $(a, 0)$ non può essere verificata da nessun destinatario.

**2. La firma rivela la chiave privata.** Come dimostrato nell'esercizio A, da $\delta = 0$ si ricava $s = -\text{SHA}(M) \cdot \gamma^{-1} \bmod q$. Trasmettere questa firma comprometterebbe permanentemente la sicurezza. Il protocollo corretto prevede di verificare $\delta \ne 0$ prima di trasmettere e, in caso contrario, generare un nuovo $r$ casuale e ricalcolare $(\gamma, \delta)$.

---

### **8. Sintesi**

| Caso | Effetto sulla firma | Effetto sulla sicurezza | Azione richiesta |
|------|--------------------|-----------------------|-----------------|
| $\delta = 0$ | Firma invalida (δ⁻¹ inesistente) | $s$ recuperabile: $s = -\text{SHA}(M)\cdot\gamma^{-1} \bmod q$ | Rigenerare $r$, non trasmettere |
| $\gamma = 0$ | Firma invalida (verifica degenere) | Existential forgery possibile | Rigenerare $r$, non trasmettere |
| $r$ riusato | Firma valida | $s$ recuperabile da due firme | Mai riusare $r$ |

> ✅ **Recap:** quando δ=0 nello schema DSA, la firma è simultaneamente inutilizzabile (la verifica richiede δ⁻¹ che non esiste) e catastrofica per la sicurezza (da δ=0 si ricava direttamente la chiave privata $s = -\text{SHA}(M)\cdot\gamma^{-1} \bmod q$). È obbligatorio rigenerare il nonce $r$ senza trasmettere la firma difettosa. La stessa logica vale per γ=0.
