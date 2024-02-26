package org.prueba.microservicio.hexagonal.domain.repository;

import org.prueba.microservicio.hexagonal.domain.Album;

import java.util.List;

public interface AlbumRepository {

    void truncateAlbumsTable();

    void saveAllAlbums(List<Album> albums);

    List<Album> findAllAlbums();

}
