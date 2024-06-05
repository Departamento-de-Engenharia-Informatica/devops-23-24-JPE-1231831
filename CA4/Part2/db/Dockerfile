FROM ubuntu:22.04
LABEL authors="inesc"

RUN apt-get update -y

RUN apt-get install -y openjdk-17-jdk
RUN apt-get install wget -y

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app/

RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar

EXPOSE 8082
EXPOSE 9092

CMD java -cp ./h2-2.2.224.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists