#!/bin/bash

echo -e '\nStarting terminals and services...\n'

lxterminal -l --working-directory=EurekaService/ -e ./run-eureka-service.sh && sleep 2
lxterminal -l --working-directory=CourseService/ -e ./run-course-service.sh && sleep 1
lxterminal -l --working-directory=FacultyService/ -e ./run-faculty-service.sh && sleep 1
lxterminal -l --working-directory=UniversityService/ -e ./run-university-service.sh && sleep 1
lxterminal -l --working-directory=InfoUniService/ -e ./run-infoUni-service.sh

echo -e '\nDone!\n'
