package com.matt.mapgenerator.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapSaver {
    public void saveMap(int[][] map) {
        // Create file
        try {
            File myObj = new File("map.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error printing map");
        }

        // Write to file
        try {
            FileWriter myWriter = new FileWriter("map.txt");
            myWriter.write("Map:");
            myWriter.write('\n');

            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x< map[0].length; x++) {
                    if(map[y][x] == 0) {
                        myWriter.write(".");
                    }
                    else if(map[y][x] == 1) {
                        myWriter.write("#");
                    }
                }
                myWriter.write('\n');
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
