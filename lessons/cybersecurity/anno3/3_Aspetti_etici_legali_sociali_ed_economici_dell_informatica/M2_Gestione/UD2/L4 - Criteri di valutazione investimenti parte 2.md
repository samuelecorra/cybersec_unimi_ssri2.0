# **M2 UD2 L4 - Criteri di valutazione investimenti parte 2**

### **3.2 Metodi di valutazione a flusso di cassa non scontato (Non DCF)**

I metodi di valutazione **non DCF** si caratterizzano per il fatto di **non tenere conto del valore economico del tempo**, ossia non prevedono l’attualizzazione dei flussi di cassa. Per tale motivo, pur essendo di semplice applicazione, presentano limiti strutturali e devono essere utilizzati con cautela.

#### **3.2.1 Il criterio del Pay-back Period**

Il criterio del **tempo di pay-back** (_Pay-back Period, $T_{pb}$\_) ha l’obiettivo di determinare **quanti anni (o periodi) sono necessari affinché l’impresa rientri in possesso del capitale inizialmente investito**.

Formalmente, il tempo di pay-back è definito come il minimo valore di $T$ tale che:

$$
T_{pb} \Longleftrightarrow \sum_{t=1}^{T} FF(t) = I_0
$$

L’obiettivo fondamentale di questo indicatore è verificare che il **tempo di recupero del capitale investito** sia inferiore a un **tempo-soglia prefissato**. Di conseguenza, nel confronto tra due investimenti alternativi, si tende a privilegiare quello con **tempo di pay-back minore**.

![](imgs/Pasted%20image%2020260209223818.png)

Tuttavia, il criterio del pay-back **non è sempre adeguato**. Si consideri, ad esempio, il confronto tra due investimenti alternativi A e B con uguale investimento iniziale ma diversa distribuzione temporale dei flussi di cassa. In tale situazione, può accadere che il tempo di pay-back risulti identico per entrambi (ad esempio pari a tre anni), rendendo gli investimenti indistinguibili secondo questo criterio.

Dal punto di vista intuitivo, però, l’investimento che **anticipa maggiormente i flussi di cassa** è preferibile, soprattutto se si considera anche l’effetto dell’attualizzazione. Il criterio del pay-back, infatti, **non tiene conto della distribuzione temporale dei flussi di cassa**, ma solo del momento in cui il capitale iniziale viene recuperato.

Per ridurre tale distorsione, è possibile calcolare il pay-back anche in versione DCF, ottenendo il **tempo di pay-back modificato o attualizzato**, definito come:

$$
T_{pb} \Longleftrightarrow \sum_{t=1}^{T} \frac{FF(t)}{(1+\sigma)^t} = I_0
$$

---

#### **3.2.2 Pay-back attualizzato e suoi limiti**

Nel caso del pay-back attualizzato, i flussi di cassa attualizzati devono eguagliare il valore dell’investimento iniziale. Tuttavia, anche questo accorgimento **non elimina tutte le distorsioni** insite nell’indicatore.

È infatti possibile che, secondo il criterio del pay-back (anche nella versione attualizzata), venga preferito un investimento che recupera prima il capitale iniziale, ma che **genera un valore economico complessivamente inferiore** rispetto a un’alternativa con ritorni più elevati nel lungo periodo.

![](imgs/Pasted%20image%2020260209223934.png)

In generale, il **tempo di pay-back**, sia attualizzato sia non attualizzato:

- **non considera i flussi di cassa successivi al momento del pareggio**;
- **non misura la redditività**, ma piuttosto la **liquidità** dell’investimento.

Per questo motivo, il pay-back è utilizzabile soprattutto come indicatore di liquidità e **solo congiuntamente ad altri indicatori di redditività**, come il NPV.

---

#### **3.2.3 Il criterio del ROI (Return on Investment)**

Un secondo metodo di valutazione non DCF è rappresentato dal **Return on Investment (ROI)**. Questo indicatore è definito come il **rapporto tra una grandezza reddituale e il capitale investito**.

Tipicamente, il ROI è espresso come:

$$
ROI = \frac{\overline{MOL}}{INV_{iniziale}}
$$

dove:

- il numeratore è rappresentato dalla **media dei Margini Operativi Lordi (MOL)** previsti sull’orizzonte temporale considerato;
- il denominatore è l’**investimento iniziale**.

![](imgs/Pasted%20image%2020260209224011.png)

Ad esempio, se l’investimento iniziale è pari a 20 e il MOL medio previsto è pari a 20, il ROI risulta:

$$
ROI = \frac{20}{20} = 100%
$$

---

#### **3.2.4 ROI basato sull’investimento medio**

In alternativa, è possibile utilizzare una versione del ROI che considera non l’investimento iniziale, ma l’**investimento medio**, al fine di tener conto degli effetti dell’ammortamento.

Infatti, il valore di un macchinario acquistato per un importo $I_0$ diminuisce progressivamente nel tempo a causa dell’ammortamento, fino ad annullarsi al termine del ciclo economico dell’investimento. Se si assume una perdita di valore lineare nel tempo, il **valore medio dell’investimento** risulta pari a:

$$
\overline{INV} = \frac{INV}{2}
$$

In questo caso, l’indicatore diventa:

$$
ROI = \frac{\overline{MOL}}{\overline{INV}}
$$

Riprendendo l’esempio precedente, con un investimento iniziale pari a 20 e un investimento medio pari a 10, si ottiene:

$$
ROI = \frac{20}{10} = 200%
$$

---

### **4. Alcune osservazioni conclusive**

In generale, le imprese valutano le proposte di investimento sulla base di uno o più dei seguenti parametri:

- ritorno sull’investimento (ROI),
- periodo di pay-back,
- flussi di cassa,
- valore attuale netto (NPV o VAN).

Tuttavia, è noto da tempo – già a partire dagli anni Trenta – che **nessuno di questi criteri, preso singolarmente, è sufficiente** per valutare correttamente un investimento. Una valutazione adeguata dovrebbe considerare **congiuntamente tutti gli indicatori**, al fine di selezionare i progetti che offrono il **miglior equilibrio tra opportunità e rischio**.

Il problema principale dei processi tradizionali di valutazione degli investimenti è che spesso **non vengono affrontate due questioni fondamentali**:

- che cosa accadrà se l’investimento proposto **non produrrà i risultati attesi**? Le conseguenze saranno marginali o comporteranno danni significativi per l’impresa?
- se l’investimento avrà successo, e in particolare se i risultati **supereranno le attese**, quali saranno le conseguenze strategiche ed economiche per l’impresa?

Queste considerazioni evidenziano la necessità di affiancare ai criteri deterministici tradizionali strumenti di analisi più avanzati, capaci di tenere conto dell’incertezza e delle implicazioni strategiche delle decisioni di investimento.