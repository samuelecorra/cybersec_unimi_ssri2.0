# **M9 UD1 Lezione 2 - Definizione rigorosa, derivate laterali e notazioni**

### **1. Obiettivo della lezione**

La Lezione 1 ha costruito l'idea; qui la fissiamo con la precisione necessaria per il resto del modulo: definizione di derivata con le sue ipotesi sul punto, derivate laterali, derivata infinita, funzione derivata e le notazioni in uso.

---
### **2. Definizione di derivata in un punto**

**Definizione.** Sia $f:A\to\mathbb{R}$ e sia $x_0\in A$ un punto **interno** al dominio (esiste un intorno $U_\delta(x_0)\subseteq A$; più in generale basta che $x_0\in A$ sia di accumulazione bilaterale per $A$). Si dice che $f$ è **derivabile in $x_0$** se esiste **finito** il limite

$$
f'(x_0) = \lim_{h\to 0}\frac{f(x_0+h)-f(x_0)}{h}
= \lim_{x\to x_0}\frac{f(x)-f(x_0)}{x-x_0}
$$

e il valore del limite si chiama **derivata di $f$ in $x_0$**.

Anatomia della definizione, pezzo per pezzo:

- il rapporto incrementale è definito per $h\neq 0$ con $x_0+h\in A$: il suo dominio è un **intorno puntato** di $0$, ed è esattamente la situazione dei limiti di M08;
- serve che $f(x_0)$ **esista**: la derivata confronta i valori vicini con il valore nel punto. Non ha alcun senso chiedersi se $\frac 1x$ è derivabile in $0$: lì la funzione non c'è (coerenza con la continuità, M08/UD5);
- il limite deve essere **finito**: se il rapporto incrementale tende a $+\infty$ o $-\infty$ si parla di **derivata infinita**, ma la funzione **non è derivabile** in quel punto (punto 4);
- $x_0$ deve accumulare da **entrambi** i lati perché il limite sia bilaterale; ai bordi del dominio si usano le derivate laterali (punto 3).

---
### **3. Derivate laterali**

**Definizione.** Si chiamano **derivata destra** e **derivata sinistra** di $f$ in $x_0$ i limiti laterali (quando esistono finiti):

$$
f'_+(x_0) = \lim_{h\to 0^+}\frac{f(x_0+h)-f(x_0)}{h},
\qquad
f'_-(x_0) = \lim_{h\to 0^-}\frac{f(x_0+h)-f(x_0)}{h}
$$

**Teorema (caratterizzazione).** $f$ è derivabile in un punto interno $x_0$ **se e solo se** entrambe le derivate laterali esistono finite e coincidono; in tal caso $f'(x_0)=f'_+(x_0)=f'_-(x_0)$.

*Dimostrazione.* È il teorema "limite bilaterale $\iff$ laterali uguali" di M08 (UD1/L3C) applicato al rapporto incrementale come funzione di $h$. $\square$

Esempio guida: $f(x)=|x|$ in $0$. Rapporto incrementale: $\frac{|h|}{h}$, cioè $1$ per $h>0$ e $-1$ per $h<0$: $f'_+(0)=1\neq -1=f'_-(0)$. Le laterali esistono finite ma diverse: $|x|$ **non è derivabile** in $0$ (punto angoloso, UD4/L1), pur essendo continua.

**Bordi del dominio.** Per $f:[a,b]\to\mathbb{R}$, in $a$ ha senso solo $f'_+(a)$ e in $b$ solo $f'_-(b)$: si parla di derivabilità **laterale** agli estremi, e "derivabile su $[a,b]$" significa derivabile nei punti interni più derivabile lateralmente ai bordi. Questa distinzione sarà cruciale nei teoremi della UD5 (dove le ipotesi chiedono derivabilità solo su $(a,b)$) e nel teorema di Fermat (che **non** vale ai bordi).

---
### **4. Derivata infinita e tangente verticale**

Se il rapporto incrementale tende a $+\infty$ (o $-\infty$) diciamo che $f$ ha **derivata infinita** in $x_0$. Geometricamente il grafico ha **tangente verticale** $x=x_0$; analiticamente però la derivabilità **manca**, perché la definizione esige un limite finito.

Esempio: $f(x)=\sqrt[3]{x}$ in $0$:

$$
\frac{f(h)-f(0)}{h} = \frac{h^{1/3}}{h} = \frac{1}{h^{2/3}} \xrightarrow[h\to 0]{} +\infty
$$

La retta verticale non ha coefficiente angolare: nessun numero reale può rappresentare quella pendenza, e infatti la definizione la esclude.

> ⚠️ "Derivata infinita" e "derivata non esistente" non sono sinonimi: nel primo caso il limite esiste in $\overline{\mathbb{R}}$ ma non è un numero (tangente verticale); nel secondo il limite può non esistere affatto (angolo, oscillazione). La classificazione completa è nella UD4, Lezione 1.

---
### **5. Funzione derivata e derivate di ordine superiore**

Sia $A'\subseteq A$ l'insieme dei punti in cui $f$ è derivabile. La **funzione derivata** è

$$
f':A'\to\mathbb{R},\qquad x\mapsto f'(x)
$$

Attenzione alla distinzione tra i due livelli: $f'(x_0)$ è **un numero** (pendenza in un punto); $f'$ è **una funzione** (la legge che a ogni punto associa la pendenza). Molti errori nascono dal confonderli — ad esempio "derivo e poi sostituisco" e "sostituisco e poi derivo" danno risultati diversi: derivare $f(2)$ (una costante!) dà $0$, non $f'(2)$.

Se $f'$ è a sua volta derivabile si definisce la **derivata seconda** $f''=(f')'$, e per ricorrenza $f^{(n)}$. Il quadro completo (classi $C^k$, esempi di funzioni derivabili con derivata discontinua) è nella UD3, Lezione 5.

---
### **6. Le notazioni della derivata**

| Notazione | Nome | Uso tipico |
|---|---|---|
| $f'(x)$, $f''(x)$, $f^{(n)}(x)$ | Lagrange | notazione **principale** di questo modulo |
| $\dfrac{df}{dx}(x_0)$, $\dfrac{dy}{dx}\Big\vert_{x=x_0}$ | Leibniz | catena, inversa, cambi di variabile |
| $Df(x)$, $D^n f(x)$ | operatore | contesti in cui la derivazione è vista come operatore lineare |
| $\dot{x}(t)$, $\ddot{x}(t)$ | Newton | **solo** per derivate rispetto al tempo (fisica) |

> ⚠️ La scrittura di Leibniz $\frac{dy}{dx}$ **non è una frazione ordinaria**: è un simbolo unico per il limite del rapporto $\frac{\Delta y}{\Delta x}$. Alcune manipolazioni "da frazione" danno formule corrette (catena: $\frac{dy}{dx}=\frac{dy}{du}\frac{du}{dx}$; inversa: $\frac{dx}{dy}=1/\frac{dy}{dx}$) — ma sono corrette perché **esistono teoremi** che le dimostrano (UD2/L5 e UD2/L6), non perché "si semplifica il $du$". Usare la semplificazione come dimostrazione è un errore concettuale; usarla come regola mnemonica, dopo aver visto i teoremi, è legittimo.

---
### **7. Prime verifiche complete con la definizione**

**Esempio 1.** $f(x)=x^2$ in $x_0=3$, con la forma "con $x$":

$$
\lim_{x\to 3}\frac{x^2-9}{x-3} = \lim_{x\to 3}(x+3) = 6
$$

(la semplificazione è lecita per $x\neq 3$: carattere locale del limite, M08). Quindi $f'(3)=6$.

**Esempio 2.** $f(x)=|x-2|$ in $x_0=2$: rapporto $\frac{|h|}{h}$, laterali $\pm 1$: non derivabile, angolo in $2$.

**Esempio 3.** $f(x)=\sqrt{x}$ in $x_0=0$ (bordo): solo la derivata destra ha senso, $\frac{\sqrt h}{h}=\frac{1}{\sqrt h}\to+\infty$: derivata destra infinita, tangente verticale, non derivabile in $0$.

---
### **8. Errori frequenti**

- ❌ Scrivere "$f'(x_0)$" per un punto fuori dal dominio, o in un punto isolato (non c'è intorno su cui fare il limite).
- ❌ Dimenticare la richiesta di **finitezza** e trattare la derivata infinita come derivabilità.
- ❌ Confondere il rapporto incrementale (funzione di $h$) con la derivata (il suo limite).
- ❌ Concludere la derivabilità da una sola derivata laterale in un punto interno.
- ❌ Usare $\frac{dy}{dx}$ come frazione per "dimostrare" identità.

---
### **9. Riepilogo**

> ✅ Derivata $=$ limite **finito** del rapporto incrementale, nelle due forme equivalenti ($h$ e $x-x_0$), in un punto del dominio che accumula bilateralmente; derivabile $\iff$ laterali finite e uguali (teorema via M08); ai bordi solo la laterale interna; derivata infinita $=$ tangente verticale ma **non** derivabilità; $f'(x_0)$ numero contro $f'$ funzione; notazione principale di Lagrange, Leibniz per catena/inversa con l'avvertenza anti-frazione, Newton solo per il tempo.
