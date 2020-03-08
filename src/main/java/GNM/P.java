package GNM;

import Lattice.Maze;

import java.util.ArrayList;


public class P {
	public static int seed=-1;


	static public void cleanMaze(Maze ma) {
		for(int j=0;j<ma.maxYDimention;j++){
			for(int i=0;i<ma.maxXDimention;i++){
				if(ma.returnBlock(i, j).blockStatus == 4){
					ma.returnBlock(i, j).blockStatus=0;
				}
			}
		}
		ma.returnBlock(ma.startXDimention, ma.startYDimention).blockStatus=2;
	}
}
