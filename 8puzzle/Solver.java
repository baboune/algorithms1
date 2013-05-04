public class Solver {
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

    }

//    // is the initial board solvable?
//    public boolean isSolvable() {
//
//    }
//
//    // min number of moves to solve initial board; -1 if no solution
//    public int moves() {
//
//    }
//
//    // sequence of boards in a shortest solution; null if no solution
//    public Iterable<Board> solution() {
//
//    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        //test toString()
        StdOut.println(initial.toString());
        
        //test dimension()
        StdOut.println("dimension: " + initial.dimension());
        
        //test hamming()
        StdOut.println("hamming: " + initial.hamming());
        
        //test manhattan()
        StdOut.println("manhattan: " + initial.manhattan());
        
        //test isGoal()
        StdOut.println("isgoal: " + initial.isGoal());
        
        //test twin()
        StdOut.println("twin: \n" + initial.twin().toString());
        
        //test equals()
        StdOut.println("equals self should be true: " + initial.equals(initial));
        StdOut.println("equals twin should be false: " + initial.equals(initial.twin()));
        
        StdOut.println("neighbors: \n" + initial.neighbors());
    }
}