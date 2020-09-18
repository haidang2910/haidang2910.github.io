import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCount_Map extends Mapper<LongWritable, Text, Text, IntWritable>{
    public void map (LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        line = line.replaceAll("//s.+", " ");
        line = line.replaceAll("//s,+", " ");
        line = line.trim();
        String[] words = line.split("\\s");
        for (String word: words){
            Text outputKey = new Text(word.toLowerCase().trim());
            IntWritable outputValue = new IntWritable(1);
            context.write(outputKey, outputValue);
        }

    }
}
