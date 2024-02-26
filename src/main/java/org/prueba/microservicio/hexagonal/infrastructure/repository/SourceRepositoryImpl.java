package org.prueba.microservicio.hexagonal.infrastructure.repository;

import org.prueba.microservicio.hexagonal.domain.Album;
import org.prueba.microservicio.hexagonal.domain.Photo;
import org.prueba.microservicio.hexagonal.domain.repository.SourceRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SourceRepositoryImpl implements SourceRepository {

    private final String urlAlbums;

    private final String urlPhotos;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ParameterizedTypeReference<List<Photo>> photoListType = new ParameterizedTypeReference<>() {};

    private final ParameterizedTypeReference<List<Album>> albumListType = new ParameterizedTypeReference<>() {};

    public SourceRepositoryImpl(String urlAlbums, String urlPhotos) {
        this.urlAlbums = urlAlbums;
        this.urlPhotos = urlPhotos;
    }

    @Override
    public List<Photo> recoverAllPhotos() {
        return restTemplate.exchange(urlPhotos, HttpMethod.GET, null, photoListType).getBody();
    }

    @Override
    public List<Album> recoverAllAlbums() {
        return restTemplate.exchange(urlAlbums, HttpMethod.GET, null, albumListType).getBody();
    }

}