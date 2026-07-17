#!/usr/bin/env bash
# =====================================================================
# run_rip.sh  —  Helper per la fase RIP.
#
# Passa dalla modalità "forwarding statico" a quella "RIP dinamico":
# rimuove dai router FRR le rotte statiche di ritorno verso la LAN di pc1
# (che servivano solo quando crouter non annunciava ancora nulla) e
# (ri)avvia crouter con la configurazione RIP. Il log va in /tmp/crouter.log.
# =====================================================================
set -u
BASE="$(cd "$(dirname "$0")/.." && pwd)"

# Toglie la rotta statica di ritorno da ciascun router FRR (se presente).
# "2>/dev/null" silenzia l'errore se la rotta non c'era.
ip netns exec r2 ip route del 10.0.1.0/24 2>/dev/null
ip netns exec r3 ip route del 10.0.1.0/24 2>/dev/null
ip netns exec r4 ip route del 10.0.1.0/24 2>/dev/null
echo "rotte statiche di ritorno rimosse da r2/r3/r4"

# Termina un'eventuale istanza precedente (-x = match esatto sul nome).
pkill -x crouter 2>/dev/null && sleep 0.5
# Avvia crouter nel namespace r1, in background (&), con log verboso.
ip netns exec r1 nohup "$BASE/build/crouter" -c "$BASE/conf/r1.conf" -v \
    >/tmp/crouter.log 2>&1 &
sleep 1
# Conferma che sia partito (pgrep -x trova il processo per nome esatto).
pgrep -x crouter >/dev/null && echo "crouter avviato con RIP (pid $(pgrep -x crouter))" \
                            || { echo "crouter NON avviato"; exit 1; }
