public class Subset {
    public static void main(String[] args) {
        String stringSequence = StdIn.readLine();
        String[] stringArr = stringSequence.split(" ");
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for(String s : stringArr) {
            q.enqueue(s);
        }
        for(int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}