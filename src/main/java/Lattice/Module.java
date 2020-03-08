package Lattice;

public class Module {
    public GridCell[][] module;
    public int maxXDimention = -1;
    public int maxYDimention = -1;
    public int w = -1;

    public Module(int w) {
        this.w = w;
        maxXDimention = 2 * w;
        maxYDimention = 2 * w - 1;
        module = new GridCell[maxXDimention][maxYDimention];

        int label = 0;
        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                GridCell gc = new GridCell();
                //set dimentions of the block
                gc.label = label++;
                gc.xDimention = i;
                gc.yDimention = j;
                module[i][j] = gc;
            }
        }
    }

    public GridCell returnBlock(int x, int y) {
        try {
            return module[x][y];
        } catch (ArrayIndexOutOfBoundsException exception) {
            return null;
        }
    }

    public void showModule() {
        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                System.out.print(module[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void showGridCellWeights() {
        for (int j = 0; j < maxYDimention; j++) {
            for (int i = 0; i < maxXDimention; i++) {
                System.out.print(returnBlock(i, j).weight + "\t");
            }
            System.out.println();
        }
    }

}
