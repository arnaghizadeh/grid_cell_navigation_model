//Backtrack Search is DFS
package Search;

import GNM.P;
import Lattice.Block;
import Lattice.Maze;

import java.util.Stack;

public class Backtrack {
    Maze realMaze;
    Block currentState;

    public Backtrack(Maze ma) {
        realMaze = ma;
        currentState = realMaze.returnBlock(realMaze.startXDimention, realMaze.startYDimention);
        currentState.blockStatus = 0;
    }

    public void findPath() {
        if (step(realMaze.startXDimention, realMaze.startYDimention)) {
            return;
        }
    }

    private boolean step(int x, int y) {
        if (realMaze.returnBlock(x, y) == null) {
            return false;
        }

        if (realMaze.returnBlock(x, y).blockStatus == 3) {
            return true;
        }
        if ((realMaze.returnBlock(x, y).blockStatus) == 1 ||
                (realMaze.returnBlock(x, y).blockStatus) == 4) {
            return false;
        }

        if ((realMaze.returnBlock(x, y).blockStatus == 0) || (realMaze.returnBlock(x, y).blockStatus == 2)) {
            realMaze.returnBlock(x, y).blockStatus = 4;
        }

        boolean result;

        if (realMaze.returnBlock(x, y + 1) != null) {
            result = step(x, y + 1);
            if (result) {
                return true;
            }
        }
        if (realMaze.returnBlock(x, y) != null) {
            result = step(x, y);
            if (result) {
                return true;
            }
        }
        if (realMaze.returnBlock(x - 1, y) != null) {
            result = step(x - 1, y);
            if (result) {
                return true;
            }
        }
        if (realMaze.returnBlock(x, y) != null) {
            result = step(x, y);
            if (result) {
                return true;
            }
        }
        if (realMaze.returnBlock(x, y - 1) != null) {
            result = step(x, y - 1);
            if (result) {
                return true;
            }
        }
        if (realMaze.returnBlock(x, y) != null) {
            result = step(x, y);
            if (result) {
                return true;
            }
        }
        if (realMaze.returnBlock(x + 1, y) != null) {
            result = step(x + 1, y);
            if (result) {
                return true;
            }
        }
        return false;
    }
}
