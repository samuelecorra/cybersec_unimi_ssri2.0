# **Lezione 3: Il valore probatorio del documento informatico**

### 1. Il valore formale del documento informatico

Il documento informatico può presentarsi in diverse configurazioni a seconda della presenza e del tipo di sottoscrizione elettronica. In particolare, i documenti informatici possono essere privi di firma elettronica oppure sottoscritti mediante firma elettronica semplice, avanzata, qualificata o digitale.

Il documento informatico semplice, cioè privo di qualsiasi firma elettronica, è comunque valido e rilevante agli effetti di legge ai sensi dell’articolo 20 del Codice dell’Amministrazione Digitale. In alcune circostanze, a discrezione del giudice, esso può anche assumere valore di atto scritto. Tuttavia, quando la legge richiede la forma scritta a pena di nullità per determinati atti, è necessario l’utilizzo della firma elettronica qualificata o della firma digitale, salvo specifiche eccezioni previste dalla normativa.

---

### 2. L’efficacia probatoria del documento informatico secondo il CAD

Il Codice dell’Amministrazione Digitale distingue diverse ipotesi di efficacia probatoria del documento informatico, in funzione della modalità di sottoscrizione.

Il documento informatico privo di firma elettronica ha un valore probatorio liberamente valutabile dal giudice, che tiene conto delle sue caratteristiche oggettive di qualità, sicurezza, integrità e immodificabilità. In questo caso l’efficacia è assimilabile a quella delle riproduzioni meccaniche previste dall’articolo 2712 del codice civile.

Il documento sottoscritto con firma elettronica semplice è anch’esso liberamente valutabile dal giudice ai sensi dell’articolo 116 del codice di procedura civile. Rientrano in questa categoria tutti i sistemi che consentono l’identificazione del soggetto attraverso conoscenze personali, caratteristiche fisiche o dispositivi in suo possesso, come PIN, password, dati biometrici o smart card.

Il documento sottoscritto con firma elettronica avanzata, qualificata o digitale possiede invece un valore probatorio più elevato. In presenza di certificato valido e non revocato, l’utilizzo del dispositivo di firma si presume riconducibile al titolare, salvo prova contraria. In tali casi il documento ha la stessa efficacia della scrittura privata e fa piena prova della provenienza delle dichiarazioni fino a querela di falso.

---

### 3. Sintesi delle diverse fattispecie di documento informatico

Le principali categorie di documento informatico possono essere riassunte in quattro fattispecie fondamentali.

Il documento informatico sottoscritto con firma digitale possiede valore di piena prova della provenienza delle dichiarazioni fino a querela di falso. Si tratta della tipologia originariamente disciplinata dal DPR n. 513 del 1997 e successivamente dal DPR n. 445 del 2000.

Il documento informatico sottoscritto con firma elettronica qualificata ha la stessa efficacia probatoria della firma digitale, purché siano presenti certificato qualificato e dispositivo di firma sicuro.

Il documento informatico sottoscritto con firma elettronica semplice è liberamente valutabile dal giudice. In questa categoria rientrano sia i documenti basati su sistemi di identificazione senza certificatore sia quelli che non soddisfano completamente i requisiti di legge per il valore di piena prova.

Infine, il documento informatico privo di firma possiede l’efficacia probatoria delle riproduzioni meccaniche.

---

### 4. Efficacia probatoria dei documenti sottoscritti

Dal punto di vista probatorio si può individuare una distinzione fondamentale.

I documenti sottoscritti con firma elettronica semplice sono liberamente valutabili dal giudice.

I documenti sottoscritti con firma elettronica avanzata, qualificata o digitale fanno invece piena prova fino a querela di falso della provenienza delle dichiarazioni in essi contenute, analogamente alla scrittura privata riconosciuta.

---

### 5. La marcatura temporale: definizione e funzione giuridica

La marcatura temporale è uno strumento informatico con rilevanza giuridica che consente di attribuire a un documento informatico una data e un’ora certe relativamente al momento della sua esistenza.

La validazione temporale è stata introdotta dal DPR n. 513 del 1997, che la definisce come il risultato di una procedura informatica mediante la quale vengono attribuite a uno o più documenti informatici una data e un orario opponibili ai terzi. Successivamente il Codice dell’Amministrazione Digitale e i provvedimenti tecnici attuativi hanno ampliato l’utilizzo di questo strumento.

La marcatura temporale permette quindi di certificare il momento preciso in cui un documento esisteva, garantendo opponibilità ai terzi e validità nel tempo. Ciò assume particolare importanza perché i certificati di firma digitale hanno una validità temporale limitata; senza marcatura temporale, alla scadenza del certificato potrebbe venire meno l’efficacia probatoria del documento. L’apposizione della marca temporale consente invece di mantenere la validità nel tempo.

---

### 6. La data del documento informatico e la validazione temporale

L’attribuzione di una data certa a un documento informatico avviene mediante la procedura di validazione temporale, spesso definita time stamping.

Essa consiste nell’associare al documento una marca temporale rilasciata dal certificatore, contenente la data e l’ora in cui la marca è stata apposta. Chiunque può verificare la data utilizzando la chiave pubblica del certificatore.

Il documento informatico può quindi contenere due firme: quella digitale dell’autore e quella del certificatore che ha apposto la marca temporale. La marca viene generata in modo tale che il soggetto che la appone non possa leggere il contenuto del documento, garantendo così la riservatezza.

La validità temporale del certificato è determinata dal certificatore e, una volta scaduto, viene meno l’efficacia probatoria privilegiata del documento e della marca temporale.

---

### 7. Procedure tecniche di marcatura temporale

Per eseguire la validazione temporale è necessario disporre della marca temporale, delle credenziali personali del richiedente e del software di firma e verifica fornito dall’ente certificatore.

Il contenuto della marcatura temporale è composto da tre elementi essenziali: l’impronta digitale del file, cioè una sintesi informatica che rappresenta univocamente il documento; la data e l’ora della validazione; la firma digitale dell’ente certificatore.

Dal punto di vista operativo, il richiedente seleziona il file e genera una richiesta di validazione tramite il software. Il sistema del certificatore crea automaticamente l’impronta del documento, appone la marca temporale, la firma digitalmente e restituisce il file marcato, generalmente con estensione .m7m. Successivamente è possibile verificare la validazione temporale con lo stesso software.

---

### 8. Requisiti giuridici della marcatura temporale

Affinché la marcatura temporale abbia valore legale devono essere rispettati alcuni requisiti fondamentali.

Il documento deve nascere come documento informatico e non come scansione di un originale cartaceo. Deve essere convertito in un formato non modificabile, tipicamente PDF/A. Deve essere firmato digitalmente da tutti i soggetti contraenti e, contestualmente all’ultima firma, deve essere apposta la marca temporale con data e ora certe opponibili ai terzi.

---

### 9. Sintesi finale

La lezione ha analizzato il valore probatorio del documento informatico, evidenziando come l’efficacia giuridica dipenda dal tipo di firma elettronica utilizzata.

È stato chiarito che il documento informatico semplice è comunque valido, ma liberamente valutabile dal giudice, mentre il documento sottoscritto con firma elettronica avanzata, qualificata o digitale ha valore di piena prova fino a querela di falso.

È stato inoltre approfondito il ruolo della marcatura temporale, strumento essenziale per attribuire data certa e garantire validità nel tempo ai documenti informatici firmati digitalmente.

---

### 10. Prossimi passi

Le lezioni successive proseguiranno con ulteriori approfondimenti sugli aspetti giuridici della sicurezza informatica e della gestione dei documenti digitali nei sistemi informativi.