#!/usr/bin/env bash
# cyber-launcher.sh — launcher locale per samu-cyberlocker (macOS / Linux)
set -eu

C='\033[96m'; M='\033[95m'; G='\033[92m'; Y='\033[93m'; R='\033[91m'
D='\033[2m'; B='\033[1m'; N='\033[0m'

PORT=5180
ROOT="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT"

printf "${C}"
cat <<'EOF'
  ==================================================
    S A M U   C Y B E R L O C K E R
    personal knowledge archive -- localhost only
  ==================================================
EOF
printf "${N}\n"

if ! command -v node >/dev/null 2>&1; then
  printf "${R}[fatal]${N} Node.js non trovato. Installalo prima di continuare.\n" >&2
  exit 1
fi
if ! command -v npm >/dev/null 2>&1; then
  printf "${R}[fatal]${N} npm non trovato.\n" >&2
  exit 1
fi
printf "${D}[boot]${N} node %s  npm %s\n" "$(node -v)" "$(npm -v)"

PIDS="$(lsof -t -i:"$PORT" 2>/dev/null || true)"
if [ -n "$PIDS" ]; then
  printf "${Y}[port]${N} Porta %s occupata (PID: %s) -- termino...\n" "$PORT" "$(echo "$PIDS" | tr '\n' ' ')"
  echo "$PIDS" | xargs -r kill -TERM 2>/dev/null || true
  sleep 1
  PIDS2="$(lsof -t -i:"$PORT" 2>/dev/null || true)"
  if [ -n "$PIDS2" ]; then
    echo "$PIDS2" | xargs -r kill -KILL 2>/dev/null || true
  fi
  printf "${G}[port]${N} Porta %s liberata.\n" "$PORT"
else
  printf "${D}[port]${N} Porta %s libera.\n" "$PORT"
fi

NEED_INSTALL=0
if [ ! -d node_modules ]; then
  NEED_INSTALL=1
  printf "${Y}[deps]${N} node_modules mancante.\n"
elif [ package.json -nt node_modules ] || [ package-lock.json -nt node_modules ]; then
  NEED_INSTALL=1
  printf "${Y}[deps]${N} package.json o package-lock.json aggiornati dopo l'ultimo install.\n"
fi
if [ "$NEED_INSTALL" -eq 1 ]; then
  printf "${C}[deps]${N} npm install...\n"
  npm install
  touch node_modules
  printf "${G}[deps]${N} Dipendenze installate.\n"
else
  printf "${D}[deps]${N} node_modules up-to-date.\n"
fi

printf "\n${M}[run]${N} ${B}http://127.0.0.1:%s${N}  (Ctrl-C per fermare)\n\n" "$PORT"
exec npm run dev
