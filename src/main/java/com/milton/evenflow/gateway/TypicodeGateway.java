package com.milton.evenflow.gateway;

import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypicodeGateway {

    /**
     *
     * @param id identifier of user
     * @return user associated with user
     */
    Mono<User> getUser(Integer id);

    /**
     *
     * @param id identifier of comment
     * @param delay delay between responses in seconds
     * @return comments associated with postId
     */
    Flux<Comment> getComments(Integer id, Long delay);
}
