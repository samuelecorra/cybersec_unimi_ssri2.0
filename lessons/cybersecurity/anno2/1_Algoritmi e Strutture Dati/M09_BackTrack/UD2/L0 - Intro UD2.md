# **Lezione 0 - Intro Unit? Didattica 2 - – String Matching**


In questa unità si affronta uno dei problemi più importanti e diffusi nell’informatica teorica e applicata: il **problema del riconoscimento di stringhe** (_string matching_).  
L’obiettivo è **trovare tutte le occorrenze** di una certa **sottostringa (pattern)** all’interno di una **stringa più lunga (testo)**, attività fondamentale in contesti come la **ricerca di parole nei documenti**, l’**analisi del DNA**, la **compilazione** o i **motori di ricerca**.

L’unità introduce inizialmente l’**algoritmo di Ricerca Bruta**, che rappresenta la soluzione più immediata: confrontare il pattern con il testo in tutte le posizioni possibili.  
Tuttavia, questo approccio comporta **molti backtrack inutili**, ovvero confronti ripetuti e ridondanti che possono essere evitati con un’analisi più intelligente.

Per questo motivo, viene poi introdotto l’**algoritmo di Knuth–Morris–Pratt (KMP)**, una raffinata evoluzione basata sulla tecnica di **backtrack controllato**, che sfrutta la conoscenza del pattern stesso per **evitare confronti superflui**.  
Il cuore del metodo è il **vettore di back (o prefisso/suffisso)**, che consente di calcolare in anticipo **quanto “tornare indietro”** nel pattern senza ricontrollare caratteri già verificati.

L’unità si propone quindi di:

- comprendere la **formulazione generale del problema di string matching** e i suoi ambiti di applicazione;
    
- imparare a eseguire **passo passo l’algoritmo di Ricerca Bruta** e calcolarne la complessità;
    
- comprendere il concetto di **backtrack inutile** e il calcolo del **vettore back**;
    
- e infine saper **applicare e analizzare l’algoritmo di Knuth–Morris–Pratt (KMP)**, valutandone la **complessità ottimizzata**.
    

In questo modo, si passa da una ricerca ingenua a un approccio **intelligente ed efficiente**, che rappresenta una delle applicazioni più eleganti della tecnica **Backtrack** nella pratica della programmazione.


---

