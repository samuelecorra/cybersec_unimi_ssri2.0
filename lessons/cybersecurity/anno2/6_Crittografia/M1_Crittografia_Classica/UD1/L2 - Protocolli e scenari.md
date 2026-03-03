# **Lezione 2: Protocolli e scenari**

---

### **1. Dal documento fisico al documento digitale**

Nell’era pre-digitale, un **documento fisico** (una lettera, un contratto, un testamento) possedeva **caratteristiche materiali** che ne garantivano la sicurezza in modo naturale:

- La **copia** era **distinguibile** dall’originale (ad esempio per inchiostro, carta, timbro).
    
- Ogni **alterazione** lasciava **tracce visibili**.
    
- L’**autenticità** si basava su elementi **fisici unici**, come la firma autografa, la ceralacca o il sigillo.
    

In altre parole, la **fisicità stessa** del documento era la sua **protezione**.

---

### **2. L’informazione digitale e i nuovi problemi di sicurezza**

Con la digitalizzazione, un **documento** diventa un **insieme di bit**, ossia di 0 e 1.  
Questo cambia completamente la natura della sicurezza:

- I documenti digitali sono **facilmente duplicabili**:  
    ogni copia è **identica** all’originale, senza alcuna differenza rilevabile.
    
- Sono **facilmente modificabili**:  
    è difficile accorgersi se un file è stato alterato, e dove.
    
- È complesso **dimostrare l’autenticità** o **identificare l’autore**.  
    Chiunque può generare un nuovo documento perfettamente plausibile.
    

➡️ In ambiente digitale **non esistono più tracce fisiche** che garantiscano la fiducia.  
Serve quindi un nuovo tipo di protezione: quella **logico-matematica**, fornita dalla **crittografia**.

---

### **3. I protocolli crittografici**

Un **protocollo crittografico** (o **schema**) è un **insieme di regole e interazioni** tra più entità volto a garantire determinate **proprietà di sicurezza**.

Un protocollo definisce:

- **chi comunica** (le entità coinvolte, es. _Alice_ e _Bob_);
    
- **come** comunicano (le fasi di scambio dei messaggi);
    
- **quali garanzie** vogliono ottenere (es. riservatezza, autenticità, integrità).
    

Esempio astratto:

```
Alice  ↔  Bob
```

Obiettivo: garantire che il messaggio inviato da Alice arrivi a Bob  
**senza essere letto, modificato o falsificato** da terzi.

Le proprietà che un protocollo può fornire includono:

- **Segretezza (confidentiality)**
    
- **Autenticità (authentication)**
    
- **Integrità (integrity)**
    
- **Non ripudio (non-repudiation)**
    

---

### **4. Scenario ideale e scenario reale**

Nel **mondo ideale**, le comunicazioni avvengono solo tra Alice e Bob, in un canale perfettamente sicuro:  
nessun intruso può leggere o modificare i messaggi.

![](imgs/Pasted%20image%2020251124225252.png)

Nel **mondo reale**, invece, il canale è insicuro e attraversato da:

![](imgs/Pasted%20image%2020251124225411.png)

- potenziali **attaccanti**,
    
- sistemi intermedi,
    
- software non affidabili.
    

Rappresentazione concettuale:

```
Scenario ideale:
Alice  ↔  Bob

Scenario reale:
Alice  ↔  (Rete insicura con attaccanti)  ↔  Bob
```

I protocolli servono proprio a **trasformare uno scenario insicuro** in **uno scenario logicamente sicuro**, anche in presenza di avversari.

---

### **5. Scenari tipici di comunicazione**

#### **Scenario 1 – Comunicazione confidenziale**

Alice vuole inviare a Bob un messaggio sicuro.  

![](imgs/Pasted%20image%2020251124225401.png)

Obiettivo: solo Bob deve poterlo leggere. Bisogna quindi presupporre che ogni possibile interazione fra due partecipanti possa essere condotta sotto il possibile intervento di un avversario che può agire dall'esterno e tentare di accedere al contenuto, o modificarlo, o intercettando il messaggio ed impedire che il destinatario ne venga a conoscenza!
Soluzione: **cifratura** (es. AES, RSA, ecc.).

#### **Scenario 2 – Autenticazione**

Un'assunzione ancor più realistica è quella in cui anche i partecipanti allo schema possono comportarsi in maniera scorretta. Sia i partecipanti sia gli avversari esterni tentano di evitare che lo scambio vada a buon fine, provando ad accedere al contenuto per modificarlo, o impadronirsi della moneta elettronica che viene scambiata...
In questi casi un protocollo crittografico fornirà dettagliatamente in che modo Alice deve impacchettare il messaggio o la moneta elettronica di modo da evitare qualsiasi comportamento malizioso esterno o dell'altro partecipante e al contempo indicherà come Bob, legittimo destinatario, deve accedere al contenuto del messaggio o come deve accedere ai soldi oggetto dello scambio elettronico.

![](imgs/Pasted%20image%2020251124225924.png)

In entrambi i casi, la sicurezza non dipende dal canale, ma **dalla matematica** che governa le chiavi e le funzioni crittografiche.

---

### **6. Da chi dobbiamo difenderci?**

I protocolli di sicurezza devono considerare **diversi tipi di nemici**:

1. **Utenti disonesti** – soggetti interni che abusano del sistema.
    
2. **Nemici esterni** – hacker o terze parti non autorizzate.
    
3. **Nemici interni** – amministratori o operatori che violano la fiducia.
    

E i nemici **non sono sempre umani**.  
Possono essere anche:

- **Hardware compromesso**,
    
- **Software malevolo** (virus, worm, trojan).

---

### **7. Ambienti di esecuzione e rischi pratici**

I protocolli sono progettati per **determinati ambienti** (reti, dispositivi, sistemi operativi),  
ma nella realtà le condizioni possono cambiare e diventare **ostili**.

Esempio:

> Un telefono cellulare, progettato per ricevere chiamate, può **divulgare automaticamente la posizione** e altre informazioni sensibili.

Questo dimostra che la **sicurezza teorica** di un protocollo non basta: serve anche una **sicurezza contestuale**, che tenga conto dell’ambiente reale in cui viene implementato.

---

### **8. Scenari e applicazioni moderne**

#### **(1) Servizi pubblici e cittadini**

La società moderna fornisce numerosi servizi digitali (e-banking, sanità, PA online) rivolti a utenti onesti.  

![](imgs/Pasted%20image%2020251124230250.png)

La crittografia serve a:

- prevenire **abusi** e **frodi**,
    
- contrastare fenomeni come il **riciclaggio di denaro** o l’uso improprio dei dati personali.
    

#### **(2) Servizi commerciali e multimediali**

Aziende e piattaforme distribuiscono contenuti digitali a pagamento (giornali, musica, film, software).  
Qui la crittografia serve a:

- evitare **pirateria** e **condivisione illegale**,
    
- garantire **pagamenti sicuri** e **diritti d’autore**.
    

![](imgs/Pasted%20image%2020251124230348.png)

Esempi reali: **Napster**, **Kazaa**, piattaforme di streaming, e-commerce e abbonamenti online.

---

### **9. Conclusione**

La crittografia non è solo una tecnica di cifratura:  
è un **insieme di protocolli** che definiscono **come** le entità devono comportarsi per comunicare in modo **sicuro e verificabile**.

Essa colma il vuoto lasciato dalla scomparsa delle garanzie fisiche nel mondo digitale, fornendo **fiducia logica e matematica** in un ambiente dove tutto può essere copiato, alterato o falsificato.