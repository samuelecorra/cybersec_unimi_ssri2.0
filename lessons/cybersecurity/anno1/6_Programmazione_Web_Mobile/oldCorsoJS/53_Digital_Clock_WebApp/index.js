// Dobbiamo aggiornare sin da subito appena la pagina web parte:

function aggiornaOrologio() {

    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');

    // Se però volessimo il formato americano tipico di after meridiem (AM) /
    // post meridiem (PM), dovremmo fare così:
    let orarioEuropeo = false;
    if(orarioEuropeo) {
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    const timeString = `${hours}:${minutes}:${seconds}`;
    document.getElementById("orologio").textContent = timeString;

    } else {
    const ampm = hours >= 12 ? 'PM' : 'AM';
    const hours12 = hours % 12 || 12; // Convertiamo 0 in 12 per la mezzanotte

    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    const timeString = `${hours12}:${minutes}:${seconds} ${ampm}`;
    document.getElementById("orologio").textContent = timeString;

}

}

// Aggiorniamo l'orologio subito
aggiornaOrologio();

// Eseguiamo l'aggiornamento ogni secondo
setInterval(aggiornaOrologio, 1000);