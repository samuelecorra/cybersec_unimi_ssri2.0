@echo off
REM cyber-launcher.bat -- launcher locale per samu-cyberlocker (Windows)
setlocal enabledelayedexpansion

set PORT=5180
cd /d "%~dp0"

echo.
echo ==================================================
echo   S A M U   C Y B E R L O C K E R
echo   personal knowledge archive -- localhost only
echo ==================================================
echo.

where node >nul 2>&1
if errorlevel 1 (
  echo [fatal] Node.js non trovato. Installalo prima di continuare.
  exit /b 1
)
where npm >nul 2>&1
if errorlevel 1 (
  echo [fatal] npm non trovato.
  exit /b 1
)

for /f "tokens=*" %%v in ('node -v') do set NODE_VER=%%v
for /f "tokens=*" %%v in ('npm -v') do set NPM_VER=%%v
echo [boot] node !NODE_VER!  npm !NPM_VER!

REM Check porta 5180: trova PID in LISTENING e killa
set FOUND_PID=
for /f "tokens=5" %%p in ('netstat -ano ^| findstr /R /C:":%PORT% .*LISTENING"') do (
  set FOUND_PID=%%p
)
if defined FOUND_PID (
  echo [port] Porta %PORT% occupata ^(PID: !FOUND_PID!^) -- termino...
  taskkill /F /PID !FOUND_PID! >nul 2>&1
  echo [port] Porta %PORT% liberata.
) else (
  echo [port] Porta %PORT% libera.
)

REM Install condizionale (fallback semplice: installa se node_modules manca)
if not exist node_modules (
  echo [deps] node_modules mancante -- npm install...
  call npm install
  if errorlevel 1 (
    echo [fatal] npm install fallito.
    exit /b 1
  )
  echo [deps] Dipendenze installate.
) else (
  echo [deps] node_modules presente.
)

echo.
echo [run] http://127.0.0.1:%PORT%  ^(Ctrl-C per fermare^)
echo.
call npm run dev
