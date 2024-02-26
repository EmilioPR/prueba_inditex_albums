package org.prueba.microservicio.hexagonal.infrastructure.configuration;

import org.prueba.microservicio.hexagonal.MicroservicioApplication;
import org.prueba.microservicio.hexagonal.domain.repository.*;
import org.prueba.microservicio.hexagonal.domain.service.*;
import org.prueba.microservicio.hexagonal.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = MicroservicioApplication.class)
public class BeanConfiguration {

    @Value("${data.source.api.url.albums}")
    private String urlAlbums;

    @Value("${data.source.api.url.photos}")
    private String urlPhotos;

    @Bean
    public AlbumService albumService(AlbumRepository albumRepository, PhotoRepository photoRepository,
            SourceRepository sourceRepository) {
        return new AlbumServiceImpl(albumRepository, photoRepository, sourceRepository);
    }

    @Bean
    public SourceRepository sourceRepository() {
        return new SourceRepositoryImpl(urlAlbums, urlPhotos);
    }

    @Bean
    public PhotoRepository photoRepository(PhotoRepositoryJpa photoRepositoryJpa) {
        return new H2PhotoRepositoryImpl(photoRepositoryJpa);
    }

    @Bean
    public AlbumRepository albumRepository(AlbumRepositoryJpa albumRepositoryJpa) {
        return new H2AlbumRepositoryImpl(albumRepositoryJpa);
    }

}
