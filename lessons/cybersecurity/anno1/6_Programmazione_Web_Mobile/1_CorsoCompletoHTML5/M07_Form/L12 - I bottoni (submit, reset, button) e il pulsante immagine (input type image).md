## **Lezione 12: I bottoni (`submit`, `reset`, `button`) e il pulsante immagine (`input type="image"`)**

---

### **1. Introduzione**

I bottoni sono fondamentali nei form.  
In HTML abbiamo **3 tipi principali** di pulsanti, più un quarto tipo “storico” che serve solo se vogliamo usare un’immagine al posto del pulsante.

Sono:

1. `type="submit"`
    
2. `type="reset"`
    
3. `type="button"`
    
4. `type="image"` — usato rarissimamente, ma utile da conoscere
    

Ognuno ha un comportamento completamente diverso, e capirli è importante perché nel mondo reale i bottoni sono il motore delle interazioni.

---

## **2. `type="submit"` – invia il form**

È il bottone più usato:

```html
<input type="submit" value="Invia">
```

Oppure con `<button>`:

```html
<button type="submit">Invia</button>
```

**Effetto:**  
Invia il form al server, con tutti i dati compilati.

Se un form non ha JavaScript, il `submit` è l’unico modo per “spedire tutto”.

---

## **3. `type="reset"` – ripristina i valori iniziali del form**

```html
<input type="reset" value="Reimposta">
```

Oppure:

```html
<button type="reset">Reset</button>
```

**Effetto:**  
Cancella tutto → riporta ogni campo al valore originale caricamento-pagina.

Utile quando:

- si modificano tanti campi
    
- si vuole tornare allo stato iniziale
    
- l’utente ha sbagliato inserimento
    

Non invia niente: _ripulisce_ e basta.

---

## **4. `type="button"` – un bottone “vuoto”, che fa qualcosa SOLO con JavaScript**

Questo bottone **di per sé non fa niente**.

```html
<button type="button" id="btn">Cliccami</button>
```

Se premi, non invia, non resetta, non cambia nulla.

Serve quando _siamo noi_ a definire il comportamento con JavaScript:

```html
<script>
  const btn = document.getElementById("btn");
  btn.onclick = function () {
    alert("Hai cliccato il bottone!");
  };
</script>
```

Quindi:

- submit = invia
    
- reset = ripulisce
    
- button = lo gestisci TU in JS
    

È fondamentale capire questo: **il 99% delle interazioni complesse sul web nasce da un `<button type="button">` collegato a JavaScript.**

---

## **5. `input type="image"` – un “submit” in forma di immagine**

È un tipo particolare di button:

```html
<input type="image" src="immagini/icona.png" alt="Invia">
```

Comportamento:

- **fa esattamente la stessa cosa di un submit**
    
- ma al posto del testo c’è un’immagine
    

Il browser mostra l’immagine come pulsante cliccabile.

### **Esempio**

```html
<form>
  <input type="image" src="/img/icona.png" alt="Invia">
</form>
```

Se premi, il form parte.

**Perché esiste?**  
Perché prima del CSS non c’era altro modo per avere bottoni “carini”.

**Perché non si usa più?**  
Perché oggi si fanno pulsanti estetici molto migliori con:

- CSS
    
- `background-image`
    
- `<button>` personalizzati
    
- SVG
    

Risultato: `type="image"` è praticamente morto, ma tecnicamente fa parte dello standard e va conosciuto.

---

## **6. Differenza tra `<input>` e `<button>`**

Entrambi funzionano:

```html
<input type="submit" value="Invia">
<button type="submit">Invia</button>
```

Ma `<button>` è più flessibile:

- può contenere HTML interno (icone, span, immagini…)
    
- può avere struttura complessa
    
- è più facile da stilare con CSS
    

In progetti moderni si usa quasi sempre:

```html
<button type="submit">Invia</button>
```

---

## **7. Riepilogo completo**

|Tipo|Funzione|Esempio|Cosa fa|
|---|---|---|---|
|`submit`|invia il form|`<button type="submit">`|spedisce i dati|
|`reset`|pulisce il form|`<button type="reset">`|ripristina i valori iniziali|
|`button`|pulsante personalizzato|`<button type="button">`|fa SOLO ciò che dici in JS|
|`image`|submit grafico|`<input type="image">`|submit “con immagine”|

---

## **8. Conclusione**

A questo punto:

- sai creare i tre bottoni fondamentali
    
- sai la differenza tra comportamento “automatico” e comportamento gestito da JavaScript
    
- conosci anche `type="image"`, utile solo per completezza
    

Nella prossima lezione analizziamo **la textarea**, il campo che ci permette di inserire testo multi-linea.