import java.util.ArrayList;
import java.util.List;

/**
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class BloomFilter<E> {

    private final int k;  // number of hash functions
    private final int m;  // size of each hash array
    private HashFunction[] hashArr;
    private boolean[] arr; // arr (should be size m) to store the value

    public BloomFilter(int k, int m) {
        this.k = k;
        this.m = m;
        this.arr = new boolean[m];
        //can use Booleal.arrfill

        for (int i = 0; i < m; i++) {
          this.arr[i]= false;
        }

        this.hashArr = hashFill(k, m);
        // you may want to initialize some private fields here
    }

    private HashFunction[] hashFill(int k, int m) {
      HashFunction[] hashArr = new HashFunction[k];
      for (int i = 0; i < k; i++) {
        hashArr[i] = new HashFunction(i, m);
      }
      return hashArr;
    }

    public boolean lookUp(E item) {
      boolean inArr = true;
      for (int i = 0; i < k; i++) {
        inArr = inArr && arr[hashArr[i].hash(item)];
      }
      return inArr;
    }

    public void insert(E item) {
      for (int i = 0; i < k; i++) {
        arr[hashArr[i].hash(item)] = true;
      }
        //throw new UnsupportedOperationException("implement me!");
    }

    public static void main(String[] args) {
      int k = 20;
      int m = 1000000;
      int n = 100000;
      BloomFilter bloom = new BloomFilter(k, m);

      for (int i = 0; i < n; i++) {
        bloom.insert(i);
      }

      int count = 0;

      for (int i = 0; i<n; i++) {
        if (bloom.lookUp(n+i)) {
          count++;
        }
      }

      float rate = count/n;

      System.out.println("False positive probability count: ", count);
      System.out.println("False positive probability rate: ", rate);

        // complete the necessary experiment for 10.104 here; please write up your findings in markdown
    }

}
