# MineSweeper
The game starts by placing a number of “bombs” in unknown random locations ina 2-D grid of cells.
The player sees a grid of blank cells-the location of the bombs is unknownat the start.
Play proceeds by allowing the player to click on any cell in the grid.The goal is to avoid 
clicking on a cell where a bomb is located.If the player clicks on a bomb, the game ends (the timer stops) 
and the entire board isrevealed.Selectinga cell thatis not a bomb should cause the cell face to changecolor and displaya number thatrepresents the number of its neighboring cells that contain bombs.

Selecting acell that does not border on any bombs should cause “clearing”, where all cells that are neighbors with the selected one and also do not border any bombs are automatically revealed.A cell has 8 neighbors: the north, south, east and west cells, togetherwith the four diagonal ones.Cells on the boundary of the grid have fewer neighbors (only five for acell on an edge and corner cells only have three).The player scores a victory by clicking to reveal all cells thatare not bombswithoutever hitting a bomb.

The Java program implements the complete layout of thegame interface, including menu options (startnew game, settings that control grid size and level of difficulty, quitthe game, and help)and game timer.  Minimum grid size is five by five; maximumis ten by ten.  The height, width, and number of bombs must be able to be set independently.  Height and width must be clamped in the range 5 to 10, and bombs from oneto one half the number of spaces on the currently defined grid.  In addition, the settings pane should let the user (alternatively) choose from three pre-set skill levels:  
Beginner(5x5 grid with 5 bombs);
Intermediate (8x8 with 15 bombs); and
Expert(10x10 with 30bombs