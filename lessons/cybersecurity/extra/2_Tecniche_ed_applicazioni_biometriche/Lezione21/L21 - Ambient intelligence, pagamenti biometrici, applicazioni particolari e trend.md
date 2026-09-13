# **Lezione 21: Ambient intelligence, pagamenti biometrici, applicazioni particolari e trend**

---

### **0. Panoramica della lezione conclusiva**

_(slide pp. 1–3)_

L'ultima lezione del corso (p. 2) tratta **pagamenti bancari biometrici**, **ambient intelligence** e **applicazioni particolari**.

**Contenuti** (p. 3):

1. applicazioni biometriche per l'**ambient intelligence**: caratteristiche necessarie, hardware, software e applicative;
2. tecnologie per i **pagamenti biometrici**: **impronta su carta di credito**, **iride**, **palmo IR**;
3. **conteggio accurato delle persone**;
4. biometria **su droni**, **su persone** (indossabile: occhiali, bodycam) e **su robot**;
5. biometria degli **stati mentali**;
6. **raccomandazioni finali**.

**Materiali della cartella** (dal README del docente):

| Materiale | Contenuto |
|---|---|
| **slide** della lezione | pubblicate come sempre |
| **dispensa** «Advanced Biometric Technologies: Emerging Scenarios and Research Trends» | A. Genovese, E. Muñoz, V. Piuri, F. Scotti, Springer, 2018 (§6) |
| **griglia di simulazione** | da stampare o annotare per la simulazione d'esame (§15) |
| **testo della simulazione d'esame** | **non pubblicato** |

Per dubbi il docente resta disponibile via posta elettronica, inviando lo **screenshot** della parte interessata con la descrizione del chiarimento necessario.

---

## **Parte 1 — Ambient intelligence e biometria**

### **1. Definizione ed evoluzione**

_(slide pp. 4–11)_

> 📌 **Ambient Intelligence** (AmI, Augusto 2007, p. 5). *«A digital environment that proactively, but sensibly, supports people in their daily lives»*: un ambiente digitale che **supporta proattivamente, ma in modo sensato**, le persone nella loro vita quotidiana.

**Aggiornamento** (p. 6):

| **AmI nel 2007** | **AmI oggi** |
|---|---|
| sensori + rete + server centrale | **Edge AI** + **percezione multimodale** + **personalizzazione** + **privacy by design** |

**Percezione multimodale** (p. 7). L'AmI moderna **non è più mono-biometrica**:

$$\text{Identity} = f(\text{Face},\ \text{Voice},\ \text{Gait},\ \text{Context},\ \text{Behavior})$$

Esempi:

- **eyepos**: volto + iride (§7.3);
- **smart car**: volto + postura + attenzione;
- **droni**: detection + tracking + re-identificazione (§10).

> 💡 **Il contesto come tratto.** Nella formula compaiono anche **contesto** e **comportamento**. Sapere che una persona entra in cucina alle 7 di mattina, come ogni giorno, aumenta la confidenza di un riconoscimento facciale incerto: è la stessa logica della fusione con le soft biometrics e con Bayes (Lezione 15, §13), estesa alle abitudini.

**Smart environment** (p. 8): l'**infrastruttura fisica** (sensori, attuatori e reti) che supporta il sistema.

**Termini del settore** (pp. 9–10):

| Termine | Significato |
|---|---|
| **ubiquitous** | accessibile **ovunque** |
| **mobile** | integra i **dispositivi mobili** |
| **context-aware** | tiene conto dell'**ambiente di esecuzione** |
| **pervasive** | unisce ubiquità, mobilità e consapevolezza del contesto |
| **ambient** | integra un'assistenza **proattiva** e orientata all'utente negli **oggetti** e negli **ambienti** della vita quotidiana |

**Proprietà dell'AmI** (p. 11): **scalabilità**, **invisibilità**, **consapevolezza del contesto**, **intelligenza**, **proattività** e capacità di **anticipare**.

<!-- SOURCE_VISUAL id="TAB21-V01" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="4" type="pagina di sezione" description="Parte 1: ambient intelligence e biometria" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Caratteristiche di un ambiente intelligente**

_(slide pp. 12–20)_

**Da casa e ufficio alla smart city** (p. 12): oggi l'AmI riguarda soprattutto **casa e ufficio**, domani la **smart city**. La p. 13 confronta le caratteristiche dei sistemi biometrici negli scenari eterogenei.

**Come deve essere l'ambiente** (p. 14): **non intrusivo**, **interconnesso**, **adattabile**, **dinamico**, **integrato** (*embedded*), **intelligente**. L'AmI è la visione di un futuro in cui gli ambienti **supportano le persone** che li abitano.

**Interazione uomo-macchina** (p. 15):

- i tradizionali strumenti di input e output **scompaiono**: niente mouse, schermi o tastiere;
- processori e sensori sono **integrati negli oggetti** di uso quotidiano;
- si comunica **direttamente in modo naturale**.

**Caratteristiche ambientali** (p. 16). L'ambiente è:

- **sensibile** alle esigenze dei suoi abitanti;
- capace di **anticiparne** bisogni e comportamenti;
- **consapevole** dei loro requisiti personali e preferenze;
- **user-friendly** nell'interazione;
- capace di **esprimere, riconoscere e rispondere alle emozioni** (Lezione 16).

**Caratteristiche chiave** (p. 17, Gaggioli 2005):

| Caratteristica | Significato |
|---|---|
| **intelligenza** | sensibile al contesto, **adattivo**, **apprende** dal comportamento degli utenti, riconosce ed esprime emozioni |
| **embedding** | dispositivi **piccoli**, possibilmente miniaturizzati, **integrati sullo sfondo** delle attività e degli ambienti |

**Hardware** (p. 18): miniaturizzato e **a basso costo**, con reti complesse di dispositivi eterogenei, come i sensori di impronte **sotto lo strato protettivo** visti nella Lezione 20.

> 📌 **AmI e biometria** (p. 19). Uso **efficace, efficiente e rispettoso della privacy** delle caratteristiche biometriche per supportare l'**adattività** e soluzioni intelligenti per l'AmI e lo **smart living**.

**Adattività tramite riconoscimento** (p. 20):

| Livello | A che cosa serve |
|---|---|
| **identificazione della persona** | applicare bisogni, preferenze e desideri **noti**; registrarne di **nuovi** per usi futuri |
| **classificazione della persona** | applicare le caratteristiche note di una **classe** di persone a servizi e operazioni, e raffinarle |
| **comprensione delle azioni** | applicare servizi e operazioni in risposta a un'**azione umana** |

> 💡 **Tre livelli, tre rischi diversi.** L'identificazione richiede un database di persone note; la classificazione (bambino o adulto, per esempio) può funzionare **senza sapere chi sei**, con meno rischi; la comprensione delle azioni sfiora le inferenze comportamentali della Lezione 13, §16. In una casa intelligente conviene usare il livello **più basso** che basta al servizio.

<!-- SOURCE_VISUAL id="TAB21-V02" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="12" type="illustrazioni" description="AmI oggi in casa e ufficio, domani nella smart city" reason="Pagina con sole illustrazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V03" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="13" type="tabella grafica" description="Tecnologie biometriche per l'AmI: comparazione degli scenari" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Scenari applicativi dell'AmI**

_(slide pp. 21–31)_

Le slide pp. 22–31 provengono da una presentazione del **prof. Vincenzo Piuri** all'Óbuda University (2014) e illustrano gli scenari con immagini:

| Scenario | Ruolo della biometria (sintesi degli esempi) |
|---|---|
| **protezione dei bambini** (p. 21) | riconoscere un bambino e impedirgli l'accesso a zone o apparecchi pericolosi |
| **cucine e ristoranti** (p. 22) | adattare servizi e preferenze alla persona riconosciuta |
| **igiene personale** (p. 23) | ambienti che si configurano sull'utente |
| **smart entertainment** (p. 24) | contenuti adattati a chi usa il sistema |
| **smart car** (p. 25) | **limite di potenza**, **configurazione**, **contatti** personalizzati per il conducente riconosciuto |
| **sistemi di trasporto intelligenti** (p. 26) | riconoscimento e servizi per i viaggiatori |
| **gestione intelligente del traffico** (p. 27) | uso di **soft biometrics** |
| **smart shop** (p. 28) | servizi personalizzati ai clienti |
| **chioschi informativi e realtà aumentata** (p. 29) | informazioni adattate all'utente |
| **biglietterie e ingressi** (p. 30) | accesso con riconoscimento |
| **sanità e ospedali** (p. 31) | identificazione dei pazienti e personalizzazione delle cure |

> ⚠️ **Descrizioni sintetiche.** Queste pagine sono quasi solo immagini: la colonna di destra riassume il senso degli esempi a partire dai titoli e dalle poche etichette leggibili (come «limite di potenza», «configurazione», «contatti» nella smart car e «soft biometrics» nel traffico). I dettagli vanno visti sulle slide.

<!-- SOURCE_VISUAL id="TAB21-V04" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="21" type="illustrazione" description="AmI per la protezione dei bambini" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V05" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="22" type="illustrazione" description="AmI in cucine e ristoranti (presentazione V. Piuri, Óbuda University 2014)" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V06" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="23" type="illustrazione" description="AmI per l'igiene personale" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V07" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="24" type="illustrazione" description="Sistemi per smart entertainment" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V08" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="25" type="illustrazione" description="Smart car con limite di potenza, configurazione e contatti personalizzati" reason="Funzioni indicate sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V09" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="26" type="illustrazione" description="Sistemi di trasporto intelligenti" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V10" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="27" type="illustrazione" description="Gestione intelligente del traffico con soft biometrics" reason="Etichette parziali sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V11" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="28" type="illustrazione" description="Smart shops" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V12" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="29" type="illustrazione" description="Chioschi informativi e realtà aumentata" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V13" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="30" type="illustrazione" description="Biglietterie e ingressi" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V14" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="31" type="illustrazione" description="Health care e ospedali" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Quali tratti per l'AmI**

_(slide pp. 32–39)_

**Non tutti i tratti sono adatti** (p. 32): ogni tratto ha proprietà e usabilità diverse.

La slide p. 33 raggruppa i tratti in **fisiologici**, **comportamentali**, **soft biometrics** e il **battito cardiaco**; la p. 34 evidenzia quelli **adatti all'AmI**, indicando il cuore come **molto rilevante**.

> 💡 **Perché il cuore è così adatto (collegamento con la Lezione 16).** L'AmI richiede riconoscimento **invisibile** e **continuo**, senza chiedere nulla all'utente. Un braccialetto o un sensore nel volante leggono ECG e PPG mentre la persona vive normalmente, e forniscono anche informazioni sullo **stato** (stress, sonno) utili all'adattività. Il prezzo è la sensibilità di quei dati (Lezione 13, §18).

**Riconoscimento facciale robusto alle rotazioni** (p. 35):

1. le **reti generative avversarie** (GAN) arricchiscono i dataset di addestramento con pose diverse;
2. si usano metodi di riconoscimento **invarianti alla posa** (*pose-invariant*).

**Stima dell'età** (pp. 36–37):

| Approccio | Uscita | Complessità |
|---|---|---|
| **classificazione** | **3 classi** (per esempio bambino, adulto, anziano) | **minore** |
| **quantizzazione** | **$N$ classi** (fasce d'età) | intermedia |
| **regressione** | **stima** con valore intero o reale | **maggiore** |

Con un **tuning** mirato si può irrobustire la rete per i contesti di AmI, dove le pose sono libere.

> 💡 **Metriche diverse (nota didattica).** La classificazione si valuta con l'**accuratezza** sulle classi; la regressione con l'errore assoluto medio
> $$MAE = \frac{1}{n} \sum_{i=1}^{n} \lvert \hat{y}_i - y_i \rvert$$
> dove $\hat{y}_i$ è l'età stimata e $y_i$ quella vera. Per un servizio AmI come «blocca i contenuti per adulti» basta spesso la classificazione a poche classi: più semplice, più robusta e meno invasiva di una stima precisa.

**Genere ed etnia** (p. 38; K. Zhang et al., «Age Group and Gender Estimation in the Wild With Deep RoR Architecture», *IEEE Access*, 2017). La stima dell'**etnia** con l'IA può avere **terribili conseguenze** sulla privacy.

> ⚠️ **Oggi è anche vietata.** Nell'Unione Europea i sistemi di categorizzazione biometrica che inferiscono l'**origine razziale o etnica** rientrano nei divieti dell'AI Act (Lezione 13, §5.2), e il caso degli uiguri mostra perché (Lezione 13, §16.2).

**Contributi possibili delle lezioni del corso** (p. 39). All'AmI si possono aggiungere molti miglioramenti a partire dalle tecnologie viste:

- **template update** (Lezione 8);
- **multimodale continuo** (Lezione 16);
- terminale **mobile** e **desktop** (Lezione 16);
- sistemi biometrici per la **voce** (Lezione 20);
- **autenticazione silente** (Lezione 16);
- **riconoscimento delle emozioni** (Lezione 16);
- **sorveglianza biometrica** (Lezione 18).

<!-- SOURCE_VISUAL id="TAB21-V15" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="34" type="schema" description="Biometrie adatte all'AmI: tratti fisiologici, comportamentali, soft biometrics e cuore molto rilevante" reason="Selezione dei tratti indicata sullo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V16" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="36" type="schema" description="Stima dell'età come classificazione a 3 classi, quantizzazione a N classi o regressione" reason="Confronto degli approcci nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V17" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="37" type="immagini" description="Stima dell'età robusta alle rotazioni con tuning per l'AmI" reason="Esempi visivi senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte 2 — Trend di mercato e di ricerca**

### **5. I trend secondo i produttori**

_(slide pp. 40–44)_

La **dispensa «Trends»** riassume e descrive le slide di questa sezione (p. 41).

**Trend secondo i produttori** (p. 42):

- **POS** biometrici;
- **transazioni mobili**: nel 2022 oltre **mille miliardi di dollari** di transazioni finanziarie su mobile;
- autenticazione **multi-stage** e **multi-fattore**.

**La dispensa** (p. 43), intitolata «Advanced Biometric Technologies: Emerging Scenarios and Research Trends», è una lettura per individuare i **principali trend di ricerca** e capire **quanto sia ampio** lo spettro di nuove applicazioni già disponibili o realizzabili a breve. La p. 44 ne riassume i trend con uno schema.

<!-- SOURCE_VISUAL id="TAB21-V18" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="44" type="schema" description="Trend dei sistemi biometrici" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. La dispensa: tecnologie, scenari e trend di ricerca**

#### **6.1 Accuratezza e accettazione dei tratti**

_(dispensa pp. 1–3)_

**Contesto** (p. 1). I sistemi biometrici, tradizionalmente usati in applicazioni ad alta sicurezza, grazie ad algoritmi avanzati, acquisizione ad alta risoluzione e architetture parallele funzionano ormai in **condizioni meno vincolate**, con **campioni non ideali** e **in tempo reale**. Il mercato era previsto a **21 miliardi di dollari** entro la fine del 2020, trainato da identificazione nazionale, controllo delle frontiere, controllo accessi e telefoni.

**Scenari emergenti** (p. 2): **infrastrutture pubbliche** (frontiere, sorveglianza, servizi umanitari, e-health, trasporto pubblico), **private** (e-banking, e-commerce, trasporto privato), **applicazioni centrate sull'utente** (domotica, intrattenimento, social media) e **dispositivi personali**.

**Accuratezza e accettazione sono legate** (p. 2): i sistemi più **accurati** hanno di solito acquisizioni più **intrusive**, quindi **minore accettazione**. I più accurati si usano dove serve **alta sicurezza** (installazioni militari, frontiere); quelli più accettati in applicazioni a **bassa sicurezza** (trasporto pubblico, dispositivi personali).

**Tabella 1 della dispensa** (p. 3):

| Tratto | Accuratezza | Accettazione |
|---|---|---|
| **volto** | media (**96% TAR** a **0,1% FAR**) | **alta** |
| **impronta** | alta (**99,4% TAR** a **0,01% FAR**) | media |
| **iride** | molto alta (**99,1% TAR** a **0,001% FAR**) | **bassa** |
| **voce** | media (**93% TAR** a **0,1% FAR**) | **alta** |

Il **TAR** (*True Acceptance Rate*) è la probabilità che il sistema conceda correttamente l'accesso a una persona autorizzata; il **FAR** la probabilità che lo conceda a una non autorizzata. L'accettazione è espressa in modo **qualitativo**, perché è soggettiva.

> 💡 **Leggere la tabella (nota didattica).** Poiché $TAR = 1 - FRR$, il volto al 96% di TAR ha un FRR del 4% con un FAR di $10^{-3}$, mentre l'iride al 99,1% ha un FRR dello 0,9% con un FAR **cento volte** più basso, $10^{-5}$. I punti di lavoro non sono allo stesso FAR: per confrontare due tratti bisogna guardare le curve DET complete (Lezione 2), non un solo numero.

**Soft biometrics** (p. 3): caratteristiche con **distintività o permanenza limitate** (età, genere, etnia, altezza), che **completano** l'informazione biometrica o **classificano** le persone in gruppi. Attirano attenzione crescente anche **palmo**, **ECG**, **camminata** e soft biometrics, per il buon compromesso tra accuratezza e accettazione.

#### **6.2 Progressi per tratto**

_(dispensa pp. 4–12)_

| Tratto | Metodi tradizionali | Limiti | Trend di ricerca |
|---|---|---|---|
| **Volto** (pp. 4–5) | feature **globali** (tutta l'immagine, più accurate ma serve alta qualità) o **locali** (landmark, più robuste a luce, posa, espressione) | invecchiamento, luce non controllata, pose laterali, espressioni, occlusioni, sfocatura, bassa risoluzione | modelli **3D**, metodi **ibridi** globali-locali, compensazione di rotazioni, espressioni e invecchiamento, **deep learning** e **CNN** |
| **Impronta** (pp. 5–6) | immagine in scala di grigi delle creste; estrazione delle **minuzie** e confronto con **graph matching** non esatto | il **contatto** è percepito come scomodo o invasivo, introduce **distorsioni non lineari**, soffre le dita sporche; attacchi con **dita finte**; l'**identificazione** su milioni di identità può richiedere **ore o giorni** | acquisizione **touchless** 2D e 3D; robustezza a campioni di bassa qualità; rilevamento dei falsi; riduzione del tempo di identificazione |
| **Iride** (pp. 6–7) | camera **NIR** a circa **30 cm**; segmentazione con **due circonferenze concentriche**; template binari e **distanza di Hamming** | serve alta **cooperazione** (sguardo, palpebre, ciglia, occhiali); la luce ambiente crea riflessi e cambia la pupilla; bassa accettazione e timore infondato per l'IR | distanze **oltre 30 cm**, **luce naturale**, utenti **non cooperativi**; segmentazione **non circolare** di immagini rumorose; compensazione di dilatazione e sguardo; **deep learning**. Utile dove il volto è coperto per tradizione o le impronte sono consumate |
| **Palmo** (pp. 7–8) | dal polso alla base delle dita, stessa pelle dei polpastrelli; linee principali, descrittori di texture, metodi di **codifica** binaria; con o senza contatto | servono acquisizioni di **alta qualità**, quindi utenti addestrati o **guide fisiche** | modelli **3D** per posizioni libere senza contatto; texture, codifica e **CNN**. Vantaggi: dispositivi **economici**, alta accuratezza, buona accettazione |
| **ECG** (pp. 8–9) | feature **fiduciali** (punti dell'onda del battito) o **non fiduciali** (dominio della frequenza o wavelet) | la **stabilità** nel tempo e in condizioni emotive e fisiologiche diverse **non è ancora dimostrata**; interoperabilità tra dispositivi poco studiata | stabilità e interoperabilità; segnali da **wearable**; **autenticazione continua**. Vantaggi: difficile da contraffare, acquisibile a lungo senza azioni dell'utente |
| **Voce** (p. 9) | **speaker recognition** (chi parla), distinta dalla speech recognition (che cosa dice); metodi dipendenti o indipendenti dal testo; coefficienti **MFCC**, che imitano la percezione umana delle frequenze | forte calo di accuratezza con segnali **rumorosi** o di bassa qualità | **deep learning** che apprende la rappresentazione direttamente dal segnale grezzo |
| **Camminata** (p. 10) | **silhouette** per frame, feature di **movimento**, machine learning; funziona a distanza e con camere di bassa qualità | poco affidabile a lunga distanza, con punti di vista diversi, sfondi non controllati, sfocatura, occlusioni | modelli **3D** e **CNN**; autenticazione **continua** e non intrusiva |
| **Età e genere** (pp. 10–11) | feature di **Gabor**, **LBP** o ad hoc più machine learning | calo con rotazioni, espressioni, luce scarsa, occlusioni | **deep learning** e **CNN** su immagini non controllate |

**Multibiometria** (pp. 11–12). Un sistema biometrico ha **quattro moduli** (sensore, estrazione delle feature, matching, decisione), e la fusione può avvenire a ciascun livello (Lezione 15, §4):

| Livello | Limite (dispensa) |
|---|---|
| **sensore** | servono dispositivi **compatibili** e condizioni simili |
| **feature** | non sempre si possono concatenare feature con rappresentazioni diverse |
| **punteggio** | dipende dalla **distribuzione dei punteggi** nello scenario, non sempre disponibile; è il livello **più usato** |
| **decisione** | nei sistemi commerciali spesso è l'**unico** livello accessibile |

Trend: fusione **a livello di feature** di sorgenti eterogenee, fusione **adattativa** dei punteggi con machine learning, strategie multibiometriche per l'**AmI**.

<!-- SOURCE_VISUAL id="TAB21-V19" source="Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf" page="9" type="grafico" description="Fig. 5: onda del battito cardiaco con i punti fiduciali usati per il riconoscimento ECG" reason="Punti fiduciali indicati sul grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V20" source="Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf" page="13" type="schema a blocchi" description="Fig. 8: fusione a livello di punteggio di volto e impronta" reason="Schema del flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Scenari emergenti**

_(dispensa pp. 13–19)_

| Area | Scenario | Contenuto (dispensa) |
|---|---|---|
| **Infrastrutture pubbliche** | **frontiere** (ABC) | e-Gate in **48 paesi**; documenti elettronici con volto e, opzionalmente, impronte e iride; servono usabilità, accuratezza, velocità, resistenza allo spoofing; trend su **valutazione avanzata della qualità** e fusione multibiometrica rispettosa della privacy (Lezione 14) |
| | **sorveglianza** | riconoscimento a distanza, in movimento, di soggetti non cooperativi; volto, **camminata**, soft biometrics; camere **PTZ** per alta risoluzione a distanza (Lezione 18) |
| | **servizi umanitari** | identificare chi riceve aiuti e ridurre le frodi; l'**UNHCR** considera la biometria strategica; rischi di spoofing per ottenere aiuti altrui e di impossibilità di enrollment (impronte consumate); **iride** per i rifugiati afghani |
| | **e-health** | fiducia nello scambio di dati sanitari; **impronta** per l'accesso alle risorse mediche e la cifratura dei dati |
| | **trasporto pubblico** | bassa sicurezza ma alto **throughput**: riconoscimento facciale non controllato, impronta **touchless**, **conteggio dei pedoni** |
| **Infrastrutture private** | **e-commerce e e-banking** | autenticazione a distanza; **palmo** e **impronta** per rafforzare le **password monouso** |
| | **trasporto privato** | lettori di impronte contro i furti; **ECG** per rilevare **distrazione, sonnolenza, ebbrezza** e autenticare il conducente in continuo (CardioWheel, Lezione 16); **car sharing** |
| **Applicazioni centrate sull'utente** | **domotica** | algoritmi a **bassa complessità** per dispositivi limitati; impronta sullo smartphone per gli elettrodomestici; **voce** riconosciuta in qualunque punto della casa |
| | **intrattenimento** | i giochi come banco di prova; riconoscimento degli utenti, **stima dell'età** per i contenuti per adulti, sensori di **profondità** (per esempio Kinect: altezza 188 cm, maschio, adulto) |
| | **social media** | contro l'**impersonificazione**, autenticazione iniziale e continua; profili per il **marketing mirato**; **social behavioral biometrics** dalle attività sul social network |
| **Dispositivi personali** | smartphone e affini | risorse di calcolo e sensori **limitati**, uso non controllato, spoofing; impronta **capacitiva** e **touchless** con la camera; volto **3D** con sensori dedicati; iride nel **visibile**; voce; **palmo** anche a bassa risoluzione; **dinamica del touchscreen**; fusione **multimodale** dei sensori già presenti |

> ⚠️ **Il marketing mirato non è neutro.** La dispensa cita la costruzione di **profili per il marketing** dai dati biometrici sui social media come uno scenario emergente. Alla luce del GDPR e delle linee guida viste (Lezione 13, §4) è esattamente un caso di **function creep** (Lezione 19, §5.1): dati raccolti per autenticare usati per profilare.

<!-- SOURCE_VISUAL id="TAB21-V21" source="Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf" page="16" type="schema" description="Fig. 10: sistema biometrico e di monitoraggio della salute per auto basato sull'ECG" reason="Architettura mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4 Sfide e trend di ricerca**

_(dispensa pp. 20–25)_

**Otto aspetti** da migliorare (p. 20, Fig. 13):

| Aspetto | Significato | Tecnologie emergenti (Fig. 13) |
|---|---|---|
| **usabilità** | facilità d'uso e di apprendimento; tempo di acquisizione e campioni di qualità insufficiente | ergonomia, valutazione della qualità, dispositivi migliori, riconoscimento dei problemi di acquisizione |
| **accettazione** | percezione degli utenti: invasività, usabilità, rischi percepiti | touchless, a distanza, in movimento |
| **privacy** | protezione dei dati da furto e abuso | crittografia, protezione dei template |
| **sicurezza** | robustezza a tratti falsi e software malevolo | multibiometria, anti-spoofing |
| **accuratezza** | capacità di distinguere gli utenti | deep learning, multibiometria, qualità, volto 3D, normalizzazione **cohort**, miglioramento dei campioni non ideali, invarianza a posa e illuminazione |
| **tempo di esecuzione** | enrollment e matching; influisce sull'usabilità | parallelismo, calcolo su **GPU** |
| **interoperabilità** | compatibilità tra sistemi, dispositivi e formati | schemi crittografici comuni, **standard** di interscambio |
| **scalabilità** | effetto della crescita di utenti e richieste | parallelismo, modularità, calcolo **distribuito** |

**Usabilità e accettazione** (pp. 20–22):

- dispositivi **meno vincolati** e **meno intrusivi**: impronta e palmo **touchless**, volto non controllato, iride a distanza, voce in AmI. Le tecnologie touchless sono **più accettate** e più **igieniche**;
- algoritmi robusti ai campioni **non ideali**, anche con deep learning per volto e camminata;
- **valutazione avanzata della qualità** con **feedback** precisi. Negli ABC (Fig. 14) il sistema riconosce automaticamente i **problemi di acquisizione**: **sporco**, **grasso** e **dita gonfie** per l'impronta; **acquisizione non frontale**, **illuminazione**, **capelli** e **occhiali** per il volto. Poi sceglie l'**azione correttiva**: compensare la non idealità oppure un **signalling intelligente** (Lezione 14, §11.3).

**Privacy e sicurezza** (p. 23):

- i dati biometrici non si possono cambiare: se rubati, permettono l'**impersonificazione per un tempo indefinito**;
- le norme internazionali limitano la **conservazione** dei dati al periodo d'uso effettivo, con log di qualità **anonimizzati**;
- si memorizzano **template** invece dei campioni, con **crittografia** specifica per la biometria (Lezione 13), e si usano normalizzazione e fusione dei punteggi **rispettose della privacy**;
- per la sicurezza servono tecniche di **liveness** capaci di rilevare più tipi di falsi (foto stampate, dita in silicone, iridi sintetiche), anche nei sistemi multibiometrici.

**Accuratezza e tempo di esecuzione** (p. 23):

- il **deep learning** ha migliorato volto, impronta, iride, palmo, ECG, voce, camminata, età e genere;
- **svantaggi**: servono **molti dati** di addestramento, i template possono essere **grandi** (problemi di storage con molti utenti), e i tempi di calcolo possono essere eccessivi dal vivo;
- implementazioni **parallele su GPU** raggiungono guadagni **fino a 14 volte** rispetto alla CPU sequenziale.

**Interoperabilità** (p. 24). Anche con gli standard, sensori diversi creano problemi. Si studiano valutazioni **cross-database** (impronta, iride, firma online) e modelli addestrati e testati su **modalità diverse** con perdite limitate.

**Scalabilità** (p. 24). È cruciale in **identificazione**, con database nazionali da milioni di record o frontiere con migliaia di passeggeri al giorno. Si usano calcolo distribuito, parallelismo, modularità e riconoscimento biometrico **as a service** nel **cloud**.

**Conclusioni della dispensa** (pp. 24–25): l'obiettivo principale è la **robustezza** a campioni acquisiti in condizioni **non controllate**, con deep learning e CNN particolarmente promettenti; restano da progettare sistemi usabili in condizioni **completamente libere**, per servizi personalizzati centrati sulla persona.

<!-- SOURCE_VISUAL id="TAB21-V22" source="Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf" page="21" type="diagramma" description="Fig. 13: aspetti dei sistemi biometrici e tecnologie emergenti per ciascuno" reason="Associazioni visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V23" source="Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf" page="22" type="schema" description="Fig. 14: rilevamento e correzione automatici dei problemi di acquisizione di impronta e volto negli ABC" reason="Flusso delle azioni correttive nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.5 Bibliografia della dispensa**

_(dispensa pp. 26–28)_

La dispensa riporta **53 riferimenti**. Tra i più utili: A. K. Jain, K. Nandakumar, A. Ross, «50 years of biometric research» (*Pattern Recognition Letters*, 2016); A. K. Jain, P. Flynn, A. Ross, *Handbook of Biometrics* (2008); B. Bhanu, A. Kumar, *Deep Learning for Biometrics* (2017); R. Donida Labati et al., «Biometric recognition in Automated Border Control: a survey» (*ACM Computing Surveys*, 2016, dispensa della Lezione 14); R. Donida Labati, V. Piuri, F. Scotti, *Touchless Fingerprint Biometrics* (2015); A. Genovese, V. Piuri, F. Scotti, *Touchless Palmprint Recognition Systems* (2014); CardioWheel (2015); J. Neves et al., survey sulla biometria in sorveglianza (2016).

---

### **7. Pagamenti biometrici**

_(slide pp. 45–62)_

#### **7.1 Il settore bancario e la biometria**

_(slide pp. 45–46)_

Il mondo bancario si aggiorna contro le **frodi** integrando la biometria nei pagamenti (p. 45), per più **sicurezza** e un'esperienza **senza interruzioni** (p. 46). La crescente accettazione (il **74%** dei consumatori) conferma che la biometria è non solo innovativa ma **richiesta**. Le novità principali:

- **carte con impronta digitale**;
- riconoscimento dell'**iride** («eyepos»);
- riconoscimento delle **vene del palmo**.

#### **7.2 L'impronta sulla carta: Match on Card**

_(slide pp. 47–57)_

**Dalla memorizzazione al Match on Card** (p. 47):

- **obiettivo 1** (p. 48): rendere la carta di credito biometrica **come il cellulare** nell'uso comune;
- **obiettivo 2** (p. 49): **limitare i problemi di privacy percepiti** dal cliente.

Le pp. 50–52 spiegano **perché il Match on Card** (MoC) e mostrano la struttura di una carta con match, confrontando soluzioni **passive**, **attive** e **migliori**.

> 📌 **Match on Card** (richiamo della Lezione 1). Template, estrazione e confronto avvengono **dentro la carta**: il dato biometrico non esce mai verso il POS, la banca o il cloud. Il terminale riceve solo l'esito. È l'architettura che massimizza la **memorizzazione personale** del §3.1 della Lezione 13, la risposta a rischio minore.

**Sensori per carte** (pp. 53–54). Servono sensori diversi da quelli tradizionali per **tipo** e **risoluzione**. Esempio **NEXT Biometrics NB-0610-S2** (Next Biometrics Group ASA, Norvegia):

| Caratteristica | Valore |
|---|---|
| spessore dei materiali | **pochi micrometri** |
| substrato | **rigido** (vetro) o **flessibile** (PET), compatibile con le smart card |
| interfaccia | **SPI** a 2–3 MHz |
| livelli di grigio | **256** |
| area attiva | **11,9 × 16,9 mm²** |
| risoluzione | **385 ppi** |
| software | algoritmi di partner ottimizzati per il sensore, supporto all'integrazione |

> 💡 **Che cosa significano 385 ppi (nota didattica).** Il passo dei pixel è $25{,}4\ \text{mm} / 385 \approx 0{,}066$ mm, quindi l'area attiva corrisponde a circa $180 \times 256$ pixel. È meno dei **500 ppi** tipici dei sistemi per le forze dell'ordine (Lezione 14, §14.3): va bene per una **verifica 1:1** sulla carta, dove il confronto è con un solo template, ma non per un'identificazione su grandi database.

**NatWest e Royal Bank of Scotland** (pp. 55–56):

- nel Regno Unito il limite di spesa **contactless** è spesso di **100 sterline**, con limiti cumulativi e autenticazione basata sul rischio, oltre i quali serve il PIN; la carta biometrica punta a rendere il pagamento ancora più rapido fino a quella soglia;
- oggi le banche usano soprattutto l'**approvazione biometrica tramite app**;
- **enrollment** semplice, in circa **cinque minuti**, a **casa** con una **custodia di plastica** (*sleeve*); una volta registrata sulla carta, l'impronta **non si può cambiare**;
- il dato biometrico **non lascia mai la carta**, non viene condiviso con esercente o banca e non finisce in cloud: un **buon bilanciamento della privacy**;
- la carta contiene un **circuito induttivo** (alimentazione dal lettore) e una **batteria** per l'enrollment.

> ⚠️ **«Non si può cambiare».** Un'impronta registrata in modo definitivo su una carta smarrita resta un template **non revocabile** in mano a chi la trova. Con il Match on Card il rischio è contenuto (il template non esce facilmente dal chip sicuro), ma è il problema della revocabilità che la biometria cancellabile cerca di risolvere (Lezione 13, §7).

**Banca Sella** (p. 57). Il gruppo Sella, con la francese **Idemia**, ha avviato la sperimentazione della carta di credito con impronta, utilizzabile per pagamenti **a contatto** e **contactless** in tutto il mondo. Il video della procedura di **enrollment** permette di riconoscere molti aspetti discussi nel corso (qualità, feedback, numero di acquisizioni).

<!-- SOURCE_VISUAL id="TAB21-V24" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="50" type="schema" description="Perché il Match on Card" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V25" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="52" type="schema" description="Struttura di una carta con match: soluzioni passive, attive e migliori" reason="Confronto grafico delle architetture" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V26" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="56" type="fotografia annotata" description="Carta biometrica NatWest con sleeve di enrollment, circuito induttivo e batteria" reason="Componenti indicati sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Iride e palmo alla cassa**

_(slide pp. 58–62)_

**Pagamenti con l'iride** (p. 58): un'ulteriore evoluzione che non richiede nemmeno la carta, con il nuovo concetto di **«eyepos»**.

**PayEye di Mastercard** (pp. 59–60):

- tecnologia **multimodale** con **iride e volto**;
- progetto pilota presso **Empik**, una delle maggiori catene europee, nel programma **Mastercard Global Biometric Checkout**;
- pagamenti rapidi con l'iride e meno bisogno di dispositivi fisici; il **primo pilota** di questo tipo in Europa;
- il terminale costa circa **500 euro**, con **illuminatori IR** e **più camere a focali diverse** (Lezioni 11–12).

**Pagamento con il palmo: Ingenico** con **Fujitsu** (pp. 61–62):

- **obiettivi**: più sicurezza e velocità nei pagamenti;
- **vantaggi**: preferito rispetto a volto e impronta perché percepito come **meno intrusivo**; offre **più controllo sull'attivazione** del pagamento rispetto al riconoscimento facciale;
- **applicazioni**: POS, **verifica dell'età** per l'acquisto di alcolici, pagamenti **senza carta** in parchi divertimento, concerti e festival;
- la slide mostra le fasi di **enrollment** e **autenticazione**.

> 💡 **Perché il controllo sull'attivazione conta.** Un volto si riconosce anche quando la persona **passa** davanti alla cassa senza voler pagare; un palmo va **appoggiato o avvicinato** deliberatamente. Il gesto diventa l'espressione del **consenso** alla transazione, un requisito tanto di usabilità quanto di privacy.

<!-- SOURCE_VISUAL id="TAB21-V27" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="60" type="fotografia prodotto" description="Terminale eyepos da circa 500 euro con illuminatori IR e camere a focali diverse" reason="Componenti indicati sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V28" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="62" type="fotografie" description="Ingenico Palm Vein con Fujitsu: enrollment e autenticazione al POS" reason="Fasi mostrate nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Conteggio delle persone in 3D**

_(slide pp. 63–65)_

**AXIS P8804 Stereo Sensor Kit** (p. 64): conteggio delle persone **3D preciso e affidabile** che ottimizza le attività di **marketing** e migliora la pianificazione di **personale e negozio**.

**Perché un sensore 3D** (p. 65). Una **mappa di profondità** permette di:

- affrontare condizioni difficili: **ombre**, **luce forte**, **bagliori**, **grandi flussi** di persone in entrata e in uscita;
- definire l'**altezza** degli oggetti da contare;
- **non contare** oggetti irrilevanti come **carrelli** o **passeggini**.

Il software di analisi gira sull'**unità principale** (fino a **8 metri**), alimentata in **PoE** o in corrente continua a bassa tensione.

> 💡 **Un conteggio che non identifica.** Un sensore stereo zenitale vede teste e spalle dall'alto come forme 3D, senza immagini riconoscibili dei volti. È l'esempio di un servizio AmI che usa la **classificazione** («persona sì o no», altezza) senza **identificazione**: il livello più basso del §2, spesso sufficiente e molto meno invasivo.

<!-- SOURCE_VISUAL id="TAB21-V29" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="63" type="immagini" description="Conteggio persone 3D con mappa di profondità" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Iride non vincolata e guidata**

_(slide pp. 66–70)_

Soluzioni **a basso costo** per l'iride **non vincolata ma guidata** stanno arrivando sul mercato (p. 66).

**M2SYS M2-AutoTilt** (pp. 67–69):

- per **enrollment su larga scala** e applicazioni di **autenticazione**;
- riduce **FTE** e **FTA** grazie al **brandeggio automatico**, che aiuta l'acquisizione, e a un **semaforo luminoso** che guida l'utente;
- **regolazione automatica dell'altezza** con **specchio motorizzato**, per controllo accessi, **identificazione dei pazienti** in sanità, **presenze**;
- acquisizione **simultanea di entrambe le iridi** entro **0,5 secondi** quando l'utente è nel campo operativo.

> 📌 **Il FTE è uno degli indicatori più critici** per i sistemi dell'iride (p. 68): se l'acquisizione è difficile, una parte della popolazione non riesce nemmeno a registrarsi.

**Specifiche** (p. 69):

| Parametro | Valore |
|---|---|
| dimensioni | 202 × 94 × 62 mm |
| campo operativo e distanza di messa a fuoco | **315–345 mm** |
| diametro dell'iride nell'immagine | **240 pixel** per un'iride media di **11,5 mm**; da **200 a 285** pixel per diametri da 9,5 a 13,5 mm |
| distanza interpupillare gestita | 45–85 mm |
| indicatori di posizione (tecnica stereoscopica proprietaria) | spento: soggetto non rilevato; **blu**: troppo lontano, avvicinarsi; **verde**: pronto; **rosso**: troppo vicino, allontanarsi |
| velocità massima dell'utente | **125 mm/s** |
| frame rate | **30 fps** |
| illuminazione IR | LED a **850 nm** (50%) e **750 nm** (50%) |

> 💡 **Verifica delle specifiche (nota didattica).** $240 / 11{,}5 \approx 20{,}9$, $200 / 9{,}5 \approx 21{,}1$ e $285 / 13{,}5 \approx 21{,}1$ pixel per millimetro: una scala costante di circa 21 px/mm, coerente con una distanza di lavoro fissa. Il campo di fuoco è largo solo 30 mm: alla velocità massima di 125 mm/s l'utente lo attraversa in $30 / 125 = 0{,}24$ s, cioè circa **7 frame** a 30 fps. Per questo servono il semaforo che rallenta l'avvicinamento e l'acquisizione simultanea dei due occhi. Le due lunghezze d'onda aiutano con occhi di colore diverso (Lezione 12, §11.3).

**Safran Morpho IAD con tornelli** (p. 70): **volto + iride in meno di 1 secondo**. L'uso dell'iride nel controllo accessi è ora possibile grazie alla **semplificazione** delle procedure di acquisizione.

<!-- SOURCE_VISUAL id="TAB21-V30" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="69" type="tabella" description="Specifiche dello scanner M2SYS M2-AutoTilt" reason="Tabella di specifiche con layout complesso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Biometria su droni**

_(slide pp. 71–83)_

La biometria è diventata **ubiqua, mobile, trasportabile** (p. 71): su **persone** con dispositivi indossabili, su **droni** per riconoscimento e sorveglianza in tempo reale, su **robot** per interazione e automazione. Una convergenza che unisce sicurezza, praticità e innovazione, **e rischi per la privacy**.

**Drone** (p. 72): **aeromobile a pilotaggio remoto**.

**Esempi di impiego** (pp. 73–74):

- un **brevetto IBM** sul riconoscimento biometrico nei droni, per il controllo di aree e perimetri riservati a persone autorizzate o per la **consegna** di materiali;
- **carceri italiane**: nella relazione del **Dipartimento dell'amministrazione penitenziaria** (DAP) per l'inaugurazione dell'anno giudiziario **2017**, uno studio di fattibilità prevede di affidare ai droni il **controllo perimetrale** e degli spazi interni, migliorando le telecamere fisse grazie al **cambio di punto di vista**.

**Ottiche e sensori** (pp. 75–79):

| Esempio | Caratteristiche |
|---|---|
| **SIMTOO XT-175** | **lenti asferiche**; camera per **navigazione remota** e **identificazione** delle persone |
| **Parrot ANAFI Ai** | drone robotico **4G** con profondità da **stereo matching** |
| **Yuneec Typhoon H Pro** con **RealSense R200** | modulo con **Intel Atom** che costruisce un **modello 3D** del mondo per evitare gli ostacoli; camera **C23** da **20 MP**, 4K, sensore da **1 pollice** |
| **RealSense D435** (p. 78) | FOV di profondità **85,2° × 58°**; profondità fino a **1280 × 720** a **90 fps**; distanza minima **0,11 m**; RGB **1920 × 1080** a 30 fps |
| **D435 con IMU** (p. 79) | movimenti e rotazioni in **6 gradi di libertà** (6DoF); gli accelerometri si usano anche per la **biometria comportamentale** in smartphone e braccialetti (Lezione 16) |

**Flusso ottico** (p. 76). Droni e UAV usano il flusso ottico della camera principale per **stabilizzare il volo** e **rilevare ostacoli** con correzioni di rotta, **senza** una vera ricostruzione 3D della scena o del soggetto (Lezione 16).

**Inseguimento automatico** (p. 80): **GeniusIdea Follow**, con riconoscimento facciale e IA, fa **orbitare il drone** attorno alla persona seguendone la struttura del volto, e riconosce i **gesti**.

**Droni biometrici** (pp. 81–83):

- **non** sono prodotti commerciali, ma parti di progetti di **sicurezza** (eventi naturali, soccorso, ricerche) e di **polizia** (pattugliamento, identificazione);
- **architettura tipica**: camera del drone → **face detection** → **streaming** → **matching sul server**;
- **polizia scozzese**: un **RPAS** (*remotely-piloted aircraft system*) per **rintracciare persone disperse** e verificarne l'identità con camere HD e reti neurali (person detection → face detection → identificazione); il sistema **non è nascosto**, perché il drone è ben visibile;
- **dataset P-DESTRE**: dati completamente annotati per **rilevamento**, **tracking**, **re-identificazione** e **ricerca** di pedoni **da dispositivi aerei**, anche con **vestiti diversi** (Lezione 18).

> 💡 **Quanti pixel sul volto da un drone (nota didattica, valori ipotetici).** Con il modello a foro stenopeico la dimensione a terra di un pixel vale $GSD = p \cdot Z / f$. Per un sensore da 1″ con pixel di circa 2,4 µm e una focale di 8,8 mm:
> - a $Z = 30$ m, $GSD \approx 2{,}4 \cdot 10^{-6} \cdot 30 / 8{,}8 \cdot 10^{-3} \approx 8{,}2$ mm, e i circa 62 mm tra gli occhi occupano solo **8 pixel**;
> - a $Z = 5$ m si arriva a circa **45 pixel**.
>
> Il requisito di circa 60 pixel tra gli occhi (Lezione 18, §12) si raggiunge solo **da vicino** o con uno **zoom**: a quota di sorveglianza il drone può rilevare e tracciare, ma per identificare deve avvicinarsi o usare la re-identificazione del corpo.

<!-- SOURCE_VISUAL id="TAB21-V31" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="73" type="disegno di brevetto" description="Brevetto IBM sul riconoscimento biometrico con droni" reason="Figura del brevetto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V32" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="80" type="fotografie" description="GeniusIdea Follow: drone che orbita attorno alla persona seguendone il volto e i gesti" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V33" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="83" type="immagini" description="Dataset P-DESTRE per detection, tracking, re-identificazione e ricerca di pedoni da droni" reason="Esempi visivi del dataset" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Biometria indossabile**

_(slide pp. 84–88)_

**Biometria indossabile, pervasiva e ubiqua** (pp. 84–85). Smartwatch, occhiali e body camera integrano sistemi biometrici basati sulle immagini per **monitorare dati vitali** e **identificare** l'utente, sia nelle **forze dell'ordine** sia per gli **utenti comuni**; si sviluppano sistemi di riconoscimento facciale anche sugli **smartwatch**.

**Sorveglianza biometrica mobile** (p. 86):

- **allarmi** di match su telefoni e tablet;
- **enrollment** di volti in una watchlist, in cloud o on-premise, da **dispositivi mobili**, **droni** e **veicoli autonomi**;
- **occhiali** con riconoscimento (Xloong);
- la slide segnala il rischio per la **privacy** (Lezione 18, §13.1).

**HELIX SDK** (p. 87): biometria **a bordo delle bodycam** delle forze dell'ordine, con confronto con **black list**, **ricercati**, **multe** e **mandati**. Un **uso molto discusso**.

> ⚠️ **Bodycam con riconoscimento in Europa.** Il riconoscimento facciale **in tempo reale** in spazi pubblici a fini di contrasto è in linea di principio **vietato** dall'AI Act, salvo eccezioni strettamente definite (Lezione 13, §5.3). Una bodycam che confronta ogni passante con una watchlist è il caso tipico di identificazione biometrica remota in tempo reale.

**Gesture e sistemi pervasivi** (p. 88). L'**AI Pin** di **Humane** concentrava le migliori caratteristiche dei sistemi smart con l'usabilità in primo piano: moduli di IA secondo le esigenze del cliente e comandi con **gesti** davanti al dispositivo. Humane è stata acquisita da **HP** per circa **116 milioni di dollari** (proprietà intellettuale, team e parte della tecnologia), ma l'AI Pin **non è stato mantenuto né rilanciato**.

<!-- SOURCE_VISUAL id="TAB21-V34" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="87" type="fotografie" description="HELIX SDK: biometria a bordo delle bodycam con black list, ricercati, multe e mandati" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Robot biometrici**

_(slide pp. 89–95)_

| Robot | Ruolo della biometria (slide) |
|---|---|
| **LG CLOi** Serving, Porter e Shopping Cart Robot (p. 90) | riconoscere i **titolari del servizio** in ambienti affollati e da diversi punti di vista; i **robot a noleggio** possono caricare le **impostazioni** dell'utente riconosciuto |
| **Boston Dynamics SpotMini** (p. 91) | robot venduti come **guardie di sicurezza**, con riconoscimento di persone **abilitate** ed **estranee** |
| **Benzie the DroneDog** (p. 91) | cane robot di **Boston Dynamics** sviluppato da **Asylon Robotics** per il **Mercedes-Benz Stadium** di Atlanta: pattuglia autonomamente con turni di **30 minuti**, video in tempo reale **1080p** a colori con **visione notturna** e **riconoscimento facciale a bordo** |
| **«Saetta»** dei **Carabinieri** (p. 92) | primo cane robot, un **Spot** di Boston Dynamics, affiancato al **Nucleo Artificieri di Roma** per ricognizioni su terreni impervi; controllato da un operatore via **tablet** fino a **150 metri**; sale e scende le scale, apre porte, rimuove ostacoli; **mappa** i luoghi con sistemi **laser** e **termici** |
| **Spot dei Servizi Segreti statunitensi** (p. 92) | avvistato nel **2024** mentre pattuglia il perimetro del resort di **Mar-a-Lago** in Florida |
| **Suzhou Pangolin Robot** (p. 93) | riconosce l'utente all'**ingresso** e lo **guida** nei percorsi di centri commerciali, ospedali, uffici |
| **LG CLOi SuitBot** (p. 94) | **esoscheletro** che apprende dall'analisi di dati **biometrici e ambientali**, misura i movimenti e suggerisce gesti e posizioni ottimali per l'**efficienza energetica** |
| **altri esoscheletri** (p. 95) | offerte in crescita (Orthexo, Ekso Bionics, Comau), adottate da numerose multinazionali |

> 📌 **Gli ausili corporei devono adattarsi alla persona in modo biometrico** (p. 94): un esoscheletro deve **misurare** e/o **riconoscere** chi lo indossa per regolare forza, ampiezza e ritmo dei movimenti.

<!-- SOURCE_VISUAL id="TAB21-V35" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="92" type="fotografie" description="Cane robot Saetta dei Carabinieri e Spot dei Servizi Segreti a Mar-a-Lago" reason="Esempi visivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. La biometria degli stati mentali**

_(slide pp. 96–106)_

**Accedere in modo biometrico a ciò che accade nel cervello** (p. 96): micro-espressioni, **EEG**, stati cerebrali, **biofeedback**.

**Micro-espressioni e credito: Ping An** (p. 97). Secondo il *Financial Times*, la società finanziaria cinese **Ping An** usa nel ramo prestiti una tecnologia che individua **54 micro-espressioni** brevi e involontarie (movimenti rapidi degli occhi, ammiccamenti) che il volto produce prima che il cervello le controlli, per **valutare le richieste di prestito**. La società dichiara di aver **ridotto le perdite su crediti del 60%**. Le banche occidentali sono state meno disposte a usarla per timori di **inaffidabilità** e di **scarsa eticità**.

> ⚠️ **Nota della slide.** La ricerca sulle micro-espressioni è un ramo dell'analisi delle emozioni, ma **non è validata** in ambito accademico come misura di **sincerità** o **affidabilità finanziaria** (si ricordi il dibattito Ekman-Feldman Barrett, Lezione 16, §4). Una riduzione del 60% delle perdite, dichiarata dall'azienda e senza un confronto controllato, non dimostra che la tecnologia misuri davvero la menzogna.

**Analisi avanzate possibili** (pp. 98–99): **stato emotivo**, **biofeedback**, **concentrazione**, **sonno**, **identificazione**.

**Biometria con l'EEG** (p. 100). L'**elettroencefalografia** registra l'attività elettrica del cervello. È un segnale **variabile nel tempo** e **senza un pattern ripetitivo evidente**, a differenza dell'ECG; studi recenti aumentano però le aspettative sulla possibilità di usarlo per la biometria.

**Hardware semplificato** (pp. 101–106):

| Dispositivo | Caratteristiche |
|---|---|
| **set classico di elettrodi** e ricerca dell'University of Eastern Finland; **Emotiv**; occhiali **Safilo-InteraXon** (p. 101) | dal laboratorio ai dispositivi indossabili |
| **NeuroSky** (p. 102) e **MindWave Mobile 2** (p. 103) | lettori consumer di **ECG** ed **EEG** |
| **Muse** di InteraXon (p. 104) | archetto sulla fronte e attorno alle orecchie con più sensori EEG; **feedback in tempo reale** per meditazione e concentrazione; usato in ricerche di NASA, IBM, MIT, Harvard |
| **sensing eyewear** (p. 105) | feedback istantaneo su ciò che accade nel cervello durante sessioni di **allenamento cognitivo** |
| **Narbis** (p. 106) | **neurofeedback** con un modello a penalità e ricompensa, il **condizionamento operante** in uso da oltre 50 anni; solo **3 sensori** (dietro ogni orecchio e sopra la testa) e un algoritmo **brevettato dalla NASA** per rilassamento, distrazione e concentrazione. Quando l'attenzione cala le **lenti si scuriscono**, quando ci si concentra tornano **trasparenti** |

> 💡 **Il biofeedback come anello chiuso.** Nei sistemi del §7 la biometria dà un esito (paga o non paga). Qui l'esito torna **alla persona** (le lenti si scuriscono) e ne modifica lo stato, che viene rimisurato: un sistema di controllo in anello chiuso sul cervello. È il massimo della «proattività» dell'AmI, e anche il caso in cui l'inferenza sullo stato interno è più delicata (Lezione 13, §16).

<!-- SOURCE_VISUAL id="TAB21-V36" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="98" type="illustrazione" description="Stati del cervello e biofeedback" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V37" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="102" type="fotografie prodotto" description="Tecnologia NeuroSky per ECG ed EEG" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB21-V38" source="Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf" page="103" type="fotografia prodotto" description="NeuroSky MindWave Mobile 2" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Conclusioni: attenzione alle 4P**

_(slide pp. 107–108)_

**Abbiamo visto** (p. 107): applicazioni per l'ambient intelligence con le caratteristiche necessarie; tecnologie per i pagamenti biometrici (impronta su carta, iride, palmo IR); biometria indossabile (occhiali, bodycam); biometria degli stati mentali; biometria e robotica.

> ✅ **Le 4P** (p. 108).
> - **Performance** in continuo aumento.
> - **Pervasività** della biometria: mobile, **nascosta** (*covert*)…
> - **Possibilità** applicative, non solo nella sicurezza.
> - **Privacy** a rischio.

> 💡 **Le 4P come riassunto del corso.** Le prime tre P sono il frutto di quanto studiato: deep learning e fusione (Lezioni 5–8 e 15), sensori e ottiche (Lezioni 11, 12 e 18), scenari applicativi (Lezioni 14, 16, 17 e 21). La quarta P è il loro prezzo, trattato nelle Lezioni 13, 17, 19 e 20: più i sistemi sono accurati, pervasivi e versatili, più devono essere progettati con la privacy, la sicurezza e il testing rigoroso (Lezioni 9–10) fin dall'inizio.

---

### **15. La simulazione d'esame**

_(griglia p. 1)_

Il testo della simulazione d'esame svolta in aula **non è pubblicato**; nella cartella c'è solo una **griglia** da stampare o annotare direttamente sul PDF.

**La griglia** ha, per ogni domanda:

| Colonna | Contenuto |
|---|---|
| **Num. Dom.** | numero della domanda |
| **Risposta data** | la risposta scelta |
| **Risposta giusta** | la risposta corretta, dopo la correzione |
| **Esatto (1 punto) / Sbagliato (0 punti)** | il punteggio della domanda |
| **Note** | osservazioni, per esempio sul ragionamento |

In fondo si calcolano i **totali**. L'indicazione è di cercare di ottenere **più di 24 punti**, con una **conversione indicativa** in voto.

> ⚠️ **Numerazione interna.** La griglia è intestata alla «simulazione di esame della lezione Num. 24», residuo della numerazione di una versione precedente del corso: si riferisce a questa lezione conclusiva.

> 💡 **Come usare la griglia per studiare.** Con 1 punto per risposta giusta e 0 per sbagliata (Lezione 1 per le regole d'esame), la colonna **Note** è la più preziosa: annotare perché si è sbagliato (definizione confusa, calcolo, lettura frettolosa del testo) indica quali lezioni ripassare. Le tabelle «Distinzioni da non confondere» alla fine di ogni lezione sono pensate proprio per le domande a risposta multipla.

---

### **16. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **AmI 2007** / **AmI oggi** | sensori, rete, server centrale / edge AI, percezione multimodale, personalizzazione, privacy by design |
| **Ubiquitous** / **pervasive** / **ambient** | accessibile ovunque / ubiquità, mobilità e contesto insieme / assistenza proattiva negli oggetti quotidiani |
| **Identificazione** / **classificazione** / **comprensione delle azioni** | chi sei / a quale gruppo appartieni / che cosa fai |
| **Classificazione dell'età** / **regressione** | poche classi, accuratezza / valore stimato, MAE |
| **TAR** / **FAR** | genuini accettati, $1 - FRR$ / impostori accettati |
| **Accuratezza** / **accettazione** | spesso in conflitto: iride accurata e poco accettata, volto il contrario |
| **Match on Card** / **match su server** | il template non lascia la carta / il template viaggia e risiede altrove |
| **Eyepos** / **carta biometrica** | nessun supporto, iride e volto alla cassa / impronta sulla carta |
| **Palmo al POS** / **volto al POS** | gesto deliberato, consenso esplicito / riconoscimento anche involontario |
| **Conteggio 3D** / **videosorveglianza** | classificazione senza identità / immagini riconoscibili |
| **Rilevare e tracciare da drone** / **identificare da drone** | pochi pixel bastano / servono vicinanza o zoom |
| **Flusso ottico del drone** / **ricostruzione 3D** | stabilizzazione e ostacoli / modello della scena con sensori di profondità |
| **ECG** / **EEG** | segnale periodico, biometria più matura / segnale senza pattern ripetitivo, ancora in studio |
| **Micro-espressioni misurate** / **sincerità dimostrata** | pattern facciali / inferenza non validata scientificamente |

---

### **17. Sintesi della lezione**

> ✅ **In sintesi.**
> - **Ambient intelligence**: ambiente digitale che supporta proattivamente e in modo sensato le persone; oggi edge AI, percezione multimodale, personalizzazione e privacy by design; proprietà di invisibilità, contesto e proattività; adattività tramite identificazione, classificazione e comprensione delle azioni; scenari dalla casa alla smart city; tratti adatti (cuore molto rilevante), volto robusto alla posa, stima di età e genere, rischi dell'etnia.
> - **Trend** (dispensa): accuratezza contro accettazione dei tratti; progressi e limiti di volto, impronta, iride, palmo, ECG, voce, camminata, soft biometrics e multibiometria; scenari pubblici, privati, centrati sull'utente e personali; otto aspetti da migliorare (usabilità, accettazione, privacy, sicurezza, accuratezza, tempo, interoperabilità, scalabilità).
> - **Pagamenti**: Match on Card con sensori sottili (NatWest, Banca Sella con Idemia), eyepos con iride e volto (PayEye), vene del palmo al POS (Ingenico con Fujitsu).
> - **Applicazioni particolari**: conteggio 3D delle persone, iride guidata a basso costo (M2-AutoTilt, Morpho IAD), droni biometrici e dataset aerei, bodycam e occhiali, robot guardiani e di servizio, esoscheletri, micro-espressioni nel credito, EEG e biofeedback.
> - **Conclusione**: le 4P (performance, pervasività, possibilità, privacy) e la griglia per la simulazione d'esame.
