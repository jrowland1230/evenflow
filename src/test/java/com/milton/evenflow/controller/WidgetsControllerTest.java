package com.milton.evenflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(WidgetsController.class)
public class WidgetsControllerTest {

    @Autowired
    WebTestClient webTestClient;


}
