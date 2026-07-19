## ***Lezione 2: Rappresentazione Analogica dell’Informazione***

---

> 📌 Questa lezione rielabora integralmente le pagine 4–8 di `M1doc.pdf`.

### **1. Perché nasce la rappresentazione analogica**

Nella Lezione 1 abbiamo visto che:

- l’informazione è **astratta**,
    
- la macchina opera solo su **grandezze fisiche**,
    
- serve quindi una **rappresentazione fisica dell’informazione**.
    

Un metodo naturale e storicamente importante per rappresentare fisicamente l’informazione è quello **analogico**.

La rappresentazione analogica nasce in modo naturale perché molti fenomeni macroscopici vengono modellati come **continui**: temperatura, intensità luminosa, pressione e tensione possono assumere, nel modello ideale, ogni valore di un intervallo. I dispositivi reali hanno comunque risoluzione, rumore e limiti fisici finiti.

---

### **2. Che cosa significa rappresentare un’informazione**

Rappresentare un’informazione significa:

> **creare una corrispondenza tra un contenuto astratto e una grandezza fisica misurabile.**

Per costruire una rappresentazione servono sempre tre elementi:

1. **Il rappresentato** → l’informazione astratta.
    
2. **Il rappresentante** → la grandezza fisica.
    
3. **La legge di corrispondenza** → la regola che lega i due.
    

Per definire correttamente questa legge dobbiamo conoscere:

- il **campo di variabilità del rappresentato**,
    
- il **campo di variabilità del rappresentante**.
    

Solo dopo possiamo costruire una mappatura coerente tra i due insiemi di valori.

---

### **3. Esempio fondamentale: temperatura rappresentata tramite tensione**

Supponiamo:

- **Rappresentato**: temperatura $T$  
    $$T \in [0^\circ C, 50^\circ C]$$
    
- **Rappresentante**: tensione elettrica $V$  
    $$V \in [0V, 5V]$$
    

Definiamo una **legge di corrispondenza lineare**:

- $0^\circ C \rightarrow 0V$
    
- $25^\circ C \rightarrow 2.5V$
    
- $50^\circ C \rightarrow 5V$

La legge è quindi

$$
V(T)=\frac{5\ \mathrm{V}}{50\ ^\circ\mathrm{C}}T
=\frac{T}{10}\ \mathrm{V},
$$

mentre la decodifica inversa è $T(V)=10V\ ^\circ\mathrm{C}/\mathrm{V}$. Per esempio, $3{,}7\ \mathrm{V}$ rappresentano $37\ ^\circ\mathrm{C}$.
    

![](imgs/Pasted%20image%2020251208082935.png)

Ogni valore della temperatura viene associato a un valore **proporzionale** di tensione.

Questo significa che:

- se la temperatura aumenta in modo continuo,
    
- anche la tensione varia in modo continuo,
    
- senza salti,
    
- senza livelli discreti.
    

Questa è **la rappresentazione analogica** nel suo significato più puro.

---

### **4. Caratteristiche matematiche della rappresentazione analogica**

Nel modello ideale del PDF, la corrispondenza scelta è:

- **biunivoca**  
    a ogni valore del rappresentato corrisponde **un solo valore del rappresentante**, e viceversa;
    
- **continua**  
    tra due valori esistono **infiniti valori intermedi**;
    
- **lineare nell’esempio**  
    il grafico è una retta, caratteristica che semplifica calibrazione e conversione. Una rappresentazione analogica non è però necessariamente lineare: può usare qualunque funzione nota e invertibile nell’intervallo di lavoro.
    

![](imgs/Pasted%20image%2020251208083003.png)

---

### **5. Vantaggi della rappresentazione analogica**

#### **5.1. Fedeltà**

Ogni piccolissima variazione del fenomeno reale si riflette nel rappresentante fisico.

Esempio:

- $+0.1^\circ C$ → piccola variazione di tensione,
    
- nessuna perdita di continuità.
    

Questo permette all’analogico di seguire con continuità il fenomeno entro i limiti di banda, sensibilità, linearità e rumore del dispositivo.

---

#### **5.2. Intuitività**

Il valore si **legge direttamente** osservando la grandezza fisica:

- altezza del mercurio → temperatura,
    
- posizione della lancetta → velocità,
    
- ampiezza dell’onda → intensità del segnale.
    

La lettura può risultare intuitiva, benché richieda comunque una scala e una legge di corrispondenza.

---

### **6. Il problema fondamentale dell’analogico: la vulnerabilità**

La rappresentazione analogica ammette un continuo di valori. Un disturbo non può quindi essere eliminato semplicemente riportando il segnale a uno fra pochi livelli ammessi: viene invece sovrapposto al valore utile.

---

#### **6.1. Sensibilità ai disturbi**

Poiché ogni valore è ammesso, anche una **minima alterazione** viene interpretata come informazione valida.

I disturbi possono essere:

- elettrici,
    
- elettromagnetici,
    
- termici,
    
- meccanici,
    
- ambientali.
    

Una variazione microscopica di tensione può generare **un errore di misura**.

Nel calcolo automatico questo costituisce un limite importante, perché il risultato deve essere:

- stabile,
    
- ripetibile,
    
- affidabile.
    

---

#### **6.2. Invecchiamento dei componenti**

Con il tempo i componenti analogici subiscono:

- ossidazione,
    
- variazioni termiche,
    
- deformazioni dei materiali,
    
- deriva dei parametri elettrici.
    

![](imgs/Pasted%20image%2020251208083028.png)

Una resistenza cambia valore →  
la relazione tensione-temperatura cambia →  
la misura diventa **progressivamente falsa**.

Il sistema non è più affidabile nel tempo.

---

#### **6.3. Errore di approssimazione e accumulo**

Nessuna misura analogica è **perfettamente precisa**.  
Ogni operazione introduce un piccolo errore.

Quando eseguiamo più operazioni in sequenza:

- gli errori **si sommano**,
    
- poi si **moltiplicano**,
    
- possono amplificarsi fino a rendere il risultato inutilizzabile.
    

Esempio concettuale:

- sommo due segnali leggermente errati,
    
- il risultato è più errato,
    
- ripeto 1000 volte → ottengo un valore completamente inattendibile.
    

Questo limita la profondità dei calcoli analogici senza tecniche di calibrazione, compensazione e controllo dell’errore.

---

### **7. Perché l’analogico non può sostenere l’informatica moderna**

L’elaborazione digitale su larga scala ricerca:

- risultati discreti riproducibili entro le specifiche,
    
- stabilità nel tempo,
    
- ripetibilità perfetta,
    
- elevata tolleranza ai disturbi,
    
- possibilità di memorizzazione fedele,
    
- capacità di eseguire miliardi di operazioni senza degradare.
    

La rappresentazione analogica:

- è continua,
    
- sensibile,
    
- instabile,
    
- soggetta a deriva,
    
- soggetta ad accumulo di errore.
    

Per questo l’analogico non è il supporto dominante del calcolo general-purpose moderno. Non è però scomparso: sensori, convertitori, amplificatori, radio e circuiti mixed-signal continuano a elaborare segnali analogici, spesso prima o dopo una sezione digitale.

> ⚠️ “Analogico” non significa “impreciso” e “digitale” non significa “esatto” in assoluto. La differenza riguarda l’insieme continuo o discreto dei valori usati nella rappresentazione; entrambi i sistemi reali hanno errori e limiti.

---

### **8. Cenni storici: dal mondo analogico al digitale**

Per oltre un secolo l’umanità ha usato **rappresentazioni analogiche**:

- termometri a mercurio,
    
- manometri,
    
- voltmetri a lancetta,
    
- telegrafo,
    
- radio,
    
- telefoni analogici.
    

Molte prime macchine di calcolo erano:

- meccaniche,
    
- poi elettromeccaniche,
    
- basate su movimenti continui.
    

Le **schede perforate di Herman Hollerith**, impiegate per il censimento statunitense del 1890, erano lette da macchine elettromeccaniche. La presenza o assenza di un foro è però già una rappresentazione **discreta**, non analogica: il supporto è materiale, ma il codice è digitale.

Durante la Seconda guerra mondiale, Alan Turing contribuì al progetto britannico della **Bombe**, macchina elettromeccanica usata nell’analisi dei messaggi Enigma. Anche in questo caso l’elaborazione era essenzialmente **logica e discreta**, pur essendo realizzata con rotori, relè e contatti elettromeccanici. Il riferimento del PDF alla sua natura “analogica” va quindi inteso soltanto come richiamo alla tecnologia fisica non elettronica, non al tipo di informazione elaborata.
    

La vera svolta arriva con:

- i **semiconduttori**,
    
- i **transistori**,
    
- le **porte logiche**,
    
- i **circuiti digitali**.
    

Con l’affermazione dei calcolatori elettronici, l’informazione interna viene prevalentemente codificata mediante:

- **due soli stati stabili**,
    
- **due classi di livelli elettrici**,
    
- **due soli simboli**: $0$ e $1$.
    

Nasce così la **rappresentazione digitale**.

---

### **9. Significato architetturale della transizione analogico → digitale**

Il passaggio dall’analogico al digitale significa:

- passare dal **continuo al discreto**,
    
- eliminare gli infiniti valori intermedi,
    
- introdurre soglie nette,
    
- consentire la **rigenerazione** dei livelli entro opportuni margini di rumore.
    

Questo rende possibile:

- il calcolo automatico affidabile,
    
- la memoria stabile,
    
- le CPU,
    
- i sistemi operativi,
    
- le reti,
    
- l’intera civiltà digitale.
    

---

### **10. Conclusione concettuale della lezione**

La rappresentazione analogica è:

- naturale,
    
- fedele,
    
- intuitiva,
    

ma è anche:

- instabile,
    
- sensibile,
    
- soggetta a errore,
    
- inadatta all’elaborazione automatica su larga scala.
    

Resta indispensabile nell’interfaccia con il mondo fisico, ma la rappresentazione discreta offre vantaggi decisivi per memoria e calcolo simbolico affidabile.

---

### **11. Collegamento diretto con la Lezione 3**

Ora siamo pronti per il passaggio decisivo:

> **come trasformare un’informazione continua in una forma stabile, immune ai disturbi e adatta al calcolo automatico?**

La risposta è:

la **rappresentazione digitale dell’informazione**.

Ed è esattamente ciò che affronteremo nella **Lezione 3**.

> ✅ Nell’analogico il rappresentante varia con continuità insieme al rappresentato. Questa continuità è intuitiva e fedele, ma rende rumore, deriva ed errori parte del valore elaborato; il digitale introduce invece un insieme finito di simboli riconoscibili.

---
