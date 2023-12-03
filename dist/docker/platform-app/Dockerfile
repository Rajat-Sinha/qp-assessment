FROM amazoncorretto:17-alpine

USER root
WORKDIR /root/
COPY app.jar /root/
EXPOSE 8080
CMD ["java","-jar","/root/app.jar","--spring.config.location=/conf/application.properties"]
