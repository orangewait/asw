########################################################
# Dockerfile per l'applicazione infoUni / eureka server 
########################################################

FROM frolvlad/alpine-oraclejdk8 

ADD build/libs/EurekaService-0.0.1-SNAPSHOT.jar EurekaService.jar

EXPOSE 8761

ENTRYPOINT ["/usr/bin/java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", "EurekaService.jar"]

