# **Lezione 1: Richiami di instradamento statico**

---

### **1. Introduzione**

Prima di introdurre l’**instradamento dinamico**, è utile richiamare il funzionamento dell’**instradamento statico**, ossia la gestione manuale delle rotte di rete da parte dell’amministratore.
In questo caso, ogni percorso viene configurato **esplicitamente** all’interno della **tabella di instradamento** del sistema.

> L’instradamento statico è semplice e stabile, ma non si adatta automaticamente ai cambiamenti nella topologia della rete.

---

### **2. Tipologie di instradamento statico**

Tre sono le configurazioni principali:

1. **Instradamento con default gateway**  
    Tutto il traffico destinato a reti sconosciute viene inviato a un **gateway predefinito**, che si occupa dell’inoltro.
    
2. **Instradamento statico con tabella**  
    L’amministratore crea manualmente **rotte specifiche** per reti o host determinati.
    
3. **Instradamento dinamico**  
    Le tabelle vengono aggiornate **automaticamente** tramite protocolli dedicati (verrà approfondito nelle prossime lezioni).
    

---

### **3. Tabella di instradamento**

Per visualizzare la tabella delle rotte si usa il comando:

```bash
netstat -rn
```

Esempio di output:

```
Destination     Gateway         Refcnt  Interface
127.0.0.1       127.0.0.1       1       lo0
172.16.12.0     172.16.12.2     26      1e0
```

- **Destination:** rete o host di destinazione.
    
- **Gateway:** router o host successivo attraverso cui inoltrare i pacchetti.
    
- **Interface:** interfaccia di rete utilizzata.
    

---

### **4. Aggiunta di rotte statiche**

Per raggiungere host remoti si aggiungono rotte alla tabella con il comando `route add`.

#### **Esempi**

```bash
# route add 207.25.98.0 172.16.12.1 1
# route add 192.0.2.32/27 somegateway
```

Sintassi generale:

```bash
route [-fnvq] add | delete [-net | -host] destination gateway [args]
```

> La destinazione è l’host o la rete da raggiungere, mentre il gateway rappresenta l’hop successivo nel percorso.

![](imgs/Pasted%20image%2020260225152325.png)

---

### **5. Esempio pratico di default route**

Per aggiungere un **gateway predefinito** su un host chiamato `arachide` che inoltra tramite `mandorla`:

```bash
# route -n add default 172.16.12.1 1
```

Successivamente è possibile verificare la connettività con un semplice comando `ping` verso un host remoto (es. `noce`).

![](imgs/Pasted%20image%2020260225152500.png)

---

### **6. Redirezione ICMP**

Durante un ping, può capitare di ricevere un messaggio di **ICMP Redirect**, ad esempio:

```
ICMP Host redirect from gateway mandorla (172.16.12.1)
to noce (172.16.12.3) for host 172.16.1.2
```

![](imgs/Pasted%20image%2020260225152857.png)

Ciò significa che il router “mandorla” avvisa l’host di utilizzare un percorso più diretto verso la destinazione.  
Tuttavia, questa funzionalità è utile solo su dispositivi datati o con funzioni limitate di instradamento.

> Nei sistemi moderni è preferibile evitare la redirezione ICMP configurando rotte esplicite per ogni sottorete.

---

### **7. Configurazione manuale di rotte statiche**

Esempi di rotte dirette per evitare redirezioni:

```bash
# route -n add 172.16.1.0 172.16.12.3 1
# route -n add 172.16.6.0 172.16.12.3 1
# route -n add 172.16.3.0 172.16.12.3 1
# route -n add 172.16.9.0 172.16.12.3 1
```

![](imgs/Pasted%20image%2020260225152923.png)

In questo modo ogni rete viene raggiunta tramite il gateway corretto (`172.16.12.3`).

---

### **8. Aggiunta automatica delle rotte all’avvio**

Per rendere permanenti le rotte statiche, è possibile inserirle nei file di inizializzazione del sistema.

#### **Esempio Unix**

```bash
/etc/init.d/inetinit
```

Contenuto:

```bash
route -n add default 172.16.12.1 1 > /dev/console
route -n add 172.16.1.0 172.16.12.3 1 > /dev/console
route -n add 172.16.6.0 172.16.12.3 1 > /dev/console
```

#### **Esempio Linux**

```bash
/etc/rc.d/rc.local
```

> Così le rotte statiche vengono caricate automaticamente a ogni riavvio del sistema.

---

### **9. Conclusione**

L’instradamento statico è:

- **semplice da configurare** e **molto prevedibile**,
    
- ma **poco flessibile**: non reagisce ai guasti o ai cambiamenti della topologia.
    

> È adatto a **piccole reti stabili** o come **punto di partenza** per comprendere i meccanismi dell’instradamento dinamico, che verrà trattato nelle prossime lezioni.


---

![](imgs/Pasted%20image%2020251125072710.png)

