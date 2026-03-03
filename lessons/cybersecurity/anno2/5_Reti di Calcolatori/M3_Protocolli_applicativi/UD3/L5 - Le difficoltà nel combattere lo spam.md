# **Lezione 5: Le difficoltà nel combattere lo spam**

---

### **1. Introduzione**

Dopo aver visto come nasce e si diffonde lo spam, questa lezione si concentra sulle **strategie di difesa** adottate nel tempo.  
Nonostante decenni di tentativi, **eliminare completamente lo spam è impossibile**: ogni contromisura tecnica ha generato **una reazione da parte degli spammer**, in una vera e propria corsa agli armamenti digitali.

---

### **2. Tecniche anti-spam iniziali**

#### **Blocco degli indirizzi IP dinamici**

Una delle prime idee per contrastare lo spam di tipo **“no relay”** (cioè inviato direttamente dagli host degli spammer) è stata quella di **bloccare gli indirizzi IP dinamici**.  
L’idea era semplice: se i provider impedissero ai client con IP dinamico di inviare posta, solo i server autorizzati potrebbero spedire messaggi.

Tuttavia, questo metodo è **poco pratico** perché:

- non esiste un modo semplice per riconoscere se un indirizzo IP è dinamico o statico;
    
- molti utenti legittimi usano connessioni dinamiche;
    
- il blocco risulterebbe troppo esteso e penalizzante.
    

---

### **3. Honeypot: le trappole per spammer**

Una tecnica più raffinata è quella delle **honeypot** (letteralmente “barattoli di miele”), ossia **finti server SMTP** o **caselle e-mail inesistenti**, create appositamente per attirare gli spammer.

Il loro scopo è:

- intercettare i messaggi di spam;
    
- **registrare gli indirizzi IP dei mittenti**;
    
- aggiornare le **blacklist** o fornire prove per azioni legali.
    

Tuttavia, la **diffusione lenta delle segnalazioni** raccolte dalle honeypot le rende **inefficaci in tempo reale**: sono più utili per indagini e azioni legali che per la difesa immediata.

---

### **4. Filtraggio orientato al contenuto**

Poiché i metodi basati su IP si rivelarono inefficaci, nei primi anni 2000 la lotta allo spam si spostò sul **contenuto dei messaggi**.  
L’idea: identificare lo spam non per _chi lo invia_, ma per _cosa contiene_.

#### **Funzionamento del filtraggio collaborativo**

- Gli spammer spesso inviano **lo stesso messaggio a milioni di utenti**.
    
- Se un certo numero di utenti segnala un messaggio come spam (spostandolo nella cartella “Posta indesiderata”), il suo **hash MD5** viene condiviso tra i server SMTP.
    
- Quando arriva un nuovo messaggio con lo stesso hash, il server lo riconosce e lo **scarta automaticamente**.
    

Questo metodo si basa sul principio della **collaborazione tra utenti e server**, analogo ai meccanismi usati per aggiornare gli antivirus.

---

### **5. SpamAssassin**

Uno dei software più diffusi per il filtraggio antispam è **SpamAssassin**, oggi considerato una **soluzione di riferimento** a livello mondiale.

#### **Come funziona**

- Analizza sia l’**intestazione** che il **contenuto** dei messaggi.
    
- Usa **regole euristiche**, **blacklist pubbliche** e **analisi statistiche**.
    
- A ogni messaggio assegna un **punteggio (“score”)** basato sulla probabilità che sia spam.
    
- Se il punteggio supera una certa soglia (es. 6.0), il messaggio viene contrassegnato o spostato nella posta indesiderata.
    

Esempio di intestazione aggiunta da SpamAssassin:

```
X-Spam-Status: No, hits=3.8 required=6.0
```

In questo caso, il messaggio **non è stato classificato come spam** (3.8 < 6.0).

SpamAssassin sfrutta anche **Vipul’s Razor**, una **rete distribuita di utenti e server** che condividono in tempo reale hash e metadati dei messaggi di spam conosciuti, aggiornando continuamente il database globale.

---

### **6. Il teorema di Bayes applicato allo spam**

Un altro approccio molto influente è quello **bayesiano**, proposto da **Paul Graham**.  
Si basa su un’idea semplice ma potente: stimare la **probabilità che un messaggio sia spam** in base alle **parole che contiene**.

#### **a. L’idea base**

Ogni volta che un utente riceve una mail, il filtro analizza le parole e calcola:

- quanto spesso appaiono nei messaggi di spam (`P(O|H)`),
    
- quanto spesso appaiono nei messaggi legittimi (`P(O|¬H)`).
    

Il filtro poi decide se classificare il messaggio come spam valutando **P(H|O)**, cioè la probabilità che il messaggio sia spam dato che contiene quella parola.

#### **b. Formula di Bayes**

$$  
P(H|O) = \frac{P(O|H) \cdot P(H)}{P(O)}  
$$

dove:

- $P(H)$ è la probabilità generale che un messaggio sia spam (stimata dal numero di messaggi nella cartella “Junk Mail”);
    
- $P(O|H)$ è la probabilità che uno spam contenga una parola specifica (“sex”, “free”, “win”...);
    
- $P(O)$ è la probabilità complessiva che la parola appaia nei messaggi ricevuti.
    

#### **c. Efficacia del metodo**

Paul Graham osservò che il filtro bayesiano **perde solo circa 5 messaggi di spam ogni 1000**, risultando **molto più preciso** dei sistemi basati su regole statiche.

---

### **7. Le contromisure degli spammer**

Gli spammer, a loro volta, hanno imparato a **ingannare i filtri intelligenti**.  
Alcune tecniche comuni:

#### **a. List splitting**

Personalizzare il contenuto dello spam per ogni destinatario, cambiando parole o frasi, in modo che i messaggi **non abbiano più lo stesso hash MD5**.

#### **b. Spam grafico**

Convertire il testo dello spam in **immagini** (formato PNG o GIF) che contengono le parole del messaggio.  
Poiché un filtro testuale non può “leggere” un’immagine, il messaggio **sfugge al riconoscimento**.

Questa tecnica sfrutta una debolezza simile a quella dei **CAPTCHA**:

> ciò che è facile da leggere per un essere umano è difficile da interpretare per un software.

Fortunatamente, **creare immagini personalizzate per ogni destinatario** è complesso e costoso, per cui la tecnica non è stata applicata su larga scala.

---

### **8. Difese contro la raccolta di indirizzi**

Un modo per ridurre lo spam è **limitare la disponibilità pubblica degli indirizzi e-mail**, togliendo agli spammer la loro “benzina”.

Gli spammer usano programmi chiamati **harvester** o **spambot** che scansionano il web alla ricerca di indirizzi di posta.  
Per difendersi, molti siti adottano strategie semplici ma utili:

#### **a. Offuscamento del testo**

Scrivere gli indirizzi in forma non standard:

```
ernesto.damiani AT unimi DOT it
```

Tuttavia, gli spambot moderni riescono facilmente a riconoscere queste sostituzioni.

#### **b. Indirizzi come immagini**

Pubblicare l’indirizzo e-mail **sotto forma di immagine** invece che testo.  
Funziona contro i bot, ma è **scomodo per gli utenti** (non possono copiare l’indirizzo).

#### **c. Codifica HTML**

Codificare l’indirizzo carattere per carattere usando i codici HTML:

```
someone@example.com → &#115;&#111;&#109;&#101;&#111;&#110;&#101;&#64;&#101;&#120;&#97;&#109;&#112;&#108;&#101;&#46;&#99;&#111;&#109;
```

Ma anche questo metodo è facilmente decodificabile, perché i codici corrispondono esattamente a lettere ASCII note.

#### **d. Tecniche crittografiche**

Le soluzioni più avanzate prevedono la **cifratura dell’indirizzo e-mail**, che viene **decifrato solo tramite uno script** quando l’utente lo utilizza.  
In questo modo l’indirizzo **non compare mai in chiaro** nel codice della pagina web.

---

### **9. Conclusione**

La lotta allo spam è un **gioco a somma zero**: ogni nuova difesa genera una nuova strategia di attacco.  
I sistemi moderni combinano più livelli di protezione:

- **filtri bayesiani**,
    
- **reti collaborative** come SpamAssassin e Razor,
    
- **analisi comportamentale**,
    
- **autenticazioni DNS** come SPF, DKIM e DMARC.
    

Nonostante tutto, lo spam rimane una delle **principali sfide aperte** della sicurezza Internet, simbolo di come **problemi semplici di protocollo** possano generare **conseguenze globali**.