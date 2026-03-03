# **Lezione 0 - Intro Unità Didattica 4 - Altri cifrari simmetrici**

---

### **Introduzione**

In questa unità esploriamo alcuni **cifrari simmetrici alternativi all’AES**, che hanno avuto grande importanza nella storia recente della crittografia moderna.  
Tra questi spiccano:

- **Blowfish**, progettato da **Bruce Schneier** nel 1993, noto per la sua **velocità**, **flessibilità** nella lunghezza della chiave (fino a 448 bit) e per essere **open-source**.
    
- **RC5**, ideato da **Ron Rivest** nel 1994, caratterizzato da una struttura **parametrica** (lunghezza del blocco, chiave e numero di round configurabili) e da un uso intensivo delle **rotazioni dipendenti dai dati**.
    

Concluderemo l’unità con una panoramica sulle **cifrature a flusso (stream ciphers)**, un approccio differente rispetto ai cifrari a blocchi, basato sulla **generazione di un keystream** pseudo-casuale combinato con il testo in chiaro tramite **operazioni XOR**.

➡️ Obiettivo: comprendere come Blowfish, RC5 e i cifrari a flusso estendano i principi della cifratura simmetrica con **diverse strutture e obiettivi di efficienza**.