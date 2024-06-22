package org.prueba.microservicio.hexagonal.domain.service;

import org.prueba.microservicio.hexagonal.domain.model.Album;
import org.prueba.microservicio.hexagonal.domain.model.AlbumListDto;
import org.prueba.microservicio.hexagonal.domain.model.Photo;
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
        return new AlbumListDto(albumRepository.findAllAlbums());
    }

    @Override
    public AlbumListDto getAlbumListFromApi() {
        List<Album> albumList = sourceRepository.recoverAllAlbums();

        Map<Integer, List<Photo>> photoMap =
                sourceRepository.recoverAllPhotos().stream().collect(Collectors.groupingBy(Photo::getAlbumId));

        albumList.forEach(albumDto -> albumDto.setPhotos(photoMap.get(albumDto.getId())));

        return new AlbumListDto(albumList);
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
