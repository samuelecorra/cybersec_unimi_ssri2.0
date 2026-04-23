// Eventi Mouse: sono tutti quegli eventi che si attivano
// quando l'utente interagisce con il mouse, come click,
// doppio click, mouse over, mouse out, ecc.

// In JavaScript abbiamo una keyword ben specifica per
// gestire gli eventi legati al mouse: "addEventListener".

// evenListener = "ascolta" per specifici eventi in modo da
// creare pagine web con eventi interattivi.

// SINTASSI GENERICA:
// .addEventListener(event, callback);

const miaBox = document.getElementById("box");

function changeColorOnClick(event) {
    miaBox.style.backgroundColor = "tomato";
    miaBox.style.color = "white";
    miaBox.textContent = "Porcavacca che male! 🤒";
}
miaBox.addEventListener("click", changeColorOnClick);

// Sintassi ovviamente snellibile con un'anonymous/arrow function!

miaBox.addEventListener("mouseover", (event) => {
    miaBox.style.backgroundColor = "yellow";
    miaBox.style.color = "red";
    miaBox.style.fontSize = "3rem";
    miaBox.textContent = "Lo so che vuoi farmi male! 😒";
});

miaBox.addEventListener("mouseout", (event) => {
    miaBox.style.backgroundColor = "lightgreen";
    miaBox.style.color = "black";
    miaBox.style.fontSize = "4.5rem";
    miaBox.textContent = "Cliccami! 🤩";
});

const mioBottone = document.getElementById("bottone");

// Riproviamo sostituendo il nome dal box al bottone:
mioBottone.addEventListener("click", changeColorOnClick);

// Sintassi ovviamente snellibile con un'anonymous/arrow function!

mioBottone.addEventListener("mouseover", (event) => {
    miaBox.style.backgroundColor = "yellow";
    miaBox.style.color = "red";
    miaBox.style.fontSize = "3rem";
    miaBox.textContent = "Lo so che vuoi farmi male! 😒";
});

mioBottone.addEventListener("mouseout", (event) => {
    miaBox.style.backgroundColor = "lightgreen";
    miaBox.style.color = "black";
    miaBox.style.fontSize = "4.5rem";
    miaBox.textContent = "Cliccami! 🤩";
});

// Ci sarebbe anche il doppio click!
mioBottone.addEventListener("dblclick", (event) => {
    miaBox.style.backgroundColor = "purple";
    miaBox.style.color = "white";
    miaBox.style.fontSize = "4rem";
    miaBox.textContent = "Hai raddoppiato il clic! 🤯";
});