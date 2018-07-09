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