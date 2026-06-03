# **L2 – MAC personalizzato AES+hash: attacco per collisione**

---

> ⚠️ **Lezione integrativa di preparazione all'esame.** Non appartiene al programma canonico delle videolezioni. Copre un argomento presente in 2 appelli (230124 e 250905) con peso di **15–20 punti**.
>
> **Studia prima:**
> - [UD3 / L1 – Codici MAC](../UD3/L1%20-%20Codici%20MAC.md) → proprietà di sicurezza MAC, tipi di attacco (known/chosen message), obiettivi (total break, selective forgery, existential forgery)
> - [UD3 / L3 – HMAC](../UD3/L3%20-%20HMAC.md) → perché HMAC è sicuro; capire le debolezze del prefix/suffix MAC è il contesto che motiva questa lezione

---

### **1. Lo schema MAC personalizzato degli appelli**

Il professore definisce uno schema MAC non-standard e chiede di trovarne un attacco. La struttura tipica è:

$$
\text{MAC}_k(m) =
\begin{cases}
\text{AES}_k(m) & \text{se } |m| = n \text{ (messaggio corto)} \\
\text{AES}_k(h(m)) & \text{se } |m| > n \text{ (messaggio lungo)}
\end{cases}
$$

dove:
- $k$ è la chiave segreta condivisa (128 bit per AES),
- $n$ è la dimensione del blocco AES (128 bit),
- $h$ è una funzione hash **pubblica e nota** (es. SHA-256 troncata a 128 bit),
- $|h(m)| = n$ per ogni $m$ (l'output di $h$ ha esattamente $n$ bit).

La domanda d'esame è: **trovare un attacco che dimostri che questo MAC non è sicuro**.

---

### **2. Osservazione chiave: la collisione strutturale**

> 📌 **Osservazione centrale:** per ogni messaggio lungo $m$ (con $|m| > n$), il digest $h(m)$ ha esattamente $n$ bit. Quindi $h(m)$ è un messaggio **corto** valido per lo schema.

Valutiamo i due MAC:

$$
\text{MAC}_k(m) = \text{AES}_k(h(m)) \qquad \text{(perché } |m| > n\text{)}
$$

$$
\text{MAC}_k(h(m)) = \text{AES}_k(h(m)) \qquad \text{(perché } |h(m)| = n\text{)}
$$

Quindi:

$$
\boxed{\text{MAC}_k(m) = \text{MAC}_k(h(m)) \quad \text{per qualsiasi } m \text{ con } |m| > n}
$$

> ⚠️ Due messaggi **distinti** ($m$ e $h(m)$) producono **lo stesso MAC**. Questa è una **collisione del MAC**, che costituisce una violazione fondamentale della sicurezza.

---

### **3. L'attacco formale: existential forgery con una query**

L'avversario esegue il seguente attacco sotto **Chosen Message Attack (CMA)**:

**Passo 1 — Query all'oracolo MAC:**

Scegli un messaggio lungo $m$ con $|m| > n$ e chiedi il suo MAC:

$$\text{richiesta: } m \xrightarrow{\text{oracolo}} \text{MAC}_k(m) = \text{AES}_k(h(m))$$

**Passo 2 — Calcolo pubblico di $h(m)$:**

Poiché $h$ è pubblica, l'avversario calcola autonomamente $h(m)$ (nessuna query aggiuntiva necessaria).

**Passo 3 — Costruzione della forgery:**

Presenta la coppia $(h(m),\ \text{MAC}_k(m))$ come messaggio autentico. Il verificatore accetta:

$$\text{MAC}_k(h(m)) \stackrel{?}{=} \text{MAC}_k(m) \quad \Rightarrow \quad \text{AES}_k(h(m)) = \text{AES}_k(h(m)) \quad \checkmark$$

> 📌 **L'attacco riesce**: con **una sola query** l'avversario ottiene un MAC valido per un messaggio ($h(m)$) che non ha mai presentato all'oracolo. Questo è un **existential forgery** sotto CMA.

---

### **4. Perché questo rompe la sicurezza MAC**

Un MAC è sicuro se un avversario che conosce alcune coppie $(m_i, \text{MAC}_k(m_i))$ non riesce a produrre una coppia valida per un messaggio **mai presentato** all'oracolo.

In questo schema:
- L'avversario presenta $m$ → ottiene $\text{MAC}_k(m)$
- Presenta $(h(m), \text{MAC}_k(m))$ come forgery
- $h(m) \ne m$ (messaggio diverso) ma il MAC è uguale
- Il verificatore accetta: la sicurezza MAC è **rotta**

Il tipo di attacco è **existential forgery under chosen message attack (CMA)** — il livello di attacco più debole, ma sufficiente a rompere la sicurezza formale.

---

### **5. Analisi strutturale: perché lo schema è difettoso**

#### **Causa del problema: comportamento inconsistente per messaggi di diverse lunghezze**

Lo schema tratta messaggi corti e lunghi in modo diverso:
- Messaggi corti ($|m| = n$): MAC = $\text{AES}_k(m)$ direttamente
- Messaggi lunghi ($|m| > n$): MAC = $\text{AES}_k(h(m))$

Il difetto è che l'output di $h$ (128 bit) cade esattamente nella categoria "messaggio corto", creando un'identità involontaria tra due percorsi di calcolo.

#### **Confronto con HMAC**

HMAC risolve questo problema in modo elegante:

$$\text{HMAC}(K, M) = H\big((K' \oplus \text{opad}) \,\|\, H((K' \oplus \text{ipad}) \,\|\, M)\big)$$

- La chiave è mischiata con `ipad` e `opad` prima e dopo l'hashing.
- Non esiste un "percorso alternativo" per messaggi di lunghezza diversa.
- Un messaggio lungo e il suo hash producono **sempre MAC diversi** perché la struttura HMAC è indipendente dalla lunghezza dell'input.

> 💡 **Lezione generale:** uno schema MAC che ha due comportamenti distinti in base alla lunghezza del messaggio crea automaticamente rischi di collisione quando i due percorsi di calcolo si sovrappongono. La sicurezza MAC richiede che la funzione $m \mapsto \text{MAC}_k(m)$ sia **iniettiva** (messaggi diversi → MAC diversi) — o almeno computazionalmente resistente alla forgery.

---

### **6. Variante: schema con parametri diversi**

Gli appelli possono presentare varianti dello stesso schema. Esempio:

$$
\text{MAC}_k(m) =
\begin{cases}
\text{AES}_k(m) & \text{se } |m| \le n \\
\text{AES}_k(h(m) \oplus k) & \text{se } |m| > n
\end{cases}
$$

In questa variante, il XOR con $k$ nell'output della hash **potrebbe** rompere la simmetria. Analisi:

- $\text{MAC}_k(m) = \text{AES}_k(h(m) \oplus k)$ per $|m| > n$
- Per costruire un messaggio corto equivalente, servirebbe trovare $m' = h(m) \oplus k$ con $|m'| = n$ tale che $\text{AES}_k(m') = \text{AES}_k(h(m) \oplus k)$ → ovvero $m' = h(m) \oplus k$
- Ma $k$ è segreto, quindi l'avversario non può calcolare $h(m) \oplus k$

Questa variante è **più sicura**: l'XOR con la chiave segreta impedisce all'avversario di costruire il messaggio collidente senza conoscere $k$. Tuttavia potrebbe avere altre vulnerabilità (es. se $|h(m) \oplus k| = n$ per ogni $m$, esiste ancora una collisione potenziale con messaggio $m' = h(m) \oplus k$ se l'avversario riesce a ottenerlo).

---

### **7. Esercizio svolto stile esame (20 punti)**

#### **Testo (Appello 230124 / 250905)**

> Sia dato il seguente schema MAC con chiave segreta $k$:
> $$\text{MAC}_k(m) = \begin{cases} \text{AES}_k(m) & \text{se } |m| = 128\text{ bit} \\ \text{AES}_k(h(m)) & \text{se } |m| > 128\text{ bit} \end{cases}$$
> dove $h$ è una funzione hash pubblica con output a 128 bit.
>
> (a) Descrivere i parametri di questo schema (chiave, input, output). *(5 pt)*
>
> (b) Trovare un attacco che rompa questo schema. Giustificare formalmente perché l'attacco ha successo. *(15 pt)*

---

#### **Soluzione (a) — Parametri:**

- **Chiave:** $k \in \{0,1\}^{128}$ (chiave AES-128 segreta condivisa tra mittente e destinatario)
- **Input:** messaggio $m$ di lunghezza arbitraria (≥ 128 bit)
- **Output:** tag di autenticazione di 128 bit

---

#### **Soluzione (b) — Attacco:**

**Costruzione dell'attacco (existential forgery under CMA):**

1. Scegli qualsiasi messaggio lungo $m$ con $|m| > 128$ bit.
2. Chiedi all'oracolo MAC il valore $t = \text{MAC}_k(m) = \text{AES}_k(h(m))$.
3. Calcola autonomamente $m' = h(m)$ (operazione pubblica, $|m'| = 128$ bit).
4. Presenta la coppia $(m', t)$ come messaggio autentico.

**Verifica che l'attacco funziona:**

Il verificatore controlla se $\text{MAC}_k(m') \stackrel{?}{=} t$:

$$\text{MAC}_k(m') = \text{MAC}_k(h(m)) = \text{AES}_k(h(m)) = t \quad \checkmark$$

La verifica ha successo perché $|m'| = |h(m)| = 128$ bit, quindi si usa la prima casistica dello schema, e $\text{AES}_k(m') = \text{AES}_k(h(m)) = t$.

**Conclusione:** l'avversario ha prodotto una coppia MAC valida $(m', t)$ per un messaggio $m'$ mai presentato all'oracolo, con una sola query. Lo schema non è sicuro: esiste un existential forgery in tempo O(1) (una query + un'applicazione di $h$).

---

### **8. Sintesi**

| Aspetto | Valore |
|---------|--------|
| **Vulnerabilità** | Collisione strutturale: $\text{MAC}_k(m) = \text{MAC}_k(h(m))$ per $|m|>n$ |
| **Tipo di attacco** | Existential forgery under CMA |
| **Query necessarie** | 1 |
| **Costo computazionale** | O(1) — una query MAC + una computazione di $h$ |
| **Causa radice** | Output di $h$ (128 bit) cade nella categoria "messaggio corto" |
| **Confronto HMAC** | HMAC non ha questo problema: struttura a doppio hash con maschere ipad/opad evita qualsiasi sovrapposizione |

> ✅ **Recap:** lo schema MAC ibrido che usa AES direttamente per messaggi corti e AES(h(m)) per messaggi lunghi è vulnerabile perché l'output di $h$ (128 bit) coincide con la lunghezza "corta". Un avversario ottiene MAC_k(m) e lo usa immediatamente come MAC valido per h(m), che non ha mai presentato all'oracolo. Una query è sufficiente per rompere completamente lo schema.
