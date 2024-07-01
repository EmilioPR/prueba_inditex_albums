package org.prueba.microservicio.hexagonal.infrastructure.configuration;

import org.junit.jupiter.api.Test;
import org.prueba.microservicio.hexagonal.domain.repository.AlbumRepository;
import org.prueba.microservicio.hexagonal.domain.repository.PhotoRepository;
import org.prueba.microservicio.hexagonal.domain.repository.SourceRepository;
import org.prueba.microservicio.hexagonal.domain.service.AlbumService;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanConfigurationTest {

    @Test
    void shouldLoadAllBeans() {
        new ApplicationContextRunner()
            .withUserConfiguration(BeanConfiguration.class)
            .withPropertyValues("data.source.api.url.albums=http://localhost:8080/albums",
                    "data.source.api.url.photos=http://localhost:8080/photos")
            .run(context -> {
                AlbumService albumService = context.getBean(AlbumService.class);
                SourceRepository sourceRepository = context.getBean(SourceRepository.class);
                PhotoRepository photoRepository = context.getBean(PhotoRepository.class);
                AlbumRepository albumRepository = context.getBean(AlbumRepository.class);

                assertNotNull(albumService);
                assertNotNull(sourceRepository);
                assertNotNull(photoRepository);
                assertNotNull(albumRepository);
            }
        );
    }

}
