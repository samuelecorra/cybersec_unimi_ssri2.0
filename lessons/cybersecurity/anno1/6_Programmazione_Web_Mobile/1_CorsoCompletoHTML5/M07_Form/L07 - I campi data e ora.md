## **Lezione 7: I campi data e ora – `date`, `time`, `month`, `week`, `datetime-local`**

---

### **1. Perché esistono così tanti input dedicati alle date?**

Prima di HTML5, l’unico modo per far scrivere una data era:

```html
<input type="text" placeholder="gg/mm/aaaa">
```

Questo creava problemi enormi:

- ognuno scriveva la data in un formato diverso
    
- bisognava validare manualmente con JavaScript
    
- si confondevano giorno/mese/anno
    
- inserire l’ora era un incubo
    
- i siti mobili non aiutavano in alcun modo
    

Con HTML5 arrivano finalmente **picker nativi**, diversi a seconda del tipo di dato temporale che vogliamo.

Il risultato?

- usabilità perfetta
    
- validazione automatica
    
- tastiere ottimizzate su mobile
    
- input precisi e standardizzati
    

Vediamoli uno per uno.

---

## **2. `type="datetime-local"` – data + ora (il più completo)**

```html
<input type="datetime-local" id="appuntamento" name="appuntamento">
```

Risultato:

- selezionatore di **giorno**
    
- selezionatore di **mese**
    
- selezionatore di **anno**
    
- selezionatore di **ora e minuti**
    

È una combinazione completa del pacchetto “tempo”.

Esempio di valore generato:

```
2025-03-10T14:30
```

Perfetto per:

- appuntamenti
    
- prenotazioni
    
- reminder
    
- timestamp precisi
    

---

## **3. `type="date"` – solo giorno/mese/anno**

```html
<input type="date" id="compleanno" name="compleanno">
```

Risultato:

- calendario completo
    
- selezione di giorno, mese e anno
    

È uno degli input più comodi in assoluto.

Esempi d’uso:

- data di nascita
    
- data di prenotazione
    
- consegna pacchi
    
- scadenze
    

---

## **4. `type="month"` – selezione solo del mese**

```html
<input type="month" id="mese" name="mese">
```

Risultato:

- selezionatore con mese + anno
    
- nessun giorno
    

Esempio:

```
2028-06
```

È utilissimo quando l’informazione “giorno preciso” **non è importante**.

Esempi d’uso:

- mese di inizio contratto
    
- mesi di rendicontazione
    
- rate mensili
    
- periodi fiscali
    

---

## **5. `type="week"` – selezione di una settimana**

```html
<input type="week" id="settimana" name="settimana">
```

Risultato:

- selezionatore di settimane numerate
    
- secondo lo standard ISO (sett. 1, sett. 2, sett. 3…)
    

Esempio:

```
2023-W35
```

Il browser calcola automaticamente la corrispondenza di calendario.

Perfetto per:

- turni lavorativi
    
- sprint di sviluppo
    
- settimane accademiche
    
- pianificazione aziendale
    

---

## **6. `type="time"` – solo orario**

```html
<input type="time" id="orario" name="orario">
```

Risultato:

- selezione di ora e minuti
    
- nessuna data
    

Formato generato:

```
14:45
```

Usi tipici:

- orari di apertura
    
- orari di appuntamento
    
- gestione di sistemi di prenotazione
    
- reminder che non richiedono la data
    

---

## **7. Il vantaggio enorme su mobile**

Tutti questi input hanno un super-bonus:

- su smartphone si aprono **picker dedicati** (calendari, orologi, ecc.)
    
- niente tastiere sbagliate
    
- zero confusione nei formati
    

Rende la compilazione:

- più rapida
    
- più precisa
    
- più comoda
    

Un miglioramento gigantesco nella UX (User Experience).

---

## **8. Riepilogo dei campi temporali**

|Input type|Cosa permette di selezionare|Esempio di valore|
|---|---|---|
|`date`|giorno + mese + anno|`2025-11-29`|
|`datetime-local`|data + ora|`2025-11-29T15:30`|
|`month`|mese + anno|`2025-11`|
|`week`|numero della settimana|`2025-W48`|
|`time`|solo ora|`15:30`|

---

## **9. Conclusione**

I campi legati alle date sono **piccoli superpoteri nativi del browser**.  
Ci permettono di ottenere input precisi, facili, standardizzati e già validati senza scrivere una riga di JavaScript.

Sono una delle aggiunte migliori di HTML5, e dovresti usarli sempre quando ti servono date o orari.

---

Prossima lezione: iniziamo a vedere gli input “di scelta” come `radio`, `checkbox`, `color`, `file` e tutto il resto dell’arsenale.