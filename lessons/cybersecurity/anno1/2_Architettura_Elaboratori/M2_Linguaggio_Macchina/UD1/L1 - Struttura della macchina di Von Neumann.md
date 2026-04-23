## **Lezione 1: Struttura della macchina di Von Neumann**

---

## **1. Origine del modello di Von Neumann**

Nel 1945, il matematico ungherese naturalizzato statunitense **John Von Neumann** formalizza un modello tecnico per la costruzione dei **calcolatori digitali programmabili**.

Questa struttura, oggi nota come **architettura di Von Neumann**, rappresenta ancora la **base concettuale dei computer moderni**.

Il modello è detto **quadripartito**, perché è costituito da **quattro grandi blocchi funzionali**:

1. **CPU**
    
2. **Memoria di lavoro**
    
3. **Interfacce di Input/Output**
    
4. **Bus di sistema**
    

---

## **2. CPU – Central Processing Unit**

La **CPU (Central Processing Unit)** è l’**unità centrale di elaborazione**, ovvero:

- il **master** del sistema,
    
- il **cervello** del calcolatore.
    

### **Funzioni fondamentali della CPU**

La CPU:

- interpreta le **istruzioni macchina**,
    
- esegue **operazioni logico-aritmetiche**,
    
- controlla la **comunicazione con memoria e periferiche**.
    

Il suo funzionamento è **sequenziale**: le istruzioni vengono eseguite una dopo l’altra secondo un ordine preciso.

---

## **3. Memoria di lavoro**

La **memoria di lavoro** è un **contenitore ad accesso rapido** che ospita:

- i **programmi** (cioè le istruzioni da eseguire),
    
- i **dati** su cui le istruzioni operano.
    

È una memoria:

- **volatile**,
    
- centrale,
    
- organizzata in **celle tutte uguali**.
    

Ogni cella contiene una **parola di memoria**, cioè una **stringa di bit a lunghezza prefissata**, che può rappresentare:

- un’istruzione,
    
- un dato numerico,
    
- un’informazione binaria generica.
    

---

## **4. Interfacce di Input/Output (I/O)**

Le **interfacce di I/O** sono **dispositivi elettronici specializzati** che:

- collegano il **calcolatore al mondo esterno**,
    
- traducono **segnali fisici** (pressioni, suoni, luce, tasti, movimento, ecc.)
    
- in **sequenze digitali** che il sistema può comprendere.
    

Le periferiche NON lavorano direttamente in binario: serve sempre una **interfaccia** che svolga la **traduzione fisico → digitale**.

---

## **5. Bus di sistema**

Il **bus di sistema** è l’insieme dei **collegamenti elettrici** che permettono la trasmissione delle informazioni tra:

- CPU,
    
- memoria,
    
- periferiche.
    

Il termine deriva dal latino **“omnibus”**, cioè **“per tutti”**, e indica che:

> il bus è un **canale condiviso**.

---

## **6. Il ciclo di funzionamento della CPU**

La CPU esegue un **ciclo continuo** che si articola in **tre macro-fasi**, che si ripetono indefinitamente finché il calcolatore è acceso.

---

### **1. Fase di Fetch – Prelievo dell’istruzione**

La CPU:

- legge dalla memoria l’**istruzione successiva da eseguire**.
    

Ogni istruzione è una **stringa di bit**.

Per sapere **da quale indirizzo leggere**, la CPU utilizza un **registro speciale** chiamato:

**Program Counter (PC)** → contiene l’indirizzo dell’istruzione corrente.

---

### **2. Fase di Decode – Decodifica**

L’istruzione prelevata viene:

- **interpretata**,
    
- riconosciuta come:
    
    - somma,
        
    - salto,
        
    - caricamento,
        
    - trasferimento,
        
    - ecc.
        

Vengono inoltre riconosciuti anche gli **operandi** coinvolti.

---

### **3. Fase di Execute – Esecuzione**

La CPU:

- **esegue materialmente l’operazione richiesta**.
    

Se l’istruzione è:

- **aritmetica/logica** → interviene l’ALU,
    
- **di trasferimento** → si accede a memoria o I/O.
    

In questa fase è possibile anche lo **scambio di dati con l’esterno**.

---

## **7. Programmi, istruzioni e dati**

Affinché la CPU sia una vera **macchina programmabile**, è fondamentale che:

> possa **scoprire i propri compiti volta per volta**.

Questi compiti sono **sequenze di stringhe di bit** che rappresentano:

- **istruzioni**,
    
- **programmi completi**.
    

Anche i **dati** sono codificati come **stringhe di bit**.

---

## **8. IPS – Instructions Per Second**

Per misurare la velocità di una CPU si introduce l’unità:

**IPS – Instructions Per Second**

Formula concettuale:

**IPS = socket × core per socket × frequenza di clock × IPC**

Esempio teorico:

- CPU: i9-14900K
    
- 24 core
    
- 6 GHz
    
- IPC ≈ 1
    

Si arriva teoricamente a:

**~144 GIPS = 144 miliardi di istruzioni al secondo**  
(stima puramente teorica).

---

## **9. Perché serve la memoria di lavoro**

Dato che:

- i programmi sono enormi,
    
- i dati sono numerosissimi,
    
- la CPU lavora a velocità altissime,
    

serve un contenitore:

> capace di **scambiare informazioni alla stessa velocità della CPU**.

Questo contenitore è la **memoria di lavoro (RAM)**.

---

## **10. Struttura logica della memoria**

La memoria è rappresentabile come un **array di celle**.

Ogni cella:

- contiene una **parola di memoria**,
    
- ha una **lunghezza fissa in bit**,
    
- è identificata da un **indirizzo**.
    

Nasce quindi la necessità di:

- **discriminare le celle** → linee di indirizzo,
    
- **trasferire i dati** → linee di dato,
    
- **specificare l’operazione** → linee di controllo (Read/Write).
    

---

## **11. Come interagiscono CPU e Memoria**

La CPU deve indicare alla memoria:

- **quale cella** (indirizzo),
    
- **che tipo di operazione**:
    
    - Read (lettura),
        
    - Write (scrittura).
        

Poi avviene lo **scambio effettivo del contenuto**.

---

## **12. Gerarchia Master–Slave**

Tra CPU e memoria esiste una **gerarchia funzionale chiara**:

- **CPU → Master**
    
- **Memoria → Slave**
    

La CPU decide **cosa fare e quando farlo**.  
La memoria **risponde alle richieste**.

---

## **13. Perché servono le periferiche**

CPU e memoria, da sole, sono **autosufficienti**, ma **isolabili dal mondo esterno**.

Senza periferiche:

- non potremmo caricare programmi,
    
- non potremmo inserire dati,
    
- non potremmo leggere risultati.
    

Il mondo esterno, inoltre, non è digitale, ma:

- fisico,
    
- biologico,
    
- umano.
    

Serve dunque un sistema di **interfacce tra mondo digitale e mondo reale**.

---

## **14. Le interfacce come “Giano bifronte”**

Le interfacce di I/O hanno **due facce**:

- una verso la **CPU** (digitale),
    
- una verso il **mondo esterno** (fisico).
    

### **Verso la CPU**

- lavorano in **bit e segnali logici**,
    
- comunicano tramite:
    
    - comandi,
        
    - stato,
        
    - dato-in,
        
    - dato-out.
        

### **Verso il mondo esterno**

- lavorano con:
    
    - segnali elettrici,
        
    - meccanici,
        
    - ottici,
        
    - sonori,
        
- comunicano con:
    
    - tastiera,
        
    - mouse,
        
    - monitor,
        
    - sensori,
        
    - microfoni,
        
    - ecc.
        

---

## **15. Struttura interna di una generica interfaccia I/O**

Ogni interfaccia contiene **registri speciali**.

### **Faccia verso la CPU**

I registri sono:

- identici a celle di memoria,
    
- leggibili e scrivibili con le stesse istruzioni della RAM.
    

### **Faccia verso il mondo esterno**

I registri permettono di:

- scambiare dati con la periferica,
    
- fornire lo stato della periferica,
    
- ricevere comandi dalla CPU.
    

Tutto è gestito da una **Unità di Controllo dell’Interfaccia**.

---

## **16. Illusione per la CPU**

Dal punto di vista della CPU:

- i registri dell’interfaccia
    
- sono **normali celle di memoria con indirizzi particolari**.
    

Ma quando la CPU scrive o legge questi indirizzi:

- in realtà sta comunicando con il **mondo esterno**.
    

Questo realizza:

- invio comandi (unilaterale),
    
- lettura stato,
    
- scambio dati (bilaterale).
    

---

✅ **Questa è la Lezione 1 completa (pagine 1–8) completamente rifinita.**

---

Se vuoi, al prossimo passo possiamo fare **subito**:

👉 **Lezione 2 – Bus & Spazio di Indirizzamento (pagine 9–12)**  
Oppure, se preferisci:  
👉 aggiungiamo **schemi logici e riassunto ultra-compatto** per il ripasso da esame.