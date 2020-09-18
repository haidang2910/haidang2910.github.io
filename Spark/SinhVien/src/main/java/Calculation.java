import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.*;
import org.apache.spark.util.LongAccumulator;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class Calculation {


    public static void calGra(String file1, String file2){
        SparkConf sparkConf = new SparkConf().setAppName("Word Count");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaRDD<String> txt1 = sparkContext.textFile(file1);
        JavaRDD<String> txt2 = sparkContext.textFile(file2);
        SQLContext sqlContext = new SQLContext(sparkContext);
        List<Student> list = new ArrayList<>();


        JavaRDD<String> data1 = txt1.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.replaceAll("15-minute test", "1").replaceAll("45-minute test","5").replaceAll("final test","5").split("\\n")).iterator();
            }
        });

        // tinh tong he so tung sinh vien theo tung mon
        JavaPairRDD<String, Double> sumCoe = data1.mapToPair(new PairFunction<String, String, Double>() {
            @Override
            public Tuple2<String, Double> call(String s) throws Exception {
                String[] tmp = s.split("\\t");
                return new Tuple2(tmp[0] + " " + tmp[2], Double.parseDouble(tmp[3]));
            }
        }).reduceByKey(new Function2<Double, Double, Double>() {
            @Override
            public Double call(Double aDouble, Double aDouble2) throws Exception {
                return aDouble + aDouble2;
            }
        });

        // tinh tong diem sinh vien theo tung mon
        JavaPairRDD<String, Double> sumGrade = data1.mapToPair(new PairFunction<String, String, Double>() {
            @Override
            public Tuple2<String, Double> call(String s) throws Exception {
                String[] tmp = s.split("\\t");
                return new Tuple2(tmp[0] + " " + tmp[2], Double.parseDouble(tmp[1])*Double.parseDouble(tmp[3]));
            }
        }).reduceByKey(new Function2<Double, Double, Double>() {
            @Override
            public Double call(Double aDouble, Double aDouble2) throws Exception {
                return aDouble + aDouble2;
            }
        });

        //tinh trung binh
        JavaPairRDD<String, Double> test = sumGrade.union(sumCoe).reduceByKey(new Function2<Double, Double, Double>() {
            @Override
            public Double call(Double aDouble, Double aDouble2) throws Exception {
                return aDouble/aDouble2;
            }
        });

        JavaRDD<String> data = test.flatMap(new FlatMapFunction<Tuple2<String, Double>, String>() {
            @Override
            public Iterator<String> call(Tuple2<String, Double> stringDoubleTuple2) throws Exception {
                return Arrays.asList(stringDoubleTuple2._1 + " " + stringDoubleTuple2._2).iterator();
            }
        });

        for (String s: data.collect()){
            list.add(new Student(Integer.parseInt(s.split("\\s")[0]), Double.parseDouble(s.split("\\s")[2]), s.split("\\s")[1]));
        }



        Dataset dataset = sqlContext.createDataset(list, Encoders.bean(Student.class));
        dataset.write().parquet("student.parquet");

        //dataset.show();
        //data.collect().forEach(System.out::println);
    }
    public static void main(String[] args) {
        calGra("hdfs://localhost:9000/user/dang/text1.txt","file:////home/dang/IdeaProjects/SinhVien/src/main/java/txt2.txt");
    }
}
