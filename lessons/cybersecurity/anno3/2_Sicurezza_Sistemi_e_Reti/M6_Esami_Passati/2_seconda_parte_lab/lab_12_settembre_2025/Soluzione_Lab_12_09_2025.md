# Soluzione Laboratorio SSRI — 12/09/2025

**Docenti: S. Cimato – M. Anisetti** · Prova open book

> ℹ️ **Questo appello di laboratorio è identico a quello del 02/07/2025.** Il file di traffico (`esame12set.pcap`) è **byte-identico** a `esame-2lug.pcap` (stesso MD5 `2b3d5a45e8a1d157a2bd577980eb044c`) e l'**esercizio firewall coincide** (stessa topologia e stessa policy). La soluzione completa e commentata è quindi la stessa: **[→ Soluzione Laboratorio 02/07/2025](../lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md)**. Di seguito il riepilogo essenziale, autosufficiente per lo studio.

---

## Traccia originale dell'appello

**Cognome:**  
**Nome:**  
**Matricola:**

**Sicurezza – SSRI - Laboratorio**

**Docenti:** S. Cimato – M. Anisetti  
**Appello del 12/09/2025**

È permesso utilizzare manuali e documentazione dei tool, ma non comunicare con altri.

### **Esercizio su Wireshark**

Esaminando il traffico reso disponibile alla pagina:

```text
https://homes.di.unimi.it/cimato/SSR/esame/12set
```

Commentare e fare considerazioni su:

1. Quali sono i nodi IP coinvolti nella prima connessione TCP?
   a. Quali sono i MAC dei dispositivi coinvolti?
   b. Quali sono le caratteristiche hardware/software dei nodi server e client?

2. Quali sono i file richiesti?

3. Dare una descrizione in breve della sessione.

4. Qual è il tentativo di phishing che si sta mettendo in atto?

### **Esercizio su firewall**

- La sottorete della reception contiene i nodi PC1 e PC2.
- La sottorete dei servizi contiene un server di backup (HTTPS e TCP 445), un servizio di autenticazione degli AP (HTTPS) e un database (TCP 5432).
- La sottorete reception può accedere al server di backup e al servizio di autenticazione.
- La sottorete guests contiene un AP WiFi in modalità bridge.
- L'AP WiFi comunica con il servizio di autenticazione per registrare gli accessi.
- L'AP WiFi comunica con il servizio di autenticazione per registrare gli accessi.
- I dispositivi connessi all'AP WiFi possono comunicare solo con Internet.
- Il servizio di autenticazione comunica con il database.
- Il server di backup è esposto a Internet sulla porta 5000.
- Il restante traffico deve essere vietato.

Utilizzare esclusivamente la seguente tabella per descrivere le regole del firewall.

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|

---

## Parte 1 — Wireshark (riepilogo)

- **Prima connessione TCP:** `10.6.27.102:49157 → 23.63.254.163:80` (a `t ≈ 14,2 s`). Client `10.6.27.102`, MAC `00:08:02:1c:47:ae` (**HP**); MAC di destinazione = gateway `20:e5:2a:b6:93:f1` (**ASUS**), perché il server è remoto.
- **HW/SW del client:** PC **HP**, **Windows 7 (NT 6.1) a 64 bit con Internet Explorer 11** (dallo `User-Agent`). La prima connessione (`www.msftncsi.com`, `GET /ncsi.txt`) è il **controllo di connettività di Windows**, traffico legittimo.
- **File richiesti:** dal server **`smart-fax.com`** (`107.180.50.162`) vengono scaricati **`/Documents/Invoice&MSO-Request.doc`** (`application/msword`) e **`/knr.exe`** (`application/x-msdownload`, eseguibile Windows).
- **Descrizione della sessione:** catena di infezione — controllo NCSI di Windows → DNS di `smart-fax.com` → download del finto documento-fattura → download di `knr.exe` → connessioni ripetute verso **`dunlop.hopto.org` (`23.105.131.229`) sulla porta 2032**, canale di **comando e controllo (C2)** del malware.
- **Tentativo di phishing:** il **finto documento di fattura** `Invoice&MSO-Request.doc` è l'esca di ingegneria sociale che veicola l'eseguibile malevolo, il quale apre poi il C2 verso un dominio **DNS dinamico** (`hopto.org`). Da distinguere dal rumore legittimo (`msftncsi.com`, WPAD/LLMNR).

## Parte 2 — Firewall (riepilogo)

Topologia Reception (`10.0.0.0/24`) / Servizi (`10.0.4.0/24`: Auth `.11`, Backup `.15`, DB `.10`) / AP-Guest (AP `10.0.1.10`, Wi-Fi `192.168.4.0/24`) / Internet (`wan0 75.23.48.1`). Regole: Reception → Backup (443, 445) e → Auth (443); AP → Auth (443); guest → **solo Internet** (80/443 + DNS); Backup **esposto su 5000** via DNAT; Auth↔DB è **intra-subnet** (non filtrato dal firewall); default deny finale. La tabella completa a otto colonne, con le note su bridge/DNAT e sull'isolamento dei guest, è nella soluzione del [02/07/2025](../lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md).
