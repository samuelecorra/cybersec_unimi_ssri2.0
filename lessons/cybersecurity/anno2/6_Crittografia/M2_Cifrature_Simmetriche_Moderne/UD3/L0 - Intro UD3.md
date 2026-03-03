# **Lezione 0 - Intro Unità Didattica 3 - AES**

---

### **Introduzione**

Con questa unità entriamo nel cuore della **crittografia moderna**: lo **AES (Advanced Encryption Standard)**, l’algoritmo che ha **sostituito definitivamente il DES e il 3DES** come standard internazionale di cifratura simmetrica.

Adottato dal **NIST nel 2001**, l’AES è il risultato di una lunga selezione tra diversi progetti crittografici. Il vincitore fu **Rijndael**, sviluppato dai belgi **Joan Daemen** e **Vincent Rijmen**, per le sue doti di **sicurezza, efficienza e flessibilità**.

In questa unità vedremo:

- Come è strutturato l’**algoritmo AES**, basato su una **rete di sostituzioni e permutazioni** anziché sulla struttura di Feistel.
    
- Le sue principali **operazioni interne**: _SubBytes_, _ShiftRows_, _MixColumns_ e _AddRoundKey_.
    
- Le **dimensioni delle chiavi** (128, 192 e 256 bit) e il corrispondente numero di **round**.
    
- Perché AES è oggi lo **standard universale** per la cifratura di dati, presente in protocolli come HTTPS, VPN, Wi-Fi e sistemi di archiviazione sicura.
    

➡️ Questa unità rappresenta il passaggio dal **mondo Feistel** alla **crittografia a blocchi moderna**, basata su principi matematici di **algebra finita** e **operazioni vettoriali su GF(2⁸)**.