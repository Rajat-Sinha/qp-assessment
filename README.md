# Grocery Booking Service

* Java Version: 17
* Spring Boot Version: 3.2.0
* Database: Postgres

## Steps to run the application on a local
* Create platform-grocery-management on the database
* Change the database credentials from the path application.properties file
* After the application starts, Use swagger to hit the API's here is the URL - http://localhost:8080/swagger-ui.html

## Steps to build the docker image, Run the below Command
* sh scripts/ci_build_platform_service.sh
* sh scripts/ci_copy_platform_service_artifacts.sh
* sh .ci/docker-image-build-platform-app.sh