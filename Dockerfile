FROM openjdk:12 AS build  
COPY . /usr/src/app/src  
WORKDIR /usr/src/app/src
RUN ./mvnw clean install

FROM openjdk:12 
COPY --from=build /usr/src/app/src/target/catalog-0.0.1-SNAPSHOT.jar /usr/app/catalog-0.0.1-SNAPSHOT.jar
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/catalog-0.0.1-SNAPSHOT.jar"]  