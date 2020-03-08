package Search;

import GNM.P;
import Lattice.Block;
import Lattice.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class LRTA {
    ArrayList<Block> openBlocks = new ArrayList<Block>();
    Maze realMaze;
    Block currentState;
    Random rand;

    public LRTA(Maze m) {
        int x = P.seed;
        if (P.seed == -1) {
            rand = new Random();
        } else {
            P.seed += 5;
            rand = new Random(x);
        }
        realMaze = m;
    }

    public void findPath() {
        currentState = realMaze.returnBlock(realMaze.startXDimention, realMaze.startYDimention);
        currentState.gValue = 0;
        currentState.hValue = Math.abs((currentState.xDimention) - realMaze.endXDimention) + Math.abs(currentState.yDimention - realMaze.endYDimention);
        currentState.fValue = currentState.gValue + currentState.hValue;
        currentState.blockStatus = 4;

        while ((realMaze.startXDimention != realMaze.endXDimention) || (realMaze.startYDimention != realMaze.endYDimention)) {
            openBlocks.clear();
            addToOpenBlocks();


            Collections.sort(openBlocks, new fValueComparator());

            //breaking tie randomly
            int index_end = 0;
            for (int i = 1; i < openBlocks.size(); i++) {
                if (openBlocks.get(i - 1).fValue == openBlocks.get(i).fValue)
                    index_end++;
                else
                    break;
            }
            int index = rand.nextInt((index_end + 1) - 0) + 0;
            currentState = openBlocks.get(index);
            if (currentState.blockStatus == 3) {
                break;
            }
            currentState.blockStatus = 4;
        }

    }

    private void addToOpenBlocks() {
        if (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention) != null
                && (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus == 0
                || realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus == 3
                || realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus == 4)) {

            if (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus == 4) {
                realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).hValue = Math.abs((currentState.xDimention - 1) - realMaze.endXDimention) + Math.abs(currentState.yDimention - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).gValue = 1;
                if (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).hValue < currentState.fValue)
                    realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).hValue = currentState.fValue;
            } else {
                realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).hValue = Math.abs((currentState.xDimention - 1) - realMaze.endXDimention) + Math.abs(currentState.yDimention - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).gValue = 0;
            }

            realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).hValue = 0;

            realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).fValue = realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).gValue + realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).hValue;
            openBlocks.add(realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention));
        }
        if (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention) != null
                && (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus == 0
                || realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus == 3
                || realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus == 4)) {

            if (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus == 4) {
                realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).hValue = Math.abs((currentState.xDimention + 1) - realMaze.endXDimention) + Math.abs(currentState.yDimention - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).gValue = 1;
                if (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).hValue < currentState.fValue)
                    realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).hValue = currentState.fValue;
            } else {
                realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).hValue = Math.abs((currentState.xDimention + 1) - realMaze.endXDimention) + Math.abs(currentState.yDimention - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).gValue = 0;
            }
            realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).hValue = 0;
            realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).fValue = realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).gValue + realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).hValue;
            openBlocks.add(realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention));
        }
        if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1) != null
                && (realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus == 0
                || realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus == 3
                || realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus == 4)) {
            if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus == 4) {
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).hValue = Math.abs(currentState.xDimention - realMaze.endXDimention) + Math.abs((currentState.yDimention - 1) - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).gValue = 1;
                if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).hValue < currentState.fValue)
                    realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).hValue = currentState.fValue;
            } else {
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).hValue = Math.abs(currentState.xDimention - realMaze.endXDimention) + Math.abs((currentState.yDimention - 1) - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).gValue = 0;
            }
            realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).hValue = 0;
            realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).fValue = realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).gValue + realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).hValue;
            openBlocks.add(realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1));
        }
        if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1) != null
                && (realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus == 0
                || realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus == 3
                || realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus == 4)) {

            if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus == 4) {
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).hValue = Math.abs(currentState.xDimention - realMaze.endXDimention) + Math.abs((currentState.yDimention + 1) - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).gValue = 1;
                if (realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).hValue < currentState.fValue)
                    realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).hValue = currentState.fValue;
            } else {
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).hValue = Math.abs(currentState.xDimention - realMaze.endXDimention) + Math.abs((currentState.yDimention + 1) - realMaze.endYDimention);
                realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).gValue = 0;
            }
            realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).hValue = 0;
            realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).fValue = realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).gValue + realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).hValue;
            openBlocks.add(realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1));
        }
    }
}
class fValueComparator implements Comparator<Block> {
	@Override
    public int compare(Block b1, Block b2){
		if(b1.fValue > b2.fValue)
			return 1;
		else if(b1.fValue == b2.fValue)
			return 0;
		else
			return -1;
	}

}
