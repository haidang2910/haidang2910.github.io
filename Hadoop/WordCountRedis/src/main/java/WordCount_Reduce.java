import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;

public class WordCount_Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce (Text word, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values){
            sum += value.get();

        }
        Jedis jedis = new Jedis("localhost");
        HashMap<String, String> list = new HashMap<>();
        context.write(word, new IntWritable(sum));
        list.put(word.toString(), String.valueOf(new IntWritable(sum)));
        jedis.hmset("wordCount", list);
        jedis.close();
    }
}
