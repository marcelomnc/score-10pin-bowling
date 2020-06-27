# score-10pin-bowling

This is a command line app that parses/processes/builds/calculates all the data (pinFalls and scores) for the 10 frames in a bowling game played by one or more players.

The ins for the app are a text file in which every line has two tab-separated fields:
1. Player Name
2. Number Of Pins Knocked Down

In the context of this app, the mentioned line (with the two fields) is called a Player Chance. Hence, in this same context, the file that contains all this data is called Player Chances File.

## Installation

Clone this repository

```bash
git clone https://github.com/marcelomnc/score-10pin-bowling
```

Cd into the repository folder

```bash
cd score-10pin-bowling
```

Build the project using maven

```bash
mvn install
```

Maven will create a folder with the name 'target'. Inside it you will find this application's executable jar file.

```bash
score-10pin-bowling-1.0-SNAPSHOT.jar
```

## Running

To run this application you will need a text file with the Player Chances Data that must be parsed and processed. You can execute it passing the mentioned file path as the first and unique argument like:

```bash
java -jar score-10pin-bowling-1.0-SNAPSHOT.jar [PATH_TO_YOUR_FILE]
```
