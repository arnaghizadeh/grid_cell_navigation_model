package GNM;


import Lattice.Maze;
import Lattice.Module;
import Search.*;


public class Main {

    public static void main(String[] args) {
        //P.seed=1;
        //DebugTests.Random();
        System.out.println("*****The simulation begins*****");
		/*Maze ma  = new Maze(10,10);
		ma.showMaze();*/

		/*Maze ma  = new Maze(10,10, 5);
		ma.showMaze();*/


        Maze ma = new Maze(10, 10, 5, 0, 0, 5, 5);
        ma.showMaze();

        /*QLearning ql = new QLearning(ma);
        ql.findPath();
        ma.showMaze();

        /*LRTA lrta = new LRTA(ma);
		lrta.findPath();
        ma.showMaze();*/

       /* Backtrack bt = new Backtrack(ma);
        bt.findPath();
        ma.showMaze();*/

        /*IDDFS iddfs = new IDDFS(ma);
        iddfs.findPath();
        ma.showMaze();*/

        Module mo = new Module(10);
        GNMSearch hs = new GNMSearch(ma,mo,0);
        hs.findPath();
        ma.showMaze();
    }

}
