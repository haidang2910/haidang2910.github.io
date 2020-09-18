package BenchMark;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Callable;

public class Connect implements Callable<Data> {

    Data data;
    Data result = new Data();
    int numRes = 0;
    int numResF = 0;

    public Connect(Data data) {
        this.data = data;
    }

    public static int random() {
        Random rd = new Random();
        return rd.nextInt(10000);
    }

    @Override
    public Data call() throws Exception {

        long startTime = System.currentTimeMillis();

        try {
            StringBuffer response = new StringBuffer();

            while (System.currentTimeMillis() - startTime < data.getTime()) {
                double start = System.nanoTime();
                URL url = new URL("http://localhost:4567/num?num=" + random());
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.connect();

                if (urlCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    numRes++;
                }
                else if (urlCon.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    numResF++;
                }
                double tpr = System.nanoTime() - start;

                if (result.getMax() < tpr/1000000) result.setMax(tpr/1000000);
                if (result.getMin() > tpr/1000000) result.setMin(tpr/1000000);

                result.addResult(tpr/1000000);

            }

            result.setRes(numRes);
            result.setRef(numResF);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}
