package com.app.MockingRecordLabel.controller;

import com.app.MockingRecordLabel.model.Artist;
import com.app.MockingRecordLabel.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping(path = "/get/list", produces = "application/json")
    public List<Artist> getArtistList() {
        return artistService.getArtistList();
    }

    @GetMapping(path = "/get/example", produces = "application/json")
    public List<Artist> getExampleList() {
        return artistService.getExampleList();
    }

    @GetMapping(path = "/get/{id}", produces = "application/json")
    public Artist getArtist(@PathVariable UUID id) {
        return artistService.getArtistById(id);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> addArtist(@RequestBody Artist newArtist) {
        return artistService.addArtist(newArtist);
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updateArtist(@RequestBody Artist updateArtist) {
        return artistService.updateArtist(updateArtist);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public HashMap<String, Object> deleteArtist(@PathVariable UUID id) {
        return artistService.deleteArtist(id);
    }
}
