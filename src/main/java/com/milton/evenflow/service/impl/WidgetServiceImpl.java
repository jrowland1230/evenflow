package com.milton.evenflow.service.impl;

import com.milton.evenflow.gateway.TypicodeGateway;
import com.milton.evenflow.model.CommentResponse;
import com.milton.evenflow.service.WidgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class WidgetServiceImpl implements WidgetService {

    private final TypicodeGateway typicodeGateway;

    public WidgetServiceImpl(TypicodeGateway typicodeGateway) {
        this.typicodeGateway = typicodeGateway;
    }

    @Override
    public Flux<CommentResponse> getComments(String postId, Long delay) {
        return typicodeGateway.getComments(postId, delay);
    }
}