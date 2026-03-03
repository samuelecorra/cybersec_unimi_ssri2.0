# **Lezione 0 - Intro Unità Didattica 1 - Cifrature a blocchi**

---

### **Introduzione**

In questa unità entriamo nel nucleo tecnico delle **cifrature simmetriche moderne**, analizzando il meccanismo delle **cifrature a blocchi**, la categoria di algoritmi su cui si basano la maggior parte dei sistemi di sicurezza odierni.

Una **cifratura a blocchi** divide il messaggio in **blocchi di bit** (tipicamente 64 o 128) e li elabora applicando una serie di trasformazioni matematiche controllate da una **chiave segreta**.

L’unità introduce due concetti fondamentali:

- La **struttura di Feistel**, modello architetturale usato da molti cifrari storici (come DES), che consente di ottenere cifratura e decifratura con lo stesso schema logico.
    
- I **principi di Shannon** — **diffusione** e **confusione** — che rappresentano i criteri matematici fondamentali per garantire che una cifratura sia resistente ad attacchi statistici e analitici.
    

Questi concetti costituiscono le fondamenta su cui si basano tutti i moderni algoritmi di cifratura a blocchi, inclusi gli standard attuali come **AES (Advanced Encryption Standard)**.