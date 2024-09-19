FROM eclipse-temurin:17-alpine
LABEL maintainer="Team Dev Comunidad Digital Comercial"
LABEL dominio="DOMAINWS"
RUN JAVA_HOME=/opt/java/openjdk

EXPOSE ${PORT}

COPY /target/obl-his-ms-agrupacion-empledaor-azr-java-1.0-SNAPSHOT.jar /opt/java
CMD ["java","-jar","/opt/java/obl-his-ms-agrupacion-empledaor-azr-java-1.0-SNAPSHOT.jar"]
