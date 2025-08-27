package com.milton.evenflow.controller;

import com.milton.evenflow.model.CommentResponse;
import com.milton.evenflow.model.CommentRequest;
import com.milton.evenflow.service.WidgetService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Validated
public class WidgetsController {

    private final WebClient webClient;
    private final WidgetService widgetService;

    public WidgetsController(WebClient webClient, WidgetService widgetService) {
        this.webClient = webClient;
        this.widgetService = widgetService;
    }

    @GetMapping("/users/{id}")
    public Mono<String> getUsers(@PathVariable String id) {

        String uriString = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com/users/")
                .path(id)
                .toUriString();

        return webClient.get()
            .uri(uriString)
            .retrieve()
            .bodyToMono(String.class);
    }

    @GetMapping(value = "/comments", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CommentResponse> getComments(@Valid CommentRequest commentRequest) {
        return widgetService.getComments(commentRequest.getPostId(), commentRequest.getDelay());
    }
}