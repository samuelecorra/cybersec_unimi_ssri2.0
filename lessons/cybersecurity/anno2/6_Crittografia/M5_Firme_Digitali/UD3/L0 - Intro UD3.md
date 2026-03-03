# **Lezione 0 - Intro Unit? Didattica 3 - Firme con DSS — Breve introduzione**


Questa unità presenta lo **schema DSS (Digital Signature Standard)**, basato sull’**algoritmo DSA**, progettato per firme efficienti e sicure.  
Vedremo i **parametri** (p, q, g), la **generazione delle chiavi** (privata $x$, pubblica $y$), il **processo di firma** che utilizza un nonce casuale $k$ e l’uso dell’**hash** del messaggio, la **procedura di verifica** e le proprietà di sicurezza richieste.

**Punti chiave:**

- DSS/DSA produce firme che dipendono da un **nonce casuale** $k$ per ogni firma; la sicurezza richiede che $k$ sia segreto e unico.
    
- Si firma l’**hash** del messaggio; la verifica usa i parametri pubblici $(p,q,g,y)$.
    
- Vantaggi pratici: firme relativamente piccole e calcolo efficiente; progettato come standard federale.
    

**Attacchi principali:**

- **Riutilo o leak del nonce $k$** → recupero della chiave privata $x$ (attacco critico).
    
- **Nonce prevedibile** (PRNG debole) → compromissione simile.
    
- **Parametri deboli o scelti male** e **hash non sicuro** → collisioni o attacchi pratici.
    

---

