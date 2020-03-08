# Grid cell Navigational Model
Official implementation of the grid cell navigational model

The code is written with pure Java. With this code you create different types of mazes and run a navigational algorithm with the created mazes

#Maze
You can create three types of mazes:

1. Created an empty maze with only open blocks and nothing else, the size is determined with x, y:

```
Maze ma  = new Maze(x,y);
```

2. Created a proper maze with open, closed, start and end  blocks, the size is determined with x, y and probability of closed blocks with p:
```
Maze ma  = new Maze(x,y, p);
```


2. Created a proper maze with open, closed, start and end  blocks, the size is determined with x, y and probability of closed blocks with p, you can fixate
the start position with startX, startY and end position with endX, endY.


```
Maze ma = new Maze(x, y, p, startX, startY, endX, endY);
```

The maze can be shown in text format on the terminal, with 
```
ma.showMaze();
```

#Algorithms
In this implementation, four extra algorithms are supported, LRTA*, QLearning, BFS, DFS which can be run with:

```
QLearning ql = new QLearning(ma);
ql.findPath();
ma.showMaze();

LRTA lrta = new LRTA(ma);
lrta.findPath();
ma.showMaze();

```
