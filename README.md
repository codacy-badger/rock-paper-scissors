[![CodeFactor](https://www.codefactor.io/repository/github/joaofranciscosantos/rock-paper-scissors/badge)](https://www.codefactor.io/repository/github/joaofranciscosantos/rock-paper-scissors)
[![Build Status](https://travis-ci.org/joaofranciscosantos/rock-paper-scissors.svg?branch=master)](https://travis-ci.org/joaofranciscosantos/rock-paper-scissors)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9f8854cde121478bb15047df0e1d1170)](https://www.codacy.com/app/joao.francis.santos/rock-paper-scissors?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=joaofranciscosantos/rock-paper-scissors&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/joaofranciscosantos/rock-paper-scissors/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/joaofranciscosantos/rock-paper-scissors?targetFile=pom.xml)
# Rock Paper Scissors
This is a Rock, Paper, Scissors multiplayer game.
<br/>
The player's name is registered after the first bet. Choose from 0 to 2 to make a bet. Press "q" to exit.
## Prerequisites
Java 8
<br/>
Maven

## Build
```
> mvn install
```
## Test
```
> mvn test
```
## Run Server
```
> java -jar target/AppServer.jar
```
## Run Client(s)
Play the game based on your input.
```
java -jar target/AppClient.jar
```
The player 'Gil' bets '1' ten times.
```
java -jar target/AppClient.jar Gil 1 10
```
