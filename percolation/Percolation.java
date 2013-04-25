//import java.util.Arrays;

public class Percolation {
    private boolean[] grid;
    private int NUM;
    private WeightedQuickUnionUF uf;
//    private int open_count;

    public Percolation(int N) {
        // the 2 is for virtual top and bottom
        grid = new boolean[N * N + 2];
        uf = new WeightedQuickUnionUF(N * N + 2);
        NUM = N;
//        open_count = 0;
        
        // create a union with virtual top and top row
        for(int i = 1; i <= N; i++) {
            uf.union(0, i);
        }
        
        // create a union with virtual bottom and bottom row
        for(int i = N * N; i > N * N - N; i--) {
            uf.union(N * N + 1, i);
        }
    }
    
    private boolean[] get_grid() {
        return grid;
    }
    
    // convert 2d point to 1d
    private int pointer(int i, int j) {
        int pointer = 1;
        pointer = pointer + ((j - 1) * NUM);
        pointer = pointer + (i - 1);
        return pointer;
    }
    
//    public int get_open_count() {
//        return open_count;
//    }
    
    public void open(int i, int j) {
        // set block to open
        if(!grid[pointer(i, j)]) {
            grid[pointer(i, j)] = true;
//            open_count++;
            // create union with open adjacent blocks
            // ensure its within bounds of array
            if(i < 1 || i > NUM || j < 1 || j > NUM) {
                throw new java.lang.IndexOutOfBoundsException();
            }
            if(i < NUM && isOpen(i+1, j)) {uf.union(pointer(i, j), pointer(i+1, j));}
            if(i > 1 && isOpen(i-1, j)) {uf.union(pointer(i, j), pointer(i-1, j));}
            if(j < NUM && isOpen(i, j+1)) {uf.union(pointer(i, j), pointer(i, j+1));}
            if(j > 1 && isOpen(i, j-1)) {uf.union(pointer(i, j), pointer(i, j-1));}            
        }
    }
    
    public boolean isOpen(int i, int j) {
        if(i < 1 || i > NUM || j < 1 || j > NUM) {
            throw new java.lang.IndexOutOfBoundsException();
        }        
        return grid[pointer(i, j)];
    }
    
    public boolean isFull(int i, int j) {
        if(i < 1 || i > NUM || j < 1 || j > NUM) {
            throw new java.lang.IndexOutOfBoundsException();
        }        
        // check that point is connected to virtual top and is open
        return (isOpen(i, j) && uf.connected(0, pointer(i, j)));
    }
    
    public boolean percolates() {
        return uf.connected(0, NUM * NUM + 1);
    }
    
    public static void main(String[] args) {
//        Percolation perc = new Percolation(3);
//        if(!perc.isOpen(2, 3)) {StdOut.println("2-3 is not open");}
//        if(perc.percolates()) {StdOut.println("percolates");} else {StdOut.println("does not percolate");}
//        perc.open(2, 3);
//        if(perc.percolates()) {StdOut.println("percolates");} else {StdOut.println("does not percolate");}        
//        perc.open(2, 2);
//        if(perc.percolates()) {StdOut.println("percolates");} else {StdOut.println("does not percolate");}        
//        perc.open(2, 1);
//        if(perc.percolates()) {StdOut.println("percolates");} else {StdOut.println("does not percolate");}        
//        if(perc.isOpen(2, 3)) {StdOut.println("2-3 is open");}
//        if(perc.uf.connected(0, 1)) {StdOut.println("0 and 1 connected");}
//        if(perc.uf.connected(0, 2)) {StdOut.println("0 and 2 connected");}
//        if(perc.uf.connected(0, 3)) {StdOut.println("0 and 3 connected");}  
//        if(!perc.uf.connected(0, 4)) {StdOut.println("0 and 4 is not connected");}
//        if(perc.isFull(2,2)) {StdOut.println("2-2 is full");}
//        perc.open(1, 1);
//        if(perc.isFull(1,1)) {StdOut.println("1-1 is full");}
//        if(!perc.isFull(3,3)) {StdOut.println("3-3 is not full");}
//        StdOut.println("1d NxN grid:");
        //StdOut.println(Arrays.toString(perc.get_grid()));
        
        Percolation p = new Percolation(10);
        p.open(10, 6);
        
    }
}