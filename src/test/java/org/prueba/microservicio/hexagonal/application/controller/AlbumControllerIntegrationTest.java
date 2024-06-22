package org.prueba.microservicio.hexagonal.application.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class AlbumControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockServer;

    @BeforeEach
    void beforeEach() throws IOException, URISyntaxException {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        setupWireMockStub("/albums", "json/albums.json");
        setupWireMockStub("/photos", "json/photos.json");
    }

    @AfterEach
    void afterEach() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Should get all albums from API")
    void shouldGetAllAlbumsFromApi() throws Exception {
        mockMvc.perform(get("/album/albums_from_api"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResourceFileAsString("json/albums_result.json")));
    }

    @Test
    @DisplayName("Should save all albums and get all albums from BBDD")
    void shouldSaveAllAlbumsAndDoAllAlbumsFromBBDD() throws Exception {
        mockMvc.perform(post("/album/save_all_albums"))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/album/albums_from_bbdd"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResourceFileAsString("json/albums_result.json")));
    }

    private String getResourceFileAsString(String fileName) throws IOException, URISyntaxException {
        return Files.readString(Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName))
                .toURI()));
    }

    private void setupWireMockStub(String url, String responseFile) throws IOException, URISyntaxException {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getResourceFileAsString(responseFile))));
    }

}