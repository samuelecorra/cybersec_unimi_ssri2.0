# **Lezione 0 - Intro Unità Didattica 3 - Tecnologia delle basi di dati distribuite**

Approfondiamo ora gli aspetti tecnici e gestionali che garantiscono il corretto funzionamento di un sistema distribuito, anche in presenza di più transazioni che operano contemporaneamente su dati condivisi.

L’obiettivo è comprendere il **problema del controllo di concorrenza** in un ambiente distribuito, cioè come assicurare che le operazioni simultanee su nodi diversi non compromettano la **consistenza del database**.

L’unità introduce inoltre le **tecniche per la rilevazione dei deadlock distribuiti**, situazioni in cui più transazioni restano bloccate in attesa l’una dell’altra, e studia il **protocollo di Two-Phase Commit (2PC)**, il meccanismo standard per garantire l’**atomicità delle transazioni distribuite**, assicurando che vengano completate tutte o annullate interamente in caso di errore.