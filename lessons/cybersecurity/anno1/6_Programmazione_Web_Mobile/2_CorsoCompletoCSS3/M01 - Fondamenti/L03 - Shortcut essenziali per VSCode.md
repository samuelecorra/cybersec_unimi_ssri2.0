**Lezione 3 – Shortcut essenziali per usare Visual Studio Code in modo efficiente**

**Perché imparare le scorciatoie fin da subito**

Le shortcut di Visual Studio Code permettono di lavorare molto più velocemente e, soprattutto, senza usare continuamente il mouse. Questo evita movimenti inutili, riduce l’affaticamento ai tendini (problema reale per chi programma tante ore) e permette di mantenere sempre le mani sulla tastiera mentre si scrive, copia, seleziona o modifica codice.  
In questa lezione vediamo solo le scorciatoie fondamentali: quelle che userai costantemente già dal prossimo video.

**Riferimento ai tasti**

Prima di iniziare, viene fatta una rapida panoramica dei tasti fondamentali presenti sulla tastiera:

- _Control_ (CTRL), situato in basso a sinistra.
    
- _Shift_ (in italiano “Maiusc”), subito sopra CTRL. Serve per scrivere in maiuscolo se tenuto premuto.
    
- _Caps Lock_ (“Bloc Maiusc”), sopra Shift: mantiene il maiuscolo fisso finché non viene disattivato.
    
- _Alt_, vicino alla barra spaziatrice.
    
- _Tab_, sopra Caps Lock: inserisce una serie definita di spazi (fondamentale per l’indentazione del codice).
    

Una volta chiarita la posizione di questi tasti, si passa all’uso concreto delle combinazioni importanti.

**Duplicare una riga**

Questa è una delle scorciatoie più utili quando si lavora con codice ripetuto.

- Alt + Shift + Freccia giù → duplica la riga verso il basso
    
- Alt + Shift + Freccia su → duplica la riga verso l’alto
    

È utilissima quando si devono copiare elementi HTML simili (ad esempio più `<li>`, più `<div>`, più righe intere di codice).  
Se si seleziona un blocco di righe e si usa questa combinazione, Visual Studio Code duplica tutto il blocco.

**Spostare una riga**

Quando si vuole riordinare il codice senza tagliare e incollare:

- Alt + Freccia su → sposta la riga verso l’alto
    
- Alt + Freccia giù → sposta la riga verso il basso
    

Questa azione non duplica, ma riorganizza.

**Selezionare più righe senza mouse**

Per selezionare più righe consecutive:

- Shift + Freccia su/giù
    

Questo è il modo più rapido per fare selezioni pulite senza toccare il mouse.  
Una volta selezionate, si possono duplicare o spostare usando le shortcut precedenti.

**Andare a capo mantenendo la riga intatta**

Se ci si trova nel mezzo di una riga e si vuole andare sotto senza spezzarla:

- Ctrl + Invio
    

Questo crea una nuova riga sotto, indipendentemente dalla posizione del cursore.  
Usare solo Invio, invece, divide la riga in due.

**Salvataggio rapido**

- Ctrl + S
    

È la scorciatoia più usata: salva il file e, se si usa Live Server, aggiorna automaticamente la pagina nel browser.  
Se si abilita l’auto-formatting al salvataggio, `Ctrl + S` diventa ancora più potente.

**Auto-format del codice con Prettier**

Per formattare automaticamente HTML, CSS o JavaScript in modo ordinato:

- Alt + Shift + F
    

Questa scorciatoia chiama Prettier e sistema indentazione, allineamento e struttura del codice.  
È fondamentale quando si scrive velocemente e la pagina diventa disordinata.

Si può anche attivare la formattazione automatica al salvataggio, ma per adesso non viene configurata per evitare di complicare l’avvio del corso.

**Completamento automatico dei tag e dei suggerimenti**

Grazie alle estensioni installate:

- scrivendo il nome di un elemento (es. `html`, `h1`, `p`, ecc.) basta premere Tab per far comparire automaticamente l’intero snippet del tag;
    
- quando si digita `<h1>` e si chiude, Visual Studio Code inserisce da solo `</h1>`;
    
- se si rinomina il tag di apertura, si aggiorna automaticamente anche quello di chiusura.
    

Questo eliminia totalmente la possibilità di dimenticare tag chiusi o tag sbagliati.

**Conclusione della lezione**

Queste shortcut rappresentano la base imprescindibile per scrivere codice in modo fluido.  
Avere già queste competenze permette di seguire il corso senza sentirsi impacciati nell’editor.  
Nella prossima lezione si analizzerà finalmente la struttura di una pagina HTML, che sarà fondamentale per comprendere come CSS interagisce con gli elementi della pagina.