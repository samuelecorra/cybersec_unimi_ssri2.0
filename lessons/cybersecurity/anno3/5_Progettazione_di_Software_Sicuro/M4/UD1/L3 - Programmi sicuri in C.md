# **Lezione 3: Programmi sicuri in C**

---

### 1. Introduzione: il problema della sicurezza nel linguaggio C

Nelle lezioni precedenti è stato evidenziato come il linguaggio C presenti numerose vulnerabilità legate alla gestione manuale della memoria e alla mancanza di controlli automatici sui tipi. Tuttavia, nonostante questi problemi, il C continua a essere largamente utilizzato in ambiti critici, come sistemi operativi, driver di dispositivo e software embedded.

La domanda centrale diventa quindi: **come è possibile ottenere programmi sicuri utilizzando un linguaggio intrinsecamente non sicuro?**

Per rispondere a questa domanda è necessario comprendere sia i limiti dei linguaggi sicuri ad alto livello sia i vantaggi che rendono il C ancora oggi indispensabile.

---

### 2. Svantaggi dei linguaggi astratti e type safe

L’utilizzo di linguaggi considerati sicuri, come Java, comporta alcuni costi tecnici e progettuali.

Il primo svantaggio riguarda le prestazioni. I linguaggi sicuri introducono controlli automatici, come la verifica dei limiti degli array e il garbage collection per prevenire dangling pointers, che comportano inevitabilmente un overhead computazionale.

Un secondo svantaggio è l’utilizzo maggiore di memoria. I runtime dei linguaggi sicuri devono mantenere informazioni aggiuntive, ad esempio sui tipi e sulle dimensioni delle strutture dati.

Un terzo elemento è la maggiore verbosità. La necessità di annotare esplicitamente i tipi aumenta la quantità di codice da scrivere.

Infine, esiste il problema del porting del codice esistente. Molti sistemi legacy sono scritti in C, e la loro migrazione verso linguaggi più sicuri può risultare costosa e complessa, anche se la sintassi di Java è simile a quella del C.

---

### 3. Vantaggi del linguaggio C

Nonostante i rischi, il C possiede caratteristiche fondamentali che ne giustificano l’uso continuo.

Il linguaggio offre:

- prestazioni molto elevate;
- gestione esplicita della memoria;
- controllo diretto della rappresentazione dei dati a basso livello;
- possibilità di riutilizzare grandi quantità di codice esistente.

Queste proprietà lo rendono particolarmente adatto a contesti dove il controllo hardware e l’efficienza sono prioritari rispetto alla sicurezza automatica.

---

### 4. Principali violazioni di sicurezza nel C

Le vulnerabilità nel linguaggio C possono essere classificate in diverse categorie.

#### 4.1 Errori spaziali di accesso alla memoria

Questi errori si verificano quando un programma accede a memoria al di fuori dei limiti assegnati, ad esempio:

- accessi out-of-bounds;
- buffer overflow.

#### 4.2 Errori temporali di accesso alla memoria

Questi errori riguardano l’uso di memoria non più valida, come:

- dangling pointers;
- uso dopo la liberazione (use-after-free).

#### 4.3 Errori di cast

Conversioni tra tipi incompatibili possono generare violazioni della sicurezza, ad esempio:

- cast tra puntatori di tipo diverso;
- conversioni tra puntatori e interi;
- uso improprio delle union.

#### 4.4 Memory leaks

I memory leak si verificano quando un programma non libera memoria che non serve più. Anche se non sempre producono vulnerabilità immediate, possono causare esaurimento delle risorse e denial of service.

---

### 5. Strategie per rendere il C più sicuro

Esistono diverse strategie per migliorare la sicurezza dei programmi scritti in C.

Le principali categorie sono:

1. strumenti di analisi statica e dinamica;
2. librerie sicure;
3. strumenti e linguaggi progettati per prevenire violazioni di sicurezza.

---

### 6. Analisi dinamica con Purify

Un esempio di strumento di analisi dinamica è Purify, sviluppato da Rational/IBM.

L’approccio consiste nel prendere in input programmi C o C++ e produrre eseguibili modificati contenenti controlli aggiuntivi. Durante l’esecuzione, questi controlli permettono di individuare:

- errori di accesso alla memoria;
- memoria non rilasciata;
- violazioni di sicurezza.

Il vantaggio principale è che lo strumento può essere applicato anche a codice esistente senza modifiche sostanziali.

Tuttavia, esistono due limiti:

- rallentamento dell’esecuzione;
- impossibilità di garantire la scoperta di tutti gli errori, poiché l’analisi avviene solo sui percorsi effettivamente eseguiti.

---

### 7. Librerie sicure per le stringhe

Le stringhe rappresentano una delle principali fonti di vulnerabilità nel C, specialmente a causa dei buffer overflow.

Per questo motivo sono state sviluppate librerie specifiche, tra cui:

- Safe C String Library;
- specifiche ISO/IEC TR 24731 per funzioni più sicure.

Queste librerie introducono versioni delle funzioni standard che includono controlli sui limiti dei buffer, riducendo la probabilità di errori.

---

### 8. Approcci per rendere sicuro il codice C

Esistono due approcci principali:

1. rendere sicuri i programmi C esistenti;
2. utilizzare varianti sicure del linguaggio C.

---

### 9. SafeC

SafeC è un sistema che traduce programmi C in programmi C più sicuri.

Il metodo consiste nell’inserire controlli aggiuntivi e informazioni sui puntatori per catturare violazioni di memoria e errori runtime.

Vantaggi:

- applicabile a codice esistente.

Svantaggi:

- rallentamento dell’esecuzione;
- aumento dell’uso di memoria.

---

### 10. CCured

CCured è un sistema più avanzato che combina:

- analisi statica;
- controlli dinamici;
- garbage collection.

Accetta programmi C con annotazioni opzionali e produce codice sicuro con modifiche minime.

Il principale svantaggio è ancora l’overhead prestazionale.

---

### 11. Cyclone

Cyclone è un linguaggio simile al C progettato per essere sicuro.

Introduce:

- controlli dinamici solo dove necessari;
- garbage collection;
- restrizioni sui puntatori.

Il vantaggio principale è il basso overhead in termini di tempo e memoria.

Lo svantaggio è la necessità di modificare il codice originale.

---

### 12. Vault

Vault è un linguaggio più astratto, simile a Java e C#.

Produce oggetti COM e utilizza concetti avanzati come:

- oggetti lineari;
- regioni di memoria.

Offre buone prestazioni ma richiede la riscrittura dei programmi esistenti.

---

### 13. Confronto tra i linguaggi e gli strumenti

Dal punto di vista del controllo a basso livello:

- SafeC e CCured permettono pieno utilizzo del C con restrizioni sui puntatori;
- Cyclone è più restrittivo;
- Vault è più astratto e meno efficiente.

Per la gestione della memoria:

- Cyclone offre diverse opzioni;
- Vault utilizza oggetti lineari e regioni;
- SafeC mantiene malloc e free esplicite;
- CCured utilizza garbage collection.

---

### 14. Confronto dei costi

Le soluzioni presentano differenze in termini di:

Prestazioni:

```

Cyclone ≈ Vault < CCured << SafeC

```

Aumento della memoria:

```

Cyclone ≈ Vault < CCured << SafeC

```

Sforzo per annotare i tipi:

```

SafeC < CCured < Cyclone ≈ Vault

```

Sforzo per portare codice esistente:

```

SafeC < CCured < Cyclone << Vault

```

---

### 15. Sintesi della lezione

In questa lezione sono stati analizzati:

- i pro e contro dell’utilizzo di linguaggi sicuri rispetto al C;
- le principali violazioni di sicurezza nel C;
- strumenti e tecniche per rendere il C più sicuro;
- il confronto tra SafeC, CCured, Cyclone e Vault.

È stato inoltre evidenziato che l’analisi dinamica tramite strumenti come Purify può aiutare a individuare errori, ma non garantisce la sicurezza completa.

---

### Prossimi passi

Nelle lezioni successive verranno analizzati ulteriori strumenti e tecniche per migliorare la sicurezza del software scritto in linguaggi non sicuri.