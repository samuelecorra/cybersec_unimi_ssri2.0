# **Lezione 0 - Intro Unità Didattica 2 - DES**

---

### **Introduzione**

In questa unità entriamo nello studio del **DES – Data Encryption Standard**, il primo grande standard mondiale di **cifratura simmetrica a blocchi** adottato ufficialmente (nel 1977) e rimasto a lungo un punto di riferimento nella crittografia moderna.

Il DES è basato su una **rete di Feistel**, e quindi eredita da essa la struttura a **round successivi** con alternanza di **sostituzioni e permutazioni**, ma applicata a blocchi di **64 bit** e con **chiavi di 56 bit** effettivi.

L’unità ha tre obiettivi principali:

1. **Comprendere a fondo il funzionamento interno** dell’algoritmo DES, analizzando le fasi di permutazione, espansione, sostituzione e ricomposizione dei blocchi.
    
2. **Studiare le modalità operative** (ECB, CBC, CFB, OFB) che permettono di adattare DES a messaggi di lunghezza arbitraria.
    
3. **Conoscere le principali tecniche di crittoanalisi**, per capire perché — pur essendo storicamente fondamentale — DES è oggi considerato **insicuro** e sostituito da standard più robusti come AES.
    

In sintesi, questa unità rappresenta il **passaggio dalla teoria alla pratica**, mostrando come i principi di **Feistel, confusione e diffusione** vengano realizzati in un algoritmo reale di cifratura industriale.