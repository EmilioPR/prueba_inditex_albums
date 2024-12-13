package org.prueba.microservicio.hexagonal.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prueba.microservicio.hexagonal.domain.model.Album;
import org.prueba.microservicio.hexagonal.domain.model.AlbumListDto;
import org.prueba.microservicio.hexagonal.domain.model.Photo;
import org.prueba.microservicio.hexagonal.domain.repository.AlbumRepository;
import org.prueba.microservicio.hexagonal.domain.repository.PhotoRepository;
import org.prueba.microservicio.hexagonal.domain.repository.SourceRepository;
import org.prueba.microservicio.hexagonal.test.TestBase;
import org.prueba.microservicio.hexagonal.util.AlbumServiceImplTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Import({AlbumServiceImplTestConfig.class})
class AlbumServiceImplTest extends TestBase {

    @MockBean
    private AlbumRepository albumRepository;

    @MockBean
    private PhotoRepository photoRepository;

    @MockBean
    private SourceRepository dataService;

    @Autowired
    private AlbumServiceImpl underTest;

    @Test
    @DisplayName("Should get album list from BBDD")
    void shouldGetAlbumListFromBBDD() {
        var albums = List.of(
                new Album(1, "album1", 1,
                        List.of(new Photo(1, "title1", "url1", "thumbnailUrl1", 1))),
                new Album(2, "album2", 2,
                        List.of(new Photo(2, "title2", "url2", "thumbnailUrl2", 2)))
        );

        when(albumRepository.findAllAlbums()).thenReturn(albums);
        AlbumListDto albumListDto = underTest.getAlbumListFromBBDD();

        assertAll(
                () -> assertNotNull(albumListDto),
                () -> assertEquals(albums.size(), albumListDto.albums().size()),
                () -> IntStream.range(0, albums.size()).forEach(i -> {
                    Album album = albumListDto.albums().get(i);
                    Photo photo = album.getPhotos().get(0);

                    assertEquals(albums.get(i).getId(), album.getId());
                    assertEquals(albums.get(i).getTitle(), album.getTitle());
                    assertEquals(albums.get(i).getUserId(), album.getUserId());
                    assertEquals(albums.get(i).getPhotos().get(0).getId(), photo.getId());
                    assertEquals(albums.get(i).getPhotos().get(0).getTitle(), photo.getTitle());
                    assertEquals(albums.get(i).getPhotos().get(0).getUrl(), photo.getUrl());
                    assertEquals(albums.get(i).getPhotos().get(0).getThumbnailUrl(), photo.getThumbnailUrl());
                })
        );
    }

    @Test
    @DisplayName("Should get album list from API")
    void shouldGetAlbumListFromApi() {
        var albums = List.of(
                new Album(1, "album1", 1,
                        List.of(new Photo(1, "title1", "url1", "thumbnailUrl1", 1))),
                new Album(2, "album2", 2,
                        List.of(new Photo(2, "title2", "url2", "thumbnailUrl2", 2)))
        );

        var photos = List.of(
                new Photo(1, "title1", "url1", "thumbnailUrl1", 1),
                new Photo(2, "title2", "url2", "thumbnailUrl2", 2)
        );

        when(dataService.recoverAllAlbums()).thenReturn(albums);
        when(dataService.recoverAllPhotos()).thenReturn(photos);

        AlbumListDto albumListDto = underTest.getAlbumListFromApi();

        assertAll(
                () -> assertNotNull(albumListDto),
                () -> assertEquals(albums.size(), albumListDto.albums().size()),
                () -> IntStream.range(0, albums.size()).forEach(i -> {
                    Album album = albumListDto.albums().get(i);
                    Photo photo = album.getPhotos().get(0);

                    assertEquals(albums.get(i).getId(), album.getId());
                    assertEquals(albums.get(i).getTitle(), album.getTitle());
                    assertEquals(albums.get(i).getUserId(), album.getUserId());
                    assertEquals(albums.get(i).getPhotos().get(0).getId(), photo.getId());
                    assertEquals(albums.get(i).getPhotos().get(0).getTitle(), photo.getTitle());
                    assertEquals(albums.get(i).getPhotos().get(0).getUrl(), photo.getUrl());
                    assertEquals(albums.get(i).getPhotos().get(0).getThumbnailUrl(), photo.getThumbnailUrl());
                })
        );
    }

    @Test
    @DisplayName("Should save all albums")
    void shouldSaveAllAlbums() {
        assertTrue(underTest.saveAllAlbums());
    }

}
