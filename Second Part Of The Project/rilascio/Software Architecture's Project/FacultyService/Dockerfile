########################################################
# Dockerfile per l'applicazione sentence / word service 
########################################################

FROM frolvlad/alpine-oraclejdk8 

ADD build/libs/FacultyService-0.0.1-SNAPSHOT.jar FacultyService.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", “FacultyService.jar"]
