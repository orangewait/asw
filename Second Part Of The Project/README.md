# ASW - Seconda parte del progetto
Repository del progetto di gruppo per l'esame di Architetture Software.
Fork di https://github.com/valeita/asw



## Build :

Posizionarsi nella cartella principale :

``` cd asw/Second\ Part\ Of\ The\ Project/rilascio/Software\ Architecture\'s\ Project/ ```

Fare il gradle clean di tutti i servizi :
``` ./clean-all-projects.sh```

Fare il gradle build di tutti i servizi :
```./build-all-projects.sh```
 
Avviare ogni singlo progetto, come in start-all-projects-noContainer.sh (funziona solo se avete installato lxterminal).
In alternativa lanciare manualmente ```./run-service-xxx``` in ogni sottocartella


## Appunti 

* Sono stati momentaneamente rimosse da build-all-projects.sh le istruzioni relative a docker

* Sono stati commentati i vecchi metodi in InfoUniServiceController.java (dalla riga 73 in poi)

* Hystrix non è magico ma va magicamente a pescare i metodi dentro i xxxServiceController.java

* EurekaService dava un sacco di errori perchè ogni server eureka è anche un client eureka, e come ogni client che non conosce il proprio server, sputa errori.
È stato sufficiente modificare application.yml seguendo le indicazioni per la modalità in standalone sul sito 

* In UniversityClient.java ho cambiato il @RequestMapping levando le parentesi graffe attorno a university.
Con le graffe non veniva riconosciuto da Hystrix che al suo posto invocava il metodo di fallback 

* Sono stati cancellati i parametri inutili in tutti i metodi in serviceClient/xxxService.java

* In InfoUniServiceController.java è stato sostituito InfoUniServiceImpl con InfoUniService, come nelle slides.
Va dichiarata una variabile interfaccia, non la sua implementazione.

* Nota : In xxxServiceController.java i @RequestMapping(value={a}/{b}/{c}) sono semplici segnaposto, ovvero indicano che quel sottoservizio richiede un certo numero di parametri e non il loro valore.
C'era un problema di mapping fra il servizio principale e i vari sottoservizi. Tutti i @RequestMapping dentro xxxServiceController.java sono stati riportati nella forma che avevano i sottoservizi nel primo progetto ( vedi i [casi d'uso](https://github.com/zubiak/asw/tree/master/First%20Part%20Of%20The%20Project) ), evitando così ad hystrix di invocare i metodi di fallback.
Non sono stati modificati i vari @RequestMapping in ServiceClient/xxxService.java

* Ho quache dubbio sull'uso di zuul visto che da nessuna parte abbiamo URI nella forma. http://localhost:8080/info/university/anno 

Se, come sembra, ora i singoli servizi funzionano per bene...domani potremo divertirci con docker e lo swarm
