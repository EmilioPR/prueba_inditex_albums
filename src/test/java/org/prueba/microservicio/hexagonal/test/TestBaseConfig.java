package org.prueba.microservicio.hexagonal.test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

@Profile({"!test"})
@TestConfiguration
public class TestBaseConfig {

}
