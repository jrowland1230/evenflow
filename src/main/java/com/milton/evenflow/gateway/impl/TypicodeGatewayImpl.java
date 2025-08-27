package com.milton.evenflow.gateway.impl;

import com.milton.evenflow.gateway.TypicodeGateway;
import com.milton.evenflow.model.CommentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
@Slf4j
public class TypicodeGatewayImpl implements TypicodeGateway {
    private final WebClient webClient;

    public TypicodeGatewayImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<CommentResponse> getComments(String postId, Long delay) {
        String uriString = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com/comments")
                .queryParam("postId", postId)
                .toUriString();

        return webClient.get()
                .uri(uriString)
                .retrieve()
                .bodyToFlux(CommentResponse.class)
                .delayElements(Duration.ofSeconds(delay));
    }
}
