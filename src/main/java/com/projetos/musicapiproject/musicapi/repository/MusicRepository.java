package com.projetos.musicapiproject.musicapi.repository;

import com.projetos.musicapiproject.musicapi.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
