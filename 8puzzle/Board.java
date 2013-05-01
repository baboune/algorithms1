public class Board {
    
    private int[][] blocks;
    private int dimension;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks;
        this.dimension = blocks.length;
    }
                                           
    // board dimension N
    public int dimension() {
        return blocks.length;
    }
    
    // number of blocks out of place
    public int hamming() {
        int blocks_out_of_place = 0;
        int expectation = 1;
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (blocks[row][col] != expectation) {
                    blocks_out_of_place++;
                }
                expectation++;
            }
        }
        // subtract 1 for empty block
        return blocks_out_of_place - 1;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0;
        // loop through each block
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                int block_value = blocks[row][col];
                if (block_value == 0) {
                    continue;
                }
                int goal_row = (block_value - 1) / dimension;
                int goal_col = (block_value - 1) % dimension;
                int row_distance = Math.abs(goal_row - row);
                int col_distance = Math.abs(goal_col - col);
                sum = sum + row_distance + col_distance;
            }
        }
        return sum;
    }
    
//    // is this board the goal board?
//    public boolean isGoal() {
//
//    }
//    
//    // a board obtained by exchanging two adjacent blocks in the same row
//    public Board twin() {
//
//    }
//    
//    // does this board equal y?
//    public boolean equals(Object y) {
//
//    }
//    
//    // all neighboring boards
//    public Iterable<Board> neighbors() {
//
//    }
//    
    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}