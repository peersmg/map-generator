package com.matt.mapgenerator.controller;

import com.matt.mapgenerator.service.CavernService;
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

        int targetWidth = 20;
        int targetHeight = 10;

        // When
        int[][] generatedMap = cavernService.generateCavern(targetWidth, targetHeight);

        // Then
        assertThat(generatedMap.length, is(targetWidth));
        assertThat(generatedMap[0].length, is(targetHeight));
    }
}
