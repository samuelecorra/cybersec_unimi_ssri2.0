# **M11 Approfondimenti per l'esame - Lezione 0 - Audit di copertura degli appelli 2025-2026**

### **1. Obiettivo dell'audit**

Questa unità nasce dal confronto puntuale tra i dieci appelli disponibili del corso di Basi di Dati — sei del 2025 e quattro del 2026 — e tutte le lezioni dei moduli M1–M10.

L'obiettivo non è sostituire i moduli, ma verificare che ogni richiesta d'esame abbia:

- una base teorica esplicita;
- un metodo operativo applicabile sotto tempo;
- almeno un esempio sufficientemente vicino alla forma dell'esercizio d'esame.

> 📌 L'audit distingue fra **nozione assente** e **tecnica d'esame non ancora sistematizzata**. Nel secondo caso la teoria è presente, ma manca una procedura compatta per combinarla e arrivare rapidamente al risultato.

---

### **2. Integrità delle fonti d'esame**

Sono state esaminate visualmente tutte le 35 pagine disponibili: 26 pagine nei sei PDF del 2025, 3 pagine nel PDF del 7 gennaio 2026 e 6 immagini per gli altri tre appelli 2026.

|Appello|Pagine disponibili|Completezza materiale|
|---|---:|---|
|14 gennaio 2025|3|Completo|
|4 febbraio 2025|6|Completo, incluse tutte le tabelle allegate|
|26 maggio 2025|3|Completo|
|13 giugno 2025|5|Completo, inclusi diagramma e fogli allegati|
|4 luglio 2025|5|Completo, inclusi schema concettuale e tabelle|
|18 settembre 2025|4|Completo, incluse tutte le tabelle allegate|
|7 gennaio 2026|3|Completo|
|8 maggio 2026|2|Il testo principale è presente; manca la tabella allegata dell'Esercizio 4 e lo schedule dell'Esercizio 3 è parzialmente coperto da annotazioni manoscritte|
|23 maggio 2026|2|Mancano il foglio di affermazioni della Domanda 4 e le tabelle di richieste/risposte dell'Esercizio 1|
|3 luglio 2026|2|Completo: tutte le richieste e tutti i dati necessari sono visibili|

Le parti mancanti non sono state ricostruite per congettura. Le soluzioni riportano invece il metodo completo e indicano esattamente quali esiti numerici non sono determinabili dalle immagini disponibili.

---

### **3. Matrice appello → teoria**

#### **3.1. Appelli 2025**

|Appello|Argomenti richiesti|Copertura nei moduli M1–M10|Esito audit|
|---|---|---|---|
|14/01|integrità referenziale e `REFERENCES`; deadlock; cardinalità; recovery; VSR/CSR; SQL/algebra; E-R|[M2](<../../M02_Modello_e_Algebra_Relazionali/UD1/L3 - Vincoli di integrità.md>), [M3](<../../M03_SQL/UD5/L3 - Autorizzazioni e controllo dell’accesso.md>), [M4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L3 - Modello E-R - Identificatori e gerarchie.md>), [M6](<../../M06_Gestione_Transazioni/UD2/L3 - Gestione dei guasti.md>)|Completa|
|04/02|ACIDe distribuite; distributività; cardinalità; timestamp; regole di associazione; SQL/algebra; E-R|[M2](<../../M02_Modello_e_Algebra_Relazionali/UD2/L5 - Equivalenza e ottimizzazione delle espressioni algebriche.md>), [M3](<../../M03_SQL/UD3/L2 - Interrogazioni insiemistiche e nidificate.md>), [M4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L1 - Modello E-R - Entità, relazioni e attributi.md>), [M6](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>), [M10](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>)|Completa|
|26/05|integrità referenziale; timestamp mono/multi; cardinalità; cube/roll-up; VSR/CSR; SQL/algebra; E-R|[M2](<../../M02_Modello_e_Algebra_Relazionali/UD1/L3 - Vincoli di integrità.md>), [M3](<../../M03_SQL/UD1/L2 - Schemi, Tabelle e Vincoli.md>), [M4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L2 - Modello E-R - Le cardinalità.md>), [M6](<../../M06_Gestione_Transazioni/UD3/L3 - View-serializzabilità.md>), [M10](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L3 - Rappresentazione multidimensionale dei dati.md>)|Completa|
|13/06|gerarchie e ristrutturazione; isolamento; trigger; cardinalità; 2PL; associazioni; SQL/algebra; E-R|[M4/UD4](<../../M04_Progettazione_di_Basi_di_Dati/UD4/L2 - Ristrutturazione di schemi E-R - Eliminazione delle gerarchie.md>), [M6](<../../M06_Gestione_Transazioni/UD3/L7 - Meccanismi per la gestione dei lock.md>), [M9](<../../M09_Basi_di_Dati_Attive/UD1/L1 - Basi di dati attive e trigger.md>), [M10](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>)|Completa|
|04/07|2PC; deadlock; cardinalità E-R; equivalenze; VSR/CSR/2PL/TS; SQL/algebra; E-R|[M2](<../../M02_Modello_e_Algebra_Relazionali/UD2/L4 - Prodotto cartesiano e Join.md>), [M4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L2 - Modello E-R - Le cardinalità.md>), [M6](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>), [M7](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>)|Completa|
|18/09|2PC; timestamp; cardinalità; trigger; associazioni; SQL/algebra; E-R|[M2](<../../M02_Modello_e_Algebra_Relazionali/UD2/L7 - Idiomi di interrogazione - cardinalità e copertura.md>), [M3](<../../M03_SQL/UD3/L1 - Ordinamenti, aggregazioni e raggruppamenti.md>), [M4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L3 - Modello E-R - Identificatori e gerarchie.md>), [M6](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>), [M7](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>), [M9](<../../M09_Basi_di_Dati_Attive/UD1/L1 - Basi di dati attive e trigger.md>)|Completa|

#### **3.2. Appelli 2026**

|Appello e quesito|Argomento richiesto|Copertura nei moduli M1–M10|Esito audit|
|---|---|---|---|
|07/01|ACIDe, deadlock, cardinalità, timestamp, SQL/algebra ed E-R|M2, M3, M4, M6, M7 e M10|Completa|
|08/05 D1|ACID e impatto della distribuzione|[M6/UD1/L1](<../../M06_Gestione_Transazioni/UD1/L1 - Transazioni.md>), [M7/UD3/L1](<../../M07_Architetture_Distribuite/UD3/L1 - Ottimizzazione, concorrenza e atomicità.md>)|Completa|
|08/05 D2|Prevenzione deadlock con timestamp, wait-die e wound-wait|[M6/UD3/L8](<../../M06_Gestione_Transazioni/UD3/L8 - Deadlock.md>)|Completa|
|08/05 D3|Terminazione, confluenza, determinismo e grafo di attivazione|[M9/UD1/L3](<../../M09_Basi_di_Dati_Attive/UD1/L3 - Proprietà delle regole attive.md>)|Completa|
|08/05 E1|Checkpoint e ripresa a caldo|[M6/UD2/L3](<../../M06_Gestione_Transazioni/UD2/L3 - Gestione dei guasti.md>)|Completa|
|08/05 E2|VSR, CSR e schedule seriali equivalenti|[M6/UD3/L3](<../../M06_Gestione_Transazioni/UD3/L3 - View-serializzabilità.md>), [M6/UD3/L4](<../../M06_Gestione_Transazioni/UD3/L4 - Conflict-serializzabilità.md>)|Completa|
|08/05 E3|Riconoscimento di schedule 2PL e sequenza lock/unlock|[M6/UD3/L5](<../../M06_Gestione_Transazioni/UD3/L5 - Locking a due fasi (2PL).md>), [M6/UD3/L7](<../../M06_Gestione_Transazioni/UD3/L7 - Meccanismi per la gestione dei lock.md>)|Completa; dato sorgente parzialmente illeggibile|
|08/05 E4|Timestamp mono- e multiversione|[M6/UD3/L6](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>)|Completa; tabella allegata assente|
|08/05 E5|Limiti di supporto da supporto/confidenza parziali|[M10/UD1/L5](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>)|Definizioni presenti; tecnica dei limiti integrata in L3|
|23/05 D1|2PC, guasti e recovery|[M7/UD3/L3 parte 1](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>), [parte 2](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>)|Completa|
|23/05 D2|NULL polimorfo e logica a tre valori|[M3/UD1/L1](<../../M03_SQL/UD1/L1 - Introduzione e Domini.md>), [M3/UD2/L1](<../../M03_SQL/UD2/L1 - SELECT, FROM, WHERE.md>)|Completa|
|23/05 D3|Cardinalità minima/massima con chiavi e riferimenti|[M2/UD1/L3](<../../M02_Modello_e_Algebra_Relazionali/UD1/L3 - Vincoli di integrità.md>), [M2/UD2/L3](<../../M02_Modello_e_Algebra_Relazionali/UD2/L3 - Unione, differenza e intersezione.md>), [M2/UD2/L4](<../../M02_Modello_e_Algebra_Relazionali/UD2/L4 - Prodotto cartesiano e Join.md>)|Operatori presenti; metodo dei limiti integrato in L1|
|23/05 D4|Vero/falso su regole di associazione|[M10/UD1/L5](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>)|Base presente; foglio delle affermazioni assente|
|23/05 E1|Esecuzione tabellare di timestamp|[M6/UD3/L6](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>)|Completa; tabelle allegate assenti|
|23/05 E2|SQL con aggregazioni, antijoin e algebra universale|[M2/UD2/L6](<../../M02_Modello_e_Algebra_Relazionali/UD2/L6 - Idiomi di interrogazione – esiste - per ogni, minimo - massimo.md>), [M3/UD3/L1](<../../M03_SQL/UD3/L1 - Ordinamenti, aggregazioni e raggruppamenti.md>), [M3/UD3/L2](<../../M03_SQL/UD3/L2 - Interrogazioni insiemistiche e nidificate.md>)|Completa|
|23/05 E3|Progettazione E–R completa|[M4/UD2/L1–L4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L1 - Modello E-R - Entità, relazioni e attributi.md>), [M4/UD5 esercitazioni](<../../M04_Progettazione_di_Basi_di_Dati/UD5/L1_1 - Esercitazione di progettazione concettuale - Schema E-R.md>)|Completa|
|03/07 E1|Conteggio di schedule view/conflict-equivalenti|[M6/UD3/L3](<../../M06_Gestione_Transazioni/UD3/L3 - View-serializzabilità.md>), [M6/UD3/L4](<../../M06_Gestione_Transazioni/UD3/L4 - Conflict-serializzabilità.md>)|Definizioni presenti; conteggio combinatorio integrato in L2|
|03/07 E2|SQL e algebra su “sempre” e “solo”|[M2/UD2/L6](<../../M02_Modello_e_Algebra_Relazionali/UD2/L6 - Idiomi di interrogazione – esiste - per ogni, minimo - massimo.md>), [M3/UD3/L1](<../../M03_SQL/UD3/L1 - Ordinamenti, aggregazioni e raggruppamenti.md>)|Completa|
|03/07 E3 e D3|E–R, vincoli e derivazioni|[M4/UD2/L1–L4](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L4 - Modello E-R - Documentazione di schemi.md>)|Completa|
|03/07 D1|2PC, guasti, finestra d'incertezza e read-only|[M7/UD3/L3 parte 1](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>), [parte 2](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>)|Completa|
|03/07 D2|Deadlock distribuito e algoritmo di rilevazione|[M7/UD3/L2](<../../M07_Architetture_Distribuite/UD3/L2 - Controllo di concorrenza.md>)|Completa|
|03/07 D4|Limiti di cardinalità in algebra e SQL|M2 e M3|Metodo dei limiti integrato in L1|
|03/07 D5|Limiti di supporto|M10/UD1/L5|Tecnica dei limiti integrata in L3|

---

### **4. Esito complessivo**

I moduli M1–M10 coprono tutte le **nozioni** nominate nei dieci appelli. Le integrazioni necessarie riguardano il passaggio dalle definizioni alla soluzione rapida di tre famiglie di esercizi:

1. [cardinalità minima e massima](<L1 - Cardinalita minima e massima di espressioni relazionali.md>);
2. [conteggio degli schedule equivalenti](<L2 - Conteggio degli schedule seriali equivalenti.md>);
3. [limiti per supporto e confidenza](<L3 - Limiti per supporto e confidenza nelle regole di associazione.md>).

> ✅ I sei appelli 2025 sono completi e non introducono lacune ulteriori. La copertura teorica 2025-2026 è completa; restano non determinabili soltanto le parti degli appelli 2026 che dipendono da allegati non forniti o da testo materialmente coperto nella fonte.
