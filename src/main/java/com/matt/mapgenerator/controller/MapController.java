package com.matt.mapgenerator.controller;

import com.google.gson.Gson;
import com.matt.mapgenerator.service.CavernService;
import com.matt.mapgenerator.utils.MapSaver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    CavernService cavernService;

    public MapController(CavernService cavernService) {
        this.cavernService = cavernService;
    }

    @GetMapping(value = "/cavern/{cavernWidth}/{cavernHeight}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCavernMap(@PathVariable("cavernWidth") int cavernWidth, @PathVariable("cavernHeight") int cavernHeight) {
        int[][] cavernMap = cavernService.setWidth(cavernWidth).setHeight(cavernHeight).build();

        if(cavernMap.length == 0) {
            return ResponseEntity.badRequest().body("Map length 0");
        }
        Gson gson = new Gson();
        String jsonMap = gson.toJson(cavernMap);

        MapSaver mapSaver = new MapSaver();
        mapSaver.saveMap(cavernMap);

        return new ResponseEntity(jsonMap, HttpStatus.OK);
    }
}