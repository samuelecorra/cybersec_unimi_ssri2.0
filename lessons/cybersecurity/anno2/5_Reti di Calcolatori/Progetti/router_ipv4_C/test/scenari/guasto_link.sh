#!/usr/bin/env bash
# =====================================================================
# guasto_link.sh  —  Scenario 4 del walkthrough: guasto del link R2<->R3
#                    e riconvergenza automatica per il cammino alternativo.
#
# COSA DIMOSTRA. Con un ping continuo attivo da pc1 a pc2, abbattiamo il
# link R2-R3. R2, perso il percorso verso R3, annuncia a crouter la rotta
# "avvelenata" (metrica 16); crouter la invalida e, al successivo annuncio
# di R4, reimpara la destinazione dal cammino ridondante (via R4). Il ping
# misura il breve "blackout" di riconvergenza.
# =====================================================================
set -u

# Avvia un ping continuo in background e ne salva l'output; $! = PID del
# processo appena messo in background. -i 0.5 = un ping ogni mezzo secondo.
echo "[1] ping continuo pc1 -> pc2 avviato"
ip netns exec pc1 ping -i 0.5 -W 1 10.0.4.100 >/tmp/ping_guasto.txt 2>&1 &
PING=$!
sleep 5

# Guasto: spegne l'interfaccia di R2 verso R3.
echo "[2] $(date +%T) - GIU' il link R2<->R3"
ip netns exec r2 ip link set eth1 down

# Lascia il tempo per la riconvergenza, poi ferma il ping (SIGINT, così
# ping stampa il riepilogo statistico finale).
sleep 40
kill -INT "$PING" 2>/dev/null
sleep 0.5

# Statistiche del ping durante il guasto (righe "transmitted" e "rtt").
echo "[3] statistiche del ping durante il guasto:"
grep -E "transmitted|rtt" /tmp/ping_guasto.txt

# Eventi RIP registrati da crouter nella finestra di riconvergenza.
echo
echo "[4] eventi RIP visti da crouter:"
grep -E "irraggiungibile|percorso migliore|timeout della rotta|triggered" /tmp/crouter.log | tail -8

# Stato finale della RIB: si chiede il dump con SIGUSR1 e lo si legge dal log.
echo
echo "[5] RIB dopo la riconvergenza:"
pkill -USR1 -x crouter
sleep 1
grep -A 11 "RIB (tabella" /tmp/crouter.log | tail -12
