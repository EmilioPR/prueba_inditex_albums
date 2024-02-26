package org.prueba.microservicio.hexagonal.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AlbumListDto {

    private List<Album> albums;

}
