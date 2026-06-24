# Formulario — Download pagina con N immagini (HTTP/1.0 vs HTTP/1.1)

**Basato su M3/UD4/L1 e L3 — Prof. Ernesto Damiani**
Per gli esercizi tipo: "Un browser deve scaricare una pagina con N immagini, X sullo stesso server e Y su un server diverso. Mostrate le richieste HTTP e calcolate le connessioni TCP / il costo in RTT."

---

## 1. Il modello di base

Una pagina HTML referenzia altre risorse (immagini, CSS, JS). Il browser deve fare **una richiesta HTTP separata per ogni risorsa**: le immagini non arrivano "gratis" con l'HTML.

**Totale oggetti = 1 HTML + N immagini**

Se ci sono **K server diversi**, le richieste si distribuiscono:
- le risorse di ogni server viaggiano sulla/e connessione/i verso quel server
- i server sono indipendenti: le connessioni verso server diversi sono in parallelo

---

## 2. HTTP/1.0 — connessione NON persistente

**Regola d'oro:** ogni oggetto = una nuova connessione TCP.

Costo per oggetto:
```
SYN → SYN+ACK → ACK   = 1 RTT  (three-way handshake)
GET → risposta          = 1 RTT
                        = 2 RTT per oggetto
```

**Formula totale (seriale, senza parallelismo browser):**
$$T_{1.0,\text{seriale}} = 2 \cdot RTT \times (\text{totale oggetti})$$

Con **N immagini su S1 e M immagini su S2:**
$$T_{1.0,\text{seriale}} = 2 \cdot RTT_{S1} \cdot (1 + N) + 2 \cdot RTT_{S2} \cdot M$$

**Con parallelismo browser** (tipicamente 4–6 conn TCP simultane per host):
Le richieste allo stesso server si sovrappongono → costo ≈ tanti "round" quante sono le ondate di connessioni parallele.

---

## 3. HTTP/1.1 — connessione persistente

**Regola d'oro:** una sola connessione TCP per server, riusata per tutti gli oggetti.

Costo per connessione (setup):
```
SYN → SYN+ACK → ACK   = 1 RTT  (una volta sola per server)
```

Costo per richiesta successiva (sulla stessa TCP):
```
GET → risposta          = 1 RTT  (no handshake ripetuto)
```

**Senza pipelining** (richieste seriali sulla stessa TCP):
$$T_{1.1,\text{no-pipeline}} = RTT_{\text{setup}} + n \cdot RTT_{\text{richiesta}}$$

**Con pipelining** (tutte le richieste mandate senza aspettare le risposte):
```
SYN/SYN+ACK/ACK = 1 RTT
GET html         = 1 RTT  → parsing
GET img1         ─┐
GET img2         ─┤ pipeline = 1 RTT per tutte
...              ─┘
```
$$T_{1.1,\text{pipeline}} \approx 1 \cdot RTT_{\text{setup}} + 1 \cdot RTT_{\text{html}} + 1 \cdot RTT_{\text{immagini}}$$

---

## 4. Schema risolutivo — step by step

**Passo 1:** Conta gli oggetti e i server.
```
Totale = 1 HTML + N immagini
Server S1: HTML + X immagini
Server S2: Y immagini
(X + Y = N)
```

**Passo 2:** Disegna/descrivi la topologia.
```
Browser
  ├── S1 ── index.html, img1, img2, ...  (X+1 risorse)
  └── S2 ── img3, img4, ...              (Y risorse)
```

**Passo 3 (HTTP/1.0):** Conta le connessioni TCP.
```
Connessioni totali = totale oggetti = 1 + N
  verso S1: 1 + X  (HTML + X immagini)
  verso S2: Y
```

**Passo 4 (HTTP/1.1):** Conta le connessioni TCP.
```
Connessioni totali = numero di server distinti = 2
  verso S1: 1 persistente (trasporta HTML + X immagini)
  verso S2: 1 persistente (trasporta Y immagini)
```

**Passo 5:** Calcola il costo in RTT.

---

## 5. Tabella di confronto sintetica

| Schema | Conn. TCP totali | RTT approssimativi |
|---|---:|---:|
| HTTP/1.0 seriale, 1+N oggetti | 1+N | ~2(1+N) RTT |
| HTTP/1.0 con parallelismo browser (4 conn/host) | 1+N (parallele) | ridotto proporzionalmente |
| HTTP/1.1 persistente, no pipelining | K (uno per server) | 1 RTT setup + 1 RTT per oggetto |
| **HTTP/1.1 persistente, con pipelining** | **K** | **~3 RTT** (setup + html + tutto il resto) |

---

## 6. Diagramma sequenza HTTP/1.0 — template

```
Browser                 S1 (html + img locali)     S2 (img remote)
──────                  ──────────────────          ──────────────

[TCP #1 → S1]
─── SYN ───────────────►
◄── SYN+ACK ───────────
─── ACK ───────────────►
─── GET /index.html HTTP/1.0 ─►
◄── HTTP/1.0 200 OK
    Content-Type: text/html
    Content-Length: N
    <html>…(immagini)…</html>
─── FIN ───────────────►

[parsing HTML: individua N immagini]

[TCP #2 → S1: img1.jpg]
─── SYN ─► … ─── GET /img1.jpg HTTP/1.0 ─► HTTP/1.0 200 OK + <jpg> ─── FIN

[TCP #3 → S1: img2.jpg]   ← stessa struttura

...

[TCP → S2: img_remota.png]
                                        ─── SYN ─► …
                                        ─── GET /img_r.png HTTP/1.0 ─►
                                        ◄── HTTP/1.0 200 OK + <png>
                                        ─── FIN
```

---

## 7. Diagramma sequenza HTTP/1.1 — template

### Variante senza pipelining

```
Browser                 S1                          S2
──────                  ──                          ──

[TCP persistente → S1]
─── SYN/SYN+ACK/ACK ──►
─── GET /index.html HTTP/1.1
    Host: s1.com
    Connection: keep-alive ─►
◄── HTTP/1.1 200 OK + <html>

   [parsing: trova img1, img2 su S1 e img3 su S2]

─── GET /img1.jpg HTTP/1.1 ─►  ← stessa TCP
◄── HTTP/1.1 200 OK + <jpg>

─── GET /img2.jpg HTTP/1.1
    Connection: close ─────►    ← ultima richiesta a S1
◄── HTTP/1.1 200 OK + <jpg>  FIN

[TCP persistente → S2, in parallelo con le img S1]
                                        ─── SYN/SYN+ACK/ACK ──────────►
                                        ─── GET /img3.png HTTP/1.1
                                            Connection: close ────────►
                                        ◄── HTTP/1.1 200 OK + <png>  FIN
```

### Variante con pipelining

```
─── SYN/SYN+ACK/ACK ──►
─── GET /index.html ─►
◄── 200 OK + <html>

[parsing immediato → img1, img2 su S1]

─── GET /img1.jpg ─►  ┐
─── GET /img2.jpg ─►  ┘ pipelining: inviate senza aspettare risposta
◄── 200 OK + <img1>
◄── 200 OK + <img2>  FIN
```

---

## 8. Regole per non sbagliare in esame

1. **Conta sempre prima:** 1 HTML + N immagini = totale oggetti.

2. **HTTP/1.0:** tante connessioni quanti oggetti. Disegna un blocco SYN/GET/risposta/FIN per ogni oggetto (anche solo schematicamente per gli ultimi).

3. **HTTP/1.1:** tante connessioni quanti server. La prima richiesta (HTML) apre la TCP; le successive la riusano. L'ultima richiesta a un server porta `Connection: close`.

4. **Oggetti su server diversi in HTTP/1.1:** le connessioni verso server diversi sono **in parallelo** — aprile contemporaneamente dopo il parsing dell'HTML.

5. **Il costo in RTT ottimale HTTP/1.1** con pipelining e due server in parallelo:
   - `1 RTT` = setup verso S1 e S2 in parallelo
   - `1 RTT` = GET html + risposta html
   - `1 RTT` = GET tutte le immagini (pipeline) + risposte
   - **Totale ≈ 3 RTT** (indipendente da N)

6. **Nota sulla "lentezza" HTTP/1.0:** ogni connessione TCP riparte da zero con lo Slow Start — il throughput è sempre subottimale. HTTP/1.1 su una singola connessione lunga arriva alla finestra di throughput ottimale.
