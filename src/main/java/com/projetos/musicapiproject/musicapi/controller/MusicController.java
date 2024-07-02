package com.projetos.musicapiproject.musicapi.controller;


import com.projetos.musicapiproject.musicapi.model.Music;
import com.projetos.musicapiproject.musicapi.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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


    private MusicService musicService;

    @GetMapping
    @Operation(summary = "Obter todas as músicas")
    public List<Music> getAllMusic() {
        return musicService.getAllMusic();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter músicas por Id")
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        Optional<Music> music = musicService.getMusicById(id);
        return music.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @Operation(summary = "Criar musica nova")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Music> createMusic(@RequestBody Music music) {
        Music savedMusic = musicService.saveMusic(music);
        return new ResponseEntity<>(savedMusic, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Atualizar musica")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Music> updateMusic(@PathVariable long id, @RequestBody Music musicDetails) {
        Optional<Music> music = musicService.getMusicById(id);
        if (music.isPresent()) {
            Music updatedMusic = music.get();
            updatedMusic.setTitle(musicDetails.getTitle());
            updatedMusic.setArtist(musicDetails.getArtist());
            updatedMusic.setAlbum(musicDetails.getAlbum());
            updatedMusic.setYear(musicDetails.getYear());
            musicService.saveMusic(updatedMusic);
        } else {
            return ResponseEntity.notFound().build();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar música")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return ResponseEntity.noContent().build();
    }
}
