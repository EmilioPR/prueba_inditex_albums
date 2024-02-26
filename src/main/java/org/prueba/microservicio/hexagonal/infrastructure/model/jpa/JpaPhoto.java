package org.prueba.microservicio.hexagonal.infrastructure.model.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "photo", indexes = @Index(name = "idx_photo_album_id", columnList = "albumId"))
@Getter
@Setter
public class JpaPhoto {

    @Id
    private Integer id;

    private String title;

    private String url;

    private String thumbnailUrl;

    private Integer albumId;

}
