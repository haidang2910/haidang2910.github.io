package GuavaCache;

import java.util.concurrent.ExecutionException;

import static spark.Spark.*;

public class bai4b {
    public static void main(String[] args) {
        GuavaCache guavaCache = new GuavaCache();
        //jedis.auth("abc@123");

        get("/num", (req, res) -> {
            String value = req.queryParams("num");
            try {
                return GuavaCache.cache.get(req.queryParams("num"));
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return null;
        });
    }
}
