# **M4 UD5 Lezione 2 – Parte II - Esercitazione di progettazione logica (Schema relazionale)**

### **1. Introduzione**

In questa seconda parte si esegue la **traduzione dello schema concettuale** dell’azienda _Sempre Vivo (SV)_ nel corrispondente **schema logico relazionale**.  
L’obiettivo è ottenere l’elenco completo delle **relazioni (tabelle)** con i rispettivi attributi, chiavi primarie e chiavi esterne, seguendo le regole di traduzione dal modello E–R al modello relazionale.

---

### **2. Schema logico relazionale**

#### **2.1 Relazioni principali**

$$  
\text{PRODOTTO(Codice, Materiale, Dimensioni, Prezzo, Produttore, Selettore)}  
$$

- Chiave primaria: $(Codice)$
    
- L’attributo `Selettore` distingue i **prodotti standard** dagli **accessori**.
    
- L’attributo `Produttore` è **facoltativo**, presente solo per gli accessori non prodotti internamente.

---

$$  
\text{NEGOZIO(Nome, Gestore, Indirizzo)}  
$$

- Chiave primaria: $(Nome)$
    
- Contiene le informazioni identificative di ciascun negozio.

---

$$  
\text{CLIENTE(CF, Nome, Cognome, Indirizzo, Telefono)}  
$$

- Chiave primaria: $(CF)$
    
- Rappresenta i clienti che effettuano ordini o richiedono servizi.

---

$$  
\text{BUONOORDINE(Codice, Data, TipoServizio, CFCliente, NomeNegozio)}  
$$

- Chiave primaria: $(Codice)$
    
- Chiavi esterne:
    
    - $(CFCliente)$ → **CLIENTE**
        
    - $(NomeNegozio)$ → **NEGOZIO**
    
- Ogni buono d’ordine è collegato a un cliente e, se presente, al negozio presso cui è stato compilato.

---

$$  
\text{MODULO(Codice, Luogo, DataIn, Durata, Prezzo, NomeEvento, CodiceBuono)}  
$$

- Chiave primaria: $(Codice)$
    
- Chiave esterna: $(CodiceBuono)$ → **BUONOORDINE**
    
- Rappresenta i moduli di servizio collegati agli ordini.
    
- La relazione _BuonoOrdine–Modulo_ è **1:1 opzionale**.

---

#### **2.2 Relazioni derivate**

$$  
\text{VENDITA(CodiceProdotto, NomeNegozio)}  
$$

- Chiave primaria: $(CodiceProdotto, NomeNegozio)$
    
- Chiavi esterne:
    
    - $(CodiceProdotto)$ → **PRODOTTO**
        
    - $(NomeNegozio)$ → **NEGOZIO**
    
- Descrive i prodotti venduti in ciascun negozio.

---

$$  
\text{ORDINE(CodiceBuono, CodiceProdotto, Quantità)}  
$$

- Chiave primaria: $(CodiceBuono, CodiceProdotto)$
    
- Chiavi esterne:
    
    - $(CodiceBuono)$ → **BUONOORDINE**
        
    - $(CodiceProdotto)$ → **PRODOTTO**
    
- Specifica la **composizione dell’ordine**, con l’attributo `Quantità`.

---

$$  
\text{COLORE(CodiceColore, CodiceProdotto)}  
$$

- Chiave primaria: $(CodiceColore, CodiceProdotto)$
    
- Chiave esterna: $(CodiceProdotto)$ → **PRODOTTO**
    
- Descrive i colori disponibili per ciascun accessorio.

---

$$  
\text{TELEFONO(NumTel, NomeNegozio)}  
$$

- Chiave primaria: $(NumTel, NomeNegozio)$
    
- Chiave esterna: $(NomeNegozio)$ → **NEGOZIO**
    
- Permette di registrare più numeri di telefono per ogni negozio.

---

### **3. Vincoli di integrità referenziale**

I **vincoli di integrità referenziale** assicurano che i collegamenti tra relazioni siano coerenti e che nessun valore di chiave esterna faccia riferimento a un elemento inesistente.

#### **3.1 Elenco dei vincoli**

---

#### **Da `COLORE` a `PRODOTTO`**

$$  
\begin{aligned}  
\text{PRODOTTO.(Codice)} &\rightarrow \text{COLORE.(CodiceProdotto)}  
\end{aligned}  
$$

---

#### **Da `VENDITA` a `PRODOTTO` e `NEGOZIO`**

$$  
\begin{aligned}  
\text{PRODOTTO.(Codice)} &\rightarrow \text{VENDITA.(CodiceProdotto)} \\  
\text{NEGOZIO.(Nome)} &\rightarrow \text{VENDITA.(NomeNegozio)}  
\end{aligned}  
$$

---

#### **Da `ORDINE` a `PRODOTTO` e `BUONOORDINE`**

$$  
\begin{aligned}  
\text{PRODOTTO.(Codice)} &\rightarrow \text{ORDINE.(CodiceProdotto)} \\  
\text{BUONOORDINE.(Codice)} &\rightarrow \text{ORDINE.(CodiceBuono)}  
\end{aligned}  
$$

---

#### **Da `MODULO` a `BUONOORDINE`**

$$  
\begin{aligned}  
\text{BUONOORDINE.(Codice)} &\rightarrow \text{MODULO.(CodiceBuono)}  
\end{aligned}  
$$

---

#### **Da `BUONOORDINE` a `CLIENTE` e `NEGOZIO`**

$$  
\begin{aligned}  
\text{CLIENTE.(CF)} &\rightarrow \text{BUONOORDINE.(CFCliente)} \\  
\text{NEGOZIO.(Nome)} &\rightarrow \text{BUONOORDINE.(NomeNegozio)}  
\end{aligned}  
$$

---

#### **Da `TELEFONO` a `NEGOZIO`**

$$  
\begin{aligned}  
\text{NEGOZIO.(Nome)} &\rightarrow \text{TELEFONO.(NomeNegozio)}  
\end{aligned}  
$$

---

### **4. Sintesi finale dello schema logico**

$$  
\begin{aligned}  
\text{PRODOTTO} & (Codice, Materiale, Dimensioni, Prezzo, Produttore, Selettore) \\  
\text{NEGOZIO} & (Nome, Gestore, Indirizzo) \\  
\text{CLIENTE} & (CF, Nome, Cognome, Indirizzo, Telefono) \\  
\text{BUONOORDINE} & (Codice, Data, TipoServizio, CFCliente, NomeNegozio) \\  
\text{MODULO} & (Codice, Luogo, DataIn, Durata, Prezzo, NomeEvento, CodiceBuono) \\  
\text{ORDINE} & (CodiceBuono, CodiceProdotto, Quantità) \\  
\text{VENDITA} & (CodiceProdotto, NomeNegozio) \\  
\text{COLORE} & (CodiceColore, CodiceProdotto) \\  
\text{TELEFONO} & (NumTel, NomeNegozio)  
\end{aligned}  
$$

---

### **5. Conclusione**

In questa esercitazione si è completata la **traduzione dello schema concettuale in schema logico**, identificando:

- le **relazioni principali e derivate**;
    
- le **chiavi primarie ed esterne**;
    
- tutti i **vincoli di integrità referenziale**;
    
- la **rappresentazione finale coerente** con le regole del modello relazionale.

> Il risultato è uno **schema logico pienamente normalizzato**, pronto per essere implementato in SQL.
