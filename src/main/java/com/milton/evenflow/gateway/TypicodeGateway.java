package com.milton.evenflow.gateway;

import com.milton.evenflow.model.CommentResponse;
import reactor.core.publisher.Flux;

public interface TypicodeGateway {
    Flux<CommentResponse> getComments(String postId, Long delay);
}
