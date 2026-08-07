# Variabili in Python - Fundamentals

"""
Python è un linguaggio in cui i tipi non vanno dichiarati esplicitamente
dal programmatore: è l'interprete che, a runtime, assegna il tipo di dato alla variabile 
in base al valore che le viene assegnato.
Si dice infatti che è un linguaggio a tipizzazione dinamica.
Non solo: è weakly typed, ovvero permette di riassegnare valori di tipo diverso alla 
stessa variabile senza generare errori.

Vedremo che questa è un'arma a doppio taglio: da un lato rende il linguaggio più semplice
da usare, dall'altro può generare errori difficili da individuare.
"""

# I due tipi numerici sono proprio come in C - non useremo double perché in Python i float sono già a doppia precisione.
age = 22 # int
balance = 6.7 # float

# I caratteri singoli, a differenza di C, sono considerati stringhe di lunghezza 1:
# In Python il tipo char è fagocitato dal tipo stringa str:
grade = 'A' # str
name = "Prodigy" # str

# N.B.: Vanno bene sia le virgolette singole che quelle doppie per le stringhe, ma non si possono mischiare.

isEngineer = True # bool


