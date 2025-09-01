package com.milton.evenflow.gateway.impl;

import com.milton.evenflow.gateway.TypicodeGateway;
import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
public class TypicodeGatewayImpl implements TypicodeGateway {
    private final WebClient webClient;

    public TypicodeGatewayImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<User> getUser(Integer id) {
        String uriString = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com/users/")
                .path(id.toString())
                .toUriString(); //TODO add configuration properties for url

        return webClient.get()
                .uri(uriString)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new Exception()) //TODO add custom exception handling
                )
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new Exception()) //TODO add custom exception handling
                )
                .bodyToMono(User.class);
    }

    @Override
    public Flux<Comment> getComments(Integer id, Long delay) {
        String uriString = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com/comments")
                .queryParam("postId", id)
                .toUriString(); //TODO add configuration properties for url

        return webClient.get()
                .uri(uriString)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new Exception()) //TODO add custom exception handling
                )
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new Exception()) //TODO add custom exception handling
                )
                .bodyToFlux(Comment.class)
                .delayElements(Duration.ofSeconds(delay));
    }
}
