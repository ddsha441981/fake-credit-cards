FROM maven:3.9.6-openjdk-17
LABEL authors="Deendayal Kumawat"

WORKDIR /fake-credit-generater-master
COPY . .
RUN mvn clean install
CMD spring-boot:run

ENTRYPOINT ["top", "-b"]