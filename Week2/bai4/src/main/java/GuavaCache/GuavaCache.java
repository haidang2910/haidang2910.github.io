package GuavaCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCache{
    public static LoadingCache<String, String> cache;
    static {

        cache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {

            @Override
            public String load(String s) throws Exception {

                Jedis jedis = new Jedis("localhost");
                String value = jedis.hget("data", s);
                jedis.close();

                return value;
            }
        });
    }

}
