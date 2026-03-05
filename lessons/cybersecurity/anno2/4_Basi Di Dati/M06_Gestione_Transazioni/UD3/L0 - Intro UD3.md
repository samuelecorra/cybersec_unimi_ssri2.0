# **Lezione 0 - Intro Unità Didattica 3 - Controllo di concorrenza**

In questa unità si affronta uno dei temi centrali nella gestione delle basi di dati: la **concorrenza tra transazioni**.  
Quando più transazioni vengono eseguite **in parallelo**, possono generarsi **anomalie** che compromettono la **correttezza e la coerenza** della base di dati, come letture sporche, aggiornamenti persi o inconsistenze temporanee.

L’obiettivo dell’unità è comprendere come il **modulo di controllo della concorrenza** del DBMS gestisca queste situazioni, garantendo che l’esecuzione complessiva delle transazioni produca risultati **equivalenti a quelli di una sequenza seriale**.  
A tal fine viene introdotto il concetto di **serializzabilità**, insieme alle **diverse definizioni di equivalenza tra schedule**, che permettono di classificare e confrontare le esecuzioni concorrenti.

Si analizzeranno poi i **principali meccanismi di controllo della concorrenza**, come il **locking a due fasi (2PL)**, i **timestamp ordering**, e gli approcci **ottimistici**, discutendo per ciascuno vantaggi e limitazioni.  
Infine, verrà esaminato il **problema del deadlock**, che può emergere quando più transazioni si bloccano a vicenda in attesa di risorse, e saranno studiate le **tecniche di rilevazione e prevenzione** utilizzate per risolverlo.