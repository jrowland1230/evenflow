package com.milton.evenflow.service;

import com.milton.evenflow.model.CommentResponse;
import reactor.core.publisher.Flux;

public interface WidgetService {
    Flux<CommentResponse> getComments(String postId, Long delay);
}
