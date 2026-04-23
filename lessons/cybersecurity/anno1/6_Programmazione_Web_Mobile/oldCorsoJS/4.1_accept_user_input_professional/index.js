// Come accettare input dall'utente:

// 1. Easy Way = window prompt
// 2. Professional Way = HTML form/textbox/ whatever we want
// con anche un bottone per inviare il dato

// 2:
let username;

document.getElementById("submitButton").onclick = function() {
    username = document.getElementById("inputNome").value;
    console.log("Ciao " + username + "! Benvenuta su Hypixel!");
    // E modifichiamo anche il titolo h1:
    document.getElementById("saluto").textContent = 
                                "Ciao " + username + "! Benvenuta su Hypixel!";
    }