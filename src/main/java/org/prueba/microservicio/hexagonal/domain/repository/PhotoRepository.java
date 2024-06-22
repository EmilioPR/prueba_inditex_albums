package org.prueba.microservicio.hexagonal.domain.repository;

import org.prueba.microservicio.hexagonal.domain.model.Photo;

import java.util.List;

public interface PhotoRepository {

    void truncatePhotosTable();

    void saveAllPhotos(List<Photo> photos);

}
