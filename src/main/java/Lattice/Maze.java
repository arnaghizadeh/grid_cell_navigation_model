package Lattice;


import GNM.P;

import java.util.Random;


public class Maze {
    public Block[][] maze;
    public int maxXDimention = 0;
    public int maxYDimention = 0;

    public int startXDimention = 0;
    public int startYDimention = 0;
    public int endXDimention = 0;
    public int endYDimention = 0;

    public Maze(int x, int y) {
        maxXDimention = x;
        maxYDimention = y;
        maze = new Block[maxXDimention][maxYDimention];

        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                Block b = new Block();
                //set dimentions of the block
                b.xDimention = i;
                b.yDimention = j;
                maze[i][j] = b;
            }
        }
    }

    public Maze(int x, int y, int probability) {
        maxXDimention = x;
        maxYDimention = y;
        maze = new Block[maxXDimention][maxYDimention];

        //initialize the maze
        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                Block b = new Block();

                //set dimentions of the block
                b.xDimention = i;
                b.yDimention = j;

                //we want to make sure that each block a a percent of chance to be open or closed
                Random rand;
                if (P.seed == -1) {
                    rand = new Random();
                } else {
                    rand = new Random(P.seed++);
                }
                int percentClosed = rand.nextInt((100 - 0) + 1) + 0;
                if (percentClosed < probability) {
                    b.blockStatus = 1;
                }

                maze[i][j] = b;
            }
        }

        //this is to choose start and end blocks
        do {
            Random xStartRand;
            Random yStartRand;
            Random xEndRand;
            Random yEndRand;

            if (P.seed == -1) {
                xStartRand = new Random();
                yStartRand = new Random();
                xEndRand = new Random();
                yEndRand = new Random();
            } else {
                xStartRand = new Random(P.seed++);
                yStartRand = new Random(P.seed++);
                xEndRand = new Random(P.seed++);
                yEndRand = new Random(P.seed++);
            }

            startXDimention = xStartRand.nextInt(((maxXDimention - 1) - 0) + 1) + 0;//we write maxXDimention-1 bcz array starts from 0 and not 1
            startYDimention = yStartRand.nextInt(((maxYDimention - 1) - 0) + 1) + 0;
            endXDimention = xEndRand.nextInt(((maxXDimention - 1) - 0) + 1) + 0;
            endYDimention = yEndRand.nextInt(((maxYDimention - 1) - 0) + 1) + 0;
        } while (((startXDimention == endXDimention) && (startYDimention == endYDimention))
                || maze[startXDimention][startYDimention].toString() != "#"
                || maze[endXDimention][endYDimention].toString() != "#");
        maze[startXDimention][startYDimention].blockStatus = 2;
        maze[endXDimention][endYDimention].blockStatus = 3;

        maze[startXDimention][startYDimention].gValue = 0;

        maze[startXDimention][startYDimention].hValue = 0;
        maze[endXDimention][endYDimention].hValue = 0;


    }

    public Maze(int x, int y, int probability, int startX, int startY, int endX, int endY) {
        maxXDimention = x;
        maxYDimention = y;
        maze = new Block[maxXDimention][maxYDimention];

        //initialize the maze
        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                Block b = new Block();

                //set dimentions of the block
                b.xDimention = i;
                b.yDimention = j;

                //we want to make sure that each block a a percent of chance to be open or closed
                Random rand;
                if (P.seed == -1) {
                    rand = new Random();
                } else {
                    rand = new Random(P.seed++);
                }
                int percentClosed = rand.nextInt((100 - 0) + 1) + 0;
                if (percentClosed < probability) {
                    b.blockStatus = 1;
                }

                maze[i][j] = b;
            }
        }

        //this is to choose start and end blocks
        if ((startX == endX) && (startY == endY)) {
            System.out.println("Error!!! Something is wrong with the X and Y input!!");
            return;
        }
        startXDimention = startX;
        startYDimention = startY;
        endXDimention = endX;
        endYDimention = endY;

        maze[startXDimention][startYDimention].blockStatus = 2;
        maze[endXDimention][endYDimention].blockStatus = 3;

        maze[startXDimention][startYDimention].gValue = 0;
        maze[startXDimention][startYDimention].hValue = 0;
        maze[endXDimention][endYDimention].hValue = 0;
    }

	public Block returnBlock(int x, int y){
		try {
			return maze[x][y];
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return null;
		}
	}

    public void showMaze() {
        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}