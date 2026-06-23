# **Lezione 5: Le difficoltà nel combattere lo spam**

---

### **1. Perché il blacklisting non basta**

Ricapitolando quanto visto nella lezione precedente, gli spammer riescono ad aggirare il problema del relay — nonostante i server SMTP siano oggi configurati più rigorosamente e cerchino di fare blacklisting su chi manda messaggi — attraverso due tecniche fondamentali:

1. **No relay**: installare un proprio server SMTP su una macchina con indirizzo IP dinamico, sempre diverso.
2. **Open proxy**: utilizzare un proxy per l'invio dei messaggi SMTP; poiché il proxy trasmette anche traffico legittimo, non è possibile escluderlo totalmente con il blacklisting.

> ⚠️ In presenza di open proxy e di indirizzamento dinamico (no relay), **un blacklisting generalizzato è impossibile**: i server di destinazione non riescono a bloccare gli spammer senza rischiare di bloccare anche traffico legittimo.

---

### **2. Honeypot: trappole per spammer**

Una tecnica complementare è quella degli **honeypot** (letteralmente "barattoli di miele"): finti server SMTP e **finte caselle di posta** creati appositamente per attirare gli spammer, in modo da:

- catturare gli **indirizzi IP dei server SMTP** usati dagli spammer;
- rendere agli spammer la vita più difficile in termini di sforzo, obbligandoli a "sprecare" messaggi su caselle inesistenti.

> ⚠️ L'honeypot è però una tecnica per identificare il **singolo spammer dopo il fatto**, non per impedire la propagazione dello spam in tempo reale. Per questo motivo deve essere complementata con tecniche diverse.

---

### **3. Il filtraggio basato sul contenuto**

Poiché il blacklisting sull'identità del mittente si rivelò inefficace, nella **seconda metà degli anni 2000** prese piede un approccio radicalmente diverso: identificare lo spam non per *chi lo invia*, ma per *cosa contiene*.

#### **Filtraggio collaborativo per hash**

Lo spammer invia lo stesso messaggio a milioni di destinatari. Quando un utente o un amministratore riconosce un messaggio di spam, viene calcolato l'**hash** (una codifica abbreviata) di quel messaggio e inviato a tutti gli altri server di posta — un po' come si fa con i database degli antivirus. I server SMTP che ricevono un messaggio possono calcolarne l'hash, confrontarlo con gli hash noti di messaggi spam e scartarlo se coincide.

---

### **4. SpamAssassin**

Tra i programmi più noti di filtraggio dello spam basato sul contenuto c'è **SpamAssassin**, un software **open source** che esegue una serie di analisi sul testo del messaggio e utilizza **due sorgenti di giudizio** in combinazione:

1. **Blacklisting automatico**: scarica automaticamente dalla rete delle liste di MTA e server di posta non affidabili e confronta l'indirizzo IP mittente con queste liste.
2. **Analisi del contenuto**: confronta il messaggio con gli hash di messaggi spam disponibili sulla rete collaborativa **Vipul's Razor**.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Vipul's Razor** è una rete distribuita e collaborativa di identificazione dello spam grazie alla quale è stato costruito un catalogo costantemente aggiornato dei messaggi di spam in circolazione. SpamAssassin si basa su questa rete per gli esempi di spam.

Combinando entrambi i criteri, SpamAssassin assegna a ogni messaggio una **probabilità di essere spam** e consente l'esecuzione di filtri che scartano i messaggi al di sopra di una certa soglia.

Ecco un esempio dell'intestazione aggiunta da SpamAssassin a un messaggio analizzato:

```
spamassassin: 3.2.1. perlscan: 1.25st.
Clear:RC:0(159.149.10.22):SA:0(3.8/6.0):.
Processed in 2.340732 secs); 09 Dec 2008 00:00:03
-0000 X-Spam-Status: No, hits=3.8 required=6.0
```

Il campo `hits=3.8` indica il punteggio spam del messaggio; `required=6.0` è la soglia. Poiché 3.8 < 6.0, il messaggio **non è classificato come spam**.

> 💡 SpamAssassin può essere installato **anche sui client di posta**, non solo sui server. In questo modo si libera il server SMTP dallo sforzo di identificare lo spam: lo spam viene recapitato all'utente, ma è il suo client a mostrargli solo i messaggi al di sotto della soglia di probabilità.

---

### **5. Il filtro bayesiano**

Una variante importante della tecnica basata sulla similarità è il **filtro bayesiano**, proposto da **Paul Graham**. Invece di confrontare il messaggio con un campione noto, esamina il **vocabolario usato** nel messaggio. È anche una buona occasione per ripassare il teorema di Bayes.

#### **Impostazione del problema**

- **Osservazione O**: un messaggio contiene una certa parola (es. "sex").
- **Ipotesi H**: il messaggio è spam.

Vogliamo conoscere $P(H|O)$: la probabilità che il messaggio sia spam sapendo che contiene la parola "sex". Questa probabilità non è facile da stimare direttamente, ma possiamo ricavarla per via bayesiana.

#### **Formula di Bayes applicata**

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

$$P(H|O) = \frac{P(O|H) \cdot P(H)}{P(O)}$$

dove:

- $P(O|H)$ = probabilità che uno **spam** contenga la parola "sex" → si stima guardando la cartella **Junk Mail** dell'utente e contando quanti messaggi spam contengono quella parola sul totale dei messaggi spam.
- $P(H)$ = probabilità generale che **un messaggio qualsiasi sia spam** → si stima come rapporto tra la dimensione della cartella Junk Mail e il totale dei messaggi ricevuti.
- $P(O)$ = probabilità che **un messaggio qualsiasi** (spam o no) contenga la parola "sex" → si stima contando quanti messaggi in totale (spam o legittimi) ricevuti dall'utente contengono quella parola.

#### **Personalizzazione e precisione**

> 📌 Tutte e tre le stime vengono calcolate **sulle caselle di posta dello specifico utente**: sono stime personalizzate, non globali. Questo consente di ottenere un classificatore ad alta precisione adattato alle abitudini di ricezione di quel singolo utente.

Le esperienze di Graham mostrano che il filtro **perde solo 5 messaggi di spam ogni 1000**, raggiungendo già dalle prime implementazioni una precisione **superiore al 95%**.

---

### **6. Le contromisure degli spammer**

#### **a. List splitting**

Gli spammer hanno intuito la debolezza del filtraggio per hash: se il messaggio viene leggermente modificato, l'hash cambia e il filtro non lo riconosce. Usano quindi degli **scrambler automatici** — programmi che variano automaticamente il messaggio usando la tecnica del **list splitting**: rendere ogni copia del messaggio leggermente diversa per ogni destinatario.

> 💡 Contro questa tecnica il filtro bayesiano è più robusto del filtraggio per hash: anche se il messaggio viene scramblato, **le parole usate non cambiano molto**, e quindi il classificatore bayesiano continua a identificare lo spam correttamente, mentre l'hash sarebbe già diverso.

#### **b. Spam grafico**

Gli spammer hanno poi intuito il pericolo delle tecniche bayesiane e hanno iniziato a usare le cosiddette **tecniche grafiche**: il messaggio viene nascosto all'interno di un'immagine (il messaggio è un file HTML che contiene un'immagine incorporata in cui compaiono le parole).

Ci si potrebbe chiedere perché non si applichi l'OCR (riconoscimento ottico dei caratteri) per estrarre il testo dall'immagine e poi applicare il filtro bayesiano. La risposta è duplice:

- **Non è sempre facile**: oggi moltissimi siti web richiedono agli utenti di digitare parole graficamente deformate (CAPTCHA), proprio perché gli algoritmi di riconoscimento dei caratteri disponibili per i computer non sono bravi quanto gli esseri umani a leggere testo da immagini perturbate.
- **È troppo lento**: anche quando è possibile, il riconoscimento di caratteri su immagini richiede così tanto tempo che il processamento di un singolo messaggio diventa incompatibile con i grandi volumi di posta che i server SMTP di grandi dimensioni devono gestire.

> ⚠️ Lo spam grafico rimane dunque molto difficile da gestire: un anti-spam grafico incontra notevoli difficoltà prestazionali a gestire le moli di posta che i grandi server di posta devono smistare.

---

### **7. Difese contro la raccolta di indirizzi (harvesting)**

Un'altra linea di difesa efficace — che agisce a monte — è rendere più **difficile e costosa** la raccolta degli indirizzi email da parte degli spammer.

Gli indirizzi dei server SMTP sono facilmente reperibili su Internet tramite i record MX del DNS; ma gli indirizzi dei **destinatari** non lo sono altrettanto: hanno un **costo di mercato** di alcuni dollari per indirizzo verificato. Se rendiamo più costosa la raccolta degli indirizzi, lo spammer — come qualsiasi altro fenomeno regolato da interessi economici — troverà meno conveniente organizzare la propria campagna.

Le tecniche per rendere difficile l'**harvesting** automatico degli indirizzi sono:

#### **a. Offuscamento testuale**

Scrivere l'indirizzo in forma non standard (`at` al posto di `@`, `dot` al posto di `.`). Non è sufficiente: gli harvester moderni riconoscono facilmente queste sostituzioni.

#### **b. Codifica ASCII carattere per carattere**

Scrivere direttamente i codici ASCII dei caratteri che compongono l'indirizzo:

```
someone@example.com →
&#115;&#111;&#109;&#101;&#111;&#110;&#101;&#64;&#101;
&#120;
&#97;&#109;&#112;&#108;&#101;&#46;&#99;&#111;&#109;
```

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Anche questa tecnica è però facilmente preda degli harvester: ogni carattere corrisponde esattamente a un codice secondo una tabella ASCII ben nota, quindi è banale scrivere un programma che la decodifichi.

#### **c. Tecniche crittografiche**

Le soluzioni più robuste prevedono la **cifratura dell'indirizzo email**: l'indirizzo viene cifrato e pubblicato in forma crittata; viene decifrato da uno script **solo al momento in cui viene effettivamente usato**. In questo modo l'indirizzo non compare mai in chiaro nella pagina.

> 💡 Il compito qui non è impedire a un essere umano di decifrarlo, ma rendere difficile l'applicazione di tecniche di raccolta automatica. Siccome il decrittaggio — sia pure con chiave pubblica nota a tutti — avviene solo nel momento d'uso, si rende più costoso per lo spammer gestire la raccolta di indirizzi in forma crittata, poi decrittarli prima dell'invio, e quindi si rende più difficile e costosa la costruzione di grandi campagne di spam.

---

### **8. Conclusione**

La lotta allo spam è un **gioco a somma zero**: ogni nuova difesa genera una nuova strategia di attacco. I sistemi moderni combinano più livelli di protezione — filtri bayesiani, reti collaborative come SpamAssassin e Vipul's Razor, protezione degli indirizzi, autenticazioni DNS come SPF e DKIM — ma lo spam rimane una delle **principali sfide aperte** della sicurezza Internet, simbolo di come problemi strutturali di protocollo possano generare conseguenze globali.
