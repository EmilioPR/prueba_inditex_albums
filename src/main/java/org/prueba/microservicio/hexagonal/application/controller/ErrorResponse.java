package org.prueba.microservicio.hexagonal.application.controller;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    
    private String error;

}
