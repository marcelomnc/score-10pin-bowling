# score-10pin-bowling

This is a command line app that parses/processes/builds/calculates all the data (**pin falls** and **scores**) for all the 10 frames in a bowling game played by one or more players.

The ins for the app are a text file in which every line has two tab-separated fields:

1. Player Name
2. Number Of Pins Knocked Down

In the context of this app, the mentioned line (with the two fields) is called a **Player Chance**. Hence, in this same context, the file that contains all this data is called **Player Chances File**.

## Validations

The app validates every line of the **Player Chances File** with the following rules:

1. Line has no field separator
2. Line has not 2 fields only
3. Line has a value for 'knocked down pins' field that is not between 0 and 10
4. Line has a value for 'knocked down pins' field that is not a number neither an F (F stands for Foul)

If the app finds lines with errors, it will print an **error report** with the following info:

1. Line number
2. Line
3. Error message

For a **Player Chances File** with these lines:

        1.dsasd
        2.
        3.67723	ughgg
        4.
        5.              
        6.			   
        7.jdjsdhaak
        8.
        9.Marcus	10	12
        10.Marcus	10
        11.Marcus	10
        12.Marcus	10
        13.Marcus	10
        14.8999	89
        
The **error report** printed by the app would be as follows:


        -------------------------PLAYER CHANCES FILE ERRORS------------------------
        
        Line Number: 1
        Line: dsasd
        Error: Line has no field separator
        
        Line Number: 2
        Line:
        Error: Line has no field separator
        
        Line Number: 3
        Line: 67723     ughgg
        Error: Line has a value for 'knocked down pins' field that is not a number neither an F
        
        Line Number: 4
        Line:
        Error: Line has no field separator
        
        Line Number: 5
        Line:
        Error: Line has no field separator
        
        Line Number: 6
        Line:
        Error: Line has not 2 fields only
        
        Line Number: 7
        Line: jdjsdhaak
        Error: Line has no field separator
        
        Line Number: 8
        Line:
        Error: Line has no field separator
        
        Line Number: 9
        Line: Marcus    10      12
        Error: Line has not 2 fields only
        
        Line Number: 14
        Line: 8999      89
        Error: Line has a value for 'knocked down pins' field that is not between 0 and 10
        
        ----------------------------------------------------------------------------


**The app only processes/builds/calculates games data when all lines inside Player Chances File has no errors**.

## Game Invalidations

The app would invalidate a player's game if any of the following rules occur:

1. Game exceeds max chances (i.e. All 10 frames of the game have been completed and there is **STILL** chances data to process)
2. Game has not enough chances data (i.e. Less than 10 frames have been completed and there is **NO** more chances data to process)
3. Any frame of the game exceeds the maximum pin fall sum value of 10.

**The app will calculate/print the scores for valid games only**.

## Installation

Clone this repository

```bash
git clone https://github.com/marcelomnc/score-10pin-bowling
```

Cd into the cloned repository folder

```bash
cd score-10pin-bowling
```

Build the project using maven

```bash
mvn install
```

Maven will create a folder with the name **target**. Cd into it

```bash
cd target
```

Inside it you will find this application's executable jar file

```bash
ls -l
total 28
drwxr-xr-x 1 owner 197609     0 Jun 25 10:21 classes/
drwxr-xr-x 1 owner 197609     0 Jun 25 09:43 generated-sources/
drwxr-xr-x 1 owner 197609     0 Jun 25 09:43 generated-test-sources/
-rw-r--r-- 1 owner 197609     0 Jun 25 10:26 java
drwxr-xr-x 1 owner 197609     0 Jun 25 10:10 maven-archiver/
drwxr-xr-x 1 owner 197609     0 Jun 25 10:10 maven-status/
-rw-r--r-- 1 owner 197609 24067 Jun 26 18:56 score-10pin-bowling-1.0-SNAPSHOT.jar
drwxr-xr-x 1 owner 197609     0 Jun 26 07:50 surefire-reports/
drwxr-xr-x 1 owner 197609     0 Jun 25 10:21 test-classes/
```

## Running

To run this application you will need a text file (**Player Chances File**) with all the data that must be parsed and processed. You can execute it passing the mentioned file path as the first and unique argument like:

```bash
java -jar score-10pin-bowling-1.0-SNAPSHOT.jar [PATH_TO_YOUR_FILE]
```

### Sample File

This is a sample data for the **Player Chances File**

    Jeff	10
    John	3
    John	7
    Jeff	7
    Jeff	3
    John	6
    John	3
    Jeff	9
    Jeff	0
    John	10
    Jeff	10
    John	8
    John	1
    Jeff	0
    Jeff	8
    John	10
    Jeff	8
    Jeff	2
    John	10
    Jeff	F
    Jeff	6
    John	9
    John	0
    Jeff	10
    John	7
    John	3
    Jeff	10
    John	4
    John	4
    Jeff	10
    Jeff	8
    Jeff	1
    John	10
    John	9
    John	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Zeros	0
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Fouls	F
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    Perfect	10
    
The rendered output by the application for the file above would be as follows

    -----------------------------------GAMES------------------------------------
    Player: Jeff
    Frame          |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  | 10  |
    PinFalls       |   X | 7 / | 9 0 |   X | 0 8 | 8 / | F 6 |   X |   X |X 8 1|
    Score          | 20  | 39  | 48  | 66  | 74  | 84  | 90  | 120 | 148 | 167 |
    Player: John
    Frame          |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  | 10  |
    PinFalls       | 3 / | 6 3 |   X | 8 1 |   X |   X | 9 0 | 7 / | 4 4 |X 9 0|
    Score          | 16  | 25  | 44  | 53  | 82  | 101 | 110 | 124 | 132 | 151 |
    Player: Zeros
    Frame          |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  | 10  |
    PinFalls       | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 |
    Score          |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |
    Player: Fouls
    Frame          |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  | 10  |
    PinFalls       | F F | F F | F F | F F | F F | F F | F F | F F | F F | F F |
    Score          |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |
    Player: Perfect
    Frame          |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  | 10  |
    PinFalls       |   X |   X |   X |   X |   X |   X |   X |   X |   X |X X X|
    Score          | 30  | 60  | 90  | 120 | 150 | 180 | 210 | 240 | 270 | 300 |
    ----------------------------------------------------------------------------

## Links

Rules for scoring a bowling game can be found on youtube **[here](https://www.youtube.com/watch?v=aBe71sD8o8c)** and **[here](https://www.youtube.com/watch?v=wY-fT2Gxa5s)**.
A very complete PDF file can be found **[here](http://usbcongress.http.internapcdn.net/usbcongress/bowl/rulebook/2018-2019Rulebook.pdf)**.