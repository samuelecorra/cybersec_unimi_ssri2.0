# **Lezione 6: Scorciatoie fondamentali in Visual Studio Code**

## **1. Perché imparare le scorciatoie fin da subito**

Prima di iniziare a scrivere davvero HTML, conviene imparare alcune scorciatoie da tastiera di base.  
Motivi:

- lavoriamo più velocemente;
    
- usiamo meno il mouse (meno fatica, meno problemi ai tendini nel lungo periodo);
    
- restiamo con le mani sulla tastiera mentre scriviamo codice.
    

In questa lezione vediamo solo poche scorciatoie essenziali, sufficienti per partire. Le altre, più avanzate, si possono imparare in seguito.

> Nota: il professore si riferisce a tastiera e tasti in ambiente Windows. Su Mac molte scorciatoie sono simili, spesso con `Ctrl` → `Cmd`, ma il concetto non cambia.

---

## **2. Orientarsi sulla tastiera: i tasti fondamentali**

Per usare bene le scorciatoie dobbiamo conoscere i tasti “speciali” principali:

- **Barra spaziatrice**: lo spazio normale.
    
- **Alt**: vicino alla barra spaziatrice, lato sinistro.
    
- **Tasto Windows**: con il logo di Windows, accanto ad Alt.
    
- **Ctrl (Control)**: ancora più a sinistra.
    
- **Maiusc (Shift)**: tasto sotto il tasto Invio, usato per scrivere in maiuscolo finché lo si tiene premuto. Sulle tastiere inglesi è `Shift`.
    
- **Bloc Maiusc (Caps Lock)**: blocca il maiuscolo finché non lo si disattiva.
    
- **Tab**: sopra il blocco Maiusc, con due freccette. Serve a inserire una “tabulazione” (più spazi insieme), molto importante per l’indentazione del codice.
    
- **Freccette direzionali**: in basso a destra (↑, ↓, ←, →) utili per muoversi nel testo.
    

Questi tasti, combinati tra loro, permettono di:

- duplicare righe,
    
- spostare righe,
    
- selezionare blocchi di codice,
    
- formattare automaticamente il file,
    
- scrivere più velocemente.
    

---

## **3. Duplicare una riga o un blocco di codice**

### **3.1 Duplicare una singola riga**

Per duplicare la riga su cui si trova il cursore:

```text
Alt + Maiusc + Freccia giù
Alt + Maiusc + Freccia su
```

- `Alt + Maiusc + Freccia giù`: duplica la riga sotto.
    
- `Alt + Maiusc + Freccia su`: duplica la riga sopra.
    

Funziona anche con più righe selezionate.

### **3.2 Duplicare più righe insieme**

1. Posizionati sulla prima riga del blocco.
    
2. Tieni premuto **Maiusc**.
    
3. Usa **Freccia su/giù** per selezionare tutte le righe desiderate.
    
4. Premi:
    

```text
Alt + Maiusc + Freccia giù
```

Tutto il blocco di codice viene duplicato. Questa operazione torna utilissima, ad esempio, quando dobbiamo ripetere più volte una struttura HTML simile (card, righe di tabella, blocchi di contenuto).

---

## **4. Spostare una riga o un blocco**

Se vogliamo **spostare** una riga (o più righe) verso l’alto o il basso senza copiarla, usiamo:

```text
Alt + Freccia giù
Alt + Freccia su
```

Differenza importante:

- con **Alt + Maiusc + Freccia** → duplichiamo;
    
- con **Alt + Freccia** → spostiamo.
    

Questo ci permette di riordinare velocemente il codice senza tagliare e incollare.

---

## **5. Selezionare senza mouse**

Per selezionare più righe senza usare il mouse:

1. Posizionati all’inizio (o alla fine) del blocco.
    
2. Tieni premuto:
    

```text
Maiusc
```

3. Usa le **freccette su/giù**: la selezione si estende alle righe successive o precedenti.
    

Questa selezione può poi essere:

- duplicata (Alt + Maiusc + Freccia giù),
    
- spostata (Alt + Freccia su/giù),
    
- copiata, tagliata, incollata.
    

L’obiettivo è ridurre al minimo i movimenti mano-tastiera → mouse → tastiera, e lavorare quasi sempre solo con la tastiera.

---

## **6. Andare a capo senza spezzare la riga**

A volte il cursore si trova in mezzo a una riga, ma vogliamo andare a capo **sotto** senza spezzare il testo della riga corrente.

Se premiamo solo **Invio**, la riga viene tagliata in due.  
Invece, per aggiungere una nuova riga sotto _senza toccare la riga attuale_, usiamo:

```text
Ctrl + Invio
```

Ovunque si trovi il cursore:

- `Ctrl + Invio` inserisce una nuova riga sotto,
    
- la riga esistente resta intatta.
    

È molto utile quando stiamo scrivendo codice e vogliamo aprire uno spazio sotto per continuare a scrivere.

---

## **7. Salvare velocemente il file**

Per salvare il file corrente:

```text
Ctrl + S
```

Questo comando lo useremo continuamente.  
Se stiamo lavorando con **Live Server**, ogni `Ctrl + S` comporta due effetti:

- il file viene salvato;
    
- la pagina nel browser viene aggiornata automaticamente.
    

---

## **8. Auto-format del codice con Prettier**

Abbiamo installato **Prettier** come formattatore di codice.  
Se il file HTML è scritto in modo disordinato (indentazione sbagliata, righe lunghe, spazi a caso), possiamo sistemarlo in un istante con:

```text
Alt + Maiusc + F
```

Questo comando:

- analizza il file,
    
- applica le regole di formattazione,
    
- rende il codice più leggibile (indentato, ordinato, coerente).
    

È sempre possibile:

- scrivere “alla buona”,
    
- e poi, ogni tanto, premere `Alt + Maiusc + F` per ripulire tutto.
    

Esiste anche l’opzione per auto-formattare **al salvataggio** (ogni `Ctrl + S`), ma il professore per ora non entra nelle impostazioni per non aggiungere troppa complessità.

---

## **9. Completamento automatico con Tab**

Visual Studio Code suggerisce spesso frammenti di codice già pronti.

### **9.1 Boilerplate HTML5**

Se scriviamo:

```text
html
```

all’inizio di un file vuoto, nella lista dei suggerimenti appare `html:5` o simile (a seconda dell’estensione installata).  
Se selezioniamo quel suggerimento e premiamo **Tab**, VS Code genera automaticamente lo scheletro base di una pagina HTML5 (doctype, `<html>`, `<head>`, `<body>`, ecc.).

### **9.2 Tag HTML rapidi**

Se scriviamo ad esempio:

```text
h1
```

e vediamo apparire il suggerimento `h1` nella lista, possiamo:

- selezionarlo con le frecce,
    
- premere **Tab**.
    

VS Code genererà automaticamente:

```html
<h1></h1>
```

con il cursore posizionato all’interno.  
Questo rende molto veloce la creazione di strutture HTML.

---

## **10. Conclusione: le basi operative sono pronte**

Ricapitolando, abbiamo visto le scorciatoie più utili per iniziare:

- **Duplicare righe**: `Alt + Maiusc + Freccia su/giù`
    
- **Spostare righe**: `Alt + Freccia su/giù`
    
- **Selezionare blocchi**: `Maiusc + Freccia su/giù`
    
- **Nuova riga sotto (senza spezzare)**: `Ctrl + Invio`
    
- **Salvare**: `Ctrl + S`
    
- **Auto-format con Prettier**: `Alt + Maiusc + F`
    
- **Completamento con Tab**: usare i suggerimenti di VS Code per HTML.
    

Queste sono le “super basi” per utilizzare Visual Studio Code in modo efficiente sin da subito.

Dalla prossima lezione inizieremo finalmente a lavorare sulla **struttura base di una pagina HTML**, analizzando in dettaglio ciò che finora abbiamo solo accennato (doctype, `<html>`, `<head>`, `<body>`, titolo, ecc.).