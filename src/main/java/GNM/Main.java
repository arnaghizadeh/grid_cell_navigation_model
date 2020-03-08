package GNM;


import Lattice.Maze;
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
        //ma.showMaze();

        /*QLearning ql = new QLearning(ma);
        ql.findPath();
        ma.showMaze();

        /*LRTA lrta = new LRTA(ma);
		lrta.findPath();
        ma.showMaze();*/

        Backtrack bt = new Backtrack(ma);
        bt.findPath();
        ma.showMaze();

    }

}
