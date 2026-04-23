# **Lezione 3: I Commenti in HTML**

I commenti sono uno degli strumenti più sottovalutati quando si inizia a programmare, ma diventano fondamentali non appena il progetto cresce o quando ci si ritrova a lavorare in team. In questa lezione vediamo **che cosa sono**, **come si creano**, **perché si usano** e anche **come possono aiutarci nel debug e nell’organizzazione del codice**.

---

## **1. Che cos’è un commento in HTML**

Un commento è una porzione di codice **che il browser ignora completamente**.

Questo significa:

- **non viene mostrato nella pagina**;
    
- viene comunque mantenuto nel **sorgente**, quindi lo vedi con:
    
    - click destro → _Visualizza sorgente pagina_.
        

I commenti appaiono in verde in Visual Studio Code, e possono contenere qualunque cosa: note, istruzioni, parti di codice temporaneamente disattivate.

Esempio:

```html
<!-- Questo è un commento -->
```

---

## **2. Come si crea un commento**

La sintassi di un commento in HTML è sempre:

```html
<!--  QUI DENTRO CI SONO I COMMENTI  -->
```

La struttura è:

- `<!--` apertura,
    
- contenuto testuale o persino codice,
    
- `-->` chiusura.
    

Tutto ciò che sta dentro **viene completamente ignorato** dal browser.

Puoi andare a capo, scrivere frasi lunghe, aggiungere note: il browser non lo mostrerà.

Esempio:

```html
<!--
  Questo è un commento
  lungo su più righe.
  Nulla di tutto questo viene renderizzato.
-->
```

---

## **3. A cosa servono realmente i commenti**

### **3.1 Servono a noi stessi**

Quando lavori su un progetto:

- oggi modifichi l’homepage,
    
- domani fai la pagina contatti,
    
- la settimana dopo torni sulla homepage perché c’è un bug…
    

…è impossibile ricordare subito **cosa avevi fatto e perché**.

Un commento può chiarire la funzione di un blocco di codice:

```html
<!-- Inizio sezione: sidebar laterale -->
```

Oppure:

```html
<!-- TODO: correggere il padding di questo blocco -->
```

Dopo giorni o settimane, ti salva la vita.

---

### **3.2 Servono quando lavori con altre persone**

Nei progetti reali, soprattutto se il file è lungo:

- nessun collega può capire al volo tutto il flusso,
    
- i blocchi di codice non sempre sono ovvi,
    
- molto codice è ripetitivo o strutturale.
    

Un semplice commento come:

```html
<!-- Footer: informazioni legali e link rapidi -->
```

permette ai collaboratori di orientarsi immediatamente.

Oppure:

```html
<!-- Inizio sezione Articoli correlati -->
...
<!-- Fine sezione Articoli correlati -->
```

Diventa immediatamente chiaro cosa appartiene a cosa.

---

## **4. Esempi pratici di commenti strutturali**

Puoi delimitare parti di codice molto ampie per capire cosa contengono:

```html
<!-- INIZIO SEZIONE 2 -->
<h2>Sezione 2</h2>
<p>Contenuto…</p>
<!-- FINE SEZIONE 2 -->
```

Oppure delimitare macro-blocchi come sidebar, footer, header, ecc.

```html
<!-- INIZIO SIDEBAR -->
<div class="sidebar">
  ...
</div>
<!-- FINE SIDEBAR -->
```

Con molti file e molte pagine, questa pratica diventa **indispensabile**.

---

## **5. Shortcut per commentare e decommentare in un attimo**

In Visual Studio Code puoi creare o rimuovere un commento premendo:

```
CTRL + ù
```

(o `CTRL + accentata`, il tasto vicino all’INVIO)

Funziona in due modi:

- **Selezioni un pezzo di codice → CTRL + ù → diventa commento**
    
- **Riripremi CTRL + ù → torna normale**
    

Esempio:

1. Selezioni:
    

```html
<p>Testo di esempio</p>
```

2. Premi:
    

```
CTRL + ù
```

Diventa:

```html
<!-- <p>Testo di esempio</p> -->
```

Puoi farlo anche su grandi blocchi selezionati con Shift + click.

---

## **6. Usare i commenti per fare debug**

I commenti in HTML hanno un superpotere:  
**possono disattivare porzioni di codice senza cancellarle**.

Questo ti permette di vedere cosa succede “se quella parte non esiste”.

Esempio:

### Prima

```html
<h3>Titolo Importante</h3>
<p>Paragrafo di prova...</p>
```

### Dopo aver commentato

```html
<!--
<h3>Titolo Importante</h3>
<p>Paragrafo di prova...</p>
-->
```

Risultato:

- la sezione **scompare** dal browser,
    
- ma il codice rimane lì, intatto, e puoi ripristinarlo in un attimo.
    

Questo è utile per testare varianti, verificare bug, o isolare problemi.

---

## **7. Estensione “TODO Highlight”: creare una To-Do List nel codice**

Il docente consiglia di installare l’estensione:

```
TODO Highlight
```

Una volta installata:

- tutto ciò che scrivi nei commenti con parole chiave come `TODO:` o `FIXME:` viene evidenziato in modo vistoso;
    
- una lista laterale mostra tutte le cose da fare nel progetto;
    
- cliccando su un elemento ti porta subito al punto giusto del file.
    

Esempio:

```html
<!-- TODO: cambiare il titolo principale -->
<!-- FIXME: sistemare il font-size del sottotitolo -->
```

Vengono evidenziate in giallo/rosa e appaiono nel pannello delle to-do.

È un metodo professionale per gestire attività, bug e modifiche future.

---

## **8. Usare i commenti come “switch” per alternative di layout**

Un altro trucco del prof è usare i commenti per **provare varianti** senza cancellare nulla.

Esempio: tre possibili titoli.

```html
<!-- <h1>Titoletto</h1> -->
<h1>Titolo Principale</h1>
<!-- <h1>Titolissimo</h1> -->
```

Con un click (CTRL + ù) puoi disattivare uno e attivare un altro, testando layout differenti in un secondo.

---

## **9. Cosa NON fare con i commenti**

- Non lasciarne troppi inutili.
    
- Non commentare l’ovvio:
    
    ```html
    <!-- Questo è un paragrafo -->
    <p>Testo...</p>
    ```
    
- Non usarli per “nascondere bug” veri: servono solo per testare.
    

---

## **10. Conclusione**

Abbiamo visto che i commenti sono:

- **invisibili al browser**,
    
- utili per **scrivere codice più leggibile**,
    
- essenziali quando **si lavora in team**,
    
- fondamentali per il **debug rapido**,
    
- potentissimi per creare **to-do list integrate nel codice**.
    

Nella prossima lezione iniziamo con le **liste HTML**:  
ordinate, non ordinate e descrittive.