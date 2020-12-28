# ProgettoOOBD
 Progetto Object Oriented e Basi di Dati 2020/2021


Appunti SQL:

Nella sequenza che riguarda i voli(sequenza_voli) non è stato specificato un valore massimo. Questo lascia il compito alla base di dati di impostarlo, e di default lo imposta al valore massimo raggiungibile, ovvero quello di un int(9223372036854775807)

La funzione validificaData() è una funzione che non riceve niente in input ma che viene automaticamente richiamata da un trigger che effettua un check sui valori delle date inserite. Questo permette al sistema di rilevare quando si cerca di creare un volo antecedente alla data odierna, operazione inutile se non dannosa.