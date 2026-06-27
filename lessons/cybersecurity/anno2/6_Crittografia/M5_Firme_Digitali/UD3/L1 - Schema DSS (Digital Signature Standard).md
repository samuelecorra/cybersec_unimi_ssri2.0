## **Lezione 1: Schema DSS (Digital Signature Standard)**

### **1. Obiettivo della lezione**

In questa lezione esaminiamo lo schema di firma digitale noto come **DSS**, cioè **Digital Signature Standard**, e l'algoritmo su cui si basa, chiamato **DSA**, cioè **Digital Signature Algorithm**.

Il DSS è uno standard proposto dal **NIST** nel 1991 come standard per la firma digitale. L'idea è analoga a quanto accaduto per il **DES** nel contesto della cifratura simmetrica: così come DES era stato standardizzato per la cifratura, DSS viene proposto come standard per la generazione e verifica di firme digitali.

Lo standard fu poi rivisto nel 1993, anche in seguito a diverse critiche ricevute nella sua prima formulazione.

Il DSS prevede l'uso di un algoritmo specifico, il **DSA**, che può essere visto come uno schema collegato alla famiglia di idee del crittosistema di **ElGamal**. A differenza di RSA, però, DSA è progettato **solo** per la firma digitale: non è pensato per cifrare messaggi né per scambiare chiavi.

> 📌 DSS è lo standard; DSA è l'algoritmo di firma digitale previsto dallo standard.

---

### **2. Caratteristiche generali del DSS**

Le caratteristiche principali del DSS sono:

- si basa sull'intrattabilità del **problema del logaritmo discreto**;
- utilizza una funzione hash della famiglia **SHA**;
- nella versione descritta dal corso, SHA produce un digest di 160 bit;
- ogni firma DSS ha lunghezza fissata pari a 320 bit;
- la firma è composta da due valori, ciascuno modulo $q$, quindi ciascuno lungo circa 160 bit.

La lunghezza fissa della firma è un punto importante. Indipendentemente dalla lunghezza del messaggio da firmare, la firma DSS è sempre:

$$
320 \text{ bit}
$$

Infatti, la firma è una coppia:

$$
(\gamma,\delta)
$$

dove sia $\gamma$ sia $\delta$ sono elementi modulo $q$, e $q$ è un primo di 160 bit.

> ✅ DSS produce firme corte e di lunghezza fissa, caratteristica utile anche in dispositivi con risorse limitate, come smart card e sistemi embedded.

---

### **3. Parametri dello schema**

L'algoritmo DSA usa una chiave pubblica e una chiave privata. Le due chiavi condividono alcuni parametri pubblici.

I parametri principali sono:

- un primo $p$;
- un primo $q$;
- un elemento $\alpha$ di ordine $q$ modulo $p$;
- un segreto $s$;
- un valore pubblico $\beta$ calcolato a partire da $s$.

Il primo parametro è:

$$
p
$$

dove $p$ è un numero primo di $L$ bit. Nel DSS classico si considera:

$$
512 \le L \le 1024
$$

con $L$ multiplo di 64.

Il secondo parametro è:

$$
q
$$

dove $q$ è un numero primo di 160 bit tale che:

$$
q \mid (p-1)
$$

cioè $q$ divide $p-1$.

Il terzo parametro pubblico è:

$$
\alpha \in \mathbb{Z}_p^*
$$

scelto in modo che abbia ordine $q$. Questo significa:

$$
\alpha^q \equiv 1 \pmod p
$$

e, più precisamente, $q$ è il più piccolo esponente positivo che riporta $\alpha$ a 1 modulo $p$.

> 📌 La condizione $q \mid (p-1)$ serve perché, nel gruppo moltiplicativo modulo $p$, possano esistere sottogruppi di ordine $q$.

---

### **4. Chiave privata e chiave pubblica**

La chiave privata di Alice contiene un numero casuale:

$$
s
$$

scelto in modo che:

$$
0 < s < q
$$

Questo valore deve rimanere segreto.

La chiave pubblica contiene invece:

$$
\beta
$$

calcolato come:

$$
\beta = \alpha^s \bmod p
$$

Quindi:

- la **chiave privata** contiene il segreto $s$;
- la **chiave pubblica** contiene $\beta$;
- i parametri $p$, $q$ e $\alpha$ sono pubblici e comuni alla descrizione della chiave.

Possiamo scrivere:

$$
\text{chiave privata} = (p,q,\alpha,s)
$$

e:

$$
\text{chiave pubblica} = (p,q,\alpha,\beta)
$$

dove:

$$
\beta = \alpha^s \bmod p
$$

> ⚠️ Dal valore pubblico $\beta$ non deve essere computazionalmente possibile ricavare $s$. Questo è esattamente un problema di logaritmo discreto: dato $\alpha$, $\beta$ e $p$, trovare $s$ tale che $\beta \equiv \alpha^s \pmod p$.

---

### **5. Ordine di un elemento**

Per capire il ruolo di $\alpha$, bisogna richiamare il concetto di **ordine** di un elemento.

Sia:

$$
\alpha \in \mathbb{Z}_n^*
$$

L'ordine di $\alpha$ è il più piccolo intero positivo $r$ tale che:

$$
\alpha^r \equiv 1 \pmod n
$$

Si scrive:

$$
\operatorname{ord}(\alpha)=r
$$

Un risultato fondamentale della teoria dei numeri, il **teorema di Lagrange**, dice che l'ordine di un elemento divide l'ordine del gruppo.

Nel gruppo:

$$
\mathbb{Z}_n^*
$$

l'ordine del gruppo è:

$$
\varphi(n)
$$

quindi:

$$
\operatorname{ord}(\alpha) \mid \varphi(n)
$$

Se $p$ è primo, allora:

$$
\varphi(p)=p-1
$$

e quindi:

$$
\operatorname{ord}(\alpha) \mid (p-1)
$$

Nel DSS si sceglie $\alpha$ in modo che:

$$
\operatorname{ord}(\alpha)=q
$$

con $q \mid (p-1)$.

---

### **6. Esempio di ordine in $\mathbb{Z}_{15}^*$**

Consideriamo:

$$
n = 15
$$

Poiché:

$$
15 = 3 \cdot 5
$$

si ha:

$$
\varphi(15) = (3-1)(5-1)=2\cdot4=8
$$

Gli elementi di:

$$
\mathbb{Z}_{15}^*
$$

sono gli interi minori di 15 e coprimi con 15:

$$
\mathbb{Z}_{15}^* = \{1,2,4,7,8,11,13,14\}
$$

Sono 8 elementi, come previsto da $\varphi(15)=8$.

Per calcolare l'ordine di un elemento, si cerca il più piccolo esponente positivo che restituisce 1 modulo 15.

Per esempio, per:

$$
\alpha = 4
$$

si ha:

$$
4^2 = 16 \equiv 1 \pmod{15}
$$

Quindi:

$$
\operatorname{ord}(4)=2
$$

Il valore 2 divide 8, coerentemente con il teorema di Lagrange.

> 💡 Questo esempio serve solo a fissare il concetto di ordine. Nel DSA si lavora con primi molto più grandi e con un sottogruppo di ordine primo $q$.

---

### **7. Esempio numerico di chiavi DSA**

Consideriamo ora l'esempio numerico del docente.

Scegliamo:

$$
p = 7879
$$

e:

$$
q = 101
$$

Si verifica che:

$$
p-1 = 7878 = 78 \cdot 101
$$

quindi:

$$
q \mid (p-1)
$$

Scegliamo poi:

$$
\alpha = 170
$$

come elemento di:

$$
\mathbb{Z}_{7879}^*
$$

di ordine $q=101$. Infatti:

$$
170^{101} \equiv 1 \pmod{7879}
$$

Alice sceglie come segreto:

$$
s = 75
$$

con:

$$
s < q
$$

Il valore pubblico corrispondente è:

$$
\beta = \alpha^s \bmod p
$$

cioè:

$$
\beta = 170^{75} \bmod 7879 = 4567
$$

Quindi:

$$
\text{chiave privata} = (7879,101,170,75)
$$

e:

$$
\text{chiave pubblica} = (7879,101,170,4567)
$$

---

### **8. Generazione della firma**

Per firmare un messaggio:

$$
M
$$

Alice deve calcolare due quantità:

$$
\gamma
$$

e:

$$
\delta
$$

La firma DSA sarà:

$$
(\gamma,\delta)
$$

Questi due valori dipendono:

- dai parametri pubblici $p$, $q$ e $\alpha$;
- dalla chiave privata $s$;
- dal digest del messaggio, calcolato con SHA;
- da un nuovo numero casuale $r$ scelto per quella firma.

> ⚠️ Il valore casuale $r$ deve essere nuovo, segreto e non riutilizzato tra firme diverse. La sicurezza pratica del DSA dipende in modo critico dalla corretta generazione di questo valore.

---

### **9. Scelta del valore casuale $r$**

Alice sceglie un intero casuale:

$$
r
$$

tale che:

$$
1 \le r \le q-1
$$

Poiché $q$ è primo, ogni valore non nullo modulo $q$ possiede inverso moltiplicativo. Quindi $r$ ammette inverso:

$$
r^{-1} \pmod q
$$

Questo inverso sarà usato nel calcolo della seconda componente della firma.

---

### **10. Calcolo di $\gamma$**

La prima componente della firma è:

$$
\gamma = (\alpha^r \bmod p) \bmod q
$$

Il calcolo avviene in due passaggi:

1. si calcola $\alpha^r$ modulo $p$;
2. si riduce il risultato modulo $q$.

Il valore $\gamma$ è quindi un intero modulo $q$, perciò ha lunghezza al massimo pari alla lunghezza di $q$, cioè 160 bit nella versione classica.

---

### **11. Calcolo di $\delta$**

La seconda componente della firma è:

$$
\delta = (\operatorname{SHA}(M) + s\gamma)r^{-1} \bmod q
$$

In questa formula:

- $\operatorname{SHA}(M)$ è il digest del messaggio;
- $s$ è la chiave privata di Alice;
- $\gamma$ è la prima componente appena calcolata;
- $r^{-1}$ è l'inverso moltiplicativo di $r$ modulo $q$.

La firma finale è:

$$
\operatorname{Sign}_{DSA}(M) = (\gamma,\delta)
$$

Dato che $\gamma$ e $\delta$ sono entrambi valori modulo $q$, e $q$ ha 160 bit, la firma ha lunghezza:

$$
160 + 160 = 320 \text{ bit}
$$

> 📌 La firma DSA non contiene il messaggio e non consente di recuperarlo: il messaggio deve essere fornito separatamente alla verifica.

---

### **12. Verifica della firma**

Per verificare una firma:

$$
(\gamma,\delta)
$$

sul messaggio:

$$
M
$$

il verificatore usa la chiave pubblica:

$$
(p,q,\alpha,\beta)
$$

La verifica calcola innanzitutto l'inverso di $\delta$ modulo $q$:

$$
\delta^{-1} \pmod q
$$

Poi calcola due valori:

$$
e' = \operatorname{SHA}(M)\delta^{-1} \bmod q
$$

e:

$$
e'' = \gamma\delta^{-1} \bmod q
$$

A questo punto si calcola:

$$
v = (\alpha^{e'}\beta^{e''} \bmod p) \bmod q
$$

La firma è accettata se:

$$
v = \gamma
$$

cioè se:

$$
\gamma = (\alpha^{e'}\beta^{e''} \bmod p) \bmod q
$$

Se l'uguaglianza non vale, la firma viene rifiutata.

---

### **13. Efficienza della generazione della firma**

Una caratteristica importante del DSA è che molte quantità usate nella firma non dipendono direttamente dal messaggio.

In particolare, una volta scelto il valore casuale $r$, si possono calcolare in anticipo:

$$
r^{-1} \bmod q
$$

e:

$$
\gamma = (\alpha^r \bmod p) \bmod q
$$

La parte costosa è soprattutto l'esponenziazione:

$$
\alpha^r \bmod p
$$

Se questa viene precomputata, quando arriva il messaggio da firmare resta da calcolare:

$$
\operatorname{SHA}(M)
$$

e poi:

$$
\delta = (\operatorname{SHA}(M)+s\gamma)r^{-1} \bmod q
$$

Nelle implementazioni ottimizzate si può quindi preparare un insieme di valori:

$$
(r,\gamma,r^{-1})
$$

da usare successivamente. Quando un messaggio deve essere firmato, si usa una terna precomputata e si calcola solo la componente dipendente dal messaggio.

> ⚠️ La precomputazione è sicura solo se ogni valore $r$ viene usato una volta sola. Riutilizzare lo stesso $r$ in due firme diverse compromette la sicurezza della chiave privata.

---

### **14. Confronto di efficienza con RSA**

La firma DSA può essere resa molto efficiente grazie alla precomputazione dei valori legati a $r$.

La verifica, invece, richiede il calcolo:

$$
\alpha^{e'}\beta^{e''} \bmod p
$$

cioè due esponenziazioni modulari e una moltiplicazione modulo $p$, seguite dalla riduzione modulo $q$.

Per questo il docente osserva che la fase di verifica può risultare più lenta della fase di generazione della firma.

Questo comportamento è diverso da quanto avviene spesso in RSA, dove l'esponente pubblico $e$ viene scelto piccolo proprio per rendere la verifica molto veloce rispetto alla firma.

> 💡 In RSA, spesso la verifica è più veloce della firma. In DSA, grazie alla precomputazione, la firma può essere molto veloce, mentre la verifica rimane computazionalmente più pesante.

---

### **15. Perché la verifica funziona**

Dimostriamo ora la correttezza della verifica.

Dalla generazione della firma sappiamo che:

$$
\delta = (\operatorname{SHA}(M)+s\gamma)r^{-1} \bmod q
$$

Moltiplicando entrambi i lati per $r$, si ottiene:

$$
r\delta \equiv \operatorname{SHA}(M)+s\gamma \pmod q
$$

Da cui:

$$
r \equiv \delta^{-1}(\operatorname{SHA}(M)+s\gamma) \pmod q
$$

Durante la verifica si calcolano:

$$
e' = \operatorname{SHA}(M)\delta^{-1} \bmod q
$$

e:

$$
e'' = \gamma\delta^{-1} \bmod q
$$

Consideriamo ora:

$$
\alpha^{e'}\beta^{e''} \pmod p
$$

Poiché:

$$
\beta = \alpha^s \bmod p
$$

si ha:

$$
\alpha^{e'}\beta^{e''} \equiv \alpha^{e'}(\alpha^s)^{e''} \pmod p
$$

quindi:

$$
\alpha^{e'}\beta^{e''} \equiv \alpha^{e'+se''} \pmod p
$$

Sostituendo le definizioni di $e'$ ed $e''$:

$$
e' + se'' \equiv \operatorname{SHA}(M)\delta^{-1} + s\gamma\delta^{-1} \pmod q
$$

Raccogliendo $\delta^{-1}$:

$$
e' + se'' \equiv \delta^{-1}(\operatorname{SHA}(M)+s\gamma) \pmod q
$$

Ma abbiamo già visto che:

$$
\delta^{-1}(\operatorname{SHA}(M)+s\gamma) \equiv r \pmod q
$$

quindi:

$$
e' + se'' \equiv r \pmod q
$$

Poiché $\alpha$ ha ordine $q$, esponenti congruenti modulo $q$ producono lo stesso valore nel sottogruppo generato da $\alpha$. Dunque:

$$
\alpha^{e'+se''} \equiv \alpha^r \pmod p
$$

Ne segue:

$$
(\alpha^{e'}\beta^{e''} \bmod p) \bmod q = (\alpha^r \bmod p) \bmod q
$$

Ma, per definizione di $\gamma$:

$$
\gamma = (\alpha^r \bmod p) \bmod q
$$

Quindi:

$$
(\alpha^{e'}\beta^{e''} \bmod p) \bmod q = \gamma
$$

La procedura di verifica recupera dunque proprio il valore $\gamma$ contenuto nella firma.

> ✅ La verifica DSA funziona perché i valori $e'$ ed $e''$ ricostruiscono, tramite la chiave pubblica, l'effetto dell'esponente casuale $r$ usato nella firma.

---

### **16. Osservazioni sul ruolo di $\gamma$**

Il test finale della verifica viene fatto sul valore:

$$
\gamma
$$

Questo valore non dipende direttamente dal messaggio. Dipende invece:

- dai parametri pubblici $p$, $q$, $\alpha$;
- dal valore casuale $r$ scelto per la firma.

Il messaggio entra nella verifica attraverso:

$$
\operatorname{SHA}(M)
$$

che contribuisce al calcolo di $e'$. La chiave privata di Alice è incorporata indirettamente nel valore pubblico:

$$
\beta = \alpha^s \bmod p
$$

e nel modo in cui $\delta$ è stato costruito.

La verifica riesce solo se tutte queste relazioni sono coerenti:

- il digest del messaggio;
- la coppia $(\gamma,\delta)$;
- la chiave pubblica $\beta$;
- i parametri pubblici $p$, $q$, $\alpha$.

---

### **17. Riepilogo**

In questa lezione abbiamo visto il funzionamento dello schema DSS/DSA.

I punti fondamentali sono:

- DSS è lo standard di firma digitale proposto dal NIST nel 1991 e rivisto nel 1993;
- DSA è l'algoritmo previsto dallo standard DSS;
- DSA è collegato alle idee di ElGamal e si basa sul problema del logaritmo discreto;
- lo schema usa una funzione hash SHA con digest di 160 bit nella versione descritta;
- ogni firma ha lunghezza fissa di 320 bit;
- i parametri pubblici sono $p$, $q$ e $\alpha$;
- $q$ divide $p-1$ e $\alpha$ ha ordine $q$ modulo $p$;
- la chiave privata è $s$;
- la chiave pubblica contiene $\beta=\alpha^s \bmod p$;
- per firmare si sceglie un valore casuale $r$ e si calcolano $\gamma$ e $\delta$;
- per verificare si calcolano $e'$, $e''$ e si controlla che il valore ricostruito coincida con $\gamma$;
- la generazione della firma può essere velocizzata precomputando $(r,\gamma,r^{-1})$;
- lo stesso valore $r$ non deve mai essere riutilizzato;
- DSA può essere usato solo per firme digitali, non per cifratura o scambio di chiavi.

> ✅ DSS/DSA è uno schema di firma a lunghezza fissa basato sul logaritmo discreto: la firma è la coppia $(\gamma,\delta)$ e la verifica controlla che tale coppia sia coerente con il messaggio, la chiave pubblica e il valore casuale usato durante la firma.

---

> 💡 **Prossimo passo per l'esame:** ora conosci le formule di firma e verifica DSA. Gli appelli 230622, 240624 e 240220 chiedono cosa succede quando $\delta=0$ nella firma prodotta, argomento da 10 punti per appello. Vai a [UD4 / L1 - DSA: firma (a,0) e caso delta=0](../UD4_Approfondimenti_Esame/L1%20-%20DSA%20-%20firma%20%28a%2C0%29%20e%20caso%20delta%3D0.md) per la dimostrazione completa della rottura della chiave privata e la risposta alla domanda "bisogna rigenerare r?".
