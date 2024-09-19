<div>
    <img src="https://www.porvenir.com.co/o/Zona-Publica-Theme/images/ZonaPublica/logo_porvenir.svg" width="250px"/>
</div>

# Micro Servicio OblHisMsAgrupacionEmpledaorAzrJava
<p align="center">
<a href="#"><img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/></a>
<a href="#"><img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" /></a>
<a href="#"><img src="https://img.shields.io/badge/Microsoft_Azure-0089D6?style=for-the-badge&logo=microsoft-azure&logoColor=white" /></a>
<a href="#"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" /></a>
<a href="#"><img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" /></a>
</p>
---

Servicio que permite validar información OblHisMsAgrupacionEmpledaorAzrJava completar por cada desarrollador
A continuacion se relacionan cada uno de los metodos que presenta este servicio:

 ````
    http post
    Hearder Required: [X_RQUID; X_CHANNEL; X_GOV_ISSUE_IDENT_TYPE; X_IDENT_SERIAL_NUM; X_COMPANY_ID; X_IP_ADDR;]
    Request: 
 ````
 ````
    Response: 
 ````

 ````
    http post
    Hearder Required: [X_RQUID; X_CHANNEL; X_GOV_ISSUE_IDENT_TYPE; X_IDENT_SERIAL_NUM; X_COMPANY_ID; X_IP_ADDR;]
    Request: 
 ````
 ````
    Response: 
 ````
# Preliminar
* apache-maven-3.8.4 o superior
* Java 11
* Coneccion/usuario a Base De Datos Azure SQL Server
* IDe IntelliJ IDEA 2019.3.5 o superior
* Validar los varibles de entorno requeridas.

Este servicio requiere que se establecezcan las siguientes variables de entorno, para ellos cuenta con un archivo de properties para cada ambiente.
````
    1.  ENV = dev|qa|prod Ambiente a ejecutar
    2.  CORS = Indica la restriccion de cors para su consumo.
    3.  DATABASEURL = Indica la url de la base de datos a la que se conectara.
    4.  DATABASEUSER = Usuario de la base de datos a la que se conectara.
    5.  DATABASEPASSWORD = Password de la base de datos a la que se conectara.
    6.  DATABASEDRIVER = Nombre principal de la clase asociada al driver de coneccion.
    7.  DATABASEPOOLSIZE = tamaño del pool de conecciones a la base de datos, por ejemplo 10.
    8.  DATABASETIMEOUT = timeout de coneccion a la base de datos, por ejemplo 15s.
    9.  ACTIVE_LOG = false|true Indica si se registra o no se registra logs.
    10. END_POINT_WS_LOG = Deberia presentar el endpoint para el registro de logs.
    11. PORT = Puerto de atencion del servicio    
    12. APPVER = Version de la aplicacion
    13. VERDATE = Fecha de versionamiento
    14. CACHE = Manejo de cache
    15. TIMEOUTLOGS = Tiempo de conexión y respuesta al servicio de logs 
````

Ubicado en la carpeta del proyecto realice la ejecucion de los comando de compilacion de maven.  Previamente valide que compresen las variables de entorno requeridas.
```
    mvn clean install
```
Si la compilacion es correcta, se deberia generar el artefactor .jar que se podra validar localmente seteando las variables de entorno correspondientes o actualizando los archivos de propiedades.
```
    java  -jar ./target/obl-his-ms-agrupacion-empledaor-azr-java-1.0-SNAPSHOT.jar --ENV=dev --PORT=8080 --APPVER=1.0.0 --VERDATE=20221212 --DATABASEURL=jdbc:sqlserver://10.160.150.74:1433;database=dbptcdcafiliaciones;encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30; --DATABASEUSER=user@azue-pv-cdc-pt-sql-db-01 --DATABASEPASSWORD=pass --DATABASEDRIVER=com.microsoft.sqlserver.jdbc.SQLServerDriver --DATABASEPOOLSIZE=1 --DATABASETIMEOUT=5s --ACTIVE_LOG=true --END_POINT_WS_LOG=http://localhost:8088/cdc/general/v1/eventos/trace/ --CORS=* --CACHE=* --TIMEOUTLOGS=5000
```

# Preparacion Despliegue En Kubernetes

A continuación, se relaciona una serie de pasos que se debería seguir para generar una imagen de docker con este servicio desplegado. Todas las tareas de generación de imágenes, transmisión de estas imágenes y despliegue en un AKS se realiza desde un servidor bastión que permite el acceso a los componentes cloud.
Luego de haber ubiucado en el bastion la informacion del proyecto de Spring boot, se debe generar el artefacto del mismo mediante el siguiente comando, previa ubicacion dentro de la raiz del proyecto.
```
    En caso de que el contenedor este corriendo se detiene el mismo.
    docker stop maven-openjdkCompileProject
    
    Con este comando se realiza la ejecucion de un contedor basado en una imagen que contiene java 11 y maven configurado y permite la compilacion y creacion del artedacto. 
    docker run -it --memory=2048m --memory-reservation=256m --rm --name maven-openjdkCompileProject -v "$(pwd)"/:/home/data/coderepository -w /home/data/coderepository adoptopenjdk/maven-openjdk11:latest mvn clean compile package

```

Este servicio cuenta con un archivo de Dockerfile que permite sean dockerizado y generar una imagen a partir del mismo, a fin de posteriormente poderlo desplegar en un entorno de kubernetes.  En los siguientes pasos se describirán las acciones necesarias para llevar este servicio a un entorno de kubernetes.
```
    Eliminar Imagen en caso de existir
    docker image rm azuepvgoydvpsptacr.azurecr.io/obl-his-ms-agrupacion-empledaor-azr-java:latest -f
    
    Creaciòn Image
    docker build -t azuepvgoydvpsptacr.azurecr.io/obl-his-ms-agrupacion-empledaor-azr-java:latest --no-cache .  

```
En los siguientes pasos se describen las acciones para realizar el traslado de la imagen de generada en docker del bastión o del entorno de devops al manejador de images (DockerHub o ACR)
Ejecuciòn del comando de login hacia azure.
```
    az login 
```
Autenticarse ante el ACR donde se espera almacerna la imagen generada, el primer comando al ejecutarlo retorna un token por pantalla, el cua se debe reemplazar en el segunto comando como valor del parametro -p
```
    az acr login -n azuepvgoydvpsptacr.azurecr.io --expose-token
    docker login azuepvgoydvpsptacr.azurecr.io -u 00000000-0000-0000-0000-000000000000 -p eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlFXRkc6TTIzNzpLMkhBOjMzREs6NVBHVDpDQTNNOkRCTjM6WE9RQjpBNVFCOkk2M1E6QVQzUTpOUUFLIn0.eyJqdGkiOiJjMTJmYTczYS1mZWY3LTRkMjYtYjljYS1kN2M1YTQ4MzU5MTMiLCJzdWIiOiJTSVNORU1BQHBvcnZlbmlyLmNvbS5jbyIsIm5iZiI6MTY3MzU0NDkwOSwiZXhwIjoxNjczNTU2NjA5LCJpYXQiOjE2NzM1NDQ5MDksImlzcyI6IkF6dXJlIENvbnRhaW5lciBSZWdpc3RyeSIsImF1ZCI6ImF6dWVwdmdveWR2cHNwdGFjci5henVyZWNyLmlvIiwidmVyc2lvbiI6IjEuMCIsInJpZCI6IjA2MDllOTZkY2YwZjQ0N2U5ZGQ4MmVmMWVjODM3ZWM3IiwiZ3JhbnRfdHlwZSI6InJlZnJlc2hfdG9rZW4iLCJhcHBpZCI6IjA0YjA3Nzk1LThkZGItNDYxYS1iYmVlLTAyZjllMWJmN2I0NiIsInRlbmFudCI6IjEwYTc2NzEyLTk0ZjYtNDZhMi05MTU1LTMxYmQ4Yjc2ZjkzNyIsInBlcm1pc3Npb25zIjp7IkFjdGlvbnMiOlsicmVhZCIsIndyaXRlIl0sIk5vdEFjdGlvbnMiOm51bGx9LCJyb2xlcyI6W119.D1936AcI4VwvCNfSDG9Uq2fjG2n7Q40REfJv3l7L9rDR0FR1ul6b7c5-mvPyroYckG5wvUL06REzgu-AD2Yps5yRJa8KfsWD99YuLwa2ToJ7B5FptorrA4y7F6AnFauiAzZrLXF7AAI-awuct6ARVejgjtgKDOQXHEU1dEOFioGd1ZkyebbfG9IanB7yWYtweKIAkjKfr-shGRbRoN4rMsccSJfjZPnDKjnQTFlBSf8RG_gKDEiUAzW7xz5_cDJUmBiBx4kLyaWiCIc0S1imCNDqAvwLGMNfq3Ibrz73tYKAa3VHYBfzjps2i3zCXDIdPtEqa4qs2K6gVweNiuL1oA
```
Luego de haber garantizado la autenticación ante el ACR, se puede proceder a realizar la trasmisión de la imagen al ACR
```
   docker push azuepvgoydvpsptacr.azurecr.io/obl-his-ms-agrupacion-empledaor-azr-java:latest
   az acr repository list --name azuepvgoydvpsptacr.azurecr.io
   az acr repository show-tags --name azuepvgoydvpsptacr.azurecr.io --repository OblHisMsAgrupacionEmpledaorAzrJava
```

A continuacion se relacionan las acciones requeridas para  efectuar el despliegue de la imagen del servicio que fue trasmitida al ACR en el paso anterior. 
Con las siguientes instruccion se podra efectuar el proceso despliegue de la imagen genera en el AKS y la creacion del Pod, Service y endpoint a nivel del AKS, en caso de el pod falle en su creacion, se puede proceder a eleminar y volver a lanzar la ejecucion.
```
    Comando 0 => kubectl delete deployment obl-his-ms-agrupacion-empledaor-azr-java-deployment  -n  NAMESPACE_AKS

    Comando 1 => sed -i 's/REPLICASET/1/g' ./k8s/obl-his-ms-agrupacion-empledaor-azr-java-deployment.yaml | sed -i 's/NAME_ARTIFACT/obl-his-ms-agrupacion-empledaor-azr-java/g' ./k8s/obl-his-ms-agrupacion-empledaor-azr-java-deployment.yaml | sed -i 's/VERSION/latest/g' ./k8s/obl-his-ms-agrupacion-empledaor-azr-java-deployment.yaml | sed -i 's/GENERACION/20221228102900/g' ./k8s/obl-his-ms-agrupacion-empledaor-azr-java-deployment.yaml | kubectl apply -f ./k8s/obl-his-ms-agrupacion-empledaor-azr-java-deployment.yaml
    
    Comando 2 (Verifar Deployment Desponibles) => kubectl get deployment -A
    
    Comando 3 (Verificar Pods Disponibles y su estado) => kubectl get pods -A
    
    Comando 4 (Verificar Services Disponibles y su estado) => kubectl get service -A

    Comando 5 (Si se requiere consulta el log del pod por que el servicio fallo se puede hacer uso del id de pod y replazando en este comando) => kubectl logs wsinitialcdc-deployment-7b85cc6857-8r7zh -n NAMESPACE_AKS
```
La regla de enrutamiento es una configuracion que se debe realizar a nivel del archivo de configuracion del ingress controller, por lo que aquì se propone generar obtener el archivo de configuracion del ingress controller, descargalo localmente y adicionar la ruta del nuevo servicio.
``` 
    1. Se ejecuta el siguiente comando para extraer la definicion del ingress controller
    kubectl get ingress -n NAMESPACE_AKS  -o yaml > ../DEF-INGRESS-CONTROLLER-AZUE-PV-CDC-PT-AKS-01.yaml
    
    2. Se adiciona a las reglas de enrutamiento el siguiente script, importante en vi oprimir la tecla insert. 
    - backend:
         service:
           name: obl-his-ms-agrupacion-empledaor-azr-java-services
           port:
              number: 8080
         path: /cdc/general/v1/object_service_demo(/|$)(.*)
         pathType: Prefix

    3. Se guarda el archivo y se vuelve a ubicar dicho archivo en el bastion para su posteior aplicacion.

    4. kubectl apply -f ../DEF-INGRESS-CONTROLLER-AZUE-PV-CDC-PT-AKS-01.yaml
```
Ahora se podria realizar la validacion del servicio, para ello se puede hacer uso del comando relacionado a continuacion:
```
    curl --insecure https://secureapicdcpt.afpporvenir.com/cdc/general/v1/empleadores/api-health/ping | jq   
```

Posterior al despliege del servicio en el Ingress Controller, se debe proceder a realizar la configuracion del servicio en el ApiManagment.  Para ello al momento de la construccion de este documento dicha configuracion se debe realizar con el administrado del equipo Cloud.


