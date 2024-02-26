package org.prueba.microservicio.hexagonal.application.controller;

import org.prueba.microservicio.hexagonal.domain.AlbumListDto;
import org.prueba.microservicio.hexagonal.domain.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums_from_bbdd")
    public AlbumListDto getAllAlbumsFromBBDD() {
        return albumService.getAlbumListFromBBDD();
    }

    @GetMapping("/albums_from_api")
    public AlbumListDto getAllAlbumsFromApi() {
        return albumService.getAlbumListFromApi();
    }

    @PostMapping("/save_all_albums")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAllAlbums() {
        albumService.saveAllAlbums();
    }

}