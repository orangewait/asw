########################################################
# Dockerfile per l'applicazione infoUni
########################################################

FROM frolvlad/alpine-oraclejdk8 

ADD build/libs/CourseService-0.0.1-SNAPSHOT.jar CourseService.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-Xmx128m", "-Xms128m"]

CMD ["-jar", "CourseService.jar"]
