package com.matt.mapgenerator.controller;

import com.matt.mapgenerator.service.CavernService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MapControllerTest {

    @Mock
    CavernService cavernService;

    @BeforeEach
    public void init() {
        cavernService = mock(CavernService.class);
    }

    @Test
    public void shouldRequestServiceWithGivenValues() {
        // Given
        MapController mapController = new MapController(cavernService);

        int targetWidth = 20;
        int targetHeight = 10;

        // When
        ResponseEntity response = mapController.getCavernMap(targetWidth, targetHeight);

        // Then
        verify(cavernService).generateCavern(targetWidth, targetHeight);
    }

    @Test
    public void shouldReturnMapJson() {
        // Given
        int targetWidth = 2;
        int targetHeight = 2;

        when(cavernService.generateCavern(targetWidth, targetHeight)).thenReturn(new int[][]{{0,0},{0,0}});

        // When
        MapController mapController = new MapController(cavernService);
        ResponseEntity response = mapController.getCavernMap(targetWidth, targetHeight);

        // Then
        verify(cavernService).generateCavern(targetWidth, targetHeight);
        System.out.println(response);
        assertThat(response.getBody(), is("[[0,0],[0,0]]"));
    }
}
