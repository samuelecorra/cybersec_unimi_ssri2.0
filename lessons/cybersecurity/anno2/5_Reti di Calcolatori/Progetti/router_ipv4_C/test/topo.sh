#!/usr/bin/env bash
# =====================================================================
# topo.sh  —  Costruisce/smonta la topologia di test con i "network
#             namespaces" del kernel Linux (stessa tecnologia dei
#             container Docker: ogni namespace è uno stack di rete isolato).
#
#   pc1 --(10.0.1.0/24)-- R1[crouter] ==(10.0.12.0/30)== R2[FRR] ==(10.0.23.0/30)== R3[FRR] --(10.0.4.0/24)-- pc2
#                              ||                                                    ||
#                              ++========(10.0.14.0/30)== R4[FRR] ==(10.0.34.0/30)===++
#
# R1 (dove gira crouter) NON ha IP a livello kernel: gli indirizzi vivono
# solo in conf/r1.conf, così è il nostro codice a rispondere ad ARP/ICMP/RIP.
#
# Uso: sudo bash topo.sh {up|down|status}
#      sudo bash topo.sh vtysh <r2|r3|r4> "<comando>"
# =====================================================================

set -u   # errore se si usa una variabile non definita (evita bug silenziosi)

# Percorso della cartella del progetto (la cartella "sopra" test/).
# $0 = questo script; dirname -> la sua cartella; "/.." -> su di un livello;
# cd + pwd -> percorso assoluto normalizzato.
BASE="$(cd "$(dirname "$0")/.." && pwd)"

# Tutti i namespace (host + router) e, separatamente, i soli router FRR.
NSES=(pc1 r1 r2 r3 r4 pc2)
FRR_NODES=(r2 r3 r4)

# die "msg": stampa su stderr (>&2) ed esce con codice 1.
die() { echo "errore: $*" >&2; exit 1; }

# Serve root: id -u è 0 solo per root. "||" esegue die se il test fallisce.
[ "$(id -u)" -eq 0 ] || die "servono privilegi di root (sudo)"

# mklink ns_a if_a ns_b if_b : crea un "cavo" virtuale (coppia veth) e ne
# mette i due capi nei due namespace, rinominandoli e attivandoli. Una veth
# è una coppia di interfacce collegate come agli estremi di un cavo:
# ciò che entra da una esce dall'altra.
mklink() { # ns_a if_a ns_b if_b
    ip link add cr-tmp-a type veth peer name cr-tmp-b || die "veth $1/$3"
    ip link set cr-tmp-a netns "$1" name "$2"   # un capo -> ns_a, rinominato if_a
    ip link set cr-tmp-b netns "$3" name "$4"   # altro capo -> ns_b, rinominato if_b
    ip netns exec "$1" ip link set "$2" up      # accendi if_a
    ip netns exec "$3" ip link set "$4" up      # accendi if_b
}

# addr ns dev cidr : assegna un indirizzo IP a una interfaccia dentro un ns.
addr() { ip netns exec "$1" ip addr add "$3" dev "$2"; }

# setmac ns dev mac : forza un MAC deterministico e leggibile. WSL2 talvolta
# assegna lo STESSO MAC casuale a più veth: qui usiamo lo schema
# 02:00:00:<nodo>:00:<interfaccia> (02 = MAC "localmente amministrato").
# Il MAC si cambia solo a interfaccia spenta, da qui down/address/up.
setmac() { # ns dev mac
    ip netns exec "$1" ip link set dev "$2" down
    ip netns exec "$1" ip link set dev "$2" address "$3"
    ip netns exec "$1" ip link set dev "$2" up
}

# start_frr ns : avvia i demoni FRR (zebra + ripd) dentro un namespace.
start_frr() {
    local ns="$1"
    # FRR cerca le sue config per-namespace; le prepariamo e le copiamo.
    mkdir -p "/etc/frr/$ns" "/var/run/frr/$ns"
    cp "$BASE/conf/frr/$ns/zebra.conf" "$BASE/conf/frr/$ns/ripd.conf" "/etc/frr/$ns/"
    : > "/etc/frr/$ns/vtysh.conf"          # ":" = no-op; ">" crea file vuoto
    chown -R frr:frr "/etc/frr/$ns" "/var/run/frr/$ns"
    # zebra = il "gestore RIB" di FRR (parla col kernel); ripd = il demone RIP.
    #  -d = demonizza (background), -N = nome dell'istanza (per-namespace),
    #  -f = file di configurazione da caricare.
    ip netns exec "$ns" /usr/lib/frr/zebra -d -N "$ns" \
        -f "/etc/frr/$ns/zebra.conf" || die "zebra ($ns)"
    ip netns exec "$ns" /usr/lib/frr/ripd  -d -N "$ns" \
        -f "/etc/frr/$ns/ripd.conf"  || die "ripd ($ns)"
    echo "FRR avviato in $ns"
}

topo_up() {
    # Crea i namespace, accende il loopback e disabilita IPv6 (la demo è
    # tutta IPv4; senza, ci sarebbe rumore di traffico IPv6). "-qw" = set
    # silenzioso (quiet write) di parametri del kernel.
    for ns in "${NSES[@]}"; do
        ip netns add "$ns" || die "namespace $ns già esistente? (prova: $0 down)"
        ip netns exec "$ns" ip link set lo up
        ip netns exec "$ns" sysctl -qw net.ipv6.conf.all.disable_ipv6=1 \
                                       net.ipv6.conf.default.disable_ipv6=1
    done

    # I "cavi" della topologia: ogni riga è un link punto-punto.
    mklink pc1 eth0  r1 eth1
    mklink r1  eth2  r2 eth0
    mklink r1  eth3  r4 eth0
    mklink r2  eth1  r3 eth0
    mklink r4  eth1  r3 eth2
    mklink r3  eth1  pc2 eth0

    # MAC deterministici (vedi setmac).
    setmac pc1 eth0 02:00:00:0a:00:01
    setmac pc2 eth0 02:00:00:0b:00:01
    setmac r1  eth1 02:00:00:01:00:01
    setmac r1  eth2 02:00:00:01:00:02
    setmac r1  eth3 02:00:00:01:00:03
    setmac r2  eth0 02:00:00:02:00:00
    setmac r2  eth1 02:00:00:02:00:01
    setmac r3  eth0 02:00:00:03:00:00
    setmac r3  eth1 02:00:00:03:00:01
    setmac r3  eth2 02:00:00:03:00:02
    setmac r4  eth0 02:00:00:04:00:00
    setmac r4  eth1 02:00:00:04:00:01

    # Gli host: IP + rotta di default verso il proprio router.
    addr pc1 eth0 10.0.1.100/24
    ip netns exec pc1 ip route add default via 10.0.1.1
    addr pc2 eth0 10.0.4.100/24
    ip netns exec pc2 ip route add default via 10.0.4.1

    # Disattiva il checksum offload sui veth: in emulazione il calcolo del
    # checksum verrebbe delegato a un hardware inesistente e i segmenti
    # arriverebbero "non calcolati". Un router userspace inoltra i byte
    # grezzi, quindi l'host finale li scarterebbe come corrotti. Dettaglio
    # dell'emulazione, non un limite di crouter (vedi offload.sh).
    bash "$BASE/test/offload.sh" >/dev/null

    # I router FRR: interfacce dei link punto-punto (/30) e LAN di pc2.
    # NB: R1 NON riceve IP qui — li gestisce crouter.
    addr r2 eth0 10.0.12.2/30
    addr r2 eth1 10.0.23.1/30
    addr r3 eth0 10.0.23.2/30
    addr r3 eth1 10.0.4.1/24
    addr r3 eth2 10.0.34.2/30
    addr r4 eth0 10.0.14.2/30
    addr r4 eth1 10.0.34.1/30

    # Sui router FRR: abilita l'inoltro IP del kernel e disattiva il
    # "reverse path filter" (rp_filter), che con i percorsi ridondanti
    # della nostra topologia scarterebbe pacchetti legittimi. Poi avvia FRR.
    for ns in "${FRR_NODES[@]}"; do
        ip netns exec "$ns" sysctl -qw net.ipv4.ip_forward=1 \
                                       net.ipv4.conf.all.rp_filter=0 \
                                       net.ipv4.conf.default.rp_filter=0
        start_frr "$ns"
    done

    echo
    echo "Topologia attiva. crouter si lancia con:"
    echo "  sudo ip netns exec r1 $BASE/build/crouter -c $BASE/conf/r1.conf"
}

topo_down() {
    # Per ogni namespace ancora presente: termina i suoi processi (prima
    # con SIGTERM, poi SIGKILL per i più ostinati) e cancella il namespace.
    #  ip netns pids = i PID dentro il namespace; xargs -r = non eseguire
    #  kill se la lista è vuota.
    for ns in "${NSES[@]}"; do
        [ -e "/var/run/netns/$ns" ] || continue   # già assente, salta
        ip netns pids "$ns" | xargs -r kill 2>/dev/null
        sleep 0.2
        ip netns pids "$ns" | xargs -r kill -9 2>/dev/null
        ip netns del "$ns"
    done
    # Rimuove le config temporanee di FRR.
    for ns in "${FRR_NODES[@]}"; do
        rm -rf "/etc/frr/$ns" "/var/run/frr/$ns"
    done
    echo "topologia rimossa."
}

topo_status() {
    # Indirizzi di ogni namespace (-br = output breve).
    for ns in "${NSES[@]}"; do
        echo "== $ns =="
        ip netns exec "$ns" ip -br addr 2>/dev/null || echo "  (assente)"
    done
    # Tabella RIP di ogni router FRR (sed indenta l'output di due spazi).
    for ns in "${FRR_NODES[@]}"; do
        echo "== $ns: tabella RIP =="
        vtysh -N "$ns" -c "show ip rip" 2>/dev/null | sed 's/^/  /'
    done
}

# Dispatch del comando: "${1:-}" = primo argomento (o stringa vuota se
# assente, grazie a set -u). Per "vtysh" si scartano i primi due argomenti
# (shift) e si passa il resto ("$*") come comando a vtysh.
case "${1:-}" in
    up)     topo_up ;;
    down)   topo_down ;;
    status) topo_status ;;
    vtysh)  shift; ns="$1"; shift; vtysh -N "$ns" -c "$*" ;;
    *)      echo "uso: $0 {up|down|status|vtysh <ns> \"<cmd>\"}" >&2; exit 1 ;;
esac
