# **Lezione 0 - Intro Unit? Didattica 1 - Socket Library**


Con questa unità introduciamo il cuore tecnico della **programmazione di rete**: le **socket**, ovvero i punti terminali di una comunicazione tra due processi che si trovano su host diversi.

Le socket forniscono un’interfaccia software universale tra **applicazioni** e **rete**, permettendo di inviare e ricevere dati attraverso i protocolli **TCP** o **UDP**.  
Attraverso poche ma fondamentali chiamate di sistema — come `socket()`, `bind()`, `listen()`, `accept()`, `connect()` e `send()/recv()` — un programmatore può costruire da zero **un server e un client TCP/IP funzionanti**.

Questa unità mostra, passo dopo passo, **come nasce una connessione Internet dal punto di vista del codice**, spiegando la logica del modello **client/server**, le differenze tra socket orientate alla connessione (TCP) e non orientate (UDP), e fornendo **esempi pratici di programmazione distribuita reale**.


---

