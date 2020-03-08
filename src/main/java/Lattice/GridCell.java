package Lattice;

import GNM.P;

public class GridCell {
	public int label = -1;
	public int xDimention = 0;
	public int yDimention = 0;
	public double weight = 0;
	public double viewWeight = 0;
	

	
	public double minimumWeight = P.maxWeight;
	public double maximumWeight = P.minWeight;
	public double minimumWeightView = P.maxWeight;
	public Block activeBlock;
	public Block lastBlock;
	public Block lastBlockView;
	public Block activeBlockView;
	public double acc=0;//accumulation
	public double accView=0;
	
	public void cloneGridCell(GridCell o){
		this.label=o.label;
		this.xDimention=o.xDimention;
		this.yDimention=o.yDimention;
		this.weight=o.weight;
		this.viewWeight=o.viewWeight;
	}
	@Override public String toString() {
		return Integer.toString((int)weight);
	}
}
