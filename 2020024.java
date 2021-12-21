import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// no need for a public class
// compile with `javac 2020024.java`
// run with `java Pascal [no. of threads]`
// refer to bottom of file for example

class Pascal extends RecursiveTask<Integer> {
    private final int n, k;
    // every task br-2
    // caching = execessive mem usage [TEST-fail]

    // we can only thresh at n, (k is dependent on n)
    private static final int thresh = 20; // tree grows bottom up
    // 300ms powerup when thresh at 20 than at 10

    public Pascal(int n, int k) {
        this.n = n;
        this.k = k;
    }

    private Integer seqC(int n, int k) {
        if ((n == k) || (n == 0) || (k == 0))
            return 1;
        else {
            return seqC(n - 1, k - 1) + seqC(n - 1, k);
        }
    }

    public Integer compute() {
        if ((this.k == 0) || (this.k == this.n))
            return 1;
        if (this.n < thresh)
            return seqC(this.n, this.k);
        final Pascal left = new Pascal(this.n - 1, this.k - 1);
        final Pascal right = new Pascal(this.n - 1, this.k);
        left.fork();
        return right.compute() + left.join();
    }

    public static void main(String[] args) {

        final int poolSize = Integer.parseInt(args[0]);
        final ForkJoinPool pool = new ForkJoinPool(poolSize); // 2 is good enough, time doesnt change
        System.out.printf("Utilizing %d threads\n", poolSize);

        final Pascal task = new Pascal(30, 10);
        final int result = pool.invoke(task);

        System.out.println(result);
    }
}
// TESTRUN results
// (base) PS C:\Users\Andy\Desktop\my_AP_sem3> javac .\2020024.java
// (base) PS C:\Users\Andy\Desktop\my_AP_sem3> java Pascal 10000
// Utilizing 10000 threads
// 30045015