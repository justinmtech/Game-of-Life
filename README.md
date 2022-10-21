# Game of Life
A customizable re-make of Conway's famous "Game of Life". Configurable environment size, cell colors, generation rate, life/death chances and generation seeds!

# Setup
1. Download the release jar file (or clone and build it yourself)
2. Run the jar file in a terminal/command prompt with "java -jar {fileName}.jar"
3. Enjoy the show!

# Optional Setup
1. Make a batch or shell script file with "java -jar {fileName}.jar" to easily start/stop the game.
2. (Optional) Tweak the configuration settings to your liking.

# Information
The game first generates until the "maxGeneration" value is reached.
After that, the simulation will replay in a GUI that updates based on the "updateDelay" setting which is in milliseconds.
Be aware that some seed generations will either instantly die or not produce anything if the "recipe" for life isn't enough.
For life to be sustained there needs to be a balance between 1's and 0's and the space between them.

# Rules of Life
A live cell dies if it has fewer than two live neighbors. 
A live cell with two or three live neighbors lives on to the next generation. 
A live cell with more than three live neighbors dies. 
A dead cell will be brought back to life if it has exactly three live neighbors.

# Configuration 
gameTitle: "Game of Life"
updateDelay: 250
generationChance: 5
useRandomDeathChance: false
randomDeathChance: 100
height: 20
width: 20
maxGeneration: 1000
seed: [0,0,1]
useSeed: false
backgroundColor: "BLACK"
cellColor: "GREEN"
useRandomCellColors: false
disableGUI: false
showGenerationInConsole: true
consoleCellAliveDisplay: "*"
consoleCellDeadDisplay: " "

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
