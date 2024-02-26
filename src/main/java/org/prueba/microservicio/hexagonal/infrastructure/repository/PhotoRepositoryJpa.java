package org.prueba.microservicio.hexagonal.infrastructure.repository;

import org.prueba.microservicio.hexagonal.infrastructure.model.jpa.JpaPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepositoryJpa extends JpaRepository<JpaPhoto, Integer> {

    @Query(value = "truncate table photo", nativeQuery = true)
    @Modifying
    void truncateTable();

}
