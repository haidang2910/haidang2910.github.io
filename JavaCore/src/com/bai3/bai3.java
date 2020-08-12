package com.bai3;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class bai3 {
    public static String readFile (String fileName) {

        BufferedReader br = null;
        FileReader fr = null;
        String txt = "";

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String currentLine;

            br = new BufferedReader(new FileReader(fileName));

            while ((currentLine = br.readLine()) != null){
                txt += currentLine;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            }

        return txt;
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

    public static void checkFrequency (String result){

        String[] words = result.split("\\s");
        int size = words.length;

        // add word to map
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < size; i++){
            addElement(map, words[i]);
        }

        List<Map.Entry<String, Integer>> sortedMap = new ArrayList<Map.Entry<String, Integer>>();
        sortedMap.addAll(map.entrySet());

        //sort map
        Collections.sort(sortedMap, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
                return stringIntegerEntry.getValue().compareTo(t1.getValue());
            }
        });

        System.out.println("10 most common words:"+sortedMap.subList(sortedMap.size()-10, sortedMap.size()));
        System.out.println("10 words that appear at least:" + sortedMap.subList(0,10));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> task1 = () -> {
            String txt1 = readFile("data/1.txt");
            return txt1;
        };

        Callable<String> task2 = () -> {
            String txt2 = readFile("data/2.txt");
            return txt2;
        };

        Callable<String> task3 = () -> {
            String txt3 = readFile("data/3.txt");
            return txt3;
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<String> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        Future<String> future3 = executor.submit(task3);
        String result = future1.get() + " " + future2.get() + " " + future3.get();
        executor.shutdown();

        checkFrequency(result);

    }
}
