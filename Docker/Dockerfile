FROM ubuntu:22.04

#RUN apt update
#RUN apt install -y software-properties-common
#RUN add-apt-repository ppa:deadsnakes/ppa -y
RUN apt update
RUN apt install -y python3 
# 3.8

RUN apt install -y openjdk-8-jdk
RUN apt install -y kotlin

WORKDIR /app

RUN touch hello.kt
RUN echo 'fun main() {println("Hello, World!")}' > hello.kt
RUN kotlinc hello.kt -d Hello.jar
RUN kotlin -classpath Hello.jar HelloKt

CMD
# more commands with ; in cmd