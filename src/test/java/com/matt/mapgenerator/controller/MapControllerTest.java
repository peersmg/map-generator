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

        when(cavernService.build()).thenReturn(new int[targetWidth][targetHeight]);
        when(cavernService.setHeight(targetHeight)).thenReturn(cavernService);
        when(cavernService.setWidth(targetWidth)).thenReturn(cavernService);

        // When
        ResponseEntity response = mapController.getCavernMap(targetWidth, targetHeight);

        // Then
        verify(cavernService).setHeight(targetHeight);
        verify(cavernService).setWidth(targetWidth);
        verify(cavernService).build();
    }

    @Test
    public void shouldReturnMapJson() {
        // Given
        MapController mapController = new MapController(cavernService);

        int targetWidth = 2;
        int targetHeight = 2;

        when(cavernService.build()).thenReturn(new int[][]{{0,0},{0,0}});

        // When
        when(cavernService.build()).thenReturn(new int[targetWidth][targetHeight]);
        when(cavernService.setHeight(targetHeight)).thenReturn(cavernService);
        when(cavernService.setWidth(targetWidth)).thenReturn(cavernService);

        ResponseEntity response = mapController.getCavernMap(targetWidth, targetHeight);

        // Then
        verify(cavernService).build();
        System.out.println(response);
        assertThat(response.getBody(), is("[[0,0],[0,0]]"));
    }
}
