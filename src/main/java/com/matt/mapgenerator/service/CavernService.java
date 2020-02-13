package com.matt.mapgenerator.service;

import com.matt.mapgenerator.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class CavernService {

    private int mapWidth = 10;
    private int mapHeight = 10;
    private double wallPercentage = 0.30;
    private int[][] cavernMap;

    public CavernService setHeight(int height) {
        mapHeight = height;
        return this;
    }

    public CavernService setWidth(int width) {
        mapWidth = width;
        return this;
    }

    public int[][] build() {
        if(mapWidth >= 0 && mapHeight >= 0) {
            generateBaseMap();
            runAutomataWithTwoCheck();
            runAutomataWithTwoCheck();
            runAutomataNormal();
        }
        else {
            return new int[][]{};
        }

        return cavernMap;
    }

    private void generateBaseMap() {
        cavernMap = new int[mapHeight][mapWidth];

        int halfX = mapWidth/2;
        int halfY = mapHeight/2;

        for (int y = 0; y< mapHeight; y++) {
            for (int x = 0; x< mapWidth; x++) {

                if(isBorder(x,y)){
                    cavernMap[y][x] = 1;
                }
                else if(x == halfX || y == halfY || x+1==halfX || y+1 ==halfY) {
                    cavernMap[y][x] = 0;
                }
                else {
                    cavernMap[y][x] = getTileType(wallPercentage);
                }
            }
        }
    }

    private boolean isBorder(int x, int y){
        if(x == 0 || y == 0) {
            return true;
        }

        if(x == mapWidth || y == mapHeight){
            return true;
        }

        return false;
    }

    private void runAutomataNormal(){
        int[][] cavernMapCopy = Util.clone2DArray(cavernMap);

        for (int y = 0; y< mapHeight; y++) {
            for (int x = 0; x< mapWidth; x++) {
                int adj = countAdjacentWalls(x, y);
                cavernMapCopy[y][x] = (adj > 5) ? 1 : 0;
            }
        }

        cavernMap = cavernMapCopy;
    }

    private void runAutomataWithTwoCheck(){
        int[][] cavernMapCopy = Util.clone2DArray(cavernMap);

        for (int y = 0; y< mapHeight; y++) {
            for (int x = 0; x< mapWidth; x++) {
                int adj = countAdjacentWalls(x, y);
                cavernMapCopy[y][x] = ((adj >= 5) || (adj < 2)) ? 1 : 0;
            }
        }

        cavernMap = cavernMapCopy;
    }

    private int countAdjacentWalls(int x, int y) {
        int adjacentWalls = 0;

        if(x-1 < 0 || x+1 >= mapWidth || y-1 < 0 || y+1>=mapHeight) {
            adjacentWalls += 3;
        }

        if(x-1 >= 0) {
            adjacentWalls += cavernMap[y][x-1] == 1 ? 1:0;

            if(y-1 >= 0) {
                adjacentWalls += cavernMap[y-1][x-1] == 1 ? 1:0;
            }

            if(y+1 < mapHeight){
                adjacentWalls += cavernMap[y+1][x-1] == 1 ? 1:0;
            }
        }

        if(y-1 >= 0) {
            adjacentWalls += cavernMap[y-1][x] == 1 ? 1:0;
        }

        if(x+1 < mapWidth) {
            adjacentWalls += cavernMap[y][x+1] == 1 ? 1:0;

            if(y-1 >= 0) {
                adjacentWalls += cavernMap[y - 1][x + 1] == 1 ? 1 : 0;
            }
        }

        if(y+1 < mapHeight) {

            adjacentWalls += cavernMap[y+1][x] == 1 ? 1:0;

            if(x+1 < mapWidth) {
                adjacentWalls += cavernMap[y+1][x+1] == 1 ? 1:0;
            }
        }

        adjacentWalls += cavernMap[y][x];

        return adjacentWalls;
    }

    private int getTileType(double wallPercentage) {
        return Math.random() < wallPercentage ? 1 : 0;
    }
}
