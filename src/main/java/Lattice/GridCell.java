package Lattice;

import GNM.P;

public class GridCell {
	public int label = -1;
	public int xDimention = 0;
	public int yDimention = 0;
	public double weight = 0;
	public double viewWeight = 0;

	@Override public String toString() {
		return Integer.toString((int)weight);
	}
}
