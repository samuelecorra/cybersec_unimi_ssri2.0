package E5_Giochi.PinkPong;
2
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ===================== MAIN =====================

public class PongGame {

    static void main(String[] args) {
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

// ===================== ENUM STATO GIOCO =====================

enum StatoGioco {
    MENU,                // schermata iniziale
    CONTO_ALLA_ROVESCIA, // 3, 2, 1 prima che la pallina parta
    IN_GIOCO,            // partita attiva
    IN_PAUSA             // overlay di pausa
}

// ===================== ENUM DIFFICOLTA' =====================

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

// ===================== PANNELLO DI GIOCO =====================

class PannelloPong extends JPanel implements ActionListener, KeyListener {

    // Dimensioni finestra
    private static final int LARGHEZZA = 800;
    private static final int ALTEZZA = 600;

    // Dimensioni e velocità delle palette
    private static final int LARGHEZZA_PALETTA = 10;
    private static final int ALTEZZA_PALETTA = 100;
    private static final int VELOCITA_PALETTA_GIOCATORE = 7;

    // Coordinate palette sinistra (IA)
    private int xPalettaSinistra = 30;
    private int yPalettaSinistra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;

    // Coordinate palette destra (giocatore)
    private int xPalettaDestra = LARGHEZZA - 40;
    private int yPalettaDestra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;

    // Pallina
    private static final int DIMENSIONE_PALLINA = 20;
    private int xPallina = LARGHEZZA / 2 - DIMENSIONE_PALLINA / 2;
    private int yPallina = ALTEZZA / 2 - DIMENSIONE_PALLINA / 2;
    private int velXPallina = 5;
    private int velYPallina = 5;

    // Stato dei tasti premuti (solo giocatore a destra)
    private boolean frecciaSuPremuta = false;
    private boolean frecciaGiuPremuta = false;

    // Punteggi
    private int punteggioSinistra = 0;  // IA
    private int punteggioDestra = 0;    // Giocatore
    private int migliorPunteggio = 0;   // miglior punteggio singolo registrato finora
    private String detentoreRecord = "Nessuno"; // chi detiene il record attuale

    // Stato globale del gioco
    private StatoGioco stato = StatoGioco.MENU;
    private Difficolta difficolta = Difficolta.NORMALE;

    // Durata countdown in "frame" (3 secondi ~ 3 * 60)
    private int fotogrammiCountdown = 0;

    // Timer del game loop (60 FPS circa)
    private final Timer timerGioco;

    // Palette di colori (main grigio, accent rosa)
    private static final Color COLORE_SFONDO = new Color(30, 30, 30);
    private static final Color COLORE_LINEA_CAMPO = new Color(255, 192, 203);   // rosa chiaro
    private static final Color COLORE_PALETTA = new Color(255, 105, 180);       // hot pink
    private static final Color COLORE_PALLINA = new Color(255, 182, 193);       // rosa pallina
    private static final Color COLORE_PUNTEGGIO = new Color(255, 110, 199);
    private static final Color COLORE_TITOLO_MENU = new Color(255, 110, 199); // rosa brillante
    private static final Color COLORE_TESTO_MENU = new Color(240, 240, 240);
    private static final Color COLORE_OVERLAY = new Color(0, 0, 0, 150);

    public PannelloPong() {
        setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
        setBackground(COLORE_SFONDO);

        setFocusable(true);
        addKeyListener(this); // registriamo il pannello per ricevere eventi da tastiera

        timerGioco = new Timer(1000 / 60, this); // 60 chiamate al secondo di actionPerformed
        timerGioco.start();
    }

    // ===================== DISEGNO =====================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast per usare funzioni più avanzate di Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Se siamo nel menu, disegno solo il menu e ritorno
        if (stato == StatoGioco.MENU) {
            disegnaMenu(g2d);
            return;
        }

        // Campo da gioco e punteggio
        disegnaCampo(g2d);
        disegnaPunteggio(g2d);

        // Overlay in base allo stato
        if (stato == StatoGioco.IN_PAUSA) {
            disegnaOverlayPausa(g2d);
        } else if (stato == StatoGioco.CONTO_ALLA_ROVESCIA) {
            disegnaOverlayCountdown(g2d);
        }
    }

    // Disegna la linea centrale, le due palette e la pallina
    private void disegnaCampo(Graphics2D g2d) {
        // Linea tratteggiata al centro
        g2d.setColor(COLORE_LINEA_CAMPO);
        Stroke vecchioTratto = g2d.getStroke();
        float[] tratteggio = {10f, 10f};
        g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 0f,
                tratteggio, 0f));
        g2d.drawLine(LARGHEZZA / 2, 0, LARGHEZZA / 2, ALTEZZA);
        g2d.setStroke(vecchioTratto);

        // Palette
        g2d.setColor(COLORE_PALETTA);
        g2d.fillRect(xPalettaSinistra, yPalettaSinistra,
                LARGHEZZA_PALETTA, ALTEZZA_PALETTA);
        g2d.fillRect(xPalettaDestra, yPalettaDestra,
                LARGHEZZA_PALETTA, ALTEZZA_PALETTA);

        // Pallina
        g2d.setColor(COLORE_PALLINA);
        g2d.fillOval(xPallina, yPallina, DIMENSIONE_PALLINA, DIMENSIONE_PALLINA);
    }

    // Disegna punteggio e miglior punteggio
    private void disegnaPunteggio(Graphics2D g2d) {
        g2d.setFont(new Font("Consolas", Font.BOLD, 32));
        g2d.setColor(COLORE_PUNTEGGIO);

        String testoPunteggio = punteggioSinistra + "   :   " + punteggioDestra;
        int larghezzaTesto = g2d.getFontMetrics().stringWidth(testoPunteggio);
        g2d.drawString(testoPunteggio, LARGHEZZA / 2 - larghezzaTesto / 2, 50);

        // Record in alto a sinistra, con chi lo detiene
        g2d.setFont(new Font("Consolas", Font.PLAIN, 18));
        String best = "Record: " + migliorPunteggio + " (" + detentoreRecord + ")";
        g2d.drawString(best, 20, 30);
    }

    // Disegna il menu iniziale con difficoltà centrata
    private void disegnaMenu(Graphics2D g2d) {
        g2d.setColor(COLORE_SFONDO);
        g2d.fillRect(0, 0, LARGHEZZA, ALTEZZA);

        // Titolo
        g2d.setColor(COLORE_TITOLO_MENU);
        g2d.setFont(new Font("Segoe UI Emoji", Font.BOLD, 40));
        String titolo = "PINK PONG 🏓 - CyberSamu Edition";
        int larghezzaTitolo = g2d.getFontMetrics().stringWidth(titolo);
        g2d.drawString(titolo, LARGHEZZA / 2 - larghezzaTitolo / 2, ALTEZZA / 3);

        // Record
        g2d.setFont(new Font("Consolas", Font.PLAIN, 24));
        String best = "Record: " + migliorPunteggio + " (" + detentoreRecord + ")";
        int larghezzaBest = g2d.getFontMetrics().stringWidth(best);
        g2d.drawString(best, LARGHEZZA / 2 - larghezzaBest / 2, ALTEZZA / 3 + 50);

        // ---------- Riga difficoltà centrata ----------
        g2d.setFont(new Font("Consolas", Font.PLAIN, 24));
        FontMetrics fm = g2d.getFontMetrics();

        String etichettaDiff = "Difficoltà: ";
        Difficolta[] valori = Difficolta.values();
        int spaziatura = 20;          // distanza orizzontale costante tra le voci
        int yBase = ALTEZZA / 2;      // coordinata verticale della riga

        // 1) Calcolo della larghezza totale della riga
        int larghezzaTotale = fm.stringWidth(etichettaDiff);
        for (int i = 0; i < valori.length; i++) {
            Difficolta d = valori[i];
            String testo = d.etichetta;
            if (d == difficolta) {
                testo = "[" + testo + "]"; // evidenziamo la difficoltà selezionata
            }
            larghezzaTotale += fm.stringWidth(testo);
            if (i < valori.length - 1) {
                larghezzaTotale += spaziatura;
            }
        }

        // 2) X di partenza per centrare l'intera riga
        int xInizio = LARGHEZZA / 2 - larghezzaTotale / 2;
        int xCorrente = xInizio;

        // 3) Disegno etichetta "Difficoltà: "
        g2d.setColor(COLORE_TESTO_MENU);
        g2d.drawString(etichettaDiff, xCorrente, yBase);
        xCorrente += fm.stringWidth(etichettaDiff);

        // 4) Disegno le tre voci di difficoltà una accanto all'altra
        for (int i = 0; i < valori.length; i++) {
            Difficolta d = valori[i];
            String testo = d.etichetta;
            if (d == difficolta) {
                testo = "[" + testo + "]";
                g2d.setColor(COLORE_PUNTEGGIO);  // colore rosa per quella selezionata
            } else {
                g2d.setColor(COLORE_TESTO_MENU);
            }

            g2d.drawString(testo, xCorrente, yBase);
            xCorrente += fm.stringWidth(testo);
            if (i < valori.length - 1) {
                xCorrente += spaziatura;
            }
        }

        // ---------- Istruzioni ----------
        g2d.setColor(COLORE_TESTO_MENU);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 18));
        String riga1 = "↑ / ↓ per cambiare difficoltà";
        String riga2 = "Invio per iniziare";
        String riga3 = "Controlli in partita: frecce ↑/↓, P o ESC per pausa (paletta destra)";

        int yIstruzioni = ALTEZZA / 2 + 60;
        int w1 = g2d.getFontMetrics().stringWidth(riga1);
        int w2 = g2d.getFontMetrics().stringWidth(riga2);
        int w3 = g2d.getFontMetrics().stringWidth(riga3);

        g2d.drawString(riga1, LARGHEZZA / 2 - w1 / 2, yIstruzioni);
        g2d.drawString(riga2, LARGHEZZA / 2 - w2 / 2, yIstruzioni + 25);
        g2d.drawString(riga3, LARGHEZZA / 2 - w3 / 2, yIstruzioni + 50);
    }

    // Overlay semi-trasparente per la pausa
    private void disegnaOverlayPausa(Graphics2D g2d) {
        g2d.setColor(COLORE_OVERLAY);
        g2d.fillRect(0, 0, LARGHEZZA, ALTEZZA);

        g2d.setColor(COLORE_TESTO_MENU);
        g2d.setFont(new Font("Consolas", Font.BOLD, 36));
        String pausa = "PAUSA";
        int w = g2d.getFontMetrics().stringWidth(pausa);
        g2d.drawString(pausa, LARGHEZZA / 2 - w / 2, ALTEZZA / 2 - 20);

        g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
        String riga1 = "R o P per riprendere (con countdown di 3 secondi)";
        String riga2 = "Q o ESC per tornare al menu";

        int w1 = g2d.getFontMetrics().stringWidth(riga1);
        int w2 = g2d.getFontMetrics().stringWidth(riga2);

        g2d.drawString(riga1, LARGHEZZA / 2 - w1 / 2, ALTEZZA / 2 + 20);
        g2d.drawString(riga2, LARGHEZZA / 2 - w2 / 2, ALTEZZA / 2 + 50);
    }

    // Overlay con il numero 3,2,1 al centro
    private void disegnaOverlayCountdown(Graphics2D g2d) {
        // Calcolo dei secondi rimanenti dal numero di frame
        int secondiRimasti = fotogrammiCountdown / 60 + 1;

        g2d.setColor(COLORE_OVERLAY);
        g2d.fillRect(0, 0, LARGHEZZA, ALTEZZA);

        g2d.setColor(COLORE_PUNTEGGIO);
        g2d.setFont(new Font("Consolas", Font.BOLD, 72));
        String testo = String.valueOf(secondiRimasti);
        int w = g2d.getFontMetrics().stringWidth(testo);
        g2d.drawString(testo, LARGHEZZA / 2 - w / 2, ALTEZZA / 2);
    }

    // ===================== GAME LOOP =====================

    @Override
    public void actionPerformed(ActionEvent e) {
        // In base allo stato, aggiorno solo ciò che mi serve
        switch (stato) {
            case IN_GIOCO -> {
                aggiornaPalette();  // IA + giocatore
                aggiornaPallina();
            }
            case CONTO_ALLA_ROVESCIA -> {
                // Durante il countdown la pallina è ferma,
                // ma possiamo già muovere palette e IA
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

        // Richiede un ridisegno del pannello
        repaint();
    }

    // Aggiorna entrambe le palette: IA a sinistra + giocatore a destra
    private void aggiornaPalette() {
        aggiornaPalettaIA();
        aggiornaPalettaGiocatore();
    }

    // ---------- IA PER PALETTA SINISTRA ----------

    private void aggiornaPalettaIA() {
        // Centro verticale della paletta sinistra (IA)
        int centroPaletta = yPalettaSinistra + ALTEZZA_PALETTA / 2;
        // Centro verticale della pallina
        int centroPallina = yPallina + DIMENSIONE_PALLINA / 2;

        // La pallina si sta muovendo verso sinistra? (verso l'IA)
        boolean pallinaVersoIA = velXPallina < 0;

        // Parametri che dipendono dalla difficoltà
        int velocitaIA;
        double probabilitaErrore;    // quanto spesso l'IA "non reagisce"
        double reattivita;           // 1.0 = reagisce ogni frame, 0.5 = metà dei frame

        switch (difficolta) {
            case FACILE -> {
                velocitaIA = 3;
                probabilitaErrore = 0.20;  // 20% dei frame l'IA non si muove proprio
                reattivita = 0.5;          // reagisce in media 1 frame su 2
            }
            case NORMALE -> {
                velocitaIA = 5;
                probabilitaErrore = 0.05;  // qualche piccolo "errore"
                reattivita = 0.75;         // reagisce nella maggior parte dei frame
            }
            case DIFFICILE -> {
                velocitaIA = 7;
                probabilitaErrore = 0.0;   // niente errori
                reattivita = 1.0;          // reagisce sempre
            }
            default -> {
                velocitaIA = 5;
                probabilitaErrore = 0.0;
                reattivita = 1.0;
            }
        }

        // In FACILE e NORMALE l'IA reagisce solo se la pallina si muove verso sinistra.
        // Questo le impedisce di inseguire sempre la pallina in anticipo.
        if (difficolta != Difficolta.DIFFICILE && !pallinaVersoIA) {
            return;
        }

        // L'IA a volte "sbaglia" e non si muove proprio in questo frame
        if (Math.random() < probabilitaErrore) {
            return;
        }

        // Reazione lenta: l'IA si muove solo in una frazione dei frame
        if (Math.random() > reattivita) {
            return;
        }

        // "Zona morta": se la pallina è quasi allineata, l'IA non si muove
        int tolleranza = 10;

        // Se il centro della pallina è più in basso di "tolleranza", scendi
        if (centroPallina > centroPaletta + tolleranza) {
            yPalettaSinistra += velocitaIA;
        }
        // Se è più in alto di "tolleranza", sali
        else if (centroPallina < centroPaletta - tolleranza) {
            yPalettaSinistra -= velocitaIA;
        }

        // Limiti verticali dello schermo
        if (yPalettaSinistra < 0) yPalettaSinistra = 0;
        if (yPalettaSinistra + ALTEZZA_PALETTA > ALTEZZA) {
            yPalettaSinistra = ALTEZZA - ALTEZZA_PALETTA;
        }
    }

    // ---------- PALETTA DESTRA CONTROLLATA DAL GIOCATORE ----------

    private void aggiornaPalettaGiocatore() {
        if (frecciaSuPremuta) {
            yPalettaDestra -= VELOCITA_PALETTA_GIOCATORE;
        }
        if (frecciaGiuPremuta) {
            yPalettaDestra += VELOCITA_PALETTA_GIOCATORE;
        }

        // Limiti verticali dello schermo
        if (yPalettaDestra < 0) yPalettaDestra = 0;
        if (yPalettaDestra + ALTEZZA_PALETTA > ALTEZZA)
            yPalettaDestra = ALTEZZA - ALTEZZA_PALETTA;
    }

    // Aggiorna posizione della pallina e gestisce collisioni e punti
    private void aggiornaPallina() {
        xPallina += velXPallina;
        yPallina += velYPallina;

        // Rimbalzo su bordo alto/basso
        if (yPallina <= 0 || yPallina + DIMENSIONE_PALLINA >= ALTEZZA) {
            velYPallina = -velYPallina;
        }

        // Collisione con palette sinistra (IA)
        if (xPallina <= xPalettaSinistra + LARGHEZZA_PALETTA &&
                xPallina >= xPalettaSinistra &&
                yPallina + DIMENSIONE_PALLINA >= yPalettaSinistra &&
                yPallina <= yPalettaSinistra + ALTEZZA_PALETTA) {

            velXPallina = Math.abs(velXPallina); // assicuro che vada verso destra
        }

        // Collisione con palette destra (giocatore)
        if (xPallina + DIMENSIONE_PALLINA >= xPalettaDestra &&
                xPallina + DIMENSIONE_PALLINA <= xPalettaDestra + LARGHEZZA_PALETTA &&
                yPallina + DIMENSIONE_PALLINA >= yPalettaDestra &&
                yPallina <= yPalettaDestra + ALTEZZA_PALETTA) {

            velXPallina = -Math.abs(velXPallina); // assicuro che vada verso sinistra
        }

        // Punto per IA (pallina esce a destra)
        if (xPallina > LARGHEZZA) {
            punteggioSinistra++;
            aggiornaMigliorPunteggio();
            reimpostaPallina(false);   // pallina riparte verso sinistra (verso IA)
            avviaCountdown();
        }

        // Punto per giocatore (pallina esce a sinistra)
        if (xPallina + DIMENSIONE_PALLINA < 0) {
            punteggioDestra++;
            aggiornaMigliorPunteggio();
            reimpostaPallina(true);    // pallina riparte verso destra (verso giocatore)
            avviaCountdown();
        }
    }

    // ===================== LOGICA DI STATO =====================

    // Riporta le palette al centro e reimposta la pallina
    private void reimpostaPosizioni() {
        yPalettaSinistra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;
        yPalettaDestra = ALTEZZA / 2 - ALTEZZA_PALETTA / 2;
        reimpostaPallina(true);
    }

    // Reimposta la posizione della pallina al centro e le sue velocità
    private void reimpostaPallina(boolean versoDestra) {
        xPallina = LARGHEZZA / 2 - DIMENSIONE_PALLINA / 2;
        yPallina = ALTEZZA / 2 - DIMENSIONE_PALLINA / 2;

        int v = difficolta.velocitaPallina;
        velXPallina = versoDestra ? v : -v;
        // Direzione verticale casuale (su o giù)
        velYPallina = (Math.random() < 0.5) ? v : -v;
    }

    // Parte una nuova partita dal menu
    private void avviaNuovaPartita() {
        // Considero chiusa l'eventuale partita precedente
        aggiornaMigliorPunteggio();
        punteggioSinistra = 0;
        punteggioDestra = 0;

        reimpostaPosizioni();
        avviaCountdown();
    }

    // Imposta lo stato su "CONTO_ALLA_ROVESCIA"
    private void avviaCountdown() {
        stato = StatoGioco.CONTO_ALLA_ROVESCIA;
        fotogrammiCountdown = 3 * 60; // ~3 secondi
    }

    // Mette il gioco in pausa
    private void mettiInPausa() {
        if (stato == StatoGioco.IN_GIOCO || stato == StatoGioco.CONTO_ALLA_ROVESCIA) {
            stato = StatoGioco.IN_PAUSA;
        }
    }

    // Dalla pausa riparte con countdown
    private void riprendiConCountdown() {
        if (stato == StatoGioco.IN_PAUSA) {
            avviaCountdown();
        }
    }

    // Torna al menu e aggiorna il best score
    private void tornaAlMenu() {
        aggiornaMigliorPunteggio();
        punteggioSinistra = 0;
        punteggioDestra = 0;
        stato = StatoGioco.MENU;
    }

    // Aggiorna il miglior punteggio e chi lo detiene (Giocatore o IA)
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

    // Seleziona difficoltà successiva nel menu
    private void selezionaDifficoltaSuccessiva() {
        Difficolta[] valori = Difficolta.values();
        int indice = (difficolta.ordinal() + 1) % valori.length;
        difficolta = valori[indice];
    }

    // Seleziona difficoltà precedente nel menu
    private void selezionaDifficoltaPrecedente() {
        Difficolta[] valori = Difficolta.values();
        int indice = (difficolta.ordinal() - 1 + valori.length) % valori.length;
        difficolta = valori[indice];
    }

    // ===================== INPUT TASTIERA =====================

    @Override
    public void keyPressed(KeyEvent e) {
        int codice = e.getKeyCode();

        switch (stato) {
            case MENU -> {
                // Nel menu usiamo ↑/↓ per cambiare difficoltà e Invio per iniziare
                switch (codice) {
                    case KeyEvent.VK_UP -> selezionaDifficoltaPrecedente();
                    case KeyEvent.VK_DOWN -> selezionaDifficoltaSuccessiva();
                    case KeyEvent.VK_ENTER -> avviaNuovaPartita();
                }
            }
            case IN_GIOCO, CONTO_ALLA_ROVESCIA -> {
                // Durante il gioco gestiamo solo la paletta destra e la pausa
                switch (codice) {
                    case KeyEvent.VK_UP -> frecciaSuPremuta = true;
                    case KeyEvent.VK_DOWN -> frecciaGiuPremuta = true;
                    case KeyEvent.VK_ESCAPE, KeyEvent.VK_P -> mettiInPausa();
                }
            }
            case IN_PAUSA -> {
                // Nella pausa: R/P per riprendere, Q/ESC per tornare al menu
                switch (codice) {
                    case KeyEvent.VK_R, KeyEvent.VK_P -> riprendiConCountdown();
                    case KeyEvent.VK_Q, KeyEvent.VK_ESCAPE -> tornaAlMenu();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codice = e.getKeyCode();

        // Resettiamo i flag dei tasti del giocatore
        switch (codice) {
            case KeyEvent.VK_UP -> frecciaSuPremuta = false;
            case KeyEvent.VK_DOWN -> frecciaGiuPremuta = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non usato (serve solo per caratteri "stampabili")
    }
}
