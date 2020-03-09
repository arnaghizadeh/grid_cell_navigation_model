### Grid cell navigational model
Official implementation of the grid cell navigation model. The code is written with pure Java. With this code, you can create different types of mazes and run a navigation algorithm with the created mazes. This could be used as a simulator for the Grid cell firing, a playground to implement your own method, doing research in robotic, etc. You can read about this method in [Grid cell navigation model](https://arnaghizadeh.github.io/papers/GNM/GNM.html) webpage. 



### Maze
You can create three types of mazes:

1 - Create an empty maze with `open(#)` blocks and nothing else, the size is determined with x, y:
```
Maze ma  = new Maze(x,y);
```

2 - Create a proper maze with `open(#)`, `closed(*)`, `start(s)`, and `end(e)` blocks, the size is determined with x, y, and the probability of closed blocks with p:
```
Maze ma  = new Maze(x,y, p);
```

3 - Created a proper maze with `open(#)`, `closed(*)`, `start(s)`, and `end(e)`  blocks, the size is determined with x, y, and the probability of closed blocks with p, you can fixate the start position with startX, startY and end position with endX, endY.


```
Maze ma = new Maze(x, y, p, startX, startY, endX, endY);
```

The maze can be shown in text format on the terminal, with 
```
ma.showMaze();
```

### Exploration Algorithms
In this implementation, four extra algorithms are supported, `LRTA*`, `Q-Learning`, `Backtrack`, and `IDDFS`, which can be run with:

```
QLearning ql = new QLearning(ma);
ql.findPath();
ma.showMaze();

LRTA lrta = new LRTA(ma);
lrta.findPath();
ma.showMaze();

Backtrack bt = new Backtrack(ma);
bt.findPath();
ma.showMaze();

IDDFS iddfs = new IDDFS(ma);
iddfs.findPath();
ma.showMaze();

```

### GNM Algorithm
The Grid cells are packed in the modules. Similarly, for this algorithm to work, we have to also create a module first. To create the modules, an initial value (`h`) has to be used. This is how to create a module:

```
Module mo = new Module(10);
```

There are two versions of the algorithm, GNM Basic and GNM. The GNM should always be used in practice. However, learning GNM Basic is a little easier than complete GNM. To choose between them, we use a mode option. The mode`0` is used for GNM Basic, and mode `1` is used for GNM. We need two objects, a module and a maze to perform the search.

```
GNMSearch hs = new GNMSearch(ma,mo,mode);
hs.findPath();
ma.showMaze();
```

If the `h` is big enough, the cells in the modules are equal or bigger than the environment. In this case, the algorithm can find destination faster. The modes `0`, `1` are for these cases. When environment could be bigger than the module we have to use mode `2` which provides some randomness in decision making.

```
mode = 0 #GNM Basic
mode = 1 #GNM
mode = 2 #GNM if mo < ma
```
### Running an example
Following provides a complete example of the program running with its outcomes of the maze before routing and the maze after routing:
```
Maze ma = new Maze(10, 10, 5, 0, 0, 5, 5);
ma.showMaze();

System.out.println();
Module mo = new Module(10);
GNMSearch hs = new GNMSearch(ma,mo,0);
hs.findPath();
ma.showMaze();
mo.showModule();
```
The output is:
```
s # # # # # # # # # 
# # # # # # # # # # 
# # # # # # # # # # 
# # # # # # # # # # 
# # # # # # # * # # 
# # # # # e # # # # 
* # # # # # # * # # 
# # # # # # # # # # 
# # # # # # # # # # 
# # # # * # # # # # 

s . . . . . # # # # 
. . # . . . # # # # 
# # # # . # # # # # 
# # # # . # # # # # 
# # # # . # # * # # 
# # # # . e # # # # 
* # # # # # # * # # 
# # # # # # # # # # 
# # # # # # # # # # 
# # # # * # # # # # 
```

### Grid cell simulator
The module can, also show its out as follows. 
```
mo.showModule();
```
The output is the grid numbers. The is the final grids for the routing example shown above. For using the program as a grid simulator, the module steps can show the grid cell values in every step of the way.   
```

2	2	2	2	2	2	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
2	2	0	2	4	2	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	2	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	2	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	2	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	2	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
```
### Installation
For ease of use the project is Maven based. Any environment that supports Maven can run the program. For development we used inlliJ IDE, so the easiest way to run the program is probably to use inlliJ IDE and simply import the project ater cloning. If you want to use command line, Maven should be installed. 

For runing the program in ubuntu, first make sure you have Maven installed:

```
sudo apt-get update
sudo apt-get install maven
```
Clone the program
```
git clone https://github.com/arnaghizadeh/grid_cell_navigation_model.git
cd grid_cell_navigation_model/
```
Run the program
```
mvn compile
mvn exec:java -Dexec.mainClass=GNM.Main
```
### Citation

The code is distributed under MIT licence. However, if you find this code useful, we would appreciate it if you consider citing the accompanied paper:

```
@article{naghizadeh2020gnm,
  title={GNM: GridCell navigational model},
  author={Naghizadeh, Alireza and Berenjian, Samaneh and Margolis, David J and Metaxas, Dimitris N},
  journal={Expert Systems with Applications},
  volume={148},
  pages={113217},
  year={2020},
  publisher={Elsevier}
}

```
