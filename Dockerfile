#Start with a base image containing Java runtime
FROM openjdk:17-jdk as build

#Add Maintainer info
LABEL maintainer="Kelvin Iseh <kelviniseh25@gmail.com>"

WORKDIR application

#The application's jar file
ARG JAR_FILE

#Add the application's jar to the container
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-jdk
WORKDIR application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./
ENTRYPOINT ["java","-cp","app:app/lib/*","com.optimagrowth.license.LicenseServiceApplication"]

# Mental mode
#Maven makes target/your.jar
#Fabric8 builds the image, using dockerFileDir as context → COPY pulls from there.
#ARG passes the jar name in.
#Stage 1 unpacks → Stage 2 copies from build.