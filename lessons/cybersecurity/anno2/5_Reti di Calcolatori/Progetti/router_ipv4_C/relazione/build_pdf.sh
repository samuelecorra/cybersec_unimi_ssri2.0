#!/usr/bin/env bash
# =====================================================================
# build_pdf.sh  —  Genera relazione.pdf da relazione.md (pandoc + wkhtmltopdf).
#
# Due pre-elaborazioni sul sorgente Markdown (fatte su una COPIA temporanea,
# il file vero non si tocca):
#  - i segnaposto HTML <!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE: X -->
#    diventano riquadri tratteggiati visibili, così nel PDF si vede DOVE
#    incollare gli screenshot;
#  - le poche formule $...$ e le emoji dei callout diventano rispettivamente
#    unicode e badge testuali (il motore wkhtmltopdf non renderebbe né la
#    matematica LaTeX né i color-emoji).
# =====================================================================
set -eu                 # -e: esci al primo errore; -u: variabili non definite = errore
cd "$(dirname "$0")"     # lavora nella cartella della relazione

SRC=relazione.md
OUT=relazione.pdf
PANDOC=${PANDOC:-pandoc}

if ! command -v "$PANDOC" >/dev/null 2>&1; then
    WIN_PANDOC_ROOT="$HOME/AppData/Local/Microsoft/WinGet/Packages"
    WIN_PANDOC=$(find "$WIN_PANDOC_ROOT" -path "*/pandoc.exe" -type f 2>/dev/null | sort | tail -n 1 || true)
    if [ -n "$WIN_PANDOC" ] && [ -x "$WIN_PANDOC" ]; then
        PANDOC="$WIN_PANDOC"
    fi
fi

if ! command -v "$PANDOC" >/dev/null 2>&1 && [ ! -x "$PANDOC" ]; then
    echo "errore: pandoc non trovato" >&2
    exit 1
fi

# La copia temporanea sta NELLA cartella della relazione (-p .) così i
# percorsi relativi delle immagini (img/...) si risolvono correttamente.
TMP=$(mktemp --suffix=.md -p .)     # copia temporanea da manipolare
trap 'rm -f "$TMP"' EXIT            # cancella la copia all'uscita (anche su errore)

# Segnaposto screenshot -> div stilizzato. sed -E abilita le regex estese;
# \1 è il testo catturato tra le parentesi (la descrizione dello screenshot).
sed -E 's@<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE:?[[:space:]]*(.*)-->@<div class="screenshot-slot">[ Spazio per screenshot ] — \1</div>@' "$SRC" > "$TMP"

# Le emoji dei callout non sono rese da wkhtmltopdf (QtWebKit non supporta
# i color-emoji): le sostituiamo con badge testuali che restano leggibili.
# sed -i modifica il file "in place".
sed -i \
    -e 's/> 📌/> ◆ **NOTA.**/' \
    -e 's/> ⚠️/> ▲ **ATTENZIONE.**/' \
    -e 's/> ⚠/> ▲ **ATTENZIONE.**/' \
    -e 's/> 💡/> ● **SPUNTO.**/' \
    -e 's/> ✅/> ✔ **IN SINTESI.**/' \
    "$TMP"

# Formule inline -> unicode (l'insieme usato nella relazione è minimo)
sed -i \
    -e 's/\$TTL \\leftarrow TTL - 1\$/TTL ← TTL − 1/g' \
    -e 's/\$m = 16 = \\infty\$/m = 16 = ∞/g' \
    -e 's/\$1 \\le m \\le 15\$/1 ≤ m ≤ 15/g' \
    -e 's/\$TTL \\le 1\$/TTL ≤ 1/g' \
    -e 's/\$TTL\$/TTL/g' \
    -e 's/\$m = 16\$/m = 16/g' \
    -e 's/\$\\infty\$/∞/g' \
    -e 's/\$L\$/L/g' \
    "$TMP"

# Conversione a PDF con pandoc.
#  --standalone           documento completo (non un frammento);
#  --css=stile.css        foglio di stile per l'aspetto (colori, tabelle...);
#  --pdf-engine=wkhtmltopdf   rende via HTML -> PDF (motore WebKit);
#  --enable-local-file-access permette di caricare il CSS locale;
#  -T/-B/-L/-R            margini superiore/inferiore/sinistro/destro.
# Le opzioni del motore si passano con --pdf-engine-opt=...
if command -v wkhtmltopdf >/dev/null 2>&1; then
    "$PANDOC" "$TMP" \
        --standalone \
        --css=stile.css \
        --pdf-engine=wkhtmltopdf \
        --pdf-engine-opt=--enable-local-file-access \
        --pdf-engine-opt=--encoding --pdf-engine-opt=utf-8 \
        --pdf-engine-opt=-T --pdf-engine-opt=18mm \
        --pdf-engine-opt=-B --pdf-engine-opt=18mm \
        --pdf-engine-opt=-L --pdf-engine-opt=16mm \
        --pdf-engine-opt=-R --pdf-engine-opt=16mm \
        -o "$OUT"
else
    HTML=$(mktemp --suffix=.html -p .)
    CHROME_PROFILE=$(mktemp -d -p .)
    trap 'rm -f "$TMP" "$HTML"; rm -rf "$CHROME_PROFILE"' EXIT
    "$PANDOC" "$TMP" --standalone --css=stile.css -o "$HTML"

    CHROME=${CHROME:-}
    for candidate in \
        "/c/Program Files/Google/Chrome/Application/chrome.exe" \
        "/c/Program Files (x86)/Microsoft/Edge/Application/msedge.exe"
    do
        if [ -z "$CHROME" ] && [ -x "$candidate" ]; then
            CHROME="$candidate"
        fi
    done

    if [ -z "$CHROME" ]; then
        for root in "$HOME/.cache/puppeteer" "$HOME/AppData/Local/ms-playwright"; do
            if [ -d "$root" ]; then
                candidate=$(find "$root" -path "*/chrome.exe" -type f 2>/dev/null | sort | tail -n 1 || true)
                if [ -n "$candidate" ] && [ -x "$candidate" ]; then
                    CHROME="$candidate"
                    break
                fi
            fi
        done
    fi

    if [ -z "$CHROME" ]; then
        echo "errore: wkhtmltopdf non trovato e nessun Chrome/Edge disponibile per il fallback" >&2
        exit 1
    fi

    OUT_WIN=$(cygpath -w "$(pwd)/$OUT")
    HTML_URI="file:///$(cygpath -w "$(pwd)/$HTML" | sed 's@\\@/@g; s@ @%20@g')"
    "$CHROME" \
        --headless=new \
        --disable-gpu \
        --user-data-dir="$(cygpath -w "$(pwd)/$CHROME_PROFILE")" \
        --no-pdf-header-footer \
        --allow-file-access-from-files \
        --print-to-pdf="$OUT_WIN" \
        "$HTML_URI"
fi

echo "creato: $(pwd)/$OUT ($(du -h "$OUT" | cut -f1))"
