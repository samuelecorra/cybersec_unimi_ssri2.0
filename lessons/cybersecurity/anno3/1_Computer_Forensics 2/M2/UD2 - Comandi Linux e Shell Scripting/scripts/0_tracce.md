# 🟩 LIVELLO 1 — Fondamentali assoluti (warm-up)

### **1. Somma di due numeri (il tuo suggerimento ✔️)**

Scrivi uno script `somma.sh` che:

1. chiede due numeri
    
2. li somma
    
3. stampa il risultato
    

Usa:

- `read`
    
- `echo`
    
- sostituzione aritmetica `$(( ... ))`
    

### **2. Stampa della data con command substitution**

Script `data.sh` che fa:

```
Oggi è:  <data>
```

dove `<data>` è preso con:

```
$(date)
```

### **3. Dire se un file esiste**

Script `exist.sh`:

- prende un argomento `$1`
    
- controlla `if [ -f "$1" ]`
    
- stampa “file presente/non presente”
    

### **4. Stampare il proprio nome utente**

Usa la variabile d’ambiente `$USER`.

---

# 🟨 LIVELLO 2 — Metacaratteri, I/O, quoting

### **5. Salva in un file tutto ciò che l’utente digita**

Script `salvadati.sh`:

- chiede del testo
    
- lo salva in `output.txt` usando redirezione `>`
    

### **6. Appendi un log con timestamp**

Script `log.sh`:

- prende una frase
    
- la appende in `logfile.txt` con:
    

```
echo "$(date): $1" >> logfile.txt
```

### **7. Mostrare il contenuto dei `.txt` con wildcard**

Script `mostra_testi.sh`:

- usa `cat *.txt`
    

### **8. Contare le righe di tutti i file nella cartella**

Usa `wc -l *`.

### **9. Mostrare l’elenco dei processi e filtrare bash**

Usa pipe:

```
ps aux | grep bash
```

Scrivi uno script che fa questo automaticamente.

### **10. Differenza tra quoting singolo e doppio**

Script che faccia:

```
echo '$USER'
echo "$USER"
```

per mostrare la differenza.

---

# 🟧 LIVELLO 3 — Sequenze, condizionamenti, operatori shell

### **11. Script che dice se un numero è pari o dispari**

Usa `if`, `$((n % 2))`, quoting, read.

### **12. Script che testa più condizioni**

“Scrivi un programma che dica:

- se il numero è > 100
    
- se è compreso tra 10 e 100
    
- se è < 10”
    

### **13. Script che richiede ripetutamente input finché non scrivi exit**

Loop:

```
while true; do
    read cmd
    [ "$cmd" = "exit" ] && break
done
```

### **14. Script che calcola la media di N numeri**

Usa:

- loop
    
- variabile accumulatore
    
- command substitution
    

### **15. Script che controlla se un utente è loggato**

Usa:

```
who | grep "$1"
```

---

# 🟥 LIVELLO 4 — Raggruppamento, sub-shell, background

### **16. Esegui due comandi in background e salva l’output**

Script:

```
(ls -l && date) > report.txt &
```

Capire:

- operatori `&&`
    
- subshell `( ... )`
    
- background `&`
    

### **17. Script che misura il tempo di esecuzione**

Usa:

```
start=$(date +%s)
# fai qualcosa
end=$(date +%s)
echo $((end - start))
```

### **18. Script che crea una subshell per navigare in una directory**

Esempio:

```
(
cd /etc
ls
)
```

Mostra che **fuori** sei ancora nel path iniziale.

---

# 🟪 LIVELLO 5 — Variabili locali, globali, funzioni

### **19. Script con variabili locali e globali**

Funzione che usa:

```
local var="CIAO"
```

e una globale `X=123`.

### **20. Script con funzioni matematiche**

Esempio:

```
somma() { echo $(( $1 + $2 )); }
```

### **21. Script che esporta una variabile e la legge da una subshell**

Dimostra differenza tra:

- variabile locale a script
    
- variabile esportata (`export`)
    

### **22. Script che genera una password casuale**

Usa:

```
tr -dc A-Za-z0-9 < /dev/urandom | head -c 16
```

---

# 🟦 LIVELLO 6 — Mini progetti utili nella tua macchina

> Ora iniziamo gli script **veramente utili**, roba che userai davvero in VM.

---

## 🟦 23. Script “organizza_downloads.sh”

Ordina automaticamente i file della cartella Downloads:

- immagini → ~/Pictures
    
- pdf → ~/Documents
    
- video → ~/Videos
    
- zip → ~/Archives  
    Usa wildcard:
    

```
mv *.jpg ~/Pictures
mv *.png ~/Pictures
mv *.pdf ~/Documents
...
```

---

## 🟦 24. Script “backup_home.sh”

Crea un archivio con:

```
tar -czf backup_$(date +%F).tar.gz ~
```

---

## 🟦 25. Script “kill_bash_zombie.sh”

Trova e uccide tutti i processi “bash” non interattivi:

```
ps aux | grep bash | grep -v grep
```

Poi `kill`.

---

## 🟦 26. Script “conta_linee.sh”

Conta tutte le righe di tutti i tuoi `.sh`, stampa la somma totale.

---

## 🟦 27. Script “clean_tmp.sh”

Pulisce la cartella `/tmp` chiedendo conferma.

---

## 🟦 28. Script “rename_lowercase.sh”

Converte tutti i file della cartella in minuscolo con:

```
mv "$f" "$(echo "$f" | tr A-Z a-z)"
```

---

## 🟦 29. Script “github_push.sh”

Automatizza:

```
git add .
git commit -m "Aggiornamento automatico del $(date)"
git push
```

---

## 🟦 30. Script “monitor_cpu.sh”

Ogni 2 secondi stampa:

```
date
top -b -n1 | head -n 10
```

---

## 🟦 31. Script “trova_grandi.sh”

Trova i file > 50MB:

```
find . -size +50M
```

---

## 🟦 32. Script “converti_mp4_mp3.sh”

Per tutti i `.mp4` in una cartella, esegue:

```
ffmpeg -i video.mp4 audio.mp3
```

---

## 🟦 33. Script “ricerca_in_tutti_i_md.sh”

Cerca una parola in TUTTI i tuoi appunti dell’intera vault di Obsidian:

```
grep -R "$1" ~/cybersamu-gitlocker
```

---

## 🟦 34. Script “report_storage.sh”

Stampa in modo leggibile:

- spazio totale
    
- file più grandi
    
- cartelle più pesanti
    
- processi che occupano più RAM
    

---

# 🟪 LIVELLO 7 — Progetti più avanzati (veri strumenti da hacker etico)

Se vuoi andare avanti:

### **35. Un mini-shell personale (myshell.sh)**

Con:

- prompt personalizzato
    
- lettura comandi
    
- subshell per eseguirli
    
- history
    

### **36. Script “watchdog” che controlla se un processo è vivo e lo riavvia**

Usa `pgrep` + loop infinito.

### **37. Script che cifra e decifra file con `openssl`**

AES-256.

### **38. Script che produce un log giornaliero automatico**

Usa cron.

### **39. Script che crea report hardware**

Con `lscpu`, `lsblk`, `lshw`.

### **40. Script che monta e smonta automaticamente una penna USB**

Riconoscimento con `udevadm`.

---

# 🚀 INIZIAMO DAL PRIMO: “Somma di due numeri”

Dimmi se vuoi la **soluzione**, oppure vuoi che ti lasci un “canovaccio” per arrivarci da solo e poi controllare la tua versione.

Vuoi:

- 🟢 la soluzione completa (con commenti educativi)?
    
- 🔵 una guida passo-passo
    
- 🟣 un sistema di “livelli” tipo videogame (ti do solo gli obiettivi)?
    
- 🔴 oppure vuoi mostrarlo tu e te lo correggo?