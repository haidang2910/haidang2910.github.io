package com.bai1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class bai1 {

    public static int random() {
        Random rd = new Random();

        return rd.nextInt(300000);
    }

    public static void genNum(Set<Integer> set) {
        while(set.size()<200000) {
            int s = random();
            set.add(s);
        }
    }

    public static void main(String[] args) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        genNum(setA);
        genNum(setB);

        System.out.println("Tap giao");
        setA.retainAll(setB);
        System.out.println(setA);


        System.out.println("Tap hop");
        setB.addAll(setA);
        System.out.println(setB);

    }
}