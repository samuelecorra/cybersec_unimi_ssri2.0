## **Lezione 2: Schema di Naor e Pinkas**

### **1. Introduzione**

Lo **schema di Naor e Pinkas** rappresenta un’estensione formale della **crittografia visuale** applicata a più partecipanti, e si basa sul concetto di **schema a soglia $(k, n)$**.  
L’obiettivo è dividere un’immagine segreta in **$n$ sottoimmagini (share)** tali che l’immagine possa essere **ricostruita solo se almeno $k$ share vengono sovrapposte**, mentre qualsiasi gruppo di $k - 1$ partecipanti **non ottiene alcuna informazione** sul contenuto originale.

---

### **2. Schema a soglia $(k, n)$**

Negli schemi a soglia:

- L’immagine segreta è suddivisa in $n$ share.
    
- Solo la **sovrapposizione di almeno $k$ share** produce l’immagine visibile.
    
- La sovrapposizione di meno di $k$ share non rivela nulla (nessun contrasto utile, immagine casuale).
    

Questo approccio consente di **regolare il livello di sicurezza** in base al valore di $k$, e di garantire una **distribuzione controllata della fiducia** tra i partecipanti.

---

### **3. Struttura d’accesso**

In un sistema con più partecipanti, non tutti devono necessariamente avere gli stessi privilegi.  
Per questo si definisce una **struttura d’accesso**, che specifica esattamente **quali sottoinsiemi** di partecipanti possono **ricostruire l’immagine segreta** e **quali no**.

- I sottoinsiemi **autorizzati** possono combinare le proprie share per ricostruire l’immagine.
    
- I sottoinsiemi **proibiti** non possiedono informazioni sufficienti per dedurre alcun dettaglio.
    

Questa struttura di accesso è quindi una **funzione di policy di autorizzazione**, applicata alla condivisione visiva.

---

### **4. Schema di Crittografia Visuale (VCS)**

Uno **schema di crittografia visuale (VCS)** per $n$ partecipanti è un metodo per codificare un’immagine segreta in $n$ immagini dette **share**, con le seguenti proprietà:

- I partecipanti **autorizzati** possono recuperare visivamente l’immagine originale **sovrapponendo le loro share**.
    
- I partecipanti **non autorizzati** non possono ricavare alcuna informazione utile dal proprio insieme di share.
    

Il VCS è quindi una **trasposizione visiva** dei tradizionali **schemi di condivisione del segreto**, come quello di Shamir, ma in cui la ricostruzione è **ottica e immediata**.

---

### **5. Struttura matematica del VCS**

Un **VCS in bianco e nero** con $n$ partecipanti è rappresentato da **due collezioni di matrici** $n \times m$:

- **$C_0$** → insieme di matrici per la codifica dei **pixel bianchi**;
    
- **$C_1$** → insieme di matrici per la codifica dei **pixel neri**.
    

Ogni riga di una matrice rappresenta la **codifica base di un pixel** per un partecipante specifico:

- La riga $i$ di $S_0$ (in $C_0$) è la codifica del pixel bianco per il partecipante $i$;
    
- La riga $i$ di $S_1$ (in $C_1$) è la codifica del pixel nero per il partecipante $i$.
    

---

### **6. Matrici di base e permutazioni**

Per generare le diverse share, si parte da due **matrici di base** $S_0$ e $S_1$ (una per pixel bianco e una per pixel nero).  
Tutte le possibili codifiche si ottengono **permutando le colonne** di queste matrici.

#### **Esempio:**

- Una matrice $S_0$ in $C_0$ può generare tutte le altre matrici in $C_0$ permutando le colonne.
    
- Lo stesso vale per $S_1$ e la collezione $C_1$.
    

Ogni **riga** della matrice permutata viene poi **distribuita** al corrispondente partecipante come parte della sua share.

---

### **7. Crittografia Visuale a Colori**

La crittografia visuale può essere estesa alle **immagini a colori**.  
In questo caso, l’immagine segreta viene scomposta in **share a colori**, e la sovrapposizione dei colori riproduce l’immagine originale.

#### **Modello RGB**

- Ogni colore è rappresentato come una tripla:  
    $$  
    x = (R, G, B)  
    $$  
    dove $R$, $G$, $B$ indicano l’intensità dei canali **Rosso**, **Verde** e **Blu**.
    
- Esempi:
    
    - $(0, 0, 0)$ → Nero (tutti i colori assorbiti)
        
    - $(255, 255, 255)$ → Bianco (nessun colore assorbito)
        
    - $(255, 0, 0)$ → Rosso (assorbiti verde e blu)
        
    - $(150, 0, 0)$ → Rosso scuro (parte del rosso assorbita)
        

Scomponendo e ricombinando i tre canali RGB, è possibile applicare la stessa logica della crittografia visuale binaria ai colori.

---

### **8. Sintesi finale**

- Lo **schema di Naor e Pinkas** realizza una **crittografia visuale a soglia $(k, n)$**.
    
- Solo i **gruppi autorizzati** possono ricostruire l’immagine segreta.
    
- Il sistema si basa su **matrici di base** per pixel bianchi e neri e **permutazioni di colonne** per generare le share.
    
- È possibile estendere la tecnica alle **immagini a colori** tramite la **composizione RGB**.
    
- La sicurezza è garantita dal fatto che **meno di $k$ share non rivelano alcuna informazione**, rendendo il sistema robusto e intuitivo anche per applicazioni pratiche.