## **Lezione 2: Tipi di virus e propagazione**

### **1. Definizione di virus**

Un **virus informatico** è un frammento di codice in grado di **autoreplicarsi**, modificando altri programmi o file per inserirvi una copia di sé stesso.  
Quando questi file vengono eseguiti, il codice del virus si attiva, consentendo la diffusione dell’infezione.

La **proprietà di autoreplicazione** è ciò che distingue i virus dagli altri tipi di malware.  
La loro attivazione richiede in genere **un’azione dell’utente**, come:

- cliccare su un allegato e-mail infetto,
    
- avviare un programma contaminato,
    
- o condividere un supporto rimovibile (es. chiavetta USB).
    

I virus tentano di **rimanere invisibili**, operando in background con gli stessi privilegi dell’utente.

---

### **2. Struttura di un virus**

Un virus informatico è composto tipicamente da **tre sezioni principali**:

1. **Meccanismo di infezione**  
    Determina _come_ il virus si diffonde (es. allegati, eseguibili, macro, rete).
    
2. **Trigger**  
    Evento o condizione che _attiva_ il payload del virus.  
    Può essere una data, un’azione specifica o un intervallo di tempo.  
    Spesso è chiamato anche **bomba logica**.
    
3. **Payload**  
    È la parte “operativa” del virus: può eseguire azioni dannose (distruzione di dati, blocchi di sistema) o semplicemente visibili (messaggi, suoni, animazioni).
    

---

### **3. Ciclo di vita di un virus**

I virus attraversano quattro fasi principali:

1. **Fase dormiente**  
    Il virus è inattivo nel sistema fino al verificarsi di un evento di attivazione.  
    Non tutti i virus hanno questa fase.
    
2. **Fase scatenante (trigger)**  
    Il virus viene attivato e inizia a eseguire la funzione prevista.
    
3. **Fase di propagazione**  
    Il virus inserisce copie di sé stesso in altri file o aree del sistema (spesso modificando leggermente il codice per evitare rilevamenti).
    
4. **Fase esecutiva**  
    Il virus svolge il suo payload: può essere innocuo o altamente distruttivo.
    

---

### **4. Esempio di struttura di virus**

```pascal
program V;
procedure attach_to_program;
repeat
  file := get_random_program;
until first_program_line = 1234567;
prepend V to file;
end;

procedure execute_payload;
(* Azioni malevole o visibili *)

procedure trigger_condition;
(* Restituisce vero se la condizione di attivazione è soddisfatta *)

begin
  attach_to_program;
  if trigger_condition then execute_payload;
  goto original program code;
end.
```

Questo pseudocodice mostra un virus che:

- individua file non infetti,
    
- li modifica inserendo una copia di sé stesso,
    
- esegue il payload al verificarsi di una condizione specifica,
    
- poi passa il controllo al programma originale per non destare sospetti.
    

---

### **5. Vettori di infezione**

I principali **canali di diffusione** dei virus sono:

- **Boot sector** (settore di avvio di dischi e chiavette USB)
    
- **Executable files** (.exe, .com, .dll)
    
- **Macro files** (documenti Word, Excel, ecc.)
    
- **Multipartite** (combinano più modalità di infezione)
    

---

### **6. Virus del boot sector**

Il **settore di avvio** è la prima parte del disco eseguita dal sistema operativo all’avvio o al montaggio del supporto.

Esempio storico: **Brain (1986)** – il primo virus per PC IBM.

**Funzionamento:**

- Si installava nel boot sector e modificava il vettore di interruzione del disco (INT 13h).
    
- Ogni volta che il sistema accedeva a un floppy, il virus controllava la presenza della propria _firma (1234h)_:
    
    - Se presente, lasciava procedere la lettura.
        
    - Se assente, infettava il disco copiando sé stesso nel settore di avvio.
        

Il risultato era una propagazione rapida su tutti i dischetti utilizzati dal sistema infetto.

---

### **7. Macro virus**

I **macro virus** si diffondono sfruttando le **funzionalità di scripting integrate** nelle applicazioni per documenti (come Microsoft Word o Excel).  
Invece di infettare file eseguibili, infettano **documenti utente**, spesso in modo **indipendente dalla piattaforma**.

**Caratteristiche principali:**

- Si diffondono facilmente tramite email o condivisione di file.
    
- I tradizionali controlli di accesso ai file sono inefficaci, poiché gli utenti possono modificare i documenti.
    
- Sono facili da scrivere e adattare.
    

**Esempio: il virus Melissa (1999)**

- Scritto in macro Visual Basic.
    
- Inviava automaticamente l’email infetta ai primi 50 contatti della rubrica di Outlook.
    
- Disabilitava temporaneamente le protezioni macro e si copiava nel _Normal Template_ di Word per infettare tutti i nuovi documenti creati.
    

---

### **8. Classificazione dei virus per tecniche di camuffamento**

|**Tipo di virus**|**Descrizione**|
|---|---|
|**Encrypted virus**|Cripta il proprio codice con una chiave casuale diversa per ogni copia, impedendo di riconoscere pattern costanti.|
|**Stealth virus**|Utilizza tecniche per **nascondersi** dal software antivirus, come intercettare le chiamate di sistema o alterare le risposte ai controlli.|
|**Polymorphic virus**|Ogni copia è **funzionalmente identica ma con codice binario diverso**, rendendo inefficaci le firme statiche.|
|**Metamorphic virus**|Si **riscrive completamente** a ogni replicazione, variando struttura e logica del codice per evitare il rilevamento.|

---

### **9. Virus compressi**

Un’altra tecnica consiste nel **comprimere il file infetto**, in modo che la versione pulita e quella infetta abbiano la stessa lunghezza, mascherando così la presenza del virus.

**Esempio di funzionamento:**

1. Il virus individua un file eseguibile non infetto.
    
2. Lo comprime, riducendone la dimensione.
    
3. Aggiunge una copia di sé stesso all’inizio del file.
    
4. Quando il programma viene eseguito, il virus si attiva e poi lancia il programma originale scompresso.
    

Questa tecnica riduce le probabilità di rilevamento tramite strumenti di analisi basati sulla dimensione dei file.

---

### **10. Sintesi**

Abbiamo analizzato:

- la **definizione e struttura interna** dei virus,
    
- le loro **fasi di vita** e modalità di diffusione,
    
- esempi pratici di infezione (Boot sector, Macro),
    
- le principali **tecniche di camuffamento** (encryption, stealth, polimorfismo, metamorfismo).
    

> I virus informatici si evolvono costantemente, combinando tecniche di replica, cifratura e offuscamento sempre più sofisticate.  
> Comprenderne la logica è il primo passo per progettare contromisure efficaci.


---