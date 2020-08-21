import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetS implements Runnable{

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

    @Override
    public void run() {
        while(true){
            try {
                String json = readUrl("http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn");
                String str = json;
                JSONObject obj = new JSONObject(json);
                int tmp = jsonReader.getS();
                jsonReader.setOldS(tmp);
                jsonReader.setS(obj.getInt("user"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
