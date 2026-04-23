// La sintassi di importazione generica è:
// import { nomeFunzioni } from 'percorsoFileDaImportare';

import { pigreco, areaCerchio, circonferenza, volumeSfera} from './mathUtil.js';

console.log("Il valore di PI è: " + pigreco); // tutto ok
const circ1 = circonferenza(5);
console.log("La circonferenza di un cerchio di raggio 5 è: " + circ1.toFixed(2) + "cm");
console.log("L'area di un cerchio di raggio 5 è: " + areaCerchio(5).toFixed(2) + "cm2");
console.log("Il volume di una sfera di raggio 5 è: " + volumeSfera(5).toFixed(2) + "cm3");