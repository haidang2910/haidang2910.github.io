package BenchMark;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Call implements Runnable {

    public static int random() {
        Random rd = new Random();

        return rd.nextInt(10000);
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public void run() {

        try {
            StringBuffer response = new StringBuffer();
            URL url = new URL("http://localhost:4567/num?num=" + random());
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.connect();
            System.out.println(readUrl(url.toString()));

            if (urlCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("ResponseCode = HTTP_OK");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
