#!/usr/bin/env bash
# =====================================================================
# demo.sh  —  Esegue i CINQUE scenari del walkthrough su una topologia
#             vergine e salva ogni output in relazione/cattura/ (la base
#             testuale della relazione; gli screenshot GUI si aggiungono
#             a mano). Da lanciare come root:
#
#             sudo bash test/demo.sh
# =====================================================================
set -u
BASE="$(cd "$(dirname "$0")/.." && pwd)"
OUT="$BASE/relazione/cattura"
mkdir -p "$OUT"

# Due scorciatoie:
#  ns <namespace> <cmd...> : esegue un comando dentro un namespace.
#    "${@:2}" = tutti gli argomenti dal secondo in poi (cioè il comando).
#  title <testo> : stampa un'intestazione colorata (ciano, "\033[1;36m").
ns()   { ip netns exec "$1" "${@:2}"; }
title(){ printf '\n\033[1;36m===== %s =====\033[0m\n' "$1"; }

# ---- Ambiente pulito -------------------------------------------------
# Ferma un eventuale crouter, ricrea la topologia da zero e aspetta che i
# soli FRR convergano tra loro prima di introdurre crouter.
pkill -x crouter 2>/dev/null
bash "$BASE/test/topo.sh" down >/dev/null 2>&1
bash "$BASE/test/topo.sh" up   >/dev/null
sleep 6

# ---- Scenario 1: convergenza a freddo --------------------------------
# Avvia crouter (&, in background), attende la convergenza, poi chiede il
# dump della RIB con SIGUSR1 e ne estrae le 12 righe finali. "tee" mostra
# a video E salva su file contemporaneamente.
title "SCENARIO 1 - Convergenza a freddo di crouter"
ns r1 "$BASE/build/crouter" -c "$BASE/conf/r1.conf" >/tmp/crouter.log 2>&1 &
sleep 14
pkill -USR1 -x crouter; sleep 1
grep -A 11 "RIB (tabella" /tmp/crouter.log | tail -12 | tee "$OUT/01_convergenza_rib.txt"

# ---- Scenario 2: data plane (ping + traceroute) ----------------------
# Le graffe { ... } raggruppano più comandi così che il loro output
# combinato passi in un solo tee. "\$" stampa un '$' letterale (prompt finto).
title "SCENARIO 2 - Data plane: ping e traceroute pc1 -> pc2"
{
    echo "\$ ping -c 4 10.0.4.100"
    ns pc1 ping -c 4 -W 2 10.0.4.100
    echo
    echo "\$ traceroute -n 10.0.4.100   (crouter = primo hop, 10.0.1.1)"
    ns pc1 traceroute -n -w 1 -q 1 10.0.4.100
} | tee "$OUT/02_dataplane.txt"

# ---- Scenario 3: interoperabilita' RIPv2 con FRR ---------------------
# Cattura per 32 s (timeout) gli update RIP su R2 con tcpdump (-vv decodifica
# in dettaglio; -n niente DNS). Poi estrae UN update inviato da crouter
# (10.0.12.1) e la tabella vista da FRR.
title "SCENARIO 3 - Interoperabilita' RIPv2 (crouter <-> FRR)"
ns r2 timeout 32 tcpdump -ni eth0 -vv udp port 520 >"$OUT/03_rip_tcpdump.txt" 2>&1 &
sleep 32
{
    echo "### tcpdump di un update RIPv2 emesso da crouter (10.0.12.1):"
    echo "### (nota il poisoned reverse: le rotte apprese via eth2 tornano"
    echo "###  su eth2 con metric 16 = infinito)"
    # awk: da quando trova un pacchetto da 10.0.12.1 (p=1) stampa le righe;
    # conta le entry ("next-hop: self") e si ferma dopo la sesta (c==6).
    awk '/10\.0\.12\.1\.520 >/{p=1} p{print} p&&/next-hop: self$/{c++} c==6{exit}' \
        "$OUT/03_rip_tcpdump.txt"
    echo
    echo "### La stessa tabella vista da FRR su R2 (show ip rip):"
    vtysh -N r2 -c "show ip rip"
} | tee "$OUT/03_rip_interop.txt"

# ---- Scenario 4: guasto e riconvergenza ------------------------------
# Delega allo script dedicato; poi ripristina il link per lo scenario 5.
title "SCENARIO 4 - Guasto del link R2-R3 e riconvergenza"
bash "$BASE/test/scenari/guasto_link.sh" | tee "$OUT/04_guasto.txt"
ns r2 ip link set eth1 up   # ripristino

# ---- Scenario 5: sessione HTTP end-to-end ----------------------------
# Attende la riconvergenza post-ripristino, avvia un server HTTP su pc2 e
# lo interroga da pc1 (attraverso crouter). -D - stampa gli header di
# risposta; -o /dev/null scarta il corpo.
title "SCENARIO 5 - Traffico applicativo HTTP pc2 -> pc1"
sleep 25
ns pc2 nohup python3 -m http.server 8080 --bind 10.0.4.100 >/dev/null 2>&1 &
sleep 2
{
    echo "\$ curl -s -D - http://10.0.4.100:8080/  (attraverso crouter)"
    ns pc1 curl -s -D - -o /dev/null --max-time 5 http://10.0.4.100:8080/
} | tee "$OUT/05_http.txt"

title "FINE — output salvati in $OUT"
ls -1 "$OUT"
