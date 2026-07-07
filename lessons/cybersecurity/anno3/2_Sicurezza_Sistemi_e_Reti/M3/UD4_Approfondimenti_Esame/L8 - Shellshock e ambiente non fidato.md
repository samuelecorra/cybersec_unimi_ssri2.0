# **UD4 - Approfondimenti d'esame: Shellshock e ambiente non fidato**

Fonte integrata: contenuto già presente nella precedente lezione `L2 - Shellshock.md`, ora ricollocato come chiusura naturale del blocco su variabili di ambiente, Set-UID e invocazione di shell.

### **1. Perché Shellshock sta in questa unità**

Shellshock è il naturale prolungamento del discorso sui programmi Set-UID e sugli attacchi via ambiente. Tutti questi casi ruotano attorno alla stessa idea: le variabili di ambiente sono input controllabile dall'utente o da un attaccante, e diventano pericolose quando raggiungono un interprete, un demone, uno script CGI o un programma privilegiato.

Shellshock è il caso più estremo: un difetto del parser di **bash** trasformava una variabile di ambiente in esecuzione immediata di codice.

---

### **2. Il meccanismo legittimo: esportare funzioni via ambiente**

Bash permette non solo di esportare *variabili* verso i processi figli, ma anche **funzioni di shell**. Il trucco storico consisteva nel codificare una funzione dentro una variabile di ambiente il cui valore comincia con la sintassi `() { ... }`:

```bash
$ foo() { echo "sono una funzione"; }
$ export -f foo
```

Internamente bash esporta questo verso i figli come una variabile di ambiente del tipo:

```
foo='() {  echo "sono una funzione"; }'
```

Quando un nuovo processo **bash** parte, scandisce le variabili di ambiente ricevute: se ne trova una il cui valore inizia con `() {`, la interpreta come **definizione di funzione** e la rende disponibile nella nuova shell. È un meccanismo comodo per propagare funzioni ai sotto-processi, e di per sé del tutto legittimo.

---

### **3. Il bug: il parser non si ferma dopo la parentesi graffa**

Il difetto (scoperto da Stéphane Chazelas e reso pubblico il **24 settembre 2014**, CVE-2014-6271) è tanto semplice quanto devastante: nel parsare il valore della variabile-funzione, **bash non si fermava alla chiusura `}`**. Continuava a leggere e a **eseguire immediatamente** qualunque comando scritto *dopo* la definizione della funzione, e lo faceva **all'avvio della shell**, prima ancora di eseguire lo script o il comando richiesto.

La prova classica in una riga:

```bash
$ env x='() { :; }; echo VULNERABILE' bash -c "echo test"
VULNERABILE
test
```

Analisi: `env` imposta la variabile di ambiente `x` con valore `() { :; }; echo VULNERABILE` e lancia `bash -c "echo test"`. All'avvio, bash vede che `x` è una funzione esportata (`() { :; }`), la definisce, ma poi **prosegue** ed esegue `echo VULNERABILE`. Solo dopo lancia il comando richiesto (`echo test`). Su un sistema patchato la riga stampa solo `test`; se compare `VULNERABILE`, bash è vulnerabile.

> ⚠️ Il punto cruciale: il codice iniettato viene eseguito **al solo avvio di bash**, senza che l'attaccante debba far eseguire nulla di particolare. Basta che riesca a **impostare una variabile di ambiente** che raggiunge un'invocazione di bash. Da qui la portata dell'attacco.

---

### **4. Perché è così grave: i vettori di attacco**

Il valore di Shellshock non sta nel bug in sé, ma nel numero enorme di contesti in cui **input esterno finisce in una variabile di ambiente passata a bash**. I vettori principali:

#### **4.1. CGI su web server (il vettore remoto e più famoso)**

Con `mod_cgi` di Apache, il server web copia **le intestazioni HTTP della richiesta in variabili di ambiente** prima di invocare lo script CGI: l'header `User-Agent` diventa `HTTP_USER_AGENT`, `Referer` diventa `HTTP_REFERER`, e così via. Se lo script CGI è (o invoca) **bash** — cosa comunissima con `#!/bin/bash` o con una `system()` interna — allora un attaccante remoto e **non autenticato** può iniettare codice semplicemente mettendo il payload in un'intestazione:

```bash
curl -H "User-Agent: () { :; }; /bin/cat /etc/passwd" \
     http://vittima/cgi-bin/status
```

Il web server (che spesso gira come utente `www-data`, talvolta con privilegi maggiori) esporta l'header malevolo in `HTTP_USER_AGENT`, lancia bash per lo script CGI, e bash esegue `cat /etc/passwd` all'avvio. Da lì l'attaccante può aprire reverse shell, scaricare malware, muoversi lateralmente.

#### **4.2. Client DHCP**

Un server DHCP malevolo può inserire stringhe di attacco in alcuni campi della risposta (es. il nome host o opzioni personalizzate). Il client `dhclient` esegue **hook script come root** passando quei valori in variabili di ambiente: se gli hook sono bash, si ottiene esecuzione di codice **come root** sulla macchina che si è appena connessa alla rete.

#### **4.3. SSH con comandi forzati / shell ristrette**

Contesti che usano `ForceCommand` (es. Git, rsync ristretti) accettano dal client la variabile `SSH_ORIGINAL_COMMAND` in ambiente: un client autenticato ma limitato può usarla per evadere la restrizione ed eseguire comandi arbitrari.

#### **4.4. Programmi Set-UID che invocano bash**

Chiudendo il cerchio con [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md): un binario Set-UID root che chiama `system()` eredita l'ambiente dell'attaccante. Se `/bin/sh` è bash e il sistema non è patchato, l'attaccante imposta una variabile-funzione con coda malevola e ottiene **privilege escalation locale** all'avvio della shell interna.

> 💡 Il filo conduttore è sempre lo stesso: *dato non fidato* (header HTTP, opzione DHCP, variabile SSH, ambiente utente) → *finisce in una variabile di ambiente* → *raggiunge un'invocazione di bash* → *codice eseguito con i privilegi del processo ospite*. Cambiano solo il canale d'ingresso e i privilegi ottenuti.

---

### **5. Impatto storico**

Il codice difettoso era presente in bash **fin dal 1989**: la vulnerabilità è rimasta latente per circa **25 anni**. Alla divulgazione risultavano esposti milioni di server web, dispositivi di rete, sistemi embedded e Mac OS X. La combinazione *remoto + non autenticato + esecuzione di codice + facilmente automatizzabile* la rese immediatamente **wormable**: nel giro di ore comparvero botnet che scandivano Internet in cerca di CGI vulnerabili. Per gravità e diffusione è spesso accostata a Heartbleed (cfr. [M4/UD4/L7 – Attacchi Heartbleed e BEAST](../../M4/UD4/L7%20-%20Attacchi%20Heartbleed%20e%20BEAST.md)), con una differenza sostanziale: Heartbleed **leggeva** memoria, Shellshock **esegue** comandi.

---

### **6. Contromisure**

1. **Patch di bash**: la correzione modifica il modo in cui vengono importate le funzioni dall'ambiente. Bash non interpreta più *qualunque* variabile che inizia con `() {`, ma solo quelle con un nome speciale e prefissato (`BASH_FUNC_nome%%`), e soprattutto **smette di parsare** dopo la definizione, senza eseguire code trailing. Le CVE successive (6277, 7169, 7186, 7187) hanno chiuso ulteriori varianti emerse subito dopo.
2. **Non usare bash per i CGI**: preferire linguaggi/interpreti che non ereditano questo comportamento, o shell minimali; disattivare `mod_cgi` dove non serve.
3. **Ripulire e minimizzare l'ambiente** prima di invocare interpreti, specie in processi privilegiati (stesso principio della difesa contro l'attacco `PATH`).
4. **Privilegio minimo e isolamento**: far girare i CGI con utenti dedicati e con il minor numero di privilegi, in modo che anche un'esecuzione riuscita produca danni contenuti.

> ✅ **Ricapitolando** — Shellshock è un difetto del parser di bash che, importando una funzione da una variabile di ambiente della forma `() { … }; <comando>`, eseguiva il `<comando>` in coda all'avvio della shell. Chiunque potesse impostare una variabile di ambiente destinata a bash — soprattutto tramite le intestazioni HTTP dei CGI, ma anche via DHCP, SSH o binari Set-UID — otteneva esecuzione di codice arbitrario con i privilegi del processo ospite, spesso da remoto e senza autenticazione. La difesa primaria è la patch di bash; quelle strutturali sono la rinuncia a bash nei CGI, la ripulitura dell'ambiente e il privilegio minimo.

Collegamenti: [L3 - Variabili d'ambiente nei processi Unix](L3%20-%20Variabili%20d'ambiente%20nei%20processi%20Unix.md), [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md), [M4/UD4/L7 - Attacchi Heartbleed e BEAST](../../M4/UD4/L7%20-%20Attacchi%20Heartbleed%20e%20BEAST.md).
