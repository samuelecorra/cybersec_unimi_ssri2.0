#!/usr/bin/env bash
# =====================================================================
# offload.sh  —  Disattiva il "TX checksum offload" (e in cascata TSO/GSO)
#                su tutte le interfacce dei namespace della topologia.
#
# PERCHÉ. Normalmente la scheda di rete calcola il checksum TCP/UDP in
# hardware: il kernel invia il pacchetto col checksum "vuoto" e l'hardware
# lo riempie. Sui veth emulati quell'hardware non esiste, quindi il
# checksum resterebbe incompleto; crouter inoltra i byte così come sono e
# l'host finale scarterebbe i segmenti come corrotti. Disattivando l'offload
# è il kernel a calcolare subito il checksum, e tutto torna. È un dettaglio
# dell'EMULAZIONE: su hardware reale il problema non si porrebbe.
# =====================================================================
set -u

for ns in pc1 pc2 r1 r2 r3 r4; do
    # Salta i namespace non presenti (/var/run/netns/<ns> non esiste).
    [ -e "/var/run/netns/$ns" ] || continue
    # ls /sys/class/net elenca le interfacce del namespace.
    for dev in $(ip netns exec "$ns" ls /sys/class/net); do
        [ "$dev" = "lo" ] && continue                 # salta il loopback
        # ethtool -K cambia i "feature" di offload. Una feature per chiamata:
        # se una non fosse modificabile su un veth, la richiesta combinata
        # fallirebbe in blocco. "tx off" disattiva anche TSO (segmentazione
        # in hardware); "gro off" disattiva il riassemblaggio in ricezione.
        ip netns exec "$ns" ethtool -K "$dev" tx off >/dev/null 2>&1
        ip netns exec "$ns" ethtool -K "$dev" gro off >/dev/null 2>&1
        # Verifica e stampa lo stato risultante (awk estrae il campo valore).
        st=$(ip netns exec "$ns" ethtool -k "$dev" | awk '/^tx-checksumming/{print $2}')
        echo "$ns/$dev tx-checksumming: $st"
    done
done
