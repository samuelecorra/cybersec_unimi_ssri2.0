# =====================================================================
# Makefile di crouter
#
# 'make' compila ogni src/*.c in un oggetto build/*.o e li collega nel
# binario build/crouter. La ricompilazione è incrementale: un .c viene
# ricompilato solo se lui o un header sono più recenti del suo .o.
# =====================================================================

CC      := gcc
# -std=gnu11 : C11 + estensioni GNU (ci servono per alcune funzioni POSIX/Linux)
# -Wall -Wextra : tutti i warning ragionevoli, obiettivo "zero warning"
# -O2 : ottimizzazione standard
CFLAGS  := -std=gnu11 -Wall -Wextra -O2

BIN     := build/crouter
# $(wildcard ...) espande la lista dei file esistenti.
SRC     := $(wildcard src/*.c)
# Sostituzione di pattern: ogni "src/x.c" diventa "build/x.o".
OBJ     := $(SRC:src/%.c=build/%.o)
HDR     := $(wildcard src/*.h)

# Il primo target è quello di default (eseguito da 'make' senza argomenti).
all: $(BIN)

# Link finale: da tutti gli oggetti al binario. $@ = il target ($(BIN)).
$(BIN): $(OBJ)
	$(CC) $(CFLAGS) -o $@ $(OBJ)

# Regola di compilazione: build/x.o da src/x.c. Dipende da TUTTI gli header
# (semplice e sicuro: se tocchi un .h ricompila tutto). Il "| build" è una
# dipendenza "order-only": garantisce che la cartella build/ esista senza
# forzare ricompilazioni quando la sua data cambia. $< = il primo
# prerequisito (il .c).
build/%.o: src/%.c $(HDR) | build
	$(CC) $(CFLAGS) -c -o $@ $<

# Crea la cartella degli oggetti.
build:
	mkdir -p build

# 'make debug' ricompila da zero senza ottimizzazioni e con i simboli (-g),
# per usare gdb e valgrind con informazioni utili.
debug: CFLAGS := -std=gnu11 -Wall -Wextra -O0 -g
debug: clean all

# Rimuove tutti gli artefatti di build (lascia solo i sorgenti).
clean:
	rm -rf build

# Target "finti" (non producono file con quel nome): vanno dichiarati
# .PHONY così make non li confonde con file e li esegue sempre.
.PHONY: all clean debug
