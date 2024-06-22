package org.prueba.microservicio.hexagonal.domain.repository;

import org.prueba.microservicio.hexagonal.domain.model.Album;
import org.prueba.microservicio.hexagonal.domain.model.Photo;

import java.util.List;

public interface SourceRepository {

    List<Photo> recoverAllPhotos();

    List<Album> recoverAllAlbums();

}
