package org.prueba.microservicio.hexagonal.domain.service;

import org.prueba.microservicio.hexagonal.domain.AlbumListDto;

public interface AlbumService {

    AlbumListDto getAlbumListFromBBDD();

    AlbumListDto getAlbumListFromApi();

    boolean saveAllAlbums();

}
