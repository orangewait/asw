########################################################
# Dockerfile per l'applicazione infoUniService
########################################################

FROM frolvlad/alpine-oraclejdk8 

ADD build/libs/InfoUniService-0.0.1-SNAPSHOT.jar InfoUniService.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", “InfoUniService.jar"]


