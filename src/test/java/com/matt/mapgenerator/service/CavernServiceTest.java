package com.matt.mapgenerator.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.CoreMatchers.is;

@SpringBootTest
public class CavernServiceTest {

    @Test
    public void shouldReturnMapWithSpecifiedDimensions() {
        // Given
        CavernService cavernService = new CavernService();

        int targetWidth = 2;
        int targetHeight = 2;

        int[][] emptyMap = new int[][]{{0, 0}, {0, 0}};

        // When
        int[][] generatedMap = cavernService.setWidth(targetWidth).setHeight(targetHeight).build();

        // Then
        assertThat(generatedMap.length, is(targetWidth));
        assertThat(generatedMap[0].length, is(targetHeight));
    }

    @Test
    public void shouldReturnEmptyArrayIfSizeNegative() {
        // Given
        CavernService cavernService = new CavernService();

        int targetWidth = -2;
        int targetHeight = 2;

        int[][] emptyMap = new int[][]{};

        // When
        int[][] generatedMap = cavernService.setWidth(targetWidth).setHeight(targetHeight).build();

        // Then
        assertThat(generatedMap.length, is(0));
        assertThat(generatedMap, is(emptyMap));
    }
}
