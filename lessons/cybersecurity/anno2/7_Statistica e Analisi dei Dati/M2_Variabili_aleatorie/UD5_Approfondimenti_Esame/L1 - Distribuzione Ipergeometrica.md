# **M2 UD5 Lezione 1 - Distribuzione Ipergeometrica**

> ⚠️ **Unità integrativa d'esame.** Questo argomento **non è trattato nelle videolezioni ufficiali del corso**, ma compare sistematicamente nei temi d'esame: appelli del [15/01/2025 (D1)](../../M4_Esami_svolti/esami_2025/4_esame_15_gennaio_2025_soluzione.md), [05/07/2025 (D3)](../../M4_Esami_svolti/esami_2025/2_esame_5_luglio_2025_soluzione.md), [19/09/2025 (D1)](../../M4_Esami_svolti/esami_2025/7_esame_19_settembre_2025_soluzione.md) e [16/01/2026 (D1)](../../M4_Esami_svolti/esami_2025/8_esame_16_gennaio_2026_soluzione.md). Questa lezione colma la lacuna.

### **1. Il contesto: campionamento senza reinserimento**

Consideriamo una **popolazione finita** di $N$ oggetti, divisa in due categorie:

- $K$ oggetti "successo" (es. componenti **difettosi**),
- $N - K$ oggetti "insuccesso" (es. componenti funzionanti).

Estraiamo un campione di $n$ oggetti **senza reinserimento** e chiamiamo $X$ il numero di successi nel campione.

> 📌 La parola chiave è **senza reinserimento**: dopo ogni estrazione la composizione della popolazione cambia, quindi la probabilità di successo **non è costante** e le prove **non sono indipendenti**. È esattamente la condizione che rende **inapplicabile la Binomiale**, la quale richiede prove indipendenti con $p$ fisso.

Il confronto va tenuto a mente come un bivio:

| Modalità di campionamento | Prove | Distribuzione di $X$ |
|---|---|---|
| **Con** reinserimento | indipendenti, $p = K/N$ costante | Binomiale $B(k \mid p, n)$ |
| **Senza** reinserimento | dipendenti, composizione variabile | **Ipergeometrica** |

### **2. La formula**

Il calcolo è puramente **combinatorio**. Gli esiti equiprobabili sono tutti i modi di scegliere $n$ oggetti da $N$, cioè $\binom{N}{n}$. I casi favorevoli all'evento "$k$ successi" si costruiscono scegliendo $k$ successi tra i $K$ disponibili **e** $n-k$ insuccessi tra gli $N-K$ disponibili (principio di moltiplicazione):

$$
P(X = k) = \frac{\dbinom{K}{k}\dbinom{N-K}{n-k}}{\dbinom{N}{n}}
$$

Il supporto è $\max(0,\ n-(N-K)) \leq k \leq \min(n,\ K)$: non si possono estrarre più successi di quanti ne esistano ($k \leq K$), né più insuccessi di quanti ne esistano ($n-k \leq N-K$).

> 💡 Struttura mnemonica: **"scelgo i difettosi tra i difettosi, i buoni tra i buoni, e normalizzo su tutte le scelte possibili"**. I coefficienti al numeratore devono sempre sommare gli argomenti in alto a $N$ e in basso a $n$: $\binom{K}{k}\binom{N-K}{n-k}$ con $K + (N-K) = N$ e $k + (n-k) = n$. È un controllo di correttezza immediato.

### **3. Media e varianza**

Posto $p = K/N$ (frazione di successi nella popolazione):

$$
E[X] = n\,\frac{K}{N} = np
$$

$$
\mathrm{Var}(X) = n\,p\,(1-p)\,\underbrace{\frac{N-n}{N-1}}_{\text{fattore di correzione}}
$$

- La **media è identica** a quella binomiale: in media, il campione rispecchia la composizione della popolazione.
- La **varianza è quella binomiale** $np(1-p)$ **moltiplicata per il fattore di correzione per popolazione finita** $\frac{N-n}{N-1} < 1$.

> 📌 Interpretazione del fattore di correzione: campionare senza reinserimento "consuma" la popolazione e vincola gli esiti, **riducendo la variabilità**. Casi estremi: se $n = 1$ il fattore vale 1 (una sola estrazione: nessuna differenza col reinserimento); se $n = N$ il fattore vale 0 (estraggo tutto: $X = K$ certo, varianza nulla).

### **4. Relazione con la Binomiale (limite per popolazione grande)**

Se $N \to \infty$ mantenendo $K/N = p$ fisso, l'ipergeometrica **converge alla Binomiale** $B(k \mid p, n)$: quando la popolazione è enorme rispetto al campione, estrarre senza reinserimento altera la composizione in modo trascurabile e le prove tornano "di fatto" indipendenti.

> ✅ **Regola pratica**: se $n/N \leq 0.05$ (campione sotto il 5% della popolazione) la Binomiale è un'ottima approssimazione. Negli esercizi d'esame, però, $n/N$ è tipicamente grande apposta (es. $4/24$, $20/300$) proprio per **obbligare** all'uso dell'ipergeometrica: leggere sempre la modalità di campionamento nel testo.

Il quadro delle approssimazioni tra distribuzioni discrete si chiude con la [Poissoniana come limite della Binomiale](../UD3/L2%20-%20orma%20analitica%20della%20distribuzione%20di%20Poisson.md):

$$
\text{Ipergeometrica} \xrightarrow[K/N = p]{N \to \infty} \text{Binomiale} \xrightarrow[np = \mu]{n \to \infty,\ p \to 0} \text{Poissoniana}
$$

### **5. Esempio guidato (dall'appello del 16/01/2026)**

Lotto di $N = 24$ componenti con $K = 6$ difettosi; campione di $n = 4$ senza reinserimento.

##### **5.1. Identificazione**

Campionamento senza reinserimento da popolazione finita dicotomica ⇒ $X \sim$ Ipergeometrica$(N{=}24, K{=}6, n{=}4)$.

##### **5.2. Probabilità puntuale**

$$
P(X = 2) = \frac{\binom{6}{2}\binom{18}{2}}{\binom{24}{4}} = \frac{15 \times 153}{10626} = \frac{2295}{10626} \approx 0.216
$$

##### **5.3. Evento complementare**

$$
P(X \geq 1) = 1 - P(X = 0) = 1 - \frac{\binom{18}{4}}{\binom{24}{4}} = 1 - \frac{3060}{10626} \approx 0.712
$$

##### **5.4. Momenti**

Con $p = 6/24 = 1/4$:

$$
E[X] = 4 \times \frac{1}{4} = 1
\qquad
\mathrm{Var}(X) = 4 \times \frac{1}{4} \times \frac{3}{4} \times \frac{24-4}{24-1} = 0.75 \times \frac{20}{23} = \frac{15}{23} \approx 0.652
$$

### **6. Errori tipici da esame**

> ⚠️ Gli errori che costano punti, in ordine di frequenza:
>
> 1. **Usare la Binomiale** con $p = K/N$ nonostante il "senza reinserimento" nel testo: la probabilità puntuale viene sbagliata e, soprattutto, la varianza risulta gonfiata (manca il fattore di correzione).
> 2. **Sbagliare il secondo coefficiente**: al numeratore serve $\binom{N-K}{n-k}$, non $\binom{N}{n-k}$.
> 3. **Fraintendere le disuguaglianze**: "più di 1 ma meno di 4" significa $k \in \{2, 3\}$, estremi **esclusi**.
> 4. **Dimenticare che $E[X] = np$ vale comunque**: molti ricalcolano la media per via combinatoria perdendo tempo; la scorciatoia è legittima e va citata.

### **7. Dove compare negli appelli**

| Appello | Domanda | Cosa chiede |
|---|---|---|
| [15/01/2025](../../M4_Esami_svolti/esami_2025/4_esame_15_gennaio_2025_soluzione.md) | D1 | Doppia estrazione ipergeometrica + Bayes |
| [05/07/2025](../../M4_Esami_svolti/esami_2025/2_esame_5_luglio_2025_soluzione.md) | D3 | Probabilità puntuali e cumulate |
| [19/09/2025](../../M4_Esami_svolti/esami_2025/7_esame_19_settembre_2025_soluzione.md) | D1 | Puntuale, intervallo aperto, valore atteso |
| [16/01/2026](../../M4_Esami_svolti/esami_2025/8_esame_16_gennaio_2026_soluzione.md) | D1 | **Identificazione giustificata** + momenti con correzione |

### **8. Sintesi finale**

| **Proprietà** | **Formula / Valore** |
|---|---|
| **Parametri** | $N$ (popolazione), $K$ (successi), $n$ (campione) |
| **Densità** | $P(X{=}k) = \binom{K}{k}\binom{N-K}{n-k}\big/\binom{N}{n}$ |
| **Supporto** | $\max(0, n-N+K) \leq k \leq \min(n, K)$ |
| **Media** | $E[X] = nK/N$ |
| **Varianza** | $np(1-p)\,\frac{N-n}{N-1}$ con $p = K/N$ |
| **Limite** | $\to B(k \mid p, n)$ per $N \to \infty$, $K/N = p$ |
| **Trigger nel testo** | "senza reinserimento", "lotto", "popolazione finita" |
