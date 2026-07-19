## ***Lezione 1: Principio di funzionamento della memoria cache***

---

> 📌 Questa lezione rielabora integralmente le pagine 1–8 di `M5doc.pdf`: divario CPU–DRAM, principio di località, nascita della cache e gerarchia dei livelli L1, L2 e L3.

### **1. Il divario fra processore e memoria**

L'evoluzione dei calcolatori non ha accelerato nello stesso modo tutti i componenti. Il grafico storico del PDF confronta, fra il 1975 e il 1998, la frequenza dei processori Intel x86 e quella delle memorie DRAM:

- fino al 1982–1983 le curve sono quasi sovrapposte;
- dagli anni Ottanta la frequenza della CPU cresce rapidamente;
- il miglioramento della DRAM è molto più lento;
- verso la fine degli anni Novanta il divario è circa di un ordine di grandezza.

La frequenza non coincide esattamente con il tempo di accesso, ma il grafico rende visibile il problema architetturale: la CPU può completare operazioni interne molto più rapidamente di quanto la memoria di lavoro possa fornirle istruzioni e dati.

Se un accesso alla DRAM richiede circa dieci cicli della CPU, durante ogni fetch il processore rischia di restare inattivo per la maggior parte del tempo. Aumentare soltanto la frequenza del processore non risolve il problema: senza un meccanismo intermedio, la memoria diventa il collo di bottiglia.

### **2. Che cosa chiede davvero la CPU alla memoria**

La memoria di lavoro è ad accesso casuale: qualunque cella può essere indirizzata con lo stesso meccanismo. Ciò non implica però che un programma reale generi indirizzi distribuiti casualmente.

Il PDF considera un esperimento concettuale:

1. si osserva il bus indirizzi della CPU;
2. a ogni accesso si registra l'indirizzo a 16 bit;
3. si colloca il tempo sull'asse orizzontale e l'indirizzo sull'asse verticale.

Se gli accessi fossero casuali, i punti riempirebbero il grafico senza struttura. Si osservano invece forme regolari:

- sequenze di celle consecutive;
- accessi ripetuti alla stessa cella;
- gruppi circoscritti di indirizzi visitati più volte;
- rare eccezioni lontane dalle zone principali.

Questi raggruppamenti sono spesso chiamati **zone calde**: regioni sulle quali si concentra, per un certo intervallo, gran parte dell'attività della CPU.

### **3. Il principio di località**

Il comportamento osservato prende il nome di **principio di località degli accessi**:

> 📌 Se all'istante $t$ la CPU accede all'indirizzo $x$, nell'immediato futuro è probabile che acceda ancora a $x$ oppure a indirizzi vicini.

La località non è una garanzia sul singolo accesso, ma una regolarità statistica sufficientemente forte da guidare il progetto hardware.

#### **3.1. Località spaziale**

La **località spaziale** indica la probabilità di usare celle prossime a quella appena richiesta. Nasce, fra l'altro, da:

- fetch di istruzioni normalmente consecutive;
- strutture dati percorse in ordine, come vettori e record;
- programmi organizzati in moduli e procedure;
- variabili dello stesso modulo collocate in indirizzi vicini.

Se all'istante $t$ viene letta la variabile `x`, è ragionevole che poco dopo servano `y` o altre variabili dello stesso contesto.

#### **3.2. Località temporale**

La **località temporale** indica la probabilità di riutilizzare presto lo stesso indirizzo. La motivazione principale evidenziata dal PDF è la presenza dei cicli:

- un gruppo di istruzioni viene eseguito ripetutamente;
- le stesse variabili di controllo e di lavoro vengono lette o scritte a ogni iterazione;
- nel grafico indirizzo-tempo compaiono perciò sequenze ricorrenti.

Chiamate di procedura, accessi allo stack e dati usati più volte producono lo stesso effetto.

### **4. L'idea della cache**

Poiché la località rende prevedibile in senso statistico il futuro prossimo, a ogni richiesta non si copia soltanto la cella interessata. Si trasferisce un **blocco** composto dalla cella richiesta e da un certo numero di celle vicine in una memoria:

- molto più veloce della DRAM;
- più piccola, perché la tecnologia veloce è più costosa per bit;
- accessibile sia in lettura sia in scrittura;
- destinata a contenere la parte statisticamente più utile della memoria di lavoro.

Questa memoria è la **cache**. Il termine deriva dal francese *caché*, “nascosto”: il programmatore continua a usare gli indirizzi della memoria di lavoro e normalmente non decide quali blocchi debbano trovarsi nella cache. Anche l'esecuzione della CPU resta riferita allo stesso spazio di indirizzamento: è il gestore della cache a intercettare gli accessi in modo trasparente.

La relazione fondamentale è:

$$
\text{CPU}\longleftrightarrow\text{cache}\longleftrightarrow\text{memoria di lavoro}.
$$

La cache non amplia lo spazio di indirizzamento e non sostituisce la DRAM. Conserva copie temporanee di informazioni che restano logicamente appartenenti alla memoria di lavoro.

### **5. Perché la cache è diventata praticabile**

Il PDF collega l'affermazione della cache alla crescita del numero di transistor integrabili nel processore. La tabella storica riportata è:

| Processore | Anno | Costo fotografato nel 1993 | MIPS iniziali | MIPS massimi | Transistor |
| --- | ---: | ---: | ---: | ---: | ---: |
| 8086 | 1978 | — | 0,33 | 0,75 | 29 K |
| 286 | 1982 | 8 USD | 1,20 | 2,66 | 134 K |
| 386 | 1985 | 91 USD | 5,00 | 11,40 | 275 K |
| 486 | 1989 | 317 USD | 20,00 | 54,00 | 1,2 M |
| Pentium | 1993 | 900 USD | — | 112,00 | 3,1 M |

I valori hanno funzione storica e non descrivono prezzi o prestazioni attuali. Dal confronto emergono due forme di miglioramento:

- **orizzontale**, all'interno della stessa generazione: l'aumento della frequenza porta all'incirca a un raddoppio delle prestazioni;
- **verticale**, fra generazioni: il numero di transistor e la complessità architetturale crescono fortemente, permettendo nuove funzioni.

Una parte dei transistor disponibili può essere usata per integrare memoria cache sullo stesso chip della CPU, alla sua frequenza di lavoro, evitando molte attese durante il fetch.

### **6. Cache di primo, secondo e terzo livello**

Una sola cache non basta a colmare l'intero divario. Si crea una gerarchia:

| Livello | Posizione concettuale | Dimensione indicativa nel contesto storico del PDF | Caratteristica dominante |
| --- | --- | --- | --- |
| L1 | a bordo del processore | pochi o alcune decine di KiB | massima velocità, capacità ridotta |
| L2 | oltre L1, storicamente anche esterna al chip | centinaia di KiB | compromesso fra velocità e capacità |
| L3 | oltre L2, storicamente esterna | decine di MiB | capacità maggiore, accesso più lento |
| Memoria di lavoro | livello sottostante | tutte le celle disponibili | capacità molto maggiore |

Le collocazioni “esterna” o “interna” riportate dal PDF descrivono l'organizzazione storica considerata. Il principio architetturale non dipende dal contenitore fisico: avvicinandosi alla CPU, i livelli sono più piccoli e veloci; avvicinandosi alla memoria di lavoro, diventano più grandi e lenti.

Nel quadro storico del PDF, la L2 esterna era realizzata con chip SRAM, più veloci e costosi della DRAM della memoria di lavoro. La L1 integrata condivideva invece area e transistor del processore con le altre funzioni architetturali, ragione per cui la sua capacità restava particolarmente limitata.

### **7. Come viene sfruttata la località**

Quando la CPU richiede una parola:

1. si cerca nel livello più vicino;
2. se la parola è presente, l'accesso termina rapidamente;
3. se manca, si consulta il livello successivo;
4. il blocco recuperato viene copiato anche verso i livelli più veloci.

Grazie alla località:

- le celle con maggiore probabilità di riuso risiedono vicino alla CPU;
- ogni livello più distante contiene una quantità maggiore di informazione;
- tutte le celle restano presenti nella memoria di lavoro;
- la maggioranza degli accessi può essere soddisfatta senza raggiungere il livello più lento.

### **8. Limiti dell'intuizione**

La cache funziona bene solo se il programma mostra località. Accessi casuali a un insieme molto più grande della cache possono causare sostituzioni continue e ridurre il vantaggio. Anche la dimensione del blocco richiede equilibrio:

- blocchi troppo piccoli sfruttano poco la località spaziale;
- blocchi troppo grandi richiedono trasferimenti lunghi e occupano spazio con dati forse inutili.

> ✅ La cache è una risposta architetturale al divario CPU–DRAM. Sfrutta località spaziale e temporale per mantenere vicino alla CPU una piccola copia delle zone più probabilmente utili, organizzata in livelli via via più grandi e lenti.
