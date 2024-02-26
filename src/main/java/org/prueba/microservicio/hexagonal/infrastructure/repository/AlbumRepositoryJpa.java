package org.prueba.microservicio.hexagonal.infrastructure.repository;

import org.prueba.microservicio.hexagonal.infrastructure.model.jpa.JpaAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AlbumRepositoryJpa extends JpaRepository<JpaAlbum, Integer> {

    @Query(value = "truncate table album", nativeQuery = true)
    @Modifying
    void truncateTable();

}
