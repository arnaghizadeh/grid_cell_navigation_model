package Lattice;

public class Block {	
	public Block parentBlock = null;
	public Block childBlock = null;
	public int xDimention = 0;
	public int yDimention = 0;
	public int gValue = 0;
	public int hValue = 0;
	public int fValue = 0;
	public int blockStatus = 0;//0 means block is open, 1 means block is closed
	//2 means it is starting point, 3 mean, 4 means visited, 5 visited before by another serch (adaptive a*)
	public void printStatus(){
		System.out.print("g:"+gValue+" h:"+hValue+" f:"+fValue);
	}
	@Override public String toString() {
		if(this.blockStatus == 0){
			return "#";
		}
		else if(this.blockStatus == 1){
			return "*";
		}
		else if(this.blockStatus == 2){
			return "s";
		}
		else if(this.blockStatus == 3){
			return "e";
		}
		else if(this.blockStatus == 4){
			return ".";
		}
		else if(this.blockStatus == 5){
			return "-";
		}
		else if(this.blockStatus == 6){
			return "X";
		}
		else{
			return "error";
		}
	}
}
