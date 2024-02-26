package org.prueba.microservicio.hexagonal.domain.repository;

import org.prueba.microservicio.hexagonal.domain.Album;
import org.prueba.microservicio.hexagonal.domain.Photo;

import java.util.List;

public interface SourceRepository {

    List<Photo> recoverAllPhotos();

    List<Album> recoverAllAlbums();

}
