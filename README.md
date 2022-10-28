# Game of Life
A customizable re-make of Conway's famous "Game of Life" made in Java. Configurable environment size, cell colors, generation rate, life/death chances and generation seeds!

Dynamic Generator (screenshot)
![cells](https://user-images.githubusercontent.com/8346060/198699697-83156dba-cd84-4402-beb7-07c8de1f9df8.png)
Static Generator (screenshot)
![static](https://user-images.githubusercontent.com/8346060/198701385-26c9658f-8eac-4684-8ff7-7f7cc19f04bd.png)


# Setup
1. Download the release jar file (or clone and build it yourself)
2. Run the jar file in a terminal with "java -jar {fileName}.jar"
3. Let the game generate, then enjoy the simulation!

# Optional Setup
- Make a batch or shell script file with "java -jar {fileName}.jar" to easily start/stop the game.
- Tweak the configuration settings to your liking.

# Generators

- The game has 2 different generators that can be changed in the config.
- The dynamic setting produces an animated simulation.
- The static setting produces an image the size of the height and width based on the seed.
- If using static generator, the seed must be 8 integers.

# Ram Usage
- The simulation uses a lot of ram (around 1.5 gigs if using a 500x500 generation).
- You can lower the height and width of the game to greatly reduce the resource consumption if needed.
- Use flags if you want to limit ram usage (ie: "java -jar -Xmx1024m -Xms512m {fileName}.jar")

# Information
- The game first generates until the "maxGeneration" value is reached.
- After that, the simulation will replay in a GUI that updates based on the "updateDelay" setting which is in milliseconds.
- Be aware that some seed generations will either instantly die or not produce anything if the "recipe" for life isn't enough.
- For life to be sustained there needs to be a balance between 1's and 0's and the space between them.

# Rules of Life (Dynamic Generator)
- A live cell dies if it has fewer than two live neighbors. 
- A live cell with two or three live neighbors lives on to the next generation. 
- A live cell with more than three live neighbors dies. 
- A dead cell will be brought back to life if it has exactly three live neighbors.

# Configuration 
- gameTitle: "Game of Life"
- generator: "DYNAMIC" #DYNAMIC or STATIC
- updateDelay: 250
- generationChance: 5
- useRandomDeathChance: false
- randomDeathChance: 100
- height: 20
- width: 20
- maxGeneration: 1000
- seed: [0,0,1]
- useSeed: false
- backgroundColor: "BLACK"
- cellColor: "GREEN"
- useRandomCellColors: false
- disableGUI: false
- showGenerationInConsole: true
- consoleCellAliveDisplay: "*"
- consoleCellDeadDisplay: " "

# List of Colors

Here are colors you can use for cells and backgrounds (backgroundColor and cellColor):
- "BLACK"
- "RED"
- "GREEN"
- "YELLOW"
- "ORANGE" 
- "BLUE" 
- "CYAN" 
- "PINK"
- "MAGENTA" 
- "GRAY"
- "DARK_GRAY"
- "LIGHT_GRAY"
- "WHITE"
