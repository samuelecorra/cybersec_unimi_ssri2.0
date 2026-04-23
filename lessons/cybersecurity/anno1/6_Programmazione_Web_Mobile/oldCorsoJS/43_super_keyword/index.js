// La keyword super in Javascript è utilizzata nelle classi per chiamare
// il costruttore ma anche e soprattutto per accedere e chiamare
// proprietà e metodi della classe genitrice.

// Se this si riferiva all'istanza della classe figlia, super si riferisce
// all'istanza della classe genitrice.

// Ripropongo l'esempio degli animali che è molto intuitivo...
// Si noti che il costruttore della classe animale PUO' anzi DOVREBBE contenere
// gli assegnamenti di nome e età, perché son proprietà comuni a tutti e 3 i figli,
// mentre le 3 velocità sono peculiari di ciascuno!
class Animale {
    constructor(nome, età){
        this.nome = nome;
        this.età = età;
    }

    muovi(velocità) {
        console.log(`${this.nome} si sta muovendo a una velocità di ${velocità} km/h.`);
    }
}

class coniglio extends Animale {
    constructor(nome, età, velocitàDiCorsa) {
        super(nome, età); // Chiama il costruttore della classe Animale
        this.velocitàDiCorsa = velocitàDiCorsa;
    }

    corre() {
        console.log(`${this.nome} sa correre. Per la precisione...`);
        // Qui possiamo usare super per accedere al metodo muovi della classe Animale
        super.muovi(this.velocitàDiCorsa);
    }
}

class pesce extends Animale {
    constructor(nome, età, velocitàDiNuoto) {
        super(nome, età); // Chiama il costruttore della classe Animale
        this.velocitàDiNuoto = velocitàDiNuoto;
    }

    nuota() {
        console.log(`${this.nome} sa nuotare. Per la precisione...`);
        super.muovi(this.velocitàDiNuoto);
    }
}

class falco extends Animale {
    constructor(nome, età, velocitàDiVolo) {
        super(nome, età); // Chiama il costruttore della classe Animale
        this.velocitàDiVolo = velocitàDiVolo;
    }

    vola() {
        console.log(`${this.nome} sa volare. Per la precisione...`);
        super.muovi(this.velocitàDiVolo);
    }
}

const coniglio1 = new coniglio("Bunny", 2, 30);
const pesce1 = new pesce("Nemo", 1, 10);
const falco1 = new falco("Pellegrino", 3, 300);

// E ora verifichiamo stampando in console...

console.log(coniglio1.nome);
console.log(coniglio1.età);
console.log(coniglio1.velocitàDiCorsa);

console.log(pesce1.nome);
console.log(pesce1.età);
console.log(pesce1.velocitàDiNuoto);

console.log(falco1.nome);
console.log(falco1.età);
console.log(falco1.velocitàDiVolo);

coniglio1.corre(); // Stampa "Bunny sa correre. Per la precisione... Bunny si sta muovendo a una velocità di 30 km/h."

pesce1.nuota(); // Stampa "Nemo sa nuotare. Per la precisione... Nemo si sta muovendo a una velocità di 10 km/h."

falco1.vola(); // Stampa "Pellegrino sa volare. Per la precisione... Pellegrino si sta muovendo a una velocità di 300 km/h."
