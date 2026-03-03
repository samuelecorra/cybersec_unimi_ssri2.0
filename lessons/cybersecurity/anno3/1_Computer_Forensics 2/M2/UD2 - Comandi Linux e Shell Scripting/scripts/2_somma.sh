#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Errore: Devi fornire esattamente due numeri come argomenti."
    echo "Uso: $0 numero1 numero2"
    exit 1
fi

re='^-?[0-9]+$'

if ! [[ $1 =~ $re ]] || ! [[ $2 =~ $re ]]; then
    echo "Errore: Entrambi gli argomenti devono essere numeri interi."
    exit 1
fi

somma=$(( $1 + $2 ))

echo "La somma di $1 e $2 è: $somma"
