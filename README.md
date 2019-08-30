# Project goal
This was an attempt for me to redo the Programming 1 coursework in Kotlin in order for me to refine my skills using a new language, original task was done in Java.

# Tasks completed
1. Implement the meter class and appliance class
2. Add House, run timePasses()
3. Modelling other appliances
4. Modelling the battery
5. Running the simulation 
6. Reading a simulation configuration file
7. Extensions - Save and Load houses

# Running the simulation
```
kotlinc house.kt -include-runtime -d house.jar
```
Takes in the parameters of the config file and the number of hours you want to run the simulation for, if hours are not specifed it runs for 168 hours (7 days)

```
java -jar house.jar <config.txt> <hours>
```

