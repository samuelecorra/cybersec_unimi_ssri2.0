## ***Lezione 2: Interrupt cablato***

---

> 📌 Questa lezione rielabora integralmente le pagine 45–47 di `M3doc.pdf`, compreso il collegamento *daisy chain*.

### **1. Struttura essenziale**

Nell’**interrupt cablato** la CPU dispone di due linee del bus di controllo:

- `INTREQ`, richiesta condivisa, attiva bassa;
- `INTACK`, riconoscimento della richiesta.

Dopo avere completato l’istruzione corrente, la CPU rileva `INTREQ=0`. Se accetta l’interruzione:

1. salva automaticamente il PC;
2. disabilita temporaneamente il riconoscimento di ulteriori interrupt;
3. attiva `INTACK`;
4. salta a un indirizzo di risposta unico e predefinito.

Poiché tutte le periferiche portano alla stessa routine iniziale, questa deve scoprire mediante **polling** quale interfaccia ha generato la richiesta. Dopo il servizio, ripristina il contesto e ritorna al PC salvato.

### **2. Identificazione mediante polling**

La RRI legge in ordine i registri di stato delle interfacce abilitate:

```text
per ogni interfaccia, secondo l'ordine di priorità:
    se richiesta_pendente = TRUE:
        servi la periferica
        termina la ricerca
```

L’ordine del polling definisce una priorità software: si interrogano prima le periferiche più urgenti. Il meccanismo è semplice, ma il tempo di avvio del servizio comprende tutte le interrogazioni precedenti.

### **3. Daisy chain del riconoscimento**

Il PDF presenta anche una catena hardware, **daisy chain**, per distribuire `INTACK`. Ogni interfaccia ha:

- un ingresso `INTIN`;
- un’uscita `INTOUT` verso l’interfaccia successiva;
- l’uscita open-collector collegata a `INTREQ`.

Il riconoscimento parte dalla CPU e attraversa le interfacce in ordine fisico. Ogni dispositivo:

- se non ha richiesto l’interrupt, propaga `INTACK` a valle;
- se ha una richiesta pendente, assorbe il riconoscimento e non lo propaga.

La prima periferica richiedente incontrata nella catena viene quindi accettata.

### **4. Priorità fisica e richieste simultanee**

La priorità dipende dalla posizione:

$$
\text{più vicina alla CPU}\Rightarrow\text{priorità maggiore}.
$$

Se due periferiche richiedono simultaneamente servizio, quella più vicina cattura `INTACK`; l’altra mantiene la richiesta e attende un successivo ciclo di interrupt.

Spostare una scheda lungo la catena ne cambia la priorità. Non è una politica modificabile dinamicamente durante l’esecuzione, salvo riconfigurare fisicamente il collegamento.

Un’interfaccia che ha già lasciato propagare `INTACK` non può “riprenderselo” nello stesso ciclo se la propria richiesta nasce subito dopo: deve mantenere `INTREQ` e attendere una nuova propagazione del riconoscimento.

### **5. Interrupt annidati e rischio di inversione della priorità**

All’ingresso nella RRI la CPU disabilita normalmente gli interrupt. Se restano disabilitati fino al termine, la periferica in servizio non può essere disturbata, ma anche una richiesta più urgente deve attendere.

La RRI può decidere di riabilitarli dopo avere salvato il contesto. In questo modo è possibile l’annidamento; tuttavia, con la linea aggregata e senza un controllo selettivo, anche una periferica meno prioritaria può interrompere una RRI che sta servendo un dispositivo più urgente.

> ⚠️ Riabilitare globalmente gli interrupt non equivale a consentire soltanto priorità superiori. L’interrupt cablato elementare non possiede una maschera dinamica selettiva.

### **6. Vantaggi e limiti**

| Vantaggio | Limite corrispondente |
| --- | --- |
| circuito semplice | identificazione mediante polling |
| una sola linea di richiesta | latenza crescente con il numero di interfacce |
| daisy chain determina una precedenza | priorità fissa e legata alla posizione |
| disabilitazione globale facile | gestione insoddisfacente degli interrupt annidati |

Il meccanismo risolve la necessità di essere avvisati rapidamente, ma non rende ottimali riconoscimento, priorità e mascheramento.

> ✅ Nell’interrupt cablato la CPU salta a una RRI comune; polling e posizione nella daisy chain identificano e ordinano le sorgenti, con semplicità circuitale ma scarsa flessibilità.
