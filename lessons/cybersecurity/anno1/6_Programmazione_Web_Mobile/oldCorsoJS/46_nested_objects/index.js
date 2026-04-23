// Gli oggetti annidati sono autoesplicativi: oggetti dentro ad altri oggetti,
// il che ci consente di creare data structures molto complesse a nostro piacimento!

// Ad esempio, un Child object sarà enclosed by un Parent object.

// Oppure, giusto per complicare già le cose:
// Person { Address{}, ContactInfo{}} oppure ancora
// Shopping Cart { Items{Keyboard{}, Mouse{}, Monitor{}}, 
//                  Payment_Methods{CreditCard{}, PayPal{}} }

// Esempio 1, partiamo da un oggetto singolo, così è più semplice:
const Persona = {
  nomeEcognome: "Principessa Gommarosa",
  età: 20,
  studia: true,
  hobbies: ["Serie TV", "Cosmesi", "Minecraft"],
  residenza: {
    città: "Fantabosco",
    via: "Piazza delle Caramelle",
    civico: 9,
    CAP: 12345,
  }
}

// Iniziamo a stampare tutto come nostro solito ormai:

console.log("Nome e cognome:", Persona.nomeEcognome);
console.log("Età:", Persona.età);
console.log("Studia:", Persona.studia);
console.log("Hobbies:", Persona.hobbies.join(", "));
console.log("Residenza:", Persona.residenza); // Così facendo otteniamo
// l'intero oggetto, ma per le singole proprietà del nested object:

console.log("  Città:", Persona.residenza.città);
console.log("  Via:", Persona.residenza.via);
console.log("  Civico:", Persona.residenza.civico);
console.log("  CAP:", Persona.residenza.CAP);

console.log("//////////////////////////////////////////////////////////");

// Si noti sin da subito che scorrere le proprietà si presta bene a un ciclo for:

for(const property in Persona.residenza) {
  console.log(`  ${property}:`, Persona.residenza[property]);
}

console.log("//////////////////////////////////////////////////////////");

// Vogliamo ora costruire una classe che contiene nested objects:

class Person {
  constructor(nomeEcognome, età, studia, hobbies, ...residenza) {
    this.nomeEcognome = nomeEcognome;
    this.età = età;
    this.studia = studia;
    this.hobbies = hobbies;
    this.residenza = new Residenza(...residenza);
  }
}

// FERMI TUTTI: cosa vuol dire mettere ...residenza?
// Significa che stiamo usando la sintassi "rest" per raccogliere tutti gli 
// argomenti rimanenti in un array.
// In questo caso, stiamo raccogliendo i dettagli della residenza.
// Possiamo anche definire dopo il costruttore di tale classe annidata,
// l'importante è che i nomi dei parametri corrispondano a quelli delle proprietà.

class Residenza {
  constructor(città, via, civico, CAP) {
    this.città = città;
    this.via = via;
    this.civico = civico;
    this.CAP = CAP;
  }
}

const Gommarosa = new Person( "Principessa Gommarosa", 20, true, 
                        ["Serie TV", "Cosmesi", "Minecraft"],
                         "Fantabosco", "Piazza delle Caramelle", 9, 12345);

const Cyber = new Person( "Cyber Genio", 21, true, 
                          ["Hacking", "Videogiochi", "Musica"],
                           "Milano", "Via Torino", 15, 20124);

// Ora verifichiamo quanto costruito stampando il tutto...

console.log(Gommarosa.nomeEcognome);
console.log(Gommarosa.età);
console.log(Gommarosa.studia);
console.log(Gommarosa.hobbies.join(", "));
console.log(`Residenza: ${Gommarosa.residenza.via} ${Gommarosa.residenza.civico}, ${Gommarosa.residenza.città} - ${Gommarosa.residenza.CAP}`);
console.log(Cyber.nomeEcognome);
console.log(Cyber.età);
console.log(Cyber.studia);
console.log(Cyber.hobbies.join(", "));
console.log(`Residenza: ${Cyber.residenza.via} ${Cyber.residenza.civico}, ${Cyber.residenza.città} - ${Cyber.residenza.CAP}`);