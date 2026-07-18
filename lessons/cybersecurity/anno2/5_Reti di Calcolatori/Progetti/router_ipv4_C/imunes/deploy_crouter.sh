#!/usr/bin/env bash
# Da eseguire DOPO aver avviato l'esperimento nella GUI di IMUNES (Execute).
# Copia il binario crouter e la sua config nel nodo R1 (container Docker) e
# imposta le rotte di default su pc1/pc2. NON avvia crouter: lo si lancia
# nel terminale del nodo R1 (per vederne il log di convergenza).
#
# Presuppone: crouter compilato in ../build/crouter e r1.conf in questa cartella.
set -u
HERE="$(cd "$(dirname "$0")" && pwd)"
CROUTER="$HERE/../build/crouter"
CONF="$HERE/r1.conf"

R1=$(docker ps --format '{{.Names}}' | grep -E "\.n1$" | head -1)
PC1=$(docker ps --format '{{.Names}}' | grep -E "\.n0$" | head -1)
PC2=$(docker ps --format '{{.Names}}' | grep -E "\.n5$" | head -1)
if [ -z "$R1" ]; then
    echo "Nessun esperimento IMUNES attivo (nodo R1 non trovato)."
    echo "Avvia prima la topologia nella GUI (pulsante Execute), poi rilancia."
    exit 1
fi
echo "Container:  R1=$R1  PC1=$PC1  PC2=$PC2"
docker cp "$CROUTER" "$R1":/crouter
docker cp "$CONF"    "$R1":/r1.conf
docker exec "$PC1" ip route replace default via 10.0.1.1
docker exec "$PC2" ip route replace default via 10.0.4.1
echo "DEPLOY-OK — crouter e r1.conf copiati in R1; rotte di default impostate."
echo "Ora nella GUI: doppio clic su R1-crouter -> nel terminale lancia UNA sola volta:"
echo "    /crouter -c /r1.conf -v"
