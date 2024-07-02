package com.projetos.musicapiproject.musicapi.controller;


import com.projetos.musicapiproject.musicapi.model.Music;
import com.projetos.musicapiproject.musicapi.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/music")
@Tag(name = "Music", description = "API para gerenciar músicas")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping
    @Operation(sumary = "Obter todas as músicas")
    public List<Music> getAllMusic(){
        return musicService.getAllMusic();
    }

    @GetMapping("/{id}")
    @Operation(sumary = "Obter músicas por Id")
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        Optional<Music> music = musicService.getMusicById(id);
        return music.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

}
