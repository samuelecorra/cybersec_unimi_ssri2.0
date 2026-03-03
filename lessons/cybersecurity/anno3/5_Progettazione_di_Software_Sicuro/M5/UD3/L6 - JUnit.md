# **Lezione 6: JUnit**

### 1. Unit testing e metodologia eXtreme Programming

JUnit è un framework per l’automazione del **unit testing** di programmi Java, progettato nell’ambito della metodologia **eXtreme Programming (XP)**. In questo contesto il testing non è una fase separata dello sviluppo, ma una componente centrale del processo di implementazione.

Ogni test contiene asserzioni che verificano automaticamente che il codice non presenti difetti. È importante sottolineare che XP non coincide con JUnit: XP rappresenta un modello organizzativo completo dello sviluppo software che include pratiche di progettazione, implementazione e testing integrate.

---

### 2. Principi dell’eXtreme Programming

La metodologia XP si basa su alcune pratiche fondamentali distribuite nelle diverse fasi del ciclo di sviluppo.

Nella fase di **planning** si utilizzano casi d’uso e storie utente, con rilasci frequenti e iterazioni continue. Nella fase di **design** si privilegiano semplicità, limitazione delle funzionalità e refactoring continuo. Nella fase di **coding** si adottano standard condivisi, pair programming e proprietà collettiva del codice, evitando il sovraccarico lavorativo.

La fase di **testing** è caratterizzata da:

- presenza obbligatoria di test unitari per tutto il codice;
    
- creazione di nuovi test ogni volta che viene trovato un bug;
    
- automazione completa dei test.
    

Questo approccio porta allo sviluppo guidato dai test.

---

### 3. Test-Driven Development (TDD)

Lo **sviluppo guidato dai test** (Test-Driven Development) segue un ciclo iterativo ben definito:

1. scrivere i casi di test a partire dalle specifiche e dagli scenari d’uso;
    
2. eseguire i test, che inizialmente falliscono;
    
3. scrivere il codice fino a quando i test vengono superati;
    
4. ripetere il processo.
    

Se viene individuato un difetto, il ciclo ricomincia scrivendo nuovi test che evidenziano il problema prima di modificare il codice.

Questo approccio implica che il progetto e l’implementazione evolvano sotto la guida dei test e degli scenari reali. Sebbene inizialmente possa sembrare più lento, nel lungo periodo porta vantaggi significativi in termini di qualità e manutenzione.

---

### 4. Principi fondamentali dello sviluppo guidato dai test

Il TDD introduce alcuni principi chiave:

- il codice applicativo e il codice di test evolvono insieme;
    
- i test costituiscono parte della documentazione del sistema;
    
- la verifica della correttezza deve essere automatica;
    
- il giudizio sulla correttezza di una classe o di un metodo viene espresso dal codice stesso attraverso i test.
    

L’unit testing diventa quindi un’attività continua e automatizzata.

---

### 5. Strumenti di unit testing

Esistono strumenti per unit testing in diversi linguaggi di programmazione. Ad esempio, per C++ sono disponibili framework come CUnit e CPPUnit. Per Java il framework più diffuso è JUnit, sviluppato da Kent Beck, autore della metodologia XP, e da Erich Gamma, noto per i design pattern.

JUnit sfrutta il meccanismo di riflessione di Java, che consente ai programmi di analizzare il proprio codice durante l’esecuzione, e supporta il programmatore in diverse attività:

- definizione ed esecuzione dei test;
    
- formalizzazione dei requisiti delle unità;
    
- debugging;
    
- integrazione continua del codice.
    

Il framework è integrato in numerosi ambienti di sviluppo, come Eclipse e NetBeans.

---

### 6. Struttura di un test in JUnit 4

JUnit 4 introduce l’uso delle **annotazioni**, che semplificano la scrittura dei test.

Un caso di test consiste tipicamente in una classe contenente metodi annotati con `@Test`. All’interno di questi metodi si verificano i risultati utilizzando istruzioni di controllo chiamate **assert**.

Un esempio semplice verifica che il prodotto tra due numeri sia corretto:

```java
@Test
public void testMultiplication() {
    assertEquals("Mult", 4, 2*2);
}
```

In questo esempio:

- `"Mult"` rappresenta un messaggio opzionale;
    
- `4` è il valore atteso;
    
- `2*2` è il valore ottenuto.
    

Se il risultato non corrisponde, il test fallisce.

---

### 7. Testing di una classe: esempio Counter

Per testare una classe si crea una classe ausiliaria, generalmente chiamata con il suffisso `Test`.

Si consideri una classe `Counter` con le seguenti operazioni:

- costruttore che inizializza il contatore a zero;
    
- metodo `inc()` che incrementa il contatore e restituisce il valore aggiornato;
    
- metodo `dec()` che decrementa il contatore.
    

Lo schema della classe può essere:

```java
public class Counter {
    public Counter(){}
    public int inc(){}
    public int dec(){}
}
```

Per testarla si crea una classe:

```java
public class CounterTest {
    @Test public void testInc() {}
    @Test public void testDec() {}
}
```

---

### 8. Struttura di un metodo di test

Un metodo di test segue una sequenza logica precisa:

1. creare gli oggetti necessari;
    
2. invocare il metodo da testare;
    
3. confrontare il risultato ottenuto con quello atteso mediante assert;
    
4. ripetere per altri scenari.
    

Ad esempio, per testare `inc()`:

```java
@Test
public void testInc() {
    Counter c = new Counter();
    assertEquals(1, c.inc());
}
```

Il framework intercetta automaticamente eventuali fallimenti e li segnala al programmatore.

---

### 9. Metodi assert principali

JUnit fornisce diversi metodi di assert, tra cui:

- `assertEquals(expected, actual)` per verificare uguaglianze;
    
- `assertTrue(expression)` per verificare condizioni booleane;
    
- `assertNull(object)` per verificare valori null;
    
- `assertSame()` per confrontare riferimenti;
    
- `fail()` per forzare un fallimento.
    

Questi metodi costituiscono il meccanismo centrale di verifica dei test.

---

### 10. Uso di JUnit negli ambienti di sviluppo

Gli ambienti di sviluppo integrati, come Eclipse, supportano la creazione e l’esecuzione automatica dei test JUnit.

La procedura tipica consiste nel:

1. creare la classe da testare;
    
2. generare automaticamente la classe di test tramite l’IDE;
    
3. selezionare i metodi da testare;
    
4. completare il codice dei test;
    
5. eseguire i test tramite l’opzione dedicata.
    

L’esecuzione produce una barra di stato che rimane verde se tutti i test sono superati.

---

### Sintesi della lezione

In questa lezione è stato introdotto il framework JUnit come strumento per l’automazione del unit testing in Java, nel contesto della metodologia eXtreme Programming e dello sviluppo guidato dai test. Sono stati analizzati i principi del Test-Driven Development, la struttura dei test in JUnit, l’uso delle annotazioni e dei metodi assert, nonché l’integrazione con gli ambienti di sviluppo.

JUnit rappresenta uno strumento fondamentale per garantire qualità, affidabilità e manutenibilità del software attraverso test automatizzati continui.