package Search;

import GNM.P;
import Lattice.Block;
import Lattice.GridCell;
import Lattice.Maze;
import Lattice.Module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GNMSearch {
	Maze realMaze;
	Module realModule;
	Block currentState;
	int searchMode=0;	
	int loopCounter = 0; //for debug only
	
	Random rand;
	
	
	public GNMSearch(Maze ma, Module mo, int searchMode){
		//create the random value
		int x=P.seed;
		if(P.seed==-1){
			rand = new Random();
		}else{
			P.seed+=5;
			rand = new Random(x);
		}
		
		realMaze = ma;
		realModule = mo;
		currentState = realMaze.returnBlock(realMaze.startXDimention, realMaze.startYDimention);
		
		//this will be used in a switch case later on 0 for GNM basic and 1 for GNM
		this.searchMode=searchMode;
		
		
		//initialize the starting cell
		GridCell firedCellS=firingCell(currentState.xDimention, currentState.yDimention);
		firedCellS.weight=1;
		firedCellS.viewWeight=1;
	}
	public void findPath(){
		while((currentState.xDimention != realMaze.endXDimention) || (currentState.yDimention!= realMaze.endYDimention)){
			currentState = chooseNext();
			//System.out.println("Open Nodes B:"+P.openNodesCounter);	
			
			
			if(currentState == null){
				System.out.println("Path is blocked, cannot reach destination");
				realMaze.showMaze();
				System.out.println();
				break;
			}
			
			if(realMaze.returnBlock(currentState.xDimention, currentState.yDimention).blockStatus==0)
			{
				//P.openNodesCounter--;			
				realMaze.returnBlock(currentState.xDimention, currentState.yDimention).blockStatus = 4;	
			}	
			
			loopCounter++;
		}
	}
	
	private Block chooseNext() {
		Block returnBlock = null;
		switch (searchMode){
		case 0://this is for basic GNM
			returnBlock = chooseNextBasic(0);
			break;
		case 1://this is for GNM
			returnBlock = chooseNextBasic(1);
			break;
		case 2:	//This is for handling probability
			returnBlock = chooseNextHandleLowMemoryProbability();
			break;
		default:
			System.out.println("The search mode is wrong!!");
			break;
		}
		
		return returnBlock;
					
	}
	private Block chooseNextHandleLowMemoryProbability() {
		GridCell firedCell0 = null;//this is for current state
		GridCell firedCell1 = null;
		GridCell firedCell2 = null;
		GridCell firedCell3 = null;
		GridCell firedCell4 = null;
		
		
		firedCell0=firingCell(currentState.xDimention, currentState.yDimention);
		
				
		if((realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention)!=null) &&
				(realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus!=1)){
			firedCell1=firingCell(currentState.xDimention - 1, currentState.yDimention);
			
			if((firedCell1.viewWeight==0) && (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus!=2))
				P.openNodesCounter++;
		}
		if((realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention)!=null) &&
				(realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus!=1)){
			firedCell2=firingCell(currentState.xDimention + 1, currentState.yDimention);
			
			if((firedCell2.viewWeight==0) && (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus!=2))
				P.openNodesCounter++;
		}
		if((realMaze.returnBlock(currentState.xDimention , currentState.yDimention - 1)!=null)&&
				(realMaze.returnBlock(currentState.xDimention , currentState.yDimention - 1).blockStatus!=1)){
			firedCell3=firingCell(currentState.xDimention , currentState.yDimention - 1);
			
			if((firedCell3.viewWeight==0)&&(realMaze.returnBlock(currentState.xDimention, currentState.yDimention-1).blockStatus!=2))
				P.openNodesCounter++;
		}
		if((realMaze.returnBlock(currentState.xDimention , currentState.yDimention + 1)!=null)&&
				(realMaze.returnBlock(currentState.xDimention , currentState.yDimention + 1).blockStatus!=1)){
			firedCell4=firingCell(currentState.xDimention , currentState.yDimention + 1);
			
			if((firedCell4.viewWeight==0)&&(realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus!=2))
				P.openNodesCounter++;
		}
		
		
		if(P.openNodesCounter==0)
			return null;
		
		//this is to adjust view weights Connections.adjustViewsWeights(firedCell0, firedCell1, firedCell2, firedCell3, firedCell4);
		if(firedCell1!=null){
			firedCell1.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention);
		}
		if(firedCell2!=null){
			firedCell2.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention);
		}
		if(firedCell3!=null){
			firedCell3.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1);
		}
		if(firedCell4!=null){
			firedCell4.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1);
		}
		
		GridCell bestChoice = chooseBestConnectionWithProbability(firedCell1, firedCell2, firedCell3, firedCell4);
		if(bestChoice ==firedCell1){
			firedCell0.weight++; 
			firedCell1.weight++;
			return realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention);
		}else if(bestChoice == firedCell2){
			firedCell0.weight++; 
			firedCell2.weight++;
			return realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention);
		}else if(bestChoice == firedCell3){
			firedCell0.weight++; 
			firedCell3.weight++;
			return realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1);
		}else if(bestChoice == firedCell4){
			firedCell0.weight++; 
			firedCell4.weight++;
			return realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1);
		}else{
			System.out.println("Choose weight is not working correctly, something is wrong!!");
			return null;
		}
	}
	private GridCell chooseBestConnectionWithProbability(GridCell gc1, GridCell gc2, GridCell gc3, GridCell gc4) {
		int index=0;
		ArrayList<GridCell> weights = new  ArrayList<GridCell>();
		ArrayList<GridCell> viewWeights =	new  ArrayList<GridCell>();
		ArrayList<GridCell> newWeights = new  ArrayList<GridCell>();
		
		if(gc1!=null)
			weights.add(gc1);
		if(gc2!=null)
			weights.add(gc2);
		if(gc3!=null)
			weights.add(gc3);		
		if(gc4!=null)
			weights.add(gc4);	
		weights.sort(new wComparator());
		
		
		
		//it is not enough to get the first min, we have to choose a min randomely between those that are equal.		
		for(int i=0;i<=weights.size()-1;i++){
			viewWeights.add(weights.get(i));
			if((i+1<weights.size())&&(weights.get(i).weight!=weights.get(i+1).weight)){
				
			
				viewWeights.sort(new vwComparator());
				for(int j=0;j<viewWeights.size();j++){
					newWeights.add(viewWeights.get(j));
				}
				viewWeights.clear();
			}
			if(i==weights.size()-1){
				if(viewWeights.size()>0){
					viewWeights.sort(new vwComparator());
					for(int j=0;j<viewWeights.size();j++){
						newWeights.add(viewWeights.get(j));
					}
					viewWeights.clear();
				}
				newWeights.add(weights.get(weights.size()-1));
			}
		}
		
		
		
		
		//we use pariti distribution for probabilities
		double P1=1,P2,P3,P4;
		
		P2=Math.pow((double)(P1/2),P.alpha);
		P3=Math.pow((double)(P1/3),P.alpha);
		P4=Math.pow((double)(P1/4),P.alpha);
		
		//we choose the randomness, good for debug.
		/*P1=0.5;
		P2=0.3;
		P3=0.2;
		P4=0.1;*/
		
		/*if(loopCounter>100000){
			System.out.println("P1:"+P1+" P2:"+P2+" P3:"+P3+" P4:"+P4+" "+P1/2+":"+Math.pow((double)(1/2),P.alpha));
		}*/
	
		
		
		boolean flag = false;
		int bestIndex = -1;
		
		
		while(flag==false){
			
			double randomNumber = rand.nextDouble();
			if((randomNumber<P4) &&  (newWeights.size()>=4)){
				bestIndex = 3;
				flag = true;
			}
			if((randomNumber<P3) && (flag == false) && (newWeights.size()>=3)){
				bestIndex = 2;
				flag = true;
			}
			if((randomNumber<P2) && (flag == false) && (newWeights.size()>=2)){
				bestIndex = 1;
				flag = true;
			}
			if((randomNumber<P1) && (flag == false)&& (newWeights.size()>=1)){
				bestIndex = 0;
				flag = true;
			}
			
			//System.out.println(randomNumber);
		}
		if(flag == false){
			System.out.println("This should not happen!!!!" + newWeights.size()+" "+weights.size());
		}//System.out.println(bestIndex);
		/*if(loopCounter>1000){
			
		}*/
		return newWeights.get(bestIndex);
	}
	private Block chooseNextBasic(int mode) {
		GridCell firedCell0 = null;//this is for current state
		GridCell firedCell1 = null;
		GridCell firedCell2 = null;
		GridCell firedCell3 = null;
		GridCell firedCell4 = null;
		
		
		firedCell0=firingCell(currentState.xDimention, currentState.yDimention);
		
				
		if((realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention)!=null) &&
				(realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus!=1)){
			firedCell1=firingCell(currentState.xDimention - 1, currentState.yDimention);
			
			if((firedCell1.viewWeight==0) && (realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus!=2))
				P.openNodesCounter++;
		}
		if((realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention)!=null) &&
				(realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus!=1)){
			firedCell2=firingCell(currentState.xDimention + 1, currentState.yDimention);
			
			if((firedCell2.viewWeight==0) && (realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus!=2))
				P.openNodesCounter++;
		}
		if((realMaze.returnBlock(currentState.xDimention , currentState.yDimention - 1)!=null)&&
				(realMaze.returnBlock(currentState.xDimention , currentState.yDimention - 1).blockStatus!=1)){
			firedCell3=firingCell(currentState.xDimention , currentState.yDimention - 1);
			
			if((firedCell3.viewWeight==0)&&(realMaze.returnBlock(currentState.xDimention, currentState.yDimention-1).blockStatus!=2))
				P.openNodesCounter++;
		}
		if((realMaze.returnBlock(currentState.xDimention , currentState.yDimention + 1)!=null)&&
				(realMaze.returnBlock(currentState.xDimention , currentState.yDimention + 1).blockStatus!=1)){
			firedCell4=firingCell(currentState.xDimention , currentState.yDimention + 1);
			
			if((firedCell4.viewWeight==0)&&(realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus!=2))
				P.openNodesCounter++;
		}
		
		
		if(P.openNodesCounter==0)
			return null;
		
		//this is to adjust view weights Connections.adjustViewsWeights(firedCell0, firedCell1, firedCell2, firedCell3, firedCell4);
		if(mode==1){//this is not used for GNM basic
		if(firedCell1!=null){
			firedCell1.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention);
		}
		if(firedCell2!=null){
			firedCell2.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention);
		}
		if(firedCell3!=null){
			firedCell3.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1);
		}
		if(firedCell4!=null){
			firedCell4.viewWeight++;
			if(realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1).blockStatus==3)
				return realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1);
		}
		}
		GridCell bestChoice = chooseBestConnection(firedCell1, firedCell2, firedCell3, firedCell4);
		
		
		//TrackCell(firedCell0);
		
		/*if(bestChoice == null)
			return null;*/

		if(bestChoice ==firedCell1){
			firedCell0.weight++; 
			firedCell1.weight++;
			return realMaze.returnBlock(currentState.xDimention - 1, currentState.yDimention);
		}else if(bestChoice == firedCell2){
			firedCell0.weight++; 
			firedCell2.weight++;
			return realMaze.returnBlock(currentState.xDimention + 1, currentState.yDimention);
		}else if(bestChoice == firedCell3){
			firedCell0.weight++; 
			firedCell3.weight++;
			return realMaze.returnBlock(currentState.xDimention, currentState.yDimention - 1);
		}else if(bestChoice == firedCell4){
			firedCell0.weight++; 
			firedCell4.weight++;
			return realMaze.returnBlock(currentState.xDimention, currentState.yDimention + 1);
		}else{
			System.out.println("Choose weight is not working correctly, something is wrong!!");
			return null;
		}
	}

	public void showSpikes(){
		for(int j=0;j<realMaze.maxYDimention;j++){
			for(int i=0;i<realMaze.maxXDimention;i++){
				//if((realMaze.returnBlock(i, j).blockStatus!=2) && (realMaze.returnBlock(i, j).blockStatus!=3))
					System.out.print(firingCell(i,j)+"\t");
				//else System.out.print(" "+"\t");
					
			}System.out.println();
		}System.out.println();
	}
	public GridCell chooseBestConnection(GridCell gc1, GridCell gc2, GridCell gc3, GridCell gc4){
		int index=0;
		ArrayList<GridCell> weights = new  ArrayList<GridCell>();
		ArrayList<GridCell> viewWeights =	new  ArrayList<GridCell>();
			
		
		if(gc1!=null)
			weights.add(gc1);
		if(gc2!=null)
			weights.add(gc2);
		if(gc3!=null)
			weights.add(gc3);		
		if(gc4!=null)
			weights.add(gc4);	
		weights.sort(new wComparator());
		
		
		//it is not enough to get the first min, we have to choose a min randomely between those that are equal.		
		if(((index+1)>=weights.size())||(weights.get(index).weight!=weights.get(index+1).weight)){
			if(weights.size()==0)
				realMaze.showMaze();
			viewWeights.add(weights.get(index));	
		}else{		
			while(((index+1)<weights.size())&&(weights.get(index).weight==weights.get(index+1).weight)){				
				viewWeights.add(weights.get(index));	
				index++;
			}
			if((index>0)&&(weights.get(index).weight==weights.get(index-1).weight)){				
				viewWeights.add(weights.get(index));
			}
		}
				
		viewWeights.sort(new vwComparator());
		
		
		//I don't know why random numbers do not work and always returns 1 so I  changed the seed from seed++ to seed*500 to get better randoms
		index=0;
		while(((index+1)<viewWeights.size())&&(viewWeights.get(index).viewWeight==viewWeights.get(index+1).viewWeight)){
			index++;
		}
		
		
		/*Random rand;
		
		if(P.seed==-1){
			rand = new Random();
		}else{
			int x =P.seed;
			P.seed=P.seed*500;
			rand = new Random(x);
		}*/
		
		int bestIndex  = rand.nextInt(index+1);
		
		/*if(viewWeights.get(bestIndex).w==P.maxWeight)
			return null;*/
		
		/*System.out.print("Index:"+index+" bestIndex:"+bestIndex+"Choice is beweeen:");
		for(int i=0;i<viewWeights.size();i++){
			System.out.print(viewWeights.get(i).w+" ");
		}System.out.println(" We have chosen:"+viewWeights.get(bestIndex).w);*/
		return viewWeights.get(bestIndex);
	}

	public GridCell firingCell(int x, int y) {
		int colDis=y/realModule.maxYDimention;
		int dMr=0;
		int dMc=0;
		if(colDis % 2 == 0){
			dMr = x % realModule.maxXDimention;
			dMc = y % realModule.maxYDimention;
		}else{
			dMr = (x+realModule.h) % realModule.maxXDimention;
			dMc = y % realModule.maxYDimention;
		}
		
		return realModule.returnBlock(dMr,dMc);
	}
}

class wComparator implements Comparator<GridCell>{
	@Override
	public int compare(GridCell cb1, GridCell cb2){
		if(cb1.weight > cb2.weight)
			return 1;
		else if(cb1.weight == cb2.weight)
			return 0;
		else
			return -1;
	}
}

class vwComparator implements Comparator<GridCell>{
	@Override
	public int compare(GridCell cb1, GridCell cb2){
		if(cb1.viewWeight > cb2.viewWeight)
			return 1;
		else if(cb1.viewWeight == cb2.viewWeight)
			return 0;
		else
			return -1;
	}
}