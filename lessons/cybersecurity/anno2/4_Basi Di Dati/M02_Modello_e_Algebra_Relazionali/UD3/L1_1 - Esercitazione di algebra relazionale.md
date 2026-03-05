# **M2 UD3 Lezione 1 - Esercitazione di algebra relazionale - Parte I**

### **1. Introduzione**

Questa esercitazione ha lo scopo di applicare in modo pratico gli operatori e gli idiomi dell’algebra relazionale a interrogazioni su più tabelle, utilizzando rinominazioni, proiezioni, selezioni, join e differenze.  
Le relazioni coinvolte sono:

- **CELLULARE(Codice, CFUtente, Modello, Marca, Colore)**
    
- **ABBONAMENTO(Numero, CFUtente, Operatore, Tariffa)**
    
- **UTENTE(CF, Nome, Cognome, Città)**

---

### **2. Interrogazione 1**

**Testo:**  
Determinare il codice dei cellulari di marca _Nokia_ di colore **rosso** oppure **nero**.

$$  
\pi_{\text{Codice}}(\sigma_{(\text{Colore}='rosso' \lor \text{Colore}='nero') \land \text{Marca}='Nokia'}(\text{CELLULARE}))  
$$

**Interpretazione:**

1. Si selezionano le tuple della tabella `CELLULARE` con marca _Nokia_ e colore rosso o nero.
    
2. Si proietta poi solo l’attributo `Codice`.

---

### **3. Interrogazione 2**

**Testo:**  
Determinare il codice dei cellulari degli utenti **residenti a Milano**.

$$
UT\_MIL := \pi_{CF}(\sigma_{Citta='Milano'}(UTENTE))
$$

$$
\pi_{Codice}(UT\_MIL \bowtie_{CF=CFUtente} CELLULARE)
$$

**Interpretazione:**

1. Si estraggono i codici fiscali degli utenti che vivono a Milano.
    
2. Si effettua un join con `CELLULARE` per ricavare i codici dei cellulari appartenenti a tali utenti.

---

### **4. Interrogazione 3**

**Testo:**  
Determinare la marca dei cellulari degli utenti serviti da **TIM** nella **citta di Milano**.

$$  
UT_TIM := \pi_{CFUtente}(\sigma_{Operatore='TIM'}(ABBONAMENTO))  
$$

$$  
UT_MIL := \rho_{CFUtente \leftarrow CF}(\pi_{CF}(\sigma_{Citta='Milano'}(UTENTE)))  
$$

$$  
\pi_{Marca}((UT_TIM \cap UT_MIL) \bowtie CELLULARE)  
$$

**Interpretazione:**

1. Si estraggono gli utenti serviti da TIM.
    
2. Si rinominano i codici fiscali degli utenti residenti a Milano.
    
3. Si considerano gli utenti che soddisfano entrambe le condizioni.
    
4. Si proiettano le marche dei loro cellulari.

---

### **5. Interrogazione 4**

**Testo:**  
Determinare gli **operatori** che servono **tutti gli utenti** della **citta di Milano**.

$$  
UT_MIL := \rho_{CFUtente \leftarrow CF}(\pi_{CF}(\sigma_{Citta='Milano'}(UTENTE)))  
$$

$$  
OP := \pi_{Operatore}(ABBONAMENTO)  
$$

$$  
TUTTI := UT_MIL \times OP  
$$

$$  
ABB_MIL := \pi_{CFUtente,Operatore}(UT_MIL \bowtie ABBONAMENTO)  
$$

$$  
\pi_{Operatore}(ABB_MIL) - \pi_{Operatore}(TUTTI - ABB_MIL)  
$$

**Interpretazione:**

1. Si identificano gli utenti di Milano e gli operatori presenti.
    
2. Si genera il prodotto cartesiano tra tutti gli utenti milanesi e gli operatori.
    
3. Si rimuovono le coppie non effettivamente presenti in ABBONAMENTO.
    
4. Il risultato e l’insieme degli operatori che servono **tutti** gli utenti di Milano.

---

### **6. Interrogazione 5**

**Testo:**  
Determinare la **tariffa minima** applicata da **TIM**.

$$  
TAR_TIM := \pi_{Tariffa}(\sigma_{Operatore='TIM'}(ABBONAMENTO))  
$$

$$  
TAR_TIM - \pi_{Tariffa}(TAR_TIM_{Tariffa>Tariffa'}(\rho_{Tariffa' \leftarrow Tariffa}(TAR_TIM)))  
$$

**Interpretazione:**

1. Si estraggono le tariffe relative a TIM.
    
2. Si eliminano tutte quelle che risultano maggiori di un’altra.
    
3. Rimane la tariffa minima.

---

### **7. Interrogazione 6**

**Testo:**  
Determinare gli **utenti (CF, Nome, Cognome, Citta)** per i quali **tutti i numeri di telefono** a loro intestati hanno **tariffa maggiore di 100**.

$$  
UT_MIN := \pi_{CFUtente}(\sigma_{Tariffa \le 100}(ABBONAMENTO))  
$$

$$  
UT_RIS := \pi_{CFUtente}(ABBONAMENTO) - UT_MIN  
$$

$$  
\pi_{CF,Nome,Cognome,Citta}(UT_RIS \bowtie_{CFUtente=CF} UTENTE)  
$$

**Interpretazione:**

1. Si individuano gli utenti che hanno almeno un numero con tariffa minore o uguale a 100.
    
2. Si sottraggono tali utenti dall’insieme di tutti quelli che hanno un abbonamento.
    
3. Si uniscono con la tabella UTENTE per ottenere i dati anagrafici di chi possiede **solo tariffe superiori a 100**.

---

### **8. Conclusione**

Questa prima parte dell’esercitazione ha mostrato come combinare selezioni, proiezioni, join, ridenominazioni e operatori insiemistici per risolvere **query complesse** in modo puramente algebrico, esattamente come avviene nel processo di traduzione e ottimizzazione delle interrogazioni SQL all’interno di un DBMS.