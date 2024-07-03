package com.projetos.musicapiproject.musicapi.service;

import com.projetos.musicapiproject.musicapi.model.Music;
import com.projetos.musicapiproject.musicapi.repository.MusicRepository;

import java.util.List;
import java.util.Optional;

public class MusicService {

    private MusicRepository musicRepository;

    public List<Music> getAllMusic(){
        return musicRepository.findAll();
    }

    public Optional<Music> getMusicById(Long id) {
        return musicRepository.findById(id);
    }

    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    public void deleteMusic(Long id) {
        musicRepository.deleteById(id);
    }

}
