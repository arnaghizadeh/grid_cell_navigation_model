### Grid cell navigational model
Official implementation of the grid cell navigation model. The code is written with pure Java. With this code you can create different types of mazes and run a navigation algorithm with the created mazes. This could be used as a simulator for Grid cell, firing, a play ground to implement you own method, doing research in robotic etc. You can read about this method in [Grid cell navigation model](https://arnaghizadeh.github.io/papers/GNM/GNM.html) webpage. 



### Maze
You can create three types of mazes:

1 - Created an empty maze with only open blocks and nothing else, the size is determined with x, y:
```
Maze ma  = new Maze(x,y);
```

2 - Created a proper maze with open, closed, start and end  blocks, the size is determined with x, y and probability of closed blocks with p:
```
Maze ma  = new Maze(x,y, p);
```


3 - Created a proper maze with open, closed, start and end  blocks, the size is determined with x, y and probability of closed blocks with p, you can fixate
the start position with startX, startY and end position with endX, endY.


```
Maze ma = new Maze(x, y, p, startX, startY, endX, endY);
```

The maze can be shown in text format on the terminal, with 
```
ma.showMaze();
```

### Exploration Algorithms
In this implementation, four extra algorithms are supported, LRTA*, QLearning, Backtrack, and IDDFS which can be run with:

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
The Grid cell are packed in the modules. Similarly, for this algorithm to work, we have to also create a module first. The modules need a initial value of `h`. This is how we create a module
```
Module mo = new Module(10);
```

We have two versions of the algorithm, GNM Basic and GNM. The GNM should always be used in practice. However, learning GNM Basic is a little easier than the complete GNM. To choose between them we use a mode option. The `0` is for GNM Basic and `1` is for GNM. We need both module and maze to perform the search.

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

### Grid cell simulator

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
