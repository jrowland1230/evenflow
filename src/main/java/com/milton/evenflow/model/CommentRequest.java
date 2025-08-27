package com.milton.evenflow.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {
    @NotBlank(message = "Name must not be blank")
    private String postId;
    @NotBlank(message = "Name must not be blank")
    private String delay;
}
