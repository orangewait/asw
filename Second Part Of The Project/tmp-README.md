# ASW - Secondo progetto

* [Descrizione del secondo progetto](#descrizione-del-secondo-progetto)

* [Build](#build)

* [Uso dei servizi](#uso-dei-servizi)



## Descrizione del secondo progetto

In questo progetto è stata realizzata e rilasciata in un ambiente di esecuzione distribuito virtuale ( docker swarm ) un'applicazione SpringCloud.

Questa applicazione, basata su un'applicazione distribuita SpringBoot realizzata nel [primo progetto](https://github.com/valeita/asw/tree/master/First%20Part%20Of%20The%20Project), è composta da vari servizi stateless che comunicano fra loro tramite invocazioni remote REST.

Lo scopo dell'applicazione è quello di fornire ai suoi client informazioni casuali relative ad università, facoltà e corsi di studio.

### Tecnologie 

Sono state utilizzate alcune librerie del progetto Spring Cloud Netflix, quali:

* **Eureka** — fornisce funzionalità di service discovery.
* **Ribbon** — fornisce funzionalità di load balancing.
* **Feign** — semplifica la gestione nel codice delle chiamate REST.
* **Hystrix** — un'implementazione del pattern circuit breaker; permette di gestire agevolmente eventuali fallimenti dei servizi.
* **Zuul** — semplifica il mapping tra path/URI dei servizi 

<!--
Parte fondamentale per la realizzazione della seconda parte del progetto, è l'utilizzo di dipendenze starter, che permette di utilizzare i strumenti che Spring Cloud che mette a disposizione.*/
-->

### Applicazione e servizi

L'applicazione è composta da :

* Un servizio principale, *InfoUniService*, che può ricevere richieste da un client HTTP/REST esterno ( ed in particolare da un qualunque browser web ) e può effettuare richieste ai suoi servizi "secondari".

* Vari servizi "secondari" ( *CourseService*, *FacultyService* e *UniversityService* ) che possono ricevere richieste dal servizio principale e scambiarsi richieste tra di loro.

* Un server Eureka permette ai servizi di scoprirsi tra loro.
  
Ciascun servizio è realizzato come un'applicazione Spring Boot ( integrata con le tecnologie Spring Cloud ) separata ed indipendente dalle altre. Infatti ogni servizio viene mandato in esecuzione ( su un application server Tomcat ) all'interno di un diverso container docker.

Il servizio principale, *InfoUniService* è esposto sulla porta 8080 dell'host, il server Eureka è esposto sulla porta 8761 mentre gli altri servizi sono esposti su delle porte casuali. Questi ultimi riescono a comunicare con il servizio principale grazie ad Eureka.<span style="color:red"> **(NON È IL CONTRARIO?)**</span>

### Note

Il codice relativo a Eureka e Ribbon nel servizio principale *InfoUniService* è stato volontariamente commentato (e non rimosso) per lasciare traccia del nostro lavoro, come richiesto dalle specifiche.
Infatti il codice del primo progetto è stato modificato e sviluppato includendo incrementalmente le varie tecnologie, pur sapendo in anticipo che l'utilizzo di Feign le avrebbe reso superflue. <span style="color:red">**(QUA FORSE È MEGLIO FARE RIFERIMENTO PROPRIO A RIBBON. EUREKA INVECE?)**</span>

### Struttura (cartelle)

Il progetto è costituito da una cartella principale "Software Architecture's Project".
Al suo interno sono presenti cinque cartelle:

* "InfoUniService", contenente il servizio principale dell'applicazione.
* "CourseService","FacultyService","UniversityService", ciascuna contenente l'omonimo servizio.
* "EurekaService", contenente il server Eureka.
---



## Build

### Compilare ogni servizio
 
Ogni servizio ha un'omonima cartella all'interno di *Software Architecture's Project*. 

Lo script `./build-all-projects.sh` lancia `gradle build` in ognuna di esse.

### Creare le immagini docker

Lo script `./build-all-images.sh` lancia `docker build` in ogni cartella.

<span style="color:red">**(QUI DOVREMMO DIRE CHE LE APPLICAZIONI COMPILATE POSSONO FUNZIONARE ANCHE PER CONTO LORO, IN LOCALE. IDEM PER LE IMMAGINI DOCKER. E POI MAGARI DOVREMMO AGGIUNGERE CHE USIAMO `./push-all-images.sh` e `./start...sh` e `./stop...sh` PER PUSHARE, AVVIARE E STOPPARE I SERVIZI SULLO SWARM)**</span>

---



## Uso dei Servizi
### Il servizio principale InfoUni

Il servizio principale infoUni fornisce informazioni (casuali) relative ad università, facoltà e corsi ai suoi client. Il servizio infoUni risponde a richieste nella forma :

**/_infoUni_/<_università>_/<_facoltà_>/<_corso_>** restituisce informazioni ( casuali ) sulla <_università_>, e informazioni( ancora casuali ) per quella <_facoltà_> e per quel <_corso_>.

**/_infoUni_/<_università_>/<_facoltà_>** restituisce invece informazioni ( casuali ) sulla <_università_>, e informazioni ( ancora casuali ) generiche per quella <_facoltà_> includendo il totale dei crediti che comprende tale <_facoltà_>.

**/_infoUni_/<_università_>** restituisce invece informazioni ( casuali ) sulla <_università_>, e informazioni ( ancora casuali ) generiche per le facoltà contenute in quella <_università_>.

ad esempio:

http://localhost:8080/infoUni/romaTre/Ingegneria/Architetture
* romaTre è stata fondata nel 1088, Ingegneria ha un piano di studi di 13 esami con Architetture avente 6 crediti.

http://localhost:8080/infoUni/romaTre/Ingegneria
* romaTre è stata fondata nel 1088 e Ingegneria ha un piano di studi di 15 esami per un totale di 150 crediti.

http://localhost:8080/infoUni/romaTre
* romaTre è stata fondata nel 962 e ha 78 facoltà.

Grazie a Zuul è stato possibile fornire all'applicazione un unico punto di accesso ovvero quello sulla porta 8080 dell'host. Questo impedisce l'accesso diretto ai servizi "secondari" (a meno che non si scopra la loro porta casuale). <span style="color:red">**(FORSE DA SCRIVERE MEGLIO)**</span>

È stato impostato il prefisso **/_info_** per distinguere fra l'invocazione di un servizio "secondario" e quellla del servizio principale.
Ogni sottoservizio è quindi mappato su :
**/_Nome_Servizio_**.
L'invocazione di un servizio "secondario" tramite Zuul ha acquisito dunque questa forma : **/_info_/_Nome_Sottoservizio_/<..>/<..>/..**

### Il servizio University

Il servizio university fornisce informazioni ( casuali ) relative all' università. Il servizio university fornisce una sola operazione:

**/_info_/_university_/<_università_>** restituisce informazioni (casuali) sulla <_università_> relativa alla sua data di fondazione.

ad esempio:

http://localhost:8080/info/university/romaTre
* 1088


### Il servizio Faculty

il servizio faculty fornisce informazioni ( casuali ) relative alle facoltà presenti in una certa università, e al loro numero di esami. il servizio faculty fornisce due operazioni:

**/_info_/_faculty_/<_università_>** restituisce informazioni (casuali) sul numero delle facoltà presenti in una certa <_università_>.

**/_info_/_faculty_/<_università_>/<_facoltà_>** restituisce informazioni (casuali) sul numero degli esami presenti in una certa <_facoltà_>.

ad esempio:

http://localhost:8080/info/faculty/romaTre
* 78

http://localhost:8080/info/faculty/romaTre/Ingegneria
* 13


### Il servizio Course

il servizio course fornisce informazioni (sempre casuali) relative ai corsi presenti in una certa università o facoltà. il servizio faculty fornisce due operazioni:

**/_info_/_course_/<_università_>/<_facoltà_>** restituisce informazioni (casuali) sul numero dei crediti totali di una certa <_facoltà_>.

**/_info_/_course_/<_università_>/<_facoltà_>/<_corso_>** restituisce informazioni (casuali) sul numero dei crediti di un certo <_corso_>.

ad esempio:

http://localhost:8080/info/course/romaTre/Ingegneria
* 180

http://localhost:8080/info/course/romaTre/Ingegneria/Architetture
* 6

