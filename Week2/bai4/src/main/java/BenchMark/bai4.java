package BenchMark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class bai4 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 4; i++){
            executor.execute(new Call());
        }

        System.out.println(System.currentTimeMillis() - startTime);

        executor.shutdown();
    }
}
