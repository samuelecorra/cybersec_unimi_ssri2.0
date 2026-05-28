# **M4 UD3 Lezione 5 - Coordinamento distribuito tra processi (parte 1)**

---

### **1. Introduzione**

Questa lezione introduce il problema del **coordinamento tra processi** in un ambiente distribuito.

In particolare, si studia come ordinare gli eventi e come usare questo ordinamento per realizzare:

- sincronizzazione tra processi;
- mutua esclusione;
- atomicita' delle attivita';
- gestione della concorrenza.

La sincronizzazione distribuita generalizza i concetti gia' visti nel caso di una singola macchina, ma introduce difficolta' aggiuntive.

> 📌 In un sistema distribuito il coordinamento dipende dalla capacita' di costruire una visione coerente dell'ordine degli eventi.

---

### **2. Problema dell'ordinamento degli eventi**

La soluzione ideale sarebbe creare un **ordinamento totale** di tutti gli eventi del sistema distribuito.

Un ordinamento totale permetterebbe di stabilire, per qualunque coppia di eventi, quale evento e' avvenuto prima.

Questo pero' non e' facilmente realizzabile in un sistema distribuito, perche' mancano due strumenti fisici fondamentali:

- un orologio comune;
- una memoria comune.

Senza un orologio unico non esiste un tempo assoluto condiviso.

Senza una memoria comune non esiste un punto centrale in cui osservare direttamente lo stato globale.

> ⚠️ In un sistema distribuito non si puo' associare facilmente a ogni evento un istante assoluto valido per tutto il sistema.

---

### **3. Ordinamento parziale**

Poiche' l'ordinamento totale non e' sempre disponibile, ci si accontenta di un **ordinamento parziale**.

Un ordinamento parziale stabilisce l'ordine solo tra eventi che hanno una relazione causale o logica.

Gli eventi non confrontabili sono considerati concorrenti.

Questa idea permette di costruire una visione dell'evoluzione globale del sistema senza richiedere un tempo assoluto.

---

### **4. Relazione "accaduto prima"**

La relazione **accaduto prima** viene introdotta per creare un ordinamento parziale degli eventi.

Si indica con:

$$
A \rightarrow B
$$

e significa che l'evento \(A\) e' accaduto prima dell'evento \(B\).

Questa relazione rappresenta un vincolo causale o logico tra eventi.

---

### **5. Regole della relazione**

La relazione "accaduto prima" viene definita con tre regole fondamentali.

#### **5.1. Eventi nello stesso processo**

Se \(A\) e \(B\) sono due eventi dello stesso processo e \(A\) avviene prima di \(B\), allora:

$$
A \rightarrow B
$$

L'ordine locale del processo definisce quindi una relazione di precedenza.

#### **5.2. Invio e ricezione di un messaggio**

Se \(A\) e' l'evento di trasmissione di un messaggio e \(B\) e' l'evento di ricezione dello stesso messaggio, allora:

$$
A \rightarrow B
$$

La ricezione non puo' avvenire prima dell'invio.

#### **5.3. Transitivita'**

Se:

$$
A \rightarrow B
$$

e:

$$
B \rightarrow C
$$

allora:

$$
A \rightarrow C
$$

La relazione e' quindi transitiva.

---

### **6. Eventi concorrenti**

La relazione "accaduto prima" non e' riflessiva.

Un evento non e' in relazione di precedenza con se stesso.

Inoltre, se due eventi non sono collegati dalla relazione, vengono considerati **concorrenti**.

Due eventi concorrenti:

- non hanno un ordine causale noto;
- non si influenzano direttamente;
- possono essere visti come indipendenti rispetto alla relazione di precedenza.

Se invece due eventi sono in relazione "accaduto prima", allora possono influenzarsi o almeno esiste un ordine che deve essere rispettato.

> 📌 Gli eventi concorrenti non sono ordinabili tramite causalita': nessuno dei due e' necessariamente prima dell'altro.

---

### **7. Esempio di ordinamento parziale**

Si considerino tre processi \(P\), \(Q\) e \(R\), ciascuno con propri eventi.

All'interno dello stesso processo, gli eventi sono ordinati localmente:

$$
P_0 \rightarrow P_1 \rightarrow P_2
$$

Non e' detto pero' che esista una relazione tra \(P_0\) e \(Q_0\), oppure tra \(P_1\) e \(Q_1\).

Se pero' un messaggio stabilisce che:

$$
P_1 \rightarrow Q_2
$$

e nel processo \(Q\):

$$
Q_2 \rightarrow Q_3
$$

e poi un altro messaggio stabilisce:

$$
Q_3 \rightarrow R_4
$$

allora per transitivita':

$$
P_1 \rightarrow R_4
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 Anche senza orologio globale, la propagazione dei messaggi permette di costruire vincoli di ordine tra eventi su macchine diverse.

---

### **8. Marche di tempo e orologi logici**

Per ottenere un ordinamento globale coerente a partire da ordinamenti parziali, si introducono:

- marche di tempo;
- orologi logici.

Un **orologio logico** non misura necessariamente il tempo fisico reale.

Serve a numerare gli eventi in modo coerente con la relazione "accaduto prima".

L'orologio logico deve evolvere in modo monotono, cioe' deve aumentare nel tempo.

---

### **9. Aggiornamento degli orologi logici**

Quando un processo genera un evento locale, incrementa il proprio orologio logico.

Quando invia un messaggio, allega al messaggio la propria marca di tempo.

Quando un processo riceve un messaggio con marca di tempo \(N\), aggiorna il proprio orologio a un valore maggiore di \(N\).

Una regola tipica e':

$$
C_i \leftarrow \max(C_i, N) + 1
$$

In questo modo, l'evento di ricezione avra' una marca maggiore dell'evento di invio.

> ✅ L'orologio logico viene forzato ad avanzare quando riceve informazioni da un altro processo, cosi' da rispettare la causalita'.

---

### **10. Generazione di marche univoche**

Per usare le marche di tempo nel sistema distribuito, bisogna garantire che siano univoche.

Esistono due soluzioni:

- generazione centralizzata;
- generazione distribuita.

---

### **11. Generazione centralizzata**

Nella soluzione centralizzata, un unico processo e' autorizzato a generare marche di tempo univoche per tutto il sistema distribuito.

Vantaggi:

- semplicita';
- unicita' garantita;
- visione unica dell'evoluzione delle marche.

Svantaggi:

- collo di bottiglia;
- rallentamento perche' tutti devono richiedere la marca al generatore;
- scarsa tolleranza ai guasti;
- problemi se il generatore diventa irraggiungibile.

> ⚠️ La centralizzazione semplifica l'unicita', ma introduce un singolo punto critico.

---

### **12. Generazione distribuita**

Nella soluzione distribuita, ogni macchina genera localmente la propria marca.

Per renderla univoca a livello globale, si compone:

$$
\text{marca globale} = (\text{marca locale}, \text{identificatore macchina})
$$

La marca locale puo' essere basata su:

- un orologio fisico locale;
- un orologio logico locale.

Gli orologi fisici delle macchine possono avere velocita' diverse, quindi non sono perfettamente sincronizzati.

Per questo si preferisce usare orologi logici, sincronizzati indirettamente tramite le interazioni tra processi.

> 📌 La coppia marca locale e identificatore della macchina rende univoca la marca nel sistema distribuito.

---

### **13. Mutua esclusione distribuita**

Il problema della mutua esclusione consiste nel garantire che un solo processo alla volta entri in una sezione critica.

In ambiente distribuito si possono usare:

- metodo centralizzato;
- metodo distribuito;
- passaggio del token.

---

### **14. Metodo centralizzato**

Nel metodo centralizzato esiste un processo coordinatore.

Il coordinatore:

- riceve le richieste di ingresso in sezione critica;
- decide quale processo puo' entrare;
- mantiene una coda dei processi in attesa;
- concede l'accesso a un processo alla volta.

Quando un processo vuole entrare nella sezione critica, invia una richiesta al coordinatore.

Il coordinatore risponde concedendo o ritardando l'accesso.

Vantaggi:

- semplice da realizzare;
- simile alla gestione centralizzata su singola macchina.

Svantaggi:

- collo di bottiglia;
- prestazioni limitate;
- scarsa tolleranza ai guasti;
- vulnerabilita' se il coordinatore muore o diventa irraggiungibile.

---

### **15. Metodo distribuito**

Nel metodo distribuito non esiste un coordinatore unico.

Quando un processo \(P\) vuole entrare nella sezione critica:

1. genera una marca di tempo;
2. invia la richiesta a tutti i processi coinvolti nella stessa sezione critica;
3. attende le risposte.

Quando un processo \(Q\) riceve la richiesta di \(P\), decide come rispondere in base al proprio stato.

#### **15.1. Q e' gia' in sezione critica**

Se \(Q\) e' gia' nella sezione critica, ritarda la risposta.

In questo modo impedisce a \(P\) di entrare finche' la sezione critica e' occupata.

#### **15.2. Q non vuole entrare**

Se \(Q\) non e' in sezione critica e non vuole entrarvi, risponde subito concedendo a \(P\) il permesso.

#### **15.3. Q vuole entrare ma non e' ancora entrato**

Se \(Q\) vuole entrare ma non e' ancora entrato, confronta la propria marca di tempo con quella di \(P\).

Se la marca di \(Q\) e' maggiore di quella di \(P\), allora \(P\) ha fatto richiesta prima e \(Q\) risponde subito.

Se la marca di \(Q\) e' minore, allora \(Q\) ha priorita' e ritarda la risposta a \(P\).

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ Il metodo distribuito usa le marche di tempo per decidere quale richiesta ha precedenza.

---

### **16. Proprieta' del metodo distribuito**

Il metodo distribuito consente una soluzione:

- priva di starvation;
- priva di deadlock;
- con buona tolleranza ai guasti rispetto alla centralizzazione;
- non dipendente da un unico coordinatore.

Il costo e' un numero maggiore di messaggi, perche' ogni richiesta deve essere comunicata ai processi coinvolti.

---

### **17. Passaggio del token**

Un'altra tecnica e' il **passaggio del token**.

I processi sono organizzati in un anello logico.

Il token rappresenta l'autorizzazione a entrare nella sezione critica.

Quando un processo riceve il token:

- se vuole entrare nella sezione critica, trattiene il token e accede alla risorsa;
- quando termina, passa il token al processo successivo;
- se non vuole entrare, passa subito il token al processo successivo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Nel metodo a token, il possesso del token equivale al permesso di usare la sezione critica.

---

### **18. Sintesi**

| Aspetto | Descrizione |
|---|---|
| **Problema principale** | Coordinare processi senza orologio e memoria comuni |
| **Ordinamento ideale** | Ordinamento totale degli eventi |
| **Soluzione pratica** | Ordinamento parziale tramite relazione "accaduto prima" |
| **Eventi concorrenti** | Eventi non collegati da relazione causale |
| **Orologi logici** | Contatori monotoni aggiornati con le comunicazioni |
| **Marca centralizzata** | Un generatore unico produce marche globali |
| **Marca distribuita** | Marca locale + identificatore della macchina |
| **Mutua esclusione centralizzata** | Coordinatore unico gestisce la sezione critica |
| **Mutua esclusione distribuita** | I processi decidono tramite timestamp e risposte |
| **Token** | Autorizzazione circolante in un anello logico |

---

### **19. Conclusione**

Il coordinamento distribuito richiede di ordinare gli eventi in assenza di un orologio globale e di una memoria comune.

La relazione "accaduto prima" permette di costruire un ordinamento parziale basato su ordine locale, invio/ricezione dei messaggi e transitivita'.

Le marche di tempo e gli orologi logici consentono di usare questo ordinamento per realizzare meccanismi di sincronizzazione.

La mutua esclusione puo' essere realizzata con coordinatore centralizzato, algoritmi distribuiti basati su timestamp o passaggio del token.
