# **Lezione 0 - Intro Unità Didattica 5 - Tecniche di controllo**

---

### **Introduzione**

In questa unità si affrontano i principi fondamentali dei **protocolli di controllo del flusso** all’interno delle comunicazioni di rete.  
Ogni collegamento tra due nodi, infatti, deve essere in grado di **regolare la velocità di trasmissione dei dati** per evitare congestioni, perdite e inefficienze.

Il **controllo di flusso** rappresenta quindi un meccanismo di **coordinamento tra mittente e destinatario**, che garantisce che:

- il mittente non invii dati più velocemente di quanto il destinatario possa riceverli o elaborarli;
    
- i buffer di ricezione non vadano in overflow;
    
- la rete mantenga un flusso stabile e affidabile.
    

Nel contesto della suite TCP/IP, questi concetti si concretizzano nel **protocollo TCP**, che implementa:

- il controllo di flusso basato sulla **finestra di ricezione (sliding window)**;
    
- e il **controllo di congestione**, che adatta dinamicamente la velocità di trasmissione in base allo stato della rete.
    

L’unità analizzerà in particolare:

- i **principi generali del controllo di flusso**;
    
- le **modalità di dimensionamento dei parametri TCP** (come dimensione della finestra e tempo di ritrasmissione);
    
- la **struttura dell’intestazione TCP/IP**, con attenzione ai campi che supportano il controllo e l’affidabilità della connessione.
    

Al termine, sarai in grado di comprendere in profondità come il TCP realizza una trasmissione **ordinata, affidabile e controllata**, coordinando mittente, destinatario e rete come un sistema dinamico perfettamente bilanciato.