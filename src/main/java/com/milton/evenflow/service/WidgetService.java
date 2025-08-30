package com.milton.evenflow.service;

import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WidgetService {
    Mono<User> getUsers(String postId);
    Flux<Comment> getComments(String postId, Long delay);
}
