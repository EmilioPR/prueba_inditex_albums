package org.prueba.microservicio.hexagonal.domain.service;

import org.prueba.microservicio.hexagonal.domain.Album;
import org.prueba.microservicio.hexagonal.domain.AlbumListDto;
import org.prueba.microservicio.hexagonal.domain.Photo;
import org.prueba.microservicio.hexagonal.domain.repository.AlbumRepository;
import org.prueba.microservicio.hexagonal.domain.repository.PhotoRepository;
import org.prueba.microservicio.hexagonal.domain.repository.SourceRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final PhotoRepository photoRepository;

    private final SourceRepository sourceRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, PhotoRepository photoRepository,
                            SourceRepository sourceRepository) {
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
        this.sourceRepository = sourceRepository;
    }

    @Override
    public AlbumListDto getAlbumListFromBBDD() {
        return AlbumListDto.builder().albums(albumRepository.findAllAlbums()).build();
    }

    @Override
    public AlbumListDto getAlbumListFromApi() {
        List<Album> albumList = sourceRepository.recoverAllAlbums();

        Map<Integer, List<Photo>> photoMap =
                sourceRepository.recoverAllPhotos().stream().collect(Collectors.groupingBy(Photo::getAlbumId));

        albumList.forEach(albumDto -> albumDto.setPhotos(photoMap.get(albumDto.getId())));

        return AlbumListDto.builder().albums(albumList).build();
    }

    @Override
    public boolean saveAllAlbums() {
        photoRepository.truncatePhotosTable();
        albumRepository.truncateAlbumsTable();
        albumRepository.saveAllAlbums(sourceRepository.recoverAllAlbums());
        photoRepository.saveAllPhotos(sourceRepository.recoverAllPhotos());
        return true;
    }

}
