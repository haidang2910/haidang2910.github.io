import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.util.LongAccumulator;
import scala.Tuple2;
import javax.sound.sampled.Line;
import java.util.Iterator;
import java.util.Arrays;

public class WordCounter {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf().setAppName("Word Count");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaRDD<String> inputFile = sparkContext.textFile("hdfs://localhost:9000/user/dang/text");
        LongAccumulator accLetter = new LongAccumulator();
        LongAccumulator accLine = new LongAccumulator();

        //count words
        JavaRDD<String> wordsFormFile = inputFile.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split("\\s")).iterator();
            }
        });

        //count letter
        JavaRDD<String> lettersFormFile = inputFile.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.replaceAll("[^a-z^A-Z]","").split("")).iterator();
            }
        });

        //count lines
        JavaRDD<String> linesFormFile = inputFile.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split("\\n")).iterator();
            }
        });

        JavaPairRDD<String, Integer> countWords = wordsFormFile.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        JavaPairRDD<String, Integer> countLine = linesFormFile.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });


        JavaPairRDD<String, Integer> countLetters = lettersFormFile.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2(s, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

//        countData.collect().forEach(System.out::println);
//        countData.collect().forEach(System.out::println);

        for (Tuple2<String, Integer> tuple2: countLetters.collect()){
            accLetter.add(tuple2._2);
        }

        for (Tuple2<String, Integer> tuple2: countLine.collect()){
            accLine.add(tuple2._2);
        }

        countWords.collect().forEach(System.out::println);
        System.out.println("number of letter: " + accLetter.value());
        System.out.println("number of line: " + accLine.value());

        //countData.saveAsTextFile("hdfs://localhost:9000/user/dang/CountData6");
    }
}
