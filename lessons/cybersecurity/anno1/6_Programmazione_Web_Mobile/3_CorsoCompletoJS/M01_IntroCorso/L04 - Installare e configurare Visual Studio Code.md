## **Lezione 4: Installare e configurare Visual Studio Code**

### **1. Perché questo video esiste**

Ciao a tutti, questa è una comunicazione “dal futuro”: mi sono accorto che ho dato per scontato qualcosa di fondamentale — **come installare Visual Studio Code** — pensando che tutti avessero già seguito HTML e CSS.

Ovviamente non è così: chi inizia da questo corso potrebbe non sapere che cos’è l’editor che usiamo per tutto il percorso.

Quindi, rimediamo subito.

---

### **2. Che cos’è Visual Studio Code**

**Visual Studio Code (VS Code)** è l’editor che useremo per l’intero corso.  
È gratuito, leggero, multipiattaforma e mantiene un’enorme quantità di estensioni utili.

Attenzione:  
**Visual Studio Code NON è Visual Studio.**  
Sono due software diversi.

A noi interessa **Visual Studio Code**, quello gratis.

---

### **3. Download di Visual Studio Code**

Per scaricarlo:

1. Cerca su Google **“VSC”** oppure **“Visual Studio Code”**.
    
2. Vai sul sito ufficiale (lo riconosci subito, è di Microsoft).
    
3. Premi **Download** per il tuo sistema operativo.
    

Il download è molto veloce.

Quando l’eseguibile è pronto, aprilo per procedere all’installazione.

---

### **4. Installazione: le quattro opzioni IMPORTANTI**

Durante l’installazione arriverai alla schermata **cruciale**:

> Le quattro spunte da attivare.  
> Se le dimentichi, poi devi reinstallare tutto.

Le opzioni che devi **assolutamente** selezionare sono:

1. **Aggiungi l’azione “Apri con Code”**
    
    - Ti permette di fare tasto destro su una cartella → _Apri con Code_.  
        È comodissimo e lo useremo spessissimo.
        
2. **Registra “code” per i file supportati**
    
    - Permette di aprire file come JSON e simili direttamente con Code.
        
3. **Aggiungi a PATH (variabili d’ambiente)**
    
    - Fondamentale: potrai aprire VS Code dal terminale con il comando
        
        ```bash
        code .
        ```
        
    - Se non selezioni questa opzione, il comando non funzionerà.
        
4. **Crea collegamento nel menu Start / Desktop** (opzionale ma utile)
    
    - Non influisce sul corso, ma è comodo.
        

Una volta selezionate tutte le spunte consigliate, premi **Installa**.

---

### **5. Aprire un progetto con VS Code**

Dopo l’installazione puoi avviare VS Code normalmente.

Hai due modi per aprire una cartella di progetto:

#### **Metodo 1 — Dal file system**

- Crea una nuova cartella, ad esempio:  
    `ciao-sono-cartella-del-progetto`
    
- Tasto destro sulla cartella → **Apri con Code**.
    

Questo è il metodo più rapido.

#### **Metodo 2 — Da Visual Studio Code**

- Apri VS Code.
    
- Vai su **File → Apri cartella**.
    
- Seleziona la tua cartella di progetto.
    

Entrambi i metodi portano allo stesso risultato.

---

### **6. Installare le estensioni fondamentali**

Ora che VS Code è installato, dobbiamo attivare le estensioni essenziali.

Vai nella barra laterale e apri **Estensioni** (icona dei blocchetti).

Le estensioni che servono subito sono:

#### **6.1 JavaScript Code Snippets**

Permette di scrivere JavaScript più velocemente con scorciatoie utili.  
Installalo subito.

#### **6.2 HTML CSS Support**

Un’estensione molto diffusa (decine di milioni di installazioni) che migliora:

- autocompletamento HTML
    
- autocompletamento CSS
    
- suggerimenti vari
    

È perfetta per chi inizia e velocizza tantissimo il lavoro.

#### **6.3 Live Server**

Questa è fondamentale.

È l’estensione che aggiunge in basso a destra il pulsante **Go Live**, che ti permette di:

- lanciare in locale il tuo progetto HTML/CSS/JS,
    
- aggiornare automaticamente la pagina ogni volta che salvi.
    

Senza Live Server, il corso diventerebbe molto più scomodo.

Installala assolutamente.

---

### **7. Usare Live Server (presentazione iniziale)**

Una volta installato:

- in basso a destra comparirà il pulsante **Go Live**.
    
- Cliccandolo, VS Code avvierà un server locale.
    
- Aprirà il tuo file `index.html` nel browser.
    
- Ogni volta che modifichi e salvi un file, la pagina si aggiorna in automatico.
    

Lo useremo poi nel corso, quindi non preoccuparti per ora: basta averlo installato.

---

### **8. Considerazioni finali

Io, che sto registrando questo video, ho appena finito tutto il corso completo: una maratona enorme, un viaggio lunghissimo ma bellissimo.  
Mi sono accorto solo dopo che mancava questo pezzo essenziale, quindi eccolo qua.

Ora siete pronti:

- avete VS Code,
    
- potete aprire progetti,
    
- potete usare le estensioni fondamentali,
    
- potete lanciare Live Server.