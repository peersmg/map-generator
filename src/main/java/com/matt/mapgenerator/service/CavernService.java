package com.matt.mapgenerator.service;

import org.springframework.stereotype.Service;

@Service
public class CavernService {

    public int[][] generateCavern(int targetWidth, int targetHeight) {
        int[][] cavernMap = {};
        if(targetWidth >= 0 && targetHeight >= 0) {
            cavernMap = new int[targetWidth][targetHeight];

            for (int x = 0; x< targetWidth; x++) {
                for (int y = 0; y< targetHeight; y++) {
                    cavernMap[y][x] = 0;
                }
            }
        }

        return cavernMap;
    }
}
