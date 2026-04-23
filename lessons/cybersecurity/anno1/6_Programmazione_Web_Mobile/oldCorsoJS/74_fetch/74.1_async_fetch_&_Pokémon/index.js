// proviamo ora a migliorare la struttura della nostra fetch:

async function fetchData(url) {

  const pokemonName = document.getElementById('pokemonName').value.toLowerCase();

  try {
    const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${pokemonName}`);
    
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    
    const data = await response.json();

    const spriteDaMostrare = data.sprites.front_default;

    const imgElement = document.getElementById('pokemonImage');
    imgElement.src = spriteDaMostrare;
    imgElement.style.display = 'block';

  } catch (error) {
      console.error('Errore riscontrato:', error);
  }
}

// Se vogliamo anche fare in modo che premendo invio parte lo stesso il fetch,
// allora dobbiamo aggiungere:
  document.getElementById('pokemonName').addEventListener('keypress', function(event) {
       if (event.key === 'Enter') {
           fetchData();
       }
   });