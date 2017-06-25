########################################################
# Dockerfile per l'applicazione sentence / word service 
########################################################

FROM frolvlad/alpine-oraclejdk8 

ADD build/libs/UniversityService-0.0.1-SNAPSHOT.jar UniversityService.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", “UniversityService.jar"]
