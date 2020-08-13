package com.bai3;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.File;

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

        File directory = new File("data");
        File[] files = directory.listFiles();
        String dirWordList = "";

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (File file: files){
            Callable<String> task = () -> {
                String wordList = readFile("data/" + file.getName());
                return wordList;
            };
            Future<String> future = executor.submit(task);
            dirWordList += future.get() + " ";
        }

        executor.shutdown();
        checkFrequency(dirWordList);
    }
}
