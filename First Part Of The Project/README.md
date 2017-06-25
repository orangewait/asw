# ASW
Repository di Architetture Software

## Descrizione del progetto:

Lo scopo di questo primo progetto è la realizzazione di una semplice applicazione distribuita, composta da alcuni servizi stateless, che comunicano tra loro tramite invocazioni remote con REST.

In particolare, il progetto deve essere composto almeno da:
 * Un servizio principale S, che può ricevere richieste da un client HTTP/REST esterno, ed in particolare da un qualunque browser web, e può effettuare richieste ai suoi servizi secondari (descritti qui sotto).
* Due o più servizi secondari (S1, S2, ...), che possono ricevere richieste dal servizio principale S e che possono scambiarsi richieste tra di loro.
  
In questo primo progetto, i diversi servizi vanno mandati in esecuzione tutti in uno stesso nodo (host), ma in application server Tomcat separati, collegati a porte diverse. In particolare, il servizio principale S è esposto sulla porta 8080, i servizi secondari sulle porte 8081, 8082, ...

Il progetto andrà realizzato usando Spring Boot. Ciascuno dei servizi è realizzato come una applicazione Spring Boot separata.

Ciascun servizio è realizzato come una applicazione separata e indipendente dagli altri servizi.

## Info generali:

Il progetto è costituito da una cartella principale "Software Architecture's Project".
Al suo interno sono contenute quattro sotto-cartelle:

* "InfoUniService", contenente il servizio principale dell'applicazione.
* "CourseService","FacultyService","UniversityService", ciascuna contenente un sottoservizio.

## Build:

Posizionarsi nella cartella principale del servizio InfoUniService ed eseguire:

    gradle build
    ./run-infoUni-service.sh

Posizionarsi nella cartella principale del servizio UniversityService ed eseguire:

    gradle build
    ./run-university-service.sh

Posizionarsi nella cartella principale del servizio FacultyService ed eseguire:

    gradle build
    ./run-faculty-service.sh
    
Posizionarsi nella cartella principale del servizio CourseService ed eseguire:

    gradle build
    ./run-course-service.sh
    

## Casi d'uso:


### Il servizio principali InfoUni:

Il servizio principale infoUni fornisce informazioni (casuali) relative ad università, facoltà e corsi ai suoi client. Il servizio infoUni fornisce tre operazioni:

**/_infoUni_/<_università>_/<_facoltà_>/<_corso_>** restituisce informazioni (casuali) sulla <_università_>, e informazioni(sempre casuali) per quella <_facoltà_> e per quel <_corso_>.

**/_infoUni_/<_università_>/<_facoltà_>** restituisce invece informazioni (casuali) sulla <_università_>, e informazioni (sempre casuali) generiche per quella <_facoltà_> includendo il totale dei crediti che comprende tale <_facoltà_>.

**/_infoUni_/<_università_>** restituisce invece informazioni (casuali) sulla <_università_>, e informazioni (sempre casuali) generiche per le facoltà contenute in quella <_università_>.

ad esempio:

http://localhost:8080/infoUni/romaTre/Ingegneria/Architetture
* romaTre è stata fondata nel 1088, Ingegneria ha un piano di studi di 13 esami con Architetture avente 6 crediti.

http://localhost:8080/infoUni/romaTre/Ingegneria
* romaTre è stata fondata nel 1088 e Ingegneria ha un piano di studi di 15 esami per un totale di 150 crediti.

http://localhost:8080/infoUni/romaTre
* romaTre è stata fondata nel 962 e ha 78 facoltà.

Il servizio infoUni va implementato come client di tre servizi secondari univeristy, faculty e course, con le caratteristiche descritte nel seguito.


### Il sottoservizio University:

Il servizio university fornisce informazioni (sempre casuali) relative all' università. Il servizio university fornisce una sola operazione:

**/_infoUni_/<_università_>** restituisce informazioni (casuali) sulla <_università_> relativa alla sua data di fondazione.

ad esempio:

http://localhost:8081/university/romaTre
* 1088


### Il sottoservizio Faculty:

il servizio faculty fornisce informazioni (sempre casuali) relative alle facoltà presenti in una certa università, e al loro numero di esami. il servizio faculty fornisce due operazioni:

**/_faculty_/<_università_>** restituisce informazioni (casuali) sul numero delle facoltà presenti in una certa <_università_>.

**/_faculty_/<_università_>/<_facoltà_>** restituisce informazioni (casuali) sul numero degli esami presenti in una certa <_facoltà_>.

ad esempio:

http://localhost:8082/faculty/romaTre
* 78

http://localhost:8082/faculty/romaTre/Ingegneria
* 13


### Il sottoservizio Course:

il servizio course fornisce informazioni (sempre casuali) relative ai corsi presenti in una certa università o facoltà. il servizio faculty fornisce due operazioni:

**/_course_/<_università_>/<_facoltà_>** restituisce informazioni (casuali) sul numero dei crediti totali di una certa <_facoltà_>.

**/_course_/<_università_>/<_facoltà_>/<_corso_>** restituisce informazioni (casuali) sul numero dei crediti di un certo <_corso_>.

ad esempio:

http://localhost:8083/course/romaTre/Ingegneria
* 180

http://localhost:8083/course/romaTre/Ingegneria/Architetture
* 6


Il servizio infoUni deve rispondere al suo client usufruendo dei servizi university, faculty e course integrando le loro risposte.
