## **Lezione 5: Estrazione di file, credenziali e individuazione di phishing**

### **1. Estrarre i file trasferiti (Export Objects)**

Quando in una cattura passa un file non cifrato (via HTTP, FTP, SMB, TFTP), Wireshark può ricostruirlo e salvarlo. Il comando è **File → Export Objects → HTTP** (o *FTP-DATA*, *SMB*, *TFTP*). Si apre l'elenco degli oggetti trasferiti con host, nome, tipo di contenuto e dimensione; da lì si salva il file sul disco per analizzarlo.

> 💡 Nella traccia del 02/07 questa vista mostra, sotto l'host `smart-fax.com`, due oggetti scaricati: `Documents/Invoice&MSO-Request.doc` (`application/msword`) e `knr.exe` (`application/x-msdownload`). È il modo più diretto per rispondere a "*quali file sono stati richiesti?*".

Per l'FTP, i file passano sul canale **dati**: si filtra `ftp-data`, si fa **Follow → TCP Stream** sulla connessione dati e si salva il contenuto come *Raw*.

---

### **2. Riconoscere il tipo di file dai magic bytes**

Anche senza estensione affidabile, i **primi byte** di un file (la *magic number*) ne rivelano il tipo. Selezionando l'inizio del payload nel riquadro *Packet Bytes* si leggono in esadecimale/ASCII:

| Magic (hex / ASCII) | Tipo di file |
|---|---|
| `4D 5A` = `MZ` | eseguibile Windows (PE/`.exe`, `.dll`) |
| `50 4B` = `PK` | archivio ZIP (anche `.docx`, `.jar`, `.apk`) |
| `D0 CF 11 E0` | documento Office legacy (`.doc`, `.xls`) |
| `25 50 44 46` = `%PDF` | documento PDF |
| `1F 8B` | dato compresso gzip |
| `FF D8 FF` | immagine JPEG |
| `89 50 4E 47` = `.PNG` | immagine PNG |

> 📌 Il `Content-Type` HTTP dichiara il tipo, ma è la magic number a **confermarlo**: un file servito come `application/x-msdownload` che inizia con `MZ` è senza dubbio un eseguibile — un dettaglio che rafforza la diagnosi di download di malware.

---

### **3. Estrarre le credenziali**

Wireshark ha una vista dedicata: **Tools → Credentials** (nelle versioni recenti) elenca automaticamente le credenziali trovate in chiaro (FTP, HTTP Basic, IMAP, POP, SMTP…), con protocollo, username e il numero del pacchetto. In alternativa si estraggono a mano:

- **FTP**: filtro `ftp.request.command == "USER" || ftp.request.command == "PASS"` (oppure semplicemente `ftp` + Follow Stream) mostra utente e password in chiaro.
- **HTTP Basic**: l'header `Authorization: Basic <base64>` si decodifica banalmente (Base64) rivelando `utente:password`.
- **Form via HTTP**: le credenziali inviate da un form finiscono nel **body di una POST** (`http.request.method == "POST"`), leggibili con Follow HTTP Stream.

> 💡 Esempio (21/02): filtrando `ftp` si legge la sequenza di `USER admin` / `PASS merlin`, `PASS mercury`, … Sono le credenziali *tentate* nel brute-force; la risposta `530 Login incorrect` a ciascuna conferma che nessuna ha funzionato. L'assenza di un `230` è la prova che l'attacco è **fallito**.

---

### **4. Riconoscere un tentativo di phishing / malware nel traffico**

Il phishing e la distribuzione di malware lasciano tracce riconoscibili. Gli indicatori da cercare:

- **Domini sospetti** nel DNS o negli header `Host:`: nomi che imitano marchi noti (typosquatting), o **DNS dinamici** gratuiti (`.hopto.org`, `.no-ip.org`, `.duckdns.org`) tipici di server di comando e controllo (C2).
- **Download di file "esca"** con nomi che sfruttano l'ingegneria sociale: documenti "Invoice", "Fattura", "Receipt", "CV", spesso `.doc`/`.docx` con macro, seguiti dal download di un **eseguibile**.
- **Catena di infezione**: pagina/documento esca → download del payload (`.exe`) → **beacon** verso un C2 (connessioni ripetute a un IP esterno su una porta insolita).
- **Pagine di login contraffatte**: una POST di credenziali verso un dominio che *non* è quello legittimo del servizio imitato.

> 💡 Esempio completo (02/07 e 12/09, stessa cattura): dopo il normale controllo di connettività di Windows (`www.msftncsi.com`), il client (Windows 7 / IE 11, hardware HP) risolve **`smart-fax.com`** e ne scarica `Invoice&MSO-Request.doc` — l'**esca**: un finto documento di fattura. Il documento innesca il download di **`knr.exe`** (eseguibile). Subito dopo l'host contatta il dominio dinamico **`dunlop.hopto.org`** (23.105.131.229) sulla porta **2032**, con connessioni ripetute: è il **canale C2** del malware appena installato. Il "tentativo di phishing" è quindi il **finto invoice document** usato come esca per veicolare il malware, la cui infezione si completa con il beacon verso il C2. La successione DNS → GET del `.doc` → GET del `.exe` → traffico verso `hopto.org` è la firma dell'intero attacco.

> ⚠️ Diffida dei falsi positivi "buoni": non tutto il traffico verso domini strani è malevolo. `msftncsi.com` (Microsoft Network Connectivity Status Indicator), `*.services.mozilla.com` (Firefox), gli OCSP responder (`ocsp.*`, `*.lencr.org`) sono attività **legittime** di sistema/browser. Distinguere il rumore di fondo dal segnale d'attacco è metà del lavoro.

---

### **5. Individuare scansioni e brute-force**

Alcuni pattern parlano da soli nella *Packet List* e nelle statistiche:

- **Port scan**: un host che invia molti `SYN` a porte diverse dello stesso bersaglio in poco tempo; le risposte `RST` indicano porte chiuse (vedi [M4/UD3 – scansione](../../M4/UD3/L1%20-%20Tipi%20e%20Approcci%20di%20Scansione.md)).
- **Brute-force**: molte richieste di autenticazione ravvicinate con esiti negativi. Nel 21/02, ~1400 tentativi `USER`/`PASS` in ~45 secondi con risposte `530` sono la firma inconfondibile di un attacco automatico a dizionario.
- **Esfiltrazione / C2**: connessioni periodiche e regolari verso un singolo IP esterno, spesso di piccole dimensioni (beacon).

> ✅ **Ricapitolando** — Wireshark ricostruisce i file trasferiti in chiaro (*Export Objects*), che si identificano con certezza dai *magic bytes* (`MZ` = eseguibile, `%PDF`, `PK`…). Le credenziali in chiaro si leggono da *Tools → Credentials* o filtrando FTP/HTTP. Il phishing e il malware si riconoscono dalla catena esca (documento "invoice") → payload (`.exe`) → beacon verso un C2 su DNS dinamico, distinguendo però questo segnale dal traffico legittimo di sistema (NCSI, Mozilla, OCSP). Brute-force e scansioni emergono dai pattern ripetitivi nelle statistiche.

Precedente: [[L4 - Analisi per protocollo (ARP, DNS, TCP, TLS, HTTP, FTP)]] · Prossima: [[L6 - Cheat-sheet operativa d'esame]].
