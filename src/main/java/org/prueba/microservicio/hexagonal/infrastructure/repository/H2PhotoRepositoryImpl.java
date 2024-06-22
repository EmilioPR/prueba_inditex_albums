package org.prueba.microservicio.hexagonal.infrastructure.repository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.prueba.microservicio.hexagonal.domain.model.Photo;
import org.prueba.microservicio.hexagonal.domain.repository.PhotoRepository;
import org.prueba.microservicio.hexagonal.infrastructure.model.jpa.JpaPhoto;

import java.lang.reflect.Type;
import java.util.List;

public class H2PhotoRepositoryImpl implements PhotoRepository {

    private final PhotoRepositoryJpa photoRepositoryJpa;

    private final Type jpaPhotoListType = new TypeToken<List<JpaPhoto>>() {}.getType();

    public H2PhotoRepositoryImpl(PhotoRepositoryJpa photoRepositoryJpa) {
        this.photoRepositoryJpa = photoRepositoryJpa;
    }

    @Override
    public void truncatePhotosTable() {
        photoRepositoryJpa.deleteAll();
    }

    @Override
    public void saveAllPhotos(List<Photo> photos) {
        photoRepositoryJpa.saveAll(new ModelMapper().map(photos, jpaPhotoListType));
    }

}
