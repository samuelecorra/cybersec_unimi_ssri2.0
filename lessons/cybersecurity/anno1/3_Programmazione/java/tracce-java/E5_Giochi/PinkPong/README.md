# README 📃

# Pink Pong 🏓 - CyberSamu Edition

---

## 1. Package e import

```java
package PinkPongV1;

```

* `package PinkPongV1;`
  Indica che tutte le classi del file appartengono al **package** `PinkPongV1`.
  Serve per organizzare il codice in “cartelle logiche” (namespace) e per evitare conflitti di nomi tra classi con lo stesso nome in progetti diversi.

* `import javax.swing.*;`
  Importa tutte le classi del package `javax.swing` (l’asterisco vuol dire “tutto”):

    * `JFrame`, `JPanel`, `SwingUtilities`, `Timer`, `JComponent`, ecc.
      Sono le classi base della **GUI Swing**: finestre, pannelli, pulsanti, timer, ecc.

* `import java.awt.*;`
  Importa tutte le classi di `java.awt` (“Abstract Window Toolkit”):

    * `Graphics`, `Graphics2D`, `Color`, `Font`, `FontMetrics`, `Dimension`, `BasicStroke`, `Stroke`…
      Sono gli strumenti per disegnare 2D, gestire colori, font, dimensioni.

* `import java.awt.event.*;`
  Importa le classi relative agli **eventi AWT**:

    * `ActionListener`, `KeyListener`, `KeyEvent`, `ActionEvent`, ecc.
      Queste servono per reagire a: tasti premuti, click, scadenza del Timer.

---

## 2. Classe `PongGame` (entry point)

```java
public class PongGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame finestra = new JFrame("Pink Pong - CyberSamu Edition");
            PannelloPong pannello = new PannelloPong();

            finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            finestra.setResizable(false);
            finestra.setContentPane(pannello);
            finestra.pack();
            finestra.setLocationRelativeTo(null);   // centra la finestra sullo schermo
            finestra.setVisible(true);

            pannello.requestFocusInWindow();        // serve per ricevere gli input da tastiera
        });
    }
}
```

* `public class PongGame { ... }`
  Classe pubblica che contiene il `main`, quindi è il **punto di ingresso** del programma.

* `public static void main(String[] args)`
  Metodo statico chiamato dalla JVM all’avvio.

* `SwingUtilities.invokeLater(() -> { ... });`
  `SwingUtilities.invokeLater` prende un `Runnable` (qui espresso come **lambda** `() -> {}`) e lo esegue sul **thread di dispatch degli eventi Swing** (Event Dispatch Thread, EDT).
  Per Swing è buona pratica creare e modificare componenti grafici sempre sull’EDT, per evitare problemi di concorrenza.

Dentro la lambda:

1. `JFrame finestra = new JFrame("Pink Pong - CyberSamu Edition");`

    * Crea la finestra principale (`JFrame`), con il titolo mostrato nella barra superiore.

2. `PannelloPong pannello = new PannelloPong();`

    * Crea un’istanza del nostro pannello personalizzato, dove avviene tutto il disegno e la logica di gioco.

3. `finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);`

    * Dice al frame: quando l’utente chiude la X, termina il programma (chiude il processo Java).

4. `finestra.setResizable(false);`

    * Impedisce all’utente di ridimensionare la finestra. È importante perché le nostre coordinate di gioco assumono una dimensione fissa (800×600).

5. `finestra.setContentPane(pannello);`

    * Imposta il contenuto della finestra: al posto del pannello predefinito, usiamo il nostro `PannelloPong`.

6. `finestra.pack();`

    * Dice al frame di “adattarsi” alla `PreferredSize` del content pane (il pannello):
      userà quella dimensione per calcolare la size definitiva.

7. `finestra.setLocationRelativeTo(null);`

    * Posiziona la finestra al centro dello schermo.
      `null` vuol dire “centra rispetto all’intero schermo”.

8. `finestra.setVisible(true);`

    * Mostra effettivamente la finestra: finché non chiami `setVisible(true)`, non vedi nulla.

9. `pannello.requestFocusInWindow();`

    * Chiede il **focus di tastiera** per il pannello, così i KeyEvent arrivano a lui.
    * Senza focus, premere i tasti non scatena i metodi del `KeyListener`.

---

## 3. Enum `StatoGioco`

```java
enum StatoGioco {
    MENU,                // schermata iniziale
    CONTO_ALLA_ROVESCIA, // 3, 2, 1 prima che la pallina parta
    IN_GIOCO,            // partita attiva
    IN_PAUSA             // overlay di pausa
}
```

* È un `enum`, quindi un tipo speciale che definisce un **insieme finito di costanti**.
* Qui rappresenta i **4 stati possibili** della finite state machine del gioco:

    * `MENU`
    * `CONTO_ALLA_ROVESCIA`
    * `IN_GIOCO`
    * `IN_PAUSA`
* Lo useremo nello `switch (stato)` per decidere cosa aggiornare/disegnare.

---

## 4. Enum `Difficolta`

```java
enum Difficolta {
    FACILE(4, "Facile"),
    NORMALE(6, "Normale"),
    DIFFICILE(8, "Difficile");

    final int velocitaPallina;
    final String etichetta;

    Difficolta(int velocitaPallina, String etichetta) {
        this.velocitaPallina = velocitaPallina;
        this.etichetta = etichetta;
    }
}
```

* Ancora un `enum`, ma con **campi associati**:

    * ogni costante (`FACILE`, `NORMALE`, `DIFFICILE`) ha:

        * `velocitaPallina` → un `int` che indica la velocità di base della pallina per quella difficoltà;
        * `etichetta` → la stringa da mostrare nel menu (“Facile”, “Normale”, “Difficile”).

* Le righe `FACILE(4, "Facile")` ecc. sono chiamate al **costruttore** dell’enum:

    * `Difficolta(int velocitaPallina, String etichetta) { ... }`
    * Dentro il costruttore:
      `this.velocitaPallina = velocitaPallina;` assegna il parametro al campo dell’istanza;
      `this.etichetta = etichetta;` idem.

* `final` sui campi significa che, una volta costruita la costante enum, i valori non possono più cambiare (sono costanti).

---

## 5. Classe `PannelloPong` – campi e costruttore

```java
class PannelloPong extends JPanel implements ActionListener, KeyListener {
```

* Estende `JPanel`: diventa un **componente grafico 2D** su cui possiamo disegnare ridefinendo `paintComponent`.
* Implementa `ActionListener` → può ricevere eventi di tipo `ActionEvent` (usati dal `Timer` Swing).
* Implementa `KeyListener` → può ricevere eventi da tastiera.

### 5.1 Costanti di dimensione e velocità

```java
private static final int LARGHEZZA = 800;
private static final int ALTEZZA = 600;
```

* `static final` = variabili di classe costanti → valori fissi per tutto il programma.
* Dimensione del campo di gioco in pixel.

```java
private static final int LARGHEZZA_PALETTA = 10;
private static final int ALTEZZA_PALETTA = 100;
private static final int VELOCITA_PALETTA_GIOCATORE = 7;
```

* Dimensioni delle palette e velocità di movimento verticale della paletta del giocatore (in pixel per frame).

### 5.2 Coordinate delle palette

```java
private int xPalettaSinistra = 30;
private int yPalettaSinistra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;

private int xPalettaDestra = LARGHEZZA - 40;
private int yPalettaDestra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;
```

* Palette sinistra:

    * `xPalettaSinistra = 30;` → 30 px dal bordo sinistro.
    * `yPalettaSinistra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;`

        * `ALTEZZA / 2` è la metà verticale (centro dello schermo);
        * `ALTEZZA_PALETTA / 2` sottratto serve a **centrarla verticalmente**.

* Palette destra:

    * `xPalettaDestra = LARGHEZZA - 40;`

        * spostata 40 px dal bordo destro (`LARGHEZZA` è la coordinata x del bordo destro).
    * Stessa formula di y per centrarla.

### 5.3 Pallina

```java
private static final int DIMENSIONE_PALLINA = 20;
private int xPallina = LARGHEZZA / 2 - DIMENSIONE_PALLINA / 2;
private int yPallina = ALTEZZA / 2 - DIMENSIONE_PALLINA / 2;
private int velXPallina = 5;
private int velYPallina = 5;
```

* Dimensione della pallina (quadrata 20×20 px).
* Posizione iniziale: al centro dello schermo (stessa logica delle palette).
* `velXPallina` e `velYPallina` sono le velocità iniziali in pixel per frame.

    * Positivo su X → si muove a destra.
    * Negativo su X → a sinistra.
    * Positivo su Y → verso il basso (sistema di coordinate grafico: origine in alto a sinistra).
    * Negativo su Y → verso l’alto.

### 5.4 Stato dei tasti

```java
private boolean frecciaSuPremuta = false;
private boolean frecciaGiuPremuta = false;
```

* Flag che indicano se la freccia su/giù è **attualmente premuta**.
* Vengono aggiornati nei metodi `keyPressed` e `keyReleased` e letti in `aggiornaPalettaGiocatore()`.

### 5.5 Punteggi e record

```java
private int punteggioSinistra = 0;  // IA
private int punteggioDestra = 0;    // Giocatore
private int migliorPunteggio = 0;   // miglior punteggio singolo registrato finora
private String detentoreRecord = "Nessuno"; // chi detiene il record attuale
```

* Punteggi delle due parti:

    * sinistra = IA;
    * destra = giocatore.
* `migliorPunteggio` → il massimo tra tutti i punteggi (IA o giocatore) ottenuti nella “vita” del programma.
* `detentoreRecord` → stringa `"Giocatore"` oppure `"IA"` (o `"Nessuno"` se nessuno ha ancora segnato).

### 5.6 Stato globale

```java
private StatoGioco stato = StatoGioco.MENU;
private Difficolta difficolta = Difficolta.NORMALE;
```

* `stato` → stato corrente della FSM (menu, countdown, ecc.).
* `difficolta` → difficoltà corrente selezionata nel menu.

### 5.7 Countdown e timer

```java
private int fotogrammiCountdown = 0;
private final Timer timerGioco;
```

* Il countdown viene gestito in **frame**:
  3 secondi a 60 fps ≈ 3 * 60 = 180 frame.
* `Timer timerGioco` è il `javax.swing.Timer` che chiama `actionPerformed` ogni 1/60 di secondo.

### 5.8 Colori

```java
private static final Color COLORE_SFONDO = new Color(30, 30, 30);
...
```

* Vari oggetti `Color` per palette, pallina, testo, overlay.
* `new Color(r, g, b)` usa valori da 0 a 255 per Rosso, Verde, Blu.
* L’overlay ha anche **alpha** (trasparenza) nella riga:

  ```java
  private static final Color COLORE_OVERLAY = new Color(0, 0, 0, 150);
  ```

  qui l’ultimo parametro è l’alpha (0 = trasparente, 255 = opaco).

### 5.9 Costruttore

```java
public PannelloPong() {
    setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
    setBackground(COLORE_SFONDO);

    setFocusable(true);
    addKeyListener(this); // registriamo il pannello per ricevere eventi da tastiera

    timerGioco = new Timer(1000 / 60, this); // 60 chiamate al secondo di actionPerformed
    timerGioco.start();
}
```

* `setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));`:

    * `Dimension` è AWT: coppia (larghezza, altezza).
    * Dice a Swing: “vorrei avere questa size”.
      `frame.pack()` userà questa informazione.

* `setBackground(COLORE_SFONDO);`:

    * colore di background del pannello: viene usato da `super.paintComponent(g)` per riempire lo sfondo.

* `setFocusable(true);`:

    * permette al pannello di **poter ottenere il focus**.

* `addKeyListener(this);`:

    * registra il pannello come ascoltatore di eventi di tastiera.

* `timerGioco = new Timer(1000 / 60, this);`:

    * `new Timer(delayMs, listener)` crea un timer con:

        * `delayMs = 1000 / 60 ≈ 16` → ~ 60 volte al secondo;
        * `listener = this` → quindi ogni tick chiamerà `actionPerformed` della classe.

* `timerGioco.start();`:

    * avvia il timer.

---

## 6. Disegno: `paintComponent` e funzioni di rendering

### 6.1 `paintComponent`

```java
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

    if (stato == StatoGioco.MENU) {
        disegnaMenu(g2d);
        return;
    }

    disegnaCampo(g2d);
    disegnaPunteggio(g2d);

    if (stato == StatoGioco.IN_PAUSA) {
        disegnaOverlayPausa(g2d);
    } else if (stato == StatoGioco.CONTO_ALLA_ROVESCIA) {
        disegnaOverlayCountdown(g2d);
    }
}
```

* `super.paintComponent(g);`
  Chiama l’implementazione padre (`JPanel`):

    * cancella l’area del pannello usando il colore di background.

* `Graphics2D g2d = (Graphics2D) g;`
  **Cast** a `Graphics2D`, versione più potente (rotazioni, anti aliasing, stroke personalizzati…).

* `setRenderingHint(...)`

    * `RenderingHints.KEY_ANTIALIASING` e `VALUE_ANTIALIAS_ON` attivano l’**anti aliasing** per linee e forme: i bordi vengono smussati.

* FSM di disegno:

    * Se `stato == MENU` → disegna solo il menu e `return`.
    * Altrimenti:

        * `disegnaCampo(g2d);` → sfondo del campo, linea centrale, palette, pallina.
        * `disegnaPunteggio(g2d);` → punteggio + record.
        * overlay:

            * se `IN_PAUSA` → disegna overlay pausa;
            * se `CONTO_ALLA_ROVESCIA` → overlay conto alla rovescia.

### 6.2 `disegnaCampo`

Usa primitive di disegno base:

* `setColor(COLORE_LINEA_CAMPO);`
* `setStroke(new BasicStroke(...))`:

    * `BasicStroke` definisce spessore, stile delle linee.
    * Qui usi un **pattern tratteggiato** (`float[] tratteggio = {10f, 10f};`):

        * 10 pixel disegnati, 10 vuoti.
* `drawLine(LARGHEZZA / 2, 0, LARGHEZZA / 2, ALTEZZA);`
  Disegna la linea centrale.

Per le palette:

```java
g2d.fillRect(xPalettaSinistra, yPalettaSinistra,
             LARGHEZZA_PALETTA, ALTEZZA_PALETTA);
```

* `fillRect(x, y, w, h)` riempie un rettangolo pieno.
* Analogamente per `xPalettaDestra`.

Per la pallina:

```java
g2d.fillOval(xPallina, yPallina, DIMENSIONE_PALLINA, DIMENSIONE_PALLINA);
```

* Ovale pieno inscritto nel rettangolo `x,y,w,h`.
* Se w = h → cerchio.

### 6.3 `disegnaPunteggio`

```java
g2d.setFont(new Font("Consolas", Font.BOLD, 32));
...
String testoPunteggio = punteggioSinistra + "   :   " + punteggioDestra;
int larghezzaTesto = g2d.getFontMetrics().stringWidth(testoPunteggio);
g2d.drawString(testoPunteggio, LARGHEZZA / 2 - larghezzaTesto / 2, 50);
```

* `new Font("Consolas", Font.BOLD, 32)` crea un font monospace, grassetto, 32pt.
* `g2d.getFontMetrics()` restituisce un `FontMetrics` che sa:

    * larghezza in pixel di una stringa (`stringWidth`);
    * altezza del font, ascendente, discendente etc.
* Per centrare:

    * `stringWidth` → larghezza del testo;
    * `LARGHEZZA / 2 - larghezzaTesto / 2` → coord x del margine sinistro affinché il testo sia centrato.

Per il record:

```java
String best = "Record: " + migliorPunteggio + " (" + detentoreRecord + ")";
g2d.drawString(best, 20, 30);
```

* Operazioni sulle stringhe:

    * `"Record: " + migliorPunteggio` → concatena la stringa con l’int (viene convertito in stringa automaticamente).
    * `+ " (" + detentoreRecord + ")"` → aggiunge parentesi e nome del detentore.

### 6.4 `disegnaMenu`

Qui c’è:

* pulizia sfondo con `fillRect(0,0,LARGHEZZA,ALTEZZA)` e colore sfondo;
* titolo centrato, usando di nuovo `stringWidth` come sopra;
* record centrato;
* riga “Difficoltà: [Normale] …” centrata **come blocco unico**:

    * prima si calcola `larghezzaTotale` sommando larghezze di:

        * etichetta “Difficoltà: ”;
        * stringhe delle singole difficoltà (squadrate con `[...]` se selezionate);
        * spaziatura tra le voci (`spaziatura`).
    * poi si calcola `xInizio = LARGHEZZA / 2 - larghezzaTotale / 2`.
    * si disegnano i pezzi in sequenza da `xCorrente`.

I testi delle istruzioni sono centrati allo stesso modo, ma ciascuno **individualmente**:

```java
int w1 = g2d.getFontMetrics().stringWidth(riga1);
g2d.drawString(riga1, LARGHEZZA / 2 - w1 / 2, yIstruzioni);
```

---

## 7. Overlay pausa e countdown

### 7.1 Pausa

```java
g2d.setColor(COLORE_OVERLAY);
g2d.fillRect(0, 0, LARGHEZZA, ALTEZZA);
```

* Rettangolo nero semi-trasparente: “oscura” il campo ma lo lascia intravedere sotto.

Poi testo centrale (“PAUSA”) e le righe “R o P per riprendere…” e “Q o ESC per tornare al menu”, sempre centrati tramite `stringWidth`.

### 7.2 Countdown

```java
int secondiRimasti = fotogrammiCountdown / 60 + 1;
```

* Se ad es. `fotogrammiCountdown` è tra 120 e 179, integer division `/ 60` dà 2, +1 → 3;
  così ottieni 3,2,1 a scendere.

Il resto è identico: overlay + numero gigante centrato.

---

## 8. Game loop: `actionPerformed`

```java
@Override
public void actionPerformed(ActionEvent e) {
    switch (stato) {
        case IN_GIOCO -> {
            aggiornaPalette();  // IA + giocatore
            aggiornaPallina();
        }
        case CONTO_ALLA_ROVESCIA -> {
            aggiornaPalette();
            if (fotogrammiCountdown > 0) {
                fotogrammiCountdown--;
            }
            if (fotogrammiCountdown <= 0) {
                stato = StatoGioco.IN_GIOCO;
            }
        }
        case MENU, IN_PAUSA -> {
            // Nessun aggiornamento della logica
        }
    }

    repaint();
}
```

* Viene chiamato dal `Timer` ogni ~16 ms.
* FSM:

    * `IN_GIOCO` → aggiorna palette e pallina.
    * `CONTO_ALLA_ROVESCIA` → palette sì (puoi già muoverti), pallina no; countdown diminuisce; se arriva a 0, passi in `IN_GIOCO`.
    * `MENU` e `IN_PAUSA` → niente logica di movimento.
* `repaint();`:

    * chiede a Swing di ridisegnare il pannello → chiamerà `paintComponent`.

---

## 9. Aggiornamento delle palette

### 9.1 `aggiornaPalette`

```java
private void aggiornaPalette() {
    aggiornaPalettaIA();
    aggiornaPalettaGiocatore();
}
```

* “dispatcher” che chiama i due metodi separati (IA e giocatore).

### 9.2 IA: `aggiornaPalettaIA`

Questa è la parte di “pseudo-AI”.

1. Calcolo dei centri:

   ```java
   int centroPaletta = yPalettaSinistra + ALTEZZA_PALETTA / 2;
   int centroPallina = yPallina + DIMENSIONE_PALLINA / 2;
   ```

   Per confrontare posizione verticale pallina/paletta, lavoriamo con i centri.

2. Direzione della pallina:

   ```java
   boolean pallinaVersoIA = velXPallina < 0;
   ```

    * Se velX < 0 → la pallina si muove verso sinistra.

3. Parametri per difficoltà:

   ```java
   int velocitaIA;
   double probabilitaErrore;
   double reattivita;
   ```

    * `velocitaIA` → quanti pixel si muove per frame.
    * `probabilitaErrore` → probabilità che in questo frame l’IA *non faccia proprio nulla*.
    * `reattivita` → “probabilità di reagire” al frame; se è 0.5, la metà delle volte non fa nulla.

   Lo `switch (difficolta)` assegna valori diversi per FACILE/NORMALE/DIFFICILE, come bilanciamento.

4. Limiti logici:

   ```java
   if (difficolta != Difficolta.DIFFICILE && !pallinaVersoIA) {
       return;
   }
   ```

    * In FACILE/NORMALE l’IA si muove **solo se la pallina va verso di lei**.
      Se la pallina sta andando a destra, l’IA sta ferma.

5. Errori casuali:

   ```java
   if (Math.random() < probabilitaErrore) {
       return;
   }

   if (Math.random() > reattivita) {
       return;
   }
   ```

    * `Math.random()` restituisce un double in [0,1).
    * Confrontandolo con `probabilitaErrore` / `reattivita` introduci una decisione casuale.

6. Controllo allineamento:

   ```java
   int tolleranza = 10;

   if (centroPallina > centroPaletta + tolleranza) {
       yPalettaSinistra += velocitaIA;
   } else if (centroPallina < centroPaletta - tolleranza) {
       yPalettaSinistra -= velocitaIA;
   }
   ```

    * Se la pallina è almeno 10px sotto il centro della paletta → scendi.
    * Se è almeno 10px sopra → sali.
    * Se è “quasi” allineata (entro 10 px) → non muovere (zona morta).

7. Clamping ai bordi:

   ```java
   if (yPalettaSinistra < 0) yPalettaSinistra = 0;
   if (yPalettaSinistra + ALTEZZA_PALETTA > ALTEZZA) {
       yPalettaSinistra = ALTEZZA - ALTEZZA_PALETTA;
   }
   ```

    * Evita che la paletta esc a fuori dallo schermo.

### 9.3 Giocatore: `aggiornaPalettaGiocatore`

* Legge i flag `frecciaSuPremuta` / `frecciaGiuPremuta`:

  ```java
  if (frecciaSuPremuta)  yPalettaDestra -= VELOCITA_PALETTA_GIOCATORE;
  if (frecciaGiuPremuta) yPalettaDestra += VELOCITA_PALETTA_GIOCATORE;
  ```

* Poi stesso clamping ai bordi.

---

## 10. Aggiornamento pallina: `aggiornaPallina`

### 10.1 Movimento base

```java
xPallina += velXPallina;
yPallina += velYPallina;
```

* Classico motore fisico super semplificato: posizione += velocità.

### 10.2 Collisioni con bordi alto/basso

```java
if (yPallina <= 0 || yPallina + DIMENSIONE_PALLINA >= ALTEZZA) {
    velYPallina = -velYPallina;
}
```

* Se la pallina tocca la riga y=0 (alto) o la riga y=ALTEZZA-DIMENSIONE (basso) → inverti la velocità verticale.

### 10.3 Collisione con palette sinistra

```java
if (xPallina <= xPalettaSinistra + LARGHEZZA_PALETTA &&
        xPallina >= xPalettaSinistra &&
        yPallina + DIMENSIONE_PALLINA >= yPalettaSinistra &&
        yPallina <= yPalettaSinistra + ALTEZZA_PALETTA) {

    velXPallina = Math.abs(velXPallina);
}
```

* Condizione di **rettangoli che si sovrappongono**:

    * `xPallina` (margine sinistro della pallina) è compreso tra:

        * `xPalettaSinistra` (margine sinistro della paletta)
        * e `xPalettaSinistra + LARGHEZZA_PALETTA` (margine destro).
    * E l’intervallo verticale della pallina interseca quello della paletta:

        * `yPallina + DIMENSIONE_PALLINA >= yPalettaSinistra` (il bordo basso della pallina è sotto il bordo alto della paletta)
        * `yPallina <= yPalettaSinistra + ALTEZZA_PALETTA` (il bordo alto della pallina è sopra il bordo basso della paletta).

* `velXPallina = Math.abs(velXPallina);`:

    * assicura che la velocità orizzontale diventi **positiva**, quindi va verso destra.

Stesso schema speculare per la paletta destra, ma:

```java
velXPallina = -Math.abs(velXPallina);
```

per assicurarsi che diventi negativa (verso sinistra).

### 10.4 Punti

IA segna:

```java
if (xPallina > LARGHEZZA) {
    punteggioSinistra++;
    aggiornaMigliorPunteggio();
    reimpostaPallina(false);   // pallina riparte verso sinistra (verso IA)
    avviaCountdown();
}
```

* Se l’intero quadrato della pallina è oltre il bordo destro (`x > LARGHEZZA`): punto IA.
* `punteggioSinistra++` → incrementa punteggio IA.
* `aggiornaMigliorPunteggio();` → aggiorna eventuale record in tempo reale.
* `reimpostaPallina(false);`:

    * la rimetti al centro e con velocità verso sinistra (perché `versoDestra=false`).
* `avviaCountdown();` → parti da 3-2-1 prima di riprendere la partita.

Giocatore segna:

```java
if (xPallina + DIMENSIONE_PALLINA < 0) {
    punteggioDestra++;
    aggiornaMigliorPunteggio();
    reimpostaPallina(true);
    avviaCountdown();
}
```

* `xPallina + DIMENSIONE_PALLINA < 0` → il bordo destro della pallina è passato il bordo sinistro dello schermo.

---

## 11. Logica di stato

### 11.1 Reimpostare posizioni

```java
private void reimpostaPosizioni() {
    yPalettaSinistra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;
    yPalettaDestra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;
    reimpostaPallina(true);
}
```

* Centra entrambe le palette e chiama `reimpostaPallina(true)`.

### 11.2 Reimpostare pallina

```java
private void reimpostaPallina(boolean versoDestra) {
    xPallina = LARGHEZZA / 2 - DIMENSIONE_PALLINA / 2;
    yPallina = ALTEZZA / 2 - DIMENSIONE_PALLINA / 2;

    int v = difficolta.velocitaPallina;
    velXPallina = versoDestra ? v : -v;
    velYPallina = (Math.random() < 0.5) ? v : -v;
}
```

* Posizione ↦ centro.
* Velocità orizzontale:

    * se `versoDestra` è `true` → velXPallina = v;
    * altrimenti = -v.
* Velocità verticale random:

    * `Math.random() < 0.5` → 50% di possibilità di essere positiva o negativa.

### 11.3 Avvio nuova partita

```java
private void avviaNuovaPartita() {
    aggiornaMigliorPunteggio();
    punteggioSinistra = 0;
    punteggioDestra = 0;

    reimpostaPosizioni();
    avviaCountdown();
}
```

* Chiude idealmente la partita precedente aggiornando il record (per sicurezza) e azzera i punteggi.

### 11.4 Countdown, pausa, ritorno al menu

Già visti in alto, ma in sintesi:

* `avviaCountdown()`:

    * `stato = CONTO_ALLA_ROVESCIA;`
    * `fotogrammiCountdown = 3 * 60;`.

* `mettiInPausa()`:

    * se stato è `IN_GIOCO` o `CONTO_ALLA_ROVESCIA` → porta a `IN_PAUSA`.

* `riprendiConCountdown()`:

    * se `IN_PAUSA` → richiama `avviaCountdown()`.

* `tornaAlMenu()`:

    * chiama `aggiornaMigliorPunteggio()`, azzera punteggi, `stato = MENU`.

### 11.5 Gestione record

```java
private void aggiornaMigliorPunteggio() {
    int punteggioIA = punteggioSinistra;
    int punteggioGiocatore = punteggioDestra;

    int punteggioMassimoPartita;
    String detentorePartita;

    if (punteggioGiocatore >= punteggioIA) {
        punteggioMassimoPartita = punteggioGiocatore;
        detentorePartita = "Giocatore";
    } else {
        punteggioMassimoPartita = punteggioIA;
        detentorePartita = "IA";
    }

    if (punteggioMassimoPartita > migliorPunteggio) {
        migliorPunteggio = punteggioMassimoPartita;
        detentoreRecord = detentorePartita;
    }
}
```

* Confronta punteggio IA e giocatore:

    * se `punteggioGiocatore >= punteggioIA` →
      il record di questa **partita** è del giocatore.
    * altrimenti dell’IA.
* Se questo record di partita è > del record globale precedente (`migliorPunteggio`) → lo aggiorna e aggiorna anche `detentoreRecord`.

---

## 12. Selezione difficoltà

```java
private void selezionaDifficoltaSuccessiva() {
    Difficolta[] valori = Difficolta.values();
    int indice = (difficolta.ordinal() + 1) % valori.length;
    difficolta = valori[indice];
}
```

* `Difficolta.values()` → array di tutte le costanti enum in ordine di dichiarazione.
* `difficolta.ordinal()` → indice della costante corrente.
* `(indice + 1) % valori.length` → incremento ciclico (FACILE → NORMALE → DIFFICILE → FACILE…).

Stessa idea in sottrazione per `selezionaDifficoltaPrecedente` (con `-1 + length` e modulo per non andare sotto zero).

---

## 13. Gestione tastiera (KeyListener)

### 13.1 `keyPressed`

```java
public void keyPressed(KeyEvent e) {
    int codice = e.getKeyCode();

    switch (stato) {
        case MENU -> { ... }
        case IN_GIOCO, CONTO_ALLA_ROVESCIA -> { ... }
        case IN_PAUSA -> { ... }
    }
}
```

* `KeyEvent` contiene:

    * `getKeyCode()` → codice intero che rappresenta il tasto (ad es. `KeyEvent.VK_UP`, `VK_ENTER`, …).

**Nel MENU:**

* `VK_UP` → `selezionaDifficoltaPrecedente();`
* `VK_DOWN` → `selezionaDifficoltaSuccessiva();`
* `VK_ENTER` → `avviaNuovaPartita();`

**In GIOCO / COUNTDOWN:**

* `VK_UP`/`VK_DOWN` → mettono a `true` i flag `frecciaSuPremuta`, `frecciaGiuPremuta` (il movimento avviene nel game loop).
* `VK_ESCAPE` o `VK_P` → `mettiInPausa();`

**In PAUSA:**

* `VK_R` o `VK_P` → `riprendiConCountdown();`
* `VK_Q` o `VK_ESCAPE` → `tornaAlMenu();`

### 13.2 `keyReleased`

```java
public void keyReleased(KeyEvent e) {
    int codice = e.getKeyCode();

    switch (codice) {
        case KeyEvent.VK_UP -> frecciaSuPremuta = false;
        case KeyEvent.VK_DOWN -> frecciaGiuPremuta = false;
    }
}
```

* Quando il tasto viene rilasciato, i flag tornano a `false` → la paletta smette di muoversi.

### 13.3 `keyTyped`

Non usato:

* questo evento è per “caratteri stampabili” (lettere, numeri), non serve per frecce / ESC / P.

---

## 14. Visione d’insieme: architettura logica

Mettiamo insieme i pezzi:

1. **Event loop Swing + Timer**

    * Swing gestisce eventi GUI (repaint, key press, ecc.) sull’EDT.
    * Il `Timer` genera un `ActionEvent` 60 volte al secondo → chiama `actionPerformed`.

2. **Finite State Machine (FSM)**

    * Lo stato `StatoGioco` controlla cosa succede in `actionPerformed` e in `paintComponent`:

        * `MENU` → nessun update logico, solo disegno del menu.
        * `CONTO_ALLA_ROVESCIA` → aggiorna palette e countdown, non la pallina.
        * `IN_GIOCO` → aggiorna tutto.
        * `IN_PAUSA` → blocca logica, mostra overlay.

3. **Input**

    * `KeyListener` imposta / resetta booleani di input.
    * Il game loop legge i booleani per muovere la paletta → separazione **input vs logica**.

4. **Fisica**

    * Pallina: posizione + velocità; collisioni con bordi e palette implementate come controlli di bounding boxes.
    * Nessuna fisica avanzata (niente spin, niente angolo variabile in base al punto di impatto), ma perfetta per l’esempio.

5. **UI 2D**

    * Disegno manuale 2D con `Graphics2D`:

        * background, rettangoli pieni, cerchi, linee, testo.
    * Allineamenti curati con `FontMetrics.stringWidth` per centrare stringhe orizzontalmente.
    * Overlay gestiti con rettangoli semi-trasparenti (RGBA con alpha < 255).

6. **Record**

    * Ogni volta che qualcuno segna → potenziale aggiornamento del record.
    * Il record vive finché il programma è in esecuzione (non salvato su file, quindi non persistente tra run).

---

Se vuoi, nel prossimo passo possiamo:

* estrarre alcune parti in **classi dedicate** (es. classe `Pallina`, `Paletta`, `Partita`),
* o aggiungere features tipo:

    * “primo a 10 vince” con schermata **Game Over**,
    * salvataggio del record su file,
    * variazione dell’angolo della pallina in base al punto in cui colpisce la paletta (un accenno di “fisica” in più).

Ma come prima versione didattica, quello che hai è già un **micro motore 2D completo** con stato, input event-driven, game loop, AI basilare e UI customizzata.
