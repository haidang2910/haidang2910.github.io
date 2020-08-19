package com.bai4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class bai4 {
    static Set<String> setPoint = new HashSet<>();
    public static List toaDo (int numberOfPoint, int distance, int dx, int dy){

        int row, col;
        double d;

        List<String> txt = new ArrayList<>();

        Random rd = new Random();

        while (setPoint.size() < numberOfPoint){
            row = rd.nextInt(5000);
            col = rd.nextInt(5000);
            d = Math.sqrt(Math.pow((dx - row), 2) + Math.pow((dy - col), 2));

            if (d < distance){
                setPoint.add("x " + row + " " + "y " + col);
            }
        }

        return txt;
    }

    public static void writeFile(String out){
        try {
            FileWriter fw = new FileWriter("output4.txt");
            fw.write(out);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String output = "";
        List<String> pointList = new ArrayList<>();
        pointList.addAll(toaDo(8000,400, 800, 800));
        pointList.addAll(toaDo(10000,500, 4000,800));
        pointList.addAll(toaDo(12000,600, 2400, 2400));

        pointList.addAll(setPoint);

        Collections.shuffle(pointList);

        Iterator<String> iterator = pointList.iterator();
        while (iterator.hasNext()) {
            output += (String) iterator.next() + "\n";
        }

        System.out.println(output);
        writeFile(output);
    }
}
