//Iterative deepening depth-first search (IDDFS) works like BFS
package Search;

import GNM.P;
import Lattice.Block;
import Lattice.Maze;

public class IDDFS {
    Maze realMaze;
    Block currentState;

    public IDDFS(Maze ma) {
        realMaze = ma;
        currentState = realMaze.returnBlock(realMaze.startXDimention, realMaze.startYDimention);
        currentState.blockStatus = 0;
    }

    public void findPath() {
        int depth = 0;
        while (true) {
            boolean found = step(realMaze.startXDimention, realMaze.startYDimention, depth++);
            if (found) {
                break;
            }
            P.cleanMaze(realMaze);
        }
    }

    private boolean step(int x, int y, int depth) {

        if (realMaze.returnBlock(x, y) == null) {
            return false;
        }
        if ((realMaze.returnBlock(x, y).blockStatus == 3)) {
            return true;
        }
        if ((realMaze.returnBlock(x, y).blockStatus) == 1 || (realMaze.returnBlock(x, y).blockStatus) == 4) {
            return false;
        }
        if (realMaze.returnBlock(x, y).blockStatus == 0 || (realMaze.returnBlock(x, y).blockStatus == 2)) {
            realMaze.returnBlock(x, y).blockStatus = 4;
        }
        if (depth > 0) {
            boolean result;
            if (realMaze.returnBlock(x, y + 1) != null) {
                result = step(x, y + 1, depth - 1);
                if (result) {
                    return true;
                }
            }
            if (realMaze.returnBlock(x, y) != null) {
                result = step(x, y, depth);
                if (result) {
                    return true;
                }
            }
            if (realMaze.returnBlock(x - 1, y) != null) {
                result = step(x - 1, y, depth - 1);
                if (result) {
                    return true;
                }
            }
            if (realMaze.returnBlock(x, y) != null) {
                result = step(x, y, depth);
                if (result) {
                    return true;
                }
            }
            if (realMaze.returnBlock(x + 1, y) != null) {
                result = step(x + 1, y, depth - 1);
                if (result) {
                    return true;
                }
            }
            if (realMaze.returnBlock(x, y) != null) {
                result = step(x, y, depth);
                if (result) {
                    return true;
                }
            }
            if (realMaze.returnBlock(x, y - 1) != null) {
                result = step(x, y - 1, depth - 1);
                if (result) {
                    return true;
                }
            }

        }
        return false;

    }

}
