package org.prueba.microservicio.hexagonal.infrastructure.repository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.prueba.microservicio.hexagonal.domain.model.Album;
import org.prueba.microservicio.hexagonal.domain.repository.AlbumRepository;
import org.prueba.microservicio.hexagonal.infrastructure.model.jpa.JpaAlbum;

import java.lang.reflect.Type;
import java.util.List;

public class H2AlbumRepositoryImpl implements AlbumRepository {

    private final AlbumRepositoryJpa albumRepositoryJpa;

    private final Type albumListType = new TypeToken<List<Album>>() {}.getType();

    private final Type jpaAlbumListType = new TypeToken<List<JpaAlbum>>() {}.getType();

    public H2AlbumRepositoryImpl(AlbumRepositoryJpa albumRepositoryJpa) {
        this.albumRepositoryJpa = albumRepositoryJpa;
    }

    @Override
    public void truncateAlbumsTable() {
        albumRepositoryJpa.deleteAll();
    }

    @Override
    public void saveAllAlbums(List<Album> albums) {
        albumRepositoryJpa.saveAll(new ModelMapper().map(albums, jpaAlbumListType));
    }

    @Override
    public List<Album> findAllAlbums() {
       return new ModelMapper().map(albumRepositoryJpa.findAll(), albumListType);
    }

}
