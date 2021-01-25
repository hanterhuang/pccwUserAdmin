From java:8
ADD pccwUserAdmin-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 18081    
ENTRYPOINT ["java","-jar","/app.jar"]