package BenchMark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class bai4 {

    static Data data = new Data ();
    static double min = Double.MAX_VALUE, mean = 0.0, p90 = 0.0, p95 = 0.0, max = 0.0;
    static int sucRes = 0;
    static int falRes = 0;
    static double sec;
    static Callable<Data> callable;
    static Future<Data> future;
    static List<Future<Data>> list = new ArrayList<>();
    static List<Double> TimeReq = new ArrayList<>();


    public static void show(){
        System.out.println("Running in "+ sec/1000 + " seconds and by 4 threads");
        System.out.println("Completed " + sucRes + " connections and " + falRes + " false");
        System.out.println("Max time: " + Math.round(max*100.0)/100.0 + "ms");
        System.out.println("Min time: " + Math.round(min*100.0)/100.0 + "ms");
        System.out.println("Mean: " + Math.round(mean*100.0)/100.0 + "ms");
        System.out.println("P90: " + Math.round(p90*100.0)/100.0 + "ms");
        System.out.println("P95: " + Math.round(p95*100.0)/100.0 + "ms");
    }

    public static void probabilityCalculation(){
        Collections.sort(TimeReq);
        for (int i = 0; i < TimeReq.size(); i++){
            mean = TimeReq.get(TimeReq.size()/2);
            p90 = TimeReq.get((int) (TimeReq.size()*0.9));
            p95 = TimeReq.get((int) (TimeReq.size()*0.95));
        }
    }

    public static void execute() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++){
            callable = new Connect(data);
            future = executor.submit(callable);
            list.add(future);
        }

        executor.shutdown();

        //set info
        for (Future<Data> i : list){
            sucRes += i.get().getRes();
            falRes += i.get().getRef();
            if (i.get().getMin() < min) min = i.get().getMin();
            if (i.get().getMax() > max) max = i.get().getMax();
            TimeReq.addAll(i.get().getTimeRequests());
        }
    }

    public static void initTime(){
        System.out.println("Time: ");
        Scanner scanner = new Scanner(System.in);
        sec = scanner.nextInt();
        data.setTime(sec);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        initTime();
        execute();
        probabilityCalculation();
        show();

    }
}
