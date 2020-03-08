package GNM;

import Lattice.Maze;

import java.util.ArrayList;


public class P {
	public static int seed=-1;
	public static int maxWeight=9;
	public static int minWeight=0;
	public static double eta=0.1;//this is learning rate for Oja's search
	public static int openNodesCounter=0;//this is required for search with blocks in GNMSearch
	public static int alpha = 1;//tb

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
