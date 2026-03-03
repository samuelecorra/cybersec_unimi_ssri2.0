# **Lezione 2: Il Contratto nel Software Object Oriented — JML**

### 1. Introduzione al Design by Contract nei programmi Java

In questa lezione viene approfondita l’applicazione pratica del Design by Contract (DbC) nel contesto della programmazione orientata agli oggetti, utilizzando il linguaggio Java e il formalismo JML (Java Modeling Language).

JML rappresenta un insieme di strumenti e tecniche che permettono di integrare direttamente nei programmi Java la specifica formale del comportamento tramite contratti. L’obiettivo è rendere esplicite le precondizioni, le postcondizioni e gli invarianti delle classi, mantenendo una stretta coerenza tra specifica e implementazione.

La sintassi adottata da JML è simile a quella utilizzata da altri strumenti DbC per linguaggi orientati agli oggetti, facilitando la comprensione e l’adozione della metodologia.

---

### 2. Sintassi di base di JML

Le annotazioni JML vengono inserite direttamente nei file `.java` sotto forma di commenti speciali, utilizzando due notazioni principali:

- `/*@ ... @*/`
    
- `//@`
    

Le condizioni sono espresse come espressioni booleane Java arricchite con operatori aggiuntivi specifici di JML, tra cui:

- `\result` — valore restituito da un metodo;
    
- `\old` — valore di una variabile prima dell’esecuzione del metodo;
    
- `\forall` — quantificatore universale;
    
- `==>` — implicazione logica.
    

Le principali parole chiave utilizzate sono:

- `requires` per le precondizioni;
    
- `ensures` per le postcondizioni;
    
- `invariant` per gli invarianti.
    

Un aspetto importante è che il file Java contenente annotazioni JML rimane comunque un file Java valido e compilabile.

---

### 3. Esempio: classe Account senza contratti

Viene presentato un esempio di classe `Account` che rappresenta un conto bancario.

La classe contiene:

- una variabile `balance` per il saldo;
    
- una costante `minBalance` che rappresenta il saldo minimo;
    
- un costruttore per la creazione del conto;
    
- un metodo `withdraw` per il prelievo;
    
- un metodo `deposit` per il deposito.
    

In questa fase iniziale il codice non contiene contratti, mostrando quindi la differenza tra implementazione pura e implementazione con DbC.

---

### 4. Contratto per il costruttore Account

Nel caso della creazione di un conto bancario, il contratto prevede:

- una precondizione: l’importo iniziale deve essere maggiore o uguale al saldo minimo;
    
- una postcondizione: il saldo del conto deve essere uguale all’importo iniziale.
    

Questo viene espresso in JML mediante:

- `requires initialAmount >= minBalance;`
    
- `ensures balance == initialAmount;`
    

Il contratto rende esplicito cosa è richiesto al cliente e cosa garantisce il costruttore.

---

### 5. Contratto per l’operazione di deposito

Per il metodo `deposit`, il contratto stabilisce:

- precondizione: l’importo deve essere positivo;
    
- postcondizione: il saldo aumenta dell’importo depositato.
    

La postcondizione utilizza l’operatore `\old` per riferirsi al valore del saldo prima dell’esecuzione del metodo:

`balance == \old(balance) + amount`

Questo consente di specificare il cambiamento di stato senza descrivere l’implementazione.

---

### 6. Contratto per l’operazione di prelievo

Per il metodo `withdraw`, il contratto stabilisce:

- precondizione: l’importo deve essere positivo e non deve violare il saldo minimo;
    
- postcondizione: il saldo diminuisce dell’importo e il valore restituito è il nuovo saldo.
    

La postcondizione utilizza:

- `\old(balance)` per il valore precedente;
    
- `\result` per il valore restituito dal metodo.
    

Questo esempio evidenzia chiaramente la separazione tra responsabilità del cliente e del fornitore.

---

### 7. Obblighi e benefici tra cliente e fornitore

Il contratto del metodo `withdraw` evidenzia la relazione tra obblighi e benefici:

- il cliente deve garantire che l’importo sia valido;
    
- il fornitore garantisce l’aggiornamento corretto del saldo;
    
- il cliente beneficia dell’informazione aggiornata restituita dal metodo.
    

Questo schema riflette esattamente il modello teorico del Design by Contract.

---

### 8. Differenza tra implementazione e specifica

Una distinzione fondamentale viene evidenziata tra:

- implementazione (come viene fatto);
    
- specifica (cosa viene garantito).
    

Ad esempio:

- istruzione: `balance = balance - amount`
    
- postcondizione: `balance == \old(balance) - amount`
    

La specifica descrive la proprietà che deve valere, indipendentemente dall’implementazione utilizzata.

---

### 9. Invariante della classe Account

Un invariante rappresenta una proprietà che deve essere sempre vera per tutte le istanze della classe.

Nel caso dell’account:

`balance >= minBalance`

Questo vincolo garantisce che il conto non possa mai scendere sotto il saldo minimo.

L’invariante rappresenta un ulteriore obbligo per l’implementazione dei metodi e una garanzia per chi utilizza la classe.

---

### 10. Utilizzi del contratto nel software

Il contratto può essere utilizzato per diversi scopi:

1. **Documentazione**  
    Le condizioni possono essere controllate sintatticamente ed esportate automaticamente, ad esempio in formato Javadoc.
    
2. **Controllo a runtime**  
    Le condizioni possono essere attivate durante sviluppo e testing e disattivate nella versione finale per evitare rallentamenti.
    
3. **Prova di correttezza**  
    Il contratto può essere utilizzato come base per dimostrare formalmente la correttezza del software.
    

---

### 11. Cenni alla correttezza del software

La correttezza del software viene definita come la coerenza tra implementazione e specifica.

Nel contesto del Design by Contract, la specifica è rappresentata dal contratto stesso.

La notazione utilizzata è la **tripla di Hoare**:

{P} A {Q}

dove:

- P è la precondizione;
    
- A è il programma;
    
- Q è la postcondizione.
    

La proprietà significa che ogni esecuzione del programma A che parte da uno stato che soddisfa P termina in uno stato che soddisfa Q.

---

### 12. Esempi di correttezza

Viene presentato l’esempio della funzione di radice quadrata:

- precondizione: l’input deve essere positivo;
    
- postcondizione: il risultato approssima correttamente la radice.
    

Un altro esempio riguarda un’istruzione semplice:

`n := n + 9`

con precondizione `n > 5`.

La dimostrazione della correttezza può risultare complessa e non sempre automatizzabile.

---

### 13. Correttezza di una classe nel Design by Contract

Una classe è considerata corretta se:

- il costruttore garantisce l’invariante;
    
- ogni metodo preserva l’invariante e soddisfa le postcondizioni quando le precondizioni sono vere.
    

Formalmente:

- `{PREcostr} constructor {INV ∧ POSTcostr}`
    
- `{PREop ∧ INV} OP {POSTop ∧ INV}`
    

Questo schema rappresenta una base teorica fondamentale per la verifica del software.

---

### 14. Sintesi della lezione

In questa lezione sono stati approfonditi:

- l’uso di JML per esprimere contratti in Java;
    
- la sintassi delle annotazioni;
    
- l’uso di `requires`, `ensures`, `invariant`;
    
- gli operatori `\old` e `\result`;
    
- la distinzione tra implementazione e specifica;
    
- l’uso dei contratti per documentazione, testing e verifica;
    
- i concetti base della correttezza del software.
    

Un aspetto fondamentale da ricordare è che il contratto rappresenta la specifica formale del comportamento del programma.

---

### 15. Prossimi passi

Nella prossima lezione verranno introdotti ulteriori elementi della sintassi JML e tecniche avanzate di specifica.