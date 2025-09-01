package com.milton.evenflow.controller;

import com.milton.evenflow.model.Comment;
import com.milton.evenflow.model.CommentRequest;
import com.milton.evenflow.model.User;
import com.milton.evenflow.service.WidgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "returns a user for a given id", operationId = "getUsers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    public Mono<User> getUsers(@PathVariable String id) {
        return widgetService.getUsers(id);
    }

    @GetMapping(value = "/comments", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "returns a list of comments for a given id", operationId = "getComments")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    public Flux<Comment> getComments(@Valid CommentRequest commentRequest) {
        return widgetService.getComments(commentRequest.getPostId(), commentRequest.getDelay());
    }
}