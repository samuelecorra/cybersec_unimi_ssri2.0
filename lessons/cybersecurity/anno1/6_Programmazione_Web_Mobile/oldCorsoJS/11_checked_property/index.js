// La proprietà checked controlla lo stato di una checkbox o di un radio button

const boxDragonTrainer = document.getElementById("boxDragonTrainer");
const radioDragonTrainer = document.getElementById("radioDragonTrainer");
const radioDragonTrainer2 = document.getElementById("radioDragonTrainer2");
const radioDragonTrainer3 = document.getElementById("radioDragonTrainer3");
const bottonePerEsito = document.getElementById("bottonePerEsito");
const submitEsito = document.getElementById("submitEsito");

bottonePerEsito.onclick = function() {
    let messaggio = "Hai scelto ";

    if (boxDragonTrainer.checked) {
            messaggio += "di diventare un'addestratrice di draghi. ";
        
            if (radioDragonTrainer.checked) {
                messaggio += "E non di un drago qualsiasi - di Furia Buia.";
            } else if (radioDragonTrainer2.checked) {
                messaggio += "E non di un drago qualsiasi - di Furia Chiara.";
            } else if (radioDragonTrainer3.checked) {
                messaggio += "E non di un drago qualsiasi - di Uncinato Mortale.";
            }
        }   
        else messaggio += "di non addestrare draghi.";

    submitEsito.textContent = messaggio;
}