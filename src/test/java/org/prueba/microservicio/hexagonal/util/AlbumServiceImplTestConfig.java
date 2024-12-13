package org.prueba.microservicio.hexagonal.util;

import org.prueba.microservicio.hexagonal.domain.repository.AlbumRepository;
import org.prueba.microservicio.hexagonal.domain.repository.PhotoRepository;
import org.prueba.microservicio.hexagonal.domain.repository.SourceRepository;
import org.prueba.microservicio.hexagonal.domain.service.AlbumServiceImpl;
import org.prueba.microservicio.hexagonal.test.TestBaseConfig;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

public class AlbumServiceImplTestConfig extends TestBaseConfig {

    @Bean
    public AlbumServiceImpl albumService(AlbumRepository albumRepository, PhotoRepository photoRepository,
                                         SourceRepository dataService) {
        return new AlbumServiceImpl(albumRepository, photoRepository, dataService);
    }

    @Bean
    public AlbumRepository albumRepository() {
        return mock(AlbumRepository.class);
    }

    @Bean
    public PhotoRepository photoRepository() {
        return mock(PhotoRepository.class);
    }

    @Bean
    public SourceRepository dataService() {
        return mock(SourceRepository.class);
    }

}
