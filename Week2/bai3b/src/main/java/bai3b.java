import redis.clients.jedis.Jedis;

import static spark.Spark.*;

public class bai3b {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost");
        jedis.auth("abc@123");

        get("/num", (req,res)->{
            String value = req.queryParams("num");
            return jedis.hmget("data",value);
        });

        jedis.close();
    }
}
