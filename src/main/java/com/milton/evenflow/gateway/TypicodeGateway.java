package com.milton.evenflow.gateway;

import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypicodeGateway {

    /**
     *
     * @param postId identifier of user
     * @return user associated with user
     */
    Mono<User> getUsers(String postId);

    /**
     *
     * @param postId identifier of comment
     * @param delay delay between responses in seconds
     * @return comments associated with postId
     */
    Flux<Comment> getComments(String postId, Long delay);
}
