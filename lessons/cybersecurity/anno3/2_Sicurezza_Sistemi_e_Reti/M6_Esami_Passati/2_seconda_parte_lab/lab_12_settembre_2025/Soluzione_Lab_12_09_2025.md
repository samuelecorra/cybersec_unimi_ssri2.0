# Soluzione Laboratorio SSRI — 12/09/2025

**Docenti: S. Cimato – M. Anisetti** · Prova open book

> ℹ️ **Questo appello di laboratorio è identico a quello del 02/07/2025.** Il file di traffico (`esame12set.pcap`) è **byte-identico** a `esame-2lug.pcap` (stesso MD5 `2b3d5a45e8a1d157a2bd577980eb044c`) e l'**esercizio firewall coincide** (stessa topologia e stessa policy). La soluzione completa e commentata è quindi la stessa: **[→ Soluzione Laboratorio 02/07/2025](../lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md)**. Di seguito il riepilogo essenziale, autosufficiente per lo studio.

---

## Parte 1 — Wireshark (riepilogo)

- **Prima connessione TCP:** `10.6.27.102:49157 → 23.63.254.163:80` (a `t ≈ 14,2 s`). Client `10.6.27.102`, MAC `00:08:02:1c:47:ae` (**HP**); MAC di destinazione = gateway `20:e5:2a:b6:93:f1` (**ASUS**), perché il server è remoto.
- **HW/SW del client:** PC **HP**, **Windows 7 (NT 6.1) a 64 bit con Internet Explorer 11** (dallo `User-Agent`). La prima connessione (`www.msftncsi.com`, `GET /ncsi.txt`) è il **controllo di connettività di Windows**, traffico legittimo.
- **File richiesti:** dal server **`smart-fax.com`** (`107.180.50.162`) vengono scaricati **`/Documents/Invoice&MSO-Request.doc`** (`application/msword`) e **`/knr.exe`** (`application/x-msdownload`, eseguibile Windows).
- **Descrizione della sessione:** catena di infezione — controllo NCSI di Windows → DNS di `smart-fax.com` → download del finto documento-fattura → download di `knr.exe` → connessioni ripetute verso **`dunlop.hopto.org` (`23.105.131.229`) sulla porta 2032**, canale di **comando e controllo (C2)** del malware.
- **Tentativo di phishing:** il **finto documento di fattura** `Invoice&MSO-Request.doc` è l'esca di ingegneria sociale che veicola l'eseguibile malevolo, il quale apre poi il C2 verso un dominio **DNS dinamico** (`hopto.org`). Da distinguere dal rumore legittimo (`msftncsi.com`, WPAD/LLMNR).

## Parte 2 — Firewall (riepilogo)

Topologia Reception (`10.0.0.0/24`) / Servizi (`10.0.4.0/24`: Auth `.11`, Backup `.15`, DB `.10`) / AP-Guest (AP `10.0.1.10`, Wi-Fi `192.168.4.0/24`) / Internet (`wan0 75.23.48.1`). Regole: Reception → Backup (443, 445) e → Auth (443); AP → Auth (443); guest → **solo Internet** (80/443 + DNS); Backup **esposto su 5000** via DNAT; Auth↔DB è **intra-subnet** (non filtrato dal firewall); default deny finale. La tabella completa a otto colonne, con le note su bridge/DNAT e sull'isolamento dei guest, è nella soluzione del [02/07/2025](../lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md).
