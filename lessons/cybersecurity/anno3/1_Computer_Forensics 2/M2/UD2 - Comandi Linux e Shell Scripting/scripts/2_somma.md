## ✅ Script pulito

```bash
#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Errore: Devi fornire esattamente due numeri come argomenti."
    echo "Uso: $0 numero1 numero2"
    exit 1
fi

re='^-?[0-9]+$'

if ! [[ $1 =~ $re ]] || ! [[ $2 =~ $re ]]; then
    echo "Errore: Entrambi gli argomenti devono essere numeri interi."
    exit 1
fi

somma=$(( $1 + $2 ))

echo "La somma di $1 e $2 è: $somma"
```

---

## 1. Cosa fa lo script

1. Controlla che tu abbia passato **esattamente due argomenti** (es. `./somma.sh 5 -3`).
2. Controlla che **entrambi** siano **numeri interi** (anche negativi).
3. Se qualcosa non va, stampa un messaggio di errore ed esce con codice `1`.
4. Se tutto è ok, **somma i due numeri** e stampa il risultato.

---

## 2. Shebang

```bash
#!/bin/bash
```

Dice al sistema:

> “Per eseguire questo file, usa l’interprete `/bin/bash`”.

---

## 3. Controllo del numero di argomenti

```bash
if [ "$#" -ne 2 ]; then
    echo "Errore: Devi fornire esattamente due numeri come argomenti."
    echo "Uso: $0 numero1 numero2"
    exit 1
fi
```

* `$#` = **numero di argomenti** passati allo script.
* `-ne` = **not equal** (confronto numerico).
* `$0` = nome dello script (utile per stampare la sintassi d’uso).

Quindi:

* `if [ "$#" -ne 2 ]; then`
  → “se il numero di argomenti è **diverso da 2**, allora…”

ATTENZIONE, per `[ ... ]` **gli spazi sono obbligatori**, perché:

* `[` è in realtà un **comando** (alias di `test`),
* ha bisogno che gli argomenti siano separati da spazi,
* l’ultima `]` è vista come **argomento letterale**.

Quindi:

* ✅ corretto:
  `if [ "$#" -ne 2 ]; then`
* ❌ sbagliato:
  `if ["$#"-ne2]; then`

Con `[[ ... ]]` Bash è più tollerante, ma è comunque buona norma separare con spazi.

---

## 4. La regex: `re='^-?[0-9]+$'`

```bash
re='^-?[0-9]+$'
```

Questa è una **espressione regolare** che riconosce **numeri interi** (anche negativi).
La leggiamo pezzo per pezzo:

* `^`
  → **ancora di inizio stringa**
  Significa: “la corrispondenza deve cominciare dall’inizio, non in mezzo”.

* `-?`
  → un **meno opzionale**

  * `-` è il segno meno.
  * `?` significa “0 o 1 volte”.

  Quindi:

  * accetta `-5`
  * accetta `5`
  * non obbliga il `-`.

* `[0-9]+`
  → una o più cifre tra `0` e `9`

  * `[0-9]` = “un carattere qualsiasi tra 0 e 9”
  * `+` = “una o più volte”.

  Esempi che vanno bene:

  * `0`
  * `7`
  * `1234`
  * `987654`

* `$`
  → **ancora di fine stringa**
  Significa: “la corrispondenza deve arrivare fino alla fine, non oltre”.

### Quindi, cosa accetta esattamente?

* ✅ `0`
* ✅ `42`
* ✅ `-1`
* ✅ `-987654`
* ❌ `3.14` (c’è un punto)
* ❌ `12a` (c’è una lettera)
* ❌ `+5` (non hai previsto il `+`)
* ❌ `--5` (due meno di fila)

È una regex per **interi decimali con segno opzionale negativo**.

---

## 5. Il controllo con `[[ $1 =~ $re ]]`

```bash
if ! [[ $1 =~ $re ]] || ! [[ $2 =~ $re ]]; then
    echo "Errore: Entrambi gli argomenti devono essere numeri interi."
    exit 1
fi
```

Qui usi alcune cose fondamentali:

* `[[ ... ]]`
  → costrutto **test esteso** di Bash (più potente e sicuro di `[ ... ]`):

  * supporta direttamente le **regex** con `=~`
  * non espande i wildcard allo stesso modo di `[ ]`
  * è più robusto a certi casi limite.

* `=~`
  → operatore “**match di espressione regolare**”.
  Significa: “la stringa a sinistra **corrisponde** al pattern regex a destra?”.

Quindi:

```bash
[[ $1 =~ $re ]]
```

vuol dire:

> “Il primo argomento (`$1`) corrisponde alla regex memorizzata nella variabile `re`?”

### Negazione e OR logico

```bash
if ! [[ $1 =~ $re ]] || ! [[ $2 =~ $re ]]; then
```

Leggiamola come logica booleana:

* `!` = **NOT**
* `||` = **OR**

Quindi la frase diventa:

> Se **NON** (il primo argomento è un numero intero) **oppure** **NON** (il secondo argomento è un numero intero)
> → allora errore.

In altre parole:

> “Se almeno uno dei due non è un intero valido, fermati”.

---

## 6. La somma aritmetica

```bash
somma=$(( $1 + $2 ))
```

* `$(( ... ))` = **sostituzione aritmetica** in Bash.
* Dentro `(( ))` Bash tratta `$1` e `$2` come numeri, non come stringhe.
* Il risultato dell’operazione viene assegnato alla variabile `somma`.

---

## 7. La stampa finale

```bash
echo "La somma di $1 e $2 è: $somma"
```

Qui è solo interpolazione di variabili:

* `$1` → primo argomento
* `$2` → secondo argomento
* `$somma` → risultato calcolato

---

## 8. Exit code

Ci sono due tipi di `exit`:

* `exit 1` → termine con **errore** (codice non zero)
* `exit 0` (o niente explicit) → termine “ok”

Questo serve moltissimo quando, in futuro, userai questo script **dentro altri script** e vorrai sapere se è andato tutto bene.
