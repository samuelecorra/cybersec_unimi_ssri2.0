# **Lezione 0 - Intro Unità Didattica 2 - Controllo di affidabilità**

In questa unità si studiano i **meccanismi che garantiscono l’affidabilità** di un sistema di gestione di basi di dati.  
L’obiettivo principale è comprendere come il **controllore di affidabilità** (o _recovery manager_) consenta di mantenere la base di dati **coerente e persistente** anche in presenza di guasti, crash o interruzioni improvvise.

Viene descritta l’**architettura del controllore di affidabilità**, analizzando le sue funzioni principali e le modalità con cui interagisce con gli altri moduli del DBMS.  
Si approfondiscono inoltre i **meccanismi di ripristino** che permettono di recuperare uno stato coerente della base di dati dopo un guasto, distinguendo tra:

- **Ripresa a caldo (warm recovery):** il sistema torna operativo rapidamente, minimizzando le perdite di dati;
    
- **Ripresa a freddo (cold recovery):** viene ripristinato uno stato stabile e garantito, anche a costo di tempi di recupero più lunghi.

Questi strumenti assicurano che il DBMS rispetti le proprietà di **atomicità** e **durabilità** delle transazioni, garantendo così la **sicurezza e l’affidabilità complessiva del sistema**.