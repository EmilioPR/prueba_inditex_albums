package org.prueba.microservicio.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    private Integer id;

    private String title;

    private String url;

    private String thumbnailUrl;

    private Integer albumId;

}
