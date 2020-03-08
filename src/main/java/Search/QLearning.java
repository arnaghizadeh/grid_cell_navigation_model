package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import GNM.P;
import Lattice.Block;
import Lattice.Maze;

public class QLearning {
    Maze realMaze;
    Block currentState;
    ArrayList<Block> openBlocks = new ArrayList<Block>();
    Random rand;

    double alpha = 0.1; // Learning rate
    double gamma = 0.9; // Eagerness - 0 looks in the near future, 1 looks in the distant future
    double epsilon = 0.1;

    int mazeWidth = 0;
    int mazeHeight = 0;
    int statesCount = 0;

    int reward = 100;
    //private final int penalty = -10;//there is no penalty in our implementation

    private int[][] MemoryCounter; // Reward lookup
    private double[][] Q;    //Q-learning

    public QLearning(Maze m) {
        int x = P.seed;
        if (P.seed == -1) {
            rand = new Random();
        } else {
            P.seed += 5;
            rand = new Random(x);
        }

        realMaze = m;
        mazeWidth = realMaze.maxXDimention;
        mazeHeight = realMaze.maxYDimention;
        statesCount = mazeHeight * mazeWidth;
        currentState = realMaze.returnBlock(realMaze.startXDimention, realMaze.startYDimention);

        Q = new double[statesCount][statesCount];
    }

    public void findPath() {
        while ((currentState.xDimention != realMaze.endXDimention) || (currentState.yDimention != realMaze.endYDimention)) {
            openBlocks.clear();
            addToOpenBlocks();

            Block nextState = chooseNextState();

            int crtState = currentState.xDimention * currentState.yDimention;
            int nxtState = nextState.xDimention * nextState.yDimention;
            double q = Q[crtState][nxtState];

            currentState = nextState;
            openBlocks.clear();
            addToOpenBlocks();

            double maxQ = maxQ();
            int r = 0;
            if (nextState.blockStatus == 3)
                r = 100;

            double value = q + alpha * (r + gamma * maxQ - q);
            Q[crtState][nxtState] = value;
            currentState.blockStatus = 4;
        }
    }

    private Block chooseNextState() {
        Block selectedNode = null;

        double random = rand.nextDouble();
        if (random < epsilon) {
            int index = rand.nextInt(openBlocks.size());
            selectedNode = openBlocks.get(index);
        } else {

            //first get the max q
            double max_value = maxQ();
            //now see which ones are equal and add them for a chance of selection
            ArrayList<Block> maximums = new ArrayList<Block>();
            for (int i = 0; i < openBlocks.size(); i++) {
                Block b_next = openBlocks.get(i);
                int q1 = currentState.xDimention * currentState.yDimention;
                int q2 = b_next.xDimention * b_next.yDimention;
                if (max_value == Q[q1][q2]) {
                    maximums.add(b_next);
                }
            }
            //System.out.println(openBlocks.size());
            int index = rand.nextInt(maximums.size());

            selectedNode = maximums.get(index);
        }
        return selectedNode;
    }

    private void addToOpenBlocks() {
        if (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention) != null
                && (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus != 1)) {

            openBlocks.add(realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention));

        }
        if (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention) != null
                && (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus != 1)) {
            openBlocks.add(realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention));

        }
        if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1) != null
                && (realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus != 1)) {
            openBlocks.add(realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1));

        }
        if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1) != null
                && (realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus != 1)) {
            openBlocks.add(realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1));
        }
    }

    double maxQ() {//this returns the max Q between currentState neighbours
        double max_value = -10000;
        for (int i = 0; i < openBlocks.size(); i++) {
            Block b_next = openBlocks.get(i);
            int q1 = currentState.xDimention * currentState.yDimention;
            int q2 = b_next.xDimention * b_next.yDimention;
            if (max_value < Q[q1][q2]) {
                max_value = Q[q1][q2];
            }
        }
        return max_value;
    }
}
