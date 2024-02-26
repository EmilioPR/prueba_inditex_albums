package org.prueba.microservicio.hexagonal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MicroservicioApplicationTests {

	@Autowired
	private ApplicationContext appContext;

	@Test
	void contextLoads() {
		assertNotNull(appContext);
	}

}
