import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class jsonReader {
    static int S = 0;
    static int oldS;

    public static void setS(int s) {
        S = s;
    }

    public static void setOldS(int oldS) {
        jsonReader.oldS = oldS;
    }

    public static int getS() {
        return S;
    }

    public static int getOldS() {
        return oldS;
    }

    private static final Logger logger = LoggerFactory.getLogger(jsonReader.class);

    public static void main(String[] args) throws Exception {

        ScanS threadS = new ScanS();
        GetS threadG = new GetS();

        Thread thread1 = new Thread(threadG);
        Thread thread2 = new Thread(threadS);

        thread1.start();
        thread2.start();


    }
}
