package com.matt.mapgenerator.utils;

public class Util {

    public static int[][] clone2DArray(int[][] array) {
        int[][] cloned = new int[array.length][array[0].length];

        for (int y = 0; y< array.length; y++) {
            for (int x = 0; x< array[0].length; x++) {
                cloned[y][x] = array[y][x];
            }
        }
        return cloned;
    }
}
