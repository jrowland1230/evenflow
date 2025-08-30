package com.milton.evenflow.controller;

import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.CommentRequest;
import com.milton.evenflow.model.User;
import com.milton.evenflow.service.WidgetService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Validated
public class WidgetsController {

    private final WidgetService widgetService;

    public WidgetsController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @GetMapping(value = "/users/{id}")
    public Mono<User> getUsers(@PathVariable String id) {
        return widgetService.getUsers(id);
    }

    @GetMapping(value = "/comments", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comment> getComments(@Valid CommentRequest commentRequest) {
        return widgetService.getComments(commentRequest.getPostId(), commentRequest.getDelay());
    }
}