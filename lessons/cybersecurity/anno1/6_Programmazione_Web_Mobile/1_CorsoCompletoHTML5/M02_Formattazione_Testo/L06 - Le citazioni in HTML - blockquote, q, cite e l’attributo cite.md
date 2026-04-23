## **Lezione X: Le citazioni in HTML — `<blockquote>`, `<q>`, `<cite>` e l’attributo `cite`**

---

## **1. Introduzione generale**

In HTML esistono tre elementi specifici per gestire le citazioni:

- **`<blockquote>`** → citazioni _lunghe_, su più righe, in forma di blocco.
    
- **`<q>`** → citazioni _brevi_, all’interno della frase.
    
- **`<cite>`** → per indicare _la fonte_ (titolo dell’opera, autore, ente).
    

Oltre a questi tre tag esiste anche **l’attributo `cite`**, che può essere applicato a `<blockquote>` e `<q>` per indicare l’URL della fonte originaria.

Attenzione:  
I tre elementi sembrano simili, ma hanno **scopi diversi**. Usarli bene significa avere:

- un codice semanticamente corretto,
    
- maggiore accessibilità,
    
- migliore comprensione da parte dei browser e degli screen reader,
    
- un markup più professionale.
    

---

## **2. `<blockquote>` – Citazioni lunghe, in blocco**

Serve a riportare:

- paragrafi lunghi,
    
- estratti di testo,
    
- conversazioni,
    
- citazioni che meritano spazio visivo.
    

### **Esempio**

```html
<p>Nel suo saggio, l’autore scrive:</p>

<blockquote>
  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec urna
  euismod, aliquet urna at, varius ligula. Nulla facilisi.
</blockquote>

<p>Come si nota, il tono è molto formale.</p>
```

### **Caratteristiche**

- È un **elemento block**, quindi si posiziona come un paragrafo a sé.
    
- Il browser applica **indentazione automatica** (margine a sinistra).
    
- È perfetto per riportare citazioni “a blocchi”, come nei libri o negli articoli di giornale.
    

---

## **3. Attributo `cite` (applicabile a `<blockquote>` e `<q>`)**

Può essere usato per memorizzare la fonte originale come URL:

```html
<blockquote cite="https://www.example.com/articolo">
  L’educazione è l’arma più potente che si possa usare per cambiare il mondo.
</blockquote>
```

### Cosa cambia visivamente?

**Nulla.**  
Non viene mostrato sullo schermo.

### A cosa serve allora?

- Documentazione interna del codice.
    
- Completezza semantica.
    
- Buona pratica quando si cita materiale proveniente da siti esterni.
    

**Nota:** secondo lo standard attuale, gli screen reader _non leggono_ il contenuto dell’attributo `cite`.  
Quindi è utile, ma non “attivo”.

---

## **4. `<q>` – Citazioni brevi _inline_**

È la versione “in linea” di una citazione.  
Ideale per frasi brevi riportate all’interno del testo.

### **Esempio**

```html
<p>
  Mia nonna diceva sempre <q>non ci sono più le mezze stagioni</q>.
</p>
```

### Caratteristiche

- È un **elemento inline**, quindi non va a capo.
    
- Il browser aggiunge **automaticamente le virgolette**.
    
- Perfetto per riprendere frasi brevi pronunciate da qualcuno.
    

---

## **5. `<cite>` – Citare opere, autori, enti**

Questa è la parte che confonde di più.

**`<cite>` NON serve per citare una frase.  
Serve a citare la _fonte_ (opera, autore, ente, libro).**

### Esempio standard

```html
<p>
  Per approfondire l’argomento, si veda <cite>Dipartimento di Ricerca</cite>.
</p>
```

Oppure:

```html
<p>
  Il romanzo <cite>I Promessi Sposi</cite> è una pietra miliare della letteratura italiana.
</p>
```

Oppure ancora, combinato con un link:

```html
<p>
  Per maggiori dettagli consultare 
  <a href="https://example.com"><cite>Dipartimento di Ricerca</cite></a>.
</p>
```

### Caratteristiche

- È un **elemento semantico** molto importante.
    
- Indica _l’opera_, non il contenuto della citazione.
    
- A differenza dell’attributo `cite`, questo **viene letto** dagli screen reader e ha valore semantico reale.
    

---

## **6. Confronto diretto tra i tre elementi**

|Elemento|Tipo|Uso corretto|Cosa fa visivamente|Accessibilità|
|---|---|---|---|---|
|**`<blockquote>`**|Block|Citazioni lunghe|Margine, indentazione|Ha senso semantico|
|**`<q>`**|Inline|Citazioni brevi|Aggiunge virgolette|Ha senso semantico|
|**`<cite>`**|Inline|Indicare la fonte (opera/autore)|Spesso italico|Letto come "titolo"|
|**Attributo `cite`**|Attributo|URL della fonte della citazione|Nessun effetto visivo|Attualmente quasi ignorato da SR|

---

## **7. Esempio completo che li usa tutti**

```html
<p>
  Nel suo discorso, l’autore affermò:
</p>

<blockquote cite="https://example.com/discorso">
  La tecnologia, se ben utilizzata, eleva l’essere umano, ma se fraintesa
  rischia di incatenarlo alle sue stesse creazioni.
</blockquote>

<p>
  Questa frase ricorda molto ciò che si trova in 
  <cite>Etica e Tecnologia Moderna</cite>.
</p>

<p>
  Come direbbe qualcuno: <q>La semplicità è la massima sofisticazione</q>.
</p>
```

---

## **8. Conclusione**

Hai ora un quadro **perfetto e completo** dei tre elementi dedicati alle citazioni:

- `<blockquote>` per blocchi lunghi,
    
- `<q>` per citazioni brevi inline,
    
- `<cite>` per indicare la fonte,
    
- attributo `cite` per riportare URL e metadati sulla provenienza della citazione.
    

Sono elementi spesso sottovalutati, ma cruciali per scrivere HTML professionale, semantico e accessibile.