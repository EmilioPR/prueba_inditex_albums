package org.prueba.microservicio.hexagonal.infrastructure.model.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "album")
@Getter
@Setter
public class JpaAlbum {

    @Id
    private Integer id;

    private String title;

    private Integer userId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "albumId")
    private List<JpaPhoto> photos;

}
