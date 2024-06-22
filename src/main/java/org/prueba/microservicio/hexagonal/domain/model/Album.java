package org.prueba.microservicio.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private Integer id;

    private String title;

    private Integer userId;

    private List<Photo> photos;

}
