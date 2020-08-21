package com.bai2;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class bai2 {
    public static String readFile() throws IOException {
        BufferedReader br = null;
        FileReader fr = null;
        String txt = "";

        try {

            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            String currentLine;

            br = new BufferedReader(new FileReader("input.txt"));

            while ((currentLine = br.readLine()) != null){
                txt += currentLine + " ";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            fr.close();

        return txt;
    }

    public static void writeFile(String out){
        try {
            FileWriter fw = new FileWriter("output.txt");
            fw.write(out);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        String output = "";
        String[] words = readFile().split("\\s");

        int size = words.length;

        Map<String, Integer> map = new TreeMap<String, Integer>();
        for (int i = 0; i < size; i++){
            addElement(map, words[i]);
        }

        for (String key : map.keySet()){
            output += key + " appears " + map.get(key) + " times \n";
        }

        System.out.println(output);
        writeFile(output);

    }

    public static void addElement(Map<String, Integer> map, String element){
        if (map.containsKey(element)){
            int count = map.get(element) + 1;
            map.put(element, count);
        }
        else{
            map.put(element, 1);
        }
    }
}
