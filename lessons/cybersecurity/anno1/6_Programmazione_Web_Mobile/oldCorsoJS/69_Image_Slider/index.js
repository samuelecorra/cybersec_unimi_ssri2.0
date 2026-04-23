// IMAGE SLIDER

// Iniziamo definendo una variabile che contenga la nodelist restituitaci dal metodo query:
const slides = document.querySelectorAll('.slides img');

// Inizializziamo a 0 l'indice delle varie immagini:
let slideIndex = 0;

// E inizializziamo a 0 il nostro "timer":
let intervalId = null;

// FACOLTATIVO MA CONSIGLIATO:
// Aspettiamo che tutto il contenuto del DOM sia caricato, e solo dopo mostriamo la prima immagine:
document.addEventListener('DOMContentLoaded', initializeSlider);



// La prima funzione serve a popolare il web browser con la prima immagine quando la chiamiamo:
function initializeSlider() {

    // L'if iniziale serve a verificare che ci siano immagini da mostrare effettivamente:
    if (slides.length > 0) {
        slides[slideIndex].classList.add('displaySlide'); // Facciamo comparire la prima immagine
        intervalId = setInterval(nextSlide, 500); // Iniziamo a loopare all'infinito verso la 
        // prossima slide. Possiamo stampare a schermo l'ID per poi sapere come stopparlo:
        console.log(intervalId)
        } 
}

function showSlide(index) {

    if (index >= slides.length) {
        slideIndex = 0; // azzeriamo per tornare dall'ultima alla prima immagine
    } 
    else if (index < 0) {  // e se andiamo indietro col pulsante previous, dovremo anche gestire il
        // fatto che potremo anche tornare dalla prima all'ultima, che ha indice uguale alla length - 1
        slideIndex = slides.length - 1;
    }

    slides.forEach((slide) => {
        slide.classList.remove('displaySlide');
    });
    slides[slideIndex].classList.add('displaySlide');
}

function prevSlide() {
    clearInterval(intervalId);
    slideIndex--;
    showSlide(slideIndex);
}

function nextSlide() {
    slideIndex++;
    showSlide(slideIndex);
}