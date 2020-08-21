import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Save {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.auth("abc@123");

        HashMap<String, String> list = new HashMap<>();


        for (int i = 0; i < 10000; i++){
            list.put(String.valueOf(i), String.valueOf((int) Math.pow(Double.valueOf(i),2)));
        }
        jedis.hmset("data", list);
        jedis.close();
    }
}
