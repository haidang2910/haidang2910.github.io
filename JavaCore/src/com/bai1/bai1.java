package com.bai1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class bai1 {
    public static void main(String[] args) {
        Random generator = new Random();
        Set<Integer> hashsetInteger1 = new HashSet<>();
        Set<Integer> hashsetInteger2 = new HashSet<>();
        int count = 0;

        for (int i = 0; i < 200000; i++){
            if (hashsetInteger1.size() < 200000) {
                for (int j = 0; j < 200000 - hashsetInteger1.size(); i++){
                    hashsetInteger1.add(generator.nextInt());
                }
            }
        }

        for (int i = 0; i < 200000; i++){
            if (hashsetInteger2.size() < 200000) {
                for (int j = 0; j < 200000 - hashsetInteger2.size(); i++){
                    hashsetInteger2.add(generator.nextInt());
                }
            }
        }

        Iterator<Integer> loop = hashsetInteger1.iterator();
        while (loop.hasNext()){
            int value = loop.next();
            if (hashsetInteger2.contains(value)){
                System.out.println(value);
                count++;
            }
        }

        System.out.println("The same element number is: " + count);
    }
}
