import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenText1 {

    private static Random Rand = new Random();
    public static int ranGrade() {
        Random rd = new Random();

        return Math.abs(rd.nextInt(10));
    }

    public static int ranID() {
        Random rd = new Random();

        return Math.abs(rd.nextInt(10)) + 1;
    }
     public static String genTest(){
        List<String> subList = new ArrayList<>();
         subList.add("15-minute test");
         subList.add("45-minute test");
         subList.add("final test");
         int ranTest =Rand.nextInt(subList.size());
         return subList.get(ranTest);
     }

     public static String genSubject(){
         List<String> subList = new ArrayList<>();
         subList.add("Math");
         subList.add("PE");
         subList.add("Science");
         int ranSub =Rand.nextInt(subList.size());
         return subList.get(ranSub);
     }

    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("text1.txt");
            for (int i = 0; i < 100000; i++){
                fw.write(String.valueOf(ranID()) + "\t" + String.valueOf(ranGrade()) + "\t" +  genSubject() + "\t" + genTest() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
