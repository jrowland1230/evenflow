package com.milton.evenflow.controller;

import com.milton.evenflow.model.Address;
import com.milton.evenflow.model.CommentRequest;
import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.User;
import com.milton.evenflow.service.WidgetService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WidgetsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private WidgetService widgetService;

    // 🧪 Test Mono for GET /users/{id} with valid request
    @Test
    @Order(1)
    void testGetUsers() throws Exception {

        User user = User.builder()
                .id("1")
                .name("Leanne Grahamh")
                .username("Bret")
                .email("test@test.com")
                .address(Address.builder()
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .city("Gwenborough")
                        .zipcode("92998-3874")
                .build()).build();

        Mono<User> mockMono = Mono.just(user);
        Mockito.when(widgetService.getUser(1)).thenReturn(mockMono);

        webTestClient.get().uri("/api/v1/users/1")
                .header("Content-Type", "application/json")
                .exchange() // Perform the request
                .expectStatus().isOk() // Assert status is 200 OK
                .expectBody(User.class)
                .isEqualTo(user);
    }

    // 🧪 Test Flux for GET /comments with valid request
    @Test
    @Order(2)
    void testGetComments() {
        CommentRequest request = new CommentRequest();
        request.setId(1);
        request.setDelay(1L);

        Comment response1 = new Comment();
        response1.setId("1");
        response1.setPostId("1");
        Comment response2 = new Comment();
        response2.setId("1");
        response2.setPostId("2");
        Comment response3 = new Comment();
        response3.setId("1");
        response3.setPostId("2");
        Comment response4= new Comment();
        response4.setId("1");
        response4.setPostId("2");

        Flux<Comment> mockFlux = Flux.just(response1, response2, response3, response4);

        Mockito.when(widgetService.getComments(1,1L)).thenReturn(mockFlux);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/comments")
                        .queryParam("postId", "1")
                        .queryParam("delay", "1")
                        .build())
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody()
                .take(2)
                .collectList()
                .as(StepVerifier::create)
                .expectNextMatches(events ->
                        events.size() == 2)
                .verifyComplete();
    }
}
